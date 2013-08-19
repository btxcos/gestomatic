package com.provisiones.types;

public class MovimientoReferenciaCatastral 
{

	private String CODTRN = "";
	private String COTDOR = "";
	private String IDPROV = "";
	private String COACCI = "";
	private String COENGP = "";
	private String COACES = "";
	private String NURCAT = "";
	private String BITC16 = "";
	private String TIRCAT = "";
	private String BITC17 = "";
	private String ENEMIS = "";
	private String COTEXA = "";
	private String BITC09 = "";
	private String OBTEXC = "";
	private String OBDEER = "";

	//Ampliacion de valor catastral
	private String BITC23 = "";
	private String IMVSUE = "";
	private String BITC24 = "";
	private String IMCATA = "";
	private String BITC25 = "";
	private String FERECA = "";

	
	private String FILLER = "                                                                                                                            ";

	//Constructor de clase

	public MovimientoReferenciaCatastral(String cODTRN, String cOTDOR,
			String iDPROV, String cOACCI, String cOENGP, String cOACES,
			String nURCAT, String bITC16, String tIRCAT, String bITC17,
			String eNEMIS, String cOTEXA, String bITC09, String oBTEXC,
			String oBDEER, String bITC23, String iMVSUE, String bITC24,
			String iMCATA, String bITC25, String fERECA) {
		super();
		CODTRN = cODTRN;
		COTDOR = cOTDOR;
		IDPROV = iDPROV;
		COACCI = cOACCI;
		COENGP = cOENGP;
		COACES = cOACES;
		NURCAT = nURCAT;
		BITC16 = bITC16;
		TIRCAT = tIRCAT;
		BITC17 = bITC17;
		ENEMIS = eNEMIS;
		COTEXA = cOTEXA;
		BITC09 = bITC09;
		OBTEXC = oBTEXC;
		OBDEER = oBDEER;
		BITC23 = bITC23;
		IMVSUE = iMVSUE;
		BITC24 = bITC24;
		IMCATA = iMCATA;
		BITC25 = bITC25;
		FERECA = fERECA;
	}	
	
	//Métodos de acceso

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

	public String getCOACES() {
		return COACES;
	}

	public void setCOACES(String cOACES) {
		COACES = cOACES;
	}

	public String getNURCAT() {
		return NURCAT;
	}

	public void setNURCAT(String nURCAT) {
		NURCAT = nURCAT;
	}

	public String getBITC16() {
		return BITC16;
	}

	public void setBITC16(String bITC16) {
		BITC16 = bITC16;
	}

	public String getTIRCAT() {
		return TIRCAT;
	}

	public void setTIRCAT(String tIRCAT) {
		TIRCAT = tIRCAT;
	}

	public String getBITC17() {
		return BITC17;
	}

	public void setBITC17(String bITC17) {
		BITC17 = bITC17;
	}

	public String getENEMIS() {
		return ENEMIS;
	}

	public void setENEMIS(String eNEMIS) {
		ENEMIS = eNEMIS;
	}

	public String getCOTEXA() {
		return COTEXA;
	}

	public void setCOTEXA(String cOTEXA) {
		COTEXA = cOTEXA;
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

	public String getBITC23() {
		return BITC23;
	}

	public void setBITC23(String bITC23) {
		BITC23 = bITC23;
	}

	public String getIMVSUE() {
		return IMVSUE;
	}

	public void setIMVSUE(String iMVSUE) {
		IMVSUE = iMVSUE;
	}

	public String getBITC24() {
		return BITC24;
	}

	public void setBITC24(String bITC24) {
		BITC24 = bITC24;
	}

	public String getIMCATA() {
		return IMCATA;
	}

	public void setIMCATA(String iMCATA) {
		IMCATA = iMCATA;
	}

	public String getBITC25() {
		return BITC25;
	}

	public void setBITC25(String bITC25) {
		BITC25 = bITC25;
	}

	public String getFERECA() {
		return FERECA;
	}

	public void setFERECA(String fERECA) {
		FERECA = fERECA;
	}

	public String getFILLER() {
		return FILLER;
	}

	public void pintaMovimientoReferenciaCatastral()
	{
		System.out.println("(MOVIMIENTO REFERENCIA CATASTRAL)");
		System.out.println("CODTRN:|"+CODTRN+"|");
		System.out.println("COTDOR:|"+COTDOR+"|");
		System.out.println("IDPROV:|"+IDPROV+"|");
		System.out.println("COACCI:|"+COACCI+"|");
		System.out.println("COENGP:|"+COENGP+"|");
		System.out.println("COACES:|"+COACES+"|");
		System.out.println("NURCAT:|"+NURCAT+"|");
		System.out.println("BITC16:|"+BITC16+"|");
		System.out.println("TIRCAT:|"+TIRCAT+"|");
		System.out.println("BITC17:|"+BITC17+"|");
		System.out.println("ENEMIS:|"+ENEMIS+"|");
		System.out.println("COTEXA:|"+COTEXA+"|");
		System.out.println("BITC09:|"+BITC09+"|");
		System.out.println("OBTEXC:|"+OBTEXC+"|");
		System.out.println("OBDEER:|"+OBDEER+"|");
		
		//Ampliacion de valor catastral
		System.out.println("BITC23:|"+BITC23+"|");
		System.out.println("IMVSUE:|"+IMVSUE+"|");
		System.out.println("BITC24:|"+BITC24+"|");
		System.out.println("IMCATA:|"+IMCATA+"|");
		System.out.println("BITC25:|"+BITC25+"|");
		System.out.println("FERECA:|"+FERECA+"|");
	}


}
