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
import com.provisiones.dal.qm.QMImpuestos;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.ImpuestoRecursoTabla;

public class QMListaImpuestos 
{
	static String sClassName = QMListaImpuestos.class.getName();
	
	static boolean bTrazas = true;

	static String sTable = "lista_impuestos_multi";
	
	static String sField1 = "cod_coaces";
	static String sField2 = "cod_nurcat";
	static String sField3 = "cod_cosbac";
	static String sField4 = "cod_movimiento";
	static String sField5 = "cod_validado";
	
	static String sField6  = "usuario_movimiento";    
	static String sField7  = "fecha_movimiento";

	public static boolean addRelacionImpuestos(String sCodCOACES, String sCodNURCAT, String sCodCOSBAC, String sCodMovimiento) 
	{
		String sMethod = "addRelacionImpuestos";
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		String sUsuario = ValoresDefecto.DEF_USUARIO;

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
			+ sField7 + 
			") " 
			+ "VALUES ('" 
			+ sCodCOACES + "','"
			+ sCodNURCAT + "','"
			+ sCodCOSBAC + "','"
			+ sCodMovimiento + "','"
			+ ValoresDefecto.DEF_PENDIENTE + "','"
		    + sUsuario + "','"
		    + Utils.timeStamp() +
			"')");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + sCodCOACES);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + sCodNURCAT);
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

	public static boolean delRelacionImpuestos(String sCodMovimiento) 
	{
		String sMethod = "delRelacionImpuestos";
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField4 + " = '" + sCodMovimiento +"')");
			
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

	public static boolean compruebaRelacionImpuestoActivo(String sCodNURCAT, String sCodCOSBAC, String sCodCOACES)
	{
		String sMethod = "compruebaRelacionImpuestoActivo";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");
		
		String sQuery = "SELECT " + sField4 + "  FROM " + sTable + 
				" WHERE " +
				"(" + sField1 + " = '" + sCodCOACES + "' " +
				" AND " + sField2 + " = '" + sCodNURCAT + "' " +
				" AND " + sField3 + " = '" + sCodCOSBAC +"' )";
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sQuery);

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField4 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodCOACES + "' " +
					" AND " + sField2 + " = '" + sCodNURCAT + "' " +
					" AND " + sField3 + " = '" + sCodCOSBAC +"' )");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField1 + ": " + sCodCOACES);
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField2 + ": " + sCodNURCAT);
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField3 + ": " + sCodCOSBAC);

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
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + sCodNURCAT);
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
		return found;
	}
	
	public static ArrayList<String>  getImpuestosPorEstado(String sEstado) 
	{
		String sMethod = "getImpuestosPorEstado";

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


			pstmt = conn.prepareStatement("SELECT " + sField4+ "  FROM " + sTable + 
					" WHERE (" + sField5 + " = '" + sEstado + "' )");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
		
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(sField4));
										
					com.provisiones.misc.Utils.debugTrace(false, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(false, sClassName, sMethod, sField5 + ": " + sEstado);
					com.provisiones.misc.Utils.debugTrace(false, sClassName, sMethod,result.get(i)); 
					
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
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: Validado: " + sEstado);

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
	
	public static ArrayList<ActivoTabla> buscaActivosAsociados(ActivoTabla activo)
	{//pendiente de coaces, de la tabla activos

		String sMethod = "buscaActivosAsociados";
		
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
		String sNURCAT = "";
		
		
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
					   + QMActivos.sField10 + ","
					   + QMActivos.sField81 + 

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
					   " FROM " + sTable + "))";
		
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
					   + QMActivos.sField10 + ","
					   + QMActivos.sField81 + 

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
					   " FROM " + sTable + "))");

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
					sNURCAT = rs.getString(QMActivos.sField81);
					
					ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, sNURCAT);
					
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
	
	public static ArrayList<ActivoTabla> buscaActivosAsociadosResueltos(ActivoTabla activo)
	{//pendiente de coaces, de la tabla activos

		String sMethod = "buscaActivosAsociadosResueltos";
		
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
		String sNURCAT = "";
		
		
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
					   + QMActivos.sField10 + ","
					   + QMActivos.sField81 + 

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
					   " WHERE ("+
					   
					   sField2 +" IN (SELECT "
					   + QMImpuestos.sField1 +
					   " FROM " + QMImpuestos.sTable +
					   " WHERE " +
					   "("
		   			   + QMImpuestos.sField7 + " = 'F' AND " 
		   			   + QMImpuestos.sField6 + " = 'S' AND "
		   			   + QMImpuestos.sField4 + " <= '"+Utils.fechaDeHoy(false)+"' AND "
		   			   + QMImpuestos.sField10 + " = '" + ValoresDefecto.DEF_ALTA + "' " +
		   			   "))"+

		   			   "AND "  

		   			   +sField3 +" IN (SELECT "
					   + QMImpuestos.sField2 +
					   " FROM " + QMImpuestos.sTable +
 					   " WHERE " +
					   "("
		   			   + QMImpuestos.sField7 + " = 'F' AND " 
		   			   + QMImpuestos.sField6 + " = 'S' AND "
		   			   + QMImpuestos.sField4 + " <= '"+Utils.fechaDeHoy(false)+"' AND "
		   			   + QMImpuestos.sField10 + " = '" + ValoresDefecto.DEF_ALTA + "' " +
		   			   "))"
		   			   
		   			   + ")))";
		
		
	
		
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
					   + QMActivos.sField10 + ","
					   + QMActivos.sField81 + 

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
					   " WHERE ("+
					   
					   sField2 +" IN (SELECT "
					   + QMImpuestos.sField1 + 
					   " FROM " + QMImpuestos.sTable +
					   " WHERE " +
					   "("
		   			   + QMImpuestos.sField7 + " = 'F' AND " 
		   			   + QMImpuestos.sField6 + " = 'S' AND "
		   			   + QMImpuestos.sField4 + " <= '"+Utils.fechaDeHoy(false)+"' AND "
		   			   + QMImpuestos.sField10 + " = '" + ValoresDefecto.DEF_ALTA + "' " +
		   			   "))"+

		   			   "AND "  

		   			   +sField3 +" IN (SELECT "
					   + QMImpuestos.sField2 + 
					   " FROM " + QMImpuestos.sTable +
					   " WHERE " +
					   "("
		   			   + QMImpuestos.sField7 + " = 'F' AND " 
		   			   + QMImpuestos.sField6 + " = 'S' AND "
		   			   + QMImpuestos.sField4 + " <= '"+Utils.fechaDeHoy(false)+"' AND "
		   			   + QMImpuestos.sField10 + " = '" + ValoresDefecto.DEF_ALTA + "' " +
		   			   "))"
		   			   
		   			   + ")))");

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
					sNURCAT = rs.getString(QMActivos.sField81);
					
					ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, sNURCAT);
					
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
	


	public static ArrayList<ImpuestoRecursoTabla> buscaImpuestosActivo(String sCodCOACES)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "buscaImpuestosActivo";

		Statement stmt = null;
		ResultSet rs = null;

		String sCOSBAC = "";
		String sDesCOSBAC = "";
		String sFEPRRE = "";
		String sFERERE = "";
		String sFEDEIN = "";
		String sBISODE = "";
		String sDesBISODE = "";
		String sBIRESO = "";
		String sDesBIRESO = "";
		String sOBTEXC = "";
		
		ArrayList<ImpuestoRecursoTabla> result = new ArrayList<ImpuestoRecursoTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		String sQuery = "SELECT "
					
					   + QMImpuestos.sField1 + ","        
					   + QMImpuestos.sField2 + ","
					   + QMImpuestos.sField3 + ","
					   + QMImpuestos.sField4 + ","
					   + QMImpuestos.sField5 + ","
					   + QMImpuestos.sField6 + ","
					   + QMImpuestos.sField7 + ","
					   + QMImpuestos.sField8 + ","
					   + QMImpuestos.sField9 +
					    

					   " FROM " + QMImpuestos.sTable + 
					   " WHERE ("

					   + QMImpuestos.sField10 + " = '" + ValoresDefecto.DEF_ALTA + "' AND "  

					   + QMImpuestos.sField1 +" IN (SELECT "
					   +  sField2 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND "  

					   + QMImpuestos.sField2 +" IN (SELECT "
					   +  sField3 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) )";					   
					   
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement("SELECT "
					
					   + QMImpuestos.sField1 + ","        
					   + QMImpuestos.sField2 + ","
					   + QMImpuestos.sField3 + ","
					   + QMImpuestos.sField4 + ","
					   + QMImpuestos.sField5 + ","
					   + QMImpuestos.sField6 + ","
					   + QMImpuestos.sField7 + ","
					   + QMImpuestos.sField8 + ","
					   + QMImpuestos.sField9 +
					    

					   " FROM " + QMImpuestos.sTable + 
					   " WHERE ("

					   + QMImpuestos.sField10 + " = '" + ValoresDefecto.DEF_ALTA + "' AND "  

					   + QMImpuestos.sField1 +" IN (SELECT "
					   +  sField2 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND "  

					   + QMImpuestos.sField2 +" IN (SELECT "
					   +  sField3 + 
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
					

					
					sCOSBAC     = rs.getString(QMImpuestos.sField2);
					sDesCOSBAC  = QMCodigosControl.getDesCOSBGA_E4(sCOSBAC);
					sFEPRRE     = Utils.recuperaFecha(rs.getString(QMImpuestos.sField3));
					sFERERE     = Utils.recuperaFecha(rs.getString(QMImpuestos.sField4));
					sFEDEIN     = Utils.recuperaFecha(rs.getString(QMImpuestos.sField5));
					sBISODE     = rs.getString(QMImpuestos.sField6);
					sDesBISODE  = QMCodigosControl.getDesBINARIA(sBISODE);
					sBIRESO     = rs.getString(QMImpuestos.sField7);
					sDesBIRESO  = QMCodigosControl.getDesBIRESO(sBIRESO);
					sOBTEXC     = rs.getString(QMImpuestos.sField9);  

					
					ImpuestoRecursoTabla impuestoencontrado = new ImpuestoRecursoTabla(
							sCOSBAC,
							sDesCOSBAC,
							sFEPRRE,
							sFERERE,
							sFEDEIN,
							sBISODE,
							sDesBISODE,
							sBIRESO,
							sDesBIRESO,
							sOBTEXC);
					
					result.add(impuestoencontrado);
					
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
	
	public static ArrayList<ImpuestoRecursoTabla> buscaDevolucionesActivo(String sCodCOACES)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "buscaDevolucionesActivo";

		Statement stmt = null;
		ResultSet rs = null;

		String sCOSBAC = "";
		String sDesCOSBAC = "";
		String sFEPRRE = "";
		String sFERERE = "";
		String sFEDEIN = "";
		String sBISODE = "";
		String sDesBISODE = "";
		String sBIRESO = "";
		String sDesBIRESO = "";
		String sOBTEXC = "";
		
		ArrayList<ImpuestoRecursoTabla> result = new ArrayList<ImpuestoRecursoTabla>();
		
		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		String sQuery = "SELECT "
					
					   + QMImpuestos.sField1 + ","        
					   + QMImpuestos.sField2 + ","
					   + QMImpuestos.sField3 + ","
					   + QMImpuestos.sField4 + ","
					   + QMImpuestos.sField5 + ","
					   + QMImpuestos.sField6 + ","
					   + QMImpuestos.sField7 + ","
					   + QMImpuestos.sField8 + ","
					   + QMImpuestos.sField9 +
					    

					   " FROM " + QMImpuestos.sTable + 
					   " WHERE ("

					   + QMImpuestos.sField7 + " = 'F' AND " 
					   + QMImpuestos.sField6 + " = 'S' AND "
					   + QMImpuestos.sField4 + " <= '"+Utils.fechaDeHoy(false)+"' AND "
					   + QMImpuestos.sField10 + " = '" + ValoresDefecto.DEF_ALTA + "' " +

					   "AND "  

					   + QMImpuestos.sField1 +" IN (SELECT "
					   +  sField2 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND "  

					   + QMImpuestos.sField2 +" IN (SELECT "
					   +  sField3 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) )";				   
					   
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement("SELECT "
					
					   + QMImpuestos.sField1 + ","        
					   + QMImpuestos.sField2 + ","
					   + QMImpuestos.sField3 + ","
					   + QMImpuestos.sField4 + ","
					   + QMImpuestos.sField5 + ","
					   + QMImpuestos.sField6 + ","
					   + QMImpuestos.sField7 + ","
					   + QMImpuestos.sField8 + ","
					   + QMImpuestos.sField9 +
					    

					   " FROM " + QMImpuestos.sTable + 
					   " WHERE ("

					   + QMImpuestos.sField7 + " = 'F' AND " 
					   + QMImpuestos.sField6 + " = 'S' AND "
					   + QMImpuestos.sField4 + " <= '"+Utils.fechaDeHoy(false)+"' AND "
					   + QMImpuestos.sField10 + " = '" + ValoresDefecto.DEF_ALTA + "' " +

					   "AND "  

					   + QMImpuestos.sField1 +" IN (SELECT "
					   +  sField2 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND "  

					   + QMImpuestos.sField2 +" IN (SELECT "
					   +  sField3 + 
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
					

					
					sCOSBAC     = rs.getString(QMImpuestos.sField2);
					sDesCOSBAC  = QMCodigosControl.getDesCOSBGA_E4(sCOSBAC);
					sFEPRRE     = Utils.recuperaFecha(rs.getString(QMImpuestos.sField3));
					sFERERE     = Utils.recuperaFecha(rs.getString(QMImpuestos.sField4));
					sFEDEIN     = Utils.recuperaFecha(rs.getString(QMImpuestos.sField5));
					sBISODE     = rs.getString(QMImpuestos.sField6);
					sDesBISODE  = QMCodigosControl.getDesBINARIA(sBISODE);
					sBIRESO     = rs.getString(QMImpuestos.sField7);
					sDesBIRESO  = QMCodigosControl.getDesBIRESO(sBIRESO);
					sOBTEXC     = rs.getString(QMImpuestos.sField9);  

					
					ImpuestoRecursoTabla impuestoencontrado = new ImpuestoRecursoTabla(
							sCOSBAC,
							sDesCOSBAC,
							sFEPRRE,
							sFERERE,
							sFEDEIN,
							sBISODE,
							sDesBISODE,
							sBIRESO,
							sDesBIRESO,
							sOBTEXC);
					
					result.add(impuestoencontrado);
					
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

	public static boolean setValidado(String sCodMovimiento, String sValidado)
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
					+ sField5 + " = '"+ sValidado + 
					"' "+
					" WHERE "+
					"(" + sField4 + " = '" + sCodMovimiento +"' )");
			
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
	
	public static String getValidado(String sCodMovimiento)
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


			pstmt = conn.prepareStatement("SELECT " + sField5 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField4 + " = '" + sCodMovimiento +"' )");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sValidado = rs.getString(sField4);

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField5 + ": " + sValidado);


				}
			}
			if (found == false) 
			{
 
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
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
	
	public static long buscaCantidadValidado(String sCodValidado)
	{
		String sMethod = "buscaCantidadValidado";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		long liNumero = 0;

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM " + sTable + 
					" WHERE " +
					"(" + sField5 + " = '" + sCodValidado + "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					liNumero = rs.getLong("COUNT(*)");
					
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod,  "Numero de registros: " + liNumero);


				}
			}
			if (found == false) 
			{
 
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: CodValidado: " + sCodValidado);

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
		return liNumero;
	}
}
