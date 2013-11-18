package com.provisiones.dal.qm.listas.errores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.qm.QMCodigosControl;
import com.provisiones.dal.qm.movimientos.QMMovimientosImpuestos;
import com.provisiones.misc.Utils;

import com.provisiones.types.errores.ErrorImpuestoTabla;
import com.provisiones.types.errores.ErrorTabla;

public class QMListaErroresImpuestos 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaErroresImpuestos.class.getName());

	static String TABLA = "pp001_lista_errores_impuestos_multi";

	static String CAMPO1  = "cod_movimiento";
	static String CAMPO2  = "cod_cotdor";

	public static boolean addErrorImpuesto(Connection conexion, String sCodMovimiento, String sCodCOTDOR)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
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
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con exito!");
				
				bSalida = true;
			} 
			catch (SQLException ex) 
			{
				bSalida = false;
				
				logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");
				logger.error("ERROR COTDOR:|"+sCodCOTDOR+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean delErrorImpuesto(Connection conexion, String sCodMovimiento, String sCodCOTDOR)
	{
		boolean bSalida = true;
		
		if (conexion != null)
		{
			Statement stmt = null;

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
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con exito!");
				
				bSalida = true;
			} 
			catch (SQLException ex) 
			{
				bSalida = false;
				
				logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");
				logger.error("ERROR COTDOR:|"+sCodCOTDOR+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static long buscaCantidadErrores(Connection conexion, String sMovimiento)
	{
		long liNumero = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT COUNT(*) FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '" + sMovimiento + "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");
				
				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						liNumero = rs.getLong("COUNT(*)");
						
						logger.debug("Encontrado el registro!");

						logger.debug( "Numero de registros:|"+liNumero+"|");
					}
				}
				if (bEncontrado == false) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				liNumero = 0;
				
				logger.error("ERROR Movimiento:|"+sMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return liNumero;
	}
	
	public static ArrayList<ErrorImpuestoTabla> buscaImpuestosConError(Connection conexion, ErrorImpuestoTabla filtro)
	{
		ArrayList<ErrorImpuestoTabla> resultado = new ArrayList<ErrorImpuestoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String COACES = "";
			String NURCAT = "";
			String COSBAC = "";
			String DCOSBAC = "";
			
			String MOVIMIENTO = "";
			String ERRORES = "";
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
						
						   + QMMovimientosImpuestos.CAMPO1 + ","
						   + QMMovimientosImpuestos.CAMPO7 + ","
						   + QMMovimientosImpuestos.CAMPO8 + ","
						   + QMMovimientosImpuestos.CAMPO11 + 

						   "  FROM " 
						   + QMMovimientosImpuestos.TABLA + 
						   " WHERE ( "
						   + QMMovimientosImpuestos.CAMPO7 +" LIKE '%"+ filtro.getCOACES() +"%' AND "
						   + QMMovimientosImpuestos.CAMPO8 +" LIKE '%"+ filtro.getNURCAT() +"%' AND "
						   + QMMovimientosImpuestos.CAMPO11 +" LIKE '%"+ filtro.getCOSBAC() +"%' AND "
						   
						   + QMMovimientosImpuestos.CAMPO1 +" IN (SELECT DISTINCT "
						   +  CAMPO1 + 
						   "  FROM " 
						   + TABLA + 
						   "))";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;
						
						COACES = rs.getString(QMMovimientosImpuestos.CAMPO7);
						NURCAT = rs.getString(QMMovimientosImpuestos.CAMPO8);
						COSBAC = rs.getString(QMMovimientosImpuestos.CAMPO11);
						DCOSBAC = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TCOSBGAT22,QMCodigosControl.ICOSBGAT22,COSBAC);
						MOVIMIENTO = rs.getString(QMMovimientosImpuestos.CAMPO1);
						ERRORES = Long.toString(buscaCantidadErrores(conexion,MOVIMIENTO));
						
						ErrorImpuestoTabla errorencontrado = new ErrorImpuestoTabla(COACES, NURCAT, COSBAC, DCOSBAC, MOVIMIENTO, ERRORES);
						
						resultado.add(errorencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug(QMMovimientosImpuestos.CAMPO1+":|"+MOVIMIENTO+"|");
					}
				}
				if (bEncontrado == false) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ErrorImpuestoTabla>();
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return resultado;
	}
	
	public static ArrayList<ErrorTabla> buscaErrores(Connection conexion, String sMovimiento)
	{
		ArrayList<ErrorTabla> resultado = new ArrayList<ErrorTabla>();
		
		Statement stmt = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		boolean bEncontrado = false;
		
		String sCodError = "";
		String sDescripcion = "";
		
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
			stmt = conexion.createStatement();

			pstmt = conexion.prepareStatement(sQuery);
			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{
				while (rs.next()) 
				{
					bEncontrado = true;
					
					sCodError = rs.getString(CAMPO2);
					sDescripcion = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TCOTDORE4, QMCodigosControl.ICOTDORE4, sCodError);
					
					ErrorTabla errorencontrado = new ErrorTabla(sCodError, sDescripcion);
					
					resultado.add(errorencontrado);
					
					logger.debug("Encontrado el registro!");

					logger.debug(sCodError+":|"+sDescripcion+"|");
				}
			}
			if (bEncontrado == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			resultado = new ArrayList<ErrorTabla>();
			
			logger.error("ERROR Movimiento:|"+sMovimiento+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		return resultado;
	}
}
