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
import com.provisiones.dal.qm.movimientos.QMMovimientosReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.types.errores.ErrorReferenciaTabla;
import com.provisiones.types.errores.ErrorTabla;

public class QMListaErroresReferencias 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaErroresReferencias.class.getName());

	static String TABLA = "pp001_lista_errores_referencias_multi";

	static String CAMPO1  = "cod_movimiento";
	static String CAMPO2  = "cod_cotdor";

	public static boolean addErrorReferencia(String sCodMovimiento, String sCodCOTDOR)
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{

			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + TABLA + " ("
					   + CAMPO1  + "," 
				       + CAMPO2  +             
				       ") VALUES ('"
				       + sCodMovimiento + "','" 
				       + sCodCOTDOR +  "' )");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");
			logger.error("ERROR COTDOR:|"+sCodCOTDOR+"|");
			
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

	public static boolean delErrorReferencia(String sCodMovimiento, String sCodCOTDOR)
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
					" WHERE " +
					"(" + CAMPO1 + " = '" + sCodMovimiento	+ "' AND "
						+ CAMPO2 + " = '" + sCodCOTDOR	+ "')");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");
			logger.error("ERROR COTDOR:|"+sCodCOTDOR+"|");

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


			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM " + TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '" + sMovimiento + "'");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					liNumero = rs.getLong("COUNT(*)");
					
					logger.debug("Encontrado el registro!");

					logger.debug( "Numero de registros:|"+liNumero+"|");


				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Movimiento:|"+sMovimiento+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		ConnectionManager.CloseDBConnection(conn);
		return liNumero;
	}
	
	public static ArrayList<ErrorReferenciaTabla> buscaReferenciasConError(ErrorReferenciaTabla filtro)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String COACES = "";
		String NURCAT = "";

		String MOVIMIENTO = "";
		String ERRORES = "";
		
		ArrayList<ErrorReferenciaTabla> result = new ArrayList<ErrorReferenciaTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					
					   + QMMovimientosReferencias.CAMPO1 + ","
					   + QMMovimientosReferencias.CAMPO7 + ","
					   + QMMovimientosReferencias.CAMPO8 + 
					   "  FROM " + QMMovimientosReferencias.TABLA + 
					   " WHERE ( "
					   + QMMovimientosReferencias.CAMPO7 +" LIKE '%"+ filtro.getCOACES() +"%' AND "
					   + QMMovimientosReferencias.CAMPO8 +" LIKE '%"+ filtro.getNURCAT() +"%' AND "
					   + QMMovimientosReferencias.CAMPO1 +" IN (SELECT DISTINCT "
					   +  CAMPO1 + 
					   "  FROM " + TABLA + "))");
					   

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					COACES = rs.getString(QMMovimientosReferencias.CAMPO7);
					NURCAT = rs.getString(QMMovimientosReferencias.CAMPO8);
					MOVIMIENTO = rs.getString(QMMovimientosReferencias.CAMPO1);
					ERRORES = Long.toString(buscaCantidadErrores(MOVIMIENTO));
					
					ErrorReferenciaTabla errorencontrado = new ErrorReferenciaTabla(COACES, NURCAT, MOVIMIENTO, ERRORES);
					
					result.add(errorencontrado);
					
					logger.debug("Encontrado el registro!");

					logger.debug(QMMovimientosReferencias.CAMPO1+":|"+MOVIMIENTO+"|");
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
					   CAMPO2 + 
					   " FROM " + TABLA + 
					   " WHERE "
					   + CAMPO1 +" = '"+ sMovimiento +"'");
			

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					sCodError = rs.getString(CAMPO2);
					sDescripcion = QMCodigosControl.getDesCampo(QMCodigosControl.TCOTDORE3, QMCodigosControl.ICOTDORE3, sCodError);

					
					ErrorTabla errorencontrado = new ErrorTabla(sCodError, sDescripcion);
					
					result.add(errorencontrado);
					
					logger.debug("Encontrado el registro!");

					logger.debug(sCodError+":|"+sDescripcion+"|");
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Movimiento:|"+sMovimiento+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
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
