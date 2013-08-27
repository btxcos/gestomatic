package com.provisiones.pl;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.ll.CLComunidades;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.Comunidad;
import com.provisiones.types.MovimientoComunidad;

public class GestorTablaComunidadActivo implements Serializable 
{

	private static final long serialVersionUID = 3791951733627793686L;
	
	static String sClassName = GestorTablaComunidadActivo.class.getName();
	
	private String sCOACES = "";
	private String sCOPOIN = "";
	private String sNOMUIN = "";
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	
	private String sCOCLDO = "";
	private String sNUDCOM = "";
	private String sNOMCOC = "";
	private String sNODCCO = "";
	
	public GestorTablaComunidadActivo()
	{
		Utils.standardIO2File("");//Salida por fichero de texto
	}
	


	private ActivoTabla activoseleccionadoalta = null;
	private ActivoTabla activoseleccionadobaja = null;
	
	private ArrayList<ActivoTabla> tablaactivos = null;

	private ArrayList<ActivoTabla> tablaactivoscomunidad = null;
	
	public void buscaActivos (ActionEvent actionEvent)
	{
		
		String sMethod = "buscaActivos";
		
		
		FacesMessage msg;
		
		ActivoTabla buscaactivos = new ActivoTabla(
				sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
				sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
				sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(), "");
		
		Utils.debugTrace(true, sClassName, sMethod, "Buscando Activos...");
		
		this.setTablaactivos(CLComunidades.buscarActivosSinComunidad(buscaactivos));
		
		Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+getTablaactivos().size()+" activos relacionados.");

		msg = new FacesMessage("Encontrados "+getTablaactivos().size()+" activos relacionados.");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}

	public void cargarComunidad(ActionEvent actionEvent)
	{
		String sMethod = "cargarComunidad";
		
		FacesMessage msg;
		
		String sMsg = "";
		
		Comunidad comunidad = CLComunidades.consultaComunidad(sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase());
		
		this.sCOCLDO = comunidad.getCOCLDO();
		this.sNUDCOM = comunidad.getNUDCOM();
		this.sNOMCOC = comunidad.getNOMCOC();
		this.sNODCCO = comunidad.getNODCCO();
	
	
	
		if (comunidad.getNUDCOM().equals(""))
		{
			sMsg = "ERROR: Los datos suministrados no corresponden a ninguna comunidad registrada. Por favor, revise los datos."; 
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,sMsg,null);
		}
		else
		{
		
			this.setTablaactivoscomunidad(CLComunidades.buscarActivosComunidad(sCOCLDO, sNUDCOM));
		
			sMsg = "La comunidad '"+sNUDCOM.toUpperCase()+"' se ha cargado correctamente.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(sMsg,null);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
			sMsg = "Encontrados "+getTablaactivoscomunidad().size()+" activos relacionados.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(sMsg,null);
		}

		FacesContext.getCurrentInstance().addMessage(null, msg);
				
	}

	public void seleccionarActivo(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "seleccionarActivo";

    	FacesMessage msg;
    	
    	
    	
    	//this.sCOACESBuscado = activoseleccionado.getCOACES();
    	
    	this.sCOACES  = activoseleccionadoalta.getCOACES();
    	
    	msg = new FacesMessage("Activo "+ sCOACES +" Seleccionado.");
    	
    	Utils.debugTrace(true, sClassName, sMethod, "Activo seleccionado: |"+sCOACES+"|");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		//return "listacomunidadesactivos.xhtml";
    }

	public FacesMessage nuevoMovimiento(String sCodCOACCI)
	{
		String sMethod = "nuevoMovimiento";

		FacesMessage msg;
		
    	MovimientoComunidad movimiento = new MovimientoComunidad (
    			ValoresDefecto.DEF_E1_CODTRN, 
    			ValoresDefecto.DEF_COTDOR, 
    			ValoresDefecto.DEF_IDPROV, 
    			sCodCOACCI, 
    			ValoresDefecto.DEF_COENGP, 
    			sCOCLDO.toUpperCase(), 
    			sNUDCOM.toUpperCase(), 
    			"S", 
    			sCOACES.toUpperCase(), 
    			"#", "", 
    			"#", "", 
    			"#", "", 
    			"#", "", 
    			"#", "", 
    			"#", "", 
    			"#", "", 
    			"#", "", "", "", "", 
    			"#", "", 
    			"");
    	

		
		int iSalida = CLComunidades.registraMovimiento(movimiento);
		
		String sMsg = "";
		
		switch (iSalida) 
		{
		case 0: //Sin errores
			sMsg = "El cambio realizado correctamente.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(sMsg,null);
			if (sCodCOACCI.equals("X"))
			{
				tablaactivoscomunidad.add(activoseleccionadoalta);
			}
			else if (sCodCOACCI.equals("E"))
			{
				tablaactivoscomunidad.remove(activoseleccionadobaja);
			}
			break;

		case -1: //error 001 - CODIGO DE ACCION DEBE SER A,M,B,X 
			sMsg = "ERROR:001 - No se ha elegido una acccion correcta. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -3: //error 003 - NO EXISTE EL ACTIVO
			sMsg = "ERROR:003 - El activo elegido no esta registrado en el sistema. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -4: //Error 004 - CIF DE LA COMUNIDAD NO PUEDE SER BLANCO, NULO O CEROS
			sMsg = "ERROR:004 - No se ha informado el numero de documento. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -5: //Error 005 - NO TIENE NOMBRE LA COMUNIDAD
			sMsg = "ERROR:005 - El nombre de la comunidad es obligatorio. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -6: //Error 006 - FALTAN DATOS DE LA CUENTA BANCARIA
			sMsg = "ERROR:006 - No se han informado los datos de la cuenta corriente. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -8: //Error 008 - EL ACTIVO EXISTE EN OTRA COMUNIDAD
			sMsg = "ERROR:008 - El activo ya esta asociado a otra comunidad. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -9: //error 009 - YA EXISTE ESTA COMUNIDAD
			sMsg = "ERROR:009 - La comunidad ya se encuentra registrada. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -10: //error 010 - EL ACTIVO YA EXISTE PARA ESTA COMUNIDAD
			sMsg = "ERROR:010 - El activo ya esta asociado a esta comunidad. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -11: //error 011 - LA COMUNIDAD NO EXISTE. ACTIVO NO SE PUEDE DAR DE ALTA
			sMsg = "ERROR:011 - No se puede dar de alta el activo, la comunidad no existe. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -12: //error 012 - LA COMUNIDAD NO EXISTE. NO SE PUEDE MODIFICAR
			sMsg = "ERROR:012 - No se puede modificar la comunidad, no se encuentra registrada. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -26: //error 026 - LA COMUNIDAD NO EXISTE, NO SE PUEDE DAR DE BAJA
			sMsg = "ERROR:026 - No se puede dar de baja la comunidad, no se encuentra registrada. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -27: //error 027 - NO SE PUEDE DAR DE BAJA LA COMUNIDAD PORQUE TIENE CUOTAS
			sMsg = "ERROR:027 - No se puede dar de baja la comunidad, aun tiene cuotas de alta. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -30: //Error 030 - LA CLASE DE DOCUMENTO DEBE SER UN CIF (2,5,J)
			sMsg = "ERROR:030 - No se ha elegido un tipo de documento. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -31: //Error 031 - NUMERO DE DOCUMENTO CIF ERRONEO
			sMsg = "ERROR:031 - El numero de documento es incorrecto. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			

		case -701: //Error 701 - datos de cuenta incorrectos
			sMsg = "ERROR:701 - Los datos de la cuenta corriente son incorrectos. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			

		case -702: //Error 702 - direccion de correo de comunidad incorrecta
			sMsg = "ERROR:702 - La direccion de correo de la comunidad es incorrecto. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			

		case -703: //Error 703 - direccion de correo del administrador incorrecta
			sMsg = "ERROR:703 - La direccion de correo del adminsitrador es incorrecto. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -801: //Error 801 - alta de una comunidad en alta
			sMsg = "ERROR:801 - La comunidad ya esta dada de alta. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -802: //Error 802 - comunidad de baja no puede recibir mas movimientos
			sMsg = "ERROR:802 - La comunidad esta baja y no puede recibir mas movimientos. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -803: //Error 803 - estado no disponible
			sMsg = "ERROR:803 - El estado de la comunidad informada no esta disponible. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -804: //Error 804 - modificacion sin cambios
			sMsg = "ERROR:804 - No hay modificaciones que realizar. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -900: //Error 900 - al crear un movimiento
			sMsg = "ERROR:900 - Se ha producido un error al registrar el movimiento. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -901: //Error 901 - error y rollback - error al crear la comuidad
			sMsg = "ERROR:901 - Se ha producido un error al registrar la comunidad. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;
			
		case -902: //Error 902 - error y rollback - error al registrar la relaccion
			sMsg = "ERROR:902 - Se ha producido un error al registrar la relacion. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -903: //Error 903 - error y rollback - error al registrar el activo durante el alta
			sMsg = "ERROR:903 - Se ha producido un error al asociar el activo durante el alta. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -904: //Error 904 - error y rollback - error al cambiar el estado
			sMsg = "ERROR:904 - Se ha producido un error al cambiar el estado de la comunidad. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -905: //Error 905 - error y rollback - error al modificar la comunidad
			sMsg = "ERROR:905 - Se ha producido un error al modificar la comunidad. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -906: //Error 906 - error y rollback - el activo ya esta vinculado
			sMsg = "ERROR:906 - El activo ya ha sido vinculado. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -907: //Error 907 - error y rollback - error al asociar el activo en la comunidad
			sMsg = "ERROR:907 - Se ha producido un error al asociar el activo a la comunidad. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -908: //Error 908 - error y rollback - el activo no esta vinculado
			sMsg = "ERROR:908 - El activo no ha sido vinculado. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -909: //Error 909 - error y rollback - error al desasociar el activo en la comunidad
			sMsg = "ERROR:909 - Se ha producido un error al desasociar el activo a la comunidad. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;
			
		default: //error generico
			sMsg = "ERROR:"+iSalida+" - La operacion solicitada ha producido un error desconocido. Por favor, revise los datos."; 
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;
		}
		
		
		Utils.debugTrace(true, sClassName, sMethod, "Finalizadas las comprobaciones.");
		
		return msg;
	}
	
    public void altaActivoComunidad(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "altaActivoComunidad";

    	FacesMessage msg;

    	//this.sCOACESBuscado = activoseleccionado.getCOACES();
    	
    	
    	//comprobar el activo
    	String sMsg = "";
		
		int iSalida = CLComunidades.comprobarActivo(sCOACES.toUpperCase());

		switch (iSalida) 
		{
			case 0: //Sin errores
				msg = nuevoMovimiento("X");
				Utils.debugTrace(true, sClassName, sMethod, "Activo dado de alta:|"+sCOACES+"|");
		    	this.sCOACES  = "";
				break;

			case -1: //error - ya vinculado
				sMsg = "ERROR: El activo '"+sCOACES.toUpperCase()+"' ya esta vinculado a otra comunidada. Por favor, revise los datos."; 
				Utils.debugTrace(true, sClassName, sMethod, sMsg);
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,sMsg,null);
				break;

			case -2: //error - no existe
				sMsg = "ERROR: El activo '"+sCOACES.toUpperCase()+"' no se encuentra registrado en el sistema. Por favor, revise los datos.";
				Utils.debugTrace(true, sClassName, sMethod, sMsg);
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,sMsg,null);
				break;

			default: //error generico
				sMsg = "ERROR: El activo '"+sCOACES.toUpperCase()+"' ha producido un error desconocido. Por favor, revise los datos.";
				Utils.debugTrace(true, sClassName, sMethod, sMsg);
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,sMsg,null);
				break;
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);

		
		//return "listacomunidadesactivos.xhtml";
    }
    
    public void bajaActivoComunidad(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "bajaActivoComunidad";

    	FacesMessage msg;

    	if (activoseleccionadobaja == null)
    	{
    		String sMsg = "ERROR: Error al dar de baja el activo, la seleccion ya no esta disponible.";
    		
    		Utils.debugTrace(true, sClassName, sMethod, sMsg);
    		
    		msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
    	}
    	else
    	{
    	
        	this.sCOACES  = activoseleccionadobaja.getCOACES();
        	
        	msg = nuevoMovimiento("E");
        	
        	Utils.debugTrace(true, sClassName, sMethod, "Activo de baja: |"+activoseleccionadobaja.getCOACES()+"|");
        	
    	}
    	
    	
    	this.sCOACES  = "";
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		//return "listacomunidadesactivos.xhtml";
    }

    
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";

    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
    	
    	this.activoseleccionadobaja = null;
    	this.tablaactivos = null;
   	
    }
    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	this.sCOCLDO = "";
    	this.sNUDCOM = "";
    	this.sNOMCOC = "";
    	this.sNODCCO = "";

    	this.sCOACES = "";

    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
    	
    	this.activoseleccionadoalta = null;
    	this.activoseleccionadobaja = null;
    	this.tablaactivos = null;
    	this.tablaactivoscomunidad = null;
   	
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
	public ArrayList<ActivoTabla> getTablaactivoscomunidad() {
		return tablaactivoscomunidad;
	}
	public void setTablaactivoscomunidad(
			ArrayList<ActivoTabla> tablaactivoscomunidad) {
		this.tablaactivoscomunidad = tablaactivoscomunidad;
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


	
	
	public ActivoTabla getActivoseleccionadoalta() {
		return activoseleccionadoalta;
	}

	public void setActivoseleccionadoalta(ActivoTabla activoseleccionadoalta) {
		this.activoseleccionadoalta = activoseleccionadoalta;
	}

	public ActivoTabla getActivoseleccionadobaja() {
		return activoseleccionadobaja;
	}

	public void setActivoseleccionadobaja(ActivoTabla activoseleccionadobaja) {
		this.activoseleccionadobaja = activoseleccionadobaja;
	}

	public ArrayList<ActivoTabla> getTablaactivos() {
		return tablaactivos;
	}

	public void setTablaactivos(ArrayList<ActivoTabla> tablaactivos) {
		this.tablaactivos = tablaactivos;
	}

}
