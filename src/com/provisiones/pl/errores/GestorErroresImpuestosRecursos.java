package com.provisiones.pl.errores;

import java.io.Serializable;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLErrores;
import com.provisiones.ll.CLImpuestos;
import com.provisiones.ll.CLReferencias;

import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

import com.provisiones.types.errores.ErrorImpuestoTabla;
import com.provisiones.types.errores.ErrorTabla;
import com.provisiones.types.movimientos.MovimientoImpuestoRecurso;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.ImpuestoRecursoTabla;

public class GestorErroresImpuestosRecursos implements Serializable 
{

	private static final long serialVersionUID = -5050709383362146069L;
	
	private static Logger logger = LoggerFactory.getLogger(GestorErroresImpuestosRecursos.class.getName());

	private String sCODTRN = ValoresDefecto.DEF_E4_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOACCI = "";
	private String sCOENGP = ValoresDefecto.DEF_COENGP;

	private String sNURCAT = "";
	private boolean bRNURCAT = true;

	private String sCOSBAC = "";
	private boolean bRCOSBAC = true;
	private String sFEPRRE = "";
	private boolean bRFEPRRE = true;
	private String sFERERE = "";
	private boolean bRFERERE = true;
	private String sFEDEIN = "";
	private boolean bRFEDEIN = true;
	private String sBISODE = "";
	private boolean bRBISODE = true;
	private String sBIRESO = "";
	private boolean bRBIRESO = true;
	private String sCOTEXA = ValoresDefecto.DEF_COTEXA;
	private String sOBTEXC = "";
	private boolean bROBTEXC = true;
	
	private String sDesCOSBAC = "";
	private String sDesBISODE = "";
	private String sDesBIRESO = "";
	
	private String sOBDEER = "";
	
	//Buscar activos
	private String sCOACES = "";
	private boolean bRCOACES = true;

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
	private String sCOSBACB = "";
	
	private transient ErrorImpuestoTabla movimientoseleccionado = null;
	private transient ArrayList<ErrorImpuestoTabla> tablaimpuestoserror = null;
	
	private transient ErrorTabla errorseleccionado = null;
	private transient ArrayList<ErrorTabla> tablaerrores = null;
	
	private transient ArrayList<ActivoTabla> tablaactivos = null;
	private transient ActivoTabla activoseleccionado = null;
	
	private transient ArrayList<ImpuestoRecursoTabla> tablaimpuestos = null;
	private transient ImpuestoRecursoTabla impuestoseleccionado = null;
	

	public GestorErroresImpuestosRecursos()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorErroresImpuestosRecursos...");	
		}
	}
	
    public void borrarPlantillaError() 
    {  
    	this.sCOACESB = "";
    	this.sNURCATB = "";
    	this.sCOSBACB = "";


    	this.movimientoseleccionado = null;
    	this.tablaimpuestoserror = null;
    	
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
    
	public void borrarPlantillaImpuesto()
	{
		
        this.sNURCAT = "";
        this.sCOSBAC = "";
        this.sFEPRRE = "";
        this.sFERERE = "";
        this.sFEDEIN = "";
        this.sBISODE = "";
        this.sBIRESO = "";
        this.sOBTEXC = "";
        
        this.sDesCOSBAC = "";
        this.sDesBISODE = "";
        this.sDesBIRESO = "";
	}
	
	public void borrarResultadosImpuesto()
	{
    	this.impuestoseleccionado = null;
    	this.tablaimpuestos = null;
	}
    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";

    	borrarPlantillaImpuesto();
    	
    	borrarResultadosActivo();
    	borrarResultadosImpuesto();
    	borrarPlantillaError();
   	
    }
    
    public boolean editarError(int iCodError) 
    {  
    	boolean bSalida = false;
  	
		switch (iCodError) 
		{
		/*case 2://prueba
			this.bRBIRESO = true;
			this.bRBISODE = true;
			this.bRCOACES = true;
			this.bRCOSBAC = true;
			this.bRFEDEIN = true;
			this.bRFEPRRE = false;
			this.bRFERERE = true;
			this.bROBTEXC = true;
			this.bRNURCAT = true;
			bSalida = true;
			break;*/
		default://error no recuperable
			this.bRBIRESO = true;
			this.bRBISODE = true;
			this.bRCOACES = true;
			this.bRCOSBAC = true;
			this.bRFEDEIN = true;
			this.bRFEPRRE = true;
			this.bRFERERE = true;
			this.bROBTEXC = true;
			this.bRNURCAT = true;
			bSalida = false;
			break;
		}
		
		return bSalida;
   	
    }
	
	public void buscaImpuestosError(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			logger.debug("Buscando Impuestos y Recursos con errores...");
			
			ErrorImpuestoTabla filtro = new ErrorImpuestoTabla(
					sCOACESB.toUpperCase(), sNURCATB.toUpperCase(), sCOSBACB.toUpperCase(), "",
					"", "");

			this.setTablaimpuestoserror(CLErrores.buscarImpuestosConErrores(filtro));
			
			sMsg = "Encontrados "+getTablaimpuestoserror().size()+" Impuestos y Recursos relacionados.";
			msg = Utils.pfmsgInfo(sMsg);
			logger.info(sMsg);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void seleccionarMovimiento(ActionEvent actionEvent) 
    {
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.sCodMovimiento = movimientoseleccionado.getMOVIMIENTO(); 
	    	
			this.setTablaerrores(CLErrores.buscarErroresImpuesto(sCodMovimiento));
			
			sMsg = "Encontrados "+getTablaerrores().size()+" errores relacionados.";
			msg = Utils.pfmsgInfo(sMsg);
			logger.debug(sMsg);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
			MovimientoImpuestoRecurso movimiento = CLImpuestos.buscarMovimientoImpuestoRecurso(sCodMovimiento);
			
			this.sCOACCI = movimiento.getCOACCI();
			this.sCOACES = movimiento.getCOACES();
			
			this.sNURCAT = movimiento.getNURCAT();
	    	this.sCOSBAC = movimiento.getCOSBAC();
	    	//this.sDesCOSBAC = movimiento.getDCOSBAC();
	    	this.sFEPRRE = Utils.recuperaFecha(movimiento.getFEPRRE());
	    	this.sFERERE = Utils.recuperaFecha(movimiento.getFERERE());
	    	this.sFEDEIN = Utils.recuperaFecha(movimiento.getFEDEIN());
	    	this.sBISODE = movimiento.getBISODE();
	    	//this.sDesBISODE = movimiento.getDBISODE();
	    	this.sBIRESO = movimiento.getBIRESO();
	    	this.sDesBIRESO = movimiento.getBIRESO();
	    	this.sOBTEXC = movimiento.getOBTEXC();
			
	    	sMsg = "Errores de Impuestos y Recursos cargados.";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	logger.info(sMsg);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void seleccionarError(ActionEvent actionEvent) 
    {
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
	    	
			this.sCodError = errorseleccionado.getsCodError(); 
			
	    	int iCodError  = Integer.parseInt(sCodError);
	    	
	    	logger.debug("Error seleccionado:|"+iCodError+"|");
	    	
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
    }

	
	public void buscaActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			ActivoTabla buscaactivos = new ActivoTabla(
					"", sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
					sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
					sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(), "");
			
			this.setTablaactivos(CLImpuestos.buscarActivosConImpuestos(buscaactivos));
			
			sMsg = "Encontrados "+getTablaactivos().size()+" activos relacionados.";
			msg = Utils.pfmsgInfo(sMsg);
			logger.info(sMsg);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void seleccionarActivo(ActionEvent actionEvent) 
    {  
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;

	    	this.sCOACES  = activoseleccionado.getCOACES();
	    	
	    	this.sNURCAT  = CLReferencias.referenciaCatastralAsociada(Integer.parseInt(sCOACES));
	    	
	    	if (sNURCAT.equals("") || !CLReferencias.estadoReferencia(sNURCAT).equals("A"))
	    	{
	    		msg = Utils.pfmsgError("La referencia catastral seleccionada no esta de alta.");
	    		logger.error("La referencia catastral seleccionada no esta de alta.");

	    		this.sNURCAT  = "";
	    	}
	    	else
	    	{
	    		msg = Utils.pfmsgInfo("Referencia "+ sNURCAT +" cargada.");
	    		logger.info("Referencia {} cargada.",sNURCAT);
	    	}

			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
    
	public void cargarImpuestos(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			try
			{
		    	this.sNURCAT  = CLReferencias.referenciaCatastralAsociada(Integer.parseInt(sCOACES));
		    	
		    	if (!sNURCAT.equals("") && CLReferencias.estadoReferencia(sNURCAT).equals("A") )
				{
		    		this.tablaimpuestos = CLImpuestos.buscarImpuestosActivos(Integer.parseInt(sCOACES));
		    		
		    		sMsg = "Encontrados "+getTablaimpuestos().size()+" impuestos relacionados.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
				}
		    	else
		    	{
		    		sMsg = "ERROR: No existe referencia catastral de alta para el activo consultado.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
		        }
			}
			catch(NumberFormatException nfe)
			{
				sMsg = "ERROR: El activo debe ser numérico. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			
	    	FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}
	
	public void seleccionarImpuesto(ActionEvent actionEvent) 
    {
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	String sMsg = "";
	    	
	    	this.sCOSBAC = impuestoseleccionado.getCOSBAC();
	    	this.sDesCOSBAC = impuestoseleccionado.getDCOSBAC();
	    	this.sFEPRRE = impuestoseleccionado.getFEPRRE();
	    	this.sFERERE = impuestoseleccionado.getFERERE();
	    	this.sFEDEIN = impuestoseleccionado.getFEDEIN();
	    	this.sBISODE = impuestoseleccionado.getBISODE();
	    	this.sDesBISODE = impuestoseleccionado.getDBISODE();
	    	this.sBIRESO = impuestoseleccionado.getBIRESO();
	    	this.sDesBIRESO = impuestoseleccionado.getBIRESO();
	    	this.sOBTEXC = impuestoseleccionado.getOBTEXC();
	    	
	    	sMsg = "Recurso de '"+sDesCOSBAC +"' Seleccionado.";
			msg = Utils.pfmsgInfo(sMsg);
			logger.info(sMsg);

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void hoyFEPRRE (ActionEvent actionEvent)
	{
		this.setsFEPRRE(Utils.fechaDeHoy(true));
		logger.debug("sFEPRRE:|{}|",sFEPRRE);
	}
	
	public void hoyFERERE (ActionEvent actionEvent)
	{
		this.setsFERERE(Utils.fechaDeHoy(true));
		logger.debug("sFERERE:|{}|",sFERERE);
	}
	
	public void hoyFEDEIN (ActionEvent actionEvent)
	{
		this.setsFEDEIN(Utils.fechaDeHoy(true));
		logger.debug("sFEDEIN:|{}|",sFEDEIN);
	}
    
	public void registraDatos(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			if (!CLImpuestos.existeMovimientoImpuestoRecurso(sCodMovimiento))
			{
				sMsg = "[FATAL] ERROR:911 - No se puede modificar el Impuesto, no existe el movimiento. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else
			{
				MovimientoImpuestoRecurso movimiento = new MovimientoImpuestoRecurso (
						sCODTRN.toUpperCase(), 
						sCOTDOR.toUpperCase(), 
						sIDPROV.toUpperCase(), 
						sCOACCI.toUpperCase(), 
						sCOENGP.toUpperCase(), 
						sCOACES,
						sNURCAT.toUpperCase(),
						ValoresDefecto.DEF_COGRUG_E4, 
						ValoresDefecto.DEF_COTACA_E4, 
						sCOSBAC.toUpperCase(),
						"", 
						Utils.compruebaFecha(sFEPRRE.toUpperCase()),
						"", 
						Utils.compruebaFecha(sFERERE.toUpperCase()),
						"", 
						Utils.compruebaFecha(sFEDEIN.toUpperCase()),
						"", 
						Utils.compruebaCodigoAlfa(sBISODE.toUpperCase()),
						"", 
						Utils.compruebaCodigoAlfa(sBIRESO.toUpperCase()),
						sCOTEXA.toUpperCase(),
						"", 
						sOBTEXC.toUpperCase(), 
						sOBDEER.toUpperCase());
				
				logger.debug("sCodMovimiento:|"+sCodMovimiento+"|");
				logger.debug("sCodError:|"+sCodError+"|");			

				int iSalida = CLErrores.reparaMovimientoImpuesto(movimiento,sCodMovimiento, sCodError);
				
				logger.debug("Codigo de salida:"+iSalida);
				
				switch (iSalida) 
				{
				case 0: //Sin errores
					tablaerrores.remove(errorseleccionado);
					sMsg = "El movimiento se ha registrado correctamente.";
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

				case -904: //Error 904 - error y rollback - error al modificar el impuesto/recurso
					sMsg = "[FATAL] ERROR:904 - Se ha producido un error al modificar el impuesto o recurso. Por favor, revise los datos y avise a soporte.";
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					break;

				default: //error generico
					msg = Utils.pfmsgFatal("[FATAL] ERROR:"+iSalida+" - La operacion solicitada ha producido un error inesperado. Por favor, revise los datos y avise a soporte.");
					logger.error("[FATAL] ERROR{} - La operacion solicitada ha producido un error inesperado. Por favor, revise los datos y avise a soporte.",iSalida);
					break;
				}
			}

			logger.debug("Finalizadas las comprobaciones.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
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

	public String getsCOSBAC() {
		return sCOSBAC;
	}

	public void setsCOSBAC(String sCOSBAC) {
		this.sCOSBAC = sCOSBAC;
	}

	public String getsFEPRRE() {
		return sFEPRRE;
	}

	public void setsFEPRRE(String sFEPRRE) {
		this.sFEPRRE = sFEPRRE;
	}

	public String getsFERERE() {
		return sFERERE;
	}

	public void setsFERERE(String sFERERE) {
		this.sFERERE = sFERERE;
	}

	public String getsFEDEIN() {
		return sFEDEIN;
	}

	public void setsFEDEIN(String sFEDEIN) {
		this.sFEDEIN = sFEDEIN;
	}

	public String getsBISODE() {
		return sBISODE;
	}

	public void setsBISODE(String sBISODE) {
		this.sBISODE = sBISODE;
	}

	public String getsBIRESO() {
		return sBIRESO;
	}

	public void setsBIRESO(String sBIRESO) {
		this.sBIRESO = sBIRESO;
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

	public String getsDesCOSBAC() {
		return sDesCOSBAC;
	}

	public void setsDesCOSBAC(String sDesCOSBAC) {
		this.sDesCOSBAC = sDesCOSBAC;
	}

	public String getsDesBISODE() {
		return sDesBISODE;
	}

	public void setsDesBISODE(String sDesBISODE) {
		this.sDesBISODE = sDesBISODE;
	}

	public String getsDesBIRESO() {
		return sDesBIRESO;
	}

	public void setsDesBIRESO(String sDesBIRESO) {
		this.sDesBIRESO = sDesBIRESO;
	}

	public String getsOBDEER() {
		return sOBDEER;
	}

	public void setsOBDEER(String sOBDEER) {
		this.sOBDEER = sOBDEER;
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

	public String getsCOSBACB() {
		return sCOSBACB;
	}

	public void setsCOSBACB(String sCOSBACB) {
		this.sCOSBACB = sCOSBACB;
	}

	public boolean isbRNURCAT() {
		return bRNURCAT;
	}

	public void setbRNURCAT(boolean bRNURCAT) {
		this.bRNURCAT = bRNURCAT;
	}

	public boolean isbRCOSBAC() {
		return bRCOSBAC;
	}

	public void setbRCOSBAC(boolean bRCOSBAC) {
		this.bRCOSBAC = bRCOSBAC;
	}

	public boolean isbRFEPRRE() {
		return bRFEPRRE;
	}

	public void setbRFEPRRE(boolean bRFEPRRE) {
		this.bRFEPRRE = bRFEPRRE;
	}

	public boolean isbRFERERE() {
		return bRFERERE;
	}

	public void setbRFERERE(boolean bRFERERE) {
		this.bRFERERE = bRFERERE;
	}

	public boolean isbRFEDEIN() {
		return bRFEDEIN;
	}

	public void setbRFEDEIN(boolean bRFEDEIN) {
		this.bRFEDEIN = bRFEDEIN;
	}

	public boolean isbRBISODE() {
		return bRBISODE;
	}

	public void setbRBISODE(boolean bRBISODE) {
		this.bRBISODE = bRBISODE;
	}

	public boolean isbRBIRESO() {
		return bRBIRESO;
	}

	public void setbRBIRESO(boolean bRBIRESO) {
		this.bRBIRESO = bRBIRESO;
	}

	public boolean isbROBTEXC() {
		return bROBTEXC;
	}

	public void setbROBTEXC(boolean bROBTEXC) {
		this.bROBTEXC = bROBTEXC;
	}

	public boolean isbRCOACES() {
		return bRCOACES;
	}

	public void setbRCOACES(boolean bRCOACES) {
		this.bRCOACES = bRCOACES;
	}

	public ErrorImpuestoTabla getMovimientoseleccionado() {
		return movimientoseleccionado;
	}

	public void setMovimientoseleccionado(ErrorImpuestoTabla movimientoseleccionado) {
		this.movimientoseleccionado = movimientoseleccionado;
	}

	public ArrayList<ErrorImpuestoTabla> getTablaimpuestoserror() {
		return tablaimpuestoserror;
	}

	public void setTablaimpuestoserror(
			ArrayList<ErrorImpuestoTabla> tablaimpuestoserror) {
		this.tablaimpuestoserror = tablaimpuestoserror;
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

	public ArrayList<ImpuestoRecursoTabla> getTablaimpuestos() {
		return tablaimpuestos;
	}

	public void setTablaimpuestos(ArrayList<ImpuestoRecursoTabla> tablaimpuestos) {
		this.tablaimpuestos = tablaimpuestos;
	}

	public ImpuestoRecursoTabla getImpuestoseleccionado() {
		return impuestoseleccionado;
	}

	public void setImpuestoseleccionado(ImpuestoRecursoTabla impuestoseleccionado) {
		this.impuestoseleccionado = impuestoseleccionado;
	}

	
}
