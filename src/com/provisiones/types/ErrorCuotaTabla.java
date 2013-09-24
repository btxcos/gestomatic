package com.provisiones.types;

public class ErrorCuotaTabla 
{
	private String COACES = "";
	private String COCLDO = "";
	private String NUDCOM = "";
	private String COSBAC = "";
	
	private String MOVIMIENTO = "";
	private String ERRORES = "";
	
	//Constructor de clase

	public ErrorCuotaTabla(String cOACES, String cOCLDO, String nUDCOM,
			String cOSBAC, String mOVIMIENTO, String eRRORES) {
		super();
		COACES = cOACES;
		COCLDO = cOCLDO;
		NUDCOM = nUDCOM;
		COSBAC = cOSBAC;
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
