package com.provisiones.pl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLActivos;
import com.provisiones.ll.CLComunidades;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Comunidad;
import com.provisiones.types.Nota;
import com.provisiones.types.movimientos.MovimientoComunidad;
import com.provisiones.types.tablas.ActivoTabla;

public class GestorActivosComunidad implements Serializable 
{

	private static final long serialVersionUID = 1699274003745770381L;

	private static Logger logger = LoggerFactory.getLogger(GestorActivosComunidad.class.getName());
	
	private String sCOACES = "";
	private String sCOPOIN = "";
	private String sNOMUIN = "";
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	private String sNUFIRE = "";
	
	private String sCOCLDO = "";
	private String sNUDCOM = "";
	private String sNOMCOC = "";
	private String sNODCCO = "";
	
	private Map<String,String> tiposcocldoHM = new LinkedHashMap<String, String>();
	
	public GestorActivosComunidad()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorActivosComunidad...");
			
			tiposcocldoHM.put("C.I.F.",                     "2");
			tiposcocldoHM.put("C.I.F país extranjero.",     "5");
			tiposcocldoHM.put("Otros persona jurídica.",    "J");
		}
	}
	
	private transient ActivoTabla activoseleccionadoalta = null;
	
	private transient ActivoTabla activoseleccionadobaja = null;
	
	private transient ArrayList<ActivoTabla> tablaactivos = null;

	private transient ArrayList<ActivoTabla> tablaactivoscomunidad = null;

	public void borrarCamposActivo()
	{
    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
    	this.sNUFIRE = "";
		
	}
	
	public void borrarResultadosActivo()
	{
    	this.activoseleccionadoalta = null;
    	this.activoseleccionadobaja = null;
    	this.tablaactivos = null;
    	this.tablaactivoscomunidad = null;
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";

    	borrarCamposActivo();
    	
    	borrarResultadosActivo();
    }
    
	public void borrarCamposComunidad()
	{
    	this.sCOCLDO = "";
    	this.sNUDCOM = "";
    	this.sNOMCOC = "";
    	this.sNODCCO = "";

    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
    	this.sNUFIRE = "";
	}
	
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
		this.sCOACES = "";

		borrarResultadosActivo();
		borrarCamposComunidad();
    }
	
	public void buscaActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.activoseleccionadoalta = null;
			
			this.setTablaactivos(null);
			
			ActivoTabla buscaactivos = new ActivoTabla(
					"", 
					sCOPOIN.toUpperCase(), 
					sNOMUIN.toUpperCase(),
					sNOPRAC.toUpperCase(), 
					sNOVIAS.toUpperCase(), 
					sNUPIAC.toUpperCase(), 
					sNUPOAC.toUpperCase(), 
					sNUPUAC.toUpperCase(), 
					sNUFIRE.toUpperCase(),
					"");

			this.setTablaactivos(CLComunidades.buscarActivosSinComunidad(buscaactivos));
			
			if (getTablaactivos().size() == 0)
			{
				sMsg = "No se encontraron Activos con los criterios solicitados.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
			}
			else if (getTablaactivos().size() == 1)
			{
				sMsg = "Encontrado un Activo relacionado.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}
			else
			{
				sMsg = "Encontrados "+getTablaactivos().size()+" Activos relacionados.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void seleccionarActivo(ActionEvent actionEvent) 
    {
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;

	    	this.sCOACES  = activoseleccionadoalta.getCOACES();
	    	
	    	String sMsg = "Activo '"+ sCOACES +"' Seleccionado.";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	logger.info(sMsg);
	    	
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void cargarComunidad(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			if (sCOCLDO.isEmpty() || sNUDCOM.isEmpty())
			{
				sMsg = "ERROR: Los campos 'Documento' y 'Número' deben de ser informados para realizar la búsqueda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else if (!CLComunidades.existeComunidad(sCOCLDO, sNUDCOM.toUpperCase()))
			{
				sMsg = "La Comunidad informada no está dada de alta. Por favor, revise los datos.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
			}
			else
			{
				Comunidad comunidad = CLComunidades.consultarComunidad(sCOCLDO, sNUDCOM.toUpperCase());
				
				this.sCOCLDO = comunidad.getsCOCLDO();
				this.sNUDCOM = comunidad.getsNUDCOM();
				this.sNOMCOC = comunidad.getsNOMCOC();
				this.sNODCCO = comunidad.getsNODCCO();
				
				this.setTablaactivoscomunidad(CLComunidades.buscarActivosComunidad(sCOCLDO, sNUDCOM.toUpperCase()));
				
				sMsg = "La Comunidad '"+sNUDCOM.toUpperCase()+"' se ha cargado correctamente.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
				
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
				sMsg = "Encontrados "+getTablaactivoscomunidad().size()+" Activos relacionados.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}				
	}

	public FacesMessage nuevoMovimiento(String sCodCOACCI)
	{
		FacesMessage msg;
		
		String sMsg = "";
		
		if (!CLComunidades.existeComunidad(sCOCLDO, sNUDCOM.toUpperCase()))
		{
			sMsg = "ERROR:011 - No se puede operar sobre el activo, la comunidad no existe. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
		}
		else
		{
	    	MovimientoComunidad movimiento = new MovimientoComunidad (
	    			ValoresDefecto.DEF_E1_CODTRN, 
	    			ValoresDefecto.DEF_COTDOR, 
	    			ValoresDefecto.DEF_IDPROV, 
	    			sCodCOACCI, 
	    			ValoresDefecto.DEF_COENGP, 
	    			sCOCLDO.toUpperCase(), 
	    			sNUDCOM.toUpperCase(), 
	    			"S", 
	    			sCOACES, 
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
	    	
	    	Nota nota = new Nota (true,"");
			
			int iSalida = CLComunidades.registraMovimiento(movimiento,nota);
			
			switch (iSalida) 
			{
			case 0: //Sin errores
				if (sCodCOACCI.equals("X"))
				{
					//TODO ERROR añadir null
					tablaactivoscomunidad.addAll(CLActivos.buscarActivoUnico(Integer.parseInt(sCOACES)));
				}
				else if (sCodCOACCI.equals("E"))
				{
					tablaactivoscomunidad.remove(activoseleccionadobaja);
				}

				sMsg = "Operación realizada correctamente.";
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
				sMsg = "ERROR:004 - No se ha informado el número de documento. Por favor, revise los datos.";
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

			/*case -11: //error 011 - LA COMUNIDAD NO EXISTE. ACTIVO NO SE PUEDE DAR DE ALTA
				sMsg = "ERROR:011 - No se puede dar de alta el activo, la comunidad no existe. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
				break;*/


			case -12: //error 012 - LA COMUNIDAD NO EXISTE. NO SE PUEDE MODIFICAR
				sMsg = "ERROR:012 - No se puede modificar la comunidad, no se encuentra registrada. Por favor, revise los datos.";
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

			case -34: //error 034 - NO SE PUEDE DAR DE BAJA EL ACTIVO PORQUE TIENE CUOTAS
				sMsg = "ERROR:034 - No se puede dar de baja el Activo, aun tiene cuotas de alta. Por favor, revise los datos.";
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
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;

			case -901: //Error 901 - error y rollback - error al crear la comuidad
				sMsg = "[FATAL] ERROR:901 - Se ha producido un error al registrar la comunidad. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;
				
			case -902: //Error 902 - error y rollback - error al registrar la relaccion
				sMsg = "[FATAL] ERROR:902 - Se ha producido un error al registrar la relacion. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;

			case -903: //Error 903 - error y rollback - error al registrar el activo durante el alta
				sMsg = "[FATAL] ERROR:903 - Se ha producido un error al asociar el activo durante el alta. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;

			case -904: //Error 904 - error y rollback - error al cambiar el estado
				sMsg = "[FATAL] ERROR:904 - Se ha producido un error al cambiar el estado de la comunidad. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;

			case -905: //Error 905 - error y rollback - error al modificar la comunidad
				sMsg = "[FATAL] ERROR:905 - Se ha producido un error al modificar la comunidad. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;

			case -906: //Error 906 - error y rollback - el activo ya esta vinculado
				sMsg = "[FATAL] ERROR:906 - El activo ya ha sido vinculado. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;

			case -907: //Error 907 - error y rollback - error al asociar el activo en la comunidad
				sMsg = "[FATAL] ERROR:907 - Se ha producido un error al asociar el activo a la comunidad. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;

			case -908: //Error 908 - error y rollback - el activo no esta vinculado
				sMsg = "[FATAL] ERROR:908 - El activo no ha sido vinculado. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;

			case -909: //Error 909 - error y rollback - error al desasociar el activo en la comunidad
				sMsg = "[FATAL] ERROR:909 - Se ha producido un error al desasociar el activo a la comunidad. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;
				
			default: //error generico
				msg = Utils.pfmsgFatal("[FATAL] ERROR:"+iSalida+" - La operacion solicitada ha producido un error desconocido. Por favor, revise los datos y avise a soporte.");
				logger.error("[FATAL] ERROR:{} - La operacion solicitada ha producido un error desconocido. Por favor, revise los datos y avise a soporte.",iSalida);
				break;
			}		
		}
		

		
		logger.debug("Finalizadas las comprobaciones.");
		
		return msg;
	}
	
    public void altaActivoComunidad(ActionEvent actionEvent) 
    {  
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	String sMsg = "";

	    	if (sCOCLDO.isEmpty() || sNUDCOM.isEmpty())
	    	{
				sMsg = "ERROR: Los campos 'Documento' y 'Número' deben de ser informados para realizar el alta de Activo. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
	    	}
	    	else if (sCOACES.isEmpty())
	    	{
				sMsg = "ERROR: Debe informar el campo Activo para darlo de alta en la Comunidad. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
	    	}
	    	else
	    	{
				try
				{
			    	//comprobar el activo
					int iSalida = CLComunidades.comprobarActivo(Integer.parseInt(sCOACES));

					switch (iSalida) 
					{
						case 0: //Sin errores
							msg = nuevoMovimiento("X");
							logger.debug("Activo dado de alta:|"+sCOACES+"|");
					    	this.sCOACES  = "";
							break;

						case -1: //error - ya vinculado
							sMsg = "ERROR: El Activo '"+sCOACES+"' ya esta vinculado a otra Comunidad. Por favor, revise los datos.";
							msg = Utils.pfmsgError(sMsg);
							logger.error(sMsg);
							break;

						case -2: //error - no existe
							sMsg = "El Activo '"+sCOACES+"' no pertenece a la cartera. Por favor, revise los datos.";
							msg = Utils.pfmsgWarning(sMsg);
							logger.warn(sMsg);
							break;

						default: //error generico
							sMsg = "[FATAL] ERROR: El Activo '"+sCOACES+"' ha producido un error desconocido. Por favor, revise los datos y avise a soporte.";
							msg = Utils.pfmsgFatal(sMsg);
							logger.error(sMsg);
							break;
					}
				}
				catch(NumberFormatException nfe)
				{
					sMsg = "ERROR: El activo debe ser numérico. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
	    	}

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}	
    }
    
    public void bajaActivoComunidad(ActionEvent actionEvent) 
    {
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;

	    	if (activoseleccionadobaja == null)
	    	{
	    		String sMsg = "ERROR: No se ha seleccionado un Activo para la baja.";
	    		msg = Utils.pfmsgError(sMsg);
	    		logger.error(sMsg);
	    	}
	    	else
	    	{
	        	this.sCOACES  = activoseleccionadobaja.getCOACES();
	        	
	        	msg = nuevoMovimiento("E");
	        	
	        	logger.debug("Activo de baja:|"+activoseleccionadobaja.getCOACES()+"|");
	    	}
	    	
	    	
	    	this.sCOACES  = "";
			
			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}
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
		this.sCOACES = sCOACES.trim();
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


	
	
	public String getsNUFIRE() {
		return sNUFIRE;
	}

	public void setsNUFIRE(String sNUFIRE) {
		this.sNUFIRE = sNUFIRE;
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

	public Map<String,String> getTiposcocldoHM() {
		return tiposcocldoHM;
	}

	public void setTiposcocldoHM(Map<String,String> tiposcocldoHM) {
		this.tiposcocldoHM = tiposcocldoHM;
	}

}
