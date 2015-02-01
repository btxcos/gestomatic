package com.provisiones.dal.qm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Fichero;

public class QMFicheros 
{
	private static Logger logger = LoggerFactory.getLogger(QMFicheros.class.getName());
	
	public static final String TABLA = "pp002_ficheros_tbl";

	public static final String CAMPO1 = "fichero_id";
	
	public static final String CAMPO2 = "nombre";
	public static final String CAMPO3 = "tipo_fichero";
	public static final String CAMPO4 = "respuesta";
	
	private QMFicheros(){}
	
	public static long addFichero(Connection conexion, Fichero NuevoFichero, byte btRespuesta)
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
					+ CAMPO2 + ","
					+ CAMPO3 + "," 
					+ CAMPO4 +
					") VALUES ('"
					+ NuevoFichero.getsNombre() + "','"
					+ NuevoFichero.getiTipoFichero() + "',"
					+ btRespuesta + ")";

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

				logger.error("ERROR FICHERO:|"+NuevoFichero.getsNombre()+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());	
			} 
			finally
			{

				Utils.closeStatement(stmt);
			}
		}

		return liCodigo;
	}
	
	public static boolean delFichero(Connection conexion, long liFicheroID) 
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '" + liFicheroID + "'";
			
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

				logger.error("ERROR FICHEROS:|"+liFicheroID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
			}
		}		

		return bSalida;
	}
	
	public static Fichero getFichero(Connection conexion, long liFicheroID)
	{
		String sNombre = "";
		int iTipoFichero = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
				       + CAMPO2 + ","
				       + CAMPO3 +
				       " FROM " 
				       + TABLA + 
				       " WHERE "
				       + CAMPO1 + " = '"+ liFicheroID +"'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");

				logger.debug(CAMPO1 + ":|"+liFicheroID+"|");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;

						sNombre = rs.getString(CAMPO2);
						iTipoFichero = rs.getInt(CAMPO3);  

						logger.debug("Encontrado el registro!");
					}
				}

				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				sNombre = "";
				iTipoFichero = 0;

				logger.error("ERROR PAGO:|"+liFicheroID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new Fichero(
				sNombre,
				iTipoFichero);
	}
	
	public static long getFicheroID(Connection conexion, String sNombre)
	{
		long liPagoID = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
				       	+ CAMPO1  +                
				       	" FROM " 
				       	+ TABLA + 
				       	" WHERE "
				       	+ CAMPO2 + " = '"+ sNombre +"'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");
				
				logger.debug(CAMPO2 + ":|"+sNombre+"|");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;

						liPagoID = rs.getLong(CAMPO1);
						
						logger.debug(CAMPO1+":|"+liPagoID+"|");
						
						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				liPagoID = 0;
				
				logger.error("ERROR FICHERO:|"+sNombre+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return liPagoID;
	}
	
	public static boolean getRespuesta(Connection conexion, long liFicheroID)
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
				       	+ CAMPO1 + " = '"+ liFicheroID +"' AND "
				       	+ CAMPO4 + " = "+ ValoresDefecto.DEF_SI;
			
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
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;
				
				logger.error("ERROR FICHERO:|"+liFicheroID+"|");

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
	
	public static String getTipoFichero(Connection conexion, long liFicheroID)
	{
		String sTipoFichero = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
						+ CAMPO3+
						" FROM " 
						+ TABLA + 
						" WHERE "
						+ CAMPO1 + " = '"+ liFicheroID +"'";
			
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

						sTipoFichero = rs.getString(CAMPO3);
						
						logger.debug("Encontrado el registro!");

					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sTipoFichero = "";
				
				logger.error("ERROR GASTO PAGADO:|"+liFicheroID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sTipoFichero;
	}
}

//Autor: Francisco Valverde Manjón
