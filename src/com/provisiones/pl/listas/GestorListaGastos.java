package com.provisiones.pl.listas;

import java.io.IOException;
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
import com.provisiones.ll.CLGastos;
import com.provisiones.ll.CLProvisiones;
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Sesion;
import com.provisiones.misc.Utils;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.GastoTabla;
import com.provisiones.types.tablas.ProvisionTabla;

public class GestorListaGastos implements Serializable 
{
	private static final long serialVersionUID = 2074872725730594297L;

	private static Logger logger = LoggerFactory.getLogger(GestorListaGastos.class.getName());

	private String sCOACES = "";
	private String sNUPROF = "";
	
	private String sCOGRUG = "";
	private String sCOTPGA = "";
	private String sCOSBGA = "";
	private String sFEDEVE = "";
	
	private String sCodGasto = "";
	
	//Busqueda Activo
	private String sCOPOIN = "";
	private String sNOMUIN = "";
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	private String sNUFIRE = "";
	
	private String sNURCAT = "";
	
	
	//Filtro Gastos Activo
	private String sCOGRUGFA = "";
	private String sCOTPGAFA = "";
	private String sCOSBGAFA = "";
	private String sFEDEVEFA = "";
	private String sIMNGASFA = "";
	private String sComparadorFA = "";
	private boolean bSeleccionadoFA = true; 
	private String sEstadoGastoFA = "";
	
	//Filtro Gastos Provision
	private String sCOGRUGFP = "";
	private String sCOTPGAFP = "";
	private String sCOSBGAFP = "";
	private String sFEDEVEFP = "";
	private String sIMNGASFP = "";
	private String sComparadorFP = "";
	private boolean bSeleccionadoFP = true;
	private String sEstadoGastoFP = "";
	
	//Filtro de provision
	private String sFEPFON = "";
	private String sEstadoProvision = "";
	
	//Filtro fecha limite
	private String sFELIPG = "";
	
	
	
	private transient ActivoTabla activoseleccionado = null;
	private transient ArrayList<ActivoTabla> tablaactivos = null;

	private transient ProvisionTabla provisionseleccionada = null;
	private transient ArrayList<ProvisionTabla> tablaprovisiones = null;
	
	private transient GastoTabla gastoseleccionado = null;
	private transient ArrayList<GastoTabla> tablagastos = null;

	private Map<String,String> tiposcogrugHM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcotpgaHM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbgaHM = new LinkedHashMap<String, String>();
	
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
	
	private Map<String,String> tiposcomparaimporteHM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposestadogastoHM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposestadoprovisionHM = new LinkedHashMap<String, String>();

	public GestorListaGastos()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorListaGastos...");
			
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

			tiposcomparaimporteHM.put("Igual a",    		"=");
			tiposcomparaimporteHM.put("Mayor o igual a",	">=");
			tiposcomparaimporteHM.put("Menor o igual a",	"<=");
			
			tiposestadogastoHM.put("ESTIMADO",	"1");
			tiposestadogastoHM.put("CONOCIDO",	"2");
			tiposestadogastoHM.put("AUTORIZADO","3");
			tiposestadogastoHM.put("PAGADO",    "4");
			tiposestadogastoHM.put("ANULADO",	"5");
			tiposestadogastoHM.put("ABONADO",	"6");
			
			tiposestadoprovisionHM.put("ABIERTA",	"P");
			tiposestadoprovisionHM.put("ENVIADA",	"E");
			tiposestadoprovisionHM.put("AUTORIZADA","T");
			tiposestadoprovisionHM.put("PAGADA",	"G");
		}
	}
	
	public void borrarCamposGasto()
	{
		this.sCOACES = "";
    	this.sNUPROF = "";
    	
    	this.setGastoseleccionado(null);
    	this.setTablagastos(null);
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
		this.sNUFIRE = "";
		
		this.sNURCAT = "";
    	
    	this.setActivoseleccionado(null);
    	this.setTablaactivos(null);
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	borrarCamposActivo();
    }
    
	public void borrarCamposFiltroGastosActivo()
	{
		this.sCOGRUGFA = "";
		this.sCOTPGAFA = "";
		this.sCOSBGAFA = "";
		this.sFEDEVEFA = "";
		this.sIMNGASFA = "";
		this.sComparadorFA = "";
		this.bSeleccionadoFA = true; 
		this.sEstadoGastoFA = "";
	}
	
    public void limpiarPlantillaFiltroGastosActivo(ActionEvent actionEvent) 
    {  
    	borrarCamposFiltroGastosActivo();
    }
    
	public void borrarCamposFiltroGastosProvision()
	{
		this.sCOGRUGFP = "";
		this.sCOTPGAFP = "";
		this.sCOSBGAFP = "";
		this.sFEDEVEFP = "";
		this.sIMNGASFP = "";
		this.sComparadorFP = "";
		this.bSeleccionadoFP = true; 
		this.sEstadoGastoFP = "";
	}
	
    public void limpiarPlantillaFiltroGastosProvision(ActionEvent actionEvent) 
    {  
    	borrarCamposFiltroGastosProvision();
    }
    
	public void borrarCamposProvision()
	{
		this.sFEPFON = "";
		this.sEstadoProvision = "";
    	
    	this.setProvisionseleccionada(null);
    	this.setTablaprovisiones(null);
	}
	
    public void limpiarPlantillaProvision(ActionEvent actionEvent) 
    {  
    	borrarCamposProvision();
    }
    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	borrarCamposGasto();
    	borrarCamposActivo();
    	borrarCamposProvision();
    	
    	this.sFELIPG= ""; 
    }
    
	public void buscarActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
	    	this.setActivoseleccionado(null);
			
	    	if (!sCOPOIN.isEmpty() && Utils.esAlfanumerico(sCOPOIN))
			{
				sMsg = "ERROR: El Código Postal debe ser numérico. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else if (sNURCAT.isEmpty())
			{
				ActivoTabla filtro = new ActivoTabla(
						sCOACES.toUpperCase(), 
						sCOPOIN, 
						sNOMUIN.toUpperCase(),
						sNOPRAC.toUpperCase(), 
						sNOVIAS.toUpperCase(), 
						sNUPIAC.toUpperCase(), 
						sNUPOAC.toUpperCase(), 
						sNUPUAC.toUpperCase(), 
						sNUFIRE.toUpperCase(),
						"");
				
				this.setTablaactivos(CLGastos.buscarActivosConGastos(filtro));

				sMsg = "Encontrados "+getTablaactivos().size()+" activos relacionados.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}
			else if (CLReferencias.existeReferenciaCatastral(sNURCAT))
			{
				this.setTablaactivos(CLReferencias.buscarActivoAsociadoConGastos(sNURCAT));
				
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
		    	this.setTablaactivos(null);
				
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
	    	
	    	String sMsg = ""; 
	    	
	    	this.sCOACES  = activoseleccionado.getCOACES();
	    	
	    	sMsg = "Activo '"+sCOACES+"' seleccionado.";
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
			
			String sFecha = Utils.compruebaFecha(sFEPFON);
			
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
				String sINGRESADOF = "";
				String sESTADOF = sEstadoProvision;
				
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
				
				this.setTablaprovisiones(CLProvisiones.buscarProvisionesConFiltro(filtro));

				if (getTablaprovisiones().size() == 0)
				{
					sMsg = "No se encontraron Provisiones con los criterios solicitados.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);
				}
				else if (getTablaprovisiones().size() == 1)
				{
					sMsg = "Encontrada una Provisión relacionada.";
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
	    	
	    	String sMsg = "";

	    	this.sNUPROF  = provisionseleccionada.getNUPROF();
	    	
	    	sMsg = "Provision '"+sNUPROF+"' seleccionada.";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	
	    	logger.info(sMsg);
	    	
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }

	public void cambiaGrupo()
	{
		tiposcotpgaHM = new LinkedHashMap<String, String>();
		tiposcosbgaHM = new LinkedHashMap<String, String>();
	}
	
	public void cambiaTipoActivo()
	{

		logger.debug("sCOGRUGFA:|"+sCOGRUGFA+"|");

		if (sCOGRUGFA !=null && !sCOGRUGFA.isEmpty())
		{
			switch (Integer.parseInt(sCOGRUGFA)) 
			{
				case 1:
					tiposcotpgaHM = tiposcotpga_g1HM;
					break;
				case 2:
					tiposcotpgaHM = tiposcotpga_g2HM;
					break;
				case 3:
					tiposcotpgaHM = tiposcotpga_g3HM;
					break;
				default:
					
					break;
			}
			tiposcosbgaHM = new LinkedHashMap<String, String>();
			sCOTPGAFA = "";
			sCOSBGAFA = "";
		}
		else
		{
			tiposcotpgaHM = new LinkedHashMap<String, String>();
			tiposcosbgaHM = new LinkedHashMap<String, String>();
			sCOTPGAFA = "";
			sCOSBGAFA = "";
		}
	}
	
	public void cambiaSubtipoActivo()
	{
		logger.debug("sCOGRUGF:|"+sCOGRUGFA+"| sCOTPGAF:|"+sCOTPGAFA+"|");
		
		if (sCOTPGAFA !=null && !sCOTPGAFA.isEmpty())
		{
			switch (Integer.parseInt(sCOGRUGFA+sCOTPGAFA)) 
			{
				case 11:
					tiposcosbgaHM = tiposcosbga_t11HM;
					break;
				case 12:
					tiposcosbgaHM = tiposcosbga_t12HM;
					break;
				case 21:
					tiposcosbgaHM = tiposcosbga_t21HM;
					break;
				case 22:
					tiposcosbgaHM = tiposcosbga_t22HM;
					break;
				case 23:
					tiposcosbgaHM = tiposcosbga_t23HM;
					break;
				case 32:
					tiposcosbgaHM = tiposcosbga_t32HM;
					break;
				case 33:
					tiposcosbgaHM = tiposcosbga_t33HM;
					break;
				default:
					tiposcosbgaHM = new LinkedHashMap<String, String>();
					break;
			}
			sCOSBGAFA = "";
		}
	}
	
	public void cambiaTipoProvision()
	{

		logger.debug("sCOGRUGFP:|"+sCOGRUGFP+"|");

		if (sCOGRUGFP !=null && !sCOGRUGFP.isEmpty())
		{
			switch (Integer.parseInt(sCOGRUGFP)) 
			{
				case 1:
					tiposcotpgaHM = tiposcotpga_g1HM;
					break;
				case 2:
					tiposcotpgaHM = tiposcotpga_g2HM;
					break;
				case 3:
					tiposcotpgaHM = tiposcotpga_g3HM;
					break;
				default:
					tiposcotpgaHM = new LinkedHashMap<String, String>();
					break;
			}
			tiposcosbgaHM = new LinkedHashMap<String, String>();
			sCOTPGAFP = "";
			sCOSBGAFP = "";
		}
		else
		{
			tiposcotpgaHM = new LinkedHashMap<String, String>();
			tiposcosbgaHM = new LinkedHashMap<String, String>();
			sCOTPGAFP = "";
			sCOSBGAFP = "";
		}
	}
	
	public void cambiaSubtipoProvision()
	{
		logger.debug("sCOGRUGFP:|"+sCOGRUGFP+"| sCOTPGAF:|"+sCOTPGAFP+"|");
		
		if (sCOTPGAFP !=null && !sCOTPGAFP.isEmpty())
		{
			switch (Integer.parseInt(sCOGRUGFP+sCOTPGAFP)) 
			{
				case 11:
					tiposcosbgaHM = tiposcosbga_t11HM;
					break;
				case 12:
					tiposcosbgaHM = tiposcosbga_t12HM;
					break;
				case 21:
					tiposcosbgaHM = tiposcosbga_t21HM;
					break;
				case 22:
					tiposcosbgaHM = tiposcosbga_t22HM;
					break;
				case 23:
					tiposcosbgaHM = tiposcosbga_t23HM;
					break;
				case 32:
					tiposcosbgaHM = tiposcosbga_t32HM;
					break;
				case 33:
					tiposcosbgaHM = tiposcosbga_t33HM;
					break;
				default:
					tiposcosbgaHM = new LinkedHashMap<String, String>();
					break;
			}
			sCOSBGAFP = "";
		}
	}
	
	public void cambiaComparadorFA()
	{
		this.bSeleccionadoFA = this.sComparadorFA.isEmpty();
		logger.debug("sComparadorFA:|"+sComparadorFA+"|");
	}
	
	public void cambiaComparadorFP()
	{
		this.bSeleccionadoFP = this.sComparadorFP.isEmpty();
	}
	
	public void hoyFEDEVEFA (ActionEvent actionEvent)
	{
		this.setsFEDEVEFA(Utils.fechaDeHoy(true));
		logger.debug("sFEDEVEFA:|"+sFEDEVEFP+"|");
	}

	public void hoyFEDEVEFP (ActionEvent actionEvent)
	{
		this.setsFEDEVEFP(Utils.fechaDeHoy(true));
		logger.debug("sFEDEVEFP:|"+sFEDEVEFP+"|");
	}
	
	public void hoyFELIPG (ActionEvent actionEvent)
	{
		this.setsFELIPG(Utils.fechaDeHoy(true));
		logger.debug("sFELIPG:|"+sFELIPG+"|");
	}
	
	public void buscarGastosActivo (ActionEvent actionEvent)
	{

		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
	    	this.setGastoseleccionado(null);
			
			try
			{
				//if (true)
				if (sCOACES.isEmpty())
				{
					sMsg = "ERROR: Debe informar el Activo para realizar una búsqueda. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					
			    	this.setTablagastos(null);
				}
				else if (CLActivos.existeActivo(Integer.parseInt(sCOACES)))
				 
				{
					if (sComparadorFA.isEmpty()) 
					{
						sIMNGASFA = "";
					}
					else
					{
						sIMNGASFA = Utils.compruebaImporte(sIMNGASFA);
					}
					
					GastoTabla filtro = new GastoTabla(
							"",
							"",
							"",   
							sCOACES,   
							sCOGRUGFA,   
							sCOTPGAFA,   
							sCOSBGAFA,   
							"",  
							"",   
							"",  
							Utils.compruebaFecha(sFEDEVEFA),   
							"",   
							"",  
							sIMNGASFA,
							sEstadoGastoFA,
							"",
							"",
							"",
							"");
					
			    	logger.debug("sCOACES:|"+sCOACES+"|");
			    	logger.debug("sCOGRUGFA:|"+sCOGRUGFA+"|");
			    	logger.debug("sCOTPGAFA:|"+sCOTPGAFA+"|");
			    	logger.debug("sCOSBGAFA:|"+sCOSBGAFA+"|");
			    	logger.debug("sComparadorFA:|"+sComparadorFA+"|");
			    	logger.debug("sIMNGASFA:|"+sIMNGASFA+"|");
			    	logger.debug("sFEDEVEFA:|"+sFEDEVEFA+"|");
			    	logger.debug("sEstadoGastoFA:|"+sEstadoGastoFA+"|");
					
					this.setTablagastos(CLGastos.buscarGastosActivoConFiltro(filtro,sComparadorFA));
					
					if (getTablagastos().size() == 0)
					{
						sMsg = "No se encontraron Gastos con los criterios solicitados.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);
						
					}
					else if (getTablagastos().size() == 1)
					{
						sMsg = "Encontrado un Gasto relacionado.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
					else
					{
						sMsg = "Encontrados "+getTablagastos().size()+" Gastos relacionados.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
				}
				else
				{
					sMsg = "El Activo '"+sCOACES+"' no pertenece a la cartera. Por favor, revise los datos.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);
					
			    	this.setTablagastos(null);
				} 
				
			}
			catch(NumberFormatException nfe)
			{
				sMsg = "ERROR: El Activo debe ser numérico. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
				
		    	this.setTablagastos(null);
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
			
			this.setGastoseleccionado(null);
			
			try
			{
				Long.parseLong(sNUPROF);
				
				if (sNUPROF.isEmpty())
				{
					sMsg = "ERROR: Debe informar la Provisión para realizar una búsqueda. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					
					this.setTablagastos(null);
				}
				else if (CLProvisiones.existeProvision(sNUPROF))
				{
					if (sComparadorFP.isEmpty()) 
					{
						sIMNGASFP = "";
					}
					else
					{
						sIMNGASFP = Utils.compruebaImporte(sIMNGASFP);
					}
					
					GastoTabla filtro = new GastoTabla(
							"",
							sNUPROF,   
							"",
							"",   
							sCOGRUGFP,   
							sCOTPGAFP,   
							sCOSBGAFP,
							"",  
							"",   
							"",
							Utils.compruebaFecha(sFEDEVEFP),   
							"",   
							"",  
							sIMNGASFP,
							"",
							"",
							"",
							"",
							"");
					
			    	logger.debug("sCOACES:|"+sCOACES+"|");
			    	logger.debug("sCOGRUGFP:|"+sCOGRUGFP+"|");
			    	logger.debug("sCOTPGAFP:|"+sCOTPGAFP+"|");
			    	logger.debug("sCOSBGAFP:|"+sCOSBGAFP+"|");
			    	logger.debug("sFEDEVEFP:|"+sFEDEVEFP+"|");
			    	logger.debug("sEstadoGastoFP:|"+sEstadoGastoFP+"|");
					
					this.setTablagastos(CLGastos.buscarGastosProvisionConFiltroEstado(filtro,sEstadoGastoFP,sComparadorFP));
					
					if (getTablagastos().size() == 0)
					{
						sMsg = "No se encontraron gastos con los criterios solicitados.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);
					}
					else if (getTablagastos().size() == 1)
					{
						sMsg = "Encontrado un gasto relacionado.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
					else
					{
						sMsg = "Encontrados "+getTablagastos().size()+" gastos relacionados.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
				}
				else
				{
					sMsg = "La Provisión '"+sNUPROF+"' no se encuentra regristada en el sistema. Por favor, revise los datos.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);
					
					this.setTablagastos(null);
				}
			}
			catch(NumberFormatException nfe)
			{
				sMsg = "ERROR: La Provisión debe ser numérica. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
				
		    	this.setTablagastos(null);
			}
			
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	public void buscarGastosFechaLimite (ActionEvent actionEvent)
	{

		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
	    	this.setGastoseleccionado(null);
	    	
	    	if (sFELIPG.isEmpty())
	    	{
				sMsg = "ERROR: Debe informar la fecha de vencimiento para realizar una búsqueda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
	    	}
	    	else
	    	{
		    	String sFecha = Utils.compruebaFecha(sFELIPG);
				
				if (sFecha.equals("#"))
				{
					sMsg = "ERROR: La fecha proporcionada no es válida. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					
			    	this.setTablaprovisiones(null);
				}
				else
				{
					
					this.setTablagastos(CLGastos.buscarGastosFechaLimite(sFecha));
					
					if (getTablagastos().size() == 0)
					{
						sMsg = "No se encontraron Gastos con los criterios solicitados.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);
						
					}
					else if (getTablagastos().size() == 1)
					{
						sMsg = "Encontrado un Gasto relacionado.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
					else
					{
						sMsg = "Encontrados "+getTablagastos().size()+" Gastos relacionados.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
				}
	    	}

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	public void cargarDetallesGasto(ActionEvent actionEvent) 
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

		    	logger.debug("sCOACES:|"+sCOACES+"|");
		    	logger.debug("sCOGRUG:|"+sCOGRUG+"|");
		    	logger.debug("sCOTPGA:|"+sCOTPGA+"|");
		    	logger.debug("sCOSBGA:|"+sCOSBGA+"|");
		    	logger.debug("sFEDEVE:|"+sFEDEVE+"|");
		    	
		    	//this.sCodGasto = Long.toString(CLGastos.buscarCodigoGasto(Integer.parseInt(sCOACES),sCOGRUG,sCOTPGA,sCOSBGA,Utils.compruebaFecha(sFEDEVE)));
		    	this.sCodGasto = gastoseleccionado.getsGastoID();
		    	
		    	
		    	logger.debug("sCodGasto:|"+sCodGasto+"|");
		    	
		    	Sesion.guardaDetalle(sCodGasto);
		    	Sesion.limpiarHistorial();
		    	Sesion.guardarHistorial("listagastos.xhtml","GestorDetallesGasto");

		    	sPagina = "detallesgasto.xhtml";
		    	
		    	
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

				msg = Utils.pfmsgWarning("No se ha seleccionado ningún gasto.");
				
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			


		}

		//return sPagina;
    }

	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES.trim();
	}

	public String getsNUPROF() {
		return sNUPROF;
	}

	public void setsNUPROF(String sNUPROF) {
		this.sNUPROF = sNUPROF.trim();
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

	public String getsFEDEVE() {
		return sFEDEVE;
	}

	public void setsFEDEVE(String sFEDEVE) {
		this.sFEDEVE = sFEDEVE;
	}

	public String getsCodGasto() {
		return sCodGasto;
	}

	public void setsCodGasto(String sCodGasto) {
		this.sCodGasto = sCodGasto;
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

	public String getsCOGRUGFA() {
		return sCOGRUGFA;
	}

	public void setsCOGRUGFA(String sCOGRUGFA) {
		this.sCOGRUGFA = sCOGRUGFA;
	}

	public String getsCOTPGAFA() {
		return sCOTPGAFA;
	}

	public void setsCOTPGAFA(String sCOTPGAFA) {
		this.sCOTPGAFA = sCOTPGAFA;
	}

	public String getsCOSBGAFA() {
		return sCOSBGAFA;
	}

	public void setsCOSBGAFA(String sCOSBGAFA) {
		this.sCOSBGAFA = sCOSBGAFA;
	}

	public String getsFEDEVEFA() {
		return sFEDEVEFA;
	}

	public void setsFEDEVEFA(String sFEDEVEFA) {
		this.sFEDEVEFA = sFEDEVEFA;
	}

	public String getsIMNGASFA() {
		return sIMNGASFA;
	}

	public void setsIMNGASFA(String sIMNGASFA) {
		this.sIMNGASFA = sIMNGASFA;
	}

	public String getsEstadoGastoFA() {
		return sEstadoGastoFA;
	}

	public void setsEstadoGastoFA(String sEstadoGastoFA) {
		this.sEstadoGastoFA = sEstadoGastoFA;
	}

	public String getsCOGRUGFP() {
		return sCOGRUGFP;
	}

	public void setsCOGRUGFP(String sCOGRUGFP) {
		this.sCOGRUGFP = sCOGRUGFP;
	}

	public String getsCOTPGAFP() {
		return sCOTPGAFP;
	}

	public void setsCOTPGAFP(String sCOTPGAFP) {
		this.sCOTPGAFP = sCOTPGAFP;
	}

	public String getsCOSBGAFP() {
		return sCOSBGAFP;
	}

	public void setsCOSBGAFP(String sCOSBGAFP) {
		this.sCOSBGAFP = sCOSBGAFP;
	}

	public String getsFEDEVEFP() {
		return sFEDEVEFP;
	}

	public void setsFEDEVEFP(String sFEDEVEFP) {
		this.sFEDEVEFP = sFEDEVEFP;
	}

	public String getsIMNGASFP() {
		return sIMNGASFP;
	}

	public void setsIMNGASFP(String sIMNGASFP) {
		this.sIMNGASFP = sIMNGASFP;
	}

	public String getsEstadoGastoFP() {
		return sEstadoGastoFP;
	}

	public void setsEstadoGastoFP(String sEstadoGastoFP) {
		this.sEstadoGastoFP = sEstadoGastoFP;
	}

	public String getsFEPFON() {
		return sFEPFON;
	}

	public void setsFEPFON(String sFEPFON) {
		this.sFEPFON = sFEPFON;
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

	public Map<String, String> getTiposcogrugHM() {
		return tiposcogrugHM;
	}

	public void setTiposcogrugHM(Map<String, String> tiposcogrugHM) {
		this.tiposcogrugHM = tiposcogrugHM;
	}

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

	public Map<String, String> getTiposestadogastoHM() {
		return tiposestadogastoHM;
	}

	public void setTiposestadogastoHM(Map<String, String> tiposestadogastoHM) {
		this.tiposestadogastoHM = tiposestadogastoHM;
	}

	public Map<String,String> getTiposcomparaimporteHM() {
		return tiposcomparaimporteHM;
	}

	public void setTiposcomparaimporteHM(Map<String,String> tiposcomparaimporteHM) {
		this.tiposcomparaimporteHM = tiposcomparaimporteHM;
	}

	public Map<String,String> getTiposestadoprovisionHM() {
		return tiposestadoprovisionHM;
	}

	public void setTiposestadoprovisionHM(Map<String,String> tiposestadoprovisionHM) {
		this.tiposestadoprovisionHM = tiposestadoprovisionHM;
	}

	public String getsEstadoProvision() {
		return sEstadoProvision;
	}

	public void setsEstadoProvision(String sEstadoProvision) {
		this.sEstadoProvision = sEstadoProvision;
	}

	public String getsNURCAT() {
		return sNURCAT;
	}

	public void setsNURCAT(String sNURCAT) {
		this.sNURCAT = sNURCAT.trim();
	}

	public String getsFELIPG() {
		return sFELIPG;
	}

	public void setsFELIPG(String sFELIPG) {
		this.sFELIPG = sFELIPG;
	}

	public String getsComparadorFA() {
		return sComparadorFA;
	}

	public void setsComparadorFA(String sComparadorFA) {
		this.sComparadorFA = sComparadorFA;
	}

	public String getsComparadorFP() {
		return sComparadorFP;
	}

	public void setsComparadorFP(String sComparadorFP) {
		this.sComparadorFP = sComparadorFP;
	}

	public boolean isbSeleccionadoFA() {
		return bSeleccionadoFA;
	}

	public void setbSeleccionadoFA(boolean bSeleccionadoFA) {
		this.bSeleccionadoFA = bSeleccionadoFA;
	}

	public boolean isbSeleccionadoFP() {
		return bSeleccionadoFP;
	}

	public void setbSeleccionadoFP(boolean bSeleccionadoFP) {
		this.bSeleccionadoFP = bSeleccionadoFP;
	}
	
}
