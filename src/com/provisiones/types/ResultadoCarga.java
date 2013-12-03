package com.provisiones.types;

import java.util.ArrayList;

import com.provisiones.types.tablas.ResultadosTabla;

public class ResultadoCarga 
{
	private int iCodigo = 0;
	private ArrayList<ResultadosTabla> alCarga;
	private String sArchivo = "";
	private String sDuracion = "";
	private long liRegistrosProcesados = 0;
	private long liRegistrosCorrectos = 0;
	
	//Constructor de clase

	public ResultadoCarga(int iCodigo, ArrayList<ResultadosTabla> alCarga,
			String sArchivo, String sDuracion, long liRegistrosProcesados,
			long liRegistrosCorrectos) {
		super();
		this.iCodigo = iCodigo;
		this.alCarga = alCarga;
		this.sArchivo = sArchivo;
		this.sDuracion = sDuracion;
		this.liRegistrosProcesados = liRegistrosProcesados;
		this.liRegistrosCorrectos = liRegistrosCorrectos;
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


	public long getLiRegistrosProcesados() {
		return liRegistrosProcesados;
	}


	public void setLiRegistrosProcesados(long liRegistrosProcesados) {
		this.liRegistrosProcesados = liRegistrosProcesados;
	}


	public long getLiRegistrosCorrectos() {
		return liRegistrosCorrectos;
	}


	public void setLiRegistrosCorrectos(long liRegistrosCorrectos) {
		this.liRegistrosCorrectos = liRegistrosCorrectos;
	}

}
