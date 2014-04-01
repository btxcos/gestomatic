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
import com.provisiones.ll.CLCuotas;
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Sesion;
import com.provisiones.misc.Utils;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.ComunidadTabla;
import com.provisiones.types.tablas.CuotaTabla;

public class GestorListaCuotas implements Serializable 
{

	private static final long serialVersionUID = -3898004614900539442L;

	private static Logger logger = LoggerFactory.getLogger(GestorListaCuotas.class.getName());
	
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
	
	private String sNURCAT = "";
	
	//Buscar comunidad
	private String sCOCLDO = "";
	private String sNUDCOM = "";
	private String sNOMCOC = "";
	
	
	private transient ActivoTabla activoseleccionado = null;
	private transient ArrayList<ActivoTabla> tablaactivos = null;
	
	private transient ComunidadTabla comunidadseleccionada = null;
	private transient ArrayList<ComunidadTabla> tablacomunidades = null;

	private transient CuotaTabla cuotaseleccionada = null;
	private transient ArrayList<CuotaTabla> tablacuotas = null;
	
	private Map<String,String> tiposcocldoHM = new LinkedHashMap<String, String>();
	
	public GestorListaCuotas()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorListaCuotas...");
			
			tiposcocldoHM.put("C.I.F.",                     "2");
			tiposcocldoHM.put("C.I.F pa�s extranjero.",     "5");
			tiposcocldoHM.put("Otros persona jur�dica.",    "J");
		}
	}
	
	public void borrarCamposFiltroActivo()
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
		this.sCOACES = "";
		
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	borrarCamposFiltroActivo();
    	borrarResultadosActivo();
    }
    
	public void borrarCamposFiltroComunidad()
	{
		this.sNOMCOC = "";
	}
	
	
	public void borrarResultadosComunidad()
	{
		this.sCOCLDO = "";
		this.sNUDCOM = "";
		
    	this.comunidadseleccionada = null;
    	this.tablacomunidades = null;
	}
	
    public void limpiarPlantillaComunidad(ActionEvent actionEvent) 
    {  
    	borrarCamposFiltroComunidad();
    	borrarResultadosComunidad();
    }
    
    
	public void borrarCamposCuota()
	{
		this.sCOACES = "";
    	this.sCOCLDO = "";
    	this.sNUDCOM = "";
    	
    	this.setCuotaseleccionada(null);
    	this.setTablacuotas(null);
	}
    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {
    	borrarCamposFiltroActivo();
    	borrarResultadosActivo();
    	borrarCamposFiltroComunidad();
    	borrarResultadosComunidad();
    	borrarCamposCuota();
    }
    
	public void buscarActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.activoseleccionado = null;
			
			if (sNURCAT.isEmpty())
			{
				ActivoTabla filtro = new ActivoTabla(
						sCOACES,
						sCOPOIN.toUpperCase(),
						sNOMUIN.toUpperCase(),
						sNOPRAC.toUpperCase(),
						sNOVIAS.toUpperCase(),
						sNUPIAC.toUpperCase(), 
						sNUPOAC.toUpperCase(),
						sNUPUAC.toUpperCase(), 
						"");
				
				this.setTablaactivos(CLCuotas.buscarActivosConCuotas(filtro));

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
			else if (CLReferencias.existeReferenciaCatastral(sNURCAT))
			{
				this.setTablaactivos(CLReferencias.buscarActivoAsociadoConComunidad(sNURCAT));
				
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
		    	this.setTablaactivos(null);
				
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
	
	public void buscarComunidad (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.comunidadseleccionada = null;
			
			this.setTablacomunidades(CLComunidades.buscarComunidadCuotasConNombre(sNOMCOC.toUpperCase()));
			
			if (getTablacomunidades().size() == 0)
			{
				sMsg = "No se encontraron Comunidades con los criterios solicitados.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
			}
			else if (getTablacomunidades().size() == 1)
			{
				sMsg = "Encontrada una Comunidad relacionada.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}
			else
			{
				sMsg = "Encontradas "+getTablacomunidades().size()+" Comunidades relacionadas.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}

			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	
	public void seleccionarComunidad(ActionEvent actionEvent) 
    {
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
	    	
	    	this.sCOCLDO  = comunidadseleccionada.getCOCLDO();
	    	this.sNUDCOM  = comunidadseleccionada.getNUDCOM();
	    	
	    	String sMsg = "Comunidad Seleccionada.";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	logger.info(sMsg);
	    	
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	
	public void buscarCuotasActivo(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			if (sCOACES.isEmpty())
			{
				sMsg = "ERROR: Debe informar el Activo para realizar una b�squeda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else
			{
				try
				{
					if (CLActivos.existeActivo(Integer.parseInt(sCOACES)))
					{
						this.tablacuotas = CLCuotas.buscarCuotasActivo(Integer.parseInt(sCOACES));
						
						if (getTablacuotas().size() == 0)
						{
							sMsg = "No se encontraron Cuotas con los criterios solicitados.";
							msg = Utils.pfmsgWarning(sMsg);
							logger.warn(sMsg);
						}
						else if (getTablacuotas().size() == 1)
						{
							sMsg = "Encontrada una Cuota relacionada.";
							msg = Utils.pfmsgInfo(sMsg);
							logger.info(sMsg);
						}
						else
						{
							sMsg = "Encontradas "+getTablaactivos().size()+" Cuotas relacionadas.";
							msg = Utils.pfmsgInfo(sMsg);
							logger.info(sMsg);
						}
					}
					else
					{
						sMsg = "El Activo '"+sCOACES+"' no pertenece a la cartera. Por favor, revise los datos.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);
					}
					

				}
				catch(NumberFormatException nfe)
				{
					sMsg = "ERROR: El activo debe ser num�rico. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}				
			}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void buscarCuotasComunidad(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";

			if (sCOCLDO.isEmpty() || sNUDCOM.isEmpty())
			{
				sMsg = "ERROR: Los campos 'Documento' y 'N�mero' deben de ser informados para realizar la b�squeda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else if (!CLComunidades.existeComunidad(sCOCLDO, sNUDCOM.toUpperCase()))
			{
				sMsg = "La comunidad informada no est� dada de alta. Por favor, revise los datos.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
			}
			else
			{
				this.tablacuotas = CLCuotas.buscarCuotasComunidad(sCOCLDO,sNUDCOM.toUpperCase());
				
				sMsg = "Encontradas "+getTablacuotas().size()+" cuotas relacionadas.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void cargarDetallesCuota(ActionEvent actionEvent) 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
			String sMsg = "";
			
			if (cuotaseleccionada != null)
			{

				this.sCOACES = cuotaseleccionada.getCOACES();
		    	this.sCOCLDO  = cuotaseleccionada.getCOCLDO();
		    	this.sNUDCOM  = cuotaseleccionada.getNUDCOM();
		    	
		    	logger.debug("sCOACES:|"+sCOACES+"|");
		    	logger.debug("sCOCLDO:|"+sCOCLDO+"|");
		    	logger.debug("sNUDCOM:|"+sNUDCOM+"|");
		    	logger.debug("sCOSBAC:|"+cuotaseleccionada.getCOSBAC()+"|");
	
		    	String sCodCuota = cuotaseleccionada.getsCuotaID();
		    	
		    	logger.debug("sCodCuota:|"+sCodCuota+"|");
		    	
		    	Sesion.guardaDetalle(sCodCuota);
		    	Sesion.limpiarHistorial();
		    	Sesion.guardarHistorial("listacuotas.xhtml","GestorDetallesCuota");

		    	sPagina = "detallescuota.xhtml";
		    	
		    	
				try 
				{
					sMsg = "Redirigiendo hacia '"+sPagina+"'";
					logger.info(sMsg);
					FacesContext.getCurrentInstance().getExternalContext().redirect(sPagina);
				}
				catch (IOException e)
				{
					FacesMessage msg;
					
					sMsg = "ERROR: Ocurri� un problema al acceder a los detalles. Por favor, avise a soporte.";
					
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					
					FacesContext.getCurrentInstance().addMessage(null, msg);
					

				}
			}
			else
			{
				FacesMessage msg;

				sMsg = "ERROR: No se ha seleccionado una Cuota.";
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

	public String getsNURCAT() {
		return sNURCAT;
	}

	public void setsNURCAT(String sNURCAT) {
		this.sNURCAT = sNURCAT;
	}

	public String getsNOMCOC() {
		return sNOMCOC;
	}

	public void setsNOMCOC(String sNOMCOC) {
		this.sNOMCOC = sNOMCOC;
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
