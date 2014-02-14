package com.provisiones.ll;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMCuotas;
import com.provisiones.dal.qm.QMGastos;
import com.provisiones.dal.qm.QMPagos;
import com.provisiones.dal.qm.listas.QMListaGastos;
import com.provisiones.dal.qm.listas.QMListaGastosProvisiones;
import com.provisiones.dal.qm.movimientos.QMMovimientosGastos;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Pago;
import com.provisiones.types.movimientos.MovimientoGasto;

public class CLPagos 
{
	//TODO EN CONTRUCCION
	private static Logger logger = LoggerFactory.getLogger(CLPagos.class.getName());

	private CLPagos(){}
	
	//ID
	public static long buscarCodigoPago (String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC)
	{
		return QMCuotas.getCuotaID(ConnectionManager.getDBConnection(),Integer.parseInt(sCodCOACES), sCodCOCLDO, sCodNUDCOM, sCodCOSBAC);
	}
	
	public static String buscarFechaPago(String sGastoID)
	{
		return QMPagos.getFechaPago(ConnectionManager.getDBConnection(),sGastoID);
	}
	
	public static int validaPago(Pago pago)
	{
		int iCodigo = 0;

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			logger.debug("validando moviviento...");
			
			long liCodGasto = Long.parseLong(pago.getsGasto());
			
			if (!QMGastos.getEstado(conexion, liCodGasto).equals(ValoresDefecto.DEF_GASTO_AUTORIZADO))
			{
				//ERROR 001 - El gasto no esta autorizado.
				iCodigo = -1;
			}
			else if (Utils.compruebaCC(pago.getsNUCCEN(), pago.getsNUCCOF(), pago.getsNUCCDI(), pago.getsNUCCNT()))
			{
				//ERROR 002 - Datos de cuenta incorrectos.
				iCodigo = -2;
			}
			else if (pago.getsFEPGPR().equals("#"))
			{
				//ERROR 003 - La fecha de pago no es correcta.
				iCodigo = -3;
			}
					
			
		}
		
		return iCodigo;
	}
	
	public static int registraPagoConexion(MovimientoGasto movimiento)
	{
		int iCodigo = -910;//Error de conexion

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			long liCodGasto = CLGastos.buscarCodigoGasto(Integer.parseInt(movimiento.getCOACES()), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE());
			
			logger.debug("Comprobando estado...");
			
			String sEstado = QMGastos.getEstado(conexion,liCodGasto);

			
			logger.debug("Estado:|"+sEstado+"|");
			logger.debug("Nuevo Estado:|"+movimiento.getCOSIGA()+"|");
			
			if (!sEstado.equals(ValoresDefecto.DEF_GASTO_CONOCIDO) && !sEstado.equals(ValoresDefecto.DEF_GASTO_ESTIMADO))//comprobar estado gasto y estado de movimiento
			{
				//Error gasto ya procesado.
				iCodigo = -1;
			}
			else
			{
				try 
				{
					conexion.setAutoCommit(false);


					//movimiento.setIMNGAS("-"+movimiento.getIMNGAS());
					long liCodMovimiento = QMMovimientosGastos.addMovimientoGasto(conexion,movimiento);

					if (liCodMovimiento == 0)
					{
						//error al crear un movimiento
						iCodigo = -900;
					}
					else
					{	
						
						if (QMListaGastos.addRelacionGasto(conexion,liCodGasto,liCodMovimiento))
						{
							//String sNUPROF = QMListaGastosProvisiones.getProvisionDeGasto(conexion, liCodGasto);
							
							if (QMListaGastosProvisiones.delRelacionGastoProvision(conexion,liCodGasto))
							{
								if (QMListaGastosProvisiones.addRelacionGastoProvision(conexion,liCodGasto, movimiento.getNUPROF()))
								{
									//Abonado
									if (QMGastos.setEstado(conexion,liCodGasto, "6"))
									{
										//OK 
										iCodigo = 0;
										conexion.commit();
									}
									else
									{
										//error estado no establecido - Rollback
										//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
										//QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
										//QMListaGastosProvisiones.delRelacionGastoProvision(conexion,liCodGasto);
										//QMListaGastosProvisiones.addRelacionGastoProvision(conexion,liCodGasto, sNUPROF);
										iCodigo = -903;
										conexion.rollback();
									}
								}
								else
								{
									//error estado no establecido - Rollback
									//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
									//QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
									//QMListaGastosProvisiones.addRelacionGastoProvision(conexion,liCodGasto, sNUPROF);
									iCodigo = -905;
									conexion.rollback();
								}
							}
							else
							{
								//error estado no establecido - Rollback
								//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
								//QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
								iCodigo = -905;
								conexion.rollback();
							}


						}
						else
						{
							//error relacion gasto no creada - Rollback
							//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
							iCodigo = -902;
							conexion.rollback();
						}
					}
					
					conexion.setAutoCommit(true);
				
				} 
				catch (SQLException e) 
				{
					//error de conexion con base de datos.
					iCodigo = -910;
					try 
					{
						//reintentamos
						conexion.rollback();
						conexion.setAutoCommit(true);
						conexion.close();
					} 
					catch (SQLException e1) 
					{
						try 
						{
							conexion.close();
						}
						catch (SQLException e2) 
						{
							logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
						}
					}
				}
			}
		}		

		logger.debug("iCodigo:|"+iCodigo+"|");
		
		return iCodigo;
	}

	public static int registraPagoSimple(Pago pago, boolean bValida)
	{
		int iCodigo = -910;//Error de conexion

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			
			iCodigo = 0;
			
			if (bValida)
			{
				iCodigo = validaPago(pago);
				
				logger.debug("validado.");
			}
			
			if (iCodigo == 0)
			{
				try
				{
					conexion.setAutoCommit(false);
					
					long liCodGasto = Long.parseLong(pago.getsGasto());
					
					if (QMListaGastos.setValidado(conexion, liCodGasto, ValoresDefecto.DEF_MOVIMIENTO_RESUELTO))
					{
						if (QMListaGastosProvisiones.setRevisado(conexion, liCodGasto, ValoresDefecto.DEF_MOVIMIENTO_RESUELTO))
						{
							String sEstado = "";
							if (QMGastos.esAbono(conexion, liCodGasto))
							{
								sEstado = ValoresDefecto.DEF_GASTO_ABONADO;
							}
							else
							{
								sEstado = ValoresDefecto.DEF_GASTO_PAGADO;
							}
							
							if (QMGastos.setEstado(conexion, liCodGasto, sEstado))
							{
								if (QMPagos.addPago(conexion, pago) == 0)
								{
									//error al crear el pago
									conexion.rollback();
									iCodigo = -900;
								}
								else
								{
									conexion.commit();
									iCodigo = 0;
								}
							}
							else
							{
								//error al establecer el estado del gasto - Rollback
								conexion.rollback();
								iCodigo = -905;
							}
						}
						else
						{
							//error sin revision - Rollback
							conexion.rollback();
							iCodigo = -904;
						}
					}
					else
					{
						//error estado no establecido - Rollback
						conexion.rollback();
						iCodigo = -903;
					}

					conexion.setAutoCommit(true);
				}
				catch (SQLException e) 
				{
					//error de conexion con base de datos.
					iCodigo = -910;

					try 
					{
						//reintentamos
						conexion.rollback();
						conexion.setAutoCommit(true);
						conexion.close();
					} 
					catch (SQLException e1) 
					{
						try 
						{
							conexion.close();
						}
						catch (SQLException e2) 
						{
							logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
						}
					}
				}
				
				

				
			}
		}
		
		return iCodigo;
	}
}
