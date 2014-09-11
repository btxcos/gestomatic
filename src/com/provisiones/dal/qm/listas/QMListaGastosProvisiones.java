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
import com.provisiones.dal.qm.QMCodigosControl;
import com.provisiones.dal.qm.QMGastos;
import com.provisiones.dal.qm.QMPagos;
import com.provisiones.dal.qm.QMProvisiones;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.tablas.GastoTabla;

public final class QMListaGastosProvisiones 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaGastosProvisiones.class.getName());
	
	public static final String TABLA = "pp002_lista_gastos_provisiones_multi";

	public static final String CAMPO1 = "cod_gasto";
	public static final String CAMPO2 = "cod_nuprof";
	public static final String CAMPO3 = "cod_revisado";
	public static final String CAMPO4 = "usuario_alta";
	public static final String CAMPO5 = "fecha_alta";

	private QMListaGastosProvisiones(){}

	public static boolean addRelacionGastoProvision(Connection conexion, long liCodGasto, String sCodNUPROF) 
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
					+ sCodNUPROF + "','"
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
				logger.error("ERROR PROVISION:|"+sCodNUPROF+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	
	public static boolean addRelacionGastoProvisionBloqueado(Connection conexion, long liCodGasto, String sCodNUPROF) 
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
					+ sCodNUPROF + "','"
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
				logger.error("ERROR PROVISION:|"+sCodNUPROF+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean addRelacionGastoProvisionInyectado(Connection conexion, long liCodGasto, String sCodNUPROF) 
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
					+ sCodNUPROF + "','"
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
				logger.error("ERROR PROVISION:|"+sCodNUPROF+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean addRelacionGastoProvisionResuelto(Connection conexion, long liCodGasto, String sCodNUPROF) 
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
					+ sCodNUPROF + "','"
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
				logger.error("ERROR PROVISION:|"+sCodNUPROF+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean delRelacionGastoProvision(Connection conexion, long liCodGasto) 
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO1 + " = '" + liCodGasto +"'";
			
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
	
	public static boolean existeRelacionGastoProvision(Connection conexion, long liCodGasto, String sCodNUPROF)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					+ CAMPO3 + 
					" FROM " 
					+ TABLA + 
					" WHERE ("	
					+ CAMPO1  + " = '"+ liCodGasto +"' AND "
					+ CAMPO2  + " = '"+ sCodNUPROF + 
					"' )";
			
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
				logger.error("ERROR PROVISION:|"+sCodNUPROF+"|");

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
	
	public static boolean existeBloqueoGasto(Connection conexion, long liCodGasto)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					+ CAMPO2 + 
					" FROM " 
					+ TABLA + 
					" WHERE ("	
					+ CAMPO1  + " = '"+ liCodGasto +"' AND "
					+ CAMPO3  + " = '"+ ValoresDefecto.DEF_MOVIMIENTO_BLOQUEADO + 
					"' )";
			
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
	
	public static boolean provisionbloqueada(Connection conexion, String sCodNUPROF)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					+ CAMPO3 + 
					" FROM " 
					+ TABLA + 
					" WHERE ("	
					+ CAMPO3  + " = '"+ ValoresDefecto.DEF_MOVIMIENTO_BLOQUEADO +"' AND "
					+ CAMPO2  + " = '"+ sCodNUPROF + 
					"' )";
			
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

				logger.error("ERROR PROVISION:|"+sCodNUPROF+"|");

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
	
	public static boolean setRevisado(Connection conexion, long liCodGasto, String sRevisado)
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO3 + " = '"+ sRevisado + "' "+
					" WHERE ("
					+ CAMPO1 + " = '"+ liCodGasto +"' AND "
					+ CAMPO3 + " <> '"+ValoresDefecto.DEF_MOVIMIENTO_RESUELTO+"' )";
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
					+ CAMPO3 + " = '"+ValoresDefecto.DEF_MOVIMIENTO_VALIDADO+"' )";
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
	
	public static boolean setAutorizado(Connection conexion, long liCodGasto, String sNUPROF)
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
					+ CAMPO2 + " = '"+ sNUPROF +"' AND "
					+ CAMPO3 + " = '"+ValoresDefecto.DEF_MOVIMIENTO_RESUELTO+"' )";
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
					+ CAMPO3 + " = '"+ValoresDefecto.DEF_MOVIMIENTO_BLOQUEADO+"' )";
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
	
	
	public static String getRevisado(Connection conexion, long liCodGasto)
	{
		String sRevisado = "";

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

						sRevisado = rs.getString(CAMPO3);

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+liCodGasto+"|");
						logger.debug(CAMPO3+":|"+sRevisado+"|");
					}
				}
				if (!bEncontrado) 
				{
	 
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sRevisado = "";

				logger.error("ERROR Gasto:|"+liCodGasto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sRevisado;
	}
	
	
	public static String getProvisionDeGasto(Connection conexion, long liCodGasto)
	{
		String sNUPROF = "";

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
					+ CAMPO1  + " = '"+ liCodGasto +"' "+
					" order by " + CAMPO2 + " limit 0,1";
			
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

						sNUPROF = rs.getString(CAMPO2);
						
						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO2+":|"+sNUPROF+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sNUPROF = "";

				logger.error("ERROR GASTO:|"+liCodGasto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sNUPROF;
	}
	
	public static String getProvisionDeAbono(Connection conexion, long liCodGasto)
	{
		String sNUPROF = "";

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
					+ CAMPO1  + " = '"+ liCodGasto +"'"+
					" order by " + CAMPO2 + " desc limit 0,1";
			
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

						sNUPROF = rs.getString(CAMPO2);
						
						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO2+":|"+sNUPROF+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sNUPROF = "";

				logger.error("ERROR GASTO:|"+liCodGasto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sNUPROF;
	}
	
	public static String getProvisionDeMovimiento(Connection conexion, long liCodMovimientoGasto)
	{
		String sNUPROF = "";
		
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
					+ CAMPO1 +
					" IN (SELECT "
					+ QMListaGastos.CAMPO1 +
					" FROM " 
					+ QMListaGastos.TABLA +
					" WHERE "
					+ QMListaGastos.CAMPO2  + " = '"+ liCodMovimientoGasto +"' )";
			
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

						sNUPROF = rs.getString(CAMPO2);

						logger.debug("Encontrado el registro!");
						logger.debug(QMListaGastos.CAMPO2+":|"+liCodMovimientoGasto+"|");
						logger.debug(CAMPO2+":|"+sNUPROF+"|");
					}
				}
				if (!bEncontrado) 
				{
	 
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sNUPROF = "";

				logger.error("ERROR MOVIMIENTO:|"+liCodMovimientoGasto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}			
		}

		return sNUPROF;
	}
	
	public static int getPrimeraFechaLimitePagoProvision(Connection conexion, String sNUPROF)
	{
		int iFechaLimite = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;


			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						   + QMGastos.CAMPO9 +

						   " FROM " 
						   + QMGastos.TABLA + 
						   " WHERE ("
						   + QMGastos.CAMPO34 + " = '" + ValoresDefecto.DEF_GASTO_AUTORIZADO + "' AND " 
						   + QMGastos.CAMPO1 + 
						   " IN (SELECT "
						   +  CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE " 
						   + CAMPO2 + " = '"+ sNUPROF + "')) order by "
						   +QMGastos.CAMPO9 + " limit 0,1";
			
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
						
						iFechaLimite = rs.getInt(QMGastos.CAMPO9);
						
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
				logger.error("ERROR NUPROF:|"+sNUPROF+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return iFechaLimite;
	}
	
	public static int getUltimaFechaDevengoProvision(Connection conexion, String sNUPROF)
	{
		int iFechaLimite = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;


			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						   + QMGastos.CAMPO7 +

						   " FROM " 
						   + QMGastos.TABLA + 
						   " WHERE ("
						   + QMGastos.CAMPO34 + " = '" + ValoresDefecto.DEF_GASTO_AUTORIZADO + "' AND " 
						   + QMGastos.CAMPO1 + 
						   " IN (SELECT "
						   +  CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE " 
						   + CAMPO2 + " = '"+ sNUPROF + "')) ORDER BY "
						   +QMGastos.CAMPO7 + "  DESC LIMIT 0,1";
			
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
						
						iFechaLimite = rs.getInt(QMGastos.CAMPO7);
						
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
				logger.error("ERROR NUPROF:|"+sNUPROF+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return iFechaLimite;
	}
	
	public static int getUltimaFechaDevengoProvisionComunidad(Connection conexion, String sNUPROF)
	{
		int iFechaLimite = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;


			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						   + QMGastos.CAMPO7 +

						   " FROM " 
						   + QMGastos.TABLA + 
						   " WHERE ("
						   + QMGastos.CAMPO34 + " = '" + ValoresDefecto.DEF_GASTO_AUTORIZADO + "' AND "
						   + QMGastos.CAMPO2 + 
						   " IN (SELECT "
						   +  QMListaComunidadesActivos.CAMPO1 + 
						   " FROM " 
						   + QMListaComunidadesActivos.TABLA + ") AND "
						   + QMGastos.CAMPO1 + 
						   " IN (SELECT "
						   + CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE " 
						   + CAMPO2 + " = '"+ sNUPROF + "')) ORDER BY "
						   + QMGastos.CAMPO7 + "  DESC LIMIT 0,1";
			
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
						
						iFechaLimite = rs.getInt(QMGastos.CAMPO7);
						
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
				logger.error("ERROR NUPROF:|"+sNUPROF+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return iFechaLimite;
	}

	public static ArrayList<Long>  buscaGastosIDPorProvision(Connection conexion, String sCodNUPROF) 
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
					" WHERE " 
					+ CAMPO2 + " = '" + sCodNUPROF + "'";

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

						resultado.add(rs.getLong(CAMPO1));

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

				logger.error("ERROR NUPROF:|"+sCodNUPROF+"|");

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

	public static ArrayList<Long> buscaGastosSinValidarEnProvision(Connection conexion, String sNUPROF) 
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
					" FROM " + TABLA + 
					" WHERE ( " 
					+ CAMPO2 + " = '"+ sNUPROF + "' AND "
					
					+ CAMPO1+ " IN (SELECT "
					+ QMListaGastos.CAMPO1 +
					" FROM " + QMListaGastos.TABLA +
					" WHERE " 
					+ QMListaGastos.CAMPO3 + " NOT IN ('"
					+ValoresDefecto.DEF_MOVIMIENTO_VALIDADO+"','"
					+ValoresDefecto.DEF_MOVIMIENTO_RESUELTO+
					"')))";
			
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

						resultado.add(rs.getLong(CAMPO1));
						
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

				logger.error("ERROR PROVISION:|"+sNUPROF+"|");

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
	
	public static ArrayList<GastoTabla> buscaGastosProvisionPorEstadoGasto(Connection conexion, String sNUPROF, String sEstado)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			String sCondicionEstado = sEstado.isEmpty()?"":QMGastos.CAMPO34 + " = '" + sEstado + "' AND ";
			
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
						   " WHERE ("
						   + sCondicionEstado
						   + QMGastos.CAMPO1 + 
						   " IN (SELECT "
						   +  CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE " 
						   + CAMPO2 + " = '"+ sNUPROF + "'))";					   
						   
			
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
						//String sEstado  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34));
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
								QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34)),
								sFEEPAI,
								sFELIPG,
								sTipoPago,
								sUrgente);
						
						
						
						resultado.add(gastoencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO2+":|"+sNUPROF+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				logger.error("ERROR NUPROF:|"+sNUPROF+"|");
				
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
	
	public static ArrayList<GastoTabla> buscaGastosProvisionPorFiltro(Connection conexion, GastoTabla filtro)
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
						   " WHERE ("
						   + QMGastos.CAMPO1 + 
						   " IN (SELECT "
						   +  CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE " 
						   + CAMPO2 + " = '"+ filtro.getNUPROF() + "') AND "
						   + QMGastos.CAMPO2 + " LIKE '%" + filtro.getCOACES() + "%' AND "  
						   + QMGastos.CAMPO3 + " LIKE '%" + filtro.getCOGRUG() + "%' AND "  
						   + QMGastos.CAMPO4 + " LIKE '%" + filtro.getCOTPGA() + "%' AND "  
						   + QMGastos.CAMPO5 + " LIKE '%" + filtro.getCOSBGA() + "%' AND "  
						   + QMGastos.CAMPO7 + " LIKE '%" + filtro.getFEDEVE() + "%')";					   
						   
			
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
						String sEstadoPROF = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF,QMCodigosControl.IESPROF,QMProvisiones.getEstado(conexion, filtro.getNUPROF()));
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
								filtro.getNUPROF(),
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

					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				logger.error("ERROR NUPROF:|"+filtro.getNUPROF()+"|");
				
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

	public static ArrayList<GastoTabla> buscaGastosProvisionPorFiltroEstado(Connection conexion, GastoTabla filtro, String sEstado, String sComparador)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			//Condiciones de filtro
			String sCondicionCOACES = filtro.getCOACES().isEmpty()?"":QMGastos.CAMPO2 + " = '" + filtro.getCOACES() + "' AND ";
			String sCondicionCOGRUG = filtro.getCOGRUG().isEmpty()?"":QMGastos.CAMPO3 + " = '" + filtro.getCOGRUG() + "' AND ";
			String sCondicionCOTPGA = filtro.getCOTPGA().isEmpty()?"":QMGastos.CAMPO4 + " = '" + filtro.getCOTPGA() + "' AND ";
			String sCondicionCOSBGA = filtro.getCOSBGA().isEmpty()?"":QMGastos.CAMPO5 + " = '" + filtro.getCOSBGA() + "' AND ";
			String sCondicionFEDEVE = (filtro.getFEDEVE().isEmpty() || filtro.getFEDEVE().equals("0"))?"":QMGastos.CAMPO7 + " = '" + filtro.getFEDEVE() + "' AND ";
			String sCondicionEstado = sEstado.isEmpty()?"":QMGastos.CAMPO34 + " = '" + sEstado + "' AND ";
			String sCondicionImporte = sComparador.isEmpty()?"":QMGastos.CAMPO15 + " "+sComparador+" " + filtro.getIMNGAS() + " AND ";
			
			
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
						   + QMGastos.CAMPO33 + ","
						   + QMGastos.CAMPO34 +	","	
						   + QMGastos.CAMPO35 +

						   " FROM " 
						   + QMGastos.TABLA + 
						   " WHERE ("
						   + sCondicionCOACES
						   + sCondicionCOGRUG
						   + sCondicionCOTPGA
						   + sCondicionCOSBGA
						   + sCondicionImporte
						   + sCondicionFEDEVE
						   + sCondicionEstado
						   + QMGastos.CAMPO1 + 
						   " IN (SELECT "
						   +  CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE " 
						   + CAMPO2 + " = '"+ filtro.getNUPROF() + "'))"+
						   " ORDER BY "+ QMGastos.CAMPO9;
						   
			
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
						String sEstadoPROF = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF,QMCodigosControl.IESPROF,QMProvisiones.getEstado(conexion, filtro.getNUPROF()));
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
						//String sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO16).equals("-"),rs.getString(QMGastos.CAMPO15));
						String sIMNGAS  = Utils.recuperaImporte(false,rs.getString(QMGastos.CAMPO33));
						//String sEstado  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34));
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
								filtro.getNUPROF(),
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
								QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34)),
								sFEEPAI,
								sFELIPG,
								sTipoPago,
								sUrgente);
						
						resultado.add(gastoencontrado);
						
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
				logger.error("ERROR NUPROF:|"+filtro.getNUPROF()+"|");
				
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
	
	public static ArrayList<GastoTabla> buscaGastosNuevosProvisionPorFiltro(Connection conexion, GastoTabla filtro, String sComparador)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			//Condiciones de filtro
			String sCondicionCOACES = filtro.getCOACES().isEmpty()?"":QMGastos.CAMPO2 + " = '" + filtro.getCOACES() + "' AND ";
			String sCondicionCOGRUG = filtro.getCOGRUG().isEmpty()?"":QMGastos.CAMPO3 + " = '" + filtro.getCOGRUG() + "' AND ";
			String sCondicionCOTPGA = filtro.getCOTPGA().isEmpty()?"":QMGastos.CAMPO4 + " = '" + filtro.getCOTPGA() + "' AND ";
			String sCondicionCOSBGA = filtro.getCOSBGA().isEmpty()?"":QMGastos.CAMPO5 + " = '" + filtro.getCOSBGA() + "' AND ";
			String sCondicionFEDEVE = (filtro.getFEDEVE().isEmpty() || filtro.getFEDEVE().equals("0"))?"":QMGastos.CAMPO7 + " = '" + filtro.getFEDEVE() + "' AND ";
			String sCondicionEstado = filtro.getESTADO().isEmpty()? 
					QMGastos.CAMPO34 + " IN ('" + ValoresDefecto.DEF_GASTO_ESTIMADO + "','" + ValoresDefecto.DEF_GASTO_CONOCIDO +"')"
					:QMGastos.CAMPO34 + " = '"+filtro.getESTADO()+"'"; 
			
			String sCondicionImporte = sComparador.isEmpty()?"":QMGastos.CAMPO15 + " "+sComparador+" " + filtro.getIMNGAS() + " AND ";
			
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
						   + QMGastos.CAMPO33 + ","
						   + QMGastos.CAMPO34 +	","	
						   + QMGastos.CAMPO35 +

						   " FROM " 
						   + QMGastos.TABLA + 
						   " WHERE ("
						   + sCondicionCOACES
						   + sCondicionCOGRUG
						   + sCondicionCOTPGA
						   + sCondicionCOSBGA
						   + sCondicionFEDEVE
						   + sCondicionImporte
						   + sCondicionEstado + " AND "
						   + QMGastos.CAMPO1 + 
						   " IN (SELECT "
						   +  CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE " 
						   + CAMPO2 + " = '"+ filtro.getNUPROF() + "'))"+
						   " ORDER BY "+ QMGastos.CAMPO9;
						   
			
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
						String sEstadoPROF = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF,QMCodigosControl.IESPROF,QMProvisiones.getEstado(conexion, filtro.getNUPROF()));
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
						//String sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO16).equals("-"),rs.getString(QMGastos.CAMPO15));
						String sIMNGAS  = Utils.recuperaImporte(false,rs.getString(QMGastos.CAMPO33));
						//String sEstado  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34));
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
								filtro.getNUPROF(),
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
								QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34)),
								sFEEPAI,
								sFELIPG,
								sTipoPago,
								sUrgente);
						
						resultado.add(gastoencontrado);
						
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
				logger.error("ERROR NUPROF:|"+filtro.getNUPROF()+"|");
				
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
	
	public static ArrayList<GastoTabla> buscaGastosProvisionRevisablesPorFiltro(Connection conexion, GastoTabla filtro, String sComparador)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			//Condiciones de filtro
			String sCondicionCOACES = filtro.getCOACES().isEmpty()?"":QMGastos.CAMPO2 + " = '" + filtro.getCOACES() + "' AND ";
			String sCondicionCOGRUG = filtro.getCOGRUG().isEmpty()?"":QMGastos.CAMPO3 + " = '" + filtro.getCOGRUG() + "' AND ";
			String sCondicionCOTPGA = filtro.getCOTPGA().isEmpty()?"":QMGastos.CAMPO4 + " = '" + filtro.getCOTPGA() + "' AND ";
			String sCondicionCOSBGA = filtro.getCOSBGA().isEmpty()?"":QMGastos.CAMPO5 + " = '" + filtro.getCOSBGA() + "' AND ";
			String sCondicionFEDEVE = (filtro.getFEDEVE().isEmpty() || filtro.getFEDEVE().equals("0"))?"":QMGastos.CAMPO7 + " = '" + filtro.getFEDEVE() + "' AND ";
			
			String sCondicionImporte = sComparador.isEmpty()?"":QMGastos.CAMPO15 + " "+sComparador+" " + filtro.getIMNGAS() + " AND ";
			
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
						   + QMGastos.CAMPO33 + ","
						   + QMGastos.CAMPO34 +	","	
						   + QMGastos.CAMPO35 +

						   " FROM " 
						   + QMGastos.TABLA + 
						   " WHERE ("
						   + sCondicionCOACES
						   + sCondicionCOGRUG
						   + sCondicionCOTPGA
						   + sCondicionCOSBGA
						   + sCondicionFEDEVE
						   + sCondicionImporte
						   + QMGastos.CAMPO34 + " IN ('" + ValoresDefecto.DEF_GASTO_AUTORIZADO + "','" + ValoresDefecto.DEF_GASTO_PAGADO + "') AND "
						   + QMGastos.CAMPO1 + 
						   " IN (SELECT "
						   +  CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE " 
						   + CAMPO2 + " = '"+ filtro.getNUPROF() + "'))"+
						   " ORDER BY "+ QMGastos.CAMPO9;
						   
			
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
						String sEstadoPROF = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF,QMCodigosControl.IESPROF,QMProvisiones.getEstado(conexion, filtro.getNUPROF()));
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
						//String sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO16).equals("-"),rs.getString(QMGastos.CAMPO15));
						String sIMNGAS  = Utils.recuperaImporte(false,rs.getString(QMGastos.CAMPO33));
						//String sEstado  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34));
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
								filtro.getNUPROF(),
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
								QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34)),
								sFEEPAI,
								sFELIPG,
								sTipoPago,
								sUrgente);
						
						resultado.add(gastoencontrado);
						
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
				logger.error("ERROR NUPROF:|"+filtro.getNUPROF()+"|");
				
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
		
	public static ArrayList<GastoTabla> buscaGastosAbonablesProvisionPorFiltroEstado(Connection conexion, GastoTabla filtro, String sEstado)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			//Condiciones de filtro
			String sCondicionCOACES = filtro.getCOACES().isEmpty()?"":QMGastos.CAMPO2 + " = '" + filtro.getCOACES() + "' AND ";
			String sCondicionCOGRUG = filtro.getCOGRUG().isEmpty()?"":QMGastos.CAMPO3 + " = '" + filtro.getCOGRUG() + "' AND ";
			String sCondicionCOTPGA = filtro.getCOTPGA().isEmpty()?"":QMGastos.CAMPO4 + " = '" + filtro.getCOTPGA() + "' AND ";
			String sCondicionCOSBGA = filtro.getCOSBGA().isEmpty()?"":QMGastos.CAMPO5 + " = '" + filtro.getCOSBGA() + "' AND ";
			String sCondicionFEDEVE = (filtro.getFEDEVE().equals("0") || filtro.getFEDEVE().isEmpty())?"":QMGastos.CAMPO7 + " = '" + filtro.getFEDEVE() + "' AND ";
			String sCondicionEstado = sEstado.isEmpty()?
					QMGastos.CAMPO34 + " IN ('" + ValoresDefecto.DEF_GASTO_AUTORIZADO + "','" + ValoresDefecto.DEF_GASTO_PAGADO + "') AND "
					:QMGastos.CAMPO34 + " = '" + sEstado + "' AND ";
			
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
						   " WHERE ("
						   + QMGastos.CAMPO10 + " <> '"+ValoresDefecto.DEF_GASTO_ABONADO+"' AND "
						   + QMGastos.CAMPO33 + " > 0 AND "
						   + sCondicionCOACES
						   + sCondicionCOGRUG
						   + sCondicionCOTPGA
						   + sCondicionCOSBGA
						   + sCondicionFEDEVE
						   + sCondicionEstado
						   + QMGastos.CAMPO1 + 
						   " IN (SELECT "
						   +  CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE " 
						   + CAMPO2 + " = '"+ filtro.getNUPROF() + "'))";					   
						   
			
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
						String sEstadoPROF = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF,QMCodigosControl.IESPROF,QMProvisiones.getEstado(conexion, filtro.getNUPROF()));
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
						//String sEstado  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34));
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
								filtro.getNUPROF(),
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
								QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34)),
								sFEEPAI,
								sFELIPG,
								sTipoPago,
								sUrgente);
						
						resultado.add(gastoencontrado);
						
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
				logger.error("ERROR NUPROF:|"+filtro.getNUPROF()+"|");
				
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
	
		
	public static ArrayList<GastoTabla> buscaGastosAbonadosEjecutablesProvisionPorFiltro(Connection conexion, GastoTabla filtro)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			//Condiciones de filtro
			String sCondicionCOACES = filtro.getCOACES().isEmpty()?"":QMGastos.CAMPO2 + " = '" + filtro.getCOACES() + "' AND ";
			String sCondicionCOGRUG = filtro.getCOGRUG().isEmpty()?"":QMGastos.CAMPO3 + " = '" + filtro.getCOGRUG() + "' AND ";
			String sCondicionCOTPGA = filtro.getCOTPGA().isEmpty()?"":QMGastos.CAMPO4 + " = '" + filtro.getCOTPGA() + "' AND ";
			String sCondicionCOSBGA = filtro.getCOSBGA().isEmpty()?"":QMGastos.CAMPO5 + " = '" + filtro.getCOSBGA() + "' AND ";
			String sCondicionFEDEVE = (filtro.getFEDEVE().equals("0") || filtro.getFEDEVE().isEmpty())?"":QMGastos.CAMPO7 + " = '" + filtro.getFEDEVE() + "' AND ";
			
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
						   " WHERE ("
						   + QMGastos.CAMPO10 + " = '"+ValoresDefecto.DEF_GASTO_ABONADO+"' AND "
						   + QMGastos.CAMPO34 + " = '" + ValoresDefecto.DEF_GASTO_ABONADO + "' AND "
						   + sCondicionCOACES
						   + sCondicionCOGRUG
						   + sCondicionCOTPGA
						   + sCondicionCOSBGA
						   + sCondicionFEDEVE
						   
						   + QMGastos.CAMPO1 + 
						   " IN (SELECT "
						   +  CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE ("
						   + CAMPO3 + " = '"+ ValoresDefecto.DEF_MOVIMIENTO_VALIDADO + "' AND "
						   + CAMPO2 + " = '"+ filtro.getNUPROF() + "' AND "
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
						
						String sGastoID = rs.getString(QMGastos.CAMPO1);
						String sEstadoPROF = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF,QMCodigosControl.IESPROF,QMProvisiones.getEstado(conexion, filtro.getNUPROF()));
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
						//String sEstado  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34));
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
								filtro.getNUPROF(),
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
								QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESGAST,QMCodigosControl.IESGAST,rs.getString(QMGastos.CAMPO34)),
								sFEEPAI,
								sFELIPG,
								sTipoPago,
								sUrgente);
						
						resultado.add(gastoencontrado);
						
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
				logger.error("ERROR NUPROF:|"+filtro.getNUPROF()+"|");
				
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
	
	public static ArrayList<GastoTabla> buscaGastosPagablesProvisionPorComunidad(Connection conexion, String sNUPROF, long liCodComunidad)
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
						   " WHERE ("
						   + QMGastos.CAMPO34 + " = '"+ ValoresDefecto.DEF_GASTO_AUTORIZADO + "' AND "
						   + QMGastos.CAMPO1 + 
						   " IN (SELECT "
						   +  CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE " 
						   + CAMPO2 + " = '"+ sNUPROF + "') AND "
						   + QMGastos.CAMPO2 + 
						   " IN (SELECT "
						   +  QMListaComunidadesActivos.CAMPO1 + 
						   " FROM " 
						   + QMListaComunidadesActivos.TABLA + 
						   " WHERE " 
						   + QMListaComunidadesActivos.CAMPO2 + " = "+ liCodComunidad + "))";
						   
			
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

					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				logger.error("ERROR NUPROF:|"+sNUPROF+"|");
				logger.error("ERROR COMUNIDAD:|"+liCodComunidad+"|");
				
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
	

	
	public static ArrayList<GastoTabla> buscaGastosPagablesProvision(Connection conexion, String sNUPROF)
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
						   " WHERE ("
						   + QMGastos.CAMPO34 + " = '"+ ValoresDefecto.DEF_GASTO_AUTORIZADO + "' AND "
						   + QMGastos.CAMPO1 + 
						   " IN (SELECT "
						   +  CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE " 
						   + CAMPO2 + " = '"+ sNUPROF + "'))";
			
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

					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				logger.error("ERROR NUPROF:|"+sNUPROF+"|");
				
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
	
	
	public static boolean resuelveGastos(Connection conexion, String sNUPROF) 
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
					" WHERE "
					+ CAMPO2 + " = '"+ sNUPROF +"'";
			
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

				logger.error("ERROR PROVISIÓN:|"+sNUPROF+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static long buscaCantidadGastos(Connection conexion, String sNUPROF)
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
					+ CAMPO2 + " = '" + sNUPROF + "'";
			
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

						liNumero = rs.getLong("COUNT("+CAMPO1+")");
						
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

				logger.error("ERROR PROVISION:|"+sNUPROF+"|");

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
	
	public static long buscaCantidadAbonosEjecutables(Connection conexion, String sNUPROF)
	{
		long liNumero = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
		
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT COUNT("+CAMPO2+") FROM " 
					+ TABLA + 
					" WHERE ("
					+ CAMPO2 + " = '" + sNUPROF + "' AND "
					+ CAMPO1 + " IN (SELECT "
					+ QMListaAbonosGastos.CAMPO1 + 
	   				" FROM " 
					+ QMListaAbonosGastos.TABLA +
	   				" WHERE " 
	   				+ QMListaAbonosGastos.CAMPO4 + " = '"+ ValoresDefecto.ABONO_EMITIDO + "'))";
			
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

						liNumero = rs.getLong("COUNT("+CAMPO2+")");
						
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

				logger.error("ERROR PROVISION:|"+sNUPROF+"|");

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
	
	public static long calculaValorProvision(Connection conexion, String sNUPROF)
	{
		long liValor = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					+ QMGastos.CAMPO15 + ","
					+ QMGastos.CAMPO16 +
					" FROM "
					+ QMGastos.TABLA + 
					" WHERE (" 
					+ QMGastos.CAMPO34 + " NOT IN ('" 
					+ ValoresDefecto.DEF_GASTO_ANULADO + "','"
					+ ValoresDefecto.DEF_GASTO_ABONADO +
					"') AND "
					+ QMGastos.CAMPO1 + " IN ( SELECT "
					+ CAMPO1 +
					" FROM " 
					+ TABLA + 
					" WHERE (" 
					+ CAMPO2 +	" = '" + sNUPROF + "' AND "
					+ CAMPO3 +	" <> '" + ValoresDefecto.DEF_MOVIMIENTO_ERRONEO +
					"')))";
			
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

						//bNegativo ? "-"+ sEuros + sCentimos : sEuros + sCentimos;
						
						liValor = rs.getString(QMGastos.CAMPO16).equals("-") ? liValor - rs.getLong(QMGastos.CAMPO15) : liValor + rs.getLong(QMGastos.CAMPO15);
						
						//dValor = dValor + Double.parseDouble(Utils.recuperaImporte(rs.getString(QMGastos.CAMPO17).equals("-"),rs.getString(QMGastos.CAMPO16)));
						
						logger.debug("Encontrado el registro!");
					}
					logger.debug("Valor de Provisión:|"+liValor+"|");
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR PROVISION:|"+sNUPROF+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return liValor;
	}
}
