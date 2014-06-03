package com.provisiones.types.tablas;

public class EstadoActivoTabla 
{
	//Identificador
	private String COACES = "";

	private String sEstado = "";
	private String sFechaActivacion = "";

	//Constructor de clase
	
	public EstadoActivoTabla(String cOACES, String sEstado,
			String sFechaActivacion) {
		super();
		COACES = cOACES;
		this.sEstado = sEstado;
		this.sFechaActivacion = sFechaActivacion;
	}

	//Métodos de acceso
	
	public String getCOACES() {
		return COACES;
	}

	public void setCOACES(String cOACES) {
		COACES = cOACES;
	}

	public String getsEstado() {
		return sEstado;
	}

	public void setsEstado(String sEstado) {
		this.sEstado = sEstado;
	}

	public String getsFechaActivacion() {
		return sFechaActivacion;
	}

	public void setsFechaActivacion(String sFechaActivacion) {
		this.sFechaActivacion = sFechaActivacion;
	}
	
	
	

}
