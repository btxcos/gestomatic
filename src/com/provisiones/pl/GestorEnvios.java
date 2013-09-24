package com.provisiones.pl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.ll.CLComunidades;
import com.provisiones.ll.CLCuotas;
import com.provisiones.ll.CLGastos;
import com.provisiones.ll.CLImpuestos;
import com.provisiones.ll.CLProvisiones;
import com.provisiones.ll.CLReferencias;
import com.provisiones.ll.FileManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

public class GestorEnvios implements Serializable 
{

	private static final long serialVersionUID = 2302669387183120830L;

	private static Logger logger = LoggerFactory.getLogger(GestorEnvios.class.getName());
	
	private String sNumComunidades = "0";
	private boolean bNumComunidades = true;
	private String sNumCuotas = "0";
	private boolean bNumCuotas = true;
	private String sNumReferencias = "0";
	private boolean bNumReferencias = true;
	private String sNumImpuestos = "0";
	private boolean bNumImpuestos = true;
	private String sNumProvisiones = "0";
	private boolean bNumProvisiones = true;
	private String sNumGastos = "0";
	private boolean bNumGastos = true;


	private transient StreamedContent file;
	
	private String sFicheroComunidades = "";
	private String sFicheroCuotas = "";
	private String sFicheroReferencias = "";
	private String sFicheroImpuestos = "";
	
	private String sFicheroCierres = "";
	private String sFicheroGastos = "";
	
	public GestorEnvios()
	{

	}

	public void cargarMovimientosPendientes(ActionEvent actionEvent)
	{
		FacesMessage msg;
    	
    	String sMsg = "";
    	
    	this.sNumComunidades  = Long.toString(CLComunidades.buscarNumeroMovimientosComunidadesPendientes());
    	this.bNumComunidades = sNumComunidades.equals("0");
    	if (!bNumComunidades)
    	{
    		this.sFicheroComunidades = FileManager.escribirComunidades();
 
    		if (!sFicheroComunidades.equals(""))
    		{

	    		sMsg = "Generado el fichero de Comunidades a enviar.";
	        	
	    		msg = Utils.pfmsgInfo(sMsg);
	    		logger.info(sMsg);
    			
    		}
    		else
    		{
	    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Comunidades.";
	        	
	    		msg = Utils.pfmsgError(sMsg);
	    		logger.error(sMsg);
    		}
    		
    		FacesContext.getCurrentInstance().addMessage(null, msg);

    	}

    	this.sNumCuotas  = Long.toString(CLCuotas.buscarNumeroMovimientosCuotasPendientes());
    	this.bNumCuotas = sNumCuotas.equals("0");
    	if (!bNumCuotas)
    	{
    		this.sFicheroCuotas = FileManager.escribirCuotas();
    		 
    		if (!sFicheroCuotas.equals(""))
    		{

	    		sMsg = "Generado el fichero de Cuotas a enviar.";
	        	
	    		msg = Utils.pfmsgInfo(sMsg);
	    		logger.info(sMsg);
    			
    		}
    		else
    		{
	    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Cuotas.";
	        	
	    		msg = Utils.pfmsgError(sMsg);
	    		logger.error(sMsg);
    		}
    		
    		FacesContext.getCurrentInstance().addMessage(null, msg);
    	}

    	this.sNumReferencias  = Long.toString(CLReferencias.buscarNumeroMovimientosReferenciasPendientes());
    	this.bNumReferencias = sNumReferencias.equals("0");
    	if (!bNumReferencias)
    	{
    		this.sFicheroReferencias = FileManager.escribirReferencias();
   		 
    		if (!sFicheroReferencias.equals(""))
    		{

	    		sMsg = "Generado el fichero de Referencias a enviar.";
	    		msg = Utils.pfmsgInfo(sMsg);
	    		logger.info(sMsg);
    			
    		}
    		else
    		{
	    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Referencias.";
	        	
	    		msg = Utils.pfmsgError(sMsg);
	    		logger.error(sMsg);
    		}
    		
    		FacesContext.getCurrentInstance().addMessage(null, msg);
    	}
    	
    	this.sNumImpuestos  = Long.toString(CLImpuestos.buscarNumeroMovimientosImpuestosPendientes());
    	this.bNumImpuestos = sNumImpuestos.equals("0");
    	if (!bNumImpuestos)
    	{
    		this.sFicheroImpuestos = FileManager.escribirImpuestos();
      		 
    		if (!sFicheroImpuestos.equals(""))
    		{

	    		sMsg = "Generado el fichero de Impuestos a enviar.";
	        	
	    		msg = Utils.pfmsgInfo(sMsg);
	    		logger.info(sMsg);
    			
    		}
    		else
    		{
	    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Impuestos.";
	        	
	    		msg = Utils.pfmsgError(sMsg);
	    		logger.error(sMsg);
    		}
    		
    		FacesContext.getCurrentInstance().addMessage(null, msg);	
    	}

    	this.sNumGastos  = Long.toString(CLGastos.buscarNumeroMovimientosGastosPendientes());
    	this.bNumGastos = sNumGastos.equals("0");
    	if (!bNumGastos)
    	{
    		this.sFicheroGastos = FileManager.escribirGastos();
    		 
    		if (!sFicheroGastos.equals(""))
    		{

	    		sMsg = "Generado el fichero de Gastos a enviar.";
	        	
	    		msg = Utils.pfmsgInfo(sMsg);
	    		logger.info(sMsg);
    			
    		}
    		else
    		{
	    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Gastos.";
	        	
	    		msg = Utils.pfmsgError(sMsg);
	    		logger.error(sMsg);
    		}
    		
    		FacesContext.getCurrentInstance().addMessage(null, msg);
    	}
    	
    	this.sNumProvisiones  = Long.toString(CLProvisiones.buscarNumeroProvisionesCerradas());
    	this.bNumProvisiones = sNumProvisiones.equals("0");
    	if (!bNumProvisiones)
    	{
    		this.sFicheroCierres = FileManager.escribirCierres();
     		 
    		if (!sFicheroCierres.equals(""))
    		{

	    		sMsg = "Generado el fichero de Provisiones a enviar.";
	        	
	    		msg = Utils.pfmsgInfo(sMsg);
	    		logger.info(sMsg);
    			
    		}
    		else
    		{
	    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Provisiones.";
	        	
	    		msg = Utils.pfmsgError(sMsg);
	    		logger.error(sMsg);
    		}
    		
    		FacesContext.getCurrentInstance().addMessage(null, msg);
    	}

    	if (bNumComunidades && bNumCuotas && bNumReferencias && bNumImpuestos && bNumGastos && bNumProvisiones)
    	{
    		sMsg = "No hay movimientos pendientes.";
    		msg = Utils.pfmsgWarning(sMsg);
    		logger.warn(sMsg);
    	}
    	else
    	{
    		sMsg = "Cargados todos los movimientos pendientes.";
    		msg = Utils.pfmsgInfo(sMsg);
    		logger.info(sMsg);

    	}


		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void descargarComunidades() 
    {  
    	FacesMessage msg;
    	
    	String sMsg = "";

    	try 
		{
			InputStream stream = new FileInputStream(sFicheroComunidades);
			
			this.file = new DefaultStreamedContent(stream, "text/plain", ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E1+".txt");
			
    		sMsg = "Descargado el fichero de Comunidades a enviar.";
        	
    		msg = Utils.pfmsgInfo(sMsg);
    		logger.info(sMsg);

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			
    		sMsg = "ERROR: Ocurrio un problema al acceder al archivo.";
        	
    		msg = Utils.pfmsgError(sMsg);
    		logger.error(sMsg);
		}

		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
    }
	
	public void descargarCuotas() 
    {  
    	FacesMessage msg;
    	
    	String sMsg = "";

    	try 
		{
			InputStream stream = new FileInputStream(sFicheroCuotas);
			
			this.file = new DefaultStreamedContent(stream, "text/plain", ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E2+".txt");
			
    		sMsg = "Descargado el fichero de Cuotas a enviar.";
        	
    		msg = Utils.pfmsgInfo(sMsg);
    		logger.info(sMsg);

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			
    		sMsg = "ERROR: Ocurrio un problema al acceder al archivo.";
        	
    		msg = Utils.pfmsgError(sMsg);
    		logger.error(sMsg);
		}

		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
    }
	
	public void descargarReferencias() 
    {  
    	FacesMessage msg;
    	
    	String sMsg = "";
    	
    	try 
		{
			InputStream stream = new FileInputStream(sFicheroReferencias);
			
			this.file = new DefaultStreamedContent(stream, "text/plain", ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E3+".txt");
			
    		sMsg = "Descargado el fichero de Referencias a enviar.";
        	
    		msg = Utils.pfmsgInfo(sMsg);
    		logger.info(sMsg);

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			
    		sMsg = "ERROR: Ocurrio un problema al acceder al archivo.";
        	
    		msg = Utils.pfmsgError(sMsg);
    		logger.error(sMsg);
		}

		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
    }
	
	public void descargarImpuestos() 
    {  
    	FacesMessage msg;
    	
    	String sMsg = "";
    	
    	try 
		{
			InputStream stream = new FileInputStream(sFicheroImpuestos);
			
			this.file = new DefaultStreamedContent(stream, "text/plain", ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E4+".txt");
			
    		sMsg = "Descargado el fichero de Impuestos a enviar.";
        	
    		msg = Utils.pfmsgInfo(sMsg);
    		logger.info(sMsg);

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			
    		sMsg = "ERROR: Ocurrio un problema al acceder al archivo.";
        	
    		msg = Utils.pfmsgError(sMsg);
    		logger.error(sMsg);
		}

		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
    }
	
	public void descargarGastos() 
    {  
    	FacesMessage msg;
    	
    	String sMsg = "";
    	
    	try 
		{
			InputStream stream = new FileInputStream(sFicheroGastos);
			
			this.file = new DefaultStreamedContent(stream, "text/plain", ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_GA+".txt");
			
    		sMsg = "Descargado el fichero de Gastos a enviar.";
        	
    		msg = Utils.pfmsgInfo(sMsg);
    		logger.info(sMsg);

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			
    		sMsg = "ERROR: Ocurrio un problema al acceder al archivo.";
        	
    		msg = Utils.pfmsgError(sMsg);
    		logger.error(sMsg);
		}

		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
    }
	
	public void descargarProvisiones() 
    {  
    	FacesMessage msg;
    	
    	String sMsg = "";
    	
    	try 
		{
			InputStream stream = new FileInputStream(sFicheroCierres);
			
			this.file = new DefaultStreamedContent(stream, "text/plain", ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_PP+".txt");
			
    		sMsg = "Descargado el fichero de Provisiones a enviar.";
        	
    		msg = Utils.pfmsgInfo(sMsg);
    		logger.info(sMsg);

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			
    		sMsg = "ERROR: Ocurrio un problema al acceder al archivo.";
        	
    		msg = Utils.pfmsgError(sMsg);
    		logger.error(sMsg);
		}

		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
    }
	
	public String getsNumComunidades() {
		return sNumComunidades;
	}

	public void setsNumComunidades(String sNumComunidades) {
		this.sNumComunidades = sNumComunidades;
	}

	public String getsNumCuotas() {
		return sNumCuotas;
	}

	public void setsNumCuotas(String sNumCuotas) {
		this.sNumCuotas = sNumCuotas;
	}

	public String getsNumReferencias() {
		return sNumReferencias;
	}

	public void setsNumReferencias(String sNumReferencias) {
		this.sNumReferencias = sNumReferencias;
	}

	public String getsNumImpuestos() {
		return sNumImpuestos;
	}

	public void setsNumImpuestos(String sNumImpuestos) {
		this.sNumImpuestos = sNumImpuestos;
	}

	public String getsNumProvisiones() {
		return sNumProvisiones;
	}

	public void setsNumProvisiones(String sNumProvisiones) {
		this.sNumProvisiones = sNumProvisiones;
	}

	public String getsNumGastos() {
		return sNumGastos;
	}

	public void setsNumGastos(String sNumGastos) {
		this.sNumGastos = sNumGastos;
	}

	public StreamedContent getFile() 
	{
		return file;
	}

	public boolean isbNumComunidades() {
		return bNumComunidades;
	}

	public void setbNumComunidades(boolean bNumComunidades) {
		this.bNumComunidades = bNumComunidades;
	}

	public boolean isbNumCuotas() {
		return bNumCuotas;
	}

	public void setbNumCuotas(boolean bNumCuotas) {
		this.bNumCuotas = bNumCuotas;
	}

	public boolean isbNumReferencias() {
		return bNumReferencias;
	}

	public void setbNumReferencias(boolean bNumReferencias) {
		this.bNumReferencias = bNumReferencias;
	}

	public boolean isbNumImpuestos() {
		return bNumImpuestos;
	}

	public void setbNumImpuestos(boolean bNumImpuestos) {
		this.bNumImpuestos = bNumImpuestos;
	}

	public boolean isbNumProvisiones() {
		return bNumProvisiones;
	}

	public void setbNumProvisiones(boolean bNumProvisiones) {
		this.bNumProvisiones = bNumProvisiones;
	}

	public boolean isbNumGastos() {
		return bNumGastos;
	}

	public void setbNumGastos(boolean bNumGastos) {
		this.bNumGastos = bNumGastos;
	}

	public String getsFicheroComunidades() {
		return sFicheroComunidades;
	}

	public void setsFicheroComunidades(String sFicheroComunidades) {
		this.sFicheroComunidades = sFicheroComunidades;
	}

	public String getsFicheroCuotas() {
		return sFicheroCuotas;
	}

	public void setsFicheroCuotas(String sFicheroCuotas) {
		this.sFicheroCuotas = sFicheroCuotas;
	}

	public String getsFicheroReferencias() {
		return sFicheroReferencias;
	}

	public void setsFicheroReferencias(String sFicheroReferencias) {
		this.sFicheroReferencias = sFicheroReferencias;
	}

	public String getsFicheroImpuestos() {
		return sFicheroImpuestos;
	}

	public void setsFicheroImpuestos(String sFicheroImpuestos) {
		this.sFicheroImpuestos = sFicheroImpuestos;
	}

	public String getsFicheroCierres() {
		return sFicheroCierres;
	}

	public void setsFicheroCierres(String sFicheroCierres) {
		this.sFicheroCierres = sFicheroCierres;
	}

	public String getsFicheroGastos() {
		return sFicheroGastos;
	}

	public void setsFicheroGastos(String sFicheroGastos) {
		this.sFicheroGastos = sFicheroGastos;
	}

}