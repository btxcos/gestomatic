package com.provisiones.dal.qm.movimientos;

import com.provisiones.dal.ConnectionManager;

import com.provisiones.misc.Utils;
import com.provisiones.types.MovimientoReferenciaCatastral;

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

	public static String sTable = "e3_movimientos_tbl";

	public static String sField1  = "e3_movimiento_id";
	static String sField2  = "cod_codtrn";  
	static String sField3  = "cod_cotdor";  
	static String sField4  = "idprov";      
	static String sField5  = "cod_coacci";  
	static String sField6  = "coengp";       
	public static String sField7  = "cod_coaces";
	public static String sField8  = "nurcat_id";   
	static String sField9  = "cod_bitc16";  
	static String sField10 = "tircat";      
	static String sField11 = "cod_bitc17";  
	static String sField12 = "enemis";      
	static String sField13 = "cotexa";      
	static String sField14 = "cod_bitc09";  
	static String sField15 = "obtexc";    
	static String sField16 = "obdeer";

	//Ampliacion de valor catastral
	static String sField17 = "cod_bitc23";  
	static String sField18 = "imvsue";      
	static String sField19 = "cod_bitc24";      
	static String sField20 = "imcata";  
	static String sField21 = "cod_bitc25";    
	static String sField22 = "fereca";


	public static int addMovimientoReferenciaCatastral(MovimientoReferenciaCatastral NuevoMovimientoReferenciaCatastral)
	{
		Statement stmt = null;
		Connection conn = null;
		ResultSet resulset = null;
		
		int iCodigo = 0;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try {

			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + sTable + " ("
				       + sField2  + ","              
				       + sField3  + ","              
				       + sField4  + ","              
				       + sField5  + ","              
				       + sField6  + ","              
				       + sField7  + ","              
				       + sField8  + ","              
				       + sField9  + ","              
				       + sField10 + ","              
				       + sField11 + ","              
				       + sField12 + ","              
				       + sField13 + ","
				       + sField14 + ","
				       + sField15 + ","
				       + sField16 +
				       //Ampliacion de valor catastral
				       ","
				       + sField17 + ","              
				       + sField18 + ","              
				       + sField19 + ","
				       + sField20 + ","
				       + sField21 + ","
				       + sField22 +
				       
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
				       + NuevoMovimientoReferenciaCatastral.getFERECA() + "' )"

				       , Statement.RETURN_GENERATED_KEYS);
			
			resulset = stmt.getGeneratedKeys();
			
			logger.debug("Ejecutada con exito!");
			
			if (resulset.next()) 
			{
				iCodigo= resulset.getInt(1);
			} 
		} 
		catch (SQLException ex) 
		{


			//logger.error("ERROR: COGRAP:|{}|",NuevaComunidad.getCOGRAP());
			
			logger.error("ERROR: NURCAT:|{}|",NuevoMovimientoReferenciaCatastral.getNURCAT());
			logger.error("ERROR: COACES:|{}|",NuevoMovimientoReferenciaCatastral.getCOACES());
			
			
			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
			
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
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField2  + " = '"+ NuevoMovimientoReferenciaCatastral.getCODTRN() + "', "
					+ sField3  + " = '"+ NuevoMovimientoReferenciaCatastral.getCOTDOR() + "', "
					+ sField4  + " = '"+ NuevoMovimientoReferenciaCatastral.getIDPROV() + "', "
					+ sField5  + " = '"+ NuevoMovimientoReferenciaCatastral.getCOACCI() + "', "
					+ sField6  + " = '"+ NuevoMovimientoReferenciaCatastral.getCOENGP() + "', "
					+ sField7  + " = '"+ NuevoMovimientoReferenciaCatastral.getCOACES() + "', "
					+ sField8  + " = '"+ NuevoMovimientoReferenciaCatastral.getNURCAT() + "', "
					+ sField9  + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC16() + "', "
					+ sField10 + " = '"+ NuevoMovimientoReferenciaCatastral.getTIRCAT() + "', "
					+ sField11 + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC17() + "', "
					+ sField12 + " = '"+ NuevoMovimientoReferenciaCatastral.getENEMIS() + "', "
					+ sField13 + " = '"+ NuevoMovimientoReferenciaCatastral.getCOTEXA() + "', "
					+ sField14 + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC09() + "', "
					+ sField15 + " = '"+ NuevoMovimientoReferenciaCatastral.getOBTEXC() + "', "
					+ sField16 + " = '"+ NuevoMovimientoReferenciaCatastral.getOBDEER() + 

					//Ampliacion de valor catastral
					"', "
					+ sField17 + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC23() + "', "
					+ sField18 + " = '"+ NuevoMovimientoReferenciaCatastral.getIMVSUE() + "', "
					+ sField19 + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC24() + "', "
					+ sField20 + " = '"+ NuevoMovimientoReferenciaCatastral.getIMCATA() + "', "
					+ sField21 + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC25() + "', "
					+ sField22 + " = '"+ NuevoMovimientoReferenciaCatastral.getFERECA() + 
					
					"' "+
					" WHERE "
					+ sField1 + " = '"+ sMovimientoReferenciaCatastralID +"'");
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: MovimientoReferenciaCatastralID:|{}|",sMovimientoReferenciaCatastralID);

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

	public static boolean delMovimientoReferenciaCatastral(String sMovimientoReferenciaCatastralID)
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
					" WHERE (" + sField1 + " = '" + sMovimientoReferenciaCatastralID + "' )");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: MovimientoReferenciaCatastralID:|{}|",sMovimientoReferenciaCatastralID);

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
		
		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					+ sField1  +               
					" FROM " + sTable + 
					" WHERE ("
					+ sField2  + " = '" + referencia.getCODTRN()	+ "' AND "
					+ sField3  + " = '" + referencia.getCOTDOR()	+ "' AND "
					+ sField4  + " = '" + referencia.getIDPROV()	+ "' AND "
					+ sField5  + " = '" + referencia.getCOACCI()	+ "' AND "
					+ sField6  + " = '" + referencia.getCOENGP()	+ "' AND "
					+ sField7  + " = '" + referencia.getCOACES()	+ "' AND "
					+ sField8  + " = '" + referencia.getNURCAT()	+ "' AND "
					+ sField9  + " = '" + referencia.getBITC16()	+ "' AND "
					+ sField10 + " = '" + referencia.getTIRCAT()	+ "' AND "
					+ sField11 + " = '" + referencia.getBITC17()	+ "' AND "
					+ sField12 + " = '" + referencia.getENEMIS()	+ "' AND "
					+ sField13 + " = '" + referencia.getCOTEXA()	+ "' AND "
					+ sField14 + " = '" + referencia.getBITC09()	+ "' AND "
					+ sField15 + " = '" + referencia.getOBTEXC()	+ "' AND "
					+ sField16 + " = '" + referencia.getOBDEER()	+ "'" +


					//Ampliacion de valor catastral
					" AND "
					+ sField17 + " = '" + referencia.getBITC23()	+ "' AND "
					+ sField18 + " = '" + referencia.getIMVSUE()	+ "' AND "
					+ sField19 + " = '" + referencia.getBITC24()	+ "' AND "
					+ sField20 + " = '" + referencia.getIMCATA()	+ "' AND "
					+ sField21 + " = '" + referencia.getBITC25()	+ "' AND "
					+ sField22 + " = '" + referencia.getFERECA()	+ "'" +

					")"); 

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sMovimientoReferenciaCatastralID = rs.getString(sField1); 

  					
  					logger.debug("Encontrado el registro!");

  					logger.debug("{}:|{}|",sField1,sMovimientoReferenciaCatastralID);

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
		
		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
				       + sField2  + ","              
				       + sField3  + ","              
				       + sField4  + ","              
				       + sField5  + ","              
				       + sField6  + ","              
				       + sField7  + ","              
				       + sField8  + ","              
				       + sField9  + ","              
				       + sField10 + ","              
				       + sField11 + ","              
				       + sField12 + ","              
				       + sField13 + ","
				       + sField14 + ","
				       + sField15 + ","
				       + sField16 +             

				       //Ampliacion de valor catastral
				       ","
				       + sField17 + ","              
				       + sField18 + ","              
				       + sField19 + ","
				       + sField20 + ","
				       + sField21 + ","
				       + sField22 + 
       
			"  FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sMovimientoReferenciaCatastralID	+ "')");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

  					sCODTRN = rs.getString(sField2); 
  					sCOTDOR = rs.getString(sField3); 
  					sIDPROV = rs.getString(sField4); 
  					sCOACCI = rs.getString(sField5); 
  					sCOENGP = rs.getString(sField6); 
  					sCOACES = rs.getString(sField7); 
  					sNURCAT = rs.getString(sField8); 
  					sBITC16 = rs.getString(sField9); 
  					sTIRCAT = rs.getString(sField10);
  					sBITC17 = rs.getString(sField11);
  					sENEMIS = rs.getString(sField12);
  					sCOTEXA = rs.getString(sField13);
  					sBITC09 = rs.getString(sField14);
  					sOBTEXC = rs.getString(sField15);
  					sOBDEER = rs.getString(sField16);

  					sBITC23 = rs.getString(sField17);
  					sIMVSUE = rs.getString(sField18);
  					sBITC24 = rs.getString(sField19);
  					sIMCATA = rs.getString(sField20);
  					sBITC25 = rs.getString(sField21);
  					sFERECA = rs.getString(sField22);
  					
  					logger.debug("Encontrado el registro!");

  					logger.debug("{}:|{}|",sField1,sMovimientoReferenciaCatastralID);

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: MovimientoReferenciaCatastralID:|{}|",sMovimientoReferenciaCatastralID);

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

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " 
					+ sField1 + 
					" FROM " 
					+ sTable + 
					" WHERE " + sField1 + " = '" + sMovimientoReferenciaID + "'");
			
			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",sField1,rs.getString(sField1));
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
			logger.error("ERROR: sMovimientoReferenciaID:|{}|",sMovimientoReferenciaID);

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
			
			
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return found;
	}
	
}
