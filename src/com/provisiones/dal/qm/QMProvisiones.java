package com.provisiones.dal.qm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;

import com.provisiones.misc.ValoresDefecto;
import com.provisiones.misc.Utils;
import com.provisiones.types.Provision;
import com.provisiones.types.tablas.ProvisionTabla;

public class QMProvisiones 
{
	private static Logger logger = LoggerFactory.getLogger(QMProvisiones.class.getName());
	
	public static final String TABLA = "pp001_provisiones_tbl";

	public static final String CAMPO1 = "nuprof_id";

	public static final String CAMPO2 = "cod_cospat";
	public static final String CAMPO3 = "cod_tas";
	public static final String CAMPO4 = "valor_total";
	public static final String CAMPO5 = "numero_gastos";
	public static final String CAMPO6 = "fepfon";
	public static final String CAMPO7 = "fecha_envio";
	public static final String CAMPO8 = "cod_estado";
	public static final String CAMPO9 = "usuario_modificacion";
	public static final String CAMPO10 = "fecha_modificacion";

	public static boolean addProvision(Provision NuevaProvision)

	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();
		String sUsuario = ConnectionManager.getUser();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
	    
		String sQuery = "INSERT INTO " 
				+ TABLA + 
				" (" 
				+ CAMPO1 + ","
				+ CAMPO2 + ","
				+ CAMPO3 + "," 
				+ CAMPO4 + ","
				+ CAMPO5 + ","
				+ CAMPO6 + ","
				+ CAMPO7 + ","
				+ CAMPO8 + ","
				+ CAMPO9 + ","
				+ CAMPO10 +
				") VALUES ('" 
				+ NuevaProvision.getsNUPROF() + "','"
				+ NuevaProvision.getsCOSPAT() + "','"
				+ NuevaProvision.getsTAS() + "','"
				+ NuevaProvision.getsValorTolal() + "','"
				+ NuevaProvision.getsNumGastos() + "','"
				+ NuevaProvision.getsFEPFON() + "','" 
				+ NuevaProvision.getsFechaValidacion() + "','" 
				+ NuevaProvision.getsCodEstado() + "','"
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
			logger.error("ERROR NUPROF:|"+NuevaProvision.getsNUPROF()+"|");

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

	public static boolean modProvision(Provision provision) 
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();
		String sUsuario = ConnectionManager.getUser();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO2 + " = '" + provision.getsCOSPAT() + "', "
				+ CAMPO3 + " = '" + provision.getsTAS() + "', "
				+ CAMPO4 + " = '" + provision.getsValorTolal() + "', " 
				+ CAMPO5 + " = '" + provision.getsNumGastos() + "', "
				+ CAMPO6 + " = '" + provision.getsFEPFON() + "', " 
				+ CAMPO7 + " = '" + provision.getsFechaValidacion() + "', " 
				+ CAMPO8 + " = '" + provision.getsCodEstado() + "', " 
				+ CAMPO9 + " = '" + sUsuario + "', " 
				+ CAMPO10 + " = '" + Utils.timeStamp() + "' " +					
				" WHERE " 
				+ CAMPO1 + " = '" + provision.getsNUPROF() + "'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);

			logger.debug("Ejecutada con exito!");

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR NUPROF:|"+provision.getsNUPROF()+"|");

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

	public static boolean delProvision(String sNUPROF) 
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "DELETE FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO1 + " = '" + sNUPROF + "'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR NUPROF:|"+sNUPROF+"|");

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

	public static Provision getProvision(String sNUPROF) 
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sCOSPAT = "";
		String sTAS = "";
		String sValorTolal = "";
		String sNumGastos = "";
		String sFEPFON = "";
		String sFechaValidacion = "";
		String sValidado = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO2 + "," 
				+ CAMPO3 + "," 
				+ CAMPO4 + "," 
				+ CAMPO5 + "," 
				+ CAMPO6 + "," 
				+ CAMPO7 + "," 
				+ CAMPO8 +
				" FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO1 + " = '" + sNUPROF + "'";
		
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

					sCOSPAT = rs.getString(CAMPO2);
					sTAS = rs.getString(CAMPO3);
					sValorTolal = rs.getString(CAMPO4);
					sNumGastos = rs.getString(CAMPO5);
					sFEPFON = rs.getString(CAMPO6);
					sFechaValidacion = rs.getString(CAMPO7);
					sValidado = rs.getString(CAMPO8);

					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO1+":|"+sNUPROF+"|");

				}
			}
			if (found == false) 
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
		//ConnectionManager.CloseDBConnection(conn);
		return new Provision(sNUPROF, sCOSPAT, sTAS, sValorTolal, sNumGastos, sFEPFON, sFechaValidacion, sValidado);
	}
	
	public static boolean setFechaEnvio(String sNUPROF, String sFechaEnvio) 
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;
		
		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO7 + " = '" + sFechaEnvio + "' " +
				" WHERE " 
				+ CAMPO1 + " = '" + sNUPROF + "'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);

			logger.debug("Ejecutada con exito!");

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR NUPROF:|"+sNUPROF+"|");

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
	
	public static long buscaCantidadProvisionesCerradasPendientes()
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		long liNumero = 0;

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT COUNT(*) FROM " 
				+ TABLA + 
				" WHERE " +
				"(" 
				+ CAMPO8 + " = '" + ValoresDefecto.DEF_BAJA + "' AND "
				+ CAMPO7 + " = '0'"+
				")";
		
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

					liNumero = rs.getLong("COUNT(*)");
					
					logger.debug("Encontrado el registro!");

					logger.debug("Numero de registros:|"+liNumero+"|");


				}
			}
			if (found == false) 
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
			Utils.closeResultSet(rs);;
			Utils.closeStatement(stmt);
		}

		//ConnectionManager.CloseDBConnection(conn);
		return liNumero;
	}
	
	public static boolean existeProvision(String sNUPROF) 
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO1  +
				" FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO1 + " = '"+ sNUPROF + "'";
		
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

					logger.debug("Encontrado el registro!");
					logger.debug(CAMPO1+":|"+sNUPROF+"|");

				}
			}
			if (found == false) 
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
		//ConnectionManager.CloseDBConnection(conn);
		return found;
	}
	
	public static boolean provisionCerrada(String sNUPROF) 
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO1  +
				" FROM " 
				+ TABLA + 
				" WHERE (" 
				+ CAMPO1 + " = '"+ sNUPROF + "' AND "
				+ CAMPO8 + " = '"+ ValoresDefecto.DEF_BAJA + 
				"')";
		
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

					logger.debug("Encontrado el registro!");
					logger.debug(CAMPO1+":|"+sNUPROF+"|");

				}
			}
			if (found == false) 
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
		//ConnectionManager.CloseDBConnection(conn);
		return found;
	}
	
	public static String getProvisionAbierta(String sCodCOSPAT, String sCodTAS) 
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sNUPROF = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO1 + 
				" FROM " 
				+ TABLA + 
				" WHERE " +
				"( " 
				+ CAMPO8 + " = '" + ValoresDefecto.DEF_ALTA + "' AND "
				+ CAMPO2 +" = '"+ sCodCOSPAT +"' AND "
				+ CAMPO3 +" = '"+ sCodTAS + "' AND "
				+ CAMPO1 +" <> 0)";
		
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

					sNUPROF = rs.getString(CAMPO1);


					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO1 + ":|"+sNUPROF+"|");

				}
			}
			if (found == false) 
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
		//ConnectionManager.CloseDBConnection(conn);
		return sNUPROF;
	}
	
	public static ArrayList<String>  getProvisionesCerradasPendientes() 
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		ArrayList<String> result = new ArrayList<String>();

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO1+ 
				" FROM " 
				+ TABLA + 
				" WHERE " +
				"(" 
				+ CAMPO8 + " = '" + ValoresDefecto.DEF_BAJA + "' AND "
				+ CAMPO7 + " = '0'"+
				")";
		
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

					logger.debug(result.get(i)); 
					
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
	
	public static ArrayList<ProvisionTabla> buscaProvisionesAbiertas() 
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		String sNUPROF = "";
		String sTAS = "";
		String sDTAS = "";
		String sCOSPAT = "";
		String sDCOSPAT = "";
		String sVALOR = "";
		String sGASTOS = "";

		ArrayList<ProvisionTabla> result = new ArrayList<ProvisionTabla>();

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO1 + ","
				+ CAMPO2 + ","
				+ CAMPO3 + ","
				+ CAMPO4 + ","
				+ CAMPO5 + 
				" FROM " + TABLA + 
				" WHERE ( " 
				+ CAMPO8 + " = '"+ ValoresDefecto.DEF_ALTA + "' AND "
				+CAMPO1+" <> '"+ValoresDefecto.DEF_GASTO_PROVISION_CONEXION+
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

					sNUPROF =  rs.getString(CAMPO1);
					sCOSPAT =  rs.getString(CAMPO2);
					sDCOSPAT =  QMCodigosControl.getDesCampo(QMCodigosControl.TSOCTIT,QMCodigosControl.ISOCTIT,sCOSPAT);
					sTAS =  rs.getString(CAMPO3);
					sDTAS =  QMCodigosControl.getDesCampo(QMCodigosControl.TTIACSA,QMCodigosControl.ITIACSA,sTAS);
					sVALOR =   rs.getString(CAMPO4);
					sGASTOS =  rs.getString(CAMPO5);

					ProvisionTabla provisionencontrada = new ProvisionTabla(sNUPROF,sCOSPAT,sDCOSPAT,sTAS,sDTAS,sVALOR,sGASTOS);
					
					result.add(provisionencontrada);
					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO1+":|"+sNUPROF+"|");

				}
			}
			if (found == false) 
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
		//ConnectionManager.CloseDBConnection(conn);
		return result;
	}
	

	
	public static String getUltimaProvisionCerrada(String sCodCOSPAT) 
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sNUPROF = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
					+ CAMPO1 + 
					" FROM " 
					+ TABLA + 
					" WHERE ( " 
					+ CAMPO8 + " = '"+ ValoresDefecto.DEF_BAJA + "' AND " 
					+ CAMPO2 +" = '"+ sCodCOSPAT +"') "+
					" order by " + CAMPO1 + " desc limit 0,1 ";
		
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

					sNUPROF = rs.getString(CAMPO1);

					logger.debug("Encontrado el registro!");
					logger.debug(CAMPO1+":|"+sNUPROF+"|");

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR sCodCOSPAT:|"+sCodCOSPAT+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return sNUPROF;
	}
	public static String getUltimaProvision() 
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sNUPROF = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
					+ CAMPO1 + 
					" FROM " 
					+ TABLA + 
					" order by " + CAMPO1 + " desc limit 0,1 ";
		
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

					sNUPROF = rs.getString(CAMPO1);


					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO1 + ":|"+sNUPROF+"|");

				}
			}
			if (found == false) 
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
		//ConnectionManager.CloseDBConnection(conn);
		return sNUPROF;
	}
}
