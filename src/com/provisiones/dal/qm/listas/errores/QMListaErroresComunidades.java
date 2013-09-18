package com.provisiones.dal.qm.listas.errores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.movimientos.QMMovimientosComunidades;
import com.provisiones.misc.Utils;
import com.provisiones.types.ErrorComunidadTabla;


public class QMListaErroresComunidades 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaErroresComunidades.class.getName());
	
	static String sTable = "lista_errores_comunidades_multi";

	static String sField1  = "cod_movimiento";
	static String sField2  = "cod_cotdor";

	public static boolean addErrorComunidad(String sCodMovimiento, String sCodCOTDOR)
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{

			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + sTable + " ("
					   + sField1  + "," 
				       + sField2  +             
				       ") VALUES ('"
				       + sCodMovimiento + "','" 
				       + sCodCOTDOR +  "' )");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: Movimiento:|{}|",sCodMovimiento);
			logger.error("ERROR: COTDOR:|{}|",sCodCOTDOR);
			
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

	public static boolean delErrorComunidad(String sCodMovimiento, String sCodCOTDOR)
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
					" WHERE " +
					"(" + sField1 + " = '" + sCodMovimiento	+ "' AND "
						+ sField2 + " = '" + sCodCOTDOR	+ "')");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: Movimiento:|{}|",sCodMovimiento);
			logger.error("ERROR: COTDOR:|{}|",sCodCOTDOR);

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
	
	public static ArrayList<ErrorComunidadTabla> buscaErrores()
	{
		Statement stmt = null;
		ResultSet rs = null;

		String COCLDO = "";
		String NUDCOM = "";
		String NOMCOC = "";
		String MOVIMIENTO = "";
		String ERRORES = "";
		
		ArrayList<ErrorComunidadTabla> result = new ArrayList<ErrorComunidadTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					   + QMMovimientosComunidades.sField7 + ","        
					   + QMMovimientosComunidades.sField8 + ","
					   + QMMovimientosComunidades.sField12 + 

					   "  FROM " + QMMovimientosComunidades.sTable + 
					   " WHERE "+ QMMovimientosComunidades.sField1 +" IN (SELECT "
					   +  sField1 + 
					   "  FROM " + sTable + "))");
			

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					COCLDO = rs.getString(QMMovimientosComunidades.sField7);
					NUDCOM = rs.getString(QMMovimientosComunidades.sField8);
					NOMCOC = rs.getString(QMMovimientosComunidades.sField12);
					MOVIMIENTO = rs.getString(QMMovimientosComunidades.sField1);
					ERRORES = "";
					
					ErrorComunidadTabla errorencontrado = new ErrorComunidadTabla(COCLDO, NUDCOM, NOMCOC, MOVIMIENTO, ERRORES);
					
					result.add(errorencontrado);
					
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",QMActivos.sField1,MOVIMIENTO);
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

/*	public static ArrayList<String> buscaErroresMovimientoComunidad(String sCodMovimiento)
	{//pendiente de coaces, de la tabla activos

		String sMethod = "buscaActivosConCuotas";
		
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
		
		ArrayList<ErrorComunidadTabla> result = new ArrayList<ErrorComunidadTabla>();
		

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
					   +  sField1 + 
					   " FROM " + sTable +
					   " WHERE " + sField2 + " IN (SELECT "
   					   + QMCuotas.sField1 + 
   					   " FROM " + QMCuotas.sTable +
   					   " WHERE " + QMCuotas.sField10 + " = '"+ ValoresDefecto.DEF_ALTA + "') AND " 
   					   + sField3 + " IN (SELECT "
   					   + QMCuotas.sField2 + 
   					   " FROM " + QMCuotas.sTable +
   					   " WHERE " + QMCuotas.sField10 + " = '"+ ValoresDefecto.DEF_ALTA + "')))";
		
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
					   +  sField1 + 
					   " FROM " + sTable +
					   " WHERE " + 
					   
					   sField2 + " IN (SELECT "
					   + QMCuotas.sField1 + 
   					   " FROM " + QMCuotas.sTable +
   					   " WHERE " + QMCuotas.sField10 + " = '"+ ValoresDefecto.DEF_ALTA + "') AND " 

   					   + sField3 + " IN (SELECT "
   					   + QMCuotas.sField2 + 
   					   " FROM " + QMCuotas.sTable +
   					   " WHERE " + QMCuotas.sField10 + " = '"+ ValoresDefecto.DEF_ALTA + "')))");

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

					logger.debug(QMActivos.sField1 + ":|{}|",sCOACES);
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontro la informacion.");
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

	}*/

}
