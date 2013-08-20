package com.provisiones.types;

public class ProvisionTabla 
{
	private String NUPROF = "";
	private String COSPAT = "";
	private String DCOSPAT = "";
	private String VALOR = "";
	private String GASTOS = "";

	//Constructor de clase
	
	public ProvisionTabla(String nUPROF, String cOSPAT, String dCOSPAT,
			String vALOR, String gASTOS) {
		super();
		NUPROF = nUPROF;
		COSPAT = cOSPAT;
		DCOSPAT = dCOSPAT;
		VALOR = vALOR;
		GASTOS = gASTOS;
	}

	//Métodos de acceso
	
	public String getNUPROF() {
		return NUPROF;
	}

	public void setNUPROF(String nUPROF) {
		NUPROF = nUPROF;
	}

	public String getCOSPAT() {
		return COSPAT;
	}

	public void setCOSPAT(String cOSPAT) {
		COSPAT = cOSPAT;
	}

	public String getDCOSPAT() {
		return DCOSPAT;
	}

	public void setDCOSPAT(String dCOSPAT) {
		DCOSPAT = dCOSPAT;
	}

	public String getVALOR() {
		return VALOR;
	}

	public void setVALOR(String vALOR) {
		VALOR = vALOR;
	}

	public String getGASTOS() {
		return GASTOS;
	}

	public void setGASTOS(String gASTOS) {
		GASTOS = gASTOS;
	}
	
	
	
	
}
