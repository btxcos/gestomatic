package com.provisiones.types.transferencias.N3414;

public class CabeceraTransferenciasN3414 
{
	private String sCodRegistro = "";
	private String sCodOperacion = "";
	private String sVersionCuaderno = "";
	private String sIdentificacionOrdenante = "";
	private String sIdentificacionOrdenanteSufijo = "";

	//Constructor de clase
	
	public CabeceraTransferenciasN3414(String sCodRegistro,
			String sCodOperacion, String sVersionCuaderno,
			String sIdentificacionOrdenante,
			String sIdentificacionOrdenanteSufijo) {
		super();
		this.sCodRegistro = sCodRegistro;
		this.sCodOperacion = sCodOperacion;
		this.sVersionCuaderno = sVersionCuaderno;
		this.sIdentificacionOrdenante = sIdentificacionOrdenante;
		this.sIdentificacionOrdenanteSufijo = sIdentificacionOrdenanteSufijo;
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
}
