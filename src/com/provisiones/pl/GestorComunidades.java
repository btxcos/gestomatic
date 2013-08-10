package com.provisiones.pl;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.ll.CLComunidades;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Comunidad;

public class GestorComunidades implements Serializable 
{

	static String sClassName = GestorComunidades.class.getName();
	
	private static final long serialVersionUID = 5043001456385781939L;
	
	private String sCODTRN = ValoresDefecto.DEF_E1_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOACCI = "A";
	private String sCOENGP = ValoresDefecto.DEF_COENGP;

	private String sCOCLDO = "";
	private String sNUDCOM = "";
	private String sNOMCOC = "";
	private String sNODCCO = "";
	private String sNOMPRC = "";
	private String sNUTPRC = "";
	private String sNOMADC = "";
	private String sNUTADC = "";
	private String sNODCAD = "";
	private String sNUCCEN = "";
	private String sNUCCOF = "";
	private String sNUCCDI = "";
	private String sNUCCNT = "";
	private String sOBTEXC = "";
	
		
	public GestorComunidades()
	{

		Utils.standardIO2File("");//Salida por fichero de texto
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

		
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Dando de alta la comunidad...");
		
		
		
		comunidad.pintaComunidad();
		
		int iSalida = CLComunidades.registraPrealta(comunidad);

		switch (iSalida) 
		{
		case 0: //Sin errores
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Hecho!");
			msg = new FacesMessage("La comunidad '"+ comunidad.getNOMCOC() + "', con documento '"+comunidad.getNUDCOM()+"', se ha creado correctamente.");
			borrarCampos();
			break;
		case -1: 
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear la comunidad '"+ comunidad.getNOMCOC() + "', con documento '"+comunidad.getNUDCOM()+"'. Por favor, revise los datos.",null);
			break;
		case -2: 
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear la comunidad, no se incluyeron los datos identificativos. Por favor, revise los datos.",null);
			break;
		default: 
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear la comunidad '"+ comunidad.getNOMCOC() + "',  se ha producido un error. Por favor, revise los datos.",null);
			break;
		}
			
		FacesContext.getCurrentInstance().addMessage(null, msg);
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


	public String getsCODTRN() {
		return sCODTRN;
	}


	public void setsCODTRN(String sCODTRN) {
		this.sCODTRN = sCODTRN;
	}


	public String getsCOTDOR() {
		return sCOTDOR;
	}


	public void setsCOTDOR(String sCOTDOR) {
		this.sCOTDOR = sCOTDOR;
	}


	public String getsIDPROV() {
		return sIDPROV;
	}


	public void setsIDPROV(String sIDPROV) {
		this.sIDPROV = sIDPROV;
	}


	public String getsCOACCI() {
		return sCOACCI;
	}


	public void setsCOACCI(String sCOACCI) {
		this.sCOACCI = sCOACCI;
	}


	public String getsCOENGP() {
		return sCOENGP;
	}


	public void setsCOENGP(String sCOENGP) {
		this.sCOENGP = sCOENGP;
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
