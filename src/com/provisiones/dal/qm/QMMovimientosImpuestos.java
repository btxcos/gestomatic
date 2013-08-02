package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.types.MovimientoImpuestoRecurso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QMMovimientosImpuestos 
{
	static String sClassName = QMMovimientosImpuestos.class.getName();
	
	static boolean bTrazas = true;

	static String sTable = "e4_movimientos_tbl";

	static String sField1 = "e4_movimiento_id";

	static String sField2  = "cod_codtrn";
	static String sField3  = "cod_cotdor";
	static String sField4  = "idprov";    
	static String sField5  = "cod_coacci";
	static String sField6  = "coengp";    
	static String sField7  = "cod_coaces";
	static String sField8  = "cod_nurcat";    
	static String sField9  = "cogruc";
	static String sField10 = "cotaca";
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

	public static int addMovimientoImpuestoRecurso(MovimientoImpuestoRecurso NuevoMovimientoImpuestoRecurso)

	{
		String sMethod = "addMovimientoImpuestoRecurso";
		Statement stmt = null;
		Connection conn = null;
		ResultSet resulset = null;
		
		int iCodigo = 0;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

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
				       + NuevoMovimientoImpuestoRecurso.getCODTRN() + "','" 
				       + NuevoMovimientoImpuestoRecurso.getCOTDOR() + "','"
				       + NuevoMovimientoImpuestoRecurso.getIDPROV() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOACCI() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOENGP() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOACES() + "','"
				       + NuevoMovimientoImpuestoRecurso.getNURCAT() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOGRUC() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOTACA() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOSBAC() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBITC18() + "','"
				       + NuevoMovimientoImpuestoRecurso.getFEPRRE() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBITC19() + "','"
				       + NuevoMovimientoImpuestoRecurso.getFERERE() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBITC20() + "','"
				       + NuevoMovimientoImpuestoRecurso.getFEDEIN() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBITC21() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBISODE() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBITC22() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBIRESO() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOTEXA() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBITC09() + "','"
				       + NuevoMovimientoImpuestoRecurso.getOBTEXC() + "','"
				       + NuevoMovimientoImpuestoRecurso.getOBDEER() + "' )", Statement.RETURN_GENERATED_KEYS);
			
			resulset = stmt.getGeneratedKeys();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			if (resulset.next()) 
			{
				iCodigo= resulset.getInt(1);
			} 
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + NuevoMovimientoImpuestoRecurso.getCOACES());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + NuevoMovimientoImpuestoRecurso.getNURCAT());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBAC: " + NuevoMovimientoImpuestoRecurso.getCOSBAC());
			
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());
			
			//bSalida = false;
		} 
		finally
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
			Utils.closeResultSet(resulset,sClassName,sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return iCodigo;//bSalida;
	}
	public static boolean modMovimientoImpuestoRecurso(MovimientoImpuestoRecurso NuevoMovimientoImpuestoRecurso, String sMovimientoImpuestoRecursoID)
	{
		String sMethod = "modMovimientoImpuestoRecurso";
		Statement stmt = null;
		boolean bSalida = true;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField2  + " = '"+ NuevoMovimientoImpuestoRecurso.getCODTRN() + "', "
					+ sField3  + " = '"+ NuevoMovimientoImpuestoRecurso.getCOTDOR() + "', "
					+ sField4  + " = '"+ NuevoMovimientoImpuestoRecurso.getIDPROV() + "', "
					+ sField5  + " = '"+ NuevoMovimientoImpuestoRecurso.getCOACCI() + "', "
					+ sField6  + " = '"+ NuevoMovimientoImpuestoRecurso.getCOENGP() + "', "
					+ sField7  + " = '"+ NuevoMovimientoImpuestoRecurso.getCOACES() + "', "
					+ sField8  + " = '"+ NuevoMovimientoImpuestoRecurso.getNURCAT() + "', "
					+ sField9  + " = '"+ NuevoMovimientoImpuestoRecurso.getCOGRUC() + "', "
					+ sField10 + " = '"+ NuevoMovimientoImpuestoRecurso.getCOTACA() + "', "
					+ sField11 + " = '"+ NuevoMovimientoImpuestoRecurso.getCOSBAC() + "', "
					+ sField12 + " = '"+ NuevoMovimientoImpuestoRecurso.getBITC18() + "', "
					+ sField13 + " = '"+ NuevoMovimientoImpuestoRecurso.getFEPRRE() + "', "
					+ sField14 + " = '"+ NuevoMovimientoImpuestoRecurso.getBITC19() + "', "
					+ sField15 + " = '"+ NuevoMovimientoImpuestoRecurso.getFERERE() + "', "
					+ sField16 + " = '"+ NuevoMovimientoImpuestoRecurso.getBITC20() + "', "
					+ sField17 + " = '"+ NuevoMovimientoImpuestoRecurso.getFEDEIN() + "', "
					+ sField18 + " = '"+ NuevoMovimientoImpuestoRecurso.getBITC21() + "', "
					+ sField19 + " = '"+ NuevoMovimientoImpuestoRecurso.getBISODE() + "', "
					+ sField20 + " = '"+ NuevoMovimientoImpuestoRecurso.getBITC22() + "', "
					+ sField21 + " = '"+ NuevoMovimientoImpuestoRecurso.getBIRESO() + "', "
					+ sField22 + " = '"+ NuevoMovimientoImpuestoRecurso.getCOTEXA() + "', "
					+ sField23 + " = '"+ NuevoMovimientoImpuestoRecurso.getBITC09() + "', "
					+ sField24 + " = '"+ NuevoMovimientoImpuestoRecurso.getOBTEXC() + "', "
					+ sField25 + " = '"+ NuevoMovimientoImpuestoRecurso.getOBDEER() +
					"' "+
					" WHERE "
					+ sField1 + " = '"+ sMovimientoImpuestoRecursoID +"'");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: MovimientoImpuestoRecursoID: " + sMovimientoImpuestoRecursoID);

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

	public static boolean delMovimientoImpuestoRecurso(String sMovimientoImpuestoRecursoID)
	{
		String sMethod = "delMovimientoImpuestoRecurso";
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sMovimientoImpuestoRecursoID + "' )");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: MovimientoImpuestoRecursoID: " + sMovimientoImpuestoRecursoID);

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

	public static MovimientoImpuestoRecurso getMovimientoImpuestoRecurso(String sMovimientoImpuestoRecursoID)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getMovimientoImpuestoRecurso";

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
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

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
					" WHERE (" + sField1 + " = '" + sMovimientoImpuestoRecursoID	+ "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

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
					
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod,sField1 + ": " + sMovimientoImpuestoRecursoID);

				}
			}
			if (found == false) 
			{
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: MovimientoImpuestoRecursoID: " + sMovimientoImpuestoRecursoID);

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
		return new MovimientoImpuestoRecurso(sCODTRN, sCOTDOR, sIDPROV, sCOACCI, sCOENGP,
				sCOACES, sNURCAT, sCOGRUC, sCOTACA, sCOSBAC, sBITC18, sFEPRRE,
				sBITC19, sFERERE, sBITC20, sFEDEIN, sBITC21, sBISODE, sBITC22,
				sBIRESO, sCOTEXA, sBITC09, sOBTEXC, sOBDEER);
	}

	public static String getMovimientoImpuestoRecursoID(MovimientoImpuestoRecurso impuesto)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getMovimientoImpuestoRecursoID";

		Statement stmt = null;
		ResultSet rs = null;

		String sImpuestoID = "";

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

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
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sImpuestoID = rs.getString(sField1);
					
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod,sField1 + ": " + sImpuestoID);
				}
			}
			if (found == false) 
			{
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + impuesto.getCOACES());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + impuesto.getNURCAT());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBAC: " + impuesto.getCOSBAC());


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
