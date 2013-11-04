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
import com.provisiones.dal.qm.QMImpuestos;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.ImpuestoRecursoTabla;

public class QMListaImpuestos 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaImpuestos.class.getName());

	static String TABLA = "pp001_lista_impuestos_multi";
	
	static String CAMPO1 = "cod_coaces";
	static String CAMPO2 = "cod_nurcat";
	static String CAMPO3 = "cod_cosbac";
	static String CAMPO4 = "cod_movimiento";
	static String CAMPO5 = "cod_validado";
	
	static String CAMPO6  = "usuario_movimiento";    
	static String CAMPO7  = "fecha_movimiento";

	public static boolean addRelacionImpuestos(String sCodCOACES, String sCodNURCAT, String sCodCOSBAC, String sCodMovimiento) 
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		String sUsuario = ValoresDefecto.DEF_USUARIO;

		conn = ConnectionManager.OpenDBConnection();
		
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
				+ CAMPO7 + 
				") VALUES ('" 
				+ sCodCOACES + "','"
				+ sCodNURCAT + "','"
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
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR COACES:|"+sCodCOACES+"|");
			logger.error("ERROR NURCAT:|"+sCodNURCAT+"|");
			logger.error("ERROR COSBAC:|"+sCodCOSBAC+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static boolean delRelacionImpuestos(String sCodMovimiento) 
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "DELETE FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO4 + " = '" + sCodMovimiento +"'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
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
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	
	public static boolean existeRelacionImpuesto(String sCodNURCAT, String sCodCOSBAC, String sCodCOACES, String sCodMovimiento)
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO4 + 
				" FROM " 
				+ TABLA + 
				" WHERE (" 
				+ CAMPO1 + " = '" + sCodCOACES + "' AND "
				+ CAMPO2 + " = '" + sCodNURCAT + "' AND " 
				+ CAMPO3 + " = '" + sCodCOSBAC + "' AND " 
				+ CAMPO4 + " = '" + sCodMovimiento +
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
			logger.error("ERROR NURCAT:|"+sCodNURCAT+"|");
			logger.error("ERROR COSBAC:|"+sCodCOSBAC+"|");
			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");


			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		ConnectionManager.CloseDBConnection(conn);
		return found;
	}

	public static boolean compruebaRelacionImpuestoActivo(String sCodNURCAT, String sCodCOSBAC, String sCodCOACES)
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO4 + 
				" FROM " 
				+ TABLA + 
				" WHERE (" 
				+ CAMPO1 + " = '" + sCodCOACES + "' AND " 
				+ CAMPO2 + " = '" + sCodNURCAT + "' AND " 
				+ CAMPO3 + " = '" + sCodCOSBAC + 
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

					logger.debug("Encontrado el registro!");
					logger.debug(CAMPO1+"|"+sCodCOACES+"|");
					logger.debug(CAMPO2+":|"+sCodNURCAT+"|");
					logger.debug(CAMPO3+":|"+sCodCOSBAC+"|");
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
			logger.error("ERROR NURCAT:|"+sCodNURCAT+"|");
			logger.error("ERROR COSBAC:|"+sCodCOSBAC+"|");


			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		ConnectionManager.CloseDBConnection(conn);
		return found;
	}
	
	public static ArrayList<String>  getImpuestosPorEstado(String sEstado) 
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	
		
		ArrayList<String> result = new ArrayList<String>(); 
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO4 + 
				" FROM " 
				+ TABLA + 
				" WHERE "
				+ CAMPO5 + " = '" + sEstado + "'";
		
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

					result.add(rs.getString(CAMPO4));
										
					logger.debug("Encontrado el registro!");
					
					logger.debug(CAMPO5+":|"+sEstado+"|");
					logger.debug(CAMPO4+":|"+result.get(i)+"|");

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

		ConnectionManager.CloseDBConnection(conn);
		return result;
	}
	
	public static ArrayList<ActivoTabla> buscaActivosAsociados(ActivoTabla activo)
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
		String sNURCAT = "";
		
		
		ArrayList<ActivoTabla> result = new ArrayList<ActivoTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		String sQuery = "SELECT "
					
					   + QMActivos.CAMPO1 + ","        
					   + QMActivos.CAMPO14 + ","
					   + QMActivos.CAMPO11 + ","
					   + QMActivos.CAMPO13 + ","
					   + QMActivos.CAMPO6 + ","
					   + QMActivos.CAMPO9 + ","
					   + QMActivos.CAMPO7 + ","
					   + QMActivos.CAMPO10 + ","
					   + QMActivos.CAMPO81 + 

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
					   "))";
		
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
					
					sCOACES = rs.getString(QMActivos.CAMPO1);
					sCOPOIN = rs.getString(QMActivos.CAMPO14);
					sNOMUIN = rs.getString(QMActivos.CAMPO11);
					sNOPRAC = rs.getString(QMActivos.CAMPO13);
					sNOVIAS = rs.getString(QMActivos.CAMPO6);
					sNUPIAC = rs.getString(QMActivos.CAMPO9);
					sNUPOAC = rs.getString(QMActivos.CAMPO7);
					sNUPUAC = rs.getString(QMActivos.CAMPO10);
					sNURCAT = rs.getString(QMActivos.CAMPO81);
					
					ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, sNURCAT);
					
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
		ConnectionManager.CloseDBConnection(conn);
		return result;

	}
	
	public static ArrayList<ActivoTabla> buscaActivosAsociadosResueltos(ActivoTabla activo)
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
		String sNURCAT = "";
		
		
		ArrayList<ActivoTabla> result = new ArrayList<ActivoTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		String sQuery = "SELECT "
					
					   + QMActivos.CAMPO1 + ","        
					   + QMActivos.CAMPO14 + ","
					   + QMActivos.CAMPO11 + ","
					   + QMActivos.CAMPO13 + ","
					   + QMActivos.CAMPO6 + ","
					   + QMActivos.CAMPO9 + ","
					   + QMActivos.CAMPO7 + ","
					   + QMActivos.CAMPO10 + ","
					   + QMActivos.CAMPO81 + 

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
					   " WHERE ("+
					   
					   CAMPO2 +" IN (SELECT "
					   + QMImpuestos.CAMPO1 + 
					   " FROM " 
					   + QMImpuestos.TABLA +
					   " WHERE " +
					   "("
		   			   + QMImpuestos.CAMPO7 + " = 'F' AND " 
		   			   + QMImpuestos.CAMPO6 + " = 'S' AND "
		   			   + QMImpuestos.CAMPO4 + " <= '"+Utils.fechaDeHoy(false)+"' AND "
		   			   + QMImpuestos.CAMPO10 + " = '" + ValoresDefecto.DEF_ALTA + "' " +
		   			   "))"+

		   			   "AND "  

		   			   +CAMPO3 +" IN (SELECT "
					   + QMImpuestos.CAMPO2 + 
					   " FROM " 
					   + QMImpuestos.TABLA +
					   " WHERE " +
					   "("
		   			   + QMImpuestos.CAMPO7 + " = 'F' AND " 
		   			   + QMImpuestos.CAMPO6 + " = 'S' AND "
		   			   + QMImpuestos.CAMPO4 + " <= '"+Utils.fechaDeHoy(false)+"' AND "
		   			   + QMImpuestos.CAMPO10 + " = '" + ValoresDefecto.DEF_ALTA + "' " +
		   			   ")))))";
		
		
	
		
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
					
					sCOACES = rs.getString(QMActivos.CAMPO1);
					sCOPOIN = rs.getString(QMActivos.CAMPO14);
					sNOMUIN = rs.getString(QMActivos.CAMPO11);
					sNOPRAC = rs.getString(QMActivos.CAMPO13);
					sNOVIAS = rs.getString(QMActivos.CAMPO6);
					sNUPIAC = rs.getString(QMActivos.CAMPO9);
					sNUPOAC = rs.getString(QMActivos.CAMPO7);
					sNUPUAC = rs.getString(QMActivos.CAMPO10);
					sNURCAT = rs.getString(QMActivos.CAMPO81);
					
					ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, sNURCAT);
					
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
		ConnectionManager.CloseDBConnection(conn);
		return result;

	}
	


	public static ArrayList<ImpuestoRecursoTabla> buscaImpuestosActivo(String sCodCOACES)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sCOSBAC = "";
		String sDesCOSBAC = "";
		String sFEPRRE = "";
		String sFERERE = "";
		String sFEDEIN = "";
		String sBISODE = "";
		String sDesBISODE = "";
		String sBIRESO = "";
		String sDesBIRESO = "";
		String sOBTEXC = "";
		
		ArrayList<ImpuestoRecursoTabla> result = new ArrayList<ImpuestoRecursoTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		String sQuery = "SELECT "
					
					   + QMImpuestos.CAMPO1 + ","        
					   + QMImpuestos.CAMPO2 + ","
					   + QMImpuestos.CAMPO3 + ","
					   + QMImpuestos.CAMPO4 + ","
					   + QMImpuestos.CAMPO5 + ","
					   + QMImpuestos.CAMPO6 + ","
					   + QMImpuestos.CAMPO7 + ","
					   + QMImpuestos.CAMPO8 + ","
					   + QMImpuestos.CAMPO9 +
					    

					   " FROM " 
					   + QMImpuestos.TABLA + 
					   " WHERE ("

					   + QMImpuestos.CAMPO10 + " = '" + ValoresDefecto.DEF_ALTA + "' AND "  

					   + QMImpuestos.CAMPO1 +" IN (SELECT "
					   +  CAMPO2 + 
					   " FROM " 
					   + TABLA + 
					   " WHERE (" 
					   + CAMPO1 + " = '" + sCodCOACES	+ "' )) AND "  

					   + QMImpuestos.CAMPO2 +" IN (SELECT "
					   +  CAMPO3 + 
					   " FROM " 
					   + TABLA + 
					   " WHERE (" 
					   + CAMPO1 + " = '" + sCodCOACES	+ "' )))";					   
					   
		
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
					

					
					sCOSBAC     = rs.getString(QMImpuestos.CAMPO2);
					sDesCOSBAC  = QMCodigosControl.getDesCampo(QMCodigosControl.TCOSBGAT21,QMCodigosControl.ICOSBGAT21,sCOSBAC);
					sFEPRRE     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO3));
					sFERERE     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO4));
					sFEDEIN     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO5));
					sBISODE     = rs.getString(QMImpuestos.CAMPO6);
					sDesBISODE  = QMCodigosControl.getDesCampo(QMCodigosControl.TBINARIA,QMCodigosControl.IBINARIA,sBISODE);
					sBIRESO     = rs.getString(QMImpuestos.CAMPO7);
					sDesBIRESO  = QMCodigosControl.getDesCampo(QMCodigosControl.TBIRESO,QMCodigosControl.IBIRESO,sBIRESO);
					sOBTEXC     = rs.getString(QMImpuestos.CAMPO9);  

					
					ImpuestoRecursoTabla impuestoencontrado = new ImpuestoRecursoTabla(
							sCOSBAC,
							sDesCOSBAC,
							sFEPRRE,
							sFERERE,
							sFEDEIN,
							sBISODE,
							sDesBISODE,
							sBIRESO,
							sDesBIRESO,
							sOBTEXC);
					
					result.add(impuestoencontrado);
					
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
		ConnectionManager.CloseDBConnection(conn);
		return result;
	}
	
	public static ArrayList<ImpuestoRecursoTabla> buscaDevolucionesActivo(String sCodCOACES)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sCOSBAC = "";
		String sDesCOSBAC = "";
		String sFEPRRE = "";
		String sFERERE = "";
		String sFEDEIN = "";
		String sBISODE = "";
		String sDesBISODE = "";
		String sBIRESO = "";
		String sDesBIRESO = "";
		String sOBTEXC = "";
		
		ArrayList<ImpuestoRecursoTabla> result = new ArrayList<ImpuestoRecursoTabla>();
		
		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		String sQuery = "SELECT "
					
					   + QMImpuestos.CAMPO1 + ","        
					   + QMImpuestos.CAMPO2 + ","
					   + QMImpuestos.CAMPO3 + ","
					   + QMImpuestos.CAMPO4 + ","
					   + QMImpuestos.CAMPO5 + ","
					   + QMImpuestos.CAMPO6 + ","
					   + QMImpuestos.CAMPO7 + ","
					   + QMImpuestos.CAMPO8 + ","
					   + QMImpuestos.CAMPO9 +
					    

					   " FROM " 
					   + QMImpuestos.TABLA + 
					   " WHERE ("

					   + QMImpuestos.CAMPO7 + " = 'F' AND " 
					   + QMImpuestos.CAMPO6 + " = 'S' AND "
					   + QMImpuestos.CAMPO4 + " <= '"+Utils.fechaDeHoy(false)+"' AND "
					   + QMImpuestos.CAMPO10 + " = '" + ValoresDefecto.DEF_ALTA + "' " +

					   "AND "  

					   + QMImpuestos.CAMPO1 +" IN (SELECT "
					   +  CAMPO2 + 
					   " FROM " 
					   + TABLA + 
					   " WHERE (" 
					   + CAMPO1 + " = '" + sCodCOACES	+ "' )) AND "  

					   + QMImpuestos.CAMPO2 +" IN (SELECT "
					   +  CAMPO3 + 
					   " FROM " 
					   + TABLA + 
					   " WHERE (" 
					   + CAMPO1 + " = '" + sCodCOACES + "' )))";				   
					   
		
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
					

					
					sCOSBAC     = rs.getString(QMImpuestos.CAMPO2);
					sDesCOSBAC  = QMCodigosControl.getDesCampo(QMCodigosControl.TCOSBGAT21,QMCodigosControl.ICOSBGAT21,sCOSBAC);
					sFEPRRE     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO3));
					sFERERE     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO4));
					sFEDEIN     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO5));
					sBISODE     = rs.getString(QMImpuestos.CAMPO6);
					sDesBISODE  = QMCodigosControl.getDesCampo(QMCodigosControl.TBINARIA,QMCodigosControl.IBINARIA,sBISODE);
					sBIRESO     = rs.getString(QMImpuestos.CAMPO7);
					sDesBIRESO  = QMCodigosControl.getDesCampo(QMCodigosControl.TBIRESO,QMCodigosControl.IBIRESO,sBIRESO);
					sOBTEXC     = rs.getString(QMImpuestos.CAMPO9);  

					
					ImpuestoRecursoTabla impuestoencontrado = new ImpuestoRecursoTabla(
							sCOSBAC,
							sDesCOSBAC,
							sFEPRRE,
							sFERERE,
							sFEDEIN,
							sBISODE,
							sDesBISODE,
							sBIRESO,
							sDesBIRESO,
							sOBTEXC);
					
					result.add(impuestoencontrado);
					
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
		ConnectionManager.CloseDBConnection(conn);
		return result;
	}

	public static boolean setValidado(String sCodMovimiento, String sValidado)
	{
		Statement stmt = null;
		boolean bSalida = true;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO5 + " = '"+ sValidado + "' "+
				" WHERE "
				+ CAMPO4 + " = '" + sCodMovimiento +"'";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();

			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
			
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
		ConnectionManager.CloseDBConnection(conn);
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

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO5 + 
				" FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO4 + " = '" + sCodMovimiento +"'";
		
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

					sValidado = rs.getString(CAMPO5);

					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO5+":|"+sValidado+"|");
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

		ConnectionManager.CloseDBConnection(conn);
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

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT COUNT(*) FROM " 
				+ TABLA + 
				" WHERE "
				+ CAMPO5 + " = '" + sCodValidado + "'";
		
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

		ConnectionManager.CloseDBConnection(conn);
		return liNumero;
	}
	
	public static ArrayList<String> buscarDependencias(String sCodCOACES, String sCodNURCAT, String sCodCOSBAC, String sCodMovimiento)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;		

		boolean found = false;
		
		ArrayList<String> result = new ArrayList<String>();

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO4  + 
				" FROM " 
				+ TABLA + 
				" WHERE (" 
				+ CAMPO1 + " = '" + sCodCOACES + "' AND "
				+ CAMPO2 + " = '" + sCodNURCAT + "' AND "
				+ CAMPO3 + " = '" + sCodCOSBAC + "' AND "
				+ CAMPO4 + " >=  '" + sCodMovimiento + 
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
					
					result.add(rs.getString(CAMPO4));

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
			logger.error("ERROR NURCAT:|"+sCodNURCAT+"|");
			logger.error("ERROR COSBAC:|"+sCodCOSBAC+"|");
			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

			found = false;
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return result;
	}
}
