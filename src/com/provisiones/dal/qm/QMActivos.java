package com.provisiones.dal.qm;

import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Activo;
import com.provisiones.types.tablas.ActivoTabla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class QMActivos
{
	private static Logger logger = LoggerFactory.getLogger(QMActivos.class.getName());
	
	public static final String TABLA = "pp002_ac_activos_tbl";

	public static final String CAMPO1 = "coaces_id";

	public static final String CAMPO2  = "nuinmu";
	public static final String CAMPO3  = "cod_cosopa";
	public static final String CAMPO4  = "cod_coenae";
	public static final String CAMPO5  = "cod_coesen";
	public static final String CAMPO6  = "novias";//
	public static final String CAMPO7  = "nupoac";//
	public static final String CAMPO8  = "nuesac";//
	public static final String CAMPO9  = "nupiac";//
	public static final String CAMPO10 = "nupuac";//
	public static final String CAMPO11 = "nomuin";//
	public static final String CAMPO12 = "cod_coprae";
	public static final String CAMPO13 = "noprac";//
	public static final String CAMPO14 = "copoin";
	public static final String CAMPO15 = "fereap";
	public static final String CAMPO16 = "cod_coreae";
	public static final String CAMPO17 = "feinau";
	public static final String CAMPO18 = "fesopo";
	public static final String CAMPO19 = "fesepo";
	public static final String CAMPO20 = "ferepo";
	public static final String CAMPO21 = "feadac";
	public static final String CAMPO22 = "cod_codiju";
	public static final String CAMPO23 = "cod_cosjup";
	public static final String CAMPO24 = "cod_costli";
	public static final String CAMPO25 = "cod_coscar";
	public static final String CAMPO26 = "cod_coesve";
	public static final String CAMPO27 = "cod_cotsin";
	public static final String CAMPO28 = "nufire";//
	public static final String CAMPO29 = "nuregp";
	public static final String CAMPO30 = "nomui0";//
	public static final String CAMPO31 = "nulibe";
	public static final String CAMPO32 = "nutome";
	public static final String CAMPO33 = "nufole";
	public static final String CAMPO34 = "nuinsr";
	public static final String CAMPO35 = "cod_cosocu";
	public static final String CAMPO36 = "cod_coxpro";
	public static final String CAMPO37 = "fesola";
	public static final String CAMPO38 = "fesela";
	public static final String CAMPO39 = "ferela";
	public static final String CAMPO40 = "ferlla";
	public static final String CAMPO41 = "caspre";
	public static final String CAMPO42 = "casutr";
	public static final String CAMPO43 = "casutc";
	public static final String CAMPO44 = "casutg";
	public static final String CAMPO45 = "cod_biarre";
	public static final String CAMPO46 = "cadorm";
	public static final String CAMPO47 = "cabano";
	public static final String CAMPO48 = "cod_bigapa";
	public static final String CAMPO49 = "cagapa";
	public static final String CAMPO50 = "casute";
	public static final String CAMPO51 = "cod_bilipo";
	public static final String CAMPO52 = "cod_biliac";
	public static final String CAMPO53 = "cod_bilius";
	public static final String CAMPO54 = "cod_biboin";
	public static final String CAMPO55 = "cod_bicefi";
	public static final String CAMPO56 = "casucb";
	public static final String CAMPO57 = "casucs";
	public static final String CAMPO58 = "feacon";
	public static final String CAMPO59 = "idauto";
	public static final String CAMPO60 = "fedema";
	public static final String CAMPO61 = "ynocur";//
	public static final String CAMPO62 = "obreco";//
	public static final String CAMPO63 = "ynolec";//
	public static final String CAMPO64 = "nolojz";//
	public static final String CAMPO65 = "ferede";
	public static final String CAMPO66 = "poprop";
	public static final String CAMPO67 = "cod_cograp";
	public static final String CAMPO68 = "fepreg";
	public static final String CAMPO69 = "fephac";
	public static final String CAMPO70 = "fefoac";
	public static final String CAMPO71 = "fevact";
	public static final String CAMPO72 = "imvact";
	public static final String CAMPO73 = "nufipr";
	public static final String CAMPO74 = "cod_cotpet";
	public static final String CAMPO75 = "feempt";
	public static final String CAMPO76 = "fesorc";
	public static final String CAMPO77 = "fesode";
	public static final String CAMPO78 = "fereac";
	public static final String CAMPO79 = "cod_coxsia";
	public static final String CAMPO80 = "nujuzd";
	public static final String CAMPO81 = "nurcat";//
	public static final String CAMPO82 = "nomprc";//
	public static final String CAMPO83 = "nutprc";//
	public static final String CAMPO84 = "nomadc";//
	public static final String CAMPO85 = "nutadc";//
	public static final String CAMPO86 = "impcoo";
	public static final String CAMPO87 = "coenor";
	public static final String CAMPO88 = "cod_cospat";
	public static final String CAMPO89 = "cod_cospas";
	public static final String CAMPO90 = "idcol3";//
	public static final String CAMPO91 = "cod_biobnu";
	public static final String CAMPO92 = "pobrar";
	public static final String CAMPO93 = "fetifi";
	
	private QMActivos(){}

	public static boolean addActivo(Connection conexion, Activo NuevoActivo) 
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			//logger.debug("Ejecutando Query...");
			
			String sQuery = "INSERT INTO " 
					   + TABLA + 
					   " ("
				       + CAMPO1  + ","
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
				       + CAMPO34 + ","              
				       + CAMPO35 + ","              
				       + CAMPO36 + ","              
				       + CAMPO37 + ","              
				       + CAMPO38 + ","              
				       + CAMPO39 + ","              
				       + CAMPO40 + ","              
				       + CAMPO41 + ","              
				       + CAMPO42 + ","              
				       + CAMPO43 + ","              
				       + CAMPO44 + ","              
				       + CAMPO45 + ","              
				       + CAMPO46 + ","              
				       + CAMPO47 + ","              
				       + CAMPO48 + ","              
				       + CAMPO49 + ","              
				       + CAMPO50 + ","              
				       + CAMPO51 + ","              
				       + CAMPO52 + ","              
				       + CAMPO53 + ","              
				       + CAMPO54 + ","              
				       + CAMPO55 + ","              
				       + CAMPO56 + ","              
				       + CAMPO57 + ","              
				       + CAMPO58 + ","              
				       + CAMPO59 + ","              
				       + CAMPO60 + ","              
				       + CAMPO61 + ","              
				       + CAMPO62 + ","              
				       + CAMPO63 + ","              
				       + CAMPO64 + ","              
				       + CAMPO65 + ","              
				       + CAMPO66 + ","              
				       + CAMPO67 + ","              
				       + CAMPO68 + ","              
				       + CAMPO69 + ","              
				       + CAMPO70 + ","              
				       + CAMPO71 + ","              
				       + CAMPO72 + ","              
				       + CAMPO73 + ","              
				       + CAMPO74 + ","              
				       + CAMPO75 + ","              
				       + CAMPO76 + ","              
				       + CAMPO77 + ","              
				       + CAMPO78 + ","              
				       + CAMPO79 + ","              
				       + CAMPO80 + ","              
				       + CAMPO81 + ","              
				       + CAMPO82 + ","              
				       + CAMPO83 + ","              
				       + CAMPO84 + ","              
				       + CAMPO85 + ","              
				       + CAMPO86 + ","              
				       + CAMPO87 + ","              
				       + CAMPO88 + ","              
				       + CAMPO89 + ","              
				       + CAMPO90 + ","              
				       + CAMPO91 + ","
				       + CAMPO92 + ","
				       + CAMPO93 +                  
				       ") VALUES ('"   
				       + NuevoActivo.getCOACES() + "','"
				       + NuevoActivo.getNUINMU() + "','"  
				       + NuevoActivo.getCOSOPA() + "','"  
				       + NuevoActivo.getCOENAE() + "','"  
				       + NuevoActivo.getCOESEN() + "',"
				       //+ NuevoActivo.getNOVIAS() + "','"
				       + "AES_ENCRYPT('"+NuevoActivo.getNOVIAS()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       //+ NuevoActivo.getNUPOAC() + "','"
				       + "AES_ENCRYPT('"+NuevoActivo.getNUPOAC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       //+ NuevoActivo.getNUESAC() + "','"
				       + "AES_ENCRYPT('"+NuevoActivo.getNUESAC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       //+ NuevoActivo.getNUPIAC() + "','"
				       + "AES_ENCRYPT('"+NuevoActivo.getNUPIAC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       //+ NuevoActivo.getNUPUAC() + "','"
				       + "AES_ENCRYPT('"+NuevoActivo.getNUPUAC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       //+ NuevoActivo.getNOMUIN() + "','"
				       + "AES_ENCRYPT('"+NuevoActivo.getNOMUIN()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'"
				       + NuevoActivo.getCOPRAE() + "',"  
				       //+ NuevoActivo.getNOPRAC() + "','"
				       + "AES_ENCRYPT('"+NuevoActivo.getNOPRAC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'"
				       + NuevoActivo.getCOPOIN() + "','"  
				       + NuevoActivo.getFEREAP() + "','"  
				       + NuevoActivo.getCOREAE() + "','"  
				       + NuevoActivo.getFEINAU() + "','"  
				       + NuevoActivo.getFESOPO() + "','"  
				       + NuevoActivo.getFESEPO() + "','"  
				       + NuevoActivo.getFEREPO() + "','"  
				       + NuevoActivo.getFEADAC() + "','"  
				       + NuevoActivo.getCODIJU() + "','"  
				       + NuevoActivo.getCOSJUP() + "','"  
				       + NuevoActivo.getCOSTLI() + "','"  
				       + NuevoActivo.getCOSCAR() + "','"  
				       + NuevoActivo.getCOESVE() + "','"  
				       + NuevoActivo.getCOTSIN() + "',"  
				       //+ NuevoActivo.getNUFIRE() + "','"
				        + "AES_ENCRYPT('"+NuevoActivo.getNUFIRE()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'"
				       + NuevoActivo.getNUREGP() + "',"  
				       //+ NuevoActivo.getNOMUI0() + "','"
				       + "AES_ENCRYPT('"+NuevoActivo.getNOMUI0()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'"
				       + NuevoActivo.getNULIBE() + "','"  
				       + NuevoActivo.getNUTOME() + "','"  
				       + NuevoActivo.getNUFOLE() + "','"  
				       + NuevoActivo.getNUINSR() + "','"  
				       + NuevoActivo.getCOSOCU() + "','"  
				       + NuevoActivo.getCOXPRO() + "','"  
				       + NuevoActivo.getFESOLA() + "','"  
				       + NuevoActivo.getFESELA() + "','"  
				       + NuevoActivo.getFERELA() + "','"  
				       + NuevoActivo.getFERLLA() + "','"  
				       + NuevoActivo.getCASPRE() + "','"  
				       + NuevoActivo.getCASUTR() + "','"  
				       + NuevoActivo.getCASUTC() + "','"  
				       + NuevoActivo.getCASUTG() + "','"  
				       + NuevoActivo.getBIARRE() + "','"  
				       + NuevoActivo.getCADORM() + "','"  
				       + NuevoActivo.getCABANO() + "','"  
				       + NuevoActivo.getBIGAPA() + "','"  
				       + NuevoActivo.getCAGAPA() + "','"  
				       + NuevoActivo.getCASUTE() + "','"  
				       + NuevoActivo.getBILIPO() + "','"  
				       + NuevoActivo.getBILIAC() + "','"  
				       + NuevoActivo.getBILIUS() + "','"  
				       + NuevoActivo.getBIBOIN() + "','"  
				       + NuevoActivo.getBICEFI() + "','"  
				       + NuevoActivo.getCASUCB() + "','"  
				       + NuevoActivo.getCASUCS() + "','"  
				       + NuevoActivo.getFEACON() + "','"  
				       + NuevoActivo.getIDAUTO() + "','"  
				       + NuevoActivo.getFEDEMA() + "',"  
				       //+ NuevoActivo.getYNOCUR() + "','"
				       + "AES_ENCRYPT('"+NuevoActivo.getYNOCUR()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       //+ NuevoActivo.getOBRECO() + "','"
				       + "AES_ENCRYPT('"+NuevoActivo.getOBRECO()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       //+ NuevoActivo.getYNOLEC() + "','"
				       + "AES_ENCRYPT('"+NuevoActivo.getYNOLEC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       //+ NuevoActivo.getNOLOJZ() + "','"
				       + "AES_ENCRYPT('"+NuevoActivo.getNOLOJZ()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'"
				       + NuevoActivo.getFEREDE() + "','"  
				       + NuevoActivo.getPOPROP() + "','"  
				       + NuevoActivo.getCOGRAP() + "','"  
				       + NuevoActivo.getFEPREG() + "','"  
				       + NuevoActivo.getFEPHAC() + "','"  
				       + NuevoActivo.getFEFOAC() + "','"  
				       + NuevoActivo.getFEVACT() + "','"  
				       + NuevoActivo.getIMVACT() + "','"  
				       + NuevoActivo.getNUFIPR() + "','"  
				       + NuevoActivo.getCOTPET() + "','"  
				       + NuevoActivo.getFEEMPT() + "','"  
				       + NuevoActivo.getFESORC() + "','"  
				       + NuevoActivo.getFESODE() + "','"  
				       + NuevoActivo.getFEREAC() + "','"  
				       + NuevoActivo.getCOXSIA() + "','"  
				       + NuevoActivo.getNUJUZD() + "','"  
				       + NuevoActivo.getNURCAT() + "',"
				       //+ "AES_ENCRYPT('"+NuevoActivo.getNURCAT()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       //+ NuevoActivo.getNOMPRC() + "','"
				       + "AES_ENCRYPT('"+NuevoActivo.getNOMPRC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       //+ NuevoActivo.getNUTPRC() + "','"
				       + "AES_ENCRYPT('"+NuevoActivo.getNUTPRC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       //+ NuevoActivo.getNOMADC() + "','"
				       + "AES_ENCRYPT('"+NuevoActivo.getNOMADC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       //+ NuevoActivo.getNUTADC() + "','"
				       + "AES_ENCRYPT('"+NuevoActivo.getNUTADC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'"
				       + NuevoActivo.getIMPCOO() + "','"  
				       + NuevoActivo.getCOENOR() + "','"  
				       + NuevoActivo.getCOSPAT() + "','"  
				       + NuevoActivo.getCOSPAS() + "',"  
				       //+ NuevoActivo.getIDCOL3() + "','"
				       + "AES_ENCRYPT('"+NuevoActivo.getIDCOL3()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'"
				       + NuevoActivo.getBIOBNU() + "','"  
				       + NuevoActivo.getPOBRAR() + "','"
				       + NuevoActivo.getFETIFI() + "' )";
			
			//logger.debug(sQuery);
			
			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				//logger.debug("Ejecutada con �xito!");

				bSalida = true;
				
			} 
			catch (SQLException ex) 
			{
				bSalida = false;

				logger.error("ERROR COACES:|"+NuevoActivo.getCOACES()+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	public static boolean modActivo(Connection conexion, Activo NuevoActivo, int iCOACES)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			//logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO2 + " = '"+ NuevoActivo.getNUINMU() + "', "
					+ CAMPO3 + " = '"+ NuevoActivo.getCOSOPA() + "', "
					+ CAMPO4 + " = '"+ NuevoActivo.getCOENAE() + "', "
					+ CAMPO5 + " = '"+ NuevoActivo.getCOESEN() + "', "
					//+ CAMPO6  + " = '"+ NuevoActivo.getNOVIAS() + "', "
					+ CAMPO6 + " = AES_ENCRYPT('"+NuevoActivo.getNOVIAS()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					//+ CAMPO7  + " = '"+ NuevoActivo.getNUPOAC() + "', "
					+ CAMPO7 + " = AES_ENCRYPT('"+NuevoActivo.getNUPOAC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					//+ CAMPO8  + " = '"+ NuevoActivo.getNUESAC() + "', "
					+ CAMPO8 + " = AES_ENCRYPT('"+NuevoActivo.getNUESAC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					//+ CAMPO9  + " = '"+ NuevoActivo.getNUPIAC() + "', "
					+ CAMPO9 + " = AES_ENCRYPT('"+NuevoActivo.getNUPIAC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					//+ CAMPO10 + " = '"+ NuevoActivo.getNUPUAC() + "', "
					+ CAMPO10 + " = AES_ENCRYPT('"+NuevoActivo.getNUPUAC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					//+ CAMPO11 + " = '"+ NuevoActivo.getNOMUIN() + "', "
					+ CAMPO11 + " = AES_ENCRYPT('"+NuevoActivo.getNOMUIN()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ CAMPO12 + " = '"+ NuevoActivo.getCOPRAE() + "', "
					//+ CAMPO13 + " = '"+ NuevoActivo.getNOPRAC() + "', "
					+ CAMPO13 + " = AES_ENCRYPT('"+NuevoActivo.getNOPRAC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ CAMPO14 + " = '"+ NuevoActivo.getCOPOIN() + "', "
					+ CAMPO15 + " = '"+ NuevoActivo.getFEREAP() + "', "
					+ CAMPO16 + " = '"+ NuevoActivo.getCOREAE() + "', "
					+ CAMPO17 + " = '"+ NuevoActivo.getFEINAU() + "', "
					+ CAMPO18 + " = '"+ NuevoActivo.getFESOPO() + "', "
					+ CAMPO19 + " = '"+ NuevoActivo.getFESEPO() + "', "
					+ CAMPO20 + " = '"+ NuevoActivo.getFEREPO() + "', "
					+ CAMPO21 + " = '"+ NuevoActivo.getFEADAC() + "', "
					+ CAMPO22 + " = '"+ NuevoActivo.getCODIJU() + "', "
					+ CAMPO23 + " = '"+ NuevoActivo.getCOSJUP() + "', "
					+ CAMPO24 + " = '"+ NuevoActivo.getCOSTLI() + "', "
					+ CAMPO25 + " = '"+ NuevoActivo.getCOSCAR() + "', "
					+ CAMPO26 + " = '"+ NuevoActivo.getCOESVE() + "', "
					+ CAMPO27 + " = '"+ NuevoActivo.getCOTSIN() + "', "
					//+ CAMPO28 + " = '"+ NuevoActivo.getNUFIRE() + "', "
					+ CAMPO28 + " = AES_ENCRYPT('"+NuevoActivo.getNUFIRE()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ CAMPO29 + " = '"+ NuevoActivo.getNUREGP() + "', "
					//+ CAMPO30 + " = '"+ NuevoActivo.getNOMUI0() + "', "
					+ CAMPO30 + " = AES_ENCRYPT('"+NuevoActivo.getNOMUI0()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ CAMPO31 + " = '"+ NuevoActivo.getNULIBE() + "', "
					+ CAMPO32 + " = '"+ NuevoActivo.getNUTOME() + "', "
					+ CAMPO33 + " = '"+ NuevoActivo.getNUFOLE() + "', "
					+ CAMPO34 + " = '"+ NuevoActivo.getNUINSR() + "', "
					+ CAMPO35 + " = '"+ NuevoActivo.getCOSOCU() + "', "
					+ CAMPO36 + " = '"+ NuevoActivo.getCOXPRO() + "', "
					+ CAMPO37 + " = '"+ NuevoActivo.getFESOLA() + "', "
					+ CAMPO38 + " = '"+ NuevoActivo.getFESELA() + "', "
					+ CAMPO39 + " = '"+ NuevoActivo.getFERELA() + "', "
					+ CAMPO40 + " = '"+ NuevoActivo.getFERLLA() + "', "
					+ CAMPO41 + " = '"+ NuevoActivo.getCASPRE() + "', "
					+ CAMPO42 + " = '"+ NuevoActivo.getCASUTR() + "', "
					+ CAMPO43 + " = '"+ NuevoActivo.getCASUTC() + "', "
					+ CAMPO44 + " = '"+ NuevoActivo.getCASUTG() + "', "
					+ CAMPO45 + " = '"+ NuevoActivo.getBIARRE() + "', "
					+ CAMPO46 + " = '"+ NuevoActivo.getCADORM() + "', "
					+ CAMPO47 + " = '"+ NuevoActivo.getCABANO() + "', "
					+ CAMPO48 + " = '"+ NuevoActivo.getBIGAPA() + "', "
					+ CAMPO49 + " = '"+ NuevoActivo.getCAGAPA() + "', "
					+ CAMPO50 + " = '"+ NuevoActivo.getCASUTE() + "', "
					+ CAMPO51 + " = '"+ NuevoActivo.getBILIPO() + "', "
					+ CAMPO52 + " = '"+ NuevoActivo.getBILIAC() + "', "
					+ CAMPO53 + " = '"+ NuevoActivo.getBILIUS() + "', "
					+ CAMPO54 + " = '"+ NuevoActivo.getBIBOIN() + "', "
					+ CAMPO55 + " = '"+ NuevoActivo.getBICEFI() + "', "
					+ CAMPO56 + " = '"+ NuevoActivo.getCASUCB() + "', "
					+ CAMPO57 + " = '"+ NuevoActivo.getCASUCS() + "', "
					+ CAMPO58 + " = '"+ NuevoActivo.getFEACON() + "', "
					+ CAMPO59 + " = '"+ NuevoActivo.getIDAUTO() + "', "
					+ CAMPO60 + " = '"+ NuevoActivo.getFEDEMA() + "', "
					//+ CAMPO61 + " = '"+ NuevoActivo.getYNOCUR() + "', "
					+ CAMPO61 + " = AES_ENCRYPT('"+NuevoActivo.getYNOCUR()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					//+ CAMPO62 + " = '"+ NuevoActivo.getOBRECO() + "', "
					+ CAMPO62 + " = AES_ENCRYPT('"+NuevoActivo.getOBRECO()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					//+ CAMPO63 + " = '"+ NuevoActivo.getYNOLEC() + "', "
					+ CAMPO63 + " = AES_ENCRYPT('"+NuevoActivo.getYNOLEC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					//+ CAMPO64 + " = '"+ NuevoActivo.getNOLOJZ() + "', "
					+ CAMPO64 + " = AES_ENCRYPT('"+NuevoActivo.getNOLOJZ()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ CAMPO65 + " = '"+ NuevoActivo.getFEREDE() + "', "
					+ CAMPO66 + " = '"+ NuevoActivo.getPOPROP() + "', "
					+ CAMPO67 + " = '"+ NuevoActivo.getCOGRAP() + "', "
					+ CAMPO68 + " = '"+ NuevoActivo.getFEPREG() + "', "
					+ CAMPO69 + " = '"+ NuevoActivo.getFEPHAC() + "', "
					+ CAMPO70 + " = '"+ NuevoActivo.getFEFOAC() + "', "
					+ CAMPO71 + " = '"+ NuevoActivo.getFEVACT() + "', "
					+ CAMPO72 + " = '"+ NuevoActivo.getIMVACT() + "', "
					+ CAMPO73 + " = '"+ NuevoActivo.getNUFIPR() + "', "
					+ CAMPO74 + " = '"+ NuevoActivo.getCOTPET() + "', "
					+ CAMPO75 + " = '"+ NuevoActivo.getFEEMPT() + "', "
					+ CAMPO76 + " = '"+ NuevoActivo.getFESORC() + "', "
					+ CAMPO77 + " = '"+ NuevoActivo.getFESODE() + "', "
					+ CAMPO78 + " = '"+ NuevoActivo.getFEREAC() + "', "
					+ CAMPO79 + " = '"+ NuevoActivo.getCOXSIA() + "', "
					+ CAMPO80 + " = '"+ NuevoActivo.getNUJUZD() + "', "
					+ CAMPO81 + " = '"+ NuevoActivo.getNURCAT() + "', "
					//+ CAMPO81 + " = AES_ENCRYPT('"+NuevoActivo.getNURCAT()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					//+ CAMPO82 + " = '"+ NuevoActivo.getNOMPRC() + "', "
					+ CAMPO82 + " = AES_ENCRYPT('"+NuevoActivo.getNOMPRC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					//+ CAMPO83 + " = '"+ NuevoActivo.getNUTPRC() + "', "
					+ CAMPO83 + " = AES_ENCRYPT('"+NuevoActivo.getNUTPRC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					//+ CAMPO84 + " = '"+ NuevoActivo.getNOMADC() + "', "
					+ CAMPO84 + " = AES_ENCRYPT('"+NuevoActivo.getNOMADC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					//+ CAMPO85 + " = '"+ NuevoActivo.getNUTADC() + "', "
					+ CAMPO85 + " = AES_ENCRYPT('"+NuevoActivo.getNUTADC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ CAMPO86 + " = '"+ NuevoActivo.getIMPCOO() + "', "
					+ CAMPO87 + " = '"+ NuevoActivo.getCOENOR() + "', "
					+ CAMPO88 + " = '"+ NuevoActivo.getCOSPAT() + "', "
					+ CAMPO89 + " = '"+ NuevoActivo.getCOSPAS() + "', "
					//+ CAMPO90 + " = '"+ NuevoActivo.getIDCOL3() + "', "
					+ CAMPO90 + " = AES_ENCRYPT('"+NuevoActivo.getIDCOL3()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
					+ CAMPO91 + " = '"+ NuevoActivo.getBIOBNU() + "', "
					+ CAMPO92 + " = '"+ NuevoActivo.getPOBRAR() + "', "
					+ CAMPO93 + " = '"+ NuevoActivo.getFETIFI() + "' "+
					" WHERE "
					+ CAMPO1 + " = '"+ iCOACES +"'";
			
			//logger.debug(sQuery);
			
			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				//logger.debug("Ejecutada con �xito!");

				bSalida = true;
						
			} 
			catch (SQLException ex) 
			{
				bSalida = false;

				logger.error("ERROR COACES:|"+NuevoActivo.getCOACES()+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

			} 
			finally 
			{

				Utils.closeStatement(stmt);
				
			}
		}

		return bSalida;
	}

	public static boolean delActivo(Connection conexion, int iCOACES)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
							+ TABLA + 
							" WHERE " 
							+ CAMPO1 + " = '" + iCOACES + "'";

			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con �xito!");

				bSalida = true;
				
			} 
			catch (SQLException ex) 
			{
				bSalida = false;

				logger.error("ERROR COACES:|"+iCOACES+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static Activo getActivo(Connection conexion, int iCOACES)
	{
		String sCOACES = "";
		String sNUINMU = "";
		String sCOSOPA = "";
		String sCOENAE = "";
		String sCOESEN = "";
		String sNOVIAS = "";
		String sNUPOAC = "";
		String sNUESAC = "";
		String sNUPIAC = "";
		String sNUPUAC = "";
		String sNOMUIN = "";
		String sCOPRAE = "";
		String sNOPRAC = "";
		String sCOPOIN = "";
		String sFEREAP = "";
		String sCOREAE = "";
		String sFEINAU = "";
		String sFESOPO = "";
		String sFESEPO = "";
		String sFEREPO = "";
		String sFEADAC = "";
		String sCODIJU = "";
		String sCOSJUP = "";
		String sCOSTLI = "";
		String sCOSCAR = "";
		String sCOESVE = "";
		String sCOTSIN = "";
		String sNUFIRE = "";
		String sNUREGP = "";
		String sNOMUI0 = "";
		String sNULIBE = "";
		String sNUTOME = "";
		String sNUFOLE = "";
		String sNUINSR = "";
		String sCOSOCU = "";
		String sCOXPRO = "";
		String sFESOLA = "";
		String sFESELA = "";
		String sFERELA = "";
		String sFERLLA = "";
		String sCASPRE = "";
		String sCASUTR = "";
		String sCASUTC = "";
		String sCASUTG = "";
		String sBIARRE = "";
		String sCADORM = "";
		String sCABANO = "";
		String sBIGAPA = "";
		String sCAGAPA = "";
		String sCASUTE = "";
		String sBILIPO = "";
		String sBILIAC = "";
		String sBILIUS = "";
		String sBIBOIN = "";
		String sBICEFI = "";
		String sCASUCB = "";
		String sCASUCS = "";
		String sFEACON = "";
		String sIDAUTO = "";
		String sFEDEMA = "";
		String sYNOCUR = "";
		String sOBRECO = "";
		String sYNOLEC = "";
		String sNOLOJZ = "";
		String sFEREDE = "";
		String sPOPROP = "";
		String sCOGRAP = "";
		String sFEPREG = "";
		String sFEPHAC = "";
		String sFEFOAC = "";
		String sFEVACT = "";
		String sIMVACT = "";
		String sNUFIPR = "";
		String sCOTPET = "";
		String sFEEMPT = "";
		String sFESORC = "";
		String sFESODE = "";
		String sFEREAC = "";
		String sCOXSIA = "";
		String sNUJUZD = "";
		String sNURCAT = "";
		String sNOMPRC = "";
		String sNUTPRC = "";
		String sNOMADC = "";
		String sNUTADC = "";
		String sIMPCOO = "";
		String sCOENOR = "";
		String sCOSPAT = "";
		String sCOSPAS = "";
		String sIDCOL3 = "";
		String sBIOBNU = "";
		String sPOBRAR = "";
		String sFETIFI = "";

		if (conexion != null)
		{
			Statement stmt = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + CAMPO1  + ","
					   + CAMPO2  + ","              
				       + CAMPO3  + ","              
				       + CAMPO4  + ","              
				       + CAMPO5  + ","              
				       //+ CAMPO6  + ","              
				       + "CONVERT(AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO7  + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO8  + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO8 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO9  + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO10 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO11 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + CAMPO12 + ","              
				       //+ CAMPO13 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
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
				       //+ CAMPO28 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + CAMPO29 + ","              
				       //+ CAMPO30 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO30 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + CAMPO31 + ","              
				       + CAMPO32 + ","              
				       + CAMPO33 + ","              
				       + CAMPO34 + ","              
				       + CAMPO35 + ","              
				       + CAMPO36 + ","              
				       + CAMPO37 + ","              
				       + CAMPO38 + ","              
				       + CAMPO39 + ","              
				       + CAMPO40 + ","              
				       + CAMPO41 + ","              
				       + CAMPO42 + ","              
				       + CAMPO43 + ","              
				       + CAMPO44 + ","              
				       + CAMPO45 + ","              
				       + CAMPO46 + ","              
				       + CAMPO47 + ","              
				       + CAMPO48 + ","              
				       + CAMPO49 + ","              
				       + CAMPO50 + ","              
				       + CAMPO51 + ","              
				       + CAMPO52 + ","              
				       + CAMPO53 + ","              
				       + CAMPO54 + ","              
				       + CAMPO55 + ","              
				       + CAMPO56 + ","              
				       + CAMPO57 + ","              
				       + CAMPO58 + ","              
				       + CAMPO59 + ","              
				       + CAMPO60 + ","              
				       //+ CAMPO61 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO61 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO62 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO62 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO63 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO63 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO64 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO64 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + CAMPO65 + ","              
				       + CAMPO66 + ","              
				       + CAMPO67 + ","              
				       + CAMPO68 + ","              
				       + CAMPO69 + ","              
				       + CAMPO70 + ","              
				       + CAMPO71 + ","              
				       + CAMPO72 + ","              
				       + CAMPO73 + ","              
				       + CAMPO74 + ","              
				       + CAMPO75 + ","              
				       + CAMPO76 + ","              
				       + CAMPO77 + ","              
				       + CAMPO78 + ","              
				       + CAMPO79 + ","              
				       + CAMPO80 + ","              
				       + CAMPO81 + ","
				       //+ "CONVERT(AES_DECRYPT("+CAMPO81 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO82 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO82 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO83 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO83 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO84 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO84 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO85 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO85 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + CAMPO86 + ","              
				       + CAMPO87 + ","              
				       + CAMPO88 + ","              
				       + CAMPO89 + ","              
				       //+ CAMPO90 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO90 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + CAMPO91 + ","
				       + CAMPO92 + ","
				       + CAMPO93 +        
				       " FROM " 
				       + TABLA + 
				       " WHERE "
				       + CAMPO1 + " = '" + iCOACES	+ "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con �xito!");

				logger.debug(CAMPO1+":|"+iCOACES+"|");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;

						sCOACES = rs.getString(CAMPO1);
						sNUINMU = rs.getString(CAMPO2);
						sCOSOPA = rs.getString(CAMPO3);
						sCOENAE = rs.getString(CAMPO4);
						sCOESEN = rs.getString(CAMPO5);
						//sNOVIAS = rs.getString(CAMPO6);
						sNOVIAS = rs.getString("CONVERT(AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sNUPOAC = rs.getString(CAMPO7);
						sNUPOAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sNUESAC = rs.getString(CAMPO8);
						sNUESAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO8 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sNUPIAC = rs.getString(CAMPO9);
						sNUPIAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sNUPUAC = rs.getString(CAMPO10);
						sNUPUAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sNOMUIN = rs.getString(CAMPO11);
						sNOMUIN = rs.getString("CONVERT(AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						sCOPRAE = rs.getString(CAMPO12);
						//sNOPRAC = rs.getString(CAMPO13);
						sNOPRAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						sCOPOIN = rs.getString(CAMPO14);
						sFEREAP = rs.getString(CAMPO15);
						sCOREAE = rs.getString(CAMPO16);
						sFEINAU = rs.getString(CAMPO17);
						sFESOPO = rs.getString(CAMPO18);
						sFESEPO = rs.getString(CAMPO19);
						sFEREPO = rs.getString(CAMPO20);
						sFEADAC = rs.getString(CAMPO21);
						sCODIJU = rs.getString(CAMPO22);
						sCOSJUP = rs.getString(CAMPO23);
						sCOSTLI = rs.getString(CAMPO24);
						sCOSCAR = rs.getString(CAMPO25);
						sCOESVE = rs.getString(CAMPO26);
						sCOTSIN = rs.getString(CAMPO27);
						//sNUFIRE = rs.getString(CAMPO28);
						sNUFIRE = rs.getString("CONVERT(AES_DECRYPT("+CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						sNUREGP = rs.getString(CAMPO29);
						//sNOMUI0 = rs.getString(CAMPO30);
						sNOMUI0 = rs.getString("CONVERT(AES_DECRYPT("+CAMPO30 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						sNULIBE = rs.getString(CAMPO31);
						sNUTOME = rs.getString(CAMPO32);
						sNUFOLE = rs.getString(CAMPO33);
						sNUINSR = rs.getString(CAMPO34);
						sCOSOCU = rs.getString(CAMPO35);
						sCOXPRO = rs.getString(CAMPO36);
						sFESOLA = rs.getString(CAMPO37);
						sFESELA = rs.getString(CAMPO38);
						sFERELA = rs.getString(CAMPO39);
						sFERLLA = rs.getString(CAMPO40);
						sCASPRE = rs.getString(CAMPO41);
						sCASUTR = rs.getString(CAMPO42);
						sCASUTC = rs.getString(CAMPO43);
						sCASUTG = rs.getString(CAMPO44);
						sBIARRE = rs.getString(CAMPO45);
						sCADORM = rs.getString(CAMPO46);
						sCABANO = rs.getString(CAMPO47);
						sBIGAPA = rs.getString(CAMPO48);
						sCAGAPA = rs.getString(CAMPO49);
						sCASUTE = rs.getString(CAMPO50);
						sBILIPO = rs.getString(CAMPO51);
						sBILIAC = rs.getString(CAMPO52);
						sBILIUS = rs.getString(CAMPO53);
						sBIBOIN = rs.getString(CAMPO54);
						sBICEFI = rs.getString(CAMPO55);
						sCASUCB = rs.getString(CAMPO56);
						sCASUCS = rs.getString(CAMPO57);
						sFEACON = rs.getString(CAMPO58);
						sIDAUTO = rs.getString(CAMPO59);
						sFEDEMA = rs.getString(CAMPO60);
						//sYNOCUR = rs.getString(CAMPO61);
						sYNOCUR = rs.getString("CONVERT(AES_DECRYPT("+CAMPO61 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sOBRECO = rs.getString(CAMPO62);
						sOBRECO = rs.getString("CONVERT(AES_DECRYPT("+CAMPO62 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sYNOLEC = rs.getString(CAMPO63);
						sYNOLEC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO63 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sNOLOJZ = rs.getString(CAMPO64);
						sNOLOJZ = rs.getString("CONVERT(AES_DECRYPT("+CAMPO64 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						sFEREDE = rs.getString(CAMPO65);
						sPOPROP = rs.getString(CAMPO66);
						sCOGRAP = rs.getString(CAMPO67);
						sFEPREG = rs.getString(CAMPO68);
						sFEPHAC = rs.getString(CAMPO69);
						sFEFOAC = rs.getString(CAMPO70);
						sFEVACT = rs.getString(CAMPO71);
						sIMVACT = rs.getString(CAMPO72);
						sNUFIPR = rs.getString(CAMPO73);
						sCOTPET = rs.getString(CAMPO74);
						sFEEMPT = rs.getString(CAMPO75);
						sFESORC = rs.getString(CAMPO76);
						sFESODE = rs.getString(CAMPO77);
						sFEREAC = rs.getString(CAMPO78);
						sCOXSIA = rs.getString(CAMPO79);
						sNUJUZD = rs.getString(CAMPO80);
						sNURCAT = rs.getString(CAMPO81);
						//sNURCAT = rs.getString("CONVERT(AES_DECRYPT("+CAMPO81 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sNOMPRC = rs.getString(CAMPO82);
						sNOMPRC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO82 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sNUTPRC = rs.getString(CAMPO83);
						sNUTPRC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO83 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sNOMADC = rs.getString(CAMPO84);
						sNOMADC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO84 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sNUTADC = rs.getString(CAMPO85);
						sNUTADC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO85 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						sIMPCOO = rs.getString(CAMPO86);
						sCOENOR = rs.getString(CAMPO87);
						sCOSPAT = rs.getString(CAMPO88);
						sCOSPAS = rs.getString(CAMPO89);
						//sIDCOL3 = rs.getString(CAMPO90);
						sIDCOL3 = rs.getString("CONVERT(AES_DECRYPT("+CAMPO90 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						sBIOBNU = rs.getString(CAMPO91);
						sPOBRAR = rs.getString(CAMPO92);
						sFETIFI = rs.getString(CAMPO93);

						logger.debug("Encontrado el registro!");

					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la informaci�n.");
				}

			} 
			catch (SQLException ex) 
			{
				sCOACES = "";
				sNUINMU = "";
				sCOSOPA = "";
				sCOENAE = "";
				sCOESEN = "";
				sNOVIAS = "";
				sNUPOAC = "";
				sNUESAC = "";
				sNUPIAC = "";
				sNUPUAC = "";
				sNOMUIN = "";
				sCOPRAE = "";
				sNOPRAC = "";
				sCOPOIN = "";
				sFEREAP = "";
				sCOREAE = "";
				sFEINAU = "";
				sFESOPO = "";
				sFESEPO = "";
				sFEREPO = "";
				sFEADAC = "";
				sCODIJU = "";
				sCOSJUP = "";
				sCOSTLI = "";
				sCOSCAR = "";
				sCOESVE = "";
				sCOTSIN = "";
				sNUFIRE = "";
				sNUREGP = "";
				sNOMUI0 = "";
				sNULIBE = "";
				sNUTOME = "";
				sNUFOLE = "";
				sNUINSR = "";
				sCOSOCU = "";
				sCOXPRO = "";
				sFESOLA = "";
				sFESELA = "";
				sFERELA = "";
				sFERLLA = "";
				sCASPRE = "";
				sCASUTR = "";
				sCASUTC = "";
				sCASUTG = "";
				sBIARRE = "";
				sCADORM = "";
				sCABANO = "";
				sBIGAPA = "";
				sCAGAPA = "";
				sCASUTE = "";
				sBILIPO = "";
				sBILIAC = "";
				sBILIUS = "";
				sBIBOIN = "";
				sBICEFI = "";
				sCASUCB = "";
				sCASUCS = "";
				sFEACON = "";
				sIDAUTO = "";
				sFEDEMA = "";
				sYNOCUR = "";
				sOBRECO = "";
				sYNOLEC = "";
				sNOLOJZ = "";
				sFEREDE = "";
				sPOPROP = "";
				sCOGRAP = "";
				sFEPREG = "";
				sFEPHAC = "";
				sFEFOAC = "";
				sFEVACT = "";
				sIMVACT = "";
				sNUFIPR = "";
				sCOTPET = "";
				sFEEMPT = "";
				sFESORC = "";
				sFESODE = "";
				sFEREAC = "";
				sCOXSIA = "";
				sNUJUZD = "";
				sNURCAT = "";
				sNOMPRC = "";
				sNUTPRC = "";
				sNOMADC = "";
				sNUTADC = "";
				sIMPCOO = "";
				sCOENOR = "";
				sCOSPAT = "";
				sCOSPAS = "";
				sIDCOL3 = "";
				sBIOBNU = "";
				sPOBRAR = "";
				sFETIFI = "";
				
				logger.error("ERROR COACES:|"+iCOACES+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}
		

		return new Activo(sCOACES, sNUINMU, sCOSOPA, sCOENAE, sCOESEN, sNOVIAS,
				sNUPOAC, sNUESAC, sNUPIAC, sNUPUAC, sNOMUIN, sCOPRAE, sNOPRAC,
				sCOPOIN, sFEREAP, sCOREAE, sFEINAU, sFESOPO, sFESEPO, sFEREPO,
				sFEADAC, sCODIJU, sCOSJUP, sCOSTLI, sCOSCAR, sCOESVE, sCOTSIN,
				sNUFIRE, sNUREGP, sNOMUI0, sNULIBE, sNUTOME, sNUFOLE, sNUINSR,
				sCOSOCU, sCOXPRO, sFESOLA, sFESELA, sFERELA, sFERLLA, sCASPRE,
				sCASUTR, sCASUTC, sCASUTG, sBIARRE, sCADORM, sCABANO, sBIGAPA,
				sCAGAPA, sCASUTE, sBILIPO, sBILIAC, sBILIUS, sBIBOIN, sBICEFI,
				sCASUCB, sCASUCS, sFEACON, sIDAUTO, sFEDEMA, sYNOCUR, sOBRECO,
				sYNOLEC, sNOLOJZ, sFEREDE, sPOPROP, sCOGRAP, sFEPREG, sFEPHAC,
				sFEFOAC, sFEVACT, sIMVACT, sNUFIPR, sCOTPET, sFEEMPT, sFESORC,
				sFESODE, sFEREAC, sCOXSIA, sNUJUZD, sNURCAT, sNOMPRC, sNUTPRC,
				sNOMADC, sNUTADC, sIMPCOO, sCOENOR, sCOSPAT, sCOSPAS, sIDCOL3,
				sBIOBNU, sPOBRAR, sFETIFI);
	}
	
	public static Activo getDetallesActivo(Connection conexion, int iCOACES)
	{
		String sCOACES = "";
		String sNUINMU = "";
		String sCOSOPA = "";
		String sCOENAE = "";
		String sCOESEN = "";
		String sNOVIAS = "";
		String sNUPOAC = "";
		String sNUESAC = "";
		String sNUPIAC = "";
		String sNUPUAC = "";
		String sNOMUIN = "";
		String sCOPRAE = "";
		String sNOPRAC = "";
		String sCOPOIN = "";
		String sFEREAP = "";
		String sCOREAE = "";
		String sFEINAU = "";
		String sFESOPO = "";
		String sFESEPO = "";
		String sFEREPO = "";
		String sFEADAC = "";
		String sCODIJU = "";
		String sCOSJUP = "";
		String sCOSTLI = "";
		String sCOSCAR = "";
		String sCOESVE = "";
		String sCOTSIN = "";
		String sNUFIRE = "";
		String sNUREGP = "";
		String sNOMUI0 = "";
		String sNULIBE = "";
		String sNUTOME = "";
		String sNUFOLE = "";
		String sNUINSR = "";
		String sCOSOCU = "";
		String sCOXPRO = "";
		String sFESOLA = "";
		String sFESELA = "";
		String sFERELA = "";
		String sFERLLA = "";
		String sCASPRE = "";
		String sCASUTR = "";
		String sCASUTC = "";
		String sCASUTG = "";
		String sBIARRE = "";
		String sCADORM = "";
		String sCABANO = "";
		String sBIGAPA = "";
		String sCAGAPA = "";
		String sCASUTE = "";
		String sBILIPO = "";
		String sBILIAC = "";
		String sBILIUS = "";
		String sBIBOIN = "";
		String sBICEFI = "";
		String sCASUCB = "";
		String sCASUCS = "";
		String sFEACON = "";
		String sIDAUTO = "";
		String sFEDEMA = "";
		String sYNOCUR = "";
		String sOBRECO = "";
		String sYNOLEC = "";
		String sNOLOJZ = "";
		String sFEREDE = "";
		String sPOPROP = "";
		String sCOGRAP = "";
		String sFEPREG = "";
		String sFEPHAC = "";
		String sFEFOAC = "";
		String sFEVACT = "";
		String sIMVACT = "";
		String sNUFIPR = "";
		String sCOTPET = "";
		String sFEEMPT = "";
		String sFESORC = "";
		String sFESODE = "";
		String sFEREAC = "";
		String sCOXSIA = "";
		String sNUJUZD = "";
		String sNURCAT = "";
		String sNOMPRC = "";
		String sNUTPRC = "";
		String sNOMADC = "";
		String sNUTADC = "";
		String sIMPCOO = "";
		String sCOENOR = "";
		String sCOSPAT = "";
		String sCOSPAS = "";
		String sIDCOL3 = "";
		String sBIOBNU = "";
		String sPOBRAR = "";
		String sFETIFI = "";

		if (conexion != null)
		{
			Statement stmt = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + CAMPO1  + ","
					   + CAMPO2  + ","              
				       + CAMPO3  + ","              
				       + CAMPO4  + ","              
				       + CAMPO5  + ","              
				       //+ CAMPO6  + ","              
				       + "CONVERT(AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO7  + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO8  + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO8 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO9  + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO10 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO11 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + CAMPO12 + ","              
				       //+ CAMPO13 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
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
				       //+ CAMPO28 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + CAMPO29 + ","              
				       //+ CAMPO30 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO30 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + CAMPO31 + ","              
				       + CAMPO32 + ","              
				       + CAMPO33 + ","              
				       + CAMPO34 + ","              
				       + CAMPO35 + ","              
				       + CAMPO36 + ","              
				       + CAMPO37 + ","              
				       + CAMPO38 + ","              
				       + CAMPO39 + ","              
				       + CAMPO40 + ","              
				       + CAMPO41 + ","              
				       + CAMPO42 + ","              
				       + CAMPO43 + ","              
				       + CAMPO44 + ","              
				       + CAMPO45 + ","              
				       + CAMPO46 + ","              
				       + CAMPO47 + ","              
				       + CAMPO48 + ","              
				       + CAMPO49 + ","              
				       + CAMPO50 + ","              
				       + CAMPO51 + ","              
				       + CAMPO52 + ","              
				       + CAMPO53 + ","              
				       + CAMPO54 + ","              
				       + CAMPO55 + ","              
				       + CAMPO56 + ","              
				       + CAMPO57 + ","              
				       + CAMPO58 + ","              
				       + CAMPO59 + ","              
				       + CAMPO60 + ","              
				       //+ CAMPO61 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO61 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO62 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO62 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO63 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO63 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO64 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO64 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + CAMPO65 + ","              
				       + CAMPO66 + ","              
				       + CAMPO67 + ","              
				       + CAMPO68 + ","              
				       + CAMPO69 + ","              
				       + CAMPO70 + ","              
				       + CAMPO71 + ","              
				       + CAMPO72 + ","              
				       + CAMPO73 + ","              
				       + CAMPO74 + ","              
				       + CAMPO75 + ","              
				       + CAMPO76 + ","              
				       + CAMPO77 + ","              
				       + CAMPO78 + ","              
				       + CAMPO79 + ","              
				       + CAMPO80 + ","              
				       + CAMPO81 + ","
				       //+ "CONVERT(AES_DECRYPT("+CAMPO81 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO82 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO82 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO83 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO83 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO84 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO84 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       //+ CAMPO85 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO85 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + CAMPO86 + ","              
				       + CAMPO87 + ","              
				       + CAMPO88 + ","              
				       + CAMPO89 + ","              
				       //+ CAMPO90 + ","
				       + "CONVERT(AES_DECRYPT("+CAMPO90 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
				       + CAMPO91 + ","
				       + CAMPO92 + ","
				       + CAMPO93 +        
				       " FROM " 
				       + TABLA + 
				       " WHERE "
				       + CAMPO1 + " = '" + iCOACES	+ "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con �xito!");

				logger.debug(CAMPO1+":|"+iCOACES+"|");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;

						sCOACES = rs.getString(CAMPO1);
						sNUINMU = rs.getString(CAMPO2);
						sCOSOPA = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSOPA, QMCodigosControl.ICOSOPA, rs.getString(CAMPO3));
						sCOENAE = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOENAE, QMCodigosControl.ICOENAE, rs.getString(CAMPO4));
						sCOESEN = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOESEN, QMCodigosControl.ICOESEN, rs.getString(CAMPO5));
						//sNOVIAS = rs.getString(CAMPO6);
						sNOVIAS = rs.getString("CONVERT(AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sNUPOAC = rs.getString(CAMPO7);
						sNUPOAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sNUESAC = rs.getString(CAMPO8);
						sNUESAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO8 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sNUPIAC = rs.getString(CAMPO9);
						sNUPIAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sNUPUAC = rs.getString(CAMPO10);
						sNUPUAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sNOMUIN = rs.getString(CAMPO11);
						sNOMUIN = rs.getString("CONVERT(AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						sCOPRAE = rs.getString(CAMPO12);
						//sNOPRAC = rs.getString(CAMPO13);
						sNOPRAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						sCOPOIN = rs.getString(CAMPO14);
						sFEREAP = rs.getString(CAMPO15);
						sCOREAE = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOREAE, QMCodigosControl.ICOREAE, rs.getString(CAMPO16));
						sFEINAU = rs.getString(CAMPO17);
						sFESOPO = rs.getString(CAMPO18);
						sFESEPO = rs.getString(CAMPO19);
						sFEREPO = rs.getString(CAMPO20);
						sFEADAC = rs.getString(CAMPO21);
						sCODIJU = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCODIJU, QMCodigosControl.ICODIJU, rs.getString(CAMPO22));
						sCOSJUP = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSJUP, QMCodigosControl.ICOSJUP, rs.getString(CAMPO23));
						sCOSTLI = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSTLI, QMCodigosControl.ICOSTLI, rs.getString(CAMPO24));
						sCOSCAR = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSCAR, QMCodigosControl.ICOSCAR, rs.getString(CAMPO25));
						sCOESVE = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOESVE, QMCodigosControl.ICOESVE, rs.getString(CAMPO26));
						sCOTSIN = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOTSIN, QMCodigosControl.ICOTSIN, rs.getString(CAMPO27));
						//sNUFIRE = rs.getString(CAMPO28);
						sNUFIRE = rs.getString("CONVERT(AES_DECRYPT("+CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						sNUREGP = rs.getString(CAMPO29);
						//sNOMUI0 = rs.getString(CAMPO30);
						sNOMUI0 = rs.getString("CONVERT(AES_DECRYPT("+CAMPO30 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						sNULIBE = rs.getString(CAMPO31);
						sNUTOME = rs.getString(CAMPO32);
						sNUFOLE = rs.getString(CAMPO33);
						sNUINSR = rs.getString(CAMPO34);
						sCOSOCU = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSOCU, QMCodigosControl.ICOSOCU, rs.getString(CAMPO35));
						sCOXPRO = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOXPRO, QMCodigosControl.ICOXPRO, rs.getString(CAMPO36));
						sFESOLA = rs.getString(CAMPO37);
						sFESELA = rs.getString(CAMPO38);
						sFERELA = rs.getString(CAMPO39);
						sFERLLA = rs.getString(CAMPO40);
						sCASPRE = rs.getString(CAMPO41);
						sCASUTR = rs.getString(CAMPO42);
						sCASUTC = rs.getString(CAMPO43);
						sCASUTG = rs.getString(CAMPO44);
						sBIARRE = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TBINARIA, QMCodigosControl.IBINARIA, rs.getString(CAMPO45));
						sCADORM = rs.getString(CAMPO46);
						sCABANO = rs.getString(CAMPO47);
						sBIGAPA = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TBINARIA, QMCodigosControl.IBINARIA, rs.getString(CAMPO48));
						sCAGAPA = rs.getString(CAMPO49);
						sCASUTE = rs.getString(CAMPO50);
						sBILIPO = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TBINARIA, QMCodigosControl.IBINARIA, rs.getString(CAMPO51));
						sBILIAC = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TBINARIA, QMCodigosControl.IBINARIA, rs.getString(CAMPO52));
						sBILIUS = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TBINARIA, QMCodigosControl.IBINARIA, rs.getString(CAMPO53));
						sBIBOIN = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TBINARIA, QMCodigosControl.IBINARIA, rs.getString(CAMPO54));
						sBICEFI = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TBINARIA, QMCodigosControl.IBINARIA, rs.getString(CAMPO55));
						sCASUCB = rs.getString(CAMPO56);
						sCASUCS = rs.getString(CAMPO57);
						sFEACON = rs.getString(CAMPO58);
						sIDAUTO = rs.getString(CAMPO59);
						sFEDEMA = rs.getString(CAMPO60);
						//sYNOCUR = rs.getString(CAMPO61);
						sYNOCUR = rs.getString("CONVERT(AES_DECRYPT("+CAMPO61 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sOBRECO = rs.getString(CAMPO62);
						sOBRECO = rs.getString("CONVERT(AES_DECRYPT("+CAMPO62 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sYNOLEC = rs.getString(CAMPO63);
						sYNOLEC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO63 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sNOLOJZ = rs.getString(CAMPO64);
						sNOLOJZ = rs.getString("CONVERT(AES_DECRYPT("+CAMPO64 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						sFEREDE = rs.getString(CAMPO65);
						sPOPROP = rs.getString(CAMPO66);
						sCOGRAP = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOGRAP, QMCodigosControl.ICOGRAP, rs.getString(CAMPO67));
						sFEPREG = rs.getString(CAMPO68);
						sFEPHAC = rs.getString(CAMPO69);
						sFEFOAC = rs.getString(CAMPO70);
						sFEVACT = rs.getString(CAMPO71);
						sIMVACT = rs.getString(CAMPO72);
						sNUFIPR = rs.getString(CAMPO73);
						sCOTPET = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOTPET, QMCodigosControl.ICOTPET, rs.getString(CAMPO74));
						sFEEMPT = rs.getString(CAMPO75);
						sFESORC = rs.getString(CAMPO76);
						sFESODE = rs.getString(CAMPO77);
						sFEREAC = rs.getString(CAMPO78);
						sCOXSIA = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOXSIA, QMCodigosControl.ICOXSIA, rs.getString(CAMPO79));
						sNUJUZD = rs.getString(CAMPO80);
						sNURCAT = rs.getString(CAMPO81);
						//sNURCAT = rs.getString("CONVERT(AES_DECRYPT("+CAMPO81 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sNOMPRC = rs.getString(CAMPO82);
						sNOMPRC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO82 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sNUTPRC = rs.getString(CAMPO83);
						sNUTPRC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO83 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sNOMADC = rs.getString(CAMPO84);
						sNOMADC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO84 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//sNUTADC = rs.getString(CAMPO85);
						sNUTADC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO85 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						sIMPCOO = rs.getString(CAMPO86);
						sCOENOR = rs.getString(CAMPO87);
						sCOSPAT = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSPAT, QMCodigosControl.ICOSPAT, rs.getString(CAMPO88));
						sCOSPAS = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSPAT, QMCodigosControl.ICOSPAT, rs.getString(CAMPO89));
						//sIDCOL3 = rs.getString(CAMPO90);
						sIDCOL3 = rs.getString("CONVERT(AES_DECRYPT("+CAMPO90 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						sBIOBNU = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TBIOBNU, QMCodigosControl.IBIOBNU, rs.getString(CAMPO91));
						sPOBRAR = rs.getString(CAMPO92);
						sFETIFI = rs.getString(CAMPO93);

						logger.debug("Encontrado el registro!");

					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la informaci�n.");
				}

			} 
			catch (SQLException ex) 
			{
				sCOACES = "";
				sNUINMU = "";
				sCOSOPA = "";
				sCOENAE = "";
				sCOESEN = "";
				sNOVIAS = "";
				sNUPOAC = "";
				sNUESAC = "";
				sNUPIAC = "";
				sNUPUAC = "";
				sNOMUIN = "";
				sCOPRAE = "";
				sNOPRAC = "";
				sCOPOIN = "";
				sFEREAP = "";
				sCOREAE = "";
				sFEINAU = "";
				sFESOPO = "";
				sFESEPO = "";
				sFEREPO = "";
				sFEADAC = "";
				sCODIJU = "";
				sCOSJUP = "";
				sCOSTLI = "";
				sCOSCAR = "";
				sCOESVE = "";
				sCOTSIN = "";
				sNUFIRE = "";
				sNUREGP = "";
				sNOMUI0 = "";
				sNULIBE = "";
				sNUTOME = "";
				sNUFOLE = "";
				sNUINSR = "";
				sCOSOCU = "";
				sCOXPRO = "";
				sFESOLA = "";
				sFESELA = "";
				sFERELA = "";
				sFERLLA = "";
				sCASPRE = "";
				sCASUTR = "";
				sCASUTC = "";
				sCASUTG = "";
				sBIARRE = "";
				sCADORM = "";
				sCABANO = "";
				sBIGAPA = "";
				sCAGAPA = "";
				sCASUTE = "";
				sBILIPO = "";
				sBILIAC = "";
				sBILIUS = "";
				sBIBOIN = "";
				sBICEFI = "";
				sCASUCB = "";
				sCASUCS = "";
				sFEACON = "";
				sIDAUTO = "";
				sFEDEMA = "";
				sYNOCUR = "";
				sOBRECO = "";
				sYNOLEC = "";
				sNOLOJZ = "";
				sFEREDE = "";
				sPOPROP = "";
				sCOGRAP = "";
				sFEPREG = "";
				sFEPHAC = "";
				sFEFOAC = "";
				sFEVACT = "";
				sIMVACT = "";
				sNUFIPR = "";
				sCOTPET = "";
				sFEEMPT = "";
				sFESORC = "";
				sFESODE = "";
				sFEREAC = "";
				sCOXSIA = "";
				sNUJUZD = "";
				sNURCAT = "";
				sNOMPRC = "";
				sNUTPRC = "";
				sNOMADC = "";
				sNUTADC = "";
				sIMPCOO = "";
				sCOENOR = "";
				sCOSPAT = "";
				sCOSPAS = "";
				sIDCOL3 = "";
				sBIOBNU = "";
				sPOBRAR = "";
				sFETIFI = "";
				
				logger.error("ERROR COACES:|"+iCOACES+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}
		

		return new Activo(sCOACES, sNUINMU, sCOSOPA, sCOENAE, sCOESEN, sNOVIAS,
				sNUPOAC, sNUESAC, sNUPIAC, sNUPUAC, sNOMUIN, sCOPRAE, sNOPRAC,
				sCOPOIN, sFEREAP, sCOREAE, sFEINAU, sFESOPO, sFESEPO, sFEREPO,
				sFEADAC, sCODIJU, sCOSJUP, sCOSTLI, sCOSCAR, sCOESVE, sCOTSIN,
				sNUFIRE, sNUREGP, sNOMUI0, sNULIBE, sNUTOME, sNUFOLE, sNUINSR,
				sCOSOCU, sCOXPRO, sFESOLA, sFESELA, sFERELA, sFERLLA, sCASPRE,
				sCASUTR, sCASUTC, sCASUTG, sBIARRE, sCADORM, sCABANO, sBIGAPA,
				sCAGAPA, sCASUTE, sBILIPO, sBILIAC, sBILIUS, sBIBOIN, sBICEFI,
				sCASUCB, sCASUCS, sFEACON, sIDAUTO, sFEDEMA, sYNOCUR, sOBRECO,
				sYNOLEC, sNOLOJZ, sFEREDE, sPOPROP, sCOGRAP, sFEPREG, sFEPHAC,
				sFEFOAC, sFEVACT, sIMVACT, sNUFIPR, sCOTPET, sFEEMPT, sFESORC,
				sFESODE, sFEREAC, sCOXSIA, sNUJUZD, sNURCAT, sNOMPRC, sNUTPRC,
				sNOMADC, sNUTADC, sIMPCOO, sCOENOR, sCOSPAT, sCOSPAS, sIDCOL3,
				sBIOBNU, sPOBRAR, sFETIFI);
	}
	

	public static boolean existeActivo(Connection conexion, int iCOACES)
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
					   + CAMPO1 + " = '" + iCOACES	+ "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con �xito!");

				

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
					logger.debug("No se encontro la informaci�n.");
				}

			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR COACES:|"+iCOACES+"|");

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
	
	public static boolean compruebaActivo(Connection conexion, Activo NuevoActivo)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			//logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO1  +
					" FROM " 
					+ TABLA + 
					" WHERE (" 
					+ CAMPO1  + " = '"+ NuevoActivo.getCOACES() + "' AND "
					+ CAMPO2  + " = '"+ NuevoActivo.getNUINMU() + "' AND "
					+ CAMPO3  + " = '"+ NuevoActivo.getCOSOPA() + "' AND "
					+ CAMPO4  + " = '"+ NuevoActivo.getCOENAE() + "' AND "
					+ CAMPO5  + " = '"+ NuevoActivo.getCOESEN() + "' AND "
					//+ CAMPO6  + " = '"+ NuevoActivo.getNOVIAS() + "' AND "
					+ CAMPO6  + " = AES_ENCRYPT('"+NuevoActivo.getNOVIAS()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					//+ CAMPO7  + " = '"+ NuevoActivo.getNUPOAC() + "' AND "
					+ CAMPO7  + " = AES_ENCRYPT('"+NuevoActivo.getNUPOAC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					//+ CAMPO8  + " = '"+ NuevoActivo.getNUESAC() + "' AND "
					+ CAMPO8  + " = AES_ENCRYPT('"+NuevoActivo.getNUESAC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					//+ CAMPO9  + " = '"+ NuevoActivo.getNUPIAC() + "' AND "
					+ CAMPO9  + " = AES_ENCRYPT('"+NuevoActivo.getNUPIAC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					//+ CAMPO10 + " = '"+ NuevoActivo.getNUPUAC() + "' AND "
					+ CAMPO10  + " = AES_ENCRYPT('"+NuevoActivo.getNUPUAC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					//+ CAMPO11 + " = '"+ NuevoActivo.getNOMUIN() + "' AND "
					+ CAMPO11  + " = AES_ENCRYPT('"+NuevoActivo.getNOMUIN()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					+ CAMPO12 + " = '"+ NuevoActivo.getCOPRAE() + "' AND "
					//+ CAMPO13 + " = '"+ NuevoActivo.getNOPRAC() + "' AND "
					+ CAMPO13  + " = AES_ENCRYPT('"+NuevoActivo.getNOPRAC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					+ CAMPO14 + " = '"+ NuevoActivo.getCOPOIN() + "' AND "
					+ CAMPO15 + " = '"+ NuevoActivo.getFEREAP() + "' AND "
					+ CAMPO16 + " = '"+ NuevoActivo.getCOREAE() + "' AND "
					+ CAMPO17 + " = '"+ NuevoActivo.getFEINAU() + "' AND "
					+ CAMPO18 + " = '"+ NuevoActivo.getFESOPO() + "' AND "
					+ CAMPO19 + " = '"+ NuevoActivo.getFESEPO() + "' AND "
					+ CAMPO20 + " = '"+ NuevoActivo.getFEREPO() + "' AND "
					+ CAMPO21 + " = '"+ NuevoActivo.getFEADAC() + "' AND "
					+ CAMPO22 + " = '"+ NuevoActivo.getCODIJU() + "' AND "
					+ CAMPO23 + " = '"+ NuevoActivo.getCOSJUP() + "' AND "
					+ CAMPO24 + " = '"+ NuevoActivo.getCOSTLI() + "' AND "
					+ CAMPO25 + " = '"+ NuevoActivo.getCOSCAR() + "' AND "
					+ CAMPO26 + " = '"+ NuevoActivo.getCOESVE() + "' AND "
					+ CAMPO27 + " = '"+ NuevoActivo.getCOTSIN() + "' AND "
					//+ CAMPO28 + " = '"+ NuevoActivo.getNUFIRE() + "' AND "
					+ CAMPO28  + " = AES_ENCRYPT('"+NuevoActivo.getNUFIRE()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					+ CAMPO29 + " = '"+ NuevoActivo.getNUREGP() + "' AND "
					//+ CAMPO30 + " = '"+ NuevoActivo.getNOMUI0() + "' AND "
					+ CAMPO30  + " = AES_ENCRYPT('"+NuevoActivo.getNOMUI0()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					+ CAMPO31 + " = '"+ NuevoActivo.getNULIBE() + "' AND "
					+ CAMPO32 + " = '"+ NuevoActivo.getNUTOME() + "' AND "
					+ CAMPO33 + " = '"+ NuevoActivo.getNUFOLE() + "' AND "
					+ CAMPO34 + " = '"+ NuevoActivo.getNUINSR() + "' AND "
					+ CAMPO35 + " = '"+ NuevoActivo.getCOSOCU() + "' AND "
					+ CAMPO36 + " = '"+ NuevoActivo.getCOXPRO() + "' AND "
					+ CAMPO37 + " = '"+ NuevoActivo.getFESOLA() + "' AND "
					+ CAMPO38 + " = '"+ NuevoActivo.getFESELA() + "' AND "
					+ CAMPO39 + " = '"+ NuevoActivo.getFERELA() + "' AND "
					+ CAMPO40 + " = '"+ NuevoActivo.getFERLLA() + "' AND "
					+ CAMPO41 + " = '"+ NuevoActivo.getCASPRE() + "' AND "
					+ CAMPO42 + " = '"+ NuevoActivo.getCASUTR() + "' AND "
					+ CAMPO43 + " = '"+ NuevoActivo.getCASUTC() + "' AND "
					+ CAMPO44 + " = '"+ NuevoActivo.getCASUTG() + "' AND "
					+ CAMPO45 + " = '"+ NuevoActivo.getBIARRE() + "' AND "
					+ CAMPO46 + " = '"+ NuevoActivo.getCADORM() + "' AND "
					+ CAMPO47 + " = '"+ NuevoActivo.getCABANO() + "' AND "
					+ CAMPO48 + " = '"+ NuevoActivo.getBIGAPA() + "' AND "
					+ CAMPO49 + " = '"+ NuevoActivo.getCAGAPA() + "' AND "
					+ CAMPO50 + " = '"+ NuevoActivo.getCASUTE() + "' AND "
					+ CAMPO51 + " = '"+ NuevoActivo.getBILIPO() + "' AND "
					+ CAMPO52 + " = '"+ NuevoActivo.getBILIAC() + "' AND "
					+ CAMPO53 + " = '"+ NuevoActivo.getBILIUS() + "' AND "
					+ CAMPO54 + " = '"+ NuevoActivo.getBIBOIN() + "' AND "
					+ CAMPO55 + " = '"+ NuevoActivo.getBICEFI() + "' AND "
					+ CAMPO56 + " = '"+ NuevoActivo.getCASUCB() + "' AND "
					+ CAMPO57 + " = '"+ NuevoActivo.getCASUCS() + "' AND "
					+ CAMPO58 + " = '"+ NuevoActivo.getFEACON() + "' AND "
					+ CAMPO59 + " = '"+ NuevoActivo.getIDAUTO() + "' AND "
					+ CAMPO60 + " = '"+ NuevoActivo.getFEDEMA() + "' AND "
					//+ CAMPO61 + " = '"+ NuevoActivo.getYNOCUR() + "' AND "
					+ CAMPO61  + " = AES_ENCRYPT('"+NuevoActivo.getYNOCUR()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					//+ CAMPO62 + " = '"+ NuevoActivo.getOBRECO() + "' AND "
					+ CAMPO62  + " = AES_ENCRYPT('"+NuevoActivo.getOBRECO()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					//+ CAMPO63 + " = '"+ NuevoActivo.getYNOLEC() + "' AND "
					+ CAMPO63  + " = AES_ENCRYPT('"+NuevoActivo.getYNOLEC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					//+ CAMPO64 + " = '"+ NuevoActivo.getNOLOJZ() + "' AND "
					+ CAMPO64  + " = AES_ENCRYPT('"+NuevoActivo.getNOLOJZ()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					+ CAMPO65 + " = '"+ NuevoActivo.getFEREDE() + "' AND "
					+ CAMPO66 + " = '"+ NuevoActivo.getPOPROP() + "' AND "
					+ CAMPO67 + " = '"+ NuevoActivo.getCOGRAP() + "' AND "
					+ CAMPO68 + " = '"+ NuevoActivo.getFEPREG() + "' AND "
					+ CAMPO69 + " = '"+ NuevoActivo.getFEPHAC() + "' AND "
					+ CAMPO70 + " = '"+ NuevoActivo.getFEFOAC() + "' AND "
					+ CAMPO71 + " = '"+ NuevoActivo.getFEVACT() + "' AND "
					+ CAMPO72 + " = '"+ NuevoActivo.getIMVACT() + "' AND "
					+ CAMPO73 + " = '"+ NuevoActivo.getNUFIPR() + "' AND "
					+ CAMPO74 + " = '"+ NuevoActivo.getCOTPET() + "' AND "
					+ CAMPO75 + " = '"+ NuevoActivo.getFEEMPT() + "' AND "
					+ CAMPO76 + " = '"+ NuevoActivo.getFESORC() + "' AND "
					+ CAMPO77 + " = '"+ NuevoActivo.getFESODE() + "' AND "
					+ CAMPO78 + " = '"+ NuevoActivo.getFEREAC() + "' AND "
					+ CAMPO79 + " = '"+ NuevoActivo.getCOXSIA() + "' AND "
					+ CAMPO80 + " = '"+ NuevoActivo.getNUJUZD() + "' AND "
					+ CAMPO81 + " = '"+ NuevoActivo.getNURCAT() + "' AND "
					//+ CAMPO81  + " = AES_ENCRYPT('"+NuevoActivo.getNURCAT()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					//+ CAMPO82 + " = '"+ NuevoActivo.getNOMPRC() + "' AND "
					+ CAMPO82  + " = AES_ENCRYPT('"+NuevoActivo.getNOMPRC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					//+ CAMPO83 + " = '"+ NuevoActivo.getNUTPRC() + "' AND "
					+ CAMPO83  + " = AES_ENCRYPT('"+NuevoActivo.getNUTPRC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					//+ CAMPO84 + " = '"+ NuevoActivo.getNOMADC() + "' AND "
					+ CAMPO84  + " = AES_ENCRYPT('"+NuevoActivo.getNOMADC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					//+ CAMPO85 + " = '"+ NuevoActivo.getNUTADC() + "' AND "
					+ CAMPO85  + " = AES_ENCRYPT('"+NuevoActivo.getNUTADC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					+ CAMPO86 + " = '"+ NuevoActivo.getIMPCOO() + "' AND "
					+ CAMPO87 + " = '"+ NuevoActivo.getCOENOR() + "' AND "
					+ CAMPO88 + " = '"+ NuevoActivo.getCOSPAT() + "' AND "
					+ CAMPO89 + " = '"+ NuevoActivo.getCOSPAS() + "' AND "
					//+ CAMPO90 + " = '"+ NuevoActivo.getIDCOL3() + "' AND "
					+ CAMPO90  + " = AES_ENCRYPT('"+NuevoActivo.getIDCOL3()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND "
					+ CAMPO91 + " = '"+ NuevoActivo.getBIOBNU() + "' AND "
					+ CAMPO92 + " = '"+ NuevoActivo.getPOBRAR() + "' AND "
					+ CAMPO93 + " = '"+ NuevoActivo.getFETIFI() + "' )";
			
			//logger.debug(sQuery);
			
			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				//logger.debug("Ejecutada con �xito!");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;

						//logger.debug("Encontrado el registro!");
						//logger.debug(CAMPO1+":|"+rs.getString(CAMPO1)+"|");
					}
				}
				if (!bEncontrado) 
				{
					//logger.debug("No se encontro la informaci�n.");
				}
						
			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR COACES:|"+NuevoActivo.getCOACES()+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bEncontrado;
	}

	public static String getReferenciaCatastral(Connection conexion, int iCOACES)
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
						+ CAMPO81  +
						//+ "CONVERT(AES_DECRYPT("+CAMPO81 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) "+
						" FROM " 
						+ TABLA + 
						" WHERE " 
						+ CAMPO1 + " = '" + iCOACES	+ "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con �xito!");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;
						
						sReferencia = rs.getString(CAMPO81);
						//sReferencia = rs.getString("CONVERT(AES_DECRYPT("+CAMPO81 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO81+":|"+sReferencia+"|");

					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la informaci�n.");
				}

			} 
			catch (SQLException ex) 
			{
				sReferencia = "";

				logger.error("ERROR COACES:|"+iCOACES+"|");

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
	
	public static String getCOTSINActivo(Connection conexion, int iCOACES)
	{
		String sCOTSIN = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + CAMPO27  +        
					   " FROM " 
					   + TABLA + 
					   " WHERE " 
					   + CAMPO1 + " = '" + iCOACES	+ "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con �xito!");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;
						
						sCOTSIN = rs.getString(CAMPO27);

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO27+":|"+sCOTSIN+"|");

					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la informaci�n.");
				}

			} 
			catch (SQLException ex) 
			{
				sCOTSIN = "";
				
				logger.error("ERROR COACES:|"+iCOACES+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sCOTSIN;
	}

	public static String getBIARREActivo(Connection conexion, int iCOACES)
	{
		String sBIARRE = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + CAMPO45  +        
					   " FROM " 
					   + TABLA + 
					   " WHERE " 
					   + CAMPO1 + " = '" + iCOACES + "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con �xito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sBIARRE = rs.getString(CAMPO45);

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO45+":|"+sBIARRE+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la informaci�n.");
				}
			} 
			catch (SQLException ex) 
			{
				sBIARRE = "";

				logger.error("ERROR COACES:|"+iCOACES+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sBIARRE;
	}
	
	public static String getFEVACTActivo(Connection conexion, int iCOACES)
	{
		String sFEVACT = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + CAMPO71  +        
					   " FROM " 
					   + TABLA + 
					   " WHERE " 
					   + CAMPO1 + " = '" + iCOACES + "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con �xito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sFEVACT = rs.getString(CAMPO71);

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO71+":|"+sFEVACT+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la informaci�n.");
				}
			} 
			catch (SQLException ex) 
			{
				sFEVACT = "";

				logger.error("ERROR COACES:|"+iCOACES+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sFEVACT;
	}
	
	public static String getFEADACActivo(Connection conexion, int iCOACES)
	{
		String sFEADAC = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + CAMPO21  +        
					   " FROM " 
					   + TABLA + 
					   " WHERE " 
					   + CAMPO1 + " = '" + iCOACES + "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con �xito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sFEADAC = rs.getString(CAMPO21);

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO21+":|"+sFEADAC+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la informaci�n.");
				}
			} 
			catch (SQLException ex) 
			{
				sFEADAC = "";

				logger.error("ERROR COACES:|"+iCOACES+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sFEADAC;
	}
	
	public static String getSociedadPatrimonial(Connection conexion, int iCOACES)
	{
		String sCodCOSPAT = "0";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + CAMPO88  +        
					   " FROM " 
					   + TABLA + 
					   " WHERE " 
					   + CAMPO1 + " = '" + iCOACES + "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con �xito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						sCodCOSPAT = rs.getString(CAMPO88);

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO88+":|"+sCodCOSPAT+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la informaci�n.");
				}
			} 
			catch (SQLException ex) 
			{
				sCodCOSPAT = "0";

				logger.error("ERROR COACES:|"+iCOACES+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sCodCOSPAT;
	}
	
	public static ArrayList<ActivoTabla> buscaListaActivos(Connection conexion, ActivoTabla filtro)
	{
		ArrayList<ActivoTabla> resultado = new ArrayList<ActivoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			//Condiciones Filtro
			String sCondicionCOPOIN = filtro.getCOPOIN().isEmpty()?"":CAMPO14 + " LIKE '%" + filtro.getCOPOIN()	+ "%' ";
			//String sCondicionNOMUIN = filtro.getNOMUIN().isEmpty()?"":CAMPO11 + " LIKE '%" + filtro.getNOMUIN()	+ "%' AND ";
			//String sCondicionNOPRAC = filtro.getNOPRAC().isEmpty()?"":CAMPO13 + " LIKE '%" + filtro.getNOPRAC()	+ "%' AND ";
			//String sCondicionNOVIAS = filtro.getNOVIAS().isEmpty()?"":CAMPO6 + " LIKE '%" + filtro.getNOVIAS()	+ "%' AND ";
			//String sCondicionNUPIAC = filtro.getNUPIAC().isEmpty()?"":CAMPO9 + " LIKE '%" + filtro.getNUPIAC()	+ "%' AND ";
			//String sCondicionNUPOAC = filtro.getNUPOAC().isEmpty()?"":CAMPO7 + " LIKE '%" + filtro.getNUPOAC()	+ "%' AND ";
			//String sCondicionNUPUAC = filtro.getNUPUAC().isEmpty()?"":CAMPO10 + " LIKE '%" + filtro.getNUPUAC()	+ "%' AND ";
			//String sCondicionNUFIRE = filtro.getNUFIRE().isEmpty()?"":CAMPO28 + " LIKE '%" + filtro.getNUFIRE()	+ "%' AND ";

			String sCondiciones = sCondicionCOPOIN;
			
			//String sCondicionNOMUIN = filtro.getNOMUIN().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"INSTR(AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'" + filtro.getNOMUIN() + "') > 0 ";
			String sCondicionNOMUIN = filtro.getNOMUIN().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"CONVERT(AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOMUIN() + "%' ";
			sCondiciones = sCondiciones + sCondicionNOMUIN;
		
			//String sCondicionNOPRAC = filtro.getNOPRAC().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"INSTR(AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'" + filtro.getNOPRAC() + "') > 0 ";
			String sCondicionNOPRAC = filtro.getNOPRAC().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"CONVERT(AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOPRAC() + "%' ";
			sCondiciones = sCondiciones + sCondicionNOPRAC;

			//String sCondicionNOVIAS = filtro.getNOVIAS().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"INSTR(AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'" + filtro.getNOVIAS() + "') > 0 ";
			String sCondicionNOVIAS = filtro.getNOVIAS().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"CONVERT(AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOVIAS() + "%' ";
			sCondiciones = sCondiciones + sCondicionNOVIAS;

			//String sCondicionNUPIAC = filtro.getNUPIAC().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"INSTR(AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'" + filtro.getNUPIAC() + "') > 0 ";
			String sCondicionNUPIAC = filtro.getNUPIAC().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"CONVERT(AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPIAC() + "%' ";
			sCondiciones = sCondiciones + sCondicionNUPIAC;

			//String sCondicionNUPOAC = filtro.getNUPOAC().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"INSTR(AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'" + filtro.getNUPOAC() + "') > 0 ";
			String sCondicionNUPOAC = filtro.getNUPOAC().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"CONVERT(AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPOAC() + "%' ";
			sCondiciones = sCondiciones + sCondicionNUPOAC;

			//String sCondicionNUPUAC = filtro.getNUPUAC().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"INSTR(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'" + filtro.getNUPUAC() + "') > 0 ";
			String sCondicionNUPUAC = filtro.getNUPUAC().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"CONVERT(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPUAC() + "%' ";
			sCondiciones = sCondiciones + sCondicionNUPUAC;

			//String sCondicionNUFIRE = filtro.getNUFIRE().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"INSTR(AES_DECRYPT("+CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'" + filtro.getNUFIRE() + "') > 0 ";
			String sCondicionNUFIRE = filtro.getNUFIRE().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"CONVERT(AES_DECRYPT("+CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUFIRE() + "%' ";
			sCondiciones = sCondiciones + sCondicionNUFIRE;
			
			String sCondicionesWHERE = sCondiciones.isEmpty()?"":" WHERE ("+sCondiciones+")";
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + CAMPO1 + ","        
					   + CAMPO14 + ","
					   //+ CAMPO11 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO13 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO6 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO9 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO7 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO10 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO28 +
					   + "CONVERT(AES_DECRYPT("+CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) "+
					   " FROM " 
					   + TABLA +
					   sCondicionesWHERE;
					   //" WHERE ("
					   //+ sCondicionCOPOIN  
					   //+ sCondicionNOMUIN  
					   //+ sCondicionNOPRAC  
					   //+ sCondicionNOVIAS 
					   //+ sCondicionNUPIAC  
					   //+ sCondicionNUPOAC
					   //+ sCondicionNUFIRE
					   //+ CAMPO10 + " LIKE '%" + filtro.getNUPUAC()	+ "%')"
					   //+ " INSTR(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'" + filtro.getNUPUAC() + "') > 0)";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con �xito!");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;
						
						String sCOACES = rs.getString(CAMPO1);
						String sCOPOIN = rs.getString(CAMPO14);
						//String sNOMUIN = rs.getString(CAMPO11);
						String sNOMUIN = rs.getString("CONVERT(AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						
						//String sNOPRAC = rs.getString(CAMPO13);
						String sNOPRAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNOVIAS = rs.getString(CAMPO6);
						//String sNOVIAS = rs.getString("AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						String sNOVIAS = rs.getString("CONVERT(AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPIAC = rs.getString(CAMPO9);
						String sNUPIAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPOAC = rs.getString(CAMPO7);
						String sNUPOAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPUAC = rs.getString(CAMPO10);
						String sNUPUAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUFIRE = rs.getString(CAMPO28);
						String sNUFIRE = rs.getString("CONVERT(AES_DECRYPT("+CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						
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
						
						//logger.debug( "Encontrado el registro!");
						//logger.debug(CAMPO1+":|"+sCOACES+"|");
					}
				}
				if (!bEncontrado) 
				{

					logger.info("No se encontro la informaci�n.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ActivoTabla>();

				logger.error("ERROR COPOIN:|"+filtro.getCOPOIN()+"|");
				logger.error("ERROR NOMUIN:|"+filtro.getNOMUIN()+"|");
				logger.error("ERROR NOPRAC:|"+filtro.getNOPRAC()+"|");
				logger.error("ERROR NOVIAS:|"+filtro.getNOVIAS()+"|");
				logger.error("ERROR NUPIAC:|"+filtro.getNUPIAC()+"|");
				logger.error("ERROR NUPOAC:|"+filtro.getNUPOAC()+"|");
				logger.error("ERROR NUPUAC:|"+filtro.getNUPUAC()+"|");
				logger.error("ERROR NUFIRE:|"+filtro.getNUFIRE()+"|");
				

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
	
	public static ArrayList<ActivoTabla> buscaListaActivosPorVendido(Connection conexion, ActivoTabla filtro, boolean bVendido)
	{
		ArrayList<ActivoTabla> resultado = new ArrayList<ActivoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;



			//Condiciones Filtro
			String sCondicionCOPOIN = filtro.getCOPOIN().isEmpty()?"":QMActivos.CAMPO14 + " LIKE '%" + filtro.getCOPOIN()	+ "%' AND ";
			String sCondicionNOMUIN = filtro.getNOMUIN().isEmpty()?"":"CONVERT(AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOMUIN() + "%' AND ";
			String sCondicionNOPRAC = filtro.getNOPRAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOPRAC() + "%' AND ";
			String sCondicionNOVIAS = filtro.getNOVIAS().isEmpty()?"":"CONVERT(AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOVIAS() + "%' AND ";
			String sCondicionNUPIAC = filtro.getNUPIAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPIAC() + "%' AND ";
			String sCondicionNUPOAC = filtro.getNUPOAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPOAC() + "%' AND ";
			String sCondicionNUPUAC = filtro.getNUPUAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPUAC() + "%' AND ";
			String sCondicionNUFIRE = filtro.getNUFIRE().isEmpty()?"":"CONVERT(AES_DECRYPT("+CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUFIRE() + "%' AND ";

			
			String sCondicionVendido = CAMPO71 + (bVendido?" <> ":" = ") +ValoresDefecto.CAMPO_NUME_SIN_INFORMAR;
			
			//TODO
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + CAMPO1 + ","        
					   + CAMPO14 + ","
					   //+ CAMPO11 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO13 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO6 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO9 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO7 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO10 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO28 +
					   + "CONVERT(AES_DECRYPT("+CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) "+
					   " FROM " 
					   + TABLA +
					   " WHERE ("
					   + sCondicionCOPOIN  
					   + sCondicionNOMUIN
					   + sCondicionNOPRAC  
					   + sCondicionNOVIAS  
					   + sCondicionNUPIAC
					   + sCondicionNUPOAC 
					   + sCondicionNUPUAC
					   + sCondicionNUFIRE
					   + sCondicionVendido
					   +")";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con �xito!");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;

						String sCOACES = rs.getString(CAMPO1);
						String sCOPOIN = rs.getString(CAMPO14);

						//String sNOMUIN = rs.getString(CAMPO11);
						String sNOMUIN = rs.getString("CONVERT(AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNOPRAC = rs.getString(CAMPO13);
						String sNOPRAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNOVIAS = rs.getString(CAMPO6);
						String sNOVIAS = rs.getString("CONVERT(AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPIAC = rs.getString(CAMPO9);
						String sNUPIAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPOAC = rs.getString(CAMPO7);
						String sNUPOAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPUAC = rs.getString(CAMPO10);
						String sNUPUAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUFIRE = rs.getString(CAMPO28);
						String sNUFIRE = rs.getString("CONVERT(AES_DECRYPT("+CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						
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
						
					}
				}
				if (!bEncontrado) 
				{

					logger.info("No se encontro la informaci�n.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ActivoTabla>();

				logger.error("ERROR COPOIN:|"+filtro.getCOPOIN()+"|");
				logger.error("ERROR NOMUIN:|"+filtro.getNOMUIN()+"|");
				logger.error("ERROR NOPRAC:|"+filtro.getNOPRAC()+"|");
				logger.error("ERROR NOVIAS:|"+filtro.getNOVIAS()+"|");
				logger.error("ERROR NUPIAC:|"+filtro.getNUPIAC()+"|");
				logger.error("ERROR NUPOAC:|"+filtro.getNUPOAC()+"|");
				logger.error("ERROR NUPUAC:|"+filtro.getNUPUAC()+"|");
				logger.error("ERROR NUFIRE:|"+filtro.getNUFIRE()+"|");

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
	
	public static ArrayList<ActivoTabla> buscaListaActivosPorInmovilizado(Connection conexion, ActivoTabla filtro, boolean bInmovilizado)
	{
		ArrayList<ActivoTabla> resultado = new ArrayList<ActivoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			//Condiciones Filtro
			String sCondicionCOPOIN = filtro.getCOPOIN().isEmpty()?"":CAMPO14 + " LIKE '%" + filtro.getCOPOIN()	+ "%' AND ";

			String sCondicionNOMUIN = filtro.getNOMUIN().isEmpty()?"":"CONVERT(AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOMUIN() + "%' AND ";
			String sCondicionNOPRAC = filtro.getNOPRAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOPRAC() + "%' AND ";
			String sCondicionNOVIAS = filtro.getNOVIAS().isEmpty()?"":"CONVERT(AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOVIAS() + "%' AND ";
			String sCondicionNUPIAC = filtro.getNUPIAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPIAC() + "%' AND ";
			String sCondicionNUPOAC = filtro.getNUPOAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPOAC() + "%' AND ";
			String sCondicionNUPUAC = filtro.getNUPUAC().isEmpty()?"":"CONVERT(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPUAC() + "%' AND ";
			String sCondicionNUFIRE = filtro.getNUFIRE().isEmpty()?"":"CONVERT(AES_DECRYPT("+CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUFIRE() + "%' AND ";			

			//String sInmovilizado = CAMPO2 + (bInmovilizado ?  " <> '"+ ValoresDefecto.CAMPO_NUME_SIN_INFORMAR : " = '"+ ValoresDefecto.CAMPO_NUME_SIN_INFORMAR)+ "' ";
			String sInmovilizado = CAMPO2 + (bInmovilizado?" <> ":" = ") +ValoresDefecto.CAMPO_NUME_SIN_INFORMAR;
			
			String sQuery = "SELECT "
					   + CAMPO1 + ","        
					   + CAMPO14 + ","
					   //+ CAMPO11 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO13 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO6 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO9 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO7 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO10 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO28 +
					   + "CONVERT(AES_DECRYPT("+CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) "+
					   " FROM " 
					   + TABLA + 
					   " WHERE ("
					   
					   + sCondicionCOPOIN  
					   + sCondicionNOMUIN  
					   + sCondicionNOPRAC  
					   + sCondicionNOVIAS 
					   + sCondicionNUPIAC  
					   + sCondicionNUPOAC
					   + sCondicionNUFIRE
					   +sCondicionNUPUAC
					   + sInmovilizado + ")";
					   //+ CAMPO10 + " LIKE '%" + filtro.getNUPUAC()	+ "%')";
					   //+ " INSTR(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'" + filtro.getNUPUAC() + "') > 0)";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con �xito!");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;
						
						String sCOACES = rs.getString(CAMPO1);
						String sCOPOIN = rs.getString(CAMPO14);
						//String sNOMUIN = rs.getString(CAMPO11);
						String sNOMUIN = rs.getString("CONVERT(AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNOPRAC = rs.getString(CAMPO13);
						String sNOPRAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNOVIAS = rs.getString(CAMPO6);
						String sNOVIAS = rs.getString("CONVERT(AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPIAC = rs.getString(CAMPO9);
						String sNUPIAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPOAC = rs.getString(CAMPO7);
						String sNUPOAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPUAC = rs.getString(CAMPO10);
						String sNUPUAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUFIRE = rs.getString(CAMPO28);
						String sNUFIRE = rs.getString("CONVERT(AES_DECRYPT("+CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						
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
						
						//logger.debug( "Encontrado el registro!");
						//logger.debug(CAMPO1+":|"+sCOACES+"|");
					}
				}
				if (!bEncontrado) 
				{

					logger.info("No se encontro la informaci�n.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ActivoTabla>();

				logger.error("ERROR COPOIN:|"+filtro.getCOPOIN()+"|");
				logger.error("ERROR NOMUIN:|"+filtro.getNOMUIN()+"|");
				logger.error("ERROR NOPRAC:|"+filtro.getNOPRAC()+"|");
				logger.error("ERROR NOVIAS:|"+filtro.getNOVIAS()+"|");
				logger.error("ERROR NUPIAC:|"+filtro.getNUPIAC()+"|");
				logger.error("ERROR NUPOAC:|"+filtro.getNUPOAC()+"|");
				logger.error("ERROR NUPUAC:|"+filtro.getNUPUAC()+"|");
				logger.error("ERROR NUFIRE:|"+filtro.getNUFIRE()+"|");
				

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
	
	public static ArrayList<ActivoTabla> buscaActivoPorCOACES(Connection conexion, int iCOACES)
	{
		ArrayList<ActivoTabla> resultado = new ArrayList<ActivoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + CAMPO1 + ","        
					   + CAMPO14 + ","
					   //+ CAMPO11 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO13 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO6 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO9 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO7 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO10 + ","
					   + "CONVERT(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
					   //+ CAMPO28 +
					   + "CONVERT(AES_DECRYPT("+CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) "+
					   " FROM " 
					   + TABLA + 
					   " WHERE "

					   + CAMPO1 + " = '" + iCOACES	+"'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con �xito!");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;
						
						String sCOACES = rs.getString(CAMPO1);
						String sCOPOIN = rs.getString(CAMPO14);
						//String sNOMUIN = rs.getString(CAMPO11);
						String sNOMUIN = rs.getString("CONVERT(AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNOPRAC = rs.getString(CAMPO13);
						String sNOPRAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNOVIAS = rs.getString(CAMPO6);
						String sNOVIAS = rs.getString("CONVERT(AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPIAC = rs.getString(CAMPO9);
						String sNUPIAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPOAC = rs.getString(CAMPO7);
						String sNUPOAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPUAC = rs.getString(CAMPO10);
						String sNUPUAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUFIRE = rs.getString(CAMPO28);
						String sNUFIRE = rs.getString("CONVERT(AES_DECRYPT("+CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						
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
						
						//logger.debug( "Encontrado el registro!");
						//logger.debug(CAMPO1+":|"+sCOACES+"|");
					}
				}
				if (!bEncontrado) 
				{

					logger.info("No se encontro la informaci�n.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ActivoTabla>();

				logger.error("ERROR ACTIVO:|"+iCOACES+"|");

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
	
	public static ArrayList<ActivoTabla> buscaListaActivosReferencias(Connection conexion, ActivoTabla filtro)
	{
		ArrayList<ActivoTabla> resultado = new ArrayList<ActivoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;			

			boolean bEncontrado = false;

			//Condiciones Filtro
			String sCondicionCOPOIN = filtro.getCOPOIN().isEmpty()?"":CAMPO14 + " LIKE '%" + filtro.getCOPOIN()	+ "%' ";
			//String sCondicionNOMUIN = filtro.getNOMUIN().isEmpty()?"":CAMPO11 + " LIKE '%" + filtro.getNOMUIN()	+ "%' AND ";
			//String sCondicionNOPRAC = filtro.getNOPRAC().isEmpty()?"":CAMPO13 + " LIKE '%" + filtro.getNOPRAC()	+ "%' AND ";
			//String sCondicionNOVIAS = filtro.getNOVIAS().isEmpty()?"":CAMPO6 + " LIKE '%" + filtro.getNOVIAS()	+ "%' AND ";
			//String sCondicionNUPIAC = filtro.getNUPIAC().isEmpty()?"":CAMPO9 + " LIKE '%" + filtro.getNUPIAC()	+ "%' AND ";
			//String sCondicionNUPOAC = filtro.getNUPOAC().isEmpty()?"":CAMPO7 + " LIKE '%" + filtro.getNUPOAC()	+ "%' AND ";
			//String sCondicionNUPUAC = filtro.getNUPUAC().isEmpty()?"":CAMPO10 + " LIKE '%" + filtro.getNUPUAC()	+ "%' AND ";
			//String sCondicionNUFIRE = filtro.getNUFIRE().isEmpty()?"":CAMPO28 + " LIKE '%" + filtro.getNUFIRE()	+ "%' AND ";

			String sCondiciones = sCondicionCOPOIN;
			
			//String sCondicionNOMUIN = filtro.getNOMUIN().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"INSTR(AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'" + filtro.getNOMUIN() + "') > 0 ";
			String sCondicionNOMUIN = filtro.getNOMUIN().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"CONVERT(AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOMUIN() + "%' ";
			sCondiciones = sCondiciones + sCondicionNOMUIN;
		
			//String sCondicionNOPRAC = filtro.getNOPRAC().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"INSTR(AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'" + filtro.getNOPRAC() + "') > 0 ";
			String sCondicionNOPRAC = filtro.getNOPRAC().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"CONVERT(AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOPRAC() + "%' ";
			sCondiciones = sCondiciones + sCondicionNOPRAC;

			//String sCondicionNOVIAS = filtro.getNOVIAS().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"INSTR(AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'" + filtro.getNOVIAS() + "') > 0 ";
			String sCondicionNOVIAS = filtro.getNOVIAS().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"CONVERT(AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNOVIAS() + "%' ";
			sCondiciones = sCondiciones + sCondicionNOVIAS;

			//String sCondicionNUPIAC = filtro.getNUPIAC().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"INSTR(AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'" + filtro.getNUPIAC() + "') > 0 ";
			String sCondicionNUPIAC = filtro.getNUPIAC().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"CONVERT(AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPIAC() + "%' ";
			sCondiciones = sCondiciones + sCondicionNUPIAC;

			//String sCondicionNUPOAC = filtro.getNUPOAC().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"INSTR(AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'" + filtro.getNUPOAC() + "') > 0 ";
			String sCondicionNUPOAC = filtro.getNUPOAC().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"CONVERT(AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPOAC() + "%' ";
			sCondiciones = sCondiciones + sCondicionNUPOAC;

			//String sCondicionNUPUAC = filtro.getNUPUAC().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"INSTR(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'" + filtro.getNUPUAC() + "') > 0 ";
			String sCondicionNUPUAC = filtro.getNUPUAC().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"CONVERT(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUPUAC() + "%' ";
			sCondiciones = sCondiciones + sCondicionNUPUAC;

			//String sCondicionNUFIRE = filtro.getNUFIRE().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"INSTR(AES_DECRYPT("+CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'" + filtro.getNUFIRE() + "') > 0 ";
			String sCondicionNUFIRE = filtro.getNUFIRE().isEmpty()?"":(sCondiciones.isEmpty()?"":"AND ")+"CONVERT(AES_DECRYPT("+CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) LIKE '%" + filtro.getNUFIRE() + "%' ";
			sCondiciones = sCondiciones + sCondicionNUFIRE;
			
			String sCondicionesWHERE = sCondiciones.isEmpty()?"":" WHERE ("+sCondiciones+")";
		
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						
						   + CAMPO1 + ","        
						   + CAMPO14 + ","
						   //+ CAMPO11 + ","
						   + "CONVERT(AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ CAMPO13 + ","
						   + "CONVERT(AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ CAMPO6 + ","
						   + "CONVERT(AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ CAMPO9 + ","
						   + "CONVERT(AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ CAMPO7 + ","
						   + "CONVERT(AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ CAMPO10 + ","
						   + "CONVERT(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   //+ CAMPO28 +
						   + "CONVERT(AES_DECRYPT("+CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   + CAMPO81 +
						   //+ "CONVERT(AES_DECRYPT("+CAMPO81 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1) " +
						   " FROM " 
						   + TABLA + 
						   sCondicionesWHERE;
						   //" WHERE ("
						   //+ sCondicionCOPOIN  
						   //+ sCondicionNOMUIN  
						   //+ sCondicionNOPRAC  
						   //+ sCondicionNOVIAS 
						   //+ sCondicionNUPIAC  
						   //+ sCondicionNUPOAC
						   //+ sCondicionNUFIRE
						   //+ CAMPO10 + " LIKE '%" + filtro.getNUPUAC()	+ "%' )";
						   //+ " INSTR(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'" + filtro.getNUPUAC() + "') > 0)";
			
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
						String sCOPOIN = rs.getString(CAMPO14);
						//String sNOMUIN = rs.getString(CAMPO11);
						String sNOMUIN = rs.getString("CONVERT(AES_DECRYPT("+CAMPO11 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNOPRAC = rs.getString(CAMPO13);
						String sNOPRAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO13 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNOVIAS = rs.getString(CAMPO6);
						String sNOVIAS = rs.getString("CONVERT(AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPIAC = rs.getString(CAMPO9);
						String sNUPIAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPOAC = rs.getString(CAMPO7);
						String sNUPOAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUPUAC = rs.getString(CAMPO10);
						String sNUPUAC = rs.getString("CONVERT(AES_DECRYPT("+CAMPO10 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						//String sNUFIRE = rs.getString(CAMPO28);
						String sNUFIRE = rs.getString("CONVERT(AES_DECRYPT("+CAMPO28 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						String sNURCAT = rs.getString(CAMPO81);
						//String sNURCAT = rs.getString("CONVERT(AES_DECRYPT("+CAMPO81 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						
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
								sNURCAT);

						resultado.add(activoencontrado);
						
						//logger.debug("Encontrado el registro!");
						//logger.debug(CAMPO1+":|"+sCOACES+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontr� la informaci�n.");
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
}

//Autor: Francisco Valverde Manj�n
