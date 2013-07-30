package com.provisiones.types;

public class ReferenciaCatastral 
{

	private String NURCAT = "";
	private String TIRCAT = "";
	private String ENEMIS = "";
	private String COTEXA = "";
	private String OBTEXC = "";

	//Constructor de clase

	public ReferenciaCatastral(String nURCAT, String tIRCAT, String eNEMIS,
			String cOTEXA, String oBTEXC) {
		super();
		NURCAT = nURCAT;
		TIRCAT = tIRCAT;
		ENEMIS = eNEMIS;
		COTEXA = cOTEXA;
		OBTEXC = oBTEXC;
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

	public String getCOTEXA() {
		return COTEXA;
	}

	public void setCOTEXA(String cOTEXA) {
		COTEXA = cOTEXA;
	}

	public String getOBTEXC() {
		return OBTEXC;
	}

	public void setOBTEXC(String oBTEXC) {
		OBTEXC = oBTEXC;
	}

	public void pintaReferenciaCatastral()
	{
		System.out.println("NURCAT:|"+NURCAT+"|");
		System.out.println("TIRCAT:|"+TIRCAT+"|");
		System.out.println("ENEMIS:|"+ENEMIS+"|");
		System.out.println("COTEXA:|"+COTEXA+"|");
		System.out.println("OBTEXC:|"+OBTEXC+"|");
	}
}
