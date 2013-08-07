package com.provisiones.pl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.ll.CLComunidades;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Comunidad;
import com.provisiones.types.MovimientoComunidad;

public class GestorMovimientosComunidades implements Serializable 
{
	static String sClassName = GestorMovimientosComunidades.class.getName();

	private static final long serialVersionUID = -9157997142376942992L;

	private String sCODTRN = ValoresDefecto.DEF_E1_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOACCI = "";
	private String sCOENGP = ValoresDefecto.DEF_COENGP;
	private String sCOCLDO = "";
	private String sNUDCOM = "";
	//private String sBITC10 = "";
	private String sCOACES = "";
	//private String sBITC01 = "";
	private String sNOMCOC = "";
	//private String sBITC02 = "";
	private String sNODCCO = "";
	//private String sBITC03 = "";
	private String sNOMPRC = "";
	//private String sBITC04 = "";
	private String sNUTPRC = "";
	//private String sBITC05 = "";
	private String sNOMADC = "";
	//private String sBITC06 = "";
	private String sNUTADC = "";
	//private String sBITC07 = "";
	private String sNODCAD = "";
	//private String sBITC08 = "";
	private String sNUCCEN = "";
	private String sNUCCOF = "";
	private String sNUCCDI = "";
	private String sNUCCNT = "";
	//private String sBITC09 = "";
	private String sOBTEXC = "";
	private String sOBDEER = "";
	
	/*private String sCODTRN = "CODTRN";
	private String sCOTDOR = "COTDOR";
	private String sIDPROV = "IDPROV";
	private String sCOACCI = "COACCI";
	private String sCOENGP = "COENGP";
	private String sCOCLDO = "COCLDO";
	private String sNUDCOM = "NUDCOM";
	private String sBITC10 = "BITC10";
	private String sCOACES = "COACES";
	private String sBITC01 = "BITC01";
	private String sNOMCOC = "NOMCOC";
	private String sBITC02 = "BITC02";
	private String sNODCCO = "NODCCO";
	private String sBITC03 = "BITC03";
	private String sNOMPRC = "NOMPRC";
	private String sBITC04 = "BITC04";
	private String sNUTPRC = "NUTPRC";
	private String sBITC05 = "BITC05";
	private String sNOMADC = "NOMADC";
	private String sBITC06 = "BITC06";
	private String sNUTADC = "NUTADC";
	private String sBITC07 = "BITC07";
	private String sNODCAD = "NODCAD";
	private String sBITC08 = "BITC08";
	private String sNUCCEN = "NUCCEN";
	private String sNUCCOF = "NUCCOF";
	private String sNUCCDI = "NUCCDI";
	private String sNUCCNT = "NUCCNT";
	private String sBITC09 = "BITC09";
	private String sOBTEXC = "OBTEXC";
	private String sOBDEER = "OBDEER";*/

	private Map<String,String> tiposdocumentoHM = new LinkedHashMap<String, String>();
	
	public GestorMovimientosComunidades()
	{
		tiposdocumentoHM.put("C.I.F.",                     "2");
		tiposdocumentoHM.put("C.I.F país extranjero.",     "5");
		tiposdocumentoHM.put("Otros persona jurídica.",    "J");
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
	
	public void limpiarPlantilla(ActionEvent actionEvent)
	{
		borrarCampos();
		this.sCOACES = "";
	}

	public void comprobarCOACES(ActionEvent actionEvent)
	{
		String sMethod = "comprobarCOACES";
		
		Utils.standardIO2File("");//Salida por fichero de texto
		
		FacesMessage msg;
		
		int iSalida = CLComunidades.comprobarActivo(sCOACES.toUpperCase());

		switch (iSalida) 
		{
		case 0: //Sin errores
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "El activo '"+sCOACES.toUpperCase()+"' esta disponible.");
			msg = new FacesMessage("El activo '"+sCOACES.toUpperCase()+"' esta disponible.",null);
			break;
		case -1: //Sin errores
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "ERROR: El activo '"+sCOACES.toUpperCase()+"' ya se encuentra registrado en otra comunidad. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El activo '"+sCOACES.toUpperCase()+"' ya se encuentra registrado en otra comunidad. Por favor, revise los datos.",null);
			break;
		case -2: //Sin errores
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "ERROR: El activo '"+sCOACES.toUpperCase()+"' no se encuentra registrado aun. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El activo '"+sCOACES.toUpperCase()+"' no se encuentra registrado aun. Por favor, revise los datos.",null);
			break;
		default: //error generico
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "ERROR: El activo '"+sCOACES.toUpperCase()+"' ha producido un error desconocido. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El activo '"+sCOACES.toUpperCase()+"' ha producido un error desconocido. Por favor, revise los datos.",null);
			break;
		}
		
		
		FacesContext.getCurrentInstance().addMessage(null, msg);	
		
	}
	
	
	public void cargarComunidad(ActionEvent actionEvent)
	{
		String sMethod = "cargarComunidad";
		
		Utils.standardIO2File("");//Salida por fichero de texto
		
		Comunidad comunidad = CLComunidades.consultaComunidad(sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase());
		
		this.sCOCLDO = comunidad.getCOCLDO();
		this.sNUDCOM = comunidad.getNUDCOM();
		this.sNOMCOC = comunidad.getNOMCOC();
		this.sNODCCO = comunidad.getNODCCO();
		this.sNOMPRC = comunidad.getNOMPRC();
		this.sNUTPRC = comunidad.getNUTPRC();
		this.sNOMADC = comunidad.getNOMADC();
		this.sNUTADC = comunidad.getNUTADC();
		this.sNODCAD = comunidad.getNODCAD();
		this.sNUCCEN = comunidad.getNUCCEN();
		this.sNUCCOF = comunidad.getNUCCOF();
		this.sNUCCDI = comunidad.getNUCCDI();
		this.sNUCCNT = comunidad.getNUCCNT();
		this.sOBTEXC = comunidad.getOBTEXC();

		
		FacesMessage msg;
		
		if (comunidad.getNUDCOM().equals(""))
		{
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, La comunidad '"+sNUDCOM.toUpperCase()+"' no esta registrada en el sistema.",null);
			
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error: La comunidad '"+sNUDCOM.toUpperCase()+"' no esta registrada en el sistema.");
		}
		else
		{
			msg = new FacesMessage("La comunidad '"+sNUDCOM.toUpperCase()+"' se ha cargado correctamente.",null);
			
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "La comunidad '"+sNUDCOM.toUpperCase()+"' se ha cargado correctamente.");			
		}
		
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
				
	}
	
	public void registraMovimiento(ActionEvent actionEvent)
	{
		String sMethod = "registraMovimiento";
		
		Utils.standardIO2File("");//Salida por fichero de texto
		
		//MovimientoComunidad movimiento = new MovimientoComunidad (sCODTRN.toUpperCase(), sCOTDOR.toUpperCase(), sIDPROV.toUpperCase(), sCOACCI.toUpperCase(), sCOENGP.toUpperCase(), sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase(), sBITC10.toUpperCase(), sCOACES.toUpperCase(), sBITC01.toUpperCase(), sNOMCOC.toUpperCase(), sBITC02.toUpperCase(), sNODCCO.toUpperCase(), sBITC03.toUpperCase(), sNOMPRC.toUpperCase(), sBITC04.toUpperCase(), sNUTPRC.toUpperCase(), sBITC05.toUpperCase(), sNOMADC.toUpperCase(), sBITC06.toUpperCase(), sNUTADC.toUpperCase(), sBITC07.toUpperCase(), sNODCAD.toUpperCase(), sBITC08.toUpperCase(), sNUCCEN.toUpperCase(), sNUCCOF.toUpperCase(), sNUCCDI.toUpperCase(), sNUCCNT.toUpperCase(), sBITC09.toUpperCase(), sOBTEXC.toUpperCase(), sOBDEER.toUpperCase());
		MovimientoComunidad movimiento = new MovimientoComunidad (sCODTRN.toUpperCase(), sCOTDOR.toUpperCase(), sIDPROV.toUpperCase(), sCOACCI.toUpperCase(), sCOENGP.toUpperCase(), sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase(), "", sCOACES.toUpperCase(), "", sNOMCOC.toUpperCase(), "", sNODCCO.toUpperCase(), "", sNOMPRC.toUpperCase(), "", sNUTPRC.toUpperCase(), "", sNOMADC.toUpperCase(), "", sNUTADC.toUpperCase(), "", sNODCAD.toUpperCase(), "", sNUCCEN.toUpperCase(), sNUCCOF.toUpperCase(), sNUCCDI.toUpperCase(), sNUCCNT.toUpperCase(), "", sOBTEXC.toUpperCase(), sOBDEER.toUpperCase());
		
		FacesMessage msg;
		
		int iSalida = CLComunidades.registraMovimiento(movimiento);
		
		switch (iSalida) 
		{
		case 0: //Sin errores
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "El movimiento en la comunidad '"+ movimiento.getNOMCOC() + "', con documento '"+movimiento.getNUDCOM()+"', se ha creado correctamente.");
			msg = new FacesMessage("El movimiento en la comunidad '"+ movimiento.getNOMCOC() + "', con documento '"+movimiento.getNUDCOM()+"', se ha creado correctamente.");
			break;
		case -1: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al dar de alta la comunidad '"+ movimiento.getNOMCOC() + "', se ha realizado una accion no permitida. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al dar de alta la comunidad '"+ movimiento.getNOMCOC() + "', se ha realizado una accion no permitida. Por favor, revise los datos.",null);
			break;
		case -2: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al modificar la comunidad '"+ movimiento.getNOMCOC() + "', ya esta dada de alta. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al modificar la comunidad '"+ movimiento.getNOMCOC() + "', ya esta dada de alta. Por favor, revise los datos.",null);
			break;
		case -3: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al modificar un activo de la comunidad '"+ movimiento.getNOMCOC() + "', el activo esta vacio. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al modificar un activo de la comunidad '"+ movimiento.getNOMCOC() + "', el activo esta vacio. Por favor, revise los datos.",null);
			break;
		case -4: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "La comunidad '"+ movimiento.getNOMCOC() + "' fue dada de baja. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La comunidad '"+ movimiento.getNOMCOC() + "' fue dada de baja. Por favor, revise los datos.",null);
			break;
		case -5: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "No existe la comunidad con identificador '"+ movimiento.getNUDCOM() + "'. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe la comunidad con identificador '"+ sNUDCOM + "'. Por favor, revise los datos.",null);
			break;
		case -6: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "No puede darse de baja la comunidad '"+ movimiento.getNOMCOC() + "' por que tiene cuotas asignadas. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puede darse de baja la comunidad '"+ movimiento.getNOMCOC() + "' por que tiene cuotas asignadas. Por favor, revise los datos.",null);
			break;
		case -7: //Error
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al dar de alta un movimiento en la comunidad '"+ movimiento.getNOMCOC() + "'. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al dar de alta un movimiento en la comunidad '"+ movimiento.getNOMCOC() + "'. Por favor, revise los datos.",null);
			break;
		case -8: //Error COACCI = "";
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al dar de alta un movimiento. No se ha elegido una accion valida. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al dar de alta un movimiento. No se ha elegido una accion valida. Por favor, revise los datos.",null);
			break;//"Error al registrar el movimiento, no hay motivo del cambio. Por favor, revise los datos."
		case -9: //Error COACCI = "";
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al registrar el movimiento, no se ha producido cambios en la comunidad. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al registrar el movimiento, no se ha producido cambios en la comunidad. Por favor, revise los datos.",null);
			break;
		case -11: //Error con rollback
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al actualizar el estado de la comunidad '"+ movimiento.getNOMCOC() + "'. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar el estado de la comunidad '"+ movimiento.getNOMCOC() + "'. Por favor, revise los datos.",null);
			break;
		case -12: //Error con rollback
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Error al dar de alta la relaccion de movimientos para '"+ movimiento.getNOMCOC() + "'. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al dar de alta la relaccion de movimientos para '"+ movimiento.getNOMCOC() + "'. Por favor, revise los datos.",null);
			break;
		default: //error generico
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "ERROR: El cambio solicitado ha producido un error desconocido. Por favor, revise los datos.");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El cambio solicitado ha producido un error desconocido. Por favor, revise los datos.",null);
			break;
		}
		
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Finalizadas las comprobaciones.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

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
	/*public String getsBITC10() {
		return sBITC10;
	}
	public void setsBITC10(String sBITC10) {
		this.sBITC10 = sBITC10;
	}*/
	public String getsCOACES() {
		return sCOACES;
	}
	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
	}
	/*public String getsBITC01() {
		return sBITC01;
	}
	public void setsBITC01(String sBITC01) {
		this.sBITC01 = sBITC01;
	}*/
	public String getsNOMCOC() {
		return sNOMCOC;
	}
	public void setsNOMCOC(String sNOMCOC) {
		this.sNOMCOC = sNOMCOC;
	}
	/*public String getsBITC02() {
		return sBITC02;
	}
	public void setsBITC02(String sBITC02) {
		this.sBITC02 = sBITC02;
	}*/
	public String getsNODCCO() {
		return sNODCCO;
	}
	public void setsNODCCO(String sNODCCO) {
		this.sNODCCO = sNODCCO;
	}
	/*public String getsBITC03() {
		return sBITC03;
	}
	public void setsBITC03(String sBITC03) {
		this.sBITC03 = sBITC03;
	}*/
	public String getsNOMPRC() {
		return sNOMPRC;
	}
	public void setsNOMPRC(String sNOMPRC) {
		this.sNOMPRC = sNOMPRC;
	}
	/*public String getsBITC04() {
		return sBITC04;
	}
	public void setsBITC04(String sBITC04) {
		this.sBITC04 = sBITC04;
	}*/
	public String getsNUTPRC() {
		return sNUTPRC;
	}
	public void setsNUTPRC(String sNUTPRC) {
		this.sNUTPRC = sNUTPRC;
	}
	/*public String getsBITC05() {
		return sBITC05;
	}
	public void setsBITC05(String sBITC05) {
		this.sBITC05 = sBITC05;
	}*/
	public String getsNOMADC() {
		return sNOMADC;
	}
	public void setsNOMADC(String sNOMADC) {
		this.sNOMADC = sNOMADC;
	}
	/*public String getsBITC06() {
		return sBITC06;
	}
	public void setsBITC06(String sBITC06) {
		this.sBITC06 = sBITC06;
	}*/
	public String getsNUTADC() {
		return sNUTADC;
	}
	public void setsNUTADC(String sNUTADC) {
		this.sNUTADC = sNUTADC;
	}
	/*public String getsBITC07() {
		return sBITC07;
	}
	public void setsBITC07(String sBITC07) {
		this.sBITC07 = sBITC07;
	}*/
	public String getsNODCAD() {
		return sNODCAD;
	}
	public void setsNODCAD(String sNODCAD) {
		this.sNODCAD = sNODCAD;
	}
	/*public String getsBITC08() {
		return sBITC08;
	}
	public void setsBITC08(String sBITC08) {
		this.sBITC08 = sBITC08;
	}*/
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
	/*public String getsBITC09() {
		return sBITC09;
	}
	public void setsBITC09(String sBITC09) {
		this.sBITC09 = sBITC09;
	}*/
	public String getsOBTEXC() {
		return sOBTEXC;
	}
	public void setsOBTEXC(String sOBTEXC) {
		this.sOBTEXC = sOBTEXC;
	}
	public String getsOBDEER() {
		return sOBDEER;
	}
	public void setsOBDEER(String sOBDEER) {
		this.sOBDEER = sOBDEER;
	}


	public Map<String,String> getTiposdocumentoHM() {
		return tiposdocumentoHM;
	}


	public void setTiposdocumentoHM(Map<String,String> tiposdocumentoHM) {
		this.tiposdocumentoHM = tiposdocumentoHM;
	}
	
	
}
