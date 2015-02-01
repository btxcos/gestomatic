package com.provisiones.types.tablas;

public class ReferenciaTabla 
{
	//Identificador
	private String sReferenciaID = "";
	
	private String NURCAT = "";
	private String TIRCAT = "";
	private String ENEMIS = "";
	private String OBTEXC = "";
	private String IMVSUE = "";
	private String IMCATA = "";
	private String FERECA = "";
	private String ESTADO = "";
	
	//Constructor de clase

	public ReferenciaTabla(String sReferenciaID, String nURCAT, String tIRCAT,
			String eNEMIS, String oBTEXC, String iMVSUE, String iMCATA,
			String fERECA, String eSTADO) {
		super();
		this.sReferenciaID = sReferenciaID;
		NURCAT = nURCAT;
		TIRCAT = tIRCAT;
		ENEMIS = eNEMIS;
		OBTEXC = oBTEXC;
		IMVSUE = iMVSUE;
		IMCATA = iMCATA;
		FERECA = fERECA;
		ESTADO = eSTADO;
	}

	//Métodos de acceso

	public String getsReferenciaID() {
		return sReferenciaID;
	}

	public void setsReferenciaID(String sReferenciaID) {
		this.sReferenciaID = sReferenciaID;
	}

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


	public String getOBTEXC() {
		return OBTEXC;
	}


	public void setOBTEXC(String oBTEXC) {
		OBTEXC = oBTEXC;
	}


	public String getIMVSUE() {
		return IMVSUE;
	}


	public void setIMVSUE(String iMVSUE) {
		IMVSUE = iMVSUE;
	}


	public String getIMCATA() {
		return IMCATA;
	}


	public void setIMCATA(String iMCATA) {
		IMCATA = iMCATA;
	}


	public String getFERECA() {
		return FERECA;
	}


	public void setFERECA(String fERECA) {
		FERECA = fERECA;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}
	
}

//Autor: Francisco Valverde Manjón