package com.provisiones.pl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLProvisiones;
import com.provisiones.ll.FileManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ResultadoEnvio;
import com.provisiones.types.tablas.ProvisionTabla;

public class GestorEnvios implements Serializable 
{

	private static final long serialVersionUID = 2302669387183120830L;

	private static Logger logger = LoggerFactory.getLogger(GestorEnvios.class.getName());
	
	private String sNumComunidades = "0";
	private boolean bNumComunidades = true;
	private String sNumCuotas = "0";
	private boolean bNumCuotas = true;
	private String sNumReferencias = "0";
	private boolean bNumReferencias = true;
	private String sNumImpuestos = "0";
	private boolean bNumImpuestos = true;
	private String sNumProvisiones = "0";
	private boolean bNumProvisiones = true;
	private String sNumGastos = "0";
	private boolean bNumGastos = true;

	//Buscar provisión
	private String sNUPROF = "";
	
	private String sNUPROFG = "";
	
	//Filtrar provisión
	private String sFEPFON = "";
	
	private String sNumTransferenciasN34 = "0";
	private boolean bNumTransferenciasN34 = true;

	private String sNumTransferenciasN3414 = "0";
	private boolean bNumTransferenciasN3414 = true;
	
	private transient StreamedContent file;
	
	private String sFicheroComunidades = "";
	private String sFicheroCuotas = "";
	private String sFicheroReferencias = "";
	private String sFicheroImpuestos = "";
	
	private String sFicheroCierres = "";
	private String sFicheroGastos = "";
	
	private String sFicheroTransferenciasN34 = "";
	private String sFicheroTransferenciasN3414 = "";
	
	
	private transient ProvisionTabla provisionseleccionada = null;
	private transient ArrayList<ProvisionTabla> tablaprovisiones = null;
	
	public GestorEnvios()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorEnvios...");	
		}
	}
	
	public void borrarCamposProvision()
	{
		this.sFEPFON = "";
    	
    	this.setProvisionseleccionada(null);
    	this.setTablaprovisiones(null);
	}
	
    public void limpiarPlantillaProvision(ActionEvent actionEvent) 
    {  
    	borrarCamposProvision();
    }

	public void cargarMovimientosPendientes(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
	    	
	    	String sMsg = "";

	    	ResultadoEnvio resultadocomunidades =  FileManager.escribirComunidades();
	    	this.sNumComunidades  = Long.toString(resultadocomunidades.getLiEntradas());
	    	this.bNumComunidades = (resultadocomunidades.getLiEntradas() == 0);

	    	logger.debug("sNumComunidades:|"+sNumComunidades+"|");
	    	if (!bNumComunidades)
	    	{
	    		this.sFicheroComunidades = resultadocomunidades.getsFichero();
	 
	    		if (!sFicheroComunidades.equals(""))
	    		{
		    		sMsg = "Generado el fichero de Comunidades a enviar.";
		        	
		    		msg = Utils.pfmsgInfo(sMsg);
		    		logger.info(sMsg);
	    		}
	    		else
	    		{
		    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Comunidades. Por favor, avise a soporte.";
		        	
		    		msg = Utils.pfmsgError(sMsg);
		    		logger.error(sMsg);
	    		}
	    		
	    		FacesContext.getCurrentInstance().addMessage(null, msg);

	    	}

	    	ResultadoEnvio resultadocuotas =  FileManager.escribirCuotas();
	    	this.sNumCuotas  = Long.toString(resultadocuotas.getLiEntradas());
	    	this.bNumCuotas = (resultadocuotas.getLiEntradas() == 0);
	    	
	    	logger.debug("sNumCuotas:|"+sNumCuotas+"|");
	    	if (!bNumCuotas)
	    	{
	    		this.sFicheroCuotas = resultadocuotas.getsFichero();
	    		 
	    		if (!sFicheroCuotas.equals(""))
	    		{

		    		sMsg = "Generado el fichero de Cuotas a enviar.";
		        	
		    		msg = Utils.pfmsgInfo(sMsg);
		    		logger.info(sMsg);
	    			
	    		}
	    		else
	    		{
		    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Cuotas.";
		        	
		    		msg = Utils.pfmsgError(sMsg);
		    		logger.error(sMsg);
	    		}
	    		
	    		FacesContext.getCurrentInstance().addMessage(null, msg);
	    	}

	    	ResultadoEnvio resultadoreferencias = FileManager.escribirReferencias();
	    	this.sNumReferencias  = Long.toString(resultadoreferencias.getLiEntradas());
	    	this.bNumReferencias = (resultadoreferencias.getLiEntradas() == 0);
	    	
	    	logger.debug("sNumReferencias:|"+sNumReferencias+"|");
	    	if (!bNumReferencias)
	    	{
	    		this.sFicheroReferencias = resultadoreferencias.getsFichero();
	   		 
	    		if (!sFicheroReferencias.equals(""))
	    		{

		    		sMsg = "Generado el fichero de Referencias a enviar.";
		    		msg = Utils.pfmsgInfo(sMsg);
		    		logger.info(sMsg);
	    			
	    		}
	    		else
	    		{
		    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Referencias.";
		        	
		    		msg = Utils.pfmsgError(sMsg);
		    		logger.error(sMsg);
	    		}
	    		
	    		FacesContext.getCurrentInstance().addMessage(null, msg);
	    	}
	    	
	    	ResultadoEnvio resultadorecursos = FileManager.escribirImpuestos();
	    	this.sNumImpuestos  = Long.toString(resultadorecursos.getLiEntradas());
	    	this.bNumImpuestos = (resultadorecursos.getLiEntradas() == 0);
	    	
	    	logger.debug("sNumImpuestos:|"+sNumImpuestos+"|");
	    	if (!bNumImpuestos)
	    	{
	    		this.sFicheroImpuestos = resultadorecursos.getsFichero();
	      		 
	    		if (!sFicheroImpuestos.equals(""))
	    		{

		    		sMsg = "Generado el fichero de Impuestos a enviar.";
		        	
		    		msg = Utils.pfmsgInfo(sMsg);
		    		logger.info(sMsg);
	    			
	    		}
	    		else
	    		{
		    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Impuestos.";
		        	
		    		msg = Utils.pfmsgError(sMsg);
		    		logger.error(sMsg);
	    		}
	    		
	    		FacesContext.getCurrentInstance().addMessage(null, msg);	
	    	}

	    	ResultadoEnvio resultadogastos = FileManager.escribirGastos();
	    	this.sNumGastos  = Long.toString(resultadogastos.getLiEntradas());
	    	this.bNumGastos = (resultadogastos.getLiEntradas() == 0);
	    	
	    	logger.debug("sNumGastos:|"+sNumGastos+"|");
	    	if (!bNumGastos)
	    	{
	    		this.sFicheroGastos = resultadogastos.getsFichero();
	    		 
	    		if (!sFicheroGastos.equals(""))
	    		{

		    		sMsg = "Generado el fichero de Gastos a enviar.";
		        	
		    		msg = Utils.pfmsgInfo(sMsg);
		    		logger.info(sMsg);
	    			
	    		}
	    		else
	    		{
		    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Gastos.";
		        	
		    		msg = Utils.pfmsgError(sMsg);
		    		logger.error(sMsg);
	    		}
	    		
	    		FacesContext.getCurrentInstance().addMessage(null, msg);
	    	}
	    	
	    	ResultadoEnvio resultadoprovisiones = FileManager.escribirCierres();
	    	this.sNumProvisiones  = Long.toString(resultadoprovisiones.getLiEntradas());
	    	this.bNumProvisiones = (resultadoprovisiones.getLiEntradas() == 0);
	    	
	    	logger.debug("sNumProvisiones:|"+sNumProvisiones+"|");
	    	if (!bNumProvisiones)
	    	{
	    		this.sFicheroCierres = resultadoprovisiones.getsFichero();
	     		 
	    		if (!sFicheroCierres.equals(""))
	    		{

		    		sMsg = "Generado el fichero de Provisiones a enviar.";
		        	
		    		msg = Utils.pfmsgInfo(sMsg);
		    		logger.info(sMsg);
	    			
	    		}
	    		else
	    		{
		    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Provisiones.";
		        	
		    		msg = Utils.pfmsgError(sMsg);
		    		logger.error(sMsg);
	    		}
	    		
	    		FacesContext.getCurrentInstance().addMessage(null, msg);
	    	}

	    	/*ResultadoEnvio resultadotransferenciasn34 = FileManager.escribirNorma34();
	    	this.sNumTransferenciasN34  = Long.toString(resultadotransferenciasn34.getLiEntradas());
	    	this.bNumTransferenciasN34 = (resultadotransferenciasn34.getLiEntradas() == 0);
	    	
	    	logger.debug("sNumTransferenciasN34:|"+sNumTransferenciasN34+"|");
	    	if (!bNumTransferenciasN34)
	    	{
	    		this.sFicheroTransferenciasN34 = resultadotransferenciasn34.getsFichero();
	     		 
	    		if (!sFicheroTransferenciasN34.equals(""))
	    		{

		    		sMsg = "Generado el fichero de Transferencias Norma 34.";
		        	
		    		msg = Utils.pfmsgInfo(sMsg);
		    		logger.info(sMsg);
	    			
	    		}
	    		else
	    		{
		    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Transferencias Norma 34.";
		        	
		    		msg = Utils.pfmsgError(sMsg);
		    		logger.error(sMsg);
	    		}
	    		
	    		FacesContext.getCurrentInstance().addMessage(null, msg);
	    	}*/
	    	
	    	if (bNumComunidades && bNumCuotas && bNumReferencias && bNumImpuestos && bNumGastos && bNumProvisiones) 
	    		//&& bNumTransferenciasN34)
	    	{
	    		sMsg = "No hay movimientos pendientes.";
	    		msg = Utils.pfmsgWarning(sMsg);
	    		logger.warn(sMsg);
	    	}
	    	else
	    	{
	    		sMsg = "Cargados todos los movimientos pendientes.";
	    		msg = Utils.pfmsgInfo(sMsg);
	    		logger.info(sMsg);

	    	}

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void cargarMovimientosAsociacionesPendientes(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
	    	
	    	String sMsg = "";

	    	ResultadoEnvio resultadocomunidades =  FileManager.escribirComunidades();
	    	this.sNumComunidades  = Long.toString(resultadocomunidades.getLiEntradas());
	    	this.bNumComunidades = (resultadocomunidades.getLiEntradas() == 0);

	    	logger.debug("sNumComunidades:|"+sNumComunidades+"|");
	    	if (!bNumComunidades)
	    	{
	    		this.sFicheroComunidades = resultadocomunidades.getsFichero();
	 
	    		if (!sFicheroComunidades.equals(""))
	    		{
		    		sMsg = "Generado el fichero de Comunidades a enviar.";
		        	
		    		msg = Utils.pfmsgInfo(sMsg);
		    		logger.info(sMsg);
	    		}
	    		else
	    		{
		    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Comunidades. Por favor, avise a soporte.";
		        	
		    		msg = Utils.pfmsgError(sMsg);
		    		logger.error(sMsg);
	    		}
	    		
	    		FacesContext.getCurrentInstance().addMessage(null, msg);

	    	}

	    	ResultadoEnvio resultadocuotas =  FileManager.escribirCuotas();
	    	this.sNumCuotas  = Long.toString(resultadocuotas.getLiEntradas());
	    	this.bNumCuotas = (resultadocuotas.getLiEntradas() == 0);
	    	
	    	logger.debug("sNumCuotas:|"+sNumCuotas+"|");
	    	if (!bNumCuotas)
	    	{
	    		this.sFicheroCuotas = resultadocuotas.getsFichero();
	    		 
	    		if (!sFicheroCuotas.equals(""))
	    		{

		    		sMsg = "Generado el fichero de Cuotas a enviar.";
		        	
		    		msg = Utils.pfmsgInfo(sMsg);
		    		logger.info(sMsg);
	    			
	    		}
	    		else
	    		{
		    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Cuotas.";
		        	
		    		msg = Utils.pfmsgError(sMsg);
		    		logger.error(sMsg);
	    		}
	    		
	    		FacesContext.getCurrentInstance().addMessage(null, msg);
	    	}

	    	ResultadoEnvio resultadoreferencias = FileManager.escribirReferencias();
	    	this.sNumReferencias  = Long.toString(resultadoreferencias.getLiEntradas());
	    	this.bNumReferencias = (resultadoreferencias.getLiEntradas() == 0);
	    	
	    	logger.debug("sNumReferencias:|"+sNumReferencias+"|");
	    	if (!bNumReferencias)
	    	{
	    		this.sFicheroReferencias = resultadoreferencias.getsFichero();
	   		 
	    		if (!sFicheroReferencias.equals(""))
	    		{

		    		sMsg = "Generado el fichero de Referencias a enviar.";
		    		msg = Utils.pfmsgInfo(sMsg);
		    		logger.info(sMsg);
	    			
	    		}
	    		else
	    		{
		    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Referencias.";
		        	
		    		msg = Utils.pfmsgError(sMsg);
		    		logger.error(sMsg);
	    		}
	    		
	    		FacesContext.getCurrentInstance().addMessage(null, msg);
	    	}
	    	
	    	ResultadoEnvio resultadorecursos = FileManager.escribirImpuestos();
	    	this.sNumImpuestos  = Long.toString(resultadorecursos.getLiEntradas());
	    	this.bNumImpuestos = (resultadorecursos.getLiEntradas() == 0);
	    	
	    	logger.debug("sNumImpuestos:|"+sNumImpuestos+"|");
	    	if (!bNumImpuestos)
	    	{
	    		this.sFicheroImpuestos = resultadorecursos.getsFichero();
	      		 
	    		if (!sFicheroImpuestos.equals(""))
	    		{

		    		sMsg = "Generado el fichero de Impuestos a enviar.";
		        	
		    		msg = Utils.pfmsgInfo(sMsg);
		    		logger.info(sMsg);
	    			
	    		}
	    		else
	    		{
		    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Impuestos.";
		        	
		    		msg = Utils.pfmsgError(sMsg);
		    		logger.error(sMsg);
	    		}
	    		
	    		FacesContext.getCurrentInstance().addMessage(null, msg);	
	    	}

	    	if (bNumComunidades && bNumCuotas && bNumReferencias && bNumImpuestos)
	    	{
	    		sMsg = "No hay movimientos de Asociaciones con Activos pendientes.";
	    		msg = Utils.pfmsgWarning(sMsg);
	    		logger.warn(sMsg);
	    	}
	    	else
	    	{
	    		sMsg = "Cargados todos los movimientos de Asociaciones con Activos pendientes.";
	    		msg = Utils.pfmsgInfo(sMsg);
	    		logger.info(sMsg);

	    	}

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void cargarMovimientosGastosPendientes(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
	    	
	    	String sMsg = "";

	      	ResultadoEnvio resultadogastos = FileManager.escribirGastos();
	    	this.sNumGastos  = Long.toString(resultadogastos.getLiEntradas());
	    	this.bNumGastos = (resultadogastos.getLiEntradas() == 0);
	    	
	    	logger.debug("sNumGastos:|"+sNumGastos+"|");
	    	if (!bNumGastos)
	    	{
	    		this.sFicheroGastos = resultadogastos.getsFichero();
	    		 
	    		if (!sFicheroGastos.equals(""))
	    		{

		    		sMsg = "Generado el fichero de Gastos a enviar.";
		        	
		    		msg = Utils.pfmsgInfo(sMsg);
		    		logger.info(sMsg);
	    			
	    		}
	    		else
	    		{
		    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Gastos.";
		        	
		    		msg = Utils.pfmsgError(sMsg);
		    		logger.error(sMsg);
	    		}
	    		
	    		FacesContext.getCurrentInstance().addMessage(null, msg);
	    	}
	    	
	    	ResultadoEnvio resultadoprovisiones = FileManager.escribirCierres();
	    	this.sNumProvisiones  = Long.toString(resultadoprovisiones.getLiEntradas());
	    	this.bNumProvisiones = (resultadoprovisiones.getLiEntradas() == 0);
	    	
	    	logger.debug("sNumProvisiones:|"+sNumProvisiones+"|");
	    	if (!bNumProvisiones)
	    	{
	    		this.sFicheroCierres = resultadoprovisiones.getsFichero();
	     		 
	    		if (!sFicheroCierres.equals(""))
	    		{

		    		sMsg = "Generado el fichero de Provisiones a enviar.";
		        	
		    		msg = Utils.pfmsgInfo(sMsg);
		    		logger.info(sMsg);
	    			
	    		}
	    		else
	    		{
		    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Provisiones.";
		        	
		    		msg = Utils.pfmsgError(sMsg);
		    		logger.error(sMsg);
	    		}
	    		
	    		FacesContext.getCurrentInstance().addMessage(null, msg);
	    	}

	  	    	
	    	if (bNumGastos && bNumProvisiones)
	    	{
	    		sMsg = "No hay movimientos de Gastos pendientes.";
	    		msg = Utils.pfmsgWarning(sMsg);
	    		logger.warn(sMsg);
	    	}
	    	else
	    	{
	    		sMsg = "Cargados todos los movimientos de Gastos pendientes.";
	    		msg = Utils.pfmsgInfo(sMsg);
	    		logger.info(sMsg);

	    	}

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
			this.setTablaprovisiones(null);
			
			if (sFecha.equals("#"))
			{
				sMsg = "La fecha proporcionada no es válida. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
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
				
				this.setTablaprovisiones(CLProvisiones.buscarProvisionesPagadasConFiltro(filtro));

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
	
	public void cargarMovimientosPagosPendientes(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
	    	
	    	String sMsg = "";
	    	
	    	this.sNumTransferenciasN34  = "0";
	    	this.bNumTransferenciasN34 = true;
	    	this.sFicheroTransferenciasN34 = "";
	    	
	    	if (sNUPROF.isEmpty())
	    	{
	    		sMsg = "ERROR: Debe informar la Provision para realizar una búsqueda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
	    	}
	    	else
	    	{
	    		try
	    		{
	    			Integer.parseInt(sNUPROF);
	    			
	    			if (CLProvisiones.existeProvision(sNUPROF))
					{
	    		    	ResultadoEnvio resultadotransferenciasn34 = FileManager.escribirNorma34(sNUPROF);
	    		    	this.sNumTransferenciasN34  = Long.toString(resultadotransferenciasn34.getLiEntradas());
	    		    	this.bNumTransferenciasN34 = (resultadotransferenciasn34.getLiEntradas() == 0);
	    		    	
	    		    	logger.debug("sNumTransferenciasN34:|"+sNumTransferenciasN34+"|");
	    		    	if (!bNumTransferenciasN34)
	    		    	{
	    		    		this.sFicheroTransferenciasN34 = resultadotransferenciasn34.getsFichero();
	    		     		 
	    		    		if (!sFicheroTransferenciasN34.equals(""))
	    		    		{

	    			    		sMsg = "Generado el fichero de Transferencias Norma 34.";
	    			    		sNUPROFG = sNUPROF;
	    			        	
	    			    		msg = Utils.pfmsgInfo(sMsg);
	    			    		logger.info(sMsg);
	    		    			
	    		    		}
	    		    		else
	    		    		{
	    			    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Transferencias Norma 34.";
	    			        	
	    			    		msg = Utils.pfmsgError(sMsg);
	    			    		logger.error(sMsg);
	    		    		}
	    		    		
	    		    		FacesContext.getCurrentInstance().addMessage(null, msg);
	    		    	}
	    		    	
	    		    	if (bNumTransferenciasN34)
	    		    	{
	    		    		sMsg = "No hay movimientos de Pagos pendientes.";
	    		    		msg = Utils.pfmsgWarning(sMsg);
	    		    		logger.warn(sMsg);
	    		    	}
	    		    	else
	    		    	{
	    		    		sMsg = "Cargados todos los movimientos de Pagos pendientes.";
	    		    		msg = Utils.pfmsgInfo(sMsg);
	    		    		logger.info(sMsg);

	    		    	}
					}
					else
					{
						sMsg = "La Provisión '"+sNUPROF+"' no se encuentra regristada en el sistema. Por favor, revise los datos.";
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
	    	}

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void cargarMovimientosPagosPendientesN3414(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
	    	
	    	String sMsg = "";
	    	
	    	this.sNumTransferenciasN34  = "0";
	    	this.bNumTransferenciasN34 = true;
	    	this.sFicheroTransferenciasN34 = "";
	    	
	    	if (sNUPROF.isEmpty())
	    	{
	    		sMsg = "ERROR: Debe informar la Provision para realizar una búsqueda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
	    	}
	    	else
	    	{
	    		try
	    		{
	    			Integer.parseInt(sNUPROF);
	    			
	    			if (CLProvisiones.existeProvision(sNUPROF))
					{
	    		    	ResultadoEnvio resultadotransferenciasn3414 = FileManager.escribirNorma3414(sNUPROF);
	    		    	this.setsNumTransferenciasN3414(Long.toString(resultadotransferenciasn3414.getLiEntradas()));
	    		    	this.setbNumTransferenciasN3414((resultadotransferenciasn3414.getLiEntradas() == 0));
	    		    	
	    		    	logger.debug("sNumTransferenciasN3414:|"+sNumTransferenciasN3414+"|");
	    		    	if (!bNumTransferenciasN3414)
	    		    	{
	    		    		this.sFicheroTransferenciasN3414 = resultadotransferenciasn3414.getsFichero();
	    		     		 
	    		    		if (!sFicheroTransferenciasN3414.equals(""))
	    		    		{

	    			    		sMsg = "Generado el fichero de Transferencias Norma 3414.";
	    			    		sNUPROFG = sNUPROF;
	    			        	
	    			    		msg = Utils.pfmsgInfo(sMsg);
	    			    		logger.info(sMsg);
	    		    			
	    		    		}
	    		    		else
	    		    		{
	    			    		sMsg = "ERROR: Ocurrio un error mientras se procesaban los datos. No se ha generado el fichero de Transferencias Norma 34.";
	    			        	
	    			    		msg = Utils.pfmsgError(sMsg);
	    			    		logger.error(sMsg);
	    		    		}
	    		    		
	    		    		FacesContext.getCurrentInstance().addMessage(null, msg);
	    		    	}
	    		    	
	    		    	if (bNumTransferenciasN3414)
	    		    	{
	    		    		sMsg = "No hay movimientos de Pagos pendientes.";
	    		    		msg = Utils.pfmsgWarning(sMsg);
	    		    		logger.warn(sMsg);
	    		    	}
	    		    	else
	    		    	{
	    		    		sMsg = "Cargados todos los movimientos de Pagos pendientes.";
	    		    		msg = Utils.pfmsgInfo(sMsg);
	    		    		logger.info(sMsg);

	    		    	}
					}
					else
					{
						sMsg = "La Provisión '"+sNUPROF+"' no se encuentra regristada en el sistema. Por favor, revise los datos.";
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
	    	}

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void descargarComunidades() 
    {  
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	String sMsg = "";

	    	try 
			{
				InputStream stream = new FileInputStream(sFicheroComunidades);
				
				this.file = new DefaultStreamedContent(stream, "text/plain", ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E1+".txt");
				
	    		sMsg = "Descargado el fichero de Comunidades a enviar.";
	        	
	    		msg = Utils.pfmsgInfo(sMsg);
	    		logger.info(sMsg);

			} 
			catch (FileNotFoundException e) 
			{
				
				
	    		sMsg = "ERROR: Ocurrio un problema al acceder al archivo.";
	        	
	    		msg = Utils.pfmsgError(sMsg);
	    		logger.error(sMsg);
			}

			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
    }
	
	public void descargarCuotas() 
    {
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	String sMsg = "";

	    	try 
			{
				InputStream stream = new FileInputStream(sFicheroCuotas);
				
				this.file = new DefaultStreamedContent(stream, "text/plain", ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E2+".txt");
				
	    		sMsg = "Descargado el fichero de Cuotas a enviar.";
	        	
	    		msg = Utils.pfmsgInfo(sMsg);
	    		logger.info(sMsg);

			} 
			catch (FileNotFoundException e) 
			{
				
				
	    		sMsg = "ERROR: Ocurrio un problema al acceder al archivo.";
	        	
	    		msg = Utils.pfmsgError(sMsg);
	    		logger.error(sMsg);
			}

			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void descargarReferencias() 
    {
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	String sMsg = "";
	    	
	    	try 
			{
				InputStream stream = new FileInputStream(sFicheroReferencias);
				
				this.file = new DefaultStreamedContent(stream, "text/plain", ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E3+".txt");
				
	    		sMsg = "Descargado el fichero de Referencias a enviar.";
	        	
	    		msg = Utils.pfmsgInfo(sMsg);
	    		logger.info(sMsg);

			} 
			catch (FileNotFoundException e) 
			{
				
				
	    		sMsg = "ERROR: Ocurrio un problema al acceder al archivo.";
	        	
	    		msg = Utils.pfmsgError(sMsg);
	    		logger.error(sMsg);
			}

			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
    }
	
	public void descargarImpuestos() 
    {
		if (ConnectionManager.comprobarConexion())
		{
		   	FacesMessage msg;
	    	
	    	String sMsg = "";
	    	
	    	try 
			{
				InputStream stream = new FileInputStream(sFicheroImpuestos);
				
				this.file = new DefaultStreamedContent(stream, "text/plain", ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E4+".txt");
				
	    		sMsg = "Descargado el fichero de Impuestos a enviar.";
	        	
	    		msg = Utils.pfmsgInfo(sMsg);
	    		logger.info(sMsg);

			} 
			catch (FileNotFoundException e) 
			{
				
				
	    		sMsg = "ERROR: Ocurrio un problema al acceder al archivo.";
	        	
	    		msg = Utils.pfmsgError(sMsg);
	    		logger.error(sMsg);
			}

			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
    }
	
	public void descargarGastos() 
    {
		if (ConnectionManager.comprobarConexion())
		{
		   	FacesMessage msg;
	    	
	    	String sMsg = "";
	    	
	    	try 
			{
				InputStream stream = new FileInputStream(sFicheroGastos);
				
				this.file = new DefaultStreamedContent(stream, "text/plain", ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_GA+".txt");
				
	    		sMsg = "Descargado el fichero de Gastos a enviar.";
	        	
	    		msg = Utils.pfmsgInfo(sMsg);
	    		logger.info(sMsg);

			} 
			catch (FileNotFoundException e) 
			{
				
				
	    		sMsg = "ERROR: Ocurrio un problema al acceder al archivo.";
	        	
	    		msg = Utils.pfmsgError(sMsg);
	    		logger.error(sMsg);
			}

			
			FacesContext.getCurrentInstance().addMessage(null, msg);			
		}		
    }
	
	public void descargarProvisiones() 
    {
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	String sMsg = "";
	    	
	    	try 
			{
				InputStream stream = new FileInputStream(sFicheroCierres);
				
				this.file = new DefaultStreamedContent(stream, "text/plain", ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_PP+".txt");
				
	    		sMsg = "Descargado el fichero de Provisiones a enviar.";
	        	
	    		msg = Utils.pfmsgInfo(sMsg);
	    		logger.info(sMsg);

			} 
			catch (FileNotFoundException e) 
			{
				
				
	    		sMsg = "ERROR: Ocurrio un problema al acceder al archivo.";
	        	
	    		msg = Utils.pfmsgError(sMsg);
	    		logger.error(sMsg);
			}

			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
    }
	
	public void descargarTransferenciasN34() 
    {
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	String sMsg = "";
	    	
	    	try 
			{
				InputStream stream = new FileInputStream(sFicheroTransferenciasN34);
				
				this.file = new DefaultStreamedContent(stream, "text/plain", ValoresDefecto.DEF_IDPROV+"_"+sNUPROFG+"_"+Utils.timeStamp()+".Q34");
				
	    		sMsg = "Descargado el fichero de Pagos por Transferencias Norma 34 a enviar.";
	        	
	    		msg = Utils.pfmsgInfo(sMsg);
	    		logger.info(sMsg);

			} 
			catch (FileNotFoundException e) 
			{
	    		sMsg = "ERROR: Ocurrio un problema al acceder al archivo.";
	    		msg = Utils.pfmsgError(sMsg);
	    		logger.error(sMsg);
			}

			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
    }
	
	public void descargarTransferenciasN3414() 
    {
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	String sMsg = "";
	    	
	    	try 
			{
				InputStream stream = new FileInputStream(sFicheroTransferenciasN3414);
				
				this.file = new DefaultStreamedContent(stream, "text/plain", ValoresDefecto.DEF_IDPROV+"_"+sNUPROFG+"_"+Utils.timeStamp()+".Q34");
				
	    		sMsg = "Descargado el fichero de Pagos por Transferencias Norma 3414 a enviar.";
	        	
	    		msg = Utils.pfmsgInfo(sMsg);
	    		logger.info(sMsg);

			} 
			catch (FileNotFoundException e) 
			{
	    		sMsg = "ERROR: Ocurrio un problema al acceder al archivo.";
	    		msg = Utils.pfmsgError(sMsg);
	    		logger.error(sMsg);
			}

			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
    }
	
	public String getsNumComunidades() {
		return sNumComunidades;
	}

	public void setsNumComunidades(String sNumComunidades) {
		this.sNumComunidades = sNumComunidades;
	}

	public String getsNumCuotas() {
		return sNumCuotas;
	}

	public void setsNumCuotas(String sNumCuotas) {
		this.sNumCuotas = sNumCuotas;
	}

	public String getsNumReferencias() {
		return sNumReferencias;
	}

	public void setsNumReferencias(String sNumReferencias) {
		this.sNumReferencias = sNumReferencias;
	}

	public String getsNumImpuestos() {
		return sNumImpuestos;
	}

	public void setsNumImpuestos(String sNumImpuestos) {
		this.sNumImpuestos = sNumImpuestos;
	}

	public String getsNumProvisiones() {
		return sNumProvisiones;
	}

	public void setsNumProvisiones(String sNumProvisiones) {
		this.sNumProvisiones = sNumProvisiones;
	}

	public String getsNumGastos() {
		return sNumGastos;
	}

	public void setsNumGastos(String sNumGastos) {
		this.sNumGastos = sNumGastos;
	}
	
		public String getsNumTransferenciasN34() {
		return sNumTransferenciasN34;
	}

	public void setsNumTransferenciasN34(String sNumTransferenciasN34) {
		this.sNumTransferenciasN34 = sNumTransferenciasN34;
	}

	public StreamedContent getFile() 
	{
		return file;
	}

	public boolean isbNumComunidades() {
		return bNumComunidades;
	}

	public void setbNumComunidades(boolean bNumComunidades) {
		this.bNumComunidades = bNumComunidades;
	}

	public boolean isbNumCuotas() {
		return bNumCuotas;
	}

	public void setbNumCuotas(boolean bNumCuotas) {
		this.bNumCuotas = bNumCuotas;
	}

	public boolean isbNumReferencias() {
		return bNumReferencias;
	}

	public void setbNumReferencias(boolean bNumReferencias) {
		this.bNumReferencias = bNumReferencias;
	}

	public boolean isbNumImpuestos() {
		return bNumImpuestos;
	}

	public void setbNumImpuestos(boolean bNumImpuestos) {
		this.bNumImpuestos = bNumImpuestos;
	}

	public boolean isbNumProvisiones() {
		return bNumProvisiones;
	}

	public void setbNumProvisiones(boolean bNumProvisiones) {
		this.bNumProvisiones = bNumProvisiones;
	}

	public boolean isbNumGastos() {
		return bNumGastos;
	}

	public void setbNumGastos(boolean bNumGastos) {
		this.bNumGastos = bNumGastos;
	}
	
	public boolean isbNumTransferenciasN34() {
		return bNumTransferenciasN34;
	}

	public void setbNumTransferenciasN34(boolean bNumTransferenciasN34) {
		this.bNumTransferenciasN34 = bNumTransferenciasN34;
	}

	public String getsNumTransferenciasN3414() {
		return sNumTransferenciasN3414;
	}

	public void setsNumTransferenciasN3414(String sNumTransferenciasN3414) {
		this.sNumTransferenciasN3414 = sNumTransferenciasN3414;
	}

	public boolean isbNumTransferenciasN3414() {
		return bNumTransferenciasN3414;
	}

	public void setbNumTransferenciasN3414(boolean bNumTransferenciasN3414) {
		this.bNumTransferenciasN3414 = bNumTransferenciasN3414;
	}

	public String getsFicheroComunidades() {
		return sFicheroComunidades;
	}

	public void setsFicheroComunidades(String sFicheroComunidades) {
		this.sFicheroComunidades = sFicheroComunidades;
	}

	public String getsFicheroCuotas() {
		return sFicheroCuotas;
	}

	public void setsFicheroCuotas(String sFicheroCuotas) {
		this.sFicheroCuotas = sFicheroCuotas;
	}

	public String getsFicheroReferencias() {
		return sFicheroReferencias;
	}

	public void setsFicheroReferencias(String sFicheroReferencias) {
		this.sFicheroReferencias = sFicheroReferencias;
	}

	public String getsFicheroImpuestos() {
		return sFicheroImpuestos;
	}

	public void setsFicheroImpuestos(String sFicheroImpuestos) {
		this.sFicheroImpuestos = sFicheroImpuestos;
	}

	public String getsFicheroCierres() {
		return sFicheroCierres;
	}

	public void setsFicheroCierres(String sFicheroCierres) {
		this.sFicheroCierres = sFicheroCierres;
	}

	public String getsFicheroGastos() {
		return sFicheroGastos;
	}

	public void setsFicheroGastos(String sFicheroGastos) {
		this.sFicheroGastos = sFicheroGastos;
	}

	public String getsFicheroTransferenciasN34() {
		return sFicheroTransferenciasN34;
	}

	public void setsFicheroTransferenciasN34(String sFicheroTransferenciasN34) {
		this.sFicheroTransferenciasN34 = sFicheroTransferenciasN34;
	}

	public String getsFicheroTransferenciasN3414() {
		return sFicheroTransferenciasN3414;
	}

	public void setsFicheroTransferenciasN3414(String sFicheroTransferenciasN3414) {
		this.sFicheroTransferenciasN3414 = sFicheroTransferenciasN3414;
	}

	public String getsNUPROF() {
		return sNUPROF;
	}

	public void setsNUPROF(String sNUPROF) {
		this.sNUPROF = sNUPROF;
	}

	public String getsFEPFON() {
		return sFEPFON;
	}

	public void setsFEPFON(String sFEPFON) {
		this.sFEPFON = sFEPFON;
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
}

//Autor: Francisco Valverde Manjón