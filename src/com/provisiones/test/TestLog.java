package com.provisiones.test;

import java.io.File;

import org.apache.log4j.xml.DOMConfigurator;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;

public class TestLog {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Utils.debugTrace(true, "TestLog", "main", "user.dir:|"+System.getProperty("user.dir")+"|");
	
		DOMConfigurator.configure(System.getProperty("user.dir")+File.separator+"WebContent"+File.separator+"WEB-INF"+File.separator+"log4j.xml");
		
		
		ConnectionManager.CloseDBConnection(ConnectionManager.OpenDBConnection());
		
	}

}
