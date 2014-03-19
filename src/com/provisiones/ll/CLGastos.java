package com.provisiones.ll;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMCodigosControl;
import com.provisiones.dal.qm.QMGastos;
import com.provisiones.dal.qm.QMProvisiones;
import com.provisiones.dal.qm.listas.QMListaGastos;
import com.provisiones.dal.qm.listas.QMListaGastosProvisiones;
import com.provisiones.dal.qm.listas.errores.QMListaErroresGastos;
import com.provisiones.dal.qm.movimientos.QMMovimientosGastos;

import com.provisiones.misc.Parser;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Gasto;
import com.provisiones.types.Provision;
import com.provisiones.types.movimientos.MovimientoGasto;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.GastoTabla;

public final class CLGastos 
{
	private static Logger logger = LoggerFactory.getLogger(CLGastos.class.getName());

	private CLGastos(){}
	
	//ID
	public static long buscarCodigoGasto(int iCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{
		return QMGastos.getGastoID(ConnectionManager.getDBConnection(),iCodCOACES, sCodCOGRUG, sCodCOTPGA, sCodCOSBGA, sFEDEVE);
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
				ValoresDefecto.DEF_FEPAGA,
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
				ValoresDefecto.DEF_FEPGPR,
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
				movimiento.getFEAUFA());
		
	}
	
	//Interfaz básico
	public static boolean anulaGasto(long liCodGasto)
	{
		return QMGastos.setEstado(ConnectionManager.getDBConnection(),liCodGasto, ValoresDefecto.DEF_GASTO_ANULADO);
	}
	
	public static boolean autorizaGasto(long liCodGasto, String sFEEAUI, String sFEAUFA)
	{
		return QMGastos.setAutorizado(ConnectionManager.getDBConnection(),liCodGasto, sFEEAUI, sFEAUFA);
	}
	
	public static boolean abonaGasto(long liCodGasto, String sFEAUFA)
	{
		return QMGastos.setAbonado(ConnectionManager.getDBConnection(),liCodGasto, sFEAUFA);
	}
	
	
	
	public static ArrayList<GastoTabla> buscarGastosActivoConFiltro(GastoTabla filtro)
	{
		return QMGastos.buscaGastosPorFiltro(ConnectionManager.getDBConnection(),filtro);
	}
	
	public static ArrayList<GastoTabla> buscarGastosActivoConFiltroEstado(GastoTabla filtro, String sEstado)
	{
		return QMGastos.buscaGastosPorFiltroEstado(ConnectionManager.getDBConnection(),filtro, sEstado);
	}
	
	public static ArrayList<GastoTabla> buscarGastosAutorizadosActivoConFiltro(GastoTabla filtro)
	{
		return QMGastos.buscaGastosPorFiltroEstado(ConnectionManager.getDBConnection(),filtro, ValoresDefecto.DEF_GASTO_AUTORIZADO);
	}
	
	public static ArrayList<GastoTabla> buscarGastosAbonablesActivoConFiltroEstado(GastoTabla filtro, String sEstado)
	{
		return QMGastos.buscaGastosAbonablesPorFiltroEstado(ConnectionManager.getDBConnection(),filtro, sEstado);
	}
	
	public static ArrayList<ActivoTabla> buscarActivosConGastos(ActivoTabla filtro)
	{
		return QMGastos.buscaActivosConGastos(ConnectionManager.getDBConnection(),filtro,"");
	}
	
	public static ArrayList<ActivoTabla> buscarActivosConGastosPendientes(ActivoTabla filtro)
	{
		return QMGastos.buscaActivosConGastos(ConnectionManager.getDBConnection(),filtro,ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
	}
	
	public static ArrayList<ActivoTabla> buscarActivosConGastosAutorizados(ActivoTabla filtro)
	{
		return QMListaGastos.buscaActivosConGastosAutorizados(ConnectionManager.getDBConnection(),filtro);
	}
	
	public static ArrayList<ActivoTabla> buscarActivosConGastosAbonables(ActivoTabla filtro)
	{
		return QMListaGastos.buscaActivosConGastosAbonablesPorFiltro(ConnectionManager.getDBConnection(),filtro);
	}
	
	public static String buscarDescripcionGasto(String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA)
	{
		return QMCodigosControl.getDesCOSBGA(ConnectionManager.getDBConnection(),sCodCOGRUG, sCodCOTPGA, sCodCOSBGA);
	}
	
	public static ArrayList<GastoTabla> buscarGastosNuevosActivo(int iCodCOACES)
	{
		return QMGastos.buscaGastosNuevosPorActivo(ConnectionManager.getDBConnection(),iCodCOACES);
	}
	
	public static ArrayList<GastoTabla> buscarGastosActivo(int iCodCOACES)
	{
		return QMGastos.buscaGastosPorActivo(ConnectionManager.getDBConnection(),iCodCOACES);
	}
	
	public static ArrayList<GastoTabla> buscarGastosValidadosActivo(int iCodCOACES)
	{
		return QMListaGastos.buscaGastosValidadosActivo(ConnectionManager.getDBConnection(),iCodCOACES);
	}
	
	public static ArrayList<GastoTabla> buscarGastosPagablesProvisionComunidad(String sNUPROF, long iCodComunidad)
	{
		return QMListaGastosProvisiones.buscaGastosPagablesProvisionPorComunidad(ConnectionManager.getDBConnection(),sNUPROF,iCodComunidad);
	}
	
	public static Gasto buscarGasto(int iCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{
		return QMGastos.getGasto(ConnectionManager.getDBConnection(),buscarCodigoGasto(iCodCOACES, sCodCOGRUG, sCodCOTPGA, sCodCOSBGA, sFEDEVE));
	}
	
	public static Gasto buscarGastoConCodigo(long liCodGasto)
	{
		return QMGastos.getGasto(ConnectionManager.getDBConnection(),liCodGasto);
	}
	
	public static Gasto buscarDetallesGasto(long liCodGasto)
	{
		return QMGastos.getDetallesGasto(ConnectionManager.getDBConnection(),liCodGasto);
	}
	
	public static String buscarNota (long liCodGasto)
	{
		return QMGastos.getNota(ConnectionManager.getDBConnection(),liCodGasto);
	}
	
	public static boolean guardarNota (long liCodGasto, String sNota)
	{
		return QMGastos.setNota(ConnectionManager.getDBConnection(),liCodGasto, sNota);
	}
	
	public static MovimientoGasto buscarMovimientoGasto (long liCodMovimiento)
	{
		return QMMovimientosGastos.getMovimientoGasto(ConnectionManager.getDBConnection(),liCodMovimiento);
	}
	
	public static long buscarNumeroMovimientosGastosPendientes()
	{
		return (QMListaGastos.buscaCantidadValidado(ConnectionManager.getDBConnection(),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE));
	}
	
	public static String buscarProvisionGasto(int iCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{
		return QMListaGastosProvisiones.getProvisionDeGasto(ConnectionManager.getDBConnection(),buscarCodigoGasto(iCodCOACES, sCodCOGRUG, sCodCOTPGA, sCodCOSBGA, sFEDEVE));
	}
	
	public static String buscarProvisionGastoID(long liCodGasto)
	{
		return QMListaGastosProvisiones.getProvisionDeGasto(ConnectionManager.getDBConnection(),liCodGasto);
	}
	
	public static ArrayList<GastoTabla> buscarGastosProvision(String sNUPROF)
	{
		return QMListaGastosProvisiones.buscaGastosProvision(ConnectionManager.getDBConnection(),sNUPROF);
	}
	
	public static ArrayList<GastoTabla> buscarGastosProvisionConFiltro(GastoTabla filtro)
	{
		return QMListaGastosProvisiones.buscaGastosProvisionPorFiltro(ConnectionManager.getDBConnection(),filtro);
	}
	
	public static ArrayList<GastoTabla> buscarGastosProvisionConFiltroEstado(GastoTabla filtro, String sEstado)
	{
		return QMListaGastosProvisiones.buscaGastosProvisionPorFiltroEstado(ConnectionManager.getDBConnection(),filtro, sEstado);
	}
	
	public static ArrayList<GastoTabla> buscarGastosAbonablesProvisionConFiltroEstado(GastoTabla filtro, String sEstado)
	{
		return QMListaGastosProvisiones.buscaGastosAbonablesProvisionPorFiltroEstado(ConnectionManager.getDBConnection(),filtro, sEstado);
	}
	
	public static ArrayList<GastoTabla> buscarGastosAutorizadosProvisionConFiltro(GastoTabla filtro)
	{
		return QMListaGastosProvisiones.buscaGastosProvisionPorFiltroEstado(ConnectionManager.getDBConnection(),filtro, ValoresDefecto.DEF_GASTO_AUTORIZADO);
	}
	
	public static String estadoGasto(long liCodGasto)
	{
		return QMGastos.getEstado(ConnectionManager.getDBConnection(),liCodGasto);
	}
	
	public static boolean existeMovimientoGasto(long liCodMovimiento)
	{
		return QMMovimientosGastos.existeMovimientoGasto(ConnectionManager.getDBConnection(),liCodMovimiento);
	}
	
	public static boolean existeGasto(int iCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{
		return QMGastos.existeGasto(ConnectionManager.getDBConnection(),iCodCOACES, sCodCOGRUG, sCodCOTPGA, sCodCOSBGA, sFEDEVE);
	}
	
	//Interfaz avanzado
	public static int eliminarMomivientosSuperfluos (long liCodGasto)
	{
		int iCodigo = 0;
		
		
		
		return iCodigo;
	}
	
	
	public static int actualizarGastoRecibido(String linea)
	{
		int iCodigo = -910;

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			MovimientoGasto gasto = Parser.leerGasto(linea);

			if (CLActivos.existeActivo(Integer.parseInt(gasto.getCOACES())))
			{
				String sEstadoProvision =  CLProvisiones.estadoProvision(gasto.getNUPROF());
				
				if (sEstadoProvision.equals(ValoresDefecto.DEF_PROVISION_ENVIADA) 
						|| sEstadoProvision.equals(ValoresDefecto.DEF_PROVISION_AUTORIZADA))
				{
					logger.debug(gasto.logMovimientoGasto());
					
					
					long liCodMovimiento = QMMovimientosGastos.getMovimientoGastoIDAutorizado(conexion,gasto);
					
					logger.debug("liCodMovimiento|"+liCodMovimiento+"|");
					
					if (liCodMovimiento != 0)
					{

						long liCodGasto = QMListaGastos.getCodGasto(conexion, liCodMovimiento);
						
						String sEstadoMovimiento = QMListaGastos.getValidado(conexion,liCodMovimiento);
						
						String sEstadoInicialGasto = QMGastos.getEstado(conexion, liCodGasto);
						
						if (sEstadoMovimiento.equals("P"))
						{
							iCodigo = -11;
						}
						else if (sEstadoMovimiento.equals("X") || sEstadoMovimiento.equals("V") || sEstadoMovimiento.equals("R"))
						{
							//TODO posible division e inclusion en pila de erroes para X
							iCodigo = 2;
						}
						else if (sEstadoMovimiento.equals("E"))
						{
							String sValidado = "";
							
							logger.debug("gasto.getCOTERR()|"+gasto.getCOTERR()+"|");
							logger.debug("ValoresDefecto.DEF_COTERR|"+ValoresDefecto.DEF_COTERR+"|");
							
							if (gasto.getCOTERR().equals(ValoresDefecto.DEF_COTERR))
							{
								if (sEstadoInicialGasto.equals(ValoresDefecto.DEF_GASTO_ABONADO))
								{
									sValidado = ValoresDefecto.DEF_MOVIMIENTO_RESUELTO;
								}
								else
								{
									sValidado = ValoresDefecto.DEF_MOVIMIENTO_VALIDADO;
								}
								
							}
							else
							{
								sValidado = ValoresDefecto.DEF_MOVIMIENTO_ERRONEO;
							}
							
							logger.debug("sValidado|"+sValidado+"|");
							
							try 
							{
								conexion.setAutoCommit(false);
								
								if (QMListaGastos.existeRelacionGasto(conexion,liCodGasto, liCodMovimiento))
								{
									if(QMListaGastos.setValidado(conexion,liCodMovimiento, sValidado))
									{
										if (sValidado.equals(ValoresDefecto.DEF_MOVIMIENTO_ERRONEO))
										{
											//recibido error
											if (QMListaErroresGastos.addErrorGasto(conexion,liCodMovimiento, gasto.getCOTERR()))
											{
												if (QMGastos.delGasto(conexion, liCodGasto))
												{
													iCodigo = 1;
												}
												else
												{
													iCodigo = -5;
												}
											}
											else
											{
												QMListaGastos.setValidado(conexion,liCodMovimiento, "E");
												iCodigo = -4;
											}
										}
										else if (sValidado.equals(ValoresDefecto.DEF_MOVIMIENTO_VALIDADO))
										{
											
											if(QMProvisiones.getEstado(conexion, gasto.getNUPROF()).equals(ValoresDefecto.DEF_PROVISION_ENVIADA))
											{
												if (!QMProvisiones.setFechaAutorizado(conexion, gasto.getNUPROF(), gasto.getFEAUFA()) 
													|| !QMProvisiones.setEstado(conexion, gasto.getNUPROF(),ValoresDefecto.DEF_PROVISION_AUTORIZADA))
												{
													iCodigo = -7;
												}
											}
											
											if (iCodigo == 0)
											{
												//recibido OK
												if (!QMListaGastosProvisiones.getRevisado(conexion,liCodGasto).equals(sValidado))
												{
													if (QMListaGastosProvisiones.setRevisado(conexion,liCodGasto, sValidado) && autorizaGasto(liCodGasto, gasto.getFEEAUI(), gasto.getFEAUFA()))
													{
														gasto.setValor_total();
																												
														if (QMProvisiones.setGastoAutorizado(conexion, gasto.getNUPROF(), gasto.getValor_total()))
														{
															logger.info("Gasto Revisado.");
															iCodigo = 0;
														}
														else
														{
															//error al actualizar la provisión
															iCodigo = -7;
														}

													}
													else
													{
														//QMListaGastos.setValidado(conexion,liCodMovimiento, "E");
														iCodigo = -5;
													}
												}
											}

											logger.info("Movimiento validado.");
										}
										else if (sValidado.equals(ValoresDefecto.DEF_MOVIMIENTO_RESUELTO))
										{
											if (QMListaGastosProvisiones.setRevisado(conexion,liCodGasto, sValidado) && abonaGasto(liCodGasto, gasto.getFEAUFA()))
											{
												logger.info("Gasto resuelto.");
												//TODO revisar los abonos
												
												iCodigo = 0;
											}
											else
											{
												//QMListaGastos.setValidado(conexion,liCodMovimiento, "E");
												iCodigo = -6;
											}
										}
										else
										{
											//QMListaGastos.setValidado(conexion,liCodMovimiento, "E");
											iCodigo = -5;
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
								
								//bSalida = QMMovimientosGastos.modMovimientoGasto(gasto, liCodMovimiento);
								//nos ahorramos modificar el movimiento y posteriormente en el bean de gestion de errores
								//recuperaremos el codigo de error de la tabla pertinente.
								
								if (iCodigo < 0)
								{
									conexion.rollback();
								}
								else
								{
									conexion.commit();
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
							
							//long liCodGasto = buscarCodigoGasto(Integer.parseInt(gasto.getCOACES()), gasto.getCOGRUG(), gasto.getCOTPGA(), gasto.getCOSBGA(), gasto.getFEDEVE());
							
	
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
				else
				{
					//error provision abierta
					iCodigo = -9;
				}

			}
			else 
			{
				//error activo desconocido
				iCodigo = -8;
			}
		}
		
		logger.error("iCodigo:|"+iCodigo+"|");
		
		return iCodigo;
	}
	
	public static int inyectarGastoVolcado(String linea)
	{
		int iCodigo = 0;

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			
			MovimientoGasto movimiento = Parser.leerGasto(linea);

			if (CLActivos.existeActivo(Integer.parseInt(movimiento.getCOACES())))
			{
				iCodigo = 0;
				//TODO revisar PA->GA
				movimiento.setFEAPLI(ValoresDefecto.DEF_FEAPLI);
				movimiento.setCOAPII(ValoresDefecto.DEF_COAPII);
				movimiento.setCOSPII(ValoresDefecto.DEF_COSPII_GA);
				movimiento.setNUCLII(ValoresDefecto.DEF_NUCLII);
				
				//movimiento.setCOSIGA(ValoresDefecto.DEF_GASTO_AUTORIZADO);
				
				
				logger.debug(movimiento.logMovimientoGasto());

				/*logger.debug("Validando movimiento...");
				
				String sEstado = decideEstado(movimiento.getCOSIGA());
				
				String sAccion = decideAccion(movimiento, sEstado);
				
				iCodigo = validaMovimiento(movimiento, sEstado,sAccion);*/
				
				if (CLProvisiones.existeProvision(movimiento.getNUPROF()))
				{
					if(QMProvisiones.getEstado(conexion, movimiento.getNUPROF()).equals(ValoresDefecto.DEF_PROVISION_ENVIADA))
					{
						Provision provision = CLProvisiones.buscarProvision(movimiento.getNUPROF());
						String sCOSPAT = CLActivos.sociedadPatrimonialAsociada(Integer.parseInt(movimiento.getCOACES()));
						
						if (QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TSOCTIT,QMCodigosControl.ISOCTIT,sCOSPAT).equals(""))
						{
							sCOSPAT = ValoresDefecto.CAMPO_NUME_SIN_INFORMAR;
						}
						
						provision.setsCOSPAT(sCOSPAT);
						
						if (provision.getsCOGRUG().equals(ValoresDefecto.CAMPO_NUME_SIN_INFORMAR))
						{
							provision.setsCOGRUG(movimiento.getCOGRUG());
						}

						String sTipo = CLActivos.compruebaTipoActivoSAREB(Integer.parseInt(movimiento.getCOACES()));
						
						provision.setsTAS(sTipo);
						provision.setsFechaAutorizado(movimiento.getFEAUFA());
						
						if (!QMProvisiones.modProvision(conexion,provision) 
							|| !QMProvisiones.setEstado(conexion, movimiento.getNUPROF(),ValoresDefecto.DEF_PROVISION_AUTORIZADA))
						{
							//Error: provision no modificada
							iCodigo = -908;
						}
					}

					
					if (iCodigo == 0)
					{
							
						long liCodGasto = QMGastos.getGastoID(conexion, Integer.parseInt(movimiento.getCOACES()), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE());
						
						logger.debug("liCodGasto:|"+liCodGasto+"|");

						try 
						{
							conexion.setAutoCommit(false);

							if (liCodGasto == 0)
							{
								//TODO pendiente cambiar DEF_GASTO_AUTORIZADO a Pagado si se paga segun se autoriza.
								liCodGasto = QMGastos.addGasto(conexion,convierteMovimientoenGasto(movimiento),ValoresDefecto.DEF_GASTO_AUTORIZADO); 
							}

							logger.debug("liCodGasto:|"+liCodGasto+"|");

							if (liCodGasto != 0)
							{
								long liCodMovimiento = QMMovimientosGastos.getMovimientoGastoID(conexion,movimiento);

								logger.debug("liCodMovimiento:|"+liCodMovimiento+"|");
								
								if (liCodMovimiento == 0)
								{
									liCodMovimiento = QMMovimientosGastos.addMovimientoGasto(conexion,movimiento);
									
									if (liCodMovimiento == 0)
									{
										//error al crear un movimiento
										iCodigo = -900;
									}
									else
									{	
										//OK - movimiento creado
										logger.debug("Hecho!");
										
										if (QMListaGastos.addRelacionGastoInyectado(conexion,liCodGasto,liCodMovimiento))
										{
											
											if (!QMListaGastosProvisiones.existeRelacionGastoProvision(conexion, liCodGasto, movimiento.getNUPROF()))
											{
												if (QMListaGastosProvisiones.addRelacionGastoProvisionInyectado(conexion,liCodGasto,movimiento.getNUPROF()))
												{
													//OK 
													if (QMProvisiones.setGastoNuevo(conexion, movimiento.getNUPROF(), QMGastos.getValorTotal(conexion,liCodGasto)))
													{
														if (QMProvisiones.setGastoAutorizado(conexion, movimiento.getNUPROF(), QMGastos.getValorTotal(conexion,liCodGasto)))
														{
															
															logger.debug("Gasto añadido.");
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
														//error al actualizar la provisión
														iCodigo = -909;
													}

												}
												else
												{
													//error relacion gasto y provision no creada - Rollback
													//QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
													//QMGastos.delGasto(conexion,liCodGasto);
													//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
													iCodigo = -906;
												}
											}
											else if (QMListaGastosProvisiones.setRevisado(conexion, liCodGasto, ValoresDefecto.DEF_MOVIMIENTO_VALIDADO))
											{
												//OK 
												if (QMProvisiones.setGastoAutorizado(conexion, movimiento.getNUPROF(), QMGastos.getValorTotal(conexion,liCodGasto)))
												{
													logger.debug("Gasto añadido.");
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
												//error relacion gasto y provision no creada - Rollback
												//QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
												//QMGastos.delGasto(conexion,liCodGasto);
												//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
												iCodigo = -906;
											}
											
										}
										else
										{
											//error relacion gasto no creada - Rollback
											//QMGastos.delGasto(conexion,liCodGasto);
											//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
											iCodigo = -902;
										}
									}
								}
								else 
								{
									logger.error("El siguiente registro ya se encontraba en el sistema:");
									logger.error("|"+linea+"|");
									iCodigo = -909;
								}
							}
							else
							{
								//error gasto no creado
								iCodigo = -901;
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
							try 
							{
								//reintentamos
								conexion.rollback();
								conexion.setAutoCommit(true);
								conexion.close();
								
								iCodigo = -910;
							} 
							catch (SQLException e1) 
							{
								iCodigo = -910;
								try 
								{
									conexion.close();
								}
								catch (SQLException e2) 
								{
									logger.error("[FATAL] Se predió la conexión de forma inesperada.");
								}
							}
						}

					}
				
				}
				else
				{
					//no existe la provision
					iCodigo = -9;
				}

			}
			else
			{
				//error activo desconocido
				iCodigo = -8;
			}


		}
		
		logger.debug("iCodigo:|"+iCodigo+"|");
		
		return iCodigo;
	}
	
	public static int validarGastoVolcado(String linea)
	{
		int iCodigo = 0;

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			
			MovimientoGasto movimiento = Parser.leerGasto(linea);

			if (CLActivos.existeActivo(Integer.parseInt(movimiento.getCOACES())))
			{
				iCodigo = 0;
			}
			else
			{
				//error activo desconocido
				iCodigo = -8;
			}


		}
		
		logger.debug("iCodigo:|"+iCodigo+"|");
		
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
		if (QMGastos.gastoAnulado(ConnectionManager.getDBConnection(),Integer.parseInt(movimiento.getCOACES()), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE()))
		{
			sAccion = "#"; //Error
		}
		else if (existeGasto(Integer.parseInt(movimiento.getCOACES()), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE()))
		{
			
			if (!movimiento.getFEAGTO().equals(ValoresDefecto.CAMPO_NUME_SIN_INFORMAR) && (sEstado.equals("1") || sEstado.equals("2")))
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
	
/*	public static long inyectaMovimiento(MovimientoGasto movimiento)
	{

		long iCodigo = 0;

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			long liCodGasto = buscarCodigoGasto(movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE());
			
			logger.debug("Comprobando estado...");
			
			String sEstado = QMGastos.getEstado(conexion,liCodGasto);

			
			logger.debug("Estado:|"+sEstado+"|");
			logger.debug("Nuevo Estado:|"+movimiento.getCOSIGA()+"|");
			
			long liCodMovimiento = "";
			
			if (!sEstado.equals(ValoresDefecto.DEF_GASTO_CONOCIDO) && !sEstado.equals(ValoresDefecto.DEF_GASTO_ESTIMADO))//comprobar estado gasto y estado de movimiento
			{
				//Error gasto ya procesado.
				iCodigo = -1;
			}
			else
			{
				
				liCodMovimiento = Integer.toString(QMMovimientosGastos.addMovimientoGasto(conexion,movimiento));

				if (liCodMovimiento.equals(ValoresDefecto.CAMPO_NUME_SIN_INFORMAR))
				{
					//error al crear un movimiento
					iCodigo = -900;
				}
				else
				{	
					
					if (QMListaGastos.addRelacionGasto(conexion,liCodGasto,liCodMovimiento))
					{
						String sNUPROF = QMListaGastosProvisiones.getProvisionDeGasto(conexion,liCodGasto);
						
						if (QMListaGastosProvisiones.delRelacionGastoProvision(conexion,liCodGasto))
						{
							if (QMListaGastosProvisiones.addRelacionGastoProvision(conexion,liCodGasto, movimiento.getNUPROF()))
							{
								//Abonado
								if (QMGastos.setEstado(conexion,liCodGasto, "6"))
								{
									//OK 
									iCodigo = 0; 
								}
								else
								{
									//error estado no establecido - Rollback
									QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
									QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
									QMListaGastosProvisiones.delRelacionGastoProvision(conexion,liCodGasto);
									QMListaGastosProvisiones.addRelacionGastoProvision(conexion,liCodGasto, sNUPROF);
									iCodigo = -903;
								}
							}
							else
							{
								//error estado no establecido - Rollback
								QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
								QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
								QMListaGastosProvisiones.addRelacionGastoProvision(conexion,liCodGasto, sNUPROF);
								iCodigo = -905;
							}
						}
						else
						{
							//error estado no establecido - Rollback
							QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
							QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
							iCodigo = -905;
						}


					}
					else
					{
						//error relacion gasto no creada - Rollback
						QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
						iCodigo = -902;
					}
				}
			}
			logger.debug("iCodigo:|"+iCodigo+"|");
			if (iCodigo == 0)
			{
				iCodigo = Long.parseLong(liCodMovimiento);
			}
		}
		
		return iCodigo;
	}*/	

	public static int registraMovimiento(MovimientoGasto movimiento, boolean bValida)
	{
		int iCodigo = -910;//Error de conexion

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			long liCodGasto = buscarCodigoGasto(Integer.parseInt(movimiento.getCOACES()), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE());
			
			logger.debug("Comprobando estado...");
			
			String sEstado = estadoGasto(liCodGasto);

			String sAccion = decideAccion(movimiento,sEstado);
			
			MovimientoGasto movimiento_revisado = CLGastos.revisaSignos(movimiento,sAccion);
			
					
			logger.debug("Estado:|"+sEstado+"|");
			logger.debug("Nuevo Estado:|"+movimiento.getCOSIGA()+"|");
			logger.debug("Accion:|"+sAccion+"|");
			
			if (bValida)
			{
				iCodigo = validaMovimiento(movimiento_revisado,sEstado,sAccion);
				
				logger.debug("validado.");
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
					try 
					{
						conexion.setAutoCommit(false);

						long liCodMovimiento = QMMovimientosGastos.addMovimientoGasto(conexion,movimiento_revisado);
						
						//String sRevisadoAnterior = QMListaGastosProvisiones.getRevisado(conexion,liCodGasto);
						
						logger.debug("Movimiento:|"+liCodMovimiento+"|");
						
						if (liCodMovimiento == 0)
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
								//Nuevo o devolución
								case D:case G:
									Gasto gastonuevo = convierteMovimientoenGasto(movimiento_revisado);
									gastonuevo.setValor_total();

									logger.debug("Dando de alta el gasto...");
									logger.debug(gastonuevo.logGasto());
								
									liCodGasto = QMGastos.addGasto(conexion,gastonuevo,movimiento_revisado.getCOSIGA()); 

									if (liCodGasto != 0)
									{
										//OK - gasto creado
										logger.debug("Hecho!");
										
										if (QMListaGastos.addRelacionGasto(conexion,liCodGasto,liCodMovimiento))
										{
											if (QMListaGastosProvisiones.addRelacionGastoProvision(conexion,liCodGasto,movimiento_revisado.getNUPROF()))
											{
												//OK 
												if (QMProvisiones.setGastoNuevo(conexion, movimiento_revisado.getNUPROF(), gastonuevo.getValor_total()))
												{
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
												//error relacion gasto no creada - Rollback
												//QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
												//QMGastos.delGasto(conexion,liCodGasto);
												//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
												
												iCodigo = -906;
											}
										}
										else
										{
											//error relacion gasto no creada - Rollback
											//QMGastos.delGasto(conexion,liCodGasto);
											//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
											
											iCodigo = -902;
										}
									}
									else
									{
										//error gasto no creado - Rollback
										//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
										
										iCodigo = -901;
									}
									break;
								//Anulado
								case N:
									long liValorAnulado = QMGastos.getValorTotal(conexion, liCodGasto);
									
									if (!QMListaGastos.existeMovimientoEnviado(conexion, liCodGasto))
									{
										conexion.rollback();
										
										if (QMListaGastos.delMovimientosGastoValidado(conexion, liCodGasto, ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE))
										{
											if (QMGastos.delGasto(conexion, liCodGasto))
											{
												if (QMProvisiones.setGastoAnuladoConexion(conexion, movimiento_revisado.getNUPROF(), liValorAnulado))
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
												//error al borrar el gasto - Rollback
												//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
												
												iCodigo = -908;
											}
										}
										else
										{
											//error al borrar el gasto - Rollback
											//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
											
											iCodigo = -908;
										}

									}
									else
									{
										if (QMListaGastos.addRelacionGasto(conexion,liCodGasto,liCodMovimiento))
										{
											if (QMGastos.setEstado(conexion,liCodGasto, ValoresDefecto.DEF_GASTO_ANULADO))
											{
												if (QMListaGastosProvisiones.setRevisado(conexion,liCodGasto, ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE))
												{
													if (QMGastos.setFechaAnulado(conexion,liCodGasto, movimiento.getFEAGTO()))
													{
														if (QMProvisiones.setGastoAnuladoConexion(conexion, movimiento_revisado.getNUPROF(), liValorAnulado))
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
														//error fecha de anulacion no registrada - Rollback
														//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
														//QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
														//QMGastos.setEstado(conexion,liCodGasto, sEstado);
														//QMListaGastosProvisiones.setRevisado(conexion,liCodGasto, sRevisadoAnterior);
														
														iCodigo = -907;
													}
													
												}
												else
												{
													//error revision no registrada - Rollback
													//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
													//QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
													//QMGastos.setEstado(conexion,liCodGasto, sEstado);
													
													iCodigo = -904;
												}
											}
											else
											{
												//error estado no establecido - Rollback
												//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
												//QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
												
												iCodigo = -903;
											}
										}
										else
										{
											//error relacion gasto no creada - Rollback
											//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
											
											iCodigo = -902;
										}	
									}
									break;
									
								//Abono	
								case A:
									if (QMListaGastos.addRelacionGasto(conexion,liCodGasto,liCodMovimiento))
									{
										String sEstadoGastoAbonado = QMGastos.getEstado(conexion,liCodGasto);
										
										if (QMGastos.setEstado(conexion,liCodGasto, ValoresDefecto.DEF_GASTO_ABONADO))
										{
											String sNUPROFAbono = QMListaGastosProvisiones.getProvisionDeGasto(conexion,liCodGasto);
											
											if (QMListaGastosProvisiones.setRevisado(conexion,liCodGasto, ValoresDefecto.DEF_MOVIMIENTO_RESUELTO))
											{
												if (QMListaGastosProvisiones.addRelacionGastoProvision(conexion,liCodGasto,movimiento_revisado.getNUPROF()))
												{
													//OK
													long liValorGasto = QMGastos.getValorTotal(conexion, liCodGasto);
													
													//TODO calcular valor abonado
													long liValorAbonado = liValorGasto;
													
													if (QMProvisiones.setGastoNuevo(conexion, movimiento_revisado.getNUPROF(), -liValorAbonado))
													{
														
														if (sEstadoGastoAbonado.equals(ValoresDefecto.DEF_GASTO_AUTORIZADO))
														{
															if (QMProvisiones.setGastoAbonado(conexion,sNUPROFAbono, liValorAbonado))
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
															//En gastos pagados no se descuenta de los autorizados
															iCodigo = 0;
														}
														
													}
													else
													{
														//error al actualizar la provisión
														iCodigo = -909;
													}

												}
												else
												{
													//error relacion gasto no creada - Rollback
													//QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
													//QMGastos.delGasto(conexion,liCodGasto);
													//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
													
													iCodigo = -906;
												}
												
											}
											else
											{
												//error revision no registrada - Rollback
												//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
												//QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
												//QMGastos.setEstado(conexion,liCodGasto, sEstado);
												
												iCodigo = -904;
											}
										}
										else
										{
											//error estado no establecido - Rollback
											//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
											//QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
											
											iCodigo = -903;
										}
									}
									else
									{
										//error relacion gasto no creada - Rollback
										//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
										
										iCodigo = -902;
									}
									break;
								case M:
									
									if (!QMListaGastos.existeMovimientoEnviado(conexion, liCodGasto))
									{
										if (!QMListaGastos.delMovimientosGastoValidado(conexion, liCodGasto, ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE))
										{
											iCodigo = -908;
										}
									}
									
									if (iCodigo == 0)
									{
										if (QMListaGastos.addRelacionGasto(conexion,liCodGasto,liCodMovimiento))
										{
											//if (QMGastos.setEstado(conexion,liCodGasto, movimiento_revisado.getCOSIGA()))
											//{
												
												if (QMListaGastosProvisiones.setRevisado(conexion,liCodGasto, ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE))
												{
													long liValorAntiguo = QMGastos.getValorTotal(conexion, liCodGasto);
													
													if(QMGastos.modGasto(conexion,convierteMovimientoenGasto(movimiento_revisado)))
													{
														//OK
														movimiento_revisado.setValor_total();
														
														if (QMProvisiones.setGastoModificado(conexion, movimiento_revisado.getNUPROF(), liValorAntiguo, movimiento_revisado.getValor_total()))
														{
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
														//Error gasto no modificado
														//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
														//QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
														//QMGastos.setEstado(conexion,liCodGasto, sEstado);
														//QMListaGastosProvisiones.setRevisado(conexion,liCodGasto, sRevisadoAnterior);
														
														iCodigo = -905;									
													}
												}
												else
												{
													//error sin revision - Rollback
													//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
													//QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
													//QMGastos.setEstado(conexion,liCodGasto, sEstado);
													
													iCodigo = -904;
												}
											//}
											//else
											//{
												//error estado no establecido - Rollback
												//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
												//QMListaGastos.delRelacionGasto(conexion,liCodMovimiento);
												
												//iCodigo = -903;
											//}
										}
										else
										{
											//error relacion gasto no creada - Rollback
											//QMMovimientosGastos.delMovimientoGasto(conexion,liCodMovimiento);
											
											iCodigo = -902;
										}
									}
									

									break;
								default:
									break;
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
		}		

		logger.debug("iCodigo:|"+iCodigo+"|");
		
		return iCodigo;
	}
	


	public static MovimientoGasto revisaSignos(MovimientoGasto movimiento, String sAccion)
	{
		MovimientoGasto movimiento_revisado = new MovimientoGasto("0","0","0","0","","0","0","0","0","0","0","0","0","0","0","","0","","0","","0","","0","","0","0","0","0","0","0","0","0","0","0","0","","0","0","0","0","0","","","0");

		Gasto gasto = buscarGasto(Integer.parseInt(movimiento.getCOACES()), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE());
		
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
			//movimiento_revisado.setFEPAGA(movimiento.getFEPAGA());
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
			//movimiento_revisado.setFEPGPR(movimiento.getFEPGPR());
		}
		
		
		else if (sAccion.equals("N")) //Anular 
		{
			movimiento_revisado.setPTPAGO(gasto.getPTPAGO());
			
			movimiento_revisado.setFFGTVP(gasto.getFFGTVP());
			//movimiento_revisado.setFEPAGA(gasto.getFEPAGA());
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
			//movimiento_revisado.setFEPGPR(ValoresDefecto.CAMPO_SIN_INFORMAR);
			

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
			//movimiento_revisado.setFEPAGA(gasto.getFEPAGA());
			movimiento_revisado.setFELIPG(gasto.getFELIPG());
			
			movimiento_revisado.setCOSIGA(gasto.getCOSIGA());
			movimiento_revisado.setFEEESI(gasto.getFEEESI());
			movimiento_revisado.setFEECOI(gasto.getFEECOI());
			movimiento_revisado.setFEEAUI(gasto.getFEEAUI());
			movimiento_revisado.setFEEPAI(gasto.getFEEPAI());

			movimiento_revisado.setFEAGTO(gasto.getFEAGTO());
			//movimiento_revisado.setFEPGPR(gasto.getFEPGPR());
			
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
			
			/*if (!movimiento.getFEPAGA().equals(gasto.getFEPAGA()))
			{
				bCambio = true;
				movimiento_revisado.setFEPAGA(movimiento.getFEPAGA());
			}
			else
			{
				movimiento_revisado.setFEPAGA(gasto.getFEPAGA());
			}*/

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
		
			/*if (!movimiento.getFEPGPR().equals(gasto.getFEPGPR()))
			{
				bCambio = true;
				movimiento_revisado.setFEPGPR(movimiento.getFEPGPR());
			}
			else
			{
				movimiento_revisado.setFEPGPR(gasto.getFEPGPR());
			}*/
			
			
			//cambio de provision tras cierre
			if (!movimiento.getNUPROF().equals(buscarProvisionGasto(Integer.parseInt(movimiento.getCOACES()), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE())))
			{
				bCambio = true;
			}
			
			if (!bCambio)
			{
				movimiento_revisado.setCOSIGA("#");
			}
		}
		
		//Pago
		movimiento_revisado.setFEPAGA(movimiento.getFEPAGA());
		movimiento_revisado.setFEPGPR(movimiento.getFEPGPR());
		
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
			
			logger.debug("validando moviviento...");
			
			if (movimiento_revisado.getNUPROF().equals(""))
			{
				//Error 800 - Error, gasto sin provision  
				iCodigo = -800;
			}
			else if (!movimiento_revisado.getFEAGTO().equals(ValoresDefecto.CAMPO_NUME_SIN_INFORMAR) && !QMGastos.existeGasto(conexion,Integer.parseInt(movimiento_revisado.getCOACES()), movimiento_revisado.getCOGRUG(), movimiento_revisado.getCOTPGA(), movimiento_revisado.getCOSBGA(), movimiento_revisado.getFEDEVE()))
			{
				//Error 002 - Llega fecha de anulación y no existe gasto en la tabla  
				iCodigo = -2;
			}
			else if (movimiento_revisado.getYCOS02().equals("-") 
					&& (movimiento_revisado.getCOSBGA().length() == 1)  
					&& !(sEstado.equals("3") || sEstado.equals("4")))
			{
				//Error 003 - Llega un abono de un gasto que NO está autorizado o pagado           
				iCodigo = -3;
			}		
			else if (Long.parseLong(movimiento_revisado.getIMDTGA()) > Long.parseLong(movimiento_revisado.getIMNGAS())) 
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
			else if (!CLActivos.existeActivo(Integer.parseInt(movimiento_revisado.getCOACES())))
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
			else if (movimiento_revisado.getPTPAGO().equals(ValoresDefecto.CAMPO_NUME_SIN_INFORMAR) || movimiento_revisado.getPTPAGO().equals(""))
			{
				//Error 019 - Periodicidad del gasto es cero o espacios.
				iCodigo = -19;
			}		
			else if (!movimiento_revisado.getFEAGTO().equals(ValoresDefecto.CAMPO_NUME_SIN_INFORMAR) && sEstado.equals("4"))
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
			
			else if (movimiento_revisado.getFEEESI().equals("") 
					&& movimiento_revisado.getFEECOI().equals(""))
			{
				//Error 807 - Fecha de estado sin informar
				iCodigo = -807;
			}
			
			//TODO REVISAR 
			else if (Long.parseLong(movimiento_revisado.getIMDTGA()) < 0)
			{
				//Error 808 - Importe de descuento negativo
				iCodigo = -808;
			}
			else if (Long.parseLong(movimiento_revisado.getIMIMGA()) < 0)
			{
				//Error 809 - Importe de impuestos negativo
				iCodigo = -809;
			}	
			
			else if (movimiento_revisado.getFEDEVE().equals(ValoresDefecto.CAMPO_NUME_SIN_INFORMAR))
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
