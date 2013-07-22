package com.provisiones.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;

import com.provisiones.dal.ConnectionManager;

import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;
import com.provisiones.types.DatosActivo;
import com.provisiones.types.Gasto;
import com.provisiones.types.Longitudes;
import com.provisiones.types.Posiciones;
import com.provisiones.dal.TableManager;
import com.provisiones.dal.qm.QMDatosActivos;

public class Test 
{
	static String sClassName = Test.class.getName();
	static boolean bEnable = true;

	public static void main(String[] args) throws IOException 
	{
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		//TableManager.crearTablasCodigos(conn);
		
		com.provisiones.dal.ConnectionManager.CloseDBConnection(conn);

		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "Conexion Realizada");
		File archivo = new File ("C:\\168AC.txt");
		FileReader fr = new FileReader (archivo);
		BufferedReader br = new BufferedReader(fr);
		String linea = "";
		int i = 26;
		String aChar = new Character((char)i).toString();
		//linea=br.readLine();
		int contador=0;
		Utils.standardIO2File("");//Salida por fichero de texto
		java.util.Date date= new java.util.Date();
		
		System.out.println("Inicio: " + new Timestamp(date.getTime()));
		
		/*String sLetras  = "qwerty     ";
		String sNumeros = "0000012345";
		System.out.println("|"+sLetras+"|");
		System.out.println("|"+sNumeros+"|");
		System.out.println("|"+Parser.limpiaCampoAlfanumerico(sLetras,"")+"|");
		System.out.println("|"+Parser.limpiaCampoNumerico(sNumeros)+"|");
		System.out.println("|"+Parser.formateaCampoAlfanumerico(sLetras,10)+"|");
		System.out.println("|"+Parser.formateaCampoNumerico(sNumeros,10)+"|");*/
		
		String sCOACES = "1";

		while((linea=br.readLine())!=null)
        {
			contador++;
    		if (linea.equals(aChar))
    			System.out.println("Lectura finalizada!");
    		else if (linea.length()< 881 )
    			System.out.println("Error en linea "+contador);
    		else
    		{
    			//System.out.println(linea.length());
    			DatosActivo activo = Parser.leerActivo(linea);
    			//activo.pintaActivo();
    			QMDatosActivos.addDatosActivo(activo);
    			//sCOACES = activo.getCOACES();

    			//activo.pintaActivo();
    		}
    		//System.out.println(contador+" OK!");
            
        }
		
		System.out.println("|"+QMDatosActivos.getDatosActivo(sCOACES).getNOVIAS()+"|");
		
		System.out.println("|"+Parser.escribirCierre("903845","20130721").length()+"|");
		
		//QMDatosActivos.delActivo("1234");
		System.out.println("Fin: " + new Timestamp(date.getTime()));
		
		br.close();
		
		

		

		
		
		
		
		/*com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "linea:|"+linea.substring(0, 8)+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "linea:|"+linea.substring(Posiciones.AC_COACES_P, Posiciones.AC_COACES_P+Longitudes.COACES_L)+"|***");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "linea:|"+linea.substring(9, 17)+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "linea:|"+linea.substring(18, 19)+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "linea:|"+linea.substring(20, 21)+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "linea:|"+linea.substring(22, 23)+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "linea:|"+linea.substring(24, 83).trim()+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "linea:|"+linea.substring(84, 100).trim()+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "linea:|"+linea.substring(101, 105)+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "Tamaño BIARRE_L:"+com.provisiones.types.Longitudes.BIARRE_L);*/
		
				
	}

}