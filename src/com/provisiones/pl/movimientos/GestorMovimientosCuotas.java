package com.provisiones.pl.movimientos;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.ll.CLCuotas;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.CuotaTabla;
import com.provisiones.types.MovimientoCuota;

public class GestorMovimientosCuotas implements Serializable 
{

	private static final long serialVersionUID = 558593056565873600L;
	
	static String sClassName = GestorMovimientosCuotas.class.getName();

	private String sCODTRN = ValoresDefecto.DEF_E2_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOACCI = "";
	private String sCOENGP = ValoresDefecto.DEF_COENGP;

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
	private String sDesPTPAGO = "";
	private String sOBTEXC = "";
	
	private String sOBDEER = "";
	
	private String sCOPOIN = "";
	private String sNOMUIN = "";
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	
	
	private ActivoTabla activoseleccionado = null;
	private ArrayList<ActivoTabla> tablaactivos = null;

	private CuotaTabla cuotaseleccionada = null;
	private ArrayList<CuotaTabla> tablacuotas = null;

	
	public GestorMovimientosCuotas()
	{
		Utils.standardIO2File("");//Salida por fichero de texto
	}
	
	public void borrarCamposCuota()
	{
		this.sCOCLDO = "";
		this.sDesCOCLDO = "";
		this.sNUDCOM = "";
		this.sCOSBAC = "";
		this.sDesCOSBAC = "";
		this.sFIPAGO = "";
		this.sFFPAGO = "";
		this.sIMCUCO = "";
		this.sFAACTA = "";
		this.sPTPAGO = "";
		this.sDesPTPAGO = "";
		this.sOBTEXC = "";
		
    	this.setCuotaseleccionada(null);
    	this.setTablacuotas(null);
		
	}
	
	public void borrarCamposActivo()
	{
    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
    	
    	this.setActivoseleccionado(null);
    	this.setTablaactivos(null);
	}
	
	public void limpiarPlantillaCuotas(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";
    	borrarCamposCuota(); 
  	
    } 

    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  

    	borrarCamposActivo();
   	
    }
    
	public void buscaActivos (ActionEvent actionEvent)
	{
		
		String sMethod = "buscaActivos";
		
		
		FacesMessage msg;
		
		ActivoTabla buscaactivos = new ActivoTabla(
				sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
				sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
				sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase());
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Buscando Activos...");
		
		this.setTablaactivos(CLCuotas.buscarActivosConCuotas(buscaactivos));
		
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
	
	public void cargarCuotas(ActionEvent actionEvent)
	{
		String sMethod = "cargarCuotas";
		
		FacesMessage msg;
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Buscando cuotas...");
		
		this.tablacuotas = CLCuotas.buscarCuotasActivo(sCOACES.toUpperCase());
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Encontradas "+getTablacuotas().size()+" cuotas relacionadas.");

		msg = new FacesMessage("Encontradas "+getTablacuotas().size()+" cuotas relacionadas.");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
				
	}
	
	public void seleccionarCuota(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "seleccionarCuota";

    	FacesMessage msg;
    	
    	this.sCOCLDO = cuotaseleccionada.getCOCLDO(); 
    	this.sDesCOCLDO = cuotaseleccionada.getDCOCLDO();
    	this.sNUDCOM = cuotaseleccionada.getNUDCOM();
    	this.sCOSBAC = cuotaseleccionada.getCOSBAC();
    	this.sDesCOSBAC = cuotaseleccionada.getDCOSBAC();
    	this.sFIPAGO = cuotaseleccionada.getFIPAGO();
    	this.sFFPAGO = cuotaseleccionada.getFFPAGO();
    	this.sIMCUCO = cuotaseleccionada.getIMCUCO();
    	this.sFAACTA = cuotaseleccionada.getFAACTA();
    	this.sPTPAGO = cuotaseleccionada.getPTPAGO();
    	this.sDesPTPAGO = cuotaseleccionada.getDPTPAGO();
    	this.sOBTEXC = cuotaseleccionada.getOBTEXC();
    	

    	//comprobar
    	
    	msg = new FacesMessage("'"+ sDesCOSBAC +"' Seleccionado.");
    	
    	com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Cuota seleccionada: |"+sCOACES+"|");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		//return "listacomunidadesactivos.xhtml";
    }
	
	public void registraDatos(ActionEvent actionEvent)
	{
		String sMethod = "registraDatos";
		
		Utils.standardIO2File("");//Salida por fichero de texto
		
		//MovimientoComunidad movimiento = new MovimientoComunidad (sCODTRN.toUpperCase(), sCOTDOR.toUpperCase(), sIDPROV.toUpperCase(), sCOACCI.toUpperCase(), sCOENGP.toUpperCase(), sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase(), sBITC10.toUpperCase(), sCOACES.toUpperCase(), sBITC01.toUpperCase(), sNOMCOC.toUpperCase(), sBITC02.toUpperCase(), sNODCCO.toUpperCase(), sBITC03.toUpperCase(), sNOMPRC.toUpperCase(), sBITC04.toUpperCase(), sNUTPRC.toUpperCase(), sBITC05.toUpperCase(), sNOMADC.toUpperCase(), sBITC06.toUpperCase(), sNUTADC.toUpperCase(), sBITC07.toUpperCase(), sNODCAD.toUpperCase(), sBITC08.toUpperCase(), sNUCCEN.toUpperCase(), sNUCCOF.toUpperCase(), sNUCCDI.toUpperCase(), sNUCCNT.toUpperCase(), sBITC09.toUpperCase(), sOBTEXC.toUpperCase(), sOBDEER.toUpperCase());
		MovimientoCuota movimiento = new MovimientoCuota (
				sCODTRN.toUpperCase(), 
				sCOTDOR.toUpperCase(), 
				sIDPROV.toUpperCase(), 
				sCOACCI.toUpperCase(), 
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
		case -3: //Error
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
			break;
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
			
		case -9: //Error COACCI = "";
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al registrar el movimiento, no se ha producido cambios en la comunidad. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al registrar el movimiento, no se ha producido cambios en la comunidad. Por favor, revise los datos.",null);
			break;
		case -11: //Error con rollback
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al actualizar el estado de la cuota. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar el estado de la cuota. Por favor, revise los datos.",null);
			break;
		case -12: //Error con rollback
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al dar de alta la relaccion de movimientos. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al dar de alta la relaccion de movimientos. Por favor, revise los datos.",null);
			break;
		default: //error generico
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "ERROR: El cambio solicitado ha producido un error desconocido. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El cambio solicitado ha producido un error desconocido. Por favor, revise los datos.",null);
			break;
		}
		
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Finalizadas las comprobaciones.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

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

	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
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

	public CuotaTabla getCuotaseleccionada() {
		return cuotaseleccionada;
	}

	public void setCuotaseleccionada(CuotaTabla cuotaseleccionada) {
		this.cuotaseleccionada = cuotaseleccionada;
	}

	public ArrayList<CuotaTabla> getTablacuotas() {
		return tablacuotas;
	}

	public void setTablacuotas(ArrayList<CuotaTabla> tablacuotas) {
		this.tablacuotas = tablacuotas;
	}

	public String getsDesCOSBAC() {
		return sDesCOSBAC;
	}

	public void setsDesCOSBAC(String sDesCOSBAC) {
		this.sDesCOSBAC = sDesCOSBAC;
	}

	public String getsDesPTPAGO() {
		return sDesPTPAGO;
	}

	public void setsDesPTPAGO(String sDesPTPAGO) {
		this.sDesPTPAGO = sDesPTPAGO;
	}

	public String getsDesCOCLDO() {
		return sDesCOCLDO;
	}

	public void setsDesCOCLDO(String sDesCOCLDO) {
		this.sDesCOCLDO = sDesCOCLDO;
	}
	
	
	
}
