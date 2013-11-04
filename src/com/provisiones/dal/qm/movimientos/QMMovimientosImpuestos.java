package com.provisiones.dal.qm.movimientos;

import com.provisiones.dal.ConnectionManager;

import com.provisiones.misc.Utils;
import com.provisiones.types.movimientos.MovimientoImpuestoRecurso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QMMovimientosImpuestos 
{
	private static Logger logger = LoggerFactory.getLogger(QMMovimientosImpuestos.class.getName());

	public static String TABLA = "pp001_e4_movimientos_tbl";

	public static String CAMPO1 = "e4_movimiento_id";

	static String CAMPO2  = "cod_codtrn";
	static String CAMPO3  = "cod_cotdor";
	static String CAMPO4  = "idprov";    
	static String CAMPO5  = "cod_coacci";
	static String CAMPO6  = "coengp";    
	public static String CAMPO7  = "cod_coaces";
	public static String CAMPO8  = "cod_nurcat";    
	static String CAMPO9  = "cogruc";
	static String CAMPO10 = "cotaca";
	public static String CAMPO11 = "cod_cosbac";
	static String CAMPO12 = "cod_bitc18";
	static String CAMPO13 = "feprre";    
	static String CAMPO14 = "cod_bitc19";
	static String CAMPO15 = "ferere";    
	static String CAMPO16 = "cod_bitc20";
	static String CAMPO17 = "fedein";    
	static String CAMPO18 = "cod_bitc21";
	static String CAMPO19 = "cod_bisode";
	static String CAMPO20 = "cod_bitc22";
	static String CAMPO21 = "cod_bireso";
	static String CAMPO22 = "cotexa";    
	static String CAMPO23 = "cod_bitc09";
	static String CAMPO24 = "obtexc";    
	static String CAMPO25 = "obdeer";		

	public static int addMovimientoImpuestoRecurso(MovimientoImpuestoRecurso NuevoMovimientoImpuestoRecurso)
	{
		Statement stmt = null;
		Connection conn = null;
		ResultSet resulset = null;
		
		int iCodigo = 0;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try {

			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + TABLA + " ("
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
				       + CAMPO25 +  
				       ") VALUES ('" 
				       + NuevoMovimientoImpuestoRecurso.getCODTRN() + "','" 
				       + NuevoMovimientoImpuestoRecurso.getCOTDOR() + "','"
				       + NuevoMovimientoImpuestoRecurso.getIDPROV() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOACCI() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOENGP() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOACES() + "','"
				       + NuevoMovimientoImpuestoRecurso.getNURCAT() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOGRUC() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOTACA() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOSBAC() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBITC18() + "','"
				       + NuevoMovimientoImpuestoRecurso.getFEPRRE() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBITC19() + "','"
				       + NuevoMovimientoImpuestoRecurso.getFERERE() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBITC20() + "','"
				       + NuevoMovimientoImpuestoRecurso.getFEDEIN() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBITC21() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBISODE() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBITC22() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBIRESO() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOTEXA() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBITC09() + "','"
				       + NuevoMovimientoImpuestoRecurso.getOBTEXC() + "','"
				       + NuevoMovimientoImpuestoRecurso.getOBDEER() + "' )", Statement.RETURN_GENERATED_KEYS);
			
			resulset = stmt.getGeneratedKeys();
			
			logger.debug("Ejecutada con exito!");
			
			if (resulset.next()) 
			{
				iCodigo= resulset.getInt(1);
			} 
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR COACES:|"+NuevoMovimientoImpuestoRecurso.getCOACES()+"|");
			logger.error("ERROR NURCAT:|"+NuevoMovimientoImpuestoRecurso.getNURCAT()+"|");
			logger.error("ERROR COSBAC:|"+NuevoMovimientoImpuestoRecurso.getCOSBAC()+"|");
			
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
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
	public static boolean modMovimientoImpuestoRecurso(MovimientoImpuestoRecurso NuevoMovimientoImpuestoRecurso, String sMovimientoImpuestoRecursoID)
	{
		Statement stmt = null;
		boolean bSalida = true;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + TABLA + 
					" SET " 
					+ CAMPO2  + " = '"+ NuevoMovimientoImpuestoRecurso.getCODTRN() + "', "
					+ CAMPO3  + " = '"+ NuevoMovimientoImpuestoRecurso.getCOTDOR() + "', "
					+ CAMPO4  + " = '"+ NuevoMovimientoImpuestoRecurso.getIDPROV() + "', "
					+ CAMPO5  + " = '"+ NuevoMovimientoImpuestoRecurso.getCOACCI() + "', "
					+ CAMPO6  + " = '"+ NuevoMovimientoImpuestoRecurso.getCOENGP() + "', "
					+ CAMPO7  + " = '"+ NuevoMovimientoImpuestoRecurso.getCOACES() + "', "
					+ CAMPO8  + " = '"+ NuevoMovimientoImpuestoRecurso.getNURCAT() + "', "
					+ CAMPO9  + " = '"+ NuevoMovimientoImpuestoRecurso.getCOGRUC() + "', "
					+ CAMPO10 + " = '"+ NuevoMovimientoImpuestoRecurso.getCOTACA() + "', "
					+ CAMPO11 + " = '"+ NuevoMovimientoImpuestoRecurso.getCOSBAC() + "', "
					+ CAMPO12 + " = '"+ NuevoMovimientoImpuestoRecurso.getBITC18() + "', "
					+ CAMPO13 + " = '"+ NuevoMovimientoImpuestoRecurso.getFEPRRE() + "', "
					+ CAMPO14 + " = '"+ NuevoMovimientoImpuestoRecurso.getBITC19() + "', "
					+ CAMPO15 + " = '"+ NuevoMovimientoImpuestoRecurso.getFERERE() + "', "
					+ CAMPO16 + " = '"+ NuevoMovimientoImpuestoRecurso.getBITC20() + "', "
					+ CAMPO17 + " = '"+ NuevoMovimientoImpuestoRecurso.getFEDEIN() + "', "
					+ CAMPO18 + " = '"+ NuevoMovimientoImpuestoRecurso.getBITC21() + "', "
					+ CAMPO19 + " = '"+ NuevoMovimientoImpuestoRecurso.getBISODE() + "', "
					+ CAMPO20 + " = '"+ NuevoMovimientoImpuestoRecurso.getBITC22() + "', "
					+ CAMPO21 + " = '"+ NuevoMovimientoImpuestoRecurso.getBIRESO() + "', "
					+ CAMPO22 + " = '"+ NuevoMovimientoImpuestoRecurso.getCOTEXA() + "', "
					+ CAMPO23 + " = '"+ NuevoMovimientoImpuestoRecurso.getBITC09() + "', "
					+ CAMPO24 + " = '"+ NuevoMovimientoImpuestoRecurso.getOBTEXC() + "', "
					+ CAMPO25 + " = '"+ NuevoMovimientoImpuestoRecurso.getOBDEER() +
					"' "+
					" WHERE "
					+ CAMPO1 + " = '"+ sMovimientoImpuestoRecursoID +"'");
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR MovimientoImpuestoRecursoID:|"+sMovimientoImpuestoRecursoID+"|");

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

	public static boolean delMovimientoImpuestoRecurso(String sMovimientoImpuestoRecursoID)
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + TABLA + 
					" WHERE (" + CAMPO1 + " = '" + sMovimientoImpuestoRecursoID + "' )");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR MovimientoImpuestoRecursoID:|"+sMovimientoImpuestoRecursoID+"|");

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

	public static MovimientoImpuestoRecurso getMovimientoImpuestoRecurso(String sMovimientoImpuestoRecursoID)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sCODTRN = "";
		String sCOTDOR = "";
		String sIDPROV = "";
		String sCOACCI = "";
		String sCOENGP = "";
		String sCOACES = "";
		String sNURCAT = "";
		String sCOGRUC = "";
		String sCOTACA = "";
		String sCOSBAC = "";
		String sBITC18 = "";
		String sFEPRRE = "";
		String sBITC19 = "";
		String sFERERE = "";
		String sBITC20 = "";
		String sFEDEIN = "";
		String sBITC21 = "";
		String sBISODE = "";
		String sBITC22 = "";
		String sBIRESO = "";
		String sCOTEXA = "";
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
				       + CAMPO25 +             
       
			"  FROM " + TABLA + 
					" WHERE (" + CAMPO1 + " = '" + sMovimientoImpuestoRecursoID	+ "')");

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
					sCOACES = rs.getString(CAMPO7); 
					sNURCAT = rs.getString(CAMPO8); 
					sCOGRUC = rs.getString(CAMPO9); 
					sCOTACA = rs.getString(CAMPO10);
					sCOSBAC = rs.getString(CAMPO11);
					sBITC18 = rs.getString(CAMPO12);
					sFEPRRE = rs.getString(CAMPO13);
					sBITC19 = rs.getString(CAMPO14);
					sFERERE = rs.getString(CAMPO15);
					sBITC20 = rs.getString(CAMPO16);
					sFEDEIN = rs.getString(CAMPO17);
					sBITC21 = rs.getString(CAMPO18);
					sBISODE = rs.getString(CAMPO19);
					sBITC22 = rs.getString(CAMPO20);
					sBIRESO = rs.getString(CAMPO21);
					sCOTEXA = rs.getString(CAMPO22);
					sBITC09 = rs.getString(CAMPO23);
					sOBTEXC = rs.getString(CAMPO24);
					sOBDEER = rs.getString(CAMPO25);
					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO1+":|"+sMovimientoImpuestoRecursoID+"|");

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR MovimientoImpuestoRecursoID:|"+sMovimientoImpuestoRecursoID+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return new MovimientoImpuestoRecurso(sCODTRN, sCOTDOR, sIDPROV, sCOACCI, sCOENGP,
				sCOACES, sNURCAT, sCOGRUC, sCOTACA, sCOSBAC, sBITC18, sFEPRRE,
				sBITC19, sFERERE, sBITC20, sFEDEIN, sBITC21, sBISODE, sBITC22,
				sBIRESO, sCOTEXA, sBITC09, sOBTEXC, sOBDEER);
	}

	public static String getMovimientoImpuestoRecursoID(MovimientoImpuestoRecurso impuesto)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sMovimientoImpuestoRecursoID = "";

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					+ CAMPO1 + 
					"  FROM " + TABLA + 
						" WHERE ("
					       + CAMPO2  +" = '" + impuesto.getCODTRN() + "' AND "
					       + CAMPO3  +" = '" + impuesto.getCOTDOR() + "' AND "
					       + CAMPO4  +" = '" + impuesto.getIDPROV() + "' AND "
					       + CAMPO5  +" = '" + impuesto.getCOACCI() + "' AND "
					       + CAMPO6  +" = '" + impuesto.getCOENGP() + "' AND "
					       + CAMPO7  +" = '" + impuesto.getCOACES() + "' AND "
					       + CAMPO8  +" = '" + impuesto.getNURCAT() + "' AND "
					       + CAMPO9  +" = '" + impuesto.getCOGRUC() + "' AND "
					       + CAMPO10 +" = '" + impuesto.getCOTACA() + "' AND "
					       + CAMPO11 +" = '" + impuesto.getCOSBAC() + "' AND "
					       + CAMPO12 +" = '" + impuesto.getBITC18() + "' AND "
					       + CAMPO13 +" = '" + impuesto.getFEPRRE() + "' AND "
					       + CAMPO14 +" = '" + impuesto.getBITC19() + "' AND "
					       + CAMPO15 +" = '" + impuesto.getFERERE() + "' AND "
					       + CAMPO16 +" = '" + impuesto.getBITC20() + "' AND "
					       + CAMPO17 +" = '" + impuesto.getFEDEIN() + "' AND "
					       + CAMPO18 +" = '" + impuesto.getBITC21() + "' AND "
					       + CAMPO19 +" = '" + impuesto.getBISODE() + "' AND "
					       + CAMPO20 +" = '" + impuesto.getBITC22() + "' AND "
					       + CAMPO21 +" = '" + impuesto.getBIRESO() + "' AND "
					       + CAMPO22 +" = '" + impuesto.getCOTEXA() + "' AND "
					       + CAMPO23 +" = '" + impuesto.getBITC09() + "' AND "
					       + CAMPO24 +" = '" + impuesto.getOBTEXC() + "' AND "
					       + CAMPO25 +" = '" + impuesto.getOBDEER() + "' )"); 

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sMovimientoImpuestoRecursoID = rs.getString(CAMPO1);
					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO1+":|"+sMovimientoImpuestoRecursoID+"|");
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR COACES:|"+impuesto.getCOACES()+"|");
			logger.error("ERROR NURCAT:|"+impuesto.getNURCAT()+"|");
			logger.error("ERROR COSBAC:|"+impuesto.getCOSBAC()+"|");


			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return sMovimientoImpuestoRecursoID;
	}
	
	public static boolean existeMovimientoImpuestoRecurso(String sMovimientoImpuestoID)
	{
		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		Connection conn = null;
		
		boolean found = false;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " 
					+ CAMPO1 + 
					" FROM " 
					+ TABLA + 
					" WHERE " + CAMPO1 + " = '" + sMovimientoImpuestoID + "'");
			
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
			logger.error("ERROR sMovimientoImpuestoID:|"+sMovimientoImpuestoID+"|");

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
