package com.provisiones.types;

public class Comunidad 
{

	String COCLDO = "";
	String NUDCOM = "";
	String NOMCOC = "";
	String NODCCO = "";
	String NOMPRC = "";
	String NUTPRC = "";
	String NOMADC = "";
	String NUTADC = "";
	String NODCAD = "";
	String NUCCEN = "";
	String NUCCOF = "";
	String NUCCDI = "";
	String NUCCNT = "";
	String OBTEXC = "";

	//Constructor de clase

	public Comunidad(String cOCLDO, String nUDCOM, String nOMCOC,
			String nODCCO, String nOMPRC, String nUTPRC, String nOMADC,
			String nUTADC, String nODCAD, String nUCCEN, String nUCCOF,
			String nUCCDI, String nUCCNT, String oBTEXC) {
		super();

		COCLDO = cOCLDO;
		NUDCOM = nUDCOM;
		NOMCOC = nOMCOC;
		NODCCO = nODCCO;
		NOMPRC = nOMPRC;
		NUTPRC = nUTPRC;
		NOMADC = nOMADC;
		NUTADC = nUTADC;
		NODCAD = nODCAD;
		NUCCEN = nUCCEN;
		NUCCOF = nUCCOF;
		NUCCDI = nUCCDI;
		NUCCNT = nUCCNT;
		OBTEXC = oBTEXC;
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

	public String getNODCCO() {
		return NODCCO;
	}

	public void setNODCCO(String nODCCO) {
		NODCCO = nODCCO;
	}

	public String getNOMPRC() {
		return NOMPRC;
	}

	public void setNOMPRC(String nOMPRC) {
		NOMPRC = nOMPRC;
	}

	public String getNUTPRC() {
		return NUTPRC;
	}

	public void setNUTPRC(String nUTPRC) {
		NUTPRC = nUTPRC;
	}

	public String getNOMADC() {
		return NOMADC;
	}

	public void setNOMADC(String nOMADC) {
		NOMADC = nOMADC;
	}

	public String getNUTADC() {
		return NUTADC;
	}

	public void setNUTADC(String nUTADC) {
		NUTADC = nUTADC;
	}

	public String getNODCAD() {
		return NODCAD;
	}

	public void setNODCAD(String nODCAD) {
		NODCAD = nODCAD;
	}

	public String getNUCCEN() {
		return NUCCEN;
	}

	public void setNUCCEN(String nUCCEN) {
		NUCCEN = nUCCEN;
	}

	public String getNUCCOF() {
		return NUCCOF;
	}

	public void setNUCCOF(String nUCCOF) {
		NUCCOF = nUCCOF;
	}

	public String getNUCCDI() {
		return NUCCDI;
	}

	public void setNUCCDI(String nUCCDI) {
		NUCCDI = nUCCDI;
	}

	public String getNUCCNT() {
		return NUCCNT;
	}

	public void setNUCCNT(String nUCCNT) {
		NUCCNT = nUCCNT;
	}

	public String getOBTEXC() {
		return OBTEXC;
	}

	public void setOBTEXC(String oBTEXC) {
		OBTEXC = oBTEXC;
	}
}
