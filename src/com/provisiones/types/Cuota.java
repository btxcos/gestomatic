package com.provisiones.types;

public class Cuota 
{

	private String CODTRN = "";
	private String COTDOR = "";
	private String IDPROV = "";
	private String COACCI = "";
	private String COCLDO = "";
	private String NUDCOM = "";
	private String COENGP = "";
	private String COACES = "";
	private String COGRUG = "";
	private String COTACA = "";
	private String COSBAC = "";
	private String BITC11 = "";
	private String FIPAGO = "";
	private String BITC12 = "";
	private String FFPAGO = "";
	private String BITC13 = "";
	private String IMCUCO = "";
	private String BITC14 = "";
	private String FAACTA = "";
	private String BITC15 = "";
	private String PTPAGO = "";
	private String BITC09 = "";
	private String OBTEXC = "";
	private String OBDEER = "";
	private String FILLER = "                                                                                                                  ";
	
	
	//Constructor de clase
	public Cuota(String cODTRN, String cOTDOR, String iDPROV, String cOACCI,
			String cOCLDO, String nUDCOM, String cOENGP, String cOACES,
			String cOGRUG, String cOTACA, String cOSBAC, String bITC11,
			String fIPAGO, String bITC12, String fFPAGO, String bITC13,
			String iMCUCO, String bITC14, String fAACTA, String bITC15,
			String pTPAGO, String bITC09, String oBTEXC, String oBDEER) {
		super();
		CODTRN = cODTRN;
		COTDOR = cOTDOR;
		IDPROV = iDPROV;
		COACCI = cOACCI;
		COCLDO = cOCLDO;
		NUDCOM = nUDCOM;
		COENGP = cOENGP;
		COACES = cOACES;
		COGRUG = cOGRUG;
		COTACA = cOTACA;
		COSBAC = cOSBAC;
		BITC11 = bITC11;
		FIPAGO = fIPAGO;
		BITC12 = bITC12;
		FFPAGO = fFPAGO;
		BITC13 = bITC13;
		IMCUCO = iMCUCO;
		BITC14 = bITC14;
		FAACTA = fAACTA;
		BITC15 = bITC15;
		PTPAGO = pTPAGO;
		BITC09 = bITC09;
		OBTEXC = oBTEXC;
		OBDEER = oBDEER;
	}



	//Métodos de acceso
	public String getFILLER() {
		return FILLER;
	}

	public String getCODTRN() {
		return CODTRN;
	}

	public void setCODTRN(String cODTRN) {
		CODTRN = cODTRN;
	}

	public String getCOTDOR() {
		return COTDOR;
	}

	public void setCOTDOR(String cOTDOR) {
		COTDOR = cOTDOR;
	}

	public String getIDPROV() {
		return IDPROV;
	}

	public void setIDPROV(String iDPROV) {
		IDPROV = iDPROV;
	}

	public String getCOACCI() {
		return COACCI;
	}

	public void setCOACCI(String cOACCI) {
		COACCI = cOACCI;
	}

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

	public String getCOENGP() {
		return COENGP;
	}

	public void setCOENGP(String cOENGP) {
		COENGP = cOENGP;
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

	public String getCOTACA() {
		return COTACA;
	}

	public void setCOTACA(String cOTACA) {
		COTACA = cOTACA;
	}

	public String getCOSBAC() {
		return COSBAC;
	}

	public void setCOSBAC(String cOSBAC) {
		COSBAC = cOSBAC;
	}

	public String getBITC11() {
		return BITC11;
	}

	public void setBITC11(String bITC11) {
		BITC11 = bITC11;
	}

	public String getFIPAGO() {
		return FIPAGO;
	}

	public void setFIPAGO(String fIPAGO) {
		FIPAGO = fIPAGO;
	}

	public String getBITC12() {
		return BITC12;
	}

	public void setBITC12(String bITC12) {
		BITC12 = bITC12;
	}

	public String getFFPAGO() {
		return FFPAGO;
	}

	public void setFFPAGO(String fFPAGO) {
		FFPAGO = fFPAGO;
	}

	public String getBITC13() {
		return BITC13;
	}

	public void setBITC13(String bITC13) {
		BITC13 = bITC13;
	}

	public String getIMCUCO() {
		return IMCUCO;
	}

	public void setIMCUCO(String iMCUCO) {
		IMCUCO = iMCUCO;
	}

	public String getBITC14() {
		return BITC14;
	}

	public void setBITC14(String bITC14) {
		BITC14 = bITC14;
	}

	public String getFAACTA() {
		return FAACTA;
	}

	public void setFAACTA(String fAACTA) {
		FAACTA = fAACTA;
	}

	public String getBITC15() {
		return BITC15;
	}

	public void setBITC15(String bITC15) {
		BITC15 = bITC15;
	}

	public String getPTPAGO() {
		return PTPAGO;
	}

	public void setPTPAGO(String pTPAGO) {
		PTPAGO = pTPAGO;
	}

	public String getBITC09() {
		return BITC09;
	}

	public void setBITC09(String bITC09) {
		BITC09 = bITC09;
	}

	public String getOBTEXC() {
		return OBTEXC;
	}

	public void setOBTEXC(String oBTEXC) {
		OBTEXC = oBTEXC;
	}

	public String getOBDEER() {
		return OBDEER;
	}

	public void setOBDEER(String oBDEER) {
		OBDEER = oBDEER;
	}



}
