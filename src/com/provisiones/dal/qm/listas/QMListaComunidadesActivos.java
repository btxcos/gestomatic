package com.provisiones.dal.qm.listas;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMComunidades;
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

	static String CAMPO1  = "cod_cocldo";
	static String CAMPO2  = "cod_nudcom";
	static String CAMPO3  = "cod_coaces";    
	static String CAMPO4  = "cod_movimiento";    
	static String CAMPO5  = "cod_validado";
	
	static String CAMPO6  = "usuario_movimiento";    
	static String CAMPO7  = "fecha_movimiento";

	public static boolean addRelacionComunidad(String sCodCOCLDO, String sCodNUDCOM, String sCodCOACES, String sCodMovimiento)
	{

		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		String sUsuario = ValoresDefecto.DEF_USUARIO;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "INSERT INTO " 
				   + TABLA + 
				   " ("
				   + CAMPO1  + "," 
			       + CAMPO2  + ","              
			       + CAMPO3  + ","
			       + CAMPO4  + ","
			       + CAMPO5  + ","
			       + CAMPO6  + ","
			       + CAMPO7  +    
			       ") VALUES ('"
			       + sCodCOCLDO + "','" 
			       + sCodNUDCOM + "','" 
			       + sCodCOACES + "','"
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
			logger.error("ERROR COCLDO:|"+sCodCOCLDO+"|");
			logger.error("ERROR NUDCOM:|"+sCodNUDCOM+"|");
			logger.error("ERROR COACES:|"+sCodCOACES+"|");
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
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "DELETE FROM " 
				+ TABLA + 
				" WHERE "
				+ CAMPO4 + " = '" + sCodMovimiento	+ "'";
		
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

	public static boolean existeRelacionComunidad(String sCodCOCLDO, String sCodNUDCOM, String sCodCOACES, String sCodMovimiento)
	{
		Statement stmt = null;
		ResultSet rs = null;
		
		boolean bSalida = true;

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
			       + CAMPO5  +
			       " FROM " 
			       + TABLA + 
			       " WHERE (" 
			       + CAMPO1 + " = '" + sCodCOCLDO + "' AND "
			       + CAMPO2 + " = '" + sCodNUDCOM + "' AND "
			       + CAMPO3 + " = '" + sCodCOACES + "' AND " 
			       + CAMPO4 + " = '" + sCodMovimiento	+ 
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
			logger.error("ERROR COACES:|"+sCodCOACES+"|");
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

	public static boolean compruebaRelacionComunidadActivo(String sCodCOCLDO, String sCodNUDCOM, String sCodCOACES)
	{
		Statement stmt = null;
		ResultSet rs = null;
		
		boolean bSalida = true;

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		String sQuery = "SELECT "
			       + CAMPO4  +               
			       " FROM " 
			       + TABLA + 
			       " WHERE ("
			       + CAMPO3 + " = '" + sCodCOACES + 
			       
			       "' AND "

	      		   + CAMPO1 + " IN (SELECT " 
				   + QMComunidades.CAMPO1 + 
				   " FROM " + QMComunidades.TABLA +
				   " WHERE (" 
				   + QMComunidades.CAMPO1 + " = '" + sCodCOCLDO + "' AND " 
			       + QMComunidades.CAMPO15 + " = '" + ValoresDefecto.DEF_ALTA + "'))" +

			       " AND "

	      		   + CAMPO2 + " IN (SELECT " 
				   + QMComunidades.CAMPO2 + 
				   " FROM " + QMComunidades.TABLA +
				   " WHERE (" 
				   + QMComunidades.CAMPO2 + " = '" + sCodNUDCOM + "' AND " 
			       + QMComunidades.CAMPO15 + " = '" + ValoresDefecto.DEF_ALTA + "'))" +

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
			logger.error("ERROR COACES:|"+sCodCOACES+"|");

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

	public static boolean activoVinculadoComunidad(String sCodCOACES)
	{
		Statement stmt = null;
		ResultSet rs = null;
		
		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		String sQuery = "SELECT "
			       + CAMPO4  +               
			       " FROM " 
			       + TABLA + 
			       " WHERE ("
			       + CAMPO3 + " = '" + sCodCOACES + "'" + " AND "

			       + CAMPO1 + " IN (SELECT " 
			       + QMComunidades.CAMPO1 + 
			       " FROM " 
			       + QMComunidades.TABLA +
			       " WHERE " 
			       + QMComunidades.CAMPO15 + " = '" + ValoresDefecto.DEF_ALTA + "')" + " AND "

	   			   + CAMPO2 + " IN (SELECT " 
	   			   + QMComunidades.CAMPO2 + 
	   			   " FROM " 
	   			   + QMComunidades.TABLA +
	   			   " WHERE " 
	   			   + QMComunidades.CAMPO15 + " = '" + ValoresDefecto.DEF_ALTA + "'))";
		
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
			logger.error("ERROR COACES:|"+sCodCOACES+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return (found);
	}
	
	public static String getActivoVinculadoComunidadID(String sCodCOCLDO, String sCodNUDCOM, String sCodCOACES)
	{
		Statement stmt = null;
		ResultSet rs = null;
		
		String sID = "";

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		String sQuery = "SELECT "
			       + CAMPO4  +
			       " FROM " 
			       + TABLA + 
			       " WHERE (" 
			       + CAMPO1 + " = '" + sCodCOCLDO + "' AND  "
			       + CAMPO2 + " = '" + sCodNUDCOM + "' AND  "
			       + CAMPO3 + " = '" + sCodCOACES +
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

					sID = rs.getString(CAMPO4);
					
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

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return sID;
	}
	
	public static boolean setValidado(String sCodMovimiento, String sValidado)
	{
		Statement stmt = null;
		boolean bSalida = true;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO5 + " = '"+ sValidado + "' "+
				" WHERE " 
				+ CAMPO4 + " = '" + sCodMovimiento	+ "'";
		
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
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sValidado = "";

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO5 + 
				" FROM " 
				+ TABLA + 
				" WHERE "
				+ CAMPO4 + " = '" + sCodMovimiento	+ "'";
		
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
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		long liNumero = 0;

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT COUNT(*) FROM " 
				+ TABLA + 
				" WHERE "
				+ CAMPO5 + " = '" + sCodValidado + "'";
		
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
	
	public static ArrayList<ActivoTabla> buscaActivosComunidad(String sCodCOCLDO, String sCodNUDCOM)
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
		
		conn = ConnectionManager.OpenDBConnection();
		
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
				   + CAMPO3 + 
				   " FROM " 
				   + TABLA + 
				   " WHERE ("
				   + CAMPO1 + " = '" + sCodCOCLDO	+ "' AND "  
				   + CAMPO2 + " = '" + sCodNUDCOM	+ "'" +
				   "))";
		
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
			logger.error("ERROR COCLDO:|"+sCodCOCLDO+"|");
			logger.error("ERROR NUDCOM:|"+sCodNUDCOM+"|");

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
		
		conn = ConnectionManager.OpenDBConnection();
		
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

					   "  FROM " 
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

					   +  CAMPO3 + 
					   "  FROM " 
					   + TABLA + 
					   " WHERE "
					   
						+ CAMPO1 + " IN (SELECT "
						+ QMComunidades.CAMPO1 +
						" FROM " 
						+ QMComunidades.TABLA + 
						" WHERE " 
						+ QMComunidades.CAMPO15 + " = '" + ValoresDefecto.DEF_ALTA + "' ) " +

						" AND "

						+ CAMPO2 + " IN (SELECT "
						+ QMComunidades.CAMPO2 +
						" FROM " 
						+ QMComunidades.TABLA + 
						" WHERE " 
						+ QMComunidades.CAMPO15 + " = '" + ValoresDefecto.DEF_ALTA + "' )))";
		
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
		ConnectionManager.CloseDBConnection(conn);
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
		
		conn = ConnectionManager.OpenDBConnection();
		
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

					   +  CAMPO3 + 
					   " FROM " 
					   + TABLA + 
					   " WHERE "
					   
						+ CAMPO1 + " IN (SELECT "
						+ QMComunidades.CAMPO1 +
						" FROM " 
						+ QMComunidades.TABLA + 
						" WHERE " 
						+ QMComunidades.CAMPO15 + " = '" + ValoresDefecto.DEF_ALTA + "' ) " +

						" AND "

						+ CAMPO2 + " IN (SELECT "
						+ QMComunidades.CAMPO2 +
						" FROM " 
						+ QMComunidades.TABLA + 
						" WHERE " 
						+ QMComunidades.CAMPO15 + " = '" + ValoresDefecto.DEF_ALTA + "' )))";
		
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
		ConnectionManager.CloseDBConnection(conn);
		return result;
	}
	
	public static ArrayList<String>  getComunidadesActivoPorEstado(String sEstado) 
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	
		
		ArrayList<String> result = new ArrayList<String>(); 
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO4+ 
				" FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO5 + " = '" + sEstado + "'";
		
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

					result.add(rs.getString(CAMPO4));
										
					logger.debug("Encontrado el registro!");
					logger.debug(CAMPO5+":|"+sEstado+"|");
					logger.debug(CAMPO4+":|"+result.get(i)+"|");

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

	
	public static ArrayList<ComunidadTabla> buscaComunidadActivo(String sCodCOACES)
	{
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
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
				   + QMComunidades.CAMPO1 + ","        
				   + QMComunidades.CAMPO2 + ","
				   + QMComunidades.CAMPO3 + ","
				   + QMComunidades.CAMPO5 + ","
				   + QMComunidades.CAMPO7 + 
				   
				   "  FROM " 
				   + QMComunidades.TABLA + 
				   " WHERE "
				   
				   + QMComunidades.CAMPO1 + " IN (SELECT " + CAMPO1 +
				   " FROM " 
				   + TABLA +
				   " WHERE "
				   + CAMPO3 +  " = '" + sCodCOACES	+ "') AND " 
				   
				   + QMComunidades.CAMPO2 + " IN (SELECT " + CAMPO2 +
				   " FROM " 
				   + TABLA +
				   " WHERE "
				   + CAMPO3 +  " = '" + sCodCOACES	+ "')";
		
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
					
					sCOCLDO = rs.getString(QMActivos.CAMPO1);
					sNUDCOM = rs.getString(QMActivos.CAMPO2);
					sNOMCOC = rs.getString(QMActivos.CAMPO3);
					sNOMPRC = rs.getString(QMActivos.CAMPO5);
					sNOMADC = rs.getString(QMActivos.CAMPO7);
					
					
					ComunidadTabla comunidadencontrada = new ComunidadTabla(sCOCLDO, sNUDCOM, sNOMCOC, sNOMPRC, sNOMADC);
					
					result.add(comunidadencontrada);
					
					logger.debug("Encontrado el registro!");
					logger.debug(CAMPO3+":|"+sCodCOACES+"|");
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
		ConnectionManager.CloseDBConnection(conn);
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
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
				   + QMComunidades.CAMPO1  + ","              
			       + QMComunidades.CAMPO2  + ","
			       + QMComunidades.CAMPO3  + ","              
			       + QMComunidades.CAMPO4  + ","              
			       + QMComunidades.CAMPO5  + ","              
			       + QMComunidades.CAMPO6  + ","              
			       + QMComunidades.CAMPO7  + ","              
			       + QMComunidades.CAMPO8  + ","              
			       + QMComunidades.CAMPO9  + ","              
			       + QMComunidades.CAMPO10 + ","              
			       + QMComunidades.CAMPO11 + ","              
			       + QMComunidades.CAMPO12 + ","              
			       + QMComunidades.CAMPO13 + ","              
			       + QMComunidades.CAMPO14 +     
				   
				   " FROM " 
				   + QMComunidades.TABLA + 
				   " WHERE "
				   
				   + QMComunidades.CAMPO1 + " IN (SELECT " + CAMPO1 +
				   " FROM " 
				   + TABLA +
				   " WHERE "
				   + CAMPO3 +  " = '" + sCodCOACES	+ "') AND " 
				   
				   + QMComunidades.CAMPO2 + " IN (SELECT " + CAMPO2 +
				   " FROM " 
				   + TABLA +
				   " WHERE "
				   + CAMPO3 +  " = '" + sCodCOACES	+ "')";
		
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
					
					sCOCLDO = rs.getString(QMComunidades.CAMPO1);  
					sNUDCOM = rs.getString(QMComunidades.CAMPO2);  
					sNOMCOC = rs.getString(QMComunidades.CAMPO3);  
					sNODCCO = rs.getString(QMComunidades.CAMPO4);  
					sNOMPRC = rs.getString(QMComunidades.CAMPO5);  
					sNUTPRC = rs.getString(QMComunidades.CAMPO6);  
					sNOMADC = rs.getString(QMComunidades.CAMPO7);  
					sNUTADC = rs.getString(QMComunidades.CAMPO8);  
					sNODCAD = rs.getString(QMComunidades.CAMPO9);  
					sNUCCEN = rs.getString(QMComunidades.CAMPO10); 
					sNUCCOF = rs.getString(QMComunidades.CAMPO11); 
					sNUCCDI = rs.getString(QMComunidades.CAMPO12); 
					sNUCCNT = rs.getString(QMComunidades.CAMPO13); 
					sOBTEXC = rs.getString(QMComunidades.CAMPO14); 
					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO3+":|"+sCodCOACES+"|");
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
		ConnectionManager.CloseDBConnection(conn);
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
				+ CAMPO4  + 
				" FROM " 
				+ TABLA + 
				" WHERE (" 
				+ CAMPO1 + " = '" + sCodCOCLDO + "' AND "
				+ CAMPO2 + " = '" + sCodNUDCOM + "' AND "
				+ CAMPO4 + " >=  '" + sCodMovimiento + 
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
					
					result.add(rs.getString(CAMPO4));

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