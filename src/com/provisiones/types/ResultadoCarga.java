package com.provisiones.types;

import java.util.ArrayList;

import com.provisiones.types.tablas.ResultadosTabla;

public class ResultadoCarga 
{
	private int iCodigo = 0;
	private ArrayList<ResultadosTabla> alCarga;
	private String sArchivo = "";
	private String sDuracion = "";
	private int iRegistrosProcesados = 0;
	private int iRegistrosCorrectos = 0;
	
	//Constructor de clase

	public ResultadoCarga(int iCodigo, ArrayList<ResultadosTabla> alCarga,
			String sArchivo, String sDuracion, int iRegistrosProcesados,
			int iRegistrosCorrectos) {
		super();
		this.iCodigo = iCodigo;
		this.alCarga = alCarga;
		this.sArchivo = sArchivo;
		this.sDuracion = sDuracion;
		this.iRegistrosProcesados = iRegistrosProcesados;
		this.iRegistrosCorrectos = iRegistrosCorrectos;
	}
	
	//Métodos de acceso

	public int getiCodigo() {
		return iCodigo;
	}

	public void setiCodigo(int iCodigo) {
		this.iCodigo = iCodigo;
	}

	public ArrayList<ResultadosTabla> getAlCarga() {
		return alCarga;
	}

	public void setAlCarga(ArrayList<ResultadosTabla> alCarga) {
		this.alCarga = alCarga;
	}

	public String getsArchivo() {
		return sArchivo;
	}

	public void setsArchivo(String sArchivo) {
		this.sArchivo = sArchivo;
	}

	public String getsDuracion() {
		return sDuracion;
	}

	public void setsDuracion(String sDuracion) {
		this.sDuracion = sDuracion;
	}

	public int getiRegistrosProcesados() {
		return iRegistrosProcesados;
	}

	public void setiRegistrosProcesados(int iRegistrosProcesados) {
		this.iRegistrosProcesados = iRegistrosProcesados;
	}

	public int getiRegistrosCorrectos() {
		return iRegistrosCorrectos;
	}

	public void setiRegistrosCorrectos(int iRegistrosCorrectos) {
		this.iRegistrosCorrectos = iRegistrosCorrectos;
	}
	
}
