package com.provisiones.pl.movimientos;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.ll.CLCuotas;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.CuotaTabla;
import com.provisiones.types.MovimientoCuota;

public class GestorMovimientosCuotas implements Serializable 
{

	private static final long serialVersionUID = 558593056565873600L;
	
	static String sClassName = GestorMovimientosCuotas.class.getName();

	private String sCODTRN = ValoresDefecto.DEF_E2_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOACCI = "";
	private String sCOENGP = ValoresDefecto.DEF_COENGP;

	private String sCOACES = "";
	
	private String sCOCLDO = "";
	private String sDesCOCLDO = "";
	
	private String sNUDCOM = "";
	private String sNOMCOC = "";
	private String sNODCCO = "";
	

	private String sCOSBAC = "";
	private String sDesCOSBAC = "";
	private String sFIPAGO = "";
	private String sFFPAGO = "";
	private String sIMCUCO = "";
	private String sFAACTA = "";
	private String sPTPAGO = "";
	private String sDesPTPAGO = "";
	private String sOBTEXC = "";
	
	private String sOBDEER = "";
	
	private String sCOPOIN = "";
	private String sNOMUIN = "";
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	
	
	private transient ActivoTabla activoseleccionado = null;
	private transient ArrayList<ActivoTabla> tablaactivos = null;

	private transient CuotaTabla cuotaseleccionada = null;
	private transient ArrayList<CuotaTabla> tablacuotas = null;

	
	public GestorMovimientosCuotas()
	{
		Utils.standardIO2File("");//Salida por fichero de texto
	}
	
	public void borrarCamposActivo()
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

    	borrarCamposActivo();
    	borrarResultadosActivo();
    }

	public void borrarCamposCuota()
	{
		this.sCOCLDO = "";
		this.sDesCOCLDO = "";
		this.sNUDCOM = "";
		this.sCOSBAC = "";
		this.sDesCOSBAC = "";
		this.sFIPAGO = "";
		this.sFFPAGO = "";
		this.sIMCUCO = "";
		this.sFAACTA = "";
		this.sPTPAGO = "";
		this.sDesPTPAGO = "";
		this.sOBTEXC = "";
	}
	
	public void borrarResultadosCuota()
	{
    	this.cuotaseleccionada = null;
    	this.tablacuotas = null;
	}
	
	public void limpiarPlantillaCuotas(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";

    	borrarCamposCuota();
    	borrarResultadosCuota();
    }
    

    
	public void buscaActivos (ActionEvent actionEvent)
	{
		
		String sMethod = "buscaActivos";
		
		
		FacesMessage msg;
		
		ActivoTabla buscaactivos = new ActivoTabla(
				sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
				sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
				sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(), "");
		
		this.setTablaactivos(CLCuotas.buscarActivosConCuotas(buscaactivos));
		
		msg = Utils.pfmsgTrace(true, sClassName, sMethod, "Encontrados "+getTablaactivos().size()+" activos relacionados.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	public void seleccionarActivo(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "seleccionarActivo";

    	FacesMessage msg;
    	
    	
    	this.sCOACES  = activoseleccionado.getCOACES();
    	
    	msg = Utils.pfmsgTrace(true, sClassName, sMethod, "Activo '"+ sCOACES +"' Seleccionado.");
    	
		FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void cargarCuotas(ActionEvent actionEvent)
	{
		String sMethod = "cargarCuotas";
		
		FacesMessage msg;
		
		this.tablacuotas = CLCuotas.buscarCuotasActivo(sCOACES.toUpperCase());
		
		msg = Utils.pfmsgTrace(true, sClassName, sMethod, "Encontradas "+getTablacuotas().size()+" cuotas relacionadas.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
				
	}
	
	public void seleccionarCuota(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "seleccionarCuota";

    	FacesMessage msg;
    	
    	this.sCOCLDO = cuotaseleccionada.getCOCLDO(); 
    	this.sDesCOCLDO = cuotaseleccionada.getDCOCLDO();
    	this.sNUDCOM = cuotaseleccionada.getNUDCOM();
    	this.sCOSBAC = cuotaseleccionada.getCOSBAC();
    	this.sDesCOSBAC = cuotaseleccionada.getDCOSBAC();
    	this.sFIPAGO = cuotaseleccionada.getFIPAGO();
    	this.sFFPAGO = cuotaseleccionada.getFFPAGO();
    	this.sIMCUCO = cuotaseleccionada.getIMCUCO();
    	this.sFAACTA = cuotaseleccionada.getFAACTA();
    	this.sPTPAGO = cuotaseleccionada.getPTPAGO();
    	this.sDesPTPAGO = cuotaseleccionada.getDPTPAGO();
    	this.sOBTEXC = cuotaseleccionada.getOBTEXC();
    	
    	msg = Utils.pfmsgTrace(true, sClassName, sMethod, "Cuota de '"+ sDesCOSBAC +"' Seleccionada.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void hoyFIPAGO (ActionEvent actionEvent)
	{
		String sMethod = "hoyFIPAGO";
		this.setsFIPAGO(Utils.fechaDeHoy(true));
		Utils.debugTrace(true, sClassName, sMethod, "sFIPAGO:|"+sFIPAGO+"|");
	}

	public void hoyFFPAGO (ActionEvent actionEvent)
	{
		String sMethod = "hoyFFPAGO";
		this.setsFFPAGO(Utils.fechaDeHoy(true));
		Utils.debugTrace(true, sClassName, sMethod, "sFFPAGO:|"+sFFPAGO+"|");
	}
	
	public void hoyFAACTA (ActionEvent actionEvent)
	{
		String sMethod = "hoyFAACTA";
		this.setsFAACTA(Utils.fechaDeHoy(true));
		Utils.debugTrace(true, sClassName, sMethod, "sFAACTA:|"+sFAACTA+"|");
	}
	
	public void registraDatos(ActionEvent actionEvent)
	{
		String sMethod = "registraDatos";
		
		FacesMessage msg;
		
		if (!CLCuotas.existeCuota(sCOACES, sCOCLDO, sNUDCOM.toUpperCase(), sCOSBAC))
		{
			msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR: La cuota no esta dada de alta. Por favor, revise los datos.");
		}
		else
		{
			MovimientoCuota movimiento = new MovimientoCuota (
					sCODTRN.toUpperCase(), 
					sCOTDOR.toUpperCase(), 
					sIDPROV.toUpperCase(), 
					sCOACCI.toUpperCase(), 
					sCOCLDO.toUpperCase(), 
					sNUDCOM.toUpperCase(), 
					sCOENGP.toUpperCase(), 
					sCOACES.toUpperCase(), 
					ValoresDefecto.DEF_COGRUG_E2, 
					ValoresDefecto.DEF_COTACA_E2, 
					Utils.compruebaCodigoPago(false,sCOSBAC.toUpperCase()), 
					"", 
					Utils.compruebaFecha(sFIPAGO.toUpperCase()), 
					"", 
					Utils.compruebaFecha(sFFPAGO.toUpperCase()), 
					"", 
					Utils.compruebaImporte(sIMCUCO.toUpperCase()), 
					"", 
					Utils.compruebaFecha(sFAACTA.toUpperCase()), 
					"", 
					sPTPAGO.toUpperCase(),
					"", 
					sOBTEXC.toUpperCase(), 
					sOBDEER.toUpperCase());
			
			
			
			int iSalida = CLCuotas.registraMovimiento(movimiento);
			
			switch (iSalida) 
			{
			case 0: //Sin errores
				msg = Utils.pfmsgTrace(true, sClassName, sMethod, "El movimiento se ha registrado correctamente.");
				break;

			case -1: //Error 001 - CODIGO DE ACCION DEBE SER A,M o B
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:001 - No se ha elegido una acccion correcta. Por favor, revise los datos.");
				break;

			case -3: //Error 003 - NO EXISTE EL ACTIVO
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:003 - El activo elegido no esta registrado en el sistema. Por favor, revise los datos.");
				break;


			case -4: //Error 004 - CIF DE LA COMUNIDAD NO PUEDE SER BLANCO O NULO
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:004 - No se ha informado el numero de documento. Por favor, revise los datos.");
				break;

			case -32: //Error 032 - EL SUBTIPO DE ACCION NO EXISTE
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:032 - El concepto de pago es obligatorio. Por favor, revise los datos.");
				break;

			case -33: //Error 033 - LA FECHA DE PRIMER PAGO DEBE SER LOGICA Y OBLIGATORIA
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:033 - La fecha del primer pago es obligatoria. Por favor, revise los datos.");
				break;


			case -34: //Error 034 - LA FECHA DE ULTIMO PAGO DEBE SER LOGICA Y OBLIGATORIA
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:034 - La fecha del ultimo pago es obligatoria. Por favor, revise los datos.");
				break;


			case -35: //Error 035 - LA FECHA DE ULTIMO PAGO NO DEBE DE SER MENOR QUE LA FECHA DE PRIMER PAGO
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:35 - La fecha del ultimo pago no puede ser menor que la del primero.");
				break;


			case -36: //Error 036 - IMPORTE DE CUOTA TIENE QUE SER MAYOR DE CERO
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:036 - El importe de la cuota tiene ser mayor que cero. Por favor, revise los datos.");
				break;

			case -41: //Error 041 - LA COMUNIDAD NO EXISTE EN LA TABLA DE COMUNIDADES GMAE10
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:041 - La comunidad propocionada no esta registrada en el sistema. Por favor, revise los datos.");
				break;

			case -42: //Error 042 - LA RELACION ACTIVO-COMUNIDAD YA EXISTE EN GMAE12. NO SE PUEDE REALIZAR EL ALTA
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:042 - El activo proporcionado esta asociado a otra comunidad. Por favor, revise los datos.");
				break;

			case -43: //Error 043 - LA RELACION ACTIVO-COMUNIDAD NO EXISTE EN GMAE12. NO SE PUEDE REALIZAR LA MODIFICACION
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:043 - El activo prorcionado no pertenece a la comunidad. Por favor, revise los datos.");
				break;

			case -44: //Error 044 - NO EXISTE PERIOCIDAD DE PAGO
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:044 - La periodicidad de pago es obligatoria. Por favor, revise los datos.");
				break;

			case -45: //Error 045 - LA RELACION ACTIVO-COMUNIDAD NO EXISTE EN GMAE12. NO SE PUEDE REALIZAR LA BAJA
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:045 - El activo prorcionado no pertenece a la comunidad. Por favor, revise los datos.");
				break;
				
			case -46: //Error 046 - LA FECHA DEL ACTA DEBE SER LOGICA Y OBLIGATORIA 
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:046 - La fecha de acta es obligatoria. Por favor, revise los datos.");
				break;
				
			case -701: //Error 701 - error en importe
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:701 - El campo importe no se ha informado correctamente. Por favor, revise los datos.");
				break;

			case -702: //Error 702 - fecha de primer pago incorrecta
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:702 - La fecha del primer pago no se ha informado correctamente. Por favor, revise los datos.");
				break;
				
			case -703: //Error 703 - fecha de ultimo pago incorrecta
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:703 - La fecha del ultimo pago no se ha informado correctamente. Por favor, revise los datos.");
				break;
				
			case -704: //Error 704 - fecha de acta incorrecta
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:704 - La fecha de acta no se ha informado correctamente. Por favor, revise los datos.");
				break;
				
			case -801: //Error 801 - alta de una cuota en alta
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:801 - La cuota ya esta dada de alta. Por favor, revise los datos.");
				break;

			case -802: //Error 802 - cuota de baja no puede recibir movimientos
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:802 - La cuota esta baja y no puede recibir movimientos. Por favor, revise los datos.");
				break;
				
			case -803: //Error 803 - estado no disponible
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:803 - El estado de la cuota informada no esta disponible. Por favor, revise los datos.");
				break;

			case -804: //Error 804 - modificacion sin cambios
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:804 - No hay modificaciones que realizar. Por favor, revise los datos.");
				break;

			case -900: //Error 900 - al crear un movimiento
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:900 - Se ha producido un error al registrar el movimiento. Por favor, revise los datos.");
				break;

			case -901: //Error 901 - error y rollback - error al crear la cuota
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:901 - Se ha producido un error al registrar la cuota. Por favor, revise los datos.");
				break;
				
			case -902: //Error 902 - error y rollback - error al registrar la relaccion
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:902 - Se ha producido un error al registrar la relacion. Por favor, revise los datos.");
				break;

			case -903: //Error 903 - error y rollback - error al cambiar el estado
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:903 - Se ha producido un error al cambiar el estado de la cuota. Por favor, revise los datos.");
				break;

			case -904: //Error 904 - error y rollback - error al modificar la cuota
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:904 - Se ha producido un error al modificar la cuota. Por favor, revise los datos.");
				break;

			default: //error generico
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:"+iSalida+" - La operacion solicitada ha producido un error desconocido. Por favor, revise los datos.");
				break;
			}
		}
		

		
		
		Utils.debugTrace(true, sClassName, sMethod, "Finalizadas las comprobaciones.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

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

	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
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

	public String getsCOSBAC() {
		return sCOSBAC;
	}

	public void setsCOSBAC(String sCOSBAC) {
		this.sCOSBAC = sCOSBAC;
	}

	public String getsFIPAGO() {
		return sFIPAGO;
	}

	public void setsFIPAGO(String sFIPAGO) {
		this.sFIPAGO = sFIPAGO;
	}

	public String getsFFPAGO() {
		return sFFPAGO;
	}

	public void setsFFPAGO(String sFFPAGO) {
		this.sFFPAGO = sFFPAGO;
	}

	public String getsIMCUCO() {
		return sIMCUCO;
	}

	public void setsIMCUCO(String sIMCUCO) {
		this.sIMCUCO = sIMCUCO;
	}

	public String getsFAACTA() {
		return sFAACTA;
	}

	public void setsFAACTA(String sFAACTA) {
		this.sFAACTA = sFAACTA;
	}

	public String getsPTPAGO() {
		return sPTPAGO;
	}

	public void setsPTPAGO(String sPTPAGO) {
		this.sPTPAGO = sPTPAGO;
	}

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

	public ActivoTabla getActivoseleccionado() {
		return activoseleccionado;
	}

	public void setActivoseleccionado(ActivoTabla activoseleccionado) {
		this.activoseleccionado = activoseleccionado;
	}

	public ArrayList<ActivoTabla> getTablaactivos() {
		return tablaactivos;
	}

	public void setTablaactivos(ArrayList<ActivoTabla> tablaactivos) {
		this.tablaactivos = tablaactivos;
	}

	public CuotaTabla getCuotaseleccionada() {
		return cuotaseleccionada;
	}

	public void setCuotaseleccionada(CuotaTabla cuotaseleccionada) {
		this.cuotaseleccionada = cuotaseleccionada;
	}

	public ArrayList<CuotaTabla> getTablacuotas() {
		return tablacuotas;
	}

	public void setTablacuotas(ArrayList<CuotaTabla> tablacuotas) {
		this.tablacuotas = tablacuotas;
	}

	public String getsDesCOSBAC() {
		return sDesCOSBAC;
	}

	public void setsDesCOSBAC(String sDesCOSBAC) {
		this.sDesCOSBAC = sDesCOSBAC;
	}

	public String getsDesPTPAGO() {
		return sDesPTPAGO;
	}

	public void setsDesPTPAGO(String sDesPTPAGO) {
		this.sDesPTPAGO = sDesPTPAGO;
	}

	public String getsDesCOCLDO() {
		return sDesCOCLDO;
	}

	public void setsDesCOCLDO(String sDesCOCLDO) {
		this.sDesCOCLDO = sDesCOCLDO;
	}
	
	
	
}
