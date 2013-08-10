package com.provisiones.pl;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.ll.CLActivos;
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.MovimientoReferenciaCatastral;

public class GestorReferenciasCatastrales implements Serializable 
{

	static String sClassName = GestorReferenciasCatastrales.class.getName();
	
	private static final long serialVersionUID = 2458459163051982275L;
	
	private String sCODTRN = ValoresDefecto.DEF_E3_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOACCI = "A";
	private String sCOENGP = ValoresDefecto.DEF_COENGP;
	
	private String sNURCAT = "";
	private String sTIRCAT = "";
	private String sENEMIS = "";
	private String sCOTEXA = "";
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
	
	
	public GestorReferenciasCatastrales()
	{
		Utils.standardIO2File("");//Salida por fichero de texto
	}

	public void buscaActivos (ActionEvent actionEvent)
	{
		
		String sMethod = "buscaActivosComunidad";
		
		
		FacesMessage msg;
		
		ActivoTabla buscaactivos = new ActivoTabla(
				sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
				sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
				sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase());
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Buscando Activos...");
		
		this.setTablaactivos(CLReferencias.buscarActivosSinReferencias(buscaactivos));
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+getTablaactivos().size()+" activos relacionados.");

		msg = new FacesMessage("Encontrados "+getTablaactivos().size()+" activos relacionados.");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	public void comprobarActivo(ActionEvent actionEvent)
	{
		String sMethod = "comprobarCOACES";
		
		
		FacesMessage msg;
		
    	this.sNURCAT  = CLReferencias.referenciaCatastralActivo(sCOACES);
    	
    	if (sNURCAT.equals(""))
    	{
    		msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"No existe un numero de referencia catastral asociado.",null);
    		FacesContext.getCurrentInstance().addMessage(null, msg);
    		
    	}

		if (CLActivos.compruebaActivo(sCOACES))
		{
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "El activo '"+sCOACES.toUpperCase()+"' esta disponible.");
			msg = new FacesMessage("El activo '"+sCOACES.toUpperCase()+"' esta disponible.",null);
		}
		else
		{
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "ERROR: El activo '"+sCOACES.toUpperCase()+"' no esta registrado en el sistema. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El activo '"+sCOACES.toUpperCase()+"' no esta registrado en el sistema. Por favor, revise los datos.",null);
		}
		
		
		FacesContext.getCurrentInstance().addMessage(null, msg);	
		
	}
	
	public void borrarPlantillaActivo()
	{
    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
	}
	
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";

    	borrarPlantillaActivo();
    	
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
   	
    }
    
	public void borrarPlantillaReferencia()
	{
		this.sCOACES = "";
        this.sNURCAT = "";
        this.sTIRCAT = "";
        this.sENEMIS = "";
        this.sCOTEXA = "";
        this.sOBTEXC = "";
	}
    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	

    	borrarPlantillaActivo();
    	
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
    	
    	borrarPlantillaReferencia();
   	
    }
    

	
	public void seleccionarActivo(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "seleccionarActivo";

    	FacesMessage msg;
    	
    	
    	
    	//this.sCOACESBuscado = activoseleccionado.getCOACES();
    	
    	this.sCOACES  = activoseleccionado.getCOACES();
    	this.sNURCAT  = CLReferencias.referenciaCatastralActivo(sCOACES);
    	
    	if (sNURCAT.equals(""))
    	{
    		msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"No existe un numero de referencia catastral asociado.",null);
    		FacesContext.getCurrentInstance().addMessage(null, msg);
    		
    	}
    		
    	
    	msg = new FacesMessage("Activo "+ sCOACES +" Seleccionado.");
    	
    	com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Activo seleccionado: |"+sCOACES+"|");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		//return "listacomunidadesactivos.xhtml";
    }
	
	public void realizaAlta(ActionEvent actionEvent)
	{
		String sMethod = "realizaAlta";
		
		//MovimientoComunidad movimiento = new MovimientoComunidad (sCODTRN.toUpperCase(), sCOTDOR.toUpperCase(), sIDPROV.toUpperCase(), sCOACCI.toUpperCase(), sCOENGP.toUpperCase(), sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase(), sBITC10.toUpperCase(), sCOACES.toUpperCase(), sBITC01.toUpperCase(), sNOMCOC.toUpperCase(), sBITC02.toUpperCase(), sNODCCO.toUpperCase(), sBITC03.toUpperCase(), sNOMPRC.toUpperCase(), sBITC04.toUpperCase(), sNUTPRC.toUpperCase(), sBITC05.toUpperCase(), sNOMADC.toUpperCase(), sBITC06.toUpperCase(), sNUTADC.toUpperCase(), sBITC07.toUpperCase(), sNODCAD.toUpperCase(), sBITC08.toUpperCase(), sNUCCEN.toUpperCase(), sNUCCOF.toUpperCase(), sNUCCDI.toUpperCase(), sNUCCNT.toUpperCase(), sBITC09.toUpperCase(), sOBTEXC.toUpperCase(), sOBDEER.toUpperCase());
		MovimientoReferenciaCatastral movimiento = new MovimientoReferenciaCatastral (
				sCODTRN.toUpperCase(), 
				sCOTDOR.toUpperCase(), 
				sIDPROV.toUpperCase(), 
				"A",//sCOACCI.toUpperCase(), 
				sCOENGP.toUpperCase(), 
				sCOACES.toUpperCase(), 
				sNURCAT.toUpperCase(),
				"", 
				sTIRCAT.toUpperCase(),
				"", 
				sENEMIS.toUpperCase(),
				sCOTEXA.toUpperCase(),
				"", 
				sOBTEXC.toUpperCase(), 
				sOBDEER.toUpperCase());
		
		FacesMessage msg;
		
		int iSalida = CLReferencias.registraMovimiento(movimiento);
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Codigo de salida:"+iSalida);
		
		switch (iSalida) 
		{
		case 0: //Sin errores
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "La cuota se ha creado correctamente.");
			msg = new FacesMessage("La cuota se ha creado correctamente.");
			break;
		case -1: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "El Numero de referencia catastral no ha sido informado. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El Numero de referencia catastral no ha sido informado. Por favor, revise los datos.",null);
			break;
		case -2: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al crear la referencia, se ha realizado una accion no permitida. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear la referencia, se ha realizado una accion no permitida. Por favor, revise los datos.",null);
			break;
		case -3: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al crear la referencia, ya esta dada de alta. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear la referencia, ya esta dada de alta. Por favor, revise los datos.",null);
			break;
			
		case -4: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al consultar la referencia asociada, su estado es desconocido. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al consultar la referencia asociada, su estado es desconocido. Por favor, revise los datos.",null);
			break;
 		case -5: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error, la referencia asociada esta dada de baja. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, la referencia asociada esta dada de baja. Por favor, revise los datos.",null);
			break;

		case -6: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "La modificacion solicitada no incluye cambios. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La modificacion solicitada no incluye cambios. Por favor, revise los datos.",null);
			break;
		case -7: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al crear el momiviento de cuota. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear el momiviento de cuota. Por favor, revise los datos.",null);
			break;
		/*case -7: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al dar de alta la cuota. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al dar de alta la cuota. Por favor, revise los datos.",null);
			break;*/
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

	public String getsOBDEER() {
		return sOBDEER;
	}

	public void setsOBDEER(String sOBDEER) {
		this.sOBDEER = sOBDEER;
	}

}
