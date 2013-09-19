package com.provisiones.test;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestPF implements Serializable
{  
	  
	private static final long serialVersionUID = 5280073149818806999L;


	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(TestPF.class.getName());
	
	String sCampo = "un dato";
	  
    public String getsCampo() {
		return sCampo;
	}

	public void setsCampo(String sCampo) {
		this.sCampo = sCampo;
	}


} 
