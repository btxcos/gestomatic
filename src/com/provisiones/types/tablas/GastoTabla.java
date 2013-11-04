package com.provisiones.types.tablas;

public class GastoTabla 
{
	private String COACES = "";
	private String COGRUG = "";
	private String COTPGA = "";
	private String COSBGA = "";
	private String DCOSBGA = "";
	private String PTPAGO = "";
	private String DPTPAGO = "";
	private String FEDEVE = "";
	private String COSIGA = "";
	private String DCOSIGA = "";
	private String IMNGAS = "";

	//Constructor de clase
	
	public GastoTabla(String cOACES, String cOGRUG, String cOTPGA,
			String cOSBGA, String dCOSBGA, String pTPAGO, String dPTPAGO,
			String fEDEVE, String cOSIGA, String dCOSIGA, String iMNGAS) {
		super();
		COACES = cOACES;
		COGRUG = cOGRUG;
		COTPGA = cOTPGA;
		COSBGA = cOSBGA;
		DCOSBGA = dCOSBGA;
		PTPAGO = pTPAGO;
		DPTPAGO = dPTPAGO;
		FEDEVE = fEDEVE;
		COSIGA = cOSIGA;
		DCOSIGA = dCOSIGA;
		IMNGAS = iMNGAS;
	}
	
	//Métodos de acceso

	public String getCOACES() {
		return COACES;
	}

	public void setCOACES(String cOACES) {
		COACES = cOACES;
	}

	public String getCOGRUG() {
		return COGRUG;
	}

	public void setCOGRUG(String cOGRUG) {
		COGRUG = cOGRUG;
	}

	public String getCOTPGA() {
		return COTPGA;
	}

	public void setCOTPGA(String cOTPGA) {
		COTPGA = cOTPGA;
	}

	public String getCOSBGA() {
		return COSBGA;
	}

	public void setCOSBGA(String cOSBGA) {
		COSBGA = cOSBGA;
	}

	public String getDCOSBGA() {
		return DCOSBGA;
	}

	public void setDCOSBGA(String dCOSBGA) {
		DCOSBGA = dCOSBGA;
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

	public String getFEDEVE() {
		return FEDEVE;
	}

	public void setFEDEVE(String fEDEVE) {
		FEDEVE = fEDEVE;
	}

	public String getCOSIGA() {
		return COSIGA;
	}

	public void setCOSIGA(String cOSIGA) {
		COSIGA = cOSIGA;
	}

	public String getDCOSIGA() {
		return DCOSIGA;
	}

	public void setDCOSIGA(String dCOSIGA) {
		DCOSIGA = dCOSIGA;
	}

	public String getIMNGAS() {
		return IMNGAS;
	}

	public void setIMNGAS(String iMNGAS) {
		IMNGAS = iMNGAS;
	}
}
