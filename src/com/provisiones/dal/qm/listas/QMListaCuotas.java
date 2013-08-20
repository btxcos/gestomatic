package com.provisiones.dal.qm.listas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMCodigosControl;
import com.provisiones.dal.qm.QMCuotas;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.CuotaTabla;

public class QMListaCuotas 
{
	static String sClassName = QMListaCuotas.class.getName();
	
	static boolean bTrazas = true;

	static String sTable = "lista_cuotas_multi";

	static String sField1 = "cod_coaces";
	static String sField2 = "cod_cocldo";
	static String sField3 = "cod_nudcom";
	static String sField4 = "cod_cosbac";
	static String sField5 = "cod_movimiento";
	static String sField6 = "cod_validado";

	public static boolean addRelacionCuotas(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC, String sCodMovimiento) 
	{
		String sMethod = "addRelacionCuotas";
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
			+ sField6 + ") " 
			+ "VALUES ('" 
			+ sCodCOACES + "','"
			+ sCodCOCLDO + "','"
			+ sCodNUDCOM + "','"
			+ sCodCOSBAC + "','"
			+ sCodMovimiento + "','"
			+ ValoresDefecto.DEF_VALIDADO + "')");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + sCodCOACES);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COCLDO: " + sCodCOCLDO);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NUDCOM: " + sCodNUDCOM);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBAC: " + sCodCOSBAC);

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

	public static boolean delRelacionCuotas(String sCodMovimiento)
	{
		String sMethod = "delRelacionCuotas";
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField5 + " = '" + sCodMovimiento +"')");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: CodMovimiento: " + sCodMovimiento);

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

	public static ArrayList<String>  getCuotas(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC) 
	{
		String sMethod = "getCuotas";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	
		
		ArrayList<String> result = new ArrayList<String>(); 
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField3 + "  FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sCodCOACES + "' " +
							"AND" + sField2 + " = '" + sCodCOCLDO + "'" +
							"AND" + sField3 + " = '" + sCodNUDCOM + "'" +
							"AND" + sField4 + " = '" + sCodCOSBAC + "' )");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(sField4));

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField1 + ": " + sCodCOACES);
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField2 + ": " + sCodCOCLDO);
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField3 + ": " + sCodNUDCOM);
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField4 + ": " + sCodCOSBAC);
					
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, result.get(i));
					
					i++;
				}
			}
			if (found == false) 
			{
				result = new ArrayList<String>(); 
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + sCodCOACES);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COCLDO: " + sCodCOCLDO);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NUDCOM: " + sCodNUDCOM);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBAC: " + sCodCOSBAC);

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
	
	public static ArrayList<String>  getCuotasPendientes(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC) 
	{
		String sMethod = "getCuotasPendientes";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	
		
		ArrayList<String> result = new ArrayList<String>(); 
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField5 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodCOACES + "' " +
							"AND" + sField2 + " = '" + sCodCOCLDO + "' " +
							"AND" + sField3 + " = '" + sCodNUDCOM + "' " +
							"AND" + sField4 + " = '" + sCodCOSBAC + "' " +
							"AND" + sField6 + " = '" + "P" + "' )");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(sField5));

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField1 + ": " + sCodCOACES);
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField2 + ": " + sCodCOCLDO);
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField3 + ": " + sCodNUDCOM);
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField4 + ": " + sCodCOSBAC);
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, result.get(i));

					i++;
				}
			}
			if (found == false) 
			{
				result = new ArrayList<String>(); 
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + sCodCOACES);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COCLDO: " + sCodCOCLDO);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NUDCOM: " + sCodNUDCOM);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBAC: " + sCodCOSBAC);

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

	public static boolean setValidado(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC, String sCodMovimiento, String sValidado)
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
					+ sField6 + " = '"+ sValidado + 
					"' "+
					" WHERE " +
					"(" + sField1 + " = '" + sCodCOACES + "' " +
					"AND" + sField2 + " = '" + sCodCOCLDO + "' " +
					"AND" + sField3 + " = '" + sCodNUDCOM + "' " +
					"AND" + sField4 + " = '" + sCodCOSBAC + "' " +
					"AND" + sField5 + " = '" + sCodMovimiento +"' )");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

			
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + sCodCOACES);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COCLDO: " + sCodCOCLDO);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NUDCOM: " + sCodNUDCOM);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBAC: " + sCodCOSBAC);

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
	
	public static String getValidado(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC, String sCodMovimiento)
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


			pstmt = conn.prepareStatement("SELECT " + sField6 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodCOACES + "' " +
					"AND" + sField2 + " = '" + sCodCOCLDO + "' " +
					"AND" + sField3 + " = '" + sCodNUDCOM + "' " +
					"AND" + sField4 + " = '" + sCodCOSBAC + "' " +
					"AND" + sField5 + " = '" + sCodMovimiento +"' )");


			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sValidado = rs.getString(sField5);
					
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField5 + ": " + sCodMovimiento);

				}
			}
			if (found == false) 
			{
 
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + sCodCOACES);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COCLDO: " + sCodCOCLDO);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NUDCOM: " + sCodNUDCOM);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBAC: " + sCodCOSBAC);

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
	
	public static ArrayList<ActivoTabla> buscaActivosComunidadDisponibles(ActivoTabla activo, String sCodCOCLDO, String sCodNUDCOM)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "buscaActivosComunidadDisponibles";

		Statement stmt = null;
		ResultSet rs = null;

		String sCOACES = "";
		String sCOPOIN = "";
		String sNOMUIN = "";
		String sNOPRAC = "";
		String sNOVIAS = "";
		String sNUPIAC = "";
		String sNUPOAC = "";
		String sNUPUAC = "";
		
		ArrayList<ActivoTabla> result = new ArrayList<ActivoTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		String sQuery = "SELECT "
					
					   + QMActivos.sField1 + ","        
					   + QMActivos.sField14 + ","
					   + QMActivos.sField11 + ","
					   + QMActivos.sField13 + ","
					   + QMActivos.sField6 + ","
					   + QMActivos.sField9 + ","
					   + QMActivos.sField7 + ","
					   + QMActivos.sField10 + 

					   " FROM " + QMActivos.sTable + 
					   " WHERE ("

					   + QMActivos.sField14 + " LIKE '%" + activo.getCOPOIN()	+ "%' AND "  
					   + QMActivos.sField11 + " LIKE '%" + activo.getNOMUIN()	+ "%' AND "  
					   + QMActivos.sField13 + " LIKE '%" + activo.getNOPRAC()	+ "%' AND "  
					   + QMActivos.sField6 + " LIKE '%" + activo.getNOVIAS()	+ "%' AND "  
					   + QMActivos.sField9 + " LIKE '%" + activo.getNUPIAC()	+ "%' AND "  
					   + QMActivos.sField7 + " LIKE '%" + activo.getNUPOAC()	+ "%' AND "  
					   + QMActivos.sField10 + " LIKE '%" + activo.getNUPUAC()	+ "%' AND "			

					   + QMActivos.sField1 +" IN (SELECT "

					   +  QMListaComunidadesActivos.sField3 + 
					   " FROM " + QMListaComunidadesActivos.sTable + 
					   " WHERE ("

					   + QMListaComunidadesActivos.sField1 + " = '" + sCodCOCLDO	+ "' AND "  
					   + QMListaComunidadesActivos.sField2 + " = '" + sCodNUDCOM	+ "' " +
					   	//	"AND "
					   //+ QMListaComunidadesActivos.sField3 + 
					   //" NOT IN  (SELECT "
					   //+  sField1 + 
					   //"  FROM " + sTable +  ")" +
					   ")))";
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement("SELECT "
					
					   + QMActivos.sField1 + ","        
					   + QMActivos.sField14 + ","
					   + QMActivos.sField11 + ","
					   + QMActivos.sField13 + ","
					   + QMActivos.sField6 + ","
					   + QMActivos.sField9 + ","
					   + QMActivos.sField7 + ","
					   + QMActivos.sField10 + 

					   " FROM " + QMActivos.sTable + 
					   " WHERE ("

					   + QMActivos.sField14 + " LIKE '%" + activo.getCOPOIN()	+ "%' AND "  
					   + QMActivos.sField11 + " LIKE '%" + activo.getNOMUIN()	+ "%' AND "  
					   + QMActivos.sField13 + " LIKE '%" + activo.getNOPRAC()	+ "%' AND "  
					   + QMActivos.sField6 + " LIKE '%" + activo.getNOVIAS()	+ "%' AND "  
					   + QMActivos.sField9 + " LIKE '%" + activo.getNUPIAC()	+ "%' AND "  
					   + QMActivos.sField7 + " LIKE '%" + activo.getNUPOAC()	+ "%' AND "  
					   + QMActivos.sField10 + " LIKE '%" + activo.getNUPUAC()	+ "%' AND "			

					   + QMActivos.sField1 +" IN (SELECT "

					   +  QMListaComunidadesActivos.sField3 + 
					   " FROM " + QMListaComunidadesActivos.sTable + 
					   " WHERE ("

					   + QMListaComunidadesActivos.sField1 + " = '" + sCodCOCLDO	+ "' AND "  
					   + QMListaComunidadesActivos.sField2 + " = '" + sCodNUDCOM	+ "' " +
					   	//	"AND "
					   //+ QMListaComunidadesActivos.sField3 + 
					   //" NOT IN  (SELECT "
					   //+  sField1 + 
					   //"  FROM " + sTable +  ")" +
					   ")))");

			


			

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					sCOACES = rs.getString(QMActivos.sField1);
					sCOPOIN = rs.getString(QMActivos.sField14);
					sNOMUIN = rs.getString(QMActivos.sField11);
					sNOPRAC = rs.getString(QMActivos.sField13);
					sNOVIAS = rs.getString(QMActivos.sField6);
					sNUPIAC = rs.getString(QMActivos.sField9);
					sNUPOAC = rs.getString(QMActivos.sField7);
					sNUPUAC = rs.getString(QMActivos.sField10);
					
					ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, "");
					
					result.add(activoencontrado);
					
					com.provisiones.misc.Utils.debugTrace(false, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(false, sClassName, sMethod, QMActivos.sField1 + ": " + sCOACES);
				}
			}
			if (found == false) 
			{
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
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
	
	public static ArrayList<CuotaTabla> buscaCuotasActivo(String sCodCOACES)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "buscaCuotasActivo";

		Statement stmt = null;
		ResultSet rs = null;

		String sCOCLDO = "";
		String sDesCOCLDO = "";
		String sNUDCOM = "";
		String sCOSBAC = "";
		String sDesCOSBAC = "";
		String sFIPAGO = "";
		String sFFPAGO = "";
		String sIMCUCO = "";
		String sFAACTA = "";
		String sPTPAGO = "";
		String sDesPTPAGO = "";
		String sOBTEXC = "";
		
		ArrayList<CuotaTabla> result = new ArrayList<CuotaTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		String sQuery = "SELECT "
					
					   + QMCuotas.sField1 + ","        
					   + QMCuotas.sField2 + ","
					   + QMCuotas.sField3 + ","
					   + QMCuotas.sField4 + ","
					   + QMCuotas.sField5 + ","
					   + QMCuotas.sField6 + ","
					   + QMCuotas.sField7 + ","
					   + QMCuotas.sField8 + ","
					   + QMCuotas.sField9 +
					    

					   " FROM " + QMCuotas.sTable + 
					   " WHERE ("

					   + QMCuotas.sField10 + " = '" + ValoresDefecto.DEF_ALTA + "' AND "
					   + QMCuotas.sField5 + " <= '"+Utils.fechaDeHoy(false)+"' AND "

					   + QMCuotas.sField1 +" IN (SELECT "
					   +  sField2 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND "  

					   + QMCuotas.sField2 +" IN (SELECT "
					   +  sField3 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND "  

					   + QMCuotas.sField3 +" IN (SELECT "
					   +  sField4 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) )";					   
					   
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement("SELECT "
					
					   + QMCuotas.sField1 + ","        
					   + QMCuotas.sField2 + ","
					   + QMCuotas.sField3 + ","
					   + QMCuotas.sField4 + ","
					   + QMCuotas.sField5 + ","
					   + QMCuotas.sField6 + ","
					   + QMCuotas.sField7 + ","
					   + QMCuotas.sField8 + ","
					   + QMCuotas.sField9 +
					    

					   " FROM " + QMCuotas.sTable + 
					   " WHERE ("

					   + QMCuotas.sField10 + " = '" + ValoresDefecto.DEF_ALTA + "' AND "  

					   + QMCuotas.sField1 +" IN (SELECT "
					   +  sField2 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND "  

					   + QMCuotas.sField2 +" IN (SELECT "
					   +  sField3 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND "  

					   + QMCuotas.sField3 +" IN (SELECT "
					   +  sField4 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) )");

			


			

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					sCOCLDO     = rs.getString(QMCuotas.sField1);
					sDesCOCLDO  = QMCodigosControl.getDesCOCLDO(sCOCLDO);
					sNUDCOM     = rs.getString(QMCuotas.sField2);
					sCOSBAC     = rs.getString(QMCuotas.sField3);
					sDesCOSBAC  = QMCodigosControl.getDesCOSBGA_E2(sCOSBAC);
					sFIPAGO     = Utils.recuperaFecha(rs.getString(QMCuotas.sField4));
					sFFPAGO     = Utils.recuperaFecha(rs.getString(QMCuotas.sField5));
					sIMCUCO     = Utils.recuperaImporte(false,rs.getString(QMCuotas.sField6));
					sFAACTA     = Utils.recuperaFecha(rs.getString(QMCuotas.sField7));
					sPTPAGO     = rs.getString(QMCuotas.sField8);
					sDesPTPAGO  = QMCodigosControl.getDesPTPAGO(sPTPAGO);
					sOBTEXC     = rs.getString(QMCuotas.sField9);  

					
					CuotaTabla cuotaencontrada = new CuotaTabla(
							sCOCLDO,
							sDesCOCLDO,
							sNUDCOM,
							sCOSBAC,
							sDesCOSBAC,
							sFIPAGO,
							sFFPAGO,
							sIMCUCO,
							sFAACTA,
							sPTPAGO,
							sDesPTPAGO,
							sOBTEXC);
					
					result.add(cuotaencontrada);
					
					com.provisiones.misc.Utils.debugTrace(false, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(false, sClassName, sMethod, sField1 + ": " + sCodCOACES);
				}
			}
			if (found == false) 
			{
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
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
	
	public static ArrayList<ActivoTabla> buscaActivosConCuotas(ActivoTabla activo)
	{//pendiente de coaces, de la tabla activos

		String sMethod = "buscaActivosConCuotas";
		
		Statement stmt = null;
		ResultSet rs = null;

		String sCOACES = "";
		String sCOPOIN = "";
		String sNOMUIN = "";
		String sNOPRAC = "";
		String sNOVIAS = "";
		String sNUPIAC = "";
		String sNUPOAC = "";
		String sNUPUAC = "";
		
		ArrayList<ActivoTabla> result = new ArrayList<ActivoTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		String sQuery = "SELECT "
					
					   + QMActivos.sField1 + ","        
					   + QMActivos.sField14 + ","
					   + QMActivos.sField11 + ","
					   + QMActivos.sField13 + ","
					   + QMActivos.sField6 + ","
					   + QMActivos.sField9 + ","
					   + QMActivos.sField7 + ","
					   + QMActivos.sField10 + 

					   " FROM " + QMActivos.sTable + 
					   " WHERE ("

					   + QMActivos.sField14 + " LIKE '%" + activo.getCOPOIN()	+ "%' AND "  
					   + QMActivos.sField11 + " LIKE '%" + activo.getNOMUIN()	+ "%' AND "  
					   + QMActivos.sField13 + " LIKE '%" + activo.getNOPRAC()	+ "%' AND "  
					   + QMActivos.sField6 + " LIKE '%" + activo.getNOVIAS()	+ "%' AND "  
					   + QMActivos.sField9 + " LIKE '%" + activo.getNUPIAC()	+ "%' AND "  
					   + QMActivos.sField7 + " LIKE '%" + activo.getNUPOAC()	+ "%' AND "  
					   + QMActivos.sField10 + " LIKE '%" + activo.getNUPUAC()	+ "%' AND "			

					   + QMActivos.sField1 +" IN (SELECT "
					   +  sField1 + 
					   " FROM " + sTable +
					   " WHERE " + sField2 + " IN (SELECT "
   					   + QMCuotas.sField1 + 
   					   " FROM " + QMCuotas.sTable +
   					   " WHERE " + QMCuotas.sField10 + " = '"+ ValoresDefecto.DEF_ALTA + "') AND " 
   					   + sField3 + " IN (SELECT "
   					   + QMCuotas.sField2 + 
   					   " FROM " + QMCuotas.sTable +
   					   " WHERE " + QMCuotas.sField10 + " = '"+ ValoresDefecto.DEF_ALTA + "')))";
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement("SELECT "
					
					   + QMActivos.sField1 + ","        
					   + QMActivos.sField14 + ","
					   + QMActivos.sField11 + ","
					   + QMActivos.sField13 + ","
					   + QMActivos.sField6 + ","
					   + QMActivos.sField9 + ","
					   + QMActivos.sField7 + ","
					   + QMActivos.sField10 + 

					   " FROM " + QMActivos.sTable + 
					   " WHERE ("

					   + QMActivos.sField14 + " LIKE '%" + activo.getCOPOIN()	+ "%' AND "  
					   + QMActivos.sField11 + " LIKE '%" + activo.getNOMUIN()	+ "%' AND "  
					   + QMActivos.sField13 + " LIKE '%" + activo.getNOPRAC()	+ "%' AND "  
					   + QMActivos.sField6 + " LIKE '%" + activo.getNOVIAS()	+ "%' AND "  
					   + QMActivos.sField9 + " LIKE '%" + activo.getNUPIAC()	+ "%' AND "  
					   + QMActivos.sField7 + " LIKE '%" + activo.getNUPOAC()	+ "%' AND "  
					   + QMActivos.sField10 + " LIKE '%" + activo.getNUPUAC()	+ "%' AND "			

					   + QMActivos.sField1 +" IN (SELECT "
					   +  sField1 + 
					   " FROM " + sTable +
					   " WHERE " + 
					   
					   sField2 + " IN (SELECT "
					   + QMCuotas.sField1 + 
   					   " FROM " + QMCuotas.sTable +
   					   " WHERE " + QMCuotas.sField10 + " = '"+ ValoresDefecto.DEF_ALTA + "') AND " 

   					   + sField3 + " IN (SELECT "
   					   + QMCuotas.sField2 + 
   					   " FROM " + QMCuotas.sTable +
   					   " WHERE " + QMCuotas.sField10 + " = '"+ ValoresDefecto.DEF_ALTA + "')))");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					sCOACES = rs.getString(QMActivos.sField1);
					sCOPOIN = rs.getString(QMActivos.sField14);
					sNOMUIN = rs.getString(QMActivos.sField11);
					sNOPRAC = rs.getString(QMActivos.sField13);
					sNOVIAS = rs.getString(QMActivos.sField6);
					sNUPIAC = rs.getString(QMActivos.sField9);
					sNUPOAC = rs.getString(QMActivos.sField7);
					sNUPUAC = rs.getString(QMActivos.sField10);
					
					ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, "");
					
					result.add(activoencontrado);
					
					com.provisiones.misc.Utils.debugTrace(false, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(false, sClassName, sMethod, QMActivos.sField1 + ": " + sCOACES);
				}
			}
			if (found == false) 
			{
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
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
