package com.provisiones.test;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.qm.listas.QMListaComunidadesActivos;
import com.provisiones.ll.CLComunidades;
import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;
import com.provisiones.types.CargaTabla;


public class TestActivos 
{
	//private static Logger logger = LoggerFactory.getLogger(TestActivos.class.getName());
	
	private ArrayList<CargaTabla> tabla = new ArrayList<CargaTabla>(); 
	
	public static void main(String[] args) throws IOException 
	{
		
	
		CargaTabla datos = new CargaTabla("A","B");
		
		
		//Utils.debugTrace(true, "TEST", "TEST", "|"+getTabla().size()+"|");

	}

	public ArrayList<CargaTabla> getTabla() {
		return tabla;
	}

	public void setTabla(ArrayList<CargaTabla> tabla) {
		this.tabla = tabla;
	}
}
