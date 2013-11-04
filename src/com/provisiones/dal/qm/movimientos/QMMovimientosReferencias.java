package com.provisiones.dal.qm.movimientos;

import com.provisiones.dal.ConnectionManager;

import com.provisiones.misc.Utils;
import com.provisiones.types.movimientos.MovimientoReferenciaCatastral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QMMovimientosReferencias
{
	private static Logger logger = LoggerFactory.getLogger(QMMovimientosReferencias.class.getName());

	public static String TABLA = "pp001_e3_movimientos_tbl";

	public static String CAMPO1  = "e3_movimiento_id";
	static String CAMPO2  = "cod_codtrn";  
	static String CAMPO3  = "cod_cotdor";  
	static String CAMPO4  = "idprov";      
	static String CAMPO5  = "cod_coacci";  
	static String CAMPO6  = "coengp";       
	public static String CAMPO7  = "cod_coaces";
	public static String CAMPO8  = "nurcat_id";   
	static String CAMPO9  = "cod_bitc16";  
	static String CAMPO10 = "tircat";      
	static String CAMPO11 = "cod_bitc17";  
	static String CAMPO12 = "enemis";      
	static String CAMPO13 = "cotexa";      
	static String CAMPO14 = "cod_bitc09";  
	static String CAMPO15 = "obtexc";    
	static String CAMPO16 = "obdeer";

	//Ampliacion de valor catastral
	static String CAMPO17 = "cod_bitc23";  
	static String CAMPO18 = "imvsue";      
	static String CAMPO19 = "cod_bitc24";      
	static String CAMPO20 = "imcata";  
	static String CAMPO21 = "cod_bitc25";    
	static String CAMPO22 = "fereca";


	public static int addMovimientoReferenciaCatastral(MovimientoReferenciaCatastral NuevoMovimientoReferenciaCatastral)
	{
		Statement stmt = null;
		Connection conn = null;
		ResultSet resulset = null;
		
		int iCodigo = 0;

		conn = ConnectionManager.OpenDBConnection();
		
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
			       + CAMPO10 + ","              
			       + CAMPO11 + ","              
			       + CAMPO12 + ","              
			       + CAMPO13 + ","
			       + CAMPO14 + ","
			       + CAMPO15 + ","
			       + CAMPO16 +
			       //Ampliacion de valor catastral
			       ","
			       + CAMPO17 + ","              
			       + CAMPO18 + ","              
			       + CAMPO19 + ","
			       + CAMPO20 + ","
			       + CAMPO21 + ","
			       + CAMPO22 +
			       
			       ") VALUES ('" 
			       + NuevoMovimientoReferenciaCatastral.getCODTRN() + "','" 
			       + NuevoMovimientoReferenciaCatastral.getCOTDOR() + "','"
			       + NuevoMovimientoReferenciaCatastral.getIDPROV() + "','"
			       + NuevoMovimientoReferenciaCatastral.getCOACCI() + "','"
			       + NuevoMovimientoReferenciaCatastral.getCOENGP() + "','"
			       + NuevoMovimientoReferenciaCatastral.getCOACES() + "','"
			       + NuevoMovimientoReferenciaCatastral.getNURCAT() + "','"
			       + NuevoMovimientoReferenciaCatastral.getBITC16() + "','"
			       + NuevoMovimientoReferenciaCatastral.getTIRCAT() + "','"
			       + NuevoMovimientoReferenciaCatastral.getBITC17() + "','"
			       + NuevoMovimientoReferenciaCatastral.getENEMIS() + "','"
			       + NuevoMovimientoReferenciaCatastral.getCOTEXA() + "','"
			       + NuevoMovimientoReferenciaCatastral.getBITC09() + "','"
			       + NuevoMovimientoReferenciaCatastral.getOBTEXC() + "','"
			       + NuevoMovimientoReferenciaCatastral.getOBDEER()

			       //Ampliacion de valor catastral
			       + "','"
			       + NuevoMovimientoReferenciaCatastral.getBITC23() + "','"
			       + NuevoMovimientoReferenciaCatastral.getIMVSUE() + "','"
			       + NuevoMovimientoReferenciaCatastral.getBITC24() + "','"
			       + NuevoMovimientoReferenciaCatastral.getIMCATA() + "','"
			       + NuevoMovimientoReferenciaCatastral.getBITC25() + "','"
			       + NuevoMovimientoReferenciaCatastral.getFERECA() + 
			       "' )";
		
		logger.debug(sQuery);

		try 
		{

			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery, Statement.RETURN_GENERATED_KEYS);
			
			resulset = stmt.getGeneratedKeys();
			
			logger.debug("Ejecutada con exito!");
			
			if (resulset.next()) 
			{
				iCodigo= resulset.getInt(1);
			} 
		} 
		catch (SQLException ex) 
		{


			//logger.error("ERROR COGRAP:|"+NuevaComunidad.getCOGRAP()+"|");
			
			logger.error("ERROR NURCAT:|"+NuevoMovimientoReferenciaCatastral.getNURCAT()+"|");
			logger.error("ERROR COACES:|"+NuevoMovimientoReferenciaCatastral.getCOACES()+"|");
			
			
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			//bSalida = false;
		} 
		finally
		{

			Utils.closeStatement(stmt);
			Utils.closeResultSet(resulset);
		}
		ConnectionManager.CloseDBConnection(conn);
		return iCodigo;
	}
	public static boolean modMovimientoReferenciaCatastral(MovimientoReferenciaCatastral NuevoMovimientoReferenciaCatastral, String sMovimientoReferenciaCatastralID)
	{
		Statement stmt = null;
		boolean bSalida = true;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO2  + " = '"+ NuevoMovimientoReferenciaCatastral.getCODTRN() + "', "
				+ CAMPO3  + " = '"+ NuevoMovimientoReferenciaCatastral.getCOTDOR() + "', "
				+ CAMPO4  + " = '"+ NuevoMovimientoReferenciaCatastral.getIDPROV() + "', "
				+ CAMPO5  + " = '"+ NuevoMovimientoReferenciaCatastral.getCOACCI() + "', "
				+ CAMPO6  + " = '"+ NuevoMovimientoReferenciaCatastral.getCOENGP() + "', "
				+ CAMPO7  + " = '"+ NuevoMovimientoReferenciaCatastral.getCOACES() + "', "
				+ CAMPO8  + " = '"+ NuevoMovimientoReferenciaCatastral.getNURCAT() + "', "
				+ CAMPO9  + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC16() + "', "
				+ CAMPO10 + " = '"+ NuevoMovimientoReferenciaCatastral.getTIRCAT() + "', "
				+ CAMPO11 + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC17() + "', "
				+ CAMPO12 + " = '"+ NuevoMovimientoReferenciaCatastral.getENEMIS() + "', "
				+ CAMPO13 + " = '"+ NuevoMovimientoReferenciaCatastral.getCOTEXA() + "', "
				+ CAMPO14 + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC09() + "', "
				+ CAMPO15 + " = '"+ NuevoMovimientoReferenciaCatastral.getOBTEXC() + "', "
				+ CAMPO16 + " = '"+ NuevoMovimientoReferenciaCatastral.getOBDEER() + 

				//Ampliacion de valor catastral
				"', "
				+ CAMPO17 + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC23() + "', "
				+ CAMPO18 + " = '"+ NuevoMovimientoReferenciaCatastral.getIMVSUE() + "', "
				+ CAMPO19 + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC24() + "', "
				+ CAMPO20 + " = '"+ NuevoMovimientoReferenciaCatastral.getIMCATA() + "', "
				+ CAMPO21 + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC25() + "', "
				+ CAMPO22 + " = '"+ NuevoMovimientoReferenciaCatastral.getFERECA() + 
				
				"' "+
				" WHERE "
				+ CAMPO1 + " = '"+ sMovimientoReferenciaCatastralID +"'";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR MovimientoReferenciaCatastralID:|"+sMovimientoReferenciaCatastralID+"|");

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

	public static boolean delMovimientoReferenciaCatastral(String sMovimientoReferenciaCatastralID)
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "DELETE FROM " 
				+ TABLA + 
				" WHERE "
				+ CAMPO1 + " = '" + sMovimientoReferenciaCatastralID + "'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR MovimientoReferenciaCatastralID:|"+sMovimientoReferenciaCatastralID+"|");

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

	public static String getMovimientoReferenciaCatastralID(MovimientoReferenciaCatastral referencia)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sMovimientoReferenciaCatastralID = "";
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
				+ CAMPO1  +               
				" FROM " 
				+ TABLA + 
				" WHERE ("
				+ CAMPO2  + " = '" + referencia.getCODTRN()	+ "' AND "
				+ CAMPO3  + " = '" + referencia.getCOTDOR()	+ "' AND "
				+ CAMPO4  + " = '" + referencia.getIDPROV()	+ "' AND "
				+ CAMPO5  + " = '" + referencia.getCOACCI()	+ "' AND "
				+ CAMPO6  + " = '" + referencia.getCOENGP()	+ "' AND "
				+ CAMPO7  + " = '" + referencia.getCOACES()	+ "' AND "
				+ CAMPO8  + " = '" + referencia.getNURCAT()	+ "' AND "
				+ CAMPO9  + " = '" + referencia.getBITC16()	+ "' AND "
				+ CAMPO10 + " = '" + referencia.getTIRCAT()	+ "' AND "
				+ CAMPO11 + " = '" + referencia.getBITC17()	+ "' AND "
				+ CAMPO12 + " = '" + referencia.getENEMIS()	+ "' AND "
				+ CAMPO13 + " = '" + referencia.getCOTEXA()	+ "' AND "
				+ CAMPO14 + " = '" + referencia.getBITC09()	+ "' AND "
				+ CAMPO15 + " = '" + referencia.getOBTEXC()	+ "' AND "
				+ CAMPO16 + " = '" + referencia.getOBDEER()	+ "'" +


				//Ampliacion de valor catastral
				" AND "
				+ CAMPO17 + " = '" + referencia.getBITC23()	+ "' AND "
				+ CAMPO18 + " = '" + referencia.getIMVSUE()	+ "' AND "
				+ CAMPO19 + " = '" + referencia.getBITC24()	+ "' AND "
				+ CAMPO20 + " = '" + referencia.getIMCATA()	+ "' AND "
				+ CAMPO21 + " = '" + referencia.getBITC25()	+ "' AND "
				+ CAMPO22 + " = '" + referencia.getFERECA()	+ "'" +

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

					sMovimientoReferenciaCatastralID = rs.getString(CAMPO1); 

  					
  					logger.debug("Encontrado el registro!");

  					logger.debug(CAMPO1+":|"+sMovimientoReferenciaCatastralID+"|");

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
		return sMovimientoReferenciaCatastralID;
	}
	
	public static MovimientoReferenciaCatastral getMovimientoReferenciaCatastral(String sMovimientoReferenciaCatastralID)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sCODTRN = "";
		String sCOTDOR = "";
		String sIDPROV = "";
		String sCOACCI = "";
		String sCOENGP = "";
		String sCOACES = "";
		String sNURCAT = "";
		String sBITC16 = "";
		String sTIRCAT = "";
		String sBITC17 = "";
		String sENEMIS = "";
		String sCOTEXA = "";
		String sBITC09 = "";
		String sOBTEXC = "";
		String sOBDEER = "";

		//Ampliacion de valor catastral
		String sBITC23 = "";
		String sIMVSUE = "";
		String sBITC24 = "";
		String sIMCATA = "";
		String sBITC25 = "";
		String sFERECA = "";
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
			       + CAMPO2  + ","              
			       + CAMPO3  + ","              
			       + CAMPO4  + ","              
			       + CAMPO5  + ","              
			       + CAMPO6  + ","              
			       + CAMPO7  + ","              
			       + CAMPO8  + ","              
			       + CAMPO9  + ","              
			       + CAMPO10 + ","              
			       + CAMPO11 + ","              
			       + CAMPO12 + ","              
			       + CAMPO13 + ","
			       + CAMPO14 + ","
			       + CAMPO15 + ","
			       + CAMPO16 +             

			       //Ampliacion de valor catastral
			       ","
			       + CAMPO17 + ","              
			       + CAMPO18 + ","              
			       + CAMPO19 + ","
			       + CAMPO20 + ","
			       + CAMPO21 + ","
			       + CAMPO22 + 

			       " FROM " 
			       + TABLA + 
			       " WHERE " 
			       + CAMPO1 + " = '" + sMovimientoReferenciaCatastralID	+ "'";
		
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

  					sCODTRN = rs.getString(CAMPO2); 
  					sCOTDOR = rs.getString(CAMPO3); 
  					sIDPROV = rs.getString(CAMPO4); 
  					sCOACCI = rs.getString(CAMPO5); 
  					sCOENGP = rs.getString(CAMPO6); 
  					sCOACES = rs.getString(CAMPO7); 
  					sNURCAT = rs.getString(CAMPO8); 
  					sBITC16 = rs.getString(CAMPO9); 
  					sTIRCAT = rs.getString(CAMPO10);
  					sBITC17 = rs.getString(CAMPO11);
  					sENEMIS = rs.getString(CAMPO12);
  					sCOTEXA = rs.getString(CAMPO13);
  					sBITC09 = rs.getString(CAMPO14);
  					sOBTEXC = rs.getString(CAMPO15);
  					sOBDEER = rs.getString(CAMPO16);

  					sBITC23 = rs.getString(CAMPO17);
  					sIMVSUE = rs.getString(CAMPO18);
  					sBITC24 = rs.getString(CAMPO19);
  					sIMCATA = rs.getString(CAMPO20);
  					sBITC25 = rs.getString(CAMPO21);
  					sFERECA = rs.getString(CAMPO22);
  					
  					logger.debug("Encontrado el registro!");

  					logger.debug(CAMPO1+":|"+sMovimientoReferenciaCatastralID+"|");

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR MovimientoReferenciaCatastralID:|"+sMovimientoReferenciaCatastralID+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return new MovimientoReferenciaCatastral(sCODTRN, sCOTDOR, sIDPROV, sCOACCI,
				sCOENGP, sCOACES, sNURCAT, sBITC16, sTIRCAT, sBITC17, sENEMIS,
				sCOTEXA, sBITC09, sOBTEXC, sOBDEER

				//Ampliacion de valor catastral
				,sBITC23, sIMVSUE, sBITC24, sIMCATA, sBITC25, sFERECA
				);
	}


	public static boolean existeMovimientoReferenciaCatastral(String sMovimientoReferenciaID)
	{
		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		Connection conn = null;
		
		boolean found = false;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO1 + 
				" FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO1 + " = '" + sMovimientoReferenciaID + "'";
		
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
					logger.debug(CAMPO1+":|"+rs.getString(CAMPO1)+"|");
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontro la información.");
			}
		} 
		catch (SQLException ex) 
		{
			found = false;
			logger.error("ERROR sMovimientoReferenciaID:|"+sMovimientoReferenciaID+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return found;
	}
	
}
