package com.provisiones.misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


import com.provisiones.types.Activo;
import com.provisiones.types.Comunidad;
import com.provisiones.types.Cuota;
import com.provisiones.types.Gasto;
import com.provisiones.types.ImpuestoRecurso;
import com.provisiones.types.Longitudes;
import com.provisiones.types.Posiciones;
import com.provisiones.types.ReferenciaCatastral;

public class Parser {

	static String sClassName = Parser.class.getName();
	static boolean bEnable = true;
	
	public static String formateaCampoNumerico (String sCampo, int iLongitud)
	{

        String sResultado = sCampo;
        
        while (sResultado.length() < iLongitud) 
        {
        	sResultado="0"+sResultado;
        }	
		return sResultado;
	}

	public static String formateaCampoAlfanumerico (String sCampo, int iLongitud)
	{

        String sResultado = sCampo;
        
        while (sResultado.length() < iLongitud) 
        {
        	sResultado=sResultado+" ";
        }	
		return sResultado;
	}
	
	
	public static Activo leerActivo (String linea)
	{

		String sCOACES = linea.substring(Posiciones.AC_COACES_P, Posiciones.AC_COACES_P+Longitudes.COACES_L);
		sCOACES = Integer.toString(Integer.parseInt(sCOACES));
		String sNUINMU = linea.substring(Posiciones.AC_NUINMU_P, Posiciones.AC_NUINMU_P+Longitudes.NUINMU_L);
		sNUINMU = Integer.toString(Integer.parseInt(sNUINMU));
		String sCOSOPA = linea.substring(Posiciones.AC_COSOPA_P, Posiciones.AC_COSOPA_P+Longitudes.COSOPA_L);
		sCOSOPA = Integer.toString(Integer.parseInt(sCOSOPA));
		String sCOENAE = linea.substring(Posiciones.AC_COENAE_P, Posiciones.AC_COENAE_P+Longitudes.COENAE_L);
		sCOENAE = Integer.toString(Integer.parseInt(sCOENAE));
		String sCOESEN = linea.substring(Posiciones.AC_COESEN_P, Posiciones.AC_COESEN_P+Longitudes.COESEN_L);
		sCOESEN = Integer.toString(Integer.parseInt(sCOESEN));
		
		String sNOVIAS = linea.substring(Posiciones.AC_NOVIAS_P, Posiciones.AC_NOVIAS_P+Longitudes.NOVIAS_L).trim();
		String sNUPOAC = linea.substring(Posiciones.AC_NUPOAC_P, Posiciones.AC_NUPOAC_P+Longitudes.NUPOAC_L).trim();
		String sNUESAC = linea.substring(Posiciones.AC_NUESAC_P, Posiciones.AC_NUESAC_P+Longitudes.NUESAC_L).trim();
		String sNUPIAC = linea.substring(Posiciones.AC_NUPIAC_P, Posiciones.AC_NUPIAC_P+Longitudes.NUPIAC_L).trim();
		String sNUPUAC = linea.substring(Posiciones.AC_NUPUAC_P, Posiciones.AC_NUPUAC_P+Longitudes.NUPUAC_L).trim();
		String sNOMUIN = linea.substring(Posiciones.AC_NOMUIN_P, Posiciones.AC_NOMUIN_P+Longitudes.NOMUIN_L).trim();
		
		String sCOPRAE = linea.substring(Posiciones.AC_COPRAE_P, Posiciones.AC_COPRAE_P+Longitudes.COPRAE_L);
		sCOPRAE = Integer.toString(Integer.parseInt(sCOPRAE));
		
		String sNOPRAC = linea.substring(Posiciones.AC_NOPRAC_P, Posiciones.AC_NOPRAC_P+Longitudes.NOPRAC_L).trim();
		
		String sCOPOIN = linea.substring(Posiciones.AC_COPOIN_P, Posiciones.AC_COPOIN_P+Longitudes.COPOIN_L);
		
		String sFEREAP = linea.substring(Posiciones.AC_FEREAP_P, Posiciones.AC_FEREAP_P+Longitudes.FEREAP_L);
		
		String sCOREAE = linea.substring(Posiciones.AC_COREAE_P, Posiciones.AC_COREAE_P+Longitudes.COREAE_L);
		sCOREAE = Integer.toString(Integer.parseInt(sCOREAE));
		
		String sFEINAU = linea.substring(Posiciones.AC_FEINAU_P, Posiciones.AC_FEINAU_P+Longitudes.FEINAU_L);
		String sFESOPO = linea.substring(Posiciones.AC_FESOPO_P, Posiciones.AC_FESOPO_P+Longitudes.FESOPO_L);
		String sFESEPO = linea.substring(Posiciones.AC_FESEPO_P, Posiciones.AC_FESEPO_P+Longitudes.FESEPO_L);
		String sFEREPO = linea.substring(Posiciones.AC_FEREPO_P, Posiciones.AC_FEREPO_P+Longitudes.FEREPO_L);
		String sFEADAC = linea.substring(Posiciones.AC_FEADAC_P, Posiciones.AC_FEADAC_P+Longitudes.FEADAC_L);
		
		String sCODIJU = linea.substring(Posiciones.AC_CODIJU_P, Posiciones.AC_CODIJU_P+Longitudes.CODIJU_L);
		sCODIJU = Integer.toString(Integer.parseInt(sCODIJU));
		String sCOSJUP = linea.substring(Posiciones.AC_COSJUP_P, Posiciones.AC_COSJUP_P+Longitudes.COSJUP_L);
		sCOSJUP = Integer.toString(Integer.parseInt(sCOSJUP));
		String sCOSTLI = linea.substring(Posiciones.AC_COSTLI_P, Posiciones.AC_COSTLI_P+Longitudes.COSTLI_L);
		sCOSTLI = Integer.toString(Integer.parseInt(sCOSTLI));
		String sCOSCAR = linea.substring(Posiciones.AC_COSCAR_P, Posiciones.AC_COSCAR_P+Longitudes.COSCAR_L);
		sCOSCAR = Integer.toString(Integer.parseInt(sCOSCAR));
		String sCOESVE = linea.substring(Posiciones.AC_COESVE_P, Posiciones.AC_COESVE_P+Longitudes.COESVE_L);
		sCOESVE = Integer.toString(Integer.parseInt(sCOESVE));

		String sCOTSIN = linea.substring(Posiciones.AC_COTSIN_P, Posiciones.AC_COTSIN_P+Longitudes.COTSIN_L);
		String sNUFIRE = linea.substring(Posiciones.AC_NUFIRE_P, Posiciones.AC_NUFIRE_P+Longitudes.NUFIRE_L).trim();

		String sNUREGP = linea.substring(Posiciones.AC_NUREGP_P, Posiciones.AC_NUREGP_P+Longitudes.NUREGP_L);
		sNUREGP = Integer.toString(Integer.parseInt(sNUREGP));
		
		String sNOMUI0 = linea.substring(Posiciones.AC_NOMUI0_P, Posiciones.AC_NOMUI0_P+Longitudes.NOMUI0_L).trim();

		String sNULIBE = linea.substring(Posiciones.AC_NULIBE_P, Posiciones.AC_NULIBE_P+Longitudes.NULIBE_L);
		sNULIBE = Integer.toString(Integer.parseInt(sNULIBE));
		String sNUTOME = linea.substring(Posiciones.AC_NUTOME_P, Posiciones.AC_NUTOME_P+Longitudes.NUTOME_L);
		sNUTOME = Integer.toString(Integer.parseInt(sNUTOME));
		String sNUFOLE = linea.substring(Posiciones.AC_NUFOLE_P, Posiciones.AC_NUFOLE_P+Longitudes.NUFOLE_L);
		sNUFOLE = Integer.toString(Integer.parseInt(sNUFOLE));
		String sNUINSR = linea.substring(Posiciones.AC_NUINSR_P, Posiciones.AC_NUINSR_P+Longitudes.NUINSR_L);
		sNUINSR = Integer.toString(Integer.parseInt(sNUINSR));
		String sCOSOCU = linea.substring(Posiciones.AC_COSOCU_P, Posiciones.AC_COSOCU_P+Longitudes.COSOCU_L);
		sCOSOCU = Integer.toString(Integer.parseInt(sCOSOCU));
		String sCOXPRO = linea.substring(Posiciones.AC_COXPRO_P, Posiciones.AC_COXPRO_P+Longitudes.COXPRO_L);
		sCOXPRO = Integer.toString(Integer.parseInt(sCOXPRO));

		String sFESOLA = linea.substring(Posiciones.AC_FESOLA_P, Posiciones.AC_FESOLA_P+Longitudes.FESOLA_L);
		String sFESELA = linea.substring(Posiciones.AC_FESELA_P, Posiciones.AC_FESELA_P+Longitudes.FESELA_L);
		String sFERELA = linea.substring(Posiciones.AC_FERELA_P, Posiciones.AC_FERELA_P+Longitudes.FERELA_L);
		String sFERLLA = linea.substring(Posiciones.AC_FERLLA_P, Posiciones.AC_FERLLA_P+Longitudes.FERLLA_L);

		String sCASPRE = linea.substring(Posiciones.AC_CASPRE_P, Posiciones.AC_CASPRE_P+Longitudes.CASPRE_L);
		sCASPRE = Integer.toString(Integer.parseInt(sCASPRE));
		String sCASUTR = linea.substring(Posiciones.AC_CASUTR_P, Posiciones.AC_CASUTR_P+Longitudes.CASUTR_L);
		sCASUTR = Integer.toString(Integer.parseInt(sCASUTR));
		String sCASUTC = linea.substring(Posiciones.AC_CASUTC_P, Posiciones.AC_CASUTC_P+Longitudes.CASUTC_L);
		sCASUTC = Integer.toString(Integer.parseInt(sCASUTC));
		String sCASUTG = linea.substring(Posiciones.AC_CASUTG_P, Posiciones.AC_CASUTG_P+Longitudes.CASUTG_L);
		sCASUTG = Integer.toString(Integer.parseInt(sCASUTG));

		String sBIARRE = linea.substring(Posiciones.AC_BIARRE_P, Posiciones.AC_BIARRE_P+Longitudes.BIARRE_L).trim();
		if (sBIARRE.equals("")) sBIARRE = "#";

		String sCADORM = linea.substring(Posiciones.AC_CADORM_P, Posiciones.AC_CADORM_P+Longitudes.CADORM_L);
		sCADORM = Integer.toString(Integer.parseInt(sCADORM));
		String sCABANO = linea.substring(Posiciones.AC_CABANO_P, Posiciones.AC_CABANO_P+Longitudes.CABANO_L);
		sCABANO = Integer.toString(Integer.parseInt(sCABANO));

		String sBIGAPA = linea.substring(Posiciones.AC_BIGAPA_P, Posiciones.AC_BIGAPA_P+Longitudes.BIGAPA_L).trim();
		if (sBIGAPA.equals("")) sBIGAPA = "#";
			
		String sCAGAPA = linea.substring(Posiciones.AC_CAGAPA_P, Posiciones.AC_CAGAPA_P+Longitudes.CAGAPA_L);
		sCAGAPA = Integer.toString(Integer.parseInt(sCAGAPA));
		String sCASUTE = linea.substring(Posiciones.AC_CASUTE_P, Posiciones.AC_CASUTE_P+Longitudes.CASUTE_L);
		sCASUTE = Integer.toString(Integer.parseInt(sCASUTE));
	
		String sBILIPO = linea.substring(Posiciones.AC_BILIPO_P, Posiciones.AC_BILIPO_P+Longitudes.BILIPO_L).trim();
		if (sBILIPO.equals("")) sBILIPO = "#";

		String sBILIAC = linea.substring(Posiciones.AC_BILIAC_P, Posiciones.AC_BILIAC_P+Longitudes.BILIAC_L).trim();
		if (sBILIAC.equals("")) sBILIAC = "#";

		String sBILIUS = linea.substring(Posiciones.AC_BILIUS_P, Posiciones.AC_BILIUS_P+Longitudes.BILIUS_L).trim();
		if (sBILIUS.equals("")) sBILIUS = "#";

		String sBIBOIN = linea.substring(Posiciones.AC_BIBOIN_P, Posiciones.AC_BIBOIN_P+Longitudes.BIBOIN_L).trim();
		if (sBIBOIN.equals("")) sBIBOIN = "#";

		String sBICEFI = linea.substring(Posiciones.AC_BICEFI_P, Posiciones.AC_BICEFI_P+Longitudes.BICEFI_L).trim();
		if (sBICEFI.equals("")) sBICEFI = "#";

		String sCASUCB = linea.substring(Posiciones.AC_CASUCB_P, Posiciones.AC_CASUCB_P+Longitudes.CASUCB_L);
		sCASUCB = Integer.toString(Integer.parseInt(sCASUCB));
		String sCASUCS = linea.substring(Posiciones.AC_CASUCS_P, Posiciones.AC_CASUCS_P+Longitudes.CASUCS_L);
		sCASUCS = Integer.toString(Integer.parseInt(sCASUCS));

		String sFEACON = linea.substring(Posiciones.AC_FEACON_P, Posiciones.AC_FEACON_P+Longitudes.FEACON_L);

		String sIDAUTO = linea.substring(Posiciones.AC_IDAUTO_P, Posiciones.AC_IDAUTO_P+Longitudes.IDAUTO_L).trim();

		String sFEDEMA = linea.substring(Posiciones.AC_FEDEMA_P, Posiciones.AC_FEDEMA_P+Longitudes.FEDEMA_L);
	
		String sYNOCUR = linea.substring(Posiciones.AC_YNOCUR_P, Posiciones.AC_YNOCUR_P+Longitudes.YNOCUR_L).trim();
		String sOBRECO = linea.substring(Posiciones.AC_OBRECO_P, Posiciones.AC_OBRECO_P+Longitudes.OBRECO_L).trim();
		String sYNOLEC = linea.substring(Posiciones.AC_YNOLEC_P, Posiciones.AC_YNOLEC_P+Longitudes.YNOLEC_L).trim();
		String sNOLOJZ = linea.substring(Posiciones.AC_NOLOJZ_P, Posiciones.AC_NOLOJZ_P+Longitudes.NOLOJZ_L).trim();

		String sFEREDE = linea.substring(Posiciones.AC_FEREDE_P, Posiciones.AC_FEREDE_P+Longitudes.FEREDE_L);

		String sPOPROP = linea.substring(Posiciones.AC_POPROP_P, Posiciones.AC_POPROP_P+Longitudes.POPROP_L);
		sPOPROP = Integer.toString(Integer.parseInt(sPOPROP));
		String sCOGRAP = linea.substring(Posiciones.AC_COGRAP_P, Posiciones.AC_COGRAP_P+Longitudes.COGRAP_L);
		sCOGRAP = Integer.toString(Integer.parseInt(sCOGRAP));

		String sFEPREG = linea.substring(Posiciones.AC_FEPREG_P, Posiciones.AC_FEPREG_P+Longitudes.FEPREG_L);
		String sFEPHAC = linea.substring(Posiciones.AC_FEPHAC_P, Posiciones.AC_FEPHAC_P+Longitudes.FEPHAC_L);
		String sFEFOAC = linea.substring(Posiciones.AC_FEFOAC_P, Posiciones.AC_FEFOAC_P+Longitudes.FEFOAC_L);
		String sFEVACT = linea.substring(Posiciones.AC_FEVACT_P, Posiciones.AC_FEVACT_P+Longitudes.FEVACT_L);

		String sIMVACT = linea.substring(Posiciones.AC_IMVACT_P, Posiciones.AC_IMVACT_P+Longitudes.IMVACT_L);
		sIMVACT = Integer.toString(Integer.parseInt(sIMVACT));
		String sNUFIPR = linea.substring(Posiciones.AC_NUFIPR_P, Posiciones.AC_NUFIPR_P+Longitudes.NUFIPR_L);
		sNUFIPR = Integer.toString(Integer.parseInt(sNUFIPR));
		String sCOTPET = linea.substring(Posiciones.AC_COTPET_P, Posiciones.AC_COTPET_P+Longitudes.COTPET_L);
		sCOTPET = Integer.toString(Integer.parseInt(sCOTPET));
		
		String sFEEMPT = linea.substring(Posiciones.AC_FEEMPT_P, Posiciones.AC_FEEMPT_P+Longitudes.FEEMPT_L);
		String sFESORC = linea.substring(Posiciones.AC_FESORC_P, Posiciones.AC_FESORC_P+Longitudes.FESORC_L);
		String sFESODE = linea.substring(Posiciones.AC_FESODE_P, Posiciones.AC_FESODE_P+Longitudes.FESODE_L);
		String sFEREAC = linea.substring(Posiciones.AC_FEREAC_P, Posiciones.AC_FEREAC_P+Longitudes.FEREAC_L);

		String sCOXSIA = linea.substring(Posiciones.AC_COXSIA_P, Posiciones.AC_COXSIA_P+Longitudes.COXSIA_L);
	
		if (sCOXSIA.trim().equals(""))
			sCOXSIA = "0";
	
		String sNUJUZD = linea.substring(Posiciones.AC_NUJUZD_P, Posiciones.AC_NUJUZD_P+Longitudes.NUJUZD_L);
		sNUJUZD = Integer.toString(Integer.parseInt(sNUJUZD));

		String sNURCAT = linea.substring(Posiciones.AC_NURCAT_P, Posiciones.AC_NURCAT_P+Longitudes.NURCAT_L);

		String sNOMPRC = linea.substring(Posiciones.AC_NOMPRC_P, Posiciones.AC_NOMPRC_P+Longitudes.NOMPRC_L).trim();
		String sNUTPRC = linea.substring(Posiciones.AC_NUTPRC_P, Posiciones.AC_NUTPRC_P+Longitudes.NUTPRC_L).trim();
		String sNOMADC = linea.substring(Posiciones.AC_NOMADC_P, Posiciones.AC_NOMADC_P+Longitudes.NOMADC_L).trim();
		String sNUTADC = linea.substring(Posiciones.AC_NUTADC_P, Posiciones.AC_NUTADC_P+Longitudes.NUTADC_L).trim();

		String sIMPCOO = linea.substring(Posiciones.AC_IMPCOO_P, Posiciones.AC_IMPCOO_P+Longitudes.IMPCOO_L);
		sIMPCOO = Integer.toString(Integer.parseInt(sIMPCOO));

		String sCOENOR = linea.substring(Posiciones.AC_COENOR_P, Posiciones.AC_COENOR_P+Longitudes.COENOR_L); 

		String sCOSPAT = linea.substring(Posiciones.AC_COSPAT_P, Posiciones.AC_COSPAT_P+Longitudes.COSPAT_L);
		sCOSPAT = Integer.toString(Integer.parseInt(sCOSPAT));
		String sCOSPAS = linea.substring(Posiciones.AC_COSPAS_P, Posiciones.AC_COSPAS_P+Longitudes.COSPAS_L);
		sCOSPAS = Integer.toString(Integer.parseInt(sCOSPAS));

		String sIDCOL3 = linea.substring(Posiciones.AC_IDCOL3_P, Posiciones.AC_IDCOL3_P+Longitudes.IDCOL3_L); 

		String sBIOBNU = linea.substring(Posiciones.AC_BIOBNU_P, Posiciones.AC_BIOBNU_P+Longitudes.BIOBNU_L).trim();
		if (sBIOBNU.equals("")) sBIOBNU = "#";

		String sPOBRAR = linea.substring(Posiciones.AC_POBRAR_P, Posiciones.AC_POBRAR_P+Longitudes.POBRAR_L);
		sPOBRAR = Integer.toString(Integer.parseInt(sPOBRAR));

		
		
		
		
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

	public static String escribirActivo (Activo activo)
	{
	
        String sCOACES = activo.getCOACES();
        String sNUINMU = activo.getNUINMU();
        String sCOSOPA = activo.getCOSOPA();
        String sCOENAE = activo.getCOENAE();
        String sCOESEN = activo.getCOESEN();
        String sNOVIAS = activo.getNOVIAS();
        String sNUPOAC = activo.getNUPOAC();
        String sNUESAC = activo.getNUESAC();
        String sNUPIAC = activo.getNUPIAC();
        String sNUPUAC = activo.getNUPUAC();
        String sNOMUIN = activo.getNOMUIN();
        String sCOPRAE = activo.getCOPRAE();
        String sNOPRAC = activo.getNOPRAC();
        String sCOPOIN = activo.getCOPOIN();
        String sFEREAP = activo.getFEREAP();
        String sCOREAE = activo.getCOREAE();
        String sFEINAU = activo.getFEINAU();
        String sFESOPO = activo.getFESOPO();
        String sFESEPO = activo.getFESEPO();
        String sFEREPO = activo.getFEREPO();
        String sFEADAC = activo.getFEADAC();
        String sCODIJU = activo.getCODIJU();
        String sCOSJUP = activo.getCOSJUP();
        String sCOSTLI = activo.getCOSTLI();
        String sCOSCAR = activo.getCOSCAR();
        String sCOESVE = activo.getCOESVE();
        String sCOTSIN = activo.getCOTSIN();
        String sNUFIRE = activo.getNUFIRE();
        String sNUREGP = activo.getNUREGP();
        String sNOMUI0 = activo.getNOMUI0();
        String sNULIBE = activo.getNULIBE();
        String sNUTOME = activo.getNUTOME();
        String sNUFOLE = activo.getNUFOLE();
        String sNUINSR = activo.getNUINSR();
        String sCOSOCU = activo.getCOSOCU();
        String sCOXPRO = activo.getCOXPRO();
        String sFESOLA = activo.getFESOLA();
        String sFESELA = activo.getFESELA();
        String sFERELA = activo.getFERELA();
        String sFERLLA = activo.getFERLLA();
        String sCASPRE = activo.getCASPRE();
        String sCASUTR = activo.getCASUTR();
        String sCASUTC = activo.getCASUTC();
        String sCASUTG = activo.getCASUTG();
        String sBIARRE = activo.getBIARRE(); 
        String sCADORM = activo.getCADORM(); 
        String sCABANO = activo.getCABANO(); 
        String sBIGAPA = activo.getBIGAPA(); 
        String sCAGAPA = activo.getCAGAPA(); 
        String sCASUTE = activo.getCASUTE(); 
        String sBILIPO = activo.getBILIPO(); 
        String sBILIAC = activo.getBILIAC(); 
        String sBILIUS = activo.getBILIUS(); 
        String sBIBOIN = activo.getBIBOIN(); 
        String sBICEFI = activo.getBICEFI(); 
        String sCASUCB = activo.getCASUCB(); 
        String sCASUCS = activo.getCASUCS(); 
        String sFEACON = activo.getFEACON(); 
        String sIDAUTO = activo.getIDAUTO(); 
        String sFEDEMA = activo.getFEDEMA(); 
        String sYNOCUR = activo.getYNOCUR(); 
        String sOBRECO = activo.getOBRECO(); 
        String sYNOLEC = activo.getYNOLEC(); 
        String sNOLOJZ = activo.getNOLOJZ(); 
        String sFEREDE = activo.getFEREDE(); 
        String sPOPROP = activo.getPOPROP(); 
        String sCOGRAP = activo.getCOGRAP(); 
        String sFEPREG = activo.getFEPREG(); 
        String sFEPHAC = activo.getFEPHAC(); 
        String sFEFOAC = activo.getFEFOAC(); 
        String sFEVACT = activo.getFEVACT(); 
        String sIMVACT = activo.getIMVACT(); 
        String sNUFIPR = activo.getNUFIPR(); 
        String sCOTPET = activo.getCOTPET(); 
        String sFEEMPT = activo.getFEEMPT(); 
        String sFESORC = activo.getFESORC(); 
        String sFESODE = activo.getFESODE(); 
        String sFEREAC = activo.getFEREAC(); 
        String sCOXSIA = activo.getCOXSIA(); 
        String sNUJUZD = activo.getNUJUZD(); 
        String sNURCAT = activo.getNURCAT(); 
        String sNOMPRC = activo.getNOMPRC(); 
        String sNUTPRC = activo.getNUTPRC(); 
        String sNOMADC = activo.getNOMADC(); 
        String sNUTADC = activo.getNUTADC(); 
        String sIMPCOO = activo.getIMPCOO(); 
        String sCOENOR = activo.getCOENOR(); 
        String sCOSPAT = activo.getCOSPAT(); 
        String sCOSPAS = activo.getCOSPAS(); 
        String sIDCOL3 = activo.getIDCOL3(); 
        String sBIOBNU = activo.getBIOBNU(); 
        String sPOBRAR = activo.getPOBRAR();		
		
		return sCOACES + sNUINMU + sCOSOPA + sCOENAE + sCOESEN + sNOVIAS
				+ sNUPOAC + sNUESAC + sNUPIAC + sNUPUAC + sNOMUIN + sCOPRAE
				+ sNOPRAC + sCOPOIN + sFEREAP + sCOREAE + sFEINAU + sFESOPO
				+ sFESEPO + sFEREPO + sFEADAC + sCODIJU + sCOSJUP + sCOSTLI
				+ sCOSCAR + sCOESVE + sCOTSIN + sNUFIRE + sNUREGP + sNOMUI0
				+ sNULIBE + sNUTOME + sNUFOLE + sNUINSR + sCOSOCU + sCOXPRO
				+ sFESOLA + sFESELA + sFERELA + sFERLLA + sCASPRE + sCASUTR
				+ sCASUTC + sCASUTG + sBIARRE + sCADORM + sCABANO + sBIGAPA
				+ sCAGAPA + sCASUTE + sBILIPO + sBILIAC + sBILIUS + sBIBOIN
				+ sBICEFI + sCASUCB + sCASUCS + sFEACON + sIDAUTO + sFEDEMA
				+ sYNOCUR + sOBRECO + sYNOLEC + sNOLOJZ + sFEREDE + sPOPROP
				+ sCOGRAP + sFEPREG + sFEPHAC + sFEFOAC + sFEVACT + sIMVACT
				+ sNUFIPR + sCOTPET + sFEEMPT + sFESORC + sFESODE + sFEREAC
				+ sCOXSIA + sNUJUZD + sNURCAT + sNOMPRC + sNUTPRC + sNOMADC
				+ sNUTADC + sIMPCOO + sCOENOR + sCOSPAT + sCOSPAS + sIDCOL3
				+ sBIOBNU + sPOBRAR;
	}

	
	public static Gasto leerGasto (String linea)
	{

		String sCOACES = linea.substring(Posiciones.GA_COACES_P, Posiciones.GA_COACES_P+Longitudes.COACES_L);
		sCOACES = Integer.toString(Integer.parseInt(sCOACES));
		String sCOGRUG = linea.substring(Posiciones.GA_COGRUG_P, Posiciones.GA_COGRUG_P+Longitudes.COGRUG_L);
		sCOGRUG = Integer.toString(Integer.parseInt(sCOGRUG));
		String sCOTPGA = linea.substring(Posiciones.GA_COTPGA_P, Posiciones.GA_COTPGA_P+Longitudes.COTPGA_L);
		sCOTPGA = Integer.toString(Integer.parseInt(sCOTPGA));
		String sCOSBGA = linea.substring(Posiciones.GA_COSBGA_P, Posiciones.GA_COSBGA_P+Longitudes.COSBGA_L);
		sCOSBGA = Integer.toString(Integer.parseInt(sCOSBGA));
		
		String sPTPAGO = linea.substring(Posiciones.GA_PTPAGO_P, Posiciones.GA_PTPAGO_P+Longitudes.PTPAGO_L);
		if (sPTPAGO.trim().equals(""))
			sPTPAGO = "0";

		String sFEDEVE = linea.substring(Posiciones.GA_FEDEVE_P, Posiciones.GA_FEDEVE_P+Longitudes.FEDEVE_L);
		String sFFGTVP = linea.substring(Posiciones.GA_FFGTVP_P, Posiciones.GA_FFGTVP_P+Longitudes.FFGTVP_L);
		String sFEPAGA = linea.substring(Posiciones.GA_FEPAGA_P, Posiciones.GA_FEPAGA_P+Longitudes.FEPAGA_L);
		String sFELIPG = linea.substring(Posiciones.GA_FELIPG_P, Posiciones.GA_FELIPG_P+Longitudes.FELIPG_L);

		String sCOSIGA = linea.substring(Posiciones.GA_COSIGA_P, Posiciones.GA_COSIGA_P+Longitudes.COSIGA_L);
		sCOSIGA = Integer.toString(Integer.parseInt(sCOSIGA));
		
		String sFEEESI = linea.substring(Posiciones.GA_FEEESI_P, Posiciones.GA_FEEESI_P+Longitudes.FEEESI_L);
		String sFEECOI = linea.substring(Posiciones.GA_FEECOI_P, Posiciones.GA_FEECOI_P+Longitudes.FEECOI_L);
		String sFEEAUI = linea.substring(Posiciones.GA_FEEAUI_P, Posiciones.GA_FEEAUI_P+Longitudes.FEEAUI_L);
		String sFEEPAI = linea.substring(Posiciones.GA_FEEPAI_P, Posiciones.GA_FEEPAI_P+Longitudes.FEEPAI_L);
	
		String sIMNGAS = linea.substring(Posiciones.GA_IMNGAS_P, Posiciones.GA_IMNGAS_P+Longitudes.IMNGAS_L);
		sIMNGAS = Integer.toString(Integer.parseInt(sIMNGAS));
		
		String sYCOS02 = linea.substring(Posiciones.GA_YCOS02_P, Posiciones.GA_YCOS02_P+Longitudes.YCOS02_L);
		
		String sIMRGAS = linea.substring(Posiciones.GA_IMRGAS_P, Posiciones.GA_IMRGAS_P+Longitudes.IMRGAS_L);
		sIMRGAS = Integer.toString(Integer.parseInt(sIMRGAS));

		String sYCOS04 = linea.substring(Posiciones.GA_YCOS04_P, Posiciones.GA_YCOS04_P+Longitudes.YCOS04_L);
		
		String sIMDGAS = linea.substring(Posiciones.GA_IMDGAS_P, Posiciones.GA_IMDGAS_P+Longitudes.IMDGAS_L);
		sIMDGAS = Integer.toString(Integer.parseInt(sIMDGAS));
		
		String sYCOS06 = linea.substring(Posiciones.GA_YCOS06_P, Posiciones.GA_YCOS06_P+Longitudes.YCOS06_L);
		
		String sIMCOST = linea.substring(Posiciones.GA_IMCOST_P, Posiciones.GA_IMCOST_P+Longitudes.IMCOST_L);
		sIMCOST = Integer.toString(Integer.parseInt(sIMCOST));
		
		String sYCOS08 = linea.substring(Posiciones.GA_YCOS08_P, Posiciones.GA_YCOS08_P+Longitudes.YCOS08_L);
		
		String sIMOGAS = linea.substring(Posiciones.GA_IMOGAS_P, Posiciones.GA_IMOGAS_P+Longitudes.IMOGAS_L);
		sIMOGAS = Integer.toString(Integer.parseInt(sIMOGAS));
		
		String sYCOS10 = linea.substring(Posiciones.GA_YCOS10_P, Posiciones.GA_YCOS10_P+Longitudes.YCOS10_L);
		
		String sIMDTGA = linea.substring(Posiciones.GA_IMDTGA_P, Posiciones.GA_IMDTGA_P+Longitudes.IMDTGA_L);
		sIMDTGA = Integer.toString(Integer.parseInt(sIMDTGA));
		String sCOUNMO = linea.substring(Posiciones.GA_COUNMO_P, Posiciones.GA_COUNMO_P+Longitudes.COUNMO_L);
		sCOUNMO = Integer.toString(Integer.parseInt(sCOUNMO));
		String sIMIMGA = linea.substring(Posiciones.GA_IMIMGA_P, Posiciones.GA_IMIMGA_P+Longitudes.IMIMGA_L);
		sIMIMGA = Integer.toString(Integer.parseInt(sIMIMGA));
		String sCOIMPT = linea.substring(Posiciones.GA_COIMPT_P, Posiciones.GA_COIMPT_P+Longitudes.COIMPT_L);
		sCOIMPT = Integer.toString(Integer.parseInt(sCOIMPT));
		String sCOTNEG = linea.substring(Posiciones.GA_COTNEG_P, Posiciones.GA_COTNEG_P+Longitudes.COTNEG_L);
		sCOTNEG = Integer.toString(Integer.parseInt(sCOTNEG));
		String sCOENCX = linea.substring(Posiciones.GA_COENCX_P, Posiciones.GA_COENCX_P+Longitudes.COENCX_L);
		sCOENCX = Integer.toString(Integer.parseInt(sCOENCX));
		String sCOOFCX = linea.substring(Posiciones.GA_COOFCX_P, Posiciones.GA_COOFCX_P+Longitudes.COOFCX_L);
		sCOOFCX = Integer.toString(Integer.parseInt(sCOOFCX));
		String sNUCONE = linea.substring(Posiciones.GA_NUCONE_P, Posiciones.GA_NUCONE_P+Longitudes.NUCONE_L);
		sNUCONE = Integer.toString(Integer.parseInt(sNUCONE));
		String sNUPROF = linea.substring(Posiciones.GA_NUPROF_P, Posiciones.GA_NUPROF_P+Longitudes.NUPROF_L);
		sNUPROF = Integer.toString(Integer.parseInt(sNUPROF));

		String sFEAGTO = linea.substring(Posiciones.GA_FEAGTO_P, Posiciones.GA_FEAGTO_P+Longitudes.FEAGTO_L);

		String sCOMONA = linea.substring(Posiciones.GA_COMONA_P, Posiciones.GA_COMONA_P+Longitudes.COMONA_L);
		sCOMONA = Integer.toString(Integer.parseInt(sCOMONA));
		
		String sBIAUTO = linea.substring(Posiciones.GA_BIAUTO_P, Posiciones.GA_BIAUTO_P+Longitudes.BIAUTO_L);
		
		String sFEAUFA = linea.substring(Posiciones.GA_FEAUFA_P, Posiciones.GA_FEAUFA_P+Longitudes.FEAUFA_L);

		String sCOTERR = linea.substring(Posiciones.GA_COTERR_P, Posiciones.GA_COTERR_P+Longitudes.COTERR_L);
		sCOTERR = Integer.toString(Integer.parseInt(sCOTERR));

		String sFMPAGN = linea.substring(Posiciones.GA_FMPAGN_P, Posiciones.GA_FMPAGN_P+Longitudes.FMPAGN_L);
		String sFEPGPR = linea.substring(Posiciones.GA_FEPGPR_P, Posiciones.GA_FEPGPR_P+Longitudes.FEPGPR_L);
		String sFEAPLI = linea.substring(Posiciones.GA_FEAPLI_P, Posiciones.GA_FEAPLI_P+Longitudes.FEAPLI_L);

		String sCOAPII = linea.substring(Posiciones.GA_COAPII_P, Posiciones.GA_COAPII_P+Longitudes.COAPII_L);
		String sCOSPII = linea.substring(Posiciones.GA_COSPII_P, Posiciones.GA_COSPII_P+Longitudes.COSPII_L);

		String sNUCLII = linea.substring(Posiciones.GA_NUCLII_P, Posiciones.GA_NUCLII_P+Longitudes.NUCLII_L);                                              
		
		
		
		
		return new Gasto(sCOACES, sCOGRUG, sCOTPGA, sCOSBGA, sPTPAGO, sFEDEVE,
				sFFGTVP, sFEPAGA, sFELIPG, sCOSIGA, sFEEESI, sFEECOI, sFEEAUI,
				sFEEPAI, sIMNGAS, sYCOS02, sIMRGAS, sYCOS04, sIMDGAS, sYCOS06,
				sIMCOST, sYCOS08, sIMOGAS, sYCOS10, sIMDTGA, sCOUNMO, sIMIMGA,
				sCOIMPT, sCOTNEG, sCOENCX, sCOOFCX, sNUCONE, sNUPROF, sFEAGTO,
				sCOMONA, sBIAUTO, sFEAUFA, sCOTERR, sFMPAGN, sFEPGPR, sFEAPLI,
				sCOAPII, sCOSPII, sNUCLII);
	}
	
	public static String escribirGasto (Gasto gasto)
	{

		String sCOACES = gasto.getCOACES();
		String sCOGRUG = gasto.getCOGRUG();
		String sCOTPGA = gasto.getCOTPGA();
		String sCOSBGA = gasto.getCOSBGA();
		String sPTPAGO = gasto.getPTPAGO();
		String sFEDEVE = gasto.getFEDEVE();
		String sFFGTVP = gasto.getFFGTVP();
		String sFEPAGA = gasto.getFEPAGA();
		String sFELIPG = gasto.getFELIPG();
		String sCOSIGA = gasto.getCOSIGA();
		String sFEEESI = gasto.getFEEESI();
		String sFEECOI = gasto.getFEECOI();
		String sFEEAUI = gasto.getFEEAUI();
		String sFEEPAI = gasto.getFEEPAI();
		String sIMNGAS = gasto.getIMNGAS();
		String sYCOS02 = gasto.getYCOS02();
		String sIMRGAS = gasto.getIMRGAS();
		String sYCOS04 = gasto.getYCOS04();
		String sIMDGAS = gasto.getIMDGAS();
		String sYCOS06 = gasto.getYCOS06();
		String sIMCOST = gasto.getIMCOST();
		String sYCOS08 = gasto.getYCOS08();
		String sIMOGAS = gasto.getIMOGAS();
		String sYCOS10 = gasto.getYCOS10();
		String sIMDTGA = gasto.getIMDTGA();
		String sCOUNMO = gasto.getCOUNMO();
		String sIMIMGA = gasto.getIMIMGA();
		String sCOIMPT = gasto.getCOIMPT();
		String sCOTNEG = gasto.getCOTNEG();
		String sCOENCX = gasto.getCOENCX();
		String sCOOFCX = gasto.getCOOFCX();
		String sNUCONE = gasto.getNUCONE();
		String sNUPROF = gasto.getNUPROF();
		String sFEAGTO = gasto.getFEAGTO();
		String sCOMONA = gasto.getCOMONA();
		String sBIAUTO = gasto.getBIAUTO();
		String sFEAUFA = gasto.getFEAUFA();
		String sCOTERR = gasto.getCOTERR();
		String sFMPAGN = gasto.getFMPAGN();
		String sFEPGPR = gasto.getFEPGPR();
		String sFEAPLI = gasto.getFEAPLI();
		String sCOAPII = gasto.getCOAPII();
		String sCOSPII = gasto.getCOSPII();
		String sNUCLII = gasto.getNUCLII();
		
		
		
		
		return sCOACES + sCOGRUG + sCOTPGA + sCOSBGA + sPTPAGO + sFEDEVE
				+ sFFGTVP + sFEPAGA + sFELIPG + sCOSIGA + sFEEESI + sFEECOI
				+ sFEEAUI + sFEEPAI + sIMNGAS + sYCOS02 + sIMRGAS + sYCOS04
				+ sIMDGAS + sYCOS06 + sIMCOST + sYCOS08 + sIMOGAS + sYCOS10
				+ sIMDTGA + sCOUNMO + sIMIMGA + sCOIMPT + sCOTNEG + sCOENCX
				+ sCOOFCX + sNUCONE + sNUPROF + sFEAGTO + sCOMONA + sBIAUTO
				+ sFEAUFA + sCOTERR + sFMPAGN + sFEPGPR + sFEAPLI + sCOAPII
				+ sCOSPII + sNUCLII;
	}
	
	public static Comunidad leerComunidad (String linea)
	{

		String sCODTRN = linea.substring(Posiciones.E1_CODTRN_P, Posiciones.E1_CODTRN_P+Longitudes.CODTRN_L);
		String sCOTDOR = linea.substring(Posiciones.E1_COTDOR_P, Posiciones.E1_COTDOR_P+Longitudes.COTDOR_L);
		String sIDPROV = linea.substring(Posiciones.E1_IDPROV_P, Posiciones.E1_IDPROV_P+Longitudes.IDPROV_L);
		String sCOACCI = linea.substring(Posiciones.E1_COACCI_P, Posiciones.E1_COACCI_P+Longitudes.COACCI_L);
		String sCOENGP = linea.substring(Posiciones.E1_COENGP_P, Posiciones.E1_COENGP_P+Longitudes.COENGP_L);
		String sCOCLDO = linea.substring(Posiciones.E1_COCLDO_P, Posiciones.E1_COCLDO_P+Longitudes.COCLDO_L);
		String sNUDCOM = linea.substring(Posiciones.E1_NUDCOM_P, Posiciones.E1_NUDCOM_P+Longitudes.NUDCOM_L);
		String sBITC10 = linea.substring(Posiciones.E1_BITC10_P, Posiciones.E1_BITC10_P+Longitudes.BITC10_L);
		String sCOACES = linea.substring(Posiciones.E1_COACES_P, Posiciones.E1_COACES_P+Longitudes.COACES_L);
		String sBITC01 = linea.substring(Posiciones.E1_BITC01_P, Posiciones.E1_BITC01_P+Longitudes.BITC01_L);
		String sNOMCOC = linea.substring(Posiciones.E1_NOMCOC_P, Posiciones.E1_NOMCOC_P+Longitudes.NOMCOC_L);
		String sBITC02 = linea.substring(Posiciones.E1_BITC02_P, Posiciones.E1_BITC02_P+Longitudes.BITC02_L);
		String sNODCCO = linea.substring(Posiciones.E1_NODCCO_P, Posiciones.E1_NODCCO_P+Longitudes.NODCCO_L);
		String sBITC03 = linea.substring(Posiciones.E1_BITC03_P, Posiciones.E1_BITC03_P+Longitudes.BITC03_L);
		String sNOMPRC = linea.substring(Posiciones.E1_NOMPRC_P, Posiciones.E1_NOMPRC_P+Longitudes.NOMPRC_L);
		String sBITC04 = linea.substring(Posiciones.E1_BITC04_P, Posiciones.E1_BITC04_P+Longitudes.BITC04_L);
		String sNUTPRC = linea.substring(Posiciones.E1_NUTPRC_P, Posiciones.E1_NUTPRC_P+Longitudes.NUTPRC_L);
		String sBITC05 = linea.substring(Posiciones.E1_BITC05_P, Posiciones.E1_BITC05_P+Longitudes.BITC05_L);
		String sNOMADC = linea.substring(Posiciones.E1_NOMADC_P, Posiciones.E1_NOMADC_P+Longitudes.NOMADC_L);
		String sBITC06 = linea.substring(Posiciones.E1_BITC06_P, Posiciones.E1_BITC06_P+Longitudes.BITC06_L);
		String sNUTADC = linea.substring(Posiciones.E1_NUTADC_P, Posiciones.E1_NUTADC_P+Longitudes.NUTADC_L);
		String sBITC07 = linea.substring(Posiciones.E1_BITC07_P, Posiciones.E1_BITC07_P+Longitudes.BITC07_L);
		String sNODCAD = linea.substring(Posiciones.E1_NODCAD_P, Posiciones.E1_NODCAD_P+Longitudes.NODCAD_L);
		String sBITC08 = linea.substring(Posiciones.E1_BITC08_P, Posiciones.E1_BITC08_P+Longitudes.BITC08_L);
		String sNUCCEN = linea.substring(Posiciones.E1_NUCCEN_P, Posiciones.E1_NUCCEN_P+Longitudes.NUCCEN_L);
		String sNUCCOF = linea.substring(Posiciones.E1_NUCCOF_P, Posiciones.E1_NUCCOF_P+Longitudes.NUCCOF_L);
		String sNUCCDI = linea.substring(Posiciones.E1_NUCCDI_P, Posiciones.E1_NUCCDI_P+Longitudes.NUCCDI_L);
		String sNUCCNT = linea.substring(Posiciones.E1_NUCCNT_P, Posiciones.E1_NUCCNT_P+Longitudes.NUCCNT_L);
		String sBITC09 = linea.substring(Posiciones.E1_BITC09_P, Posiciones.E1_BITC09_P+Longitudes.BITC09_L);
		String sOBTEXC = linea.substring(Posiciones.E1_OBTEXC_P, Posiciones.E1_OBTEXC_P+Longitudes.OBTEXC_L);
		String sOBDEER = linea.substring(Posiciones.E1_OBDEER_P, Posiciones.E1_OBDEER_P+Longitudes.OBDEER_L);

		
		
		return new Comunidad(sCODTRN, sCOTDOR, sIDPROV, sCOACCI, sCOENGP,
				sCOCLDO, sNUDCOM, sBITC10, sCOACES, sBITC01, sNOMCOC, sBITC02,
				sNODCCO, sBITC03, sNOMPRC, sBITC04, sNUTPRC, sBITC05, sNOMADC,
				sBITC06, sNUTADC, sBITC07, sNODCAD, sBITC08, sNUCCEN, sNUCCOF,
				sNUCCDI, sNUCCNT, sBITC09, sOBTEXC, sOBDEER);
	}
	
	public static String escribirComunidad (Comunidad comunidad)
	{

        String sCODTRN = comunidad.getCODTRN();
        String sCOTDOR = comunidad.getCOTDOR();
        String sIDPROV = comunidad.getIDPROV();
        String sCOACCI = comunidad.getCOACCI();
        String sCOENGP = comunidad.getCOENGP();
        String sCOCLDO = comunidad.getCOCLDO();
        String sNUDCOM = comunidad.getNUDCOM();
        String sBITC10 = comunidad.getBITC10();
        String sCOACES = comunidad.getCOACES();
        String sBITC01 = comunidad.getBITC01();
        String sNOMCOC = comunidad.getNOMCOC();
        String sBITC02 = comunidad.getBITC02();
        String sNODCCO = comunidad.getNODCCO();
        String sBITC03 = comunidad.getBITC03();
        String sNOMPRC = comunidad.getNOMPRC();
        String sBITC04 = comunidad.getBITC04();
        String sNUTPRC = comunidad.getNUTPRC();
        String sBITC05 = comunidad.getBITC05();
        String sNOMADC = comunidad.getNOMADC();
        String sBITC06 = comunidad.getBITC06();
        String sNUTADC = comunidad.getNUTADC();
        String sBITC07 = comunidad.getBITC07();
        String sNODCAD = comunidad.getNODCAD();
        String sBITC08 = comunidad.getBITC08();
        String sNUCCEN = comunidad.getNUCCEN();
        String sNUCCOF = comunidad.getNUCCOF();
        String sNUCCDI = comunidad.getNUCCDI();
        String sNUCCNT = comunidad.getNUCCNT();
        String sBITC09 = comunidad.getBITC09();
        String sOBTEXC = comunidad.getOBTEXC();
        String sOBDEER = comunidad.getOBDEER();
		
		
		return sCODTRN + sCOTDOR + sIDPROV + sCOACCI + sCOENGP + sCOCLDO
				+ sNUDCOM + sBITC10 + sCOACES + sBITC01 + sNOMCOC + sBITC02
				+ sNODCCO + sBITC03 + sNOMPRC + sBITC04 + sNUTPRC + sBITC05
				+ sNOMADC + sBITC06 + sNUTADC + sBITC07 + sNODCAD + sBITC08
				+ sNUCCEN + sNUCCOF + sNUCCDI + sNUCCNT + sBITC09 + sOBTEXC
				+ sOBDEER;
	}

	public static Cuota leerCuota (String linea)
	{

		String sCODTRN = linea.substring(Posiciones.E2_CODTRN_P, Posiciones.E2_CODTRN_P+Longitudes.CODTRN_L);
		String sCOTDOR = linea.substring(Posiciones.E2_COTDOR_P, Posiciones.E2_COTDOR_P+Longitudes.COTDOR_L);
		String sIDPROV = linea.substring(Posiciones.E2_IDPROV_P, Posiciones.E2_IDPROV_P+Longitudes.IDPROV_L);
		String sCOACCI = linea.substring(Posiciones.E2_COACCI_P, Posiciones.E2_COACCI_P+Longitudes.COACCI_L);
		String sCOCLDO = linea.substring(Posiciones.E2_COCLDO_P, Posiciones.E2_COCLDO_P+Longitudes.COCLDO_L);
		String sNUDCOM = linea.substring(Posiciones.E2_NUDCOM_P, Posiciones.E2_NUDCOM_P+Longitudes.NUDCOM_L);
		String sCOENGP = linea.substring(Posiciones.E2_COENGP_P, Posiciones.E2_COENGP_P+Longitudes.COENGP_L);
		String sCOACES = linea.substring(Posiciones.E2_COACES_P, Posiciones.E2_COACES_P+Longitudes.COACES_L);
		String sCOGRUG = linea.substring(Posiciones.E2_COGRUG_P, Posiciones.E2_COGRUG_P+Longitudes.COGRUG_L);
		String sCOTACA = linea.substring(Posiciones.E2_COTACA_P, Posiciones.E2_COTACA_P+Longitudes.COTACA_L);
		String sCOSBAC = linea.substring(Posiciones.E2_COSBAC_P, Posiciones.E2_COSBAC_P+Longitudes.COSBAC_L);
		String sBITC11 = linea.substring(Posiciones.E2_BITC11_P, Posiciones.E2_BITC11_P+Longitudes.BITC11_L);
		String sFIPAGO = linea.substring(Posiciones.E2_FIPAGO_P, Posiciones.E2_FIPAGO_P+Longitudes.FIPAGO_L);
		String sBITC12 = linea.substring(Posiciones.E2_BITC12_P, Posiciones.E2_BITC12_P+Longitudes.BITC12_L);
		String sFFPAGO = linea.substring(Posiciones.E2_FFPAGO_P, Posiciones.E2_FFPAGO_P+Longitudes.FFPAGO_L);
		String sBITC13 = linea.substring(Posiciones.E2_BITC13_P, Posiciones.E2_BITC13_P+Longitudes.BITC13_L);
		String sIMCUCO = linea.substring(Posiciones.E2_IMCUCO_P, Posiciones.E2_IMCUCO_P+Longitudes.IMCUCO_L);
		String sBITC14 = linea.substring(Posiciones.E2_BITC14_P, Posiciones.E2_BITC14_P+Longitudes.BITC14_L);
		String sFAACTA = linea.substring(Posiciones.E2_FAACTA_P, Posiciones.E2_FAACTA_P+Longitudes.FAACTA_L);
		String sBITC15 = linea.substring(Posiciones.E2_BITC15_P, Posiciones.E2_BITC15_P+Longitudes.BITC15_L);
		String sPTPAGO = linea.substring(Posiciones.E2_PTPAGO_P, Posiciones.E2_PTPAGO_P+Longitudes.PTPAGO_L);
		String sBITC09 = linea.substring(Posiciones.E2_BITC09_P, Posiciones.E2_BITC09_P+Longitudes.BITC09_L);
		String sOBTEXC = linea.substring(Posiciones.E2_OBTEXC_P, Posiciones.E2_OBTEXC_P+Longitudes.OBTEXC_L);
		String sOBDEER = linea.substring(Posiciones.E2_OBDEER_P, Posiciones.E2_OBDEER_P+Longitudes.OBDEER_L);
		
		
		return new Cuota(sCODTRN, sCOTDOR, sIDPROV, sCOACCI, sCOCLDO, sNUDCOM,
				sCOENGP, sCOACES, sCOGRUG, sCOTACA, sCOSBAC, sBITC11, sFIPAGO,
				sBITC12, sFFPAGO, sBITC13, sIMCUCO, sBITC14, sFAACTA, sBITC15,
				sPTPAGO, sBITC09, sOBTEXC, sOBDEER);
	}
	
	public static String escribirCuota (Cuota cuota)
	{

        String sCODTRN = cuota.getCODTRN();
        String sCOTDOR = cuota.getCOTDOR();
        String sIDPROV = cuota.getIDPROV();
        String sCOACCI = cuota.getCOACCI();
        String sCOCLDO = cuota.getCOCLDO();
        String sNUDCOM = cuota.getNUDCOM();
        String sCOENGP = cuota.getCOENGP();
        String sCOACES = cuota.getCOACES();
        String sCOGRUG = cuota.getCOGRUG();
        String sCOTACA = cuota.getCOTACA();
        String sCOSBAC = cuota.getCOSBAC();
        String sBITC11 = cuota.getBITC11();
        String sFIPAGO = cuota.getFIPAGO();
        String sBITC12 = cuota.getBITC12();
        String sFFPAGO = cuota.getFFPAGO();
        String sBITC13 = cuota.getBITC13();
        String sIMCUCO = cuota.getIMCUCO();
        String sBITC14 = cuota.getBITC14();
        String sFAACTA = cuota.getFAACTA();
        String sBITC15 = cuota.getBITC15();
        String sPTPAGO = cuota.getPTPAGO();
        String sBITC09 = cuota.getBITC09();
        String sOBTEXC = cuota.getOBTEXC();
        String sOBDEER = cuota.getOBDEER();

		
		
		return sCODTRN + sCOTDOR + sIDPROV + sCOACCI + sCOCLDO + sNUDCOM
				+ sCOENGP + sCOACES + sCOGRUG + sCOTACA + sCOSBAC + sBITC11
				+ sFIPAGO + sBITC12 + sFFPAGO + sBITC13 + sIMCUCO + sBITC14
				+ sFAACTA + sBITC15 + sPTPAGO + sBITC09 + sOBTEXC + sOBDEER;
	}
	
	public static ReferenciaCatastral leerReferenciaCatastral (String linea)
	{

		String sCODTRN = linea.substring(Posiciones.E3_CODTRN_P, Posiciones.E3_CODTRN_P+Longitudes.CODTRN_L);
		String sCOTDOR = linea.substring(Posiciones.E3_COTDOR_P, Posiciones.E3_COTDOR_P+Longitudes.COTDOR_L);
		String sIDPROV = linea.substring(Posiciones.E3_IDPROV_P, Posiciones.E3_IDPROV_P+Longitudes.IDPROV_L);
		String sCOACCI = linea.substring(Posiciones.E3_COACCI_P, Posiciones.E3_COACCI_P+Longitudes.COACCI_L);
		String sCOENGP = linea.substring(Posiciones.E3_COENGP_P, Posiciones.E3_COENGP_P+Longitudes.COENGP_L);
		String sCOACES = linea.substring(Posiciones.E3_COACES_P, Posiciones.E3_COACES_P+Longitudes.COACES_L);
		String sNURCAT = linea.substring(Posiciones.E3_NURCAT_P, Posiciones.E3_NURCAT_P+Longitudes.NURCAT_L);
		String sBITC16 = linea.substring(Posiciones.E3_BITC16_P, Posiciones.E3_BITC16_P+Longitudes.BITC16_L);
		String sTIRCAT = linea.substring(Posiciones.E3_TIRCAT_P, Posiciones.E3_TIRCAT_P+Longitudes.TIRCAT_L);
		String sBITC17 = linea.substring(Posiciones.E3_BITC17_P, Posiciones.E3_BITC17_P+Longitudes.BITC17_L);
		String sENEMIS = linea.substring(Posiciones.E3_ENEMIS_P, Posiciones.E3_ENEMIS_P+Longitudes.ENEMIS_L);
		String sCOTEXA = linea.substring(Posiciones.E3_COTEXA_P, Posiciones.E3_COTEXA_P+Longitudes.COTEXA_L);
		String sBITC09 = linea.substring(Posiciones.E3_BITC09_P, Posiciones.E3_BITC09_P+Longitudes.BITC09_L);
		String sOBTEXC = linea.substring(Posiciones.E3_OBTEXC_P, Posiciones.E3_OBTEXC_P+Longitudes.OBTEXC_L);
		String sOBDEER = linea.substring(Posiciones.E3_OBDEER_P, Posiciones.E3_OBDEER_P+Longitudes.OBDEER_L);
		
		
		return new ReferenciaCatastral(sCODTRN, sCOTDOR, sIDPROV, sCOACCI,
				sCOENGP, sCOACES, sNURCAT, sBITC16, sTIRCAT, sBITC17, sENEMIS,
				sCOTEXA, sBITC09, sOBTEXC, sOBDEER);
	}
	
	public static String escribirReferenciaCatastral (ReferenciaCatastral referenciacatrastral)
	{

        String sCODTRN = referenciacatrastral.getCODTRN();
        String sCOTDOR = referenciacatrastral.getCOTDOR();
        String sIDPROV = referenciacatrastral.getIDPROV();
        String sCOACCI = referenciacatrastral.getCOACCI();
        String sCOENGP = referenciacatrastral.getCOENGP();
        String sCOACES = referenciacatrastral.getCOACES();
        String sNURCAT = referenciacatrastral.getNURCAT();
        String sBITC16 = referenciacatrastral.getBITC16();
        String sTIRCAT = referenciacatrastral.getTIRCAT();
        String sBITC17 = referenciacatrastral.getBITC17();
        String sENEMIS = referenciacatrastral.getENEMIS();
        String sCOTEXA = referenciacatrastral.getCOTEXA();
        String sBITC09 = referenciacatrastral.getBITC09();
        String sOBTEXC = referenciacatrastral.getOBTEXC();
        String sOBDEER = referenciacatrastral.getOBDEER();	
		
		return sCODTRN + sCOTDOR + sIDPROV + sCOACCI + sCOENGP + sCOACES
				+ sNURCAT + sBITC16 + sTIRCAT + sBITC17 + sENEMIS + sCOTEXA
				+ sBITC09 + sOBTEXC + sOBDEER;
	}

	public static ImpuestoRecurso leerImpuestoRecurso (String linea)
	{

		String sCODTRN = linea.substring(Posiciones.E4_CODTRN_P, Posiciones.E4_CODTRN_P+Longitudes.CODTRN_L);
		String sCOTDOR = linea.substring(Posiciones.E4_COTDOR_P, Posiciones.E4_COTDOR_P+Longitudes.COTDOR_L);
		String sIDPROV = linea.substring(Posiciones.E4_IDPROV_P, Posiciones.E4_IDPROV_P+Longitudes.IDPROV_L);
		String sCOACCI = linea.substring(Posiciones.E4_COACCI_P, Posiciones.E4_COACCI_P+Longitudes.COACCI_L);
		String sCOENGP = linea.substring(Posiciones.E4_COENGP_P, Posiciones.E4_COENGP_P+Longitudes.COENGP_L);
		String sCOACES = linea.substring(Posiciones.E4_COACES_P, Posiciones.E4_COACES_P+Longitudes.COACES_L);
		String sNURCAT = linea.substring(Posiciones.E4_NURCAT_P, Posiciones.E4_NURCAT_P+Longitudes.NURCAT_L);
		String sCOGRUC = linea.substring(Posiciones.E4_COGRUC_P, Posiciones.E4_COGRUC_P+Longitudes.COGRUC_L);
		String sCOTACA = linea.substring(Posiciones.E4_COTACA_P, Posiciones.E4_COTACA_P+Longitudes.COTACA_L);
		String sCOSBAC = linea.substring(Posiciones.E4_COSBAC_P, Posiciones.E4_COSBAC_P+Longitudes.COSBAC_L);
		String sBITC18 = linea.substring(Posiciones.E4_BITC18_P, Posiciones.E4_BITC18_P+Longitudes.BITC18_L);
		String sFEPRRE = linea.substring(Posiciones.E4_FEPRRE_P, Posiciones.E4_FEPRRE_P+Longitudes.FEPRRE_L);
		String sBITC19 = linea.substring(Posiciones.E4_BITC19_P, Posiciones.E4_BITC19_P+Longitudes.BITC19_L);
		String sFERERE = linea.substring(Posiciones.E4_FERERE_P, Posiciones.E4_FERERE_P+Longitudes.FERERE_L);
		String sBITC20 = linea.substring(Posiciones.E4_BITC20_P, Posiciones.E4_BITC20_P+Longitudes.BITC20_L);
		String sFEDEIN = linea.substring(Posiciones.E4_FEDEIN_P, Posiciones.E4_FEDEIN_P+Longitudes.FEDEIN_L);
		String sBITC21 = linea.substring(Posiciones.E4_BITC21_P, Posiciones.E4_BITC21_P+Longitudes.BITC21_L);
		String sBISODE = linea.substring(Posiciones.E4_BISODE_P, Posiciones.E4_BISODE_P+Longitudes.BISODE_L);
		String sBITC22 = linea.substring(Posiciones.E4_BITC22_P, Posiciones.E4_BITC22_P+Longitudes.BITC22_L);
		String sBIRESO = linea.substring(Posiciones.E4_BIRESO_P, Posiciones.E4_BIRESO_P+Longitudes.BIRESO_L);
		String sCOTEXA = linea.substring(Posiciones.E4_COTEXA_P, Posiciones.E4_COTEXA_P+Longitudes.COTEXA_L);
		String sBITC09 = linea.substring(Posiciones.E4_BITC09_P, Posiciones.E4_BITC09_P+Longitudes.BITC09_L);
		String sOBTEXC = linea.substring(Posiciones.E4_OBTEXC_P, Posiciones.E4_OBTEXC_P+Longitudes.OBTEXC_L);
		String sOBDEER = linea.substring(Posiciones.E4_OBDEER_P, Posiciones.E4_OBDEER_P+Longitudes.OBDEER_L);
		
		return new ImpuestoRecurso(sCODTRN, sCOTDOR, sIDPROV, sCOACCI, sCOENGP,
				sCOACES, sNURCAT, sCOGRUC, sCOTACA, sCOSBAC, sBITC18, sFEPRRE,
				sBITC19, sFERERE, sBITC20, sFEDEIN, sBITC21, sBISODE, sBITC22,
				sBIRESO, sCOTEXA, sBITC09, sOBTEXC, sOBDEER);
	}
	
	public static String escribirImpuestoRecurso (ImpuestoRecurso impuestorecurso)
	{

        String sCODTRN = impuestorecurso.getCODTRN();
        String sCOTDOR = impuestorecurso.getCOTDOR();
        String sIDPROV = impuestorecurso.getIDPROV();
        String sCOACCI = impuestorecurso.getCOACCI();
        String sCOENGP = impuestorecurso.getCOENGP();
        String sCOACES = impuestorecurso.getCOACES();
        String sNURCAT = impuestorecurso.getNURCAT();
        String sCOGRUC = impuestorecurso.getCOGRUC();
        String sCOTACA = impuestorecurso.getCOTACA();
        String sCOSBAC = impuestorecurso.getCOSBAC();
        String sBITC18 = impuestorecurso.getBITC18();
        String sFEPRRE = impuestorecurso.getFEPRRE();
        String sBITC19 = impuestorecurso.getBITC19();
        String sFERERE = impuestorecurso.getFERERE();
        String sBITC20 = impuestorecurso.getBITC20();
        String sFEDEIN = impuestorecurso.getFEDEIN();
        String sBITC21 = impuestorecurso.getBITC21();
        String sBISODE = impuestorecurso.getBISODE();
        String sBITC22 = impuestorecurso.getBITC22();
        String sBIRESO = impuestorecurso.getBIRESO();
        String sCOTEXA = impuestorecurso.getCOTEXA();
        String sBITC09 = impuestorecurso.getBITC09();
        String sOBTEXC = impuestorecurso.getOBTEXC();
        String sOBDEER = impuestorecurso.getOBDEER();
		
		return sCODTRN + sCOTDOR + sIDPROV + sCOACCI + sCOENGP + sCOACES
				+ sNURCAT + sCOGRUC + sCOTACA + sCOSBAC + sBITC18 + sFEPRRE
				+ sBITC19 + sFERERE + sBITC20 + sFEDEIN + sBITC21 + sBISODE
				+ sBITC22 + sBIRESO + sCOTEXA + sBITC09 + sOBTEXC + sOBDEER;
	}
	
	public static void main(String[] args) throws IOException {

		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main",	"Conexion Realizada");
		File archivo = new File("C:\\168AC.txt");
		FileReader fr = new FileReader(archivo);
		BufferedReader br = new BufferedReader(fr);
		String linea = br.readLine();
		br.close();

		Activo activo = leerActivo(linea);

		activo.pintaActivo();

	}

}
