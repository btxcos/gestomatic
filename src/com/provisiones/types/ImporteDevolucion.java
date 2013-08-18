package com.provisiones.types;

public class ImporteDevolucion 
{
	private boolean bSigno = false;
	private String sImporte = "";
	
	//Constructor de clase
	
	public ImporteDevolucion(boolean bSigno, String sImporte) {
		super();
		this.bSigno = bSigno;
		this.sImporte = sImporte;
	}

	//Métodos de acceso
	
	public boolean isbSigno() {
		return bSigno;
	}

	public void setbSigno(boolean bSigno) {
		this.bSigno = bSigno;
	}

	public String getsImporte() {
		return sImporte;
	}

	public void setsImporte(String sImporte) {
		this.sImporte = sImporte;
	}
	
	public void pinta()
	{
		System.out.println(bSigno ? "-"+ sImporte:sImporte);
	}
	
}
