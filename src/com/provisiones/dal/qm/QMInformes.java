package com.provisiones.dal.qm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.qm.listas.QMListaComunidadesActivos;
import com.provisiones.dal.qm.listas.QMListaGastosProvisiones;
import com.provisiones.misc.Utils;
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
		String sActivosTotales = "0";
		
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
						
						sActivosTotales = rs.getString("COUNT(DISTINCT("+QMGastos.CAMPO2+"))");

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
	
	public static String getActivosGestionadosUltimoMes(Connection conexion)
	{
		String sActivosTotales = "0";
		
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
						
						sActivosTotales = rs.getString("COUNT(DISTINCT("+QMGastos.CAMPO2+"))");

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
