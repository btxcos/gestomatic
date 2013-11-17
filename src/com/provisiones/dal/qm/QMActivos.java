package com.provisiones.dal.qm;

import com.provisiones.misc.Utils;
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

public class QMActivos
{
	private static Logger logger = LoggerFactory.getLogger(QMActivos.class.getName());
	
	public static final String TABLA = "pp001_ac_activos_tbl";

	public static final String CAMPO1 = "coaces_id";

	public static final String CAMPO2  = "nuinmu";
	public static final String CAMPO3  = "cod_cosopa";
	public static final String CAMPO4  = "cod_coenae";
	public static final String CAMPO5  = "cod_coesen";
	public static final String CAMPO6  = "novias";
	public static final String CAMPO7  = "nupoac";
	public static final String CAMPO8  = "nuesac";
	public static final String CAMPO9  = "nupiac";
	public static final String CAMPO10 = "nupuac";
	public static final String CAMPO11 = "nomuin";
	public static final String CAMPO12 = "cod_coprae";
	public static final String CAMPO13 = "noprac";
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
	public static final String CAMPO28 = "nufire";
	public static final String CAMPO29 = "nuregp";
	public static final String CAMPO30 = "nomui0";
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
	public static final String CAMPO61 = "ynocur";
	public static final String CAMPO62 = "obreco";
	public static final String CAMPO63 = "ynolec";
	public static final String CAMPO64 = "nolojz";
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
	public static final String CAMPO81 = "nurcat";
	public static final String CAMPO82 = "nomprc";
	public static final String CAMPO83 = "nutprc";
	public static final String CAMPO84 = "nomadc";
	public static final String CAMPO85 = "nutadc";
	public static final String CAMPO86 = "impcoo";
	public static final String CAMPO87 = "coenor";
	public static final String CAMPO88 = "cod_cospat";
	public static final String CAMPO89 = "cod_cospas";
	public static final String CAMPO90 = "idcol3";
	public static final String CAMPO91 = "cod_biobnu";
	public static final String CAMPO92 = "pobrar";

	public static boolean addActivo(Connection conexion, Activo NuevoActivo) 
	{
		boolean bSalida = false;
		
		if (!(conexion == null))
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
				       + CAMPO92 +                  
				       ") VALUES ('"   
				       + NuevoActivo.getCOACES() + "','"
				       + NuevoActivo.getNUINMU() + "','"  
				       + NuevoActivo.getCOSOPA() + "','"  
				       + NuevoActivo.getCOENAE() + "','"  
				       + NuevoActivo.getCOESEN() + "','"  
				       + NuevoActivo.getNOVIAS() + "','"  
				       + NuevoActivo.getNUPOAC() + "','"  
				       + NuevoActivo.getNUESAC() + "','"  
				       + NuevoActivo.getNUPIAC() + "','"  
				       + NuevoActivo.getNUPUAC() + "','"  
				       + NuevoActivo.getNOMUIN() + "','"  
				       + NuevoActivo.getCOPRAE() + "','"  
				       + NuevoActivo.getNOPRAC() + "','"  
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
				       + NuevoActivo.getCOTSIN() + "','"  
				       + NuevoActivo.getNUFIRE() + "','"  
				       + NuevoActivo.getNUREGP() + "','"  
				       + NuevoActivo.getNOMUI0() + "','"  
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
				       + NuevoActivo.getFEDEMA() + "','"  
				       + NuevoActivo.getYNOCUR() + "','"  
				       + NuevoActivo.getOBRECO() + "','"  
				       + NuevoActivo.getYNOLEC() + "','"  
				       + NuevoActivo.getNOLOJZ() + "','"  
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
				       + NuevoActivo.getNURCAT() + "','"  
				       + NuevoActivo.getNOMPRC() + "','"  
				       + NuevoActivo.getNUTPRC() + "','"  
				       + NuevoActivo.getNOMADC() + "','"  
				       + NuevoActivo.getNUTADC() + "','"  
				       + NuevoActivo.getIMPCOO() + "','"  
				       + NuevoActivo.getCOENOR() + "','"  
				       + NuevoActivo.getCOSPAT() + "','"  
				       + NuevoActivo.getCOSPAS() + "','"  
				       + NuevoActivo.getIDCOL3() + "','"  
				       + NuevoActivo.getBIOBNU() + "','"  
				       + NuevoActivo.getPOBRAR() + "' )";
			
			logger.debug(sQuery);
			
			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con éxito!");

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
	public static boolean modActivo(Connection conexion, Activo NuevoActivo, String sCodCOACES)
	{
		boolean bSalida = false;

		if (!(conexion == null))
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO2  + " = '"+ NuevoActivo.getNUINMU() + "', "
					+ CAMPO3  + " = '"+ NuevoActivo.getCOSOPA() + "', "
					+ CAMPO4  + " = '"+ NuevoActivo.getCOENAE() + "', "
					+ CAMPO5  + " = '"+ NuevoActivo.getCOESEN() + "', "
					+ CAMPO6  + " = '"+ NuevoActivo.getNOVIAS() + "', "
					+ CAMPO7  + " = '"+ NuevoActivo.getNUPOAC() + "', "
					+ CAMPO8  + " = '"+ NuevoActivo.getNUESAC() + "', "
					+ CAMPO9  + " = '"+ NuevoActivo.getNUPIAC() + "', "
					+ CAMPO10 + " = '"+ NuevoActivo.getNUPUAC() + "', "
					+ CAMPO11 + " = '"+ NuevoActivo.getNOMUIN() + "', "
					+ CAMPO12 + " = '"+ NuevoActivo.getCOPRAE() + "', "
					+ CAMPO13 + " = '"+ NuevoActivo.getNOPRAC() + "', "
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
					+ CAMPO28 + " = '"+ NuevoActivo.getNUFIRE() + "', "
					+ CAMPO29 + " = '"+ NuevoActivo.getNUREGP() + "', "
					+ CAMPO30 + " = '"+ NuevoActivo.getNOMUI0() + "', "
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
					+ CAMPO61 + " = '"+ NuevoActivo.getYNOCUR() + "', "
					+ CAMPO62 + " = '"+ NuevoActivo.getOBRECO() + "', "
					+ CAMPO63 + " = '"+ NuevoActivo.getYNOLEC() + "', "
					+ CAMPO64 + " = '"+ NuevoActivo.getNOLOJZ() + "', "
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
					+ CAMPO82 + " = '"+ NuevoActivo.getNOMPRC() + "', "
					+ CAMPO83 + " = '"+ NuevoActivo.getNUTPRC() + "', "
					+ CAMPO84 + " = '"+ NuevoActivo.getNOMADC() + "', "
					+ CAMPO85 + " = '"+ NuevoActivo.getNUTADC() + "', "
					+ CAMPO86 + " = '"+ NuevoActivo.getIMPCOO() + "', "
					+ CAMPO87 + " = '"+ NuevoActivo.getCOENOR() + "', "
					+ CAMPO88 + " = '"+ NuevoActivo.getCOSPAT() + "', "
					+ CAMPO89 + " = '"+ NuevoActivo.getCOSPAS() + "', "
					+ CAMPO90 + " = '"+ NuevoActivo.getIDCOL3() + "', "
					+ CAMPO91 + " = '"+ NuevoActivo.getBIOBNU() + "', "
					+ CAMPO92 + " = '"+ NuevoActivo.getPOBRAR() + "' "+
					" WHERE "
					+ CAMPO1 + " = '"+ sCodCOACES +"'";
			
			logger.debug(sQuery);
			
			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con éxito!");

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

	public static boolean delActivo(Connection conexion, String sCodCOACES)
	{
		boolean bSalida = false;

		if (!(conexion == null))
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
							+ TABLA + 
							" WHERE " 
							+ CAMPO1 + " = '" + sCodCOACES + "'";

			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con éxito!");

				bSalida = true;
				
			} 
			catch (SQLException ex) 
			{
				bSalida = false;

				logger.error("ERROR COACES:|"+sCodCOACES+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static Activo getActivo(Connection conexion, String sCodCOACES)
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

		if (!(conexion == null))
		{
			Statement stmt = null;
			
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			


			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
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
				       + CAMPO92 +        
				       " FROM " 
				       + TABLA + 
				       " WHERE "
				       + CAMPO1 + " = '" + sCodCOACES	+ "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				
				

				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				logger.debug(CAMPO1+":|"+sCodCOACES+"|");

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
						sNOVIAS = rs.getString(CAMPO6);
						sNUPOAC = rs.getString(CAMPO7);
						sNUESAC = rs.getString(CAMPO8);
						sNUPIAC = rs.getString(CAMPO9);
						sNUPUAC = rs.getString(CAMPO10);
						sNOMUIN = rs.getString(CAMPO11);
						sCOPRAE = rs.getString(CAMPO12);
						sNOPRAC = rs.getString(CAMPO13);
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
						sNUFIRE = rs.getString(CAMPO28);
						sNUREGP = rs.getString(CAMPO29);
						sNOMUI0 = rs.getString(CAMPO30);
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
						sYNOCUR = rs.getString(CAMPO61);
						sOBRECO = rs.getString(CAMPO62);
						sYNOLEC = rs.getString(CAMPO63);
						sNOLOJZ = rs.getString(CAMPO64);
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
						sNOMPRC = rs.getString(CAMPO82);
						sNUTPRC = rs.getString(CAMPO83);
						sNOMADC = rs.getString(CAMPO84);
						sNUTADC = rs.getString(CAMPO85);
						sIMPCOO = rs.getString(CAMPO86);
						sCOENOR = rs.getString(CAMPO87);
						sCOSPAT = rs.getString(CAMPO88);
						sCOSPAS = rs.getString(CAMPO89);
						sIDCOL3 = rs.getString(CAMPO90);
						sBIOBNU = rs.getString(CAMPO91);
						sPOBRAR = rs.getString(CAMPO92);

						logger.debug("Encontrado el registro!");

					}
				}
				if (bEncontrado == false) 
				{
					logger.debug("No se encontro la información.");
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
				
				logger.error("ERROR COACES:|"+sCodCOACES+"|");

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
				sBIOBNU, sPOBRAR);
	}

	public static boolean existeActivo(Connection conexion, String sCodCOACES)
	{
		boolean bEncontrado = false;

		if (!(conexion == null))
		{
			
			Statement stmt = null;

			ResultSet rs = null;
			PreparedStatement pstmt = null;

			

			//logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + CAMPO1  +        
					   " FROM " 
					   + TABLA + 
					   " WHERE " 
					   + CAMPO1 + " = '" + sCodCOACES	+ "'";
			
			//logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				
				

				rs = pstmt.executeQuery();
				
				//logger.debug("Ejecutada con éxito!");

				

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;

						logger.debug("Encontrado el registro!");
						//logger.debug(CAMPO1+":|"+rs.getString(CAMPO1)+"|");
					}
				}
				if (bEncontrado == false) 
				{
					logger.debug("No se encontro la información.");
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
	
	public static boolean compruebaActivo(Connection conexion, Activo NuevoActivo)
	{
		boolean bEncontrado = false;

		if (!(conexion == null))
		{
			Statement stmt = null;

			ResultSet rs = null;
			PreparedStatement pstmt = null;

			logger.debug("Ejecutando Query...");
			
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
					+ CAMPO6  + " = '"+ NuevoActivo.getNOVIAS() + "' AND "
					+ CAMPO7  + " = '"+ NuevoActivo.getNUPOAC() + "' AND "
					+ CAMPO8  + " = '"+ NuevoActivo.getNUESAC() + "' AND "
					+ CAMPO9  + " = '"+ NuevoActivo.getNUPIAC() + "' AND "
					+ CAMPO10 + " = '"+ NuevoActivo.getNUPUAC() + "' AND "
					+ CAMPO11 + " = '"+ NuevoActivo.getNOMUIN() + "' AND "
					+ CAMPO12 + " = '"+ NuevoActivo.getCOPRAE() + "' AND "
					+ CAMPO13 + " = '"+ NuevoActivo.getNOPRAC() + "' AND "
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
					+ CAMPO28 + " = '"+ NuevoActivo.getNUFIRE() + "' AND "
					+ CAMPO29 + " = '"+ NuevoActivo.getNUREGP() + "' AND "
					+ CAMPO30 + " = '"+ NuevoActivo.getNOMUI0() + "' AND "
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
					+ CAMPO61 + " = '"+ NuevoActivo.getYNOCUR() + "' AND "
					+ CAMPO62 + " = '"+ NuevoActivo.getOBRECO() + "' AND "
					+ CAMPO63 + " = '"+ NuevoActivo.getYNOLEC() + "' AND "
					+ CAMPO64 + " = '"+ NuevoActivo.getNOLOJZ() + "' AND "
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
					+ CAMPO82 + " = '"+ NuevoActivo.getNOMPRC() + "' AND "
					+ CAMPO83 + " = '"+ NuevoActivo.getNUTPRC() + "' AND "
					+ CAMPO84 + " = '"+ NuevoActivo.getNOMADC() + "' AND "
					+ CAMPO85 + " = '"+ NuevoActivo.getNUTADC() + "' AND "
					+ CAMPO86 + " = '"+ NuevoActivo.getIMPCOO() + "' AND "
					+ CAMPO87 + " = '"+ NuevoActivo.getCOENOR() + "' AND "
					+ CAMPO88 + " = '"+ NuevoActivo.getCOSPAT() + "' AND "
					+ CAMPO89 + " = '"+ NuevoActivo.getCOSPAS() + "' AND "
					+ CAMPO90 + " = '"+ NuevoActivo.getIDCOL3() + "' AND "
					+ CAMPO91 + " = '"+ NuevoActivo.getBIOBNU() + "' AND "
					+ CAMPO92 + " = '"+ NuevoActivo.getPOBRAR() + "' )";
			
			logger.debug(sQuery);
			
			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				
				

				rs = pstmt.executeQuery();
				
				//logger.debug("Ejecutada con éxito!");

				

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;

						logger.debug("Encontrado el registro!");
						//logger.debug(CAMPO1+":|"+rs.getString(CAMPO1)+"|");
					}
				}
				if (bEncontrado == false) 
				{
					logger.debug("No se encontro la información.");
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

	public static String getReferenciaCatastral(Connection conexion, String sCodCOACES)
	{
		String sReferencia = "";

		if (!(conexion == null))
		{
			Statement stmt = null;

			ResultSet rs = null;
			PreparedStatement pstmt = null;
			

			
			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + CAMPO81  +        
					   " FROM " 
					   + TABLA + 
					   " WHERE " 
					   + CAMPO1 + " = '" + sCodCOACES	+ "'";
			
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
						
						sReferencia = rs.getString(CAMPO81);

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO81+":|"+sReferencia+"|");

					}
				}
				if (bEncontrado == false) 
				{
					logger.debug("No se encontro la información.");
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
	
	public static String getCOTSINActivo(Connection conexion, String sCodCOACES)
	{
		String sCOTSIN = "";

		if (!(conexion == null))
		{
			Statement stmt = null;

			ResultSet rs = null;
			PreparedStatement pstmt = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + CAMPO27  +        
					   " FROM " 
					   + TABLA + 
					   " WHERE " 
					   + CAMPO1 + " = '" + sCodCOACES	+ "'";
			
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
						
						sCOTSIN = rs.getString(CAMPO27);

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO27+":|"+sCOTSIN+"|");

					}
				}
				if (bEncontrado == false) 
				{
					logger.debug("No se encontro la información.");
				}

			} 
			catch (SQLException ex) 
			{
				sCOTSIN = "";
				
				logger.error("ERROR COACES:|"+sCodCOACES+"|");

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

	public static String getBIARREActivo(Connection conexion, String sCodCOACES)
	{
		String sBIARRE = "";

		if (!(conexion == null))
		{
			Statement stmt = null;

			ResultSet rs = null;
			PreparedStatement pstmt = null;



			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + CAMPO45  +        
					   " FROM " 
					   + TABLA + 
					   " WHERE " 
					   + CAMPO1 + " = '" + sCodCOACES + "'";
			
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
						
						sBIARRE = rs.getString(CAMPO45);

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO45+":|"+sBIARRE+"|");
					}
				}
				if (bEncontrado == false) 
				{
					logger.debug("No se encontro la información.");
				}

			} 
			catch (SQLException ex) 
			{
				sBIARRE = "";

				logger.error("ERROR COACES:|"+sCodCOACES+"|");

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
	
	public static String getSociedadPatrimonial(Connection conexion, String sCodCOACES)
	{
		String sCodCOSPAT = "0";
		
		if (!(conexion == null))
		{
			Statement stmt = null;

			ResultSet rs = null;
			PreparedStatement pstmt = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + CAMPO88  +        
					   " FROM " 
					   + TABLA + 
					   " WHERE " 
					   + CAMPO1 + " = '" + sCodCOACES + "'";
			
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
						
						sCodCOSPAT = rs.getString(CAMPO88);

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO88+":|"+sCodCOSPAT+"|");

					}
				}
				if (bEncontrado == false) 
				{
					logger.debug("No se encontro la información.");
				}

			} 
			catch (SQLException ex) 
			{
				sCodCOSPAT = "0";

				logger.error("ERROR COACES:|"+sCodCOACES+"|");

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

		if (!(conexion == null))
		{
			Statement stmt = null;

			ResultSet rs = null;
			PreparedStatement pstmt = null;

			String sCOACES = "";
			String sCOPOIN = "";
			String sNOMUIN = "";
			String sNOPRAC = "";
			String sNOVIAS = "";
			String sNUPIAC = "";
			String sNUPOAC = "";
			String sNUPUAC = "";
			


			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + CAMPO1 + ","        
					   + CAMPO14 + ","
					   + CAMPO11 + ","
					   + CAMPO13 + ","
					   + CAMPO6 + ","
					   + CAMPO9 + ","
					   + CAMPO7 + ","
					   + CAMPO10 + 
					   " FROM " 
					   + TABLA + 
					   " WHERE ("
					   + CAMPO14 + " LIKE '%" + filtro.getCOPOIN()	+ "%' AND "  
					   + CAMPO11 + " LIKE '%" + filtro.getNOMUIN()	+ "%' AND "  
					   + CAMPO13 + " LIKE '%" + filtro.getNOPRAC()	+ "%' AND "  
					   + CAMPO6 + " LIKE '%" + filtro.getNOVIAS()	+ "%' AND "  
					   + CAMPO9 + " LIKE '%" + filtro.getNUPIAC()	+ "%' AND "  
					   + CAMPO7 + " LIKE '%" + filtro.getNUPOAC()	+ "%' AND "  
					   + CAMPO10 + " LIKE '%" + filtro.getNUPUAC()	+ 			   
					   "%')";
			
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
						
						sCOACES = rs.getString(CAMPO1);
						sCOPOIN = rs.getString(CAMPO14);
						sNOMUIN = rs.getString(CAMPO11);
						sNOPRAC = rs.getString(CAMPO13);
						sNOVIAS = rs.getString(CAMPO6);
						sNUPIAC = rs.getString(CAMPO9);
						sNUPOAC = rs.getString(CAMPO7);
						sNUPUAC = rs.getString(CAMPO10);
						
						ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, "");
						
						resultado.add(activoencontrado);
						
						//logger.debug( "Encontrado el registro!");
						//logger.debug(CAMPO1+":|"+sCOACES+"|");
					}
				}
				if (bEncontrado == false) 
				{

					logger.info("No se encontro la información.");
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

		if (!(conexion == null))
		{
			Statement stmt = null;
			ResultSet rs = null;

			String sCOACES = "";
			String sCOPOIN = "";
			String sNOMUIN = "";
			String sNOPRAC = "";
			String sNOVIAS = "";
			String sNUPIAC = "";
			String sNUPOAC = "";
			String sNUPUAC = "";
			String sNURCAT = "";
			

			

			PreparedStatement pstmt = null;
			boolean found = false;
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						
						   + CAMPO1 + ","        
						   + CAMPO14 + ","
						   + CAMPO11 + ","
						   + CAMPO13 + ","
						   + CAMPO6 + ","
						   + CAMPO9 + ","
						   + CAMPO7 + ","
						   + CAMPO10 + ","
						   + CAMPO81 + 

						   " FROM " 
						   + TABLA + 
						   " WHERE ("

						   + CAMPO14 + " LIKE '%" + filtro.getCOPOIN()	+ "%' AND "  
						   + CAMPO11 + " LIKE '%" + filtro.getNOMUIN()	+ "%' AND "  
						   + CAMPO13 + " LIKE '%" + filtro.getNOPRAC()	+ "%' AND "  
						   + CAMPO6 + " LIKE '%" + filtro.getNOVIAS()	+ "%' AND "  
						   + CAMPO9 + " LIKE '%" + filtro.getNUPIAC()	+ "%' AND "  
						   + CAMPO7 + " LIKE '%" + filtro.getNUPOAC()	+ "%' AND "  
						   + CAMPO10 + " LIKE '%" + filtro.getNUPUAC()	+ "%' )";
			
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
						found = true;
						
						sCOACES = rs.getString(CAMPO1);
						sCOPOIN = rs.getString(CAMPO14);
						sNOMUIN = rs.getString(CAMPO11);
						sNOPRAC = rs.getString(CAMPO13);
						sNOVIAS = rs.getString(CAMPO6);
						sNUPIAC = rs.getString(CAMPO9);
						sNUPOAC = rs.getString(CAMPO7);
						sNUPUAC = rs.getString(CAMPO10);
						sNURCAT = rs.getString(CAMPO81);
						
						ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, sNURCAT);
						
						resultado.add(activoencontrado);
						
						//logger.debug("Encontrado el registro!");
						//logger.debug(CAMPO1+":|"+sCOACES+"|");
					}
				}
				if (found == false) 
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
}
