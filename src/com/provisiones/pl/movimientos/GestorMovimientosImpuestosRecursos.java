package com.provisiones.pl.movimientos;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.ll.CLImpuestos;
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.ImpuestoRecursoTabla;
import com.provisiones.types.MovimientoImpuestoRecurso;

public class GestorMovimientosImpuestosRecursos implements Serializable 
{

	static String sClassName = GestorMovimientosImpuestosRecursos.class.getName();
	
	private static final long serialVersionUID = -6724439464144511204L;

	private String sCODTRN = ValoresDefecto.DEF_E4_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOACCI = "";
	private String sCOENGP = ValoresDefecto.DEF_COENGP;

	private String sNURCAT = "";

	private String sCOSBAC = "";
	private String sFEPRRE = "";
	private String sFERERE = "";
	private String sFEDEIN = "";
	private String sBISODE = "";
	private String sBIRESO = "";
	private String sCOTEXA = ValoresDefecto.DEF_COTEXA;
	private String sOBTEXC = "";
	
	private String sDesCOSBAC = "";
	private String sDesBISODE = "";
	private String sDesBIRESO = "";
	
	private String sOBDEER = "";
	
	//Buscar activos
	private String sCOACES = "";

	private String sCOPOIN = "";
	private String sNOMUIN = "";	
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	
	private ArrayList<ActivoTabla> tablaactivos = null;
	private ActivoTabla activoseleccionado = null;
	
	private ArrayList<ImpuestoRecursoTabla> tablaimpuestos = null;
	private ImpuestoRecursoTabla impuestoseleccionado = null;
	

	public GestorMovimientosImpuestosRecursos()
	{
		Utils.standardIO2File("");//Salida por fichero de texto
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
	
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";

    	borrarPlantillaActivo();
    	
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
   	
    }
    
	public void borrarPlantillaImpuestos()
	{
		this.sCOACES = "";
        this.sNURCAT = "";
        this.sCOSBAC = "";
        this.sFEPRRE = "";
        this.sFERERE = "";
        this.sFEDEIN = "";
        this.sBISODE = "";
        this.sBIRESO = "";
        //this.sCOTEXA = "";
        this.sOBTEXC = "";
        
        this.sDesCOSBAC = "";
        this.sDesBISODE = "";
        this.sDesBIRESO = "";
	}
    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	

    	borrarPlantillaActivo();
    	
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
    	
    	borrarPlantillaImpuestos();
    	
    	this.impuestoseleccionado = null;
    	this.tablaimpuestos = null;
   	
    }
    
	public void cargarImpuestos(ActionEvent actionEvent)
	{
		String sMethod = "cargarImpuestos";
		
		FacesMessage msg;

		
    	this.sNURCAT  = CLReferencias.referenciaCatastralAsociada(sCOACES);
    	
		Utils.debugTrace(true, sClassName, sMethod, "Activo seleccionado: |"+sCOACES+"|");
		Utils.debugTrace(true, sClassName, sMethod, "Referencia cargada: |"+sNURCAT+"|");

    	if (!sNURCAT.equals("") && CLReferencias.estadoReferencia(sNURCAT).equals("A") )
		{
    		

    		Utils.debugTrace(true, sClassName, sMethod, "Buscando impuestos...");
    		
    		this.tablaimpuestos = CLImpuestos.buscarImpuestosActivos(sCOACES.toUpperCase());
    		
    		Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+getTablaimpuestos().size()+" impuestos relacionados.");

    		msg = new FacesMessage("Encontrados "+getTablaimpuestos().size()+" impuestos relacionadas.");
    		
		}
    	else
    	{
    		Utils.debugTrace(true, sClassName, sMethod, "ERROR: No existe referencia catastral de alta para el activo consultado.");
    		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"No existe referencia catastral de alta para el activo consultado.",null);
        }
    	
    	FacesContext.getCurrentInstance().addMessage(null, msg);
				
	}
	
	public void seleccionarImpuesto(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "seleccionarImpuesto";

    	FacesMessage msg;
    	
    	this.sCOSBAC = impuestoseleccionado.getCOSBAC();
    	this.sDesCOSBAC = impuestoseleccionado.getDCOSBAC();
    	this.sFEPRRE = impuestoseleccionado.getFEPRRE();
    	this.sFERERE = impuestoseleccionado.getFERERE();
    	this.sFEDEIN = impuestoseleccionado.getFEDEIN();
    	this.sBISODE = impuestoseleccionado.getBISODE();
    	this.sDesBISODE = impuestoseleccionado.getDBISODE();
    	this.sBIRESO = impuestoseleccionado.getBIRESO();
    	this.sDesBIRESO = impuestoseleccionado.getBIRESO();
    	this.sOBTEXC = impuestoseleccionado.getOBTEXC();
    	
    	
    	msg = new FacesMessage("'"+sDesCOSBAC +"' Seleccionado.");
    	
    	
    	
    	Utils.debugTrace(true, sClassName, sMethod, "Impuesto seleccionado: |"+sCOSBAC+"|"+sDesCOSBAC+"|");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
    }
    
	public void registraDatos(ActionEvent actionEvent)
	{
		String sMethod = "registraDatos";
		
		//MovimientoComunidad movimiento = new MovimientoComunidad (sCODTRN.toUpperCase(), sCOTDOR.toUpperCase(), sIDPROV.toUpperCase(), sCOACCI.toUpperCase(), sCOENGP.toUpperCase(), sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase(), sBITC10.toUpperCase(), sCOACES.toUpperCase(), sBITC01.toUpperCase(), sNOMCOC.toUpperCase(), sBITC02.toUpperCase(), sNODCCO.toUpperCase(), sBITC03.toUpperCase(), sNOMPRC.toUpperCase(), sBITC04.toUpperCase(), sNUTPRC.toUpperCase(), sBITC05.toUpperCase(), sNOMADC.toUpperCase(), sBITC06.toUpperCase(), sNUTADC.toUpperCase(), sBITC07.toUpperCase(), sNODCAD.toUpperCase(), sBITC08.toUpperCase(), sNUCCEN.toUpperCase(), sNUCCOF.toUpperCase(), sNUCCDI.toUpperCase(), sNUCCNT.toUpperCase(), sBITC09.toUpperCase(), sOBTEXC.toUpperCase(), sOBDEER.toUpperCase());
		MovimientoImpuestoRecurso movimiento = new MovimientoImpuestoRecurso (
				sCODTRN.toUpperCase(), 
				sCOTDOR.toUpperCase(), 
				sIDPROV.toUpperCase(), 
				sCOACCI.toUpperCase(), 
				sCOENGP.toUpperCase(), 
				sCOACES.toUpperCase(),
				sNURCAT.toUpperCase(),
				ValoresDefecto.DEF_COGRUG_E4, 
				ValoresDefecto.DEF_COTACA_E4, 
				sCOSBAC.toUpperCase(),
				"", 
				Utils.compruebaFecha(sFEPRRE.toUpperCase()),
				"", 
				Utils.compruebaFecha(sFERERE.toUpperCase()),
				"", 
				Utils.compruebaFecha(sFEDEIN.toUpperCase()),
				"", 
				Utils.compruebaCodigoAlfa(sBISODE.toUpperCase()),
				"", 
				Utils.compruebaCodigoAlfa(sBIRESO.toUpperCase()),
				sCOTEXA.toUpperCase(),
				"", 
				sOBTEXC.toUpperCase(), 
				sOBDEER.toUpperCase());
		
		FacesMessage msg;
		
		String sMsg = "";
		
		int iSalida = CLImpuestos.registraMovimiento(movimiento);
		
		Utils.debugTrace(true, sClassName, sMethod, "Codigo de salida:"+iSalida);
		
		switch (iSalida) 
		{
		case 0: //Sin errores
			sMsg = "El movimiento se ha registrado correctamente.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(sMsg,null);
			break;

		case -1: //Error 001 - CODIGO DE ACCION DEBE SER A,M o B
			sMsg = "ERROR:001 - No se ha elegido una acccion correcta. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -3: //Error 003 - NO EXISTE EL ACTIVO
			sMsg = "ERROR:003 - El activo elegido no esta registrado en el sistema. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -32: //Error 032 - EL SUBTIPO DE ACCION NO EXISTE
			sMsg = "ERROR:032 - No se a informado el concepto. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -54: //Error 054 - LA REFERENCIA CATASTRAL ES OBLIGATORIA
			sMsg = "ERROR:054 - La referencia catastral es obligatoria. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -55: //Error 055 - LA FECHA PRESENTACION DE RECURSO DEBE SER LOGICA Y OBLIGATORIA
			sMsg = "ERROR:055 - La fecha de presentacion de recurso es obligatoria.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;			

		case -61: //Error 061 - NO SE PUEDE REALIZAR EL ALTA PORQUE NO EXISTE REFERENCIA CATASTRAL EN GMAE13
			sMsg = "ERROR:061 - La referencia catastral proporcionada no se encuentra registrada.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -62: //Error 062 - INDICADOR SOLICITUD DEVOLUCION DEBE SER 'S' O 'N'
			sMsg = "ERROR:062 - El indicador de solicitud de devolucion es obligatorio.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -64: //Error 064 - NO SE PUEDE REALIZAR EL ALTA PORQUE YA EXISTE EL REGISTRO EN GMAE57
			sMsg = "ERROR:064 - El recurso o impuesto ya se encuentra registrado. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -66: //Error 066 - NO SE PUEDE ACTUALIZAR PORQUE NO EXISTE EL REGISTRO EN GMAE57
			sMsg = "ERROR:066 - El recurso o impuesto no se encuentra registrado. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -67: //Error 067 - NO SE PUEDE ACTUALIZAR PORQUE NO EXISTE REFERENCIA CATASTRAL EN GMAE13
			sMsg = "ERROR:067 - La referencia catrastral no se encuentra registrada. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -68: //Error 068 - NO SE PUEDE ELIMINAR PORQUE NO EXISTE REGISTRO EN GMAE57
			sMsg = "ERROR:068 - El recurso o impuesto no se encuentra registrado. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -101: //Error 101 - TIENE F.PRESENTACION, TIPO RESOLUCION Y NO F.RESOLUCION
			sMsg = "ERROR:101 - El recurso o impuesto tiene informada la fecha de presentacion, el tipo de resolucion pero no la fecha de resolucion. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -102: //Error 102 - TIENE F.PRESENTACION, F.RESOLUCION Y NO TIPO RESOLUCION
			sMsg = "ERROR:102 - El recurso o impuesto tiene informada la fecha de presentacion, la fecha de resolucion pero no el tipo de resolucion. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -103: //Error 103 - NO TIENE F.PRESENTACION Y SI TIPO RESOLUCION
			sMsg = "ERROR:103 - El recurso o impuesto no tiene informada la fecha de presentacion pero si el tipo de resolucion. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -104: //Error 104 - NO TIENE F.PRESENTACION, TIPO RESOLUCION Y SI F.RESOLUCION
			sMsg = "ERROR:104 - El recurso o impuesto no tiene informada la fecha de presentacion pero si el tipo de resolucion y fecha de resolucion. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -105: //Error 105 - NO TIENE S.DEVOLUCION, Y SI F.DEVOLUCION
			sMsg = "ERROR:105 - El recurso o impuesto no tiene informado el indicador de devolucion pero si la fecha de devolucion. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -106: //Error 106 - NO TIENE F.PRESENTACION, Y SI F.DEVOLUCION
			sMsg = "ERROR:106 - El recurso o impuesto no tiene informada la fecha de presentacion pero si la fecha de devolucion. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -107: //Error 107 - NO TIENE TIPO RESOLUCION, Y SI F.DEVOLUCION
			sMsg = "ERROR:107 - El recurso o impuesto no tiene informado el tipo de resolucion pero si la fecha de devolucion. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -108: //Error 108 - NO TIENE F.PRESENTACION, Y SI S.DEVOLUCION
			sMsg = "ERROR:108 - El recurso o impuesto no tiene informada la fecha de presentacion pero si el indicador de devolucion. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -109: //Error 109 - EL TIPO RESOLUCION ES DESFAVORABLE Y TIENE F.DEVOLUCION
			sMsg = "ERROR:109 - El tipo de resolucion es 'DESFAVORABLE' y tiene informada la fecha de devolucion. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -110: //Error 110 - LA F.RESOLUCION ES MENOR A LA F.PRESENTACION
			sMsg = "ERROR:110 - La fecha de resolucion es anterior a la fecha de presentacion. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -111: //Error 111 - LA F.DEVOLUCION ES MENOR A LA F.PRESENTACION
			sMsg = "ERROR:111 - La fecha de devolucion es anterior a la fecha de presentacion. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -112: //Error 112 - LA F.DEVOLUCION ES MENOR A LA F.RESOLUCION
			sMsg = "ERROR:112 - La fecha de devolucion es anterior a la fecha de resolucion. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -700: //Error 700 - no existe relacion con el activo
			sMsg = "ERROR:700 - El activo suministrado no esta relacionado con la referencia catastral informada. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -801: //Error 801 - alta de un impuesto/recurso en alta
			sMsg = "ERROR:801 - El impuesto o recurso ya esta dada de alta. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -802: //Error 802 - impuesto/recurso de baja no puede recibir mas movimientos
			sMsg = "ERROR:802 - El impuesto o recurso esta baja y no puede recibir mas movimientos. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -803: //Error 803 - estado no disponible
			sMsg = "ERROR:803 - El estado del impuesto o recurso informado no esta disponible. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -804: //Error 804 - modificacion sin cambios
			sMsg = "ERROR:804 - No hay modificaciones que realizar. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -805: //Error 805 - fecha de resolucion es invalida
			sMsg = "ERROR:805 - La fecha de resolucion es invalida. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -806: //Error 806 - fecha de devolucion es invalida
			sMsg = "ERROR:806 -  La fecha de devolucion es invalida. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -807: //Error 807 - fecha de devolucion es invalida
			sMsg = "ERROR:807 -  La fecha de presentacion es invalida. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -900: //Error 900 - al crear un movimiento
			sMsg = "ERROR:900 - Se ha producido un error al registrar el movimiento. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -901: //Error 901 - error y rollback - error al crear el impuesto/recurso
			sMsg = "ERROR:901 - Se ha producido un error al registrar el impuesto o recurso. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;
			
		case -902: //Error 902 - error y rollback - error al registrar la relaccion
			sMsg = "ERROR:902 - Se ha producido un error al registrar la relacion. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -903: //Error 903 - error y rollback - error al cambiar el estado
			sMsg = "ERROR:903 - Se ha producido un error al cambiar el estado del impuesto o recurso. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -904: //Error 904 - error y rollback - error al modificar el impuesto/recurso
			sMsg = "ERROR:904 - Se ha producido un error al modificar el impuesto o recurso. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		default: //error generico
			sMsg = "ERROR:"+iSalida+" - La operacion solicitada ha producido un error desconocido. Por favor, revise los datos."; 
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;
		}
		
		
		Utils.debugTrace(true, sClassName, sMethod, "Finalizadas las comprobaciones.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
	
	public void hoyFEPRRE (ActionEvent actionEvent)
	{
		String sMethod = "hoyFEPRRE";
		this.setsFEPRRE(Utils.fechaDeHoy(true));
		Utils.debugTrace(true, sClassName, sMethod, "sFEPRRE:|"+sFEPRRE+"|");
	}
	
	public void hoyFERERE (ActionEvent actionEvent)
	{
		String sMethod = "hoyFERERE";
		this.setsFERERE(Utils.fechaDeHoy(true));
		Utils.debugTrace(true, sClassName, sMethod, "sFERERE:|"+sFERERE+"|");
	}
	
	public void hoyFEDEIN (ActionEvent actionEvent)
	{
		String sMethod = "hoyFEDEIN";
		this.setsFEDEIN(Utils.fechaDeHoy(true));
		Utils.debugTrace(true, sClassName, sMethod, "sFEDEIN:|"+sFEDEIN+"|");
	}
	
	public void seleccionarActivo(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "seleccionarActivo";

    	FacesMessage msg;
    	

    	this.sCOACES  = activoseleccionado.getCOACES();
    	this.sNURCAT  = CLReferencias.referenciaCatastralAsociada(sCOACES);
    	
    	if (sNURCAT.equals("") || !CLReferencias.estadoReferencia(sNURCAT).equals("A"))
    	{
    		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"La referencia catastral seleccionada no esta de alta.",null);
    		this.sNURCAT  = "";
    	}
    	else
    	{	
    	
    		msg = new FacesMessage("Referencia "+ sNURCAT +" cargada.");
    	
    		Utils.debugTrace(true, sClassName, sMethod, "Activo seleccionado: |"+sCOACES+"|");
    		Utils.debugTrace(true, sClassName, sMethod, "Referencia cargada: |"+sNURCAT+"|");
    	}

		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		//return "listacomunidadesactivos.xhtml";
    }
	
	public void buscaActivos (ActionEvent actionEvent)
	{
		
		String sMethod = "buscaActivosComunidad";
		
		
		FacesMessage msg;
		
		ActivoTabla buscaactivos = new ActivoTabla(
				sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
				sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
				sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(), "");
		
		Utils.debugTrace(true, sClassName, sMethod, "Buscando Activos...");
		
		this.setTablaactivos(CLImpuestos.buscarActivosConImpuestos(buscaactivos));
		
		Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+getTablaactivos().size()+" activos relacionados.");

		msg = new FacesMessage("Encontrados "+getTablaactivos().size()+" activos relacionados.");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
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

	public String getsCOACCI() {
		return sCOACCI;
	}

	public void setsCOACCI(String sCOACCI) {
		this.sCOACCI = sCOACCI;
	}

	public String getsCOENGP() {
		return sCOENGP;
	}

	public void setsCOENGP(String sCOENGP) {
		this.sCOENGP = sCOENGP;
	}

	public String getsNURCAT() {
		return sNURCAT;
	}

	public void setsNURCAT(String sNURCAT) {
		this.sNURCAT = sNURCAT;
	}

	public String getsCOSBAC() {
		return sCOSBAC;
	}

	public void setsCOSBAC(String sCOSBAC) {
		this.sCOSBAC = sCOSBAC;
	}

	public String getsFEPRRE() {
		return sFEPRRE;
	}

	public void setsFEPRRE(String sFEPRRE) {
		this.sFEPRRE = sFEPRRE;
	}

	public String getsFERERE() {
		return sFERERE;
	}

	public void setsFERERE(String sFERERE) {
		this.sFERERE = sFERERE;
	}

	public String getsFEDEIN() {
		return sFEDEIN;
	}

	public void setsFEDEIN(String sFEDEIN) {
		this.sFEDEIN = sFEDEIN;
	}

	public String getsBISODE() {
		return sBISODE;
	}

	public void setsBISODE(String sBISODE) {
		this.sBISODE = sBISODE;
	}

	public String getsBIRESO() {
		return sBIRESO;
	}

	public void setsBIRESO(String sBIRESO) {
		this.sBIRESO = sBIRESO;
	}

	public String getsCOTEXA() {
		return sCOTEXA;
	}

	public void setsCOTEXA(String sCOTEXA) {
		this.sCOTEXA = sCOTEXA;
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

	public ArrayList<ActivoTabla> getTablaactivos() {
		return tablaactivos;
	}

	public void setTablaactivos(ArrayList<ActivoTabla> tablaactivos) {
		this.tablaactivos = tablaactivos;
	}

	public ActivoTabla getActivoseleccionado() {
		return activoseleccionado;
	}

	public void setActivoseleccionado(ActivoTabla activoseleccionado) {
		this.activoseleccionado = activoseleccionado;
	}

	public ImpuestoRecursoTabla getImpuestoseleccionado() {
		return impuestoseleccionado;
	}

	public void setImpuestoseleccionado(ImpuestoRecursoTabla impuestoseleccionado) {
		this.impuestoseleccionado = impuestoseleccionado;
	}

	public ArrayList<ImpuestoRecursoTabla> getTablaimpuestos() {
		return tablaimpuestos;
	}

	public void setTablaimpuestos(ArrayList<ImpuestoRecursoTabla> tablaimpuestos) {
		this.tablaimpuestos = tablaimpuestos;
	}

	public String getsDesCOSBAC() {
		return sDesCOSBAC;
	}

	public void setsDesCOSBAC(String sDesCOSBAC) {
		this.sDesCOSBAC = sDesCOSBAC;
	}

	public String getsDesBISODE() {
		return sDesBISODE;
	}

	public void setsDesBISODE(String sDesBISODE) {
		this.sDesBISODE = sDesBISODE;
	}

	public String getsDesBIRESO() {
		return sDesBIRESO;
	}

	public void setsDesBIRESO(String sDesBIRESO) {
		this.sDesBIRESO = sDesBIRESO;
	}
	
	
	
	
	
}
