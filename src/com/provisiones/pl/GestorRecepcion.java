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

public class GestorRecepcion implements Serializable 
{
	private static final long serialVersionUID = -7510569297029106911L;

	private static Logger logger = LoggerFactory.getLogger(GestorRecepcion.class.getName());
	
	private transient ArrayList<ResultadosTabla> tablamensajes = new ArrayList<ResultadosTabla>();
	
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
			
			boolean bRecibido = true;
		
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
			case -4:
				sMsg = "El archivo de Gastos debe de ser primero supervisado por la entidad.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
				break;
			case -5:
				sMsg = "El archivo de Cierres debe comprobado por la entidad.";
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
