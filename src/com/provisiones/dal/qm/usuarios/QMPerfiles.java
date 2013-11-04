package com.provisiones.dal.qm.usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;

import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

public class QMPerfiles 
{
	private static Logger logger = LoggerFactory.getLogger(QMPerfiles.class.getName());
	
	public static final String TABLA = "perfiles_usuarios_tbl";

	public static final String CAMPO1  = "perfil_id";
	public static final String CAMPO2  = "perfil";
	public static final String CAMPO3  = "activo";

	public static boolean addPerfil(String sPerfil)

	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "INSERT INTO " + TABLA + " ("
			       + CAMPO2  + ","              
			       + CAMPO3  + 
			       ") VALUES ('"
			       + sPerfil + "',"
			       + ValoresDefecto.ACTIVO + " )";

		logger.debug(sQuery);
		
		try {

			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR PERFIL:|"+sPerfil+"|");
			
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


	public static boolean delPerfil(String sCodPerfil)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "DELETE FROM "
				+ TABLA + 
				" WHERE "
				+ CAMPO1  + " = '"+ sCodPerfil +"'";
		
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
	
	public static boolean getPerfilID(String sPerfil)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
				+ CAMPO1  +
				" FROM "
				+ TABLA + 
				" WHERE "
				+ CAMPO2  + " = '"+ sPerfil +"'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR PERFIL:|"+sPerfil+"|");

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
	
	public static boolean getEstado(String sCodPerfil)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
		
		String sEstado = "";
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
				+ CAMPO3  +
				" FROM "
				+ TABLA + 
				" WHERE "
				+ CAMPO1  + " = '"+ sCodPerfil +"'";
		
		logger.debug(sQuery);
		
		

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sEstado = rs.getString(CAMPO3);
					
					logger.debug("Encontrado el registro!");
					logger.debug(CAMPO3+":|"+sEstado+"|");

				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: sCodPerfil:|"+sCodPerfil+"|");

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return found;
	}
	
	public static boolean setEstado(String sCodPerfil, String sEstado)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO3 + " = "+ sEstado + 
				" WHERE "
				+ CAMPO1 + " = '" + sCodPerfil +"'";
		
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
