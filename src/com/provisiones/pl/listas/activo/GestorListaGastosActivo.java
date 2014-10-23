package com.provisiones.pl.listas.activo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLActivos;
import com.provisiones.ll.CLGastos;
import com.provisiones.ll.CLInformes;
import com.provisiones.ll.CLProvisiones;
import com.provisiones.misc.Sesion;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Transicion;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.GastoTabla;
import com.provisiones.types.tablas.ProvisionTabla;

public class GestorListaGastosActivo implements Serializable 
{

	private static final long serialVersionUID = -8751809920322336954L;

	private static Logger logger = LoggerFactory.getLogger(GestorListaGastosActivo.class.getName());

	private String sCOACES = "";
	private String sNUPROF = "";
	
	//Filtro Gastos Activo
	private String sCOGRUGFA = "";
	private String sCOTPGAFA = "";
	private String sCOSBGAFA = "";
	private String sFEDEVEINIFA = "";
	private String sFEDEVEFINFA = "";
	private String sPeriodoFA = "";
	private boolean bInicioFA = true;
	private boolean bFinFA = true;
	private String sIMNGASFA = "";
	private String sComparadorFA = "";
	private boolean bSeleccionadoFA = true; 
	private String sEstadoGastoFA = "";
	
	private String sCodGasto = "";
	
	private int iCOACES = 0;
	
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
	
	private String sNombreInforme = "";
	private boolean bSinInforme = true; 
	
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
	
	private Map<String,String> tiposperiodoHM = new LinkedHashMap<String, String>();

	private transient StreamedContent file;
	
	public GestorListaGastosActivo()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorListaGastosActivo...");
			
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
			
			tiposperiodoHM.put("TODAS",	"T");
			tiposperiodoHM.put("FIJA",	"F");
			tiposperiodoHM.put("ENTRE", "E");
			
			cargarDetallesActivo();
		}
	}
	
	
	public void volver(ActionEvent actionEvent)
	{
		
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
	
	public void borrarCamposGasto()
	{
    	this.sNUPROF = "";
    	
    	this.setGastoseleccionado(null);
    	this.setTablagastos(null);
    	this.bSinInforme = true;
	}
	
    
	public void borrarCamposFiltroGastosActivo()
	{
		this.sCOGRUGFA = "";
		this.sCOTPGAFA = "";
		this.sCOSBGAFA = "";
		this.sFEDEVEINIFA = "";
		this.sFEDEVEFINFA = "";
		this.sPeriodoFA = "";
		this.bInicioFA = true;
		this.bFinFA = true;
		this.sIMNGASFA = "";
		this.sComparadorFA = "";
		this.bSeleccionadoFA = true; 
		this.sEstadoGastoFA = "";
	}
	
    public void limpiarPlantillaFiltroGastosActivo(ActionEvent actionEvent) 
    {  
    	borrarCamposFiltroGastosActivo();
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
    	borrarCamposProvision();
    	
    	this.sFELIPG= ""; 
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
				
				//this.setTablaprovisiones(CLProvisiones.buscarProvisionesConFiltro(filtro));
				this.setTablaprovisiones(CLProvisiones.buscarProvisionesActivoConFiltro(iCOACES,filtro));
				
				

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
	
	public void cambiaPeriodoFA()
	{
		if (sPeriodoFA.isEmpty())
		{
			this.bInicioFA = true;
			this.sFEDEVEINIFA = "";
			this.bFinFA = true;
			this.sFEDEVEFINFA = "";
			
		}
		else if (sPeriodoFA.equals(ValoresDefecto.DEF_PERIODOS_FECHA_TODOS))
		{
			this.bInicioFA = true;
			this.sFEDEVEINIFA = "";
			this.bFinFA = true;
			this.sFEDEVEFINFA = "";
		}
		else if (sPeriodoFA.equals(ValoresDefecto.DEF_PERIODOS_FECHA_FIJO))
		{
			this.bInicioFA = false;
			this.bFinFA = true;
			this.sFEDEVEFINFA = "";
		}
		else if (sPeriodoFA.equals(ValoresDefecto.DEF_PERIODOS_FECHA_ENTRE))
		{
			this.bInicioFA = false;
			this.bFinFA = false;
		}
		
		logger.debug("sPeriodoFA:|"+sPeriodoFA+"|");
	}
	
	public void hoyFEDEVEINIFA (ActionEvent actionEvent)
	{
		this.setsFEDEVEINIFA(Utils.fechaDeHoy(true));
		logger.debug("sFEDEVEINIFA:|"+sFEDEVEINIFA+"|");
	}
	
	public void hoyFEDEVEFINFA (ActionEvent actionEvent)
	{
		this.setsFEDEVEFINFA(Utils.fechaDeHoy(true));
		logger.debug("sFEDEVEFINFA:|"+sFEDEVEFINFA+"|");
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
	
	public void cargarDetallesActivo()
	{
		FacesMessage msg;
		
		String sMsg = "";

		//this.sCOACES  = Sesion.cargarDetalle();
		this.sCOACES  = CLActivos.recuperaID();
		
		logger.debug("sCOACES:|"+sCOACES+"|");
		
		
		try
		{

			this.iCOACES = Integer.parseInt(sCOACES);
			
			sMsg = "Detalles cargados correctamente.";
			msg = Utils.pfmsgInfo(sMsg);
			logger.info(sMsg);
			
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
	
	public void buscarGastosActivo (ActionEvent actionEvent)
	{

		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
	    	this.setGastoseleccionado(null);
			
	    	//if (true)
			if (sCOACES.isEmpty())
			{
				sMsg = "ERROR: Debe informar el Activo para realizar una búsqueda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
				
		    	this.setTablagastos(null);
			}
			else
			{
				if (CLActivos.existeActivo(iCOACES))
				{
					String sImporte = "";
					
					if (!sComparadorFA.isEmpty()) 
					{
						sImporte = Utils.compruebaImporte(sIMNGASFA);
					}
					
					GastoTabla filtro = new GastoTabla(
							"",
							sNUPROF,
							"",   
							sCOACES,   
							sCOGRUGFA,   
							sCOTPGAFA,   
							sCOSBGAFA,   
							"",  
							"",   
							"",  
							Utils.compruebaFecha(sFEDEVEINIFA),   
							"",   
							"",  
							sImporte,
							sEstadoGastoFA,
							"",
							sFELIPG,
							"",
							"");
					
			    	logger.debug("sCOACES:|"+sCOACES+"|");
			    	logger.debug("sCOGRUGFA:|"+sCOGRUGFA+"|");
			    	logger.debug("sCOTPGAFA:|"+sCOTPGAFA+"|");
			    	logger.debug("sCOSBGAFA:|"+sCOSBGAFA+"|");
			    	logger.debug("sComparadorFA:|"+sComparadorFA+"|");
			    	logger.debug("sIMNGASFA:|"+sIMNGASFA+"|");
			    	logger.debug("sFEDEVEFA:|"+sFEDEVEINIFA+"|");
			    	logger.debug("sFEDEVEFA:|"+sFEDEVEFINFA+"|");
			    	logger.debug("sEstadoGastoFA:|"+sEstadoGastoFA+"|");
			    	
			    	if (sPeriodoFA.equals(ValoresDefecto.DEF_PERIODOS_FECHA_FIJO) && (sFEDEVEINIFA.isEmpty()||sFEDEVEINIFA.equals(ValoresDefecto.CAMPO_NUME_SIN_INFORMAR)))
			    	{
			    		sPeriodoFA = ValoresDefecto.DEF_PERIODOS_FECHA_TODOS;
			    	}
					
					this.setTablagastos(CLGastos.buscarGastosActivoProvisionConFiltro(filtro,sComparadorFA,Utils.compruebaFecha(sFEDEVEFINFA), sPeriodoFA));
					
					
					if (getTablagastos().size() > 0)
					{
						bSinInforme = false;
						
						sNombreInforme = CLInformes.generarInformeGastosActivo(iCOACES,getTablagastos());
					}
					else
					{
						bSinInforme = true;
						
						sNombreInforme = "";
						
					}
					
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

		    	this.sCodGasto = gastoseleccionado.getsGastoID();
		    	
		    	
		    	logger.debug("sCodGasto:|"+sCodGasto+"|");

		    	Transicion transicion = new Transicion (
		    			sCodGasto,
		    			ValoresDefecto.ID_GASTO,
		    			"listagastosactivo.xhtml",
		    			"GestorDetallesGasto");
		    	
		    	Sesion.guardarTransicion(transicion, false);
		    	
		    	//Sesion.guardaDetalle(sCodGasto);
		    	//Sesion.limpiarHistorial();
		    	//Sesion.guardarHistorial("listagastos.xhtml","GestorDetallesGasto");

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
	
	public void descargarInforme(ActionEvent actionEvent) 
    {  
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	String sMsg = "";
	    	
			if (getTablagastos().size() == 0)
			{
				sMsg = "No se encontraron Gastos que exportar.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
				
			}
			else
			{
		    	try 
				{
		    		InputStream stream = new FileInputStream(sNombreInforme);
					
					this.setFile(new DefaultStreamedContent(stream, "text/plain", "Informe_Gastos_ACtivo_"+sCOACES+"_"+Utils.fechaDeHoy(false)+".pdf"));
					
		    		sMsg = "Descargado el Informe a enviar.";
		        	
		    		msg = Utils.pfmsgInfo(sMsg);
		    		logger.info(sMsg);

				} 
				catch (FileNotFoundException e) 
				{
					
					
		    		sMsg = "ERROR: Ocurrio un problema al acceder al archivo.";
		        	
		    		msg = Utils.pfmsgError(sMsg);
		    		logger.error(sMsg);
				}
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

	public String getsNUPROF() {
		return sNUPROF;
	}

	public void setsNUPROF(String sNUPROF) {
		this.sNUPROF = sNUPROF.trim();
	}

	public String getsCodGasto() {
		return sCodGasto;
	}

	public void setsCodGasto(String sCodGasto) {
		this.sCodGasto = sCodGasto;
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

	public String getsFEDEVEINIFA() {
		return sFEDEVEINIFA;
	}


	public void setsFEDEVEINIFA(String sFEDEVEINIFA) {
		this.sFEDEVEINIFA = sFEDEVEINIFA;
	}


	public String getsFEDEVEFINFA() {
		return sFEDEVEFINFA;
	}


	public void setsFEDEVEFINFA(String sFEDEVEFINFA) {
		this.sFEDEVEFINFA = sFEDEVEFINFA;
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

	public Map<String, String> getTiposperiodoHM() {
		return tiposperiodoHM;
	}


	public void setTiposperiodoHM(Map<String, String> tiposperiodoHM) {
		this.tiposperiodoHM = tiposperiodoHM;
	}


	public String getsEstadoProvision() {
		return sEstadoProvision;
	}

	public void setsEstadoProvision(String sEstadoProvision) {
		this.sEstadoProvision = sEstadoProvision;
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


	public String getsPeriodoFA() {
		return sPeriodoFA;
	}


	public void setsPeriodoFA(String sPeriodoFA) {
		this.sPeriodoFA = sPeriodoFA;
	}


	public boolean isbInicioFA() {
		return bInicioFA;
	}


	public void setbInicioFA(boolean bInicioFA) {
		this.bInicioFA = bInicioFA;
	}


	public boolean isbFinFA() {
		return bFinFA;
	}


	public void setbFinFA(boolean bFinFA) {
		this.bFinFA = bFinFA;
	}


	public StreamedContent getFile() {
		return file;
	}


	public void setFile(StreamedContent file) {
		this.file = file;
	}


	public boolean isbSinInforme() {
		return bSinInforme;
	}


	public void setbSinInforme(boolean bSinInforme) {
		this.bSinInforme = bSinInforme;
	}
	
}
