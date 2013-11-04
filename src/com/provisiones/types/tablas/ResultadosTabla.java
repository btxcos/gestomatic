package com.provisiones.types.tablas;

public class ResultadosTabla 
{

	private String sObjeto = "";
	private String sResultado = "";

	//Constructor de clase

	public ResultadosTabla(String sObjeto, String sResultado) {
		super();
		this.sObjeto = sObjeto;
		this.sResultado = sResultado;
	}

	//Métodos de acceso
	
	public String getsObjeto() {
		return sObjeto;
	}

	public void setsObjeto(String sObjeto) {
		this.sObjeto = sObjeto;
	}

	public String getsResultado() {
		return sResultado;
	}

	public void setsResultado(String sResultado) {
		this.sResultado = sResultado;
	}

}
