package com.provisiones.types;

public class MovimientoComunidad 
{
	private String CODTRN = "";
	private String COTDOR = "";
	private String IDPROV = "";
	private String COACCI = "";
	private String COENGP = "";
	private String COCLDO = "";
	private String NUDCOM = "";
	private String BITC10 = "";
	private String COACES = "";
	private String BITC01 = "";
	private String NOMCOC = "";
	private String BITC02 = "";
	private String NODCCO = "";
	private String BITC03 = "";
	private String NOMPRC = "";
	private String BITC04 = "";
	private String NUTPRC = "";
	private String BITC05 = "";
	private String NOMADC = "";
	private String BITC06 = "";
	private String NUTADC = "";
	private String BITC07 = "";
	private String NODCAD = "";
	private String BITC08 = "";
	private String NUCCEN = "";
	private String NUCCOF = "";
	private String NUCCDI = "";
	private String NUCCNT = "";
	private String BITC09 = "";
	private String OBTEXC = "";
	private String OBDEER = "";
	private String FILLER = "                                                                                                                        ";

	//Constructor de clase
	public MovimientoComunidad(String cODTRN, String cOTDOR, String iDPROV,
			String cOACCI, String cOENGP, String cOCLDO, String nUDCOM,
			String bITC10, String cOACES, String bITC01, String nOMCOC,
			String bITC02, String nODCCO, String bITC03, String nOMPRC,
			String bITC04, String nUTPRC, String bITC05, String nOMADC,
			String bITC06, String nUTADC, String bITC07, String nODCAD,
			String bITC08, String nUCCEN, String nUCCOF, String nUCCDI,
			String nUCCNT, String bITC09, String oBTEXC, String oBDEER) {
		super();
		CODTRN = cODTRN;
		COTDOR = cOTDOR;
		IDPROV = iDPROV;
		COACCI = cOACCI;
		COENGP = cOENGP;
		COCLDO = cOCLDO;
		NUDCOM = nUDCOM;
		BITC10 = bITC10;
		COACES = cOACES;
		BITC01 = bITC01;
		NOMCOC = nOMCOC;
		BITC02 = bITC02;
		NODCCO = nODCCO;
		BITC03 = bITC03;
		NOMPRC = nOMPRC;
		BITC04 = bITC04;
		NUTPRC = nUTPRC;
		BITC05 = bITC05;
		NOMADC = nOMADC;
		BITC06 = bITC06;
		NUTADC = nUTADC;
		BITC07 = bITC07;
		NODCAD = nODCAD;
		BITC08 = bITC08;
		NUCCEN = nUCCEN;
		NUCCOF = nUCCOF;
		NUCCDI = nUCCDI;
		NUCCNT = nUCCNT;
		BITC09 = bITC09;
		OBTEXC = oBTEXC;
		OBDEER = oBDEER;
	}


	//Métodos de acceso
	public String getFILLER() {
		return FILLER;
	}

	public String getCODTRN() {
		return CODTRN;
	}
	public void setCODTRN(String cODTRN) {
		CODTRN = cODTRN;
	}
	public String getCOTDOR() {
		return COTDOR;
	}
	public void setCOTDOR(String cOTDOR) {
		COTDOR = cOTDOR;
	}
	public String getIDPROV() {
		return IDPROV;
	}
	public void setIDPROV(String iDPROV) {
		IDPROV = iDPROV;
	}
	public String getCOACCI() {
		return COACCI;
	}
	public void setCOACCI(String cOACCI) {
		COACCI = cOACCI;
	}
	public String getCOENGP() {
		return COENGP;
	}
	public void setCOENGP(String cOENGP) {
		COENGP = cOENGP;
	}
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
	public String getBITC10() {
		return BITC10;
	}
	public void setBITC10(String bITC10) {
		BITC10 = bITC10;
	}
	public String getCOACES() {
		return COACES;
	}
	public void setCOACES(String cOACES) {
		COACES = cOACES;
	}
	public String getBITC01() {
		return BITC01;
	}
	public void setBITC01(String bITC01) {
		BITC01 = bITC01;
	}
	public String getNOMCOC() {
		return NOMCOC;
	}
	public void setNOMCOC(String nOMCOC) {
		NOMCOC = nOMCOC;
	}
	public String getBITC02() {
		return BITC02;
	}
	public void setBITC02(String bITC02) {
		BITC02 = bITC02;
	}
	public String getNODCCO() {
		return NODCCO;
	}
	public void setNODCCO(String nODCCO) {
		NODCCO = nODCCO;
	}
	public String getBITC03() {
		return BITC03;
	}
	public void setBITC03(String bITC03) {
		BITC03 = bITC03;
	}
	public String getNOMPRC() {
		return NOMPRC;
	}
	public void setNOMPRC(String nOMPRC) {
		NOMPRC = nOMPRC;
	}
	public String getBITC04() {
		return BITC04;
	}
	public void setBITC04(String bITC04) {
		BITC04 = bITC04;
	}
	public String getNUTPRC() {
		return NUTPRC;
	}
	public void setNUTPRC(String nUTPRC) {
		NUTPRC = nUTPRC;
	}
	public String getBITC05() {
		return BITC05;
	}
	public void setBITC05(String bITC05) {
		BITC05 = bITC05;
	}
	public String getNOMADC() {
		return NOMADC;
	}
	public void setNOMADC(String nOMADC) {
		NOMADC = nOMADC;
	}
	public String getBITC06() {
		return BITC06;
	}
	public void setBITC06(String bITC06) {
		BITC06 = bITC06;
	}
	public String getNUTADC() {
		return NUTADC;
	}
	public void setNUTADC(String nUTADC) {
		NUTADC = nUTADC;
	}
	public String getBITC07() {
		return BITC07;
	}
	public void setBITC07(String bITC07) {
		BITC07 = bITC07;
	}
	public String getNODCAD() {
		return NODCAD;
	}
	public void setNODCAD(String nODCAD) {
		NODCAD = nODCAD;
	}
	public String getBITC08() {
		return BITC08;
	}
	public void setBITC08(String bITC08) {
		BITC08 = bITC08;
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
	public String getBITC09() {
		return BITC09;
	}
	public void setBITC09(String bITC09) {
		BITC09 = bITC09;
	}
	public String getOBTEXC() {
		return OBTEXC;
	}
	public void setOBTEXC(String oBTEXC) {
		OBTEXC = oBTEXC;
	}
	public String getOBDEER() {
		return OBDEER;
	}
	public void setOBDEER(String oBDEER) {
		OBDEER = oBDEER;
	}
	
	public String logMovimientoComunidad()
	{
		return String.format("(MOVIMIENTO COMUNIDAD)\nCODTRN:|%s|\nCOTDOR:|%s|\nIDPROV:|%s|\nCOACCI:|%s|\nCOENGP:|%s|\nCOCLDO:|%s|\nNUDCOM:|%s|\nBITC10:|%s|\nCOACES:|%s|\nBITC01:|%s|\nNOMCOC:|%s|\nBITC02:|%s|\nNODCCO:|%s|\nBITC03:|%s|\nNOMPRC:|%s|\nBITC04:|%s|\nNUTPRC:|%s|\nBITC05:|%s|\nNOMADC:|%s|\nBITC06:|%s|\nNUTADC:|%s|\nBITC07:|%s|\nNODCAD:|%s|\nBITC08:|%s|\nNUCCEN:|%s|\nNUCCOF:|%s|\nNUCCDI:|%s|\nNUCCNT:|%s|\nBITC09:|%s|\nOBTEXC:|%s|\nOBDEER:|%s|",CODTRN,COTDOR,IDPROV,COACCI,COENGP,COCLDO,NUDCOM,BITC10,COACES,BITC01,NOMCOC,BITC02,NODCCO,BITC03,NOMPRC,BITC04,NUTPRC,BITC05,NOMADC,BITC06,NUTADC,BITC07,NODCAD,BITC08,NUCCEN,NUCCOF,NUCCDI,NUCCNT,BITC09,OBTEXC,OBDEER);
	}
}
