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
import com.provisiones.types.tablas.ProvisionTabla;

public class GestorProvisiones implements Serializable 
{
	private static final long serialVersionUID = 6140067108661410063L;

	private static Logger logger = LoggerFactory.getLogger(GestorProvisiones.class.getName());

	private String sNUPROF = "";
	private String sCOSPAT = "";
	private String sDCOSPAT = "";
	private String sDTAS = "";
	private String sDCOGRUG = "";	
	private String sDCOTPGA = "";
	private String sValorTolal = "";
	private String sNumGastos = "";
	
	private boolean bLibre = true; 

	private transient ArrayList<ProvisionTabla> tablaprovisiones = null;
	
	private transient ProvisionTabla provisionseleccionada = null;
	
	public GestorProvisiones()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorProvisiones...");	
		}
	}
	
    public void borrarCamposProvision()
    {
    	this.sNUPROF = "";
    	this.sCOSPAT = "";
    	this.sDCOSPAT = "";
    	this.sDTAS = "";
    	this.sDCOGRUG = "";
    	this.sDCOTPGA = "";
    	this.sNumGastos = "";
    	this.sValorTolal = "";
    	
    	this.tablaprovisiones = null;
    	this.provisionseleccionada = null;
    	
    	bLibre = true;

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
	    	this.sDTAS  = provisionseleccionada.getDTAS();
	    	this.sDCOGRUG  = provisionseleccionada.getDCOGRUG();
	    	this.sDCOTPGA  = provisionseleccionada.getDCOTPGA();
	    	this.sValorTolal  = provisionseleccionada.getVALOR();//CLProvisiones.calcularValorProvision(sNUPROF);
	    	this.sNumGastos  = provisionseleccionada.getGASTOS();//Long.toString(CLProvisiones.buscarNumeroGastosProvision(sNUPROF));
	    	
	    	bLibre = false;
	    	
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
			
			/*Provision provision = new Provision(sNUPROF, 
					sCOSPAT, 
					sTAS,
					ValoresDefecto.CAMPO_NUME_SIN_INFORMAR,
					ValoresDefecto.CAMPO_NUME_SIN_INFORMAR,
					Utils.fechaDeHoy(false),
					sNumGastos,
					Utils.compruebaImporte(sValorTolal),
					ValoresDefecto.CAMPO_NUME_SIN_INFORMAR, 
					ValoresDefecto.CAMPO_NUME_SIN_INFORMAR,
					ValoresDefecto.CAMPO_NUME_SIN_INFORMAR, 
					ValoresDefecto.CAMPO_NUME_SIN_INFORMAR, 
					ValoresDefecto.CAMPO_NUME_SIN_INFORMAR,
					ValoresDefecto.CAMPO_NUME_SIN_INFORMAR,
					ValoresDefecto.CAMPO_NUME_SIN_INFORMAR, 
					ValoresDefecto.DEF_PROVISION_PENDIENTE);*/
			
					
			//CLProvisiones.detallesProvision(sNUPROF);
			String sMsg = "";

			//if (CLProvisiones.cerrarProvision(provision))
			
			if (sNUPROF.isEmpty())
			{
				sMsg = "Debe seleccionar una provisión antes. Por favor, revise los datos.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
			}
			else
			{
				if (CLProvisiones.cerrarProvision(sNUPROF,Utils.fechaDeHoy(false)))
				{
					sMsg = "Provision '"+ sNUPROF +"' cerrada.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
					borrarCamposProvision();

				}
				else
				{
					sMsg = "[FATAL] ERROR: ha ocurrido un error al cerrar la provision. Avise a soporte.";
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
				}
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

	public String getsDTAS() {
		return sDTAS;
	}

	public void setsDTAS(String sDTAS) {
		this.sDTAS = sDTAS;
	}

	public String getsDCOGRUG() {
		return sDCOGRUG;
	}

	public void setsDCOGRUG(String sDCOGRUG) {
		this.sDCOGRUG = sDCOGRUG;
	}

	public String getsDCOTPGA() {
		return sDCOTPGA;
	}

	public void setsDCOTPGA(String sDCOTPGA) {
		this.sDCOTPGA = sDCOTPGA;
	}

	public boolean isbLibre() {
		return bLibre;
	}

	public void setbLibre(boolean bLibre) {
		this.bLibre = bLibre;
	}

}
