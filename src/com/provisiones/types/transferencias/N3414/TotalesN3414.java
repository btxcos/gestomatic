package com.provisiones.types.transferencias.N3414;

public class TotalesN3414 
{
	private String sCodRegistro = "";
	private String sCodOperacion = "";
	private String sTotalImportesGeneral = "";
	private String sNumeroRegistros = "";
	private String sTotalRegistros = "";

	//Constructor de clase
	
	public TotalesN3414(String sCodRegistro, String sCodOperacion,
			String sTotalImportesGeneral, String sNumeroRegistros,
			String sTotalRegistros) {
		super();
		this.sCodRegistro = sCodRegistro;
		this.sCodOperacion = sCodOperacion;
		this.sTotalImportesGeneral = sTotalImportesGeneral;
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

	public String getsTotalImportesGeneral() {
		return sTotalImportesGeneral;
	}

	public void setsTotalImportesGeneral(String sTotalImportesGeneral) {
		this.sTotalImportesGeneral = sTotalImportesGeneral;
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

//Autor: Francisco Valverde Manjón