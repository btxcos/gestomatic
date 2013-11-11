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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

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

public class FileManager 
{

	private static Logger logger = LoggerFactory.getLogger(FileManager.class.getName());
	
	public static String guardarFichero(FileUploadEvent event)
	{
		logger.debug("ID:|{}|",event.getPhaseId().getOrdinal());
	
		logger.debug("Guardando archivo...");
        UploadedFile file = event.getFile();
        
        
        
        String sFichero = Utils.timeStamp()+"_"+file.getFileName();

        InputStream is;
		try 
		{
			is = file.getInputstream();
	        OutputStream out = new FileOutputStream(ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sFichero);
	        
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
			// TODO Auto-generated catch block
			logger.error("Error al guardar el fichero recibido.");
			e.printStackTrace();
		}
        logger.debug("Completado con exito.");
        return sFichero;
	}

	public static String escribirComunidades() 
	{
		//Los movimientos de las comunidades estan repartidos entre las comunidades y los activos incluidos
		
		ArrayList<String> resultcomunidades = QMListaComunidades.getComunidadesPorEstado("P");
		ArrayList<String> resultactivos = QMListaComunidadesActivos.getMovimientosComunidadesActivoPorEstado("P");

		ArrayList<String> resultcomunidadesactivos = new ArrayList<String>(resultcomunidades);
		
		resultcomunidadesactivos.addAll(resultactivos);
		
		HashSet<String> hslimpia = new HashSet<String>(resultcomunidadesactivos);

	   resultcomunidadesactivos.clear();
	   resultcomunidadesactivos.addAll(hslimpia);
		
		Collections.sort(resultcomunidadesactivos);

		String sNombreFichero = ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+Utils.timeStamp()+"_"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E1+".txt";
		
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

            for (int i = 0; i < resultcomunidades.size() ; i++)
            {
               QMListaComunidades.setValidado(resultcomunidades.get(i),ValoresDefecto.DEF_MOVIMIENTO_ENVIADO);
            }
            
            for (int i = 0; i < resultactivos.size() ; i++)
            {
         	   QMListaComunidadesActivos.setValidado(resultactivos.get(i),ValoresDefecto.DEF_MOVIMIENTO_ENVIADO);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            
            sNombreFichero = "";
            
            for (int i = 0; i < resultcomunidades.size() ; i++)
            {
               QMListaComunidades.setValidado(resultcomunidades.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
            }
            
            for (int i = 0; i < resultcomunidadesactivos.size() ; i++)
            {
         	   QMListaComunidadesActivos.setValidado(resultactivos.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
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
        		   
           } 
           catch (Exception e2) 
           {
              e2.printStackTrace();
              
              sNombreFichero = "";
              
              for (int i = 0; i < resultcomunidades.size() ; i++)
              {
                 QMListaComunidades.setValidado(resultcomunidades.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
              }
              
              for (int i = 0; i < resultcomunidadesactivos.size() ; i++)
              {
           	   QMListaComunidadesActivos.setValidado(resultactivos.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
              }
              
              logger.error("Ocurrió un error al cerrar el fichero de envio, se restauran los estados afectados.");
           }
        }
        return sNombreFichero;
	}
	
	public static String escribirCuotas() 
	{
		ArrayList<String> resultcuotas =  QMListaCuotas.getCuotasPorEstado("P");
		
		FileWriter ficheroE2 = null;
		
        PrintWriter pw = null;
        
        String sNombreFichero = ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+Utils.timeStamp()+"_"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E2+".txt";
        
        try
        {
            ficheroE2 = new FileWriter(sNombreFichero);
            pw = new PrintWriter(ficheroE2);
            
            for (int i = 0; i < resultcuotas.size() ; i++)
            {
                pw.println(Parser.escribirCuota(QMMovimientosCuotas.getMovimientoCuota(resultcuotas.get(i))));
                QMListaCuotas.setValidado(resultcuotas.get(i),ValoresDefecto.DEF_MOVIMIENTO_ENVIADO);
            }
            pw.print(ValoresDefecto.DEF_FIN_FICHERO);
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            
            //En caso de error se devuelven los registros a su estado anterior
            sNombreFichero = "";
            
            for (int i = 0; i < resultcuotas.size() ; i++)
            {
            	QMListaCuotas.setValidado(resultcuotas.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
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
            	   
           } 
           catch (Exception e2) 
           {
              e2.printStackTrace();
              
              //En caso de error se devuelven los registros a su estado anterior
              sNombreFichero = "";
              
              for (int i = 0; i < resultcuotas.size() ; i++)
              {
            	  QMListaCuotas.setValidado(resultcuotas.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
              }
              
              logger.error("Ocurrió un error al cerrar el fichero de envio, se restauran los estados afectados.");
              
           }
        }
 
        return sNombreFichero;
	}
	
	public static String escribirReferencias() 
	{
		ArrayList<String> resultreferencias = QMListaReferencias.getReferenciasPorEstado("P");
		
		FileWriter ficheroE3 = null;
		
        PrintWriter pw = null;
        
        String sNombreFichero = ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+Utils.timeStamp()+"_"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E3+".txt";
        try
        {
            ficheroE3 = new FileWriter(sNombreFichero);
            pw = new PrintWriter(ficheroE3);
            
            for (int i = 0; i < resultreferencias.size() ; i++)
            {
                pw.println(Parser.escribirReferenciaCatastral(QMMovimientosReferencias.getMovimientoReferenciaCatastral(resultreferencias.get(i))));
                QMListaReferencias.setValidado(resultreferencias.get(i),ValoresDefecto.DEF_MOVIMIENTO_ENVIADO);
            }
            pw.print(ValoresDefecto.DEF_FIN_FICHERO);
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            
            //En caso de error se devuelven los registros a su estado anterior
            sNombreFichero = "";
            
            for (int i = 0; i < resultreferencias.size() ; i++)
            {
            	QMListaReferencias.setValidado(resultreferencias.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
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
               }
           } 
           catch (Exception e2) 
           {
              e2.printStackTrace();

              //En caso de error se devuelven los registros a su estado anterior
              sNombreFichero = "";
              
              for (int i = 0; i < resultreferencias.size() ; i++)
              {
              	QMListaReferencias.setValidado(resultreferencias.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
              }
              
              logger.error("Ocurrió un error al cerrar el fichero de envio, se restauran los estados afectados.");
           }
        }
        
        return sNombreFichero;
	}
	
	public static String escribirImpuestos() 
	{
		ArrayList<String> resultimpuestos = QMListaImpuestos.getImpuestosPorEstado("P");
		
		FileWriter ficheroE4 = null;
		
        PrintWriter pw = null;
        
        String sNombreFichero = ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+Utils.timeStamp()+"_"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E4+".txt";
        
        try
        {
            ficheroE4 = new FileWriter(sNombreFichero);
            pw = new PrintWriter(ficheroE4);
            
            for (int i = 0; i < resultimpuestos.size() ; i++)
            {
                pw.println(Parser.escribirImpuestoRecurso(QMMovimientosImpuestos.getMovimientoImpuestoRecurso(resultimpuestos.get(i))));
                QMListaImpuestos.setValidado(resultimpuestos.get(i),ValoresDefecto.DEF_MOVIMIENTO_ENVIADO);
            }
            pw.print(ValoresDefecto.DEF_FIN_FICHERO);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            
            //En caso de error se devuelven los registros a su estado anterior
            sNombreFichero = "";
            
            for (int i = 0; i < resultimpuestos.size() ; i++)
            {
            	QMListaImpuestos.setValidado(resultimpuestos.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
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
              e2.printStackTrace();
              
              //En caso de error se devuelven los registros a su estado anterior
              sNombreFichero = "";
              
              for (int i = 0; i < resultimpuestos.size() ; i++)
              {
              	QMListaImpuestos.setValidado(resultimpuestos.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
              }
              
              logger.error("Ocurrió un error al cerrar el fichero de envio, se restauran los estados afectados.");
              
           }
        }
        
        return sNombreFichero;
	}
	
	public static String escribirGastos() 
	{
		ArrayList<String> resultgastos = QMListaGastos.getGastosPorEstado("P");
        
        FileWriter ficheroGA = null;
        
        PrintWriter pw = null;
        
        String sNombreFichero = ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+Utils.timeStamp()+"_"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_GA+".txt";
        
        try
        {

            ficheroGA = new FileWriter(sNombreFichero);
            pw = new PrintWriter(ficheroGA);
            
            for (int i = 0; i < resultgastos.size(); i++)
            {
                pw.println(Parser.escribirGasto(QMMovimientosGastos.getMovimientoGasto(resultgastos.get(i))));
                QMListaGastos.setValidado(resultgastos.get(i),ValoresDefecto.DEF_MOVIMIENTO_ENVIADO);
            }
            pw.print(ValoresDefecto.DEF_FIN_FICHERO);
 
        } 
        catch (IOException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
            //En caso de error se devuelven los registros a su estado anterior
            sNombreFichero = "";
            
            for (int i = 0; i < resultgastos.size() ; i++)
            {
            	QMListaImpuestos.setValidado(resultgastos.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
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
              	QMListaImpuestos.setValidado(resultgastos.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
              }
              
              logger.error("Ocurrió un error al cerrar el fichero de envio, se restauran los estados afectados.");
           }
        }
        
        return sNombreFichero;
	}

	public static String escribirCierres() 
	{
		ArrayList<String> resultcierres = QMProvisiones.getProvisionesCerradasPendientes();
        
        FileWriter ficheroPP = null;
        
        PrintWriter pw = null;
        
        String sNombreFichero = ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+Utils.timeStamp()+"_"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_PP+".txt";
        
        try
        {

        	ficheroPP = new FileWriter(sNombreFichero);
            pw = new PrintWriter(ficheroPP);
            
            for (int i = 0; i < resultcierres.size(); i++)
            {
            	Provision provision = QMProvisiones.getProvision(resultcierres.get(i));
                pw.println(Parser.escribirCierre(provision.getsNUPROF(),provision.getsFEPFON()));
                QMProvisiones.setFechaEnvio(resultcierres.get(i),Utils.fechaDeHoy(false));
            }
            pw.print(ValoresDefecto.DEF_FIN_FICHERO);
 
        } 
        catch (IOException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
            //En caso de error se devuelven los registros a su estado anterior
            sNombreFichero = "";
            
            for (int i = 0; i < resultcierres.size() ; i++)
            {
            	QMProvisiones.setFechaEnvio(resultcierres.get(i),"0");
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
            	  QMProvisiones.setFechaEnvio(resultcierres.get(i),"0");
              }
              
              logger.error("Ocurrió un error al cerrar el fichero de envio, se restauran los estados afectados.");
           }
        }
        
        return sNombreFichero;
	}
	
	public static ArrayList<ResultadosTabla> leerActivos(String sNombre)
	{
		logger.debug( "Fichero:|{}{}|",ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS,sNombre);

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
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

			String sTiempo = Utils.timeStamp();

			int contador= 0 ;
			int registros = 0;
			
			int iLongitudValida = Longitudes.ACTIVOS_L-Longitudes.FILLER_ACTIVOS_L-Longitudes.OBDEER_L;
			
			while((linea=br.readLine())!=null)
	        {
				contador++;

				logger.debug("Longitud de línea leida:|{}|",linea.length());
				logger.debug("Longitud de línea válida:|{}|",iLongitudValida);

	    		if (linea.equals(sFinFichero))
	    		{
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			logger.error("Error en línea {}, tamaño incorrecto.",contador);
	    		}
	    		else
	    		{
	    			int iCodigo = CLActivos.actualizaActivoLeido(linea);
	    			String sMensaje = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sMensaje = "Activo registrado.";
	    				logger.info("Línea "+contador+": "+sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -1:
	    				//sMensaje = "El registro ya se encuentra en el sistema.";
	    				//logger.error("Línea "+contador+": "+sMensaje);
	    				//sMensaje = "Línea "+contador+": "+sMensaje;
	    				sMensaje = "[FATAL] Error al actualizar el Activo.";
	    				logger.error("(X) Línea "+contador+": "+sMensaje);
	    				sMensaje = "(X) Línea "+contador+": "+sMensaje;
	    				break;
	    			case -2:
	    				sMensaje = "[FATAL] Error al registrar el Activo.";
	    				logger.error("(X) Línea "+contador+": "+sMensaje);
	    				sMensaje = "(X) Línea "+contador+": "+sMensaje;
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

			logger.debug("Duración de la carga: "+Utils.duracion(sTiempo,Utils.timeStamp()));
			
			logger.debug("Registros procesados:|"+contador+"|");
			logger.debug("Registros correctos:|"+registros+"|");

			//logger.info( "Actualizados "+registros+" registros.");
			logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			logger.error("No se encontró el fichero recibido.");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			e.printStackTrace();
		}
		logger.debug("tabla.size():|{}|",tabla.size());
		
        return tabla;
	}

	public static ArrayList<ResultadosTabla> leerGastosRevisados(String sNombre) 
	{
		logger.debug( "Fichero:|{}{}|",ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS,sNombre);

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		FileReader fr;

		try 
		{
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			
			logger.debug("Leyendo fichero..");

			String sTiempo = Utils.timeStamp();

			int contador= 0 ;
			int registros = 0;
			
			int iLongitudValida = Longitudes.GASTOS_L-Longitudes.FILLER_GASTOS_L-Longitudes.OBDEER_L;
			
			while((linea=br.readLine())!=null)
	        {
				contador++;

				logger.debug("Longitud de línea leida:|{}|",linea.length());
				logger.debug("Longitud de línea válida:|{}|",iLongitudValida);

	    		if (linea.equals(sFinFichero))
	    		{
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			logger.error("Error en línea {}, tamaño incorrecto.",contador);
	    		}
	    		else
	    		{
	    			int iCodigo = CLGastos.actualizaGastoLeido(linea);
	    			
	    			String sMensaje = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sMensaje = "Movimiento validado.";
	    				logger.info("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case 1:
	    				sMensaje = "Movimiento de Gasto pendiente de revisión.";
	    				logger.info("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -1:
	    				sMensaje = "Registro no encontrado en el sistema.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -2:
	    				sMensaje = "No existe relación con la Gasto.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -3:
	    				sMensaje = "[FATAL] Error al validar la relación con el Gasto.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -4:
	    				sMensaje = "[FATAL] Error al registrar el movimiento pendiente.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -5:
	    				sMensaje = "[FATAL] Error al actualizar la revisión del gasto.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;	
	    			case -10:
	    				sMensaje = "[FATAL] Estado del movimiento desconocido.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -11:
	    				sMensaje = "[FATAL] El movimiento recibido figura como 'no enviado'.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -12:
	    				sMensaje = "El movimiento recibido ya ha sido revisado.";
	    				logger.warn("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
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
		
			logger.debug("Inicio:|"+sTiempo+"|");
			logger.debug("Fin:|"+Utils.timeStamp()+"|");
			
			logger.debug("Registros procesados:|"+contador+"|");
			logger.debug("Registros correctos:|"+registros+"|");

			logger.info( "Lectura de "+sNombre+" finalizada.");
			logger.info( "Actualizados "+registros+" registros.");
			logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			logger.error("No se encontró el fichero recibido.");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			e.printStackTrace();
		}
		logger.debug("tabla.size():|{}|",tabla.size());
		
        return tabla;
		
	
	}

	public static ArrayList<ResultadosTabla> leerComunidadesRevisadas(String sNombre)
	{
		
		logger.debug( "Fichero:|{}{}|",ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS,sNombre);

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		FileReader fr;
		try 
		{
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			
			logger.debug("Leyendo fichero..");

			String sTiempo = Utils.timeStamp();

			int contador= 0 ;
			int registros = 0;
			
			int iLongitudValida = Longitudes.COMUNIDADES_L-Longitudes.FILLER_COMUNIDADES_L-Longitudes.OBDEER_L;
				
			while((linea=br.readLine())!=null)
	        {
				contador++;

				logger.debug("Longitud de línea leida:|{}|",linea.length());
				logger.debug("Longitud de línea válida:|{}|",iLongitudValida);

	    		if (linea.equals(sFinFichero))
	    		{
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			logger.error("Error en línea {}, tamaño incorrecto.",contador);
	    		}
	    		else
	    		{
	    			int iCodigo = CLComunidades.actualizaComunidadLeida(linea);
	    			String sMensaje = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sMensaje = "Movimiento validado.";
	    				logger.info("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case 1:
	    				sMensaje = "Movimento de Comunidad pendiente de revisión.";
	    				logger.info("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -1:
	    				sMensaje = "Registro no encontrado en el sistema.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -2:
	    				sMensaje = "No existe relación con la Comunidad.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -3:
	    				sMensaje = "No existe relación Activo-Comunidad.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -4:
	    				sMensaje = "[FATAL] Error al validar la reclación con la Comunidad.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -5:
	    				sMensaje = "[FATAL] Error al validar la relación Activo-Comunidad.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -6:
	    				sMensaje = "[FATAL] Error al registrar el movimiento pendiente.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -9:
	    				sMensaje = "[FATAL] Accion desconocida.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -10:
	    				sMensaje = "[FATAL] Estado del movimiento desconocido.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -11:
	    				sMensaje = "[FATAL] El movimiento recibido figura como 'no enviado'.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -12:
	    				sMensaje = "El movimiento recibido ya ha sido revisado.";
	    				logger.warn("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
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
		
			logger.debug("Inicio:|"+sTiempo+"|");
			logger.debug("Fin:|"+Utils.timeStamp()+"|");
			
			logger.debug("Registros procesados:|"+contador+"|");
			logger.debug("Registros correctos:|"+registros+"|");

			logger.info( "Lectura de "+sNombre+" finalizada.");
			logger.info( "Actualizados "+registros+" registros.");
			logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			logger.error("No se encontró el fichero recibido.");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			e.printStackTrace();
		}
		logger.debug("tabla.size():|{}|",tabla.size());
		
        return tabla;
	}
	
	public static ArrayList<ResultadosTabla> leerCuotasRevisadas(String sNombre) 
	{
		logger.debug( "Fichero:|{}{}|",ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS,sNombre);

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		FileReader fr;
		try 
		{
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			
			logger.debug("Leyendo fichero..");

			String sTiempo = Utils.timeStamp();

			int contador= 0 ;
			int registros = 0;
			
			int iLongitudValida = Longitudes.CUOTAS_L-Longitudes.FILLER_CUOTAS_L-Longitudes.OBDEER_L;
			
			while((linea=br.readLine())!=null)
	        {
				contador++;

				logger.debug("Longitud de línea leida:|{}|",linea.length());
				logger.debug("Longitud de línea válida:|{}|",iLongitudValida);

	    		if (linea.equals(sFinFichero))
	    		{
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			logger.error("Error en línea {}, tamaño incorrecto.",contador);
	    		}
	    		else
	    		{
	    			int iCodigo = CLCuotas.actualizaCuotaLeida(linea);
	    			String sMensaje = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sMensaje = "Movimiento validado.";
	    				logger.info("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case 1:
	    				sMensaje = "Movimento de Cuota pendiente de revisión.";
	    				logger.info("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -1:
	    				sMensaje = "Registro no encontrado en el sistema.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -2:
	    				sMensaje = "No existe relación con la Cuota.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -3:
	    				sMensaje = "[FATAL] Error al validar la reclación con la Cuota.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -4:
	    				sMensaje = "[FATAL] Error al registrar el movimiento pendiente.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;	    				
	    			case -9:
	    				sMensaje = "[FATAL] Accion desconocida.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -10:
	    				sMensaje = "[FATAL] Estado del movimiento desconocido.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -11:
	    				sMensaje = "[FATAL] El movimiento recibido figura como 'no enviado'.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -12:
	    				sMensaje = "El movimiento recibido ya ha sido revisado.";
	    				logger.warn("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
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
		
			logger.debug("Inicio:|"+sTiempo+"|");
			logger.debug("Fin:|"+Utils.timeStamp()+"|");
			
			logger.debug("Registros procesados:|"+contador+"|");
			logger.debug("Registros correctos:|"+registros+"|");

			logger.info( "Lectura de "+sNombre+" finalizada.");
			logger.info( "Actualizados "+registros+" registros.");
			logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			logger.error("No se encontró el fichero recibido.");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			e.printStackTrace();
		}
		logger.debug("tabla.size():|{}|",tabla.size());
		
        return tabla;
	}
	
	public static ArrayList<ResultadosTabla> leerReferenciasRevisadas(String sNombre) 
	{
		logger.debug( "Fichero:|{}{}|",ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS,sNombre);

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		FileReader fr;
		try 
		{
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			
			logger.debug("Leyendo fichero..");

			String sTiempo = Utils.timeStamp();

			int contador= 0 ;
			int registros = 0;
			
			int iLongitudValida = Longitudes.REFERENCIAS_L-Longitudes.FILLER_REFERENCIAS_L-Longitudes.OBDEER_L;

			while((linea=br.readLine())!=null)
	        {
				contador++;

				logger.debug("Longitud de línea leida:|{}|",linea.length());
				logger.debug("Longitud de línea válida:|{}|",iLongitudValida);

	    		if (linea.equals(sFinFichero))
	    		{
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			logger.error("Error en línea {}, tamaño incorrecto.",contador);
	    		}
	    		else
	    		{
	    			int iCodigo = CLReferencias.actualizaReferenciaLeida(linea);

	    			String sMensaje = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sMensaje = "Movimiento validado.";
	    				logger.info("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case 1:
	    				sMensaje = "Movimento de Referencia Catastral pendiente de revisión.";
	    				logger.info("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -1:
	    				sMensaje = "Registro no encontrado en el sistema.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -2:
	    				sMensaje = "No existe relación con la Referencia Catastral.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -3:
	    				sMensaje = "[FATAL] Error al validar la reclación con la Referencia Catastral.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -4:
	    				sMensaje = "[FATAL] Error al registrar el movimiento pendiente.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;	    				
	    			case -9:
	    				sMensaje = "[FATAL] Accion desconocida.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -10:
	    				sMensaje = "[FATAL] Estado del movimiento desconocido.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -11:
	    				sMensaje = "[FATAL] El movimiento recibido figura como 'no enviado'.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -12:
	    				sMensaje = "El movimiento recibido ya ha sido revisado.";
	    				logger.warn("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
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
		
			logger.debug("Inicio:|"+sTiempo+"|");
			logger.debug("Fin:|"+Utils.timeStamp()+"|");
			
			logger.debug("Registros procesados:|"+contador+"|");
			logger.debug("Registros correctos:|"+registros+"|");

			logger.info( "Lectura de "+sNombre+" finalizada.");
			logger.info( "Actualizados "+registros+" registros.");
			logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			logger.error("No se encontró el fichero recibido.");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			e.printStackTrace();
		}
		logger.debug("tabla.size():|{}|",tabla.size());
		
        return tabla;
	}

	public static ArrayList<ResultadosTabla> leerImpuestosRevisadas(String sNombre) 
	{

		logger.debug( "Fichero:|{}{}|",ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS,sNombre);

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		FileReader fr;

		try 
		{
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			
			logger.debug("Leyendo fichero..");

			String sTiempo = Utils.timeStamp();

			int contador= 0 ;
			int registros = 0;
			
			int iLongitudValida = Longitudes.IMPUESTOS_L-Longitudes.FILLER_IMPUESTOS_L-Longitudes.OBDEER_L;

			while((linea=br.readLine())!=null)
	        {
				contador++;

				logger.debug("Longitud de línea leida:|{}|",linea.length());
				logger.debug("Longitud de línea válida:|{}|",iLongitudValida);

	    		if (linea.equals(sFinFichero))
	    		{
	    			logger.info("Lectura finalizada.");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			logger.error("Error en línea {}, tamaño incorrecto.",contador);
	    		}
	    		else
	    		{
	    			int iCodigo = CLImpuestos.actualizaImpuestoLeido(linea);

	    			String sMensaje = "";
	    			
	    			switch (iCodigo)
	    			{
	    			case 0:
	    				sMensaje = "Movimiento validado.";
	    				logger.info("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case 1:
	    				sMensaje = "Movimento de Impuesto pendiente de revisión.";
	    				logger.info("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -1:
	    				sMensaje = "Registro no encontrado en el sistema.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -2:
	    				sMensaje = "No existe relación con el Impuesto.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -3:
	    				sMensaje = "[FATAL] Error al validar la reclación con el Impuestos.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -4:
	    				sMensaje = "[FATAL] Error al registrar el movimiento pendiente.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;	    				
	    			case -9:
	    				sMensaje = "[FATAL] Accion desconocida.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -10:
	    				sMensaje = "[FATAL] Estado del movimiento desconocido.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -11:
	    				sMensaje = "[FATAL] El movimiento recibido figura como 'no enviado'.";
	    				logger.error("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
	    				break;
	    			case -12:
	    				sMensaje = "El movimiento recibido ya ha sido revisado.";
	    				logger.warn("Línea {}: {}",contador,sMensaje);
	    				sMensaje = "Línea "+contador+": "+sMensaje;
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
		
			logger.debug("Inicio:|"+sTiempo+"|");
			logger.debug("Fin:|"+Utils.timeStamp()+"|");
			
			logger.debug("Registros procesados:|"+contador+"|");
			logger.debug("Registros correctos:|"+registros+"|");

			logger.info( "Lectura de "+sNombre+" finalizada.");
			logger.info( "Actualizados "+registros+" registros.");
			logger.info( "Encontrados "+(contador-registros)+" registros erróneos.\n");
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			logger.error("No se encontró el fichero recibido.");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			e.printStackTrace();
		}
		logger.debug("tabla.size():|{}|",tabla.size());
		
        return tabla;

	}
	
	public static Resultados splitter(String sNombre) 
	{
		int iCodigo = 0;
		Resultados carga;
		
		ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>();
		
		logger.debug("|{}|{}|",sNombre,sNombre.substring(0, 3));
		
		
		if (sNombre.length() < 9)
		{
			iCodigo = -1;
			logger.error("El archivo suministrado no pertenece a esta subaplicacion INFOCAM.");
		}
		else 
		{
			String sNombreOriginal = sNombre.substring(sNombre.length()-9);
			
			if (sNombreOriginal.toUpperCase().matches("("+ValoresDefecto.DEF_COAPII+")(AC|RG|PA|GA|PP|E1|E2|E3|E4)(\\.TXT)$"))
			{

				String sTipo = sNombreOriginal.substring(3, 5).toUpperCase();

				logger.debug("Redirigiendo lectura...");

				logger.debug("Tipo:|{}|",sTipo);

				ValoresDefecto.TIPOSFICHERO COSPII = ValoresDefecto.TIPOSFICHERO.valueOf(sTipo);

				switch (COSPII) {
				case AC:
					logger.debug("Activos");
					tabla = leerActivos(sNombre);

					if (tabla.size() == 0)	
					{
						iCodigo = 1;
					}
					break;
				case RG:
					logger.debug("Rechazados");
					tabla = leerGastosRevisados(sNombre);

					if (tabla.size() == 0)					
					{
						iCodigo = 2;
					}
					break;
				case PA:
					logger.debug("Autorizados");
					tabla = leerGastosRevisados(sNombre);

					if (tabla.size() == 0)					
					{
						iCodigo = 3;
					}
					break;
				case GA:
					logger.debug("Gastos");
					/*tabla = leerGastosRevisados(sNombre);

					if (tabla.size() == 0)	
					{*/
						iCodigo = 4;
					//}
					break;
				case PP:
					logger.debug("Cierres");
					/*tabla = leerCierres(sNombre); //No implementable

					if (tabla.size() == 0)	
					{*/
						iCodigo = 5;
					//}
					break;
				case E1:
					logger.debug("Comunidades");
					tabla = leerComunidadesRevisadas(sNombre);

					if (tabla.size() == 0)
					{
						iCodigo = 6;
					}
					break;
				case E2:
					logger.debug("Cuotas");
					tabla = leerCuotasRevisadas(sNombre);

					if (tabla.size() == 0)
					{
						iCodigo = 7;
					}
					break;
				case E3:
					logger.debug("Referencias Catastrales");
					tabla = leerReferenciasRevisadas(sNombre);

					if (tabla.size() == 0)
					{
						iCodigo = 8;
					}
					break;
				case E4:
					logger.debug("Impuestos");
					tabla = leerImpuestosRevisadas(sNombre);

					if (tabla.size() == 0)
					{
						iCodigo = 9;
					}
					break;
				default:
					logger.error("El archivo suministrado no coincide con el nombrado establecido:");
					logger.error("168XX.txt donde XX puede ser AC, RG, PA, GA, PP, E1, E2, E3 o E4. ");
					iCodigo = -3;
					break;
				}
				logger.debug("tabla.size():|{}|",tabla.size());
				logger.debug("Operativa completa.");

			} 
			else
			{
				iCodigo = -1;
				logger.error("El archivo suministrado no pertenece a esta subaplicacion INFOCAM.");
				//lista = new ArrayList<String>();
			}
			

		}
		
		carga = new Resultados(iCodigo,tabla);
		
		return carga;
	}
}
