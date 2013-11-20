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
import com.provisiones.dal.qm.movimientos.QMMovimientosReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.types.errores.ErrorReferenciaTabla;
import com.provisiones.types.errores.ErrorTabla;

public class QMListaErroresReferencias 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaErroresReferencias.class.getName());

	public static final String TABLA = "pp001_lista_errores_referencias_multi";

	public static final String CAMPO1  = "cod_movimiento";
	public static final String CAMPO2  = "cod_cotdor";

	public static boolean addErrorReferencia(Connection conexion, String sCodMovimiento, String sCodCOTDOR)
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

	public static boolean delErrorReferencia(Connection conexion, String sCodMovimiento, String sCodCOTDOR)
	{
		boolean bSalida = false;

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
				if (!bEncontrado) 
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
	
	public static ArrayList<ErrorReferenciaTabla> buscaReferenciasConError(Connection conexion, ErrorReferenciaTabla filtro)
	{
		ArrayList<ErrorReferenciaTabla> resultado = new ArrayList<ErrorReferenciaTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String COACES = "";
			String NURCAT = "";

			String MOVIMIENTO = "";
			String ERRORES = "";
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
						
						   + QMMovimientosReferencias.CAMPO1 + ","
						   + QMMovimientosReferencias.CAMPO7 + ","
						   + QMMovimientosReferencias.CAMPO8 + 
						   " FROM " 
						   + QMMovimientosReferencias.TABLA + 
						   " WHERE ( "
						   + QMMovimientosReferencias.CAMPO7 +" LIKE '%"+ filtro.getCOACES() +"%' AND "
						   + QMMovimientosReferencias.CAMPO8 +" LIKE '%"+ filtro.getNURCAT() +"%' AND "
						   + QMMovimientosReferencias.CAMPO1 +" IN (SELECT DISTINCT "
						   +  CAMPO1 + 
						   " FROM " 
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
						
						COACES = rs.getString(QMMovimientosReferencias.CAMPO7);
						NURCAT = rs.getString(QMMovimientosReferencias.CAMPO8);
						MOVIMIENTO = rs.getString(QMMovimientosReferencias.CAMPO1);
						ERRORES = Long.toString(buscaCantidadErrores(conexion,MOVIMIENTO));
						
						ErrorReferenciaTabla errorencontrado = new ErrorReferenciaTabla(COACES, NURCAT, MOVIMIENTO, ERRORES);
						
						resultado.add(errorencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug(QMMovimientosReferencias.CAMPO1+":|"+MOVIMIENTO+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ErrorReferenciaTabla>();
				
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

		if (conexion != null)
		{
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
						sDescripcion = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TCOTDORE3, QMCodigosControl.ICOTDORE3, sCodError);
						
						ErrorTabla errorencontrado = new ErrorTabla(sCodError, sDescripcion);
						
						resultado.add(errorencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug(sCodError+":|"+sDescripcion+"|");
					}
				}
				if (!bEncontrado) 
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
		}

		return resultado;
	}
}
