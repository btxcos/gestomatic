package com.provisiones.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.misc.Utils;


public class ConnectionManager 
{
	private static String sConnector = "org.mariadb.jdbc.Driver";
	private static String sConnectorType = "jdbc:mariadb:";
	private static String sHostResource = "//localhost/glsl";
	private static String sLogin = "?user=root&password=glsl1234";
	
	private static Logger logger = LoggerFactory.getLogger(ConnectionManager.class.getName());
	

	public static Connection OpenDBConnection() 
	{

		try 
		{
			Class.forName(sConnector);
		} 
		catch (Exception ex) 
		{
			logger.error("ErrorMessage: {}", ex.getMessage());
			return null;
		}

		Connection conn = null;
		
		String sConnectionData = sConnectorType+sHostResource+sLogin;

		try 
		{
			
			//logger.debug("tiempo INI:|{}|", Utils.timeStamp());
			conn = DriverManager.getConnection(sConnectionData);
			//logger.debug("tiempo FIN:|{}|", Utils.timeStamp());

		} 
		catch (SQLException ex) 
		{
			logger.error("SQLException: {}", ex.getMessage());
			logger.error("SQLState: {}", ex.getSQLState());
			logger.error("VendorError: {}", ex.getErrorCode());
		}

		logger.debug("Conexión realizada.");
		return conn;

	}
	public static boolean CloseDBConnection (Connection conn)
	{
		try 
		{
				//logger.debug("tiempo INI:|{}|", Utils.timeStamp());
				conn.close();
				//logger.debug("tiempo FIN:|{}|", Utils.timeStamp());
		} 
		catch (SQLException ex) 
		{
			logger.error("SQLException: {}", ex.getMessage());
			logger.error("SQLState: {}", ex.getSQLState());
			logger.error("VendorError: {}", ex.getErrorCode());
		}
		//logger.debug("Desconexión realizada.");
		return true;
	}
}
