package com.provisiones.pl;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.ll.CLComunidades;
import com.provisiones.ll.CLCuotas;
import com.provisiones.ll.CLGastos;
import com.provisiones.ll.CLImpuestos;
import com.provisiones.ll.CLProvisiones;
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.types.Provision;
import com.provisiones.types.ProvisionTabla;

public class GestorEnvios implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2302669387183120830L;



	static String sClassName = GestorEnvios.class.getName();
	

	
	private String sNumComunidades = "0";
	private String sNumCuotas = "0";
	private String sNumReferencias = "0";
	private String sNumImpuestos = "0";
	private String sNumProvisiones = "0";
	private String sNumGastos = "0";

	public GestorEnvios()
	{
		Utils.standardIO2File("");//Salida por fichero de texto
	}

	public void cargarMovimientosPendientes(ActionEvent actionEvent)
	{
		String sMethod = "cargaMovimientosPendientes";
		
		FacesMessage msg;
    	
    	String sMsg = "";
    	
    	this.sNumComunidades  = Long.toString(CLComunidades.buscarNumeroMovimientosComunidadesPendientes());
    	this.sNumCuotas  = Long.toString(CLCuotas.buscarNumeroMovimientosCuotasPendientes());
    	this.sNumReferencias  = Long.toString(CLReferencias.buscarNumeroMovimientosReferenciasPendientes());
    	this.sNumImpuestos  = Long.toString(CLImpuestos.buscarNumeroMovimientosImpuestosPendientes());
    	this.sNumProvisiones  = Long.toString(CLProvisiones.buscarNumeroProvisionesCerradas());
    	this.sNumGastos  = Long.toString(CLGastos.buscarNumeroMovimientosGastosPendientes());

		
		sMsg = "Cargados todos los movimientos pendientes.";
		Utils.debugTrace(true, sClassName, sMethod, sMsg);
		msg = new FacesMessage(sMsg);

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void generarComunidades(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "generarComunidades";

    	FacesMessage msg;
    	
    	String sMsg = "";
    	
    	if (!sNumComunidades.equals("0"))
    	{
    		
    		//1- Generar fichero comuniadades
    		//2- Poner a enviado las comunidades pendientes
    		
    		sMsg = "Generado el fichero de comunidades a enviar.";
        	
    		msg = Utils.pfmsgTrace(true, sClassName, sMethod, sMsg);
    	}
    	else
    	{
    		sMsg = "No existen comunidades pendientes de enviar.";
    				
    		msg = Utils.pfmsgWarning(true, sClassName, sMethod, sMsg);
    	}
    	

		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
    }
	
/*	public void cargarProvision(ActionEvent actionEvent)
	{
		CLProvisiones.detallesProvision(sNUPROF);
	}*/
	

	
/*	public void cerrarProvision(ActionEvent actionEvent)
	{
		String sMethod = "cerrarProvision";
		
    	FacesMessage msg;
    	
    	String sMsg = "";
		
		Provision provision = CLProvisiones.detallesProvision(sNUPROF);
		
		provision.setsFEPFON(Utils.fechaDeHoy(false));
		
		provision.setsCodEstado("B");
		
		if (CLProvisiones.cerrarProvision(provision))
		{
			sMsg = "Provision '"+ sNUPROF +"' cerrada.";
			msg = new FacesMessage(sMsg);

		}
		else
		{
			sMsg = "ERROR: ha ocurrido un error al cerrar la provision.";
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,sMsg,null);
		}

    	
    	Utils.debugTrace(true, sClassName, sMethod, sMsg);
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}*/

	public String getsNumComunidades() {
		return sNumComunidades;
	}

	public void setsNumComunidades(String sNumComunidades) {
		this.sNumComunidades = sNumComunidades;
	}

	public String getsNumCuotas() {
		return sNumCuotas;
	}

	public void setsNumCuotas(String sNumCuotas) {
		this.sNumCuotas = sNumCuotas;
	}

	public String getsNumReferencias() {
		return sNumReferencias;
	}

	public void setsNumReferencias(String sNumReferencias) {
		this.sNumReferencias = sNumReferencias;
	}

	public String getsNumImpuestos() {
		return sNumImpuestos;
	}

	public void setsNumImpuestos(String sNumImpuestos) {
		this.sNumImpuestos = sNumImpuestos;
	}

	public String getsNumProvisiones() {
		return sNumProvisiones;
	}

	public void setsNumProvisiones(String sNumProvisiones) {
		this.sNumProvisiones = sNumProvisiones;
	}

	public String getsNumGastos() {
		return sNumGastos;
	}

	public void setsNumGastos(String sNumGastos) {
		this.sNumGastos = sNumGastos;
	}




}