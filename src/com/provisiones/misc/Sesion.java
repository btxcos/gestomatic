package com.provisiones.misc;

import java.util.LinkedList;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.pl.GestorSesion;
import com.provisiones.types.Historial;

public final class Sesion 
{
	//Interfaz con el GestorSesion
	
	private static Logger logger = LoggerFactory.getLogger(Sesion.class.getName());
	
	private Sesion() {}

	
	public static String cargarDetalle()
	{
		String sDetalle = "";
		if (ConnectionManager.comprobarConexion())
		{
			sDetalle = ((GestorSesion)((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorSesion")).getsDetalle();
		}
		return sDetalle;
		
	}
	
	public static void guardaDetalle(String sDetalle)
	{
		logger.debug("Guardando:"+sDetalle);

		if (ConnectionManager.comprobarConexion())
		{
			((GestorSesion)((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("GestorSesion")).setsDetalle(sDetalle);
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
	
}
