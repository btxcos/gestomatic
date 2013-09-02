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
	


	private transient ActivoTabla activoseleccionadoalta = null;
	
	private transient ActivoTabla activoseleccionadobaja = null;
	
	private transient ArrayList<ActivoTabla> tablaactivos = null;

	private transient ArrayList<ActivoTabla> tablaactivoscomunidad = null;

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
    	this.activoseleccionadoalta = null;
    	this.activoseleccionadobaja = null;
    	this.tablaactivos = null;
    	this.tablaactivoscomunidad = null;
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";

    	borrarCamposActivo();
    	
    	borrarResultadosActivo();
    }
    
	public void borrarCamposComunidad()
	{
    	this.sCOCLDO = "";
    	this.sNUDCOM = "";
    	this.sNOMCOC = "";
    	this.sNODCCO = "";

    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
	}
	
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
		this.sCOACES = "";

		borrarResultadosActivo();
		borrarCamposComunidad();
    }
	
	public void buscaActivos (ActionEvent actionEvent)
	{
		
		String sMethod = "buscaActivos";
		
		
		FacesMessage msg;
		
		ActivoTabla buscaactivos = new ActivoTabla(
				sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
				sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
				sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(), "");
		
		msg = Utils.pfmsgTrace(true, sClassName, sMethod, "Buscando Activos...");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		this.setTablaactivos(CLComunidades.buscarActivosSinComunidad(buscaactivos));
		
		msg = Utils.pfmsgTrace(true, sClassName, sMethod, "Encontrados "+getTablaactivos().size()+" activos relacionados.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}

	public void seleccionarActivo(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "seleccionarActivo";

    	FacesMessage msg;

    	
    	this.sCOACES  = activoseleccionadoalta.getCOACES();
    	
    	msg = Utils.pfmsgTrace(true, sClassName, sMethod, "Activo '"+ sCOACES +"' Seleccionado.");
    	
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
    }
	
	public void cargarComunidad(ActionEvent actionEvent)
	{
		String sMethod = "cargarComunidad";
		
		FacesMessage msg;
		
		Comunidad comunidad = CLComunidades.consultaComunidad(sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase());
		
		this.sCOCLDO = comunidad.getCOCLDO();
		this.sNUDCOM = comunidad.getNUDCOM();
		this.sNOMCOC = comunidad.getNOMCOC();
		this.sNODCCO = comunidad.getNODCCO();
	
	
	
		if (comunidad.getNUDCOM().equals(""))
		{
			msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR: Los datos suministrados no corresponden a ninguna comunidad registrada. Por favor, revise los datos."); 
		}
		else
		{
		
			this.setTablaactivoscomunidad(CLComunidades.buscarActivosComunidad(sCOCLDO, sNUDCOM));
		
			msg = Utils.pfmsgTrace(true, sClassName, sMethod, "La comunidad '"+sNUDCOM.toUpperCase()+"' se ha cargado correctamente.");
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
			msg = Utils.pfmsgTrace(true, sClassName, sMethod, "Encontrados "+getTablaactivoscomunidad().size()+" activos relacionados.");
		}

		FacesContext.getCurrentInstance().addMessage(null, msg);
				
	}

	public FacesMessage nuevoMovimiento(String sCodCOACCI)
	{
		String sMethod = "nuevoMovimiento";

		FacesMessage msg;
		
		if (!CLComunidades.existeComunidad(sCOCLDO, sNUDCOM.toUpperCase()))
		{
			msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:011 - No se puede operar sobre el activo, la comunidad no existe. Por favor, revise los datos.");
		}
		else
		{
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
			
			switch (iSalida) 
			{
			case 0: //Sin errores
				msg = Utils.pfmsgTrace(true, sClassName, sMethod, "Cambio realizado correctamente.");
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
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:001 - No se ha elegido una acccion correcta. Por favor, revise los datos.");
				break;

			case -3: //error 003 - NO EXISTE EL ACTIVO
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:003 - El activo elegido no esta registrado en el sistema. Por favor, revise los datos.");
				break;


			case -4: //Error 004 - CIF DE LA COMUNIDAD NO PUEDE SER BLANCO, NULO O CEROS
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:004 - No se ha informado el numero de documento. Por favor, revise los datos.");
				break;

			case -5: //Error 005 - NO TIENE NOMBRE LA COMUNIDAD
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:005 - El nombre de la comunidad es obligatorio. Por favor, revise los datos.");
				break;

			case -6: //Error 006 - FALTAN DATOS DE LA CUENTA BANCARIA
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:006 - No se han informado los datos de la cuenta corriente. Por favor, revise los datos.");
				break;

			case -8: //Error 008 - EL ACTIVO EXISTE EN OTRA COMUNIDAD
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:008 - El activo ya esta asociado a otra comunidad. Por favor, revise los datos.");
				break;


			case -9: //error 009 - YA EXISTE ESTA COMUNIDAD
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:009 - La comunidad ya se encuentra registrada. Por favor, revise los datos.");
				break;


			case -10: //error 010 - EL ACTIVO YA EXISTE PARA ESTA COMUNIDAD
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:010 - El activo ya esta asociado a esta comunidad. Por favor, revise los datos.");
				break;


			/*case -11: //error 011 - LA COMUNIDAD NO EXISTE. ACTIVO NO SE PUEDE DAR DE ALTA
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:011 - No se puede dar de alta el activo, la comunidad no existe. Por favor, revise los datos.");
				break;*/


			case -12: //error 012 - LA COMUNIDAD NO EXISTE. NO SE PUEDE MODIFICAR
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:012 - No se puede modificar la comunidad, no se encuentra registrada. Por favor, revise los datos.");
				break;


			case -26: //error 026 - LA COMUNIDAD NO EXISTE, NO SE PUEDE DAR DE BAJA
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:026 - No se puede dar de baja la comunidad, no se encuentra registrada. Por favor, revise los datos.");
				break;

			case -27: //error 027 - NO SE PUEDE DAR DE BAJA LA COMUNIDAD PORQUE TIENE CUOTAS
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:027 - No se puede dar de baja la comunidad, aun tiene cuotas de alta. Por favor, revise los datos.");
				break;

			case -30: //Error 030 - LA CLASE DE DOCUMENTO DEBE SER UN CIF (2,5,J)
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:030 - No se ha elegido un tipo de documento. Por favor, revise los datos.");
				break;
				
			case -31: //Error 031 - NUMERO DE DOCUMENTO CIF ERRONEO
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:031 - El numero de documento es incorrecto. Por favor, revise los datos.");
				break;
				
			case -701: //Error 701 - datos de cuenta incorrectos
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:701 - Los datos de la cuenta corriente son incorrectos. Por favor, revise los datos.");
				break;
				

			case -702: //Error 702 - direccion de correo de comunidad incorrecta
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:702 - La direccion de correo de la comunidad es incorrecta. Por favor, revise los datos.");
				break;
				

			case -703: //Error 703 - direccion de correo del administrador incorrecta
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:703 - La direccion de correo del adminsitrador es incorrecta. Por favor, revise los datos.");
				break;

			case -801: //Error 801 - alta de una comunidad en alta
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:801 - La comunidad ya esta dada de alta. Por favor, revise los datos.");
				break;

			case -802: //Error 802 - comunidad de baja no puede recibir mas movimientos
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:802 - La comunidad esta baja y no puede recibir mas movimientos. Por favor, revise los datos.");
				break;
				
			case -803: //Error 803 - estado no disponible
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:803 - El estado de la comunidad informada no esta disponible. Por favor, revise los datos.");
				break;

			case -804: //Error 804 - modificacion sin cambios
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR:804 - No hay modificaciones que realizar. Por favor, revise los datos.");
				break;

			case -900: //Error 900 - al crear un movimiento
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:900 - Se ha producido un error al registrar el movimiento. Por favor, revise los datos.");
				break;

			case -901: //Error 901 - error y rollback - error al crear la comuidad
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:901 - Se ha producido un error al registrar la comunidad. Por favor, revise los datos.");
				break;
				
			case -902: //Error 902 - error y rollback - error al registrar la relaccion
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:902 - Se ha producido un error al registrar la relacion. Por favor, revise los datos.");
				break;

			case -903: //Error 903 - error y rollback - error al registrar el activo durante el alta
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:903 - Se ha producido un error al asociar el activo durante el alta. Por favor, revise los datos.");
				break;

			case -904: //Error 904 - error y rollback - error al cambiar el estado
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:904 - Se ha producido un error al cambiar el estado de la comunidad. Por favor, revise los datos.");
				break;

			case -905: //Error 905 - error y rollback - error al modificar la comunidad
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:905 - Se ha producido un error al modificar la comunidad. Por favor, revise los datos.");
				break;

			case -906: //Error 906 - error y rollback - el activo ya esta vinculado
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:906 - El activo ya ha sido vinculado. Por favor, revise los datos.");
				break;

			case -907: //Error 907 - error y rollback - error al asociar el activo en la comunidad
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:907 - Se ha producido un error al asociar el activo a la comunidad. Por favor, revise los datos.");
				break;

			case -908: //Error 908 - error y rollback - el activo no esta vinculado
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:908 - El activo no ha sido vinculado. Por favor, revise los datos.");
				break;

			case -909: //Error 909 - error y rollback - error al desasociar el activo en la comunidad
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:909 - Se ha producido un error al desasociar el activo a la comunidad. Por favor, revise los datos.");
				break;
				
			default: //error generico
				msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR:"+iSalida+" - La operacion solicitada ha producido un error desconocido. Por favor, revise los datos.");
				break;
			}		
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
		
		int iSalida = CLComunidades.comprobarActivo(sCOACES.toUpperCase());

		switch (iSalida) 
		{
			case 0: //Sin errores
				msg = nuevoMovimiento("X");
				Utils.debugTrace(true, sClassName, sMethod, "Activo dado de alta:|"+sCOACES+"|");
		    	this.sCOACES  = "";
				break;

			case -1: //error - ya vinculado
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR: El activo '"+sCOACES.toUpperCase()+"' ya esta vinculado a otra comunidada. Por favor, revise los datos.");
				break;

			case -2: //error - no existe
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR: El activo '"+sCOACES.toUpperCase()+"' no se encuentra registrado en el sistema. Por favor, revise los datos.");
				break;

			default: //error generico
				msg = Utils.pfmsgError(true, sClassName, sMethod, "ERROR: El activo '"+sCOACES.toUpperCase()+"' ha producido un error desconocido. Por favor, revise los datos.");
				break;
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	
    }
    
    public void bajaActivoComunidad(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "bajaActivoComunidad";

    	FacesMessage msg;

    	if (activoseleccionadobaja == null)
    	{
    		msg = Utils.pfmsgFatal(true, sClassName, sMethod, "ERROR: Error al dar de baja el activo, la seleccion ya no esta disponible.");
    	}
    	else
    	{
        	this.sCOACES  = activoseleccionadobaja.getCOACES();
        	
        	msg = nuevoMovimiento("E");
        	
        	Utils.debugTrace(true, sClassName, sMethod, "Activo de baja: |"+activoseleccionadobaja.getCOACES()+"|");
    	}
    	
    	
    	this.sCOACES  = "";
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
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
