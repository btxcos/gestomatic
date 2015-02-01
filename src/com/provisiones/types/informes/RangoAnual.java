package com.provisiones.types.informes;

public class RangoAnual 
{
	
	private String sMes = "";
	private String sValor = "";

	//Constructor de clase

	public RangoAnual(String sMes, String sValor) {
		super();
		this.sMes = sMes;
		this.sValor = sValor;
	}
	
	//Métodos de acceso

	public String getsMes() {
		return sMes;
	}

	public void setsMes(String sMes) {
		this.sMes = sMes;
	}

	public String getsValor() {
		return sValor;
	}

	public void setsValor(String sValor) {
		this.sValor = sValor;
	}
}

//Autor: Francisco Valverde Manjón