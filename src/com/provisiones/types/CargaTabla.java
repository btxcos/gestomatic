package com.provisiones.types;

public class CargaTabla 
{

	private String sArchivo = "";
	private String sResultado = "";

	//Constructor de clase

	public CargaTabla(String sArchivo, String sResultado) {
		super();
		this.sArchivo = sArchivo;
		this.sResultado = sResultado;
	}

	//Métodos de acceso

	public String getsArchivo() {
		return sArchivo;
	}
	public void setsArchivo(String sArchivo) {
		this.sArchivo = sArchivo;
	}
	public String getsResultado() {
		return sResultado;
	}
	public void setsResultado(String sResultado) {
		this.sResultado = sResultado;
	}
	
	
	
}
