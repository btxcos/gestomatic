package com.provisiones.dal.qm.listas;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMComunidades;
//import com.provisiones.dal.qm.QMComunidades;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Comunidad;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.ComunidadTabla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class QMListaComunidadesActivos
{
	private static Logger logger = LoggerFactory.getLogger(QMListaComunidadesActivos.class.getName());
	
	public static final String TABLA = "pp002_lista_comunidades_activos_multi";

	//identificadores
	public static final String CAMPO1  = "cod_coaces";
	public static final String CAMPO2  = "cod_comunidad";
	public static final String CAMPO3  = "cod_movimiento";    

	//Campos de control
	public static final String CAMPO4  = "cod_validado";    
	public static final String CAMPO5  = "usuario_movimiento";
	public static final String CAMPO6  = "fecha_movimiento";    

	private QMListaComunidadesActivos(){}
	
	public static boolean addRelacionComunidad(Connection conexion, int iCodCOACES, long liCodComunidad, long liCodMovimiento)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			String sUsuario = ConnectionManager.getUser();
			
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "INSERT INTO " 
					   + TABLA + 
					   " ("
					   + CAMPO1  + "," 
				       + CAMPO2  + ","              
				       + CAMPO3  + ","
				       + CAMPO4  + ","
				       + CAMPO5  + ","
				       + CAMPO6  +    
				       ") VALUES ('"
				       + iCodCOACES + "','" 
				       + liCodComunidad + "','"
				       + liCodMovimiento + "','"
				       + ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE + "','"
				       + sUsuario  + "','"
				       + Utils.timeStamp() + 
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

				logger.error("ERROR COACES:|"+iCodCOACES+"|");
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

	public static boolean addRelacionComunidadInyectada(Connection conexion, int iCodCOACES, long liCodComunidad, long liCodMovimiento)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			String sUsuario = ConnectionManager.getUser();
			
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "INSERT INTO " 
					   + TABLA + 
					   " ("
					   + CAMPO1  + "," 
				       + CAMPO2  + ","              
				       + CAMPO3  + ","
				       + CAMPO4  + ","
				       + CAMPO5  + ","
				       + CAMPO6  +    
				       ") VALUES ('"
				       + iCodCOACES + "','" 
				       + liCodComunidad + "','"
				       + liCodMovimiento + "','"
				       + ValoresDefecto.DEF_MOVIMIENTO_VALIDADO + "','"
				       + sUsuario  + "','"
				       + Utils.timeStamp() + 
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

				logger.error("ERROR COACES:|"+iCodCOACES+"|");
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

	public static boolean existeRelacionComunidad(Connection conexion, int iCodCOACES, long liCodComunidad, long liCodMovimiento)
	{
		boolean bEncontrado = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
				       + CAMPO5  +
				       " FROM " 
				       + TABLA + 
				       " WHERE (" 
				       + CAMPO1 + " = '" + iCodCOACES + "' AND "
				       + CAMPO2 + " = '" + liCodComunidad + "' AND "
				       + CAMPO3 + " = '" + liCodMovimiento	+ 
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

				logger.error("ERROR COACES:|"+iCodCOACES+"|");
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

	public static boolean compruebaRelacionComunidadActivo(Connection conexion, long liCodComunidad, int iCodCOACES)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
				       + CAMPO3  +               
				       " FROM " 
				       + TABLA + 
				       " WHERE ("
				       + CAMPO1 + " = '" + iCodCOACES + 
				       
				       "' AND "

		      		   + CAMPO2 + " IN (SELECT " 
					   + QMComunidades.CAMPO1 + 
					   " FROM " + QMComunidades.TABLA +
					   " WHERE (" 
					   + QMComunidades.CAMPO1 + " = '" + liCodComunidad + "' AND " 
				       + QMComunidades.CAMPO13 + " = '" + ValoresDefecto.DEF_ALTA + "')))";
			
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

				logger.error("ERROR COACES:|"+iCodCOACES+"|");

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

	public static boolean activoVinculadoComunidad(Connection conexion, int iCodCOACES)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
				       + CAMPO3  +               
				       " FROM " 
				       + TABLA + 
				       " WHERE ("
				       + CAMPO1 + " = '" + iCodCOACES + "'" + " AND "

				       + CAMPO2 + " IN (SELECT " 
				       + QMComunidades.CAMPO1 + 
				       " FROM " 
				       + QMComunidades.TABLA +
				       " WHERE " 
				       + QMComunidades.CAMPO13 + " = '" + ValoresDefecto.DEF_ALTA + "'))";
			
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

				logger.error("ERROR COACES:|"+iCodCOACES+"|");

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
	
	public static long getMovimientoDeActivoVinculadoComunidad(Connection conexion, long liCodComunidad, int iCodCOACES)
	{
		long liMovimiento = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
				       + CAMPO3  +
				       " FROM " 
				       + TABLA + 
				       " WHERE (" 
				       + CAMPO1 + " = '" + iCodCOACES + "' AND  "
				       + CAMPO2 + " = '" + liCodComunidad +
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

						liMovimiento = rs.getLong(CAMPO3);
						
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
				liMovimiento = 0;

				logger.error("ERROR COACES:|"+iCodCOACES+"|");
				logger.error("ERROR Comunidad:|"+liCodComunidad+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return liMovimiento;
	}
	
	public static boolean resuelveActivo(Connection conexion, int iCOACES, long liCodComunidad)
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO4 + " = '"+ ValoresDefecto.DEF_MOVIMIENTO_RESUELTO + "' "+
					" WHERE (" 
					+ CAMPO1 + " = '" + iCOACES	+ "' AND "
					+ CAMPO2 + " = '" + liCodComunidad + "' AND "
					+ CAMPO4 + " = '" + ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE	+ "')";
			
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

				logger.error("ERROR COACES:|"+iCOACES+"|");
				logger.error("ERROR COMUNIDAD:|"+liCodComunidad+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
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
					+ CAMPO4 + " = '"+ sValidado + "' "+
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
					+ CAMPO4 + 
					" FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO3 + " = '" + liCodMovimiento	+ "'";
			
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

						sValidado = rs.getString(CAMPO5);
						
						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO5+":|"+sValidado+"|");
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
			
			String sQuery = "SELECT COUNT("+CAMPO1+") FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO4 + " = '" + sCodValidado + "'";
			
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

						liNumero = rs.getLong("COUNT("+CAMPO1+")");
						
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
	
	public static long buscaNumeroActivos(Connection conexion, long liCodComunidad)
	{
		long liNumero = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT COUNT("+CAMPO1+") FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO2 + " = '" + liCodComunidad + "'";
			
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

						liNumero = rs.getLong("COUNT("+CAMPO1+")");
						
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

				logger.error("ERROR liCodComunidad:|"+liCodComunidad+"|");

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
	
	public static ArrayList<ActivoTabla> buscaActivosComunidad(Connection conexion, long liCodComunidad)
	{
		ArrayList<ActivoTabla> resultado = new ArrayList<ActivoTabla>();
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			String sCOACES = "";
			String sCOPOIN = "";
			String sNOMUIN = "";
			String sNOPRAC = "";
			String sNOVIAS = "";
			String sNUPIAC = "";
			String sNUPOAC = "";
			String sNUPUAC = "";
			//String sNURCAT = "";
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + QMActivos.CAMPO1 + ","        
					   + QMActivos.CAMPO14 + ","
					   + QMActivos.CAMPO11 + ","
					   + QMActivos.CAMPO13 + ","
					   + QMActivos.CAMPO6 + ","
					   + QMActivos.CAMPO9 + ","
					   + QMActivos.CAMPO7 + ","
					   + QMActivos.CAMPO10 +
					   //+ QMActivos.CAMPO81 + 
					   " FROM " 
					   + QMActivos.TABLA + 
					   " WHERE "
					   + QMActivos.CAMPO1 +" IN (SELECT "
					   + CAMPO1 + 
					   " FROM " 
					   + TABLA + 
					   " WHERE "
					   + CAMPO2 + " = '" + liCodComunidad	+ "')";
			
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
						
						sCOACES = rs.getString(QMActivos.CAMPO1);
						sCOPOIN = rs.getString(QMActivos.CAMPO14);
						sNOMUIN = rs.getString(QMActivos.CAMPO11);
						sNOPRAC = rs.getString(QMActivos.CAMPO13);
						sNOVIAS = rs.getString(QMActivos.CAMPO6);
						sNUPIAC = rs.getString(QMActivos.CAMPO9);
						sNUPOAC = rs.getString(QMActivos.CAMPO7);
						sNUPUAC = rs.getString(QMActivos.CAMPO10);
						//sNURCAT = rs.getString(QMActivos.CAMPO81);
						
						ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, "");
						
						resultado.add(activoencontrado);
						
						logger.debug("Encontrado el registro!");
						logger.debug(QMActivos.CAMPO1+":|"+sCOACES+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ActivoTabla>();

				logger.error("ERROR Comunidad:|"+liCodComunidad+"|");

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
	
	public static ArrayList<ActivoTabla> buscaActivosComunidadDisponibles(Connection conexion, ActivoTabla filtro, long liCodComunidad)
	{
		ArrayList<ActivoTabla> resultado = new ArrayList<ActivoTabla>();
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			String sCOACES = "";
			String sCOPOIN = "";
			String sNOMUIN = "";
			String sNOPRAC = "";
			String sNOVIAS = "";
			String sNUPIAC = "";
			String sNUPOAC = "";
			String sNUPUAC = "";
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
						
						   + QMActivos.CAMPO1 + ","        
						   + QMActivos.CAMPO14 + ","
						   + QMActivos.CAMPO11 + ","
						   + QMActivos.CAMPO13 + ","
						   + QMActivos.CAMPO6 + ","
						   + QMActivos.CAMPO9 + ","
						   + QMActivos.CAMPO7 + ","
						   + QMActivos.CAMPO10 + 

						   " FROM " 
						   + QMActivos.TABLA + 
						   " WHERE ("

						   + QMActivos.CAMPO14 + " LIKE '%" + filtro.getCOPOIN()	+ "%' AND "  
						   + QMActivos.CAMPO11 + " LIKE '%" + filtro.getNOMUIN()	+ "%' AND "  
						   + QMActivos.CAMPO13 + " LIKE '%" + filtro.getNOPRAC()	+ "%' AND "  
						   + QMActivos.CAMPO6 + " LIKE '%" + filtro.getNOVIAS()	+ "%' AND "  
						   + QMActivos.CAMPO9 + " LIKE '%" + filtro.getNUPIAC()	+ "%' AND "  
						   + QMActivos.CAMPO7 + " LIKE '%" + filtro.getNUPOAC()	+ "%' AND "  
						   + QMActivos.CAMPO10 + " LIKE '%" + filtro.getNUPUAC()	+ "%' AND "			

						   + QMActivos.CAMPO1 +" IN (SELECT "

						   + CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE (" 
						   
						   + CAMPO2 +" IN (SELECT "

						   + QMComunidades.CAMPO1 + 
						   " FROM " 
						   + QMComunidades.TABLA + 
						   " WHERE "
						   + QMComunidades.CAMPO1 + " = '" + liCodComunidad	+ "'))))";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();
				
				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sCOACES = rs.getString(QMActivos.CAMPO1);
						sCOPOIN = rs.getString(QMActivos.CAMPO14);
						sNOMUIN = rs.getString(QMActivos.CAMPO11);
						sNOPRAC = rs.getString(QMActivos.CAMPO13);
						sNOVIAS = rs.getString(QMActivos.CAMPO6);
						sNUPIAC = rs.getString(QMActivos.CAMPO9);
						sNUPOAC = rs.getString(QMActivos.CAMPO7);
						sNUPUAC = rs.getString(QMActivos.CAMPO10);
						
						ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, "");
						
						resultado.add(activoencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug(QMActivos.CAMPO1+":|"+sCOACES+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ActivoTabla>();
				
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
	
	public static ArrayList<ActivoTabla> buscaActivosSinComunidad(Connection conexion, ActivoTabla filtro)
	{
		ArrayList<ActivoTabla> resultado = new ArrayList<ActivoTabla>();
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;			

			String sCOACES = "";
			String sCOPOIN = "";
			String sNOMUIN = "";
			String sNOPRAC = "";
			String sNOVIAS = "";
			String sNUPIAC = "";
			String sNUPOAC = "";
			String sNUPUAC = "";
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
						
						   + QMActivos.CAMPO1 + ","        
						   + QMActivos.CAMPO14 + ","
						   + QMActivos.CAMPO11 + ","
						   + QMActivos.CAMPO13 + ","
						   + QMActivos.CAMPO6 + ","
						   + QMActivos.CAMPO9 + ","
						   + QMActivos.CAMPO7 + ","
						   + QMActivos.CAMPO10 + 

						   " FROM " 
						   + QMActivos.TABLA + 
						   " WHERE ("

						   + QMActivos.CAMPO14 + " LIKE '%" + filtro.getCOPOIN()	+ "%' AND "  
						   + QMActivos.CAMPO11 + " LIKE '%" + filtro.getNOMUIN()	+ "%' AND "  
						   + QMActivos.CAMPO13 + " LIKE '%" + filtro.getNOPRAC()	+ "%' AND "  
						   + QMActivos.CAMPO6 + " LIKE '%" + filtro.getNOVIAS()	+ "%' AND "  
						   + QMActivos.CAMPO9 + " LIKE '%" + filtro.getNUPIAC()	+ "%' AND "  
						   + QMActivos.CAMPO7 + " LIKE '%" + filtro.getNUPOAC()	+ "%' AND "  
						   + QMActivos.CAMPO10 + " LIKE '%" + filtro.getNUPUAC()	+ "%' AND "			

						   + QMActivos.CAMPO1 +" NOT IN (SELECT "

						   + CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE "
						   
							+ CAMPO2 + " IN (SELECT "
							+ QMComunidades.CAMPO1 +
							" FROM " 
							+ QMComunidades.TABLA + 
							" WHERE " 
							+ QMComunidades.CAMPO13 + " = '" + ValoresDefecto.DEF_ALTA + "' )))";
			
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
						
						sCOACES = rs.getString(QMActivos.CAMPO1);
						sCOPOIN = rs.getString(QMActivos.CAMPO14);
						sNOMUIN = rs.getString(QMActivos.CAMPO11);
						sNOPRAC = rs.getString(QMActivos.CAMPO13);
						sNOVIAS = rs.getString(QMActivos.CAMPO6);
						sNUPIAC = rs.getString(QMActivos.CAMPO9);
						sNUPOAC = rs.getString(QMActivos.CAMPO7);
						sNUPUAC = rs.getString(QMActivos.CAMPO10);
						
						ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, "");
						
						resultado.add(activoencontrado);
						
						//logger.debug("Encontrado el registro!");
						//logger.debug(QMActivos.CAMPO1+":|"+sCOACES+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ActivoTabla>();
				
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
	
	public static ArrayList<ActivoTabla> buscaActivosConComunidad(Connection conexion, ActivoTabla filtro)
	{
		ArrayList<ActivoTabla> resultado = new ArrayList<ActivoTabla>();
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			String sCOACES = "";
			String sCOPOIN = "";
			String sNOMUIN = "";
			String sNOPRAC = "";
			String sNOVIAS = "";
			String sNUPIAC = "";
			String sNUPOAC = "";
			String sNUPUAC = "";
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
						
						   + QMActivos.CAMPO1 + ","        
						   + QMActivos.CAMPO14 + ","
						   + QMActivos.CAMPO11 + ","
						   + QMActivos.CAMPO13 + ","
						   + QMActivos.CAMPO6 + ","
						   + QMActivos.CAMPO9 + ","
						   + QMActivos.CAMPO7 + ","
						   + QMActivos.CAMPO10 + 

						   " FROM " 
						   + QMActivos.TABLA + 
						   " WHERE ("

						   + QMActivos.CAMPO14 + " LIKE '%" + filtro.getCOPOIN()	+ "%' AND "  
						   + QMActivos.CAMPO11 + " LIKE '%" + filtro.getNOMUIN()	+ "%' AND "  
						   + QMActivos.CAMPO13 + " LIKE '%" + filtro.getNOPRAC()	+ "%' AND "  
						   + QMActivos.CAMPO6 + " LIKE '%" + filtro.getNOVIAS()	+ "%' AND "  
						   + QMActivos.CAMPO9 + " LIKE '%" + filtro.getNUPIAC()	+ "%' AND "  
						   + QMActivos.CAMPO7 + " LIKE '%" + filtro.getNUPOAC()	+ "%' AND "  
						   + QMActivos.CAMPO10 + " LIKE '%" + filtro.getNUPUAC()	+ "%' AND "			

						   + QMActivos.CAMPO1 +" IN (SELECT "

						   + CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE "
						   
							+ CAMPO2 + " IN (SELECT "
							+ QMComunidades.CAMPO1 +
							" FROM " 
							+ QMComunidades.TABLA + 
							" WHERE " 
							+ QMComunidades.CAMPO13 + " = '" + ValoresDefecto.DEF_ALTA + "')))";
			
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
						
						sCOACES = rs.getString(QMActivos.CAMPO1);
						sCOPOIN = rs.getString(QMActivos.CAMPO14);
						sNOMUIN = rs.getString(QMActivos.CAMPO11);
						sNOPRAC = rs.getString(QMActivos.CAMPO13);
						sNOVIAS = rs.getString(QMActivos.CAMPO6);
						sNUPIAC = rs.getString(QMActivos.CAMPO9);
						sNUPOAC = rs.getString(QMActivos.CAMPO7);
						sNUPUAC = rs.getString(QMActivos.CAMPO10);
						
						ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, "");
						
						resultado.add(activoencontrado);
						
						logger.debug("Encontrado el registro!");
						logger.debug(QMActivos.CAMPO1+":|"+sCOACES+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ActivoTabla>();

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
	
	public static ArrayList<Long>  getMovimientosComunidadesActivoPorEstado(Connection conexion, String sEstado) 
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
					+ CAMPO3+ 
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO4 + " = '" + sEstado + "'";
			
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

						resultado.add(rs.getLong(CAMPO3));
											
						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO4+":|"+sEstado+"|");
						logger.debug(CAMPO3+":|"+resultado.get(i)+"|");

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

	
	public static ArrayList<ComunidadTabla> buscaComunidadActivo(Connection conexion, int iCodCOACES)
	{
		//Sin uso

		ArrayList<ComunidadTabla> resultado = new ArrayList<ComunidadTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + QMComunidades.CAMPO1 + "," 
					   + QMComunidades.CAMPO2 + ","        
					   + QMComunidades.CAMPO3 + ","
					   + "AES_DECRYPT("+QMComunidades.CAMPO4 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+QMComunidades.CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+QMComunidades.CAMPO8 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))" +

					   
					   "  FROM " 
					   + QMComunidades.TABLA + 
					   " WHERE "
					   
					   + QMComunidades.CAMPO1 + " IN (SELECT " 
					   + CAMPO2 +
					   " FROM " 
					   + TABLA +
					   " WHERE "
					   + CAMPO1 +  " = '" + iCodCOACES	+ "')";
			
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
						
						String sCOCLDO = rs.getString(QMComunidades.CAMPO2);
						String sNUDCOM = rs.getString(QMComunidades.CAMPO3);

						String sNOMCOC = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO4 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						String sNOMPRC = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						String sNOMADC = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO8 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");

						
						String sActivos = ""+buscaNumeroActivos(conexion, rs.getLong(QMComunidades.CAMPO1));
						
						ComunidadTabla comunidadencontrada = new ComunidadTabla(sCOCLDO, sNUDCOM, sNOMCOC, sNOMPRC, sNOMADC, sActivos);
						
						resultado.add(comunidadencontrada);
						
						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+iCodCOACES+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ComunidadTabla>();

				logger.error("ERROR COACES:|"+iCodCOACES+"|");

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
	
	public static Comunidad buscaComunidadPorActivo(Connection conexion, int iCodCOACES)
	{


		String sCOCLDO = "";
		String sNUDCOM = "";
		String sNOMCOC = "";
		String sNODCCO = "";
		String sNOMPRC = "";
		String sNUTPRC = "";
		String sNOMADC = "";
		String sNUTADC = "";
		String sNODCAD = "";
		String sCuenta = "";
		String sOBTEXC = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
				       + QMComunidades.CAMPO2  + ","
				       + QMComunidades.CAMPO3  + ","              
				       /*+ QMComunidades.CAMPO4  + ","              
				       + QMComunidades.CAMPO5  + ","              
				       + QMComunidades.CAMPO6  + ","              
				       + QMComunidades.CAMPO7  + ","              
				       + QMComunidades.CAMPO8  + ","              
				       + QMComunidades.CAMPO9  + ","              
				       + QMComunidades.CAMPO10 + ","              
				       + QMComunidades.CAMPO11 + ","              
				       + QMComunidades.CAMPO12 + ","              
				       + QMComunidades.CAMPO13 + "," 
				       + QMComunidades.CAMPO14 + ","*/
				       + "AES_DECRYPT("+QMComunidades.CAMPO4 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+QMComunidades.CAMPO5 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+QMComunidades.CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+QMComunidades.CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+QMComunidades.CAMPO8 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+QMComunidades.CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+QMComunidades.CAMPO10+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + QMComunidades.CAMPO11 + "," 
				       
				       + QMComunidades.CAMPO12 +     
					   
					   " FROM " 
					   + QMComunidades.TABLA + 
					   " WHERE "
					   
					   + QMComunidades.CAMPO1 + " IN (SELECT " + CAMPO2 +
					   " FROM " 
					   + TABLA +
					   " WHERE "
					   + CAMPO1 +  " = '" + iCodCOACES	+ "')";
			
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
						
						sCOCLDO = rs.getString(QMComunidades.CAMPO2); 
						sNUDCOM = rs.getString(QMComunidades.CAMPO3);
						
						/*sNOMCOC = rs.getString(QMComunidades.CAMPO4); 
						sNODCCO = rs.getString(QMComunidades.CAMPO5); 
						sNOMPRC = rs.getString(QMComunidades.CAMPO6); 
						sNUTPRC = rs.getString(QMComunidades.CAMPO7); 
						sNOMADC = rs.getString(QMComunidades.CAMPO8); 
						sNUTADC = rs.getString(QMComunidades.CAMPO9); 
						sNODCAD = rs.getString(QMComunidades.CAMPO10);
						sNUCCEN = rs.getString(QMComunidades.CAMPO11);
						sNUCCOF = rs.getString(QMComunidades.CAMPO12);
						sNUCCDI = rs.getString(QMComunidades.CAMPO13);
						sNUCCNT = rs.getString(QMComunidades.CAMPO14);*/
						
						sNOMCOC = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO4 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"); 
						sNODCCO = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO5 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"); 
						sNOMPRC = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"); 
						sNUTPRC = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"); 
						sNOMADC = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO8 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"); 
						sNUTADC = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"); 
						sNODCAD = rs.getString("AES_DECRYPT("+QMComunidades.CAMPO10+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"); 

						sCuenta = rs.getString(QMComunidades.CAMPO11);						
						sOBTEXC = rs.getString(QMComunidades.CAMPO12);

						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+iCodCOACES+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				sCOCLDO = "";
				sNUDCOM = "";
				sNOMCOC = "";
				sNODCCO = "";
				sNOMPRC = "";
				sNUTPRC = "";
				sNOMADC = "";
				sNUTADC = "";
				sNODCAD = "";
				sCuenta = "";
				sOBTEXC = "";

				logger.error("ERROR COACES:|"+iCodCOACES+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

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
				sCuenta,
				sOBTEXC);
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
					+ CAMPO3  + 
					" FROM " 
					+ TABLA + 
					" WHERE (" 
					+ CAMPO2 + " = '" + liCodComunidad + "' AND "
					+ CAMPO3 + " >=  '" + liCodMovimiento + 
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
						
						resultado.add(rs.getLong(CAMPO3));

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