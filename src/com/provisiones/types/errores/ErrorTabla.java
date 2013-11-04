package com.provisiones.types.errores;

public class ErrorTabla 
{

	private String sCodError = "";
	private String sDescripcion = "";
	
	//Constructor de clase

	public ErrorTabla(String sCodError, String sDescripcion) {
		super();
		this.sCodError = sCodError;
		this.sDescripcion = sDescripcion;
	}

	//Métodos de acceso

	public String getsCodError() {
		return sCodError;
	}

	public void setsCodError(String sCodError) {
		this.sCodError = sCodError;
	}

	public String getsDescripcion() {
		return sDescripcion;
	}

	public void setsDescripcion(String sDescripcion) {
		this.sDescripcion = sDescripcion;
	}
	
	
	
}
