package com.provisiones.types.tablas;

public class ResultadosTabla 
{

	private String sObjeto = "";
	private long liLinea = 0;
	private String sResultado = "";
	private String sDescripcion = "";

	//Constructor de clase

	public ResultadosTabla(String sObjeto, long liLinea, String sResultado,
			String sDescripcion) {
		super();
		this.sObjeto = sObjeto;
		this.liLinea = liLinea;
		this.sResultado = sResultado;
		this.sDescripcion = sDescripcion;
	}

	//Métodos de acceso
	
	public String getsObjeto() {
		return sObjeto;
	}

	public void setsObjeto(String sObjeto) {
		this.sObjeto = sObjeto;
	}

	public long getLiLinea() {
		return liLinea;
	}

	public void setLiLinea(long liLinea) {
		this.liLinea = liLinea;
	}

	public String getsResultado() {
		return sResultado;
	}

	public void setsResultado(String sResultado) {
		this.sResultado = sResultado;
	}

	public String getsDescripcion() {
		return sDescripcion;
	}

	public void setsDescripcion(String sDescripcion) {
		this.sDescripcion = sDescripcion;
	}

}

//Autor: Francisco Valverde Manjón