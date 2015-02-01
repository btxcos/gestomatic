package com.provisiones.pl.usuarios;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLUsuarios;
import com.provisiones.misc.Utils;
import com.provisiones.types.usuarios.Usuario;

public class GestorUsuarios implements Serializable 
{
	private static final long serialVersionUID = -8581275303315090026L;
	
	private static Logger logger = LoggerFactory.getLogger(GestorUsuarios.class.getName());
	
	private String sLogin = "";
	private String sPassword = "";
	private String sNombre = "";
	private String sApellido1 = "";
	private String sApellido2 = "";	
	private String sContacto = "";
	private String sTipoUsuario = "";
	
	private String sNota = "";
	
		
	public GestorUsuarios()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorUsuarios...");	
		}
	}
	
	public void borrarCamposUsuario()
	{
    	this.sLogin = "";
    	this.sPassword = "";
    	this.sNombre = "";
    	this.sApellido1 = "";
    	this.sApellido2 = "";
    	this.sContacto = "";
    	this.sTipoUsuario = "";
		
	}
	
    public void limpiarNota(ActionEvent actionEvent) 
    {  
    	this.sNota = "";
    }

	
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	borrarCamposUsuario();
    }


  
	public void comprobarUsuario (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			if (sLogin.isEmpty())
			{
				sMsg = "ERROR: El nombre de usuario no puede ir vacio. Por favor, elija un nombre.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);

			}
			else if (CLUsuarios.usuarioValido(sLogin))
			{
				sMsg = "ERROR: El nombre de usuario '"+sLogin+"' ya se encuentra registrado en el sistema. Por favor, elija otro.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else
			{
				sMsg = "El usuario '"+sLogin+"' esta disponible.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}

	
	public void realizaAlta(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			try
			{
				if (sLogin.isEmpty() || sPassword.isEmpty())
				{
					sMsg = "ERROR: Los campos 'Nombre de usuario' y 'Contraseña' son obligatorios. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
		    	}
				else if (CLUsuarios.usuarioValido(sLogin))
				{
					sMsg = "ERROR: El nombre de usuario '"+sLogin+"' ya se encuentra registrado en el sistema. Por favor, elija otro.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
		    	}
				else if (sPassword.length()<5)
				{
					sMsg = "ERROR: La contraseña elegida es demasiado corta. Por favor, elija otra.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.error(sMsg);
				}
				else
				{
					Usuario usuario = new Usuario (
							sLogin,
							sPassword,
							sNombre,
							sApellido1,
							sApellido2,	
							sContacto,
							"",
							"",
							"1",	
							"");
					
					logger.debug(usuario.logUsuario());

					int iSalida = CLUsuarios.altaUsuario(usuario);
					
					switch (iSalida) 
					{
					case 0: //Sin errores
						
						sMsg = "El Usuario se ha registrado correctamente.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
						break;

					case -901: //Error 901 - error y rollback - error al crear la comuidad
						sMsg = "[FATAL] ERROR:901 - Se ha producido un error al registrar el usuario. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
					
					case -910: //Error 910 - error y rollback - error al conectar con la base de datos
						sMsg = "[FATAL] ERROR:910 - Se ha producido un error al conectar con la base de datos. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					default: //error generico
						sMsg = "[FATAL] ERROR:"+iSalida+" - La operacion solicitada ha producido un error desconocido. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
					}
				}
				
				logger.debug("Finalizadas las comprobaciones.");

			}
			catch(NumberFormatException nfe)
			{
				sMsg = "ERROR: El Activo debe ser numérico. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}

	public String getsLogin() {
		return sLogin;
	}

	public void setsLogin(String sLogin) {
		this.sLogin = sLogin.trim().toLowerCase();
	}

	public String getsPassword() {
		return sPassword;
	}

	public void setsPassword(String sPassword) {
		this.sPassword = sPassword.trim();
	}

	public String getsNombre() {
		return sNombre;
	}

	public void setsNombre(String sNombre) {
		this.sNombre = sNombre.trim();
	}

	public String getsApellido1() {
		return sApellido1;
	}

	public void setsApellido1(String sApellido1) {
		this.sApellido1 = sApellido1.trim();
	}

	public String getsApellido2() {
		return sApellido2;
	}

	public void setsApellido2(String sApellido2) {
		this.sApellido2 = sApellido2.trim();
	}

	public String getsContacto() {
		return sContacto;
	}

	public void setsContacto(String sContacto) {
		this.sContacto = sContacto.trim();
	}

	public String getsTipoUsuario() {
		return sTipoUsuario;
	}

	public void setsTipoUsuario(String sTipoUsuario) {
		this.sTipoUsuario = sTipoUsuario;
	}

	public String getsNota() {
		return sNota;
	}

	public void setsNota(String sNota) {
		this.sNota = sNota.trim();
	}

}

//Autor: Francisco Valverde Manjón