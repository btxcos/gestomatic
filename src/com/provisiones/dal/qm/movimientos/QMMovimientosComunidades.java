package com.provisiones.dal.qm.movimientos;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.types.movimientos.MovimientoComunidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QMMovimientosComunidades
{
	private static Logger logger = LoggerFactory.getLogger(QMMovimientosComunidades.class.getName());

	public static String TABLA = "pp001_e1_movimientos_tbl";

	public static String CAMPO1 = "e1_movimiento_id";
	
	static String CAMPO2 = "cod_codtrn";
	static String CAMPO3 = "cod_cotdor";
	static String CAMPO4 = "idprov";
	static String CAMPO5 = "cod_coacci";
	static String CAMPO6 = "coengp";
	public static String CAMPO7 = "cod_cocldo";
	public static String CAMPO8 = "cod_nudcom";
	static String CAMPO9 = "cod_bitc10";
	public static String CAMPO10 = "cod_coaces";
	static String CAMPO11 = "cod_bitc01";
	public static String CAMPO12 = "nomcoc";
	static String CAMPO13 = "cod_bitc02";
	static String CAMPO14 = "nodcco";
	static String CAMPO15 = "cod_bitc03";
	static String CAMPO16 = "nomprc";
	static String CAMPO17 = "cod_bitc04";
	static String CAMPO18 = "nutprc";
	static String CAMPO19 = "cod_bitc05";
	static String CAMPO20 = "nomadc";
	static String CAMPO21 = "cod_bitc06";
	static String CAMPO22 = "nutadc";
	static String CAMPO23 = "cod_bitc07";
	static String CAMPO24 = "nodcad";
	static String CAMPO25 = "cod_bitc08";
	static String CAMPO26 = "nuccen";
	static String CAMPO27 = "nuccof";
	static String CAMPO28 = "nuccdi";
	static String CAMPO29 = "nuccnt";
	static String CAMPO30 = "cod_bitc09";
	static String CAMPO31 = "obtexc";
	static String CAMPO32 = "obdeer";         

	


	public static int addMovimientoComunidad(MovimientoComunidad NuevoMovimientoComunidad)
	{
		Statement stmt = null;
		Connection conn = null;
		ResultSet resulset = null;
		
		int iCodigo = 0;

		conn = ConnectionManager.OpenDBConnection();
		
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
			       + CAMPO32 +               
			       ") VALUES ('" 
			       + NuevoMovimientoComunidad.getCODTRN() + "','" 
			       + NuevoMovimientoComunidad.getCOTDOR() + "','"
			       + NuevoMovimientoComunidad.getIDPROV() + "','"
			       + NuevoMovimientoComunidad.getCOACCI() + "','"
			       + NuevoMovimientoComunidad.getCOENGP() + "','"
			       + NuevoMovimientoComunidad.getCOCLDO() + "','"
			       + NuevoMovimientoComunidad.getNUDCOM() + "','"
			       + NuevoMovimientoComunidad.getBITC10() + "','"
			       + NuevoMovimientoComunidad.getCOACES() + "','"
			       + NuevoMovimientoComunidad.getBITC01() + "','"
			       + NuevoMovimientoComunidad.getNOMCOC() + "','"
			       + NuevoMovimientoComunidad.getBITC02() + "','"
			       + NuevoMovimientoComunidad.getNODCCO() + "','"
			       + NuevoMovimientoComunidad.getBITC03() + "','"
			       + NuevoMovimientoComunidad.getNOMPRC() + "','"
			       + NuevoMovimientoComunidad.getBITC04() + "','"
			       + NuevoMovimientoComunidad.getNUTPRC() + "','"
			       + NuevoMovimientoComunidad.getBITC05() + "','"
			       + NuevoMovimientoComunidad.getNOMADC() + "','"
			       + NuevoMovimientoComunidad.getBITC06() + "','"
			       + NuevoMovimientoComunidad.getNUTADC() + "','"
			       + NuevoMovimientoComunidad.getBITC07() + "','"
			       + NuevoMovimientoComunidad.getNODCAD() + "','"
			       + NuevoMovimientoComunidad.getBITC08() + "','"
			       + NuevoMovimientoComunidad.getNUCCEN() + "','"
			       + NuevoMovimientoComunidad.getNUCCOF() + "','"
			       + NuevoMovimientoComunidad.getNUCCDI() + "','"
			       + NuevoMovimientoComunidad.getNUCCNT() + "','"
			       + NuevoMovimientoComunidad.getBITC09() + "','"
			       + NuevoMovimientoComunidad.getOBTEXC() + "','"
			       + NuevoMovimientoComunidad.getOBDEER() + 
			       "' )";
		
		logger.debug(sQuery);

		try {

			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery, Statement.RETURN_GENERATED_KEYS);
			
			resulset = stmt.getGeneratedKeys();
			
			logger.debug("Ejecutada con exito!");
			
			if (resulset.next()) 
			{
				iCodigo= resulset.getInt(1);
			} 
		} 
		catch (SQLException ex) 
		{


			logger.error("ERROR COCLDO:|"+NuevoMovimientoComunidad.getCOCLDO()+"|");
			logger.error("ERROR NUDCOM:|"+NuevoMovimientoComunidad.getNUDCOM()+"|");
			logger.error("ERROR COACES:|"+NuevoMovimientoComunidad.getCOACES()+"|");
			
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

			//bSalida = false;
			
		} 
		finally
		{

			Utils.closeStatement(stmt);
			Utils.closeResultSet(resulset);
		}

		logger.error("iCodigo: |" + iCodigo +"|");
		
		ConnectionManager.CloseDBConnection(conn);
		return iCodigo;//bSalida
	}
	public static boolean modMovimientoComunidad(MovimientoComunidad NuevoMovimientoComunidad, String sMovimientoComunidadID)
	{
		Statement stmt = null;
		boolean bSalida = true;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO2  + " = '"+ NuevoMovimientoComunidad.getCODTRN() + "', " 
				+ CAMPO3  + " = '"+ NuevoMovimientoComunidad.getCOTDOR() + "', " 
				+ CAMPO4  + " = '"+ NuevoMovimientoComunidad.getIDPROV() + "', " 
				+ CAMPO5  + " = '"+ NuevoMovimientoComunidad.getCOACCI() + "', " 
				+ CAMPO6  + " = '"+ NuevoMovimientoComunidad.getCOENGP() + "', " 
				+ CAMPO7  + " = '"+ NuevoMovimientoComunidad.getCOCLDO() + "', " 
				+ CAMPO8  + " = '"+ NuevoMovimientoComunidad.getNUDCOM() + "', " 
				+ CAMPO9  + " = '"+ NuevoMovimientoComunidad.getBITC10() + "', " 
				+ CAMPO10 + " = '"+ NuevoMovimientoComunidad.getCOACES() + "', " 
				+ CAMPO11 + " = '"+ NuevoMovimientoComunidad.getBITC01() + "', " 
				+ CAMPO12 + " = '"+ NuevoMovimientoComunidad.getNOMCOC() + "', " 
				+ CAMPO13 + " = '"+ NuevoMovimientoComunidad.getBITC02() + "', " 
				+ CAMPO14 + " = '"+ NuevoMovimientoComunidad.getNODCCO() + "', " 
				+ CAMPO15 + " = '"+ NuevoMovimientoComunidad.getBITC03() + "', " 
				+ CAMPO16 + " = '"+ NuevoMovimientoComunidad.getNOMPRC() + "', " 
				+ CAMPO17 + " = '"+ NuevoMovimientoComunidad.getBITC04() + "', " 
				+ CAMPO18 + " = '"+ NuevoMovimientoComunidad.getNUTPRC() + "', " 
				+ CAMPO19 + " = '"+ NuevoMovimientoComunidad.getBITC05() + "', " 
				+ CAMPO20 + " = '"+ NuevoMovimientoComunidad.getNOMADC() + "', " 
				+ CAMPO21 + " = '"+ NuevoMovimientoComunidad.getBITC06() + "', " 
				+ CAMPO22 + " = '"+ NuevoMovimientoComunidad.getNUTADC() + "', " 
				+ CAMPO23 + " = '"+ NuevoMovimientoComunidad.getBITC07() + "', " 
				+ CAMPO24 + " = '"+ NuevoMovimientoComunidad.getNODCAD() + "', " 
				+ CAMPO25 + " = '"+ NuevoMovimientoComunidad.getBITC08() + "', " 
				+ CAMPO26 + " = '"+ NuevoMovimientoComunidad.getNUCCEN() + "', " 
				+ CAMPO27 + " = '"+ NuevoMovimientoComunidad.getNUCCOF() + "', " 
				+ CAMPO28 + " = '"+ NuevoMovimientoComunidad.getNUCCDI() + "', " 
				+ CAMPO29 + " = '"+ NuevoMovimientoComunidad.getNUCCNT() + "', " 
				+ CAMPO30 + " = '"+ NuevoMovimientoComunidad.getBITC09() + "', " 
				+ CAMPO31 + " = '"+ NuevoMovimientoComunidad.getOBTEXC() + "', " 
				+ CAMPO32 + " = '"+ NuevoMovimientoComunidad.getOBDEER() +
				"' "+
				" WHERE "
				+ CAMPO1 + " = '"+ sMovimientoComunidadID +"'";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR MovimientoComunidadID:|"+sMovimientoComunidadID+"|");
			
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static boolean delMovimientoComunidad(String sMovimientoComunidadID)
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "DELETE FROM " 
				+ TABLA + 
				" WHERE "
				+ CAMPO1 + " = '" + sMovimientoComunidadID + "'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR MovimientoComunidadID:|"+sMovimientoComunidadID+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	
	public static MovimientoComunidad getMovimientoComunidad(String sMovimientoComunidadID)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sCODTRN = "";
		String sCOTDOR = "";
		String sIDPROV = "";
		String sCOACCI = "";
		String sCOENGP = "";
		String sCOCLDO = "";
		String sNUDCOM = "";
		String sBITC10 = "";
		String sCOACES = "";
		String sBITC01 = "";
		String sNOMCOC = "";
		String sBITC02 = "";
		String sNODCCO = "";
		String sBITC03 = "";
		String sNOMPRC = "";
		String sBITC04 = "";
		String sNUTPRC = "";
		String sBITC05 = "";
		String sNOMADC = "";
		String sBITC06 = "";
		String sNUTADC = "";
		String sBITC07 = "";
		String sNODCAD = "";
		String sBITC08 = "";
		String sNUCCEN = "";
		String sNUCCOF = "";
		String sNUCCDI = "";
		String sNUCCNT = "";
		String sBITC09 = "";
		String sOBTEXC = "";
		String sOBDEER = "";

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
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
			       + CAMPO1 + " = '" + sMovimientoComunidadID + "'";
		
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

					sCODTRN = rs.getString(CAMPO2);  
					sCOTDOR = rs.getString(CAMPO3);  
					sIDPROV = rs.getString(CAMPO4);  
					sCOACCI = rs.getString(CAMPO5);  
					sCOENGP = rs.getString(CAMPO6);  
					sCOCLDO = rs.getString(CAMPO7);  
					sNUDCOM = rs.getString(CAMPO8);  
					sBITC10 = rs.getString(CAMPO9);  
					sCOACES = rs.getString(CAMPO10); 
					sBITC01 = rs.getString(CAMPO11); 
					sNOMCOC = rs.getString(CAMPO12); 
					sBITC02 = rs.getString(CAMPO13); 
					sNODCCO = rs.getString(CAMPO14); 
					sBITC03 = rs.getString(CAMPO15); 
					sNOMPRC = rs.getString(CAMPO16); 
					sBITC04 = rs.getString(CAMPO17); 
					sNUTPRC = rs.getString(CAMPO18); 
					sBITC05 = rs.getString(CAMPO19); 
					sNOMADC = rs.getString(CAMPO20); 
					sBITC06 = rs.getString(CAMPO21); 
					sNUTADC = rs.getString(CAMPO22); 
					sBITC07 = rs.getString(CAMPO23); 
					sNODCAD = rs.getString(CAMPO24); 
					sBITC08 = rs.getString(CAMPO25); 
					sNUCCEN = rs.getString(CAMPO26); 
					sNUCCOF = rs.getString(CAMPO27); 
					sNUCCDI = rs.getString(CAMPO28); 
					sNUCCNT = rs.getString(CAMPO29); 
					sBITC09 = rs.getString(CAMPO30); 
					sOBTEXC = rs.getString(CAMPO31); 
					sOBDEER = rs.getString(CAMPO32); 
					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO1+":|"+sMovimientoComunidadID+"|");


				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR MovimientoComunidadID:|"+sMovimientoComunidadID+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return new MovimientoComunidad(sCODTRN, sCOTDOR, sIDPROV, sCOACCI, sCOENGP,
				sCOCLDO, sNUDCOM, sBITC10, sCOACES, sBITC01, sNOMCOC, sBITC02,
				sNODCCO, sBITC03, sNOMPRC, sBITC04, sNUTPRC, sBITC05, sNOMADC,
				sBITC06, sNUTADC, sBITC07, sNODCAD, sBITC08, sNUCCEN, sNUCCOF,
				sNUCCDI, sNUCCNT, sBITC09, sOBTEXC, sOBDEER);
	}
	
	public static String getMovimientoComunidadID(MovimientoComunidad comunidad)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sMovimientoComunidadID = "";

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
				+ CAMPO1 + 
				" FROM " 
				+ TABLA + 
				" WHERE ("
				+ CAMPO2  +" = '" + comunidad.getCODTRN() + "' AND "
				+ CAMPO4  +" = '" + comunidad.getIDPROV() + "' AND "
				+ CAMPO5  +" = '" + comunidad.getCOACCI() + "' AND "
				+ CAMPO6  +" = '" + comunidad.getCOENGP() + "' AND "
				+ CAMPO7  +" = '" + comunidad.getCOCLDO() + "' AND "
				+ CAMPO8  +" = '" + comunidad.getNUDCOM() + "' AND "
				+ CAMPO9  +" = '" + comunidad.getBITC10() + "' AND "
				+ CAMPO10 +" = '" + comunidad.getCOACES() + "' AND "
				+ CAMPO11 +" = '" + comunidad.getBITC01() + "' AND "
				+ CAMPO12 +" = '" + comunidad.getNOMCOC() + "' AND "
				+ CAMPO13 +" = '" + comunidad.getBITC02() + "' AND "
				+ CAMPO14 +" = '" + comunidad.getNODCCO() + "' AND "
				+ CAMPO15 +" = '" + comunidad.getBITC03() + "' AND "
				+ CAMPO16 +" = '" + comunidad.getNOMPRC() + "' AND "
				+ CAMPO17 +" = '" + comunidad.getBITC04() + "' AND "
				+ CAMPO18 +" = '" + comunidad.getNUTPRC() + "' AND "
				+ CAMPO19 +" = '" + comunidad.getBITC05() + "' AND "
				+ CAMPO20 +" = '" + comunidad.getNOMADC() + "' AND "
				+ CAMPO21 +" = '" + comunidad.getBITC06() + "' AND "
				+ CAMPO22 +" = '" + comunidad.getNUTADC() + "' AND "
				+ CAMPO23 +" = '" + comunidad.getBITC07() + "' AND "
				+ CAMPO24 +" = '" + comunidad.getNODCAD() + "' AND "
				+ CAMPO25 +" = '" + comunidad.getBITC08() + "' AND "
				+ CAMPO26 +" = '" + comunidad.getNUCCEN() + "' AND "
				+ CAMPO27 +" = '" + comunidad.getNUCCOF() + "' AND "
				+ CAMPO28 +" = '" + comunidad.getNUCCDI() + "' AND "
				+ CAMPO29 +" = '" + comunidad.getNUCCNT() + "' AND "
				+ CAMPO30 +" = '" + comunidad.getBITC09() + "' AND "
				+ CAMPO31 +" = '" + comunidad.getOBTEXC() + 
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

					sMovimientoComunidadID = rs.getString(CAMPO1);
					
					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO1+":|"+sMovimientoComunidadID+"|");

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}			
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR COCLDO:|"+comunidad.getCOCLDO()+"|");
			logger.error("ERROR NUDCOM:|"+comunidad.getNUDCOM()+"|");
			logger.error("ERROR COACES:|"+comunidad.getCOACES()+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return sMovimientoComunidadID;
	}
	
	public static boolean existeMovimientoComunidad(String sMovimientoComunidadID)
	{
		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		Connection conn = null;
		
		boolean found = false;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO1 + 
				" FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO1 + " = '" + sMovimientoComunidadID + "'";
		
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
					logger.debug(CAMPO1+":|"+rs.getString(CAMPO1)+"|");
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontro la información.");
			}
		} 
		catch (SQLException ex) 
		{
			found = false;
			logger.error("ERROR MovimientoComunidadID:|"+sMovimientoComunidadID+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return found;
	}
}