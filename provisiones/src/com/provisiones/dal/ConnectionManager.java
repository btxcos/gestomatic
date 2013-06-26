package com.provisiones.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager 
{
	static String sClassName = ConnectionManager.class.getName();
	
	private static String sConnector = "org.mariadb.jdbc.Driver";
	private static String sConnectorType = "jdbc:mysql:";
	private static String sHostResource = "//localhost/glsl";
	private static String sLogin = "?user=root&password=glsl1234";
	
	static boolean bEnable = false;

	public static Connection OpenDBConnection() 
	{
		String sMethod = "OpenDBConnection";
		try 
		{

			Class.forName(sConnector);
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			return null;
		}

		Connection conn = null;
		
		String sConnectionData = sConnectorType+sHostResource+sLogin;

		try 
		{

			conn = DriverManager.getConnection(sConnectionData);

		} 
		catch (SQLException ex) 
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		Utils.debugTrace(bEnable,sClassName,sMethod,"Conectado con exito!");
		return conn;

	}
	public static boolean CloseDBConnection (Connection conn)
	{
		String sMethod = "CloseDBConnection";
		try 
		{
				conn.close();
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());
		}
		Utils.debugTrace(bEnable,sClassName,sMethod,"Desconexion realizada.");
		return true;
	}
}
