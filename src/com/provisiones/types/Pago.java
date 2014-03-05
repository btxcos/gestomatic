package com.provisiones.types;

public class Pago 
{
	private String sCOACES = "";
	private String sGasto = "";
	private String sTipoPago = "";
	private String sCodOperacion = "";
	private String sFEPGPR = "";

	//Constructor de clase
	
	public Pago(String sCOACES, String sGasto, String sTipoPago,
			String sCodOperacion, String sFEPGPR) {
		super();
		this.sCOACES = sCOACES;
		this.sGasto = sGasto;
		this.sTipoPago = sTipoPago;
		this.sCodOperacion = sCodOperacion;
		this.sFEPGPR = sFEPGPR;
	}

	//M�todos de acceso

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

	//log
	public String logPago()
	{
		return String.format("(PAGO)\nsGasto :|"+sGasto+"|\nsTipoPago    :|"+sTipoPago+"|\nsCodOperacion  :|"+sCodOperacion+"|\nsFEPGPR:|"+sFEPGPR+"|");
	}


	
}
