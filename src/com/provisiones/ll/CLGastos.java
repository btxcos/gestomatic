package com.provisiones.ll;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMCodigosControl;
import com.provisiones.dal.qm.QMGastos;
import com.provisiones.dal.qm.listas.QMListaGastos;
import com.provisiones.dal.qm.listas.QMListaGastosProvisiones;
import com.provisiones.dal.qm.listas.errores.QMListaErroresGastos;
import com.provisiones.dal.qm.movimientos.QMMovimientosGastos;

import com.provisiones.misc.Parser;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Gasto;
import com.provisiones.types.movimientos.MovimientoGasto;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.GastoTabla;

public class CLGastos 
{
	private static Logger logger = LoggerFactory.getLogger(CLGastos.class.getName());
	
	//ID
	public static String buscarCodigoGasto(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{
		return QMGastos.getGastoID(ConnectionManager.getDBConnection(),sCodCOACES, sCodCOGRUG, sCodCOTPGA, sCodCOSBGA, sFEDEVE);
	}
	
	//Conversion
	public static MovimientoGasto convierteGastoenMovimiento(Gasto gasto, String sNUPROF)
	{
		logger.debug("Convirtiendo...");
		
		return new MovimientoGasto(
				gasto.getCOACES(),
				gasto.getCOGRUG(),
				gasto.getCOTPGA(),
				gasto.getCOSBGA(),
				gasto.getPTPAGO(),
				gasto.getFEDEVE(),
				gasto.getFFGTVP(),
				gasto.getFEPAGA(),
				gasto.getFELIPG(),
				gasto.getCOSIGA(),
				gasto.getFEEESI(),
				gasto.getFEECOI(),
				gasto.getFEEAUI(),
				gasto.getFEEPAI(),
				gasto.getIMNGAS(),
				gasto.getYCOS02(),
				gasto.getIMRGAS(),
				gasto.getYCOS04(),
				gasto.getIMDGAS(),
				gasto.getYCOS06(),
				gasto.getIMCOST(),
				gasto.getYCOS08(),
				gasto.getIMOGAS(),
				gasto.getYCOS10(),
				gasto.getIMDTGA(),
				ValoresDefecto.DEF_COUNMO,
				gasto.getIMIMGA(),
				gasto.getCOIMPT(),
				gasto.getCOTNEG(),
				ValoresDefecto.DEF_COENCX,
				ValoresDefecto.DEF_COOFCX,
				ValoresDefecto.DEF_NUCONE,
				sNUPROF,
				gasto.getFEAGTO(),
				gasto.getCOMONA(),
				gasto.getBIAUTO(),
				gasto.getFEAUFA(),
				ValoresDefecto.DEF_COTERR,
				ValoresDefecto.DEF_FMPAGN,
				gasto.getFEPGPR(),
				ValoresDefecto.DEF_FEAPLI,
				ValoresDefecto.DEF_COAPII,
				ValoresDefecto.DEF_COSPII_GA,
				ValoresDefecto.DEF_NUCLII);
		
	}
	
	public static Gasto convierteMovimientoenGasto(MovimientoGasto movimiento)
	{
		logger.debug("Convirtiendo...");
		
		return new Gasto(
				movimiento.getCOACES(),
				movimiento.getCOGRUG(),
				movimiento.getCOTPGA(),
				movimiento.getCOSBGA(),
				movimiento.getPTPAGO(),
				movimiento.getFEDEVE(),
				movimiento.getFFGTVP(),
				movimiento.getFEPAGA(),
				movimiento.getFELIPG(),
				movimiento.getCOSIGA(),
				movimiento.getFEEESI(),
				movimiento.getFEECOI(),
				movimiento.getFEEAUI(),
				movimiento.getFEEPAI(),
				movimiento.getIMNGAS(),
				movimiento.getYCOS02(),
				movimiento.getIMRGAS(),
				movimiento.getYCOS04(),
				movimiento.getIMDGAS(),
				movimiento.getYCOS06(),
				movimiento.getIMCOST(),
				movimiento.getYCOS08(),
				movimiento.getIMOGAS(),
				movimiento.getYCOS10(),
				movimiento.getIMDTGA(),
				movimiento.getIMIMGA(),
				movimiento.getCOIMPT(),
				movimiento.getCOTNEG(),
				movimiento.getFEAGTO(),
				movimiento.getCOMONA(),
				movimiento.getBIAUTO(),
				movimiento.getFEAUFA(),
				movimiento.getFEPGPR());
		
	}
	
	//Interfaz básico
	public static boolean anulaGasto(String sCodGasto)
	{
		return QMGastos.setEstado(ConnectionManager.getDBConnection(),sCodGasto, ValoresDefecto.DEF_GASTO_ANULADO);
	}
	
	public static ArrayList<ActivoTabla> buscarActivosConGastos(ActivoTabla filtro)
	{
		return QMGastos.buscaActivosConGastos(ConnectionManager.getDBConnection(),filtro);
	}
	
	public static ArrayList<ActivoTabla> buscarActivosConGastosValidados(ActivoTabla filtro)
	{
		return QMListaGastos.buscaActivosConGastosValidados(ConnectionManager.getDBConnection(),filtro);
	}
	
	public static String buscarDescripcionGasto(String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA)
	{
		return QMCodigosControl.getDesCOSBGA(ConnectionManager.getDBConnection(),sCodCOGRUG, sCodCOTPGA, sCodCOSBGA);
	}
	
	public static ArrayList<GastoTabla> buscarGastosActivo(String sCodCOACES)
	{
		return QMGastos.buscaGastosActivo(ConnectionManager.getDBConnection(),sCodCOACES);
	}
	
	public static ArrayList<GastoTabla> buscarGastosValidadosActivo(String sCodCOACES)
	{
		return QMListaGastos.buscaGastosValidadosActivo(ConnectionManager.getDBConnection(),sCodCOACES);
	}
	
	public static Gasto buscarGasto(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{
		return QMGastos.getGasto(ConnectionManager.getDBConnection(),buscarCodigoGasto(sCodCOACES, sCodCOGRUG, sCodCOTPGA, sCodCOSBGA, sFEDEVE));
	}
	
	public static MovimientoGasto buscarMovimientoGasto (String sCodMovimiento)
	{
		return QMMovimientosGastos.getMovimientoGasto(ConnectionManager.getDBConnection(),sCodMovimiento);
	}
	
	public static long buscarNumeroMovimientosGastosPendientes()
	{
		return (QMListaGastos.buscaCantidadValidado(ConnectionManager.getDBConnection(),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE));
	}
	
	public static String buscarProvisionGasto(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{
		
		return QMListaGastosProvisiones.getProvisionDeGasto(ConnectionManager.getDBConnection(),buscarCodigoGasto(sCodCOACES, sCodCOGRUG, sCodCOTPGA, sCodCOSBGA, sFEDEVE));
	}
	
	public static String estadoGasto(String sCodGasto)
	{
		return QMGastos.getEstado(ConnectionManager.getDBConnection(),sCodGasto);
	}
	
	public static boolean existeMovimientoGasto(String sCodMovimiento)
	{
		return QMMovimientosGastos.existeMovimientoGasto(ConnectionManager.getDBConnection(),sCodMovimiento);
	}
	
	public static boolean existeGasto(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{
		return QMGastos.existeGasto(ConnectionManager.getDBConnection(),sCodCOACES, sCodCOGRUG, sCodCOTPGA, sCodCOSBGA, sFEDEVE);
	}
	
	//Interfaz avanzado
	public static int actualizarGastoLeido(String linea)
	{
		int iCodigo = 0;

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			MovimientoGasto gasto = Parser.leerGasto(linea);

			logger.debug(gasto.logMovimientoGasto());
			
			
			String sCodMovimiento = QMMovimientosGastos.getMovimientoGastoID(conexion,gasto);
			
			logger.debug("sCodMovimiento|"+sCodMovimiento+"|");
			
			if (!(sCodMovimiento.equals("")))
			{

				
				String sEstadoMovimiento = QMListaGastos.getValidado(conexion,sCodMovimiento);
				
				if (sEstadoMovimiento.equals("P"))
				{
					iCodigo = -11;
				}
				else if (sEstadoMovimiento.equals("X") || sEstadoMovimiento.equals("V") || sEstadoMovimiento.equals("R"))
				{
					iCodigo = -12;
				}
				else if (sEstadoMovimiento.equals("E"))
				{
					String sValidado = "";
					
					logger.debug("gasto.getCOTERR()|"+gasto.getCOTERR()+"|");
					logger.debug("ValoresDefecto.DEF_COTERR|"+ValoresDefecto.DEF_COTERR+"|");
					
					if (gasto.getCOTERR().equals(ValoresDefecto.DEF_COTERR))
					{
						sValidado = "V";
					}
					else
					{
						sValidado = "X";
					}
					
					logger.debug("sValidado|"+sValidado+"|");
					
					String sCodGasto = buscarCodigoGasto(gasto.getCOACES(), gasto.getCOGRUG(), gasto.getCOTPGA(), gasto.getCOSBGA(), gasto.getFEDEVE());
					
					if (QMListaGastos.existeRelacionGasto(conexion,sCodGasto, sCodMovimiento))
					{
						if(QMListaGastos.setValidado(conexion,sCodMovimiento, sValidado))
						{
							if (sValidado.equals("X"))
							{
								//recibido error
								if (QMListaErroresGastos.addErrorGasto(conexion,sCodMovimiento, gasto.getCOTERR()))
								{
									iCodigo = 1;
								}
								else
								{
									QMListaGastos.setValidado(conexion,sCodMovimiento, "E");
									iCodigo = -4;
								}
							}
							else
							{
								//recibido OK
								if (!QMListaGastosProvisiones.getRevisado(conexion,sCodMovimiento).equals(sValidado))
								{
									if (QMListaGastosProvisiones.setRevisado(conexion,sCodMovimiento, sValidado))
									{
										logger.info("Gasto Revisado.");
									}
									else
									{
										QMListaGastos.setValidado(conexion,sCodMovimiento, "E");
										iCodigo = -5;
									}
								}

								logger.info("Movimiento validado.");
							}
						}
						else
						{
							iCodigo = -3;
						}
					}
					else
					{
						iCodigo = -2;
					}
					
					//bSalida = QMMovimientosGastos.modMovimientoGasto(gasto, sCodMovimiento);
					//nos ahorramos modificar el movimiento y posteriormente en el bean de gestion de errores
					//recuperaremos el codigo de error de la tabla pertinente.
				}
				else
				{
					iCodigo = -10;
				}
					
			}
			else 
			{
				logger.error("El siguiente registro no se encuentra en el sistema:");
				logger.error("|"+linea+"|");
				iCodigo = -1;
			}
		}
		
		logger.error("iCodigo:|"+iCodigo+"|");
		
		return iCodigo;
	}
	
	public static ArrayList<ActivoTabla> buscarActivosConMovimientos(ActivoTabla filtro)
	{
		ArrayList<ActivoTabla> resultcuotas = CLCuotas.buscarActivosConCuotas(filtro);  
		ArrayList<ActivoTabla> resultimpuestos = CLImpuestos.buscarActivosConImpuestosResueltos(filtro);
		
		ArrayList<ActivoTabla> resultcuotasimpuestos = new ArrayList<ActivoTabla>(resultcuotas);
		resultcuotasimpuestos.addAll(resultimpuestos);
		
		logger.debug("TAM RESULT-C:|"+resultcuotas.size()+"|");
		logger.debug("TAM RESULT-I:|"+resultimpuestos.size()+"|");
		
		logger.debug("TAM RESULT-CI:|"+resultcuotasimpuestos.size()+"|");
		
		//Eliminamos duplicados
		HashSet<ActivoTabla> hslimpia = new HashSet<ActivoTabla>(resultcuotasimpuestos);
		
		logger.debug("RESULT-CI:|"+hslimpia.toString()+"|");
		
		resultcuotasimpuestos.clear();
		resultcuotasimpuestos.addAll(hslimpia);
		
		return resultcuotasimpuestos;
	}
	
	public static String decideAccion(MovimientoGasto movimiento, String sEstado)
	{
		String sAccion = "";
		if (QMGastos.gastoAnulado(ConnectionManager.getDBConnection(),movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE()))
		{
			sAccion = "#"; //Error
		}
		else if (existeGasto(movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE()))
		{
			
			if (!movimiento.getFEAGTO().equals("0") && (sEstado.equals("1") || sEstado.equals("2")))
			{
				sAccion = "N"; //Anular
			}
			else if (movimiento.getIMNGAS().startsWith("-") && (sEstado.equals("3") || sEstado.equals("4")))
			{
				sAccion = "A"; //Abono
			}
			else if (!movimiento.getIMNGAS().startsWith("-") && (sEstado.equals("1") || sEstado.equals("2") || sEstado.equals("3")))
			{
				sAccion = "M"; //Modificacion
			}
			/*else if (!movimiento.getIMNGAS().equals("-") && sEstado.equals("3"))
			{
				sAccion = "P"; //Pago
			}*/
			else
			{
				sAccion = "#"; //Error
			}
			
		}
		else
		{
			logger.debug("IMNGAS:|"+movimiento.getIMNGAS()+"|");
			logger.debug("COSBGA:|"+movimiento.getCOSBGA()+"|");

			if (movimiento.getIMNGAS().startsWith("-") && (Integer.parseInt(movimiento.getCOSBGA()) > 49))
			{
				sAccion = "D"; //Devolucion
			}
			else if (!movimiento.getIMNGAS().startsWith("-") && (Integer.parseInt(movimiento.getCOSBGA()) < 10))
			{
				sAccion = "G"; //Gasto
			}
			else
			{
				sAccion = "#"; //Error
			}
			
		}
		
		logger.debug("sAccion:|"+sAccion+"|");
		return sAccion;
	}
	
	public static String decideEstado(String sCOSIGA)
	{
		String sEstado  = "";
		if (sCOSIGA.equals("5") || sCOSIGA.equals("7"))
		{
			sEstado = ValoresDefecto.DEF_GASTO_PAGADO;
		}
		else if (sCOSIGA.equals("6"))
		{
			sEstado = ValoresDefecto.DEF_GASTO_AUTORIZADO;
		}
		else
		{
			sEstado = sCOSIGA;
		}
		
		logger.debug("sEstado:|"+sEstado+"|");
		return sEstado;
	}
	
	public static long inyectaMovimiento(MovimientoGasto movimiento)
	{

		long iCodigo = 0;

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			String sCodGasto = buscarCodigoGasto(movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE());
			
			logger.debug("Comprobando estado...");
			
			String sEstado = QMGastos.getEstado(conexion,sCodGasto);

			
			logger.debug("Estado:|"+sEstado+"|");
			logger.debug("Nuevo Estado:|"+movimiento.getCOSIGA()+"|");
			
			String sCodMovimiento = "";
			
			if (!sEstado.equals(ValoresDefecto.DEF_GASTO_CONOCIDO) && !sEstado.equals(ValoresDefecto.DEF_GASTO_ESTIMADO))//comprobar estado gasto y estado de movimiento
			{
				//Error gasto ya procesado.
				iCodigo = -1;
			}
			else
			{
				
				sCodMovimiento = Integer.toString(QMMovimientosGastos.addMovimientoGasto(conexion,movimiento));

				if (sCodMovimiento.equals("0"))
				{
					//error al crear un movimiento
					iCodigo = -900;
				}
				else
				{	
					
					if (QMListaGastos.addRelacionGasto(conexion,sCodGasto,sCodMovimiento))
					{
						String sNUPROF = QMListaGastosProvisiones.getProvisionDeGasto(conexion,sCodGasto);
						
						if (QMListaGastosProvisiones.delRelacionGastoProvision(conexion,sCodGasto))
						{
							if (QMListaGastosProvisiones.addRelacionGastoProvision(conexion,sCodGasto, movimiento.getNUPROF()))
							{
								//Abonado
								if (QMGastos.setEstado(conexion,sCodGasto, "6"))
								{
									//OK 
									iCodigo = 0; 
								}
								else
								{
									//error estado no establecido - Rollback
									QMMovimientosGastos.delMovimientoGasto(conexion,sCodMovimiento);
									QMListaGastos.delRelacionGasto(conexion,sCodMovimiento);
									QMListaGastosProvisiones.delRelacionGastoProvision(conexion,sCodGasto);
									QMListaGastosProvisiones.addRelacionGastoProvision(conexion,sCodGasto, sNUPROF);
									iCodigo = -903;
								}
							}
							else
							{
								//error estado no establecido - Rollback
								QMMovimientosGastos.delMovimientoGasto(conexion,sCodMovimiento);
								QMListaGastos.delRelacionGasto(conexion,sCodMovimiento);
								QMListaGastosProvisiones.addRelacionGastoProvision(conexion,sCodGasto, sNUPROF);
								iCodigo = -905;
							}
						}
						else
						{
							//error estado no establecido - Rollback
							QMMovimientosGastos.delMovimientoGasto(conexion,sCodMovimiento);
							QMListaGastos.delRelacionGasto(conexion,sCodMovimiento);
							iCodigo = -905;
						}


					}
					else
					{
						//error relacion gasto no creada - Rollback
						QMMovimientosGastos.delMovimientoGasto(conexion,sCodMovimiento);
						iCodigo = -902;
					}
				}
			}
			logger.debug("iCodigo:|"+iCodigo+"|");
			if (iCodigo == 0)
			{
				iCodigo = Long.parseLong(sCodMovimiento);
			}
		}
		
		return iCodigo;
	}	

	public static int registraMovimiento(MovimientoGasto movimiento, boolean bValida)
	{
		int iCodigo = 0;

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			String sCodGasto = buscarCodigoGasto(movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE());
			
			logger.debug("Comprobando estado...");
			
			String sEstado = estadoGasto(sCodGasto);

			String sAccion = decideAccion(movimiento,sEstado);
			
			MovimientoGasto movimiento_revisado = CLGastos.revisaSignos(movimiento,sAccion);
			
					
			logger.debug("Estado:|"+sEstado+"|");
			logger.debug("Nuevo Estado:|"+movimiento.getCOSIGA()+"|");
			logger.debug("Accion:|"+sAccion+"|");
			
			if (bValida)
			{
				iCodigo = validaMovimiento(movimiento_revisado,sEstado,sAccion);
			}

			if (iCodigo == 0)
			{
				if (sAccion.equals("#"))
				{	
					//Error, Accion no permitida  
					iCodigo = -804;
				}
				else
				{
					String sCodMovimiento = Integer.toString(QMMovimientosGastos.addMovimientoGasto(conexion,movimiento_revisado));
					
					String sRevisadoAnterior = QMListaGastosProvisiones.getRevisado(conexion,sCodGasto);
					
					logger.debug("Movimiento:|"+sCodMovimiento+"|");
					
					if (sCodMovimiento.equals("0"))
					{
						//error al crear un movimiento
						iCodigo = -900;
					}
					else
					{	
				
						ValoresDefecto.TIPOSACCIONESGASTO ACCION = ValoresDefecto.TIPOSACCIONESGASTO.valueOf(sAccion);
						
						logger.debug("sAccion:|"+sAccion+"|");
						
						switch (ACCION)
						{
							
							case D:case G:
								Gasto gastonuevo = convierteMovimientoenGasto(movimiento_revisado);

								logger.debug("Dando de alta la cuota...");
								logger.debug(gastonuevo.logGasto());
							
								sCodGasto = Integer.toString(QMGastos.addGasto(conexion,gastonuevo,movimiento_revisado.getCOSIGA())); 

								if (!sCodGasto.equals("0"))
								{
									//OK - gasto creado
									logger.debug("Hecho!");
									
									if (QMListaGastos.addRelacionGasto(conexion,sCodGasto,sCodMovimiento))
									{
										if (QMListaGastosProvisiones.addRelacionGastoProvision(conexion,sCodGasto,movimiento_revisado.getNUPROF()))
										{
											//OK 
											iCodigo = 0;
										}
										else
										{
											//error relacion gasto no creada - Rollback
											QMListaGastos.delRelacionGasto(conexion,sCodMovimiento);
											QMGastos.delGasto(conexion,sCodGasto);
											QMMovimientosGastos.delMovimientoGasto(conexion,sCodMovimiento);
											iCodigo = -906;
										}
									}
									else
									{
										//error relacion gasto no creada - Rollback
										QMGastos.delGasto(conexion,sCodGasto);
										QMMovimientosGastos.delMovimientoGasto(conexion,sCodMovimiento);
										iCodigo = -902;
									}
								}
								else
								{
									//error gasto no creado - Rollback
									QMMovimientosGastos.delMovimientoGasto(conexion,sCodMovimiento);
									iCodigo = -901;
								}
								break;
							case N:case A:
								
								if (QMListaGastos.addRelacionGasto(conexion,sCodGasto,sCodMovimiento))
								{
									String sNuevoEstado = "";
									if (sAccion.equals("N"))
									{
										sNuevoEstado = "5"; //Anulado
									}
									else
									{
										sNuevoEstado = "6"; //Abonado
									}

									if (QMGastos.setEstado(conexion,sCodGasto, sNuevoEstado))
									{
										if (QMListaGastosProvisiones.setRevisado(conexion,sCodGasto, ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE))
										{
											if (QMGastos.setFechaAnulado(conexion,sCodGasto, movimiento.getFEAGTO()))
											{
												//OK 
												iCodigo = 0;
											}
											else
											{
												//error fecha de anulacion no registrada - Rollback
												QMMovimientosGastos.delMovimientoGasto(conexion,sCodMovimiento);
												QMListaGastos.delRelacionGasto(conexion,sCodMovimiento);
												QMGastos.setEstado(conexion,sCodGasto, sEstado);
												QMListaGastosProvisiones.setRevisado(conexion,sCodGasto, sRevisadoAnterior);
												iCodigo = -907;
											}
											
										}
										else
										{
											//error revision no registrada - Rollback
											QMMovimientosGastos.delMovimientoGasto(conexion,sCodMovimiento);
											QMListaGastos.delRelacionGasto(conexion,sCodMovimiento);
											QMGastos.setEstado(conexion,sCodGasto, sEstado);
											iCodigo = -904;
										}
									}
									else
									{
										//error estado no establecido - Rollback
										QMMovimientosGastos.delMovimientoGasto(conexion,sCodMovimiento);
										QMListaGastos.delRelacionGasto(conexion,sCodMovimiento);
										iCodigo = -903;
									}
								}
								else
								{
									//error relacion gasto no creada - Rollback
									QMMovimientosGastos.delMovimientoGasto(conexion,sCodMovimiento);
									iCodigo = -902;
								}
								break;
							case M:

								if (QMListaGastos.addRelacionGasto(conexion,sCodGasto,sCodMovimiento))
								{
									if (QMGastos.setEstado(conexion,sCodGasto, movimiento_revisado.getCOSIGA()))
									{
										
										if (QMListaGastosProvisiones.setRevisado(conexion,sCodGasto, ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE))
										{
											if(QMGastos.modGasto(conexion,convierteMovimientoenGasto(movimiento_revisado)))
											{
												//OK
												iCodigo = 0; 
											}
											else
											{
												//Error gasto no modificado
												QMMovimientosGastos.delMovimientoGasto(conexion,sCodMovimiento);
												QMListaGastos.delRelacionGasto(conexion,sCodMovimiento);
												QMGastos.setEstado(conexion,sCodGasto, sEstado);
												QMListaGastosProvisiones.setRevisado(conexion,sCodGasto, sRevisadoAnterior);
												iCodigo = -905;									
											}
										}
										else
										{
											//error sin revision - Rollback
											QMMovimientosGastos.delMovimientoGasto(conexion,sCodMovimiento);
											QMListaGastos.delRelacionGasto(conexion,sCodMovimiento);
											QMGastos.setEstado(conexion,sCodGasto, sEstado);
											iCodigo = -904;
										}
									}
									else
									{
										//error estado no establecido - Rollback
										QMMovimientosGastos.delMovimientoGasto(conexion,sCodMovimiento);
										QMListaGastos.delRelacionGasto(conexion,sCodMovimiento);
										iCodigo = -903;
									}
								}
								else
								{
									//error relacion gasto no creada - Rollback
									QMMovimientosGastos.delMovimientoGasto(conexion,sCodMovimiento);
									iCodigo = -902;
								}
								break;
							default:
								break;
						}
					}
				}
			}
		}		

		logger.debug("iCodigo:|"+iCodigo+"|");
		
		return iCodigo;
	}
	
	public static int registraPagoConexion(MovimientoGasto movimiento)
	{
		int iCodigo = 0;

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			String sCodGasto = buscarCodigoGasto(movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE());
			
			logger.debug("Comprobando estado...");
			
			String sEstado = QMGastos.getEstado(conexion,sCodGasto);

			
			logger.debug("Estado:|"+sEstado+"|");
			logger.debug("Nuevo Estado:|"+movimiento.getCOSIGA()+"|");
			
			if (!sEstado.equals(ValoresDefecto.DEF_GASTO_CONOCIDO) && !sEstado.equals(ValoresDefecto.DEF_GASTO_ESTIMADO))//comprobar estado gasto y estado de movimiento
			{
				//Error gasto ya procesado.
				iCodigo = -1;
			}
			else
			{
				//movimiento.setIMNGAS("-"+movimiento.getIMNGAS());
				String sCodMovimiento = Integer.toString(QMMovimientosGastos.addMovimientoGasto(conexion,movimiento));

				if (sCodMovimiento.equals("0"))
				{
					//error al crear un movimiento
					iCodigo = -900;
				}
				else
				{	
					
					if (QMListaGastos.addRelacionGasto(conexion,sCodGasto,sCodMovimiento))
					{
						String sNUPROF = QMListaGastosProvisiones.getProvisionDeGasto(conexion, sCodGasto);
						
						if (QMListaGastosProvisiones.delRelacionGastoProvision(conexion,sCodGasto))
						{
							if (QMListaGastosProvisiones.addRelacionGastoProvision(conexion,sCodGasto, movimiento.getNUPROF()))
							{
								//Abonado
								if (QMGastos.setEstado(conexion,sCodGasto, "6"))
								{
									//OK 
									iCodigo = 0; 
								}
								else
								{
									//error estado no establecido - Rollback
									QMMovimientosGastos.delMovimientoGasto(conexion,sCodMovimiento);
									QMListaGastos.delRelacionGasto(conexion,sCodMovimiento);
									QMListaGastosProvisiones.delRelacionGastoProvision(conexion,sCodGasto);
									QMListaGastosProvisiones.addRelacionGastoProvision(conexion,sCodGasto, sNUPROF);
									iCodigo = -903;
								}
							}
							else
							{
								//error estado no establecido - Rollback
								QMMovimientosGastos.delMovimientoGasto(conexion,sCodMovimiento);
								QMListaGastos.delRelacionGasto(conexion,sCodMovimiento);
								QMListaGastosProvisiones.addRelacionGastoProvision(conexion,sCodGasto, sNUPROF);
								iCodigo = -905;
							}
						}
						else
						{
							//error estado no establecido - Rollback
							QMMovimientosGastos.delMovimientoGasto(conexion,sCodMovimiento);
							QMListaGastos.delRelacionGasto(conexion,sCodMovimiento);
							iCodigo = -905;
						}


					}
					else
					{
						//error relacion gasto no creada - Rollback
						QMMovimientosGastos.delMovimientoGasto(conexion,sCodMovimiento);
						iCodigo = -902;
					}
				}
			}
		}		

		logger.debug("iCodigo:|"+iCodigo+"|");
		
		return iCodigo;
	}

	public static MovimientoGasto revisaSignos(MovimientoGasto movimiento, String sAccion)
	{
		MovimientoGasto movimiento_revisado = new MovimientoGasto("0","0","0","0","","0","0","0","0","0","0","0","0","0","0","","0","","0","","0","","0","","0","0","0","0","0","0","0","0","0","0","0","","0","0","0","0","0","","","0");

		Gasto gasto = buscarGasto(movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE());
		
		logger.debug(movimiento.logMovimientoGasto());
	
		//logger.debug("Revisando Accion: |"+movimiento.getCOACCI()+"|");
		
		movimiento_revisado.setCOACES(movimiento.getCOACES());
		
		movimiento_revisado.setCOGRUG(movimiento.getCOGRUG());
		movimiento_revisado.setCOTPGA(movimiento.getCOTPGA());
		movimiento_revisado.setCOSBGA(movimiento.getCOSBGA());
		movimiento_revisado.setFEDEVE(movimiento.getFEDEVE());
		
		//Provision
		movimiento_revisado.setNUPROF(movimiento.getNUPROF());

		if (movimiento.getIMNGAS().startsWith("-"))
		{
			movimiento_revisado.setYCOS02("-");
			movimiento_revisado.setIMNGAS(movimiento.getIMNGAS().replaceFirst("-", ""));
		}
		else
		{
			movimiento_revisado.setIMNGAS(movimiento.getIMNGAS());
		}
		
		if (movimiento.getIMRGAS().startsWith("-"))
		{
			movimiento_revisado.setYCOS04("-");
			movimiento_revisado.setIMRGAS(movimiento.getIMRGAS().replaceFirst("-", ""));
		}
		else
		{
			movimiento_revisado.setIMRGAS(movimiento.getIMRGAS());
		}

		if (movimiento.getIMDGAS().startsWith("-"))
		{
			movimiento_revisado.setYCOS06("-");
			movimiento_revisado.setIMDGAS(movimiento.getIMDGAS().replaceFirst("-", ""));
		}
		else
		{
			movimiento_revisado.setIMDGAS(movimiento.getIMDGAS());
		}		


		if (movimiento.getIMCOST().startsWith("-"))
		{
			movimiento_revisado.setYCOS08("-");
			movimiento_revisado.setIMCOST(movimiento.getIMCOST().replaceFirst("-", ""));
		}
		else
		{
			movimiento_revisado.setIMCOST(movimiento.getIMCOST());
		}
		
		if (movimiento.getIMOGAS().startsWith("-"))
		{
			movimiento_revisado.setYCOS10("-");
			movimiento_revisado.setIMOGAS(movimiento.getIMOGAS().replaceFirst("-", ""));
		}
		else
		{
			movimiento_revisado.setIMOGAS(movimiento.getIMOGAS());
		}
		
		if (sAccion.equals("G") || sAccion.equals("D")) //Nuevo
		{
			movimiento_revisado.setPTPAGO(movimiento.getPTPAGO());

			movimiento_revisado.setFFGTVP(movimiento.getFFGTVP());
			movimiento_revisado.setFEPAGA(movimiento.getFEPAGA());
			movimiento_revisado.setFELIPG(movimiento.getFELIPG());
			
			movimiento_revisado.setCOSIGA(movimiento.getCOSIGA());
			movimiento_revisado.setFEEESI(movimiento.getFEEESI());
			movimiento_revisado.setFEECOI(movimiento.getFEECOI());
			movimiento_revisado.setFEEAUI(movimiento.getFEEAUI());
			movimiento_revisado.setFEEPAI(movimiento.getFEEPAI());
			movimiento_revisado.setIMDTGA(movimiento.getIMDTGA());
			
			movimiento_revisado.setIMIMGA(movimiento.getIMIMGA());
			movimiento_revisado.setCOIMPT(movimiento.getCOIMPT());
			movimiento_revisado.setFEAGTO(movimiento.getFEAGTO());
			movimiento_revisado.setFEPGPR(movimiento.getFEPGPR());
		}
		
		
		else if (sAccion.equals("N")) //Anular 
		{
			movimiento_revisado.setPTPAGO(gasto.getPTPAGO());
			
			movimiento_revisado.setFFGTVP(gasto.getFFGTVP());
			movimiento_revisado.setFEPAGA(gasto.getFEPAGA());
			movimiento_revisado.setFELIPG(gasto.getFELIPG());

			movimiento_revisado.setCOSIGA(gasto.getCOSIGA());
			movimiento_revisado.setFEEESI(gasto.getFEEESI());
			movimiento_revisado.setFEECOI(gasto.getFEECOI());
			movimiento_revisado.setFEEAUI(gasto.getFEEAUI());
			movimiento_revisado.setFEEPAI(gasto.getFEEPAI());
			movimiento_revisado.setIMDTGA(gasto.getIMDTGA());
			
			movimiento_revisado.setIMIMGA(gasto.getIMIMGA());
			movimiento_revisado.setCOIMPT(gasto.getCOIMPT());
			movimiento_revisado.setFEAGTO(movimiento.getFEAGTO());
			movimiento_revisado.setFEPGPR(gasto.getFEPGPR());
			

		}
		else if (sAccion.equals("A")) //Abono 
		{
			boolean bCambio = false;
			
			if (!movimiento_revisado.getIMNGAS().equals(gasto.getIMNGAS()) 
					|| !movimiento_revisado.getYCOS02().equals(gasto.getYCOS02()))
			{
				bCambio = true;
			}
			if (!movimiento_revisado.getIMRGAS().equals(gasto.getIMRGAS()) 
					|| !movimiento_revisado.getYCOS04().equals(gasto.getYCOS04()))
			{
				bCambio = true;	
			}

			if (!movimiento_revisado.getIMDGAS().equals(gasto.getIMDGAS()) 
					|| !movimiento_revisado.getYCOS06().equals(gasto.getYCOS06()))
			{
				bCambio = true;	
			}

			if (!movimiento_revisado.getIMCOST().equals(gasto.getIMCOST()) 
					|| !movimiento_revisado.getYCOS08().equals(gasto.getYCOS08()))
			{
				bCambio = true;	
			}
			if (!movimiento_revisado.getIMOGAS().equals(gasto.getIMOGAS()) 
					|| !movimiento_revisado.getYCOS10().equals(gasto.getYCOS10()))
			{
				bCambio = true;	
			}
			
			if (!movimiento.getIMDTGA().equals(gasto.getIMDTGA()))
			{
				bCambio = true;
				movimiento_revisado.setIMDTGA(movimiento.getIMDTGA());
			}
			else
			{
				movimiento_revisado.setIMDTGA(gasto.getIMDTGA());
			}
			
			if (!movimiento.getIMIMGA().equals(gasto.getIMIMGA()))
			{
				bCambio = true;
				movimiento_revisado.setIMIMGA(movimiento.getIMIMGA());
			}
			else
			{
				movimiento_revisado.setIMIMGA(gasto.getIMDTGA());
			}
			
			if (!movimiento.getCOIMPT().equals(gasto.getCOIMPT()))
			{
				bCambio = true;
				movimiento_revisado.setCOIMPT(movimiento.getCOIMPT());
			}
			else
			{
				movimiento_revisado.setCOIMPT(gasto.getCOIMPT());
			}
			movimiento_revisado.setPTPAGO(gasto.getPTPAGO());

			movimiento_revisado.setFFGTVP(gasto.getFFGTVP());
			movimiento_revisado.setFEPAGA(gasto.getFEPAGA());
			movimiento_revisado.setFELIPG(gasto.getFELIPG());
			
			movimiento_revisado.setCOSIGA(gasto.getCOSIGA());
			movimiento_revisado.setFEEESI(gasto.getFEEESI());
			movimiento_revisado.setFEECOI(gasto.getFEECOI());
			movimiento_revisado.setFEEAUI(gasto.getFEEAUI());
			movimiento_revisado.setFEEPAI(gasto.getFEEPAI());

			movimiento_revisado.setFEAGTO(gasto.getFEAGTO());
			movimiento_revisado.setFEPGPR(gasto.getFEPGPR());
			
			if (!bCambio)
			{
				movimiento_revisado.setCOSIGA("#");
			}
		}
		else if (sAccion.equals("M") || sAccion.equals("#")) //Modificacion
		{
			boolean bCambio = false;
			
			if (!movimiento_revisado.getIMNGAS().equals(gasto.getIMNGAS()) 
					|| !movimiento_revisado.getYCOS02().equals(gasto.getYCOS02()))
			{
				bCambio = true;
				
			}
			if (!movimiento_revisado.getIMRGAS().equals(gasto.getIMRGAS()) 
					|| !movimiento_revisado.getYCOS04().equals(gasto.getYCOS04()))
			{
				bCambio = true;	

			}

			if (!movimiento_revisado.getIMDGAS().equals(gasto.getIMDGAS()) 
					|| !movimiento_revisado.getYCOS06().equals(gasto.getYCOS06()))
			{
				bCambio = true;	
			}

			if (!movimiento_revisado.getIMCOST().equals(gasto.getIMCOST()) 
					|| !movimiento_revisado.getYCOS08().equals(gasto.getYCOS08()))
			{
				bCambio = true;	
			}
			if (!movimiento_revisado.getIMOGAS().equals(gasto.getIMOGAS()) 
					|| !movimiento_revisado.getYCOS10().equals(gasto.getYCOS10()))
			{
				bCambio = true;	
			}
			
			if (!movimiento.getIMDTGA().equals(gasto.getIMDTGA()))
			{
				bCambio = true;
				movimiento_revisado.setIMDTGA(movimiento.getIMDTGA());
			}
			else
			{
				movimiento_revisado.setIMDTGA(gasto.getIMDTGA());
			}
			
			if (!movimiento.getIMIMGA().equals(gasto.getIMIMGA()))
			{
				bCambio = true;
				movimiento_revisado.setIMIMGA(movimiento.getIMIMGA());
			}
			else
			{
				movimiento_revisado.setIMIMGA(gasto.getIMDTGA());
			}
			
			if (!movimiento.getCOIMPT().equals(gasto.getCOIMPT()))
			{
				bCambio = true;
				movimiento_revisado.setCOIMPT(movimiento.getCOIMPT());
			}
			else
			{
				movimiento_revisado.setCOIMPT(gasto.getCOIMPT());
			}

			if (!movimiento.getPTPAGO().equals(gasto.getPTPAGO()))
			{
				bCambio = true;
				movimiento_revisado.setPTPAGO(movimiento.getPTPAGO());
			}
			else
			{
				movimiento_revisado.setPTPAGO(gasto.getPTPAGO());
			}

			if (!movimiento.getFFGTVP().equals(gasto.getFFGTVP()))
			{
				bCambio = true;
				movimiento_revisado.setFFGTVP(movimiento.getFFGTVP());
			}
			else
			{
				movimiento_revisado.setFFGTVP(gasto.getFFGTVP());
			}
			
			if (!movimiento.getFEPAGA().equals(gasto.getFEPAGA()))
			{
				bCambio = true;
				movimiento_revisado.setFEPAGA(movimiento.getFEPAGA());
			}
			else
			{
				movimiento_revisado.setFEPAGA(gasto.getFEPAGA());
			}

			if (!movimiento.getFELIPG().equals(gasto.getFELIPG()))
			{
				bCambio = true;
				movimiento_revisado.setFELIPG(movimiento.getFELIPG());
			}
			else
			{
				movimiento_revisado.setFELIPG(gasto.getFELIPG());
			}			
			
			if (!movimiento.getCOSIGA().equals(gasto.getCOSIGA()))
			{
				bCambio = true;
				movimiento_revisado.setCOSIGA(movimiento.getCOSIGA());
			}
			else
			{
				movimiento_revisado.setCOSIGA(gasto.getCOSIGA());
			}

			if (!movimiento.getFEEESI().equals(gasto.getFEEESI()))
			{
				bCambio = true;
				movimiento_revisado.setFEEESI(movimiento.getFEEESI());
			}
			else
			{
				movimiento_revisado.setFEEESI(gasto.getFEEESI());
			}

			if (!movimiento.getFEECOI().equals(gasto.getFEECOI()))
			{
				bCambio = true;
				movimiento_revisado.setFEECOI(movimiento.getFEECOI());
			}
			else
			{
				movimiento_revisado.setFEECOI(gasto.getFEECOI());
			}
			
			if (!movimiento.getFEEAUI().equals(gasto.getFEEAUI()))
			{
				bCambio = true;
				movimiento_revisado.setFEEAUI(movimiento.getFEEAUI());
			}
			else
			{
				movimiento_revisado.setFEEAUI(gasto.getFEEAUI());
			}
			
			if (!movimiento.getFEEPAI().equals(gasto.getFEEPAI()))
			{
				bCambio = true;
				movimiento_revisado.setFEEPAI(movimiento.getFEEPAI());
			}
			else
			{
				movimiento_revisado.setFEEPAI(gasto.getFEEPAI());
			}

			if (!movimiento.getFEAGTO().equals(gasto.getFEAGTO()))
			{
				bCambio = true;
				movimiento_revisado.setFEAGTO(movimiento.getFEAGTO());
			}
			else
			{
				movimiento_revisado.setFEAGTO(gasto.getFEAGTO());
			}
		
			if (!movimiento.getFEPGPR().equals(gasto.getFEPGPR()))
			{
				bCambio = true;
				movimiento_revisado.setFEPGPR(movimiento.getFEPGPR());
			}
			else
			{
				movimiento_revisado.setFEPGPR(gasto.getFEPGPR());
			}
			
			
			//cambio de provision tras cierre
			if (!movimiento.getNUPROF().equals(buscarProvisionGasto(movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE())))
			{
				bCambio = true;
			}
			
			if (!bCambio)
			{
				movimiento_revisado.setCOSIGA("#");
			}
		}
		//Moneda
		movimiento_revisado.setCOUNMO(movimiento.getCOUNMO());


		
		//Autorizacion
		movimiento_revisado.setCOMONA(movimiento.getCOMONA());
		movimiento_revisado.setBIAUTO(movimiento.getBIAUTO());
		movimiento_revisado.setCOTNEG(movimiento.getCOTNEG());
		movimiento_revisado.setFEAUFA(movimiento.getFEAUFA());
		movimiento_revisado.setCOTERR(movimiento.getCOTERR());
				
		//Conexion
		movimiento_revisado.setCOENCX(movimiento.getCOENCX());
		movimiento_revisado.setCOOFCX(movimiento.getCOOFCX());
		movimiento_revisado.setNUCONE(movimiento.getNUCONE());
		movimiento_revisado.setFMPAGN(movimiento.getFMPAGN());
		
		//Aplicacion
		movimiento_revisado.setFEAPLI(movimiento.getFEAPLI());
		movimiento_revisado.setCOAPII(movimiento.getCOAPII());
		movimiento_revisado.setCOSPII(movimiento.getCOSPII());
		movimiento_revisado.setNUCLII(movimiento.getNUCLII());	
		
		logger.debug("Revisado! Nuevo movimiento:");
		
		logger.debug(movimiento_revisado.logMovimientoGasto());
		
		return movimiento_revisado;

	}	
	
	public static int validaMovimiento(MovimientoGasto movimiento_revisado, String sEstado, String sAccion)
	{
		int iCodigo = 0;

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			if (movimiento_revisado.getNUPROF().equals(""))
			{
				//Error 800 - Error, gasto sin provision  
				iCodigo = -800;
			}
			else if (!movimiento_revisado.getFEAGTO().equals("0") && !QMGastos.existeGasto(conexion,movimiento_revisado.getCOACES(), movimiento_revisado.getCOGRUG(), movimiento_revisado.getCOTPGA(), movimiento_revisado.getCOSBGA(), movimiento_revisado.getFEDEVE()))
			{
				//Error 002 - Llega fecha de anulación y no existe gasto en la tabla  
				iCodigo = -2;
			}
			else if ((sEstado.equals("3") || sEstado.equals("4")) && movimiento_revisado.getYCOS02().equals("-"))
			{
				//Error 003 - Llega un abono de un gasto que NO está pagado           
				iCodigo = -3;
			}		
			else if (Double.parseDouble(movimiento_revisado.getIMDTGA()) > Double.parseDouble(movimiento_revisado.getIMNGAS())) 
			{
				//Error 004 - Descuento mayor que importe nominal del gasto
				iCodigo = -4;
			}
			else if (CLProvisiones.estaCerrada(movimiento_revisado.getNUPROF()))
			{
				//Error 006 - La provisión ya está cerrada
				iCodigo = -6;
			}
			else if ((movimiento_revisado.getCOGRUG().equals("")) 
					|| (movimiento_revisado.getCOTPGA().equals("")) 
					|| (movimiento_revisado.getCOSBGA().equals("")))
			{
				//Error 007 - Error en grupo / tipo / subtipo de acción     
				iCodigo = -7;
			}
			else if (!CLActivos.existeActivo(movimiento_revisado.getCOACES()))
			{
				//Error 008 - No existe el activo en la base corporativa
				iCodigo = -8;
			}
			else if (sEstado.equals("5") && movimiento_revisado.getYCOS02().equals("-"))
			{
				//Error 012 - Llega un abono de un gasto que está anulado
				iCodigo = -12;
			}
			else if (sEstado.equals("6") && movimiento_revisado.getYCOS02().equals("-"))
			{
				//Error 013 - Llega un abono de un gasto que ya está abonado, o bien está en la misma provisión sin anular.
				iCodigo = -13;
			}
			else if (movimiento_revisado.getPTPAGO().equals("0") || movimiento_revisado.getPTPAGO().equals(""))
			{
				//Error 019 - Periodicidad del gasto es cero o espacios.
				iCodigo = -19;
			}		
			else if (!movimiento_revisado.getFEAGTO().equals("0") && sEstado.equals("4"))
			{
				//Error 023 - Llega anulación de un gasto que YA está pagado
				iCodigo = -23;
			}		
			else if (sAccion.equals("M") && sEstado.equals("4"))
			{
				//Error 024 - Llega modificación de un gasto que YA está pagado
				iCodigo = -024;
			}		
			else if (!movimiento_revisado.getFEPGPR().equals("") && CLProvisiones.estaCerrada(movimiento_revisado.getNUPROF()))
			{
				//Error 061 - La provisión ya está cerrada pero se ha actualizado la fecha de pago a proveedor.
				iCodigo = -61;
			}
			else if ((Integer.parseInt(movimiento_revisado.getCOSBGA()) > 49) && !movimiento_revisado.getYCOS02().equals("-"))
			{
				//Error 062 - Llega una devolución con importe positivo.
				iCodigo = -62;
			}
			else if (movimiento_revisado.getFEDEVE().equals("#"))
			{
				//Error 701 - Fecha de devengo incorrecta
				iCodigo = -701;
			}
			else if (movimiento_revisado.getFFGTVP().equals("#"))
			{
				//Error 702 - Fecha de fin de periodo incorrecta
				iCodigo = -702;
			}
			else if (movimiento_revisado.getFEPAGA().equals("#"))
			{
				//Error 703 - Fecha de pago incorrecta
				iCodigo = -703;
			}
			else if (movimiento_revisado.getFELIPG().equals("#"))
			{
				//Error 704 - Fecha de limite de pago incorrecta
				iCodigo = -704;
			}
			else if (movimiento_revisado.getFEEESI().equals("#"))
			{
				//Error 705 - Fecha de estado estimado incorrecta
				iCodigo = -705;
			}
			else if (movimiento_revisado.getFEECOI().equals("#"))
			{
				//Error 706 - Fecha de estado conocido incorrecta
				iCodigo = -706;
			}
			else if (movimiento_revisado.getFEAGTO().equals("#"))
			{
				//Error 707 - Fecha de anulacion del gasto incorrecta
				iCodigo = -707;
			}
			else if (movimiento_revisado.getFEPGPR().equals("#"))
			{
				//Error 708 - Fecha de pago al proveedor incorrecta
				iCodigo = -708;
			}
			else if (movimiento_revisado.getIMNGAS().equals("#"))
			{
				//Error 709 - Importe del gasto incorrecto
				iCodigo = -709;
			}
			else if (movimiento_revisado.getIMRGAS().equals("#"))
			{
				//Error 710 - Recargo en el importe del gasto incorrecto
				iCodigo = -710;
			}
			else if (movimiento_revisado.getIMDGAS().equals("#"))
			{
				//Error 711 - Importe de demora del gasto incorrecto
				iCodigo = -711;
			}
			else if (movimiento_revisado.getIMCOST().equals("#"))
			{
				//Error 712 - Importe de costas incorrecto
				iCodigo = -712;
			}
			else if (movimiento_revisado.getIMOGAS().equals("#"))
			{
				//Error 713 - Importe de otros incrementos incorrecto
				iCodigo = -713;
			}
			else if (movimiento_revisado.getIMDTGA().equals("#"))
			{
				//Error 714 - Importe de descuento incorrecto
				iCodigo = -714;
			}
			else if (movimiento_revisado.getIMIMGA().equals("#"))
			{
				//Error 715 - Importe de impuestos incorrecto
				iCodigo = -715;
			}
			
			else if (movimiento_revisado.getFEEESI().equals("") && movimiento_revisado.getFEECOI().equals(""))
			{
				//Error 807 - Fecha de estado sin informar
				iCodigo = -807;
			}
			else if (Double.parseDouble(movimiento_revisado.getIMDTGA()) < 0)
			{
				//Error 808 - Importe de descuento negativo
				iCodigo = -808;
			}
			else if (Double.parseDouble(movimiento_revisado.getIMIMGA()) < 0)
			{
				//Error 809 - Importe de impuestos negativo
				iCodigo = -809;
			}	
			
			else if (movimiento_revisado.getFEDEVE().equals("0"))
			{
				//Error no se ha informado la fecha de devengo.
				iCodigo = -801;
			}
			else if (movimiento_revisado.getCOSIGA().equals(""))
			{
				//Error no se ha elegido una situacion del gasto.
				iCodigo = -802;
			}
			else if (movimiento_revisado.getIMNGAS().equals(""))
			{
				//Error no se ha informado el campo importe
				iCodigo = -803;
			}
			else if (movimiento_revisado.getCOSIGA().equals("#"))
			{
				//error modificacion sin cambios
				iCodigo = -806;	

			}
			else if (sEstado.equals("") && !movimiento_revisado.getCOSIGA().equals("1") && !movimiento_revisado.getCOSIGA().equals("2"))
			{
				//error estado no disponible
				iCodigo = -805;
			}
		}
				
		return iCodigo;
	}
}
