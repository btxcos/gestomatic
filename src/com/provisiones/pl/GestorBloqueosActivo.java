package com.provisiones.pl;

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
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.EstadoActivoTabla;

public class GestorBloqueosActivo implements Serializable 
{

	private static final long serialVersionUID = -7248014413361854070L;

	private static Logger logger = LoggerFactory.getLogger(GestorBloqueosActivo.class.getName());
	
	//Buscar Activo
	private String sCOACESB = "";
	
	//Filtro Activo
	private String sCOPOINB = "";
	private String sNOMUINB = "";
	private String sNOPRACB = "";
	private String sNOVIASB = "";
	private String sNUPIACB = "";
	private String sNUPOACB = "";
	private String sNUPUACB = "";
	
	private String sNURCATB = "";
	
	private String sEstadoB = "";
	
	private boolean bBloqueo = false;
	
	private String sFechaActivacion = "";
	
	private String sEstado = "";

	private Map<String,String> tiposestadosHM = new LinkedHashMap<String, String>();
	
	private transient ActivoTabla activoseleccionado = null;
	
	private transient ArrayList<EstadoActivoTabla> tablaactivos = null;
	
	public GestorBloqueosActivo()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorActivosComunidad...");
			
			tiposestadosHM.put("BLOQUEADO",    "B");
			tiposestadosHM.put("DESBLOQUEADO", "D");

		}
	}
	
	public void borrarCamposBuscar()
	{
		this.sCOACESB = "";
	}
	
	public void borrarCamposBuscarActivo()
	{
		this.sCOPOINB = "";
		this.sNOMUINB = "";
		this.sNOPRACB = "";
		this.sNOVIASB = "";
		this.sNUPIACB = "";
		this.sNUPOACB = "";
		this.sNUPUACB = "";
		
		this.sNURCATB = "";
	}
	
	public void borrarResultadosActivo()
	{
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {    	
    	borrarCamposBuscarActivo();
    	borrarResultadosActivo();
    }
    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {
    	this.sEstado = "";
    	this.sFechaActivacion = "";
    	
    	borrarCamposBuscar();
    	borrarCamposBuscarActivo();

    }
    
	public void hoyFechaActivacion (ActionEvent actionEvent)
	{
		this.setsFechaActivacion(Utils.fechaDeHoy(true));
		logger.debug("sFechaActivacion:|"+sFechaActivacion+"|");
	}
    
	public void buscarActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.activoseleccionado = null;
			
			if (sNURCATB.isEmpty())
			{
				ActivoTabla filtro = new ActivoTabla(
						"", sCOPOINB.toUpperCase(), sNOMUINB.toUpperCase(),
						sNOPRACB.toUpperCase(), sNOVIASB.toUpperCase(), sNUPIACB.toUpperCase(), 
						sNUPOACB.toUpperCase(), sNUPUACB.toUpperCase(), "");
				
				//TODO AC
				//this.setTablaactivos(CLGastos.buscarActivosConAbonosEjecutables(filtro));
				
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
			else if (CLReferencias.existeReferenciaCatastral(sNURCATB))
			{
				//TODO RC
				//this.setTablaactivos(CLReferencias.buscarActivoAsociadoConGastosAutorizados(sNURCATB));
				
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
		    	this.tablaactivos = null;
				
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
	
	public void cargarEstado(ActionEvent actionEvent) 
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

	public String getsCOACESB() {
		return sCOACESB;
	}

	public void setsCOACESB(String sCOACESB) {
		this.sCOACESB = sCOACESB;
	}

	public String getsCOPOINB() {
		return sCOPOINB;
	}

	public void setsCOPOINB(String sCOPOINB) {
		this.sCOPOINB = sCOPOINB;
	}

	public String getsNOMUINB() {
		return sNOMUINB;
	}

	public void setsNOMUINB(String sNOMUINB) {
		this.sNOMUINB = sNOMUINB;
	}

	public String getsNOPRACB() {
		return sNOPRACB;
	}

	public void setsNOPRACB(String sNOPRACB) {
		this.sNOPRACB = sNOPRACB;
	}

	public String getsNOVIASB() {
		return sNOVIASB;
	}

	public void setsNOVIASB(String sNOVIASB) {
		this.sNOVIASB = sNOVIASB;
	}

	public String getsNUPIACB() {
		return sNUPIACB;
	}

	public void setsNUPIACB(String sNUPIACB) {
		this.sNUPIACB = sNUPIACB;
	}

	public String getsNUPOACB() {
		return sNUPOACB;
	}

	public void setsNUPOACB(String sNUPOACB) {
		this.sNUPOACB = sNUPOACB;
	}

	public String getsNUPUACB() {
		return sNUPUACB;
	}

	public void setsNUPUACB(String sNUPUACB) {
		this.sNUPUACB = sNUPUACB;
	}

	public String getsNURCATB() {
		return sNURCATB;
	}

	public void setsNURCATB(String sNURCATB) {
		this.sNURCATB = sNURCATB;
	}

	public String getsEstadoB() {
		return sEstadoB;
	}

	public void setsEstadoB(String sEstadoB) {
		this.sEstadoB = sEstadoB;
	}

	public boolean isbBloqueo() {
		return bBloqueo;
	}

	public void setbBloqueo(boolean bBloqueo) {
		this.bBloqueo = bBloqueo;
	}

	public String getsFechaActivacion() {
		return sFechaActivacion;
	}

	public void setsFechaActivacion(String sFechaActivacion) {
		this.sFechaActivacion = sFechaActivacion;
	}

	public String getsEstado() {
		return sEstado;
	}

	public void setsEstado(String sEstado) {
		this.sEstado = sEstado;
	}

	public Map<String, String> getTiposestadosHM() {
		return tiposestadosHM;
	}

	public void setTiposestadosHM(Map<String, String> tiposestadosHM) {
		this.tiposestadosHM = tiposestadosHM;
	}

	public ActivoTabla getActivoseleccionado() {
		return activoseleccionado;
	}

	public void setActivoseleccionado(ActivoTabla activoseleccionado) {
		this.activoseleccionado = activoseleccionado;
	}

	public ArrayList<EstadoActivoTabla> getTablaactivos() {
		return tablaactivos;
	}

	public void setTablaactivos(ArrayList<EstadoActivoTabla> tablaactivos) {
		this.tablaactivos = tablaactivos;
	}
    
}
