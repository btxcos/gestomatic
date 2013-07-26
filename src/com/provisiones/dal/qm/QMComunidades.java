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

	static String sField1  = "cod_codtrn";
	static String sField2  = "cotdor";    
	static String sField3  = "idprov";    
	static String sField4  = "cod_coacci";
	static String sField5  = "coengp";    
	static String sField6  = "cod_cocldo";
	static String sField7  = "nudcom_id";    
	static String sField8  = "cod_bitc10";
	static String sField9  = "cod_bitc01";
	static String sField10 = "nomcoc";    
	static String sField11 = "cod_bitc02";
	static String sField12 = "nodcco";    
	static String sField13 = "cod_bitc03";
	static String sField14 = "nomprc";    
	static String sField15 = "cod_bitc04";
	static String sField16 = "nutprc";    
	static String sField17 = "cod_bitc05";
	static String sField18 = "nomadc";    
	static String sField19 = "cod_bitc06";
	static String sField20 = "nutadc";    
	static String sField21 = "cod_bitc07";
	static String sField22 = "nodcad";    
	static String sField23 = "cod_bitc08";
	static String sField24 = "nuccen";    
	static String sField25 = "nuccof";    
	static String sField26 = "nuccdi";    
	static String sField27 = "nuccnt";    
	static String sField28 = "cod_bitc09";
	static String sField29 = "obtexc";    
	static String sField30 = "obdeer";    

	public static boolean addComunidad(Comunidad NuevaComunidad)

	{
		String sMethod = "addComunidad";
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
				       + sField30 +               
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
			
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NUDCOM: " + NuevaComunidad.getNUDCOM());
			
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
					+ sField1  + " = '"+ NuevaComunidad.getCODTRN() + "','"
					+ sField2  + " = '"+ NuevaComunidad.getCOTDOR() + "','"
					+ sField3  + " = '"+ NuevaComunidad.getIDPROV() + "','"
					+ sField4  + " = '"+ NuevaComunidad.getCOACCI() + "','"
					+ sField5  + " = '"+ NuevaComunidad.getCOENGP() + "','"
					+ sField6  + " = '"+ NuevaComunidad.getCOCLDO() + "','"
					//+ sField7  + " = '"+ NuevaComunidad.getNUDCOM() + "','"
					+ sField8  + " = '"+ NuevaComunidad.getBITC10() + "','"
					+ sField9  + " = '"+ NuevaComunidad.getBITC01() + "','"
					+ sField10 + " = '"+ NuevaComunidad.getNOMCOC() + "','"
					+ sField11 + " = '"+ NuevaComunidad.getBITC02() + "','"
					+ sField12 + " = '"+ NuevaComunidad.getNODCCO() + "','"
					+ sField13 + " = '"+ NuevaComunidad.getBITC03() + "','"
					+ sField14 + " = '"+ NuevaComunidad.getNOMPRC() + "','"
					+ sField15 + " = '"+ NuevaComunidad.getBITC04() + "','"
					+ sField16 + " = '"+ NuevaComunidad.getNUTPRC() + "','"
					+ sField17 + " = '"+ NuevaComunidad.getBITC05() + "','"
					+ sField18 + " = '"+ NuevaComunidad.getNOMADC() + "','"
					+ sField19 + " = '"+ NuevaComunidad.getBITC06() + "','"
					+ sField20 + " = '"+ NuevaComunidad.getNUTADC() + "','"
					+ sField21 + " = '"+ NuevaComunidad.getBITC07() + "','"
					+ sField22 + " = '"+ NuevaComunidad.getNODCAD() + "','"
					+ sField23 + " = '"+ NuevaComunidad.getBITC08() + "','"
					+ sField24 + " = '"+ NuevaComunidad.getNUCCEN() + "','"
					+ sField25 + " = '"+ NuevaComunidad.getNUCCOF() + "','"
					+ sField26 + " = '"+ NuevaComunidad.getNUCCDI() + "','"
					+ sField27 + " = '"+ NuevaComunidad.getNUCCNT() + "','"
					+ sField28 + " = '"+ NuevaComunidad.getBITC09() + "','"
					+ sField29 + " = '"+ NuevaComunidad.getOBTEXC() + "','"
					+ sField30 + " = '"+ NuevaComunidad.getOBDEER() +
					"' "+
					" WHERE "
					+ sField7 + " = '"+ sComunidadID +"'");
			
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
					" WHERE (" + sField7 + " = '" + sComunidadID + "' )");
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
				       + sField30 +               
       
			"  FROM " + sTable + 
					" WHERE (" + sField7 + " = '" + sComunidadID	+ "')");

			rs = pstmt.executeQuery();

			System.out.println("===================================================");
			System.out.println(sField7 + ": " + sComunidadID);

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCOACES = "";
					sCODTRN = rs.getString(sField1); 
					sCOTDOR = rs.getString(sField2); 
					sIDPROV = rs.getString(sField3); 
					sCOACCI = rs.getString(sField4); 
					sCOENGP = rs.getString(sField5); 
					sCOCLDO = rs.getString(sField6); 
					sNUDCOM = rs.getString(sField7); 
					sBITC10 = rs.getString(sField8); 
					sBITC01 = rs.getString(sField9); 
					sNOMCOC = rs.getString(sField10);
					sBITC02 = rs.getString(sField11);
					sNODCCO = rs.getString(sField12);
					sBITC03 = rs.getString(sField13);
					sNOMPRC = rs.getString(sField14);
					sBITC04 = rs.getString(sField15);
					sNUTPRC = rs.getString(sField16);
					sBITC05 = rs.getString(sField17);
					sNOMADC = rs.getString(sField18);
					sBITC06 = rs.getString(sField19);
					sNUTADC = rs.getString(sField20);
					sBITC07 = rs.getString(sField21);
					sNODCAD = rs.getString(sField22);
					sBITC08 = rs.getString(sField23);
					sNUCCEN = rs.getString(sField24);
					sNUCCOF = rs.getString(sField25);
					sNUCCDI = rs.getString(sField26);
					sNUCCNT = rs.getString(sField27);
					sBITC09 = rs.getString(sField28);
					sOBTEXC = rs.getString(sField29);
					sOBDEER = rs.getString(sField30);




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