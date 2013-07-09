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
		
		
		
		
		return new Activo(
				linea.substring(Posiciones.AC_COACES_P, Posiciones.AC_COACES_P+Longitudes.COACES_L),
				linea.substring(Posiciones.AC_NUINMU_P, Posiciones.AC_NUINMU_P+Longitudes.NUINMU_L),
				linea.substring(Posiciones.AC_COSOPA_P, Posiciones.AC_COSOPA_P+Longitudes.COSOPA_L),
				linea.substring(Posiciones.AC_COENAE_P, Posiciones.AC_COENAE_P+Longitudes.COENAE_L),
				linea.substring(Posiciones.AC_COESEN_P, Posiciones.AC_COESEN_P+Longitudes.COESEN_L),
				linea.substring(Posiciones.AC_NOVIAS_P, Posiciones.AC_NOVIAS_P+Longitudes.NOVIAS_L).trim(),
				linea.substring(Posiciones.AC_NUPOAC_P, Posiciones.AC_NUPOAC_P+Longitudes.NUPOAC_L).trim(),
				linea.substring(Posiciones.AC_NUESAC_P, Posiciones.AC_NUESAC_P+Longitudes.NUESAC_L).trim(),
				linea.substring(Posiciones.AC_NUPIAC_P, Posiciones.AC_NUPIAC_P+Longitudes.NUPIAC_L).trim(),
				linea.substring(Posiciones.AC_NUPUAC_P, Posiciones.AC_NUPUAC_P+Longitudes.NUPUAC_L).trim(),
				linea.substring(Posiciones.AC_NOMUIN_P, Posiciones.AC_NOMUIN_P+Longitudes.NOMUIN_L).trim(),
				linea.substring(Posiciones.AC_COPRAE_P, Posiciones.AC_COPRAE_P+Longitudes.COPRAE_L),
				linea.substring(Posiciones.AC_NOPRAC_P, Posiciones.AC_NOPRAC_P+Longitudes.NOPRAC_L).trim(),
				linea.substring(Posiciones.AC_COPOIN_P, Posiciones.AC_COPOIN_P+Longitudes.COPOIN_L),
				linea.substring(Posiciones.AC_FEREAP_P, Posiciones.AC_FEREAP_P+Longitudes.FEREAP_L),
				linea.substring(Posiciones.AC_COREAE_P, Posiciones.AC_COREAE_P+Longitudes.COREAE_L),
				linea.substring(Posiciones.AC_FEINAU_P, Posiciones.AC_FEINAU_P+Longitudes.FEINAU_L),
				linea.substring(Posiciones.AC_FESOPO_P, Posiciones.AC_FESOPO_P+Longitudes.FESOPO_L),
				linea.substring(Posiciones.AC_FESEPO_P, Posiciones.AC_FESEPO_P+Longitudes.FESEPO_L),
				linea.substring(Posiciones.AC_FEREPO_P, Posiciones.AC_FEREPO_P+Longitudes.FEREPO_L),
				linea.substring(Posiciones.AC_FEADAC_P, Posiciones.AC_FEADAC_P+Longitudes.FEADAC_L),
				linea.substring(Posiciones.AC_CODIJU_P, Posiciones.AC_CODIJU_P+Longitudes.CODIJU_L),
				linea.substring(Posiciones.AC_COSJUP_P, Posiciones.AC_COSJUP_P+Longitudes.COSJUP_L),
				linea.substring(Posiciones.AC_COSTLI_P, Posiciones.AC_COSTLI_P+Longitudes.COSTLI_L),
				linea.substring(Posiciones.AC_COSCAR_P, Posiciones.AC_COSCAR_P+Longitudes.COSCAR_L),
				linea.substring(Posiciones.AC_COESVE_P, Posiciones.AC_COESVE_P+Longitudes.COESVE_L),
				linea.substring(Posiciones.AC_COTSIN_P, Posiciones.AC_COTSIN_P+Longitudes.COTSIN_L),
				linea.substring(Posiciones.AC_NUFIRE_P, Posiciones.AC_NUFIRE_P+Longitudes.NUFIRE_L).trim(),
				linea.substring(Posiciones.AC_NUREGP_P, Posiciones.AC_NUREGP_P+Longitudes.NUREGP_L),
				linea.substring(Posiciones.AC_NOMUI0_P, Posiciones.AC_NOMUI0_P+Longitudes.NOMUI0_L).trim(),
				linea.substring(Posiciones.AC_NULIBE_P, Posiciones.AC_NULIBE_P+Longitudes.NULIBE_L),
				linea.substring(Posiciones.AC_NUTOME_P, Posiciones.AC_NUTOME_P+Longitudes.NUTOME_L),
				linea.substring(Posiciones.AC_NUFOLE_P, Posiciones.AC_NUFOLE_P+Longitudes.NUFOLE_L),
				linea.substring(Posiciones.AC_NUINSR_P, Posiciones.AC_NUINSR_P+Longitudes.NUINSR_L),
				linea.substring(Posiciones.AC_COSOCU_P, Posiciones.AC_COSOCU_P+Longitudes.COSOCU_L),
				linea.substring(Posiciones.AC_COXPRO_P, Posiciones.AC_COXPRO_P+Longitudes.COXPRO_L),
				linea.substring(Posiciones.AC_FESOLA_P, Posiciones.AC_FESOLA_P+Longitudes.FESOLA_L),
				linea.substring(Posiciones.AC_FESELA_P, Posiciones.AC_FESELA_P+Longitudes.FESELA_L),
				linea.substring(Posiciones.AC_FERELA_P, Posiciones.AC_FERELA_P+Longitudes.FERELA_L),
				linea.substring(Posiciones.AC_FERLLA_P, Posiciones.AC_FERLLA_P+Longitudes.FERLLA_L),
				linea.substring(Posiciones.AC_CASPRE_P, Posiciones.AC_CASPRE_P+Longitudes.CASPRE_L),
				linea.substring(Posiciones.AC_CASUTR_P, Posiciones.AC_CASUTR_P+Longitudes.CASUTR_L),
				linea.substring(Posiciones.AC_CASUTC_P, Posiciones.AC_CASUTC_P+Longitudes.CASUTC_L),
				linea.substring(Posiciones.AC_CASUTG_P, Posiciones.AC_CASUTG_P+Longitudes.CASUTG_L),
				linea.substring(Posiciones.AC_BIARRE_P, Posiciones.AC_BIARRE_P+Longitudes.BIARRE_L),
				linea.substring(Posiciones.AC_CADORM_P, Posiciones.AC_CADORM_P+Longitudes.CADORM_L),
				linea.substring(Posiciones.AC_CABANO_P, Posiciones.AC_CABANO_P+Longitudes.CABANO_L),
				linea.substring(Posiciones.AC_BIGAPA_P, Posiciones.AC_BIGAPA_P+Longitudes.BIGAPA_L),
				linea.substring(Posiciones.AC_CAGAPA_P, Posiciones.AC_CAGAPA_P+Longitudes.CAGAPA_L),
				linea.substring(Posiciones.AC_CASUTE_P, Posiciones.AC_CASUTE_P+Longitudes.CASUTE_L),
				linea.substring(Posiciones.AC_BILIPO_P, Posiciones.AC_BILIPO_P+Longitudes.BILIPO_L),
				linea.substring(Posiciones.AC_BILIAC_P, Posiciones.AC_BILIAC_P+Longitudes.BILIAC_L),
				linea.substring(Posiciones.AC_BILIUS_P, Posiciones.AC_BILIUS_P+Longitudes.BILIUS_L),
				linea.substring(Posiciones.AC_BIBOIN_P, Posiciones.AC_BIBOIN_P+Longitudes.BIBOIN_L),
				linea.substring(Posiciones.AC_BICEFI_P, Posiciones.AC_BICEFI_P+Longitudes.BICEFI_L),
				linea.substring(Posiciones.AC_CASUCB_P, Posiciones.AC_CASUCB_P+Longitudes.CASUCB_L),
				linea.substring(Posiciones.AC_CASUCS_P, Posiciones.AC_CASUCS_P+Longitudes.CASUCS_L),
				linea.substring(Posiciones.AC_FEACON_P, Posiciones.AC_FEACON_P+Longitudes.FEACON_L),
				linea.substring(Posiciones.AC_IDAUTO_P, Posiciones.AC_IDAUTO_P+Longitudes.IDAUTO_L),
				linea.substring(Posiciones.AC_FEDEMA_P, Posiciones.AC_FEDEMA_P+Longitudes.FEDEMA_L),
				linea.substring(Posiciones.AC_YNOCUR_P, Posiciones.AC_YNOCUR_P+Longitudes.YNOCUR_L),
				linea.substring(Posiciones.AC_OBRECO_P, Posiciones.AC_OBRECO_P+Longitudes.OBRECO_L),
				linea.substring(Posiciones.AC_YNOLEC_P, Posiciones.AC_YNOLEC_P+Longitudes.YNOLEC_L),
				linea.substring(Posiciones.AC_NOLOJZ_P, Posiciones.AC_NOLOJZ_P+Longitudes.NOLOJZ_L),
				linea.substring(Posiciones.AC_FEREDE_P, Posiciones.AC_FEREDE_P+Longitudes.FEREDE_L),
				linea.substring(Posiciones.AC_POPROP_P, Posiciones.AC_POPROP_P+Longitudes.POPROP_L),
				linea.substring(Posiciones.AC_COGRAP_P, Posiciones.AC_COGRAP_P+Longitudes.COGRAP_L),
				linea.substring(Posiciones.AC_FEPREG_P, Posiciones.AC_FEPREG_P+Longitudes.FEPREG_L),
				linea.substring(Posiciones.AC_FEPHAC_P, Posiciones.AC_FEPHAC_P+Longitudes.FEPHAC_L),
				linea.substring(Posiciones.AC_FEFOAC_P, Posiciones.AC_FEFOAC_P+Longitudes.FEFOAC_L),
				linea.substring(Posiciones.AC_FEVACT_P, Posiciones.AC_FEVACT_P+Longitudes.FEVACT_L),
				linea.substring(Posiciones.AC_IMVACT_P, Posiciones.AC_IMVACT_P+Longitudes.IMVACT_L),
				linea.substring(Posiciones.AC_NUFIPR_P, Posiciones.AC_NUFIPR_P+Longitudes.NUFIPR_L),
				linea.substring(Posiciones.AC_COTPET_P, Posiciones.AC_COTPET_P+Longitudes.COTPET_L),
				linea.substring(Posiciones.AC_FEEMPT_P, Posiciones.AC_FEEMPT_P+Longitudes.FEEMPT_L),
				linea.substring(Posiciones.AC_FESORC_P, Posiciones.AC_FESORC_P+Longitudes.FESORC_L),
				linea.substring(Posiciones.AC_FESODE_P, Posiciones.AC_FESODE_P+Longitudes.FESODE_L),
				linea.substring(Posiciones.AC_FEREAC_P, Posiciones.AC_FEREAC_P+Longitudes.FEREAC_L),
				linea.substring(Posiciones.AC_COXSIA_P, Posiciones.AC_COXSIA_P+Longitudes.COXSIA_L),
				linea.substring(Posiciones.AC_NUJUZD_P, Posiciones.AC_NUJUZD_P+Longitudes.NUJUZD_L),
				linea.substring(Posiciones.AC_NURCAT_P, Posiciones.AC_NURCAT_P+Longitudes.NURCAT_L),
				linea.substring(Posiciones.AC_NOMPRC_P, Posiciones.AC_NOMPRC_P+Longitudes.NOMPRC_L),
				linea.substring(Posiciones.AC_NUTPRC_P, Posiciones.AC_NUTPRC_P+Longitudes.NUTPRC_L),
				linea.substring(Posiciones.AC_NOMADC_P, Posiciones.AC_NOMADC_P+Longitudes.NOMADC_L),
				linea.substring(Posiciones.AC_NUTADC_P, Posiciones.AC_NUTADC_P+Longitudes.NUTADC_L),
				linea.substring(Posiciones.AC_IMPCOO_P, Posiciones.AC_IMPCOO_P+Longitudes.IMPCOO_L),
				linea.substring(Posiciones.AC_COENOR_P, Posiciones.AC_COENOR_P+Longitudes.COENOR_L),
				linea.substring(Posiciones.AC_COSPAT_P, Posiciones.AC_COSPAT_P+Longitudes.COSPAT_L),
				linea.substring(Posiciones.AC_COSPAS_P, Posiciones.AC_COSPAS_P+Longitudes.COSPAS_L),
				linea.substring(Posiciones.AC_IDCOL3_P, Posiciones.AC_IDCOL3_P+Longitudes.IDCOL3_L),
				linea.substring(Posiciones.AC_BIOBNU_P, Posiciones.AC_BIOBNU_P+Longitudes.BIOBNU_L),
				linea.substring(Posiciones.AC_POBRAR_P, Posiciones.AC_POBRAR_P+Longitudes.POBRAR_L)
);
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
