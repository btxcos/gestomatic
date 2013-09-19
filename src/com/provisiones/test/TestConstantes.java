package com.provisiones.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestConstantes 
{
	
	private static Logger logger = LoggerFactory.getLogger(TestConstantes.class.getName());


	public static void main(String[] args) 
	{

		
		//Parser.escribirActivo(activo);
		
		
		logger.debug("Generando ficheros...");
		
		String sPrueba = "XXXXXXXXXXXXXXXXX%sXXXXXXXXXXX";
		
		logger.debug("|"+String.format(sPrueba,"Hola")+"|");

	}

}
