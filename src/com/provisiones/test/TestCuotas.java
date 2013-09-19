package com.provisiones.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.misc.Utils;


public class TestCuotas 
{

	private static Logger logger = LoggerFactory.getLogger(TestCuotas.class.getName());


	public static void main(String[] args) 
	{

		
		//logger.addAppender(new FileAppender(new PatternLayout(),"prueba.log", false));
		
		//DOMConfigurator.configure(System.getProperty("user.dir")+File.separator+"WebContent"+File.separator+"WEB-INF"+File.separator+"log4j.xml");
		
		//PropertyConfigurator.configure(System.getProperty("user.dir")+File.separator+"log4j"+File.separator+"log4j.properties");
		
		//PropertyConfigurator.configure(System.getProperty("user.dir")+File.separator+"log4j"+File.separator+"log4j.properties");
		
		logger.info(Utils.timeStamp());
		logger.error(Utils.timeStamp());
		
		//Parser.escribirActivo(activo);
		
		//BasicConfigurator.configure();
		
		logger.debug("Generando ficheros...");
		
		//Utils.debugTrace2(logger, sClassName, sMethod, "Generando ficheros...");
		
		
		/*String sArchivo = "ID_168e1.txt";
		
		
		logger.debug("|"+sArchivo.substring(sArchivo.length()-9)+"|");
		
		if (sArchivo.substring(sArchivo.length()-9).toUpperCase().matches("(168)(AC|RG|PA|GA|PP|E1|E2|E3|E4)(\\.TXT)$"))
		{
			logger.debug("|"+sArchivo+"| Reconocido!");
		}
		else
		{
			logger.debug("|"+sArchivo+"| NO Reconocido.");
		}
		
		logger.debug("|"+File.separator+"|");

		logger.debug("|"+Utils.fechaDeHoy(true)+"|");
		
		logger.debug("|"+Utils.timeStamp()+"|");*/
		

		
	
		
	}
}
