package com.provisiones.types;

public class Provision 
{

	private String sNUPROF = "";
	private String sCOSPAT = "";
	private String sTAS = "";
	private String sCOGRUG = "";
	private String sCOTPGA = "";
	private String sFEPFON = "";
	private String sNumGastos = "";	
	private String sValorTolal = "";
	private String sFechaEnvio = "";
	private String sGastosAutorizados = "";
	private String sValorAutorizado = "";
	private String sFechaAutorizado = "";
	private String sGastosPagados = "";
	private String sValorPagado = "";
	private String sFechaPagado = "";
	private String sRecargoTotal = "";
	private String sGastosAbonados = "";
	private String sAbonoTotal = "";
	private String sValorIngresado = "";
	private String sFechaIngresado = "";
	private String sCodEstado = "";

	//Constructor de clase

	public Provision(String sNUPROF, String sCOSPAT, String sTAS,
			String sCOGRUG, String sCOTPGA, String sFEPFON, String sNumGastos,
			String sValorTolal, String sFechaEnvio, String sGastosAutorizados,
			String sValorAutorizado, String sFechaAutorizado,
			String sGastosPagados, String sValorPagado, String sFechaPagado,
			String sRecargoTotal, String sGastosAbonados, String sAbonoTotal,
			String sValorIngresado, String sFechaIngresado, String sCodEstado) {
		super();
		this.sNUPROF = sNUPROF;
		this.sCOSPAT = sCOSPAT;
		this.sTAS = sTAS;
		this.sCOGRUG = sCOGRUG;
		this.sCOTPGA = sCOTPGA;
		this.sFEPFON = sFEPFON;
		this.sNumGastos = sNumGastos;
		this.sValorTolal = sValorTolal;
		this.sFechaEnvio = sFechaEnvio;
		this.sGastosAutorizados = sGastosAutorizados;
		this.sValorAutorizado = sValorAutorizado;
		this.sFechaAutorizado = sFechaAutorizado;
		this.sGastosPagados = sGastosPagados;
		this.sValorPagado = sValorPagado;
		this.sFechaPagado = sFechaPagado;
		this.sRecargoTotal = sRecargoTotal;
		this.sGastosAbonados = sGastosAbonados;
		this.sAbonoTotal = sAbonoTotal;
		this.sValorIngresado = sValorIngresado;
		this.sFechaIngresado = sFechaIngresado;
		this.sCodEstado = sCodEstado;
	}

	//Métodos de acceso
	
	public String getsNUPROF() {
		return sNUPROF;
	}

	public void setsNUPROF(String sNUPROF) {
		this.sNUPROF = sNUPROF;
	}

	public String getsCOSPAT() {
		return sCOSPAT;
	}

	public void setsCOSPAT(String sCOSPAT) {
		this.sCOSPAT = sCOSPAT;
	}

	public String getsTAS() {
		return sTAS;
	}

	public void setsTAS(String sTAS) {
		this.sTAS = sTAS;
	}

	public String getsCOGRUG() {
		return sCOGRUG;
	}

	public void setsCOGRUG(String sCOGRUG) {
		this.sCOGRUG = sCOGRUG;
	}

	public String getsCOTPGA() {
		return sCOTPGA;
	}

	public void setsCOTPGA(String sCOTPGA) {
		this.sCOTPGA = sCOTPGA;
	}

	public String getsFEPFON() {
		return sFEPFON;
	}

	public void setsFEPFON(String sFEPFON) {
		this.sFEPFON = sFEPFON;
	}

	public String getsNumGastos() {
		return sNumGastos;
	}

	public void setsNumGastos(String sNumGastos) {
		this.sNumGastos = sNumGastos;
	}

	public String getsValorTolal() {
		return sValorTolal;
	}

	public void setsValorTolal(String sValorTolal) {
		this.sValorTolal = sValorTolal;
	}

	public String getsFechaEnvio() {
		return sFechaEnvio;
	}

	public void setsFechaEnvio(String sFechaEnvio) {
		this.sFechaEnvio = sFechaEnvio;
	}

	public String getsGastosAutorizados() {
		return sGastosAutorizados;
	}

	public void setsGastosAutorizados(String sGastosAutorizados) {
		this.sGastosAutorizados = sGastosAutorizados;
	}

	public String getsValorAutorizado() {
		return sValorAutorizado;
	}

	public void setsValorAutorizado(String sValorAutorizado) {
		this.sValorAutorizado = sValorAutorizado;
	}

	public String getsFechaAutorizado() {
		return sFechaAutorizado;
	}

	public void setsFechaAutorizado(String sFechaAutorizado) {
		this.sFechaAutorizado = sFechaAutorizado;
	}

	public String getsGastosPagados() {
		return sGastosPagados;
	}

	public void setsGastosPagados(String sGastosPagados) {
		this.sGastosPagados = sGastosPagados;
	}

	public String getsValorPagado() {
		return sValorPagado;
	}

	public void setsValorPagado(String sValorPagado) {
		this.sValorPagado = sValorPagado;
	}

	public String getsFechaPagado() {
		return sFechaPagado;
	}

	public void setsFechaPagado(String sFechaPagado) {
		this.sFechaPagado = sFechaPagado;
	}

	public String getsRecargoTotal() {
		return sRecargoTotal;
	}

	public void setsRecargoTotal(String sRecargoTotal) {
		this.sRecargoTotal = sRecargoTotal;
	}

	public String getsGastosAbonados() {
		return sGastosAbonados;
	}

	public void setsGastosAbonados(String sGastosAbonados) {
		this.sGastosAbonados = sGastosAbonados;
	}

	public String getsAbonoTotal() {
		return sAbonoTotal;
	}

	public void setsAbonoTotal(String sAbonoTotal) {
		this.sAbonoTotal = sAbonoTotal;
	}

	public String getsValorIngresado() {
		return sValorIngresado;
	}

	public void setsValorIngresado(String sValorIngresado) {
		this.sValorIngresado = sValorIngresado;
	}

	public String getsFechaIngresado() {
		return sFechaIngresado;
	}

	public void setsFechaIngresado(String sFechaIngresado) {
		this.sFechaIngresado = sFechaIngresado;
	}

	public String getsCodEstado() {
		return sCodEstado;
	}

	public void setsCodEstado(String sCodEstado) {
		this.sCodEstado = sCodEstado;
	}

	//log
	public String logProvision()
	{
		return "(PROVISION)\nsNUPROF           :|"+sNUPROF+"|\nsCOSPAT           :|"+sCOSPAT+"|\nsTAS              :|"+sTAS+"|\nsCOGRUG           :|"+sCOGRUG+"|\nsCOTPGA           :|"+sCOTPGA+"|\nsFEPFON           :|"+sFEPFON+"|\nsNumGastos        :|"+sNumGastos+"|\nsValorTolal       :|"+sValorTolal+"|\nsFechaEnvio       :|"+sFechaEnvio+"|\nsGastosAutorizados:|"+sGastosAutorizados+"|\nsValorAutorizado  :|"+sValorAutorizado+"|\nsFechaAutorizado  :|"+sFechaAutorizado+"|\nsGastosPagados    :|"+sGastosPagados+"|\nsValorPagado      :|"+sValorPagado+"|\nsFechaPagado      :|"+sFechaPagado+"|\nsCodEstado        :|"+sCodEstado+"|";
	}

}
