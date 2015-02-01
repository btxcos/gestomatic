package com.provisiones.types.errores;

public class ErrorGastoTabla 
{
	private String NUPROF = "";
	private String COACES = "";
	
	private String COGRUG = "";
	private String COTPGA = "";
	private String COSBGA = "";
	private String DCOSBGA = "";
	private String IMNGAS = "";
	private String FEDEVE = "";

	private String MOVIMIENTO = "";
	private String ERRORES = "";

	//Constructor de clase

	public ErrorGastoTabla(String nUPROF, String cOACES, String cOGRUG,
			String cOTPGA, String cOSBGA, String dCOSBGA, String iMNGAS,
			String fEDEVE, String mOVIMIENTO, String eRRORES) {
		super();
		NUPROF = nUPROF;
		COACES = cOACES;
		COGRUG = cOGRUG;
		COTPGA = cOTPGA;
		COSBGA = cOSBGA;
		DCOSBGA = dCOSBGA;
		IMNGAS = iMNGAS;
		FEDEVE = fEDEVE;
		MOVIMIENTO = mOVIMIENTO;
		ERRORES = eRRORES;
	}

	//Métodos de acceso

	public String getNUPROF() {
		return NUPROF;
	}

	public void setNUPROF(String nUPROF) {
		NUPROF = nUPROF;
	}
	
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

	public String getIMNGAS() {
		return IMNGAS;
	}

	public void setIMNGAS(String iMNGAS) {
		IMNGAS = iMNGAS;
	}

	public String getFEDEVE() {
		return FEDEVE;
	}

	public void setFEDEVE(String fEDEVE) {
		FEDEVE = fEDEVE;
	}

	public String getMOVIMIENTO() {
		return MOVIMIENTO;
	}

	public void setMOVIMIENTO(String mOVIMIENTO) {
		MOVIMIENTO = mOVIMIENTO;
	}

	public String getERRORES() {
		return ERRORES;
	}

	public void setERRORES(String eRRORES) {
		ERRORES = eRRORES;
	}
}

//Autor: Francisco Valverde Manjón