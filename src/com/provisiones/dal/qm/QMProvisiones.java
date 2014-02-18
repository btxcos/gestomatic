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
import com.provisiones.dal.qm.listas.QMListaGastosProvisiones;

import com.provisiones.misc.ValoresDefecto;
import com.provisiones.misc.Utils;
import com.provisiones.types.Provision;
import com.provisiones.types.tablas.ProvisionTabla;

public final class QMProvisiones 
{
	private static Logger logger = LoggerFactory.getLogger(QMProvisiones.class.getName());
	
	public static final String TABLA = "pp002_provisiones_tbl";

	public static final String CAMPO1 = "nuprof_id";

	public static final String CAMPO2 = "cod_cospat";
	public static final String CAMPO3 = "cod_tas";
	public static final String CAMPO4 = "cogrug";
	public static final String CAMPO5 = "cotpga";
	public static final String CAMPO6 = "fepfon";
	public static final String CAMPO7 = "numero_gastos";
	public static final String CAMPO8 = "valor_total";
	public static final String CAMPO9 = "fecha_envio";
	public static final String CAMPO10 = "gastos_autorizados";
	public static final String CAMPO11 = "valor_autorizado";	
	public static final String CAMPO12 = "fecha_autorizado";
	public static final String CAMPO13 = "gastos_pagados";
	public static final String CAMPO14 = "valor_pagado";	
	public static final String CAMPO15 = "fecha_pagado";
	public static final String CAMPO16 = "cod_estado";
	public static final String CAMPO17 = "usuario_modificacion";
	public static final String CAMPO18 = "fecha_modificacion";
	public static final String CAMPO19 = "nota";

	private QMProvisiones(){}
	
	public static boolean addProvision(Connection conexion, Provision NuevaProvision)
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
					+ CAMPO5 + ","
					+ CAMPO6 + ","
					+ CAMPO7 + ","
					+ CAMPO8 + ","
					+ CAMPO9 + ","
					+ CAMPO10 + ","
					+ CAMPO11 + ","
					+ CAMPO12 + ","
					+ CAMPO13 + ","
					+ CAMPO14 + ","
					+ CAMPO15 + ","
					+ CAMPO16 + ","
					+ CAMPO17 + ","
					+ CAMPO18 + ","
					+ CAMPO19 +
					") VALUES ('" 
					+ NuevaProvision.getsNUPROF() + "','"
					+ NuevaProvision.getsCOSPAT() + "','"
					+ NuevaProvision.getsTAS() + "','"
					+ NuevaProvision.getsCOGRUG() + "','"
					+ NuevaProvision.getsCOTPGA() + "','"
					+ NuevaProvision.getsFEPFON() + "','" 
					+ NuevaProvision.getsNumGastos() + "','"
					+ NuevaProvision.getsValorTolal() + "','"
					+ NuevaProvision.getsFechaEnvio() + "','"
					+ NuevaProvision.getsGastosAutorizados() + "','"
					+ NuevaProvision.getsValorAutorizado() + "','"
					+ NuevaProvision.getsFechaAutorizado() + "','" 
					+ NuevaProvision.getsGastosPagados() + "','"
					+ NuevaProvision.getsValorPagado() + "','"
					+ NuevaProvision.getsFechaPagado() + "','" 
					+ NuevaProvision.getsCodEstado() + "','"
					+ sUsuario + "','"
					+ Utils.timeStamp() + "','"
					+ ValoresDefecto.CAMPO_ALFA_SIN_INFORMAR + 
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

				logger.error("ERROR NUPROF:|"+NuevaProvision.getsNUPROF()+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());	
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}		

		return bSalida;
	}

	public static boolean modProvision(Connection conexion, Provision provision) 
	{
		boolean bSalida = false;
		
		String sUsuario = ConnectionManager.getUser();

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO2 + " = '" + provision.getsCOSPAT() + "', "
					+ CAMPO3 + " = '" + provision.getsTAS() + "', "
					+ CAMPO4 + " = '" + provision.getsCOGRUG() + "', "
					+ CAMPO5 + " = '" + provision.getsCOTPGA() + "', "
					+ CAMPO6 + " = '" + provision.getsFEPFON() + "', "
					+ CAMPO7 + " = '" + provision.getsNumGastos() + "', "
					+ CAMPO8 + " = '" + provision.getsValorTolal() + "', " 
					+ CAMPO9 + " = '" + provision.getsFechaEnvio() + "', "
					+ CAMPO10 + " = '" + provision.getsGastosAutorizados() + "', " 
					+ CAMPO11 + " = '" + provision.getsValorAutorizado() + "', "
 					+ CAMPO12 + " = '" + provision.getsFechaAutorizado() + "', " 
 					+ CAMPO13 + " = '" + provision.getsGastosPagados() + "', " 
					+ CAMPO14 + " = '" + provision.getsValorPagado() + "', "
 					+ CAMPO15 + " = '" + provision.getsFechaPagado() + "', " 
					+ CAMPO16 + " = '" + provision.getsCodEstado() + "', "
					+ CAMPO17 + " = '" + sUsuario + "', " 
					+ CAMPO18 + " = '" + Utils.timeStamp() + "' " +					
					" WHERE " 
					+ CAMPO1 + " = '" + provision.getsNUPROF() + "'";


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

				logger.error("ERROR NUPROF:|"+provision.getsNUPROF()+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean delProvision(Connection conexion, String sNUPROF) 
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '" + sNUPROF + "'";
			
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

				logger.error("ERROR NUPROF:|"+sNUPROF+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
			}
		}		

		return bSalida;
	}

	public static Provision getProvision(Connection conexion, String sNUPROF) 
	{
		String sCOSPAT = "";
		String sTAS = "";
		String sCOGRUG = "";
		String sCOTPGA = "";
		String sFEPFON = "";
		String sNumGastos = "";
		String sValorTolal = "";
		String sFechaEnvio = "";
		String sGastosAutorizados = "";	
		String sValorAutorizado = "";
		String sFechaAutorizado = "";
		String sGastosPagados = "";	
		String sValorPagado = "";
		String sFechaPagado = ""; 
		String sEstado = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO2 + "," 
					+ CAMPO3 + "," 
					+ CAMPO4 + "," 
					+ CAMPO5 + "," 
					+ CAMPO6 + "," 
					+ CAMPO7 + "," 
					+ CAMPO8 + "," 
					+ CAMPO9 + ","
					+ CAMPO10 + "," 
					+ CAMPO11 + "," 
					+ CAMPO12 + "," 
					+ CAMPO13 + "," 
					+ CAMPO14 + "," 
					+ CAMPO15 + "," 
					+ CAMPO16 +  
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '" + sNUPROF + "'";
			
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

						sCOSPAT = rs.getString(CAMPO2);
						sTAS = rs.getString(CAMPO3);
						sCOGRUG = rs.getString(CAMPO4);
						sCOTPGA = rs.getString(CAMPO5);
						sFEPFON = rs.getString(CAMPO6);
						sNumGastos = rs.getString(CAMPO7);
						sValorTolal = rs.getString(CAMPO8);
						sFechaEnvio = rs.getString(CAMPO9);
						sGastosAutorizados = rs.getString(CAMPO10);
						sValorAutorizado = rs.getString(CAMPO11);
						sFechaAutorizado = rs.getString(CAMPO12);
						sGastosPagados = rs.getString(CAMPO13);
						sValorPagado = rs.getString(CAMPO14);
						sFechaPagado = rs.getString(CAMPO15);
						sEstado = rs.getString(CAMPO16);

						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+sNUPROF+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sCOSPAT = "";
				sTAS = "";
				sCOGRUG = "";
				sCOTPGA = "";
				sFEPFON = "";
				sNumGastos = "";
				sValorTolal = "";
				sFechaEnvio = "";
				sGastosAutorizados = "";	
				sValorAutorizado = "";
				sFechaAutorizado = "";
				sGastosPagados = "";	
				sValorPagado = "";
				sFechaPagado = ""; 
				sEstado = "";

				logger.error("ERROR NUPROF:|"+sNUPROF+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new Provision(sNUPROF, sCOSPAT, sTAS, sCOGRUG, sCOTPGA, sFEPFON, sNumGastos, sValorTolal, sFechaEnvio, sGastosAutorizados, sValorAutorizado, sFechaAutorizado, sGastosPagados, sValorPagado, sFechaPagado, sEstado);
	}
	
	public static Provision getDetallesProvision(Connection conexion, String sNUPROF) 
	{
		String sCOSPAT = "";
		String sTAS = "";
		String sCOGRUG = "";
		String sCOTPGA = "";
		String sFEPFON = "";
		String sNumGastos = "";
		String sValorTolal = "";
		String sFechaEnvio = "";
		String sGastosAutorizados = "";	
		String sValorAutorizado = "";
		String sFechaAutorizado = "";
		String sGastosPagados = "";	
		String sValorPagado = "";
		String sFechaPagado = ""; 
		String sEstado = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO2 + "," 
					+ CAMPO3 + "," 
					+ CAMPO4 + "," 
					+ CAMPO5 + "," 
					+ CAMPO6 + "," 
					+ CAMPO7 + "," 
					+ CAMPO8 + "," 
					+ CAMPO9 + ","
					+ CAMPO10 + "," 
					+ CAMPO11 + "," 
					+ CAMPO12 + "," 
					+ CAMPO13 + "," 
					+ CAMPO14 + "," 
					+ CAMPO15 + "," 
					+ CAMPO16 +  
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '" + sNUPROF + "'";
			
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

						sCOSPAT = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSPAT, QMCodigosControl.ICOSPAT, rs.getString(CAMPO2));
						sTAS = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TTIACSA, QMCodigosControl.ITIACSA, rs.getString(CAMPO3));
						
						if (rs.getInt(CAMPO4) == 0)
						{
							sCOGRUG = "SIN INFORMACION";
							sCOTPGA = "SIN INFORMACION";
						}
						else
						{
							sCOGRUG = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOGRUG, QMCodigosControl.ICOGRUG, rs.getString(CAMPO4));
							sCOTPGA = (rs.getInt(CAMPO5) == 0)? "VARIOS": QMCodigosControl.getDesCOTPGA(conexion, sCOGRUG, rs.getString(CAMPO5));
						}

						sFEPFON = rs.getString(CAMPO6);
						sNumGastos = rs.getString(CAMPO7);
						sValorTolal = rs.getString(CAMPO8);
						sFechaEnvio = rs.getString(CAMPO9);
						sGastosAutorizados = rs.getString(CAMPO10);
						sValorAutorizado = rs.getString(CAMPO11);
						sFechaAutorizado = rs.getString(CAMPO12);
						sGastosPagados = rs.getString(CAMPO13);
						sValorPagado = rs.getString(CAMPO14);
						sFechaPagado = rs.getString(CAMPO15);
						sEstado = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF, QMCodigosControl.IESPROF, rs.getString(CAMPO16));

						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+sNUPROF+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sCOSPAT = "";
				sTAS = "";
				sCOGRUG = "";
				sCOTPGA = "";
				sFEPFON = "";
				sNumGastos = "";
				sValorTolal = "";
				sFechaEnvio = "";
				sGastosAutorizados = "";	
				sValorAutorizado = "";
				sFechaAutorizado = "";
				sGastosPagados = "";	
				sValorPagado = "";
				sFechaPagado = ""; 
				sEstado = "";

				logger.error("ERROR NUPROF:|"+sNUPROF+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new Provision(sNUPROF, sCOSPAT, sTAS, sCOGRUG, sCOTPGA, sFEPFON, sNumGastos, sValorTolal, sFechaEnvio, sGastosAutorizados, sValorAutorizado, sFechaAutorizado, sGastosPagados, sValorPagado, sFechaPagado, sEstado);
	}
	
	public static boolean setFechaEnvio(Connection conexion, String sNUPROF, String sFechaEnvio) 
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO9 + " = '" + sFechaEnvio + "' " +
					" WHERE " 
					+ CAMPO1 + " = '" + sNUPROF + "'";
			
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

				logger.error("ERROR NUPROF:|"+sNUPROF+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean setFechaAutorizado(Connection conexion, String sNUPROF, String sFechaAutorizado) 
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO12 + " = '" + sFechaAutorizado + "' " +
					" WHERE " 
					+ CAMPO1 + " = '" + sNUPROF + "'";
			
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

				logger.error("ERROR NUPROF:|"+sNUPROF+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean setFechaPagado(Connection conexion, String sNUPROF, String sFechaPagado) 
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO15 + " = '" + sFechaPagado + "' " +
					" WHERE " 
					+ CAMPO1 + " = '" + sNUPROF + "'";
			
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

				logger.error("ERROR NUPROF:|"+sNUPROF+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean setEstado(Connection conexion, String sNUPROF, String sEstado) 
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO16 + " = '" + sEstado + "' " +
					" WHERE " 
					+ CAMPO1 + " = '" + sNUPROF + "'";
			
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

				logger.error("ERROR NUPROF:|"+sNUPROF+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	

	
	public static String getEstado(Connection conexion, String sNUPROF) 
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
					+ CAMPO16  +
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '"+ sNUPROF + "'";
			
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
						
						sValidado = rs.getString(CAMPO16);

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+sNUPROF+"|");
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

				logger.error("ERROR NUPROF:|"+sNUPROF+"|");

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

	public static boolean setNota(Connection conexion, String sNUPROF, String sNota)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO19 + " = '"+ sNota +"' "+
					" WHERE "
					+ CAMPO1 + " = '"+ sNUPROF +"'";
			
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

				logger.error("ERROR PROVISION:|"+sNUPROF+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

			} 
			finally 
			{

				Utils.closeStatement(stmt);
			}			
		}

		return bSalida;
	}
	
	public static String getNota(Connection conexion, String sNUPROF)
	{
		String sNota = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
						+ CAMPO19 + 
						" FROM " 
						+ TABLA + 
						" WHERE "
						+ CAMPO1 + " = '"+ sNUPROF +"'";
			
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

						sNota = rs.getString(CAMPO19);
						
						logger.debug(CAMPO1+":|"+sNUPROF+"|");
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO19+":|"+sNota+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sNota = "";
				
				logger.error("ERROR PROVISION:|"+sNUPROF+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sNota;
	}
	
	public static long buscaCantidadProvisionesCerradasPorEstado(Connection conexion, String sEstado)
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
					" WHERE " +
					"("
					+ CAMPO6 + " <> '0' AND "
					+ CAMPO9 + " = '0' AND "
					+ CAMPO16 + " = '" + sEstado + "'"
					+")";
			
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

						logger.debug("Numero de registros:|"+liNumero+"|");
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
	
	public static boolean existeProvision(Connection conexion, String sNUPROF) 
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

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
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+sNUPROF+"|");
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

				logger.error("ERROR NUPROF:|"+sNUPROF+"|");

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
	
	public static boolean provisionCerrada(Connection conexion, String sNUPROF) 
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO1  +
					" FROM " 
					+ TABLA + 
					" WHERE (" 
					+ CAMPO1 + " = '"+ sNUPROF + "' AND "
					+ CAMPO16 + " <> '"+ ValoresDefecto.DEF_PROVISION_ABIERTA + 
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

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+sNUPROF+"|");
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

				logger.error("ERROR NUPROF:|"+sNUPROF+"|");

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
	
	
	
	public static String getProvisionAbierta(Connection conexion, String sCodCOSPAT, String sCodTAS) 
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
					+ CAMPO1 + 
					" FROM " 
					+ TABLA + 
					" WHERE " +
					"( " 
					+ CAMPO16 + " = '" + ValoresDefecto.DEF_PROVISION_ABIERTA + "' AND "
					+ CAMPO2 +" = '"+ sCodCOSPAT +"' AND "
					+ CAMPO3 +" = '"+ sCodTAS + "' AND "
					+ CAMPO1 +" <> '"+ValoresDefecto.DEF_GASTO_PROVISION_CONEXION+"')";
			
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

						sNUPROF = rs.getString(CAMPO1);

						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1 + ":|"+sNUPROF+"|");
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
	
	public static ArrayList<String>  getProvisionesCerradasPorEstado(Connection conexion, String sEstado) 
	{
		//TODO Revisar y eliminar busqueda por campo 9
		ArrayList<String> resultado = new ArrayList<String>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO1+ 
					" FROM " 
					+ TABLA + 
					" WHERE (" 
					+ CAMPO16 + " = '" + sEstado + "' AND "
					+ CAMPO12 + " = '0'"+
					")";
			
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

						logger.debug("Resultado:|"+resultado.get(i)+"|"); 
						
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
				resultado = new ArrayList<String>(); 

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
	
	public static ArrayList<ProvisionTabla> buscaProvisionesPorEstado(Connection conexion, String sEstado) 
	{
		ArrayList<ProvisionTabla> resultado = new ArrayList<ProvisionTabla>();
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sNUPROF = "";
			String sCOSPAT = "";
			String sDCOSPAT = "";
			String sTAS = "";
			String sDTAS = "";
			String sCOGRUG = "";
			String sDCOGRUG = "";
			String sCOTPGA = "";
			String sDCOTPGA = "";
			String sFEPFON = "";
			String sGASTOS = "";
			String sVALOR = "";


			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO1 + ","
					+ CAMPO2 + ","
					+ CAMPO3 + ","
					+ CAMPO4 + ","
					+ CAMPO5 + ","
					+ CAMPO6 + ","
					+ CAMPO7 + ","
					+ CAMPO8 + 
					" FROM " + TABLA + 
					" WHERE ( " 
					+ CAMPO16 + " = '"+ sEstado + "' AND "
					+CAMPO1+" <> '"+ValoresDefecto.DEF_GASTO_PROVISION_CONEXION+
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

						sNUPROF =  rs.getString(CAMPO1);
						sCOSPAT =  rs.getString(CAMPO2);
						sDCOSPAT =  QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TSOCTIT,QMCodigosControl.ISOCTIT,sCOSPAT);
						sTAS =  rs.getString(CAMPO3);
						sDTAS =  QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TTIACSA,QMCodigosControl.ITIACSA,sTAS);
						sCOGRUG =  rs.getString(CAMPO4);
						sDCOGRUG =   QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOGRUG,QMCodigosControl.ICOGRUG,sCOGRUG);
						sCOTPGA =   rs.getString(CAMPO5);
						sDCOTPGA =   QMCodigosControl.getDesCOTPGA(conexion,sCOGRUG, sCOTPGA);
						sFEPFON =   rs.getString(CAMPO6);
						sGASTOS =  rs.getString(CAMPO7);
						sVALOR =   Utils.recuperaImporte(false,rs.getString(CAMPO8));

						ProvisionTabla provisionencontrada = new ProvisionTabla(sNUPROF,sCOSPAT,sDCOSPAT,sTAS,sDTAS,sCOGRUG,sDCOGRUG,sCOTPGA,sDCOTPGA,sFEPFON,sVALOR,sGASTOS);
						
						resultado.add(provisionencontrada);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+sNUPROF+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ProvisionTabla>();

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
	
	public static ArrayList<ProvisionTabla> buscaProvisionesPorFecha(Connection conexion, String sFEPFON) 
	{
		ArrayList<ProvisionTabla> resultado = new ArrayList<ProvisionTabla>();
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sNUPROF = "";
			String sCOSPAT = "";
			String sDCOSPAT = "";
			String sTAS = "";
			String sDTAS = "";
			String sCOGRUG = "";
			String sDCOGRUG = "";
			String sCOTPGA = "";
			String sDCOTPGA = "";
			String sGASTOS = "";
			String sVALOR = "";
			
			String sCondicion = (sFEPFON.equals("0")) ? "" : CAMPO8 + " = '"+ sFEPFON + "' AND ";

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO1 + ","
					+ CAMPO2 + ","
					+ CAMPO3 + ","
					+ CAMPO4 + ","
					+ CAMPO5 + ","
					//+ CAMPO6 + ","
					+ CAMPO7 + ","
					+ CAMPO8 + 
					" FROM " + TABLA + 
					" WHERE ( " 
					+ sCondicion
					+CAMPO1+" <> '"+ValoresDefecto.DEF_GASTO_PROVISION_CONEXION+
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

						sNUPROF =  rs.getString(CAMPO1);
						sCOSPAT =  rs.getString(CAMPO2);
						sDCOSPAT =  QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TSOCTIT,QMCodigosControl.ISOCTIT,sCOSPAT);
						sTAS =  rs.getString(CAMPO3);
						sDTAS =  QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TTIACSA,QMCodigosControl.ITIACSA,sTAS);
						sCOGRUG =  rs.getString(CAMPO4);
						sDCOGRUG =   QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOGRUG,QMCodigosControl.ICOGRUG,sCOGRUG);
						sCOTPGA =   rs.getString(CAMPO5);
						sDCOTPGA =   QMCodigosControl.getDesCOTPGA(conexion,sCOGRUG, sCOTPGA);
						//sFEPFON =   rs.getString(CAMPO6);
						sGASTOS =  rs.getString(CAMPO7);
						sVALOR =   Utils.recuperaImporte(false,rs.getString(CAMPO8));

						ProvisionTabla provisionencontrada = new ProvisionTabla(sNUPROF,sCOSPAT,sDCOSPAT,sTAS,sDTAS,sCOGRUG,sDCOGRUG,sCOTPGA,sDCOTPGA,sFEPFON,sVALOR,sGASTOS);
						
						resultado.add(provisionencontrada);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+sNUPROF+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ProvisionTabla>();

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
	
	public static ArrayList<ProvisionTabla> buscaProvisionesAutorizadasPorFecha(Connection conexion, String sFEPFON) 
	{
		ArrayList<ProvisionTabla> resultado = new ArrayList<ProvisionTabla>();
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sNUPROF = "";
			String sCOSPAT = "";
			String sDCOSPAT = "";
			String sTAS = "";
			String sDTAS = "";
			String sCOGRUG = "";
			String sDCOGRUG = "";
			String sCOTPGA = "";
			String sDCOTPGA = "";
			//String sFEPFON = "";
			String sGASTOS = "";
			String sVALOR = "";
			
			String sCondicion = (sFEPFON.equals("0")) ? "" : CAMPO6 + " = '"+ sFEPFON + "' AND ";

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO1 + ","
					+ CAMPO2 + ","
					+ CAMPO3 + ","
					+ CAMPO4 + ","
					+ CAMPO5 + ","
					//+ CAMPO6 + ","
					+ CAMPO7 + ","
					+ CAMPO8 + 
					" FROM " + TABLA + 
					" WHERE ( " 
					+ sCondicion
					+ CAMPO1 + " <> '"+ValoresDefecto.DEF_GASTO_PROVISION_CONEXION+ "' AND "
					+ CAMPO12 +" <> '"+ ValoresDefecto.CAMPO_NUME_SIN_INFORMAR +"') ";

			
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

						sNUPROF =  rs.getString(CAMPO1);
						sCOSPAT =  rs.getString(CAMPO2);
						sDCOSPAT =  QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TSOCTIT,QMCodigosControl.ISOCTIT,sCOSPAT);
						sTAS =  rs.getString(CAMPO3);
						sDTAS =  QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TTIACSA,QMCodigosControl.ITIACSA,sTAS);
						sCOGRUG =  rs.getString(CAMPO4);
						sDCOGRUG =   QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOGRUG,QMCodigosControl.ICOGRUG,sCOGRUG);
						sCOTPGA =   rs.getString(CAMPO5);
						sDCOTPGA =   QMCodigosControl.getDesCOTPGA(conexion,sCOGRUG, sCOTPGA);
						//sFEPFON =   rs.getString(CAMPO6);
						sGASTOS =  rs.getString(CAMPO7);
						sVALOR =   Utils.recuperaImporte(false,rs.getString(CAMPO8));

						ProvisionTabla provisionencontrada = new ProvisionTabla(sNUPROF,sCOSPAT,sDCOSPAT,sTAS,sDTAS,sCOGRUG,sDCOGRUG,sCOTPGA,sDCOTPGA,sFEPFON,sVALOR,sGASTOS);
						
						resultado.add(provisionencontrada);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+sNUPROF+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ProvisionTabla>();

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
	
	public static ArrayList<ProvisionTabla> buscaProvisionesAutorizadasPorActivo(Connection conexion, int iCOACES)
	{
		ArrayList<ProvisionTabla> resultado = new ArrayList<ProvisionTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			String sNUPROF = "";
			String sCOSPAT = "";
			String sDCOSPAT = "";
			String sTAS = "";
			String sDTAS = "";
			String sCOGRUG = "";
			String sDCOGRUG = "";
			String sCOTPGA = "";
			String sDCOTPGA = "";
			String sFEPFON = "";
			String sGASTOS = "";
			String sVALOR = "";

			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT " 
					+ CAMPO1 + ","
					+ CAMPO2 + ","
					+ CAMPO3 + ","
					+ CAMPO4 + ","
					+ CAMPO5 + ","
					+ CAMPO6 + ","
					+ CAMPO7 + ","
					+ CAMPO8 + 
					" FROM " 
					+ TABLA
					+ " WHERE ("
					+ CAMPO12 +" <> '"+ ValoresDefecto.CAMPO_NUME_SIN_INFORMAR +"' AND "
					+ CAMPO1 + " IN (SELECT "
					+ QMListaGastosProvisiones.CAMPO2 +
					" FROM "
					+ QMListaGastosProvisiones.TABLA +
					" WHERE "
					+ QMListaGastosProvisiones.CAMPO1 + 
					" IN (SELECT "
					+ QMGastos.CAMPO1 + 
					" FROM " 
					+ QMGastos.TABLA + 
					" WHERE "
					+ QMGastos.CAMPO2 + " = '" + iCOACES + "')))";
					
						   
			
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
						
						sNUPROF =  rs.getString(CAMPO1);
						sCOSPAT =  rs.getString(CAMPO2);
						sDCOSPAT =  QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TSOCTIT,QMCodigosControl.ISOCTIT,sCOSPAT);
						sTAS =  rs.getString(CAMPO3);
						sDTAS =  QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TTIACSA,QMCodigosControl.ITIACSA,sTAS);
						sCOGRUG =  rs.getString(CAMPO4);
						sDCOGRUG =   QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOGRUG,QMCodigosControl.ICOGRUG,sCOGRUG);
						sCOTPGA =   rs.getString(CAMPO5);
						sDCOTPGA =   QMCodigosControl.getDesCOTPGA(conexion,sCOGRUG, sCOTPGA);
						sFEPFON =   rs.getString(CAMPO6);
						sGASTOS =  rs.getString(CAMPO7);
						sVALOR =   Utils.recuperaImporte(false,rs.getString(CAMPO8));


						ProvisionTabla provisionencontrada = new ProvisionTabla(sNUPROF,sCOSPAT,sDCOSPAT,sTAS,sDTAS,sCOGRUG,sDCOGRUG,sCOTPGA,sDCOTPGA,sFEPFON,sVALOR,sGASTOS);
						
						resultado.add(provisionencontrada);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+sNUPROF+"|");

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
	
	public static String getUltimaProvisionCerrada(Connection conexion, String sCodCOSPAT) 
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
						+ CAMPO1 + 
						" FROM " 
						+ TABLA + 
						" WHERE ( " 
						+ CAMPO16 + " <> '"+ ValoresDefecto.DEF_PROVISION_ABIERTA + "' AND " 
						+ CAMPO2 +" = '"+ sCodCOSPAT +"') "+
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

						sNUPROF = rs.getString(CAMPO1);

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+sNUPROF+"|");
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

				logger.error("ERROR sCodCOSPAT:|"+sCodCOSPAT+"|");

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
	public static String getUltimaProvision(Connection conexion) 
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
						+ CAMPO1 + 
						" FROM " 
						+ TABLA + 
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

						sNUPROF = rs.getString(CAMPO1);

						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1 + ":|"+sNUPROF+"|");
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
}
