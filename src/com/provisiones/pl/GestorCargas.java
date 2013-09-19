package com.provisiones.pl;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.provisiones.ll.FileManager;
import com.provisiones.misc.Utils;


public class GestorCargas implements Serializable
{

	private static final long serialVersionUID = 942487732660619012L;

	private static Logger logger = LoggerFactory.getLogger(GestorCargas.class.getName());


	public void handleFileUpload(FileUploadEvent event)throws IOException 
    {
		FacesMessage msg;
		
		logger.debug("Iniciando carga...");
		
		int iCodigoError = FileManager.splitter(FileManager.guardarFichero(event));
		
		logger.debug("iCodigoError:|{}|",iCodigoError);
		
		if (iCodigoError == 0)
		{

			msg = Utils.pfmsgTrace("'"+event.getFile().getFileName() +"' ha subido correctamente.");
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
}
