package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ReferenciaCatastral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QMReferencias
{
	static String sClassName = QMReferencias.class.getName();
	
	static boolean bTrazas = true;

	public static final String sTable = "e3_referencias_tbl";

	public static final String sField1  = "nurcat_id";    
	public static final String sField2  = "tircat";    
	public static final String sField3 = "enemis";    
	public static final String sField4 = "cotexa";    
	public static final String sField5 = "obtexc";

	//Ampliacion de valor catastral
	public static final String sField6 = "imvsue";    
	public static final String sField7 = "imcata";    
	public static final String sField8 = "fereca";
	
	public static final String sField9 = "cod_estado";

	public static boolean addReferenciaCatastral(ReferenciaCatastral NuevaReferenciaCatastral)

	{
		String sMethod = "addReferenciaCatastral";
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

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
				       + sField9  +              
				       ") VALUES ('" 
				       + NuevaReferenciaCatastral.getNURCAT() + "','"
				       + NuevaReferenciaCatastral.getTIRCAT() + "','"
				       + NuevaReferenciaCatastral.getENEMIS() + "','"
				       + NuevaReferenciaCatastral.getCOTEXA() + "','"
				       + NuevaReferenciaCatastral.getOBTEXC() + "','"
				       
				       //Ampliacion de valor catastral
				       + NuevaReferenciaCatastral.getIMVSUE() + "','"
				       + NuevaReferenciaCatastral.getIMCATA() + "','"
				       + NuevaReferenciaCatastral.getFERECA() + "','"

				       + ValoresDefecto.DEF_ALTA + "' )");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + NuevaReferenciaCatastral.getNURCAT());
			
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
	public static boolean modReferenciaCatastral(ReferenciaCatastral NuevaReferenciaCatastral, String sCodNURCAT)
	{
		String sMethod = "modReferenciaCatastral";
	
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
					+ sField1  + " = '"+ NuevaReferenciaCatastral.getNURCAT() + "', "
					+ sField2  + " = '"+ NuevaReferenciaCatastral.getTIRCAT() + "', "
					+ sField3  + " = '"+ NuevaReferenciaCatastral.getENEMIS() + "', "
					+ sField4  + " = '"+ NuevaReferenciaCatastral.getCOTEXA() + "', "
					+ sField5  + " = '"+ NuevaReferenciaCatastral.getOBTEXC() + "', "
					
					//Ampliacion de valor catastral
					+ sField6  + " = '"+ NuevaReferenciaCatastral.getIMVSUE() + "', "
					+ sField7  + " = '"+ NuevaReferenciaCatastral.getIMCATA() + "', "
					+ sField8  + " = '"+ NuevaReferenciaCatastral.getFERECA() +
					"' "+
					" WHERE "
					+ sField1 + " = '"+ sCodNURCAT +"'");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + NuevaReferenciaCatastral.getNURCAT());

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

	public static boolean delReferenciaCatastral(String sCodNURCAT)
	{
		String sMethod = "delReferenciaCatastral";
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sCodNURCAT + "' )");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + sCodNURCAT);

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

	public static ReferenciaCatastral getReferenciaCatastral(String sCodNURCAT)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getReferenciaCatastral";

		Statement stmt = null;
		ResultSet rs = null;

		String sNURCAT = "";
		String sTIRCAT = "";
		String sENEMIS = "";
		String sCOTEXA = "";
		String sOBTEXC = "";
		
		//Ampliacion de valor catastral
		String sIMVSUE = "";
		String sIMCATA = "";
		String sFERECA = "";

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
				       + sField1  + ","
				       + sField2  + ","              
				       + sField3  + ","              
				       + sField4  + ","              
				       + sField5  +  

				       //Ampliacion de valor catastral
				       ","              
				       + sField6  + ","              
				       + sField7  + ","              
				       + sField8  +
       
			"  FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sCodNURCAT	+ "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

  					sNURCAT = rs.getString(sField1); 
  					sTIRCAT = rs.getString(sField2); 
  					sENEMIS = rs.getString(sField3);
  					sCOTEXA = rs.getString(sField4);
  					sOBTEXC = rs.getString(sField5);

  					//Ampliacion de valor catastral
  					sIMVSUE = rs.getString(sField6);
  					sIMCATA = rs.getString(sField7);
  					sFERECA = rs.getString(sField8);
  					
  					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

  					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod,sField1 + ": " + sCodNURCAT);
				}
			}
			if (found == false) 
			{
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + sCodNURCAT);

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
		return new ReferenciaCatastral(sNURCAT, sTIRCAT, sENEMIS, sCOTEXA, sOBTEXC, sIMVSUE, sIMCATA, sFERECA);
	}
	
	public static boolean existeReferenciaCatastral(String sCodNURCAT)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getReferenciaCatastral";

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

			pstmt = conn.prepareStatement("SELECT "
				       + sField1  +              
			"  FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sCodNURCAT	+ "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

  					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

  					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod,sField1 + ": " + sCodNURCAT);
				}
			}
			if (found == false) 
			{
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + sCodNURCAT);

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
		return found;
	}

	public static boolean setEstado(String sCodNURCAT, String sEstado)
	{
		String sMethod = "setEstado";
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
					+ sField9 + " = '"+ sEstado + 
					"' "+
					" WHERE "+
					"(" + sField1 + " = '" + sCodNURCAT + "')");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + sCodNURCAT);

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
	
	public static String getEstado(String sCodNURCAT)
	{
		String sMethod = "getEstado";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sEstado = "";

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField9 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodNURCAT + "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sEstado = rs.getString(sField9);

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField9 + ": " + sEstado);


				}
			}
			if (found == false) 
			{
 
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + sCodNURCAT);

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
		return sEstado;
	}
	
}
