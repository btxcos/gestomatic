package com.provisiones.types;

public class Pago 
{
	private String sGasto = "";
	private String sTP = "";
	private String sFEPGPR = "";
	private String sPais = "";	
	private String sDCIBAN = "";
	private String sNUCCEN = "";
	private String sNUCCOF = "";
	private String sNUCCDI = "";
	private String sNUCCNT = "";

	//Constructor de clase
	
	public Pago(String sGasto, String sTP, String sFEPGPR,
			String sPais, String sDCIBAN, String sNUCCEN, String sNUCCOF,
			String sNUCCDI, String sNUCCNT) {
		super();
		this.sGasto = sGasto;
		this.sTP = sTP;
		this.sFEPGPR = sFEPGPR;
		this.sPais = sPais;
		this.sDCIBAN = sDCIBAN;
		this.sNUCCEN = sNUCCEN;
		this.sNUCCOF = sNUCCOF;
		this.sNUCCDI = sNUCCDI;
		this.sNUCCNT = sNUCCNT;
	}
	
	//Métodos de acceso

	public String getsGasto() {
		return sGasto;
	}

	public void setsGasto(String sGasto) {
		this.sGasto = sGasto;
	}

	public String getsTP() {
		return sTP;
	}

	public void setsTP(String sTP) {
		this.sTP = sTP;
	}

	public String getsFEPGPR() {
		return sFEPGPR;
	}

	public void setsFEPGPR(String sFEPGPR) {
		this.sFEPGPR = sFEPGPR;
	}

	public String getsPais() {
		return sPais;
	}

	public void setsPais(String sPais) {
		this.sPais = sPais;
	}

	public String getsDCIBAN() {
		return sDCIBAN;
	}

	public void setsDCIBAN(String sDCIBAN) {
		this.sDCIBAN = sDCIBAN;
	}

	public String getsNUCCEN() {
		return sNUCCEN;
	}

	public void setsNUCCEN(String sNUCCEN) {
		this.sNUCCEN = sNUCCEN;
	}

	public String getsNUCCOF() {
		return sNUCCOF;
	}

	public void setsNUCCOF(String sNUCCOF) {
		this.sNUCCOF = sNUCCOF;
	}

	public String getsNUCCDI() {
		return sNUCCDI;
	}

	public void setsNUCCDI(String sNUCCDI) {
		this.sNUCCDI = sNUCCDI;
	}

	public String getsNUCCNT() {
		return sNUCCNT;
	}

	public void setsNUCCNT(String sNUCCNT) {
		this.sNUCCNT = sNUCCNT;
	}
	
	//log
	public String logPago()
	{
		return String.format("(PAGO)\nsGasto :|"+sGasto+"|\nsTP    :|"+sTP+"|\nsFEPGPR:|"+sFEPGPR+"|\nsPais  :|"+sPais+"|\nsDCIBAN:|"+sDCIBAN+"|\nsNUCCEN:|"+sNUCCEN+"|\nsNUCCOF:|"+sNUCCOF+"|\nsNUCCDI:|"+sNUCCDI+"|\nsNUCCNT:|"+sNUCCNT+"|");
	}
	
}
