package com.provisiones.types;

public class Gasto 
{

	private String COACES = "";
	private String COGRUG = "";
	private String COTPGA = "";
	private String COSBGA = "";
	private String PTPAGO = "";
	private String FEDEVE = "";
	private String FFGTVP = "";
	private String FEPAGA = "";
	private String FELIPG = "";
	private String COSIGA = "";
	private String FEEESI = "";
	private String FEECOI = "";
	private String FEEAUI = "";
	private String FEEPAI = "";
	private String IMNGAS = "";
	private String YCOS02 = "";
	private String IMRGAS = "";
	private String YCOS04 = "";
	private String IMDGAS = "";
	private String YCOS06 = "";
	private String IMCOST = "";
	private String YCOS08 = "";
	private String IMOGAS = "";
	private String YCOS10 = "";
	private String IMDTGA = "";
	private String IMIMGA = "";
	private String COIMPT = "";
	private String COTNEG = "";
	private String FEAGTO = "";
	private String COMONA = "";
	private String BIAUTO = "";
	private String FEAUFA = "";
	private String FEPGPR = "";
	
	//Constructor de clase

	public Gasto(String cOACES, String cOGRUG, String cOTPGA, String cOSBGA,
			String pTPAGO, String fEDEVE, String fFGTVP, String fEPAGA,
			String fELIPG, String cOSIGA, String fEEESI, String fEECOI,
			String fEEAUI, String fEEPAI, String iMNGAS, String yCOS02,
			String iMRGAS, String yCOS04, String iMDGAS, String yCOS06,
			String iMCOST, String yCOS08, String iMOGAS, String yCOS10,
			String iMDTGA, String iMIMGA, String cOIMPT, String cOTNEG,
			String fEAGTO, String cOMONA, String bIAUTO, String fEAUFA,
			String fEPGPR) {
		super();
		COACES = cOACES;
		COGRUG = cOGRUG;
		COTPGA = cOTPGA;
		COSBGA = cOSBGA;
		PTPAGO = pTPAGO;
		FEDEVE = fEDEVE;
		FFGTVP = fFGTVP;
		FEPAGA = fEPAGA;
		FELIPG = fELIPG;
		COSIGA = cOSIGA;
		FEEESI = fEEESI;
		FEECOI = fEECOI;
		FEEAUI = fEEAUI;
		FEEPAI = fEEPAI;
		IMNGAS = iMNGAS;
		YCOS02 = yCOS02;
		IMRGAS = iMRGAS;
		YCOS04 = yCOS04;
		IMDGAS = iMDGAS;
		YCOS06 = yCOS06;
		IMCOST = iMCOST;
		YCOS08 = yCOS08;
		IMOGAS = iMOGAS;
		YCOS10 = yCOS10;
		IMDTGA = iMDTGA;
		IMIMGA = iMIMGA;
		COIMPT = cOIMPT;
		COTNEG = cOTNEG;
		FEAGTO = fEAGTO;
		COMONA = cOMONA;
		BIAUTO = bIAUTO;
		FEAUFA = fEAUFA;
		FEPGPR = fEPGPR;
	}

	//Métodos de acceso
	
	public String getCOACES() {
		return COACES;
	}

	public void setCOACES(String cOACES) {
		COACES = cOACES;
	}

	public String getCOGRUG() {
		return COGRUG;
	}

	public void setCOGRUG(String cOGRUG) {
		COGRUG = cOGRUG;
	}

	public String getCOTPGA() {
		return COTPGA;
	}

	public void setCOTPGA(String cOTPGA) {
		COTPGA = cOTPGA;
	}

	public String getCOSBGA() {
		return COSBGA;
	}

	public void setCOSBGA(String cOSBGA) {
		COSBGA = cOSBGA;
	}

	public String getPTPAGO() {
		return PTPAGO;
	}

	public void setPTPAGO(String pTPAGO) {
		PTPAGO = pTPAGO;
	}

	public String getFEDEVE() {
		return FEDEVE;
	}

	public void setFEDEVE(String fEDEVE) {
		FEDEVE = fEDEVE;
	}

	public String getFFGTVP() {
		return FFGTVP;
	}

	public void setFFGTVP(String fFGTVP) {
		FFGTVP = fFGTVP;
	}

	public String getFEPAGA() {
		return FEPAGA;
	}

	public void setFEPAGA(String fEPAGA) {
		FEPAGA = fEPAGA;
	}

	public String getFELIPG() {
		return FELIPG;
	}

	public void setFELIPG(String fELIPG) {
		FELIPG = fELIPG;
	}

	public String getCOSIGA() {
		return COSIGA;
	}

	public void setCOSIGA(String cOSIGA) {
		COSIGA = cOSIGA;
	}

	public String getFEEESI() {
		return FEEESI;
	}

	public void setFEEESI(String fEEESI) {
		FEEESI = fEEESI;
	}

	public String getFEECOI() {
		return FEECOI;
	}

	public void setFEECOI(String fEECOI) {
		FEECOI = fEECOI;
	}

	public String getFEEAUI() {
		return FEEAUI;
	}

	public void setFEEAUI(String fEEAUI) {
		FEEAUI = fEEAUI;
	}

	public String getFEEPAI() {
		return FEEPAI;
	}

	public void setFEEPAI(String fEEPAI) {
		FEEPAI = fEEPAI;
	}

	public String getIMNGAS() {
		return IMNGAS;
	}

	public void setIMNGAS(String iMNGAS) {
		IMNGAS = iMNGAS;
	}

	public String getYCOS02() {
		return YCOS02;
	}

	public void setYCOS02(String yCOS02) {
		YCOS02 = yCOS02;
	}

	public String getIMRGAS() {
		return IMRGAS;
	}

	public void setIMRGAS(String iMRGAS) {
		IMRGAS = iMRGAS;
	}

	public String getYCOS04() {
		return YCOS04;
	}

	public void setYCOS04(String yCOS04) {
		YCOS04 = yCOS04;
	}

	public String getIMDGAS() {
		return IMDGAS;
	}

	public void setIMDGAS(String iMDGAS) {
		IMDGAS = iMDGAS;
	}

	public String getYCOS06() {
		return YCOS06;
	}

	public void setYCOS06(String yCOS06) {
		YCOS06 = yCOS06;
	}

	public String getIMCOST() {
		return IMCOST;
	}

	public void setIMCOST(String iMCOST) {
		IMCOST = iMCOST;
	}

	public String getYCOS08() {
		return YCOS08;
	}

	public void setYCOS08(String yCOS08) {
		YCOS08 = yCOS08;
	}

	public String getIMOGAS() {
		return IMOGAS;
	}

	public void setIMOGAS(String iMOGAS) {
		IMOGAS = iMOGAS;
	}

	public String getYCOS10() {
		return YCOS10;
	}

	public void setYCOS10(String yCOS10) {
		YCOS10 = yCOS10;
	}

	public String getIMDTGA() {
		return IMDTGA;
	}

	public void setIMDTGA(String iMDTGA) {
		IMDTGA = iMDTGA;
	}

	public String getIMIMGA() {
		return IMIMGA;
	}

	public void setIMIMGA(String iMIMGA) {
		IMIMGA = iMIMGA;
	}

	public String getCOIMPT() {
		return COIMPT;
	}

	public void setCOIMPT(String cOIMPT) {
		COIMPT = cOIMPT;
	}

	public String getCOTNEG() {
		return COTNEG;
	}

	public void setCOTNEG(String cOTNEG) {
		COTNEG = cOTNEG;
	}

	public String getFEAGTO() {
		return FEAGTO;
	}

	public void setFEAGTO(String fEAGTO) {
		FEAGTO = fEAGTO;
	}

	public String getCOMONA() {
		return COMONA;
	}

	public void setCOMONA(String cOMONA) {
		COMONA = cOMONA;
	}

	public String getBIAUTO() {
		return BIAUTO;
	}

	public void setBIAUTO(String bIAUTO) {
		BIAUTO = bIAUTO;
	}

	public String getFEAUFA() {
		return FEAUFA;
	}

	public void setFEAUFA(String fEAUFA) {
		FEAUFA = fEAUFA;
	}

	public String getFEPGPR() {
		return FEPGPR;
	}

	public void setFEPGPR(String fEPGPR) {
		FEPGPR = fEPGPR;
	}
	
	public void pintaMovimientoGasto()
	{
		System.out.println("(GASTO)");
		System.out.println("COACES:|"+COACES+"|");
		System.out.println("COGRUG:|"+COGRUG+"|");
		System.out.println("COTPGA:|"+COTPGA+"|");
		System.out.println("COSBGA:|"+COSBGA+"|");
		System.out.println("PTPAGO:|"+PTPAGO+"|");
		System.out.println("FEDEVE:|"+FEDEVE+"|");
		System.out.println("FFGTVP:|"+FFGTVP+"|");
		System.out.println("FEPAGA:|"+FEPAGA+"|");
		System.out.println("FELIPG:|"+FELIPG+"|");
		System.out.println("COSIGA:|"+COSIGA+"|");
		System.out.println("FEEESI:|"+FEEESI+"|");
		System.out.println("FEECOI:|"+FEECOI+"|");
		System.out.println("FEEAUI:|"+FEEAUI+"|");
		System.out.println("FEEPAI:|"+FEEPAI+"|");
		System.out.println("IMNGAS:|"+IMNGAS+"|");
		System.out.println("YCOS02:|"+YCOS02+"|");
		System.out.println("IMRGAS:|"+IMRGAS+"|");
		System.out.println("YCOS04:|"+YCOS04+"|");
		System.out.println("IMDGAS:|"+IMDGAS+"|");
		System.out.println("YCOS06:|"+YCOS06+"|");
		System.out.println("IMCOST:|"+IMCOST+"|");
		System.out.println("YCOS08:|"+YCOS08+"|");
		System.out.println("IMOGAS:|"+IMOGAS+"|");
		System.out.println("YCOS10:|"+YCOS10+"|");
		System.out.println("IMDTGA:|"+IMDTGA+"|");
		System.out.println("IMIMGA:|"+IMIMGA+"|");
		System.out.println("COIMPT:|"+COIMPT+"|");
		System.out.println("COTNEG:|"+COTNEG+"|");
		System.out.println("FEAGTO:|"+FEAGTO+"|");
		System.out.println("COMONA:|"+COMONA+"|");
		System.out.println("BIAUTO:|"+BIAUTO+"|");
		System.out.println("FEAUFA:|"+FEAUFA+"|");
		System.out.println("FEPGPR:|"+FEPGPR+"|");

	} 
}
