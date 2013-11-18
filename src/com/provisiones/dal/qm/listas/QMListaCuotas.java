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
import com.provisiones.dal.qm.QMCuotas;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.tablas.ActivoTabla;

public class QMListaCuotas 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaCuotas.class.getName());
	
	static String TABLA = "pp001_lista_cuotas_multi";

	//identificadores
	static String CAMPO1 = "cod_coaces";        
	static String CAMPO2 = "cod_cuota";         
	static String CAMPO3 = "cod_movimiento";  
	
	//Campos de control
	static String CAMPO4 = "cod_validado";      
	static String CAMPO5 = "usuario_movimiento";
	static String CAMPO6 = "fecha_movimiento";

	public static boolean addRelacionCuotas(Connection conexion, String sCodCOACES, String sCodCuota, String sCodMovimiento) 
	{
		boolean bSalida = true;
		
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
					+ CAMPO6 +
					") VALUES ('" 
					+ sCodCOACES + "','"
					+ sCodCuota + "','"
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
				
				logger.debug("Ejecutada con éxito!");
			} 
			catch (SQLException ex) 
			{
				bSalida = false;

				logger.error("ERROR COACES:|"+sCodCOACES+"|");
				logger.error("ERROR Cuota:|"+sCodCuota+"|");
				logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean delRelacionCuotas(Connection conexion, String sCodMovimiento)
	{
		boolean bSalida = true;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO3 + " = '" + sCodMovimiento +"'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con éxito!");
			} 
			catch (SQLException ex) 
			{
				bSalida = false;

				logger.error("ERROR CodMovimiento:|"+sCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean existeRelacionCuota(Connection conexion, String sCodCuota, String sCodMovimiento)
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
					+ CAMPO2 + " = '" + sCodCuota + "' AND " 
					+ CAMPO3 + " = '" + sCodMovimiento + 
					"' )";

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
				logger.error("ERROR Couta:|"+sCodCuota+"|");
				logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

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
	

	
	public static ArrayList<String>  getCuotasPorEstado(Connection conexion, String sEstado) 
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
					+ CAMPO3 + 
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO4 + " = '" + sEstado + "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");
				
				int i = 0;
				
				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						resultado.add(rs.getString(CAMPO3));
											
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO4+":|"+sEstado+"|");
						logger.debug(CAMPO3+":|"+resultado.get(i)+"|");
						
						i++;
					}
				}
				if (bEncontrado == false) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<String>(); 

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
	
	public static ArrayList<String>  getCuotasPendientes(Connection conexion, String sCodCuota) 
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
					+ CAMPO3 + 
					" FROM " 
					+ TABLA + 
					" WHERE (" 
					+ CAMPO2 + " = '" + sCodCuota + "' AND " 
					+ CAMPO4 + " = '" + "P" + 
					"' )";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");
				
				int i = 0;
				
				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						resultado.add(rs.getString(CAMPO3));

						logger.debug("Encontrado el registro!");
						
						logger.debug(CAMPO2+":|"+sCodCuota+"|");
						logger.debug(CAMPO3+":|"+resultado.get(i)+"|");

						i++;
					}
				}
				if (bEncontrado == false) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<String>();

				logger.error("ERROR Cuota:|"+sCodCuota+"|");

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
					+ CAMPO4 + " = '"+ sValidado + "' "+
					" WHERE " 
					+ CAMPO3 + " = '" + sCodMovimiento +"'";
			
			logger.debug(sQuery);
			
			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con éxito!");

				bSalida = true;
			}
			catch (SQLException ex) 
			{
				bSalida = false;

				logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

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
					+ CAMPO4 + 
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO3 + " = '" + sCodMovimiento +"'";
			
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

						sValidado = rs.getString(CAMPO4);
						
						logger.debug("Encontrado el registro!");
						
						logger.debug(CAMPO3+":|"+sCodMovimiento+"|");
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

				logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

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
					+ CAMPO4 + " = '" + sCodValidado + "'";
			
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
				liNumero = 0;

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

	public static ArrayList<ActivoTabla> buscaActivosConCuotas(Connection conexion, ActivoTabla activo)
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
						   +  CAMPO1 + 
						   " FROM " 
						   + TABLA +
						   " WHERE " 
						   
						   + CAMPO2 + " IN (SELECT "
	   					   + QMCuotas.CAMPO1 + 
	   					   " FROM " 
	   					   + QMCuotas.TABLA +
	   					   " WHERE " + QMCuotas.CAMPO12 + " = '"+ ValoresDefecto.DEF_ALTA + "')))";
			
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
				resultado = new ArrayList<ActivoTabla>();

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
	
	public static ArrayList<String> buscarDependencias(Connection conexion, String sCodCuota, String sCodMovimiento)
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
					+ CAMPO3  + 
					"  FROM " 
					+ TABLA + 
					" WHERE (" 
					+ CAMPO2 + " = '" + sCodCuota + "' AND "
					+ CAMPO3 + " >=  '" + sCodMovimiento + 
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
						
						resultado.add(rs.getString(CAMPO3));

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
				resultado = new ArrayList<String>();

				logger.error("ERROR Cuota:|"+sCodCuota+"|");
				logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

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

