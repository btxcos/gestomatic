package com.provisiones.pl;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.ll.CLComunidades;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.Comunidad;
import com.provisiones.types.MovimientoComunidad;

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
	
	public GestorTablaComunidadActivo()
	{
		Utils.standardIO2File("");//Salida por fichero de texto
	}
	


	private ActivoTabla activoseleccionadoalta = null;
	private ActivoTabla activoseleccionadobaja = null;
	
	private ArrayList<ActivoTabla> tablaactivos = null;

	private ArrayList<ActivoTabla> tablaactivoscomunidad = null;
	
	public void buscaActivos (ActionEvent actionEvent)
	{
		
		String sMethod = "buscaActivos";
		
		
		FacesMessage msg;
		
		ActivoTabla buscaactivos = new ActivoTabla(
				sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
				sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
				sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase());
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Buscando Activos...");
		
		this.setTablaactivos(CLComunidades.buscarActivosDisponibles(buscaactivos));
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+getTablaactivos().size()+" activos relacionados.");

		msg = new FacesMessage("Encontrados "+getTablaactivos().size()+" activos relacionados.");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}

	public void cargarComunidad(ActionEvent actionEvent)
	{
		String sMethod = "cargarComunidad";
		
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
			
			this.setTablaactivoscomunidad(CLComunidades.buscarActivosComunidad(sCOCLDO, sNUDCOM));
			
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+getTablaactivoscomunidad().size()+" activos relacionados.");
			
			msg = new FacesMessage("La comunidad '"+sNUDCOM.toUpperCase()+"' se ha cargado correctamente.",null);
			
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "La comunidad '"+sNUDCOM.toUpperCase()+"' se ha cargado correctamente.");			
		}
		
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
				
	}

	public void seleccionarActivo(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "seleccionarActivo";

    	FacesMessage msg;
    	
    	
    	
    	//this.sCOACESBuscado = activoseleccionado.getCOACES();
    	
    	this.sCOACES  = activoseleccionadoalta.getCOACES();
    	
    	msg = new FacesMessage("Activo "+ sCOACES +" Seleccionado.");
    	
    	com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Activo seleccionado: |"+sCOACES+"|");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		//return "listacomunidadesactivos.xhtml";
    }

	public FacesMessage nuevoMovimiento(String sCodCOACCI)
	{
		String sMethod = "nuevoMovimiento";

		FacesMessage msg;
		
    	MovimientoComunidad movimiento = new MovimientoComunidad (
    			ValoresDefecto.DEF_E1_CODTRN, 
    			ValoresDefecto.DEF_COTDOR, 
    			ValoresDefecto.DEF_IDPROV, 
    			sCodCOACCI, 
    			ValoresDefecto.DEF_COENGP, 
    			sCOCLDO.toUpperCase(), 
    			sNUDCOM.toUpperCase(), 
    			"S", 
    			sCOACES.toUpperCase(), 
    			"#", "", 
    			"#", "", 
    			"#", "", 
    			"#", "", 
    			"#", "", 
    			"#", "", 
    			"#", "", 
    			"#", "", "", "", "", 
    			"#", "", 
    			"");
    	

		
		int iSalida = CLComunidades.registraMovimiento(movimiento);
		
		switch (iSalida) 
		{
		case 0: //Sin errores
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "El movimiento en la comunidad '"+ movimiento.getNOMCOC() + "', con documento '"+movimiento.getNUDCOM()+"', se ha creado correctamente.");
			msg = new FacesMessage("El movimiento en la comunidad '"+ movimiento.getNOMCOC() + "', con documento '"+movimiento.getNUDCOM()+"', se ha creado correctamente.");
			if (sCodCOACCI.equals("X"))
			{
				tablaactivoscomunidad.add(activoseleccionadoalta);
			}
			else if (sCodCOACCI.equals("E"))
			{
				tablaactivoscomunidad.remove(activoseleccionadobaja);
			}
			break;
		case -1: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al dar de alta la comunidad '"+ movimiento.getNOMCOC() + "', se ha realizado una accion no permitida. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al dar de alta la comunidad '"+ movimiento.getNOMCOC() + "', se ha realizado una accion no permitida. Por favor, revise los datos.",null);
			break;
		case -2: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al modificar la comunidad '"+ movimiento.getNOMCOC() + "', ya esta dada de alta. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al modificar la comunidad '"+ movimiento.getNOMCOC() + "', ya esta dada de alta. Por favor, revise los datos.",null);
			break;
		case -3: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al modificar un activo de la comunidad '"+ movimiento.getNOMCOC() + "', el activo esta vacio. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al modificar un activo de la comunidad '"+ movimiento.getNOMCOC() + "', el activo esta vacio. Por favor, revise los datos.",null);
			break;
		case -4: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "La comunidad '"+ movimiento.getNOMCOC() + "' fue dada de baja. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La comunidad '"+ movimiento.getNOMCOC() + "' fue dada de baja. Por favor, revise los datos.",null);
			break;
		case -5: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "No existe la comunidad con identificador '"+ movimiento.getNUDCOM() + "'. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe la comunidad con identificador '"+ sNUDCOM + "'. Por favor, revise los datos.",null);
			break;
		case -6: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "No puede darse de baja la comunidad '"+ movimiento.getNOMCOC() + "' por que tiene cuotas asignadas. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puede darse de baja la comunidad '"+ movimiento.getNOMCOC() + "' por que tiene cuotas asignadas. Por favor, revise los datos.",null);
			break;
		case -7: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al dar de alta un movimiento en la comunidad '"+ movimiento.getNOMCOC() + "'. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al dar de alta un movimiento en la comunidad '"+ movimiento.getNOMCOC() + "'. Por favor, revise los datos.",null);
			break;
		case -8: //Error COACCI = "";
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al dar de alta un movimiento. No se ha elegido una accion valida. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al dar de alta un movimiento. No se ha elegido una accion valida. Por favor, revise los datos.",null);
			break;
		case -11: //Error con rollback
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al actualizar el estado de la comunidad '"+ movimiento.getNOMCOC() + "'. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar el estado de la comunidad '"+ movimiento.getNOMCOC() + "'. Por favor, revise los datos.",null);
			break;
		case -12: //Error con rollback
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al dar de alta la relaccion de movimientos para '"+ movimiento.getNOMCOC() + "'. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al dar de alta la relaccion de movimientos para '"+ movimiento.getNOMCOC() + "'. Por favor, revise los datos.",null);
			break;
		default: //error generico
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "ERROR: El cambio solicitado ha producido un error desconocido. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El cambio solicitado ha producido un error desconocido. Por favor, revise los datos.",null);
			break;
		}
		
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Finalizadas las comprobaciones.");
		
		return msg;
	}
	
    public void altaActivoComunidad(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "altaActivoComunidad";

    	FacesMessage msg;

    	//this.sCOACESBuscado = activoseleccionado.getCOACES();
    	
    	this.sCOACES  = activoseleccionadoalta.getCOACES();
    	
    	//buscar activo y darlo de alta en la comunidad
    	
    	com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Activo seleccionado: |"+sCOACES+"|");
    	
    	

    	msg = nuevoMovimiento("X");

    	
    	this.sCOACES  = "";
    	
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		//return "listacomunidadesactivos.xhtml";
    }
    
    public void bajaActivoComunidad(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "bajaActivoComunidad";

    	FacesMessage msg;

    	if (activoseleccionadobaja == null)
    	{
    		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Esto esta vacio hermano!!!");
    	}
    	
    	com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Activo seleccionado: |"+activoseleccionadobaja.getCOACES()+"|");
    	
    	this.sCOACES  = activoseleccionadobaja.getCOACES();
    	
    	com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Activo seleccionado: |"+sCOACES+"|");
    	
    	//buscar activo y borrarlo
    	
    	
    	
    	msg = new FacesMessage("Activo "+ sCOACES +" Seleccionado.");
    	
    	com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Activo seleccionado: |"+sCOACES+"|");
   	
    	msg = nuevoMovimiento("E");
    	
    	
    	this.sCOACES  = "";
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		//return "listacomunidadesactivos.xhtml";
    }

    
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";

    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
    	
    	this.activoseleccionadobaja = null;
    	this.tablaactivos = null;
   	
    }
    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	this.sCOCLDO = "";
    	this.sNUDCOM = "";
    	this.sNOMCOC = "";
    	this.sNODCCO = "";

    	this.sCOACES = "";

    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
    	
    	this.activoseleccionadoalta = null;
    	this.activoseleccionadobaja = null;
    	this.tablaactivos = null;
    	this.tablaactivoscomunidad = null;
   	
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


	
	
	public ActivoTabla getActivoseleccionadoalta() {
		return activoseleccionadoalta;
	}

	public void setActivoseleccionadoalta(ActivoTabla activoseleccionadoalta) {
		this.activoseleccionadoalta = activoseleccionadoalta;
	}

	public ActivoTabla getActivoseleccionadobaja() {
		return activoseleccionadobaja;
	}

	public void setActivoseleccionadobaja(ActivoTabla activoseleccionadobaja) {
		this.activoseleccionadobaja = activoseleccionadobaja;
	}

	public ArrayList<ActivoTabla> getTablaactivos() {
		return tablaactivos;
	}

	public void setTablaactivos(ArrayList<ActivoTabla> tablaactivos) {
		this.tablaactivos = tablaactivos;
	}

}
