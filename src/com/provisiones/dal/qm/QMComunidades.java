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

	//Primary Key
	public static final String CAMPO1  = "e1_comunidad_id";
	
	//Unique Key - comunidad
	public static final String CAMPO2  = "cod_cocldo";
	public static final String CAMPO3  = "nudcom_id"; 
	
	//Campos secundarios
	public static final String CAMPO4  = "nomcoc";    
	public static final String CAMPO5  = "nodcco";    
	public static final String CAMPO6  = "nomprc";    
	public static final String CAMPO7  = "nutprc";    
	public static final String CAMPO8  = "nomadc";    
	public static final String CAMPO9  = "nutadc";    
	public static final String CAMPO10 = "nodcad";    
	public static final String CAMPO11 = "nuccen";    
	public static final String CAMPO12 = "nuccof";    
	public static final String CAMPO13 = "nuccdi";    
	public static final String CAMPO14 = "nuccnt";    
	public static final String CAMPO15 = "obtexc";
	
	//Campos de control
	public static final String CAMPO16 = "cod_estado";
	

	public static long addComunidad(Comunidad NuevaComunidad)

	{
		Connection conn = null;

		Statement stmt = null;
		ResultSet resulset = null;
		
		conn = ConnectionManager.getDBConnection();

		long iCodigo = 0;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "INSERT INTO " 
				   + TABLA + 
				   " ("
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
				   + CAMPO15 + "," 
			       + CAMPO16 +               
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
			stmt.executeUpdate(sQuery, Statement.RETURN_GENERATED_KEYS);
			
			resulset = stmt.getGeneratedKeys();
			
			if (resulset.next()) 
			{
				iCodigo= resulset.getLong(1);
			} 

			logger.debug("Ejecutada con exito!");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			iCodigo = 0;
			
			logger.error("ERROR COCLDO:|"+NuevaComunidad.getsCOCLDO()+"|");
			logger.error("ERROR NUDCOM:|"+NuevaComunidad.getsNUDCOM()+"|");
			
			logger.error("ERROR SQLException:",ex.getMessage());
			logger.error("ERROR SQLState:",ex.getSQLState());
			logger.error("ERROR VendorError:",ex.getErrorCode());
		} 
		finally
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return iCodigo;
	}
	public static boolean modComunidad(Comunidad NuevaComunidad, String sComunidadID)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				//+ CAMPO2    + " = '"+ NuevaComunidad.getsCOCLDO() + "', "
				//+ CAMPO3    + " = '"+ NuevaComunidad.getsNUDCOM() + "', "
				+ CAMPO4    + " = '"+ NuevaComunidad.getsNOMCOC() + "', "
				+ CAMPO5    + " = '"+ NuevaComunidad.getsNODCCO() + "', "
				+ CAMPO6    + " = '"+ NuevaComunidad.getsNOMPRC() + "', "
				+ CAMPO7    + " = '"+ NuevaComunidad.getsNUTPRC() + "', "
				+ CAMPO8    + " = '"+ NuevaComunidad.getsNOMADC() + "', "
				+ CAMPO9    + " = '"+ NuevaComunidad.getsNUTADC() + "', "
				+ CAMPO10   + " = '"+ NuevaComunidad.getsNODCAD() + "', "
				+ CAMPO11   + " = '"+ NuevaComunidad.getsNUCCEN() + "', "
				+ CAMPO12   + " = '"+ NuevaComunidad.getsNUCCOF() + "', "
				+ CAMPO13   + " = '"+ NuevaComunidad.getsNUCCDI() + "', "
				+ CAMPO14   + " = '"+ NuevaComunidad.getsNUCCNT() + "', "
				+ CAMPO15 + " = '"+ NuevaComunidad.getsOBTEXC() +
				"' "+
				" WHERE "
				+ CAMPO1 + " = '"+ sComunidadID +"'";
		
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

			logger.error("ERROR COMUNIDAD:|"+sComunidadID+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static boolean delComunidad(String sComunidadID)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "DELETE FROM " 
				+ TABLA + 
				" WHERE "
				+ CAMPO1 + " = '"+ sComunidadID+"'";
		
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

			logger.error("ERROR COMUNIDAD:|"+sComunidadID+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static Comunidad getComunidad(String sComunidadID)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();
		
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

		boolean bEncontrado = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
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
			       + CAMPO14  + ","  
			       + CAMPO15 +               
			       " FROM " 
			       + TABLA + 
			       " WHERE "
			       + CAMPO1 + " = '"+ sComunidadID +"'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement(sQuery);
			

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			logger.debug(CAMPO1 + ":|"+sComunidadID+"|");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					bEncontrado = true;

					sCOCLDO = rs.getString(CAMPO2);  
					sNUDCOM = rs.getString(CAMPO3);  
					sNOMCOC = rs.getString(CAMPO4);  
					sNODCCO = rs.getString(CAMPO5);  
					sNOMPRC = rs.getString(CAMPO6);  
					sNUTPRC = rs.getString(CAMPO7);  
					sNOMADC = rs.getString(CAMPO8);  
					sNUTADC = rs.getString(CAMPO9);  
					sNODCAD = rs.getString(CAMPO10); 
					sNUCCEN = rs.getString(CAMPO11); 
					sNUCCOF = rs.getString(CAMPO12); 
					sNUCCDI = rs.getString(CAMPO13); 
					sNUCCNT = rs.getString(CAMPO14); 
					sOBTEXC = rs.getString(CAMPO15); 

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
			logger.error("ERROR COMUNIDAD:|"+sComunidadID+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
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

	public static String getComunidadID(String sCodCOCLDO, String sCodNUDCOM)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();
		
		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		String sComunidadID = "";

		boolean bEncontrado = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
			       	+ CAMPO1  +                
			       	" FROM " 
			       	+ TABLA + 
			       	" WHERE ("
			       	+ CAMPO2 + " = '"+ sCodCOCLDO +"' AND "
			       	+ CAMPO3 + " = '"+ sCodNUDCOM +"')";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement(sQuery);
			

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			logger.debug(CAMPO2 + ":|"+sCodCOCLDO+"|");
			logger.debug(CAMPO3 + ":|"+sCodNUDCOM+"|");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					bEncontrado = true;

					sComunidadID = rs.getString(CAMPO1);
					
					logger.debug(CAMPO1+":|"+sComunidadID+"|");
					
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
			sComunidadID = "";
			
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
		return sComunidadID;
	}	
	
	public static boolean existeComunidad(String sCodCOCLDO, String sCodNUDCOM)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();
		
		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		boolean bEncontrado = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
			       	+ CAMPO2  +                
			       	" FROM " 
			       	+ TABLA + 
			       	" WHERE ("
			       	+ CAMPO2 + " = '"+ sCodCOCLDO +"' AND "
			       	+ CAMPO3 + " = '"+ sCodNUDCOM +"')";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement(sQuery);
			

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			logger.debug(CAMPO2 + ":|"+sCodCOCLDO+"|");
			logger.debug(CAMPO3 + ":|"+sCodNUDCOM+"|");

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
			bEncontrado = false;
			
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
		return bEncontrado;
	}
	
	public static boolean setEstado(String sComunidadID, String sEstado)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO16 + " = '"+ sEstado +"' "+
				" WHERE "
				+ CAMPO1 + " = '"+ sComunidadID +"'";
		
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

			logger.error("ERROR COMUNIDAD:|"+sComunidadID+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	
	public static String getEstado(String sComunidadID)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		String sEstado = "";
		
		boolean bEncontrado = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
					+ CAMPO16 + 
					" FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO1 + " = '"+ sComunidadID +"'";
		
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
					bEncontrado = true;

					sEstado = rs.getString(CAMPO16);
					
					logger.debug(CAMPO1+":|"+sComunidadID+"|");
					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO16+":|"+sEstado+"|");
				}
			}
			if (bEncontrado == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			sEstado = "";
			
			logger.error("ERROR COMUNIDAD:|"+sComunidadID+"|");

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