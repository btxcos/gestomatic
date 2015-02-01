package com.provisiones.types;

public class Historial 
{
	private String sPagina = "";
	private String sGestor = "";

	//Constructor de clase
	
	public Historial(String sPagina, String sGestor) {
		super();
		this.sPagina = sPagina;
		this.sGestor = sGestor;
	}
	
	//Métodos de acceso
	
	public String getsPagina() {
		return sPagina;
	}
	public void setsPagina(String sPagina) {
		this.sPagina = sPagina;
	}
	public String getsGestor() {
		return sGestor;
	}
	public void setsGestor(String sGestor) {
		this.sGestor = sGestor;
	}
	
	
	
}

//Autor: Francisco Valverde Manjón