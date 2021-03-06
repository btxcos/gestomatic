package com.provisiones.pl;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.FileManager;
import com.provisiones.misc.Utils;
import com.provisiones.types.ResultadoCarga;
import com.provisiones.types.tablas.ResultadosTabla;

public class GestorRecepcion implements Serializable 
{
	private static final long serialVersionUID = -7510569297029106911L;

	private static Logger logger = LoggerFactory.getLogger(GestorRecepcion.class.getName());
	
	private transient ArrayList<ResultadosTabla> tablamensajes = new ArrayList<ResultadosTabla>();
	
	private String sArchivo = "resultado";
	private String sDuracion = "";
	private String sRegistrosProcesados = "";
	private String sRegistrosErroneos = "";
	
	private int iContador = 0;
	
	private boolean bPoll = true;

	public GestorRecepcion ()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorRecepcion...");
		}
	}

	public void borrarResultadosCarga()
	{
    	this.tablamensajes = new ArrayList<ResultadosTabla>();
    	this.sArchivo = "resultado";
    	this.sDuracion = "";
    	this.sRegistrosProcesados = "";
    	this.sRegistrosErroneos = "";
    	this.iContador = 0;
	}
	
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	borrarResultadosCarga();
    }
    
    public void cuenta() 
    {  
        iContador=iContador+1;
        logger.debug("iContador:"+iContador);
    } 
    
	public void cargaArchivo(FileUploadEvent event) 
    {
		borrarResultadosCarga();
		
		if (ConnectionManager.comprobarConexion())
		{
			this.bPoll = false;
			
			FacesMessage msg;
			
			logger.debug("Iniciando carga...");
			
			boolean bRecibido = true;
		
			ResultadoCarga resultado = FileManager.splitter(FileManager.guardarFichero(event,bRecibido),bRecibido);
			
			int iCodigoError = resultado.getiCodigo();

			this.sArchivo = resultado.getsArchivo()+"-resultado";

			this.sRegistrosProcesados = Long.toString(resultado.getLiRegistrosProcesados());
			this.sRegistrosErroneos = Long.toString(resultado.getLiRegistrosProcesados()-resultado.getLiRegistrosCorrectos());

			this.sDuracion = resultado.getsDuracion();
			
			logger.debug("iCodigoError:|{}|",iCodigoError);
			
			if (resultado.getAlCarga().size() > 0)
			{
				this.tablamensajes.addAll(resultado.getAlCarga());
			}
			
			logger.debug("tablamensajes.size():|{}|",tablamensajes.size());
			
			String sMsg = "";
			
			switch (iCodigoError) 
			{
			case 0:
				sMsg = "'"+event.getFile().getFileName() +"' ha subido correctamente.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
				break;
			case 4:
				sMsg = "El archivo de Gastos debe de ser primero supervisado por la entidad.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
				break;
			case 5:
				sMsg = "El archivo de Cierres debe comprobado por la entidad.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
				break;
			case 10:
				sMsg = "ERROR: El archivo '"+event.getFile().getFileName() +"' no tiene un nombre reconocible. Por favor, reviselo.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
				break;
			case -4:
				sMsg = "El archivo proporcionado no pudo ser registrado. Por favor, avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;
			default:
				sMsg = "ERROR: Se encontraron problemas al procesar el archivo '"+event.getFile().getFileName() +"', contiene registros inconsistentes con el sistema. Por favor, reviselo.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;
			}
			
			this.bPoll = true;
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
			logger.debug("Carga completada!");
		}

	}


	public ArrayList<ResultadosTabla> getTablamensajes() {
		return tablamensajes;
	}


	public void setTablamensajes(ArrayList<ResultadosTabla> tablamensajes) {
		this.tablamensajes = tablamensajes;
	}
	
	public String getsArchivo() {
		return sArchivo;
	}

	public void setsArchivo(String sArchivo) {
		this.sArchivo = sArchivo;
	}

	public String getsDuracion() {
		return sDuracion;
	}

	public void setsDuracion(String sDuracion) {
		this.sDuracion = sDuracion;
	}

	public String getsRegistrosProcesados() {
		return sRegistrosProcesados;
	}

	public void setsRegistrosProcesados(String sRegistrosProcesados) {
		this.sRegistrosProcesados = sRegistrosProcesados;
	}

	public String getsRegistrosErroneos() {
		return sRegistrosErroneos;
	}

	public void setsRegistrosErroneos(String sRegistrosErroneos) {
		this.sRegistrosErroneos = sRegistrosErroneos;
	}

	public int getiContador() {
		return iContador;
	}

	public void setiContador(int iContador) {
		this.iContador = iContador;
	}

	public boolean isbPoll() {
		return bPoll;
	}

	public void setbPoll(boolean bPoll) {
		this.bPoll = bPoll;
	}

}

//Autor: Francisco Valverde Manj�n