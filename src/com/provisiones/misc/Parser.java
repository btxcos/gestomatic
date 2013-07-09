package com.provisiones.misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


import com.provisiones.types.Activo;
import com.provisiones.types.Longitudes;
import com.provisiones.types.Posiciones;

public class Parser {

	static String sClassName = Parser.class.getName();
	static boolean bEnable = true;
	
	public static Activo LeerActivo (String linea)
	{

		String sCOACES = linea.substring(Posiciones.AC_COACES_P, Posiciones.AC_COACES_P+Longitudes.COACES_L);
		String sNUINMU = linea.substring(Posiciones.AC_NUINMU_P, Posiciones.AC_NUINMU_P+Longitudes.NUINMU_L);
		String sCOSOPA = linea.substring(Posiciones.AC_COSOPA_P, Posiciones.AC_COSOPA_P+Longitudes.COSOPA_L);
		String sCOENAE = linea.substring(Posiciones.AC_COENAE_P, Posiciones.AC_COENAE_P+Longitudes.COENAE_L);
		String sCOESEN = linea.substring(Posiciones.AC_COESEN_P, Posiciones.AC_COESEN_P+Longitudes.COESEN_L);
		String sNOVIAS = linea.substring(Posiciones.AC_NOVIAS_P, Posiciones.AC_NOVIAS_P+Longitudes.NOVIAS_L).trim();
		String sNUPOAC = linea.substring(Posiciones.AC_NUPOAC_P, Posiciones.AC_NUPOAC_P+Longitudes.NUPOAC_L).trim();
		String sNUESAC = linea.substring(Posiciones.AC_NUESAC_P, Posiciones.AC_NUESAC_P+Longitudes.NUESAC_L).trim();
		String sNUPIAC = linea.substring(Posiciones.AC_NUPIAC_P, Posiciones.AC_NUPIAC_P+Longitudes.NUPIAC_L).trim();
		String sNUPUAC = linea.substring(Posiciones.AC_NUPUAC_P, Posiciones.AC_NUPUAC_P+Longitudes.NUPUAC_L).trim();
		String sNOMUIN = linea.substring(Posiciones.AC_NOMUIN_P, Posiciones.AC_NOMUIN_P+Longitudes.NOMUIN_L).trim();
		String sCOPRAE = linea.substring(Posiciones.AC_COPRAE_P, Posiciones.AC_COPRAE_P+Longitudes.COPRAE_L);
		String sNOPRAC = linea.substring(Posiciones.AC_NOPRAC_P, Posiciones.AC_NOPRAC_P+Longitudes.NOPRAC_L).trim();
		String sCOPOIN = linea.substring(Posiciones.AC_COPOIN_P, Posiciones.AC_COPOIN_P+Longitudes.COPOIN_L);
		String sFEREAP = linea.substring(Posiciones.AC_FEREAP_P, Posiciones.AC_FEREAP_P+Longitudes.FEREAP_L);
		String sCOREAE = linea.substring(Posiciones.AC_COREAE_P, Posiciones.AC_COREAE_P+Longitudes.COREAE_L);
		String sFEINAU = linea.substring(Posiciones.AC_FEINAU_P, Posiciones.AC_FEINAU_P+Longitudes.FEINAU_L);
		String sFESOPO = linea.substring(Posiciones.AC_FESOPO_P, Posiciones.AC_FESOPO_P+Longitudes.FESOPO_L);
		String sFESEPO = linea.substring(Posiciones.AC_FESEPO_P, Posiciones.AC_FESEPO_P+Longitudes.FESEPO_L);
		String sFEREPO = linea.substring(Posiciones.AC_FEREPO_P, Posiciones.AC_FEREPO_P+Longitudes.FEREPO_L);
		String sFEADAC = linea.substring(Posiciones.AC_FEADAC_P, Posiciones.AC_FEADAC_P+Longitudes.FEADAC_L);
		String sCODIJU = linea.substring(Posiciones.AC_CODIJU_P, Posiciones.AC_CODIJU_P+Longitudes.CODIJU_L);
		String sCOSJUP = linea.substring(Posiciones.AC_COSJUP_P, Posiciones.AC_COSJUP_P+Longitudes.COSJUP_L);
		String sCOSTLI = linea.substring(Posiciones.AC_COSTLI_P, Posiciones.AC_COSTLI_P+Longitudes.COSTLI_L);
		String sCOSCAR = linea.substring(Posiciones.AC_COSCAR_P, Posiciones.AC_COSCAR_P+Longitudes.COSCAR_L);
		String sCOESVE = linea.substring(Posiciones.AC_COESVE_P, Posiciones.AC_COESVE_P+Longitudes.COESVE_L);
		String sCOTSIN = linea.substring(Posiciones.AC_COTSIN_P, Posiciones.AC_COTSIN_P+Longitudes.COTSIN_L);
		String sNUFIRE = linea.substring(Posiciones.AC_NUFIRE_P, Posiciones.AC_NUFIRE_P+Longitudes.NUFIRE_L).trim();
		String sNUREGP = linea.substring(Posiciones.AC_NUREGP_P, Posiciones.AC_NUREGP_P+Longitudes.NUREGP_L);
		String sNOMUI0 = linea.substring(Posiciones.AC_NOMUI0_P, Posiciones.AC_NOMUI0_P+Longitudes.NOMUI0_L).trim();
		String sNULIBE = linea.substring(Posiciones.AC_NULIBE_P, Posiciones.AC_NULIBE_P+Longitudes.NULIBE_L);
		String sNUTOME = linea.substring(Posiciones.AC_NUTOME_P, Posiciones.AC_NUTOME_P+Longitudes.NUTOME_L);
		String sNUFOLE = linea.substring(Posiciones.AC_NUFOLE_P, Posiciones.AC_NUFOLE_P+Longitudes.NUFOLE_L);
		String sNUINSR = linea.substring(Posiciones.AC_NUINSR_P, Posiciones.AC_NUINSR_P+Longitudes.NUINSR_L);
		String sCOSOCU = linea.substring(Posiciones.AC_COSOCU_P, Posiciones.AC_COSOCU_P+Longitudes.COSOCU_L);
		String sCOXPRO = linea.substring(Posiciones.AC_COXPRO_P, Posiciones.AC_COXPRO_P+Longitudes.COXPRO_L);
		String sFESOLA = linea.substring(Posiciones.AC_FESOLA_P, Posiciones.AC_FESOLA_P+Longitudes.FESOLA_L);
		String sFESELA = linea.substring(Posiciones.AC_FESELA_P, Posiciones.AC_FESELA_P+Longitudes.FESELA_L);
		String sFERELA = linea.substring(Posiciones.AC_FERELA_P, Posiciones.AC_FERELA_P+Longitudes.FERELA_L);
		String sFERLLA = linea.substring(Posiciones.AC_FERLLA_P, Posiciones.AC_FERLLA_P+Longitudes.FERLLA_L);
		String sCASPRE = linea.substring(Posiciones.AC_CASPRE_P, Posiciones.AC_CASPRE_P+Longitudes.CASPRE_L);
		String sCASUTR = linea.substring(Posiciones.AC_CASUTR_P, Posiciones.AC_CASUTR_P+Longitudes.CASUTR_L);
		String sCASUTC = linea.substring(Posiciones.AC_CASUTC_P, Posiciones.AC_CASUTC_P+Longitudes.CASUTC_L);
		String sCASUTG = linea.substring(Posiciones.AC_CASUTG_P, Posiciones.AC_CASUTG_P+Longitudes.CASUTG_L);
		String sBIARRE = linea.substring(Posiciones.AC_BIARRE_P, Posiciones.AC_BIARRE_P+Longitudes.BIARRE_L);
		String sCADORM = linea.substring(Posiciones.AC_CADORM_P, Posiciones.AC_CADORM_P+Longitudes.CADORM_L);
		String sCABANO = linea.substring(Posiciones.AC_CABANO_P, Posiciones.AC_CABANO_P+Longitudes.CABANO_L);
		String sBIGAPA = linea.substring(Posiciones.AC_BIGAPA_P, Posiciones.AC_BIGAPA_P+Longitudes.BIGAPA_L);
		String sCAGAPA = linea.substring(Posiciones.AC_CAGAPA_P, Posiciones.AC_CAGAPA_P+Longitudes.CAGAPA_L);
		String sCASUTE = linea.substring(Posiciones.AC_CASUTE_P, Posiciones.AC_CASUTE_P+Longitudes.CASUTE_L);
		String sBILIPO = linea.substring(Posiciones.AC_BILIPO_P, Posiciones.AC_BILIPO_P+Longitudes.BILIPO_L);
		String sBILIAC = linea.substring(Posiciones.AC_BILIAC_P, Posiciones.AC_BILIAC_P+Longitudes.BILIAC_L);
		String sBILIUS = linea.substring(Posiciones.AC_BILIUS_P, Posiciones.AC_BILIUS_P+Longitudes.BILIUS_L);
		String sBIBOIN = linea.substring(Posiciones.AC_BIBOIN_P, Posiciones.AC_BIBOIN_P+Longitudes.BIBOIN_L);
		String sBICEFI = linea.substring(Posiciones.AC_BICEFI_P, Posiciones.AC_BICEFI_P+Longitudes.BICEFI_L);
		String sCASUCB = linea.substring(Posiciones.AC_CASUCB_P, Posiciones.AC_CASUCB_P+Longitudes.CASUCB_L);
		String sCASUCS = linea.substring(Posiciones.AC_CASUCS_P, Posiciones.AC_CASUCS_P+Longitudes.CASUCS_L);
		String sFEACON = linea.substring(Posiciones.AC_FEACON_P, Posiciones.AC_FEACON_P+Longitudes.FEACON_L);
		String sIDAUTO = linea.substring(Posiciones.AC_IDAUTO_P, Posiciones.AC_IDAUTO_P+Longitudes.IDAUTO_L);
		String sFEDEMA = linea.substring(Posiciones.AC_FEDEMA_P, Posiciones.AC_FEDEMA_P+Longitudes.FEDEMA_L);
		String sYNOCUR = linea.substring(Posiciones.AC_YNOCUR_P, Posiciones.AC_YNOCUR_P+Longitudes.YNOCUR_L);
		String sOBRECO = linea.substring(Posiciones.AC_OBRECO_P, Posiciones.AC_OBRECO_P+Longitudes.OBRECO_L);
		String sYNOLEC = linea.substring(Posiciones.AC_YNOLEC_P, Posiciones.AC_YNOLEC_P+Longitudes.YNOLEC_L);
		String sNOLOJZ = linea.substring(Posiciones.AC_NOLOJZ_P, Posiciones.AC_NOLOJZ_P+Longitudes.NOLOJZ_L);
		String sFEREDE = linea.substring(Posiciones.AC_FEREDE_P, Posiciones.AC_FEREDE_P+Longitudes.FEREDE_L);
		String sPOPROP = linea.substring(Posiciones.AC_POPROP_P, Posiciones.AC_POPROP_P+Longitudes.POPROP_L);
		String sCOGRAP = linea.substring(Posiciones.AC_COGRAP_P, Posiciones.AC_COGRAP_P+Longitudes.COGRAP_L);
		String sFEPREG = linea.substring(Posiciones.AC_FEPREG_P, Posiciones.AC_FEPREG_P+Longitudes.FEPREG_L);
		String sFEPHAC = linea.substring(Posiciones.AC_FEPHAC_P, Posiciones.AC_FEPHAC_P+Longitudes.FEPHAC_L);
		String sFEFOAC = linea.substring(Posiciones.AC_FEFOAC_P, Posiciones.AC_FEFOAC_P+Longitudes.FEFOAC_L);
		String sFEVACT = linea.substring(Posiciones.AC_FEVACT_P, Posiciones.AC_FEVACT_P+Longitudes.FEVACT_L);
		String sIMVACT = linea.substring(Posiciones.AC_IMVACT_P, Posiciones.AC_IMVACT_P+Longitudes.IMVACT_L);
		String sNUFIPR = linea.substring(Posiciones.AC_NUFIPR_P, Posiciones.AC_NUFIPR_P+Longitudes.NUFIPR_L);
		String sCOTPET = linea.substring(Posiciones.AC_COTPET_P, Posiciones.AC_COTPET_P+Longitudes.COTPET_L);
		String sFEEMPT = linea.substring(Posiciones.AC_FEEMPT_P, Posiciones.AC_FEEMPT_P+Longitudes.FEEMPT_L);
		String sFESORC = linea.substring(Posiciones.AC_FESORC_P, Posiciones.AC_FESORC_P+Longitudes.FESORC_L);
		String sFESODE = linea.substring(Posiciones.AC_FESODE_P, Posiciones.AC_FESODE_P+Longitudes.FESODE_L);
		String sFEREAC = linea.substring(Posiciones.AC_FEREAC_P, Posiciones.AC_FEREAC_P+Longitudes.FEREAC_L);
		String sCOXSIA = linea.substring(Posiciones.AC_COXSIA_P, Posiciones.AC_COXSIA_P+Longitudes.COXSIA_L);
		String sNUJUZD = linea.substring(Posiciones.AC_NUJUZD_P, Posiciones.AC_NUJUZD_P+Longitudes.NUJUZD_L);
		String sNURCAT = linea.substring(Posiciones.AC_NURCAT_P, Posiciones.AC_NURCAT_P+Longitudes.NURCAT_L);
		String sNOMPRC = linea.substring(Posiciones.AC_NOMPRC_P, Posiciones.AC_NOMPRC_P+Longitudes.NOMPRC_L);
		String sNUTPRC = linea.substring(Posiciones.AC_NUTPRC_P, Posiciones.AC_NUTPRC_P+Longitudes.NUTPRC_L);
		String sNOMADC = linea.substring(Posiciones.AC_NOMADC_P, Posiciones.AC_NOMADC_P+Longitudes.NOMADC_L);
		String sNUTADC = linea.substring(Posiciones.AC_NUTADC_P, Posiciones.AC_NUTADC_P+Longitudes.NUTADC_L);
		String sIMPCOO = linea.substring(Posiciones.AC_IMPCOO_P, Posiciones.AC_IMPCOO_P+Longitudes.IMPCOO_L);
		String sCOENOR = linea.substring(Posiciones.AC_COENOR_P, Posiciones.AC_COENOR_P+Longitudes.COENOR_L); 
		String sCOSPAT = linea.substring(Posiciones.AC_COSPAT_P, Posiciones.AC_COSPAT_P+Longitudes.COSPAT_L); 
		String sCOSPAS = linea.substring(Posiciones.AC_COSPAS_P, Posiciones.AC_COSPAS_P+Longitudes.COSPAS_L); 
		String sIDCOL3 = linea.substring(Posiciones.AC_IDCOL3_P, Posiciones.AC_IDCOL3_P+Longitudes.IDCOL3_L); 
		String sBIOBNU = linea.substring(Posiciones.AC_BIOBNU_P, Posiciones.AC_BIOBNU_P+Longitudes.BIOBNU_L); 
		String sPOBRAR = linea.substring(Posiciones.AC_POBRAR_P, Posiciones.AC_POBRAR_P+Longitudes.POBRAR_L); 

		
		
		
		
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

	public static void main(String[] args) throws IOException {

		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main",	"Conexion Realizada");
		File archivo = new File("C:\\168AC.txt");
		FileReader fr = new FileReader(archivo);
		BufferedReader br = new BufferedReader(fr);
		String linea = br.readLine();
		br.close();

		Activo activo = LeerActivo(linea);

		activo.pintaActivo();

	}

}
