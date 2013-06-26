package com.provisiones.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Utils 
{
	
	public static void debugTrace(boolean bEnable, String sClass, String sMethod, String sMsg)
	{
		if (bEnable)
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
	
}
