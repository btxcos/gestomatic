package com.provisiones.pl;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLImpuestos;
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.movimientos.MovimientoImpuestoRecurso;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.ReferenciaTabla;

public class GestorImpuestosRecursos implements Serializable 
{
	private static final long serialVersionUID = -6693758981602847934L;
	
	private static Logger logger = LoggerFactory.getLogger(GestorImpuestosRecursos.class.getName());
	
	private String sCODTRN = ValoresDefecto.DEF_E4_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOACCI = "A";
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
	
	private transient ArrayList<ActivoTabla> tablaactivos = null;
	private transient ActivoTabla activoseleccionado = null;

	private transient ArrayList<ReferenciaTabla> tablareferencias = null;
	private transient ReferenciaTabla referenciaseleccionada = null;	
	
	public GestorImpuestosRecursos()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorImpuestosRecursos...");	
		}
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
    
	public void borrarPlantillaImpuesto()
	{
		
        this.sNURCAT = "";
        this.sCOSBAC = "";
        this.sFEPRRE = "";
        this.sFERERE = "";
        this.sFEDEIN = "";
        this.sBISODE = "";
        this.sBIRESO = "";
        this.sOBTEXC = "";
	}
	
	public void borrarResultadosReferencia()
	{
    	this.referenciaseleccionada = null;
    	this.tablareferencias = null;
	}
    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	
    	this.sCOACES = "";

    	borrarResultadosActivo();
    	borrarResultadosReferencia();
    	
    	borrarPlantillaImpuesto();
   	
    }
    
	public void buscaActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			ActivoTabla buscaactivos = new ActivoTabla(
					sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
					sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
					sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(), "");
		
			this.setTablaactivos(CLReferencias.buscarActivosConReferencias(buscaactivos));
			
			msg = Utils.pfmsgInfo("Encontrados "+getTablaactivos().size()+" activos relacionados.");
			logger.info("Encontrados {} activos relacionados.",getTablaactivos().size());
			FacesContext.getCurrentInstance().addMessage(null, msg);	
		}
	}
	
	public void seleccionarActivo(ActionEvent actionEvent) 
    {  
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
	    	
	    	this.sCOACES  = activoseleccionado.getCOACES();

			msg = Utils.pfmsgInfo("Activo '"+ sCOACES +"' cargado.");
			logger.info("Activo '{}' cargado.",sCOACES);
	    	
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
    
	public void cargarReferencias (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			this.tablareferencias = CLReferencias.buscarReferenciasActivo(sCOACES.toUpperCase());
			
			msg = Utils.pfmsgInfo("Encontradas "+getTablareferencias().size()+" referencias relacionadas.");
			logger.info("Encontradas {} referencias relacionadas.",getTablareferencias().size());
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
		
	public void cargarReferencia(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
	    	this.sNURCAT  = CLReferencias.referenciaCatastralAsociada(sCOACES);
	    	
	    	if (sNURCAT.equals("") || !CLReferencias.estadoReferencia(sNURCAT).equals("A"))
	    	{
				msg = Utils.pfmsgError("ERROR: No existe referencia catastral de alta para el activo consultado.");
				logger.error("ERROR: No existe referencia catastral de alta para el activo consultado.");
	    	}
	    	else
			{
				msg = Utils.pfmsgInfo("Encontrada referencia para el activo '"+sCOACES.toUpperCase()+"'.");
				logger.info("Encontrada referencia para el activo '{}'.",sCOACES.toUpperCase());
			}
			
			
			FacesContext.getCurrentInstance().addMessage(null, msg);	
		}
	}
	
	public void seleccionarReferencia(ActionEvent actionEvent) 
    {  
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
	    	
	    	this.sNURCAT = referenciaseleccionada.getNURCAT(); 
	 	
			msg = Utils.pfmsgInfo("Referencia '"+ sNURCAT +"' Seleccionada.");
			logger.info("Referencia '{}' Seleccionada.",sNURCAT);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}
    }
	
	public void hoyFEPRRE (ActionEvent actionEvent)
	{
		this.setsFEPRRE(Utils.fechaDeHoy(true));
		logger.debug("sFEPRRE:|{}|",sFEPRRE);
	}
    
	public void realizaAlta(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			if (CLImpuestos.existeImpuestoRecurso(sNURCAT.toUpperCase(), sCOSBAC) && !CLImpuestos.estaDeBaja(sNURCAT.toUpperCase(), sCOSBAC))
			{
				sMsg = "ERROR:064 - El recurso o impuesto ya se encuentra registrado. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else
			{
				MovimientoImpuestoRecurso movimiento = new MovimientoImpuestoRecurso (
						sCODTRN.toUpperCase(), 
						sCOTDOR.toUpperCase(), 
						sIDPROV.toUpperCase(), 
						"A", 
						sCOENGP.toUpperCase(), 
						sCOACES.toUpperCase(),
						sNURCAT.toUpperCase(),
						ValoresDefecto.DEF_COGRUG_E4, 
						ValoresDefecto.DEF_COTACA_E4, 
						sCOSBAC.toUpperCase(),
						"", 
						Utils.compruebaFecha(sFEPRRE.toUpperCase()),
						"", 
						ValoresDefecto.CAMPO_SIN_INFORMAR,
						"", 
						ValoresDefecto.CAMPO_SIN_INFORMAR,
						"", 
						Utils.compruebaCodigoAlfa(sBISODE.toUpperCase()),
						"", 
						ValoresDefecto.DEF_CODIGO_ALFA,
						ValoresDefecto.DEF_COTEXA,
						"", 
						sOBTEXC.toUpperCase(), 
						sOBDEER.toUpperCase());
				
				int iSalida = CLImpuestos.registraMovimiento(movimiento);
				
				switch (iSalida) 
				{
				case 0: //Sin errores
					sMsg = "El impuesto o recurso se ha creado correctamente.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
					break;

				case -1: //Error 001 - CODIGO DE ACCION DEBE SER A,M o B
					sMsg = "ERROR:001 - No se ha elegido una acccion correcta. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -3: //Error 003 - NO EXISTE EL ACTIVO
					sMsg = "ERROR:003 - El activo elegido no esta registrado en el sistema. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;
					
				case -32: //Error 032 - EL SUBTIPO DE ACCION NO EXISTE
					sMsg = "ERROR:032 - No se a informado el concepto. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -54: //Error 054 - LA REFERENCIA CATASTRAL ES OBLIGATORIA
					sMsg = "ERROR:054 - La referencia catastral es obligatoria. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -55: //Error 055 - LA FECHA PRESENTACION DE RECURSO DEBE SER LOGICA Y OBLIGATORIA
					sMsg = "ERROR:055 - La fecha de presentacion de recurso es obligatoria.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;			

				case -61: //Error 061 - NO SE PUEDE REALIZAR EL ALTA PORQUE NO EXISTE REFERENCIA CATASTRAL EN GMAE13
					sMsg = "ERROR:061 - La referencia catastral proporcionada no se encuentra registrada.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -62: //Error 062 - INDICADOR SOLICITUD DEVOLUCION DEBE SER 'S' O 'N'
					sMsg = "ERROR:062 - El indicador de solicitud de devolucion es obligatorio.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;
					
				/*case -64: //Error 064 - NO SE PUEDE REALIZAR EL ALTA PORQUE YA EXISTE EL REGISTRO EN GMAE57
					sMsg = "ERROR:064 - El recurso o impuesto ya se encuentra registrado. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;*/
					
				case -66: //Error 066 - NO SE PUEDE ACTUALIZAR PORQUE NO EXISTE EL REGISTRO EN GMAE57
					sMsg = "ERROR:066 - El recurso o impuesto no se encuentra registrado. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;
					
				case -67: //Error 067 - NO SE PUEDE ACTUALIZAR PORQUE NO EXISTE REFERENCIA CATASTRAL EN GMAE13
					sMsg = "ERROR:067 - La referencia catrastral no se encuentra registrada. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -68: //Error 068 - NO SE PUEDE ELIMINAR PORQUE NO EXISTE REGISTRO EN GMAE57
					sMsg = "ERROR:068 - El recurso o impuesto no se encuentra registrado. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -101: //Error 101 - TIENE F.PRESENTACION, TIPO RESOLUCION Y NO F.RESOLUCION
					sMsg = "ERROR:101 - El recurso o impuesto tiene informada la fecha de resolucion, el tipo de resolucion pero no la fecha de resolucion. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -102: //Error 102 - TIENE F.PRESENTACION, F.RESOLUCION Y NO TIPO RESOLUCION
					sMsg = "ERROR:102 - El recurso o impuesto tiene informada la fecha de presentacion, la fecha de resolucion pero no el tipo de resolucion. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -103: //Error 103 - NO TIENE F.PRESENTACION Y SI TIPO RESOLUCION
					sMsg = "ERROR:103 - El recurso o impuesto no tiene informada la fecha de presentacion pero si el tipo de resolucion. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -104: //Error 104 - NO TIENE F.PRESENTACION, TIPO RESOLUCION Y SI F.RESOLUCION
					sMsg = "ERROR:104 - El recurso o impuesto no tiene informada la fecha de presentacion pero si el tipo de resolucion y fecha de resolucion. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -105: //Error 105 - NO TIENE S.DEVOLUCION, Y SI F.DEVOLUCION
					sMsg = "ERROR:105 - El recurso o impuesto no tiene informado el indicador de devolucion pero si la fecha de devolucion. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -106: //Error 106 - NO TIENE F.PRESENTACION, Y SI F.DEVOLUCION
					sMsg = "ERROR:106 - El recurso o impuesto no tiene informada la fecha de presentacion pero si la fecha de devolucion. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -107: //Error 107 - NO TIENE TIPO RESOLUCION, Y SI F.DEVOLUCION
					sMsg = "ERROR:107 - El recurso o impuesto no tiene informado el tipo de resolucion pero si la fecha de devolucion. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -108: //Error 108 - NO TIENE F.PRESENTACION, Y SI S.DEVOLUCION
					sMsg = "ERROR:108 - El recurso o impuesto no tiene informada la fecha de presentacion pero si el indicador de devolucion. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -109: //Error 109 - EL TIPO RESOLUCION ES DESFAVORABLE Y TIENE F.DEVOLUCION
					sMsg = "ERROR:109 - El tipo de resolucion es 'DESFAVORABLE' y tiene informada la fecha de devolucion. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -110: //Error 110 - LA F.RESOLUCION ES MENOR A LA F.PRESENTACION
					sMsg = "ERROR:110 - La fecha de resolucion es anterior a la fecha de presentacion. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -111: //Error 111 - LA F.DEVOLUCION ES MENOR A LA F.PRESENTACION
					sMsg = "ERROR:111 - La fecha de devolucion es anterior a la fecha de presentacion. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -112: //Error 112 - LA F.DEVOLUCION ES MENOR A LA F.RESOLUCION
					sMsg = "ERROR:112 - La fecha de devolucion es anterior a la fecha de resolucion. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;
					
				case -700: //Error 700 - no existe relacion con el activo
					sMsg = "ERROR:700 - El activo suministrado no esta relacionado con la referencia catastral informada. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -801: //Error 801 - alta de un impuesto/recurso en alta
					sMsg = "ERROR:801 - El impuesto o recurso ya esta dada de alta. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -802: //Error 802 - impuesto/recurso de baja no puede recibir movimientos
					sMsg = "ERROR:802 - El impuesto o recurso esta baja y no puede recibir movimientos. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;
					
				case -803: //Error 803 - estado no disponible
					sMsg = "ERROR:803 - El estado del impuesto o recurso informado no esta disponible. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -804: //Error 804 - modificacion sin cambios
					sMsg = "ERROR:804 - No hay modificaciones que realizar. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;
					
				case -805: //Error 805 - fecha de resolucion es invalida
					sMsg = "ERROR:805 - La fecha de resolucion es invalida. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -806: //Error 806 - fecha de devolucion es invalida
					sMsg = "ERROR:806 -  La fecha de devolucion es invalida. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;
					
				case -807: //Error 807 - fecha de devolucion es invalida
					sMsg = "ERROR:807 -  La fecha de presentacion es invalida. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					break;

				case -900: //Error 900 - al crear un movimiento
					sMsg = "[FATAL] ERROR:900 - Se ha producido un error al registrar el movimiento. Por favor, revise los datos y avise a soporte.";
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					break;

				case -901: //Error 901 - error y rollback - error al crear el impuesto/recurso
					sMsg = "[FATAL] ERROR:901 - Se ha producido un error al registrar el impuesto o recurso. Por favor, revise los datos y avise a soporte.";
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					break;
					
				case -902: //Error 902 - error y rollback - error al registrar la relaccion
					sMsg = "[FATAL] ERROR:902 - Se ha producido un error al registrar la relacion. Por favor, revise los datos y avise a soporte.";
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					break;

				case -903: //Error 903 - error y rollback - error al cambiar el estado
					sMsg = "[FATAL] ERROR:903 - Se ha producido un error al cambiar el estado del impuesto o recurso. Por favor, revise los datos y avise a soporte.";
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					break;

				case -904: //Error 904 - error y rollback - error al modificar el impuesto/recurso
					sMsg = "[FATAL] ERROR:904 - Se ha producido un error al modificar el impuesto o recurso. Por favor, revise los datos y avise a soporte.";
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

			logger.debug("Finalizadas las comprobaciones.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
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

	public ArrayList<ReferenciaTabla> getTablareferencias() {
		return tablareferencias;
	}

	public void setTablareferencias(ArrayList<ReferenciaTabla> tablareferencias) {
		this.tablareferencias = tablareferencias;
	}

	public ReferenciaTabla getReferenciaseleccionada() {
		return referenciaseleccionada;
	}

	public void setReferenciaseleccionada(ReferenciaTabla referenciaseleccionada) {
		this.referenciaseleccionada = referenciaseleccionada;
	}
	
	
	
}
