package com.provisiones.pl;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLActivos;

import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.tablas.ActivoTabla;

public class GestorActivos implements Serializable 
{

	private static final long serialVersionUID = 1167759390910696681L;
	
	private static Logger logger = LoggerFactory.getLogger(GestorActivos.class.getName());
	
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOENGP = ValoresDefecto.DEF_COENGP;

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
	
	private transient ActivoTabla activoseleccionado = null;
	private transient ArrayList<ActivoTabla> tablaactivos = null;
	
	
	public GestorActivos()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorActivos...");	
			encuentraActivos();
		}
	}
	
	
	public void borrarCamposActivo()
	{
    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
    	
    	this.setActivoseleccionado(null);
    	this.setTablaactivos(null);
	}
	
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  

    	borrarCamposActivo();
   	
    }
	
	public void encuentraActivos()
	{
		ActivoTabla buscaactivos = new ActivoTabla(
				sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
				sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
				sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(), "");
		
		logger.debug("Buscando Activos...");
		
		this.setTablaactivos(CLActivos.buscarActivos(buscaactivos));
		
		logger.debug("Encontrados {} activos relacionados.",getTablaactivos().size());
	}
	
	public void buscaActivos (ActionEvent actionEvent)
	{

		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			encuentraActivos();

			msg = new FacesMessage("Encontrados "+getTablaactivos().size()+" activos relacionados.");
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	public void seleccionarActivo(ActionEvent actionEvent) 
    {  
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;

	    	this.sCOACES  = activoseleccionado.getCOACES();
	    	
	    	msg = new FacesMessage("Activo "+ sCOACES +" Seleccionado.");
	    	
	    	logger.debug("Activo seleccionado:|{}|",sCOACES);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		
    }
	
	public String cargarComunidad() 
    {  
		String sPagina = "login.xhtml";
		
		if (ConnectionManager.comprobarConexion())
		{
	    	this.sCOACES = activoseleccionado.getCOACES();
	    	
	    	logger.debug("Redirigiendo...");
	    	
	    	sPagina =  "movimientoscomunidades.xhtml";
		}

		return sPagina;
    }

	public String cargarCuota() 
    {  
		String sPagina = "login.xhtml";
		
		if (ConnectionManager.comprobarConexion())
		{
	    	this.sCOACES = activoseleccionado.getCOACES();
	    	
	    	logger.debug("Redirigiendo...");
	    	
	    	sPagina =  "movimientoscuotas.xhtml";
		}

		return sPagina;
    }
	
	public String cargarReferencia() 
    {  
 		String sPagina = "login.xhtml";
		
		if (ConnectionManager.comprobarConexion())
		{
	    	this.sCOACES = activoseleccionado.getCOACES();
	    	
	    	logger.debug("Redirigiendo...");
	    	
	    	sPagina =  "movimientosreferencias.xhtml";
		}

		return sPagina;
    }
	
	public String cargarImpuestos() 
    {  
		String sPagina = "login.xhtml";
		
		if (ConnectionManager.comprobarConexion())
		{
	    	this.sCOACES = activoseleccionado.getCOACES();
	    	
	    	logger.debug("Redirigiendo...");
	    	
	    	sPagina =  "movimientosimpuestos.xhtml";
		}
		
		return sPagina;

    }
	public String cargarGastos() 
    {  
		String sPagina = "login.xhtml";
		
		if (ConnectionManager.comprobarConexion())
		{
	    	this.sCOACES = activoseleccionado.getCOACES();
	    	
	    	logger.debug("Redirigiendo...");
	    	
	    	sPagina =  "gastos.xhtml";
		}

		return sPagina;
    }

	public String cargarDetalles() 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
	    	this.sCOACES = activoseleccionado.getCOACES();
	    	
	    	logger.debug("sCOACES:|{}|",sCOACES);
	    	
	    	logger.debug("Redirigiendo...");

	    	sPagina = "detallesactivo.xhtml";
		}

		return sPagina;
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

	public ActivoTabla getActivoseleccionado() {
		return activoseleccionado;
	}

	public void setActivoseleccionado(ActivoTabla activoseleccionado) {
		this.activoseleccionado = activoseleccionado;
	}

	public ArrayList<ActivoTabla> getTablaactivos() {
		return tablaactivos;
	}

	public void setTablaactivos(ArrayList<ActivoTabla> tablaactivos) {
		this.tablaactivos = tablaactivos;
	}

	public String getsIDPROV() {
		return sIDPROV;
	}

	public void setsIDPROV(String sIDPROV) {
		this.sIDPROV = sIDPROV;
	}

	public String getsCOENGP() {
		return sCOENGP;
	}

	public void setsCOENGP(String sCOENGP) {
		this.sCOENGP = sCOENGP;
	}


}
