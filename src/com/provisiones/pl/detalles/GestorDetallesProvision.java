package com.provisiones.pl.detalles;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

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
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Provision;
import com.provisiones.types.tablas.GastoTabla;

public class GestorDetallesProvision implements Serializable 
{
	private static final long serialVersionUID = 6585445551060014479L;
	
	private static Logger logger = LoggerFactory.getLogger(GestorDetallesProvision.class.getName());
	
	
	private String sNUPROF = "";
	private String sCOSPAT = "";
	private String sTAS = "";

	private String sCOGRUG = "";
	private String sCOTPGA = "";
	
	private String sFEPFON = "";
	private String sNumGastos = "";	
	private String sValorTotal = "";
	private String sFechaEnvio = "";

	private String sGastosAutorizados = "";
	private String sValorAutorizado = "";
	private String sFechaAutorizado = "";

	private String sGastosPagados = "";
	private String sValorPagado = "";
	private String sFechaPagado = "";
	private String sCodEstado = "";
	
	
	

	private String sCOACES = "";

	
	//Gasto
	private String sCOGRUGG = "";
	private String sCOTPGAG = "";
	private String sCOSBGAG = "";
	private String sFEDEVEG = "";
	
	private String sCodGasto = ""; 

	private String sNota = "";
	
	private String sEstadoGastos = "";
	
	private transient GastoTabla gastoseleccionado = null;
	
	private transient ArrayList<GastoTabla> tablagastos = null;
	
	private Map<String,String> tiposestadogastoHM = new LinkedHashMap<String, String>();
	
	public GestorDetallesProvision()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorDetallesProvision...");
			cargarDetallesProvision();
			
			tiposestadogastoHM.put("ENVIADOS",	"E");
			tiposestadogastoHM.put("AUTORIZADO","3");
			tiposestadogastoHM.put("PAGADO",    "4");
			tiposestadogastoHM.put("ABONADOS",  "6");

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
	    	
	    	this.sCOGRUG = provision.getsCOGRUG();
	    	this.sCOTPGA = provision.getsCOTPGA();

	    	this.sFEPFON = Utils.recuperaFecha(provision.getsFEPFON());
	    	this.sNumGastos = provision.getsNumGastos();
	    	this.sValorTotal = Utils.recuperaImporte(false,provision.getsValorTotal());
	    	this.sFechaEnvio = Utils.recuperaFecha(provision.getsFechaEnvio());

	    	this.sGastosAutorizados = provision.getsGastosAutorizados();
	    	this.sValorAutorizado = Utils.recuperaImporte(false,provision.getsValorAutorizado());
	    	this.sFechaAutorizado = Utils.recuperaFecha(provision.getsFechaAutorizado());

	    	this.sGastosPagados = provision.getsGastosPagados();
	    	this.sValorPagado = Utils.recuperaImporte(false,provision.getsValorPagado());
	    	this.sFechaPagado = Utils.recuperaFecha(provision.getsFechaPagado());

	    	this.sCodEstado = provision.getsCodEstado();
	    	
		}
		
	}
	
    public void limpiarNota(ActionEvent actionEvent) 
    {  
    	this.sNota = "";
    }
	
	public void guardaNota (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;

			String sMsg = "";
			
			if (CLProvisiones.guardarNota(sNUPROF, sNota))
			{
				sMsg = "Nota guardada correctamente.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}
			else
			{
				sMsg = "ERROR: Ocurrió un error al guardar la Nota de la Referencia Catastral. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
			}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		
		}
	}
	
	public void buscarGastos()
	{
		FacesMessage msg;

		String sMsg = "";
		
 		
		if (sEstadoGastos.equals(ValoresDefecto.DEF_GASTO_AUTORIZADO) 
				|| sEstadoGastos.equals(ValoresDefecto.DEF_GASTO_PAGADO)
				|| sEstadoGastos.equals(ValoresDefecto.DEF_GASTO_ABONADO))
		{
			this.setTablagastos(CLGastos.buscarGastosProvisionConEstadoGasto(sNUPROF, sEstadoGastos));
		}
		/*if (sEstadoGastos.equals(ValoresDefecto.DEF_GASTO_AUTORIZADO))
		{
			this.setTablagastos(CLGastos.buscarGastosAutorizadosProvision(sNUPROF));	
		}
		else if (sEstadoGastos.equals(ValoresDefecto.DEF_GASTO_PAGADO))
		{
			this.setTablagastos(CLGastos.buscarGastosPagadosProvision(sNUPROF));	
		}
		else if (sEstadoGastos.equals(ValoresDefecto.DEF_GASTO_ABONADO))
		{
			this.setTablagastos(CLGastos.buscarGastosAbonadosProvision(sNUPROF));	
		}*/
		else if (sEstadoGastos.equals("E"))
		{
			this.setTablagastos(CLGastos.buscarGastosEnviadosProvision(sNUPROF));
		}
    	
    	
    	sMsg = "Gastos cargados correctamente.";
		msg = Utils.pfmsgInfo(sMsg);
		logger.info(sMsg);

    	FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void cargarDetallesGasto(ActionEvent actionEvent) 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
			
			if (gastoseleccionado != null)
			{

				this.sCOACES = gastoseleccionado.getCOACES();
		    	this.sCOGRUGG = gastoseleccionado.getCOGRUG();
		    	this.sCOTPGAG = gastoseleccionado.getCOTPGA();
		    	this.sCOSBGAG = gastoseleccionado.getCOSBGA();
		    	this.sFEDEVEG = gastoseleccionado.getFEDEVE();
		    	
		    	this.sCodGasto = Long.toString(CLGastos.buscarCodigoGasto(Integer.parseInt(sCOACES),sCOGRUGG,sCOTPGAG,sCOSBGAG,Utils.compruebaFecha(sFEDEVEG)));
		    	
		    	
		    	Sesion.guardaDetalle(sCodGasto);
		    	
		    	logger.debug("sCodGasto:|"+sCodGasto+"|");
		    	
		    	logger.debug("sCOACES:|"+sCOACES+"|");
		    	logger.debug("sCOGRUGG:|"+sCOGRUGG+"|");
		    	logger.debug("sCOTPGAG:|"+sCOTPGAG+"|");
		    	logger.debug("sCOSBGAG:|"+sCOSBGAG+"|");
		    	logger.debug("sFEDEVEG:|"+sFEDEVEG+"|");
		    	
		    	logger.debug("Redirigiendo...");
		    	
		    	Sesion.guardarHistorial("detallesprovision.xhtml","GestorDetallesGasto");

		    	sPagina = "detallesgasto.xhtml";
		    	
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
			else
			{
				FacesMessage msg;

				msg = Utils.pfmsgWarning("No se ha seleccionado ningún gasto.");
				
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

	public String getsFEPFON() {
		return sFEPFON;
	}

	public void setsFEPFON(String sFEPFON) {
		this.sFEPFON = sFEPFON;
	}

	public String getsNumGastos() {
		return sNumGastos;
	}

	public void setsNumGastos(String sNumGastos) {
		this.sNumGastos = sNumGastos;
	}

	public String getsValorTotal() {
		return sValorTotal;
	}

	public void setsValorTotal(String sValorTotal) {
		this.sValorTotal = sValorTotal;
	}

	public String getsFechaEnvio() {
		return sFechaEnvio;
	}

	public void setsFechaEnvio(String sFechaEnvio) {
		this.sFechaEnvio = sFechaEnvio;
	}

	public String getsGastosAutorizados() {
		return sGastosAutorizados;
	}

	public void setsGastosAutorizados(String sGastosAutorizados) {
		this.sGastosAutorizados = sGastosAutorizados;
	}

	public String getsValorAutorizado() {
		return sValorAutorizado;
	}

	public void setsValorAutorizado(String sValorAutorizado) {
		this.sValorAutorizado = sValorAutorizado;
	}

	public String getsFechaAutorizado() {
		return sFechaAutorizado;
	}

	public void setsFechaAutorizado(String sFechaAutorizado) {
		this.sFechaAutorizado = sFechaAutorizado;
	}

	public String getsGastosPagados() {
		return sGastosPagados;
	}

	public void setsGastosPagados(String sGastosPagados) {
		this.sGastosPagados = sGastosPagados;
	}

	public String getsValorPagado() {
		return sValorPagado;
	}

	public void setsValorPagado(String sValorPagado) {
		this.sValorPagado = sValorPagado;
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

	public String getsCOGRUGG() {
		return sCOGRUGG;
	}

	public void setsCOGRUGG(String sCOGRUGG) {
		this.sCOGRUGG = sCOGRUGG;
	}

	public String getsCOTPGAG() {
		return sCOTPGAG;
	}

	public void setsCOTPGAG(String sCOTPGAG) {
		this.sCOTPGAG = sCOTPGAG;
	}

	public String getsCOSBGAG() {
		return sCOSBGAG;
	}

	public void setsCOSBGAG(String sCOSBGAG) {
		this.sCOSBGAG = sCOSBGAG;
	}

	public String getsFEDEVEG() {
		return sFEDEVEG;
	}

	public void setsFEDEVEG(String sFEDEVEG) {
		this.sFEDEVEG = sFEDEVEG;
	}

	public String getsCodGasto() {
		return sCodGasto;
	}

	public void setsCodGasto(String sCodGasto) {
		this.sCodGasto = sCodGasto;
	}
	
	public String getsNota() {
		return sNota;
	}

	public void setsNota(String sNota) {
		this.sNota = sNota;
	}

	public String getsEstadoGastos() {
		return sEstadoGastos;
	}

	public void setsEstadoGastos(String sEstadoGastos) {
		this.sEstadoGastos = sEstadoGastos;
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

	public Map<String, String> getTiposestadogastoHM() {
		return tiposestadogastoHM;
	}

	public void setTiposestadogastoHM(Map<String, String> tiposestadogastoHM) {
		this.tiposestadogastoHM = tiposestadogastoHM;
	}
}
