package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.types.Cuota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QMCuotas
{
	static String sClassName = QMCuotas.class.getName();

	static String sTable = "e2_cuotas_tbl";

	static String sField1 = "e2_cuotas_id";

	static String sField2  = "cod_codtrn";
	static String sField3  = "cod_cotdor";
	static String sField4  = "idprov";
	static String sField5  = "cod_coacci";
	static String sField6  = "cod_cocldo";
	static String sField7  = "nudcom";
	static String sField8  = "coengp";
	static String sField9  = "cod_coaces";
	static String sField10 = "cogrug";     
	static String sField11 = "cotaca";     
	static String sField12 = "cod_cosbac";     
	static String sField13 = "cod_bitc11"; 
	static String sField14 = "fipago";     
	static String sField15 = "cod_bitc12"; 
	static String sField16 = "ffpago";     
	static String sField17 = "cod_bitc13"; 
	static String sField18 = "imcuco";     
	static String sField19 = "cod_bitc14"; 
	static String sField20 = "faacta";     
	static String sField21 = "cod_bitc15"; 
	static String sField22 = "ptpago";     
	static String sField23 = "cod_bitc09"; 
	static String sField24 = "obtexc";     
	static String sField25 = "obdeer";     

	public static boolean addCuota(Cuota NuevaCuota)

	{
		String sMethod = "addCuota";
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
				       + NuevaCuota.getCODTRN() + "','" 
				       + NuevaCuota.getCOTDOR() + "','"
				       + NuevaCuota.getIDPROV() + "','"
				       + NuevaCuota.getCOACCI() + "','"
				       + NuevaCuota.getCOCLDO() + "','"
				       + NuevaCuota.getNUDCOM() + "','"
				       + NuevaCuota.getCOENGP() + "','"
				       + NuevaCuota.getCOACES() + "','"
				       + NuevaCuota.getCOGRUG() + "','"
				       + NuevaCuota.getCOTACA() + "','"
				       + NuevaCuota.getCOSBAC() + "','"
				       + NuevaCuota.getBITC11() + "','"
				       + NuevaCuota.getFIPAGO() + "','"
				       + NuevaCuota.getBITC12() + "','"
				       + NuevaCuota.getFFPAGO() + "','"
				       + NuevaCuota.getBITC13() + "','"
				       + NuevaCuota.getIMCUCO() + "','"
				       + NuevaCuota.getBITC14() + "','"
				       + NuevaCuota.getFAACTA() + "','"
				       + NuevaCuota.getBITC15() + "','"
				       + NuevaCuota.getPTPAGO() + "','"
				       + NuevaCuota.getBITC09() + "','"
				       + NuevaCuota.getOBTEXC() + "','"
				       + NuevaCuota.getOBDEER() + "' )");
		} 
		catch (SQLException ex) 
		{


			//System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRAP: " + NuevaComunidad.getCOGRAP());
			
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + NuevaCuota.getCOACES());
			
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
	public static boolean modCuota(Cuota NuevaCuota, String sCuotaID)
	{
		String sMethod = "modCuota";
		Statement stmt = null;
		boolean bExit = false;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField2  + " = '"+ NuevaCuota.getCODTRN() + "','"
					+ sField3  + " = '"+ NuevaCuota.getCOTDOR() + "','"
					+ sField4  + " = '"+ NuevaCuota.getIDPROV() + "','"
					+ sField5  + " = '"+ NuevaCuota.getCOACCI() + "','"
					+ sField6  + " = '"+ NuevaCuota.getCOCLDO() + "','"
					+ sField7  + " = '"+ NuevaCuota.getNUDCOM() + "','"
					+ sField8  + " = '"+ NuevaCuota.getCOENGP() + "','"
					+ sField9  + " = '"+ NuevaCuota.getCOACES() + "','"
					+ sField10 + " = '"+ NuevaCuota.getCOGRUG() + "','"
					+ sField11 + " = '"+ NuevaCuota.getCOTACA() + "','"
					+ sField12 + " = '"+ NuevaCuota.getCOSBAC() + "','"
					+ sField13 + " = '"+ NuevaCuota.getBITC11() + "','"
					+ sField14 + " = '"+ NuevaCuota.getFIPAGO() + "','"
					+ sField15 + " = '"+ NuevaCuota.getBITC12() + "','"
					+ sField16 + " = '"+ NuevaCuota.getFFPAGO() + "','"
					+ sField17 + " = '"+ NuevaCuota.getBITC13() + "','"
					+ sField18 + " = '"+ NuevaCuota.getIMCUCO() + "','"
					+ sField19 + " = '"+ NuevaCuota.getBITC14() + "','"
					+ sField20 + " = '"+ NuevaCuota.getFAACTA() + "','"
					+ sField21 + " = '"+ NuevaCuota.getBITC15() + "','"
					+ sField22 + " = '"+ NuevaCuota.getPTPAGO() + "','"
					+ sField23 + " = '"+ NuevaCuota.getBITC09() + "','"
					+ sField24 + " = '"+ NuevaCuota.getOBTEXC() + "','"
					+ sField25 + " = '"+ NuevaCuota.getOBDEER() +
					"' "+
					" WHERE "
					+ sField1 + " = '"+ sCuotaID +"'");
			
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

	public static boolean delCuota(String sCuotaID)
	{
		String sMethod = "delCuota";
		Statement stmt = null;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sCuotaID + "' )");
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

	public static Cuota getCuota(String sCuotaID)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getCuota";

		Statement stmt = null;
		ResultSet rs = null;

		String sCODTRN = "";
		String sCOTDOR = "";
		String sIDPROV = "";
		String sCOACCI = "";
		String sCOCLDO = "";
		String sNUDCOM = "";
		String sCOENGP = "";
		String sCOACES = "";
		String sCOGRUG = "";
		String sCOTACA = "";
		String sCOSBAC = "";
		String sBITC11 = "";
		String sFIPAGO = "";
		String sBITC12 = "";
		String sFFPAGO = "";
		String sBITC13 = "";
		String sIMCUCO = "";
		String sBITC14 = "";
		String sFAACTA = "";
		String sBITC15 = "";
		String sPTPAGO = "";
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
					" WHERE (" + sField1 + " = '" + sCuotaID	+ "')");

			rs = pstmt.executeQuery();

			System.out.println("===================================================");
			System.out.println(sField1 + ": " + sCuotaID);

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCODTRN = rs.getString(sField2);
					sCOTDOR = rs.getString(sField3);
					sIDPROV = rs.getString(sField4);
					sCOACCI = rs.getString(sField5);
					sCOCLDO = rs.getString(sField6);
					sNUDCOM = rs.getString(sField7);
					sCOENGP = rs.getString(sField8);
					sCOACES = rs.getString(sField9); 
					sCOGRUG = rs.getString(sField10);
					sCOTACA = rs.getString(sField11);
					sCOSBAC = rs.getString(sField12);
					sBITC11 = rs.getString(sField13);
					sFIPAGO = rs.getString(sField14);
					sBITC12 = rs.getString(sField15);
					sFFPAGO = rs.getString(sField16);
					sBITC13 = rs.getString(sField17);
					sIMCUCO = rs.getString(sField18);
					sBITC14 = rs.getString(sField19);
					sFAACTA = rs.getString(sField20);
					sBITC15 = rs.getString(sField21);
					sPTPAGO = rs.getString(sField22);
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
		return new Cuota(sCODTRN, sCOTDOR, sIDPROV, sCOACCI, sCOCLDO, sNUDCOM,
				sCOENGP, sCOACES, sCOGRUG, sCOTACA, sCOSBAC, sBITC11, sFIPAGO,
				sBITC12, sFFPAGO, sBITC13, sIMCUCO, sBITC14, sFAACTA, sBITC15,
				sPTPAGO, sBITC09, sOBTEXC, sOBDEER);
	}
	
	public static String getCuotaID(Cuota cuota)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getCuotaID";

		Statement stmt = null;
		ResultSet rs = null;

		String sCuotaID = "";

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
					       + sField2  +" = '" + cuota.getCODTRN() + "' AND "
					       + sField3  +" = '" + cuota.getCOTDOR() + "' AND "
					       + sField4  +" = '" + cuota.getIDPROV() + "' AND "
					       + sField5  +" = '" + cuota.getCOACCI() + "' AND "
					       + sField6  +" = '" + cuota.getCOCLDO() + "' AND "
					       + sField7  +" = '" + cuota.getNUDCOM() + "' AND "
					       + sField8  +" = '" + cuota.getCOENGP() + "' AND "
					       + sField9  +" = '" + cuota.getCOACES() + "' AND "
					       + sField10 +" = '" + cuota.getCOGRUG() + "' AND "
					       + sField11 +" = '" + cuota.getCOTACA() + "' AND "
					       + sField12 +" = '" + cuota.getCOSBAC() + "' AND "
					       + sField13 +" = '" + cuota.getBITC11() + "' AND "
					       + sField14 +" = '" + cuota.getFIPAGO() + "' AND "
					       + sField15 +" = '" + cuota.getBITC12() + "' AND "
					       + sField16 +" = '" + cuota.getFFPAGO() + "' AND "
					       + sField17 +" = '" + cuota.getBITC13() + "' AND "
					       + sField18 +" = '" + cuota.getIMCUCO() + "' AND "
					       + sField19 +" = '" + cuota.getBITC14() + "' AND "
					       + sField20 +" = '" + cuota.getFAACTA() + "' AND "
					       + sField21 +" = '" + cuota.getBITC15() + "' AND "
					       + sField22 +" = '" + cuota.getPTPAGO() + "' AND "
					       + sField23 +" = '" + cuota.getBITC09() + "' AND "
					       + sField24 +" = '" + cuota.getOBTEXC() + "' AND "
					       + sField25 +" = '" + cuota.getOBDEER() + "' )");

			rs = pstmt.executeQuery();

			//System.out.println("===================================================");
			//System.out.println(sField1 + ": " + sCuotaID);

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCuotaID = rs.getString(sField1);
					System.out.println(sField1 + ": " + sCuotaID);



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
		return sCuotaID;
	}

}
