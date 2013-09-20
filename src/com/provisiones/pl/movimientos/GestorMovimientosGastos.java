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

import com.provisiones.dal.qm.QMGastos;
import com.provisiones.ll.CLActivos;
import com.provisiones.ll.CLGastos;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

import com.provisiones.types.ActivoTabla;
import com.provisiones.types.Gasto;
import com.provisiones.types.GastoTabla;
import com.provisiones.types.MovimientoGasto;


public class GestorMovimientosGastos implements Serializable 
{

	private static final long serialVersionUID = 3669307013282571769L;

	private static Logger logger = LoggerFactory.getLogger(GestorMovimientosGastos.class.getName());

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
	private String sFEPAGA = "";
	private String sFELIPG = "";
	private String sCOSIGA = "";
	private String sFEEESI = "";
	private boolean bFEEESI = true;
	private String sFEECOI = "";
	private boolean bFEECOI = true;
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
	private boolean bIMIMGA = false;
	private String sCOIMPT = "";
	
	private String sCOTNEG = ValoresDefecto.DEF_COTNEG;
	
	private String sCOENCX = ValoresDefecto.DEF_COENCX;
	private String sCOOFCX = ValoresDefecto.DEF_COOFCX;
	private String sNUCONE = ValoresDefecto.DEF_NUCONE;
	private String sNUPROF = "";
	private String sFEAGTO = ValoresDefecto.DEF_FEAGTO;
	private String sCOMONA = ValoresDefecto.DEF_COMONA;
	private String sBIAUTO = ValoresDefecto.DEF_BIAUTO;
	private String sFEAUFA = ValoresDefecto.DEF_FEAUFA;
	private String sCOTERR = ValoresDefecto.DEF_COTERR;
	private String sFMPAGN = ValoresDefecto.DEF_FMPAGN;
	private String sFEPGPR = "";
	
	private String sFEAPLI = ValoresDefecto.DEF_FEAPLI;
	
	private String sCOAPII = ValoresDefecto.DEF_COAPII;
	private String sCOSPII = ValoresDefecto.DEF_COSPII_GA;
	private String sNUCLII = ValoresDefecto.DEF_NUCLII;

	//recuperar cuotas
	private String sCOSBAC = "";
	private String sIMCUCO = "";
	
	//filtro de activos
	private String sCOPOIN = "";
	private String sNOMUIN = "";
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	
	
	private transient ActivoTabla activoseleccionado = null;
	private transient ArrayList<ActivoTabla> tablaactivos = null;

	private transient GastoTabla gastoseleccionado = null;
	private transient ArrayList<GastoTabla> tablagastos = null;
	
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

	private Map<String,String> tiposcosigaHM = new LinkedHashMap<String, String>();

	public GestorMovimientosGastos()
	{
		Utils.standardIO2File("");//Salida por fichero de texto
		
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
    	
	  	Gasto gasto = QMGastos.getGasto(sCOACES, sCOGRUG, sCOTPGA, sCOSBGA, Utils.compruebaFecha(sFEDEVE));
    	
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
	
	public void cambiaTipo()
	{
		logger.debug("sCOGRUG:|{}|",sCOGRUG);

		if (sCOGRUG !=null && !sCOGRUG.equals(""))
		{
			switch (Integer.parseInt(sCOGRUG)) 
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
			sCOTPGA = "";
			sCOSBGA = "";
		}
	}
	
	public void cambiaSubtipo()
	{
		logger.debug("sCOGRUG:|{}| sCOTPGA:|{}|",sCOGRUG,sCOTPGA);
		
		if (sCOTPGA !=null && !sCOTPGA.equals(""))
		{
			switch (Integer.parseInt(sCOGRUG+sCOTPGA)) 
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
			sCOSBGA = "";
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
		
		if (!CLGastos.existeGasto(sCOACES, sCOGRUG, sCOTPGA, sCOSBGA, Utils.compruebaFecha(sFEDEVE)))
		{
			sMsg = "El gasto informado no se puede tramitar, no existe en el sistema.";
			msg = Utils.pfmsgError(sMsg);
			logger.error(sMsg);
		}
		else
		{
			MovimientoGasto movimiento = new MovimientoGasto (
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
			
			int iSalida = CLGastos.registraMovimiento(movimiento);
			
			logger.debug("Codigo de salida:"+iSalida);
			
			switch (iSalida) 
			{
			case 0: //Sin errores
				sMsg = "El movimiento se ha registrado correctamente.";
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
	public String getsDCOSBGA() {
		return sDCOSBGA;
	}
	public void setsDCOSBGA(String sDCOSBGA) {
		this.sDCOSBGA = sDCOSBGA;
	}


	
}
