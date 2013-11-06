package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Cuota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QMCuotas
{
	private static Logger logger = LoggerFactory.getLogger(QMCuotas.class.getName());
	
	public static final String TABLA = "pp001_e2_cuotas_tbl";

	public static final String CAMPO1  = "cod_coaces";
	public static final String CAMPO2  = "cod_cocldo";
	public static final String CAMPO3  = "cod_nudcom";
	public static final String CAMPO4  = "cod_cosbac";
	public static final String CAMPO5  = "fipago";    
	public static final String CAMPO6  = "ffpago";    
	public static final String CAMPO7  = "imcuco";    
	public static final String CAMPO8  = "faacta";    
	public static final String CAMPO9  = "cod_ptpago";
	public static final String CAMPO10  = "obtexc";

	public static final String CAMPO11 = "cod_estado";

	public static boolean addCuota(Cuota NuevaCuota)

	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");

		try {

			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + TABLA + " ("
					   + CAMPO1  + ","  
				       + CAMPO2  + ","              
				       + CAMPO3  + ","              
				       + CAMPO4  + ","              
				       + CAMPO5  + ","              
				       + CAMPO6  + ","              
				       + CAMPO7  + ","              
				       + CAMPO8  + ","
				       + CAMPO9  + ","
				       + CAMPO10  + ","
				       + CAMPO11  + 
				       ") VALUES ('"
				       + NuevaCuota.getCOACES() + "','"
				       + NuevaCuota.getCOCLDO() + "','"
				       + NuevaCuota.getNUDCOM() + "','"
				       + NuevaCuota.getCOSBAC() + "','"
				       + NuevaCuota.getFIPAGO() + "','"
				       + NuevaCuota.getFFPAGO() + "','"
				       + NuevaCuota.getIMCUCO() + "','"
				       + NuevaCuota.getFAACTA() + "','"
				       + NuevaCuota.getPTPAGO() + "','"
				       + NuevaCuota.getOBTEXC() + "','" 
				       + ValoresDefecto.DEF_ALTA + "' )");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR COACES:|"+NuevaCuota.getCOACES()+"|");
			logger.error("ERROR COCLDO:|"+NuevaCuota.getCOCLDO()+"|");
			logger.error("ERROR NUDCOM:|"+NuevaCuota.getNUDCOM()+"|");
			logger.error("ERROR COSBAC:|"+NuevaCuota.getCOSBAC()+"|");
			
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
	public static boolean modCuota(Cuota NuevaCuota, String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + TABLA + 
					" SET " 
					+ CAMPO5  + " = '"+ NuevaCuota.getFIPAGO() + "', "
					+ CAMPO6  + " = '"+ NuevaCuota.getFFPAGO() + "', "
					+ CAMPO7  + " = '"+ NuevaCuota.getIMCUCO() + "', "
					+ CAMPO8  + " = '"+ NuevaCuota.getFAACTA() + "', "
					+ CAMPO9  + " = '"+ NuevaCuota.getPTPAGO() + "', "
					+ CAMPO10 + " = '"+ NuevaCuota.getOBTEXC() + 
					"' "+
					" WHERE " +
					"("	+ CAMPO1  + " = '"+ sCodCOACES +"' AND " +
						CAMPO2  + " = '"+ sCodCOCLDO +"' AND " +
						CAMPO3  + " = '"+ sCodNUDCOM +"' AND " +
					    CAMPO4  + " = '"+ sCodCOSBAC + "' )");
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR COACES:|"+NuevaCuota.getCOACES()+"|");
			logger.error("ERROR COCLDO:|"+NuevaCuota.getCOCLDO()+"|");
			logger.error("ERROR NUDCOM:|"+NuevaCuota.getNUDCOM()+"|");
			logger.error("ERROR COSBAC:|"+NuevaCuota.getCOSBAC()+"|");

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

	public static boolean delCuota(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + TABLA + 
					" WHERE " +
					"("	+ CAMPO1  + " = '"+ sCodCOACES +"' AND " +
					CAMPO2  + " = '"+ sCodCOCLDO +"' AND " +
					CAMPO3  + " = '"+ sCodNUDCOM +"' AND " +
				    CAMPO4  + " = '"+ sCodCOSBAC + "' )");
			
			logger.debug("Ejecutada con exito!");
			
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
	
	public static boolean existeCuota(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();
		
		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		boolean found = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + CAMPO11 + "  FROM " + TABLA + 
					" WHERE " +
					"("	+ CAMPO1  + " = '"+ sCodCOACES +"' AND " +
					CAMPO2  + " = '"+ sCodCOCLDO +"' AND " +
					CAMPO3  + " = '"+ sCodNUDCOM +"' AND " +
				    CAMPO4  + " = '"+ sCodCOSBAC + "' )");

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

	public static Cuota getCuota(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sCOACES = "";
		String sCOCLDO = "";
		String sNUDCOM = "";
		String sCOSBAC = "";
		String sFIPAGO = "";
		String sFFPAGO = "";
		String sIMCUCO = "";
		String sFAACTA = "";
		String sPTPAGO = "";
		String sOBTEXC = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					   + CAMPO1  + ","
					   + CAMPO2  + ","              
				       + CAMPO3  + ","              
				       + CAMPO4  + ","              
				       + CAMPO5  + ","              
				       + CAMPO6  + ","              
				       + CAMPO7  + ","              
				       + CAMPO8  + ","
				       + CAMPO9  + ","
				       + CAMPO10  +       
				       "  FROM " + TABLA + 
					" WHERE " +
					"("	+ CAMPO1  + " = '"+ sCodCOACES +"' AND " +
					CAMPO2  + " = '"+ sCodCOCLDO +"' AND " +
					CAMPO3  + " = '"+ sCodNUDCOM +"' AND " +
				    CAMPO4  + " = '"+ sCodCOSBAC + "' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCOACES = rs.getString(CAMPO1);
					sCOCLDO = rs.getString(CAMPO2);
					sNUDCOM = rs.getString(CAMPO3);
					sCOSBAC = rs.getString(CAMPO4);
					sFIPAGO = rs.getString(CAMPO5);
					sFFPAGO = rs.getString(CAMPO6);
					sIMCUCO = rs.getString(CAMPO7);
					sFAACTA = rs.getString(CAMPO8);
					sPTPAGO = rs.getString(CAMPO9);
					sOBTEXC = rs.getString(CAMPO10);
					
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
			logger.error("ERROR NUDCOM:|"+sCOSBAC+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return new Cuota(sCOACES, sCOCLDO, sNUDCOM, sCOSBAC, sFIPAGO, sFFPAGO, sIMCUCO, sFAACTA, sPTPAGO, sOBTEXC);
	}
	
	public static boolean tieneCuotas(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "              
			       + CAMPO3  +       
			       "  FROM " + TABLA + 
				" WHERE " +
				"("	+ CAMPO1  + " = '"+ sCodCOACES +"' AND " +
					CAMPO2  + " = '"+ sCodCOCLDO +"' AND " +
					CAMPO3  + " = '"+ sCodNUDCOM +"' AND " +
			      CAMPO11  + " <> 'B' )";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "              
				       + CAMPO3  +       
				       "  FROM " + TABLA + 
					" WHERE " +
					"("	+ CAMPO1  + " = '"+ sCodCOACES +"' AND " +
					CAMPO2  + " = '"+ sCodCOCLDO +"' AND " +
					CAMPO3  + " = '"+ sCodNUDCOM +"' AND " +
				      CAMPO11  + " <> 'B' )");

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
			logger.error("ERROR COCLDO:|"+sCodCOCLDO+"|");
			logger.error("ERROR NUDCOM:|"+sCodNUDCOM+"|");

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
	
	public static boolean setEstado(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC, String sEstado)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();
		
		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + TABLA + 
					" SET " 
					+ CAMPO11 + " = '"+ sEstado + 
					"' "+
					" WHERE "+
					"("	+ CAMPO1  + " = '"+ sCodCOACES +"' AND " +
					CAMPO2  + " = '"+ sCodCOCLDO +"' AND " +
					CAMPO3  + " = '"+ sCodNUDCOM +"' AND " +
				    CAMPO4  + " = '"+ sCodCOSBAC + "' )");
			
			logger.debug("Ejecutada con exito!");
			
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
	
	public static String getEstado(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sEstado = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + CAMPO11 + "  FROM " + TABLA + 
					" WHERE " +
					"("	+ CAMPO1  + " = '"+ sCodCOACES +"' AND " +
					CAMPO2  + " = '"+ sCodCOCLDO +"' AND " +
					CAMPO3  + " = '"+ sCodNUDCOM +"' AND " +
				    CAMPO4  + " = '"+ sCodCOSBAC + "' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sEstado = rs.getString(CAMPO11);
					
					logger.debug("Encontrado el registro!");
					
					logger.debug(CAMPO11+":|"+sEstado+"|");


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
		return sEstado;
	}
	
}
