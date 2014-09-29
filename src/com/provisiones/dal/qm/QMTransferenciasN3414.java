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
import com.provisiones.types.transferencias.N3414.TransferenciaN3414;

public class QMTransferenciasN3414 
{
	private static Logger logger = LoggerFactory.getLogger(QMTransferenciasN3414.class.getName());
	
	public static final String TABLA = "pp002_transferencias_n3414_tbl";

	public static final String CAMPO1 = "transferencia_id";
	
	public static final String CAMPO2 = "cuenta_beneficiario";
	public static final String CAMPO3 = "importe_transferencia";
	public static final String CAMPO4 = "bic_beneficiario";
	public static final String CAMPO5 = "nombre_beneficiario";
	public static final String CAMPO6 = "direccion_beneficiario1";
	public static final String CAMPO7 = "direccion_beneficiario2";
	public static final String CAMPO8 = "direccion_beneficiario3";
	public static final String CAMPO9 = "pais_beneficiario";
	public static final String CAMPO10 = "concepto";

	private QMTransferenciasN3414(){}
	
	public static long addTransferencia(Connection conexion, TransferenciaN3414 NuevaTransferencia)
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
					+ CAMPO4 + ","
					+ CAMPO5 + ","
					+ CAMPO6 + ","
					+ CAMPO7 + ","
					+ CAMPO8 + ","
					+ CAMPO9 + ","
					+ CAMPO10 +
					") VALUES (" 
					+ "AES_ENCRYPT('"+NuevaTransferencia.getsCuentaBeneficiario()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ "'" + NuevaTransferencia.getsImporteTransferencia() + "',"
					+ "AES_ENCRYPT('"+NuevaTransferencia.getsBICBeneficiario()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ "AES_ENCRYPT('"+NuevaTransferencia.getsNombreBeneficiario()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ "AES_ENCRYPT('"+NuevaTransferencia.getsDireccionBeneficiario1()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ "AES_ENCRYPT('"+NuevaTransferencia.getsDireccionBeneficiario2()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ "AES_ENCRYPT('"+NuevaTransferencia.getsDireccionBeneficiario3()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ "AES_ENCRYPT('"+NuevaTransferencia.getsPaisBeneficiario()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ "AES_ENCRYPT('"+NuevaTransferencia.getsConcepto()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")))";

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

				logger.error("ERROR CONCEPTO:|"+NuevaTransferencia.getsConcepto()+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());	
			} 
			finally
			{

				Utils.closeStatement(stmt);
			}
		}

		return liCodigo;
	}
	
	public static boolean delTransferencia(Connection conexion, long liTransferenciaID) 
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '" + liTransferenciaID + "'";
			
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

				logger.error("ERROR Transferencia:|"+liTransferenciaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
			}
		}		

		return bSalida;
	}
	
	public static TransferenciaN3414 getTransferencia(Connection conexion, long liTransferenciaID)
	{
		String sCuentaBeneficiario = "";
		String sImporteTransferencia = "";
		String sBICBeneficiario = "";
		String sNombreBeneficiario = "";
		String sDireccionBeneficiario1 = "";
		String sDireccionBeneficiario2 = "";
		String sDireccionBeneficiario3 = "";
		String sPaisBeneficiario = "";
		String sConcepto = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
						+ "AES_DECRYPT("+CAMPO2 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
						+ CAMPO3 + "," 
						+ "AES_DECRYPT("+CAMPO4 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
						+ "AES_DECRYPT("+CAMPO5 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
						+ "AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
						+ "AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ," 
						+ "AES_DECRYPT("+CAMPO8 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
						+ "AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
						+ "AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"+
				       " FROM " 
				       + TABLA + 
				       " WHERE "
				       + CAMPO1 + " = '"+ liTransferenciaID +"'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");

				logger.debug(CAMPO1 + ":|"+liTransferenciaID+"|");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;

						sCuentaBeneficiario = rs.getString("AES_DECRYPT("+CAMPO2 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sImporteTransferencia = rs.getString(CAMPO3);
						sBICBeneficiario = rs.getString("AES_DECRYPT("+CAMPO4 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sNombreBeneficiario = rs.getString("AES_DECRYPT("+CAMPO5 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sDireccionBeneficiario1 = rs.getString("AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sDireccionBeneficiario2 = rs.getString("AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sDireccionBeneficiario3 = rs.getString("AES_DECRYPT("+CAMPO8 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sPaisBeneficiario = rs.getString("AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sConcepto = rs.getString("AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");

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
				sCuentaBeneficiario = "";
				sImporteTransferencia = "";
				sBICBeneficiario = "";
				sNombreBeneficiario = "";
				sDireccionBeneficiario1 = "";
				sDireccionBeneficiario2 = "";
				sDireccionBeneficiario3 = "";
				sPaisBeneficiario = "";
				sConcepto = "";

				logger.error("ERROR TRANSFERENCIA:|"+liTransferenciaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new TransferenciaN3414(
				sCuentaBeneficiario,
				sImporteTransferencia,
				sBICBeneficiario,
				sNombreBeneficiario,
				sDireccionBeneficiario1,
				sDireccionBeneficiario2,
				sDireccionBeneficiario3,
				sPaisBeneficiario,
				sConcepto);
	}
}
