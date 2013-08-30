package com.provisiones.dal.qm.listas;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.ReferenciaTabla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QMListaReferencias
{
	static String sClassName = QMListaReferencias.class.getName();
	
	static boolean bTrazas = true;

	static String sTable = "lista_referencias_multi";

	static String sField1  = "cod_nurcat";
	static String sField2  = "cod_coaces";    
	static String sField3  = "cod_movimiento";    
	static String sField4  = "cod_validado";

	public static boolean addRelacionReferencia(String sCodNURCAT, String sCodCOACES, String sCodMovimiento)

	{
		String sMethod = "addRelacionReferencia";
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
				       + sCodNURCAT + "','" 
				       + sCodCOACES + "','"
				       + sCodMovimiento + "','"
				       + ValoresDefecto.DEF_PENDIENTE + "' )");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{

			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + sCodNURCAT);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + sCodCOACES);
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

	public static boolean delRelacionReferencia(String sCodMovimiento)
	{
		String sMethod = "delRelacionReferencia";
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

	public static boolean existeRelacionReferencia(String sCodNURCAT, String sCodCOACES, String sCodMovimiento)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "existeRelacionReferencia";

		Statement stmt = null;
		ResultSet rs = null;

		PreparedStatement pstmt = null;
		boolean found = false;

		boolean bSalida = true; 
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
				       + sField4  +               
       
			"  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodNURCAT + "' AND " 
					+ sField2 + " = '" + sCodCOACES + "' AND " 
					+ sField3 + " = '" + sCodMovimiento	+ "')");

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
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + sCodNURCAT);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + sCodCOACES);
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
	
	public static boolean compruebaRelacionReferenciaActivo(String sCodNURCAT, String sCodCOACES)
	{
		
		String sMethod = "compruebaRelacionReferenciaActivo";

		Statement stmt = null;
		ResultSet rs = null;

		PreparedStatement pstmt = null;
		boolean found = false;

		boolean bSalida = true; 
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
				       + sField4  +               
       
			"  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodNURCAT + "' AND " 
					+ sField2 + " = '" + sCodCOACES + "')");

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
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + sCodNURCAT);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + sCodCOACES);

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
	
	public static ArrayList<String>  getReferenciasPorEstado(String sEstado) 
	{
		String sMethod = "getReferenciasPorEstado";

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


			pstmt = conn.prepareStatement("SELECT " + sField3+ "  FROM " + sTable + 
					" WHERE (" + sField4 + " = '" + sEstado + "' )");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
		
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(sField3));
										
					com.provisiones.misc.Utils.debugTrace(false, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(false, sClassName, sMethod, sField4 + ": " + sEstado);
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

	public static boolean setValidado(String sCodNURCAT, String sCodCOACES, String sCodMovimiento, String sValidado)
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
					"(" + sField1 + " = '" + sCodNURCAT + "' AND " 
					+ sField2 + " = '" + sCodCOACES + "' AND " 
					+ sField3 + " = '" + sCodMovimiento	+ "')");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + sCodNURCAT);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + sCodCOACES);
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
	
	public static String getValidado(String sCodNURCAT, String sCodCOACES, String sCodMovimiento)
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
					"(" + sField1 + " = '" + sCodNURCAT + "' AND " 
					+ sField2 + " = '" + sCodCOACES + "' AND " 
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
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + sCodNURCAT);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + sCodCOACES);
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
	
	public static ArrayList<ReferenciaTabla> buscaReferenciasActivo(String sCodCOACES)
	{
		
		String sMethod = "buscaActivosComunidad";

		Statement stmt = null;
		ResultSet rs = null;

		String sNURCAT = "";
		String sTIRCAT = "";
		String sENEMIS = "";
		String sOBTEXC = "";
		
		String sIMVSUE = "";
		String sIMCATA = "";
		String sFERECA = "";
		
		ArrayList<ReferenciaTabla> result = new ArrayList<ReferenciaTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					   + QMReferencias.sField1 + ","        
					   + QMReferencias.sField2 + ","
					   + QMReferencias.sField3 + ","
					   + QMReferencias.sField5 + 

					   //Ampliacion de valor catastral
					   ","
					   + QMReferencias.sField6 + ","
					   + QMReferencias.sField7 + ","
					   + QMReferencias.sField8 + 
					   
					   "  FROM " + QMReferencias.sTable + 
					   " WHERE " 
					   + QMReferencias.sField1 + " IN " +
					   "(SELECT " + sField1 + 
					   " FROM " + sTable +
					   " WHERE "
					   + sField2 +  " = '" + sCodCOACES	+ "')");
			

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					sNURCAT = rs.getString(QMReferencias.sField1);
					sTIRCAT = rs.getString(QMReferencias.sField2);
					sENEMIS = rs.getString(QMReferencias.sField3);
					sOBTEXC = rs.getString(QMReferencias.sField5);

					//Ampliacion de valor catastral
					sIMVSUE = Utils.recuperaImporte(false,rs.getString(QMReferencias.sField6));
					sIMCATA = Utils.recuperaImporte(false,rs.getString(QMReferencias.sField7));
					sFERECA = Utils.recuperaFecha(rs.getString(QMReferencias.sField8));
					
					ReferenciaTabla referenciaencontrada = new ReferenciaTabla(sNURCAT, sTIRCAT, sENEMIS, sOBTEXC

							//Ampliacion de valor catastral
							, sIMVSUE, sIMCATA, sFERECA
							
							);
					
					result.add(referenciaencontrada);
					
					com.provisiones.misc.Utils.debugTrace(false, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(false, sClassName, sMethod, sField3 + ": " + sCodCOACES);
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
	
	
	public static ArrayList<ActivoTabla> buscaActivosNoAsociados(ActivoTabla activo)
	{//pendiente de coaces, de la tabla activos

		String sMethod = "buscaActivosNoAsociados";
		
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

					   + QMActivos.sField1 +" NOT IN (SELECT "
					   +  sField2 + 
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

					   + QMActivos.sField1 +" NOT IN (SELECT "
					   +  sField2 + 
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
					   +  sField2 + 
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
					   +  sField2 + 
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
	

	public static boolean activoAsociado(String sCodCOACES)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "activoAsociado";

		Statement stmt = null;
		ResultSet rs = null;
		
		boolean bSalida = true;

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		String sQuery = "";
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sQuery);
		
		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
				    + sField1  +
				    "  FROM " + sTable + 
				    " WHERE "
				    + sField2 + " = '" + sCodCOACES + "'");

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
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + sCodCOACES);

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
	
	public static String referenciaAsociada(String sCodCOACES)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "referenciaAsociada";

		Statement stmt = null;
		ResultSet rs = null;
		
		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		String sReferencia = "";
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		String sQuery = "SELECT "
			    + sField1  +
			    "  FROM " + sTable + 
			    " WHERE "
			    + sField2 + " = '" + sCodCOACES + "'";
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sQuery);
		
		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
				    + sField1  +
				    "  FROM " + sTable + 
				    " WHERE "
				    + sField2 + " = '" + sCodCOACES + "'");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					sReferencia = rs.getString(sField1);

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
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + sCodCOACES);

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
		return sReferencia;
	}
}