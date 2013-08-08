package com.provisiones.types;

public class ReferenciaTabla 
{
	private String NURCAT = "";
	private String TIRCAT = "";
	private String ENEMIS = "";
	
	//Constructor de clase

	public ReferenciaTabla(String nURCAT, String tIRCAT, String eNEMIS) {
		super();
		NURCAT = nURCAT;
		TIRCAT = tIRCAT;
		ENEMIS = eNEMIS;
	}

	//Métodos de acceso
	
	public String getNURCAT() {
		return NURCAT;
	}

	public void setNURCAT(String nURCAT) {
		NURCAT = nURCAT;
	}

	public String getTIRCAT() {
		return TIRCAT;
	}

	public void setTIRCAT(String tIRCAT) {
		TIRCAT = tIRCAT;
	}

	public String getENEMIS() {
		return ENEMIS;
	}

	public void setENEMIS(String eNEMIS) {
		ENEMIS = eNEMIS;
	}
	
	
}
