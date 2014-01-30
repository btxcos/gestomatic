package com.provisiones.pl.errores;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLComunidades;
import com.provisiones.ll.CLCuentas;
import com.provisiones.ll.CLErrores;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

import com.provisiones.types.Comunidad;
import com.provisiones.types.Cuenta;
import com.provisiones.types.errores.ErrorComunidadTabla;
import com.provisiones.types.errores.ErrorTabla;
import com.provisiones.types.movimientos.MovimientoComunidad;
import com.provisiones.types.tablas.ActivoTabla;

public class GestorErroresComunidades implements Serializable 
{
	private static final long serialVersionUID = 8107483385802371051L;

	private static Logger logger = LoggerFactory.getLogger(GestorErroresComunidades.class.getName());
	
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
	
	//Buscar errores
	private String sCodMovimiento ="";
	private String sCodError = "";
	
	private String sCOCLDOB = "";
	private String sNUDCOMB = "";
	private String sNOMCOCB = "";
	private String sCOACESB = "";
	
	
	private transient ErrorComunidadTabla movimientoseleccionado = null;
	private transient ArrayList<ErrorComunidadTabla> tablacomunidadeserror = null;
	

	private transient ErrorTabla errorseleccionado = null;
	private transient ArrayList<ErrorTabla> tablaerrores = null;
	
	
	private transient ActivoTabla activoseleccionado = null;
	
	private transient ArrayList<ActivoTabla> tablaactivos = null;
	

	
	public GestorErroresComunidades()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorErroresComunidades...");	
		}
	}

    public void borrarPlantillaError() 
    {  
    	this.sCOACESB = "";

    	this.sNUDCOMB = "";
    	this.sCOCLDOB = "";
    	this.sNOMCOCB = "";

    	
    	this.movimientoseleccionado = null;
    	this.tablacomunidadeserror = null;
    	
    	this.errorseleccionado = null;
    	this.tablaerrores = null;
    	
    	this.sCodMovimiento ="";
    	this.sCodError = "";
   	
    }
	
    public void limpiarPlantillaError(ActionEvent actionEvent) 
    {  
    	borrarPlantillaError();
   	
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
		borrarPlantillaError();
		borrarPlantillaActivo();
		borrarCampos();

		this.sCOACES = "";
	}
	
    public boolean editarError(int iCodError) 
    {  
    	boolean bSalida = false;
  	
		switch (iCodError) 
		{
		/*case 2://prueba
			this.bRCOACES = true;
			this.bRCOCLDO = true;
			this.bRNODCAD = false;
			this.bRNODCCO = true;
			this.bRNOMADC = true;
			this.bRNOMCOC = true;
			this.bRNOMPRC = true;
			this.bRNUCCDI = true;
			this.bRNUCCEN = true;
			this.bRNUCCNT = true;
			this.bRNUCCOF = true;
			this.bRNUDCOM = true;
			this.bRNUTADC = true;
			this.bRNUTPRC = true;
			this.bROBTEXC = true;
			bSalida = true;
			break;*/
		default://error no recuperable
			this.bRCOACES = true;
			this.bRCOCLDO = true;
			this.bRNODCAD = true;
			this.bRNODCCO = true;
			this.bRNOMADC = true;
			this.bRNOMCOC = true;
			this.bRNOMPRC = true;
			this.bRNUCCDI = true;
			this.bRNUCCEN = true;
			this.bRNUCCNT = true;
			this.bRNUCCOF = true;
			this.bRNUDCOM = true;
			this.bRNUTADC = true;
			this.bRNUTPRC = true;
			this.bROBTEXC = true;
			bSalida = false;
			break;
		}
		
		return bSalida;
   	
    }
	
	public void buscaComunidadesError(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			logger.debug("Buscando Comunidades con errores...");
			
			if (sCOACESB.equals(""))
			{
				ErrorComunidadTabla filtro = new ErrorComunidadTabla(
						sCOCLDOB.toUpperCase(), "", sNUDCOMB.toUpperCase(), sNOMCOCB.toUpperCase(),
						"", "");

				this.setTablacomunidadeserror(CLErrores.buscarComunidadesConErrores(filtro));

			}
			else
			{
				this.setTablacomunidadeserror(CLErrores.buscarComunidadesActivoConErrores(sCOACESB));
			}
			
			
			msg = Utils.pfmsgInfo("Encontradas "+getTablacomunidadeserror().size()+" Comunidades relacionadas.");
			logger.debug("Encontradas {} comunidades relacionadas.",getTablacomunidadeserror().size());
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void seleccionarMovimiento(ActionEvent actionEvent) 
    {
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			this.sCodMovimiento = movimientoseleccionado.getMOVIMIENTO(); 
	    	
			this.setTablaerrores(CLErrores.buscarErroresComunidad(sCodMovimiento));
			
			msg = Utils.pfmsgInfo("Encontrados "+getTablaerrores().size()+" errores relacionados.");
			logger.debug("Encontrados {} errores relacionados.",getTablaerrores().size());
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
			MovimientoComunidad movimiento = CLComunidades.buscarMovimientoComunidad(sCodMovimiento);
			
			this.sCOACCI = movimiento.getCOACCI();
			this.sCOACES = movimiento.getCOACES();
			this.sCOCLDO = movimiento.getCOCLDO();
			this.sNUDCOM = movimiento.getNUDCOM();
			this.sNOMCOC = movimiento.getNOMCOC();
			this.sNODCCO = movimiento.getNODCCO();
			this.sNOMPRC = movimiento.getNOMPRC();
			this.sNUTPRC = movimiento.getNUTPRC();
			this.sNOMADC = movimiento.getNOMADC();
			this.sNUTADC = movimiento.getNUTADC();
			this.sNODCAD = movimiento.getNODCAD();
			this.sNUCCEN = movimiento.getNUCCEN();
			this.sNUCCOF = movimiento.getNUCCOF();
			this.sNUCCDI = movimiento.getNUCCDI();
			this.sNUCCNT = movimiento.getNUCCNT();
			this.sOBTEXC = movimiento.getOBTEXC();
					
	        	
	    	msg = Utils.pfmsgInfo("Errores de comunidad cargados.");
	    	logger.debug("Errores de comunidad cargados.");
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void seleccionarError(ActionEvent actionEvent) 
    {
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
	    	
			this.sCodError = errorseleccionado.getsCodError(); 
			
	    	int iCodError  = Integer.parseInt(sCodError);
	    	
	    	
	    	logger.debug("Error seleccionado:|{}|",iCodError);
	    	
	    	String sMsg ="";
	    	
	    	if (editarError(iCodError))
	    	{
	    		sMsg = "Error editado.";
	    		msg = Utils.pfmsgInfo(sMsg);
	    		logger.info(sMsg);
	    	}
	    	else
	    	{
	    		sMsg = "[FATAL] ERROR: El error seleccionado no es recuperable. Por favor, pongase en contacto con soporte.";
	    		msg = Utils.pfmsgFatal(sMsg);
	    		logger.error(sMsg);
	    	}

	    	FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	

	
	public void seleccionarActivo(ActionEvent actionEvent) 
    {
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
	    	
	    	this.sCOACES  = activoseleccionado.getCOACES();
	    	
	    	msg = Utils.pfmsgInfo("Activo "+ sCOACES +" Seleccionado.");
	    	logger.debug("Activo seleccionado:|{}|",sCOACES);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void buscaActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			ActivoTabla buscaactivos = new ActivoTabla(
					sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
					sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
					sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(), "");
			
			logger.debug("Buscando Activos...");
			
			this.setTablaactivos(CLComunidades.buscarActivosConComunidad(buscaactivos));

			msg = Utils.pfmsgInfo("Encontrados "+getTablaactivos().size()+" activos relacionados.");
			logger.debug("Encontrados {} activos relacionados.",getTablaactivos().size());
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void comprobarActivo(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
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
	}
	
	public void buscarComunidad(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			borrarCampos();

			Comunidad comunidad = CLComunidades.buscarComunidad(sCOACES.toUpperCase());
			
			Cuenta cuenta = CLCuentas.buscarCuenta(Long.parseLong(comunidad.getsCuenta()));
			
			this.sCOCLDO = comunidad.getsCOCLDO();
			this.sNUDCOM = comunidad.getsNUDCOM();
			this.sNOMCOC = comunidad.getsNOMCOC();
			this.sNODCCO = comunidad.getsNODCCO();
			this.sNOMPRC = comunidad.getsNOMPRC();
			this.sNUTPRC = comunidad.getsNUTPRC();
			this.sNOMADC = comunidad.getsNOMADC();
			this.sNUTADC = comunidad.getsNUTADC();
			this.sNODCAD = comunidad.getsNODCAD();
			this.sNUCCEN = cuenta.getsNUCCEN();
			this.sNUCCOF = cuenta.getsNUCCOF();
			this.sNUCCDI = cuenta.getsNUCCDI();
			this.sNUCCNT = cuenta.getsNUCCNT();
			this.sOBTEXC = comunidad.getsOBTEXC();
			
			if (comunidad.getsNUDCOM().equals(""))
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
	}
	

	public void cargarComunidad(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			Comunidad comunidad = CLComunidades.consultarComunidad(sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase());
			
			Cuenta cuenta = CLCuentas.buscarCuenta(Long.parseLong(comunidad.getsCuenta()));
			
			this.sCOCLDO = comunidad.getsCOCLDO();
			this.sNUDCOM = comunidad.getsNUDCOM();
			this.sNOMCOC = comunidad.getsNOMCOC();
			this.sNODCCO = comunidad.getsNODCCO();
			this.sNOMPRC = comunidad.getsNOMPRC();
			this.sNUTPRC = comunidad.getsNUTPRC();
			this.sNOMADC = comunidad.getsNOMADC();
			this.sNUTADC = comunidad.getsNUTADC();
			this.sNODCAD = comunidad.getsNODCAD();
			this.sNUCCEN = cuenta.getsNUCCEN();
			this.sNUCCOF = cuenta.getsNUCCOF();
			this.sNUCCDI = cuenta.getsNUCCDI();
			this.sNUCCNT = cuenta.getsNUCCNT();
			this.sOBTEXC = comunidad.getsOBTEXC();

			if (comunidad.getsNUDCOM().equals(""))
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
	}
	
	public void registraDatos(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			if (!CLComunidades.existeMovimientoComunidad(sCodMovimiento))
			{
				sMsg = "[FATAL] ERROR:911 - No se puede modificar la comunidad, no existe el movimiento. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
			}
			else
			{
				MovimientoComunidad nuevomovimiento = new MovimientoComunidad (sCODTRN.toUpperCase(), sCOTDOR.toUpperCase(), sIDPROV.toUpperCase(), sCOACCI.toUpperCase(), sCOENGP.toUpperCase(), sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase(), "", sCOACES.toUpperCase(), "", sNOMCOC.toUpperCase(), "", sNODCCO.toUpperCase(), "", sNOMPRC.toUpperCase(), "", sNUTPRC.toUpperCase(), "", sNOMADC.toUpperCase(), "", sNUTADC.toUpperCase(), "", sNODCAD.toUpperCase(), "", sNUCCEN.toUpperCase(), sNUCCOF.toUpperCase(), sNUCCDI.toUpperCase(), sNUCCNT.toUpperCase(), "", sOBTEXC.toUpperCase(), sOBDEER.toUpperCase());
				
				int iSalida = CLErrores.reparaMovimientoComunidad(nuevomovimiento,sCodMovimiento,sCodError);
				
				switch (iSalida) 
				{
				case 0: //Sin errores
					tablaerrores.remove(errorseleccionado);
					sMsg = "El movimiento se ha modificado correctamente.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
					break;

				case -804: //Error 804 - modificacion sin cambios
					sMsg = "ERROR:804 - No hay modificaciones que realizar. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -900: //Error 900 - al crear un movimiento
					sMsg = "[FATAL] ERROR:900 - Se ha producido un error al registrar el movimiento. Por favor, revise los datos y avise a soporte.";
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					break;

				case -905: //Error 905 - error y rollback - error al modificar la comunidad
					sMsg = "[FATAL] ERROR:905 - Se ha producido un error al modificar la comunidad. Por favor, revise los datos y avise a soporte.";
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					break;
					
				case -910: //Error 910 - error y rollback - error al eliminar el error
					sMsg = "[FATAL] ERROR:909 - Se ha producido un error al eliminar el movimiento erroneo. Por favor, revise los datos y avise a soporte.";
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					break;

				default: //error generico
					msg = Utils.pfmsgFatal("[FATAL] ERROR:"+iSalida+" - La operacion solicitada ha producido un error inesperado. Por favor, revise los datos y avise a soporte.");
					logger.error("[FATAL] ERROR:{} - La operacion solicitada ha producido un error inesperado. Por favor, revise los datos y avise a soporte.",iSalida);
					break;
				}
			}
			
			logger.debug("Finalizadas las comprobaciones.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
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

	public boolean isbRCOCLDO() {
		return bRCOCLDO;
	}

	public void setbRCOCLDO(boolean bRCOCLDO) {
		this.bRCOCLDO = bRCOCLDO;
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

	public boolean isbRCOACES() {
		return bRCOACES;
	}

	public void setbRCOACES(boolean bRCOACES) {
		this.bRCOACES = bRCOACES;
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

	public boolean isbRNODCAD() {
		return bRNODCAD;
	}

	public void setbRNODCAD(boolean bRNODCAD) {
		this.bRNODCAD = bRNODCAD;
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

	public String getsCOCLDOB() {
		return sCOCLDOB;
	}

	public void setsCOCLDOB(String sCOCLDOB) {
		this.sCOCLDOB = sCOCLDOB;
	}

	public String getsNUDCOMB() {
		return sNUDCOMB;
	}

	public void setsNUDCOMB(String sNUDCOMB) {
		this.sNUDCOMB = sNUDCOMB;
	}

	public String getsNOMCOCB() {
		return sNOMCOCB;
	}

	public void setsNOMCOCB(String sNOMCOCB) {
		this.sNOMCOCB = sNOMCOCB;
	}

	public String getsCOACESB() {
		return sCOACESB;
	}

	public void setsCOACESB(String sCOACESB) {
		this.sCOACESB = sCOACESB;
	}

	public boolean isbRNUDCOM() {
		return bRNUDCOM;
	}

	public void setbRNUDCOM(boolean bRNUDCOM) {
		this.bRNUDCOM = bRNUDCOM;
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

	public ErrorComunidadTabla getMovimientoseleccionado() {
		return movimientoseleccionado;
	}

	public void setMovimientoseleccionado(ErrorComunidadTabla movimientoseleccionado) {
		this.movimientoseleccionado = movimientoseleccionado;
	}

	public ArrayList<ErrorComunidadTabla> getTablacomunidadeserror() {
		return tablacomunidadeserror;
	}

	public void setTablacomunidadeserror(
			ArrayList<ErrorComunidadTabla> tablacomunidadeserror) {
		this.tablacomunidadeserror = tablacomunidadeserror;
	}

	public ErrorTabla getErrorseleccionado() {
		return errorseleccionado;
	}

	public void setErrorseleccionado(ErrorTabla errorseleccionado) {
		this.errorseleccionado = errorseleccionado;
	}

	public ArrayList<ErrorTabla> getTablaerrores() {
		return tablaerrores;
	}

	public void setTablaerrores(ArrayList<ErrorTabla> tablaerrores) {
		this.tablaerrores = tablaerrores;
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
