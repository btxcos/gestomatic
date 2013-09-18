package com.provisiones.dal.qm.movimientos;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.types.MovimientoCuota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QMMovimientosCuotas
{
	private static Logger logger = LoggerFactory.getLogger(QMMovimientosCuotas.class.getName());

	static String sTable = "e2_movimientos_tbl";

	static String sField1 = "e2_movimiento_id";

	static String sField2  = "cod_codtrn";
	static String sField3  = "cod_cotdor";
	static String sField4  = "idprov";
	static String sField5  = "cod_coacci";
	static String sField6  = "cod_cocldo";
	static String sField7  = "cod_nudcom";
	static String sField8  = "coengp";
	static String sField9  = "cod_coaces";
	static String sField10 = "cogrug";     
	static String sField11 = "cotaca";     
	static String sField12 = "cod_cosbac";     
	static String sField13 = "cod_bitc11"; 
	static String sField14 = "fipago";     
	static String sField15 = "cod_bitc12"; 
	static String sField16 = "ffpago";     
	static String sField17 = "cod_bitc13"; 
	static String sField18 = "imcuco";     
	static String sField19 = "cod_bitc14"; 
	static String sField20 = "faacta";     
	static String sField21 = "cod_bitc15"; 
	static String sField22 = "cod_ptpago";     
	static String sField23 = "cod_bitc09"; 
	static String sField24 = "obtexc";     
	static String sField25 = "obdeer";     

	public static int addMovimientoCuota(MovimientoCuota NuevoMovimientoCuota)
	{
		Statement stmt = null;
		Connection conn = null;
		ResultSet resulset = null;
		
		int iCodigo = 0;

		conn = ConnectionManager.OpenDBConnection();
		
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
			       + sField25 + 
			       ") VALUES ('" 
			       + NuevoMovimientoCuota.getCODTRN() + "','" 
			       + NuevoMovimientoCuota.getCOTDOR() + "','"
			       + NuevoMovimientoCuota.getIDPROV() + "','"
			       + NuevoMovimientoCuota.getCOACCI() + "','"
			       + NuevoMovimientoCuota.getCOCLDO() + "','"
			       + NuevoMovimientoCuota.getNUDCOM() + "','"
			       + NuevoMovimientoCuota.getCOENGP() + "','"
			       + NuevoMovimientoCuota.getCOACES() + "','"
			       + NuevoMovimientoCuota.getCOGRUG() + "','"
			       + NuevoMovimientoCuota.getCOTACA() + "','"
			       + NuevoMovimientoCuota.getCOSBAC() + "','"
			       + NuevoMovimientoCuota.getBITC11() + "','"
			       + NuevoMovimientoCuota.getFIPAGO() + "','"
			       + NuevoMovimientoCuota.getBITC12() + "','"
			       + NuevoMovimientoCuota.getFFPAGO() + "','"
			       + NuevoMovimientoCuota.getBITC13() + "','"
			       + NuevoMovimientoCuota.getIMCUCO() + "','"
			       + NuevoMovimientoCuota.getBITC14() + "','"
			       + NuevoMovimientoCuota.getFAACTA() + "','"
			       + NuevoMovimientoCuota.getBITC15() + "','"
			       + NuevoMovimientoCuota.getPTPAGO() + "','"
			       + NuevoMovimientoCuota.getBITC09() + "','"
			       + NuevoMovimientoCuota.getOBTEXC() + "','"
			       + NuevoMovimientoCuota.getOBDEER() + "' )";
		
		logger.debug(sQuery);

		try {

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
				       + sField25 + 
				       ") VALUES ('" 
				       + NuevoMovimientoCuota.getCODTRN() + "','" 
				       + NuevoMovimientoCuota.getCOTDOR() + "','"
				       + NuevoMovimientoCuota.getIDPROV() + "','"
				       + NuevoMovimientoCuota.getCOACCI() + "','"
				       + NuevoMovimientoCuota.getCOCLDO() + "','"
				       + NuevoMovimientoCuota.getNUDCOM() + "','"
				       + NuevoMovimientoCuota.getCOENGP() + "','"
				       + NuevoMovimientoCuota.getCOACES() + "','"
				       + NuevoMovimientoCuota.getCOGRUG() + "','"
				       + NuevoMovimientoCuota.getCOTACA() + "','"
				       + NuevoMovimientoCuota.getCOSBAC() + "','"
				       + NuevoMovimientoCuota.getBITC11() + "','"
				       + NuevoMovimientoCuota.getFIPAGO() + "','"
				       + NuevoMovimientoCuota.getBITC12() + "','"
				       + NuevoMovimientoCuota.getFFPAGO() + "','"
				       + NuevoMovimientoCuota.getBITC13() + "','"
				       + NuevoMovimientoCuota.getIMCUCO() + "','"
				       + NuevoMovimientoCuota.getBITC14() + "','"
				       + NuevoMovimientoCuota.getFAACTA() + "','"
				       + NuevoMovimientoCuota.getBITC15() + "','"
				       + NuevoMovimientoCuota.getPTPAGO() + "','"
				       + NuevoMovimientoCuota.getBITC09() + "','"
				       + NuevoMovimientoCuota.getOBTEXC() + "','"
				       + NuevoMovimientoCuota.getOBDEER() + "' )", Statement.RETURN_GENERATED_KEYS);
			
			resulset = stmt.getGeneratedKeys();
			
			logger.debug("Ejecutada con exito!");
			
			if (resulset.next()) 
			{
				iCodigo= resulset.getInt(1);
			} 
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: COACES:|{}|",NuevoMovimientoCuota.getCOACES());
			logger.error("ERROR: COCLDO:|{}|",NuevoMovimientoCuota.getCOCLDO());
			logger.error("ERROR: NUDCOM:|{}|",NuevoMovimientoCuota.getNUDCOM());
			logger.error("ERROR: COSBAC:|{}|",NuevoMovimientoCuota.getCOSBAC());
			
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
	public static boolean modMovimientoCuota(MovimientoCuota NuevoMovimientoCuota, String sMovimientoCuotaID)
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
					+ sField2  + " = '"+ NuevoMovimientoCuota.getCODTRN() + "', "
					+ sField3  + " = '"+ NuevoMovimientoCuota.getCOTDOR() + "', "
					+ sField4  + " = '"+ NuevoMovimientoCuota.getIDPROV() + "', "
					+ sField5  + " = '"+ NuevoMovimientoCuota.getCOACCI() + "', "
					+ sField6  + " = '"+ NuevoMovimientoCuota.getCOCLDO() + "', "
					+ sField7  + " = '"+ NuevoMovimientoCuota.getNUDCOM() + "', "
					+ sField8  + " = '"+ NuevoMovimientoCuota.getCOENGP() + "', "
					+ sField9  + " = '"+ NuevoMovimientoCuota.getCOACES() + "', "
					+ sField10 + " = '"+ NuevoMovimientoCuota.getCOGRUG() + "', "
					+ sField11 + " = '"+ NuevoMovimientoCuota.getCOTACA() + "', "
					+ sField12 + " = '"+ NuevoMovimientoCuota.getCOSBAC() + "', "
					+ sField13 + " = '"+ NuevoMovimientoCuota.getBITC11() + "', "
					+ sField14 + " = '"+ NuevoMovimientoCuota.getFIPAGO() + "', "
					+ sField15 + " = '"+ NuevoMovimientoCuota.getBITC12() + "', "
					+ sField16 + " = '"+ NuevoMovimientoCuota.getFFPAGO() + "', "
					+ sField17 + " = '"+ NuevoMovimientoCuota.getBITC13() + "', "
					+ sField18 + " = '"+ NuevoMovimientoCuota.getIMCUCO() + "', "
					+ sField19 + " = '"+ NuevoMovimientoCuota.getBITC14() + "', "
					+ sField20 + " = '"+ NuevoMovimientoCuota.getFAACTA() + "', "
					+ sField21 + " = '"+ NuevoMovimientoCuota.getBITC15() + "', "
					+ sField22 + " = '"+ NuevoMovimientoCuota.getPTPAGO() + "', "
					+ sField23 + " = '"+ NuevoMovimientoCuota.getBITC09() + "', "
					+ sField24 + " = '"+ NuevoMovimientoCuota.getOBTEXC() + "', "
					+ sField25 + " = '"+ NuevoMovimientoCuota.getOBDEER() +
					"' "+
					" WHERE "
					+ sField1 + " = '"+ sMovimientoCuotaID +"'");
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: MovimientoCuotaID:|{}|",sMovimientoCuotaID);
			logger.error("ERROR: COACES:|{}|",NuevoMovimientoCuota.getCOACES());
			logger.error("ERROR: COCLDO:|{}|",NuevoMovimientoCuota.getCOCLDO());
			logger.error("ERROR: NUDCOM:|{}|",NuevoMovimientoCuota.getNUDCOM());
			logger.error("ERROR: COSBAC:|{}|",NuevoMovimientoCuota.getCOSBAC());

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

	public static boolean delMovimientoCuota(String sMovimientoCuotaID)
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
					" WHERE (" + sField1 + " = '" + sMovimientoCuotaID + "' )");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: MovimientoCuotaID:|{}|",sMovimientoCuotaID);


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

	public static MovimientoCuota getMovimientoCuota(String sMovimientoCuotaID)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sCODTRN = "";
		String sCOTDOR = "";
		String sIDPROV = "";
		String sCOACCI = "";
		String sCOCLDO = "";
		String sNUDCOM = "";
		String sCOENGP = "";
		String sCOACES = "";
		String sCOGRUG = "";
		String sCOTACA = "";
		String sCOSBAC = "";
		String sBITC11 = "";
		String sFIPAGO = "";
		String sBITC12 = "";
		String sFFPAGO = "";
		String sBITC13 = "";
		String sIMCUCO = "";
		String sBITC14 = "";
		String sFAACTA = "";
		String sBITC15 = "";
		String sPTPAGO = "";
		String sBITC09 = "";
		String sOBTEXC = "";
		String sOBDEER = "";

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
				       + sField25 +              
       
			"  FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sMovimientoCuotaID	+ "')");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCODTRN = rs.getString(sField2);
					sCOTDOR = rs.getString(sField3);
					sIDPROV = rs.getString(sField4);
					sCOACCI = rs.getString(sField5);
					sCOCLDO = rs.getString(sField6);
					sNUDCOM = rs.getString(sField7);
					sCOENGP = rs.getString(sField8);
					sCOACES = rs.getString(sField9); 
					sCOGRUG = rs.getString(sField10);
					sCOTACA = rs.getString(sField11);
					sCOSBAC = rs.getString(sField12);
					sBITC11 = rs.getString(sField13);
					sFIPAGO = rs.getString(sField14);
					sBITC12 = rs.getString(sField15);
					sFFPAGO = rs.getString(sField16);
					sBITC13 = rs.getString(sField17);
					sIMCUCO = rs.getString(sField18);
					sBITC14 = rs.getString(sField19);
					sFAACTA = rs.getString(sField20);
					sBITC15 = rs.getString(sField21);
					sPTPAGO = rs.getString(sField22);
					sBITC09 = rs.getString(sField23);
					sOBTEXC = rs.getString(sField24);
					sOBDEER = rs.getString(sField25);

					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",sField1,sMovimientoCuotaID);


				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: MovimientoCuotaID:|{}|",sMovimientoCuotaID);

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
		return new MovimientoCuota(sCODTRN, sCOTDOR, sIDPROV, sCOACCI, sCOCLDO, sNUDCOM,
				sCOENGP, sCOACES, sCOGRUG, sCOTACA, sCOSBAC, sBITC11, sFIPAGO,
				sBITC12, sFFPAGO, sBITC13, sIMCUCO, sBITC14, sFAACTA, sBITC15,
				sPTPAGO, sBITC09, sOBTEXC, sOBDEER);
	}
	
	public static String getMovimientoCuotaID(MovimientoCuota cuota)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sMovimientoCuotaID = "";

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
					       + sField2  +" = '" + cuota.getCODTRN() + "' AND "
					       + sField3  +" = '" + cuota.getCOTDOR() + "' AND "
					       + sField4  +" = '" + cuota.getIDPROV() + "' AND "
					       + sField5  +" = '" + cuota.getCOACCI() + "' AND "
					       + sField6  +" = '" + cuota.getCOCLDO() + "' AND "
					       + sField7  +" = '" + cuota.getNUDCOM() + "' AND "
					       + sField8  +" = '" + cuota.getCOENGP() + "' AND "
					       + sField9  +" = '" + cuota.getCOACES() + "' AND "
					       + sField10 +" = '" + cuota.getCOGRUG() + "' AND "
					       + sField11 +" = '" + cuota.getCOTACA() + "' AND "
					       + sField12 +" = '" + cuota.getCOSBAC() + "' AND "
					       + sField13 +" = '" + cuota.getBITC11() + "' AND "
					       + sField14 +" = '" + cuota.getFIPAGO() + "' AND "
					       + sField15 +" = '" + cuota.getBITC12() + "' AND "
					       + sField16 +" = '" + cuota.getFFPAGO() + "' AND "
					       + sField17 +" = '" + cuota.getBITC13() + "' AND "
					       + sField18 +" = '" + cuota.getIMCUCO() + "' AND "
					       + sField19 +" = '" + cuota.getBITC14() + "' AND "
					       + sField20 +" = '" + cuota.getFAACTA() + "' AND "
					       + sField21 +" = '" + cuota.getBITC15() + "' AND "
					       + sField22 +" = '" + cuota.getPTPAGO() + "' AND "
					       + sField23 +" = '" + cuota.getBITC09() + "' AND "
					       + sField24 +" = '" + cuota.getOBTEXC() + "' AND "
					       + sField25 +" = '" + cuota.getOBDEER() + "' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			
			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sMovimientoCuotaID = rs.getString(sField1);
					
					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",sField1,sMovimientoCuotaID);
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: COACES:|{}|",cuota.getCOACES());
			logger.error("ERROR: COCLDO:|{}|",cuota.getCOCLDO());
			logger.error("ERROR: NUDCOM:|{}|",cuota.getNUDCOM());
			logger.error("ERROR: COSBAC:|{}|",cuota.getCOSBAC());

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
		return sMovimientoCuotaID;
	}

}
