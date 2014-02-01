package com.provisiones.pl.pagos;

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
import com.provisiones.ll.CLDescripciones;
import com.provisiones.ll.CLGastos;
import com.provisiones.ll.CLPagos;
import com.provisiones.ll.CLProvisiones;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Comunidad;
import com.provisiones.types.Cuenta;
import com.provisiones.types.Gasto;
import com.provisiones.types.Pago;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.GastoTabla;
import com.provisiones.types.tablas.ProvisionTabla;

public class GestorPagosSimple implements Serializable 
{

	private static final long serialVersionUID = 649592960198529152L;

	private static Logger logger = LoggerFactory.getLogger(GestorPagosSimple.class.getName());

	//Buscar
	private String sCOACESB = "";
	private String sNUPROFB = "";
	
	private String sCOGRUGB = "";
	private String sCOTPGAB = "";
	private String sCOSBGAB = "";
	private String sFEDEVEB = "";
	
	private String sCodGastoB = "";
	
	private String sCOPOINB = "";
	private String sNOMUINB = "";
	private String sNOPRACB = "";
	private String sNOVIASB = "";
	private String sNUPIACB = "";
	private String sNUPOACB = "";
	private String sNUPUACB = "";
	
	private String sFEPFONB = "";
	
	//Gasto
	private String sCOACES = "";
	private boolean bDevolucion = false;
	private String sCOGRUG = "";
	private String sCOTPGA = "";
	private String sCOSBGA = "";
	private String sDCOSBGA = "";
	private String sPTPAGO = "";
	private String sDPTPAGO = "";
	
	private String sFEDEVE = "";
	private String sFFGTVP = "";
	//private String sFEPAGA = ValoresDefecto.DEF_FEPAGA;
	private String sFELIPG = "";
	private String sCOSIGA = "";
	private String sEstado = "";
	private String sFEEESI = "";
	private String sFEECOI = "";
	private String sFEEAUI = "";
	//private String sFEEPAI = "";

	private String sIMNGAS = "";
	private String sYCOS02 = "";
	private String sIMRGAS = "";
	private String sYCOS04 = "";
	private String sIMDGAS = "";
	private String sYCOS06 = "";
	private String sIMCOST = "";
	private String sYCOS08 = "";
	private String sIMOGAS = "";
	private String sYCOS10 = "";
	
	private String sIMDTGA = "";
	private String sCOUNMO = ValoresDefecto.DEF_COUNMO;
	private String sIMIMGA = "";
	private boolean bIMIMGA = false;
	private String sCOIMPT = "";
	private String sDCOIMPT = "";
	
	//private String sCOTNEG = ValoresDefecto.DEF_COTNEG;
	
	//private String sCOENCX = ValoresDefecto.DEF_COENCX;
	//private String sCOOFCX = ValoresDefecto.DEF_COOFCX;
	//private String sNUCONE = ValoresDefecto.DEF_NUCONE;
	private String sNUPROF = "";
	//private String sCOMONA = ValoresDefecto.DEF_COMONA;
	//private String sBIAUTO = ValoresDefecto.DEF_BIAUTO;
	//private String sFEAUFA = ValoresDefecto.DEF_FEAUFA;
	//private String sCOTERR = ValoresDefecto.DEF_COTERR;
	//private String sFMPAGN = ValoresDefecto.DEF_FMPAGN;
	//private String sFEPGPR = ValoresDefecto.DEF_FEPGPR;
	
	//private String sFEAPLI = ValoresDefecto.DEF_FEAPLI;
	
	//private String sCOAPII = ValoresDefecto.DEF_COAPII;
	//private String sCOSPII = ValoresDefecto.DEF_COSPII_GA;
	//private String sNUCLII = ValoresDefecto.DEF_NUCLII;
	
	
	//Comunidad
	private String sCOCLDO = "";
	private String sNUDCOM = "";

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

	private transient ActivoTabla activoseleccionado = null;
	private transient ArrayList<ActivoTabla> tablaactivos = null;

	private transient ProvisionTabla provisionseleccionada = null;
	private transient ArrayList<ProvisionTabla> tablaprovisiones = null;
	
	private transient GastoTabla gastoseleccionado = null;
	private transient ArrayList<GastoTabla> tablagastos = null;

	public GestorPagosSimple()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorListaGastos...");	
		}
	}
	
	public void borrarCamposBuscar()
	{
		this.sCOACESB = "";
    	this.sNUPROFB = "";
    	
    	this.setGastoseleccionado(null);
    	this.setTablagastos(null);
	}
	
	public void borrarCamposActivo()
	{
		this.sCOPOINB = "";
		this.sNOMUINB = "";
		this.sNOPRACB = "";
		this.sNOVIASB = "";
		this.sNUPIACB = "";
		this.sNUPOACB = "";
		this.sNUPUACB = "";
    	
    	this.setActivoseleccionado(null);
    	this.setTablaactivos(null);
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	borrarCamposActivo();
    }
    
    
	public void borrarCamposProvision()
	{
		this.sFEPFONB = "";
    	
    	this.setProvisionseleccionada(null);
    	this.setTablaprovisiones(null);
	}
	
    public void limpiarPlantillaProvision(ActionEvent actionEvent) 
    {  
    	borrarCamposProvision();
    }
    
	public void borrarCamposGasto()
	{
		this.sCOACES = "";
		this.sCOGRUG = "";
		this.bDevolucion = false;
		this.sCOTPGA = "";
		this.sCOSBGA = "";
		this.sPTPAGO = "";
		this.sDPTPAGO = "";
		this.sDCOSBGA = ""; 

		this.sFEDEVE = "";
		this.sFFGTVP = "";
		//this.sFEPAGA = ValoresDefecto.DEF_FEPAGA;
		this.sFELIPG = "";

		this.sCOSIGA = "";
		this.sEstado = "";
		this.sFEEESI = "";
		this.sFEECOI = "";
		this.sFEEAUI = "";


		this.sIMNGAS = "";
		this.sYCOS02 = "";
		this.sIMRGAS = "";
		this.sYCOS04 = "";
		this.sIMDGAS = "";
		this.sYCOS06 = "";
		this.sIMCOST = "";
		this.sYCOS08 = "";
		this.sIMOGAS = "";
		this.sYCOS10 = "";
		this.sIMDTGA = "";
		this.sCOUNMO = "";
		this.sIMIMGA = "";
		this.sCOIMPT = "";
		this.sDCOIMPT = "";

		//this.sCOTNEG = ValoresDefecto.DEF_COTNEG;

		//this.sCOENCX = ValoresDefecto.DEF_COENCX;
		//this.sCOOFCX = ValoresDefecto.DEF_COOFCX;
		//this.sNUCONE = ValoresDefecto.DEF_NUCONE;

		this.sNUPROF = "";

		//this.sFEAGTO = ValoresDefecto.DEF_FEAGTO;

		//this.sCOMONA = ValoresDefecto.DEF_COMONA;
		//this.sBIAUTO = ValoresDefecto.DEF_BIAUTO;
		//this.sFEAUFA = ValoresDefecto.DEF_FEAUFA;
		//this.sCOTERR = ValoresDefecto.DEF_COTERR;

		//this.sFMPAGN = ValoresDefecto.DEF_FMPAGN;
		//this.sFEPGPR = "";
		
		//this.sFEAPLI = ValoresDefecto.DEF_FEAPLI;
		
		//this.sCOAPII = ValoresDefecto.DEF_COAPII;
		//this.sCOSPII = ValoresDefecto.DEF_COSPII_GA;
		//this.sNUCLII = ValoresDefecto.DEF_NUCLII;

	}
	
	public void borrarCamposComunidad()
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

	}
    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	borrarCamposBuscar();
    	borrarCamposActivo();
    	borrarCamposProvision();
    	borrarCamposGasto();
    }
    
	public void buscarActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			ActivoTabla filtro = new ActivoTabla(
					"", sCOPOINB.toUpperCase(), sNOMUINB.toUpperCase(),
					sNOPRACB.toUpperCase(), sNOVIASB.toUpperCase(), sNUPIACB.toUpperCase(), 
					sNUPOACB.toUpperCase(), sNUPUACB.toUpperCase(), "");
			
			this.setTablaactivos(CLGastos.buscarActivosConGastosAutorizados(filtro));

			String sMsg = "Encontrados "+getTablaactivos().size()+" activos relacionados.";
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
	    	
	    	this.sCOACESB  = activoseleccionado.getCOACES();
	    	
	    	String sMsg = "Activo '"+sCOACESB+"' seleccionado.";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	logger.info(sMsg);
	    	
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void buscarProvisiones (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = ""; 
			
			String sFecha = Utils.compruebaFecha(sFEPFONB);
			
			if (sFecha.equals("#"))
			{
				sMsg = "La fecha proporcionada no es válida. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else
			{
				this.setTablaprovisiones(CLProvisiones.buscarProvisionesAutorizadasFecha(sFecha));

				sMsg = "Encontradas "+getTablaprovisiones().size()+" provisiones relacionadas.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	public void seleccionarProvision(ActionEvent actionEvent) 
    { 
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	this.sNUPROFB  = provisionseleccionada.getNUPROF();
	    	
	    	String sMsg = "Provision '"+sNUPROFB+"' seleccionada.";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	logger.info(sMsg);
	    	
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
    
	public void buscarGastosActivo (ActionEvent actionEvent)
	{

		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;

			if (!sCOACESB.equals(""))
			{
				try
				{
					this.setTablagastos(CLGastos.buscarGastosActivo(Integer.parseInt(sCOACESB)));
					
					if (getTablagastos().size() == 0)
					{
						msg = Utils.pfmsgWarning("No se encontraron gastos con los criterios solicitados.");
					}
					else if (getTablagastos().size() == 1)
					{
						msg = Utils.pfmsgInfo("Encontrado un gasto relacionado.");
					}
					else
					{
						msg = Utils.pfmsgInfo("Encontrados "+getTablagastos().size()+" gastos relacionados.");
					}
				}
				catch(NumberFormatException nfe)
				{
					String sMsg = "ERROR: El activo debe ser numérico. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				
				
			}
			else
			{
				msg = Utils.pfmsgWarning("No se informó el campo 'Activo'. Por favor, revise los datos.");
			}


			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
    
	public void buscarGastosProvision (ActionEvent actionEvent)
	{

		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;

			if (!sNUPROFB.equals(""))
			{
				this.setTablagastos(CLGastos.buscarGastosProvision(sNUPROFB));
				
				if (getTablagastos().size() == 0)
				{
					msg = Utils.pfmsgWarning("No se encontraron gastos con los criterios solicitados.");
				}
				else if (getTablagastos().size() == 1)
				{
					msg = Utils.pfmsgInfo("Encontrado un gasto relacionado.");
				}
				else
				{
					msg = Utils.pfmsgInfo("Encontrados "+getTablagastos().size()+" gastos relacionados.");
				}
			}
			else
			{
				msg = Utils.pfmsgWarning("No se informó el campo 'Provisión'. Por favor, revise los datos.");
			}


			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	public void seleccionarGasto(ActionEvent actionEvent) 
    { 
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	String sMsg = "";

			this.sCOACES = gastoseleccionado.getCOACES();
			
			//Cargar Comunidad
			
			//Cargar Gasto
	    	this.sCOGRUG = gastoseleccionado.getCOGRUG();
	    	this.sCOTPGA = gastoseleccionado.getCOTPGA();
	    	this.sCOSBGA = gastoseleccionado.getCOSBGA();
	    	this.sFEDEVE = gastoseleccionado.getFEDEVE();
	    	
	    	this.sDCOSBGA = gastoseleccionado.getDCOSBGA();
	    	
	    	
	    	this.sCodGastoB = Long.toString(CLGastos.buscarCodigoGasto(Integer.parseInt(sCOACES),sCOGRUG,sCOTPGA,sCOSBGA,Utils.compruebaFecha(sFEDEVE)));

		  	Gasto gasto = CLGastos.buscarGastoConCodigo(Long.parseLong(sCodGastoB));

	    	logger.debug(gasto.logGasto());
	    	
	    	this.sFEDEVE = Utils.recuperaFecha(gasto.getFEDEVE());
	 
	    	this.setbDevolucion((Integer.parseInt(sCOSBGA) > 49));

			this.sDPTPAGO = gastoseleccionado.getDPTPAGO();

			this.sFFGTVP = Utils.recuperaFecha(gasto.getFFGTVP());
			
			//TODO sacar de datos de pago
			//this.sFEPAGA = Utils.recuperaFecha(gasto.getFEPAGA());
			this.sFELIPG = Utils.recuperaFecha(gasto.getFELIPG());

			this.sEstado = CLDescripciones.descripcionEstadoGasto(CLGastos.estadoGasto(Long.parseLong(sCodGastoB)));
			
			this.sFEEESI = Utils.recuperaFecha(gasto.getFEEESI());
			this.sFEECOI = Utils.recuperaFecha(gasto.getFEECOI());
			this.sFEEAUI = Utils.recuperaFecha(gasto.getFEEAUI());
			//this.sFEEPAI = Utils.recuperaFecha(gasto.getFEEPAI());
			this.sIMNGAS = Utils.recuperaImporte(gasto.getYCOS02().equals("-"),gasto.getIMNGAS());
			this.sIMRGAS = Utils.recuperaImporte(gasto.getYCOS04().equals("-"),gasto.getIMRGAS());
			this.sIMDGAS = Utils.recuperaImporte(gasto.getYCOS06().equals("-"),gasto.getIMDGAS());
			this.sIMCOST = Utils.recuperaImporte(gasto.getYCOS08().equals("-"),gasto.getIMCOST());
			this.sIMOGAS = Utils.recuperaImporte(gasto.getYCOS10().equals("-"),gasto.getIMOGAS());
			this.sIMDTGA = Utils.recuperaImporte(false,gasto.getIMDTGA());
			this.sIMIMGA = Utils.recuperaImporte(false,gasto.getIMIMGA());
			this.setsDCOIMPT(CLDescripciones.descripcionTipoImpuestoGasto(gasto.getCOIMPT()));
			
			//TODO sacar de datos de pago
			//this.sFEPGPR = Utils.recuperaFecha(gasto.getFEPGPR());
			
			this.sCOUNMO = ValoresDefecto.DEF_COUNMO;
			
			//TODO sacar de datos de pago
			//this.sCOENCX = ValoresDefecto.DEF_COENCX;
			//this.sCOOFCX = ValoresDefecto.DEF_COOFCX;
			//this.sNUCONE = ValoresDefecto.DEF_NUCONE;
			
			this.sNUPROF = CLGastos.buscarProvisionGasto(Integer.parseInt(sCOACES), sCOGRUG, sCOTPGA, sCOSBGA, gasto.getFEDEVE());

	    	
	    	sMsg = "Gasto cargado.";
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
			
			try
			{
				Comunidad comunidad = CLComunidades.buscarComunidad(Integer.parseInt(sCOACES));
				
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

				
				if (comunidad.getsNUDCOM().equals(""))
				{
					sMsg = "ERROR: El Activo '"+sCOACES+"' no esta asociado a ninguna comunidad.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else
				{
					sMsg = "La comunidad se ha cargado correctamente.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
				}
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
	
	public void pagar()
	{
		Pago pago = new Pago(sCodGastoB,ValoresDefecto.DEF_PAGO_SIMPLE, Utils.fechaDeHoy(false),"ES","",sNUCCEN,sNUCCOF,sNUCCDI,sNUCCNT);
		
		CLPagos.pagaGasto(pago);
	}
	
	public String cargarDetalles() 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
			if (gastoseleccionado != null)
			{
				this.sCOACES = gastoseleccionado.getCOACES();
		    	this.sCOGRUG = gastoseleccionado.getCOGRUG();
		    	this.sCOTPGA = gastoseleccionado.getCOTPGA();
		    	this.sCOSBGA = gastoseleccionado.getCOSBGA();
		    	this.sFEDEVE = gastoseleccionado.getFEDEVE();
		    	
				try
				{
					this.sCodGastoB = Long.toString(CLGastos.buscarCodigoGasto(Integer.parseInt(sCOACES),sCOGRUG,sCOTPGA,sCOSBGA,Utils.compruebaFecha(sFEDEVE)));
			    	
			    	logger.debug("sCodGasto:|"+sCodGastoB+"|");
			    	
			    	logger.debug("sCOACES:|"+sCOACES+"|");
			    	logger.debug("sCOGRUG:|"+sCOGRUG+"|");
			    	logger.debug("sCOTPGA:|"+sCOTPGA+"|");
			    	logger.debug("sCOSBGA:|"+sCOSBGA+"|");
			    	logger.debug("sFEDEVE:|"+sFEDEVE+"|");
			    	
			    	logger.debug("Redirigiendo...");

			    	sPagina = "detallesgasto.xhtml";
				}
				catch(NumberFormatException nfe)
				{
					FacesMessage msg;
					
					String sMsg = "ERROR: El activo debe ser numérico. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
		    	
		    	
			}
			else
			{
				FacesMessage msg;

				msg = Utils.pfmsgWarning("No se ha seleccionado ningún gasto.");
				
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}

		}

		return sPagina;
    }
	
	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
	}

	public boolean isbDevolucion() {
		return bDevolucion;
	}

	public void setbDevolucion(boolean bDevolucion) {
		this.bDevolucion = bDevolucion;
	}

	public String getsCOGRUG() {
		return sCOGRUG;
	}

	public void setsCOGRUG(String sCOGRUG) {
		this.sCOGRUG = sCOGRUG;
	}

	public String getsCOTPGA() {
		return sCOTPGA;
	}

	public void setsCOTPGA(String sCOTPGA) {
		this.sCOTPGA = sCOTPGA;
	}

	public String getsCOSBGA() {
		return sCOSBGA;
	}

	public void setsCOSBGA(String sCOSBGA) {
		this.sCOSBGA = sCOSBGA;
	}

	public String getsDCOSBGA() {
		return sDCOSBGA;
	}

	public void setsDCOSBGA(String sDCOSBGA) {
		this.sDCOSBGA = sDCOSBGA;
	}

	public String getsPTPAGO() {
		return sPTPAGO;
	}

	public void setsPTPAGO(String sPTPAGO) {
		this.sPTPAGO = sPTPAGO;
	}

	public String getsDPTPAGO() {
		return sDPTPAGO;
	}

	public void setsDPTPAGO(String sDPTPAGO) {
		this.sDPTPAGO = sDPTPAGO;
	}

	public String getsFEDEVE() {
		return sFEDEVE;
	}

	public void setsFEDEVE(String sFEDEVE) {
		this.sFEDEVE = sFEDEVE;
	}

	public String getsFFGTVP() {
		return sFFGTVP;
	}

	public void setsFFGTVP(String sFFGTVP) {
		this.sFFGTVP = sFFGTVP;
	}

	public String getsFELIPG() {
		return sFELIPG;
	}

	public void setsFELIPG(String sFELIPG) {
		this.sFELIPG = sFELIPG;
	}

	public String getsCOSIGA() {
		return sCOSIGA;
	}

	public void setsCOSIGA(String sCOSIGA) {
		this.sCOSIGA = sCOSIGA;
	}

	public String getsEstado() {
		return sEstado;
	}

	public void setsEstado(String sEstado) {
		this.sEstado = sEstado;
	}

	public String getsFEEESI() {
		return sFEEESI;
	}

	public void setsFEEESI(String sFEEESI) {
		this.sFEEESI = sFEEESI;
	}

	public String getsFEECOI() {
		return sFEECOI;
	}

	public void setsFEECOI(String sFEECOI) {
		this.sFEECOI = sFEECOI;
	}

	public String getsFEEAUI() {
		return sFEEAUI;
	}

	public void setsFEEAUI(String sFEEAUI) {
		this.sFEEAUI = sFEEAUI;
	}

	public String getsIMNGAS() {
		return sIMNGAS;
	}

	public void setsIMNGAS(String sIMNGAS) {
		this.sIMNGAS = sIMNGAS;
	}

	public String getsYCOS02() {
		return sYCOS02;
	}

	public void setsYCOS02(String sYCOS02) {
		this.sYCOS02 = sYCOS02;
	}

	public String getsIMRGAS() {
		return sIMRGAS;
	}

	public void setsIMRGAS(String sIMRGAS) {
		this.sIMRGAS = sIMRGAS;
	}

	public String getsYCOS04() {
		return sYCOS04;
	}

	public void setsYCOS04(String sYCOS04) {
		this.sYCOS04 = sYCOS04;
	}

	public String getsIMDGAS() {
		return sIMDGAS;
	}

	public void setsIMDGAS(String sIMDGAS) {
		this.sIMDGAS = sIMDGAS;
	}

	public String getsYCOS06() {
		return sYCOS06;
	}

	public void setsYCOS06(String sYCOS06) {
		this.sYCOS06 = sYCOS06;
	}

	public String getsIMCOST() {
		return sIMCOST;
	}

	public void setsIMCOST(String sIMCOST) {
		this.sIMCOST = sIMCOST;
	}

	public String getsYCOS08() {
		return sYCOS08;
	}

	public void setsYCOS08(String sYCOS08) {
		this.sYCOS08 = sYCOS08;
	}

	public String getsIMOGAS() {
		return sIMOGAS;
	}

	public void setsIMOGAS(String sIMOGAS) {
		this.sIMOGAS = sIMOGAS;
	}

	public String getsYCOS10() {
		return sYCOS10;
	}

	public void setsYCOS10(String sYCOS10) {
		this.sYCOS10 = sYCOS10;
	}

	public String getsIMDTGA() {
		return sIMDTGA;
	}

	public void setsIMDTGA(String sIMDTGA) {
		this.sIMDTGA = sIMDTGA;
	}

	public String getsCOUNMO() {
		return sCOUNMO;
	}

	public void setsCOUNMO(String sCOUNMO) {
		this.sCOUNMO = sCOUNMO;
	}

	public String getsIMIMGA() {
		return sIMIMGA;
	}

	public void setsIMIMGA(String sIMIMGA) {
		this.sIMIMGA = sIMIMGA;
	}

	public boolean isbIMIMGA() {
		return bIMIMGA;
	}

	public void setbIMIMGA(boolean bIMIMGA) {
		this.bIMIMGA = bIMIMGA;
	}

	public String getsCOIMPT() {
		return sCOIMPT;
	}

	public void setsCOIMPT(String sCOIMPT) {
		this.sCOIMPT = sCOIMPT;
	}

	public String getsDCOIMPT() {
		return sDCOIMPT;
	}

	public void setsDCOIMPT(String sDCOIMPT) {
		this.sDCOIMPT = sDCOIMPT;
	}

	public String getsNUPROF() {
		return sNUPROF;
	}

	public void setsNUPROF(String sNUPROF) {
		this.sNUPROF = sNUPROF;
	}

	public String getsCOACESB() {
		return sCOACESB;
	}

	public void setsCOACESB(String sCOACESB) {
		this.sCOACESB = sCOACESB;
	}

	public String getsNUPROFB() {
		return sNUPROFB;
	}

	public void setsNUPROFB(String sNUPROFB) {
		this.sNUPROFB = sNUPROFB;
	}

	public String getsCOGRUGB() {
		return sCOGRUGB;
	}

	public void setsCOGRUGB(String sCOGRUGB) {
		this.sCOGRUGB = sCOGRUGB;
	}

	public String getsCOTPGAB() {
		return sCOTPGAB;
	}

	public void setsCOTPGAB(String sCOTPGAB) {
		this.sCOTPGAB = sCOTPGAB;
	}

	public String getsCOSBGAB() {
		return sCOSBGAB;
	}

	public void setsCOSBGAB(String sCOSBGAB) {
		this.sCOSBGAB = sCOSBGAB;
	}

	public String getsFEDEVEB() {
		return sFEDEVEB;
	}

	public void setsFEDEVEB(String sFEDEVEB) {
		this.sFEDEVEB = sFEDEVEB;
	}

	public String getsCodGastoB() {
		return sCodGastoB;
	}

	public void setsCodGastoB(String sCodGastoB) {
		this.sCodGastoB = sCodGastoB;
	}

	public String getsCOPOINB() {
		return sCOPOINB;
	}

	public void setsCOPOINB(String sCOPOINB) {
		this.sCOPOINB = sCOPOINB;
	}

	public String getsNOMUINB() {
		return sNOMUINB;
	}

	public void setsNOMUINB(String sNOMUINB) {
		this.sNOMUINB = sNOMUINB;
	}

	public String getsNOPRACB() {
		return sNOPRACB;
	}

	public void setsNOPRACB(String sNOPRACB) {
		this.sNOPRACB = sNOPRACB;
	}

	public String getsNOVIASB() {
		return sNOVIASB;
	}

	public void setsNOVIASB(String sNOVIASB) {
		this.sNOVIASB = sNOVIASB;
	}

	public String getsNUPIACB() {
		return sNUPIACB;
	}

	public void setsNUPIACB(String sNUPIACB) {
		this.sNUPIACB = sNUPIACB;
	}

	public String getsNUPOACB() {
		return sNUPOACB;
	}

	public void setsNUPOACB(String sNUPOACB) {
		this.sNUPOACB = sNUPOACB;
	}

	public String getsNUPUACB() {
		return sNUPUACB;
	}

	public void setsNUPUACB(String sNUPUACB) {
		this.sNUPUACB = sNUPUACB;
	}

	public String getsFEPFONB() {
		return sFEPFONB;
	}

	public void setsFEPFONB(String sFEPFONB) {
		this.sFEPFONB = sFEPFONB;
	}

	public GastoTabla getGastoseleccionado() {
		return gastoseleccionado;
	}

	public void setGastoseleccionado(GastoTabla gastoseleccionado) {
		this.gastoseleccionado = gastoseleccionado;
	}

	public ArrayList<GastoTabla> getTablagastos() {
		return tablagastos;
	}

	public void setTablagastos(ArrayList<GastoTabla> tablagastos) {
		this.tablagastos = tablagastos;
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

	public ProvisionTabla getProvisionseleccionada() {
		return provisionseleccionada;
	}

	public void setProvisionseleccionada(ProvisionTabla provisionseleccionada) {
		this.provisionseleccionada = provisionseleccionada;
	}

	public ArrayList<ProvisionTabla> getTablaprovisiones() {
		return tablaprovisiones;
	}

	public void setTablaprovisiones(ArrayList<ProvisionTabla> tablaprovisiones) {
		this.tablaprovisiones = tablaprovisiones;
	}
	
}
