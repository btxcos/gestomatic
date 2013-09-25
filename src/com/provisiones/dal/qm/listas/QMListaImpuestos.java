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
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.ImpuestoRecursoTabla;

public class QMListaImpuestos 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaImpuestos.class.getName());

	static String sTable = "lista_impuestos_multi";
	
	static String sField1 = "cod_coaces";
	static String sField2 = "cod_nurcat";
	static String sField3 = "cod_cosbac";
	static String sField4 = "cod_movimiento";
	static String sField5 = "cod_validado";
	
	static String sField6  = "usuario_movimiento";    
	static String sField7  = "fecha_movimiento";

	public static boolean addRelacionImpuestos(String sCodCOACES, String sCodNURCAT, String sCodCOSBAC, String sCodMovimiento) 
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		String sUsuario = ValoresDefecto.DEF_USUARIO;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + sTable + " (" 
			+ sField1 + ","
			+ sField2 + ","
			+ sField3 + "," 
			+ sField4 + "," 
			+ sField5 + ","
			+ sField6 + "," 
			+ sField7 + 
			") " 
			+ "VALUES ('" 
			+ sCodCOACES + "','"
			+ sCodNURCAT + "','"
			+ sCodCOSBAC + "','"
			+ sCodMovimiento + "','"
			+ ValoresDefecto.DEF_PENDIENTE + "','"
		    + sUsuario + "','"
		    + Utils.timeStamp() +
			"')");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: COACES:|{}|",sCodCOACES);
			logger.error("ERROR: NURCAT:|{}|",sCodNURCAT);
			logger.error("ERROR: COSBAC:|{}|",sCodCOSBAC);

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

	public static boolean delRelacionImpuestos(String sCodMovimiento) 
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField4 + " = '" + sCodMovimiento +"')");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: CodMovimiento:|{}|",sCodMovimiento);

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
	
	public static boolean existeRelacionImpuesto(String sCodNURCAT, String sCodCOSBAC, String sCodCOACES, String sCodMovimiento)
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField4 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodCOACES + "' " +
					" AND " + sField2 + " = '" + sCodNURCAT + "' " +
					" AND " + sField3 + " = '" + sCodCOSBAC + "' " +
					" AND " + sField4 + " = '" + sCodMovimiento +"' )");

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
			logger.error("ERROR: COACES:|{}|",sCodCOACES);
			logger.error("ERROR: NURCAT:|{}|",sCodNURCAT);
			logger.error("ERROR: COSBAC:|{}|",sCodCOSBAC);
			logger.error("ERROR: Movimiento:|{}|",sCodMovimiento);


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

	public static boolean compruebaRelacionImpuestoActivo(String sCodNURCAT, String sCodCOSBAC, String sCodCOACES)
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " + sField4 + "  FROM " + sTable + 
				" WHERE " +
				"(" + sField1 + " = '" + sCodCOACES + "' " +
				" AND " + sField2 + " = '" + sCodNURCAT + "' " +
				" AND " + sField3 + " = '" + sCodCOSBAC +"' )";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField4 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodCOACES + "' " +
					" AND " + sField2 + " = '" + sCodNURCAT + "' " +
					" AND " + sField3 + " = '" + sCodCOSBAC +"' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",sField1,sCodCOACES);
					logger.debug("{}:|{}|",sField2,sCodNURCAT);
					logger.debug("{}:|{}|",sField3,sCodCOSBAC);
				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: COACES:|{}|",sCodCOACES);
			logger.error("ERROR: NURCAT:|{}|",sCodNURCAT);
			logger.error("ERROR: COSBAC:|{}|",sCodCOSBAC);


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

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField4+ "  FROM " + sTable + 
					" WHERE (" + sField5 + " = '" + sEstado + "' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
		
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(sField4));
										
					logger.debug("Encontrado el registro!");
					
					logger.debug("{}:|{}|",sField5,sEstado);
					logger.debug("{}:|{}|",sField4,result.get(i));

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
			logger.error("ERROR: Validado:|{}|",sEstado);

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
					
					   + QMActivos.sField1 + ","        
					   + QMActivos.sField14 + ","
					   + QMActivos.sField11 + ","
					   + QMActivos.sField13 + ","
					   + QMActivos.sField6 + ","
					   + QMActivos.sField9 + ","
					   + QMActivos.sField7 + ","
					   + QMActivos.sField10 + ","
					   + QMActivos.sField81 + 

					   " FROM " + QMActivos.sTable + 
					   " WHERE ("

					   + QMActivos.sField14 + " LIKE '%" + activo.getCOPOIN()	+ "%' AND "  
					   + QMActivos.sField11 + " LIKE '%" + activo.getNOMUIN()	+ "%' AND "  
					   + QMActivos.sField13 + " LIKE '%" + activo.getNOPRAC()	+ "%' AND "  
					   + QMActivos.sField6 + " LIKE '%" + activo.getNOVIAS()	+ "%' AND "  
					   + QMActivos.sField9 + " LIKE '%" + activo.getNUPIAC()	+ "%' AND "  
					   + QMActivos.sField7 + " LIKE '%" + activo.getNUPOAC()	+ "%' AND "  
					   + QMActivos.sField10 + " LIKE '%" + activo.getNUPUAC()	+ "%' AND "			

					   + QMActivos.sField1 +" IN (SELECT "
					   +  sField1 + 
					   " FROM " + sTable + "))";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement("SELECT "
					
					   + QMActivos.sField1 + ","        
					   + QMActivos.sField14 + ","
					   + QMActivos.sField11 + ","
					   + QMActivos.sField13 + ","
					   + QMActivos.sField6 + ","
					   + QMActivos.sField9 + ","
					   + QMActivos.sField7 + ","
					   + QMActivos.sField10 + ","
					   + QMActivos.sField81 + 

					   " FROM " + QMActivos.sTable + 
					   " WHERE ("

					   + QMActivos.sField14 + " LIKE '%" + activo.getCOPOIN()	+ "%' AND "  
					   + QMActivos.sField11 + " LIKE '%" + activo.getNOMUIN()	+ "%' AND "  
					   + QMActivos.sField13 + " LIKE '%" + activo.getNOPRAC()	+ "%' AND "  
					   + QMActivos.sField6 + " LIKE '%" + activo.getNOVIAS()	+ "%' AND "  
					   + QMActivos.sField9 + " LIKE '%" + activo.getNUPIAC()	+ "%' AND "  
					   + QMActivos.sField7 + " LIKE '%" + activo.getNUPOAC()	+ "%' AND "  
					   + QMActivos.sField10 + " LIKE '%" + activo.getNUPUAC()	+ "%' AND "			

					   + QMActivos.sField1 +" IN (SELECT "
					   +  sField1 + 
					   " FROM " + sTable + "))");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					sCOACES = rs.getString(QMActivos.sField1);
					sCOPOIN = rs.getString(QMActivos.sField14);
					sNOMUIN = rs.getString(QMActivos.sField11);
					sNOPRAC = rs.getString(QMActivos.sField13);
					sNOVIAS = rs.getString(QMActivos.sField6);
					sNUPIAC = rs.getString(QMActivos.sField9);
					sNUPOAC = rs.getString(QMActivos.sField7);
					sNUPUAC = rs.getString(QMActivos.sField10);
					sNURCAT = rs.getString(QMActivos.sField81);
					
					ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, sNURCAT);
					
					result.add(activoencontrado);
					
					logger.debug("Encontrado el registro!");
					logger.debug("{}:|{}|",QMActivos.sField1,sCOACES);

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
					
					   + QMActivos.sField1 + ","        
					   + QMActivos.sField14 + ","
					   + QMActivos.sField11 + ","
					   + QMActivos.sField13 + ","
					   + QMActivos.sField6 + ","
					   + QMActivos.sField9 + ","
					   + QMActivos.sField7 + ","
					   + QMActivos.sField10 + ","
					   + QMActivos.sField81 + 

					   " FROM " + QMActivos.sTable + 
					   " WHERE ("

					   + QMActivos.sField14 + " LIKE '%" + activo.getCOPOIN()	+ "%' AND "  
					   + QMActivos.sField11 + " LIKE '%" + activo.getNOMUIN()	+ "%' AND "  
					   + QMActivos.sField13 + " LIKE '%" + activo.getNOPRAC()	+ "%' AND "  
					   + QMActivos.sField6 + " LIKE '%" + activo.getNOVIAS()	+ "%' AND "  
					   + QMActivos.sField9 + " LIKE '%" + activo.getNUPIAC()	+ "%' AND "  
					   + QMActivos.sField7 + " LIKE '%" + activo.getNUPOAC()	+ "%' AND "  
					   + QMActivos.sField10 + " LIKE '%" + activo.getNUPUAC()	+ "%' AND "			

					   + QMActivos.sField1 +" IN (SELECT "
					   +  sField1 + 
					   " FROM " + sTable + 
					   " WHERE ("+
					   
					   sField2 +" IN (SELECT "
					   + QMImpuestos.sField1 +
					   " FROM " + QMImpuestos.sTable +
					   " WHERE " +
					   "("
		   			   + QMImpuestos.sField7 + " = 'F' AND " 
		   			   + QMImpuestos.sField6 + " = 'S' AND "
		   			   + QMImpuestos.sField4 + " <= '"+Utils.fechaDeHoy(false)+"' AND "
		   			   + QMImpuestos.sField10 + " = '" + ValoresDefecto.DEF_ALTA + "' " +
		   			   "))"+

		   			   "AND "  

		   			   +sField3 +" IN (SELECT "
					   + QMImpuestos.sField2 +
					   " FROM " + QMImpuestos.sTable +
 					   " WHERE " +
					   "("
		   			   + QMImpuestos.sField7 + " = 'F' AND " 
		   			   + QMImpuestos.sField6 + " = 'S' AND "
		   			   + QMImpuestos.sField4 + " <= '"+Utils.fechaDeHoy(false)+"' AND "
		   			   + QMImpuestos.sField10 + " = '" + ValoresDefecto.DEF_ALTA + "' " +
		   			   "))"
		   			   
		   			   + ")))";
		
		
	
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement("SELECT "
					
					   + QMActivos.sField1 + ","        
					   + QMActivos.sField14 + ","
					   + QMActivos.sField11 + ","
					   + QMActivos.sField13 + ","
					   + QMActivos.sField6 + ","
					   + QMActivos.sField9 + ","
					   + QMActivos.sField7 + ","
					   + QMActivos.sField10 + ","
					   + QMActivos.sField81 + 

					   " FROM " + QMActivos.sTable + 
					   " WHERE ("

					   + QMActivos.sField14 + " LIKE '%" + activo.getCOPOIN()	+ "%' AND "  
					   + QMActivos.sField11 + " LIKE '%" + activo.getNOMUIN()	+ "%' AND "  
					   + QMActivos.sField13 + " LIKE '%" + activo.getNOPRAC()	+ "%' AND "  
					   + QMActivos.sField6 + " LIKE '%" + activo.getNOVIAS()	+ "%' AND "  
					   + QMActivos.sField9 + " LIKE '%" + activo.getNUPIAC()	+ "%' AND "  
					   + QMActivos.sField7 + " LIKE '%" + activo.getNUPOAC()	+ "%' AND "  
					   + QMActivos.sField10 + " LIKE '%" + activo.getNUPUAC()	+ "%' AND "			

					   + QMActivos.sField1 +" IN (SELECT "
					   +  sField1 + 
					   " FROM " + sTable + 
					   " WHERE ("+
					   
					   sField2 +" IN (SELECT "
					   + QMImpuestos.sField1 + 
					   " FROM " + QMImpuestos.sTable +
					   " WHERE " +
					   "("
		   			   + QMImpuestos.sField7 + " = 'F' AND " 
		   			   + QMImpuestos.sField6 + " = 'S' AND "
		   			   + QMImpuestos.sField4 + " <= '"+Utils.fechaDeHoy(false)+"' AND "
		   			   + QMImpuestos.sField10 + " = '" + ValoresDefecto.DEF_ALTA + "' " +
		   			   "))"+

		   			   "AND "  

		   			   +sField3 +" IN (SELECT "
					   + QMImpuestos.sField2 + 
					   " FROM " + QMImpuestos.sTable +
					   " WHERE " +
					   "("
		   			   + QMImpuestos.sField7 + " = 'F' AND " 
		   			   + QMImpuestos.sField6 + " = 'S' AND "
		   			   + QMImpuestos.sField4 + " <= '"+Utils.fechaDeHoy(false)+"' AND "
		   			   + QMImpuestos.sField10 + " = '" + ValoresDefecto.DEF_ALTA + "' " +
		   			   "))"
		   			   
		   			   + ")))");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					sCOACES = rs.getString(QMActivos.sField1);
					sCOPOIN = rs.getString(QMActivos.sField14);
					sNOMUIN = rs.getString(QMActivos.sField11);
					sNOPRAC = rs.getString(QMActivos.sField13);
					sNOVIAS = rs.getString(QMActivos.sField6);
					sNUPIAC = rs.getString(QMActivos.sField9);
					sNUPOAC = rs.getString(QMActivos.sField7);
					sNUPUAC = rs.getString(QMActivos.sField10);
					sNURCAT = rs.getString(QMActivos.sField81);
					
					ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, sNURCAT);
					
					result.add(activoencontrado);
					
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",QMActivos.sField1,sCOACES);

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
					
					   + QMImpuestos.sField1 + ","        
					   + QMImpuestos.sField2 + ","
					   + QMImpuestos.sField3 + ","
					   + QMImpuestos.sField4 + ","
					   + QMImpuestos.sField5 + ","
					   + QMImpuestos.sField6 + ","
					   + QMImpuestos.sField7 + ","
					   + QMImpuestos.sField8 + ","
					   + QMImpuestos.sField9 +
					    

					   " FROM " + QMImpuestos.sTable + 
					   " WHERE ("

					   + QMImpuestos.sField10 + " = '" + ValoresDefecto.DEF_ALTA + "' AND "  

					   + QMImpuestos.sField1 +" IN (SELECT "
					   +  sField2 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND "  

					   + QMImpuestos.sField2 +" IN (SELECT "
					   +  sField3 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) )";					   
					   
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement("SELECT "
					
					   + QMImpuestos.sField1 + ","        
					   + QMImpuestos.sField2 + ","
					   + QMImpuestos.sField3 + ","
					   + QMImpuestos.sField4 + ","
					   + QMImpuestos.sField5 + ","
					   + QMImpuestos.sField6 + ","
					   + QMImpuestos.sField7 + ","
					   + QMImpuestos.sField8 + ","
					   + QMImpuestos.sField9 +
					    

					   " FROM " + QMImpuestos.sTable + 
					   " WHERE ("

					   + QMImpuestos.sField10 + " = '" + ValoresDefecto.DEF_ALTA + "' AND "  

					   + QMImpuestos.sField1 +" IN (SELECT "
					   +  sField2 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND "  

					   + QMImpuestos.sField2 +" IN (SELECT "
					   +  sField3 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) )");

			


			

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					

					
					sCOSBAC     = rs.getString(QMImpuestos.sField2);
					sDesCOSBAC  = QMCodigosControl.getDesCampo(QMCodigosControl.TCOSBGAT21,QMCodigosControl.ICOSBGAT21,sCOSBAC);
					sFEPRRE     = Utils.recuperaFecha(rs.getString(QMImpuestos.sField3));
					sFERERE     = Utils.recuperaFecha(rs.getString(QMImpuestos.sField4));
					sFEDEIN     = Utils.recuperaFecha(rs.getString(QMImpuestos.sField5));
					sBISODE     = rs.getString(QMImpuestos.sField6);
					sDesBISODE  = QMCodigosControl.getDesCampo(QMCodigosControl.TBINARIA,QMCodigosControl.IBINARIA,sBISODE);
					sBIRESO     = rs.getString(QMImpuestos.sField7);
					sDesBIRESO  = QMCodigosControl.getDesCampo(QMCodigosControl.TBIRESO,QMCodigosControl.IBIRESO,sBIRESO);
					sOBTEXC     = rs.getString(QMImpuestos.sField9);  

					
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
					
					logger.debug("{}:|{}|",sField1,sCodCOACES);

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
					
					   + QMImpuestos.sField1 + ","        
					   + QMImpuestos.sField2 + ","
					   + QMImpuestos.sField3 + ","
					   + QMImpuestos.sField4 + ","
					   + QMImpuestos.sField5 + ","
					   + QMImpuestos.sField6 + ","
					   + QMImpuestos.sField7 + ","
					   + QMImpuestos.sField8 + ","
					   + QMImpuestos.sField9 +
					    

					   " FROM " + QMImpuestos.sTable + 
					   " WHERE ("

					   + QMImpuestos.sField7 + " = 'F' AND " 
					   + QMImpuestos.sField6 + " = 'S' AND "
					   + QMImpuestos.sField4 + " <= '"+Utils.fechaDeHoy(false)+"' AND "
					   + QMImpuestos.sField10 + " = '" + ValoresDefecto.DEF_ALTA + "' " +

					   "AND "  

					   + QMImpuestos.sField1 +" IN (SELECT "
					   +  sField2 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND "  

					   + QMImpuestos.sField2 +" IN (SELECT "
					   +  sField3 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) )";				   
					   
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement("SELECT "
					
					   + QMImpuestos.sField1 + ","        
					   + QMImpuestos.sField2 + ","
					   + QMImpuestos.sField3 + ","
					   + QMImpuestos.sField4 + ","
					   + QMImpuestos.sField5 + ","
					   + QMImpuestos.sField6 + ","
					   + QMImpuestos.sField7 + ","
					   + QMImpuestos.sField8 + ","
					   + QMImpuestos.sField9 +
					    

					   " FROM " + QMImpuestos.sTable + 
					   " WHERE ("

					   + QMImpuestos.sField7 + " = 'F' AND " 
					   + QMImpuestos.sField6 + " = 'S' AND "
					   + QMImpuestos.sField4 + " <= '"+Utils.fechaDeHoy(false)+"' AND "
					   + QMImpuestos.sField10 + " = '" + ValoresDefecto.DEF_ALTA + "' " +

					   "AND "  

					   + QMImpuestos.sField1 +" IN (SELECT "
					   +  sField2 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND "  

					   + QMImpuestos.sField2 +" IN (SELECT "
					   +  sField3 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) )");

			


			

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					

					
					sCOSBAC     = rs.getString(QMImpuestos.sField2);
					sDesCOSBAC  = QMCodigosControl.getDesCampo(QMCodigosControl.TCOSBGAT21,QMCodigosControl.ICOSBGAT21,sCOSBAC);
					sFEPRRE     = Utils.recuperaFecha(rs.getString(QMImpuestos.sField3));
					sFERERE     = Utils.recuperaFecha(rs.getString(QMImpuestos.sField4));
					sFEDEIN     = Utils.recuperaFecha(rs.getString(QMImpuestos.sField5));
					sBISODE     = rs.getString(QMImpuestos.sField6);
					sDesBISODE  = QMCodigosControl.getDesCampo(QMCodigosControl.TBINARIA,QMCodigosControl.IBINARIA,sBISODE);
					sBIRESO     = rs.getString(QMImpuestos.sField7);
					sDesBIRESO  = QMCodigosControl.getDesCampo(QMCodigosControl.TBIRESO,QMCodigosControl.IBIRESO,sBIRESO);
					sOBTEXC     = rs.getString(QMImpuestos.sField9);  

					
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
					
					logger.debug("{}:|{}|",sField1,sCodCOACES);

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

	public static boolean setValidado(String sCodMovimiento, String sValidado)
	{
		Statement stmt = null;
		boolean bSalida = true;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField5 + " = '"+ sValidado + 
					"' "+
					" WHERE "+
					"(" + sField4 + " = '" + sCodMovimiento +"' )");
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: Movimiento:|{}|",sCodMovimiento);

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

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField5 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField4 + " = '" + sCodMovimiento +"' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sValidado = rs.getString(sField5);

					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",sField5,sValidado);
				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: Movimiento:|{}|",sCodMovimiento);

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

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM " + sTable + 
					" WHERE " +
					"(" + sField5 + " = '" + sCodValidado + "')");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					liNumero = rs.getLong("COUNT(*)");
					
					logger.debug("Encontrado el registro!");

					logger.debug( "Numero de registros:|{}|",liNumero);


				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: CodValidado:|{}|",sCodValidado);

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

		try 
		{

			
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT " 
					+ sField4  + 
					"  FROM " + sTable + 
					" WHERE " +
					"(" 
					+ sField1 + " = '" + sCodCOACES + "' AND "
					+ sField2 + " = '" + sCodNURCAT + "' AND "
					+ sField3 + " = '" + sCodCOSBAC + "' AND "
					+ sField4 + " >=  '" + sCodMovimiento + "')");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					result.add(rs.getString(sField4));

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
			logger.error("ERROR: COACES:|{}|",sCodCOACES);
			logger.error("ERROR: NURCAT:|{}|",sCodNURCAT);
			logger.error("ERROR: COSBAC:|{}|",sCodCOSBAC);
			logger.error("ERROR: Movimiento:|{}|",sCodMovimiento);

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
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
