package com.provisiones.pl.detalles;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Sesion;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ReferenciaCatastral;
import com.provisiones.types.Transicion;

public class GestorDetallesReferencia implements Serializable 
{

	private static final long serialVersionUID = -4124878862721187556L;

	private static Logger logger = LoggerFactory.getLogger(GestorDetallesReferencia.class.getName());
	
	
	private String sNURCAT = "";
	private String sTIRCAT = "";
	private String sENEMIS = "";
	private String sCOTEXA = ValoresDefecto.DEF_COTEXA;
	private String sOBTEXC = "";
	
	//Ampliacion de valor catastral
	private String sIMVSUE = "";
	private String sIMCATA = "";
	private String sFERECA = "";
	
	//Buscar activos
	private String sCOACES = "";

	private String sNota = "";
	private String sNotaOriginal = "";
	private boolean bConNotas = false;
	
	private long liCodReferencia = 0;
	
	public GestorDetallesReferencia()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorDetallesReferencia...");
			cargarDetallesReferencia();
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
	
	public void cargarDetallesReferencia()
	{
		logger.debug("Cargando Referencia...");

		
		//this.sNUPROF = ((GestorListaProvisiones)((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorListaProvisiones")).getsNUPROF();
		
		//String sCodReferencia = Sesion.cargarDetalle();
		
		String sCodReferencia = CLReferencias.recuperaID();
		
		logger.debug("sCodReferencia:|"+sCodReferencia+"|");
		
		FacesMessage msg;
		
		String sMsg = "";
		
		if (!sCodReferencia.equals(""))
		{
			try
			{
				this.liCodReferencia = Long.parseLong(sCodReferencia);

			  	ReferenciaCatastral referencia = CLReferencias.buscarDetallesReferencia(liCodReferencia);

		    	logger.debug(referencia.logReferenciaCatastral());
			  	
		    	this.sNURCAT = referencia.getNURCAT();
		    	this.sTIRCAT = referencia.getTIRCAT();
		    	this.sENEMIS = referencia.getENEMIS();
		    	this.sCOTEXA = referencia.getCOTEXA();
		    	this.sOBTEXC = referencia.getOBTEXC();
		    	
		    	//Ampliacion de valor catastral
		    	this.sIMVSUE = Utils.recuperaImporte(false,referencia.getIMVSUE());
		    	this.sIMCATA = Utils.recuperaImporte(false,referencia.getIMCATA());
		    	this.sFERECA = Utils.recuperaFecha(referencia.getFERECA());

		    	
		    	this.sCOACES = Integer.toString(CLReferencias.obtenerActivoDeReferecia(liCodReferencia));
		    	
		    		
		    	this.sNotaOriginal = CLReferencias.buscarNota(liCodReferencia);
				this.sNota = sNotaOriginal;
				
				this.bConNotas = !sNota.isEmpty();
			
				sMsg = "La referencia se cargó correctamente.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
				
			}
			catch(NumberFormatException nfe)
			{
				sMsg = "ERROR: Ocurrio un error al cargar los datos de la Comunidad. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
			}


		}
		else
		{
			sMsg = "ERROR: Ocurrio un error al recuperar la comunidad. Por favor, revise los datos y avise a soporte.";
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
			
			if (CLReferencias.guardarNota(liCodReferencia, sNota))
			{
				sMsg = "Nota guardada correctamente.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}
			else
			{
				sMsg = "ERROR: Ocurrió un error al guardar la Nota de la Referencia Catastral. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
			}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		
		}
	}
	
    public void restablecerNota(ActionEvent actionEvent) 
    {  
    	this.sNota = sNotaOriginal;
    	this.bConNotas = !sNota.isEmpty();
    }
	
	
	public void cargarDetallesActivo(ActionEvent actionEvent) 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
			
			if (sCOACES != "")
			{
		    	Transicion transicion = new Transicion (
		    			sCOACES,
		    			ValoresDefecto.ID_ACTIVO,
		    			"detallesreferencia.xhtml",
		    			"GestorDetallesActivo");
		    	
		    	Sesion.guardarTransicion(transicion, false);
				
		    	//Sesion.guardaDetalle(sCOACES);
		    	//Sesion.guardarHistorial("detallesreferencia.xhtml","GestorDetallesActivo");

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

    }

	public String getsNURCAT() {
		return sNURCAT;
	}

	public void setsNURCAT(String sNURCAT) {
		this.sNURCAT = sNURCAT;
	}

	public String getsTIRCAT() {
		return sTIRCAT;
	}

	public void setsTIRCAT(String sTIRCAT) {
		this.sTIRCAT = sTIRCAT;
	}

	public String getsENEMIS() {
		return sENEMIS;
	}

	public void setsENEMIS(String sENEMIS) {
		this.sENEMIS = sENEMIS;
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

	public String getsIMVSUE() {
		return sIMVSUE;
	}

	public void setsIMVSUE(String sIMVSUE) {
		this.sIMVSUE = sIMVSUE;
	}

	public String getsIMCATA() {
		return sIMCATA;
	}

	public void setsIMCATA(String sIMCATA) {
		this.sIMCATA = sIMCATA;
	}

	public String getsFERECA() {
		return sFERECA;
	}

	public void setsFERECA(String sFERECA) {
		this.sFERECA = sFERECA;
	}

	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
	}

	public String getsNota() {
		return sNota;
	}

	public void setsNota(String sNota) {
		this.sNota = sNota;
	}

	public boolean isbConNotas() {
		return bConNotas;
	}

	public void setbConNotas(boolean bConNotas) {
		this.bConNotas = bConNotas;
	}
}

//Autor: Francisco Valverde Manjón