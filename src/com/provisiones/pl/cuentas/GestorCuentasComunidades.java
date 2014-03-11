package com.provisiones.pl.cuentas;

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
import com.provisiones.ll.CLCuentas;
import com.provisiones.misc.Utils;
import com.provisiones.types.Comunidad;
import com.provisiones.types.Cuenta;
import com.provisiones.types.tablas.ActivoTabla;

public class GestorCuentasComunidades implements Serializable 
{

	private static final long serialVersionUID = -2522402698942070335L;

	private static Logger logger = LoggerFactory.getLogger(GestorCuentasComunidades.class.getName());
	
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
	
	//Cuenta principal
	private String sPais = "";	
	private String sDCIBAN = "";
	private String sNUCCEN = "";
	private String sNUCCOF = "";
	private String sNUCCDI = "";
	private String sNUCCNT = "";
	private String sDescripcion = "";

	//Cuenta nueva
	private String sPaisN = "ES";	
	private String sDCIBANN = "#";
	private String sNUCCENN = "";
	private String sNUCCOFN = "";
	private String sNUCCDIN = "";
	private String sNUCCNTN = "";
	private String sDescripcionN = "";
	
	private transient ActivoTabla activoseleccionado = null;
	private transient ArrayList<ActivoTabla> tablaactivos = null;

	private transient Cuenta cuentaseleccionada = null;
	private transient ArrayList<Cuenta> tablacuentas = null;
	
	private Map<String,String> tiposcocldoHM = new LinkedHashMap<String, String>();

	public GestorCuentasComunidades()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorCuentasComunidades...");
			
			tiposcocldoHM.put("C.I.F.",                     "2");
			tiposcocldoHM.put("C.I.F país extranjero.",     "5");
			tiposcocldoHM.put("Otros persona jurídica.",    "J");
		}
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
    	
    	this.tablaactivos = null;
	}

    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";

    	borrarCamposActivo();
    }
    
	public void borrarCamposComunidad()
	{
    	this.sCOCLDO = "";
    	this.sNUDCOM = "";
    	this.sNOMCOC = "";
    	this.sNODCCO = "";

    	this.sPais = "";
    	this.sDCIBAN = "";
    	this.sNUCCEN = "";
    	this.sNUCCOF = "";
    	this.sNUCCDI = "";
    	this.sNUCCNT = "";
    	this.sDescripcion = "";
    	
    	this.tablacuentas = null;
	}
	
	public void borrarCamposNuevaCuenta()
	{
    	//this.sPaisN = "";
    	//this.sDCIBANN = "";
    	this.sNUCCENN = "";
    	this.sNUCCOFN = "";
    	this.sNUCCDIN = "";
    	this.sNUCCNTN = "";
    	this.sDescripcionN = "";
	}
	
    public void limpiarPlantillaNuevaCuenta(ActionEvent actionEvent) 
    {  
		borrarCamposNuevaCuenta();
    }

    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
		this.sCOACES = "";

		borrarCamposActivo();
		borrarCamposComunidad();
		borrarCamposNuevaCuenta();
    }
	
	public void buscaActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			ActivoTabla filtro = new ActivoTabla(
					"", sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
					sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
					sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(), "");
			
			this.setTablaactivos(CLComunidades.buscarActivosConComunidad(filtro));

			String sMsg = "Encontrados "+getTablaactivos().size()+" Activos relacionados.";
			msg = Utils.pfmsgInfo(sMsg);
			logger.info(sMsg);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void seleccionarActivo(ActionEvent actionEvent) 
    {
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;

	    	this.sCOACES  = activoseleccionado.getCOACES();
	    	
	    	String sMsg = "Activo '"+ sCOACES +"' Seleccionado.";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	logger.info(sMsg);
	    	
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void buscarComunidad(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			borrarCamposComunidad();
			
			if (sCOACES.equals(""))
			{
				sMsg = "ERROR: Debe informar el Activo para realizar una búsqueda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else
			{
				try
				{
					if (!CLActivos.existeActivo(Integer.parseInt(sCOACES)))
					{
						sMsg = "El Activo '"+sCOACES+"' no pertenece a la cartera. Por favor, revise los datos.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);
					}
					else
					{
						Comunidad comunidad = CLComunidades.buscarComunidadDeActivo(Integer.parseInt(sCOACES));
						
						if (comunidad.getsNUDCOM().equals(""))
						{
							sMsg = "ERROR: El Activo '"+sCOACES+"' no esta asociado a una comunidad.";
							msg = Utils.pfmsgError(sMsg);
							logger.error(sMsg);
						}
						else
						{
							this.sCOCLDO = comunidad.getsCOCLDO();
							this.sNUDCOM = comunidad.getsNUDCOM();
							this.sNOMCOC = comunidad.getsNOMCOC();
							this.sNODCCO = comunidad.getsNODCCO();

							Cuenta cuenta = CLCuentas.buscarCuenta(Long.parseLong(comunidad.getsCuenta()));
							
							this.sPais = cuenta.getsPais();
							this.sDCIBAN = cuenta.getsDCIBAN();
							this.sNUCCEN = cuenta.getsNUCCEN();
							this.sNUCCOF = cuenta.getsNUCCOF();
							this.sNUCCDI = cuenta.getsNUCCDI();
							this.sNUCCNT = cuenta.getsNUCCNT();
							this.sDescripcion = cuenta.getsDescripcion();
							
							this.setTablacuentas(CLCuentas.buscarCuentasConvencionalesComunidad(CLComunidades.buscarCodigoComunidad(comunidad.getsCOCLDO(), comunidad.getsNUDCOM())));
							
							sMsg = "La comunidad se ha cargado correctamente.";
							msg = Utils.pfmsgInfo(sMsg);
							logger.info(sMsg);
						}	
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
	
	public void cargarComunidad(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = ""; 

			if (sCOCLDO.equals("") || sNUDCOM.equals(""))
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

				Cuenta cuenta = CLCuentas.buscarCuenta(Long.parseLong(comunidad.getsCuenta()));
				
				this.sPais = cuenta.getsPais();
				this.sDCIBAN = cuenta.getsDCIBAN();
				this.sNUCCEN = cuenta.getsNUCCEN();
				this.sNUCCOF = cuenta.getsNUCCOF();
				this.sNUCCDI = cuenta.getsNUCCDI();
				this.sNUCCNT = cuenta.getsNUCCNT();
				this.sDescripcion = cuenta.getsDescripcion();
				
				this.setTablacuentas(CLCuentas.buscarCuentasConvencionalesComunidad(CLComunidades.buscarCodigoComunidad(comunidad.getsCOCLDO(), comunidad.getsNUDCOM())));
				
				sMsg = "La Comunidad '"+sNUDCOM+"' se ha cargado correctamente.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
				
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
				sMsg = "Encontradas "+getTablacuentas().size()+" Cuentas relacionadas.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
				
			}
					
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}			
	}

	public FacesMessage nuevoMovimiento(boolean bAlta, Cuenta cuenta)
	{
		FacesMessage msg;
		
		String sMsg = "";
		
		if (!CLComunidades.existeComunidad(sCOCLDO, sNUDCOM.toUpperCase()))
		{
			sMsg = "ERROR:011 - No se puede operar sobre la cuenta, la comunidad no existe. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
		}
		else
		{
			
			int iSalida = CLCuentas.registraMovimientoComunidad(bAlta, sCOCLDO, sNUDCOM, cuenta);
			
			switch (iSalida) 
			{
			case 0: //Sin errores
				if (bAlta)
				{
					tablacuentas.add(cuenta);
			    	borrarCamposNuevaCuenta();
				}
				else
				{
					
					this.sPaisN = cuenta.getsPais();
					this.sDCIBANN = cuenta.getsDCIBAN();
					this.sNUCCENN = cuenta.getsNUCCEN();
					this.sNUCCOFN = cuenta.getsNUCCOF();
					this.sNUCCDIN = cuenta.getsNUCCDI();
					this.sNUCCNTN = cuenta.getsNUCCNT();
					this.sDescripcionN = cuenta.getsDescripcion();
					
					tablacuentas.remove(cuentaseleccionada);
				}

				sMsg = "Operación de "+(bAlta? "Alta":"Baja")+" realizada correctamente.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
				break;

			case -901: //Error 901 - error y rollback - error al crear la Cuenta
				sMsg = "[FATAL] ERROR:901 - Se ha producido un error al registrar la cuenta. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;

			case -902: //Error 902 - error y rollback - error al registrar la relaccion
				sMsg = "[FATAL] ERROR:902 - Se ha producido un error al registrar la relación. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;

			case -903: //Error 903 - error y rollback - error al borrar la cuenta
				sMsg = "[FATAL] ERROR:903 - Se ha producido un error al borrar la cuenta. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;

			case -904: //Error 904 - error y rollback - error al eliminar la relaccion
				sMsg = "[FATAL] ERROR:904 - Se ha producido un error al eliminar la relacion. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;

			case -910: //Error 910 - error y rollback - error al conectar con la base de datos
				sMsg = "[FATAL] ERROR:910 - Se ha producido un error al conectar con la base de datos. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;

			default: //error generico
				sMsg = "[FATAL] ERROR: Se ha producido un error desconocido. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;
			}		
		}
		

		
		logger.debug("Finalizadas las comprobaciones.");
		
		return msg;
	}
	
    public void altaCuentaComunidad(ActionEvent actionEvent) 
    {  
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	String sMsg = "";

	    	//comprobar la cuenta
			if (!CLCuentas.existeCuenta(sNUCCENN, sNUCCOFN, sNUCCDIN, sNUCCNTN))
			{
				if (sCOCLDO.equals("") ||
					sNUDCOM.equals("") || 

					sPaisN.equals("") ||
					sDCIBANN.equals("") ||
					sNUCCENN.equals("") ||
					sNUCCOFN.equals("") ||
					sNUCCDIN.equals("") ||
					sNUCCNTN.equals("") ||
					sDescripcionN.equals(""))
				{
					sMsg = "ERROR: Faltan campos por informar para realizar el alta. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else if (!Utils.compruebaCC(sNUCCENN,sNUCCOFN,sNUCCDIN,sNUCCNTN))
				{
					sMsg = "ERROR: El dígito de control es erróneo. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else
				{
					Cuenta cuenta = new Cuenta(
							sPaisN.toUpperCase(), 
							Utils.calculaDCIBAN(sPaisN.toUpperCase(), sNUCCENN, sNUCCOFN, sNUCCDIN, sNUCCNTN), 
							sNUCCENN, 
							sNUCCOFN, 
							sNUCCDIN, 
							sNUCCNTN, 
							sDescripcionN);

					msg = nuevoMovimiento(true, cuenta);
					logger.debug("Cuenta dada de alta:|"+sPaisN+"|"+sDCIBANN+"|"+sNUCCENN+"|"+sNUCCOFN+"|"+sNUCCDIN+"|"+sNUCCNTN+"|"+sDescripcionN+"|");

				}
			}
			else
			{
				sMsg = "ERROR: La cuenta proporcionada ya esta registrada. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}	
    }
    
    public void bajaCuentaComunidad(ActionEvent actionEvent) 
    {
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;

	    	if (cuentaseleccionada == null)
	    	{
	    		String sMsg = "ERROR: No se ha seleccionado una cuenta.";
	    		msg = Utils.pfmsgError(sMsg);
	    		logger.error(sMsg);
	    	}
	    	else
	    	{
	    		Cuenta cuenta = cuentaseleccionada;

	    		msg = nuevoMovimiento(false, cuenta);
	    	}
	
			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}
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

	public String getsPais() {
		return sPais;
	}

	public void setsPais(String sPais) {
		this.sPais = sPais;
	}

	public String getsDCIBAN() {
		return sDCIBAN;
	}

	public void setsDCIBAN(String sDCIBAN) {
		this.sDCIBAN = sDCIBAN;
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

	public String getsDescripcion() {
		return sDescripcion;
	}

	public void setsDescripcion(String sDescripcion) {
		this.sDescripcion = sDescripcion;
	}

	public String getsPaisN() {
		return sPaisN;
	}

	public void setsPaisN(String sPaisN) {
		this.sPaisN = sPaisN;
	}

	public String getsDCIBANN() {
		return sDCIBANN;
	}

	public void setsDCIBANN(String sDCIBANN) {
		this.sDCIBANN = sDCIBANN;
	}

	public String getsNUCCENN() {
		return sNUCCENN;
	}

	public void setsNUCCENN(String sNUCCENN) {
		this.sNUCCENN = sNUCCENN;
	}

	public String getsNUCCOFN() {
		return sNUCCOFN;
	}

	public void setsNUCCOFN(String sNUCCOFN) {
		this.sNUCCOFN = sNUCCOFN;
	}

	public String getsNUCCDIN() {
		return sNUCCDIN;
	}

	public void setsNUCCDIN(String sNUCCDIN) {
		this.sNUCCDIN = sNUCCDIN;
	}

	public String getsNUCCNTN() {
		return sNUCCNTN;
	}

	public void setsNUCCNTN(String sNUCCNTN) {
		this.sNUCCNTN = sNUCCNTN;
	}

	public String getsDescripcionN() {
		return sDescripcionN;
	}

	public void setsDescripcionN(String sDescripcionN) {
		this.sDescripcionN = sDescripcionN;
	}

	public ActivoTabla getActivoseleccionado() {
		return activoseleccionado;
	}

	public void setActivoseleccionado(ActivoTabla activoseleccionado) {
		this.activoseleccionado = activoseleccionado;
	}

	public Cuenta getCuentaseleccionada() {
		return cuentaseleccionada;
	}

	public void setCuentaseleccionada(Cuenta cuentaseleccionada) {
		this.cuentaseleccionada = cuentaseleccionada;
	}

	public ArrayList<ActivoTabla> getTablaactivos() {
		return tablaactivos;
	}

	public void setTablaactivos(ArrayList<ActivoTabla> tablaactivos) {
		this.tablaactivos = tablaactivos;
	}

	public ArrayList<Cuenta> getTablacuentas() {
		return tablacuentas;
	}

	public void setTablacuentas(ArrayList<Cuenta> tablacuentas) {
		this.tablacuentas = tablacuentas;
	}

	public Map<String,String> getTiposcocldoHM() {
		return tiposcocldoHM;
	}

	public void setTiposcocldoHM(Map<String,String> tiposcocldoHM) {
		this.tiposcocldoHM = tiposcocldoHM;
	}

}
