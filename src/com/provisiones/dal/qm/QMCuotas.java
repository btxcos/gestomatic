package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Cuota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QMCuotas
{
	static String sClassName = QMCuotas.class.getName();

	static String sTable = "e2_cuotas_tbl";

	static String sField1  = "cod_cocldo";
	static String sField2  = "cod_nudcom";
	static String sField3  = "cod_cosbac";
	static String sField4  = "fipago";    
	static String sField5  = "ffpago";    
	static String sField6  = "imcuco";    
	static String sField7  = "faacta";    
	static String sField8  = "cod_ptpago";
	static String sField9  = "obtexc";

	static String sField10 = "cod_estado";

	public static boolean addCuota(Cuota NuevaCuota)

	{
		String sMethod = "addCuota";
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

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
				       + sField10  + 
				       ") VALUES ('" 
				       + NuevaCuota.getCOCLDO() + "','"
				       + NuevaCuota.getNUDCOM() + "','"
				       + NuevaCuota.getCOSBAC() + "','"
				       + NuevaCuota.getFIPAGO() + "','"
				       + NuevaCuota.getFFPAGO() + "','"
				       + NuevaCuota.getIMCUCO() + "','"
				       + NuevaCuota.getFAACTA() + "','"
				       + NuevaCuota.getPTPAGO() + "','"
				       + NuevaCuota.getOBTEXC() + "','" 
				       + ValoresDefecto.DEF_PENDIENTE + "' )");
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NUDCOM: " + NuevaCuota.getNUDCOM());
			
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
	public static boolean modCuota(Cuota NuevaCuota, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC)
	{
		String sMethod = "modCuota";
		Statement stmt = null;
		boolean bSalida = true;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField1  + " = '"+ NuevaCuota.getCOCLDO() + "', "
					+ sField2  + " = '"+ NuevaCuota.getNUDCOM() + "', "
					+ sField3  + " = '"+ NuevaCuota.getCOSBAC() + "', "
					+ sField4  + " = '"+ NuevaCuota.getFIPAGO() + "', "
					+ sField5  + " = '"+ NuevaCuota.getFFPAGO() + "', "
					+ sField6  + " = '"+ NuevaCuota.getIMCUCO() + "', "
					+ sField7  + " = '"+ NuevaCuota.getFAACTA() + "', "
					+ sField8  + " = '"+ NuevaCuota.getPTPAGO() + "', "
					+ sField9 + " = '"+ NuevaCuota.getOBTEXC() + 
					"' "+
					" WHERE " +
					"("	+ sField1  + " = '"+ sCodCOCLDO +"' AND " +
						sField2  + " = '"+ sCodNUDCOM +"' AND " +
					    sField3  + " = '"+ sCodCOSBAC + "' )");
			
		} 
		catch (SQLException ex) 
		{

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

	public static boolean delCuota(String sCodCOCLDO, String sCodNUDCOM, String sCOSBAC)
	{
		String sMethod = "delCuota";
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE " +
					"("	+ sField1  + " = '"+ sCodCOCLDO +"' AND " +
						sField2  + " = '"+ sCodNUDCOM +"' AND " +
				      sField3  + " = '"+ sCOSBAC + "' )");
		} 
		catch (SQLException ex) 
		{

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

	public static Cuota getCuota(String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getCuota";

		Statement stmt = null;
		ResultSet rs = null;

		String sCOCLDO = "";
		String sNUDCOM = "";
		String sCOSBAC = "";
		String sFIPAGO = "";
		String sFFPAGO = "";
		String sIMCUCO = "";
		String sFAACTA = "";
		String sPTPAGO = "";
		String sOBTEXC = "";

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
				       + sField9  +       
				       "  FROM " + sTable + 
					" WHERE " +
					"("	+ sField1  + " = '"+ sCodCOCLDO +"' AND " +
						sField2  + " = '"+ sCodNUDCOM +"' AND " +
				      sField3  + " = '"+ sCodCOSBAC + "' )");

			rs = pstmt.executeQuery();

			System.out.println("===================================================");
			System.out.println(sField1 + ": " + sCodCOCLDO);
			System.out.println(sField2 + ": " + sCodNUDCOM);
			System.out.println(sField3 + ": " + sCodCOSBAC);

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCOCLDO = rs.getString(sField1);
					sNUDCOM = rs.getString(sField2);
					sCOSBAC = rs.getString(sField3);
					sFIPAGO = rs.getString(sField4);
					sFFPAGO = rs.getString(sField5);
					sIMCUCO = rs.getString(sField6);
					sFAACTA = rs.getString(sField7);
					sPTPAGO = rs.getString(sField8); 
					sOBTEXC = rs.getString(sField9);
					
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
		return new Cuota(sCOCLDO, sNUDCOM, sCOSBAC, sFIPAGO, sFFPAGO, sIMCUCO, sFAACTA, sPTPAGO, sOBTEXC);
	}
	
	public static boolean tieneCuotas(String sCodCOCLDO, String sCodNUDCOM)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getCuota";

		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		boolean found = true;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "              
				       + sField3  +       
				       "  FROM " + sTable + 
					" WHERE " +
					"("	+ sField1  + " = '"+ sCodCOCLDO +"' AND " +
						sField2  + " = '"+ sCodNUDCOM +"' AND " +
				      sField10  + " <> 'B' )");

			rs = pstmt.executeQuery();

			System.out.println("===================================================");
			System.out.println(sField1 + ": " + sCodCOCLDO);
			System.out.println(sField2 + ": " + sCodNUDCOM);

			found = (rs != null);
			
			if (!found) 
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
		return found;
	}
	
/*	public static String getCuotaID(Cuota cuota)
	{
		
		String sMethod = "getCuotaID";

		Statement stmt = null;
		ResultSet rs = null;

		String sCuotaID = "";

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					+ sField1 + 
					"  FROM " + sTable + 
						" WHERE " +
						"("
					       + sField1  +" = '" + cuota.getCOCLDO() + "' AND "
					       + sField2  +" = '" + cuota.getNUDCOM() + "' AND "
					       + sField3  +" = '" + cuota.getCOSBAC() + "' AND "
					       + sField4  +" = '" + cuota.getFIPAGO() + "' AND "
					       + sField5  +" = '" + cuota.getFFPAGO() + "' AND "
					       + sField6  +" = '" + cuota.getIMCUCO() + "' AND "
					       + sField7  +" = '" + cuota.getFAACTA() + "' AND "
					       + sField8  +" = '" + cuota.getPTPAGO() + "' AND "
					       + sField9  +" = '" + cuota.getOBTEXC() + 
					       "' )");

			rs = pstmt.executeQuery();

			//System.out.println("===================================================");
			//System.out.println(sField1 + ": " + sCuotaID);

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCuotaID = rs.getString(sField1);
					System.out.println(sField1 + ": " + sCuotaID);



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
		return sCuotaID;
	}*/

	public static boolean setEstado(String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC, String sEstado)
	{
		String sMethod = "setEstado";
		Statement stmt = null;
		boolean bSalida = true;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField10 + " = '"+ sEstado + 
					"' "+
					" WHERE "+
					"(" + sField1 + " = '" + sCodCOCLDO + "' AND " +
						sField2 + " = '" + sCodNUDCOM + "' AND " +
						sField3 + " = '" + sCodCOSBAC + "' )");
			
		} 
		catch (SQLException ex) 
		{

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
	
	public static String getEstado(String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC)
	{
		String sMethod = "getEstado";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sEstado = "";

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField10 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodCOCLDO + "' AND " +
						sField2 + " = '" + sCodNUDCOM + "' AND " +
					sField3 + " = '" + sCodCOSBAC + "' )");

			rs = pstmt.executeQuery();
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sEstado = rs.getString(sField10);
					System.out.println("===================================================");
					System.out.println(sField10 + ": " + sEstado);


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
		return sEstado;
	}
	
}
