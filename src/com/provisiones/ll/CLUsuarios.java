package com.provisiones.ll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.qm.usuarios.QMUsuarios;

public class CLUsuarios 
{
	private static Logger logger = LoggerFactory.getLogger(CLUsuarios.class.getName());
	
	
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
