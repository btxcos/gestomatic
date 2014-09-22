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
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Sesion;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Transicion;
import com.provisiones.types.tablas.ReferenciaTabla;

public class GestorListaReferenciasActivo implements Serializable
{

	private static final long serialVersionUID = -5674695405991567004L;

	private static Logger logger = LoggerFactory.getLogger(GestorListaReferenciasActivo.class.getName());

	//Buscar activos
	private String sCOACES = "";

	//Filtro referencia activo
	private String sNURCATFA = "";
	private String sTIRCATFA = "";
	private String sIMVSUEFA = "";
	private String sComparadorSueloFA = "";
	private boolean bSeleccionadoSueloFA = true; 
	private String sIMCATAFA = "";
	private String sComparadorCatastralFA = "";
	private boolean bSeleccionadoCatastralFA = true; 
	private String sFERECAFA = "";
	private String sENEMISFA = "";

	
	private transient ReferenciaTabla referenciaseleccionada = null;
	private transient ArrayList<ReferenciaTabla> tablareferencias = null;
	
	private Map<String,String> tiposcomparaimporteHM = new LinkedHashMap<String, String>();

	public GestorListaReferenciasActivo()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorListaReferenciasActivo...");
			
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
	
	public void borrarCamposFiltroRefereciasActivo()
	{
		this.sNURCATFA = "";
		this.sTIRCATFA = "";
		this.sIMVSUEFA = "";
		this.sComparadorSueloFA = "";
		this.bSeleccionadoSueloFA = true; 
		this.sIMCATAFA = "";
		this.sComparadorCatastralFA = "";
		this.bSeleccionadoCatastralFA = true; 
		this.sFERECAFA = "";
		this.sENEMISFA = "";

	}
	
    public void limpiarPlantillaFiltroRefereciasActivo(ActionEvent actionEvent) 
    {  
    	borrarCamposFiltroRefereciasActivo();
    }
    
    
	public void borrarCamposReferencia()
	{
    	
    	this.setReferenciaseleccionada(null);
    	this.setTablareferencias(null);
	}
	

    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {
    	this.sCOACES = "";	

    	borrarCamposReferencia();
    	borrarCamposFiltroRefereciasActivo();
    }
    


	
	public void buscarReferenciasActivo (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			if (sCOACES.isEmpty())
			{
				sMsg = "ERROR: Debe informar el campo 'Activo' para realizar una búsqueda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
				
				this.setTablareferencias(null);
			}
			else
			{
				try
				{
					int iCOACES = Integer.parseInt(sCOACES);
					
					String sImporteSuelo = "";
					
					if (!sComparadorSueloFA.isEmpty())
					{
						sImporteSuelo = Utils.compruebaImporte(sIMVSUEFA);
					}
					
					String sImporteCatastral = "";

					if (!sComparadorCatastralFA.isEmpty())
					{
						sImporteCatastral = Utils.compruebaImporte(sIMCATAFA);
					}
					
					ReferenciaTabla filtro = new ReferenciaTabla(
							"",
							sNURCATFA,   
							sTIRCATFA,
							sENEMISFA,
							"",   
							sImporteSuelo,
							sImporteCatastral,
							Utils.compruebaFecha(sFERECAFA));
					
					//this.tablareferencias = CLReferencias.buscarReferenciasActivo(Integer.parseInt(sCOACES));
					this.tablareferencias = CLReferencias.buscarReferenciasActivo(filtro,sComparadorSueloFA,sComparadorCatastralFA, iCOACES);
					
					if (getTablareferencias().size() == 0)
					{
						sMsg = "No se encontraron Referencias con los criterios solicitados.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);
					}
					else if (getTablareferencias().size() == 1)
					{
						sMsg = "Encontrada una Referencia relacionada.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
					else
					{
						sMsg = "Encontradas "+getTablareferencias().size()+" Referencias relacionadas.";
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
			}

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	

	
	public void cambiaComparadorSueloFA()
	{
		this.bSeleccionadoSueloFA = this.sComparadorSueloFA.isEmpty();
		logger.debug("sComparadorSueloFA:|"+sComparadorSueloFA+"|");
	}
	
	public void cambiaComparadorCatastralFA()
	{
		this.bSeleccionadoCatastralFA = this.sComparadorCatastralFA.isEmpty();
		logger.debug("sComparadorCatastralFA:|"+sComparadorCatastralFA+"|");
	}
	
	public void hoyFERECAFA (ActionEvent actionEvent)
	{
		this.setsFERECAFA(Utils.fechaDeHoy(true));
		logger.debug("sFERECAFA:|"+sFERECAFA+"|");
	}
	
	public void cargarDetallesActivo()
	{

		//this.sCOACES  = Sesion.cargarDetalle();
		this.sCOACES  = CLActivos.recuperaID();
		
		logger.debug("sCOACES:|"+sCOACES+"|");

	}
	
	public void cargarDetallesReferencia (ActionEvent actionEvent) 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
			
			if (referenciaseleccionada != null)
			{

		    	String sNURCAT  = referenciaseleccionada.getNURCAT();
		    	
		    	String sCodReferencia = Long.toString(CLReferencias.buscarCodigoReferencia(sNURCAT));
		
		    	logger.debug("sCodReferencia:|"+sCodReferencia+"|");

		    	Transicion transicion = new Transicion (
		    			sCodReferencia,
		    			ValoresDefecto.ID_REFERENCIA,
		    			"listareferenciasactivo.xhtml",
		    			"GestorDetallesReferencia");
		    	
		    	Sesion.guardarTransicion(transicion, false);
		    	
		    	//Sesion.guardaDetalle(sCodReferencia);
		    	//Sesion.limpiarHistorial();
		    	//Sesion.guardarHistorial("listareferencias.xhtml","GestorDetallesReferencia");

		    	sPagina = "detallesreferencia.xhtml";
		    	
		    	
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

				msg = Utils.pfmsgWarning("No se ha seleccionado ningún gasto.");
				
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

	public ReferenciaTabla getReferenciaseleccionada() {
		return referenciaseleccionada;
	}

	public void setReferenciaseleccionada(ReferenciaTabla referenciaseleccionada) {
		this.referenciaseleccionada = referenciaseleccionada;
	}

	public ArrayList<ReferenciaTabla> getTablareferencias() {
		return tablareferencias;
	}

	public void setTablareferencias(ArrayList<ReferenciaTabla> tablareferencias) {
		this.tablareferencias = tablareferencias;
	}

	public Map<String,String> getTiposcomparaimporteHM() {
		return tiposcomparaimporteHM;
	}

	public void setTiposcomparaimporteHM(Map<String,String> tiposcomparaimporteHM) {
		this.tiposcomparaimporteHM = tiposcomparaimporteHM;
	}

	public String getsNURCATFA() {
		return sNURCATFA;
	}

	public void setsNURCATFA(String sNURCATFA) {
		this.sNURCATFA = sNURCATFA;
	}

	public String getsTIRCATFA() {
		return sTIRCATFA;
	}

	public void setsTIRCATFA(String sTIRCATFA) {
		this.sTIRCATFA = sTIRCATFA;
	}

	public String getsIMVSUEFA() {
		return sIMVSUEFA;
	}

	public void setsIMVSUEFA(String sIMVSUEFA) {
		this.sIMVSUEFA = sIMVSUEFA;
	}

	public String getsComparadorSueloFA() {
		return sComparadorSueloFA;
	}

	public void setsComparadorSueloFA(String sComparadorSueloFA) {
		this.sComparadorSueloFA = sComparadorSueloFA;
	}

	public boolean isbSeleccionadoSueloFA() {
		return bSeleccionadoSueloFA;
	}

	public void setbSeleccionadoSueloFA(boolean bSeleccionadoSueloFA) {
		this.bSeleccionadoSueloFA = bSeleccionadoSueloFA;
	}

	public String getsIMCATAFA() {
		return sIMCATAFA;
	}

	public void setsIMCATAFA(String sIMCATAFA) {
		this.sIMCATAFA = sIMCATAFA;
	}

	public String getsComparadorCatastralFA() {
		return sComparadorCatastralFA;
	}

	public void setsComparadorCatastralFA(String sComparadorCatastralFA) {
		this.sComparadorCatastralFA = sComparadorCatastralFA;
	}

	public boolean isbSeleccionadoCatastralFA() {
		return bSeleccionadoCatastralFA;
	}

	public void setbSeleccionadoCatastralFA(boolean bSeleccionadoCatastralFA) {
		this.bSeleccionadoCatastralFA = bSeleccionadoCatastralFA;
	}

	public String getsFERECAFA() {
		return sFERECAFA;
	}

	public void setsFERECAFA(String sFERECAFA) {
		this.sFERECAFA = sFERECAFA;
	}

	public String getsENEMISFA() {
		return sENEMISFA;
	}

	public void setsENEMISFA(String sENEMISFA) {
		this.sENEMISFA = sENEMISFA;
	}

}
