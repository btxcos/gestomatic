package com.provisiones.misc;

import java.util.LinkedList;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.pl.GestorSesion;
import com.provisiones.types.Historial;
import com.provisiones.types.Transicion;

public final class Sesion 
{
	//Interfaz con el GestorSesion
	
	private static Logger logger = LoggerFactory.getLogger(Sesion.class.getName());
	
	private Sesion() {}

	
	public static String cargarID()
	{
		String sID = "";
		if (ConnectionManager.comprobarConexion())
		{
			sID = ((GestorSesion)((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorSesion")).getsID();
		}
		return sID;
		
	}
	
	public static void guardaID(String sID)
	{
		logger.debug("Guardando:"+sID);

		if (ConnectionManager.comprobarConexion())
		{
			((GestorSesion)((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorSesion")).setsID(sID);
		}
		
	}
	
	public static int cargarTipoID()
	{
		int iTipoID = 0;
		if (ConnectionManager.comprobarConexion())
		{
			iTipoID = ((GestorSesion)((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorSesion")).getiTipoID();
		}
		return iTipoID;
		
	}
	
	public static void guardaTipoID(int iTipoID)
	{
		logger.debug("Guardando:"+iTipoID);

		if (ConnectionManager.comprobarConexion())
		{
			((GestorSesion)((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorSesion")).setiTipoID(iTipoID);
		}
		
	}
	
	public static String cargarHistorial()
	{
		String sPagina = "login.xhtml";

		if (ConnectionManager.comprobarConexion())
		{
			LinkedList<Historial> historial = ((GestorSesion)((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorSesion")).getHistorial();
			
			Historial datos = historial.pollLast();
			
			sPagina = datos.getsPagina();
			
			logger.debug("historial:"+historial.toString());
			
			
			((GestorSesion)((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorSesion")).setHistorial(historial);
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			
			session.removeAttribute(datos.getsGestor());
		}
		return sPagina;
		
	}

	public static void guardarHistorial(String sPagina, String sGestor)
	{
		logger.debug("Guardando:"+sPagina);

		LinkedList<Historial> historial = ((GestorSesion)((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorSesion")).getHistorial();
		
		Historial datos = new Historial(sPagina, sGestor);
		
		historial.offer(datos);
		
		logger.debug("historial:"+historial.toString());
		
		((GestorSesion)((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorSesion")).setHistorial(historial);

		
	}
	
	public static void limpiarHistorial()
	{

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		LinkedList<Historial> historial = ((GestorSesion)((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorSesion")).getHistorial();
		
		while (historial.size() > 0)
		{
			Historial datos = historial.pollLast();
			session.removeAttribute(datos.getsGestor());
		}		
		
		((GestorSesion)((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorSesion")).setHistorial(new LinkedList<Historial>());

		
	}
	
	public static void guardarTransicion(Transicion transicion, boolean bLimpia)
	{
    	if (bLimpia)
    	{
        	limpiarHistorial();
    	}

		guardaID(transicion.getsID());
    	guardaTipoID(transicion.getiTipoID());
    	guardarHistorial(transicion.getsPaginaOrigen(),transicion.getsGestorDestino());
		
	}
}

//Autor: Francisco Valverde Manjón