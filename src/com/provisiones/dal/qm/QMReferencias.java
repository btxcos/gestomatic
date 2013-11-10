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

	//Primary Key
	public static final String CAMPO1  = "e3_referencia_id";
	
	//Unique Key - referencia
	public static final String CAMPO2  = "nurcat_id";
	
	//Campos secundarios
	public static final String CAMPO3  = "tircat";
	public static final String CAMPO4  = "enemis";    
	public static final String CAMPO5  = "cotexa";    
	public static final String CAMPO6  = "obtexc";    
	public static final String CAMPO7  = "imvsue";    
	public static final String CAMPO8  = "imcata";    
	public static final String CAMPO9  = "fereca";
	
	//Campos de control
	public static final String CAMPO10 = "cod_estado";	

	public static long addReferenciaCatastral(ReferenciaCatastral NuevaReferenciaCatastral)

	{
		Connection conn = null;

		Statement stmt = null;
		ResultSet resulset = null;
		
		conn = ConnectionManager.getDBConnection();

		long liCodigo = 0;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "INSERT INTO " 
				   + TABLA + 
				   " ("
			       + CAMPO2  + ","              
			       + CAMPO3  + ","              
			       + CAMPO4  + ","
			       + CAMPO5  + ","
			       + CAMPO6  + ","              
			       + CAMPO7  + ","
			       + CAMPO8  + ","
			       + CAMPO9  + ","
			       + CAMPO10 +              
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

		try 
		{

			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery, Statement.RETURN_GENERATED_KEYS);
			
			resulset = stmt.getGeneratedKeys();
			
			if (resulset.next()) 
			{
				liCodigo= resulset.getLong(1);
			} 

			logger.debug("Ejecutada con exito!");
			
		}
		catch (SQLException ex) 
		{
			liCodigo = 0;

			logger.error("ERROR NURCAT:|"+NuevaReferenciaCatastral.getNURCAT()+"|");
			
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return liCodigo;
	}
	public static boolean modReferenciaCatastral(ReferenciaCatastral NuevaReferenciaCatastral, String sCodReferencia)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				//+ CAMPO2  + " = '"+ NuevaReferenciaCatastral.getNURCAT() + "', "
				+ CAMPO3  + " = '"+ NuevaReferenciaCatastral.getTIRCAT() + "', "
				+ CAMPO4  + " = '"+ NuevaReferenciaCatastral.getENEMIS() + "', "
				+ CAMPO5  + " = '"+ NuevaReferenciaCatastral.getCOTEXA() + "', "
				+ CAMPO6  + " = '"+ NuevaReferenciaCatastral.getOBTEXC() + "', "
				
				//Ampliacion de valor catastral
				+ CAMPO7  + " = '"+ NuevaReferenciaCatastral.getIMVSUE() + "', "
				+ CAMPO8  + " = '"+ NuevaReferenciaCatastral.getIMCATA() + "', "
				+ CAMPO9  + " = '"+ NuevaReferenciaCatastral.getFERECA() +
				"' "+
				" WHERE "
				+ CAMPO1 + " = '"+ sCodReferencia +"'";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Referencia:|"+sCodReferencia+"|");

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

	public static boolean delReferenciaCatastral(String sCodReferencia)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "DELETE FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO1 + " = '" + sCodReferencia + "'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Referencia:|"+sCodReferencia+"|");

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

	public static ReferenciaCatastral getReferenciaCatastral(String sCodReferencia)
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
			       + CAMPO2  + ","
			       + CAMPO3  + ","              
			       + CAMPO4  + ","              
			       + CAMPO5  + ","              
			       + CAMPO6  +  

			       //Ampliacion de valor catastral
			       ","              
			       + CAMPO7  + ","              
			       + CAMPO8  + ","              
			       + CAMPO9  +

			       " FROM " 
			       + TABLA + 
			       " WHERE " + CAMPO1 + " = '" + sCodReferencia	+ "'";
		
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

  					sNURCAT = rs.getString(CAMPO2); 
  					sTIRCAT = rs.getString(CAMPO3); 
  					sENEMIS = rs.getString(CAMPO4);
  					sCOTEXA = rs.getString(CAMPO5);
  					sOBTEXC = rs.getString(CAMPO6);

  					//Ampliacion de valor catastral
  					sIMVSUE = rs.getString(CAMPO7);
  					sIMCATA = rs.getString(CAMPO8);
  					sFERECA = rs.getString(CAMPO9);
  					
  					logger.debug("Encontrado el registro!");

  					logger.debug(CAMPO2+":|"+sNURCAT+"|");
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Referencia:|"+sCodReferencia+"|");

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
	
	public static String getReferenciaCatastralID(String sCodNURCAT)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sReferenciaID = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO1 + 
				" FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO2 + " = '" + sCodNURCAT + "'";
		
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

					sReferenciaID = rs.getString(CAMPO1);

					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO1+":|"+sReferenciaID+"|");


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
		return sReferenciaID;
	}
	
	public static boolean existeReferenciaCatastral(String sCodReferencia)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
			       + CAMPO4  +              
			       " FROM " 
			       + TABLA + 
			       " WHERE " 
			       + CAMPO1 + " = '" + sCodReferencia	+ "'";
		
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

  					logger.debug(CAMPO1+":|"+sCodReferencia+"|");
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Referencia:|"+sCodReferencia+"|");

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

	public static boolean setEstado(String sCodReferencia, String sEstado)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO10 + " = '"+ sEstado + 
				"' "+
				" WHERE "
				+ CAMPO1 + " = '" + sCodReferencia + "'";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Referencia:|"+sCodReferencia+"|");

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
	
	public static String getEstado(String sCodReferencia)
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
				+ CAMPO10 + 
				" FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO1 + " = '" + sCodReferencia + "'";
		
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

					sEstado = rs.getString(CAMPO10);

					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO10+":|"+sEstado+"|");


				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Referencia:|"+sCodReferencia+"|");

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
