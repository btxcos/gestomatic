package com.provisiones.pl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class GestorDatosGenerales implements Serializable 
{


	private static final long serialVersionUID = -669897445986653574L;

	private Map<String,String> tiposcocldoHM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposptpagoHM = new LinkedHashMap<String, String>();

	private Map<String,String> tiposcogrugHM = new LinkedHashMap<String, String>();
	//private Map<String,String> tiposcotpgaHM = new LinkedHashMap<String, String>();
	//private Map<String,String> tiposcosbgaHM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposcotpga_g1HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcotpga_g2HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcotpga_g3HM = new LinkedHashMap<String, String>();

	private Map<String,String> tiposcosbga_t11HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbga_t21HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbga_t22HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbga_t23HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbga_t32HM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposcosigaHM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcoimptHM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcotnegHM = new LinkedHashMap<String, String>();
	
	
	private Map<String,String> tiposbiresoHM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposbinariaHM = new LinkedHashMap<String, String>();
	
	public GestorDatosGenerales()
	{
		tiposcocldoHM.put("C.I.F.",                     "2");
		tiposcocldoHM.put("C.I.F país extranjero.",     "5");
		tiposcocldoHM.put("Otros persona jurídica.",    "J");
		
		tiposbiresoHM.put("FAVORABLE",   "F");
		tiposbiresoHM.put("DESFAVORABLE","D");
		
		tiposbinariaHM.put("SI","S");
		tiposbinariaHM.put("NO","N");

		tiposptpagoHM.put("APERIODICO",      "1");
		tiposptpagoHM.put("MENSUAL",         "2");
		tiposptpagoHM.put("BIMENSUAL",       "3");
		tiposptpagoHM.put("TRIMESTRAL",      "4");
		tiposptpagoHM.put("CUATRIMESTRAL",   "5");
		tiposptpagoHM.put("SEMESTRAL",       "6");
		tiposptpagoHM.put("ANUAL",           "7");
		tiposptpagoHM.put("VARIOS PERIODOS", "8");
		
		tiposcogrugHM.put("Compraventa",      "1");
		tiposcogrugHM.put("Pendientes",       "2");
		tiposcogrugHM.put("Acciones",         "3");

		tiposcotpga_g1HM.put("Plusvalia", "1");
		tiposcotpga_g1HM.put("Notaria",   "2");

		tiposcotpga_g2HM.put("Tasas-Impuestos", "1");
		tiposcotpga_g2HM.put("Comunidades",     "2");
		tiposcotpga_g2HM.put("Suministros",     "3");
		
		tiposcotpga_g3HM.put("Honorarios","2");
		tiposcotpga_g3HM.put("Licencias", "3");
		
		tiposcosbga_t11HM.put("Plusvalia", "0");
		tiposcosbga_t11HM.put("Notaria",   "1");
		/*tiposcosbga_t11HM.put("Devolucion Plusvalia", "50");
		tiposcosbga_t11HM.put("Devolucion Notaria",     "51");*/

		tiposcosbga_t21HM.put("Impuestos e IBIS",                     "0");
		tiposcosbga_t21HM.put("IBIS",                                 "1");
		tiposcosbga_t21HM.put("Tasas basura",                         "2");
		tiposcosbga_t21HM.put("Tasas alcantarillado",                 "3");
		tiposcosbga_t21HM.put("Tasas agua",                           "4");
		tiposcosbga_t21HM.put("Contribuciones especiales",            "5");
		tiposcosbga_t21HM.put("Otras tasas",                          "6");
		/*tiposcosbga_t21HM.put("Devolución Impuestos e IBIS",         "50");
		tiposcosbga_t21HM.put("Devolución IBIS",                     "51");
		tiposcosbga_t21HM.put("Devolución Tasas basura",             "52");
		tiposcosbga_t21HM.put("Devolución Tasas alcantarillado",     "53");
		tiposcosbga_t21HM.put("Devolución Tasas agua",               "54");
		tiposcosbga_t21HM.put("Devolución Contribuciones especiales","55");
		tiposcosbga_t21HM.put("Devolución Otras tasas",              "56");*/
		
		tiposcosbga_t22HM.put("Comunidad",	                   	"0");  
		tiposcosbga_t22HM.put("Ordinaria",                     	"1");  
		tiposcosbga_t22HM.put("Extras Comunidad",              	"2");  
		tiposcosbga_t22HM.put("Mancomunidad",                  	"3");  
		tiposcosbga_t22HM.put("Extras Mancomunidad",           	"4");  
		tiposcosbga_t22HM.put("Obras comunidad",               	"5");  
		/*tiposcosbga_t22HM.put("Devolucion Comunidades",       "50"); 
		tiposcosbga_t22HM.put("Devolucion Ordinaria",          	"51"); 
		tiposcosbga_t22HM.put("Devolucion Extras Comunidad",   	"52"); 
		tiposcosbga_t22HM.put("Devolucion Mancomunidad",       	"53"); 
		tiposcosbga_t22HM.put("Devolucion Extras Mancomunidad",	"54"); 
		tiposcosbga_t22HM.put("Devolucion Obras comunidad",   	"55");*/
		
		
		tiposcosbga_t23HM.put("Suministros",               "0");
		tiposcosbga_t23HM.put("Suministro luz",            "1");
		tiposcosbga_t23HM.put("Suministro agua",           "2");
		tiposcosbga_t23HM.put("Suministro gas",            "3");
		/*tiposcosbga_t23HM.put("Devolucion Suministros",  "50");
		tiposcosbga_t23HM.put("Devolucion Suministro luz", "51");
		tiposcosbga_t23HM.put("Devolucion Suministro agua","52");
		tiposcosbga_t23HM.put("Devolución Suministro gas", "53");*/
		
		tiposcosbga_t32HM.put("Honorarios Colaboradores","0");  
		tiposcosbga_t32HM.put("Prescripcion",            "1");  
		tiposcosbga_t32HM.put("Colaboracion",            "2");  
		tiposcosbga_t32HM.put("Otros honorarios",        "3");  
		tiposcosbga_t32HM.put("Servicios varios",        "4");
		
		tiposcosigaHM.put("ESTIMADO",            "1");
		tiposcosigaHM.put("CONOCIDO",            "2");
		tiposcosigaHM.put("AUTORIZADO",          "3");
		tiposcosigaHM.put("PAGADO",              "4");
		tiposcosigaHM.put("PAGADO PARCIALMENTE", "5");
		tiposcosigaHM.put("ESPERA DE PAGO",		 "6");
		tiposcosigaHM.put("PAGADO CONEXION",     "7");
		
		tiposcoimptHM.put("SIN IMPUESTO",	"0");  
		tiposcoimptHM.put("IVA",            "1");  
		tiposcoimptHM.put("IGIC",           "2");  
		tiposcoimptHM.put("IPSI",           "3");  
		tiposcoimptHM.put("IRPF",           "4");
		
		tiposcotnegHM.put("SIN INFORMAR",	"0");  
		tiposcotnegHM.put("CLIENTE",        "1");  
		tiposcotnegHM.put("CAJA",           "2");
	
	}

	public Map<String, String> getTiposcocldoHM() {
		return tiposcocldoHM;
	}

	public void setTiposcocldoHM(Map<String, String> tiposcocldoHM) {
		this.tiposcocldoHM = tiposcocldoHM;
	}

	public Map<String, String> getTiposptpagoHM() {
		return tiposptpagoHM;
	}

	public void setTiposptpagoHM(Map<String, String> tiposptpagoHM) {
		this.tiposptpagoHM = tiposptpagoHM;
	}

	public Map<String, String> getTiposcogrugHM() {
		return tiposcogrugHM;
	}

	public void setTiposcogrugHM(Map<String, String> tiposcogrugHM) {
		this.tiposcogrugHM = tiposcogrugHM;
	}

	public Map<String, String> getTiposcotpga_g1HM() {
		return tiposcotpga_g1HM;
	}

	public void setTiposcotpga_g1HM(Map<String, String> tiposcotpga_g1HM) {
		this.tiposcotpga_g1HM = tiposcotpga_g1HM;
	}

	public Map<String, String> getTiposcotpga_g2HM() {
		return tiposcotpga_g2HM;
	}

	public void setTiposcotpga_g2HM(Map<String, String> tiposcotpga_g2HM) {
		this.tiposcotpga_g2HM = tiposcotpga_g2HM;
	}

	public Map<String, String> getTiposcotpga_g3HM() {
		return tiposcotpga_g3HM;
	}

	public void setTiposcotpga_g3HM(Map<String, String> tiposcotpga_g3HM) {
		this.tiposcotpga_g3HM = tiposcotpga_g3HM;
	}

	public Map<String, String> getTiposcosbga_t11HM() {
		return tiposcosbga_t11HM;
	}

	public void setTiposcosbga_t11HM(Map<String, String> tiposcosbga_t11HM) {
		this.tiposcosbga_t11HM = tiposcosbga_t11HM;
	}

	public Map<String, String> getTiposcosbga_t21HM() {
		return tiposcosbga_t21HM;
	}

	public void setTiposcosbga_t21HM(Map<String, String> tiposcosbga_t21HM) {
		this.tiposcosbga_t21HM = tiposcosbga_t21HM;
	}

	public Map<String, String> getTiposcosbga_t22HM() {
		return tiposcosbga_t22HM;
	}

	public void setTiposcosbga_t22HM(Map<String, String> tiposcosbga_t22HM) {
		this.tiposcosbga_t22HM = tiposcosbga_t22HM;
	}

	public Map<String, String> getTiposcosbga_t23HM() {
		return tiposcosbga_t23HM;
	}

	public void setTiposcosbga_t23HM(Map<String, String> tiposcosbga_t23HM) {
		this.tiposcosbga_t23HM = tiposcosbga_t23HM;
	}

	public Map<String, String> getTiposcosbga_t32HM() {
		return tiposcosbga_t32HM;
	}

	public void setTiposcosbga_t32HM(Map<String, String> tiposcosbga_t32HM) {
		this.tiposcosbga_t32HM = tiposcosbga_t32HM;
	}

	public Map<String, String> getTiposbiresoHM() {
		return tiposbiresoHM;
	}

	public void setTiposbiresoHM(Map<String, String> tiposbiresoHM) {
		this.tiposbiresoHM = tiposbiresoHM;
	}

	public Map<String, String> getTiposbinariaHM() {
		return tiposbinariaHM;
	}

	public void setTiposbinariaHM(Map<String, String> tiposbinariaHM) {
		this.tiposbinariaHM = tiposbinariaHM;
	}

	public Map<String,String> getTiposcosigaHM() {
		return tiposcosigaHM;
	}

	public void setTiposcosigaHM(Map<String,String> tiposcosigaHM) {
		this.tiposcosigaHM = tiposcosigaHM;
	}

	public Map<String,String> getTiposcoimptHM() {
		return tiposcoimptHM;
	}

	public void setTiposcoimptHM(Map<String,String> tiposcoimptHM) {
		this.tiposcoimptHM = tiposcoimptHM;
	}

	public Map<String, String> getTiposcotnegHM() {
		return tiposcotnegHM;
	}

	public void setTiposcotnegHM(Map<String, String> tiposcotnegHM) {
		this.tiposcotnegHM = tiposcotnegHM;
	}

	
}
