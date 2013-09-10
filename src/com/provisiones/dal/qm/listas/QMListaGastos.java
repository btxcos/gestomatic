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
import com.provisiones.dal.qm.QMGastos;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.GastoTabla;

public class QMListaGastos 
{
	static String sClassName = QMListaGastos.class.getName();
	
	static boolean bTrazas = true;

	static String sTable = "lista_gastos_multi";

	static String sField1 = "cod_coaces";
	static String sField2 = "cod_cogrug";    
	static String sField3 = "cotpga";    
	static String sField4 = "cosbga";    
	static String sField5 = "fedeve";
	
	static String sField6 = "cod_nuprof";
	static String sField7 = "cod_movimiento";

	static String sField8 = "cod_validado";
	
	static String sField9 = "usuario_movimiento";    
	static String sField10 = "fecha_movimiento";


	public static boolean addRelacionGasto(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE, String sCodNUPROF, String sCodGasto) 
	{
		String sMethod = "addRelacionGastos";
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		String sUsuario = ValoresDefecto.DEF_USUARIO;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + sTable + 
					" (" + sField1 + "," 
						+ sField2 + "," 
						+ sField3 + "," 
						+ sField4 + ","
						+ sField5 + "," 
						+ sField6 + "," 
						+ sField7 + "," 
						+ sField8 + ","
						+ sField9 + "," 
						+ sField10 +						
						") " +
					"VALUES ('" 
						+ sCodCOACES + "','"
						+ sCodCOGRUG + "','"
						+ sCodCOTPGA + "','" 
						+ sCodCOSBGA + "','"
						+ sFEDEVE + "','"
						+ sCodNUPROF + "','"
						+ sCodGasto + "','"
						+ ValoresDefecto.DEF_PENDIENTE + "','"
					    + sUsuario + "','"
					    + Utils.timeStamp() +
						"')");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + sCodCOACES);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRUG: " + sCodCOGRUG);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COTPGA: " + sCodCOTPGA);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBGA: " + sCodCOSBGA);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + sCodCOACES);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: FEDEVE: " + sFEDEVE);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: Gasto: " + sCodGasto);

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

	public static boolean delRelacionGasto(String sCodGasto) 
	{
		String sMethod = "delRelacionGastos";
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField7 + " = '" + sCodGasto +"')");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: Gasto: " + sCodGasto);

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
	
	public static String getProvisionDeGasto(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getProvisionDeGasto";

		Statement stmt = null;
		ResultSet rs = null;

		String sProvision = "";

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");
		
		String sQuery = "SELECT "
				+ sField6 + 
				"  FROM " + sTable + 
					" WHERE " +
					"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
					sField2  + " = '"+ sCodCOGRUG +"' AND " +
					sField3  + " = '"+ sCodCOTPGA +"' AND " +
					sField4  + " = '"+ sCodCOSBGA +"' AND " +
				    sField5  + " = '"+ sFEDEVE + "' )";
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sQuery);

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					+ sField6 + 
					"  FROM " + sTable + 
						" WHERE " +
						"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
						sField2  + " = '"+ sCodCOGRUG +"' AND " +
						sField3  + " = '"+ sCodCOTPGA +"' AND " +
						sField4  + " = '"+ sCodCOSBGA +"' AND " +
					    sField5  + " = '"+ sFEDEVE + "' )");


			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sProvision = rs.getString(sField6);
					
					
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField6 + ": " + sProvision);

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
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRUG: " + sCodCOGRUG);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COTPGA: " + sCodCOTPGA);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBGA: " + sCodCOSBGA);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: FEDEVE: " + sFEDEVE);

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
		return sProvision;
	}
	
	public static String getProvisionDeMovimiento(String sCodGasto)
	{
		String sMethod = "getProvisionDeMovimiento";

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
					"("	+ sField7  + " = '"+ sCodGasto +"' )");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sValidado = rs.getString(sField4);

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod,sField3 + ": " + sCodGasto);
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod,sField4 + ": " + sValidado);


				}
			}
			if (found == false) 
			{
 
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: Gasto: " + sCodGasto);

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
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField7+ "  FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sCodCOACES + "' )");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
		
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(sField7));
										
					com.provisiones.misc.Utils.debugTrace(false, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(false, sClassName, sMethod, sField1 + ": " + sCodCOACES);
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
	
	public static ArrayList<String>  getGastosPorEstado(String sEstado) 
	{
		String sMethod = "getGastosPorEstado";

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


			pstmt = conn.prepareStatement("SELECT " + sField7+ "  FROM " + sTable + 
					" WHERE (" + sField8 + " = '" + sEstado + "' )");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
		
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(sField7));
										
					com.provisiones.misc.Utils.debugTrace(false, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(false, sClassName, sMethod, sField8 + ": " + sEstado);
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
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField7 + "  FROM " + sTable + 
					" WHERE (" + sField6 + " = '" + sCodNUPROF + "' )");

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
					com.provisiones.misc.Utils.debugTrace(false, sClassName, sMethod, sField2 + ": " + sCodNUPROF);
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
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NUPROF: " + sCodNUPROF);

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


	
	public static boolean setValidado(String sCodGasto, String sValidado)
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
					+ sField8 + " = '"+ sValidado + 
					"' "+
					" WHERE "
					+ sField7 + " = '"+ sCodGasto +"'");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: Gasto: " + sCodGasto);

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
	
	public static String getValidado(String sCodGasto)
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


			pstmt = conn.prepareStatement("SELECT " + sField8 + "  FROM " + sTable + 
					" WHERE (" + sField7 + " = '" + sCodGasto + "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sValidado = rs.getString(sField8);

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod,sField7 + ": " + sCodGasto);
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod,sField8 + ": " + sValidado);


				}
			}
			if (found == false) 
			{
 
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: Gasto: " + sCodGasto);

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
					"(" + sField8 + " = '" + sCodValidado + "')");

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
	
	public static ArrayList<ActivoTabla> buscaActivosConGastos(ActivoTabla activo)
	{//pendiente de coaces, de la tabla activos

		String sMethod = "buscaActivosConGastos";
		
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
					   " WHERE " + 
					   
					   sField2 + " IN (SELECT "
					   + QMGastos.sField2 + 
   					   " FROM " + QMGastos.sTable +
   					   " WHERE " + QMGastos.sField34 + " = '"+ ValoresDefecto.DEF_GASTO_ESTIMADO + "' " +
   					   		" OR "+ QMGastos.sField34 + " = '"+ ValoresDefecto.DEF_GASTO_CONOCIDO + "' ) AND " 

   					   + sField3 + " IN (SELECT "
					   + QMGastos.sField3 + 
   					   " FROM " + QMGastos.sTable +
   					   " WHERE " + QMGastos.sField34 + " = '"+ ValoresDefecto.DEF_GASTO_ESTIMADO + "' " +
   					   		" OR "+ QMGastos.sField34 + " = '"+ ValoresDefecto.DEF_GASTO_CONOCIDO + "' ) AND "

   					   + sField4 + " IN (SELECT "
					   + QMGastos.sField4 + 
   					   " FROM " + QMGastos.sTable +
   					   " WHERE " + QMGastos.sField34 + " = '"+ ValoresDefecto.DEF_GASTO_ESTIMADO + "' " +
   					   		" OR "+ QMGastos.sField34 + " = '"+ ValoresDefecto.DEF_GASTO_CONOCIDO + "' ) AND "
   					   
   					   + sField5 + " IN (SELECT "
   					   + QMGastos.sField6 + 
   					   " FROM " + QMGastos.sTable +
   					   " WHERE " + QMGastos.sField34 + " = '"+ ValoresDefecto.DEF_GASTO_ESTIMADO + "' " +
   					   		" OR "+ QMGastos.sField34 + " = '"+ ValoresDefecto.DEF_GASTO_CONOCIDO + "' )" +
   					   "))";
		
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
					   + QMGastos.sField2 + 
   					   " FROM " + QMGastos.sTable +
   					   " WHERE " + QMGastos.sField34 + " = '"+ ValoresDefecto.DEF_GASTO_ESTIMADO + "' " +
   					   		" OR "+ QMGastos.sField34 + " = '"+ ValoresDefecto.DEF_GASTO_CONOCIDO + "' ) AND " 

   					   + sField3 + " IN (SELECT "
					   + QMGastos.sField3 + 
   					   " FROM " + QMGastos.sTable +
   					   " WHERE " + QMGastos.sField34 + " = '"+ ValoresDefecto.DEF_GASTO_ESTIMADO + "' " +
   					   		" OR "+ QMGastos.sField34 + " = '"+ ValoresDefecto.DEF_GASTO_CONOCIDO + "' ) AND "

   					   + sField4 + " IN (SELECT "
					   + QMGastos.sField4 + 
   					   " FROM " + QMGastos.sTable +
   					   " WHERE " + QMGastos.sField34 + " = '"+ ValoresDefecto.DEF_GASTO_ESTIMADO + "' " +
   					   		" OR "+ QMGastos.sField34 + " = '"+ ValoresDefecto.DEF_GASTO_CONOCIDO + "' ) AND "
   					   
   					   + sField5 + " IN (SELECT "
   					   + QMGastos.sField6 + 
   					   " FROM " + QMGastos.sTable +
   					   " WHERE " + QMGastos.sField34 + " = '"+ ValoresDefecto.DEF_GASTO_ESTIMADO + "' " +
   					   		" OR "+ QMGastos.sField34 + " = '"+ ValoresDefecto.DEF_GASTO_CONOCIDO + "' )" +
   					   "))");

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
	
	public static ArrayList<GastoTabla> buscaGastosActivo(String sCodCOACES)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "buscaGastosActivo";

		Statement stmt = null;
		ResultSet rs = null;

		String sCOACES = "";
		String sCOGRUG = "";
		String sCOTPGA = "";
		String sCOSBGA = "";
		String sDCOSBGA = "";
		String sPTPAGO = "";
		String sDPTPAGO = "";
		String sFEDEVE = "";
		String sCOSIGA = "";
		String sDCOSIGA = "";
		String sIMNGAS = "";
		
		ArrayList<GastoTabla> result = new ArrayList<GastoTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		String sQuery = "SELECT "
					
					   + QMGastos.sField1 + ","        
					   + QMGastos.sField2 + ","
					   + QMGastos.sField3 + ","
					   + QMGastos.sField4 + ","
					   + QMGastos.sField5 + ","
					   + QMGastos.sField6 + ","
					   + QMGastos.sField10 + ","
					   + QMGastos.sField15 + ","
					   + QMGastos.sField16+
					    

					   " FROM " + QMGastos.sTable + 
					   " WHERE (" +
					   "("
					   + QMGastos.sField34 + " = '" + ValoresDefecto.DEF_GASTO_ESTIMADO + "' OR "
					   + QMGastos.sField34 + " = '" + ValoresDefecto.DEF_GASTO_CONOCIDO + 					   
					   "') AND "					   
					   
					   + QMGastos.sField6 + " <= '"+Utils.fechaDeHoy(false)+"' AND "

					   + QMGastos.sField1 +" IN (SELECT "
					   +  sField1 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND "  

					   + QMGastos.sField2 +" IN (SELECT "
					   +  sField2 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND "  

					   + QMGastos.sField3 +" IN (SELECT "
					   +  sField3 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND " 
					   
					   + QMGastos.sField4 +" IN (SELECT "
					   +  sField4 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND "					   
					   
					   
					   + QMGastos.sField6 +" IN (SELECT "
					   +  sField5 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) )";					   
					   
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement("SELECT "
					
					   + QMGastos.sField1 + ","        
					   + QMGastos.sField2 + ","
					   + QMGastos.sField3 + ","
					   + QMGastos.sField4 + ","
					   + QMGastos.sField5 + ","
					   + QMGastos.sField6 + ","
					   + QMGastos.sField10 + ","
					   + QMGastos.sField15 + ","
					   + QMGastos.sField16+
					    

					   " FROM " + QMGastos.sTable + 
					   " WHERE (" +
					   "("
					   + QMGastos.sField34 + " = '" + ValoresDefecto.DEF_GASTO_ESTIMADO + "' OR "
					   + QMGastos.sField34 + " = '" + ValoresDefecto.DEF_GASTO_CONOCIDO + 
			   
					   "') AND "					   
					   
					   + QMGastos.sField6 + " <= '"+Utils.fechaDeHoy(false)+"' AND "

					   + QMGastos.sField1 +" IN (SELECT "
					   +  sField1 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND "  

					   + QMGastos.sField2 +" IN (SELECT "
					   +  sField2 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND "  

					   + QMGastos.sField3 +" IN (SELECT "
					   +  sField3 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND " 
					   
					   + QMGastos.sField4 +" IN (SELECT "
					   +  sField4 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND "					   
					   
					   
					   + QMGastos.sField6 +" IN (SELECT "
					   +  sField5 + 
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
					   
					
					sCOACES  = rs.getString(QMGastos.sField1);
					sCOGRUG  = rs.getString(QMGastos.sField2);
					sCOTPGA  = rs.getString(QMGastos.sField3);
					sCOSBGA  = rs.getString(QMGastos.sField4);
					sDCOSBGA = QMCodigosControl.getDesCOSBGA(sCOGRUG,sCOTPGA,sCOSBGA);
					sPTPAGO  = rs.getString(QMGastos.sField5);
					sDPTPAGO = QMCodigosControl.getDesPTPAGO(sPTPAGO);
					sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.sField6));
					sCOSIGA  = rs.getString(QMGastos.sField10);
					sDCOSIGA = "";//QMCodigosControl.
					sIMNGAS  = rs.getString(QMGastos.sField16)+Utils.recuperaImporte(false,rs.getString(QMGastos.sField15));
							

 

					
					GastoTabla gastoencontrado = new GastoTabla(
							sCOACES,
							sCOGRUG,
							sCOTPGA,
							sCOSBGA,
							sDCOSBGA,
							sPTPAGO,
							sDPTPAGO,
							sFEDEVE,
							sCOSIGA,
							sDCOSIGA,
							sIMNGAS);
					
					result.add(gastoencontrado);
					
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
	
	
}
