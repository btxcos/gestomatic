package com.provisiones.pl.listas.activo;

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
import com.provisiones.ll.CLCuotas;
import com.provisiones.misc.Sesion;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Transicion;
import com.provisiones.types.tablas.CuotaTabla;

public class GestorListaCuotasActivo implements Serializable 
{

	private static final long serialVersionUID = 6737029348493340280L;

	private static Logger logger = LoggerFactory.getLogger(GestorListaCuotasActivo.class.getName());
	
	//Buscar activos
	private String sCOACES = "";

	//Filtro cuotas activo
	private String sCOSBACFA = "";
	private String sFIPAGOFA = "";
	private String sFFPAGOFA = "";
	private String sIMCUCOFA = "";
	private String sFAACTAFA = "";
	private String sPTPAGOFA = "";
	private String sComparadorFA = "";
	private boolean bSeleccionadoFA = true; 

	private transient CuotaTabla cuotaseleccionada = null;
	private transient ArrayList<CuotaTabla> tablacuotas = null;
	
	private Map<String,String> tiposcosbacHM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposptpagoHM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposcomparaimporteHM = new LinkedHashMap<String, String>();
	
	public GestorListaCuotasActivo()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorListaCuotas...");
			
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
			
			cargarDetallesActivo();
		}
	}
	
	public void volver(ActionEvent actionEvent)
	{
		
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
    

    
    
	public void borrarCamposCuota()
	{
		this.sCOACES = "";
    	
    	this.setCuotaseleccionada(null);
    	this.setTablacuotas(null);
	}
    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {
    	borrarCamposCuota();
    	borrarCamposFiltroCuotasActivo();
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
		    		int iCOACES = Integer.parseInt(sCOACES);
		    		
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
	
	public void cambiaComparadorFA()
	{
		this.bSeleccionadoFA = this.sComparadorFA.isEmpty();
		logger.debug("sComparadorFA:|"+sComparadorFA+"|");
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
	
	public void cargarDetallesActivo()
	{

		//this.sCOACES  = Sesion.cargarDetalle();
		this.sCOACES  = CLActivos.recuperaID();
		
		logger.debug("sCOACES:|"+sCOACES+"|");
		
	}
	

	
	public void cargarDetallesCuota(ActionEvent actionEvent) 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
			String sMsg = "";
			
			if (cuotaseleccionada != null)
			{
	
		    	String sCodCuota = cuotaseleccionada.getsCuotaID();
		    	
		    	logger.debug("sCodCuota:|"+sCodCuota+"|");

		    	Transicion transicion = new Transicion (
		    			sCodCuota,
		    			ValoresDefecto.ID_CUOTA,
		    			"listacuotasactivo.xhtml",
		    			"GestorDetallesCuota");
		    	
		    	Sesion.guardarTransicion(transicion, false);
		    	
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

