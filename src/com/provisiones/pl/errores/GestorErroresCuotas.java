package com.provisiones.pl.errores;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLCuotas;
import com.provisiones.ll.CLErrores;

import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

import com.provisiones.types.errores.ErrorCuotaTabla;
import com.provisiones.types.errores.ErrorTabla;
import com.provisiones.types.movimientos.MovimientoCuota;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.CuotaTabla;

public class GestorErroresCuotas implements Serializable 
{
	private static final long serialVersionUID = -4267610637904788595L;

	private static Logger logger = LoggerFactory.getLogger(GestorErroresCuotas.class.getName());
	
	private String sCODTRN = ValoresDefecto.DEF_E2_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOACCI = "";
	private String sCOENGP = ValoresDefecto.DEF_COENGP;

	private String sCOACES = "";
	private boolean bRCOACES = true;
	
	private String sCOCLDO = "";
	private String sDesCOCLDO = "";
	private boolean bRCOCLDO = true;
	
	private String sNUDCOM = "";
	private boolean bRNUDCOM = true;
	private String sNOMCOC = "";
	private boolean bRNOMCOC = true;
	private String sNODCCO = "";
	private boolean bRNODCCO = true;
	

	private String sCOSBAC = "";
	private String sDesCOSBAC = "";
	private boolean bRCOSBAC = true;
	
	private String sFIPAGO = "";
	private boolean bRFIPAGO = true;
	private String sFFPAGO = "";
	private boolean bRFFPAGO = true;
	private String sIMCUCO = "";
	private boolean bRIMCUCO = true;
	private String sFAACTA = "";
	private boolean bRFAACTA = true;
	
	private String sPTPAGO = "";
	private String sDesPTPAGO = "";
	private boolean bRPTPAGO = true;
	
	private String sOBTEXC = "";
	private boolean bROBTEXC = true;
	
	private String sOBDEER = "";
	
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
	private String sCOCLDOB = "";
	private String sNUDCOMB = "";
	private String sCOSBACB = "";
	
	
	private transient ErrorCuotaTabla movimientoseleccionado = null;
	private transient ArrayList<ErrorCuotaTabla> tablacuotaserror = null;
	

	private transient ErrorTabla errorseleccionado = null;
	private transient ArrayList<ErrorTabla> tablaerrores = null;
	
	
	private transient ActivoTabla activoseleccionado = null;
	private transient ArrayList<ActivoTabla> tablaactivos = null;

	private transient CuotaTabla cuotaseleccionada = null;
	private transient ArrayList<CuotaTabla> tablacuotas = null;

	
	public GestorErroresCuotas()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorErroresCuotas...");	
		}
	}
	
    public void borrarPlantillaError() 
    {  
    	this.sCOACESB = "";
    	this.sCOCLDOB = "";
    	this.sNUDCOMB = "";
    	this.sCOSBACB = "";

    	this.movimientoseleccionado = null;
    	this.tablacuotaserror = null;
    	
    	this.errorseleccionado = null;
    	this.tablaerrores = null;
    	
    	this.sCodMovimiento ="";
    	this.sCodError = "";
   	
    }
	
    public void limpiarPlantillaError(ActionEvent actionEvent) 
    {  
    	borrarPlantillaError();
   	
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
	}
	
	public void borrarResultadosActivo()
	{
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";

    	borrarCamposActivo();
    	borrarResultadosActivo();
    }

	public void borrarCamposCuota()
	{
		this.sCOCLDO = "";
		this.sDesCOCLDO = "";
		this.sNUDCOM = "";
		this.sCOSBAC = "";
		this.sDesCOSBAC = "";
		this.sFIPAGO = "";
		this.sFFPAGO = "";
		this.sIMCUCO = "";
		this.sFAACTA = "";
		this.sPTPAGO = "";
		this.sDesPTPAGO = "";
		this.sOBTEXC = "";
	}
	
	public void borrarResultadosCuota()
	{
    	this.cuotaseleccionada = null;
    	this.tablacuotas = null;
	}
	
	public void limpiarPlantillaCuotas(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";

    	borrarCamposCuota();
    	borrarResultadosCuota();
    	borrarPlantillaError();
    }
    
    public boolean editarError(int iCodError) 
    {  
    	boolean bSalida = false;
  	
		switch (iCodError) 
		{
		/*case 2://prueba
			this.bRCOACES = true;
			this.bRCOCLDO = true;
			this.bRCOSBAC = true;
			this.bRFAACTA = false;
			this.bRFIPAGO = true;
			this.bRFFPAGO = true;
			this.bRIMCUCO = true;
			this.bRNODCCO = true;
			this.bRNOMCOC = true;
			this.bRNUDCOM = true;
			this.bROBTEXC = true;
			this.bRPTPAGO = true;
			bSalida = true;
			break;*/
		default://error no recuperable
			this.bRCOACES = true;
			this.bRCOCLDO = true;
			this.bRCOSBAC = true;
			this.bRFAACTA = true;
			this.bRFIPAGO = true;
			this.bRFFPAGO = true;
			this.bRIMCUCO = true;
			this.bRNODCCO = true;
			this.bRNOMCOC = true;
			this.bRNUDCOM = true;
			this.bROBTEXC = true;
			this.bRPTPAGO = true;
			bSalida = false;
			break;
		}
		
		return bSalida;
   	
    }
	
	public void buscaCuotasError(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			logger.debug("Buscando Cuotas con errores...");
			
			ErrorCuotaTabla filtro = new ErrorCuotaTabla(
						sCOACESB.toUpperCase(), sCOCLDOB, "", sNUDCOMB.toUpperCase(),sCOSBACB, "",
						"", "");

			this.setTablacuotaserror(CLErrores.buscarCuotasConErrores(filtro));
			
			sMsg = "Encontradas "+getTablacuotaserror().size()+" Cuotas relacionadas.";
			msg = Utils.pfmsgInfo(sMsg);
			logger.debug(sMsg);
			
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
	    	
			this.setTablaerrores(CLErrores.buscarErroresCuota(Integer.parseInt(sCodMovimiento)));
			
			sMsg = "Encontrados "+getTablaerrores().size()+" errores relacionados.";
			msg = Utils.pfmsgInfo(sMsg);
			logger.debug(sMsg);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
			MovimientoCuota movimiento = CLCuotas.buscarMovimientoCuota(Integer.parseInt(sCodMovimiento));
			
			this.sCOACCI = movimiento.getCOACCI();
			this.sCOACES = movimiento.getCOACES();
	    	this.sCOCLDO = movimiento.getCOCLDO(); 
	    	this.sNUDCOM = movimiento.getNUDCOM();
	    	this.sCOSBAC = movimiento.getCOSBAC();
	    	this.sFIPAGO = Utils.recuperaFecha(movimiento.getFIPAGO());
	    	this.sFFPAGO = Utils.recuperaFecha(movimiento.getFFPAGO());
	    	this.sIMCUCO = Utils.recuperaImporte(false,movimiento.getIMCUCO());
	    	this.sFAACTA = Utils.recuperaFecha(movimiento.getFAACTA());
	    	this.sPTPAGO = movimiento.getPTPAGO();
	    	this.sOBTEXC = movimiento.getOBTEXC();
					
	    	sMsg = "Errores de Cuota cargados.";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	logger.debug(sMsg);
			
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
			
			this.setTablaactivos(CLCuotas.buscarActivosConCuotas(buscaactivos));
			
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
			
			String sMsg = "";
	    	
	    	this.sCOACES  = activoseleccionado.getCOACES();
	    	
	    	sMsg = "Activo '"+ sCOACES +"' Seleccionado.";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	logger.info(sMsg);
	    	
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void cargarCuotas(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";

			try
			{
				this.tablacuotas = CLCuotas.buscarCuotasActivo(Integer.parseInt(sCOACES));
				
				sMsg = "Encontradas "+getTablacuotas().size()+" cuotas relacionadas.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
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
	
	public void seleccionarCuota(ActionEvent actionEvent) 
    {
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	String sMsg = "";
	    	
	    	this.sCOCLDO = cuotaseleccionada.getCOCLDO(); 
	    	this.sDesCOCLDO = cuotaseleccionada.getDCOCLDO();
	    	this.sNUDCOM = cuotaseleccionada.getNUDCOM();
	    	this.sCOSBAC = cuotaseleccionada.getCOSBAC();
	    	this.sDesCOSBAC = cuotaseleccionada.getDCOSBAC();
	    	this.sFIPAGO = cuotaseleccionada.getFIPAGO();
	    	this.sFFPAGO = cuotaseleccionada.getFFPAGO();
	    	this.sIMCUCO = cuotaseleccionada.getIMCUCO();
	    	this.sFAACTA = cuotaseleccionada.getFAACTA();
	    	this.sPTPAGO = cuotaseleccionada.getPTPAGO();
	    	this.sDesPTPAGO = cuotaseleccionada.getDPTPAGO();
	    	this.sOBTEXC = cuotaseleccionada.getOBTEXC();
	    	
	    	sMsg = "Cuota de '"+ sDesCOSBAC +"' Seleccionada.";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	logger.info(sMsg);

	    	FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void hoyFIPAGO (ActionEvent actionEvent)
	{
		this.setsFIPAGO(Utils.fechaDeHoy(true));
		logger.debug("sFIPAGO:|{}|",sFIPAGO);
	}

	public void hoyFFPAGO (ActionEvent actionEvent)
	{
		this.setsFFPAGO(Utils.fechaDeHoy(true));
		logger.debug("sFFPAGO:|{}|",sFFPAGO);
	}
	
	public void hoyFAACTA (ActionEvent actionEvent)
	{
		this.setsFAACTA(Utils.fechaDeHoy(true));
		logger.debug("sFAACTA:|{}|",sFAACTA);
	}
	
	public void registraDatos(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			if (!CLCuotas.existeMovimientoCuota(Integer.parseInt(sCodMovimiento)))
			{
				sMsg = "[FATAL] ERROR:911 - No se puede modificar la cuota, no existe el movimiento. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
			}
			else
			{
				MovimientoCuota movimiento = new MovimientoCuota (
						sCODTRN.toUpperCase(), 
						sCOTDOR.toUpperCase(), 
						sIDPROV.toUpperCase(), 
						sCOACCI.toUpperCase(), 
						sCOCLDO.toUpperCase(), 
						sNUDCOM.toUpperCase(), 
						sCOENGP.toUpperCase(), 
						sCOACES, 
						ValoresDefecto.DEF_COGRUG_E2, 
						ValoresDefecto.DEF_COTACA_E2, 
						Utils.compruebaCodigoPago(false,sCOSBAC.toUpperCase()), 
						"", 
						Utils.compruebaFecha(sFIPAGO.toUpperCase()), 
						"", 
						Utils.compruebaFecha(sFFPAGO.toUpperCase()), 
						"", 
						Utils.compruebaImporte(sIMCUCO.toUpperCase()), 
						"", 
						Utils.compruebaFecha(sFAACTA.toUpperCase()), 
						"", 
						sPTPAGO.toUpperCase(),
						"", 
						sOBTEXC.toUpperCase(), 
						sOBDEER.toUpperCase());
				
				
				logger.debug("sCodMovimiento:|{}|",sCodMovimiento);
				logger.debug("sCodError:|{}|",sCodError);
				int iSalida = CLErrores.reparaMovimientoCuota(movimiento,Integer.parseInt(sCodMovimiento), sCodError);
				
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
					sMsg = "[FATAL] ERROR:904 - Se ha producido un error al modificar la cuota. Por favor, revise los datos y avise a soporte.";
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

	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
	}

	public String getsCOCLDO() {
		return sCOCLDO;
	}

	public void setsCOCLDO(String sCOCLDO) {
		this.sCOCLDO = sCOCLDO;
	}

	public String getsDesCOCLDO() {
		return sDesCOCLDO;
	}

	public void setsDesCOCLDO(String sDesCOCLDO) {
		this.sDesCOCLDO = sDesCOCLDO;
	}

	public String getsNUDCOM() {
		return sNUDCOM;
	}

	public void setsNUDCOM(String sNUDCOM) {
		this.sNUDCOM = sNUDCOM;
	}

	public String getsNOMCOC() {
		return sNOMCOC;
	}

	public void setsNOMCOC(String sNOMCOC) {
		this.sNOMCOC = sNOMCOC;
	}

	public String getsNODCCO() {
		return sNODCCO;
	}

	public void setsNODCCO(String sNODCCO) {
		this.sNODCCO = sNODCCO;
	}

	public String getsCOSBAC() {
		return sCOSBAC;
	}

	public void setsCOSBAC(String sCOSBAC) {
		this.sCOSBAC = sCOSBAC;
	}

	public String getsDesCOSBAC() {
		return sDesCOSBAC;
	}

	public void setsDesCOSBAC(String sDesCOSBAC) {
		this.sDesCOSBAC = sDesCOSBAC;
	}

	public String getsFIPAGO() {
		return sFIPAGO;
	}

	public void setsFIPAGO(String sFIPAGO) {
		this.sFIPAGO = sFIPAGO;
	}

	public String getsFFPAGO() {
		return sFFPAGO;
	}

	public void setsFFPAGO(String sFFPAGO) {
		this.sFFPAGO = sFFPAGO;
	}

	public String getsIMCUCO() {
		return sIMCUCO;
	}

	public void setsIMCUCO(String sIMCUCO) {
		this.sIMCUCO = sIMCUCO;
	}

	public String getsFAACTA() {
		return sFAACTA;
	}

	public void setsFAACTA(String sFAACTA) {
		this.sFAACTA = sFAACTA;
	}

	public String getsPTPAGO() {
		return sPTPAGO;
	}

	public void setsPTPAGO(String sPTPAGO) {
		this.sPTPAGO = sPTPAGO;
	}

	public String getsDesPTPAGO() {
		return sDesPTPAGO;
	}

	public void setsDesPTPAGO(String sDesPTPAGO) {
		this.sDesPTPAGO = sDesPTPAGO;
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

	public String getsCOCLDOB() {
		return sCOCLDOB;
	}

	public void setsCOCLDOB(String sCOCLDOB) {
		this.sCOCLDOB = sCOCLDOB;
	}

	public String getsNUDCOMB() {
		return sNUDCOMB;
	}

	public void setsNUDCOMB(String sNUDCOMB) {
		this.sNUDCOMB = sNUDCOMB;
	}

	public String getsCOSBACB() {
		return sCOSBACB;
	}

	public void setsCOSBACB(String sCOSBACB) {
		this.sCOSBACB = sCOSBACB;
	}

	public boolean isbRCOACES() {
		return bRCOACES;
	}

	public void setbRCOACES(boolean bRCOACES) {
		this.bRCOACES = bRCOACES;
	}

	public boolean isbRCOCLDO() {
		return bRCOCLDO;
	}

	public void setbRCOCLDO(boolean bRCOCLDO) {
		this.bRCOCLDO = bRCOCLDO;
	}

	public boolean isbRNUDCOM() {
		return bRNUDCOM;
	}

	public void setbRNUDCOM(boolean bRNUDCOM) {
		this.bRNUDCOM = bRNUDCOM;
	}

	public boolean isbRNOMCOC() {
		return bRNOMCOC;
	}

	public void setbRNOMCOC(boolean bRNOMCOC) {
		this.bRNOMCOC = bRNOMCOC;
	}

	public boolean isbRNODCCO() {
		return bRNODCCO;
	}

	public void setbRNODCCO(boolean bRNODCCO) {
		this.bRNODCCO = bRNODCCO;
	}

	public boolean isbRCOSBAC() {
		return bRCOSBAC;
	}

	public void setbRCOSBAC(boolean bRCOSBAC) {
		this.bRCOSBAC = bRCOSBAC;
	}

	public boolean isbRFIPAGO() {
		return bRFIPAGO;
	}

	public void setbRFIPAGO(boolean bRFIPAGO) {
		this.bRFIPAGO = bRFIPAGO;
	}

	public boolean isbRFFPAGO() {
		return bRFFPAGO;
	}

	public void setbRFFPAGO(boolean bRFFPAGO) {
		this.bRFFPAGO = bRFFPAGO;
	}

	public boolean isbRIMCUCO() {
		return bRIMCUCO;
	}

	public void setbRIMCUCO(boolean bRIMCUCO) {
		this.bRIMCUCO = bRIMCUCO;
	}

	public boolean isbRFAACTA() {
		return bRFAACTA;
	}

	public void setbRFAACTA(boolean bRFAACTA) {
		this.bRFAACTA = bRFAACTA;
	}

	public boolean isbRPTPAGO() {
		return bRPTPAGO;
	}

	public void setbRPTPAGO(boolean bRPTPAGO) {
		this.bRPTPAGO = bRPTPAGO;
	}

	public boolean isbROBTEXC() {
		return bROBTEXC;
	}

	public void setbROBTEXC(boolean bROBTEXC) {
		this.bROBTEXC = bROBTEXC;
	}

	public ErrorCuotaTabla getMovimientoseleccionado() {
		return movimientoseleccionado;
	}

	public void setMovimientoseleccionado(ErrorCuotaTabla movimientoseleccionado) {
		this.movimientoseleccionado = movimientoseleccionado;
	}

	public ArrayList<ErrorCuotaTabla> getTablacuotaserror() {
		return tablacuotaserror;
	}

	public void setTablacuotaserror(ArrayList<ErrorCuotaTabla> tablacuotaserror) {
		this.tablacuotaserror = tablacuotaserror;
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

	public CuotaTabla getCuotaseleccionada() {
		return cuotaseleccionada;
	}

	public void setCuotaseleccionada(CuotaTabla cuotaseleccionada) {
		this.cuotaseleccionada = cuotaseleccionada;
	}

	public ArrayList<CuotaTabla> getTablacuotas() {
		return tablacuotas;
	}

	public void setTablacuotas(ArrayList<CuotaTabla> tablacuotas) {
		this.tablacuotas = tablacuotas;
	}
}
