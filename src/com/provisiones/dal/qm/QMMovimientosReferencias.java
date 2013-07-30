package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.types.MovimientoReferenciaCatastral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QMMovimientosReferencias
{
	static String sClassName = QMMovimientosReferencias.class.getName();

	static String sTable = "e3_movimientos_tbl";

	static String sField1  = "e3_movimiento_id";
	static String sField2  = "cod_codtrn";  
	static String sField3  = "cod_cotdor";  
	static String sField4  = "idprov";      
	static String sField5  = "cod_coacci";  
	static String sField6  = "coengp";       
	static String sField7  = "nurcat_id";   
	static String sField8  = "cod_bitc16";  
	static String sField9  = "tircat";      
	static String sField10 = "cod_bitc17";  
	static String sField11 = "enemis";      
	static String sField12 = "cotexa";      
	static String sField13 = "cod_bitc09";  
	static String sField14 = "obtexc";      
	static String sField15 = "obdeer";      



	public static boolean addMovimientoReferenciaCatastral(MovimientoReferenciaCatastral NuevoMovimientoReferenciaCatastral)

	{
		String sMethod = "addMovimientoReferenciaCatastral";
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		conn = ConnectionManager.OpenDBConnection();

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
				       + sField15 +  
				       ") VALUES ('" 
				       + NuevoMovimientoReferenciaCatastral.getCODTRN() + "','" 
				       + NuevoMovimientoReferenciaCatastral.getCOTDOR() + "','"
				       + NuevoMovimientoReferenciaCatastral.getIDPROV() + "','"
				       + NuevoMovimientoReferenciaCatastral.getCOACCI() + "','"
				       + NuevoMovimientoReferenciaCatastral.getCOENGP() + "','"
				       + NuevoMovimientoReferenciaCatastral.getNURCAT() + "','"
				       + NuevoMovimientoReferenciaCatastral.getBITC16() + "','"
				       + NuevoMovimientoReferenciaCatastral.getTIRCAT() + "','"
				       + NuevoMovimientoReferenciaCatastral.getBITC17() + "','"
				       + NuevoMovimientoReferenciaCatastral.getENEMIS() + "','"
				       + NuevoMovimientoReferenciaCatastral.getCOTEXA() + "','"
				       + NuevoMovimientoReferenciaCatastral.getBITC09() + "','"
				       + NuevoMovimientoReferenciaCatastral.getOBTEXC() + "','"
				       + NuevoMovimientoReferenciaCatastral.getOBDEER() + "' )");
		} 
		catch (SQLException ex) 
		{


			//System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRAP: " + NuevaComunidad.getCOGRAP());
			
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + NuevoMovimientoReferenciaCatastral.getNURCAT());
			
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());
			
			bSalida = false;
		} 
		finally
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	public static boolean modMovimientoReferenciaCatastral(MovimientoReferenciaCatastral NuevoMovimientoReferenciaCatastral, String sMovimientoReferenciaCatastralID)
	{
		String sMethod = "modMovimientoReferenciaCatastral";
		Statement stmt = null;
		boolean bSalida = true;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
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
					+ sField7  + " = '"+ NuevoMovimientoReferenciaCatastral.getNURCAT() + "', "
					+ sField8  + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC16() + "', "
					+ sField9  + " = '"+ NuevoMovimientoReferenciaCatastral.getTIRCAT() + "', "
					+ sField10 + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC17() + "', "
					+ sField11 + " = '"+ NuevoMovimientoReferenciaCatastral.getENEMIS() + "', "
					+ sField12 + " = '"+ NuevoMovimientoReferenciaCatastral.getCOTEXA() + "', "
					+ sField13 + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC09() + "', "
					+ sField14 + " = '"+ NuevoMovimientoReferenciaCatastral.getOBTEXC() + "', "
					+ sField15 + " = '"+ NuevoMovimientoReferenciaCatastral.getOBDEER() + 
					"' "+
					" WHERE "
					+ sField1 + " = '"+ sMovimientoReferenciaCatastralID +"'");
			
		} 
		catch (SQLException ex) 
		{

			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());

			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt, sClassName, sMethod);

		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static boolean delMovimientoReferenciaCatastral(String sMovimientoReferenciaCatastralID)
	{
		String sMethod = "delMovimientoReferenciaCatastral";
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sMovimientoReferenciaCatastralID + "' )");
		} 
		catch (SQLException ex) 
		{

			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static MovimientoReferenciaCatastral getMovimientoReferenciaCatastral(String sMovimientoReferenciaCatastralID)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getMovimientoReferenciaCatastral";

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


		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();

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
				       + sField15 +             
         
       
			"  FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sMovimientoReferenciaCatastralID	+ "')");

			rs = pstmt.executeQuery();

			System.out.println("===================================================");
			System.out.println(sField1 + ": " + sMovimientoReferenciaCatastralID);

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
  					sNURCAT = rs.getString(sField7); 
  					sBITC16 = rs.getString(sField8); 
  					sTIRCAT = rs.getString(sField9); 
  					sBITC17 = rs.getString(sField10);
  					sENEMIS = rs.getString(sField11);
  					sCOTEXA = rs.getString(sField12);
  					sBITC09 = rs.getString(sField13);
  					sOBTEXC = rs.getString(sField14);
  					sOBDEER = rs.getString(sField15);

				}
			}
			if (found == false) 
			{
				System.out.println("No Information Found");
			}

		} 
		catch (SQLException ex) 
		{

			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs,sClassName,sMethod);
			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return new MovimientoReferenciaCatastral(sCODTRN, sCOTDOR, sIDPROV, sCOACCI,
				sCOENGP, sCOACES, sNURCAT, sBITC16, sTIRCAT, sBITC17, sENEMIS,
				sCOTEXA, sBITC09, sOBTEXC, sOBDEER);
	}


	public static boolean verificarMovimientoReferenciaCatastral(String sCodReferencia)
	{
		String sMethod = "verificarMovimientoReferenciaCatastral";

		Statement stmt = null;
		ResultSet rs = null;

		boolean bSalida = true;

		PreparedStatement pstmt = null;
		boolean found = false;
	

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField15 + "  FROM " + sTable + 
					" WHERE (" + sField6 + " = '" + sCodReferencia + "')");

			rs = pstmt.executeQuery();
			
			found = (rs != null);
			
			if (found == false) 
			{
 				System.out.println("No Information Found");
			}

		} 
		catch (SQLException ex) 
		{

			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());
			
			bSalida = false;
		} 
		finally 
		{
			Utils.closeResultSet(rs,sClassName,sMethod);
			Utils.closeStatement(stmt, sClassName, sMethod);
		}

		ConnectionManager.CloseDBConnection(conn);
		return (found && bSalida);
	}
	
}
