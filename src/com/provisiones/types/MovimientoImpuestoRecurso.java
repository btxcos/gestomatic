package com.provisiones.types;

public class MovimientoImpuestoRecurso 
{

	private String CODTRN = "";
	private String COTDOR = "";
	private String IDPROV = "";
	private String COACCI = "";
	private String COENGP = "";
	private String COACES = "";
	private String NURCAT = "";
	private String COGRUC = "";
	private String COTACA = "";
	private String COSBAC = "";
	private String BITC18 = "";
	private String FEPRRE = "";
	private String BITC19 = "";
	private String FERERE = "";
	private String BITC20 = "";
	private String FEDEIN = "";
	private String BITC21 = "";
	private String BISODE = "";
	private String BITC22 = "";
	private String BIRESO = "";
	private String COTEXA = "";
	private String BITC09 = "";
	private String OBTEXC = "";
	private String OBDEER = "";
	private String FILLER = "                                                                                                               ";

	//Constructor de clase
	public MovimientoImpuestoRecurso(String cODTRN, String cOTDOR, String iDPROV,
			String cOACCI, String cOENGP, String cOACES, String nURCAT,
			String cOGRUC, String cOTACA, String cOSBAC, String bITC18,
			String fEPRRE, String bITC19, String fERERE, String bITC20,
			String fEDEIN, String bITC21, String bISODE, String bITC22,
			String bIRESO, String cOTEXA, String bITC09, String oBTEXC,
			String oBDEER) {
		super();
		CODTRN = cODTRN;
		COTDOR = cOTDOR;
		IDPROV = iDPROV;
		COACCI = cOACCI;
		COENGP = cOENGP;
		COACES = cOACES;
		NURCAT = nURCAT;
		COGRUC = cOGRUC;
		COTACA = cOTACA;
		COSBAC = cOSBAC;
		BITC18 = bITC18;
		FEPRRE = fEPRRE;
		BITC19 = bITC19;
		FERERE = fERERE;
		BITC20 = bITC20;
		FEDEIN = fEDEIN;
		BITC21 = bITC21;
		BISODE = bISODE;
		BITC22 = bITC22;
		BIRESO = bIRESO;
		COTEXA = cOTEXA;
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

	public String getCOGRUC() {
		return COGRUC;
	}

	public void setCOGRUC(String cOGRUC) {
		COGRUC = cOGRUC;
	}

	public String getCOTACA() {
		return COTACA;
	}

	public void setCOTACA(String cOTACA) {
		COTACA = cOTACA;
	}

	public String getCOSBAC() {
		return COSBAC;
	}

	public void setCOSBAC(String cOSBAC) {
		COSBAC = cOSBAC;
	}

	public String getBITC18() {
		return BITC18;
	}

	public void setBITC18(String bITC18) {
		BITC18 = bITC18;
	}

	public String getFEPRRE() {
		return FEPRRE;
	}

	public void setFEPRRE(String fEPRRE) {
		FEPRRE = fEPRRE;
	}

	public String getBITC19() {
		return BITC19;
	}

	public void setBITC19(String bITC19) {
		BITC19 = bITC19;
	}

	public String getFERERE() {
		return FERERE;
	}

	public void setFERERE(String fERERE) {
		FERERE = fERERE;
	}

	public String getBITC20() {
		return BITC20;
	}

	public void setBITC20(String bITC20) {
		BITC20 = bITC20;
	}

	public String getFEDEIN() {
		return FEDEIN;
	}

	public void setFEDEIN(String fEDEIN) {
		FEDEIN = fEDEIN;
	}

	public String getBITC21() {
		return BITC21;
	}

	public void setBITC21(String bITC21) {
		BITC21 = bITC21;
	}

	public String getBISODE() {
		return BISODE;
	}

	public void setBISODE(String bISODE) {
		BISODE = bISODE;
	}

	public String getBITC22() {
		return BITC22;
	}

	public void setBITC22(String bITC22) {
		BITC22 = bITC22;
	}

	public String getBIRESO() {
		return BIRESO;
	}

	public void setBIRESO(String bIRESO) {
		BIRESO = bIRESO;
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

	public void pintaMovimientoImpuestoRecurso()
	{
		System.out.println("(MOVIMIENTO IMPUESTO/RECURSO)");
		System.out.println("CODTRN:|"+CODTRN+"|");
		System.out.println("COTDOR:|"+COTDOR+"|");
		System.out.println("IDPROV:|"+IDPROV+"|");
		System.out.println("COACCI:|"+COACCI+"|");
		System.out.println("COENGP:|"+COENGP+"|");
		System.out.println("COACES:|"+COACES+"|");
		System.out.println("NURCAT:|"+NURCAT+"|");
		System.out.println("COGRUC:|"+COGRUC+"|");
		System.out.println("COTACA:|"+COTACA+"|");
		System.out.println("COSBAC:|"+COSBAC+"|");
		System.out.println("BITC18:|"+BITC18+"|");
		System.out.println("FEPRRE:|"+FEPRRE+"|");
		System.out.println("BITC19:|"+BITC19+"|");
		System.out.println("FERERE:|"+FERERE+"|");
		System.out.println("BITC20:|"+BITC20+"|");
		System.out.println("FEDEIN:|"+FEDEIN+"|");
		System.out.println("BITC21:|"+BITC21+"|");
		System.out.println("BISODE:|"+BISODE+"|");
		System.out.println("BITC22:|"+BITC22+"|");
		System.out.println("BIRESO:|"+BIRESO+"|");
		System.out.println("COTEXA:|"+COTEXA+"|");
		System.out.println("BITC09:|"+BITC09+"|");
		System.out.println("OBTEXC:|"+OBTEXC+"|");
		System.out.println("OBDEER:|"+OBDEER+"|");
	}
}
