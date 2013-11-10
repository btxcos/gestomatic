package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ImpuestoRecurso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QMImpuestos 
{
	private static Logger logger = LoggerFactory.getLogger(QMImpuestos.class.getName());
	
	public static final String TABLA = "pp001_e4_impuestos_tbl";

	//Primary Key
	public static final String CAMPO1  = "e4_impuesto_id";
	
	//Unique Key - impuesto
	public static final String CAMPO2  = "cod_nurcat";
	public static final String CAMPO3  = "cod_cosbac";
	
	//Campos secundarios
	public static final String CAMPO4  = "feprre";    
	public static final String CAMPO5  = "ferere";    
	public static final String CAMPO6  = "fedein";    
	public static final String CAMPO7  = "cod_bisode";
	public static final String CAMPO8  = "cod_bireso";
	public static final String CAMPO9  = "cotexa";    
	public static final String CAMPO10 = "obtexc";
	
	//Campos de control
	public static final String CAMPO11 = "cod_estado";

	public static long addImpuesto(ImpuestoRecurso NuevoImpuestoRecurso)

	{
		Connection conn = null;

		Statement stmt = null;
		ResultSet resulset = null;
		
		conn = ConnectionManager.getDBConnection();

		long liCodigo = 0;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "INSERT INTO " + TABLA + " ("
			       + CAMPO2  + "," 
			       + CAMPO3  + ","              
			       + CAMPO4  + ","              
			       + CAMPO5  + ","              
			       + CAMPO6  + ","              
			       + CAMPO7  + ","              
			       + CAMPO8  + ","
			       + CAMPO9  + ","
			       + CAMPO10 + ","
			       + CAMPO11 +  
			       ") VALUES ('" 
			       + NuevoImpuestoRecurso.getNURCAT() + "','"
			       + NuevoImpuestoRecurso.getCOSBAC() + "','"
			       + NuevoImpuestoRecurso.getFEPRRE() + "','"
			       + NuevoImpuestoRecurso.getFERERE() + "','"
			       + NuevoImpuestoRecurso.getFEDEIN() + "','"
			       + NuevoImpuestoRecurso.getBISODE() + "','"
			       + NuevoImpuestoRecurso.getBIRESO() + "','"
			       + NuevoImpuestoRecurso.getCOTEXA() + "','"
			       + NuevoImpuestoRecurso.getOBTEXC() + "','" 
			       + ValoresDefecto.DEF_ALTA + "' )";

		try 
		{

			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery, Statement.RETURN_GENERATED_KEYS);
			
			resulset = stmt.getGeneratedKeys();
			
			if (resulset.next()) 
			{
				liCodigo= resulset.getLong(1);
			} 

			logger.debug("Ejecutada con exito!");
			
		}
		catch (SQLException ex) 
		{
			liCodigo = 0;		

			logger.error("ERROR NURCAT:|"+NuevoImpuestoRecurso.getNURCAT()+"|");
			logger.error("ERROR COSBAC:|"+NuevoImpuestoRecurso.getCOSBAC()+"|");
			
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return liCodigo;
	}

	public static boolean modImpuestoRecurso(ImpuestoRecurso NuevoImpuestoRecurso, String sCodImpuesto)
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
					+ CAMPO2 + " = '"+ NuevoImpuestoRecurso.getNURCAT() + "', "
					+ CAMPO3 + " = '"+ NuevoImpuestoRecurso.getCOSBAC() + "', "
					+ CAMPO4 + " = '"+ NuevoImpuestoRecurso.getFEPRRE() + "', "
					+ CAMPO5 + " = '"+ NuevoImpuestoRecurso.getFERERE() + "', "
					+ CAMPO6 + " = '"+ NuevoImpuestoRecurso.getFEDEIN() + "', "
					+ CAMPO7 + " = '"+ NuevoImpuestoRecurso.getBISODE() + "', "
					+ CAMPO8 + " = '"+ NuevoImpuestoRecurso.getBIRESO() + "', "
					+ CAMPO9 + " = '"+ NuevoImpuestoRecurso.getCOTEXA() + "', "
					+ CAMPO10 + " = '"+ NuevoImpuestoRecurso.getOBTEXC() +
					"' "+
					" WHERE "
					+ CAMPO1 + " = '" + sCodImpuesto + "'");
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			bSalida = false;

			logger.error("ERROR Impuesto:|"+sCodImpuesto+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static boolean delImpuestoRecurso(String sCodImpuesto)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '" + sCodImpuesto + "'");
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			bSalida = false;

			logger.error("ERROR Impuesto:|"+sCodImpuesto+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static ImpuestoRecurso getImpuestoRecurso(String sCodImpuesto)
	{	
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sNURCAT = "";
		String sCOSBAC = "";
		String sFEPRRE = "";
		String sFERERE = "";
		String sFEDEIN = "";
		String sBISODE = "";
		String sBIRESO = "";
		String sCOTEXA = "";
		String sOBTEXC = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
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
					" WHERE "
					+ CAMPO1 + " = '" + sCodImpuesto + "'");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sNURCAT = rs.getString(CAMPO2); 
					sCOSBAC = rs.getString(CAMPO3);
					sFEPRRE = rs.getString(CAMPO4);
					sFERERE = rs.getString(CAMPO5);
					sFEDEIN = rs.getString(CAMPO6);
					sBISODE = rs.getString(CAMPO7);
					sBIRESO = rs.getString(CAMPO8);
					sCOTEXA = rs.getString(CAMPO9);
					sOBTEXC = rs.getString(CAMPO10);

					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO2+":|"+sNURCAT+"|");
					logger.debug(CAMPO3+":|"+sCOSBAC+"|");

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Impuesto:|"+sCodImpuesto+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return new ImpuestoRecurso(sNURCAT, sCOSBAC, sFEPRRE, sFERERE, sFEDEIN, sBISODE, sBIRESO, sCOTEXA, sOBTEXC);
	}

	public static String getImpuestoRecursoID(String sCodNURCAT,String sCodCOSBAC)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sImpuestoRecursoID = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " 
					+ CAMPO1 + 
					" FROM " 
					+ TABLA + 
					" WHERE ("
					+ CAMPO2 + " = '" + sCodNURCAT + "' AND "
					+ CAMPO3 + " = '" + sCodCOSBAC + "')");
			
			logger.debug("Ejecutada con exito!");

			rs = pstmt.executeQuery();
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sImpuestoRecursoID = rs.getString(CAMPO1);
					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO11+":|"+sImpuestoRecursoID+"|");


				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			sImpuestoRecursoID = "";
			
			logger.error("ERROR NURCAT:|"+sCodNURCAT+"|");
			logger.error("ERROR COSBAC:|"+sCodCOSBAC+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		//ConnectionManager.CloseDBConnection(conn);
		return sImpuestoRecursoID;
	}
	
	public static boolean existeImpuestoRecurso(String sCodImpuesto)
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

			pstmt = conn.prepareStatement("SELECT "
				       + CAMPO11  +       
				       " FROM "
				       + TABLA + 
				       " WHERE " 
				       + CAMPO1 + " = '" + sCodImpuesto + "'");

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
			found = false;

			logger.error("ERROR Impuesto:|"+sCodImpuesto+"|");

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
	
	
	public static boolean tieneImpuestoRecurso(String sCodNURCAT)
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

			pstmt = conn.prepareStatement("SELECT "
				       + CAMPO2  + 
			"  FROM " + TABLA + 
					" WHERE " +
					"(" + CAMPO1 + " = '" + sCodNURCAT + "' AND " +
					CAMPO10  + " <> 'B' )");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;


					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO1+":|"+sCodNURCAT+"|");

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

/*	public static String getImpuestoRecursoID(ImpuestoRecurso impuesto)
	{
		
		String sMethod = "getImpuestoRecursoID";

		Statement stmt = null;
		ResultSet rs = null;

		String sImpuestoID = "";

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					+ CAMPO1 + 
					"  FROM " + TABLA + 
						" WHERE ("
					       + CAMPO2  +" = '" + impuesto.getCODTRN() + "' AND "
					       + CAMPO3  +" = '" + impuesto.getCOTDOR() + "' AND "
					       + CAMPO4  +" = '" + impuesto.getIDPROV() + "' AND "
					       + CAMPO5  +" = '" + impuesto.getCOACCI() + "' AND "
					       + CAMPO6  +" = '" + impuesto.getCOENGP() + "' AND "
					       + CAMPO7  +" = '" + impuesto.getCOACES() + "' AND "
					       + CAMPO8  +" = '" + impuesto.getNURCAT() + "' AND "
					       + CAMPO9  +" = '" + impuesto.getCOGRUC() + "' AND "
					       + CAMPO10 +" = '" + impuesto.getCOTACA() + "' AND "
					       + CAMPO11 +" = '" + impuesto.getCOSBAC() + "' AND "
					       + CAMPO12 +" = '" + impuesto.getBITC18() + "' AND "
					       + CAMPO13 +" = '" + impuesto.getFEPRRE() + "' AND "
					       + CAMPO14 +" = '" + impuesto.getBITC19() + "' AND "
					       + CAMPO15 +" = '" + impuesto.getFERERE() + "' AND "
					       + CAMPO16 +" = '" + impuesto.getBITC20() + "' AND "
					       + CAMPO17 +" = '" + impuesto.getFEDEIN() + "' AND "
					       + CAMPO18 +" = '" + impuesto.getBITC21() + "' AND "
					       + CAMPO19 +" = '" + impuesto.getBISODE() + "' AND "
					       + CAMPO20 +" = '" + impuesto.getBITC22() + "' AND "
					       + CAMPO21 +" = '" + impuesto.getBIRESO() + "' AND "
					       + CAMPO22 +" = '" + impuesto.getCOTEXA() + "' AND "
					       + CAMPO23 +" = '" + impuesto.getBITC09() + "' AND "
					       + CAMPO24 +" = '" + impuesto.getOBTEXC() + "' AND "
					       + CAMPO25 +" = '" + impuesto.getOBDEER() + "' )"); 

			rs = pstmt.executeQuery();

			//System.out.println("===================================================");
			//System.out.println(CAMPO1 + ":|"+sCuotaID+"|");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sImpuestoID = rs.getString(CAMPO1);
					System.out.println(CAMPO1 + ":|"+sImpuestoID+"|");
				}
			}
			if (found == false) 
			{
				System.out.println("No Information Found");
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
		return sImpuestoID;
	}*/

	public static boolean setEstado(String sCodImpuesto, String sEstado)
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
					+ CAMPO11 + " = '"+ sEstado + "' "+
					" WHERE "
					+ CAMPO1 + " = '" + sCodImpuesto + "'");
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Impuesto:|"+sCodImpuesto+"|");

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
	
	public static String getEstado(String sCodImpuesto)
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


			pstmt = conn.prepareStatement("SELECT " 
					+ CAMPO11 + 
					" FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO1 + " = '" + sCodImpuesto + "'");
			
			logger.debug("Ejecutada con exito!");

			rs = pstmt.executeQuery();
			
			
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
			logger.error("ERROR Impuesto:|"+sCodImpuesto+"|");

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
