package com.provisiones.pl.detalles;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;

import com.provisiones.ll.CLComunidades;
import com.provisiones.ll.CLCuotas;
import com.provisiones.ll.CLDescripciones;
import com.provisiones.misc.Sesion;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Cuota;

public class GestorDetallesCuota implements Serializable 
{

	private static final long serialVersionUID = 6743390702228585107L;

	private static Logger logger = LoggerFactory.getLogger(GestorDetallesCuota.class.getName());
	
	private String sCODTRN = ValoresDefecto.DEF_E2_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOENGP = ValoresDefecto.DEF_COENGP;

	private long liCodCuota = 0;
	
	private String sCOACES = "";
	
	private String sCOCLDO = "";
	private String sDesCOCLDO = "";
	
	private String sNUDCOM = "";
	private String sNOMCOC = "";
	private String sNODCCO = "";

	private String sCOSBAC = "";
	private String sDesCOSBAC = "";
	private String sFIPAGO = "";
	private String sFFPAGO = "";
	private String sIMCUCO = "";
	private String sFAACTA = "";
	private String sPTPAGO = "";
	private String sOBTEXC = "";
	
	private String sOBDEER = "";
	
	private String sNota = "";
	
	public GestorDetallesCuota()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorDetallesCuota...");
			cargarDetallesCuota();
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
	
	public void cargarDetallesCuota()
	{
		logger.debug("Cargando Cuota...");

		
		//this.sNUPROF = ((GestorListaProvisiones)((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorListaProvisiones")).getsNUPROF();
		
		String sCodCuota = Sesion.cargarDetalle();
		
		logger.debug("sCodCuota:|"+sCodCuota+"|");
		
		FacesMessage msg;
		
		String sMsg = "";
		
		if (!sCodCuota.equals(""))
		{
			try
			{
				this.liCodCuota = Long.parseLong(sCodCuota);

			  	Cuota cuota = CLCuotas.buscarCuota(liCodCuota);

		    	logger.debug(cuota.logCuota());
			  	
		    	this.sCOACES = cuota.getCOACES();
		    	this.sCOCLDO = cuota.getCOCLDO(); 
		    	this.sDesCOCLDO = CLDescripciones.descripcionTipoDocumento(sCOCLDO);
		    	this.sNUDCOM = cuota.getNUDCOM();
		    	this.sCOSBAC = cuota.getCOSBAC();
		    	this.sDesCOSBAC = CLDescripciones.descripcionTipoCuota(sCOSBAC);
		    	this.sFIPAGO = Utils.recuperaFecha(cuota.getFIPAGO());
		    	this.sFFPAGO = Utils.recuperaFecha(cuota.getFFPAGO());
		    	this.sIMCUCO = Utils.recuperaImporte(false,cuota.getIMCUCO());
		    	this.sFAACTA = Utils.recuperaFecha(cuota.getFAACTA());
		    	this.sPTPAGO = CLDescripciones.descripcionPeriodicidad(cuota.getPTPAGO());
		    	this.sOBTEXC = cuota.getOBTEXC();
		    	
		    		
		    	this.sOBDEER = "";
		    		
		    	this.sNota = CLCuotas.buscarNota(liCodCuota);

				sMsg = "La Cuota se cargó correctamente.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
				
			}
			catch(NumberFormatException nfe)
			{
				sMsg = "ERROR: Ocurrió un error al cargar los datos de la Cuota. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
			}


		}
		else
		{
			sMsg = "ERROR: Ocurrió un error al recuperar la Cuota. Por favor, revise los datos y avise a soporte.";
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
			
			if (CLCuotas.guardarNota(liCodCuota, sNota))
			{
				sMsg = "Nota guardada correctamente.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}
			else
			{
				sMsg = "ERROR: Ocurrio un error al guardar la nota de la Cuota. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
			}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		
		}
	}
	
	public void cargarDetallesActivo(ActionEvent actionEvent) 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
			
			logger.debug("sCOACES:|"+sCOACES+"|");
			
			if (sCOACES != "")
			{
		    	Sesion.guardaDetalle(sCOACES);
		    	Sesion.guardarHistorial("detallescuota.xhtml","GestorDetallesActivo");

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

				msg = Utils.pfmsgWarning("No se ha seleccionado ningún Activo.");
				
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
		}

		//return sPagina;
    }
	
	public void cargarDetallesComunidad(ActionEvent actionEvent) 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
			String sMsg = "";
			
	    	logger.debug("sCOCLDO:|"+sCOCLDO+"|");
	    	logger.debug("sNUDCOM:|"+sNUDCOM+"|");
	    	
	    	String sCodComunidad = Long.toString(CLComunidades.buscarCodigoComunidad(sCOCLDO, sNUDCOM));
	    	logger.debug("sCodComunidad:|"+sCodComunidad+"|");
	    	
	    	Sesion.guardaDetalle(sCodComunidad);
	    	Sesion.guardarHistorial("detallescuota.xhtml","GestorDetallesComunidad");

	    	sPagina = "detallescomunidad.xhtml";
	    	
	    	
			try 
			{
				sMsg = "Redirigiendo hacia '"+sPagina+"'";
				logger.info(sMsg);
				FacesContext.getCurrentInstance().getExternalContext().redirect(sPagina);
			}
			catch (IOException e)
			{
				FacesMessage msg;
				
				sMsg = "ERROR: Ocurrió un problema al acceder a los detalles de Comunidad. Por favor, avise a soporte.";
				
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

	public String getsDesCOCLDO() {
		return sDesCOCLDO;
	}

	public void setsDesCOCLDO(String sDesCOCLDO) {
		this.sDesCOCLDO = sDesCOCLDO;
	}

	public String getsDesCOSBAC() {
		return sDesCOSBAC;
	}

	public void setsDesCOSBAC(String sDesCOSBAC) {
		this.sDesCOSBAC = sDesCOSBAC;
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

	public String getsNODCCO() {
		return sNODCCO;
	}

	public void setsNODCCO(String sNODCCO) {
		this.sNODCCO = sNODCCO;
	}

	public String getsCOSBAC() {
		return sCOSBAC;
	}

	public void setsCOSBAC(String sCOSBAC) {
		this.sCOSBAC = sCOSBAC;
	}

	public String getsFIPAGO() {
		return sFIPAGO;
	}

	public void setsFIPAGO(String sFIPAGO) {
		this.sFIPAGO = sFIPAGO;
	}

	public String getsFFPAGO() {
		return sFFPAGO;
	}

	public void setsFFPAGO(String sFFPAGO) {
		this.sFFPAGO = sFFPAGO;
	}

	public String getsIMCUCO() {
		return sIMCUCO;
	}

	public void setsIMCUCO(String sIMCUCO) {
		this.sIMCUCO = sIMCUCO;
	}

	public String getsFAACTA() {
		return sFAACTA;
	}

	public void setsFAACTA(String sFAACTA) {
		this.sFAACTA = sFAACTA;
	}

	public String getsPTPAGO() {
		return sPTPAGO;
	}

	public void setsPTPAGO(String sPTPAGO) {
		this.sPTPAGO = sPTPAGO;
	}

	public String getsOBTEXC() {
		return sOBTEXC;
	}

	public void setsOBTEXC(String sOBTEXC) {
		this.sOBTEXC = sOBTEXC;
	}

	public String getsOBDEER() {
		return sOBDEER;
	}

	public void setsOBDEER(String sOBDEER) {
		this.sOBDEER = sOBDEER;
	}

	public String getsNota() {
		return sNota;
	}

	public void setsNota(String sNota) {
		this.sNota = sNota;
	}
	
}
