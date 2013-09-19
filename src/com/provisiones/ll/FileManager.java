package com.provisiones.ll;

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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.qm.QMProvisiones;
import com.provisiones.dal.qm.listas.QMListaComunidades;
import com.provisiones.dal.qm.listas.QMListaComunidadesActivos;
import com.provisiones.dal.qm.listas.QMListaCuotas;
import com.provisiones.dal.qm.listas.QMListaGastos;
import com.provisiones.dal.qm.listas.QMListaImpuestos;
import com.provisiones.dal.qm.listas.QMListaReferencias;
import com.provisiones.dal.qm.movimientos.QMMovimientosComunidades;
import com.provisiones.dal.qm.movimientos.QMMovimientosCuotas;
import com.provisiones.dal.qm.movimientos.QMMovimientosGastos;
import com.provisiones.dal.qm.movimientos.QMMovimientosImpuestos;
import com.provisiones.dal.qm.movimientos.QMMovimientosReferencias;
import com.provisiones.misc.Longitudes;
import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Provision;

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
		ArrayList<String> resultactivos = QMListaComunidadesActivos.getComunidadesActivoPorEstado("P");

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
                pw.println(Parser.escribirComunidad(QMMovimientosComunidades.getMovimientoComunidad(resultcomunidadesactivos.get(i))));
            }

            pw.print(ValoresDefecto.DEF_FIN_FICHERO);

            for (int i = 0; i < resultcomunidades.size() ; i++)
            {
               QMListaComunidades.setValidado(resultcomunidades.get(i),ValoresDefecto.DEF_ENVIADO);
            }
            
            for (int i = 0; i < resultactivos.size() ; i++)
            {
         	   QMListaComunidadesActivos.setValidado(resultactivos.get(i),ValoresDefecto.DEF_ENVIADO);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            
            sNombreFichero = "";
            
            for (int i = 0; i < resultcomunidades.size() ; i++)
            {
               QMListaComunidades.setValidado(resultcomunidades.get(i),ValoresDefecto.DEF_PENDIENTE);
            }
            
            for (int i = 0; i < resultcomunidadesactivos.size() ; i++)
            {
         	   QMListaComunidadesActivos.setValidado(resultactivos.get(i),ValoresDefecto.DEF_PENDIENTE);
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
                 QMListaComunidades.setValidado(resultcomunidades.get(i),ValoresDefecto.DEF_PENDIENTE);
              }
              
              for (int i = 0; i < resultcomunidadesactivos.size() ; i++)
              {
           	   QMListaComunidadesActivos.setValidado(resultactivos.get(i),ValoresDefecto.DEF_PENDIENTE);
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
                QMListaCuotas.setValidado(resultcuotas.get(i),ValoresDefecto.DEF_ENVIADO);
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
            	QMListaCuotas.setValidado(resultcuotas.get(i),ValoresDefecto.DEF_PENDIENTE);
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
            	  QMListaCuotas.setValidado(resultcuotas.get(i),ValoresDefecto.DEF_PENDIENTE);
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
                QMListaReferencias.setValidado(resultreferencias.get(i),ValoresDefecto.DEF_ENVIADO);
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
            	QMListaReferencias.setValidado(resultreferencias.get(i),ValoresDefecto.DEF_PENDIENTE);
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
              	QMListaReferencias.setValidado(resultreferencias.get(i),ValoresDefecto.DEF_PENDIENTE);
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
                QMListaImpuestos.setValidado(resultimpuestos.get(i),ValoresDefecto.DEF_ENVIADO);
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
            	QMListaImpuestos.setValidado(resultimpuestos.get(i),ValoresDefecto.DEF_PENDIENTE);
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
              	QMListaImpuestos.setValidado(resultimpuestos.get(i),ValoresDefecto.DEF_PENDIENTE);
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
                QMListaGastos.setValidado(resultgastos.get(i),ValoresDefecto.DEF_ENVIADO);
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
            	QMListaImpuestos.setValidado(resultgastos.get(i),ValoresDefecto.DEF_PENDIENTE);
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
              	QMListaImpuestos.setValidado(resultgastos.get(i),ValoresDefecto.DEF_PENDIENTE);
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
	
	
	public static boolean leerActivos(String sNombre)
	{

		boolean bSalida = false;

		

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		FileReader fr;
		
		try 
		{
			fr = new FileReader (archivo);
			BufferedReader br = new BufferedReader(fr);

			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			

			
			
			
			logger.debug( "Leyendo fichero..");

			java.util.Date date= new java.util.Date();
			logger.debug("Inicio:|{}|",new Timestamp(date.getTime()));

			int contador=0;
			int registros = 0;

			while((linea=br.readLine())!=null)
	        {
				contador++;
	    		if (linea.equals(sFinFichero))
	    		{
	    			logger.debug( "Lectura finalizada!");
	    		}
	    		else if (linea.length()< (Longitudes.ACTIVOS_L-Longitudes.FILLER_ACTIVOS_L) )
	    		{
	    			logger.error("Error en linea {}",contador);
	    		}
	    		else
	    		{
	    			if (CLActivos.actualizaActivoLeido(linea))
	    				registros++;
	    		}
	            
	        }
			
			
			br.close();

			
			bSalida = ((contador-registros-1) == 0);
			
			logger.debug( "Lectura de {} finalizada.",sNombre);
			logger.debug( "Actualizados {} registros.",registros);
			logger.debug( "Encontrados {} registros erroneos.",(contador-registros-1));
		
		

		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			e.printStackTrace();
		}		
        return bSalida;
	}
	
	public static boolean leerGastosRevisados(String sNombre) 
	{
		boolean bSalida = false;
		
		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		FileReader fr;
		try 
		{
			fr = new FileReader (archivo);

			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			

			logger.debug( "Leyendo fichero..");

			java.util.Date date= new java.util.Date();
			logger.debug("Inicio:|{}|", new Timestamp(date.getTime()));

			int contador=0;
			int registros = 0;
			
			while((linea=br.readLine())!=null)
	        {
				contador++;
	    		if (linea.equals(sFinFichero))
	    		{
	    			logger.debug( "Lectura finalizada!");
	    		}
	    		else if (linea.length()< (Longitudes.GASTOS_L-Longitudes.FILLER_GASTOS_L) )
	    		{
	    			logger.error("Error en linea {}",contador);
	    		}
	    		else
	    		{
	    				registros++;
	    		}
	            
	        }
			
			br.close();
			
			bSalida = ((contador-registros-1) == 0);
			
			logger.debug( "Lectura de {} finalizada.",sNombre);
			logger.debug( "Actualizados {} registros.",registros);
			logger.debug( "Encontrados {} registros erroneos.",(contador-registros-1));
			
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			e.printStackTrace();
		}				
        return bSalida;
	}

	public static boolean leerComunidadesRevisadas(String sNombre)
	{
		boolean bSalida = false;
		
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

			java.util.Date date= new java.util.Date();
			logger.debug("Inicio:|{}|",new Timestamp(date.getTime()));

			int contador= 0 ;
			int registros = 0;
			bSalida = true;
			
			int iLongitudValida = Longitudes.COMUNIDADES_L-Longitudes.FILLER_COMUNIDADES_L-Longitudes.OBDEER_L;
				
			while((linea=br.readLine())!=null)
	        {
				contador++;

				logger.debug("Posiciones leidas:|{}|",linea.length());
				logger.debug("Comunidades:|{}|",Longitudes.COMUNIDADES_L);
				logger.debug("Filler:|{}|",Longitudes.FILLER_COMUNIDADES_L);
				logger.debug("Resta:|{}|",iLongitudValida);

	    		if (linea.equals(sFinFichero))
	    		{
	    			logger.debug("Lectura finalizada!");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			logger.debug("Error en linea {}",contador);
	    		}
	    		else
	    		{
	    			if (CLComunidades.actualizaComunidadLeida(linea))
	    				registros++;
	    		}
	        }
		
			br.close();
		
			logger.debug("Contador:|{}|\n Registros:|{}|",contador,registros);
			
			bSalida = ((contador-registros-1) == 0);
			
			logger.debug( "Lectura de {} finalizada.",sNombre);
			logger.debug( "Actualizados {} registros.",registros);
			logger.debug( "Encontrados {} registros erroneos.",(contador-registros-1));

		
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			e.printStackTrace();
		}
        return bSalida;
	}
	
	public static boolean leerCuotasRevisadas(String sNombre) 
	{
		boolean bSalida = false;
		
		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		FileReader fr;
		try 
		{
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			

			logger.debug("Leyendo fichero..");

			java.util.Date date= new java.util.Date();
			logger.debug("Inicio:|{}|", new Timestamp(date.getTime()));

			int contador= 0 ;
			int registros = 0;
			
			while((linea=br.readLine())!=null)
	        {
				contador++;
	    		if (linea.equals(sFinFichero))
	    		{
	    			logger.debug("Lectura finalizada!");
	    		}
	    		else if (linea.length()< (Longitudes.CUOTAS_L-Longitudes.FILLER_CUOTAS_L) )
	    		{
	    			logger.error("Error en linea {}",contador);
	    		}
	    		else
	    		{
	    			if (CLCuotas.actualizaCuotaLeida(linea))
	    				registros++;
	    		}
	        }
			
			br.close();
			
			logger.debug("Contador:|{}|\n Registros:|{}|",contador,registros);
			
			bSalida = ((contador-registros-1) == 0);
			
			logger.debug( "Lectura de {} finalizada.",sNombre);
			logger.debug( "Actualizados {} registros.",registros);
			logger.debug( "Encontrados {} registros erroneos.",(contador-registros-1));

		
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			e.printStackTrace();
		}
        return bSalida;
	}
	
	public static boolean leerReferenciasRevisadas(String sNombre) 
	{
		boolean bSalida = false;
		
		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		FileReader fr;
		try 
		{
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;

			logger.debug("Leyendo fichero..");

			java.util.Date date= new java.util.Date();
			logger.debug("Inicio:|{}|",new Timestamp(date.getTime()));

			int contador= 0 ;
			int registros = 0;
			
			while((linea=br.readLine())!=null)
	        {
				contador++;
	    		if (linea.equals(sFinFichero))
	    		{
	    			logger.debug( "Lectura finalizada!");
	    		}
	    		else if (linea.length()< (Longitudes.REFERENCIAS_L-Longitudes.FILLER_REFERENCIAS_L) )
	    		{
	    			logger.error("Error en linea {}"+contador);
	    		}
	    		else
	    		{
	    			if (CLReferencias.actualizaReferenciaLeida(linea))
	    				registros++;
	    		}
	        }
		
			br.close();
			
			logger.debug("Contador:|{}|\n Registros:|{}|",contador,registros);
		
			bSalida = ((contador-registros-1) == 0);
			
			logger.debug( "Lectura de {} finalizada.",sNombre);
			logger.debug( "Actualizados {} registros.",registros);
			logger.debug( "Encontrados {} registros erroneos.",(contador-registros-1));

		
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			e.printStackTrace();
		}
        return bSalida;
	}

	public static boolean leerImpuestosRevisadas(String sNombre) 
	{
		boolean bSalida = false;
		
		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		FileReader fr;

		try 
		{
			fr = new FileReader (archivo);

			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			

			logger.debug("Leyendo fichero..");

			java.util.Date date= new java.util.Date();
			logger.debug("Inicio:|{}|",new Timestamp(date.getTime()));

			int contador= 0 ;
			int registros = 0;
			
			while((linea=br.readLine())!=null)
	        {
				contador++;
	    		if (linea.equals(sFinFichero))
	    		{
	    			logger.debug( "Lectura finalizada!");
	    		}
	    		else if (linea.length()< (Longitudes.IMPUESTOS_L-Longitudes.FILLER_IMPUESTOS_L) )
	    		{
	    			logger.error( "Error en linea "+contador);
	    		}
	    		else
	    		{
	    			if (CLImpuestos.actualizaImpuestoLeido(linea))
	    				registros++;
	    		}
	        }
			
			br.close();
			
			logger.debug("Contador:|{}|\n Registros:|{}|",contador,registros);

			bSalida = ((contador-registros-1) == 0);
			
			logger.debug( "Lectura de {} finalizada.",sNombre);
			logger.debug( "Actualizados {} registros.",registros);
			logger.debug( "Encontrados {} registros erroneos.",(contador-registros-1));

		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error("Ocurrió un error al acceder al fichero recibido.");
			e.printStackTrace();
		}	
        return bSalida;
	}
	
	public static int splitter(String sNombre) throws IOException 
	{
		int iCodigo = 0;

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

					if (!leerActivos(sNombre))
					{
						iCodigo = 1;
					}
					break;
				case RG:
					logger.debug("Rechazados");
					
					if (!leerGastosRevisados(sNombre))
					{
						iCodigo = 2;
					}
					break;
				case PA:
					logger.debug("Autorizados");

					if (!leerGastosRevisados(sNombre))
					{
						iCodigo = 3;
					}
					break;
				case GA:
					logger.debug("Gastos");
					
					logger.warn("El archivo de gastos debe de ser primero supervisado por la entidad.");

					/*if (!leerGastosRevisados(sNombre))
					{
						iCodigo = 4;
					}*/
					break;
				case PP:
					logger.debug("Cierres");
					logger.warn("El archivo de cierres debe comprobado por la entidad.");

					/*if (!leerCierres(sNombre)) //No implementable
					{
						iCodigo = 5;
					}*/
					break;
				case E1:
					logger.debug("Comunidades");

					if (!leerComunidadesRevisadas(sNombre))
					{
						iCodigo = 6;
					}
					break;
				case E2:
					logger.debug("Cuotas");

					if (!leerCuotasRevisadas(sNombre))
					{
						iCodigo = 7;
					}
					break;
				case E3:
					logger.debug("Referencias Catastrales");

					if (!leerReferenciasRevisadas(sNombre))
					{
						iCodigo = 8;
					}
					break;
				case E4:
					logger.debug("Impuestos");

					if (!leerImpuestosRevisadas(sNombre))
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

				logger.debug("Operativa completa.");

			} 
			else
			{
				iCodigo = -2;
				logger.error("El archivo suministrado no pertenece a esta subaplicacion INFOCAM.");
				//lista = new ArrayList<String>();
			}
			

		}
		return iCodigo;
	}
}
