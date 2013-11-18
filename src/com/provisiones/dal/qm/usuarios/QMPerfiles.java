package com.provisiones.dal.qm.usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

public class QMPerfiles 
{
	private static Logger logger = LoggerFactory.getLogger(QMPerfiles.class.getName());
	
	public static final String TABLA = "pp001_perfiles_usuarios_tbl";

	public static final String CAMPO1  = "perfil_id";
	public static final String CAMPO2  = "perfil";
	public static final String CAMPO3  = "activo";

	public static boolean addPerfil(Connection conexion, String sPerfil)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "INSERT INTO " 
						+ TABLA + 
						" ("
						+ CAMPO2  + ","              
						+ CAMPO3  + 
						") VALUES ('"
						+ sPerfil + "',"
						+ ValoresDefecto.ACTIVO + 
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
				
				logger.error("ERROR PERFIL:|"+sPerfil+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}


	public static boolean delPerfil(Connection conexion, String sCodPerfil)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM "
					+ TABLA + 
					" WHERE "
					+ CAMPO1  + " = '"+ sCodPerfil +"'";
			
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

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean getPerfilID(Connection conexion, String sPerfil)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
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
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con exito!");
				
				bSalida = true;
			} 
			catch (SQLException ex) 
			{
				bSalida = false;
				
				logger.error("ERROR PERFIL:|"+sPerfil+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public boolean getEstado(Connection conexion, String sCodPerfil)
	{
		boolean bEstado = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
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
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");
				
				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						bEstado = rs.getBoolean(CAMPO3);
						
						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO3+":|"+bEstado+"|");
					}
				}
				if (bEncontrado == false) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				bEstado = false;

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
		}

		return bEstado;
	}
	
	public static boolean setEstado(Connection conexion, String sCodPerfil, String sEstado)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
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
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con exito!");
				
				bSalida = true;
			} 
			catch (SQLException ex) 
			{
				bSalida = false;
				
				logger.error("ERROR sCodPerfil:|"+sCodPerfil+"|");

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
