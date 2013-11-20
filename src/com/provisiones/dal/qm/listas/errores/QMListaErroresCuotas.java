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

	public static boolean addErrorCuota(Connection conexion, String sCodMovimiento, String sCodCOTDOR)
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

				logger.error("ERROR: Movimiento:|"+sCodMovimiento+"|");
				logger.error("ERROR: COTDOR:|"+sCodCOTDOR+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean delErrorCuota(Connection conexion, String sCodMovimiento, String sCodCOTDOR)
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
				
				logger.error("ERROR: Movimiento:|"+sCodMovimiento+"|");
				logger.error("ERROR: COTDOR:|"+sCodCOTDOR+"|");

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
				
				logger.error("ERROR: Movimiento:|"+sMovimiento+"|");

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
	
	public static ArrayList<ErrorCuotaTabla> buscaCuotasConError(Connection conexion, ErrorCuotaTabla filtro)
	{
		ArrayList<ErrorCuotaTabla> resultado = new ArrayList<ErrorCuotaTabla>();
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String COACES = "";
			String COCLDO = "";
			String DCOCLDO = "";
			String NUDCOM = "";
			String COSBAC = "";
			String DCOSBAC = "";
			
			String MOVIMIENTO = "";
			String ERRORES = "";
			
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
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						COACES = rs.getString(QMMovimientosCuotas.CAMPO9);
						COCLDO = rs.getString(QMMovimientosCuotas.CAMPO6);
						DCOCLDO = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TCOCLDO, QMCodigosControl.ICOCLDO, COCLDO);
						NUDCOM = rs.getString(QMMovimientosCuotas.CAMPO7);
						COSBAC = rs.getString(QMMovimientosCuotas.CAMPO12);
						DCOSBAC = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TCOSBGAT22,QMCodigosControl.ICOSBGAT22,COSBAC);
						MOVIMIENTO = rs.getString(QMMovimientosCuotas.CAMPO1);
						ERRORES = Long.toString(buscaCantidadErrores(conexion,MOVIMIENTO));
						
						ErrorCuotaTabla errorencontrado = new ErrorCuotaTabla(COACES, COCLDO, DCOCLDO, NUDCOM, COSBAC, DCOSBAC,MOVIMIENTO, ERRORES);
						
						resultado.add(errorencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug(QMMovimientosCuotas.CAMPO1+":|"+MOVIMIENTO+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ErrorCuotaTabla>();
				
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
						sDescripcion = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TCOTDORE2, QMCodigosControl.ICOTDORE2, sCodError);
						
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
				
				logger.error("ERROR: Movimiento:|"+sMovimiento);

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
