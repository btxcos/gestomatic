package com.provisiones.types;

public class Provision 
{

	private String sNUPROF = "";
	private String sCOSPAT = "";
	private String sTAS = "";
	private String sValorTolal = "";
	private String sNumGastos = "";	
	private String sFEPFON = "";
	private String sFechaValidacion = "";
	private String sCodEstado = "";


	//Constructor de clase
	
	public Provision(String sNUPROF, String sCOSPAT, String sTAS,
			String sValorTolal, String sNumGastos, String sFEPFON,
			String sFechaValidacion, String sCodEstado) {
		super();
		this.sNUPROF = sNUPROF;
		this.sCOSPAT = sCOSPAT;
		this.sTAS = sTAS;
		this.sValorTolal = sValorTolal;
		this.sNumGastos = sNumGastos;
		this.sFEPFON = sFEPFON;
		this.sFechaValidacion = sFechaValidacion;
		this.sCodEstado = sCodEstado;
	}

	
	//Métodos de acceso

	public String getsNUPROF() {
		return sNUPROF;
	}


	public void setsNUPROF(String sNUPROF) {
		this.sNUPROF = sNUPROF;
	}


	public String getsCOSPAT() {
		return sCOSPAT;
	}


	public void setsCOSPAT(String sCOSPAT) {
		this.sCOSPAT = sCOSPAT;
	}


	public String getsTAS() {
		return sTAS;
	}


	public void setsTAS(String sTAS) {
		this.sTAS = sTAS;
	}


	public String getsValorTolal() {
		return sValorTolal;
	}


	public void setsValorTolal(String sValorTolal) {
		this.sValorTolal = sValorTolal;
	}


	public String getsNumGastos() {
		return sNumGastos;
	}


	public void setsNumGastos(String sNumGastos) {
		this.sNumGastos = sNumGastos;
	}


	public String getsFEPFON() {
		return sFEPFON;
	}


	public void setsFEPFON(String sFEPFON) {
		this.sFEPFON = sFEPFON;
	}


	public String getsFechaValidacion() {
		return sFechaValidacion;
	}


	public void setsFechaValidacion(String sFechaValidacion) {
		this.sFechaValidacion = sFechaValidacion;
	}


	public String getsCodEstado() {
		return sCodEstado;
	}


	public void setsCodEstado(String sCodEstado) {
		this.sCodEstado = sCodEstado;
	}

	public void pintaProvision()
	{
		System.out.println("(PROVISION)");
		System.out.println("sNUPROF         :|"+sNUPROF+"|");
		System.out.println("sCOSPAT         :|"+sCOSPAT+"|");
		System.out.println("sTAS            :|"+sTAS+"|");
		System.out.println("sFEPFON         :|"+sFEPFON+"|");
		System.out.println("sFechaValidacion:|"+sFechaValidacion+"|");
		System.out.println("sCodEstado      :|"+sCodEstado+"|");
		System.out.println("sValorTolal     :|"+sValorTolal+"|");
		System.out.println("sNumGastos      :|"+sNumGastos+"|");
	}




}
