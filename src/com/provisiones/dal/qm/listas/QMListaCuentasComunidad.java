package com.provisiones.dal.qm.listas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.misc.Utils;

public class QMListaCuentasComunidad 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaCuentasComunidad.class.getName());
	
	public static final String TABLA = "pp002_lista_cuentas_comunidades_multi";

	//identificadores
	public static final String CAMPO1  = "cod_cuenta";
	public static final String CAMPO2  = "cod_comunidad";

	private QMListaCuentasComunidad(){}

	public static boolean addRelacionComunidad(Connection conexion, String sCodCuenta, String sCodComunidad, String sCodMovimiento)
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
				       + CAMPO2  +    
				       ") VALUES ('"
				       + sCodCuenta + "','" 
				       + sCodComunidad + "' )";
			
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

				logger.error("ERROR CUENTA:|"+sCodCuenta+"|");
				logger.error("ERROR COMUNIDAD:|"+sCodComunidad+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean delRelacionComunidad(Connection conexion, String sCodCuenta, String sCodComunidad)
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE ("
					+ CAMPO1 + " = '" + sCodCuenta	+ "' AND "
					+ CAMPO2 + " = '" + sCodComunidad	+ "' )";
			
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

				logger.error("ERROR CUENTA:|"+sCodCuenta+"|");
				logger.error("ERROR COMUNIDAD:|"+sCodComunidad+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean existeRelacionComunidad(Connection conexion, String sCodCuenta, String sCodComunidad)
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
				       + CAMPO1 + " = '" + sCodCuenta + "' AND "
				       + CAMPO2 + " = '" + sCodComunidad + "')";
			
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

				logger.error("ERROR CUENTA:|"+sCodCuenta+"|");
				logger.error("ERROR COMUNIDAD:|"+sCodComunidad+"|");

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
