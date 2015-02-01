package com.provisiones.types.errores;

public class ErrorComunidadTabla 
{

	private String COCLDO = "";
	private String DCOCLDO = "";
	private String NUDCOM = "";
	private String NOMCOC = "";
	
	private String MOVIMIENTO = "";
	private String ERRORES = "";

	//Constructor de clase

	public ErrorComunidadTabla(String cOCLDO, String dCOCLDO, String nUDCOM,
			String nOMCOC, String mOVIMIENTO, String eRRORES) {
		super();
		COCLDO = cOCLDO;
		DCOCLDO = dCOCLDO;
		NUDCOM = nUDCOM;
		NOMCOC = nOMCOC;
		MOVIMIENTO = mOVIMIENTO;
		ERRORES = eRRORES;
	}

	//Métodos de acceso
	
	public String getCOCLDO() {
		return COCLDO;
	}

	public void setCOCLDO(String cOCLDO) {
		COCLDO = cOCLDO;
	}

	public String getDCOCLDO() {
		return DCOCLDO;
	}

	public void setDCOCLDO(String dCOCLDO) {
		DCOCLDO = dCOCLDO;
	}

	public String getNUDCOM() {
		return NUDCOM;
	}

	public void setNUDCOM(String nUDCOM) {
		NUDCOM = nUDCOM;
	}

	public String getNOMCOC() {
		return NOMCOC;
	}

	public void setNOMCOC(String nOMCOC) {
		NOMCOC = nOMCOC;
	}

	public String getMOVIMIENTO() {
		return MOVIMIENTO;
	}

	public void setMOVIMIENTO(String mOVIMIENTO) {
		MOVIMIENTO = mOVIMIENTO;
	}

	public String getERRORES() {
		return ERRORES;
	}

	public void setERRORES(String eRRORES) {
		ERRORES = eRRORES;
	}

}

//Autor: Francisco Valverde Manjón