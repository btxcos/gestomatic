package com.provisiones.types;

public class Cuota 
{

	private String COACES = "";
	private String COCLDO = "";
	private String NUDCOM = "";
	private String COSBAC = "";
	private String FIPAGO = "";
	private String FFPAGO = "";
	private String IMCUCO = "";
	private String FAACTA = "";
	private String PTPAGO = "";
	private String OBTEXC = "";

	
	//Constructor de clase

	public Cuota(String cOACES, String cOCLDO, String nUDCOM, String cOSBAC,
			String fIPAGO, String fFPAGO, String iMCUCO, String fAACTA,
			String pTPAGO, String oBTEXC) {
		super();
		COACES = cOACES;
		COCLDO = cOCLDO;
		NUDCOM = nUDCOM;
		COSBAC = cOSBAC;
		FIPAGO = fIPAGO;
		FFPAGO = fFPAGO;
		IMCUCO = iMCUCO;
		FAACTA = fAACTA;
		PTPAGO = pTPAGO;
		OBTEXC = oBTEXC;
	}
	
	//Métodos de acceso

	public String getCOACES() {
		return COACES;
	}

	public void setCOACES(String cOACES) {
		COACES = cOACES;
	}

	public String getCOCLDO() {
		return COCLDO;
	}

	public void setCOCLDO(String cOCLDO) {
		COCLDO = cOCLDO;
	}

	public String getNUDCOM() {
		return NUDCOM;
	}

	public void setNUDCOM(String nUDCOM) {
		NUDCOM = nUDCOM;
	}

	public String getCOSBAC() {
		return COSBAC;
	}

	public void setCOSBAC(String cOSBAC) {
		COSBAC = cOSBAC;
	}

	public String getFIPAGO() {
		return FIPAGO;
	}

	public void setFIPAGO(String fIPAGO) {
		FIPAGO = fIPAGO;
	}

	public String getFFPAGO() {
		return FFPAGO;
	}

	public void setFFPAGO(String fFPAGO) {
		FFPAGO = fFPAGO;
	}

	public String getIMCUCO() {
		return IMCUCO;
	}

	public void setIMCUCO(String iMCUCO) {
		IMCUCO = iMCUCO;
	}

	public String getFAACTA() {
		return FAACTA;
	}

	public void setFAACTA(String fAACTA) {
		FAACTA = fAACTA;
	}

	public String getPTPAGO() {
		return PTPAGO;
	}

	public void setPTPAGO(String pTPAGO) {
		PTPAGO = pTPAGO;
	}

	public String getOBTEXC() {
		return OBTEXC;
	}

	public void setOBTEXC(String oBTEXC) {
		OBTEXC = oBTEXC;
	}

	public String logCuota()
	{
		return String.format("(CUOTA)\nCOACES:|%s|\nCOCLDO:|%s|\nNUDCOM:|%s|\nCOSBAC:|%s|\nFIPAGO:|%s|\nFFPAGO:|%s|\nIMCUCO:|%s|\nFAACTA:|%s|\nPTPAGO:|%s|\nOBTEXC:|%s|",COACES,COCLDO,NUDCOM,COSBAC,FIPAGO,FFPAGO,IMCUCO,FAACTA,PTPAGO,OBTEXC);
	}
	
}

//Autor: Francisco Valverde Manjón