package com.provisiones.types.transferencias.N3414;

public class ResumenN3414 
{
	private String sCodRegistro = "";
	private String sCodOperacion = "";
	private String sTotalImportes = "";
	private String sNumeroRegistros = "";
	private String sTotalRegistros = "";

	//Constructor de clase
	
	public ResumenN3414(String sCodRegistro, String sCodOperacion,
			String sTotalImportes, String sNumeroRegistros,
			String sTotalRegistros) {
		super();
		this.sCodRegistro = sCodRegistro;
		this.sCodOperacion = sCodOperacion;
		this.sTotalImportes = sTotalImportes;
		this.sNumeroRegistros = sNumeroRegistros;
		this.sTotalRegistros = sTotalRegistros;
	}
	
	//Métodos de acceso

	public String getsCodRegistro() {
		return sCodRegistro;
	}

	public void setsCodRegistro(String sCodRegistro) {
		this.sCodRegistro = sCodRegistro;
	}

	public String getsCodOperacion() {
		return sCodOperacion;
	}

	public void setsCodOperacion(String sCodOperacion) {
		this.sCodOperacion = sCodOperacion;
	}

	public String getsTotalImportes() {
		return sTotalImportes;
	}

	public void setsTotalImportes(String sTotalImportes) {
		this.sTotalImportes = sTotalImportes;
	}

	public String getsNumeroRegistros() {
		return sNumeroRegistros;
	}

	public void setsNumeroRegistros(String sNumeroRegistros) {
		this.sNumeroRegistros = sNumeroRegistros;
	}

	public String getsTotalRegistros() {
		return sTotalRegistros;
	}

	public void setsTotalRegistros(String sTotalRegistros) {
		this.sTotalRegistros = sTotalRegistros;
	}
}
