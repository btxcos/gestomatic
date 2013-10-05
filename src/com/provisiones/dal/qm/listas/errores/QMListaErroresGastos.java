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
import com.provisiones.types.ErrorGastoTabla;
import com.provisiones.types.ErrorTabla;

public class QMListaErroresGastos 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaErroresGastos.class.getName());
	
	static String sTable = "lista_errores_gastos_multi";

	static String sField1  = "cod_movimiento";
	static String sField2  = "cod_coterr";

	public static boolean addErrorGasto(String sCodMovimiento, String sCodCOTDOR)
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

	public static boolean delErrorGasto(String sCodMovimiento, String sCodCOTDOR)
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

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					
					   + QMMovimientosGastos.sField1 + ","
					   + QMMovimientosGastos.sField2 + ","
					   + QMMovimientosGastos.sField3 + ","
					   + QMMovimientosGastos.sField4 + ","
					   + QMMovimientosGastos.sField5 + ","
					   + QMMovimientosGastos.sField7 + ","
					   + QMMovimientosGastos.sField16 + ","
					   + QMMovimientosGastos.sField17 + 

					   "  FROM " + QMMovimientosGastos.sTable + 
					   " WHERE ( "
					   + QMMovimientosGastos.sField2 +" LIKE '%"+ filtro.getCOACES() +"%' AND "
					   + QMMovimientosGastos.sField3 +" LIKE '%"+ filtro.getCOGRUG() +"%' AND "
					   + QMMovimientosGastos.sField4 +" LIKE '%"+ filtro.getCOTPGA() +"%' AND "
					   + QMMovimientosGastos.sField5 +" LIKE '%"+ filtro.getCOSBGA() +"%' AND "
					   + QMMovimientosGastos.sField7 +" LIKE '%"+ filtro.getFEDEVE() +"%' AND "
					   + QMMovimientosGastos.sField16 +" LIKE '%"+ filtro.getIMNGAS() +"%' AND "
					   + QMMovimientosGastos.sField1 +" IN (SELECT DISTINCT "
					   +  sField1 + 
					   "  FROM " + sTable + "))");
					   

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					COACES = rs.getString(QMMovimientosGastos.sField2);
					COGRUG = rs.getString(QMMovimientosGastos.sField3);
					COTPGA = rs.getString(QMMovimientosGastos.sField4);
					COSBGA = rs.getString(QMMovimientosGastos.sField5);
					DCOSBGA = QMCodigosControl.getDesCOSBGA(COGRUG,COTPGA,COSBGA);
					IMNGAS = rs.getString(QMMovimientosGastos.sField17)+Utils.recuperaImporte(false, rs.getString(QMMovimientosGastos.sField16));
					FEDEVE = Utils.recuperaFecha(rs.getString(QMMovimientosGastos.sField7));
					logger.debug("{}:|{}|",QMMovimientosGastos.sField7,FEDEVE);
					MOVIMIENTO = rs.getString(QMMovimientosGastos.sField1);
					ERRORES = Long.toString(buscaCantidadErrores(MOVIMIENTO));
					
					ErrorGastoTabla errorencontrado = new ErrorGastoTabla(COACES, COGRUG, COTPGA, COSBGA, DCOSBGA, IMNGAS, FEDEVE,MOVIMIENTO, ERRORES);
					
					result.add(errorencontrado);
					
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",QMMovimientosGastos.sField1,MOVIMIENTO);
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
					sDescripcion = QMCodigosControl.getDesCampo(QMCodigosControl.TCOTERR, QMCodigosControl.ICOTERR, sCodError);

					
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
