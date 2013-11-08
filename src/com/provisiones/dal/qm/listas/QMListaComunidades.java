package com.provisiones.dal.qm.listas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;

import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class QMListaComunidades
{
	private static Logger logger = LoggerFactory.getLogger(QMListaComunidades.class.getName());

	static String TABLA = "pp001_lista_comunidades_multi";

	//identificadores
	static String CAMPO1  = "cod_comunidad";
	static String CAMPO2  = "cod_movimiento";

	//Campos de control
	static String CAMPO3  = "cod_validado";    
	static String CAMPO4  = "usuario_movimiento";
	static String CAMPO5  = "fecha_movimiento";    

	public static boolean addRelacionComunidad(String sCodComunidad, String sCodMovimiento)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();
		String sUsuario = ValoresDefecto.DEF_USUARIO;

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "INSERT INTO " 
				   + TABLA + 
				   " ("
				   + CAMPO1  + "," 
			       + CAMPO2  + ","              
			       + CAMPO3  + ","
			       + CAMPO4  + ","
			       + CAMPO5  +  
			       ") VALUES ('"
			       + sCodComunidad + "','" 
			       + sCodMovimiento + "','"
			       + ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE + "','"
			       + sUsuario + "','"
			       + Utils.timeStamp() +
			       "' )";
		
		logger.debug(sQuery);

		try 
		{

			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			bSalida = false;

			logger.error("ERROR Comunidad:|"+sCodComunidad+"|");
			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");
			
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally
		{
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static boolean delRelacionComunidad(String sCodMovimiento)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "DELETE FROM " 
				+ TABLA + 
				" WHERE "
				+ CAMPO3 + " = '" + sCodMovimiento	+ "'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			bSalida = false;

			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	
	public static boolean existeComunidad(String sCodComunidad)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		boolean bEncontrado = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
			       + CAMPO4  + 
			       " FROM " 
			       + TABLA + 
			       " WHERE " 
			       + CAMPO1 + " = '" + sCodComunidad + "'";
		
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
					bEncontrado = true;

					logger.debug("Encontrado el registro!");

				}
			}
			if (bEncontrado == false) 
			{
				logger.debug("No se encontró la información.");
			}			

		} 
		catch (SQLException ex) 
		{
			bEncontrado = false;

			logger.error("ERROR Comunidad:|"+sCodComunidad+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bEncontrado;
	}
	
	public static boolean existeRelacionComunidad(String sCodComunidad, String sCodMovimiento)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;		

		boolean bEncontrado = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
			       + CAMPO4  + 
			       " FROM "
			       + TABLA + 
			       " WHERE (" 
			       + CAMPO1 + " = '" + sCodComunidad + "' AND "
			       + CAMPO2 + " = '" + sCodMovimiento + 
			       "')";
		
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
					bEncontrado = true;

					logger.debug("Encontrado el registro!");

				}
			}
			if (bEncontrado == false) 
			{
				logger.debug("No se encontró la información.");
			}			

		} 
		catch (SQLException ex) 
		{
			bEncontrado = false;

			logger.error("ERROR Comunidad:|"+sCodComunidad+"|");
			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bEncontrado;
	}

	public static ArrayList<String>  getComunidadesPorEstado(String sEstado) 
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		ArrayList<String> result = new ArrayList<String>(); 

		boolean bEncontrado = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
					+ CAMPO2 + 
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO3 + " = '" + sEstado + "'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
		
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					bEncontrado = true;

					result.add(rs.getString(CAMPO2));
										
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO3+":|"+sEstado+"|");
					logger.debug(CAMPO2+":|"+result.get(i)+"|");
					
					i++;
				}
			}
			if (bEncontrado == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			result = new ArrayList<String>();

			logger.error("ERROR Validado:|"+sEstado+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		//ConnectionManager.CloseDBConnection(conn);
		return result;
	}
	
	public static boolean setValidado(String sCodMovimiento, String sValidado)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO3 + " = '"+ sValidado + "' "+
				" WHERE " 
				+ CAMPO2 + " = '" + sCodMovimiento	+ "'";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			bSalida = false;

			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	
	public static String getValidado(String sCodMovimiento)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sValidado = "";

		boolean bEncontrado = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO3 + 
				" FROM " 
				+ TABLA + 
				" WHERE "
				+ CAMPO2 + " = '" + sCodMovimiento	+ "'";
		
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
					bEncontrado = true;

					sValidado = rs.getString(CAMPO3);
					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO3+":|"+sValidado+"|");
				}
			}
			if (bEncontrado == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			sValidado = "";

			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		//ConnectionManager.CloseDBConnection(conn);
		return sValidado;
	}
	
	public static long buscaCantidadValidado(String sCodValidado)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		long liNumero = 0;

		boolean bEncontrado = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT COUNT(*) FROM " 
				+ TABLA + 
				" WHERE "
				+ CAMPO3 + " = '" + sCodValidado + "'";
		
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
					bEncontrado = true;

					liNumero = rs.getLong("COUNT(*)");
					
					logger.debug("Encontrado el registro!");

					logger.debug( "Numero de registros:|"+liNumero+"|");
				}
			}
			if (bEncontrado == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			liNumero = 0;

			logger.error("ERROR CodValidado:|"+sCodValidado+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		//ConnectionManager.CloseDBConnection(conn);
		return liNumero;
	}
	
	public static ArrayList<String> buscarDependencias(String sCodComunidad, String sCodMovimiento)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;		

		boolean bEncontrado = false;
		
		ArrayList<String> result = new ArrayList<String>();

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO2  + 
				" FROM " 
				+ TABLA + 
				" WHERE (" 
				+ CAMPO1 + " = '" + sCodComunidad + "' AND "
				+ CAMPO2 + " >=  '" + sCodMovimiento + 
				"')";
		
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
					bEncontrado = true;
					
					result.add(rs.getString(CAMPO2));

					logger.debug("Encontrado el registro!");

				}
			}
			if (bEncontrado == false) 
			{
				logger.debug("No se encontró la información.");
			}			

		} 
		catch (SQLException ex) 
		{
			result = new ArrayList<String>();

			logger.error("ERROR Comunidad:|"+sCodComunidad+"|");
			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return result;
	}
}