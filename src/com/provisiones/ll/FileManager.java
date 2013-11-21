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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMProvisiones;

import com.provisiones.dal.qm.listas.QMListaComunidades;
import com.provisiones.dal.qm.listas.QMListaComunidadesActivos;
import com.provisiones.dal.qm.listas.QMListaCuotas;
import com.provisiones.dal.qm.listas.QMListaGastos;
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

import com.provisiones.types.Resultados;
import com.provisiones.types.Provision;
import com.provisiones.types.tablas.ResultadosTabla;

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

	public static String escribirComunidades() 
	{
		String sNombreFichero = "";

		Connection conexion = ConnectionManager.getDBConnection();

		if (conexion != null)
		{
			//Los movimientos de las comunidades estan repartidos entre las comunidades y los activos incluidos
			
			ArrayList<String> resultcomunidades = QMListaComunidades.getComunidadesPorEstado(conexion,ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
			ArrayList<String> resultactivos = QMListaComunidadesActivos.getMovimientosComunidadesActivoPorEstado(conexion,ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);

			ArrayList<String> resultcomunidadesactivos = new ArrayList<String>(resultcomunidades);
			
			resultcomunidadesactivos.addAll(resultactivos);
			
			HashSet<String> hslimpia = new HashSet<String>(resultcomunidadesactivos);

		   resultcomunidadesactivos.clear();
		   resultcomunidadesactivos.addAll(hslimpia);
			
			Collections.sort(resultcomunidadesactivos);

			sNombreFichero = ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+Utils.timeStamp()+"_"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E1+".txt";
			
			FileWriter ficheroE1 = null;
			
	        PrintWriter pw = null;
	        try
	        {
	        	
	        	ficheroE1 = new FileWriter(sNombreFichero);
	        	
	            pw = new PrintWriter(ficheroE1);

	            for (int i = 0; i < resultcomunidadesactivos.size() ; i++)
	            {
	                pw.println(Parser.escribirComunidad(CLComunidades.buscarMovimientoComunidad(resultcomunidadesactivos.get(i))));
	            }

	            pw.print(ValoresDefecto.DEF_FIN_FICHERO);

	            //TODO conexion.setAutoCommit(false);
	            for (int i = 0; i < resultcomunidades.size() ; i++)
	            {
	               QMListaComunidades.setValidado(conexion,resultcomunidades.get(i),ValoresDefecto.DEF_MOVIMIENTO_ENVIADO);
	            }
	            
	            for (int i = 0; i < resultactivos.size() ; i++)
	            {
	         	   QMListaComunidadesActivos.setValidado(conexion,resultactivos.get(i),ValoresDefecto.DEF_MOVIMIENTO_ENVIADO);
	            }
	        } 
	        catch (IOException e) 
	        {
	            sNombreFichero = "";
	            
	            //TODO conexion.rollback();
	            for (int i = 0; i < resultcomunidades.size() ; i++)
	            {
	               QMListaComunidades.setValidado(conexion,resultcomunidades.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
	            }
	            
	            for (int i = 0; i < resultcomunidadesactivos.size() ; i++)
	            {
	         	   QMListaComunidadesActivos.setValidado(conexion,resultactivos.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
	            }
	            
	            logger.error("Ocurrió un error al escribir en el fichero de envio, se restauran los estados afectados.");
	            
	        } 
	        finally 
	        {
	           try 
	           {
	        	   if (null != ficheroE1)
	        	   {
	        		   ficheroE1.close();
	        		   logger.debug("Generados!");
	        	   }
	        	   // distinguir con fich == null para hacer commit o rolback
	        	   //TODO conexion.commit(); + conexion.setAutoCommit(true);
	        		   
	           } 
	           catch (Exception e2) 
	           {
	              sNombreFichero = "";
	              
	              for (int i = 0; i < resultcomunidades.size() ; i++)
	              {
	                 QMListaComunidades.setValidado(conexion,resultcomunidades.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
	              }
	              
	              for (int i = 0; i < resultcomunidadesactivos.size() ; i++)
	              {
	           	   QMListaComunidadesActivos.setValidado(conexion,resultactivos.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
	              }
	              //TODO conexion.rollback();+ conexion.setAutoCommit(true);
	              logger.error("Ocurrió un error al cerrar el fichero de envio, se restauran los estados afectados.");
	           }
	        }
		}

        return sNombreFichero;
	}
	
	public static String escribirCuotas() 
	{
		String sNombreFichero = "";

		Connection conexion = ConnectionManager.getDBConnection();

		if (conexion != null)
		{
			ArrayList<String> resultcuotas =  QMListaCuotas.getCuotasPorEstado(conexion,ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
			
			FileWriter ficheroE2 = null;
			
	        PrintWriter pw = null;
	        
	        sNombreFichero = ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+Utils.timeStamp()+"_"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E2+".txt";
	        
	        try
	        {
	            ficheroE2 = new FileWriter(sNombreFichero);
	            pw = new PrintWriter(ficheroE2);
	            
	            // TODO conexion.setAutoCommit(false);
	            for (int i = 0; i < resultcuotas.size() ; i++)
	            {
	                pw.println(Parser.escribirCuota(QMMovimientosCuotas.getMovimientoCuota(conexion,resultcuotas.get(i))));
	                QMListaCuotas.setValidado(conexion,resultcuotas.get(i),ValoresDefecto.DEF_MOVIMIENTO_ENVIADO);
	            }
	            pw.print(ValoresDefecto.DEF_FIN_FICHERO);
	            
	        } 
	        catch (IOException e) 
	        {
	            //En caso de error se devuelven los registros a su estado anterior
	            sNombreFichero = "";
	            
	            //TODO conexion.rollback();
	            for (int i = 0; i < resultcuotas.size() ; i++)
	            {
	            	QMListaCuotas.setValidado(conexion,resultcuotas.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
	            }
	            
	            logger.error("Ocurrió un error al escribir en el fichero de envio, se restauran los estados afectados.");
	        } 
	        finally 
	        {
	           try 
	           {
	        	   if (null != ficheroE2)
	        	   {
	        		   ficheroE2.close();
	        		   logger.debug( "Generados!");
	        	   }
	        	 //TODO conexion.commit(); + conexion.setAutoCommit(true);
	            	   
	           } 
	           catch (Exception e2) 
	           {
	              //En caso de error se devuelven los registros a su estado anterior
	              sNombreFichero = "";
	              
	              //TODO conexion.rollback(); + conexion.setAutoCommit(true);
	              for (int i = 0; i < resultcuotas.size() ; i++)
	              {
	            	  QMListaCuotas.setValidado(conexion,resultcuotas.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
	              }
	              
	              logger.error("Ocurrió un error al cerrar el fichero de envio, se restauran los estados afectados.");
	              
	           }
	        }
		}
 
        return sNombreFichero;
	}
	
	public static String escribirReferencias() 
	{
		String sNombreFichero = "";

		Connection conexion = ConnectionManager.getDBConnection();

		if (conexion != null)
		{
			ArrayList<String> resultreferencias = QMListaReferencias.getReferenciasPorEstado(conexion,ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
			
			FileWriter ficheroE3 = null;
			
	        PrintWriter pw = null;
	        
	        sNombreFichero = ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+Utils.timeStamp()+"_"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E3+".txt";
	        try
	        {
	            ficheroE3 = new FileWriter(sNombreFichero);
	            pw = new PrintWriter(ficheroE3);
	            
	          //TODO conexion.setAutoCommit(false);
	            for (int i = 0; i < resultreferencias.size() ; i++)
	            {
	                pw.println(Parser.escribirReferenciaCatastral(QMMovimientosReferencias.getMovimientoReferenciaCatastral(conexion,resultreferencias.get(i))));
	                QMListaReferencias.setValidado(conexion,resultreferencias.get(i),ValoresDefecto.DEF_MOVIMIENTO_ENVIADO);
	            }
	            pw.print(ValoresDefecto.DEF_FIN_FICHERO);
	            
	        } 
	        catch (IOException e) 
	        {
	            //En caso de error se devuelven los registros a su estado anterior
	            sNombreFichero = "";
	          //TODO conexion.rollback();
	            for (int i = 0; i < resultreferencias.size() ; i++)
	            {
	            	QMListaReferencias.setValidado(conexion,resultreferencias.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
	            }
	            
	            logger.error("Ocurrió un error al escribir en el fichero de envio, se restauran los estados afectados.");
	        } 
	        finally 
	        {
	           try 
	           {
	               if (null != ficheroE3)
	               {
	            	   ficheroE3.close();
	            	   logger.debug( "Generados!");
	            	 //TODO conexion.commit(); + conexion.setAutoCommit(true);
	               }
	           } 
	           catch (Exception e2) 
	           {
	              //En caso de error se devuelven los registros a su estado anterior
	              sNombreFichero = "";
	              
	              for (int i = 0; i < resultreferencias.size() ; i++)
	              {
	              	QMListaReferencias.setValidado(conexion,resultreferencias.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
	              }
	              
	              logger.error("Ocurrió un error al cerrar el fichero de envio, se restauran los estados afectados.");
	           }
	        }
		}
        
        return sNombreFichero;
	}
	
	public static String escribirImpuestos() 
	{
		String sNombreFichero = "";

		Connection conexion = ConnectionManager.getDBConnection();

		if (conexion != null)
		{
			ArrayList<String> resultimpuestos = QMListaImpuestos.getImpuestosPorEstado(conexion,ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
			
			FileWriter ficheroE4 = null;
			
	        PrintWriter pw = null;
	        
	        sNombreFichero = ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+Utils.timeStamp()+"_"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E4+".txt";
	        
	        try
	        {
	            ficheroE4 = new FileWriter(sNombreFichero);
	            pw = new PrintWriter(ficheroE4);
	            
	            for (int i = 0; i < resultimpuestos.size() ; i++)
	            {
	                pw.println(Parser.escribirImpuestoRecurso(QMMovimientosImpuestos.getMovimientoImpuestoRecurso(conexion,resultimpuestos.get(i))));
	                QMListaImpuestos.setValidado(conexion,resultimpuestos.get(i),ValoresDefecto.DEF_MOVIMIENTO_ENVIADO);
	            }
	            pw.print(ValoresDefecto.DEF_FIN_FICHERO);
	        } 
	        catch (IOException e) 
	        {
	            
	            
	            //En caso de error se devuelven los registros a su estado anterior
	            sNombreFichero = "";
	            
	            for (int i = 0; i < resultimpuestos.size() ; i++)
	            {
	            	QMListaImpuestos.setValidado(conexion,resultimpuestos.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
	            }
	            
	            logger.error("Ocurrió un error al escribir en el fichero de envio, se restauran los estados afectados.");
	        } 
	        finally 
	        {
	           try 
	           {
	               if (null != ficheroE4)
	               {
	            	   ficheroE4.close();
	            	   logger.debug( "Generados!");
	               }
	           } 
	           catch (Exception e2) 
	           {
	              //En caso de error se devuelven los registros a su estado anterior
	              sNombreFichero = "";
	              
	              for (int i = 0; i < resultimpuestos.size() ; i++)
	              {
	              	QMListaImpuestos.setValidado(conexion,resultimpuestos.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
	              }
	              
	              logger.error("Ocurrió un error al cerrar el fichero de envio, se restauran los estados afectados.");
	              
	           }
	        }
		}
        
        return sNombreFichero;
	}
	
	public static String escribirGastos() 
	{
		String sNombreFichero = "";

		Connection conexion = ConnectionManager.getDBConnection();

		if (conexion != null)
		{
			ArrayList<String> resultgastos = QMListaGastos.getGastosPorEstado(conexion,ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
	        
	        FileWriter ficheroGA = null;
	        
	        PrintWriter pw = null;
	        
	        sNombreFichero = ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+Utils.timeStamp()+"_"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_GA+".txt";
	        
	        try
	        {

	            ficheroGA = new FileWriter(sNombreFichero);
	            pw = new PrintWriter(ficheroGA);
	            
	            for (int i = 0; i < resultgastos.size(); i++)
	            {
	                pw.println(Parser.escribirGasto(QMMovimientosGastos.getMovimientoGasto(conexion,resultgastos.get(i))));
	                QMListaGastos.setValidado(conexion,resultgastos.get(i),ValoresDefecto.DEF_MOVIMIENTO_ENVIADO);
	            }
	            pw.print(ValoresDefecto.DEF_FIN_FICHERO);
	 
	        } 
	        catch (IOException e) 
	        {
	            //En caso de error se devuelven los registros a su estado anterior
	            sNombreFichero = "";
	            
	            for (int i = 0; i < resultgastos.size() ; i++)
	            {
	            	QMListaImpuestos.setValidado(conexion,resultgastos.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
	            }
	            
	            logger.error("Ocurrió un error al escribir en el fichero de envio, se restauran los estados afectados.");
			} 

	        finally 
	        {
	           try 
	           {

	        	   if (null != ficheroGA)
	        	   {
	        		   ficheroGA.close();
	        		   logger.debug("Generados!");
	        	   }
	              
	           } 
	           catch (Exception e2) 
	           {
	              e2.printStackTrace();
	              //En caso de error se devuelven los registros a su estado anterior
	              sNombreFichero = "";
	              
	              for (int i = 0; i < resultgastos.size() ; i++)
	              {
	              	QMListaImpuestos.setValidado(conexion,resultgastos.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
	              }
	              
	              logger.error("Ocurrió un error al cerrar el fichero de envio, se restauran los estados afectados.");
	           }
	        }
		}
        
        return sNombreFichero;
	}

	public static String escribirCierres() 
	{
		String sNombreFichero = "";

		Connection conexion = ConnectionManager.getDBConnection();

		if (conexion != null)
		{
			ArrayList<String> resultcierres = QMProvisiones.getProvisionesCerradasPendientes(conexion);
	        
	        FileWriter ficheroPP = null;
	        
	        PrintWriter pw = null;
	        
	        sNombreFichero = ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+Utils.timeStamp()+"_"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_PP+".txt";
	        
	        try
	        {

	        	ficheroPP = new FileWriter(sNombreFichero);
	            pw = new PrintWriter(ficheroPP);
	            
	            for (int i = 0; i < resultcierres.size(); i++)
	            {
	            	Provision provision = QMProvisiones.getProvision(conexion,resultcierres.get(i));
	                pw.println(Parser.escribirCierre(provision.getsNUPROF(),provision.getsFEPFON()));
	                QMProvisiones.setFechaEnvio(conexion,resultcierres.get(i),Utils.fechaDeHoy(false));
	            }
	            pw.print(ValoresDefecto.DEF_FIN_FICHERO);
	 
	        } 
	        catch (IOException e) 
	        {
	            //En caso de error se devuelven los registros a su estado anterior
	            sNombreFichero = "";
	            
	            for (int i = 0; i < resultcierres.size() ; i++)
	            {
	            	QMProvisiones.setFechaEnvio(conexion,resultcierres.get(i),"0");
	            }
	            
	            logger.error("Ocurrió un error al escribir en el fichero de envio, se restauran los estados afectados.");
			} 

	        finally 
	        {
	           try 
	           {

	        	   if (null != ficheroPP)
	        	   {
	        		   ficheroPP.close();
	        		   logger.debug( "Generados!");
	        	   }
	              
	           } 
	           catch (Exception e2) 
	           {
	              e2.printStackTrace();
	              //En caso de error se devuelven los registros a su estado anterior
	              sNombreFichero = "";
	              
	              for (int i = 0; i < resultcierres.size() ; i++)
	              {
	            	  QMProvisiones.setFechaEnvio(conexion,resultcierres.get(i),"0");
	              }
	              
	              logger.error("Ocurrió un error al cerrar el fichero de envio, se restauran los estados afectados.");
	           }
	        }
		}
        
        return sNombreFichero;
	}
	
	public static ArrayList<ResultadosTabla> leerActivos(String sNombre)
	{
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		logger.debug( "Fichero:|"+ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre+"|");

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		FileReader fr;
		try 
		{
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			
			/*logger.debug("Leyendo fichero..");

			java.util.Date date= new java.util.Date();
			
			String sTiempo = (new Timestamp(date.getTime())).toString();*/
			
			logger.debug("Leyendo fichero..");

			long liTiempo = System.currentTimeMillis();

			int contador= 0 ;
			int registros = 0;
			
			int iLongitudValida = Longitudes.ACTIVOS_L-Longitudes.FILLER_ACTIVOS_L-Longitudes.OBDEER_L;
			
			while((linea=br.readLine())!=null)
	        {
				contador++;

				logger.debug("Longitud de línea leida:|"+linea.length()+"|");
				logger.debug("Longitud de línea válida:|"+iLongitudValida+"|");

	    		if (linea.equals(sFinFichero))
	    		{
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			logger.error("Error en línea "+contador+", tamaño incorrecto.");
	    		}
	    		else
	    		{
	    			int iCodigo = CLActivos.actualizaActivoLeido(linea);
	    			String sMensaje = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sMensaje = "Nuevo Activo registrado.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_NUEVO+"Línea "+contador+": "+sMensaje;
	    				logger.info(sMensaje);
	    				break;
	    			case -1:
	    				sMensaje = "[FATAL] Error al actualizar el Activo.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -2:
	    				sMensaje = "[FATAL] Error al registrar el Activo.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case 1:
	    				sMensaje = "Activo actualizado.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ACTUALIZADO+"Línea "+contador+": "+sMensaje;
	    				logger.info(sMensaje);
	    				break;
	    			case 2:
	    				sMensaje = "El registro ya se encuentra en el sistema.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.warn(sMensaje);
	    				break;
	    			}
	    			if ( iCodigo >= 0 )
	    			{
	    				registros++;
	    			}

	    			ResultadosTabla resultado = new ResultadosTabla(sNombre, sMensaje);
	    			tabla.add(resultado);
	    		}
	        }
		
			br.close();

			logger.info( "Lectura de "+sNombre+" finalizada.\n");

			logger.info("Duración de la carga: "+Utils.duracion(liTiempo,System.currentTimeMillis()));
			
			logger.debug("Registros procesados:|"+contador+"|");
			logger.debug("Registros correctos:|"+registros+"|");

			logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
		}
		catch (FileNotFoundException e)
		{
			logger.error("No se encontró el fichero recibido.");
		}
		catch (IOException e)
		{
			logger.error("Ocurrió un error al acceder al fichero recibido.");
		}
		logger.debug("tabla.size():|"+tabla.size()+"|");
		
        return tabla;
	}

	public static ArrayList<ResultadosTabla> leerGastosRevisados(String sNombre) 
	{
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		logger.debug( "Fichero:|"+ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre+"|");

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		FileReader fr;

		try 
		{
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			
			logger.debug("Leyendo fichero..");

			long liTiempo = System.currentTimeMillis();

			int contador= 0 ;
			int registros = 0;
			
			int iLongitudValida = Longitudes.GASTOS_L-Longitudes.FILLER_GASTOS_L-Longitudes.OBDEER_L;
			
			while((linea=br.readLine())!=null)
	        {
				contador++;

				logger.debug("Longitud de línea leida:|"+linea.length()+"|");
				
				logger.debug("Longitud de línea válida:|"+iLongitudValida+"|");

	    		if (linea.equals(sFinFichero))
	    		{
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			logger.error("Error en línea "+contador+", tamaño incorrecto.");
	    		}
	    		else
	    		{
	    			int iCodigo = CLGastos.actualizarGastoRecibido(linea);
	    			
	    			String sMensaje = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sMensaje = "Movimiento validado.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_VALIDADO+"Línea "+contador+": "+sMensaje;
	    				logger.info(sMensaje);
	    				break;
	    			case 1:
	    				sMensaje = "Movimiento de Gasto pendiente de revisión.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_REVISAR+"(E) Línea "+contador+": "+sMensaje;
	    				logger.info(sMensaje);
	    				break;
	    			case -1:
	    				sMensaje = "Registro no encontrado en el sistema.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -2:
	    				sMensaje = "No existe relación con la Gasto.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -3:
	    				sMensaje = "[FATAL] Error al validar la relación con el Gasto.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -4:
	    				sMensaje = "[FATAL] Error al registrar el movimiento pendiente.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -5:
	    				sMensaje = "[FATAL] Error al actualizar la revisión del gasto.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;	
	    			case -10:
	    				sMensaje = "[FATAL] Estado del movimiento desconocido.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -11:
	    				sMensaje = "[FATAL] El movimiento recibido figura como 'no enviado'.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -12:
	    				sMensaje = "El movimiento recibido ya ha sido revisado.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.warn(sMensaje);
	    				break;
	    			}
	    			
	    			if ( iCodigo >= 0 )
	    			{
	    				registros++;
	    			}

	    			ResultadosTabla resultado = new ResultadosTabla(sNombre, sMensaje);
	    			tabla.add(resultado);
	    		}
	        }
		
			br.close();
		
			logger.info( "Lectura de "+sNombre+" finalizada.\n");

			logger.info("Duración de la carga: "+Utils.duracion(liTiempo,System.currentTimeMillis()));
			
			logger.debug("Registros procesados:|"+contador+"|");
			logger.debug("Registros correctos:|"+registros+"|");

			logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
		}
		catch (FileNotFoundException e)
		{
			logger.error("No se encontró el fichero recibido.");
		}
		catch (IOException e)
		{
			logger.error("Ocurrió un error al acceder al fichero recibido.");
		}
		logger.debug("tabla.size():|"+tabla.size()+"|");
		
        return tabla;
	}
	
	public static ArrayList<ResultadosTabla> leerGastosVolcados(String sNombre) 
	{
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		logger.debug( "Fichero:|"+ValoresDefecto.DEF_PATH_BACKUP_CARGADOS+sNombre+"|");

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_CARGADOS+sNombre);
		
		FileReader fr;

		try 
		{
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			
			logger.debug("Leyendo fichero..");

			long liTiempo = System.currentTimeMillis();

			int contador= 0 ;
			int registros = 0;
			
			int iLongitudValida = Longitudes.GASTOS_L-Longitudes.FILLER_GASTOS_L-Longitudes.OBDEER_L;
			
			while((linea=br.readLine())!=null)
	        {
				contador++;

				logger.debug("Longitud de línea leida:|"+linea.length()+"|");
				
				logger.debug("Longitud de línea válida:|"+iLongitudValida+"|");

	    		if (linea.equals(sFinFichero))
	    		{
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			logger.error("Error en línea "+contador+", tamaño incorrecto.");
	    		}
	    		else
	    		{
	    			int iCodigo = CLGastos.inyectarGastoVolcado(linea);
	    			
	    			String sMensaje = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sMensaje = "Movimiento asimilado.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_NUEVO+"Línea "+contador+": "+sMensaje;
	    				logger.info(sMensaje);
	    				break;
	    			case -1:
	    				sMensaje = "[FATAL] Error al crear la Provisión del Gasto.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -2:
	    				sMensaje = "[FATAL] Error al crear el Gasto.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -3:
	    				sMensaje = "El movimiento cargado ya se encontraba en el sistema.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -4:
	    				sMensaje = "[FATAL] Error al registrar el movimiento.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -5:
	    				sMensaje = "[FATAL] Error al crear la relación del Gasto.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;	
	    			case -6:
	    				sMensaje = "[FATAL] Error al crear la relación del Gasto con la Provisión.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			}
	    			
	    			if ( iCodigo >= 0 )
	    			{
	    				registros++;
	    			}

	    			ResultadosTabla resultado = new ResultadosTabla(sNombre, sMensaje);
	    			tabla.add(resultado);
	    		}
	        }
		
			br.close();
		
			logger.info( "Lectura de "+sNombre+" finalizada.\n");

			logger.info("Duración de la carga: "+Utils.duracion(liTiempo,System.currentTimeMillis()));
			
			logger.debug("Registros procesados:|"+contador+"|");
			logger.debug("Registros correctos:|"+registros+"|");

			logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
		}
		catch (FileNotFoundException e)
		{
			logger.error("No se encontró el fichero recibido.");
		}
		catch (IOException e)
		{
			logger.error("Ocurrió un error al acceder al fichero recibido.");
		}
		logger.debug("tabla.size():|"+tabla.size()+"|");
		
        return tabla;
		
	
	}

	public static ArrayList<ResultadosTabla> leerComunidadesRevisadas(String sNombre)
	{
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		logger.debug( "Fichero:|"+ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre+"|");

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		FileReader fr;
		try 
		{
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			
			logger.debug("Leyendo fichero..");

			long liTiempo = System.currentTimeMillis();

			int contador= 0 ;
			int registros = 0;
			
			int iLongitudValida = Longitudes.COMUNIDADES_L-Longitudes.FILLER_COMUNIDADES_L-Longitudes.OBDEER_L;
				
			while((linea=br.readLine())!=null)
	        {
				contador++;

				logger.debug("Longitud de línea leida:|"+linea.length()+"|");
				logger.debug("Longitud de línea válida:|"+iLongitudValida+"|");

	    		if (linea.equals(sFinFichero))
	    		{
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			logger.error("Error en línea "+contador+", tamaño incorrecto.");
	    		}
	    		else
	    		{
	    			int iCodigo = CLComunidades.actualizarComunidadLeida(linea);
	    			String sMensaje = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sMensaje = "Movimiento validado.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_VALIDADO+"Línea "+contador+": "+sMensaje;
	    				logger.info(sMensaje);
	    				break;
	    			case 1:
	    				sMensaje = "Movimento de Comunidad pendiente de revisión.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_REVISAR+"Línea "+contador+": "+sMensaje;
	    				logger.info(sMensaje);
	    				break;
	    			case -1:
	    				sMensaje = "Registro no encontrado en el sistema.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -2:
	    				sMensaje = "No existe relación con la Comunidad.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -3:
	    				sMensaje = "No existe relación Activo-Comunidad.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -4:
	    				sMensaje = "[FATAL] Error al validar la reclación con la Comunidad.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -5:
	    				sMensaje = "[FATAL] Error al validar la relación Activo-Comunidad.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -6:
	    				sMensaje = "[FATAL] Error al registrar el movimiento pendiente.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -9:
	    				sMensaje = "[FATAL] Accion desconocida.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -10:
	    				sMensaje = "[FATAL] Estado del movimiento desconocido.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -11:
	    				sMensaje = "[FATAL] El movimiento recibido figura como 'no enviado'.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -12:
	    				sMensaje = "El movimiento recibido ya ha sido revisado.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.warn(sMensaje);
	    				break;
	    			}
	    			if ( iCodigo >= 0 )
	    			{
	    				registros++;
	    			}

	    			ResultadosTabla resultado = new ResultadosTabla(sNombre, sMensaje);
	    			tabla.add(resultado);
	    		}
	        }
		
			br.close();
		
			logger.info( "Lectura de "+sNombre+" finalizada.\n");

			logger.info("Duración de la carga: "+Utils.duracion(liTiempo,System.currentTimeMillis()));
			
			logger.debug("Registros procesados:|"+contador+"|");
			logger.debug("Registros correctos:|"+registros+"|");

			logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
		}
		catch (FileNotFoundException e)
		{
			logger.error("No se encontró el fichero recibido.");
		}
		catch (IOException e)
		{
			logger.error("Ocurrió un error al acceder al fichero recibido.");
		}
		logger.debug("tabla.size():|"+tabla.size()+"|");
		
        return tabla;
	}
	
	public static ArrayList<ResultadosTabla> leerCuotasRevisadas(String sNombre) 
	{
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		logger.debug( "Fichero:|"+ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre+"|");

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		FileReader fr;
		try 
		{
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			
			logger.debug("Leyendo fichero..");

			long liTiempo = System.currentTimeMillis();

			int contador= 0 ;
			int registros = 0;
			
			int iLongitudValida = Longitudes.CUOTAS_L-Longitudes.FILLER_CUOTAS_L-Longitudes.OBDEER_L;
			
			while((linea=br.readLine())!=null)
	        {
				contador++;

				logger.debug("Longitud de línea leida:|"+linea.length()+"|");
				logger.debug("Longitud de línea válida:|"+iLongitudValida+"|");

	    		if (linea.equals(sFinFichero))
	    		{
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			logger.error("Error en línea "+contador+", tamaño incorrecto.");
	    		}
	    		else
	    		{
	    			int iCodigo = CLCuotas.actualizarCuotaLeida(linea);
	    			String sMensaje = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sMensaje = "Movimiento validado.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.info(sMensaje);
	    				break;
	    			case 1:
	    				sMensaje = "Movimento de Cuota pendiente de revisión.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.info(sMensaje);
	    				break;
	    			case -1:
	    				sMensaje = "Registro no encontrado en el sistema.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -2:
	    				sMensaje = "No existe relación con la Cuota.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -3:
	    				sMensaje = "[FATAL] Error al validar la reclación con la Cuota.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -4:
	    				sMensaje = "[FATAL] Error al registrar el movimiento pendiente.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;	    				
	    			case -9:
	    				sMensaje = "[FATAL] Accion desconocida.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -10:
	    				sMensaje = "[FATAL] Estado del movimiento desconocido.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -11:
	    				sMensaje = "[FATAL] El movimiento recibido figura como 'no enviado'.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -12:
	    				sMensaje = "El movimiento recibido ya ha sido revisado.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.warn(sMensaje);
	    				break;
	    			}
	    			if ( iCodigo >= 0 )
	    			{
	    				registros++;
	    			}

	    			ResultadosTabla resultado = new ResultadosTabla(sNombre, sMensaje);
	    			tabla.add(resultado);
	    		}
	        }
		
			br.close();
		
			logger.info( "Lectura de "+sNombre+" finalizada.\n");

			logger.info("Duración de la carga: "+Utils.duracion(liTiempo,System.currentTimeMillis()));
			
			logger.debug("Registros procesados:|"+contador+"|");
			logger.debug("Registros correctos:|"+registros+"|");

			logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
		}
		catch (FileNotFoundException e)
		{
			logger.error("No se encontró el fichero recibido.");
		}
		catch (IOException e)
		{
			logger.error("Ocurrió un error al acceder al fichero recibido.");
		}
		logger.debug("tabla.size():|"+tabla.size()+"|");
		
        return tabla;
	}
	
	public static ArrayList<ResultadosTabla> leerReferenciasRevisadas(String sNombre) 
	{
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		logger.debug( "Fichero:|"+ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre+"|");

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		FileReader fr;
		try 
		{
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			
			logger.debug("Leyendo fichero..");

			long liTiempo = System.currentTimeMillis();

			int contador= 0 ;
			int registros = 0;
			
			int iLongitudValida = Longitudes.REFERENCIAS_L-Longitudes.FILLER_REFERENCIAS_L-Longitudes.OBDEER_L;

			while((linea=br.readLine())!=null)
	        {
				contador++;

				logger.debug("Longitud de línea leida:|"+linea.length()+"|");
				logger.debug("Longitud de línea válida:|"+iLongitudValida+"|");

	    		if (linea.equals(sFinFichero))
	    		{
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			logger.error("Error en línea "+contador+", tamaño incorrecto.");
	    		}
	    		else
	    		{
	    			int iCodigo = CLReferencias.actualizaReferenciaLeida(linea);

	    			String sMensaje = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sMensaje = "Movimiento validado.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.info(sMensaje);
	    				break;
	    			case 1:
	    				sMensaje = "Movimento de Referencia Catastral pendiente de revisión.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.info(sMensaje);
	    				break;
	    			case -1:
	    				sMensaje = "Registro no encontrado en el sistema.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -2:
	    				sMensaje = "No existe relación con la Referencia Catastral.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -3:
	    				sMensaje = "[FATAL] Error al validar la reclación con la Referencia Catastral.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -4:
	    				sMensaje = "[FATAL] Error al registrar el movimiento pendiente.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;	    				
	    			case -9:
	    				sMensaje = "[FATAL] Accion desconocida.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -10:
	    				sMensaje = "[FATAL] Estado del movimiento desconocido.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -11:
	    				sMensaje = "[FATAL] El movimiento recibido figura como 'no enviado'.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -12:
	    				sMensaje = "El movimiento recibido ya ha sido revisado.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.warn(sMensaje);
	    				break;
	    			}
	    			
	    			if ( iCodigo >= 0 )
	    			{
	    				registros++;
	    			}

	    			ResultadosTabla resultado = new ResultadosTabla(sNombre, sMensaje);
	    			tabla.add(resultado);
	    		}
	        }
		
			br.close();
		
			logger.info( "Lectura de "+sNombre+" finalizada.\n");

			logger.info("Duración de la carga: "+Utils.duracion(liTiempo,System.currentTimeMillis()));
			
			logger.debug("Registros procesados:|"+contador+"|");
			logger.debug("Registros correctos:|"+registros+"|");

			logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
		}
		catch (FileNotFoundException e)
		{
			logger.error("No se encontró el fichero recibido.");
		}
		catch (IOException e)
		{
			logger.error("Ocurrió un error al acceder al fichero recibido.");
		}
		logger.debug("tabla.size():|"+tabla.size()+"|");
		
        return tabla;
	}

	public static ArrayList<ResultadosTabla> leerImpuestosRevisadas(String sNombre) 
	{
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		logger.debug( "Fichero:|"+ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre+"|");

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		FileReader fr;

		try 
		{
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			
			logger.debug("Leyendo fichero..");

			long liTiempo = System.currentTimeMillis();

			int contador= 0 ;
			int registros = 0;
			
			int iLongitudValida = Longitudes.IMPUESTOS_L-Longitudes.FILLER_IMPUESTOS_L-Longitudes.OBDEER_L;

			while((linea=br.readLine())!=null)
	        {
				contador++;

				logger.debug("Longitud de línea leida:|"+linea.length()+"|");
				logger.debug("Longitud de línea válida:|"+iLongitudValida+"|");
				

	    		if (linea.equals(sFinFichero))
	    		{
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			logger.error("Error en línea "+contador+", tamaño incorrecto.");
	    		}
	    		else
	    		{
	    			int iCodigo = CLImpuestos.actualizarImpuestoLeido(linea);

	    			String sMensaje = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sMensaje = "Movimiento validado.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.info(sMensaje);
	    				break;
	    			case 1:
	    				sMensaje = "Movimento de Impuesto pendiente de revisión.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.info(sMensaje);
	    				break;
	    			case -1:
	    				sMensaje = "Registro no encontrado en el sistema.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -2:
	    				sMensaje = "No existe relación con el Impuesto.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -3:
	    				sMensaje = "[FATAL] Error al validar la reclación con el Impuestos.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -4:
	    				sMensaje = "[FATAL] Error al registrar el movimiento pendiente.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;	    				
	    			case -9:
	    				sMensaje = "[FATAL] Accion desconocida.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -10:
	    				sMensaje = "[FATAL] Estado del movimiento desconocido.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -11:
	    				sMensaje = "[FATAL] El movimiento recibido figura como 'no enviado'.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -12:
	    				sMensaje = "El movimiento recibido ya ha sido revisado.";
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				logger.warn(sMensaje);
	    				break;
	    			}
	    			if ( iCodigo >= 0 )
	    			{
	    				registros++;
	    			}

	    			ResultadosTabla resultado = new ResultadosTabla(sNombre, sMensaje);
	    			tabla.add(resultado);
	    		}
	        }
		
			br.close();
		
			logger.info( "Lectura de "+sNombre+" finalizada.\n");

			logger.info("Duración de la carga: "+Utils.duracion(liTiempo,System.currentTimeMillis()));
			
			logger.debug("Registros procesados:|"+contador+"|");
			logger.debug("Registros correctos:|"+registros+"|");

			logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
		}
		catch (FileNotFoundException e)
		{
			logger.error("No se encontró el fichero recibido.");
		}
		catch (IOException e)
		{
			logger.error("Ocurrió un error al acceder al fichero recibido.");
		}
		logger.debug("tabla.size():|"+tabla.size()+"|");
		
        return tabla;

	}
	
	public static ArrayList<ResultadosTabla> leerCierresVolcados(String sNombre)
	{
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		logger.debug( "Fichero:|"+ValoresDefecto.DEF_PATH_BACKUP_CARGADOS+sNombre+"|");

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_CARGADOS+sNombre);
		
		FileReader fr;
		try 
		{
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			logger.debug("Leyendo fichero..");

			long liTiempo = System.currentTimeMillis();

			int contador= 0 ;
			int registros = 0;
			
			int iLongitudValida = Longitudes.CIERRE_L-Longitudes.FILLER_CIERRE_L;
			
			while((linea=br.readLine())!=null)
	        {
				contador++;

				logger.debug("Longitud de línea leida:|"+linea.length()+"|");
				logger.debug("Longitud de línea válida:|"+iLongitudValida+"|");

	    		if (linea.equals(sFinFichero))
	    		{
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			logger.error("Error en línea "+contador+", tamaño incorrecto.");
	    		}
	    		else
	    		{
	    			int iCodigo = CLProvisiones.inyertarCierreVolcado(linea);
	    			String sMensaje = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sMensaje = "Provisión cerrada.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_NUEVO+"Línea "+contador+": "+sMensaje;
	    				logger.info(sMensaje);
	    				break;
	    			case -1:
	    				sMensaje = "La provisión no se encuentra en el sistema.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			case -2:
	    				sMensaje = "[FATAL] Error al cerrar la Provisión.";
	    				sMensaje = ValoresDefecto.DEF_CARGA_ERROR+"Línea "+contador+": "+sMensaje;
	    				logger.error(sMensaje);
	    				break;
	    			}
	    			if ( iCodigo >= 0 )
	    			{
	    				registros++;
	    			}

	    			ResultadosTabla resultado = new ResultadosTabla(sNombre, sMensaje);
	    			tabla.add(resultado);
	    		}
	        }
		
			br.close();

			logger.info( "Lectura de "+sNombre+" finalizada.\n");

			logger.info("Duración de la carga: "+Utils.duracion(liTiempo,System.currentTimeMillis()));
			
			logger.debug("Registros procesados:|"+contador+"|");
			logger.debug("Registros correctos:|"+registros+"|");

			logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
		}
		catch (FileNotFoundException e)
		{
			logger.error("No se encontró el fichero recibido.");
		}
		catch (IOException e)
		{
			logger.error("Ocurrió un error al acceder al fichero recibido.");
		}
		logger.debug("tabla.size():|"+tabla.size()+"|");
		
        return tabla;
	}
	
	public static Resultados splitter(String sNombre, boolean bRecibido) 
	{
		Resultados carga = null;

		int iCodigo = 0;
		
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		logger.debug("|"+sNombre+"|"+sNombre.substring(0, 3)+"|");
		
		
		if (sNombre.length() < 9)
		{
			iCodigo = -10;
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
						tabla = leerActivos(sNombre);

						if (tabla.size() == 0)	
						{
							iCodigo = -1;
						}
					}
					else
					{
						//Volcado no aceptado
						iCodigo = -1;
					}
					break;
				case RG:
					logger.debug("Rechazados");
					if (bRecibido)
					{
						tabla = leerGastosRevisados(sNombre);

						if (tabla.size() == 0)					
						{
							iCodigo = -2;
						}
					}
					else
					{
						//Volcado no aceptado
						iCodigo = -2;
					}
					break;
				case PA:
					logger.debug("Autorizados");
					if (bRecibido)
					{
						tabla = leerGastosRevisados(sNombre);

						if (tabla.size() == 0)					
						{
							iCodigo = -3;
						}
					}
					else
					{
						//Volcado no aceptado
						iCodigo = -3;
					}
					break;
				case GA:
					logger.debug("Gastos");
					if (bRecibido)
					{
						//Recepción no aceptada
						iCodigo = -4;
					}
					else
					{
						tabla = leerGastosVolcados(sNombre);

						if (tabla.size() == 0)	
						{
							iCodigo = -4;
						}
					}
					break;
				case PP:
					logger.debug("Cierres");
					if (bRecibido)
					{
						//Recepción no aceptada
						iCodigo = -5;
					}
					else
					{
						tabla = leerCierresVolcados(sNombre);

						if (tabla.size() == 0)	
						{
							iCodigo = -5;
						}
					}
					break;
				case E1:
					logger.debug("Comunidades");
					if (bRecibido)
					{
						tabla = leerComunidadesRevisadas(sNombre);

						if (tabla.size() == 0)
						{
							iCodigo = -6;
						}						
					}
					else
					{
						//inyectar
						iCodigo = -6;
					}
					break;
				case E2:
					logger.debug("Cuotas");
					if (bRecibido)
					{
						tabla = leerCuotasRevisadas(sNombre);

						if (tabla.size() == 0)
						{
							iCodigo = -7;
						}						
					}
					else
					{
						//inyectar
						iCodigo = -7;
					}
					break;
				case E3:
					logger.debug("Referencias Catastrales");
					if (bRecibido)
					{
						tabla = leerReferenciasRevisadas(sNombre);

						if (tabla.size() == 0)
						{
							iCodigo = -8;
						}						
					}
					else
					{
						//inyectar
						iCodigo = -8;
					}
					break;
				case E4:
					logger.debug("Impuestos");
					if (bRecibido)
					{
						tabla = leerImpuestosRevisadas(sNombre);

						if (tabla.size() == 0)
						{
							iCodigo = -9;
						}						
					}
					else
					{
						//inyectar
						iCodigo = -9;
					}
					break;
				default:
					logger.error("El archivo suministrado no coincide con el nombrado establecido:");
					logger.error("[*_]168XX.txt donde XX puede ser AC, RG, PA, GA, PP, E1, E2, E3 o E4. ");
					iCodigo = -10;
					break;
				}
				logger.debug("tabla.size():|"+tabla.size()+"|");
				logger.debug("Operativa completa.");

			} 
			else
			{
				iCodigo = -1;
				logger.error("El archivo suministrado no pertenece a esta subaplicacion INFOCAM.");
				//lista = new ArrayList<String>();
			}
			

		}
		
		//carga.setiCodigo(iCodigo);
		carga = new Resultados(iCodigo,tabla);
		
		return carga;
	}
}
