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
import com.provisiones.types.ProvisionTabla;

public class QMProvisiones 
{
	private static Logger logger = LoggerFactory.getLogger(QMProvisiones.class.getName());
	
	public static final String sTable = "provisiones_tbl";

	public static final String sField1 = "nuprof_id";

	public static final String sField2 = "cod_cospat";
	public static final String sField3 = "cod_tas";
	public static final String sField4 = "valor_total";
	public static final String sField5 = "numero_gastos";
	public static final String sField6 = "fepfon";
	public static final String sField7 = "fecha_envio";
	public static final String sField8 = "cod_estado";
	public static final String sField9 = "usuario_modificacion";
	public static final String sField10 = "fecha_modificacion";

	public static boolean addProvision(Provision NuevaProvision)

	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();
		String sUsuario = ValoresDefecto.DEF_USUARIO;

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
	    
		String sQuery = "INSERT INTO " 
				+ sTable + 
				" (" 
				+ sField1 + ","
				+ sField2 + ","
				+ sField3 + "," 
				+ sField4 + ","
				+ sField5 + ","
				+ sField6 + ","
				+ sField7 + ","
				+ sField8 + ","
				+ sField9 + ","
				+ sField10 +
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
			logger.error("ERROR: NUPROF:|{}|",NuevaProvision.getsNUPROF());

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static boolean modProvision(Provision provision) 
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();
		String sUsuario = ValoresDefecto.DEF_USUARIO;

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + " SET " 
					+ sField2 + " = '" + provision.getsCOSPAT() + "', "
					+ sField3 + " = '" + provision.getsTAS() + "', "
					+ sField4 + " = '" + provision.getsValorTolal() + "', " 
					+ sField5 + " = '" + provision.getsNumGastos() + "', "
					+ sField6 + " = '" + provision.getsFEPFON() + "', " 
					+ sField7 + " = '" + provision.getsFechaValidacion() + "', " 
					+ sField8 + " = '" + provision.getsCodEstado() + "', " 
					+ sField9 + " = '" + sUsuario + "', " 
					+ sField10 + " = '" + Utils.timeStamp() + "' " 					
					+ " WHERE " + sField1 + " = '" + provision.getsNUPROF() + "'");

			logger.debug("Ejecutada con exito!");

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: NUPROF:|{}|",provision.getsNUPROF());

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static boolean delProvision(String sNUPROF) 
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sNUPROF + "' )");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: NUPROF:|{}|",sNUPROF);

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
			
			bSalida = false;
		} 
		finally
		{

			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static Provision getProvision(String sNUPROF) 
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

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

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " 
					+ sField2 + "," 
					+ sField3 + "," 
					+ sField4 + "," 
					+ sField5 + "," 
					+ sField6 + "," 
					+ sField7 + "," 
					+ sField8 +
					" FROM " 
					+ sTable + 
					" WHERE " 
					+ sField1 + " = '" + sNUPROF + "'");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");



			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCOSPAT = rs.getString(sField2);
					sTAS = rs.getString(sField3);
					sValorTolal = rs.getString(sField4);
					sNumGastos = rs.getString(sField5);
					sFEPFON = rs.getString(sField6);
					sFechaValidacion = rs.getString(sField7);
					sValidado = rs.getString(sField8);

					
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",sField1,sNUPROF);

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: NUPROF:|{}|",sNUPROF);

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return new Provision(sNUPROF, sCOSPAT, sTAS, sValorTolal, sNumGastos, sFEPFON, sFechaValidacion, sValidado);
	}
	
	public static boolean setFechaEnvio(String sNUPROF, String sFechaEnvio) 
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;
		
		boolean bSalida = true;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + " SET " 
					+ sField7 + " = '" + sFechaEnvio + "' " 
					+ " WHERE " + sField1 + " = '" + sNUPROF + "'");

			logger.debug("Ejecutada con exito!");

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: NUPROF:|{}|",sNUPROF);

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	
	public static long buscaCantidadProvisionesCerradasPendientes()
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		long liNumero = 0;

		boolean found = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM " + sTable + 
					" WHERE " +
					"(" 
					+ sField8 + " = '" + ValoresDefecto.DEF_BAJA + "' AND "
					+ sField7 + " = '0'"+
					")");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					liNumero = rs.getLong("COUNT(*)");
					
					logger.debug("Encontrado el registro!");

					logger.debug("Numero de registros:|{}|",liNumero);


				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs);;
			Utils.closeStatement(stmt);
		}

		ConnectionManager.CloseDBConnection(conn);
		return liNumero;
	}
	
	public static boolean existeProvision(String sNUPROF) 
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		boolean found = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " + sField1  +
					" FROM " + sTable + " WHERE (" + sField1 + " = '"
					+ sNUPROF + "')");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");



			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",sField1,sNUPROF);

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: NUPROF:|{}|",sNUPROF);

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return found;
	}
	
	public static boolean provisionCerrada(String sNUPROF) 
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		boolean found = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " + sField1  +
					" FROM " + sTable + 
					" WHERE (" 
					+ sField1 + " = '"+ sNUPROF + "' AND "
					+ sField8 + " = '"+ ValoresDefecto.DEF_BAJA + "')");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");



			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",sField1,sNUPROF);

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: NUPROF:|{}|",sNUPROF);

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return found;
	}
	
	public static String getProvisionAbierta(String sCodCOSPAT, String sCodTAS) 
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sNUPROF = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " + sField1 + 
					" FROM " + sTable + 
					" WHERE " +
					"( " + sField8 + " = '" + ValoresDefecto.DEF_ALTA + "' AND "
					+ sField2 +" = '"+ sCodCOSPAT +"' AND "
					+ sField3 +" = '"+ sCodTAS +"')");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sNUPROF = rs.getString(sField1);


					
					logger.debug("Encontrado el registro!");

					logger.debug(sField1 + ":|{}|",sNUPROF);

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return sNUPROF;
	}
	
	public static ArrayList<String>  getProvisionesCerradasPendientes() 
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		ArrayList<String> result = new ArrayList<String>();

		boolean found = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField1+ "  FROM " + sTable + 
					" WHERE " +
					"(" 
					+ sField8 + " = '" + ValoresDefecto.DEF_BAJA + "' AND "
					+ sField7 + " = '0'"+
					")");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
		
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(sField1));
										
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
			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		ConnectionManager.CloseDBConnection(conn);
		return result;
	}
	
	public static ArrayList<ProvisionTabla> buscaProvisionesAbiertas() 
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

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

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " 
					+ sField1 + ","
					+ sField2 + ","
					+ sField3 + ","
					+ sField4 + ","
					+ sField5 + 
					" FROM " + sTable + 
					" WHERE ( " 
					+ sField8 + " = '"+ ValoresDefecto.DEF_ALTA + "' AND "
					+sField1+" <> '"+ValoresDefecto.DEF_GASTO_PROVISION_CONEXION+"' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");



			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sNUPROF =  rs.getString(sField1);
					sCOSPAT =  rs.getString(sField2);
					sDCOSPAT =  QMCodigosControl.getDesCampo(QMCodigosControl.TSOCTIT,QMCodigosControl.ISOCTIT,sCOSPAT);
					sTAS =  rs.getString(sField3);
					sDTAS =  QMCodigosControl.getDesCampo(QMCodigosControl.TTIACSA,QMCodigosControl.ITIACSA,sTAS);
					sVALOR =   rs.getString(sField4);
					sGASTOS =  rs.getString(sField5);

					ProvisionTabla provisionencontrada = new ProvisionTabla(sNUPROF,sCOSPAT,sDCOSPAT,sTAS,sDTAS,sVALOR,sGASTOS);
					
					result.add(provisionencontrada);
					
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",sField1,sNUPROF);

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return result;
	}
	

	
	public static String getUltimaProvisionCerrada(String sCodCOSPAT) 
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sNUPROF = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " + sField1 + 
					" FROM " + sTable + 
					" WHERE ( " + sField8 + " = '"
					+ ValoresDefecto.DEF_BAJA + "' AND " +
							      sField2 +" = '"+ sCodCOSPAT +"') "+
					" order by " + sField1 + " desc limit 0,1 ");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sNUPROF = rs.getString(sField1);

					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",sField1,sNUPROF);

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: sCodCOSPAT:|{}|",sCodCOSPAT);

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return sNUPROF;
	}
	public static String getUltimaProvision() 
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sNUPROF = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " + sField1 + 
					" FROM " + sTable + 
					" order by " + sField1 + " desc limit 0,1 ");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");



			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sNUPROF = rs.getString(sField1);


					
					logger.debug("Encontrado el registro!");

					logger.debug(sField1 + ":|{}|",sNUPROF);

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return sNUPROF;
	}
}
