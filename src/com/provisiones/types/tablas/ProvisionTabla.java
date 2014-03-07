package com.provisiones.types.tablas;

public class ProvisionTabla 
{
	//Identificador
	private String NUPROF = "";
	
	private String COSPAT = "";
	private String DCOSPAT = "";
	private String TAS = "";
	private String DTAS = "";	
	private String COGRUG = "";
	private String DCOGRUG = "";	
	private String COTPGA = "";
	private String DCOTPGA = "";
	private String FEPFON = "";
	private String VALOR = "";
	private String GASTOS = "";

	//Constructor de clase
	
	public ProvisionTabla(String nUPROF, String cOSPAT, String dCOSPAT,
			String tAS, String dTAS, String cOGRUG, String dCOGRUG,
			String cOTPGA, String dCOTPGA, String fEPFON, String vALOR,
			String gASTOS) {
		super();
		NUPROF = nUPROF;
		COSPAT = cOSPAT;
		DCOSPAT = dCOSPAT;
		TAS = tAS;
		DTAS = dTAS;
		COGRUG = cOGRUG;
		DCOGRUG = dCOGRUG;
		COTPGA = cOTPGA;
		DCOTPGA = dCOTPGA;
		setFEPFON(fEPFON);
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

	public String getTAS() {
		return TAS;
	}

	public void setTAS(String tAS) {
		TAS = tAS;
	}

	public String getDTAS() {
		return DTAS;
	}

	public void setDTAS(String dTAS) {
		DTAS = dTAS;
	}

	public String getCOGRUG() {
		return COGRUG;
	}

	public void setCOGRUG(String cOGRUG) {
		COGRUG = cOGRUG;
	}

	public String getDCOGRUG() {
		return DCOGRUG;
	}

	public void setDCOGRUG(String dCOGRUG) {
		DCOGRUG = dCOGRUG;
	}

	public String getCOTPGA() {
		return COTPGA;
	}

	public void setCOTPGA(String cOTPGA) {
		COTPGA = cOTPGA;
	}

	public String getDCOTPGA() {
		return DCOTPGA;
	}

	public void setDCOTPGA(String dCOTPGA) {
		DCOTPGA = dCOTPGA;
	}

	public String getFEPFON() {
		return FEPFON;
	}

	public void setFEPFON(String fEPFON) {
		FEPFON = fEPFON;
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
