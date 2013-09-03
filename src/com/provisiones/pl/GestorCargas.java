package com.provisiones.pl;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import com.provisiones.ll.FileManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

public class GestorCargas implements Serializable
{

	private static final long serialVersionUID = 942487732660619012L;

	static String sClassName = GestorCargas.class.getName();
	static boolean bEnable = true;


	public void handleFileUpload(FileUploadEvent event)throws IOException 
    {
		String sMethod = "handleFileUpload";
		
		FacesMessage msg;

		Utils.standardIO2File("");//Salida por fichero de texto
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod,"handleFileUpload >>>");
		
		if (FileManager.splitter(FileManager.guardarFichero(event)))
		{
			msg = new FacesMessage("'"+event.getFile().getFileName() +"' ha subido correctamente.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		else
		{
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: El archivo '"+event.getFile().getFileName() +"' no tiene un nombre de archivo reconocible: '"+ValoresDefecto.DEF_COAPII+"AC|RG|PA|GA|PP|E1|E2|E3|E4.TXT'. Por favor, reviselo.",null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		 
		
		
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod,"<<< handleFileUpload");
	}  
}
