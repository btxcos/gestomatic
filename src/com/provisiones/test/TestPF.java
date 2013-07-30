package com.provisiones.test;

import java.io.IOException;
import java.io.Serializable;

import com.provisiones.ll.FileManager;

import javax.faces.application.FacesMessage;  
import javax.faces.context.FacesContext;  
  
import org.primefaces.event.FileUploadEvent;  


public class TestPF implements Serializable{  
	  
     
	/**
	 * 
	 */
	private static final long serialVersionUID = 5280073149818806999L;
	static String sClassName = TestPF.class.getName();
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
			FileManager.guardarFichero(event);
			FileManager.splitter(event.getFile().getFileName());

			setsCampo("El archivo " + event.getFile().getFileName() + " ha subido correctamente.");
	        FacesMessage msg = new FacesMessage("Correcto!", event.getFile().getFileName() + " ha subido correctamente.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);  
	}  



  
 
} 
