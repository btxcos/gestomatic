package com.provisiones.pl.errores;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.ll.CLComunidades;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

import com.provisiones.types.ActivoTabla;
import com.provisiones.types.Comunidad;
import com.provisiones.types.ErrorComunidadTabla;
import com.provisiones.types.MovimientoComunidad;

public class GestorErroresComunidad implements Serializable 
{
	private static final long serialVersionUID = 8107483385802371051L;

	private static Logger logger = LoggerFactory.getLogger(GestorErroresComunidad.class.getName());
	
	private String sCODTRN = ValoresDefecto.DEF_E1_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOACCI = "";
	private String sCOENGP = ValoresDefecto.DEF_COENGP;
	private String sCOCLDO = "";
	private boolean bRCOCLDO = true;
	private String sNUDCOM = "";
	private boolean bRNUDCOM = true;
	private String sCOACES = "";
	private boolean bRCOACES = true;
	private String sNOMCOC = "";
	private boolean bRNOMCOC = true;
	private String sNODCCO = "";
	private boolean bRNODCCO = true;
	private String sNOMPRC = "";
	private boolean bRNOMPRC = true;
	private String sNUTPRC = "";
	private boolean bRNUTPRC = true;
	private String sNOMADC = "";
	private boolean bRNOMADC = true;
	private String sNUTADC = "";
	private boolean bRNUTADC = true;
	private String sNODCAD = "";
	private boolean bRNODCAD = true;
	private String sNUCCEN = "";
	private boolean bRNUCCEN = true;
	private String sNUCCOF = "";
	private boolean bRNUCCOF = true;
	private String sNUCCDI = "";
	private boolean bRNUCCDI = true;
	private String sNUCCNT = "";
	private boolean bRNUCCNT = true;
	private String sOBTEXC = "";
	private boolean bROBTEXC = true;
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
	
	private ErrorComunidadTabla errorseleccionado = null;
	
	private ArrayList<ErrorComunidadTabla> tablaerrores = null;
	
	public GestorErroresComunidad()
	{
		Utils.standardIO2File("");//Salida por fichero de texto
	}

    public void borrarPlantillaActivo() 
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
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	borrarPlantillaActivo();
   	
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
		borrarPlantillaActivo();
		borrarCampos();
		this.sCOACES = "";
	}
	
	public void encuentraErroresComunidad()
	{
		FacesMessage msg;

		logger.debug("Buscando Activos...");
		
		this.setTablaerrores(CLComunidades.buscarErroresComunidades());
		
		msg = Utils.pfmsgInfo("Encontrados "+getTablaerrores().size()+" errores relacionados.");
		logger.debug("Encontrados {} errores relacionados.",getTablaerrores().size());
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void seleccionarActivo(ActionEvent actionEvent) 
    {  
		FacesMessage msg;
    	
    	this.sCOACES  = activoseleccionado.getCOACES();
    	
    	msg = Utils.pfmsgInfo("Activo "+ sCOACES +" Seleccionado.");
    	logger.debug("Activo seleccionado:|{}|",sCOACES);
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void buscaActivos (ActionEvent actionEvent)
	{
		FacesMessage msg;
		
		ActivoTabla buscaactivos = new ActivoTabla(
				sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
				sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
				sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(), "");
		
		logger.debug("Buscando Activos...");
		
		this.setTablaactivos(CLComunidades.buscaActivosConComunidad(buscaactivos));

		msg = Utils.pfmsgInfo("Encontrados "+getTablaactivos().size()+" activos relacionados.");
		logger.debug("Encontrados {} activos relacionados.",getTablaactivos().size());
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	public void comprobarActivo(ActionEvent actionEvent)
	{
		FacesMessage msg;
		
		String sMsg = "";
		
		int iSalida = CLComunidades.comprobarActivo(sCOACES.toUpperCase());

		switch (iSalida) 
		{
			case 0: //Sin errores
				sMsg = "El activo '"+sCOACES.toUpperCase()+"' esta disponible.";
				logger.debug(sMsg);
				msg = new FacesMessage(sMsg,null);
				break;

			case -1: //error - ya vinculado
				sMsg = "ERROR: El activo '"+sCOACES.toUpperCase()+"' ya esta vinculado a otra comunidada. Por favor, revise los datos."; 
				logger.debug(sMsg);
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,sMsg,null);
				break;

			case -2: //error - no existe
				sMsg = "ERROR: El activo '"+sCOACES.toUpperCase()+"' no se encuentra registrado en el sistema. Por favor, revise los datos.";
				logger.debug(sMsg);
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,sMsg,null);
				break;

			default: //error generico
				sMsg = "ERROR: El activo '"+sCOACES.toUpperCase()+"' ha producido un error desconocido. Por favor, revise los datos.";
				logger.debug(sMsg);
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,sMsg,null);
				break;
		}

		
		FacesContext.getCurrentInstance().addMessage(null, msg);	
		
	}
	
	public void buscarComunidad(ActionEvent actionEvent)
	{
		FacesMessage msg;
		
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
			msg = Utils.pfmsgError("ERROR: El Activo '"+sCOACES.toUpperCase()+"' no esta asociado a ninguna comunidad.");
			logger.error("ERROR: El Activo '{}' no esta asociado a ninguna comunidad.",sCOACES.toUpperCase());
		}
		else
		{
			msg = Utils.pfmsgInfo("La comunidad se ha cargado correctamente.");
			logger.info("La comunidad se ha cargado correctamente.");
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	

	public void cargarComunidad(ActionEvent actionEvent)
	{
		FacesMessage msg;
		
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
			msg = Utils.pfmsgError("Error: La comunidad '"+sNUDCOM.toUpperCase()+"' no esta registrada en el sistema.");
			logger.error("Error: La comunidad '{}' no esta registrada en el sistema.",sNUDCOM.toUpperCase());
		}
		else
		{
			msg = Utils.pfmsgInfo("La comunidad '"+sNUDCOM.toUpperCase()+"' se ha cargado correctamente.");
			logger.info("La comunidad '{}' se ha cargado correctamente.",sNUDCOM.toUpperCase());			
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
				
	}
	
	public void registraDatos(ActionEvent actionEvent)
	{
		MovimientoComunidad movimiento = new MovimientoComunidad (sCODTRN.toUpperCase(), sCOTDOR.toUpperCase(), sIDPROV.toUpperCase(), sCOACCI.toUpperCase(), sCOENGP.toUpperCase(), sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase(), "", sCOACES.toUpperCase(), "", sNOMCOC.toUpperCase(), "", sNODCCO.toUpperCase(), "", sNOMPRC.toUpperCase(), "", sNUTPRC.toUpperCase(), "", sNOMADC.toUpperCase(), "", sNUTADC.toUpperCase(), "", sNODCAD.toUpperCase(), "", sNUCCEN.toUpperCase(), sNUCCOF.toUpperCase(), sNUCCDI.toUpperCase(), sNUCCNT.toUpperCase(), "", sOBTEXC.toUpperCase(), sOBDEER.toUpperCase());
		
		FacesMessage msg;
		
		String sMsg = "";
		
		int iSalida = CLComunidades.registraMovimiento(movimiento);
		
		switch (iSalida) 
		{
		case 0: //Sin errores
			
			sMsg = "La comunidad se ha creado correctamente.";
			msg = Utils.pfmsgInfo(sMsg);
			logger.info(sMsg);
			break;

		case -1: //error 001 - CODIGO DE ACCION DEBE SER A,M,B,X 
			sMsg = "ERROR:001 - No se ha elegido una acccion correcta. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;

		case -3: //error 003 - NO EXISTE EL ACTIVO
			sMsg = "ERROR:003 - El activo elegido no esta registrado en el sistema. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;


		case -4: //Error 004 - CIF DE LA COMUNIDAD NO PUEDE SER BLANCO, NULO O CEROS
			sMsg = "ERROR:004 - No se ha informado el numero de documento. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;

		case -5: //Error 005 - NO TIENE NOMBRE LA COMUNIDAD
			sMsg = "ERROR:005 - El nombre de la comunidad es obligatorio. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;

		case -6: //Error 006 - FALTAN DATOS DE LA CUENTA BANCARIA
			sMsg = "ERROR:006 - No se han informado los datos de la cuenta corriente. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;

		case -8: //Error 008 - EL ACTIVO EXISTE EN OTRA COMUNIDAD
			sMsg = "ERROR:008 - El activo ya esta asociado a otra comunidad. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;


		case -9: //error 009 - YA EXISTE ESTA COMUNIDAD
			sMsg = "ERROR:009 - La comunidad ya se encuentra registrada. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;


		case -10: //error 010 - EL ACTIVO YA EXISTE PARA ESTA COMUNIDAD
			sMsg = "ERROR:010 - El activo ya esta asociado a esta comunidad. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;


		case -11: //error 011 - LA COMUNIDAD NO EXISTE. ACTIVO NO SE PUEDE DAR DE ALTA
			sMsg = "ERROR:011 - No se puede dar de alta el activo, la comunidad no existe. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;


		case -12: //error 012 - LA COMUNIDAD NO EXISTE. NO SE PUEDE MODIFICAR
			sMsg = "ERROR:012 - No se puede modificar la comunidad, no se encuentra registrada. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;

		case -22: //error 022 - NO SE PUEDE DAR ALTA SI CONTROL DE ACTIVO NO ES S
			sMsg = "ERROR:022 - Para dar de alta la comunidad es necesario incluir un activo. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;
			
		case -26: //error 026 - LA COMUNIDAD NO EXISTE, NO SE PUEDE DAR DE BAJA
			sMsg = "ERROR:026 - No se puede dar de baja la comunidad, no se encuentra registrada. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;

		case -27: //error 027 - NO SE PUEDE DAR DE BAJA LA COMUNIDAD PORQUE TIENE CUOTAS
			sMsg = "ERROR:027 - No se puede dar de baja la comunidad, aun tiene cuotas de alta. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;

		case -30: //Error 030 - LA CLASE DE DOCUMENTO DEBE SER UN CIF (2,5,J)
			sMsg = "ERROR:030 - No se ha elegido un tipo de documento. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;
			
		case -31: //Error 031 - NUMERO DE DOCUMENTO CIF ERRONEO
			sMsg = "ERROR:031 - El numero de documento es incorrecto. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;
			
		case -701: //Error 701 - datos de cuenta incorrectos
			sMsg = "ERROR:701 - Los datos de la cuenta corriente son incorrectos. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;
			

		case -702: //Error 702 - direccion de correo de comunidad incorrecta
			sMsg = "ERROR:702 - La direccion de correo de la comunidad es incorrecta. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;
			

		case -703: //Error 703 - direccion de correo del administrador incorrecta
			sMsg = "ERROR:703 - La direccion de correo del adminsitrador es incorrecta. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;

		case -801: //Error 801 - alta de una comunidad en alta
			sMsg = "ERROR:801 - La comunidad ya esta dada de alta. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;

		case -802: //Error 802 - comunidad de baja no puede recibir mas movimientos
			sMsg = "ERROR:802 - La comunidad esta de baja y no puede recibir mas movimientos. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;
			
		case -803: //Error 803 - estado no disponible
			sMsg = "ERROR:803 - El estado de la comunidad informada no esta disponible. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;

		case -804: //Error 804 - modificacion sin cambios
			sMsg = "ERROR:804 - No hay modificaciones que realizar. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;

		case -900: //Error 900 - al crear un movimiento
			sMsg = "[FATAL] ERROR:900 - Se ha producido un error al registrar el movimiento. Por favor, revise los datos y avise a soporte.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;

		case -901: //Error 901 - error y rollback - error al crear la comuidad
			sMsg = "[FATAL] ERROR:901 - Se ha producido un error al registrar la comunidad. Por favor, revise los datos y avise a soporte.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;
			
		case -902: //Error 902 - error y rollback - error al registrar la relaccion
			sMsg = "[FATAL] ERROR:902 - Se ha producido un error al registrar la relacion. Por favor, revise los datos y avise a soporte.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;

		case -903: //Error 903 - error y rollback - error al registrar el activo durante el alta
			sMsg = "[FATAL] ERROR:903 - Se ha producido un error al asociar el activo durante el alta. Por favor, revise los datos y avise a soporte.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;

		case -904: //Error 904 - error y rollback - error al cambiar el estado
			sMsg = "[FATAL] ERROR:904 - Se ha producido un error al cambiar el estado de la comunidad. Por favor, revise los datos y avise a soporte.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;

		case -905: //Error 905 - error y rollback - error al modificar la comunidad
			sMsg = "[FATAL] ERROR:905 - Se ha producido un error al modificar la comunidad. Por favor, revise los datos y avise a soporte.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;

		case -906: //Error 906 - error y rollback - el activo ya esta vinculado
			sMsg = "[FATAL] ERROR:906 - El activo ya ha sido vinculado. Por favor, revise los datos y avise a soporte.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;

		case -907: //Error 907 - error y rollback - error al asociar el activo en la comunidad
			sMsg = "[FATAL] ERROR:907 - Se ha producido un error al asociar el activo a la comunidad. Por favor, revise los datos y avise a soporte.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;

		case -908: //Error 908 - error y rollback - el activo no esta vinculado
			sMsg = "[FATAL] ERROR:908 - El activo no ha sido vinculado. Por favor, revise los datos y avise a soporte.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;

		case -909: //Error 909 - error y rollback - error al desasociar el activo en la comunidad
			sMsg = "[FATAL] ERROR:909 - Se ha producido un error al desasociar el activo a la comunidad. Por favor, revise los datos y avise a soporte.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
			break;
			
		default: //error generico
			msg = Utils.pfmsgError("[FATAL] ERROR:"+iSalida+" - La operación solicitada ha producido un error desconocido. Por favor, revise los datos y avise a soporte.");
			logger.error("[FATAL] ERROR:{} - La operación solicitada ha producido un error desconocido. Por favor, revise los datos y avise a soporte.",iSalida);
			break;
		}
		
		
		logger.debug("Finalizadas las comprobaciones.");
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

	public boolean isbRCOCLDO() {
		return bRCOCLDO;
	}

	public void setbRCOCLDO(boolean bRCOCLDO) {
		this.bRCOCLDO = bRCOCLDO;
	}

	public boolean isbRNUDCOM() {
		return bRNUDCOM;
	}

	public void setbRNUDCOM(boolean bRNUDCOM) {
		this.bRNUDCOM = bRNUDCOM;
	}

	public boolean isbRCOACES() {
		return bRCOACES;
	}

	public void setbRCOACES(boolean bRCOACES) {
		this.bRCOACES = bRCOACES;
	}

	public boolean isbRNOMCOC() {
		return bRNOMCOC;
	}

	public void setbRNOMCOC(boolean bRNOMCOC) {
		this.bRNOMCOC = bRNOMCOC;
	}

	public boolean isbRNODCCO() {
		return bRNODCCO;
	}

	public void setbRNODCCO(boolean bRNODCCO) {
		this.bRNODCCO = bRNODCCO;
	}

	public boolean isbRNOMPRC() {
		return bRNOMPRC;
	}

	public void setbRNOMPRC(boolean bRNOMPRC) {
		this.bRNOMPRC = bRNOMPRC;
	}

	public boolean isbRNUTPRC() {
		return bRNUTPRC;
	}

	public void setbRNUTPRC(boolean bRNUTPRC) {
		this.bRNUTPRC = bRNUTPRC;
	}

	public boolean isbRNOMADC() {
		return bRNOMADC;
	}

	public void setbRNOMADC(boolean bRNOMADC) {
		this.bRNOMADC = bRNOMADC;
	}

	public boolean isbRNUTADC() {
		return bRNUTADC;
	}

	public void setbRNUTADC(boolean bRNUTADC) {
		this.bRNUTADC = bRNUTADC;
	}

	public boolean isbRNODCAD() {
		return bRNODCAD;
	}

	public void setbRNODCAD(boolean bRNODCAD) {
		this.bRNODCAD = bRNODCAD;
	}

	public boolean isbRNUCCEN() {
		return bRNUCCEN;
	}

	public void setbRNUCCEN(boolean bRNUCCEN) {
		this.bRNUCCEN = bRNUCCEN;
	}

	public boolean isbRNUCCOF() {
		return bRNUCCOF;
	}

	public void setbRNUCCOF(boolean bRNUCCOF) {
		this.bRNUCCOF = bRNUCCOF;
	}

	public boolean isbRNUCCDI() {
		return bRNUCCDI;
	}

	public void setbRNUCCDI(boolean bRNUCCDI) {
		this.bRNUCCDI = bRNUCCDI;
	}

	public boolean isbRNUCCNT() {
		return bRNUCCNT;
	}

	public void setbRNUCCNT(boolean bRNUCCNT) {
		this.bRNUCCNT = bRNUCCNT;
	}

	public boolean isbROBTEXC() {
		return bROBTEXC;
	}

	public void setbROBTEXC(boolean bROBTEXC) {
		this.bROBTEXC = bROBTEXC;
	}

	public ErrorComunidadTabla getErrorseleccionado() {
		return errorseleccionado;
	}

	public void setErrorseleccionado(ErrorComunidadTabla errorseleccionado) {
		this.errorseleccionado = errorseleccionado;
	}

	public ArrayList<ErrorComunidadTabla> getTablaerrores() {
		return tablaerrores;
	}

	public void setTablaerrores(ArrayList<ErrorComunidadTabla> tablaerrores) {
		this.tablaerrores = tablaerrores;
	}
		
}
