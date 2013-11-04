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

	static String CAMPO1  = "cod_cocldo";
	static String CAMPO2  = "cod_nudcom";
	static String CAMPO3  = "cod_movimiento";    
	static String CAMPO4  = "cod_validado";
	
	static String CAMPO5  = "usuario_movimiento";    
	static String CAMPO6  = "fecha_movimiento";

	public static boolean addRelacionComunidad(String sCodCOCLDO, String sCodNUDCOM, String sCodMovimiento)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();
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
			       + CAMPO5  + ","
			       + CAMPO6  +  
			       ") VALUES ('"
			       + sCodCOCLDO + "','" 
			       + sCodNUDCOM + "','" 
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
			logger.error("ERROR COCLDO:|"+sCodCOCLDO+"|");
			logger.error("ERROR NUDCOM:|"+sCodNUDCOM+"|");
			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");
			
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

	public static boolean delRelacionComunidad(String sCodMovimiento)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

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
			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

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
	
	public static boolean existeComunidad(String sCodCOCLDO, String sCodNUDCOM)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		boolean bSalida = true;
		
		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
			       + CAMPO4  + 
			       " FROM " 
			       + TABLA + 
			       " WHERE (" 
			       + CAMPO1 + " = '" + sCodCOCLDO + "' AND "
			       + CAMPO2 + " = '" + sCodNUDCOM + 
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
					found = true;

					logger.debug("Encontrado el registro!");

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}			

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR COCLDO:|"+sCodCOCLDO+"|");
			logger.error("ERROR NUDCOM:|"+sCodNUDCOM+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return (found && bSalida);
	}
	
	public static boolean existeRelacionComunidad(String sCodCOCLDO, String sCodNUDCOM, String sCodMovimiento)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;		

		boolean bSalida = true;
		
		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
			       + CAMPO4  + 
			       " FROM "
			       + TABLA + 
			       " WHERE (" 
			       + CAMPO1 + " = '" + sCodCOCLDO + "' AND "
			       + CAMPO2 + " = '" + sCodNUDCOM + "' AND "
			       + CAMPO3 + " = '" + sCodMovimiento + 
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
					found = true;

					logger.debug("Encontrado el registro!");

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}			

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR COCLDO:|"+sCodCOCLDO+"|");
			logger.error("ERROR NUDCOM:|"+sCodNUDCOM+"|");
			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return (found && bSalida);
	}

	public static ArrayList<String>  getComunidadesPorEstado(String sEstado) 
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		ArrayList<String> result = new ArrayList<String>(); 

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
					+ CAMPO3 + 
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO4 + " = '" + sEstado + "'";
		
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
					found = true;

					result.add(rs.getString(CAMPO3));
										
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO4+":|"+sEstado+"|");
					logger.debug(CAMPO3+":|"+result.get(i)+"|");
					
					i++;
				}
			}
			if (found == false) 
			{
				result = new ArrayList<String>(); 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Validado:|"+sEstado+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		ConnectionManager.CloseDBConnection(conn);
		return result;
	}
	
	public static boolean setValidado(String sCodMovimiento, String sValidado)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO4 + " = '"+ sValidado + "' "+
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
			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

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
	
	public static String getValidado(String sCodMovimiento)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sValidado = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO4 + 
				" FROM " 
				+ TABLA + 
				" WHERE "
				+ CAMPO3 + " = '" + sCodMovimiento	+ "'";
		
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

					sValidado = rs.getString(CAMPO4);
					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO4+":|"+sValidado+"|");
				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		ConnectionManager.CloseDBConnection(conn);
		return sValidado;
	}
	
	public static long buscaCantidadValidado(String sCodValidado)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		long liNumero = 0;

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT COUNT(*) FROM " 
				+ TABLA + 
				" WHERE "
				+ CAMPO4 + " = '" + sCodValidado + "'";
		
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

					liNumero = rs.getLong("COUNT(*)");
					
					logger.debug("Encontrado el registro!");

					logger.debug( "Numero de registros:|"+liNumero+"|");
				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR CodValidado:|"+sCodValidado+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		ConnectionManager.CloseDBConnection(conn);
		return liNumero;
	}
	
	public static ArrayList<String> buscarDependencias(String sCodCOCLDO, String sCodNUDCOM, String sCodMovimiento)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;		

		boolean found = false;
		
		ArrayList<String> result = new ArrayList<String>();

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO3  + 
				" FROM " 
				+ TABLA + 
				" WHERE (" 
				+ CAMPO1 + " = '" + sCodCOCLDO + "' AND "
				+ CAMPO2 + " = '" + sCodNUDCOM + "' AND "
				+ CAMPO3 + " >=  '" + sCodMovimiento + 
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
					found = true;
					
					result.add(rs.getString(CAMPO3));

					logger.debug("Encontrado el registro!");

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}			

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR COCLDO:|"+sCodCOCLDO+"|");
			logger.error("ERROR NUDCOM:|"+sCodNUDCOM+"|");
			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

			found = false;
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return result;
	}
}