package com.provisiones.types;

import java.util.ArrayList;

public class Resultados 
{
	private int iCodigo = 0;
	private ArrayList<ResultadosTabla> alCarga;

	public Resultados(int iCodigo, ArrayList<ResultadosTabla> alCarga) {
		super();
		this.iCodigo = iCodigo;
		this.alCarga = alCarga;
	}

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
