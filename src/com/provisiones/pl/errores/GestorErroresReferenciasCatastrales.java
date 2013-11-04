package com.provisiones.pl.errores;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


import com.provisiones.ll.CLErrores;
import com.provisiones.ll.CLReferencias;

import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

import com.provisiones.types.errores.ErrorReferenciaTabla;
import com.provisiones.types.errores.ErrorTabla;
import com.provisiones.types.movimientos.MovimientoReferenciaCatastral;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.ReferenciaTabla;

public class GestorErroresReferenciasCatastrales implements Serializable 
{
	private static final long serialVersionUID = 4706436052659575697L;
	
	private static Logger logger = LoggerFactory.getLogger(GestorErroresReferenciasCatastrales.class.getName());
	
	private String sCODTRN = ValoresDefecto.DEF_E3_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOACCI = "";
	private String sCOENGP = ValoresDefecto.DEF_COENGP;
	
	private String sNURCAT = "";
	private boolean bRNURCAT = true;
	private String sTIRCAT = "";
	private boolean bRTIRCAT = true;
	private String sENEMIS = "";
	private boolean bRENEMIS = true;
	private String sCOTEXA = ValoresDefecto.DEF_COTEXA;
	private String sOBTEXC = "";
	private boolean bROBTEXC = true;
	
	private String sOBDEER = "";
	
	//Ampliacion de valor catastral
	private String sIMVSUE = "";
	private boolean bRIMVSUE = true;
	private String sIMCATA = "";
	private boolean bRIMCATA = true;
	private String sFERECA = "";
	private boolean bRFERECA = true;
	
	private String sCOACES = "";
	private boolean bRCOACES = true;

	//Buscar activos
	private String sCOPOIN = "";
	private String sNOMUIN = "";	
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	
	//Buscar errores
	private String sCodMovimiento ="";
	private String sCodError = "";
	
	private String sCOACESB = "";
	private String sNURCATB = "";

	
	private transient ErrorReferenciaTabla movimientoseleccionado = null;
	private transient ArrayList<ErrorReferenciaTabla> tablareferenciaserror = null;
	
	private transient ErrorTabla errorseleccionado = null;
	private transient ArrayList<ErrorTabla> tablaerrores = null;
	
	private transient ArrayList<ActivoTabla> tablaactivos = null;
	private transient ActivoTabla activoseleccionado = null;
	
	private transient ArrayList<ReferenciaTabla> tablareferencias = null;
	private transient ReferenciaTabla referenciaseleccionada = null;
	
	
	public GestorErroresReferenciasCatastrales()
	{

	}
	
    public void borrarPlantillaError() 
    {  
    	this.sCOACESB = "";
    	this.sNURCATB = "";


    	this.movimientoseleccionado = null;
    	this.tablareferenciaserror = null;
    	
    	this.errorseleccionado = null;
    	this.tablaerrores = null;
    	
    	this.sCodMovimiento ="";
    	this.sCodError = "";
   	
    }
	
    public void limpiarPlantillaError(ActionEvent actionEvent) 
    {  
    	borrarPlantillaError();
   	
    }
	
	public void borrarPlantillaActivo()
	{
    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
	}
	
	public void borrarResultadosActivo()
	{
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
	}
	
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";

    	borrarPlantillaActivo();
    	
    	borrarResultadosActivo();
   	
    }
    
	public void borrarPlantillaReferencia()
	{
        this.sNURCAT = "";
        this.sTIRCAT = "";
        
    	//Ampliacion de valor catastral
    	this.sIMVSUE = "";
    	this.sIMCATA = "";
    	this.sFERECA = "";
        
        this.sENEMIS = "";
        this.sOBTEXC = "";
	}
	
	public void borrarResultadosReferencia()
	{
    	this.referenciaseleccionada = null;
    	this.tablareferencias = null;
	}
    
    public void limpiarPlantilla (ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";
    	
    	borrarPlantillaError();
    	
    	borrarResultadosActivo();
    	
    	borrarPlantillaReferencia();
    	
    	borrarResultadosReferencia();
    }
    
    public boolean editarError(int iCodError) 
    {  
    	boolean bSalida = false;
  	
		switch (iCodError) 
		{
		/*case 2://prueba
			this.bRENEMIS = false;
			this.bRFERECA = true;
			this.bRIMCATA = true;
			this.bRIMVSUE = true;
			this.bRNURCAT = true;
			this.bROBTEXC = true;
			this.bRTIRCAT = true;
			bSalida = true;
			break;*/
		default://error no recuperable
			this.bRENEMIS = true;
			this.bRFERECA = true;
			this.bRIMCATA = true;
			this.bRIMVSUE = true;
			this.bRNURCAT = true;
			this.bROBTEXC = true;
			this.bRTIRCAT = true;
			bSalida = false;
			break;
		}
		
		return bSalida;
   	
    }
	
	public void buscaReferenciasError(ActionEvent actionEvent)
	{
		FacesMessage msg;
		
		logger.debug("Buscando Referencias con errores...");
		
		ErrorReferenciaTabla filtro = new ErrorReferenciaTabla(
					sCOACESB.toUpperCase(), sNURCATB.toUpperCase(),
					"", "");

			this.setTablareferenciaserror(CLErrores.buscarReferenciasConErrores(filtro));

		
		
		msg = Utils.pfmsgInfo("Encontradas "+getTablareferenciaserror().size()+" Referencias relacionadas.");
		logger.debug("Encontradas {} Referencias relacionadas.",getTablareferenciaserror().size());
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void seleccionarMovimiento(ActionEvent actionEvent) 
    {  
		FacesMessage msg;
		
		this.sCodMovimiento = movimientoseleccionado.getMOVIMIENTO(); 
    	
		this.setTablaerrores(CLErrores.buscarErroresReferencia(sCodMovimiento));
		
		msg = Utils.pfmsgInfo("Encontrados "+getTablaerrores().size()+" errores relacionados.");
		logger.debug("Encontrados {} errores relacionados.",getTablaerrores().size());
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		MovimientoReferenciaCatastral movimiento = CLReferencias.buscarMovimientoReferenciaCatastral(sCodMovimiento);
		
		this.sCOACCI = movimiento.getCOACCI();
		this.sCOACES = movimiento.getCOACES();
		
    	this.sNURCAT = movimiento.getNURCAT(); 
    	this.sTIRCAT = movimiento.getTIRCAT();
    	this.sENEMIS = movimiento.getENEMIS();
    	this.sOBTEXC = movimiento.getOBTEXC();
    	
    	//Ampliacion de valor catastral
    	this.sIMVSUE = Utils.recuperaImporte(false,movimiento.getIMVSUE());
    	this.sIMCATA = Utils.recuperaImporte(false,movimiento.getIMCATA());
    	this.sFERECA = Utils.recuperaFecha(movimiento.getFERECA());
    	
				
        	
    	msg = Utils.pfmsgInfo("Errores de Referencia Catastral cargados.");
    	logger.debug("Errores de Referencia Catastral cargados.");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void seleccionarError(ActionEvent actionEvent) 
    {  
		FacesMessage msg;
    	
		this.sCodError = errorseleccionado.getsCodError(); 
		
    	int iCodError  = Integer.parseInt(sCodError);
    	
    	
    	logger.debug("Error seleccionado:|{}|",iCodError);
    	
    	String sMsg ="";
    	
    	if (editarError(iCodError))
    	{
    		sMsg = "Error editado.";
    		msg = Utils.pfmsgInfo(sMsg);
    		logger.info(sMsg);
    	}
    	else
    	{
    		sMsg = "[FATAL] ERROR: El error seleccionado no es recuperable. Por favor, pongase en contacto con soporte.";
    		msg = Utils.pfmsgFatal(sMsg);
    		logger.error(sMsg);
    	}

    	FacesContext.getCurrentInstance().addMessage(null, msg);
    }

	public void buscaActivos (ActionEvent actionEvent)
	{
		FacesMessage msg;
		
		ActivoTabla buscaactivos = new ActivoTabla(
				sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
				sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
				sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(),"");
		
		this.setTablaactivos(CLReferencias.buscarActivosConReferencias(buscaactivos));
		
		msg = Utils.pfmsgInfo("Encontrados "+getTablaactivos().size()+" activos relacionados.");
		logger.info("Encontrados "+getTablaactivos().size()+" activos relacionados.");

		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	public void seleccionarActivo(ActionEvent actionEvent) 
    {  
    	FacesMessage msg;
    	
    	this.sCOACES  = activoseleccionado.getCOACES();
    	
    	msg = Utils.pfmsgInfo("Activo '"+ sCOACES +"' cargado.");
    	logger.info("Activo '{}' cargado.",sCOACES);

    	FacesContext.getCurrentInstance().addMessage(null, msg);
		
    }
	
	public void cargarReferencias (ActionEvent actionEvent)
	{
		FacesMessage msg;
		
		this.tablareferencias = CLReferencias.buscarReferenciasActivo(sCOACES.toUpperCase());
		
		msg = Utils.pfmsgInfo("Encontradas "+getTablareferencias().size()+" referencias relacionadas.");
		logger.info("Encontradas {} referencias relacionadas.",getTablareferencias().size());

		FacesContext.getCurrentInstance().addMessage(null, msg);		
	}
	
    
	public void seleccionarReferencia(ActionEvent actionEvent) 
    {  
    	FacesMessage msg;
    	
    	this.sNURCAT = referenciaseleccionada.getNURCAT(); 
    	this.sTIRCAT = referenciaseleccionada.getTIRCAT();
    	this.sENEMIS = referenciaseleccionada.getENEMIS();
    	this.sOBTEXC = referenciaseleccionada.getOBTEXC();
    	
    	//Ampliacion de valor catastral
    	this.sIMVSUE = referenciaseleccionada.getIMVSUE();
    	this.sIMCATA = referenciaseleccionada.getIMCATA();
    	this.sFERECA = referenciaseleccionada.getFERECA();

    	msg = Utils.pfmsgInfo("Referencia '"+ sNURCAT +"' Seleccionada.");
    	logger.info("Referencia '{}' Seleccionada.",sNURCAT);

    	FacesContext.getCurrentInstance().addMessage(null, msg);

    }

	public void hoyFERECA (ActionEvent actionEvent)
	{
		this.setsFERECA(Utils.fechaDeHoy(true));
		logger.debug("sFERECA:|{}|",sFERECA);
	}
	
	public void registraDatos(ActionEvent actionEvent)
	{
		FacesMessage msg;
		
		String sMsg = "";
		
		if (!CLReferencias.existeMovimientoReferenciaCatastral(sCodMovimiento))
		{
			sMsg = "[FATAL] ERROR:911 - No se puede modificar la Referencia, no existe el movimiento. Por favor, revise los datos y avise a soporte.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
		}
		else
		{
			MovimientoReferenciaCatastral movimiento = new MovimientoReferenciaCatastral (
					sCODTRN.toUpperCase(), 
					sCOTDOR.toUpperCase(), 
					sIDPROV.toUpperCase(), 
					sCOACCI.toUpperCase(), 
					sCOENGP.toUpperCase(), 
					sCOACES.toUpperCase(), 
					sNURCAT.toUpperCase(),
					"", 
					sTIRCAT.toUpperCase(),
					"", 
					sENEMIS.toUpperCase(),
					ValoresDefecto.DEF_COTEXA,
					"", 
					sOBTEXC.toUpperCase(), 
					sOBDEER.toUpperCase(),
					"", 
					Utils.compruebaImporte(sIMVSUE.toUpperCase()),
					"", 
					Utils.compruebaImporte(sIMCATA.toUpperCase()),
					"", 
					Utils.compruebaFecha(sFERECA.toUpperCase()));

			logger.debug("sCodMovimiento:|{}|",sCodMovimiento);
			logger.debug("sCodError:|{}|",sCodError);			
			int iSalida = CLErrores.reparaMovimientoReferencia(movimiento,sCodMovimiento, sCodError);
			
			logger.debug("Codigo de salida:"+iSalida);
			
			switch (iSalida) 
			{
			case 0: //Sin errores
				tablaerrores.remove(errorseleccionado);
				sMsg = "El movimiento se ha modificado correctamente.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
				break;

			case -804: //Error 804 - modificacion sin cambios
				sMsg = "ERROR:804 - No hay modificaciones que realizar. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
				break;

			case -900: //Error 900 - al crear un movimiento
				sMsg = "[FATAL] ERROR:900 - Se ha producido un error al registrar el movimiento. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;

			case -904: //Error 904 - error y rollback - error al modificar la cuota
				sMsg = "[FATAL] ERROR:904 - Se ha producido un error al modificar la referencia catastral. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;

			default: //error generico
				msg = Utils.pfmsgFatal("[FATAL] ERROR:"+iSalida+" - La operacion solicitada ha producido un error inesperado. Por favor, revise los datos y avise a soporte.");
				logger.error("[FATAL] ERROR:{} - La operacion solicitada ha producido un error inesperado. Por favor, revise los datos y avise a soporte.",iSalida);
				break;
			}			
		}

		
		
		logger.debug("Finalizadas las comprobaciones.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public String getsCODTRN() {
		return sCODTRN;
	}

	public void setsCODTRN(String sCODTRN) {
		this.sCODTRN = sCODTRN;
	}

	public String getsCOTDOR() {
		return sCOTDOR;
	}

	public void setsCOTDOR(String sCOTDOR) {
		this.sCOTDOR = sCOTDOR;
	}

	public String getsIDPROV() {
		return sIDPROV;
	}

	public void setsIDPROV(String sIDPROV) {
		this.sIDPROV = sIDPROV;
	}

	public String getsCOACCI() {
		return sCOACCI;
	}

	public void setsCOACCI(String sCOACCI) {
		this.sCOACCI = sCOACCI;
	}

	public String getsCOENGP() {
		return sCOENGP;
	}

	public void setsCOENGP(String sCOENGP) {
		this.sCOENGP = sCOENGP;
	}

	public String getsNURCAT() {
		return sNURCAT;
	}

	public void setsNURCAT(String sNURCAT) {
		this.sNURCAT = sNURCAT;
	}

	public String getsTIRCAT() {
		return sTIRCAT;
	}

	public void setsTIRCAT(String sTIRCAT) {
		this.sTIRCAT = sTIRCAT;
	}

	public String getsENEMIS() {
		return sENEMIS;
	}

	public void setsENEMIS(String sENEMIS) {
		this.sENEMIS = sENEMIS;
	}

	public String getsCOTEXA() {
		return sCOTEXA;
	}

	public void setsCOTEXA(String sCOTEXA) {
		this.sCOTEXA = sCOTEXA;
	}

	public String getsOBTEXC() {
		return sOBTEXC;
	}

	public void setsOBTEXC(String sOBTEXC) {
		this.sOBTEXC = sOBTEXC;
	}

	public String getsOBDEER() {
		return sOBDEER;
	}

	public void setsOBDEER(String sOBDEER) {
		this.sOBDEER = sOBDEER;
	}

	public String getsIMVSUE() {
		return sIMVSUE;
	}

	public void setsIMVSUE(String sIMVSUE) {
		this.sIMVSUE = sIMVSUE;
	}

	public String getsIMCATA() {
		return sIMCATA;
	}

	public void setsIMCATA(String sIMCATA) {
		this.sIMCATA = sIMCATA;
	}

	public String getsFERECA() {
		return sFERECA;
	}

	public void setsFERECA(String sFERECA) {
		this.sFERECA = sFERECA;
	}

	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
	}

	public String getsCOPOIN() {
		return sCOPOIN;
	}

	public void setsCOPOIN(String sCOPOIN) {
		this.sCOPOIN = sCOPOIN;
	}

	public String getsNOMUIN() {
		return sNOMUIN;
	}

	public void setsNOMUIN(String sNOMUIN) {
		this.sNOMUIN = sNOMUIN;
	}

	public String getsNOPRAC() {
		return sNOPRAC;
	}

	public void setsNOPRAC(String sNOPRAC) {
		this.sNOPRAC = sNOPRAC;
	}

	public String getsNOVIAS() {
		return sNOVIAS;
	}

	public void setsNOVIAS(String sNOVIAS) {
		this.sNOVIAS = sNOVIAS;
	}

	public String getsNUPIAC() {
		return sNUPIAC;
	}

	public void setsNUPIAC(String sNUPIAC) {
		this.sNUPIAC = sNUPIAC;
	}

	public String getsNUPOAC() {
		return sNUPOAC;
	}

	public void setsNUPOAC(String sNUPOAC) {
		this.sNUPOAC = sNUPOAC;
	}

	public String getsNUPUAC() {
		return sNUPUAC;
	}

	public void setsNUPUAC(String sNUPUAC) {
		this.sNUPUAC = sNUPUAC;
	}
	
	public String getsCOACESB() {
		return sCOACESB;
	}

	public void setsCOACESB(String sCOACESB) {
		this.sCOACESB = sCOACESB;
	}

	public String getsNURCATB() {
		return sNURCATB;
	}

	public void setsNURCATB(String sNURCATB) {
		this.sNURCATB = sNURCATB;
	}

	public String getsCodMovimiento() {
		return sCodMovimiento;
	}

	public void setsCodMovimiento(String sCodMovimiento) {
		this.sCodMovimiento = sCodMovimiento;
	}

	public String getsCodError() {
		return sCodError;
	}

	public void setsCodError(String sCodError) {
		this.sCodError = sCodError;
	}

	public boolean isbRNURCAT() {
		return bRNURCAT;
	}

	public void setbRNURCAT(boolean bRNURCAT) {
		this.bRNURCAT = bRNURCAT;
	}

	public boolean isbRTIRCAT() {
		return bRTIRCAT;
	}

	public void setbRTIRCAT(boolean bRTIRCAT) {
		this.bRTIRCAT = bRTIRCAT;
	}

	public boolean isbRENEMIS() {
		return bRENEMIS;
	}

	public void setbRENEMIS(boolean bRENEMIS) {
		this.bRENEMIS = bRENEMIS;
	}

	public boolean isbROBTEXC() {
		return bROBTEXC;
	}

	public void setbROBTEXC(boolean bROBTEXC) {
		this.bROBTEXC = bROBTEXC;
	}

	public boolean isbRIMVSUE() {
		return bRIMVSUE;
	}

	public void setbRIMVSUE(boolean bRIMVSUE) {
		this.bRIMVSUE = bRIMVSUE;
	}

	public boolean isbRIMCATA() {
		return bRIMCATA;
	}

	public void setbRIMCATA(boolean bRIMCATA) {
		this.bRIMCATA = bRIMCATA;
	}

	public boolean isbRFERECA() {
		return bRFERECA;
	}

	public void setbRFERECA(boolean bRFERECA) {
		this.bRFERECA = bRFERECA;
	}

	public ErrorReferenciaTabla getMovimientoseleccionado() {
		return movimientoseleccionado;
	}

	public void setMovimientoseleccionado(
			ErrorReferenciaTabla movimientoseleccionado) {
		this.movimientoseleccionado = movimientoseleccionado;
	}

	public ArrayList<ErrorReferenciaTabla> getTablareferenciaserror() {
		return tablareferenciaserror;
	}

	public void setTablareferenciaserror(
			ArrayList<ErrorReferenciaTabla> tablareferenciaserror) {
		this.tablareferenciaserror = tablareferenciaserror;
	}

	public ErrorTabla getErrorseleccionado() {
		return errorseleccionado;
	}

	public void setErrorseleccionado(ErrorTabla errorseleccionado) {
		this.errorseleccionado = errorseleccionado;
	}

	public ArrayList<ErrorTabla> getTablaerrores() {
		return tablaerrores;
	}

	public void setTablaerrores(ArrayList<ErrorTabla> tablaerrores) {
		this.tablaerrores = tablaerrores;
	}

	public ArrayList<ActivoTabla> getTablaactivos() {
		return tablaactivos;
	}

	public void setTablaactivos(ArrayList<ActivoTabla> tablaactivos) {
		this.tablaactivos = tablaactivos;
	}

	public ActivoTabla getActivoseleccionado() {
		return activoseleccionado;
	}

	public void setActivoseleccionado(ActivoTabla activoseleccionado) {
		this.activoseleccionado = activoseleccionado;
	}

	public ArrayList<ReferenciaTabla> getTablareferencias() {
		return tablareferencias;
	}

	public void setTablareferencias(ArrayList<ReferenciaTabla> tablareferencias) {
		this.tablareferencias = tablareferencias;
	}

	public ReferenciaTabla getReferenciaseleccionada() {
		return referenciaseleccionada;
	}

	public void setReferenciaseleccionada(ReferenciaTabla referenciaseleccionada) {
		this.referenciaseleccionada = referenciaseleccionada;
	}

	public boolean isbRCOACES() {
		return bRCOACES;
	}

	public void setbRCOACES(boolean bRCOACES) {
		this.bRCOACES = bRCOACES;
	}
}

