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
import com.provisiones.types.movimientos.MovimientoComunidad;
import com.provisiones.types.tablas.ActivoTabla;

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
	public static MovimientoComunidad convierteComunidadenMovimiento(Comunidad comunidad, String sCodCOACES, String sCodCOACCI)
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
				sCodCOACES,
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
	public static ArrayList<ActivoTabla> buscarActivosComunidad (String sCodCOCLDO, String sCodNUDCOM)
	{
		return QMListaComunidadesActivos.buscaActivosComunidad(ConnectionManager.getDBConnection(),buscarCodigoComunidad (sCodCOCLDO, sCodNUDCOM));
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
	
	public static Comunidad buscarComunidad (String sCodCOACES)
	{
		return QMListaComunidadesActivos.buscaComunidadPorActivo(ConnectionManager.getDBConnection(),sCodCOACES);
	}
	
	public static MovimientoComunidad buscarMovimientoComunidad (String sCodMovimiento)
	{
		return QMMovimientosComunidades.getMovimientoComunidad(ConnectionManager.getDBConnection(),sCodMovimiento);
	}
	
	public static long buscarNumeroMovimientosComunidadesPendientes()
	{
		return (QMListaComunidadesActivos.buscaCantidadValidado(ConnectionManager.getDBConnection(),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE) 
				+ QMListaComunidades.buscaCantidadValidado(ConnectionManager.getDBConnection(),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE));
	}
	
	public static int comprobarActivo (String sCOCLDO, String sNUDCOM, String sCOACES)
	{
		int iCodigo = 0;
		
		if (CLActivos.existeActivo(sCOACES))
		{
			if (CLComunidades.comprobarRelacion(sCOCLDO, sNUDCOM, sCOACES))
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
	
	public static boolean comprobarRelacion (String sCodCOCLDO, String sCodNUDCOM, String sCodCOACES)
	{
		return QMListaComunidadesActivos.compruebaRelacionComunidadActivo(ConnectionManager.getDBConnection(),buscarCodigoComunidad (sCodCOCLDO, sCodNUDCOM), sCodCOACES);
	}
	
	public static Comunidad consultarComunidad (String sCOCLDO, String sNUDCOM)
	{
		return QMComunidades.getComunidad(ConnectionManager.getDBConnection(),buscarCodigoComunidad(sCOCLDO,sNUDCOM));
	}
	
	public static boolean esActivoVinculadoAComunidad (String sCOACES)
	{
		return QMListaComunidadesActivos.activoVinculadoComunidad(ConnectionManager.getDBConnection(),sCOACES);
	}
	
	public static boolean existeComunidad (String sCodCOCLDO, String sCodNUDCOM)
	{
		return QMComunidades.existeComunidad(ConnectionManager.getDBConnection(),buscarCodigoComunidad (sCodCOCLDO, sCodNUDCOM));
	}
	
	public static boolean existeMovimientoComunidad (String sCodMovimiento)
	{
		return QMMovimientosComunidades.existeMovimientoComunidad(ConnectionManager.getDBConnection(),sCodMovimiento);
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

			if (CLActivos.existeActivo(comunidad.getCOACES()))
			{
				logger.debug(comunidad.logMovimientoComunidad());
				
				
				String sCodMovimiento = QMMovimientosComunidades.getMovimientoComunidadID(conexion,comunidad);
				
				logger.debug("sCodMovimiento|"+sCodMovimiento+"|");
				
				if (!(sCodMovimiento.equals("")))
				{
					logger.debug("comunidad.getCOACCI()|{}|",comunidad.getCOACCI());

					ValoresDefecto.TIPOSACCIONES COACCI = ValoresDefecto.TIPOSACCIONES.valueOf(comunidad.getCOACCI());
					
					String sEstado = "";
					
					switch (COACCI)
					{
					case X:case E:
						sEstado = QMListaComunidadesActivos.getValidado(conexion,sCodMovimiento);
						break;

					case M: case B: case A:
						sEstado = QMListaComunidades.getValidado(conexion,sCodMovimiento);
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
							sValidado = "V";
						}
						else
						{
							sValidado = "X";
						}
						
						logger.debug("sValidado|{}|",sValidado);

						switch (COACCI)
						{
						case X:case E:
							if (QMListaComunidadesActivos.existeRelacionComunidad(conexion,comunidad.getCOACES(), liCodComunidad, sCodMovimiento))
							{
								if (QMListaComunidadesActivos.setValidado(conexion,sCodMovimiento, sValidado))
								{
									if (sValidado.equals("X"))
									{
										//recibido un error
										if (QMListaErroresComunidades.addErrorComunidad(conexion,sCodMovimiento, comunidad.getCOTDOR()))
										{
											iCodigo = 1;
										}
										else
										{
											QMListaComunidadesActivos.setValidado(conexion,sCodMovimiento, "E");
											iCodigo = -6;
										}
									}
									else
									{
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
							if (QMListaComunidades.existeRelacionComunidad(conexion,liCodComunidad, sCodMovimiento))
							{
								if (QMListaComunidadesActivos.existeRelacionComunidad(conexion, comunidad.getCOACES(), liCodComunidad, sCodMovimiento))
								{
									if (QMListaComunidades.setValidado(conexion,sCodMovimiento, sValidado))
									{
										if (QMListaComunidadesActivos.setValidado(conexion,sCodMovimiento, sValidado))
										{
											if (sValidado.equals("X"))
											{
												//recibido error
												if (QMListaErroresComunidades.addErrorComunidad(conexion,sCodMovimiento, comunidad.getCOTDOR()))
												{
													iCodigo = 1;
												}
												else
												{
													QMListaComunidadesActivos.setValidado(conexion,sCodMovimiento, "E");
													QMListaComunidades.setValidado(conexion,sCodMovimiento, "E");
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
											QMListaComunidades.setValidado(conexion,sCodMovimiento, "E");
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
							if (QMListaComunidades.existeRelacionComunidad(conexion,liCodComunidad, sCodMovimiento))
							{
								if(QMListaComunidades.setValidado(conexion,sCodMovimiento, sValidado))
								{
									if (sValidado.equals("X"))
									{
										//recibido error
										if (QMListaErroresComunidades.addErrorComunidad(conexion,sCodMovimiento, comunidad.getCOTDOR()))
										{
											iCodigo = 1;
										}
										else
										{
											QMListaComunidades.setValidado(conexion,sCodMovimiento, "E");
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
						
						//bSalida = QMMovimientosComunidades.modMovimientoComunidad(comunidad, sCodMovimiento);
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
	
	public static int comprobarActivo (String sCOACES)
	{
		int iCodigo = -3;
		
		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			if (CLActivos.existeActivo(sCOACES))
			{
				if (CLComunidades.esActivoVinculadoAComunidad(sCOACES))
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
	
	public static int registraMovimiento(MovimientoComunidad movimiento)
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
				
				MovimientoComunidad movimiento_revisado = revisaCodigosControl(movimiento,liCodComunidad);
				
				if (movimiento_revisado.getCOACCI().equals("#"))
				{	
					//Error modificacion sin cambios
					iCodigo = -804;	
				}
				else
				{
					try 
					{
						conexion.setAutoCommit(false);
						
						int indice = QMMovimientosComunidades.addMovimientoComunidad(conexion,movimiento_revisado);
						
						if (indice == 0)
						{
							//Error al crear un movimiento
							iCodigo = -900;
							conexion.rollback();
						}
						else
						{
							ValoresDefecto.TIPOSACCIONES COACCI = ValoresDefecto.TIPOSACCIONES.valueOf(movimiento.getCOACCI());
							
							switch (COACCI) 
							{
								case A:
									
									logger.debug("Dando de Alta...");
									
									long liCodCuenta = QMCuentas.addCuenta(conexion, new Cuenta("ES","#",movimiento.getNUCCEN(), movimiento.getNUCCOF(), movimiento.getNUCCDI(), movimiento.getNUCCNT(),"COMUNIDAD"),ValoresDefecto.CUENTA_COMUNIDAD);
									
									if (liCodCuenta != 0)
									{
										Comunidad comunidad = convierteMovimientoenComunidad(movimiento_revisado);
										
										liCodComunidad = QMComunidades.addComunidad(conexion,comunidad);
										
										if (liCodComunidad != 0)
										{
										
											//relacion cuenta-Comunidad
											if (QMListaCuentasComunidades.addRelacionComunidad(conexion, liCodCuenta, liCodComunidad))
											{
												if (QMListaComunidades.addRelacionComunidad(conexion,liCodComunidad, Integer.toString(indice)))
												{	
																				

													logger.debug("COACES:|{}|",movimiento_revisado.getCOACES());
													/*if (movimiento_revisado.getCOACES().equals("0") || movimiento_revisado.getCOACES().equals(""))
													{
														//OK 
														iCodigo = 0;
													}
													else*/ 
													if (QMListaComunidadesActivos.addRelacionComunidad(conexion,movimiento_revisado.getCOACES(), liCodComunidad, Integer.toString(indice)))
													{
														//OK 
														iCodigo = 0;
														
														conexion.commit();
													}
													else
													{
														//Error y Rollback - error al registrar el activo durante el alta
														iCodigo = -903;
														//QMMovimientosComunidades.delMovimientoComunidad(conexion,Integer.toString(indice));
														//QMComunidades.delComunidad(conexion,liCodComunidad);
														//QMListaComunidades.delRelacionComunidad(conexion,Integer.toString(indice));
														conexion.rollback();
													}
												}
												else
												{
													//Error y Rollback - error al registrar la relaccion
														iCodigo = -902;
														//QMMovimientosComunidades.delMovimientoComunidad(conexion,Integer.toString(indice));
														//QMComunidades.delComunidad(conexion,liCodComunidad);
														
														conexion.rollback();
												}
											}
											else
											{
												//Error y Rollback - error al registrar la relacion cuenta-comunidad
												iCodigo = -912;
												
												conexion.rollback();
											}
											
											
										}
										else
										{
											//Error y Rollback - error al registrar la comuidad
												iCodigo = -901;
												//QMMovimientosComunidades.delMovimientoComunidad(conexion,Integer.toString(indice));
												
												conexion.rollback();
										}
									}
									else
									{
										//Error y Rollback - error al registrar la cuenta de la comunidad
											iCodigo = -911;
											
											conexion.rollback();
									}								

									break;
								case B:
									if (QMListaComunidades.addRelacionComunidad(conexion,liCodComunidad, Integer.toString(indice)))
									{
										
										logger.debug("Dando de Baja...");
										if (QMComunidades.setEstado(conexion,liCodComunidad, "B"))
										{
											//OK 
											iCodigo = 0; 
											conexion.commit();
										}
										else
										{
											//error y rollback - error al cambiar el estado
											iCodigo = -904;
											//QMListaComunidades.delRelacionComunidad(conexion,Integer.toString(indice));
											//QMMovimientosComunidades.delMovimientoComunidad(conexion,Integer.toString(indice));
											
											conexion.rollback();
										}
									}
									else
									{
										//error y rollback - error al registrar la relaccion
										iCodigo = -902;
										//QMMovimientosComunidades.delMovimientoComunidad(conexion,Integer.toString(indice));
										
										conexion.rollback();
									}
									break;
								case M:
									if (QMListaComunidades.addRelacionComunidad(conexion,liCodComunidad, Integer.toString(indice)))
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
												//OK 
												iCodigo = 0;
												conexion.commit();
											}
											else
											{
												//error y rollback - error al modificar la comunidad
												iCodigo = -905;
												//QMListaComunidades.delRelacionComunidad(conexion,Integer.toString(indice));
												//QMMovimientosComunidades.delMovimientoComunidad(conexion,Integer.toString(indice));
												conexion.rollback();
											}
											
										}
										else
										{
											if (liCuentaNueva == 0)
											{
												liCuentaNueva = QMCuentas.addCuenta(conexion, new Cuenta("ES","#",movimiento.getNUCCEN(), movimiento.getNUCCOF(), movimiento.getNUCCDI(), movimiento.getNUCCNT(),"COMUNIDAD"), ValoresDefecto.CUENTA_COMUNIDAD);
												
												if (liCuentaNueva != 0)
												{
													if (QMListaCuentasComunidades.addRelacionComunidad(conexion, liCuentaNueva, liCodComunidad))
													{
														if (QMComunidades.modComunidad(conexion,convierteMovimientoenComunidad(movimiento), liCodComunidad))
														{
															if (QMCuentas.delCuenta(conexion, liCodCuentaAntigua))
															{
																//OK 
																iCodigo = 0;
																conexion.commit();
															}
															else
															{
																//Error y Rollback - error al eliminar la cuenta antigua de la comunidad
																iCodigo = -913;
																
																conexion.rollback();
															}

														}
														else
														{
															//error y rollback - error al modificar la comunidad
															iCodigo = -905;
															//QMListaComunidades.delRelacionComunidad(conexion,Integer.toString(indice));
															//QMMovimientosComunidades.delMovimientoComunidad(conexion,Integer.toString(indice));
															conexion.rollback();
														}
													}
													else
													{
														//Error y Rollback - error al registrar la relacion cuenta-comunidad
														iCodigo = -912;
														
														conexion.rollback();
													}
												}
												else
												{
													//Error y Rollback - error al registrar la cuenta de la comunidad
														iCodigo = -911;
														
														conexion.rollback();
												}
											}
											else
											{
												//Error y Rollback - error al eliminar la cuenta antigua de la comunidad
												iCodigo = -914;
												
												conexion.rollback();
											}	
										}

									}
									else
									{
										//error y rollback - error al registrar la relaccion
										iCodigo = -902;
										//QMMovimientosComunidades.delMovimientoComunidad(conexion,Integer.toString(indice));
										conexion.rollback();
									}
									break;
								case X:
										String sMovimientoAlta = QMListaComunidadesActivos.getMovimientoDeActivoVinculadoComunidad(conexion,liCodComunidad, movimiento_revisado.getCOACES());
										if (!sMovimientoAlta.equals(""))
										{
											//error y rollback - el activo ya esta vinculado
											iCodigo = -906;
											//QMMovimientosComunidades.delMovimientoComunidad(conexion,Integer.toString(indice));
											conexion.rollback();
										}
										else
										{							
											if (QMListaComunidadesActivos.addRelacionComunidad(conexion,movimiento_revisado.getCOACES(), liCodComunidad, Integer.toString(indice)))
											{
												//OK 
												iCodigo = 0;
												conexion.commit();
											}
											else
											{
												//error y rollback - error al asociar el activo en la comunidad
												iCodigo = -907;
												//QMMovimientosComunidades.delMovimientoComunidad(conexion,Integer.toString(indice));
												conexion.rollback();
											}
										}
									break;
								case E:
										String sMovimientoBaja = QMListaComunidadesActivos.getMovimientoDeActivoVinculadoComunidad(conexion,liCodComunidad, movimiento_revisado.getCOACES());
										
										if (sMovimientoBaja.equals(""))
										{
											//error y rollback - el activo no esta vinculado
											iCodigo = -908;
											//QMMovimientosComunidades.delMovimientoComunidad(conexion,Integer.toString(indice));
											conexion.rollback();
										}
										else
										{	
											if (QMListaComunidadesActivos.delRelacionComunidad(conexion,sMovimientoBaja))
											{
												//OK 
												iCodigo = 0;
												conexion.commit();
											}
											else
											{
												//error y rollback - error al desasociar el activo en la comunidad
												iCodigo = -909;
												//QMMovimientosComunidades.delMovimientoComunidad(conexion,Integer.toString(indice));
												//QMListaComunidadesActivos.addRelacionComunidad(conexion,movimiento_revisado.getCOACES(), liCodComunidad, sMovimientoBaja);
												
												conexion.rollback();
											}
										}
									break;
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
					
					if (movimiento.getNOMCOC().equals(""))
					{
						movimiento_revisado.setBITC01("#");
					}
					else
					{
						movimiento_revisado.setBITC01("S");
						movimiento_revisado.setNOMCOC(movimiento.getNOMCOC());
					}

					if (movimiento.getNODCCO().equals(""))
					{
						movimiento_revisado.setBITC02("#");
					}
					else
					{
						movimiento_revisado.setBITC02("S");
						movimiento_revisado.setNODCCO(movimiento.getNODCCO());
					}

					if (movimiento.getNOMPRC().equals(""))
					{
						movimiento_revisado.setBITC03("#");
					}
					else
					{
						movimiento_revisado.setBITC03("S");
						movimiento_revisado.setNOMPRC(movimiento.getNOMPRC());
					}
					
					if (movimiento.getNUTPRC().equals(""))
					{
						movimiento_revisado.setBITC04("#");
					}
					else
					{
						movimiento_revisado.setBITC04("S");
						movimiento_revisado.setNUTPRC(movimiento.getNUTPRC());
					}
					
					if (movimiento.getNOMADC().equals(""))
					{
						movimiento_revisado.setBITC05("#");
					}
					else
					{
						movimiento_revisado.setBITC05("S");
						movimiento_revisado.setNOMADC(movimiento.getNOMADC());
					}
					
					if (movimiento.getNUTADC().equals(""))
					{
						movimiento_revisado.setBITC06("#");
					}
					else
					{
						movimiento_revisado.setBITC06("S");
						movimiento_revisado.setNUTADC(movimiento.getNUTADC());
					}
					
					if (movimiento.getNODCAD().equals(""))
					{
						movimiento_revisado.setBITC07("#");
					}
					else
					{
						movimiento_revisado.setBITC07("S");
						movimiento_revisado.setNODCAD(movimiento.getNODCAD());
					}
					
					if (movimiento.getNUCCEN().equals("") && movimiento.getNUCCOF().equals("") && movimiento.getNUCCDI().equals("") && movimiento.getNUCCNT().equals(""))
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
					
					if (movimiento.getOBTEXC().equals(""))
					{
						movimiento_revisado.setBITC09("#");
					}
					else
					{
						movimiento_revisado.setBITC09("A");
						movimiento_revisado.setOBTEXC(movimiento.getOBTEXC());
					}
					
					if (movimiento.getCOACES().equals(""))
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
					else if (movimiento.getOBTEXC().equals("") && !comunidad.getsOBTEXC().equals(""))
					{
						movimiento_revisado.setBITC09("B");
						movimiento_revisado.setOBTEXC("");
						bCambio = true;
					}
					else if (!movimiento.getOBTEXC().equals("") &&  comunidad.getsOBTEXC().equals(""))
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
		
			if (movimiento.getCOACCI().equals(""))
			{
				//Error 001 - CODIGO DE ACCION DEBE SER A,M,B,X o E
				iCodigo = -1;
			}
			else if (movimiento.getCOCLDO().equals(""))
			{
				//Error 030 - LA CLASE DE DOCUMENTO DEBE SER UN CIF (2,5,J)
				iCodigo = -30;
			}
			else if (movimiento.getNUDCOM().equals(""))
			{
				//Error 004 - CIF DE LA COMUNIDAD NO PUEDE SER BLANCO, NULO O CEROS
				iCodigo = -4;
			}
			else if (!Utils.compruebaCIF(movimiento.getNUDCOM()))
			{
				//Error 031 - NUMERO DE DOCUMENTO CIF ERRONEO
				iCodigo = -31;
			}
			else if (movimiento.getCOACCI().equals("A") && movimiento.getNOMCOC().equals(""))
			{
				//Error 005 - NO TIENE NOMBRE LA COMUNIDAD
				iCodigo = -5;
			}
			else if (!movimiento.getNODCCO().equals("") && !Utils.compruebaCorreo(movimiento.getNODCCO()))
			{
				//Error direccion de correo de comunidad incorrecta
				iCodigo = -702;
			}
			else if (!movimiento.getNODCAD().equals("") && !Utils.compruebaCorreo(movimiento.getNODCAD()))
			{
				//Error direccion de correo del administrador incorrecta
				iCodigo = -703;
			}
			else if (movimiento.getCOACCI().equals("A") && (movimiento.getNUCCEN().equals("")
					|| movimiento.getNUCCOF().equals("")
					|| movimiento.getNUCCDI().equals("")
					|| movimiento.getNUCCNT().equals("")))
			{
				//Error 006 - FALTAN DATOS DE LA CUENTA BANCARIA
				iCodigo = -6;
			}
			else if ((movimiento.getCOACCI().equals("A")||movimiento.getCOACCI().equals("M")) && !Utils.compruebaCC(movimiento.getNUCCEN(),movimiento.getNUCCOF(),movimiento.getNUCCDI(),movimiento.getNUCCNT()))
			{
				//Error datos de cuenta incorrectos
				iCodigo = -701;
			}
			
			else if (movimiento.getCOACCI().equals("A") && movimiento.getCOACES().equals(""))
			{
				//Error 022 - NO SE PUEDE DAR ALTA SI CONTROL DE ACTIVO NO ES S
				iCodigo = -22;
			}		
			else if ( !movimiento.getCOACES().equals("") && !CLActivos.existeActivo(movimiento.getCOACES()))
			{
				//Error 003 - NO EXISTE EL ACTIVO
				iCodigo = -3;
			}
			else if ((movimiento.getCOACCI().equals("A") || movimiento.getCOACCI().equals("X")) && CLComunidades.esActivoVinculadoAComunidad(movimiento.getCOACES()))
			{
				//Error 008 - EL ACTIVO EXISTE EN OTRA COMUNIDAD
				iCodigo = -8;
			}	
			else if ( movimiento.getCOACCI().equals("A") && QMComunidades.existeComunidad(conexion,liCodComunidad))
			{
				//Error 009 - YA EXISTE ESTA COMUNIDAD
				iCodigo = -9;
			}		
			else if (sEstado.equals("A") && movimiento.getCOACCI().equals("X") && comprobarRelacion(movimiento.getCOCLDO(), movimiento.getNUDCOM(), movimiento.getCOACES()))
			{
				//Error 010 - EL ACTIVO YA EXISTE PARA ESTA COMUNIDAD
				iCodigo = -10;
			}
			else if (movimiento.getCOACCI().equals("X") && !QMComunidades.existeComunidad(conexion,liCodComunidad))
			{
				//Error 011 - LA COMUNIDAD NO EXISTE. ACTIVO NO SE PUEDE DAR DE ALTA
				iCodigo = -11;
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
			else if (movimiento.getCOACCI().equals("B") && CLCuotas.tieneCuotas(movimiento.getCOACES(),movimiento.getCOCLDO(), movimiento.getNUDCOM()))
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
			else if (sEstado.equals("") && !movimiento.getCOACCI().equals("A"))
			{
				//Error estado no disponible
				iCodigo = -803;
			}
		}
		
		return iCodigo;

	}
}
