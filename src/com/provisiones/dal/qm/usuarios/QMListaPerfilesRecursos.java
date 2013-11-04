package com.provisiones.dal.qm.usuarios;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

public class QMListaPerfilesRecursos 
{
	private static Logger logger = LoggerFactory.getLogger(QMPerfiles.class.getName());
	
	public static final String sTable = "lista_perfiles_recursos_multi";

	public static final String sField1  = "cod_perfil";
	public static final String sField2  = "cod_recurso";
	public static final String sField3  = "usuario_alta";
	public static final String sField4  = "fecha_alta";

	public static boolean addRelacionPerfil(String sCodPerfil, String sCodRecurso, String sUsuario)

	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "INSERT INTO " + sTable + " ("
			       + sField1  + ","
			       + sField2  + ","
			       + sField3  + "," 
			       + sField4  + 
			       ") VALUES ('"
			       + sCodPerfil + "',"
			       + sCodRecurso + "',"
			       + sUsuario  + "','"
			       + Utils.timeStamp() +  
			       " )";

		logger.debug(sQuery);
		
		try {

			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR sCodPerfil:|"+sCodPerfil+"|");
			logger.error("ERROR sCodRecurso:|"+sCodRecurso+"|");
			
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally
		{

			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}


	public static boolean delRelacionPerfil(String sCodPerfil, String sCodRecurso)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "DELETE FROM "
				+ sTable + 
				" WHERE ("
				+ sField1  + " = '"+ sCodPerfil +"' AND "
				+ sField2  + " = '"+ sCodRecurso +"')";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR sCodPerfil:|"+sCodPerfil+"|");
			logger.error("ERROR sCodRecurso:|"+sCodRecurso+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	

}
