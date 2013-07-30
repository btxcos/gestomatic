package com.provisiones.dal.qm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.types.Provision;

public class QMProvisiones 
{
	static String sClassName = QMCuotas.class.getName();

	static String sTable = "provisiones_tbl";

	static String sField1 = "nuprof_id";

	static String sField2 = "fepfon";
	static String sField3 = "fecha_validacion";
	static String sField4 = "estado";
	static String sField5 = "valortotal";
	static String sField6 = "numgastos";
	

	public static boolean addProvision(Provision NuevaProvision)

	{
		String sMethod = "addProvision";
		Statement stmt = null;
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try 
		{

			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + sTable + " (" 
					+ sField1 + ","
					+ sField2 + ","
					+ sField3 + "," 
					+ sField4 + ","
					+ sField5 + "," 
					+ sField6
					+ ") VALUES ('" 
					+ NuevaProvision.getsNUPROF() + "','"
					+ NuevaProvision.getsFEPFON() + "','" 
					+ NuevaProvision.getsFechaValidacion() + "','" 
					+ NuevaProvision.getsEstado() + "','" 
					+ NuevaProvision.getsValorTolal() + "','" 
					+ NuevaProvision.getsNumGastos() + "' )");

		} 
		catch (SQLException ex) 
		{

			// System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRAP: "
			// + NuevaComunidad.getCOGRAP());

			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: NUPROF: " + NuevaProvision.getsNUPROF());

			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: SQLException: " + ex.getMessage());
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("[" + sClassName + "." + sMethod + "] ERROR: VendorError: " + ex.getErrorCode());
		} 
		finally 
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return true;
	}

	public static boolean modProvision(Provision NuevaProvision, String sNUPROF) 
	{
		String sMethod = "modProvision";
		Statement stmt = null;
		boolean bExit = false;
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + " SET " 
					+ sField2 + " = '" + NuevaProvision.getsFEPFON() + "','" 
					+ sField3 + " = '" + NuevaProvision.getsFechaValidacion() + "','"
					+ sField4 + " = '" + NuevaProvision.getsEstado() + "','" 
					+ sField5 + " = '" + NuevaProvision.getsValorTolal() + "','" 
					+ sField6 + " = '" + NuevaProvision.getsNumGastos() + "' " 
					+ " WHERE " + sField1 + " = '" + sNUPROF + "'");

		} 
		catch (SQLException ex) 
		{

			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: SQLException: " + ex.getMessage());
			System.out.println("[" + sClassName + "." + sMethod + "] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: VendorError: " + ex.getErrorCode());
		} 
		finally 
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
			bExit = true;
		}
		ConnectionManager.CloseDBConnection(conn);
		return bExit;
	}

	public static boolean delProvision(String sNUPROF) 
	{
		String sMethod = "delProvision";
		Statement stmt = null;
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sNUPROF + "' )");
		} 
		catch (SQLException ex) 
		{

			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: SQLException: " + ex.getMessage());
			System.out.println("[" + sClassName + "." + sMethod + "] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: VendorError: " + ex.getErrorCode());
		} 
		finally
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return true;
	}

	public static Provision getProvision(String sNUPROF) 
	{


		String sMethod = "getProvision";

		Statement stmt = null;
		ResultSet rs = null;

		String sFEPFON = "";
		String sFechaValidacion = "";
		String sEstado = "";
		String sValorTolal = "";
		String sNumGastos = "";

		PreparedStatement pstmt = null;
		boolean found = false;

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try {
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " + sField2 + "," + sField3
					+ "," + sField4 + "," + sField5 + "," + sField6 +
					"  FROM " + sTable + " WHERE (" + sField1 + " = '"
					+ sNUPROF + "')");

			rs = pstmt.executeQuery();

			System.out
					.println("===================================================");
			System.out.println(sField1 + ": " + sNUPROF);

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sFEPFON = rs.getString(sField2);
					sFechaValidacion = rs.getString(sField3);
					sEstado = rs.getString(sField4);
					sValorTolal = rs.getString(sField3);
					sNumGastos = rs.getString(sField4);
					
					// System.out.println(sField2 + ": " + sApplication);
					// System.out.println(sField3 + ": " + sContactCode);
					// System.out.println(sField4 + ": " + sProjectCode);
					// System.out.println("===================================================");

				}
			}
			if (found == false) 
			{
				System.out.println("No Information Found");
			}

		} 
		catch (SQLException ex) 
		{

			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: SQLException: " + ex.getMessage());
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: VendorError: " + ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs, sClassName, sMethod);
			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return new Provision(sNUPROF, sFEPFON, sFechaValidacion, sEstado, sValorTolal, sNumGastos);
	}
	
	public static boolean verificarProvision(String sNUPROF) 
	{


		String sMethod = "getProvision";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;

		boolean found = false;

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try {
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " + sField2 + "," + sField3
					+ "," + sField4 + "," + sField5 + "," + sField6 +
					"  FROM " + sTable + " WHERE (" + sField1 + " = '"
					+ sNUPROF + "')");

			rs = pstmt.executeQuery();

			System.out
					.println("===================================================");
			System.out.println(sField1 + ": " + sNUPROF);
			
			found = (rs != null); 

			if (found == false) 
			{
				System.out.println("No Information Found");
			}

		} 
		catch (SQLException ex) 
		{

			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: SQLException: " + ex.getMessage());
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: VendorError: " + ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs, sClassName, sMethod);
			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return found;
	}
}
