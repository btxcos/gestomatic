package com.provisiones.types;

public class Pago 
{
	private String sCOACES = "";
	private String sGasto = "";
	private String sTipoPago = "";
	private String sCodOperacion = "";
	private String sFEPGPR = "";
	private String sRecargoAdicional = "";

	//Constructor de clase
	
	public Pago(String sCOACES, String sGasto, String sTipoPago,
			String sCodOperacion, String sFEPGPR, String sRecargoAdicional) {
		super();
		this.sCOACES = sCOACES;
		this.sGasto = sGasto;
		this.sTipoPago = sTipoPago;
		this.sCodOperacion = sCodOperacion;
		this.sFEPGPR = sFEPGPR;
		this.sRecargoAdicional = sRecargoAdicional;
	}

	//Métodos de acceso

	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
	}

	public String getsGasto() {
		return sGasto;
	}

	public void setsGasto(String sGasto) {
		this.sGasto = sGasto;
	}

	public String getsTipoPago() {
		return sTipoPago;
	}

	public void setsTipoPago(String sTipoPago) {
		this.sTipoPago = sTipoPago;
	}

	public String getsCodOperacion() {
		return sCodOperacion;
	}

	public void setsCodOperacion(String sCodOperacion) {
		this.sCodOperacion = sCodOperacion;
	}

	public String getsFEPGPR() {
		return sFEPGPR;
	}

	public void setsFEPGPR(String sFEPGPR) {
		this.sFEPGPR = sFEPGPR;
	}

	public String getsRecargoAdicional() {
		return sRecargoAdicional;
	}

	public void setsRecargoAdicional(String sRecargoAdicional) {
		this.sRecargoAdicional = sRecargoAdicional;
	}

	//log
	public String logPago()
	{
		return String.format("(PAGO)\nsGasto :|"+sGasto+"|\nsTipoPago    :|"+sTipoPago+"|\nsCodOperacion  :|"+sCodOperacion+"|\nsFEPGPR:|"+sFEPGPR+"|");
	}


	
}
