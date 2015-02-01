package com.provisiones.dal.qm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.listas.QMListaGastosProvisiones;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Pago;
import com.provisiones.types.tablas.ActivoTabla;

public class QMPagos
{

	private static Logger logger = LoggerFactory.getLogger(QMPagos.class.getName());
	
	public static final String TABLA = "pp002_pagos_tbl";

	public static final String CAMPO1 = "pago_id";

	public static final String CAMPO2 = "cod_coaces";
	public static final String CAMPO3 = "cod_gasto";
	public static final String CAMPO4 = "cod_tipo_pago";
	public static final String CAMPO5 = "cod_operacion";
	public static final String CAMPO6 = "fepgpr";
	public static final String CAMPO7 = "fecha_pago_real";
	public static final String CAMPO8 = "recargo_adicional";
	public static final String CAMPO9 = "enviado";
	public static final String CAMPO10 = "usuario_pago";
	public static final String CAMPO11 = "fecha_pago";
	public static final String CAMPO12 = "nota";
	public static final String CAMPO13 = "archivo_envio";


	private QMPagos(){}
	
	public static long addPago(Connection conexion, Pago NuevoPago, byte btEnviado)
	{
		long liCodigo = 0;

		if (conexion != null)
		{
			String sUsuario = ConnectionManager.getUser();
			
			Statement stmt = null;

			ResultSet resulset = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "INSERT INTO " 
					+ TABLA + 
					" ("
					+ CAMPO2 + ","
					+ CAMPO3 + "," 
					+ CAMPO4 + ","
					+ CAMPO5 + ","
					+ CAMPO6 + ","
					+ CAMPO7 + ","
					+ CAMPO8 + ","
					+ CAMPO9 + ","
					+ CAMPO10 + ","
					+ CAMPO11 + ","
					+ CAMPO12 +
					") VALUES ('"
					+ NuevoPago.getsCOACES() + "','"
					+ NuevoPago.getsGasto() + "','"
					+ NuevoPago.getsTipoPago() + "','"
					+ NuevoPago.getsCodOperacion() + "','"
					+ NuevoPago.getsFEPGPR() + "','"
					+ NuevoPago.getsFechaPagoReal() + "','"
					+ NuevoPago.getsRecargoAdicional() + "',"
					+ btEnviado + ",'"
					+ sUsuario + "','"
					+ Utils.timeStamp() + "',"
					+ "AES_ENCRYPT('"+ValoresDefecto.CAMPO_ALFA_SIN_INFORMAR+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))" + 
				    ")";

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
				
				logger.debug("Ejecutada con exito!");
			}
			catch (SQLException ex) 
			{
				liCodigo = 0;

				logger.error("ERROR GASTO:|"+NuevoPago.getsGasto()+"|");
				logger.error("ERROR FECHA:|"+NuevoPago.getsFEPGPR()+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());	
			} 
			finally
			{

				Utils.closeStatement(stmt);
			}
		}

		return liCodigo;
	}

	public static boolean modPago(Connection conexion, Pago pago, long liPagoID, byte btEnviado) 
	{
		boolean bSalida = false;
		
		String sUsuario = ConnectionManager.getUser();

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET "
					+ CAMPO2 + " = '" + pago.getsCOACES() + "', "
					+ CAMPO3 + " = '" + pago.getsGasto() + "', "
					+ CAMPO4 + " = '" + pago.getsTipoPago() + "', "
					+ CAMPO5 + " = '" + pago.getsCodOperacion() + "', " 
					+ CAMPO6 + " = '" + pago.getsFEPGPR() + "', "
					+ CAMPO7 + " = '" + pago.getsFechaPagoReal() + "', "
					+ CAMPO8 + " = '" + pago.getsRecargoAdicional() + "', "
					+ CAMPO9 + " = " + btEnviado + ", " 
					+ CAMPO10 + " = '" + sUsuario + "', " 
					+ CAMPO11 + " = '" + Utils.timeStamp() + "' " +					
					" WHERE " 
					+ CAMPO1 + " = '" + liPagoID + "'";


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

				logger.error("ERROR PAGO:|"+liPagoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean delPago(Connection conexion, long liPagoID) 
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '" + liPagoID + "'";
			
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

				logger.error("ERROR PAGO:|"+liPagoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
			}
		}		

		return bSalida;
	}
	
	public static Pago getPago(Connection conexion, long liPagoID)
	{
		String sCOACES = "";
		String sGasto = "";
		String sTipoPago = "";
		String sCodOperacion = "";
		String sFEPGPR = "";
		String sFechaPagoReal = "";
		String sRecargoAdicional = "";

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
				       + CAMPO8
				       +" FROM " 
				       + TABLA + 
				       " WHERE "
				       + CAMPO1 + " = '"+ liPagoID +"'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");

				logger.debug(CAMPO1 + ":|"+liPagoID+"|");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;

						sCOACES = rs.getString(CAMPO2);
						sGasto = rs.getString(CAMPO3);  
						sTipoPago = rs.getString(CAMPO4);
						sCodOperacion = rs.getString(CAMPO5);
						sFEPGPR = rs.getString(CAMPO6);
						sFechaPagoReal = rs.getString(CAMPO7);
						sRecargoAdicional = rs.getString(CAMPO8);

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
				sCOACES = "";
				sGasto = "";
				sTipoPago = "";
				sCodOperacion = "";
				sFEPGPR = "";
				sFechaPagoReal = "";
				sRecargoAdicional = "";

				logger.error("ERROR PAGO:|"+liPagoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new Pago(
				sCOACES,
				sGasto,
				sTipoPago,
				sCodOperacion,
				sFEPGPR,
				sFechaPagoReal,
				sRecargoAdicional);
	}
	
	public static Pago getPagoGasto(Connection conexion, long liCodGasto)
	{
		String sCOACES = "";
		String sGasto = "";
		String sTipoPago = "";
		String sCodOperacion = "";
		String sFEPGPR = "";
		String sFechaPagoReal = "";
		String sRecargoAdicional = "";

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
				       + CAMPO8
				       +" FROM " 
				       + TABLA + 
				       " WHERE "
				       + CAMPO3 + " = '"+ liCodGasto +"'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");

				logger.debug(CAMPO3 + ":|"+liCodGasto+"|");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;

						sCOACES = rs.getString(CAMPO2);
						sGasto = rs.getString(CAMPO3);  
						sTipoPago = rs.getString(CAMPO4);
						sCodOperacion = rs.getString(CAMPO5);
						sFEPGPR = rs.getString(CAMPO6);
						sFechaPagoReal = rs.getString(CAMPO7);
						sRecargoAdicional = rs.getString(CAMPO8);

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
				sCOACES = "";
				sGasto = "";
				sTipoPago = "";
				sCodOperacion = "";
				sFEPGPR = "";
				sFechaPagoReal = "";
				sRecargoAdicional = "";

				logger.error("ERROR GASTO:|"+liCodGasto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new Pago(
				sCOACES,
				sGasto,
				sTipoPago,
				sCodOperacion,
				sFEPGPR,
				sFechaPagoReal,
				sRecargoAdicional);
	}

	public static long getPagoID(Connection conexion, long liCodGasto)
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
				       	+ CAMPO3 + " = '"+ liCodGasto +"'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");
				
				logger.debug(CAMPO3 + ":|"+liCodGasto+"|");

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
				
				logger.error("ERROR PAGO:|"+liCodGasto+"|");

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
	
	public static boolean setNota(Connection conexion, long liPagoID, String sNota)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					//+ CAMPO12 + " = '"+ sNota +"' "+
					+ CAMPO12 + " = AES_ENCRYPT('"+sNota+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) "+
					" WHERE "
					+ CAMPO1 + " = '"+ liPagoID +"'";
			
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

				logger.error("ERROR PAGO:|"+liPagoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

			} 
			finally 
			{

				Utils.closeStatement(stmt);
			}			
		}

		return bSalida;
	}
	
	public static String getNota(Connection conexion, long liPagoID)
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
						//+ CAMPO12 +
						+"CONVERT(AES_DECRYPT("+CAMPO12+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)"+
						" FROM " 
						+ TABLA + 
						" WHERE "
						+ CAMPO1 + " = '"+ liPagoID +"'";
			
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

						//sNota = rs.getString(CAMPO12);
						
						sNota = rs.getString("CONVERT(AES_DECRYPT("+CAMPO12 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						
						logger.debug(CAMPO1+":|"+liPagoID+"|");
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO12+":|"+sNota+"|");
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
				
				logger.error("ERROR PAGO:|"+liPagoID+"|");

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
	
	public static boolean setEnviado(Connection conexion, long liPagoID, byte btEnviado)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO9 + " = "+ btEnviado +
					" WHERE "
					+ CAMPO1  + " = '"+ liPagoID +"'";
			
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

				logger.error("ERROR PAGO:|"+liPagoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}
		
		return bSalida;
	}
	
	public static boolean getEnviado(Connection conexion, long liPagoID)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
				       	+ CAMPO9  +                
				       	" FROM " 
				       	+ TABLA + 
				       	" WHERE "
				       	+ CAMPO3 + " = '"+ liPagoID +"' AND "
				       	+ CAMPO9 + " = "+ ValoresDefecto.PAGO_ENVIADO;
			
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
				
				logger.error("ERROR PAGO:|"+liPagoID+"|");

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
	
	public static String getTipoPago(Connection conexion, long liCodGasto)
	{
		String sTipoPago = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
						+ CAMPO4+
						" FROM " 
						+ TABLA + 
						" WHERE "
						+ CAMPO3 + " = '"+ liCodGasto +"'";
			
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

						sTipoPago = rs.getString(CAMPO4);
						
						logger.debug(CAMPO3+":|"+liCodGasto+"|");
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO4+":|"+sTipoPago+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sTipoPago = "";
				
				logger.error("ERROR GASTO PAGADO:|"+liCodGasto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sTipoPago;
	}
	
	public static boolean existePago(Connection conexion, long liCodGasto)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
				       	+ CAMPO9  +                
				       	" FROM " 
				       	+ TABLA + 
				       	" WHERE "
				       	+ CAMPO3 + " = '"+ liCodGasto +"'";
			
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
				
				logger.error("ERROR GASTO:|"+liCodGasto+"|");

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
	
	public static String getFechaPago(Connection conexion, long liPagoID) 
	{
		String sFechaPago = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO6  +
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '"+ liPagoID + "'";
			
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
						
						sFechaPago = rs.getString(CAMPO6);

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+liPagoID+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sFechaPago = "";

				logger.error("ERROR PAGO:|"+liPagoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}		

		return sFechaPago;
	}
	
	public static String getCodOperacion(Connection conexion, long liPagoID) 
	{
		String sCodOperacion = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO5  +
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '"+ liPagoID + "'";
			
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
						
						sCodOperacion = rs.getString(CAMPO5);

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+liPagoID+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sCodOperacion = "";

				logger.error("ERROR PAGO:|"+liPagoID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}		

		return sCodOperacion;
	}
	
	public static ArrayList<Integer> buscarActivosPagoEnvio(Connection conexion, byte btEnviado) 
	{
		ArrayList<Integer> resultado = new ArrayList<Integer>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ QMGastos.CAMPO2  +
					" FROM " 
					+ QMGastos.TABLA + 
					" WHERE " 
					+ QMGastos.CAMPO1 + " IN (SELECT "
					+ CAMPO3  +
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO9 + " = "+ btEnviado + "))";
			
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
						
						int iCOACES = rs.getInt(QMGastos.CAMPO2);
						
						resultado.add(iCOACES);
						
						logger.debug("Encontrado el registro!");
						logger.debug(QMGastos.CAMPO2+":|"+iCOACES+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<Integer>();

				logger.error("ERROR ENVIADO:|"+btEnviado+"|");

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
	
	public static ArrayList<Long> buscarPagoEnvio(Connection conexion, byte btEnviado) 
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
					+ CAMPO1  +
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO9 + " = "+ btEnviado + 
					" ORDER BY " + CAMPO2 + " ASC";
			
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
						
						long liPagoID = rs.getLong(CAMPO1);
						
						resultado.add(liPagoID);
						
						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+liPagoID+"|");
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

				logger.error("ERROR ENVIADO:|"+btEnviado+"|");

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
	
	public static ArrayList<Long> buscarPagosEmitidosProvision(Connection conexion, String sNUPROF, String sTipoPago) 
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
					+ CAMPO1  +
					" FROM " 
					+ TABLA + 
					" WHERE ("
					+ CAMPO4 + " = '"+ sTipoPago + "' AND "
					+ CAMPO9 + " = "+ ValoresDefecto.PAGO_EMITIDO + " AND "
					+ CAMPO3 + " IN (SELECT "
					+ QMListaGastosProvisiones.CAMPO1 +
					" FROM " 
					+ QMListaGastosProvisiones.TABLA + 
					" WHERE "
					+ QMListaGastosProvisiones.CAMPO2 + " = '"+ sNUPROF +"'))"+
					" ORDER BY " + CAMPO2 + " ASC";
			
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
						
						long liPagoID = rs.getLong(CAMPO1);
						
						resultado.add(liPagoID);
						
						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+liPagoID+"|");
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

				logger.error("ERROR PROVISION:|"+sNUPROF+"|");

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
	
	public static long buscaCantidadPagosSinEnviarPorTipo(Connection conexion, String sTipoPago)
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
					" WHERE " +
					"("
					+ CAMPO4 + " = '"+sTipoPago+"' AND "
					+ CAMPO9 + " = "+ValoresDefecto.PAGO_EMITIDO+
					")";
			
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

						logger.debug("Numero de registros:|"+liNumero+"|");
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
	
	public static ArrayList<ActivoTabla> buscaActivosConPagosPorFiltro(Connection conexion, ActivoTabla filtro)
	{
		ArrayList<ActivoTabla> resultado = new ArrayList<ActivoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String sCondicionCOPOIN = filtro.getCOPOIN().isEmpty()?"":QMActivos.CAMPO14 + " LIKE '%" + filtro.getCOPOIN()	+ "%' AND ";
			//String sCondicionNOMUIN = filtro.getNOMUIN().isEmpty()?"":QMActivos.CAMPO11 + " LIKE '%" + filtro.getNOMUIN()	+ "%' AND ";
			//String sCondicionNOPRAC = filtro.getNOPRAC().isEmpty()?"":QMActivos.CAMPO13 + " LIKE '%" + filtro.getNOPRAC()	+ "%' AND ";
			//String sCondicionNOVIAS = filtro.getNOVIAS().isEmpty()?"":QMActivos.CAMPO6 + " LIKE '%" + filtro.getNOVIAS()	+ "%' AND ";
			//String sCondicionNUPIAC = filtro.getNUPIAC().isEmpty()?"":QMActivos.CAMPO9 + " LIKE '%" + filtro.getNUPIAC()	+ "%' AND ";
			//String sCondicionNUPOAC = filtro.getNUPOAC().isEmpty()?"":QMActivos.CAMPO7 + " LIKE '%" + filtro.getNUPOAC()	+ "%' AND ";
			//String sCondicionNUPUAC = filtro.getNUPUAC().isEmpty()?"":QMActivos.CAMPO10 + " LIKE '%" + filtro.getNUPUAC()	+ "%' AND ";
			//String sCondicionNUFIRE = filtro.getNUFIRE().isEmpty()?"":QMActivos.CAMPO28 + " LIKE '%" + filtro.getNUFIRE()	+ "%' AND ";

			String sCondicionNOMUIN = filtro.getNOMUIN().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOMUIN() + "%' AND ";
			String sCondicionNOPRAC = filtro.getNOPRAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOPRAC() + "%' AND ";
			String sCondicionNOVIAS = filtro.getNOVIAS().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOVIAS() + "%' AND ";
			String sCondicionNUPIAC = filtro.getNUPIAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPIAC() + "%' AND ";
			String sCondicionNUPOAC = filtro.getNUPOAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPOAC() + "%' AND ";
			String sCondicionNUPUAC = filtro.getNUPUAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPUAC() + "%' AND ";
			String sCondicionNUFIRE = filtro.getNUFIRE().isEmpty()?"":"CONVERT(AES_DECRYPT("+QMActivos.CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUFIRE() + "%' AND ";
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						
						   + QMActivos.CAMPO1 + ","        
						   + QMActivos.CAMPO14 + ","
						   //+ QMActivos.CAMPO11 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO13 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO6 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO9 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO7 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO10 + ","
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ QMActivos.CAMPO28 +
						   + "CONVERT(AES_DECRYPT("+QMActivos.CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) "+

						   " FROM " 
						   + QMActivos.TABLA + 
						   " WHERE ("
						   + sCondicionCOPOIN  
						   + sCondicionNOMUIN
						   + sCondicionNOPRAC  
						   + sCondicionNOVIAS  
						   + sCondicionNUPIAC
						   + sCondicionNUPOAC 
						   + sCondicionNUPUAC
						   + sCondicionNUFIRE
						   + QMActivos.CAMPO1 +" IN (SELECT "
						   + CAMPO2 + 
	   					   " FROM " 
						   + TABLA +"))";
			
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
						
						String sCOACES = rs.getString(QMActivos.CAMPO1);
						String sCOPOIN = rs.getString(QMActivos.CAMPO14);

						//String sNOMUIN = rs.getString(QMActivos.CAMPO11);
						String sNOMUIN = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNOPRAC = rs.getString(QMActivos.CAMPO13);
						String sNOPRAC = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNOVIAS = rs.getString(QMActivos.CAMPO6);
						String sNOVIAS = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPIAC = rs.getString(QMActivos.CAMPO9);
						String sNUPIAC = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPOAC = rs.getString(QMActivos.CAMPO7);
						String sNUPOAC = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPUAC = rs.getString(QMActivos.CAMPO10);
						String sNUPUAC = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUFIRE = rs.getString(QMActivos.CAMPO28);
						String sNUFIRE = rs.getString("CONVERT(AES_DECRYPT("+QMActivos.CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						
						ActivoTabla activoencontrado = new ActivoTabla(
								sCOACES, 
								sCOPOIN, 
								sNOMUIN, 
								sNOPRAC, 
								sNOVIAS, 
								sNUPIAC, 
								sNUPOAC, 
								sNUPUAC,
								sNUFIRE,
								"");
						
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
