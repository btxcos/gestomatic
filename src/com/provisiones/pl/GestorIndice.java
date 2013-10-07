package com.provisiones.pl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.ll.CLProvisiones;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

public class GestorIndice implements Serializable 
{
	private static final long serialVersionUID = -3089995402905654371L;
	
	private static Logger logger = LoggerFactory.getLogger(GestorIndice.class.getName());
	
	private String sUsuario = ValoresDefecto.DEF_USUARIO;
	
	public GestorIndice()
	{
		logger.info("Inicializando aplicacion.");
		Utils.inicializarDirectorios();
		CLProvisiones.inicializaProvisiones();
	}

	public String getsUsuario() {
		return sUsuario;
	}

	public void setsUsuario(String sUsuario) {
		this.sUsuario = sUsuario;
	}
}
