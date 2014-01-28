package com.provisiones.pl.listas;

import java.io.IOException;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLGastos;
import com.provisiones.ll.CLProvisiones;
import com.provisiones.misc.Sesion;
import com.provisiones.misc.Utils;

import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.ProvisionTabla;

public class GestorListaProvisiones implements Serializable 
{
	private static final long serialVersionUID = 4042070631372501791L;

	private static Logger logger = LoggerFactory.getLogger(GestorListaProvisiones.class.getName());

	private String sNUPROF = "";
	private String sCOSPAT = "";
	private String sDCOSPAT = "";
	private String sTAS = "";
	private String sDTAS = "";
	private String sValorTolal = "";
	private String sNumGastos = "";
	private String sValorAutorizado = "";
	private String sGastosAutorizados = "";	
	private String sFEPFON = "";
	private String sFechaEnvio = "";
	private String sFechaAutorizado = "";
	private String sFechaPagado = "";
	private String sEstado = "";

	
	//Buscar Activos
	private String sCOACESB = "";

	private String sCOPOIN = "";

	private String sNOMUIN = "";
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	
	//Buscar Comunidades
	private String sCOCLDOB = "";
	private String sNUDCOMB = "";
	private String sNOMCOCB = "";
	private String sNODCCOB = "";

	//Fechas
	private String sFEPFONB = "";

	private transient ActivoTabla activoseleccionado = null;
	private transient ArrayList<ActivoTabla> tablaactivos = null;

	private transient ProvisionTabla provisionseleccionada = null;
	private transient ArrayList<ProvisionTabla> tablaprovisiones = null;
	
	
	public GestorListaProvisiones()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorListaProvisiones...");	
		}
	}
	
	public void buscarActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			ActivoTabla filtro = new ActivoTabla(
					"", sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
					sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
					sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(), "");
			
			this.setTablaactivos(CLGastos.buscarActivosConGastosAutorizados(filtro));

			msg = Utils.pfmsgInfo("Encontrados "+getTablaactivos().size()+" activos relacionados.");
			
			logger.info("Encontrados {} activos relacionados.",getTablaactivos().size());
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	public void seleccionarActivo(ActionEvent actionEvent) 
    { 
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	String sMsg = ""; 
	    	
	    	this.sCOACESB  = activoseleccionado.getCOACES();
	    	
	    	sMsg = "Activo '"+sCOACESB+"' seleccionado.";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	
	    	logger.info(sMsg);
	    	
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }

	public void buscaProvisionesAutorizadasPorActivo(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
	    	
			this.tablaprovisiones = CLProvisiones.buscarProvisionesAutorizadasActivo(sCOACESB); 

			msg = Utils.pfmsgInfo("Encontradas "+getTablaprovisiones().size()+" provisiones abiertas.");
			logger.info("Encontradas {} provisiones abiertas.",getTablaprovisiones().size());

			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}
	}
	
	public void buscaProvisionesAutorizadasPorComunidad(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
	    	
			//TODO Implementar busqueda por comunidad
			this.tablaprovisiones = CLProvisiones.buscarProvisionesAbiertas(); 

			msg = Utils.pfmsgInfo("Encontradas "+getTablaprovisiones().size()+" provisiones abiertas.");
			logger.info("Encontradas {} provisiones abiertas.",getTablaprovisiones().size());

			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}
	}
	
	public void buscaProvisionesAutorizadasPorFechaCierre(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
	    	
			this.tablaprovisiones = CLProvisiones.buscarProvisionesAutorizadasFecha(sFEPFONB); 

			msg = Utils.pfmsgInfo("Encontradas "+getTablaprovisiones().size()+" provisiones abiertas.");
			logger.info("Encontradas {} provisiones abiertas.",getTablaprovisiones().size());

			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}
	}
	
	
	public void seleccionarProvision(ActionEvent actionEvent) 
    {
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	this.sNUPROF  = provisionseleccionada.getNUPROF();
	    	this.sCOSPAT  = provisionseleccionada.getCOSPAT();
	    	this.sDCOSPAT  = provisionseleccionada.getDCOSPAT();
	    	this.sTAS  = provisionseleccionada.getTAS();
	    	this.sDTAS  = provisionseleccionada.getDTAS();
	    	this.sValorTolal  = Utils.recuperaImporte(false, provisionseleccionada.getVALOR());//CLProvisiones.calcularValorProvision(sNUPROF);
	    	this.sNumGastos  = provisionseleccionada.getGASTOS();//Long.toString(CLProvisiones.buscarNumeroGastosProvision(sNUPROF));
	    	
	    	msg = Utils.pfmsgInfo("Provision '"+ sNUPROF +"' Seleccionada.");
	    	logger.info("Provision '{}' Seleccionada.",sNUPROF);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}
    }
	
	public void cargarDetalles(ActionEvent actionEvent) 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
			if (provisionseleccionada != null)
			{
				this.sNUPROF  = provisionseleccionada.getNUPROF();
				logger.debug("sNUPROF:|"+sNUPROF+"|");
		    	
		    	Sesion.guardaDetalle(sNUPROF);
		    	Sesion.limpiarHistorial();
		    	Sesion.guardarHistorial("listaprovisiones.xhtml","GestorDetallesProvision");

		    	sPagina = "detallesprovision.xhtml";
			}
			else
			{
				FacesMessage msg;

				msg = Utils.pfmsgWarning("No se ha seleccionado una provisión.");
				
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
			try 
			{
		    	logger.debug("Redirigiendo...");
				FacesContext.getCurrentInstance().getExternalContext().redirect(sPagina);
			}
			catch (IOException e)
			{
				FacesMessage msg;
				
				String sMsg = "ERROR: Ocurrió un problema al acceder a los detalles. Por favor, avise a soporte.";
				
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				
				FacesContext.getCurrentInstance().addMessage(null, msg);
				

			}

		}

		//return sPagina;

    }
	
	public String getsNUPROF() {
		return sNUPROF;
	}

	public void setsNUPROF(String sNUPROF) {
		this.sNUPROF = sNUPROF;
	}

	public String getsFEPFON() {
		return sFEPFON;
	}

	public void setsFEPFON(String sFEPFON) {
		this.sFEPFON = sFEPFON;
	}

	public String getsFechaEnvio() {
		return sFechaEnvio;
	}

	public void setsFechaEnvio(String sFechaEnvio) {
		this.sFechaEnvio = sFechaEnvio;
	}

	public String getsFechaAutorizado() {
		return sFechaAutorizado;
	}

	public void setsFechaAutorizado(String sFechaAutorizado) {
		this.sFechaAutorizado = sFechaAutorizado;
	}

	public String getsFechaPagado() {
		return sFechaPagado;
	}

	public void setsFechaPagado(String sFechaPagado) {
		this.sFechaPagado = sFechaPagado;
	}

	public String getsValorTolal() {
		return sValorTolal;
	}

	public void setsValorTolal(String sValorTolal) {
		this.sValorTolal = sValorTolal;
	}

	public String getsNumGastos() {
		return sNumGastos;
	}

	public void setsNumGastos(String sNumGastos) {
		this.sNumGastos = sNumGastos;
	}
	
	public String getsValorAutorizado() {
		return sValorAutorizado;
	}

	public void setsValorAutorizado(String sValorAutorizado) {
		this.sValorAutorizado = sValorAutorizado;
	}

	public String getsGastosAutorizados() {
		return sGastosAutorizados;
	}

	public void setsGastosAutorizados(String sGastosAutorizados) {
		this.sGastosAutorizados = sGastosAutorizados;
	}

	public String getsEstado() {
		return sEstado;
	}

	public void setsEstado(String sEstado) {
		this.sEstado = sEstado;
	}

	public String getsCOSPAT() {
		return sCOSPAT;
	}

	public void setsCOSPAT(String sCOSPAT) {
		this.sCOSPAT = sCOSPAT;
	}

	public ArrayList<ProvisionTabla> getTablaprovisiones() {
		return tablaprovisiones;
	}

	public void setTablaprovisiones(ArrayList<ProvisionTabla> tablaprovisiones) {
		this.tablaprovisiones = tablaprovisiones;
	}

	public ProvisionTabla getProvisionseleccionada() {
		return provisionseleccionada;
	}

	public void setProvisionseleccionada(ProvisionTabla provisionseleccionada) {
		this.provisionseleccionada = provisionseleccionada;
	}

	public String getsDCOSPAT() {
		return sDCOSPAT;
	}

	public void setsDCOSPAT(String sDCOSPAT) {
		this.sDCOSPAT = sDCOSPAT;
	}

	public String getsTAS() {
		return sTAS;
	}

	public void setsTAS(String sTAS) {
		this.sTAS = sTAS;
	}

	public String getsDTAS() {
		return sDTAS;
	}

	public void setsDTAS(String sDTAS) {
		this.sDTAS = sDTAS;
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

	public String getsNOMCOCB() {
		return sNOMCOCB;
	}

	public void setsNOMCOCB(String sNOMCOCB) {
		this.sNOMCOCB = sNOMCOCB;
	}

	public String getsNODCCOB() {
		return sNODCCOB;
	}

	public void setsNODCCOB(String sNODCCOB) {
		this.sNODCCOB = sNODCCOB;
	}

	public String getsFEPFONB() {
		return sFEPFONB;
	}

	public void setsFEPFONB(String sFEPFONB) {
		this.sFEPFONB = sFEPFONB;
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
	

}
