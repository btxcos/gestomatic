package com.provisiones.types;

public class Provision 
{

	private String sNUPROF = "";
	private String sValorTolal = "";
	private String sNumGastos = "";	
	private String sFEPFON = "";
	private String sFechaValidacion = "";
	private String sValidado = "";


	//Constructor de clase
	
	public Provision(String sNUPROF, String sValorTolal, String sNumGastos,
			String sFEPFON, String sFechaValidacion, String sValidado) {
		super();
		this.sNUPROF = sNUPROF;
		this.sValorTolal = sValorTolal;
		this.sNumGastos = sNumGastos;
		this.sFEPFON = sFEPFON;
		this.sFechaValidacion = sFechaValidacion;
		this.sValidado = sValidado;
	}
	
	//Métodos de acceso

	public String getsNUPROF() {
		return sNUPROF;
	}

	public void setsNUPROF(String sNUPROF) {
		this.sNUPROF = sNUPROF;
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

	public String getsValidado() {
		return sValidado;
	}

	public void setsValidado(String sValidado) {
		this.sValidado = sValidado;
	}

	public void pintaProvision()
	{
		System.out.println("(PROVISION)");
		System.out.println("sNUPROF         :|"+sNUPROF         +"|");
		System.out.println("sFEPFON         :|"+sFEPFON         +"|");
		System.out.println("sFechaValidacion:|"+sFechaValidacion+"|");
		System.out.println("sValidado       :|"+sValidado       +"|");
		System.out.println("sValorTolal     :|"+sValorTolal     +"|");
		System.out.println("sNumGastos      :|"+sNumGastos      +"|");
	}

}
