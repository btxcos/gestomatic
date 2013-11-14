package com.provisiones.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.usuarios.QMUsuarios;
import com.provisiones.misc.Utils;
import com.provisiones.types.tablas.ResultadosTabla;
import com.provisiones.types.usuarios.Usuario;


@SuppressWarnings("unused")
public class TestActivos 
{
	//private static Logger logger = LoggerFactory.getLogger(TestActivos.class.getName());
	
	private ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>(); 
	
	public static void main(String[] args) throws IOException 
	{
		
	


	}

	public ArrayList<ResultadosTabla> getTabla() {
		return tabla;
	}

	public void setTabla(ArrayList<ResultadosTabla> tabla) {
		this.tabla = tabla;
	}
}
