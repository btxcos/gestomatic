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

public class QMReferencias
{
	static String sClassName = QMReferencias.class.getName();

	static String sTable = "e3_referencias_tbl";

	static String sField1  = "cod_codtrn";
	static String sField2  = "cod_cotdor";
	static String sField3  = "idprov";    
	static String sField4  = "cod_coacci";
	static String sField5  = "coengp";    
	static String sField6  = "nurcat_id";    
	static String sField7  = "cod_bitc16";
	static String sField8  = "tircat";    
	static String sField9  = "cod_bitc17";
	static String sField10 = "enemis";    
	static String sField11 = "cotexa";    
	static String sField12 = "cod_bitc09";
	static String sField13 = "obtexc";    
	static String sField14 = "obdeer";
	
	static String sField15 = "cod_validado";


	public static boolean addReferenciaCatastral(ReferenciaCatastral NuevaReferenciaCatastral)

	{
		String sMethod = "addReferenciaCatastral";
		Statement stmt = null;
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

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
				       + NuevaReferenciaCatastral.getOBDEER() + "','"
				       + ValoresDefecto.DEF_VALIDADO + "' )");
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
	public static boolean modReferenciaCatastral(ReferenciaCatastral NuevaReferenciaCatastral, String sReferenciaCatastralID, String sValidado)
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
					+ sField1  + " = '"+ NuevaReferenciaCatastral.getCODTRN() + "','"
					+ sField2  + " = '"+ NuevaReferenciaCatastral.getCOTDOR() + "','"
					+ sField3  + " = '"+ NuevaReferenciaCatastral.getIDPROV() + "','"
					+ sField4  + " = '"+ NuevaReferenciaCatastral.getCOACCI() + "','"
					+ sField5  + " = '"+ NuevaReferenciaCatastral.getCOENGP() + "','"
					//+ sField6  + " = '"+ NuevaReferenciaCatastral.getNURCAT() + "','"
					+ sField7  + " = '"+ NuevaReferenciaCatastral.getBITC16() + "','"
					+ sField8  + " = '"+ NuevaReferenciaCatastral.getTIRCAT() + "','"
					+ sField9  + " = '"+ NuevaReferenciaCatastral.getBITC17() + "','"
					+ sField10 + " = '"+ NuevaReferenciaCatastral.getENEMIS() + "','"
					+ sField11 + " = '"+ NuevaReferenciaCatastral.getCOTEXA() + "','"
					+ sField12 + " = '"+ NuevaReferenciaCatastral.getBITC09() + "','"
					+ sField13 + " = '"+ NuevaReferenciaCatastral.getOBTEXC() + "','"
					+ sField14 + " = '"+ NuevaReferenciaCatastral.getOBDEER() + "','"
					+ sField15 + " = '"+ sValidado + 
					"' "+
					" WHERE "
					+ sField6 + " = '"+ sReferenciaCatastralID +"'");
			
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
					" WHERE (" + sField6 + " = '" + sReferenciaCatastralID + "' )");
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
				       + sField1  + ","
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
					" WHERE (" + sField6 + " = '" + sReferenciaCatastralID	+ "')");

			rs = pstmt.executeQuery();

			System.out.println("===================================================");
			System.out.println(sField1 + ": " + sReferenciaCatastralID);

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCODTRN = rs.getString(sField1); 
  					sCOTDOR = rs.getString(sField2); 
  					sIDPROV = rs.getString(sField3); 
  					sCOACCI = rs.getString(sField4); 
  					sCOENGP = rs.getString(sField5); 
  					sNURCAT = rs.getString(sField6); 
  					sBITC16 = rs.getString(sField7); 
  					sTIRCAT = rs.getString(sField8); 
  					sBITC17 = rs.getString(sField9);
  					sENEMIS = rs.getString(sField10);
  					sCOTEXA = rs.getString(sField11);
  					sBITC09 = rs.getString(sField12);
  					sOBTEXC = rs.getString(sField13);
  					sOBDEER = rs.getString(sField14);


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
