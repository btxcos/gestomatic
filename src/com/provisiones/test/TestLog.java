package com.provisiones.test;

import java.io.File;

import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;

public class TestLog 
{
	private static Logger logger = LoggerFactory.getLogger(TestLog.class.getName());
	
	public static void main(String[] args) 
	{
	
		DOMConfigurator.configure(System.getProperty("user.dir")+File.separator+"WebContent"+File.separator+"WEB-INF"+File.separator+"log4j.xml");
		
		logger.debug("user.dir:|"+System.getProperty("user.dir")+"|");
		
		ConnectionManager.CloseDBConnection(ConnectionManager.OpenDBConnection());
		
	}

}
