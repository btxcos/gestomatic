package com.provisiones.types;

public class Gasto 
{

	private String COACES = "";
	private String COGRUG = "";
	private String COTPGA = "";
	private String COSBGA = "";
	private String FEDEVE = "";
	
	private String IMPORTE = "";

	
	//Constructor de clase
	
	public Gasto(String cOACES, String cOGRUG, String cOTPGA, String cOSBGA,
			String fEDEVE, String iMPORTE) {
		super();
		COACES = cOACES;
		COGRUG = cOGRUG;
		COTPGA = cOTPGA;
		COSBGA = cOSBGA;
		FEDEVE = fEDEVE;
		IMPORTE = iMPORTE;
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

	public String getFEDEVE() {
		return FEDEVE;
	}

	public void setFEDEVE(String fEDEVE) {
		FEDEVE = fEDEVE;
	}

	public String getIMPORTE() {
		return IMPORTE;
	}

	public void setIMPORTE(String iMPORTE) {
		IMPORTE = iMPORTE;
	}
}
