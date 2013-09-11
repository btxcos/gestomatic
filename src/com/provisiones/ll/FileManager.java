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

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

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

	static String sClassName = FileManager.class.getName();
	static boolean bEnable = true;
	
	public static String guardarFichero(FileUploadEvent event)
	{
		String sMethod = "guardarFichero";
		
		Utils.debugTrace(true, sClassName, sMethod,"ID:|"+event.getPhaseId().getOrdinal()+"|");
	
		Utils.debugTrace(true, sClassName, sMethod,"Guardando archivo...");
        UploadedFile file = event.getFile();
        
        
        
        String sFichero = Utils.timeStamp()+"_"+file.getFileName();

        //long fileSize = file.getSize();
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
			e.printStackTrace();
		}
        Utils.debugTrace(true, sClassName, sMethod,"Completado con exito.");
        return sFichero;
	}

	public static String escribirComunidades() 
	{
		String sMethod = "escribirComunidades";
		
		
		//Los movimientos de las comunidades estan repartidos entre las comunidades y los activos incluidos
		
		ArrayList<String> resultcomunidades = QMListaComunidades.getComunidadesPorEstado("P");
		ArrayList<String> resultactivos = QMListaComunidadesActivos.getComunidadesActivoPorEstado("P");

		ArrayList<String> resultcomunidadesactivos = new ArrayList<String>(resultactivos);

		resultcomunidadesactivos.removeAll(resultcomunidades);
		resultcomunidadesactivos.addAll(resultcomunidades);
		
		
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
            
        } 
        finally 
        {
           try 
           {
        	   if (null != ficheroE1)
        	   {
        		   ficheroE1.close();
        		   Utils.debugTrace(true, sClassName, sMethod, "Generados!");
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
           }
        }
        return sNombreFichero;
	}
	
	public static String escribirCuotas() 
	{
		String sMethod = "escribirCuotas";
		
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
        } 
        finally 
        {
           try 
           {
        	   if (null != ficheroE2)
        	   {
        		   ficheroE2.close();
        		   Utils.debugTrace(true, sClassName, sMethod, "Generados!");
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
              
           }
        }
 
        return sNombreFichero;
	}
	
	public static String escribirReferencias() 
	{
		String sMethod = "escribirReferencias";
		
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
        } 
        finally 
        {
           try 
           {
               if (null != ficheroE3)
               {
            	   ficheroE3.close();
            	   Utils.debugTrace(true, sClassName, sMethod, "Generados!");
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
           }
        }
        
        return sNombreFichero;
	}
	
	public static String escribirImpuestos() 
	{
		String sMethod = "escribirImpuestos";
		
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
        } 
        finally 
        {
           try 
           {
               if (null != ficheroE4)
               {
            	   ficheroE4.close();
            	   Utils.debugTrace(true, sClassName, sMethod, "Generados!");
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
              
           }
        }
        
        return sNombreFichero;
	}
	
	public static String escribirGastos() 
	{
		String sMethod = "escribirGastos";
		
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
		} 

        finally 
        {
           try 
           {

        	   if (null != ficheroGA)
        	   {
        		   ficheroGA.close();
        		   Utils.debugTrace(true, sClassName, sMethod, "Generados!");
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
           }
        }
        
        return sNombreFichero;
	}

	public static String escribirCierres() 
	{
		String sMethod = "escribirCierres";
		
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
		} 

        finally 
        {
           try 
           {

        	   if (null != ficheroPP)
        	   {
        		   ficheroPP.close();
        		   Utils.debugTrace(true, sClassName, sMethod, "Generados!");
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
           }
        }
        
        return sNombreFichero;
	}
	
	
	public static boolean leerActivos(String sNombre)
	{

		String sMethod = "leerActivos";
		
		boolean bSalida = false;

		

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		FileReader fr;
		
		try 
		{
			fr = new FileReader (archivo);
			BufferedReader br = new BufferedReader(fr);

			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			

			
			
			
			Utils.debugTrace(true, sClassName, sMethod, "Leyendo fichero..");

			java.util.Date date= new java.util.Date();
			Utils.debugTrace(true, sClassName, sMethod, "Inicio: " + new Timestamp(date.getTime()));

			int contador=0;
			int registros = 0;

			while((linea=br.readLine())!=null)
	        {
				contador++;
	    		if (linea.equals(sFinFichero))
	    			Utils.debugTrace(true, sClassName, sMethod, "Lectura finalizada!");
	    		else if (linea.length()< (Longitudes.ACTIVOS_L-Longitudes.FILLER_ACTIVOS_L) )
	    			Utils.debugTrace(true, sClassName, sMethod, "Error en linea "+contador);
	    		else
	    		{
	    			if (CLActivos.actualizaActivoLeido(linea))
	    				registros++;
	    		}
	            
	        }
			
			
			br.close();

			
			bSalida = ((contador-registros-1) == 0);
			
			Utils.debugTrace(true, sClassName, sMethod, "Lectura de "+sNombre+" finalizada.");
			Utils.debugTrace(true, sClassName, sMethod, "Actualizados "+registros+" registros.");
			Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+(contador-registros-1)+" registros erroneos.");
		
		

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
        return bSalida;
	}
	
	public static boolean leerGastosRevisados(String sNombre) 
	{
		String sMethod = "leerGastosRevisados";

		boolean bSalida = false;
		
		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		FileReader fr;
		try 
		{
			fr = new FileReader (archivo);

			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			

			Utils.debugTrace(true, sClassName, sMethod, "Leyendo fichero..");

			java.util.Date date= new java.util.Date();
			System.out.println("Inicio: " + new Timestamp(date.getTime()));

			int contador=0;
			int registros = 0;
			
			//ArrayList<String> lista_rechazados = new ArrayList<String>(); 
			
			while((linea=br.readLine())!=null)
	        {
				contador++;
	    		if (linea.equals(sFinFichero))
	    			Utils.debugTrace(true, sClassName, sMethod, "Lectura finalizada!");
	    		else if (linea.length()< (Longitudes.GASTOS_L-Longitudes.FILLER_GASTOS_L) )
	    			Utils.debugTrace(true, sClassName, sMethod, "Error en linea "+contador);
	    		else
	    		{
	    			//if (CLGastos.actualizaGastoLeido(linea))
	    				registros++;
	    		}
	            
	        }
			
			br.close();
			
			bSalida = ((contador-registros-1) == 0);
			
			Utils.debugTrace(true, sClassName, sMethod, "Lectura de "+sNombre+" finalizada.");
			Utils.debugTrace(true, sClassName, sMethod, "Actualizados "+registros+" registros.");
			Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+(contador-registros-1)+" registros erroneos.");
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
        return bSalida;
	}

	/*public static boolean leerCierres(String sNombre) throws IOException 
	{

		String sMethod = "leerCierres";
		
		boolean bSalida = false;
		
		File archivo = new File (ValoresDefecto.DEF_PATH+sNombre);
		FileReader fr = new FileReader (archivo);
		BufferedReader br = new BufferedReader(fr);
		
		String linea = "";

		String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;


		Utils.standardIO2File("");//Salida por fichero de texto
		
		
		Utils.debugTrace(true, sClassName, sMethod, "Leyendo fichero..");

		java.util.Date date= new java.util.Date();
		Utils.debugTrace(true, sClassName, sMethod, "Inicio: " + new Timestamp(date.getTime()));

		//ArrayList<String> lista_provisiones_cerradas = new ArrayList<String>();
		
		int contador=0;
		while((linea=br.readLine())!=null)
        {
			contador++;
    		if (linea.equals(sFinFichero))
    			Utils.debugTrace(true, sClassName, sMethod, "Lectura finalizada!");
    		else if (linea.length()< (Longitudes.CIERRE_L-Longitudes.FILLER_CIERRE_L) )
    			Utils.debugTrace(true, sClassName, sMethod, "Error en linea "+contador);
    		else
    		{
    			Cierre cierre = Parser.LeerCierre(linea);
    			//lista_provisiones_cerradas.add(cierre.getsNUPROF());
    		}
            
        }
		
		
		br.close();
		
		Utils.debugTrace(true, sClassName, sMethod, "Lectura de "+sNombre+" finalizada.");
		
        return bSalida;
	}*/
	
	public static boolean leerComunidadesRevisadas(String sNombre)
	{
		String sMethod = "leerComunidadesRevisadas";

		boolean bSalida = false;
		
		Utils.debugTrace(true, sClassName, sMethod, "Fichero:|"+ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre+"|");

		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		
		FileReader fr;
		try 
		{
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			
			
			Utils.debugTrace(true, sClassName, sMethod, "Leyendo fichero..");

			java.util.Date date= new java.util.Date();
			System.out.println("Inicio: " + new Timestamp(date.getTime()));

			int contador= 0 ;
			int registros = 0;
			bSalida = true;
			
			//String sNUDCOM = "";
			//ArrayList<Comunidad> lista_comunidades = new ArrayList<Comunidad>();
			
			int iLongitudValida = Longitudes.COMUNIDADES_L-Longitudes.FILLER_COMUNIDADES_L-Longitudes.OBDEER_L;
				
			while((linea=br.readLine())!=null)
	        {
				contador++;

				Utils.debugTrace(true, sClassName, sMethod, 
						"TAMS (Posiciones leidas|"+linea.length()+
						"|Comunidades:|"+Longitudes.COMUNIDADES_L+
						"|Filler:|"+Longitudes.FILLER_COMUNIDADES_L+
						"| Resta:|"+(iLongitudValida)+"|");

	    		if (linea.equals(sFinFichero))
	    		{
	    			Utils.debugTrace(true, sClassName, sMethod, "Lectura finalizada!");
	    		}
	    		else if (linea.length()< iLongitudValida )
	    		{
	    			Utils.debugTrace(true, sClassName, sMethod, "Error en linea "+contador);
	    		}
	    		else
	    		{
	    			if (CLComunidades.actualizaComunidadLeida(linea))
	    				registros++;
	    		}
	        }
		
			br.close();
		
			bSalida = ((contador-registros-1) == 0);
			
			Utils.debugTrace(true, sClassName, sMethod, "Lectura de "+sNombre+" finalizada.");
			Utils.debugTrace(true, sClassName, sMethod, "Actualizados "+registros+" registros.");
			Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+(contador-registros-1)+" registros erroneos.");

		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return bSalida;
	}
	
	public static boolean leerCuotasRevisadas(String sNombre) 
	{
		String sMethod = "leerCuotasRevisadas";

		boolean bSalida = false;
		
		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		FileReader fr;
		try 
		{
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			

			Utils.debugTrace(true, sClassName, sMethod, "Leyendo fichero..");

			java.util.Date date= new java.util.Date();
			System.out.println("Inicio: " + new Timestamp(date.getTime()));

			int contador= 0 ;
			int registros = 0;
			
			//ArrayList<Comunidad> lista_comunidades = new ArrayList<Comunidad>();
			
				
			while((linea=br.readLine())!=null)
	        {
				contador++;
	    		if (linea.equals(sFinFichero))
	    			Utils.debugTrace(true, sClassName, sMethod, "Lectura finalizada!");
	    		else if (linea.length()< (Longitudes.CUOTAS_L-Longitudes.FILLER_CUOTAS_L) )
	    			Utils.debugTrace(true, sClassName, sMethod, "Error en linea "+contador);
	    		else
	    		{
	    			if (CLCuotas.actualizaCuotaLeida(linea))
	    				registros++;
	    		}
	        }
			
			br.close();
			
			bSalida = ((contador-registros-1) == 0);
			
			Utils.debugTrace(true, sClassName, sMethod, "Lectura de "+sNombre+" finalizada.");
			Utils.debugTrace(true, sClassName, sMethod, "Actualizados "+registros+" registros.");
			Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+(contador-registros-1)+" registros erroneos.");

		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return bSalida;
	}
	
	public static boolean leerReferenciasRevisadas(String sNombre) 
	{
		String sMethod = "leerReferenciasRevisadas";

		boolean bSalida = false;
		
		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		FileReader fr;
		try 
		{
			fr = new FileReader (archivo);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;

			Utils.debugTrace(true, sClassName, sMethod, "Leyendo fichero..");

			java.util.Date date= new java.util.Date();
			System.out.println("Inicio: " + new Timestamp(date.getTime()));

			int contador= 0 ;
			int registros = 0;
			
			//String sNUDCOM = "";
			//ArrayList<Comunidad> lista_comunidades = new ArrayList<Comunidad>();
			
				
			while((linea=br.readLine())!=null)
	        {
				contador++;
	    		if (linea.equals(sFinFichero))
	    			Utils.debugTrace(true, sClassName, sMethod, "Lectura finalizada!");
	    		else if (linea.length()< (Longitudes.REFERENCIAS_L-Longitudes.FILLER_REFERENCIAS_L) )
	    			Utils.debugTrace(true, sClassName, sMethod, "Error en linea "+contador);
	    		else
	    		{
	    			if (CLReferencias.actualizaReferenciaLeida(linea))
	    				registros++;
	    		}
	        }
		
			br.close();
		
			bSalida = ((contador-registros-1) == 0);
			
			Utils.debugTrace(true, sClassName, sMethod, "Lectura de "+sNombre+" finalizada.");
			Utils.debugTrace(true, sClassName, sMethod, "Actualizados "+registros+" registros.");
			Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+(contador-registros-1)+" registros erroneos.");

		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
        return bSalida;
	}

	public static boolean leerImpuestosRevisadas(String sNombre) 
	{
		String sMethod = "leerImpuestosRevisadas";

		boolean bSalida = false;
		
		File archivo = new File (ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+sNombre);
		FileReader fr;
		try 
		{
			fr = new FileReader (archivo);

			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";

			String sFinFichero = ValoresDefecto.DEF_FIN_FICHERO;
			

			Utils.debugTrace(true, sClassName, sMethod, "Leyendo fichero..");

			java.util.Date date= new java.util.Date();
			System.out.println("Inicio: " + new Timestamp(date.getTime()));

			int contador= 0 ;
			int registros = 0;
			
			//ArrayList<Comunidad> lista_comunidades = new ArrayList<Comunidad>();
			
				
			while((linea=br.readLine())!=null)
	        {
				contador++;
	    		if (linea.equals(sFinFichero))
	    			Utils.debugTrace(true, sClassName, sMethod, "Lectura finalizada!");
	    		else if (linea.length()< (Longitudes.IMPUESTOS_L-Longitudes.FILLER_IMPUESTOS_L) )
	    			Utils.debugTrace(true, sClassName, sMethod, "Error en linea "+contador);
	    		else
	    		{
	    			if (CLImpuestos.actualizaImpuestoLeido(linea))
	    				registros++;
	    		}
	        }
			
			br.close();

			bSalida = ((contador-registros-1) == 0);
			
			Utils.debugTrace(true, sClassName, sMethod, "Lectura de "+sNombre+" finalizada.");
			Utils.debugTrace(true, sClassName, sMethod, "Actualizados "+registros+" registros.");
			Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+(contador-registros-1)+" registros erroneos.");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
        return bSalida;
	}
	
	public static boolean splitter(String sNombre) throws IOException 
	{

		String sMethod = "splitter";

		boolean bSalida = false;
		
		//ArrayList<String> lista;


		Utils.debugTrace(true, sClassName, sMethod,
				"|"+sNombre+"|"+sNombre.substring(0, 3)+"|");
		
		
		
		if (sNombre.length() < 9)
		{
			Utils
			.debugTrace(true, sClassName, sMethod,
					"El archivo suministrado no pertenece a esta subaplicacion INFOCAM.");
		}
		else 
		{
			String sNombreOriginal = sNombre.substring(sNombre.length()-9);
			
			if (sNombreOriginal.toUpperCase().matches("("+ValoresDefecto.DEF_COAPII+")(AC|RG|PA|GA|PP|E1|E2|E3|E4)(\\.TXT)$"))
			{
				
				
				String sTipo = sNombreOriginal.substring(3, 5).toUpperCase();

				Utils.debugTrace(true, sClassName, sMethod,
						"Redirigiendo lectura...");

				System.out.println("Tipo:|" + sTipo + "|");

				ValoresDefecto.TIPOSFICHERO COSPII = ValoresDefecto.TIPOSFICHERO.valueOf(sTipo);

				switch (COSPII) {
				case AC:
					Utils.debugTrace(true, sClassName, sMethod,
							"Activos");
					bSalida = leerActivos(sNombre);
					//lista = new ArrayList<String>();
					break;
				case RG:
					Utils.debugTrace(true, sClassName, sMethod,
							"Rechazados");
					
					bSalida = leerGastosRevisados(sNombre);
					//lista = new ArrayList<String>(leerGastosValidados(sNombre));
					break;
				case PA:
					Utils.debugTrace(true, sClassName, sMethod,
							"Autorizados");
					bSalida = leerGastosRevisados(sNombre);
					//lista = new ArrayList<String>(leerGastosValidados(sNombre));
					break;
				case GA:
					Utils.debugTrace(true, sClassName, sMethod,
							"Gastos");
					
					Utils.debugTrace(true, sClassName, sMethod,
							"El archivo de gastos debe de ser primero supervisado por la entidad.");
					bSalida = leerGastosRevisados(sNombre);
					//lista = new ArrayList<String>(leerGastosValidados(sNombre));
					break;
				case PP:
					Utils.debugTrace(true, sClassName, sMethod,
							"Cierres");
					Utils
					.debugTrace(true, sClassName, sMethod,
							"El archivo de cierres debe comprobado por la entidad.");
					//bSalida = leerCierres(sNombre);
					//lista = new ArrayList<String>(leerCierres(sNombre));
					break;
				case E1:
					Utils.debugTrace(true, sClassName, sMethod,
							"Comunidades");
					bSalida = leerComunidadesRevisadas(sNombre);
					//lista = new ArrayList<String>();
					break;
				case E2:
					Utils.debugTrace(true, sClassName, sMethod,
							"Cuotas");
					bSalida = leerCuotasRevisadas(sNombre);
					//lista = new ArrayList<String>();
					break;
				case E3:
					Utils.debugTrace(true, sClassName, sMethod,
							"Referencias Catastrales");
					bSalida = leerReferenciasRevisadas(sNombre);
					//lista = new ArrayList<String>();
					break;
				case E4:
					Utils.debugTrace(true, sClassName, sMethod,
							"Impuestos");
					bSalida = leerImpuestosRevisadas(sNombre);
					//lista = new ArrayList<String>();
					break;
				default:
					Utils.debugTrace(true, sClassName, sMethod,
									"El archivo suministrado no coincide con el nombrado establecido:");
					Utils.debugTrace(true, sClassName, sMethod,
									"168XX.txt donde XX puede ser AC, RG, PA, GA, PP, E1, E2, E3 o E4. ");
					//lista = new ArrayList<String>();
					break;
				}

				Utils.debugTrace(true, sClassName, sMethod,
						"Operativa completa.");

			} 
			else
			{
				Utils.debugTrace(true, sClassName, sMethod,
								"El archivo suministrado no pertenece a esta subaplicacion INFOCAM.");
				//lista = new ArrayList<String>();
			}
			

		}
		return bSalida;
	}
	public static void main(String[] args) throws IOException 
	{
		String sMethod = "main";
		
		String sPrueba = "168AC3.txt";
		
		Utils.debugTrace(true, sClassName, sMethod,"Probando el archivo "+ sPrueba);
		splitter(sPrueba);
	}
}
