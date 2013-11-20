package com.provisiones.dal.qm.listas.errores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.provisiones.dal.qm.QMCodigosControl;
import com.provisiones.dal.qm.movimientos.QMMovimientosComunidades;
import com.provisiones.misc.Utils;
import com.provisiones.types.errores.ErrorComunidadTabla;
import com.provisiones.types.errores.ErrorTabla;


public class QMListaErroresComunidades 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaErroresComunidades.class.getName());
	
	static String TABLA = "pp001_lista_errores_comunidades_multi";

	static String CAMPO1  = "cod_movimiento";
	static String CAMPO2  = "cod_cotdor";

	public static boolean addErrorComunidad(Connection conexion, String sCodMovimiento, String sCodCOTDOR)
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
						+ sCodCOTDOR +  "' )";
			
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

	public static boolean delErrorComunidad(Connection conexion, String sCodMovimiento, String sCodCOTDOR)
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
				
				logger.error("ERROR sMovimiento:|"+sMovimiento+"|");

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
	
	public static ArrayList<ErrorComunidadTabla> buscaComunidadesConError(Connection conexion, ErrorComunidadTabla filtro)
	{
		ArrayList<ErrorComunidadTabla> resultado = new ArrayList<ErrorComunidadTabla>();
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String COCLDO = "";
			String DCOCLDO = "";
			String NUDCOM = "";
			String NOMCOC = "";
			String MOVIMIENTO = "";
			String ERRORES = "";
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
						
						   + QMMovimientosComunidades.CAMPO1 + ","
						   + QMMovimientosComunidades.CAMPO7 + ","
						   + QMMovimientosComunidades.CAMPO8 + ","
						   + QMMovimientosComunidades.CAMPO12 + 

						   " FROM " 
						   + QMMovimientosComunidades.TABLA + 
						   " WHERE ( "
						   + QMMovimientosComunidades.CAMPO7 +" LIKE '%"+ filtro.getCOCLDO() +"%' AND "
						   + QMMovimientosComunidades.CAMPO8 +" LIKE '%"+ filtro.getNUDCOM() +"%' AND "
						   + QMMovimientosComunidades.CAMPO7 +" LIKE '%"+ filtro.getNOMCOC() +"%' AND "
						   
						   + QMMovimientosComunidades.CAMPO1 +" IN (SELECT DISTINCT "
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
						
						COCLDO = rs.getString(QMMovimientosComunidades.CAMPO7);
						DCOCLDO = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TCOCLDO, QMCodigosControl.ICOCLDO, COCLDO);
						NUDCOM = rs.getString(QMMovimientosComunidades.CAMPO8);
						NOMCOC = rs.getString(QMMovimientosComunidades.CAMPO12);
						MOVIMIENTO = rs.getString(QMMovimientosComunidades.CAMPO1);
						ERRORES = Long.toString(buscaCantidadErrores(conexion,MOVIMIENTO));
						
						ErrorComunidadTabla errorencontrado = new ErrorComunidadTabla(COCLDO, DCOCLDO, NUDCOM, NOMCOC, MOVIMIENTO, ERRORES);
						
						resultado.add(errorencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug(QMMovimientosComunidades.CAMPO1+":|"+MOVIMIENTO+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ErrorComunidadTabla>();
				
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
	
	public static ArrayList<ErrorComunidadTabla> buscaComunidadesActivoConError(Connection conexion, String sCOACES)
	{
		ArrayList<ErrorComunidadTabla> resultado = new ArrayList<ErrorComunidadTabla>();
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String COCLDO = "";
			String DCOCLDO = "";
			String NUDCOM = "";
			String NOMCOC = "";
			String MOVIMIENTO = "";
			String ERRORES = "";
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
						
						   + QMMovimientosComunidades.CAMPO1 + ","
						   + QMMovimientosComunidades.CAMPO7 + ","
						   + QMMovimientosComunidades.CAMPO8 + ","
						   + QMMovimientosComunidades.CAMPO12 + 

						   "  FROM " 
						   + QMMovimientosComunidades.TABLA + 
						   " WHERE ( "
						   + QMMovimientosComunidades.CAMPO10 +" LIKE '%"+ sCOACES +"%' AND "
						   
						   + QMMovimientosComunidades.CAMPO1 +" IN (SELECT DISTINCT "
						   +  CAMPO1 + 
						   "  FROM " 
						   + TABLA + "))";
			
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
						
						COCLDO = rs.getString(QMMovimientosComunidades.CAMPO7);
						DCOCLDO = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TCOCLDO, QMCodigosControl.ICOCLDO, COCLDO);
						NUDCOM = rs.getString(QMMovimientosComunidades.CAMPO8);
						NOMCOC = rs.getString(QMMovimientosComunidades.CAMPO12);
						MOVIMIENTO = rs.getString(QMMovimientosComunidades.CAMPO1);
						ERRORES = Long.toString(buscaCantidadErrores(conexion,MOVIMIENTO));
						
						ErrorComunidadTabla errorencontrado = new ErrorComunidadTabla(COCLDO, DCOCLDO, NUDCOM, NOMCOC, MOVIMIENTO, ERRORES);
						
						resultado.add(errorencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug(QMMovimientosComunidades.CAMPO1+":|"+MOVIMIENTO+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ErrorComunidadTabla>();
				
				logger.error("ERROR COACES:|"+sCOACES+"|");
				
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
						sDescripcion = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TCOTDORE1, QMCodigosControl.ICOTDORE1, sCodError);
						
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
				
				logger.error("ERROR Movimiento:|",sMovimiento+"|");

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
