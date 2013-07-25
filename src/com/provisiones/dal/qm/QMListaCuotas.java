package com.provisiones.dal.qm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;

public class QMListaCuotas 
{
	static String sClassName = QMListaCuotas.class.getName();

	static String sTable = "lista_cuotas_multi";
	static String sField1 = "cod_coaces";
	static String sField2 = "cod_nudcom";
	static String sField3 = "cod_cuotas";

	public static boolean addRelacionCuotas(String sCodCOACES, String sCodNUDCOM, String CodCuotas) 
	{
		String sMethod = "addRelacionCuotas";
		Statement stmt = null;
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + sTable + 
					" (" + sField1 + "," + sField2 + "," + sField3 +") " +
					"VALUES ('" + sCodCOACES + "','"+ sCodNUDCOM + "','"+ CodCuotas + "')");
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

	public static boolean delRelacionCuotas(String sCodCOACES, String sCodNUDCOM, String CodCuotas) 
	{
		String sMethod = "delRelacionCuotas";
		Statement stmt = null;
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sCodCOACES + "' " +
							"AND  " + sField2 + " = '" + sCodNUDCOM + "' " +
							"AND  " + sField3 + " = '" + CodCuotas +"')");
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

	public static ArrayList<String>  getCuotas(String sCodCOACES, String sCodNUDCOM) 
	{
		String sMethod = "getCuotas";

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


			pstmt = conn.prepareStatement("SELECT " + sField3 + "  FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sCodCOACES + "' " +
							"AND" + sField2 + " = '" + sCodNUDCOM + "' )");

			rs = pstmt.executeQuery();
			
			
			System.out.println("===================================================");
			System.out.println(sField1 + ": " + sCodCOACES);
			System.out.println(sField2 + ": " + sCodNUDCOM);

			

			
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(sField3));
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
}
