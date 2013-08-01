package com.provisiones.pl;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.dal.qm.QMComunidades;
import com.provisiones.misc.Utils;
import com.provisiones.types.Comunidad;

public class GestorComunidades implements Serializable 
{

	static String sClassName = GestorComunidades.class.getName();
	
	private static final long serialVersionUID = 5043001456385781939L;

	String sCOCLDO = "";
	String sNUDCOM = "";
	String sNOMCOC = "";
	String sNODCCO = "";
	String sNOMPRC = "";
	String sNUTPRC = "";
	String sNOMADC = "";
	String sNUTADC = "";
	String sNODCAD = "";
	String sNUCCEN = "";
	String sNUCCOF = "";
	String sNUCCDI = "";
	String sNUCCNT = "";
	String sOBTEXC = "";
	
	private Map<String,String> tiposdocumentoHM = new LinkedHashMap<String, String>();
	
	public GestorComunidades()
	{
		tiposdocumentoHM.put("D.N.I.",                     "1");
		tiposdocumentoHM.put("C.I.F.",                     "2");
		tiposdocumentoHM.put("Tarjeta Residente.",         "3");
		tiposdocumentoHM.put("Pasaporte",                  "4");
		tiposdocumentoHM.put("C.I.F país extranjero.",     "5");
		tiposdocumentoHM.put("D.N.I país extranjero.",     "7");
		tiposdocumentoHM.put("Tarj. identif. diplomática.","8");
		tiposdocumentoHM.put("Menor.",                     "9");
		tiposdocumentoHM.put("Otros persona física.",      "F");
		tiposdocumentoHM.put("Otros persona jurídica.",    "J");

	}
	
	
	public void realizaAlta(ActionEvent actionEvent)throws IOException 
    {
		String sMethod = "realizaAlta";
		
		FacesMessage msg;
		
		Comunidad comunidad = new Comunidad(sCOCLDO, 
				sNUDCOM, 
				sNOMCOC, 
				sNODCCO, 
				sNOMPRC, 
				sNUTPRC, 
				sNOMADC, 
				sNUTADC, 
				sNODCAD, 
				sNUCCEN, 
				sNUCCOF, 
				sNUCCDI, 
				sNUCCNT, 
				sOBTEXC);

		Utils.standardIO2File("");//Salida por fichero de texto
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Dando de alta la comunidad...");
		
		comunidad.pintaComunidad();
		
		if (QMComunidades.addComunidad(comunidad))
		{
		
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Hecho!");
		
			msg = new FacesMessage("La comunidad "+ comunidad.getNOMCOC() + "se ha creado correctamente.");
			borrarCampos();
		}
		else
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear la comunidad "+ comunidad.getNOMCOC() + ". Por favor, revise los datos.",null);
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		//return "index.xhtml";

	} 

	public void borrarCampos()
	{
		this.sCOCLDO = "";
		this.sNUDCOM = "";
		this.sNOMCOC = "";
		this.sNODCCO = "";
		this.sNOMPRC = "";
		this.sNUTPRC = "";
		this.sNOMADC = "";
		this.sNUTADC = "";
		this.sNODCAD = "";
		this.sNUCCEN = "";
		this.sNUCCOF = "";
		this.sNUCCDI = "";
		this.sNUCCNT = "";
		this.sOBTEXC = "";
	}
	
	public String salir()
	{
		String sPagina = "index.xhtml";
		
		borrarCampos();
		
		return sPagina;
	}
	
	public Map<String, String> getTiposdocumentoHM() {
		return tiposdocumentoHM;
	}



	public void setTiposdocumentoHM(Map<String, String> tiposdocumentoHM) {
		this.tiposdocumentoHM = tiposdocumentoHM;
	}



	public String getsCOCLDO() {
		return sCOCLDO;
	}

	public void setsCOCLDO(String sCOCLDO) {
		this.sCOCLDO = sCOCLDO;
	}

	public String getsNUDCOM() {
		return sNUDCOM;
	}

	public void setsNUDCOM(String sNUDCOM) {
		this.sNUDCOM = sNUDCOM;
	}

	public String getsNOMCOC() {
		return sNOMCOC;
	}

	public void setsNOMCOC(String sNOMCOC) {
		this.sNOMCOC = sNOMCOC;
	}

	public String getsNODCCO() {
		return sNODCCO;
	}

	public void setsNODCCO(String sNODCCO) {
		this.sNODCCO = sNODCCO;
	}

	public String getsNOMPRC() {
		return sNOMPRC;
	}

	public void setsNOMPRC(String sNOMPRC) {
		this.sNOMPRC = sNOMPRC;
	}

	public String getsNUTPRC() {
		return sNUTPRC;
	}

	public void setsNUTPRC(String sNUTPRC) {
		this.sNUTPRC = sNUTPRC;
	}

	public String getsNOMADC() {
		return sNOMADC;
	}

	public void setsNOMADC(String sNOMADC) {
		this.sNOMADC = sNOMADC;
	}

	public String getsNUTADC() {
		return sNUTADC;
	}

	public void setsNUTADC(String sNUTADC) {
		this.sNUTADC = sNUTADC;
	}

	public String getsNODCAD() {
		return sNODCAD;
	}

	public void setsNODCAD(String sNODCAD) {
		this.sNODCAD = sNODCAD;
	}

	public String getsNUCCEN() {
		return sNUCCEN;
	}

	public void setsNUCCEN(String sNUCCEN) {
		this.sNUCCEN = sNUCCEN;
	}

	public String getsNUCCOF() {
		return sNUCCOF;
	}

	public void setsNUCCOF(String sNUCCOF) {
		this.sNUCCOF = sNUCCOF;
	}

	public String getsNUCCDI() {
		return sNUCCDI;
	}

	public void setsNUCCDI(String sNUCCDI) {
		this.sNUCCDI = sNUCCDI;
	}

	public String getsNUCCNT() {
		return sNUCCNT;
	}

	public void setsNUCCNT(String sNUCCNT) {
		this.sNUCCNT = sNUCCNT;
	}

	public String getsOBTEXC() {
		return sOBTEXC;
	}

	public void setsOBTEXC(String sOBTEXC) {
		this.sOBTEXC = sOBTEXC;
	}




	
	
	
}
