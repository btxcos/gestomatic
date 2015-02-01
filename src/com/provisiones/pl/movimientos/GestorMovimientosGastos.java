package com.provisiones.pl.movimientos;

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
import com.provisiones.ll.CLGastos;
import com.provisiones.ll.CLProvisiones;
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

import com.provisiones.types.Gasto;
import com.provisiones.types.Nota;
import com.provisiones.types.movimientos.MovimientoGasto;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.GastoTabla;
import com.provisiones.types.tablas.ProvisionTabla;


public class GestorMovimientosGastos implements Serializable 
{

	private static final long serialVersionUID = 3669307013282571769L;

	private static Logger logger = LoggerFactory.getLogger(GestorMovimientosGastos.class.getName());

	//Gasto Buscado
	private long liCodGasto = 0;
	
	//Buscar Activo
	private String sCOACESB = "";

	//Filtro Gasto Activo
	private String sCOGRUGBA = "";
	private String sCOTPGABA = "";
	private String sCOSBGABA = "";
	private String sFEDEVEBA = "";
	private String sIMNGASBA = "";
	private String sComparadorBA = "";
	private boolean bSeleccionadoBA = true; 	
	private String sEstadoBA = "";
	
	//Filtro Activo
	private String sCOPOINB = "";
	private String sNOMUINB = "";
	private String sNOPRACB = "";
	private String sNOVIASB = "";
	private String sNUPIACB = "";
	private String sNUPOACB = "";
	private String sNUPUACB = "";
	private String sNUFIREB = "";
	
	private String sNURCATB = "";
	
	//TODO Eliminar tras transicion
	private boolean bUrgente = false;	

	//Buscar Provision
	private String sNUPROFB = "";

	//Filtro Provision
	private String sFEPFONB = "";

	
	//Filtro Gasto provision
	private String sCOGRUGBP = "";
	private String sCOTPGABP = "";
	private String sCOSBGABP = "";
	private String sFEDEVEBP = "";
	private String sCOACESBP = "";
	private String sIMNGASBP = "";
	private String sComparadorBP = "";
	private boolean bSeleccionadoBP = true; 		
	private String sEstadoBP = "";
	
	//Gasto objetivo
	private String sCOACES = "";
	private boolean bDevolucion = false;
	private String sCOGRUG = "";
	private String sCOTPGA = "";
	private String sCOSBGA = "";
	private String sDCOSBGA = "";
	private String sPTPAGO = "";
	private String sFEDEVE = "";
	private String sFFGTVP = "";
	private boolean bFFGTVP = true;
	//private String sFEPAGA = ValoresDefecto.DEF_FEPAGA;
	private String sFELIPG = "";
	private String sCOSIGA = "";
	private String sFEEESI = "";
	private boolean bFEEESI = true;
	private String sFEECOI = "";
	private boolean bFEECOI = true;
	//private String sFEEAUI = "";
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
	
	//private String sCOTNEG = ValoresDefecto.DEF_COTNEG;
	
	//private String sCOENCX = ValoresDefecto.DEF_COENCX;
	//private String sCOOFCX = ValoresDefecto.DEF_COOFCX;
	//private String sNUCONE = ValoresDefecto.DEF_NUCONE;
	private String sNUPROF = "";
	private String sFEAGTO = ValoresDefecto.DEF_FEAGTO;
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

	//recuperar cuotas
	private String sCOSBAC = "";
	private String sIMCUCO = "";
	
	//Nota
	private String sNota = "";
	private String sNotaOriginal = "";
	private boolean bConNotas = false;
	
	private transient ActivoTabla activoseleccionado = null;
	private transient ArrayList<ActivoTabla> tablaactivos = null;

	private transient ProvisionTabla provisionseleccionada = null;
	private transient ArrayList<ProvisionTabla> tablaprovisiones = null;

	private transient GastoTabla gastoactivoseleccionado = null;
	private transient ArrayList<GastoTabla> tablagastosactivo = null;
	
	private transient GastoTabla gastoprovisionseleccionado = null;
	private transient ArrayList<GastoTabla> tablagastosprovision = null;
	
	private Map<String,String> tiposcogrugHM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposcotpgaHM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbgaHM = new LinkedHashMap<String, String>();
	
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
	
	private Map<String,String> tiposptpagoHM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposcoimptHM = new LinkedHashMap<String, String>();

	private Map<String,String> tiposcosigaHM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposcomparaimporteHM = new LinkedHashMap<String, String>();

	public GestorMovimientosGastos()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorMovimientosGastos...");
			
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
			
			tiposptpagoHM.put("APERIODICO",      "1");
			tiposptpagoHM.put("MENSUAL",         "2");
			tiposptpagoHM.put("BIMENSUAL",       "3");
			tiposptpagoHM.put("TRIMESTRAL",      "4");
			tiposptpagoHM.put("CUATRIMESTRAL",   "5");
			tiposptpagoHM.put("SEMESTRAL",       "6");
			tiposptpagoHM.put("ANUAL",           "7");
			tiposptpagoHM.put("VARIOS PERIODOS", "8");
			
			tiposcosigaHM.put("ESTIMADO",            "1");
			tiposcosigaHM.put("CONOCIDO",            "2");

			tiposcoimptHM.put("SIN IMPUESTO",	"0");  
			tiposcoimptHM.put("IVA",            "1");  
			tiposcoimptHM.put("IGIC",           "2");  
			tiposcoimptHM.put("IPSI",           "3");  
			tiposcoimptHM.put("IRPF",           "4");
			
			tiposcomparaimporteHM.put("Igual a",    		"=");
			tiposcomparaimporteHM.put("Mayor o igual a",	">=");
			tiposcomparaimporteHM.put("Menor o igual a",	"<=");
		}
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
    	this.sNUFIREB = "";
    	
    	this.sNURCATB = "";
	}
	
	public void borrarResultadosActivo()
	{
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {
    	this.sCOACESB = "";

    	borrarCamposBuscarActivo();
    	
    	borrarResultadosActivo();
    }
	

	public void borrarCamposBuscarProvision()
	{
		this.sFEPFONB = "";
    	
    	this.setProvisionseleccionada(null);
    	this.setTablaprovisiones(null);
	}
	
    public void limpiarPlantillaProvision(ActionEvent actionEvent) 
    {  
    	this.sNUPROFB = "";
    	
    	borrarCamposBuscarProvision();
    }
    
	public void borrarCamposBuscarGastoActivo()
	{
		this.sCOGRUGBA = "";
		this.sCOTPGABA = "";
		this.sCOSBGABA = "";
		this.sFEDEVEBA = "";
		this.sIMNGASBA = "";
		this.sComparadorBA = "";
		this.bSeleccionadoBA = true; 
		this.sEstadoBA = "";
		
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
		this.sIMNGASBA = "";
		this.sComparadorBA = "";
		this.bSeleccionadoBA = true; 
		this.sEstadoBP = "";
	
		this.sCOACESBP = "";
		
		cambiaGrupoProvision();
		
    	this.setGastoprovisionseleccionado(null);
    	this.setTablagastosprovision(null);
	}
	
    public void limpiarPlantillaBuscarGastoProvision(ActionEvent actionEvent) 
    {  
    	borrarCamposBuscarGastoProvision();
    }
    
	public void borrarPlantillaGasto()
	{
		this.liCodGasto = 0;

		this.sCOACES = "";
		this.sCOGRUG = "";
		this.bDevolucion = false;
		this.sCOTPGA = "";
		this.sCOSBGA = "";
		this.sPTPAGO = "";

		this.sFEDEVE = "";
		this.sFFGTVP = "";
		this.bFFGTVP = true;
		//this.sFEPAGA = ValoresDefecto.DEF_FEPAGA;
		this.sFELIPG = "";

		this.sCOSIGA = "";
		this.sFEEESI = "";
		this.bFEEESI = true;
		this.sFEECOI = "";
		this.bFEECOI = true;
		//this.sFEEAUI = "";
		//this.sFEEPAI = "";

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

		//this.sCOTNEG = ValoresDefecto.DEF_COTNEG;

		//this.sCOENCX = ValoresDefecto.DEF_COENCX;
		//this.sCOOFCX = ValoresDefecto.DEF_COOFCX;
		//this.sNUCONE = ValoresDefecto.DEF_NUCONE;

		this.sNUPROF = "";

		this.sFEAGTO = ValoresDefecto.DEF_FEAGTO;

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
		
		this.sCOSBAC = "";
		this.sIMCUCO = "";
		
	}
	
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	this.sCOACESB = "";
    	this.sNUPROFB = "";

    	this.sNota = "";
    	this.sNotaOriginal = "";
    	
    	this.bUrgente = false;
    	
    	this.liCodGasto = 0;
    	
    	borrarCamposBuscarActivo();
    	borrarCamposBuscarProvision();
    	borrarCamposBuscarGastoActivo();
    	borrarCamposBuscarGastoProvision();
    	
    	borrarPlantillaGasto();
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
			
			if (liCodGasto == 0)
			{
				sMsg = "Debe de haber cargado un Gasto antes de guardar la nota. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else if (CLGastos.guardarNota(liCodGasto, sNota))
			{
				sMsg = "Nota guardada correctamente.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}
			else
			{
				sMsg = "ERROR: Ocurrio un error al guardar la nota del Gasto. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
			}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		
		}
	}
    
    public void restablecerNota(ActionEvent actionEvent) 
    {  
    	this.sNota = sNotaOriginal;
    	this.setbConNotas(!sNota.isEmpty());
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
		else
		{
			tiposcotpgaHM = new LinkedHashMap<String, String>();
			tiposcosbgaHM = new LinkedHashMap<String, String>();
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
		else
		{
			tiposcotpgaHM = new LinkedHashMap<String, String>();
			tiposcosbgaHM = new LinkedHashMap<String, String>();
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
	
	public void cambiaComparadorBA()
	{
		this.bSeleccionadoBA = this.sComparadorBA.isEmpty();
		logger.debug("sComparadorBA:|"+sComparadorBA+"|");
	}
	
	public void cambiaComparadorBP()
	{
		this.bSeleccionadoBP = this.sComparadorBP.isEmpty();
		logger.debug("sComparadorBP:|"+sComparadorBP+"|");
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
	
	public void cambiaFechaPorSituacion()
	{

		if (sCOSIGA !=null && !sCOSIGA.equals(""))
		{
			switch (Integer.parseInt(sCOSIGA)) 
			{
				case 1:
					this.bFEEESI = false;
					this.bFEECOI = true;
					//this.sFEEESI = "";
					//this.sFEECOI = "";
					break;
				case 2:
					this.bFEEESI = true;
					this.bFEECOI = false;
					//this.sFEEESI = "";
					//this.sFEECOI = "";
					break;
			}

		}
	}
	
	public void cambiaImporteImpuesto()
	{

		if (sCOIMPT !=null && !sCOIMPT.equals(""))
		{
			switch (Integer.parseInt(sCOIMPT)) 
			{
				case 0:
					this.bIMIMGA = true;
					this.sIMIMGA = "";
					break;
				default:
					this.bIMIMGA = false;
					break;
			}

		}
	}
	
	public void cambiaFechaFinPeriodo()
	{

		if (sPTPAGO !=null && !sPTPAGO.equals(""))
		{
			switch (Integer.parseInt(sPTPAGO)) 
			{
				case 8:
					this.bFFGTVP = false;
					break;
				default:
					this.bFFGTVP = true;
					this.sFFGTVP = "";
					break;
			}

		}
	}
	
	
	public void hoyFEDEVE (ActionEvent actionEvent)
	{
		this.setsFEDEVE(Utils.fechaDeHoy(true));
		logger.debug("sFEDEVE:|{}|",sFEDEVE);
	}

	public void hoyFFGTVP (ActionEvent actionEvent)
	{
		this.setsFFGTVP(Utils.fechaDeHoy(true));
		logger.debug("sFFGTVP:|{}|",sFFGTVP);
	}

	/*public void hoyFEPAGA (ActionEvent actionEvent)
	{
		this.setsFEPAGA(Utils.fechaDeHoy(true));
		logger.debug("sFEPAGA:|{}|",sFEPAGA);
	}*/

	public void hoyFELIPG (ActionEvent actionEvent)
	{
		this.setsFELIPG(Utils.fechaDeHoy(true));
		logger.debug("sFELIPG:|{}|",sFELIPG);
	}

	public void hoyFEEESI (ActionEvent actionEvent)
	{
		this.setsFEEESI(Utils.fechaDeHoy(true));
		logger.debug("sFEEESI:|{}|",sFEEESI);
	}

	public void hoyFEECOI (ActionEvent actionEvent)
	{
		this.setsFEECOI(Utils.fechaDeHoy(true));
		logger.debug("sFEECOI:|{}|",sFEECOI);
	}
	
	/*public void hoyFEPGPR (ActionEvent actionEvent)
	{
		this.setsFEPGPR(Utils.fechaDeHoy(true));
		logger.debug("sFEPGPR:|{}|",sFEPGPR);
	}*/
	
	public void hoyFEAGTO (ActionEvent actionEvent)
	{
		this.setsFEAGTO(Utils.fechaDeHoy(true));
		logger.debug("sFEAGTO:|{}|",sFEAGTO);
	}
	
	public void buscarActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.activoseleccionado = null;
			
			this.setTablaactivos(null);
			
			if (!sCOPOINB.isEmpty() && Utils.esAlfanumerico(sCOPOINB))
			{
				sMsg = "ERROR: El Código Postal debe ser numérico. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else if (sNURCATB.isEmpty())
			{
				ActivoTabla filtro = new ActivoTabla(
						"", 
						sCOPOINB, 
						sNOMUINB.toUpperCase(),
						sNOPRACB.toUpperCase(), 
						sNOVIASB.toUpperCase(), 
						sNUPIACB.toUpperCase(), 
						sNUPOACB.toUpperCase(), 
						sNUPUACB.toUpperCase(), 
						sNUFIREB.toUpperCase(),
						"");
				
				this.setTablaactivos(CLGastos.buscarActivosConGastosPendientes(filtro));
				
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
				this.setTablaactivos(CLReferencias.buscarActivoAsociadoConGastosAbonables(sNURCATB));
				
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
			
			this.provisionseleccionada = null;
			
			String sFecha = Utils.compruebaFecha(sFEPFONB);
			
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
				String sINGRESADOF = "";
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
						sINGRESADOF,
						sESTADOF);
						
				
				this.setTablaprovisiones(CLProvisiones.buscarProvisionesAbiertasConFiltro(filtro));

				if (getTablaprovisiones().size() == 0)
				{
					sMsg = "No se encontraron Provisiones con los criterios solicitados.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);
				}
				else if (getTablaprovisiones().size() == 1)
				{
					sMsg = "Encontrado una Provisión relacionada.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
				}
				else
				{
					sMsg = "Encontradas "+getTablaprovisiones().size()+" Provisiones relacionadas.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
				}
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
					

						if (sComparadorBA.isEmpty()) 
						{
							sIMNGASBA = "";
						}
						else
						{
							sIMNGASBA = Utils.compruebaImporte(sIMNGASBA);
						}
						
						GastoTabla filtro = new GastoTabla(
								"",
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
								sIMNGASBA,
								sEstadoBA,//TODO meter estado en el filtro
								"",
								"",
								"",
								"");
						
						
						//CLGastos.buscarGastosNuevosActivo(Integer.parseInt(sCOACES)
						this.setTablagastosactivo(CLGastos.buscarGastosNuevosActivo(filtro, sComparadorBA));
						
						if (getTablagastosactivo().size() == 0)
						{
							sMsg = "No se encontraron gastos con los criterios solicitados.";
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
							sMsg = "Encontrados "+getTablagastosactivo().size()+" gastos relacionados.";
							msg = Utils.pfmsgInfo(sMsg);
							logger.info(sMsg);
						}

				}
				else
				{
					sMsg = "El Activo '"+sCOACESB+"' no pertenece a la cartera. Por favor, revise los datos.";
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
			
			this.tablagastosprovision = null;

			try
			{
				Integer.parseInt(sNUPROFB);
				
				if (sNUPROFB.isEmpty())
				{
					sMsg = "No se informó el campo 'Provisión'. Por favor, revise los datos.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);
				}
				else if (!sCOACESBP.isEmpty() && Utils.esAlfanumerico(sCOACESBP))
				{
					sMsg = "ERROR: El Activo de filtro debe ser numérico. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else if (CLProvisiones.existeProvision(sNUPROFB))
				{
						if (sComparadorBP.isEmpty()) 
						{
							sIMNGASBP = "";
						}
						else
						{
							sIMNGASBP = Utils.compruebaImporte(sIMNGASBP);
						}
					
						
						GastoTabla filtro = new GastoTabla(
								"",
								sNUPROFB,   
								"",
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
								sIMNGASBP,
								sEstadoBP,//TODO meter estado en el filtro
								"",
								"",
								"",
								"");
						
						this.setTablagastosprovision(CLGastos.buscarGastosNuevosProvisionConFiltro(filtro, sComparadorBP));
						
						if (getTablagastosprovision().size() == 0)
						{
							sMsg = "No se encontraron gastos con los criterios solicitados.";
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
							sMsg = "Encontrados "+getTablagastosprovision().size()+" gastos relacionados.";
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

			try
			{
		    	this.liCodGasto = Long.parseLong(gastoseleccionado.getsGastoID());
		    	
		    	this.bUrgente = CLGastos.esUrgente(liCodGasto);
		    	
		    	logger.debug("liCodGasto:|"+liCodGasto+"|");
		    	
		    	this.sCOACES = gastoseleccionado.getCOACES();
		    	
		    	this.sCOGRUG = gastoseleccionado.getCOGRUG();
		    	this.sCOTPGA = gastoseleccionado.getCOTPGA();
		    	this.sCOSBGA = gastoseleccionado.getCOSBGA();
		    	this.sDCOSBGA = gastoseleccionado.getDCOSBGA().replaceFirst("Devolucion ", "");
		    	this.sFEDEVE = gastoseleccionado.getFEDEVE();
		    	
		    	//logger.debug("liCodGasto:|"+CLGastos.buscarCodigoGasto(Integer.parseInt(sCOACES), sCOGRUG, sCOTPGA, sCOSBGA, Utils.compruebaFecha(sFEDEVE))+"|");
		    	
		    	
		    	

				
			  	//Gasto gasto = CLGastos.buscarGasto(Integer.parseInt(sCOACES), sCOGRUG, sCOTPGA, sCOSBGA, Utils.compruebaFecha(sFEDEVE));
		    	
				Gasto gasto = CLGastos.buscarGastoConCodigo(liCodGasto);
				
		    	logger.debug(gasto.logGasto());
		 
		    	this.bDevolucion = (Integer.parseInt(sCOSBGA) > 49);

				this.sPTPAGO = gasto.getPTPAGO();

				this.sFFGTVP = Utils.recuperaFecha(gasto.getFFGTVP());
				//this.sFEPAGA = Utils.recuperaFecha(gasto.getFEPAGA());
				this.sFELIPG = Utils.recuperaFecha(gasto.getFELIPG());
				this.sCOSIGA = gasto.getCOSIGA();
				this.sFEEESI = Utils.recuperaFecha(gasto.getFEEESI());
				this.sFEECOI = Utils.recuperaFecha(gasto.getFEECOI());
				//this.sFEEAUI = Utils.recuperaFecha(gasto.getFEEAUI());
				//this.sFEEPAI = Utils.recuperaFecha(gasto.getFEEPAI());
				this.sIMNGAS = Utils.recuperaImporte(gasto.getYCOS02().equals("-"),gasto.getIMNGAS());
				this.sIMRGAS = Utils.recuperaImporte(gasto.getYCOS04().equals("-"),gasto.getIMRGAS());
				this.sIMDGAS = Utils.recuperaImporte(gasto.getYCOS06().equals("-"),gasto.getIMDGAS());
				this.sIMCOST = Utils.recuperaImporte(gasto.getYCOS08().equals("-"),gasto.getIMCOST());
				this.sIMOGAS = Utils.recuperaImporte(gasto.getYCOS10().equals("-"),gasto.getIMOGAS());
				this.sIMDTGA = Utils.recuperaImporte(false,gasto.getIMDTGA());
				this.sIMIMGA = Utils.recuperaImporte(false,gasto.getIMIMGA());
				this.sCOIMPT = gasto.getCOIMPT();
				
				//this.sCOTNEG = gasto.getCOTNEG();
				this.sFEAGTO = Utils.recuperaFecha(gasto.getFEAGTO());
				//this.sCOMONA = gasto.getCOMONA();
				//this.sBIAUTO = gasto.getBIAUTO();
				//this.sFEAUFA = Utils.recuperaFecha(gasto.getFEAUFA());
				
				//TODO Buscar FEPGPR en la tabla de pagos si esta en estado pagado, si no, ira a 0.
				//this.sFEPGPR = Utils.recuperaFecha(gasto.getFEPGPR());
				
				this.sCOUNMO = ValoresDefecto.DEF_COUNMO;
				
				//this.sCOENCX = ValoresDefecto.DEF_COENCX;
				//this.sCOOFCX = ValoresDefecto.DEF_COOFCX;
				//this.sNUCONE = ValoresDefecto.DEF_NUCONE;
				
				//this.sNUPROF = CLGastos.buscarProvisionGasto(Integer.parseInt(sCOACES), sCOGRUG, sCOTPGA, sCOSBGA, Utils.compruebaFecha(sFEDEVE));

				this.sNUPROF = CLGastos.obtenerProvisionDeGasto(liCodGasto);
				
				//this.sCOTERR = ValoresDefecto.DEF_COTERR;
				//this.sFMPAGN = Utils.recuperaFecha(ValoresDefecto.DEF_FMPAGN);
			
				//this.sFEAPLI = Utils.recuperaFecha(ValoresDefecto.DEF_FEAPLI);
				//this.sCOAPII = ValoresDefecto.DEF_COAPII;
				//this.sCOSPII = ValoresDefecto.DEF_COSPII_GA;
				//this.sNUCLII = ValoresDefecto.DEF_NUCLII;
				
				this.sNotaOriginal = CLGastos.buscarNota(liCodGasto);
				this.sNota = sNotaOriginal;
				
				this.bConNotas = !sNota.isEmpty();
				
				String sTipo = bDevolucion ? "La devolucion":"El Gasto"; 
				
				sMsg = sTipo+" de '"+sDCOSBGA+"' se ha cargado.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}
			catch(NumberFormatException nfe)
			{
				sMsg = "ERROR: No se pudo recuperar el código de gasto correctamente. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
			}


			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }

	public void registraGasto(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			try
			{
				if (!CLGastos.existeGasto(Integer.parseInt(sCOACES), sCOGRUG, sCOTPGA, sCOSBGA, Utils.compruebaFecha(sFEDEVE)))
				{
					sMsg = "El movimiento sobre el Gasto informado no se puede tramitar, no existe en el sistema.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else if (bDevolucion)
				{
					sMsg = "Las devoluciones no pueden ser modificadas.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);			
				}
				else if (sCOSIGA.equals(ValoresDefecto.DEF_GASTO_ESTIMADO) && sFEEESI.isEmpty())
				{
					sMsg = "ERROR: La fecha de estado estimado no puede ir vacia. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else if (sCOSIGA.equals(ValoresDefecto.DEF_GASTO_CONOCIDO) && sFEECOI.isEmpty())
				{
					sMsg = "ERROR: La fecha de estado conocido no puede ir vacia. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else if (sIMNGAS.isEmpty())
				{
					sMsg = "ERROR: El importe no puede ir vacio. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else if (Utils.esAlfanumerico(sCOACES))
				{
					sMsg = "ERROR: El activo debe ser numérico. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else
				{
					int iCodCOACES = Integer.parseInt(sCOACES);
					
					if (!CLActivos.existeActivo(iCodCOACES))
					{
						sMsg = "El activo '"+sCOACES+"' no pertenece a esta cartera. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
					}
					else 
					{
						String sFechaVentaActivo = CLActivos.buscarFechaVentaActivo(iCodCOACES);
						
						String sFechaBloqueo = CLActivos.buscarFechaBloqueo(iCodCOACES);
						
						if (!sFechaVentaActivo.equals("0") && (Long.parseLong(Utils.compruebaFecha(sFEDEVE)) > Long.parseLong(sFechaVentaActivo)))
						{
							sMsg = "ERROR: El Gasto informado no puede darse de alta, la fecha de devengo es superior a la de venta del Activo ("+Utils.recuperaFecha(sFechaVentaActivo)+"). Por favor, revise los datos";
							msg = Utils.pfmsgError(sMsg);
							logger.error(sMsg);
						}
						else if (!sFechaBloqueo.equals("0") && (Long.parseLong(Utils.compruebaFecha(sFEDEVE)) > Long.parseLong(sFechaBloqueo)))
						{
							sMsg = "ERROR: El Gasto informado no puede darse de alta, la fecha de devengo es superior a la de bloqueo del Activo ("+Utils.recuperaFecha(sFechaBloqueo)+"). Por favor, revise los datos";
							msg = Utils.pfmsgError(sMsg);
							logger.error(sMsg);
						}
						else
						{
							String sIMNGASCI =  Utils.compruebaImporte(sIMNGAS);
							String sIMRGASCI =  Utils.compruebaImporte(sIMRGAS);
							String sIMDGASCI =  Utils.compruebaImporte(sIMDGAS);
							String sIMCOSTCI =  Utils.compruebaImporte(sIMCOST);
							String sIMOGASCI =  Utils.compruebaImporte(sIMOGAS);
							String sIMDTGACI =  Utils.compruebaImporte(sIMDTGA);
							String sIMIMGACI =  Utils.compruebaImporte(sIMIMGA);
							
							if (sIMNGASCI.equals(ValoresDefecto.CAMPO_NUME_INCORRECTO))
							{
								sMsg = "ERROR: El Importe del gasto debe ser numérico. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
							}
							else if (sIMRGASCI.equals(ValoresDefecto.CAMPO_NUME_INCORRECTO))
							{
								sMsg = "ERROR: El Recargo en el importe del gasto debe ser numérico. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
							}
							else if (sIMDGASCI.equals(ValoresDefecto.CAMPO_NUME_INCORRECTO))
							{
								sMsg = "ERROR: El Importe de demora del gasto debe ser numérico. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
							}
							else if (sIMCOSTCI.equals(ValoresDefecto.CAMPO_NUME_INCORRECTO))
							{
								sMsg = "ERROR: El Importe de costas debe ser numérico. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
							}
							else if (sIMOGASCI.equals(ValoresDefecto.CAMPO_NUME_INCORRECTO))
							{
								sMsg = "ERROR: El Importe de otros incrementos debe ser numérico. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
							}
							else if (sIMDTGACI.equals(ValoresDefecto.CAMPO_NUME_INCORRECTO))
							{
								sMsg = "ERROR: El Importe de descuento de gastos debe ser numérico. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
							}
							else if (sIMIMGACI.equals(ValoresDefecto.CAMPO_NUME_INCORRECTO))
							{
								sMsg = "ERROR: El Importe de impuestos del gasto debe ser numérico. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
							}
							else
							{
								MovimientoGasto movimiento = new MovimientoGasto (
										sCOACES,
										sCOGRUG.toUpperCase(),
										sCOTPGA.toUpperCase(),
										Utils.compruebaCodigoPago(false, sCOSBGA.toUpperCase()),
										sPTPAGO.toUpperCase(),
										Utils.compruebaFecha(sFEDEVE),
										Utils.compruebaFecha(sFFGTVP),
										"0",
										Utils.compruebaFecha(sFELIPG),
										sCOSIGA.toUpperCase(),
										Utils.compruebaFecha(sFEEESI),
										Utils.compruebaFecha(sFEECOI),
										"0",
										"0",
										Utils.compruebaImporte(sIMNGAS.toUpperCase()),
										sYCOS02.toUpperCase(),
										Utils.compruebaImporte(sIMRGAS.toUpperCase()),
										sYCOS04.toUpperCase(),
										Utils.compruebaImporte(sIMDGAS.toUpperCase()),
										sYCOS06.toUpperCase(),
										Utils.compruebaImporte(sIMCOST.toUpperCase()),
										sYCOS08.toUpperCase(),
										Utils.compruebaImporte(sIMOGAS.toUpperCase()),
										sYCOS10.toUpperCase(),
										Utils.compruebaImporte(sIMDTGA.toUpperCase()),
										ValoresDefecto.DEF_COUNMO,
										Utils.compruebaImporte(sIMIMGA.toUpperCase()),
										Utils.compruebaCodigoNum(sCOIMPT.toUpperCase()),
										ValoresDefecto.DEF_COTNEG,
										ValoresDefecto.DEF_COENCX,
										ValoresDefecto.DEF_COOFCX,
										ValoresDefecto.DEF_NUCONE,
										sNUPROF.toUpperCase(),
										Utils.compruebaFecha(sFEAGTO),
										ValoresDefecto.DEF_COMONA,
										ValoresDefecto.DEF_BIAUTO,
										ValoresDefecto.DEF_FEAUFA,
										ValoresDefecto.DEF_COTERR,
										ValoresDefecto.DEF_FMPAGN,
										ValoresDefecto.DEF_FEPGPR,
										ValoresDefecto.DEF_FEAPLI,
										ValoresDefecto.DEF_COAPII,
										ValoresDefecto.DEF_COSPII_GA,
										ValoresDefecto.DEF_NUCLII);

								//movimiento.pintaMovimientoGasto();
								
								//TODO nota
								Nota nota = new Nota (sNotaOriginal.equals(sNota),sNota);
								
								if (nota.isbInvalida())
								{
									nota.setsContenido("");
								}
								
								int iSalida = CLGastos.registraMovimiento(movimiento,true,nota,bUrgente);
								
								logger.debug("Codigo de salida:"+iSalida);
								
								switch (iSalida) 
								{
								case 0: //Sin errores
									sMsg = "El movimiento se ha registrado correctamente.";
									msg = Utils.pfmsgInfo(sMsg);
									logger.info(sMsg);
									break;
									
								case 1: //Sin errores
									sMsg = "El cambio de nota se ha registrado correctamente.";
									msg = Utils.pfmsgInfo(sMsg);
									logger.info(sMsg);
									break;

								case -2: //Error 002 - Llega fecha de anulación y no existe gasto en la tabla
									sMsg = "ERROR:002 - El gasto que se anula no existe. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;

								case -3: //Error 003 - Llega un abono de un gasto que NO está pagado
									sMsg = "ERROR:003 - El gasto a abonar no esta pagado. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;

								case -4: //Error 004 - Descuento mayor que importe nominal del gasto
									sMsg = "ERROR:004 - El descuento informado es superior al gasto. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;

								case -6: //Error 006 - La provisión ya está cerrada
									sMsg = "ERROR:006 - La provisión ya esta cerrada. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;

								case -7: //Error 007 - Error en grupo / tipo / subtipo de acción
									sMsg = "ERROR:007 - El grupo, tipo y subtipo de gasto deben informarse. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;

								case -8: //Error 008 - No existe el activo en la base corporativa
									sMsg = "ERROR:008 - El activo informado no se encuentra resistrado en el sistema. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;

								case -12: //Error 012 - Llega un abono de un gasto que está anulado
									sMsg = "ERROR:012 - El gasto a abonar esta anulado. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;

								case -13: //Error 013 - Llega un abono de un gasto que ya está abonado, o bien está en la misma provisión sin anular.
									sMsg = "ERROR:013 - El gasto a abonar ya esta abonado. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;

								case -19: //Error 019 - Periodicidad del gasto es cero o espacios.
									sMsg = "ERROR:019 - El campo periodicidad del pago es obligatorio. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;

								case -23: //Error 023 - Llega anulación de un gasto que YA está pagado
									sMsg = "ERROR:023 - El gasto a anular ya esta pagado. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;

								case -24: //Error 024 - Llega modificación de un gasto que YA está pagado
									sMsg = "ERROR:024 - El gasto a modificar ya esta pagado. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;

								case -61: //Error 061 - La provisión ya está cerrada pero se ha actualizado la fecha de pago a proveedor.
									sMsg = "ERROR:061 - La provision esta cerrada, no se puede actualizar la fecha de pago a proveedor. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;
									
								case -62: //Error 062 - Llega una devolución con importe positivo. 
									sMsg = "ERROR:062 - La devolucion debe incluir un importe del gasto con valor negativo. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;
									
								case -701: //Error 701 - Fecha de devengo incorrecta  
									sMsg = "ERROR:701 - La fecha de devengo esta incorrectamente informada. Por favor, cargue los datos del activo.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;

								case -702: //Error 702 - Fecha de fin de periodo incorrecta  
									sMsg = "ERROR:702 - La fecha de fin de periodo esta incorrectamente informada. Por favor, cargue los datos del activo.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;
									
								case -703: //Error 703 - Fecha de pago incorrecta
									sMsg = "ERROR:703 - La fecha de pago esta incorrectamente informada. Por favor, cargue los datos del activo.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;
									
								case -704: //Error 704 - Fecha de limite de pago incorrecta
									sMsg = "ERROR:704 - La fecha de limite de pago esta incorrectamente informada. Por favor, cargue los datos del activo.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;
									
								case -705: //Error 705 - Fecha de estado estimado incorrecta
									sMsg = "ERROR:705 - La fecha de estado estimado esta incorrectamente informada. Por favor, cargue los datos del activo.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;
									
								case -706: //Error 706 - Fecha de estado conocido incorrecta
									sMsg = "ERROR:706 - La fecha de estado conocido esta incorrectamente informada. Por favor, cargue los datos del activo.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;
									
								case -707: //Error 707 - Fecha de anulacion del gasto incorrecta  
									sMsg = "ERROR:707 - La fecha de anulacion de gasto esta incorrectamente informada. Por favor, cargue los datos del activo.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;
									
								case -708: //Error 708 - Fecha de pago al proveedor incorrecta
									sMsg = "ERROR:708 - La fecha de pago al proveedor esta incorrectamente informada. Por favor, cargue los datos del activo.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;				
									
								case -709: //Error 709 - Importe del gasto incorrecto
									sMsg = "ERROR:709 - El importe del gasto esta incorrectamente informada. Por favor, cargue los datos del activo.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;
									
								case -710: //Error 710 - Recargo en el importe del gasto incorrecto
									sMsg = "ERROR:710 - El recargo en el importe del gasto esta incorrectamente informada. Por favor, cargue los datos del activo.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;
									
								case -711: //Error 711 - Importe de demora del gasto incorrecto
									sMsg = "ERROR:711 - El importe de demora del gasto esta incorrectamente informada. Por favor, cargue los datos del activo.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;
									
								case -712: //Error 712 - Importe de costas incorrecto
									sMsg = "ERROR:712 - El importe de costas esta incorrectamente informada. Por favor, cargue los datos del activo.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;				
									
								case -713: //Error 713 - Importe de otros incrementos incorrecto   
									sMsg = "ERROR:713 - El importe de otros incrementos esta incorrectamente informada. Por favor, cargue los datos del activo.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;
									
								case -714: //Error 714 - Importe de descuento incorrecto
									sMsg = "ERROR:714 - El importe de descuento esta incorrectamente informada. Por favor, cargue los datos del activo.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;
									
								case -715: //Error 715 - Importe de impuestos incorrecto
									sMsg = "ERROR:715 - El importe de impuestos esta incorrectamente informada. Por favor, cargue los datos del activo.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;
									
								case -800: //Error 800 - Gasto sin provision  
									sMsg = "ERROR:801 - No se ha cargado una provision de gastos. Por favor, cargue los datos del activo.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;

								case -801: //Error 801 - No se ha informado la fecha de devengo
									sMsg = "ERROR:801 - No se ha informado la fecha de devengo. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;

								case -802: //Error 802 - No se ha elegido una situacion del gasto
									sMsg = "ERROR:802 - No se ha elegido una situacion del gasto. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;
									
								case -803: //Error 803 - No se ha informado el campo importe de gasto
									sMsg = "ERROR:803 - No se ha informado el campo importe de gasto. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;

								case -804: //Error 804 - Accion no permitida
									sMsg = "ERROR:804 - No se pueden registrar los datos. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;

								case -805: //Error 805 - estado no disponible
									sMsg = "ERROR:805 - El estado del gasto no esta disponible. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;

								case -806: //Error 806 - modificacion sin cambios
									sMsg = "ERROR:806 - No hay modificaciones que realizar. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;
									
								case -807: //Error 807 - Fecha de estado sin informar
									sMsg = "ERROR:807 - No se ha informado ninguna fecha de estado del gasto. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;
									
								case -808: //Error 808 - Importe de descuento negativo
									sMsg = "ERROR:808 - El importe del descuento no puede ser negativo. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;
									
								case -809: //Error 809 - Importe de impuestos negativo
									sMsg = "ERROR:809 - El importe de impuestos no puede ser negativo. Por favor, revise los datos.";
									msg = Utils.pfmsgError(sMsg);
									logger.error(sMsg);
									break;

								case -900: //Error 900 - al crear un movimiento
									sMsg = "[FATAL] ERROR:900 - Se ha producido un error al registrar el movimiento. Por favor, revise los datos y avise a soporte.";
									msg = Utils.pfmsgFatal(sMsg);
									logger.error(sMsg);
									break;

								case -901: //Error 901 - error y rollback - error al crear el gasto
									sMsg = "[FATAL] ERROR:901 - Se ha producido un error al registrar el nuevo gasto. Por favor, revise los datos y avise a soporte.";
									msg = Utils.pfmsgFatal(sMsg);
									logger.error(sMsg);
									break;
									
								case -902: //Error 902 - error y rollback - error al registrar la relaccion
									sMsg = "[FATAL] ERROR:902 - Se ha producido un error al registrar la relacion. Por favor, revise los datos y avise a soporte.";
									msg = Utils.pfmsgFatal(sMsg);
									logger.error(sMsg);
									break;

								case -903: //Error 903 - error y rollback - error al cambiar el estado
									sMsg = "[FATAL] ERROR:903 - Se ha producido un error al cambiar el estado del gasto. Por favor, revise los datos y avise a soporte.";
									msg = Utils.pfmsgFatal(sMsg);
									logger.error(sMsg);
									break;
									
								case -904: //Error 904 - error y rollback - error al revisar el gasto
									sMsg = "[FATAL] ERROR:904 - Se ha producido un error al revisar el gasto. Por favor, revise los datos y avise a soporte.";
									msg = Utils.pfmsgFatal(sMsg);
									logger.error(sMsg);
									break;

								case -905: //Error 905 - error y rollback - error al modificar el gasto
									sMsg = "[FATAL] ERROR:905 - Se ha producido un error al modificar el gasto. Por favor, revise los datos y avise a soporte.";
									msg = Utils.pfmsgFatal(sMsg);
									logger.error(sMsg);
									break;

								case -906: //Error 906 - error y rollback - error al registrar la relaccion en provision
									sMsg = "[FATAL] ERROR:906 - Se ha producido un error al registra la relacion con la provisión. Por favor, revise los datos y avise a soporte.";
									msg = Utils.pfmsgFatal(sMsg);
									logger.error(sMsg);
									break;
									
								case -907: //Error 906 - error y rollback - error al registrar la fecha de anulacion
									sMsg = "[FATAL] ERROR:906 - Se ha producido un error al registra la fecha de anulación del gasto. Por favor, revise los datos y avise a soporte.";
									msg = Utils.pfmsgFatal(sMsg);
									logger.error(sMsg);
									break;
									
								case -908: //Error 908 - error y rollback - error al eliminar el gasto
									sMsg = "[FATAL] ERROR:908 - Se ha producido un error al eliminar el gasto. Por favor, revise los datos y avise a soporte.";
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
							}
							
							
						}
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
			
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
    
	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES.trim();
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

	public String getsPTPAGO() {
		return sPTPAGO;
	}

	public void setsPTPAGO(String sPTPAGO) {
		this.sPTPAGO = sPTPAGO;
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

	/*public String getsFEPAGA() {
		return sFEPAGA;
	}

	public void setsFEPAGA(String sFEPAGA) {
		this.sFEPAGA = sFEPAGA;
	}*/

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

	/*public String getsFEEAUI() {
		return sFEEAUI;
	}

	public void setsFEEAUI(String sFEEAUI) {
		this.sFEEAUI = sFEEAUI;
	}

	public String getsFEEPAI() {
		return sFEEPAI;
	}

	public void setsFEEPAI(String sFEEPAI) {
		this.sFEEPAI = sFEEPAI;
	}*/

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

	public String getsCOIMPT() {
		return sCOIMPT;
	}

	public void setsCOIMPT(String sCOIMPT) {
		this.sCOIMPT = sCOIMPT;
	}

	/*public String getsCOTNEG() {
		return sCOTNEG;
	}

	public void setsCOTNEG(String sCOTNEG) {
		this.sCOTNEG = sCOTNEG;
	}

	public String getsCOENCX() {
		return sCOENCX;
	}

	public void setsCOENCX(String sCOENCX) {
		this.sCOENCX = sCOENCX;
	}

	public String getsCOOFCX() {
		return sCOOFCX;
	}

	public void setsCOOFCX(String sCOOFCX) {
		this.sCOOFCX = sCOOFCX;
	}

	public String getsNUCONE() {
		return sNUCONE;
	}

	public void setsNUCONE(String sNUCONE) {
		this.sNUCONE = sNUCONE;
	}*/

	public String getsNUPROF() {
		return sNUPROF;
	}

	public void setsNUPROF(String sNUPROF) {
		this.sNUPROF = sNUPROF;
	}

	public String getsFEAGTO() {
		return sFEAGTO;
	}

	public void setsFEAGTO(String sFEAGTO) {
		this.sFEAGTO = sFEAGTO;
	}

	/*public String getsCOMONA() {
		return sCOMONA;
	}

	public void setsCOMONA(String sCOMONA) {
		this.sCOMONA = sCOMONA;
	}

	public String getsBIAUTO() {
		return sBIAUTO;
	}

	public void setsBIAUTO(String sBIAUTO) {
		this.sBIAUTO = sBIAUTO;
	}

	public String getsFEAUFA() {
		return sFEAUFA;
	}

	public void setsFEAUFA(String sFEAUFA) {
		this.sFEAUFA = sFEAUFA;
	}

	public String getsCOTERR() {
		return sCOTERR;
	}

	public void setsCOTERR(String sCOTERR) {
		this.sCOTERR = sCOTERR;
	}

	public String getsFMPAGN() {
		return sFMPAGN;
	}

	public void setsFMPAGN(String sFMPAGN) {
		this.sFMPAGN = sFMPAGN;
	}

	public String getsFEPGPR() {
		return sFEPGPR;
	}

	public void setsFEPGPR(String sFEPGPR) {
		this.sFEPGPR = sFEPGPR;
	}

	public String getsFEAPLI() {
		return sFEAPLI;
	}

	public void setsFEAPLI(String sFEAPLI) {
		this.sFEAPLI = sFEAPLI;
	}*/

	/*public String getsCOAPII() {
		return sCOAPII;
	}

	public void setsCOAPII(String sCOAPII) {
		this.sCOAPII = sCOAPII;
	}

	public String getsCOSPII() {
		return sCOSPII;
	}

	public void setsCOSPII(String sCOSPII) {
		this.sCOSPII = sCOSPII;
	}

	public String getsNUCLII() {
		return sNUCLII;
	}

	public void setsNUCLII(String sNUCLII) {
		this.sNUCLII = sNUCLII;
	}*/

	public Map<String, String> getTiposcotpgaHM() {
		return tiposcotpgaHM;
	}

	public void setTiposcotpgaHM(Map<String, String> tiposcotpgaHM) {
		this.tiposcotpgaHM = tiposcotpgaHM;
	}

	public Map<String, String> getTiposcosbgaHM() {
		return tiposcosbgaHM;
	}

	public void setTiposcosbgaHM(Map<String, String> tiposcosbgaHM) {
		this.tiposcosbgaHM = tiposcosbgaHM;
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

	public Map<String,String> getTiposcosbga_t12HM() {
		return tiposcosbga_t12HM;
	}
	public void setTiposcosbga_t12HM(Map<String,String> tiposcosbga_t12HM) {
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

	public String getsCOSBAC() {
		return sCOSBAC;
	}
	public void setsCOSBAC(String sCOSBAC) {
		this.sCOSBAC = sCOSBAC;
	}

	public String getsIMCUCO() {
		return sIMCUCO;
	}
	public void setsIMCUCO(String sIMCUCO) {
		this.sIMCUCO = sIMCUCO;
	}

	public boolean isbFEEESI() {
		return bFEEESI;
	}
	public void setbFEEESI(boolean bFEEESI) {
		this.bFEEESI = bFEEESI;
	}
	public boolean isbFEECOI() {
		return bFEECOI;
	}
	public void setbFEECOI(boolean bFEECOI) {
		this.bFEECOI = bFEECOI;
	}
	public boolean isbFFGTVP() {
		return bFFGTVP;
	}
	public void setbFFGTVP(boolean bFFGTVP) {
		this.bFFGTVP = bFFGTVP;
	}
	public Map<String, String> getTiposcosigaHM() {
		return tiposcosigaHM;
	}
	public void setTiposcosigaHM(Map<String, String> tiposcosigaHM) {
		this.tiposcosigaHM = tiposcosigaHM;
	}
	public boolean isbIMIMGA() {
		return bIMIMGA;
	}
	public void setbIMIMGA(boolean bIMIMGA) {
		this.bIMIMGA = bIMIMGA;
	}
	public boolean isbDevolucion() {
		return bDevolucion;
	}
	public void setbDevolucion(boolean bDevolucion) {
		this.bDevolucion = bDevolucion;
	}

	public String getsDCOSBGA() {
		return sDCOSBGA;
	}
	public void setsDCOSBGA(String sDCOSBGA) {
		this.sDCOSBGA = sDCOSBGA;
	}

	public String getsNota() {
		return sNota;
	}

	public void setsNota(String sNota) {
		this.sNota = sNota;
	}

	public boolean isbConNotas() {
		return bConNotas;
	}

	public void setbConNotas(boolean bConNotas) {
		this.bConNotas = bConNotas;
	}

	public Map<String,String> getTiposptpagoHM() {
		return tiposptpagoHM;
	}

	public void setTiposptpagoHM(Map<String,String> tiposptpagoHM) {
		this.tiposptpagoHM = tiposptpagoHM;
	}

	public Map<String,String> getTiposcoimptHM() {
		return tiposcoimptHM;
	}

	public void setTiposcoimptHM(Map<String,String> tiposcoimptHM) {
		this.tiposcoimptHM = tiposcoimptHM;
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

	public String getsIMNGASBA() {
		return sIMNGASBA;
	}

	public void setsIMNGASBA(String sIMNGASBA) {
		this.sIMNGASBA = sIMNGASBA;
	}

	public String getsComparadorBA() {
		return sComparadorBA;
	}

	public void setsComparadorBA(String sComparadorBA) {
		this.sComparadorBA = sComparadorBA;
	}

	public boolean isbSeleccionadoBA() {
		return bSeleccionadoBA;
	}

	public void setbSeleccionadoBA(boolean bSeleccionadoBA) {
		this.bSeleccionadoBA = bSeleccionadoBA;
	}

	public String getsEstadoBA() {
		return sEstadoBA;
	}

	public void setsEstadoBA(String sEstadoBA) {
		this.sEstadoBA = sEstadoBA;
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

	public String getsNUFIREB() {
		return sNUFIREB;
	}

	public void setsNUFIREB(String sNUFIREB) {
		this.sNUFIREB = sNUFIREB;
	}

	public String getsNURCATB() {
		return sNURCATB;
	}

	public void setsNURCATB(String sNURCATB) {
		this.sNURCATB = sNURCATB.trim();
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

	public String getsIMNGASBP() {
		return sIMNGASBP;
	}

	public void setsIMNGASBP(String sIMNGASBP) {
		this.sIMNGASBP = sIMNGASBP;
	}

	public String getsComparadorBP() {
		return sComparadorBP;
	}

	public void setsComparadorBP(String sComparadorBP) {
		this.sComparadorBP = sComparadorBP;
	}

	public boolean isbSeleccionadoBP() {
		return bSeleccionadoBP;
	}

	public void setbSeleccionadoBP(boolean bSeleccionadoBP) {
		this.bSeleccionadoBP = bSeleccionadoBP;
	}

	public String getsEstadoBP() {
		return sEstadoBP;
	}

	public void setsEstadoBP(String sEstadoBP) {
		this.sEstadoBP = sEstadoBP;
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

	public Map<String,String> getTiposcogrugHM() {
		return tiposcogrugHM;
	}

	public void setTiposcogrugHM(Map<String,String> tiposcogrugHM) {
		this.tiposcogrugHM = tiposcogrugHM;
	}

	public Map<String,String> getTiposcomparaimporteHM() {
		return tiposcomparaimporteHM;
	}

	public void setTiposcomparaimporteHM(Map<String,String> tiposcomparaimporteHM) {
		this.tiposcomparaimporteHM = tiposcomparaimporteHM;
	}
}

//Autor: Francisco Valverde Manjón