package com.provisiones.dal.qm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Provision;
import com.provisiones.types.ProvisionTabla;

public class QMProvisiones 
{
	static String sClassName = QMProvisiones.class.getName();
	
	static boolean bTrazas = true;

	public static final String sTable = "provisiones_tbl";

	public static final String sField1 = "nuprof_id";

	public static final String sField2 = "cod_cospat";
	public static final String sField3 = "cod_tas";
	public static final String sField4 = "valor_total";
	public static final String sField5 = "numero_gastos";
	public static final String sField6 = "fepfon";
	public static final String sField7 = "fecha_envio";
	public static final String sField8 = "cod_estado";
	public static final String sField9 = "usuario_modificacion";
	public static final String sField10 = "fecha_modificacion";

	

	public static boolean addProvision(Provision NuevaProvision)

	{
		String sMethod = "addProvision";
		Statement stmt = null;
		Connection conn = null;
		
		String sUsuario = ValoresDefecto.DEF_USUARIO;
		
		boolean bSalida = true;

		conn = ConnectionManager.OpenDBConnection();
		
		Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

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
					+ sField7 + ","
					+ sField8 + ","
					+ sField9 + ","
					+ sField10
					+ ") VALUES ('" 
					+ NuevaProvision.getsNUPROF() + "','"
					+ NuevaProvision.getsCOSPAT() + "','"
					+ NuevaProvision.getsTAS() + "','"
					+ NuevaProvision.getsValorTolal() + "','"
					+ NuevaProvision.getsNumGastos() + "','"
					+ NuevaProvision.getsFEPFON() + "','" 
					+ NuevaProvision.getsFechaValidacion() + "','" 
					+ NuevaProvision.getsCodEstado() + "','"
					+ sUsuario + "','"
					+ Utils.timeStamp() + "')");
			
			Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

		} 
		catch (SQLException ex) 
		{
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: NUPROF: " + NuevaProvision.getsNUPROF());

			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: SQLException: " + ex.getMessage());
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("[" + sClassName + "." + sMethod + "] ERROR: VendorError: " + ex.getErrorCode());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static boolean modProvision(Provision NuevaProvision, String sNUPROF) 
	{
		String sMethod = "modProvision";
		Statement stmt = null;
		boolean bSalida = true;
		Connection conn = null;
		
		String sUsuario = ValoresDefecto.DEF_USUARIO;

		conn = ConnectionManager.OpenDBConnection();
		
		Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + " SET " 
					+ sField2 + " = '" + NuevaProvision.getsCOSPAT() + "', "
					+ sField3 + " = '" + NuevaProvision.getsTAS() + "', "
					+ sField4 + " = '" + NuevaProvision.getsValorTolal() + "', " 
					+ sField5 + " = '" + NuevaProvision.getsNumGastos() + "', "
					+ sField6 + " = '" + NuevaProvision.getsFEPFON() + "', " 
					+ sField7 + " = '" + NuevaProvision.getsFechaValidacion() + "', " 
					+ sField8 + " = '" + NuevaProvision.getsCodEstado() + "', " 
					+ sField9 + " = '" + sUsuario + "', " 
					+ sField10 + " = '" + Utils.timeStamp() + "' " 					
					+ " WHERE " + sField1 + " = '" + sNUPROF + "'");

			Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

		} 
		catch (SQLException ex) 
		{
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: NUPROF: " + sNUPROF);

			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: SQLException: " + ex.getMessage());
			System.out.println("[" + sClassName + "." + sMethod + "] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: VendorError: " + ex.getErrorCode());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static boolean delProvision(String sNUPROF) 
	{
		String sMethod = "delProvision";
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		conn = ConnectionManager.OpenDBConnection();
		
		Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sNUPROF + "' )");
			
			Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: NUPROF: " + sNUPROF);

			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: SQLException: " + ex.getMessage());
			System.out.println("[" + sClassName + "." + sMethod + "] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: VendorError: " + ex.getErrorCode());
			
			bSalida = false;
		} 
		finally
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static Provision getProvision(String sNUPROF) 
	{


		String sMethod = "getProvision";

		Statement stmt = null;
		ResultSet rs = null;

		String sCOSPAT = "";
		String sTAS = "";
		String sValorTolal = "";
		String sNumGastos = "";
		String sFEPFON = "";
		String sFechaValidacion = "";
		String sValidado = "";


		PreparedStatement pstmt = null;
		boolean found = false;

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " + sField2 + "," + sField3
					+ "," + sField4 + "," + sField5 + "," + sField6 + "," + sField7 + "," + sField8 +
					" FROM " + sTable + " WHERE (" + sField1 + " = '"
					+ sNUPROF + "')");

			rs = pstmt.executeQuery();
			
			Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");



			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCOSPAT = rs.getString(sField2);
					sTAS = rs.getString(sField3);
					sValorTolal = rs.getString(sField4);
					sNumGastos = rs.getString(sField5);
					sFEPFON = rs.getString(sField6);
					sFechaValidacion = rs.getString(sField7);
					sValidado = rs.getString(sField8);

					
					Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					Utils.debugTrace(bTrazas, sClassName, sMethod, sField1 + ": " + sNUPROF);

				}
			}
			if (found == false) 
			{
				Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: NUPROF: " + sNUPROF);

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
		return new Provision(sNUPROF, sCOSPAT, sTAS, sValorTolal, sNumGastos, sFEPFON, sFechaValidacion, sValidado);
	}
	
	public static long buscaCantidadProvisionesCerradasPendientes()
	{
		String sMethod = "buscaCantidadProvisionesCerradasPendientes";

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
					"(" 
					+ sField8 + " = '" + ValoresDefecto.DEF_BAJA + "' AND "
					+ sField7 + " = '0'"+
					")");

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
	
	public static boolean existeProvision(String sNUPROF) 
	{


		String sMethod = "existeProvision";

		Statement stmt = null;
		ResultSet rs = null;

		PreparedStatement pstmt = null;
		boolean found = false;

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " + sField1  +
					" FROM " + sTable + " WHERE (" + sField1 + " = '"
					+ sNUPROF + "')");

			rs = pstmt.executeQuery();
			
			Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");



			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");
					Utils.debugTrace(bTrazas, sClassName, sMethod, sField1 + ": " + sNUPROF);

				}
			}
			if (found == false) 
			{
				Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: NUPROF: " + sNUPROF);

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
		return found;
	}
	
	public static boolean provisionCerrada(String sNUPROF) 
	{


		String sMethod = "provisionCerrada";

		Statement stmt = null;
		ResultSet rs = null;

		PreparedStatement pstmt = null;
		boolean found = false;

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " + sField1  +
					" FROM " + sTable + 
					" WHERE (" 
					+ sField1 + " = '"+ sNUPROF + "' AND "
					+ sField8 + " = '"+ ValoresDefecto.DEF_BAJA + "')");

			rs = pstmt.executeQuery();
			
			Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");



			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");
					Utils.debugTrace(bTrazas, sClassName, sMethod, sField1 + ": " + sNUPROF);

				}
			}
			if (found == false) 
			{
				Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: NUPROF: " + sNUPROF);

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
		return found;
	}
	
	public static String getProvisionAbierta(String sCodCOSPAT, String sCodTAS) 
	{


		String sMethod = "getProvisionAbierta";

		Statement stmt = null;
		ResultSet rs = null;

		String sNUPROF = "";


		PreparedStatement pstmt = null;
		boolean found = false;

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");
		
		String sQuery = "SELECT " + sField1 + 
				" FROM " + sTable + 
				" WHERE " +
				"( " + sField8 + " = '" + ValoresDefecto.DEF_ALTA + "' AND "
				+ sField2 +" = '"+ sCodCOSPAT +"' AND "
				+ sField3 +" = '"+ sCodTAS +"')";

		Utils.debugTrace(bTrazas, sClassName, sMethod, sQuery);
		
		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " + sField1 + 
					" FROM " + sTable + 
					" WHERE " +
					"( " + sField8 + " = '" + ValoresDefecto.DEF_ALTA + "' AND "
					+ sField2 +" = '"+ sCodCOSPAT +"' AND "
					+ sField3 +" = '"+ sCodTAS +"')");

			rs = pstmt.executeQuery();
			
			Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");



			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sNUPROF = rs.getString(sField1);


					
					Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					Utils.debugTrace(bTrazas, sClassName, sMethod, sField1 + ": " + sNUPROF);

				}
			}
			if (found == false) 
			{
				Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
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
		return sNUPROF;
	}
	
	public static ArrayList<ProvisionTabla> buscaProvisionesAbiertas() 
	{


		String sMethod = "buscaProvisionesAbiertas";

		Statement stmt = null;
		ResultSet rs = null;
		
		
		String sNUPROF = "";
		String sTAS = "";
		String sDTAS = "";
		String sCOSPAT = "";
		String sDCOSPAT = "";
		String sVALOR = "";
		String sGASTOS = "";

		ArrayList<ProvisionTabla> result = new ArrayList<ProvisionTabla>();


		PreparedStatement pstmt = null;
		boolean found = false;

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " 
					+ sField1 + ","
					+ sField2 + ","
					+ sField3 + ","
					+ sField4 + ","
					+ sField5 + 
					" FROM " + sTable + 
					" WHERE ( " + sField8 + " = '"
					+ ValoresDefecto.DEF_ALTA + "')");

			rs = pstmt.executeQuery();
			
			Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");



			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sNUPROF =  rs.getString(sField1);
					sCOSPAT =  rs.getString(sField2);
					sDCOSPAT =  QMCodigosControl.getDesSociedadesTitulizadas(sCOSPAT);
					sTAS =  rs.getString(sField3);
					sDTAS =  QMCodigosControl.getDesTipoActivo(sTAS);
					sVALOR =   rs.getString(sField4);
					sGASTOS =  rs.getString(sField5);

					ProvisionTabla provisionencontrada = new ProvisionTabla(sNUPROF,sCOSPAT,sDCOSPAT,sTAS,sDTAS,sVALOR,sGASTOS);
					
					result.add(provisionencontrada);
					
					Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					Utils.debugTrace(bTrazas, sClassName, sMethod, sField1 + ": " + sNUPROF);

				}
			}
			if (found == false) 
			{
				Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
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
		return result;
	}
	
	public static String getUltimaProvisionCerrada(String sCodCOSPAT) 
	{


		String sMethod = "getUltimaProvisionCerrada";

		Statement stmt = null;
		ResultSet rs = null;

		String sNUPROF = "";


		PreparedStatement pstmt = null;
		boolean found = false;

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " + sField1 + 
					" FROM " + sTable + 
					" WHERE ( " + sField8 + " = '"
					+ ValoresDefecto.DEF_BAJA + "' AND " +
							      sField2 +" = '"+ sCodCOSPAT +"') "+
					" order by " + sField1 + " desc limit 0,1 ");

			rs = pstmt.executeQuery();
			
			Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");



			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sNUPROF = rs.getString(sField1);


					
					Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					Utils.debugTrace(bTrazas, sClassName, sMethod, sField1 + ": " + sNUPROF);

				}
			}
			if (found == false) 
			{
				Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("[" + sClassName + "." + sMethod	+ "] ERROR: sCodCOSPAT: " + sCodCOSPAT);

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
		return sNUPROF;
	}
	public static String getUltimaProvision() 
	{


		String sMethod = "getUltimaProvision";

		Statement stmt = null;
		ResultSet rs = null;

		String sNUPROF = "";


		PreparedStatement pstmt = null;
		boolean found = false;

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " + sField1 + 
					" FROM " + sTable + 
					" order by " + sField1 + " desc limit 0,1 ");

			rs = pstmt.executeQuery();
			
			Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");



			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sNUPROF = rs.getString(sField1);


					
					Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					Utils.debugTrace(bTrazas, sClassName, sMethod, sField1 + ": " + sNUPROF);

				}
			}
			if (found == false) 
			{
				Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
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
		return sNUPROF;
	}

	
}
