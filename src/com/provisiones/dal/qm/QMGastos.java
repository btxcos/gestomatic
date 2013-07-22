
package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.types.Gasto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QMGastos
{
	static String sClassName = QMDatosActivos.class.getName();

	static String sTable = "ga_gastos_tbl";

	static String sField1  = "ga_gastos_id";
	static String sField2  = "cogrug";
	static String sField3  = "cotpga";
	static String sField4  = "cosbga";
	static String sField5  = "ptpago";
	static String sField6  = "fedeve";
	static String sField7  = "ffgtvp";
	static String sField8  = "fepaga";
	static String sField9  = "felipg";
	static String sField10 = "cosiga";
	static String sField11 = "feeesi";
	static String sField12 = "feecoi";
	static String sField13 = "feeaui";
	static String sField14 = "feepai";
	static String sField15 = "imngas";
	static String sField16 = "ycos02";
	static String sField17 = "imrgas";
	static String sField18 = "ycos04";
	static String sField19 = "imdgas";
	static String sField20 = "ycos06";
	static String sField21 = "imcost";
	static String sField22 = "ycos08";
	static String sField23 = "imogas";
	static String sField24 = "ycos10";
	static String sField25 = "imdtga";
	static String sField26 = "counmo";
	static String sField27 = "imimga";
	static String sField28 = "coimpt";
	static String sField29 = "cotneg";
	static String sField30 = "coencx";
	static String sField31 = "coofcx";
	static String sField32 = "nucone";
	static String sField33 = "nuprof";
	static String sField34 = "feagto";
	static String sField35 = "comona";
	static String sField36 = "biauto";
	static String sField37 = "feaufa";
	static String sField38 = "coterr";
	static String sField39 = "fmpagn";
	static String sField40 = "fepgpr";
	static String sField41 = "feapli";
	static String sField42 = "coapii";
	static String sField43 = "cospii";
	static String sField44 = "nuclii";

	public static boolean addGasto (Gasto NuevoGasto) 
	 
	{
		String sMethod = "addGasto";
		Statement stmt = null;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		try 
		{
			
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
				       + sField25 + ","              
				       + sField26 + ","              
				       + sField27 + ","              
				       + sField28 + ","              
				       + sField29 + ","              
				       + sField30 + ","              
				       + sField31 + ","              
				       + sField32 + ","              
				       + sField33 + ","              
				       + sField34 + ","              
				       + sField35 + ","              
				       + sField36 + ","              
				       + sField37 + ","              
				       + sField38 + ","              
				       + sField39 + ","              
				       + sField40 + ","              
				       + sField41 + ","              
				       + sField42 + ","              
				       + sField43 + ","              
				       + sField44 +                  
				       					") VALUES ('"        
				 				       + NuevoGasto.getCOGRUG() + "','"  
								       + NuevoGasto.getCOTPGA() + "','"  
								       + NuevoGasto.getCOSBGA() + "','"  
								       + NuevoGasto.getPTPAGO() + "','"  
								       + NuevoGasto.getFEDEVE() + "','"  
								       + NuevoGasto.getFFGTVP() + "','"  
								       + NuevoGasto.getFEPAGA() + "','"  
								       + NuevoGasto.getFELIPG() + "','"  
								       + NuevoGasto.getCOSIGA() + "','"  
								       + NuevoGasto.getFEEESI() + "','"  
								       + NuevoGasto.getFEECOI() + "','"  
								       + NuevoGasto.getFEEAUI() + "','"  
								       + NuevoGasto.getFEEPAI() + "','"  
								       + NuevoGasto.getIMNGAS() + "','"  
								       + NuevoGasto.getYCOS02() + "','"  
								       + NuevoGasto.getIMRGAS() + "','"  
								       + NuevoGasto.getYCOS04() + "','"  
								       + NuevoGasto.getIMDGAS() + "','"  
								       + NuevoGasto.getYCOS06() + "','"  
								       + NuevoGasto.getIMCOST() + "','"  
								       + NuevoGasto.getYCOS08() + "','"  
								       + NuevoGasto.getIMOGAS() + "','"  
								       + NuevoGasto.getYCOS10() + "','"  
								       + NuevoGasto.getIMDTGA() + "','"  
								       + NuevoGasto.getCOUNMO() + "','"  
								       + NuevoGasto.getIMIMGA() + "','"  
								       + NuevoGasto.getCOIMPT() + "','"  
								       + NuevoGasto.getCOTNEG() + "','"  
								       + NuevoGasto.getCOENCX() + "','"  
								       + NuevoGasto.getCOOFCX() + "','"  
								       + NuevoGasto.getNUCONE() + "','"  
								       + NuevoGasto.getNUPROF() + "','"  
								       + NuevoGasto.getFEAGTO() + "','"  
								       + NuevoGasto.getCOMONA() + "','"  
								       + NuevoGasto.getBIAUTO() + "','"  
								       + NuevoGasto.getFEAUFA() + "','"  
								       + NuevoGasto.getCOTERR() + "','"  
								       + NuevoGasto.getFMPAGN() + "','"  
								       + NuevoGasto.getFEPGPR() + "','"  
								       + NuevoGasto.getFEAPLI() + "','"  
								       + NuevoGasto.getCOAPII() + "','"  
								       + NuevoGasto.getCOSPII() + "','"  
								       + NuevoGasto.getNUCLII() + "' )");
		} 
		catch (SQLException ex) 
		{
	
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + NuevoGasto.getCOACES());
			
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
	public static boolean modGasto(Gasto NuevoGasto, String sGastoID)
	{
		String sMethod = "modGasto";
		Statement stmt = null;
		boolean bExit = false;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField2  + " = '"+ NuevoGasto.getCOGRUG() + "','"
					+ sField3  + " = '"+ NuevoGasto.getCOTPGA() + "','"
					+ sField4  + " = '"+ NuevoGasto.getCOSBGA() + "','"
					+ sField5  + " = '"+ NuevoGasto.getPTPAGO() + "','"
					+ sField6  + " = '"+ NuevoGasto.getFEDEVE() + "','"
					+ sField7  + " = '"+ NuevoGasto.getFFGTVP() + "','"
					+ sField8  + " = '"+ NuevoGasto.getFEPAGA() + "','"
					+ sField9  + " = '"+ NuevoGasto.getFELIPG() + "','"
					+ sField10 + " = '"+ NuevoGasto.getCOSIGA() + "','"
					+ sField11 + " = '"+ NuevoGasto.getFEEESI() + "','"
					+ sField12 + " = '"+ NuevoGasto.getFEECOI() + "','"
					+ sField13 + " = '"+ NuevoGasto.getFEEAUI() + "','"
					+ sField14 + " = '"+ NuevoGasto.getFEEPAI() + "','"
					+ sField15 + " = '"+ NuevoGasto.getIMNGAS() + "','"
					+ sField16 + " = '"+ NuevoGasto.getYCOS02() + "','"
					+ sField17 + " = '"+ NuevoGasto.getIMRGAS() + "','"
					+ sField18 + " = '"+ NuevoGasto.getYCOS04() + "','"
					+ sField19 + " = '"+ NuevoGasto.getIMDGAS() + "','"
					+ sField20 + " = '"+ NuevoGasto.getYCOS06() + "','"
					+ sField21 + " = '"+ NuevoGasto.getIMCOST() + "','"
					+ sField22 + " = '"+ NuevoGasto.getYCOS08() + "','"
					+ sField23 + " = '"+ NuevoGasto.getIMOGAS() + "','"
					+ sField24 + " = '"+ NuevoGasto.getYCOS10() + "','"
					+ sField25 + " = '"+ NuevoGasto.getIMDTGA() + "','"
					+ sField26 + " = '"+ NuevoGasto.getCOUNMO() + "','"
					+ sField27 + " = '"+ NuevoGasto.getIMIMGA() + "','"
					+ sField28 + " = '"+ NuevoGasto.getCOIMPT() + "','"
					+ sField29 + " = '"+ NuevoGasto.getCOTNEG() + "','"
					+ sField30 + " = '"+ NuevoGasto.getCOENCX() + "','"
					+ sField31 + " = '"+ NuevoGasto.getCOOFCX() + "','"
					+ sField32 + " = '"+ NuevoGasto.getNUCONE() + "','"
					+ sField33 + " = '"+ NuevoGasto.getNUPROF() + "','"
					+ sField34 + " = '"+ NuevoGasto.getFEAGTO() + "','"
					+ sField35 + " = '"+ NuevoGasto.getCOMONA() + "','"
					+ sField36 + " = '"+ NuevoGasto.getBIAUTO() + "','"
					+ sField37 + " = '"+ NuevoGasto.getFEAUFA() + "','"
					+ sField38 + " = '"+ NuevoGasto.getCOTERR() + "','"
					+ sField39 + " = '"+ NuevoGasto.getFMPAGN() + "','"
					+ sField40 + " = '"+ NuevoGasto.getFEPGPR() + "','"
					+ sField41 + " = '"+ NuevoGasto.getFEAPLI() + "','"
					+ sField42 + " = '"+ NuevoGasto.getCOAPII() + "','"
					+ sField43 + " = '"+ NuevoGasto.getCOSPII() + "','"
					+ sField44 + " = '"+ NuevoGasto.getNUCLII() + "' "+
					" WHERE "
					+ sField1 + " = '"+ sGastoID +"'");
			
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

	public static boolean delGasto(String sGastoID)
	{
		String sMethod = "delGasto";
		Statement stmt = null;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sGastoID + "' )");
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

	public static Gasto getGasto(String sGastoID)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getActivo";

		Statement stmt = null;
		ResultSet rs = null;

		String sCOACES = "";
		String sCOGRUG = "";
		String sCOTPGA = "";
		String sCOSBGA = "";
		String sPTPAGO = "";
		String sFEDEVE = "";
		String sFFGTVP = "";
		String sFEPAGA = "";
		String sFELIPG = "";
		String sCOSIGA = "";
		String sFEEESI = "";
		String sFEECOI = "";
		String sFEEAUI = "";
		String sFEEPAI = "";
		String sIMNGAS = "";
		String sYCOS02 = "";
		String sIMRGAS = "";
		String sYCOS04 = "";
		String sIMDGAS = "";
		String sYCOS06 = "";
		String sIMCOST = "";
		String sYCOS08 = "";
		String sIMOGAS = "";
		String sYCOS10 = "";
		String sIMDTGA = "";
		String sCOUNMO = "";
		String sIMIMGA = "";
		String sCOIMPT = "";
		String sCOTNEG = "";
		String sCOENCX = "";
		String sCOOFCX = "";
		String sNUCONE = "";
		String sNUPROF = "";
		String sFEAGTO = "";
		String sCOMONA = "";
		String sBIAUTO = "";
		String sFEAUFA = "";
		String sCOTERR = "";
		String sFMPAGN = "";
		String sFEPGPR = "";
		String sFEAPLI = "";
		String sCOAPII = "";
		String sCOSPII = "";
		String sNUCLII = "";

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
				       + sField25 + ","              
				       + sField26 + ","              
				       + sField27 + ","              
				       + sField28 + ","              
				       + sField29 + ","              
				       + sField30 + ","              
				       + sField31 + ","              
				       + sField32 + ","              
				       + sField33 + ","              
				       + sField34 + ","              
				       + sField35 + ","              
				       + sField36 + ","              
				       + sField37 + ","              
				       + sField38 + ","              
				       + sField39 + ","              
				       + sField40 + ","              
				       + sField41 + ","              
				       + sField42 + ","              
				       + sField43 + ","              
				       + sField44 +       
			"  FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sGastoID	+ "')");

			rs = pstmt.executeQuery();

			System.out.println("===================================================");
			System.out.println(sField1 + ": " + sGastoID);

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCOACES = "";
					sCOGRUG = rs.getString(sField2); 
					sCOTPGA = rs.getString(sField3); 
					sCOSBGA = rs.getString(sField4); 
					sPTPAGO = rs.getString(sField5); 
					sFEDEVE = rs.getString(sField6); 
					sFFGTVP = rs.getString(sField7); 
					sFEPAGA = rs.getString(sField8); 
					sFELIPG = rs.getString(sField9); 
					sCOSIGA = rs.getString(sField10);
					sFEEESI = rs.getString(sField11);
					sFEECOI = rs.getString(sField12);
					sFEEAUI = rs.getString(sField13);
					sFEEPAI = rs.getString(sField14);
					sIMNGAS = rs.getString(sField15);
					sYCOS02 = rs.getString(sField16);
					sIMRGAS = rs.getString(sField17);
					sYCOS04 = rs.getString(sField18);
					sIMDGAS = rs.getString(sField19);
					sYCOS06 = rs.getString(sField20);
					sIMCOST = rs.getString(sField21);
					sYCOS08 = rs.getString(sField22);
					sIMOGAS = rs.getString(sField23);
					sYCOS10 = rs.getString(sField24);
					sIMDTGA = rs.getString(sField25);
					sCOUNMO = rs.getString(sField26);
					sIMIMGA = rs.getString(sField27);
					sCOIMPT = rs.getString(sField28);
					sCOTNEG = rs.getString(sField29);
					sCOENCX = rs.getString(sField30);
					sCOOFCX = rs.getString(sField31);
					sNUCONE = rs.getString(sField32);
					sNUPROF = rs.getString(sField33);
					sFEAGTO = rs.getString(sField34);
					sCOMONA = rs.getString(sField35);
					sBIAUTO = rs.getString(sField36);
					sFEAUFA = rs.getString(sField37);
					sCOTERR = rs.getString(sField38);
					sFMPAGN = rs.getString(sField39);
					sFEPGPR = rs.getString(sField40);
					sFEAPLI = rs.getString(sField41);
					sCOAPII = rs.getString(sField42);
					sCOSPII = rs.getString(sField43);
					sNUCLII = rs.getString(sField44);



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
		return new Gasto(sCOACES, sCOGRUG, sCOTPGA, sCOSBGA, sPTPAGO, sFEDEVE,
				sFFGTVP, sFEPAGA, sFELIPG, sCOSIGA, sFEEESI, sFEECOI, sFEEAUI,
				sFEEPAI, sIMNGAS, sYCOS02, sIMRGAS, sYCOS04, sIMDGAS, sYCOS06,
				sIMCOST, sYCOS08, sIMOGAS, sYCOS10, sIMDTGA, sCOUNMO, sIMIMGA,
				sCOIMPT, sCOTNEG, sCOENCX, sCOOFCX, sNUCONE, sNUPROF, sFEAGTO,
				sCOMONA, sBIAUTO, sFEAUFA, sCOTERR, sFMPAGN, sFEPGPR, sFEAPLI,
				sCOAPII, sCOSPII, sNUCLII);
	}

}
