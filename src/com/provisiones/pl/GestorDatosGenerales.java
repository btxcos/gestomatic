package com.provisiones.pl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class GestorDatosGenerales implements Serializable 
{


	private static final long serialVersionUID = -669897445986653574L;

	private Map<String,String> tiposcocldoHM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposptpagoHM = new LinkedHashMap<String, String>();

	private Map<String,String> tiposcosbga_t21HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbga_t22HM = new LinkedHashMap<String, String>();
	
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
		/*tiposcosbga_t22HM.put("Devolucion Comunidades",        	"50"); 
		tiposcosbga_t22HM.put("Devolucion Ordinaria",          	"51"); 
		tiposcosbga_t22HM.put("Devolucion Extras Comunidad",   	"52"); 
		tiposcosbga_t22HM.put("Devolucion Mancomunidad",       	"53"); 
		tiposcosbga_t22HM.put("Devolucion Extras Mancomunidad",	"54"); 
		tiposcosbga_t22HM.put("Devolucion Obras comunidad",   	"55");*/
		
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
	
	

}
