package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ReferenciaCatastral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QMReferencias
{
	private static Logger logger = LoggerFactory.getLogger(QMReferencias.class.getName());
	
	public static final String sTable = "e3_referencias_tbl";

	public static final String sField1  = "nurcat_id";    
	public static final String sField2  = "tircat";    
	public static final String sField3 = "enemis";    
	public static final String sField4 = "cotexa";    
	public static final String sField5 = "obtexc";

	//Ampliacion de valor catastral
	public static final String sField6 = "imvsue";    
	public static final String sField7 = "imcata";    
	public static final String sField8 = "fereca";
	
	public static final String sField9 = "cod_estado";

	public static boolean addReferenciaCatastral(ReferenciaCatastral NuevaReferenciaCatastral)

	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");

		try {

			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + sTable + " ("
					   + sField1  + ","
				       + sField2  + ","              
				       + sField3  + ","              
				       + sField4  + ","
				       + sField5  + ","
				       + sField6  + ","              
				       + sField7  + ","
				       + sField8  + ","
				       + sField9  +              
				       ") VALUES ('" 
				       + NuevaReferenciaCatastral.getNURCAT() + "','"
				       + NuevaReferenciaCatastral.getTIRCAT() + "','"
				       + NuevaReferenciaCatastral.getENEMIS() + "','"
				       + NuevaReferenciaCatastral.getCOTEXA() + "','"
				       + NuevaReferenciaCatastral.getOBTEXC() + "','"
				       
				       //Ampliacion de valor catastral
				       + NuevaReferenciaCatastral.getIMVSUE() + "','"
				       + NuevaReferenciaCatastral.getIMCATA() + "','"
				       + NuevaReferenciaCatastral.getFERECA() + "','"

				       + ValoresDefecto.DEF_ALTA + "' )");
			
			logger.debug("Ejecutada con exito!");

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: NURCAT:|{}|",NuevaReferenciaCatastral.getNURCAT());
			
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
	public static boolean modReferenciaCatastral(ReferenciaCatastral NuevaReferenciaCatastral, String sCodNURCAT)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField1  + " = '"+ NuevaReferenciaCatastral.getNURCAT() + "', "
					+ sField2  + " = '"+ NuevaReferenciaCatastral.getTIRCAT() + "', "
					+ sField3  + " = '"+ NuevaReferenciaCatastral.getENEMIS() + "', "
					+ sField4  + " = '"+ NuevaReferenciaCatastral.getCOTEXA() + "', "
					+ sField5  + " = '"+ NuevaReferenciaCatastral.getOBTEXC() + "', "
					
					//Ampliacion de valor catastral
					+ sField6  + " = '"+ NuevaReferenciaCatastral.getIMVSUE() + "', "
					+ sField7  + " = '"+ NuevaReferenciaCatastral.getIMCATA() + "', "
					+ sField8  + " = '"+ NuevaReferenciaCatastral.getFERECA() +
					"' "+
					" WHERE "
					+ sField1 + " = '"+ sCodNURCAT +"'");
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: NURCAT:|{}|",NuevaReferenciaCatastral.getNURCAT());

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

	public static boolean delReferenciaCatastral(String sCodNURCAT)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sCodNURCAT + "' )");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: NURCAT:|{}|",sCodNURCAT);

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

	public static ReferenciaCatastral getReferenciaCatastral(String sCodNURCAT)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();		

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sNURCAT = "";
		String sTIRCAT = "";
		String sENEMIS = "";
		String sCOTEXA = "";
		String sOBTEXC = "";
		
		//Ampliacion de valor catastral
		String sIMVSUE = "";
		String sIMCATA = "";
		String sFERECA = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
				       + sField1  + ","
				       + sField2  + ","              
				       + sField3  + ","              
				       + sField4  + ","              
				       + sField5  +  

				       //Ampliacion de valor catastral
				       ","              
				       + sField6  + ","              
				       + sField7  + ","              
				       + sField8  +
       
			"  FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sCodNURCAT	+ "')");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

  					sNURCAT = rs.getString(sField1); 
  					sTIRCAT = rs.getString(sField2); 
  					sENEMIS = rs.getString(sField3);
  					sCOTEXA = rs.getString(sField4);
  					sOBTEXC = rs.getString(sField5);

  					//Ampliacion de valor catastral
  					sIMVSUE = rs.getString(sField6);
  					sIMCATA = rs.getString(sField7);
  					sFERECA = rs.getString(sField8);
  					
  					logger.debug("Encontrado el registro!");

  					logger.debug("{}:|{}|",sField1,sCodNURCAT);
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: NURCAT:|{}|",sCodNURCAT);

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
		return new ReferenciaCatastral(sNURCAT, sTIRCAT, sENEMIS, sCOTEXA, sOBTEXC, sIMVSUE, sIMCATA, sFERECA);
	}
	
	public static boolean existeReferenciaCatastral(String sCodNURCAT)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		boolean found = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
				       + sField1  +              
			"  FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sCodNURCAT	+ "')");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

  					logger.debug("Encontrado el registro!");

  					logger.debug("{}:|{}|",sField1,sCodNURCAT);
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: NURCAT:|{}|",sCodNURCAT);

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

	public static boolean setEstado(String sCodNURCAT, String sEstado)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField9 + " = '"+ sEstado + 
					"' "+
					" WHERE "+
					"(" + sField1 + " = '" + sCodNURCAT + "')");
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: NURCAT:|{}|",sCodNURCAT);

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
	
	public static String getEstado(String sCodNURCAT)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sEstado = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField9 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodNURCAT + "')");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sEstado = rs.getString(sField9);

					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",sField9,sEstado);


				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: NURCAT:|{}|",sCodNURCAT);

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
		return sEstado;
	}
}
