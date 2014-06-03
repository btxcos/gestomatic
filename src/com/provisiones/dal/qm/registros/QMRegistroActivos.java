package com.provisiones.dal.qm.registros;

import java.io.Serializable;
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
import com.provisiones.dal.qm.listas.QMListaReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.EstadoActivoTabla;

public class QMRegistroActivos implements Serializable 
{
	private static Logger logger = LoggerFactory.getLogger(QMRegistroActivos.class.getName());
	
	private static final long serialVersionUID = -6800531057724764009L;
	
	public static final String TABLA = "pp002_registro_activos_multi";

	//identificadores
	public static final String CAMPO1  = "cod_coaces";

	//Campos de control
	public static final String CAMPO2  = "fecha_registro";
	public static final String CAMPO3  = "usuario_carga";
	public static final String CAMPO4  = "fecha_carga";
	public static final String CAMPO5  = "cod_estado";
	public static final String CAMPO6  = "fecha_bloqueo";
	public static final String CAMPO7  = "nota";
	

	
	public static boolean addRegistroActivo(Connection conexion, int iCodCOACES)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			String sUsuario = ConnectionManager.getUser();
			
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sTimeStamp = Utils.timeStamp();
			
			String sQuery = "INSERT INTO " 
					   + TABLA + 
					   " ("
					   + CAMPO1  + "," 
				       + CAMPO2  + ","              
				       + CAMPO3  + ","
				       + CAMPO4  + ","
				       + CAMPO5  + ","
				       + CAMPO6  + ","
				       + CAMPO7  + 
				       ") VALUES ('"
				       + iCodCOACES + "','" 
				       + sTimeStamp + "','"
				       + sUsuario + "','"
				       + sTimeStamp + "','"
				       + ValoresDefecto.DEF_ACTIVO_DESBLOQUEADO + "','"
				       + ValoresDefecto.CAMPO_NUME_SIN_INFORMAR + "', "
				       + "AES_ENCRYPT('"+ValoresDefecto.CAMPO_ALFA_SIN_INFORMAR+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))" + 
				       ")";
			
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

				logger.error("ERROR Activo:|"+iCodCOACES+"|");
				//logger.error("ERROR Movimiento:|"+sFechaRegistro+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
			}
		}	

		return bSalida;
	}
	
	public static boolean modRegistroActivo(Connection conexion, int iCodCOACES)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			String sUsuario = ConnectionManager.getUser();
			
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO3  + " = '"+ sUsuario + "', "
					+ CAMPO4  + " = '"+ Utils.timeStamp() + "' "+
					" WHERE " 
					+ CAMPO1  + " = '"+ iCodCOACES +"'";
			
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

				logger.error("ERROR iCodCOACES:|"+iCodCOACES+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}			
		}

		return bSalida;
	}

	public static boolean delRegistroActivo(Connection conexion, int iCodCOACES)
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO1 + " = '" + iCodCOACES	+ "'";
			
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

				logger.error("ERROR iCodCOACES:|"+iCodCOACES+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean existeRegistroActivo(Connection conexion, int iCodCOACES)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;	

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
				       + CAMPO1  + 
				       " FROM "
				       + TABLA + 
				       " WHERE " 
				       + CAMPO1 + " = '" + iCodCOACES + "'";
			
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

				logger.error("ERROR iCodCOACES:|"+iCodCOACES+"|");

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

	public static boolean setComentario(Connection conexion, int iCodCOACES, String sComentario)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO7 + " = '"+ sComentario + "' "+
					" WHERE "
					+ CAMPO1  + " = '"+ iCodCOACES +"'";
			
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

				logger.error("ERROR ACTIVO:|"+iCodCOACES+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}
		
		return bSalida;
	}
	
	public static String getFechaBloqueo(Connection conexion, int iCodCOACES)
	{
		String sFecha = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
						+ CAMPO6 +
						" FROM " 
						+ TABLA + 
						" WHERE "
						+ CAMPO1 + " = '"+ iCodCOACES +"'";
			
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

						sFecha = rs.getString(CAMPO6);
						
						logger.debug(CAMPO1+":|"+iCodCOACES+"|");
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO6+":|"+sFecha+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sFecha = "";
				
				logger.error("ERROR ACTIVO:|"+iCodCOACES+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sFecha;
	}
	
	public static boolean setFechaBloqueo(Connection conexion, int iCodCOACES, String sFecha)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO6 + " = '"+ sFecha +"' "+
					
					" WHERE "
					+ CAMPO1 + " = '"+ iCodCOACES +"'";
			
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

				logger.error("ERROR ACTIVO:|"+iCodCOACES+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

			} 
			finally 
			{

				Utils.closeStatement(stmt);
			}			
		}

		return bSalida;
	}
	
	
	
	public static boolean setNota(Connection conexion, int iCodCOACES, String sNota)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					//+ CAMPO7 + " = '"+ sNota +"' "+
					+ CAMPO7 + " = AES_ENCRYPT('"+sNota+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) "+
					" WHERE "
					+ CAMPO1 + " = '"+ iCodCOACES +"'";
			
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

				logger.error("ERROR ACTIVO:|"+iCodCOACES+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

			} 
			finally 
			{

				Utils.closeStatement(stmt);
			}			
		}

		return bSalida;
	}
	
	public static String getNota(Connection conexion, int iCodCOACES)
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
						//+ CAMPO7 +
						+"AES_DECRYPT("+CAMPO7+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"+
						" FROM " 
						+ TABLA + 
						" WHERE "
						+ CAMPO1 + " = '"+ iCodCOACES +"'";
			
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

						//sNota = rs.getString(CAMPO7);
						
						sNota = rs.getString("AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						
						logger.debug(CAMPO1+":|"+iCodCOACES+"|");
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO7+":|"+sNota+"|");
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
				
				logger.error("ERROR ACTIVO:|"+iCodCOACES+"|");

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

	public static ArrayList<EstadoActivoTabla> buscaActivosRegistradosPorFiltroEstado(Connection conexion, ActivoTabla filtro, String sEstado)
	{
		ArrayList<EstadoActivoTabla> resultado = new ArrayList<EstadoActivoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String sCondicionEstado = sEstado.isEmpty()? "": CAMPO5 + " = '"+ sEstado +"' AND "; 
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						   + CAMPO1 + ","
						   + CAMPO5 + ","
						   + CAMPO6 +
						   " FROM " + TABLA +
						   " WHERE ("
						   + sCondicionEstado
						   + CAMPO1 + " IN (SELECT "
						   + QMActivos.CAMPO1 + 
						   " FROM " + QMActivos.TABLA + 
						   " WHERE ("
						   + QMActivos.CAMPO14 + " LIKE '%" + filtro.getCOPOIN()	+ "%' AND "  
						   + QMActivos.CAMPO11 + " LIKE '%" + filtro.getNOMUIN()	+ "%' AND "  
						   + QMActivos.CAMPO13 + " LIKE '%" + filtro.getNOPRAC()	+ "%' AND "  
						   + QMActivos.CAMPO6 + " LIKE '%" + filtro.getNOVIAS()	+ "%' AND "  
						   + QMActivos.CAMPO9 + " LIKE '%" + filtro.getNUPIAC()	+ "%' AND "  
						   + QMActivos.CAMPO7 + " LIKE '%" + filtro.getNUPOAC()	+ "%' AND "  
						   + QMActivos.CAMPO10 + " LIKE '%" + filtro.getNUPUAC()	+ "%')))";
			
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
						
						String sCOACES = rs.getString(CAMPO1);
						String sEstadoActivo = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESACTI,QMCodigosControl.IESACTI,rs.getString(CAMPO5));
						String sFechaActivacion = Utils.recuperaFecha(rs.getString(CAMPO6));

						
						EstadoActivoTabla activoencontrado = new EstadoActivoTabla(
								sCOACES, 
								sEstadoActivo, 
								sFechaActivacion);
						
						resultado.add(activoencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug("{}:|"+QMActivos.CAMPO1,sCOACES);
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<EstadoActivoTabla>();

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return resultado;

	}
	
	public static ArrayList<EstadoActivoTabla> buscaActivoReferenciaRegistradoPorReferencia(Connection conexion, String sNURCAT)
	{
		ArrayList<EstadoActivoTabla> resultado = new ArrayList<EstadoActivoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						   + CAMPO1 + ","
						   + CAMPO5 + ","
						   + CAMPO6 +
						   " FROM " + TABLA +
						   " WHERE ("
						   + CAMPO1 + " IN (SELECT "
						   + QMListaReferencias.CAMPO1 + 
						   " FROM " + QMListaReferencias.TABLA + 
						   " WHERE "
						   + QMListaReferencias.CAMPO2 + " = '"+sNURCAT+"'))";
			
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
						
						String sCOACES = rs.getString(CAMPO1);
						String sEstadoActivo = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TESACTI,QMCodigosControl.IESACTI,rs.getString(CAMPO5));
						String sFechaActivacion = Utils.recuperaFecha(rs.getString(CAMPO6));

						
						EstadoActivoTabla activoencontrado = new EstadoActivoTabla(
								sCOACES, 
								sEstadoActivo, 
								sFechaActivacion);
						
						resultado.add(activoencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug("{}:|"+QMActivos.CAMPO1,sCOACES);
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<EstadoActivoTabla>();

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return resultado;

	}
	
}
