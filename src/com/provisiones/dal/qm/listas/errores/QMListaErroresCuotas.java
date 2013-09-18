package com.provisiones.dal.qm.listas.errores;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;

public class QMListaErroresCuotas 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaErroresCuotas.class.getName());
	
	static String sTable = "lista_errores_cuotas_multi";

	static String sField1  = "cod_movimiento";
	static String sField2  = "cod_cotdor";

	public static boolean addErrorCuota(String sCodMovimiento, String sCodCOTDOR)
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

	public static boolean delErrorCuota(String sCodMovimiento, String sCodCOTDOR)
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
}
