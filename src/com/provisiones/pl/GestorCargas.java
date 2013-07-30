package com.provisiones.pl;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import com.provisiones.ll.FileManager;
import com.provisiones.misc.Utils;

public class GestorCargas implements Serializable
{

	private static final long serialVersionUID = 942487732660619012L;

	static String sClassName = GestorCargas.class.getName();
	static boolean bEnable = true;

	String sCampo = "un dato";
	
	
	  
    public String getsCampo() {
		return sCampo;
	}


	public void setsCampo(String sCampo) {
		this.sCampo = sCampo;
	}


	public void handleFileUpload(FileUploadEvent event)throws IOException 
    {
		String sMethod = "guardarFichero";

		Utils.standardIO2File("");//Salida por fichero de texto
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod,"handleFileUpload >>>");
		
		FileManager.guardarFichero(event);
		FileManager.splitter(event.getFile().getFileName());

		setsCampo("El archivo " + event.getFile().getFileName() + " ha subido correctamente.");
		FacesMessage msg = new FacesMessage("Correcto!", event.getFile().getFileName() + " ha subido correctamente.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod,"<<< handleFileUpload");
	}  
}
