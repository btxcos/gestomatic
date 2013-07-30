package com.provisiones.pl;

import java.io.Serializable;

import com.provisiones.dal.qm.QMActivos;
import com.provisiones.types.Activo;

public class GestorGastos implements Serializable 
{
	private static final long serialVersionUID = 476229907564908389L;

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
	private String COUNMO = "";
	private String IMIMGA = "";
	private String COIMPT = "";
	private String COTNEG = "";
	private String COENCX = "";
	private String COOFCX = "";
	private String NUCONE = "";
	private String NUPROF = "";
	private String FEAGTO = "";
	private String COMONA = "";
	private String BIAUTO = "";
	private String FEAUFA = "";
	private String COTERR = "";
	private String FMPAGN = "";
	private String FEPGPR = "";
	private String FEAPLI = "";
	private String COAPII = "";
	private String COSPII = "";
	private String NUCLII = "";

	private Activo Activo = QMActivos.getActivo("");

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


	public String getCOUNMO() {
		return COUNMO;
	}


	public void setCOUNMO(String cOUNMO) {
		COUNMO = cOUNMO;
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


	public String getCOENCX() {
		return COENCX;
	}


	public void setCOENCX(String cOENCX) {
		COENCX = cOENCX;
	}


	public String getCOOFCX() {
		return COOFCX;
	}


	public void setCOOFCX(String cOOFCX) {
		COOFCX = cOOFCX;
	}


	public String getNUCONE() {
		return NUCONE;
	}


	public void setNUCONE(String nUCONE) {
		NUCONE = nUCONE;
	}


	public String getNUPROF() {
		return NUPROF;
	}


	public void setNUPROF(String nUPROF) {
		NUPROF = nUPROF;
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


	public String getCOTERR() {
		return COTERR;
	}


	public void setCOTERR(String cOTERR) {
		COTERR = cOTERR;
	}


	public String getFMPAGN() {
		return FMPAGN;
	}


	public void setFMPAGN(String fMPAGN) {
		FMPAGN = fMPAGN;
	}


	public String getFEPGPR() {
		return FEPGPR;
	}


	public void setFEPGPR(String fEPGPR) {
		FEPGPR = fEPGPR;
	}


	public String getFEAPLI() {
		return FEAPLI;
	}


	public void setFEAPLI(String fEAPLI) {
		FEAPLI = fEAPLI;
	}


	public String getCOAPII() {
		return COAPII;
	}


	public void setCOAPII(String cOAPII) {
		COAPII = cOAPII;
	}


	public String getCOSPII() {
		return COSPII;
	}


	public void setCOSPII(String cOSPII) {
		COSPII = cOSPII;
	}


	public String getNUCLII() {
		return NUCLII;
	}


	public void setNUCLII(String nUCLII) {
		NUCLII = nUCLII;
	}
}
