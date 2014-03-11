package com.provisiones.types;

public class ResultadoEnvio 
{
	private String sFichero = "";
	private long liEntradas = 0;

	//Constructor de clase
	
	public ResultadoEnvio(String sFichero, long liEntradas) {
		super();
		this.sFichero = sFichero;
		this.liEntradas = liEntradas;
	}
	
	//Métodos de acceso

	public String getsFichero() {
		return sFichero;
	}

	public void setsFichero(String sFichero) {
		this.sFichero = sFichero;
	}

	public long getLiEntradas() {
		return liEntradas;
	}

	public void setLiEntradas(long liEntradas) {
		this.liEntradas = liEntradas;
	}
}
