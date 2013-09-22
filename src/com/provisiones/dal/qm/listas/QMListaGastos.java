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
import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMCodigosControl;
import com.provisiones.dal.qm.QMGastos;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.GastoTabla;

public class QMListaGastos 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaGastos.class.getName());
	
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


	public static boolean addRelacionGasto(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE, String sCodNUPROF, String sCodMovimiento) 
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
						+ sCodMovimiento + "','"
						+ ValoresDefecto.DEF_PENDIENTE + "','"
					    + sUsuario + "','"
					    + Utils.timeStamp() +
						"')");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: COACES:|{}|",sCodCOACES);
			logger.error("ERROR: COGRUG:|{}|",sCodCOGRUG);
			logger.error("ERROR: COTPGA:|{}|",sCodCOTPGA);
			logger.error("ERROR: COSBGA:|{}|",sCodCOSBGA);
			logger.error("ERROR: COACES:|{}|",sCodCOACES);
			logger.error("ERROR: FEDEVE:|{}|",sFEDEVE);
			logger.error("ERROR: Gasto:|{}|",sCodMovimiento);

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

	public static boolean delRelacionGasto(String sCodMovimiento) 
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
					" WHERE (" + sField7 + " = '" + sCodMovimiento +"')");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: Gasto:|{}|",sCodMovimiento);

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
	
	public static boolean existeRelacionGasto(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE, String sCodNUPROF, String sCodMovimiento)
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
					+ sField8 + 
					"  FROM " + sTable + 
						" WHERE " +
						"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
						sField2  + " = '"+ sCodCOGRUG +"' AND " +
						sField3  + " = '"+ sCodCOTPGA +"' AND " +
						sField4  + " = '"+ sCodCOSBGA +"' AND " +
						sField5  + " = '"+ sFEDEVE +"' AND " +
						sField6  + " = '"+ sCodNUPROF +"' AND " +
					    sField7  + " = '"+ sCodMovimiento + "' )");


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
			logger.error("ERROR: COACES:|{}|",sCodCOACES);
			logger.error("ERROR: COGRUG:|{}|",sCodCOGRUG);
			logger.error("ERROR: COTPGA:|{}|",sCodCOTPGA);
			logger.error("ERROR: COSBGA:|{}|",sCodCOSBGA);
			logger.error("ERROR: FEDEVE:|{}|",sFEDEVE);
			logger.error("ERROR: NUPROF:|{}|",sCodNUPROF);
			logger.error("ERROR: MOVIMIENTO:|{}|",sCodMovimiento);

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
	
	public static String getProvisionDeGasto(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sProvision = "";

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
				+ sField6 + 
				"  FROM " + sTable + 
					" WHERE " +
					"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
					sField2  + " = '"+ sCodCOGRUG +"' AND " +
					sField3  + " = '"+ sCodCOTPGA +"' AND " +
					sField4  + " = '"+ sCodCOSBGA +"' AND " +
				    sField5  + " = '"+ sFEDEVE + "' )";
		
		logger.debug(sQuery);

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
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sProvision = rs.getString(sField6);
					
					
					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",sField6,sProvision);

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: COACES:|{}|",sCodCOACES);
			logger.error("ERROR: COGRUG:|{}|",sCodCOGRUG);
			logger.error("ERROR: COTPGA:|{}|",sCodCOTPGA);
			logger.error("ERROR: COSBGA:|{}|",sCodCOSBGA);
			logger.error("ERROR: FEDEVE:|{}|",sFEDEVE);

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
	
	public static String getProvisionDeMovimiento(String sCodMovimiento)
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sValidado = "";

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField6 + "  FROM " + sTable + 
					" WHERE " +
					"("	+ sField7  + " = '"+ sCodMovimiento +"' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sValidado = rs.getString(sField4);

					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",sField3,sCodMovimiento);
					logger.debug("{}:|{}|",sField4,sValidado);
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
		return sValidado;
	}

	public static ArrayList<String>  getGastosPorActivo(String sCodCOACES) 
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


			pstmt = conn.prepareStatement("SELECT " + sField7+ "  FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sCodCOACES + "' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
		
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(sField7));
										
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",sField1,sCodCOACES);
					logger.debug("{}:|{}|",sField7,result.get(i));
				
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
			logger.error("ERROR: COACES:|{}|",sCodCOACES);

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
	
	public static ArrayList<String>  getGastosPorEstado(String sEstado) 
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


			pstmt = conn.prepareStatement("SELECT " + sField7+ "  FROM " + sTable + 
					" WHERE (" + sField8 + " = '" + sEstado + "' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
		
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(sField7));
										
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",sField8,sEstado);
					logger.debug("{}:|{}|",sField7,result.get(i));

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
			logger.error("ERROR: Validado:|{}|",sEstado);

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


			pstmt = conn.prepareStatement("SELECT " + sField7 + "  FROM " + sTable + 
					" WHERE (" + sField6 + " = '" + sCodNUPROF + "' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(sField7));

					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",sField6,sCodNUPROF);
					logger.debug("{}:|{}|",sField7,result.get(i));
					
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


	
	public static boolean setValidado(String sCodMovimiento, String sValidado)
	{
		Statement stmt = null;
		boolean bSalida = true;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField8 + " = '"+ sValidado + 
					"' "+
					" WHERE "
					+ sField7 + " = '"+ sCodMovimiento +"'");
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: Gasto:|{}|",sCodMovimiento);

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
	
	public static String getValidado(String sCodMovimiento)
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sValidado = "";

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField8 + "  FROM " + sTable + 
					" WHERE (" + sField7 + " = '" + sCodMovimiento + "')");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sValidado = rs.getString(sField8);

					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",sField7,sCodMovimiento);
					logger.debug("{}:|{}|",sField8,sValidado);
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
		return sValidado;
	}
	
	public static long buscaCantidadValidado(String sCodValidado)
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		long liNumero = 0;

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM " + sTable + 
					" WHERE " +
					"(" + sField8 + " = '" + sCodValidado + "')");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					liNumero = rs.getLong("COUNT(*)");
					
					logger.debug("Encontrado el registro!");

					logger.debug( "Numero de registros:|{}|",liNumero);


				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: CodValidado:|{}|",sCodValidado);

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
		return liNumero;
	}
	
	public static ArrayList<ActivoTabla> buscaActivosConGastos(ActivoTabla activo)
	{
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
		
		logger.debug("Ejecutando Query...");

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
		
		logger.debug(sQuery);
		
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
			
			logger.debug("Ejecutada con exito!");

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
					
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",QMActivos.sField1,sCOACES);
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
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
	
	public static ArrayList<GastoTabla> buscaGastosActivo(String sCodCOACES)
	{
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
		
		logger.debug("Ejecutando Query...");

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
					   
		
		logger.debug(sQuery);
		
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
			
			logger.debug("Ejecutada con exito!");

			

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
					sDPTPAGO = QMCodigosControl.getDesCampo(QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
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
					
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",sField1,sCodCOACES);
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
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
