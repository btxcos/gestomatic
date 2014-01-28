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
	public static final String CAMPO4 = "valor_total";
	public static final String CAMPO5 = "numero_gastos";
	public static final String CAMPO6 = "valor_autorizado";
	public static final String CAMPO7 = "gastos_autorizados";
	public static final String CAMPO8 = "fepfon";
	public static final String CAMPO9 = "fecha_envio";
	public static final String CAMPO10 = "fecha_autorizado";
	public static final String CAMPO11 = "fecha_pagado";
	public static final String CAMPO12 = "cod_estado";
	public static final String CAMPO13 = "usuario_modificacion";
	public static final String CAMPO14 = "fecha_modificacion";

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
					+ CAMPO14 +
					") VALUES ('" 
					+ NuevaProvision.getsNUPROF() + "','"
					+ NuevaProvision.getsCOSPAT() + "','"
					+ NuevaProvision.getsTAS() + "','"
					+ NuevaProvision.getsValorTolal() + "','"
					+ NuevaProvision.getsNumGastos() + "','"
					+ NuevaProvision.getsValorAutorizado() + "','"
					+ NuevaProvision.getsGastosAutorizados() + "','"
					+ NuevaProvision.getsFEPFON() + "','" 
					+ NuevaProvision.getsFechaEnvio() + "','"
					+ NuevaProvision.getsFechaAutorizado() + "','" 
					+ NuevaProvision.getsFechaPagado() + "','" 
					+ NuevaProvision.getsCodEstado() + "','"
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
					+ CAMPO4 + " = '" + provision.getsValorTolal() + "', " 
					+ CAMPO5 + " = '" + provision.getsNumGastos() + "', "
					+ CAMPO6 + " = '" + provision.getsValorAutorizado() + "', " 
					+ CAMPO7 + " = '" + provision.getsGastosAutorizados() + "', "
					+ CAMPO8 + " = '" + provision.getsFEPFON() + "', " 
					+ CAMPO9 + " = '" + provision.getsFechaEnvio() + "', "
					+ CAMPO10 + " = '" + provision.getsFechaAutorizado() + "', " 
					+ CAMPO11 + " = '" + provision.getsFechaPagado() + "', " 
					+ CAMPO12 + " = '" + provision.getsCodEstado() + "', "
					+ CAMPO13 + " = '" + sUsuario + "', " 
					+ CAMPO14 + " = '" + Utils.timeStamp() + "' " +					
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
		String sValorTolal = "";
		String sNumGastos = "";
		String sValorAutorizado = "";
		String sGastosAutorizados = "";	
		String sFEPFON = "";
		String sFechaEnvio = "";     
		String sFechaAutorizado = "";
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
					+ CAMPO12 +  
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
						sValorTolal = rs.getString(CAMPO4);
						sNumGastos = rs.getString(CAMPO5);
						sValorAutorizado = rs.getString(CAMPO6);
						sGastosAutorizados = rs.getString(CAMPO7);
						sFEPFON = rs.getString(CAMPO8);
						sFechaEnvio = rs.getString(CAMPO9);
						sFechaAutorizado = rs.getString(CAMPO10);
						sFechaPagado = rs.getString(CAMPO11);
						sEstado = rs.getString(CAMPO12);

						
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
				sValorTolal = "";
				sNumGastos = "";
				sValorAutorizado = "";
				sGastosAutorizados = "";	
				sFEPFON = "";
				sFechaEnvio = "";
				sFechaAutorizado = "";
				sFechaPagado = "";

				logger.error("ERROR NUPROF:|"+sNUPROF+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new Provision(sNUPROF, sCOSPAT, sTAS, sValorTolal, sNumGastos, sValorAutorizado, sGastosAutorizados, sFEPFON, sFechaEnvio, sFechaAutorizado, sFechaPagado, sEstado);
	}
	
	public static Provision getDetallesProvision(Connection conexion, String sNUPROF) 
	{
		String sCOSPAT = "";
		String sTAS = "";
		String sValorTolal = "";
		String sNumGastos = "";
		String sValorAutorizado = "";
		String sGastosAutorizados = "";	
		String sFEPFON = "";
		String sFechaEnvio = "";     
		String sFechaAutorizado = "";
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
					+ CAMPO12 +  
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
						sValorTolal = rs.getString(CAMPO4);
						sNumGastos = rs.getString(CAMPO5);
						sValorAutorizado = rs.getString(CAMPO6);
						sGastosAutorizados = rs.getString(CAMPO7);
						sFEPFON = rs.getString(CAMPO8);
						sFechaEnvio = rs.getString(CAMPO9);
						sFechaAutorizado = rs.getString(CAMPO10);
						sFechaPagado = rs.getString(CAMPO11);
						sEstado = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESPROF, QMCodigosControl.IESPROF, rs.getString(CAMPO12));

						
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
				sValorTolal = "";
				sNumGastos = "";
				sValorAutorizado = "";
				sGastosAutorizados = "";	
				sFEPFON = "";
				sFechaEnvio = "";
				sFechaAutorizado = "";
				sFechaPagado = "";

				logger.error("ERROR NUPROF:|"+sNUPROF+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new Provision(sNUPROF, sCOSPAT, sTAS, sValorTolal, sNumGastos, sValorAutorizado, sGastosAutorizados, sFEPFON, sFechaEnvio, sFechaAutorizado, sFechaPagado, sEstado);
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
					+ CAMPO10 + " = '" + sFechaAutorizado + "' " +
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
	
	public static boolean setFechaFacturado(Connection conexion, String sNUPROF, String sFechaPagado) 
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO11 + " = '" + sFechaPagado + "' " +
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
					+ CAMPO12 + " = '" + sEstado + "' " +
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
					+ CAMPO12  +
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
						
						sValidado = rs.getString(CAMPO12);

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
					+ CAMPO12 + " = '" + sEstado + "' AND "
					+ CAMPO9 + " = '0' AND "
					+ CAMPO8 + " <> '0'"+
					")";
			
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
					+ CAMPO12 + " <> '"+ ValoresDefecto.DEF_PROVISION_ABIERTA + 
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
					+ CAMPO12 + " = '" + ValoresDefecto.DEF_PROVISION_ABIERTA + "' AND "
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
					+ CAMPO12 + " = '" + sEstado + "' AND "
					+ CAMPO9 + " = '0'"+
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

						logger.debug(resultado.get(i)); 
						
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
			String sTAS = "";
			String sDTAS = "";
			String sCOSPAT = "";
			String sDCOSPAT = "";
			String sVALOR = "";
			String sGASTOS = "";

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO1 + ","
					+ CAMPO2 + ","
					+ CAMPO3 + ","
					+ CAMPO4 + ","
					+ CAMPO5 + 
					" FROM " + TABLA + 
					" WHERE ( " 
					+ CAMPO12 + " = '"+ sEstado + "' AND "
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
						sVALOR =   rs.getString(CAMPO4);
						sGASTOS =  rs.getString(CAMPO5);

						ProvisionTabla provisionencontrada = new ProvisionTabla(sNUPROF,sCOSPAT,sDCOSPAT,sTAS,sDTAS,sVALOR,sGASTOS);
						
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
			String sTAS = "";
			String sDTAS = "";
			String sCOSPAT = "";
			String sDCOSPAT = "";
			String sVALOR = "";
			String sGASTOS = "";
			
			String sCondicion = (sFEPFON.equals("0")) ? "" : CAMPO8 + " = '"+ sFEPFON + "' AND ";

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO1 + ","
					+ CAMPO2 + ","
					+ CAMPO3 + ","
					+ CAMPO4 + ","
					+ CAMPO5 + 
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
						sVALOR =   rs.getString(CAMPO4);
						sGASTOS =  rs.getString(CAMPO5);

						ProvisionTabla provisionencontrada = new ProvisionTabla(sNUPROF,sCOSPAT,sDCOSPAT,sTAS,sDTAS,sVALOR,sGASTOS);
						
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
			String sTAS = "";
			String sDTAS = "";
			String sCOSPAT = "";
			String sDCOSPAT = "";
			String sVALOR = "";
			String sGASTOS = "";
			
			String sCondicion = (sFEPFON.equals("0")) ? "" : CAMPO8 + " = '"+ sFEPFON + "' AND ";

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO1 + ","
					+ CAMPO2 + ","
					+ CAMPO3 + ","
					+ CAMPO4 + ","
					+ CAMPO5 + 
					" FROM " + TABLA + 
					" WHERE ( " 
					+ sCondicion
					+ CAMPO1 + " <> '"+ValoresDefecto.DEF_GASTO_PROVISION_CONEXION+ "' AND "
					+ CAMPO10 +" <> '"+ ValoresDefecto.CAMPO_SIN_INFORMAR +"') ";

			
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
						sVALOR =   rs.getString(CAMPO4);
						sGASTOS =  rs.getString(CAMPO5);

						ProvisionTabla provisionencontrada = new ProvisionTabla(sNUPROF,sCOSPAT,sDCOSPAT,sTAS,sDTAS,sVALOR,sGASTOS);
						
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
	
	public static ArrayList<ProvisionTabla> buscaProvisionesAutorizadasPorActivo(Connection conexion, String sCOACES)
	{
		ArrayList<ProvisionTabla> resultado = new ArrayList<ProvisionTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			String sNUPROF = "";
			String sTAS = "";
			String sDTAS = "";
			String sCOSPAT = "";
			String sDCOSPAT = "";
			String sVALOR = "";
			String sGASTOS = "";
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT " 
					+ CAMPO1 + "," 
					+ CAMPO2 + "," 
					+ CAMPO3 + "," 
					+ CAMPO4 + "," 
					+ CAMPO5 + 
					" FROM " 
					+ TABLA
					+ " WHERE ("
					+ CAMPO10 +" <> '"+ ValoresDefecto.CAMPO_SIN_INFORMAR +"' AND "
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
					+ QMGastos.CAMPO2 + " = '" + sCOACES + "')))";
					
						   
			
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
						sVALOR =   rs.getString(CAMPO4);
						sGASTOS =  rs.getString(CAMPO5);

						ProvisionTabla provisionencontrada = new ProvisionTabla(sNUPROF,sCOSPAT,sDCOSPAT,sTAS,sDTAS,sVALOR,sGASTOS);
						
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
						+ CAMPO12 + " <> '"+ ValoresDefecto.DEF_PROVISION_ABIERTA + "' AND " 
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
