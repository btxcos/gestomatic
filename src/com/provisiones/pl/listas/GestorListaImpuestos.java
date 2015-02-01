package com.provisiones.pl.listas;

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
import com.provisiones.ll.CLImpuestos;
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Sesion;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Transicion;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.ImpuestoRecursoTabla;

public class GestorListaImpuestos implements Serializable 
{
	private static final long serialVersionUID = 658962915704318075L;

	private static Logger logger = LoggerFactory.getLogger(GestorListaImpuestos.class.getName());

	//Buscar activos
	private String sCOACES = "";

	//Filtro activos
	private String sCOPOIN = "";
	private String sNOMUIN = "";	
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	private String sNUFIRE = "";
	private String sNURCATF = "";
	
	//filtro recurso activo
	private String sNURCATFA = "";
	private String sCOSBACFA = "";
	private String sFEPRREFA = "";
	private String sFEREREFA = "";
	private String sFEDEINFA = "";
	private String sBISODEFA = "";
	private String sBIRESOFA = "";
	

	private transient ArrayList<ActivoTabla> tablaactivos = null;
	private transient ActivoTabla activoseleccionado = null;
	
	private transient ArrayList<ImpuestoRecursoTabla> tablaimpuestos = null;
	private transient ImpuestoRecursoTabla impuestoseleccionado = null;
	
	private Map<String,String> tiposbiresoHM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposbinariaHM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposcosbacHM = new LinkedHashMap<String, String>();
	
	public GestorListaImpuestos()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorListaImpuestos...");
			
			tiposbinariaHM.put("SI","S");
			tiposbinariaHM.put("NO","N");
			
			tiposbiresoHM.put("FAVORABLE",   "F");
			tiposbiresoHM.put("DESFAVORABLE","D");
			
			tiposcosbacHM.put("Impuestos e IBIS",                     "0");
			tiposcosbacHM.put("IBIS",                                 "1");
			tiposcosbacHM.put("Tasas basura",                         "2");
			tiposcosbacHM.put("Tasas alcantarillado",                 "3");
			tiposcosbacHM.put("Tasas agua",                           "4");
			tiposcosbacHM.put("Contribuciones especiales",            "5");
			tiposcosbacHM.put("Otras tasas",                          "6");
			
		}
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
		this.sNUFIRE = "";
		
		this.sNURCATF = "";
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
    
	public void borrarCamposFiltroRecursosActivo()
	{
		this.sNURCATFA = "";
		this.sCOSBACFA = "";
		this.sFEPRREFA = "";
		this.sFEREREFA = "";
		this.sFEDEINFA = "";
		this.sBISODEFA = "";
		this.sBIRESOFA = "";
	}
	
    public void limpiarPlantillaFiltroRecursosActivo(ActionEvent actionEvent) 
    {  
    	borrarCamposFiltroRecursosActivo();
    }
    
	public void borrarCamposImpuesto()
	{
    	this.setImpuestoseleccionado(null);
    	this.setTablaimpuestos(null);
	}
    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {
    	this.sCOACES = "";
    	
    	borrarCamposActivo();
    	borrarCamposImpuesto();
    	borrarCamposFiltroRecursosActivo();
    }
    
	public void buscarActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.activoseleccionado = null;
			
			this.setTablaactivos(null);
			
			if (sNURCATF.isEmpty())
			{
				ActivoTabla filtro = new ActivoTabla(
						"", 
						sCOPOIN.toUpperCase(), 
						sNOMUIN.toUpperCase(),
						sNOPRAC.toUpperCase(), 
						sNOVIAS.toUpperCase(), 
						sNUPIAC.toUpperCase(), 
						sNUPOAC.toUpperCase(), 
						sNUPUAC.toUpperCase(), 
						sNUFIRE.toUpperCase(),
						"");
				
				this.setTablaactivos(CLImpuestos.buscarActivosConImpuestos(filtro));
				
				if (getTablaactivos().size() == 0)
				{
					sMsg = "No se encontraron Activos con los criterios solicitados.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);
				}
				else if (getTablaactivos().size() == 1)
				{
					sMsg = "Encontrado un Activo relacionado.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
				}
				else
				{
					sMsg = "Encontrados "+getTablaactivos().size()+" Activos relacionados.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
				}
			}
			else if (CLReferencias.existeReferenciaCatastral(sNURCATF))
			{
				this.setTablaactivos(CLReferencias.buscarActivoAsociadoConRecursos(sNURCATF));
				
				if (getTablaactivos().size() == 0)
				{
					sMsg = "No se encontraron Activos con los criterios solicitados.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);
				}
				else
				{
					sMsg = "Encontrado un Activo relacionado.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
				}
			}
			else
			{
				
				sMsg = "La Referencia Catastral informada no se encuentrar registrada en el sistema. Por favor, revise los datos.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
			}


			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void seleccionarActivo(ActionEvent actionEvent) 
    {
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
	    	
	    	this.sCOACES  = activoseleccionado.getCOACES();
	    	
	    	String sMsg = "Activo '"+ sCOACES +"' Seleccionado.";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	logger.info(sMsg);
	    	
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void buscarRecursosActivo(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.impuestoseleccionado = null;
			
			this.tablaimpuestos = null;
			
			if (sCOACES.isEmpty())
			{
				sMsg = "ERROR: Debe informar el Activo para realizar una búsqueda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else
			{
				
				try
				{
					
					int iCOACES = Integer.parseInt(sCOACES);
					
					ImpuestoRecursoTabla filtro = new ImpuestoRecursoTabla(
							"",
							sNURCATFA,   
							sCOSBACFA,
							"",
							Utils.compruebaFecha(sFEPRREFA),   
							Utils.compruebaFecha(sFEREREFA),
							Utils.compruebaFecha(sFEDEINFA),
							sBISODEFA,
							"",
							sBIRESOFA,
							"",
							"",
							"");
					
					//this.tablaimpuestos = CLImpuestos.buscarImpuestosActivos(iCOACES);
					this.tablaimpuestos = CLImpuestos.buscarImpuestosActivoConFiltro(filtro,iCOACES);
					
					
					if (getTablaimpuestos().size() == 0)
					{
						sMsg = "No se encontraron Impuestos con los criterios solicitados.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);
					}
					else if (getTablaimpuestos().size() == 1)
					{
						sMsg = "Encontrado un Impuesto relacionado.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
					else
					{
						sMsg = "Encontradas "+getTablaimpuestos().size()+" Impuestos relacionados.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
					
					
					//this.sNURCAT  = CLReferencias.referenciaCatastralAsociada(iCOACES);

				}
				catch(NumberFormatException nfe)
				{
					sMsg = "ERROR: El activo debe ser numérico. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
			}
			
	    	FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void hoyFEPRREFA (ActionEvent actionEvent)
	{
		this.setsFEPRREFA(Utils.fechaDeHoy(true));
		logger.debug("sFEPRREFA:|"+sFEPRREFA+"|");
	}
	
	public void hoyFEREREFA (ActionEvent actionEvent)
	{
		this.setsFEREREFA(Utils.fechaDeHoy(true));
		logger.debug("sFEREREFA:|"+sFEREREFA+"|");
	}
	
	public void hoyFEDEINFA (ActionEvent actionEvent)
	{
		this.setsFEDEINFA(Utils.fechaDeHoy(true));
		logger.debug("sFEDEINFA:|"+sFEDEINFA+"|");
	}
	
	public void cargarDetallesImpuesto(ActionEvent actionEvent) 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
			String sMsg = "";
			
			if (impuestoseleccionado != null)
			{
		    	
		    	//String sCodImpuesto = Long.toString(CLImpuestos.buscarCodigoImpuesto(sNURCAT, sCOSBAC));
		    	String sCodImpuesto = impuestoseleccionado.getsRecursoID();
		    	
		    	logger.debug("sCodImpuesto:|"+sCodImpuesto+"|");

		    	Transicion transicion = new Transicion (
		    			sCodImpuesto,
		    			ValoresDefecto.ID_RECURSO,
		    			"listaimpuestos.xhtml",
		    			"GestorDetallesImpuesto");
		    	
		    	Sesion.guardarTransicion(transicion, true);
		    	
		    	//Sesion.guardaDetalle(sCodImpuesto);
		    	//Sesion.limpiarHistorial();
		    	//Sesion.guardarHistorial("listaimpuestos.xhtml","GestorDetallesImpuesto");

		    	sPagina = "detallesimpuesto.xhtml";
		    	
		    	
				try 
				{
					sMsg = "Redirigiendo hacia '"+sPagina+"'";
					logger.info(sMsg);
					FacesContext.getCurrentInstance().getExternalContext().redirect(sPagina);
				}
				catch (IOException e)
				{
					FacesMessage msg;
					
					sMsg = "ERROR: Ocurrió un problema al acceder a los detalles. Por favor, avise a soporte.";
					
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					
					FacesContext.getCurrentInstance().addMessage(null, msg);
					

				}
			}
			else
			{
				FacesMessage msg;

				sMsg = "ERROR: No se ha seleccionado un Recurso.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
				
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			


		}

		//return sPagina;
    }

	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES.trim();
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

	public String getsNUFIRE() {
		return sNUFIRE;
	}

	public void setsNUFIRE(String sNUFIRE) {
		this.sNUFIRE = sNUFIRE;
	}

	public String getsNURCATFA() {
		return sNURCATFA;
	}

	public void setsNURCATFA(String sNURCATFA) {
		this.sNURCATFA = sNURCATFA;
	}

	public String getsCOSBACFA() {
		return sCOSBACFA;
	}

	public void setsCOSBACFA(String sCOSBACFA) {
		this.sCOSBACFA = sCOSBACFA;
	}

	public String getsFEPRREFA() {
		return sFEPRREFA;
	}

	public void setsFEPRREFA(String sFEPRREFA) {
		this.sFEPRREFA = sFEPRREFA;
	}

	public String getsFEREREFA() {
		return sFEREREFA;
	}

	public void setsFEREREFA(String sFEREREFA) {
		this.sFEREREFA = sFEREREFA;
	}

	public String getsFEDEINFA() {
		return sFEDEINFA;
	}

	public void setsFEDEINFA(String sFEDEINFA) {
		this.sFEDEINFA = sFEDEINFA;
	}

	public String getsBISODEFA() {
		return sBISODEFA;
	}

	public void setsBISODEFA(String sBISODEFA) {
		this.sBISODEFA = sBISODEFA;
	}

	public String getsBIRESOFA() {
		return sBIRESOFA;
	}

	public void setsBIRESOFA(String sBIRESOFA) {
		this.sBIRESOFA = sBIRESOFA;
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

	public Map<String, String> getTiposbiresoHM() {
		return tiposbiresoHM;
	}

	public void setTiposbiresoHM(Map<String, String> tiposbiresoHM) {
		this.tiposbiresoHM = tiposbiresoHM;
	}

	public Map<String, String> getTiposbinariaHM() {
		return tiposbinariaHM;
	}

	public void setTiposbinariaHM(Map<String, String> tiposbinariaHM) {
		this.tiposbinariaHM = tiposbinariaHM;
	}

	public Map<String,String> getTiposcosbacHM() {
		return tiposcosbacHM;
	}

	public void setTiposcosbacHM(Map<String,String> tiposcosbacHM) {
		this.tiposcosbacHM = tiposcosbacHM;
	}

	public String getsNURCATF() {
		return sNURCATF;
	}

	public void setsNURCATF(String sNURCATF) {
		this.sNURCATF = sNURCATF.trim().toUpperCase();
	}
}

//Autor: Francisco Valverde Manjón