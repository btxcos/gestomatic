package com.provisiones.test;

import java.io.File;

import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.qm.listas.QMListaGastosProvisiones;
import com.provisiones.misc.Utils;

public class TestLog 
{
	private static Logger logger = LoggerFactory.getLogger(TestLog.class.getName());
	
	public static void main(String[] args) 
	{
	
		//DOMConfigurator.configure(System.getProperty("user.dir")+File.separator+"WebContent"+File.separator+"WEB-INF"+File.separator+"log4j.xml");
		
		DOMConfigurator.configure("C:"+File.separator+"SDK"+File.separator+"GLSL"+File.separator+"workspace"+File.separator+"provisiones"+File.separator+"build"+File.separator+"classes"+File.separator+"log4j.xml");
		
		logger.debug("user.dir:|"+System.getProperty("user.dir")+"|");

	    /*long prev_time1;
	    long time1;
	    Runtime runtime1;
	    long prev_memory1;
	    long memory1;

	    long prev_time2;
	    long time2;
	    Runtime runtime2;
	    long prev_memory2;
	    long memory2;

	    long prev_time3;
	    long time3;
	    Runtime runtime3;
	    long prev_memory3;
	    long memory3;


	    //-------
	    
	    runtime1 = Runtime.getRuntime();
	    prev_memory1 = runtime1.freeMemory();
	    prev_time1 = System.currentTimeMillis();

	    logger.debug("|"+"A"+"||"+"B"+"||"+"C"+"|");

	    memory1 = prev_memory1 - runtime1.freeMemory();
	    time1 = System.currentTimeMillis() - prev_time1;

	    System.out.println("Memoria del +: " + memory1);
	    System.out.println("Tiempo del +: " + time1);
	    
	    System.out.println("runtime1: "+ runtime1.toString());

	    
	    //-------
	    
	    runtime2 = Runtime.getRuntime();
	    prev_memory2 = runtime2.freeMemory();
	    prev_time2 = System.currentTimeMillis();

	    logger.debug(String.format("|%s||%s||%s|","A","B","C"));

	    memory2 = prev_memory2 - runtime2.freeMemory();
	    time2 = System.currentTimeMillis() - prev_time2;

	    System.out.println("Memoria del String.format: " + memory2);
	    System.out.println("Tiempo del String.format: " + time2);
	    
	    System.out.println("runtime2: "+ runtime2.toString());
	    
	    //-------
	    
	    runtime3 = Runtime.getRuntime();
	    prev_memory3 = runtime3.freeMemory();
	    prev_time3 = System.currentTimeMillis();

		logger.debug("|{}||{}||{}|","A","B","C");

	    memory3 = prev_memory3 - runtime3.freeMemory();
	    time3 = System.currentTimeMillis() - prev_time3;
	    
	    System.out.println("Memoria del {}: " + memory3);
	    System.out.println("Tiempo del {}: " + time3);
	    
	    System.out.println("runtime3: "+ runtime3.toString());*/

	    
	    Utils.debugTraceArrayList(true, "TestLog", "main", QMListaGastosProvisiones.buscaGastosSinValidarEnProvision("1304000"));
		
	}

}
