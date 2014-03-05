package com.provisiones.types.transferencias.N34;

public class TransferenciaN34 
{
	private String sCodOrdenante = "";
	private String sReferenciaBeneficiario = "";
	private String sImporte = "";
	private String sNUCCEN = "";
	private String sNUCCOF = "";
	private String sNUCCDI = "";
	private String sNUCCNT = "";
	private String sNombreBeneficiario = "";
	private String sDomicilio1Beneficiario = "";
	private String sDomicilio2Beneficiario = "";
	private String sPlazaBeneficiario = "";
	private String sProvinciaBeneficiario = "";
	private String sConcepto1Transferencia = "";
	private String sConcepto2Transferencia = "";
	
	//Constructor de clase

	public TransferenciaN34(String sCodOrdenante,
			String sReferenciaBeneficiario, String sImporte, String sNUCCEN,
			String sNUCCOF, String sNUCCDI, String sNUCCNT,
			String sNombreBeneficiario, String sDomicilio1Beneficiario,
			String sDomicilio2Beneficiario, String sPlazaBeneficiario,
			String sProvinciaBeneficiario, String sConcepto1Transferencia,
			String sConcepto2Transferencia) {
		super();
		this.sCodOrdenante = sCodOrdenante;
		this.sReferenciaBeneficiario = sReferenciaBeneficiario;
		this.sImporte = sImporte;
		this.sNUCCEN = sNUCCEN;
		this.sNUCCOF = sNUCCOF;
		this.sNUCCDI = sNUCCDI;
		this.sNUCCNT = sNUCCNT;
		this.sNombreBeneficiario = sNombreBeneficiario;
		this.sDomicilio1Beneficiario = sDomicilio1Beneficiario;
		this.sDomicilio2Beneficiario = sDomicilio2Beneficiario;
		this.sPlazaBeneficiario = sPlazaBeneficiario;
		this.sProvinciaBeneficiario = sProvinciaBeneficiario;
		this.sConcepto1Transferencia = sConcepto1Transferencia;
		this.sConcepto2Transferencia = sConcepto2Transferencia;
	}
	
	//Métodos de acceso

	public String getsCodOrdenante() {
		return sCodOrdenante;
	}

	public void setsCodOrdenante(String sCodOrdenante) {
		this.sCodOrdenante = sCodOrdenante;
	}

	public String getsReferenciaBeneficiario() {
		return sReferenciaBeneficiario;
	}

	public void setsReferenciaBeneficiario(String sReferenciaBeneficiario) {
		this.sReferenciaBeneficiario = sReferenciaBeneficiario;
	}

	public String getsImporte() {
		return sImporte;
	}

	public void setsImporte(String sImporte) {
		this.sImporte = sImporte;
	}

	public String getsNUCCEN() {
		return sNUCCEN;
	}

	public void setsNUCCEN(String sNUCCEN) {
		this.sNUCCEN = sNUCCEN;
	}

	public String getsNUCCOF() {
		return sNUCCOF;
	}

	public void setsNUCCOF(String sNUCCOF) {
		this.sNUCCOF = sNUCCOF;
	}

	public String getsNUCCDI() {
		return sNUCCDI;
	}

	public void setsNUCCDI(String sNUCCDI) {
		this.sNUCCDI = sNUCCDI;
	}

	public String getsNUCCNT() {
		return sNUCCNT;
	}

	public void setsNUCCNT(String sNUCCNT) {
		this.sNUCCNT = sNUCCNT;
	}

	public String getsNombreBeneficiario() {
		return sNombreBeneficiario;
	}

	public void setsNombreBeneficiario(String sNombreBeneficiario) {
		this.sNombreBeneficiario = sNombreBeneficiario;
	}

	public String getsDomicilio1Beneficiario() {
		return sDomicilio1Beneficiario;
	}

	public void setsDomicilio1Beneficiario(String sDomicilio1Beneficiario) {
		this.sDomicilio1Beneficiario = sDomicilio1Beneficiario;
	}

	public String getsDomicilio2Beneficiario() {
		return sDomicilio2Beneficiario;
	}

	public void setsDomicilio2Beneficiario(String sDomicilio2Beneficiario) {
		this.sDomicilio2Beneficiario = sDomicilio2Beneficiario;
	}

	public String getsPlazaBeneficiario() {
		return sPlazaBeneficiario;
	}

	public void setsPlazaBeneficiario(String sPlazaBeneficiario) {
		this.sPlazaBeneficiario = sPlazaBeneficiario;
	}

	public String getsProvinciaBeneficiario() {
		return sProvinciaBeneficiario;
	}

	public void setsProvinciaBeneficiario(String sProvinciaBeneficiario) {
		this.sProvinciaBeneficiario = sProvinciaBeneficiario;
	}

	public String getsConcepto1Transferencia() {
		return sConcepto1Transferencia;
	}

	public void setsConcepto1Transferencia(String sConcepto1Transferencia) {
		this.sConcepto1Transferencia = sConcepto1Transferencia;
	}

	public String getsConcepto2Transferencia() {
		return sConcepto2Transferencia;
	}

	public void setsConcepto2Transferencia(String sConcepto2Transferencia) {
		this.sConcepto2Transferencia = sConcepto2Transferencia;
	}

}
