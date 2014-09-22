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
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Sesion;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Transicion;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.ComunidadTabla;

public class GestorListaActivos implements Serializable {

	private static final long serialVersionUID = -8623132319361614898L;
	
	private static Logger logger = LoggerFactory.getLogger(GestorListaActivos.class.getName());
	
	//Buscar activos
	private String sCOACES = "";

	//Busqueda Activos
	private String sCOPOIN = "";
	private String sNOMUIN = "";
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	private String sNUFIRE = "";
	
	private String sNURCAT = "";
	
	private String sEstadoActivo = "";
	
	//Comunidad
	private String sCOCLDO = "";
	private String sNUDCOM = "";
	private String sNOMCOC = "";

	private transient ActivoTabla activoseleccionado = null;
	private transient ArrayList<ActivoTabla> tablaactivos = null;
	
	private transient ComunidadTabla comunidadseleccionada = null;
	private transient ArrayList<ComunidadTabla> tablacomunidades = null;
	
	private Map<String,String> tiposestadoactivoHM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposcocldoHM = new LinkedHashMap<String, String>();
	
	public GestorListaActivos()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorListaActivos...");
		
			tiposestadoactivoHM.put("SIN VENDER", 	ValoresDefecto.DEF_ALTA);
			tiposestadoactivoHM.put("VENDIDO",		ValoresDefecto.DEF_BAJA);
			
			tiposcocldoHM.put("C.I.F.",                     "2");
			tiposcocldoHM.put("C.I.F país extranjero.",     "5");
			tiposcocldoHM.put("Otros persona jurídica.",    "J");
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
    	
    	this.sEstadoActivo= ""; 
    	
 	}
	
	public void borrarCamposResultadosActivo()
	{
    	this.setActivoseleccionado(null);
    	this.setTablaactivos(null);
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
	
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	borrarCamposFiltroActivo();
    	borrarCamposResultadosActivo();
    	this.sCOACES = "";
    	
    	this.sNURCAT = "";
    	
    	borrarCamposFiltroComunidad();
    	borrarResultadosComunidad();
    }
	
	public void buscaActivos (ActionEvent actionEvent)
	{

		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			try
			{
				
				if (sCOACES.isEmpty())
				{
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
						
						this.setTablaactivos(CLActivos.buscarActivosPorEstadoVenta(filtro,sEstadoActivo));

					}
					else if (CLReferencias.existeReferenciaCatastral(sNURCAT))
					{
						this.setTablaactivos(CLReferencias.buscarActivoAsociado(sNURCAT));
						
					}
					else
					{
				    	this.setTablaactivos(null);
						
						sMsg = "La Referencia Catastral informada no se encuentrar registrada en el sistema. Por favor, revise los datos.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);
					}
				}
				else
				{
					int iCOACES = Integer.parseInt(sCOACES);
					
					this.setTablaactivos(CLActivos.buscarActivoUnico(iCOACES));
				}

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
			catch(NumberFormatException nfe)
			{
				sMsg = "ERROR: El activo debe ser numérico. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
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
			
			this.comunidadseleccionada = null;
			
			this.setTablacomunidades(CLComunidades.buscarComunidadConNombre(sNOMCOC));
			
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
	
	public void BuscarActivosComunidad(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			if (sCOCLDO.isEmpty() || sNUDCOM.isEmpty())
			{
				sMsg = "ERROR: Los campos 'Documento' y 'Número' deben de ser informados para realizar la búsqueda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else if (!CLComunidades.existeComunidad(sCOCLDO, sNUDCOM.toUpperCase()))
			{
				sMsg = "La Comunidad informada no está dada de alta. Por favor, revise los datos.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
			}
			else
			{
				
				this.setTablaactivos(CLComunidades.buscarActivosComunidad(sCOCLDO, sNUDCOM.toUpperCase()));
				
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
	    	
	    	
	    	sMsg = "Activo "+ sCOACES +" Seleccionado.";
	    	msg = new FacesMessage(sMsg);
	    	logger.debug(sMsg);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		
    }
	
	public void cargarComunidad(ActionEvent actionEvent) 
    {  
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
			if (activoseleccionado != null)
			{
		    	this.sCOACES = activoseleccionado.getCOACES();

		    	logger.debug("sCOACES:|"+sCOACES+"|");
		    	
		    	Transicion transicion = new Transicion (
		    			sCOACES,
		    			ValoresDefecto.ID_ACTIVO,
		    			"listaactivos.xhtml",
		    			"GestorDetallesComunidad");
		    	
		    	Sesion.guardarTransicion(transicion, true);
		    	
		    	//Sesion.guardaDetalle(sCOACES);
		    	//Sesion.guardaTipoDetalle(ValoresDefecto.ID_ACTIVO);
		    	//Sesion.limpiarHistorial();
		    	//Sesion.guardarHistorial("listaactivos.xhtml","GestorDetallesComunidad");

		    	sPagina = "detallescomunidad.xhtml";
		    	
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

				msg = Utils.pfmsgWarning("No se ha seleccionado ningún activo.");
				
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}

		}
    }

	public String cargarCuota()
    {  
		String sPagina = "login.xhtml";
		
		if (ConnectionManager.comprobarConexion())
		{
	    	this.sCOACES = activoseleccionado.getCOACES();
	    	
	    	logger.debug("Redirigiendo...");
	    	
	    	sPagina =  "movimientoscuotas.xhtml";
		}

		return sPagina;
    }
	
	public String cargarReferencia() 
    {  
 		String sPagina = "login.xhtml";
		
		if (ConnectionManager.comprobarConexion())
		{
	    	this.sCOACES = activoseleccionado.getCOACES();
	    	
	    	logger.debug("Redirigiendo...");
	    	
	    	sPagina =  "movimientosreferencias.xhtml";
		}

		return sPagina;
    }
	
	public String cargarImpuestos() 
    {  
		String sPagina = "login.xhtml";
		
		if (ConnectionManager.comprobarConexion())
		{
	    	this.sCOACES = activoseleccionado.getCOACES();
	    	
	    	logger.debug("Redirigiendo...");
	    	
	    	sPagina =  "movimientosimpuestos.xhtml";
		}
		
		return sPagina;

    }
	public String cargarGastos() 
    {  
		String sPagina = "login.xhtml";
		
		if (ConnectionManager.comprobarConexion())
		{
	    	this.sCOACES = activoseleccionado.getCOACES();
	    	
	    	logger.debug("Redirigiendo...");
	    	
	    	sPagina =  "gastos.xhtml";
		}

		return sPagina;
    }

	public void cargarDetalles(ActionEvent actionEvent) 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
			if (activoseleccionado != null)
			{
		    	this.sCOACES = activoseleccionado.getCOACES();

		    	logger.debug("sCOACES:|"+sCOACES+"|");
		    	
		    	Transicion transicion = new Transicion (
		    			sCOACES,
		    			ValoresDefecto.ID_ACTIVO,
		    			"listaactivos.xhtml",
		    			"GestorDetallesActivo");
		    	
		    	Sesion.guardarTransicion(transicion, true);
		    			    	
		    	//Sesion.guardaDetalle(sCOACES);
		    	//Sesion.guardaTipoDetalle(ValoresDefecto.ID_ACTIVO);
		    	//Sesion.limpiarHistorial();
		    	//Sesion.guardarHistorial("listaactivos.xhtml","GestorDetallesActivo");

		    	sPagina = "detallesactivo.xhtml";
		    	
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

				msg = Utils.pfmsgWarning("No se ha seleccionado ningún activo.");
				
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


	public String getsNUFIRE() {
		return sNUFIRE;
	}


	public void setsNUFIRE(String sNUFIRE) {
		this.sNUFIRE = sNUFIRE;
	}


	public String getsNURCAT() {
		return sNURCAT;
	}


	public void setsNURCAT(String sNURCAT) {
		this.sNURCAT = sNURCAT;
	}


	public String getsEstadoActivo() {
		return sEstadoActivo;
	}


	public void setsEstadoActivo(String sEstadoActivo) {
		this.sEstadoActivo = sEstadoActivo;
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


	public Map<String, String> getTiposestadoactivoHM() {
		return tiposestadoactivoHM;
	}


	public void setTiposestadoactivoHM(Map<String, String> tiposestadoactivoHM) {
		this.tiposestadoactivoHM = tiposestadoactivoHM;
	}


	public Map<String, String> getTiposcocldoHM() {
		return tiposcocldoHM;
	}


	public void setTiposcocldoHM(Map<String, String> tiposcocldoHM) {
		this.tiposcocldoHM = tiposcocldoHM;
	}

}
