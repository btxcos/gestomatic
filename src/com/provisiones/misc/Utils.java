package com.provisiones.misc;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Utils 
{
	
	public static void debugTrace(boolean bEnable, String sClass, String sMethod, String sMsg)
	{
		boolean bContrazas = true;
		//String sTimeStamp = "";
		
		if (bContrazas && bEnable)
		{
			System.out.println("["+sClass+"."+sMethod+"] "+sMsg);
		}
	}
	
	public static void debugTraceArrayList(boolean bEnable, String sClass, String sMethod, ArrayList<String> result)
	{
		if (bEnable)
		{
			for (int j=0;j<result.size();j++)
			{
				System.out.println("["+sClass+"."+sMethod+"] |"+result.get(j)+"|");
			}
		}
	}

	public static boolean closeResultSet ( ResultSet rs, String sClassName, String sMethod)
	{
		boolean bExit = true;
		if (rs != null) 
		{
			try 
			{
				rs.close();
			} 
			catch (SQLException sqlEx) 
			{
				debugTrace(true, sClassName, sMethod, "ERROR: Unspected connection close.");
				bExit = false;
			}
			rs = null;
		}
		return bExit;
		
	}

	public static boolean closeStatement( Statement stmt, String sClassName, String sMethod)
	{
		boolean bExit = true;
		if (stmt != null) 
		{
			try 
			{
				stmt.close();
			} 
			catch (SQLException sqlEx) 
			{
				debugTrace(true, sClassName, sMethod, "ERROR: Unspected connection close.");
				bExit = false;
				
			}
			stmt = null;
		}
		return bExit;
		
	}
	public static String FormatPath(String sPath)
	{
		String sResultPath = "";
		
		{
			
		}
		
		return sResultPath;
	}
	public static void standardIO2File(String fileName){
		 
        if(fileName.equals("")){//Si viene vacío usamos este por defecto
 
            fileName="C:\\javalog.txt";
 
        }
 
        try {
 
            //Creamos un printstream sobre el archivo permitiendo añadir al
 
            //final para no sobreescribir.
 
            PrintStream ps = new PrintStream(new BufferedOutputStream(
 
                    new FileOutputStream(new File(fileName),true)),true);
 
            //Redirigimos entrada y salida estandar
 
            System.setOut(ps);
 
            System.setErr(ps);
 
        } catch (FileNotFoundException ex) {
 
            System.err.println("Se ha producido una excepción FileNotFoundException");
 
        }
 
    }
	
}
