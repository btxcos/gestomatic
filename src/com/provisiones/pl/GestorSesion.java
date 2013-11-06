package com.provisiones.pl;

import java.io.Serializable;
import java.sql.Connection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLUsuarios;

public class GestorSesion implements Serializable 
{

	private static final long serialVersionUID = 7221600887316983592L;
	
	private static Logger logger = LoggerFactory.getLogger(GestorSesion.class.getName());
	
	private String sUsuario = "";
	private String sClave = "";
	
	private String sMensaje = "";
	private boolean bComprobado = false;
	
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
	
	public String cargarPortal() 
    {  
		String sPagina = "login.xhtml";
		
    	if (bComprobado)
    	{
    		logger.debug("Redirigiendo...");
    		sPagina = "index.xhtml";
    	}
    	
    	return sPagina;
    }
	
	public void cerrarSesion(ActionEvent actionEvent)
	{
		FacesMessage msg;

		logger.debug(conn.toString());
		ConnectionManager.closeDBConnection(conn);
		
		msg = new FacesMessage(sMensaje);
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		logger.debug("sMensaje:|"+sMensaje+"|");
		
		borrarCamposLogin();
	}
	
	
	public void login (ActionEvent actionEvent)
	{
		
		FacesMessage msg;
		
		if (sUsuario.equals(""))
		{
			sMensaje = "No se ha informado el campo 'Usuario'.";
		}
		else if (sClave.equals(""))
		{
			sMensaje = "No se ha informado el campo 'Clave'.";
		}
		else if (CLUsuarios.usuarioValido(sUsuario))
		{
			bComprobado = CLUsuarios.comprobarCredenciales(sUsuario, sClave);
			
			if (bComprobado)
			{
				if (CLUsuarios.estaConectado(sUsuario))
				{
					sMensaje = "Tiene una sesión abierta. Se cerrará para abrir una nueva. ";
				}
				else
				{
					sMensaje = "La sesión se ha registrado correctamente.";
				}
				
				conn = ConnectionManager.openDBConnection();
				logger.debug("Conexión:|"+conn.toString()+"|");
		
			}
			else
			{
				sMensaje = "La contraseña no es correcta.";
			}
		}
		else
		{
			sMensaje = "El usuario '"+sUsuario+"' no está registrado en el sistema.";
		}
		



		msg = new FacesMessage(sMensaje);
		
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

	public boolean isbComprobado() {
		return bComprobado;
	}


}
