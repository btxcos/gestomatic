package com.provisiones.types.tablas;

public class ComunidadTabla 
{
	//Identificador
	private String sComunidadID = "";
	
	private String COCLDO = "";
	private String DCOCLDO = "";
	private String NUDCOM = "";
	private String NOMCOC = "";
	private String NOMPRC = "";
	private String NOMADC = "";

	//Número de Activos Asociados
	private String sActivos = "";
	
	//Constructor de clase

	public ComunidadTabla(String sComunidadID, String cOCLDO, String dCOCLDO,
			String nUDCOM, String nOMCOC, String nOMPRC, String nOMADC,
			String sActivos) {
		super();
		this.sComunidadID = sComunidadID;
		COCLDO = cOCLDO;
		DCOCLDO = dCOCLDO;
		NUDCOM = nUDCOM;
		NOMCOC = nOMCOC;
		NOMPRC = nOMPRC;
		NOMADC = nOMADC;
		this.sActivos = sActivos;
	}
	
	//Métodos de acceso
	
	public String getsComunidadID() {
		return sComunidadID;
	}



	public void setsComunidadID(String sComunidadID) {
		this.sComunidadID = sComunidadID;
	}

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
