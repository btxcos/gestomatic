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
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.GastoTabla;

public class QMListaGastos 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaGastos.class.getName());
	
	static String TABLA = "pp001_lista_gastos_multi";

	static String CAMPO1 = "cod_gasto";
	static String CAMPO2 = "cod_movimiento";
	
	static String CAMPO3 = "cod_validado";
	
	static String CAMPO4 = "usuario_movimiento";    
	static String CAMPO5 = "fecha_movimiento";


	public static boolean addRelacionGasto(Connection conexion, String sCodGasto, String sCodMovimiento) 
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
					+ sCodMovimiento + "','"
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
				logger.error("ERROR MOVIMIENTO:|"+sCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean delRelacionGasto(Connection conexion, String sCodMovimiento) 
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO2 + " = '" + sCodMovimiento +"'";
			
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
				
				logger.error("ERROR MOVIMIENTO:|"+sCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{

				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean existeRelacionGasto(Connection conexion, String sCodGasto, String sCodMovimiento)
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
					+ CAMPO1  + " = '"+ sCodGasto +"' AND " 
					+ CAMPO2  + " = '"+ sCodMovimiento + 
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
				if (bEncontrado == false) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR GASTO:|"+sCodGasto+"|");
				logger.error("ERROR MOVIMIENTO:|"+sCodMovimiento+"|");

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
	
	public static ArrayList<String>  getGastosPorEstado(Connection conexion, String sEstado) 
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

						resultado.add(rs.getString(CAMPO2));
											
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO3+":|"+sEstado+"|");
						logger.debug(CAMPO2+":|"+resultado.get(i)+"|");

						i++;
					}
				}
				if (bEncontrado == false) 
				{
					resultado = new ArrayList<String>(); 
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
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
	

	public static boolean setValidado(Connection conexion, String sCodMovimiento, String sValidado)
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
					" WHERE "
					+ CAMPO2 + " = '"+ sCodMovimiento +"'";
			
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
				
				logger.error("ERROR Gasto:|"+sCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static String getValidado(Connection conexion, String sCodMovimiento)
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
					+ CAMPO2 + " = '" + sCodMovimiento + "'";
			
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
						logger.debug(CAMPO2+":|"+sCodMovimiento+"|");
						logger.debug(CAMPO3+":|"+sValidado+"|");
					}
				}
				if (bEncontrado == false) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sValidado = "";

				logger.error("ERROR Gasto:|"+sCodMovimiento+"|");

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
			
			String sQuery = "SELECT COUNT(*) FROM " 
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
				if (bEncontrado == false) 
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
	
	public static ArrayList<ActivoTabla> buscaActivosConGastosValidados(Connection conexion, ActivoTabla activo)
	{
		ArrayList<ActivoTabla> resultado = new ArrayList<ActivoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String sCOACES = "";
			String sCOPOIN = "";
			String sNOMUIN = "";
			String sNOPRAC = "";
			String sNOVIAS = "";
			String sNUPIAC = "";
			String sNUPOAC = "";
			String sNUPUAC = "";

			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						
						   + QMActivos.CAMPO1 + ","        
						   + QMActivos.CAMPO14 + ","
						   + QMActivos.CAMPO11 + ","
						   + QMActivos.CAMPO13 + ","
						   + QMActivos.CAMPO6 + ","
						   + QMActivos.CAMPO9 + ","
						   + QMActivos.CAMPO7 + ","
						   + QMActivos.CAMPO10 + 

						   " FROM " 
						   + QMActivos.TABLA + 
						   " WHERE ("

						   + QMActivos.CAMPO14 + " LIKE '%" + activo.getCOPOIN()	+ "%' AND "  
						   + QMActivos.CAMPO11 + " LIKE '%" + activo.getNOMUIN()	+ "%' AND "  
						   + QMActivos.CAMPO13 + " LIKE '%" + activo.getNOPRAC()	+ "%' AND "  
						   + QMActivos.CAMPO6 + " LIKE '%" + activo.getNOVIAS()	+ "%' AND "  
						   + QMActivos.CAMPO9 + " LIKE '%" + activo.getNUPIAC()	+ "%' AND "  
						   + QMActivos.CAMPO7 + " LIKE '%" + activo.getNUPOAC()	+ "%' AND "  
						   + QMActivos.CAMPO10 + " LIKE '%" + activo.getNUPUAC()	+ "%' AND "			

						   + QMActivos.CAMPO1 +" IN (SELECT "
						   + QMGastos.CAMPO2 + 
	   					   " FROM " 
						   + QMGastos.TABLA +
	   					   " WHERE " 
	   					   + QMGastos.CAMPO35 + " = '"+ ValoresDefecto.DEF_GASTO_ESTIMADO + "' " + " OR "
	   					   + QMGastos.CAMPO35 + " = '"+ ValoresDefecto.DEF_GASTO_CONOCIDO + "' AND " 

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
						
						sCOACES = rs.getString(QMActivos.CAMPO1);
						sCOPOIN = rs.getString(QMActivos.CAMPO14);
						sNOMUIN = rs.getString(QMActivos.CAMPO11);
						sNOPRAC = rs.getString(QMActivos.CAMPO13);
						sNOVIAS = rs.getString(QMActivos.CAMPO6);
						sNUPIAC = rs.getString(QMActivos.CAMPO9);
						sNUPOAC = rs.getString(QMActivos.CAMPO7);
						sNUPUAC = rs.getString(QMActivos.CAMPO10);
						
						ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, "");
						
						resultado.add(activoencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug(QMActivos.CAMPO1+":|"+sCOACES+"|");
					}
				}
				if (bEncontrado == false) 
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
	
	public static ArrayList<GastoTabla> buscaGastosValidadosActivo(Connection conexion, String sCodCOACES)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			String sCOACES = "";
			String sCOGRUG = "";
			String sCOTPGA = "";
			String sCOSBGA = "";
			String sDCOSBGA = "";
			String sPTPAGO = "";
			String sDPTPAGO = "";
			String sFEDEVE = "";
			String sCOSIGA = "";
			String sDCOSIGA = "";
			String sIMNGAS = "";
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						
						   + QMGastos.CAMPO2 + ","        
						   + QMGastos.CAMPO3 + ","
						   + QMGastos.CAMPO4 + ","
						   + QMGastos.CAMPO5 + ","
						   + QMGastos.CAMPO6 + ","
						   + QMGastos.CAMPO7 + ","
						   + QMGastos.CAMPO11 + ","
						   + QMGastos.CAMPO16 + ","
						   + QMGastos.CAMPO17 +

						   " FROM " 
						   + QMGastos.TABLA + 
						   " WHERE ((("
						   + QMGastos.CAMPO35 + 
						   " IN ('" 
						   + ValoresDefecto.DEF_GASTO_ESTIMADO + "','" 
						   + ValoresDefecto.DEF_GASTO_CONOCIDO + 
						   "')) AND "
						   + QMGastos.CAMPO17 + " <> '" + ValoresDefecto.DEF_NEGATIVO + "' "+
						   ") AND "					   
						   + QMGastos.CAMPO2 + " = '" + sCodCOACES	+ "' AND "
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
						
						sCOACES  = rs.getString(QMGastos.CAMPO2);
						sCOGRUG  = rs.getString(QMGastos.CAMPO3);
						sCOTPGA  = rs.getString(QMGastos.CAMPO4);
						sCOSBGA  = rs.getString(QMGastos.CAMPO5);
						sDCOSBGA = QMCodigosControl.getDesCOSBGA(conexion,sCOGRUG,sCOTPGA,sCOSBGA);
						sPTPAGO  = rs.getString(QMGastos.CAMPO6);
						sDPTPAGO = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
						sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO7));
						sCOSIGA  = rs.getString(QMGastos.CAMPO11);
						sDCOSIGA = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TCOSIGA,QMCodigosControl.ICOSIGA,sCOSIGA);
						sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO17).equals("-"),rs.getString(QMGastos.CAMPO16));
						
						GastoTabla gastoencontrado = new GastoTabla(
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
								sIMNGAS);
						
						resultado.add(gastoencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+sCodCOACES+"|");
					}
				}
				if (bEncontrado == false) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				logger.error("ERROR COACES:|"+sCodCOACES+"|");
				
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
	
	public static ArrayList<String> buscarDependencias(Connection conexion, String sCodGasto, String sCodMovimiento)
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
					+ CAMPO2  + 
					" FROM " 
					+ TABLA + 
					" WHERE (" 
					+ CAMPO1 + " = '" + sCodGasto + "' AND "
					+ CAMPO2 + " >=  '" + sCodMovimiento + 
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
						
						resultado.add(rs.getString(CAMPO2));

						logger.debug("Encontrado el registro!");
					}
				}
				if (bEncontrado == false) 
				{
					logger.debug("No se encontró la información.");
				}			
			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;
				
				logger.error("ERROR GASTO:|"+sCodGasto+"|");
				logger.error("ERROR MOVIMIENTO:|"+sCodMovimiento+"|");

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
	
	public static ArrayList<String> buscarMovimientosGasto(Connection conexion, String sCodGasto)
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
					+ CAMPO2  + 
					"  FROM " + TABLA + 
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
						
						resultado.add(rs.getString(CAMPO2));

						logger.debug("Encontrado el registro!");
					}
				}
				if (bEncontrado == false) 
				{
					logger.debug("No se encontró la información.");
				}			
			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;
				
				logger.error("ERROR GASTO:|"+sCodGasto+"|");

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
	
	public static ArrayList<String> buscarMovimientosValidadosGasto(Connection conexion, String sCodGasto)
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
					   + CAMPO3 + " = '"+ ValoresDefecto.DEF_MOVIMIENTO_VALIDADO + "'";
			
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
						
						resultado.add(rs.getString(CAMPO2));

						logger.debug("Encontrado el registro!");
					}
				}
				if (bEncontrado == false) 
				{
					logger.debug("No se encontró la información.");
				}			
			} 
			catch (SQLException ex) 
			{
				logger.error("ERROR GASTO:|"+sCodGasto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

				bEncontrado = false;
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
