package com.provisiones.dal.qm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.types.Activo;

public class QMActivos 
{
	static String sClassName = QMCuotas.class.getName();

	static String sTable = "ac_activos_tbl";

	static String sField1 = "activo_coaces_id";

	static String sField2 = "cod_datos";
	static String sField3 = "cod_comunidad";
	static String sField4 = "cod_referencia";
	
	public static boolean addActivo(Activo NuevoActivo)

	{
		String sMethod = "addActivo";
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
					+ sField4
					+ ") VALUES ('" 
					+ NuevoActivo.getsCOACES() + "','"
					+ NuevoActivo.getsCodDatos() + "','" 
					+ NuevoActivo.getsCodComunidad()	+ "','" 
					+ NuevoActivo.getsCodReferencia() + "' )");
			
		} 
		catch (SQLException ex) 
		{

			// System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRAP: "
			// + NuevaComunidad.getCOGRAP());

			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: COACES: " + NuevoActivo.getsCOACES());

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

	public static boolean modActivo(Activo NuevoActivo, String sCOACES) 
	{
		String sMethod = "modActivo";
		Statement stmt = null;
		boolean bExit = false;
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + " SET " 
					+ sField2 + " = '" + NuevoActivo.getsCodDatos() + "','" 
					+ sField3 + " = '" + NuevoActivo.getsCodComunidad() + "','" 
					+ sField4 + " = '" + NuevoActivo.getsCodReferencia() + "' " 
					+ " WHERE " + sField1 + " = '" + sCOACES + "'");

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

	public static boolean delActivo(String sCOACES) 
	{
		String sMethod = "delCuota";
		Statement stmt = null;
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sCOACES + "' )");
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

	public static Activo getActivo(String sCOACES) 
	{


		String sMethod = "getActivo";

		Statement stmt = null;
		ResultSet rs = null;

		String sCodDatos = "";
		String sCodComunidad = "";
		String sCodReferencia = "";


		PreparedStatement pstmt = null;
		boolean found = false;

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try {
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " + sField2 + "," + sField3
					+ "," + sField4 +
					"  FROM " + sTable + " WHERE (" + sField1 + " = '"
					+ sCOACES + "')");

			rs = pstmt.executeQuery();

			System.out
					.println("===================================================");
			System.out.println(sField1 + ": " + sCOACES);

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCodDatos = rs.getString(sField2);
					sCodComunidad = rs.getString(sField3);
					sCodReferencia = rs.getString(sField4);

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
		return new Activo(sCOACES, sCodDatos, sCodComunidad, sCodReferencia);
	}
}