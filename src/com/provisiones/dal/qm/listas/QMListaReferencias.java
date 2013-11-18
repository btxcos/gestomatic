package com.provisiones.dal.qm.listas;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.ReferenciaTabla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QMListaReferencias
{
	private static Logger logger = LoggerFactory.getLogger(QMListaReferencias.class.getName());

	static String TABLA = "pp001_lista_referencias_multi";

	//identificadores
	static String CAMPO1  = "cod_coaces";
	static String CAMPO2  = "cod_referencia";    
	static String CAMPO3  = "cod_movimiento"; 
	
	//Campos de control
	static String CAMPO4  = "cod_validado";
	static String CAMPO5  = "usuario_movimiento";    
	static String CAMPO6  = "fecha_movimiento";

	public static boolean addRelacionReferencia(Connection conexion, String sCodCOACES, String sCodReferencia, String sCodMovimiento)
	{
		boolean bSalida = false;

		String sUsuario = ConnectionManager.getUser();

		if (conexion != null)
		{
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
				       + sCodCOACES + "','" 
				       + sCodReferencia + "','"
				       + sCodMovimiento + "','"
				       + ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE + "','"
				       + sUsuario + "','"
				       + Utils.timeStamp() +
				       "')";
			
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

				logger.error("ERROR COACES:|"+sCodCOACES+"|");
				logger.error("ERROR Referencia:|"+sCodReferencia+"|");
				logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean delRelacionReferencia(Connection conexion, String sCodMovimiento)
	{
		boolean bSalida = true;

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO3 + " = '" + sCodMovimiento	+ "'";
			
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
				
				logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean existeRelacionReferencia(Connection conexion, String sCodCOACES, String sCodReferencia, String sCodMovimiento)
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
					+ CAMPO1 + " = '" + sCodCOACES + "' AND " 
					+ CAMPO2 + " = '" + sCodReferencia + "' AND " 
					+ CAMPO3 + " = '" + sCodMovimiento	+ 
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
				if (bEncontrado == false) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR COACES:|"+sCodCOACES+"|");
				logger.error("ERROR Referencia:|"+sCodReferencia+"|");
				logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

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
	
	public static boolean compruebaRelacionReferenciaActivo(Connection conexion, String sCodCOACES, String sCodReferencia)
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
					+ CAMPO1 + " = '" + sCodCOACES + "' AND " 
					+ CAMPO2 + " = '" + sCodReferencia + 
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
				if (bEncontrado == false) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR COACES:|"+sCodCOACES+"|");
				logger.error("ERROR Referencia:|"+sCodReferencia+"|");

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
	
	public static ArrayList<String>  getReferenciasPorEstado(Connection conexion, String sEstado) 
	{
		ArrayList<String> resultado = new ArrayList<String>(); 

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

						resultado.add(rs.getString(CAMPO3));
											
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO4+":|"+sEstado+"|");
						logger.debug(CAMPO3+":|"+resultado.get(i)+"|");
						
						i++;
					}
				}
				if (bEncontrado == false) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<String>(); 

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

	public static boolean setValidado(Connection conexion, String sCodMovimiento, String sValidado)
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
					+ CAMPO3 + " = '" + sCodMovimiento	+ "'";
			
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

				logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static String getValidado(Connection conexion, String sCodMovimiento)
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
					+ CAMPO3 + " = '" + sCodMovimiento	+ "'";
			
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

						sValidado = rs.getString(CAMPO4);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO4+":|"+sValidado+"|");
					}
				}
				if (bEncontrado == false) 
				{
	 
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sValidado = "";

				logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

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
			
			String sQuery = "SELECT COUNT(*) FROM " 
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

						liNumero = rs.getLong("COUNT(*)");
						
						logger.debug("Encontrado el registro!");

						logger.debug( "Numero de registros:|"+liNumero+"|");
					}
				}
				if (bEncontrado == false) 
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
	
	public static ArrayList<ReferenciaTabla> buscaReferenciasActivo(Connection conexion, String sCodCOACES)
	{
		ArrayList<ReferenciaTabla> resultado = new ArrayList<ReferenciaTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String sNURCAT = "";
			String sTIRCAT = "";
			String sENEMIS = "";
			String sOBTEXC = "";
			
			String sIMVSUE = "";
			String sIMCATA = "";
			String sFERECA = "";
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + QMReferencias.CAMPO2 + ","        
					   + QMReferencias.CAMPO3 + ","
					   + QMReferencias.CAMPO4 + ","
					   + QMReferencias.CAMPO6 + 

					   //Ampliacion de valor catastral
					   ","
					   + QMReferencias.CAMPO7 + ","
					   + QMReferencias.CAMPO8 + ","
					   + QMReferencias.CAMPO9 + 
					   
					   "  FROM " 
					   + QMReferencias.TABLA + 
					   " WHERE " 
					   + QMReferencias.CAMPO10 + " = '" + ValoresDefecto.DEF_ALTA + "' AND "
					   + QMReferencias.CAMPO1 + " IN " +
					   "(SELECT " + CAMPO2 + 
					   " FROM " 
					   + TABLA +
					   " WHERE "
					   + CAMPO1 +  " = '" + sCodCOACES	+ "')";
			
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
						
						sNURCAT = rs.getString(QMReferencias.CAMPO2);
						sTIRCAT = rs.getString(QMReferencias.CAMPO3);
						sENEMIS = rs.getString(QMReferencias.CAMPO4);
						sOBTEXC = rs.getString(QMReferencias.CAMPO6);

						//Ampliacion de valor catastral
						sIMVSUE = Utils.recuperaImporte(false,rs.getString(QMReferencias.CAMPO7));
						sIMCATA = Utils.recuperaImporte(false,rs.getString(QMReferencias.CAMPO8));
						sFERECA = Utils.recuperaFecha(rs.getString(QMReferencias.CAMPO9));
						
						ReferenciaTabla referenciaencontrada = new ReferenciaTabla(sNURCAT, sTIRCAT, sENEMIS, sOBTEXC

								//Ampliacion de valor catastral
								, sIMVSUE, sIMCATA, sFERECA
								
								);
						
						resultado.add(referenciaencontrada);
						
						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+sCodCOACES+"|");
					}
				}
				if (bEncontrado == false) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ReferenciaTabla>();

				logger.error("ERROR COACES:|"+sCodCOACES+"|");

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
	
	
	public static ArrayList<ActivoTabla> buscaActivosNoAsociados(Connection conexion, ActivoTabla filtro)
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
			String sNURCAT = "";
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						
						   + QMActivos.CAMPO1 + ","        
						   + QMActivos.CAMPO14 + ","
						   + QMActivos.CAMPO11 + ","
						   + QMActivos.CAMPO13 + ","
						   + QMActivos.CAMPO6 + ","
						   + QMActivos.CAMPO9 + ","
						   + QMActivos.CAMPO7 + ","
						   + QMActivos.CAMPO10 + ","
						   + QMActivos.CAMPO81 + 

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
						   +  CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE "
						   
							+ CAMPO2 + " IN (SELECT "
							+ QMReferencias.CAMPO1 +
							"  FROM " 
							+ QMReferencias.TABLA + 
							" WHERE " + QMReferencias.CAMPO10 + " = '" + ValoresDefecto.DEF_ALTA + "' )))";
			
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
						sNURCAT = rs.getString(QMActivos.CAMPO81);
						
						ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, sNURCAT);
						
						resultado.add(activoencontrado);
						
						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+sCOACES+"|");
					}
				}
				if (bEncontrado == false) 
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
	
	public static ArrayList<ActivoTabla> buscaActivosAsociados(Connection conexion, ActivoTabla filtro)
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
			String sNURCAT = "";
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						
						   + QMActivos.CAMPO1 + ","        
						   + QMActivos.CAMPO14 + ","
						   + QMActivos.CAMPO11 + ","
						   + QMActivos.CAMPO13 + ","
						   + QMActivos.CAMPO6 + ","
						   + QMActivos.CAMPO9 + ","
						   + QMActivos.CAMPO7 + ","
						   + QMActivos.CAMPO10 + ","
						   + QMActivos.CAMPO81 + 

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
						   +  CAMPO1 + 
						   " FROM " 
						   + TABLA +
						   " WHERE " 
						   
						   + CAMPO2 + " IN (SELECT "
	   					   + QMReferencias.CAMPO1 + 
	   					   " FROM " 
	   					   + QMReferencias.TABLA +
	   					   " WHERE " 
	   					   + QMReferencias.CAMPO10 + " = '"+ ValoresDefecto.DEF_ALTA + "')))";
			
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
						sNURCAT = rs.getString(QMActivos.CAMPO81);
						
						ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, sNURCAT);
						
						resultado.add(activoencontrado);
						
						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+sCOACES+"|");
					}
				}
				if (bEncontrado == false) 
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
	
	public static boolean activoAsociado(Connection conexion, String sCodCOACES)
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
				    "  FROM " 
				    + TABLA + 
				    " WHERE "
				    + CAMPO1 + " = '" + sCodCOACES + "'";
			
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
				
				logger.error("ERROR COACES:|"+sCodCOACES+"|");

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
	
	public static String referenciaAsociada(Connection conexion, String sCodCOACES)
	{
		String sReferencia = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
				    + QMReferencias.CAMPO2  +
				    " FROM " 
				    + QMReferencias.TABLA + 
					" WHERE "
					+ QMReferencias.CAMPO1 + " IN (SELECT "
				    + CAMPO2  +
				    " FROM " 
				    + TABLA + 
				    " WHERE "
				    + CAMPO1 + " = '" + sCodCOACES + "')";
			
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
						
						sReferencia = rs.getString(QMReferencias.CAMPO2);

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
				sReferencia = "";

				logger.error("ERROR COACES:|"+sCodCOACES+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sReferencia;
	}
	
	public static ArrayList<String> buscarDependencias(Connection conexion, String sCodCOACES, String sCodReferencia, String sCodMovimiento)
	{
		ArrayList<String> resultado = new ArrayList<String>();

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
					+ CAMPO1 + " = '" + sCodCOACES + "' AND "
					+ CAMPO2 + " = '" + sCodReferencia + "' AND "
					+ CAMPO3 + " >=  '" + sCodMovimiento + 
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
						
						resultado.add(rs.getString(CAMPO3));

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
				resultado = new ArrayList<String>();

				logger.error("ERROR COACES:|"+sCodCOACES+"|");
				logger.error("ERROR Referencia:|"+sCodReferencia+"|");
				logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

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