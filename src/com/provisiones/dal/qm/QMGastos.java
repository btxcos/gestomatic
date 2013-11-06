
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
	
	public static final String TABLA = "pp001_ga_gastos_tbl";

	public static final String CAMPO1  = "ga_gasto_id";
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
	public static final String CAMPO27 = "imimga";     
	public static final String CAMPO28 = "cod_coimpt"; 
	public static final String CAMPO29 = "cod_cotneg"; 
	public static final String CAMPO30 = "feagto";     
	public static final String CAMPO31 = "cod_comona"; 
	public static final String CAMPO32 = "cod_biauto"; 
	public static final String CAMPO33 = "feaufa";     
	public static final String CAMPO34 = "fepgpr";     
	public static final String CAMPO35 = "cod_estado"; 

	
	public static int addGasto (Gasto NuevoGasto, String sEstado) 
	 
	{
		Connection conn = null;

		Statement stmt = null;
		ResultSet resulset = null;
		
		conn = ConnectionManager.getDBConnection();

		int iCodigo = 0;

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
			       + CAMPO34  + ","    
			       + CAMPO35 +               
          
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
			iCodigo = 0;
			
			logger.error("ERROR COACES:|"+NuevoGasto.getCOACES()+"|");
			logger.error("ERROR COGRUG:|"+NuevoGasto.getCOGRUG()+"|");
			logger.error("ERROR COTPGA:|"+NuevoGasto.getCOTPGA()+"|");
			logger.error("ERROR COSBGA:|"+NuevoGasto.getCOSBGA()+"|");
			logger.error("ERROR FEDEVE:|"+NuevoGasto.getFEDEVE()+"|");
			
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		
		return iCodigo;
	}

	public static boolean modGasto(Gasto NuevoGasto)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO6  + " = '"+ NuevoGasto.getPTPAGO() + "', "
				+ CAMPO8  + " = '"+ NuevoGasto.getFFGTVP() + "', "
				+ CAMPO9  + " = '"+ NuevoGasto.getFEPAGA() + "', "
				+ CAMPO10  + " = '"+ NuevoGasto.getFELIPG() + "', "
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
				+ CAMPO27 + " = '"+ NuevoGasto.getIMIMGA() + "', "
				+ CAMPO28 + " = '"+ NuevoGasto.getCOIMPT() + "', "
				+ CAMPO29 + " = '"+ NuevoGasto.getCOTNEG() + "', "
				+ CAMPO30 + " = '"+ NuevoGasto.getFEAGTO() + "', "
				+ CAMPO31 + " = '"+ NuevoGasto.getCOMONA() + "', "
				+ CAMPO32 + " = '"+ NuevoGasto.getBIAUTO() + "', "
				+ CAMPO33 + " = '"+ NuevoGasto.getFEAUFA() + "', "
				+ CAMPO34 + " = '"+ NuevoGasto.getFEPGPR() + 
				"' "+
				" WHERE " +
				"("	+ 
				CAMPO2  + " = '"+ NuevoGasto.getCOACES() +"' AND " +
				CAMPO3  + " = '"+ NuevoGasto.getCOGRUG() +"' AND " +
				CAMPO4  + " = '"+ NuevoGasto.getCOTPGA() +"' AND " +
				CAMPO5  + " = '"+ NuevoGasto.getCOSBGA() +"' AND " +
				CAMPO7  + " = '"+ NuevoGasto.getFEDEVE() + "' )";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);

			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR COACES:|"+NuevoGasto.getCOACES()+"|");
			logger.error("ERROR COGRUG:|"+NuevoGasto.getCOGRUG()+"|");
			logger.error("ERROR COTPGA:|"+NuevoGasto.getCOTPGA()+"|");
			logger.error("ERROR COSBGA:|"+NuevoGasto.getCOSBGA()+"|");
			logger.error("ERROR FEDEVE:|"+NuevoGasto.getFEDEVE()+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static boolean delGasto(String sGastoID)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true; 

		logger.debug("Ejecutando Query...");
		
		String sQuery = "DELETE FROM " 
				+ TABLA + 
				" WHERE "
				+ CAMPO1  + " = '"+ sGastoID +"'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR GASTO:|"+sGastoID+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static Gasto getGasto(String sGastoID)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

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
			       + CAMPO33  + ","
			       + CAMPO34 +      
			       "  FROM " 
			       + TABLA + 
			       " WHERE "
			       + CAMPO1  + " = '"+ sGastoID +"'";
		
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
					sIMIMGA = rs.getString(CAMPO27); 
					sCOIMPT = rs.getString(CAMPO28); 
					sCOTNEG = rs.getString(CAMPO29); 
					sFEAGTO = rs.getString(CAMPO30); 
					sCOMONA = rs.getString(CAMPO31); 
					sBIAUTO = rs.getString(CAMPO32); 
					sFEAUFA = rs.getString(CAMPO33); 
					sFEPGPR = rs.getString(CAMPO34); 
					
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
			logger.error("ERROR GASTO:|"+sGastoID+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		
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
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sGastoID = "";


		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
			       + CAMPO1 +      
			       " FROM " 
			       + TABLA + 
			       " WHERE "+
			       "("	
			       + CAMPO2  + " = '"+ sCodCOACES +"' AND " 
			       + CAMPO3  + " = '"+ sCodCOGRUG +"' AND "
			       + CAMPO4  + " = '"+ sCodCOTPGA +"' AND "
			       + CAMPO5  + " = '"+ sCodCOSBGA +"' AND "
			       + CAMPO7  + " = '"+ sFEDEVE + "' )";

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

					sGastoID = rs.getString(CAMPO1);  
					
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
			logger.error("ERROR COACES:|"+sCodCOACES+"|");
			logger.error("ERROR COGRUG:|"+sCodCOGRUG+"|");
			logger.error("ERROR COTPGA:|"+sCodCOTPGA+"|");
			logger.error("ERROR COSBGA:|"+sCodCOSBGA+"|");
			logger.error("ERROR FEDEVE:|"+sFEDEVE+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return sGastoID;
	}
	
	public static boolean existeGasto(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
					+ CAMPO1  +       
					" FROM " 
					+ TABLA + 
					" WHERE "+
					"("	+
					CAMPO2  + " = '"+ sCodCOACES +"' AND " +
					CAMPO3  + " = '"+ sCodCOGRUG +"' AND " +
					CAMPO4  + " = '"+ sCodCOTPGA +"' AND " +
					CAMPO5  + " = '"+ sCodCOSBGA +"' AND " +
					CAMPO7  + " = '"+ sFEDEVE + 
					"' )";
		
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
			logger.error("ERROR COACES:|"+sCodCOACES+"|");
			logger.error("ERROR COGRUG:|"+sCodCOGRUG+"|");
			logger.error("ERROR COTPGA:|"+sCodCOTPGA+"|");
			logger.error("ERROR COSBGA:|"+sCodCOSBGA+"|");
			logger.error("ERROR FEDEVE:|"+sFEDEVE+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return found;
	}
	
	public static boolean gastoAnulado(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
					+ CAMPO1 + 
					" FROM " 
					+ TABLA + 
					" WHERE " +
					"("	+ 
					CAMPO2  + " = '"+ sCodCOACES +"' AND " +
					CAMPO3  + " = '"+ sCodCOGRUG +"' AND " +
					CAMPO4  + " = '"+ sCodCOTPGA +"' AND " +
					CAMPO5  + " = '"+ sCodCOSBGA +"' AND " +
					CAMPO7  + " = '"+ sFEDEVE +"' AND " +
					CAMPO30 + " <> '"+  ValoresDefecto.CAMPO_SIN_INFORMAR +"' AND " +
				    CAMPO35  + " = '"+ ValoresDefecto.DEF_GASTO_ANULADO + 
				    "')";
		
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
			logger.error("ERROR COACES:|"+sCodCOACES+"|");
			logger.error("ERROR COGRUG:|"+sCodCOGRUG+"|");
			logger.error("ERROR COTPGA:|"+sCodCOTPGA+"|");
			logger.error("ERROR COSBGA:|"+sCodCOSBGA+"|");
			logger.error("ERROR FEDEVE:|"+sFEDEVE+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return found;
	}
	
	public static boolean setFechaAnulado(String sCodGasto, String sFEAGTO)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO30 + " = '"+ sFEAGTO + "' "+
				" WHERE "
				+ CAMPO1  + " = '"+ sCodGasto +"'";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR GASTO:|"+sCodGasto+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static boolean setEstado(String sCodGasto, String sEstado)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO35 + " = '"+ sEstado + "' "+
				" WHERE "
				+ CAMPO1  + " = '"+ sCodGasto +"'";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR GASTO:|"+sCodGasto+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	
	public static String getEstado(String sCodGasto)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sEstado = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
				+ CAMPO35 + 
				" FROM "
				+ TABLA + 
				" WHERE "
				+ CAMPO1  + " = '"+ sCodGasto +"'";
		
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

					sEstado = rs.getString(CAMPO35);
					
					
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|"+CAMPO35,sEstado);

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR GASTO:|"+sCodGasto);

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
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

		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO1+ 
				" FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO2 + " = '" + sCodCOACES + "'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
		
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(CAMPO1));
										
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|"+CAMPO2,sCodCOACES);
					logger.debug("{}:|"+CAMPO1,result.get(i));
				
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
			logger.error("ERROR COACES:|"+sCodCOACES);

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		//ConnectionManager.CloseDBConnection(conn);
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
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");

		String sQuery = "SELECT "
					
					   + QMActivos.CAMPO1 + ","        
					   + QMActivos.CAMPO14 + ","
					   + QMActivos.CAMPO11 + ","
					   + QMActivos.CAMPO13 + ","
					   + QMActivos.CAMPO6 + ","
					   + QMActivos.CAMPO9 + ","
					   + QMActivos.CAMPO7 + ","
					   + QMActivos.CAMPO10 + 

					   " FROM " + QMActivos.TABLA + 
					   " WHERE ("

					   + QMActivos.CAMPO14 + " LIKE '%" + activo.getCOPOIN()	+ "%' AND "  
					   + QMActivos.CAMPO11 + " LIKE '%" + activo.getNOMUIN()	+ "%' AND "  
					   + QMActivos.CAMPO13 + " LIKE '%" + activo.getNOPRAC()	+ "%' AND "  
					   + QMActivos.CAMPO6 + " LIKE '%" + activo.getNOVIAS()	+ "%' AND "  
					   + QMActivos.CAMPO9 + " LIKE '%" + activo.getNUPIAC()	+ "%' AND "  
					   + QMActivos.CAMPO7 + " LIKE '%" + activo.getNUPOAC()	+ "%' AND "  
					   + QMActivos.CAMPO10 + " LIKE '%" + activo.getNUPUAC()	+ "%' AND "			

					   + QMActivos.CAMPO1 +" IN (SELECT "
					   +  CAMPO2 + 
					   " FROM " + TABLA +
					   " WHERE " 
					   + CAMPO35 + " = '"+ ValoresDefecto.DEF_GASTO_ESTIMADO + "' " + " OR "
   					   + CAMPO35 + " = '"+ ValoresDefecto.DEF_GASTO_CONOCIDO + "' ) )";
		
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
					
					sCOACES = rs.getString(QMActivos.CAMPO1);
					sCOPOIN = rs.getString(QMActivos.CAMPO14);
					sNOMUIN = rs.getString(QMActivos.CAMPO11);
					sNOPRAC = rs.getString(QMActivos.CAMPO13);
					sNOVIAS = rs.getString(QMActivos.CAMPO6);
					sNUPIAC = rs.getString(QMActivos.CAMPO9);
					sNUPOAC = rs.getString(QMActivos.CAMPO7);
					sNUPUAC = rs.getString(QMActivos.CAMPO10);
					
					ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, "");
					
					result.add(activoencontrado);
					
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|"+QMActivos.CAMPO1,sCOACES);
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
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
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");

		String sQuery = "SELECT "
					
					   + CAMPO2 + ","        
					   + CAMPO3 + ","
					   + CAMPO4 + ","
					   + CAMPO5 + ","
					   + CAMPO6 + ","
					   + CAMPO7 + ","
					   + CAMPO11 + ","
					   + CAMPO16 + ","
					   + CAMPO17 +

					   " FROM " + TABLA + 
					   " WHERE (" +
					   "("
					   + CAMPO35 + " = '" + ValoresDefecto.DEF_GASTO_ESTIMADO + "' OR "
					   + CAMPO35 + " = '" + ValoresDefecto.DEF_GASTO_CONOCIDO + 					   
					   "') AND "					   
					   
					   + CAMPO7 + " <= '"+Utils.fechaDeHoy(false)+"')";					   
					   
		
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
					   
					
					sCOACES  = rs.getString(QMGastos.CAMPO2);
					sCOGRUG  = rs.getString(QMGastos.CAMPO3);
					sCOTPGA  = rs.getString(QMGastos.CAMPO4);
					sCOSBGA  = rs.getString(QMGastos.CAMPO5);
					sDCOSBGA = QMCodigosControl.getDesCOSBGA(sCOGRUG,sCOTPGA,sCOSBGA);
					sPTPAGO  = rs.getString(QMGastos.CAMPO6);
					sDPTPAGO = QMCodigosControl.getDesCampo(QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
					sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO7));
					sCOSIGA  = rs.getString(QMGastos.CAMPO11);
					sDCOSIGA = QMCodigosControl.getDesCampo(QMCodigosControl.TCOSIGA,QMCodigosControl.ICOSIGA,sCOSIGA);
					sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO17).equals("-"),rs.getString(QMGastos.CAMPO16));

 

					
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

					logger.debug(CAMPO2+":|"+sCodCOACES+"|");
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return result;
	}
}
