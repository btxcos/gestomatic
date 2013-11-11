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


	public static boolean addRelacionGasto(String sCodGasto, String sCodMovimiento) 
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		String sUsuario = ConnectionManager.getUser();

		conn = ConnectionManager.getDBConnection();
		
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
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR GASTO:|"+sCodGasto+"|");
			logger.error("ERROR MOVIMIENTO:|"+sCodMovimiento+"|");

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

	public static boolean delRelacionGasto(String sCodMovimiento) 
	{
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "DELETE FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO2 + " = '" + sCodMovimiento +"'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR MOVIMIENTO:|"+sCodMovimiento+"|");

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
	
	public static boolean existeRelacionGasto(String sCodGasto, String sCodMovimiento)
	{
		Statement stmt = null;
		ResultSet rs = null;

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
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
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement(sQuery);


			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR GASTO:|"+sCodGasto+"|");
			logger.error("ERROR MOVIMIENTO:|"+sCodMovimiento+"|");

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
	
	public static ArrayList<String>  getGastosPorEstado(String sEstado) 
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
				+ CAMPO2 + 
				" FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO3 + " = '" + sEstado + "'";
		
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

					result.add(rs.getString(CAMPO2));
										
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO3+":|"+sEstado+"|");
					logger.debug(CAMPO2+":|"+result.get(i)+"|");

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
				+ CAMPO3 + " = '"+ sValidado + "' "+
				" WHERE "
				+ CAMPO2 + " = '"+ sCodMovimiento +"'";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Gasto:|"+sCodMovimiento+"|");

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
				+ CAMPO3 + 
				" FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO2 + " = '" + sCodMovimiento + "'";
		
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

					sValidado = rs.getString(CAMPO3);

					logger.debug("Encontrado el registro!");
					logger.debug(CAMPO2+":|"+sCodMovimiento+"|");
					logger.debug(CAMPO3+":|"+sValidado+"|");
				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Gasto:|"+sCodMovimiento+"|");

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
				+ CAMPO3 + " = '" + sCodValidado + "'";
		
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
	

	
	public static ArrayList<ActivoTabla> buscaActivosConGastosValidados(ActivoTabla activo)
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
   					   + CAMPO3 + " = '"+ ValoresDefecto.DEF_MOVIMIENTO_VALIDADO + "' "+
   					   		
   					   	")))";
		
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
	
	public static ArrayList<GastoTabla> buscaGastosValidadosActivo(String sCodCOACES)
	{
		Statement stmt = null;
		ResultSet rs = null;

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
		
		ArrayList<GastoTabla> result = new ArrayList<GastoTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
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
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					   
					
					sCOACES  = rs.getString(QMGastos.CAMPO2);
					sCOGRUG  = rs.getString(QMGastos.CAMPO3);
					sCOTPGA  = rs.getString(QMGastos.CAMPO4);
					sCOSBGA  = rs.getString(QMGastos.CAMPO5);
					sDCOSBGA = QMCodigosControl.getDesCOSBGA(sCOGRUG,sCOTPGA,sCOSBGA);
					sPTPAGO  = rs.getString(QMGastos.CAMPO6);
					sDPTPAGO = QMCodigosControl.getDesCampo(QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
					sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO7));
					sCOSIGA  = rs.getString(QMGastos.CAMPO11);
					sDCOSIGA = QMCodigosControl.getDesCampo(QMCodigosControl.TCOSIGA,QMCodigosControl.ICOSIGA,sCOSIGA);
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
					
					result.add(gastoencontrado);
					
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
	
	public static ArrayList<String> buscarDependencias(String sCodGasto, String sCodMovimiento)
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

			
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					result.add(rs.getString(CAMPO2));

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
			logger.error("ERROR GASTO:|"+sCodGasto+"|");
			logger.error("ERROR MOVIMIENTO:|"+sCodMovimiento+"|");

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
	
	public static ArrayList<String> buscarMovimientosGasto(String sCodGasto)
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
				+ CAMPO2  + 
				"  FROM " + TABLA + 
				" WHERE "
				+ CAMPO1 + " = '" + sCodGasto + "'";
		
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
					
					result.add(rs.getString(CAMPO2));

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
			logger.error("ERROR GASTO:|"+sCodGasto+"|");

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
	
	public static ArrayList<String> buscarMovimientosValidadosGasto(String sCodGasto)
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
				   + CAMPO1 + 
				   " FROM " 
				   + TABLA + 
				   " WHERE " 
				   + CAMPO3 + " = '"+ ValoresDefecto.DEF_MOVIMIENTO_VALIDADO + "'";
		
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
					
					result.add(rs.getString(CAMPO2));

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
			logger.error("ERROR GASTO:|"+sCodGasto+"|");

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
