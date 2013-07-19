package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.types.ImpuestoRecurso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QMImpuestos 
{
	static String sClassName = QMImpuestos.class.getName();

	static String sTable = "e4_impuestos_tbl";

	static String sField1 = "e4_impuestos_id";

	static String sField2  = "cod_codtrn";
	static String sField3  = "cod_cotdor";
	static String sField4  = "idprov";
	static String sField5  = "cod_coacci";
	static String sField6  = "coengp";
	static String sField7  = "nurcat";
	static String sField8  = "cod_cogruc";
	static String sField9  = "cod_cotaca";
	static String sField10 = "cod_cosbac";
	static String sField11 = "cod_bitc18";
	static String sField12 = "feprre";
	static String sField13 = "cod_bitc19";
	static String sField14 = "ferere";
	static String sField15 = "cod_bitc20";
	static String sField16 = "fedein";
	static String sField17 = "cod_bitc21";
	static String sField18 = "cod_bisode";
	static String sField19 = "cod_bitc22";
	static String sField20 = "cod_bireso";
	static String sField21 = "cotexa";
	static String sField22 = "cod_bitc09";
	static String sField23 = "obtexc";
	static String sField24 = "obdeer";

	public static boolean addImpuesto(ImpuestoRecurso NuevoImpuestoRecurso)

	{
		String sMethod = "addImpuesto";
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
				       + sField15 + ","              
				       + sField16 + ","              
				       + sField17 + ","              
				       + sField18 + ","              
				       + sField19 + ","              
				       + sField20 + ","              
				       + sField21 + ","              
				       + sField22 + ","              
				       + sField23 + ","              
				       + sField24 +  
				       ") VALUES ('" 
				       + NuevoImpuestoRecurso.getCODTRN() + "','" 
				       + NuevoImpuestoRecurso.getCOTDOR() + "','"
				       + NuevoImpuestoRecurso.getIDPROV() + "','"
				       + NuevoImpuestoRecurso.getCOACCI() + "','"
				       + NuevoImpuestoRecurso.getCOENGP() + "','"
				       + NuevoImpuestoRecurso.getNURCAT() + "','"
				       + NuevoImpuestoRecurso.getCOGRUC() + "','"
				       + NuevoImpuestoRecurso.getCOTACA() + "','"
				       + NuevoImpuestoRecurso.getCOSBAC() + "','"
				       + NuevoImpuestoRecurso.getBITC18() + "','"
				       + NuevoImpuestoRecurso.getFEPRRE() + "','"
				       + NuevoImpuestoRecurso.getBITC19() + "','"
				       + NuevoImpuestoRecurso.getFERERE() + "','"
				       + NuevoImpuestoRecurso.getBITC20() + "','"
				       + NuevoImpuestoRecurso.getFEDEIN() + "','"
				       + NuevoImpuestoRecurso.getBITC21() + "','"
				       + NuevoImpuestoRecurso.getBISODE() + "','"
				       + NuevoImpuestoRecurso.getBITC22() + "','"
				       + NuevoImpuestoRecurso.getBIRESO() + "','"
				       + NuevoImpuestoRecurso.getCOTEXA() + "','"
				       + NuevoImpuestoRecurso.getBITC09() + "','"
				       + NuevoImpuestoRecurso.getOBTEXC() + "','"
				       + NuevoImpuestoRecurso.getOBDEER() + "' )");
		} 
		catch (SQLException ex) 
		{


			//System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRAP: " + NuevaComunidad.getCOGRAP());
			
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + NuevoImpuestoRecurso.getCOACES());
			
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
	public static boolean modImpuestoRecurso(ImpuestoRecurso NuevoImpuestoRecurso, String sImpuestoRecursoID)
	{
		String sMethod = "modImpuestoRecurso";
		Statement stmt = null;
		boolean bExit = false;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField2  + " = '"+ NuevoImpuestoRecurso.getCODTRN() + "','"
					+ sField3  + " = '"+ NuevoImpuestoRecurso.getCOTDOR() + "','"
					+ sField4  + " = '"+ NuevoImpuestoRecurso.getIDPROV() + "','"
					+ sField5  + " = '"+ NuevoImpuestoRecurso.getCOACCI() + "','"
					+ sField6  + " = '"+ NuevoImpuestoRecurso.getCOENGP() + "','"
					+ sField7  + " = '"+ NuevoImpuestoRecurso.getNURCAT() + "','"
					+ sField8  + " = '"+ NuevoImpuestoRecurso.getCOGRUC() + "','"
					+ sField9  + " = '"+ NuevoImpuestoRecurso.getCOTACA() + "','"
					+ sField10 + " = '"+ NuevoImpuestoRecurso.getCOSBAC() + "','"
					+ sField11 + " = '"+ NuevoImpuestoRecurso.getBITC18() + "','"
					+ sField12 + " = '"+ NuevoImpuestoRecurso.getFEPRRE() + "','"
					+ sField13 + " = '"+ NuevoImpuestoRecurso.getBITC19() + "','"
					+ sField14 + " = '"+ NuevoImpuestoRecurso.getFERERE() + "','"
					+ sField15 + " = '"+ NuevoImpuestoRecurso.getBITC20() + "','"
					+ sField16 + " = '"+ NuevoImpuestoRecurso.getFEDEIN() + "','"
					+ sField17 + " = '"+ NuevoImpuestoRecurso.getBITC21() + "','"
					+ sField18 + " = '"+ NuevoImpuestoRecurso.getBISODE() + "','"
					+ sField19 + " = '"+ NuevoImpuestoRecurso.getBITC22() + "','"
					+ sField20 + " = '"+ NuevoImpuestoRecurso.getBIRESO() + "','"
					+ sField21 + " = '"+ NuevoImpuestoRecurso.getCOTEXA() + "','"
					+ sField22 + " = '"+ NuevoImpuestoRecurso.getBITC09() + "','"
					+ sField23 + " = '"+ NuevoImpuestoRecurso.getOBTEXC() + "','"
					+ sField24 + " = '"+ NuevoImpuestoRecurso.getOBDEER() +
					"' "+
					" WHERE "
					+ sField1 + " = '"+ sImpuestoRecursoID +"'");
			
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

	public static boolean delImpuestoRecurso(String sImpuestoRecursoID)
	{
		String sMethod = "delImpuestoRecurso";
		Statement stmt = null;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sImpuestoRecursoID + "' )");
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

	public static ImpuestoRecurso getImpuestoRecurso(String sImpuestoRecursoID)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getImpuestoRecurso";

		Statement stmt = null;
		ResultSet rs = null;

		String sCODTRN = "";
		String sCOTDOR = "";
		String sIDPROV = "";
		String sCOACCI = "";
		String sCOENGP = "";
		String sCOACES = "";
		String sNURCAT = "";
		String sCOGRUC = "";
		String sCOTACA = "";
		String sCOSBAC = "";
		String sBITC18 = "";
		String sFEPRRE = "";
		String sBITC19 = "";
		String sFERERE = "";
		String sBITC20 = "";
		String sFEDEIN = "";
		String sBITC21 = "";
		String sBISODE = "";
		String sBITC22 = "";
		String sBIRESO = "";
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
				       + sField15 + ","              
				       + sField16 + ","              
				       + sField17 + ","              
				       + sField18 + ","              
				       + sField19 + ","              
				       + sField20 + ","              
				       + sField21 + ","              
				       + sField22 + ","              
				       + sField23 + ","              
				       + sField24 +             
       
			"  FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sImpuestoRecursoID	+ "')");

			rs = pstmt.executeQuery();

			System.out.println("===================================================");
			System.out.println(sField1 + ": " + sImpuestoRecursoID);

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
					sCOGRUC = rs.getString(sField8);
					sCOTACA = rs.getString(sField9);
					sCOSBAC = rs.getString(sField10);
					sBITC18 = rs.getString(sField11);
					sFEPRRE = rs.getString(sField12);
					sBITC19 = rs.getString(sField13);
					sFERERE = rs.getString(sField14);
					sBITC20 = rs.getString(sField15);
					sFEDEIN = rs.getString(sField16);
					sBITC21 = rs.getString(sField17);
					sBISODE = rs.getString(sField18);
					sBITC22 = rs.getString(sField19);
					sBIRESO = rs.getString(sField20);
					sCOTEXA = rs.getString(sField21);
					sBITC09 = rs.getString(sField22);
					sOBTEXC = rs.getString(sField23);
					sOBDEER = rs.getString(sField24);


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
		return new ImpuestoRecurso(sCODTRN, sCOTDOR, sIDPROV, sCOACCI, sCOENGP,
				sCOACES, sNURCAT, sCOGRUC, sCOTACA, sCOSBAC, sBITC18, sFEPRRE,
				sBITC19, sFERERE, sBITC20, sFEDEIN, sBITC21, sBISODE, sBITC22,
				sBIRESO, sCOTEXA, sBITC09, sOBTEXC, sOBDEER);
	}

}
