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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QMCuotas
{
	private static Logger logger = LoggerFactory.getLogger(QMCuotas.class.getName());
	
	public static final String sTable = "e2_cuotas_tbl";

	public static final String sField1  = "cod_coaces";
	public static final String sField2  = "cod_cocldo";
	public static final String sField3  = "cod_nudcom";
	public static final String sField4  = "cod_cosbac";
	public static final String sField5  = "fipago";    
	public static final String sField6  = "ffpago";    
	public static final String sField7  = "imcuco";    
	public static final String sField8  = "faacta";    
	public static final String sField9  = "cod_ptpago";
	public static final String sField10  = "obtexc";

	public static final String sField11 = "cod_estado";

	public static boolean addCuota(Cuota NuevaCuota)

	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");

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
				       + sField9  + ","
				       + sField10  + ","
				       + sField11  + 
				       ") VALUES ('"
				       + NuevaCuota.getCOACES() + "','"
				       + NuevaCuota.getCOCLDO() + "','"
				       + NuevaCuota.getNUDCOM() + "','"
				       + NuevaCuota.getCOSBAC() + "','"
				       + NuevaCuota.getFIPAGO() + "','"
				       + NuevaCuota.getFFPAGO() + "','"
				       + NuevaCuota.getIMCUCO() + "','"
				       + NuevaCuota.getFAACTA() + "','"
				       + NuevaCuota.getPTPAGO() + "','"
				       + NuevaCuota.getOBTEXC() + "','" 
				       + ValoresDefecto.DEF_ALTA + "' )");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: COACES:|{}|",NuevaCuota.getCOACES());
			logger.error("ERROR: COCLDO:|{}|",NuevaCuota.getCOCLDO());
			logger.error("ERROR: NUDCOM:|{}|",NuevaCuota.getNUDCOM());
			logger.error("ERROR: COSBAC:|{}|",NuevaCuota.getCOSBAC());
			
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
	public static boolean modCuota(Cuota NuevaCuota, String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField5  + " = '"+ NuevaCuota.getFIPAGO() + "', "
					+ sField6  + " = '"+ NuevaCuota.getFFPAGO() + "', "
					+ sField7  + " = '"+ NuevaCuota.getIMCUCO() + "', "
					+ sField8  + " = '"+ NuevaCuota.getFAACTA() + "', "
					+ sField9  + " = '"+ NuevaCuota.getPTPAGO() + "', "
					+ sField10 + " = '"+ NuevaCuota.getOBTEXC() + 
					"' "+
					" WHERE " +
					"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
						sField2  + " = '"+ sCodCOCLDO +"' AND " +
						sField3  + " = '"+ sCodNUDCOM +"' AND " +
					    sField4  + " = '"+ sCodCOSBAC + "' )");
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: COACES:|{}|",NuevaCuota.getCOACES());
			logger.error("ERROR: COCLDO:|{}|",NuevaCuota.getCOCLDO());
			logger.error("ERROR: NUDCOM:|{}|",NuevaCuota.getNUDCOM());
			logger.error("ERROR: COSBAC:|{}|",NuevaCuota.getCOSBAC());

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

	public static boolean delCuota(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE " +
					"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
					sField2  + " = '"+ sCodCOCLDO +"' AND " +
					sField3  + " = '"+ sCodNUDCOM +"' AND " +
				    sField4  + " = '"+ sCodCOSBAC + "' )");
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: COACES:|{}|",sCodCOACES);
			logger.error("ERROR: COCLDO:|{}|",sCodCOCLDO);
			logger.error("ERROR: NUDCOM:|{}|",sCodNUDCOM);
			logger.error("ERROR: COSBAC:|{}|",sCodCOSBAC);

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
	
	public static boolean existeCuota(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();
		
		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		boolean found = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField11 + "  FROM " + sTable + 
					" WHERE " +
					"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
					sField2  + " = '"+ sCodCOCLDO +"' AND " +
					sField3  + " = '"+ sCodNUDCOM +"' AND " +
				    sField4  + " = '"+ sCodCOSBAC + "' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					logger.debug("Encontrado el registro!");
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
			logger.error("ERROR: COCLDO:|{}|",sCodCOCLDO);
			logger.error("ERROR: NUDCOM:|{}|",sCodNUDCOM);
			logger.error("ERROR: COSBAC:|{}|",sCodCOSBAC);

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

	public static Cuota getCuota(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sCOACES = "";
		String sCOCLDO = "";
		String sNUDCOM = "";
		String sCOSBAC = "";
		String sFIPAGO = "";
		String sFFPAGO = "";
		String sIMCUCO = "";
		String sFAACTA = "";
		String sPTPAGO = "";
		String sOBTEXC = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					   + sField1  + ","
					   + sField2  + ","              
				       + sField3  + ","              
				       + sField4  + ","              
				       + sField5  + ","              
				       + sField6  + ","              
				       + sField7  + ","              
				       + sField8  + ","
				       + sField9  + ","
				       + sField10  +       
				       "  FROM " + sTable + 
					" WHERE " +
					"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
					sField2  + " = '"+ sCodCOCLDO +"' AND " +
					sField3  + " = '"+ sCodNUDCOM +"' AND " +
				    sField4  + " = '"+ sCodCOSBAC + "' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCOACES = rs.getString(sField1);
					sCOCLDO = rs.getString(sField2);
					sNUDCOM = rs.getString(sField3);
					sCOSBAC = rs.getString(sField4);
					sFIPAGO = rs.getString(sField5);
					sFFPAGO = rs.getString(sField6);
					sIMCUCO = rs.getString(sField7);
					sFAACTA = rs.getString(sField8);
					sPTPAGO = rs.getString(sField9);
					sOBTEXC = rs.getString(sField10);
					
					logger.debug("Encontrado el registro!");
					
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
			logger.error("ERROR: COCLDO:|{}|",sCodCOCLDO);
			logger.error("ERROR: NUDCOM:|{}|",sCodNUDCOM);
			logger.error("ERROR: NUDCOM:|{}|",sCOSBAC);

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
		return new Cuota(sCOACES, sCOCLDO, sNUDCOM, sCOSBAC, sFIPAGO, sFFPAGO, sIMCUCO, sFAACTA, sPTPAGO, sOBTEXC);
	}
	
	public static boolean tieneCuotas(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "              
			       + sField3  +       
			       "  FROM " + sTable + 
				" WHERE " +
				"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
					sField2  + " = '"+ sCodCOCLDO +"' AND " +
					sField3  + " = '"+ sCodNUDCOM +"' AND " +
			      sField11  + " <> 'B' )";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "              
				       + sField3  +       
				       "  FROM " + sTable + 
					" WHERE " +
					"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
					sField2  + " = '"+ sCodCOCLDO +"' AND " +
					sField3  + " = '"+ sCodNUDCOM +"' AND " +
				      sField11  + " <> 'B' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");


			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					logger.debug("Encontrado el registro!");

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
			logger.error("ERROR: COCLDO:|{}|",sCodCOCLDO);
			logger.error("ERROR: NUDCOM:|{}|",sCodNUDCOM);

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
	
	public static boolean setEstado(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC, String sEstado)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();
		
		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField11 + " = '"+ sEstado + 
					"' "+
					" WHERE "+
					"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
					sField2  + " = '"+ sCodCOCLDO +"' AND " +
					sField3  + " = '"+ sCodNUDCOM +"' AND " +
				    sField4  + " = '"+ sCodCOSBAC + "' )");
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: COACES:|{}|",sCodCOACES);
			logger.error("ERROR: COCLDO:|{}|",sCodCOCLDO);
			logger.error("ERROR: NUDCOM:|{}|",sCodNUDCOM);
			logger.error("ERROR: COSBAC:|{}|",sCodCOSBAC);

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
	
	public static String getEstado(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sEstado = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField11 + "  FROM " + sTable + 
					" WHERE " +
					"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
					sField2  + " = '"+ sCodCOCLDO +"' AND " +
					sField3  + " = '"+ sCodNUDCOM +"' AND " +
				    sField4  + " = '"+ sCodCOSBAC + "' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sEstado = rs.getString(sField11);
					
					logger.debug("Encontrado el registro!");
					
					logger.debug("{}:|{}|",sField11,sEstado);


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
			logger.error("ERROR: COCLDO:|{}|",sCodCOCLDO);
			logger.error("ERROR: NUDCOM:|{}|",sCodNUDCOM);
			logger.error("ERROR: COSBAC:|{}|",sCodCOSBAC);

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
		return sEstado;
	}
	
}
