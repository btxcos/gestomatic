package com.provisiones.types.tablas;

public class CuotaTabla 
{
	private String COCLDO = "";
	private String DCOCLDO = "";
	private String NUDCOM = "";
	private String COSBAC = "";
	private String DCOSBAC = "";
	private String FIPAGO = "";
	private String FFPAGO = "";
	private String IMCUCO = "";
	private String FAACTA = "";
	private String PTPAGO = "";
	private String DPTPAGO = "";
	private String OBTEXC = "";

	//Constructor de clase	
	
	public CuotaTabla(String cOCLDO, String dCOCLDO, String nUDCOM,
			String cOSBAC, String dCOSBAC, String fIPAGO, String fFPAGO,
			String iMCUCO, String fAACTA, String pTPAGO, String dPTPAGO,
			String oBTEXC) {
		super();
		COCLDO = cOCLDO;
		DCOCLDO = dCOCLDO;
		NUDCOM = nUDCOM;
		COSBAC = cOSBAC;
		DCOSBAC = dCOSBAC;
		FIPAGO = fIPAGO;
		FFPAGO = fFPAGO;
		IMCUCO = iMCUCO;
		FAACTA = fAACTA;
		PTPAGO = pTPAGO;
		DPTPAGO = dPTPAGO;
		OBTEXC = oBTEXC;
	}

	//Métodos de acceso
	
	public String getCOCLDO() {
		return COCLDO;
	}

	public void setCOCLDO(String cOCLDO) {
		COCLDO = cOCLDO;
	}

	public String getDCOCLDO() {
		return DCOCLDO;
	}

	public void setDCOCLDO(String dCOCLDO) {
		DCOCLDO = dCOCLDO;
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

	public String getDCOSBAC() {
		return DCOSBAC;
	}

	public void setDCOSBAC(String dCOSBAC) {
		DCOSBAC = dCOSBAC;
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

	public String getDPTPAGO() {
		return DPTPAGO;
	}

	public void setDPTPAGO(String dPTPAGO) {
		DPTPAGO = dPTPAGO;
	}

	public String getOBTEXC() {
		return OBTEXC;
	}

	public void setOBTEXC(String oBTEXC) {
		OBTEXC = oBTEXC;
	}

	
	
	
}
