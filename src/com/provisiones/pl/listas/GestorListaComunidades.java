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
import com.provisiones.ll.CLActivos;
import com.provisiones.ll.CLComunidades;
import com.provisiones.misc.Sesion;
import com.provisiones.misc.Utils;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.ComunidadTabla;

public class GestorListaComunidades implements Serializable
{
	private static final long serialVersionUID = 3330294042440714420L;

	private static Logger logger = LoggerFactory.getLogger(GestorListaComunidades.class.getName());
	
	private String sCOACES = "";

	//Busqueda Activos
	private String sCOPOIN = "";
	private String sNOMUIN = "";
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	
	private String sCOCLDO = "";
	private String sNUDCOM = "";
	private String sNOMCOC = "";

	private transient ActivoTabla activoseleccionado = null;
	private transient ArrayList<ActivoTabla> tablaactivos = null;
	
	private transient ComunidadTabla comunidadseleccionada = null;
	private transient ArrayList<ComunidadTabla> tablacomunidades = null;
	
	private Map<String,String> tiposcocldoHM = new LinkedHashMap<String, String>();

	public GestorListaComunidades()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorListaComunidades...");
			
			tiposcocldoHM.put("C.I.F.",                     "2");
			tiposcocldoHM.put("C.I.F país extranjero.",     "5");
			tiposcocldoHM.put("Otros persona jurídica.",    "J");
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
    	
    	this.setActivoseleccionado(null);
    	this.setTablaactivos(null);
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	borrarCamposActivo();
    }
    
	public void borrarCamposComunidad()
	{
    	this.sCOCLDO = "";
    	this.sNUDCOM = "";
    	
    	this.setComunidadseleccionada(null);
    	this.setTablacomunidades(null);
	}
    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {
		this.sCOACES = "";
    	
    	borrarCamposActivo();
    	borrarCamposComunidad();
    }
    
	public void buscarActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			ActivoTabla filtro = new ActivoTabla(
					sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
					sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
					sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(), "");
			
			this.setTablaactivos(CLComunidades.buscarActivosConComunidad(filtro));

			String sMsg = "Encontrados "+getTablaactivos().size()+" activos relacionados.";
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
	    	
	    	sMsg = "Activo '"+sCOACES+"' seleccionado.";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	
	    	logger.info(sMsg);
	    	
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void buscarComunidadActivo (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = ""; 
			
			if (sCOACES.equals(""))
			{
				sMsg = "ERROR: Debe informar el Activo para realizar una búsqueda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else
			{
				try
				{
					if (!CLActivos.existeActivo(Integer.parseInt(sCOACES)))
					{
						sMsg = "El Activo '"+sCOACES+"' no pertenece a la cartera. Por favor, revise los datos.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);
					}
					else
					{
						this.setTablacomunidades(CLComunidades.buscarComunidadActivo (Integer.parseInt(sCOACES)));

						sMsg = "Encontradas "+getTablacomunidades().size()+" comunidades relacionadas.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
				}
				catch(NumberFormatException nfe)
				{
					sMsg = "ERROR: El Activo debe ser numérico. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}

			}

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	public void buscarComunidad (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = ""; 
			
			if (sCOCLDO.equals("") || sNUDCOM.equals(""))
			{
				sMsg = "ERROR: Los campos 'Documento' y 'Número' deben de ser informados para realizar la búsqueda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else if (!CLComunidades.existeComunidad(sCOCLDO, sNUDCOM.toUpperCase()))
			{
				sMsg = "La comunidad informada no está dada de alta. Por favor, revise los datos.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
			}
			else
			{
				this.setTablacomunidades(CLComunidades.buscarComunidad (sCOCLDO,sNUDCOM.toUpperCase()));

				sMsg = "Encontradas "+getTablacomunidades().size()+" comunidades relacionadas.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}


			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	public void cargarDetallesComunidad(ActionEvent actionEvent) 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
			String sMsg = "";
			
			if (comunidadseleccionada != null)
			{

		    	this.sCOCLDO  = comunidadseleccionada.getCOCLDO();
		    	this.sNUDCOM  = comunidadseleccionada.getNUDCOM();

		    	logger.debug("sCOCLDO:|"+sCOCLDO+"|");
		    	logger.debug("sNUDCOM:|"+sNUDCOM+"|");
		    	
		    	//String sCodComunidad = Long.toString(CLComunidades.buscarCodigoComunidad(sCOCLDO, sNUDCOM));
		    	
		    	String sCodComunidad = comunidadseleccionada.getsComunidadID();
		    	
		    	logger.debug("sCodComunidad:|"+sCodComunidad+"|");
		    	
		    	Sesion.guardaDetalle(sCodComunidad);
		    	Sesion.limpiarHistorial();
		    	Sesion.guardarHistorial("listacomunidades.xhtml","GestorDetallesComunidad");

		    	sPagina = "detallescomunidad.xhtml";
		    	
		    	
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

				sMsg = "ERROR: No se ha seleccionado una Comunidad.";
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

	public String getsCOCLDO() {
		return sCOCLDO;
	}

	public void setsCOCLDO(String sCOCLDO) {
		this.sCOCLDO = sCOCLDO;
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

	public ComunidadTabla getComunidadseleccionada() {
		return comunidadseleccionada;
	}

	public void setComunidadseleccionada(ComunidadTabla comunidadseleccionada) {
		this.comunidadseleccionada = comunidadseleccionada;
	}

	public ArrayList<ComunidadTabla> getTablacomunidades() {
		return tablacomunidades;
	}

	public void setTablacomunidades(ArrayList<ComunidadTabla> tablacomunidades) {
		this.tablacomunidades = tablacomunidades;
	}

	public Map<String,String> getTiposcocldoHM() {
		return tiposcocldoHM;
	}

	public void setTiposcocldoHM(Map<String,String> tiposcocldoHM) {
		this.tiposcocldoHM = tiposcocldoHM;
	}

}
