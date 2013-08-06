package com.provisiones.types;

public class Provision 
{

	private String sNUPROF = "";
	private String sFEPFON = "";
	private String sFechaValidacion = "";
	private String sEstado = "";
	private String sValorTolal = "";
	private String sNumGastos = "";

	//Constructor de clase
	
	public Provision(String sNUPROF, String sFEPFON, String sFechaValidacion,
			String sEstado, String sValorTolal, String sNumGastos) {
		super();
		this.sNUPROF = sNUPROF;
		this.sFEPFON = sFEPFON;
		this.sFechaValidacion = sFechaValidacion;
		this.sEstado = sEstado;
		this.sValorTolal = sValorTolal;
		this.sNumGastos = sNumGastos;
	}
	
	//Métodos de acceso
	
	public String getsNUPROF() {
		return sNUPROF;
	}
	public void setsNUPROF(String sNUPROF) {
		this.sNUPROF = sNUPROF;
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
	public String getsEstado() {
		return sEstado;
	}
	public void setsEstado(String sEstado) {
		this.sEstado = sEstado;
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
	public void pintaProvision()
	{
		System.out.println("(PROVISION)");
		System.out.println("sNUPROF         :|"+sNUPROF         +"|");
		System.out.println("sFEPFON         :|"+sFEPFON         +"|");
		System.out.println("sFechaValidacion:|"+sFechaValidacion+"|");
		System.out.println("sEstado         :|"+sEstado         +"|");
		System.out.println("sValorTolal     :|"+sValorTolal     +"|");
		System.out.println("sNumGastos      :|"+sNumGastos      +"|");
	}
}
