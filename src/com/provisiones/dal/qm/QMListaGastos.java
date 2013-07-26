package com.provisiones.dal.qm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;

public class QMListaGastos 
{
	static String sClassName = QMListaGastos.class.getName();

	static String sTable = "lista_gastos_multi";
	static String sField1 = "cod_coaces";
	static String sField2 = "cod_gasto";
	static String sField3 = "cod_nuprof";


	public static boolean addRelacionGastos(String sCodCOACES, String sCodGastos, String sCodNUPROF) 
	{
		String sMethod = "addRelacionGastos";
		Statement stmt = null;
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + sTable + 
					" (" + sField1 + "," + sField2 + "," + sField3 +") " +
					"VALUES ('" + sCodCOACES + "','"+ sCodGastos + "','"+ sCodNUPROF + "')");
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

	public static boolean delRelacionGastos(String sCodCOACES, String sCodGastos, String sCodNUPROF) 
	{
		String sMethod = "delRelacionGastos";
		Statement stmt = null;
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sCodCOACES + "' " +
							"AND  " + sField2 + " = '" + sCodGastos+"'"+
							"AND  " + sField3 + " = '" + sCodNUPROF+"')");
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

	public static ArrayList<String>  getGastosPorActivo(String sCodCOACES) 
	{
		String sMethod = "getGastosPorActivo";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	
		
		ArrayList<String> result = new ArrayList<String>(); 
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField2 + "  FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sCodCOACES + "' )");

			rs = pstmt.executeQuery();
			
			
			System.out.println("===================================================");
			System.out.println(sField1 + ": " + sCodCOACES);

			
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(sField2));
					System.out.println(result.get(i));

					System.out.println("===================================================");
					i++;
				}
			}
			if (found == false) 
			{
				result = new ArrayList<String>(); 
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
		return result;
	}
	
	public static ArrayList<String>  getGastosPorProvision(String sCodNUPROF) 
	{
		String sMethod = "getGastosPorProvision";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	
		
		ArrayList<String> result = new ArrayList<String>(); 
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField2 + "  FROM " + sTable + 
					" WHERE (" + sField3 + " = '" + sCodNUPROF + "' )");

			rs = pstmt.executeQuery();
			
			
			System.out.println("===================================================");
			System.out.println(sField3 + ": " + sCodNUPROF);

			
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(sField2));
					System.out.println(result.get(i));

					System.out.println("===================================================");
					i++;
				}
			}
			if (found == false) 
			{
				result = new ArrayList<String>(); 
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
		return result;
	}

	public static String  getGastoID(String sCodCOACES, String sCodNUPROF) 
	{
		String sMethod = "getGastoID";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sResult = "";

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField2 + "  FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sCodCOACES + "' " +
							 " AND "  + sField3 + " = '" + sCodNUPROF + "')");

			rs = pstmt.executeQuery();
			
			
			System.out.println("===================================================");
			System.out.println(sField1 + ": " + sCodCOACES);
			System.out.println(sField3 + ": " + sCodNUPROF);

			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sResult = rs.getString(sField2);

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
		return sResult;
	}
}
