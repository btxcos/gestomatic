package com.provisiones.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.misc.Utils;
import com.provisiones.pl.GestorSesion;

public class ConnectionManager 
{
	private static String sConnector = "org.mariadb.jdbc.Driver";
	private static String sConnectorType = "jdbc:mariadb:";
	private static String sHostResource = "//localhost/glsl";
	//private static String sLogin = "?user=root&password=glsl1234";
	
	private static String sURL = sConnectorType+sHostResource;
	private static String sUser = "root";
	private static String sPass = "glsl1234";
	
	
	private static Logger logger = LoggerFactory.getLogger(ConnectionManager.class.getName());
	
	public static Connection getDBConnection() 
	{
		Connection conn = null; 
		try
		{
			conn = ((GestorSesion)((HttpSession) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorSesion")).getConn();
		}
		catch (NullPointerException e)
		{
			try 
			{
				if (!(conn == null) && conn.isValid(1))
				{
					logger.debug("Cerrando: "+conn.toString());
					ConnectionManager.closeDBConnection(conn);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			FacesMessage msg;

			String sMensaje = "ERROR: La sesión ha expirado.";
			
			msg = Utils.pfmsgError(sMensaje);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
			logger.error(sMensaje);

		}
		
		return conn;
	}
	
	public static String getUser() 
	{
		return ((GestorSesion)((HttpSession) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorSesion")).getsUsuario();
	}
	
	public static boolean initDBDriver()
	{
		boolean bSalida = true;
		try 
		{
			Class.forName(sConnector).newInstance();
		} 
		catch (Exception ex) 
		{
			logger.error("Error Message: "+ ex.getMessage());
			
			bSalida = false;
		}
		
		return bSalida;
	}
	
	

	public static Connection openDBConnection() 
	{



		Connection conn = null;
		
		//String sConnectionData = sConnectorType+sHostResource+sLogin;

		try 
		{
			
			//logger.debug("tiempo INI:|{}|", Utils.timeStamp());
			//conn = DriverManager.getConnection(sConnectionData);
			conn = DriverManager.getConnection(sURL,sUser,sPass);
			//logger.debug("tiempo FIN:|{}|", Utils.timeStamp());

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		}

		//logger.debug("Conexión realizada.");
		return conn;

	}
	
	public static boolean closeDBConnection (Connection conn)
	{
		if (conn != null)
		{
			try 
			{
					//logger.debug("tiempo INI:|{}|", Utils.timeStamp());
					conn.close();
					//logger.debug("tiempo FIN:|{}|", Utils.timeStamp());
			} 
			catch (SQLException ex)
			{
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			}
		}
		else
		{
			logger.error("ERROR: La conexión proporcionada no es válida.");
		}

		//logger.debug("Desconexión realizada.");
		return true;
	}
}
