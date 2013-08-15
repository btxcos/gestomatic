package com.provisiones.pl.movimientos;

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

public class GestorMovimientosComunidades implements Serializable 
{
	static String sClassName = GestorMovimientosComunidades.class.getName();

	private static final long serialVersionUID = -9157997142376942992L;

	private String sCODTRN = ValoresDefecto.DEF_E1_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOACCI = "";
	private String sCOENGP = ValoresDefecto.DEF_COENGP;
	private String sCOCLDO = "";
	private String sNUDCOM = "";
	private String sCOACES = "";
	private String sNOMCOC = "";
	private String sNODCCO = "";
	private String sNOMPRC = "";
	private String sNUTPRC = "";
	private String sNOMADC = "";
	private String sNUTADC = "";
	private String sNODCAD = "";
	private String sNUCCEN = "";
	private String sNUCCOF = "";
	private String sNUCCDI = "";
	private String sNUCCNT = "";

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
	
	public GestorMovimientosComunidades()
	{
		Utils.standardIO2File("");//Salida por fichero de texto
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

	public void borrarCampos()
	{
		this.sCOCLDO = "";
		this.sNUDCOM = "";
		this.sNOMCOC = "";
		this.sNODCCO = "";
		this.sNOMPRC = "";
		this.sNUTPRC = "";
		this.sNOMADC = "";
		this.sNUTADC = "";
		this.sNODCAD = "";
		this.sNUCCEN = "";
		this.sNUCCOF = "";
		this.sNUCCDI = "";
		this.sNUCCNT = "";
		this.sOBTEXC = "";
	}
	
	public void limpiarPlantilla(ActionEvent actionEvent)
	{
		borrarCampos();
		this.sCOACES = "";
	}
	
	public void seleccionarActivo(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "seleccionarActivo";

    	FacesMessage msg;
    	
    	this.sCOACES  = activoseleccionado.getCOACES();
    	
    	msg = new FacesMessage("Activo "+ sCOACES +" Seleccionado.");
    	
    	Utils.debugTrace(true, sClassName, sMethod, "Activo seleccionado: |"+sCOACES+"|");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void buscaActivos (ActionEvent actionEvent)
	{
		
		String sMethod = "buscaActivos";
		
		
		FacesMessage msg;
		
		ActivoTabla buscaactivos = new ActivoTabla(
				sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
				sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
				sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase());
		
		Utils.debugTrace(true, sClassName, sMethod, "Buscando Activos...");
		
		this.setTablaactivos(CLComunidades.buscaActivosConComunidad(buscaactivos));
		
		Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+getTablaactivos().size()+" activos relacionados.");

		msg = new FacesMessage("Encontrados "+getTablaactivos().size()+" activos relacionados.");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	public void comprobarActivo(ActionEvent actionEvent)
	{
		String sMethod = "comprobarCOACES";
		
		Utils.standardIO2File("");//Salida por fichero de texto
		
		FacesMessage msg;
		
		String sMsg = "";
		
		int iSalida = CLComunidades.comprobarActivo(sCOACES.toUpperCase());

		switch (iSalida) 
		{
			case 0: //Sin errores
				sMsg = "El activo '"+sCOACES.toUpperCase()+"' esta disponible.";
				Utils.debugTrace(true, sClassName, sMethod, sMsg);
				msg = new FacesMessage(sMsg,null);
				break;

			case -1: //error - ya vinculado
				sMsg = "ERROR: El activo '"+sCOACES.toUpperCase()+"' ya esta vinculado a otra comunidada. Por favor, revise los datos."; 
				Utils.debugTrace(true, sClassName, sMethod, sMsg);
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,sMsg,null);
				break;

			case -2: //error - no existe
				sMsg = "ERROR: El activo '"+sCOACES.toUpperCase()+"' no se encuentra registrado en el sistema. Por favor, revise los datos.";
				Utils.debugTrace(true, sClassName, sMethod, sMsg);
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,sMsg,null);
				break;

			default: //error generico
				sMsg = "ERROR: El activo '"+sCOACES.toUpperCase()+"' ha producido un error desconocido. Por favor, revise los datos.";
				Utils.debugTrace(true, sClassName, sMethod, sMsg);
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,sMsg,null);
				break;
		}

		
		FacesContext.getCurrentInstance().addMessage(null, msg);	
		
	}
	
	public void buscarComunidad(ActionEvent actionEvent)
	{
		String sMethod = "BuscarComunidad";
		
		FacesMessage msg;
		
		String sMsg = "";
		
		borrarCampos();

		Comunidad comunidad = CLComunidades.buscarComunidad(sCOACES.toUpperCase());
		
		this.sCOCLDO = comunidad.getCOCLDO();
		this.sNUDCOM = comunidad.getNUDCOM();
		this.sNOMCOC = comunidad.getNOMCOC();
		this.sNODCCO = comunidad.getNODCCO();
		this.sNOMPRC = comunidad.getNOMPRC();
		this.sNUTPRC = comunidad.getNUTPRC();
		this.sNOMADC = comunidad.getNOMADC();
		this.sNUTADC = comunidad.getNUTADC();
		this.sNODCAD = comunidad.getNODCAD();
		this.sNUCCEN = comunidad.getNUCCEN();
		this.sNUCCOF = comunidad.getNUCCOF();
		this.sNUCCDI = comunidad.getNUCCDI();
		this.sNUCCNT = comunidad.getNUCCNT();
		this.sOBTEXC = comunidad.getOBTEXC();
		
		if (comunidad.getNUDCOM().equals(""))
		{
			sMsg = "Error: El Activo '"+sCOACES.toUpperCase()+"' no esta asociado a ninguna comunidad.";
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);

			Utils.debugTrace(true, sClassName, sMethod, sMsg);
		}
		else
		{
			sMsg = "La comunidad se ha cargado correctamente.";
			msg = new FacesMessage(sMsg,null);
		
			Utils.debugTrace(true, sClassName, sMethod, sMsg);			
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	

	public void cargarComunidad(ActionEvent actionEvent)
	{
		String sMethod = "cargarComunidad";
		
		FacesMessage msg;
		
		String sMsg = "";
		

		Comunidad comunidad = CLComunidades.consultaComunidad(sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase());
		
		this.sCOCLDO = comunidad.getCOCLDO();
		this.sNUDCOM = comunidad.getNUDCOM();
		this.sNOMCOC = comunidad.getNOMCOC();
		this.sNODCCO = comunidad.getNODCCO();
		this.sNOMPRC = comunidad.getNOMPRC();
		this.sNUTPRC = comunidad.getNUTPRC();
		this.sNOMADC = comunidad.getNOMADC();
		this.sNUTADC = comunidad.getNUTADC();
		this.sNODCAD = comunidad.getNODCAD();
		this.sNUCCEN = comunidad.getNUCCEN();
		this.sNUCCOF = comunidad.getNUCCOF();
		this.sNUCCDI = comunidad.getNUCCDI();
		this.sNUCCNT = comunidad.getNUCCNT();
		this.sOBTEXC = comunidad.getOBTEXC();

		if (comunidad.getNUDCOM().equals(""))
		{
			sMsg = "Error: La comunidad '"+sNUDCOM.toUpperCase()+"' no esta registrada en el sistema.";
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);

			Utils.debugTrace(true, sClassName, sMethod, sMsg);
		}
		else
		{
			sMsg = "La comunidad '"+sNUDCOM.toUpperCase()+"' se ha cargado correctamente.";
			msg = new FacesMessage(sMsg,null);
		
			Utils.debugTrace(true, sClassName, sMethod, sMsg);			
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
				
	}
	
	public void registraDatos(ActionEvent actionEvent)
	{
		String sMethod = "registraDatos";
		
		
		
		//MovimientoComunidad movimiento = new MovimientoComunidad (sCODTRN.toUpperCase(), sCOTDOR.toUpperCase(), sIDPROV.toUpperCase(), sCOACCI.toUpperCase(), sCOENGP.toUpperCase(), sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase(), sBITC10.toUpperCase(), sCOACES.toUpperCase(), sBITC01.toUpperCase(), sNOMCOC.toUpperCase(), sBITC02.toUpperCase(), sNODCCO.toUpperCase(), sBITC03.toUpperCase(), sNOMPRC.toUpperCase(), sBITC04.toUpperCase(), sNUTPRC.toUpperCase(), sBITC05.toUpperCase(), sNOMADC.toUpperCase(), sBITC06.toUpperCase(), sNUTADC.toUpperCase(), sBITC07.toUpperCase(), sNODCAD.toUpperCase(), sBITC08.toUpperCase(), sNUCCEN.toUpperCase(), sNUCCOF.toUpperCase(), sNUCCDI.toUpperCase(), sNUCCNT.toUpperCase(), sBITC09.toUpperCase(), sOBTEXC.toUpperCase(), sOBDEER.toUpperCase());
		MovimientoComunidad movimiento = new MovimientoComunidad (sCODTRN.toUpperCase(), sCOTDOR.toUpperCase(), sIDPROV.toUpperCase(), sCOACCI.toUpperCase(), sCOENGP.toUpperCase(), sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase(), "", sCOACES.toUpperCase(), "", sNOMCOC.toUpperCase(), "", sNODCCO.toUpperCase(), "", sNOMPRC.toUpperCase(), "", sNUTPRC.toUpperCase(), "", sNOMADC.toUpperCase(), "", sNUTADC.toUpperCase(), "", sNODCAD.toUpperCase(), "", sNUCCEN.toUpperCase(), sNUCCOF.toUpperCase(), sNUCCDI.toUpperCase(), sNUCCNT.toUpperCase(), "", sOBTEXC.toUpperCase(), sOBDEER.toUpperCase());
		
		FacesMessage msg;
		
		String sMsg = "";
		
		int iSalida = CLComunidades.registraMovimiento(movimiento);
		
		switch (iSalida) 
		{
		case 0: //Sin errores
			sMsg = "El movimiento se ha registrado correctamente.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(sMsg,null);
			break;

		case -1: //error 001 - CODIGO DE ACCION DEBE SER A,M,B,X 
			sMsg = "ERROR:001 - No se ha elegido una acccion correcta. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -3: //error 003 - NO EXISTE EL ACTIVO
			sMsg = "ERROR:003 - El activo elegido no esta registrado en el sistema. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -4: //Error 004 - CIF DE LA COMUNIDAD NO PUEDE SER BLANCO, NULO O CEROS
			sMsg = "ERROR:004 - No se ha informado el numero de documento. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -8: //Error 008 - EL ACTIVO EXISTE EN OTRA COMUNIDAD
			sMsg = "ERROR:008 - El activo ya esta asociado a otra comunidad. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -9: //error 009 - YA EXISTE ESTA COMUNIDAD
			sMsg = "ERROR:009 - La comunidad ya se encuentra registrada. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -10: //error 010 - EL ACTIVO YA EXISTE PARA ESTA COMUNIDAD
			sMsg = "ERROR:010 - El activo ya esta asociado a esta comunidad. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -11: //error 011 - LA COMUNIDAD NO EXISTE. ACTIVO NO SE PUEDE DAR DE ALTA
			sMsg = "ERROR:011 - No se puede dar de alta el activo, la comunidad no existe. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -12: //error 012 - LA COMUNIDAD NO EXISTE. NO SE PUEDE MODIFICAR
			sMsg = "ERROR:012 - No se puede modificar la comunidad, no se encuentra registrada. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -26: //error 026 - LA COMUNIDAD NO EXISTE, NO SE PUEDE DAR DE BAJA
			sMsg = "ERROR:026 - No se puede dar de baja la comunidad, no se encuentra registrada. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -27: //error 027 - NO SE PUEDE DAR DE BAJA LA COMUNIDAD PORQUE TIENE CUOTAS
			sMsg = "ERROR:027 - No se puede dar de baja la comunidad, aun tiene cuotas de alta. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -30: //Error 030 - LA CLASE DE DOCUMENTO DEBE SER UN CIF (2,5,J)
			sMsg = "ERROR:030 - No se ha elegido un tipo de documento. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -801: //Error 801 - alta de una comunidad en alta
			sMsg = "ERROR:801 - La comunidad ya esta dada de alta. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -802: //Error 802 - comunidad de baja no puede recibir mas movimientos
			sMsg = "ERROR:802 - La comunidad esta baja y no puede recibir mas movimientos. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -803: //Error 803 - estado no disponible
			sMsg = "ERROR:803 - El estado de la comunidad informada no esta disponible. Por favor, revise los datos.";
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

		case -901: //Error 901 - error y rollback - error al crear la comuidad
			sMsg = "ERROR:901 - Se ha producido un error al registrar la comunidad. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;
			
		case -902: //Error 902 - error y rollback - error al registrar la relaccion
			sMsg = "ERROR:902 - Se ha producido un error al registrar la relacion. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -903: //Error 903 - error y rollback - error al registrar el activo durante el alta
			sMsg = "ERROR:903 - Se ha producido un error al asociar el activo durante el alta. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -904: //Error 904 - error y rollback - error al cambiar el estado
			sMsg = "ERROR:904 - Se ha producido un error al cambiar el estado de la comunidad. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -905: //Error 905 - error y rollback - error al modificar la comunidad
			sMsg = "ERROR:905 - Se ha producido un error al modificar la comunidad. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -906: //Error 906 - error y rollback - el activo ya esta vinculado
			sMsg = "ERROR:906 - El activo ya ha sido vinculado. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -907: //Error 907 - error y rollback - error al asociar el activo en la comunidad
			sMsg = "ERROR:907 - Se ha producido un error al asociar el activo a la comunidad. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -908: //Error 908 - error y rollback - el activo no esta vinculado
			sMsg = "ERROR:908 - El activo no ha sido vinculado. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -909: //Error 909 - error y rollback - error al desasociar el activo en la comunidad
			sMsg = "ERROR:909 - Se ha producido un error al desasociar el activo a la comunidad. Por favor, revise los datos.";
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

	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
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

	public String getsNOMPRC() {
		return sNOMPRC;
	}

	public void setsNOMPRC(String sNOMPRC) {
		this.sNOMPRC = sNOMPRC;
	}

	public String getsNUTPRC() {
		return sNUTPRC;
	}

	public void setsNUTPRC(String sNUTPRC) {
		this.sNUTPRC = sNUTPRC;
	}

	public String getsNOMADC() {
		return sNOMADC;
	}

	public void setsNOMADC(String sNOMADC) {
		this.sNOMADC = sNOMADC;
	}

	public String getsNUTADC() {
		return sNUTADC;
	}

	public void setsNUTADC(String sNUTADC) {
		this.sNUTADC = sNUTADC;
	}

	public String getsNODCAD() {
		return sNODCAD;
	}

	public void setsNODCAD(String sNODCAD) {
		this.sNODCAD = sNODCAD;
	}

	public String getsNUCCEN() {
		return sNUCCEN;
	}

	public void setsNUCCEN(String sNUCCEN) {
		this.sNUCCEN = sNUCCEN;
	}

	public String getsNUCCOF() {
		return sNUCCOF;
	}

	public void setsNUCCOF(String sNUCCOF) {
		this.sNUCCOF = sNUCCOF;
	}

	public String getsNUCCDI() {
		return sNUCCDI;
	}

	public void setsNUCCDI(String sNUCCDI) {
		this.sNUCCDI = sNUCCDI;
	}

	public String getsNUCCNT() {
		return sNUCCNT;
	}

	public void setsNUCCNT(String sNUCCNT) {
		this.sNUCCNT = sNUCCNT;
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

}
