package com.provisiones.dal.qm.listas;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMComunidades;
//import com.provisiones.dal.qm.QMComunidades;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Comunidad;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.ComunidadTabla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QMListaComunidadesActivos
{
	private static Logger logger = LoggerFactory.getLogger(QMListaComunidadesActivos.class.getName());
	
	static String TABLA = "pp001_lista_comunidades_activos_multi";

	//identificadores
	static String CAMPO1  = "cod_coaces";
	static String CAMPO2  = "cod_comunidad";
	static String CAMPO3  = "cod_movimiento";    

	//Campos de control
	static String CAMPO4  = "cod_validado";    
	static String CAMPO5  = "usuario_movimiento";
	static String CAMPO6  = "fecha_movimiento";    


	public static boolean addRelacionComunidad(String sCodCOACES, String sCodComunidad, String sCodMovimiento)
	{

		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		String sUsuario = ConnectionManager.getUser();

		conn = ConnectionManager.getDBConnection();
		
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
			       + sCodCOACES + "','" 
			       + sCodComunidad + "','"
			       + sCodMovimiento + "','"
			       + ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE + "','"
			       + sUsuario  + "','"
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

			logger.error("ERROR COACES:|"+sCodCOACES+"|");
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
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		conn = ConnectionManager.getDBConnection();
		
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

	public static boolean existeRelacionComunidad(String sCodCOACES, String sCodComunidad, String sCodMovimiento)
	{
		Statement stmt = null;
		ResultSet rs = null;

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
			       + CAMPO5  +
			       " FROM " 
			       + TABLA + 
			       " WHERE (" 
			       + CAMPO1 + " = '" + sCodCOACES + "' AND "
			       + CAMPO2 + " = '" + sCodComunidad + "' AND "
			       + CAMPO3 + " = '" + sCodMovimiento	+ 
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
			found = false;

			logger.error("ERROR COACES:|"+sCodCOACES+"|");
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
		return found;
	}

	public static boolean compruebaRelacionComunidadActivo(String sCodComunidad, String sCodCOACES)
	{
		Statement stmt = null;
		ResultSet rs = null;

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");

		String sQuery = "SELECT "
			       + CAMPO3  +               
			       " FROM " 
			       + TABLA + 
			       " WHERE ("
			       + CAMPO1 + " = '" + sCodCOACES + 
			       
			       "' AND "

	      		   + CAMPO2 + " IN (SELECT " 
				   + QMComunidades.CAMPO1 + 
				   " FROM " + QMComunidades.TABLA +
				   " WHERE (" 
				   + QMComunidades.CAMPO1 + " = '" + sCodComunidad + "' AND " 
			       + QMComunidades.CAMPO16 + " = '" + ValoresDefecto.DEF_ALTA + "'))" +

			       ")";
		
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
			found = false;

			logger.error("ERROR COACES:|"+sCodCOACES+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return found;
	}

	public static boolean activoVinculadoComunidad(String sCodCOACES)
	{
		Statement stmt = null;
		ResultSet rs = null;
		
		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");

		String sQuery = "SELECT "
			       + CAMPO3  +               
			       " FROM " 
			       + TABLA + 
			       " WHERE ("
			       + CAMPO1 + " = '" + sCodCOACES + "'" + " AND "

			       + CAMPO2 + " IN (SELECT " 
			       + QMComunidades.CAMPO1 + 
			       " FROM " 
			       + QMComunidades.TABLA +
			       " WHERE " 
			       + QMComunidades.CAMPO16 + " = '" + ValoresDefecto.DEF_ALTA + "'))";
		
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
			found = false;

			logger.error("ERROR COACES:|"+sCodCOACES+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return found;
	}
	
	public static String getMovimientoDeActivoVinculadoComunidad(String sCodComunidad, String sCodCOACES)
	{
		Statement stmt = null;
		ResultSet rs = null;
		
		String sMovimiento = "";

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");

		String sQuery = "SELECT "
			       + CAMPO3  +
			       " FROM " 
			       + TABLA + 
			       " WHERE (" 
			       + CAMPO1 + " = '" + sCodCOACES + "' AND  "
			       + CAMPO2 + " = '" + sCodComunidad +
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

					sMovimiento = rs.getString(CAMPO3);
					
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
			logger.error("ERROR COACES:|"+sCodCOACES+"|");
			logger.error("ERROR Comunidad:|"+sCodComunidad+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return sMovimiento;
	}
	
	public static boolean setValidado(String sCodMovimiento, String sValidado)
	{
		Statement stmt = null;
		boolean bSalida = true;
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
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
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sValidado = "";

		Connection conn = null;

		conn = ConnectionManager.getDBConnection();
		
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

					sValidado = rs.getString(CAMPO5);
					
					logger.debug("Encontrado el registro!");
					logger.debug(CAMPO5+":|"+sValidado+"|");

				}
			}
			if (found == false) 
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
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;

		long liNumero = 0;

		Connection conn = null;

		conn = ConnectionManager.getDBConnection();
		
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
	
	public static ArrayList<ActivoTabla> buscaActivosComunidad(String sCodComunidad)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sCOACES = "";
		String sCOPOIN = "";
		String sNOMUIN = "";
		String sNOPRAC = "";
		String sNOVIAS = "";
		String sNUPIAC = "";
		String sNUPOAC = "";
		String sNUPUAC = "";
		//String sNURCAT = "";
		
		ArrayList<ActivoTabla> result = new ArrayList<ActivoTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
				   + QMActivos.CAMPO1 + ","        
				   + QMActivos.CAMPO14 + ","
				   + QMActivos.CAMPO11 + ","
				   + QMActivos.CAMPO13 + ","
				   + QMActivos.CAMPO6 + ","
				   + QMActivos.CAMPO9 + ","
				   + QMActivos.CAMPO7 + ","
				   + QMActivos.CAMPO10 +
				   //+ QMActivos.CAMPO81 + 
				   " FROM " 
				   + QMActivos.TABLA + 
				   " WHERE "
				   + QMActivos.CAMPO1 +" IN (SELECT "
				   + CAMPO1 + 
				   " FROM " 
				   + TABLA + 
				   " WHERE "
				   + CAMPO2 + " = '" + sCodComunidad	+ "')";
		
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
					
					sCOACES = rs.getString(QMActivos.CAMPO1);
					sCOPOIN = rs.getString(QMActivos.CAMPO14);
					sNOMUIN = rs.getString(QMActivos.CAMPO11);
					sNOPRAC = rs.getString(QMActivos.CAMPO13);
					sNOVIAS = rs.getString(QMActivos.CAMPO6);
					sNUPIAC = rs.getString(QMActivos.CAMPO9);
					sNUPOAC = rs.getString(QMActivos.CAMPO7);
					sNUPUAC = rs.getString(QMActivos.CAMPO10);
					//sNURCAT = rs.getString(QMActivos.CAMPO81);
					
					ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, "");
					
					result.add(activoencontrado);
					
					logger.debug("Encontrado el registro!");
					logger.debug(QMActivos.CAMPO1+":|"+sCOACES+"|");
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			result = new ArrayList<ActivoTabla>();

			logger.error("ERROR Comunidad:|"+sCodComunidad+"|");

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
	
	public static ArrayList<ActivoTabla> buscaActivosComunidadDisponibles(ActivoTabla activo, String sCodComunidad)
	{
		
		Statement stmt = null;
		ResultSet rs = null;

		String sCOACES = "";
		String sCOPOIN = "";
		String sNOMUIN = "";
		String sNOPRAC = "";
		String sNOVIAS = "";
		String sNUPIAC = "";
		String sNUPOAC = "";
		String sNUPUAC = "";
		
		ArrayList<ActivoTabla> result = new ArrayList<ActivoTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
					
					   + QMActivos.CAMPO1 + ","        
					   + QMActivos.CAMPO14 + ","
					   + QMActivos.CAMPO11 + ","
					   + QMActivos.CAMPO13 + ","
					   + QMActivos.CAMPO6 + ","
					   + QMActivos.CAMPO9 + ","
					   + QMActivos.CAMPO7 + ","
					   + QMActivos.CAMPO10 + 

					   " FROM " 
					   + QMActivos.TABLA + 
					   " WHERE ("

					   + QMActivos.CAMPO14 + " LIKE '%" + activo.getCOPOIN()	+ "%' AND "  
					   + QMActivos.CAMPO11 + " LIKE '%" + activo.getNOMUIN()	+ "%' AND "  
					   + QMActivos.CAMPO13 + " LIKE '%" + activo.getNOPRAC()	+ "%' AND "  
					   + QMActivos.CAMPO6 + " LIKE '%" + activo.getNOVIAS()	+ "%' AND "  
					   + QMActivos.CAMPO9 + " LIKE '%" + activo.getNUPIAC()	+ "%' AND "  
					   + QMActivos.CAMPO7 + " LIKE '%" + activo.getNUPOAC()	+ "%' AND "  
					   + QMActivos.CAMPO10 + " LIKE '%" + activo.getNUPUAC()	+ "%' AND "			

					   + QMActivos.CAMPO1 +" IN (SELECT "

					   + CAMPO1 + 
					   " FROM " 
					   + TABLA + 
					   " WHERE (" 
					   
					   + CAMPO2 +" IN (SELECT "

					   + QMComunidades.CAMPO1 + 
					   " FROM " 
					   + QMComunidades.TABLA + 
					   " WHERE "
					   + QMComunidades.CAMPO1 + " = '" + sCodComunidad	+ "'))))";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con éxito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					sCOACES = rs.getString(QMActivos.CAMPO1);
					sCOPOIN = rs.getString(QMActivos.CAMPO14);
					sNOMUIN = rs.getString(QMActivos.CAMPO11);
					sNOPRAC = rs.getString(QMActivos.CAMPO13);
					sNOVIAS = rs.getString(QMActivos.CAMPO6);
					sNUPIAC = rs.getString(QMActivos.CAMPO9);
					sNUPOAC = rs.getString(QMActivos.CAMPO7);
					sNUPUAC = rs.getString(QMActivos.CAMPO10);
					
					ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, "");
					
					result.add(activoencontrado);
					
					logger.debug("Encontrado el registro!");

					logger.debug(QMActivos.CAMPO1+":|"+sCOACES+"|");
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			result = new ArrayList<ActivoTabla>();
			
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
	
	public static ArrayList<ActivoTabla> buscaActivosSinComunidad(ActivoTabla activo)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sCOACES = "";
		String sCOPOIN = "";
		String sNOMUIN = "";
		String sNOPRAC = "";
		String sNOVIAS = "";
		String sNUPIAC = "";
		String sNUPOAC = "";
		String sNUPUAC = "";
		
		ArrayList<ActivoTabla> result = new ArrayList<ActivoTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
					
					   + QMActivos.CAMPO1 + ","        
					   + QMActivos.CAMPO14 + ","
					   + QMActivos.CAMPO11 + ","
					   + QMActivos.CAMPO13 + ","
					   + QMActivos.CAMPO6 + ","
					   + QMActivos.CAMPO9 + ","
					   + QMActivos.CAMPO7 + ","
					   + QMActivos.CAMPO10 + 

					   " FROM " 
					   + QMActivos.TABLA + 
					   " WHERE ("

					   + QMActivos.CAMPO14 + " LIKE '%" + activo.getCOPOIN()	+ "%' AND "  
					   + QMActivos.CAMPO11 + " LIKE '%" + activo.getNOMUIN()	+ "%' AND "  
					   + QMActivos.CAMPO13 + " LIKE '%" + activo.getNOPRAC()	+ "%' AND "  
					   + QMActivos.CAMPO6 + " LIKE '%" + activo.getNOVIAS()	+ "%' AND "  
					   + QMActivos.CAMPO9 + " LIKE '%" + activo.getNUPIAC()	+ "%' AND "  
					   + QMActivos.CAMPO7 + " LIKE '%" + activo.getNUPOAC()	+ "%' AND "  
					   + QMActivos.CAMPO10 + " LIKE '%" + activo.getNUPUAC()	+ "%' AND "			

					   + QMActivos.CAMPO1 +" NOT IN (SELECT "

					   + CAMPO1 + 
					   " FROM " 
					   + TABLA + 
					   " WHERE "
					   
						+ CAMPO2 + " IN (SELECT "
						+ QMComunidades.CAMPO1 +
						" FROM " 
						+ QMComunidades.TABLA + 
						" WHERE " 
						+ QMComunidades.CAMPO16 + " = '" + ValoresDefecto.DEF_ALTA + "' )))";
		
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
					
					sCOACES = rs.getString(QMActivos.CAMPO1);
					sCOPOIN = rs.getString(QMActivos.CAMPO14);
					sNOMUIN = rs.getString(QMActivos.CAMPO11);
					sNOPRAC = rs.getString(QMActivos.CAMPO13);
					sNOVIAS = rs.getString(QMActivos.CAMPO6);
					sNUPIAC = rs.getString(QMActivos.CAMPO9);
					sNUPOAC = rs.getString(QMActivos.CAMPO7);
					sNUPUAC = rs.getString(QMActivos.CAMPO10);
					
					ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, "");
					
					result.add(activoencontrado);
					
					logger.debug("Encontrado el registro!");
					logger.debug(QMActivos.CAMPO1+":|"+sCOACES+"|");
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			result = new ArrayList<ActivoTabla>();
			
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
	
	public static ArrayList<ActivoTabla> buscaActivosConComunidad(ActivoTabla activo)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sCOACES = "";
		String sCOPOIN = "";
		String sNOMUIN = "";
		String sNOPRAC = "";
		String sNOVIAS = "";
		String sNUPIAC = "";
		String sNUPOAC = "";
		String sNUPUAC = "";
		
		ArrayList<ActivoTabla> result = new ArrayList<ActivoTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
					
					   + QMActivos.CAMPO1 + ","        
					   + QMActivos.CAMPO14 + ","
					   + QMActivos.CAMPO11 + ","
					   + QMActivos.CAMPO13 + ","
					   + QMActivos.CAMPO6 + ","
					   + QMActivos.CAMPO9 + ","
					   + QMActivos.CAMPO7 + ","
					   + QMActivos.CAMPO10 + 

					   " FROM " 
					   + QMActivos.TABLA + 
					   " WHERE ("

					   + QMActivos.CAMPO14 + " LIKE '%" + activo.getCOPOIN()	+ "%' AND "  
					   + QMActivos.CAMPO11 + " LIKE '%" + activo.getNOMUIN()	+ "%' AND "  
					   + QMActivos.CAMPO13 + " LIKE '%" + activo.getNOPRAC()	+ "%' AND "  
					   + QMActivos.CAMPO6 + " LIKE '%" + activo.getNOVIAS()	+ "%' AND "  
					   + QMActivos.CAMPO9 + " LIKE '%" + activo.getNUPIAC()	+ "%' AND "  
					   + QMActivos.CAMPO7 + " LIKE '%" + activo.getNUPOAC()	+ "%' AND "  
					   + QMActivos.CAMPO10 + " LIKE '%" + activo.getNUPUAC()	+ "%' AND "			

					   + QMActivos.CAMPO1 +" IN (SELECT "

					   + CAMPO1 + 
					   " FROM " 
					   + TABLA + 
					   " WHERE "
					   
						+ CAMPO2 + " IN (SELECT "
						+ QMComunidades.CAMPO1 +
						" FROM " 
						+ QMComunidades.TABLA + 
						" WHERE " 
						+ QMComunidades.CAMPO16 + " = '" + ValoresDefecto.DEF_ALTA + "' )))";
		
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
					
					sCOACES = rs.getString(QMActivos.CAMPO1);
					sCOPOIN = rs.getString(QMActivos.CAMPO14);
					sNOMUIN = rs.getString(QMActivos.CAMPO11);
					sNOPRAC = rs.getString(QMActivos.CAMPO13);
					sNOVIAS = rs.getString(QMActivos.CAMPO6);
					sNUPIAC = rs.getString(QMActivos.CAMPO9);
					sNUPOAC = rs.getString(QMActivos.CAMPO7);
					sNUPUAC = rs.getString(QMActivos.CAMPO10);
					
					ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, "");
					
					result.add(activoencontrado);
					
					logger.debug("Encontrado el registro!");
					logger.debug(QMActivos.CAMPO1+":|"+sCOACES+"|");
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
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
	
	public static ArrayList<String>  getMovimientosComunidadesActivoPorEstado(String sEstado) 
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	
		
		ArrayList<String> result = new ArrayList<String>(); 
		Connection conn = null;

		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO3+ 
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

	
	public static ArrayList<ComunidadTabla> buscaComunidadActivo(String sCodCOACES)
	{
		//Sin uso
		
		Statement stmt = null;
		ResultSet rs = null;

		String sCOCLDO = "";
		String sNUDCOM = "";
		String sNOMCOC = "";
		String sNOMPRC = "";
		String sNOMADC = "";
		
		ArrayList<ComunidadTabla> result = new ArrayList<ComunidadTabla>();

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
				   + QMComunidades.CAMPO2 + ","        
				   + QMComunidades.CAMPO3 + ","
				   + QMComunidades.CAMPO4 + ","
				   + QMComunidades.CAMPO6 + ","
				   + QMComunidades.CAMPO8 + 
				   
				   "  FROM " 
				   + QMComunidades.TABLA + 
				   " WHERE "
				   
				   + QMComunidades.CAMPO1 + " IN (SELECT " 
				   + CAMPO2 +
				   " FROM " 
				   + TABLA +
				   " WHERE "
				   + CAMPO1 +  " = '" + sCodCOACES	+ "')";
		
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
					
					sCOCLDO = rs.getString(QMActivos.CAMPO2);
					sNUDCOM = rs.getString(QMActivos.CAMPO3);
					sNOMCOC = rs.getString(QMActivos.CAMPO4);
					sNOMPRC = rs.getString(QMActivos.CAMPO6);
					sNOMADC = rs.getString(QMActivos.CAMPO8);
					
					
					ComunidadTabla comunidadencontrada = new ComunidadTabla(sCOCLDO, sNUDCOM, sNOMCOC, sNOMPRC, sNOMADC);
					
					result.add(comunidadencontrada);
					
					logger.debug("Encontrado el registro!");
					logger.debug(CAMPO1+":|"+sCodCOACES+"|");
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			result = new ArrayList<ComunidadTabla>();

			logger.error("ERROR COACES:|"+sCodCOACES+"|");

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
	
	public static Comunidad buscaComunidadPorActivo(String sCodCOACES)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sCOCLDO = "";
		String sNUDCOM = "";
		String sNOMCOC = "";
		String sNODCCO = "";
		String sNOMPRC = "";
		String sNUTPRC = "";
		String sNOMADC = "";
		String sNUTADC = "";
		String sNODCAD = "";
		String sNUCCEN = "";
		String sNUCCOF = "";
		String sNUCCDI = "";
		String sNUCCNT = "";
		String sOBTEXC = "";
		
		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
			       + QMComunidades.CAMPO2  + ","
			       + QMComunidades.CAMPO3  + ","              
			       /*+ QMComunidades.CAMPO4  + ","              
			       + QMComunidades.CAMPO5  + ","              
			       + QMComunidades.CAMPO6  + ","              
			       + QMComunidades.CAMPO7  + ","              
			       + QMComunidades.CAMPO8  + ","              
			       + QMComunidades.CAMPO9  + ","              
			       + QMComunidades.CAMPO10 + ","              
			       + QMComunidades.CAMPO11 + ","              
			       + QMComunidades.CAMPO12 + ","              
			       + QMComunidades.CAMPO13 + "," 
			       + QMComunidades.CAMPO14 + ","*/
			       + "AES_DECRYPT("+QMComunidades.CAMPO4 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
			       + "AES_DECRYPT("+QMComunidades.CAMPO5 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
			       + "AES_DECRYPT("+QMComunidades.CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
			       + "AES_DECRYPT("+QMComunidades.CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
			       + "AES_DECRYPT("+QMComunidades.CAMPO8 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
			       + "AES_DECRYPT("+QMComunidades.CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
			       + "AES_DECRYPT("+QMComunidades.CAMPO10+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
			       + "AES_DECRYPT("+QMComunidades.CAMPO11+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
			       + "AES_DECRYPT("+QMComunidades.CAMPO12+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
			       + "AES_DECRYPT("+QMComunidades.CAMPO13+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
			       + "AES_DECRYPT("+QMComunidades.CAMPO14+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"

			       + QMComunidades.CAMPO15 +     
				   
				   " FROM " 
				   + QMComunidades.TABLA + 
				   " WHERE "
				   
				   + QMComunidades.CAMPO1 + " IN (SELECT " + CAMPO2 +
				   " FROM " 
				   + TABLA +
				   " WHERE "
				   + CAMPO1 +  " = '" + sCodCOACES	+ "')";
		
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
					
					sCOCLDO = rs.getString(QMComunidades.CAMPO2); 
					sNUDCOM = rs.getString(QMComunidades.CAMPO3);
					
					/*sNOMCOC = rs.getString(QMComunidades.CAMPO4); 
					sNODCCO = rs.getString(QMComunidades.CAMPO5); 
					sNOMPRC = rs.getString(QMComunidades.CAMPO6); 
					sNUTPRC = rs.getString(QMComunidades.CAMPO7); 
					sNOMADC = rs.getString(QMComunidades.CAMPO8); 
					sNUTADC = rs.getString(QMComunidades.CAMPO9); 
					sNODCAD = rs.getString(QMComunidades.CAMPO10);
					sNUCCEN = rs.getString(QMComunidades.CAMPO11);
					sNUCCOF = rs.getString(QMComunidades.CAMPO12);
					sNUCCDI = rs.getString(QMComunidades.CAMPO13);
					sNUCCNT = rs.getString(QMComunidades.CAMPO14);*/
					
					sNOMCOC = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO4 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"); 
					sNODCCO = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO5 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"); 
					sNOMPRC = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"); 
					sNUTPRC = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"); 
					sNOMADC = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO8 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"); 
					sNUTADC = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"); 
					sNODCAD = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO10+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"); 
					sNUCCEN = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO11+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"); 
					sNUCCOF = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO12+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"); 
					sNUCCDI = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO13+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"); 
					sNUCCNT = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO14+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"); 

					
					sOBTEXC = rs.getString(QMComunidades.CAMPO15);

					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO1+":|"+sCodCOACES+"|");
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR COACES:|"+sCodCOACES+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return new Comunidad(
				sCOCLDO,
				sNUDCOM,
				sNOMCOC,
				sNODCCO,
				sNOMPRC,
				sNUTPRC,
				sNOMADC,
				sNUTADC,
				sNODCAD,
				sNUCCEN,
				sNUCCOF,
				sNUCCDI,
				sNUCCNT,
				sOBTEXC);
	}
	
	public static ArrayList<String> buscarDependencias(String sCodComunidad, String sCodMovimiento)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

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
				+ CAMPO2 + " = '" + sCodComunidad + "' AND "
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