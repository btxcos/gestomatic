package com.provisiones.types.tablas;

public class ComunidadTabla 
{
	private String COCLDO = "";
	private String NUDCOM = "";
	private String NOMCOC = "";
	private String NOMPRC = "";
	private String NOMADC = "";
	
	//Constructor de clase

	public ComunidadTabla(String cOCLDO, String nUDCOM, String nOMCOC,
			String nOMPRC, String nOMADC) {
		super();
		COCLDO = cOCLDO;
		NUDCOM = nUDCOM;
		NOMCOC = nOMCOC;
		NOMPRC = nOMPRC;
		NOMADC = nOMADC;
	}

	//M�todos de acceso


	public String getCOCLDO() {
		return COCLDO;
	}

	public void setCOCLDO(String cOCLDO) {
		COCLDO = cOCLDO;
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

	public String getNOMPRC() {
		return NOMPRC;
	}

	public void setNOMPRC(String nOMPRC) {
		NOMPRC = nOMPRC;
	}

	public String getNOMADC() {
		return NOMADC;
	}

	public void setNOMADC(String nOMADC) {
		NOMADC = nOMADC;
	}

	

}
