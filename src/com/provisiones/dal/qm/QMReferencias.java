package com.provisiones.dal.qm;



import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.types.ReferenciaCatastral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QMReferencias
{
	static String sClassName = QMReferencias.class.getName();

	static String sTable = "e3_referencias_tbl";

	static String sField1 = "e3_referencias_id";

	static String sField2  = "cod_codtrn";
	static String sField3  = "cod_cotdor";
	static String sField4  = "idprov";
	static String sField5  = "cod_coacci";
	static String sField6  = "coengp";
	static String sField7  = "nurcat";
	static String sField8  = "cod_bitc16";
	static String sField9  = "tircat";
	static String sField10 = "cod_bitc17";
	static String sField11 = "enemis";
	static String sField12 = "cotexa";
	static String sField13 = "cod_bitc09";
	static String sField14 = "obtexc";
	static String sField15 = "obdeer";

	public static boolean addReferenciaCatastral(ReferenciaCatastral NuevaReferenciaCatastral)

	{
		String sMethod = "addReferenciaCatastral";
		Statement stmt = null;
		Connection conn = null;

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
				       + NuevaReferenciaCatastral.getCODTRN() + "','" 
				       + NuevaReferenciaCatastral.getCOTDOR() + "','"
				       + NuevaReferenciaCatastral.getIDPROV() + "','"
				       + NuevaReferenciaCatastral.getCOACCI() + "','"
				       + NuevaReferenciaCatastral.getCOENGP() + "','"
				       + NuevaReferenciaCatastral.getNURCAT() + "','"
				       + NuevaReferenciaCatastral.getBITC16() + "','"
				       + NuevaReferenciaCatastral.getTIRCAT() + "','"
				       + NuevaReferenciaCatastral.getBITC17() + "','"
				       + NuevaReferenciaCatastral.getENEMIS() + "','"
				       + NuevaReferenciaCatastral.getCOTEXA() + "','"
				       + NuevaReferenciaCatastral.getBITC09() + "','"
				       + NuevaReferenciaCatastral.getOBTEXC() + "','"
				       + NuevaReferenciaCatastral.getOBDEER() + "' )");
		} 
		catch (SQLException ex) 
		{


			//System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRAP: " + NuevaComunidad.getCOGRAP());
			
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + NuevaReferenciaCatastral.getCOACES());
			
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());			
		} 
		finally
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return true;
	}
	public static boolean modReferenciaCatastral(ReferenciaCatastral NuevaReferenciaCatastral, String sReferenciaCatastralID)
	{
		String sMethod = "modReferenciaCatastral";
		Statement stmt = null;
		boolean bExit = false;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField2  + " = '"+ NuevaReferenciaCatastral.getCODTRN() + "','"
					+ sField3  + " = '"+ NuevaReferenciaCatastral.getCOTDOR() + "','"
					+ sField4  + " = '"+ NuevaReferenciaCatastral.getIDPROV() + "','"
					+ sField5  + " = '"+ NuevaReferenciaCatastral.getCOACCI() + "','"
					+ sField6  + " = '"+ NuevaReferenciaCatastral.getCOENGP() + "','"
					+ sField7  + " = '"+ NuevaReferenciaCatastral.getNURCAT() + "','"
					+ sField8  + " = '"+ NuevaReferenciaCatastral.getBITC16() + "','"
					+ sField9  + " = '"+ NuevaReferenciaCatastral.getTIRCAT() + "','"
					+ sField10 + " = '"+ NuevaReferenciaCatastral.getBITC17() + "','"
					+ sField11 + " = '"+ NuevaReferenciaCatastral.getENEMIS() + "','"
					+ sField12 + " = '"+ NuevaReferenciaCatastral.getCOTEXA() + "','"
					+ sField13 + " = '"+ NuevaReferenciaCatastral.getBITC09() + "','"
					+ sField14 + " = '"+ NuevaReferenciaCatastral.getOBTEXC() + "','"
					+ sField15 + " = '"+ NuevaReferenciaCatastral.getOBDEER() + 
					"' "+
					" WHERE "
					+ sField1 + " = '"+ sReferenciaCatastralID +"'");
			
		} 
		catch (SQLException ex) 
		{

			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());
		} 
		finally 
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
			bExit = true;
		}
		ConnectionManager.CloseDBConnection(conn);
		return bExit;
	}

	public static boolean delReferenciaCatastral(String sReferenciaCatastralID)
	{
		String sMethod = "delReferenciaCatastral";
		Statement stmt = null;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sReferenciaCatastralID + "' )");
		} 
		catch (SQLException ex) 
		{

			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());
		} 
		finally 
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return true;
	}

	public static ReferenciaCatastral getReferenciaCatastral(String sReferenciaCatastralID)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getReferenciaCatastral";

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
					" WHERE (" + sField1 + " = '" + sReferenciaCatastralID	+ "')");

			rs = pstmt.executeQuery();

			System.out.println("===================================================");
			System.out.println(sField1 + ": " + sReferenciaCatastralID);

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCOACES = "";
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


					//System.out.println(sField2 + ": " + sApplication);
					//System.out.println(sField3 + ": " + sContactCode);
					//System.out.println(sField4 + ": " + sProjectCode);
					//System.out.println("===================================================");

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
		return new ReferenciaCatastral(sCODTRN, sCOTDOR, sIDPROV, sCOACCI,
				sCOENGP, sCOACES, sNURCAT, sBITC16, sTIRCAT, sBITC17, sENEMIS,
				sCOTEXA, sBITC09, sOBTEXC, sOBDEER);
	}

}
