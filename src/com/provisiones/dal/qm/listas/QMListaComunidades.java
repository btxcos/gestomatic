package com.provisiones.dal.qm.listas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.movimientos.QMMovimientosComunidades;

import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public final class QMListaComunidades
{
	private static Logger logger = LoggerFactory.getLogger(QMListaComunidades.class.getName());

	public static final String TABLA = "pp002_lista_comunidades_multi";

	//identificadores
	public static final String CAMPO1 = "cod_comunidad";
	public static final String CAMPO2 = "cod_movimiento";

	//Campos de control
	public static final String CAMPO3 = "cod_validado";    
	public static final String CAMPO4 = "usuario_movimiento";
	public static final String CAMPO5 = "fecha_movimiento";
	public static final String CAMPO6 = "archivo_envio";
	public static final String CAMPO7 = "archivo_respuesta";
	
	
	private QMListaComunidades(){}
	
	public static boolean addRelacionComunidad(Connection conexion, long liCodComunidad, long liCodMovimiento)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
			String sUsuario = ConnectionManager.getUser();

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
				       + CAMPO7  +  
				       ") VALUES ('"
				       + liCodComunidad + "','" 
				       + liCodMovimiento + "','"
				       + ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE + "','"
				       + sUsuario + "','"
				       + Utils.timeStamp() +"','"
				       + ValoresDefecto.CAMPO_NUME_SIN_INFORMAR + "','"
				       + ValoresDefecto.CAMPO_NUME_SIN_INFORMAR +
				       "' )";
			
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

				logger.error("ERROR Comunidad:|"+liCodComunidad+"|");
				logger.error("ERROR Movimiento:|"+liCodMovimiento+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
			}
		}	

		return bSalida;
	}

	public static boolean addRelacionComunidadInyectada(Connection conexion, long liCodComunidad, long liCodMovimiento)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
			String sUsuario = ConnectionManager.getUser();

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
				       + CAMPO7  +  
				       ") VALUES ('"
				       + liCodComunidad + "','" 
				       + liCodMovimiento + "','"
				       + ValoresDefecto.DEF_MOVIMIENTO_VALIDADO + "','"
				       + sUsuario + "','"
				       + Utils.timeStamp() +"','"
				       + ValoresDefecto.CAMPO_NUME_SIN_INFORMAR + "','"
				       + ValoresDefecto.CAMPO_NUME_SIN_INFORMAR +
				       "' )";
			
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

				logger.error("ERROR Comunidad:|"+liCodComunidad+"|");
				logger.error("ERROR Movimiento:|"+liCodMovimiento+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
			}
		}	

		return bSalida;
	}
	
	public static boolean delRelacionComunidad(Connection conexion, long liCodMovimiento)
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO3 + " = '" + liCodMovimiento	+ "'";
			
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

				logger.error("ERROR Movimiento:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean existeComunidad(Connection conexion, long liCodComunidad)
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
				       + CAMPO1 + " = '" + liCodComunidad + "'";
			
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

				logger.error("ERROR Comunidad:|"+liCodComunidad+"|");

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
	
	public static boolean existeRelacionComunidad(Connection conexion, long liCodComunidad, long liCodMovimiento)
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
				       " WHERE (" 
				       + CAMPO1 + " = '" + liCodComunidad + "' AND "
				       + CAMPO2 + " = '" + liCodMovimiento + 
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

				logger.error("ERROR Comunidad:|"+liCodComunidad+"|");
				logger.error("ERROR Movimiento:|"+liCodMovimiento+"|");

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

	public static boolean existeAltaPendienteComunidad(Connection conexion, long liCodComunidad)
	{
		boolean bEncontrado = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
				       + QMMovimientosComunidades.CAMPO1  +
				       " FROM " 
				       + QMMovimientosComunidades.TABLA + 
				       " WHERE ("
				       + QMMovimientosComunidades.CAMPO5 + " = '" + ValoresDefecto.DEF_COACCI_COMUNIDAD_ALTA + "' AND  "
				       + QMMovimientosComunidades.CAMPO1 + " IN (SELECT "
				       + CAMPO2  +
				       " FROM " 
				       + TABLA + 
				       " WHERE (" 
				       + CAMPO1 + " = '" + liCodComunidad + "' AND  "
				       + CAMPO3 + " = '" + ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE +
				       "')))";
			
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

				logger.error("ERROR Comunidad:|"+liCodComunidad+"|");

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
	
	public static ArrayList<Long>  getMovimientosComunidadesPorEstado(Connection conexion, String sEstado) 
	{
		ArrayList<Long> resultado = new ArrayList<Long>(); 

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

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
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");
				
				int i = 0;
				
				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						resultado.add(rs.getLong(CAMPO2));
											
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO3+":|"+sEstado+"|");
						logger.debug(CAMPO2+":|"+resultado.get(i)+"|");
						
						i++;
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<Long>();

				logger.error("ERROR Validado:|"+sEstado+"|");

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
	
	public static boolean setValidado(Connection conexion, long liCodMovimiento, String sValidado)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO3 + " = '"+ sValidado + "' "+
					" WHERE " 
					+ CAMPO2 + " = '" + liCodMovimiento	+ "'";
			
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

				logger.error("ERROR Movimiento:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}	

		return bSalida;
	}
	
	public static String getValidado(Connection conexion, long liCodMovimiento)
	{
		String sValidado = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO3 + 
					" FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO2 + " = '" + liCodMovimiento	+ "'";
			
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

						sValidado = rs.getString(CAMPO3);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO3+":|"+sValidado+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sValidado = "";

				logger.error("ERROR Movimiento:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sValidado;
	}
	
	public static boolean setArchivoEnvio(Connection conexion, long liCodMovimiento, long liCodArchivo)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO6 + " = '"+ liCodArchivo + "' "+
					" WHERE " 
					+ CAMPO2 + " = '" + liCodMovimiento	+ "'";
			
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

				logger.error("ERROR Movimiento:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}	

		return bSalida;
	}
	
	public static long getArchivoEnvio(Connection conexion, long liCodMovimiento)
	{
		long liCodArchivo = 0;

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
					+ CAMPO2 + " = '" + liCodMovimiento	+ "'";
			
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

						liCodArchivo = rs.getLong(CAMPO6);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO6+":|"+liCodArchivo+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				liCodArchivo = 0;

				logger.error("ERROR Movimiento:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return liCodArchivo;
	}
	
	public static boolean setArchivoRespuesta(Connection conexion, long liCodMovimiento, long liCodArchivo)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO7 + " = '"+ liCodArchivo + "' "+
					" WHERE " 
					+ CAMPO2 + " = '" + liCodMovimiento	+ "'";
			
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

				logger.error("ERROR Movimiento:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}	

		return bSalida;
	}
	
	public static long getArchivoRespuesta(Connection conexion, long liCodMovimiento)
	{
		long liCodArchivo = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO7 + 
					" FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO2 + " = '" + liCodMovimiento	+ "'";
			
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

						liCodArchivo = rs.getLong(CAMPO7);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO7+":|"+liCodArchivo+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				liCodArchivo = 0;

				logger.error("ERROR Movimiento:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return liCodArchivo;
	}
	
	public static long buscaCantidadValidado(Connection conexion, String sCodValidado)
	{
		long liNumero = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT COUNT(*) FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO3 + " = '" + sCodValidado + "'";
			
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

						liNumero = rs.getLong("COUNT(*)");
						
						logger.debug("Encontrado el registro!");

						logger.debug( "Numero de registros:|"+liNumero+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				liNumero = 0;

				logger.error("ERROR CodValidado:|"+sCodValidado+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return liNumero;
	}
	
	public static ArrayList<Long> buscarDependencias(Connection conexion, long liCodComunidad, long liCodMovimiento)
	{
		ArrayList<Long> resultado = new ArrayList<Long>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;	

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO2  + 
					" FROM " 
					+ TABLA + 
					" WHERE (" 
					+ CAMPO1 + " = '" + liCodComunidad + "' AND "
					+ CAMPO2 + " >=  '" + liCodMovimiento + 
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
						
						resultado.add(rs.getLong(CAMPO2));

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
				resultado = new ArrayList<Long>();

				logger.error("ERROR Comunidad:|"+liCodComunidad+"|");
				logger.error("ERROR Movimiento:|"+liCodMovimiento+"|");

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

//Autor: Francisco Valverde Manjón