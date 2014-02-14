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

public final class QMListaGastos 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaGastos.class.getName());
	
	public static final String TABLA = "pp002_lista_gastos_multi";

	public static final String CAMPO1 = "cod_gasto";
	public static final String CAMPO2 = "cod_movimiento";
	
	public static final String CAMPO3 = "cod_validado";
	
	public static final String CAMPO4 = "usuario_movimiento";    
	public static final String CAMPO5 = "fecha_movimiento";

	private QMListaGastos(){}

	public static boolean addRelacionGasto(Connection conexion, long liCodGasto, long liCodMovimiento) 
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
					+ liCodMovimiento + "','"
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
				logger.error("ERROR MOVIMIENTO:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean addRelacionGastoInyectado(Connection conexion, long liCodGasto, long liCodMovimiento) 
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
					+ liCodMovimiento + "','"
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
				logger.error("ERROR MOVIMIENTO:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean delRelacionGasto(Connection conexion, long liCodMovimiento) 
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO2 + " = '" + liCodMovimiento +"'";
			
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
				
				logger.error("ERROR MOVIMIENTO:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{

				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean existeRelacionGasto(Connection conexion, long liCodGasto, long liCodMovimiento)
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
					+ CAMPO1  + " = '"+ liCodGasto +"' AND " 
					+ CAMPO2  + " = '"+ liCodMovimiento + 
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
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR GASTO:|"+liCodGasto+"|");
				logger.error("ERROR MOVIMIENTO:|"+liCodMovimiento+"|");

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
	
	public static ArrayList<Long>  getGastosPorEstado(Connection conexion, String sEstado) 
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

						resultado.add(rs.getLong(CAMPO2));
											
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO3+":|"+sEstado+"|");
						logger.debug(CAMPO2+":|"+resultado.get(i)+"|");

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
				resultado = new ArrayList<Long>();
				
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
	

	public static boolean setValidado(Connection conexion, long liCodMovimiento, String sValidado)
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
					+ CAMPO2 + " = '"+ liCodMovimiento +"'";
			
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
				
				logger.error("ERROR Gasto:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static String getValidado(Connection conexion, long liCodMovimiento)
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
					+ CAMPO2 + " = '" + liCodMovimiento + "'";
			
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
						logger.debug(CAMPO2+":|"+liCodMovimiento+"|");
						logger.debug(CAMPO3+":|"+sValidado+"|");
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

				logger.error("ERROR Gasto:|"+liCodMovimiento+"|");

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
				if (!bEncontrado) 
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
	
	public static ArrayList<ActivoTabla> buscaActivosConGastosAutorizados(Connection conexion, ActivoTabla activo)
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
	   					   + QMGastos.CAMPO34 + " = '"+ ValoresDefecto.DEF_GASTO_AUTORIZADO +"' AND "

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
				if (!bEncontrado) 
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
	
	public static ArrayList<GastoTabla> buscaGastosValidadosActivo(Connection conexion, int iCodCOACES)
	{
		ArrayList<GastoTabla> resultado = new ArrayList<GastoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			String sNUPROF = "";
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
						   + QMGastos.CAMPO1 + ","
						   + QMGastos.CAMPO2 + ","        
						   + QMGastos.CAMPO3 + ","
						   + QMGastos.CAMPO4 + ","
						   + QMGastos.CAMPO5 + ","
						   + QMGastos.CAMPO6 + ","
						   + QMGastos.CAMPO7 + ","
						   + QMGastos.CAMPO10 + ","
						   + QMGastos.CAMPO15 + ","
						   + QMGastos.CAMPO16 +

						   " FROM " 
						   + QMGastos.TABLA + 
						   " WHERE ((("
						   + QMGastos.CAMPO34 + 
						   " IN ('" 
						   + ValoresDefecto.DEF_GASTO_ESTIMADO + "','" 
						   + ValoresDefecto.DEF_GASTO_CONOCIDO + 
						   "')) AND "
						   + QMGastos.CAMPO16 + " <> '" + ValoresDefecto.DEF_NEGATIVO + "' "+
						   ") AND "					   
						   + QMGastos.CAMPO2 + " = '" + iCodCOACES	+ "' AND "
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
						
						sNUPROF = QMListaGastosProvisiones.getProvisionDeGasto(conexion, rs.getLong(QMGastos.CAMPO1));
						sCOACES  = rs.getString(QMGastos.CAMPO2);
						sCOGRUG  = rs.getString(QMGastos.CAMPO3);
						sCOTPGA  = rs.getString(QMGastos.CAMPO4);
						sCOSBGA  = rs.getString(QMGastos.CAMPO5);
						sDCOSBGA = QMCodigosControl.getDesCOSBGA(conexion,sCOGRUG,sCOTPGA,sCOSBGA);
						sPTPAGO  = rs.getString(QMGastos.CAMPO6);
						sDPTPAGO = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
						sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO7));
						sCOSIGA  = rs.getString(QMGastos.CAMPO10);
						sDCOSIGA = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TCOSIGA,QMCodigosControl.ICOSIGA,sCOSIGA);
						sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO16).equals("-"),rs.getString(QMGastos.CAMPO15));
						
						GastoTabla gastoencontrado = new GastoTabla(
								sNUPROF,
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

						logger.debug(CAMPO1+":|"+iCodCOACES+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<GastoTabla>();
				
				logger.error("ERROR COACES:|"+iCodCOACES+"|");
				
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
	
	public static ArrayList<Long> buscarDependencias(Connection conexion, long liCodGasto, long liCodMovimiento)
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
					+ CAMPO2  + 
					" FROM " 
					+ TABLA + 
					" WHERE (" 
					+ CAMPO1 + " = '" + liCodGasto + "' AND "
					+ CAMPO2 + " >=  '" + liCodMovimiento + 
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
						
						resultado.add(rs.getLong(CAMPO2));

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
				
				logger.error("ERROR GASTO:|"+liCodGasto+"|");
				logger.error("ERROR MOVIMIENTO:|"+liCodMovimiento+"|");

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
	
	public static ArrayList<Long> buscarMovimientosGasto(Connection conexion, long liCodGasto)
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
					+ CAMPO2  + 
					"  FROM " + TABLA + 
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
						
						resultado.add(rs.getLong(CAMPO2));

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
				
				logger.error("ERROR GASTO:|"+liCodGasto+"|");

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
	
	public static ArrayList<Long> buscarMovimientosValidadosGasto(Connection conexion, long liCodGasto)
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
					   " WHERE (" 
					   + CAMPO1 + " = '"+ liCodGasto + "' AND "
					   + CAMPO3 + " = '"+ ValoresDefecto.DEF_MOVIMIENTO_VALIDADO + "')";
			
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
						
						resultado.add(rs.getLong(CAMPO2));

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
				
				logger.error("ERROR GASTO:|"+liCodGasto+"|");

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
	
}
