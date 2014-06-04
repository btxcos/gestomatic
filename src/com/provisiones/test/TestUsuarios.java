package com.provisiones.test;

import java.io.IOException;
import java.sql.Connection;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.usuarios.QMUsuarios;
import com.provisiones.types.usuarios.Usuario;

public class TestUsuarios 
{

	
	public static void main(String[] args) throws IOException 
	{
		String sLogin = "";
		String sPassword = "";
		String sNombre = "";
		String sApellido1 = "";
		String sApellido2 = "";	
		String sContacto = "";
		String sFechaAlta = "";
		String sFechaModificacion = "";
		String sTipoUsuario = "1";
		String sActivo = "";
		
		
		Usuario usuario = new Usuario(
				sLogin,
				sPassword,
				sNombre,
				sApellido1,
				sApellido2,	
				sContacto,
				sFechaAlta,
				sFechaModificacion,
				sTipoUsuario,	
				sActivo
				);
		
		Connection conx = ConnectionManager.openDBConnection();
		
		QMUsuarios.addUsuario(conx, usuario);
		
		ConnectionManager.closeDBConnection(conx);
	}

}
