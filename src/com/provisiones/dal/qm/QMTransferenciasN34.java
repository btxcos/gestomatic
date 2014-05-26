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
import com.provisiones.types.transferencias.N34.TransferenciaN34;

public class QMTransferenciasN34 
{

	private static Logger logger = LoggerFactory.getLogger(QMTransferenciasN34.class.getName());
	
	public static final String TABLA = "pp002_transferencias_n34_tbl";

	public static final String CAMPO1 = "transferencia_id";

	public static final String CAMPO2 = "codigo_ordenante";
	public static final String CAMPO3 = "referencia_beneficiario";
	public static final String CAMPO4 = "importe";
	public static final String CAMPO5 = "nuccen";
	public static final String CAMPO6 = "nuccof";
	public static final String CAMPO7 = "nuccdi";
	public static final String CAMPO8 = "nuccnt";
	public static final String CAMPO9 = "nombre_beneficiario";
	public static final String CAMPO10 = "domicilio1_beneficiario";
	public static final String CAMPO11 = "domicilio2_beneficiario";
	public static final String CAMPO12 = "plaza_beneficiario";
	public static final String CAMPO13 = "provincia_beneficiario";
	public static final String CAMPO14 = "concepto1_transferencia";
	public static final String CAMPO15 = "concepto2_transferencia";

	private QMTransferenciasN34(){}
	
	
	public static long addTransferencia(Connection conexion, TransferenciaN34 NuevaTransferencia)
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
					+ CAMPO10 + ","
					+ CAMPO11 + ","
					+ CAMPO12 + ","
					+ CAMPO13 + ","
					+ CAMPO14 + ","
					+ CAMPO15 +
					") VALUES ('" 
					+ NuevaTransferencia.getsCodOrdenante() + "','"
					+ NuevaTransferencia.getsReferenciaBeneficiario() + "','"
					+ NuevaTransferencia.getsImporte() + "',"
					+ "AES_ENCRYPT('"+NuevaTransferencia.getsNUCCEN()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ "AES_ENCRYPT('"+NuevaTransferencia.getsNUCCOF()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ "AES_ENCRYPT('"+NuevaTransferencia.getsNUCCDI()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ "AES_ENCRYPT('"+NuevaTransferencia.getsNUCCNT()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ "AES_ENCRYPT('"+NuevaTransferencia.getsNombreBeneficiario()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ "AES_ENCRYPT('"+NuevaTransferencia.getsDomicilio1Beneficiario()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ "AES_ENCRYPT('"+NuevaTransferencia.getsDomicilio2Beneficiario()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ "AES_ENCRYPT('"+NuevaTransferencia.getsPlazaBeneficiario()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ "AES_ENCRYPT('"+NuevaTransferencia.getsProvinciaBeneficiario()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ "AES_ENCRYPT('"+NuevaTransferencia.getsConcepto1Transferencia()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ "AES_ENCRYPT('"+NuevaTransferencia.getsConcepto2Transferencia()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")))";

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

				logger.error("ERROR TRANSFERENCIA:|"+NuevaTransferencia.getsReferenciaBeneficiario()+"|");
				logger.error("ERROR CONCEPTO:|"+NuevaTransferencia.getsConcepto1Transferencia()+"|");

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
	
	public static TransferenciaN34 getTransferencia(Connection conexion, long liTransferenciaID)
	{
		String sCodOrdenante = "";
		String sReferenciaBeneficiario = "";
		String sImporte = "";
		String sNUCCEN = "";
		String sNUCCOF = "";
		String sNUCCDI = "";
		String sNUCCNT = "";
		String sNombreBeneficiario = "";
		String sDomicilio1Beneficiario = "";
		String sDomicilio2Beneficiario = "";
		String sPlazaBeneficiario = "";
		String sProvinciaBeneficiario = "";
		String sConcepto1Transferencia = "";
		String sConcepto2Transferencia = "";
	


		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
						+ CAMPO2 + ","
						+ CAMPO3 + "," 
						+ CAMPO4 + ","
						+ "AES_DECRYPT("+CAMPO5 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
						+ "AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
						+ "AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
						+ "AES_DECRYPT("+CAMPO8 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
						+ "AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ," 
						+ "AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
						+ "AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
						+ "AES_DECRYPT("+CAMPO12 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
						+ "AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
						+ "AES_DECRYPT("+CAMPO14 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
						+ "AES_DECRYPT("+CAMPO15 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"+
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

						sCodOrdenante = rs.getString(CAMPO2);
						sReferenciaBeneficiario = rs.getString(CAMPO3);
						sImporte = rs.getString(CAMPO4);
						sNUCCEN = rs.getString("AES_DECRYPT("+CAMPO5 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sNUCCOF = rs.getString("AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sNUCCDI = rs.getString("AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sNUCCNT = rs.getString("AES_DECRYPT("+CAMPO8 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sNombreBeneficiario = rs.getString("AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sDomicilio1Beneficiario = rs.getString("AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sDomicilio2Beneficiario = rs.getString("AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sPlazaBeneficiario = rs.getString("AES_DECRYPT("+CAMPO12 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sProvinciaBeneficiario = rs.getString("AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sConcepto1Transferencia = rs.getString("AES_DECRYPT("+CAMPO14 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sConcepto2Transferencia = rs.getString("AES_DECRYPT("+CAMPO15 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");


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
				sCodOrdenante = "";
				sReferenciaBeneficiario = "";
				sImporte = "";
				sNUCCEN = "";
				sNUCCOF = "";
				sNUCCDI = "";
				sNUCCNT = "";
				sNombreBeneficiario = "";
				sDomicilio1Beneficiario = "";
				sDomicilio2Beneficiario = "";
				sPlazaBeneficiario = "";
				sProvinciaBeneficiario = "";
				sConcepto1Transferencia = "";
				sConcepto2Transferencia = "";

				logger.error("ERROR TRANSFERENCIA:|"+liTransferenciaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new TransferenciaN34(sCodOrdenante,
		sReferenciaBeneficiario,
		sImporte,
		sNUCCEN,
		sNUCCOF,
		sNUCCDI,
		sNUCCNT,
		sNombreBeneficiario,
		sDomicilio1Beneficiario,
		sDomicilio2Beneficiario,
		sPlazaBeneficiario,
		sProvinciaBeneficiario,
		sConcepto1Transferencia,
		sConcepto2Transferencia);
	}
	
}
