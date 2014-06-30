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
import com.provisiones.ll.CLProvisiones;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Provision;
import com.provisiones.types.tablas.ProvisionTabla;

public class GestorIngresos implements Serializable 
{
	private static final long serialVersionUID = -5070870594578551674L;

	private static Logger logger = LoggerFactory.getLogger(GestorIngresos.class.getName());

	//Buscar Provision
	private String sNUPROFB = "";
	
	//Filtro Provision
	private String sFEPFONF = "";
	private String sEstadoProvisionF = "";
	
	//Ingreso
	private String sNUPROF = "";
	private String sFEPFON = "";
	private String sFechaIngreso = "";
	private String sValorIngreso = "";
	private String sValorAutorizado = "";
	private String sNumGastosAutorizados = "";	
	
	//Detalles
	private String sDCOSPAT = "";
	private String sDTAS = "";
	private String sDCOGRUG = "";	
	private String sDCOTPGA = "";
	private String sValorTotal = "";
	private String sNumGastos = "";

	private transient ArrayList<ProvisionTabla> tablaprovisiones = null;
	
	private transient ProvisionTabla provisionseleccionada = null;
	
	private Map<String,String> tiposestadoprovisionHM = new LinkedHashMap<String, String>();
	
	public GestorIngresos()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorIngresos...");
			
			tiposestadoprovisionHM.put("AUTORIZADA","T");
			tiposestadoprovisionHM.put("PAGADA",    "G");
		}
	}
	
    public void borrarCamposFiltroProvision()
    {
    	this.sFEPFONF = "";
    	this.sEstadoProvisionF = "";
    }
	
	public void limpiarPlantillaProvision()
	{
		this.sNUPROF = "";
		
		borrarCamposFiltroProvision();
    	
    	this.tablaprovisiones = null;
    	this.provisionseleccionada = null;
	}
	
    public void borrarCamposIngreso()
    {
    	this.sNUPROF = "";
    	this.sNumGastosAutorizados = "";
    	this.sValorAutorizado = "";
    	this.sFechaIngreso = "";
    	this.sValorIngreso = "";
    	this.sFEPFON = "";
    }
	
    public void borrarCamposProvision()
    {
    	this.sDCOSPAT = "";
    	this.sDTAS = "";
    	this.sDCOGRUG = "";
    	this.sDCOTPGA = "";
    	this.sNumGastos = "";
    	this.sValorTotal = "";
    }
    
	public void limpiarPlantilla()
	{
		this.sNUPROFB = "";
		borrarCamposFiltroProvision();
		borrarCamposIngreso();
		borrarCamposProvision();
		
    	this.tablaprovisiones = null;
    	this.provisionseleccionada = null;
	}
    
    
	public void buscarProvisiones(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;

			String sMsg = "";
			
			logger.debug("buscarProvisiones...");
			
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
				String sINGRESADOF = "";
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
						sINGRESADOF,
						sESTADOF);

				this.tablaprovisiones = CLProvisiones.buscarProvisionesIngresablesConFiltro(filtro); 

				if (getTablaprovisiones().size() == 0)
				{
					sMsg = "No se encontraron Provisiones con los criterios solicitados.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);
				}
				else if (getTablaprovisiones().size() == 1)
				{
					sMsg = "Encontrada una Provisión relacionada.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
				}
				else
				{
					sMsg = "Encontradas "+getTablaprovisiones().size()+" Provisiones relacionadas.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
				}
			}

			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}
	}
	


	public void seleccionarProvision(ActionEvent actionEvent) 
    {
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	this.sNUPROFB = provisionseleccionada.getNUPROF();
	    	borrarCamposIngreso();
	    	borrarCamposProvision();
	    	
	    	String sMsg = "Provision '"+ sNUPROFB +"' Seleccionada.";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	logger.info(sMsg);
			
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
			this.tablaprovisiones = null;
			
			if (sNUPROFB.isEmpty())
			{
				sMsg = "ERROR: Debe informar la Provision para realizar una búsqueda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else if (Utils.esAlfanumerico(sNUPROFB))
			{
				sMsg = "ERROR: La Provisión debe ser numérica. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else
			{

				if (!CLProvisiones.BuscarFechaIngresado(sNUPROFB).equals("0"))
				{
					sMsg = "ERROR: La Provisión ya registro un ingreso. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else if (CLProvisiones.existeProvision(sNUPROFB))
				{
					String sEstado = CLProvisiones.estadoProvision(sNUPROFB);
					
					if (sEstado.equals(ValoresDefecto.DEF_PROVISION_AUTORIZADA) 
						|| sEstado.equals(ValoresDefecto.DEF_PROVISION_PAGADA))
					{
						this.sNUPROF = sNUPROFB;

						Provision provision = CLProvisiones.buscarDetallesProvision(sNUPROF);
						
						this.sFEPFON = Utils.recuperaFecha(provision.getsFEPFON());
						this.sValorAutorizado = Utils.recuperaImporte(false,provision.getsValorAutorizado());
						this.sNumGastosAutorizados = provision.getsGastosAutorizados();	
						
						//Detalles
						this.sDCOSPAT = provision.getsCOSPAT();
						this.sDTAS = provision.getsTAS();
						this.sDCOGRUG = provision.getsCOGRUG();	
						this.sDCOTPGA = provision.getsCOTPGA();
						this.sValorTotal = Utils.recuperaImporte(false,provision.getsValorTotal());
						this.sNumGastos = provision.getsNumGastos();
						
						sMsg = "Provisión "+sNUPROF+" cargada correctamente.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
					else
					{
						sMsg = "La Provisión '"+sNUPROFB+"' no se encuentra en un estado que pueda recibir ingresos. Por favor, revise los datos.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);
					}
				}
				else
				{
					sMsg = "La Provisión '"+sNUPROFB+"' no se encuentra regristada en el sistema. Por favor, revise los datos.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);
				}
			}

			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}
	}
	
	public void hoyFechaIngreso (ActionEvent actionEvent)
	{
		this.setsFechaIngreso(Utils.fechaDeHoy(true));
		logger.debug("sFechaIngreso:|{}|",sFechaIngreso);
	}
	
	public void registraIngreso(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";

			if (sNUPROF.isEmpty())
			{
				sMsg = "Debe comprobar la Provisión antes. Por favor, revise los datos.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
			}
			else
			{
				try
				{
					long liValorAutorizado = Long.parseLong(Utils.compruebaImporte(sValorAutorizado));
					
					long liValorIngreso = Long.parseLong(Utils.compruebaImporte(sValorIngreso));
					
					if (liValorAutorizado != liValorIngreso)
					{
						sMsg = "ERROR: El valor a ingresar es distinto del valor autorizado para esta Provisión. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);					
					}
					else
					{

						if (CLProvisiones.registraIngreso(sNUPROF,Utils.compruebaFecha(sFechaIngreso),liValorIngreso))
						{
							sMsg = "Ingreso realizado correctamente.";
							msg = Utils.pfmsgInfo(sMsg);
							logger.info(sMsg);
							borrarCamposIngreso();
							borrarCamposProvision();

						}
						else
						{
							sMsg = "[FATAL] ERROR: ha ocurrido un error al realizar el ingreso. Avise a soporte.";
							msg = Utils.pfmsgFatal(sMsg);
							logger.error(sMsg);
						}
					}
				}
				catch(NumberFormatException nfe)
				{
					sMsg = "ERROR: El valor a ingresar debe ser numérico. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}


			}

			
			


	    	
			FacesContext.getCurrentInstance().addMessage(null, msg);	
		}
	}

	public String getsNUPROF() {
		return sNUPROF;
	}

	public void setsNUPROF(String sNUPROF) {
		this.sNUPROF = sNUPROF;
	}

	public String getsFEPFON() {
		return sFEPFON;
	}

	public void setsFEPFON(String sFEPFON) {
		this.sFEPFON = sFEPFON;
	}

	public String getsFechaIngreso() {
		return sFechaIngreso;
	}

	public void setsFechaIngreso(String sFechaIngreso) {
		this.sFechaIngreso = sFechaIngreso;
	}

	public String getsValorIngreso() {
		return sValorIngreso;
	}

	public void setsValorIngreso(String sValorIngreso) {
		this.sValorIngreso = sValorIngreso;
	}

	public String getsValorTotal() {
		return sValorTotal;
	}

	public void setsValorTotal(String sValorTotal) {
		this.sValorTotal = sValorTotal;
	}

	public String getsNumGastos() {
		return sNumGastos;
	}

	public void setsNumGastos(String sNumGastos) {
		this.sNumGastos = sNumGastos;
	}
	
	public String getsValorAutorizado() {
		return sValorAutorizado;
	}

	public void setsValorAutorizado(String sValorAutorizado) {
		this.sValorAutorizado = sValorAutorizado;
	}

	public ArrayList<ProvisionTabla> getTablaprovisiones() {
		return tablaprovisiones;
	}

	public void setTablaprovisiones(ArrayList<ProvisionTabla> tablaprovisiones) {
		this.tablaprovisiones = tablaprovisiones;
	}

	public ProvisionTabla getProvisionseleccionada() {
		return provisionseleccionada;
	}

	public void setProvisionseleccionada(ProvisionTabla provisionseleccionada) {
		this.provisionseleccionada = provisionseleccionada;
	}

	public String getsDCOSPAT() {
		return sDCOSPAT;
	}

	public void setsDCOSPAT(String sDCOSPAT) {
		this.sDCOSPAT = sDCOSPAT;
	}

	public String getsDTAS() {
		return sDTAS;
	}

	public void setsDTAS(String sDTAS) {
		this.sDTAS = sDTAS;
	}

	public String getsDCOGRUG() {
		return sDCOGRUG;
	}

	public void setsDCOGRUG(String sDCOGRUG) {
		this.sDCOGRUG = sDCOGRUG;
	}

	public String getsDCOTPGA() {
		return sDCOTPGA;
	}

	public void setsDCOTPGA(String sDCOTPGA) {
		this.sDCOTPGA = sDCOTPGA;
	}

	public String getsNUPROFB() {
		return sNUPROFB;
	}

	public void setsNUPROFB(String sNUPROFB) {
		this.sNUPROFB = sNUPROFB;
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

	public String getsNumGastosAutorizados() {
		return sNumGastosAutorizados;
	}

	public void setsNumGastosAutorizados(String sNumGastosAutorizados) {
		this.sNumGastosAutorizados = sNumGastosAutorizados;
	}

	public Map<String, String> getTiposestadoprovisionHM() {
		return tiposestadoprovisionHM;
	}

	public void setTiposestadoprovisionHM(Map<String, String> tiposestadoprovisionHM) {
		this.tiposestadoprovisionHM = tiposestadoprovisionHM;
	}
	
}
