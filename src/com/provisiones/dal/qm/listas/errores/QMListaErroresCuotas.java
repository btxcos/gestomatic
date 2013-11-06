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
import com.provisiones.dal.qm.movimientos.QMMovimientosCuotas;
import com.provisiones.misc.Utils;

import com.provisiones.types.errores.ErrorCuotaTabla;
import com.provisiones.types.errores.ErrorTabla;

public class QMListaErroresCuotas 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaErroresCuotas.class.getName());
	
	static String TABLA = "pp001_lista_errores_cuotas_multi";

	static String CAMPO1  = "cod_movimiento";
	static String CAMPO2  = "cod_cotdor";

	public static boolean addErrorCuota(String sCodMovimiento, String sCodCOTDOR)
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "INSERT INTO " 
				+ TABLA + 
				" ("
				+ CAMPO1  + "," 
			    + CAMPO2  +             
			    ") VALUES ('"
			    + sCodMovimiento + "','" 
			    + sCodCOTDOR + 
			    "')";
		
		logger.debug(sQuery);

		try 
		{

			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			bSalida = false;

			logger.error("ERROR: Movimiento:|"+sCodMovimiento+"|");
			logger.error("ERROR: COTDOR:|"+sCodCOTDOR+"|");
			
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static boolean delErrorCuota(String sCodMovimiento, String sCodCOTDOR)
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");

		
		String sQuery = "DELETE FROM " 
				+ TABLA + 
				" WHERE (" 
				+ CAMPO1 + " = '" + sCodMovimiento	+ "' AND "
				+ CAMPO2 + " = '" + sCodCOTDOR	+ 
				"')";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: Movimiento:|"+sCodMovimiento+"|");
			logger.error("ERROR: COTDOR:|"+sCodCOTDOR+"|");

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
	
	public static long buscaCantidadErrores(String sMovimiento)
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		long liNumero = 0;

		Connection conn = null;

		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT COUNT(*) FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO1 + " = '" + sMovimiento + "'";
		
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
			logger.error("ERROR: Movimiento:|"+sMovimiento+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		//ConnectionManager.CloseDBConnection(conn);
		return liNumero;
	}
	
	public static ArrayList<ErrorCuotaTabla> buscaCuotasConError(ErrorCuotaTabla filtro)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String COACES = "";
		String COCLDO = "";
		String DCOCLDO = "";
		String NUDCOM = "";
		String COSBAC = "";
		String DCOSBAC = "";
		
		String MOVIMIENTO = "";
		String ERRORES = "";
		
		ArrayList<ErrorCuotaTabla> result = new ArrayList<ErrorCuotaTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
					
					   + QMMovimientosCuotas.CAMPO1 + ","
					   + QMMovimientosCuotas.CAMPO6 + ","
					   + QMMovimientosCuotas.CAMPO7 + ","
					   + QMMovimientosCuotas.CAMPO9 + ","
					   + QMMovimientosCuotas.CAMPO12 + 

					   "  FROM " 
					   + QMMovimientosCuotas.TABLA + 
					   " WHERE ( "
					   + QMMovimientosCuotas.CAMPO6 +" LIKE '%"+ filtro.getCOCLDO() +"%' AND "
					   + QMMovimientosCuotas.CAMPO7 +" LIKE '%"+ filtro.getNUDCOM() +"%' AND "
					   + QMMovimientosCuotas.CAMPO9 +" LIKE '%"+ filtro.getCOACES() +"%' AND "
					   + QMMovimientosCuotas.CAMPO12 +" LIKE '%"+ filtro.getCOSBAC() +"%' AND "
					   + QMMovimientosCuotas.CAMPO1 +" IN (SELECT DISTINCT "
					   +  CAMPO1 + 
					   "  FROM " 
					   + TABLA + 
					   "))";
		
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
					
					COACES = rs.getString(QMMovimientosCuotas.CAMPO9);
					COCLDO = rs.getString(QMMovimientosCuotas.CAMPO6);
					DCOCLDO = QMCodigosControl.getDesCampo(QMCodigosControl.TCOCLDO, QMCodigosControl.ICOCLDO, COCLDO);
					NUDCOM = rs.getString(QMMovimientosCuotas.CAMPO7);
					COSBAC = rs.getString(QMMovimientosCuotas.CAMPO12);
					DCOSBAC = QMCodigosControl.getDesCampo(QMCodigosControl.TCOSBGAT22,QMCodigosControl.ICOSBGAT22,COSBAC);
					MOVIMIENTO = rs.getString(QMMovimientosCuotas.CAMPO1);
					ERRORES = Long.toString(buscaCantidadErrores(MOVIMIENTO));
					
					ErrorCuotaTabla errorencontrado = new ErrorCuotaTabla(COACES, COCLDO, DCOCLDO, NUDCOM, COSBAC, DCOSBAC,MOVIMIENTO, ERRORES);
					
					result.add(errorencontrado);
					
					logger.debug("Encontrado el registro!");

					logger.debug(QMMovimientosCuotas.CAMPO1+":|"+MOVIMIENTO+"|");
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
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO2 + 
				" FROM " 
				+ TABLA + 
				" WHERE "
				+ CAMPO1 +" = '"+ sMovimiento +"'";
		
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
					
					sCodError = rs.getString(CAMPO2);
					sDescripcion = QMCodigosControl.getDesCampo(QMCodigosControl.TCOTDORE2, QMCodigosControl.ICOTDORE2, sCodError);

					
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
			logger.error("ERROR: Movimiento:|"+sMovimiento);

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
