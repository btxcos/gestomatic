package com.provisiones.ll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.qm.usuarios.QMUsuarios;

public class CLUsuarios 
{
	private static Logger logger = LoggerFactory.getLogger(CLUsuarios.class.getName());
	
	
	public static boolean comprobarCredenciales (String sUsuario, String sContrase�a)
	{
		logger.debug("sUsuario:|{}|",sUsuario);
		
		return QMUsuarios.getPassword(sUsuario).equals(sContrase�a);
	}
	
	public static boolean estaConectado (String sUsuario)
	{
		logger.debug("sUsuario:|{}|",sUsuario);
		
		//comprobar si est� conectado
		return false;
	}
	
	public static boolean usuarioValido (String sUsuario)
	{
		logger.debug("sUsuario:|{}|",sUsuario);
		
		return QMUsuarios.existeUsuario(sUsuario);
	}
	
}
