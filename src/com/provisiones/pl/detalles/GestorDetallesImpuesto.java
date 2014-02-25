package com.provisiones.pl.detalles;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLImpuestos;
import com.provisiones.ll.CLDescripciones;
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Sesion;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ImpuestoRecurso;



public class GestorDetallesImpuesto implements Serializable 
{

	private static final long serialVersionUID = 5574339668343885712L;

	private static Logger logger = LoggerFactory.getLogger(GestorDetallesImpuesto.class.getName());
	
	private String sCODTRN = ValoresDefecto.DEF_E4_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOENGP = ValoresDefecto.DEF_COENGP;

	private long liCodImpuesto = 0;
	
	private String sNURCAT = "";

	private String sCOSBAC = "";
	private String sFEPRRE = "";
	private String sFERERE = "";
	private String sFEDEIN = "";
	private String sBISODE = "";
	private String sBIRESO = "";
	private String sCOTEXA = ValoresDefecto.DEF_COTEXA;
	private String sOBTEXC = "";
	
	private String sDesCOSBAC = "";
	private String sDesBISODE = "";
	private String sDesBIRESO = "";
	
	private String sNota = "";
	
	public GestorDetallesImpuesto()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorDetallesImpuesto...");
			cargarDetallesImpuesto();
		}
	}
	
	public void volver(ActionEvent actionEvent)
	{
		logger.debug("Volviendo...");
		//return Utils.cargarHistorial(sClase);
		
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
	
	public void cargarDetallesImpuesto()
	{
		logger.debug("Cargando Impuesto...");

		
		//this.sNUPROF = ((GestorListaProvisiones)((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorListaProvisiones")).getsNUPROF();
		
		String sCodImpuesto = Sesion.cargarDetalle();
		
		logger.debug("sCodImpuesto:|"+sCodImpuesto+"|");
		
		FacesMessage msg;
		
		String sMsg = "";
		
		if (!sCodImpuesto.equals(""))
		{
			try
			{
				this.liCodImpuesto = Long.parseLong(sCodImpuesto);

			  	ImpuestoRecurso impuesto = CLImpuestos.buscarImpuestoRecurso(liCodImpuesto);

		    	logger.debug(impuesto.logImpuestoRecurso());
			  	
		    	this.sNURCAT = impuesto.getNURCAT();
		    	this.sCOSBAC = impuesto.getCOSBAC();
		    	this.sDesCOSBAC = CLDescripciones.descripcionTipoImpuesto(impuesto.getCOSBAC());
		    	this.sFEPRRE = Utils.recuperaFecha(impuesto.getFEPRRE());
		    	this.sFERERE = Utils.recuperaFecha(impuesto.getFERERE());
		    	this.sFEDEIN = Utils.recuperaFecha(impuesto.getFEDEIN());
		    	this.sBISODE = impuesto.getBISODE();
		    	this.sDesBISODE = CLDescripciones.descripcionBinaria(impuesto.getBISODE());
		    	this.sBIRESO = impuesto.getBIRESO();
		    	this.sDesBIRESO = CLDescripciones.descripcionResolucion(impuesto.getBIRESO());
		    	this.sOBTEXC = impuesto.getOBTEXC();
		    		
		    	this.sNota = CLImpuestos.buscarNota(liCodImpuesto);

				sMsg = "La Impuesto se cargó correctamente.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
				
			}
			catch(NumberFormatException nfe)
			{
				sMsg = "ERROR: Ocurrió un error al cargar los datos de la Impuesto. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
			}


		}
		else
		{
			sMsg = "ERROR: Ocurrió un error al recuperar la Impuesto. Por favor, revise los datos y avise a soporte.";
			msg = Utils.pfmsgFatal(sMsg);
			logger.error(sMsg);
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}

    public void limpiarNota(ActionEvent actionEvent) 
    {  
    	this.sNota = "";
    }
	
	public void guardaNota (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;

			String sMsg = "";
			
			if (CLImpuestos.guardarNota(liCodImpuesto, sNota))
			{
				sMsg = "Nota guardada correctamente.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}
			else
			{
				sMsg = "ERROR: Ocurrio un error al guardar la nota de la Impuesto. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
			}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		
		}
	}
	
	public void cargarDetallesReferencia (ActionEvent actionEvent) 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
			
			String sCodReferncia = Long.toString(CLReferencias.buscarCodigoReferencia(sNURCAT));
			
	    	logger.debug("sCodReferncia:|"+sCodReferncia+"|");
	    	
	    	Sesion.guardaDetalle(sCodReferncia);
	    	Sesion.guardarHistorial("detallesimpuesto.xhtml","GestorDetallesReferencia");

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

		//return sPagina;
    }

	public String getsCODTRN() {
		return sCODTRN;
	}

	public void setsCODTRN(String sCODTRN) {
		this.sCODTRN = sCODTRN;
	}

	public String getsCOTDOR() {
		return sCOTDOR;
	}

	public void setsCOTDOR(String sCOTDOR) {
		this.sCOTDOR = sCOTDOR;
	}

	public String getsIDPROV() {
		return sIDPROV;
	}

	public void setsIDPROV(String sIDPROV) {
		this.sIDPROV = sIDPROV;
	}

	public String getsCOENGP() {
		return sCOENGP;
	}

	public void setsCOENGP(String sCOENGP) {
		this.sCOENGP = sCOENGP;
	}

	public String getsNURCAT() {
		return sNURCAT;
	}

	public void setsNURCAT(String sNURCAT) {
		this.sNURCAT = sNURCAT;
	}

	public String getsCOSBAC() {
		return sCOSBAC;
	}

	public void setsCOSBAC(String sCOSBAC) {
		this.sCOSBAC = sCOSBAC;
	}

	public String getsFEPRRE() {
		return sFEPRRE;
	}

	public void setsFEPRRE(String sFEPRRE) {
		this.sFEPRRE = sFEPRRE;
	}

	public String getsFERERE() {
		return sFERERE;
	}

	public void setsFERERE(String sFERERE) {
		this.sFERERE = sFERERE;
	}

	public String getsFEDEIN() {
		return sFEDEIN;
	}

	public void setsFEDEIN(String sFEDEIN) {
		this.sFEDEIN = sFEDEIN;
	}

	public String getsBISODE() {
		return sBISODE;
	}

	public void setsBISODE(String sBISODE) {
		this.sBISODE = sBISODE;
	}

	public String getsBIRESO() {
		return sBIRESO;
	}

	public void setsBIRESO(String sBIRESO) {
		this.sBIRESO = sBIRESO;
	}

	public String getsCOTEXA() {
		return sCOTEXA;
	}

	public void setsCOTEXA(String sCOTEXA) {
		this.sCOTEXA = sCOTEXA;
	}

	public String getsOBTEXC() {
		return sOBTEXC;
	}

	public void setsOBTEXC(String sOBTEXC) {
		this.sOBTEXC = sOBTEXC;
	}

	public String getsDesCOSBAC() {
		return sDesCOSBAC;
	}

	public void setsDesCOSBAC(String sDesCOSBAC) {
		this.sDesCOSBAC = sDesCOSBAC;
	}

	public String getsDesBISODE() {
		return sDesBISODE;
	}

	public void setsDesBISODE(String sDesBISODE) {
		this.sDesBISODE = sDesBISODE;
	}

	public String getsDesBIRESO() {
		return sDesBIRESO;
	}

	public void setsDesBIRESO(String sDesBIRESO) {
		this.sDesBIRESO = sDesBIRESO;
	}

	public String getsNota() {
		return sNota;
	}

	public void setsNota(String sNota) {
		this.sNota = sNota;
	}
}
