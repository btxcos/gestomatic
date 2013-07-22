package com.provisiones.types;

public class Activo
{

	private String sCOACES = "";
	private String sCodDatos = "";
	private String sCodComunidad = "";
	private String sCodReferencia = "";

	//Constructor de clase

	public Activo(String sCOACES, String sCodDatos, String sCodComunidad,
			String sCodReferencia) {
		super();
		this.sCOACES = sCOACES;
		this.sCodDatos = sCodDatos;
		this.sCodComunidad = sCodComunidad;
		this.sCodReferencia = sCodReferencia;
	}

	//Métodos de acceso

	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
	}

	public String getsCodDatos() {
		return sCodDatos;
	}

	public void setsCodDatos(String sCodDatos) {
		this.sCodDatos = sCodDatos;
	}

	public String getsCodComunidad() {
		return sCodComunidad;
	}

	public void setsCodComunidad(String sCodComunidad) {
		this.sCodComunidad = sCodComunidad;
	}

	public String getsCodReferencia() {
		return sCodReferencia;
	}

	public void setsCodReferencia(String sCodReferencia) {
		this.sCodReferencia = sCodReferencia;
	}
	
}
