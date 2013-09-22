package com.provisiones.dal.qm.movimientos;

import com.provisiones.dal.ConnectionManager;

import com.provisiones.misc.Utils;
import com.provisiones.types.MovimientoGasto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QMMovimientosGastos
{
	private static Logger logger = LoggerFactory.getLogger(QMMovimientosGastos.class.getName());
	
	public static final String sTable = "ga_movimientos_tbl";

	public static final String sField1  = "ga_movimiento_id";
	
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
	public static final String sField27 = "counmo";    
	public static final String sField28 = "imimga";    
	public static final String sField29 = "cod_coimpt";
	public static final String sField30 = "cod_cotneg";
	public static final String sField31 = "coencx";    
	public static final String sField32 = "coofcx";    
	public static final String sField33 = "nucone";    
	public static final String sField34 = "nuprof";    
	public static final String sField35 = "feagto";    
	public static final String sField36 = "cod_comona";
	public static final String sField37 = "cod_biauto";
	public static final String sField38 = "feaufa";    
	public static final String sField39 = "cod_coterr";
	public static final String sField40 = "fmpagn";    
	public static final String sField41 = "fepgpr";    
	public static final String sField42 = "feapli";    
	public static final String sField43 = "coapii";    
	public static final String sField44 = "cospii";    
	public static final String sField45 = "nuclii";
	
	public static int addMovimientoGasto (MovimientoGasto NuevoGasto) 
	{
		Statement stmt = null;
		Connection conn = null;
		ResultSet resulset = null;
		
		int iCodigo = 0;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		try 
		{
			
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + sTable + " ("
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
				       + sField34 + ","              
				       + sField35 + ","              
				       + sField36 + ","              
				       + sField37 + ","              
				       + sField38 + ","              
				       + sField39 + ","              
				       + sField40 + ","              
				       + sField41 + ","              
				       + sField42 + ","              
				       + sField43 + ","
				       + sField44 + ","
				       + sField45 +                 
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
								       + NuevoGasto.getCOUNMO() + "','"  
								       + NuevoGasto.getIMIMGA() + "','"  
								       + NuevoGasto.getCOIMPT() + "','"  
								       + NuevoGasto.getCOTNEG() + "','"  
								       + NuevoGasto.getCOENCX() + "','"  
								       + NuevoGasto.getCOOFCX() + "','"  
								       + NuevoGasto.getNUCONE() + "','"  
								       + NuevoGasto.getNUPROF() + "','"  
								       + NuevoGasto.getFEAGTO() + "','"  
								       + NuevoGasto.getCOMONA() + "','"  
								       + NuevoGasto.getBIAUTO() + "','"  
								       + NuevoGasto.getFEAUFA() + "','"  
								       + NuevoGasto.getCOTERR() + "','"  
								       + NuevoGasto.getFMPAGN() + "','"  
								       + NuevoGasto.getFEPGPR() + "','"  
								       + NuevoGasto.getFEAPLI() + "','"  
								       + NuevoGasto.getCOAPII() + "','"  
								       + NuevoGasto.getCOSPII() + "','"
								       + NuevoGasto.getNUCLII() + "' )", Statement.RETURN_GENERATED_KEYS);

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
			
			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
			
			//bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
			Utils.closeResultSet(resulset);
		}
		ConnectionManager.CloseDBConnection(conn);
		
		return iCodigo;
	}
	public static boolean modMovimientoGasto(MovimientoGasto NuevoGasto, String sGastoID)
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
					+ sField2  + " = '"+ NuevoGasto.getCOACES() + "', "
					+ sField3  + " = '"+ NuevoGasto.getCOGRUG() + "', "
					+ sField4  + " = '"+ NuevoGasto.getCOTPGA() + "', "
					+ sField5  + " = '"+ NuevoGasto.getCOSBGA() + "', "
					+ sField6  + " = '"+ NuevoGasto.getPTPAGO() + "', "
					+ sField7  + " = '"+ NuevoGasto.getFEDEVE() + "', "
					+ sField8  + " = '"+ NuevoGasto.getFFGTVP() + "', "
					+ sField9  + " = '"+ NuevoGasto.getFEPAGA() + "', "
					+ sField10 + " = '"+ NuevoGasto.getFELIPG() + "', "
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
					+ sField27 + " = '"+ NuevoGasto.getCOUNMO() + "', "
					+ sField28 + " = '"+ NuevoGasto.getIMIMGA() + "', "
					+ sField29 + " = '"+ NuevoGasto.getCOIMPT() + "', "
					+ sField30 + " = '"+ NuevoGasto.getCOTNEG() + "', "
					+ sField31 + " = '"+ NuevoGasto.getCOENCX() + "', "
					+ sField32 + " = '"+ NuevoGasto.getCOOFCX() + "', "
					+ sField33 + " = '"+ NuevoGasto.getNUCONE() + "', "
					+ sField34 + " = '"+ NuevoGasto.getNUPROF() + "', "
					+ sField35 + " = '"+ NuevoGasto.getFEAGTO() + "', "
					+ sField36 + " = '"+ NuevoGasto.getCOMONA() + "', "
					+ sField37 + " = '"+ NuevoGasto.getBIAUTO() + "', "
					+ sField38 + " = '"+ NuevoGasto.getFEAUFA() + "', "
					+ sField39 + " = '"+ NuevoGasto.getCOTERR() + "', "
					+ sField40 + " = '"+ NuevoGasto.getFMPAGN() + "', "
					+ sField41 + " = '"+ NuevoGasto.getFEPGPR() + "', "
					+ sField42 + " = '"+ NuevoGasto.getFEAPLI() + "', "
					+ sField43 + " = '"+ NuevoGasto.getCOAPII() + "', "
					+ sField44 + " = '"+ NuevoGasto.getCOSPII() + "', "
					+ sField45 + " = '"+ NuevoGasto.getNUCLII() + 
					"' "+
					" WHERE "
					+ sField1 + " = '"+ sGastoID +"'");
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: GastoID:|{}|",sGastoID);
			
			logger.error("ERROR: COACES:|{}|",NuevoGasto.getCOACES());
			logger.error("ERROR: COGRUG:|{}|",NuevoGasto.getCOGRUG());
			logger.error("ERROR: COTPGA:|{}|",NuevoGasto.getCOTPGA());
			logger.error("ERROR: COSBGA:|{}|",NuevoGasto.getCOSBGA());

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

	public static boolean delMovimientoGasto(String sGastoID)
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
					" WHERE (" + sField1 + " = '" + sGastoID + "' )");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: GastoID:|{}|",sGastoID);

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

	public static MovimientoGasto getMovimientoGasto(String sGastoID)
	{
		Statement stmt = null;
		ResultSet rs = null;

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
		String sCOUNMO = "";
		String sIMIMGA = "";
		String sCOIMPT = "";
		String sCOTNEG = "";
		String sCOENCX = "";
		String sCOOFCX = "";
		String sNUCONE = "";
		String sNUPROF = "";
		String sFEAGTO = "";
		String sCOMONA = "";
		String sBIAUTO = "";
		String sFEAUFA = "";
		String sCOTERR = "";
		String sFMPAGN = "";
		String sFEPGPR = "";
		String sFEAPLI = "";
		String sCOAPII = "";
		String sCOSPII = "";
		String sNUCLII = "";

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
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
				       + sField33 + ","              
				       + sField34 + ","              
				       + sField35 + ","              
				       + sField36 + ","              
				       + sField37 + ","              
				       + sField38 + ","              
				       + sField39 + ","              
				       + sField40 + ","              
				       + sField41 + ","              
				       + sField42 + ","              
				       + sField43 + ","
				       + sField44 + ","  
				       + sField45 +       
			"  FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sGastoID	+ "')");

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
					sCOUNMO = rs.getString(sField27);
					sIMIMGA = rs.getString(sField28);
					sCOIMPT = rs.getString(sField29);
					sCOTNEG = rs.getString(sField30);
					sCOENCX = rs.getString(sField31);
					sCOOFCX = rs.getString(sField32);
					sNUCONE = rs.getString(sField33);
					sNUPROF = rs.getString(sField34);
					sFEAGTO = rs.getString(sField35);
					sCOMONA = rs.getString(sField36);
					sBIAUTO = rs.getString(sField37);
					sFEAUFA = rs.getString(sField38);
					sCOTERR = rs.getString(sField39);
					sFMPAGN = rs.getString(sField40);
					sFEPGPR = rs.getString(sField41);
					sFEAPLI = rs.getString(sField42);
					sCOAPII = rs.getString(sField43);
					sCOSPII = rs.getString(sField44);
					sNUCLII = rs.getString(sField45);
					
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
			logger.error("ERROR: GastoID:|{}|",sGastoID);

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
		return new MovimientoGasto(sCOACES, sCOGRUG, sCOTPGA, sCOSBGA, sPTPAGO, sFEDEVE,
				sFFGTVP, sFEPAGA, sFELIPG, sCOSIGA, sFEEESI, sFEECOI, sFEEAUI,
				sFEEPAI, sIMNGAS, sYCOS02, sIMRGAS, sYCOS04, sIMDGAS, sYCOS06,
				sIMCOST, sYCOS08, sIMOGAS, sYCOS10, sIMDTGA, sCOUNMO, sIMIMGA,
				sCOIMPT, sCOTNEG, sCOENCX, sCOOFCX, sNUCONE, sNUPROF, sFEAGTO,
				sCOMONA, sBIAUTO, sFEAUFA, sCOTERR, sFMPAGN, sFEPGPR, sFEAPLI,
				sCOAPII, sCOSPII, sNUCLII);
	}

	public static String getMovimientoGastoID(MovimientoGasto gasto)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sGastoID = "";

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
					"  FROM " + sTable + 
						" WHERE ("
					       + sField2  +" = '" + gasto.getCOACES() + "' AND "
					       + sField3  +" = '" + gasto.getCOGRUG() + "' AND "
					       + sField4  +" = '" + gasto.getCOTPGA() + "' AND "
					       + sField5  +" = '" + gasto.getCOSBGA() + "' AND "
					       + sField6  +" = '" + gasto.getPTPAGO() + "' AND "
					       + sField7  +" = '" + gasto.getFEDEVE() + "' AND "
					       + sField8  +" = '" + gasto.getFFGTVP() + "' AND "
					       + sField9  +" = '" + gasto.getFEPAGA() + "' AND "
					       + sField10 +" = '" + gasto.getFELIPG() + "' AND "
					       + sField11 +" = '" + gasto.getCOSIGA() + "' AND "
					       + sField12 +" = '" + gasto.getFEEESI() + "' AND "
					       + sField13 +" = '" + gasto.getFEECOI() + "' AND "
					       + sField14 +" = '" + gasto.getFEEAUI() + "' AND "
					       + sField15 +" = '" + gasto.getFEEPAI() + "' AND "
					       + sField16 +" = '" + gasto.getIMNGAS() + "' AND "
					       + sField17 +" = '" + gasto.getYCOS02() + "' AND "
					       + sField18 +" = '" + gasto.getIMRGAS() + "' AND "
					       + sField19 +" = '" + gasto.getYCOS04() + "' AND "
					       + sField20 +" = '" + gasto.getIMDGAS() + "' AND "
					       + sField21 +" = '" + gasto.getYCOS06() + "' AND "
					       + sField22 +" = '" + gasto.getIMCOST() + "' AND "
					       + sField23 +" = '" + gasto.getYCOS08() + "' AND "
					       + sField24 +" = '" + gasto.getIMOGAS() + "' AND "
					       + sField25 +" = '" + gasto.getYCOS10() + "' AND "
					       + sField26 +" = '" + gasto.getIMDTGA() + "' AND "
					       + sField27 +" = '" + gasto.getCOUNMO() + "' AND "
					       + sField28 +" = '" + gasto.getIMIMGA() + "' AND "
					       + sField29 +" = '" + gasto.getCOIMPT() + "' AND "
					       + sField30 +" = '" + gasto.getCOTNEG() + "' AND "
					       + sField31 +" = '" + gasto.getCOENCX() + "' AND "
					       + sField32 +" = '" + gasto.getCOOFCX() + "' AND "
					       + sField33 +" = '" + gasto.getNUCONE() + "' AND "
					       + sField34 +" = '" + gasto.getNUPROF() + "' AND "
					       + sField35 +" = '" + gasto.getFEAGTO() + "' AND "
					       + sField37 +" = '" + gasto.getBIAUTO() + "' AND "
					       + sField38 +" = '" + gasto.getFEAUFA() + "' AND "
					       + sField40 +" = '" + gasto.getFMPAGN() + "' AND "
					       + sField41 +" = '" + gasto.getFEPGPR() + "' AND "
					       + sField42 +" = '" + gasto.getFEAPLI() + "' AND "
					       + sField43 +" = '" + gasto.getCOAPII() + "' AND "
					       + sField44 +" = '" + gasto.getCOSPII() + "' AND "
					       + sField45 +" = '" + gasto.getNUCLII() + "' )"); 


			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sGastoID = rs.getString(sField1);
					
					
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
			logger.error("ERROR: COACES:|{}|",gasto.getCOACES());
			logger.error("ERROR: COGRUG:|{}|",gasto.getCOGRUG());
			logger.error("ERROR: COTPGA:|{}|",gasto.getCOTPGA());
			logger.error("ERROR: COSBGA:|{}|",gasto.getCOSBGA());

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
}
