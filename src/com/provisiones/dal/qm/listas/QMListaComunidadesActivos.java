package com.provisiones.dal.qm.listas;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMComunidades;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.Comunidad;
import com.provisiones.types.ComunidadTabla;

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
	
	static String sTable = "lista_comunidades_activos_multi";

	static String sField1  = "cod_cocldo";
	static String sField2  = "cod_nudcom";
	static String sField3  = "cod_coaces";    
	static String sField4  = "cod_movimiento";    
	static String sField5  = "cod_validado";
	
	static String sField6  = "usuario_movimiento";    
	static String sField7  = "fecha_movimiento";

	public static boolean addRelacionComunidad(String sCodCOCLDO, String sCodNUDCOM, String sCodCOACES, String sCodMovimiento)
	{

		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		String sUsuario = ValoresDefecto.DEF_USUARIO;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{

			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + sTable + " ("
					   + sField1  + "," 
				       + sField2  + ","              
				       + sField3  + ","
				       + sField4  + ","
				       + sField5  + ","
				       + sField6  + ","
				       + sField7  +    
				       ") VALUES ('"
				       + sCodCOCLDO + "','" 
				       + sCodNUDCOM + "','" 
				       + sCodCOACES + "','"
				       + sCodMovimiento + "','"
				       + ValoresDefecto.DEF_PENDIENTE + "','"
				       + sUsuario  + "','"
				       + Utils.timeStamp() + 
				       "' )");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: COCLDO:|{}|",sCodCOCLDO);
			logger.error("ERROR: NUDCOM:|{}|",sCodNUDCOM);
			logger.error("ERROR: COACES:|{}|",sCodCOACES);
			logger.error("ERROR: Movimiento:|{}|",sCodMovimiento);
			
			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
			
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

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE " +
					"(" + sField4 + " = '" + sCodMovimiento	+ "')");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: Movimiento:|{}|",sCodMovimiento);

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
			
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
		

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
				       + sField5  + " " +               
       
			"  FROM " + sTable + 
					" WHERE " +
					"(" 
					+ sField1 + " = '" + sCodCOCLDO + "' AND "
					+ sField2 + " = '" + sCodNUDCOM + "' AND "
					+ sField3 + " = '" + sCodCOACES + "' AND " 
					+ sField4 + " = '" + sCodMovimiento	+ 
					"')");
			
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
			logger.error("ERROR: COCLDO:|{}|",sCodCOCLDO);
			logger.error("ERROR: NUDCOM:|{}|",sCodNUDCOM);
			logger.error("ERROR: COACES:|{}|",sCodCOACES);
			logger.error("ERROR: Movimiento:|{}|",sCodMovimiento);

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
			
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
			       + sField4  +               
			       "  FROM " + sTable + " WHERE " +
			       "("
			       + sField3 + " = '" + sCodCOACES + 
			       
			       "' AND "

	      		   + sField1 + " IN (SELECT " 
				   + QMComunidades.sField1 + 
				   " FROM " + QMComunidades.sTable +
				   " WHERE (" 
				   + QMComunidades.sField1 + " = '" + sCodCOCLDO + "' AND " 
			       + QMComunidades.sField15 + " = '" + ValoresDefecto.DEF_ALTA + "'))" +

			       " AND "

	      		   + sField2 + " IN (SELECT " 
				   + QMComunidades.sField2 + 
				   " FROM " + QMComunidades.sTable +
				   " WHERE (" 
				   + QMComunidades.sField2 + " = '" + sCodNUDCOM + "' AND " 
			       + QMComunidades.sField15 + " = '" + ValoresDefecto.DEF_ALTA + "'))" +

			       ")";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
				       + sField4  +               
				       "  FROM " + sTable + " WHERE " +
				       "("
				       + sField3 + " = '" + sCodCOACES + 
				       
				       "' AND "

		      		   + sField1 + " IN (SELECT " 
   					   + QMComunidades.sField1 + 
   					   " FROM " + QMComunidades.sTable +
   					   " WHERE (" 
   					   + QMComunidades.sField1 + " = '" + sCodCOCLDO + "' AND " 
				       + QMComunidades.sField15 + " = '" + ValoresDefecto.DEF_ALTA + "'))" +

				       " AND "

		      		   + sField2 + " IN (SELECT " 
   					   + QMComunidades.sField2 + 
   					   " FROM " + QMComunidades.sTable +
   					   " WHERE (" 
   					   + QMComunidades.sField2 + " = '" + sCodNUDCOM + "' AND " 
				       + QMComunidades.sField15 + " = '" + ValoresDefecto.DEF_ALTA + "'))" +

				       ")");

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
			logger.error("ERROR: COACES:|{}|",sCodCOACES);

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
			
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
			       + sField4  +               
			       
		"  FROM " + sTable + 
				" WHERE " +
	       "("
	       + sField3 + " = '" + sCodCOACES + "'" +

	       " AND "

	   + sField1 + " IN (SELECT " 
		   + QMComunidades.sField1 + 
		   " FROM " + QMComunidades.sTable +
		   " WHERE " + QMComunidades.sField15 + " = '" + ValoresDefecto.DEF_ALTA + "')" +

	       " AND "

	   + sField2 + " IN (SELECT " 
		   + QMComunidades.sField2 + 
		   " FROM " + QMComunidades.sTable +
		   " WHERE " + QMComunidades.sField15 + " = '" + ValoresDefecto.DEF_ALTA + "')" +

				")";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
				       + sField4  +               
       
			"  FROM " + sTable + 
					" WHERE " +
		       "("
		       + sField3 + " = '" + sCodCOACES + "'" +
		       
		       " AND "

   		   + sField1 + " IN (SELECT " 
			   + QMComunidades.sField1 + 
			   " FROM " + QMComunidades.sTable +
			   " WHERE " + QMComunidades.sField15 + " = '" + ValoresDefecto.DEF_ALTA + "')" +

		       " AND "

   		   + sField2 + " IN (SELECT " 
			   + QMComunidades.sField2 + 
			   " FROM " + QMComunidades.sTable +
			   " WHERE " + QMComunidades.sField15 + " = '" + ValoresDefecto.DEF_ALTA + "')" +

					")");

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
			logger.error("ERROR: COACES:|{}|",sCodCOACES);

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
			       + sField4  +               
			       
		"  FROM " + sTable + 
				" WHERE (" 
		+ sField1 + " = '" + sCodCOCLDO + "' AND  "
		+ sField2 + " = '" + sCodNUDCOM + "' AND  "
		+ sField3 + " = '" + sCodCOACES +
		"')";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
				       + sField4  +               
       
			"  FROM " + sTable + 
					" WHERE (" 
			+ sField1 + " = '" + sCodCOCLDO + "' AND  "
			+ sField2 + " = '" + sCodNUDCOM + "' AND  "
			+ sField3 + " = '" + sCodCOACES +
			"')");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sID = rs.getString(sField4);
					
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
			logger.error("ERROR: COACES:|{}|",sCodCOACES);

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
		return sID;
	}
	
	public static boolean setValidado(String sCodMovimiento, String sValidado)
	{
		Statement stmt = null;
		boolean bSalida = true;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField5 + " = '"+ sValidado + "' "+
					" WHERE "+
					"(" 
					+ sField4 + " = '" + sCodMovimiento	+ "'" +
					")");
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: Movimiento:|{}|",sCodMovimiento);

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
			
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

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField5 + "  FROM " + sTable + 
					" WHERE " +
					"(" 
					+ sField4 + " = '" + sCodMovimiento	+ "'" +
					")");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sValidado = rs.getString(sField5);
					
					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",sField5,sValidado);

				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: Movimiento:|{}|",sCodMovimiento);

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

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM " + sTable + 
					" WHERE " +
					"(" + sField5 + " = '" + sCodValidado + "')");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					liNumero = rs.getLong("COUNT(*)");
					
					logger.debug("Encontrado el registro!");

					logger.debug( "Numero de registros:|{}|",liNumero);


				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: CodValidado:|{}|",sCodValidado);

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

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					   + QMActivos.sField1 + ","        
					   + QMActivos.sField14 + ","
					   + QMActivos.sField11 + ","
					   + QMActivos.sField13 + ","
					   + QMActivos.sField6 + ","
					   + QMActivos.sField9 + ","
					   + QMActivos.sField7 + ","
					   + QMActivos.sField10 +
					   //+ QMActivos.sField81 + 
					   "  FROM " + QMActivos.sTable + 
					   " WHERE "+ QMActivos.sField1 +" IN (SELECT "
					   +  sField3 + 
					   "  FROM " + sTable + 
					   " WHERE " +
					   "("
					   + sField1 + " = '" + sCodCOCLDO	+ "' AND "  
					   + sField2 + " = '" + sCodNUDCOM	+ "'" +
					   "))");
			

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					sCOACES = rs.getString(QMActivos.sField1);
					sCOPOIN = rs.getString(QMActivos.sField14);
					sNOMUIN = rs.getString(QMActivos.sField11);
					sNOPRAC = rs.getString(QMActivos.sField13);
					sNOVIAS = rs.getString(QMActivos.sField6);
					sNUPIAC = rs.getString(QMActivos.sField9);
					sNUPOAC = rs.getString(QMActivos.sField7);
					sNUPUAC = rs.getString(QMActivos.sField10);
					//sNURCAT = rs.getString(QMActivos.sField81);
					
					ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, "");
					
					result.add(activoencontrado);
					
					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",QMActivos.sField1,sCOACES);
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: COCLDO:|{}|",sCodCOCLDO);
			logger.error("ERROR: NUDCOM:|{}|",sCodNUDCOM);

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

		try 
		{
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement("SELECT "
					
					   + QMActivos.sField1 + ","        
					   + QMActivos.sField14 + ","
					   + QMActivos.sField11 + ","
					   + QMActivos.sField13 + ","
					   + QMActivos.sField6 + ","
					   + QMActivos.sField9 + ","
					   + QMActivos.sField7 + ","
					   + QMActivos.sField10 + 

					   "  FROM " + QMActivos.sTable + 
					   " WHERE ("

					   + QMActivos.sField14 + " LIKE '%" + activo.getCOPOIN()	+ "%' AND "  
					   + QMActivos.sField11 + " LIKE '%" + activo.getNOMUIN()	+ "%' AND "  
					   + QMActivos.sField13 + " LIKE '%" + activo.getNOPRAC()	+ "%' AND "  
					   + QMActivos.sField6 + " LIKE '%" + activo.getNOVIAS()	+ "%' AND "  
					   + QMActivos.sField9 + " LIKE '%" + activo.getNUPIAC()	+ "%' AND "  
					   + QMActivos.sField7 + " LIKE '%" + activo.getNUPOAC()	+ "%' AND "  
					   + QMActivos.sField10 + " LIKE '%" + activo.getNUPUAC()	+ "%' AND "			

					   + QMActivos.sField1 +" NOT IN (SELECT "

					   +  sField3 + 
					   "  FROM " + sTable + 
					   " WHERE "
					   
						+ sField1 + " IN (SELECT "
						+ QMComunidades.sField1 +
						"  FROM " + QMComunidades.sTable + 
						" WHERE " + QMComunidades.sField15 + " = '" + ValoresDefecto.DEF_ALTA + "' ) " +

						" AND "

						+ sField2 + " IN (SELECT "
						+ QMComunidades.sField2 +
						"  FROM " + QMComunidades.sTable + 
						" WHERE " + QMComunidades.sField15 + " = '" + ValoresDefecto.DEF_ALTA + "' )" + 						
			   
					   	" ))");

			


			

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					sCOACES = rs.getString(QMActivos.sField1);
					sCOPOIN = rs.getString(QMActivos.sField14);
					sNOMUIN = rs.getString(QMActivos.sField11);
					sNOPRAC = rs.getString(QMActivos.sField13);
					sNOVIAS = rs.getString(QMActivos.sField6);
					sNUPIAC = rs.getString(QMActivos.sField9);
					sNUPOAC = rs.getString(QMActivos.sField7);
					sNUPUAC = rs.getString(QMActivos.sField10);
					
					ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, "");
					
					result.add(activoencontrado);
					
					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",QMActivos.sField1,sCOACES);
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
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

		try 
		{
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement("SELECT "
					
					   + QMActivos.sField1 + ","        
					   + QMActivos.sField14 + ","
					   + QMActivos.sField11 + ","
					   + QMActivos.sField13 + ","
					   + QMActivos.sField6 + ","
					   + QMActivos.sField9 + ","
					   + QMActivos.sField7 + ","
					   + QMActivos.sField10 + 

					   "  FROM " + QMActivos.sTable + 
					   " WHERE ("

					   + QMActivos.sField14 + " LIKE '%" + activo.getCOPOIN()	+ "%' AND "  
					   + QMActivos.sField11 + " LIKE '%" + activo.getNOMUIN()	+ "%' AND "  
					   + QMActivos.sField13 + " LIKE '%" + activo.getNOPRAC()	+ "%' AND "  
					   + QMActivos.sField6 + " LIKE '%" + activo.getNOVIAS()	+ "%' AND "  
					   + QMActivos.sField9 + " LIKE '%" + activo.getNUPIAC()	+ "%' AND "  
					   + QMActivos.sField7 + " LIKE '%" + activo.getNUPOAC()	+ "%' AND "  
					   + QMActivos.sField10 + " LIKE '%" + activo.getNUPUAC()	+ "%' AND "			

					   + QMActivos.sField1 +" IN (SELECT "

					   +  sField3 + 
					   "  FROM " + sTable + 
					   " WHERE "
					   
						+ sField1 + " IN (SELECT "
						+ QMComunidades.sField1 +
						"  FROM " + QMComunidades.sTable + 
						" WHERE " + QMComunidades.sField15 + " = '" + ValoresDefecto.DEF_ALTA + "' ) " +

						" AND "

						+ sField2 + " IN (SELECT "
						+ QMComunidades.sField2 +
						"  FROM " + QMComunidades.sTable + 
						" WHERE " + QMComunidades.sField15 + " = '" + ValoresDefecto.DEF_ALTA + "' )" + 						
			   
					   	" ))");

			


			

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					sCOACES = rs.getString(QMActivos.sField1);
					sCOPOIN = rs.getString(QMActivos.sField14);
					sNOMUIN = rs.getString(QMActivos.sField11);
					sNOPRAC = rs.getString(QMActivos.sField13);
					sNOVIAS = rs.getString(QMActivos.sField6);
					sNUPIAC = rs.getString(QMActivos.sField9);
					sNUPOAC = rs.getString(QMActivos.sField7);
					sNUPUAC = rs.getString(QMActivos.sField10);
					
					ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, "");
					
					result.add(activoencontrado);
					
					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",QMActivos.sField1,sCOACES);
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
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

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField4+ "  FROM " + sTable + 
					" WHERE (" + sField5 + " = '" + sEstado + "' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
		
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(sField4));
										
					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",sField5,sEstado);
					logger.debug("{}:|{}|",sField4,result.get(i));

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
			logger.error("ERROR: Validado:|{}|",sEstado);

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

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					   + QMComunidades.sField1 + ","        
					   + QMComunidades.sField2 + ","
					   + QMComunidades.sField3 + ","
					   + QMComunidades.sField5 + ","
					   + QMComunidades.sField7 + 
					   
					   "  FROM " + QMComunidades.sTable + 
					   " WHERE "
					   + QMComunidades.sField1 + " IN " +
					   "(SELECT " + sField1 +
					   " FROM " + sTable +
					   " WHERE "
					   + sField3 +  " = '" + sCodCOACES	+ "') AND " 
					   + QMComunidades.sField2 + " IN " +
					   "(SELECT " + sField2 +
					   " FROM " + sTable +
					   " WHERE "
					   + sField3 +  " = '" + sCodCOACES	+ "')");
		
			

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					sCOCLDO = rs.getString(QMActivos.sField1);
					sNUDCOM = rs.getString(QMActivos.sField2);
					sNOMCOC = rs.getString(QMActivos.sField3);
					sNOMPRC = rs.getString(QMActivos.sField5);
					sNOMADC = rs.getString(QMActivos.sField7);
					
					
					ComunidadTabla comunidadencontrada = new ComunidadTabla(sCOCLDO, sNUDCOM, sNOMCOC, sNOMPRC, sNOMADC);
					
					result.add(comunidadencontrada);
					
					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",sField3,sCodCOACES);
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: COACES:|{}|",sCodCOACES);

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

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					   + QMComunidades.sField1  + ","              
				       + QMComunidades.sField2  + ","
				       + QMComunidades.sField3  + ","              
				       + QMComunidades.sField4  + ","              
				       + QMComunidades.sField5  + ","              
				       + QMComunidades.sField6  + ","              
				       + QMComunidades.sField7  + ","              
				       + QMComunidades.sField8  + ","              
				       + QMComunidades.sField9  + ","              
				       + QMComunidades.sField10 + ","              
				       + QMComunidades.sField11 + ","              
				       + QMComunidades.sField12 + ","              
				       + QMComunidades.sField13 + ","              
				       + QMComunidades.sField14 +     
					   
					   "  FROM " + QMComunidades.sTable + 
					   " WHERE "
					   + QMComunidades.sField1 + " IN " +
					   "(SELECT " + sField1 +
					   " FROM " + sTable +
					   " WHERE "
					   + sField3 +  " = '" + sCodCOACES	+ "') AND " 
					   + QMComunidades.sField2 + " IN " +
					   "(SELECT " + sField2 +
					   " FROM " + sTable +
					   " WHERE "
					   + sField3 +  " = '" + sCodCOACES	+ "')");
		
			

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					sCOCLDO = rs.getString(QMComunidades.sField1);  
					sNUDCOM = rs.getString(QMComunidades.sField2);  
					sNOMCOC = rs.getString(QMComunidades.sField3);  
					sNODCCO = rs.getString(QMComunidades.sField4);  
					sNOMPRC = rs.getString(QMComunidades.sField5);  
					sNUTPRC = rs.getString(QMComunidades.sField6);  
					sNOMADC = rs.getString(QMComunidades.sField7);  
					sNUTADC = rs.getString(QMComunidades.sField8);  
					sNODCAD = rs.getString(QMComunidades.sField9);  
					sNUCCEN = rs.getString(QMComunidades.sField10); 
					sNUCCOF = rs.getString(QMComunidades.sField11); 
					sNUCCDI = rs.getString(QMComunidades.sField12); 
					sNUCCNT = rs.getString(QMComunidades.sField13); 
					sOBTEXC = rs.getString(QMComunidades.sField14); 
					
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",sField3,sCodCOACES);
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: COACES:|{}|",sCodCOACES);

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
	
}