package com.provisiones.pl.cuentas;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLActivos;
import com.provisiones.ll.CLCuentas;
import com.provisiones.misc.Utils;
import com.provisiones.types.Cuenta;
import com.provisiones.types.tablas.ActivoTabla;

public class GestorCuentasActivos implements Serializable 
{

	private static final long serialVersionUID = -8971515370375587161L;

	private static Logger logger = LoggerFactory.getLogger(GestorCuentasActivos.class.getName());
	
	private String sCOACES = "";

	
	private String sCOPOIN = "";
	private String sNOMUIN = "";
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	
	//Nueva Cuenta
	private String sPais = "ES";	
	private String sDCIBAN = "#";
	private String sNUCCEN = "";
	private String sNUCCOF = "";
	private String sNUCCDI = "";
	private String sNUCCNT = "";
	private String sDescripcion = "";
	
	public GestorCuentasActivos()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorCuentasActivos...");	
		}
	}
	
	private transient ActivoTabla activoseleccionado = null;
	
	private transient Cuenta cuentaseleccionada = null;
	
	private transient ArrayList<ActivoTabla> tablaactivos = null;

	private transient ArrayList<Cuenta> tablacuentas = null;

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
    	
    	this.tablacuentas = null;
    }
    
	public void borrarCamposNuevaCuenta()
	{
    	//this.sPais = "";
    	//this.sDCIBAN = "";
    	this.sNUCCEN = "";
    	this.sNUCCOF = "";
    	this.sNUCCDI = "";
    	this.sNUCCNT = "";
    	this.sDescripcion = "";
	}
	
    public void limpiarPlantillaNuevaCuenta(ActionEvent actionEvent) 
    {  
		borrarCamposNuevaCuenta();
    }
	
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
		this.sCOACES = "";

		borrarCamposActivo();
		borrarCamposNuevaCuenta();
		
		this.tablacuentas = null;
    }
	
	public void buscaActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			ActivoTabla filtro = new ActivoTabla(
					"", sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
					sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
					sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(), "");

			this.setTablaactivos(CLActivos.buscarActivos(filtro));
			
			sMsg = "Encontrados "+getTablaactivos().size()+" activos.";
					
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

	    	String sMsg = "";
	    	
	    	this.sCOACES  = activoseleccionado.getCOACES();
	    	
	    	sMsg = "Activo '"+ sCOACES +"' Seleccionado.";
	    	
	    	msg = Utils.pfmsgInfo(sMsg);
	    	logger.info(sMsg);
	    	
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void buscaCuentas (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			try
			{
				this.setTablacuentas(CLCuentas.buscarCuentasActivo(Integer.parseInt(sCOACES)));
				
				sMsg = "Encontradas "+getTablacuentas().size()+" cuentas.";
						
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}
			catch(NumberFormatException nfe)
			{
				sMsg = "ERROR: El activo debe ser numérico. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	
	public void cargarCuenta(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
		
			String sMsg = "";
			
			this.sPais = cuentaseleccionada.getsPais();
			this.sDCIBAN = cuentaseleccionada.getsDCIBAN();
			this.sNUCCEN = cuentaseleccionada.getsNUCCEN();
			this.sNUCCOF = cuentaseleccionada.getsNUCCOF();
			this.sNUCCDI = cuentaseleccionada.getsNUCCDI();
			this.sNUCCNT = cuentaseleccionada.getsNUCCNT();
			this.sDescripcion = cuentaseleccionada.getsDescripcion();
			
			sMsg = "Cuenta cargada.";
			msg = Utils.pfmsgInfo(sMsg);
			logger.info(sMsg);
	

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}				
	}

	public FacesMessage nuevoMovimiento(boolean bAlta, Cuenta cuenta)
	{
	
		FacesMessage msg;
		
		String sMsg = "";
		
		try
		{
			if (!CLActivos.existeActivo(Integer.parseInt(sCOACES)))
			{
				sMsg = "ERROR: No se puede operar sobre la cuenta, el activo no pertenece a la cartera. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else
			{
				
				int iSalida = CLCuentas.registraMovimientoActivo(bAlta, Integer.parseInt(sCOACES), cuenta);
				
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
						
						this.sPais = cuenta.getsPais();
						this.sDCIBAN = cuenta.getsDCIBAN();
						this.sNUCCEN = cuenta.getsNUCCEN();
						this.sNUCCOF = cuenta.getsNUCCOF();
						this.sNUCCDI = cuenta.getsNUCCDI();
						this.sNUCCNT = cuenta.getsNUCCNT();
						this.sDescripcion = cuenta.getsDescripcion();
						
						tablacuentas.remove(cuentaseleccionada);
					}

					sMsg = "Operación de "+(bAlta? "Alta":"Baja")+" realizada.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
					break;

				case -901: //Error 901 - error y rollback - error al crear la Cuenta
					sMsg = "[FATAL] ERROR:901 - Se ha producido un error al registrar la cuenta. Por favor, revise los datos y avise a soporte.";
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					break;

				case -902: //Error 902 - error y rollback - error al registrar la relaccion
					sMsg = "[FATAL] ERROR:902 - Se ha producido un error al registrar la relacion. Por favor, revise los datos y avise a soporte.";
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
		}
		catch(NumberFormatException nfe)
		{
			sMsg = "ERROR: El activo debe ser numérico. Por favor, revise los datos.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
		}


		
		return msg;
	}
	
    public void altaCuentaActivo(ActionEvent actionEvent) 
    {  
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	String sMsg = "";

	    	//comprobar la cuenta
			if (!CLCuentas.existeCuenta(sNUCCEN, sNUCCOF, sNUCCDI, sNUCCNT))
			{
				if (sCOACES.equals("") || 

						sPais.equals("") ||
						sDCIBAN.equals("") ||
						sNUCCEN.equals("") ||
						sNUCCOF.equals("") ||
						sNUCCDI.equals("") ||
						sNUCCNT.equals("") ||
						sDescripcion.equals(""))
					{
						sMsg = "ERROR: Faltan campos por informar para realizar el alta. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
					}
					else
					{
						Cuenta cuenta = new Cuenta(
								sPais.toUpperCase(), 
								Utils.calculaDCIBAN(sPais.toUpperCase(), sNUCCEN, sNUCCOF, sNUCCDI, sNUCCNT), 
								sNUCCEN, 
								sNUCCOF, 
								sNUCCDI, 
								sNUCCNT, 
								sDescripcion);

						msg = nuevoMovimiento(true, cuenta);
						logger.debug("Cuenta dada de alta:|"+sPais+"|"+sDCIBAN+"|"+sNUCCEN+"|"+sNUCCOF+"|"+sNUCCDI+"|"+sNUCCNT+"|"+sDescripcion+"|");

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
    
    public void bajaCuentaActivo(ActionEvent actionEvent) 
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

}
