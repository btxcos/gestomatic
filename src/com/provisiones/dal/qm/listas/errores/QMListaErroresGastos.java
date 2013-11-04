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
import com.provisiones.dal.qm.movimientos.QMMovimientosGastos;
import com.provisiones.misc.Utils;
import com.provisiones.types.errores.ErrorGastoTabla;
import com.provisiones.types.errores.ErrorTabla;

public class QMListaErroresGastos 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaErroresGastos.class.getName());
	
	static String TABLA = "pp001_lista_errores_gastos_multi";

	static String CAMPO1  = "cod_movimiento";
	static String CAMPO2  = "cod_coterr";

	public static boolean addErrorGasto(String sCodMovimiento, String sCodCOTDOR)
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		conn = ConnectionManager.OpenDBConnection();
		
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

	public static boolean delErrorGasto(String sCodMovimiento, String sCodCOTDOR)
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		conn = ConnectionManager.OpenDBConnection();
		
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
		
		String sQuery = "SELECT COUNT(*) FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO1 + " = '" + sMovimiento + "'";

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
			logger.error("ERROR sMovimiento:|"+sMovimiento+"|");

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
	
	public static ArrayList<ErrorGastoTabla> buscaGastosConError(ErrorGastoTabla filtro)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String COACES = "";
		String COGRUG = "";
		String COTPGA = "";
		String COSBGA = "";
		String DCOSBGA = "";
		String IMNGAS = "";
		String FEDEVE = "";
		
		String MOVIMIENTO = "";
		String ERRORES = "";
		
		ArrayList<ErrorGastoTabla> result = new ArrayList<ErrorGastoTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
					
					   + QMMovimientosGastos.CAMPO1 + ","
					   + QMMovimientosGastos.CAMPO2 + ","
					   + QMMovimientosGastos.CAMPO3 + ","
					   + QMMovimientosGastos.CAMPO4 + ","
					   + QMMovimientosGastos.CAMPO5 + ","
					   + QMMovimientosGastos.CAMPO7 + ","
					   + QMMovimientosGastos.CAMPO16 + ","
					   + QMMovimientosGastos.CAMPO17 + 

					   "  FROM " 
					   + QMMovimientosGastos.TABLA + 
					   " WHERE ( "
					   + QMMovimientosGastos.CAMPO2 +" LIKE '%"+ filtro.getCOACES() +"%' AND "
					   + QMMovimientosGastos.CAMPO3 +" LIKE '%"+ filtro.getCOGRUG() +"%' AND "
					   + QMMovimientosGastos.CAMPO4 +" LIKE '%"+ filtro.getCOTPGA() +"%' AND "
					   + QMMovimientosGastos.CAMPO5 +" LIKE '%"+ filtro.getCOSBGA() +"%' AND "
					   + QMMovimientosGastos.CAMPO7 +" LIKE '%"+ filtro.getFEDEVE() +"%' AND "
					   + QMMovimientosGastos.CAMPO16 +" LIKE '%"+ filtro.getIMNGAS() +"%' AND "
					   + QMMovimientosGastos.CAMPO1 +" IN (SELECT DISTINCT "
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
					
					COACES = rs.getString(QMMovimientosGastos.CAMPO2);
					COGRUG = rs.getString(QMMovimientosGastos.CAMPO3);
					COTPGA = rs.getString(QMMovimientosGastos.CAMPO4);
					COSBGA = rs.getString(QMMovimientosGastos.CAMPO5);
					DCOSBGA = QMCodigosControl.getDesCOSBGA(COGRUG,COTPGA,COSBGA);
					IMNGAS = Utils.recuperaImporte(rs.getString(QMMovimientosGastos.CAMPO17).equals("-"),rs.getString(QMMovimientosGastos.CAMPO16));
					FEDEVE = Utils.recuperaFecha(rs.getString(QMMovimientosGastos.CAMPO7));
					logger.debug(QMMovimientosGastos.CAMPO7+":|"+FEDEVE+"|");
					MOVIMIENTO = rs.getString(QMMovimientosGastos.CAMPO1);
					ERRORES = Long.toString(buscaCantidadErrores(MOVIMIENTO));
					
					ErrorGastoTabla errorencontrado = new ErrorGastoTabla(COACES, COGRUG, COTPGA, COSBGA, DCOSBGA, IMNGAS, FEDEVE,MOVIMIENTO, ERRORES);
					
					result.add(errorencontrado);
					
					logger.debug("Encontrado el registro!");

					logger.debug(QMMovimientosGastos.CAMPO1+":|"+MOVIMIENTO+"|");
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
					sDescripcion = QMCodigosControl.getDesCampo(QMCodigosControl.TCOTERR, QMCodigosControl.ICOTERR, sCodError);

					
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
