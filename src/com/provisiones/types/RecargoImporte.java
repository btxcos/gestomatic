package com.provisiones.types;

public class RecargoImporte 
{
	private String sTipoRecargo = "";
	private String sValorRecargo = "";

	//Constructor de clase
	
	public RecargoImporte(String sTipoRecargo, String sValorRecargo) {
		super();
		this.sTipoRecargo = sTipoRecargo;
		this.sValorRecargo = sValorRecargo;
	}
	
	//Métodos de acceso

	public String getsTipoRecargo() {
		return sTipoRecargo;
	}

	public void setsTipoRecargo(String sTipoRecargo) {
		this.sTipoRecargo = sTipoRecargo;
	}

	public String getsValorRecargo() {
		return sValorRecargo;
	}

	public void setsValorRecargo(String sValorRecargo) {
		this.sValorRecargo = sValorRecargo;
	}

}

//Autor: Francisco Valverde Manjón