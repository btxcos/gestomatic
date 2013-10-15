package com.provisiones.test;

import java.io.IOException;
import java.util.ArrayList;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.provisiones.types.ResultadosTabla;


public class TestActivos 
{
	//private static Logger logger = LoggerFactory.getLogger(TestActivos.class.getName());
	
	private ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>(); 
	
	public static void main(String[] args) throws IOException 
	{
		
	
		//ResultadosTabla datos = new ResultadosTabla("A","B");
		
		
		//Utils.debugTrace(true, "TEST", "TEST", "|"+getTabla().size()+"|");

	}

	public ArrayList<ResultadosTabla> getTabla() {
		return tabla;
	}

	public void setTabla(ArrayList<ResultadosTabla> tabla) {
		this.tabla = tabla;
	}
}
