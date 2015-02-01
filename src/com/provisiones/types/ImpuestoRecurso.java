package com.provisiones.types;

public class ImpuestoRecurso 
{

	private String NURCAT = "";
	private String COSBAC = "";
	private String FEPRRE = "";
	private String FERERE = "";
	private String FEDEIN = "";
	private String BISODE = "";
	private String BIRESO = "";
	private String COTEXA = "";
	private String OBTEXC = "";

	//Constructor de clase

	public ImpuestoRecurso(String nURCAT, String cOSBAC, String fEPRRE,
			String fERERE, String fEDEIN, String bISODE, String bIRESO,
			String cOTEXA, String oBTEXC) {
		super();
		NURCAT = nURCAT;
		COSBAC = cOSBAC;
		FEPRRE = fEPRRE;
		FERERE = fERERE;
		FEDEIN = fEDEIN;
		BISODE = bISODE;
		BIRESO = bIRESO;
		COTEXA = cOTEXA;
		OBTEXC = oBTEXC;
	}

	//Métodos de acceso

		public String getNURCAT() {
		return NURCAT;
	}

	public void setNURCAT(String nURCAT) {
		NURCAT = nURCAT;
	}

	public String getCOSBAC() {
		return COSBAC;
	}

	public void setCOSBAC(String cOSBAC) {
		COSBAC = cOSBAC;
	}

	public String getFEPRRE() {
		return FEPRRE;
	}

	public void setFEPRRE(String fEPRRE) {
		FEPRRE = fEPRRE;
	}

	public String getFERERE() {
		return FERERE;
	}

	public void setFERERE(String fERERE) {
		FERERE = fERERE;
	}

	public String getFEDEIN() {
		return FEDEIN;
	}

	public void setFEDEIN(String fEDEIN) {
		FEDEIN = fEDEIN;
	}

	public String getBISODE() {
		return BISODE;
	}

	public void setBISODE(String bISODE) {
		BISODE = bISODE;
	}

	public String getBIRESO() {
		return BIRESO;
	}

	public void setBIRESO(String bIRESO) {
		BIRESO = bIRESO;
	}

	public String getCOTEXA() {
		return COTEXA;
	}

	public void setCOTEXA(String cOTEXA) {
		COTEXA = cOTEXA;
	}

	public String getOBTEXC() {
		return OBTEXC;
	}

	public void setOBTEXC(String oBTEXC) {
		OBTEXC = oBTEXC;
	}

	public String logImpuestoRecurso()
	{
		return String.format("(IMPUESTO/RECURSO)\nNURCAT:|%s|\nCOSBAC:|%s|\nFEPRRE:|%s|\nFERERE:|%s|\nFEDEIN:|%s|\nBISODE:|%s|\nBIRESO:|%s|\nCOTEXA:|%s|\nOBTEXC:|%s|",NURCAT,COSBAC,FEPRRE,FERERE,FEDEIN,BISODE,BIRESO,COTEXA,OBTEXC);
	}
}

//Autor: Francisco Valverde Manjón