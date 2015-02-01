package com.provisiones.types;

public class Fichero 
{
	private String sNombre = "";
	private int iTipoFichero = 0;
	
	//Constructor de clase

	public Fichero(String sNombre, int iTipoFichero) {
		super();
		this.sNombre = sNombre;
		this.iTipoFichero = iTipoFichero;
	}
	
	//Métodos de acceso

	public String getsNombre() {
		return sNombre;
	}

	public void setsNombre(String sNombre) {
		this.sNombre = sNombre;
	}

	public int getiTipoFichero() {
		return iTipoFichero;
	}

	public void setiTipoFichero(int iTipoFichero) {
		this.iTipoFichero = iTipoFichero;
	}

}

//Autor: Francisco Valverde Manjón