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
import com.provisiones.dal.qm.QMCodigosControl;
import com.provisiones.dal.qm.movimientos.QMMovimientosImpuestos;
import com.provisiones.misc.Utils;

import com.provisiones.types.ErrorImpuestoTabla;
import com.provisiones.types.ErrorTabla;

public class QMListaErroresImpuestos 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaErroresImpuestos.class.getName());

	static String sTable = "lista_errores_impuestos_multi";

	static String sField1  = "cod_movimiento";
	static String sField2  = "cod_cotdor";

	public static boolean addErrorImpuesto(String sCodMovimiento, String sCodCOTDOR)
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

	public static boolean delErrorImpuesto(String sCodMovimiento, String sCodCOTDOR)
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
	
	public static ArrayList<ErrorImpuestoTabla> buscaImpuestosConError(ErrorImpuestoTabla filtro)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String COACES = "";
		String NURCAT = "";
		String COSBAC = "";
		String DCOSBAC = "";
		
		String MOVIMIENTO = "";
		String ERRORES = "";
		
		ArrayList<ErrorImpuestoTabla> result = new ArrayList<ErrorImpuestoTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					
					   + QMMovimientosImpuestos.sField1 + ","
					   + QMMovimientosImpuestos.sField7 + ","
					   + QMMovimientosImpuestos.sField8 + ","
					   + QMMovimientosImpuestos.sField11 + 

					   "  FROM " + QMMovimientosImpuestos.sTable + 
					   " WHERE ( "
					   + QMMovimientosImpuestos.sField7 +" LIKE '%"+ filtro.getCOACES() +"%' AND "
					   + QMMovimientosImpuestos.sField8 +" LIKE '%"+ filtro.getNURCAT() +"%' AND "
					   + QMMovimientosImpuestos.sField11 +" LIKE '%"+ filtro.getCOSBAC() +"%' AND "
					   + QMMovimientosImpuestos.sField1 +" IN (SELECT DISTINCT "
					   +  sField1 + 
					   "  FROM " + sTable + "))");
					   

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					
					COACES = rs.getString(QMMovimientosImpuestos.sField7);
					NURCAT = rs.getString(QMMovimientosImpuestos.sField8);
					COSBAC = rs.getString(QMMovimientosImpuestos.sField11);
					DCOSBAC = QMCodigosControl.getDesCampo(QMCodigosControl.TCOSBGAT22,QMCodigosControl.ICOSBGAT22,COSBAC);
					MOVIMIENTO = rs.getString(QMMovimientosImpuestos.sField1);
					ERRORES = Long.toString(buscaCantidadErrores(MOVIMIENTO));
					
					ErrorImpuestoTabla errorencontrado = new ErrorImpuestoTabla(COACES, NURCAT, COSBAC, DCOSBAC, MOVIMIENTO, ERRORES);
					
					result.add(errorencontrado);
					
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",QMMovimientosImpuestos.sField1,MOVIMIENTO);
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
					sDescripcion = QMCodigosControl.getDesCampo(QMCodigosControl.TCOTDORE4, QMCodigosControl.ICOTDORE4, sCodError);

					
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
