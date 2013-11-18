package com.provisiones.dal.qm;

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

	public static long addImpuesto(Connection conexion, ImpuestoRecurso NuevoImpuestoRecurso)
	{
		long liCodigo = 0;

		if (conexion != null)
		{
			Statement stmt = null;
			ResultSet resulset = null;

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

				stmt = conexion.createStatement();
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
		}

		return liCodigo;
	}

	public static boolean modImpuestoRecurso(Connection conexion, ImpuestoRecurso NuevoImpuestoRecurso, String sCodImpuesto)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " + TABLA + 
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
					+ CAMPO1 + " = '" + sCodImpuesto + "'";
			
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

				logger.error("ERROR Impuesto:|"+sCodImpuesto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean delImpuestoRecurso(Connection conexion, String sCodImpuesto)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");

			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '" + sCodImpuesto + "'";

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

				logger.error("ERROR Impuesto:|"+sCodImpuesto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static ImpuestoRecurso getImpuestoRecurso(Connection conexion, String sCodImpuesto)
	{	
		String sNURCAT = "";
		String sCOSBAC = "";
		String sFEPRRE = "";
		String sFERERE = "";
		String sFEDEIN = "";
		String sBISODE = "";
		String sBIRESO = "";
		String sCOTEXA = "";
		String sOBTEXC = "";

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
				       + CAMPO10  +              
				       " FROM " + TABLA + 
				       " WHERE "
				       + CAMPO1 + " = '" + sCodImpuesto + "'";
			
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
				if (bEncontrado == false) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				sNURCAT = "";
				sCOSBAC = "";
				sFEPRRE = "";
				sFERERE = "";
				sFEDEIN = "";
				sBISODE = "";
				sBIRESO = "";
				sCOTEXA = "";
				sOBTEXC = "";

				logger.error("ERROR Impuesto:|"+sCodImpuesto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new ImpuestoRecurso(sNURCAT, sCOSBAC, sFEPRRE, sFERERE, sFEDEIN, sBISODE, sBIRESO, sCOTEXA, sOBTEXC);
	}

	public static String getImpuestoRecursoID(Connection conexion, String sCodNURCAT,String sCodCOSBAC)
	{
		String sImpuestoRecursoID = "";

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
					+ CAMPO2 + " = '" + sCodNURCAT + "' AND "
					+ CAMPO3 + " = '" + sCodCOSBAC + "')";
			
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

						sImpuestoRecursoID = rs.getString(CAMPO1);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO11+":|"+sImpuestoRecursoID+"|");
					}
				}
				if (bEncontrado == false) 
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
		}

		return sImpuestoRecursoID;
	}
	
	public static boolean existeImpuestoRecurso(Connection conexion, String sCodImpuesto)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
				       + CAMPO11  +       
				       " FROM "
				       + TABLA + 
				       " WHERE " 
				       + CAMPO1 + " = '" + sCodImpuesto + "'";
			
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

				logger.error("ERROR Impuesto:|"+sCodImpuesto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return bEncontrado;
	}
	
	
	public static boolean tieneImpuestoRecurso(Connection conexion, String sCodNURCAT)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
				       + CAMPO2  + 
				       " FROM " + TABLA + 
				       " WHERE (" 
				       + CAMPO1 + " = '" + sCodNURCAT + "' AND "
				       + CAMPO10  + " <> 'B' )";
			
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

						logger.debug(CAMPO1+":|"+sCodNURCAT+"|");
					}
				}
				if (bEncontrado == false) 
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
		}

		return bEncontrado;
	}


	public static boolean setEstado(Connection conexion, String sCodImpuesto, String sEstado)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " + TABLA + 
					" SET " 
					+ CAMPO11 + " = '"+ sEstado + "' "+
					" WHERE "
					+ CAMPO1 + " = '" + sCodImpuesto + "'";
			
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

				logger.error("ERROR Impuesto:|"+sCodImpuesto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}		

		return bSalida;
	}
	
	public static String getEstado(Connection conexion, String sCodImpuesto)
	{
		String sEstado = "";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO11 + 
					" FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO1 + " = '" + sCodImpuesto + "'";
			
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

						sEstado = rs.getString(CAMPO11);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO11+":|"+sEstado+"|");
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

				logger.error("ERROR Impuesto:|"+sCodImpuesto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sEstado;
	}

}
