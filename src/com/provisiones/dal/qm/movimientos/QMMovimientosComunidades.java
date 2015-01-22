package com.provisiones.dal.qm.movimientos;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.movimientos.MovimientoComunidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class QMMovimientosComunidades
{
	private static Logger logger = LoggerFactory.getLogger(QMMovimientosComunidades.class.getName());

	public static final String TABLA = "pp002_e1_movimientos_tbl";

	public static final String CAMPO1 = "e1_movimiento_id";
	
	public static final String CAMPO2 = "cod_codtrn";
	public static final String CAMPO3 = "cod_cotdor";
	public static final String CAMPO4 = "idprov";
	public static final String CAMPO5 = "cod_coacci";
	public static final String CAMPO6 = "coengp";
	public static final String CAMPO7 = "cod_cocldo";
	public static final String CAMPO8 = "cod_nudcom";
	public static final String CAMPO9 = "cod_bitc10";
	public static final String CAMPO10 = "cod_coaces";
	public static final String CAMPO11 = "cod_bitc01";
	public static final String CAMPO12 = "nomcoc";
	public static final String CAMPO13 = "cod_bitc02";
	public static final String CAMPO14 = "nodcco";
	public static final String CAMPO15 = "cod_bitc03";
	public static final String CAMPO16 = "nomprc";
	public static final String CAMPO17 = "cod_bitc04";
	public static final String CAMPO18 = "nutprc";
	public static final String CAMPO19 = "cod_bitc05";
	public static final String CAMPO20 = "nomadc";
	public static final String CAMPO21 = "cod_bitc06";
	public static final String CAMPO22 = "nutadc";
	public static final String CAMPO23 = "cod_bitc07";
	public static final String CAMPO24 = "nodcad";
	public static final String CAMPO25 = "cod_bitc08";
	public static final String CAMPO26 = "nuccen";
	public static final String CAMPO27 = "nuccof";
	public static final String CAMPO28 = "nuccdi";
	public static final String CAMPO29 = "nuccnt";
	public static final String CAMPO30 = "cod_bitc09";
	public static final String CAMPO31 = "obtexc";
	public static final String CAMPO32 = "obdeer";         

	//Campos de control
	public static final String CAMPO33  = "usuario_movimiento";
	public static final String CAMPO34  = "fecha_movimiento";
	
	private QMMovimientosComunidades(){}
	
	public static long addMovimientoComunidad(Connection conexion, MovimientoComunidad NuevoMovimientoComunidad)
	{
		long liCodigo = 0;

		if (conexion != null)
		{
			Statement stmt = null;
			ResultSet resulset = null;
			
			String sUsuario = ConnectionManager.getUser();
			
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
				       + CAMPO10 + ","              
				       + CAMPO11 + ","              
				       + CAMPO12 + ","              
				       + CAMPO13 + ","              
				       + CAMPO14 + ","              
				       + CAMPO15 + ","              
				       + CAMPO16 + ","              
				       + CAMPO17 + ","              
				       + CAMPO18 + ","              
				       + CAMPO19 + ","              
				       + CAMPO20 + ","              
				       + CAMPO21 + ","              
				       + CAMPO22 + ","              
				       + CAMPO23 + ","              
				       + CAMPO24 + ","              
				       + CAMPO25 + ","              
				       + CAMPO26 + ","              
				       + CAMPO27 + ","              
				       + CAMPO28 + ","              
				       + CAMPO29 + ","
				       + CAMPO30 + ","
				       + CAMPO31 + ","
				       + CAMPO32 + ","
				       + CAMPO33 + ","
				       + CAMPO34 +               
				       ") VALUES ('" 
				       
				       
				       				       
				       
				       + NuevoMovimientoComunidad.getCODTRN() + "','" 
				       + NuevoMovimientoComunidad.getCOTDOR() + "','"
				       + NuevoMovimientoComunidad.getIDPROV() + "','"
				       + NuevoMovimientoComunidad.getCOACCI() + "','"
				       + NuevoMovimientoComunidad.getCOENGP() + "','"
				       + NuevoMovimientoComunidad.getCOCLDO() + "','"
				       + NuevoMovimientoComunidad.getNUDCOM() + "','"
				       + NuevoMovimientoComunidad.getBITC10() + "','"
				       + NuevoMovimientoComunidad.getCOACES() + "','"
				       + NuevoMovimientoComunidad.getBITC01() + "',"
				       //+ NuevoMovimientoComunidad.getNOMCOC() + "','"
				       + "AES_ENCRYPT('"+NuevoMovimientoComunidad.getNOMCOC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'"
				       + NuevoMovimientoComunidad.getBITC02() + "',"
				       //+ NuevoMovimientoComunidad.getNODCCO() + "',"
				       + "AES_ENCRYPT('"+NuevoMovimientoComunidad.getNODCCO()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'"
				       + NuevoMovimientoComunidad.getBITC03() + "',"
				       //+ NuevoMovimientoComunidad.getNOMPRC() + "','"
				       + "AES_ENCRYPT('"+NuevoMovimientoComunidad.getNOMPRC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'"
				       + NuevoMovimientoComunidad.getBITC04() + "',"
				       //+ NuevoMovimientoComunidad.getNUTPRC() + "','"
				       + "AES_ENCRYPT('"+NuevoMovimientoComunidad.getNUTPRC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'"
				       + NuevoMovimientoComunidad.getBITC05() + "',"
				       //+ NuevoMovimientoComunidad.getNOMADC() + "','"
				       + "AES_ENCRYPT('"+NuevoMovimientoComunidad.getNOMADC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'"
				       + NuevoMovimientoComunidad.getBITC06() + "',"
				       //+ NuevoMovimientoComunidad.getNUTADC() + "','"
				       + "AES_ENCRYPT('"+NuevoMovimientoComunidad.getNUTADC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'"
				       + NuevoMovimientoComunidad.getBITC07() + "',"
				       //+ NuevoMovimientoComunidad.getNODCAD() + "','"
				       + "AES_ENCRYPT('"+NuevoMovimientoComunidad.getNODCAD()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'"
				       + NuevoMovimientoComunidad.getBITC08() + "',"
				       //+ NuevoMovimientoComunidad.getNUCCEN() + "','"
				       + "AES_ENCRYPT('"+NuevoMovimientoComunidad.getNUCCEN()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       //+ NuevoMovimientoComunidad.getNUCCOF() + "','"
				       + "AES_ENCRYPT('"+NuevoMovimientoComunidad.getNUCCOF()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       //+ NuevoMovimientoComunidad.getNUCCDI() + "','"
				       + "AES_ENCRYPT('"+NuevoMovimientoComunidad.getNUCCDI()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       //+ NuevoMovimientoComunidad.getNUCCNT() + "','"
				       + "AES_ENCRYPT('"+NuevoMovimientoComunidad.getNUCCNT()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'"
				       + NuevoMovimientoComunidad.getBITC09() + "','"
				       + NuevoMovimientoComunidad.getOBTEXC() + "','"
				       + NuevoMovimientoComunidad.getOBDEER() + "','"
				       + sUsuario + "','"
				       + Utils.timeStamp() +
				       "' )";
			
			
			 
			 
			 
			

			
			logger.debug(sQuery);

			try 
			{

				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery, Statement.RETURN_GENERATED_KEYS);
				
				resulset = stmt.getGeneratedKeys();
				
				logger.debug("Ejecutada con exito!");
				
				if (resulset.next()) 
				{
					liCodigo= resulset.getLong(1);
				} 
			} 
			catch (SQLException ex) 
			{
				liCodigo = 0;

				logger.error("ERROR COCLDO:|"+NuevoMovimientoComunidad.getCOCLDO()+"|");
				logger.error("ERROR NUDCOM:|"+NuevoMovimientoComunidad.getNUDCOM()+"|");
				logger.error("ERROR COACES:|"+NuevoMovimientoComunidad.getCOACES()+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
				Utils.closeResultSet(resulset);
			}	
		}

		logger.debug("iCodigo: |" + liCodigo +"|");

		return liCodigo;
	}
	public static boolean modMovimientoComunidad(Connection conexion, MovimientoComunidad NuevoMovimientoComunidad, long liMovimientoComunidadID)
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					
					
					
					+ CAMPO2  + " = '"+ NuevoMovimientoComunidad.getCODTRN() + "', " 
					+ CAMPO3  + " = '"+ NuevoMovimientoComunidad.getCOTDOR() + "', " 
					+ CAMPO4  + " = '"+ NuevoMovimientoComunidad.getIDPROV() + "', " 
					+ CAMPO5  + " = '"+ NuevoMovimientoComunidad.getCOACCI() + "', " 
					+ CAMPO6  + " = '"+ NuevoMovimientoComunidad.getCOENGP() + "', " 
					+ CAMPO7  + " = '"+ NuevoMovimientoComunidad.getCOCLDO() + "', " 
					+ CAMPO8  + " = '"+ NuevoMovimientoComunidad.getNUDCOM() + "', " 
					+ CAMPO9  + " = '"+ NuevoMovimientoComunidad.getBITC10() + "', " 
					+ CAMPO10 + " = '"+ NuevoMovimientoComunidad.getCOACES() + "', " 
					+ CAMPO11 + " = '"+ NuevoMovimientoComunidad.getBITC01() + "', " 
					/*+ CAMPO12 + " = '"+ NuevoMovimientoComunidad.getNOMCOC() + "', " 
					+ CAMPO13 + " = '"+ NuevoMovimientoComunidad.getBITC02() + "', " 
					+ CAMPO14 + " = '"+ NuevoMovimientoComunidad.getNODCCO() + "', " 
					+ CAMPO15 + " = '"+ NuevoMovimientoComunidad.getBITC03() + "', " 
					+ CAMPO16 + " = '"+ NuevoMovimientoComunidad.getNOMPRC() + "', " 
					+ CAMPO17 + " = '"+ NuevoMovimientoComunidad.getBITC04() + "', " 
					+ CAMPO18 + " = '"+ NuevoMovimientoComunidad.getNUTPRC() + "', " 
					+ CAMPO19 + " = '"+ NuevoMovimientoComunidad.getBITC05() + "', " 
					+ CAMPO20 + " = '"+ NuevoMovimientoComunidad.getNOMADC() + "', " 
					+ CAMPO21 + " = '"+ NuevoMovimientoComunidad.getBITC06() + "', " 
					+ CAMPO22 + " = '"+ NuevoMovimientoComunidad.getNUTADC() + "', " 
					+ CAMPO23 + " = '"+ NuevoMovimientoComunidad.getBITC07() + "', " 
					+ CAMPO24 + " = '"+ NuevoMovimientoComunidad.getNODCAD() + "', " 
					+ CAMPO25 + " = '"+ NuevoMovimientoComunidad.getBITC08() + "', " 
					+ CAMPO26 + " = '"+ NuevoMovimientoComunidad.getNUCCEN() + "', " 
					+ CAMPO27 + " = '"+ NuevoMovimientoComunidad.getNUCCOF() + "', " 
					+ CAMPO28 + " = '"+ NuevoMovimientoComunidad.getNUCCDI() + "', " 
					+ CAMPO29 + " = '"+ NuevoMovimientoComunidad.getNUCCNT() + "', " */
					+ CAMPO12 + " = AES_ENCRYPT('"+ NuevoMovimientoComunidad.getNOMCOC() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), " 
					+ CAMPO13 + " = '"+ NuevoMovimientoComunidad.getBITC02() + "', " 
					+ CAMPO14 + " = AES_ENCRYPT('"+ NuevoMovimientoComunidad.getNODCCO() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), "
					+ CAMPO15 + " = '"+ NuevoMovimientoComunidad.getBITC03() + "', " 
					+ CAMPO16 + " = AES_ENCRYPT('"+ NuevoMovimientoComunidad.getNOMPRC() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), "
					+ CAMPO17 + " = '"+ NuevoMovimientoComunidad.getBITC04() + "', " 
					+ CAMPO18 + " = AES_ENCRYPT('"+ NuevoMovimientoComunidad.getNUTPRC() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), "
					+ CAMPO19 + " = '"+ NuevoMovimientoComunidad.getBITC05() + "', " 
					+ CAMPO20 + " = AES_ENCRYPT('"+ NuevoMovimientoComunidad.getNOMADC() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), "
					+ CAMPO21 + " = '"+ NuevoMovimientoComunidad.getBITC06() + "', " 
					+ CAMPO22 + " = AES_ENCRYPT('"+ NuevoMovimientoComunidad.getNUTADC() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), "
					+ CAMPO23 + " = '"+ NuevoMovimientoComunidad.getBITC07() + "', " 
					+ CAMPO24 + " = AES_ENCRYPT('"+ NuevoMovimientoComunidad.getNODCAD() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), "
					+ CAMPO25 + " = '"+ NuevoMovimientoComunidad.getBITC08() + "', " 
					+ CAMPO26 + " = AES_ENCRYPT('"+ NuevoMovimientoComunidad.getNUCCEN() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), " 
					+ CAMPO27 + " = AES_ENCRYPT('"+ NuevoMovimientoComunidad.getNUCCOF() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), " 
					+ CAMPO28 + " = AES_ENCRYPT('"+ NuevoMovimientoComunidad.getNUCCDI() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), " 
					+ CAMPO29 + " = AES_ENCRYPT('"+ NuevoMovimientoComunidad.getNUCCNT() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), " 
					
					+ CAMPO30 + " = '"+ NuevoMovimientoComunidad.getBITC09() + "', " 
					+ CAMPO31 + " = '"+ NuevoMovimientoComunidad.getOBTEXC() + "', " 
					+ CAMPO32 + " = '"+ NuevoMovimientoComunidad.getOBDEER() +
					"' "+
					" WHERE "
					+ CAMPO1 + " = '"+ liMovimientoComunidadID +"'";
			
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
				
				logger.error("ERROR MovimientoComunidadID:|"+liMovimientoComunidadID+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}	
		}

		return bSalida;
	}

	public static boolean delMovimientoComunidad(Connection conexion, long liMovimientoComunidadID)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO1 + " = '" + liMovimientoComunidadID + "'";
			
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

				logger.error("ERROR MovimientoComunidadID:|"+liMovimientoComunidadID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static MovimientoComunidad getMovimientoComunidad(Connection conexion, long liMovimientoComunidadID)
	{
		String sCODTRN = "";
		String sCOTDOR = "";
		String sIDPROV = "";
		String sCOACCI = "";
		String sCOENGP = "";
		String sCOCLDO = "";
		String sNUDCOM = "";
		String sBITC10 = "";
		String sCOACES = "";
		String sBITC01 = "";
		String sNOMCOC = "";
		String sBITC02 = "";
		String sNODCCO = "";
		String sBITC03 = "";
		String sNOMPRC = "";
		String sBITC04 = "";
		String sNUTPRC = "";
		String sBITC05 = "";
		String sNOMADC = "";
		String sBITC06 = "";
		String sNUTADC = "";
		String sBITC07 = "";
		String sNODCAD = "";
		String sBITC08 = "";
		String sNUCCEN = "";
		String sNUCCOF = "";
		String sNUCCDI = "";
		String sNUCCNT = "";
		String sBITC09 = "";
		String sOBTEXC = "";
		String sOBDEER = "";

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
				       + CAMPO8  + ","              
				       + CAMPO9  + ","              
				       + CAMPO10 + ","              
				       + CAMPO11 + ","              
				       /*+ CAMPO12 + ","              
				       + CAMPO13 + ","              
				       + CAMPO14 + ","              
				       + CAMPO15 + ","              
				       + CAMPO16 + ","              
				       + CAMPO17 + ","              
				       + CAMPO18 + ","              
				       + CAMPO19 + ","              
				       + CAMPO20 + ","              
				       + CAMPO21 + ","              
				       + CAMPO22 + ","              
				       + CAMPO23 + ","              
				       + CAMPO24 + ","              
				       + CAMPO25 + ","              
				       + CAMPO26 + ","              
				       + CAMPO27 + ","              
				       + CAMPO28 + ","              
				       + CAMPO29 + ","*/
				       + "CONVERT(AES_DECRYPT("+ CAMPO12 + ",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + CAMPO13 + ","              
				       + "CONVERT(AES_DECRYPT("+ CAMPO14 + ",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + CAMPO15 + ","              
				       + "CONVERT(AES_DECRYPT("+ CAMPO16 + ",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + CAMPO17 + ","              
				       + "CONVERT(AES_DECRYPT("+ CAMPO18 + ",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + CAMPO19 + ","              
				       + "CONVERT(AES_DECRYPT("+ CAMPO20 + ",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + CAMPO21 + ","              
				       + "CONVERT(AES_DECRYPT("+ CAMPO22 + ",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + CAMPO23 + ","              
				       + "CONVERT(AES_DECRYPT("+ CAMPO24 + ",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + CAMPO25 + ","              
				       + "CONVERT(AES_DECRYPT("+ CAMPO26 + ",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + "CONVERT(AES_DECRYPT("+ CAMPO27 + ",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + "CONVERT(AES_DECRYPT("+ CAMPO28 + ",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + "CONVERT(AES_DECRYPT("+ CAMPO29 + ",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       
				       + CAMPO30 + ","
				       + CAMPO31 + ","
				       + CAMPO32 +               
				       " FROM " 
				       + TABLA + 
				       " WHERE " 
				       + CAMPO1 + " = '" + liMovimientoComunidadID + "'";
			
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

						sCODTRN = rs.getString(CAMPO2);  
						sCOTDOR = rs.getString(CAMPO3);  
						sIDPROV = rs.getString(CAMPO4);  
						sCOACCI = rs.getString(CAMPO5);  
						sCOENGP = rs.getString(CAMPO6);  
						sCOCLDO = rs.getString(CAMPO7);  
						sNUDCOM = rs.getString(CAMPO8);  
						sBITC10 = rs.getString(CAMPO9);  
						sCOACES = rs.getString(CAMPO10); 
						sBITC01 = rs.getString(CAMPO11); 
						sNOMCOC = rs.getString("CONVERT(AES_DECRYPT("+ CAMPO12 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)"); 
						sBITC02 = rs.getString(CAMPO13); 
						sNODCCO = rs.getString("CONVERT(AES_DECRYPT("+ CAMPO14 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)"); 
						sBITC03 = rs.getString(CAMPO15); 
						sNOMPRC = rs.getString("CONVERT(AES_DECRYPT("+ CAMPO16 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)"); 
						sBITC04 = rs.getString(CAMPO17); 
						sNUTPRC = rs.getString("CONVERT(AES_DECRYPT("+ CAMPO18 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)"); 
						sBITC05 = rs.getString(CAMPO19); 
						sNOMADC = rs.getString("CONVERT(AES_DECRYPT("+ CAMPO20 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)"); 
						sBITC06 = rs.getString(CAMPO21); 
						sNUTADC = rs.getString("CONVERT(AES_DECRYPT("+ CAMPO22 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)"); 
						sBITC07 = rs.getString(CAMPO23); 
						sNODCAD = rs.getString("CONVERT(AES_DECRYPT("+ CAMPO24 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)"); 
						sBITC08 = rs.getString(CAMPO25); 
						sNUCCEN = rs.getString("CONVERT(AES_DECRYPT("+ CAMPO26 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)"); 
						sNUCCOF = rs.getString("CONVERT(AES_DECRYPT("+ CAMPO27 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)"); 
						sNUCCDI = rs.getString("CONVERT(AES_DECRYPT("+ CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)"); 
						sNUCCNT = rs.getString("CONVERT(AES_DECRYPT("+ CAMPO29 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)"); 
						sBITC09 = rs.getString(CAMPO30); 
						sOBTEXC = rs.getString(CAMPO31); 
						sOBDEER = rs.getString(CAMPO32); 
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+liMovimientoComunidadID+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sCODTRN = "";
				sCOTDOR = "";
				sIDPROV = "";
				sCOACCI = "";
				sCOENGP = "";
				sCOCLDO = "";
				sNUDCOM = "";
				sBITC10 = "";
				sCOACES = "";
				sBITC01 = "";
				sNOMCOC = "";
				sBITC02 = "";
				sNODCCO = "";
				sBITC03 = "";
				sNOMPRC = "";
				sBITC04 = "";
				sNUTPRC = "";
				sBITC05 = "";
				sNOMADC = "";
				sBITC06 = "";
				sNUTADC = "";
				sBITC07 = "";
				sNODCAD = "";
				sBITC08 = "";
				sNUCCEN = "";
				sNUCCOF = "";
				sNUCCDI = "";
				sNUCCNT = "";
				sBITC09 = "";
				sOBTEXC = "";
				sOBDEER = "";
				
				logger.error("ERROR MovimientoComunidadID:|"+liMovimientoComunidadID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new MovimientoComunidad(sCODTRN, sCOTDOR, sIDPROV, sCOACCI, sCOENGP,
				sCOCLDO, sNUDCOM, sBITC10, sCOACES, sBITC01, sNOMCOC, sBITC02,
				sNODCCO, sBITC03, sNOMPRC, sBITC04, sNUTPRC, sBITC05, sNOMADC,
				sBITC06, sNUTADC, sBITC07, sNODCAD, sBITC08, sNUCCEN, sNUCCOF,
				sNUCCDI, sNUCCNT, sBITC09, sOBTEXC, sOBDEER);
	}
	
	public static long getMovimientoComunidadID(Connection conexion, MovimientoComunidad comunidad)
	{
		long liMovimientoComunidadID = 0;
		
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
					" WHERE ("
					+ CAMPO2  +" = '" + comunidad.getCODTRN() + "' AND "
					+ CAMPO4  +" = '" + comunidad.getIDPROV() + "' AND "
					+ CAMPO5  +" = '" + comunidad.getCOACCI() + "' AND "
					+ CAMPO6  +" = '" + comunidad.getCOENGP() + "' AND "
					+ CAMPO7  +" = '" + comunidad.getCOCLDO() + "' AND "
					+ CAMPO8  +" = '" + comunidad.getNUDCOM() + "' AND "
					+ CAMPO9  +" = '" + comunidad.getBITC10() + "' AND "
					+ CAMPO10 +" = '" + comunidad.getCOACES() + "' AND "
					+ CAMPO11 +" = '" + comunidad.getBITC01() + "' AND "
					+ CAMPO12 +" = AES_ENCRYPT('" + comunidad.getNOMCOC() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					+ CAMPO13 +" = '" + comunidad.getBITC02() + "' AND "
					+ CAMPO14 +" = AES_ENCRYPT('" + comunidad.getNODCCO() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					+ CAMPO15 +" = '" + comunidad.getBITC03() + "' AND "
					+ CAMPO16 +" = AES_ENCRYPT('" + comunidad.getNOMPRC() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					+ CAMPO17 +" = '" + comunidad.getBITC04() + "' AND "
					+ CAMPO18 +" = AES_ENCRYPT('" + comunidad.getNUTPRC() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					+ CAMPO19 +" = '" + comunidad.getBITC05() + "' AND "
					+ CAMPO20 +" = AES_ENCRYPT('" + comunidad.getNOMADC() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					+ CAMPO21 +" = '" + comunidad.getBITC06() + "' AND "
					+ CAMPO22 +" = AES_ENCRYPT('" + comunidad.getNUTADC() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					+ CAMPO23 +" = '" + comunidad.getBITC07() + "' AND "
					+ CAMPO24 +" = AES_ENCRYPT('" + comunidad.getNODCAD() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					+ CAMPO25 +" = '" + comunidad.getBITC08() + "' AND "
					+ CAMPO26 +" = AES_ENCRYPT('" + comunidad.getNUCCEN() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					+ CAMPO27 +" = AES_ENCRYPT('" + comunidad.getNUCCOF() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					+ CAMPO28 +" = AES_ENCRYPT('" + comunidad.getNUCCDI() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					+ CAMPO29 +" = AES_ENCRYPT('" + comunidad.getNUCCNT() + "',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					+ CAMPO30 +" = '" + comunidad.getBITC09() + "' AND "
					+ CAMPO31 +" = '" + comunidad.getOBTEXC() + 
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

						liMovimientoComunidadID = rs.getLong(CAMPO1);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+liMovimientoComunidadID+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}			
			} 
			catch (SQLException ex) 
			{
				liMovimientoComunidadID = 0;
				
				logger.error("ERROR COCLDO:|"+comunidad.getCOCLDO()+"|");
				logger.error("ERROR NUDCOM:|"+comunidad.getNUDCOM()+"|");
				logger.error("ERROR COACES:|"+comunidad.getCOACES()+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return liMovimientoComunidadID;
	}
	
	public static boolean existeMovimientoComunidad(Connection conexion, long liMovimientoComunidadID)
	{
		boolean bEncontrado = false;
		
		Statement stmt = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO1 + 
				" FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO1 + " = '" + liMovimientoComunidadID + "'";
		
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
					logger.debug(CAMPO1+":|"+rs.getString(CAMPO1)+"|");
				}
			}
			if (!bEncontrado) 
			{
				logger.debug("No se encontro la información.");
			}
		} 
		catch (SQLException ex) 
		{
			bEncontrado = false;

			logger.error("ERROR MovimientoComunidadID:|"+liMovimientoComunidadID+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeStatement(stmt);
		}

		return bEncontrado;
	}
}