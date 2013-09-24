package com.provisiones.types;

public class ErrorReferenciaTabla 
{
	private String COACES = "";
	private String NURCAT = "";
		
	private String MOVIMIENTO = "";
	private String ERRORES = "";

	//Constructor de clase
	
	public ErrorReferenciaTabla(String cOACES, String nURCAT,
			String mOVIMIENTO, String eRRORES) {
		super();
		COACES = cOACES;
		NURCAT = nURCAT;
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
