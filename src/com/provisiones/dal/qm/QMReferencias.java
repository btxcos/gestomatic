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
	
	public static final String TABLA = "pp001_e3_referencias_tbl";

	public static final String CAMPO1  = "nurcat_id";    
	public static final String CAMPO2  = "tircat";    
	public static final String CAMPO3 = "enemis";    
	public static final String CAMPO4 = "cotexa";    
	public static final String CAMPO5 = "obtexc";

	//Ampliacion de valor catastral
	public static final String CAMPO6 = "imvsue";    
	public static final String CAMPO7 = "imcata";    
	public static final String CAMPO8 = "fereca";
	
	public static final String CAMPO9 = "cod_estado";

	public static boolean addReferenciaCatastral(ReferenciaCatastral NuevaReferenciaCatastral)

	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

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
			       + CAMPO6  + ","              
			       + CAMPO7  + ","
			       + CAMPO8  + ","
			       + CAMPO9  +              
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

			       + ValoresDefecto.DEF_ALTA + "' )";
		
		logger.debug(sQuery);

		try {

			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR NURCAT:|"+NuevaReferenciaCatastral.getNURCAT()+"|");
			
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	public static boolean modReferenciaCatastral(ReferenciaCatastral NuevaReferenciaCatastral, String sCodNURCAT)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO1  + " = '"+ NuevaReferenciaCatastral.getNURCAT() + "', "
				+ CAMPO2  + " = '"+ NuevaReferenciaCatastral.getTIRCAT() + "', "
				+ CAMPO3  + " = '"+ NuevaReferenciaCatastral.getENEMIS() + "', "
				+ CAMPO4  + " = '"+ NuevaReferenciaCatastral.getCOTEXA() + "', "
				+ CAMPO5  + " = '"+ NuevaReferenciaCatastral.getOBTEXC() + "', "
				
				//Ampliacion de valor catastral
				+ CAMPO6  + " = '"+ NuevaReferenciaCatastral.getIMVSUE() + "', "
				+ CAMPO7  + " = '"+ NuevaReferenciaCatastral.getIMCATA() + "', "
				+ CAMPO8  + " = '"+ NuevaReferenciaCatastral.getFERECA() +
				"' "+
				" WHERE "
				+ CAMPO1 + " = '"+ sCodNURCAT +"'";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR NURCAT:|"+NuevaReferenciaCatastral.getNURCAT()+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static boolean delReferenciaCatastral(String sCodNURCAT)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "DELETE FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO1 + " = '" + sCodNURCAT + "'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR NURCAT:|"+sCodNURCAT+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static ReferenciaCatastral getReferenciaCatastral(String sCodNURCAT)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();		

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
		
		String sQuery = "SELECT "
			       + CAMPO1  + ","
			       + CAMPO2  + ","              
			       + CAMPO3  + ","              
			       + CAMPO4  + ","              
			       + CAMPO5  +  

			       //Ampliacion de valor catastral
			       ","              
			       + CAMPO6  + ","              
			       + CAMPO7  + ","              
			       + CAMPO8  +

			       " FROM " 
			       + TABLA + 
			       " WHERE " + CAMPO1 + " = '" + sCodNURCAT	+ "'";
		
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

  					sNURCAT = rs.getString(CAMPO1); 
  					sTIRCAT = rs.getString(CAMPO2); 
  					sENEMIS = rs.getString(CAMPO3);
  					sCOTEXA = rs.getString(CAMPO4);
  					sOBTEXC = rs.getString(CAMPO5);

  					//Ampliacion de valor catastral
  					sIMVSUE = rs.getString(CAMPO6);
  					sIMCATA = rs.getString(CAMPO7);
  					sFERECA = rs.getString(CAMPO8);
  					
  					logger.debug("Encontrado el registro!");

  					logger.debug(CAMPO1+":|"+sCodNURCAT+"|");
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR NURCAT:|"+sCodNURCAT+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return new ReferenciaCatastral(sNURCAT, sTIRCAT, sENEMIS, sCOTEXA, sOBTEXC, sIMVSUE, sIMCATA, sFERECA);
	}
	
	public static boolean existeReferenciaCatastral(String sCodNURCAT)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
			       + CAMPO1  +              
			       " FROM " 
			       + TABLA + 
			       " WHERE " 
			       + CAMPO1 + " = '" + sCodNURCAT	+ "'";
		
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

  					logger.debug(CAMPO1+":|"+sCodNURCAT+"|");
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR NURCAT:|"+sCodNURCAT+"|");

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

	public static boolean setEstado(String sCodNURCAT, String sEstado)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO9 + " = '"+ sEstado + 
				"' "+
				" WHERE "
				+ CAMPO1 + " = '" + sCodNURCAT + "'";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR NURCAT:|"+sCodNURCAT+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	
	public static String getEstado(String sCodNURCAT)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sEstado = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO9 + 
				" FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO1 + " = '" + sCodNURCAT + "'";
		
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

					sEstado = rs.getString(CAMPO9);

					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO9+":|"+sEstado+"|");


				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR NURCAT:|"+sCodNURCAT+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		//ConnectionManager.CloseDBConnection(conn);
		return sEstado;
	}
}
