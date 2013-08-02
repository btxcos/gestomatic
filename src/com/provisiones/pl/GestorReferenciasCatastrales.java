package com.provisiones.pl;

import java.io.Serializable;

public class GestorReferenciasCatastrales implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2458459163051982275L;
	
	private String sNURCAT = "";
	private String sTIRCAT = "";
	private String sENEMIS = "";
	private String sCOTEXA = "";
	private String sOBTEXC = "";

	public String getsNURCAT() {
		return sNURCAT;
	}
	public void setsNURCAT(String sNURCAT) {
		this.sNURCAT = sNURCAT;
	}
	public String getsTIRCAT() {
		return sTIRCAT;
	}
	public void setsTIRCAT(String sTIRCAT) {
		this.sTIRCAT = sTIRCAT;
	}
	public String getsENEMIS() {
		return sENEMIS;
	}
	public void setsENEMIS(String sENEMIS) {
		this.sENEMIS = sENEMIS;
	}
	public String getsCOTEXA() {
		return sCOTEXA;
	}
	public void setsCOTEXA(String sCOTEXA) {
		this.sCOTEXA = sCOTEXA;
	}
	public String getsOBTEXC() {
		return sOBTEXC;
	}
	public void setsOBTEXC(String sOBTEXC) {
		this.sOBTEXC = sOBTEXC;
	}
	
	
	

}
