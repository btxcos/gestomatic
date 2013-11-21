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

import com.provisiones.types.Resultados;
import com.provisiones.types.tablas.ResultadosTabla;


public class GestorCargas implements Serializable
{

	private static final long serialVersionUID = 942487732660619012L;

	private static Logger logger = LoggerFactory.getLogger(GestorCargas.class.getName());
	
	private transient ArrayList<ResultadosTabla> tablamensajes = new ArrayList<ResultadosTabla>();
	
	public GestorCargas ()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorCargas...");
		}
	}

	public void borrarResultadosCarga()
	{
    	this.tablamensajes = new ArrayList<ResultadosTabla>();
	}
	
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	borrarResultadosCarga();
    }
    
	public void cargaArchivo(FileUploadEvent event) 
    {
		
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			logger.debug("Iniciando carga...");
			
			boolean bRecibido = false; 
			
			Resultados carga = FileManager.splitter(FileManager.guardarFichero(event,bRecibido),bRecibido);
			
			int iCodigoError = carga.getiCodigo();
			
			logger.debug("iCodigoError:|{}|",iCodigoError);
			
			if (carga.getAlCarga().size() > 0)
			{
			
				this.tablamensajes.addAll(carga.getAlCarga());
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
			case -1:
				sMsg = "El archivo de Activos debe de ser cargado por recepción.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
				break;
			case -2:
				sMsg = "El archivo de Rechazados debe de ser cargado por recepción.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
				break;
			case -3:
				sMsg = "El archivo de Autorizados debe de ser cargado por recepción.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
				break;
			case -10:
				sMsg = "ERROR: El archivo '"+event.getFile().getFileName() +"' no tiene un nombre reconocible. Por favor, reviselo.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
				break;
			default:
				sMsg = "ERROR: Se encontraron problemas al procesar el archivo '"+event.getFile().getFileName() +"', contiene registros inconsistentes con el sistema. Por favor, reviselo.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;
			}
			
			if (iCodigoError == 0)
			{

				msg = Utils.pfmsgInfo("'"+event.getFile().getFileName() +"' ha subido correctamente.");
				logger.info("'{}' ha subido correctamente.",event.getFile().getFileName());

			}
			else if (iCodigoError == -1)
			{
			
				msg = Utils.pfmsgError("ERROR: El archivo '"+event.getFile().getFileName() +"' no tiene un nombre reconocible. Por favor, reviselo.");
				logger.error("ERROR: El archivo '{}' no tiene un nombre reconocible. Por favor, reviselo.",event.getFile().getFileName());

			}
			else if (iCodigoError == 4)
			{
				msg = Utils.pfmsgWarning("El archivo de Gastos debe de ser primero supervisado por la entidad.");
				logger.warn("El archivo de Gastos debe de ser primero supervisado por la entidad.");
			}
			else if (iCodigoError == 5)
			{
				msg = Utils.pfmsgWarning("El archivo de Cierres debe comprobado por la entidad.");
				logger.warn("El archivo de Cierres debe comprobado por la entidad.");
			}
			else
			{
				msg = Utils.pfmsgFatal("ERROR: Se encontraron problemas al procesar el archivo '"+event.getFile().getFileName() +"', contiene registros inconsistentes con el sistema. Por favor, reviselo.");
				logger.error("[FATAL] ERROR: Se encontraron problemas al procesar el archivo '{}', contiene registros inconsistentes con el sistema. Por favor, reviselo.",event.getFile().getFileName());
			}

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
}
