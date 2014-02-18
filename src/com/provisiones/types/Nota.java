package com.provisiones.types;

public class Nota 
{

	private boolean bInvalida = false;
	private String sContenido = "";

	//Constructor de clase
	
	public Nota(boolean bInvalida, String sContenido) {
		super();
		this.bInvalida = bInvalida;
		this.sContenido = sContenido;
	}
	
	//Métodos de acceso

	public boolean isbInvalida() {
		return bInvalida;
	}

	public void setbInvalida(boolean bInvalida) {
		this.bInvalida = bInvalida;
	}

	public String getsContenido() {
		return sContenido;
	}

	public void setsContenido(String sContenido) {
		this.sContenido = sContenido;
	}

}
