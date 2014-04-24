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
	public static final String CAMPO7 = "recargo_adicional";
	public static final String CAMPO8 = "enviado";
	public static final String CAMPO9 = "usuario_pago";
	public static final String CAMPO10 = "fecha_pago";
	public static final String CAMPO11 = "nota";


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
					+ CAMPO11 +
					") VALUES ('"
					+ NuevoPago.getsCOACES() + "','"
					+ NuevoPago.getsGasto() + "','"
					+ NuevoPago.getsTipoPago() + "','"
					+ NuevoPago.getsCodOperacion() + "','"
					+ NuevoPago.getsFEPGPR() + "','"
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
					+ CAMPO7 + " = '" + pago.getsRecargoAdicional() + "', "
					+ CAMPO8 + " = " + btEnviado + ", " 
					+ CAMPO9 + " = '" + sUsuario + "', " 
					+ CAMPO10 + " = '" + Utils.timeStamp() + "' " +					
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
				       + CAMPO7
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
						sRecargoAdicional = rs.getString(CAMPO7);

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
	
	public static boolean setNota(Connection conexion, String sNUPROF, String sNota)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					//+ CAMPO11 + " = '"+ sNota +"' "+
					+ CAMPO11 + " = AES_ENCRYPT('"+sNota+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) "+
					" WHERE "
					+ CAMPO1 + " = '"+ sNUPROF +"'";
			
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

				logger.error("ERROR PROVISION:|"+sNUPROF+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

			} 
			finally 
			{

				Utils.closeStatement(stmt);
			}			
		}

		return bSalida;
	}
	
	public static String getNota(Connection conexion, String sNUPROF)
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
						//+ CAMPO11 +
						+"AES_DECRYPT("+CAMPO11+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"+
						" FROM " 
						+ TABLA + 
						" WHERE "
						+ CAMPO1 + " = '"+ sNUPROF +"'";
			
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

						//sNota = rs.getString(CAMPO19);
						
						sNota = rs.getString("AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						
						logger.debug(CAMPO1+":|"+sNUPROF+"|");
						
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
				
				logger.error("ERROR PROVISION:|"+sNUPROF+"|");

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
					+ CAMPO8 + " = "+ btEnviado +
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
				       	+ CAMPO8  +                
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
					+ CAMPO8 + " = "+ btEnviado + "))";
			
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
					+ CAMPO8 + " = "+ btEnviado + 
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
	
	public static ArrayList<Long> buscarPagosEmitidosProvision(Connection conexion, String sNUPROF) 
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
					+ CAMPO8 + " = "+ ValoresDefecto.PAGO_EMITIDO + " AND "
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
					+ CAMPO8 + " = "+ValoresDefecto.PAGO_EMITIDO+
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
}
