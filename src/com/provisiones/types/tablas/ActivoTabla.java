package com.provisiones.types.tablas;

public class ActivoTabla  
{
	//Identificador
	private String COACES = "";

	private String COPOIN = "";
	private String NOMUIN = "";
	private String NOPRAC = "";
	private String NOVIAS = "";
	private String NUPIAC = "";
	private String NUPOAC = "";
	private String NUPUAC = "";
	private String NUFIRE = "";
	private String NURCAT = "";
	
	
	
	//Constructor de clase
	public ActivoTabla(String cOACES, String cOPOIN, String nOMUIN,
			String nOPRAC, String nOVIAS, String nUPIAC, String nUPOAC,
			String nUPUAC, String nUFIRE, String nURCAT) {
		super();
		COACES = cOACES;
		COPOIN = cOPOIN;
		NOMUIN = nOMUIN;
		NOPRAC = nOPRAC;
		NOVIAS = nOVIAS;
		NUPIAC = nUPIAC;
		NUPOAC = nUPOAC;
		NUPUAC = nUPUAC;
		NUFIRE = nUFIRE;
		NURCAT = nURCAT;
	}


	public String getCOACES() {
		return COACES;
	}

	public void setCOACES(String cOACES) {
		COACES = cOACES;
	}

	public String getCOPOIN() {
		return COPOIN;
	}

	public void setCOPOIN(String cOPOIN) {
		COPOIN = cOPOIN;
	}

	public String getNOMUIN() {
		return NOMUIN;
	}

	public void setNOMUIN(String nOMUIN) {
		NOMUIN = nOMUIN;
	}

	public String getNOPRAC() {
		return NOPRAC;
	}

	public void setNOPRAC(String nOPRAC) {
		NOPRAC = nOPRAC;
	}

	public String getNOVIAS() {
		return NOVIAS;
	}

	public void setNOVIAS(String nOVIAS) {
		NOVIAS = nOVIAS;
	}

	public String getNUPIAC() {
		return NUPIAC;
	}

	public void setNUPIAC(String nUPIAC) {
		NUPIAC = nUPIAC;
	}

	public String getNUPOAC() {
		return NUPOAC;
	}

	public void setNUPOAC(String nUPOAC) {
		NUPOAC = nUPOAC;
	}

	public String getNUPUAC() {
		return NUPUAC;
	}

	public void setNUPUAC(String nUPUAC) {
		NUPUAC = nUPUAC;
	}

	public String getNUFIRE() {
		return NUFIRE;
	}

	public void setNUFIRE(String nUFIRE) {
		NUFIRE = nUFIRE;
	}

	public String getNURCAT() {
		return NURCAT;
	}

	public void setNURCAT(String nURCAT) {
		NURCAT = nURCAT;
	}

}

//Autor: Francisco Valverde Manjón