package com.provisiones.types;

public class Comunidad 
{

	private String sCOCLDO = "";
	private String sNUDCOM = "";
	private String sNOMCOC = "";
	private String sNODCCO = "";
	private String sNOMPRC = "";
	private String sNUTPRC = "";
	private String sNOMADC = "";
	private String sNUTADC = "";
	private String sNODCAD = "";
	private String sCuenta = "";
	private String sOBTEXC = "";

	//Constructor de clase

	public Comunidad(String sCOCLDO, String sNUDCOM, String sNOMCOC,
			String sNODCCO, String sNOMPRC, String sNUTPRC, String sNOMADC,
			String sNUTADC, String sNODCAD, String sCuenta, String sOBTEXC) {
		super();
		this.sCOCLDO = sCOCLDO;
		this.sNUDCOM = sNUDCOM;
		this.sNOMCOC = sNOMCOC;
		this.sNODCCO = sNODCCO;
		this.sNOMPRC = sNOMPRC;
		this.sNUTPRC = sNUTPRC;
		this.sNOMADC = sNOMADC;
		this.sNUTADC = sNUTADC;
		this.sNODCAD = sNODCAD;
		this.sCuenta = sCuenta;
		this.sOBTEXC = sOBTEXC;
	}

	//Métodos de acceso

	public String getsCOCLDO() {
		return sCOCLDO;
	}

	public void setsCOCLDO(String sCOCLDO) {
		this.sCOCLDO = sCOCLDO;
	}

	public String getsNUDCOM() {
		return sNUDCOM;
	}

	public void setsNUDCOM(String sNUDCOM) {
		this.sNUDCOM = sNUDCOM;
	}

	public String getsNOMCOC() {
		return sNOMCOC;
	}

	public void setsNOMCOC(String sNOMCOC) {
		this.sNOMCOC = sNOMCOC;
	}

	public String getsNODCCO() {
		return sNODCCO;
	}

	public void setsNODCCO(String sNODCCO) {
		this.sNODCCO = sNODCCO;
	}

	public String getsNOMPRC() {
		return sNOMPRC;
	}

	public void setsNOMPRC(String sNOMPRC) {
		this.sNOMPRC = sNOMPRC;
	}

	public String getsNUTPRC() {
		return sNUTPRC;
	}

	public void setsNUTPRC(String sNUTPRC) {
		this.sNUTPRC = sNUTPRC;
	}

	public String getsNOMADC() {
		return sNOMADC;
	}

	public void setsNOMADC(String sNOMADC) {
		this.sNOMADC = sNOMADC;
	}

	public String getsNUTADC() {
		return sNUTADC;
	}

	public void setsNUTADC(String sNUTADC) {
		this.sNUTADC = sNUTADC;
	}

	public String getsNODCAD() {
		return sNODCAD;
	}

	public void setsNODCAD(String sNODCAD) {
		this.sNODCAD = sNODCAD;
	}

	public String getsCuenta() {
		return sCuenta;
	}

	public void setsCuenta(String sCuenta) {
		this.sCuenta = sCuenta;
	}

	public String getsOBTEXC() {
		return sOBTEXC;
	}

	public void setsOBTEXC(String sOBTEXC) {
		this.sOBTEXC = sOBTEXC;
	}

	public String logComunidad()
	{
		return "(COMUNIDAD)\nCOCLDO:|"+sCOCLDO+
				"|\nNUDCOM:|"+sNUDCOM+
				"|\nNOMCOC:|"+sNOMCOC+
				"|\nNODCCO:|"+sNODCCO+
				"|\nNOMPRC:|"+sNOMPRC+
				"|\nNUTPRC:|"+sNUTPRC+
				"|\nNOMADC:|"+sNOMADC+
				"|\nNUTADC:|"+sNUTADC+
				"|\nNODCAD:|"+sNODCAD+
				"|\nCuenta:|"+sCuenta+
				"|\nOBTEXC:|"+sOBTEXC+"|";
	}


}

//Autor: Francisco Valverde Manjón