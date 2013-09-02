package com.provisiones.pl;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.ll.CLActivos;
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.MovimientoReferenciaCatastral;

public class GestorReferenciasCatastrales implements Serializable 
{

	static String sClassName = GestorReferenciasCatastrales.class.getName();
	
	private static final long serialVersionUID = 2458459163051982275L;
	
	private String sCODTRN = ValoresDefecto.DEF_E3_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOACCI = "A";
	private String sCOENGP = ValoresDefecto.DEF_COENGP;
	
	private String sNURCAT = "";
	private String sTIRCAT = "";
	private String sENEMIS = "";
	private String sCOTEXA = ValoresDefecto.DEF_COTEXA;
	private String sOBTEXC = "";
	
	private String sOBDEER = "";
	
	//Ampliacion de valor catastral
	private String sIMVSUE = "";
	private String sIMCATA = "";
	private String sFERECA = "";
	
	//Buscar activos
	private String sCOACES = "";

	private String sCOPOIN = "";
	private String sNOMUIN = "";	
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	
	private transient ArrayList<ActivoTabla> tablaactivos = null;
	
	private transient ActivoTabla activoseleccionado = null;
	
	
	public GestorReferenciasCatastrales()
	{
		Utils.standardIO2File("");//Salida por fichero de texto
	}

	public void borrarPlantillaActivo()
	{
    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
	}
	
	public void borrarResultadosActivo()
	{
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";

    	borrarPlantillaActivo();
    	
    	borrarResultadosActivo();
   	
    }
    
	public void borrarPlantillaReferencia()
	{
		this.sNURCAT = "";
        this.sTIRCAT = "";
        this.sENEMIS = "";
        this.sOBTEXC = "";
	}
    
    public void limpiarPlantillaReferencia(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";

    	borrarResultadosActivo();
    	
    	borrarPlantillaReferencia();

   	
    }
	
	public void buscaActivos (ActionEvent actionEvent)
	{
		
		String sMethod = "buscaActivosComunidad";
		
		
		FacesMessage msg;
		
		ActivoTabla buscaactivos = new ActivoTabla(
				sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
				sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
				sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(), "");
		
		msg = Utils.pfmsgTrace(true, sClassName, sMethod, "Buscando Activos...");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		this.setTablaactivos(CLReferencias.buscarActivosSinReferencias(buscaactivos));
		
		msg = Utils.pfmsgTrace(true, sClassName, sMethod, "Encontrados "+getTablaactivos().size()+" activos relacionados.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void seleccionarActivo(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "seleccionarActivo";

    	FacesMessage msg;

    	
    	this.sCOACES  = activoseleccionado.getCOACES();
    	this.sNURCAT  = CLReferencias.referenciaCatastralActivo(sCOACES);
    	
    	if (sNURCAT.equals(""))
    	{
    		msg = Utils.pfmsgWarning(true, sClassName, sMethod, "No existe un numero de referencia catastral asociado.");
    		FacesContext.getCurrentInstance().addMessage(null, msg);
    	}
    		
    	
    	msg = Utils.pfmsgTrace(true, sClassName, sMethod, "Activo '"+ sCOACES +"' Seleccionado.");
 		FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void comprobarActivo(ActionEvent actionEvent)
	{
		String sMethod = "comprobarCOACES";
		
		
		FacesMessage msg;
		
    	this.sNURCAT  = CLReferencias.referenciaCatastralActivo(sCOACES);
    	
    	if (sNURCAT.equals(""))
    	{
    		msg = Utils.pfmsgWarning(true, sClassName, sMethod, "No existe un numero de referencia catastral asociado.");
    		FacesContext.getCurrentInstance().addMessage(null, msg);
    		
    	}

		if (CLActivos.compruebaActivo(sCOACES))
		{
			msg = Utils.pfmsgTrace(true, sClassName, sMethod, "El activo '"+sCOACES.toUpperCase()+"' esta disponible.");
		}
		else
		{
			msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR: El activo '"+sCOACES.toUpperCase()+"' no esta registrado en el sistema. Por favor, revise los datos.");
		}
		
		
		FacesContext.getCurrentInstance().addMessage(null, msg);	
		
	}
	

    
	public void hoyFERECA (ActionEvent actionEvent)
	{
		String sMethod = "hoyFERECA";
		this.setsFERECA(Utils.fechaDeHoy(true));
		Utils.debugTrace(true, sClassName, sMethod, "sFERECA:|"+sFERECA+"|");
	}
		
	public void realizaAlta(ActionEvent actionEvent)
	{
		String sMethod = "realizaAlta";
		
		FacesMessage msg;
		
		if (CLReferencias.existeReferenciaCatastral(sNURCAT.toUpperCase()))
		{
			msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:049 - La referencia catastral propocionada ya esta registrada en el sistema. Por favor, revise los datos.");
		}
		else
		{
			MovimientoReferenciaCatastral movimiento = new MovimientoReferenciaCatastral (
					sCODTRN.toUpperCase(), 
					sCOTDOR.toUpperCase(), 
					sIDPROV.toUpperCase(), 
					"A",
					sCOENGP.toUpperCase(), 
					sCOACES.toUpperCase(), 
					sNURCAT.toUpperCase(),
					"", 
					sTIRCAT.toUpperCase(),
					"", 
					sENEMIS.toUpperCase(),
					sCOTEXA.toUpperCase(),
					"", 
					sOBTEXC.toUpperCase(), 
					sOBDEER.toUpperCase(),
					"", 
					Utils.compruebaImporte(sIMVSUE.toUpperCase()),
					"", 
					Utils.compruebaImporte(sIMCATA.toUpperCase()),
					"", 
					Utils.compruebaFecha(sFERECA.toUpperCase()));
			
			int iSalida = CLReferencias.registraMovimiento(movimiento);
			
			switch (iSalida) 
			{
			case 0: //Sin errores
				msg = Utils.pfmsgTrace(true, sClassName, sMethod, "La referencia catastral se ha creado correctamente.");
				break;

			case -1: //Error 001 - CODIGO DE ACCION DEBE SER A,M o B
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:001 - No se ha elegido una acccion correcta. Por favor, revise los datos.");
				break;

			case -3: //Error 003 - NO EXISTE EL ACTIVO
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:003 - El activo elegido no esta registrado en el sistema. Por favor, revise los datos.");
				break;

			/*case -49: //Error 049 - LA REFERENCIA CATASTRAL YA EXISTE NO SE PUEDE DAR DE ALTA
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:049 - La referencia catastral propocionada ya esta registrada en el sistema. Por favor, revise los datos.");
				break;*/

			case -50: //Error 050 - LA REFERENCIA CATASTRAL NO EXISTE NO SE PUEDE MODIFICAR
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:050 - La referencia catastral propocionada no esta registrada en el sistema. Por favor, revise los datos.");
				break;			

			case -51: //Error 051 - LA REFERENCIA CATASTRAL NO EXISTE NO SE PUEDE DAR DE BAJA
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:051 - La referencia catastral propocionada no esta registrada en el sistema. Por favor, revise los datos.");
				break;

			case -52: //Error 052 - TITULAR CATASTRAL OBLIGATORIO. NO SE PUEDE DAR DE ALTA
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:052 - El titular catastral es obligatorio. Por favor, revise los datos.");
				break;
				
			case -53: //Error 053 - EXISTEN DATOS EN GMAE57. NO SE PUEDE REALIZAR LA BAJA
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:053 - Existen recursos o impuestos pendientes de esta referencia. Por favor, revise los datos.");
				break;
				
			case -54: //Error 054 - LA REFERENCIA CATASTRAL ES OBLIGATORIA
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:054 - La referencia catastral es obligatoria. Por favor, revise los datos.");
				break;
				
			//Ampliacion de valor catastral
			case -82: //Error 082 - EL VALOR DEL SUELO TIENE QUE SER MAYOR DE CERO
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:082 - El valor del suelo debe de ser mayor que 0. Por favor, revise los datos.");
				break;
				
			case -83: //Error 083 - EL VALOR CATASTRAL TIENE QUE SER MAYOR DE CERO
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:083 - El valor catastral debe de ser mayor que 0. Por favor, revise los datos.");
				break;

			case -85: //Error 085 - FECHA REVISION DEL VALOR CATASTRAL NO TRAE UN VALOR LOGICO
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:085 - La fecha de revision del valor catastral no esta bien informada. Por favor, revise los datos.");
				break;
				
			case -700: //Error 700 - No existe realcion con ese activo
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:700 - El activo suministrado no esta relacionado con la referencia catastral informada. Por favor, revise los datos.");
				break;
				
			case -701: //Error 701 - Valor del suelo incorrecto
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:701 - El valor del suelo no esta correctamente informado. Por favor, revise los datos.");
				break;
				
			case -702: //Error 702 - Valor catastral incorrecto
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:702 - El valor catastral no esta correctamente informado. Por favor, revise los datos.");
				break;
				
			case -801: //Error 801 - alta de una referencia en alta
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:801 - La referencia ya esta dada de alta. Por favor, revise los datos.");
				break;

			case -802: //Error 802 - referencia catastral de baja no puede recibir mas movimientos
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:802 - La referencia catastral esta baja y no puede recibir mas movimientos. Por favor, revise los datos.");
				break;
				
			case -803: //Error 803 - estado no disponible
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:803 - El estado de la referencia catastral informada no esta disponible. Por favor, revise los datos.");
				break;

			case -804: //Error 804 - modificacion sin cambios
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:804 - No hay modificaciones que realizar. Por favor, revise los datos.");
				break;

			case -900: //Error 900 - al crear un movimiento
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:900 - Se ha producido un error al registrar el movimiento. Por favor, revise los datos.");
				break;

			case -901: //Error 901 - error y rollback - error al crear la cuota
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:901 - Se ha producido un error al registrar la referencia catastral. Por favor, revise los datos.");
				break;
				
			case -902: //Error 902 - error y rollback - error al registrar la relaccion
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:902 - Se ha producido un error al registrar la relacion. Por favor, revise los datos.");
				break;

			case -903: //Error 903 - error y rollback - error al cambiar el estado
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:903 - Se ha producido un error al cambiar el estado de la referencia catastral. Por favor, revise los datos.");
				break;

			case -904: //Error 904 - error y rollback - error al modificar la cuota
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:904 - Se ha producido un error al modificar la referencia catastral. Por favor, revise los datos.");
				break;

			default: //error generico
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:"+iSalida+" - La operacion solicitada ha producido un error desconocido. Por favor, revise los datos.");
				break;
			}
		}
		

		
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Finalizadas las comprobaciones.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
	
	public String getsNURCAT() {
		return sNURCAT;
	}
	public void setsNURCAT(String sNURCAT) {
		this.sNURCAT = sNURCAT;
	}
	public String getsTIRCAT() {
		return sTIRCAT;
	}
	public void setsTIRCAT(String sTIRCAT) {
		this.sTIRCAT = sTIRCAT;
	}
	public String getsENEMIS() {
		return sENEMIS;
	}
	public void setsENEMIS(String sENEMIS) {
		this.sENEMIS = sENEMIS;
	}
	public String getsCOTEXA() {
		return sCOTEXA;
	}
	public void setsCOTEXA(String sCOTEXA) {
		this.sCOTEXA = sCOTEXA;
	}
	public String getsOBTEXC() {
		return sOBTEXC;
	}
	public void setsOBTEXC(String sOBTEXC) {
		this.sOBTEXC = sOBTEXC;
	}

	public ArrayList<ActivoTabla> getTablaactivos() {
		return tablaactivos;
	}

	public void setTablaactivos(ArrayList<ActivoTabla> tablaactivos) {
		this.tablaactivos = tablaactivos;
	}

	public ActivoTabla getActivoseleccionado() {
		return activoseleccionado;
	}

	public void setActivoseleccionado(ActivoTabla activoseleccionado) {
		this.activoseleccionado = activoseleccionado;
	}

	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
	}

	public String getsCOPOIN() {
		return sCOPOIN;
	}

	public void setsCOPOIN(String sCOPOIN) {
		this.sCOPOIN = sCOPOIN;
	}

	public String getsNOMUIN() {
		return sNOMUIN;
	}

	public void setsNOMUIN(String sNOMUIN) {
		this.sNOMUIN = sNOMUIN;
	}

	public String getsNOPRAC() {
		return sNOPRAC;
	}

	public void setsNOPRAC(String sNOPRAC) {
		this.sNOPRAC = sNOPRAC;
	}

	public String getsNOVIAS() {
		return sNOVIAS;
	}

	public void setsNOVIAS(String sNOVIAS) {
		this.sNOVIAS = sNOVIAS;
	}

	public String getsNUPIAC() {
		return sNUPIAC;
	}

	public void setsNUPIAC(String sNUPIAC) {
		this.sNUPIAC = sNUPIAC;
	}

	public String getsNUPOAC() {
		return sNUPOAC;
	}

	public void setsNUPOAC(String sNUPOAC) {
		this.sNUPOAC = sNUPOAC;
	}

	public String getsNUPUAC() {
		return sNUPUAC;
	}

	public void setsNUPUAC(String sNUPUAC) {
		this.sNUPUAC = sNUPUAC;
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

	public String getsOBDEER() {
		return sOBDEER;
	}

	public void setsOBDEER(String sOBDEER) {
		this.sOBDEER = sOBDEER;
	}

	public String getsIMVSUE() {
		return sIMVSUE;
	}

	public void setsIMVSUE(String sIMVSUE) {
		this.sIMVSUE = sIMVSUE;
	}

	public String getsIMCATA() {
		return sIMCATA;
	}

	public void setsIMCATA(String sIMCATA) {
		this.sIMCATA = sIMCATA;
	}

	public String getsFERECA() {
		return sFERECA;
	}

	public void setsFERECA(String sFERECA) {
		this.sFERECA = sFERECA;
	}
}
