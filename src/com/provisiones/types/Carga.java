package com.provisiones.types;

import java.util.ArrayList;

public class Carga 
{
	private int iCodigo = 0;
	private ArrayList<CargaTabla> alCarga;

	//Constructor de clase

	public Carga(int iCodigo, ArrayList<CargaTabla> alCarga) {
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
	public ArrayList<CargaTabla> getAlCarga() {
		return alCarga;
	}
	public void setAlCarga(ArrayList<CargaTabla> alCarga) {
		this.alCarga = alCarga;
	}
	
	
}
