package com.provisiones.ll;

import java.sql.Connection;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.usuarios.QMUsuarios;
import com.provisiones.pl.GestorSesion;
import com.provisiones.types.usuarios.Usuario;

public final class CLUsuarios 
{
	private static Logger logger = LoggerFactory.getLogger(CLUsuarios.class.getName());

	private CLUsuarios(){}
	
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
		return QMUsuarios.getPassword(sUsuario).equals(sContraseña);
	}
	
	public static boolean estaConectado (String sUsuario)
	{
		//TODO comprobar si está conectado
		return false;
	}
	
	public static boolean usuarioValido (String sUsuario)
	{
		return QMUsuarios.existeUsuario(sUsuario);
	}
	
	public static int altaUsuario (Usuario NuevoUsuario)
	{		
		int iCodigo = -910;//Error de conexion
	
		Connection conexion = ConnectionManager.getDBConnection();
	
		if (conexion != null)
		{
			long liCodUsuario = QMUsuarios.addUsuario (ConnectionManager.getDBConnection(),NuevoUsuario);
			
			if (liCodUsuario > 0)
			{
				//OK
				iCodigo = 0;
			}
			else
			{
				//Error al crear el usuario
				iCodigo = -901;
			}
				
		}
		return iCodigo;
	}
	
}
