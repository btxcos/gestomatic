package com.provisiones.pl;

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
import com.provisiones.ll.CLComunidades;
import com.provisiones.ll.CLCuotas;
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Comunidad;
import com.provisiones.types.Nota;
import com.provisiones.types.movimientos.MovimientoCuota;
import com.provisiones.types.tablas.ActivoTabla;

public class GestorCuotas implements Serializable 
{
	private static final long serialVersionUID = 498724061894118772L;

	private static Logger logger = LoggerFactory.getLogger(GestorCuotas.class.getName());

	//Buscar activos
	private String sCOACES = "";

	//Filtro activos
	private String sCOPOIN = "";
	private String sNOMUIN = "";	
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	private String sNUFIRE = "";
	
	private String sNURCAT = "";

	//Comunidad
	private String sCOCLDO = "";
	private String sNUDCOM = "";
	private String sNOMCOC = "";
	private String sNODCCO = "";

	//Pago
	private String sCOSBAC = "";
	private String sFIPAGO = "";
	private String sFFPAGO = "";
	private String sIMCUCO = "";
	private String sFAACTA = "";
	private String sPTPAGO = "";
	
	//Observaciones
	private String sOBTEXC = "";

	//Notas
	private String sNota = "";
	
	private transient ArrayList<ActivoTabla> tablaactivos = null;
	private transient ActivoTabla activoseleccionado = null;
	
	private Map<String,String> tiposcocldoHM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposcosbacHM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposptpagoHM = new LinkedHashMap<String, String>();
	
	public GestorCuotas()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorCuotas...");	
			
			tiposcocldoHM.put("C.I.F.",                     "2");
			tiposcocldoHM.put("C.I.F país extranjero.",     "5");
			tiposcocldoHM.put("Otros persona jurídica.",    "J");
			
			tiposcosbacHM.put("Comunidad",	                   	"0");  
			tiposcosbacHM.put("Ordinaria",                     	"1");  
			tiposcosbacHM.put("Extras Comunidad",              	"2");  
			tiposcosbacHM.put("Mancomunidad",                  	"3");  
			tiposcosbacHM.put("Extras Mancomunidad",           	"4");  
			tiposcosbacHM.put("Obras comunidad",               	"5");
			
			tiposptpagoHM.put("APERIODICO",      "1");
			tiposptpagoHM.put("MENSUAL",         "2");
			tiposptpagoHM.put("BIMENSUAL",       "3");
			tiposptpagoHM.put("TRIMESTRAL",      "4");
			tiposptpagoHM.put("CUATRIMESTRAL",   "5");
			tiposptpagoHM.put("SEMESTRAL",       "6");
			tiposptpagoHM.put("ANUAL",           "7");
			tiposptpagoHM.put("VARIOS PERIODOS", "8");
		}
	}
	
	public void borrarCamposFiltroActivo()
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
	}
	
	public void borrarResultadosActivo()
	{
		this.sCOACES = "";
		
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	
    	borrarCamposFiltroActivo();
    	
    	borrarResultadosActivo();
    }
    
	public void borrarCamposComunidad()
	{
		this.sCOCLDO = "";
		this.sNUDCOM = "";
    	this.sNOMCOC = "";
    	this.sNODCCO = "";
	}
	
	public void borrarCamposCuota()
	{
		this.sCOSBAC = "";
		this.sFIPAGO = "";
		this.sFFPAGO = "";
		this.sIMCUCO = "";
		this.sFAACTA = "";
		this.sPTPAGO = "";
		this.sOBTEXC = "";
	}
	
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	borrarCamposFiltroActivo();
    	borrarResultadosActivo();
  
    	borrarCamposComunidad();
    	borrarCamposCuota();
    }
    
    public void limpiarNota(ActionEvent actionEvent) 
    {  
    	this.sNota = "";
    }
    
	public void buscaActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.activoseleccionado = null;
			
	    	this.setTablaactivos(null);
			
			if (sNURCAT.isEmpty())
			{
				ActivoTabla filtro = new ActivoTabla(
						"", 
						sCOPOIN.toUpperCase(), 
						sNOMUIN.toUpperCase(),
						sNOPRAC.toUpperCase(), 
						sNOVIAS.toUpperCase(), 
						sNUPIAC.toUpperCase(), 
						sNUPOAC.toUpperCase(), 
						sNUPUAC.toUpperCase(),
						sNUFIRE.toUpperCase(),
						"");
				
				this.setTablaactivos(CLComunidades.buscarActivosConComunidad(filtro));
				
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
			else if (CLReferencias.existeReferenciaCatastral(sNURCAT))
			{
				this.setTablaactivos(CLReferencias.buscarActivoAsociadoConComunidad(sNURCAT));
				
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
	    	
	    	this.sCOACES  = activoseleccionado.getCOACES();

	    	String sMsg = "Activo '"+ sCOACES +"' Seleccionado.";
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
			borrarCamposCuota();
			
			if (sCOACES.isEmpty())
			{
				sMsg = "ERROR: El Activo debe de ir informado para realizar la búsqueda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else
			{
				try
				{
					Comunidad comunidad = CLComunidades.buscarComunidadDeActivo(Integer.parseInt(sCOACES));
					
					this.sCOCLDO = comunidad.getsCOCLDO();
					this.sNUDCOM = comunidad.getsNUDCOM();
					this.sNOMCOC = comunidad.getsNOMCOC();
					this.sNODCCO = comunidad.getsNODCCO();
					
					if (comunidad.getsNUDCOM().isEmpty())
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
			}

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void hoyFIPAGO (ActionEvent actionEvent)
	{
		this.setsFIPAGO(Utils.fechaDeHoy(true));
		logger.debug("sFIPAGO:|"+sFIPAGO+"|");
	}

	public void hoyFFPAGO (ActionEvent actionEvent)
	{
		this.setsFFPAGO(Utils.fechaDeHoy(true));
		logger.debug("sFFPAGO:|"+sFFPAGO+"|");
	}
	
	public void hoyFAACTA (ActionEvent actionEvent)
	{
		this.setsFAACTA(Utils.fechaDeHoy(true));
		logger.debug("sFAACTA:|"+sFAACTA+"|");
	}
	
	
	public void realizaAlta(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			try
			{
				if (CLCuotas.existeCuota(Integer.parseInt(sCOACES), sCOCLDO, sNUDCOM.toUpperCase(), sCOSBAC) && !CLCuotas.estaDeBaja(Integer.parseInt(sCOACES), sCOCLDO, sNUDCOM.toUpperCase(), sCOSBAC))
				{
					sMsg = "ERROR:801 - La cuota ya esta dada de alta. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else
				{
					
					MovimientoCuota movimiento = new MovimientoCuota (
							ValoresDefecto.DEF_E2_CODTRN, 
							ValoresDefecto.DEF_COTDOR, 
							ValoresDefecto.DEF_IDPROV, 
							ValoresDefecto.DEF_COACCI_CUOTA_ALTA,
							sCOCLDO, 
							sNUDCOM, 
							ValoresDefecto.DEF_COENGP, 
							sCOACES, 
							ValoresDefecto.DEF_COGRUG_E2, 
							ValoresDefecto.DEF_COTACA_E2, 
							Utils.compruebaCodigoPago(false,sCOSBAC), 
							"", 
							Utils.compruebaFecha(sFIPAGO), 
							"", 
							Utils.compruebaFecha(sFFPAGO), 
							"", 
							Utils.compruebaImporte(sIMCUCO),
							"", 
							Utils.compruebaFecha(sFAACTA), 
							"", 
							sPTPAGO.toUpperCase(),
							"", 
							sOBTEXC.toUpperCase(), 
							ValoresDefecto.CAMPO_ALFA_SIN_INFORMAR);
					
					Nota nota = new Nota (false,sNota);
					
					int iSalida = CLCuotas.registraMovimiento(movimiento, nota);
					
					switch (iSalida) 
					{
					case 0: //Sin errores
						sMsg = "La cuota se ha creado correctamente.";
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

					case -4: //Error 004 - CIF DE LA COMUNIDAD NO PUEDE SER BLANCO O NULO
						sMsg = "ERROR:004 - No se ha informado el numero de documento. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -32: //Error 032 - EL SUBTIPO DE ACCION NO EXISTE
						sMsg = "ERROR:032 - El concepto de pago es obligatorio. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -33: //Error 033 - LA FECHA DE PRIMER PAGO DEBE SER LOGICA Y OBLIGATORIA
						sMsg = "ERROR:033 - La fecha del primer pago es obligatoria. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -34: //Error 034 - LA FECHA DE ULTIMO PAGO DEBE SER LOGICA Y OBLIGATORIA
						sMsg = "ERROR:034 - La fecha del ultimo pago es obligatoria. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -35: //Error 035 - LA FECHA DE ULTIMO PAGO NO DEBE DE SER MENOR QUE LA FECHA DE PRIMER PAGO
						sMsg = "ERROR:35 - La fecha del ultimo pago no puede ser menor que la del primero.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -36: //Error 036 - IMPORTE DE CUOTA TIENE QUE SER MAYOR DE CERO
						sMsg = "ERROR:036 - El importe de la cuota tiene ser mayor que cero. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -41: //Error 041 - LA COMUNIDAD NO EXISTE EN LA TABLA DE COMUNIDADES GMAE10
						sMsg = "ERROR:041 - La comunidad propocionada no esta registrada en el sistema. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -42: //Error 042 - LA RELACION ACTIVO-COMUNIDAD YA EXISTE EN GMAE12. NO SE PUEDE REALIZAR EL ALTA
						sMsg = "ERROR:042 - El activo proporcionado esta asociado a otra comunidad. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -43: //Error 043 - LA RELACION ACTIVO-COMUNIDAD NO EXISTE EN GMAE12. NO SE PUEDE REALIZAR LA MODIFICACION
						sMsg = "ERROR:043 - El activo prorcionado no pertenece a la comunidad. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -44: //Error 044 - NO EXISTE PERIOCIDAD DE PAGO
						sMsg = "ERROR:044 - La periodicidad de pago es obligatoria. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -45: //Error 045 - LA RELACION ACTIVO-COMUNIDAD NO EXISTE EN GMAE12. NO SE PUEDE REALIZAR LA BAJA
						sMsg = "ERROR:045 - El activo prorcionado no pertenece a la comunidad. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -46: //Error 046 - LA FECHA DEL ACTA DEBE SER LOGICA Y OBLIGATORIA 
						sMsg = "ERROR:046 - La fecha de acta es obligatoria. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -701: //Error 701 - error en importe
						sMsg = "ERROR:701 - El campo importe no se ha informado correctamente. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -702: //Error 702 - fecha de primer pago incorrecta
						sMsg = "ERROR:702 - La fecha del primer pago no se ha informado correctamente. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -703: //Error 703 - fecha de ultimo pago incorrecta
						sMsg = "ERROR:703 - La fecha del ultimo pago no se ha informado correctamente. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -704: //Error 704 - fecha de acta incorrecta
						sMsg = "ERROR:704 - La fecha de acta no se ha informado correctamente. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					/*case -801: //Error 801 - alta de una cuota en alta
						sMsg = "ERROR:801 - La cuota ya esta dada de alta. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;*/

					case -802: //Error 802 - cuota de baja no puede recibir movimientos
						sMsg = "ERROR:802 - La cuota esta baja y no puede recibir movimientos. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -803: //Error 803 - estado no disponible
						sMsg = "ERROR:803 - El estado de la cuota informada no esta disponible. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -804: //Error 804 - modificacion sin cambios
						sMsg = "ERROR:804 - No hay modificaciones que realizar. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -900: //Error 900 - al crear un movimiento
						sMsg = "[FATAL] ERROR:900 - Se ha producido un error al registrar el movimiento. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -901: //Error 901 - error y rollback - error al crear la cuota
						sMsg = "[FATAL] ERROR:901 - Se ha producido un error al registrar la cuota. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -902: //Error 902 - error y rollback - error al registrar la relaccion
						sMsg = "[FATAL] ERROR:902 - Se ha producido un error al registrar la relacion. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -903: //Error 903 - error y rollback - error al cambiar el estado
						sMsg = "[FATAL] ERROR:903 - Se ha producido un error al cambiar el estado de la cuota. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -904: //Error 904 - error y rollback - error al modificar la cuota
						sMsg = "[FATAL] ERROR:904 - Se ha producido un error al modificar la cuota. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -910: //Error 910 - error y rollback - error al conectar con la base de datos
						sMsg = "[FATAL] ERROR:910 - Se ha producido un error al conectar con la base de datos. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -915: //Error 915 - error y rollback - error al guardar la nota
						sMsg = "[FATAL] ERROR:915 - Se ha producido un error al guardar la nota de la cuota. Por favor, revise los datos y avise a soporte.";
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

	public String getsNURCAT() {
		return sNURCAT;
	}

	public void setsNURCAT(String sNURCAT) {
		this.sNURCAT = sNURCAT.trim().toUpperCase();
	}

	public String getsCOCLDO() {
		return sCOCLDO;
	}

	public void setsCOCLDO(String sCOCLDO) {
		this.sCOCLDO = sCOCLDO;
	}

	public String getsNUDCOM() {
		return sNUDCOM;
	}

	public void setsNUDCOM(String sNUDCOM) {
		this.sNUDCOM = sNUDCOM;
	}

	public String getsNOMCOC() {
		return sNOMCOC;
	}

	public void setsNOMCOC(String sNOMCOC) {
		this.sNOMCOC = sNOMCOC;
	}

	public String getsNODCCO() {
		return sNODCCO;
	}

	public void setsNODCCO(String sNODCCO) {
		this.sNODCCO = sNODCCO;
	}

	public String getsCOSBAC() {
		return sCOSBAC;
	}

	public void setsCOSBAC(String sCOSBAC) {
		this.sCOSBAC = sCOSBAC;
	}

	public String getsFIPAGO() {
		return sFIPAGO;
	}

	public void setsFIPAGO(String sFIPAGO) {
		this.sFIPAGO = sFIPAGO;
	}

	public String getsFFPAGO() {
		return sFFPAGO;
	}

	public void setsFFPAGO(String sFFPAGO) {
		this.sFFPAGO = sFFPAGO;
	}

	public String getsIMCUCO() {
		return sIMCUCO;
	}

	public void setsIMCUCO(String sIMCUCO) {
		this.sIMCUCO = sIMCUCO.trim();
	}

	public String getsFAACTA() {
		return sFAACTA;
	}

	public void setsFAACTA(String sFAACTA) {
		this.sFAACTA = sFAACTA;
	}

	public String getsPTPAGO() {
		return sPTPAGO;
	}

	public void setsPTPAGO(String sPTPAGO) {
		this.sPTPAGO = sPTPAGO;
	}

	public String getsOBTEXC() {
		return sOBTEXC;
	}

	public void setsOBTEXC(String sOBTEXC) {
		this.sOBTEXC = sOBTEXC.trim().toUpperCase();
	}

	public String getsNota() {
		return sNota;
	}

	public void setsNota(String sNota) {
		this.sNota = sNota.trim();
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

	public Map<String, String> getTiposcocldoHM() {
		return tiposcocldoHM;
	}

	public void setTiposcocldoHM(Map<String, String> tiposcocldoHM) {
		this.tiposcocldoHM = tiposcocldoHM;
	}

	public Map<String, String> getTiposcosbacHM() {
		return tiposcosbacHM;
	}

	public void setTiposcosbacHM(Map<String, String> tiposcosbacHM) {
		this.tiposcosbacHM = tiposcosbacHM;
	}

	public Map<String, String> getTiposptpagoHM() {
		return tiposptpagoHM;
	}

	public void setTiposptpagoHM(Map<String, String> tiposptpagoHM) {
		this.tiposptpagoHM = tiposptpagoHM;
	}
}

//Autor: Francisco Valverde Manjón