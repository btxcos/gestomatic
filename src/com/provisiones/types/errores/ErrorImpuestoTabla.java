package com.provisiones.types.errores;

public class ErrorImpuestoTabla 
{
	private String COACES = "";
	
	private String NURCAT = "";
	private String COSBAC = "";
	private String DCOSBAC = "";

	private String MOVIMIENTO = "";
	private String ERRORES = "";

	//Constructor de clase

	public ErrorImpuestoTabla(String cOACES, String nURCAT, String cOSBAC,
			String dCOSBAC, String mOVIMIENTO, String eRRORES) {
		super();
		COACES = cOACES;
		NURCAT = nURCAT;
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
