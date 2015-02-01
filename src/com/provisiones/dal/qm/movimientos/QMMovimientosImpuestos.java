package com.provisiones.dal.qm.movimientos;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.types.movimientos.MovimientoImpuestoRecurso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class QMMovimientosImpuestos 
{
	private static Logger logger = LoggerFactory.getLogger(QMMovimientosImpuestos.class.getName());

	public static final String TABLA = "pp002_e4_movimientos_tbl";

	public static final String CAMPO1 = "e4_movimiento_id";

	public static final String CAMPO2  = "cod_codtrn";
	public static final String CAMPO3  = "cod_cotdor";
	public static final String CAMPO4  = "idprov";    
	public static final String CAMPO5  = "cod_coacci";
	public static final String CAMPO6  = "coengp";    
	public static final String CAMPO7  = "cod_coaces";
	public static final String CAMPO8  = "cod_nurcat";    
	public static final String CAMPO9  = "cogruc";
	public static final String CAMPO10 = "cotaca";
	public static final String CAMPO11 = "cod_cosbac";
	public static final String CAMPO12 = "cod_bitc18";
	public static final String CAMPO13 = "feprre";    
	public static final String CAMPO14 = "cod_bitc19";
	public static final String CAMPO15 = "ferere";    
	public static final String CAMPO16 = "cod_bitc20";
	public static final String CAMPO17 = "fedein";    
	public static final String CAMPO18 = "cod_bitc21";
	public static final String CAMPO19 = "cod_bisode";
	public static final String CAMPO20 = "cod_bitc22";
	public static final String CAMPO21 = "cod_bireso";
	public static final String CAMPO22 = "cotexa";    
	public static final String CAMPO23 = "cod_bitc09";
	public static final String CAMPO24 = "obtexc";    
	public static final String CAMPO25 = "obdeer";
	
	//Campos de control
	public static final String CAMPO26  = "usuario_movimiento";
	public static final String CAMPO27  = "fecha_movimiento";

	private QMMovimientosImpuestos(){}
	
	public static long addMovimientoImpuestoRecurso(Connection conexion, MovimientoImpuestoRecurso NuevoMovimientoImpuestoRecurso)
	{
		long liCodigo = 0;

		if (conexion != null)
		{
			Statement stmt = null;
			ResultSet resulset = null;
			
			String sUsuario = ConnectionManager.getUser();
			
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
				       + CAMPO16 + ","              
				       + CAMPO17 + ","              
				       + CAMPO18 + ","              
				       + CAMPO19 + ","              
				       + CAMPO20 + ","              
				       + CAMPO21 + ","              
				       + CAMPO22 + ","              
				       + CAMPO23 + ","
				       + CAMPO24 + ","
				       + CAMPO25 + "," 
				       + CAMPO26 + "," 
				       + CAMPO27 +  
				       ") VALUES ('" 
				       + NuevoMovimientoImpuestoRecurso.getCODTRN() + "','" 
				       + NuevoMovimientoImpuestoRecurso.getCOTDOR() + "','"
				       + NuevoMovimientoImpuestoRecurso.getIDPROV() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOACCI() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOENGP() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOACES() + "','"
				       + NuevoMovimientoImpuestoRecurso.getNURCAT() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOGRUC() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOTACA() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOSBAC() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBITC18() + "','"
				       + NuevoMovimientoImpuestoRecurso.getFEPRRE() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBITC19() + "','"
				       + NuevoMovimientoImpuestoRecurso.getFERERE() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBITC20() + "','"
				       + NuevoMovimientoImpuestoRecurso.getFEDEIN() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBITC21() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBISODE() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBITC22() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBIRESO() + "','"
				       + NuevoMovimientoImpuestoRecurso.getCOTEXA() + "','"
				       + NuevoMovimientoImpuestoRecurso.getBITC09() + "','"
				       + NuevoMovimientoImpuestoRecurso.getOBTEXC() + "','"
				       + NuevoMovimientoImpuestoRecurso.getOBDEER() + "','"
				       + sUsuario + "','"
				       + Utils.timeStamp() +
				       "' )";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery, Statement.RETURN_GENERATED_KEYS);
				
				resulset = stmt.getGeneratedKeys();
				
				logger.debug("Ejecutada con exito!");
				
				if (resulset.next()) 
				{
					liCodigo= resulset.getLong(1);
				} 
			} 
			catch (SQLException ex) 
			{
				liCodigo = 0;
				
				logger.error("ERROR COACES:|"+NuevoMovimientoImpuestoRecurso.getCOACES()+"|");
				logger.error("ERROR NURCAT:|"+NuevoMovimientoImpuestoRecurso.getNURCAT()+"|");
				logger.error("ERROR COSBAC:|"+NuevoMovimientoImpuestoRecurso.getCOSBAC()+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			}
			finally
			{
				Utils.closeStatement(stmt);
				Utils.closeResultSet(resulset);
			}
		}

		return liCodigo;
	}
	public static boolean modMovimientoImpuestoRecurso(Connection conexion, MovimientoImpuestoRecurso NuevoMovimientoImpuestoRecurso, long liMovimientoImpuestoRecursoID)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO2  + " = '"+ NuevoMovimientoImpuestoRecurso.getCODTRN() + "', "
					+ CAMPO3  + " = '"+ NuevoMovimientoImpuestoRecurso.getCOTDOR() + "', "
					+ CAMPO4  + " = '"+ NuevoMovimientoImpuestoRecurso.getIDPROV() + "', "
					+ CAMPO5  + " = '"+ NuevoMovimientoImpuestoRecurso.getCOACCI() + "', "
					+ CAMPO6  + " = '"+ NuevoMovimientoImpuestoRecurso.getCOENGP() + "', "
					+ CAMPO7  + " = '"+ NuevoMovimientoImpuestoRecurso.getCOACES() + "', "
					+ CAMPO8  + " = '"+ NuevoMovimientoImpuestoRecurso.getNURCAT() + "', "
					+ CAMPO9  + " = '"+ NuevoMovimientoImpuestoRecurso.getCOGRUC() + "', "
					+ CAMPO10 + " = '"+ NuevoMovimientoImpuestoRecurso.getCOTACA() + "', "
					+ CAMPO11 + " = '"+ NuevoMovimientoImpuestoRecurso.getCOSBAC() + "', "
					+ CAMPO12 + " = '"+ NuevoMovimientoImpuestoRecurso.getBITC18() + "', "
					+ CAMPO13 + " = '"+ NuevoMovimientoImpuestoRecurso.getFEPRRE() + "', "
					+ CAMPO14 + " = '"+ NuevoMovimientoImpuestoRecurso.getBITC19() + "', "
					+ CAMPO15 + " = '"+ NuevoMovimientoImpuestoRecurso.getFERERE() + "', "
					+ CAMPO16 + " = '"+ NuevoMovimientoImpuestoRecurso.getBITC20() + "', "
					+ CAMPO17 + " = '"+ NuevoMovimientoImpuestoRecurso.getFEDEIN() + "', "
					+ CAMPO18 + " = '"+ NuevoMovimientoImpuestoRecurso.getBITC21() + "', "
					+ CAMPO19 + " = '"+ NuevoMovimientoImpuestoRecurso.getBISODE() + "', "
					+ CAMPO20 + " = '"+ NuevoMovimientoImpuestoRecurso.getBITC22() + "', "
					+ CAMPO21 + " = '"+ NuevoMovimientoImpuestoRecurso.getBIRESO() + "', "
					+ CAMPO22 + " = '"+ NuevoMovimientoImpuestoRecurso.getCOTEXA() + "', "
					+ CAMPO23 + " = '"+ NuevoMovimientoImpuestoRecurso.getBITC09() + "', "
					+ CAMPO24 + " = '"+ NuevoMovimientoImpuestoRecurso.getOBTEXC() + "', "
					+ CAMPO25 + " = '"+ NuevoMovimientoImpuestoRecurso.getOBDEER() + "' "+
					" WHERE "
					+ CAMPO1 + " = '"+ liMovimientoImpuestoRecursoID +"'";
			
			logger.debug(sQuery);
			
			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con exito!");

				bSalida = true;
			} 
			catch (SQLException ex) 
			{
				bSalida = false;
				
				logger.error("ERROR MovimientoImpuestoRecursoID:|"+liMovimientoImpuestoRecursoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean delMovimientoImpuestoRecurso(Connection conexion, long liMovimientoImpuestoRecursoID)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '" + liMovimientoImpuestoRecursoID + "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con exito!");
				
				bSalida = true;
			} 
			catch (SQLException ex) 
			{
				bSalida = false;
				
				logger.error("ERROR MovimientoImpuestoRecursoID:|"+liMovimientoImpuestoRecursoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static MovimientoImpuestoRecurso getMovimientoImpuestoRecurso(Connection conexion, long liMovimientoImpuestoRecursoID)
	{
		String sCODTRN = "";
		String sCOTDOR = "";
		String sIDPROV = "";
		String sCOACCI = "";
		String sCOENGP = "";
		String sCOACES = "";
		String sNURCAT = "";
		String sCOGRUC = "";
		String sCOTACA = "";
		String sCOSBAC = "";
		String sBITC18 = "";
		String sFEPRRE = "";
		String sBITC19 = "";
		String sFERERE = "";
		String sBITC20 = "";
		String sFEDEIN = "";
		String sBITC21 = "";
		String sBISODE = "";
		String sBITC22 = "";
		String sBIRESO = "";
		String sCOTEXA = "";
		String sBITC09 = "";
		String sOBTEXC = "";
		String sOBDEER = "";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

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
				       + CAMPO14 + ","              
				       + CAMPO15 + ","              
				       + CAMPO16 + ","              
				       + CAMPO17 + ","              
				       + CAMPO18 + ","              
				       + CAMPO19 + ","              
				       + CAMPO20 + ","              
				       + CAMPO21 + ","              
				       + CAMPO22 + ","              
				       + CAMPO23 + ","
				       + CAMPO24 + "," 
				       + CAMPO25 + 
				       " FROM " 
				       + TABLA + 
				       " WHERE "
				       + CAMPO1 + " = '" + liMovimientoImpuestoRecursoID	+ "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						sCODTRN = rs.getString(CAMPO2); 
						sCOTDOR = rs.getString(CAMPO3); 
						sIDPROV = rs.getString(CAMPO4); 
						sCOACCI = rs.getString(CAMPO5); 
						sCOENGP = rs.getString(CAMPO6); 
						sCOACES = rs.getString(CAMPO7); 
						sNURCAT = rs.getString(CAMPO8); 
						sCOGRUC = rs.getString(CAMPO9); 
						sCOTACA = rs.getString(CAMPO10);
						sCOSBAC = rs.getString(CAMPO11);
						sBITC18 = rs.getString(CAMPO12);
						sFEPRRE = rs.getString(CAMPO13);
						sBITC19 = rs.getString(CAMPO14);
						sFERERE = rs.getString(CAMPO15);
						sBITC20 = rs.getString(CAMPO16);
						sFEDEIN = rs.getString(CAMPO17);
						sBITC21 = rs.getString(CAMPO18);
						sBISODE = rs.getString(CAMPO19);
						sBITC22 = rs.getString(CAMPO20);
						sBIRESO = rs.getString(CAMPO21);
						sCOTEXA = rs.getString(CAMPO22);
						sBITC09 = rs.getString(CAMPO23);
						sOBTEXC = rs.getString(CAMPO24);
						sOBDEER = rs.getString(CAMPO25);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+liMovimientoImpuestoRecursoID+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				sCODTRN = "";
				sCOTDOR = "";
				sIDPROV = "";
				sCOACCI = "";
				sCOENGP = "";
				sCOACES = "";
				sNURCAT = "";
				sCOGRUC = "";
				sCOTACA = "";
				sCOSBAC = "";
				sBITC18 = "";
				sFEPRRE = "";
				sBITC19 = "";
				sFERERE = "";
				sBITC20 = "";
				sFEDEIN = "";
				sBITC21 = "";
				sBISODE = "";
				sBITC22 = "";
				sBIRESO = "";
				sCOTEXA = "";
				sBITC09 = "";
				sOBTEXC = "";
				sOBDEER = "";
				
				logger.error("ERROR MovimientoImpuestoRecursoID:|"+liMovimientoImpuestoRecursoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new MovimientoImpuestoRecurso(sCODTRN, sCOTDOR, sIDPROV, sCOACCI, sCOENGP,
				sCOACES, sNURCAT, sCOGRUC, sCOTACA, sCOSBAC, sBITC18, sFEPRRE,
				sBITC19, sFERERE, sBITC20, sFEDEIN, sBITC21, sBISODE, sBITC22,
				sBIRESO, sCOTEXA, sBITC09, sOBTEXC, sOBDEER);
	}

	public static long getMovimientoImpuestoRecursoID(Connection conexion, MovimientoImpuestoRecurso impuesto)
	{
		long liMovimientoImpuestoRecursoID = 0;
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
						   + CAMPO1 + 
						   " FROM " 
						   + TABLA + 
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
					       + CAMPO25 +" = '" + impuesto.getOBDEER() + 
					       "')";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery); 
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						liMovimientoImpuestoRecursoID = rs.getLong(CAMPO1);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+liMovimientoImpuestoRecursoID+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				liMovimientoImpuestoRecursoID = 0;
				
				logger.error("ERROR COACES:|"+impuesto.getCOACES()+"|");
				logger.error("ERROR NURCAT:|"+impuesto.getNURCAT()+"|");
				logger.error("ERROR COSBAC:|"+impuesto.getCOSBAC()+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return liMovimientoImpuestoRecursoID;
	}
	
	public static boolean existeMovimientoImpuestoRecurso(Connection conexion, long liMovimientoImpuestoRecursoID)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO1 + 
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '" + liMovimientoImpuestoRecursoID + "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");
				
				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+rs.getString(CAMPO1)+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la información.");
				}
			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR sMovimientoImpuestoID:|"+liMovimientoImpuestoRecursoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bEncontrado;
	}
}

//Autor: Francisco Valverde Manjón