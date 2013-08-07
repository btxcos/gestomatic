package com.provisiones.pl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.event.ActionEvent;

import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

public class GestorMovimientosCuotas implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 558593056565873600L;

	private String sCODTRN = ValoresDefecto.DEF_E2_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOACCI = "";
	private String sCOENGP = ValoresDefecto.DEF_COENGP;

	private String sCOACES = "";
	
	private String sCOCLDO = "";
	private String sNUDCOM = "";
	private String sNOMCOC = "";
	private String sNODCCO = "";
	
	private String sCOSBAC = "";
	private String sFIPAGO = "";
	private String sFFPAGO = "";
	private String sIMCUCO = "";
	private String sFAACTA = "";
	private String sPTPAGO = "";
	private String sOBTEXC = "";
	
	private String sOBDEER = "";
	
	
	private Map<String,String> tiposdocumentoHM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposperiodicidadHM = new LinkedHashMap<String, String>();
	private Map<String,String> tipospagoHM = new LinkedHashMap<String, String>();
	

	public GestorMovimientosCuotas()
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

		Utils.standardIO2File("");//Salida por fichero de texto
	}
	
	public void borrarCampos()
	{
		this.sCOCLDO = "";
		this.sNUDCOM = "";
		this.sCOSBAC = "";
		this.sFIPAGO = "";
		this.sFFPAGO = "";
		this.sIMCUCO = "";
		this.sFAACTA = "";
		this.sPTPAGO = "";
		this.sOBTEXC = "";
	}
	
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
		this.sCOCLDO = "";
		this.sNUDCOM = "";
		this.sCOSBAC = "";
		this.sFIPAGO = "";
		this.sFFPAGO = "";
		this.sIMCUCO = "";
		this.sFAACTA = "";
		this.sPTPAGO = "";
		this.sOBTEXC = "";

    	this.sCOACES = "";

    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
   	
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
  	
    } 

    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";

    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
    	
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
   	
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




	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
	}

	public String getsCODTRN() {
		return sCODTRN;
	}

	public void setsCODTRN(String sCODTRN) {
		this.sCODTRN = sCODTRN;
	}

	public String getsCOTDOR() {
		return sCOTDOR;
	}

	public void setsCOTDOR(String sCOTDOR) {
		this.sCOTDOR = sCOTDOR;
	}

	public String getsIDPROV() {
		return sIDPROV;
	}

	public void setsIDPROV(String sIDPROV) {
		this.sIDPROV = sIDPROV;
	}

	public String getsCOACCI() {
		return sCOACCI;
	}

	public void setsCOACCI(String sCOACCI) {
		this.sCOACCI = sCOACCI;
	}

	public String getsCOENGP() {
		return sCOENGP;
	}

	public void setsCOENGP(String sCOENGP) {
		this.sCOENGP = sCOENGP;
	}

	public String getsCOCLDO() {
		return sCOCLDO;
	}

	public void setsCOCLDO(String sCOCLDO) {
		this.sCOCLDO = sCOCLDO;
	}

	public String getsNUDCOM() {
		return sNUDCOM;
	}

	public void setsNUDCOM(String sNUDCOM) {
		this.sNUDCOM = sNUDCOM;
	}

	public String getsNOMCOC() {
		return sNOMCOC;
	}

	public void setsNOMCOC(String sNOMCOC) {
		this.sNOMCOC = sNOMCOC;
	}

	public String getsNODCCO() {
		return sNODCCO;
	}

	public void setsNODCCO(String sNODCCO) {
		this.sNODCCO = sNODCCO;
	}

	public String getsCOSBAC() {
		return sCOSBAC;
	}

	public void setsCOSBAC(String sCOSBAC) {
		this.sCOSBAC = sCOSBAC;
	}

	public String getsFIPAGO() {
		return sFIPAGO;
	}

	public void setsFIPAGO(String sFIPAGO) {
		this.sFIPAGO = sFIPAGO;
	}

	public String getsFFPAGO() {
		return sFFPAGO;
	}

	public void setsFFPAGO(String sFFPAGO) {
		this.sFFPAGO = sFFPAGO;
	}

	public String getsIMCUCO() {
		return sIMCUCO;
	}

	public void setsIMCUCO(String sIMCUCO) {
		this.sIMCUCO = sIMCUCO;
	}

	public String getsFAACTA() {
		return sFAACTA;
	}

	public void setsFAACTA(String sFAACTA) {
		this.sFAACTA = sFAACTA;
	}

	public String getsPTPAGO() {
		return sPTPAGO;
	}

	public void setsPTPAGO(String sPTPAGO) {
		this.sPTPAGO = sPTPAGO;
	}

	public String getsOBTEXC() {
		return sOBTEXC;
	}

	public void setsOBTEXC(String sOBTEXC) {
		this.sOBTEXC = sOBTEXC;
	}

	public String getsOBDEER() {
		return sOBDEER;
	}

	public void setsOBDEER(String sOBDEER) {
		this.sOBDEER = sOBDEER;
	}
	
	
	
}
