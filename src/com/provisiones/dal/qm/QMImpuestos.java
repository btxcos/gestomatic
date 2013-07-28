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
	static String sField7  = "cod_coaces";
	static String sField8  = "nurcat";    
	static String sField9  = "cod_cogruc";
	static String sField10 = "cod_cotaca";
	static String sField11 = "cod_cosbac";
	static String sField12 = "cod_bitc18";
	static String sField13 = "feprre";    
	static String sField14 = "cod_bitc19";
	static String sField15 = "ferere";    
	static String sField16 = "cod_bitc20";
	static String sField17 = "fedein";    
	static String sField18 = "cod_bitc21";
	static String sField19 = "cod_bisode";
	static String sField20 = "cod_bitc22";
	static String sField21 = "cod_bireso";
	static String sField22 = "cotexa";    
	static String sField23 = "cod_bitc09";
	static String sField24 = "obtexc";    
	static String sField25 = "obdeer";		

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
				       + sField24 + "," 
				       + sField25 +  
				       ") VALUES ('" 
				       + NuevoImpuestoRecurso.getCODTRN() + "','" 
				       + NuevoImpuestoRecurso.getCOTDOR() + "','"
				       + NuevoImpuestoRecurso.getIDPROV() + "','"
				       + NuevoImpuestoRecurso.getCOACCI() + "','"
				       + NuevoImpuestoRecurso.getCOENGP() + "','"
				       + NuevoImpuestoRecurso.getCOACES() + "','"
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
					+ sField7  + " = '"+ NuevoImpuestoRecurso.getCOACES() + "','"
					+ sField8  + " = '"+ NuevoImpuestoRecurso.getNURCAT() + "','"
					+ sField9  + " = '"+ NuevoImpuestoRecurso.getCOGRUC() + "','"
					+ sField10 + " = '"+ NuevoImpuestoRecurso.getCOTACA() + "','"
					+ sField11 + " = '"+ NuevoImpuestoRecurso.getCOSBAC() + "','"
					+ sField12 + " = '"+ NuevoImpuestoRecurso.getBITC18() + "','"
					+ sField13 + " = '"+ NuevoImpuestoRecurso.getFEPRRE() + "','"
					+ sField14 + " = '"+ NuevoImpuestoRecurso.getBITC19() + "','"
					+ sField15 + " = '"+ NuevoImpuestoRecurso.getFERERE() + "','"
					+ sField16 + " = '"+ NuevoImpuestoRecurso.getBITC20() + "','"
					+ sField17 + " = '"+ NuevoImpuestoRecurso.getFEDEIN() + "','"
					+ sField18 + " = '"+ NuevoImpuestoRecurso.getBITC21() + "','"
					+ sField19 + " = '"+ NuevoImpuestoRecurso.getBISODE() + "','"
					+ sField20 + " = '"+ NuevoImpuestoRecurso.getBITC22() + "','"
					+ sField21 + " = '"+ NuevoImpuestoRecurso.getBIRESO() + "','"
					+ sField22 + " = '"+ NuevoImpuestoRecurso.getCOTEXA() + "','"
					+ sField23 + " = '"+ NuevoImpuestoRecurso.getBITC09() + "','"
					+ sField24 + " = '"+ NuevoImpuestoRecurso.getOBTEXC() + "','"
					+ sField25 + " = '"+ NuevoImpuestoRecurso.getOBDEER() +
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
				       + sField24 + "," 
				       + sField25 +             
       
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

					sCODTRN = rs.getString(sField2); 
					sCOTDOR = rs.getString(sField3); 
					sIDPROV = rs.getString(sField4); 
					sCOACCI = rs.getString(sField5); 
					sCOENGP = rs.getString(sField6); 
					sCOACES = rs.getString(sField7); 
					sNURCAT = rs.getString(sField8); 
					sCOGRUC = rs.getString(sField9); 
					sCOTACA = rs.getString(sField10);
					sCOSBAC = rs.getString(sField11);
					sBITC18 = rs.getString(sField12);
					sFEPRRE = rs.getString(sField13);
					sBITC19 = rs.getString(sField14);
					sFERERE = rs.getString(sField15);
					sBITC20 = rs.getString(sField16);
					sFEDEIN = rs.getString(sField17);
					sBITC21 = rs.getString(sField18);
					sBISODE = rs.getString(sField19);
					sBITC22 = rs.getString(sField20);
					sBIRESO = rs.getString(sField21);
					sCOTEXA = rs.getString(sField22);
					sBITC09 = rs.getString(sField23);
					sOBTEXC = rs.getString(sField24);
					sOBDEER = rs.getString(sField25);

					
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

	public static String getImpuestoRecursoID(ImpuestoRecurso impuesto)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getImpuestoRecursoID";

		Statement stmt = null;
		ResultSet rs = null;

		String sImpuestoID = "";

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					+ sField1 + 
					"  FROM " + sTable + 
						" WHERE ("
					       + sField2  +" = '" + impuesto.getCODTRN() + "' AND "
					       + sField3  +" = '" + impuesto.getCOTDOR() + "' AND "
					       + sField4  +" = '" + impuesto.getIDPROV() + "' AND "
					       + sField5  +" = '" + impuesto.getCOACCI() + "' AND "
					       + sField6  +" = '" + impuesto.getCOENGP() + "' AND "
					       + sField7  +" = '" + impuesto.getCOACES() + "' AND "
					       + sField8  +" = '" + impuesto.getNURCAT() + "' AND "
					       + sField9  +" = '" + impuesto.getCOGRUC() + "' AND "
					       + sField10 +" = '" + impuesto.getCOTACA() + "' AND "
					       + sField11 +" = '" + impuesto.getCOSBAC() + "' AND "
					       + sField12 +" = '" + impuesto.getBITC18() + "' AND "
					       + sField13 +" = '" + impuesto.getFEPRRE() + "' AND "
					       + sField14 +" = '" + impuesto.getBITC19() + "' AND "
					       + sField15 +" = '" + impuesto.getFERERE() + "' AND "
					       + sField16 +" = '" + impuesto.getBITC20() + "' AND "
					       + sField17 +" = '" + impuesto.getFEDEIN() + "' AND "
					       + sField18 +" = '" + impuesto.getBITC21() + "' AND "
					       + sField19 +" = '" + impuesto.getBISODE() + "' AND "
					       + sField20 +" = '" + impuesto.getBITC22() + "' AND "
					       + sField21 +" = '" + impuesto.getBIRESO() + "' AND "
					       + sField22 +" = '" + impuesto.getCOTEXA() + "' AND "
					       + sField23 +" = '" + impuesto.getBITC09() + "' AND "
					       + sField24 +" = '" + impuesto.getOBTEXC() + "' AND "
					       + sField25 +" = '" + impuesto.getOBDEER() + "' )"); 

			rs = pstmt.executeQuery();

			//System.out.println("===================================================");
			//System.out.println(sField1 + ": " + sCuotaID);

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sImpuestoID = rs.getString(sField1);
					System.out.println(sField1 + ": " + sImpuestoID);
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
		return sImpuestoID;
	}
}
