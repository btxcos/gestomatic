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

	public static boolean addErrorGasto(Connection conexion, String sCodMovimiento, String sCodCOTDOR)
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

	public static boolean delErrorGasto(Connection conexion, String sCodMovimiento, String sCodCOTDOR)
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
	
	public static ArrayList<ErrorGastoTabla> buscaGastosConError(Connection conexion, ErrorGastoTabla filtro)
	{
		ArrayList<ErrorGastoTabla> resultado = new ArrayList<ErrorGastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			boolean bEncontrado = false;

			String COACES = "";
			String COGRUG = "";
			String COTPGA = "";
			String COSBGA = "";
			String DCOSBGA = "";
			String IMNGAS = "";
			String FEDEVE = "";
			
			String MOVIMIENTO = "";
			String ERRORES = "";
			
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
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;
						
						COACES = rs.getString(QMMovimientosGastos.CAMPO2);
						COGRUG = rs.getString(QMMovimientosGastos.CAMPO3);
						COTPGA = rs.getString(QMMovimientosGastos.CAMPO4);
						COSBGA = rs.getString(QMMovimientosGastos.CAMPO5);
						DCOSBGA = QMCodigosControl.getDesCOSBGA(conexion,COGRUG,COTPGA,COSBGA);
						IMNGAS = Utils.recuperaImporte(rs.getString(QMMovimientosGastos.CAMPO17).equals("-"),rs.getString(QMMovimientosGastos.CAMPO16));
						FEDEVE = Utils.recuperaFecha(rs.getString(QMMovimientosGastos.CAMPO7));
						logger.debug(QMMovimientosGastos.CAMPO7+":|"+FEDEVE+"|");
						MOVIMIENTO = rs.getString(QMMovimientosGastos.CAMPO1);
						ERRORES = Long.toString(buscaCantidadErrores(conexion,MOVIMIENTO));
						
						ErrorGastoTabla errorencontrado = new ErrorGastoTabla(COACES, COGRUG, COTPGA, COSBGA, DCOSBGA, IMNGAS, FEDEVE,MOVIMIENTO, ERRORES);
						
						resultado.add(errorencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug(QMMovimientosGastos.CAMPO1+":|"+MOVIMIENTO+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ErrorGastoTabla>();
				
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
						sDescripcion = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TCOTERR, QMCodigosControl.ICOTERR, sCodError);
						
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
