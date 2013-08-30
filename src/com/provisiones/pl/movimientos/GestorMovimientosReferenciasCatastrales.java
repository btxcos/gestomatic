package com.provisiones.pl.movimientos;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.MovimientoReferenciaCatastral;
import com.provisiones.types.ReferenciaTabla;

public class GestorMovimientosReferenciasCatastrales implements Serializable 
{

	static String sClassName = GestorMovimientosReferenciasCatastrales.class.getName();
	
	private static final long serialVersionUID = -1805847552638917701L;

	private String sCODTRN = ValoresDefecto.DEF_E3_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOACCI = "";
	private String sCOENGP = ValoresDefecto.DEF_COENGP;
	
	private String sNURCAT = "";
	private String sTIRCAT = "";
	private String sENEMIS = "";
	private String sCOTEXA = ValoresDefecto.DEF_COTEXA;
	private String sOBTEXC = "";
	
	private String sOBDEER = "";
	
	//Ampliacion de valor catastral
	private String sIMVSUE = "";
	private String sIMCATA = "";
	private String sFERECA = "";
	
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
	
	private ArrayList<ReferenciaTabla> tablareferencias = null;
	private ReferenciaTabla referenciaseleccionada = null;
	
	
	public GestorMovimientosReferenciasCatastrales()
	{
		Utils.standardIO2File("");//Salida por fichero de texto
	}

	public void buscaActivos (ActionEvent actionEvent)
	{
		
		String sMethod = "buscaActivos";
		
		
		FacesMessage msg;
		
		ActivoTabla buscaactivos = new ActivoTabla(
				sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
				sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
				sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(),"");
		
		Utils.debugTrace(true, sClassName, sMethod, "Buscando Activos...");
		
		this.setTablaactivos(CLReferencias.buscarActivosConReferencias(buscaactivos));
		
		Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+getTablaactivos().size()+" activos relacionados.");

		msg = new FacesMessage("Encontrados "+getTablaactivos().size()+" activos relacionados.");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	public void cargarReferencias (ActionEvent actionEvent)
	{
		
		String sMethod = "cargarReferencias";
		
		FacesMessage msg;
		
		String sMsg = ""; 
		
		Utils.debugTrace(true, sClassName, sMethod, "Buscando cuotas...");
		
		this.tablareferencias = CLReferencias.buscarReferenciasActivo(sCOACES.toUpperCase());
		
		sMsg = "Encontradas "+getTablareferencias().size()+" referencias relacionadas.";
		
		Utils.debugTrace(true, sClassName, sMethod, sMsg);

		msg = new FacesMessage(sMsg, null);
		
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
        
    	//Ampliacion de valor catastral
    	this.sIMVSUE = "";
    	this.sIMCATA = "";
    	this.sFERECA = "";
        
        this.sENEMIS = "";
        this.sOBTEXC = "";
	}
    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	

    	borrarPlantillaActivo();
    	
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
    	
    	borrarPlantillaReferencia();
    	
    	this.referenciaseleccionada = null;
    	this.tablareferencias = null;
   	
    }
    
	public void hoyFERECA (ActionEvent actionEvent)
	{
		String sMethod = "hoyFERECA";
		this.setsFERECA(Utils.fechaDeHoy(true));
		Utils.debugTrace(true, sClassName, sMethod, "sFERECA:|"+sFERECA+"|");
	}
	
	public void seleccionarActivo(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "seleccionarActivo";

    	FacesMessage msg;
    	
    	this.sCOACES  = activoseleccionado.getCOACES();
    	
   		msg = new FacesMessage("Activo "+ sCOACES +" cargado.");
    	
   		Utils.debugTrace(true, sClassName, sMethod, "Activo seleccionado: |"+sCOACES+"|");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
    }
	
	public void seleccionarReferencia(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "seleccionarReferencia";

    	FacesMessage msg;
    	
    	this.sNURCAT = referenciaseleccionada.getNURCAT(); 
    	this.sTIRCAT = referenciaseleccionada.getTIRCAT();
    	this.sENEMIS = referenciaseleccionada.getENEMIS();
    	this.sOBTEXC = referenciaseleccionada.getOBTEXC();
    	
    	//Ampliacion de valor catastral
    	this.sIMVSUE = referenciaseleccionada.getIMVSUE();
    	this.sIMCATA = referenciaseleccionada.getIMCATA();
    	this.sFERECA = referenciaseleccionada.getFERECA();

    	Utils.debugTrace(true, sClassName, sMethod, "sIMVSUE:|"+sIMVSUE+"|");
    	Utils.debugTrace(true, sClassName, sMethod, "sIMCATA:|"+sIMCATA+"|");
    	Utils.debugTrace(true, sClassName, sMethod, "sFERECA:|"+sFERECA+"|");
 	
    	String sMsg = "Referencia '"+ sNURCAT +"' Seleccionada.";
    	
    	msg = new FacesMessage(sMsg, null);
    	
    	Utils.debugTrace(true, sClassName, sMethod, sMsg);
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		//return "listacomunidadesactivos.xhtml";
    }
	
	
	public void registraDatos(ActionEvent actionEvent)
	{
		String sMethod = "registraDatos";
		
		
		
		//MovimientoComunidad movimiento = new MovimientoComunidad (sCODTRN.toUpperCase(), sCOTDOR.toUpperCase(), sIDPROV.toUpperCase(), sCOACCI.toUpperCase(), sCOENGP.toUpperCase(), sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase(), sBITC10.toUpperCase(), sCOACES.toUpperCase(), sBITC01.toUpperCase(), sNOMCOC.toUpperCase(), sBITC02.toUpperCase(), sNODCCO.toUpperCase(), sBITC03.toUpperCase(), sNOMPRC.toUpperCase(), sBITC04.toUpperCase(), sNUTPRC.toUpperCase(), sBITC05.toUpperCase(), sNOMADC.toUpperCase(), sBITC06.toUpperCase(), sNUTADC.toUpperCase(), sBITC07.toUpperCase(), sNODCAD.toUpperCase(), sBITC08.toUpperCase(), sNUCCEN.toUpperCase(), sNUCCOF.toUpperCase(), sNUCCDI.toUpperCase(), sNUCCNT.toUpperCase(), sBITC09.toUpperCase(), sOBTEXC.toUpperCase(), sOBDEER.toUpperCase());
		MovimientoReferenciaCatastral movimiento = new MovimientoReferenciaCatastral (
				sCODTRN.toUpperCase(), 
				sCOTDOR.toUpperCase(), 
				sIDPROV.toUpperCase(), 
				sCOACCI.toUpperCase(), 
				sCOENGP.toUpperCase(), 
				sCOACES.toUpperCase(), 
				sNURCAT.toUpperCase(),
				"", 
				sTIRCAT.toUpperCase(),
				"", 
				sENEMIS.toUpperCase(),
				ValoresDefecto.DEF_COTEXA,
				"", 
				sOBTEXC.toUpperCase(), 
				sOBDEER.toUpperCase(),
				"", 
				Utils.compruebaImporte(sIMVSUE.toUpperCase()),
				"", 
				Utils.compruebaImporte(sIMCATA.toUpperCase()),
				"", 
				Utils.compruebaFecha(sFERECA.toUpperCase()));
		
		FacesMessage msg;
		
		String sMsg = "";
		
		int iSalida = CLReferencias.registraMovimiento(movimiento);
		
		Utils.debugTrace(true, sClassName, sMethod, "Codigo de salida:"+iSalida);
		
		switch (iSalida) 
		{
		case 0: //Sin errores
			sMsg = "El movimiento se ha registrado correctamente.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(sMsg,null);
	    	break;

		case -1: //Error 001 - CODIGO DE ACCION DEBE SER A,M o B
			sMsg = "ERROR:001 - No se ha elegido una acccion correcta. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -3: //Error 003 - NO EXISTE EL ACTIVO
			sMsg = "ERROR:003 - El activo elegido no esta registrado en el sistema. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -49: //Error 049 - LA REFERENCIA CATASTRAL YA EXISTE NO SE PUEDE DAR DE ALTA
			sMsg = "ERROR:049 - La referencia catastral propocionada ya esta registrada en el sistema. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -50: //Error 050 - LA REFERENCIA CATASTRAL NO EXISTE NO SE PUEDE MODIFICAR
			sMsg = "ERROR:050 - La referencia catastral propocionada no esta registrada en el sistema. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;			

		case -51: //Error 051 - LA REFERENCIA CATASTRAL NO EXISTE NO SE PUEDE DAR DE BAJA
			sMsg = "ERROR:051 - La referencia catastral propocionada no esta registrada en el sistema. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -52: //Error 052 - TITULAR CATASTRAL OBLIGATORIO. NO SE PUEDE DAR DE ALTA
			sMsg = "ERROR:052 - El titular catastral es obligatorio. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -53: //Error 053 - EXISTEN DATOS EN GMAE57. NO SE PUEDE REALIZAR LA BAJA
			sMsg = "ERROR:053 - Existen recursos o impuestos pendientes de esta referencia. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -54: //Error 054 - LA REFERENCIA CATASTRAL ES OBLIGATORIA
			sMsg = "ERROR:054 - La referencia catastral es obligatoria. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		//Ampliacion de valor catastral
		case -82: //Error 082 - EL VALOR DEL SUELO TIENE QUE SER MAYOR DE CERO
			sMsg = "ERROR:082 - El valor del suelo debe de ser mayor que 0. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
				
		case -83: //Error 083 - EL VALOR CATASTRAL TIENE QUE SER MAYOR DE CERO
			sMsg = "ERROR:083 - El valor catastral debe de ser mayor que 0. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -85: //Error 085 - FECHA REVISION DEL VALOR CATASTRAL NO TRAE UN VALOR LOGICO
			sMsg = "ERROR:085 - La fecha de revision del valor catastral no esta bien informada. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -700: //Error 700 - No existe realcion con ese activo
			sMsg = "ERROR:700 - El activo suministrado no esta relacionado con la referencia catastral informada. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -701: //Error 701 - Valor del suelo incorrecto
			sMsg = "ERROR:701 - El valor del suelo no esta correctamente informado. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -702: //Error 702 - Valor catastral incorrecto
			sMsg = "ERROR:702 - El valor catastral no esta correctamente informado. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -801: //Error 801 - alta de una referencia en alta
			sMsg = "ERROR:801 - La referencia ya esta dada de alta. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -802: //Error 802 - referencia catastral de baja no puede recibir mas movimientos
			sMsg = "ERROR:802 - La referencia catastral esta baja y no puede recibir mas movimientos. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -803: //Error 803 - estado no disponible
			sMsg = "ERROR:803 - El estado de la referencia catastral informada no esta disponible. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -804: //Error 804 - modificacion sin cambios
			sMsg = "ERROR:804 - No hay modificaciones que realizar. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -900: //Error 900 - al crear un movimiento
			sMsg = "ERROR:900 - Se ha producido un error al registrar el movimiento. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -901: //Error 901 - error y rollback - error al crear la cuota
			sMsg = "ERROR:901 - Se ha producido un error al registrar la referencia catastral. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;
			
		case -902: //Error 902 - error y rollback - error al registrar la relaccion
			sMsg = "ERROR:902 - Se ha producido un error al registrar la relacion. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -903: //Error 903 - error y rollback - error al cambiar el estado
			sMsg = "ERROR:903 - Se ha producido un error al cambiar el estado de la referencia catastral. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -904: //Error 904 - error y rollback - error al modificar la cuota
			sMsg = "ERROR:904 - Se ha producido un error al modificar la referencia catastral. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		default: //error generico
			sMsg = "ERROR:"+iSalida+" - La operacion solicitada ha producido un error desconocido. Por favor, revise los datos."; 
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;
		}
		
		
		Utils.debugTrace(true, sClassName, sMethod, "Finalizadas las comprobaciones.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

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

	public String getsOBDEER() {
		return sOBDEER;
	}

	public void setsOBDEER(String sOBDEER) {
		this.sOBDEER = sOBDEER;
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

	public ArrayList<ReferenciaTabla> getTablareferencias() {
		return tablareferencias;
	}

	public void setTablareferencias(ArrayList<ReferenciaTabla> tablareferencias) {
		this.tablareferencias = tablareferencias;
	}

	public ReferenciaTabla getReferenciaseleccionada() {
		return referenciaseleccionada;
	}

	public void setReferenciaseleccionada(ReferenciaTabla referenciaseleccionada) {
		this.referenciaseleccionada = referenciaseleccionada;
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
	
	
}
