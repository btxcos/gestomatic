package com.provisiones.dal.qm.usuarios;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;

public class QMListaPerfilesRecursos 
{
	private static Logger logger = LoggerFactory.getLogger(QMPerfiles.class.getName());
	
	public static final String TABLA = "pp001_lista_perfiles_recursos_multi";

	public static final String CAMPO1  = "cod_perfil";
	public static final String CAMPO2  = "cod_recurso";
	public static final String CAMPO3  = "usuario_alta";
	public static final String CAMPO4  = "fecha_alta";

	public static boolean addRelacionPerfil(String sCodPerfil, String sCodRecurso, String sUsuario)

	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "INSERT INTO " + TABLA + " ("
			       + CAMPO1  + ","
			       + CAMPO2  + ","
			       + CAMPO3  + "," 
			       + CAMPO4  + 
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
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}


	public static boolean delRelacionPerfil(String sCodPerfil, String sCodRecurso)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "DELETE FROM "
				+ TABLA + 
				" WHERE ("
				+ CAMPO1  + " = '"+ sCodPerfil +"' AND "
				+ CAMPO2  + " = '"+ sCodRecurso +"')";
		
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
		//ConnectionManager.CloseDBConnection(conn);

		return bSalida;
	}
	

}
