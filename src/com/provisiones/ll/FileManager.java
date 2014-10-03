package com.provisiones.ll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMPagos;
import com.provisiones.dal.qm.QMProvisiones;

import com.provisiones.dal.qm.listas.QMListaComunidades;
import com.provisiones.dal.qm.listas.QMListaComunidadesActivos;
import com.provisiones.dal.qm.listas.QMListaCuotas;
import com.provisiones.dal.qm.listas.QMListaGastos;
import com.provisiones.dal.qm.listas.QMListaGastosProvisiones;
import com.provisiones.dal.qm.listas.QMListaImpuestos;
import com.provisiones.dal.qm.listas.QMListaReferencias;

import com.provisiones.dal.qm.movimientos.QMMovimientosCuotas;
import com.provisiones.dal.qm.movimientos.QMMovimientosGastos;
import com.provisiones.dal.qm.movimientos.QMMovimientosImpuestos;
import com.provisiones.dal.qm.movimientos.QMMovimientosReferencias;

import com.provisiones.misc.Longitudes;
import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

import com.provisiones.types.Cuenta;
import com.provisiones.types.ResultadoEnvio;
import com.provisiones.types.ResultadoCarga;
import com.provisiones.types.tablas.ResultadosTabla;
import com.provisiones.types.transferencias.N34.OrdenanteN34;
import com.provisiones.types.transferencias.N34.ResumenN34;
import com.provisiones.types.transferencias.N34.TransferenciaN34;
import com.provisiones.types.transferencias.N3414.CabeceraTransferenciasN3414;
import com.provisiones.types.transferencias.N3414.OrdenanteN3414;
import com.provisiones.types.transferencias.N3414.ResumenN3414;
import com.provisiones.types.transferencias.N3414.TotalesN3414;
import com.provisiones.types.transferencias.N3414.TransferenciaN3414;

public final class FileManager 
{
	private static Logger logger = LoggerFactory.getLogger(FileManager.class.getName());

	private FileManager(){}
	
	public static String guardarFichero(FileUploadEvent event,boolean bRecibido)
	{
		logger.debug("ID:|"+event.getPhaseId().getOrdinal()+"|");
	
		logger.debug("Guardando archivo...");
        UploadedFile file = event.getFile();
        
        String sPATH = "";
        
        if (bRecibido)
        {
        	sPATH = ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS;
        }
        else
        {
        	sPATH = ValoresDefecto.DEF_PATH_BACKUP_CARGADOS;
        }
        
        String sFichero = Utils.timeStamp()+"_"+file.getFileName();

        InputStream is;
		try 
		{
			is = file.getInputstream();
	        OutputStream out = new FileOutputStream(sPATH+sFichero);
	        
	        byte buf[] = new byte[1024];
	        int len;
	        
	        while ((len = is.read(buf)) > 0)
	        {
	            out.write(buf, 0, len);
	        }
	        
	        is.close();
	        out.close();

		} 
		catch (IOException e) 
		{
			logger.error("Error al guardar el fichero recibido.");
		}
        logger.debug("Completado con exito.");
        return sFichero;
	}

	public static ResultadoEnvio escribirComunidades() 
	{
		String sNombreFichero = "";
		long liEntradas = 0;

		Connection conexion = ConnectionManager.getDBConnection();

		if (conexion != null)
		{
			//Los movimientos de las comunidades estan repartidos entre las comunidades y los activos incluidos
			
			ArrayList<Long> resultcomunidades = QMListaComunidades.getMovimientosComunidadesPorEstado(conexion,ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
			ArrayList<Long> resultactivos = QMListaComunidadesActivos.getMovimientosComunidadesActivoPorEstado(conexion,ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);

			ArrayList<Long> resultcomunidadesactivos = new ArrayList<Long>(resultcomunidades);
			
			resultcomunidadesactivos.addAll(resultactivos);
			
			HashSet<Long> hslimpia = new HashSet<Long>(resultcomunidadesactivos);

			resultcomunidadesactivos.clear();
			resultcomunidadesactivos.addAll(hslimpia);
			
			Collections.sort(resultcomunidadesactivos);
			
			if (resultcomunidadesactivos.size() > 0)
			{
				sNombreFichero = ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+Utils.timeStamp()+"_"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E1+".txt";
				
				FileWriter ficheroE1 = null;
				
		        PrintWriter pw = null;
		        
		        boolean bOK = false;
		        try
		        {
		        	conexion.setAutoCommit(false);

		            for (int i = 0; i < resultcomunidades.size() ; i++)
		            {
		            	bOK = QMListaComunidades.setValidado(conexion,resultcomunidades.get(i),ValoresDefecto.DEF_MOVIMIENTO_ENVIADO); 
		               if (!bOK)
		               {
		            	   i = resultcomunidades.size();
		               }
		            }
		            
		            if (bOK)
		            {
			            for (int i = 0; i < resultactivos.size() ; i++)
			            {
			            	bOK = QMListaComunidadesActivos.setValidado(conexion,resultactivos.get(i),ValoresDefecto.DEF_MOVIMIENTO_ENVIADO);
				            if (!bOK)
				            {
				            	i = resultcomunidades.size();
				            }
			            }
		            }

		            if (bOK)
		            {
			        	ficheroE1 = new FileWriter(sNombreFichero);
			        	
			            pw = new PrintWriter(ficheroE1);

			            for (int i = 0; i < resultcomunidadesactivos.size() ; i++)
			            {
			                pw.println(Parser.escribirComunidad(CLComunidades.buscarMovimientoComunidad(resultcomunidadesactivos.get(i))));
			            }
			            
			            pw.print(ValoresDefecto.DEF_FIN_FICHERO);
			            liEntradas = resultcomunidadesactivos.size();
		            }
		            
		        } 
		        catch (IOException eEescribirFichero) 
		        {
		            sNombreFichero = "";
		            liEntradas = 0;
		            try
		            {
		            	conexion.rollback();
		            	conexion.setAutoCommit(true);
		            }
		            catch (SQLException eDeshacerCambios)
			        {
						try 
						{
							//reintentamos
							conexion.rollback();
							conexion.setAutoCommit(true);
							conexion.close();
						} 
						catch (SQLException eReintentar) 
						{
							try 
							{
								conexion.close();
							}
							catch (SQLException eCerrarConexion) 
							{
								logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
							}
						}
					}

		            
		            logger.error("Ocurrió un error al escribir en el fichero de envio, se restauran los estados afectados.");
		            
		        }
		        catch (SQLException eGuardarCambios)
		        {
		            sNombreFichero = "";
		            liEntradas = 0;
					try 
					{
						//reintentamos
						conexion.rollback();
						conexion.setAutoCommit(true);
						conexion.close();
					} 
					catch (SQLException eReintentar) 
					{
						try 
						{
							conexion.close();
						}
						catch (SQLException eCerrarConexion) 
						{
							logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
						}
					}
				} 
		        finally 
		        {
		        	try 
			        {
			        	if (null != ficheroE1)
			        	{
			        		if (bOK)
			        		{
				        		conexion.commit();

							    logger.info("Generado:|"+sNombreFichero+"|"); 
			        		}
			        		else
			        		{
					        	sNombreFichero = "";
					        	liEntradas = 0;

								conexion.rollback();
								logger.error("Error al revisar los movimientos.");
			        		}
			        		ficheroE1.close();

			        	}
			        	else
			        	{
			        		sNombreFichero = "";
					        liEntradas = 0;

							conexion.rollback();
								
							logger.error("Error de descriptor de fichero.");
			        	}
		        		conexion.setAutoCommit(true);

			        }
			        catch (IOException eCerarFichero) 
			        {
			        	sNombreFichero = "";
			        	liEntradas = 0;
			              
						try 
						{
							conexion.rollback();
							conexion.setAutoCommit(true);
						}
						catch (SQLException eDeshacerCambios) 
						{
							try 
							{
								//reintentamos
								conexion.rollback();
								conexion.setAutoCommit(true);
								conexion.close();
							} 
							catch (SQLException eReintentar) 
							{
								try 
								{
									conexion.close();
								}
								catch (SQLException eCerrarConexion) 
								{
									logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
								}
							}
						}
						
			            //TODO conexion.rollback();+ conexion.setAutoCommit(true);
			            logger.error("Ocurrió un error al cerrar el fichero de envio, se restauran los estados afectados.");
			        }
		        	catch (SQLException eDeshacerCambios)
			        {
			        	sNombreFichero = "";
			        	liEntradas = 0;

						try 
						{
							//reintentamos
							conexion.rollback();
							conexion.setAutoCommit(true);
							conexion.close();
						} 
						catch (SQLException eReintentar) 
						{
							try 
							{
								conexion.close();
							}
							catch (SQLException eCerrarConexion) 
							{
								logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
							}
						}
					}


		        }
			}


		}

        return new ResultadoEnvio(sNombreFichero,liEntradas);
	}
	
	public static ResultadoEnvio escribirCuotas() 
	{
		String sNombreFichero = "";
		long liEntradas = 0;

		Connection conexion = ConnectionManager.getDBConnection();

		if (conexion != null)
		{
			ArrayList<Long> resultcuotas =  QMListaCuotas.getMovimientosCuotasPorEstado(conexion,ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);

			if (resultcuotas.size() > 0)
			{
				FileWriter ficheroE2 = null;
				
		        PrintWriter pw = null;
		        
		        sNombreFichero = ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+Utils.timeStamp()+"_"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E2+".txt";
		        
		        boolean bOK = false;
		        try
		        {
		            ficheroE2 = new FileWriter(sNombreFichero);
		            pw = new PrintWriter(ficheroE2);
		            
		        	conexion.setAutoCommit(false);

		            for (int i = 0; i < resultcuotas.size() ; i++)
		            {
		            	bOK = QMListaCuotas.setValidado(conexion,resultcuotas.get(i),ValoresDefecto.DEF_MOVIMIENTO_ENVIADO);
		            	if (bOK)
		            	{
		            		pw.println(Parser.escribirCuota(QMMovimientosCuotas.getMovimientoCuota(conexion,resultcuotas.get(i))));
		            	}
		            	else
		            	{
		            		i = resultcuotas.size();
		            	}
		            }
		            if (bOK)
		            {
		            	pw.print(ValoresDefecto.DEF_FIN_FICHERO);
		            	liEntradas = resultcuotas.size();
		            }
		            
		            
		        } 
		        catch (IOException e) 
		        {
		        	sNombreFichero = "";
		        	liEntradas = 0;
		            
		        	//En caso de error se devuelven los registros a su estado anterior
		            try
		            {
		            	conexion.rollback();
		            	conexion.setAutoCommit(true);
		            }
		            catch (SQLException eDeshacerCambios)
			        {
						try 
						{
							//reintentamos
							conexion.rollback();
							conexion.setAutoCommit(true);
							conexion.close();
						} 
						catch (SQLException eReintentar) 
						{
							try 
							{
								conexion.close();
							}
							catch (SQLException eCerrarConexion) 
							{
								logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
							}
						}
					}
		            
		            logger.error("Ocurrió un error al escribir en el fichero de envio, se restauran los estados afectados.");
		        }
		        catch (SQLException eGuardarCambios)
		        {
		            sNombreFichero = "";
		            liEntradas = 0;
					try 
					{
						//reintentamos
						conexion.rollback();
						conexion.setAutoCommit(true);
						conexion.close();
					} 
					catch (SQLException eReintentar) 
					{
						try 
						{
							conexion.close();
						}
						catch (SQLException eCerrarConexion) 
						{
							logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
						}
					}
				} 
		        finally 
		        {
		        	try 
			        {
			        	if (null != ficheroE2)
			        	{
			        		if (bOK)
			        		{
				        		conexion.commit();

							    logger.info("Generado:|"+sNombreFichero+"|"); 
			        		}
			        		else
			        		{
					        	sNombreFichero = "";
					        	liEntradas = 0;

								conexion.rollback();
								logger.error("Error al revisar los movimientos.");
			        		}
			        		ficheroE2.close();

			        	}
			        	else
			        	{
			        		sNombreFichero = "";
					        liEntradas = 0;

							conexion.rollback();
								
							logger.error("Error de descriptor de fichero.");
			        	}
		        		conexion.setAutoCommit(true);

			        }
			        catch (IOException eCerarFichero) 
			        {
			        	sNombreFichero = "";
			        	liEntradas = 0;
			              
						try 
						{
							conexion.rollback();
							conexion.setAutoCommit(true);
						}
						catch (SQLException eDeshacerCambios) 
						{
							try 
							{
								//reintentamos
								conexion.rollback();
								conexion.setAutoCommit(true);
								conexion.close();
							} 
							catch (SQLException eReintentar) 
							{
								try 
								{
									conexion.close();
								}
								catch (SQLException eCerrarConexion) 
								{
									logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
								}
							}
						}
						
			            //TODO conexion.rollback();+ conexion.setAutoCommit(true);
			            logger.error("Ocurrió un error al cerrar el fichero de envio, se restauran los estados afectados.");
			        }
		        	catch (SQLException eDeshacerCambios)
			        {
			        	sNombreFichero = "";
			        	liEntradas = 0;

						try 
						{
							//reintentamos
							conexion.rollback();
							conexion.setAutoCommit(true);
							conexion.close();
						} 
						catch (SQLException eReintentar) 
						{
							try 
							{
								conexion.close();
							}
							catch (SQLException eCerrarConexion) 
							{
								logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
							}
						}
					}
		        }
			}
			
		}
 
        return new ResultadoEnvio(sNombreFichero,liEntradas);
	}
	
	public static ResultadoEnvio escribirReferencias() 
	{
		String sNombreFichero = "";
		long liEntradas = 0;

		Connection conexion = ConnectionManager.getDBConnection();

		if (conexion != null)
		{
			ArrayList<Long> resultreferencias = QMListaReferencias.getMovimientosReferenciasPorEstado(conexion,ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
			
			if (resultreferencias.size() > 0)
			{
				FileWriter ficheroE3 = null;
				
		        PrintWriter pw = null;
		        
		        sNombreFichero = ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+Utils.timeStamp()+"_"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E3+".txt";
		        
		        boolean bOK = false;
		        try
		        {
		            ficheroE3 = new FileWriter(sNombreFichero);
		            pw = new PrintWriter(ficheroE3);
		            
		        	conexion.setAutoCommit(false);

		            for (int i = 0; i < resultreferencias.size() ; i++)
		            {
		            	bOK = QMListaReferencias.setValidado(conexion,resultreferencias.get(i),ValoresDefecto.DEF_MOVIMIENTO_ENVIADO);
		            	if (bOK)
		            	{
		            		pw.println(Parser.escribirReferenciaCatastral(QMMovimientosReferencias.getMovimientoReferenciaCatastral(conexion,resultreferencias.get(i))));
		            	}
		            	else
		            	{
		            		i = resultreferencias.size();
		            	}
		            }

		            if (bOK)
	            	{
	    	            pw.print(ValoresDefecto.DEF_FIN_FICHERO);
	    	            liEntradas = resultreferencias.size();
	            	}
		        } 
		        catch (IOException e) 
		        {
		        	sNombreFichero = "";
		        	liEntradas = 0;
		            
		        	//En caso de error se devuelven los registros a su estado anterior
		            try
		            {
		            	conexion.rollback();
		            	conexion.setAutoCommit(true);
		            }
		            catch (SQLException eDeshacerCambios)
			        {
						try 
						{
							//reintentamos
							conexion.rollback();
							conexion.setAutoCommit(true);
							conexion.close();
						} 
						catch (SQLException eReintentar) 
						{
							try 
							{
								conexion.close();
							}
							catch (SQLException eCerrarConexion) 
							{
								logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
							}
						}
					}
		            
		            logger.error("Ocurrió un error al escribir en el fichero de envio, se restauran los estados afectados.");
		        } 
		        catch (SQLException eGuardarCambios)
		        {
		            sNombreFichero = "";
		            liEntradas = 0;
					try 
					{
						//reintentamos
						conexion.rollback();
						conexion.setAutoCommit(true);
						conexion.close();
					} 
					catch (SQLException eReintentar) 
					{
						try 
						{
							conexion.close();
						}
						catch (SQLException eCerrarConexion) 
						{
							logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
						}
					}
				} 
		        finally 
		        {
		        	try 
			        {
			        	if (null != ficheroE3)
			        	{
			        		if (bOK)
			        		{
				        		conexion.commit();

							    logger.info("Generado:|"+sNombreFichero+"|"); 
			        		}
			        		else
			        		{
					        	sNombreFichero = "";
					        	liEntradas = 0;

								conexion.rollback();
								logger.error("Error al revisar los movimientos.");
			        		}
			        		ficheroE3.close();

			        	}
			        	else
			        	{
			        		sNombreFichero = "";
					        liEntradas = 0;

							conexion.rollback();
								
							logger.error("Error de descriptor de fichero.");
			        	}
		        		conexion.setAutoCommit(true);
			        }
			        catch (IOException eCerarFichero) 
			        {
			        	sNombreFichero = "";
			        	liEntradas = 0;
			              
						try 
						{
							conexion.rollback();
							conexion.setAutoCommit(true);
						}
						catch (SQLException eDeshacerCambios) 
						{
							try 
							{
								//reintentamos
								conexion.rollback();
								conexion.setAutoCommit(true);
								conexion.close();
							} 
							catch (SQLException eReintentar) 
							{
								try 
								{
									conexion.close();
								}
								catch (SQLException eCerrarConexion) 
								{
									logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
								}
							}
						}
						
			            logger.error("Ocurrió un error al cerrar el fichero de envio, se restauran los estados afectados.");
			        }
		        	catch (SQLException eDeshacerCambios)
			        {
			        	sNombreFichero = "";
			        	liEntradas = 0;

			        	try 
						{
							//reintentamos
							conexion.rollback();
							conexion.setAutoCommit(true);
							conexion.close();
						} 
						catch (SQLException eReintentar) 
						{
							try 
							{
								conexion.close();
							}
							catch (SQLException eCerrarConexion) 
							{
								logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
							}
						}
					}
		        }
			}

		}
        
        return new ResultadoEnvio(sNombreFichero,liEntradas);
	}
	
	public static ResultadoEnvio escribirImpuestos() 
	{
		String sNombreFichero = "";
		long liEntradas = 0;

		Connection conexion = ConnectionManager.getDBConnection();

		if (conexion != null)
		{
			ArrayList<Long> resultimpuestos = QMListaImpuestos.getMovimientosImpuestosPorEstado(conexion,ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
			
			if (resultimpuestos.size() > 0)
			{
				FileWriter ficheroE4 = null;
				
		        PrintWriter pw = null;
		        
		        sNombreFichero = ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+Utils.timeStamp()+"_"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E4+".txt";
		        
		        boolean bOK = false;
		        try
		        {
		            ficheroE4 = new FileWriter(sNombreFichero);
		            pw = new PrintWriter(ficheroE4);
		            
		            conexion.setAutoCommit(false);

		            for (int i = 0; i < resultimpuestos.size() ; i++)
		            {
		            	bOK = QMListaImpuestos.setValidado(conexion,resultimpuestos.get(i),ValoresDefecto.DEF_MOVIMIENTO_ENVIADO);
		            	if (bOK)
		            	{
		            		pw.println(Parser.escribirImpuestoRecurso(QMMovimientosImpuestos.getMovimientoImpuestoRecurso(conexion,resultimpuestos.get(i))));	
		            	}
		            	else
		            	{
		            		i = resultimpuestos.size();
		            	}
		            }
		            if (bOK)
		            {
		            	pw.print(ValoresDefecto.DEF_FIN_FICHERO);
		            	liEntradas = resultimpuestos.size();
		            }
		            
		        } 
		        catch (IOException e) 
		        {
		        	sNombreFichero = "";
		        	liEntradas = 0;
		            
		        	//En caso de error se devuelven los registros a su estado anterior
		            try
		            {
		            	conexion.rollback();
		            	conexion.setAutoCommit(true);
		            }
		            catch (SQLException eDeshacerCambios)
			        {
						try 
						{
							//reintentamos
							conexion.rollback();
							conexion.setAutoCommit(true);
							conexion.close();
						} 
						catch (SQLException eReintentar) 
						{
							try 
							{
								conexion.close();
							}
							catch (SQLException eCerrarConexion) 
							{
								logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
							}
						}
					}
		            
		            logger.error("Ocurrió un error al escribir en el fichero de envio, se restauran los estados afectados.");
		        } 
		        catch (SQLException eGuardarCambios)
		        {
		            sNombreFichero = "";
		            liEntradas = 0;
					try 
					{
						//reintentamos
						conexion.rollback();
						conexion.setAutoCommit(true);
						conexion.close();
					} 
					catch (SQLException eReintentar) 
					{
						try 
						{
							conexion.close();
						}
						catch (SQLException eCerrarConexion) 
						{
							logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
						}
					}
				} 
		        finally 
		        {
		        	try 
			        {
			        	if (null != ficheroE4)
			        	{
			        		if (bOK)
			        		{
				        		conexion.commit();

							    logger.info("Generado:|"+sNombreFichero+"|"); 
			        		}
			        		else
			        		{
					        	sNombreFichero = "";
					        	liEntradas = 0;

								conexion.rollback();
								logger.error("Error al revisar los movimientos.");
			        		}
			        		ficheroE4.close();

			        	}
			        	else
			        	{
			        		sNombreFichero = "";
					        liEntradas = 0;

							conexion.rollback();
								
							logger.error("Error de descriptor de fichero.");
			        	}
		        		conexion.setAutoCommit(true);
			        }
			        catch (IOException eCerarFichero) 
			        {
			        	sNombreFichero = "";
			        	liEntradas = 0;
			              
						try 
						{
							conexion.rollback();
							conexion.setAutoCommit(true);
						}
						catch (SQLException eDeshacerCambios) 
						{
							try 
							{
								//reintentamos
								conexion.rollback();
								conexion.setAutoCommit(true);
								conexion.close();
							} 
							catch (SQLException eReintentar) 
							{
								try 
								{
									conexion.close();
								}
								catch (SQLException eCerrarConexion) 
								{
									logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
								}
							}
						}
						
			            logger.error("Ocurrió un error al cerrar el fichero de envio, se restauran los estados afectados.");
			        }
		        	catch (SQLException eDeshacerCambios)
			        {
			        	sNombreFichero = "";
			        	liEntradas = 0;

			        	try 
						{
							//reintentamos
							conexion.rollback();
							conexion.setAutoCommit(true);
							conexion.close();
						} 
						catch (SQLException eReintentar) 
						{
							try 
							{
								conexion.close();
							}
							catch (SQLException eCerrarConexion) 
							{
								logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
							}
						}
					}
		        }
			}
			
		}
        
        return new ResultadoEnvio(sNombreFichero,liEntradas);
	}
	
	public static ResultadoEnvio escribirGastos() 
	{
		String sNombreFichero = "";
		long liEntradas = 0;

		Connection conexion = ConnectionManager.getDBConnection();

		if (conexion != null)
		{
			ArrayList<Long> resultgastos = QMListaGastos.getMovimientosGastosPorEstado(conexion,ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
	        
			if (resultgastos.size() > 0)
			{
		        FileWriter ficheroGA = null;
		        
		        PrintWriter pw = null;
		        
		        sNombreFichero = ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+Utils.timeStamp()+"_"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_GA+".txt";
		        
		        boolean bOK = false;
		        try
		        {

		            ficheroGA = new FileWriter(sNombreFichero);
		            pw = new PrintWriter(ficheroGA);
		            
		        	conexion.setAutoCommit(false);
		            
		            for (int i = 0; i < resultgastos.size(); i++)
		            {
		            	bOK = (QMListaGastos.setValidado(conexion,resultgastos.get(i),ValoresDefecto.DEF_MOVIMIENTO_ENVIADO) &&
		            			QMListaGastosProvisiones.setRevisado(conexion, QMListaGastos.getCodGasto(conexion, resultgastos.get(i)), ValoresDefecto.DEF_MOVIMIENTO_ENVIADO));
		            	if (bOK)
		            	{
		            		pw.println(Parser.escribirGasto(QMMovimientosGastos.getMovimientoGasto(conexion,resultgastos.get(i))));
		            	}
		            	else
		            	{
		            		i = resultgastos.size();
		            	}
		            }
		            if (bOK)
		            {
		            	pw.print(ValoresDefecto.DEF_FIN_FICHERO);
		            	liEntradas = resultgastos.size();
		            }
		        } 
		        catch (IOException e) 
		        {
		        	sNombreFichero = "";
		        	liEntradas = 0;
		            
		        	//En caso de error se devuelven los registros a su estado anterior
		            try
		            {
		            	conexion.rollback();
		            	conexion.setAutoCommit(true);
		            }
		            catch (SQLException eDeshacerCambios)
			        {
						try 
						{
							//reintentamos
							conexion.rollback();
							conexion.setAutoCommit(true);
							conexion.close();
						} 
						catch (SQLException eReintentar) 
						{
							try 
							{
								conexion.close();
							}
							catch (SQLException eCerrarConexion) 
							{
								logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
							}
						}
					}
		            
		            logger.error("Ocurrió un error al escribir en el fichero de envio, se restauran los estados afectados.");
		        } 
		        catch (SQLException eGuardarCambios)
		        {
		            sNombreFichero = "";
		            liEntradas = 0;
					try 
					{
						//reintentamos
						conexion.rollback();
						conexion.setAutoCommit(true);
						conexion.close();
					} 
					catch (SQLException eReintentar) 
					{
						try 
						{
							conexion.close();
						}
						catch (SQLException eCerrarConexion) 
						{
							logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
						}
					}
				} 
		        finally 
		        {
		        	try 
			        {
			        	if (null != ficheroGA)
			        	{
			        		if (bOK)
			        		{
				        		conexion.commit();

							    logger.info("Generado:|"+sNombreFichero+"|"); 
			        		}
			        		else
			        		{
					        	sNombreFichero = "";
					        	liEntradas = 0;

								conexion.rollback();
								logger.error("Error al revisar los movimientos.");
			        		}
			        		ficheroGA.close();

			        	}
			        	else
			        	{
			        		sNombreFichero = "";
					        liEntradas = 0;

							conexion.rollback();
								
							logger.error("Error de descriptor de fichero.");
			        	}
		        		conexion.setAutoCommit(true);
			        }
			        catch (IOException eCerarFichero) 
			        {
			        	sNombreFichero = "";
			        	liEntradas = 0;
			              
						try 
						{
							conexion.rollback();
							conexion.setAutoCommit(true);
						}
						catch (SQLException eDeshacerCambios) 
						{
							try 
							{
								//reintentamos
								conexion.rollback();
								conexion.setAutoCommit(true);
								conexion.close();
							} 
							catch (SQLException eReintentar) 
							{
								try 
								{
									conexion.close();
								}
								catch (SQLException eCerrarConexion) 
								{
									logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
								}
							}
						}
						
			            logger.error("Ocurrió un error al cerrar el fichero de envio, se restauran los estados afectados.");
			        }
		        	catch (SQLException eDeshacerCambios)
			        {
			        	sNombreFichero = "";
			        	liEntradas = 0;

			        	try 
						{
							//reintentamos
							conexion.rollback();
							conexion.setAutoCommit(true);
							conexion.close();
						} 
						catch (SQLException eReintentar) 
						{
							try 
							{
								conexion.close();
							}
							catch (SQLException eCerrarConexion) 
							{
								logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
							}
						}
					}
		        }
			}

		}
        
        return new ResultadoEnvio(sNombreFichero,liEntradas);
	}

	public static ResultadoEnvio escribirCierres() 
	{
		String sNombreFichero = "";
		long liEntradas = 0;

		Connection conexion = ConnectionManager.getDBConnection();

		if (conexion != null)
		{
			ArrayList<String> resultcierres = CLProvisiones.buscarProvisionesCerradasPendientes();
	        
			if (resultcierres.size() > 0)
			{
		        FileWriter ficheroPP = null;
		        
		        PrintWriter pw = null;
		        
		        sNombreFichero = ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+Utils.timeStamp()+"_"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_PP+".txt";
		        
		        boolean bOK = false;
		        try
		        {

		        	ficheroPP = new FileWriter(sNombreFichero);
		            pw = new PrintWriter(ficheroPP);
		        	
		            conexion.setAutoCommit(false);
		            
		            for (int i = 0; i < resultcierres.size(); i++)
		            {
		            	bOK = (QMProvisiones.setFechaEnvio(conexion,resultcierres.get(i),Utils.fechaDeHoy(false)) && 
		            			QMProvisiones.setEstado(conexion, resultcierres.get(i), ValoresDefecto.DEF_PROVISION_ENVIADA));
		            	
		            	if (bOK)
		            	{
		            		pw.println(Parser.escribirCierre(resultcierres.get(i),QMProvisiones.getFechaCierre(conexion, resultcierres.get(i))));	
		            	}
		            	else
		            	{
		            		i = resultcierres.size();
		            	}   
		            }
	            	if (bOK)
	            	{
	            		pw.print(ValoresDefecto.DEF_FIN_FICHERO);
	            		liEntradas = resultcierres.size();
	            	}
		        } 
		        catch (IOException e) 
		        {
		        	sNombreFichero = "";
		        	liEntradas = 0;
		            
		        	//En caso de error se devuelven los registros a su estado anterior
		            try
		            {
		            	conexion.rollback();
		            	conexion.setAutoCommit(true);
		            }
		            catch (SQLException eDeshacerCambios)
			        {
						try 
						{
							//reintentamos
							conexion.rollback();
							conexion.setAutoCommit(true);
							conexion.close();
						} 
						catch (SQLException eReintentar) 
						{
							try 
							{
								conexion.close();
							}
							catch (SQLException eCerrarConexion) 
							{
								logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
							}
						}
					}
		            
		            logger.error("Ocurrió un error al escribir en el fichero de envio, se restauran los estados afectados.");
		        } 
		        catch (SQLException eGuardarCambios)
		        {
		            sNombreFichero = "";
		            liEntradas = 0;
					try 
					{
						//reintentamos
						conexion.rollback();
						conexion.setAutoCommit(true);
						conexion.close();
					} 
					catch (SQLException eReintentar) 
					{
						try 
						{
							conexion.close();
						}
						catch (SQLException eCerrarConexion) 
						{
							logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
						}
					}
				} 
		        finally 
		        {
		        	try 
			        {
			        	if (null != ficheroPP)
			        	{
			        		if (bOK)
			        		{
				        		conexion.commit();

							    logger.info("Generado:|"+sNombreFichero+"|"); 
			        		}
			        		else
			        		{
					        	sNombreFichero = "";
					        	liEntradas = 0;

								conexion.rollback();
								logger.error("Error al revisar los movimientos.");
			        		}
			        		ficheroPP.close();

			        	}
			        	else
			        	{
			        		sNombreFichero = "";
					        liEntradas = 0;

							conexion.rollback();
								
							logger.error("Error de descriptor de fichero.");
			        	}
		        		conexion.setAutoCommit(true);
			        }
			        catch (IOException eCerarFichero) 
			        {
			        	sNombreFichero = "";
			        	liEntradas = 0;
			              
						try 
						{
							conexion.rollback();
							conexion.setAutoCommit(true);
						}
						catch (SQLException eDeshacerCambios) 
						{
							try 
							{
								//reintentamos
								conexion.rollback();
								conexion.setAutoCommit(true);
								conexion.close();
							} 
							catch (SQLException eReintentar) 
							{
								try 
								{
									conexion.close();
								}
								catch (SQLException eCerrarConexion) 
								{
									logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
								}
							}
						}
						
			            logger.error("Ocurrió un error al cerrar el fichero de envio, se restauran los estados afectados.");
			        }
		        	catch (SQLException eDeshacerCambios)
			        {
			        	sNombreFichero = "";
			        	liEntradas = 0;

			        	try 
						{
							//reintentamos
							conexion.rollback();
							conexion.setAutoCommit(true);
							conexion.close();
						} 
						catch (SQLException eReintentar) 
						{
							try 
							{
								conexion.close();
							}
							catch (SQLException eCerrarConexion) 
							{
								logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
							}
						}
					}
		        }
			}

		}
        
        return new ResultadoEnvio(sNombreFichero,liEntradas);
	}
	
	public static ResultadoEnvio escribirNorma34(String sNUPROF) 
	{
		String sNombreFichero = "";
		long liEntradas = 0;

		Connection conexion = ConnectionManager.getDBConnection();

		if (conexion != null)
		{
			ArrayList<Long> resultpagos = CLPagos.buscarPagosSinEnviar(sNUPROF,ValoresDefecto.DEF_PAGO_NORMA34);

			if (resultpagos.size() > 0)
			{
		        FileWriter ficheroN34 = null;
		        
		        PrintWriter pw = null;
		        
		        int iLineas = 0;
		        
		        sNombreFichero = ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+Utils.timeStamp()+"_"+ValoresDefecto.DEF_PAGOS+"_"+ValoresDefecto.DEF_NORMA34+".Q34";
		        
		        boolean bOK = false;
		        try
		        {

		        	ficheroN34 = new FileWriter(sNombreFichero);
		            pw = new PrintWriter(ficheroN34);
		            
		            Cuenta cuentaordenante = new Cuenta ("",
		            		"",
		            		ValoresDefecto.DEF_ORDENANTE_ENTIDAD,
		            		ValoresDefecto.DEF_ORDENANTE_OFICINA,
		            		ValoresDefecto.DEF_ORDENANTE_DIGITO_CONTROL,
		            		ValoresDefecto.DEF_ORDENANTE_CUENTA,
		            		"");
		            
		            OrdenanteN34 ordenante = CLTransferencias.generarOrdenanteN34(cuentaordenante);
		            
		            ArrayList<String> camposordenante = Parser.escribirOrdenanteN34(ordenante);

		            
		            
		            for (int i = 0; i < camposordenante.size(); i++)
		            {
		            	pw.println(camposordenante.get(i));
		            }
		            
		            iLineas = iLineas + camposordenante.size();

		            
		            long liSumaImportes = 0;
		            
		            conexion.setAutoCommit(false);
		            
		            for (int i = 0; i < resultpagos.size(); i++)
		            {
		            	logger.debug("resultpagos.get("+i+"):|"+resultpagos.get(i)+"|");
		            	
		            	bOK = QMPagos.setEnviado(conexion, resultpagos.get(i),ValoresDefecto.PAGO_ENVIADO);
		            	
		            	if (bOK)
		            	{
		            		String sCodOperacion = QMPagos.getCodOperacion(conexion, resultpagos.get(i));
		            		
		            		logger.debug("sCodOperacion:|"+sCodOperacion+"|");
		            		
			            	TransferenciaN34 transferencia = CLTransferencias.buscarTransferenciaN34(Long.parseLong(sCodOperacion));
			            	
			            	ArrayList<String> campostransferencia = Parser.escribirTransferenciaN34(transferencia);
				            
				            for (int j = 0; j < campostransferencia.size(); j++)
				            {
				            	pw.println(campostransferencia.get(j));
				            }
				            
				            iLineas = iLineas + campostransferencia.size();
				            
				            logger.debug("transferencia.getsImporte():|"+transferencia.getsImporte()+"|");
				            
				            liSumaImportes = liSumaImportes + Long.parseLong(transferencia.getsImporte());
		            	}
		            	else
		            	{
		            		i = resultpagos.size();
		            	}
		            }
		            
		            if (bOK)
		            {
			            iLineas++;
			            
			            ResumenN34 resumen = CLTransferencias.generarResumenN34(liSumaImportes, resultpagos.size(), iLineas);
			            
			            String sResumen = Parser.escribirResumenN34(resumen);
			            pw.println(sResumen);
			            liEntradas = resultpagos.size();
		            }
		 
		        } 
		        catch (IOException e) 
		        {
		        	sNombreFichero = "";
		        	liEntradas = 0;
		            
		        	//En caso de error se devuelven los registros a su estado anterior
		            try
		            {
		            	conexion.rollback();
		            	conexion.setAutoCommit(true);
		            }
		            catch (SQLException eDeshacerCambios)
			        {
						try 
						{
							//reintentamos
							conexion.rollback();
							conexion.setAutoCommit(true);
							conexion.close();
						} 
						catch (SQLException eReintentar) 
						{
							try 
							{
								conexion.close();
							}
							catch (SQLException eCerrarConexion) 
							{
								logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
							}
						}
					}
		            
		            logger.error("Ocurrió un error al escribir en el fichero de envio, se restauran los estados afectados.");
		        } 
		        catch (SQLException eGuardarCambios)
		        {
		            sNombreFichero = "";
		            liEntradas = 0;
					try 
					{
						//reintentamos
						conexion.rollback();
						conexion.setAutoCommit(true);
						conexion.close();
					} 
					catch (SQLException eReintentar) 
					{
						try 
						{
							conexion.close();
						}
						catch (SQLException eCerrarConexion) 
						{
							logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
						}
					}
				} 
		        finally 
		        {
		        	try 
			        {
			        	if (null != ficheroN34)
			        	{
			        		if (bOK)
			        		{
				        		conexion.commit();

							    logger.info("Generado:|"+sNombreFichero+"|"); 
			        		}
			        		else
			        		{
					        	sNombreFichero = "";
					        	liEntradas = 0;

								conexion.rollback();
								logger.error("Error al revisar los movimientos.");
			        		}
			        		ficheroN34.close();

			        	}
			        	else
			        	{
			        		sNombreFichero = "";
					        liEntradas = 0;

							conexion.rollback();
								
							logger.error("Error de descriptor de fichero.");
			        	}
		        		conexion.setAutoCommit(true);
			        }
			        catch (IOException eCerarFichero) 
			        {
			        	sNombreFichero = "";
			        	liEntradas = 0;
			              
						try 
						{
							conexion.rollback();
							conexion.setAutoCommit(true);
						}
						catch (SQLException eDeshacerCambios) 
						{
							try 
							{
								//reintentamos
								conexion.rollback();
								conexion.setAutoCommit(true);
								conexion.close();
							} 
							catch (SQLException eReintentar) 
							{
								try 
								{
									conexion.close();
								}
								catch (SQLException eCerrarConexion) 
								{
									logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
								}
							}
						}
						
			            logger.error("Ocurrió un error al cerrar el fichero de envio, se restauran los estados afectados.");
			        }
		        	catch (SQLException eDeshacerCambios)
			        {
			        	sNombreFichero = "";
			        	liEntradas = 0;

			        	try 
						{
							//reintentamos
							conexion.rollback();
							conexion.setAutoCommit(true);
							conexion.close();
						} 
						catch (SQLException eReintentar) 
						{
							try 
							{
								conexion.close();
							}
							catch (SQLException eCerrarConexion) 
							{
								logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
							}
						}
					}
		        }
			}
		}
        
        return new ResultadoEnvio(sNombreFichero,liEntradas);
	}
	
	public static ResultadoEnvio escribirNorma3414(String sNUPROF) 
	{
		String sNombreFichero = "";
		long liEntradas = 0;

		Connection conexion = ConnectionManager.getDBConnection();

		if (conexion != null)
		{
			ArrayList<Long> resultpagos = CLPagos.buscarPagosSinEnviar(sNUPROF,ValoresDefecto.DEF_PAGO_NORMA3414);

			if (resultpagos.size() > 0)
			{
		        FileWriter ficheroN34 = null;
		        
		        PrintWriter pw = null;
		        
		        int iLineas = 0;
		        
		        sNombreFichero = ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+Utils.timeStamp()+"_"+ValoresDefecto.DEF_PAGOS+"_"+ValoresDefecto.DEF_NORMA3414+".Q34";
		        
		        boolean bOK = false;
		        try
		        {

		        	ficheroN34 = new FileWriter(sNombreFichero);
		            pw = new PrintWriter(ficheroN34);
		            
		            Cuenta cuentaordenante = new Cuenta (
		            		ValoresDefecto.DEF_ORDENANTE_PAIS,
		            		ValoresDefecto.DEF_ORDENANTE_IBAN,
		            		ValoresDefecto.DEF_ORDENANTE_ENTIDAD,
		            		ValoresDefecto.DEF_ORDENANTE_OFICINA,
		            		ValoresDefecto.DEF_ORDENANTE_DIGITO_CONTROL,
		            		ValoresDefecto.DEF_ORDENANTE_CUENTA,
		            		"");
		            
		            OrdenanteN3414 ordenante = CLTransferencias.generarOrdenanteN3414(cuentaordenante);
		            
		            String sOrdenante = Parser.escribirOrdenanteN3414(ordenante);

		            pw.println(sOrdenante);
		            
		            iLineas = iLineas + 1;
		            
		            CabeceraTransferenciasN3414 cabecera = CLTransferencias.generarCabeceraTransferenciaN3414();

		            String sCabecera = Parser.escribirCabeceraTransferenciasN3414(cabecera);
		            
		            pw.println(sCabecera);

		            iLineas = iLineas + 1;

		            long liSumaImportes = 0;
		            
		            conexion.setAutoCommit(false);
		            
		            for (int i = 0; i < resultpagos.size(); i++)
		            {
		            	logger.debug("resultpagos.get("+i+"):|"+resultpagos.get(i)+"|");
		            	
		            	bOK = QMPagos.setEnviado(conexion, resultpagos.get(i),ValoresDefecto.PAGO_ENVIADO);
		            	
		            	if (bOK)
		            	{
		            		String sCodOperacion = QMPagos.getCodOperacion(conexion, resultpagos.get(i));
		            		
		            		logger.debug("sCodOperacion:|"+sCodOperacion+"|");
		            		
		            		TransferenciaN3414 transferencia = CLTransferencias.buscarTransferenciaN3414(Long.parseLong(sCodOperacion));
			            	
			            	String sBeneficiarioTransferencia = Parser.escribirBeneficiarioTransferenciaN3414(transferencia);
			            	
			            	logger.debug("sBeneficiarioTransferencia:|"+sBeneficiarioTransferencia.length()+"|");
				            
			            	pw.println(sBeneficiarioTransferencia);
				            
				            iLineas = iLineas + 1;
				            
				            logger.debug("transferencia.getsImporte():|"+transferencia.getsImporteTransferencia()+"|");
				            
				            liSumaImportes = liSumaImportes + Long.parseLong(transferencia.getsImporteTransferencia());
		            	}
		            	else
		            	{
		            		i = resultpagos.size();
		            	}
		            }
		            
		            if (bOK)
		            {
			            
			            ResumenN3414 resumen = CLTransferencias.generarResumenN3414(liSumaImportes, resultpagos.size(), iLineas);
			         
			            iLineas++;
			            
			            String sResumen = Parser.escribirResumenN3414(resumen);
			            pw.println(sResumen);
			            
			            iLineas++;
			            
			            TotalesN3414 totales = CLTransferencias.generarTotalesN3414(liSumaImportes, resultpagos.size(), iLineas);
			            
			            String sTotales = Parser.escribirTotalesN3414(totales);
			            pw.println(sTotales);
			            
			            liEntradas = resultpagos.size();
		            }
		 
		        } 
		        catch (IOException e) 
		        {
		        	sNombreFichero = "";
		        	liEntradas = 0;
		            
		        	//En caso de error se devuelven los registros a su estado anterior
		            try
		            {
		            	conexion.rollback();
		            	conexion.setAutoCommit(true);
		            }
		            catch (SQLException eDeshacerCambios)
			        {
						try 
						{
							//reintentamos
							conexion.rollback();
							conexion.setAutoCommit(true);
							conexion.close();
						} 
						catch (SQLException eReintentar) 
						{
							try 
							{
								conexion.close();
							}
							catch (SQLException eCerrarConexion) 
							{
								logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
							}
						}
					}
		            
		            logger.error("Ocurrió un error al escribir en el fichero de envio, se restauran los estados afectados.");
		        } 
		        catch (SQLException eGuardarCambios)
		        {
		            sNombreFichero = "";
		            liEntradas = 0;
					try 
					{
						//reintentamos
						conexion.rollback();
						conexion.setAutoCommit(true);
						conexion.close();
					} 
					catch (SQLException eReintentar) 
					{
						try 
						{
							conexion.close();
						}
						catch (SQLException eCerrarConexion) 
						{
							logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
						}
					}
				} 
		        finally 
		        {
		        	try 
			        {
			        	if (null != ficheroN34)
			        	{
			        		if (bOK)
			        		{
				        		conexion.commit();

							    logger.info("Generado:|"+sNombreFichero+"|"); 
			        		}
			        		else
			        		{
					        	sNombreFichero = "";
					        	liEntradas = 0;

								conexion.rollback();
								logger.error("Error al revisar los movimientos.");
			        		}
			        		ficheroN34.close();

			        	}
			        	else
			        	{
			        		sNombreFichero = "";
					        liEntradas = 0;

							conexion.rollback();
								
							logger.error("Error de descriptor de fichero.");
			        	}
		        		conexion.setAutoCommit(true);
			        }
			        catch (IOException eCerarFichero) 
			        {
			        	sNombreFichero = "";
			        	liEntradas = 0;
			              
						try 
						{
							conexion.rollback();
							conexion.setAutoCommit(true);
						}
						catch (SQLException eDeshacerCambios) 
						{
							try 
							{
								//reintentamos
								conexion.rollback();
								conexion.setAutoCommit(true);
								conexion.close();
							} 
							catch (SQLException eReintentar) 
							{
								try 
								{
									conexion.close();
								}
								catch (SQLException eCerrarConexion) 
								{
									logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
								}
							}
						}
						
			            logger.error("Ocurrió un error al cerrar el fichero de envio, se restauran los estados afectados.");
			        }
		        	catch (SQLException eDeshacerCambios)
			        {
			        	sNombreFichero = "";
			        	liEntradas = 0;

			        	try 
						{
							//reintentamos
							conexion.rollback();
							conexion.setAutoCommit(true);
							conexion.close();
						} 
						catch (SQLException eReintentar) 
						{
							try 
							{
								conexion.close();
							}
							catch (SQLException eCerrarConexion) 
							{
								logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
							}
						}
					}
		        }
			}
		}
        
        return new ResultadoEnvio(sNombreFichero,liEntradas);
	}
	
	public static ResultadoCarga leerActivos(String sNombre)
	{
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		ResultadoCarga resultadocarga;
		
		//logger.debug( "Fichero:|"+ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre+"|");

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		FileReader fr;
		
		long contador= 0 ;
		long registros = 0;
		int iSalida = 0;
		
		String sDuracion = "0";
		
		try 
		{
			long liTiempo = System.currentTimeMillis();
			
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			//logger.debug("Leyendo fichero..");

			int iLongitudValida = Longitudes.ACTIVOS_L-Longitudes.FILLER_ACTIVOS_L-Longitudes.OBDEER_L;
			
			while((linea=br.readLine())!=null)
	        {
				contador++;

				//logger.debug("Longitud de línea leida:|"+linea.length()+"|");
				//logger.debug("Longitud de línea válida:|"+iLongitudValida+"|");

	    		if (linea.equals(sFinFichero))
	    		{
	    			contador--;
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			iSalida = -1;
	    			logger.error("Error en línea "+contador+", tamaño incorrecto.");
	    		}
	    		else
	    		{
	    			int iCodigo = CLActivos.actualizaActivoLeido(linea);

	    			String sResultado = "";
	    			String sDescripcion = "";

	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sDescripcion = "Nuevo Activo registrado.";
	    				sResultado = ValoresDefecto.DEF_CARGA_NUEVO;
	    				break;
	    			case -1:
	    				sDescripcion = "[FATAL] Error al crear el Activo.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -2:
	    				sDescripcion = "[FATAL] Error al actualizar el Activo.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -3:
	    				sDescripcion = "[FATAL] Error al crear la entrada en el registro de Activos.";
	    				sResultado = ValoresDefecto.DEF_CARGA_NUEVO;
	    				break;
	    			case -4:
	    				sDescripcion = "[FATAL] Error al modificar la entrada en el registro de Activos.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -5:
	    				sDescripcion = "[FATAL] Error de acceso a base de datos.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case 1:
	    				sDescripcion = "Activo actualizado.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ACTUALIZADO;
	    				break;
	    			case 2:
	    				sDescripcion = "El registro ya se encuentra en el sistema.";
	    				sResultado = ValoresDefecto.DEF_CARGA_SINCAMBIOS;
	    				break;
	    			}
	    			
	    			String sMensaje = "["+sResultado+"] Línea "+contador+": "+sDescripcion;

	    			if ( iCodigo >= 0 )
	    			{
	    				logger.info(sMensaje);
	    				registros++;
	    			}
	    			else
	    			{
	    				iSalida = -1;
	    				logger.error(sMensaje);
	    			}

	    			ResultadosTabla resultadolectura = new ResultadosTabla(sNombre,contador,sResultado,sDescripcion);
	    			tabla.add(resultadolectura);
	    		}
	        }
			
			sDuracion = Utils.duracion(liTiempo,System.currentTimeMillis());
		
			br.close();
			
			//logger.debug("Registros procesados:|"+contador+"|");
			//logger.debug("Registros correctos:|"+registros+"|");
			
			if (iSalida != 0)
			{
				logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
			}
			
			logger.info("Duración de la carga: "+sDuracion);
		}
		catch (FileNotFoundException e)
		{
			logger.error("No se encontró el fichero recibido.");
			iSalida = -2;
		}
		catch (IOException e)
		{
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			iSalida = -3;
		}

		resultadocarga = new ResultadoCarga(iSalida,tabla,sNombre,sDuracion,contador,registros);
		
		logger.info( "Lectura de "+sNombre+" finalizada.\n");
		
	
		//logger.debug("tabla.size():|"+tabla.size()+"|");
		
        return resultadocarga;
	}

	public static ResultadoCarga leerGastosRevisados(String sNombre) 
	{
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		ResultadoCarga resultadocarga;
		
		//logger.debug( "Fichero:|"+ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre+"|");

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		FileReader fr;

		long contador= 0 ;
		long registros = 0;
		int iSalida = 0;
		
		String sDuracion = "0";
		
		try 
		{
			long liTiempo = System.currentTimeMillis();
			
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			//logger.debug("Leyendo fichero..");
			
			int iLongitudValida = Longitudes.GASTOS_L-Longitudes.FILLER_GASTOS_L-Longitudes.OBDEER_L;
			
			while((linea=br.readLine())!=null)
	        {
				contador++;

				//logger.debug("Longitud de línea leida:|"+linea.length()+"|");
				
				//logger.debug("Longitud de línea válida:|"+iLongitudValida+"|");

	    		if (linea.equals(sFinFichero))
	    		{
	    			contador--;
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			iSalida = -1;
	    			logger.error("Error en línea "+contador+", tamaño incorrecto.");
	    		}
	    		else
	    		{
	    			int iCodigo = CLGastos.actualizarGastoRecibido(linea);
	    			
	    			String sResultado = "";
	    			String sDescripcion = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sDescripcion = "Movimiento validado.";
	    				sResultado = ValoresDefecto.DEF_CARGA_VALIDADO;
	    				break;
	    			case 1:
	    				sDescripcion = "Movimiento de Gasto pendiente de revisión.";
	    				sResultado = ValoresDefecto.DEF_CARGA_REVISAR;
	    				break;
	    			case 2:
	    				sDescripcion = "El movimiento recibido ya esta en revisión.";
	    				sResultado = ValoresDefecto.DEF_CARGA_SINCAMBIOS;
	    				break;
	    			case -1:
	    				sDescripcion = "Registro no encontrado en el sistema.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -2:
	    				sDescripcion = "No existe relación con la Gasto.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -3:
	    				sDescripcion = "[FATAL] Error al validar la relación con el Gasto.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -4:
	    				sDescripcion = "[FATAL] Error al registrar el movimiento pendiente.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -5:
	    				sDescripcion = "[FATAL] Error al actualizar la revisión del Gasto.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -6:
	    				sDescripcion = "[FATAL] Error al actualizar la revisión del Abono.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -7:
	    				sDescripcion = "[FATAL] Error al actualizar la Provisión del Gasto.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -8:
	    				sDescripcion = "El activo no pertenece a la cartera.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -9:
	    				sDescripcion = "La provisión de gastos asociada figura como 'no enviada'.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -10:
	    				sDescripcion = "[FATAL] Estado del movimiento desconocido.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -11:
	    				sDescripcion = "[FATAL] El movimiento recibido figura como 'no enviado'.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -12:
	    				sDescripcion = "[FATAL] El gasto esta resuelto y puede recibir más movimientos.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
					case -910:
						sDescripcion = "[FATAL] Se ha producido un error al conectar con la base de datos.";
						sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
						break;
	    			}

	    			String sMensaje = "["+sResultado+"] Línea "+contador+": "+sDescripcion;

	    			if ( iCodigo >= 0 )
	    			{
	    				logger.info(sMensaje);
	    				registros++;
	    			}
	    			else
	    			{
	    				iSalida = -1;
	    				logger.error(sMensaje);
	    			}

	    			ResultadosTabla resultadolectura = new ResultadosTabla(sNombre,contador,sResultado,sDescripcion);
	    			tabla.add(resultadolectura);

	    		}
	        }

			sDuracion = Utils.duracion(liTiempo,System.currentTimeMillis());
			
			br.close();
			
			//logger.debug("Registros procesados:|"+contador+"|");
			//logger.debug("Registros correctos:|"+registros+"|");
			
			if (iSalida != 0)
			{
				logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
			}
			
			logger.info("Duración de la carga: "+sDuracion);
		}
		catch (FileNotFoundException e)
		{
			logger.error("No se encontró el fichero recibido.");
			iSalida = -2;
		}
		catch (IOException e)
		{
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			iSalida = -3;
		}

		resultadocarga = new ResultadoCarga(iSalida,tabla,sNombre,sDuracion,contador,registros);
		
		logger.info( "Lectura de "+sNombre+" finalizada.\n");
		
	
		//logger.debug("tabla.size():|"+tabla.size()+"|");
		
        return resultadocarga;
			
	}
	
	public static ResultadoCarga leerGastosVolcados(String sNombre) 
	{
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		ResultadoCarga resultadocarga;
		
		//logger.debug( "Fichero:|"+ValoresDefecto.DEF_PATH_BACKUP_CARGADOS+sNombre+"|");

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_CARGADOS+sNombre);
		
		FileReader fr;
		
		long contador= 0 ;
		long registros = 0;
		int iSalida = 0;
		
		String sDuracion = "0";

		try 
		{
			long liTiempo = System.currentTimeMillis();
			
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			
			//logger.debug("Leyendo fichero..");
			
			int iLongitudValida = Longitudes.GASTOS_L-Longitudes.FILLER_GASTOS_L-Longitudes.OBDEER_L;
			
			while((linea=br.readLine())!=null)
	        {
				contador++;

				//logger.debug("Longitud de línea leida:|"+linea.length()+"|");
				
				//logger.debug("Longitud de línea válida:|"+iLongitudValida+"|");

	    		if (linea.equals(sFinFichero))
	    		{
	    			contador--;
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			iSalida = -1;
	    			logger.error("Error en línea "+contador+", tamaño incorrecto.");
	    		}
	    		else
	    		{
	    			int iCodigo = CLGastos.inyectarGastoVolcado(linea);
	    			//int iCodigo = CLGastos.validarGastoVolcado(linea);

	    			String sResultado = "";
	    			String sDescripcion = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sDescripcion = "Movimiento asimilado.";
	    				sResultado = ValoresDefecto.DEF_CARGA_NUEVO;
	    				break;
	    			case -8:
	    				sDescripcion = "El activo no pertenece a la cartera.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -9:
	    				sDescripcion = "No existe la provisión de gasto en el sistema.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -12:
	    				sDescripcion = "El gasto no admite más movimientos salvo abonos.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;	
	    			case -800:
	    				sDescripcion = "El movimiento cargado ya se encontraba en el sistema.";
	    				sResultado = ValoresDefecto.DEF_CARGA_SINCAMBIOS;
	    				break;
	    			case -900:
	    				sDescripcion = "[FATAL] Error al registrar el movimiento.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -901:
	    				sDescripcion = "[FATAL] Error al crear el Gasto.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -902:
	    				sDescripcion = "[FATAL] Error al crear la relación del Gasto.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;	
	    			case -903:
	    				sDescripcion = "[FATAL] Error al crear la Provisión del Gasto.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -904:
	    				sDescripcion = "[FATAL] Error al crear la relación del Gasto con la Provisión.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -905:
	    				sDescripcion = "[FATAL] Error al actualizar el estado del Gasto.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -906:
	    				sDescripcion = "[FATAL] Error al actualizar el estado de la Provisión del Gasto.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -907:
	    				sDescripcion = "[FATAL] Error al actualizar el Gasto.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -908:
	    				sDescripcion = "[FATAL] Error al actualizar la Provisión del Gasto.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -909:
	    				sDescripcion = "[FATAL] Error al registar el Abono del Gasto.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -910:
	    				sDescripcion = "[FATAL] Error de conexion con base de datos.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -911:
	    				sDescripcion = "[FATAL]  Error al actualizar la relación del Gasto con la Provisión.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -912:
	    				sDescripcion = "[FATAL]  Error al actualizar la relación del Gasto.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;

	    			default:
	    				sDescripcion = "El movimiento cargado no ha podido ser validado ("+iCodigo+").";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    				
	    			}
	    			
	    			String sMensaje = "["+sResultado+"] Línea "+contador+": "+sDescripcion;

	    			if ( iCodigo >= 0 )
	    			{
	    				logger.info(sMensaje);
	    				registros++;
	    			}
	    			else
	    			{
	    				iSalida = -1;
	    				logger.error(sMensaje);
	    			}

	    			ResultadosTabla resultadolectura = new ResultadosTabla(sNombre,contador,sResultado,sDescripcion);
	    			tabla.add(resultadolectura);
	    		}
	        }
			
			sDuracion = Utils.duracion(liTiempo,System.currentTimeMillis());
		
			br.close();
		
			//logger.debug("Registros procesados:|"+contador+"|");
			//logger.debug("Registros correctos:|"+registros+"|");

			if (iSalida != 0)
			{
				logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
			}
			
			logger.info("Duración de la carga: "+sDuracion);
		}
		catch (FileNotFoundException e)
		{
			logger.error("No se encontró el fichero recibido.");
			iSalida = -2;
		}
		catch (IOException e)
		{
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			iSalida = -3;
		}

		resultadocarga = new ResultadoCarga(iSalida,tabla,sNombre,sDuracion,contador,registros);
		
		logger.info( "Lectura de "+sNombre+" finalizada.\n");
		
	
		//logger.debug("tabla.size():|"+tabla.size()+"|");
		
        return resultadocarga;
	
	}
	
	public static ResultadoCarga validarGastosVolcados(String sNombre) 
	{
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		ResultadoCarga resultadocarga;
		
		//logger.debug( "Fichero:|"+ValoresDefecto.DEF_PATH_BACKUP_CARGADOS+sNombre+"|");

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_CARGADOS+sNombre);
		
		FileReader fr;
		
		long contador= 0 ;
		long registros = 0;
		int iSalida = 0;
		
		String sDuracion = "0";

		try 
		{
			long liTiempo = System.currentTimeMillis();
			
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			
			//logger.debug("Leyendo fichero..");
			
			int iLongitudValida = Longitudes.GASTOS_L-Longitudes.FILLER_GASTOS_L-Longitudes.OBDEER_L;
			
			while((linea=br.readLine())!=null)
	        {
				contador++;

				//logger.debug("Longitud de línea leida:|"+linea.length()+"|");
				
				//logger.debug("Longitud de línea válida:|"+iLongitudValida+"|");

	    		if (linea.equals(sFinFichero))
	    		{
	    			contador--;
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			iSalida = -1;
	    			logger.error("Error en línea "+contador+", tamaño incorrecto.");
	    		}
	    		else
	    		{
	    			//int iCodigo = CLGastos.inyectarGastoVolcado(linea);
	    			int iCodigo = CLGastos.validarGastoVolcado(linea);

	    			String sResultado = "";
	    			String sDescripcion = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sDescripcion = "Movimiento asimilado.";
	    				sResultado = ValoresDefecto.DEF_CARGA_NUEVO;
	    				break;
	    			case -8:
	    				sDescripcion = "El activo no pertenece a la cartera.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -9:
	    				sDescripcion = "No existe la provisión de gasto en el sistema.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;	
	    			case -908:
	    				sDescripcion = "[FATAL] Error al crear la Provisión del Gasto.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -901:
	    				sDescripcion = "[FATAL] Error al crear el Gasto.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -909:
	    				sDescripcion = "El movimiento cargado ya se encontraba en el sistema.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -900:
	    				sDescripcion = "[FATAL] Error al registrar el movimiento.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -902:
	    				sDescripcion = "[FATAL] Error al crear la relación del Gasto.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;	
	    			case -906:
	    				sDescripcion = "[FATAL] Error al crear la relación del Gasto con la Provisión.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			default:
	    				sDescripcion = "El movimiento cargado no ha podido ser validado ("+iCodigo+").";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    				
	    			}
	    			
	    			String sMensaje = "["+sResultado+"] Línea "+contador+": "+sDescripcion;

	    			if ( iCodigo >= 0 )
	    			{
	    				logger.info(sMensaje);
	    				registros++;
	    			}
	    			else
	    			{
	    				iSalida = -1;
	    				logger.error(sMensaje);
	    			}

	    			ResultadosTabla resultadolectura = new ResultadosTabla(sNombre,contador,sResultado,sDescripcion);
	    			tabla.add(resultadolectura);
	    		}
	        }
			
			sDuracion = Utils.duracion(liTiempo,System.currentTimeMillis());
		
			br.close();
		
			//logger.debug("Registros procesados:|"+contador+"|");
			//logger.debug("Registros correctos:|"+registros+"|");

			if (iSalida != 0)
			{
				logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
			}
			
			logger.info("Duración de la carga: "+sDuracion);
		}
		catch (FileNotFoundException e)
		{
			logger.error("No se encontró el fichero recibido.");
			iSalida = -2;
		}
		catch (IOException e)
		{
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			iSalida = -3;
		}

		resultadocarga = new ResultadoCarga(iSalida,tabla,sNombre,sDuracion,contador,registros);
		
		logger.info( "Lectura de "+sNombre+" finalizada.\n");
		
	
		//logger.debug("tabla.size():|"+tabla.size()+"|");
		
        return resultadocarga;
	
	}

	public static ResultadoCarga leerComunidadesRevisadas(String sNombre)
	{
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		ResultadoCarga resultadocarga;
		
		//logger.debug( "Fichero:|"+ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre+"|");

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		FileReader fr;

		long contador= 0 ;
		long registros = 0;
		int iSalida = 0;
		
		String sDuracion = "0";
		
		try 
		{
			long liTiempo = System.currentTimeMillis();
			
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			logger.debug("Leyendo fichero..");

			int iLongitudValida = Longitudes.COMUNIDADES_L-Longitudes.FILLER_COMUNIDADES_L-Longitudes.OBDEER_L;
				
			while((linea=br.readLine())!=null)
	        {
				contador++;

				logger.debug("Longitud de línea leida:|"+linea.length()+"|");
				logger.debug("Longitud de línea válida:|"+iLongitudValida+"|");

	    		if (linea.equals(sFinFichero))
	    		{
	    			contador--;
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			iSalida = -1;
	    			logger.error("Error en línea "+contador+", tamaño incorrecto.");
	    		}
	    		else
	    		{
	    			int iCodigo = CLComunidades.actualizarComunidadLeida(linea);

	    			String sResultado = "";
	    			String sDescripcion = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sDescripcion = "Movimiento validado.";
	    				sResultado = ValoresDefecto.DEF_CARGA_VALIDADO;
	    				break;
	    			case 1:
	    				sDescripcion = "Movimento de Comunidad pendiente de revisión.";
	    				sResultado = ValoresDefecto.DEF_CARGA_REVISAR;
	    				break;
	    			case -1:
	    				sDescripcion = "Registro no encontrado en el sistema.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -2:
	    				sDescripcion = "No existe relación con la Comunidad.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -3:
	    				sDescripcion = "No existe relación Activo-Comunidad.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -4:
	    				sDescripcion = "[FATAL] Error al validar la reclación con la Comunidad.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -5:
	    				sDescripcion = "[FATAL] Error al validar la relación Activo-Comunidad.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -6:
	    				sDescripcion = "[FATAL] Error al registrar el movimiento pendiente.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -8:
	    				sDescripcion = "El activo no pertenece a la cartera.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -9:
	    				sDescripcion = "[FATAL] Accion desconocida.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -10:
	    				sDescripcion = "[FATAL] Estado del movimiento desconocido.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -11:
	    				sDescripcion = "[FATAL] El movimiento recibido figura como 'no enviado'.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -12:
	    				sDescripcion = "El movimiento recibido ya ha sido revisado.";
	    				sResultado = ValoresDefecto.DEF_CARGA_SINCAMBIOS;
	    				break;
	    			}

	    			String sMensaje = "["+sResultado+"] Línea "+contador+": "+sDescripcion;

	    			if ( iCodigo >= 0 )
	    			{
	    				logger.info(sMensaje);
	    				registros++;
	    			}
	    			else
	    			{
	    				iSalida = -1;
	    				logger.error(sMensaje);
	    			}

	    			ResultadosTabla resultadolectura = new ResultadosTabla(sNombre,contador,sResultado,sDescripcion);
	    			tabla.add(resultadolectura);
	    		}
	        }

			sDuracion = Utils.duracion(liTiempo,System.currentTimeMillis());
			
			br.close();
			
			//logger.debug("Registros procesados:|"+contador+"|");
			//logger.debug("Registros correctos:|"+registros+"|");
			
			if (iSalida != 0)
			{
				logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
			}
			
			logger.info("Duración de la carga: "+sDuracion);
		}
		catch (FileNotFoundException e)
		{
			logger.error("No se encontró el fichero recibido.");
			iSalida = -2;
		}
		catch (IOException e)
		{
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			iSalida = -3;
		}

		resultadocarga = new ResultadoCarga(iSalida,tabla,sNombre,sDuracion,contador,registros);
		
		logger.info( "Lectura de "+sNombre+" finalizada.\n");
		
	
		//logger.debug("tabla.size():|"+tabla.size()+"|");
		
        return resultadocarga;
	}
	
	public static ResultadoCarga leerComunidadesVolcadas(String sNombre)
	{
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		ResultadoCarga resultadocarga;
		
		logger.debug( "Fichero:|"+ValoresDefecto.DEF_PATH_BACKUP_CARGADOS+sNombre+"|");

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_CARGADOS+sNombre);
		
		FileReader fr;

		long contador= 0 ;
		long registros = 0;
		int iSalida = 0;
		
		String sDuracion = "0";
		
		try 
		{
			long liTiempo = System.currentTimeMillis();
			
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			logger.debug("Leyendo fichero..");

			int iLongitudValida = Longitudes.COMUNIDADES_L-Longitudes.FILLER_COMUNIDADES_L-Longitudes.OBDEER_L;
				
			while((linea=br.readLine())!=null)
	        {
				contador++;

				logger.debug("Longitud de línea leida:|"+linea.length()+"|");
				logger.debug("Longitud de línea válida:|"+iLongitudValida+"|");

	    		if (linea.equals(sFinFichero))
	    		{
	    			contador--;
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			iSalida = -1;
	    			logger.error("Error en línea "+contador+", tamaño incorrecto.");
	    		}
	    		else
	    		{
	    			int iCodigo = CLComunidades.inyectarComunidadVolcada(linea);

	    			String sResultado = "";
	    			String sDescripcion = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sDescripcion = "Movimiento validado.";
	    				sResultado = ValoresDefecto.DEF_CARGA_VALIDADO;
	    				break;
	    			case 1:
	    				sDescripcion = "Movimento de Comunidad pendiente de revisión.";
	    				sResultado = ValoresDefecto.DEF_CARGA_REVISAR;
	    				break;
	    			case -1:
	    				sDescripcion = "El movimiento cargado ya se encontraba en el sistema.";
	    				sResultado = ValoresDefecto.DEF_CARGA_SINCAMBIOS;
	    				break;
	    			case -2:
	    				sDescripcion = "No existe relación con la Comunidad.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -3:
	    				sDescripcion = "No existe relación Activo-Comunidad.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -4:
	    				sDescripcion = "[FATAL] Error al validar la reclación con la Comunidad.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -5:
	    				sDescripcion = "[FATAL] Error al validar la relación Activo-Comunidad.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -6:
	    				sDescripcion = "[FATAL] Error al registrar el movimiento pendiente.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -8:
	    				sDescripcion = "El activo no pertenece a la cartera.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -9:
	    				sDescripcion = "[FATAL] Accion desconocida.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -10:
	    				sDescripcion = "[FATAL] Estado del movimiento desconocido.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -11:
	    				sDescripcion = "[FATAL] El movimiento recibido figura como 'no enviado'.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -12:
	    				sDescripcion = "El movimiento recibido ya ha sido revisado.";
	    				sResultado = ValoresDefecto.DEF_CARGA_SINCAMBIOS;
	    				break;
	    			case -13:
	    				sDescripcion = "[FATAL] El movimiento recibido figura como 'enviado'.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;

					case -801: //Error 801 - alta de una comunidad en alta
						sDescripcion = "La comunidad ya esta dada de alta. Por favor, revise los datos.";
						sResultado = ValoresDefecto.DEF_CARGA_ERROR;
						break;

					case -802: //Error 802 - comunidad de baja no puede recibir mas movimientos
						sDescripcion = "La comunidad esta de baja y no puede recibir más movimientos.";
						sResultado = ValoresDefecto.DEF_CARGA_ERROR;
						break;
						
					case -803: //Error 803 - comunidad no disponible
						sDescripcion = "Error al modificar. La comunidad leida no está registrada.";
						sResultado = ValoresDefecto.DEF_CARGA_ERROR;
						break;
	    				
	    			case -900: //Error 900 - al crear un movimiento
						sDescripcion = "[FATAL] Se ha producido un error al registrar el movimiento.";
						sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
						break;

					case -901: //Error 901 - error y rollback - error al crear la comuidad
						sDescripcion = "[FATAL] Se ha producido un error al registrar la comunidad.";
						sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
						break;
						
					case -902: //Error 902 - error y rollback - error al registrar la relaccion
						sDescripcion = "[FATAL] Se ha producido un error al registrar la relacion.";
						sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
						break;

					case -903: //Error 903 - error y rollback - error al registrar el activo durante el alta
						sDescripcion = "[FATAL] Se ha producido un error al asociar el activo durante el alta.";
						sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
						break;

					case -904: //Error 904 - error y rollback - error al cambiar el estado
						sDescripcion = "[FATAL] Se ha producido un error al cambiar el estado de la comunidad.";
						sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
						break;

					case -905: //Error 905 - error y rollback - error al modificar la comunidad
						sDescripcion = "[FATAL] Se ha producido un error al modificar la comunidad.";
						sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
						break;

					case -906: //Error 906 - error y rollback - el activo ya esta vinculado
						sDescripcion = "[FATAL] El activo ya ha sido vinculado.";
						sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
						break;

					case -907: //Error 907 - error y rollback - error al asociar el activo en la comunidad
						sDescripcion = "[FATAL] Se ha producido un error al asociar el activo a la comunidad.";
						sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
						break;

					case -908: //Error 908 - error y rollback - el activo no esta vinculado
						sDescripcion = "[FATAL] El activo no ha sido vinculado.";
						sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
						break;

					case -909: //Error 909 - error y rollback - error al desasociar el activo en la comunidad
						sDescripcion = "[FATAL] Se ha producido un error al desasociar el activo a la comunidad.";
						sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
						break;
						
					case -910: //Error 910 - error y rollback - error al conectar con la base de datos
						sDescripcion = "[FATAL] Se ha producido un error al conectar con la base de datos.";
						sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
						break;
						
					case -911: //Error 911 - error y rollback - error al crear la cuenta de la comunidad
						sDescripcion = "[FATAL] Se ha producido un error al crear la cuenta de la comunidad.";
						sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
						break;
						
					case -912: //Error 912 - error y rollback - error al crear la cuenta de la comunidad
						sDescripcion = "[FATAL] Se ha producido un error al crear la relacion cuenta-comunidad.";
						sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
						break;

					case -913: //Error 913 - error y rollback - error al crear la cuenta de la comunidad
						sDescripcion = "[FATAL] Se ha producido un error al crear la cuenta de la comunidad, ya esta dada de alta.";
						sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
						break;

					case -914: //Error 914 - error y rollback - error la cuenta nueva ya existe
						sDescripcion = "[FATAL] Se ha producido un error al crear la cuenta de la comunidad, ya existe. Por favor, revise los datos y avise a soporte.";
						sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
						break;
						
					default: //error generico
						sDescripcion = "[FATAL] La operacion solicitada ha producido un error desconocido("+iCodigo+"). Por favor, revise los datos y avise a soporte.";
						sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
						break;
	    			}

	    			String sMensaje = "["+sResultado+"] Línea "+contador+": "+sDescripcion;

	    			if ( iCodigo >= 0 )
	    			{
	    				logger.info(sMensaje);
	    				registros++;
	    			}
	    			else
	    			{
	    				iSalida = -1;
	    				logger.error(sMensaje);
	    			}

	    			ResultadosTabla resultadolectura = new ResultadosTabla(sNombre,contador,sResultado,sDescripcion);
	    			tabla.add(resultadolectura);
	    		}
	        }

			sDuracion = Utils.duracion(liTiempo,System.currentTimeMillis());
			
			br.close();
			
			//logger.debug("Registros procesados:|"+contador+"|");
			//logger.debug("Registros correctos:|"+registros+"|");
			
			if (iSalida != 0)
			{
				logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
			}
			
			logger.info("Duración de la carga: "+sDuracion);
		}
		catch (FileNotFoundException e)
		{
			logger.error("No se encontró el fichero recibido.");
			iSalida = -2;
		}
		catch (IOException e)
		{
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			iSalida = -3;
		}

		resultadocarga = new ResultadoCarga(iSalida,tabla,sNombre,sDuracion,contador,registros);
		
		logger.info( "Lectura de "+sNombre+" finalizada.\n");
		
	
		//logger.debug("tabla.size():|"+tabla.size()+"|");
		
        return resultadocarga;
	}
	
	public static ResultadoCarga leerCuotasRevisadas(String sNombre) 
	{
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		ResultadoCarga resultadocarga;
		
		//logger.debug( "Fichero:|"+ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre+"|");

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		FileReader fr;

		long contador= 0 ;
		long registros = 0;
		int iSalida = 0;
		
		String sDuracion = "0";
		
		try 
		{
			long liTiempo = System.currentTimeMillis();
			
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			logger.debug("Leyendo fichero..");
			
			int iLongitudValida = Longitudes.CUOTAS_L-Longitudes.FILLER_CUOTAS_L-Longitudes.OBDEER_L;
			
			while((linea=br.readLine())!=null)
	        {
				contador++;

				logger.debug("Longitud de línea leida:|"+linea.length()+"|");
				logger.debug("Longitud de línea válida:|"+iLongitudValida+"|");

	    		if (linea.equals(sFinFichero))
	    		{
	    			contador--;
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			iSalida = -1;
	    			logger.error("Error en línea "+contador+", tamaño incorrecto.");
	    		}
	    		else
	    		{
	    			int iCodigo = CLCuotas.actualizarCuotaLeida(linea);

	    			String sResultado = "";
	    			String sDescripcion = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sDescripcion = "Movimiento validado.";
	    				sResultado = ValoresDefecto.DEF_CARGA_VALIDADO;
	    				break;
	    			case 1:
	    				sDescripcion = "Movimento de Cuota pendiente de revisión.";
	    				sResultado = ValoresDefecto.DEF_CARGA_REVISAR;
	    				break;
	    			case -1:
	    				sDescripcion = "Registro no encontrado en el sistema.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -2:
	    				sDescripcion = "No existe relación con la Cuota.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -3:
	    				sDescripcion = "[FATAL] Error al validar la reclación con la Cuota.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -4:
	    				sDescripcion = "[FATAL] Error al registrar el movimiento pendiente.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -8:
	    				sDescripcion = "El activo no pertenece a la cartera.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -9:
	    				sDescripcion = "[FATAL] Accion desconocida.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -10:
	    				sDescripcion = "[FATAL] Estado del movimiento desconocido.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -11:
	    				sDescripcion = "[FATAL] El movimiento recibido figura como 'no enviado'.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -12:
	    				sDescripcion = "El movimiento recibido ya ha sido revisado.";
	    				sResultado = ValoresDefecto.DEF_CARGA_SINCAMBIOS;
	    				break;
	    			}
	    			
	    			String sMensaje = "["+sResultado+"] Línea "+contador+": "+sDescripcion;

	    			if ( iCodigo >= 0 )
	    			{
	    				logger.info(sMensaje);
	    				registros++;
	    			}
	    			else
	    			{
	    				iSalida = -1;
	    				logger.error(sMensaje);
	    			}

	    			ResultadosTabla resultadolectura = new ResultadosTabla(sNombre,contador,sResultado,sDescripcion);
	    			tabla.add(resultadolectura);
	    		}
	        }

			sDuracion = Utils.duracion(liTiempo,System.currentTimeMillis());
			
			br.close();
			
			//logger.debug("Registros procesados:|"+contador+"|");
			//logger.debug("Registros correctos:|"+registros+"|");
			
			if (iSalida != 0)
			{
				logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
			}
			
			logger.info("Duración de la carga: "+sDuracion);
		}
		catch (FileNotFoundException e)
		{
			logger.error("No se encontró el fichero recibido.");
			iSalida = -2;
		}
		catch (IOException e)
		{
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			iSalida = -3;
		}

		resultadocarga = new ResultadoCarga(iSalida,tabla,sNombre,sDuracion,contador,registros);
		
		logger.info( "Lectura de "+sNombre+" finalizada.\n");
		
	
		//logger.debug("tabla.size():|"+tabla.size()+"|");
		
        return resultadocarga;
	}
	
	public static ResultadoCarga leerReferenciasRevisadas(String sNombre) 
	{
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		ResultadoCarga resultadocarga;
		
		//logger.debug( "Fichero:|"+ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre+"|");

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		FileReader fr;

		long contador= 0 ;
		long registros = 0;
		int iSalida = 0;
		
		String sDuracion = "0";
		
		try 
		{
			long liTiempo = System.currentTimeMillis();
			
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			logger.debug("Leyendo fichero..");
			
			int iLongitudValida = Longitudes.REFERENCIAS_L-Longitudes.FILLER_REFERENCIAS_L-Longitudes.OBDEER_L;

			while((linea=br.readLine())!=null)
	        {
				contador++;

				logger.debug("Longitud de línea leida:|"+linea.length()+"|");
				logger.debug("Longitud de línea válida:|"+iLongitudValida+"|");

	    		if (linea.equals(sFinFichero))
	    		{
	    			contador--;
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			iSalida = -1;
	    			logger.error("Error en línea "+contador+", tamaño incorrecto.");
	    		}
	    		else
	    		{
	    			int iCodigo = CLReferencias.actualizaReferenciaLeida(linea);

	    			String sResultado = "";
	    			String sDescripcion = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sDescripcion = "Movimiento validado.";
	    				sResultado = ValoresDefecto.DEF_CARGA_VALIDADO;
	    				break;
	    			case 1:
	    				sDescripcion = "Movimento de Referencia Catastral pendiente de revisión.";
	    				sResultado = ValoresDefecto.DEF_CARGA_REVISAR;
	    				break;
	    			case -1:
	    				sDescripcion = "Registro no encontrado en el sistema.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -2:
	    				sDescripcion = "No existe relación con la Referencia Catastral.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -3:
	    				sDescripcion = "[FATAL] Error al validar la reclación con la Referencia Catastral.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -4:
	    				sDescripcion = "[FATAL] Error al registrar el movimiento pendiente.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -8:
	    				sDescripcion = "El activo no pertenece a la cartera.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -9:
	    				sDescripcion = "[FATAL] Accion desconocida.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -10:
	    				sDescripcion = "[FATAL] Estado del movimiento desconocido.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -11:
	    				sDescripcion = "[FATAL] El movimiento recibido figura como 'no enviado'.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -12:
	    				sDescripcion = "El movimiento recibido ya ha sido revisado.";
	    				sResultado = ValoresDefecto.DEF_CARGA_SINCAMBIOS;
	    				break;
	    			}
	    			
	    			String sMensaje = "["+sResultado+"] Línea "+contador+": "+sDescripcion;

	    			if ( iCodigo >= 0 )
	    			{
	    				logger.info(sMensaje);
	    				registros++;
	    			}
	    			else
	    			{
	    				iSalida = -1;
	    				logger.error(sMensaje);
	    			}

	    			ResultadosTabla resultadolectura = new ResultadosTabla(sNombre,contador,sResultado,sDescripcion);
	    			tabla.add(resultadolectura);
	    			
	    		}
	        }

			sDuracion = Utils.duracion(liTiempo,System.currentTimeMillis());
			
			br.close();
			
			//logger.debug("Registros procesados:|"+contador+"|");
			//logger.debug("Registros correctos:|"+registros+"|");
			
			if (iSalida != 0)
			{
				logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
			}
			
			logger.info("Duración de la carga: "+sDuracion);
		}
		catch (FileNotFoundException e)
		{
			logger.error("No se encontró el fichero recibido.");
			iSalida = -2;
		}
		catch (IOException e)
		{
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			iSalida = -3;
		}

		resultadocarga = new ResultadoCarga(iSalida,tabla,sNombre,sDuracion,contador,registros);
		
		logger.info( "Lectura de "+sNombre+" finalizada.\n");
		
	
		//logger.debug("tabla.size():|"+tabla.size()+"|");
		
        return resultadocarga;
	}

	public static ResultadoCarga leerImpuestosRevisadas(String sNombre) 
	{
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		ResultadoCarga resultadocarga;
		
		//logger.debug( "Fichero:|"+ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre+"|");

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		FileReader fr;

		long contador= 0 ;
		long registros = 0;
		int iSalida = 0;
		
		String sDuracion = "0";

		try 
		{
			long liTiempo = System.currentTimeMillis();
			
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			logger.debug("Leyendo fichero..");
			
			int iLongitudValida = Longitudes.IMPUESTOS_L-Longitudes.FILLER_IMPUESTOS_L-Longitudes.OBDEER_L;

			while((linea=br.readLine())!=null)
	        {
				contador++;

				logger.debug("Longitud de línea leida:|"+linea.length()+"|");
				logger.debug("Longitud de línea válida:|"+iLongitudValida+"|");
				

	    		if (linea.equals(sFinFichero))
	    		{
	    			contador--;
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			iSalida = -1;
	    			logger.error("Error en línea "+contador+", tamaño incorrecto.");
	    		}
	    		else
	    		{
	    			int iCodigo = CLImpuestos.actualizarImpuestoLeido(linea);

	    			String sResultado = "";
	    			String sDescripcion = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sDescripcion = "Movimiento validado.";
	    				sResultado = ValoresDefecto.DEF_CARGA_VALIDADO;
	    				break;
	    			case 1:
	    				sDescripcion = "Movimento de Impuesto pendiente de revisión.";
	    				sResultado = ValoresDefecto.DEF_CARGA_REVISAR;
	    				break;
	    			case -1:
	    				sDescripcion = "Registro no encontrado en el sistema.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -2:
	    				sDescripcion = "No existe relación con el Impuesto.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -3:
	    				sDescripcion = "[FATAL] Error al validar la reclación con el Impuestos.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -4:
	    				sDescripcion = "[FATAL] Error al registrar el movimiento pendiente.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;	 
	    			case -8:
	    				sDescripcion = "El activo no pertenece a la cartera.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -9:
	    				sDescripcion = "[FATAL] Acción desconocida.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -10:
	    				sDescripcion = "[FATAL] Estado del movimiento desconocido.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -11:
	    				sDescripcion = "[FATAL] El movimiento recibido figura como 'no enviado'.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			case -12:
	    				sDescripcion = "El movimiento recibido ya ha sido revisado.";
	    				sResultado = ValoresDefecto.DEF_CARGA_SINCAMBIOS;
	    				break;
	    			}

	    			String sMensaje = "["+sResultado+"] Línea "+contador+": "+sDescripcion;

	    			if ( iCodigo >= 0 )
	    			{
	    				logger.info(sMensaje);
	    				registros++;
	    			}
	    			else
	    			{
	    				iSalida = -1;
	    				logger.error(sMensaje);
	    			}

	    			ResultadosTabla resultadolectura = new ResultadosTabla(sNombre,contador,sResultado,sDescripcion);
	    			tabla.add(resultadolectura);
	    		}
	        }

			sDuracion = Utils.duracion(liTiempo,System.currentTimeMillis());
			
			br.close();
			
			//logger.debug("Registros procesados:|"+contador+"|");
			//logger.debug("Registros correctos:|"+registros+"|");
			
			if (iSalida != 0)
			{
				logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
			}
			
			logger.info("Duración de la carga: "+sDuracion);
		}
		catch (FileNotFoundException e)
		{
			logger.error("No se encontró el fichero recibido.");
			iSalida = -2;
		}
		catch (IOException e)
		{
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			iSalida = -3;
		}

		resultadocarga = new ResultadoCarga(iSalida,tabla,sNombre,sDuracion,contador,registros);
		
		logger.info( "Lectura de "+sNombre+" finalizada.\n");
		
	
		//logger.debug("tabla.size():|"+tabla.size()+"|");
		
        return resultadocarga;
	}
	
	public static ResultadoCarga leerCierresVolcados(String sNombre)
	{
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		ResultadoCarga resultadocarga;
		
		//logger.debug( "Fichero:|"+ValoresDefecto.DEF_PATH_BACKUP_CARGADOS+sNombre+"|");

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_CARGADOS+sNombre);
		
		FileReader fr;
		
		long contador= 0 ;
		long registros = 0;
		int iSalida = 0;
		
		String sDuracion = "0";
		
		try 
		{
			long liTiempo = System.currentTimeMillis();
			
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			//logger.debug("Leyendo fichero..");

			int iLongitudValida = Longitudes.CIERRE_L-Longitudes.FILLER_CIERRE_L;
			
			while((linea=br.readLine())!=null)
	        {
				contador++;

				//logger.debug("Longitud de línea leida:|"+linea.length()+"|");
				//logger.debug("Longitud de línea válida:|"+iLongitudValida+"|");

	    		if (linea.equals(sFinFichero))
	    		{
	    			contador--;
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			iSalida = -1;
	    			logger.error("Error en línea "+contador+", tamaño incorrecto.");
	    		}
	    		else
	    		{
	    			int iCodigo = CLProvisiones.inyertarCierreVolcado(linea);

	    			String sResultado = "";
	    			String sDescripcion = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sDescripcion = "Provisión asimilada correctamente.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ACTUALIZADO;
	    				break;
	    			case -1:
	    				sDescripcion = "La provisión ya se encuentra en el sistema.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERROR;
	    				break;
	    			case -2:
	    				sDescripcion = "[FATAL] Error al asimilar la Provisión.";
	    				sResultado = ValoresDefecto.DEF_CARGA_ERRORFATAL;
	    				break;
	    			}

	    			String sMensaje = "["+sResultado+"] Línea "+contador+": "+sDescripcion;

	    			if ( iCodigo >= 0 )
	    			{
	    				logger.info(sMensaje);
	    				registros++;
	    			}
	    			else
	    			{
	    				iSalida = -1;
	    				logger.error(sMensaje);
	    			}

	    			ResultadosTabla resultadolectura = new ResultadosTabla(sNombre,contador,sResultado,sDescripcion);
	    			tabla.add(resultadolectura);
	    		}
	        }
		
			sDuracion = Utils.duracion(liTiempo,System.currentTimeMillis());
			
			br.close();
			
			//logger.debug("Registros procesados:|"+contador+"|");
			//logger.debug("Registros correctos:|"+registros+"|");
			
			if (iSalida != 0)
			{
				logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
			}
			
			logger.info("Duración de la carga: "+sDuracion);
		}
		catch (FileNotFoundException e)
		{
			logger.error("No se encontró el fichero recibido.");
			iSalida = -2;
		}
		catch (IOException e)
		{
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			iSalida = -3;
		}

		resultadocarga = new ResultadoCarga(iSalida,tabla,sNombre,sDuracion,contador,registros);
		
		logger.info( "Lectura de "+sNombre+" finalizada.\n");
		
	
		//logger.debug("tabla.size():|"+tabla.size()+"|");
		
        return resultadocarga;
	}
	
	public static ResultadoCarga splitter(String sNombre, boolean bRecibido) 
	{
		int iCodigo = 0;
		
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		ResultadoCarga carga = new ResultadoCarga(iCodigo,tabla,"N/A","N/A",0,0);
		
		logger.debug("|"+sNombre+"|"+sNombre.substring(0, 3)+"|");
		
		
		if (sNombre.length() < 9)
		{
			iCodigo = 10;
			logger.error("El archivo suministrado no pertenece a esta subaplicacion INFOCAM.");
		}
		else 
		{
			String sNombreOriginal = sNombre.substring(sNombre.length()-9);
			
			if (sNombreOriginal.toUpperCase().matches("("+ValoresDefecto.DEF_COAPII+")(AC|RG|PA|GA|PP|E1|E2|E3|E4)(\\.TXT)$"))
			{

				String sTipo = sNombreOriginal.substring(3, 5).toUpperCase();

				logger.debug("Redirigiendo lectura...");

				logger.debug("Tipo:|"+sTipo+"|");

				ValoresDefecto.TIPOSFICHERO COSPII = ValoresDefecto.TIPOSFICHERO.valueOf(sTipo);

				switch (COSPII) {
				case AC:
					logger.debug("Activos");
					if (bRecibido)
					{
						carga = leerActivos(sNombre);
					}
					else
					{
						//Volcado no aceptado
						carga.setiCodigo(1);
					}
					break;
				case RG:
					logger.debug("Rechazados");
					if (bRecibido)
					{
						carga = leerGastosRevisados(sNombre);
					}
					else
					{
						//Volcado no aceptado
						carga.setiCodigo(2);
					}
					break;
				case PA:
					logger.debug("Autorizados");
					if (bRecibido)
					{
						carga = leerGastosRevisados(sNombre);
					}
					else
					{
						carga = leerGastosVolcados(sNombre);
						//Volcado no aceptado
						//carga.setiCodigo(3);
					}
					break;
				case GA:
					logger.debug("Gastos");
					if (bRecibido)
					{
						//Recepción no aceptada
						carga.setiCodigo(4);
					}
					else
					{
						carga = validarGastosVolcados(sNombre);
						//carga.setiCodigo(4);
					}
					break;
				case PP:
					logger.debug("Cierres");
					if (bRecibido)
					{
						//Recepción no aceptada
						carga.setiCodigo(5);
					}
					else
					{
						carga = leerCierresVolcados(sNombre);
					}
					break;
				case E1:
					logger.debug("Comunidades");
					if (bRecibido)
					{
						carga = leerComunidadesRevisadas(sNombre);
					}
					else
					{
						//inyectar
						carga = leerComunidadesVolcadas(sNombre);
					}
					break;
				case E2:
					logger.debug("Cuotas");
					if (bRecibido)
					{
						carga = leerCuotasRevisadas(sNombre);
					}
					else
					{
						//inyectar
						carga.setiCodigo(7);
					}
					break;
				case E3:
					logger.debug("Referencias Catastrales");
					if (bRecibido)
					{
						carga = leerReferenciasRevisadas(sNombre);
					}
					else
					{
						//inyectar
						carga.setiCodigo(8);
					}
					break;
				case E4:
					logger.debug("Impuestos");
					if (bRecibido)
					{
						carga = leerImpuestosRevisadas(sNombre);
					}
					else
					{
						//inyectar
						carga.setiCodigo(9);
					}
					break;
				default:
					logger.error("El archivo suministrado no coincide con el nombrado establecido:");
					logger.error("[*_]168XX.txt donde XX puede ser AC, RG, PA, GA, PP, E1, E2, E3 o E4. ");
					carga.setiCodigo(10);
					break;
				}

				logger.debug("Carga realizada.");

			} 
			else
			{
				carga.setiCodigo(10);
				logger.error("El archivo suministrado no pertenece a esta subaplicacion INFOCAM.");
				//lista = new ArrayList<String>();
			}
			

		}
		
		logger.debug("Codigo:|"+carga.getiCodigo()+"|");
		logger.debug("Procesados:|"+carga.getLiRegistrosProcesados()+"|");
		logger.debug("Correctos:|"+carga.getLiRegistrosCorrectos()+"|");
		logger.debug("Duracion:|"+carga.getsDuracion()+"|");
		
		//carga.setiCodigo(iCodigo);
		//carga = new Resultados(iCodigo,tabla);
		
		return carga;
	}
}
