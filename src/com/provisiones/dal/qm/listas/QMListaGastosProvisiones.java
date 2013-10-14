package com.provisiones.dal.qm.listas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

public class QMListaGastosProvisiones 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaGastosProvisiones.class.getName());
	
	static String sTable = "lista_gastos_provisiones_multi";

	static String sField1 = "cod_gasto";
	static String sField2 = "cod_nuprof";
	
	static String sField3 = "usuario_alta";    
	static String sField4 = "fecha_alta";


	public static boolean addRelacionGastoProvision(String sCodGasto, String sCodNUPROF) 
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		String sUsuario = ValoresDefecto.DEF_USUARIO;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + sTable + 
					" (" + sField1 + "," 
						+ sField2 + "," 
						+ sField3 + "," 
						+ sField4 +						
						") " +
					"VALUES ('" 
						+ sCodGasto + "','"
						+ sCodNUPROF + "','"
					    + sUsuario + "','"
					    + Utils.timeStamp() +
						"')");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: GASTO:|{}|",sCodGasto);
			logger.error("ERROR: PROVISION:|{}|",sCodNUPROF);

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	
	public static boolean delRelacionGastoProvision(String sCodGasto) 
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sCodGasto +"')");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: GASTO:|{}|",sCodGasto);

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	
	public static boolean existeRelacionGastoProvision(String sCodGasto, String sCodNUPROF)
	{
		Statement stmt = null;
		ResultSet rs = null;

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
						+ sField1 + 
						" FROM " 
						+ sTable + 
						" WHERE ("	
						+ sField1  + " = '"+ sCodGasto +"' AND " +
					    sField2  + " = '"+ sCodNUPROF + "' )");


			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: GASTO:|{}|",sCodGasto);
			logger.error("ERROR: PROVISION:|{}|",sCodNUPROF);

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return found;
	}
	
	public static String getProvisionDeGasto(String sCodGasto)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sCodNUPROF = "";

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
				+ sField2 + 
				" FROM " 
				+ sTable + 
				" WHERE "
				+ sField1  + " = '"+ sCodGasto +"'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					+ sField2 + 
					" FROM " 
					+ sTable + 
					" WHERE "
					+ sField1  + " = '"+ sCodGasto +"'");


			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCodNUPROF = rs.getString(sField2);
					
					
					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",sField2,sCodNUPROF);

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: GASTO:|{}|",sCodGasto);

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return sCodNUPROF;
	}
	
	public static String getProvisionDeMovimiento(String sCodMovimiento)
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sProvision = "";

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " 
					+ sField2 + 
					" FROM " 
					+ sTable + 
					" WHERE "
					+ sField1 +
					"IN (SELECT "
					+ QMListaGastos.sField1 +
					" FROM " 
					+ QMListaGastos.sTable +
					" WHERE "
					+ QMListaGastos.sField2  + " = '"+ sCodMovimiento +"' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sProvision = rs.getString(sField2);

					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",QMListaGastos.sField2,sCodMovimiento);
					logger.debug("{}:|{}|",sField2,sProvision);
				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: Gasto:|{}|",sCodMovimiento);

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		ConnectionManager.CloseDBConnection(conn);
		return sProvision;
	}



	public static ArrayList<String>  getGastosPorProvision(String sCodNUPROF) 
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	
		
		ArrayList<String> result = new ArrayList<String>(); 
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField1 + "  FROM " + sTable + 
					" WHERE " 
					+ sField2 + " = '" + sCodNUPROF + "'");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(sField1));

					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",sField2,sCodNUPROF);
					logger.debug("{}:|{}|",sField1,result.get(i));
					
					i++;
				}
			}
			if (found == false) 
			{
				result = new ArrayList<String>(); 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: NUPROF:|{}|",sCodNUPROF);

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		ConnectionManager.CloseDBConnection(conn);
		return result;
	}

	public static ArrayList<String> buscaGastosSinValidarEnProvision(String sNUPROF) 
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		String sGastoID = "0";

		ArrayList<String> result = new ArrayList<String>();

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ sField1 + 
				" FROM " + sTable + 
				" WHERE ( " 
				+ sField2 + " = '"+ sNUPROF + "' AND "
				+ sField1+ 
				" IN (SELECT "
				+ QMListaGastos.sField1 +
				" FROM " + QMListaGastos.sTable +
				" WHERE  " 
				+ QMListaGastos.sField3 + " NOT IN ('"
				+ValoresDefecto.DEF_MOVIMIENTO_VALIDADO+"','"
				+ValoresDefecto.DEF_MOVIMIENTO_RESUELTO+
				"')))";
		
		logger.debug(sQuery);
		
		

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " 
					+ sField1 + 
					" FROM " + sTable + 
					" WHERE ( " 
					+ sField2 + " = '"+ sNUPROF + "' AND "
					+ sField1+ 
					" IN (SELECT "
					+ QMListaGastos.sField1 +
					" FROM " + QMListaGastos.sTable +
					" WHERE " 
					+ QMListaGastos.sField3 + " NOT IN ('"
					+ValoresDefecto.DEF_MOVIMIENTO_VALIDADO+"','"
					+ValoresDefecto.DEF_MOVIMIENTO_RESUELTO+
					"')))");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");



			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sGastoID =  rs.getString(sField1);
					
					result.add(sGastoID);
					
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",sField1,sGastoID);

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: PROVISION:{}",sNUPROF);

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);

		return result;
	}
}
