package com.provisiones.types;

public class CuotaTabla 
{

	private String COCLDO = "";
	private String NUDCOM = "";
	private String COSBAC = "";
	private String DesCOSBAC = "";
	private String FIPAGO = "";
	private String FFPAGO = "";
	private String IMCUCO = "";
	private String FAACTA = "";
	private String PTPAGO = "";
	private String DesPTPAGO = "";
	private String OBTEXC = "";
	
	//Constructor de clase

	public CuotaTabla(String cOCLDO, String nUDCOM, String cOSBAC,
			String desCOSBAC, String fIPAGO, String fFPAGO, String iMCUCO,
			String fAACTA, String pTPAGO, String desPTPAGO, String oBTEXC) {
		super();
		COCLDO = cOCLDO;
		NUDCOM = nUDCOM;
		COSBAC = cOSBAC;
		DesCOSBAC = desCOSBAC;
		FIPAGO = fIPAGO;
		FFPAGO = fFPAGO;
		IMCUCO = iMCUCO;
		FAACTA = fAACTA;
		PTPAGO = pTPAGO;
		DesPTPAGO = desPTPAGO;
		OBTEXC = oBTEXC;
	}
	
	//Métodos de acceso
	
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
	public String getDesCOSBAC() {
		return DesCOSBAC;
	}
	public void setDesCOSBAC(String desCOSBAC) {
		DesCOSBAC = desCOSBAC;
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
	public String getDesPTPAGO() {
		return DesPTPAGO;
	}
	public void setDesPTPAGO(String desPTPAGO) {
		DesPTPAGO = desPTPAGO;
	}
	public String getOBTEXC() {
		return OBTEXC;
	}
	public void setOBTEXC(String oBTEXC) {
		OBTEXC = oBTEXC;
	}
	
	
}
