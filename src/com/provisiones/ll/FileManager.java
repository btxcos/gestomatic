package com.provisiones.ll;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.provisiones.dal.qm.QMDatosActivos;
import com.provisiones.dal.qm.QMGastos;
import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;
import com.provisiones.types.DatosActivo;
import com.provisiones.types.Gasto;
import com.provisiones.types.Longitudes;

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
		boolean bSalida = false;
		
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
        
        return bSalida;
	}
	
	
	public static boolean leerActivos(String sNombre) throws IOException 
	{

		boolean bSalida = false;

		

		File archivo = new File ("C:\\"+sNombre);
		FileReader fr = new FileReader (archivo);
		BufferedReader br = new BufferedReader(fr);
		
		String linea = "";

		int i = 26; //Caracter ->
		String aChar = new Character((char)i).toString();

		Utils.standardIO2File("");//Salida por fichero de texto
		
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "leerActivos", "Leyendo fichero..");

		java.util.Date date= new java.util.Date();
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "leerActivos", "Inicio: " + new Timestamp(date.getTime()));

		int contador=0;
		while((linea=br.readLine())!=null)
        {
			contador++;
    		if (linea.equals(aChar))
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, "leerActivos", "Lectura finalizada!");
    		else if (linea.length()< (Longitudes.ACTIVOS_L-Longitudes.FILLER_ACTIVOS_L) )
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, "leerActivos", "Error en linea "+contador);
    		else
    		{
    			DatosActivo activo = Parser.leerActivo(linea);
    			QMDatosActivos.addDatosActivo(activo);
    		}
            
        }
		
		
		br.close();
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "leerActivos", "Lectura de "+sNombre+" finalizada.");
		
        bSalida = true;
        
        return bSalida;
	}

	public static boolean leerGastosRechazados(String sNombre) throws IOException 
	{

		boolean bSalida = false;

		

		File archivo = new File ("C:\\"+sNombre);
		FileReader fr = new FileReader (archivo);
		BufferedReader br = new BufferedReader(fr);
		
		String linea = "";

		int i = 26; //Caracter ->
		String aChar = new Character((char)i).toString();

		Utils.standardIO2File("");//Salida por fichero de texto
		
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "leerGastosRechazados", "Leyendo fichero..");

		java.util.Date date= new java.util.Date();
		System.out.println("Inicio: " + new Timestamp(date.getTime()));

		int contador=0;
		while((linea=br.readLine())!=null)
        {
			contador++;
    		if (linea.equals(aChar))
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, "leerGastosRechazados", "Lectura finalizada!");
    		else if (linea.length()< (Longitudes.GASTOS_L-Longitudes.FILLER_GASTOS_L) )
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, "leerGastosRechazados", "Error en linea "+contador);
    		else
    		{
    			Gasto gasto = Parser.leerGasto(linea);
    			QMGastos.addGasto(gasto);
    		}
            
        }
		
		
		br.close();
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "leerGastosRechazados", "Lectura de "+sNombre+" finalizada.");
		
        bSalida = true;
        
        return bSalida;
	}
	
	public static boolean splitter(String sNombre) throws IOException 
	{
		boolean bSalida = false;
		
		String sTipo = sNombre.substring(3,5).toUpperCase();
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "splitter", "Redirigiendo lectura...");
		
		System.out.println("Tipo:|"+sTipo+"|");
		
		TIPOSFICHERO Tipo = TIPOSFICHERO.valueOf(sTipo);
		

		switch (Tipo) {
		case AC:
			System.out.println("Activos");
			leerActivos(sNombre);
			break;
		case RG:
			System.out.println("Rechazados");
			//leerGastosRechazados(sNombre);
			break;
		case PA:
			System.out.println("Autorizados");
			break;
		case GA:
			System.out.println("Gastos");
			leerGastosRechazados(sNombre);
			break;
		case PP:
			System.out.println("Cierres");
			break;
		case E1:
			System.out.println("Comunidades");
			break;
		case E2:
			System.out.println("Cuotas");
			break;
		case E3:
			System.out.println("Referencias Catastrales");
			break;
		case E4:
			System.out.println("Impuestos");
			break;
		default:
			System.out.println("error");
			break;
		}
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "splitter", "Operativa completa.");
        bSalida = true;
        
        return bSalida;
	}
	public static void main(String[] args) throws IOException 
	{
		splitter("168ga2.txt");
	}
}
