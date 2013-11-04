package com.provisiones.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.types.Cierre;
import com.provisiones.types.Activo;
import com.provisiones.types.movimientos.MovimientoComunidad;
import com.provisiones.types.movimientos.MovimientoCuota;
import com.provisiones.types.movimientos.MovimientoGasto;
import com.provisiones.types.movimientos.MovimientoImpuestoRecurso;
import com.provisiones.types.movimientos.MovimientoReferenciaCatastral;

public class Parser {

	private static Logger logger = LoggerFactory.getLogger(Parser.class.getName());

	public static String limpiaCampoNumerico (String sCampo)
	{

        String sResultado = sCampo;
 
        while ((sResultado.startsWith("0")) && (sResultado.length() > 1) ) 
        {
        	sResultado=sResultado.substring(1);
        }
        
        logger.debug("sResultado:|{}|",sResultado);
        
		return sResultado;
	}

	public static String limpiaCampoAlfanumerico(String sCampo)
	{

		return sCampo.trim();
	}
	
	public static String limpiaCampoAlfanumericoCodigo (String sCampo, String sValorBlanco)
	{
		String sResultado = sCampo.trim();
		
		if (sResultado.length()==0)
			sResultado = sValorBlanco;

		 logger.debug("sResultado:|{}|",sResultado);
		
		return sResultado;
	}
	
	public static String formateaCampoNumerico (String sCampo, int iLongitud)
	{

        String sResultado = sCampo;
        
        while (sResultado.length() < iLongitud) 
        {
        	sResultado="0"+sResultado;
        }
        
        logger.debug("sResultado:|{}|",sResultado);
        
		return sResultado;
	}

	public static String formateaCampoAlfanumerico (String sCampo, int iLongitud)
	{

        String sResultado = sCampo;
        
        while (sResultado.length() < iLongitud) 
        {
        	sResultado = sResultado+" ";
        }
        
        logger.debug("sResultado:|{}|",sResultado);
        
		return sResultado;
	}
	
	public static String formateaCampoAlfanumericoCodigo (String sCampo, int iLongitud, String sValorComodin)
	{

        String sResultado = sCampo;
        
		if (sCampo.equals(sValorComodin))
		{
			sResultado = " ";
			while (sResultado.length() < iLongitud)
			{
				sResultado = sResultado + " ";
			}
			
		}
		
		 logger.debug("sResultado:|{}|",sResultado);
		
		return sResultado;
	}
	
	public static Activo leerActivo (String linea)
	{

		String sCOACES = linea.substring(Posiciones.AC_COACES_P, Posiciones.AC_COACES_P+Longitudes.COACES_L);
		String sNUINMU = linea.substring(Posiciones.AC_NUINMU_P, Posiciones.AC_NUINMU_P+Longitudes.NUINMU_L);
		String sCOSOPA = linea.substring(Posiciones.AC_COSOPA_P, Posiciones.AC_COSOPA_P+Longitudes.COSOPA_L);
		String sCOENAE = linea.substring(Posiciones.AC_COENAE_P, Posiciones.AC_COENAE_P+Longitudes.COENAE_L);
		String sCOESEN = linea.substring(Posiciones.AC_COESEN_P, Posiciones.AC_COESEN_P+Longitudes.COESEN_L);
		String sNOVIAS = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_NOVIAS_P, Posiciones.AC_NOVIAS_P+Longitudes.NOVIAS_L));
		String sNUPOAC = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_NUPOAC_P, Posiciones.AC_NUPOAC_P+Longitudes.NUPOAC_L));
		String sNUESAC = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_NUESAC_P, Posiciones.AC_NUESAC_P+Longitudes.NUESAC_L));
		String sNUPIAC = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_NUPIAC_P, Posiciones.AC_NUPIAC_P+Longitudes.NUPIAC_L));
		String sNUPUAC = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_NUPUAC_P, Posiciones.AC_NUPUAC_P+Longitudes.NUPUAC_L));
		String sNOMUIN = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_NOMUIN_P, Posiciones.AC_NOMUIN_P+Longitudes.NOMUIN_L));
		String sCOPRAE = linea.substring(Posiciones.AC_COPRAE_P, Posiciones.AC_COPRAE_P+Longitudes.COPRAE_L);
		String sNOPRAC = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_NOPRAC_P, Posiciones.AC_NOPRAC_P+Longitudes.NOPRAC_L));
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
		String sCOTSIN = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_COTSIN_P, Posiciones.AC_COTSIN_P+Longitudes.COTSIN_L));
		String sNUFIRE = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_NUFIRE_P, Posiciones.AC_NUFIRE_P+Longitudes.NUFIRE_L));
		String sNUREGP = linea.substring(Posiciones.AC_NUREGP_P, Posiciones.AC_NUREGP_P+Longitudes.NUREGP_L);
		String sNOMUI0 = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_NOMUI0_P, Posiciones.AC_NOMUI0_P+Longitudes.NOMUI0_L));
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
		String sBIARRE = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.AC_BIARRE_P, Posiciones.AC_BIARRE_P+Longitudes.BIARRE_L),"#");
		String sCADORM = linea.substring(Posiciones.AC_CADORM_P, Posiciones.AC_CADORM_P+Longitudes.CADORM_L);
		String sCABANO = linea.substring(Posiciones.AC_CABANO_P, Posiciones.AC_CABANO_P+Longitudes.CABANO_L);
		String sBIGAPA = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.AC_BIGAPA_P, Posiciones.AC_BIGAPA_P+Longitudes.BIGAPA_L),"#");
		String sCAGAPA = linea.substring(Posiciones.AC_CAGAPA_P, Posiciones.AC_CAGAPA_P+Longitudes.CAGAPA_L);
		String sCASUTE = linea.substring(Posiciones.AC_CASUTE_P, Posiciones.AC_CASUTE_P+Longitudes.CASUTE_L);
		String sBILIPO = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.AC_BILIPO_P, Posiciones.AC_BILIPO_P+Longitudes.BILIPO_L),"#");
		String sBILIAC = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.AC_BILIAC_P, Posiciones.AC_BILIAC_P+Longitudes.BILIAC_L),"#");
		String sBILIUS = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.AC_BILIUS_P, Posiciones.AC_BILIUS_P+Longitudes.BILIUS_L),"#");
		String sBIBOIN = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.AC_BIBOIN_P, Posiciones.AC_BIBOIN_P+Longitudes.BIBOIN_L),"#");
		String sBICEFI = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.AC_BICEFI_P, Posiciones.AC_BICEFI_P+Longitudes.BICEFI_L),"#");
		String sCASUCB = linea.substring(Posiciones.AC_CASUCB_P, Posiciones.AC_CASUCB_P+Longitudes.CASUCB_L);
		String sCASUCS = linea.substring(Posiciones.AC_CASUCS_P, Posiciones.AC_CASUCS_P+Longitudes.CASUCS_L);
		String sFEACON = linea.substring(Posiciones.AC_FEACON_P, Posiciones.AC_FEACON_P+Longitudes.FEACON_L);
		String sIDAUTO = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_IDAUTO_P, Posiciones.AC_IDAUTO_P+Longitudes.IDAUTO_L));
		String sFEDEMA = linea.substring(Posiciones.AC_FEDEMA_P, Posiciones.AC_FEDEMA_P+Longitudes.FEDEMA_L);
		String sYNOCUR = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_YNOCUR_P, Posiciones.AC_YNOCUR_P+Longitudes.YNOCUR_L));
		String sOBRECO = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_OBRECO_P, Posiciones.AC_OBRECO_P+Longitudes.OBRECO_L));
		String sYNOLEC = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_YNOLEC_P, Posiciones.AC_YNOLEC_P+Longitudes.YNOLEC_L));
		String sNOLOJZ = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_NOLOJZ_P, Posiciones.AC_NOLOJZ_P+Longitudes.NOLOJZ_L));
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
		String sCOXSIA = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.AC_COXSIA_P, Posiciones.AC_COXSIA_P+Longitudes.COXSIA_L),"0");
		String sNUJUZD = linea.substring(Posiciones.AC_NUJUZD_P, Posiciones.AC_NUJUZD_P+Longitudes.NUJUZD_L);
		String sNURCAT = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_NURCAT_P, Posiciones.AC_NURCAT_P+Longitudes.NURCAT_L));
		String sNOMPRC = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_NOMPRC_P, Posiciones.AC_NOMPRC_P+Longitudes.NOMPRC_L));
		String sNUTPRC = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_NUTPRC_P, Posiciones.AC_NUTPRC_P+Longitudes.NUTPRC_L));
		String sNOMADC = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_NOMADC_P, Posiciones.AC_NOMADC_P+Longitudes.NOMADC_L));
		String sNUTADC = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_NUTADC_P, Posiciones.AC_NUTADC_P+Longitudes.NUTADC_L));
		String sIMPCOO = linea.substring(Posiciones.AC_IMPCOO_P, Posiciones.AC_IMPCOO_P+Longitudes.IMPCOO_L);
		String sCOENOR = linea.substring(Posiciones.AC_COENOR_P, Posiciones.AC_COENOR_P+Longitudes.COENOR_L);
		String sCOSPAT = linea.substring(Posiciones.AC_COSPAT_P, Posiciones.AC_COSPAT_P+Longitudes.COSPAT_L);
		String sCOSPAS = linea.substring(Posiciones.AC_COSPAS_P, Posiciones.AC_COSPAS_P+Longitudes.COSPAS_L);
		String sIDCOL3 = limpiaCampoAlfanumerico(linea.substring(Posiciones.AC_IDCOL3_P, Posiciones.AC_IDCOL3_P+Longitudes.IDCOL3_L));
		String sBIOBNU = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.AC_BIOBNU_P, Posiciones.AC_BIOBNU_P+Longitudes.BIOBNU_L),"#");
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

	public static String escribirActivo (Activo activo)
	{
	
        String sCOACES = formateaCampoNumerico(activo.getCOACES(),Longitudes.COACES_L);
        String sNUINMU = formateaCampoNumerico(activo.getNUINMU(),Longitudes.NUINMU_L);
        String sCOSOPA = formateaCampoNumerico(activo.getCOSOPA(),Longitudes.COSOPA_L);
        String sCOENAE = formateaCampoNumerico(activo.getCOENAE(),Longitudes.COENAE_L);
        String sCOESEN = formateaCampoNumerico(activo.getCOESEN(),Longitudes.COESEN_L);
        String sNOVIAS = formateaCampoAlfanumerico(activo.getNOVIAS(),Longitudes.NOVIAS_L);
        String sNUPOAC = formateaCampoAlfanumerico(activo.getNUPOAC(),Longitudes.NUPOAC_L);
        String sNUESAC = formateaCampoAlfanumerico(activo.getNUESAC(),Longitudes.NUESAC_L);
        String sNUPIAC = formateaCampoAlfanumerico(activo.getNUPIAC(),Longitudes.NUPIAC_L);
        String sNUPUAC = formateaCampoAlfanumerico(activo.getNUPUAC(),Longitudes.NUPUAC_L);
        String sNOMUIN = formateaCampoAlfanumerico(activo.getNOMUIN(),Longitudes.NOMUIN_L);
        String sCOPRAE = formateaCampoNumerico(activo.getCOPRAE(),Longitudes.COPRAE_L);
        String sNOPRAC = formateaCampoAlfanumerico(activo.getNOPRAC(),Longitudes.NOPRAC_L);
        String sCOPOIN = formateaCampoNumerico(activo.getCOPOIN(),Longitudes.COPOIN_L);
        String sFEREAP = formateaCampoNumerico(activo.getFEREAP(),Longitudes.FEREAP_L);
        String sCOREAE = formateaCampoNumerico(activo.getCOREAE(),Longitudes.COREAE_L);
        String sFEINAU = formateaCampoNumerico(activo.getFEINAU(),Longitudes.FEINAU_L);
        String sFESOPO = formateaCampoNumerico(activo.getFESOPO(),Longitudes.FESOPO_L);
        String sFESEPO = formateaCampoNumerico(activo.getFESEPO(),Longitudes.FESEPO_L);
        String sFEREPO = formateaCampoNumerico(activo.getFEREPO(),Longitudes.FEREPO_L);
        String sFEADAC = formateaCampoNumerico(activo.getFEADAC(),Longitudes.FEADAC_L);
        String sCODIJU = formateaCampoNumerico(activo.getCODIJU(),Longitudes.CODIJU_L);
        String sCOSJUP = formateaCampoNumerico(activo.getCOSJUP(),Longitudes.COSJUP_L);
        String sCOSTLI = formateaCampoNumerico(activo.getCOSTLI(),Longitudes.COSTLI_L);
        String sCOSCAR = formateaCampoNumerico(activo.getCOSCAR(),Longitudes.COSCAR_L);
        String sCOESVE = formateaCampoNumerico(activo.getCOESVE(),Longitudes.COESVE_L);
        String sCOTSIN = formateaCampoAlfanumerico(activo.getCOTSIN(),Longitudes.COTSIN_L);
        String sNUFIRE = formateaCampoAlfanumerico(activo.getNUFIRE(),Longitudes.NUFIRE_L);
        String sNUREGP = formateaCampoNumerico(activo.getNUREGP(),Longitudes.NUREGP_L);
        String sNOMUI0 = formateaCampoAlfanumerico(activo.getNOMUI0(),Longitudes.NOMUI0_L);
        String sNULIBE = formateaCampoNumerico(activo.getNULIBE(),Longitudes.NULIBE_L);
        String sNUTOME = formateaCampoNumerico(activo.getNUTOME(),Longitudes.NUTOME_L);
        String sNUFOLE = formateaCampoNumerico(activo.getNUFOLE(),Longitudes.NUFOLE_L);
        String sNUINSR = formateaCampoNumerico(activo.getNUINSR(),Longitudes.NUINSR_L);
        String sCOSOCU = formateaCampoNumerico(activo.getCOSOCU(),Longitudes.COSOCU_L);
        String sCOXPRO = formateaCampoNumerico(activo.getCOXPRO(),Longitudes.COXPRO_L);
        String sFESOLA = formateaCampoNumerico(activo.getFESOLA(),Longitudes.FESOLA_L);
        String sFESELA = formateaCampoNumerico(activo.getFESELA(),Longitudes.FESELA_L);
        String sFERELA = formateaCampoNumerico(activo.getFERELA(),Longitudes.FERELA_L);
        String sFERLLA = formateaCampoNumerico(activo.getFERLLA(),Longitudes.FERLLA_L);
        String sCASPRE = formateaCampoNumerico(activo.getCASPRE(),Longitudes.CASPRE_L);
        String sCASUTR = formateaCampoNumerico(activo.getCASUTR(),Longitudes.CASUTR_L);
        String sCASUTC = formateaCampoNumerico(activo.getCASUTC(),Longitudes.CASUTC_L);
        String sCASUTG = formateaCampoNumerico(activo.getCASUTG(),Longitudes.CASUTG_L);
        String sBIARRE = formateaCampoAlfanumericoCodigo(activo.getBIARRE(),Longitudes.BIARRE_L,"#"); 
        String sCADORM = formateaCampoNumerico(activo.getCADORM(),Longitudes.CADORM_L); 
        String sCABANO = formateaCampoNumerico(activo.getCABANO(),Longitudes.CABANO_L); 
        String sBIGAPA = formateaCampoAlfanumericoCodigo(activo.getBIGAPA(),Longitudes.BIGAPA_L,"#"); 
        String sCAGAPA = formateaCampoNumerico(activo.getCAGAPA(),Longitudes.CAGAPA_L); 
        String sCASUTE = formateaCampoNumerico(activo.getCASUTE(),Longitudes.CASUTE_L); 
        String sBILIPO = formateaCampoAlfanumericoCodigo(activo.getBILIPO(),Longitudes.BILIPO_L,"#"); 
        String sBILIAC = formateaCampoAlfanumericoCodigo(activo.getBILIAC(),Longitudes.BILIAC_L,"#"); 
        String sBILIUS = formateaCampoAlfanumericoCodigo(activo.getBILIUS(),Longitudes.BILIUS_L,"#"); 
        String sBIBOIN = formateaCampoAlfanumericoCodigo(activo.getBIBOIN(),Longitudes.BIBOIN_L,"#"); 
        String sBICEFI = formateaCampoAlfanumericoCodigo(activo.getBICEFI(),Longitudes.BICEFI_L,"#"); 
        String sCASUCB = formateaCampoNumerico(activo.getCASUCB(),Longitudes.CASUCB_L); 
        String sCASUCS = formateaCampoNumerico(activo.getCASUCS(),Longitudes.CASUCS_L); 
        String sFEACON = formateaCampoNumerico(activo.getFEACON(),Longitudes.FEACON_L); 
        String sIDAUTO = formateaCampoAlfanumerico(activo.getIDAUTO(),Longitudes.IDAUTO_L); 
        String sFEDEMA = formateaCampoNumerico(activo.getFEDEMA(),Longitudes.FEDEMA_L); 
        String sYNOCUR = formateaCampoAlfanumerico(activo.getYNOCUR(),Longitudes.YNOCUR_L); 
        String sOBRECO = formateaCampoAlfanumerico(activo.getOBRECO(),Longitudes.OBRECO_L); 
        String sYNOLEC = formateaCampoAlfanumerico(activo.getYNOLEC(),Longitudes.YNOLEC_L); 
        String sNOLOJZ = formateaCampoAlfanumerico(activo.getNOLOJZ(),Longitudes.NOLOJZ_L); 
        String sFEREDE = formateaCampoNumerico(activo.getFEREDE(),Longitudes.FEREDE_L); 
        String sPOPROP = formateaCampoNumerico(activo.getPOPROP(),Longitudes.POPROP_L); 
        String sCOGRAP = formateaCampoNumerico(activo.getCOGRAP(),Longitudes.COGRAP_L); 
        String sFEPREG = formateaCampoNumerico(activo.getFEPREG(),Longitudes.FEPREG_L); 
        String sFEPHAC = formateaCampoNumerico(activo.getFEPHAC(),Longitudes.FEPHAC_L); 
        String sFEFOAC = formateaCampoNumerico(activo.getFEFOAC(),Longitudes.FEFOAC_L); 
        String sFEVACT = formateaCampoNumerico(activo.getFEVACT(),Longitudes.FEVACT_L); 
        String sIMVACT = formateaCampoNumerico(activo.getIMVACT(),Longitudes.IMVACT_L); 
        String sNUFIPR = formateaCampoNumerico(activo.getNUFIPR(),Longitudes.NUFIPR_L); 
        String sCOTPET = formateaCampoNumerico(activo.getCOTPET(),Longitudes.COTPET_L); 
        String sFEEMPT = formateaCampoNumerico(activo.getFEEMPT(),Longitudes.FEEMPT_L); 
        String sFESORC = formateaCampoNumerico(activo.getFESORC(),Longitudes.FESORC_L); 
        String sFESODE = formateaCampoNumerico(activo.getFESODE(),Longitudes.FESODE_L); 
        String sFEREAC = formateaCampoNumerico(activo.getFEREAC(),Longitudes.FEREAC_L); 
        String sCOXSIA = formateaCampoAlfanumericoCodigo(activo.getCOXSIA(),Longitudes.COXSIA_L,"0"); 

        String sNUJUZD = formateaCampoNumerico(activo.getNUJUZD(),Longitudes.NUJUZD_L); 
        String sNURCAT = formateaCampoAlfanumerico(activo.getNURCAT(),Longitudes.NURCAT_L); 
        String sNOMPRC = formateaCampoAlfanumerico(activo.getNOMPRC(),Longitudes.NOMPRC_L); 
        String sNUTPRC = formateaCampoAlfanumerico(activo.getNUTPRC(),Longitudes.NUTPRC_L); 
        String sNOMADC = formateaCampoAlfanumerico(activo.getNOMADC(),Longitudes.NOMADC_L); 
        String sNUTADC = formateaCampoAlfanumerico(activo.getNUTADC(),Longitudes.NUTADC_L); 
        String sIMPCOO = formateaCampoNumerico(activo.getIMPCOO(),Longitudes.IMPCOO_L); 
        String sCOENOR = formateaCampoNumerico(activo.getCOENOR(),Longitudes.COENOR_L); 
        String sCOSPAT = formateaCampoNumerico(activo.getCOSPAT(),Longitudes.COSPAT_L); 
        String sCOSPAS = formateaCampoNumerico(activo.getCOSPAS(),Longitudes.COSPAS_L); 
        String sIDCOL3 = formateaCampoAlfanumerico(activo.getIDCOL3(),Longitudes.IDCOL3_L); 
        String sBIOBNU = formateaCampoAlfanumericoCodigo(activo.getBIOBNU(),Longitudes.BIOBNU_L,"#");

        String sPOBRAR = formateaCampoNumerico(activo.getPOBRAR(),Longitudes.POBRAR_L);		
		
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

	
	public static MovimientoGasto leerGasto (String linea)
	{

		String sCOACES = limpiaCampoNumerico(linea.substring(Posiciones.GA_COACES_P, Posiciones.GA_COACES_P+Longitudes.COACES_L));
		String sCOGRUG = limpiaCampoNumerico(linea.substring(Posiciones.GA_COGRUG_P, Posiciones.GA_COGRUG_P+Longitudes.COGRUG_L));
		String sCOTPGA = limpiaCampoNumerico(linea.substring(Posiciones.GA_COTPGA_P, Posiciones.GA_COTPGA_P+Longitudes.COTPGA_L));
		String sCOSBGA = limpiaCampoNumerico(linea.substring(Posiciones.GA_COSBGA_P, Posiciones.GA_COSBGA_P+Longitudes.COSBGA_L));
		String sPTPAGO = linea.substring(Posiciones.GA_PTPAGO_P, Posiciones.GA_PTPAGO_P+Longitudes.PTPAGO_L);
		String sFEDEVE = linea.substring(Posiciones.GA_FEDEVE_P, Posiciones.GA_FEDEVE_P+Longitudes.FEDEVE_L);
		String sFFGTVP = linea.substring(Posiciones.GA_FFGTVP_P, Posiciones.GA_FFGTVP_P+Longitudes.FFGTVP_L);
		String sFEPAGA = linea.substring(Posiciones.GA_FEPAGA_P, Posiciones.GA_FEPAGA_P+Longitudes.FEPAGA_L);
		String sFELIPG = linea.substring(Posiciones.GA_FELIPG_P, Posiciones.GA_FELIPG_P+Longitudes.FELIPG_L);
		String sCOSIGA = limpiaCampoNumerico(linea.substring(Posiciones.GA_COSIGA_P, Posiciones.GA_COSIGA_P+Longitudes.COSIGA_L));
		String sFEEESI = linea.substring(Posiciones.GA_FEEESI_P, Posiciones.GA_FEEESI_P+Longitudes.FEEESI_L);
		String sFEECOI = linea.substring(Posiciones.GA_FEECOI_P, Posiciones.GA_FEECOI_P+Longitudes.FEECOI_L);
		String sFEEAUI = linea.substring(Posiciones.GA_FEEAUI_P, Posiciones.GA_FEEAUI_P+Longitudes.FEEAUI_L);
		String sFEEPAI = linea.substring(Posiciones.GA_FEEPAI_P, Posiciones.GA_FEEPAI_P+Longitudes.FEEPAI_L);
		String sIMNGAS = limpiaCampoNumerico(linea.substring(Posiciones.GA_IMNGAS_P, Posiciones.GA_IMNGAS_P+Longitudes.IMNGAS_L));
		String sYCOS02 = linea.substring(Posiciones.GA_YCOS02_P, Posiciones.GA_YCOS02_P+Longitudes.YCOS02_L);
		String sIMRGAS = limpiaCampoNumerico(linea.substring(Posiciones.GA_IMRGAS_P, Posiciones.GA_IMRGAS_P+Longitudes.IMRGAS_L));
		String sYCOS04 = linea.substring(Posiciones.GA_YCOS04_P, Posiciones.GA_YCOS04_P+Longitudes.YCOS04_L);
		String sIMDGAS = limpiaCampoNumerico(linea.substring(Posiciones.GA_IMDGAS_P, Posiciones.GA_IMDGAS_P+Longitudes.IMDGAS_L));
		String sYCOS06 = linea.substring(Posiciones.GA_YCOS06_P, Posiciones.GA_YCOS06_P+Longitudes.YCOS06_L);
		String sIMCOST = limpiaCampoNumerico(linea.substring(Posiciones.GA_IMCOST_P, Posiciones.GA_IMCOST_P+Longitudes.IMCOST_L));
		String sYCOS08 = linea.substring(Posiciones.GA_YCOS08_P, Posiciones.GA_YCOS08_P+Longitudes.YCOS08_L);
		String sIMOGAS = limpiaCampoNumerico(linea.substring(Posiciones.GA_IMOGAS_P, Posiciones.GA_IMOGAS_P+Longitudes.IMOGAS_L));
		String sYCOS10 = linea.substring(Posiciones.GA_YCOS10_P, Posiciones.GA_YCOS10_P+Longitudes.YCOS10_L);
		String sIMDTGA = limpiaCampoNumerico(linea.substring(Posiciones.GA_IMDTGA_P, Posiciones.GA_IMDTGA_P+Longitudes.IMDTGA_L));
		String sCOUNMO = linea.substring(Posiciones.GA_COUNMO_P, Posiciones.GA_COUNMO_P+Longitudes.COUNMO_L);
		String sIMIMGA = limpiaCampoNumerico(linea.substring(Posiciones.GA_IMIMGA_P, Posiciones.GA_IMIMGA_P+Longitudes.IMIMGA_L));
		String sCOIMPT = limpiaCampoNumerico(linea.substring(Posiciones.GA_COIMPT_P, Posiciones.GA_COIMPT_P+Longitudes.COIMPT_L));
		String sCOTNEG = limpiaCampoNumerico(linea.substring(Posiciones.GA_COTNEG_P, Posiciones.GA_COTNEG_P+Longitudes.COTNEG_L));
		String sCOENCX = limpiaCampoNumerico(linea.substring(Posiciones.GA_COENCX_P, Posiciones.GA_COENCX_P+Longitudes.COENCX_L));
		String sCOOFCX = limpiaCampoNumerico(linea.substring(Posiciones.GA_COOFCX_P, Posiciones.GA_COOFCX_P+Longitudes.COOFCX_L));
		String sNUCONE = limpiaCampoNumerico(linea.substring(Posiciones.GA_NUCONE_P, Posiciones.GA_NUCONE_P+Longitudes.NUCONE_L));
		String sNUPROF = limpiaCampoNumerico(linea.substring(Posiciones.GA_NUPROF_P, Posiciones.GA_NUPROF_P+Longitudes.NUPROF_L));
		String sFEAGTO = linea.substring(Posiciones.GA_FEAGTO_P, Posiciones.GA_FEAGTO_P+Longitudes.FEAGTO_L);
		String sCOMONA = limpiaCampoNumerico(linea.substring(Posiciones.GA_COMONA_P, Posiciones.GA_COMONA_P+Longitudes.COMONA_L));
		String sBIAUTO = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.GA_BIAUTO_P, Posiciones.GA_BIAUTO_P+Longitudes.BIAUTO_L),"0");
		String sFEAUFA = linea.substring(Posiciones.GA_FEAUFA_P, Posiciones.GA_FEAUFA_P+Longitudes.FEAUFA_L);
		String sCOTERR = limpiaCampoNumerico(linea.substring(Posiciones.GA_COTERR_P, Posiciones.GA_COTERR_P+Longitudes.COTERR_L));
		String sFMPAGN = linea.substring(Posiciones.GA_FMPAGN_P, Posiciones.GA_FMPAGN_P+Longitudes.FMPAGN_L);
		String sFEPGPR = linea.substring(Posiciones.GA_FEPGPR_P, Posiciones.GA_FEPGPR_P+Longitudes.FEPGPR_L);
		String sFEAPLI = linea.substring(Posiciones.GA_FEAPLI_P, Posiciones.GA_FEAPLI_P+Longitudes.FEAPLI_L);
		String sCOAPII = linea.substring(Posiciones.GA_COAPII_P, Posiciones.GA_COAPII_P+Longitudes.COAPII_L);
		String sCOSPII = linea.substring(Posiciones.GA_COSPII_P, Posiciones.GA_COSPII_P+Longitudes.COSPII_L);
		String sNUCLII = limpiaCampoNumerico(linea.substring(Posiciones.GA_NUCLII_P, Posiciones.GA_NUCLII_P+Longitudes.NUCLII_L));
		
		
		return new MovimientoGasto(sCOACES, sCOGRUG, sCOTPGA, sCOSBGA, sPTPAGO, sFEDEVE,
				sFFGTVP, sFEPAGA, sFELIPG, sCOSIGA, sFEEESI, sFEECOI, sFEEAUI,
				sFEEPAI, sIMNGAS, sYCOS02, sIMRGAS, sYCOS04, sIMDGAS, sYCOS06,
				sIMCOST, sYCOS08, sIMOGAS, sYCOS10, sIMDTGA, sCOUNMO, sIMIMGA,
				sCOIMPT, sCOTNEG, sCOENCX, sCOOFCX, sNUCONE, sNUPROF, sFEAGTO,
				sCOMONA, sBIAUTO, sFEAUFA, sCOTERR, sFMPAGN, sFEPGPR, sFEAPLI,
				sCOAPII, sCOSPII, sNUCLII);
	}
	
	public static String escribirGasto (MovimientoGasto gasto)
	{

        String sCOACES = formateaCampoNumerico(gasto.getCOACES(),Longitudes.COACES_L);
        String sCOGRUG = formateaCampoNumerico(gasto.getCOGRUG(),Longitudes.COGRUG_L);
        String sCOTPGA = formateaCampoNumerico(gasto.getCOTPGA(),Longitudes.COTPGA_L);
        String sCOSBGA = formateaCampoNumerico(gasto.getCOSBGA(),Longitudes.COSBGA_L);
        String sPTPAGO = formateaCampoAlfanumerico(gasto.getPTPAGO(),Longitudes.PTPAGO_L);
        String sFEDEVE = formateaCampoNumerico(gasto.getFEDEVE(),Longitudes.FEDEVE_L);
        String sFFGTVP = formateaCampoNumerico(gasto.getFFGTVP(),Longitudes.FFGTVP_L);
        String sFEPAGA = formateaCampoNumerico(gasto.getFEPAGA(),Longitudes.FEPAGA_L);
        String sFELIPG = formateaCampoNumerico(gasto.getFELIPG(),Longitudes.FELIPG_L);
        String sCOSIGA = formateaCampoNumerico(gasto.getCOSIGA(),Longitudes.COSIGA_L);
        String sFEEESI = formateaCampoNumerico(gasto.getFEEESI(),Longitudes.FEEESI_L);
        String sFEECOI = formateaCampoNumerico(gasto.getFEECOI(),Longitudes.FEECOI_L);
        String sFEEAUI = formateaCampoNumerico(gasto.getFEEAUI(),Longitudes.FEEAUI_L);
        String sFEEPAI = formateaCampoNumerico(gasto.getFEEPAI(),Longitudes.FEEPAI_L);
        String sIMNGAS = formateaCampoNumerico(gasto.getIMNGAS(),Longitudes.IMNGAS_L);
        String sYCOS02 = formateaCampoAlfanumerico(gasto.getYCOS02(),Longitudes.YCOS02_L);
        String sIMRGAS = formateaCampoNumerico(gasto.getIMRGAS(),Longitudes.IMRGAS_L);
        String sYCOS04 = formateaCampoAlfanumerico(gasto.getYCOS04(),Longitudes.YCOS04_L);
        String sIMDGAS = formateaCampoNumerico(gasto.getIMDGAS(),Longitudes.IMDGAS_L);
        String sYCOS06 = formateaCampoAlfanumerico(gasto.getYCOS06(),Longitudes.YCOS06_L);
        String sIMCOST = formateaCampoNumerico(gasto.getIMCOST(),Longitudes.IMCOST_L);
        String sYCOS08 = formateaCampoAlfanumerico(gasto.getYCOS08(),Longitudes.YCOS08_L);
        String sIMOGAS = formateaCampoNumerico(gasto.getIMOGAS(),Longitudes.IMOGAS_L);
        String sYCOS10 = formateaCampoAlfanumerico(gasto.getYCOS10(),Longitudes.YCOS10_L);
        String sIMDTGA = formateaCampoNumerico(gasto.getIMDTGA(),Longitudes.IMDTGA_L);
        String sCOUNMO = formateaCampoNumerico(gasto.getCOUNMO(),Longitudes.COUNMO_L);
        String sIMIMGA = formateaCampoNumerico(gasto.getIMIMGA(),Longitudes.IMIMGA_L);
        String sCOIMPT = formateaCampoNumerico(gasto.getCOIMPT(),Longitudes.COIMPT_L);
        String sCOTNEG = formateaCampoNumerico(gasto.getCOTNEG(),Longitudes.COTNEG_L);
        String sCOENCX = formateaCampoNumerico(gasto.getCOENCX(),Longitudes.COENCX_L);
        String sCOOFCX = formateaCampoNumerico(gasto.getCOOFCX(),Longitudes.COOFCX_L);
        String sNUCONE = formateaCampoNumerico(gasto.getNUCONE(),Longitudes.NUCONE_L);
        String sNUPROF = formateaCampoNumerico(gasto.getNUPROF(),Longitudes.NUPROF_L);
        String sFEAGTO = formateaCampoNumerico(gasto.getFEAGTO(),Longitudes.FEAGTO_L);
        String sCOMONA = formateaCampoNumerico(gasto.getCOMONA(),Longitudes.COMONA_L);
        String sBIAUTO = formateaCampoAlfanumericoCodigo(gasto.getBIAUTO(),Longitudes.BIAUTO_L,"0");
        String sFEAUFA = formateaCampoNumerico(gasto.getFEAUFA(),Longitudes.FEAUFA_L);
        String sCOTERR = formateaCampoNumerico(gasto.getCOTERR(),Longitudes.COTERR_L);
        String sFMPAGN = formateaCampoNumerico(gasto.getFMPAGN(),Longitudes.FMPAGN_L);
        String sFEPGPR = formateaCampoNumerico(gasto.getFEPGPR(),Longitudes.FEPGPR_L);
        String sFEAPLI = formateaCampoNumerico(gasto.getFEAPLI(),Longitudes.FEAPLI_L);
        String sCOAPII = formateaCampoAlfanumerico(gasto.getCOAPII(),Longitudes.COAPII_L);
        String sCOSPII = formateaCampoAlfanumerico(gasto.getCOSPII(),Longitudes.COSPII_L);
        String sNUCLII = formateaCampoNumerico(gasto.getNUCLII(),Longitudes.NUCLII_L);
		
		
		
		
		return sCOACES + sCOGRUG + sCOTPGA + sCOSBGA + sPTPAGO + sFEDEVE
				+ sFFGTVP + sFEPAGA + sFELIPG + sCOSIGA + sFEEESI + sFEECOI
				+ sFEEAUI + sFEEPAI + sIMNGAS + sYCOS02 + sIMRGAS + sYCOS04
				+ sIMDGAS + sYCOS06 + sIMCOST + sYCOS08 + sIMOGAS + sYCOS10
				+ sIMDTGA + sCOUNMO + sIMIMGA + sCOIMPT + sCOTNEG + sCOENCX
				+ sCOOFCX + sNUCONE + sNUPROF + sFEAGTO + sCOMONA + sBIAUTO
				+ sFEAUFA + sCOTERR + sFMPAGN + sFEPGPR + sFEAPLI + sCOAPII
				+ sCOSPII + sNUCLII 
				+ gasto.getFILLER();
	}
	
	public static MovimientoComunidad leerComunidad (String linea)
	{

		String sCODTRN = linea.substring(Posiciones.E1_CODTRN_P, Posiciones.E1_CODTRN_P+Longitudes.CODTRN_L);
		String sCOTDOR = limpiaCampoNumerico(linea.substring(Posiciones.E1_COTDOR_P, Posiciones.E1_COTDOR_P+Longitudes.COTDOR_L));
		String sIDPROV = limpiaCampoNumerico(linea.substring(Posiciones.E1_IDPROV_P, Posiciones.E1_IDPROV_P+Longitudes.IDPROV_L));
		String sCOACCI = linea.substring(Posiciones.E1_COACCI_P, Posiciones.E1_COACCI_P+Longitudes.COACCI_L);
		String sCOENGP = limpiaCampoNumerico(linea.substring(Posiciones.E1_COENGP_P, Posiciones.E1_COENGP_P+Longitudes.COENGP_L));
		String sCOCLDO = linea.substring(Posiciones.E1_COCLDO_P, Posiciones.E1_COCLDO_P+Longitudes.COCLDO_L);
		String sNUDCOM = linea.substring(Posiciones.E1_NUDCOM_P, Posiciones.E1_NUDCOM_P+Longitudes.NUDCOM_L).trim();
		String sBITC10 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E1_BITC10_P, Posiciones.E1_BITC10_P+Longitudes.BITC10_L),"#");
		String sCOACES = limpiaCampoNumerico(linea.substring(Posiciones.E1_COACES_P, Posiciones.E1_COACES_P+Longitudes.COACES_L));
		String sBITC01 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E1_BITC01_P, Posiciones.E1_BITC01_P+Longitudes.BITC01_L),"#");
		String sNOMCOC = linea.substring(Posiciones.E1_NOMCOC_P, Posiciones.E1_NOMCOC_P+Longitudes.NOMCOC_L).trim();
		String sBITC02 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E1_BITC02_P, Posiciones.E1_BITC02_P+Longitudes.BITC02_L),"#");
		String sNODCCO = linea.substring(Posiciones.E1_NODCCO_P, Posiciones.E1_NODCCO_P+Longitudes.NODCCO_L).trim();
		String sBITC03 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E1_BITC03_P, Posiciones.E1_BITC03_P+Longitudes.BITC03_L),"#");
		String sNOMPRC = linea.substring(Posiciones.E1_NOMPRC_P, Posiciones.E1_NOMPRC_P+Longitudes.NOMPRC_L).trim();
		String sBITC04 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E1_BITC04_P, Posiciones.E1_BITC04_P+Longitudes.BITC04_L),"#");
		String sNUTPRC = linea.substring(Posiciones.E1_NUTPRC_P, Posiciones.E1_NUTPRC_P+Longitudes.NUTPRC_L).trim();
		String sBITC05 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E1_BITC05_P, Posiciones.E1_BITC05_P+Longitudes.BITC05_L),"#");
		String sNOMADC = linea.substring(Posiciones.E1_NOMADC_P, Posiciones.E1_NOMADC_P+Longitudes.NOMADC_L).trim();
		String sBITC06 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E1_BITC06_P, Posiciones.E1_BITC06_P+Longitudes.BITC06_L),"#");
		String sNUTADC = linea.substring(Posiciones.E1_NUTADC_P, Posiciones.E1_NUTADC_P+Longitudes.NUTADC_L).trim();
		String sBITC07 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E1_BITC07_P, Posiciones.E1_BITC07_P+Longitudes.BITC07_L),"#");
		String sNODCAD = linea.substring(Posiciones.E1_NODCAD_P, Posiciones.E1_NODCAD_P+Longitudes.NODCAD_L).trim();
		String sBITC08 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E1_BITC08_P, Posiciones.E1_BITC08_P+Longitudes.BITC08_L),"#");
		String sNUCCEN = limpiaCampoNumerico(linea.substring(Posiciones.E1_NUCCEN_P, Posiciones.E1_NUCCEN_P+Longitudes.NUCCEN_L));
		String sNUCCOF = limpiaCampoNumerico(linea.substring(Posiciones.E1_NUCCOF_P, Posiciones.E1_NUCCOF_P+Longitudes.NUCCOF_L));
		String sNUCCDI = limpiaCampoNumerico(linea.substring(Posiciones.E1_NUCCDI_P, Posiciones.E1_NUCCDI_P+Longitudes.NUCCDI_L));
		String sNUCCNT = limpiaCampoNumerico(linea.substring(Posiciones.E1_NUCCNT_P, Posiciones.E1_NUCCNT_P+Longitudes.NUCCNT_L));
		String sBITC09 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E1_BITC09_P, Posiciones.E1_BITC09_P+Longitudes.BITC09_L),"#");
		String sOBTEXC = linea.substring(Posiciones.E1_OBTEXC_P, Posiciones.E1_OBTEXC_P+Longitudes.OBTEXC_L).trim();
		//String sOBDEER = linea.substring(Posiciones.E1_OBDEER_P, Posiciones.E1_OBDEER_P+Longitudes.OBDEER_L);
		String sOBDEER = linea.substring(Posiciones.E1_OBDEER_P);
		
		
		return new MovimientoComunidad(sCODTRN, sCOTDOR, sIDPROV, sCOACCI, sCOENGP,
				sCOCLDO, sNUDCOM, sBITC10, sCOACES, sBITC01, sNOMCOC, sBITC02,
				sNODCCO, sBITC03, sNOMPRC, sBITC04, sNUTPRC, sBITC05, sNOMADC,
				sBITC06, sNUTADC, sBITC07, sNODCAD, sBITC08, sNUCCEN, sNUCCOF,
				sNUCCDI, sNUCCNT, sBITC09, sOBTEXC, sOBDEER);
	}
	
	public static String escribirComunidad (MovimientoComunidad comunidad)
	{

        String sCODTRN = formateaCampoAlfanumerico(comunidad.getCODTRN(),Longitudes.CODTRN_L);
        String sCOTDOR = formateaCampoNumerico(comunidad.getCOTDOR(),Longitudes.COTDOR_L);
        String sIDPROV = formateaCampoNumerico(comunidad.getIDPROV(),Longitudes.IDPROV_L);
        String sCOACCI = formateaCampoAlfanumerico(comunidad.getCOACCI(),Longitudes.COACCI_L);
        String sCOENGP = formateaCampoNumerico(comunidad.getCOENGP(),Longitudes.COENGP_L);
        String sCOCLDO = formateaCampoAlfanumerico(comunidad.getCOCLDO(),Longitudes.COCLDO_L);
        String sNUDCOM = formateaCampoAlfanumerico(comunidad.getNUDCOM(),Longitudes.NUDCOM_L);
        String sBITC10 = formateaCampoAlfanumericoCodigo(comunidad.getBITC10(),Longitudes.BITC10_L,"#");
        String sCOACES = formateaCampoNumerico(comunidad.getCOACES(),Longitudes.COACES_L);
        String sBITC01 = formateaCampoAlfanumericoCodigo(comunidad.getBITC01(),Longitudes.BITC01_L,"#");
        String sNOMCOC = formateaCampoAlfanumerico(comunidad.getNOMCOC(),Longitudes.NOMCOC_L);
        String sBITC02 = formateaCampoAlfanumericoCodigo(comunidad.getBITC02(),Longitudes.BITC02_L,"#");
        String sNODCCO = formateaCampoAlfanumerico(comunidad.getNODCCO(),Longitudes.NODCCO_L);
        String sBITC03 = formateaCampoAlfanumericoCodigo(comunidad.getBITC03(),Longitudes.BITC03_L,"#");
        String sNOMPRC = formateaCampoAlfanumerico(comunidad.getNOMPRC(),Longitudes.NOMPRC_L);
        String sBITC04 = formateaCampoAlfanumericoCodigo(comunidad.getBITC04(),Longitudes.BITC04_L,"#");
        String sNUTPRC = formateaCampoAlfanumerico(comunidad.getNUTPRC(),Longitudes.NUTPRC_L);
        String sBITC05 = formateaCampoAlfanumericoCodigo(comunidad.getBITC05(),Longitudes.BITC05_L,"#");
        String sNOMADC = formateaCampoAlfanumerico(comunidad.getNOMADC(),Longitudes.NOMADC_L);
        String sBITC06 = formateaCampoAlfanumericoCodigo(comunidad.getBITC06(),Longitudes.BITC06_L,"#");
        String sNUTADC = formateaCampoAlfanumerico(comunidad.getNUTADC(),Longitudes.NUTADC_L);
        String sBITC07 = formateaCampoAlfanumericoCodigo(comunidad.getBITC07(),Longitudes.BITC07_L,"#");
        String sNODCAD = formateaCampoAlfanumerico(comunidad.getNODCAD(),Longitudes.NODCAD_L);
        String sBITC08 = formateaCampoAlfanumericoCodigo(comunidad.getBITC08(),Longitudes.BITC08_L,"#");
        String sNUCCEN = formateaCampoNumerico(comunidad.getNUCCEN(),Longitudes.NUCCEN_L);
        String sNUCCOF = formateaCampoNumerico(comunidad.getNUCCOF(),Longitudes.NUCCOF_L);
        String sNUCCDI = formateaCampoNumerico(comunidad.getNUCCDI(),Longitudes.NUCCDI_L);
        String sNUCCNT = formateaCampoNumerico(comunidad.getNUCCNT(),Longitudes.NUCCNT_L);
        String sBITC09 = formateaCampoAlfanumericoCodigo(comunidad.getBITC09(),Longitudes.BITC09_L,"#");
        String sOBTEXC = formateaCampoAlfanumerico(comunidad.getOBTEXC(),Longitudes.OBTEXC_L);
        String sOBDEER = formateaCampoAlfanumerico(comunidad.getOBDEER(),Longitudes.OBDEER_L);

		
		
		return sCODTRN + sCOTDOR + sIDPROV + sCOACCI + sCOENGP + sCOCLDO
				+ sNUDCOM + sBITC10 + sCOACES + sBITC01 + sNOMCOC + sBITC02
				+ sNODCCO + sBITC03 + sNOMPRC + sBITC04 + sNUTPRC + sBITC05
				+ sNOMADC + sBITC06 + sNUTADC + sBITC07 + sNODCAD + sBITC08
				+ sNUCCEN + sNUCCOF + sNUCCDI + sNUCCNT + sBITC09 + sOBTEXC
				+ sOBDEER 
				+ comunidad.getFILLER();
	}

	public static MovimientoCuota leerCuota (String linea)
	{

		String sCODTRN = linea.substring(Posiciones.E2_CODTRN_P, Posiciones.E2_CODTRN_P+Longitudes.CODTRN_L);
		String sCOTDOR = limpiaCampoNumerico(linea.substring(Posiciones.E2_COTDOR_P, Posiciones.E2_COTDOR_P+Longitudes.COTDOR_L));
		String sIDPROV = limpiaCampoNumerico(linea.substring(Posiciones.E2_IDPROV_P, Posiciones.E2_IDPROV_P+Longitudes.IDPROV_L));
		String sCOACCI = linea.substring(Posiciones.E2_COACCI_P, Posiciones.E2_COACCI_P+Longitudes.COACCI_L);
		String sCOCLDO = linea.substring(Posiciones.E2_COCLDO_P, Posiciones.E2_COCLDO_P+Longitudes.COCLDO_L);
		String sNUDCOM = linea.substring(Posiciones.E2_NUDCOM_P, Posiciones.E2_NUDCOM_P+Longitudes.NUDCOM_L).trim();
		String sCOENGP = limpiaCampoNumerico(linea.substring(Posiciones.E2_COENGP_P, Posiciones.E2_COENGP_P+Longitudes.COENGP_L));
		String sCOACES = limpiaCampoNumerico(linea.substring(Posiciones.E2_COACES_P, Posiciones.E2_COACES_P+Longitudes.COACES_L));
		String sCOGRUG = limpiaCampoNumerico(linea.substring(Posiciones.E2_COGRUG_P, Posiciones.E2_COGRUG_P+Longitudes.COGRUG_L));
		String sCOTACA = limpiaCampoNumerico(linea.substring(Posiciones.E2_COTACA_P, Posiciones.E2_COTACA_P+Longitudes.COTACA_L));
		String sCOSBAC = limpiaCampoNumerico(linea.substring(Posiciones.E2_COSBAC_P, Posiciones.E2_COSBAC_P+Longitudes.COSBAC_L));
		String sBITC11 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E2_BITC11_P, Posiciones.E2_BITC11_P+Longitudes.BITC11_L),"#");
		String sFIPAGO = linea.substring(Posiciones.E2_FIPAGO_P, Posiciones.E2_FIPAGO_P+Longitudes.FIPAGO_L);
		String sBITC12 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E2_BITC12_P, Posiciones.E2_BITC12_P+Longitudes.BITC12_L),"#");
		String sFFPAGO = linea.substring(Posiciones.E2_FFPAGO_P, Posiciones.E2_FFPAGO_P+Longitudes.FFPAGO_L);
		String sBITC13 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E2_BITC13_P, Posiciones.E2_BITC13_P+Longitudes.BITC13_L),"#");
		String sIMCUCO = limpiaCampoNumerico(linea.substring(Posiciones.E2_IMCUCO_P, Posiciones.E2_IMCUCO_P+Longitudes.IMCUCO_L));
		String sBITC14 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E2_BITC14_P, Posiciones.E2_BITC14_P+Longitudes.BITC14_L),"#");
		String sFAACTA = linea.substring(Posiciones.E2_FAACTA_P, Posiciones.E2_FAACTA_P+Longitudes.FAACTA_L);
		String sBITC15 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E2_BITC15_P, Posiciones.E2_BITC15_P+Longitudes.BITC15_L),"#");
		String sPTPAGO = linea.substring(Posiciones.E2_PTPAGO_P, Posiciones.E2_PTPAGO_P+Longitudes.PTPAGO_L);
		String sBITC09 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E2_BITC09_P, Posiciones.E2_BITC09_P+Longitudes.BITC09_L),"#");
		String sOBTEXC = linea.substring(Posiciones.E2_OBTEXC_P, Posiciones.E2_OBTEXC_P+Longitudes.OBTEXC_L).trim();
		//String sOBDEER = linea.substring(Posiciones.E2_OBDEER_P, Posiciones.E2_OBDEER_P+Longitudes.OBDEER_L);
		String sOBDEER = linea.substring(Posiciones.E2_OBDEER_P);
		
		return new MovimientoCuota(sCODTRN, sCOTDOR, sIDPROV, sCOACCI, sCOCLDO, sNUDCOM,
				sCOENGP, sCOACES, sCOGRUG, sCOTACA, sCOSBAC, sBITC11, sFIPAGO,
				sBITC12, sFFPAGO, sBITC13, sIMCUCO, sBITC14, sFAACTA, sBITC15,
				sPTPAGO, sBITC09, sOBTEXC, sOBDEER);
	}
	
	public static String escribirCuota (MovimientoCuota cuota)
	{

        String sCODTRN = formateaCampoAlfanumerico(cuota.getCODTRN(),Longitudes.CODTRN_L);
        String sCOTDOR = formateaCampoNumerico(cuota.getCOTDOR(),Longitudes.COTDOR_L);
        String sIDPROV = formateaCampoNumerico(cuota.getIDPROV(),Longitudes.IDPROV_L);
        String sCOACCI = formateaCampoAlfanumerico(cuota.getCOACCI(),Longitudes.COACCI_L);
        String sCOCLDO = formateaCampoAlfanumerico(cuota.getCOCLDO(),Longitudes.COCLDO_L);
        String sNUDCOM = formateaCampoAlfanumerico(cuota.getNUDCOM(),Longitudes.NUDCOM_L);
        String sCOENGP = formateaCampoNumerico(cuota.getCOENGP(),Longitudes.COENGP_L);
        String sCOACES = formateaCampoNumerico(cuota.getCOACES(),Longitudes.COACES_L);
        String sCOGRUG = formateaCampoNumerico(cuota.getCOGRUG(),Longitudes.COGRUG_L);
        String sCOTACA = formateaCampoNumerico(cuota.getCOTACA(),Longitudes.COTACA_L);
        String sCOSBAC = formateaCampoNumerico(cuota.getCOSBAC(),Longitudes.COSBAC_L);
        String sBITC11 = formateaCampoAlfanumericoCodigo(cuota.getBITC11(),Longitudes.BITC11_L,"#");
        String sFIPAGO = formateaCampoNumerico(cuota.getFIPAGO(),Longitudes.FIPAGO_L);
        String sBITC12 = formateaCampoAlfanumericoCodigo(cuota.getBITC12(),Longitudes.BITC12_L,"#");
        String sFFPAGO = formateaCampoNumerico(cuota.getFFPAGO(),Longitudes.FFPAGO_L);
        String sBITC13 = formateaCampoAlfanumericoCodigo(cuota.getBITC13(),Longitudes.BITC13_L,"#");
        String sIMCUCO = formateaCampoNumerico(cuota.getIMCUCO(),Longitudes.IMCUCO_L);
        String sBITC14 = formateaCampoAlfanumericoCodigo(cuota.getBITC14(),Longitudes.BITC14_L,"#");
        String sFAACTA = formateaCampoNumerico(cuota.getFAACTA(),Longitudes.FAACTA_L);
        String sBITC15 = formateaCampoAlfanumericoCodigo(cuota.getBITC15(),Longitudes.BITC15_L,"#");
        String sPTPAGO = formateaCampoAlfanumerico(cuota.getPTPAGO(),Longitudes.PTPAGO_L);
        String sBITC09 = formateaCampoAlfanumericoCodigo(cuota.getBITC09(),Longitudes.BITC09_L,"#");
        String sOBTEXC = formateaCampoAlfanumerico(cuota.getOBTEXC(),Longitudes.OBTEXC_L);
        String sOBDEER = formateaCampoAlfanumerico(cuota.getOBDEER(),Longitudes.OBDEER_L);


		
		
		return sCODTRN + sCOTDOR + sIDPROV + sCOACCI + sCOCLDO + sNUDCOM
				+ sCOENGP + sCOACES + sCOGRUG + sCOTACA + sCOSBAC + sBITC11
				+ sFIPAGO + sBITC12 + sFFPAGO + sBITC13 + sIMCUCO + sBITC14
				+ sFAACTA + sBITC15 + sPTPAGO + sBITC09 + sOBTEXC + sOBDEER 
				+ cuota.getFILLER();
	}
	
	public static MovimientoReferenciaCatastral leerReferenciaCatastral (String linea)
	{

		String sCODTRN = linea.substring(Posiciones.E3_CODTRN_P, Posiciones.E3_CODTRN_P+Longitudes.CODTRN_L);
		String sCOTDOR = limpiaCampoNumerico(linea.substring(Posiciones.E3_COTDOR_P, Posiciones.E3_COTDOR_P+Longitudes.COTDOR_L));
		String sIDPROV = limpiaCampoNumerico(linea.substring(Posiciones.E3_IDPROV_P, Posiciones.E3_IDPROV_P+Longitudes.IDPROV_L));
		String sCOACCI = linea.substring(Posiciones.E3_COACCI_P, Posiciones.E3_COACCI_P+Longitudes.COACCI_L);
		String sCOENGP = limpiaCampoNumerico(linea.substring(Posiciones.E3_COENGP_P, Posiciones.E3_COENGP_P+Longitudes.COENGP_L));
		String sCOACES = limpiaCampoNumerico(linea.substring(Posiciones.E3_COACES_P, Posiciones.E3_COACES_P+Longitudes.COACES_L));
		String sNURCAT = linea.substring(Posiciones.E3_NURCAT_P, Posiciones.E3_NURCAT_P+Longitudes.NURCAT_L).trim();
		String sBITC16 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E3_BITC16_P, Posiciones.E3_BITC16_P+Longitudes.BITC16_L),"#");
		String sTIRCAT = linea.substring(Posiciones.E3_TIRCAT_P, Posiciones.E3_TIRCAT_P+Longitudes.TIRCAT_L).trim();
		String sBITC17 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E3_BITC17_P, Posiciones.E3_BITC17_P+Longitudes.BITC17_L),"#");
		String sENEMIS = linea.substring(Posiciones.E3_ENEMIS_P, Posiciones.E3_ENEMIS_P+Longitudes.ENEMIS_L).trim();
		String sCOTEXA = limpiaCampoNumerico(linea.substring(Posiciones.E3_COTEXA_P, Posiciones.E3_COTEXA_P+Longitudes.COTEXA_L));
		String sBITC09 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E3_BITC09_P, Posiciones.E3_BITC09_P+Longitudes.BITC09_L),"#");
		String sOBTEXC = linea.substring(Posiciones.E3_OBTEXC_P, Posiciones.E3_OBTEXC_P+Longitudes.OBTEXC_L).trim();
		String sOBDEER = linea.substring(Posiciones.E3_OBDEER_P, Posiciones.E3_OBDEER_P+Longitudes.OBDEER_L).trim();

		//Ampliacion de valor catastral
		String sBITC23 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E3_BITC23_P, Posiciones.E3_BITC23_P+Longitudes.BITC23_L),"#");
		String sIMVSUE = limpiaCampoNumerico(linea.substring(Posiciones.E3_IMVSUE_P, Posiciones.E3_IMVSUE_P+Longitudes.IMVSUE_L));
		String sBITC24 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E3_BITC24_P, Posiciones.E3_BITC24_P+Longitudes.BITC24_L),"#");
		String sIMCATA = limpiaCampoNumerico(linea.substring(Posiciones.E3_IMCATA_P, Posiciones.E3_IMCATA_P+Longitudes.IMCATA_L));
		String sBITC25 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E3_BITC25_P, Posiciones.E3_BITC25_P+Longitudes.BITC25_L),"#");
		String sFERECA = linea.substring(Posiciones.E3_FERECA_P, Posiciones.E3_FERECA_P+Longitudes.FERECA_L);
		
		return new MovimientoReferenciaCatastral(sCODTRN, sCOTDOR, sIDPROV, sCOACCI,
				sCOENGP, sCOACES, sNURCAT, sBITC16, sTIRCAT, sBITC17, sENEMIS,
				sCOTEXA, sBITC09, sOBTEXC, sOBDEER, sBITC23, sIMVSUE, sBITC24, sIMCATA, sBITC25, sFERECA);
	}
	
	public static String escribirReferenciaCatastral (MovimientoReferenciaCatastral referenciacatrastral)
	{

        String sCODTRN = formateaCampoAlfanumerico(referenciacatrastral.getCODTRN(),Longitudes.CODTRN_L);
        String sCOTDOR = formateaCampoNumerico(referenciacatrastral.getCOTDOR(),Longitudes.COTDOR_L);
        String sIDPROV = formateaCampoNumerico(referenciacatrastral.getIDPROV(),Longitudes.IDPROV_L);
        String sCOACCI = formateaCampoAlfanumerico(referenciacatrastral.getCOACCI(),Longitudes.COACCI_L);
        String sCOENGP = formateaCampoNumerico(referenciacatrastral.getCOENGP(),Longitudes.COENGP_L);
        String sCOACES = formateaCampoNumerico(referenciacatrastral.getCOACES(),Longitudes.COACES_L);
        String sNURCAT = formateaCampoAlfanumerico(referenciacatrastral.getNURCAT(),Longitudes.NURCAT_L);
        String sBITC16 = formateaCampoAlfanumericoCodigo(referenciacatrastral.getBITC16(),Longitudes.BITC16_L,"#");
        String sTIRCAT = formateaCampoAlfanumerico(referenciacatrastral.getTIRCAT(),Longitudes.TIRCAT_L);
        String sBITC17 = formateaCampoAlfanumericoCodigo(referenciacatrastral.getBITC17(),Longitudes.BITC17_L,"#");
        String sENEMIS = formateaCampoAlfanumerico(referenciacatrastral.getENEMIS(),Longitudes.ENEMIS_L);
        String sCOTEXA = formateaCampoNumerico(referenciacatrastral.getCOTEXA(),Longitudes.COTEXA_L);
        String sBITC09 = formateaCampoAlfanumericoCodigo(referenciacatrastral.getBITC09(),Longitudes.BITC09_L,"#");
        String sOBTEXC = formateaCampoAlfanumerico(referenciacatrastral.getOBTEXC(),Longitudes.OBTEXC_L);
        String sOBDEER = formateaCampoAlfanumerico(referenciacatrastral.getOBDEER(),Longitudes.OBDEER_L);
        
		//Ampliacion de valor catastral
		String sBITC23 = formateaCampoAlfanumericoCodigo(referenciacatrastral.getBITC23(),Longitudes.BITC23_L,"#");
		String sIMVSUE = formateaCampoNumerico(referenciacatrastral.getIMVSUE(),Longitudes.IMVSUE_L);
		String sBITC24 = formateaCampoAlfanumericoCodigo(referenciacatrastral.getBITC24(),Longitudes.BITC24_L,"#");
		String sIMCATA = formateaCampoNumerico(referenciacatrastral.getIMCATA(),Longitudes.IMCATA_L);
		String sBITC25 = formateaCampoAlfanumericoCodigo(referenciacatrastral.getBITC25(),Longitudes.BITC25_L,"#");
		String sFERECA = formateaCampoNumerico(referenciacatrastral.getFERECA(),Longitudes.FERECA_L);

		
		return sCODTRN + sCOTDOR + sIDPROV + sCOACCI + sCOENGP + sCOACES
				+ sNURCAT + sBITC16 + sTIRCAT + sBITC17 + sENEMIS + sCOTEXA
				+ sBITC09 + sOBTEXC + sOBDEER
				//Ampliacion de valor catastral
				+ sBITC23 + sIMVSUE + sBITC24 + sIMCATA + sBITC25 + sFERECA
				
				+ referenciacatrastral.getFILLER();
	}

	public static MovimientoImpuestoRecurso leerImpuestoRecurso (String linea)
	{

		String sCODTRN = linea.substring(Posiciones.E4_CODTRN_P, Posiciones.E4_CODTRN_P+Longitudes.CODTRN_L);
		String sCOTDOR = limpiaCampoNumerico(linea.substring(Posiciones.E4_COTDOR_P, Posiciones.E4_COTDOR_P+Longitudes.COTDOR_L));
		String sIDPROV = limpiaCampoNumerico(linea.substring(Posiciones.E4_IDPROV_P, Posiciones.E4_IDPROV_P+Longitudes.IDPROV_L));
		String sCOACCI = linea.substring(Posiciones.E4_COACCI_P, Posiciones.E4_COACCI_P+Longitudes.COACCI_L);
		String sCOENGP = limpiaCampoNumerico(linea.substring(Posiciones.E4_COENGP_P, Posiciones.E4_COENGP_P+Longitudes.COENGP_L));
		String sCOACES = limpiaCampoNumerico(linea.substring(Posiciones.E4_COACES_P, Posiciones.E4_COACES_P+Longitudes.COACES_L));
		String sNURCAT = linea.substring(Posiciones.E4_NURCAT_P, Posiciones.E4_NURCAT_P+Longitudes.NURCAT_L).trim();
		String sCOGRUC = limpiaCampoNumerico(linea.substring(Posiciones.E4_COGRUC_P, Posiciones.E4_COGRUC_P+Longitudes.COGRUC_L));
		String sCOTACA = limpiaCampoNumerico(linea.substring(Posiciones.E4_COTACA_P, Posiciones.E4_COTACA_P+Longitudes.COTACA_L));
		String sCOSBAC = limpiaCampoNumerico(linea.substring(Posiciones.E4_COSBAC_P, Posiciones.E4_COSBAC_P+Longitudes.COSBAC_L));
		String sBITC18 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E4_BITC18_P, Posiciones.E4_BITC18_P+Longitudes.BITC18_L),"#");
		String sFEPRRE = linea.substring(Posiciones.E4_FEPRRE_P, Posiciones.E4_FEPRRE_P+Longitudes.FEPRRE_L);
		String sBITC19 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E4_BITC19_P, Posiciones.E4_BITC19_P+Longitudes.BITC19_L),"#");
		String sFERERE = linea.substring(Posiciones.E4_FERERE_P, Posiciones.E4_FERERE_P+Longitudes.FERERE_L);
		String sBITC20 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E4_BITC20_P, Posiciones.E4_BITC20_P+Longitudes.BITC20_L),"#");
		String sFEDEIN = linea.substring(Posiciones.E4_FEDEIN_P, Posiciones.E4_FEDEIN_P+Longitudes.FEDEIN_L);
		String sBITC21 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E4_BITC21_P, Posiciones.E4_BITC21_P+Longitudes.BITC21_L),"#");
		String sBISODE = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E4_BISODE_P, Posiciones.E4_BISODE_P+Longitudes.BISODE_L),"#");
		String sBITC22 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E4_BITC22_P, Posiciones.E4_BITC22_P+Longitudes.BITC22_L),"#");
		String sBIRESO = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E4_BIRESO_P, Posiciones.E4_BIRESO_P+Longitudes.BIRESO_L),"#");
		String sCOTEXA = limpiaCampoNumerico(linea.substring(Posiciones.E4_COTEXA_P, Posiciones.E4_COTEXA_P+Longitudes.COTEXA_L));
		String sBITC09 = limpiaCampoAlfanumericoCodigo(linea.substring(Posiciones.E4_BITC09_P, Posiciones.E4_BITC09_P+Longitudes.BITC09_L),"#");
		String sOBTEXC = linea.substring(Posiciones.E4_OBTEXC_P, Posiciones.E4_OBTEXC_P+Longitudes.OBTEXC_L).trim();
		//String sOBDEER = linea.substring(Posiciones.E4_OBDEER_P, Posiciones.E4_OBDEER_P+Longitudes.OBDEER_L);
		String sOBDEER = linea.substring(Posiciones.E4_OBDEER_P);
		
		return new MovimientoImpuestoRecurso(sCODTRN, sCOTDOR, sIDPROV, sCOACCI, sCOENGP,
				sCOACES, sNURCAT, sCOGRUC, sCOTACA, sCOSBAC, sBITC18, sFEPRRE,
				sBITC19, sFERERE, sBITC20, sFEDEIN, sBITC21, sBISODE, sBITC22,
				sBIRESO, sCOTEXA, sBITC09, sOBTEXC, sOBDEER);
	}
	
	public static String escribirImpuestoRecurso (MovimientoImpuestoRecurso impuestorecurso)
	{

        String sCODTRN = formateaCampoAlfanumerico(impuestorecurso.getCODTRN(),Longitudes.CODTRN_L);
        String sCOTDOR = formateaCampoNumerico(impuestorecurso.getCOTDOR(),Longitudes.COTDOR_L);
        String sIDPROV = formateaCampoNumerico(impuestorecurso.getIDPROV(),Longitudes.IDPROV_L);
        String sCOACCI = formateaCampoAlfanumerico(impuestorecurso.getCOACCI(),Longitudes.COACCI_L);
        String sCOENGP = formateaCampoNumerico(impuestorecurso.getCOENGP(),Longitudes.COENGP_L);
        String sCOACES = formateaCampoNumerico(impuestorecurso.getCOACES(),Longitudes.COACES_L);
        String sNURCAT = formateaCampoAlfanumerico(impuestorecurso.getNURCAT(),Longitudes.NURCAT_L);
        String sCOGRUC = formateaCampoNumerico(impuestorecurso.getCOGRUC(),Longitudes.COGRUC_L);
        String sCOTACA = formateaCampoNumerico(impuestorecurso.getCOTACA(),Longitudes.COTACA_L);
        String sCOSBAC = formateaCampoNumerico(impuestorecurso.getCOSBAC(),Longitudes.COSBAC_L);
        String sBITC18 = formateaCampoAlfanumericoCodigo(impuestorecurso.getBITC18(),Longitudes.BITC18_L,"#");
        String sFEPRRE = formateaCampoNumerico(impuestorecurso.getFEPRRE(),Longitudes.FEPRRE_L);
        String sBITC19 = formateaCampoAlfanumericoCodigo(impuestorecurso.getBITC19(),Longitudes.BITC19_L,"#");
        String sFERERE = formateaCampoNumerico(impuestorecurso.getFERERE(),Longitudes.FERERE_L);
        String sBITC20 = formateaCampoAlfanumericoCodigo(impuestorecurso.getBITC20(),Longitudes.BITC20_L,"#");
        String sFEDEIN = formateaCampoNumerico(impuestorecurso.getFEDEIN(),Longitudes.FEDEIN_L);
        String sBITC21 = formateaCampoAlfanumericoCodigo(impuestorecurso.getBITC21(),Longitudes.BITC21_L,"#");
        String sBISODE = formateaCampoAlfanumericoCodigo(impuestorecurso.getBISODE(),Longitudes.BISODE_L,"#");
        String sBITC22 = formateaCampoAlfanumericoCodigo(impuestorecurso.getBITC22(),Longitudes.BITC22_L,"#");
        String sBIRESO = formateaCampoAlfanumericoCodigo(impuestorecurso.getBIRESO(),Longitudes.BIRESO_L,"#");
        String sCOTEXA = formateaCampoNumerico(impuestorecurso.getCOTEXA(),Longitudes.COTEXA_L);
        String sBITC09 = formateaCampoAlfanumericoCodigo(impuestorecurso.getBITC09(),Longitudes.BITC09_L,"#");
        String sOBTEXC = formateaCampoAlfanumerico(impuestorecurso.getOBTEXC(),Longitudes.OBTEXC_L);
        String sOBDEER = formateaCampoAlfanumerico(impuestorecurso.getOBDEER(),Longitudes.OBDEER_L);

		
		return sCODTRN + sCOTDOR + sIDPROV + sCOACCI + sCOENGP + sCOACES
				+ sNURCAT + sCOGRUC + sCOTACA + sCOSBAC + sBITC18 + sFEPRRE
				+ sBITC19 + sFERERE + sBITC20 + sFEDEIN + sBITC21 + sBISODE
				+ sBITC22 + sBIRESO + sCOTEXA + sBITC09 + sOBTEXC + sOBDEER
				+ impuestorecurso.getFILLER();
	}

	public static Cierre LeerCierre(String linea)
	{
		String sNUPROF = limpiaCampoNumerico(linea.substring(0, Longitudes.NUPROF_L));
		String sFEPFON = linea.substring(Longitudes.NUPROF_L+1, Longitudes.NUPROF_L+Longitudes.FEPFON_L+1);

		return new Cierre(sNUPROF,sFEPFON);
	}
	
	public static String escribirCierre (String sNUPROF, String sFEPFON)
	{
        String sFILLER = "                               ";

		return formateaCampoNumerico(sNUPROF,Longitudes.NUPROF_L) + formateaCampoNumerico(sFEPFON,Longitudes.FEPFON_L) + sFILLER;
	}
	
}
