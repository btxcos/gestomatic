package com.provisiones.pl;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.LinkedList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLProvisiones;
import com.provisiones.ll.CLUsuarios;
import com.provisiones.misc.Utils;
import com.provisiones.types.Historial;

public class GestorSesion implements Serializable 
{

	private static final long serialVersionUID = 7221600887316983592L;
	
	private static Logger logger = LoggerFactory.getLogger(GestorSesion.class.getName());
	
	private String sUsuario = "";
	private String sClave = "";
	
	private String sMensaje = "";
	private boolean bComprobado = false;
	
	private String sDetalle = "";
	
	private LinkedList<Historial> historial = new  LinkedList<Historial>();
	
	
	private Connection conn = null;
	
	public GestorSesion()
	{
		borrarCamposLogin();
	}
	
	public void borrarCamposLogin()
	{
    	this.sUsuario = "";
    	this.sClave = "";
    	this.sMensaje = "";
    	this.bComprobado = false;
	}
	
	public void iniciaSesion()
	{
		if (!ConnectionManager.initDBDriver())
		{
			FacesMessage msg;
			
			String sMsg = "ERROR FATAL: No se ha podido iniciar una sesión con el servidor de base de datos. Por favor avise a soporte.";
			
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);

			FacesContext.getCurrentInstance().addMessage(null, msg);
			
			anulaSesion();
		}
	}
	
	public void anulaSesion()
	{
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		session.invalidate();
		
		try 
		{
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		} 
		catch (IOException e) 
		{
			logger.error("ERROR: No se puede acceder a la página de login.");
		}
	}
	
	public String cargarPortal() 
    {  
		String sPagina = "login.xhtml";
		
    	if (bComprobado)
    	{
    		logger.debug("Redirigiendo...");
    		sPagina = "index.xhtml";
			logger.info("Inicializando aplicación.");
			Utils.inicializarDirectorios();
			CLProvisiones.inicializaProvisiones();	
    	}
    	
    	return sPagina;
    }
	
	public void cerrarSesion()
	{
		FacesMessage msg;

		
		String sMsg = "Sesión cerrada";
		
		msg = new FacesMessage(sMsg);
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		if (conn != null)
		{
			logger.info(sMsg + ": "+ conn.toString());

			ConnectionManager.closeDBConnection(conn);
		}

		
		borrarCamposLogin();
		
		try 
		{
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		} 
		catch (IOException e) 
		{
			logger.error("ERROR: No se puede acceder a la página de login.");
		}
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		try
		{
			session.invalidate();
		}
		catch (Exception es)
		{
			logger.error("ERROR: Error terminando la sesión: " + es.getMessage());
		}
		
		//return "login.xhtml?facesRedirect=false";
	}
	
	
	public void login (ActionEvent actionEvent)
	{
		
		FacesMessage msg;
		
		if (sUsuario.equals(""))
		{
			sMensaje = "No se ha informado el campo 'Usuario'.";
			msg = Utils.pfmsgError(sMensaje);
			sMensaje = "Pulse 'Continuar' y revise los datos.";
			
			bComprobado = false;
		}
		else if (sClave.equals(""))
		{
			sMensaje = "No se ha informado el campo 'Contraseña'.";
			msg = Utils.pfmsgError(sMensaje);
			sMensaje = "Pulse 'Continuar' y revise los datos.";
			
			bComprobado = false;
		}
		else if (CLUsuarios.usuarioValido(sUsuario))
		{
			bComprobado = CLUsuarios.comprobarCredenciales(sUsuario, sClave);
			
			if (bComprobado)
			{
				if (CLUsuarios.estaConectado(sUsuario))
				{
					sMensaje = "Tiene una sesión abierta. Se cerrará para abrir una nueva. ";
					msg = Utils.pfmsgWarning(sMensaje);
				}
				else
				{
					sMensaje = "Acceso permitido.";
					msg = Utils.pfmsgInfo(sMensaje);
				}
				sMensaje = "Para entrar al portal pulse 'Continuar'.";
				
				conn = ConnectionManager.openDBConnection();
				logger.debug("Conexión:|"+conn.toString()+"|");
		
			}
			else
			{
				sMensaje = "La contraseña no es correcta.";
				msg = Utils.pfmsgError(sMensaje);
				sMensaje = "Pulse 'Continuar' y revise los datos.";
			}
		}
		else
		{
			sMensaje = "El usuario '"+sUsuario+"' no tiene acceso al sistema.";
			msg = Utils.pfmsgError(sMensaje);
			sMensaje = "Pulse 'Continuar' y revise los datos.";
		}
		
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		logger.debug("sMensaje:|"+sMensaje+"|");
		
		this.sClave = "";
		
	}

	public String getsUsuario() {
		return sUsuario;
	}

	public void setsUsuario(String sUsuario) {
		this.sUsuario = sUsuario;
	}

	public String getsClave() {
		return sClave;
	}

	public void setsClave(String sClave) {
		this.sClave = sClave;
	}

	public String getsMensaje() {
		return sMensaje;
	}

	public void setsMensaje(String sMensaje) {
		this.sMensaje = sMensaje;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public boolean isbComprobado() {
		return bComprobado;
	}

	public String getsDetalle() {
		return sDetalle;
	}

	public void setsDetalle(String sDetalle) {
		this.sDetalle = sDetalle;
	}

	public LinkedList<Historial> getHistorial() {
		return historial;
	}

	public void setHistorial(LinkedList<Historial> historial) {
		this.historial = historial;
	}
}
