package com.provisiones.types.transferencias.N3414;

public class TransferenciaN3414 
{
	private String sCuentaBeneficiario = "";
	private String sImporteTransferencia = "";
	private String sBICBeneficiario = "";
	private String sNombreBeneficiario = "";
	private String sDireccionBeneficiario1 = "";
	private String sDireccionBeneficiario2 = "";
	private String sDireccionBeneficiario3 = "";
	private String sPaisBeneficiario = "";
	private String sConcepto = "";
	
	//Constructor de clase

	public TransferenciaN3414(String sCuentaBeneficiario,
			String sImporteTransferencia, String sBICBeneficiario,
			String sNombreBeneficiario, String sDireccionBeneficiario1,
			String sDireccionBeneficiario2, String sDireccionBeneficiario3,
			String sPaisBeneficiario, String sConcepto) {
		super();
		this.sCuentaBeneficiario = sCuentaBeneficiario;
		this.sImporteTransferencia = sImporteTransferencia;
		this.sBICBeneficiario = sBICBeneficiario;
		this.sNombreBeneficiario = sNombreBeneficiario;
		this.sDireccionBeneficiario1 = sDireccionBeneficiario1;
		this.sDireccionBeneficiario2 = sDireccionBeneficiario2;
		this.sDireccionBeneficiario3 = sDireccionBeneficiario3;
		this.sPaisBeneficiario = sPaisBeneficiario;
		this.sConcepto = sConcepto;
	}
	
	//Métodos de acceso

	public String getsCuentaBeneficiario() {
		return sCuentaBeneficiario;
	}

	public void setsCuentaBeneficiario(String sCuentaBeneficiario) {
		this.sCuentaBeneficiario = sCuentaBeneficiario;
	}

	public String getsImporteTransferencia() {
		return sImporteTransferencia;
	}

	public void setsImporteTransferencia(String sImporteTransferencia) {
		this.sImporteTransferencia = sImporteTransferencia;
	}

	public String getsBICBeneficiario() {
		return sBICBeneficiario;
	}

	public void setsBICBeneficiario(String sBICBeneficiario) {
		this.sBICBeneficiario = sBICBeneficiario;
	}

	public String getsNombreBeneficiario() {
		return sNombreBeneficiario;
	}

	public void setsNombreBeneficiario(String sNombreBeneficiario) {
		this.sNombreBeneficiario = sNombreBeneficiario;
	}

	public String getsDireccionBeneficiario1() {
		return sDireccionBeneficiario1;
	}

	public void setsDireccionBeneficiario1(String sDireccionBeneficiario1) {
		this.sDireccionBeneficiario1 = sDireccionBeneficiario1;
	}

	public String getsDireccionBeneficiario2() {
		return sDireccionBeneficiario2;
	}

	public void setsDireccionBeneficiario2(String sDireccionBeneficiario2) {
		this.sDireccionBeneficiario2 = sDireccionBeneficiario2;
	}

	public String getsDireccionBeneficiario3() {
		return sDireccionBeneficiario3;
	}

	public void setsDireccionBeneficiario3(String sDireccionBeneficiario3) {
		this.sDireccionBeneficiario3 = sDireccionBeneficiario3;
	}

	public String getsPaisBeneficiario() {
		return sPaisBeneficiario;
	}

	public void setsPaisBeneficiario(String sPaisBeneficiario) {
		this.sPaisBeneficiario = sPaisBeneficiario;
	}

	public String getsConcepto() {
		return sConcepto;
	}

	public void setsConcepto(String sConcepto) {
		this.sConcepto = sConcepto;
	}
}

//Autor: Francisco Valverde Manjón