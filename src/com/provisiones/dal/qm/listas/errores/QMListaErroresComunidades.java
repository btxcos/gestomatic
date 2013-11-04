package com.provisiones.dal.qm.listas.errores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.provisiones.dal.ConnectionManager;

import com.provisiones.dal.qm.QMCodigosControl;
import com.provisiones.dal.qm.movimientos.QMMovimientosComunidades;
import com.provisiones.misc.Utils;
import com.provisiones.types.errores.ErrorComunidadTabla;
import com.provisiones.types.errores.ErrorTabla;


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
	
	public static long buscaCantidadErrores(String sMovimiento)
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		long liNumero = 0;

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM " + sTable + 
					" WHERE " 
					+ sField1 + " = '" + sMovimiento + "'");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					liNumero = rs.getLong("COUNT(*)");
					
					logger.debug("Encontrado el registro!");

					logger.debug( "Numero de registros:|{}|",liNumero);


				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: sMovimiento:|{}|",sMovimiento);

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
		return liNumero;
	}
	
	public static ArrayList<ErrorComunidadTabla> buscaComunidadesConError(ErrorComunidadTabla filtro)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String COCLDO = "";
		String DCOCLDO = "";
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
					
					   + QMMovimientosComunidades.sField1 + ","
					   + QMMovimientosComunidades.sField7 + ","
					   + QMMovimientosComunidades.sField8 + ","
					   + QMMovimientosComunidades.sField12 + 

					   "  FROM " + QMMovimientosComunidades.sTable + 
					   " WHERE ( "
					   + QMMovimientosComunidades.sField7 +" LIKE '%"+ filtro.getCOCLDO() +"%' AND "
					   + QMMovimientosComunidades.sField8 +" LIKE '%"+ filtro.getNUDCOM() +"%' AND "
					   + QMMovimientosComunidades.sField7 +" LIKE '%"+ filtro.getNOMCOC() +"%' AND "
					   + QMMovimientosComunidades.sField1 +" IN (SELECT DISTINCT "
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
					DCOCLDO = QMCodigosControl.getDesCampo(QMCodigosControl.TCOCLDO, QMCodigosControl.ICOCLDO, COCLDO);
					NUDCOM = rs.getString(QMMovimientosComunidades.sField8);
					NOMCOC = rs.getString(QMMovimientosComunidades.sField12);
					MOVIMIENTO = rs.getString(QMMovimientosComunidades.sField1);
					ERRORES = Long.toString(buscaCantidadErrores(MOVIMIENTO));
					
					ErrorComunidadTabla errorencontrado = new ErrorComunidadTabla(COCLDO, DCOCLDO, NUDCOM, NOMCOC, MOVIMIENTO, ERRORES);
					
					result.add(errorencontrado);
					
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",QMMovimientosComunidades.sField1,MOVIMIENTO);
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
	
	public static ArrayList<ErrorComunidadTabla> buscaComunidadesActivoConError(String sCOACES)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String COCLDO = "";
		String DCOCLDO = "";
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
					
					   + QMMovimientosComunidades.sField1 + ","
					   + QMMovimientosComunidades.sField7 + ","
					   + QMMovimientosComunidades.sField8 + ","
					   + QMMovimientosComunidades.sField12 + 

					   "  FROM " + QMMovimientosComunidades.sTable + 
					   " WHERE ( "
					   + QMMovimientosComunidades.sField10 +" LIKE '%"+ sCOACES +"%' AND "
					   + QMMovimientosComunidades.sField1 +" IN (SELECT DISTINCT "
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
					DCOCLDO = QMCodigosControl.getDesCampo(QMCodigosControl.TCOCLDO, QMCodigosControl.ICOCLDO, COCLDO);
					NUDCOM = rs.getString(QMMovimientosComunidades.sField8);
					NOMCOC = rs.getString(QMMovimientosComunidades.sField12);
					MOVIMIENTO = rs.getString(QMMovimientosComunidades.sField1);
					ERRORES = Long.toString(buscaCantidadErrores(MOVIMIENTO));
					
					ErrorComunidadTabla errorencontrado = new ErrorComunidadTabla(COCLDO, DCOCLDO, NUDCOM, NOMCOC, MOVIMIENTO, ERRORES);
					
					result.add(errorencontrado);
					
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",QMMovimientosComunidades.sField1,MOVIMIENTO);
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: COACES:|{}|",sCOACES);
			
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
	
	public static ArrayList<ErrorTabla> buscaErrores(String sMovimiento)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sCodError = "";
		String sDescripcion = "";
		
		ArrayList<ErrorTabla> result = new ArrayList<ErrorTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " +
					   sField2 + 
					   " FROM " + sTable + 
					   " WHERE "
					   + sField1 +" = '"+ sMovimiento +"'");
			

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					sCodError = rs.getString(sField2);
					sDescripcion = QMCodigosControl.getDesCampo(QMCodigosControl.TCOTDORE1, QMCodigosControl.ICOTDORE1, sCodError);

					
					ErrorTabla errorencontrado = new ErrorTabla(sCodError, sDescripcion);
					
					result.add(errorencontrado);
					
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",sCodError,sDescripcion);
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: sMovimiento:{}",sMovimiento);

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
