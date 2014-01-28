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
	private String sPais = "";	
	private String sDCIBAN = "";
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
		
	}
	
	public void borrarResultadosActivo()
	{
    	this.tablaactivos = null;
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";

    	borrarCamposActivo();
    	
    	borrarResultadosActivo();
    }
    
	public void borrarCamposComunidad()
	{

    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
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
			
			ActivoTabla filtro = new ActivoTabla(
					sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
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
			

			this.setTablacuentas(CLCuentas.buscarCuentasActivo(sCOACES));
			
			sMsg = "Encontradas "+getTablacuentas().size()+" cuentas.";
					
			msg = Utils.pfmsgInfo(sMsg);
			logger.info(sMsg);

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

	public FacesMessage nuevoMovimiento(boolean bOperacion)
	{
		
		FacesMessage msg;

		String sMsg = "";
		
		if (ConnectionManager.comprobarConexion())
		{
			if (sCOACES.equals(""))
			{
				sMsg = "ERROR: El campo Activo no ha sido informado. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else if (!CLActivos.existeActivo(sCOACES))
			{
				sMsg = "ERROR: El Activo informado no pertenece a la cartera. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else
			{
				int iSalida = CLCuentas.registraMovimientoActivo(bOperacion,sCOACES, sNUCCEN, sNUCCOF, sNUCCDI, sNUCCNT, sDescripcion);

				switch (iSalida)
				{
					case 0: //Sin errores

						//this.sPais  = "";
						this.sNUCCEN  = "";
						this.sNUCCOF  = "";
						this.sNUCCDI  = "";
				    	this.sNUCCNT  = "";
						sMsg = "Operación de "+(bOperacion? "Alta":"Baja")+" realizada.";
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
		else
		{
			sMsg = "[FATAL] ERROR:910 - Se ha producido un error al conectar con la base de datos. Por favor, revise los datos y avise a soporte.";
			msg = Utils.pfmsgFatal(sMsg);
			logger.error(sMsg);
		}
		
		return msg;
	}
	
    public void altaCuenta(ActionEvent actionEvent) 
    {  
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	String sMsg = "";

	    	//comprobar el activo
			if (!CLCuentas.buscarCodigoCuenta (sNUCCEN, sNUCCOF, sNUCCDI, sNUCCNT).equals(""))
			{
				sMsg = "ERROR: - La cuenta ya esta dada de alta. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else
			{
				msg = nuevoMovimiento(true);
			}
			

			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
    
    public void bajaCuenta(ActionEvent actionEvent) 
    {
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	String sMsg = "";

	    	//comprobar el activo
			if (CLCuentas.buscarCodigoCuenta (sNUCCEN, sNUCCOF, sNUCCDI, sNUCCNT).equals(""))
			{
				sMsg = "ERROR: - La cuenta no esta dada de alta. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else
			{
				msg = nuevoMovimiento(false);
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
