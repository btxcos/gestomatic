package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Comunidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QMComunidades
{
	private static Logger logger = LoggerFactory.getLogger(QMComunidades.class.getName());
	
	public static final String sTable = "e1_comunidades_tbl";

	public static final String sField1  = "cod_cocldo";
	
	public static final String sField2  = "nudcom_id"; 
	
	public static final String sField3  = "nomcoc";    
	public static final String sField4  = "nodcco";    
	public static final String sField5  = "nomprc";    
	public static final String sField6  = "nutprc";    
	public static final String sField7  = "nomadc";    
	public static final String sField8  = "nutadc";    
	public static final String sField9  = "nodcad";    
	public static final String sField10 = "nuccen";    
	public static final String sField11 = "nuccof";    
	public static final String sField12 = "nuccdi";    
	public static final String sField13 = "nuccnt";    
	public static final String sField14 = "obtexc";

	public static final String sField15 = "cod_estado";
	

	public static boolean addComunidad(Comunidad NuevaComunidad)

	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");

		try 
		{

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
				       + sField10 + ","              
				       + sField11 + ","              
				       + sField12 + ","              
				       + sField13 + ","
				       + sField14 + "," 
				       + sField15 +               
				       ") VALUES ('" 
				       + NuevaComunidad.getsCOCLDO() + "','" 
				       + NuevaComunidad.getsNUDCOM() + "','"
				       + NuevaComunidad.getsNOMCOC() + "','"
				       + NuevaComunidad.getsNODCCO() + "','"
				       + NuevaComunidad.getsNOMPRC() + "','"
				       + NuevaComunidad.getsNUTPRC() + "','"
				       + NuevaComunidad.getsNOMADC() + "','"
				       + NuevaComunidad.getsNUTADC() + "','"
				       + NuevaComunidad.getsNODCAD() + "','"
				       + NuevaComunidad.getsNUCCEN() + "','"
				       + NuevaComunidad.getsNUCCOF() + "','"
				       + NuevaComunidad.getsNUCCDI() + "','"
				       + NuevaComunidad.getsNUCCNT() + "','"
				       + NuevaComunidad.getsOBTEXC() + "','" 
				       + ValoresDefecto.DEF_ALTA + "' )");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{


			
			logger.error("ERROR: COCLDO:|{}|",NuevaComunidad.getsCOCLDO());
			logger.error("ERROR: NUDCOM:|{}|",NuevaComunidad.getsNUDCOM());
			
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
	public static boolean modComunidad(Comunidad NuevaComunidad, String sCodCOCLDO, String sCodNUDCOM)
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
					//+ sField1  + " = '"+ NuevaComunidad.getsCOCLDO() + "', "
					//+ sField2  + " = '"+ NuevaComunidad.getsNUDCOM() + "', "
					+ sField3  + " = '"+ NuevaComunidad.getsNOMCOC() + "', "
					+ sField4  + " = '"+ NuevaComunidad.getsNODCCO() + "', "
					+ sField5  + " = '"+ NuevaComunidad.getsNOMPRC() + "', "
					+ sField6  + " = '"+ NuevaComunidad.getsNUTPRC() + "', "
					+ sField7  + " = '"+ NuevaComunidad.getsNOMADC() + "', "
					+ sField8  + " = '"+ NuevaComunidad.getsNUTADC() + "', "
					+ sField9  + " = '"+ NuevaComunidad.getsNODCAD() + "', "
					+ sField10 + " = '"+ NuevaComunidad.getsNUCCEN() + "', "
					+ sField11 + " = '"+ NuevaComunidad.getsNUCCOF() + "', "
					+ sField12 + " = '"+ NuevaComunidad.getsNUCCDI() + "', "
					+ sField13 + " = '"+ NuevaComunidad.getsNUCCNT() + "', "
					+ sField14 + " = '"+ NuevaComunidad.getsOBTEXC() +
					"' "+
					" WHERE " +
					"("+ sField1 + " = '"+ sCodCOCLDO +"' AND "+
						sField2 + " = '"+ sCodNUDCOM +"')");
			
			logger.debug("Ejecutada con exito!");

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: COCLDO:|{}|",NuevaComunidad.getsCOCLDO());
			logger.error("ERROR: NUDCOM:|{}|",NuevaComunidad.getsNUDCOM());

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

	public static boolean delComunidad(String sCodCOCLDO, String sCodNUDCOM)
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
					"("+ sField1 + " = '"+ sCodCOCLDO +"' AND "+
					sField2 + " = '"+ sCodNUDCOM +"')");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: COCLDO:|{}|",sCodCOCLDO);
			logger.error("ERROR: NUDCOM:|{}|",sCodNUDCOM);

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

	public static Comunidad getComunidad(String sCodCOCLDO, String sCodNUDCOM)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();
		
		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sCOCLDO = "";
		String sNUDCOM = "";
		String sNOMCOC = "";
		String sNODCCO = "";
		String sNOMPRC = "";
		String sNUTPRC = "";
		String sNOMADC = "";
		String sNUTADC = "";
		String sNODCAD = "";
		String sNUCCEN = "";
		String sNUCCOF = "";
		String sNUCCDI = "";
		String sNUCCNT = "";
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
				       + sField10 + ","              
				       + sField11 + ","              
				       + sField12 + ","              
				       + sField13 + ","              
				       + sField14 +               
       
			"  FROM " + sTable + 
					" WHERE " +
					"("+ sField1 + " = '"+ sCodCOCLDO +"' AND "+
					sField2 + " = '"+ sCodNUDCOM +"')");
			

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			logger.debug(sField1 + ":|{}|",sCodCOCLDO);
			logger.debug(sField2 + ":|{}|",sCodNUDCOM);

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCOCLDO = rs.getString(sField1);  
					sNUDCOM = rs.getString(sField2);  
					sNOMCOC = rs.getString(sField3);  
					sNODCCO = rs.getString(sField4);  
					sNOMPRC = rs.getString(sField5);  
					sNUTPRC = rs.getString(sField6);  
					sNOMADC = rs.getString(sField7);  
					sNUTADC = rs.getString(sField8);  
					sNODCAD = rs.getString(sField9);  
					sNUCCEN = rs.getString(sField10); 
					sNUCCOF = rs.getString(sField11); 
					sNUCCDI = rs.getString(sField12); 
					sNUCCNT = rs.getString(sField13); 
					sOBTEXC = rs.getString(sField14); 

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
		return new Comunidad(
				sCOCLDO,
				sNUDCOM,
				sNOMCOC,
				sNODCCO,
				sNOMPRC,
				sNUTPRC,
				sNOMADC,
				sNUTADC,
				sNODCAD,
				sNUCCEN,
				sNUCCOF,
				sNUCCDI,
				sNUCCNT,
				sOBTEXC);
	}

	public static boolean existeComunidad(String sCodCOCLDO, String sCodNUDCOM)
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

			pstmt = conn.prepareStatement("SELECT "
				       + sField2  +                
       			"  FROM " + sTable + 
					" WHERE " +
					"("+ sField1 + " = '"+ sCodCOCLDO +"' AND "+
					sField2 + " = '"+ sCodNUDCOM +"')");
			

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			logger.debug(sField1 + ":|{}|",sCodCOCLDO);
			logger.debug(sField2 + ":|{}|",sCodNUDCOM);

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
	
	public static boolean setEstado(String sCodCOCLDO, String sCodNUDCOM, String sEstado)
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
					+ sField15 + " = '"+ sEstado + 
					"' "+
					" WHERE "+
					"("+ sField1 + " = '"+ sCodCOCLDO +"' AND "+
					sField2 + " = '"+ sCodNUDCOM +"')");
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: COCLDO:|{}|",sCodCOCLDO);
			logger.error("ERROR: NUDCOM:|{}|",sCodNUDCOM);

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
	
	public static String getEstado(String sCodCOCLDO, String sCodNUDCOM)
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


			pstmt = conn.prepareStatement("SELECT " + sField15 + "  FROM " + sTable + 
					" WHERE " +
					"("+ sField1 + " = '"+ sCodCOCLDO +"' AND "+
					sField2 + " = '"+ sCodNUDCOM +"')");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sEstado = rs.getString(sField15);
					
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",sField15,sEstado);
					logger.debug("{}:|{}|",sField1,sCodCOCLDO);
					logger.debug("{}:|{}|",sField2,sCodNUDCOM);
				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
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
		return sEstado;
	}
}