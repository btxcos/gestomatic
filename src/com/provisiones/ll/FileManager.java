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

import com.provisiones.misc.Longitudes;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

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

		
		
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Leyendo fichero..");

		java.util.Date date= new java.util.Date();
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Inicio: " + new Timestamp(date.getTime()));

		int contador=0;
		int registros = 0;

		while((linea=br.readLine())!=null)
        {
			contador++;
    		if (linea.equals(aChar))
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Lectura finalizada!");
    		else if (linea.length()< (Longitudes.ACTIVOS_L-Longitudes.FILLER_ACTIVOS_L) )
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error en linea "+contador);
    		else
    		{
    			if (CLActivos.actualizaActivoLeido(linea))
    				registros++;
    		}
            
        }
		
		
		br.close();
		
		bSalida = ((contador-registros-1) == 0);
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Lectura de "+sNombre+" finalizada.");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Actualizados "+registros+" registros.");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+(contador-registros-1)+" registros erroneos.");
		
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

		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Leyendo fichero..");

		java.util.Date date= new java.util.Date();
		System.out.println("Inicio: " + new Timestamp(date.getTime()));

		int contador=0;
		int registros = 0;
		
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
    			if (CLGastos.actualizaGastoLeido(linea))
    				registros++;
    		}
            
        }
		
		br.close();
		
		bSalida = ((contador-registros-1) == 0);
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Lectura de "+sNombre+" finalizada.");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Actualizados "+registros+" registros.");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+(contador-registros-1)+" registros erroneos.");
			
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
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Leyendo fichero..");

		java.util.Date date= new java.util.Date();
		System.out.println("Inicio: " + new Timestamp(date.getTime()));

		int contador= 0 ;
		int registros = 0;
		bSalida = true;
		
		//String sNUDCOM = "";
		//ArrayList<Comunidad> lista_comunidades = new ArrayList<Comunidad>();
		
			
		while((linea=br.readLine())!=null)
        {
			contador++;

			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, 
					"TAMS (linea|"+linea.length()+
					"|Comunidades:|"+Longitudes.COMUNIDADES_L+
					"|Filler:|"+Longitudes.FILLER_COMUNIDADES_L+
					"| Resta:|"+(Longitudes.COMUNIDADES_L-Longitudes.FILLER_COMUNIDADES_L)+"|");

    		if (linea.equals(aChar))
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Lectura finalizada!");
    		else if (linea.length()< (Longitudes.COMUNIDADES_L-Longitudes.FILLER_COMUNIDADES_L) )
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error en linea "+contador);
    		else
    		{
    			if (CLComunidades.actualizaComunidadLeida(linea))
    				registros++;
    		}
        }
	
		br.close();
	
		bSalida = ((contador-registros-1) == 0);
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Lectura de "+sNombre+" finalizada.");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Actualizados "+registros+" registros.");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+(contador-registros-1)+" registros erroneos.");
		
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

		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Leyendo fichero..");

		java.util.Date date= new java.util.Date();
		System.out.println("Inicio: " + new Timestamp(date.getTime()));

		int contador= 0 ;
		int registros = 0;
		
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
    			if (CLCuotas.actualizaCuotaLeida(linea))
    				registros++;
    		}
        }
		
		br.close();
		
		bSalida = ((contador-registros-1) == 0);
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Lectura de "+sNombre+" finalizada.");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Actualizados "+registros+" registros.");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+(contador-registros-1)+" registros erroneos.");
		
        return bSalida;
	}
	
	public static boolean leerReferencias(String sNombre) throws IOException 
	{
		String sMethod = "leerReferencias";

		boolean bSalida = false;
		
		File archivo = new File ("C:\\"+sNombre);
		FileReader fr = new FileReader (archivo);
		BufferedReader br = new BufferedReader(fr);
		
		String linea = "";

		int i = 26; //Caracter ->
		String aChar = new Character((char)i).toString();

		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Leyendo fichero..");

		java.util.Date date= new java.util.Date();
		System.out.println("Inicio: " + new Timestamp(date.getTime()));

		int contador= 0 ;
		int registros = 0;
		
		//String sNUDCOM = "";
		//ArrayList<Comunidad> lista_comunidades = new ArrayList<Comunidad>();
		
			
		while((linea=br.readLine())!=null)
        {
			contador++;
    		if (linea.equals(aChar))
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Lectura finalizada!");
    		else if (linea.length()< (Longitudes.REFERENCIAS_L-Longitudes.FILLER_REFERENCIAS_L) )
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error en linea "+contador);
    		else
    		{
    			if (CLReferencias.actualizaReferenciaLeida(linea))
    				registros++;
    		}
        }
	
		br.close();
	
		bSalida = ((contador-registros-1) == 0);
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Lectura de "+sNombre+" finalizada.");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Actualizados "+registros+" registros.");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+(contador-registros-1)+" registros erroneos.");
		 
        return bSalida;
	}

	public static boolean leerImpuestos(String sNombre) throws IOException 
	{
		String sMethod = "leerImpuestos";

		boolean bSalida = false;
		
		File archivo = new File ("C:\\"+sNombre);
		FileReader fr = new FileReader (archivo);
		BufferedReader br = new BufferedReader(fr);
		
		String linea = "";

		int i = 26; //Caracter ->
		String aChar = new Character((char)i).toString();

		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Leyendo fichero..");

		java.util.Date date= new java.util.Date();
		System.out.println("Inicio: " + new Timestamp(date.getTime()));

		int contador= 0 ;
		int registros = 0;
		
		//ArrayList<Comunidad> lista_comunidades = new ArrayList<Comunidad>();
		
			
		while((linea=br.readLine())!=null)
        {
			contador++;
    		if (linea.equals(aChar))
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Lectura finalizada!");
    		else if (linea.length()< (Longitudes.IMPUESTOS_L-Longitudes.FILLER_IMPUESTOS_L) )
    			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error en linea "+contador);
    		else
    		{
    			if (CLImpuestos.actualizaImpuestoLeido(linea))
    				registros++;
    		}
        }
		
		br.close();

		bSalida = ((contador-registros-1) == 0);
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Lectura de "+sNombre+" finalizada.");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Actualizados "+registros+" registros.");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+(contador-registros-1)+" registros erroneos.");
		

        return bSalida;
	}
	
	public static boolean splitter(String sNombre) throws IOException {

		String sMethod = "splitter";

		boolean bSalida = false;
		
		//ArrayList<String> lista;
		

		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod,
				"|"+sNombre+"|"+sNombre.substring(0, 3)+"|");
		
		if (sNombre.substring(0, 3).equals(ValoresDefecto.DEF_COAPII)) 
		{
			String sTipo = sNombre.substring(3, 5).toUpperCase();

			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod,
					"Redirigiendo lectura...");

			System.out.println("Tipo:|" + sTipo + "|");

			TIPOSFICHERO COSPII = TIPOSFICHERO.valueOf(sTipo);

			switch (COSPII) {
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
				bSalida = leerGastosRevisados(sNombre);
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
		
		String sPrueba = "168AC3.txt";
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod,"Probando el archivo "+ sPrueba);
		splitter(sPrueba);
	}
}
