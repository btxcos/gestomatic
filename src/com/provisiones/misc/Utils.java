package com.provisiones.misc;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Utils 
{
	static String sClassName = Utils.class.getName();
	
	static boolean bTraza = true;
	
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
				debugTrace(bTraza, sClassName, sMethod, "ERROR: Unspected connection close.");
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
				debugTrace(bTraza, sClassName, sMethod, "ERROR: Unspected connection close.");
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
	
	public static String compruebaPago(boolean bCodDevolucion, String sTipoPago)
	{
		String sMethod = "compruebaPago";
			
		String sTipo = bCodDevolucion ? "5"+ sTipoPago : sTipoPago;
		
		debugTrace(bTraza, sClassName, sMethod, "Codigo de pago:|"+sTipo+"|");
		
		
		return sTipo;
	}

	public static String compruebaImporte(boolean bNegativo, String sImporte)
	{
		String sMethod = "compruebaImporte";
		
		String sImporteReal = sImporte.replaceFirst("-", "");
		
		if (sImporte.length()>3)
		{
			String sEuros = sImporteReal.substring(0, sImporte.length()-3);
			String sCentimos = sImporteReal.substring(sImporte.length()-2,sImporte.length());
		
			debugTrace(bTraza, sClassName, sMethod, "sEuros:|"+sEuros+"|");
			debugTrace(bTraza, sClassName, sMethod, "sCentimos:|"+sCentimos+"|");
		
			
			sImporteReal = bNegativo ? "-"+ sEuros + sCentimos : sEuros + sCentimos;
		}
		else if (sImporte.equals(""))
			sImporteReal= "0";
		
		debugTrace(bTraza, sClassName, sMethod, "Importe:|"+sImporteReal+"|");
		
		return sImporteReal;
	}

	public static String compruebaFecha(String sFecha)
	{
		String sMethod = "compruebaFecha";
		
		debugTrace(bTraza, sClassName, sMethod, "Fecha:|"+sFecha+"|");
		
		String sFechaFormateada = "";
		
		if (!sFecha.equals(""))
		{
			String sDia = sFecha.substring(0, 2);
			String sMes = sFecha.substring(3, 5);
			String sAño = sFecha.substring(6, 10);
		
			debugTrace(bTraza, sClassName, sMethod, "sDia:|"+sDia+"|");
			debugTrace(bTraza, sClassName, sMethod, "sMes:|"+sMes+"|");
			debugTrace(bTraza, sClassName, sMethod, "sAño:|"+sAño+"|");
		
		
			
			sFechaFormateada = sAño+sMes+sDia;
		
			debugTrace(bTraza, sClassName, sMethod, "Fecha:|"+sFechaFormateada+"|");
		
			try 
			{
				DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				Date myDate = formatter.parse(sFechaFormateada);
				debugTrace(bTraza, sClassName, sMethod,"|"+myDate+"|");
				formatter.setLenient(false);
				myDate = formatter.parse(sFechaFormateada);
				debugTrace(bTraza, sClassName, sMethod,"|"+myDate+"|");
			} 
			catch (ParseException e) 
			{
				sFechaFormateada = "#";
			} 
		}
		else
			sFechaFormateada = "0";
		
		return sFechaFormateada;
	}
	
	public static String recuperaImporte(boolean bNegativo, String sImporte)
	{
		String sMethod = "recuperaImporte";
		
		String sImporteReal = "";
		
		if (sImporte.length()>3)
		{
			String sEuros = sImporte.substring(0, sImporte.length()-2);
			String sCentimos = sImporte.substring(sImporte.length()-2,sImporte.length());
		
			debugTrace(bTraza, sClassName, sMethod, "sEuros:|"+sEuros+"|");
			debugTrace(bTraza, sClassName, sMethod, "sCentimos:|"+sCentimos+"|");
		
			
			sImporteReal = bNegativo ? "-"+ sEuros + "." + sCentimos : sEuros + "." + sCentimos;
		}
		debugTrace(bTraza, sClassName, sMethod, "Importe:|"+sImporteReal+"|");
		
		return sImporteReal;
	}
	
	public static String recuperaFecha(String sFecha)
	{
		String sMethod = "recuperaFecha";
		
		debugTrace(bTraza, sClassName, sMethod, "Fecha:|"+sFecha+"|");
		
		String sFechaFormateada = "";
		
		if (!sFecha.equals("0"))
		{
			String sAño = sFecha.substring(0, 4);
			String sMes = sFecha.substring(4, 6);
			String sDia = sFecha.substring(6, 8);
			
		
			debugTrace(bTraza, sClassName, sMethod, "sDia:|"+sDia+"|");
			debugTrace(bTraza, sClassName, sMethod, "sMes:|"+sMes+"|");
			debugTrace(bTraza, sClassName, sMethod, "sAño:|"+sAño+"|");
		
		
			
			sFechaFormateada = sDia+"/"+sMes+"/"+sAño;
		
			debugTrace(bTraza, sClassName, sMethod, "Fecha:|"+sFechaFormateada+"|");
		}
		
		return sFechaFormateada;
	}
}
