package com.provisiones.dal;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManager
{

	static String sClassName = TableManager.class.getName();

	public static boolean eliminarTablas(Connection conn)
	{
		Statement stmt = null;

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DROP TABLE binaria_tbl;");
			stmt.executeUpdate("DROP TABLE biobnu_tbl;");
			stmt.executeUpdate("DROP TABLE codiju_tbl;");
			stmt.executeUpdate("DROP TABLE coenae_tbl;");
			stmt.executeUpdate("DROP TABLE coesen_tbl;");
			stmt.executeUpdate("DROP TABLE coesve_tbl;");
			stmt.executeUpdate("DROP TABLE cograp_tbl;");
			stmt.executeUpdate("DROP TABLE coprae_tbl;");
			stmt.executeUpdate("DROP TABLE coreae_tbl;");
			stmt.executeUpdate("DROP TABLE coscar_tbl;");
			stmt.executeUpdate("DROP TABLE cosjup_tbl;");
			stmt.executeUpdate("DROP TABLE cosocu_tbl;");
			stmt.executeUpdate("DROP TABLE cosopa_tbl;");
			stmt.executeUpdate("DROP TABLE cospat_tbl;");
			stmt.executeUpdate("DROP TABLE costli_tbl;");
			stmt.executeUpdate("DROP TABLE cotdor_e1_tbl;");
			stmt.executeUpdate("DROP TABLE cotdor_e2_tbl;");
			stmt.executeUpdate("DROP TABLE cotdor_e3_tbl;");
			stmt.executeUpdate("DROP TABLE cotdor_e4_tbl;");
			stmt.executeUpdate("DROP TABLE cotpet_tbl;");
			stmt.executeUpdate("DROP TABLE cotsin_tbl;");
			stmt.executeUpdate("DROP TABLE coxpro_tbl;");
			stmt.executeUpdate("DROP TABLE coxsia_tbl;");
			
		} 
		catch (SQLException ex) 
		{

			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally 
		{
			if (stmt != null) 
			{
				try 
				{
					stmt.close();
				} 
				catch (SQLException sqlEx) 
				{
				} 
				stmt = null;
			}
		}
			
		return true;
	}
	
	
	public static boolean crearTablasCodigos(Connection conn)
	{

		Statement stmt = null;
		boolean bExit = false;

		try {
			stmt = conn.createStatement();

			stmt.executeUpdate("CREATE TABLE cosopa_tbl ("
					+ "cosopa_id INT, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(cosopa_id))");

			stmt.executeUpdate("CREATE TABLE coenae_tbl ("
					+ "coenae_id INT, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(coenae_id))");

			stmt.executeUpdate("CREATE TABLE coesen_tbl ("
					+ "coesen_id INT, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(coesen_id))");

			stmt.executeUpdate("CREATE TABLE coprae_tbl ("
					+ "coprae_id INT, " +

					"descripcion VARCHAR(18) NOT NULL, " +

					"PRIMARY KEY(coprae_id))");

			stmt.executeUpdate("CREATE TABLE coreae_tbl ("
					+ "coreae_id INT, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(coreae_id))");

			stmt.executeUpdate("CREATE TABLE codiju_tbl ("
					+ "codiju_id INT, " +

					"descripcion VARCHAR(60) NOT NULL, " +

					"PRIMARY KEY(codiju_id))");

			stmt.executeUpdate("CREATE TABLE cosjup_tbl ("
					+ "cosjup_id INT, " +

					"descripcion VARCHAR(60) NOT NULL, " +

					"PRIMARY KEY(cosjup_id))");

			stmt.executeUpdate("CREATE TABLE costli_tbl ("
					+ "costli_id INT, " +

					"descripcion VARCHAR(60) NOT NULL, " +

					"PRIMARY KEY(costli_id))");

			stmt.executeUpdate("CREATE TABLE coscar_tbl ("
					+ "coscar_id INT, " +

					"descripcion VARCHAR(60) NOT NULL, " +

					"PRIMARY KEY(coscar_id))");

			stmt.executeUpdate("CREATE TABLE coesve_tbl ("
					+ "coesve_id INT, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(coesve_id))");

			stmt.executeUpdate("CREATE TABLE cotsin_tbl ("
					+ "cotsin_id VARCHAR(4), " +

					"descripcion VARCHAR(60) NOT NULL, " +

					"PRIMARY KEY(cotsin_id))");

			stmt.executeUpdate("CREATE TABLE cosocu_tbl ("
					+ "cosocu_id INT, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(cosocu_id))");

			stmt.executeUpdate("CREATE TABLE coxpro_tbl ("
					+ "coxpro_id INT, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(coxpro_id))");

			stmt.executeUpdate("CREATE TABLE binaria_tbl ("
					+ "binaria_id CHAR, " +

					"descripcion VARCHAR(3) NOT NULL, " +

					"PRIMARY KEY(binaria_id))");

			stmt.executeUpdate("CREATE TABLE cograp_tbl ("
					+ "cograp_id INT, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(cograp_id))");

			stmt.executeUpdate("CREATE TABLE cotpet_tbl ("
					+ "cotpet_id INT, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(cotpet_id))");

			stmt.executeUpdate("CREATE TABLE coxsia_tbl ("
					+ "coxsia_id INT, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(coxsia_id))");

			stmt.executeUpdate("CREATE TABLE cospat_tbl ("
					+ "cospat_id INT, " +

					"descripcion VARCHAR(60) NOT NULL, " +

					"PRIMARY KEY(cospat_id))");

			stmt.executeUpdate("CREATE TABLE biobnu_tbl ("
					+ "biobnu_id CHAR, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(biobnu_id))");

			stmt.executeUpdate("CREATE TABLE cotdor_e1_tbl ("
					+ "cotdor_id INT, " +

					"descripcion VARCHAR(90) NOT NULL, " +

					"PRIMARY KEY(cotdor_id))");

			stmt.executeUpdate("CREATE TABLE cotdor_e2_tbl ("
					+ "cotdor_id INT, " +

					"descripcion VARCHAR(90) NOT NULL, " +

					"PRIMARY KEY(cotdor_id))");

			stmt.executeUpdate("CREATE TABLE cotdor_e3_tbl ("
					+ "cotdor_id INT, " +

					"descripcion VARCHAR(90) NOT NULL, " +

					"PRIMARY KEY(cotdor_id))");

			stmt.executeUpdate("CREATE TABLE cotdor_e4_tbl ("
					+ "cotdor_id INT, " +

					"descripcion VARCHAR(90) NOT NULL, " +

					"PRIMARY KEY(cotdor_id))");
			
		} catch (SQLException ex) {

			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
					bExit = true;
				} catch (SQLException sqlEx) {
				}
				stmt = null;
			}
		}
		return bExit;
	}
}
