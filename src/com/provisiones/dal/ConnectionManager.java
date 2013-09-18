package com.provisiones.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.misc.Utils;


public class ConnectionManager 
{
	static String sClassName = ConnectionManager.class.getName();
	
	private static String sConnector = "org.mariadb.jdbc.Driver";
	private static String sConnectorType = "jdbc:mysql:";
	private static String sHostResource = "//localhost/glsl";
	private static String sLogin = "?user=root&password=glsl1234";
	
	private static Logger logger = LoggerFactory.getLogger(sClassName);
	
	static boolean bEnable = false;

	public static Connection OpenDBConnection() 
	{

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
			
			logger.debug("tiempo INI:|{}|", Utils.timeStamp());
			conn = DriverManager.getConnection(sConnectionData);
			logger.debug("tiempo FIN:|{}|", Utils.timeStamp());

		} 
		catch (SQLException ex) 
		{
			logger.error("SQLException: {}", ex.getMessage());
			logger.error("SQLState: {}", ex.getSQLState());
			logger.error("VendorError: {}", ex.getErrorCode());
		}

		logger.info("Conexión realizada.");
		return conn;

	}
	public static boolean CloseDBConnection (Connection conn)
	{
		try 
		{
				conn.close();
		} 
		catch (SQLException ex) 
		{
			logger.error("SQLException: {}", ex.getMessage());
			logger.error("SQLState: {}", ex.getSQLState());
			logger.error("VendorError: {}", ex.getErrorCode());
		}
		logger.info("Desconexión realizada.");
		return true;
	}
}
