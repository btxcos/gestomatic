package com.provisiones.types;

public class Cuenta 
{
	private String sPais = "";
	private String sDCIBAN = "";
	private String sNUCCEN = "";
	private String sNUCCOF = "";
	private String sNUCCDI = "";
	private String sNUCCNT = "";
	private String sDescripcion = "";

	//Constructor de clase

	public Cuenta(String sPais, String sDCIBAN, String sNUCCEN, String sNUCCOF,
			String sNUCCDI, String sNUCCNT, String sDescripcion) {
		super();
		this.sPais = sPais;
		this.sDCIBAN = sDCIBAN;
		this.sNUCCEN = sNUCCEN;
		this.sNUCCOF = sNUCCOF;
		this.sNUCCDI = sNUCCDI;
		this.sNUCCNT = sNUCCNT;
		this.sDescripcion = sDescripcion;
	}	

	//Métodos de acceso
	
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

	public String getsDescripcion() {
		return sDescripcion;
	}

	public void setsDescripcion(String sDescripcion) {
		this.sDescripcion = sDescripcion;
	}
	
}
