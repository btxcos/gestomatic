package com.provisiones.pl;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.ll.CLActivos;
import com.provisiones.ll.CLComunidades;
import com.provisiones.misc.Utils;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.Comunidad;

public class GestorTablaComunidadActivo implements Serializable 
{

	private static final long serialVersionUID = 3791951733627793686L;
	
	static String sClassName = GestorTablaComunidadActivo.class.getName();
	
	private String sCOACES = "";
	private String sCOPOIN = "";
	private String sNOMUIN = "";
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	
	private String sCOCLDO = "";
	private String sNUDCOM = "";
	private String sNOMCOC = "";
	private String sNODCCO = "";
	
	

	private String sCOACESBuscado = "";
	
	private ActivoTabla activoseleccionado;
	
	private ArrayList<ActivoTabla> tablaactivos; 

	private ArrayList<ActivoTabla> tablaactivoscomunidad;
	
	public void buscaActivos (ActionEvent actionEvent)
	{
		
		String sMethod = "buscaActivos";
		
		Utils.standardIO2File("");//Salida por fichero de texto
		
		FacesMessage msg;
		
		ActivoTabla buscaactivos = new ActivoTabla(
				sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
				sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
				sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase());
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Buscando Activos...");
		
		this.setTablaactivos(CLActivos.buscarActivos(buscaactivos));
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+getTablaactivos().size()+" activos relacionados.");

		msg = new FacesMessage("Encontrados "+getTablaactivos().size()+" activos relacionados.");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
    public void seleccionarActivo(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "seleccionarActivo";

    	FacesMessage msg;
    	
    	
    	
    	//this.sCOACESBuscado = activoseleccionado.getCOACES();
    	
    	this.sCOACES  = activoseleccionado.getCOACES();
    	
    	msg = new FacesMessage("Activo "+ sCOACES +" Seleccionado.");
    	
    	com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Activo seleccionado: |"+sCOACES+"|");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		//return "listacomunidadesactivos.xhtml";
    }

    public void altaActivo(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "bajaActivo";

    	FacesMessage msg;

    	//this.sCOACESBuscado = activoseleccionado.getCOACES();
    	
    	this.sCOACES  = activoseleccionado.getCOACES();
    	
    	//buscar activo y darlo de alta en la comunidad
    	
    	
    	
    	msg = new FacesMessage("Activo "+ sCOACES +" Seleccionado.");
    	
    	com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Activo seleccionado: |"+sCOACES+"|");
    	
    	
    	tablaactivos.remove(activoseleccionado);  
    	
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		//return "listacomunidadesactivos.xhtml";
    }
    
    public void bajaActivo(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "bajaActivo";

    	FacesMessage msg;

    	//this.sCOACESBuscado = activoseleccionado.getCOACES();
    	
    	this.sCOACES  = activoseleccionado.getCOACES();
    	
    	//buscar activo y borrarlo
    	
    	
    	
    	msg = new FacesMessage("Activo "+ sCOACES +" Seleccionado.");
    	
    	com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Activo seleccionado: |"+sCOACES+"|");
    	
    	
    	tablaactivos.remove(activoseleccionado);  
    	
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		//return "listacomunidadesactivos.xhtml";
    }

    public void borrarPlantilla(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";
    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
    	this.sCOACESBuscado =  "";
    } 
    
	public void cargarComunidad(ActionEvent actionEvent)
	{
		String sMethod = "cargarComunidad";
		
		Utils.standardIO2File("");//Salida por fichero de texto
		
		Comunidad comunidad = CLComunidades.consultaComunidad(sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase());
		
		this.sCOCLDO = comunidad.getCOCLDO();
		this.sNUDCOM = comunidad.getNUDCOM();
		this.sNOMCOC = comunidad.getNOMCOC();
		this.sNODCCO = comunidad.getNODCCO();
		
		FacesMessage msg;
		
		if (comunidad.getNUDCOM().equals(""))
		{
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, La comunidad '"+sNUDCOM.toUpperCase()+"' no esta registrada en el sistema.",null);
			
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error: La comunidad '"+sNUDCOM.toUpperCase()+"' no esta registrada en el sistema.");
		}
		else
		{
			msg = new FacesMessage("La comunidad '"+sNUDCOM.toUpperCase()+"' se ha cargado correctamente.",null);
			
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "La comunidad '"+sNUDCOM.toUpperCase()+"' se ha cargado correctamente.");			
		}
		
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
				
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
	public String getsNODCCO() {
		return sNODCCO;
	}
	public void setsNODCCO(String sNODCCO) {
		this.sNODCCO = sNODCCO;
	}
	public ArrayList<ActivoTabla> getTablaactivoscomunidad() {
		return tablaactivoscomunidad;
	}
	public void setTablaactivoscomunidad(
			ArrayList<ActivoTabla> tablaactivoscomunidad) {
		this.tablaactivoscomunidad = tablaactivoscomunidad;
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
	
	public String getsCOACESBuscado() {
		return sCOACESBuscado;
	}
	public void setsCOACESBuscado(String sCOACESBuscado) {
		this.sCOACESBuscado = sCOACESBuscado;
	}
	

}
