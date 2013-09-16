package com.provisiones.pl.detalles;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.provisiones.dal.qm.QMActivos;
import com.provisiones.misc.Utils;
import com.provisiones.pl.GestorActivos;
import com.provisiones.types.Activo;

public class GestorDetallesActivo implements Serializable 
{

	private static final long serialVersionUID = 6852249796176190672L;

	static String sClassName = GestorDetallesActivo.class.getName();
	
	private String sCOACES = "";
	private String sNUINMU = "";
	private String sCOSOPA = "";
	private String sCOENAE = "";
	private String sCOESEN = "";
	private String sNOVIAS = "";
	private String sNUPOAC = "";
	private String sNUESAC = "";
	private String sNUPIAC = "";
	private String sNUPUAC = "";
	private String sNOMUIN = "";
	private String sCOPRAE = "";
	private String sNOPRAC = "";
	private String sCOPOIN = "";
	private String sFEREAP = "";
	private String sCOREAE = "";
	private String sFEINAU = "";
	private String sFESOPO = "";
	private String sFESEPO = "";
	private String sFEREPO = "";
	private String sFEADAC = "";
	private String sCODIJU = "";
	private String sCOSJUP = "";
	private String sCOSTLI = "";
	private String sCOSCAR = "";
	private String sCOESVE = "";
	private String sCOTSIN = "";
	private String sNUFIRE = "";
	private String sNUREGP = "";
	private String sNOMUI0 = "";
	private String sNULIBE = "";
	private String sNUTOME = "";
	private String sNUFOLE = "";
	private String sNUINSR = "";
	private String sCOSOCU = "";
	private String sCOXPRO = "";
	private String sFESOLA = "";
	private String sFESELA = "";
	private String sFERELA = "";
	private String sFERLLA = "";
	private String sCASPRE = "";
	private String sCASUTR = "";
	private String sCASUTC = "";
	private String sCASUTG = "";
	private String sBIARRE = "";
	private String sCADORM = "";
	private String sCABANO = "";
	private String sBIGAPA = "";
	private String sCAGAPA = "";
	private String sCASUTE = "";
	private String sBILIPO = "";
	private String sBILIAC = "";
	private String sBILIUS = "";
	private String sBIBOIN = "";
	private String sBICEFI = "";
	private String sCASUCB = "";
	private String sCASUCS = "";
	private String sFEACON = "";
	private String sIDAUTO = "";
	private String sFEDEMA = "";
	private String sYNOCUR = "";
	private String sOBRECO = "";
	private String sYNOLEC = "";
	private String sNOLOJZ = "";
	private String sFEREDE = "";
	private String sPOPROP = "";
	private String sCOGRAP = "";
	private String sFEPREG = "";
	private String sFEPHAC = "";
	private String sFEFOAC = "";
	private String sFEVACT = "";
	private String sIMVACT = "";
	private String sNUFIPR = "";
	private String sCOTPET = "";
	private String sFEEMPT = "";
	private String sFESORC = "";
	private String sFESODE = "";
	private String sFEREAC = "";
	private String sCOXSIA = "";
	private String sNUJUZD = "";
	private String sNURCAT = "";
	private String sNOMPRC = "";
	private String sNUTPRC = "";
	private String sNOMADC = "";
	private String sNUTADC = "";
	private String sIMPCOO = "";
	private String sCOENOR = "";
	private String sCOSPAT = "";
	private String sCOSPAS = "";
	private String sIDCOL3 = "";
	private String sBIOBNU = "";
	private String sPOBRAR = "";
	
	public GestorDetallesActivo()
	{
		Utils.standardIO2File("");//Salida por fichero de texto
		
		getCOACESElegido();
    	//FacesContext FCInstance = FacesContext.getCurrentInstance();
    	//String theBeanName = "GestorActivos";
    	//GestorActivos bean = (GestorActivos) FCInstance.getELContext().getELResolver().getValue(FCInstance.getELContext(), null, theBeanName);
    	
		//String sValor = bean.getsCOACES();

	}
	public String volver()
	{
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		session.removeAttribute("GestorDetallesActivo");
		
		return "listaactivos.xhtml";
	}
	
	public void getCOACESElegido()
	{
		

		
		String sValor = ((GestorActivos)((HttpSession) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorActivos")).getsCOACES();
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "GestorDetallesActivo", "sCOACESnB:|"+sValor+"|");

		
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, "GestorDetallesActivo", "sCOACES:|"+sCOACES+"|");
		
		
		if (!sValor.equals(""))
		{
			com.provisiones.misc.Utils.debugTrace(true, sClassName, "GestorDetallesActivo", "It's Works!!!");
		
			Activo activo = QMActivos.getActivo(sValor);
		
		this.sCOACES = activo.getCOACES();
		this.sNUINMU = activo.getNUINMU();
		this.sCOSOPA = activo.getCOSOPA();
		this.sCOENAE = activo.getCOENAE();
		this.sCOESEN = activo.getCOESEN();
		this.sNOVIAS = activo.getNOVIAS();
		this.sNUPOAC = activo.getNUPOAC();
		this.sNUESAC = activo.getNUESAC();
		this.sNUPIAC = activo.getNUPIAC();
		this.sNUPUAC = activo.getNUPUAC();
		this.sNOMUIN = activo.getNOMUIN();
		this.sCOPRAE = activo.getCOPRAE();
		this.sNOPRAC = activo.getNOPRAC();
		this.sCOPOIN = activo.getCOPOIN();
		this.sFEREAP = Utils.recuperaFecha(activo.getFEREAP());
		this.sCOREAE = activo.getCOREAE();
		this.sFEINAU = Utils.recuperaFecha(activo.getFEINAU());
		this.sFESOPO = Utils.recuperaFecha(activo.getFESOPO());
		this.sFESEPO = Utils.recuperaFecha(activo.getFESEPO());
		this.sFEREPO = Utils.recuperaFecha(activo.getFEREPO());
		this.sFEADAC = Utils.recuperaFecha(activo.getFEADAC());
		this.sCODIJU = activo.getCODIJU();
		this.sCOSJUP = activo.getCOSJUP();
		this.sCOSTLI = activo.getCOSTLI();
		this.sCOSCAR = activo.getCOSCAR();
		this.sCOESVE = activo.getCOESVE();
		this.sCOTSIN = activo.getCOTSIN();
		this.sNUFIRE = activo.getNUFIRE();
		this.sNUREGP = activo.getNUREGP();
		this.sNOMUI0 = activo.getNOMUI0();
		this.sNULIBE = activo.getNULIBE();
		this.sNUTOME = activo.getNUTOME();
		this.sNUFOLE = activo.getNUFOLE();
		this.sNUINSR = activo.getNUINSR();
		this.sCOSOCU = activo.getCOSOCU();
		this.sCOXPRO = activo.getCOXPRO();
		this.sFESOLA = Utils.recuperaFecha(activo.getFESOLA());
		this.sFESELA = Utils.recuperaFecha(activo.getFESELA());
		this.sFERELA = Utils.recuperaFecha(activo.getFERELA());
		this.sFERLLA = Utils.recuperaFecha(activo.getFERLLA());
		this.sCASPRE = Utils.recuperaImporte(false,activo.getCASPRE());
		this.sCASUTR = Utils.recuperaImporte(false,activo.getCASUTR());
		this.sCASUTC = Utils.recuperaImporte(false,activo.getCASUTC());
		this.sCASUTG = Utils.recuperaImporte(false,activo.getCASUTG());
		this.sBIARRE = activo.getBIARRE();
		this.sCADORM = activo.getCADORM();
		this.sCABANO = activo.getCABANO();
		this.sBIGAPA = activo.getBIGAPA();
		this.sCAGAPA = activo.getCAGAPA();
		this.sCASUTE = activo.getCASUTE();
		this.sBILIPO = activo.getBILIPO();
		this.sBILIAC = activo.getBILIAC();
		this.sBILIUS = activo.getBILIUS();
		this.sBIBOIN = activo.getBIBOIN();
		this.sBICEFI = activo.getBICEFI();
		this.sCASUCB = Utils.recuperaImporte(false,activo.getCASUCB());
		this.sCASUCS = Utils.recuperaImporte(false,activo.getCASUCS());
		this.sFEACON = activo.getFEACON();
		this.sIDAUTO = activo.getIDAUTO();
		this.sFEDEMA = Utils.recuperaFecha(activo.getFEDEMA());
		this.sYNOCUR = activo.getYNOCUR();
		this.sOBRECO = activo.getOBRECO();
		this.sYNOLEC = activo.getYNOLEC();
		this.sNOLOJZ = activo.getNOLOJZ();
		this.sFEREDE = Utils.recuperaFecha(activo.getFEREDE());
		this.sPOPROP = activo.getPOPROP();
		this.sCOGRAP = activo.getCOGRAP();
		this.sFEPREG = Utils.recuperaFecha(activo.getFEPREG());
		this.sFEPHAC = Utils.recuperaFecha(activo.getFEPHAC());
		this.sFEFOAC = Utils.recuperaFecha(activo.getFEFOAC());
		this.sFEVACT = Utils.recuperaFecha(activo.getFEVACT());
		this.sIMVACT = Utils.recuperaImporte(false,activo.getIMVACT());
		this.sNUFIPR = activo.getNUFIPR();
		this.sCOTPET = activo.getCOTPET();
		this.sFEEMPT = Utils.recuperaFecha(activo.getFEEMPT());
		this.sFESORC = Utils.recuperaFecha(activo.getFESORC());
		this.sFESODE = Utils.recuperaFecha(activo.getFESODE());
		this.sFEREAC = Utils.recuperaFecha(activo.getFEREAC());
		this.sCOXSIA = activo.getCOXSIA();
		this.sNUJUZD = activo.getNUJUZD();
		this.sNURCAT = activo.getNURCAT();
		this.sNOMPRC = activo.getNOMPRC();
		this.sNUTPRC = activo.getNUTPRC();
		this.sNOMADC = activo.getNOMADC();
		this.sNUTADC = activo.getNUTADC();
		this.sIMPCOO = Utils.recuperaImporte(false,activo.getIMPCOO());
		this.sCOENOR = activo.getCOENOR();
		this.sCOSPAT = activo.getCOSPAT();
		this.sCOSPAS = activo.getCOSPAS();
		this.sIDCOL3 = activo.getIDCOL3();
		this.sBIOBNU = activo.getBIOBNU();
		this.sPOBRAR = Utils.recuperaImporte(false,activo.getPOBRAR());
		}
		
	}

	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
	}

	public String getsNUINMU() {
		return sNUINMU;
	}

	public void setsNUINMU(String sNUINMU) {
		this.sNUINMU = sNUINMU;
	}

	public String getsCOSOPA() {
		return sCOSOPA;
	}

	public void setsCOSOPA(String sCOSOPA) {
		this.sCOSOPA = sCOSOPA;
	}

	public String getsCOENAE() {
		return sCOENAE;
	}

	public void setsCOENAE(String sCOENAE) {
		this.sCOENAE = sCOENAE;
	}

	public String getsCOESEN() {
		return sCOESEN;
	}

	public void setsCOESEN(String sCOESEN) {
		this.sCOESEN = sCOESEN;
	}

	public String getsNOVIAS() {
		return sNOVIAS;
	}

	public void setsNOVIAS(String sNOVIAS) {
		this.sNOVIAS = sNOVIAS;
	}

	public String getsNUPOAC() {
		return sNUPOAC;
	}

	public void setsNUPOAC(String sNUPOAC) {
		this.sNUPOAC = sNUPOAC;
	}

	public String getsNUESAC() {
		return sNUESAC;
	}

	public void setsNUESAC(String sNUESAC) {
		this.sNUESAC = sNUESAC;
	}

	public String getsNUPIAC() {
		return sNUPIAC;
	}

	public void setsNUPIAC(String sNUPIAC) {
		this.sNUPIAC = sNUPIAC;
	}

	public String getsNUPUAC() {
		return sNUPUAC;
	}

	public void setsNUPUAC(String sNUPUAC) {
		this.sNUPUAC = sNUPUAC;
	}

	public String getsNOMUIN() {
		return sNOMUIN;
	}

	public void setsNOMUIN(String sNOMUIN) {
		this.sNOMUIN = sNOMUIN;
	}

	public String getsCOPRAE() {
		return sCOPRAE;
	}

	public void setsCOPRAE(String sCOPRAE) {
		this.sCOPRAE = sCOPRAE;
	}

	public String getsNOPRAC() {
		return sNOPRAC;
	}

	public void setsNOPRAC(String sNOPRAC) {
		this.sNOPRAC = sNOPRAC;
	}

	public String getsCOPOIN() {
		return sCOPOIN;
	}

	public void setsCOPOIN(String sCOPOIN) {
		this.sCOPOIN = sCOPOIN;
	}

	public String getsFEREAP() {
		return sFEREAP;
	}

	public void setsFEREAP(String sFEREAP) {
		this.sFEREAP = sFEREAP;
	}

	public String getsCOREAE() {
		return sCOREAE;
	}

	public void setsCOREAE(String sCOREAE) {
		this.sCOREAE = sCOREAE;
	}

	public String getsFEINAU() {
		return sFEINAU;
	}

	public void setsFEINAU(String sFEINAU) {
		this.sFEINAU = sFEINAU;
	}

	public String getsFESOPO() {
		return sFESOPO;
	}

	public void setsFESOPO(String sFESOPO) {
		this.sFESOPO = sFESOPO;
	}

	public String getsFESEPO() {
		return sFESEPO;
	}

	public void setsFESEPO(String sFESEPO) {
		this.sFESEPO = sFESEPO;
	}

	public String getsFEREPO() {
		return sFEREPO;
	}

	public void setsFEREPO(String sFEREPO) {
		this.sFEREPO = sFEREPO;
	}

	public String getsFEADAC() {
		return sFEADAC;
	}

	public void setsFEADAC(String sFEADAC) {
		this.sFEADAC = sFEADAC;
	}

	public String getsCODIJU() {
		return sCODIJU;
	}

	public void setsCODIJU(String sCODIJU) {
		this.sCODIJU = sCODIJU;
	}

	public String getsCOSJUP() {
		return sCOSJUP;
	}

	public void setsCOSJUP(String sCOSJUP) {
		this.sCOSJUP = sCOSJUP;
	}

	public String getsCOSTLI() {
		return sCOSTLI;
	}

	public void setsCOSTLI(String sCOSTLI) {
		this.sCOSTLI = sCOSTLI;
	}

	public String getsCOSCAR() {
		return sCOSCAR;
	}

	public void setsCOSCAR(String sCOSCAR) {
		this.sCOSCAR = sCOSCAR;
	}

	public String getsCOESVE() {
		return sCOESVE;
	}

	public void setsCOESVE(String sCOESVE) {
		this.sCOESVE = sCOESVE;
	}

	public String getsCOTSIN() {
		return sCOTSIN;
	}

	public void setsCOTSIN(String sCOTSIN) {
		this.sCOTSIN = sCOTSIN;
	}

	public String getsNUFIRE() {
		return sNUFIRE;
	}

	public void setsNUFIRE(String sNUFIRE) {
		this.sNUFIRE = sNUFIRE;
	}

	public String getsNUREGP() {
		return sNUREGP;
	}

	public void setsNUREGP(String sNUREGP) {
		this.sNUREGP = sNUREGP;
	}

	public String getsNOMUI0() {
		return sNOMUI0;
	}

	public void setsNOMUI0(String sNOMUI0) {
		this.sNOMUI0 = sNOMUI0;
	}

	public String getsNULIBE() {
		return sNULIBE;
	}

	public void setsNULIBE(String sNULIBE) {
		this.sNULIBE = sNULIBE;
	}

	public String getsNUTOME() {
		return sNUTOME;
	}

	public void setsNUTOME(String sNUTOME) {
		this.sNUTOME = sNUTOME;
	}

	public String getsNUFOLE() {
		return sNUFOLE;
	}

	public void setsNUFOLE(String sNUFOLE) {
		this.sNUFOLE = sNUFOLE;
	}

	public String getsNUINSR() {
		return sNUINSR;
	}

	public void setsNUINSR(String sNUINSR) {
		this.sNUINSR = sNUINSR;
	}

	public String getsCOSOCU() {
		return sCOSOCU;
	}

	public void setsCOSOCU(String sCOSOCU) {
		this.sCOSOCU = sCOSOCU;
	}

	public String getsCOXPRO() {
		return sCOXPRO;
	}

	public void setsCOXPRO(String sCOXPRO) {
		this.sCOXPRO = sCOXPRO;
	}

	public String getsFESOLA() {
		return sFESOLA;
	}

	public void setsFESOLA(String sFESOLA) {
		this.sFESOLA = sFESOLA;
	}

	public String getsFESELA() {
		return sFESELA;
	}

	public void setsFESELA(String sFESELA) {
		this.sFESELA = sFESELA;
	}

	public String getsFERELA() {
		return sFERELA;
	}

	public void setsFERELA(String sFERELA) {
		this.sFERELA = sFERELA;
	}

	public String getsFERLLA() {
		return sFERLLA;
	}

	public void setsFERLLA(String sFERLLA) {
		this.sFERLLA = sFERLLA;
	}

	public String getsCASPRE() {
		return sCASPRE;
	}

	public void setsCASPRE(String sCASPRE) {
		this.sCASPRE = sCASPRE;
	}

	public String getsCASUTR() {
		return sCASUTR;
	}

	public void setsCASUTR(String sCASUTR) {
		this.sCASUTR = sCASUTR;
	}

	public String getsCASUTC() {
		return sCASUTC;
	}

	public void setsCASUTC(String sCASUTC) {
		this.sCASUTC = sCASUTC;
	}

	public String getsCASUTG() {
		return sCASUTG;
	}

	public void setsCASUTG(String sCASUTG) {
		this.sCASUTG = sCASUTG;
	}

	public String getsBIARRE() {
		return sBIARRE;
	}

	public void setsBIARRE(String sBIARRE) {
		this.sBIARRE = sBIARRE;
	}

	public String getsCADORM() {
		return sCADORM;
	}

	public void setsCADORM(String sCADORM) {
		this.sCADORM = sCADORM;
	}

	public String getsCABANO() {
		return sCABANO;
	}

	public void setsCABANO(String sCABANO) {
		this.sCABANO = sCABANO;
	}

	public String getsBIGAPA() {
		return sBIGAPA;
	}

	public void setsBIGAPA(String sBIGAPA) {
		this.sBIGAPA = sBIGAPA;
	}

	public String getsCAGAPA() {
		return sCAGAPA;
	}

	public void setsCAGAPA(String sCAGAPA) {
		this.sCAGAPA = sCAGAPA;
	}

	public String getsCASUTE() {
		return sCASUTE;
	}

	public void setsCASUTE(String sCASUTE) {
		this.sCASUTE = sCASUTE;
	}

	public String getsBILIPO() {
		return sBILIPO;
	}

	public void setsBILIPO(String sBILIPO) {
		this.sBILIPO = sBILIPO;
	}

	public String getsBILIAC() {
		return sBILIAC;
	}

	public void setsBILIAC(String sBILIAC) {
		this.sBILIAC = sBILIAC;
	}

	public String getsBILIUS() {
		return sBILIUS;
	}

	public void setsBILIUS(String sBILIUS) {
		this.sBILIUS = sBILIUS;
	}

	public String getsBIBOIN() {
		return sBIBOIN;
	}

	public void setsBIBOIN(String sBIBOIN) {
		this.sBIBOIN = sBIBOIN;
	}

	public String getsBICEFI() {
		return sBICEFI;
	}

	public void setsBICEFI(String sBICEFI) {
		this.sBICEFI = sBICEFI;
	}

	public String getsCASUCB() {
		return sCASUCB;
	}

	public void setsCASUCB(String sCASUCB) {
		this.sCASUCB = sCASUCB;
	}

	public String getsCASUCS() {
		return sCASUCS;
	}

	public void setsCASUCS(String sCASUCS) {
		this.sCASUCS = sCASUCS;
	}

	public String getsFEACON() {
		return sFEACON;
	}

	public void setsFEACON(String sFEACON) {
		this.sFEACON = sFEACON;
	}

	public String getsIDAUTO() {
		return sIDAUTO;
	}

	public void setsIDAUTO(String sIDAUTO) {
		this.sIDAUTO = sIDAUTO;
	}

	public String getsFEDEMA() {
		return sFEDEMA;
	}

	public void setsFEDEMA(String sFEDEMA) {
		this.sFEDEMA = sFEDEMA;
	}

	public String getsYNOCUR() {
		return sYNOCUR;
	}

	public void setsYNOCUR(String sYNOCUR) {
		this.sYNOCUR = sYNOCUR;
	}

	public String getsOBRECO() {
		return sOBRECO;
	}

	public void setsOBRECO(String sOBRECO) {
		this.sOBRECO = sOBRECO;
	}

	public String getsYNOLEC() {
		return sYNOLEC;
	}

	public void setsYNOLEC(String sYNOLEC) {
		this.sYNOLEC = sYNOLEC;
	}

	public String getsNOLOJZ() {
		return sNOLOJZ;
	}

	public void setsNOLOJZ(String sNOLOJZ) {
		this.sNOLOJZ = sNOLOJZ;
	}

	public String getsFEREDE() {
		return sFEREDE;
	}

	public void setsFEREDE(String sFEREDE) {
		this.sFEREDE = sFEREDE;
	}

	public String getsPOPROP() {
		return sPOPROP;
	}

	public void setsPOPROP(String sPOPROP) {
		this.sPOPROP = sPOPROP;
	}

	public String getsCOGRAP() {
		return sCOGRAP;
	}

	public void setsCOGRAP(String sCOGRAP) {
		this.sCOGRAP = sCOGRAP;
	}

	public String getsFEPREG() {
		return sFEPREG;
	}

	public void setsFEPREG(String sFEPREG) {
		this.sFEPREG = sFEPREG;
	}

	public String getsFEPHAC() {
		return sFEPHAC;
	}

	public void setsFEPHAC(String sFEPHAC) {
		this.sFEPHAC = sFEPHAC;
	}

	public String getsFEFOAC() {
		return sFEFOAC;
	}

	public void setsFEFOAC(String sFEFOAC) {
		this.sFEFOAC = sFEFOAC;
	}

	public String getsFEVACT() {
		return sFEVACT;
	}

	public void setsFEVACT(String sFEVACT) {
		this.sFEVACT = sFEVACT;
	}

	public String getsIMVACT() {
		return sIMVACT;
	}

	public void setsIMVACT(String sIMVACT) {
		this.sIMVACT = sIMVACT;
	}

	public String getsNUFIPR() {
		return sNUFIPR;
	}

	public void setsNUFIPR(String sNUFIPR) {
		this.sNUFIPR = sNUFIPR;
	}

	public String getsCOTPET() {
		return sCOTPET;
	}

	public void setsCOTPET(String sCOTPET) {
		this.sCOTPET = sCOTPET;
	}

	public String getsFEEMPT() {
		return sFEEMPT;
	}

	public void setsFEEMPT(String sFEEMPT) {
		this.sFEEMPT = sFEEMPT;
	}

	public String getsFESORC() {
		return sFESORC;
	}

	public void setsFESORC(String sFESORC) {
		this.sFESORC = sFESORC;
	}

	public String getsFESODE() {
		return sFESODE;
	}

	public void setsFESODE(String sFESODE) {
		this.sFESODE = sFESODE;
	}

	public String getsFEREAC() {
		return sFEREAC;
	}

	public void setsFEREAC(String sFEREAC) {
		this.sFEREAC = sFEREAC;
	}

	public String getsCOXSIA() {
		return sCOXSIA;
	}

	public void setsCOXSIA(String sCOXSIA) {
		this.sCOXSIA = sCOXSIA;
	}

	public String getsNUJUZD() {
		return sNUJUZD;
	}

	public void setsNUJUZD(String sNUJUZD) {
		this.sNUJUZD = sNUJUZD;
	}

	public String getsNURCAT() {
		return sNURCAT;
	}

	public void setsNURCAT(String sNURCAT) {
		this.sNURCAT = sNURCAT;
	}

	public String getsNOMPRC() {
		return sNOMPRC;
	}

	public void setsNOMPRC(String sNOMPRC) {
		this.sNOMPRC = sNOMPRC;
	}

	public String getsNUTPRC() {
		return sNUTPRC;
	}

	public void setsNUTPRC(String sNUTPRC) {
		this.sNUTPRC = sNUTPRC;
	}

	public String getsNOMADC() {
		return sNOMADC;
	}

	public void setsNOMADC(String sNOMADC) {
		this.sNOMADC = sNOMADC;
	}

	public String getsNUTADC() {
		return sNUTADC;
	}

	public void setsNUTADC(String sNUTADC) {
		this.sNUTADC = sNUTADC;
	}

	public String getsIMPCOO() {
		return sIMPCOO;
	}

	public void setsIMPCOO(String sIMPCOO) {
		this.sIMPCOO = sIMPCOO;
	}

	public String getsCOENOR() {
		return sCOENOR;
	}

	public void setsCOENOR(String sCOENOR) {
		this.sCOENOR = sCOENOR;
	}

	public String getsCOSPAT() {
		return sCOSPAT;
	}

	public void setsCOSPAT(String sCOSPAT) {
		this.sCOSPAT = sCOSPAT;
	}

	public String getsCOSPAS() {
		return sCOSPAS;
	}

	public void setsCOSPAS(String sCOSPAS) {
		this.sCOSPAS = sCOSPAS;
	}

	public String getsIDCOL3() {
		return sIDCOL3;
	}

	public void setsIDCOL3(String sIDCOL3) {
		this.sIDCOL3 = sIDCOL3;
	}

	public String getsBIOBNU() {
		return sBIOBNU;
	}

	public void setsBIOBNU(String sBIOBNU) {
		this.sBIOBNU = sBIOBNU;
	}

	public String getsPOBRAR() {
		return sPOBRAR;
	}

	public void setsPOBRAR(String sPOBRAR) {
		this.sPOBRAR = sPOBRAR;
	}
	
}
