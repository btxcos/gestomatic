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
import com.provisiones.dal.qm.QMGastos;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

public class QMListaGastosProvisiones 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaGastosProvisiones.class.getName());
	
	static String TABLA = "pp001_lista_gastos_provisiones_multi";

	static String CAMPO1 = "cod_gasto";
	static String CAMPO2 = "cod_nuprof";
	static String CAMPO3 = "cod_revisado";
	static String CAMPO4 = "usuario_alta";
	static String CAMPO5 = "fecha_alta";


	public static boolean addRelacionGastoProvision(Connection conexion, String sCodGasto, String sCodNUPROF) 
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
					+ sCodGasto + "','"
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
				
				logger.error("ERROR GASTO:|"+sCodGasto+"|");
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
	
	public static boolean delRelacionGastoProvision(Connection conexion, String sCodGasto) 
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO1 + " = '" + sCodGasto +"'";
			
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

				logger.error("ERROR GASTO:|"+sCodGasto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
				
			} 
			finally 
			{

				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean existeRelacionGastoProvision(Connection conexion, String sCodGasto, String sCodNUPROF)
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
					+ CAMPO1  + " = '"+ sCodGasto +"' AND "
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

				logger.error("ERROR GASTO:|"+sCodGasto+"|");
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
	
	public static boolean setRevisado(Connection conexion, String sCodGasto, String sRevisado)
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
					" WHERE "
					+ CAMPO1 + " = '"+ sCodGasto +"'";
			
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

				logger.error("ERROR Gasto:|"+sCodGasto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static String getRevisado(Connection conexion, String sCodGasto)
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
					+ CAMPO1 + " = '" + sCodGasto + "'";
			
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
						logger.debug(CAMPO1+":|"+sCodGasto+"|");
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

				logger.error("ERROR Gasto:|"+sCodGasto+"|");

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
	
	
	public static String getProvisionDeGasto(Connection conexion, String sCodGasto)
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
					+ CAMPO1  + " = '"+ sCodGasto +"'";
			
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

				logger.error("ERROR GASTO:|"+sCodGasto+"|");

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
	
	public static String getProvisionDeMovimiento(Connection conexion, String sCodMovimiento)
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
					"IN (SELECT "
					+ QMListaGastos.CAMPO1 +
					" FROM " 
					+ QMListaGastos.TABLA +
					" WHERE "
					+ QMListaGastos.CAMPO2  + " = '"+ sCodMovimiento +"' )";
			
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
						logger.debug(QMListaGastos.CAMPO2+":|"+sCodMovimiento+"|");
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

				logger.error("ERROR Gasto:|"+sCodMovimiento+"|");

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

	public static ArrayList<String>  buscaGastosPorProvision(Connection conexion, String sCodNUPROF) 
	{
		ArrayList<String> resultado = new ArrayList<String>(); 

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
				
				int i = 0;
				
				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						resultado.add(rs.getString(CAMPO1));

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO2+":|"+sCodNUPROF+"|");
						logger.debug(CAMPO1+":|"+resultado.get(i)+"|");
						
						i++;
					}
				}
				if (!bEncontrado) 
				{
					resultado = new ArrayList<String>(); 
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<String>();

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

	public static ArrayList<String> buscaGastosSinValidarEnProvision(Connection conexion, String sNUPROF) 
	{
		ArrayList<String> resultado = new ArrayList<String>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sGastoID = "0";

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

						sGastoID =  rs.getString(CAMPO1);
						
						resultado.add(sGastoID);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+sGastoID+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<String>();

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
			
			String sQuery = "SELECT COUNT(*) FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO2 + " = '" + sNUPROF + "'";

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
	
	public static double calculaValorProvision(Connection conexion, String sNUPROF)
	{
		double dValor = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					+ QMGastos.CAMPO16 + ","
					+ QMGastos.CAMPO17 +
					" FROM "
					+ QMGastos.TABLA + 
					" WHERE (" 
					+ QMGastos.CAMPO35 + " NOT IN ('" 
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

						dValor = dValor + Double.parseDouble(Utils.recuperaImporte(rs.getString(QMGastos.CAMPO17).equals("-"),rs.getString(QMGastos.CAMPO16)));
						
						logger.debug("Encontrado el registro!");
					}
					logger.debug("Valor de Provisión:|"+dValor+"|");
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

		return dValor;
	}
}
