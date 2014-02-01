package com.provisiones.dal.qm;

import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ReferenciaCatastral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class QMReferencias
{
	private static Logger logger = LoggerFactory.getLogger(QMReferencias.class.getName());
	
	public static final String TABLA = "pp002_e3_referencias_tbl";

	//Primary Key
	public static final String CAMPO1  = "e3_referencia_id";
	
	//Unique Key - referencia
	public static final String CAMPO2  = "nurcat_id";
	
	//Campos secundarios
	public static final String CAMPO3  = "tircat";
	public static final String CAMPO4  = "enemis";    
	public static final String CAMPO5  = "cotexa";    
	public static final String CAMPO6  = "obtexc";    
	public static final String CAMPO7  = "imvsue";    
	public static final String CAMPO8  = "imcata";    
	public static final String CAMPO9  = "fereca";
	
	//Campos de control
	public static final String CAMPO10 = "cod_estado";
	public static final String CAMPO11 = "nota";

	private QMReferencias(){}
	
	public static long addReferenciaCatastral(Connection conexion, ReferenciaCatastral NuevaReferenciaCatastral)
	{
		long liCodigo = 0;

		if (conexion != null)
		{
			Statement stmt = null;
			ResultSet resulset = null;

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
				       + CAMPO10  + ","
				       + CAMPO11 +              
				       ") VALUES ('" 
				       + NuevaReferenciaCatastral.getNURCAT() + "','"
				       + NuevaReferenciaCatastral.getTIRCAT() + "','"
				       + NuevaReferenciaCatastral.getENEMIS() + "','"
				       + NuevaReferenciaCatastral.getCOTEXA() + "','"
				       + NuevaReferenciaCatastral.getOBTEXC() + "','"
				       
				       //Ampliacion de valor catastral
				       + NuevaReferenciaCatastral.getIMVSUE() + "','"
				       + NuevaReferenciaCatastral.getIMCATA() + "','"
				       + NuevaReferenciaCatastral.getFERECA() + "','"

				       + ValoresDefecto.DEF_ALTA + "','"
				       + ValoresDefecto.CAMPO_SIN_INFORMAR + 
				       "')";
			
			logger.debug(sQuery);

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

				logger.error("ERROR NURCAT:|"+NuevaReferenciaCatastral.getNURCAT()+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
			}
		}

		return liCodigo;
	}
	public static boolean modReferenciaCatastral(Connection conexion, ReferenciaCatastral NuevaReferenciaCatastral, long liReferenciaID)
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					//+ CAMPO2  + " = '"+ NuevaReferenciaCatastral.getNURCAT() + "', "
					+ CAMPO3  + " = '"+ NuevaReferenciaCatastral.getTIRCAT() + "', "
					+ CAMPO4  + " = '"+ NuevaReferenciaCatastral.getENEMIS() + "', "
					+ CAMPO5  + " = '"+ NuevaReferenciaCatastral.getCOTEXA() + "', "
					+ CAMPO6  + " = '"+ NuevaReferenciaCatastral.getOBTEXC() + "', "
					
					//Ampliacion de valor catastral
					+ CAMPO7  + " = '"+ NuevaReferenciaCatastral.getIMVSUE() + "', "
					+ CAMPO8  + " = '"+ NuevaReferenciaCatastral.getIMCATA() + "', "
					+ CAMPO9  + " = '"+ NuevaReferenciaCatastral.getFERECA() +
					"' "+
					" WHERE "
					+ CAMPO1 + " = '"+ liReferenciaID +"'";
			
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
				
				logger.error("ERROR Referencia:|"+liReferenciaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{

				Utils.closeStatement(stmt);
			}
		}		

		return bSalida;
	}

	public static boolean delReferenciaCatastral(Connection conexion, long liReferenciaID)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '" + liReferenciaID + "'";
			
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

				logger.error("ERROR Referencia:|"+liReferenciaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}		

		return bSalida;
	}

	public static ReferenciaCatastral getReferenciaCatastral(Connection conexion, long liReferenciaID)
	{
		String sNURCAT = "";
		String sTIRCAT = "";
		String sENEMIS = "";
		String sCOTEXA = "";
		String sOBTEXC = "";
		
		//Ampliacion de valor catastral
		String sIMVSUE = "";
		String sIMCATA = "";
		String sFERECA = "";
		
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
				       + CAMPO6  +  

				       //Ampliacion de valor catastral
				       ","              
				       + CAMPO7  + ","              
				       + CAMPO8  + ","              
				       + CAMPO9  +

				       " FROM " 
				       + TABLA + 
				       " WHERE " + CAMPO1 + " = '" + liReferenciaID	+ "'";
			
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
	  					sTIRCAT = rs.getString(CAMPO3); 
	  					sENEMIS = rs.getString(CAMPO4);
	  					sCOTEXA = rs.getString(CAMPO5);
	  					sOBTEXC = rs.getString(CAMPO6);

	  					//Ampliacion de valor catastral
	  					sIMVSUE = rs.getString(CAMPO7);
	  					sIMCATA = rs.getString(CAMPO8);
	  					sFERECA = rs.getString(CAMPO9);
	  					
	  					logger.debug("Encontrado el registro!");

	  					logger.debug(CAMPO2+":|"+sNURCAT+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sNURCAT = "";
				sTIRCAT = "";
				sENEMIS = "";
				sCOTEXA = "";
				sOBTEXC = "";
				
				//Ampliacion de valor catastral
				sIMVSUE = "";
				sIMCATA = "";
				sFERECA = "";
				
				logger.error("ERROR Referencia:|"+liReferenciaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new ReferenciaCatastral(sNURCAT, sTIRCAT, sENEMIS, sCOTEXA, sOBTEXC, sIMVSUE, sIMCATA, sFERECA);
	}
	
	public static long getReferenciaCatastralID(Connection conexion, String sCodNURCAT)
	{
		long liReferenciaID = 0;
		
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
					" WHERE " 
					+ CAMPO2 + " = '" + sCodNURCAT + "'";
			
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

						liReferenciaID = rs.getLong(CAMPO1);

						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+liReferenciaID+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				liReferenciaID = 0;

				logger.error("ERROR NURCAT:|"+sCodNURCAT+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return liReferenciaID;
	}
	
	public static boolean existeReferenciaCatastral(Connection conexion, long liReferenciaID)
	{
		boolean bEncontrado = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
				       + CAMPO4  +              
				       " FROM " 
				       + TABLA + 
				       " WHERE " 
				       + CAMPO1 + " = '" + liReferenciaID	+ "'";
			
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

	  					logger.debug(CAMPO1+":|"+liReferenciaID+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR Referencia:|"+liReferenciaID+"|");

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

	public static boolean setEstado(Connection conexion, long liReferenciaID, String sEstado)
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO10 + " = '"+ sEstado + 
					"' "+
					" WHERE "
					+ CAMPO1 + " = '" + liReferenciaID + "'";
			
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

				logger.error("ERROR Referencia:|"+liReferenciaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}		

		return bSalida;
	}
	
	public static String getEstado(Connection conexion, long liReferenciaID)
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
					+ CAMPO10 + 
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '" + liReferenciaID + "'";
			
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

						sEstado = rs.getString(CAMPO10);

						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO10+":|"+sEstado+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sEstado = "";

				logger.error("ERROR Referencia:|"+liReferenciaID+"|");

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
	
	public static boolean setNota(Connection conexion, long liReferenciaID, String sNota)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO11 + " = '"+ sNota +"' "+
					" WHERE "
					+ CAMPO1 + " = '"+ liReferenciaID +"'";
			
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

				logger.error("ERROR COMUNIDAD:|"+liReferenciaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

			} 
			finally 
			{

				Utils.closeStatement(stmt);
			}			
		}

		return bSalida;
	}
	
	public static String getNota(Connection conexion, long liReferenciaID)
	{
		String sNota = "";

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
						+ CAMPO1 + " = '"+ liReferenciaID +"'";
			
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

						sNota = rs.getString(CAMPO11);
						
						logger.debug(CAMPO1+":|"+liReferenciaID+"|");
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO11+":|"+sNota+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sNota = "";
				
				logger.error("ERROR COMUNIDAD:|"+liReferenciaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sNota;
	}
}
