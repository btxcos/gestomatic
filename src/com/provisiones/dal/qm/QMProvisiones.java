package com.provisiones.dal.qm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Provision;

public class QMProvisiones 
{
	static String sClassName = QMProvisiones.class.getName();
	
	static boolean bTrazas = true;

	public static final String sTable = "provisiones_tbl";

	public static final String sField1 = "nuprof_id";

	public static final String sField2 = "cod_cospat";
	public static final String sField3 = "valor_total";
	public static final String sField4 = "numero_gastos";
	public static final String sField5 = "fepfon";
	public static final String sField6 = "fecha_validacion";
	public static final String sField7 = "cod_validado";
	

	

	public static boolean addProvision(Provision NuevaProvision)

	{
		String sMethod = "addProvision";
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{

			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + sTable + " (" 
					+ sField1 + ","
					+ sField2 + ","
					+ sField3 + "," 
					+ sField4 + ","
					+ sField5 + ","
					+ sField6 + "," 
					+ sField7
					+ ") VALUES ('" 
					+ NuevaProvision.getsNUPROF() + "','"
					+ NuevaProvision.getsCOSPAT() + "','"
					+ NuevaProvision.getsValorTolal() + "','"
					+ NuevaProvision.getsNumGastos() + "','"
					+ NuevaProvision.getsFEPFON() + "','" 
					+ NuevaProvision.getsFechaValidacion() + "','" 
					+ NuevaProvision.getsValidado() + "')");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

		} 
		catch (SQLException ex) 
		{
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: NUPROF: " + NuevaProvision.getsNUPROF());

			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: SQLException: " + ex.getMessage());
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("[" + sClassName + "." + sMethod + "] ERROR: VendorError: " + ex.getErrorCode());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static boolean modProvision(Provision NuevaProvision, String sNUPROF) 
	{
		String sMethod = "modProvision";
		Statement stmt = null;
		boolean bSalida = true;
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + " SET " 
					+ sField2 + " = '" + NuevaProvision.getsCOSPAT() + "', "
					+ sField3 + " = '" + NuevaProvision.getsValorTolal() + "', " 
					+ sField4 + " = '" + NuevaProvision.getsNumGastos() + "', "
					+ sField5 + " = '" + NuevaProvision.getsFEPFON() + "', " 
					+ sField6 + " = '" + NuevaProvision.getsFechaValidacion() + "', " 
					+ sField7 + " = '" + NuevaProvision.getsValidado() + "' " 
					+ " WHERE " + sField1 + " = '" + sNUPROF + "'");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

		} 
		catch (SQLException ex) 
		{
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: NUPROF: " + sNUPROF);

			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: SQLException: " + ex.getMessage());
			System.out.println("[" + sClassName + "." + sMethod + "] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: VendorError: " + ex.getErrorCode());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static boolean delProvision(String sNUPROF) 
	{
		String sMethod = "delProvision";
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sNUPROF + "' )");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: NUPROF: " + sNUPROF);

			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: SQLException: " + ex.getMessage());
			System.out.println("[" + sClassName + "." + sMethod + "] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: VendorError: " + ex.getErrorCode());
			
			bSalida = false;
		} 
		finally
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static Provision getProvision(String sNUPROF) 
	{


		String sMethod = "getProvision";

		Statement stmt = null;
		ResultSet rs = null;

		String sCOSPAT = "";
		String sValorTolal = "";
		String sNumGastos = "";
		String sFEPFON = "";
		String sFechaValidacion = "";
		String sValidado = "";


		PreparedStatement pstmt = null;
		boolean found = false;

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " + sField2 + "," + sField3
					+ "," + sField4 + "," + sField5 + "," + sField6 + "," + sField7 +
					" FROM " + sTable + " WHERE (" + sField1 + " = '"
					+ sNUPROF + "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");



			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCOSPAT = rs.getString(sField2);
					sValorTolal = rs.getString(sField3);
					sNumGastos = rs.getString(sField4);
					sFEPFON = rs.getString(sField5);
					sFechaValidacion = rs.getString(sField6);
					sValidado = rs.getString(sField7);

					
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField1 + ": " + sNUPROF);

				}
			}
			if (found == false) 
			{
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: NUPROF: " + sNUPROF);

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
		return new Provision(sNUPROF, sCOSPAT, sValorTolal, sNumGastos, sFEPFON, sFechaValidacion, sValidado);
	}
	
	public static boolean existeProvision(String sNUPROF) 
	{


		String sMethod = "existeProvision";

		Statement stmt = null;
		ResultSet rs = null;

		PreparedStatement pstmt = null;
		boolean found = false;

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " + sField1  +
					" FROM " + sTable + " WHERE (" + sField1 + " = '"
					+ sNUPROF + "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");



			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField1 + ": " + sNUPROF);

				}
			}
			if (found == false) 
			{
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: NUPROF: " + sNUPROF);

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
	
	public static String getProvisionAbierta() 
	{


		String sMethod = "getProvisionAbierta";

		Statement stmt = null;
		ResultSet rs = null;

		String sNUPROF = "";


		PreparedStatement pstmt = null;
		boolean found = false;

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " + sField1 + 
					" FROM " + sTable + 
					" WHERE ( " + sField7 + " = '"
					+ ValoresDefecto.DEF_PENDIENTE + "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");



			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sNUPROF = rs.getString(sField1);


					
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField1 + ": " + sNUPROF);

				}
			}
			if (found == false) 
			{
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
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
		return sNUPROF;
	}
	

}
