package com.provisiones.pl;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.provisiones.ll.FileManager;
import com.provisiones.misc.Utils;

import com.provisiones.types.Carga;
import com.provisiones.types.CargaTabla;


public class GestorCargas implements Serializable
{

	private static final long serialVersionUID = 942487732660619012L;

	private static Logger logger = LoggerFactory.getLogger(GestorCargas.class.getName());
	
	private transient ArrayList<CargaTabla> tablamensajes = new ArrayList<CargaTabla>() ;


	public void handleFileUpload(FileUploadEvent event) 
    {
		FacesMessage msg;
		
		logger.debug("Iniciando carga...");
		
		Carga carga = FileManager.splitter(FileManager.guardarFichero(event));
		
		int iCodigoError = carga.getiCodigo();
		
		logger.debug("iCodigoError:|{}|",iCodigoError);
		
		/*ArrayList<CargaTabla> result = getTablamensajes();
		
		logger.debug("result.size():|{}|",result.size());
		
		result.addAll(carga.getAlCarga());
		
		logger.debug("result.size():|{}|",result.size());
		
		
		this.setTablamensajes(result);*/
		
		this.tablamensajes.addAll(carga.getAlCarga());
		
		logger.debug("tablamensajes.size():|{}|",tablamensajes.size());
		
		
		if (iCodigoError == 0)
		{

			msg = Utils.pfmsgInfo("'"+event.getFile().getFileName() +"' ha subido correctamente.");
			logger.info("'{}' ha subido correctamente.",event.getFile().getFileName());

		}
		else if (iCodigoError < 0)
		{
		
			msg = Utils.pfmsgError("ERROR: El archivo '"+event.getFile().getFileName() +"' no tiene un nombre reconocible. Por favor, reviselo.");
			logger.error("ERROR: El archivo '{}' no tiene un nombre reconocible. Por favor, reviselo.",event.getFile().getFileName());

		}
		else
		{

			msg = Utils.pfmsgFatal("ERROR: Se encontraron problemas al procesar el archivo '"+event.getFile().getFileName() +"', contiene registros inconsistentes con el sistema. Por favor, reviselo.");
			logger.error("[FATAL] ERROR: Se encontraron problemas al procesar el archivo '{}', contiene registros inconsistentes con el sistema. Por favor, reviselo.",event.getFile().getFileName());
		}

		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		logger.debug("Carga completada!");
	}


	public ArrayList<CargaTabla> getTablamensajes() {
		return tablamensajes;
	}


	public void setTablamensajes(ArrayList<CargaTabla> tablamensajes) {
		this.tablamensajes = tablamensajes;
	}  
}
