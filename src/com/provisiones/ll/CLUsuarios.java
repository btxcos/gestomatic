package com.provisiones.ll;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.qm.usuarios.QMUsuarios;
import com.provisiones.pl.GestorSesion;

public class CLUsuarios 
{
	private static Logger logger = LoggerFactory.getLogger(CLUsuarios.class.getName());
	
	public static boolean comprobarConexion()
	{
		boolean bConectado = true;
		
		
		try
		{
			bConectado = ((GestorSesion)((HttpSession) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorSesion")).isbComprobado();
		}
		catch (NullPointerException npe)
		{
			bConectado = false;
		}
		
		logger.debug("Conectado:"+bConectado);
		
		if (bConectado)
		{
			//comprobar permisos
			logger.debug("Coprobando permisos...");	
		}
		
		return bConectado;
	}
	
	public static boolean comprobarCredenciales (String sUsuario, String sContraseña)
	{
		logger.debug("sUsuario:|{}|",sUsuario);
		
		return QMUsuarios.getPassword(sUsuario).equals(sContraseña);
	}
	
	public static boolean estaConectado (String sUsuario)
	{
		logger.debug("sUsuario:|{}|",sUsuario);
		
		//comprobar si está conectado
		return false;
	}
	
	public static boolean usuarioValido (String sUsuario)
	{
		logger.debug("sUsuario:|{}|",sUsuario);
		
		return QMUsuarios.existeUsuario(sUsuario);
	}
	
}
