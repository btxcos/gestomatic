package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.types.Comunidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QMComunidades
{
	static String sClassName = QMComunidades.class.getName();

	static String sTable = "e1_comunidades_tbl";

	static String sField1 = "e1_comunidades_id";

	static String sField2 = "cod_codtrn";
	static String sField3 = "cotdor";
	static String sField4 = "idprov";
	static String sField5 = "cod_coacci";
	static String sField6 = "coengp";
	static String sField7 = "cod_cocldo";
	static String sField8 = "nudcom";
	static String sField9 = "cod_bitc10";
	static String sField10 = "cod_bitc01";
	static String sField11 = "nomcoc";
	static String sField12 = "cod_bitc02";
	static String sField13 = "nodcco";
	static String sField14 = "cod_bitc03";
	static String sField15 = "nomprc";
	static String sField16 = "cod_bitc04";
	static String sField17 = "nutprc";
	static String sField18 = "cod_bitc05";
	static String sField19 = "nomadc";
	static String sField20 = "cod_bitc06";
	static String sField21 = "nutadc";
	static String sField22 = "cod_bitc07";
	static String sField23 = "nodcad";
	static String sField24 = "cod_bitc08";
	static String sField25 = "nuccen";
	static String sField26 = "nuccof";
	static String sField27 = "nuccdi";
	static String sField28 = "nuccnt";
	static String sField29 = "cod_bitc09";
	static String sField30 = "obtexc";
	static String sField31 = "obdeer";

	public static boolean addComunidad(Comunidad NuevaComunidad)

	{
		String sMethod = "addComunidad";
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
				       + sField25 + ","              
				       + sField26 + ","              
				       + sField27 + ","              
				       + sField28 + ","              
				       + sField29 + ","              
				       + sField30 + ","              
				       + sField31 +
				       ") VALUES ('" 
				       + NuevaComunidad.getCODTRN() + "','" 
				       + NuevaComunidad.getCOTDOR() + "','"
				       + NuevaComunidad.getIDPROV() + "','"
				       + NuevaComunidad.getCOACCI() + "','"
				       + NuevaComunidad.getCOENGP() + "','"
				       + NuevaComunidad.getCOCLDO() + "','"
				       + NuevaComunidad.getNUDCOM() + "','"
				       + NuevaComunidad.getBITC10() + "','"
				       + NuevaComunidad.getBITC01() + "','"
				       + NuevaComunidad.getNOMCOC() + "','"
				       + NuevaComunidad.getBITC02() + "','"
				       + NuevaComunidad.getNODCCO() + "','"
				       + NuevaComunidad.getBITC03() + "','"
				       + NuevaComunidad.getNOMPRC() + "','"
				       + NuevaComunidad.getBITC04() + "','"
				       + NuevaComunidad.getNUTPRC() + "','"
				       + NuevaComunidad.getBITC05() + "','"
				       + NuevaComunidad.getNOMADC() + "','"
				       + NuevaComunidad.getBITC06() + "','"
				       + NuevaComunidad.getNUTADC() + "','"
				       + NuevaComunidad.getBITC07() + "','"
				       + NuevaComunidad.getNODCAD() + "','"
				       + NuevaComunidad.getBITC08() + "','"
				       + NuevaComunidad.getNUCCEN() + "','"
				       + NuevaComunidad.getNUCCOF() + "','"
				       + NuevaComunidad.getNUCCDI() + "','"
				       + NuevaComunidad.getNUCCNT() + "','"
				       + NuevaComunidad.getBITC09() + "','"
				       + NuevaComunidad.getOBTEXC() + "','"
				       + NuevaComunidad.getOBDEER() + "' )");
		} 
		catch (SQLException ex) 
		{


			//System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRAP: " + NuevaComunidad.getCOGRAP());
			
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + NuevaComunidad.getCOACES());
			
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
	public static boolean modComunidad(Comunidad NuevaComunidad, String sComunidadID)
	{
		String sMethod = "modComunidad";
		Statement stmt = null;
		boolean bExit = false;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField2  + " = '"+ NuevaComunidad.getCODTRN() + "','"
					+ sField3  + " = '"+ NuevaComunidad.getCOTDOR() + "','"
					+ sField4  + " = '"+ NuevaComunidad.getIDPROV() + "','"
					+ sField5  + " = '"+ NuevaComunidad.getCOACCI() + "','"
					+ sField6  + " = '"+ NuevaComunidad.getCOENGP() + "','"
					+ sField7  + " = '"+ NuevaComunidad.getCOCLDO() + "','"
					+ sField8  + " = '"+ NuevaComunidad.getNUDCOM() + "','"
					+ sField9  + " = '"+ NuevaComunidad.getBITC10() + "','"
					+ sField10 + " = '"+ NuevaComunidad.getBITC01() + "','"
					+ sField11 + " = '"+ NuevaComunidad.getNOMCOC() + "','"
					+ sField12 + " = '"+ NuevaComunidad.getBITC02() + "','"
					+ sField13 + " = '"+ NuevaComunidad.getNODCCO() + "','"
					+ sField14 + " = '"+ NuevaComunidad.getBITC03() + "','"
					+ sField15 + " = '"+ NuevaComunidad.getNOMPRC() + "','"
					+ sField16 + " = '"+ NuevaComunidad.getBITC04() + "','"
					+ sField17 + " = '"+ NuevaComunidad.getNUTPRC() + "','"
					+ sField18 + " = '"+ NuevaComunidad.getBITC05() + "','"
					+ sField19 + " = '"+ NuevaComunidad.getNOMADC() + "','"
					+ sField20 + " = '"+ NuevaComunidad.getBITC06() + "','"
					+ sField21 + " = '"+ NuevaComunidad.getNUTADC() + "','"
					+ sField22 + " = '"+ NuevaComunidad.getBITC07() + "','"
					+ sField23 + " = '"+ NuevaComunidad.getNODCAD() + "','"
					+ sField24 + " = '"+ NuevaComunidad.getBITC08() + "','"
					+ sField25 + " = '"+ NuevaComunidad.getNUCCEN() + "','"
					+ sField26 + " = '"+ NuevaComunidad.getNUCCOF() + "','"
					+ sField27 + " = '"+ NuevaComunidad.getNUCCDI() + "','"
					+ sField28 + " = '"+ NuevaComunidad.getNUCCNT() + "','"
					+ sField29 + " = '"+ NuevaComunidad.getBITC09() + "','"
					+ sField30 + " = '"+ NuevaComunidad.getOBTEXC() + "','"
					+ sField31 + " = '"+ NuevaComunidad.getOBDEER() + 
					"' "+
					" WHERE "
					+ sField1 + " = '"+ sComunidadID +"'");
			
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

	public static boolean delComunidad(String sComunidadID)
	{
		String sMethod = "delComunidad";
		Statement stmt = null;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sComunidadID + "' )");
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

	public static Comunidad getComunidad(String sComunidadID)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getComunidad";

		Statement stmt = null;
		ResultSet rs = null;

		String sCODTRN = "";
		String sCOTDOR = "";
		String sIDPROV = "";
		String sCOACCI = "";
		String sCOENGP = "";
		String sCOCLDO = "";
		String sNUDCOM = "";
		String sBITC10 = "";
		String sCOACES = "";
		String sBITC01 = "";
		String sNOMCOC = "";
		String sBITC02 = "";
		String sNODCCO = "";
		String sBITC03 = "";
		String sNOMPRC = "";
		String sBITC04 = "";
		String sNUTPRC = "";
		String sBITC05 = "";
		String sNOMADC = "";
		String sBITC06 = "";
		String sNUTADC = "";
		String sBITC07 = "";
		String sNODCAD = "";
		String sBITC08 = "";
		String sNUCCEN = "";
		String sNUCCOF = "";
		String sNUCCDI = "";
		String sNUCCNT = "";
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
				       + sField25 + ","              
				       + sField26 + ","              
				       + sField27 + ","              
				       + sField28 + ","              
				       + sField29 + ","              
				       + sField30 + ","              
				       + sField31 +               
       
			"  FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sComunidadID	+ "')");

			rs = pstmt.executeQuery();

			System.out.println("===================================================");
			System.out.println(sField1 + ": " + sComunidadID);

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
					sCOCLDO = rs.getString(sField7);
					sNUDCOM = rs.getString(sField8);
					sBITC10 = rs.getString(sField9);
					sBITC01 = rs.getString(sField10);
					sNOMCOC = rs.getString(sField11);
					sBITC02 = rs.getString(sField12);
					sNODCCO = rs.getString(sField13);
					sBITC03 = rs.getString(sField14);
					sNOMPRC = rs.getString(sField15);
					sBITC04 = rs.getString(sField16);
					sNUTPRC = rs.getString(sField17);
					sBITC05 = rs.getString(sField18);
					sNOMADC = rs.getString(sField19);
					sBITC06 = rs.getString(sField20);
					sNUTADC = rs.getString(sField21);
					sBITC07 = rs.getString(sField22);
					sNODCAD = rs.getString(sField23);
					sBITC08 = rs.getString(sField24);
					sNUCCEN = rs.getString(sField25);
					sNUCCOF = rs.getString(sField26);
					sNUCCDI = rs.getString(sField27);
					sNUCCNT = rs.getString(sField28);
					sBITC09 = rs.getString(sField29);
					sOBTEXC = rs.getString(sField30);
					sOBDEER = rs.getString(sField31);



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
		return new Comunidad(sCODTRN, sCOTDOR, sIDPROV, sCOACCI, sCOENGP,
				sCOCLDO, sNUDCOM, sBITC10, sCOACES, sBITC01, sNOMCOC, sBITC02,
				sNODCCO, sBITC03, sNOMPRC, sBITC04, sNUTPRC, sBITC05, sNOMADC,
				sBITC06, sNUTADC, sBITC07, sNODCAD, sBITC08, sNUCCEN, sNUCCOF,
				sNUCCDI, sNUCCNT, sBITC09, sOBTEXC, sOBDEER);
	}

}