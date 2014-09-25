package com.provisiones.types.tablas;

public class ImpuestoRecursoTabla 
{
	//Identificador
	private String sRecursoID = "";

	private String NURCAT = "";
	private String COSBAC = "";
	private String DCOSBAC = "";
	private String FEPRRE = "";
	private String FERERE = "";
	private String FEDEIN = "";
	private String BISODE = "";
	private String DBISODE = "";
	private String BIRESO = "";
	private String DBIRESO = "";
	private String OBTEXC = "";
	private String ESTADO = "";
	
	//Constructor de clase

	public ImpuestoRecursoTabla(String sRecursoID, String nURCAT,
			String cOSBAC, String dCOSBAC, String fEPRRE, String fERERE,
			String fEDEIN, String bISODE, String dBISODE, String bIRESO,
			String dBIRESO, String oBTEXC, String eSTADO) {
		super();
		this.sRecursoID = sRecursoID;
		NURCAT = nURCAT;
		COSBAC = cOSBAC;
		DCOSBAC = dCOSBAC;
		FEPRRE = fEPRRE;
		FERERE = fERERE;
		FEDEIN = fEDEIN;
		BISODE = bISODE;
		DBISODE = dBISODE;
		BIRESO = bIRESO;
		DBIRESO = dBIRESO;
		OBTEXC = oBTEXC;
		ESTADO = eSTADO;
	}

	//Métodos de acceso

	public String getsRecursoID() {
		return sRecursoID;
	}

	public void setsRecursoID(String sRecursoID) {
		this.sRecursoID = sRecursoID;
	}
	
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

	public String getDCOSBAC() {
		return DCOSBAC;
	}

	public void setDCOSBAC(String dCOSBAC) {
		DCOSBAC = dCOSBAC;
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

	public String getDBISODE() {
		return DBISODE;
	}

	public void setDBISODE(String dBISODE) {
		DBISODE = dBISODE;
	}

	public String getBIRESO() {
		return BIRESO;
	}

	public void setBIRESO(String bIRESO) {
		BIRESO = bIRESO;
	}

	public String getDBIRESO() {
		return DBIRESO;
	}

	public void setDBIRESO(String dBIRESO) {
		DBIRESO = dBIRESO;
	}

	public String getOBTEXC() {
		return OBTEXC;
	}

	public void setOBTEXC(String oBTEXC) {
		OBTEXC = oBTEXC;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

}
