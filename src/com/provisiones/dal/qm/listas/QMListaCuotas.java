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
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.CuotaTabla;

public class QMListaCuotas 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaCuotas.class.getName());
	
	static String sTable = "lista_cuotas_multi";

	static String sField1 = "cod_coaces";
	static String sField2 = "cod_cocldo";
	static String sField3 = "cod_nudcom";
	static String sField4 = "cod_cosbac";
	static String sField5 = "cod_movimiento";
	static String sField6 = "cod_validado";
	
	static String sField7  = "usuario_movimiento";    
	static String sField8  = "fecha_movimiento";

	public static boolean addRelacionCuotas(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC, String sCodMovimiento) 
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
			+ sField7 + ","
			+ sField8 +
			") " 
			+ "VALUES ('" 
			+ sCodCOACES + "','"
			+ sCodCOCLDO + "','"
			+ sCodNUDCOM + "','"
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
			logger.error("ERROR: COCLDO:|{}|",sCodCOCLDO);
			logger.error("ERROR: NUDCOM:|{}|",sCodNUDCOM);
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

	public static boolean delRelacionCuotas(String sCodMovimiento)
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
					" WHERE (" + sField5 + " = '" + sCodMovimiento +"')");
			
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
	
	public static boolean compruebaRelacionCuotaActivo(String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC, String sCodCOACES)
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


			pstmt = conn.prepareStatement("SELECT " + sField6 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodCOACES + "' " +
					"AND" + sField2 + " = '" + sCodCOCLDO + "' " +
					"AND" + sField3 + " = '" + sCodNUDCOM + "' " +
					"AND" + sField4 + " = '" + sCodCOSBAC + "' )");


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
			logger.error("ERROR: COCLDO:|{}|",sCodCOCLDO);
			logger.error("ERROR: NUDCOM:|{}|",sCodNUDCOM);
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
	
	public static ArrayList<String>  getCuotasPorEstado(String sEstado) 
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


			pstmt = conn.prepareStatement("SELECT " + sField5+ "  FROM " + sTable + 
					" WHERE (" + sField6 + " = '" + sEstado + "' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
		
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(sField5));
										
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",sField6,sEstado);
					logger.debug("{}:|{}|",sField5,result.get(i));
					
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

	public static ArrayList<String>  getCuotas(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC) 
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


			pstmt = conn.prepareStatement("SELECT " + sField3 + "  FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sCodCOACES + "' " +
							"AND" + sField2 + " = '" + sCodCOCLDO + "'" +
							"AND" + sField3 + " = '" + sCodNUDCOM + "'" +
							"AND" + sField4 + " = '" + sCodCOSBAC + "' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(sField3));

					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",sField1,sCodCOACES);
					logger.debug("{}:|{}|",sField2,sCodCOCLDO);
					logger.debug("{}:|{}|",sField3,sCodNUDCOM);
					logger.debug("{}:|{}|",sField4,sCodCOSBAC);

					
					
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
			logger.error("ERROR: COACES:|{}|",sCodCOACES);
			logger.error("ERROR: COCLDO:|{}|",sCodCOCLDO);
			logger.error("ERROR: NUDCOM:|{}|",sCodNUDCOM);
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

		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField5 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodCOACES + "' " +
							"AND" + sField2 + " = '" + sCodCOCLDO + "' " +
							"AND" + sField3 + " = '" + sCodNUDCOM + "' " +
							"AND" + sField4 + " = '" + sCodCOSBAC + "' " +
							"AND" + sField6 + " = '" + "P" + "' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(sField5));

					logger.debug("Encontrado el registro!");
					
					logger.debug("{}:|{}|",sField1,sCodCOACES);
					logger.debug("{}:|{}|",sField2,sCodCOCLDO);
					logger.debug("{}:|{}|",sField3,sCodNUDCOM);
					logger.debug("{}:|{}|",sField4,sCodCOSBAC);

					logger.debug("{}:|{}|",sField5,result.get(i));

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
			logger.error("ERROR: COACES:|{}|",sCodCOACES);
			logger.error("ERROR: COCLDO:|{}|",sCodCOCLDO);
			logger.error("ERROR: NUDCOM:|{}|",sCodNUDCOM);
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
					+ sField6 + " = '"+ sValidado + 
					"' "+
					" WHERE " +
					"(" + sField5 + " = '" + sCodMovimiento +"' )");
			
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


			pstmt = conn.prepareStatement("SELECT " + sField6 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField5 + " = '" + sCodMovimiento +"' )");


			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sValidado = rs.getString(sField5);
					
					logger.debug("Encontrado el registro!");
					
					logger.debug("{}:|{}|",sField5,sCodMovimiento);
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
					"(" + sField6 + " = '" + sCodValidado + "')");

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
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

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
					   + QMActivos.sField10 + 

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

					   +  QMListaComunidadesActivos.sField3 + 
					   " FROM " + QMListaComunidadesActivos.sTable + 
					   " WHERE ("

					   + QMListaComunidadesActivos.sField1 + " = '" + sCodCOCLDO	+ "' AND "  
					   + QMListaComunidadesActivos.sField2 + " = '" + sCodNUDCOM	+ "' " +
					   	//	"AND "
					   //+ QMListaComunidadesActivos.sField3 + 
					   //" NOT IN  (SELECT "
					   //+  sField1 + 
					   //"  FROM " + sTable +  ")" +
					   ")))");

			


			

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
					
					ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, "");
					
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
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement("SELECT "
					
					   + QMCuotas.sField1 + ","        
					   + QMCuotas.sField2 + ","
					   + QMCuotas.sField3 + ","
					   + QMCuotas.sField4 + ","
					   + QMCuotas.sField5 + ","
					   + QMCuotas.sField6 + ","
					   + QMCuotas.sField7 + ","
					   + QMCuotas.sField8 + ","
					   + QMCuotas.sField9 + ","
					   + QMCuotas.sField10 +
					    

					   " FROM " + QMCuotas.sTable + 
					   " WHERE ("

					   + QMCuotas.sField11 + " = '" + ValoresDefecto.DEF_ALTA + "' AND "  

					   + QMCuotas.sField2 +" IN (SELECT "
					   +  sField2 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND "  

					   + QMCuotas.sField3 +" IN (SELECT "
					   +  sField3 + 
					   " FROM " + sTable + 
					   " WHERE (" 
					   + sField1 + " = '" + sCodCOACES	+ "' ) ) AND "  

					   + QMCuotas.sField4 +" IN (SELECT "
					   +  sField4 + 
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
					
					sCOCLDO     = rs.getString(QMCuotas.sField2);
					sDesCOCLDO  = QMCodigosControl.getDesCampo(QMCodigosControl.TCOCLDO, QMCodigosControl.ICOCLDO, sCOCLDO);
					sNUDCOM     = rs.getString(QMCuotas.sField3);
					sCOSBAC     = rs.getString(QMCuotas.sField4);
					sDesCOSBAC  = QMCodigosControl.getDesCampo(QMCodigosControl.TCOSBGAT22,QMCodigosControl.ICOSBGAT22,sCOSBAC);
					sFIPAGO     = Utils.recuperaFecha(rs.getString(QMCuotas.sField5));
					sFFPAGO     = Utils.recuperaFecha(rs.getString(QMCuotas.sField6));
					sIMCUCO     = Utils.recuperaImporte(false,rs.getString(QMCuotas.sField7));
					sFAACTA     = Utils.recuperaFecha(rs.getString(QMCuotas.sField8));
					sPTPAGO     = rs.getString(QMCuotas.sField9);
					sDesPTPAGO  = QMCodigosControl.getDesCampo(QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
					sOBTEXC     = rs.getString(QMCuotas.sField10);  

					
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
		
		conn = ConnectionManager.OpenDBConnection();
		
		logger.debug("Ejecutando Query...");

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
					   + QMActivos.sField10 + 

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
					   " WHERE " 
					   
					   + sField2 + " IN (SELECT "
   					   + QMCuotas.sField2 + 
   					   " FROM " + QMCuotas.sTable +
   					   " WHERE " + QMCuotas.sField11 + " = '"+ ValoresDefecto.DEF_ALTA + "') AND " 

   					   + sField3 + " IN (SELECT "
   					   + QMCuotas.sField3 + 
   					   " FROM " + QMCuotas.sTable +
   					   " WHERE " + QMCuotas.sField11 + " = '"+ ValoresDefecto.DEF_ALTA + "')))");

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
					
					ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, "");
					
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
}
