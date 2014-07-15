package com.provisiones.types.tablas;

public class GastoTabla 
{
	//Identificador
	private String sGastoID = "";
	
	private String NUPROF = "";
	private String ESTADOPROF = "";
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
	private String ESTADO = "";
	private String FEEPAI = "";
	private String FELIPG = "";
	private String TIPOPAGO = "";

	//Constructor de clase
	
	public GastoTabla(String sGastoID, String nUPROF, String eSTADOPROF,
			String cOACES, String cOGRUG, String cOTPGA, String cOSBGA,
			String dCOSBGA, String pTPAGO, String dPTPAGO, String fEDEVE,
			String cOSIGA, String dCOSIGA, String iMNGAS, String eSTADO,
			String fEEPAI, String fELIPG, String tIPOPAGO) {
		super();
		this.sGastoID = sGastoID;
		NUPROF = nUPROF;
		ESTADOPROF = eSTADOPROF;
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
		ESTADO = eSTADO;
		FEEPAI = fEEPAI;
		FELIPG = fELIPG;
		TIPOPAGO = tIPOPAGO;
	}
	
	//Métodos de acceso

	public String getsGastoID() {
		return sGastoID;
	}

	public void setsGastoID(String sGastoID) {
		this.sGastoID = sGastoID;
	}

	public String getNUPROF() {
		return NUPROF;
	}

	public void setNUPROF(String nUPROF) {
		NUPROF = nUPROF;
	}
	
	public String getESTADOPROF() {
		return ESTADOPROF;
	}

	public void setESTADOPROF(String eSTADOPROF) {
		ESTADOPROF = eSTADOPROF;
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

	public String getESTADO() {
		return ESTADO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public String getFEEPAI() {
		return FEEPAI;
	}

	public void setFEEPAI(String fEEPAI) {
		FEEPAI = fEEPAI;
	}

	public String getFELIPG() {
		return FELIPG;
	}

	public void setFELIPG(String fELIPG) {
		FELIPG = fELIPG;
	}

	public String getTIPOPAGO() {
		return TIPOPAGO;
	}

	public void setTIPOPAGO(String tIPOPAGO) {
		TIPOPAGO = tIPOPAGO;
	}

}
