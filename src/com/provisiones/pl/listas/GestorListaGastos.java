package com.provisiones.pl.listas;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLComunidades;
import com.provisiones.ll.CLGastos;
import com.provisiones.ll.CLProvisiones;
import com.provisiones.misc.Utils;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.GastoTabla;
import com.provisiones.types.tablas.ProvisionTabla;

public class GestorListaGastos implements Serializable 
{
	private static final long serialVersionUID = 2074872725730594297L;

	private static Logger logger = LoggerFactory.getLogger(GestorListaGastos.class.getName());

	private String sCOACES = "";
	private String sNUPROF = "";
	
	private String sCOGRUG = "";
	private String sCOTPGA = "";
	private String sCOSBGA = "";
	private String sFEDEVE = "";
	
	private String sCodGasto = "";
	
	private String sCOPOIN = "";
	private String sNOMUIN = "";
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	
	private String sFEPFON = "";
	
	private transient ActivoTabla activoseleccionado = null;
	private transient ArrayList<ActivoTabla> tablaactivos = null;

	private transient ProvisionTabla provisionseleccionada = null;
	private transient ArrayList<ProvisionTabla> tablaprovisiones = null;
	
	private transient GastoTabla gastoseleccionado = null;
	private transient ArrayList<GastoTabla> tablagastos = null;

	public GestorListaGastos()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorListaGastos...");	
		}
	}
	
	public void borrarCamposGasto()
	{
		this.sCOACES = "";
    	this.sNUPROF = "";
    	
    	this.setGastoseleccionado(null);
    	this.setTablagastos(null);
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
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	borrarCamposActivo();
    }
    
    
	public void borrarCamposProvision()
	{
		this.sFEPFON = "";
    	
    	this.setProvisionseleccionada(null);
    	this.setTablaprovisiones(null);
	}
	
    public void limpiarPlantillaProvision(ActionEvent actionEvent) 
    {  
    	borrarCamposProvision();
    }
    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	borrarCamposGasto();
    	borrarCamposActivo();
    	borrarCamposProvision();
    }
    
	public void buscarActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			ActivoTabla buscaactivos = new ActivoTabla(
					sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
					sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
					sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(), "");
			
			this.setTablaactivos(CLComunidades.buscarActivosSinComunidad(buscaactivos));

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
	    	
	    	this.sCOACES  = activoseleccionado.getCOACES();
	    	
	    	sMsg = "Activo '"+sCOACES+"' seleccionado.";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	
	    	logger.info(sMsg);
	    	
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void buscarProvisiones (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = ""; 

			this.setTablaprovisiones(CLProvisiones.buscarProvisionesFecha(Utils.compruebaFecha(sFEPFON)));

			sMsg = "Encontradas "+getTablaprovisiones().size()+" provisiones relacionadas.";
			msg = Utils.pfmsgInfo(sMsg);
			
			logger.info(sMsg);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	public void seleccionarProvision(ActionEvent actionEvent) 
    { 
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	String sMsg = "";

	    	this.sNUPROF  = provisionseleccionada.getNUPROF();
	    	
	    	sMsg = "Provision '"+sNUPROF+"' seleccionada.";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	
	    	logger.info(sMsg);
	    	
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
    
	public void encuentraGastos()
	{
		
		logger.debug("Buscando Gastos...");
		
		if (!sCOACES.equals(""))
		{
			this.setTablagastos(CLGastos.buscarGastosActivo(sCOACES));
		}
		else if (!sNUPROF.equals(""))
		{
			this.setTablagastos(CLGastos.buscarGastosProvision(sNUPROF));
		}
		
		logger.debug("Encontrados "+getTablagastos().size()+" activos relacionados.");
	}
    
	public void buscarGastos (ActionEvent actionEvent)
	{

		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			encuentraGastos();
			
			if (getTablagastos().size() == 0)
			{
				msg = Utils.pfmsgWarning("No se encontraron gastos con los criterios solicitados.");
			}
			else if (getTablagastos().size() == 1)
			{
				msg = Utils.pfmsgInfo("Encontrado un gasto relacionado.");
			}
			else
			{
				msg = Utils.pfmsgInfo("Encontrados "+getTablagastos().size()+" gastos relacionados.");
			}

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	public String cargarDetalles() 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
			if (gastoseleccionado != null)
			{
		    	this.sCOGRUG = gastoseleccionado.getCOGRUG();
		    	this.sCOTPGA = gastoseleccionado.getCOTPGA();
		    	this.sCOSBGA = gastoseleccionado.getCOSBGA();
		    	this.sFEDEVE = gastoseleccionado.getFEDEVE();
		    	
		    	this.sCodGasto = CLGastos.buscarCodigoGasto(sCOACES,sCOGRUG,sCOTPGA,sCOSBGA,sFEDEVE);
		    	
		    	logger.debug("sCOACES:|"+sCOACES+"|");
		    	logger.debug("sCOGRUG:|"+sCOGRUG+"|");
		    	logger.debug("sCOTPGA:|"+sCOTPGA+"|");
		    	logger.debug("sCOSBGA:|"+sCOSBGA+"|");
		    	logger.debug("sFEDEVE:|"+sFEDEVE+"|");
		    	
		    	logger.debug("Redirigiendo...");

		    	sPagina = "detallesgasto.xhtml";
			}
			else
			{
				FacesMessage msg;

				msg = Utils.pfmsgWarning("No se ha seleccionado ning�n gasto.");
				
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}

		}

		return sPagina;
    }
	
	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
	}

	public String getsNUPROF() {
		return sNUPROF;
	}

	public void setsNUPROF(String sNUPROF) {
		this.sNUPROF = sNUPROF;
	}

	public String getsCOGRUG() {
		return sCOGRUG;
	}

	public void setsCOGRUG(String sCOGRUG) {
		this.sCOGRUG = sCOGRUG;
	}

	public String getsCOTPGA() {
		return sCOTPGA;
	}

	public void setsCOTPGA(String sCOTPGA) {
		this.sCOTPGA = sCOTPGA;
	}

	public String getsCOSBGA() {
		return sCOSBGA;
	}

	public void setsCOSBGA(String sCOSBGA) {
		this.sCOSBGA = sCOSBGA;
	}

	public String getsFEDEVE() {
		return sFEDEVE;
	}

	public void setsFEDEVE(String sFEDEVE) {
		this.sFEDEVE = sFEDEVE;
	}

	public GastoTabla getGastoseleccionado() {
		return gastoseleccionado;
	}

	public void setGastoseleccionado(GastoTabla gastoseleccionado) {
		this.gastoseleccionado = gastoseleccionado;
	}

	public ArrayList<GastoTabla> getTablagastos() {
		return tablagastos;
	}

	public void setTablagastos(ArrayList<GastoTabla> tablagastos) {
		this.tablagastos = tablagastos;
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

	public String getsFEPFON() {
		return sFEPFON;
	}

	public void setsFEPFON(String sFEPFON) {
		this.sFEPFON = sFEPFON;
	}

	public String getsCodGasto() {
		return sCodGasto;
	}

	public void setsCodGasto(String sCodGasto) {
		this.sCodGasto = sCodGasto;
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

	public ProvisionTabla getProvisionseleccionada() {
		return provisionseleccionada;
	}

	public void setProvisionseleccionada(ProvisionTabla provisionseleccionada) {
		this.provisionseleccionada = provisionseleccionada;
	}

	public ArrayList<ProvisionTabla> getTablaprovisiones() {
		return tablaprovisiones;
	}

	public void setTablaprovisiones(ArrayList<ProvisionTabla> tablaprovisiones) {
		this.tablaprovisiones = tablaprovisiones;
	}
	
}
