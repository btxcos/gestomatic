package com.provisiones.types.transferencias.N3414;

public class OrdenanteN3414 
{
	private String sCodRegistro = "";
	private String sCodOperacion = "";
	private String sVersionCuaderno = "";
	private String sNumeroDato = "";
	private String sIdentificacionOrdenante = "";
	private String sIdentificacionOrdenanteSufijo = "";
	private String sFechaCreacionFichero = "";
	private String sFechaEjecucionOrdenes = "";
	private String sIdentificadorCuentaOrdenante = "";
	private String sCuentaOrdenante = "";
	private String sDetallesCargos = "";
	private String sNombreOrdenante = "";
	private String sDireccionOrdenante1 = "";
	private String sDireccionOrdenante2 = "";
	private String sDireccionOrdenante3 = "";
	private String sPaisOrdenante = "";

	//Constructor de clase
	
	public OrdenanteN3414(String sCodRegistro, String sCodOperacion,
			String sVersionCuaderno, String sNumeroDato,
			String sIdentificacionOrdenante,
			String sIdentificacionOrdenanteSufijo,
			String sFechaCreacionFichero, String sFechaEjecucionOrdenes,
			String sIdentificadorCuentaOrdenante, String sCuentaOrdenante,
			String sDetallesCargos, String sNombreOrdenante,
			String sDireccionOrdenante1, String sDireccionOrdenante2,
			String sDireccionOrdenante3, String sPaisOrdenante) {
		super();
		this.sCodRegistro = sCodRegistro;
		this.sCodOperacion = sCodOperacion;
		this.sVersionCuaderno = sVersionCuaderno;
		this.sNumeroDato = sNumeroDato;
		this.sIdentificacionOrdenante = sIdentificacionOrdenante;
		this.sIdentificacionOrdenanteSufijo = sIdentificacionOrdenanteSufijo;
		this.sFechaCreacionFichero = sFechaCreacionFichero;
		this.sFechaEjecucionOrdenes = sFechaEjecucionOrdenes;
		this.sIdentificadorCuentaOrdenante = sIdentificadorCuentaOrdenante;
		this.sCuentaOrdenante = sCuentaOrdenante;
		this.sDetallesCargos = sDetallesCargos;
		this.sNombreOrdenante = sNombreOrdenante;
		this.sDireccionOrdenante1 = sDireccionOrdenante1;
		this.sDireccionOrdenante2 = sDireccionOrdenante2;
		this.sDireccionOrdenante3 = sDireccionOrdenante3;
		this.sPaisOrdenante = sPaisOrdenante;
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


	public String getsVersionCuaderno() {
		return sVersionCuaderno;
	}


	public void setsVersionCuaderno(String sVersionCuaderno) {
		this.sVersionCuaderno = sVersionCuaderno;
	}


	public String getsNumeroDato() {
		return sNumeroDato;
	}


	public void setsNumeroDato(String sNumeroDato) {
		this.sNumeroDato = sNumeroDato;
	}


	public String getsIdentificacionOrdenante() {
		return sIdentificacionOrdenante;
	}


	public void setsIdentificacionOrdenante(String sIdentificacionOrdenante) {
		this.sIdentificacionOrdenante = sIdentificacionOrdenante;
	}


	public String getsIdentificacionOrdenanteSufijo() {
		return sIdentificacionOrdenanteSufijo;
	}


	public void setsIdentificacionOrdenanteSufijo(
			String sIdentificacionOrdenanteSufijo) {
		this.sIdentificacionOrdenanteSufijo = sIdentificacionOrdenanteSufijo;
	}


	public String getsFechaCreacionFichero() {
		return sFechaCreacionFichero;
	}


	public void setsFechaCreacionFichero(String sFechaCreacionFichero) {
		this.sFechaCreacionFichero = sFechaCreacionFichero;
	}


	public String getsFechaEjecucionOrdenes() {
		return sFechaEjecucionOrdenes;
	}


	public void setsFechaEjecucionOrdenes(String sFechaEjecucionOrdenes) {
		this.sFechaEjecucionOrdenes = sFechaEjecucionOrdenes;
	}


	public String getsIdentificadorCuentaOrdenante() {
		return sIdentificadorCuentaOrdenante;
	}


	public void setsIdentificadorCuentaOrdenante(
			String sIdentificadorCuentaOrdenante) {
		this.sIdentificadorCuentaOrdenante = sIdentificadorCuentaOrdenante;
	}


	public String getsCuentaOrdenante() {
		return sCuentaOrdenante;
	}


	public void setsCuentaOrdenante(String sCuentaOrdenante) {
		this.sCuentaOrdenante = sCuentaOrdenante;
	}


	public String getsDetallesCargos() {
		return sDetallesCargos;
	}


	public void setsDetallesCargos(String sDetallesCargos) {
		this.sDetallesCargos = sDetallesCargos;
	}


	public String getsNombreOrdenante() {
		return sNombreOrdenante;
	}


	public void setsNombreOrdenante(String sNombreOrdenante) {
		this.sNombreOrdenante = sNombreOrdenante;
	}


	public String getsDireccionOrdenante1() {
		return sDireccionOrdenante1;
	}


	public void setsDireccionOrdenante1(String sDireccionOrdenante1) {
		this.sDireccionOrdenante1 = sDireccionOrdenante1;
	}


	public String getsDireccionOrdenante2() {
		return sDireccionOrdenante2;
	}


	public void setsDireccionOrdenante2(String sDireccionOrdenante2) {
		this.sDireccionOrdenante2 = sDireccionOrdenante2;
	}


	public String getsDireccionOrdenante3() {
		return sDireccionOrdenante3;
	}


	public void setsDireccionOrdenante3(String sDireccionOrdenante3) {
		this.sDireccionOrdenante3 = sDireccionOrdenante3;
	}


	public String getsPaisOrdenante() {
		return sPaisOrdenante;
	}


	public void setsPaisOrdenante(String sPaisOrdenante) {
		this.sPaisOrdenante = sPaisOrdenante;
	}

}
