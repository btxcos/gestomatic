package com.provisiones.dal.qm.usuarios;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.misc.Utils;

public final class QMListaPerfilesRecursos 
{
	private static Logger logger = LoggerFactory.getLogger(QMPerfiles.class.getName());
	
	public static final String TABLA = "pp001_lista_perfiles_recursos_multi";

	public static final String CAMPO1  = "cod_perfil";
	public static final String CAMPO2  = "cod_recurso";
	public static final String CAMPO3  = "usuario_alta";
	public static final String CAMPO4  = "fecha_alta";

	private QMListaPerfilesRecursos(){}
	
	public static boolean addRelacionPerfil(Connection conexion, String sCodPerfil, String sCodRecurso, String sUsuario)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "INSERT INTO " 
					+ TABLA + 
					" ("
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
			
			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con exito!");
				
				bSalida = true;
			} 
			catch (SQLException ex) 
			{
				bSalida = false;
				
				logger.error("ERROR sCodPerfil:|"+sCodPerfil+"|");
				logger.error("ERROR sCodRecurso:|"+sCodRecurso+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}


	public static boolean delRelacionPerfil(Connection conexion, String sCodPerfil, String sCodRecurso)
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM "
					+ TABLA + 
					" WHERE ("
					+ CAMPO1  + " = '"+ sCodPerfil +"' AND "
					+ CAMPO2  + " = '"+ sCodRecurso +"')";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con exito!");
				
				bSalida = true;
			} 
			catch (SQLException ex) 
			{
				bSalida = false;
				
				logger.error("ERROR sCodPerfil:|"+sCodPerfil+"|");
				logger.error("ERROR sCodRecurso:|"+sCodRecurso+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	

}
