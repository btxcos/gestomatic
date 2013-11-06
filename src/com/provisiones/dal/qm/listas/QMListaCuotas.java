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
import com.provisiones.dal.qm.QMCuotas;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.CuotaTabla;

public class QMListaCuotas 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaCuotas.class.getName());
	
	static String TABLA = "pp001_lista_cuotas_multi";

	static String CAMPO1 = "cod_coaces";
	static String CAMPO2 = "cod_cocldo";
	static String CAMPO3 = "cod_nudcom";
	static String CAMPO4 = "cod_cosbac";
	static String CAMPO5 = "cod_movimiento";
	static String CAMPO6 = "cod_validado";
	
	static String CAMPO7  = "usuario_movimiento";    
	static String CAMPO8  = "fecha_movimiento";

	public static boolean addRelacionCuotas(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC, String sCodMovimiento) 
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
				+ CAMPO5 + ","
				+ CAMPO6 + ","
				+ CAMPO7 + ","
				+ CAMPO8 +
				") VALUES ('" 
				+ sCodCOACES + "','"
				+ sCodCOCLDO + "','"
				+ sCodNUDCOM + "','"
				+ sCodCOSBAC + "','"
				+ sCodMovimiento + "','"
				+ ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE + "','"
			    + sUsuario + "','"
			    + Utils.timeStamp() + 
				"')";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con éxito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR COACES:|"+sCodCOACES+"|");
			logger.error("ERROR COCLDO:|"+sCodCOCLDO+"|");
			logger.error("ERROR NUDCOM:|"+sCodNUDCOM+"|");
			logger.error("ERROR COSBAC:|"+sCodCOSBAC+"|");

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

	public static boolean delRelacionCuotas(String sCodMovimiento)
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "DELETE FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO5 + " = '" + sCodMovimiento +"'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con éxito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR CodMovimiento:|"+sCodMovimiento+"|");

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
	
	public static boolean existeRelacionCuota(String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC, String sCodCOACES, String sCodMovimiento)
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	
		Connection conn = null;

		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO6 + 
				" FROM " 
				+ TABLA + 
				" WHERE (" 
				+ CAMPO1 + " = '" + sCodCOACES + "' AND " 
				+ CAMPO2 + " = '" + sCodCOCLDO + "' AND " 
				+ CAMPO3 + " = '" + sCodNUDCOM + "' AND " 
				+ CAMPO4 + " = '" + sCodCOSBAC + "' AND " 
				+ CAMPO5 + " = '" + sCodMovimiento + 
				"' )";

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement(sQuery);


			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con éxito!");			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					logger.debug("Encontrado el registro!");
				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR COACES:|"+sCodCOACES+"|");
			logger.error("ERROR COCLDO:|"+sCodCOCLDO+"|");
			logger.error("ERROR NUDCOM:|"+sCodNUDCOM+"|");
			logger.error("ERROR COSBAC:|"+sCodCOSBAC+"|");
			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

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
	
	public static boolean compruebaRelacionCuotaActivo(String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC, String sCodCOACES)
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	
		Connection conn = null;

		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO6 + 
				" FROM " 
				+ TABLA + 
				" WHERE (" 
				+ CAMPO1 + " = '" + sCodCOACES + "' AND " 
				+ CAMPO2 + " = '" + sCodCOCLDO + "' AND " 
				+ CAMPO3 + " = '" + sCodNUDCOM + "' AND " 
				+ CAMPO4 + " = '" + sCodCOSBAC + 
				"')";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement(sQuery);


			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con éxito!");			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					logger.debug("Encontrado el registro!");
				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR COACES:|"+sCodCOACES+"|");
			logger.error("ERROR COCLDO:|"+sCodCOCLDO+"|");
			logger.error("ERROR NUDCOM:|"+sCodNUDCOM+"|");
			logger.error("ERROR COSBAC:|"+sCodCOSBAC+"|");

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
	
	public static ArrayList<String>  getCuotasPorEstado(String sEstado) 
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
				+ CAMPO5+ 
				" FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO6 + " = '" + sEstado + "'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con éxito!");
			
		
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(CAMPO5));
										
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO6+":|"+sEstado+"|");
					logger.debug(CAMPO5+":|"+result.get(i)+"|");
					
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
			logger.error("ERROR Validado:|"+sEstado+"|");

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

	public static ArrayList<String>  getCuotas(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC) 
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
				+ CAMPO3 + 
				" FROM " 
				+ TABLA + 
				" WHERE (" 
				+ CAMPO1 + " = '" + sCodCOACES + "' AND " 
				+ CAMPO2 + " = '" + sCodCOCLDO + "' AND " 
				+ CAMPO3 + " = '" + sCodNUDCOM + "' AND " 
				+ CAMPO4 + " = '" + sCodCOSBAC + "')";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con éxito!");
			
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(CAMPO3));

					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO1+":|"+sCodCOACES+"|");
					logger.debug(CAMPO2+":|"+sCodCOCLDO+"|");
					logger.debug(CAMPO3+":|"+sCodNUDCOM+"|");
					logger.debug(CAMPO4+":|"+sCodCOSBAC+"|");

					
					
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
			logger.error("ERROR COACES:|"+sCodCOACES+"|");
			logger.error("ERROR COCLDO:|"+sCodCOCLDO+"|");
			logger.error("ERROR NUDCOM:|"+sCodNUDCOM+"|");
			logger.error("ERROR COSBAC:|"+sCodCOSBAC+"|");

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
	
	public static ArrayList<String>  getCuotasPendientes(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC) 
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
				+ CAMPO5 + 
				" FROM " 
				+ TABLA + 
				" WHERE (" 
				+ CAMPO1 + " = '" + sCodCOACES + "' AND " 
				+ CAMPO2 + " = '" + sCodCOCLDO + "' AND " 
				+ CAMPO3 + " = '" + sCodNUDCOM + "' AND " 
				+ CAMPO4 + " = '" + sCodCOSBAC + "' AND " 
				+ CAMPO6 + " = '" + "P" + 
				"' )";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con éxito!");
			
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(CAMPO5));

					logger.debug("Encontrado el registro!");
					
					logger.debug(CAMPO1+":|"+sCodCOACES+"|");
					logger.debug(CAMPO2+":|"+sCodCOCLDO+"|");
					logger.debug(CAMPO3+":|"+sCodNUDCOM+"|");
					logger.debug(CAMPO4+":|"+sCodCOSBAC+"|");

					logger.debug(CAMPO5+":|"+result.get(i)+"|");

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
			logger.error("ERROR COACES:|"+sCodCOACES+"|");
			logger.error("ERROR COCLDO:|"+sCodCOCLDO+"|");
			logger.error("ERROR NUDCOM:|"+sCodNUDCOM+"|");
			logger.error("ERROR COSBAC:|"+sCodCOSBAC+"|");

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

	public static boolean setValidado(String sCodMovimiento, String sValidado)
	{
		Statement stmt = null;
		boolean bSalida = true;
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO6 + " = '"+ sValidado + "' "+
				" WHERE " 
				+ CAMPO5 + " = '" + sCodMovimiento +"'";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();

			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con éxito!");

			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

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
	
	public static String getValidado(String sCodMovimiento)
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sValidado = "";

		Connection conn = null;

		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO6 + 
				" FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO5 + " = '" + sCodMovimiento +"'";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement(sQuery);


			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con éxito!");			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sValidado = rs.getString(CAMPO6);
					
					logger.debug("Encontrado el registro!");
					
					logger.debug(CAMPO5+":|"+sCodMovimiento+"|");
				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		//ConnectionManager.CloseDBConnection(conn);
		return sValidado;
	}
	
	public static long buscaCantidadValidado(String sCodValidado)
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
				+ CAMPO6 + " = '" + sCodValidado + "'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con éxito!");
			
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
			logger.error("ERROR CodValidado:|"+sCodValidado+"|");

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
	
	public static ArrayList<ActivoTabla> buscaActivosComunidadDisponibles(ActivoTabla activo, String sCodCOCLDO, String sCodNUDCOM)
	{
		
		Statement stmt = null;
		ResultSet rs = null;

		String sCOACES = "";
		String sCOPOIN = "";
		String sNOMUIN = "";
		String sNOPRAC = "";
		String sNOVIAS = "";
		String sNUPIAC = "";
		String sNUPOAC = "";
		String sNUPUAC = "";
		
		ArrayList<ActivoTabla> result = new ArrayList<ActivoTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
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

					   +  QMListaComunidadesActivos.CAMPO3 + 
					   " FROM " 
					   + QMListaComunidadesActivos.TABLA + 
					   " WHERE ("

					   + QMListaComunidadesActivos.CAMPO1 + " = '" + sCodCOCLDO	+ "' AND "  
					   + QMListaComunidadesActivos.CAMPO2 + " = '" + sCodNUDCOM	+ "' " +
					   	//	"AND "
					   //+ QMListaComunidadesActivos.CAMPO3 + 
					   //" NOT IN  (SELECT "
					   //+  CAMPO1 + 
					   //"  FROM " + TABLA +  ")" +
					   ")))";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con éxito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					sCOACES = rs.getString(QMActivos.CAMPO1);
					sCOPOIN = rs.getString(QMActivos.CAMPO14);
					sNOMUIN = rs.getString(QMActivos.CAMPO11);
					sNOPRAC = rs.getString(QMActivos.CAMPO13);
					sNOVIAS = rs.getString(QMActivos.CAMPO6);
					sNUPIAC = rs.getString(QMActivos.CAMPO9);
					sNUPOAC = rs.getString(QMActivos.CAMPO7);
					sNUPUAC = rs.getString(QMActivos.CAMPO10);
					
					ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, "");
					
					result.add(activoencontrado);
					
					logger.debug("Encontrado el registro!");

					logger.debug(QMActivos.CAMPO1+":|"+sCOACES+"|");
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
	
	public static ArrayList<CuotaTabla> buscaCuotasActivo(String sCodCOACES)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sCOCLDO = "";
		String sDesCOCLDO = "";
		String sNUDCOM = "";
		String sCOSBAC = "";
		String sDesCOSBAC = "";
		String sFIPAGO = "";
		String sFFPAGO = "";
		String sIMCUCO = "";
		String sFAACTA = "";
		String sPTPAGO = "";
		String sDesPTPAGO = "";
		String sOBTEXC = "";
		
		ArrayList<CuotaTabla> result = new ArrayList<CuotaTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
					
					   + QMCuotas.CAMPO1 + ","        
					   + QMCuotas.CAMPO2 + ","
					   + QMCuotas.CAMPO3 + ","
					   + QMCuotas.CAMPO4 + ","
					   + QMCuotas.CAMPO5 + ","
					   + QMCuotas.CAMPO6 + ","
					   + QMCuotas.CAMPO7 + ","
					   + QMCuotas.CAMPO8 + ","
					   + QMCuotas.CAMPO9 + ","
					   + QMCuotas.CAMPO10 +
					    

					   " FROM " 
					   + QMCuotas.TABLA + 
					   " WHERE ("

					   + QMCuotas.CAMPO11 + " = '" + ValoresDefecto.DEF_ALTA + "' AND "  

					   + QMCuotas.CAMPO2 +" IN (SELECT "
					   +  CAMPO2 + 
					   " FROM " 
					   + TABLA + 
					   " WHERE (" 
					   + CAMPO1 + " = '" + sCodCOACES	+ "' ) ) AND "  

					   + QMCuotas.CAMPO3 +" IN (SELECT "
					   +  CAMPO3 + 
					   " FROM " 
					   + TABLA + 
					   " WHERE (" 
					   + CAMPO1 + " = '" + sCodCOACES	+ "' ) ) AND "  

					   + QMCuotas.CAMPO4 +" IN (SELECT "
					   +  CAMPO4 + 
					   " FROM " 
					   + TABLA + 
					   " WHERE (" 
					   + CAMPO1 + " = '" + sCodCOACES	+ "' ) ) )";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement(sQuery);

			


			

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con éxito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					sCOCLDO     = rs.getString(QMCuotas.CAMPO2);
					sDesCOCLDO  = QMCodigosControl.getDesCampo(QMCodigosControl.TCOCLDO, QMCodigosControl.ICOCLDO, sCOCLDO);
					sNUDCOM     = rs.getString(QMCuotas.CAMPO3);
					sCOSBAC     = rs.getString(QMCuotas.CAMPO4);
					sDesCOSBAC  = QMCodigosControl.getDesCampo(QMCodigosControl.TCOSBGAT22,QMCodigosControl.ICOSBGAT22,sCOSBAC);
					sFIPAGO     = Utils.recuperaFecha(rs.getString(QMCuotas.CAMPO5));
					sFFPAGO     = Utils.recuperaFecha(rs.getString(QMCuotas.CAMPO6));
					sIMCUCO     = Utils.recuperaImporte(false,rs.getString(QMCuotas.CAMPO7));
					sFAACTA     = Utils.recuperaFecha(rs.getString(QMCuotas.CAMPO8));
					sPTPAGO     = rs.getString(QMCuotas.CAMPO9);
					sDesPTPAGO  = QMCodigosControl.getDesCampo(QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
					sOBTEXC     = rs.getString(QMCuotas.CAMPO10);  

					
					CuotaTabla cuotaencontrada = new CuotaTabla(
							sCOCLDO,
							sDesCOCLDO,
							sNUDCOM,
							sCOSBAC,
							sDesCOSBAC,
							sFIPAGO,
							sFFPAGO,
							sIMCUCO,
							sFAACTA,
							sPTPAGO,
							sDesPTPAGO,
							sOBTEXC);
					
					result.add(cuotaencontrada);
					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO1+":|"+sCodCOACES+"|");
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
	
	public static ArrayList<ActivoTabla> buscaActivosConCuotas(ActivoTabla activo)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sCOACES = "";
		String sCOPOIN = "";
		String sNOMUIN = "";
		String sNOPRAC = "";
		String sNOVIAS = "";
		String sNUPIAC = "";
		String sNUPOAC = "";
		String sNUPUAC = "";
		
		ArrayList<ActivoTabla> result = new ArrayList<ActivoTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
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
   					   + QMCuotas.CAMPO2 + 
   					   " FROM " 
   					   + QMCuotas.TABLA +
   					   " WHERE " + QMCuotas.CAMPO11 + " = '"+ ValoresDefecto.DEF_ALTA + "') AND " 

   					   + CAMPO3 + " IN (SELECT "
   					   + QMCuotas.CAMPO3 + 
   					   " FROM " 
   					   + QMCuotas.TABLA +
   					   " WHERE " + QMCuotas.CAMPO11 + " = '"+ ValoresDefecto.DEF_ALTA + "')))";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con éxito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					sCOACES = rs.getString(QMActivos.CAMPO1);
					sCOPOIN = rs.getString(QMActivos.CAMPO14);
					sNOMUIN = rs.getString(QMActivos.CAMPO11);
					sNOPRAC = rs.getString(QMActivos.CAMPO13);
					sNOVIAS = rs.getString(QMActivos.CAMPO6);
					sNUPIAC = rs.getString(QMActivos.CAMPO9);
					sNUPOAC = rs.getString(QMActivos.CAMPO7);
					sNUPUAC = rs.getString(QMActivos.CAMPO10);
					
					ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, "");
					
					result.add(activoencontrado);
					
					logger.debug("Encontrado el registro!");

					logger.debug(QMActivos.CAMPO1+":|"+sCOACES+"|");
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
	
	public static ArrayList<String> buscarDependencias(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC, String sCodMovimiento)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;		

		boolean found = false;
		
		ArrayList<String> result = new ArrayList<String>();

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO5  + 
				"  FROM " 
				+ TABLA + 
				" WHERE (" 
				+ CAMPO1 + " = '" + sCodCOACES + "' AND "
				+ CAMPO2 + " = '" + sCodCOCLDO + "' AND "
				+ CAMPO3 + " = '" + sCodNUDCOM + "' AND "
				+ CAMPO4 + " = '" + sCodCOSBAC + "' AND "
				+ CAMPO5 + " >=  '" + sCodMovimiento + 
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
					
					result.add(rs.getString(CAMPO5));

					logger.debug("Encontrado el registro!");

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}			

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR COCLDO:|"+sCodCOCLDO+"|");
			logger.error("ERROR NUDCOM:|"+sCodNUDCOM+"|");
			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

			found = false;
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return result;
	}
}

