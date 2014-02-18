package com.provisiones.types.tablas;

public class ComunidadTabla 
{
	private String COCLDO = "";
	private String NUDCOM = "";
	private String NOMCOC = "";
	private String NOMPRC = "";
	private String NOMADC = "";

	private String sActivos = "";
	
	//Constructor de clase

	public ComunidadTabla(String cOCLDO, String nUDCOM, String nOMCOC,
			String nOMPRC, String nOMADC, String sActivos) {
		super();
		COCLDO = cOCLDO;
		NUDCOM = nUDCOM;
		NOMCOC = nOMCOC;
		NOMPRC = nOMPRC;
		NOMADC = nOMADC;
		this.sActivos = sActivos;
	}

	//Métodos de acceso
	
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

	public String getsActivos() {
		return sActivos;
	}

	public void setsActivos(String sActivos) {
		this.sActivos = sActivos;
	}

}
