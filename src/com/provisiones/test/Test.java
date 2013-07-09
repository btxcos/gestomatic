package com.provisiones.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

import com.provisiones.dal.ConnectionManager;

import com.provisiones.types.Longitudes;
import com.provisiones.types.Posiciones;
import com.provisiones.dal.TableManager;

public class Test 
{
	static String sClassName = Test.class.getName();
	static boolean bEnable = true;

	public static void main(String[] args) throws IOException 
	{
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		TableManager.crearTablasCodigos(conn);
		
		com.provisiones.dal.ConnectionManager.CloseDBConnection(conn);

		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "Conexion Realizada");
		File archivo = new File ("C:\\168AC.txt");
		FileReader fr = new FileReader (archivo);
		BufferedReader br = new BufferedReader(fr);
		String linea = br.readLine();
		br.close();
		
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "linea:|"+linea.substring(0, 8)+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "linea:|"+linea.substring(Posiciones.AC_COACES_P, Posiciones.AC_COACES_P+Longitudes.COACES_L)+"|***");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "linea:|"+linea.substring(9, 17)+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "linea:|"+linea.substring(18, 19)+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "linea:|"+linea.substring(20, 21)+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "linea:|"+linea.substring(22, 23)+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "linea:|"+linea.substring(24, 83).trim()+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "linea:|"+linea.substring(84, 100).trim()+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "linea:|"+linea.substring(101, 105)+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "Tamaño BIARRE_L:"+com.provisiones.types.Longitudes.BIARRE_L);
		
				
	}

}