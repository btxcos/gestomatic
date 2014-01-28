package com.provisiones.pl.detalles;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLGastos;
import com.provisiones.ll.CLProvisiones;
import com.provisiones.misc.Sesion;
import com.provisiones.misc.Utils;
import com.provisiones.types.Provision;
import com.provisiones.types.tablas.GastoTabla;

public class GestorDetallesProvision implements Serializable 
{
	private static final long serialVersionUID = 6585445551060014479L;
	
	private static Logger logger = LoggerFactory.getLogger(GestorDetallesProvision.class.getName());
	
	
	private String sNUPROF = "";
	private String sCOSPAT = "";
	private String sTAS = "";
	private String sValorTolal = "";
	private String sNumGastos = "";	
	private String sValorAutorizado = "";
	private String sGastosAutorizados = "";	
	private String sFEPFON = "";
	private String sFechaEnvio = "";
	private String sFechaAutorizado = "";
	private String sFechaPagado = "";
	private String sCodEstado = "";
	
	private String sCOACES = "";
	
	private String sCOGRUG = "";
	private String sCOTPGA = "";
	private String sCOSBGA = "";
	private String sFEDEVE = "";
	
	private String sCodGasto = ""; 

	private transient GastoTabla gastoseleccionado = null;
	private transient ArrayList<GastoTabla> tablagastos = null;
	
	public GestorDetallesProvision()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorDetallesProvision...");
			cargarDetallesProvision();
		}
	}
	
	public void volver(ActionEvent actionEvent)
	{
		logger.debug("Volviendo...");
		//return Utils.cargarHistorial(sClase);
		
		try 
		{
			FacesContext.getCurrentInstance().getExternalContext().redirect(Sesion.cargarHistorial());
		}
		catch (IOException e)
		{
			FacesMessage msg;
			
			String sMsg = "ERROR: Ocurrió un problema al intentar regresar. Por favor, avise a soporte.";
			
			msg = Utils.pfmsgFatal(sMsg);
			logger.error(sMsg);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
			

		}
	}
	
	public void cargarDetallesProvision()
	{
		logger.debug("Cargando Provision...");

		
		//this.sNUPROF = ((GestorListaProvisiones)((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorListaProvisiones")).getsNUPROF();
		
		this.sNUPROF = Sesion.cargarDetalle();
		
		logger.debug("sNUPROF:|"+sNUPROF+"|");
		
		
		if (!sNUPROF.equals(""))
		{

		  	Provision provision = CLProvisiones.buscarDetallesProvision(sNUPROF);

	    	logger.debug(provision.logProvision());
		  	
	    	this.sCOSPAT = provision.getsCOSPAT();
	    	this.sTAS = provision.getsTAS();
	    	this.sValorTolal = Utils.recuperaImporte(false,provision.getsValorTolal());
	    	this.sNumGastos = provision.getsNumGastos();	
	    	this.sFEPFON = Utils.recuperaFecha(provision.getsFEPFON());
	    	this.sFechaEnvio = Utils.recuperaFecha(provision.getsFechaEnvio());
	    	this.sFechaAutorizado = Utils.recuperaFecha(provision.getsFechaAutorizado());
	    	this.sValorAutorizado = Utils.recuperaImporte(false,provision.getsValorAutorizado());
	    	this.sGastosAutorizados = provision.getsGastosAutorizados();	
	    	this.sFechaPagado = Utils.recuperaFecha(provision.getsFechaPagado());
	    	this.sCodEstado = provision.getsCodEstado();
	    	
	    	
		}
		
	}
	
	public void buscarGastos()
	{
    	this.setTablagastos(CLGastos.buscarGastosProvision(sNUPROF));

    	logger.debug("Tamaño:"+tablagastos.size());	
	}
	
	public void cargarDetallesGasto(ActionEvent actionEvent) 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
			
			if (gastoseleccionado != null)
			{

				this.sCOACES = gastoseleccionado.getCOACES();
		    	this.sCOGRUG = gastoseleccionado.getCOGRUG();
		    	this.sCOTPGA = gastoseleccionado.getCOTPGA();
		    	this.sCOSBGA = gastoseleccionado.getCOSBGA();
		    	this.sFEDEVE = gastoseleccionado.getFEDEVE();
		    	
		    	this.sCodGasto = CLGastos.buscarCodigoGasto(sCOACES,sCOGRUG,sCOTPGA,sCOSBGA,Utils.compruebaFecha(sFEDEVE));
		    	
		    	
		    	Sesion.guardaDetalle(sCodGasto);
		    	
		    	logger.debug("sCodGasto:|"+sCodGasto+"|");
		    	
		    	logger.debug("sCOACES:|"+sCOACES+"|");
		    	logger.debug("sCOGRUG:|"+sCOGRUG+"|");
		    	logger.debug("sCOTPGA:|"+sCOTPGA+"|");
		    	logger.debug("sCOSBGA:|"+sCOSBGA+"|");
		    	logger.debug("sFEDEVE:|"+sFEDEVE+"|");
		    	
		    	logger.debug("Redirigiendo...");
		    	
		    	Sesion.guardarHistorial("detallesprovision.xhtml","GestorDetallesGasto");

		    	sPagina = "detallesgasto.xhtml";
			}
			else
			{
				FacesMessage msg;

				msg = Utils.pfmsgWarning("No se ha seleccionado ningún gasto.");
				
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
			try 
			{
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

	public String getsCOSPAT() {
		return sCOSPAT;
	}

	public void setsCOSPAT(String sCOSPAT) {
		this.sCOSPAT = sCOSPAT;
	}

	public String getsTAS() {
		return sTAS;
	}

	public void setsTAS(String sTAS) {
		this.sTAS = sTAS;
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

	public String getsCodEstado() {
		return sCodEstado;
	}

	public void setsCodEstado(String sCodEstado) {
		this.sCodEstado = sCodEstado;
	}

	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
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

	public String getsCodGasto() {
		return sCodGasto;
	}

	public void setsCodGasto(String sCodGasto) {
		this.sCodGasto = sCodGasto;
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
	
}
