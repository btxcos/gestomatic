package com.provisiones.pl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLActivos;
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Nota;
import com.provisiones.types.movimientos.MovimientoReferenciaCatastral;
import com.provisiones.types.tablas.ActivoTabla;

public class GestorReferenciasCatastrales implements Serializable 
{
	private static final long serialVersionUID = 2458459163051982275L;
	
	private static Logger logger = LoggerFactory.getLogger(GestorReferenciasCatastrales.class.getName());
	
	//Buscar activos
	private String sCOACES = "";

	//Filtrar activos
	private String sCOPOIN = "";
	private String sNOMUIN = "";	
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	private String sNUFIRE = "";
	
	private String sNURCATF = "";
	
	//Referencia Catastral
	private String sNURCAT = "";
	private String sTIRCAT = "";
	
	//Valor catastral
	private String sIMVSUE = "";
	private String sIMCATA = "";
	private String sFERECA = "";
	
	//Entidad de emisión
	private String sENEMIS = "";

	//Observaciones
	private String sOBTEXC = "";

	//Notas
	private String sNota = "";
	
	private transient ArrayList<ActivoTabla> tablaactivos = null;
	
	private transient ActivoTabla activoseleccionado = null;
	
	
	public GestorReferenciasCatastrales()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorReferenciasCatastrales...");	
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
    	this.sNUFIRE = "";
    	
    	this.sNURCATF = "";
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
    
	public void borrarPlantillaReferencia()
	{
		this.sNURCAT = "";
        this.sTIRCAT = "";
        this.sENEMIS = "";
        this.sOBTEXC = "";
        
        //Ampliacion de valor catastral
        this.sIMVSUE = "";
        this.sIMCATA = "";
        this.sFERECA = "";
	}
    
    public void limpiarPlantillaReferencia(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";

    	borrarResultadosActivo();
    	
    	borrarPlantillaReferencia();

   	
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
			
			if (sNURCATF.isEmpty())
			{
				ActivoTabla buscaactivos = new ActivoTabla(
						sCOACES.toUpperCase(), 
						sCOPOIN.toUpperCase(), 
						sNOMUIN.toUpperCase(),
						sNOPRAC.toUpperCase(), 
						sNOVIAS.toUpperCase(), 
						sNUPIAC.toUpperCase(), 
						sNUPOAC.toUpperCase(), 
						sNUPUAC.toUpperCase(), 
						sNUFIRE.toUpperCase(),
						"");
				
				this.setTablaactivos(CLActivos.buscarListaActivosConReferencia(buscaactivos));
				
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
			else if (CLReferencias.existeReferenciaCatastral(sNURCATF))
			{
				this.setTablaactivos(CLReferencias.buscarActivoAsociado(sNURCATF));
				
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

	    	String sMsg = "";
	    	
	    	this.sCOACES  = activoseleccionado.getCOACES();

			try
			{
				int iCOACES = Integer.parseInt(sCOACES);
				
		    	this.sNURCAT  = CLReferencias.referenciaCatastralActivo(iCOACES);
		    	
		    	if (sNURCAT.isEmpty())
		    	{
		    		if (CLReferencias.estaAsociado(iCOACES))
		    		{
			    		sMsg = "El número de Referencia Catastral asociado ya fue registrado.";
		    		}
		    		else
		    		{
			    		sMsg = "El Activo carece de una Referencia Catastral asociada.";
		    		}
		    		
		    		msg = Utils.pfmsgWarning(sMsg);
		    		logger.warn(sMsg);
		    	}
		    	else
		    	{
		    		sMsg = "La Referencia '"+sNURCAT+"' se ha cargado correctamente.";
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

	 		FacesContext.getCurrentInstance().addMessage(null, msg);
			
		}
    }
	
	public void comprobarActivo(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
	    	String sMsg ="";
	    	
	    	if (sCOACES.isEmpty())
	    	{
				sMsg = "ERROR: El Activo debe de ir informado. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
	    	}
	    	else
	    	{
				try
				{
					int iCOACES = Integer.parseInt(sCOACES);
					
			    	this.sNURCAT  = CLReferencias.referenciaCatastralActivo(iCOACES);
			    	
			    	if (sNURCAT.isEmpty())
			    	{
			    		
			    		if (CLReferencias.estaAsociado(iCOACES))
			    		{
				    		sMsg = "El número de Referencia Catastral asociado ya fue registrado.";
			    		}
			    		else
			    		{
				    		sMsg = "El Activo carece de una Referencia Catastral asociada.";
			    		}
			    		
			    		msg = Utils.pfmsgWarning(sMsg);
			    		logger.warn(sMsg);
			    		FacesContext.getCurrentInstance().addMessage(null, msg);
			    	}

					if (CLActivos.existeActivo(Integer.parseInt(sCOACES)))
					{
						sMsg = "El activo '"+sCOACES+"' está disponible.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
					else
					{
						sMsg = "ERROR: El activo '"+sCOACES+"' no está registrado en el sistema. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
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
	

    
	public void hoyFERECA (ActionEvent actionEvent)
	{
		this.setsFERECA(Utils.fechaDeHoy(true));
		logger.debug("sFERECA:|{}|",sFERECA);
	}
		
	public void realizaAlta(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";

			try
			{
				if (CLReferencias.existeReferenciaCatastral(sNURCAT.toUpperCase()) && !CLReferencias.estaDeBaja(sNURCAT.toUpperCase()))
				{
					sMsg = "ERROR:049 - La Referencia Catastral propocionada ya está dada de alta en el sistema. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else
				{
					
					MovimientoReferenciaCatastral movimiento = new MovimientoReferenciaCatastral (
							ValoresDefecto.DEF_E3_CODTRN, 
							ValoresDefecto.DEF_COTDOR, 
							ValoresDefecto.DEF_IDPROV, 
							"A",
							ValoresDefecto.DEF_COENGP, 
							sCOACES, 
							sNURCAT,
							"", 
							sTIRCAT,
							"", 
							sENEMIS,
							ValoresDefecto.DEF_COTEXA,
							"", 
							sOBTEXC, 
							ValoresDefecto.CAMPO_ALFA_SIN_INFORMAR,
							"", 
							Utils.compruebaImporte(sIMVSUE),
							"", 
							Utils.compruebaImporte(sIMCATA),
							"", 
							Utils.compruebaFecha(sFERECA));
					
					Nota nota = new Nota (false,sNota);
					
					int iSalida = CLReferencias.registraMovimiento(movimiento, nota);
					
					switch (iSalida) 
					{
					case 0: //Sin errores
						sMsg = "La referencia catastral se ha creado correctamente.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
						break;

					case -1: //Error 001 - CODIGO DE ACCION DEBE SER A,M o B
						sMsg = "ERROR:001 - No se ha elegido una acccion correcta. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -3: //Error 003 - NO EXISTE EL ACTIVO
						sMsg = "ERROR:003 - El activo elegido no está registrado en el sistema. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					/*case -49: //Error 049 - LA REFERENCIA CATASTRAL YA EXISTE NO SE PUEDE DAR DE ALTA
						sMsg = "ERROR:049 - La referencia catastral propocionada ya está registrada en el sistema. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;*/

					case -50: //Error 050 - LA REFERENCIA CATASTRAL NO EXISTE NO SE PUEDE MODIFICAR
						sMsg = "ERROR:050 - La referencia catastral propocionada no está registrada en el sistema. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;			

					case -51: //Error 051 - LA REFERENCIA CATASTRAL NO EXISTE NO SE PUEDE DAR DE BAJA
						sMsg = "ERROR:051 - La referencia catastral propocionada no está registrada en el sistema. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -52: //Error 052 - TITULAR CATASTRAL OBLIGATORIO. NO SE PUEDE DAR DE ALTA
						sMsg = "ERROR:052 - El titular catastral es obligatorio. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -53: //Error 053 - EXISTEN DATOS EN GMAE57. NO SE PUEDE REALIZAR LA BAJA
						sMsg = "ERROR:053 - Existen recursos o impuestos pendientes de está referencia. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -54: //Error 054 - LA REFERENCIA CATASTRAL ES OBLIGATORIA
						sMsg = "ERROR:054 - La referencia catastral es obligatoria. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					//Ampliacion de valor catastral
					case -82: //Error 082 - EL VALOR DEL SUELO TIENE QUE SER MAYOR DE CERO
						sMsg = "ERROR:082 - El valor del suelo debe de ser mayor que 0. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -83: //Error 083 - EL VALOR CATASTRAL TIENE QUE SER MAYOR DE CERO
						sMsg = "ERROR:083 - El valor catastral debe de ser mayor que 0. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -85: //Error 085 - FECHA REVISION DEL VALOR CATASTRAL NO TRAE UN VALOR LOGICO
						sMsg = "ERROR:085 - La fecha de revision del valor catastral no está bien informada. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -700: //Error 700 - No existe realcion con ese activo
						sMsg = "ERROR:700 - El activo suministrado no está relacionado con la referencia catastral informada. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -701: //Error 701 - Valor del suelo incorrecto
						sMsg = "ERROR:701 - El valor del suelo no está correctamente informado. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -702: //Error 702 - Valor catastral incorrecto
						sMsg = "ERROR:702 - El valor catastral no está correctamente informado. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -801: //Error 801 - alta de una referencia en alta
						sMsg = "ERROR:801 - La referencia ya está dada de alta. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -802: //Error 802 - referencia catastral de baja no puede recibir movimientos
						sMsg = "ERROR:802 - La referencia catastral está baja y no puede recibir movimientos. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -803: //Error 803 - estado no disponible
						sMsg = "ERROR:803 - El estado de la referencia catastral informada no está disponible. Por favor, revise los datos.";
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
						sMsg = "[FATAL] ERROR:901 - Se ha producido un error al registrar la referencia catastral. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -902: //Error 902 - error y rollback - error al registrar la relaccion
						sMsg = "[FATAL] ERROR:902 - Se ha producido un error al registrar la relacion. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -903: //Error 903 - error y rollback - error al cambiar el estado
						sMsg = "[FATAL] ERROR:903 - Se ha producido un error al cambiar el estado de la referencia catastral. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -904: //Error 904 - error y rollback - error al modificar la cuota
						sMsg = "[FATAL] ERROR:904 - Se ha producido un error al modificar la referencia catastral. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -910: //Error 910 - error y rollback - error al conectar con la base de datos
						sMsg = "[FATAL] ERROR:910 - Se ha producido un error al conectar con la base de datos. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -915: //Error 915 - error y rollback - error al guardar la nota
						sMsg = "[FATAL] ERROR:915 - Se ha producido un error al guardar la nota de la referencia catastral. Por favor, revise los datos y avise a soporte.";
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

	public String getsNURCAT() {
		return sNURCAT;
	}

	public void setsNURCAT(String sNURCAT) {
		this.sNURCAT = sNURCAT.trim().toUpperCase();
	}

	public String getsTIRCAT() {
		return sTIRCAT;
	}

	public void setsTIRCAT(String sTIRCAT) {
		this.sTIRCAT = sTIRCAT.trim().toUpperCase();
	}

	public String getsENEMIS() {
		return sENEMIS;
	}

	public void setsENEMIS(String sENEMIS) {
		this.sENEMIS = sENEMIS.trim().toUpperCase();
	}

	public String getsOBTEXC() {
		return sOBTEXC;
	}

	public void setsOBTEXC(String sOBTEXC) {
		this.sOBTEXC = sOBTEXC.trim().toUpperCase();
	}

	public String getsIMVSUE() {
		return sIMVSUE;
	}

	public void setsIMVSUE(String sIMVSUE) {
		this.sIMVSUE = sIMVSUE.trim();
	}

	public String getsIMCATA() {
		return sIMCATA;
	}

	public void setsIMCATA(String sIMCATA) {
		this.sIMCATA = sIMCATA.trim();
	}

	public String getsFERECA() {
		return sFERECA;
	}

	public void setsFERECA(String sFERECA) {
		this.sFERECA = sFERECA;
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

	public String getsNURCATF() {
		return sNURCATF;
	}

	public void setsNURCATF(String sNURCATF) {
		this.sNURCATF = sNURCATF.trim().toUpperCase();
	}

}

//Autor: Francisco Valverde Manjón