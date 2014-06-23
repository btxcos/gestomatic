package com.provisiones.ll;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMComunidades;
import com.provisiones.dal.qm.QMCuentas;
import com.provisiones.dal.qm.listas.QMListaComunidades;
import com.provisiones.dal.qm.listas.QMListaComunidadesActivos;
import com.provisiones.dal.qm.listas.QMListaCuentasComunidades;
import com.provisiones.dal.qm.listas.errores.QMListaErroresComunidades;
import com.provisiones.dal.qm.movimientos.QMMovimientosComunidades;
import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Comunidad;
import com.provisiones.types.Cuenta;
import com.provisiones.types.Nota;
import com.provisiones.types.movimientos.MovimientoComunidad;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.ComunidadTabla;

public final class CLComunidades 
{
	private static Logger logger = LoggerFactory.getLogger(CLComunidades.class.getName());
	
	private CLComunidades(){}
	
	//ID
	public static long buscarCodigoComunidad (String sCodCOCLDO, String sCodNUDCOM)
	{
		return QMComunidades.getComunidadID(ConnectionManager.getDBConnection(),sCodCOCLDO, sCodNUDCOM);
	}
	
	//Conversion
	public static MovimientoComunidad convierteComunidadenMovimiento(Comunidad comunidad, int iCodCOACES, String sCodCOACCI)
	{
		logger.debug("Convirtiendo...");
		
		Cuenta cuenta = CLCuentas.buscarCuenta(Long.parseLong(comunidad.getsCuenta()));
		
		return new MovimientoComunidad(ValoresDefecto.DEF_E1_CODTRN,
				ValoresDefecto.DEF_COTDOR,
				ValoresDefecto.DEF_IDPROV,
				sCodCOACCI,
				ValoresDefecto.DEF_COENGP,
				comunidad.getsCOCLDO(),
				comunidad.getsNUDCOM(),
				"",
				Integer.toString(iCodCOACES),
				"",
				comunidad.getsNOMCOC(),
				"",
				comunidad.getsNODCCO(),
				"",
				comunidad.getsNOMPRC(),
				"",
				comunidad.getsNUTPRC(),
				"",
				comunidad.getsNOMADC(),
				"",
				comunidad.getsNUTADC(),
				"",
				comunidad.getsNODCAD(),
				"",
				cuenta.getsNUCCEN(),
				cuenta.getsNUCCOF(),
				cuenta.getsNUCCDI(),
				cuenta.getsNUCCNT(),
				"",
				comunidad.getsOBTEXC(),
				"");
		
	}
	public static Comunidad convierteMovimientoenComunidad(MovimientoComunidad movimiento)
	{
		logger.debug("Convirtiendo...");
		
		return new Comunidad(movimiento.getCOCLDO(),
				movimiento.getNUDCOM(),
				movimiento.getNOMCOC(),
				movimiento.getNODCCO(),
				movimiento.getNOMPRC(),
				movimiento.getNUTPRC(),
				movimiento.getNOMADC(),
				movimiento.getNUTADC(),
				movimiento.getNODCAD(),
				Long.toString(CLCuentas.buscarCodigoCuenta(movimiento.getNUCCEN(), movimiento.getNUCCOF(), movimiento.getNUCCDI(), movimiento.getNUCCNT())),
				movimiento.getOBTEXC());
	}
	
	//Interfaz básico

	
	public static ArrayList<ComunidadTabla> buscarComunidad (String sCodCOCLDO, String sCodNUDCOM)
	{
		return QMComunidades.buscaComunidad(ConnectionManager.getDBConnection(),buscarCodigoComunidad (sCodCOCLDO, sCodNUDCOM));
	}
	
	public static ArrayList<ComunidadTabla> buscarComunidades ()
	{
		return QMComunidades.buscaComunidad(ConnectionManager.getDBConnection(),0);
	}
	
	public static ArrayList<ComunidadTabla> buscarComunidadConNombre (String sNOMCOC)
	{
		return QMComunidades.buscaComunidadPorNombre(ConnectionManager.getDBConnection(), sNOMCOC);
	}
	
	public static ArrayList<ComunidadTabla> buscarComunidadCuotasConNombre (String sNOMCOC)
	{
		return QMComunidades.buscaComunidadCuotasPorNombre(ConnectionManager.getDBConnection(), sNOMCOC);
	}
	
	public static ArrayList<ComunidadTabla> buscarComunidadActivo (int iCOACES)
	{
		return QMListaComunidadesActivos.buscaComunidadActivo(ConnectionManager.getDBConnection(),iCOACES);
	}
	
	public static ArrayList<ActivoTabla> buscarActivosComunidad (String sCodCOCLDO, String sCodNUDCOM)
	{
		return QMListaComunidadesActivos.buscaActivosComunidad(ConnectionManager.getDBConnection(),buscarCodigoComunidad (sCodCOCLDO, sCodNUDCOM));
	}
	
	public static ArrayList<ActivoTabla> buscarActivosCodigoComunidad (long liCodComunidad)
	{
		return QMListaComunidadesActivos.buscaActivosComunidad(ConnectionManager.getDBConnection(),liCodComunidad);
	}
	
	public static ArrayList<ActivoTabla> buscarActivosComunidadDisponibles (ActivoTabla filtro, String sCodCOCLDO, String sCodNUDCOM)
	{
		ArrayList<ActivoTabla> result = new ArrayList<ActivoTabla>();

		if (CLComunidades.existeComunidad(sCodCOCLDO, sCodNUDCOM))
		{
			result = QMListaComunidadesActivos.buscaActivosComunidadDisponibles(ConnectionManager.getDBConnection(),filtro,CLComunidades.buscarCodigoComunidad(sCodCOCLDO, sCodNUDCOM)); 
		}

		return result;
	}
	
	public static ArrayList<ActivoTabla> buscarActivosConComunidad (ActivoTabla activofiltro)
	{
		return QMListaComunidadesActivos.buscaActivosConComunidad(ConnectionManager.getDBConnection(),activofiltro);
	}

	public static ArrayList<ActivoTabla> buscarActivosSinComunidad (ActivoTabla activofiltro)
	{
		return QMListaComunidadesActivos.buscaActivosSinComunidad(ConnectionManager.getDBConnection(),activofiltro);
	}
	
	public static Comunidad buscarComunidadDeActivo (int iCodCOACES)
	{
		return QMListaComunidadesActivos.buscaComunidadPorActivo(ConnectionManager.getDBConnection(),iCodCOACES);
	}
	
	public static long buscarCodigoComunidadDeActivo (int iCodCOACES)
	{
		return QMListaComunidadesActivos.buscaCodigoComunidadPorActivo(ConnectionManager.getDBConnection(),iCodCOACES);
	}
	
	public static ArrayList<ComunidadTabla> buscarComunidadesPagablesDeProvision (String sNUPROF)
	{
		return QMListaComunidadesActivos.buscaComunidadesPagablesPorProvision(ConnectionManager.getDBConnection(),sNUPROF);
	}
	
	public static ArrayList<ComunidadTabla> buscarComunidadesProvisionadasConFiltro(ComunidadTabla filtro)
	{
		return QMListaComunidadesActivos.buscaComunidadesProvisionadasPorFiltro(ConnectionManager.getDBConnection(), filtro);
	}
	
	public static Comunidad buscarComunidad (long liCodComunidad)
	{
		return QMComunidades.getComunidad(ConnectionManager.getDBConnection(),liCodComunidad);
	}

	public static Comunidad buscarDetallesComunidad (long liCodComunidad)
	{
		return QMComunidades.getDetallesComunidad(ConnectionManager.getDBConnection(),liCodComunidad);
	}
	
	public static MovimientoComunidad buscarMovimientoComunidad (long liCodMovimiento)
	{
		return QMMovimientosComunidades.getMovimientoComunidad(ConnectionManager.getDBConnection(),liCodMovimiento);
	}
	
	public static String buscarNota (long liCodComunidad)
	{
		return QMComunidades.getNota(ConnectionManager.getDBConnection(),liCodComunidad);
	}
	
	public static boolean guardarNota (long liCodComunidad, String sNota)
	{
		return QMComunidades.setNota(ConnectionManager.getDBConnection(),liCodComunidad, sNota);
	}
	
	public static long buscarNumeroMovimientosComunidadesPendientes()
	{
		return (QMListaComunidadesActivos.buscaCantidadValidado(ConnectionManager.getDBConnection(),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE) 
				+ QMListaComunidades.buscaCantidadValidado(ConnectionManager.getDBConnection(),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE));
	}
	
	public static long buscarNumeroActivosComunidad(long liCodComunidad)
	{
		return QMListaComunidadesActivos.buscaNumeroActivos(ConnectionManager.getDBConnection(), liCodComunidad);
	}
	
	public static int comprobarComunidadActivo (String sCOCLDO, String sNUDCOM, int iCodCOACES)
	{
		int iCodigo = 0;
		
		if (CLActivos.existeActivo(iCodCOACES))
		{
			if (CLComunidades.comprobarRelacion(sCOCLDO, sNUDCOM, iCodCOACES))
			{
				iCodigo = 0;
			}
			else
			{
				iCodigo = -1;
			}
		}
		else
		{
			iCodigo = -2;
		}

		logger.debug("Código de salida:|{}|",iCodigo);
		
		return iCodigo;
	}
	
	public static boolean comprobarRelacion (String sCodCOCLDO, String sCodNUDCOM, int iCodCOACES)
	{
		return QMListaComunidadesActivos.compruebaRelacionComunidadActivo(ConnectionManager.getDBConnection(),buscarCodigoComunidad (sCodCOCLDO, sCodNUDCOM), iCodCOACES);
	}
	
	public static Comunidad consultarComunidad (String sCOCLDO, String sNUDCOM)
	{
		return QMComunidades.getComunidad(ConnectionManager.getDBConnection(),buscarCodigoComunidad(sCOCLDO,sNUDCOM));
	}
	
	public static boolean esActivoVinculadoAComunidad (int iCodCOACES)
	{
		return QMListaComunidadesActivos.activoVinculadoComunidad(ConnectionManager.getDBConnection(),iCodCOACES);
	}
	
	public static boolean existeComunidad (String sCodCOCLDO, String sCodNUDCOM)
	{
		return QMComunidades.existeComunidad(ConnectionManager.getDBConnection(),buscarCodigoComunidad (sCodCOCLDO, sCodNUDCOM));
	}
	
	public static boolean existeMovimientoComunidad (long liCodMovimiento)
	{
		return QMMovimientosComunidades.existeMovimientoComunidad(ConnectionManager.getDBConnection(),liCodMovimiento);
	}
	
	//Interfaz avanzado
	public static int actualizarComunidadLeida(String linea)
	{
		int iCodigo = 0; //Variable de entorno para fallo conexion

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			MovimientoComunidad comunidad = Parser.leerComunidad(linea);

			if (CLActivos.existeActivo(Integer.parseInt(comunidad.getCOACES())))
			{
				logger.debug(comunidad.logMovimientoComunidad());
				
				
				long liCodMovimiento = QMMovimientosComunidades.getMovimientoComunidadID(conexion,comunidad);
				
				logger.debug("liCodMovimiento|"+liCodMovimiento+"|");
				
				if (liCodMovimiento != 0)
				{
					logger.debug("comunidad.getCOACCI()|{}|",comunidad.getCOACCI());

					ValoresDefecto.TIPOSACCIONES COACCI = ValoresDefecto.TIPOSACCIONES.valueOf(comunidad.getCOACCI());
					
					String sEstado = "";
					
					switch (COACCI)
					{
					case X:case E:
						sEstado = QMListaComunidadesActivos.getValidado(conexion,liCodMovimiento);
						break;
						
					case M: case B: case A:
						sEstado = QMListaComunidades.getValidado(conexion,liCodMovimiento);
						break;
						
					default:
						logger.error("Se ha recibido un movimiento con acción desconocida:|{}|.",comunidad.getCOACCI());
						iCodigo = -9;
						break;
					}
					
					if (sEstado.equals("P"))
					{
						iCodigo = -11;
					}
					else if (sEstado.equals("X") || sEstado.equals("V") || sEstado.equals("R"))
					{
						iCodigo = -12;
					}
					else if (sEstado.equals("E"))
					{
						String sValidado = "";
						
						logger.debug("comunidad.getCOTDOR()|{}|",comunidad.getCOTDOR());
						logger.debug("ValoresDefecto.DEF_COTDOR|{}|",ValoresDefecto.DEF_COTDOR);
						
						long liCodComunidad = buscarCodigoComunidad(comunidad.getCOCLDO(),comunidad.getNUDCOM());

						if (comunidad.getCOTDOR().equals(ValoresDefecto.DEF_COTDOR))
						{
							if (COACCI.equals("B"))
							{
								sValidado = "R";
							}
							else
							{
								sValidado = "V";
							}
							
						}
						else
						{
							sValidado = "X";
						}
						
						logger.debug("sValidado|{}|",sValidado);

						switch (COACCI)
						{
						case X:case E:
							if (QMListaComunidadesActivos.existeRelacionComunidad(conexion,Integer.parseInt(comunidad.getCOACES()), liCodComunidad, liCodMovimiento))
							{
								if (QMListaComunidadesActivos.setValidado(conexion,liCodMovimiento, sValidado))
								{
									if (sValidado.equals("X"))
									{
										//recibido un error
										if (QMListaErroresComunidades.addErrorComunidad(conexion,liCodMovimiento, comunidad.getCOTDOR()))
										{
											iCodigo = 1;
										}
										else
										{
											QMListaComunidadesActivos.setValidado(conexion,liCodMovimiento, "E");
											iCodigo = -6;
										}
									}
									else
									{
										//TODO borrar activo validado con COACCI E
										if (comunidad.getCOACCI().equals(ValoresDefecto.DEF_COACCI_COMUNIDAD_BAJA_ACTIVO))
										{
											QMListaComunidadesActivos.delRelacionComunidad(conexion, liCodMovimiento);
										}
										//recibido un OK
										logger.info("Movimiento validado.");
									}
								}
								else
								{
									iCodigo = -5;
								}
							}
							else
							{
								iCodigo = -3;
							}
							break;
						case A:
							if (QMListaComunidades.existeRelacionComunidad(conexion,liCodComunidad, liCodMovimiento))
							{
								if (QMListaComunidadesActivos.existeRelacionComunidad(conexion, Integer.parseInt(comunidad.getCOACES()), liCodComunidad, liCodMovimiento))
								{
									if (QMListaComunidades.setValidado(conexion,liCodMovimiento, sValidado))
									{
										if (QMListaComunidadesActivos.setValidado(conexion,liCodMovimiento, sValidado))
										{
											if (sValidado.equals("X"))
											{
												//recibido error
												if (QMListaErroresComunidades.addErrorComunidad(conexion,liCodMovimiento, comunidad.getCOTDOR()))
												{
													iCodigo = 1;
												}
												else
												{
													QMListaComunidadesActivos.setValidado(conexion,liCodMovimiento, "E");
													QMListaComunidades.setValidado(conexion,liCodMovimiento, "E");
													iCodigo = -6;
												}
											}
											else
											{
												//recibido OK
												logger.info("Movimiento validado.");
											}
										}
										else
										{
											QMListaComunidades.setValidado(conexion,liCodMovimiento, "E");
											iCodigo = -5;
										}
									}
									else
									{
										iCodigo = -4;
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
							break;
						case M: case B:
							if (QMListaComunidades.existeRelacionComunidad(conexion,liCodComunidad, liCodMovimiento))
							{
								if(QMListaComunidades.setValidado(conexion,liCodMovimiento, sValidado))
								{
									if (sValidado.equals("X"))
									{
										//recibido error
										if (QMListaErroresComunidades.addErrorComunidad(conexion,liCodMovimiento, comunidad.getCOTDOR()))
										{
											iCodigo = 1;
										}
										else
										{
											QMListaComunidades.setValidado(conexion,liCodMovimiento, "E");
											iCodigo = -6;
										}
									}
									else
									{
										//recibido OK
										logger.info("Movimiento validado.");
									}
								}
								else
								{
									iCodigo = -4;
								}
							}
							else
							{
								iCodigo = -2;
							}
							break;
							
						default:
							logger.error("Se ha recibido un movimiento con acción desconocida:|{}|.",comunidad.getCOACCI());
							iCodigo = -9;
							break;
						
						}
						
						//bSalida = QMMovimientosComunidades.modMovimientoComunidad(comunidad, liCodMovimiento);
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
					logger.error("|{}|",linea);
					iCodigo = -1;
				}
			}
			else
			{
				//activo desconocido
				iCodigo = -8;
			}

		}
		
		logger.error("iCodigo:|{}|",iCodigo);
		
		return iCodigo;
	}
	
	public static int inyectarComunidadVolcada(String linea)
	{
		int iCodigo = 0; //Variable de entorno para fallo conexion

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			MovimientoComunidad movimiento = Parser.leerComunidad(linea);

			if (CLActivos.existeActivo(Integer.parseInt(movimiento.getCOACES())))
			{
				logger.debug(movimiento.logMovimientoComunidad());
				
				
				long liCodMovimiento = QMMovimientosComunidades.getMovimientoComunidadID(conexion,movimiento);
				
				logger.debug("liCodMovimiento|"+liCodMovimiento+"|");
				
				if (liCodMovimiento == 0)
				{
					logger.debug("comunidad.getCOACCI()|{}|",movimiento.getCOACCI());

					ValoresDefecto.TIPOSACCIONES COACCI = ValoresDefecto.TIPOSACCIONES.valueOf(movimiento.getCOACCI());
					
					String sEstado = "";
					
					switch (COACCI)
					{
					case X:case E:
						sEstado = QMListaComunidadesActivos.getValidado(conexion,liCodMovimiento);
						break;

					case M: case B:
						sEstado = QMListaComunidades.getValidado(conexion,liCodMovimiento);
						break;
					case A:
						sEstado = "";
					 	break;
					default:
						logger.error("Se ha recibido un movimiento con acción desconocida:|"+movimiento.getCOACCI()+"|.");
						iCodigo = -9;
						break;
					}
					
					if (sEstado.equals("P"))
					{
						iCodigo = -11;
					}
					else if (sEstado.equals("X") || sEstado.equals("R"))
					{
						iCodigo = -12;
					}
					else if (sEstado.equals("E"))
					{
						iCodigo = -13;
					}
					else if (sEstado.isEmpty() || sEstado.equals("V"))
					{
						try 
						{
							conexion.setAutoCommit(false);
							
							long indice = QMMovimientosComunidades.addMovimientoComunidad(conexion,movimiento);
							
							long liCodComunidad = buscarCodigoComunidad(movimiento.getCOCLDO(),movimiento.getNUDCOM());
							
							if (indice == 0)
							{
								//Error al crear un movimiento
								iCodigo = -900;
							}
							else
							{
								//ValoresDefecto.TIPOSACCIONES COACCI = ValoresDefecto.TIPOSACCIONES.valueOf(movimiento.getCOACCI());
								
								switch (COACCI) 
								{
									case A:
										if (liCodComunidad == 0)
										{
											
											long liCodCuenta = QMCuentas.getCuentaID(conexion, movimiento.getNUCCEN(), movimiento.getNUCCOF(), movimiento.getNUCCDI(), movimiento.getNUCCNT());
											
											if (liCodCuenta == 0)
											{
												logger.debug("Dando de Alta...");
												
												String sPais = "ES";
												
												String sDCIBAN = Utils.calculaDCIBAN(sPais, movimiento.getNUCCEN(), movimiento.getNUCCOF(), movimiento.getNUCCDI(), movimiento.getNUCCNT());
												
												liCodCuenta = QMCuentas.addCuenta(conexion, new Cuenta(sPais,sDCIBAN,movimiento.getNUCCEN(), movimiento.getNUCCOF(), movimiento.getNUCCDI(), movimiento.getNUCCNT(),"COMUNIDAD"));
												
												if (liCodCuenta != 0)
												{
													Comunidad comunidad = convierteMovimientoenComunidad(movimiento);
													
													liCodComunidad = QMComunidades.addComunidad(conexion,comunidad);
													
													if (liCodComunidad != 0)
													{

														if (QMListaComunidades.addRelacionComunidadInyectada(conexion,liCodComunidad, indice))
														{	
																						

															logger.debug("COACES:|{}|",movimiento.getCOACES());

															if (QMListaComunidadesActivos.addRelacionComunidadInyectada(conexion,Integer.parseInt(movimiento.getCOACES()), liCodComunidad, indice))
															{
																
																//relacion cuenta-Comunidad
																if (QMListaCuentasComunidades.addRelacionComunidad(conexion, liCodCuenta, liCodComunidad,ValoresDefecto.CUENTA_COMUNIDAD))
																{
																	//OK 
																	iCodigo = 0;
																}
																else
																{
																	//Error y Rollback - error al registrar la relacion cuenta-comunidad
																	iCodigo = -912;
																}
																
															}
															else
															{
																//Error y Rollback - error al registrar el activo durante el alta
																iCodigo = -903;
															}
														}
														else
														{
															//Error y Rollback - error al registrar la relaccion
															iCodigo = -902;
														}
													

													}
													else
													{
														//Error y Rollback - error al registrar la comuidad
														iCodigo = -901;
													}
													
													
												}
												else
												{
													//Error y Rollback - error al registrar la cuenta de la comunidad
													iCodigo = -911;
												}	
											}
											else
											{
												Comunidad comunidad = convierteMovimientoenComunidad(movimiento);
												
												liCodComunidad = QMComunidades.addComunidad(conexion,comunidad);
												
												if (liCodComunidad != 0)
												{

													if (QMListaComunidades.addRelacionComunidadInyectada(conexion,liCodComunidad, indice))
													{	
																					

														logger.debug("COACES:|{}|",movimiento.getCOACES());

														if (QMListaComunidadesActivos.addRelacionComunidadInyectada(conexion,Integer.parseInt(movimiento.getCOACES()), liCodComunidad, indice))
														{
															
															if (QMListaCuentasComunidades.addRelacionComunidad(conexion, liCodCuenta, liCodComunidad,ValoresDefecto.CUENTA_COMUNIDAD))
															{
																//OK 
																iCodigo = 0;
															}
															else
															{
																//Error y Rollback - error al registrar la relacion cuenta-comunidad
																iCodigo = -912;
															}
															
														}
														else
														{
															//Error y Rollback - error al registrar el activo durante el alta
															iCodigo = -903;
														}
													}
													else
													{
														//Error y Rollback - error al registrar la relaccion
														iCodigo = -902;
													}
												

												}
												else
												{
													//Error y Rollback - error al registrar la comuidad
													iCodigo = -901;
												}
												//Error y Rollback - Cuenta de la comunidad ya de alta
												//iCodigo = -914;
												
	
											}
											
											
											

											
										}
										else
										{
											//Error 801 - alta de una comunidad en alta
											iCodigo = -801;
										}
										break;
									case B:
										
										if (liCodComunidad != 0)
										{
											if (QMListaComunidades.addRelacionComunidadInyectada(conexion,liCodComunidad, indice))
											{
												
												logger.debug("Dando de Baja...");
												if (QMComunidades.setEstado(conexion,liCodComunidad, "B"))
												{
													//OK 
													iCodigo = 0; 
												}
												else
												{
													//error y rollback - error al cambiar el estado
													iCodigo = -904;
												}
											}
											else
											{
												//error y rollback - error al registrar la relaccion
												iCodigo = -902;
											}
										}
										else
										{
											//Error 803 - comunidad no disponible
											iCodigo = -803;
										}
										break;
									case M:
										if (liCodComunidad != 0)
										{
											if (QMListaComunidades.addRelacionComunidadInyectada(conexion,liCodComunidad, indice))
											{
												logger.debug("Modificando...");
													
												logger.debug(movimiento.logMovimientoComunidad());
												logger.debug(movimiento.logMovimientoComunidad());
												
												long liCodCuentaAntigua = QMComunidades.getCodCuentaComunidad(conexion, liCodComunidad);
												
												long liCuentaNueva = QMCuentas.getCuentaID(conexion,movimiento.getNUCCEN(), movimiento.getNUCCOF(), movimiento.getNUCCDI(), movimiento.getNUCCNT());
												
												if (liCodCuentaAntigua == liCuentaNueva)
												{
													if (QMComunidades.modComunidad(conexion,convierteMovimientoenComunidad(movimiento), liCodComunidad))
													{	
														//OK 
														iCodigo = 0;
													}
													else
													{
														//error y rollback - error al modificar la comunidad
														iCodigo = -905;
													}
													
												}
												else
												{
													if (liCuentaNueva == 0)
													{
														liCuentaNueva = QMCuentas.addCuenta(conexion, new Cuenta("ES","#",movimiento.getNUCCEN(), movimiento.getNUCCOF(), movimiento.getNUCCDI(), movimiento.getNUCCNT(),"COMUNIDAD"));
														
														if (liCuentaNueva != 0)
														{
															if (QMListaCuentasComunidades.addRelacionComunidad(conexion, liCuentaNueva, liCodComunidad, ValoresDefecto.CUENTA_COMUNIDAD))
															{
																if (QMComunidades.modComunidad(conexion,convierteMovimientoenComunidad(movimiento), liCodComunidad))
																{
																	if (QMListaCuentasComunidades.delRelacionComunidad(conexion, liCodCuentaAntigua, liCodComunidad))
																	{
																		if (!CLCuentas.tieneMasRelacciones(liCodCuentaAntigua))
																		{
																			if (QMCuentas.delCuenta(conexion, liCodCuentaAntigua))
																			{
																				//OK 
																				iCodigo = 0;
																			}
																			else
																			{
																				//Error y Rollback - error al eliminar la cuenta antigua de la comunidad
																				iCodigo = -913;
																			}
																		}
																		else
																		{
																			//OK 
																			iCodigo = 0;
																		}
																	}
																	else
																	{
																		//Error y Rollback - error al eliminar la cuenta antigua de la comunidad
																		iCodigo = -913;
																	}

																}
																else
																{
																	//error y rollback - error al modificar la comunidad
																	iCodigo = -905;
																}
															}
															else
															{
																//Error y Rollback - error al registrar la relacion cuenta-comunidad
																iCodigo = -912;
															}
														}
														else
														{
															//Error y Rollback - error al registrar la cuenta de la comunidad
															iCodigo = -911;
														}
													}
													else
													{
														//Error y Rollback - error al eliminar la cuenta antigua de la comunidad
														iCodigo = -914;
													}	
												}
											}
											else
											{
												//error y rollback - error al registrar la relaccion
												iCodigo = -902;
											}	
										}
										else
										{
											//Error 803 - comunidad no disponible
											iCodigo = -803;
										}
										break;
									case X:
										if (liCodComunidad != 0)
										{
											long  liCodMovimientoAlta = QMListaComunidadesActivos.getMovimientoDeActivoVinculadoComunidad(conexion,liCodComunidad, Integer.parseInt(movimiento.getCOACES()));
											if (liCodMovimientoAlta != 0)
											{
												//error y rollback - el activo ya esta vinculado
												iCodigo = -906;
											}
											else
											{							
												if (QMListaComunidadesActivos.addRelacionComunidadInyectada(conexion,Integer.parseInt(movimiento.getCOACES()), liCodComunidad, indice))
												{
													//OK 
													iCodigo = 0;
												}
												else
												{
													//error y rollback - error al asociar el activo en la comunidad
													iCodigo = -907;
												}
											}
										}
										else
										{
											//Error 803 - comunidad no disponible
											iCodigo = -803;
										}
										break;
									case E:
										if (liCodComunidad != 0)
										{
											long liCodMovimientoBaja = QMListaComunidadesActivos.getMovimientoDeActivoVinculadoComunidad(conexion,liCodComunidad, Integer.parseInt(movimiento.getCOACES()));
											
											if (liCodMovimientoBaja == 0)
											{
												//error y rollback - el activo no esta vinculado
												iCodigo = -908;
											}
											else
											{	
												if (QMListaComunidadesActivos.delRelacionComunidad(conexion,liCodMovimientoBaja))
												{
													//OK 
													iCodigo = 0;
												}
												else
												{
													//error y rollback - error al desasociar el activo en la comunidad
													iCodigo = -909;
												}
											}	
										}
										else
										{
											//Error 803 - comunidad no disponible
											iCodigo = -803;
										}
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
					else
					{
						iCodigo = -10;
					}
						
				}
				else 
				{
					logger.error("El siguiente registro ya se encuentra en el sistema:");
					logger.error("|{}|",linea);
					iCodigo = -1;
				}
			}
			else
			{
				//activo desconocido
				iCodigo = -8;
			}

		}
		
		logger.debug ("iCodigo:|"+iCodigo+"|");
		
		return iCodigo;
	}
	
	public static int comprobarActivo (int iCodCOACES)
	{
		int iCodigo = -3;
		
		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			if (CLActivos.existeActivo(iCodCOACES))
			{
				if (CLComunidades.esActivoVinculadoAComunidad(iCodCOACES))
				{
					//error - ya vinculado
					iCodigo = -1;
				}
			}
			else
			{
				//error - no existe
				iCodigo = -2;
			}
		}
		
		logger.debug("Código de salida:|{}|",iCodigo);
		
		return iCodigo;
	}
	
	
	
	public static int registraMovimiento(MovimientoComunidad movimiento, Nota nota)
	{
		int iCodigo = -910;//Error de conexion
		
		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			long liCodComunidad = buscarCodigoComunidad(movimiento.getCOCLDO(),movimiento.getNUDCOM());
			
			iCodigo = validaMovimiento(movimiento,liCodComunidad);
			
			if (iCodigo == 0)
			{
				//OK correcto estado y accion
				
				logger.debug("Revisar...");
				MovimientoComunidad movimiento_revisado = revisaCodigosControl(movimiento,liCodComunidad);
				logger.debug("Revisado!");
				
				
				if (movimiento_revisado.getCOACCI().equals("#"))
				{	
					if (nota.isbInvalida())
					{
						//Error modificacion sin cambios
						iCodigo = -804;
					}
					else
					{
						if (QMComunidades.setNota(conexion, liCodComunidad, nota.getsContenido()))
						{
							iCodigo = 0;
						}
						else
						{
							//Error al guardar la nota
							iCodigo = -915;
						}
						
					}
	
				}
				else
				{
					try 
					{
						conexion.setAutoCommit(false);
						
						long liCodMovimiento = QMMovimientosComunidades.addMovimientoComunidad(conexion,movimiento_revisado);
						
						if (liCodMovimiento == 0)
						{
							//Error al crear un movimiento
							iCodigo = -900;
						}
						else
						{
							ValoresDefecto.TIPOSACCIONES COACCI = ValoresDefecto.TIPOSACCIONES.valueOf(movimiento.getCOACCI());
							
							switch (COACCI) 
							{
								case A:
									
									long liCodCuenta = QMCuentas.getCuentaID(conexion, movimiento.getNUCCEN(), movimiento.getNUCCOF(), movimiento.getNUCCDI(), movimiento.getNUCCNT());
									
									if (liCodCuenta == 0)
									{
										logger.debug("Dando de Alta...");
										
										String sPais = "ES";

										String sDCIBAN = Utils.calculaDCIBAN(sPais, movimiento.getNUCCEN(), movimiento.getNUCCOF(), movimiento.getNUCCDI(), movimiento.getNUCCNT());
										
										liCodCuenta = QMCuentas.addCuenta(conexion, new Cuenta(sPais,sDCIBAN,movimiento.getNUCCEN(), movimiento.getNUCCOF(), movimiento.getNUCCDI(), movimiento.getNUCCNT(),"COMUNIDAD"));
										
										if (liCodCuenta != 0)
										{
											Comunidad comunidad = convierteMovimientoenComunidad(movimiento_revisado);
											
											liCodComunidad = QMComunidades.addComunidad(conexion,comunidad);
											
											if (liCodComunidad != 0)
											{

												if (QMListaComunidades.addRelacionComunidad(conexion,liCodComunidad, liCodMovimiento))
												{	
																				

													logger.debug("COACES:|{}|",movimiento_revisado.getCOACES());

													if (QMListaComunidadesActivos.addRelacionComunidad(conexion,Integer.parseInt(movimiento_revisado.getCOACES()), liCodComunidad, liCodMovimiento))
													{
														
														//relacion cuenta-Comunidad
														if (QMListaCuentasComunidades.addRelacionComunidad(conexion, liCodCuenta, liCodComunidad,ValoresDefecto.CUENTA_COMUNIDAD))
														{
															if (nota.isbInvalida())
															{
																//OK 
																iCodigo = 0;
															}
															else
															{
																if (QMComunidades.setNota(conexion, liCodComunidad, nota.getsContenido()))
																{
																	//OK 
																	iCodigo = 0;
																}
																else
																{
																	//Error al guardar la nota
																	iCodigo = -915;
																}
															}
														}
														else
														{
															//Error y Rollback - error al registrar la relacion cuenta-comunidad
															iCodigo = -912;
														}
														
														

													}
													else
													{
														//Error y Rollback - error al registrar el activo durante el alta
														iCodigo = -903;
													}
												}
												else
												{
													//Error y Rollback - error al registrar la relaccion
													iCodigo = -902;
												}
											

											}
											else
											{
												//Error y Rollback - error al registrar la comuidad
													iCodigo = -901;
											}
											
											
										}
										else
										{
											//Error y Rollback - error al registrar la cuenta de la comunidad
												iCodigo = -911;
										}	
									}
									else
									{
										Comunidad comunidad = convierteMovimientoenComunidad(movimiento_revisado);

										liCodComunidad = QMComunidades.addComunidad(conexion,comunidad);
										
										if (liCodComunidad != 0)
										{

											if (QMListaComunidades.addRelacionComunidad(conexion,liCodComunidad, liCodMovimiento))
											{	
																			

												logger.debug("COACES:|{}|",movimiento_revisado.getCOACES());

												if (QMListaComunidadesActivos.addRelacionComunidad(conexion,Integer.parseInt(movimiento_revisado.getCOACES()), liCodComunidad, liCodMovimiento))
												{
													
													//relacion cuenta-Comunidad
													if (QMListaCuentasComunidades.addRelacionComunidad(conexion, liCodCuenta, liCodComunidad,ValoresDefecto.CUENTA_COMUNIDAD))
													{
														if (nota.isbInvalida())
														{
															//OK 
															iCodigo = 0;
														}
														else
														{
															if (QMComunidades.setNota(conexion, liCodComunidad, nota.getsContenido()))
															{
																//OK 
																iCodigo = 0;
															}
															else
															{
																//Error al guardar la nota
																iCodigo = -915;
															}
														}
													}
													else
													{
														//Error y Rollback - error al registrar la relacion cuenta-comunidad
														iCodigo = -912;
													}
													
													

												}
												else
												{
													//Error y Rollback - error al registrar el activo durante el alta
													iCodigo = -903;
												}
											}
											else
											{
												//Error y Rollback - error al registrar la relaccion
												iCodigo = -902;
											}
										

										}
										else
										{
											//Error y Rollback - error al registrar la comuidad
												iCodigo = -901;
										}
										
										//Error y Rollback - Cuenta de la comunidad ya de alta
										//iCodigo = -914;


									}
									
									

																

									break;
								case B:
									if (QMListaComunidades.existeAltaPendienteComunidad(conexion, liCodComunidad))
									{
										ArrayList<Long> resultado = QMListaCuentasComunidades.buscaCodigosCuentasComunidad(conexion, liCodComunidad);
										
										if (QMComunidades.delComunidad(conexion, liCodComunidad))
										{
											//desasociar cuentas
											
											if (QMListaCuentasComunidades.eliminarRelacionesCuentasComunidad(conexion, liCodComunidad))
											{
												
												
												if (resultado.size() > 0)
												{
										            for (int i = 0; i < resultado.size(); i++)
										            {
									            		if (!CLCuentas.tieneMasRelacciones(resultado.get(i)))
									            		{
									            			if (QMCuentas.delCuenta(conexion, resultado.get(i)))
									            			{
										            			//OK 
																iCodigo = 0;
									            			}
									            			else
									            			{
																//Error al dar de baja las cuentas de la comunidad
																iCodigo = -917;
																i = resultado.size();
									            			}
									            		}
									            		else
									            		{
									            			//OK 
															iCodigo = 0;
									            		}
										            }
												}
											}
											else
											{
												//Error al desasociar las cuentas de la comunidad
												iCodigo = -918;
												
											}
											
											
											
											/*if (QMListaCuentasComunidades.eliminarCuentasComunidad(conexion, liCodComunidad))
											
											{
												CLCuentas.tieneMasRelacciones(liCodCuenta)
												//OK 
												iCodigo = 0;
											}
											else
											{
												//Error al dar de baja las cuentas de la comunidad
												iCodigo = -917;
											}*/
										}
										else
										{
											//Error al eliminar la comunidad
											iCodigo = -916;
										}
									}
									else
									{
										if (QMListaComunidades.addRelacionComunidad(conexion,liCodComunidad, liCodMovimiento))
										{
											
											logger.debug("Dando de Baja...");
											if (QMComunidades.setEstado(conexion,liCodComunidad, "B"))
											{
												if (nota.isbInvalida())
												{
													//OK 
													iCodigo = 0;
												}
												else
												{
													if (QMComunidades.setNota(conexion, liCodComunidad, nota.getsContenido()))
													{
														//OK 
														iCodigo = 0;
													}
													else
													{
														//Error al guardar la nota
														iCodigo = -915;
													}
												}
											}
											else
											{
												//error y rollback - error al cambiar el estado
												iCodigo = -904;
											}
										}
										else
										{
											//error y rollback - error al registrar la relaccion
											iCodigo = -902;
										}
									}
									
									
									break;
								case M:
									if (QMListaComunidades.addRelacionComunidad(conexion,liCodComunidad, liCodMovimiento))
									{
										logger.debug("Modificando...");
											
										logger.debug(movimiento.logMovimientoComunidad());
										logger.debug(movimiento_revisado.logMovimientoComunidad());
										
										long liCodCuentaAntigua = QMComunidades.getCodCuentaComunidad(conexion, liCodComunidad);
										
										long liCuentaNueva = QMCuentas.getCuentaID(conexion,movimiento.getNUCCEN(), movimiento.getNUCCOF(), movimiento.getNUCCDI(), movimiento.getNUCCNT());
										
										if (liCodCuentaAntigua == liCuentaNueva)
										{
											if (QMComunidades.modComunidad(conexion,convierteMovimientoenComunidad(movimiento), liCodComunidad))
											{	
												if (nota.isbInvalida())
												{
													//OK 
													iCodigo = 0;
												}
												else
												{
													if (QMComunidades.setNota(conexion, liCodComunidad, nota.getsContenido()))
													{
														//OK 
														iCodigo = 0;
													}
													else
													{
														//Error al guardar la nota
														iCodigo = -915;
													}
												}
											}
											else
											{
												//error y rollback - error al modificar la comunidad
												iCodigo = -905;
											}
											
										}
										else
										{
											if (liCuentaNueva == 0)
											{
												String sPais = "ES";

												String sDCIBAN = Utils.calculaDCIBAN(sPais, movimiento.getNUCCEN(), movimiento.getNUCCOF(), movimiento.getNUCCDI(), movimiento.getNUCCNT());
												
												liCuentaNueva = QMCuentas.addCuenta(conexion, new Cuenta(sPais,sDCIBAN,movimiento.getNUCCEN(), movimiento.getNUCCOF(), movimiento.getNUCCDI(), movimiento.getNUCCNT(),"COMUNIDAD"));
												
												if (liCuentaNueva != 0)
												{
													if (QMListaCuentasComunidades.addRelacionComunidad(conexion, liCuentaNueva, liCodComunidad, ValoresDefecto.CUENTA_COMUNIDAD))
													{
														if (QMComunidades.modComunidad(conexion,convierteMovimientoenComunidad(movimiento), liCodComunidad))
														{
															if (QMCuentas.delCuenta(conexion, liCodCuentaAntigua))
															{
																if (nota.isbInvalida())
																{
																	//OK 
																	iCodigo = 0;
																}
																else
																{
																	if (QMComunidades.setNota(conexion, liCodComunidad, nota.getsContenido()))
																	{
																		//OK 
																		iCodigo = 0;
																	}
																	else
																	{
																		//Error al guardar la nota
																		iCodigo = -915;
																	}
																	
																}
															}
															else
															{
																//Error y Rollback - error al eliminar la cuenta antigua de la comunidad
																iCodigo = -913;
															}

														}
														else
														{
															//error y rollback - error al modificar la comunidad
															iCodigo = -905;
														}
													}
													else
													{
														//Error y Rollback - error al registrar la relacion cuenta-comunidad
														iCodigo = -912;
													}
												}
												else
												{
													//Error y Rollback - error al registrar la cuenta de la comunidad
														iCodigo = -911;
												}
											}
											else
											{
												//Error y Rollback - error la cuenta nueva ya existe
												iCodigo = -914;
											}	
										}

									}
									else
									{
										//error y rollback - error al registrar la relaccion
										iCodigo = -902;
									}
									break;
								case X:
										long  liCodMovimientoAlta = QMListaComunidadesActivos.getMovimientoDeActivoVinculadoComunidad(conexion,liCodComunidad, Integer.parseInt(movimiento_revisado.getCOACES()));
										if (liCodMovimientoAlta != 0)
										{
											//error y rollback - el activo ya esta vinculado
											iCodigo = -906;
										}
										else
										{							
											if (QMListaComunidadesActivos.addRelacionComunidad(conexion,Integer.parseInt(movimiento_revisado.getCOACES()), liCodComunidad, liCodMovimiento))
											{
												if (nota.isbInvalida())
												{
													//OK 
													iCodigo = 0;
												}
												else
												{
													if (QMComunidades.setNota(conexion, liCodComunidad, nota.getsContenido()))
													{
														//OK 
														iCodigo = 0;
													}
													else
													{
														//Error al guardar la nota
														iCodigo = -915;
													}
													
												}
											}
											else
											{
												//error y rollback - error al asociar el activo en la comunidad
												iCodigo = -907;
											}
										}
									break;
								case E:
										long liCodMovimientoBaja = QMListaComunidadesActivos.getMovimientoDeActivoVinculadoComunidad(conexion,liCodComunidad, Integer.parseInt(movimiento_revisado.getCOACES()));
										
										if (liCodMovimientoBaja == 0)
										{
											//error y rollback - el activo no esta vinculado
											iCodigo = -908;
										}
										else
										{
											if (QMListaComunidadesActivos.existeAltaPendienteActivo(conexion, Integer.parseInt(movimiento_revisado.getCOACES()), liCodComunidad))
											{
												if (QMListaComunidadesActivos.delRelacionComunidad(conexion,liCodMovimientoBaja))//.setValidado(conexion, liCodMovimientoBaja, ValoresDefecto.DEF_MOVIMIENTO_RESUELTO))
													
												{
													if (nota.isbInvalida())
													{
														//OK 
														iCodigo = 0;
													}
													else
													{
														if (QMComunidades.setNota(conexion, liCodComunidad, nota.getsContenido()))
														{
															//OK 
															iCodigo = 0;
														}
														else
														{
															//Error al guardar la nota
															iCodigo = -915;
														}
														
													}
												}
												else
												{
													//error y rollback - error al desasociar el activo en la comunidad
													iCodigo = -909;
												}

											}
											else
											{
												if (QMListaComunidadesActivos.addRelacionComunidad(conexion,Integer.parseInt(movimiento_revisado.getCOACES()), liCodComunidad, liCodMovimiento))
												{
													if (nota.isbInvalida())
													{
														//OK 
														iCodigo = 0;
													}
													else
													{
														if (QMComunidades.setNota(conexion, liCodComunidad, nota.getsContenido()))
														{
															//OK 
															iCodigo = 0;
														}
														else
														{
															//Error al guardar la nota
															iCodigo = -915;
														}
														
													}
												}
												else
												{
													//error y rollback - error al asociar el activo en la comunidad
													iCodigo = -909;

												}
												
											}
										}
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

		logger.debug("Codigo de Salida:|{}|",iCodigo);
		return iCodigo;
	}

	public static MovimientoComunidad revisaCodigosControl(MovimientoComunidad movimiento, long liCodComunidad)
	{
		MovimientoComunidad movimiento_revisado = new MovimientoComunidad("", "", "", "", "", "", "", "", "0", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "0", "0", "0", "0", "", "", "");
		
		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			Comunidad comunidad = QMComunidades.getComunidad(conexion,liCodComunidad);
			
			String sEstado = QMComunidades.getEstado(conexion,liCodComunidad);
			
			logger.debug(comunidad.logComunidad());
			
			logger.debug(movimiento.logMovimientoComunidad());
			
			
			logger.debug("Estado:|{}|",sEstado);
			logger.debug("Acción:|{}|",movimiento.getCOACCI());
			
			movimiento_revisado.setCODTRN(movimiento.getCODTRN());
			movimiento_revisado.setCOTDOR(movimiento.getCOTDOR());
			movimiento_revisado.setIDPROV(movimiento.getIDPROV());
			movimiento_revisado.setCOACCI(movimiento.getCOACCI());
			movimiento_revisado.setCOENGP(movimiento.getCOENGP());
			movimiento_revisado.setCOCLDO(movimiento.getCOCLDO());
			movimiento_revisado.setNUDCOM(movimiento.getNUDCOM());
		
			movimiento_revisado.setOBDEER(movimiento.getOBDEER());
			
					
			
				if (movimiento.getCOACCI().equals("A"))
				{
					
					if (movimiento.getNOMCOC().isEmpty())
					{
						movimiento_revisado.setBITC01("#");
					}
					else
					{
						movimiento_revisado.setBITC01("S");
						movimiento_revisado.setNOMCOC(movimiento.getNOMCOC());
					}

					if (movimiento.getNODCCO().isEmpty())
					{
						movimiento_revisado.setBITC02("#");
					}
					else
					{
						movimiento_revisado.setBITC02("S");
						movimiento_revisado.setNODCCO(movimiento.getNODCCO());
					}

					if (movimiento.getNOMPRC().isEmpty())
					{
						movimiento_revisado.setBITC03("#");
					}
					else
					{
						movimiento_revisado.setBITC03("S");
						movimiento_revisado.setNOMPRC(movimiento.getNOMPRC());
					}
					
					if (movimiento.getNUTPRC().isEmpty())
					{
						movimiento_revisado.setBITC04("#");
					}
					else
					{
						movimiento_revisado.setBITC04("S");
						movimiento_revisado.setNUTPRC(movimiento.getNUTPRC());
					}
					
					if (movimiento.getNOMADC().isEmpty())
					{
						movimiento_revisado.setBITC05("#");
					}
					else
					{
						movimiento_revisado.setBITC05("S");
						movimiento_revisado.setNOMADC(movimiento.getNOMADC());
					}
					
					if (movimiento.getNUTADC().isEmpty())
					{
						movimiento_revisado.setBITC06("#");
					}
					else
					{
						movimiento_revisado.setBITC06("S");
						movimiento_revisado.setNUTADC(movimiento.getNUTADC());
					}
					
					if (movimiento.getNODCAD().isEmpty())
					{
						movimiento_revisado.setBITC07("#");
					}
					else
					{
						movimiento_revisado.setBITC07("S");
						movimiento_revisado.setNODCAD(movimiento.getNODCAD());
					}
					
					if (movimiento.getNUCCEN().isEmpty() && movimiento.getNUCCOF().isEmpty() && movimiento.getNUCCDI().isEmpty() && movimiento.getNUCCNT().isEmpty())
					{
						movimiento_revisado.setBITC08("#");
					}
					else
					{
						movimiento_revisado.setBITC08("S");
						movimiento_revisado.setNUCCEN(movimiento.getNUCCEN());
						movimiento_revisado.setNUCCOF(movimiento.getNUCCOF());
						movimiento_revisado.setNUCCDI(movimiento.getNUCCDI());
						movimiento_revisado.setNUCCNT(movimiento.getNUCCNT());
					}
					
					if (movimiento.getOBTEXC().isEmpty())
					{
						movimiento_revisado.setBITC09("#");
					}
					else
					{
						movimiento_revisado.setBITC09("A");
						movimiento_revisado.setOBTEXC(movimiento.getOBTEXC());
					}
					
					if (movimiento.getCOACES().isEmpty())
					{
						movimiento_revisado.setBITC10("#");
					}
					else
					{
						movimiento_revisado.setBITC10("S");
						movimiento_revisado.setCOACES(movimiento.getCOACES());
					}
				}
				else if (movimiento.getCOACCI().equals("M"))
				{
					boolean bCambio = false;
					
					if (movimiento.getNOMCOC().equals(comunidad.getsNOMCOC()))
					{
						movimiento_revisado.setBITC01("#");
					}
					else
					{
						movimiento_revisado.setBITC01("S");
						movimiento_revisado.setNOMCOC(movimiento.getNOMCOC());
						bCambio = true;
					}

					if (movimiento.getNODCCO().equals(comunidad.getsNODCCO()))
					{
						movimiento_revisado.setBITC02("#");
					}
					else
					{
						movimiento_revisado.setBITC02("S");
						movimiento_revisado.setNODCCO(movimiento.getNODCCO());
						bCambio = true;
					}

					if (movimiento.getNOMPRC().equals(comunidad.getsNOMPRC()))
					{
						movimiento_revisado.setBITC03("#");
					}
					else
					{
						movimiento_revisado.setBITC03("S");
						movimiento_revisado.setNOMPRC(movimiento.getNOMPRC());
						bCambio = true;
					}
					
					if (movimiento.getNUTPRC().equals(comunidad.getsNUTPRC()))
					{
						movimiento_revisado.setBITC04("#");
					}
					else
					{
						movimiento_revisado.setBITC04("S");
						movimiento_revisado.setNUTPRC(movimiento.getNUTPRC());
						bCambio = true;
					}
					
					if (movimiento.getNOMADC().equals(comunidad.getsNOMADC()))
					{
						movimiento_revisado.setBITC05("#");
					}
					else
					{
						movimiento_revisado.setBITC05("S");
						movimiento_revisado.setNOMADC(movimiento.getNOMADC());
						bCambio = true;
					}
					
					if (movimiento.getNUTADC().equals(comunidad.getsNUTADC()))
					{
						movimiento_revisado.setBITC06("#");
					}
					else
					{
						movimiento_revisado.setBITC06("S");
						movimiento_revisado.setNUTADC(movimiento.getNUTADC());
						bCambio = true;
					}
					
					if (movimiento.getNODCAD().equals(comunidad.getsNODCAD()))
					{
						movimiento_revisado.setBITC07("#");
					}
					else
					{
						movimiento_revisado.setBITC07("S");
						movimiento_revisado.setNODCAD(movimiento.getNODCAD());
						bCambio = true;
					}
					
					Cuenta cuenta = CLCuentas.buscarCuenta(Long.parseLong(comunidad.getsCuenta()));
					
					if (movimiento.getNUCCEN().equals(cuenta.getsNUCCEN()) 
						&& movimiento.getNUCCOF().equals(cuenta.getsNUCCOF()) 
						&& movimiento.getNUCCDI().equals(cuenta.getsNUCCDI()) 
						&& movimiento.getNUCCNT().equals(cuenta.getsNUCCNT()))
					{
						movimiento_revisado.setBITC08("#");

					}
					else
					{
						movimiento_revisado.setBITC08("S");
						movimiento_revisado.setNUCCEN(movimiento.getNUCCEN());
						movimiento_revisado.setNUCCOF(movimiento.getNUCCOF());
						movimiento_revisado.setNUCCDI(movimiento.getNUCCDI());
						movimiento_revisado.setNUCCNT(movimiento.getNUCCNT());
						bCambio = true;
					}
					
					if (movimiento.getOBTEXC().equals(comunidad.getsOBTEXC()))
					{
						movimiento_revisado.setBITC09("#");
					}
					else if (movimiento.getOBTEXC().isEmpty() && !comunidad.getsOBTEXC().isEmpty())
					{
						movimiento_revisado.setBITC09("B");
						movimiento_revisado.setOBTEXC("");
						bCambio = true;
					}
					else if (!movimiento.getOBTEXC().isEmpty() &&  comunidad.getsOBTEXC().isEmpty())
					{
						movimiento_revisado.setBITC09("A");
						movimiento_revisado.setOBTEXC(movimiento.getOBTEXC());
						bCambio = true;
					}
					else 
					{
						movimiento_revisado.setBITC09("M");
						movimiento_revisado.setOBTEXC(movimiento.getOBTEXC());
						bCambio = true;
					}

					movimiento_revisado.setCOACES("0");
					movimiento_revisado.setBITC10("#");
					
					if (!bCambio)
					{
						movimiento_revisado.setCOACCI("#");
					}

				}
				else //X, E o B
				{
					
					movimiento_revisado.setBITC01("#");
					movimiento_revisado.setBITC02("#");
					movimiento_revisado.setBITC03("#");
					movimiento_revisado.setBITC04("#");
					movimiento_revisado.setBITC05("#");
					movimiento_revisado.setBITC06("#");
					movimiento_revisado.setBITC07("#");
					movimiento_revisado.setBITC08("#");
					movimiento_revisado.setBITC09("#");

					if (movimiento.getCOACCI().equals("X") || movimiento.getCOACCI().equals("E"))
					{
						movimiento_revisado.setBITC10("S");
						movimiento_revisado.setCOACES(movimiento.getCOACES());
					}
					else
					{
						movimiento_revisado.setBITC10("#");
					}
				}	
		}

		logger.debug("Revisado! Nuevo movimiento:");
		logger.debug(movimiento_revisado.logMovimientoComunidad());
		
		return movimiento_revisado;

	}
	
	public static int validaMovimiento(MovimientoComunidad movimiento, long liCodComunidad)
	{
		int iCodigo = 0;

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			logger.debug("Comprobando estado...");
			String sEstado = QMComunidades.getEstado(conexion,liCodComunidad);
			
			logger.debug("Estado:|{}|",sEstado);
			logger.debug("Accion:|{}|",movimiento.getCOACCI());
			
			int iCOACES = 0;
			
			if (!movimiento.getCOACES().isEmpty())
			{
				iCOACES = Integer.parseInt(movimiento.getCOACES());
			}
		
			if (movimiento.getCOACCI().isEmpty())
			{
				//Error 001 - CODIGO DE ACCION DEBE SER A,M,B,X o E
				iCodigo = -1;
			}
			else if (movimiento.getCOCLDO().isEmpty())
			{
				//Error 030 - LA CLASE DE DOCUMENTO DEBE SER UN CIF (2,5,J)
				iCodigo = -30;
			}
			else if (movimiento.getNUDCOM().isEmpty())
			{
				//Error 004 - CIF DE LA COMUNIDAD NO PUEDE SER BLANCO, NULO O CEROS
				iCodigo = -4;
			}
			else if (!Utils.compruebaCIF(movimiento.getNUDCOM()))
			{
				//Error 031 - NUMERO DE DOCUMENTO CIF ERRONEO
				iCodigo = -31;
			}
			else if (movimiento.getCOACCI().equals("A") && movimiento.getNOMCOC().isEmpty())
			{
				//Error 005 - NO TIENE NOMBRE LA COMUNIDAD
				iCodigo = -5;
			}
			else if (!movimiento.getNODCCO().isEmpty() && !Utils.compruebaCorreo(movimiento.getNODCCO()))
			{
				//Error direccion de correo de comunidad incorrecta
				iCodigo = -702;
			}
			else if (!movimiento.getNODCAD().isEmpty() && !Utils.compruebaCorreo(movimiento.getNODCAD()))
			{
				//Error direccion de correo del administrador incorrecta
				iCodigo = -703;
			}
			else if (movimiento.getCOACCI().equals("A") && (movimiento.getNUCCEN().isEmpty()
					|| movimiento.getNUCCOF().isEmpty()
					|| movimiento.getNUCCDI().isEmpty()
					|| movimiento.getNUCCNT().isEmpty()))
			{
				//Error 006 - FALTAN DATOS DE LA CUENTA BANCARIA
				iCodigo = -6;
			}
			else if ((movimiento.getCOACCI().equals("A")||movimiento.getCOACCI().equals("M")) && !Utils.compruebaCC(movimiento.getNUCCEN(),movimiento.getNUCCOF(),movimiento.getNUCCDI(),movimiento.getNUCCNT()))
			{
				//Error datos de cuenta incorrectos
				iCodigo = -701;
			}
			
			else if (movimiento.getCOACCI().equals("A") && movimiento.getCOACES().isEmpty())
			{
				//Error 022 - NO SE PUEDE DAR ALTA SI CONTROL DE ACTIVO NO ES S
				iCodigo = -22;
			}		
			else if ( !movimiento.getCOACES().isEmpty() && !CLActivos.existeActivo(iCOACES))
			{
				//Error 003 - NO EXISTE EL ACTIVO
				iCodigo = -3;
			}
			else if ((movimiento.getCOACCI().equals("A") || movimiento.getCOACCI().equals("X")) && CLComunidades.esActivoVinculadoAComunidad(iCOACES))
			{
				//Error 008 - EL ACTIVO EXISTE EN OTRA COMUNIDAD
				iCodigo = -8;
			}	
			else if ( movimiento.getCOACCI().equals("A") && QMComunidades.existeComunidad(conexion,liCodComunidad))
			{
				//Error 009 - YA EXISTE ESTA COMUNIDAD
				iCodigo = -9;
			}		
			else if (sEstado.equals("A") && movimiento.getCOACCI().equals("X") && comprobarRelacion(movimiento.getCOCLDO(), movimiento.getNUDCOM(), iCOACES))
			{
				//Error 010 - EL ACTIVO YA EXISTE PARA ESTA COMUNIDAD
				iCodigo = -10;
			}
			else if (movimiento.getCOACCI().equals("X") && !QMComunidades.existeComunidad(conexion,liCodComunidad))
			{
				//Error 011 - LA COMUNIDAD NO EXISTE. ACTIVO NO SE PUEDE DAR DE ALTA
				iCodigo = -11;
			}
			else if (movimiento.getCOACCI().equals("E") && CLCuotas.tieneCuotasActivo(iCOACES))
			{
				//Error 034 - NO SE PUEDE DAR DE BAJA EL ACTIVO PORQUE TIENE CUOTAS
				iCodigo = -34;			
			}			
			else if (movimiento.getCOACCI().equals("M") && !QMComunidades.existeComunidad(conexion,liCodComunidad))
			{
				//Error 012 - LA COMUNIDAD NO EXISTE. NO SE PUEDE MODIFICAR
				iCodigo = -12;
			}
			else if (movimiento.getCOACCI().equals("B") && !QMComunidades.existeComunidad(conexion,liCodComunidad))
			{
				//Error 026 - LA COMUNIDAD NO EXISTE, NO SE PUEDE DAR DE BAJA
				iCodigo = -26;
			}
			else if (movimiento.getCOACCI().equals("B") && CLCuotas.tieneCuotasComunidad(movimiento.getCOCLDO(), movimiento.getNUDCOM()))
			{
				//Error 027 - NO SE PUEDE DAR DE BAJA LA COMUNIDAD PORQUE TIENE CUOTAS
				iCodigo = -27;			
			}
		
			else if (sEstado.equals("A") && movimiento.getCOACCI().equals("A"))
			{
				//Error alta de una comunidad en alta
				iCodigo = -801;
			}
			else if (sEstado.equals("B"))
			{
				//Error comunidad de baja no puede recibir mas movimientos
				iCodigo = -802;
			}
			else if (sEstado.isEmpty() && !movimiento.getCOACCI().equals("A"))
			{
				//Error estado no disponible
				iCodigo = -803;
			}
		}

		logger.debug("Comprobado!");
		
		return iCodigo;

	}
}
