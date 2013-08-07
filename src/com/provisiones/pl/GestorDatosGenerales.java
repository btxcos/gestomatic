package com.provisiones.pl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class GestorDatosGenerales implements Serializable 
{


	private static final long serialVersionUID = -669897445986653574L;


	
	private Map<String,String> tiposdocumentoHM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposperiodicidadHM = new LinkedHashMap<String, String>();
	private Map<String,String> tipospagoHM = new LinkedHashMap<String, String>();

	public GestorDatosGenerales()
	{
		tiposdocumentoHM.put("C.I.F.",                     "2");
		tiposdocumentoHM.put("C.I.F país extranjero.",     "5");
		tiposdocumentoHM.put("Otros persona jurídica.",    "J");
		
		
		tiposperiodicidadHM.put("APERIODICO",      "1");
		tiposperiodicidadHM.put("MENSUAL",         "2");
		tiposperiodicidadHM.put("BIMENSUAL",       "3");
		tiposperiodicidadHM.put("TRIMESTRAL",      "4");
		tiposperiodicidadHM.put("CUATRIMESTRAL",   "5");
		tiposperiodicidadHM.put("SEMESTRAL",       "6");
		tiposperiodicidadHM.put("ANUAL",           "7");
		tiposperiodicidadHM.put("VARIOS PERIODOS", "8");
		
		tipospagoHM.put("Comunidad",	                   	"0");  
		tipospagoHM.put("Ordinaria",                     	"1");  
		tipospagoHM.put("Extras Comunidad",              	"2");  
		tipospagoHM.put("Mancomunidad",                  	"3");  
		tipospagoHM.put("Extras Mancomunidad",           	"4");  
		tipospagoHM.put("Obras comunidad",               	"5");  
		/*tipospagoHM.put("Devolucion Comunidades",        	"50"); 
		tipospagoHM.put("Devolucion Ordinaria",          	"51"); 
		tipospagoHM.put("Devolucion Extras Comunidad",   	"52"); 
		tipospagoHM.put("Devolucion Mancomunidad",       	"53"); 
		tipospagoHM.put("Devolucion Extras Mancomunidad",	"54"); 
		tipospagoHM.put("Devolucion Obras comunidad",   	"55");*/ 
	}
	
	
	
	public Map<String, String> getTiposdocumentoHM() {
		return tiposdocumentoHM;
	}
	public void setTiposdocumentoHM(Map<String, String> tiposdocumentoHM) {
		this.tiposdocumentoHM = tiposdocumentoHM;
	}
	public Map<String, String> getTiposperiodicidadHM() {
		return tiposperiodicidadHM;
	}
	public void setTiposperiodicidadHM(Map<String, String> tiposperiodicidadHM) {
		this.tiposperiodicidadHM = tiposperiodicidadHM;
	}
	public Map<String, String> getTipospagoHM() {
		return tipospagoHM;
	}
	public void setTipospagoHM(Map<String, String> tipospagoHM) {
		this.tipospagoHM = tipospagoHM;
	}
	
	

}
