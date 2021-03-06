package com.provisiones.dal.qm.listas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMCodigosControl;
import com.provisiones.dal.qm.QMGastos;
import com.provisiones.dal.qm.QMPagos;
import com.provisiones.dal.qm.QMProvisiones;
import com.provisiones.dal.qm.movimientos.QMMovimientosGastos;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.GastoTabla;

public final class QMListaGastos 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaGastos.class.getName());
	
	public static final String TABLA = "pp002_lista_gastos_multi";

	public static final String CAMPO1 = "cod_gasto";
	public static final String CAMPO2 = "cod_movimiento";
	
	public static final String CAMPO3 = "cod_validado";
	
	public static final String CAMPO4 = "usuario_movimiento";    
	public static final String CAMPO5 = "fecha_movimiento";
	public static final String CAMPO6 = "archivo_envio";
	public static final String CAMPO7 = "archivo_respuesta";
	
	private QMListaGastos(){}

	public static boolean addRelacionGasto(Connection conexion, long liCodGasto, long liCodMovimiento) 
	{
		boolean bSalida = false;

		String sUsuario = ConnectionManager.getUser();
	
		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "INSERT INTO " 
					+ TABLA + 
					" (" 
					+ CAMPO1 + "," 
					+ CAMPO2 + "," 
					+ CAMPO3 + "," 
					+ CAMPO4 + ","
					+ CAMPO5 +						
					") VALUES ('" 
					+ liCodGasto + "','"
					+ liCodMovimiento + "','"
					+ ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE + "','"
				    + sUsuario + "','"
				    + Utils.timeStamp() +
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
				
				logger.error("ERROR GASTO:|"+liCodGasto+"|");
				logger.error("ERROR MOVIMIENTO:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean addRelacionGastoBloqueado(Connection conexion, long liCodGasto, long liCodMovimiento) 
	{
		boolean bSalida = false;

		String sUsuario = ConnectionManager.getUser();
	
		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "INSERT INTO " 
					+ TABLA + 
					" (" 
					+ CAMPO1 + "," 
					+ CAMPO2 + "," 
					+ CAMPO3 + "," 
					+ CAMPO4 + ","
					+ CAMPO5 +						
					") VALUES ('" 
					+ liCodGasto + "','"
					+ liCodMovimiento + "','"
					+ ValoresDefecto.DEF_MOVIMIENTO_BLOQUEADO + "','"
				    + sUsuario + "','"
				    + Utils.timeStamp() +
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
				
				logger.error("ERROR GASTO:|"+liCodGasto+"|");
				logger.error("ERROR MOVIMIENTO:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean addRelacionGastoInyectado(Connection conexion, long liCodGasto, long liCodMovimiento) 
	{
		boolean bSalida = false;

		String sUsuario = ConnectionManager.getUser();
	
		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "INSERT INTO " 
					+ TABLA + 
					" (" 
					+ CAMPO1 + "," 
					+ CAMPO2 + "," 
					+ CAMPO3 + "," 
					+ CAMPO4 + ","
					+ CAMPO5 +						
					") VALUES ('" 
					+ liCodGasto + "','"
					+ liCodMovimiento + "','"
					+ ValoresDefecto.DEF_MOVIMIENTO_VALIDADO + "','"
				    + sUsuario + "','"
				    + Utils.timeStamp() +
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
				
				logger.error("ERROR GASTO:|"+liCodGasto+"|");
				logger.error("ERROR MOVIMIENTO:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean addRelacionGastoResuelto(Connection conexion, long liCodGasto, long liCodMovimiento) 
	{
		boolean bSalida = false;

		String sUsuario = ConnectionManager.getUser();
	
		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "INSERT INTO " 
					+ TABLA + 
					" (" 
					+ CAMPO1 + "," 
					+ CAMPO2 + "," 
					+ CAMPO3 + "," 
					+ CAMPO4 + ","
					+ CAMPO5 +						
					") VALUES ('" 
					+ liCodGasto + "','"
					+ liCodMovimiento + "','"
					+ ValoresDefecto.DEF_MOVIMIENTO_RESUELTO + "','"
				    + sUsuario + "','"
				    + Utils.timeStamp() +
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
				
				logger.error("ERROR GASTO:|"+liCodGasto+"|");
				logger.error("ERROR MOVIMIENTO:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean delRelacionGasto(Connection conexion, long liCodMovimiento) 
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO2 + " = '" + liCodMovimiento +"'";
			
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
				
				logger.error("ERROR MOVIMIENTO:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{

				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean existeRelacionGasto(Connection conexion, long liCodGasto, long liCodMovimiento)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					+ CAMPO1 + 
					" FROM " 
					+ TABLA + 
					" WHERE ("
					+ CAMPO1  + " = '"+ liCodGasto +"' AND " 
					+ CAMPO2  + " = '"+ liCodMovimiento + 
					"')";
			
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
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR GASTO:|"+liCodGasto+"|");
				logger.error("ERROR MOVIMIENTO:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return bEncontrado;
	}
	
	public static boolean existeMovimientoEnviado(Connection conexion, long liCodGasto)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					+ CAMPO1 + 
					" FROM " 
					+ TABLA + 
					" WHERE ("
					+ CAMPO1  + " = '"+ liCodGasto +"' AND " 
					+ CAMPO3  + " <> '"+ ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE + 
					"')";
			
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
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR GASTO:|"+liCodGasto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return bEncontrado;
	}
	
	public static boolean delMovimientosGastoValidado(Connection conexion, long liCodGasto, String sCodValidado) 
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sCondicionValidado = sCodValidado.isEmpty()? "":CAMPO3 + " = '"+ sCodValidado + "' AND ";
			
			String sQuery = "DELETE FROM " 
					+ QMMovimientosGastos.TABLA + 
					" WHERE " 
					+ QMMovimientosGastos.CAMPO1 + " IN (SELECT "
					+  CAMPO2 + 
					" FROM " 
					+ TABLA + 
					" WHERE ("
					+ sCondicionValidado
					+ CAMPO1 + " = '"+ liCodGasto + "'))";
			
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
				
				logger.error("ERROR GASTO:|"+liCodGasto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{

				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static ArrayList<Long>  getMovimientosGastosPorEstado(Connection conexion, String sEstado) 
	{
		ArrayList<Long> resultado = new ArrayList<Long>(); 

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO2 + 
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO3 + " = '" + sEstado + "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");
			
				int i = 0;
				
				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						resultado.add(rs.getLong(CAMPO2));
											
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO3+":|"+sEstado+"|");
						logger.debug(CAMPO2+":|"+resultado.get(i)+"|");

						i++;
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<Long>();
				
				logger.error("ERROR Validado:|"+sEstado+"|");

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
	

	public static boolean setValidado(Connection conexion, long liCodMovimiento, String sValidado)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO3 + " = '"+ sValidado + "' "+
					" WHERE ("
					+ CAMPO2 + " = '"+ liCodMovimiento +"' AND "
					+ CAMPO3 + " <> '"+ValoresDefecto.DEF_MOVIMIENTO_RESUELTO+"')";
			
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
				
				logger.error("ERROR MOVIMIENTO:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean setResuelto(Connection conexion, long liCodGasto)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO3 + " = '"+ ValoresDefecto.DEF_MOVIMIENTO_RESUELTO + "' "+
					" WHERE ("
					+ CAMPO1 + " = '"+ liCodGasto +"' AND "
					+ CAMPO3 + " = '"+ValoresDefecto.DEF_MOVIMIENTO_VALIDADO+"')";
			
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
				
				logger.error("ERROR Gasto:|"+liCodGasto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean setAutorizado(Connection conexion, long liCodGasto)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO3 + " = '"+ ValoresDefecto.DEF_MOVIMIENTO_VALIDADO + "' "+
					" WHERE ("
					+ CAMPO1 + " = '"+ liCodGasto +"' AND "
					+ CAMPO3 + " = '"+ValoresDefecto.DEF_MOVIMIENTO_RESUELTO+"')";
			
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
				
				logger.error("ERROR Gasto:|"+liCodGasto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean setDesbloqueado(Connection conexion, long liCodGasto)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO3 + " = '"+ ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE + "' "+
					" WHERE ("
					+ CAMPO1 + " = '"+ liCodGasto +"' AND "
					+ CAMPO3 + " = '"+ValoresDefecto.DEF_MOVIMIENTO_BLOQUEADO+"')";
			
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
				
				logger.error("ERROR Gasto:|"+liCodGasto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	

	public static String getValidado(Connection conexion, long liCodMovimiento)
	{
		String sValidado = "";
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO3 + 
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO2 + " = '" + liCodMovimiento + "'";
			
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

						sValidado = rs.getString(CAMPO3);

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO2+":|"+liCodMovimiento+"|");
						logger.debug(CAMPO3+":|"+sValidado+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sValidado = "";

				logger.error("ERROR Gasto:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sValidado;
	}
	
	public static long getCodGasto(Connection conexion, long liCodMovimiento)
	{
		long liCodGasto = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO1 + 
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO2 + " = '" + liCodMovimiento + "'";
			
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

						liCodGasto = rs.getLong(CAMPO1);

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO2+":|"+liCodMovimiento+"|");
						logger.debug(CAMPO1+":|"+liCodGasto+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				liCodGasto = 0;

				logger.error("ERROR Gasto:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return liCodGasto;
	}
	
	public static String getUltimoMoivimientoGastoValidado(Connection conexion, String sCodGasto, String sCodValidado) 
	{
		String sCodMovimiento = "";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
						+ CAMPO2 + 
						" FROM " 
						+ TABLA + 
						" WHERE ( " 
						+ CAMPO1 + " = '" + sCodGasto + "' AND " 
						+ CAMPO3 + " = '" + sCodValidado + "') "+
						" order by " + CAMPO1 + " desc limit 0,1 ";
			
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

						sCodMovimiento = rs.getString(CAMPO2);

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO2+":|"+sCodMovimiento+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sCodMovimiento = "";

				logger.error("ERROR sCodGasto:|"+sCodGasto+"|");
				logger.error("ERROR sCodValidado:|"+sCodValidado+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sCodMovimiento;
	}
	
	public static long buscaCantidadValidado(Connection conexion, String sCodValidado)
	{
		long liNumero = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT COUNT("+CAMPO1+") FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO3 + " = '" + sCodValidado + "'";
			
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
				logger.error("ERROR CodValidado:|"+sCodValidado+"|");

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
	
	public static ArrayList<ActivoTabla> buscaActivosConGastosAutorizados(Connection conexion, ActivoTabla filtro)
	{
		ArrayList<ActivoTabla> resultado = new ArrayList<ActivoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String sCondicionCOPOIN = filtro.getCOPOIN().isEmpty()?"":QMActivos.CAMPO14 + " LIKE '%" + filtro.getCOPOIN()	+ "%' AND ";
			//String sCondicionNOMUIN = filtro.getNOMUIN().isEmpty()?"":QMActivos.CAMPO11 + " LIKE '%" + filtro.getNOMUIN()	+ "%' AND ";
			//String sCondicionNOPRAC = filtro.getNOPRAC().isEmpty()?"":QMActivos.CAMPO13 + " LIKE '%" + filtro.getNOPRAC()	+ "%' AND ";
			//String sCondicionNOVIAS = filtro.getNOVIAS().isEmpty()?"":QMActivos.CAMPO6 + " LIKE '%" + filtro.getNOVIAS()	+ "%' AND ";
			//String sCondicionNUPIAC = filtro.getNUPIAC().isEmpty()?"":QMActivos.CAMPO9 + " LIKE '%" + filtro.getNUPIAC()	+ "%' AND ";
			//String sCondicionNUPOAC = filtro.getNUPOAC().isEmpty()?"":QMActivos.CAMPO7 + " LIKE '%" + filtro.getNUPOAC()	+ "%' AND ";
			//String sCondicionNUPUAC = filtro.getNUPUAC().isEmpty()?"":QMActivos.CAMPO10 + " LIKE '%" + filtro.getNUPUAC()	+ "%' AND ";
			//String sCondicionNUFIRE = filtro.getNUFIRE().isEmpty()?"":QMActivos.CAMPO28 + " LIKE '%" + filtro.getNUFIRE()	+ "%' AND ";

			String sCondicionNOMUIN = filtro.getNOMUIN().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOMUIN() + "%' AND ";
			String sCondicionNOPRAC = filtro.getNOPRAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOPRAC() + "%' AND ";
			String sCondicionNOVIAS = filtro.getNOVIAS().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOVIAS() + "%' AND ";
			String sCondicionNUPIAC = filtro.getNUPIAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPIAC() + "%' AND ";
			String sCondicionNUPOAC = filtro.getNUPOAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPOAC() + "%' AND ";
			String sCondicionNUPUAC = filtro.getNUPUAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPUAC() + "%' AND ";
			String sCondicionNUFIRE = filtro.getNUFIRE().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUFIRE() + "%' AND ";


			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						
						   + QMActivos.CAMPO1 + ","        
						   + QMActivos.CAMPO14 + ","
						   //+ QMActivos.CAMPO11 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO13 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO6 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO9 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO7 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO10 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO28 +
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) "+

						   " FROM " 
						   + QMActivos.TABLA + 
						   " WHERE ("

					   		+ sCondicionCOPOIN  
					   		+ sCondicionNOMUIN  
					   		+ sCondicionNOPRAC  
					   		+ sCondicionNOVIAS 
					   		+ sCondicionNUPIAC  
					   		+ sCondicionNUPOAC
					   		+ sCondicionNUPUAC
					   		+ sCondicionNUFIRE		

						   + QMActivos.CAMPO1 +" IN (SELECT "
						   + QMGastos.CAMPO2 + 
	   					   " FROM " 
						   + QMGastos.TABLA +
	   					   " WHERE " 
	   					   + QMGastos.CAMPO34 + " = '"+ ValoresDefecto.DEF_GASTO_AUTORIZADO +"' AND "

	   					   + QMGastos.CAMPO1 + " IN (SELECT "
						   + CAMPO1 + 
	   					   " FROM " 
						   + TABLA +
	   					   " WHERE " 
	   					   + CAMPO3 + " = '"+ ValoresDefecto.DEF_MOVIMIENTO_VALIDADO + "')))";
			
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
						
						String sCOACES = rs.getString(QMActivos.CAMPO1);
						String sCOPOIN = rs.getString(QMActivos.CAMPO14);
						//String sNOMUIN = rs.getString(QMActivos.CAMPO11);
						String sNOMUIN = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNOPRAC = rs.getString(QMActivos.CAMPO13);
						String sNOPRAC = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNOVIAS = rs.getString(QMActivos.CAMPO6);
						String sNOVIAS = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPIAC = rs.getString(QMActivos.CAMPO9);
						String sNUPIAC = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPOAC = rs.getString(QMActivos.CAMPO7);
						String sNUPOAC = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPUAC = rs.getString(QMActivos.CAMPO10);
						String sNUPUAC = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUFIRE = rs.getString(QMActivos.CAMPO28);
						String sNUFIRE = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						
						ActivoTabla activoencontrado = new ActivoTabla(
								sCOACES, 
								sCOPOIN, 
								sNOMUIN, 
								sNOPRAC, 
								sNOVIAS, 
								sNUPIAC, 
								sNUPOAC, 
								sNUPUAC,
								sNUFIRE,
								"");
						
						resultado.add(activoencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug(QMActivos.CAMPO1+":|"+sCOACES+"|");
					}
				}
				if (!bEncontrado) 
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
		}

		return resultado;

	}

	public static ArrayList<ActivoTabla> buscaActivosConGastosAbonablesPorFiltro(Connection conexion, ActivoTabla filtro)
	{
		ArrayList<ActivoTabla> resultado = new ArrayList<ActivoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String sCondicionCOPOIN = filtro.getCOPOIN().isEmpty()?"":QMActivos.CAMPO14 + " LIKE '%" + filtro.getCOPOIN()	+ "%' AND ";
			//String sCondicionNOMUIN = filtro.getNOMUIN().isEmpty()?"":QMActivos.CAMPO11 + " LIKE '%" + filtro.getNOMUIN()	+ "%' AND ";
			//String sCondicionNOPRAC = filtro.getNOPRAC().isEmpty()?"":QMActivos.CAMPO13 + " LIKE '%" + filtro.getNOPRAC()	+ "%' AND ";
			//String sCondicionNOVIAS = filtro.getNOVIAS().isEmpty()?"":QMActivos.CAMPO6 + " LIKE '%" + filtro.getNOVIAS()	+ "%' AND ";
			//String sCondicionNUPIAC = filtro.getNUPIAC().isEmpty()?"":QMActivos.CAMPO9 + " LIKE '%" + filtro.getNUPIAC()	+ "%' AND ";
			//String sCondicionNUPOAC = filtro.getNUPOAC().isEmpty()?"":QMActivos.CAMPO7 + " LIKE '%" + filtro.getNUPOAC()	+ "%' AND ";
			//String sCondicionNUPUAC = filtro.getNUPUAC().isEmpty()?"":QMActivos.CAMPO10 + " LIKE '%" + filtro.getNUPUAC()	+ "%' AND ";
			//String sCondicionNUFIRE = filtro.getNUFIRE().isEmpty()?"":QMActivos.CAMPO28 + " LIKE '%" + filtro.getNUFIRE()	+ "%' AND ";

			String sCondicionNOMUIN = filtro.getNOMUIN().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOMUIN() + "%' AND ";
			String sCondicionNOPRAC = filtro.getNOPRAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOPRAC() + "%' AND ";
			String sCondicionNOVIAS = filtro.getNOVIAS().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOVIAS() + "%' AND ";
			String sCondicionNUPIAC = filtro.getNUPIAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPIAC() + "%' AND ";
			String sCondicionNUPOAC = filtro.getNUPOAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPOAC() + "%' AND ";
			String sCondicionNUPUAC = filtro.getNUPUAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPUAC() + "%' AND ";
			String sCondicionNUFIRE = filtro.getNUFIRE().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUFIRE() + "%' AND ";


			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						
						   + QMActivos.CAMPO1 + ","        
						   + QMActivos.CAMPO14 + ","
						   //+ QMActivos.CAMPO11 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO13 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO6 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO9 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO7 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO10 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO28 +
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) "+

						   " FROM " 
						   + QMActivos.TABLA + 
						   " WHERE ("

					   		+ sCondicionCOPOIN  
					   		+ sCondicionNOMUIN  
					   		+ sCondicionNOPRAC  
					   		+ sCondicionNOVIAS 
					   		+ sCondicionNUPIAC  
					   		+ sCondicionNUPOAC
					   		+ sCondicionNUPUAC
					   		+ sCondicionNUFIRE		

						   + QMActivos.CAMPO1 +" IN (SELECT "
						   + QMGastos.CAMPO2 + 
	   					   " FROM " 
						   + QMGastos.TABLA +
	   					   " WHERE "
						   + QMGastos.CAMPO33 + " > 0 AND "
						   + QMGastos.CAMPO34 + " IN ('" + ValoresDefecto.DEF_GASTO_AUTORIZADO + "','" + ValoresDefecto.DEF_GASTO_PAGADO + "') AND "
	   					   + QMGastos.CAMPO1 + " IN (SELECT "
						   + CAMPO1 + 
	   					   " FROM " 
						   + TABLA +
	   					   " WHERE " 
	   					   + CAMPO3 + " IN ('"+ ValoresDefecto.DEF_MOVIMIENTO_VALIDADO + "','"+ValoresDefecto.DEF_MOVIMIENTO_RESUELTO+"'))))";
			
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
						
						String sCOACES = rs.getString(QMActivos.CAMPO1);
						String sCOPOIN = rs.getString(QMActivos.CAMPO14);
						//String sNOMUIN = rs.getString(QMActivos.CAMPO11);
						String sNOMUIN = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNOPRAC = rs.getString(QMActivos.CAMPO13);
						String sNOPRAC = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNOVIAS = rs.getString(QMActivos.CAMPO6);
						String sNOVIAS = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPIAC = rs.getString(QMActivos.CAMPO9);
						String sNUPIAC = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPOAC = rs.getString(QMActivos.CAMPO7);
						String sNUPOAC = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPUAC = rs.getString(QMActivos.CAMPO10);
						String sNUPUAC = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUFIRE = rs.getString(QMActivos.CAMPO28);
						String sNUFIRE = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						
						ActivoTabla activoencontrado = new ActivoTabla(
								sCOACES, 
								sCOPOIN, 
								sNOMUIN, 
								sNOPRAC, 
								sNOVIAS, 
								sNUPIAC, 
								sNUPOAC, 
								sNUPUAC,
								sNUFIRE,
								"");
						
						resultado.add(activoencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug(QMActivos.CAMPO1+":|"+sCOACES+"|");
					}
				}
				if (!bEncontrado) 
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
		}

		return resultado;

	}
	
		
	public static ArrayList<ActivoTabla> buscaActivosConAbonosEjecutablesPorFiltro(Connection conexion, ActivoTabla filtro)
	{
		ArrayList<ActivoTabla> resultado = new ArrayList<ActivoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String sCondicionCOPOIN = filtro.getCOPOIN().isEmpty()?"":QMActivos.CAMPO14 + " LIKE '%" + filtro.getCOPOIN()	+ "%' AND ";
			//String sCondicionNOMUIN = filtro.getNOMUIN().isEmpty()?"":QMActivos.CAMPO11 + " LIKE '%" + filtro.getNOMUIN()	+ "%' AND ";
			//String sCondicionNOPRAC = filtro.getNOPRAC().isEmpty()?"":QMActivos.CAMPO13 + " LIKE '%" + filtro.getNOPRAC()	+ "%' AND ";
			//String sCondicionNOVIAS = filtro.getNOVIAS().isEmpty()?"":QMActivos.CAMPO6 + " LIKE '%" + filtro.getNOVIAS()	+ "%' AND ";
			//String sCondicionNUPIAC = filtro.getNUPIAC().isEmpty()?"":QMActivos.CAMPO9 + " LIKE '%" + filtro.getNUPIAC()	+ "%' AND ";
			//String sCondicionNUPOAC = filtro.getNUPOAC().isEmpty()?"":QMActivos.CAMPO7 + " LIKE '%" + filtro.getNUPOAC()	+ "%' AND ";
			//String sCondicionNUPUAC = filtro.getNUPUAC().isEmpty()?"":QMActivos.CAMPO10 + " LIKE '%" + filtro.getNUPUAC()	+ "%' AND ";
			//String sCondicionNUFIRE = filtro.getNUFIRE().isEmpty()?"":QMActivos.CAMPO28 + " LIKE '%" + filtro.getNUFIRE()	+ "%' AND ";

			String sCondicionNOMUIN = filtro.getNOMUIN().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOMUIN() + "%' AND ";
			String sCondicionNOPRAC = filtro.getNOPRAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOPRAC() + "%' AND ";
			String sCondicionNOVIAS = filtro.getNOVIAS().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOVIAS() + "%' AND ";
			String sCondicionNUPIAC = filtro.getNUPIAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPIAC() + "%' AND ";
			String sCondicionNUPOAC = filtro.getNUPOAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPOAC() + "%' AND ";
			String sCondicionNUPUAC = filtro.getNUPUAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPUAC() + "%' AND ";
			String sCondicionNUFIRE = filtro.getNUFIRE().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUFIRE() + "%' AND ";

			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						
						   + QMActivos.CAMPO1 + ","        
						   + QMActivos.CAMPO14 + ","
						   //+ QMActivos.CAMPO11 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO13 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO6 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO9 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO7 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO10 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO28 +
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) "+

						   " FROM " 
						   + QMActivos.TABLA + 
						   " WHERE ("

					   		+ sCondicionCOPOIN  
					   		+ sCondicionNOMUIN  
					   		+ sCondicionNOPRAC  
					   		+ sCondicionNOVIAS 
					   		+ sCondicionNUPIAC  
					   		+ sCondicionNUPOAC
					   		+ sCondicionNUPUAC
					   		+ sCondicionNUFIRE		

						   + QMActivos.CAMPO1 +" IN (SELECT "
						   + QMGastos.CAMPO2 + 
	   					   " FROM " 
						   + QMGastos.TABLA +
	   					   " WHERE "
						   + QMGastos.CAMPO10 + " = '" + ValoresDefecto.DEF_GASTO_ABONADO + "' AND "
						   + QMGastos.CAMPO34 + " = '" + ValoresDefecto.DEF_GASTO_ABONADO + "' AND "
	   					   + QMGastos.CAMPO1 + " IN (SELECT "
						   + CAMPO1 + 
	   					   " FROM " 
						   + TABLA +
	   					   " WHERE " 
	   					   + CAMPO3 + " = '"+ ValoresDefecto.DEF_MOVIMIENTO_VALIDADO + "' AND "
	   					   + CAMPO1 + " IN (SELECT "
	   					   + QMListaAbonosGastos.CAMPO1 + 
	   					   " FROM " 
						   + QMListaAbonosGastos.TABLA +
	   					   " WHERE " 
	   					   + QMListaAbonosGastos.CAMPO4 + " = '"+ ValoresDefecto.ABONO_EMITIDO + "'))))";
			
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
						
						String sCOACES = rs.getString(QMActivos.CAMPO1);
						String sCOPOIN = rs.getString(QMActivos.CAMPO14);
						//String sNOMUIN = rs.getString(QMActivos.CAMPO11);
						String sNOMUIN = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNOPRAC = rs.getString(QMActivos.CAMPO13);
						String sNOPRAC = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNOVIAS = rs.getString(QMActivos.CAMPO6);
						String sNOVIAS = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPIAC = rs.getString(QMActivos.CAMPO9);
						String sNUPIAC = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPOAC = rs.getString(QMActivos.CAMPO7);
						String sNUPOAC = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPUAC = rs.getString(QMActivos.CAMPO10);
						String sNUPUAC = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUFIRE = rs.getString(QMActivos.CAMPO28);
						String sNUFIRE = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						
						ActivoTabla activoencontrado = new ActivoTabla(
								sCOACES, 
								sCOPOIN, 
								sNOMUIN, 
								sNOPRAC, 
								sNOVIAS, 
								sNUPIAC, 
								sNUPOAC, 
								sNUPUAC,
								sNUFIRE,
								"");
						
						resultado.add(activoencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug(QMActivos.CAMPO1+":|"+sCOACES+"|");
					}
				}
				if (!bEncontrado) 
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
		}

		return resultado;

	}
	
	public static ArrayList<GastoTabla> buscaGastosValidadosActivo(Connection conexion, int iCodCOACES)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						   + QMGastos.CAMPO1 + ","
						   + QMGastos.CAMPO2 + ","        
						   + QMGastos.CAMPO3 + ","
						   + QMGastos.CAMPO4 + ","
						   + QMGastos.CAMPO5 + ","
						   + QMGastos.CAMPO6 + ","
						   + QMGastos.CAMPO7 + ","
						   + QMGastos.CAMPO9 + ","
						   + QMGastos.CAMPO10 + ","
						   + QMGastos.CAMPO14 + ","
						   + QMGastos.CAMPO15 + ","
						   + QMGastos.CAMPO16 + ","
						   + QMGastos.CAMPO34 +	","	
						   + QMGastos.CAMPO35 +

						   " FROM " 
						   + QMGastos.TABLA + 
						   " WHERE ((("
						   + QMGastos.CAMPO34 + 
						   " IN ('" 
						   + ValoresDefecto.DEF_GASTO_ESTIMADO + "','" 
						   + ValoresDefecto.DEF_GASTO_CONOCIDO + 
						   "')) AND "
						   + QMGastos.CAMPO16 + " <> '" + ValoresDefecto.DEF_NEGATIVO + "' "+
						   ") AND "					   
						   + QMGastos.CAMPO2 + " = '" + iCodCOACES	+ "' AND "
						   + QMGastos.CAMPO7 + " <= '"+Utils.fechaDeHoy(false)+"' AND "

						   + QMGastos.CAMPO1 +" IN (SELECT "
						   +  CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE " 
						   + CAMPO3 + " = '"+ ValoresDefecto.DEF_MOVIMIENTO_VALIDADO + "'))";					   
						   
			
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
						
						String sGastoID = rs.getString(QMGastos.CAMPO1);
						String sNUPROF = QMListaGastosProvisiones.getProvisionDeGasto(conexion, rs.getLong(QMGastos.CAMPO1));
						String sEstadoPROF = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF,QMCodigosControl.IESPROF,QMProvisiones.getEstado(conexion, sNUPROF));
						String sCOACES  = rs.getString(QMGastos.CAMPO2);
						String sCOGRUG  = rs.getString(QMGastos.CAMPO3);
						String sCOTPGA  = rs.getString(QMGastos.CAMPO4);
						String sCOSBGA  = rs.getString(QMGastos.CAMPO5);
						String sDCOSBGA = QMCodigosControl.getDesCOSBGA(conexion,sCOGRUG,sCOTPGA,sCOSBGA);
						String sPTPAGO  = rs.getString(QMGastos.CAMPO6);
						String sDPTPAGO = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
						String sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO7));
						String sCOSIGA  = rs.getString(QMGastos.CAMPO10);
						String sDCOSIGA = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TCOSIGA,QMCodigosControl.ICOSIGA,sCOSIGA);
						String sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO16).equals("-"),rs.getString(QMGastos.CAMPO15));
						String sEstado  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34));
						String sFEEPAI  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO14));
						String sFELIPG  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO9));
						String sTipoPago  = "";
						
						if (sCOSIGA.equals(ValoresDefecto.DEF_GASTO_PAGADO)
							|| sCOSIGA.equals(ValoresDefecto.DEF_GASTO_ABONADO))
						{
							sTipoPago  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOPAGO,QMCodigosControl.ICOPAGO,QMPagos.getTipoPago(conexion, rs.getLong(QMGastos.CAMPO1)));							
						}
						
						String sUrgente  = Utils.recuperaBit((rs.getString(QMGastos.CAMPO35)));
						
						GastoTabla gastoencontrado = new GastoTabla(
								sGastoID,
								sNUPROF,
								sEstadoPROF,
								sCOACES,
								sCOGRUG,
								sCOTPGA,
								sCOSBGA,
								sDCOSBGA,
								sPTPAGO,
								sDPTPAGO,
								sFEDEVE,
								sCOSIGA,
								sDCOSIGA,
								sIMNGAS,
								sEstado,
								sFEEPAI,
								sFELIPG,
								sTipoPago,
								sUrgente);
						
						resultado.add(gastoencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+iCodCOACES+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<GastoTabla>();
				
				logger.error("ERROR COACES:|"+iCodCOACES+"|");
				
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
	
	public static ArrayList<Long> buscarDependencias(Connection conexion, long liCodGasto, long liCodMovimiento)
	{
		ArrayList<Long> resultado = new ArrayList<Long>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;	
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO2  + 
					" FROM " 
					+ TABLA + 
					" WHERE (" 
					+ CAMPO1 + " = '" + liCodGasto + "' AND "
					+ CAMPO2 + " >=  '" + liCodMovimiento + 
					"')";
			
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
						
						resultado.add(rs.getLong(CAMPO2));

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}			
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<Long>();
				
				logger.error("ERROR GASTO:|"+liCodGasto+"|");
				logger.error("ERROR MOVIMIENTO:|"+liCodMovimiento+"|");

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
	
	public static ArrayList<Long> buscarMovimientosGasto(Connection conexion, long liCodGasto)
	{
		ArrayList<Long> resultado = new ArrayList<Long>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO2  + 
					"  FROM " + TABLA + 
					" WHERE "
					+ CAMPO1 + " = '" + liCodGasto + "'";
			
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
						
						resultado.add(rs.getLong(CAMPO2));

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}			
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<Long>();
				
				logger.error("ERROR GASTO:|"+liCodGasto+"|");

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
	
	public static ArrayList<Long> buscarMovimientosValidadosGasto(Connection conexion, long liCodGasto)
	{
		ArrayList<Long> resultado = new ArrayList<Long>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + CAMPO1 + 
					   " FROM " 
					   + TABLA + 
					   " WHERE (" 
					   + CAMPO1 + " = '"+ liCodGasto + "' AND "
					   + CAMPO3 + " = '"+ ValoresDefecto.DEF_MOVIMIENTO_VALIDADO + "')";
			
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
						
						resultado.add(rs.getLong(CAMPO2));

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}			
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<Long>();
				
				logger.error("ERROR GASTO:|"+liCodGasto+"|");

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

//Autor: Francisco Valverde Manjón