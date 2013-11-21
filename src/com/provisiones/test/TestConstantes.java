package com.provisiones.test;

import com.provisiones.misc.Utils;


public class TestConstantes 
{
	
	//private static Logger logger = LoggerFactory.getLogger(TestConstantes.class.getName());


	public static void main(String[] args) 
	{
		String sImporte = "123.2132112312";
		
		System.out.println(Utils.compruebaImporte(sImporte));
		
		System.out.println(Utils.cortaDecimales(sImporte));
	}

}
