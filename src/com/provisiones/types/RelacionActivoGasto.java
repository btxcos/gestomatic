package com.provisiones.types;

public class RelacionActivoGasto 
{
	private int iCOACES = 0;
	private long liCodGasto = 0;

	//Constructor de clase
	
	public RelacionActivoGasto(int iCOACES, long liCodGasto) {
		super();
		this.iCOACES = iCOACES;
		this.liCodGasto = liCodGasto;
	}
	
	//Métodos de acceso
	
	public int getiCOACES() {
		return iCOACES;
	}
	public void setiCOACES(int iCOACES) {
		this.iCOACES = iCOACES;
	}
	public long getLiCodGasto() {
		return liCodGasto;
	}
	public void setLiCodGasto(long liCodGasto) {
		this.liCodGasto = liCodGasto;
	}
	
	
}
