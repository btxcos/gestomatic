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
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Transicion;
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
	private String sNUFIRE = "";
	
	private String sNURCAT = "";
	
	//Buscar comunidad
	private String sCOCLDO = "";
	private String sNUDCOM = "";
	private String sNOMCOC = "";
	
	private int iCOACES = 0;
	
	
	//Filtro cuotas activo
	private String sCOSBACFA = "";
	private String sFIPAGOFA = "";
	private String sFFPAGOFA = "";
	private String sIMCUCOFA = "";
	private String sFAACTAFA = "";
	private String sPTPAGOFA = "";
	private String sComparadorFA = "";
	private boolean bSeleccionadoFA = true; 

	//Filtro cuotas comunidad
	private String sCOACESFC = "";
	private String sCOSBACFC = "";
	private String sFIPAGOFC = "";
	private String sFFPAGOFC = "";
	private String sIMCUCOFC = "";
	private String sFAACTAFC = "";
	private String sPTPAGOFC = "";
	private String sComparadorFC = "";
	private boolean bSeleccionadoFC = true;
	
	
	private transient ActivoTabla activoseleccionado = null;
	private transient ArrayList<ActivoTabla> tablaactivos = null;
	
	private transient ComunidadTabla comunidadseleccionada = null;
	private transient ArrayList<ComunidadTabla> tablacomunidades = null;

	private transient CuotaTabla cuotaseleccionada = null;
	private transient ArrayList<CuotaTabla> tablacuotas = null;
	
	private Map<String,String> tiposcocldoHM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposcosbacHM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposptpagoHM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposcomparaimporteHM = new LinkedHashMap<String, String>();
	
	public GestorListaCuotas()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorListaCuotas...");
			
			tiposcocldoHM.put("C.I.F.",                     "2");
			tiposcocldoHM.put("C.I.F país extranjero.",     "5");
			tiposcocldoHM.put("Otros persona jurídica.",    "J");
			
			tiposcosbacHM.put("Comunidad",	                   	"0");  
			tiposcosbacHM.put("Ordinaria",                     	"1");  
			tiposcosbacHM.put("Extras Comunidad",              	"2");  
			tiposcosbacHM.put("Mancomunidad",                  	"3");  
			tiposcosbacHM.put("Extras Mancomunidad",           	"4");  
			tiposcosbacHM.put("Obras comunidad",               	"5");
			
			tiposptpagoHM.put("APERIODICO",      "1");
			tiposptpagoHM.put("MENSUAL",         "2");
			tiposptpagoHM.put("BIMENSUAL",       "3");
			tiposptpagoHM.put("TRIMESTRAL",      "4");
			tiposptpagoHM.put("CUATRIMESTRAL",   "5");
			tiposptpagoHM.put("SEMESTRAL",       "6");
			tiposptpagoHM.put("ANUAL",           "7");
			tiposptpagoHM.put("VARIOS PERIODOS", "8");
			
			tiposcomparaimporteHM.put("Igual a",    		"=");
			tiposcomparaimporteHM.put("Mayor o igual a",	">=");
			tiposcomparaimporteHM.put("Menor o igual a",	"<=");
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
		this.sNUFIRE = "";
	}
	
	public void borrarResultadosActivo()
	{
		this.sCOACES = "";
		this.iCOACES = 0;
		
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	borrarCamposFiltroActivo();
    	borrarResultadosActivo();
    }
    
	public void borrarCamposFiltroCuotasActivo()
	{
		this.sCOSBACFA = "";
		this.sFIPAGOFA = "";
		this.sFFPAGOFA = "";
		this.sIMCUCOFA = "";
		this.sFAACTAFA = "";
		this.sPTPAGOFA = "";
		this.sComparadorFA = "";
		this.bSeleccionadoFA = true; 

	}
	
    public void limpiarPlantillaFiltroCuotasActivo(ActionEvent actionEvent) 
    {  
    	borrarCamposFiltroCuotasActivo();
    }
    
	public void borrarCamposFiltroCuotasComunidad()
	{
		this.sCOACESFC = "";
		this.sCOSBACFC = "";
		this.sFIPAGOFC = "";
		this.sFFPAGOFC = "";
		this.sIMCUCOFC = "";
		this.sFAACTAFC = "";
		this.sPTPAGOFC = "";
		this.sComparadorFC = "";
		this.bSeleccionadoFC = true; 
	}
	
    public void limpiarPlantillaFiltroCuotasComunidad(ActionEvent actionEvent) 
    {  
    	borrarCamposFiltroCuotasComunidad();
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
		this.iCOACES = 0;
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
    	borrarCamposFiltroCuotasActivo();
    	borrarCamposFiltroCuotasComunidad();
    }
    
	public void buscarActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.activoseleccionado = null;
			
			this.setTablaactivos(null);
			
			if (sNURCAT.isEmpty())
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
			
			this.setTablacomunidades(CLComunidades.buscarComunidadCuotasConNombre(sNOMCOC));
			
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
			
			if (comunidadseleccionada == null)
			{
				logger.debug("NULACO!!");
			}
			else
			{
				logger.debug("sCOCLDO:|"+comunidadseleccionada.getCOCLDO()+"|");
				logger.debug("sNUDCOM:|"+comunidadseleccionada.getNUDCOM()+"|");
			}
			
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
			
			this.cuotaseleccionada = null;

			this.tablacuotas = null;
			
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
		    		this.iCOACES = Integer.parseInt(sCOACES);
		    		
					if (CLActivos.existeActivo(iCOACES))
					{
						try
						{
							if (CLActivos.existeActivo(iCOACES))
							{
								String sImporte = "";
								
								if (!sComparadorFA.isEmpty())
								{
									sImporte = Utils.compruebaImporte(sIMCUCOFA);
								}
								
								CuotaTabla filtro = new CuotaTabla(
										"",
										sCOACES,   
										"",
										"",
										"",   
										sCOSBACFA,
										"",
										Utils.compruebaFecha(sFIPAGOFA),  
										Utils.compruebaFecha(sFFPAGOFA),   
										sImporte,  
										Utils.compruebaFecha(sFAACTAFA),   
										sPTPAGOFA,
										"",
										"",
										"");
								
								//this.tablacuotas = CLCuotas.buscarCuotasActivo(iCOACES);
								this.tablacuotas = CLCuotas.buscarCuotasActivoConFiltro(filtro, sComparadorFA);
								
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
									sMsg = "Encontradas "+getTablacuotas().size()+" Cuotas relacionadas.";
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
							sMsg = "ERROR: El activo debe ser numérico. Por favor, revise los datos.";
							msg = Utils.pfmsgError(sMsg);
							logger.error(sMsg);
						}				
					}
					else
					{
						sMsg = "El Activo '"+sCOACES+"' no pertenece a la cartera. Por favor, revise los datos.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);
						
				    	this.setTablacuotas(null);
					} 
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
	
	public void buscarCuotasComunidad(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.cuotaseleccionada = null;
			
			this.tablacuotas = null;

			if (sCOCLDO.isEmpty() || sNUDCOM.isEmpty())
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
				String sImporte = "";
				
				if (!sComparadorFC.isEmpty())
				{
					sImporte = Utils.compruebaImporte(sIMCUCOFC);
				}
				
				CuotaTabla filtro = new CuotaTabla(
						"",
						sCOACESFC,   
						sCOCLDO,
						"",
						sNUDCOM.toUpperCase(),   
						sCOSBACFC,
						"",
						Utils.compruebaFecha(sFIPAGOFC),  
						Utils.compruebaFecha(sFFPAGOFC),   
						sImporte,  
						Utils.compruebaFecha(sFAACTAFC),   
						sPTPAGOFC,
						"",
						"",
						"");
				
				//this.tablacuotas = CLCuotas.buscarCuotasComunidad(sCOCLDO,sNUDCOM.toUpperCase());
				this.tablacuotas = CLCuotas.buscarCuotasComunidadConFiltro(filtro,sComparadorFC);
				
				
				sMsg = "Encontradas "+getTablacuotas().size()+" cuotas relacionadas.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void cambiaComparadorFA()
	{
		this.bSeleccionadoFA = this.sComparadorFA.isEmpty();
		logger.debug("sComparadorFA:|"+sComparadorFA+"|");
	}
	
	public void cambiaComparadorFC()
	{
		this.bSeleccionadoFC = this.sComparadorFC.isEmpty();
	}
	
	public void hoyFIPAGOFA (ActionEvent actionEvent)
	{
		this.setsFIPAGOFA(Utils.fechaDeHoy(true));
		logger.debug("sFIPAGOFA:|"+sFIPAGOFA+"|");
	}
	
	public void hoyFFPAGOFA (ActionEvent actionEvent)
	{
		this.setsFFPAGOFA(Utils.fechaDeHoy(true));
		logger.debug("sFFPAGOFA:|"+sFFPAGOFA+"|");
	}
	
	public void hoyFAACTAFA (ActionEvent actionEvent)
	{
		this.setsFAACTAFA(Utils.fechaDeHoy(true));
		logger.debug("sFAACTAFA:|"+sFAACTAFA+"|");
	}
	
	public void hoyFIPAGOFC (ActionEvent actionEvent)
	{
		this.setsFIPAGOFC(Utils.fechaDeHoy(true));
		logger.debug("sFIPAGOFC:|"+sFIPAGOFC+"|");
	}
	
	public void hoyFFPAGOFC (ActionEvent actionEvent)
	{
		this.setsFFPAGOFC(Utils.fechaDeHoy(true));
		logger.debug("sFFPAGOFC:|"+sFFPAGOFC+"|");
	}
	
	public void hoyFAACTAFC (ActionEvent actionEvent)
	{
		this.setsFAACTAFC(Utils.fechaDeHoy(true));
		logger.debug("sFAACTA:|"+sFAACTAFC+"|");
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

		    	Transicion transicion = new Transicion (
		    			sCodCuota,
		    			ValoresDefecto.ID_CUOTA,
		    			"listacuotas.xhtml",
		    			"GestorDetallesCuota");
		    	
		    	Sesion.guardarTransicion(transicion, true);
		    	
		    	//Sesion.guardaDetalle(sCodCuota);
		    	//Sesion.limpiarHistorial();
		    	//Sesion.guardarHistorial("listacuotas.xhtml","GestorDetallesCuota");

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
					
					sMsg = "ERROR: Ocurrió un problema al acceder a los detalles. Por favor, avise a soporte.";
					
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
		this.sCOACES = sCOACES.trim();
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
		this.sNUDCOM = sNUDCOM.trim().toUpperCase();
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
		this.sNOMCOC = sNOMCOC.trim().toUpperCase();
	}

	public String getsCOSBACFA() {
		return sCOSBACFA;
	}

	public void setsCOSBACFA(String sCOSBACFA) {
		this.sCOSBACFA = sCOSBACFA;
	}

	public String getsFIPAGOFA() {
		return sFIPAGOFA;
	}

	public void setsFIPAGOFA(String sFIPAGOFA) {
		this.sFIPAGOFA = sFIPAGOFA;
	}
	
	public String getsFFPAGOFA() {
		return sFFPAGOFA;
	}

	public void setsFFPAGOFA(String sFFPAGOFA) {
		this.sFFPAGOFA = sFFPAGOFA;
	}

	public String getsIMCUCOFA() {
		return sIMCUCOFA;
	}

	public void setsIMCUCOFA(String sIMCUCOFA) {
		this.sIMCUCOFA = sIMCUCOFA;
	}

	public String getsFAACTAFA() {
		return sFAACTAFA;
	}

	public void setsFAACTAFA(String sFAACTAFA) {
		this.sFAACTAFA = sFAACTAFA;
	}

	public String getsPTPAGOFA() {
		return sPTPAGOFA;
	}

	public void setsPTPAGOFA(String sPTPAGOFA) {
		this.sPTPAGOFA = sPTPAGOFA;
	}

	public String getsComparadorFA() {
		return sComparadorFA;
	}

	public void setsComparadorFA(String sComparadorFA) {
		this.sComparadorFA = sComparadorFA;
	}

	public boolean isbSeleccionadoFA() {
		return bSeleccionadoFA;
	}

	public void setbSeleccionadoFA(boolean bSeleccionadoFA) {
		this.bSeleccionadoFA = bSeleccionadoFA;
	}

	public String getsCOSBACFC() {
		return sCOSBACFC;
	}

	public String getsCOACESFC() {
		return sCOACESFC;
	}

	public void setsCOACESFC(String sCOACESFC) {
		this.sCOACESFC = sCOACESFC;
	}

	public void setsCOSBACFC(String sCOSBACFC) {
		this.sCOSBACFC = sCOSBACFC;
	}

	public String getsFIPAGOFC() {
		return sFIPAGOFC;
	}

	public void setsFIPAGOFC(String sFIPAGOFC) {
		this.sFIPAGOFC = sFIPAGOFC;
	}

	public String getsFFPAGOFC() {
		return sFFPAGOFC;
	}

	public void setsFFPAGOFC(String sFFPAGOFC) {
		this.sFFPAGOFC = sFFPAGOFC;
	}

	public String getsIMCUCOFC() {
		return sIMCUCOFC;
	}

	public void setsIMCUCOFC(String sIMCUCOFC) {
		this.sIMCUCOFC = sIMCUCOFC;
	}

	public String getsFAACTAFC() {
		return sFAACTAFC;
	}

	public void setsFAACTAFC(String sFAACTAFC) {
		this.sFAACTAFC = sFAACTAFC;
	}

	public String getsPTPAGOFC() {
		return sPTPAGOFC;
	}

	public void setsPTPAGOFC(String sPTPAGOFC) {
		this.sPTPAGOFC = sPTPAGOFC;
	}

	public String getsComparadorFC() {
		return sComparadorFC;
	}

	public void setsComparadorFC(String sComparadorFC) {
		this.sComparadorFC = sComparadorFC;
	}

	public boolean isbSeleccionadoFC() {
		return bSeleccionadoFC;
	}

	public void setbSeleccionadoFC(boolean bSeleccionadoFC) {
		this.bSeleccionadoFC = bSeleccionadoFC;
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

	public Map<String, String> getTiposcosbacHM() {
		return tiposcosbacHM;
	}

	public void setTiposcosbacHM(Map<String, String> tiposcosbacHM) {
		this.tiposcosbacHM = tiposcosbacHM;
	}

	public Map<String,String> getTiposptpagoHM() {
		return tiposptpagoHM;
	}

	public void setTiposptpagoHM(Map<String,String> tiposptpagoHM) {
		this.tiposptpagoHM = tiposptpagoHM;
	}

	public Map<String, String> getTiposcomparaimporteHM() {
		return tiposcomparaimporteHM;
	}

	public void setTiposcomparaimporteHM(Map<String, String> tiposcomparaimporteHM) {
		this.tiposcomparaimporteHM = tiposcomparaimporteHM;
	}

}
