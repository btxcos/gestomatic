
package com.provisiones.dal.qm;

import com.provisiones.dal.qm.listas.QMListaAbonosGastos;
import com.provisiones.dal.qm.listas.QMListaGastosProvisiones;
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

public final class QMGastos
{
	private static Logger logger = LoggerFactory.getLogger(QMGastos.class.getName());
	
	public static final String TABLA = "pp002_ga_gastos_tbl";

	public static final String CAMPO1  = "ga_gasto_id";
	public static final String CAMPO2  = "cod_coaces"; 
	public static final String CAMPO3  = "cod_cogrug";
	public static final String CAMPO4  = "cotpga";     
	public static final String CAMPO5  = "cosbga";     
	public static final String CAMPO6  = "cod_ptpago";
	public static final String CAMPO7  = "fedeve";     
	public static final String CAMPO8  = "ffgtvp";     
	public static final String CAMPO9  = "felipg";     
	public static final String CAMPO10 = "cod_cosiga";
	public static final String CAMPO11 = "feeesi";     
	public static final String CAMPO12 = "feecoi";     
	public static final String CAMPO13 = "feeaui";     
	public static final String CAMPO14 = "feepai";     
	public static final String CAMPO15 = "imngas";     
	public static final String CAMPO16 = "ycos02";     
	public static final String CAMPO17 = "imrgas";     
	public static final String CAMPO18 = "ycos04";     
	public static final String CAMPO19 = "imdgas";
	public static final String CAMPO20 = "ycos06";     
	public static final String CAMPO21 = "imcost";     
	public static final String CAMPO22 = "ycos08";     
	public static final String CAMPO23 = "imogas";     
	public static final String CAMPO24 = "ycos10";     
	public static final String CAMPO25 = "imdtga";     
	public static final String CAMPO26 = "imimga";     
	public static final String CAMPO27 = "cod_coimpt"; 
	public static final String CAMPO28 = "cod_cotneg"; 
	public static final String CAMPO29 = "feagto";     
	public static final String CAMPO30 = "cod_comona"; 
	public static final String CAMPO31 = "cod_biauto"; 
	public static final String CAMPO32 = "feaufa";     
	public static final String CAMPO33 = "valor_total";
	public static final String CAMPO34 = "cod_estado";
	public static final String CAMPO35 = "urgente";
	public static final String CAMPO36 = "nota";
	
	private QMGastos(){}
	
	public static long addGasto(Connection conexion, Gasto NuevoGasto, String sEstado, byte btUrgente, String sNota) 
	{
		long iCodigo = 0;

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
				       + CAMPO36 +               
	          
				       ") VALUES ('"        
				       + NuevoGasto.getCOACES() + "','"
				       + NuevoGasto.getCOGRUG() + "','"
				       + NuevoGasto.getCOTPGA() + "','"  
				       + NuevoGasto.getCOSBGA() + "','"  
				       + NuevoGasto.getPTPAGO() + "','"  
				       + NuevoGasto.getFEDEVE() + "','"  
				       + NuevoGasto.getFFGTVP() + "','"  
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
				       + NuevoGasto.getValor_total() + "','"
				       + sEstado + "', "
				       + btUrgente + ", "
				       + "AES_ENCRYPT('"+sNota+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))" + 
				       ")";
			
			logger.debug(sQuery);

			try 
			{
				
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery, Statement.RETURN_GENERATED_KEYS);

				resulset = stmt.getGeneratedKeys();
				
				if (resulset.next()) 
				{
					iCodigo= resulset.getLong(1);
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
		}
		
		return iCodigo;
	}

	public static boolean modGasto(Connection conexion, Gasto NuevoGasto)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO6  + " = '"+ NuevoGasto.getPTPAGO() + "', "
					+ CAMPO8  + " = '"+ NuevoGasto.getFFGTVP() + "', "
					+ CAMPO9  + " = '"+ NuevoGasto.getFELIPG() + "', "
					+ CAMPO10 + " = '"+ NuevoGasto.getCOSIGA() + "', "
					+ CAMPO11 + " = '"+ NuevoGasto.getFEEESI() + "', "
					+ CAMPO12 + " = '"+ NuevoGasto.getFEECOI() + "', "
					+ CAMPO13 + " = '"+ NuevoGasto.getFEEAUI() + "', "
					+ CAMPO14 + " = '"+ NuevoGasto.getFEEPAI() + "', "
					+ CAMPO15 + " = '"+ NuevoGasto.getIMNGAS() + "', "
					+ CAMPO16 + " = '"+ NuevoGasto.getYCOS02() + "', "
					+ CAMPO17 + " = '"+ NuevoGasto.getIMRGAS() + "', "
					+ CAMPO18 + " = '"+ NuevoGasto.getYCOS04() + "', "
					+ CAMPO19 + " = '"+ NuevoGasto.getIMDGAS() + "', "
					+ CAMPO20 + " = '"+ NuevoGasto.getYCOS06() + "', "
					+ CAMPO21 + " = '"+ NuevoGasto.getIMCOST() + "', "
					+ CAMPO22 + " = '"+ NuevoGasto.getYCOS08() + "', "
					+ CAMPO23 + " = '"+ NuevoGasto.getIMOGAS() + "', "
					+ CAMPO24 + " = '"+ NuevoGasto.getYCOS10() + "', "
					+ CAMPO25 + " = '"+ NuevoGasto.getIMDTGA() + "', "
					+ CAMPO26 + " = '"+ NuevoGasto.getIMIMGA() + "', "
					+ CAMPO27 + " = '"+ NuevoGasto.getCOIMPT() + "', "
					+ CAMPO28 + " = '"+ NuevoGasto.getCOTNEG() + "', "
					+ CAMPO29 + " = '"+ NuevoGasto.getFEAGTO() + "', "
					+ CAMPO30 + " = '"+ NuevoGasto.getCOMONA() + "', "
					+ CAMPO31 + " = '"+ NuevoGasto.getBIAUTO() + "', "
					+ CAMPO32 + " = '"+ NuevoGasto.getFEAUFA() + "', "
					+ CAMPO33 + " = '"+ NuevoGasto.getValor_total() +

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
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);

				logger.debug("Ejecutada con exito!");
				
				bSalida = true;
				
			} 
			catch (SQLException ex) 
			{
				bSalida = false;

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
		}

		return bSalida;
	}
	
	public static boolean modImportes(Connection conexion, Gasto NuevoGasto, long liCodGasto)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO15 + " = '"+ NuevoGasto.getIMNGAS() + "', "
					+ CAMPO16 + " = '"+ NuevoGasto.getYCOS02() + "', "
					+ CAMPO17 + " = '"+ NuevoGasto.getIMRGAS() + "', "
					+ CAMPO18 + " = '"+ NuevoGasto.getYCOS04() + "', "
					+ CAMPO19 + " = '"+ NuevoGasto.getIMDGAS() + "', "
					+ CAMPO20 + " = '"+ NuevoGasto.getYCOS06() + "', "
					+ CAMPO21 + " = '"+ NuevoGasto.getIMCOST() + "', "
					+ CAMPO22 + " = '"+ NuevoGasto.getYCOS08() + "', "
					+ CAMPO23 + " = '"+ NuevoGasto.getIMOGAS() + "', "
					+ CAMPO24 + " = '"+ NuevoGasto.getYCOS10() + "', "
					+ CAMPO25 + " = '"+ NuevoGasto.getIMDTGA() + "', "
					+ CAMPO26 + " = '"+ NuevoGasto.getIMIMGA() + "', "
					+ CAMPO33 + " = '"+ NuevoGasto.getValor_total() +

					"' "+
					" WHERE "
					+ CAMPO1  + " = "+ liCodGasto;
			
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
		}

		return bSalida;
	}

	public static boolean delGasto(Connection conexion, long liGastoID)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO1  + " = '"+ liGastoID +"'";
			
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

				logger.error("ERROR GASTO:|"+liGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static Gasto getGasto(Connection conexion, long liGastoID)
	{
		String sCOACES = "";
		String sCOGRUG = "";
		String sCOTPGA = "";
		String sCOSBGA = "";
		String sPTPAGO = "";
		String sFEDEVE = "";
		String sFFGTVP = "";
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
				       + CAMPO32 +      
				       " FROM " 
				       + TABLA + 
				       " WHERE "
				       + CAMPO1  + " = '"+ liGastoID +"'";
			
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
						sFELIPG = rs.getString(CAMPO9);  
						sCOSIGA = rs.getString(CAMPO10); 
						sFEEESI = rs.getString(CAMPO11); 
						sFEECOI = rs.getString(CAMPO12); 
						sFEEAUI = rs.getString(CAMPO13); 
						sFEEPAI = rs.getString(CAMPO14); 
						sIMNGAS = rs.getString(CAMPO15); 
						sYCOS02 = rs.getString(CAMPO16); 
						sIMRGAS = rs.getString(CAMPO17); 
						sYCOS04 = rs.getString(CAMPO18); 
						sIMDGAS = rs.getString(CAMPO19); 
						sYCOS06 = rs.getString(CAMPO20); 
						sIMCOST = rs.getString(CAMPO21); 
						sYCOS08 = rs.getString(CAMPO22); 
						sIMOGAS = rs.getString(CAMPO23); 
						sYCOS10 = rs.getString(CAMPO24); 
						sIMDTGA = rs.getString(CAMPO25); 
						sIMIMGA = rs.getString(CAMPO26); 
						sCOIMPT = rs.getString(CAMPO27); 
						sCOTNEG = rs.getString(CAMPO28); 
						sFEAGTO = rs.getString(CAMPO29); 
						sCOMONA = rs.getString(CAMPO30); 
						sBIAUTO = rs.getString(CAMPO31); 
						sFEAUFA = rs.getString(CAMPO32); 
						
						logger.debug("Encontrado el registro!");

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
				sIMIMGA = "";
				sCOIMPT = "";
				sCOTNEG = "";
				sFEAGTO = "";
				sCOMONA = "";
				sBIAUTO = "";
				sFEAUFA = "";

				logger.error("ERROR GASTO:|"+liGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}
		
		return new Gasto(
				sCOACES, 
				sCOGRUG, 
				sCOTPGA, 
				sCOSBGA,
				sPTPAGO, 
				sFEDEVE, 
				sFFGTVP, 
				sFELIPG, 
				sCOSIGA, 
				sFEEESI, 
				sFEECOI,
				sFEEAUI, 
				sFEEPAI, 
				sIMNGAS, 
				sYCOS02, 
				sIMRGAS, 
				sYCOS04, 
				sIMDGAS,
				sYCOS06, 
				sIMCOST, 
				sYCOS08, 
				sIMOGAS, 
				sYCOS10, 
				sIMDTGA, 
				sIMIMGA,
				sCOIMPT, 
				sCOTNEG, 
				sFEAGTO, 
				sCOMONA, 
				sBIAUTO, 
				sFEAUFA);
	}
	
	
	public static Gasto getDetallesGasto(Connection conexion, long liGastoID)
	{
		String sCOACES = "";
		String sCOGRUG = "";
		String sCOTPGA = "";
		String sCOSBGA = "";
		String sPTPAGO = "";
		String sFEDEVE = "";
		String sFFGTVP = "";
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
				       + CAMPO32 +      
				       " FROM " 
				       + TABLA + 
				       " WHERE "
				       + CAMPO1  + " = '"+ liGastoID +"'";
			
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
						sCOGRUG = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOGRUG, QMCodigosControl.ICOGRUG, rs.getString(CAMPO3));
						sCOTPGA = QMCodigosControl.getDesCOTPGA(conexion, rs.getString(CAMPO3), rs.getString(CAMPO4));
						
						sCOSBGA = QMCodigosControl.getDesCOSBGA(conexion, rs.getString(CAMPO3), rs.getString(CAMPO4), rs.getString(CAMPO5));
 
						sPTPAGO = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TPTPAGO, QMCodigosControl.IPTPAGO, rs.getString(CAMPO6));  
						sFEDEVE = rs.getString(CAMPO7);
						sFFGTVP = rs.getString(CAMPO8);  
						sFELIPG = rs.getString(CAMPO9);  
						sCOSIGA = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST, QMCodigosControl.IESGAST,getEstado(conexion,liGastoID)); 
						sFEEESI = rs.getString(CAMPO11); 
						sFEECOI = rs.getString(CAMPO12); 
						sFEEAUI = rs.getString(CAMPO13); 
						sFEEPAI = rs.getString(CAMPO14); 
						sIMNGAS = rs.getString(CAMPO15); 
						sYCOS02 = rs.getString(CAMPO16); 
						sIMRGAS = rs.getString(CAMPO17); 
						sYCOS04 = rs.getString(CAMPO18); 
						sIMDGAS = rs.getString(CAMPO19); 
						sYCOS06 = rs.getString(CAMPO20); 
						sIMCOST = rs.getString(CAMPO21); 
						sYCOS08 = rs.getString(CAMPO22); 
						sIMOGAS = rs.getString(CAMPO23); 
						sYCOS10 = rs.getString(CAMPO24); 
						sIMDTGA = rs.getString(CAMPO25); 
						sIMIMGA = rs.getString(CAMPO26); 
						sCOIMPT = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOIMPT, QMCodigosControl.ICOIMPT, rs.getString(CAMPO27)); 
						sCOTNEG = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOTNEG, QMCodigosControl.ICOTNEG, rs.getString(CAMPO28)); 
						sFEAGTO = rs.getString(CAMPO29); 
						sCOMONA = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOMONA, QMCodigosControl.ICOMONA, rs.getString(CAMPO30)); 
						sBIAUTO = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TBIAUTO, QMCodigosControl.IBIAUTO, rs.getString(CAMPO31)); 
						sFEAUFA = rs.getString(CAMPO32); 
						
						logger.debug("Encontrado el registro!");

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
				sIMIMGA = "";
				sCOIMPT = "";
				sCOTNEG = "";
				sFEAGTO = "";
				sCOMONA = "";
				sBIAUTO = "";
				sFEAUFA = "";

				logger.error("ERROR GASTO:|"+liGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}
		
		return new Gasto(sCOACES, sCOGRUG, sCOTPGA, sCOSBGA,
				sPTPAGO, sFEDEVE, sFFGTVP, sFELIPG, sCOSIGA, sFEEESI, sFEECOI,
				sFEEAUI, sFEEPAI, sIMNGAS, sYCOS02, sIMRGAS, sYCOS04, sIMDGAS,
				sYCOS06, sIMCOST, sYCOS08, sIMOGAS, sYCOS10, sIMDTGA, sIMIMGA,
				sCOIMPT, sCOTNEG, sFEAGTO, sCOMONA, sBIAUTO, sFEAUFA);
	}
	
	public static long getGastoID(Connection conexion, int iCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{
		long liGastoID = 0;

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
				       " WHERE "+
				       "("	
				       + CAMPO2  + " = '"+ iCodCOACES +"' AND " 
				       + CAMPO3  + " = '"+ sCodCOGRUG +"' AND "
				       + CAMPO4  + " = '"+ sCodCOTPGA +"' AND "
				       + CAMPO5  + " = '"+ sCodCOSBGA +"' AND "
				       + CAMPO7  + " = '"+ sFEDEVE + "' )";

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

						liGastoID = rs.getLong(CAMPO1);  
						
						logger.debug(CAMPO1+":|"+liGastoID+"|");
						
						logger.debug("Encontrado el registro!");

					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				liGastoID = 0;

				logger.error("ERROR COACES:|"+iCodCOACES+"|");
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
		}

		return liGastoID;
	}
	
	public static boolean existeGasto(Connection conexion, int iCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
						+ CAMPO1  +       
						" FROM " 
						+ TABLA + 
						" WHERE ("	
						+ CAMPO2  + " = '"+ iCodCOACES +"' AND " 
						+ CAMPO3  + " = '"+ sCodCOGRUG +"' AND " 
						+ CAMPO4  + " = '"+ sCodCOTPGA +"' AND " 
						+ CAMPO5  + " = '"+ sCodCOSBGA +"' AND " 
						+ CAMPO7  + " = '"+ sFEDEVE + 
						"' )";
			
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
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR COACES:|"+iCodCOACES+"|");
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
		}

		return bEncontrado;
	}
	
	public static boolean tieneGastos(Connection conexion, int iCodCOACES)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
						+ CAMPO1  +       
						" FROM " 
						+ TABLA + 
						" WHERE "	
						+ CAMPO2  + " = '"+ iCodCOACES +"' ";
			
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
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR COACES:|"+iCodCOACES+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return bEncontrado;
	}
	
	public static boolean esAbono(Connection conexion, long liGastoID)
	{
		//TODO repasar
		boolean bEncontrado = false;
		
		boolean bAbono = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
						+ CAMPO16  +       
						" FROM " 
						+ TABLA + 
						" WHERE "	
						+ CAMPO1  + " = '"+ liGastoID + "'";
			
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
						
						bAbono = rs.getString(CAMPO16).equals(ValoresDefecto.DEF_NEGATIVO);

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				bAbono = false;

				logger.error("ERROR GASTO:|"+liGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return bAbono;
	}
	
	public static boolean estaAbonando(Connection conexion, long liGastoID)
	{
		//TODO repasar
		boolean bEncontrado = false;
		
		boolean bAbono = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
						+ CAMPO16  +       
						" FROM " 
						+ TABLA + 
						" WHERE "	
						+ CAMPO1  + " = '"+ liGastoID + "'";
			
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
						
						bAbono = rs.getString(CAMPO16).equals(ValoresDefecto.DEF_NEGATIVO);

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				bAbono = false;

				logger.error("ERROR GASTO:|"+liGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return bAbono;
	}
	
	public static boolean gastoAnulado(Connection conexion, int iCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
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
						" WHERE ("	
						+ CAMPO2  + " = '"+ iCodCOACES +"' AND " 
						+ CAMPO3  + " = '"+ sCodCOGRUG +"' AND " 
						+ CAMPO4  + " = '"+ sCodCOTPGA +"' AND " 
						+ CAMPO5  + " = '"+ sCodCOSBGA +"' AND " 
						+ CAMPO7  + " = '"+ sFEDEVE +"' AND " 
						+ CAMPO29 + " <> '"+  ValoresDefecto.CAMPO_NUME_SIN_INFORMAR +"' AND " 
						+ CAMPO34  + " = '"+ ValoresDefecto.DEF_GASTO_ANULADO + 
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

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR COACES:|"+iCodCOACES+"|");
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
		}

		return bEncontrado;
	}
	
	public static boolean setFechaAnulado(Connection conexion, long liGastoID, String sFEAGTO)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO29 + " = '"+ sFEAGTO + "' "+
					" WHERE "
					+ CAMPO1  + " = '"+ liGastoID +"'";
			
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

				logger.error("ERROR GASTO:|"+liGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean setFechaPagado(Connection conexion, long liGastoID, String sFEEPAI)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO14 + " = '"+ sFEEPAI + "' "+
					" WHERE "
					+ CAMPO1  + " = '"+ liGastoID +"'";
			
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

				logger.error("ERROR GASTO:|"+liGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean setAbonado(Connection conexion, long liGastoID, String sFEPGPR)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO10 + " = '"+ ValoresDefecto.DEF_GASTO_ABONADO + "', "
					+ CAMPO14 + " = '"+ sFEPGPR + "', "
					+ CAMPO34 + " = '"+ ValoresDefecto.DEF_GASTO_PAGADO + "'" +
					" WHERE "
					+ CAMPO1  + " = '"+ liGastoID +"'";
			
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

				logger.error("ERROR GASTO:|"+liGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean setAutorizado(Connection conexion, long liGastoID, String sFEEAUI, String sFEAUFA)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO13 + " = '"+ sFEEAUI + "', "
					+ CAMPO31 + " = '"+ ValoresDefecto.DEF_BIAUTO_AUTORIZADO + "', "
					+ CAMPO32 + " = '"+ sFEAUFA + "', "
					+ CAMPO34 + " = '"+ ValoresDefecto.DEF_GASTO_AUTORIZADO + "' "+
					" WHERE "
					+ CAMPO1  + " = '"+ liGastoID +"'";
			
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

				logger.error("ERROR GASTO:|"+liGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean setAutorizadoUrgente(Connection conexion, long liGastoID, String sFEEAUI, String sFEAUFA)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO13 + " = '"+ sFEEAUI + "', "
					+ CAMPO31 + " = '"+ ValoresDefecto.DEF_BIAUTO_AUTORIZADO + "', "
					+ CAMPO32 + " = '"+ sFEAUFA + "' "+
					" WHERE "
					+ CAMPO1  + " = '"+ liGastoID +"'";
			
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

				logger.error("ERROR GASTO:|"+liGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean setPagado(Connection conexion, long liGastoID, String sFEEPAI)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET "
					+ CAMPO10 + " = '"+ ValoresDefecto.DEF_GASTO_PAGADO + "', "
					+ CAMPO14 + " = '"+ sFEEPAI + "', "
					+ CAMPO34 + " = '"+ ValoresDefecto.DEF_GASTO_PAGADO + "' "+
					" WHERE "
					+ CAMPO1  + " = '"+ liGastoID +"'";
			
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

				logger.error("ERROR GASTO:|"+liGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean setPagadoAbonado(Connection conexion, long liGastoID, String sFEEPAI)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET "
					+ CAMPO14 + " = '"+ sFEEPAI + "', "
					+ CAMPO34 + " = '"+ ValoresDefecto.DEF_GASTO_ABONADO + "' "+
					" WHERE "
					+ CAMPO1  + " = '"+ liGastoID +"'";
			
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

				logger.error("ERROR GASTO:|"+liGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean setPagadoConexion(Connection conexion, long liGastoID, String sFEEPAI)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET "
					+ CAMPO10 + " = '"+ ValoresDefecto.DEF_GASTO_PAGADO_CONEXION + "', "
					+ CAMPO14 + " = '"+ sFEEPAI + "', "
					+ CAMPO34 + " = '"+ ValoresDefecto.DEF_GASTO_PAGADO_CONEXION + "' "+
					" WHERE "
					+ CAMPO1  + " = '"+ liGastoID +"'";
			
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

				logger.error("ERROR GASTO:|"+liGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean setCOSIGA(Connection conexion, long liGastoID, String sCOSIGA)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO10 + " = '"+ sCOSIGA + "' "+
					" WHERE "
					+ CAMPO1  + " = '"+ liGastoID +"'";
			
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

				logger.error("ERROR GASTO:|"+liGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}
		
		return bSalida;
	}
	
	public static String getCOSIGA(Connection conexion, long liGastoID)
	{
		String sCOSIGA = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					+ CAMPO10 + 
					" FROM "
					+ TABLA + 
					" WHERE "
					+ CAMPO1  + " = '"+ liGastoID +"'";
			
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

						sCOSIGA = rs.getString(CAMPO10);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO10+":|"+sCOSIGA+"|");

					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				sCOSIGA = "";

				logger.error("ERROR GASTO:|"+liGastoID);

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sCOSIGA;
	}
	
	public static String getFEDEVE(Connection conexion, long liGastoID)
	{
		String sFEDEVE = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					+ CAMPO7 + 
					" FROM "
					+ TABLA + 
					" WHERE "
					+ CAMPO1  + " = '"+ liGastoID +"'";
			
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

						sFEDEVE = rs.getString(CAMPO7);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO7+":|"+sFEDEVE+"|");

					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				sFEDEVE = "";

				logger.error("ERROR GASTO:|"+liGastoID);

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sFEDEVE;
	}
	
	public static String getFechaAutorizado(Connection conexion, long liGastoID)
	{
		String sFecha = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					+ CAMPO13 + 
					" FROM "
					+ TABLA + 
					" WHERE "
					+ CAMPO1  + " = '"+ liGastoID +"'";
			
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

						sFecha = rs.getString(CAMPO13);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO13+":|"+sFecha+"|");

					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				sFecha = "";

				logger.error("ERROR GASTO:|"+liGastoID);

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sFecha;
	}
	
	public static boolean setEstado(Connection conexion, long liGastoID, String sEstado)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO34 + " = '"+ sEstado + "' "+
					" WHERE "
					+ CAMPO1  + " = '"+ liGastoID +"'";
			
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

				logger.error("ERROR GASTO:|"+liGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}
		
		return bSalida;
	}
	
	public static String getEstado(Connection conexion, long liGastoID)
	{
		String sEstado = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					+ CAMPO34 + 
					" FROM "
					+ TABLA + 
					" WHERE "
					+ CAMPO1  + " = '"+ liGastoID +"'";
			
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

						sEstado = rs.getString(CAMPO34);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO34+":|"+sEstado+"|");

					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				sEstado = "";

				logger.error("ERROR GASTO:|"+liGastoID);

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sEstado;
	}
	
	public static boolean setUrgente(Connection conexion, long liGastoID, byte btUrgente)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO35 + " = '"+ btUrgente + "' "+
					" WHERE "
					+ CAMPO1  + " = '"+ liGastoID +"'";
			
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

				logger.error("ERROR GASTO:|"+liGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}
		
		return bSalida;
	}
	
	public static boolean getUrgente(Connection conexion, long liGastoID)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					+ CAMPO35 + 
					" FROM "
					+ TABLA + 
					" WHERE "
					+ CAMPO1  + " = '"+ liGastoID +"' AND "
					+ CAMPO35 + " = "+ ValoresDefecto.GASTO_URGENTE;
			
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
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR GASTO:|"+liGastoID);

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return bEncontrado;
	}
	
	public static boolean setNota(Connection conexion, long liGastoID, String sNota)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					//+ CAMPO36 + " = '"+ sNota +"' "+
					+ CAMPO36 + " = AES_ENCRYPT('"+sNota+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) "+
					" WHERE "
					+ CAMPO1 + " = '"+ liGastoID +"'";
			
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

				logger.error("ERROR GASTO:|"+liGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

			} 
			finally 
			{

				Utils.closeStatement(stmt);
			}			
		}

		return bSalida;
	}
	
	public static String getNota(Connection conexion, long liGastoID)
	{
		String sNota = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
						//+ CAMPO36 +
						+"AES_DECRYPT("+CAMPO36+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"+
						" FROM " 
						+ TABLA + 
						" WHERE "
						+ CAMPO1 + " = '"+ liGastoID +"'";
			
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

						//sNota = rs.getString(CAMPO36);
						
						sNota = rs.getString("AES_DECRYPT("+CAMPO36 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						
						logger.debug(CAMPO1+":|"+liGastoID+"|");
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO36+":|"+sNota+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sNota = "";
				
				logger.error("ERROR GASTO:|"+liGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sNota;
	}
	
	public static long getValorTotal(Connection conexion, long liGastoID)
	{
		long liValorTotal = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
						+ CAMPO33 + 
						" FROM " 
						+ TABLA + 
						" WHERE "
						+ CAMPO1 + " = '"+ liGastoID +"'";
			
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

						liValorTotal = rs.getLong(CAMPO33);
						
						logger.debug(CAMPO1+":|"+liGastoID+"|");
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO33+":|"+liValorTotal+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				liValorTotal = 0;
				
				logger.error("ERROR GASTO:|"+liGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return liValorTotal;
	}
	
	public static long getValor(Connection conexion, long liGastoID)
	{
		long liIMNGAS = 0;
		String sYCOS02 = "";
		long liIMRGAS = 0;
		String sYCOS04 = "";
		long liIMDGAS = 0;
		String sYCOS06 = "";
		long liIMCOST = 0;
		String sYCOS08 = "";
		long liIMOGAS = 0;
		String sYCOS10 = "";
		long liIMDTGA = 0;
		long liIMIMGA = 0;


		long liValor = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
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
				       + CAMPO28 +      
				       "  FROM " 
				       + TABLA + 
				       " WHERE "
				       + CAMPO1  + " = '"+ liGastoID +"'";
			
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

						liIMNGAS = rs.getLong(CAMPO16); 
						sYCOS02 = rs.getString(CAMPO17); 
						liIMRGAS = rs.getLong(CAMPO18); 
						sYCOS04 = rs.getString(CAMPO19); 
						liIMDGAS = rs.getLong(CAMPO20); 
						sYCOS06 = rs.getString(CAMPO21); 
						liIMCOST = rs.getLong(CAMPO22); 
						sYCOS08 = rs.getString(CAMPO23); 
						liIMOGAS = rs.getLong(CAMPO24); 
						sYCOS10 = rs.getString(CAMPO25); 
						liIMDTGA = rs.getLong(CAMPO26); 
						liIMIMGA = rs.getLong(CAMPO28); 
						
						logger.debug("Encontrado el registro!");

					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{

				liIMNGAS = 0;
				sYCOS02 = "";
				liIMRGAS = 0;
				sYCOS04 = "";
				liIMDGAS = 0;
				sYCOS06 = "";
				liIMCOST = 0;
				sYCOS08 = "";
				liIMOGAS = 0;
				sYCOS10 = "";
				liIMDTGA = 0;
				liIMIMGA = 0;

				logger.error("ERROR GASTO:|"+liGastoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}
		
		if (sYCOS02.equals("-"))
		{
			liIMNGAS = -liIMNGAS;
		}
		if (sYCOS04.equals("-"))
		{
			liIMRGAS = -liIMRGAS;
		}
		if (sYCOS06.equals("-"))
		{
			liIMDGAS = -liIMDGAS;
		}
		if (sYCOS08.equals("-"))
		{
			liIMCOST = -liIMCOST;
		}
		if (sYCOS10.equals("-"))
		{
			liIMOGAS = -liIMOGAS;
		}
		
		logger.debug("liIMNGAS:|"+liIMNGAS+"|");
		logger.debug("liIMRGAS:|"+liIMRGAS+"|");
		logger.debug("liIMDGAS:|"+liIMDGAS+"|");
		logger.debug("liIMCOST:|"+liIMCOST+"|");
		logger.debug("liIMOGAS:|"+liIMOGAS+"|");		
		

		liValor = liIMNGAS+liIMRGAS+liIMDGAS+liIMCOST+liIMOGAS;
		
		logger.debug("liValor1:|"+liValor+"|");
		
		if (liValor < 0)
		{
			liValor = liValor + liIMDTGA;
		}
		else
		{
			liValor = liValor - liIMDTGA;
		}
		
		logger.debug("liIMDTGA:|"+liIMDTGA+"|");
		
		if (liValor < 0)
		{
			liValor = liValor - liIMIMGA;
		}
		else
		{
			liValor = liValor + liIMIMGA;
		}
		
		logger.debug("liIMIMGA:|"+liIMIMGA+"|");

		logger.debug("liValor2:|"+liValor+"|");
		
		return liValor;
	}
	
	public static ArrayList<String>  getGastosPorActivo(Connection conexion, int iCodCOACES) 
	{
		ArrayList<String> resultado = new ArrayList<String>(); 

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
		
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO1+ 
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO2 + " = '" + iCodCOACES + "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");
			
				int i = 0;
				
				if (rs != null) 
				{
					
					while (rs.next()) 
					{
						bEncontrado = true;

						resultado.add(rs.getString(CAMPO1));
											
						logger.debug("Encontrado el registro!");

						logger.debug("{}:|"+CAMPO2,iCodCOACES);
						logger.debug("{}:|"+CAMPO1,resultado.get(i));
					
						i++;
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<String>(); 

				logger.error("ERROR COACES:|"+iCodCOACES);

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return resultado;
	}
	
	public static ArrayList<ActivoTabla> buscaActivosConGastos(Connection conexion, ActivoTabla filtro, String sEstado)
	{
		ArrayList<ActivoTabla> resultado = new ArrayList<ActivoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String sCondicionEstado = "";
			
			if (sEstado.equals(ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE))
			{
				sCondicionEstado = " WHERE " + CAMPO34 + " IN ("+ ValoresDefecto.DEF_GASTO_ESTIMADO + "," + ValoresDefecto.DEF_GASTO_CONOCIDO + ")"; 
			}
			else if (!sCondicionEstado.isEmpty())
			{
				sCondicionEstado = " WHERE " + CAMPO34 + " = '" + sEstado + "'";
			}

			String sCondicionCOPOIN = filtro.getCOPOIN().isEmpty()?"":QMActivos.CAMPO14 + " LIKE '%" + filtro.getCOPOIN()	+ "%' AND ";
			String sCondicionNOMUIN = filtro.getNOMUIN().isEmpty()?"":QMActivos.CAMPO11 + " LIKE '%" + filtro.getNOMUIN()	+ "%' AND ";
			String sCondicionNOPRAC = filtro.getNOPRAC().isEmpty()?"":QMActivos.CAMPO13 + " LIKE '%" + filtro.getNOPRAC()	+ "%' AND ";
			String sCondicionNOVIAS = filtro.getNOVIAS().isEmpty()?"":QMActivos.CAMPO6 + " LIKE '%" + filtro.getNOVIAS()	+ "%' AND ";
			String sCondicionNUPIAC = filtro.getNUPIAC().isEmpty()?"":QMActivos.CAMPO9 + " LIKE '%" + filtro.getNUPIAC()	+ "%' AND ";
			String sCondicionNUPOAC = filtro.getNUPOAC().isEmpty()?"":QMActivos.CAMPO7 + " LIKE '%" + filtro.getNUPOAC()	+ "%' AND ";
			String sCondicionNUPUAC = filtro.getNUPUAC().isEmpty()?"":QMActivos.CAMPO10 + " LIKE '%" + filtro.getNUPUAC()	+ "%' AND ";
			String sCondicionNUFIRE = filtro.getNUFIRE().isEmpty()?"":QMActivos.CAMPO28 + " LIKE '%" + filtro.getNUFIRE()	+ "%' AND ";
	
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						
						   + QMActivos.CAMPO1 + ","        
						   + QMActivos.CAMPO14 + ","
						   + QMActivos.CAMPO11 + ","
						   + QMActivos.CAMPO13 + ","
						   + QMActivos.CAMPO6 + ","
						   + QMActivos.CAMPO9 + ","
						   + QMActivos.CAMPO7 + ","
						   + QMActivos.CAMPO10 + ","
						   + QMActivos.CAMPO28 + 

						   " FROM " + QMActivos.TABLA + 
						   " WHERE ("

					   		+ sCondicionCOPOIN  
					   		+ sCondicionNOMUIN  
					   		+ sCondicionNOPRAC  
					   		+ sCondicionNOVIAS 
					   		+ sCondicionNUPIAC  
					   		+ sCondicionNUPOAC
					   		+ sCondicionNUPUAC
					   		+ sCondicionNUFIRE

					   		+ QMActivos.CAMPO1 +" IN (SELECT "
						   + CAMPO2 + 
						   " FROM " + TABLA
						   + sCondicionEstado
						   + "))";
			
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
						
						String sCOACES = rs.getString(QMActivos.CAMPO1);
						String sCOPOIN = rs.getString(QMActivos.CAMPO14);
						String sNOMUIN = rs.getString(QMActivos.CAMPO11);
						String sNOPRAC = rs.getString(QMActivos.CAMPO13);
						String sNOVIAS = rs.getString(QMActivos.CAMPO6);
						String sNUPIAC = rs.getString(QMActivos.CAMPO9);
						String sNUPOAC = rs.getString(QMActivos.CAMPO7);
						String sNUPUAC = rs.getString(QMActivos.CAMPO10);
						String sNUFIRE = rs.getString(QMActivos.CAMPO28);
						
						ActivoTabla activoencontrado = new ActivoTabla(
								sCOACES, 
								sCOPOIN, 
								sNOMUIN, 
								sNOPRAC, 
								sNOVIAS, 
								sNUPIAC, 
								sNUPOAC, 
								sNUPUAC,
								sNUFIRE,
								"");
						
						resultado.add(activoencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug("{}:|"+QMActivos.CAMPO1,sCOACES);
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ActivoTabla>();

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return resultado;

	}
	
	public static ArrayList<GastoTabla> buscaGastosNuevosPorActivo(Connection conexion, int iCodCOACES)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
					+ CAMPO1 + "," 	
					+ CAMPO2 + ","        
					+ CAMPO3 + ","
					+ CAMPO4 + ","
					+ CAMPO5 + ","
					+ CAMPO6 + ","
					+ CAMPO7 + ","
					+ CAMPO9 + ","
					+ CAMPO10 + ","
					+ CAMPO14 + ","
					+ CAMPO15 + ","
					+ CAMPO16 + ","
					+ CAMPO34 + ","
					+ CAMPO35 +	
					" FROM " 
					+ TABLA + 
					" WHERE ("
					+ CAMPO2 + " = "+ iCodCOACES+ " AND "
					+ CAMPO34 + " IN ('" + ValoresDefecto.DEF_GASTO_ESTIMADO + "','" + ValoresDefecto.DEF_GASTO_CONOCIDO +"') AND "					   
					+ CAMPO7 + " <= '"+Utils.fechaDeHoy(false)+"')";
						   
			
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
						
						String sGastoID = rs.getString(QMGastos.CAMPO1);
						String sNUPROF = QMListaGastosProvisiones.getProvisionDeGasto(conexion, rs.getLong(QMGastos.CAMPO1));
						String sEstadoPROF = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF,QMCodigosControl.IESPROF,QMProvisiones.getEstado(conexion, sNUPROF));
						String sCOACES  = rs.getString(QMGastos.CAMPO2);
						String sCOGRUG  = rs.getString(QMGastos.CAMPO3);
						String sCOTPGA  = rs.getString(QMGastos.CAMPO4);
						String sCOSBGA  = rs.getString(QMGastos.CAMPO5);
						String sDCOSBGA = QMCodigosControl.getDesCOSBGA(conexion,sCOGRUG,sCOTPGA,sCOSBGA);
						String sPTPAGO  = rs.getString(QMGastos.CAMPO6);
						String sDPTPAGO = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
						String sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO7));
						String sCOSIGA  = rs.getString(QMGastos.CAMPO10);
						String sDCOSIGA = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSIGA,QMCodigosControl.ICOSIGA,sCOSIGA);
						String sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO16).equals("-"),rs.getString(QMGastos.CAMPO15));
						String sEstado  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34));
						String sFEEPAI  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO14));
						String sFELIPG  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO9));

						String sTipoPago  = "";
						
						if (sCOSIGA.equals(ValoresDefecto.DEF_GASTO_PAGADO)
							|| sCOSIGA.equals(ValoresDefecto.DEF_GASTO_ABONADO))
						{
							sTipoPago  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOPAGO,QMCodigosControl.ICOPAGO,QMPagos.getTipoPago(conexion, rs.getLong(QMGastos.CAMPO1)));							
						}
						
						String sUrgente  = Utils.recuperaBit((rs.getString(QMGastos.CAMPO35)));
						
						GastoTabla gastoencontrado = new GastoTabla(
								sGastoID,
								sNUPROF,
								sEstadoPROF,
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
								sIMNGAS,
								sEstado,
								sFEEPAI,
								sFELIPG,
								sTipoPago,
								sUrgente);
						
						resultado.add(gastoencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO2+":|"+iCodCOACES+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<GastoTabla>();

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}			
		}

		return resultado;
	}
	
	public static ArrayList<GastoTabla> buscaGastosNuevosPorFiltro(Connection conexion, GastoTabla filtro, String sComparador)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String sCondicionFEDEVE = filtro.getFEDEVE().equals(ValoresDefecto.CAMPO_NUME_SIN_INFORMAR)? "": CAMPO7 + " >= '"+filtro.getFEDEVE()+"' AND ";
			String sCondicionCOGRUG = filtro.getCOGRUG().isEmpty()? "": CAMPO3 + " >= '"+filtro.getCOGRUG()+"' AND ";
			String sCondicionCOTPGA = filtro.getCOTPGA().isEmpty()? "": CAMPO4 + " >= '"+filtro.getCOTPGA()+"' AND ";
			String sCondicionCOSBGA = filtro.getCOSBGA().isEmpty()? "": CAMPO5 + " >= '"+filtro.getCOSBGA()+"' AND ";
			
			String sCondicionEstado = filtro.getESTADO().isEmpty()? 
					CAMPO34 + " IN ('" + ValoresDefecto.DEF_GASTO_ESTIMADO + "','" + ValoresDefecto.DEF_GASTO_CONOCIDO +"')"
					:CAMPO34 + " = '"+filtro.getESTADO()+"'"; 
			
			String sCondicionImporte = sComparador.isEmpty()?"":CAMPO15 + " "+sComparador+" " + filtro.getIMNGAS() + " AND ";
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
					+ CAMPO1 + "," 	
					+ CAMPO2 + ","        
					+ CAMPO3 + ","
					+ CAMPO4 + ","
					+ CAMPO5 + ","
					+ CAMPO6 + ","
					+ CAMPO7 + ","
					+ CAMPO9 + ","
					+ CAMPO10 + ","
					+ CAMPO14 + ","
					+ CAMPO15 + ","
					+ CAMPO16 + ","
					+ CAMPO34 + ","
					+ CAMPO35 +	
					" FROM " 
					+ TABLA + 
					" WHERE ("
					+ sCondicionCOGRUG  
					+ sCondicionCOTPGA  
					+ sCondicionCOSBGA  
					+ sCondicionFEDEVE	
					+ sCondicionImporte
					+ sCondicionEstado 
					
					+" AND "+ CAMPO2 + " = '" + filtro.getCOACES() + "' )";
						   
			
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
						
						String sGastoID = rs.getString(QMGastos.CAMPO1);
						String sNUPROF = QMListaGastosProvisiones.getProvisionDeGasto(conexion, rs.getLong(QMGastos.CAMPO1));
						String sEstadoPROF = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF,QMCodigosControl.IESPROF,QMProvisiones.getEstado(conexion, sNUPROF));
						String sCOACES  = rs.getString(QMGastos.CAMPO2);
						String sCOGRUG  = rs.getString(QMGastos.CAMPO3);
						String sCOTPGA  = rs.getString(QMGastos.CAMPO4);
						String sCOSBGA  = rs.getString(QMGastos.CAMPO5);
						String sDCOSBGA = QMCodigosControl.getDesCOSBGA(conexion,sCOGRUG,sCOTPGA,sCOSBGA);
						String sPTPAGO  = rs.getString(QMGastos.CAMPO6);
						String sDPTPAGO = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
						String sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO7));
						String sCOSIGA  = rs.getString(QMGastos.CAMPO10);
						String sDCOSIGA = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSIGA,QMCodigosControl.ICOSIGA,sCOSIGA);
						String sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO16).equals("-"),rs.getString(QMGastos.CAMPO15));
						String sEstado  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34));
						String sFEEPAI  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO14));
						String sFELIPG  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO9));
						String sTipoPago  = "";
						
						if (sCOSIGA.equals(ValoresDefecto.DEF_GASTO_PAGADO)
							|| sCOSIGA.equals(ValoresDefecto.DEF_GASTO_ABONADO))
						{
							sTipoPago  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOPAGO,QMCodigosControl.ICOPAGO,QMPagos.getTipoPago(conexion, rs.getLong(QMGastos.CAMPO1)));							
						}
						
						String sUrgente  = Utils.recuperaBit((rs.getString(QMGastos.CAMPO35)));
						
						GastoTabla gastoencontrado = new GastoTabla(
								sGastoID,
								sNUPROF,
								sEstadoPROF,
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
								sIMNGAS,
								sEstado,
								sFEEPAI,
								sFELIPG,
								sTipoPago,
								sUrgente);
						
						resultado.add(gastoencontrado);
						
						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<GastoTabla>();

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}			
		}

		return resultado;
	}
	
	public static ArrayList<GastoTabla> buscaGastosPorActivo(Connection conexion, int iCodCOACES)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
					+ CAMPO1 + "," 	
					+ CAMPO2 + ","        
					+ CAMPO3 + ","
					+ CAMPO4 + ","
					+ CAMPO5 + ","
					+ CAMPO6 + ","
					+ CAMPO7 + ","
					+ CAMPO9 + ","
					+ CAMPO10 + ","
					+ CAMPO14 + ","
					+ CAMPO15 + ","
					+ CAMPO16 + ","
					+ CAMPO34 + ","
					+ CAMPO35 +	

						   " FROM " 
						   + TABLA + 
						   " WHERE "
						   + CAMPO2 + " = '"+iCodCOACES+"'";					   
						   
			
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
						
						String sGastoID = rs.getString(QMGastos.CAMPO1);
						String sNUPROF = QMListaGastosProvisiones.getProvisionDeGasto(conexion, rs.getLong(QMGastos.CAMPO1));
						String sEstadoPROF = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF,QMCodigosControl.IESPROF,QMProvisiones.getEstado(conexion, sNUPROF));
						String sCOACES  = rs.getString(QMGastos.CAMPO2);
						String sCOGRUG  = rs.getString(QMGastos.CAMPO3);
						String sCOTPGA  = rs.getString(QMGastos.CAMPO4);
						String sCOSBGA  = rs.getString(QMGastos.CAMPO5);
						String sDCOSBGA = QMCodigosControl.getDesCOSBGA(conexion,sCOGRUG,sCOTPGA,sCOSBGA);
						String sPTPAGO  = rs.getString(QMGastos.CAMPO6);
						String sDPTPAGO = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
						String sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO7));
						String sCOSIGA  = rs.getString(QMGastos.CAMPO10);
						String sDCOSIGA = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSIGA,QMCodigosControl.ICOSIGA,sCOSIGA);
						String sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO16).equals("-"),rs.getString(QMGastos.CAMPO15));
						String sEstado  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34));
						String sFEEPAI  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO14));
						String sFELIPG  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO9));
						String sTipoPago  = "";
						
						if (sCOSIGA.equals(ValoresDefecto.DEF_GASTO_PAGADO)
							|| sCOSIGA.equals(ValoresDefecto.DEF_GASTO_ABONADO))
						{
							sTipoPago  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOPAGO,QMCodigosControl.ICOPAGO,QMPagos.getTipoPago(conexion, rs.getLong(QMGastos.CAMPO1)));							
						}
						
						String sUrgente  = Utils.recuperaBit((rs.getString(QMGastos.CAMPO35)));
						
						GastoTabla gastoencontrado = new GastoTabla(
								sGastoID,
								sNUPROF,
								sEstadoPROF,
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
								sIMNGAS,
								sEstado,
								sFEEPAI,
								sFELIPG,
								sTipoPago,
								sUrgente);
						
						resultado.add(gastoencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO2+":|"+iCodCOACES+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<GastoTabla>();

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}			
		}

		return resultado;
	}
	
	public static ArrayList<GastoTabla> buscaGastosPorFiltroActivoProvision(Connection conexion, GastoTabla filtro, String sComparador)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			boolean bEncontrado = false;

			String sCondicionFEDEVE = filtro.getFEDEVE().equals(ValoresDefecto.CAMPO_NUME_SIN_INFORMAR)? "": CAMPO7 + " >= '"+filtro.getFEDEVE()+"' AND ";
			String sCondicionCOGRUG = filtro.getCOGRUG().isEmpty()? "": CAMPO3 + " >= '"+filtro.getCOGRUG()+"' AND ";
			String sCondicionCOTPGA = filtro.getCOTPGA().isEmpty()? "": CAMPO4 + " >= '"+filtro.getCOTPGA()+"' AND ";
			String sCondicionCOSBGA = filtro.getCOSBGA().isEmpty()? "": CAMPO5 + " >= '"+filtro.getCOSBGA()+"' AND ";
			
			String sCondicionEstado = filtro.getESTADO().isEmpty()?	"":CAMPO34 + " = '"+filtro.getESTADO()+"' AND "; 
			
			String sCondicionImporte = sComparador.isEmpty()?"":CAMPO15 + " "+sComparador+" " + filtro.getIMNGAS() + " AND ";
	
			String sCondicionFELIPG = filtro.getFELIPG().isEmpty()? "": CAMPO9 + " >= '"+filtro.getFELIPG()+"' AND ";
			
			String sCondicionNUPROF = filtro.getNUPROF().isEmpty()? "": 
				" AND "+ CAMPO1 + " IN (SELECT "
				+ QMListaGastosProvisiones.CAMPO1 + 
				" FROM " 
				+ QMListaGastosProvisiones.TABLA + 
				" WHERE "
				+ QMListaGastosProvisiones.CAMPO2 + " = " + filtro.getNUPROF() + ") ";
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
					+ CAMPO1 + "," 	
					+ CAMPO2 + ","        
					+ CAMPO3 + ","
					+ CAMPO4 + ","
					+ CAMPO5 + ","
					+ CAMPO6 + ","
					+ CAMPO7 + ","
					+ CAMPO9 + ","
					+ CAMPO10 + ","
					+ CAMPO14 + ","
					+ CAMPO15 + ","
					+ CAMPO16 + ","
					+ CAMPO34 + ","
					+ CAMPO35 +	

					" FROM " 
					+ TABLA + 
					" WHERE "
					+ sCondicionCOGRUG  
					+ sCondicionCOTPGA  
					+ sCondicionCOSBGA  
					+ sCondicionFEDEVE	
					+ sCondicionImporte
					+ sCondicionFELIPG
					+ sCondicionEstado
					+ CAMPO2 + " = '"+filtro.getCOACES()+"' "
					+ sCondicionNUPROF;
   
						   
			
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
						
						String sGastoID = rs.getString(QMGastos.CAMPO1);

						String sNUPROF = sCondicionNUPROF.isEmpty()? QMListaGastosProvisiones.getProvisionDeGasto(conexion, rs.getLong(QMGastos.CAMPO1)):
							filtro.getNUPROF();

						String sEstadoPROF = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF,QMCodigosControl.IESPROF,QMProvisiones.getEstado(conexion, sNUPROF));
						String sCOACES  = rs.getString(QMGastos.CAMPO2);
						String sCOGRUG  = rs.getString(QMGastos.CAMPO3);
						String sCOTPGA  = rs.getString(QMGastos.CAMPO4);
						String sCOSBGA  = rs.getString(QMGastos.CAMPO5);
						String sDCOSBGA = QMCodigosControl.getDesCOSBGA(conexion,sCOGRUG,sCOTPGA,sCOSBGA);
						String sPTPAGO  = rs.getString(QMGastos.CAMPO6);
						String sDPTPAGO = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
						String sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO7));
						String sCOSIGA  = rs.getString(QMGastos.CAMPO10);
						String sDCOSIGA = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSIGA,QMCodigosControl.ICOSIGA,sCOSIGA);
						String sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO16).equals("-"),rs.getString(QMGastos.CAMPO15));
						String sEstado  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34));
						String sFEEPAI  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO14));
						String sFELIPG  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO9));
						String sTipoPago  = "";
						
						if (sCOSIGA.equals(ValoresDefecto.DEF_GASTO_PAGADO)
							|| sCOSIGA.equals(ValoresDefecto.DEF_GASTO_ABONADO))
						{
							sTipoPago  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOPAGO,QMCodigosControl.ICOPAGO,QMPagos.getTipoPago(conexion, rs.getLong(QMGastos.CAMPO1)));							
						}
						
						String sUrgente  = Utils.recuperaBit((rs.getString(QMGastos.CAMPO35)));
						
						GastoTabla gastoencontrado = new GastoTabla(
								sGastoID,
								sNUPROF,
								sEstadoPROF,
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
								sIMNGAS,
								sEstado,
								sFEEPAI,
								sFELIPG,
								sTipoPago,
								sUrgente);
						
						resultado.add(gastoencontrado);
						
						logger.debug("Encontrado el registro!");

					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<GastoTabla>();

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}			
		}

		return resultado;
	}
	
	public static ArrayList<GastoTabla> buscaGastosPorFechaLimite(Connection conexion, String sFELIPG)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String sCondicionFELIPG = sFELIPG.isEmpty()? "": CAMPO9 + " >= '"+sFELIPG+"' AND ";
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
					+ CAMPO1 + "," 	
					+ CAMPO2 + ","
					+ CAMPO3 + ","
					+ CAMPO4 + ","
					+ CAMPO5 + ","
					+ CAMPO6 + ","
					+ CAMPO7 + ","
					+ CAMPO9 + ","
					+ CAMPO10 + ","
					+ CAMPO14 + ","
					+ CAMPO15 + ","
					+ CAMPO16 + ","
					+ CAMPO34 + ","
					+ CAMPO35 +	

					" FROM " 
					+ TABLA + 
					" WHERE ("
					+ sCondicionFELIPG
					+ CAMPO34 + " IN ('" + ValoresDefecto.DEF_GASTO_ESTIMADO + "','" + ValoresDefecto.DEF_GASTO_CONOCIDO + "','" + ValoresDefecto.DEF_GASTO_AUTORIZADO + "'))"+
					" ORDER BY "+CAMPO9;
	   
						   
			
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
						
						String sGastoID = rs.getString(QMGastos.CAMPO1);
						String sNUPROF = QMListaGastosProvisiones.getProvisionDeGasto(conexion, rs.getLong(QMGastos.CAMPO1));
						String sEstadoPROF = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF,QMCodigosControl.IESPROF,QMProvisiones.getEstado(conexion, sNUPROF));
						String sCOACES  = rs.getString(QMGastos.CAMPO2);
						String sCOGRUG  = rs.getString(QMGastos.CAMPO3);
						String sCOTPGA  = rs.getString(QMGastos.CAMPO4);
						String sCOSBGA  = rs.getString(QMGastos.CAMPO5);
						String sDCOSBGA = QMCodigosControl.getDesCOSBGA(conexion,sCOGRUG,sCOTPGA,sCOSBGA);
						String sPTPAGO  = rs.getString(QMGastos.CAMPO6);
						String sDPTPAGO = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
						String sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO7));
						String sCOSIGA  = rs.getString(QMGastos.CAMPO10);
						String sDCOSIGA = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSIGA,QMCodigosControl.ICOSIGA,sCOSIGA);
						String sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO16).equals("-"),rs.getString(QMGastos.CAMPO15));
						String sEstado  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34));
						String sFEEPAI  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO14));
						String sTipoPago  = "";
						
						if (sCOSIGA.equals(ValoresDefecto.DEF_GASTO_PAGADO)
							|| sCOSIGA.equals(ValoresDefecto.DEF_GASTO_ABONADO))
						{
							sTipoPago  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOPAGO,QMCodigosControl.ICOPAGO,QMPagos.getTipoPago(conexion, rs.getLong(QMGastos.CAMPO1)));							
						}
						
						String sUrgente  = Utils.recuperaBit((rs.getString(QMGastos.CAMPO35)));
						
						GastoTabla gastoencontrado = new GastoTabla(
								sGastoID,
								sNUPROF,
								sEstadoPROF,
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
								sIMNGAS,
								sEstado,
								sFEEPAI,
								Utils.recuperaFecha(rs.getString(QMGastos.CAMPO9)),
								sTipoPago,
								sUrgente);
						
						resultado.add(gastoencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO9+":|"+sFELIPG+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<GastoTabla>();

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}			
		}

		return resultado;
	}
	
	
	/*public static ArrayList<GastoTabla> buscaGastosPorFiltro(Connection conexion, GastoTabla filtro)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
					+ CAMPO1 + "," 	
					+ CAMPO2 + ","        
					+ CAMPO3 + ","
					+ CAMPO4 + ","
					+ CAMPO5 + ","
					+ CAMPO6 + ","
					+ CAMPO7 + ","
					+ CAMPO9 + ","
					+ CAMPO10 + ","
					+ CAMPO14 + ","
					+ CAMPO15 + ","
					+ CAMPO16 + ","
					+ CAMPO34 + ","
					+ CAMPO35 +	

						   " FROM " 
						   + TABLA + 
						   " WHERE "
						   + CAMPO2 + " = '" + filtro.getCOACES() + "' AND "
						   + CAMPO3 + " LIKE '%" + filtro.getCOGRUG() + "%' AND "  
						   + CAMPO4 + " LIKE '%" + filtro.getCOTPGA() + "%' AND "  
						   + CAMPO5 + " LIKE '%" + filtro.getCOSBGA() + "%' AND "  
						   + CAMPO7 + " LIKE '%" + filtro.getFEDEVE() + "%'";					   
						   
			
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
						
						String sGastoID = rs.getString(QMGastos.CAMPO1);
						String sNUPROF = QMListaGastosProvisiones.getProvisionDeGasto(conexion, rs.getLong(QMGastos.CAMPO1));
						String sEstadoPROF = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF,QMCodigosControl.IESPROF,QMProvisiones.getEstado(conexion, sNUPROF));
						String sCOACES  = rs.getString(QMGastos.CAMPO2);
						String sCOGRUG  = rs.getString(QMGastos.CAMPO3);
						String sCOTPGA  = rs.getString(QMGastos.CAMPO4);
						String sCOSBGA  = rs.getString(QMGastos.CAMPO5);
						String sDCOSBGA = QMCodigosControl.getDesCOSBGA(conexion,sCOGRUG,sCOTPGA,sCOSBGA);
						String sPTPAGO  = rs.getString(QMGastos.CAMPO6);
						String sDPTPAGO = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
						String sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO7));
						String sCOSIGA  = rs.getString(QMGastos.CAMPO10);
						String sDCOSIGA = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSIGA,QMCodigosControl.ICOSIGA,sCOSIGA);
						String sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO16).equals("-"),rs.getString(QMGastos.CAMPO15));
						String sEstado  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34));
						String sFEEPAI  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO14));
						String sFELIPG  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO9));
						
						GastoTabla gastoencontrado = new GastoTabla(
								sGastoID,
								sNUPROF,
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
								sIMNGAS,
								sEstado,
								sFEEPAI,
								sFELIPG);
						
						resultado.add(gastoencontrado);
						
						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<GastoTabla>();

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}			
		}

		return resultado;
	}*/
	
	public static ArrayList<GastoTabla> buscaGastosPorFiltro(Connection conexion, GastoTabla filtro, String sComparador)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			//Condiciones de filtro
			String sCondicionCOGRUG = filtro.getCOGRUG().isEmpty()?"":CAMPO3 + " = '" + filtro.getCOGRUG() + "' AND ";
			String sCondicionCOTPGA = filtro.getCOTPGA().isEmpty()?"":CAMPO4 + " = '" + filtro.getCOTPGA() + "' AND ";
			String sCondicionCOSBGA = filtro.getCOSBGA().isEmpty()?"":CAMPO5 + " = '" + filtro.getCOSBGA() + "' AND ";
			String sCondicionFEDEVE = (filtro.getFEDEVE().isEmpty() || filtro.getFEDEVE().equals("0"))?"":CAMPO7 + " = '" + filtro.getFEDEVE() + "' AND ";
			String sCondicionEstado = filtro.getESTADO().isEmpty()?"":CAMPO34 + " = '" + filtro.getESTADO() + "' AND ";
			String sCondicionImporte = sComparador.isEmpty()?"":CAMPO15 + " "+sComparador+" " + filtro.getIMNGAS() + " AND ";
			//String sCondicionEstado = sEstado.isEmpty()?"":CAMPO34 + " = '" + sEstado + "' AND ";
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
					+ CAMPO1 + "," 	
					+ CAMPO2 + ","        
					+ CAMPO3 + ","
					+ CAMPO4 + ","
					+ CAMPO5 + ","
					+ CAMPO6 + ","
					+ CAMPO7 + ","
					+ CAMPO9 + ","
					+ CAMPO10 + ","
					+ CAMPO14 + ","
					+ CAMPO15 + ","
					+ CAMPO16 + ","
					+ CAMPO33 + ","
					+ CAMPO34 + ","
					+ CAMPO35 +	

				   " FROM " 
				   + TABLA + 
				   " WHERE ("
				   + sCondicionCOGRUG
				   + sCondicionCOTPGA
				   + sCondicionCOSBGA
				   + sCondicionImporte
				   + sCondicionFEDEVE
				   + sCondicionEstado
				   + CAMPO2 + " = '" + filtro.getCOACES() + "')"+
	 			   " ORDER BY "+ CAMPO9;
						   
			
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
						
						String sGastoID = rs.getString(QMGastos.CAMPO1);
						String sNUPROF = QMListaGastosProvisiones.getProvisionDeGasto(conexion, rs.getLong(QMGastos.CAMPO1));
						String sEstadoPROF = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF,QMCodigosControl.IESPROF,QMProvisiones.getEstado(conexion, sNUPROF));
						String sCOACES  = rs.getString(QMGastos.CAMPO2);
						String sCOGRUG  = rs.getString(QMGastos.CAMPO3);
						String sCOTPGA  = rs.getString(QMGastos.CAMPO4);
						String sCOSBGA  = rs.getString(QMGastos.CAMPO5);
						String sDCOSBGA = QMCodigosControl.getDesCOSBGA(conexion,sCOGRUG,sCOTPGA,sCOSBGA);
						String sPTPAGO  = rs.getString(QMGastos.CAMPO6);
						String sDPTPAGO = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
						String sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO7));
						String sCOSIGA  = rs.getString(QMGastos.CAMPO10);
						String sDCOSIGA = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSIGA,QMCodigosControl.ICOSIGA,sCOSIGA);
						//String sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO16).equals("-"),rs.getString(QMGastos.CAMPO15));
						
						String sIMNGAS  = Utils.recuperaImporte(false,rs.getString(QMGastos.CAMPO33));
						//sEstado  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34));
						String sFEEPAI  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO14));
						String sFELIPG  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO9));
						String sTipoPago  = "";
						
						if (sCOSIGA.equals(ValoresDefecto.DEF_GASTO_PAGADO)
							|| sCOSIGA.equals(ValoresDefecto.DEF_GASTO_ABONADO))
						{
							sTipoPago  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOPAGO,QMCodigosControl.ICOPAGO,QMPagos.getTipoPago(conexion, rs.getLong(QMGastos.CAMPO1)));							
						}
						
						String sUrgente  = Utils.recuperaBit((rs.getString(QMGastos.CAMPO35)));
						
						GastoTabla gastoencontrado = new GastoTabla(
								sGastoID,
								sNUPROF,
								sEstadoPROF,
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
								sIMNGAS,
								QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34)),
								sFEEPAI,
								sFELIPG,
								sTipoPago,
								sUrgente);
						
						resultado.add(gastoencontrado);
						
						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<GastoTabla>();

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}			
		}

		return resultado;
	}
	
	public static ArrayList<GastoTabla> buscaGastosPorFiltroSinActivo(Connection conexion, GastoTabla filtro, String sComparador)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			//Condiciones de filtro
			String sCondicionCOGRUG = filtro.getCOGRUG().isEmpty()?"":CAMPO3 + " = '" + filtro.getCOGRUG() + "' AND ";
			String sCondicionCOTPGA = filtro.getCOTPGA().isEmpty()?"":CAMPO4 + " = '" + filtro.getCOTPGA() + "' AND ";
			String sCondicionCOSBGA = filtro.getCOSBGA().isEmpty()?"":CAMPO5 + " = '" + filtro.getCOSBGA() + "' AND ";
			String sCondicionFEDEVE = (filtro.getFEDEVE().isEmpty() || filtro.getFEDEVE().equals("0"))?"":CAMPO7 + " = '" + filtro.getFEDEVE() + "' AND ";
			String sCondicionEstado = filtro.getESTADO().isEmpty()?"":CAMPO34 + " = '" + filtro.getESTADO() + "' AND ";
			//String sCondicionEstado = sEstado.isEmpty()?"":CAMPO34 + " = '" + sEstado + "' AND ";
			String sCondicionImporte = sComparador.isEmpty()?"":CAMPO15 + " "+sComparador+" " + filtro.getIMNGAS() + " AND ";
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
					+ CAMPO1 + "," 	
					+ CAMPO2 + ","        
					+ CAMPO3 + ","
					+ CAMPO4 + ","
					+ CAMPO5 + ","
					+ CAMPO6 + ","
					+ CAMPO7 + ","
					+ CAMPO9 + ","
					+ CAMPO10 + ","
					+ CAMPO14 + ","
					+ CAMPO15 + ","
					+ CAMPO16 + ","
					+ CAMPO33 + ","
					+ CAMPO34 + ","
					+ CAMPO35 +	

				   " FROM " 
				   + TABLA + 
				   " WHERE ("
				   + sCondicionCOGRUG
				   + sCondicionCOTPGA
				   + sCondicionCOSBGA
				   + sCondicionFEDEVE
				   + sCondicionEstado
				   + sCondicionImporte
	 			   //+ CAMPO2 + " = '" + filtro.getCOACES() + "')"+
				   + CAMPO2 + " LIKE '%" + filtro.getCOACES() + "%')"+
	 			   " ORDER BY "+ CAMPO9;
						   
			
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
						
						String sGastoID = rs.getString(QMGastos.CAMPO1);
						String sNUPROF = QMListaGastosProvisiones.getProvisionDeGasto(conexion, rs.getLong(QMGastos.CAMPO1));
						String sEstadoPROF = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF,QMCodigosControl.IESPROF,QMProvisiones.getEstado(conexion, sNUPROF));
						String sCOACES  = rs.getString(QMGastos.CAMPO2);
						String sCOGRUG  = rs.getString(QMGastos.CAMPO3);
						String sCOTPGA  = rs.getString(QMGastos.CAMPO4);
						String sCOSBGA  = rs.getString(QMGastos.CAMPO5);
						String sDCOSBGA = QMCodigosControl.getDesCOSBGA(conexion,sCOGRUG,sCOTPGA,sCOSBGA);
						String sPTPAGO  = rs.getString(QMGastos.CAMPO6);
						String sDPTPAGO = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
						String sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO7));
						String sCOSIGA  = rs.getString(QMGastos.CAMPO10);
						String sDCOSIGA = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSIGA,QMCodigosControl.ICOSIGA,sCOSIGA);
						//String sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO16).equals("-"),rs.getString(QMGastos.CAMPO15));
						
						String sIMNGAS  = Utils.recuperaImporte(false,rs.getString(QMGastos.CAMPO33));
						//sEstado  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34));
						String sFEEPAI  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO14));
						String sFELIPG  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO9));
						String sTipoPago  = "";
						
						if (sCOSIGA.equals(ValoresDefecto.DEF_GASTO_PAGADO)
							|| sCOSIGA.equals(ValoresDefecto.DEF_GASTO_ABONADO))
						{
							sTipoPago  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOPAGO,QMCodigosControl.ICOPAGO,QMPagos.getTipoPago(conexion, rs.getLong(QMGastos.CAMPO1)));							
						}
						
						String sUrgente  = Utils.recuperaBit((rs.getString(QMGastos.CAMPO35)));
						
						GastoTabla gastoencontrado = new GastoTabla(
								sGastoID,
								sNUPROF,
								sEstadoPROF,
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
								sIMNGAS,
								QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34)),
								sFEEPAI,
								sFELIPG,
								sTipoPago,
								sUrgente);
						
						resultado.add(gastoencontrado);
						
						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<GastoTabla>();

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}			
		}

		return resultado;
	}
	
	public static ArrayList<GastoTabla> buscaGastosPagablesPorFiltro(Connection conexion, GastoTabla filtro, String sComparador)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			//Condiciones de filtro
			String sCondicionCOGRUG = filtro.getCOGRUG().isEmpty()?"":CAMPO3 + " = '" + filtro.getCOGRUG() + "' AND ";
			String sCondicionCOTPGA = filtro.getCOTPGA().isEmpty()?"":CAMPO4 + " = '" + filtro.getCOTPGA() + "' AND ";
			String sCondicionCOSBGA = filtro.getCOSBGA().isEmpty()?"":CAMPO5 + " = '" + filtro.getCOSBGA() + "' AND ";
			String sCondicionFEDEVE = (filtro.getFEDEVE().isEmpty() || filtro.getFEDEVE().equals("0"))?"":CAMPO7 + " = '" + filtro.getFEDEVE() + "' AND ";
			String sCondicionUrgente = filtro.getCOSBGA().isEmpty()?"":CAMPO35 + " = '" + filtro.getURGENTE() + "' AND ";
			
			String sCondicionImporte = sComparador.isEmpty()?"":CAMPO15 + " "+sComparador+" " + filtro.getIMNGAS() + " AND ";
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
					+ CAMPO1 + "," 	
					+ CAMPO2 + ","        
					+ CAMPO3 + ","
					+ CAMPO4 + ","
					+ CAMPO5 + ","
					+ CAMPO6 + ","
					+ CAMPO7 + ","
					+ CAMPO9 + ","
					+ CAMPO10 + ","
					+ CAMPO14 + ","
					+ CAMPO15 + ","
					+ CAMPO16 + ","
					+ CAMPO33 + ","
					+ CAMPO34 + ","
					+ CAMPO35 +		

				   " FROM " 
				   + TABLA + 
				   " WHERE ("
				   + sCondicionCOGRUG
				   + sCondicionCOTPGA
				   + sCondicionCOSBGA
				   + sCondicionFEDEVE
				   + sCondicionUrgente
				   + sCondicionImporte
	 			   + CAMPO2 + " = '" + filtro.getCOACES() + "') AND ("
				   + CAMPO34 + " = '" + ValoresDefecto.DEF_GASTO_AUTORIZADO + "' OR ( " 
				   + CAMPO34 + " IN ('" + ValoresDefecto.DEF_GASTO_ESTIMADO + "','"
				   + ValoresDefecto.DEF_GASTO_CONOCIDO + "') AND ("
	 			   + CAMPO35 + " = " + ValoresDefecto.GASTO_URGENTE + ")))"+
	 			   " ORDER BY "+ CAMPO9;
						   
			
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
						
						String sGastoID = rs.getString(QMGastos.CAMPO1);
						String sNUPROF = QMListaGastosProvisiones.getProvisionDeGasto(conexion, rs.getLong(QMGastos.CAMPO1));
						String sEstadoPROF = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF,QMCodigosControl.IESPROF,QMProvisiones.getEstado(conexion, sNUPROF));
						String sCOACES  = rs.getString(QMGastos.CAMPO2);
						String sCOGRUG  = rs.getString(QMGastos.CAMPO3);
						String sCOTPGA  = rs.getString(QMGastos.CAMPO4);
						String sCOSBGA  = rs.getString(QMGastos.CAMPO5);
						String sDCOSBGA = QMCodigosControl.getDesCOSBGA(conexion,sCOGRUG,sCOTPGA,sCOSBGA);
						String sPTPAGO  = rs.getString(QMGastos.CAMPO6);
						String sDPTPAGO = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
						String sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO7));
						String sCOSIGA  = rs.getString(QMGastos.CAMPO10);
						String sDCOSIGA = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSIGA,QMCodigosControl.ICOSIGA,sCOSIGA);
						//String sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO16).equals("-"),rs.getString(QMGastos.CAMPO15));
						
						String sIMNGAS  = Utils.recuperaImporte(false,rs.getString(QMGastos.CAMPO33));
						//sEstado  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34));
						String sFEEPAI  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO14));
						String sFELIPG  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO9));
						String sTipoPago  = "";
						
						if (sCOSIGA.equals(ValoresDefecto.DEF_GASTO_PAGADO)
							|| sCOSIGA.equals(ValoresDefecto.DEF_GASTO_ABONADO))
						{
							sTipoPago  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOPAGO,QMCodigosControl.ICOPAGO,QMPagos.getTipoPago(conexion, rs.getLong(QMGastos.CAMPO1)));							
						}
						
						String sUrgente  = Utils.recuperaBit((rs.getString(QMGastos.CAMPO35)));
						
						GastoTabla gastoencontrado = new GastoTabla(
								sGastoID,
								sNUPROF,
								sEstadoPROF,
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
								sIMNGAS,
								QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34)),
								sFEEPAI,
								sFELIPG,
								sTipoPago,
								sUrgente);
						
						resultado.add(gastoencontrado);
						
						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<GastoTabla>();

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}			
		}

		return resultado;
	}
	
	public static ArrayList<GastoTabla> buscaGastosRevisablesPorFiltro(Connection conexion, GastoTabla filtro, String sComparador)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			//Condiciones de filtro
			String sCondicionCOGRUG = filtro.getCOGRUG().isEmpty()?"":CAMPO3 + " = '" + filtro.getCOGRUG() + "' AND ";
			String sCondicionCOTPGA = filtro.getCOTPGA().isEmpty()?"":CAMPO4 + " = '" + filtro.getCOTPGA() + "' AND ";
			String sCondicionCOSBGA = filtro.getCOSBGA().isEmpty()?"":CAMPO5 + " = '" + filtro.getCOSBGA() + "' AND ";
			String sCondicionFEDEVE = (filtro.getFEDEVE().isEmpty() || filtro.getFEDEVE().equals("0"))?"":CAMPO7 + " = '" + filtro.getFEDEVE() + "' AND ";
			
			String sCondicionImporte = sComparador.isEmpty()?"":CAMPO15 + " "+sComparador+" " + filtro.getIMNGAS() + " AND ";
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
					+ CAMPO1 + "," 	
					+ CAMPO2 + ","        
					+ CAMPO3 + ","
					+ CAMPO4 + ","
					+ CAMPO5 + ","
					+ CAMPO6 + ","
					+ CAMPO7 + ","
					+ CAMPO9 + ","
					+ CAMPO10 + ","
					+ CAMPO14 + ","
					+ CAMPO15 + ","
					+ CAMPO16 + ","
					+ CAMPO33 + ","
					+ CAMPO34 + ","
					+ CAMPO35 +		

				   " FROM " 
				   + TABLA + 
				   " WHERE ("
				   + sCondicionCOGRUG
				   + sCondicionCOTPGA
				   + sCondicionCOSBGA
				   + sCondicionFEDEVE
				   + sCondicionImporte
				   + CAMPO33 + " > 0 AND "
				   + CAMPO34 + " IN ('" + ValoresDefecto.DEF_GASTO_ABONADO + "','" + ValoresDefecto.DEF_GASTO_PAGADO + "') AND "
	 			   + CAMPO2 + " = '" + filtro.getCOACES() + "')"+
	 			   " ORDER BY "+ CAMPO9;
						   
			
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
						
						String sGastoID = rs.getString(QMGastos.CAMPO1);
						String sNUPROF = QMListaGastosProvisiones.getProvisionDeGasto(conexion, rs.getLong(QMGastos.CAMPO1));
						String sEstadoPROF = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF,QMCodigosControl.IESPROF,QMProvisiones.getEstado(conexion, sNUPROF));
						String sCOACES  = rs.getString(QMGastos.CAMPO2);
						String sCOGRUG  = rs.getString(QMGastos.CAMPO3);
						String sCOTPGA  = rs.getString(QMGastos.CAMPO4);
						String sCOSBGA  = rs.getString(QMGastos.CAMPO5);
						String sDCOSBGA = QMCodigosControl.getDesCOSBGA(conexion,sCOGRUG,sCOTPGA,sCOSBGA);
						String sPTPAGO  = rs.getString(QMGastos.CAMPO6);
						String sDPTPAGO = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
						String sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO7));
						String sCOSIGA  = rs.getString(QMGastos.CAMPO10);
						String sDCOSIGA = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSIGA,QMCodigosControl.ICOSIGA,sCOSIGA);
						//String sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO16).equals("-"),rs.getString(QMGastos.CAMPO15));
						
						String sIMNGAS  = Utils.recuperaImporte(false,rs.getString(QMGastos.CAMPO33));
						//sEstado  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34));
						String sFEEPAI  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO14));
						String sFELIPG  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO9));
						String sTipoPago  = "";
						
						if (sCOSIGA.equals(ValoresDefecto.DEF_GASTO_PAGADO)
							|| sCOSIGA.equals(ValoresDefecto.DEF_GASTO_ABONADO))
						{
							sTipoPago  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOPAGO,QMCodigosControl.ICOPAGO,QMPagos.getTipoPago(conexion, rs.getLong(QMGastos.CAMPO1)));							
						}
						
						String sUrgente  = Utils.recuperaBit((rs.getString(QMGastos.CAMPO35)));
						
						GastoTabla gastoencontrado = new GastoTabla(
								sGastoID,
								sNUPROF,
								sEstadoPROF,
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
								sIMNGAS,
								QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34)),
								sFEEPAI,
								sFELIPG,
								sTipoPago,
								sUrgente);
						
						resultado.add(gastoencontrado);
						
						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<GastoTabla>();

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}			
		}

		return resultado;
	}

	public static ArrayList<GastoTabla> buscaGastosAbonablesPorFiltroEstado(Connection conexion, GastoTabla filtro, String sEstado)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			//Condiciones de filtro
			String sCondicionCOGRUG = filtro.getCOGRUG().isEmpty()?"":CAMPO3 + " = '" + filtro.getCOGRUG() + "' AND ";
			String sCondicionCOTPGA = filtro.getCOTPGA().isEmpty()?"":CAMPO4 + " = '" + filtro.getCOTPGA() + "' AND ";
			String sCondicionCOSBGA = filtro.getCOSBGA().isEmpty()?"":CAMPO5 + " = '" + filtro.getCOSBGA() + "' AND ";
			String sCondicionFEDEVE = (filtro.getFEDEVE().isEmpty() || filtro.getFEDEVE().equals("0"))?"":CAMPO7 + " = '" + filtro.getFEDEVE() + "' AND ";
			String sCondicionEstado = sEstado.isEmpty()?
					CAMPO34 + " IN ('" + ValoresDefecto.DEF_GASTO_AUTORIZADO + "','" + ValoresDefecto.DEF_GASTO_PAGADO + "') AND "
					:CAMPO34 + " = '" + sEstado + "' AND ";
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
					+ CAMPO1 + "," 	
					+ CAMPO2 + ","        
					+ CAMPO3 + ","
					+ CAMPO4 + ","
					+ CAMPO5 + ","
					+ CAMPO6 + ","
					+ CAMPO7 + ","
					+ CAMPO9 + ","
					+ CAMPO10 + ","
					+ CAMPO14 + ","
					+ CAMPO15 + ","
					+ CAMPO16 + ","
					+ CAMPO34 + ","
					+ CAMPO35 +	
	

						   " FROM " 
						   + TABLA + 
						   " WHERE ("
						   + CAMPO33 + " > 0 AND "
						   + sCondicionCOGRUG
						   + sCondicionCOTPGA
						   + sCondicionCOSBGA
						   + sCondicionFEDEVE
						   + sCondicionEstado
						   + CAMPO10 + " <> '" + ValoresDefecto.DEF_GASTO_ABONADO + "' AND "
			 			   + CAMPO2 + " = '" + filtro.getCOACES() + "')";
						   
			
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
						
						String sGastoID = rs.getString(QMGastos.CAMPO1);
						String sNUPROF = QMListaGastosProvisiones.getProvisionDeGasto(conexion, rs.getLong(QMGastos.CAMPO1));
						String sEstadoPROF = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF,QMCodigosControl.IESPROF,QMProvisiones.getEstado(conexion, sNUPROF));
						String sCOACES  = rs.getString(QMGastos.CAMPO2);
						String sCOGRUG  = rs.getString(QMGastos.CAMPO3);
						String sCOTPGA  = rs.getString(QMGastos.CAMPO4);
						String sCOSBGA  = rs.getString(QMGastos.CAMPO5);
						String sDCOSBGA = QMCodigosControl.getDesCOSBGA(conexion,sCOGRUG,sCOTPGA,sCOSBGA);
						String sPTPAGO  = rs.getString(QMGastos.CAMPO6);
						String sDPTPAGO = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
						String sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO7));
						String sCOSIGA  = rs.getString(QMGastos.CAMPO10);
						String sDCOSIGA = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSIGA,QMCodigosControl.ICOSIGA,sCOSIGA);
						String sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO16).equals("-"),rs.getString(QMGastos.CAMPO15));
						//String sEstado  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34));
						String sFEEPAI  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO14));
						String sFELIPG  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO9));
						String sTipoPago  = "";
						
						if (sCOSIGA.equals(ValoresDefecto.DEF_GASTO_PAGADO)
							|| sCOSIGA.equals(ValoresDefecto.DEF_GASTO_ABONADO))
						{
							sTipoPago  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOPAGO,QMCodigosControl.ICOPAGO,QMPagos.getTipoPago(conexion, rs.getLong(QMGastos.CAMPO1)));							
						}
						
						String sUrgente  = Utils.recuperaBit((rs.getString(QMGastos.CAMPO35)));
						
						GastoTabla gastoencontrado = new GastoTabla(
								sGastoID,
								sNUPROF,
								sEstadoPROF,
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
								sIMNGAS,
								QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34)),
								sFEEPAI,
								sFELIPG,
								sTipoPago,
								sUrgente);
						
						resultado.add(gastoencontrado);
						
						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<GastoTabla>();

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}			
		}

		return resultado;
	}
	
	
	public static ArrayList<GastoTabla> buscaGastosAbonadosEjecutablesPorFiltro(Connection conexion, GastoTabla filtro)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			//Condiciones de filtro
			String sCondicionCOGRUG = filtro.getCOGRUG().isEmpty()?"":CAMPO3 + " = '" + filtro.getCOGRUG() + "' AND ";
			String sCondicionCOTPGA = filtro.getCOTPGA().isEmpty()?"":CAMPO4 + " = '" + filtro.getCOTPGA() + "' AND ";
			String sCondicionCOSBGA = filtro.getCOSBGA().isEmpty()?"":CAMPO5 + " = '" + filtro.getCOSBGA() + "' AND ";
			String sCondicionFEDEVE = (filtro.getFEDEVE().isEmpty() || filtro.getFEDEVE().equals("0"))?"":CAMPO7 + " = '" + filtro.getFEDEVE() + "' AND ";

			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
					+ CAMPO1 + "," 	
					+ CAMPO2 + ","        
					+ CAMPO3 + ","
					+ CAMPO4 + ","
					+ CAMPO5 + ","
					+ CAMPO6 + ","
					+ CAMPO7 + ","
					+ CAMPO9 + ","
					+ CAMPO10 + ","
					+ CAMPO14 + ","
					+ CAMPO15 + ","
					+ CAMPO16 + ","
					+ CAMPO34 + ","
					+ CAMPO35 +		
	

						   " FROM " 
						   + TABLA + 
						   " WHERE ("
						   + sCondicionCOGRUG
						   + sCondicionCOTPGA
						   + sCondicionCOSBGA
						   + sCondicionFEDEVE
			 			   + CAMPO2 + " = '" + filtro.getCOACES() + "' AND "
						   + CAMPO10 + " = '" + ValoresDefecto.DEF_GASTO_ABONADO + "' AND "
						   + CAMPO34 + " = '" + ValoresDefecto.DEF_GASTO_ABONADO + "' AND "
			 			   + CAMPO1 + " IN (SELECT "
			 			   + QMListaAbonosGastos.CAMPO1 + 
	   					   " FROM " 
						   + QMListaAbonosGastos.TABLA +
	   					   " WHERE " 
	   					   + QMListaAbonosGastos.CAMPO4 + " = '"+ ValoresDefecto.ABONO_EMITIDO + "'))";
						   
			
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
						
						String sGastoID = rs.getString(QMGastos.CAMPO1);
						String sNUPROF = QMListaGastosProvisiones.getProvisionDeGasto(conexion, rs.getLong(QMGastos.CAMPO1));
						String sEstadoPROF = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF,QMCodigosControl.IESPROF,QMProvisiones.getEstado(conexion, sNUPROF));
						String sCOACES  = rs.getString(QMGastos.CAMPO2);
						String sCOGRUG  = rs.getString(QMGastos.CAMPO3);
						String sCOTPGA  = rs.getString(QMGastos.CAMPO4);
						String sCOSBGA  = rs.getString(QMGastos.CAMPO5);
						String sDCOSBGA = QMCodigosControl.getDesCOSBGA(conexion,sCOGRUG,sCOTPGA,sCOSBGA);
						String sPTPAGO  = rs.getString(QMGastos.CAMPO6);
						String sDPTPAGO = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
						String sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO7));
						String sCOSIGA  = rs.getString(QMGastos.CAMPO10);
						String sDCOSIGA = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSIGA,QMCodigosControl.ICOSIGA,sCOSIGA);
						String sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO16).equals("-"),rs.getString(QMGastos.CAMPO15));
						//String sEstado  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34));
						String sFEEPAI  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO14));
						String sFELIPG  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO9));
						String sTipoPago  = "";
						
						if (sCOSIGA.equals(ValoresDefecto.DEF_GASTO_PAGADO)
							|| sCOSIGA.equals(ValoresDefecto.DEF_GASTO_ABONADO))
						{
							sTipoPago  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOPAGO,QMCodigosControl.ICOPAGO,QMPagos.getTipoPago(conexion, rs.getLong(QMGastos.CAMPO1)));							
						}
						
						String sUrgente  = Utils.recuperaBit((rs.getString(QMGastos.CAMPO35)));
						
						GastoTabla gastoencontrado = new GastoTabla(
								sGastoID,
								sNUPROF,
								sEstadoPROF,
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
								sIMNGAS,
								QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34)),
								sFEEPAI,
								sFELIPG,
								sTipoPago,
								sUrgente);
						
						resultado.add(gastoencontrado);
						
						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<GastoTabla>();

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}			
		}

		return resultado;
	}
}
