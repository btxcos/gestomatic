package com.provisiones.dal.qm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.qm.listas.QMListaComunidades;
import com.provisiones.dal.qm.listas.QMListaComunidadesActivos;
import com.provisiones.dal.qm.listas.QMListaCuotas;
import com.provisiones.dal.qm.listas.QMListaGastosProvisiones;
import com.provisiones.dal.qm.listas.QMListaImpuestos;
import com.provisiones.dal.qm.listas.QMListaReferencias;
import com.provisiones.dal.qm.registros.QMRegistroActivos;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.informes.CierreInforme;
import com.provisiones.types.informes.RangoAnual;


public class QMInformes 
{
	private static Logger logger = LoggerFactory.getLogger(QMInformes.class.getName());
	
	private QMInformes(){}
	
	public static String getActivosTotales(Connection conexion)
	{
		String sActivosTotales = "0";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT COUNT("
					   + QMActivos.CAMPO3  +        
					   ") FROM " 
					   + QMActivos.TABLA;
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sActivosTotales = rs.getString("COUNT("+QMActivos.CAMPO3+")");

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sActivosTotales = "0";

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sActivosTotales;
	}
	
	public static String getActivosGestionadosTotales(Connection conexion)
	{
		String sActivosGestionados = "0";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT COUNT(DISTINCT("
					   + QMGastos.CAMPO2  +        
					   ")) FROM " 
					   + QMGastos.TABLA;
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sActivosGestionados = rs.getString("COUNT(DISTINCT("+QMGastos.CAMPO2+"))");

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sActivosGestionados = "0";

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sActivosGestionados;
	}
	
	public static String getActivosGestionadosUltimoMes(Connection conexion)
	{
		String sActivosGestionadosUltimoMes = "0";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT COUNT(DISTINCT("
					   + QMGastos.CAMPO2  +        
					   ")) FROM " 
					   + QMGastos.TABLA+
					   " WHERE "
					   + QMGastos.CAMPO7 + " > " + Utils.primeroDeMes();
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sActivosGestionadosUltimoMes = rs.getString("COUNT(DISTINCT("+QMGastos.CAMPO2+"))");

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sActivosGestionadosUltimoMes = "0";

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sActivosGestionadosUltimoMes;
	}
	
	public static String getActivosVendidosTotales(Connection conexion)
	{
		String sActivosVendidosTotales = "0";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT COUNT("
					   + QMActivos.CAMPO3  +        
					   ") FROM " 
					   + QMActivos.TABLA + 
					   " WHERE "
					   + QMActivos.CAMPO71 + " > 0";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sActivosVendidosTotales = rs.getString("COUNT("+QMActivos.CAMPO3+")");

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sActivosVendidosTotales = "0";

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sActivosVendidosTotales;
	}
	
	public static String getGastosTotales(Connection conexion)
	{
		String sGastosTotales = "0";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT COUNT("
					   + QMGastos.CAMPO3  +        
					   ") FROM " 
					   + QMGastos.TABLA;
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sGastosTotales = rs.getString("COUNT("+QMGastos.CAMPO3+")");

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sGastosTotales = "0";

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sGastosTotales;
	}

	public static String getGastosEstado(Connection conexion, String sEstado)
	{
		String sNumGastos = "0";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT COUNT("
					   + QMGastos.CAMPO3  +        
					   ") FROM " 
					   + QMGastos.TABLA +
					   " WHERE "
					   + QMGastos.CAMPO34 + " = " + sEstado;
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sNumGastos = rs.getString("COUNT("+QMGastos.CAMPO3+")");

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sNumGastos = "0";

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sNumGastos;
	}
	
	public static String getValorTotalGastos(Connection conexion)
	{
		String sValorTotal = "0";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT SUM("
					   + QMGastos.CAMPO33  +        
					   ") FROM " 
					   + QMGastos.TABLA;
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sValorTotal = rs.getString("SUM("+QMGastos.CAMPO33+")");

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sValorTotal = "0";

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sValorTotal;
	}
	
	public static String getTotalGastosTipoEstado(Connection conexion, String sTipoGastos, String sEstadoGasto)
	{
		String sValorTotal = "0";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;
			
			String sCondicionTipoGasto = "";
			
			if (sTipoGastos.equals(ValoresDefecto.DEF_GASTO_COMUNIDADES) 
					||sTipoGastos.equals(ValoresDefecto.DEF_GASTO_SUMINISTROS))
			{
				sCondicionTipoGasto = QMGastos.CAMPO3 + " = " + ValoresDefecto.DEF_COGRUG_PENDIENTES +
						" AND (" + QMGastos.CAMPO4 + " = " + ValoresDefecto.DEF_COTPGA_COMUNIDADES +
						" OR "+ QMGastos.CAMPO4 + " = " + ValoresDefecto.DEF_COTPGA_SUMINISTROS + ")";
			}
			else if (sTipoGastos.equals(ValoresDefecto.DEF_GASTO_IMPUESTOS)
					||sTipoGastos.equals(ValoresDefecto.DEF_GASTO_PLUSVALIAS))
			{
				sCondicionTipoGasto = "(" + QMGastos.CAMPO3 + " = " + ValoresDefecto.DEF_COGRUG_PENDIENTES +
						" AND " + QMGastos.CAMPO4 + " = " + ValoresDefecto.DEF_COTPGA_IMPUESTO + 
						") OR ("+ QMGastos.CAMPO3 + " = " + ValoresDefecto.DEF_COGRUG_COMPRAVENTA + 
						" AND " + QMGastos.CAMPO4 + " = " + ValoresDefecto.DEF_COTPGA_PLUSVALIA + ")";
			}
			else
			{
				sCondicionTipoGasto = QMGastos.CAMPO3 + " = " + ValoresDefecto.DEF_COGRUG_ACCIONES +
						" OR ("+ QMGastos.CAMPO3 + " = " + ValoresDefecto.DEF_COGRUG_COMPRAVENTA + 
						" AND " + QMGastos.CAMPO4 + " <> " + ValoresDefecto.DEF_COTPGA_PLUSVALIA +
						") OR "+ QMGastos.CAMPO3 + " = " + ValoresDefecto.DEF_COGRUG_ACCIONES;
			}
			
			String sCondicionEstado = sEstadoGasto.isEmpty()?"": ") AND (" +QMGastos.CAMPO34 + " = " + sEstadoGasto;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT COUNT("
					   + QMGastos.CAMPO3  +        
					   ") FROM " 
					   + QMGastos.TABLA +
					   " WHERE ("
					   + sCondicionTipoGasto + 
					   sCondicionEstado +
					   ")";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sValorTotal = rs.getString("COUNT("+QMGastos.CAMPO3+")");

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sValorTotal = "0";

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sValorTotal;
	}
	
	public static String getProvisionesTotales(Connection conexion)
	{
		String sProvisionesTotales = "0";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT COUNT("
					   + QMProvisiones.CAMPO4  +        
					   ") FROM " 
					   + QMProvisiones.TABLA;
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sProvisionesTotales = rs.getString("COUNT("+QMProvisiones.CAMPO4+")");

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sProvisionesTotales = "0";

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sProvisionesTotales;
	}
	
	public static String getProvisionesEstado(Connection conexion, String sEstado)
	{
		String sNumProvisiones = "0";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT COUNT("
					   + QMProvisiones.CAMPO4  +        
					   ") FROM " 
					   + QMProvisiones.TABLA +
					   " WHERE "
					   + QMProvisiones.CAMPO21 + " = '" + sEstado + "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sNumProvisiones = rs.getString("COUNT("+QMProvisiones.CAMPO4+")");

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sNumProvisiones = "0";

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sNumProvisiones;
	}
	
	public static String getGastosEnProvisionesTotales(Connection conexion)
	{
		String sSuma = "0";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT SUM("
					   + QMProvisiones.CAMPO7  +        
					   ") FROM " 
					   + QMProvisiones.TABLA;
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sSuma = rs.getString("SUM("+QMProvisiones.CAMPO7+")");

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sSuma = "0";

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sSuma;
	}
	
	public static String getValorProvisionado(Connection conexion)
	{
		String sValor = "0";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT SUM("
					   + QMProvisiones.CAMPO8  +        
					   ") FROM " 
					   + QMProvisiones.TABLA;
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sValor = rs.getString("SUM("+QMProvisiones.CAMPO8+")");

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sValor = "0";

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sValor;
	}
	
	public static String getValorProvisionadoEstado(Connection conexion, String sEstado)
	{
		String sNumProvisiones = "0";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;
			
			String sCondicionCampo = "";
			
			if (sEstado.equals(ValoresDefecto.DEF_PROVISION_AUTORIZADA))
			{
				sCondicionCampo = QMProvisiones.CAMPO11;
			}
			else if (sEstado.equals(ValoresDefecto.DEF_PROVISION_PAGADA))
			{
				sCondicionCampo = QMProvisiones.CAMPO14;
			}
			else
			{
				sCondicionCampo = QMProvisiones.CAMPO19;
			}
			
			String sCondicionEstado = sEstado.isEmpty()?"":" WHERE " + QMProvisiones.CAMPO21 + " = '" + sEstado + "'";

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT SUM("
					   + sCondicionCampo +        
					   ") FROM " 
					   + QMProvisiones.TABLA +
					   sCondicionEstado;
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sNumProvisiones = rs.getString("SUM("+sCondicionCampo+")");

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sNumProvisiones = "0";

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sNumProvisiones;
	}
	
	public static String getComunidadesTotales(Connection conexion)
	{
		String sComunidadesTotales = "0";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT COUNT("
					   + QMComunidades.CAMPO2  +        
					   ") FROM " 
					   + QMComunidades.TABLA;
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sComunidadesTotales = rs.getString("COUNT("+QMComunidades.CAMPO2+")");

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sComunidadesTotales = "0";

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sComunidadesTotales;
	}

	public static String getCuotasTotales(Connection conexion)
	{
		String sCuotasTotales = "0";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT COUNT("
					   + QMCuotas.CAMPO5  +        
					   ") FROM " 
					   + QMCuotas.TABLA;
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sCuotasTotales = rs.getString("COUNT("+QMCuotas.CAMPO5+")");

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sCuotasTotales = "0";

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sCuotasTotales;
	}
	
	public static String getReferenciasCatastralesTotales(Connection conexion)
	{
		String sReferenciasCatastralesTotales = "0";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT COUNT("
					   + QMReferencias.CAMPO10  +        
					   ") FROM " 
					   + QMReferencias.TABLA;
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sReferenciasCatastralesTotales = rs.getString("COUNT("+QMReferencias.CAMPO10+")");

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sReferenciasCatastralesTotales = "0";

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sReferenciasCatastralesTotales;
	}
	
	public static String getRecursosTotales(Connection conexion)
	{
		String sRecursosTotales = "0";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT COUNT("
					   + QMImpuestos.CAMPO3  +        
					   ") FROM " 
					   + QMImpuestos.TABLA;
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sRecursosTotales = rs.getString("COUNT("+QMImpuestos.CAMPO3+")");

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sRecursosTotales = "0";

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sRecursosTotales;
	}
	
	public static ArrayList<String> buscaActivosGestionadosEnRango(Connection conexion, ArrayList<RangoAnual> rango)
	{
		ArrayList<String> resultado = new ArrayList<String>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			for(int i=0; i<rango.size(); i++)
			{
				logger.debug("Ejecutando Query...");

				String sQuery = "SELECT COUNT(DISTINCT("
						   + QMGastos.CAMPO2  +        
						   ")) FROM " 
						   + QMGastos.TABLA+
						   " WHERE "
						   + QMGastos.CAMPO7 + " BETWEEN " + rango.get(i).getsValor() + " AND " + ((i > 10)? Utils.primeroDeMes():rango.get(i+1).getsValor()) ;				   
							   
				
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
							
							String sValor = rs.getString("COUNT(DISTINCT("+QMGastos.CAMPO2+"))");
							
							resultado.add(sValor);

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
			

		}

		return resultado;
	}
	
	public static ArrayList<String> buscaActivosVendidosAcumuladosEnRango(Connection conexion, ArrayList<RangoAnual> rango)
	{
		ArrayList<String> resultado = new ArrayList<String>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			for(int i=0; i<rango.size(); i++)
			{
				logger.debug("Ejecutando Query...");

				String sQuery = "SELECT COUNT("
						   + QMActivos.CAMPO1  +        
						   ") FROM " 
						   + QMActivos.TABLA+
						   " WHERE "
						   + QMActivos.CAMPO71 + " BETWEEN 1 AND " + rango.get(i).getsValor();				   
							   
				
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
							
							String sValor = rs.getString("COUNT("+QMActivos.CAMPO1+")");
							
							resultado.add(sValor);

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
			

		}

		return resultado;
	}
	
	public static ArrayList<String> buscaActivosNuevosEnRango(Connection conexion, ArrayList<RangoAnual> rango)
	{
		ArrayList<String> resultado = new ArrayList<String>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			for(int i=0; i<rango.size(); i++)
			{
				logger.debug("Ejecutando Query...");

				String sQuery = "SELECT COUNT(DISTINCT("
						   + QMRegistroActivos.CAMPO1  +        
						   ")) FROM " 
						   + QMRegistroActivos.TABLA+
						   " WHERE "
						   + QMRegistroActivos.CAMPO2 + " BETWEEN " + rango.get(i).getsValor() + "000000000 AND " + ((i > 10)? Utils.primeroDeMes():rango.get(i+1).getsValor()) +"000000000";				   
							   
				
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
							
							String sValor = rs.getString("COUNT(DISTINCT("+QMRegistroActivos.CAMPO1+"))");
							
							resultado.add(sValor);

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
			

		}

		return resultado;
	}
	
	public static ArrayList<String> buscaGastosEstadoEnRango(Connection conexion, ArrayList<RangoAnual> rango, String sEstado)
	{
		ArrayList<String> resultado = new ArrayList<String>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String sCondicionFecha = "";
			
			if (sEstado.equals(ValoresDefecto.DEF_GASTO_AUTORIZADO))
			{
				sCondicionFecha = QMGastos.CAMPO32;
			}
			else if (sEstado.equals(ValoresDefecto.DEF_GASTO_PAGADO))
			{
				sCondicionFecha = QMGastos.CAMPO14;
			}
			else
			{
				sCondicionFecha = QMGastos.CAMPO7;
			}
			
			for(int i=0; i<rango.size(); i++)
			{
				logger.debug("Ejecutando Query...");

				String sQuery = "SELECT COUNT("
						   + QMGastos.CAMPO3  +        
						   ") FROM " 
						   + QMGastos.TABLA+
						   " WHERE "
						   + sCondicionFecha + 
						   " BETWEEN " + rango.get(i).getsValor() + " AND " + ((i > 10)? Utils.primeroDeMes():rango.get(i+1).getsValor()) ;				   
							   
				
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
							
							String sValor = rs.getString("COUNT("+QMGastos.CAMPO3+")");
							
							resultado.add(sValor);

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
			

		}

		return resultado;
	}
	
	public static ArrayList<String> buscaProvisionesEstadoEnRango(Connection conexion, ArrayList<RangoAnual> rango, String sEstado)
	{
		ArrayList<String> resultado = new ArrayList<String>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String sCondicionEstado = sEstado.isEmpty()?"":QMProvisiones.CAMPO21 + " = '" + sEstado+ "' AND ";
			
			for(int i=0; i<rango.size(); i++)
			{
				logger.debug("Ejecutando Query...");

				String sQuery = "SELECT COUNT("
						   + QMProvisiones.CAMPO4  +        
						   ") FROM " 
						   + QMProvisiones.TABLA+
						   " WHERE "
						   + sCondicionEstado
						   + QMProvisiones.CAMPO6 + " BETWEEN " + rango.get(i).getsValor() + " AND " + ((i > 10)? Utils.primeroDeMes():rango.get(i+1).getsValor()) ;				   
							   
				
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
							
							String sValor = rs.getString("COUNT("+QMGastos.CAMPO3+")");
							
							resultado.add(sValor);

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
			

		}

		return resultado;
	}
	
	public static ArrayList<String> buscaValoresProvisionesEstadoEnRango(Connection conexion, ArrayList<RangoAnual> rango, String sEstado)
	{
		ArrayList<String> resultado = new ArrayList<String>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			String sCondicionCampo = "";
			
			if (sEstado.equals(ValoresDefecto.DEF_PROVISION_AUTORIZADA))
			{
				sCondicionCampo = QMProvisiones.CAMPO11;
			}
			else if (sEstado.equals(ValoresDefecto.DEF_PROVISION_PAGADA))
			{
				sCondicionCampo = QMProvisiones.CAMPO14;
			}
			else
			{
				sCondicionCampo = QMProvisiones.CAMPO19;
			}
			
			String sCondicionEstado = sEstado.isEmpty()?"":QMProvisiones.CAMPO21 + " = '" + sEstado+ "' AND ";
			
			for(int i=0; i<rango.size(); i++)
			{
				logger.debug("Ejecutando Query...");

				String sQuery = "SELECT SUM("
						   + sCondicionCampo +        
						   ") FROM " 
						   + QMProvisiones.TABLA+
						   " WHERE "
						   + sCondicionEstado
						   + QMProvisiones.CAMPO6 + " BETWEEN " + rango.get(i).getsValor() + " AND " + ((i > 10)? Utils.primeroDeMes():rango.get(i+1).getsValor()) ;				   
							   
				
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
							
							
							
							String sValor = rs.getString("SUM("+sCondicionCampo+")");
							
							resultado.add(sValor);

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
			

		}

		return resultado;
	}
	
	public static ArrayList<String> buscaComunidadesEnRango(Connection conexion, ArrayList<RangoAnual> rango)
	{
		ArrayList<String> resultado = new ArrayList<String>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			for(int i=0; i<rango.size(); i++)
			{
				logger.debug("Ejecutando Query...");

				String sQuery = "SELECT COUNT(DISTINCT("
						   + QMListaComunidades.CAMPO1  +        
						   ")) FROM " 
						   + QMListaComunidades.TABLA+
						   " WHERE "
						   + QMListaComunidades.CAMPO5 + " BETWEEN " + rango.get(i).getsValor() + "000000000 AND " + ((i > 10)? Utils.primeroDeMes():rango.get(i+1).getsValor()) +"000000000";				   
							   
				
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
							
							String sValor = rs.getString("COUNT(DISTINCT("+QMListaComunidades.CAMPO1+"))");
							
							resultado.add(sValor);

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
			

		}

		return resultado;
	}
	
	public static ArrayList<String> buscaCuotasEnRango(Connection conexion, ArrayList<RangoAnual> rango)
	{
		ArrayList<String> resultado = new ArrayList<String>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			for(int i=0; i<rango.size(); i++)
			{
				logger.debug("Ejecutando Query...");

				String sQuery = "SELECT COUNT(DISTINCT("
						   + QMListaCuotas.CAMPO2  +        
						   ")) FROM " 
						   + QMListaCuotas.TABLA+
						   " WHERE "
						   + QMListaCuotas.CAMPO6 + " BETWEEN " + rango.get(i).getsValor() + "000000000 AND " + ((i > 10)? Utils.primeroDeMes():rango.get(i+1).getsValor()) +"000000000";				   
							   
				
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
							
							String sValor = rs.getString("COUNT(DISTINCT("+QMListaCuotas.CAMPO2+"))");
							
							resultado.add(sValor);

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
			

		}

		return resultado;
	}
	
	public static ArrayList<String> buscaReferenciasCatastralesEnRango(Connection conexion, ArrayList<RangoAnual> rango)
	{
		ArrayList<String> resultado = new ArrayList<String>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			for(int i=0; i<rango.size(); i++)
			{
				logger.debug("Ejecutando Query...");

				String sQuery = "SELECT COUNT(DISTINCT("
						   + QMListaReferencias.CAMPO2  +        
						   ")) FROM " 
						   + QMListaReferencias.TABLA+
						   " WHERE "
						   + QMListaReferencias.CAMPO6 + " BETWEEN " + rango.get(i).getsValor() + "000000000 AND " + ((i > 10)? Utils.primeroDeMes():rango.get(i+1).getsValor()) +"000000000";				   
							   
				
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
							
							String sValor = rs.getString("COUNT(DISTINCT("+QMListaReferencias.CAMPO2+"))");
							
							resultado.add(sValor);

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
			

		}

		return resultado;
	}
	
	public static ArrayList<String> buscaRecursosEnRango(Connection conexion, ArrayList<RangoAnual> rango)
	{
		ArrayList<String> resultado = new ArrayList<String>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			for(int i=0; i<rango.size(); i++)
			{
				logger.debug("Ejecutando Query...");

				String sQuery = "SELECT COUNT(DISTINCT("
						   + QMListaImpuestos.CAMPO2  +        
						   ")) FROM " 
						   + QMListaImpuestos.TABLA+
						   " WHERE "
						   + QMListaImpuestos.CAMPO6 + " BETWEEN " + rango.get(i).getsValor() + "000000000 AND " + ((i > 10)? Utils.primeroDeMes():rango.get(i+1).getsValor()) +"000000000";				   
							   
				
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
							
							String sValor = rs.getString("COUNT(DISTINCT("+QMListaImpuestos.CAMPO2+"))");
							
							resultado.add(sValor);

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
			

		}

		return resultado;
	}
	
	public static ArrayList<CierreInforme> buscaCierreInformeProvision(Connection conexion, String sNUPROF)
	{
		ArrayList<CierreInforme> resultado = new ArrayList<CierreInforme>();

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
						   + QMGastos.CAMPO15 + ","
						   + QMGastos.CAMPO16 + ","
						   + QMGastos.CAMPO35 +

						   " FROM " 
						   + QMGastos.TABLA + 
						   " WHERE ("
						   + QMGastos.CAMPO1 + 
						   " IN (SELECT "
						   +  QMListaGastosProvisiones.CAMPO1 + 
						   " FROM " 
						   + QMListaGastosProvisiones.TABLA + 
						   " WHERE " 
						   + QMListaGastosProvisiones.CAMPO2 + " = '"+ sNUPROF + "'))";					   
						   
			
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
						String sCOACES  = rs.getString(QMGastos.CAMPO2);
						
						int iCOACES = rs.getInt(QMGastos.CAMPO2);
						
						String sFEADAC  = Utils.recuperaFecha(QMActivos.getFEADACActivo(conexion,iCOACES));
						String sDTAS =  QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TTIACSA,QMCodigosControl.ITIACSA,com.provisiones.ll.CLActivos.compruebaTipoObra(iCOACES));
						
						String sCOGRUG  = rs.getString(QMGastos.CAMPO3);
						String sCOTPGA  = rs.getString(QMGastos.CAMPO4);
						String sCOSBGA  = rs.getString(QMGastos.CAMPO5);
						String sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO7));
						String sFELIPG  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO9));
						String sDCOSBGA = QMCodigosControl.getDesCOSBGA(conexion,sCOGRUG,sCOTPGA,sCOSBGA);
						String sDPTPAGO = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,rs.getString(QMGastos.CAMPO6));
						String sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO16).equals("-"),rs.getString(QMGastos.CAMPO15));
						String sCIF = QMListaComunidadesActivos.getCIFComunidadActivo(conexion,iCOACES);
						String sFAACTA = Utils.recuperaFecha(QMCuotas.getFAACTACuota(conexion,com.provisiones.ll.CLGastos.obtenerCuotaDeGasto(rs.getInt(QMGastos.CAMPO1))));
						String sNURCAT = QMActivos.getReferenciaCatastral(conexion,iCOACES);
						
						CierreInforme entrada = new CierreInforme(
								"G"+sGastoID,
								sCOACES,
								sFEADAC,
								sDTAS,
								sCOGRUG,
								sCOTPGA,
								sCOSBGA,
								sFEDEVE,
								sFELIPG,
								sDCOSBGA,
								sDPTPAGO,
								sIMNGAS,
								sCIF,
								sFAACTA,
								sNURCAT,
								"");
						
						resultado.add(entrada);
						
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
	
/*	public static ArrayList<PresupuestoInforme> buscaPresupuestoInformeFechas(Connection conexion, String sFechaInicio, String sFechaFin)
	{
		ArrayList<PresupuestoInforme> resultado = new ArrayList<PresupuestoInforme>();

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
						   + QMGastos.CAMPO15 + ","
						   + QMGastos.CAMPO16 + ","
						   + QMGastos.CAMPO35 +

						   " FROM " 
						   + QMGastos.TABLA + 
						   " WHERE ("
						   + QMGastos.CAMPO1 + 
						   " IN (SELECT "
						   +  QMListaGastosProvisiones.CAMPO1 + 
						   " FROM " 
						   + QMListaGastosProvisiones.TABLA + 
						   " WHERE " 
						   + QMListaGastosProvisiones.CAMPO2 + " = '"+ sNUPROF + "'))";					   
						   
			
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
						String sCOACES  = rs.getString(QMGastos.CAMPO2);
						
						int iCOACES = rs.getInt(QMGastos.CAMPO2);
						
						String sFEADAC  = Utils.recuperaFecha(QMActivos.getFEADACActivo(conexion,iCOACES));
						String sDTAS =  QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TTIACSA,QMCodigosControl.ITIACSA,com.provisiones.ll.CLActivos.compruebaTipoObra(iCOACES));
						
						String sCOGRUG  = rs.getString(QMGastos.CAMPO3);
						String sCOTPGA  = rs.getString(QMGastos.CAMPO4);
						String sCOSBGA  = rs.getString(QMGastos.CAMPO5);
						String sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO7));
						String sFELIPG  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO9));
						String sDCOSBGA = QMCodigosControl.getDesCOSBGA(conexion,sCOGRUG,sCOTPGA,sCOSBGA);
						String sDPTPAGO = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,rs.getString(QMGastos.CAMPO6));
						String sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO16).equals("-"),rs.getString(QMGastos.CAMPO15));
						String sCIF = QMListaComunidadesActivos.getCIFComunidadActivo(conexion,iCOACES);
						String sFAACTA = Utils.recuperaFecha(QMCuotas.getFAACTACuota(conexion,com.provisiones.ll.CLGastos.obtenerCuotaDeGasto(rs.getInt(QMGastos.CAMPO1))));
						String sNURCAT = QMActivos.getReferenciaCatastral(conexion,iCOACES);
						
						PresupuestoInforme entrada = new PresupuestoInforme(
								"G"+sGastoID,
								sCOACES,
								sFEADAC,
								sDTAS,
								sCOGRUG,
								sCOTPGA,
								sCOSBGA,
								sFEDEVE,
								sFELIPG,
								sDCOSBGA,
								sDPTPAGO,
								sIMNGAS,
								sCIF,
								sFAACTA,
								sNURCAT,
								"");
						
						resultado.add(entrada);
						
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
	}*/
}

//Autor: Francisco Valverde Manjón
