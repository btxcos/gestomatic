package com.provisiones.test;

import java.io.IOException;
import java.util.ArrayList;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.provisiones.misc.Utils;
import com.provisiones.types.tablas.ResultadosTabla;


public class TestActivos 
{
	//private static Logger logger = LoggerFactory.getLogger(TestActivos.class.getName());
	
	private ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>(); 
	
	public static void main(String[] args) throws IOException 
	{
		
	
		//ResultadosTabla datos = new ResultadosTabla("A","B");
		
		
		//Utils.debugTrace(true, "TEST", "TEST", "|"+getTabla().size()+"|");
		
		String sMsg = "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789";
		System.out.println(sMsg);
		System.out.println(sMsg.length());
		
		System.out.println("|"+Utils.timeStamp()+"|");
		sMsg = Utils.cifra(sMsg);
		
		System.out.println("|"+Utils.timeStamp()+"|");
		System.out.println("|"+sMsg+"|");
		System.out.println(sMsg.length());
		
		
		
		

		System.out.println("|"+Utils.timeStamp()+"|");
		System.out.println(Utils.descifra(sMsg));
		System.out.println("|"+Utils.timeStamp()+"|");

	}

	public ArrayList<ResultadosTabla> getTabla() {
		return tabla;
	}

	public void setTabla(ArrayList<ResultadosTabla> tabla) {
		this.tabla = tabla;
	}
}
