package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.types.Activo;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class QMDatosActivos
{
	static String sClassName = QMDatosActivos.class.getName();

	static String sTable = "ac_datos_tbl";

	static String sField1 = "ac_datos_id";

	static String sField2  = "nuinmu";
	static String sField3  = "cod_cosopa";
	static String sField4  = "cod_coenae";
	static String sField5  = "cod_coesen";
	static String sField6  = "novias";
	static String sField7  = "nupoac";
	static String sField8  = "nuesac";
	static String sField9  = "nupiac";
	static String sField10 = "nupuac";
	static String sField11 = "nomuin";
	static String sField12 = "cod_coprae";
	static String sField13 = "noprac";
	static String sField14 = "copoin";
	static String sField15 = "fereap";
	static String sField16 = "cod_coreae";
	static String sField17 = "feinau";
	static String sField18 = "fesopo";
	static String sField19 = "fesepo";
	static String sField20 = "ferepo";
	static String sField21 = "feadac";
	static String sField22 = "cod_codiju";
	static String sField23 = "cod_cosjup";
	static String sField24 = "cod_costli";
	static String sField25 = "cod_coscar";
	static String sField26 = "cod_coesve";
	static String sField27 = "cod_cotsin";
	static String sField28 = "nufire";
	static String sField29 = "nuregp";
	static String sField30 = "nomui0";
	static String sField31 = "nulibe";
	static String sField32 = "nutome";
	static String sField33 = "nufole";
	static String sField34 = "nuinsr";
	static String sField35 = "cod_cosocu";
	static String sField36 = "cod_coxpro";
	static String sField37 = "fesola";
	static String sField38 = "fesela";
	static String sField39 = "ferela";
	static String sField40 = "ferlla";
	static String sField41 = "caspre";
	static String sField42 = "casutr";
	static String sField43 = "casutc";
	static String sField44 = "casutg";
	static String sField45 = "cod_biarre";
	static String sField46 = "cadorm";
	static String sField47 = "cabano";
	static String sField48 = "cod_bigapa";
	static String sField49 = "cagapa";
	static String sField50 = "casute";
	static String sField51 = "cod_bilipo";
	static String sField52 = "cod_biliac";
	static String sField53 = "cod_bilius";
	static String sField54 = "cod_biboin";
	static String sField55 = "cod_bicefi";
	static String sField56 = "casucb";
	static String sField57 = "casucs";
	static String sField58 = "feacon";
	static String sField59 = "idauto";
	static String sField60 = "fedema";
	static String sField61 = "ynocur";
	static String sField62 = "obreco";
	static String sField63 = "ynolec";
	static String sField64 = "nolojz";
	static String sField65 = "ferede";
	static String sField66 = "poprop";
	static String sField67 = "cod_cograp";
	static String sField68 = "fepreg";
	static String sField69 = "fephac";
	static String sField70 = "fefoac";
	static String sField71 = "fevact";
	static String sField72 = "imvact";
	static String sField73 = "nufipr";
	static String sField74 = "cod_cotpet";
	static String sField75 = "feempt";
	static String sField76 = "fesorc";
	static String sField77 = "fesode";
	static String sField78 = "fereac";
	static String sField79 = "cod_coxsia";
	static String sField80 = "nujuzd";
	static String sField81 = "nurcat";
	static String sField82 = "nomprc";
	static String sField83 = "nutprc";
	static String sField84 = "nomadc";
	static String sField85 = "nutadc";
	static String sField86 = "impcoo";
	static String sField87 = "coenor";
	static String sField88 = "cod_cospat";
	static String sField89 = "cod_cospas";
	static String sField90 = "idcol3";
	static String sField91 = "cod_biobnu";
	static String sField92 = "pobrar";

	public static boolean addActivo (Activo NuevoActivo) 
	 
	{
		String sMethod = "addApplication";
		Statement stmt = null;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		try 
		{
			
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + sTable + " ("
				       + sField2  + ","              
				       + sField3  + ","              
				       + sField4  + ","              
				       + sField5  + ","              
				       + sField6  + ","              
				       + sField7  + ","              
				       + sField8  + ","              
				       + sField9  + ","              
				       + sField10 + ","              
				       + sField11 + ","              
				       + sField12 + ","              
				       + sField13 + ","              
				       + sField14 + ","              
				       + sField15 + ","              
				       + sField16 + ","              
				       + sField17 + ","              
				       + sField18 + ","              
				       + sField19 + ","              
				       + sField20 + ","              
				       + sField21 + ","              
				       + sField22 + ","              
				       + sField23 + ","              
				       + sField24 + ","              
				       + sField25 + ","              
				       + sField26 + ","              
				       + sField27 + ","              
				       + sField28 + ","              
				       + sField29 + ","              
				       + sField30 + ","              
				       + sField31 + ","              
				       + sField32 + ","              
				       + sField33 + ","              
				       + sField34 + ","              
				       + sField35 + ","              
				       + sField36 + ","              
				       + sField37 + ","              
				       + sField38 + ","              
				       + sField39 + ","              
				       + sField40 + ","              
				       + sField41 + ","              
				       + sField42 + ","              
				       + sField43 + ","              
				       + sField44 + ","              
				       + sField45 + ","              
				       + sField46 + ","              
				       + sField47 + ","              
				       + sField48 + ","              
				       + sField49 + ","              
				       + sField50 + ","              
				       + sField51 + ","              
				       + sField52 + ","              
				       + sField53 + ","              
				       + sField54 + ","              
				       + sField55 + ","              
				       + sField56 + ","              
				       + sField57 + ","              
				       + sField58 + ","              
				       + sField59 + ","              
				       + sField60 + ","              
				       + sField61 + ","              
				       + sField62 + ","              
				       + sField63 + ","              
				       + sField64 + ","              
				       + sField65 + ","              
				       + sField66 + ","              
				       + sField67 + ","              
				       + sField68 + ","              
				       + sField69 + ","              
				       + sField70 + ","              
				       + sField71 + ","              
				       + sField72 + ","              
				       + sField73 + ","              
				       + sField74 + ","              
				       + sField75 + ","              
				       + sField76 + ","              
				       + sField77 + ","              
				       + sField78 + ","              
				       + sField79 + ","              
				       + sField80 + ","              
				       + sField81 + ","              
				       + sField82 + ","              
				       + sField83 + ","              
				       + sField84 + ","              
				       + sField85 + ","              
				       + sField86 + ","              
				       + sField87 + ","              
				       + sField88 + ","              
				       + sField89 + ","              
				       + sField90 + ","              
				       + sField91 + ","              
				       + sField92 +                  
				       					") VALUES ('"        
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
				       + NuevoActivo.getPOBRAR() + "' )");
		} 
		catch (SQLException ex) 
		{

			//System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSJUP: " + NuevoActivo.getCOSJUP());
			//System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRAP: " + NuevoActivo.getCOGRAP());
			
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + NuevoActivo.getCOACES());
			
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());			
		} 
		finally 
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return true;
	}

}
