
package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Gasto;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.GastoTabla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QMGastos
{
	private static Logger logger = LoggerFactory.getLogger(QMGastos.class.getName());
	
	public static final String sTable = "ga_gastos_tbl";

	public static final String sField1  = "ga_gasto_id";
	public static final String sField2  = "cod_coaces"; 
	public static final String sField3  = "cod_cogrug"; 
	public static final String sField4  = "cotpga";     
	public static final String sField5  = "cosbga";     
	public static final String sField6  = "cod_ptpago"; 
	public static final String sField7  = "fedeve";     
	public static final String sField8  = "ffgtvp";     
	public static final String sField9  = "fepaga";     
	public static final String sField10 = "felipg";     
	public static final String sField11 = "cod_cosiga"; 
	public static final String sField12 = "feeesi";     
	public static final String sField13 = "feecoi";     
	public static final String sField14 = "feeaui";     
	public static final String sField15 = "feepai";     
	public static final String sField16 = "imngas";     
	public static final String sField17 = "ycos02";     
	public static final String sField18 = "imrgas";     
	public static final String sField19 = "ycos04";     
	public static final String sField20 = "imdgas";     
	public static final String sField21 = "ycos06";     
	public static final String sField22 = "imcost";     
	public static final String sField23 = "ycos08";     
	public static final String sField24 = "imogas";     
	public static final String sField25 = "ycos10";     
	public static final String sField26 = "imdtga";     
	public static final String sField27 = "imimga";     
	public static final String sField28 = "cod_coimpt"; 
	public static final String sField29 = "cod_cotneg"; 
	public static final String sField30 = "feagto";     
	public static final String sField31 = "cod_comona"; 
	public static final String sField32 = "cod_biauto"; 
	public static final String sField33 = "feaufa";     
	public static final String sField34 = "fepgpr";     
	public static final String sField35 = "cod_estado"; 

	
	public static int addGasto (Gasto NuevoGasto, String sEstado) 
	 
	{
		Connection conn = null;

		Statement stmt = null;
		ResultSet resulset = null;
		
		conn = ConnectionManager.OpenDBConnection();

		int iCodigo = 0;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "INSERT INTO " + sTable + " ("
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
			       + sField15 + ","              
			       + sField16 + ","              
			       + sField17 + ","              
			       + sField18 + ","              
			       + sField19 + ","              
			       + sField20 + ","              
			       + sField21 + ","              
			       + sField22 + ","              
			       + sField23 + ","              
			       + sField24 + ","              
			       + sField25 + ","              
			       + sField26 + ","              
			       + sField27 + ","              
			       + sField28 + ","              
			       + sField29 + ","              
			       + sField30 + ","              
			       + sField31 + ","              
			       + sField32 + ","              
			       + sField33 + ","
			       + sField34  + ","    
			       + sField35 +               
          
			       ") VALUES ('"        
			       + NuevoGasto.getCOACES() + "','"
			       + NuevoGasto.getCOGRUG() + "','"
			       + NuevoGasto.getCOTPGA() + "','"  
			       + NuevoGasto.getCOSBGA() + "','"  
			       + NuevoGasto.getPTPAGO() + "','"  
			       + NuevoGasto.getFEDEVE() + "','"  
			       + NuevoGasto.getFFGTVP() + "','"  
			       + NuevoGasto.getFEPAGA() + "','"  
			       + NuevoGasto.getFELIPG() + "','"  
			       + NuevoGasto.getCOSIGA() + "','"  
			       + NuevoGasto.getFEEESI() + "','"  
			       + NuevoGasto.getFEECOI() + "','"  
			       + NuevoGasto.getFEEAUI() + "','"  
			       + NuevoGasto.getFEEPAI() + "','"  
			       + NuevoGasto.getIMNGAS() + "','"  
			       + NuevoGasto.getYCOS02() + "','"  
			       + NuevoGasto.getIMRGAS() + "','"  
			       + NuevoGasto.getYCOS04() + "','"  
			       + NuevoGasto.getIMDGAS() + "','"  
			       + NuevoGasto.getYCOS06() + "','"  
			       + NuevoGasto.getIMCOST() + "','"  
			       + NuevoGasto.getYCOS08() + "','"  
			       + NuevoGasto.getIMOGAS() + "','"  
			       + NuevoGasto.getYCOS10() + "','"  
			       + NuevoGasto.getIMDTGA() + "','"  
			       + NuevoGasto.getIMIMGA() + "','"  
			       + NuevoGasto.getCOIMPT() + "','"  
			       + NuevoGasto.getCOTNEG() + "','"  
			       + NuevoGasto.getFEAGTO() + "','"  
			       + NuevoGasto.getCOMONA() + "','"  
			       + NuevoGasto.getBIAUTO() + "','"  
			       + NuevoGasto.getFEAUFA() + "','"  
			       + NuevoGasto.getFEPGPR() + "','"
			       + sEstado + "' )";
		
		logger.debug(sQuery);

		try 
		{
			
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery, Statement.RETURN_GENERATED_KEYS);

			resulset = stmt.getGeneratedKeys();
			
			if (resulset.next()) 
			{
				iCodigo= resulset.getInt(1);
			} 

			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			
			logger.error("ERROR: COACES:|{}|",NuevoGasto.getCOACES());
			logger.error("ERROR: COGRUG:|{}|",NuevoGasto.getCOGRUG());
			logger.error("ERROR: COTPGA:|{}|",NuevoGasto.getCOTPGA());
			logger.error("ERROR: COSBGA:|{}|",NuevoGasto.getCOSBGA());
			logger.error("ERROR: FEDEVE:|{}|",NuevoGasto.getFEDEVE());
			
			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
			iCodigo = 0;
		} 
		finally 
		{
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		
		return iCodigo;
	}
	public static boolean modGasto(Gasto NuevoGasto)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " + sTable + 
				" SET " 
				+ sField6  + " = '"+ NuevoGasto.getPTPAGO() + "', "
				+ sField8  + " = '"+ NuevoGasto.getFFGTVP() + "', "
				+ sField9  + " = '"+ NuevoGasto.getFEPAGA() + "', "
				+ sField10  + " = '"+ NuevoGasto.getFELIPG() + "', "
				+ sField11 + " = '"+ NuevoGasto.getCOSIGA() + "', "
				+ sField12 + " = '"+ NuevoGasto.getFEEESI() + "', "
				+ sField13 + " = '"+ NuevoGasto.getFEECOI() + "', "
				+ sField14 + " = '"+ NuevoGasto.getFEEAUI() + "', "
				+ sField15 + " = '"+ NuevoGasto.getFEEPAI() + "', "
				+ sField16 + " = '"+ NuevoGasto.getIMNGAS() + "', "
				+ sField17 + " = '"+ NuevoGasto.getYCOS02() + "', "
				+ sField18 + " = '"+ NuevoGasto.getIMRGAS() + "', "
				+ sField19 + " = '"+ NuevoGasto.getYCOS04() + "', "
				+ sField20 + " = '"+ NuevoGasto.getIMDGAS() + "', "
				+ sField21 + " = '"+ NuevoGasto.getYCOS06() + "', "
				+ sField22 + " = '"+ NuevoGasto.getIMCOST() + "', "
				+ sField23 + " = '"+ NuevoGasto.getYCOS08() + "', "
				+ sField24 + " = '"+ NuevoGasto.getIMOGAS() + "', "
				+ sField25 + " = '"+ NuevoGasto.getYCOS10() + "', "
				+ sField26 + " = '"+ NuevoGasto.getIMDTGA() + "', "
				+ sField27 + " = '"+ NuevoGasto.getIMIMGA() + "', "
				+ sField28 + " = '"+ NuevoGasto.getCOIMPT() + "', "
				+ sField29 + " = '"+ NuevoGasto.getCOTNEG() + "', "
				+ sField30 + " = '"+ NuevoGasto.getFEAGTO() + "', "
				+ sField31 + " = '"+ NuevoGasto.getCOMONA() + "', "
				+ sField32 + " = '"+ NuevoGasto.getBIAUTO() + "', "
				+ sField33 + " = '"+ NuevoGasto.getFEAUFA() + "', "
				+ sField34 + " = '"+ NuevoGasto.getFEPGPR() + 
				"' "+
				" WHERE " +
				"("	+ 
					sField2  + " = '"+ NuevoGasto.getCOACES() +"' AND " +
					sField3  + " = '"+ NuevoGasto.getCOGRUG() +"' AND " +
					sField4  + " = '"+ NuevoGasto.getCOTPGA() +"' AND " +
					sField5  + " = '"+ NuevoGasto.getCOSBGA() +"' AND " +
				    sField7  + " = '"+ NuevoGasto.getFEDEVE() + "' )";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField6  + " = '"+ NuevoGasto.getPTPAGO() + "', "
					+ sField8  + " = '"+ NuevoGasto.getFFGTVP() + "', "
					+ sField9  + " = '"+ NuevoGasto.getFEPAGA() + "', "
					+ sField10  + " = '"+ NuevoGasto.getFELIPG() + "', "
					+ sField11 + " = '"+ NuevoGasto.getCOSIGA() + "', "
					+ sField12 + " = '"+ NuevoGasto.getFEEESI() + "', "
					+ sField13 + " = '"+ NuevoGasto.getFEECOI() + "', "
					+ sField14 + " = '"+ NuevoGasto.getFEEAUI() + "', "
					+ sField15 + " = '"+ NuevoGasto.getFEEPAI() + "', "
					+ sField16 + " = '"+ NuevoGasto.getIMNGAS() + "', "
					+ sField17 + " = '"+ NuevoGasto.getYCOS02() + "', "
					+ sField18 + " = '"+ NuevoGasto.getIMRGAS() + "', "
					+ sField19 + " = '"+ NuevoGasto.getYCOS04() + "', "
					+ sField20 + " = '"+ NuevoGasto.getIMDGAS() + "', "
					+ sField21 + " = '"+ NuevoGasto.getYCOS06() + "', "
					+ sField22 + " = '"+ NuevoGasto.getIMCOST() + "', "
					+ sField23 + " = '"+ NuevoGasto.getYCOS08() + "', "
					+ sField24 + " = '"+ NuevoGasto.getIMOGAS() + "', "
					+ sField25 + " = '"+ NuevoGasto.getYCOS10() + "', "
					+ sField26 + " = '"+ NuevoGasto.getIMDTGA() + "', "
					+ sField27 + " = '"+ NuevoGasto.getIMIMGA() + "', "
					+ sField28 + " = '"+ NuevoGasto.getCOIMPT() + "', "
					+ sField29 + " = '"+ NuevoGasto.getCOTNEG() + "', "
					+ sField30 + " = '"+ NuevoGasto.getFEAGTO() + "', "
					+ sField31 + " = '"+ NuevoGasto.getCOMONA() + "', "
					+ sField32 + " = '"+ NuevoGasto.getBIAUTO() + "', "
					+ sField33 + " = '"+ NuevoGasto.getFEAUFA() + "', "
					+ sField34 + " = '"+ NuevoGasto.getFEPGPR() + 
					"' "+
					" WHERE " +
					"("	+ 
						sField2  + " = '"+ NuevoGasto.getCOACES() +"' AND " +
						sField3  + " = '"+ NuevoGasto.getCOGRUG() +"' AND " +
						sField4  + " = '"+ NuevoGasto.getCOTPGA() +"' AND " +
						sField5  + " = '"+ NuevoGasto.getCOSBGA() +"' AND " +
					    sField7  + " = '"+ NuevoGasto.getFEDEVE() + "' )");

			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: COACES:|{}|",NuevoGasto.getCOACES());
			logger.error("ERROR: COGRUG:|{}|",NuevoGasto.getCOGRUG());
			logger.error("ERROR: COTPGA:|{}|",NuevoGasto.getCOTPGA());
			logger.error("ERROR: COSBGA:|{}|",NuevoGasto.getCOSBGA());
			logger.error("ERROR: FEDEVE:|{}|",NuevoGasto.getFEDEVE());

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

	public static boolean delGasto(String sGastoID)
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
					" WHERE "
					+ sField1  + " = '"+ sGastoID +"'");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: GASTO:|{}|",sGastoID);

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

	public static Gasto getGasto(String sGastoID)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sCOACES = "";
		String sCOGRUG = "";
		String sCOTPGA = "";
		String sCOSBGA = "";
		String sPTPAGO = "";
		String sFEDEVE = "";
		String sFFGTVP = "";
		String sFEPAGA = "";
		String sFELIPG = "";
		String sCOSIGA = "";
		String sFEEESI = "";
		String sFEECOI = "";
		String sFEEAUI = "";
		String sFEEPAI = "";
		String sIMNGAS = "";
		String sYCOS02 = "";
		String sIMRGAS = "";
		String sYCOS04 = "";
		String sIMDGAS = "";
		String sYCOS06 = "";
		String sIMCOST = "";
		String sYCOS08 = "";
		String sIMOGAS = "";
		String sYCOS10 = "";
		String sIMDTGA = "";
		String sIMIMGA = "";
		String sCOIMPT = "";
		String sCOTNEG = "";
		String sFEAGTO = "";
		String sCOMONA = "";
		String sBIAUTO = "";
		String sFEAUFA = "";
		String sFEPGPR = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");

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
				       + sField9  + ","              
				       + sField10 + ","              
				       + sField11 + ","              
				       + sField12 + ","              
				       + sField13 + ","              
				       + sField14 + ","              
				       + sField15 + ","              
				       + sField16 + ","              
				       + sField17 + ","              
				       + sField18 + ","              
				       + sField19 + ","              
				       + sField20 + ","              
				       + sField21 + ","              
				       + sField22 + ","              
				       + sField23 + ","              
				       + sField24 + ","              
				       + sField25 + ","              
				       + sField26 + ","              
				       + sField27 + ","              
				       + sField28 + ","              
				       + sField29 + ","              
				       + sField30 + ","              
				       + sField31 + ","              
				       + sField32 + ","
				       + sField33  + ","
				       + sField34 +      
				       "  FROM " 
				       + sTable + 
				       " WHERE "
				       + sField1  + " = '"+ sGastoID +"'");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			


			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCOACES = rs.getString(sField2); 
					sCOGRUG = rs.getString(sField3);
					sCOTPGA = rs.getString(sField4);
					sCOSBGA = rs.getString(sField5); 
					sPTPAGO = rs.getString(sField6);  
					sFEDEVE = rs.getString(sField7);
					sFFGTVP = rs.getString(sField8);  
					sFEPAGA = rs.getString(sField9);  
					sFELIPG = rs.getString(sField10); 
					sCOSIGA = rs.getString(sField11); 
					sFEEESI = rs.getString(sField12); 
					sFEECOI = rs.getString(sField13); 
					sFEEAUI = rs.getString(sField14); 
					sFEEPAI = rs.getString(sField15); 
					sIMNGAS = rs.getString(sField16); 
					sYCOS02 = rs.getString(sField17); 
					sIMRGAS = rs.getString(sField18); 
					sYCOS04 = rs.getString(sField19); 
					sIMDGAS = rs.getString(sField20); 
					sYCOS06 = rs.getString(sField21); 
					sIMCOST = rs.getString(sField22); 
					sYCOS08 = rs.getString(sField23); 
					sIMOGAS = rs.getString(sField24); 
					sYCOS10 = rs.getString(sField25); 
					sIMDTGA = rs.getString(sField26); 
					sIMIMGA = rs.getString(sField27); 
					sCOIMPT = rs.getString(sField28); 
					sCOTNEG = rs.getString(sField29); 
					sFEAGTO = rs.getString(sField30); 
					sCOMONA = rs.getString(sField31); 
					sBIAUTO = rs.getString(sField32); 
					sFEAUFA = rs.getString(sField33); 
					sFEPGPR = rs.getString(sField34); 
					
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
			logger.error("ERROR: GASTO:|{}|",sGastoID);

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
		
		return new Gasto(sCOACES, sCOGRUG, sCOTPGA, sCOSBGA,
				sPTPAGO, sFEDEVE, sFFGTVP, sFEPAGA, sFELIPG, sCOSIGA, sFEEESI,
				sFEECOI, sFEEAUI, sFEEPAI, sIMNGAS, sYCOS02, sIMRGAS, sYCOS04,
				sIMDGAS, sYCOS06, sIMCOST, sYCOS08, sIMOGAS, sYCOS10, sIMDTGA,
				sIMIMGA, sCOIMPT, sCOTNEG, sFEAGTO, sCOMONA, sBIAUTO, sFEAUFA,
				sFEPGPR);
	}
	
	public static String getGastoID(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sGastoID = "";


		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
			       + sField1 +      
			       " FROM " 
			       + sTable + 
			       " WHERE "+
			       "("	
			       + sField2  + " = '"+ sCodCOACES +"' AND " 
			       + sField3  + " = '"+ sCodCOGRUG +"' AND "
			       + sField4  + " = '"+ sCodCOTPGA +"' AND "
			       + sField5  + " = '"+ sCodCOSBGA +"' AND "
			       + sField7  + " = '"+ sFEDEVE + "' )";

		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			


			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sGastoID = rs.getString(sField1);  
					
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
		return sGastoID;
	}
	
	public static boolean existeGasto(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
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
				       + sField1  +       
			"  FROM " + sTable + 
			" WHERE "+
			"("	+ sField2  + " = '"+ sCodCOACES +"' AND " +
			sField3  + " = '"+ sCodCOGRUG +"' AND " +
			sField4  + " = '"+ sCodCOTPGA +"' AND " +
			sField5  + " = '"+ sCodCOSBGA +"' AND " +
		    sField7  + " = '"+ sFEDEVE + "' )");

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
		return found;
	}
	
	public static boolean gastoAnulado(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
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
					+ sField1 + 
					"  FROM " + sTable + 
						" WHERE " +
						"("	+ sField2  + " = '"+ sCodCOACES +"' AND " +
						sField3  + " = '"+ sCodCOGRUG +"' AND " +
						sField4  + " = '"+ sCodCOTPGA +"' AND " +
						sField5  + " = '"+ sCodCOSBGA +"' AND " +
						sField7  + " = '"+ sFEDEVE +"' AND " +
						sField30 + " <> '"+  ValoresDefecto.CAMPO_SIN_INFORMAR +"' AND " +
					    sField35  + " = '"+ ValoresDefecto.DEF_GASTO_ANULADO + "' )");


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
		return found;
	}
	
	public static boolean setFechaAnulado(String sCodGasto, String sFEAGTO)
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
					+ sField30 + " = '"+ sFEAGTO + "' "+
					" WHERE "
					+ sField1  + " = '"+ sCodGasto +"'");
			
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

	public static boolean setEstado(String sCodGasto, String sEstado)
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
					+ sField35 + " = '"+ sEstado + "' "+
					" WHERE "
					+ sField1  + " = '"+ sCodGasto +"'");
			
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
	
	public static String getEstado(String sCodGasto)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sEstado = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
						+ sField35 + 
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

					sEstado = rs.getString(sField35);
					
					
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",sField35,sEstado);

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
		return sEstado;
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


			pstmt = conn.prepareStatement(
					"SELECT " 
					+ sField1+ 
					" FROM " 
					+ sTable + 
					" WHERE " 
					+ sField2 + " = '" + sCodCOACES + "'");

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

					logger.debug("{}:|{}|",sField2,sCodCOACES);
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
					   +  sField2 + 
					   " FROM " + sTable +
					   " WHERE " 
					   + sField35 + " = '"+ ValoresDefecto.DEF_GASTO_ESTIMADO + "' " + " OR "
   					   + sField35 + " = '"+ ValoresDefecto.DEF_GASTO_CONOCIDO + "' ) )";
		
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
					   +  sField2 + 
					   " FROM " + sTable +
					   " WHERE " 
					   + sField35 + " = '"+ ValoresDefecto.DEF_GASTO_ESTIMADO + "' " + " OR "
   					   + sField35 + " = '"+ ValoresDefecto.DEF_GASTO_CONOCIDO + "' ) )");

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
					
					   + sField2 + ","        
					   + sField3 + ","
					   + sField4 + ","
					   + sField5 + ","
					   + sField6 + ","
					   + sField7 + ","
					   + sField11 + ","
					   + sField16 + ","
					   + sField17 +

					   " FROM " + sTable + 
					   " WHERE (" +
					   "("
					   + sField35 + " = '" + ValoresDefecto.DEF_GASTO_ESTIMADO + "' OR "
					   + sField35 + " = '" + ValoresDefecto.DEF_GASTO_CONOCIDO + 					   
					   "') AND "					   
					   
					   + sField7 + " <= '"+Utils.fechaDeHoy(false)+"')";					   
					   
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement("SELECT "
					
					   + sField2 + ","        
					   + sField3 + ","
					   + sField4 + ","
					   + sField5 + ","
					   + sField6 + ","
					   + sField7 + ","
					   + sField11 + ","
					   + sField16 + ","
					   + sField17 +

					   " FROM " + sTable + 
					   " WHERE (" +
					   "("
					   + sField35 + " = '" + ValoresDefecto.DEF_GASTO_ESTIMADO + "' OR "
					   + sField35 + " = '" + ValoresDefecto.DEF_GASTO_CONOCIDO + 					   
					   "') AND "					   
					   
					   + sField7 + " <= '"+Utils.fechaDeHoy(false)+"')");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					   
					
					sCOACES  = rs.getString(QMGastos.sField2);
					sCOGRUG  = rs.getString(QMGastos.sField3);
					sCOTPGA  = rs.getString(QMGastos.sField4);
					sCOSBGA  = rs.getString(QMGastos.sField5);
					sDCOSBGA = QMCodigosControl.getDesCOSBGA(sCOGRUG,sCOTPGA,sCOSBGA);
					sPTPAGO  = rs.getString(QMGastos.sField6);
					sDPTPAGO = QMCodigosControl.getDesCampo(QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
					sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.sField7));
					sCOSIGA  = rs.getString(QMGastos.sField11);
					sDCOSIGA = QMCodigosControl.getDesCampo(QMCodigosControl.TCOSIGA,QMCodigosControl.ICOSIGA,sCOSIGA);
					sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.sField17).equals("-"),rs.getString(QMGastos.sField16));

 

					
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

					logger.debug("{}:|{}|",sField2,sCodCOACES);
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
