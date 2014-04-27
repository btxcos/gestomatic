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
import com.provisiones.ll.CLDescripciones;
import com.provisiones.ll.CLGastos;
import com.provisiones.ll.CLPagos;
import com.provisiones.ll.CLProvisiones;
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Gasto;
import com.provisiones.types.movimientos.MovimientoGasto;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.GastoTabla;
import com.provisiones.types.tablas.ProvisionTabla;

public class GestorAbonos implements Serializable 
{
	private static final long serialVersionUID = -6798102863183547873L;

	private static Logger logger = LoggerFactory.getLogger(GestorAbonos.class.getName());

	//Buscar Activo
	private String sCOACESB = "";

	//Filtro Gasto Activo
	private String sCOGRUGBA = "";
	private String sCOTPGABA = "";
	private String sCOSBGABA = "";
	private String sFEDEVEBA = "";
	private String sEstadoBA = "";
	
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

	
	//Filtro Gasto provision
	private String sCOGRUGBP = "";
	private String sCOTPGABP = "";
	private String sCOSBGABP = "";
	private String sFEDEVEBP = "";
	private String sCOACESBP = "";
	private String sEstadoBP = "";
	
	//Gasto Buscado
	private long liCodGastoB = 0;
	
	
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
	private String sFEPAGA = "";
	private String sFELIPG = "";
	private String sCOSIGA = "";
	private String sEstado = "";
	private String sFEEESI = "";
	private String sFEECOI = "";
	private String sFEEAUI = "";
	private String sFEEPAI = "";

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

	private String sCOIMPT = "";
	private String sDCOIMPT = "";
	
	//private String sCOTNEG = ValoresDefecto.DEF_COTNEG;
	
	//private String sCOENCX = ValoresDefecto.DEF_COENCX;
	//private String sCOOFCX = ValoresDefecto.DEF_COOFCX;
	//private String sNUCONE = ValoresDefecto.DEF_NUCONE;
	private String sNUPROF = "";
	
	private String sFEPGPR = "";
	//private String sCOMONA = ValoresDefecto.DEF_COMONA;
	private String sBIAUTO = "";
	private String sFEAUFA = "";
	//private String sCOTERR = ValoresDefecto.DEF_COTERR;
	//private String sFMPAGN = ValoresDefecto.DEF_FMPAGN;
	//private String sFEPGPR = ValoresDefecto.DEF_FEPGPR;
	
	//private String sFEAPLI = ValoresDefecto.DEF_FEAPLI;
	
	//private String sCOAPII = ValoresDefecto.DEF_COAPII;
	//private String sCOSPII = ValoresDefecto.DEF_COSPII_GA;
	//private String sNUCLII = ValoresDefecto.DEF_NUCLII;
	
	//Abono parcial
	private String sIMNGASA = "";
	private boolean bIMNGASA = true;
	private String sIMRGASA = "";
	private boolean bIMRGASA = true;
	private String sIMDGASA = "";
	private boolean bIMDGASA = true;
	private String sIMCOSTA = "";
	private boolean bIMCOSTA = true;
	private String sIMOGASA = "";
	private boolean bIMOGASA = true;
	private String sIMDTGAA = "";
	private boolean bIMDTGAA = true;
	private String sIMIMGAA = "";	
	private boolean bIMIMGAA = true;
	
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
	
	private Map<String,String> tiposestadogastoHM = new LinkedHashMap<String, String>();

	private transient ActivoTabla activoseleccionado = null;
	private transient ArrayList<ActivoTabla> tablaactivos = null;

	private transient ProvisionTabla provisionseleccionada = null;
	private transient ArrayList<ProvisionTabla> tablaprovisiones = null;

	private transient GastoTabla gastoactivoseleccionado = null;
	private transient ArrayList<GastoTabla> tablagastosactivo = null;
	
	private transient GastoTabla gastoprovisionseleccionado = null;
	private transient ArrayList<GastoTabla> tablagastosprovision = null;
	
	public GestorAbonos()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorAbonos...");

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
			
			tiposestadogastoHM.put("AUTORIZADO","3");
			tiposestadogastoHM.put("PAGADO",    "4");

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
	}
	
	public void borrarResultadosActivo()
	{
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {    	
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

	public void borrarImportesAbono()
	{
		this.sIMNGASA = "";
		this.bIMNGASA = true;
		this.sIMRGASA = "";
		this.bIMRGASA = true;
		this.sIMDGASA = "";
		this.bIMDGASA = true;
		this.sIMCOSTA = "";
		this.bIMCOSTA = true;
		this.sIMOGASA = "";
		this.bIMOGASA = true;
		this.sIMDTGAA = "";
		this.bIMDTGAA = true;
		this.sIMIMGAA = "";	
		this.bIMIMGAA = true;
	}
 
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	borrarCamposBuscar();
    	borrarCamposBuscarActivo();
    	borrarCamposBuscarProvision();
    	borrarCamposBuscarGastoActivo();
    	borrarCamposBuscarGastoProvision();
    	borrarCamposGasto();
    	borrarImportesAbono();
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
    
	public void buscarActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.activoseleccionado = null;
			
			this.setTablaactivos(null);
			
			if (sNURCATB.isEmpty())
			{
				ActivoTabla filtro = new ActivoTabla(
						"", 
						sCOPOINB.toUpperCase(), 
						sNOMUINB.toUpperCase(),
						sNOPRACB.toUpperCase(), 
						sNOVIASB.toUpperCase(), 
						sNUPIACB.toUpperCase(), 
						sNUPOACB.toUpperCase(), 
						sNUPUACB.toUpperCase(), 
						"");
				
				this.setTablaactivos(CLGastos.buscarActivosConGastosAbonables(filtro));
				
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
				this.setTablaprovisiones(CLProvisiones.buscarProvisionesAbonablesFecha(sFecha));

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
			
			
			if (sCOACESB.isEmpty())
			{
				sMsg = "No se informó el campo 'Activo'. Por favor, revise los datos.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
			}
			else
			{
				try
				{
					Integer.parseInt(sCOACESB);
					
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
							sFEDEVEBA,   
							"",   
							"",  
							"",
							"",
							"",//TODO meter estado en el filtro
							"");
					
					
					
					this.setTablagastosactivo(CLGastos.buscarGastosAbonablesActivoConFiltroEstado(filtro,sEstadoBA));
					
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
				catch(NumberFormatException nfe)
				{
					sMsg = "ERROR: El Activo debe ser numérico. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
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
			
			if (sNUPROFB.isEmpty())
			{
				sMsg = "No se informó el campo 'Provisión'. Por favor, revise los datos.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
			}
			else if (!sCOACESBP.isEmpty() && Utils.esAlfanumerico(sCOACESBP))
			{
				sMsg = "ERROR: El Activo debe ser numérico. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else
			{
				try
				{
					Integer.parseInt(sNUPROFB);
					
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
							sFEDEVEBP,   
							"",   
							"",  
							"",
							"",
							"",//TODO meter estado en el filtro
							"");
					
					this.setTablagastosprovision(CLGastos.buscarGastosAbonablesProvisionConFiltroEstado(filtro,sEstadoBP));
					
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
				catch(NumberFormatException nfe)
				{
					sMsg = "ERROR: La Provisión debe ser numérica. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
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
	    	
	    	this.liCodGastoB = CLGastos.buscarCodigoGasto(Integer.parseInt(sCOACES),sCOGRUG,sCOTPGA,sCOSBGA,Utils.compruebaFecha(sFEDEVE));

		  	Gasto gasto = CLGastos.buscarGastoConCodigo(liCodGastoB);

	    	logger.debug(gasto.logGasto());
	    	
	    	this.sFEDEVE = Utils.recuperaFecha(gasto.getFEDEVE());
	 
	    	this.setbDevolucion((Integer.parseInt(sCOSBGA) > 49));

			this.sDPTPAGO = gastoseleccionado.getDPTPAGO();

			this.sFFGTVP = Utils.recuperaFecha(gasto.getFFGTVP());
			

			this.sFELIPG = Utils.recuperaFecha(gasto.getFELIPG());

			this.sEstado = CLDescripciones.descripcionEstadoGasto(CLGastos.estadoGasto(liCodGastoB));
			
			this.sFEEESI = Utils.recuperaFecha(gasto.getFEEESI());
			this.sFEECOI = Utils.recuperaFecha(gasto.getFEECOI());
			this.sFEEAUI = Utils.recuperaFecha(gasto.getFEEAUI());
			this.sFEEPAI = Utils.recuperaFecha(gasto.getFEEPAI());
			this.sIMNGAS = Utils.recuperaImporte(gasto.getYCOS02().equals("-"),gasto.getIMNGAS());
			this.sIMRGAS = Utils.recuperaImporte(gasto.getYCOS04().equals("-"),gasto.getIMRGAS());
			this.sIMDGAS = Utils.recuperaImporte(gasto.getYCOS06().equals("-"),gasto.getIMDGAS());
			this.sIMCOST = Utils.recuperaImporte(gasto.getYCOS08().equals("-"),gasto.getIMCOST());
			this.sIMOGAS = Utils.recuperaImporte(gasto.getYCOS10().equals("-"),gasto.getIMOGAS());
			this.sIMDTGA = Utils.recuperaImporte(false,gasto.getIMDTGA());
			this.sIMIMGA = Utils.recuperaImporte(false,gasto.getIMIMGA());
			this.setsDCOIMPT(CLDescripciones.descripcionTipoImpuestoGasto(gasto.getCOIMPT()));
			this.sCOIMPT = gasto.getCOIMPT();
			
			this.sBIAUTO = "";
			this.sFEAUFA = "0";
			
			if (CLPagos.estaPagado(liCodGastoB))
			{
				//TODO sacar de datos de pago
				//this.sFEPGPR = Utils.recuperaFecha(gasto.getFEPGPR());
				//TODO sacar de datos de pago
				//this.sFEPAGA = Utils.recuperaFecha(gasto.getFEPAGA());
				this.sFEPGPR = CLPagos.buscarFechaPago(CLPagos.buscarCodigoPago(liCodGastoB));
				this.sFEPAGA = sFEPGPR;
			}

			
			this.sCOUNMO = ValoresDefecto.DEF_COUNMO;
			
			//TODO sacar de datos de pago
			//this.sCOENCX = ValoresDefecto.DEF_COENCX;
			//this.sCOOFCX = ValoresDefecto.DEF_COOFCX;
			//this.sNUCONE = ValoresDefecto.DEF_NUCONE;
			
			this.sNUPROF = CLGastos.buscarProvisionGasto(Integer.parseInt(sCOACES), sCOGRUG, sCOTPGA, sCOSBGA, gasto.getFEDEVE());

			cargarImportes();
	    	
	    	sMsg = "Gasto cargado.";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	
	    	logger.info(sMsg);
	    	
  			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void cargarImportes()
    { 
		if (ConnectionManager.comprobarConexion())
		{


			this.sIMNGASA = sIMNGAS;
			this.bIMNGASA = sIMNGAS.equals("0");
			this.sIMRGASA = sIMRGAS;
			this.bIMRGASA = sIMRGAS.equals("0");
			this.sIMDGASA = sIMDGAS;
			this.bIMDGASA = sIMDGAS.equals("0");
			this.sIMCOSTA = sIMCOST;
			this.bIMCOSTA = sIMCOST.equals("0");
			this.sIMOGASA = sIMOGAS;
			this.bIMOGASA = sIMOGAS.equals("0");

			this.sIMDTGAA = sIMDTGA;
			this.bIMDTGAA = sIMDTGA.equals("0");
			this.sIMIMGAA = sIMIMGA;
			this.bIMIMGAA = sIMIMGA.equals("0");
			
			logger.debug("sIMNGASA:|"+sIMNGASA+"|");
			logger.debug("sIMRGASA:|"+sIMRGASA+"|");
			logger.debug("sIMDGASA:|"+sIMDGASA+"|");
			logger.debug("sIMCOSTA:|"+sIMCOSTA+"|");
			logger.debug("sIMOGASA:|"+sIMOGASA+"|");

			logger.debug("sIMDTGAA:|"+sIMDTGAA+"|");
			logger.debug("sIMIMGAA:|"+sIMIMGAA+"|");


	    	
		}
    }
	
    public void restaurarImportes(ActionEvent actionEvent) 
    {  
    	FacesMessage msg;
    	
    	String sMsg = "";
    	
    	cargarImportes();
    	
    	sMsg = "Importes cargados.";
    	msg = Utils.pfmsgInfo(sMsg);
    	
    	logger.info(sMsg);
    	
		FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void registrarAbono()
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
				else
				{
					this.sNUPROF = CLProvisiones.provisionAsignada(Integer.parseInt(sCOACES),sCOGRUG,sCOTPGA);
					
					if (sNUPROF.isEmpty())
					{
						sMsg = "[FATAL] No se pudo asignar una Provisión al Abono. Por favor, avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
					}
					else
					{
						MovimientoGasto movimiento = new MovimientoGasto (
								sCOACES,
								sCOGRUG,
								sCOTPGA,
								sCOSBGA,
								sPTPAGO,
								Utils.compruebaFecha(sFEDEVE),
								Utils.compruebaFecha(sFFGTVP),
								Utils.compruebaFecha(sFEPAGA),
								Utils.compruebaFecha(sFELIPG),
								sCOSIGA,
								Utils.compruebaFecha(sFEEESI),
								Utils.compruebaFecha(sFEECOI),
								Utils.compruebaFecha(sFEEAUI),
								Utils.compruebaFecha(sFEEPAI),
								Utils.invierteSigno(Utils.compruebaImporte(sIMNGASA)),
								sYCOS02,
								Utils.invierteSigno(Utils.compruebaImporte(sIMRGASA)),
								sYCOS04,
								Utils.invierteSigno(Utils.compruebaImporte(sIMDGASA)),
								sYCOS06,
								Utils.invierteSigno(Utils.compruebaImporte(sIMCOSTA)),
								sYCOS08,
								Utils.invierteSigno(Utils.compruebaImporte(sIMOGASA)),
								sYCOS10,
								Utils.compruebaImporte(sIMDTGAA),
								ValoresDefecto.DEF_COUNMO,
								Utils.compruebaImporte(sIMIMGAA),
								Utils.compruebaCodigoNum(sCOIMPT),
								ValoresDefecto.DEF_COTNEG,
								ValoresDefecto.DEF_COENCX,
								ValoresDefecto.DEF_COOFCX,
								ValoresDefecto.DEF_NUCONE,
								sNUPROF,
								ValoresDefecto.DEF_FEAGTO,
								ValoresDefecto.DEF_COMONA,
								sBIAUTO,
								Utils.compruebaFecha(sFEAUFA),
								ValoresDefecto.DEF_COTERR,
								ValoresDefecto.DEF_FMPAGN,
								Utils.compruebaFecha(sFEPGPR),
								ValoresDefecto.DEF_FEAPLI,
								ValoresDefecto.DEF_COAPII,
								ValoresDefecto.DEF_COSPII_GA,
								ValoresDefecto.DEF_NUCLII);


						if (Math.abs(movimiento.getValor_total())>Math.abs(CLGastos.buscarValorTotal(liCodGastoB)))
						{
							sMsg = "ERROR: El valor del Abono no puede ser superior al del Gasto. Por favor, revise los datos.";
							msg = Utils.pfmsgError(sMsg);
							logger.error(sMsg);
						}
						else
						{
							int iSalida = CLGastos.registraMovimiento(movimiento,true);
							
							switch (iSalida) 
							{
							case 0: //Sin errores
								sMsg = "El Abono se ha asignado correctamente a la Provisión '"+sNUPROF+"'.";
								msg = Utils.pfmsgInfo(sMsg);
								logger.info(sMsg);
								break;

							case -2: //Error 002 - Llega fecha de anulación y no existe Gasto en la tabla
								sMsg = "ERROR:002 - El Gasto que se anula no existe. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;

							case -3: //Error 003 - Llega un abono de un Gasto que NO está autorizado o pagado
								sMsg = "ERROR:003 - El Gasto a abonar tienen que estar autorizado o pagado. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;

							case -4: //Error 004 - Descuento mayor que importe nominal del Gasto
								sMsg = "ERROR:004 - El descuento informado es superior al Gasto. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;

							case -6: //Error 006 - La provisión ya está cerrada
								sMsg = "ERROR:006 - La provisión ya esta cerrada. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;

							case -7: //Error 007 - Error en grupo / tipo / subtipo de acción
								sMsg = "ERROR:007 - El grupo, tipo y subtipo de Gasto deben informarse. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;

							case -8: //Error 008 - No existe el activo en la base corporativa
								sMsg = "ERROR:008 - El activo informado no se encuentra resistrado en el sistema. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;

							case -12: //Error 012 - Llega un abono de un Gasto que está anulado
								sMsg = "ERROR:012 - El Gasto a abonar esta anulado. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;

							case -13: //Error 013 - Llega un abono de un Gasto que ya está abonado, o bien está en la misma provisión sin anular.
								sMsg = "ERROR:013 - El Gasto a abonar ya esta abonado. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;

							case -19: //Error 019 - Periodicidad del Gasto es cero o espacios.
								sMsg = "ERROR:019 - El campo periodicidad del pago es obligatorio. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;

							case -23: //Error 023 - Llega anulación de un Gasto que YA está pagado
								sMsg = "ERROR:023 - El Gasto a anular ya esta pagado. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;

							case -24: //Error 024 - Llega modificación de un Gasto que YA está pagado
								sMsg = "ERROR:024 - El Gasto a modificar ya esta pagado. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;

							case -61: //Error 061 - La provisión ya está cerrada pero se ha actualizado la fecha de pago a proveedor.
								sMsg = "ERROR:061 - La provision esta cerrada, no se puede actualizar la fecha de pago a proveedor. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;
								
							case -62: //Error 062 - Llega una devolución con importe positivo. 
								sMsg = "ERROR:062 - La devolucion debe incluir un importe del Gasto con valor negativo. Por favor, revise los datos.";
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
								
							case -707: //Error 707 - Fecha de anulacion del Gasto incorrecta  
								sMsg = "ERROR:707 - La fecha de anulacion de Gasto esta incorrectamente informada. Por favor, cargue los datos del activo.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;
								
							case -708: //Error 708 - Fecha de pago al proveedor incorrecta
								sMsg = "ERROR:708 - La fecha de pago al proveedor esta incorrectamente informada. Por favor, cargue los datos del activo.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;				
								
							case -709: //Error 709 - Importe del Gasto incorrecto
								sMsg = "ERROR:709 - El importe del Gasto esta incorrectamente informada. Por favor, cargue los datos del activo.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;
								
							case -710: //Error 710 - Recargo en el importe del Gasto incorrecto
								sMsg = "ERROR:710 - El recargo en el importe del Gasto esta incorrectamente informada. Por favor, cargue los datos del activo.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;
								
							case -711: //Error 711 - Importe de demora del Gasto incorrecto
								sMsg = "ERROR:711 - El importe de demora del Gasto esta incorrectamente informada. Por favor, cargue los datos del activo.";
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

							case -802: //Error 802 - No se ha elegido una situacion del Gasto
								sMsg = "ERROR:802 - No se ha elegido una situacion del Gasto. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;
								
							case -803: //Error 803 - No se ha informado el campo importe de Gasto
								sMsg = "ERROR:803 - No se ha informado el campo importe de Gasto. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;

							case -804: //Error 804 - Accion no permitida
								sMsg = "ERROR:804 - No se pueden registrar los datos. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;

							case -805: //Error 805 - estado no disponible
								sMsg = "ERROR:805 - El estado del Gasto no esta disponible. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;

							case -806: //Error 806 - modificacion sin cambios
								sMsg = "ERROR:806 - No hay modificaciones que realizar. Por favor, revise los datos.";
								msg = Utils.pfmsgError(sMsg);
								logger.error(sMsg);
								break;
								
							case -807: //Error 807 - Fecha de estado sin informar
								sMsg = "ERROR:807 - No se ha informado ninguna fecha de estado del Gasto. Por favor, revise los datos.";
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

							case -901: //Error 901 - error y rollback - error al crear el Gasto
								sMsg = "[FATAL] ERROR:901 - Se ha producido un error al registrar el nuevo Gasto. Por favor, revise los datos y avise a soporte.";
								msg = Utils.pfmsgFatal(sMsg);
								logger.error(sMsg);
								break;
								
							case -902: //Error 902 - error y rollback - error al registrar la relaccion
								sMsg = "[FATAL] ERROR:902 - Se ha producido un error al registrar la relacion. Por favor, revise los datos y avise a soporte.";
								msg = Utils.pfmsgFatal(sMsg);
								logger.error(sMsg);
								break;

							case -903: //Error 903 - error y rollback - error al cambiar el estado
								sMsg = "[FATAL] ERROR:903 - Se ha producido un error al cambiar el estado del Gasto. Por favor, revise los datos y avise a soporte.";
								msg = Utils.pfmsgFatal(sMsg);
								logger.error(sMsg);
								break;

							case -904: //Error 904 - error y rollback - error al revisar el Gasto
								sMsg = "[FATAL] ERROR:904 - Se ha producido un error al revisar el Gasto. Por favor, revise los datos y avise a soporte.";
								msg = Utils.pfmsgFatal(sMsg);
								logger.error(sMsg);
								break;

							case -905: //Error 905 - error y rollback - error al modificar el Gasto
								sMsg = "[FATAL] ERROR:905 - Se ha producido un error al modificar el Gasto. Por favor, revise los datos y avise a soporte.";
								msg = Utils.pfmsgFatal(sMsg);
								logger.error(sMsg);
								break;
								
							case -906: //Error 906 - error y rollback - error al registrar la relaccion en provision
								sMsg = "[FATAL] ERROR:906 - Se ha producido un error al registra la relacion con la provisión. Por favor, revise los datos y avise a soporte.";
								msg = Utils.pfmsgFatal(sMsg);
								logger.error(sMsg);
								break;
								
							case -907: //Error 907 - error y rollback - error al registrar la fecha de anulacion
								sMsg = "[FATAL] ERROR:907 - Se ha producido un error al registra la fecha de anulación del Gasto. Por favor, revise los datos y avise a soporte.";
								msg = Utils.pfmsgFatal(sMsg);
								logger.error(sMsg);
								break;

							case -908: //Error 908 - error y rollback - error al eliminar el Gasto
								sMsg = "[FATAL] ERROR:908 - Se ha producido un error al eliminar el Gasto. Por favor, revise los datos y avise a soporte.";
								msg = Utils.pfmsgFatal(sMsg);
								logger.error(sMsg);
								break;

							case -909: //Error 909 - error y rollback - error al actualizar la provision
								sMsg = "[FATAL] ERROR:909 - Se ha producido un error al actualizar la provisión. Por favor, revise los datos y avise a soporte.";
								msg = Utils.pfmsgFatal(sMsg);
								logger.error(sMsg);
								break;
								
							case -910: //Error 910 - error y rollback - error al conectar con la base de datos
								sMsg = "[FATAL] ERROR:910 - Se ha producido un error al conectar con la base de datos. Por favor, revise los datos y avise a soporte.";
								msg = Utils.pfmsgFatal(sMsg);
								logger.error(sMsg);
								break;

							case -911: //Error 911 - error y rollback - error al registar el abono
								sMsg = "[FATAL] ERROR:911 - Se ha producido un error al registar el abono. Por favor, revise los datos y avise a soporte.";
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
		this.sCOACESB = sCOACESB;
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
		this.sNURCATB = sNURCATB;
	}

	public String getsNUPROFB() {
		return sNUPROFB;
	}

	public void setsNUPROFB(String sNUPROFB) {
		this.sNUPROFB = sNUPROFB;
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
		this.sCOACESBP = sCOACESBP;
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

	public String getsFEPAGA() {
		return sFEPAGA;
	}

	public void setsFEPAGA(String sFEPAGA) {
		this.sFEPAGA = sFEPAGA;
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

	public String getsFEEPAI() {
		return sFEEPAI;
	}

	public void setsFEEPAI(String sFEEPAI) {
		this.sFEEPAI = sFEEPAI;
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

	public String getsIMNGASA() {
		return sIMNGASA;
	}

	public void setsIMNGASA(String sIMNGASA) {
		this.sIMNGASA = sIMNGASA;
	}

	public String getsIMRGASA() {
		return sIMRGASA;
	}

	public void setsIMRGASA(String sIMRGASA) {
		this.sIMRGASA = sIMRGASA;
	}

	public String getsIMDGASA() {
		return sIMDGASA;
	}

	public void setsIMDGASA(String sIMDGASA) {
		this.sIMDGASA = sIMDGASA;
	}

	public String getsIMCOSTA() {
		return sIMCOSTA;
	}

	public void setsIMCOSTA(String sIMCOSTA) {
		this.sIMCOSTA = sIMCOSTA;
	}

	public String getsIMOGASA() {
		return sIMOGASA;
	}

	public void setsIMOGASA(String sIMOGASA) {
		this.sIMOGASA = sIMOGASA;
	}

	public String getsIMDTGAA() {
		return sIMDTGAA;
	}

	public void setsIMDTGAA(String sIMDTGAA) {
		this.sIMDTGAA = sIMDTGAA;
	}

	public String getsIMIMGAA() {
		return sIMIMGAA;
	}

	public void setsIMIMGAA(String sIMIMGAA) {
		this.sIMIMGAA = sIMIMGAA;
	}

	public boolean isbIMNGASA() {
		return bIMNGASA;
	}

	public void setbIMNGASA(boolean bIMNGASA) {
		this.bIMNGASA = bIMNGASA;
	}

	public boolean isbIMRGASA() {
		return bIMRGASA;
	}

	public void setbIMRGASA(boolean bIMRGASA) {
		this.bIMRGASA = bIMRGASA;
	}

	public boolean isbIMDGASA() {
		return bIMDGASA;
	}

	public void setbIMDGASA(boolean bIMDGASA) {
		this.bIMDGASA = bIMDGASA;
	}

	public boolean isbIMCOSTA() {
		return bIMCOSTA;
	}

	public void setbIMCOSTA(boolean bIMCOSTA) {
		this.bIMCOSTA = bIMCOSTA;
	}

	public boolean isbIMOGASA() {
		return bIMOGASA;
	}

	public void setbIMOGASA(boolean bIMOGASA) {
		this.bIMOGASA = bIMOGASA;
	}

	public boolean isbIMDTGAA() {
		return bIMDTGAA;
	}

	public void setbIMDTGAA(boolean bIMDTGAA) {
		this.bIMDTGAA = bIMDTGAA;
	}

	public boolean isbIMIMGAA() {
		return bIMIMGAA;
	}

	public void setbIMIMGAA(boolean bIMIMGAA) {
		this.bIMIMGAA = bIMIMGAA;
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

	public String getsEstadoBA() {
		return sEstadoBA;
	}

	public void setsEstadoBA(String sEstadoBA) {
		this.sEstadoBA = sEstadoBA;
	}

	public String getsEstadoBP() {
		return sEstadoBP;
	}

	public void setsEstadoBP(String sEstadoBP) {
		this.sEstadoBP = sEstadoBP;
	}

	public Map<String, String> getTiposestadogastoHM() {
		return tiposestadogastoHM;
	}

	public void setTiposestadogastoHM(Map<String, String> tiposestadogastoHM) {
		this.tiposestadogastoHM = tiposestadogastoHM;
	}

}
