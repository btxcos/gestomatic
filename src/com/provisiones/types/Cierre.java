package com.provisiones.types;

public class Cierre 
{
	private String sNUPROF = "";
	private String sFEPFON = "";

	//Constructor de clase
	
	public Cierre(String sNUPROF, String sFEPFON) {
		super();
		this.sNUPROF = sNUPROF;
		this.sFEPFON = sFEPFON;
	}

	//Métodos de acceso
	
	public String getsNUPROF() {
		return sNUPROF;
	}

	public void setsNUPROF(String sNUPROF) {
		this.sNUPROF = sNUPROF;
	}

	public String getsFEPFON() {
		return sFEPFON;
	}

	public void setsFEPFON(String sFEPFON) {
		this.sFEPFON = sFEPFON;
	}
	
	

}

//Autor: Francisco Valverde Manjón