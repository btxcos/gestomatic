package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Comunidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QMComunidades
{
	private static Logger logger = LoggerFactory.getLogger(QMComunidades.class.getName());
	
	public static final String TABLA = "pp001_e1_comunidades_tbl";

	public static final String CAMPO1  = "cod_cocldo";
	
	public static final String CAMPO2  = "nudcom_id"; 
	
	public static final String CAMPO3  = "nomcoc";    
	public static final String CAMPO4  = "nodcco";    
	public static final String CAMPO5  = "nomprc";    
	public static final String CAMPO6  = "nutprc";    
	public static final String CAMPO7  = "nomadc";    
	public static final String CAMPO8  = "nutadc";    
	public static final String CAMPO9  = "nodcad";    
	public static final String CAMPO10 = "nuccen";    
	public static final String CAMPO11 = "nuccof";    
	public static final String CAMPO12 = "nuccdi";    
	public static final String CAMPO13 = "nuccnt";    
	public static final String CAMPO14 = "obtexc";

	public static final String CAMPO15 = "cod_estado";
	

	public static boolean addComunidad(Comunidad NuevaComunidad)

	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "INSERT INTO " 
				   + TABLA + 
				   " ("
				   + CAMPO1  + "," 
			       + CAMPO2  + ","              
			       + CAMPO3  + ","              
			       + CAMPO4  + ","              
			       + CAMPO5  + ","              
			       + CAMPO6  + ","              
			       + CAMPO7  + ","              
			       + CAMPO8  + ","              
			       + CAMPO9  + ","              
			       + CAMPO10 + ","              
			       + CAMPO11 + ","              
			       + CAMPO12 + ","              
			       + CAMPO13 + ","
			       + CAMPO14 + "," 
			       + CAMPO15 +               
			       ") VALUES ('" 
			       + NuevaComunidad.getsCOCLDO() + "','" 
			       + NuevaComunidad.getsNUDCOM() + "','"
			       + NuevaComunidad.getsNOMCOC() + "','"
			       + NuevaComunidad.getsNODCCO() + "','"
			       + NuevaComunidad.getsNOMPRC() + "','"
			       + NuevaComunidad.getsNUTPRC() + "','"
			       + NuevaComunidad.getsNOMADC() + "','"
			       + NuevaComunidad.getsNUTADC() + "','"
			       + NuevaComunidad.getsNODCAD() + "','"
			       + NuevaComunidad.getsNUCCEN() + "','"
			       + NuevaComunidad.getsNUCCOF() + "','"
			       + NuevaComunidad.getsNUCCDI() + "','"
			       + NuevaComunidad.getsNUCCNT() + "','"
			       + NuevaComunidad.getsOBTEXC() + "','" 
			       + ValoresDefecto.DEF_ALTA + "' )";
		
		logger.debug(sQuery);

		try 
		{

			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{


			
			logger.error("ERROR COCLDO:|"+NuevaComunidad.getsCOCLDO()+"|");
			logger.error("ERROR NUDCOM:|"+NuevaComunidad.getsNUDCOM()+"|");
			
			logger.error("ERROR SQLException:",ex.getMessage());
			logger.error("ERROR SQLState:",ex.getSQLState());
			logger.error("ERROR VendorError:",ex.getErrorCode());
			
			bSalida = false;
		} 
		finally
		{

			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	public static boolean modComunidad(Comunidad NuevaComunidad, String sCodCOCLDO, String sCodNUDCOM)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				//+ CAMPO1  + " = '"+ NuevaComunidad.getsCOCLDO() + "', "
				//+ CAMPO2  + " = '"+ NuevaComunidad.getsNUDCOM() + "', "
				+ CAMPO3  + " = '"+ NuevaComunidad.getsNOMCOC() + "', "
				+ CAMPO4  + " = '"+ NuevaComunidad.getsNODCCO() + "', "
				+ CAMPO5  + " = '"+ NuevaComunidad.getsNOMPRC() + "', "
				+ CAMPO6  + " = '"+ NuevaComunidad.getsNUTPRC() + "', "
				+ CAMPO7  + " = '"+ NuevaComunidad.getsNOMADC() + "', "
				+ CAMPO8  + " = '"+ NuevaComunidad.getsNUTADC() + "', "
				+ CAMPO9  + " = '"+ NuevaComunidad.getsNODCAD() + "', "
				+ CAMPO10 + " = '"+ NuevaComunidad.getsNUCCEN() + "', "
				+ CAMPO11 + " = '"+ NuevaComunidad.getsNUCCOF() + "', "
				+ CAMPO12 + " = '"+ NuevaComunidad.getsNUCCDI() + "', "
				+ CAMPO13 + " = '"+ NuevaComunidad.getsNUCCNT() + "', "
				+ CAMPO14 + " = '"+ NuevaComunidad.getsOBTEXC() +
				"' "+
				" WHERE " +
				"("+ CAMPO1 + " = '"+ sCodCOCLDO +"' AND "+
					CAMPO2 + " = '"+ sCodNUDCOM +"')";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR COCLDO:|"+NuevaComunidad.getsCOCLDO()+"|");
			logger.error("ERROR NUDCOM:|"+NuevaComunidad.getsNUDCOM()+"|");

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

	public static boolean delComunidad(String sCodCOCLDO, String sCodNUDCOM)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "DELETE FROM " 
				+ TABLA + 
				" WHERE " +
				"("+ CAMPO1 + " = '"+ sCodCOCLDO +"' AND "+
				CAMPO2 + " = '"+ sCodNUDCOM +"')";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR COCLDO:|"+sCodCOCLDO+"|");
			logger.error("ERROR NUDCOM:|"+sCodNUDCOM+"|");

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

	public static Comunidad getComunidad(String sCodCOCLDO, String sCodNUDCOM)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();
		
		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sCOCLDO = "";
		String sNUDCOM = "";
		String sNOMCOC = "";
		String sNODCCO = "";
		String sNOMPRC = "";
		String sNUTPRC = "";
		String sNOMADC = "";
		String sNUTADC = "";
		String sNODCAD = "";
		String sNUCCEN = "";
		String sNUCCOF = "";
		String sNUCCDI = "";
		String sNUCCNT = "";
		String sOBTEXC = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
			       + CAMPO1  + ","              
			       + CAMPO2  + ","
			       + CAMPO3  + ","              
			       + CAMPO4  + ","              
			       + CAMPO5  + ","              
			       + CAMPO6  + ","              
			       + CAMPO7  + ","              
			       + CAMPO8  + ","              
			       + CAMPO9  + ","              
			       + CAMPO10 + ","              
			       + CAMPO11 + ","              
			       + CAMPO12 + ","              
			       + CAMPO13 + ","              
			       + CAMPO14 +               
			       " FROM " 
			       + TABLA + 
			       " WHERE " +
			       "("+ CAMPO1 + " = '"+ sCodCOCLDO +"' AND "+
			       CAMPO2 + " = '"+ sCodNUDCOM +"')";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement(sQuery);
			

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			logger.debug(CAMPO1 + ":|"+sCodCOCLDO+"|");
			logger.debug(CAMPO2 + ":|"+sCodNUDCOM+"|");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCOCLDO = rs.getString(CAMPO1);  
					sNUDCOM = rs.getString(CAMPO2);  
					sNOMCOC = rs.getString(CAMPO3);  
					sNODCCO = rs.getString(CAMPO4);  
					sNOMPRC = rs.getString(CAMPO5);  
					sNUTPRC = rs.getString(CAMPO6);  
					sNOMADC = rs.getString(CAMPO7);  
					sNUTADC = rs.getString(CAMPO8);  
					sNODCAD = rs.getString(CAMPO9);  
					sNUCCEN = rs.getString(CAMPO10); 
					sNUCCOF = rs.getString(CAMPO11); 
					sNUCCDI = rs.getString(CAMPO12); 
					sNUCCNT = rs.getString(CAMPO13); 
					sOBTEXC = rs.getString(CAMPO14); 

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

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.CloseDBConnection(conn);
		return new Comunidad(
				sCOCLDO,
				sNUDCOM,
				sNOMCOC,
				sNODCCO,
				sNOMPRC,
				sNUTPRC,
				sNOMADC,
				sNUTADC,
				sNODCAD,
				sNUCCEN,
				sNUCCOF,
				sNUCCDI,
				sNUCCNT,
				sOBTEXC);
	}

	public static boolean existeComunidad(String sCodCOCLDO, String sCodNUDCOM)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();
		
		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
			       	+ CAMPO2  +                
			       	" FROM " 
			       	+ TABLA + 
			       	" WHERE " +
			       	"("+ CAMPO1 + " = '"+ sCodCOCLDO +"' AND "+
			       	CAMPO2 + " = '"+ sCodNUDCOM +"')";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement(sQuery);
			

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			logger.debug(CAMPO1 + ":|"+sCodCOCLDO+"|");
			logger.debug(CAMPO2 + ":|"+sCodNUDCOM+"|");

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
			logger.error("ERROR COCLDO:|"+sCodCOCLDO+"|");
			logger.error("ERROR NUDCOM:|"+sCodNUDCOM+"|");

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
	
	public static boolean setEstado(String sCodCOCLDO, String sCodNUDCOM, String sEstado)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO15 + " = '"+ sEstado + 
				"' "+
				" WHERE "+
				"("+ CAMPO1 + " = '"+ sCodCOCLDO +"' AND "+
				CAMPO2 + " = '"+ sCodNUDCOM +"')";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR COCLDO:|"+sCodCOCLDO+"|");
			logger.error("ERROR NUDCOM:|"+sCodNUDCOM+"|");

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
	
	public static String getEstado(String sCodCOCLDO, String sCodNUDCOM)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		String sEstado = "";
		
		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
					+ CAMPO15 + 
					" FROM " 
					+ TABLA + 
					" WHERE " +
					"("+ CAMPO1 + " = '"+ sCodCOCLDO +"' AND "+
					CAMPO2 + " = '"+ sCodNUDCOM +"')";
		
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

					sEstado = rs.getString(CAMPO15);
					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO15+":|"+sEstado+"|");
					logger.debug(CAMPO1+":|"+sCodCOCLDO+"|");
					logger.debug(CAMPO2+":|"+sCodNUDCOM+"|");
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

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		ConnectionManager.CloseDBConnection(conn);
		return sEstado;
	}
}