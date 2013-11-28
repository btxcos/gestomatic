package com.provisiones.test;

import com.provisiones.misc.Utils;


public class TestConstantes 
{
	
	//private static Logger logger = LoggerFactory.getLogger(TestConstantes.class.getName());


	public static void main(String[] args) 
	{
		String sImporte = "123231321321";
		
		
		String sEuros = sImporte.substring(0, sImporte.length()-2);
		String sCentimos = sImporte.substring(sImporte.length()-2,sImporte.length());
		
		System.out.println(sEuros);
		System.out.println(sCentimos);
			
		System.out.println(Utils.recuperaImporte(true,sImporte));
		
		System.out.println(Utils.compruebaImporte(Utils.recuperaImporte(true,sImporte)));
	}

}
