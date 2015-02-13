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

public final class ConnectionManager 
{
	private static Logger logger = LoggerFactory.getLogger(ConnectionManager.class.getName());
	
	private static String sConnector = "org.mariadb.jdbc.Driver";
	private static String sConnectorType = "jdbc:mariadb:";
	private static String sHostResource = "//localhost:3306/catalogo";
	//private static String sLogin = "?user="+sUser+"&password="+sPass;
	
	private static String sURL = sConnectorType+sHostResource;
	private static String sUser = "root";
	private static String sPass = "root1234";
	
	private ConnectionManager(){}

    public static boolean comprobarConexion()
    {
    	boolean bSalida = true;
    	
    	if (ConnectionManager.getDBConnection() == null)
    	{
    		FacesMessage msg;
    		
    		msg = Utils.pfmsgFatal("Se perdi� la sesi�n con el servidor. Por favor, salga y vuelva a iniciar una sesi�n.");
    		
    		FacesContext.getCurrentInstance().addMessage(null, msg);
    		
    		bSalida = false;
    	}
    	
    	return bSalida;
    }
	
	public static Connection getDBConnection() 
	{
		Connection conn = null; 
		try
		{
			conn = ((GestorSesion)((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorSesion")).getConn();
		}
		catch (NullPointerException e)
		{
			try 
			{
				if ((conn != null) && conn.isValid(1))
				{
					logger.debug("Cerrando: "+conn.toString());
					ConnectionManager.closeDBConnection(conn);
				}
			} 
			catch (SQLException e1) 
			{
				logger.warn("La conexi�n fue cerrada.");
			}
			
			
			/*try 
			{
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
			} 
			catch (IOException eL) 
			{
				logger.error("ERROR: No se puede acceder a la p�gina de login.");
			}*/
			
			logger.debug("Gestionando sesi�n...");
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			logger.debug("Hecho!");
			
			try
			{
				logger.debug("invalidando...");
				session.invalidate();
				logger.debug("session.invalidate()");
			}
			catch (Exception es)
			{
				logger.error("ERROR: Error terminando la sesi�n: " + es.getMessage());
			}
			
			logger.error("ERROR: La sesi�n ha expirado.");

		}
		
		return conn;
	}
	
	public static String getUser() 
	{
		String sUser = "";
		
		try
		{
			sUser = ((GestorSesion)((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorSesion")).getsUsuario(); 
		}
		catch (NullPointerException npe)
		{
			sUser = "";
			logger.error("ERROR: Ocurri� un problema al solicitar el usuario de sesi�n.");
		}
		return sUser;
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
			
			//conn.setAutoCommit(false);

		} 
		catch (SQLException ex) 
		{
    		FacesMessage msg;
    		
    		msg = Utils.pfmsgFatal("No se pudo abrir una conexi�n con el servidor. Por favor, avise a soporte.");
    		
    		FacesContext.getCurrentInstance().addMessage(null, msg);
    		
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		}

		//logger.debug("Conexi�n realizada.");
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
			logger.error("ERROR: La conexi�n proporcionada no es v�lida.");
		}

		//logger.debug("Desconexi�n realizada.");
		return true;
	}
}

//Autor: Francisco Valverde Manj�n
