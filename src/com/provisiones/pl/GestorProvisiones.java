package com.provisiones.pl;

import java.io.Serializable;
import java.util.Calendar;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.ll.CLProvisiones;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Provision;

public class GestorProvisiones implements Serializable 
{

	private static final long serialVersionUID = 6099738101025444086L;

	static String sClassName = GestorProvisiones.class.getName();
	
	private String sNUPROF = "";
	private String sValorTolal = "";
	private String sNumGastos = "";
	private String sFEPFON = "";
	private String sFechaValidacion = "";
	private String sValidado = "";

	private boolean bBloqueaAbrir = true;
	private boolean bBloqueaCerrar = true;

	
	public GestorProvisiones()
	{
		Utils.standardIO2File("");//Salida por fichero de texto
		//cargaProvisionAbierta();
	}

	public void cargaProvisionAbierta()
	{
		String sMethod = "cargaProvisionAbierta";
		
		FacesMessage msg;
    	
    	String sMsg = "";

		String sProvisionAbierta = CLProvisiones.ultimaProvisionAbierta(); 
		
		Utils.debugTrace(true, sClassName, sMethod, "Provision abierta:|"+sProvisionAbierta+"|");
		
		if (sProvisionAbierta.equals(""))
		{
			Calendar fecha = Calendar.getInstance();
			String sSiguienteProvision = fecha.get(Calendar.YEAR)+ValoresDefecto.DEF_COREAE+fecha.get(Calendar.DAY_OF_YEAR); 
			
			if (CLProvisiones.existeProvision(sSiguienteProvision))
			{
				this.bBloqueaAbrir = true;
				sMsg = "ERROR: La provision '"+sSiguienteProvision+"' ya fue cerrada.";
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,sMsg,null);
			}
			else
			{
				this.sNUPROF = sSiguienteProvision;
				this.sValorTolal = "0";
				this.sNumGastos = "0";
				this.sFEPFON = "Sin cerrar";
				this.sFechaValidacion = "Sin validar";
				this.sValidado = "Pendiente";
				this.bBloqueaAbrir = false;
				
				sMsg = "Cargando nueva provision.";
				msg = new FacesMessage(sMsg);
			}			
			this.bBloqueaCerrar = true;
		}
		else
		{
			Provision provision = CLProvisiones.detallesProvision(sProvisionAbierta);
			
			this.sNUPROF = provision.getsNUPROF();
			this.sValorTolal = provision.getsValorTolal();
			this.sNumGastos = provision.getsNumGastos();
			this.sFEPFON = provision.getsFEPFON();
			this.sFechaValidacion = provision.getsFechaValidacion();
			this.sValidado = provision.getsValidado();
			this.bBloqueaAbrir = true;
			this.bBloqueaCerrar = false;
			
			sMsg = "Cargando provision abierta '"+sProvisionAbierta+"'.";
			msg = new FacesMessage(sMsg);
		}
    	Utils.debugTrace(true, sClassName, sMethod, sMsg);
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void cargarProvision(ActionEvent actionEvent)
	{
		cargaProvisionAbierta();
	}
	
	public void abrirProvision(ActionEvent actionEvent)
	{
		
		//Cargar provision abierta
		
		//las provisiones se abren automaticamente 
		String sMethod = "abrirProvision";
		
		FacesMessage msg;
    	
    	String sMsg = "";
		
		Provision provision = new Provision (sNUPROF, "0", "0","0","0","0",ValoresDefecto.DEF_PENDIENTE);
		if (CLProvisiones.ultimaProvisionAbierta().equals(sNUPROF))
		{
			sMsg = "ERROR: Esta provision ya esta abierta, cierrela antes de abrir una nueva.";
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,sMsg,null);
		}
		else if (CLProvisiones.nuevaProvision(provision))
		{
			sMsg = "Provision '"+ sNUPROF +"' abierta.";
			msg = new FacesMessage(sMsg);
		}
		else
		{
			sMsg = "ERROR: La provision '"+CLProvisiones.ultimaProvisionAbierta()+"' esta abierta, cierrela antes de abrir una nueva.";
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,sMsg,null);
		}
    	
    	Utils.debugTrace(true, sClassName, sMethod, sMsg);
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		this.bBloqueaAbrir = true;
		this.bBloqueaCerrar = false;
	}
	
	public void cerrarProvision(ActionEvent actionEvent)
	{
		String sMethod = "cerrarProvision";
		
    	FacesMessage msg;
    	
    	String sMsg = "";
		
		Provision provision = CLProvisiones.detallesProvision(CLProvisiones.ultimaProvisionAbierta());
		
		provision.setsFEPFON(Utils.fechaDeHoy(false));
		
		provision.setsValidado("E");
		
		CLProvisiones.cerrarProvision(provision);

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
		this.bBloqueaAbrir = true;
		this.bBloqueaCerrar = true;
	}

	public String getsNUPROF() {
		return sNUPROF;
	}

	public void setsNUPROF(String sNUPROF) {
		this.sNUPROF = sNUPROF;
	}

	public String getsFEPFON() {
		return sFEPFON;
	}

	public void setsFEPFON(String sFEPFON) {
		this.sFEPFON = sFEPFON;
	}

	public String getsFechaValidacion() {
		return sFechaValidacion;
	}

	public void setsFechaValidacion(String sFechaValidacion) {
		this.sFechaValidacion = sFechaValidacion;
	}

	public String getsValorTolal() {
		return sValorTolal;
	}

	public void setsValorTolal(String sValorTolal) {
		this.sValorTolal = sValorTolal;
	}

	public String getsNumGastos() {
		return sNumGastos;
	}

	public void setsNumGastos(String sNumGastos) {
		this.sNumGastos = sNumGastos;
	}

	public String getsValidado() {
		return sValidado;
	}

	public void setsValidado(String sValidado) {
		this.sValidado = sValidado;
	}

	public boolean isbBloqueaAbrir() {
		return bBloqueaAbrir;
	}

	public void setbBloqueaAbrir(boolean bBloqueaAbrir) {
		this.bBloqueaAbrir = bBloqueaAbrir;
	}

	public boolean isbBloqueaCerrar() {
		return bBloqueaCerrar;
	}

	public void setbBloqueaCerrar(boolean bBloqueaCerrar) {
		this.bBloqueaCerrar = bBloqueaCerrar;
	}


	
	
}
