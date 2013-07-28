package com.provisiones.ll;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.provisiones.dal.qm.QMComunidades;
import com.provisiones.dal.qm.QMCuotas;
import com.provisiones.dal.qm.QMDatosActivos;
import com.provisiones.dal.qm.QMGastos;
import com.provisiones.dal.qm.QMListaCuotas;
import com.provisiones.dal.qm.QMListaGastos;
import com.provisiones.misc.Longitudes;
import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Cierre;
import com.provisiones.types.Comunidad;
import com.provisiones.types.Cuota;
import com.provisiones.types.DatosActivo;
import com.provisiones.types.Gasto;

public class FileManager 
{

	public enum TIPOSFICHERO 
	{
		AC, RG, PA, GA, PP, E1, E2, E3, E4
	}
	
	static String sClassName = FileManager.class.getName();
	static boolean bEnable = true;
	
	public static boolean guardarFichero(FileUploadEvent event) throws IOException 
	{
		String sMethod = "guardarFichero";

		boolean bSalida = false;
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod,"Guardando archivo...");
        UploadedFile file = event.getFile();
        String fileName = file.getFileName();
        //long fileSize = file.getSize();
        InputStream is = file.getInputstream();
        OutputStream out = new FileOutputStream("C:\\"+fileName);
        byte buf[] = new byte[1024];
        int len;
        while ((len = is.read(buf)) > 0)
            out.write(buf, 0, len);
        is.close();
        out.close();
        bSalida = true;
        com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod,"Completado con exito.");
        return bSalida;
	}
	
	
	public static boolean leerActivos(String sNombre) throws IOException 
	{

		String sMethod = "leerActivos";
		
		boolean bSalida = false;

		

		File archivo = new File ("C:\\"+sNombre);
		FileReader fr = new FileReader (archivo);
		BufferedReader br = new BufferedReader(fr);
		
		String linea = "";

		int i = 26; //Caracter ->
		String aChar = new Character((char)i).toString();

		Utils.standardIO2File("");//Salida por fichero de texto
		
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Leyendo fichero..");

		java.util.Date date= new java.util.Date();
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Inicio: " + new Timestamp(date.getTime()));

		int contador=0;
		while((linea=br.readLine())!=null)
        {
			contador++;
    		if (linea.equals(aChar))
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Lectura finalizada!");
    		else if (linea.length()< (Longitudes.ACTIVOS_L-Longitudes.FILLER_ACTIVOS_L) )
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error en linea "+contador);
    		else
    		{
    			DatosActivo activo = Parser.leerActivo(linea);
    			QMDatosActivos.addDatosActivo(activo);
    		}
            
        }
		
		
		br.close();
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Lectura de "+sNombre+" finalizada.");
		
        bSalida = true;
        
        return bSalida;
	}
	
	public static boolean leerGastosRevisados(String sNombre) throws IOException 
	{
		String sMethod = "leerGastosRevisados";

		boolean bSalida = false;
		
		File archivo = new File ("C:\\"+sNombre);
		FileReader fr = new FileReader (archivo);
		BufferedReader br = new BufferedReader(fr);
		
		String linea = "";

		int i = 26; //Caracter ->
		String aChar = new Character((char)i).toString();

		Utils.standardIO2File("");//Salida por fichero de texto
		
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Leyendo fichero..");

		java.util.Date date= new java.util.Date();
		System.out.println("Inicio: " + new Timestamp(date.getTime()));

		int contador=0;
		
		String sCodGastos = "";
		//ArrayList<String> lista_rechazados = new ArrayList<String>(); 
		
		while((linea=br.readLine())!=null)
        {
			contador++;
    		if (linea.equals(aChar))
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Lectura finalizada!");
    		else if (linea.length()< (Longitudes.GASTOS_L-Longitudes.FILLER_GASTOS_L) )
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error en linea "+contador);
    		else
    		{
    			Gasto gasto = Parser.leerGasto(linea);
    			
    			String sValidado = "";
    			
    			if (gasto.getCOTERR().equals(ValoresDefecto.DEF_COTERR))
    				sValidado = "V";
    			else
    				sValidado = "X";
    			
    			sCodGastos = QMListaGastos.getGastoID(gasto.getCOACES(), gasto.getNUPROF());
    			//lista_rechazados.add(sCodGastos);
    			QMListaGastos.setValidado(sCodGastos, sValidado);
    			QMGastos.modGasto(gasto, sCodGastos);

    		}
            
        }
		
		
		br.close();
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Lectura de "+sNombre+" finalizada.");
		
        
        return bSalida;
	}

	/*public static boolean leerCierres(String sNombre) throws IOException 
	{

		String sMethod = "leerCierres";
		
		boolean bSalida = false;
		
		File archivo = new File ("C:\\"+sNombre);
		FileReader fr = new FileReader (archivo);
		BufferedReader br = new BufferedReader(fr);
		
		String linea = "";

		int i = 26; //Caracter ->
		String aChar = new Character((char)i).toString();

		Utils.standardIO2File("");//Salida por fichero de texto
		
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Leyendo fichero..");

		java.util.Date date= new java.util.Date();
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Inicio: " + new Timestamp(date.getTime()));

		//ArrayList<String> lista_provisiones_cerradas = new ArrayList<String>();
		
		int contador=0;
		while((linea=br.readLine())!=null)
        {
			contador++;
    		if (linea.equals(aChar))
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Lectura finalizada!");
    		else if (linea.length()< (Longitudes.CIERRE_L-Longitudes.FILLER_CIERRE_L) )
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error en linea "+contador);
    		else
    		{
    			Cierre cierre = Parser.LeerCierre(linea);
    			//lista_provisiones_cerradas.add(cierre.getsNUPROF());
    		}
            
        }
		
		
		br.close();
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Lectura de "+sNombre+" finalizada.");
		
        return bSalida;
	}*/
	
	public static boolean leerComunidades(String sNombre) throws IOException 
	{
		String sMethod = "leerComunidades";

		boolean bSalida = false;
		
		File archivo = new File ("C:\\"+sNombre);
		FileReader fr = new FileReader (archivo);
		BufferedReader br = new BufferedReader(fr);
		
		String linea = "";

		int i = 26; //Caracter ->
		String aChar = new Character((char)i).toString();

		Utils.standardIO2File("");//Salida por fichero de texto
		
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Leyendo fichero..");

		java.util.Date date= new java.util.Date();
		System.out.println("Inicio: " + new Timestamp(date.getTime()));

		int contador=0;
		
		//String sNUDCOM = "";
		//ArrayList<Comunidad> lista_comunidades = new ArrayList<Comunidad>();
		
			
		while((linea=br.readLine())!=null)
        {
			contador++;
    		if (linea.equals(aChar))
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Lectura finalizada!");
    		else if (linea.length()< (Longitudes.COMUNIDADES_L-Longitudes.FILLER_COMUNIDADES_L) )
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error en linea "+contador);
    		else
    		{
    			Comunidad comunidad = Parser.leerComunidad(linea);
    			String sValidado = "";
    			
    			if (comunidad.getCOTDOR().equals(ValoresDefecto.DEF_COTDOR))
    				sValidado = "V";
    			else
    				sValidado = "X";
    			//lista_comunidades.add(comunidad);
    			
    			QMComunidades.modComunidad(comunidad, comunidad.getNUDCOM(), sValidado);***


    		}
            
        }
		
		
		br.close();
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Lectura de "+sNombre+" finalizada.");
		
        
        return bSalida;
	}
	
	public static boolean leerCuotas(String sNombre) throws IOException 
	{
		String sMethod = "leerCuotas";

		boolean bSalida = false;
		
		File archivo = new File ("C:\\"+sNombre);
		FileReader fr = new FileReader (archivo);
		BufferedReader br = new BufferedReader(fr);
		
		String linea = "";

		int i = 26; //Caracter ->
		String aChar = new Character((char)i).toString();

		Utils.standardIO2File("");//Salida por fichero de texto
		
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Leyendo fichero..");

		java.util.Date date= new java.util.Date();
		System.out.println("Inicio: " + new Timestamp(date.getTime()));

		int contador=0;
		
		//ArrayList<Comunidad> lista_comunidades = new ArrayList<Comunidad>();
		
			
		while((linea=br.readLine())!=null)
        {
			contador++;
    		if (linea.equals(aChar))
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Lectura finalizada!");
    		else if (linea.length()< (Longitudes.CUOTAS_L-Longitudes.FILLER_CUOTAS_L) )
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error en linea "+contador);
    		else
    		{
    			Cuota cuota = Parser.leerCuota(linea);
    			
    			String sBKCOTDOR = ValoresDefecto.DEF_COTDOR;
    			String sBKOBDEER = ValoresDefecto.DEF_OBDEER.trim();
    			
    			String sValidado = "";
    			
    			if (cuota.getCOTDOR().equals(ValoresDefecto.DEF_COTDOR))
    			{
    				sValidado = "V";
    				sBKOBDEER = cuota.getOBDEER();
    			}
    			else
    			{
    				sValidado = "X";
        			sBKCOTDOR = cuota.getCOTDOR();
        			sBKOBDEER = cuota.getOBDEER();
        			cuota.setCOTDOR(ValoresDefecto.DEF_COTDOR);

    			}
 			
    			cuota.setOBDEER(ValoresDefecto.DEF_OBDEER.trim());
    			
  				   				
    			String sCodCuota = QMCuotas.getCuotaID(cuota);
    			
    			cuota.setCOTDOR(sBKCOTDOR);
    			cuota.setCOTDOR(sBKOBDEER);
  				
  				QMCuotas.modCuota(cuota, sCodCuota);
  				QMListaCuotas.setValidado(sCodCuota, sValidado);

    		}
            
        }
		
		
		br.close();
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Lectura de "+sNombre+" finalizada.");
		
        
        return bSalida;
	}
	
	public static boolean splitter(String sNombre) throws IOException {

		String sMethod = "splitter";

		boolean bSalida = false;
		
		//ArrayList<String> lista;

		if (sNombre.substring(0, 1).equals("")) 
		{
			String sTipo = sNombre.substring(3, 5).toUpperCase();

			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod,
					"Redirigiendo lectura...");

			System.out.println("Tipo:|" + sTipo + "|");

			TIPOSFICHERO Tipo = TIPOSFICHERO.valueOf(sTipo);

			switch (Tipo) {
			case AC:
				com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod,
						"Activos");
				bSalida = leerActivos(sNombre);
				//lista = new ArrayList<String>();
				break;
			case RG:
				com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod,
						"Rechazados");
				
				bSalida = leerGastosRevisados(sNombre);
				//lista = new ArrayList<String>(leerGastosValidados(sNombre));
				break;
			case PA:
				com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod,
						"Autorizados");
				bSalida = leerGastosRevisados(sNombre);
				//lista = new ArrayList<String>(leerGastosValidados(sNombre));
				break;
			case GA:
				com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod,
						"Gastos");
				
				com.provisiones.misc.Utils
				.debugTrace(true, sClassName, sMethod,
						"El archivo de gastos debe de ser primero supervisado por la entidad.");
				//bSalida = leerGastosRevisados(sNombre);
				//lista = new ArrayList<String>(leerGastosValidados(sNombre));
				break;
			case PP:
				com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod,
						"Cierres");
				com.provisiones.misc.Utils
				.debugTrace(true, sClassName, sMethod,
						"El archivo de cierres debe comprobado por la entidad.");
				//bSalida = leerCierres(sNombre);
				//lista = new ArrayList<String>(leerCierres(sNombre));
				break;
			case E1:
				com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod,
						"Comunidades");
				bSalida = leerComunidades(sNombre);
				//lista = new ArrayList<String>();
				break;
			case E2:
				com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod,
						"Cuotas");
				bSalida = leerCuotas(sNombre);
				//lista = new ArrayList<String>();
				break;
			case E3:
				com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod,
						"Referencias Catastrales");
				bSalida = leerReferencias(sNombre);
				//lista = new ArrayList<String>();
				break;
			case E4:
				com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod,
						"Impuestos");
				bSalida = leerImpuestos(sNombre);
				//lista = new ArrayList<String>();
				break;
			default:
				com.provisiones.misc.Utils
						.debugTrace(true, sClassName, sMethod,
								"El archivo suministrado no coincide con el nombrado establecido:");
				com.provisiones.misc.Utils
						.debugTrace(true, sClassName, sMethod,
								"168XX.txt donde XX puede ser AC, RG, PA, GA, PP, E1, E2, E3 o E4. ");
				//lista = new ArrayList<String>();
				break;
			}

			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod,
					"Operativa completa.");

		} 
		else
		{
			com.provisiones.misc.Utils
					.debugTrace(true, sClassName, sMethod,
							"El archivo suministrado no pertenece a esta subaplicacion INFOCAM.");
			//lista = new ArrayList<String>();
		}
		return bSalida;
	}
	public static void main(String[] args) throws IOException 
	{
		String sMethod = "main";

		splitter("168ga2.txt");
	}
}
