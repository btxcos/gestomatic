package com.provisiones.dal.qm;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;

public class QMRegistroActivos implements Serializable 
{
	private static Logger logger = LoggerFactory.getLogger(QMRegistroActivos.class.getName());
	
	private static final long serialVersionUID = -6800531057724764009L;
	
	public static final String TABLA = "pp002_registro_activos_multi";

	//identificadores
	public static final String CAMPO1  = "cod_coaces";
	public static final String CAMPO2  = "fecha_registro";

	//Campos de control
	public static final String CAMPO3  = "usuario_carga";
	public static final String CAMPO4  = "fecha_carga";

	
	public static boolean addRelacionActivo(Connection conexion, String sCodActivo)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			String sUsuario = ConnectionManager.getUser();
			
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
				       + sCodActivo + "','" 
				       + Utils.timeStamp() + "','"
				       + sUsuario + "','"
				       + Utils.timeStamp() +
				       "' )";
			
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

				logger.error("ERROR Activo:|"+sCodActivo+"|");
				//logger.error("ERROR Movimiento:|"+sFechaRegistro+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
			}
		}	

		return bSalida;
	}
	
	public static boolean modRelacionActivo(Connection conexion, String sCodActivo)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			String sUsuario = ConnectionManager.getUser();
			
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO3  + " = '"+ sUsuario + "', "
					+ CAMPO4  + " = '"+ Utils.timeStamp() + "' "+
					" WHERE " 
					+ CAMPO1  + " = '"+ sCodActivo +"'";
			
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

				logger.error("ERROR sCodActivo:|"+sCodActivo+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}			
		}

		return bSalida;
	}

	public static boolean delRelacionActivo(Connection conexion, String sCodActivo)
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO1 + " = '" + sCodActivo	+ "'";
			
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

				logger.error("ERROR sCodActivo:|"+sCodActivo+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean existeRelacionActivo(Connection conexion, String sCodActivo, String sFechaRegistro)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;	

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
				       + CAMPO1  + 
				       " FROM "
				       + TABLA + 
				       " WHERE (" 
				       + CAMPO1 + " = '" + sCodActivo + "' AND "
				       + CAMPO2 + " = '" + sFechaRegistro + 
				       "')";
			
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

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}			

			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR sCodActivo:|"+sCodActivo+"|");
				logger.error("ERROR sFechaRegistro:|"+sFechaRegistro+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}	

		return bEncontrado;
	}


}
