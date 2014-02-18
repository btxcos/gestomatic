package com.provisiones.pl.detalles;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLComunidades;
import com.provisiones.ll.CLCuentas;
import com.provisiones.misc.Sesion;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Comunidad;
import com.provisiones.types.Cuenta;
import com.provisiones.types.tablas.ActivoTabla;

public class GestorDetallesComunidad implements Serializable 
{

	private static final long serialVersionUID = 1204890492527617532L;


	private static Logger logger = LoggerFactory.getLogger(GestorDetallesComunidad.class.getName());
	
	private String sCODTRN = ValoresDefecto.DEF_E1_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOENGP = ValoresDefecto.DEF_COENGP;

	private long liCodComunidad = 0;
	
	private String sNumActivosComunidad = "";
	
	private String sCOCLDO = "";
	private String sNUDCOM = "";
	private String sNOMCOC = "";
	private String sNODCCO = "";
	private String sNOMPRC = "";
	private String sNUTPRC = "";
	private String sNOMADC = "";
	private String sNUTADC = "";
	private String sNODCAD = "";
	
	private String sPais = "";
	private String sDCIBAN = "";
	
	private String sNUCCEN = "";
	private String sNUCCOF = "";
	private String sNUCCDI = "";
	private String sNUCCNT = "";

	private String sOBTEXC = "";
	
	private String sOBDEER = "";
	
	private String sCOACES = "";
	
	private String sNota = "";
	
	private transient ActivoTabla activoseleccionado = null;
	private transient ArrayList<ActivoTabla> tablaactivos = null;

	private transient Cuenta cuentaseleccionada = null;
	private transient ArrayList<Cuenta> tablacuentas = null;
	
	public GestorDetallesComunidad()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorDetallesComunidad...");
			cargarDetallesComunidad();
		}
	}
	
	public void volver(ActionEvent actionEvent)
	{
		logger.debug("Volviendo...");
		//return Utils.cargarHistorial(sClase);
		
		try 
		{
			FacesContext.getCurrentInstance().getExternalContext().redirect(Sesion.cargarHistorial());
		}
		catch (IOException e)
		{
			FacesMessage msg;
			
			String sMsg = "ERROR: Ocurrió un problema al intentar regresar. Por favor, avise a soporte.";
			
			msg = Utils.pfmsgFatal(sMsg);
			logger.error(sMsg);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
			

		}
	}
	
	public void cargarDetallesComunidad()
	{
		logger.debug("Cargando Comunidad...");

		
		//this.sNUPROF = ((GestorListaProvisiones)((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorListaProvisiones")).getsNUPROF();
		
		String sCodComunidad = Sesion.cargarDetalle();
		
		logger.debug("sCodComunidad:|"+sCodComunidad+"|");
		
		FacesMessage msg;
		
		String sMsg = "";
		
		if (!sCodComunidad.equals(""))
		{
			try
			{
				this.liCodComunidad = Long.parseLong(sCodComunidad);

			  	Comunidad comunidad = CLComunidades.buscarDetallesComunidad(liCodComunidad);

		    	logger.debug(comunidad.logComunidad());
			  	
		    	this.sCOCLDO = comunidad.getsCOCLDO();
		    	this.sNUDCOM = comunidad.getsNUDCOM();
		    	this.sNOMCOC = comunidad.getsNOMCOC();
		    	this.sNODCCO = comunidad.getsNODCCO();
		    	this.sNOMPRC = comunidad.getsNOMPRC();
		    	this.sNUTPRC = comunidad.getsNUTPRC();
		    	this.sNOMADC = comunidad.getsNOMADC();
		    	this.sNUTADC = comunidad.getsNUTADC();
		    	this.sNODCAD = comunidad.getsNODCAD();
		    	
		    	Cuenta cuenta = CLCuentas.buscarCuenta(Long.parseLong(comunidad.getsCuenta()));
		    	
		    	this.sPais = cuenta.getsPais();
		    	this.sDCIBAN = cuenta.getsDCIBAN();	    	
		    	this.sNUCCEN = cuenta.getsNUCCEN();
		    	this.sNUCCOF = cuenta.getsNUCCOF();
		    	this.sNUCCDI = cuenta.getsNUCCDI();
		    	this.sNUCCNT = cuenta.getsNUCCNT();

		    	this.sOBTEXC = comunidad.getsOBTEXC();
		    		
		    	this.sOBDEER = "";
		    		
		    	this.sNota = CLComunidades.buscarNota(liCodComunidad);

		    	this.sNumActivosComunidad = Long.toString(CLComunidades.buscarNumeroActivosComunidad(liCodComunidad));

		    	this.setTablacuentas(CLCuentas.buscarCuentasComunidad(CLComunidades.buscarCodigoComunidad(sCOCLDO, sNUDCOM)));
		    	
				this.setTablaactivos(CLComunidades.buscarActivosCodigoComunidad(liCodComunidad));
				
				
				sMsg = "La Comunidad se cargó correctamente.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
				
			}
			catch(NumberFormatException nfe)
			{
				sMsg = "ERROR: Ocurrió un error al cargar los datos de la Comunidad. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
			}


		}
		else
		{
			sMsg = "ERROR: Ocurrió un error al recuperar la comunidad. Por favor, revise los datos y avise a soporte.";
			msg = Utils.pfmsgFatal(sMsg);
			logger.error(sMsg);
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}

    public void limpiarNota(ActionEvent actionEvent) 
    {  
    	this.sNota = "";
    }
	
	public void guardaNota (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;

			String sMsg = "";
			
			if (CLComunidades.guardarNota(liCodComunidad, sNota))
			{
				sMsg = "Nota guardada correctamente.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}
			else
			{
				sMsg = "ERROR: Ocurrio un error al guardar la nota de la comunidad. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
			}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		
		}
	}
	
	public void buscaCuentas (ActionEvent actionEvent)
	{

		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			logger.debug("Buscando Cuentas adicionales...");
			
			this.setTablacuentas(CLCuentas.buscarCuentasComunidad(CLComunidades.buscarCodigoComunidad(sCOCLDO, sNUDCOM)));


			if (getTablacuentas().size() == 0)
			{
				sMsg = "No se encontraron cuentas adicionales.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
			}
			else if (getTablacuentas().size() == 1)
			{
				sMsg = "Encontrada una cuenta adicional.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.warn(sMsg);
			}
			else
			{
				sMsg = "Encontradas "+getTablacuentas().size()+" cuentas adicionales.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}

	public void buscaActivos (ActionEvent actionEvent)
	{


		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			logger.debug("Buscando Activos...");
			
			this.setTablaactivos(CLComunidades.buscarActivosCodigoComunidad(liCodComunidad));


			if (getTablaactivos().size() == 0)
			{
				sMsg = "No se encontraron activos asociados.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
			}
			else if (getTablaactivos().size() == 1)
			{
				sMsg = "Encontrado un activo asociado.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}
			else
			{
				sMsg = "Encontrados "+getTablaactivos().size()+" activos asociados.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	
	
	public void cargarDetallesActivo(ActionEvent actionEvent) 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
			
			this.sCOACES = activoseleccionado.getCOACES(); 

			logger.debug("sCOACES:|"+sCOACES+"|");
			
			if (sCOACES != "")
			{
		    	Sesion.guardaDetalle(sCOACES);
		    	Sesion.guardarHistorial("detallescomunidad.xhtml","GestorDetallesActivo");

		    	sPagina = "detallesactivo.xhtml";
		    	
				try 
				{
					logger.debug("Redirigiendo...");
					FacesContext.getCurrentInstance().getExternalContext().redirect(sPagina);
				}
				catch (IOException e)
				{
					FacesMessage msg;
					
					String sMsg = "ERROR: Ocurrió un problema al acceder a los detalles. Por favor, avise a soporte.";
					
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			}
			else
			{
				FacesMessage msg;

				msg = Utils.pfmsgWarning("No se ha seleccionado ningún activo.");
				
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
		}

		//return sPagina;
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

	public String getsNota() {
		return sNota;
	}

	public void setsNota(String sNota) {
		this.sNota = sNota;
	}

	public String getsNumActivosComunidad() {
		return sNumActivosComunidad;
	}

	public void setsNumActivosComunidad(String sNumActivosComunidad) {
		this.sNumActivosComunidad = sNumActivosComunidad;
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

	public Cuenta getCuentaseleccionada() {
		return cuentaseleccionada;
	}

	public void setCuentaseleccionada(Cuenta cuentaseleccionada) {
		this.cuentaseleccionada = cuentaseleccionada;
	}

	public ArrayList<Cuenta> getTablacuentas() {
		return tablacuentas;
	}

	public void setTablacuentas(ArrayList<Cuenta> tablacuentas) {
		this.tablacuentas = tablacuentas;
	}
	
}
