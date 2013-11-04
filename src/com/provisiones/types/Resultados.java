package com.provisiones.types;

import java.util.ArrayList;

import com.provisiones.types.tablas.ResultadosTabla;

public class Resultados 
{
	private int iCodigo = 0;
	private ArrayList<ResultadosTabla> alCarga;
	
	//Constructor de clase

	public Resultados(int iCodigo, ArrayList<ResultadosTabla> alCarga) {
		super();
		this.iCodigo = iCodigo;
		this.alCarga = alCarga;
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
	
	
}
