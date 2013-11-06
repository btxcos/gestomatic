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


	public static boolean addRelacionGastoProvision(String sCodGasto, String sCodNUPROF) 
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		String sUsuario = ValoresDefecto.DEF_USUARIO;

		conn = ConnectionManager.getDBConnection();
		
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
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR GASTO:|"+sCodGasto+"|");
			logger.error("ERROR PROVISION:|"+sCodNUPROF+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	
	public static boolean delRelacionGastoProvision(String sCodGasto) 
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "DELETE FROM " 
				+ TABLA + 
				" WHERE "
				+ CAMPO1 + " = '" + sCodGasto +"'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR GASTO:|"+sCodGasto+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	
	public static boolean existeRelacionGastoProvision(String sCodGasto, String sCodNUPROF)
	{
		Statement stmt = null;
		ResultSet rs = null;

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
				+ CAMPO1 + 
				" FROM " 
				+ TABLA + 
				" WHERE ("	
				+ CAMPO1  + " = '"+ sCodGasto +"' AND "
				+ CAMPO2  + " = '"+ sCodNUPROF + 
				"' )";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement(sQuery);


			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR GASTO:|"+sCodGasto+"|");
			logger.error("ERROR PROVISION:|"+sCodNUPROF+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return found;
	}
	
	public static boolean setRevisado(String sCodGasto, String sRevisado)
	{
		Statement stmt = null;
		boolean bSalida = true;
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO3 + " = '"+ sRevisado + "' "+
				" WHERE "
				+ CAMPO2 + " = '"+ sCodGasto +"'";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Gasto:|"+sCodGasto+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	
	public static String getRevisado(String sCodMovimiento)
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sRevisado = "";

		Connection conn = null;

		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO3 + 
				" FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO2 + " = '" + sCodMovimiento + "'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sRevisado = rs.getString(CAMPO3);

					logger.debug("Encontrado el registro!");
					logger.debug(CAMPO2+":|"+sCodMovimiento+"|");
					logger.debug(CAMPO3+":|"+sRevisado+"|");
				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Gasto:|"+sCodMovimiento+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		//ConnectionManager.CloseDBConnection(conn);
		return sRevisado;
	}
	
	
	public static String getProvisionDeGasto(String sCodGasto)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sCodNUPROF = "";

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
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
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement(sQuery);


			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCodNUPROF = rs.getString(CAMPO2);
					
					
					logger.debug("Encontrado el registro!");
					logger.debug(CAMPO2+":|"+sCodNUPROF+"|");

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR GASTO:|"+sCodGasto+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return sCodNUPROF;
	}
	
	public static String getProvisionDeMovimiento(String sCodMovimiento)
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sProvision = "";

		Connection conn = null;

		conn = ConnectionManager.getDBConnection();
		
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
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sProvision = rs.getString(CAMPO2);

					logger.debug("Encontrado el registro!");
					logger.debug(QMListaGastos.CAMPO2+":|"+sCodMovimiento+"|");
					logger.debug(CAMPO2+":|"+sProvision+"|");
				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Gasto:|"+sCodMovimiento+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		//ConnectionManager.CloseDBConnection(conn);
		return sProvision;
	}



	public static ArrayList<String>  buscaGastosPorProvision(String sCodNUPROF) 
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	
		
		ArrayList<String> result = new ArrayList<String>(); 
		Connection conn = null;

		conn = ConnectionManager.getDBConnection();
		
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
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(CAMPO1));

					logger.debug("Encontrado el registro!");
					logger.debug(CAMPO2+":|"+sCodNUPROF+"|");
					logger.debug(CAMPO1+":|"+result.get(i)+"|");
					
					i++;
				}
			}
			if (found == false) 
			{
				result = new ArrayList<String>(); 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR NUPROF:|"+sCodNUPROF+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		//ConnectionManager.CloseDBConnection(conn);
		return result;
	}

	public static ArrayList<String> buscaGastosSinValidarEnProvision(String sNUPROF) 
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		String sGastoID = "0";

		ArrayList<String> result = new ArrayList<String>();

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO1 + 
				" FROM " + TABLA + 
				" WHERE ( " 
				+ CAMPO2 + " = '"+ sNUPROF + "' AND "
				+ CAMPO1+ 
				" IN (SELECT "
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
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");



			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sGastoID =  rs.getString(CAMPO1);
					
					result.add(sGastoID);
					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO1+":|"+sGastoID+"|");

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR PROVISION:|"+sNUPROF+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);

		return result;
	}
	
	public static boolean resuelveGastos(String sNUPROF) 
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;
		
		boolean bSalida = true;

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
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR PROVISIÓN:|"+sNUPROF+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	
	public static long buscaCantidadGastos(String sNUPROF)
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		long liNumero = 0;

		Connection conn = null;

		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT COUNT(*) FROM " 
				+ TABLA + 
				" WHERE "
				+ CAMPO2 + " = '" + sNUPROF + "'";

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					liNumero = rs.getLong("COUNT(*)");
					
					logger.debug("Encontrado el registro!");

					logger.debug( "Numero de registros:|"+liNumero+"|");


				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR PROVISION:|"+sNUPROF+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		//ConnectionManager.CloseDBConnection(conn);
		return liNumero;
	}
	
	public static double calculaValorProvision(String sNUPROF)
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		double dValor = 0;

		Connection conn = null;

		conn = ConnectionManager.getDBConnection();
		
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
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					dValor = dValor + Double.parseDouble(Utils.recuperaImporte(rs.getString(QMGastos.CAMPO17).equals("-"),rs.getString(QMGastos.CAMPO16)));
					
					logger.debug("Encontrado el registro!");
				}
				logger.debug("Valor de Provisión:|"+dValor+"|");
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR PROVISION:|"+sNUPROF+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		//ConnectionManager.CloseDBConnection(conn);
		return dValor;
	}
}
