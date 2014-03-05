package com.provisiones.types.transferencias.N34;

public class ResumenN34 
{
	private String sCodOrdenante = "";
	private String sSumaImportes = "";
	private String sNumeroTransferencias = "";
	private String sNumeroRegistros = "";

	//Constructor de clase
	
	public ResumenN34(String sCodOrdenante, String sSumaImportes,
			String sNumeroTransferencias, String sNumeroRegistros) {
		super();
		this.sCodOrdenante = sCodOrdenante;
		this.sSumaImportes = sSumaImportes;
		this.sNumeroTransferencias = sNumeroTransferencias;
		this.sNumeroRegistros = sNumeroRegistros;
	}

	//Métodos de acceso

	public String getsCodOrdenante() {
		return sCodOrdenante;
	}

	public void setsCodOrdenante(String sCodOrdenante) {
		this.sCodOrdenante = sCodOrdenante;
	}

	public String getsSumaImportes() {
		return sSumaImportes;
	}

	public void setsSumaImportes(String sSumaImportes) {
		this.sSumaImportes = sSumaImportes;
	}

	public String getsNumeroTransferencias() {
		return sNumeroTransferencias;
	}

	public void setsNumeroTransferencias(String sNumeroTransferencias) {
		this.sNumeroTransferencias = sNumeroTransferencias;
	}

	public String getsNumeroRegistros() {
		return sNumeroRegistros;
	}

	public void setsNumeroRegistros(String sNumeroRegistros) {
		this.sNumeroRegistros = sNumeroRegistros;
	}
}
