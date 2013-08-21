package com.provisiones.pl;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.ll.CLProvisiones;
import com.provisiones.misc.Utils;

import com.provisiones.types.Provision;
import com.provisiones.types.ProvisionTabla;

public class GestorProvisiones implements Serializable 
{

	private static final long serialVersionUID = 6099738101025444086L;

	static String sClassName = GestorProvisiones.class.getName();
	
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

	private ArrayList<ProvisionTabla> tablaprovisiones = null;
	
	private ProvisionTabla provisionseleccionada = null;
	
	public GestorProvisiones()
	{
		Utils.standardIO2File("");//Salida por fichero de texto
		//cargaProvisionAbierta();
	}

	public void cargaProvisionesAbiertas()
	{
		String sMethod = "cargaProvisionAbierta";
		
		FacesMessage msg;
    	
    	String sMsg = "";

		this.tablaprovisiones = CLProvisiones.buscarProvisionesAbiertas(); 
		
		
		sMsg = "Encontradas "+getTablaprovisiones().size()+" provisiones abiertas.";
		Utils.debugTrace(true, sClassName, sMethod, sMsg);
		msg = new FacesMessage(sMsg);

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void seleccionarProvision(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "seleccionarProvision";

    	FacesMessage msg;
    	
    	this.sNUPROF  = provisionseleccionada.getNUPROF();
    	this.sCOSPAT  = provisionseleccionada.getCOSPAT();
    	this.sDCOSPAT  = provisionseleccionada.getDCOSPAT();
    	this.sTAS  = provisionseleccionada.getCOSPAT();
    	this.sDTAS  = provisionseleccionada.getDCOSPAT();
    	this.sValorTolal  = provisionseleccionada.getVALOR();
    	this.sNumGastos  = provisionseleccionada.getGASTOS();
    	
    	msg = new FacesMessage("Provision '"+ sNUPROF +"' Seleccionada.");
    	
    	Utils.debugTrace(true, sClassName, sMethod, "Provision seleccionada: |"+sNUPROF+"|");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
    }
	
	public void cargarProvision(ActionEvent actionEvent)
	{
		CLProvisiones.detallesProvision(sNUPROF);
	}
	

	
	public void cerrarProvision(ActionEvent actionEvent)
	{
		String sMethod = "cerrarProvision";
		
    	FacesMessage msg;
    	
    	String sMsg = "";
		
		Provision provision = CLProvisiones.detallesProvision(sNUPROF);
		
		provision.setsFEPFON(Utils.fechaDeHoy(false));
		
		provision.setsCodEstado("B");
		
		if (CLProvisiones.cerrarProvision(provision))
		{
			sMsg = "Provision '"+ sNUPROF +"' cerrada.";
			msg = new FacesMessage(sMsg);

		}
		else
		{
			sMsg = "ERROR: ha ocurrido un error al cerrar la provision.";
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,sMsg,null);
		}

    	
    	Utils.debugTrace(true, sClassName, sMethod, sMsg);
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
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
