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
import com.provisiones.ll.CLActivos;
import com.provisiones.ll.CLDescripciones;
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.EstadoActivoTabla;

public class GestorBloqueosActivo implements Serializable 
{

	private static final long serialVersionUID = -7248014413361854070L;

	private static Logger logger = LoggerFactory.getLogger(GestorBloqueosActivo.class.getName());
	
	//Accion
	private String sAccion = "";
	
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
	private String sNUFIREB = "";
	
	private String sNURCATB = "";
	
	private String sEstadoB = "";
	
	private boolean bBloqueo = false;
	private String sCOACES = "";
	private String sEstadoD = "";
	private String sFechaOriginal = "";
	private String sFechaActivacion = "";

	private Map<String,String> tiposestadosHM = new LinkedHashMap<String, String>();
	
	private transient EstadoActivoTabla activoseleccionado = null;
	
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
		this.sNUFIREB = "";
		
		this.sNURCATB = "";
		
		this.sEstadoB = "";
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
    
	public void borrarCamposBloqueo()
	{
		this.bBloqueo = false;
		this.sCOACES = "";
    	this.sEstadoD = "";
    	this.sFechaActivacion = "";
    	this.sFechaOriginal = "";
	}
    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {
    	this.sAccion = "";
    	
    	borrarCamposBuscar();
    	borrarCamposBuscarActivo();
    	borrarCamposBloqueo();

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
			
			if (!sCOPOINB.isEmpty() && Utils.esAlfanumerico(sCOPOINB))
			{
				sMsg = "ERROR: El Código Postal debe ser numérico. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else if (sNURCATB.isEmpty())
			{
				ActivoTabla filtro = new ActivoTabla(
						"", 
						sCOPOINB, 
						sNOMUINB.toUpperCase(),
						sNOPRACB.toUpperCase(), 
						sNOVIASB.toUpperCase(), 
						sNUPIACB.toUpperCase(), 
						sNUPOACB.toUpperCase(), 
						sNUPUACB.toUpperCase(),
						sNUFIREB.toUpperCase(),
						"");
				
				//TODO AC
				
				this.setTablaactivos(CLActivos.buscaActivosRegistrados(filtro, sEstadoB));
				
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
				this.setTablaactivos(CLReferencias.buscarActivoAsociadoRegistrado(sNURCATB));
				
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
	    	
	    	String sMsg = "";
	    	
	    	try
	    	{
	    		this.sCOACES =  sCOACESB;
		    	EstadoActivoTabla estado = CLActivos.buscarEstadoActivo(Integer.parseInt(sCOACES));

		    	this.sEstadoD = CLDescripciones.descripcionEstadoActivo(estado.getsEstado());
		    	
		    	this.sFechaOriginal = Utils.recuperaFecha(estado.getsFechaActivacion());
		    	
		    	this.sFechaActivacion = sFechaOriginal;
		    	
		    	this.bBloqueo = estado.getsEstado().equals(ValoresDefecto.DEF_ACTIVO_BLOQUEADO);
		    	
		    	sMsg = "Activo '"+sCOACESB+"' cargado.";
		    	msg = Utils.pfmsgInfo(sMsg);
		    	logger.info(sMsg);
		    	
				FacesContext.getCurrentInstance().addMessage(null, msg);
	    	}
			catch(NumberFormatException nfe)
			{
				sMsg = "ERROR: El Activo debe ser numérico. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}	
	    	


		}
    }
	
	public void modificarBloqueo()
	{
		if (ConnectionManager.comprobarConexion())
		{
			

			FacesMessage msg;
			
			String sMsg = "";
			
			if (sAccion.isEmpty())
			{
				sMsg = "ERROR: No se ha seleccionado una Acción. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else if (sCOACES.isEmpty())
			{
				sMsg = "ERROR: No se ha cargado un Activo. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else if (sAccion.equals(ValoresDefecto.DEF_ACTIVO_BLOQUEADO) && bBloqueo) 
			{
				sMsg = "ERROR: No se puede bloquear un Activo bloqueado. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else if (sAccion.equals(ValoresDefecto.DEF_ACTIVO_DESBLOQUEADO) && !bBloqueo) 
			{
				sMsg = "ERROR: No se puede desbloquear un Activo desbloqueado. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else if (sAccion.equals(ValoresDefecto.DEF_MODIFICACION) && !bBloqueo) 
			{
				sMsg = "ERROR: No se puede modificar la fecha de bloqueo de un Activo desbloqueado. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else if (sAccion.equals(ValoresDefecto.DEF_ACTIVO_BLOQUEADO) && sFechaActivacion.isEmpty()) 
			{
				sMsg = "ERROR: No se puede bloquear sin fecha de inicio de bloqueo. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else if (sAccion.equals(ValoresDefecto.DEF_MODIFICACION) && sFechaActivacion.isEmpty()) 
			{
				sMsg = "ERROR: No se puede modificar sin fecha de inicio de bloqueo. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else if (sAccion.equals(ValoresDefecto.DEF_MODIFICACION) && sFechaActivacion.equals(sFechaOriginal)) 
			{
				sMsg = "No se ha modificado, la fecha de inicio de bloqueo no ha cambiado. Por favor, revise los datos.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
			}
			else
			{
				logger.debug("sCOACES:|"+sCOACES+"|");
				logger.debug("sAccion:|"+sAccion+"|");
				logger.debug("sFechaActivacion:|"+sFechaActivacion+"|");
				
				EstadoActivoTabla estado = new EstadoActivoTabla(sCOACES, sAccion, Utils.compruebaFecha(sFechaActivacion));
				
				int iSalida = CLActivos.modificaBloqueoActivo(estado);
				
				logger.debug("iSalida:|"+iSalida+"|");
				
				switch (iSalida) 
				{
				case 0: //Sin errores
					borrarCamposBloqueo();

					sMsg = "El cambio en el bloqueo se ha registrado correctamente.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
					break;

				case -901: //Error 901 - al bloquear el activo
					sMsg = "[FATAL] ERROR:901 - Se ha producido un error al bloquear el Activo. Por favor, revise los datos y avise a soporte.";
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					break;
					
				case -902: //Error 902 - al desbloquear el activo
					sMsg = "[FATAL] ERROR:902 - Se ha producido un error al desbloquear el Activo. Por favor, revise los datos y avise a soporte.";
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					break;

				case -903: //Error 903 - - al modificar la fecha de bloqueo
					sMsg = "[FATAL] ERROR:903 - Se ha producido un error al modificar la fecha de bloqueo. Por favor, revise los datos y avise a soporte.";
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					break;

				case -904: //Error 904 - operación desconocida
					sMsg = "[FATAL] ERROR:904 - Se ha producido una operación desconocida. Por favor, revise los datos y avise a soporte.";
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					break;

				case -905: //Error 905 - al conectar con la base de datos
					sMsg = "[FATAL] ERROR:905 - Se ha producido un error al conectar con la base de datos. Por favor, revise los datos y avise a soporte.";
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					break;
					
				default: //error generico
					sMsg = "[FATAL] ERROR:"+iSalida+" - La operacion solicitada ha producido un error desconocido. Por favor, revise los datos y avise a soporte.";
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					break;
				}

				logger.debug("Finalizadas las comprobaciones.");
			}
			

			FacesContext.getCurrentInstance().addMessage(null, msg);

		}	
		
	}

	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
	}

	public String getsAccion() {
		return sAccion;
	}

	public void setsAccion(String sAccion) {
		this.sAccion = sAccion;
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

	public String getsNUFIREB() {
		return sNUFIREB;
	}

	public void setsNUFIREB(String sNUFIREB) {
		this.sNUFIREB = sNUFIREB;
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

	public String getsEstadoD() {
		return sEstadoD;
	}

	public void setsEstadoD(String sEstadoD) {
		this.sEstadoD = sEstadoD;
	}

	public Map<String, String> getTiposestadosHM() {
		return tiposestadosHM;
	}

	public void setTiposestadosHM(Map<String, String> tiposestadosHM) {
		this.tiposestadosHM = tiposestadosHM;
	}

	public EstadoActivoTabla getActivoseleccionado() {
		return activoseleccionado;
	}

	public void setActivoseleccionado(EstadoActivoTabla activoseleccionado) {
		this.activoseleccionado = activoseleccionado;
	}

	public ArrayList<EstadoActivoTabla> getTablaactivos() {
		return tablaactivos;
	}

	public void setTablaactivos(ArrayList<EstadoActivoTabla> tablaactivos) {
		this.tablaactivos = tablaactivos;
	}
    
}
