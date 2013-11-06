package com.provisiones.dal.qm.listas;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.ReferenciaTabla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QMListaReferencias
{
	private static Logger logger = LoggerFactory.getLogger(QMListaReferencias.class.getName());

	static String TABLA = "pp001_lista_referencias_multi";

	static String CAMPO1  = "cod_nurcat";
	static String CAMPO2  = "cod_coaces";    
	static String CAMPO3  = "cod_movimiento";    
	static String CAMPO4  = "cod_validado";
	
	static String CAMPO5  = "usuario_movimiento";    
	static String CAMPO6  = "fecha_movimiento";

	public static boolean addRelacionReferencia(String sCodNURCAT, String sCodCOACES, String sCodMovimiento)
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
				   + CAMPO1  + "," 
			       + CAMPO2  + ","              
			       + CAMPO3  + ","              
			       + CAMPO4  + ","              
			       + CAMPO5  + ","              
			       + CAMPO6  +    
			       ") VALUES ('" 
			       + sCodNURCAT + "','" 
			       + sCodCOACES + "','"
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
			bSalida = false;

			logger.error("ERROR NURCAT:|"+sCodNURCAT+"|");
			logger.error("ERROR COACES:|"+sCodCOACES+"|");
			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");
			
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static boolean delRelacionReferencia(String sCodMovimiento)
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "DELETE FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO3 + " = '" + sCodMovimiento	+ "'";
		
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
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static boolean existeRelacionReferencia(String sCodNURCAT, String sCodCOACES, String sCodMovimiento)
	{
		Statement stmt = null;
		ResultSet rs = null;

		PreparedStatement pstmt = null;
		boolean found = false;

		boolean bSalida = true; 
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
				+ CAMPO4  +               
			    " FROM " 
				+ TABLA + 
				" WHERE (" 
				+ CAMPO1 + " = '" + sCodNURCAT + "' AND " 
				+ CAMPO2 + " = '" + sCodCOACES + "' AND " 
				+ CAMPO3 + " = '" + sCodMovimiento	+ 
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
			logger.error("ERROR NURCAT:|"+sCodNURCAT+"|");
			logger.error("ERROR COACES:|"+sCodCOACES+"|");
			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return (found && bSalida);
	}
	
	public static boolean compruebaRelacionReferenciaActivo(String sCodNURCAT, String sCodCOACES)
	{
		Statement stmt = null;
		ResultSet rs = null;

		PreparedStatement pstmt = null;
		boolean found = false;

		boolean bSalida = true; 
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
				+ CAMPO4  +               
			    " FROM " 
				+ TABLA + 
				" WHERE (" 
				+ CAMPO1 + " = '" + sCodNURCAT + "' AND " 
				+ CAMPO2 + " = '" + sCodCOACES + 
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
			logger.error("ERROR NURCAT:|"+sCodNURCAT+"|");
			logger.error("ERROR COACES:|"+sCodCOACES+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return (found && bSalida);
	}
	
	public static ArrayList<String>  getReferenciasPorEstado(String sEstado) 
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
				" WHERE " 
				+ CAMPO4 + " = '" + sEstado + "'";
		
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

					result.add(rs.getString(CAMPO3));
										
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO4+":|"+sEstado+"|");
					logger.debug(CAMPO3+":|"+result.get(i)+"|");
					
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
				+ CAMPO4 + " = '"+ sValidado + "' "+
				" WHERE "
				+ CAMPO3 + " = '" + sCodMovimiento	+ "'";
		
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
				+ CAMPO4 + 
				" FROM " 
				+ TABLA + 
				" WHERE "
				+ CAMPO3 + " = '" + sCodMovimiento	+ "'";
		
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

					sValidado = rs.getString(CAMPO4);
					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO4+":|"+sValidado+"|");
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
				+ CAMPO4 + " = '" + sCodValidado + "'";
		
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

		//ConnectionManager.CloseDBConnection(conn);
		return liNumero;
	}
	
	public static ArrayList<ReferenciaTabla> buscaReferenciasActivo(String sCodCOACES)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sNURCAT = "";
		String sTIRCAT = "";
		String sENEMIS = "";
		String sOBTEXC = "";
		
		String sIMVSUE = "";
		String sIMCATA = "";
		String sFERECA = "";
		
		ArrayList<ReferenciaTabla> result = new ArrayList<ReferenciaTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
				   + QMReferencias.CAMPO1 + ","        
				   + QMReferencias.CAMPO2 + ","
				   + QMReferencias.CAMPO3 + ","
				   + QMReferencias.CAMPO5 + 

				   //Ampliacion de valor catastral
				   ","
				   + QMReferencias.CAMPO6 + ","
				   + QMReferencias.CAMPO7 + ","
				   + QMReferencias.CAMPO8 + 
				   
				   "  FROM " 
				   + QMReferencias.TABLA + 
				   " WHERE " 
				   + QMReferencias.CAMPO9 + " = '" + ValoresDefecto.DEF_ALTA + "' AND "
				   + QMReferencias.CAMPO1 + " IN " +
				   "(SELECT " + CAMPO1 + 
				   " FROM " 
				   + TABLA +
				   " WHERE "
				   + CAMPO2 +  " = '" + sCodCOACES	+ "')";
		
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
					
					sNURCAT = rs.getString(QMReferencias.CAMPO1);
					sTIRCAT = rs.getString(QMReferencias.CAMPO2);
					sENEMIS = rs.getString(QMReferencias.CAMPO3);
					sOBTEXC = rs.getString(QMReferencias.CAMPO5);

					//Ampliacion de valor catastral
					sIMVSUE = Utils.recuperaImporte(false,rs.getString(QMReferencias.CAMPO6));
					sIMCATA = Utils.recuperaImporte(false,rs.getString(QMReferencias.CAMPO7));
					sFERECA = Utils.recuperaFecha(rs.getString(QMReferencias.CAMPO8));
					
					ReferenciaTabla referenciaencontrada = new ReferenciaTabla(sNURCAT, sTIRCAT, sENEMIS, sOBTEXC

							//Ampliacion de valor catastral
							, sIMVSUE, sIMCATA, sFERECA
							
							);
					
					result.add(referenciaencontrada);
					
					logger.debug("Encontrado el registro!");
					logger.debug(CAMPO3+":|"+sCodCOACES+"|");
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
	
	
	public static ArrayList<ActivoTabla> buscaActivosNoAsociados(ActivoTabla activo)
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

					   + QMActivos.CAMPO1 +" NOT IN (SELECT "
					   +  CAMPO2 + 
					   " FROM " 
					   + TABLA + 
					   " WHERE "
					   
						+ CAMPO1 + " IN (SELECT "
						+ QMReferencias.CAMPO1 +
						"  FROM " 
						+ QMReferencias.TABLA + 
						" WHERE " + QMReferencias.CAMPO9 + " = '" + ValoresDefecto.DEF_ALTA + "' )))";
		
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
		//ConnectionManager.CloseDBConnection(conn);
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
					   +  CAMPO2 + 
					   " FROM " 
					   + TABLA +
					   " WHERE " 
					   
					   + CAMPO1 + " IN (SELECT "
   					   + QMReferencias.CAMPO1 + 
   					   " FROM " 
   					   + QMReferencias.TABLA +
   					   " WHERE " 
   					   + QMReferencias.CAMPO9 + " = '"+ ValoresDefecto.DEF_ALTA + "')))";
		
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
		//ConnectionManager.CloseDBConnection(conn);
		return result;

	}
	
	public static ArrayList<ActivoTabla> buscaListaActivosReferencias(ActivoTabla activo)
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
					   + QMActivos.CAMPO10 + " LIKE '%" + activo.getNUPUAC()	+ "%' )";
		
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
		//ConnectionManager.CloseDBConnection(conn);
		return result;

	}
	

	public static boolean activoAsociado(String sCodCOACES)
	{
		Statement stmt = null;
		ResultSet rs = null;
		
		boolean bSalida = true;

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");

		String sQuery = "SELECT "
			    + CAMPO1  +
			    "  FROM " 
			    + TABLA + 
			    " WHERE "
			    + CAMPO2 + " = '" + sCodCOACES + "'";
		
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

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return (found && bSalida);
	}
	
	public static String referenciaAsociada(String sCodCOACES)
	{
		Statement stmt = null;
		ResultSet rs = null;
		
		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		String sReferencia = "";
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");

		String sQuery = "SELECT "
			    + CAMPO1  +
			    " FROM " 
			    + TABLA + 
			    " WHERE "
			    + CAMPO2 + " = '" + sCodCOACES + "'";
		
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
					
					sReferencia = rs.getString(CAMPO1);

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

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return sReferencia;
	}
	
	public static ArrayList<String> buscarDependencias(String sCodNURCAT, String sCodCOACES, String sCodMovimiento)
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
				+ CAMPO3  + 
				" FROM " 
				+ TABLA + 
				" WHERE (" 
				+ CAMPO1 + " = '" + sCodNURCAT + "' AND "
				+ CAMPO2 + " = '" + sCodCOACES + "' AND "
				+ CAMPO3 + " >=  '" + sCodMovimiento + 
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
					
					result.add(rs.getString(CAMPO3));

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
			logger.error("ERROR NURCAT:|"+sCodNURCAT+"|");
			logger.error("ERROR COACES:|"+sCodCOACES+"|");
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