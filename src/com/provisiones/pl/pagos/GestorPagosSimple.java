package com.provisiones.pl.pagos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLActivos;
import com.provisiones.ll.CLComunidades;
import com.provisiones.ll.CLCuentas;
import com.provisiones.ll.CLDescripciones;
import com.provisiones.ll.CLGastos;
import com.provisiones.ll.CLPagos;
import com.provisiones.ll.CLProvisiones;
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Cuenta;
import com.provisiones.types.Gasto;
import com.provisiones.types.Pago;
import com.provisiones.types.Provision;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.GastoTabla;
import com.provisiones.types.tablas.ProvisionTabla;

public class GestorPagosSimple implements Serializable 
{

	private static final long serialVersionUID = 649592960198529152L;

	private static Logger logger = LoggerFactory.getLogger(GestorPagosSimple.class.getName());

	//Buscar Activo
	private String sCOACESB = "";

	//Filtro Gasto Activo
	private String sCOGRUGBA = "";
	private String sCOTPGABA = "";
	private String sCOSBGABA = "";
	private String sFEDEVEBA = "";
	
	//Filtro Activo
	private String sCOPOINB = "";
	private String sNOMUINB = "";
	private String sNOPRACB = "";
	private String sNOVIASB = "";
	private String sNUPIACB = "";
	private String sNUPOACB = "";
	private String sNUPUACB = "";
	
	private String sNURCATB = "";
	

	//Buscar Provision
	private String sNUPROFB = "";

	//Filtro Provision
	private String sFEPFONB = "";

	//Progreso de Pago
	private String sGastosPorPagar = "";
	private String sValorPorPagar = "";
	private String sFechaLimite = "";
	
	//Filtro Gasto provision
	private String sCOGRUGBP = "";
	private String sCOTPGABP = "";
	private String sCOSBGABP = "";
	private String sFEDEVEBP = "";
	private String sCOACESBP = "";
	
	//Gasto Buscado
	private String sCodGastoB = "";
	
	
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
	
	private String sFEPGPR = "";
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
	
	private String sTipoPago = "";

	//Cuenta
	private String sPais = "";	
	private String sDCIBAN = "";
	private String sNUCCEN = "";
	private String sNUCCOF = "";
	private String sNUCCDI = "";
	private String sNUCCNT = "";
	
	private String sDescripcion = "";
	
	private Map<String,String> tiposcogrugHM = new LinkedHashMap<String, String>();

	private Map<String,String> tiposcotpgaHMA = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbgaHMA = new LinkedHashMap<String, String>();

	private Map<String,String> tiposcotpgaHMP = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbgaHMP = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposcotpga_g1HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcotpga_g2HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcotpga_g3HM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposcosbga_t11HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbga_t12HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbga_t21HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbga_t22HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbga_t23HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbga_t32HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbga_t33HM = new LinkedHashMap<String, String>();

	private transient ActivoTabla activoseleccionado = null;
	private transient ArrayList<ActivoTabla> tablaactivos = null;

	private transient ProvisionTabla provisionseleccionada = null;
	private transient ArrayList<ProvisionTabla> tablaprovisiones = null;

	private transient GastoTabla gastoactivoseleccionado = null;
	private transient ArrayList<GastoTabla> tablagastosactivo = null;
	
	private transient GastoTabla gastoprovisionseleccionado = null;
	private transient ArrayList<GastoTabla> tablagastosprovision = null;
	
	private transient Cuenta cuentaactivoseleccionada = null;
	private transient ArrayList<Cuenta> tablacuentasactivo = null;

	private transient Cuenta cuentacomunidadseleccionada = null;
	private transient ArrayList<Cuenta> tablacuentascomunidad = null;

	public GestorPagosSimple()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorPagosSimple...");

			tiposcogrugHM.put("Compraventa",      "1");
			tiposcogrugHM.put("Pendientes",       "2");
			tiposcogrugHM.put("Acciones",         "3");

			tiposcotpga_g1HM.put("Plusvalia", "1");
			tiposcotpga_g1HM.put("Notaria",   "2");

			tiposcotpga_g2HM.put("Tasas-Impuestos", "1");
			tiposcotpga_g2HM.put("Comunidades",     "2");
			tiposcotpga_g2HM.put("Suministros",     "3");
			
			tiposcotpga_g3HM.put("Honorarios","2");
			tiposcotpga_g3HM.put("Licencias", "3");
			
			tiposcosbga_t11HM.put("Plusvalia", "0");
			tiposcosbga_t12HM.put("Notaria",   "1");

			tiposcosbga_t21HM.put("Impuestos e IBIS",                     "0");
			tiposcosbga_t21HM.put("IBIS",                                 "1");
			tiposcosbga_t21HM.put("Tasas basura",                         "2");
			tiposcosbga_t21HM.put("Tasas alcantarillado",                 "3");
			tiposcosbga_t21HM.put("Tasas agua",                           "4");
			tiposcosbga_t21HM.put("Contribuciones especiales",            "5");
			tiposcosbga_t21HM.put("Otras tasas",                          "6");
			
			tiposcosbga_t22HM.put("Comunidad",	                   	"0");  
			tiposcosbga_t22HM.put("Ordinaria",                     	"1");  
			tiposcosbga_t22HM.put("Extras Comunidad",              	"2");  
			tiposcosbga_t22HM.put("Mancomunidad",                  	"3");  
			tiposcosbga_t22HM.put("Extras Mancomunidad",           	"4");  
			tiposcosbga_t22HM.put("Obras comunidad",               	"5");  
			
			tiposcosbga_t23HM.put("Suministros",               "0");
			tiposcosbga_t23HM.put("Suministro luz",            "1");
			tiposcosbga_t23HM.put("Suministro agua",           "2");
			tiposcosbga_t23HM.put("Suministro gas",            "3");
			
			tiposcosbga_t32HM.put("Honorarios Colaboradores","0");  
			tiposcosbga_t32HM.put("Prescripcion",            "1");  
			tiposcosbga_t32HM.put("Colaboracion",            "2");  
			tiposcosbga_t32HM.put("Otros honorarios",        "3");  
			tiposcosbga_t32HM.put("Servicios varios",        "4");
			
			tiposcosbga_t33HM.put("Obtencion de Licencias", "0");
		}
	}
	
	public void borrarCamposBuscar()
	{
		this.sCOACESB = "";
    	this.sNUPROFB = "";
    	
    	this.setGastoactivoseleccionado(null);
    	this.setGastoprovisionseleccionado(null);
    	this.setTablagastosactivo(null);
    	this.setTablagastosprovision(null);
	}
	
	public void borrarCamposBuscarActivo()
	{
		this.sCOPOINB = "";
		this.sNOMUINB = "";
		this.sNOPRACB = "";
		this.sNOVIASB = "";
		this.sNUPIACB = "";
		this.sNUPOACB = "";
		this.sNUPUACB = "";
		
		this.sNURCATB = "";
    	
    	this.setActivoseleccionado(null);
    	this.setTablaactivos(null);
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	borrarCamposBuscarActivo();
    }
    
    
	public void borrarCamposBuscarProvision()
	{
		this.sFEPFONB = "";
    	
    	this.setProvisionseleccionada(null);
    	this.setTablaprovisiones(null);
	}
	
    public void limpiarPlantillaProvision(ActionEvent actionEvent) 
    {  
    	borrarCamposBuscarProvision();
    }
    
	public void borrarCamposBuscarGastoActivo()
	{
		this.sCOGRUGBA = "";
		this.sCOTPGABA = "";
		this.sCOSBGABA = "";
		this.sFEDEVEBA = "";
		
		cambiaGrupoActivo();
	
    	this.setGastoactivoseleccionado(null);

    	this.setTablagastosactivo(null);
	}
	
    public void limpiarPlantillaBuscarGastoActivo(ActionEvent actionEvent) 
    {  
    	borrarCamposBuscarGastoActivo();
    }
    
    
	public void borrarCamposBuscarGastoProvision()
	{
		this.sCOGRUGBP = "";
		this.sCOTPGABP = "";
		this.sCOSBGABP = "";
		this.sFEDEVEBP = "";
	
		this.sCOACESBP = "";
		
		cambiaGrupoProvision();
		
    	this.setGastoprovisionseleccionado(null);
    	this.setTablagastosprovision(null);
	}
	
    public void limpiarPlantillaBuscarGastoProvision(ActionEvent actionEvent) 
    {  
    	borrarCamposBuscarGastoProvision();
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

		this.sNUPROF = "";

	}

	public void borrarCamposCuenta()
	{

		this.sPais = "";
		this.sDCIBAN = "";
		this.sNUCCEN = "";
		this.sNUCCOF = "";
		this.sNUCCDI = "";
		this.sNUCCNT = "";
		this.sDescripcion = "";
		
		this.sTipoPago = "";

	}
	
	
    public void limpiarPlantillaCuenta(ActionEvent actionEvent) 
    {  
    	borrarCamposCuenta();
    }
    public void borrarCamposProgreso()
    {
    	this.sGastosPorPagar = "";
    	this.sValorPorPagar = "";
    	this.sFechaLimite = "";
    }
	
	
	public void borrarCamposPago()
	{
		this.sFEPGPR = "";
		
		borrarCamposCuenta();

	}
	
	
    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	borrarCamposBuscar();
    	borrarCamposBuscarActivo();
    	borrarCamposBuscarProvision();
    	borrarCamposBuscarGastoActivo();
    	borrarCamposBuscarGastoProvision();
    	borrarCamposProgreso();
    	borrarCamposPago();
    	borrarCamposGasto();
    }
    
	public void cambiaGrupoActivo()
	{
		tiposcotpgaHMA = new LinkedHashMap<String, String>();
		tiposcosbgaHMA = new LinkedHashMap<String, String>();
	}
	
	public void cambiaTipoActivo()
	{

		logger.debug("sCOGRUGBA:|"+sCOGRUGBA+"|");

		if (sCOGRUGBA !=null && !sCOGRUGBA.isEmpty())
		{
			switch (Integer.parseInt(sCOGRUGBA)) 
			{
				case 1:
					tiposcotpgaHMA = tiposcotpga_g1HM;
					break;
				case 2:
					tiposcotpgaHMA = tiposcotpga_g2HM;
					break;
				case 3:
					tiposcotpgaHMA = tiposcotpga_g3HM;
					break;
				default:
					tiposcotpgaHMA = new LinkedHashMap<String, String>();
					break;
			}
			tiposcosbgaHMA = new LinkedHashMap<String, String>();
			sCOTPGABA = "";
			sCOSBGABA = "";
		}
	}
	
	public void cambiaSubtipoActivo()
	{
		logger.debug("sCOTPGABA:|"+sCOGRUGBA+"| sCOTPGABA:|"+sCOTPGABA+"|");
		
		if (sCOTPGABA !=null && !sCOTPGABA.isEmpty())
		{
			switch (Integer.parseInt(sCOGRUGBA+sCOTPGABA)) 
			{
				case 11:
					tiposcosbgaHMA = tiposcosbga_t11HM;
					break;
				case 12:
					tiposcosbgaHMA = tiposcosbga_t12HM;
					break;
				case 21:
					tiposcosbgaHMA = tiposcosbga_t21HM;
					break;
				case 22:
					tiposcosbgaHMA = tiposcosbga_t22HM;
					break;
				case 23:
					tiposcosbgaHMA = tiposcosbga_t23HM;
					break;
				case 32:
					tiposcosbgaHMA = tiposcosbga_t32HM;
					break;
				case 33:
					tiposcosbgaHMA = tiposcosbga_t33HM;
					break;
				default:
					tiposcosbgaHMA = new LinkedHashMap<String, String>();
					break;
			}
			sCOSBGABA = "";
		}
	}
	
	public void cambiaGrupoProvision()
	{
		tiposcotpgaHMP = new LinkedHashMap<String, String>();
		tiposcosbgaHMP = new LinkedHashMap<String, String>();
	}
	
	public void cambiaTipoProvision()
	{

		logger.debug("sCOGRUGB:|"+sCOGRUGBP+"|");

		if (sCOGRUGBP !=null && !sCOGRUGBP.isEmpty())
		{
			switch (Integer.parseInt(sCOGRUGBP)) 
			{
				case 1:
					tiposcotpgaHMP = tiposcotpga_g1HM;
					break;
				case 2:
					tiposcotpgaHMP = tiposcotpga_g2HM;
					break;
				case 3:
					tiposcotpgaHMP = tiposcotpga_g3HM;
					break;
				default:
					tiposcotpgaHMP = new LinkedHashMap<String, String>();
					break;
			}
			tiposcosbgaHMP = new LinkedHashMap<String, String>();
			sCOTPGABP = "";
			sCOSBGABP = "";
		}
	}
	
	public void cambiaSubtipoProvision()
	{
		logger.debug("sCOTPGABP:|"+sCOGRUGBP+"| sCOTPGABP:|"+sCOTPGABP+"|");
		
		if (sCOTPGABP !=null && !sCOTPGABP.isEmpty())
		{
			switch (Integer.parseInt(sCOGRUGBP+sCOTPGABP)) 
			{
				case 11:
					tiposcosbgaHMP = tiposcosbga_t11HM;
					break;
				case 12:
					tiposcosbgaHMP = tiposcosbga_t12HM;
					break;
				case 21:
					tiposcosbgaHMP = tiposcosbga_t21HM;
					break;
				case 22:
					tiposcosbgaHMP = tiposcosbga_t22HM;
					break;
				case 23:
					tiposcosbgaHMP = tiposcosbga_t23HM;
					break;
				case 32:
					tiposcosbgaHMP = tiposcosbga_t32HM;
					break;
				case 33:
					tiposcosbgaHMP = tiposcosbga_t33HM;
					break;
				default:
					tiposcosbgaHMP = new LinkedHashMap<String, String>();
					break;
			}
			sCOSBGABP = "";
		}
	}
	
	public void hoyFEDEVEBA (ActionEvent actionEvent)
	{
		this.setsFEDEVEBA(Utils.fechaDeHoy(true));
		logger.debug("sFEDEVEBA:|"+sFEDEVEBA+"|");
	}
	
	public void hoyFEDEVEBP (ActionEvent actionEvent)
	{
		this.setsFEDEVEBP(Utils.fechaDeHoy(true));
		logger.debug("sFEDEVEBP:|"+sFEDEVEBP+"|");
	}
    
	public void hoyFEPGPR (ActionEvent actionEvent)
	{
		this.setsFEPGPR(Utils.fechaDeHoy(true));
		logger.debug("sFEPGPR:|"+sFEPGPR+"|");
	}
    
	public void buscarActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.activoseleccionado = null;
			
			if (sNURCATB.isEmpty())
			{
				ActivoTabla filtro = new ActivoTabla(
						"", sCOPOINB.toUpperCase(), sNOMUINB.toUpperCase(),
						sNOPRACB.toUpperCase(), sNOVIASB.toUpperCase(), sNUPIACB.toUpperCase(), 
						sNUPOACB.toUpperCase(), sNUPUACB.toUpperCase(), "");
				
				this.setTablaactivos(CLGastos.buscarActivosConGastosAutorizados(filtro));
				
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

			}
			else if (CLReferencias.existeReferenciaCatastral(sNURCATB))
			{
				this.setTablaactivos(CLReferencias.buscarActivoAsociadoConGastosAutorizados(sNURCATB));
				
				if (getTablaactivos().size() == 0)
				{
					sMsg = "No se encontraron Activos con los criterios solicitados.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);
				}
				else
				{
					sMsg = "Encontrado un Activo relacionado.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
				}
			}
			else
			{
		    	this.tablaactivos = null;
				
				sMsg = "La Referencia Catastral informada no se encuentrar registrada en el sistema. Por favor, revise los datos.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
			}
		
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
			
			
			this.setProvisionseleccionada(null);
			
			if (sFecha.equals("#"))
			{
				sMsg = "La fecha proporcionada no es válida. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
				
		    	this.setTablaprovisiones(null);
			}
			else
			{
				String sNUPROFF = "";
				String sCOSPATF = "";
				String sDCOSPATF = "";
				String sTASF = "";
				String sDTASF = "";	
				String sCOGRUGF = "";
				String sDCOGRUGF = "";	
				String sCOTPGAF = "";
				String sDCOTPGAF = "";
				String sFEPFONF = sFecha;
				String sVALORF = "";
				String sGASTOSF = "";
				String sESTADOF = "";
				
				ProvisionTabla filtro = new ProvisionTabla(
						sNUPROFF, 
						sCOSPATF, 
						sDCOSPATF,
						sTASF, 
						sDTASF, 
						sCOGRUGF, 
						sDCOGRUGF, 
						sCOTPGAF, 
						sDCOTPGAF, 
						sFEPFONF, 
						sVALORF, 
						sGASTOSF,
						sESTADOF);				

				this.setTablaprovisiones(CLProvisiones.buscarProvisionesAutorizadasConFiltro(filtro));

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

			String sMsg = "";
			
			this.gastoactivoseleccionado = null;
			
			this.setTablagastosactivo(null);
			
			try
			{
				if (sCOACESB.isEmpty())
				{
					sMsg = "No se informó el campo 'Activo'. Por favor, revise los datos.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);
				}
				else if (CLActivos.existeActivo(Integer.parseInt(sCOACESB)))
				{
					GastoTabla filtro = new GastoTabla(
							"",
							"",   
							sCOACESB,   
							sCOGRUGBA,   
							sCOTPGABA,   
							sCOSBGABA,   
							"",  
							"",   
							"",  
							Utils.compruebaFecha(sFEDEVEBA),   
							"",   
							"",  
							"",
							"",
							"",//TODO meter estado en el filtro
							"");
					
					
					
					this.setTablagastosactivo(CLGastos.buscarGastosAutorizadosActivoConFiltro(filtro));
					
					if (getTablagastosactivo().size() == 0)
					{
						sMsg = "No se encontraron Gastos con los criterios solicitados.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);
					}
					else if (getTablagastosactivo().size() == 1)
					{
						sMsg = "Encontrado un Gasto relacionado.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
					else
					{
						sMsg = "Encontrados "+getTablagastosactivo().size()+" Gastos relacionados.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}

				}
				else
				{
					sMsg = "El Activo '"+sCOACES+"' no pertenece a la cartera. Por favor, revise los datos.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);
				}

			}
			catch(NumberFormatException nfe)
			{
				sMsg = "ERROR: El Activo debe ser numérico. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
    
	public void buscarGastosProvision (ActionEvent actionEvent)
	{

		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.gastoprovisionseleccionado = null;
			
			this.setTablagastosprovision(null);
			
			try
			{
				Long.parseLong(sNUPROFB);
				
				if (sNUPROFB.isEmpty())
				{
					sMsg = "No se informó el campo 'Provisión'. Por favor, revise los datos.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);

				}
				else if (CLProvisiones.existeProvision(sNUPROFB))
				{
					if (!CLProvisiones.estaPagada(sNUPROFB))
					{
						GastoTabla filtro = new GastoTabla(
								"",
								sNUPROFB,   
								sCOACESBP,   
								sCOGRUGBP,   
								sCOTPGABP,   
								sCOSBGABP,   
								"",  
								"",   
								"",  
								Utils.compruebaFecha(sFEDEVEBP),   
								"",   
								"",  
								"",
								"",
								"",//TODO meter estado en el filtro
								"");
						
						this.setTablagastosprovision(CLGastos.buscarGastosAutorizadosProvisionConFiltro(filtro));
						
					}
					
					if (getTablagastosprovision().size() == 0)
					{
						sMsg = "No se encontraron Gastos con los criterios solicitados.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);
					}
					else if (getTablagastosprovision().size() == 1)
					{
						sMsg = "Encontrado un Gasto relacionado.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
					else
					{
						sMsg = "Encontrados "+getTablagastosprovision().size()+" Gastos relacionados.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
					
				}
				else
				{
					sMsg = "La Provisión '"+sNUPROFB+"' no se encuentra regristada en el sistema. Por favor, revise los datos.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);
				}
			}
			catch(NumberFormatException nfe)
			{
				sMsg = "ERROR: La Provisión debe ser numérica. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}


			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	public void seleccionarGastoActivo(ActionEvent actionEvent) 
    { 
		if (ConnectionManager.comprobarConexion())
		{
	    	if (gastoactivoseleccionado!=null)
	    	{
	    		
		    	logger.debug("sCOACES:|"+gastoactivoseleccionado.getCOACES()+"|");
		    	logger.debug("sCOGRUG:|"+gastoactivoseleccionado.getCOGRUG()+"|");
		    	logger.debug("sCOTPGA:|"+gastoactivoseleccionado.getCOTPGA()+"|");
		    	logger.debug("sCOSBGA:|"+gastoactivoseleccionado.getCOSBGA()+"|");
		    	logger.debug("sFEDEVE:|"+gastoactivoseleccionado.getFEDEVE()+"|");

		    	cargarGasto(gastoactivoseleccionado);
	    	}
	    	else
	    	{
		    	FacesMessage msg;
		    	
		    	String sMsg = "";
	    		
		    	sMsg = "ERROR: Ocurrio un problema al seleccionar el Gasto del Activo. Por favor, revise los datos y avise a soporte.";
		    	msg = Utils.pfmsgError(sMsg);
		    	logger.error(sMsg);
		    	
		    	FacesContext.getCurrentInstance().addMessage(null, msg);
	    	}
		}
    }

	public void seleccionarGastoProvision(ActionEvent actionEvent) 
    { 
		if (ConnectionManager.comprobarConexion())
		{
	    	if (gastoprovisionseleccionado!=null)
	    	{
		    	logger.debug("sCOACES:|"+gastoprovisionseleccionado.getCOACES()+"|");
		    	logger.debug("sCOGRUG:|"+gastoprovisionseleccionado.getCOGRUG()+"|");
		    	logger.debug("sCOTPGA:|"+gastoprovisionseleccionado.getCOTPGA()+"|");
		    	logger.debug("sCOSBGA:|"+gastoprovisionseleccionado.getCOSBGA()+"|");
		    	logger.debug("sFEDEVE:|"+gastoprovisionseleccionado.getFEDEVE()+"|");
	    		cargarGasto(gastoprovisionseleccionado);
	    	}
	    	else
	    	{
		    	FacesMessage msg;
		    	
		    	String sMsg = "";
	    		
		    	sMsg = "ERROR: Ocurrio un problema al seleccionar el Gasto de la Provisión. Por favor, revise los datos y avise a soporte.";
		    	msg = Utils.pfmsgError(sMsg);
		    	logger.error(sMsg);
		    	
		    	FacesContext.getCurrentInstance().addMessage(null, msg);
	    	}
		}
    }

	
	
	public void cargarGasto(GastoTabla gastoseleccionado) 
    { 
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	String sMsg = "";
	    	
	    	borrarCamposGasto();
	    	borrarCamposProgreso();
	    	
	    	if (gastoseleccionado != null)
	    	{
				this.sCOACES = gastoseleccionado.getCOACES();
				
				//Cargar Comunidad
				
				//Cargar Gasto
		    	this.sCOGRUG = gastoseleccionado.getCOGRUG();
		    	this.sCOTPGA = gastoseleccionado.getCOTPGA();
		    	this.sCOSBGA = gastoseleccionado.getCOSBGA();
		    	this.sFEDEVE = gastoseleccionado.getFEDEVE();
		    	
		    	logger.debug("sCOACES:|"+sCOACES+"|");
		    	logger.debug("sCOGRUG:|"+sCOGRUG+"|");
		    	logger.debug("sCOTPGA:|"+sCOTPGA+"|");
		    	logger.debug("sCOSBGA:|"+sCOSBGA+"|");
		    	logger.debug("sFEDEVE:|"+sFEDEVE+"|");
		    	
		    	
		    	this.sDCOSBGA = gastoseleccionado.getDCOSBGA();
		    	
		    	
		    	this.sCodGastoB = Long.toString(CLGastos.buscarCodigoGasto(Integer.parseInt(sCOACES),sCOGRUG,sCOTPGA,sCOSBGA,Utils.compruebaFecha(sFEDEVE)));

			  	Gasto gasto = CLGastos.buscarGastoConCodigo(Long.parseLong(sCodGastoB));

		    	logger.debug(gasto.logGasto());
		    	
		    	this.sFEDEVE = Utils.recuperaFecha(gasto.getFEDEVE());
		 
		    	this.setbDevolucion((Integer.parseInt(sCOSBGA) > 49));
		    	
		    	if (bDevolucion)
		    	{
		    		this.sPais = "ES";
		    		this.sDCIBAN = "00";
		    		this.sNUCCEN = "0000";
		    		this.sNUCCOF = "0000";
		    		this.sNUCCDI = "00";
		    		this.sNUCCNT = "0000000000";
		    		
		    		this.setsDescripcion("DEVOLUCION");
		    		
		    		this.sTipoPago = ValoresDefecto.DEF_PAGO_DEVOLUCION;
		    	}
		    	else
		    	{
		    		borrarCamposCuenta();
		    	}

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
				actualizaProgreso();
				
		    	sMsg = "Gasto cargado.";
		    	msg = Utils.pfmsgInfo(sMsg);
		    	
		    	logger.info(sMsg);
	    	}
	    	else
	    	{
	    		sMsg = "ERROR: Ocurrio un error al cargar los datos del Gasto. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
	    	}
	    	
  			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void actualizaProgreso()
	{
		Provision provision = CLProvisiones.buscarDetallesProvision(sNUPROF);

    	this.sGastosPorPagar = Long.toString(Long.parseLong(provision.getsGastosAutorizados()) - Long.parseLong(provision.getsGastosPagados()));
    	this.sValorPorPagar = Utils.recuperaImporte(false,Long.toString(Long.parseLong(provision.getsValorAutorizado()) - Long.parseLong(provision.getsValorPagado())));
    	this.sFechaLimite = CLProvisiones.buscarPrimeraFechaLimitePago(sNUPROF);
	}
	
	public void buscaCuentas (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.cuentacomunidadseleccionada = null;
			
			if (sCOACES.isEmpty())
			{
				sMsg = "ERROR: Debe haber cargado un gasto para realizar una búsqueda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
				
		    	this.setTablacuentascomunidad(null);
			}
			else
			{
				try
				{
					this.setTablacuentasactivo(CLCuentas.buscarCuentasActivo(Integer.parseInt(sCOACES)));
					
					if (getTablacuentasactivo().size() == 0)
					{
						sMsg = "No se encontraron Cuentas con los criterios solicitados.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);
						
					}
					else if (getTablacuentasactivo().size() == 1)
					{
						sMsg = "Encontrada una Cuenta del Activo.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
					else
					{
						sMsg = "Encontradas "+getTablacuentasactivo().size()+" Cuentas del Activo.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
					
					FacesContext.getCurrentInstance().addMessage(null, msg);
					
					int iCOACES = Integer.parseInt(sCOACES);
					
					if (CLComunidades.esActivoVinculadoAComunidad(iCOACES))
					{
						long liCodComunidad = CLComunidades.buscarCodigoComunidadDeActivo(iCOACES);
						
						this.setTablacuentascomunidad(CLCuentas.buscarCuentasComunidad(liCodComunidad));
						
						if (getTablacuentascomunidad().size() == 0)
						{
							sMsg = "ERROR: No se encontraron Cuentas asociadas a la Comunidad. Por favor, revise los datos y avise a soporte.";
							msg = Utils.pfmsgFatal(sMsg);
							logger.error(sMsg);
							
						}
						else if (getTablacuentascomunidad().size() == 1)
						{
							sMsg = "Encontrada una Cuenta de la Comunidad.";
							msg = Utils.pfmsgInfo(sMsg);
							logger.info(sMsg);
						}
						else
						{
							sMsg = "Encontradas "+getTablacuentascomunidad().size()+" Cuentas de la Comunidad.";
							msg = Utils.pfmsgInfo(sMsg);
							logger.info(sMsg);
						}						
					}
					else
					{
						sMsg = "El Activo no esta asociado a una comunidad.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);
						
						this.setTablacuentascomunidad(null);
					}

					
				}
				catch(NumberFormatException nfe)
				{
					sMsg = "ERROR: El Activo debe ser numérico. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					
					this.setTablacuentascomunidad(null);
				}
			}
			

			

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	public void seleccionarPagoPorVentanilla(ActionEvent actionEvent)
	{
		this.sPais = "ES";
		this.sDCIBAN = "00";
		this.sNUCCEN = "0000";
		this.sNUCCOF = "0000";
		this.sNUCCDI = "00";
		this.sNUCCNT = "0000000000";
		
		this.setsDescripcion("POR VENTANILLA");
		
		this.sTipoPago = ValoresDefecto.DEF_PAGO_VENTANILLA;
	}
	
	
	public void seleccionarCuentaActivo(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
		
			String sMsg = "";
			
			if (cuentaactivoseleccionada == null)
			{
				logger.debug("NULACO!!");
			}
			
	    	logger.debug("getsPais:|"+cuentaactivoseleccionada.getsPais()+"|");
	    	logger.debug("getsDCIBAN:|"+cuentaactivoseleccionada.getsDCIBAN()+"|");
	    	logger.debug("getsNUCCEN:|"+cuentaactivoseleccionada.getsNUCCEN()+"|");
	    	logger.debug("getsNUCCOF:|"+cuentaactivoseleccionada.getsNUCCOF()+"|");
	    	logger.debug("getsNUCCDI:|"+cuentaactivoseleccionada.getsNUCCDI()+"|");
	    	logger.debug("getsNUCCNT:|"+cuentaactivoseleccionada.getsNUCCNT()+"|");
			
			this.sPais = cuentaactivoseleccionada.getsPais();
			this.sDCIBAN = cuentaactivoseleccionada.getsDCIBAN();
			this.sNUCCEN = cuentaactivoseleccionada.getsNUCCEN();
			this.sNUCCOF = cuentaactivoseleccionada.getsNUCCOF();
			this.sNUCCDI = cuentaactivoseleccionada.getsNUCCDI();
			this.sNUCCNT = cuentaactivoseleccionada.getsNUCCNT();
			
			this.setsDescripcion(cuentaactivoseleccionada.getsDescripcion());
			
			sMsg = "Cuenta cargada.";
			msg = Utils.pfmsgInfo(sMsg);
			logger.info(sMsg);
	

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}				
	}
	
	public void seleccionarCuentaComunidad(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
		
			String sMsg = "";
			
			if (cuentacomunidadseleccionada == null)
			{
				logger.debug("NULACO!!");
			}
			
	    	logger.debug("getsPais:|"+cuentacomunidadseleccionada.getsPais()+"|");
	    	logger.debug("getsDCIBAN:|"+cuentacomunidadseleccionada.getsDCIBAN()+"|");
	    	logger.debug("getsNUCCEN:|"+cuentacomunidadseleccionada.getsNUCCEN()+"|");
	    	logger.debug("getsNUCCOF:|"+cuentacomunidadseleccionada.getsNUCCOF()+"|");
	    	logger.debug("getsNUCCDI:|"+cuentacomunidadseleccionada.getsNUCCDI()+"|");
	    	logger.debug("getsNUCCNT:|"+cuentacomunidadseleccionada.getsNUCCNT()+"|");
			
			this.sPais = cuentacomunidadseleccionada.getsPais();
			this.sDCIBAN = cuentacomunidadseleccionada.getsDCIBAN();
			this.sNUCCEN = cuentacomunidadseleccionada.getsNUCCEN();
			this.sNUCCOF = cuentacomunidadseleccionada.getsNUCCOF();
			this.sNUCCDI = cuentacomunidadseleccionada.getsNUCCDI();
			this.sNUCCNT = cuentacomunidadseleccionada.getsNUCCNT();
			
			this.setsDescripcion(cuentacomunidadseleccionada.getsDescripcion());
			
			sMsg = "Cuenta cargada.";
			msg = Utils.pfmsgInfo(sMsg);
			logger.info(sMsg);
	

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}				
	}

	public void registrarPago()
	{
		
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			try
			{
				if (sCOACES.isEmpty() || sCOGRUG.isEmpty() || sCOTPGA.isEmpty() || sCOSBGA.isEmpty() || sFEDEVE.isEmpty())
				{
					sMsg = "ERROR: No se ha seleccionado un Gasto. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else if (!CLGastos.existeGasto(Integer.parseInt(sCOACES), sCOGRUG, sCOTPGA, sCOSBGA, Utils.compruebaFecha(sFEDEVE)))
				{
					sMsg = "ERROR: El Gasto informado no existe en el sistema. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else if (sFEPGPR.isEmpty())
				{
					sMsg = "ERROR: La fecha de pago no se ha informado. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else if (sNUCCEN.isEmpty() ||
						sNUCCOF.isEmpty() ||
						sNUCCDI.isEmpty() ||
						sNUCCNT.isEmpty())
				{
					sMsg = "ERROR: Faltan campos en la cuenta de pago por informar. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}					
				else
				{
					if (bDevolucion)
					{
						this.sTipoPago= ValoresDefecto.DEF_PAGO_DEVOLUCION;
					}
					else if (sNUCCEN.equals("0000") ||
					sNUCCOF.equals("0000") ||
					sNUCCDI.equals("00") ||
					sNUCCNT.equals("0000000000"))
					{
						this.sTipoPago= ValoresDefecto.DEF_PAGO_VENTANILLA; 
					}
					else
					{
						this.sTipoPago= ValoresDefecto.DEF_PAGO_NORMA34;
					}
					
					
					Pago pago = new Pago(sCOACES,sCodGastoB,sTipoPago,ValoresDefecto.CAMPO_NUME_SIN_INFORMAR,Utils.compruebaFecha(sFEPGPR));
					
					Cuenta cuenta = new Cuenta (sPais,sDCIBAN,sNUCCEN,sNUCCOF,sNUCCDI,sNUCCNT,"");
					
					int iSalida = CLPagos.registraPagoSimple(pago, cuenta, true);
					
					switch (iSalida) 
					{
					case 0: //Sin errores
						actualizaProgreso();
						borrarCamposPago();
						sMsg = "El pago se ha registrado correctamente.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
						break;

					case -1: //ERROR 001 - El gasto no esta autorizado.
						sMsg = "ERROR:001 - El gasto no está autorizado. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -2: //Error 002 - Datos de cuenta incorrectos
						sMsg = "ERROR:002 - Los datos de la cuenta corriente son incorrectos. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -3: //Error 003 - La fecha de pago no es correcta
						sMsg = "ERROR:004 - La fecha de pago no es correcta. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -900: //Error 900 - al registrar el pago
						sMsg = "[FATAL] ERROR:900 - Se ha producido un error al registrar el pago. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -903: //Error 903 - error y rollback - error al cambiar el estado
						sMsg = "[FATAL] ERROR:903 - Se ha producido un error al resolver la relación con el gasto. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -904: //Error 904 - error y rollback - error al revisar el gasto
						sMsg = "[FATAL] ERROR:904 - Se ha producido un error al resolver la relación gasto-provision. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -905: //Error 905 - error y rollback - error al modificar el gasto
						sMsg = "[FATAL] ERROR:905 - Se ha producido un error al establecer el nuevo estado del gasto. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -906: //Error 906 - error y rollback - error al crear la transferencia
						sMsg = "[FATAL] ERROR:906 - Se ha producido un error al registrar la transferencia del pago. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -907: //Error 907 - error y rollback - error al desbloquear la provision del gasto
						sMsg = "[FATAL] ERROR:907 - Se ha producido un error al desbloquear la provision del gasto. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -908: //Error 908 - error y rollback - error al desbloquear el gasto
						sMsg = "[FATAL] ERROR:908 - Se ha producido un error al desbloquear el gasto. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -910: //Error 910 - error y rollback - error al conectar con la base de datos
						sMsg = "[FATAL] ERROR:910 - Se ha producido un error al conectar con la base de datos. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					default: //error generico
						sMsg = "[FATAL] ERROR:"+iSalida+" - La operacion solicitada ha producido un error desconocido. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
					}

					logger.debug("Finalizadas las comprobaciones.");
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

	public String getsCOACESB() {
		return sCOACESB;
	}

	public void setsCOACESB(String sCOACESB) {
		this.sCOACESB = sCOACESB.trim();
	}

	public String getsCOGRUGBA() {
		return sCOGRUGBA;
	}

	public void setsCOGRUGBA(String sCOGRUGBA) {
		this.sCOGRUGBA = sCOGRUGBA;
	}

	public String getsCOTPGABA() {
		return sCOTPGABA;
	}

	public void setsCOTPGABA(String sCOTPGABA) {
		this.sCOTPGABA = sCOTPGABA;
	}

	public String getsCOSBGABA() {
		return sCOSBGABA;
	}

	public void setsCOSBGABA(String sCOSBGABA) {
		this.sCOSBGABA = sCOSBGABA;
	}

	public String getsFEDEVEBA() {
		return sFEDEVEBA;
	}

	public void setsFEDEVEBA(String sFEDEVEBA) {
		this.sFEDEVEBA = sFEDEVEBA;
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

	public String getsNURCATB() {
		return sNURCATB;
	}

	public void setsNURCATB(String sNURCATB) {
		this.sNURCATB = sNURCATB.trim().toUpperCase();
	}

	public String getsNUPROFB() {
		return sNUPROFB;
	}

	public void setsNUPROFB(String sNUPROFB) {
		this.sNUPROFB = sNUPROFB.trim();
	}

	public String getsFEPFONB() {
		return sFEPFONB;
	}

	public void setsFEPFONB(String sFEPFONB) {
		this.sFEPFONB = sFEPFONB;
	}

	public String getsGastosPorPagar() {
		return sGastosPorPagar;
	}

	public void setsGastosPorPagar(String sGastosPorPagar) {
		this.sGastosPorPagar = sGastosPorPagar;
	}

	public String getsValorPorPagar() {
		return sValorPorPagar;
	}

	public void setsValorPorPagar(String sValorPorPagar) {
		this.sValorPorPagar = sValorPorPagar;
	}

	public String getsFechaLimite() {
		return sFechaLimite;
	}

	public void setsFechaLimite(String sFechaLimite) {
		this.sFechaLimite = sFechaLimite;
	}

	public String getsCOGRUGBP() {
		return sCOGRUGBP;
	}

	public void setsCOGRUGBP(String sCOGRUGBP) {
		this.sCOGRUGBP = sCOGRUGBP;
	}

	public String getsCOTPGABP() {
		return sCOTPGABP;
	}

	public void setsCOTPGABP(String sCOTPGABP) {
		this.sCOTPGABP = sCOTPGABP;
	}

	public String getsCOSBGABP() {
		return sCOSBGABP;
	}

	public void setsCOSBGABP(String sCOSBGABP) {
		this.sCOSBGABP = sCOSBGABP;
	}

	public String getsFEDEVEBP() {
		return sFEDEVEBP;
	}

	public void setsFEDEVEBP(String sFEDEVEBP) {
		this.sFEDEVEBP = sFEDEVEBP;
	}

	public String getsCOACESBP() {
		return sCOACESBP;
	}

	public void setsCOACESBP(String sCOACESBP) {
		this.sCOACESBP = sCOACESBP.trim();
	}

	public String getsCodGastoB() {
		return sCodGastoB;
	}

	public void setsCodGastoB(String sCodGastoB) {
		this.sCodGastoB = sCodGastoB;
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

	public String getsFEPGPR() {
		return sFEPGPR;
	}

	public void setsFEPGPR(String sFEPGPR) {
		this.sFEPGPR = sFEPGPR;
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

	public Map<String, String> getTiposcogrugHM() {
		return tiposcogrugHM;
	}

	public void setTiposcogrugHM(Map<String, String> tiposcogrugHM) {
		this.tiposcogrugHM = tiposcogrugHM;
	}

	public Map<String, String> getTiposcotpgaHMA() {
		return tiposcotpgaHMA;
	}

	public void setTiposcotpgaHMA(Map<String, String> tiposcotpgaHMA) {
		this.tiposcotpgaHMA = tiposcotpgaHMA;
	}

	public Map<String, String> getTiposcosbgaHMA() {
		return tiposcosbgaHMA;
	}

	public void setTiposcosbgaHMA(Map<String, String> tiposcosbgaHMA) {
		this.tiposcosbgaHMA = tiposcosbgaHMA;
	}

	public Map<String, String> getTiposcotpgaHMP() {
		return tiposcotpgaHMP;
	}

	public void setTiposcotpgaHMP(Map<String, String> tiposcotpgaHMP) {
		this.tiposcotpgaHMP = tiposcotpgaHMP;
	}

	public Map<String, String> getTiposcosbgaHMP() {
		return tiposcosbgaHMP;
	}

	public void setTiposcosbgaHMP(Map<String, String> tiposcosbgaHMP) {
		this.tiposcosbgaHMP = tiposcosbgaHMP;
	}

	public Map<String, String> getTiposcotpga_g1HM() {
		return tiposcotpga_g1HM;
	}

	public void setTiposcotpga_g1HM(Map<String, String> tiposcotpga_g1HM) {
		this.tiposcotpga_g1HM = tiposcotpga_g1HM;
	}

	public Map<String, String> getTiposcotpga_g2HM() {
		return tiposcotpga_g2HM;
	}

	public void setTiposcotpga_g2HM(Map<String, String> tiposcotpga_g2HM) {
		this.tiposcotpga_g2HM = tiposcotpga_g2HM;
	}

	public Map<String, String> getTiposcotpga_g3HM() {
		return tiposcotpga_g3HM;
	}

	public void setTiposcotpga_g3HM(Map<String, String> tiposcotpga_g3HM) {
		this.tiposcotpga_g3HM = tiposcotpga_g3HM;
	}

	public Map<String, String> getTiposcosbga_t11HM() {
		return tiposcosbga_t11HM;
	}

	public void setTiposcosbga_t11HM(Map<String, String> tiposcosbga_t11HM) {
		this.tiposcosbga_t11HM = tiposcosbga_t11HM;
	}

	public Map<String, String> getTiposcosbga_t12HM() {
		return tiposcosbga_t12HM;
	}

	public void setTiposcosbga_t12HM(Map<String, String> tiposcosbga_t12HM) {
		this.tiposcosbga_t12HM = tiposcosbga_t12HM;
	}

	public Map<String, String> getTiposcosbga_t21HM() {
		return tiposcosbga_t21HM;
	}

	public void setTiposcosbga_t21HM(Map<String, String> tiposcosbga_t21HM) {
		this.tiposcosbga_t21HM = tiposcosbga_t21HM;
	}

	public Map<String, String> getTiposcosbga_t22HM() {
		return tiposcosbga_t22HM;
	}

	public void setTiposcosbga_t22HM(Map<String, String> tiposcosbga_t22HM) {
		this.tiposcosbga_t22HM = tiposcosbga_t22HM;
	}

	public Map<String, String> getTiposcosbga_t23HM() {
		return tiposcosbga_t23HM;
	}

	public void setTiposcosbga_t23HM(Map<String, String> tiposcosbga_t23HM) {
		this.tiposcosbga_t23HM = tiposcosbga_t23HM;
	}

	public Map<String, String> getTiposcosbga_t32HM() {
		return tiposcosbga_t32HM;
	}

	public void setTiposcosbga_t32HM(Map<String, String> tiposcosbga_t32HM) {
		this.tiposcosbga_t32HM = tiposcosbga_t32HM;
	}

	public Map<String, String> getTiposcosbga_t33HM() {
		return tiposcosbga_t33HM;
	}

	public void setTiposcosbga_t33HM(Map<String, String> tiposcosbga_t33HM) {
		this.tiposcosbga_t33HM = tiposcosbga_t33HM;
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

	public GastoTabla getGastoactivoseleccionado() {
		return gastoactivoseleccionado;
	}

	public void setGastoactivoseleccionado(GastoTabla gastoactivoseleccionado) {
		this.gastoactivoseleccionado = gastoactivoseleccionado;
	}

	public ArrayList<GastoTabla> getTablagastosactivo() {
		return tablagastosactivo;
	}

	public void setTablagastosactivo(ArrayList<GastoTabla> tablagastosactivo) {
		this.tablagastosactivo = tablagastosactivo;
	}

	public GastoTabla getGastoprovisionseleccionado() {
		return gastoprovisionseleccionado;
	}

	public void setGastoprovisionseleccionado(GastoTabla gastoprovisionseleccionado) {
		this.gastoprovisionseleccionado = gastoprovisionseleccionado;
	}

	public ArrayList<GastoTabla> getTablagastosprovision() {
		return tablagastosprovision;
	}

	public void setTablagastosprovision(ArrayList<GastoTabla> tablagastosprovision) {
		this.tablagastosprovision = tablagastosprovision;
	}

	public Cuenta getCuentaactivoseleccionada() {
		return cuentaactivoseleccionada;
	}

	public void setCuentaactivoseleccionada(Cuenta cuentaactivoseleccionada) {
		this.cuentaactivoseleccionada = cuentaactivoseleccionada;
	}

	public ArrayList<Cuenta> getTablacuentasactivo() {
		return tablacuentasactivo;
	}

	public void setTablacuentasactivo(ArrayList<Cuenta> tablacuentasactivo) {
		this.tablacuentasactivo = tablacuentasactivo;
	}

	public Cuenta getCuentacomunidadseleccionada() {
		return cuentacomunidadseleccionada;
	}

	public void setCuentacomunidadseleccionada(Cuenta cuentacomunidadseleccionada) {
		this.cuentacomunidadseleccionada = cuentacomunidadseleccionada;
	}

	public ArrayList<Cuenta> getTablacuentascomunidad() {
		return tablacuentascomunidad;
	}

	public void setTablacuentascomunidad(ArrayList<Cuenta> tablacuentascomunidad) {
		this.tablacuentascomunidad = tablacuentascomunidad;
	}
}
