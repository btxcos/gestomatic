package com.provisiones.dal.qm.listas;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QMListaComunidades
{
	static String sClassName = QMListaComunidades.class.getName();
	
	static boolean bTrazas = true;

	static String sTable = "lista_comunidades_multi";

	static String sField1  = "cod_cocldo";
	static String sField2  = "cod_nudcom";
	static String sField3  = "cod_movimiento";    
	static String sField4  = "cod_validado";

	public static boolean addRelacionComunidad(String sCodCOCLDO, String sCodNUDCOM, String sCodMovimiento)

	{
		String sMethod = "addRelacionComunidad";
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{

			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + sTable + " ("
					   + sField1  + "," 
				       + sField2  + ","              
				       + sField3  + ","
				       + sField4  +               
				       ") VALUES ('"
				       + sCodCOCLDO + "','" 
				       + sCodNUDCOM + "','" 
				       + sCodMovimiento + "','"
				       + ValoresDefecto.DEF_VALIDADO + "' )");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COCLDO: " + sCodCOCLDO);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NUDCOM: " + sCodNUDCOM);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: Movimiento: " + sCodMovimiento);
			
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

	public static boolean delRelacionComunidad(String sCodMovimiento)
	{
		String sMethod = "delRelacionComunidad";
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE " +
					"(" + sField3 + " = '" + sCodMovimiento	+ "')");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: Movimiento: " + sCodMovimiento);

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

	public static boolean existeRelacionComunidad(String sCodCOCLDO, String sCodNUDCOM, String sCodMovimiento)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "existeRelacionComunidad";

		Statement stmt = null;
		ResultSet rs = null;
		
		boolean bSalida = true;

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
				       + sField4  + " " +               
       
			"  FROM " + sTable + 
					" WHERE " +
					"(" 
					+ sField1 + " = '" + sCodCOCLDO + "' AND "
					+ sField2 + " = '" + sCodNUDCOM + "' AND "
					+ sField3 + " = '" + sCodMovimiento	+ 
					"')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

				}
			}
			if (found == false) 
			{
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}			

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COCLDO: " + sCodCOCLDO);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NUDCOM: " + sCodNUDCOM);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: Movimiento: " + sCodMovimiento);

			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());
			
			bSalida = false;
		} 
		finally 
		{
			Utils.closeResultSet(rs,sClassName,sMethod);
			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return (found && bSalida);
	}

	
	public static boolean setValidado(String sCodCOCLDO, String sCodNUDCOM, String sCodMovimiento, String sValidado)
	{
		String sMethod = "setValidado";
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
					+ sField4 + " = '"+ sValidado + 
					"' "+
					" WHERE "+
					"(" 
					+ sField1 + " = '" + sCodCOCLDO + "' AND " 
					+ sField2 + " = '" + sCodNUDCOM + "' AND "
					+ sField3 + " = '" + sCodMovimiento	+ 
					"')");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COCLDO: " + sCodCOCLDO);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NUDCOM: " + sCodNUDCOM);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: Movimiento: " + sCodMovimiento);

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
	
	public static String getValidado(String sCodCOCLDO, String sCodNUDCOM, String sCodMovimiento)
	{
		String sMethod = "getValidado";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sValidado = "";

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField4 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodCOCLDO + "' AND "
					+ sField2 + " = '" + sCodNUDCOM + "' AND "
					+ sField3 + " = '" + sCodMovimiento	+ "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sValidado = rs.getString(sField4);
					
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField4 + ": " + sValidado);


				}
			}
			if (found == false) 
			{
 
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COCLDO: " + sCodCOCLDO);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NUDCOM: " + sCodNUDCOM);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: Movimiento: " + sCodMovimiento);

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
		return sValidado;
	}
}