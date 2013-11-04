package com.provisiones.pl.errores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.ll.CLActivos;
import com.provisiones.ll.CLErrores;
import com.provisiones.ll.CLGastos;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

import com.provisiones.types.Gasto;
import com.provisiones.types.errores.ErrorGastoTabla;
import com.provisiones.types.errores.ErrorTabla;
import com.provisiones.types.movimientos.MovimientoGasto;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.GastoTabla;

public class GestorErroresGastos implements Serializable 
{

	private static final long serialVersionUID = 4678565126481256193L;

	private static Logger logger = LoggerFactory.getLogger(GestorErroresGastos.class.getName());

	private String sCOACES = "";
	private boolean bCOACES = true;
	private boolean bDevolucion = true;
	private String sCOGRUG = "";
	private boolean bCOGRUG = true;
	private String sCOTPGA = "";
	private boolean bCOTPGA = true;
	private String sCOSBGA = "";
	private boolean bCOSBGA = true;
	private String sDCOSBGA = "";
	private String sPTPAGO = "";
	private boolean bPTPAGO = true;
	private String sFEDEVE = "";
	private boolean bFEDEVE = true;
	private String sFFGTVP = "";
	private boolean bFFGTVP = true;
	private String sFEPAGA = "";
	private boolean bFEPAGA = true;
	private String sFELIPG = "";
	private boolean bFELIPG = true;
	private String sCOSIGA = "";
	private boolean bCOSIGA = true;
	private String sFEEESI = "";
	private boolean bFEEESI = true;
	private String sFEECOI = "";
	private boolean bFEECOI = true;
	private String sFEEAUI = "";
	private boolean bFEEAUI = true;
	private String sFEEPAI = "";
	private boolean bFEEPAI = true;

	private String sIMNGAS = "";
	private boolean bIMNGAS = true;
	private String sYCOS02 = "";
	private String sIMRGAS = "";
	private boolean bIMRGAS = true;
	private String sYCOS04 = "";
	private String sIMDGAS = "";
	private boolean bIMDGAS = true;
	private String sYCOS06 = "";
	private String sIMCOST = "";
	private boolean bIMCOST = true;
	private String sYCOS08 = "";
	private String sIMOGAS = "";
	private boolean bIMOGAS = true;
	private String sYCOS10 = "";
	
	private String sIMDTGA = "";
	private boolean bIMDTGA = true;
	private String sCOUNMO = ValoresDefecto.DEF_COUNMO;
	private String sIMIMGA = "";
	private boolean bIMIMGA = true;
	private String sCOIMPT = "";
	private boolean bCOIMPT = true;
	
	private String sCOTNEG = ValoresDefecto.DEF_COTNEG;
	
	private String sCOENCX = ValoresDefecto.DEF_COENCX;
	private String sCOOFCX = ValoresDefecto.DEF_COOFCX;
	private String sNUCONE = ValoresDefecto.DEF_NUCONE;
	private String sNUPROF = "";
	private String sFEAGTO = ValoresDefecto.DEF_FEAGTO;
	private boolean bFEAGTO = true;
	private String sCOMONA = ValoresDefecto.DEF_COMONA;
	private String sBIAUTO = ValoresDefecto.DEF_BIAUTO;
	private String sFEAUFA = ValoresDefecto.DEF_FEAUFA;
	private String sCOTERR = ValoresDefecto.DEF_COTERR;
	private String sFMPAGN = ValoresDefecto.DEF_FMPAGN;
	private String sFEPGPR = "";
	private boolean bFEPGPR = true;
	
	private String sFEAPLI = ValoresDefecto.DEF_FEAPLI;
	
	private String sCOAPII = ValoresDefecto.DEF_COAPII;
	private String sCOSPII = ValoresDefecto.DEF_COSPII_GA;
	private String sNUCLII = ValoresDefecto.DEF_NUCLII;

	//recuperar cuotas
	private String sCOSBAC = "";
	private boolean bCOSBAC = true;
	private String sIMCUCO = "";
	private boolean bIMCUCO = true;
	
	//filtro de activos
	private String sCOPOIN = "";
	private String sNOMUIN = "";
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	
	//Buscar errores
	private String sCodMovimiento ="";
	private String sCodError = "";
	
	private String sCOACESB = "";
	private String sCOGRUGB = "";
	private String sCOTPGAB = "";
	private String sCOSBGAB = "";
	private String sFEDEVEB = "";

	private transient ErrorGastoTabla movimientoseleccionado = null;
	private transient ArrayList<ErrorGastoTabla> tablagastoserror = null;
	
	private transient ErrorTabla errorseleccionado = null;
	private transient ArrayList<ErrorTabla> tablaerrores = null;
		

	private transient ActivoTabla activoseleccionado = null;
	private transient ArrayList<ActivoTabla> tablaactivos = null;

	private transient GastoTabla gastoseleccionado = null;
	private transient ArrayList<GastoTabla> tablagastos = null;
	
	private transient Map<String,String> tiposcotpgaHM = new LinkedHashMap<String, String>();
	private transient Map<String,String> tiposcosbgaHM = new LinkedHashMap<String, String>();
	
	private transient Map<String,String> tiposcotpga_g1HM = new LinkedHashMap<String, String>();
	private transient Map<String,String> tiposcotpga_g2HM = new LinkedHashMap<String, String>();
	private transient Map<String,String> tiposcotpga_g3HM = new LinkedHashMap<String, String>();
	
	private transient Map<String,String> tiposcosbga_t11HM = new LinkedHashMap<String, String>();
	private transient Map<String,String> tiposcosbga_t12HM = new LinkedHashMap<String, String>();
	private transient Map<String,String> tiposcosbga_t21HM = new LinkedHashMap<String, String>();
	private transient Map<String,String> tiposcosbga_t22HM = new LinkedHashMap<String, String>();
	private transient Map<String,String> tiposcosbga_t23HM = new LinkedHashMap<String, String>();
	private transient Map<String,String> tiposcosbga_t32HM = new LinkedHashMap<String, String>();
	private transient Map<String,String> tiposcosbga_t33HM = new LinkedHashMap<String, String>();

	private transient Map<String,String> tiposcosigaHM = new LinkedHashMap<String, String>();

	public GestorErroresGastos()
	{
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
		
		tiposcosigaHM.put("ESTIMADO",            "1");
		tiposcosigaHM.put("CONOCIDO",            "2");
		
	}
	
    public void borrarPlantillaError() 
    {  
    	this.sCOACESB = "";

    	this.sCOGRUGB = "";
    	this.sCOTPGAB = "";
    	this.sCOSBGAB = "";
    	this.sFEDEVEB = "";

    	
    	this.movimientoseleccionado = null;
    	this.tablagastoserror = null;
    	
    	this.errorseleccionado = null;
    	this.tablaerrores = null;
    	
    	this.sCodMovimiento ="";
    	this.sCodError = "";
   	
    }
	
    public void limpiarPlantillaError(ActionEvent actionEvent) 
    {  
    	borrarPlantillaError();
   	
    }
	
	public void borrarPlantillaActivo()
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
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {
    	this.sCOACES = "";

    	borrarPlantillaActivo();
    	
    	borrarResultadosActivo();
    }
	
	public void borrarPlantillaGasto()
	{
		this.sCOGRUG = "";
		this.bDevolucion = false;
		this.sCOTPGA = "";
		this.sCOSBGA = "";
		this.sPTPAGO = "";

		this.sFEDEVE = "";
		this.sFFGTVP = "";
		this.bFFGTVP = true;
		this.sFEPAGA = "";
		this.sFELIPG = "";

		this.sCOSIGA = "";
		this.sFEEESI = "";
		this.bFEEESI = true;
		this.sFEECOI = "";
		this.bFEECOI = true;
		this.sFEEAUI = "";
		this.sFEEPAI = "";

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

		this.sCOTNEG = ValoresDefecto.DEF_COTNEG;

		this.sCOENCX = ValoresDefecto.DEF_COENCX;
		this.sCOOFCX = ValoresDefecto.DEF_COOFCX;
		this.sNUCONE = ValoresDefecto.DEF_NUCONE;

		this.sNUPROF = "";

		this.sFEAGTO = ValoresDefecto.DEF_FEAGTO;

		this.sCOMONA = ValoresDefecto.DEF_COMONA;
		this.sBIAUTO = ValoresDefecto.DEF_BIAUTO;
		this.sFEAUFA = ValoresDefecto.DEF_FEAUFA;
		this.sCOTERR = ValoresDefecto.DEF_COTERR;

		this.sFMPAGN = ValoresDefecto.DEF_FMPAGN;
		this.sFEPGPR = "";
		
		this.sFEAPLI = ValoresDefecto.DEF_FEAPLI;
		
		this.sCOAPII = ValoresDefecto.DEF_COAPII;
		this.sCOSPII = ValoresDefecto.DEF_COSPII_GA;
		this.sNUCLII = ValoresDefecto.DEF_NUCLII;
		
		this.sCOSBAC = "";
		this.sIMCUCO = "";
		
	}
	
	public void borrarResultadosGasto()
	{
		this.gastoseleccionado = null;
		this.tablagastos = null;
	}
	
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";
    	
    	borrarPlantillaGasto();
    	    	
    	borrarResultadosActivo();

    	borrarResultadosGasto();
    	
    	borrarPlantillaError();
    }
    
    public boolean editarError(int iCodError) 
    {  
    	boolean bSalida = false;
  	
		switch (iCodError) 
		{
		case 2://prueba
			this.bCOACES = true;
			this.bCOGRUG = true;
			this.bCOIMPT = true;
			this.bCOSBAC = true;
			this.bCOSBGA = true;
			this.bCOSIGA = true;
			this.bCOTPGA = true;
			this.bDevolucion = true;
			this.bFEDEVE = true;
			this.bFEEAUI = true;
			this.bFEECOI = true;
			this.bFEEESI = true;
			this.bFEEPAI = true;
			this.bFELIPG = true;
			this.bFEPAGA = true;
			this.bFEPGPR = true;
			this.bFFGTVP = true;
			this.bIMCOST = true;
			this.bIMCUCO = true;
			this.bIMDGAS = true;
			this.bIMDTGA = true;
			this.bIMIMGA = true;
			this.bIMNGAS = false;
			this.bIMOGAS = true;
			this.bIMRGAS = true;
			this.bPTPAGO = true;
			bSalida = true;
			break;
		default://error no recuperable
			this.bCOACES = true;
			this.bCOGRUG = true;
			this.bCOIMPT = true;
			this.bCOSBAC = true;
			this.bCOSBGA = true;
			this.bCOSIGA = true;
			this.bCOTPGA = true;
			this.bDevolucion = true;
			this.bFEDEVE = true;
			this.bFEEAUI = true;
			this.bFEECOI = true;
			this.bFEEESI = true;
			this.bFEEPAI = true;
			this.bFELIPG = true;
			this.bFEPAGA = true;
			this.bFEPGPR = true;
			this.bFFGTVP = true;
			this.bIMCOST = true;
			this.bIMCUCO = true;
			this.bIMDGAS = true;
			this.bIMDTGA = true;
			this.bIMIMGA = true;
			this.bIMNGAS = true;
			this.bIMOGAS = true;
			this.bIMRGAS = true;
			this.bPTPAGO = true;

			bSalida = false;
			break;
		}
		
		return bSalida;
   	
    }
    
	public void buscaGastosError(ActionEvent actionEvent)
	{
		FacesMessage msg;
		
		logger.debug("Buscando Gastos con errores...");
		
		ErrorGastoTabla filtro = new ErrorGastoTabla(
				sCOACESB, sCOGRUGB, sCOTPGAB, sCOSBGA, "", "",Utils.compruebaFecha(sFEDEVE),
				"", "");

		this.setTablagastoserror(CLErrores.buscarGastosConErrores(filtro));


		
		
		msg = Utils.pfmsgInfo("Encontrados "+getTablagastoserror().size()+" Gastos relacionados.");
		logger.debug("Encontrados {} gastos relacionados.",getTablagastoserror().size());
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void seleccionarMovimiento(ActionEvent actionEvent) 
    {  
		FacesMessage msg;
		
		this.sCodMovimiento = movimientoseleccionado.getMOVIMIENTO(); 
    	
		this.setTablaerrores(CLErrores.buscarErroresGasto(sCodMovimiento));
		
		msg = Utils.pfmsgInfo("Encontrados "+getTablaerrores().size()+" errores relacionados.");
		logger.debug("Encontrados {} errores relacionados.",getTablaerrores().size());
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		MovimientoGasto movimiento = CLGastos.buscarMovimientoGasto(sCodMovimiento);
		
		this.sCOACES = movimiento.getCOACES();
	   	this.sCOGRUG = movimiento.getCOGRUG();
    	this.sCOTPGA = movimiento.getCOTPGA();
    	this.sCOSBGA = movimiento.getCOSBGA();
    	this.sDCOSBGA = movimientoseleccionado.getDCOSBGA().replaceFirst("Devolucion ", "");
    	this.sFEDEVE = Utils.recuperaFecha(movimiento.getFEDEVE());
    	
    	
		logger.debug("sCOACES:|{}|",sCOACES);
		logger.debug("sCOGRUG:|{}|",sCOGRUG);
		logger.debug("sCOTPGA:|{}|",sCOTPGA);
		logger.debug("sCOSBGA:|{}|",sCOSBGA);
		logger.debug("sFEDEVE:|{}|",sFEDEVE);
    	
    	this.bDevolucion = (Integer.parseInt(sCOSBGA) > 49);

		this.sPTPAGO = movimiento.getPTPAGO();

		this.sFFGTVP = Utils.recuperaFecha(movimiento.getFFGTVP());
		this.sFEPAGA = Utils.recuperaFecha(movimiento.getFEPAGA());
		this.sFELIPG = Utils.recuperaFecha(movimiento.getFELIPG());
		this.sCOSIGA = movimiento.getCOSIGA();
		this.sFEEESI = Utils.recuperaFecha(movimiento.getFEEESI());
		this.sFEECOI = Utils.recuperaFecha(movimiento.getFEECOI());
		this.sFEEAUI = Utils.recuperaFecha(movimiento.getFEEAUI());
		this.sFEEPAI = Utils.recuperaFecha(movimiento.getFEEPAI());
		this.sIMNGAS = Utils.recuperaImporte(movimiento.getYCOS02().equals("-"),movimiento.getIMNGAS());
		this.sIMRGAS = Utils.recuperaImporte(movimiento.getYCOS04().equals("-"),movimiento.getIMRGAS());
		this.sIMDGAS = Utils.recuperaImporte(movimiento.getYCOS06().equals("-"),movimiento.getIMDGAS());
		this.sIMCOST = Utils.recuperaImporte(movimiento.getYCOS08().equals("-"),movimiento.getIMCOST());
		this.sIMOGAS = Utils.recuperaImporte(movimiento.getYCOS10().equals("-"),movimiento.getIMOGAS());
		this.sIMDTGA = Utils.recuperaImporte(false,movimiento.getIMDTGA());
		this.sIMIMGA = Utils.recuperaImporte(false,movimiento.getIMIMGA());
		this.sCOIMPT = movimiento.getCOIMPT();
		
		this.sCOTNEG = movimiento.getCOTNEG();
		this.sFEAGTO = Utils.recuperaFecha(movimiento.getFEAGTO());
		this.sCOMONA = movimiento.getCOMONA();
		this.sBIAUTO = movimiento.getBIAUTO();
		this.sFEAUFA = Utils.recuperaFecha(movimiento.getFEAUFA());
		this.sFEPGPR = Utils.recuperaFecha(movimiento.getFEPGPR());
		
		this.sCOUNMO = movimiento.getCOUNMO();
		
		this.sCOENCX = movimiento.getCOENCX();
		this.sCOOFCX = movimiento.getCOOFCX();
		this.sNUCONE = movimiento.getNUCONE();
		
		this.sNUPROF = movimiento.getNUPROF();

		this.sCOTERR = movimiento.getCOTERR();
		this.sFMPAGN = Utils.recuperaFecha(movimiento.getFMPAGN());
	
		this.sFEAPLI = Utils.recuperaFecha(movimiento.getFEAPLI());
		this.sCOAPII = movimiento.getCOAPII();
		this.sCOSPII = movimiento.getCOSPII();
		this.sNUCLII = movimiento.getNUCLII();
				
        	
    	msg = Utils.pfmsgInfo("Datos del movimiento cargados.");
    	logger.debug("Datos del movimiento cargados.");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void seleccionarError(ActionEvent actionEvent) 
    {  
		FacesMessage msg;
    	
		this.sCodError = errorseleccionado.getsCodError(); 
		
    	int iCodError  = Integer.parseInt(sCodError);
    	
    	
    	logger.debug("Error seleccionado:|{}|",iCodError);
    	
    	String sMsg ="";
    	
    	if (editarError(iCodError))
    	{
    		sMsg = "Error editado.";
    		msg = Utils.pfmsgInfo(sMsg);
    		logger.info(sMsg);
    	}
    	else
    	{
    		sMsg = "[FATAL] ERROR: El error seleccionado no es recuperable. Por favor, pongase en contacto con soporte.";
    		msg = Utils.pfmsgFatal(sMsg);
    		logger.error(sMsg);
    	}

    	FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
	public void buscaActivos (ActionEvent actionEvent)
	{
		FacesMessage msg;
		
		ActivoTabla buscaactivos = new ActivoTabla(
				sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
				sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
				sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(), "");
		
		this.setTablaactivos(CLGastos.buscarActivosConGastos(buscaactivos));
		
		msg = Utils.pfmsgInfo("Encontrados "+getTablaactivos().size()+" activos relacionados.");
		logger.info("Encontrados {} activos relacionados.",getTablaactivos().size());
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void seleccionarActivo(ActionEvent actionEvent) 
    {  
    	FacesMessage msg;
 
    	this.sCOACES  = activoseleccionado.getCOACES();
    	
    	msg = Utils.pfmsgInfo("Activo '"+ sCOACES +"' Seleccionado.");
    	logger.info("Activo '{}' Seleccionado.",sCOACES);
    	
    	FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void cargarDatos(ActionEvent actionEvent)
	{
		FacesMessage msg;
		
		if (CLActivos.compruebaActivo(sCOACES))
		{
			this.tablagastos = CLGastos.buscarGastosActivo(sCOACES);
		
			msg = Utils.pfmsgInfo("Encontrados "+getTablagastos().size()+" gastos en curso.");
			logger.info("Encontrados {} gastos en curso.",getTablagastos().size());
		}
		else
		{
			msg = Utils.pfmsgError("ERROR: No exite el activo '"+sCOACES+"'. Por favor, revise los datos.");
			logger.error("ERROR: No exite el activo '{}'. Por favor, revise los datos.",sCOACES);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	public void seleccionarGasto(ActionEvent actionEvent) 
    {  
    	FacesMessage msg;
    	
    	this.sCOGRUG = gastoseleccionado.getCOGRUG();
    	this.sCOTPGA = gastoseleccionado.getCOTPGA();
    	this.sCOSBGA = gastoseleccionado.getCOSBGA();
    	this.sDCOSBGA = gastoseleccionado.getDCOSBGA().replaceFirst("Devolucion ", "");
    	this.sFEDEVE = gastoseleccionado.getFEDEVE();
    	
	  	Gasto gasto = CLGastos.buscarGasto(sCOACES, sCOGRUG, sCOTPGA, sCOSBGA, Utils.compruebaFecha(sFEDEVE));
    	
    	logger.debug(gasto.logGasto());
 
    	this.bDevolucion = (Integer.parseInt(sCOSBGA) > 49);

		this.sPTPAGO = gasto.getPTPAGO();

		this.sFFGTVP = Utils.recuperaFecha(gasto.getFFGTVP());
		this.sFEPAGA = Utils.recuperaFecha(gasto.getFEPAGA());
		this.sFELIPG = Utils.recuperaFecha(gasto.getFELIPG());
		this.sCOSIGA = gasto.getCOSIGA();
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
		this.sCOIMPT = gasto.getCOIMPT();
		
		this.sCOTNEG = gasto.getCOTNEG();
		this.sFEAGTO = Utils.recuperaFecha(gasto.getFEAGTO());
		this.sCOMONA = gasto.getCOMONA();
		this.sBIAUTO = gasto.getBIAUTO();
		this.sFEAUFA = Utils.recuperaFecha(gasto.getFEAUFA());
		this.sFEPGPR = Utils.recuperaFecha(gasto.getFEPGPR());
		
		this.sCOUNMO = ValoresDefecto.DEF_COUNMO;
		
		this.sCOENCX = ValoresDefecto.DEF_COENCX;
		this.sCOOFCX = ValoresDefecto.DEF_COOFCX;
		this.sNUCONE = ValoresDefecto.DEF_NUCONE;
		
		this.sNUPROF = CLGastos.buscarProvisionGasto(sCOACES, sCOGRUG, sCOTPGA, sCOSBGA, Utils.compruebaFecha(sFEDEVE));

		this.sCOTERR = ValoresDefecto.DEF_COTERR;
		this.sFMPAGN = ValoresDefecto.DEF_FMPAGN;
	
		this.sFEAPLI = ValoresDefecto.DEF_FEAPLI;
		this.sCOAPII = ValoresDefecto.DEF_COAPII;
		this.sCOSPII = ValoresDefecto.DEF_COSPII_GA;
		this.sNUCLII = ValoresDefecto.DEF_NUCLII;
		
		String sTipo = bDevolucion ? "La devolucion":"El Gasto"; 
		
		msg = Utils.pfmsgInfo(sTipo+" de '"+sDCOSBGA+"' se ha cargado.");
		logger.info("{} de '{}' se ha cargado.",sTipo,sDCOSBGA);

		FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	
	
	public void cambiaGrupo()
	{
		tiposcotpgaHM = new LinkedHashMap<String, String>();
		tiposcosbgaHM = new LinkedHashMap<String, String>();
		
	}
	
	public void cambiaTipoB()
	{
		logger.debug("sCOGRUGB:|{}|",sCOGRUGB);

		if (sCOGRUGB !=null && !sCOGRUGB.equals(""))
		{
			switch (Integer.parseInt(sCOGRUGB)) 
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
			sCOTPGAB = "";
			sCOSBGAB = "";
		}
	}
	
	public void cambiaSubtipoB()
	{
		logger.debug("sCOGRUGB:|{}| sCOTPGAB:|{}|",sCOGRUGB,sCOTPGAB);
		
		if (sCOTPGAB !=null && !sCOTPGAB.equals(""))
		{
			switch (Integer.parseInt(sCOGRUGB+sCOTPGAB)) 
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
			sCOSBGAB = "";
		}
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
	
	public void hoyFEDEVEB (ActionEvent actionEvent)
	{
		this.setsFEDEVEB(Utils.fechaDeHoy(true));
		logger.debug("sFEDEVEB:|{}|",sFEDEVEB);
	}

	public void hoyFFGTVP (ActionEvent actionEvent)
	{
		this.setsFFGTVP(Utils.fechaDeHoy(true));
		logger.debug("sFFGTVP:|{}|",sFFGTVP);
	}

	public void hoyFEPAGA (ActionEvent actionEvent)
	{
		this.setsFEPAGA(Utils.fechaDeHoy(true));
		logger.debug("sFEPAGA:|{}|",sFEPAGA);
	}

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
	
	public void hoyFEPGPR (ActionEvent actionEvent)
	{
		this.setsFEPGPR(Utils.fechaDeHoy(true));
		logger.debug("sFEPGPR:|{}|",sFEPGPR);
	}
	
	public void hoyFEAGTO (ActionEvent actionEvent)
	{
		this.setsFEAGTO(Utils.fechaDeHoy(true));
		logger.debug("sFEAGTO:|{}|",sFEAGTO);
	}

	public void registraGasto(ActionEvent actionEvent)
	{
		FacesMessage msg;
		
		String sMsg = "";
		
		if (!CLGastos.existeMovimientoGasto(sCodMovimiento))
		{
			sMsg = "[FATAL] ERROR:911 - No se puede modificar el gasto, no existe el movimiento. Por favor, revise los datos y avise a soporte.";
			msg = Utils.pfmsgFatal(sMsg);
			logger.error(sMsg);
		}
		else
		{
			logger.debug("sCOACES:|{}|",sCOACES);
			logger.debug("sCOGRUG:|{}|",sCOGRUG);
			logger.debug("sCOTPGA:|{}|",sCOTPGA);
			logger.debug("sCOSBGA:|{}|",sCOSBGA);
			logger.debug("sFEDEVE:|{}|",sFEDEVE);
			
			MovimientoGasto nuevomovimiento = new MovimientoGasto (
					sCOACES.toUpperCase(),
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
					Utils.compruebaFecha(sFEPGPR),
					ValoresDefecto.DEF_FEAPLI,
					ValoresDefecto.DEF_COAPII,
					ValoresDefecto.DEF_COSPII_GA,
					ValoresDefecto.DEF_NUCLII);

			//movimiento.pintaMovimientoGasto();
			
			int iSalida = CLErrores.reparaMovimientoGasto(nuevomovimiento,sCodMovimiento,sCodError);
			
			logger.debug("Codigo de salida:"+iSalida);
			
			switch (iSalida) 
			{
			case 0: //Sin errores
				sMsg = "El movimiento se ha registrado correctamente.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
				break;

			case -806: //Error 806 - modificacion sin cambios
				sMsg = "ERROR:806 - No hay modificaciones que realizar. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
				break;
				
	
			case -900: //Error 900 - al crear un movimiento
				sMsg = "[FATAL] ERROR:900 - Se ha producido un error al registrar el movimiento. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;

			case -904: //Error 904 - error y rollback - error al modificar el gasto
				sMsg = "[FATAL] ERROR:904 - Se ha producido un error al modificar el gasto. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;

			default: //error generico
				msg = Utils.pfmsgFatal("[FATAL] ERROR:"+iSalida+" - La operacion solicitada ha producido un error desconocido. Por favor, revise los datos y avise a soporte.");
				logger.error("[FATAL] ERROR:{} - La operacion solicitada ha producido un error desconocido. Por favor, revise los datos y avise a soporte.",iSalida);
				break;
			}

		}
		
		logger.debug("Finalizadas las comprobaciones.");
		FacesContext.getCurrentInstance().addMessage(null, msg);		


	}

	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
	}

	public String getsCOGRUG() {
		return sCOGRUG;
	}

	public void setsCOGRUG(String sCOGRUG) {
		logger.debug(sCOGRUG);
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

	public String getsCOTNEG() {
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
	}

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

	public String getsCOMONA() {
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
	}

	public String getsCOAPII() {
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

	public boolean isbCOACES() {
		return bCOACES;
	}

	public void setbCOACES(boolean bCOACES) {
		this.bCOACES = bCOACES;
	}

	public boolean isbDevolucion() {
		return bDevolucion;
	}

	public void setbDevolucion(boolean bDevolucion) {
		this.bDevolucion = bDevolucion;
	}

	public boolean isbCOGRUG() {
		return bCOGRUG;
	}

	public void setbCOGRUG(boolean bCOGRUG) {
		this.bCOGRUG = bCOGRUG;
	}

	public boolean isbCOTPGA() {
		return bCOTPGA;
	}

	public void setbCOTPGA(boolean bCOTPGA) {
		this.bCOTPGA = bCOTPGA;
	}

	public boolean isbCOSBGA() {
		return bCOSBGA;
	}

	public void setbCOSBGA(boolean bCOSBGA) {
		this.bCOSBGA = bCOSBGA;
	}

	public boolean isbPTPAGO() {
		return bPTPAGO;
	}

	public void setbPTPAGO(boolean bPTPAGO) {
		this.bPTPAGO = bPTPAGO;
	}

	public boolean isbFEDEVE() {
		return bFEDEVE;
	}

	public void setbFEDEVE(boolean bFEDEVE) {
		this.bFEDEVE = bFEDEVE;
	}

	public boolean isbFFGTVP() {
		return bFFGTVP;
	}

	public void setbFFGTVP(boolean bFFGTVP) {
		this.bFFGTVP = bFFGTVP;
	}

	public boolean isbFEPAGA() {
		return bFEPAGA;
	}

	public void setbFEPAGA(boolean bFEPAGA) {
		this.bFEPAGA = bFEPAGA;
	}

	public boolean isbFELIPG() {
		return bFELIPG;
	}

	public void setbFELIPG(boolean bFELIPG) {
		this.bFELIPG = bFELIPG;
	}

	public boolean isbCOSIGA() {
		return bCOSIGA;
	}

	public void setbCOSIGA(boolean bCOSIGA) {
		this.bCOSIGA = bCOSIGA;
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

	public boolean isbFEEAUI() {
		return bFEEAUI;
	}

	public void setbFEEAUI(boolean bFEEAUI) {
		this.bFEEAUI = bFEEAUI;
	}

	public boolean isbFEEPAI() {
		return bFEEPAI;
	}

	public void setbFEEPAI(boolean bFEEPAI) {
		this.bFEEPAI = bFEEPAI;
	}

	public boolean isbIMNGAS() {
		return bIMNGAS;
	}

	public void setbIMNGAS(boolean bIMNGAS) {
		this.bIMNGAS = bIMNGAS;
	}

	public boolean isbIMRGAS() {
		return bIMRGAS;
	}

	public void setbIMRGAS(boolean bIMRGAS) {
		this.bIMRGAS = bIMRGAS;
	}

	public boolean isbIMDGAS() {
		return bIMDGAS;
	}

	public void setbIMDGAS(boolean bIMDGAS) {
		this.bIMDGAS = bIMDGAS;
	}

	public boolean isbIMCOST() {
		return bIMCOST;
	}

	public void setbIMCOST(boolean bIMCOST) {
		this.bIMCOST = bIMCOST;
	}

	public boolean isbIMOGAS() {
		return bIMOGAS;
	}

	public void setbIMOGAS(boolean bIMOGAS) {
		this.bIMOGAS = bIMOGAS;
	}

	public boolean isbIMDTGA() {
		return bIMDTGA;
	}

	public void setbIMDTGA(boolean bIMDTGA) {
		this.bIMDTGA = bIMDTGA;
	}

	public boolean isbIMIMGA() {
		return bIMIMGA;
	}

	public void setbIMIMGA(boolean bIMIMGA) {
		this.bIMIMGA = bIMIMGA;
	}

	public boolean isbCOIMPT() {
		return bCOIMPT;
	}

	public void setbCOIMPT(boolean bCOIMPT) {
		this.bCOIMPT = bCOIMPT;
	}

	public boolean isbFEPGPR() {
		return bFEPGPR;
	}

	public void setbFEPGPR(boolean bFEPGPR) {
		this.bFEPGPR = bFEPGPR;
	}

	public boolean isbCOSBAC() {
		return bCOSBAC;
	}

	public void setbCOSBAC(boolean bCOSBAC) {
		this.bCOSBAC = bCOSBAC;
	}

	public boolean isbIMCUCO() {
		return bIMCUCO;
	}

	public void setbIMCUCO(boolean bIMCUCO) {
		this.bIMCUCO = bIMCUCO;
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

	public Map<String, String> getTiposcosigaHM() {
		return tiposcosigaHM;
	}

	public void setTiposcosigaHM(Map<String, String> tiposcosigaHM) {
		this.tiposcosigaHM = tiposcosigaHM;
	}

	public ErrorGastoTabla getMovimientoseleccionado() {
		return movimientoseleccionado;
	}

	public void setMovimientoseleccionado(ErrorGastoTabla movimientoseleccionado) {
		this.movimientoseleccionado = movimientoseleccionado;
	}

	public ArrayList<ErrorGastoTabla> getTablagastoserror() {
		return tablagastoserror;
	}

	public void setTablagastoserror(ArrayList<ErrorGastoTabla> tablagastoserror) {
		this.tablagastoserror = tablagastoserror;
	}

	public ErrorTabla getErrorseleccionado() {
		return errorseleccionado;
	}

	public void setErrorseleccionado(ErrorTabla errorseleccionado) {
		this.errorseleccionado = errorseleccionado;
	}

	public ArrayList<ErrorTabla> getTablaerrores() {
		return tablaerrores;
	}

	public void setTablaerrores(ArrayList<ErrorTabla> tablaerrores) {
		this.tablaerrores = tablaerrores;
	}

	public String getsCodMovimiento() {
		return sCodMovimiento;
	}

	public void setsCodMovimiento(String sCodMovimiento) {
		this.sCodMovimiento = sCodMovimiento;
	}

	public String getsCodError() {
		return sCodError;
	}

	public void setsCodError(String sCodError) {
		this.sCodError = sCodError;
	}

	public String getsCOACESB() {
		return sCOACESB;
	}

	public void setsCOACESB(String sCOACESB) {
		this.sCOACESB = sCOACESB;
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

	public boolean isbFEAGTO() {
		return bFEAGTO;
	}

	public void setbFEAGTO(boolean bFEAGTO) {
		this.bFEAGTO = bFEAGTO;
	}
}