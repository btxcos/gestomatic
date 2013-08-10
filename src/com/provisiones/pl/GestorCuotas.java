package com.provisiones.pl;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.ll.CLComunidades;
import com.provisiones.ll.CLCuotas;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.Comunidad;
import com.provisiones.types.MovimientoCuota;

public class GestorCuotas implements Serializable 
{
	static String sClassName = GestorCuotas.class.getName();

	private static final long serialVersionUID = 498724061894118772L;

	private String sCODTRN = ValoresDefecto.DEF_E2_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOACCI = "A";
	private String sCOENGP = ValoresDefecto.DEF_COENGP;

	private String sCOCLDO = "";
	private String sNUDCOM = "";
	private String sNOMCOC = "";
	private String sNODCCO = "";

	private boolean bDevolucion = false;
	
	private String sCOSBAC = "";
	private String sFIPAGO = "";
	private String sFFPAGO = "";
	private String sIMCUCO = "";
	private String sFAACTA = "";
	private String sPTPAGO = "";
	private String sOBTEXC = "";
	
	private String sOBDEER = "";
	
	//Buscar activos
	private String sCOACES = "";

	private String sCOPOIN = "";
	private String sNOMUIN = "";	
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	
	private ArrayList<ActivoTabla> tablaactivos = null;
	
	private ActivoTabla activoseleccionado = null;
	
	public GestorCuotas()
	{


		Utils.standardIO2File("");//Salida por fichero de texto

	}
	
	public void borrarCampos()
	{
		this.sCOCLDO = "";
		this.sNUDCOM = "";
		this.sCOSBAC = "";
		this.sFIPAGO = "";
		this.sFFPAGO = "";
		this.sIMCUCO = "";
		this.sFAACTA = "";
		this.sPTPAGO = "";
		this.sOBTEXC = "";
	}
	
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
		this.sCOCLDO = "";
		this.sNUDCOM = "";
		this.sCOSBAC = "";
		this.sFIPAGO = "";
		this.sFFPAGO = "";
		this.sIMCUCO = "";
		this.sFAACTA = "";
		this.sPTPAGO = "";
		this.sOBTEXC = "";
    	this.setsNOMCOC("");
    	this.setsNODCCO("");

    	this.sCOACES = "";

    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
   	
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
  	
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
    	
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
   	
    }
    
	public void cargarComunidad(ActionEvent actionEvent)
	{
		String sMethod = "cargarComunidad";
		
		FacesMessage msg;
		
		if (CLComunidades.consultaEstadoComunidad(sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase()))
		{
			Comunidad comunidad = CLComunidades.consultaComunidad(sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase());
		
			this.sCOCLDO = comunidad.getCOCLDO();
			this.sNUDCOM = comunidad.getNUDCOM();
			this.sNOMCOC = comunidad.getNOMCOC();
			this.sNODCCO = comunidad.getNODCCO();
		
		
		
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
		}
		else
		{
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, La comunidad '"+sNUDCOM.toUpperCase()+"' no esta dada de alta en el sistema.",null);
			
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error: La comunidad '"+sNUDCOM.toUpperCase()+"' no esta dada de alta en el sistema.");
			
			this.sCOCLDO = "";
			this.sNUDCOM = "";
		}
		
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
	
	public void buscaActivosComunidad (ActionEvent actionEvent)
	{
		
		String sMethod = "buscaActivosComunidad";
		
		
		FacesMessage msg;
		
		ActivoTabla buscaactivos = new ActivoTabla(
				sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
				sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
				sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase());
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Buscando Activos...");
		
		this.setTablaactivos(CLCuotas.buscarActivosComunidadDisponibles(buscaactivos, sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase()));
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+getTablaactivos().size()+" activos relacionados.");

		msg = new FacesMessage("Encontrados "+getTablaactivos().size()+" activos relacionados.");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	public void comprobarActivo(ActionEvent actionEvent)
	{
		String sMethod = "comprobarCOACES";
		
		Utils.standardIO2File("");//Salida por fichero de texto
		
		FacesMessage msg;
		
		int iSalida = CLCuotas.comprobarActivo(sCOCLDO.toUpperCase(),sNUDCOM.toUpperCase(),sCOACES.toUpperCase());

		switch (iSalida) 
		{
		case 0: //Sin errores
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "El activo '"+sCOACES.toUpperCase()+"' esta disponible.");
			msg = new FacesMessage("El activo '"+sCOACES.toUpperCase()+"' esta disponible.",null);
			break;
		case -1: //Sin errores
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "ERROR: El activo '"+sCOACES.toUpperCase()+"' no pertenece a la comunidad. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El activo '"+sCOACES.toUpperCase()+"' no pertenece a la comunidad. Por favor, revise los datos.",null);
			break;
		case -2: //Sin errores
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "ERROR: El activo '"+sCOACES.toUpperCase()+"' no esta disponible. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El activo '"+sCOACES.toUpperCase()+"' no esta disponible. Por favor, revise los datos.",null);
			break;
		default: //error generico
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "ERROR: El activo '"+sCOACES.toUpperCase()+"' ha producido un error desconocido. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El activo '"+sCOACES.toUpperCase()+"' ha producido un error desconocido. Por favor, revise los datos.",null);
			break;
		}
		
		
		FacesContext.getCurrentInstance().addMessage(null, msg);	
		
	}
	

	
	
	public void realizaAlta(ActionEvent actionEvent)
	{
		String sMethod = "registraMovimiento";
		
		Utils.standardIO2File("");//Salida por fichero de texto
		
		//MovimientoComunidad movimiento = new MovimientoComunidad (sCODTRN.toUpperCase(), sCOTDOR.toUpperCase(), sIDPROV.toUpperCase(), sCOACCI.toUpperCase(), sCOENGP.toUpperCase(), sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase(), sBITC10.toUpperCase(), sCOACES.toUpperCase(), sBITC01.toUpperCase(), sNOMCOC.toUpperCase(), sBITC02.toUpperCase(), sNODCCO.toUpperCase(), sBITC03.toUpperCase(), sNOMPRC.toUpperCase(), sBITC04.toUpperCase(), sNUTPRC.toUpperCase(), sBITC05.toUpperCase(), sNOMADC.toUpperCase(), sBITC06.toUpperCase(), sNUTADC.toUpperCase(), sBITC07.toUpperCase(), sNODCAD.toUpperCase(), sBITC08.toUpperCase(), sNUCCEN.toUpperCase(), sNUCCOF.toUpperCase(), sNUCCDI.toUpperCase(), sNUCCNT.toUpperCase(), sBITC09.toUpperCase(), sOBTEXC.toUpperCase(), sOBDEER.toUpperCase());
		MovimientoCuota movimiento = new MovimientoCuota (
				sCODTRN.toUpperCase(), 
				sCOTDOR.toUpperCase(), 
				sIDPROV.toUpperCase(), 
				"A",//sCOACCI.toUpperCase(), 
				sCOCLDO.toUpperCase(), 
				sNUDCOM.toUpperCase(), 
				sCOENGP.toUpperCase(), 
				sCOACES.toUpperCase(), 
				ValoresDefecto.DEF_COGRUG_E2, 
				ValoresDefecto.DEF_COTACA_E2, 
				Utils.compruebaPago(false,sCOSBAC.toUpperCase()), 
				"", 
				Utils.compruebaFecha(sFIPAGO.toUpperCase()), 
				"", 
				Utils.compruebaFecha(sFFPAGO.toUpperCase()), 
				"", 
				Utils.compruebaImporte(false,sIMCUCO.toUpperCase()), 
				"", 
				Utils.compruebaFecha(sFAACTA.toUpperCase()), 
				"", 
				sPTPAGO.toUpperCase(),
				"", 
				sOBTEXC.toUpperCase(), 
				sOBDEER.toUpperCase());
		
		FacesMessage msg;
		
		int iSalida = CLCuotas.registraMovimiento(movimiento);
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Codigo de salida:"+iSalida);
		
		switch (iSalida) 
		{
		case 0: //Sin errores
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "La cuota se ha creado correctamente.");
			msg = new FacesMessage("La cuota se ha creado correctamente.");
			break;
		case -1: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al crear la cuota, se ha realizado una accion no permitida. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear la cuota, se ha realizado una accion no permitida. Por favor, revise los datos.",null);
			break;
		case -2: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al crear la cuota, ya esta dada de alta. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear la cuota, ya esta dada de alta. Por favor, revise los datos.",null);
			break;
			
/*		case -3: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al consultar la cuota asociada, su estado es desconocido. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al consultar la cuota asociada, su estado es desconocido. Por favor, revise los datos.",null);
			break;
 		case -4: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error, la cuota asociada esta dada de baja. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, la cuota asociada esta dada de baja. Por favor, revise los datos.",null);
			break;

		case -5: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "La modificacion solicitada no incluye cambios. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La modificacion solicitada no incluye cambios. Por favor, revise los datos.",null);
			break;*/
		case -6: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al crear el momiviento de cuota. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear el momiviento de cuota. Por favor, revise los datos.",null);
			break;
		case -7: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al dar de alta la cuota. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al dar de alta la cuota. Por favor, revise los datos.",null);
			break;
		case -8: //Error COACCI = "";
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al crear una relaccion de cuotas. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear una relaccion de cuotas. Por favor, revise los datos.",null);
			break;//"Error al registrar el movimiento, no hay motivo del cambio. Por favor, revise los datos."
			
		/*case -9: //Error COACCI = "";
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al registrar el movimiento, no se ha producido cambios en la comunidad. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al registrar el movimiento, no se ha producido cambios en la comunidad. Por favor, revise los datos.",null);
			break;
		case -11: //Error con rollback
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al actualizar el estado de la comunidad '"+ movimiento.getNOMCOC() + "'. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar el estado de la comunidad '"+ movimiento.getNOMCOC() + "'. Por favor, revise los datos.",null);
			break;
		case -12: //Error con rollback
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al dar de alta la relaccion de movimientos para '"+ movimiento.getNOMCOC() + "'. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al dar de alta la relaccion de movimientos para '"+ movimiento.getNOMCOC() + "'. Por favor, revise los datos.",null);
			break;*/
		default: //error generico
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "ERROR: El cambio solicitado ha producido un error desconocido. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El cambio solicitado ha producido un error desconocido. Por favor, revise los datos.",null);
			break;
		}
		
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Finalizadas las comprobaciones.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
	
	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
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

	public String getsCOACCI() {
		return sCOACCI;
	}

	public void setsCOACCI(String sCOACCI) {
		this.sCOACCI = sCOACCI;
	}

	public String getsCOENGP() {
		return sCOENGP;
	}

	public void setsCOENGP(String sCOENGP) {
		this.sCOENGP = sCOENGP;
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

	public ArrayList<ActivoTabla> getTablaactivos() {
		return tablaactivos;
	}

	public void setTablaactivos(ArrayList<ActivoTabla> tablaactivos) {
		this.tablaactivos = tablaactivos;
	}

	public ActivoTabla getActivoseleccionado() {
		return activoseleccionado;
	}

	public void setActivoseleccionado(ActivoTabla activoseleccionado) {
		this.activoseleccionado = activoseleccionado;
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

	public String getsOBDEER() {
		return sOBDEER;
	}

	public void setsOBDEER(String sOBDEER) {
		this.sOBDEER = sOBDEER;
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

	public boolean isbDevolucion() {
		return bDevolucion;
	}

	public void setbDevolucion(boolean bDevolucion) {
		this.bDevolucion = bDevolucion;
	}

}
