package com.provisiones.pl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLProvisiones;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

import com.provisiones.types.Provision;
import com.provisiones.types.tablas.ProvisionTabla;

public class GestorProvisiones implements Serializable 
{
	private static final long serialVersionUID = 6140067108661410063L;

	private static Logger logger = LoggerFactory.getLogger(GestorProvisiones.class.getName());

	private String sNUPROF = "";
	private String sCOSPAT = "";
	private String sDCOSPAT = "";
	private String sTAS = "";
	private String sDTAS = "";
	private String sValorTolal = "";
	private String sNumGastos = "";
	private String sFEPFON = "";
	private String sFechaValidacion = "";
	private String sValidado = "";

	private transient ArrayList<ProvisionTabla> tablaprovisiones = null;
	
	private transient ProvisionTabla provisionseleccionada = null;
	
	public GestorProvisiones()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorProvisiones...");	
		}
	}

	public void cargaProvisionesAbiertas(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
	    	
			this.tablaprovisiones = CLProvisiones.buscarProvisionesAbiertas(); 

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
	    	this.sValorTolal  = Double.toString(CLProvisiones.calcularValorProvision(sNUPROF));
	    	this.sNumGastos  = Long.toString(CLProvisiones.buscarNumeroGastosProvision(sNUPROF));
	    	
	    	msg = Utils.pfmsgInfo("Provision '"+ sNUPROF +"' Seleccionada.");
	    	logger.info("Provision '{}' Seleccionada.",sNUPROF);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}
    }
	
	public void cerrarProvision(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			Provision provision = new Provision(sNUPROF, sCOSPAT, sTAS, Utils.compruebaImporte(sValorTolal), sNumGastos, Utils.fechaDeHoy(false),ValoresDefecto.CAMPO_SIN_INFORMAR, ValoresDefecto.DEF_BAJA);
			
					
			//CLProvisiones.detallesProvision(sNUPROF);
			String sMsg = "";

			if (CLProvisiones.cerrarProvision(provision))
			{
				sMsg = "Provision '"+ sNUPROF +"' cerrada.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);

			}
			else
			{
				sMsg = "[FATAL] ERROR: ha ocurrido un error al cerrar la provision. Avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
			}

	    	
			FacesContext.getCurrentInstance().addMessage(null, msg);	
		}
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

	public String getsFechaValidacion() {
		return sFechaValidacion;
	}

	public void setsFechaValidacion(String sFechaValidacion) {
		this.sFechaValidacion = sFechaValidacion;
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

	public String getsValidado() {
		return sValidado;
	}

	public void setsValidado(String sValidado) {
		this.sValidado = sValidado;
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



}
