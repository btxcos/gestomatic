package com.provisiones.types.errores;

public class ErrorCuotaTabla 
{
	private String COACES = "";
	private String COCLDO = "";
	private String DCOCLDO = "";
	private String NUDCOM = "";
	private String COSBAC = "";
	private String DCOSBAC = "";
	
	private String MOVIMIENTO = "";
	private String ERRORES = "";
	
	//Constructor de clase

	public ErrorCuotaTabla(String cOACES, String cOCLDO, String dCOCLDO,
			String nUDCOM, String cOSBAC, String dCOSBAC, String mOVIMIENTO,
			String eRRORES) {
		super();
		COACES = cOACES;
		COCLDO = cOCLDO;
		DCOCLDO = dCOCLDO;
		NUDCOM = nUDCOM;
		COSBAC = cOSBAC;
		DCOSBAC = dCOSBAC;
		MOVIMIENTO = mOVIMIENTO;
		ERRORES = eRRORES;
	}
	
	//Métodos de acceso

	public String getCOACES() {
		return COACES;
	}

	public void setCOACES(String cOACES) {
		COACES = cOACES;
	}

	public String getCOCLDO() {
		return COCLDO;
	}

	public void setCOCLDO(String cOCLDO) {
		COCLDO = cOCLDO;
	}

	public String getDCOCLDO() {
		return DCOCLDO;
	}

	public void setDCOCLDO(String dCOCLDO) {
		DCOCLDO = dCOCLDO;
	}

	public String getNUDCOM() {
		return NUDCOM;
	}

	public void setNUDCOM(String nUDCOM) {
		NUDCOM = nUDCOM;
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

	public String getMOVIMIENTO() {
		return MOVIMIENTO;
	}

	public void setMOVIMIENTO(String mOVIMIENTO) {
		MOVIMIENTO = mOVIMIENTO;
	}

	public String getERRORES() {
		return ERRORES;
	}

	public void setERRORES(String eRRORES) {
		ERRORES = eRRORES;
	}
	
	
}

//Autor: Francisco Valverde Manjón