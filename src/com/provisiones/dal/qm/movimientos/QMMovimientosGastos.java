package com.provisiones.dal.qm.movimientos;

import com.provisiones.misc.Utils;
import com.provisiones.types.movimientos.MovimientoGasto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class QMMovimientosGastos
{
	private static Logger logger = LoggerFactory.getLogger(QMMovimientosGastos.class.getName());
	
	public static final String TABLA = "pp002_ga_movimientos_tbl";

	public static final String CAMPO1  = "ga_movimiento_id";
	
	public static final String CAMPO2  = "cod_coaces";
	public static final String CAMPO3  = "cod_cogrug";    
	public static final String CAMPO4  = "cotpga";    
	public static final String CAMPO5  = "cosbga";    
	public static final String CAMPO6  = "cod_ptpago";
	public static final String CAMPO7  = "fedeve";    
	public static final String CAMPO8  = "ffgtvp";    
	public static final String CAMPO9  = "fepaga";    
	public static final String CAMPO10 = "felipg";    
	public static final String CAMPO11 = "cod_cosiga";
	public static final String CAMPO12 = "feeesi";    
	public static final String CAMPO13 = "feecoi";    
	public static final String CAMPO14 = "feeaui";    
	public static final String CAMPO15 = "feepai";    
	public static final String CAMPO16 = "imngas";    
	public static final String CAMPO17 = "ycos02";    
	public static final String CAMPO18 = "imrgas";    
	public static final String CAMPO19 = "ycos04";    
	public static final String CAMPO20 = "imdgas";    
	public static final String CAMPO21 = "ycos06";    
	public static final String CAMPO22 = "imcost";    
	public static final String CAMPO23 = "ycos08";    
	public static final String CAMPO24 = "imogas";    
	public static final String CAMPO25 = "ycos10";    
	public static final String CAMPO26 = "imdtga";    
	public static final String CAMPO27 = "counmo";    
	public static final String CAMPO28 = "imimga";    
	public static final String CAMPO29 = "cod_coimpt";
	public static final String CAMPO30 = "cod_cotneg";
	public static final String CAMPO31 = "coencx";    
	public static final String CAMPO32 = "coofcx";    
	public static final String CAMPO33 = "nucone";    
	public static final String CAMPO34 = "nuprof";    
	public static final String CAMPO35 = "feagto";    
	public static final String CAMPO36 = "cod_comona";
	public static final String CAMPO37 = "cod_biauto";
	public static final String CAMPO38 = "feaufa";    
	public static final String CAMPO39 = "cod_coterr";
	public static final String CAMPO40 = "fmpagn";    
	public static final String CAMPO41 = "fepgpr";    
	public static final String CAMPO42 = "feapli";    
	public static final String CAMPO43 = "coapii";    
	public static final String CAMPO44 = "cospii";    
	public static final String CAMPO45 = "nuclii";

	private QMMovimientosGastos(){}
	
	public static int addMovimientoGasto(Connection conexion, MovimientoGasto NuevoGasto) 
	{
		int iCodigo = 0;
	
		if (conexion != null)
		{
			Statement stmt = null;
			ResultSet resulset = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "INSERT INTO " 
					 	+ TABLA + 
					 	" ("
					 	+ CAMPO2  + ","              
					 	+ CAMPO3  + ","              
					 	+ CAMPO4  + ","              
					 	+ CAMPO5  + ","              
					 	+ CAMPO6  + ","              
					 	+ CAMPO7  + ","              
					 	+ CAMPO8  + ","              
					 	+ CAMPO9  + ","              
					 	+ CAMPO10 + ","              
					 	+ CAMPO11 + ","              
					 	+ CAMPO12 + ","              
					 	+ CAMPO13 + ","              
					 	+ CAMPO14 + ","              
					 	+ CAMPO15 + ","              
					 	+ CAMPO16 + ","              
					 	+ CAMPO17 + ","              
					 	+ CAMPO18 + ","              
					 	+ CAMPO19 + ","              
					 	+ CAMPO20 + ","              
					 	+ CAMPO21 + ","              
					 	+ CAMPO22 + ","              
					 	+ CAMPO23 + ","              
					 	+ CAMPO24 + ","              
					 	+ CAMPO25 + ","              
					 	+ CAMPO26 + ","              
					 	+ CAMPO27 + ","              
					 	+ CAMPO28 + ","              
					 	+ CAMPO29 + ","              
					 	+ CAMPO30 + ","              
					 	+ CAMPO31 + ","              
					 	+ CAMPO32 + ","              
					 	+ CAMPO33 + ","              
					 	+ CAMPO34 + ","              
					 	+ CAMPO35 + ","              
					 	+ CAMPO36 + ","              
					 	+ CAMPO37 + ","              
					 	+ CAMPO38 + ","              
					 	+ CAMPO39 + ","              
					 	+ CAMPO40 + ","              
					 	+ CAMPO41 + ","              
					 	+ CAMPO42 + ","              
					 	+ CAMPO43 + ","
					 	+ CAMPO44 + ","
					 	+ CAMPO45 +                 
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
						+ NuevoGasto.getNUCLII() + 
						"')";
			
			logger.debug(sQuery);
			
			try 
			{
				stmt = conexion.createStatement();
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
				iCodigo = 0;
				
				logger.error("ERROR COACES:|"+NuevoGasto.getCOACES()+"|");
				logger.error("ERROR COGRUG:|"+NuevoGasto.getCOGRUG()+"|");
				logger.error("ERROR COTPGA:|"+NuevoGasto.getCOTPGA()+"|");
				logger.error("ERROR COSBGA:|"+NuevoGasto.getCOSBGA()+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
				Utils.closeResultSet(resulset);
			}
		}
		
		return iCodigo;
	}
	public static boolean modMovimientoGasto(Connection conexion, MovimientoGasto NuevoGasto, String sGastoID)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO2  + " = '"+ NuevoGasto.getCOACES() + "', "
					+ CAMPO3  + " = '"+ NuevoGasto.getCOGRUG() + "', "
					+ CAMPO4  + " = '"+ NuevoGasto.getCOTPGA() + "', "
					+ CAMPO5  + " = '"+ NuevoGasto.getCOSBGA() + "', "
					+ CAMPO6  + " = '"+ NuevoGasto.getPTPAGO() + "', "
					+ CAMPO7  + " = '"+ NuevoGasto.getFEDEVE() + "', "
					+ CAMPO8  + " = '"+ NuevoGasto.getFFGTVP() + "', "
					+ CAMPO9  + " = '"+ NuevoGasto.getFEPAGA() + "', "
					+ CAMPO10 + " = '"+ NuevoGasto.getFELIPG() + "', "
					+ CAMPO11 + " = '"+ NuevoGasto.getCOSIGA() + "', "
					+ CAMPO12 + " = '"+ NuevoGasto.getFEEESI() + "', "
					+ CAMPO13 + " = '"+ NuevoGasto.getFEECOI() + "', "
					+ CAMPO14 + " = '"+ NuevoGasto.getFEEAUI() + "', "
					+ CAMPO15 + " = '"+ NuevoGasto.getFEEPAI() + "', "
					+ CAMPO16 + " = '"+ NuevoGasto.getIMNGAS() + "', "
					+ CAMPO17 + " = '"+ NuevoGasto.getYCOS02() + "', "
					+ CAMPO18 + " = '"+ NuevoGasto.getIMRGAS() + "', "
					+ CAMPO19 + " = '"+ NuevoGasto.getYCOS04() + "', "
					+ CAMPO20 + " = '"+ NuevoGasto.getIMDGAS() + "', "
					+ CAMPO21 + " = '"+ NuevoGasto.getYCOS06() + "', "
					+ CAMPO22 + " = '"+ NuevoGasto.getIMCOST() + "', "
					+ CAMPO23 + " = '"+ NuevoGasto.getYCOS08() + "', "
					+ CAMPO24 + " = '"+ NuevoGasto.getIMOGAS() + "', "
					+ CAMPO25 + " = '"+ NuevoGasto.getYCOS10() + "', "
					+ CAMPO26 + " = '"+ NuevoGasto.getIMDTGA() + "', "
					+ CAMPO27 + " = '"+ NuevoGasto.getCOUNMO() + "', "
					+ CAMPO28 + " = '"+ NuevoGasto.getIMIMGA() + "', "
					+ CAMPO29 + " = '"+ NuevoGasto.getCOIMPT() + "', "
					+ CAMPO30 + " = '"+ NuevoGasto.getCOTNEG() + "', "
					+ CAMPO31 + " = '"+ NuevoGasto.getCOENCX() + "', "
					+ CAMPO32 + " = '"+ NuevoGasto.getCOOFCX() + "', "
					+ CAMPO33 + " = '"+ NuevoGasto.getNUCONE() + "', "
					+ CAMPO34 + " = '"+ NuevoGasto.getNUPROF() + "', "
					+ CAMPO35 + " = '"+ NuevoGasto.getFEAGTO() + "', "
					+ CAMPO36 + " = '"+ NuevoGasto.getCOMONA() + "', "
					+ CAMPO37 + " = '"+ NuevoGasto.getBIAUTO() + "', "
					+ CAMPO38 + " = '"+ NuevoGasto.getFEAUFA() + "', "
					+ CAMPO39 + " = '"+ NuevoGasto.getCOTERR() + "', "
					+ CAMPO40 + " = '"+ NuevoGasto.getFMPAGN() + "', "
					+ CAMPO41 + " = '"+ NuevoGasto.getFEPGPR() + "', "
					+ CAMPO42 + " = '"+ NuevoGasto.getFEAPLI() + "', "
					+ CAMPO43 + " = '"+ NuevoGasto.getCOAPII() + "', "
					+ CAMPO44 + " = '"+ NuevoGasto.getCOSPII() + "', "
					+ CAMPO45 + " = '"+ NuevoGasto.getNUCLII() + "' " +
					"WHERE "
					+ CAMPO1 + " = '"+ sGastoID +"'";
			
			logger.debug(sQuery);
			
			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con exito!");

				bSalida = true;
			} 
			catch (SQLException ex) 
			{
				bSalida = false;
				
				logger.error("ERROR GastoID:|"+sGastoID+"|");
				
				logger.error("ERROR COACES:|"+NuevoGasto.getCOACES()+"|");
				logger.error("ERROR COGRUG:|"+NuevoGasto.getCOGRUG()+"|");
				logger.error("ERROR COTPGA:|"+NuevoGasto.getCOTPGA()+"|");
				logger.error("ERROR COSBGA:|"+NuevoGasto.getCOSBGA()+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean delMovimientoGasto(Connection conexion, String sGastoID)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO1 + " = '" + sGastoID + "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con exito!");
				
				bSalida = true;
			} 
			catch (SQLException ex) 
			{
				bSalida = false;
				
				logger.error("ERROR GastoID:|"+sGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static MovimientoGasto getMovimientoGasto(Connection conexion, String sGastoID)
	{
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

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
				       + CAMPO2  + ","              
				       + CAMPO3  + ","              
				       + CAMPO4  + ","              
				       + CAMPO5  + ","              
				       + CAMPO6  + ","              
				       + CAMPO7  + ","              
				       + CAMPO8  + ","              
				       + CAMPO9  + ","              
				       + CAMPO10 + ","              
				       + CAMPO11 + ","              
				       + CAMPO12 + ","              
				       + CAMPO13 + ","              
				       + CAMPO14 + ","              
				       + CAMPO15 + ","              
				       + CAMPO16 + ","              
				       + CAMPO17 + ","              
				       + CAMPO18 + ","              
				       + CAMPO19 + ","              
				       + CAMPO20 + ","              
				       + CAMPO21 + ","              
				       + CAMPO22 + ","              
				       + CAMPO23 + ","              
				       + CAMPO24 + ","              
				       + CAMPO25 + ","              
				       + CAMPO26 + ","              
				       + CAMPO27 + ","              
				       + CAMPO28 + ","              
				       + CAMPO29 + ","              
				       + CAMPO30 + ","              
				       + CAMPO31 + ","              
				       + CAMPO32 + ","              
				       + CAMPO33 + ","              
				       + CAMPO34 + ","              
				       + CAMPO35 + ","              
				       + CAMPO36 + ","              
				       + CAMPO37 + ","              
				       + CAMPO38 + ","              
				       + CAMPO39 + ","              
				       + CAMPO40 + ","              
				       + CAMPO41 + ","              
				       + CAMPO42 + ","              
				       + CAMPO43 + ","
				       + CAMPO44 + ","  
				       + CAMPO45 +       
				       " FROM " 
				       + TABLA + 
				       " WHERE " 
				       + CAMPO1 + " = '" + sGastoID	+ "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						sCOACES = rs.getString(CAMPO2); 
						sCOGRUG = rs.getString(CAMPO3); 
						sCOTPGA = rs.getString(CAMPO4); 
						sCOSBGA = rs.getString(CAMPO5); 
						sPTPAGO = rs.getString(CAMPO6); 
						sFEDEVE = rs.getString(CAMPO7); 
						sFFGTVP = rs.getString(CAMPO8); 
						sFEPAGA = rs.getString(CAMPO9); 
						sFELIPG = rs.getString(CAMPO10);
						sCOSIGA = rs.getString(CAMPO11);
						sFEEESI = rs.getString(CAMPO12);
						sFEECOI = rs.getString(CAMPO13);
						sFEEAUI = rs.getString(CAMPO14);
						sFEEPAI = rs.getString(CAMPO15);
						sIMNGAS = rs.getString(CAMPO16);
						sYCOS02 = rs.getString(CAMPO17);
						sIMRGAS = rs.getString(CAMPO18);
						sYCOS04 = rs.getString(CAMPO19);
						sIMDGAS = rs.getString(CAMPO20);
						sYCOS06 = rs.getString(CAMPO21);
						sIMCOST = rs.getString(CAMPO22);
						sYCOS08 = rs.getString(CAMPO23);
						sIMOGAS = rs.getString(CAMPO24);
						sYCOS10 = rs.getString(CAMPO25);
						sIMDTGA = rs.getString(CAMPO26);
						sCOUNMO = rs.getString(CAMPO27);
						sIMIMGA = rs.getString(CAMPO28);
						sCOIMPT = rs.getString(CAMPO29);
						sCOTNEG = rs.getString(CAMPO30);
						sCOENCX = rs.getString(CAMPO31);
						sCOOFCX = rs.getString(CAMPO32);
						sNUCONE = rs.getString(CAMPO33);
						sNUPROF = rs.getString(CAMPO34);
						sFEAGTO = rs.getString(CAMPO35);
						sCOMONA = rs.getString(CAMPO36);
						sBIAUTO = rs.getString(CAMPO37);
						sFEAUFA = rs.getString(CAMPO38);
						sCOTERR = rs.getString(CAMPO39);
						sFMPAGN = rs.getString(CAMPO40);
						sFEPGPR = rs.getString(CAMPO41);
						sFEAPLI = rs.getString(CAMPO42);
						sCOAPII = rs.getString(CAMPO43);
						sCOSPII = rs.getString(CAMPO44);
						sNUCLII = rs.getString(CAMPO45);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+sGastoID+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sCOACES = "";
				sCOGRUG = "";
				sCOTPGA = "";
				sCOSBGA = "";
				sPTPAGO = "";
				sFEDEVE = "";
				sFFGTVP = "";
				sFEPAGA = "";
				sFELIPG = "";
				sCOSIGA = "";
				sFEEESI = "";
				sFEECOI = "";
				sFEEAUI = "";
				sFEEPAI = "";
				sIMNGAS = "";
				sYCOS02 = "";
				sIMRGAS = "";
				sYCOS04 = "";
				sIMDGAS = "";
				sYCOS06 = "";
				sIMCOST = "";
				sYCOS08 = "";
				sIMOGAS = "";
				sYCOS10 = "";
				sIMDTGA = "";
				sCOUNMO = "";
				sIMIMGA = "";
				sCOIMPT = "";
				sCOTNEG = "";
				sCOENCX = "";
				sCOOFCX = "";
				sNUCONE = "";
				sNUPROF = "";
				sFEAGTO = "";
				sCOMONA = "";
				sBIAUTO = "";
				sFEAUFA = "";
				sCOTERR = "";
				sFMPAGN = "";
				sFEPGPR = "";
				sFEAPLI = "";
				sCOAPII = "";
				sCOSPII = "";
				sNUCLII = "";
				
				logger.error("ERROR GastoID:|"+sGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new MovimientoGasto(sCOACES, sCOGRUG, sCOTPGA, sCOSBGA, sPTPAGO, sFEDEVE,
				sFFGTVP, sFEPAGA, sFELIPG, sCOSIGA, sFEEESI, sFEECOI, sFEEAUI,
				sFEEPAI, sIMNGAS, sYCOS02, sIMRGAS, sYCOS04, sIMDGAS, sYCOS06,
				sIMCOST, sYCOS08, sIMOGAS, sYCOS10, sIMDTGA, sCOUNMO, sIMIMGA,
				sCOIMPT, sCOTNEG, sCOENCX, sCOOFCX, sNUCONE, sNUPROF, sFEAGTO,
				sCOMONA, sBIAUTO, sFEAUFA, sCOTERR, sFMPAGN, sFEPGPR, sFEAPLI,
				sCOAPII, sCOSPII, sNUCLII);
	}

	public static String getMovimientoGastoID(Connection conexion, MovimientoGasto gasto)
	{
		String sGastoID = "";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
						   + CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE ("
					       + CAMPO2  +" = '" + gasto.getCOACES() + "' AND "
					       + CAMPO3  +" = '" + gasto.getCOGRUG() + "' AND "
					       + CAMPO4  +" = '" + gasto.getCOTPGA() + "' AND "
					       + CAMPO5  +" = '" + gasto.getCOSBGA() + "' AND "
					       + CAMPO6  +" = '" + gasto.getPTPAGO() + "' AND "
					       + CAMPO7  +" = '" + gasto.getFEDEVE() + "' AND "
					       + CAMPO8  +" = '" + gasto.getFFGTVP() + "' AND "
					       + CAMPO9  +" = '" + gasto.getFEPAGA() + "' AND "
					       + CAMPO10 +" = '" + gasto.getFELIPG() + "' AND "
					       + CAMPO11 +" = '" + gasto.getCOSIGA() + "' AND "
					       + CAMPO12 +" = '" + gasto.getFEEESI() + "' AND "
					       + CAMPO13 +" = '" + gasto.getFEECOI() + "' AND "
					       + CAMPO14 +" = '" + gasto.getFEEAUI() + "' AND "
					       + CAMPO15 +" = '" + gasto.getFEEPAI() + "' AND "
					       + CAMPO16 +" = '" + gasto.getIMNGAS() + "' AND "
					       + CAMPO17 +" = '" + gasto.getYCOS02() + "' AND "
					       + CAMPO18 +" = '" + gasto.getIMRGAS() + "' AND "
					       + CAMPO19 +" = '" + gasto.getYCOS04() + "' AND "
					       + CAMPO20 +" = '" + gasto.getIMDGAS() + "' AND "
					       + CAMPO21 +" = '" + gasto.getYCOS06() + "' AND "
					       + CAMPO22 +" = '" + gasto.getIMCOST() + "' AND "
					       + CAMPO23 +" = '" + gasto.getYCOS08() + "' AND "
					       + CAMPO24 +" = '" + gasto.getIMOGAS() + "' AND "
					       + CAMPO25 +" = '" + gasto.getYCOS10() + "' AND "
					       + CAMPO26 +" = '" + gasto.getIMDTGA() + "' AND "
					       + CAMPO27 +" = '" + gasto.getCOUNMO() + "' AND "
					       + CAMPO28 +" = '" + gasto.getIMIMGA() + "' AND "
					       + CAMPO29 +" = '" + gasto.getCOIMPT() + "' AND "
					       + CAMPO30 +" = '" + gasto.getCOTNEG() + "' AND "
					       + CAMPO31 +" = '" + gasto.getCOENCX() + "' AND "
					       + CAMPO32 +" = '" + gasto.getCOOFCX() + "' AND "
					       + CAMPO33 +" = '" + gasto.getNUCONE() + "' AND "
					       + CAMPO34 +" = '" + gasto.getNUPROF() + "' AND "
					       + CAMPO35 +" = '" + gasto.getFEAGTO() + "' AND "
					       + CAMPO37 +" = '" + gasto.getBIAUTO() + "' AND "
					       + CAMPO38 +" = '" + gasto.getFEAUFA() + "' AND "
					       + CAMPO40 +" = '" + gasto.getFMPAGN() + "' AND "
					       + CAMPO41 +" = '" + gasto.getFEPGPR() + "' AND "
					       //+ CAMPO42 +" = '" + gasto.getFEAPLI() + "' AND "
					       + CAMPO43 +" = '" + gasto.getCOAPII() + "' AND "
					       + CAMPO44 +" = '" + gasto.getCOSPII() + "' AND "
					       + CAMPO45 +" = '" + gasto.getNUCLII() + 
					       "')";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery); 
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						sGastoID = rs.getString(CAMPO1);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+sGastoID+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sGastoID = "";
				
				logger.error("ERROR COACES:|"+gasto.getCOACES()+"|");
				logger.error("ERROR COGRUG:|"+gasto.getCOGRUG()+"|");
				logger.error("ERROR COTPGA:|"+gasto.getCOTPGA()+"|");
				logger.error("ERROR COSBGA:|"+gasto.getCOSBGA()+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sGastoID;
	}
	
	public static String getMovimientoGastoIDAutorizado(Connection conexion, MovimientoGasto gasto)
	{
		String sGastoID = "";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
						   + CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE ("
					       + CAMPO2  +" = '" + gasto.getCOACES() + "' AND "
					       + CAMPO3  +" = '" + gasto.getCOGRUG() + "' AND "
					       + CAMPO4  +" = '" + gasto.getCOTPGA() + "' AND "
					       + CAMPO5  +" = '" + gasto.getCOSBGA() + "' AND "
					       + CAMPO6  +" = '" + gasto.getPTPAGO() + "' AND "
					       + CAMPO7  +" = '" + gasto.getFEDEVE() + "' AND "
					       + CAMPO8  +" = '" + gasto.getFFGTVP() + "' AND "
					       + CAMPO9  +" = '" + gasto.getFEPAGA() + "' AND "
					       + CAMPO10 +" = '" + gasto.getFELIPG() + "' AND "
					       + CAMPO11 +" = '" + gasto.getCOSIGA() + "' AND "
					       + CAMPO12 +" = '" + gasto.getFEEESI() + "' AND "
					       + CAMPO13 +" = '" + gasto.getFEECOI() + "' AND "
					       //+ CAMPO14 +" = '" + gasto.getFEEAUI() + "' AND "
					       + CAMPO15 +" = '" + gasto.getFEEPAI() + "' AND "
					       + CAMPO16 +" = '" + gasto.getIMNGAS() + "' AND "
					       + CAMPO17 +" = '" + gasto.getYCOS02() + "' AND "
					       + CAMPO18 +" = '" + gasto.getIMRGAS() + "' AND "
					       + CAMPO19 +" = '" + gasto.getYCOS04() + "' AND "
					       + CAMPO20 +" = '" + gasto.getIMDGAS() + "' AND "
					       + CAMPO21 +" = '" + gasto.getYCOS06() + "' AND "
					       + CAMPO22 +" = '" + gasto.getIMCOST() + "' AND "
					       + CAMPO23 +" = '" + gasto.getYCOS08() + "' AND "
					       + CAMPO24 +" = '" + gasto.getIMOGAS() + "' AND "
					       + CAMPO25 +" = '" + gasto.getYCOS10() + "' AND "
					       + CAMPO26 +" = '" + gasto.getIMDTGA() + "' AND "
					       + CAMPO27 +" = '" + gasto.getCOUNMO() + "' AND "
					       + CAMPO28 +" = '" + gasto.getIMIMGA() + "' AND "
					       //+ CAMPO29 +" = '" + gasto.getCOIMPT() + "' AND "
					       //+ CAMPO30 +" = '" + gasto.getCOTNEG() + "' AND "
					       + CAMPO31 +" = '" + gasto.getCOENCX() + "' AND "
					       + CAMPO32 +" = '" + gasto.getCOOFCX() + "' AND "
					       + CAMPO33 +" = '" + gasto.getNUCONE() + "' AND "
					       + CAMPO34 +" = '" + gasto.getNUPROF() + "' AND "
					       + CAMPO35 +" = '" + gasto.getFEAGTO() + "' AND "
					      // + CAMPO37 +" = '" + gasto.getBIAUTO() + "' AND "
					      // + CAMPO38 +" = '" + gasto.getFEAUFA() + "' AND "
					       + CAMPO40 +" = '" + gasto.getFMPAGN() + "' AND "
					       + CAMPO41 +" = '" + gasto.getFEPGPR() + "') ";
					       /*AND "
					       + CAMPO42 +" = '" + gasto.getFEAPLI() + "' AND "
					       + CAMPO43 +" = '" + gasto.getCOAPII() + "' AND "
					       + CAMPO44 +" = '" + gasto.getCOSPII() + "' AND "
					       + CAMPO45 +" = '" + gasto.getNUCLII() + 
					       "')";*/
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery); 
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						sGastoID = rs.getString(CAMPO1);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+sGastoID+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sGastoID = "";
				
				logger.error("ERROR COACES:|"+gasto.getCOACES()+"|");
				logger.error("ERROR COGRUG:|"+gasto.getCOGRUG()+"|");
				logger.error("ERROR COTPGA:|"+gasto.getCOTPGA()+"|");
				logger.error("ERROR COSBGA:|"+gasto.getCOSBGA()+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sGastoID;
	}

	public static boolean existeMovimientoGasto(Connection conexion, String sMovimientoGastoID)
	{
		boolean bEncontrado = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO1 + 
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '" + sMovimientoGastoID + "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");
				
				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+rs.getString(CAMPO1)+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la información.");
				}
			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR sMovimientoGastoID:|"+sMovimientoGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bEncontrado;
	}
	
}
