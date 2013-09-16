package com.provisiones.test;

import java.io.File;
import com.provisiones.misc.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.log4j.xml.DOMConfigurator;



public class TestCuotas {
	static String sClassName = TestCuotas.class.getName();
	
	//private static Logger logger = LoggerFactory.getLogger(TestCuotas.class);
	
	private static Logger logger = LoggerFactory.getLogger(sClassName);


	/**
	 * @param args
	 */
	

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		//Cuota cuota1 
		//QMListaCuotas.buscaCuotasActivo("3109139");
		
		boolean bTraza = true;
		String sMethod = "testFicheros";
		
		//logger.addAppender(new FileAppender(new PatternLayout(),"prueba.log", false));
		
		DOMConfigurator.configure(System.getProperty("user.dir")+File.separator+"WebContent"+File.separator+"WEB-INF"+File.separator+"log4j.xml");
		
		//PropertyConfigurator.configure(System.getProperty("user.dir")+File.separator+"log4j"+File.separator+"log4j.properties");
		
		//PropertyConfigurator.configure(System.getProperty("user.dir")+File.separator+"log4j"+File.separator+"log4j.properties");
		
		logger.info(Utils.timeStamp());
		logger.error(Utils.timeStamp());
		
		//Parser.escribirActivo(activo);
		
		//BasicConfigurator.configure();
		
		Utils.debugTrace(true, sClassName, sMethod, "Generando ficheros...");
		
		//Utils.debugTrace2(logger, sClassName, sMethod, "Generando ficheros...");
		
		
		/*String sArchivo = "ID_168e1.txt";
		
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "|"+sArchivo.substring(sArchivo.length()-9)+"|");
		
		if (sArchivo.substring(sArchivo.length()-9).toUpperCase().matches("(168)(AC|RG|PA|GA|PP|E1|E2|E3|E4)(\\.TXT)$"))
		{
			Utils.debugTrace(bTraza, sClassName, sMethod, "|"+sArchivo+"| Reconocido!");
		}
		else
		{
			Utils.debugTrace(bTraza, sClassName, sMethod, "|"+sArchivo+"| NO Reconocido.");
		}
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "|"+File.separator+"|");

		Utils.debugTrace(bTraza, sClassName, sMethod, "|"+Utils.fechaDeHoy(true)+"|");
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "|"+Utils.timeStamp()+"|");*/
		

		
	
		
	}
}
