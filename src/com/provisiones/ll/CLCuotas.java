package com.provisiones.ll;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMCuotas;
import com.provisiones.dal.qm.listas.QMListaCuotas;
import com.provisiones.dal.qm.listas.errores.QMListaErroresCuotas;
import com.provisiones.dal.qm.movimientos.QMMovimientosCuotas;

import com.provisiones.misc.Parser;
import com.provisiones.misc.ValoresDefecto;

import com.provisiones.types.Cuota;
import com.provisiones.types.Nota;
import com.provisiones.types.movimientos.MovimientoCuota;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.CuotaTabla;

public final class CLCuotas 
{
	private static Logger logger = LoggerFactory.getLogger(CLCuotas.class.getName());

	private CLCuotas(){}
	
	//ID
	public static long buscarCodigoCuota (int iCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC)
	{
		return QMCuotas.getCuotaID(ConnectionManager.getDBConnection(),iCOACES, sCodCOCLDO, sCodNUDCOM, sCodCOSBAC);
	}

	//Conversion
	public static MovimientoCuota convierteCuotaenMovimiento(Cuota cuota, int iCOACES, String sCodCOACCI)
	{
		logger.debug("Convirtiendo...");
		
		return new MovimientoCuota(
				ValoresDefecto.DEF_E2_CODTRN,
				ValoresDefecto.DEF_COTDOR,
				ValoresDefecto.DEF_IDPROV,
				sCodCOACCI,
				cuota.getCOCLDO(),
				cuota.getNUDCOM(),
				ValoresDefecto.DEF_COENGP,
				Integer.toString(iCOACES),
				ValoresDefecto.DEF_COGRUG_E2,
				ValoresDefecto.DEF_COTACA_E2,
				cuota.getCOSBAC(),
				"",
				cuota.getFIPAGO(),
				"",
				cuota.getFFPAGO(),
				"",
				cuota.getIMCUCO(),
				"",
				cuota.getFAACTA(),
				"",
				cuota.getPTPAGO(),
				"",
				cuota.getOBTEXC(),
				"");
		
	}

	public static Cuota convierteMovimientoenCuota(MovimientoCuota movimiento)
	{
		logger.debug("Convirtiendo...");
		
		return new Cuota(
				movimiento.getCOACES(),
				movimiento.getCOCLDO(),
				movimiento.getNUDCOM(),
				movimiento.getCOSBAC(),
				movimiento.getFIPAGO(),
				movimiento.getFFPAGO(),
				movimiento.getIMCUCO(),
				movimiento.getFAACTA(),
				movimiento.getPTPAGO(),
				movimiento.getOBTEXC());
	}
	
	//Interfaz básico
	public static ArrayList<ActivoTabla> buscarActivosConCuotas (ActivoTabla activo)
	{
		return QMListaCuotas.buscaActivosConCuotas(ConnectionManager.getDBConnection(),activo);
	}

	public static ArrayList<CuotaTabla> buscarCuotasActivo (int iCOACES)
	{
		return QMCuotas.buscaCuotasActivo(ConnectionManager.getDBConnection(),iCOACES);
	}
	
	public static ArrayList<CuotaTabla> buscarCuotasComunidad (String sCodCOCLDO, String sCodNUDCOM)
	{
		return QMCuotas.buscaCuotasComunidad(ConnectionManager.getDBConnection(),sCodCOCLDO,sCodNUDCOM);
	}
	
	public static MovimientoCuota buscarMovimientoCuota (long liCodMovimiento)
	{
		return QMMovimientosCuotas.getMovimientoCuota(ConnectionManager.getDBConnection(),liCodMovimiento);
	}
	
	public static String buscarNota (long liCodCuota)
	{
		return QMCuotas.getNota(ConnectionManager.getDBConnection(),liCodCuota);
	}
	
	public static Cuota buscarCuota (long liCodCuota)
	{
		return QMCuotas.getCuota(ConnectionManager.getDBConnection(),liCodCuota);
	}
	
	public static Cuota buscarDetallesCuota (long liCodCuota)
	{
		return QMCuotas.getDetallesCuota(ConnectionManager.getDBConnection(),liCodCuota);
	}
	
	public static boolean guardarNota (long liCodCuota, String sNota)
	{
		return QMCuotas.setNota(ConnectionManager.getDBConnection(),liCodCuota, sNota);
	}
	
	public static long buscarNumeroMovimientosCuotasPendientes()
	{
		return (QMListaCuotas.buscaCantidadValidado(ConnectionManager.getDBConnection(),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE));
	}

	public static boolean estaDeBaja (int iCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC)
	{
		return QMCuotas.getEstado(ConnectionManager.getDBConnection(),buscarCodigoCuota(iCOACES, sCodCOCLDO, sCodNUDCOM, sCodCOSBAC)).equals(ValoresDefecto.DEF_BAJA);
	}
	
	public static boolean existeCuota(int iCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC)
	{
		return QMCuotas.existeCuota(ConnectionManager.getDBConnection(),buscarCodigoCuota(iCOACES, sCodCOCLDO, sCodNUDCOM, sCodCOSBAC));
	}

	public static boolean existeMovimientoCuota (long liCodMovimiento)
	{
		return QMMovimientosCuotas.existeMovimientoCuota(ConnectionManager.getDBConnection(),liCodMovimiento);
	}
	
	public static boolean tieneCuotasActivo (int iCOACES)
	{
		return QMCuotas.tieneCuotasActivo(ConnectionManager.getDBConnection(),iCOACES);
	}

	public static boolean tieneCuotasComunidad (String sCodCOCLDO, String sCodNUDCOM)
	{
		return QMCuotas.tieneCuotasComunidad(ConnectionManager.getDBConnection(),sCodCOCLDO, sCodNUDCOM);
	}
	
	public static int actualizarCuotaLeida(String linea)
	{
		int iCodigo = 0;

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			MovimientoCuota cuota = Parser.leerCuota(linea);

			if (CLActivos.existeActivo(Integer.parseInt(cuota.getCOACES())))
			{
				logger.debug(cuota.logMovimientoCuota());
				
				long liCodMovimiento = QMMovimientosCuotas.getMovimientoCuotaID(conexion,cuota);
				
				logger.debug("liCodMovimiento|"+liCodMovimiento+"|");
				
				if (liCodMovimiento != 0)
				{

					
					String sEstado = QMListaCuotas.getValidado(conexion,liCodMovimiento);
					
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

						logger.debug("cuota.getCOTDOR()|"+cuota.getCOTDOR()+"|");
						logger.debug("ValoresDefecto.DEF_COTDOR|"+ValoresDefecto.DEF_COTDOR+"|");

						if (cuota.getCOTDOR().equals(ValoresDefecto.DEF_COTDOR))
						{
							sValidado = "V";
						}
						else
						{
							sValidado = "X";
						}
						
						logger.debug("sValidado|"+sValidado+"|");
						
						logger.debug("cuota.getCOACCI()|"+cuota.getCOACCI()+"|");

						ValoresDefecto.TIPOSACCIONES COACCI = ValoresDefecto.TIPOSACCIONES.valueOf(cuota.getCOACCI());

						switch (COACCI)
						{
						case A: case M: case B:
							if (QMListaCuotas.existeRelacionCuota(conexion,buscarCodigoCuota(Integer.parseInt(cuota.getCOACES()),cuota.getCOCLDO(),cuota.getNUDCOM(),cuota.getCOSBAC()), liCodMovimiento))
							{
								if(QMListaCuotas.setValidado(conexion,liCodMovimiento, sValidado))
								{
									if (sValidado.equals("X"))
									{
										//recibido error
										if (QMListaErroresCuotas.addErrorCuota(conexion,liCodMovimiento, cuota.getCOTDOR()))
										{
											iCodigo = 1;
										}
										else
										{
											QMListaCuotas.setValidado(conexion,liCodMovimiento, "E");
											iCodigo = -4;
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
									iCodigo = -3;
								}
							}
							else
							{
								iCodigo = -2;
							}
							break;
							
						default:
							logger.error("Se ha recibido un movimiento con acción desconocida:|"+cuota.getCOACCI()+"|.");
							iCodigo = -9;
							break;
						
						}
						
						//bSalida = QMMovimientosCuotas.modMovimientoCuota(cuota, liCodMovimiento);
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
			else
			{
				//activo desconocido
				iCodigo = -8;
			}

		}
		
		logger.error("iCodigo:|"+iCodigo+"|");
		
		return iCodigo;
		
	}
	
	public static int registraMovimiento(MovimientoCuota movimiento, Nota nota)
	{
		int iCodigo = -910;//Error de conexion

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			
			long liCodCuota = buscarCodigoCuota(Integer.parseInt(movimiento.getCOACES()), movimiento.getCOCLDO(),movimiento.getNUDCOM(), movimiento.getCOSBAC());
			
			iCodigo = validaMovimiento(movimiento,liCodCuota);

			if (iCodigo == 0)
			{
				MovimientoCuota movimiento_revisado = revisaCodigosControl(movimiento,liCodCuota);
				
				if (movimiento_revisado.getCOACCI().equals("#"))
				{	
					if (nota.isbInvalida())
					{
						//Error modificacion sin cambios
						iCodigo = -804;
					}
					else
					{
						if (QMCuotas.setNota(conexion, liCodCuota, nota.getsContenido()))
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

						long indice = QMMovimientosCuotas.addMovimientoCuota(conexion,movimiento_revisado);
						
						if (indice == 0)
						{
							//error al crear un movimiento
							iCodigo = -900;
						}
						else
						{

							ValoresDefecto.TIPOSACCIONES COACCI = ValoresDefecto.TIPOSACCIONES.valueOf(movimiento.getCOACCI());
						
							switch (COACCI)
							{
								case A:
									Cuota cuotadealta = convierteMovimientoenCuota(movimiento_revisado);

									logger.debug("Dando de alta la cuota...");

									logger.debug(cuotadealta.logCuota());
									
									if (estaDeBaja(Integer.parseInt(movimiento_revisado.getCOACES()), movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), movimiento_revisado.getCOSBAC()))
									{
										if (QMListaCuotas.addRelacionCuotas(conexion,Integer.parseInt(movimiento_revisado.getCOACES()), liCodCuota, indice))
										{
											//OK 
											if (QMCuotas.setEstado(conexion,liCodCuota, ValoresDefecto.DEF_ALTA))
											{
												//Se cambian los valores de la antigua cuota
												if(QMCuotas.modCuota(conexion,convierteMovimientoenCuota(movimiento), liCodCuota))
												{
													
													if (nota.isbInvalida())
													{
														//OK 
														iCodigo = 0;
													}
													else
													{
														if (QMCuotas.setNota(conexion, liCodCuota, nota.getsContenido()))
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
													//Error cuota no modificada
													iCodigo = -904;	
												} 
											}
											else
											{
												//error estado no establecido - Rollback
												//QMMovimientosCuotas.delMovimientoCuota(conexion,Integer.toString(indice));
												//QMListaCuotas.delRelacionCuotas(conexion,Integer.toString(indice));
												iCodigo = -903;
											}
										}
										else
										{
											//error relacion cuota no creada - Rollback
											iCodigo = -902;
										}
									}
									else
									{
										liCodCuota = QMCuotas.addCuota(conexion,cuotadealta);
										if (liCodCuota != 0)
										{
											//OK - Cuota creada
											logger.debug("Hecho!");
											if (QMListaCuotas.addRelacionCuotas(conexion,Integer.parseInt(movimiento_revisado.getCOACES()), liCodCuota, indice))
											{
												if (nota.isbInvalida())
												{
													//OK 
													iCodigo = 0;
												}
												else
												{
													if (QMCuotas.setNota(conexion, liCodCuota, nota.getsContenido()))
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
												//error relacion cuota no creada - Rollback
												iCodigo = -902;
											}
										}
										else
										{
											//error cuota no creada - Rollback
											iCodigo = -901;
										}
									}

									break;
								case B:
									if (QMListaCuotas.addRelacionCuotas(conexion,Integer.parseInt(movimiento_revisado.getCOACES()), liCodCuota, indice))
									{
										if (QMCuotas.setEstado(conexion,liCodCuota, ValoresDefecto.DEF_BAJA))
										{
											if (nota.isbInvalida())
											{
												//OK 
												iCodigo = 0;
											}
											else
											{
												if (QMCuotas.setNota(conexion, liCodCuota, nota.getsContenido()))
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
											//error estado no establecido - Rollback
											iCodigo = -903;
										}
									}
									else
									{
										//error relacion cuota no creada - Rollback
										iCodigo = -902;
									}
									break;
								case M:
									if (QMListaCuotas.addRelacionCuotas(conexion,Integer.parseInt(movimiento_revisado.getCOACES()), liCodCuota, indice))
									{
										//Cuota cuotamodificada = QMCuotas.getCuota( movimiento_revisado.getCOCLDO(), movimiento_revisado.getNUDCOM(), movimiento_revisado.getCOSBAC());
										if(QMCuotas.modCuota(conexion,convierteMovimientoenCuota(movimiento), liCodCuota))
										{
											if (nota.isbInvalida())
											{
												//OK 
												iCodigo = 0;
											}
											else
											{
												if (QMCuotas.setNota(conexion, liCodCuota, nota.getsContenido()))
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
											//Error cuota no modificada
											iCodigo = -904;
										}

									}
									else
									{
										//error relacion cuota no creada - Rollback
										iCodigo = -902;
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
	
	public static MovimientoCuota revisaCodigosControl(MovimientoCuota movimiento, long liCodCuota)
	{
		MovimientoCuota movimiento_revisado = new MovimientoCuota("", "", "", "", "", "", "", "0", "", "", "", "", "0", "", "0", "", "0", "", "0", "", "0", "", "", "");

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			Cuota cuota = QMCuotas.getCuota(conexion,liCodCuota);
			
			logger.debug(cuota.logCuota());
			
			logger.debug(movimiento.logMovimientoCuota());
			
			
			logger.debug("Revisando Acción:|"+movimiento.getCOACCI()+"|");
			
			movimiento_revisado.setCODTRN(movimiento.getCODTRN());
			movimiento_revisado.setCOTDOR(movimiento.getCOTDOR());
			movimiento_revisado.setIDPROV(movimiento.getIDPROV());
			movimiento_revisado.setCOACCI(movimiento.getCOACCI());
			movimiento_revisado.setCOCLDO(movimiento.getCOCLDO());
			movimiento_revisado.setNUDCOM(movimiento.getNUDCOM());
			movimiento_revisado.setCOENGP(movimiento.getCOENGP());
			movimiento_revisado.setCOACES(movimiento.getCOACES());
			
			movimiento_revisado.setCOGRUG(movimiento.getCOGRUG());
			movimiento_revisado.setCOTACA(movimiento.getCOTACA());
			movimiento_revisado.setCOSBAC(movimiento.getCOSBAC());
			
			
			movimiento_revisado.setOBDEER(movimiento.getOBDEER());
			
					
			
				if (movimiento.getCOACCI().equals(ValoresDefecto.DEF_ALTA))
				{
					
					if (movimiento.getFIPAGO().equals("0"))
					{
						movimiento_revisado.setBITC11("#");
					}
					else
					{
						movimiento_revisado.setBITC11("S");
						movimiento_revisado.setFIPAGO(movimiento.getFIPAGO());
					}

					if (movimiento.getFFPAGO().equals("0"))
					{
						movimiento_revisado.setBITC12("#");
					}
					else
					{
						movimiento_revisado.setBITC12("S");
						movimiento_revisado.setFFPAGO(movimiento.getFFPAGO());
					}

					if (movimiento.getIMCUCO().equals("0"))
					{
						movimiento_revisado.setBITC13("#");
					}
					else
					{
						movimiento_revisado.setBITC13("S");
						movimiento_revisado.setIMCUCO(movimiento.getIMCUCO());
					}
					
					if (movimiento.getFAACTA().equals("0"))
					{
						movimiento_revisado.setBITC14("#");
					}
					else
					{
						movimiento_revisado.setBITC14("S");
						movimiento_revisado.setFAACTA(movimiento.getFAACTA());
					}
					
					if (movimiento.getPTPAGO().equals(""))
					{
						movimiento_revisado.setBITC15("#");
					}
					else
					{
						movimiento_revisado.setBITC15("S");
						movimiento_revisado.setPTPAGO(movimiento.getPTPAGO());
					}
					
					
					if (movimiento.getOBTEXC().equals(""))
					{
						movimiento_revisado.setBITC09("#");
					}
					else
					{
						movimiento_revisado.setBITC09(ValoresDefecto.DEF_ALTA);
						movimiento_revisado.setOBTEXC(movimiento.getOBTEXC());
					}
					

				}
				else if (movimiento.getCOACCI().equals("M"))
				{
					boolean bCambio = false;
					
					if (movimiento.getFIPAGO().equals(cuota.getFIPAGO()))
					{
						movimiento_revisado.setBITC11("#");
					}
					else
					{
						movimiento_revisado.setBITC11("S");
						movimiento_revisado.setFIPAGO(movimiento.getFIPAGO());
						bCambio = true;
					}

					if (movimiento.getFFPAGO().equals(cuota.getFFPAGO()))
					{
						movimiento_revisado.setBITC12("#");
					}
					else
					{
						movimiento_revisado.setBITC12("S");
						movimiento_revisado.setFFPAGO(movimiento.getFFPAGO());
						bCambio = true;
					}

					if (movimiento.getIMCUCO().equals(cuota.getIMCUCO()))
					{
						movimiento_revisado.setBITC13("#");
					}
					else
					{
						movimiento_revisado.setBITC13("S");
						movimiento_revisado.setIMCUCO(movimiento.getIMCUCO());
						bCambio = true;
					}
					
					if (movimiento.getFAACTA().equals(cuota.getFAACTA()))
					{
						movimiento_revisado.setBITC14("#");
					}
					else
					{
						movimiento_revisado.setBITC14("S");
						movimiento_revisado.setFAACTA(movimiento.getFAACTA());
						bCambio = true;
					}
					
					if (movimiento.getPTPAGO().equals(cuota.getPTPAGO()))
					{
						movimiento_revisado.setBITC15("#");
					}
					else
					{
						movimiento_revisado.setBITC15("S");
						movimiento_revisado.setPTPAGO(movimiento.getPTPAGO());
						bCambio = true;
					}
					
					if (movimiento.getOBTEXC().equals(cuota.getOBTEXC()))
					{
						movimiento_revisado.setBITC09("#");
					}
					else if (movimiento.getOBTEXC().equals("") && !cuota.getOBTEXC().equals(""))
					{
						movimiento_revisado.setBITC09(ValoresDefecto.DEF_BAJA);
						movimiento_revisado.setOBTEXC("");
						bCambio = true;
					}
					else if (!movimiento.getOBTEXC().equals("") &&  cuota.getOBTEXC().equals(""))
					{
						movimiento_revisado.setBITC09(ValoresDefecto.DEF_ALTA);
						movimiento_revisado.setOBTEXC(movimiento.getOBTEXC());
						bCambio = true;
					}
					else 
					{
						movimiento_revisado.setBITC09("M");
						movimiento_revisado.setOBTEXC(movimiento.getOBTEXC());
						bCambio = true;
					}
					
					if (!bCambio)
					{
						movimiento_revisado.setCOACCI("#");
					}

				}
				else if (movimiento.getCOACCI().equals(ValoresDefecto.DEF_BAJA))
				{
					movimiento_revisado.setBITC11("#");
					movimiento_revisado.setBITC12("#");
					movimiento_revisado.setBITC13("#");
					movimiento_revisado.setBITC14("#");
					movimiento_revisado.setBITC15("#");
					movimiento_revisado.setBITC09("#");
				}
				else
				{
					movimiento_revisado.setCOACCI("");
				}
		}		

		logger.debug("Revisado! Nuevo movimiento:");

		logger.debug(movimiento_revisado.logMovimientoCuota());
		
		return movimiento_revisado;

	}
	
	public static int validaMovimiento(MovimientoCuota movimiento, long liCodCuota)
	{
		int iCodigo = 0;

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			logger.debug("Comprobando estado...");
			
			String sEstado = QMCuotas.getEstado(conexion,liCodCuota);
			
			logger.debug("Estado:|"+sEstado+"|");
			logger.debug("Acción:|"+movimiento.getCOACCI()+"|");

			
			if (movimiento.getCOACCI().equals(""))
			{
				//Error 001 - CODIGO DE ACCION DEBE SER A,M o B
				iCodigo = -1;
			}
			else if (movimiento.getCOACES().equals("") || !CLActivos.existeActivo(Integer.parseInt(movimiento.getCOACES())))
			{
				//Error 003 - NO EXISTE EL ACTIVO
				iCodigo = -3;
			}		
			else if (movimiento.getNUDCOM().equals(""))
			{
				//Error 004 - CIF DE LA COMUNIDAD NO PUEDE SER BLANCO O NULO
				iCodigo = -4;
			}
			else if (!CLComunidades.existeComunidad(movimiento.getCOCLDO(), movimiento.getNUDCOM()))
			{
				//Error 041 - LA COMUNIDAD NO EXISTE EN LA TABLA DE COMUNIDADES GMAE10
				iCodigo = -41;
			}
			else if (movimiento.getCOSBAC().equals(""))
			{
				//Error 032 - EL SUBTIPO DE ACCION NO EXISTE
				iCodigo = -32;
			}
			else if (movimiento.getIMCUCO().equals("#"))
			{
				//Error 701 - importe incorrecto
				iCodigo = -701;
			}		
			else if (Double.parseDouble(movimiento.getIMCUCO()) <= 0)
			{
				//Error 036 - IMPORTE DE CUOTA TIENE QUE SER MAYOR DE CERO
				iCodigo = -36;
			}
			else if (movimiento.getFIPAGO().equals("#"))
			{
				//Error 702 - fecha de primer pago incorrecta
				iCodigo = -702;
			}
			else if (movimiento.getFIPAGO().equals("0"))
			{
				//Error 033 - LA FECHA DE PRIMER PAGO DEBE SER LOGICA Y OBLIGATORIA
				iCodigo = -33;
			}
			else if (movimiento.getFFPAGO().equals("#"))
			{
				//Error 703 - fecha de ultimo pago incorrecta
				iCodigo = -703;
			}
			else if (movimiento.getFFPAGO().equals("0"))
			{
				//Error 034 - LA FECHA DE ULTIMO PAGO DEBE SER LOGICA Y OBLIGATORIA
				iCodigo = -34;
			}
			else if (movimiento.getFAACTA().equals("#"))
			{
				//Error 704 - fecha de acta incorrecta
				iCodigo = -704;
			}
			else if (movimiento.getFAACTA().equals("0"))
			{
				//Error 046 - LA FECHA DEL ACTA DEBE SER LOGICA Y OBLIGATORIA 
				iCodigo = -46;
			}
			else if (Integer.parseInt(movimiento.getFFPAGO()) <  Integer.parseInt(movimiento.getFIPAGO()))
			{
				//Error 035 - LA FECHA DE ULTIMO PAGO NO DEBE DE SER MENOR QUE LA FECHA DE PRIMER PAGO
				iCodigo = -35;
			}
			else if (movimiento.getPTPAGO().equals(""))
			{
				//Error 044 - NO EXISTE PERIOCIDAD DE PAGO
				iCodigo = -44;
			}
			

			else if (movimiento.getCOACCI().equals(ValoresDefecto.DEF_ALTA) && 
					CLComunidades.esActivoVinculadoAComunidad(Integer.parseInt(movimiento.getCOACES())) && 
					!CLComunidades.comprobarRelacion(movimiento.getCOCLDO(), movimiento.getNUDCOM(),Integer.parseInt(movimiento.getCOACES())))
			{
				//Error 042 - LA RELACION ACTIVO-COMUNIDAD YA EXISTE EN GMAE12. NO SE PUEDE REALIZAR EL ALTA
				iCodigo = -42;
			}
			else if (movimiento.getCOACCI().equals("M") &&  
					!CLComunidades.comprobarRelacion(movimiento.getCOCLDO(), movimiento.getNUDCOM(),Integer.parseInt(movimiento.getCOACES())))
			{
				//Error 043 - LA RELACION ACTIVO-COMUNIDAD NO EXISTE EN GMAE12. NO SE PUEDE REALIZAR LA MODIFICACION
				iCodigo = -43;
			}
			else if (movimiento.getCOACCI().equals(ValoresDefecto.DEF_BAJA) &&  
					!CLComunidades.comprobarRelacion(movimiento.getCOCLDO(), movimiento.getNUDCOM(),Integer.parseInt(movimiento.getCOACES())))
			{
				//Error 045 - LA RELACION ACTIVO-COMUNIDAD NO EXISTE EN GMAE12. NO SE PUEDE REALIZAR LA BAJA
				iCodigo = -45;
			}		
			else if (sEstado.equals(ValoresDefecto.DEF_ALTA) && movimiento.getCOACCI().equals(ValoresDefecto.DEF_ALTA))
			{
				//Error alta de una comunidad en alta
				iCodigo = -801;
			}
			else if (sEstado.equals(ValoresDefecto.DEF_BAJA) && !movimiento.getCOACCI().equals(ValoresDefecto.DEF_ALTA))
			{
				//error referencia de baja, solo puede recibir altas
				iCodigo = -802;
			}
			else if (sEstado.equals("") && !movimiento.getCOACCI().equals(ValoresDefecto.DEF_ALTA))
			{
				//error estado no disponible
				iCodigo = -803;
			}
		}		
		
		return iCodigo;

	}
}
