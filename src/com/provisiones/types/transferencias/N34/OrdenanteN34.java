package com.provisiones.types.transferencias.N34;

public class OrdenanteN34 
{
	private String sCodOrdenante = "";
	private String sFechaEnvio = "";
	private String sFechaEmision = "";
	private String sNUCCEN = "";
	private String sNUCCOF = "";
	private String sNUCCNT = "";	
	private String sDetalleCargo = "";
	private String sNUCCDI = "";
	private String sNombre = "";
	private String sDomicilio = "";
	private String sPlaza = "";

	//Constructor de clase
	
	public OrdenanteN34(String sCodOrdenante, String sFechaEnvio,
			String sFechaEmision, String sNUCCEN, String sNUCCOF,
			String sNUCCNT, String sDetalleCargo, String sNUCCDI,
			String sNombre, String sDomicilio, String sPlaza) {
		super();
		this.sCodOrdenante = sCodOrdenante;
		this.sFechaEnvio = sFechaEnvio;
		this.sFechaEmision = sFechaEmision;
		this.sNUCCEN = sNUCCEN;
		this.sNUCCOF = sNUCCOF;
		this.sNUCCNT = sNUCCNT;
		this.sDetalleCargo = sDetalleCargo;
		this.sNUCCDI = sNUCCDI;
		this.sNombre = sNombre;
		this.sDomicilio = sDomicilio;
		this.sPlaza = sPlaza;
	}

	//Métodos de acceso

	public String getsCodOrdenante() {
		return sCodOrdenante;
	}

	public void setsCodOrdenante(String sCodOrdenante) {
		this.sCodOrdenante = sCodOrdenante;
	}

	public String getsFechaEnvio() {
		return sFechaEnvio;
	}

	public void setsFechaEnvio(String sFechaEnvio) {
		this.sFechaEnvio = sFechaEnvio;
	}

	public String getsFechaEmision() {
		return sFechaEmision;
	}

	public void setsFechaEmision(String sFechaEmision) {
		this.sFechaEmision = sFechaEmision;
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

	public String getsNUCCNT() {
		return sNUCCNT;
	}

	public void setsNUCCNT(String sNUCCNT) {
		this.sNUCCNT = sNUCCNT;
	}

	public String getsDetalleCargo() {
		return sDetalleCargo;
	}

	public void setsDetalleCargo(String sDetalleCargo) {
		this.sDetalleCargo = sDetalleCargo;
	}

	public String getsNUCCDI() {
		return sNUCCDI;
	}

	public void setsNUCCDI(String sNUCCDI) {
		this.sNUCCDI = sNUCCDI;
	}

	public String getsNombre() {
		return sNombre;
	}

	public void setsNombre(String sNombre) {
		this.sNombre = sNombre;
	}

	public String getsDomicilio() {
		return sDomicilio;
	}

	public void setsDomicilio(String sDomicilio) {
		this.sDomicilio = sDomicilio;
	}

	public String getsPlaza() {
		return sPlaza;
	}

	public void setsPlaza(String sPlaza) {
		this.sPlaza = sPlaza;
	}
}
