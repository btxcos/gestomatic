package com.provisiones.types;

public class Transicion 
{
	private String sID = "";
	private int iTipoID = 0;
	private String sPaginaOrigen = "";
	private String sGestorDestino = "";

	//Constructor de clase
	
	public Transicion(String sID, int iTipoID, String sPaginaOrigen,
			String sGestorDestino) {
		super();
		this.sID = sID;
		this.iTipoID = iTipoID;
		this.sPaginaOrigen = sPaginaOrigen;
		this.sGestorDestino = sGestorDestino;
	}

	//Métodos de acceso

	public String getsID() {
		return sID;
	}


	public void setsID(String sID) {
		this.sID = sID;
	}


	public int getiTipoID() {
		return iTipoID;
	}


	public void setiTipoID(int iTipoID) {
		this.iTipoID = iTipoID;
	}


	public String getsPaginaOrigen() {
		return sPaginaOrigen;
	}


	public void setsPaginaOrigen(String sPaginaOrigen) {
		this.sPaginaOrigen = sPaginaOrigen;
	}


	public String getsGestorDestino() {
		return sGestorDestino;
	}


	public void setsGestorDestino(String sGestorDestino) {
		this.sGestorDestino = sGestorDestino;
	}
	
}
