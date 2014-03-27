package com.provisiones.pl.listas;

import java.io.IOException;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLActivos;
import com.provisiones.ll.CLComunidades;
import com.provisiones.ll.CLGastos;
import com.provisiones.ll.CLProvisiones;
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Sesion;
import com.provisiones.misc.Utils;

import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.ComunidadTabla;
import com.provisiones.types.tablas.ProvisionTabla;

public class GestorListaProvisiones implements Serializable 
{
	private static final long serialVersionUID = 4042070631372501791L;

	private static Logger logger = LoggerFactory.getLogger(GestorListaProvisiones.class.getName());

	
	private String sNUPROF = "";
	
	//Buscar Activos
	private String sCOACESB = "";

	//Filtro Activo
	private String sNOMUINF = "";
	private String sCOPOINF = "";
	private String sNOPRACF = "";
	private String sNOVIASF = "";
	private String sNUPIACF = "";
	private String sNUPOACF = "";
	private String sNUPUACF = "";
	
	private String sNURCATF = "";
	
	//Filtro Provision Activos
	private String sFEPFONFA = "";
	private String sEstadoProvisionFA = "";
	
	//Buscar Comunidades
	private String sCOCLDOB = "";
	private String sNUDCOMB = "";
	
	//Filtro Comunidad
	private String sNOMCOCF = "";
	private String sNOMPRCF = "";
	private String sNOMADCF = "";

	//Filtro Provision Comunidades
	private String sFEPFONFC = "";
	private String sEstadoProvisionFC = "";
	
	//Buscar Provision
	private String sNUPROFB = "";
	
	//Filtro Provision
	private String sFEPFONF = "";
	private String sEstadoProvisionF = "";

	private transient ActivoTabla activoseleccionado = null;
	private transient ArrayList<ActivoTabla> tablaactivos = null;
	
	private transient ComunidadTabla comunidadseleccionada = null;
	private transient ArrayList<ComunidadTabla> tablacomunidades = null;

	private transient ProvisionTabla provisionseleccionada = null;
	private transient ArrayList<ProvisionTabla> tablaprovisiones = null;
	
	private Map<String,String> tiposcocldoHM = new LinkedHashMap<String, String>();

	private Map<String,String> tiposestadoprovisionHM = new LinkedHashMap<String, String>();
	
	public GestorListaProvisiones()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorListaProvisiones...");	

			tiposcocldoHM.put("C.I.F.",                     "2");
			tiposcocldoHM.put("C.I.F país extranjero.",     "5");
			tiposcocldoHM.put("Otros persona jurídica.",    "J");
			
			tiposestadoprovisionHM.put("ABIERTA",	"P");
			tiposestadoprovisionHM.put("ENVIADA",	"E");
			tiposestadoprovisionHM.put("AUTORIZADA","T");
			tiposestadoprovisionHM.put("PAGADA",	"G");
		}
	}
	

	public void borrarCamposActivo()
	{
		this.sCOACESB = "";
		
		this.sCOPOINF = "";
		this.sNOMUINF = "";
		this.sNOPRACF = "";
		this.sNOVIASF = "";
		this.sNUPIACF = "";
		this.sNUPOACF = "";
		this.sNUPUACF = "";
    	
    	this.setActivoseleccionado(null);
    	this.setTablaactivos(null);
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	borrarCamposActivo();
    }
    
	public void borrarCamposComunidad()
	{
		this.sCOCLDOB = "";
		this.sNUDCOMB = "";

		this.sNOMCOCF = "";
		this.sNOMPRCF = "";
		this.sNOMADCF = "";
		
    	this.setComunidadseleccionada(null);
    	this.setTablacomunidades(null);
	}
    
    public void limpiarPlantillaComunidad(ActionEvent actionEvent) 
    {  
    	borrarCamposComunidad();
    }
    
	public void borrarCamposProvision()
	{
		this.sNUPROFB = "";
		
		this.sFEPFONF = "";
		this.sEstadoProvisionF = "";
    	
    	this.setProvisionseleccionada(null);
    	this.setTablaprovisiones(null);
	}
	
    public void limpiarPlantillaProvision(ActionEvent actionEvent) 
    {  
    	borrarCamposProvision();
    }
    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	borrarCamposComunidad();
    	borrarCamposActivo();
    	borrarCamposProvision();
    }	
	
	public void buscarActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
	    	this.setActivoseleccionado(null);
			
			if (sNURCATF.isEmpty())
			{
				ActivoTabla filtro = new ActivoTabla(
						"", 
						sCOPOINF.toUpperCase(), 
						sNOMUINF.toUpperCase(),
						sNOPRACF.toUpperCase(), 
						sNOVIASF.toUpperCase(), 
						sNUPIACF.toUpperCase(), 
						sNUPOACF.toUpperCase(), 
						sNUPUACF.toUpperCase(), 
						"");
				
				this.setTablaactivos(CLGastos.buscarActivosConGastosAutorizados(filtro));

				sMsg = "Encontrados "+getTablaactivos().size()+" Activos relacionados.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}
			else if (CLReferencias.existeReferenciaCatastral(sNURCATF))
			{
				this.setTablaactivos(CLReferencias.buscarActivoAsociadoConProvisiones(sNURCATF));
				
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
	    	
	    	this.sCOACESB  = activoseleccionado.getCOACES();
	    	
	    	String sMsg = "Activo '"+sCOACESB+"' seleccionado.";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	logger.info(sMsg);
	    	
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }

	public void buscarComunidades(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.comunidadseleccionada = null;
			
			ComunidadTabla filtro = new ComunidadTabla(
					"",
					"", 
					"",
					"",
					sNOMCOCF,
					sNOMPRCF,
					sNOMADCF,
					"");
			
			this.setTablacomunidades(CLComunidades.buscarComunidadesProvisionadasConFiltro(filtro));
			
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
			
			String sMsg = "";
			
			this.sCOCLDOB = comunidadseleccionada.getCOCLDO();
			this.sNUDCOMB = comunidadseleccionada.getNUDCOM();
			
			logger.error("sCOCLDOB:|"+sCOCLDOB+"|");
			logger.error("sNUDCOMB:|"+sNUDCOMB+"|");
			
			sMsg = "La Comunidad '"+comunidadseleccionada.getNOMCOC()+"' se ha cargado correctamente.";
			msg = Utils.pfmsgInfo(sMsg);
			logger.info(sMsg);

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}				
	}
	
	public void buscarProvisionesActivo(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;

			String sMsg = "";
			
			this.provisionseleccionada = null;
			
			if (sCOACESB.isEmpty())
			{
				sMsg = "ERROR: El campo 'Activo' debe de ser informado para realizar la búsqueda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
				
				this.tablaprovisiones = null;
			}
			else
			{
				try
				{
					int iCOACES = Integer.parseInt(sCOACESB);
					
					if (CLActivos.existeActivo(iCOACES))
					{
						String sFecha = Utils.compruebaFecha(sFEPFONFA);
						
						this.setProvisionseleccionada(null);
						
						if (sFecha.equals("#"))
						{
							sMsg = "La fecha proporcionada no es válida. Por favor, revise los datos.";
							msg = Utils.pfmsgError(sMsg);
							logger.error(sMsg);
							
					    	this.setTablaprovisiones(null);
						}
						else
						{
							
							String sNUPROFF = "";
							String sCOSPATF = "";
							String sDCOSPATF = "";
							String sTASF = "";
							String sDTASF = "";	
							String sCOGRUGF = "";
							String sDCOGRUGF = "";	
							String sCOTPGAF = "";
							String sDCOTPGAF = "";
							String sFEPFONF = sFecha;
							String sVALORF = "";
							String sGASTOSF = "";
							String sESTADOF = sEstadoProvisionFA;
							
							logger.debug("sFEPFONF:|"+sFEPFONF+"|");
							logger.debug("sESTADOF:|"+sESTADOF+"|");
							
							ProvisionTabla filtro = new ProvisionTabla(
									sNUPROFF, 
									sCOSPATF, 
									sDCOSPATF,
									sTASF, 
									sDTASF, 
									sCOGRUGF, 
									sDCOGRUGF, 
									sCOTPGAF, 
									sDCOTPGAF, 
									sFEPFONF, 
									sVALORF, 
									sGASTOSF,
									sESTADOF);

							this.tablaprovisiones = CLProvisiones.buscarProvisionesActivoConFiltro(iCOACES, filtro); 

							sMsg = "Encontradas "+getTablaprovisiones().size()+" provisiones relacionadas.";
							msg = Utils.pfmsgInfo(sMsg);
							logger.info(sMsg);
						}
						

					}
					else
					{
						sMsg = "El Activo '"+sCOACESB+"' no pertenece a la cartera. Por favor, revise los datos.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);
						
						this.tablaprovisiones = null;
					}
				}
				catch(NumberFormatException nfe)
				{
					sMsg = "ERROR: El Activo debe ser numérico. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					
					this.tablaprovisiones = null;
				}
			}

			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}
	}
	
	
	
	public void buscarProvisionesComunidad(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;

			String sMsg = "";
			
			this.provisionseleccionada = null;
			
			if (sCOCLDOB.isEmpty() || sNUDCOMB.isEmpty())
			{
				sMsg = "ERROR: Los campos 'Documento' y 'Número' deben de ser informados para realizar la búsqueda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
				
				this.tablaprovisiones = null;
			}
			else if (!CLComunidades.existeComunidad(sCOCLDOB, sNUDCOMB.toUpperCase()))
			{
				sMsg = "La comunidad informada no está dada de alta. Por favor, revise los datos.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
				
				this.tablaprovisiones = null;
			}
			else
			{
				String sFecha = Utils.compruebaFecha(sFEPFONFC);
				
				this.setProvisionseleccionada(null);
				
				if (sFecha.equals("#"))
				{
					sMsg = "La fecha proporcionada no es válida. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					
			    	this.setTablaprovisiones(null);
				}
				else
				{
					long liCodComunidad = CLComunidades.buscarCodigoComunidad(sCOCLDOB, sNUDCOMB);
					
					String sNUPROFF = "";
					String sCOSPATF = "";
					String sDCOSPATF = "";
					String sTASF = "";
					String sDTASF = "";	
					String sCOGRUGF = "";
					String sDCOGRUGF = "";	
					String sCOTPGAF = "";
					String sDCOTPGAF = "";
					String sFEPFONF = sFecha;
					String sVALORF = "";
					String sGASTOSF = "";
					String sESTADOF = sEstadoProvisionFC;
					
					logger.debug("sFEPFONF:|"+sFEPFONF+"|");
					logger.debug("sESTADOF:|"+sESTADOF+"|");
					
					ProvisionTabla filtro = new ProvisionTabla(
							sNUPROFF, 
							sCOSPATF, 
							sDCOSPATF,
							sTASF, 
							sDTASF, 
							sCOGRUGF, 
							sDCOGRUGF, 
							sCOTPGAF, 
							sDCOTPGAF, 
							sFEPFONF, 
							sVALORF, 
							sGASTOSF,
							sESTADOF);

					this.tablaprovisiones = CLProvisiones.buscarProvisionesComunidadConFiltro(liCodComunidad, filtro); 

					sMsg = "Encontradas "+getTablaprovisiones().size()+" provisiones relacionadas.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
				}
				

			}

			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}
	}
	
	public void buscarProvisiones(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;

			String sMsg = "";
			
			this.provisionseleccionada = null;
			
			String sFecha = Utils.compruebaFecha(sFEPFONF);
			
			this.setProvisionseleccionada(null);
			
			if (sFecha.equals("#"))
			{
				sMsg = "La fecha proporcionada no es válida. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
				
		    	this.setTablaprovisiones(null);
			}
			else
			{
				
				String sNUPROFF = "";
				String sCOSPATF = "";
				String sDCOSPATF = "";
				String sTASF = "";
				String sDTASF = "";	
				String sCOGRUGF = "";
				String sDCOGRUGF = "";	
				String sCOTPGAF = "";
				String sDCOTPGAF = "";
				String sFEPFONF = sFecha;
				String sVALORF = "";
				String sGASTOSF = "";
				String sESTADOF = sEstadoProvisionF;
				
				logger.debug("sFEPFONF:|"+sFEPFONF+"|");
				logger.debug("sESTADOF:|"+sESTADOF+"|");
				
				ProvisionTabla filtro = new ProvisionTabla(
						sNUPROFF, 
						sCOSPATF, 
						sDCOSPATF,
						sTASF, 
						sDTASF, 
						sCOGRUGF, 
						sDCOGRUGF, 
						sCOTPGAF, 
						sDCOTPGAF, 
						sFEPFONF, 
						sVALORF, 
						sGASTOSF,
						sESTADOF);

				this.tablaprovisiones = CLProvisiones.buscarProvisionesConFiltro(filtro); 

				sMsg = "Encontradas "+getTablaprovisiones().size()+" provisiones relacionadas.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}

			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}
	}
	
	public void comprobarProvision(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.provisionseleccionada = null;
			
			if (sNUPROFB.isEmpty())
			{
				sMsg = "ERROR: Debe informar la Provision para realizar una búsqueda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
				
				this.tablaprovisiones = null;
			}
			else
			{
				try
				{
					if (CLProvisiones.existeProvision(sNUPROFB))
					{
						this.tablaprovisiones = CLProvisiones.buscarProvisionUnica(sNUPROFB); 
						
						if (getTablaprovisiones().size() == 0)
						{
							sMsg = "ERROR: No se encontraron Provisiones con los criterios solicitados. Por favor, revise los datos y avise a soporte.";
							msg = Utils.pfmsgFatal(sMsg);
							logger.error(sMsg);
						}
						else 
						{
							sMsg = "Encontrada una Provisión relacionada.";
							msg = Utils.pfmsgInfo(sMsg);
							logger.info(sMsg);
						}
					}
					else
					{
						sMsg = "La Provisión '"+sNUPROF+"' no se encuentra regristada en el sistema. Por favor, revise los datos.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);
						
						this.tablaprovisiones = null;
					}
					
				}
				catch(NumberFormatException nfe)
				{
					sMsg = "ERROR: La Provisión debe ser numérica. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					
					this.tablaprovisiones = null;
				}
				
			}

			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}
	}
	
	public void cargarDetalles(ActionEvent actionEvent) 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
			if (provisionseleccionada != null)
			{
				this.sNUPROF  = provisionseleccionada.getNUPROF();
				logger.debug("sNUPROF:|"+sNUPROF+"|");
		    	
		    	Sesion.guardaDetalle(sNUPROF);
		    	Sesion.limpiarHistorial();
		    	Sesion.guardarHistorial("listaprovisiones.xhtml","GestorDetallesProvision");

		    	sPagina = "detallesprovision.xhtml";
		    	
				try 
				{
			    	logger.debug("Redirigiendo...");
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

				msg = Utils.pfmsgWarning("No se ha seleccionado una provisión.");
				
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			


		}

    }


	public String getsCOACESB() {
		return sCOACESB;
	}


	public void setsCOACESB(String sCOACESB) {
		this.sCOACESB = sCOACESB.trim();
	}


	public String getsNOMUINF() {
		return sNOMUINF;
	}


	public void setsNOMUINF(String sNOMUINF) {
		this.sNOMUINF = sNOMUINF.trim().toUpperCase();
	}


	public String getsCOPOINF() {
		return sCOPOINF;
	}


	public void setsCOPOINF(String sCOPOINF) {
		this.sCOPOINF = sCOPOINF.trim().toUpperCase();
	}


	public String getsNOPRACF() {
		return sNOPRACF;
	}


	public void setsNOPRACF(String sNOPRACF) {
		this.sNOPRACF = sNOPRACF.trim().toUpperCase();
	}


	public String getsNOVIASF() {
		return sNOVIASF;
	}


	public void setsNOVIASF(String sNOVIASF) {
		this.sNOVIASF = sNOVIASF.trim().toUpperCase();
	}


	public String getsNUPIACF() {
		return sNUPIACF;
	}


	public void setsNUPIACF(String sNUPIACF) {
		this.sNUPIACF = sNUPIACF.trim().toUpperCase();
	}


	public String getsNUPOACF() {
		return sNUPOACF;
	}


	public void setsNUPOACF(String sNUPOACF) {
		this.sNUPOACF = sNUPOACF.trim().toUpperCase();
	}


	public String getsNUPUACF() {
		return sNUPUACF;
	}


	public void setsNUPUACF(String sNUPUACF) {
		this.sNUPUACF = sNUPUACF.trim().toUpperCase();
	}


	public String getsNURCATF() {
		return sNURCATF;
	}


	public void setsNURCATF(String sNURCATF) {
		this.sNURCATF = sNURCATF.trim().toUpperCase();
	}


	public String getsFEPFONFA() {
		return sFEPFONFA;
	}


	public void setsFEPFONFA(String sFEPFONFA) {
		this.sFEPFONFA = sFEPFONFA;
	}


	public String getsEstadoProvisionFA() {
		return sEstadoProvisionFA;
	}


	public void setsEstadoProvisionFA(String sEstadoProvisionFA) {
		this.sEstadoProvisionFA = sEstadoProvisionFA;
	}


	public String getsCOCLDOB() {
		return sCOCLDOB;
	}


	public void setsCOCLDOB(String sCOCLDOB) {
		this.sCOCLDOB = sCOCLDOB;
	}


	public String getsNUDCOMB() {
		return sNUDCOMB;
	}


	public void setsNUDCOMB(String sNUDCOMB) {
		this.sNUDCOMB = sNUDCOMB.trim().toUpperCase();
	}


	public String getsNOMCOCF() {
		return sNOMCOCF;
	}


	public void setsNOMCOCF(String sNOMCOCF) {
		this.sNOMCOCF = sNOMCOCF.trim().toUpperCase();
	}

	public String getsNOMPRCF() {
		return sNOMPRCF;
	}


	public void setsNOMPRCF(String sNOMPRCF) {
		this.sNOMPRCF = sNOMPRCF;
	}


	public String getsNOMADCF() {
		return sNOMADCF;
	}


	public void setsNOMADCF(String sNOMADCF) {
		this.sNOMADCF = sNOMADCF;
	}

	
	public String getsFEPFONFC() {
		return sFEPFONFC;
	}


	public void setsFEPFONFC(String sFEPFONFC) {
		this.sFEPFONFC = sFEPFONFC;
	}


	public String getsEstadoProvisionFC() {
		return sEstadoProvisionFC;
	}


	public void setsEstadoProvisionFC(String sEstadoProvisionFC) {
		this.sEstadoProvisionFC = sEstadoProvisionFC;
	}


	public String getsNUPROFB() {
		return sNUPROFB;
	}


	public void setsNUPROFB(String sNUPROFB) {
		this.sNUPROFB = sNUPROFB.trim();
	}


	public String getsFEPFONF() {
		return sFEPFONF;
	}


	public void setsFEPFONF(String sFEPFONF) {
		this.sFEPFONF = sFEPFONF;
	}


	public String getsEstadoProvisionF() {
		return sEstadoProvisionF;
	}


	public void setsEstadoProvisionF(String sEstadoProvisionF) {
		this.sEstadoProvisionF = sEstadoProvisionF;
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


	public Map<String, String> getTiposcocldoHM() {
		return tiposcocldoHM;
	}


	public void setTiposcocldoHM(Map<String, String> tiposcocldoHM) {
		this.tiposcocldoHM = tiposcocldoHM;
	}


	public Map<String, String> getTiposestadoprovisionHM() {
		return tiposestadoprovisionHM;
	}


	public void setTiposestadoprovisionHM(Map<String, String> tiposestadoprovisionHM) {
		this.tiposestadoprovisionHM = tiposestadoprovisionHM;
	}

}
