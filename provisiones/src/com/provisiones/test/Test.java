package com.provisiones.test;

import java.sql.Connection;

public class Test 
{
	static String sClassName = Test.class.getName();
	static boolean bEnable = true;

	public static void main(String[] args) 
	{
		Connection conn = null;

		conn = com.provisiones.dal.ConnectionManager.OpenDBConnection();
		com.provisiones.dal.ConnectionManager.CloseDBConnection(conn);
		com.provisiones.dal.Utils.debugTrace(true, sClassName, "main", "Conexion Realizada");
				
	}

}
