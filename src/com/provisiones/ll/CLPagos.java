package com.provisiones.ll;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMGastos;
import com.provisiones.dal.qm.QMPagos;
import com.provisiones.dal.qm.QMProvisiones;
import com.provisiones.dal.qm.QMTransferenciasN34;
import com.provisiones.dal.qm.listas.QMListaAbonosGastos;
import com.provisiones.dal.qm.listas.QMListaGastos;
import com.provisiones.dal.qm.listas.QMListaGastosProvisiones;
import com.provisiones.dal.qm.movimientos.QMMovimientosGastos;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Cuenta;
import com.provisiones.types.Nota;
import com.provisiones.types.Pago;
import com.provisiones.types.RecargoImporte;
import com.provisiones.types.movimientos.MovimientoGasto;
import com.provisiones.types.tablas.GastoTabla;
import com.provisiones.types.transferencias.N34.TransferenciaN34;

public class CLPagos 
{
	//TODO EN CONTRUCCION
	private static Logger logger = LoggerFactory.getLogger(CLPagos.class.getName());

	private CLPagos(){}
	
	//ID
	public static long buscarCodigoPago (long liCodGasto)
	{
		return QMPagos.getPagoID(ConnectionManager.getDBConnection(),liCodGasto);
	}
	
	public static Pago buscarPago (long liCodPago)
	{
		return QMPagos.getPago(ConnectionManager.getDBConnection(),liCodPago);
	}
	
	public static String buscarFechaPago(long liPagoID)
	{
		return QMPagos.getFechaPago(ConnectionManager.getDBConnection(),liPagoID);
	}
	
	public static long buscarNumeroPagosTransferenciasN34SinEnviar()
	{
		return QMPagos.buscaCantidadPagosSinEnviarPorTipo(ConnectionManager.getDBConnection(),ValoresDefecto.DEF_PAGO_NORMA34);
	}
	
	public static ArrayList<Integer> buscarActivosPagoSinEnviar()
	{
		return QMPagos.buscarActivosPagoEnvio(ConnectionManager.getDBConnection(),ValoresDefecto.PAGO_EMITIDO);
	}
	
	/*public static ArrayList<Long> buscarPagosSinEnviar()
	{
		return QMPagos.buscarPagoEnvio(ConnectionManager.getDBConnection(),ValoresDefecto.PAGO_EMITIDO);
	}*/
	
	public static ArrayList<Long> buscarPagosSinEnviar(String sNUPROF)
	{
		return QMPagos.buscarPagosEmitidosProvision(ConnectionManager.getDBConnection(),sNUPROF);
	}
	
	public static boolean estaPagado(long liCodGasto)
	{
		return QMPagos.existePago(ConnectionManager.getDBConnection(),liCodGasto);
	}
	
	public static boolean estaAbonado(long liCodGasto)
	{
		return QMListaAbonosGastos.existeRelacionAbono(ConnectionManager.getDBConnection(),liCodGasto);
	}
	
	public static int validaPago(Pago pago, Cuenta cuenta)
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
			else if (pago.getsTipoPago().equals(ValoresDefecto.DEF_PAGO_NORMA34) && !Utils.compruebaCC(cuenta.getsNUCCEN(), cuenta.getsNUCCOF(), cuenta.getsNUCCDI(), cuenta.getsNUCCNT()))
			{
				//ERROR 002 - Datos de cuenta incorrectos.
				iCodigo = -2;
			}
			else if (pago.getsFEPGPR().equals("#"))
			{
				//ERROR 003 - La fecha de pago no es correcta.
				iCodigo = -3;
			}
			/*else if (Long.parseLong(QMGastos.getFEDEVE(conexion, liCodGasto)) > Long.parseLong(pago.getsFEPGPR()))
			{
				//ERROR 004 - Fecha de devengo anterior a la fecha de pago.
				iCodigo = -4;
			}*/			
			
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
									if (QMGastos.setPagadoConexion(conexion,liCodGasto, movimiento.getFMPAGN()))
									{
										if (QMProvisiones.setGastoAnuladoConexion(conexion, movimiento.getNUPROF(), QMGastos.getValorTotal(conexion, liCodGasto)))
										{
											//OK 
											iCodigo = 0;
										}
										else
										{
											//error al actualizar la provisión
											iCodigo = -909;
										}
									}
									else
									{
										//error estado no establecido - Rollback
										//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
										//QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
										//QMListaGastosProvisiones.delRelacionGastoProvision(conexion,liCodGasto);
										//QMListaGastosProvisiones.addRelacionGastoProvision(conexion,liCodGasto, sNUPROF);
										iCodigo = -903;
										
									}
								}
								else
								{
									//error estado no establecido - Rollback
									//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
									//QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
									//QMListaGastosProvisiones.addRelacionGastoProvision(conexion,liCodGasto, sNUPROF);
									iCodigo = -905;
									
								}
							}
							else
							{
								//error estado no establecido - Rollback
								//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
								//QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
								iCodigo = -905;
								
							}


						}
						else
						{
							//error relacion gasto no creada - Rollback
							//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
							iCodigo = -902;
							
						}
					}
					
					if (iCodigo == 0)
					{
						//Comprobamos el estado
						String sNUPROF = QMListaGastosProvisiones.getProvisionDeGasto(conexion, liCodGasto);
						
						if(QMProvisiones.provisionPagada(conexion, sNUPROF))
						{
							//Cambiamos su estado a pagada.
							if (!QMProvisiones.setFechaPagado(conexion, sNUPROF, Utils.fechaDeHoy(false))
								|| !QMProvisiones.setEstado(conexion, sNUPROF,ValoresDefecto.DEF_PROVISION_PAGADA))
							{
								iCodigo = -909;
							}
						}
					}

					
					if (iCodigo == 0)
					{
						conexion.commit();
					}
					else
					{
						conexion.rollback();
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

	public static int registraPagoSimple(Pago pago, Cuenta cuenta, boolean bValida, Nota nota)
	{
		int iCodigo = -910;//Error de conexion

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			
			iCodigo = 0;
			
			if (bValida)
			{
				iCodigo = validaPago(pago, cuenta);
				
				logger.debug("validado.");
			}
			
			if (iCodigo == 0)
			{
				try
				{
					conexion.setAutoCommit(false);
					
					long liCodGasto = Long.parseLong(pago.getsGasto());
					
					String sNUPROF = QMListaGastosProvisiones.getProvisionDeGasto(conexion, liCodGasto);
					
					if (QMListaGastos.setResuelto(conexion, liCodGasto))
					{
						if (QMListaGastosProvisiones.setResuelto(conexion, liCodGasto))
						{
							if (CLGastos.existeBloqueo(liCodGasto))
							{
								if (QMGastos.setAbonado(conexion, liCodGasto, pago.getsFEPGPR()))
								{
									if (QMListaGastos.setDesbloqueado(conexion, liCodGasto))
									{
										if (QMListaGastosProvisiones.setDesbloqueado(conexion, liCodGasto))
										{
											iCodigo = 0;
										}
										else
										{
											//error al desbloquear la provision del gasto - Rollback
											iCodigo = -907;
										}
									}
									else
									{
										//error al desbloquear el gasto - Rollback
										iCodigo = -908;
									}
									
								}
								else
								{
									//error al establecer el estado del gasto - Rollback
									iCodigo = -905;
								}
							}
							else
							{
								if (QMGastos.getCOSIGA(conexion, liCodGasto).equals(ValoresDefecto.DEF_GASTO_ABONADO))
								{
									if (QMGastos.setPagadoAbonado(conexion, liCodGasto, pago.getsFEPGPR()))
									{
										iCodigo = 0;
									}
									else
									{
										//error al establecer el estado del gasto - Rollback
										iCodigo = -905;
									}
								}
								else
								{
									if (QMGastos.setPagado(conexion, liCodGasto, pago.getsFEPGPR()))
									{
										iCodigo = 0;
									}
									else
									{
										//error al establecer el estado del gasto - Rollback
										iCodigo = -905;
									}
								}

							}
							
							if (iCodigo == 0)
							{
								if (pago.getsTipoPago().equals(ValoresDefecto.DEF_PAGO_NORMA34))
								{
									long liRecargo = Utils.redondeaRecargo(Long.parseLong(pago.getsRecargoAdicional()));

									TransferenciaN34 transferencia = CLTransferencias.generarTransferenciaN34(liCodGasto, cuenta, liRecargo);
									long liCodTransferencia = QMTransferenciasN34.addTransferencia(conexion, transferencia);
									
									if (liCodTransferencia != 0)
									{
										pago.setsCodOperacion(Long.toString(liCodTransferencia));

										if (QMPagos.addPago(conexion, pago, ValoresDefecto.PAGO_EMITIDO) != 0)
										{
											
											iCodigo = 0;
										}
										else
										{
											//error al crear el pago
											iCodigo = -900;
										}
									}
									else
									{
										//error al crear la transferencia pago
										iCodigo = -906;
									}
								}
								else
								{
									if (QMPagos.addPago(conexion, pago, ValoresDefecto.PAGO_ENVIADO) != 0)
									{
										
										iCodigo = 0;
									}
									else
									{
										//error al crear el pago
										
										iCodigo = -900;
									}
								}
								
								if (iCodigo == 0)
								{
									logger.debug("pago.getsRecargoAdicional():|"+pago.getsRecargoAdicional()+"|");
									if (QMProvisiones.setGastoPagado(conexion,sNUPROF, QMGastos.getValorTotal(conexion, liCodGasto), pago.getsRecargoAdicional()))
									{
										//OK 
										iCodigo = 0;
									}
									else
									{
										//error al actualizar la provisión
										iCodigo = -909;
									}
								}
							}
							
			
						}
						else
						{
							//error sin revision - Rollback
							
							iCodigo = -904;
						}
					}
					else
					{
						//error estado no establecido - Rollback
						
						iCodigo = -903;
					}
					if (iCodigo == 0)
					{
						
						if(QMProvisiones.provisionPagada(conexion, sNUPROF))
						{
							//Cambiamos su estado a pagada.
							if (!QMProvisiones.setFechaPagado(conexion, sNUPROF, pago.getsFEPGPR()) 
								|| !QMProvisiones.setEstado(conexion, sNUPROF,ValoresDefecto.DEF_PROVISION_PAGADA))
							{
								iCodigo = -909;
							}
						}
					}

					
					if (iCodigo == 0)
					{
						conexion.commit();
					}
					else
					{
						conexion.rollback();
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
	
	public static int registraPagoComunidad(long iCodComunidad, String sNUPROF, String sTipoPago, String sFEPGPR, Cuenta cuenta, RecargoImporte recargo, boolean bValida, Nota nota)
	{
		int iCodigo = -910;//Error de conexion

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			ArrayList<GastoTabla> listagastos = CLGastos.buscarGastosPagablesProvisionComunidad(sNUPROF,iCodComunidad);
			
            for (int i = 0; i < listagastos.size() ; i++)
            {
            	String sRecargo = "0";
            	
            	GastoTabla gasto = listagastos.get(i);
            	
            	String sPago = sTipoPago;
            	
            	long liValorTotal = QMGastos.getValorTotal(conexion, Long.parseLong(gasto.getsGastoID()));
            	
            	if (gasto.getIMNGAS().startsWith("-"))
            	{
            		sPago = ValoresDefecto.DEF_PAGO_DEVOLUCION;
            	}
            	else if (liValorTotal == 0)
				{
            		sPago = ValoresDefecto.DEF_PAGO_VENTANILLA;
				}            	
            	
            	if (recargo.getsTipoRecargo().equals(ValoresDefecto.DEF_RECARGO_FIJO))
            	{
            		sRecargo = recargo.getsValorRecargo()+"0000";
            	}
            	else if (recargo.getsTipoRecargo().equals(ValoresDefecto.DEF_RECARGO_PROPORCIONAL))
            	{
            		/*
            		long liRedondeo = 0;
            		
            		//BOE-A-1998-29216 - Artículo 11. Redondeo.
            		if (((liValorTotal * Long.parseLong(sValorRecargo))%10000) >5555)
            		{
            			liRedondeo = 1;
            		}
            		
            		sRecargo = Long.toString(((liValorTotal * Long.parseLong(sValorRecargo))/10000)+liRedondeo);
            		*/
            		
            		sRecargo = Long.toString(liValorTotal * Long.parseLong(recargo.getsValorRecargo()));
            	}
            	
            	logger.debug("sRecargo:|"+sRecargo+"|");
            	
            	Pago pago = new Pago(gasto.getCOACES(),gasto.getsGastoID(),sPago,ValoresDefecto.CAMPO_NUME_SIN_INFORMAR,sFEPGPR,sRecargo);
            	
            	iCodigo = registraPagoSimple(pago, cuenta, bValida, nota);
            	
            	if (iCodigo != 0)
            	{
            		i = listagastos.size();
            	}
            }
		}
		
		return iCodigo;
	}
	
	public static int registraPagoProvision(String sNUPROF, String sTipoPago, String sFEPGPR, Cuenta cuenta, RecargoImporte recargo, boolean bValida, Nota nota)
	{
		int iCodigo = -910;//Error de conexion

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			ArrayList<GastoTabla> listagastos = CLGastos.buscarGastosPagablesProvision(sNUPROF);
			
            for (int i = 0; i < listagastos.size() ; i++)
            {
            	String sRecargo = "0";
            	
            	GastoTabla gasto = listagastos.get(i);
            	
            	String sPago = sTipoPago;
            	
            	long liValorTotal = QMGastos.getValorTotal(conexion, Long.parseLong(gasto.getsGastoID()));
            	
            	if (gasto.getIMNGAS().startsWith("-"))
            	{
            		sPago = ValoresDefecto.DEF_PAGO_DEVOLUCION;
            	}
            	else if (liValorTotal == 0)
				{
            		sPago = ValoresDefecto.DEF_PAGO_VENTANILLA;
				}
            	
            	if (recargo.getsTipoRecargo().equals(ValoresDefecto.DEF_RECARGO_FIJO))
            	{
            		sRecargo = recargo.getsValorRecargo()+"0000";
            	}
            	else if (recargo.getsTipoRecargo().equals(ValoresDefecto.DEF_RECARGO_PROPORCIONAL))
            	{
            		/*
            		long liRedondeo = 0;
            		
            		//BOE-A-1998-29216 - Artículo 11. Redondeo.
            		if (((liValorTotal * Long.parseLong(sValorRecargo))%10000) >5555)
            		{
            			liRedondeo = 1;
            		}
            		
            		sRecargo = Long.toString(((liValorTotal * Long.parseLong(sValorRecargo))/10000)+liRedondeo);
            		*/
            		
            		sRecargo = Long.toString(liValorTotal * Long.parseLong(recargo.getsValorRecargo()));
            	}
            	
            	logger.debug("sRecargo:|"+sRecargo+"|");
            	
            	Pago pago = new Pago(gasto.getCOACES(),gasto.getsGastoID(),sPago,ValoresDefecto.CAMPO_NUME_SIN_INFORMAR,sFEPGPR, sRecargo);
            	
            	iCodigo = registraPagoSimple(pago, cuenta, bValida, nota);
            	
            	if (iCodigo != 0)
            	{
            		i = listagastos.size();
            	}
            }
		}
		
		return iCodigo;
	}
}
