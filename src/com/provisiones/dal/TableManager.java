package com.provisiones.dal;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableManager
{
	private static Logger logger = LoggerFactory.getLogger(TableManager.class.getName());

	public static boolean eliminarTablasCodigos(Connection conn)
	{
		Statement stmt = null;

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DROP TABLE biauto_tbl;");
			stmt.executeUpdate("DROP TABLE binaria_tbl;");
			stmt.executeUpdate("DROP TABLE biobnu_tbl;");
			stmt.executeUpdate("DROP TABLE coacci_e1_tbl;");
			stmt.executeUpdate("DROP TABLE coacci_e2_tbl;");
			stmt.executeUpdate("DROP TABLE coacci_e3_tbl;");
			stmt.executeUpdate("DROP TABLE coacci_e4_tbl;");
			stmt.executeUpdate("DROP TABLE cocldo_tbl;");
			stmt.executeUpdate("DROP TABLE codiju_tbl;");
			stmt.executeUpdate("DROP TABLE codtrn_tbl;");
			stmt.executeUpdate("DROP TABLE coenae_tbl;");
			stmt.executeUpdate("DROP TABLE coesen_tbl;");
			stmt.executeUpdate("DROP TABLE coesve_tbl;");
			stmt.executeUpdate("DROP TABLE cograp_tbl;");
			stmt.executeUpdate("DROP TABLE cogrug_tbl;");
			stmt.executeUpdate("DROP TABLE coimpt_tbl;");
			stmt.executeUpdate("DROP TABLE comona_tbl;");
			stmt.executeUpdate("DROP TABLE coprae_tbl;");
			stmt.executeUpdate("DROP TABLE coreae_tbl;");
			stmt.executeUpdate("DROP TABLE cosbga_t21_tbl;");
			stmt.executeUpdate("DROP TABLE cosbga_t22_tbl;");
			stmt.executeUpdate("DROP TABLE cosbga_t23_tbl;");
			stmt.executeUpdate("DROP TABLE cosbga_t32_tbl;");
			stmt.executeUpdate("DROP TABLE coscar_tbl;");
			stmt.executeUpdate("DROP TABLE cosiga_tbl;");
			stmt.executeUpdate("DROP TABLE cosjup_tbl;");
			stmt.executeUpdate("DROP TABLE cosocu_tbl;");
			stmt.executeUpdate("DROP TABLE cosopa_tbl;");
			stmt.executeUpdate("DROP TABLE cospat_tbl;");
			stmt.executeUpdate("DROP TABLE costli_tbl;");
			stmt.executeUpdate("DROP TABLE cotdor_e1_tbl;");
			stmt.executeUpdate("DROP TABLE cotdor_e2_tbl;");
			stmt.executeUpdate("DROP TABLE cotdor_e3_tbl;");
			stmt.executeUpdate("DROP TABLE cotdor_e4_tbl;");
			stmt.executeUpdate("DROP TABLE coterr_tbl;");
			stmt.executeUpdate("DROP TABLE cotneg_tbl;");
			stmt.executeUpdate("DROP TABLE cotpet_tbl;");
			stmt.executeUpdate("DROP TABLE cotpga_g1_tbl;");
			stmt.executeUpdate("DROP TABLE cotpga_g2_tbl;");
			stmt.executeUpdate("DROP TABLE cotpga_g3_tbl;");
			stmt.executeUpdate("DROP TABLE cotsin_tbl;");
			stmt.executeUpdate("DROP TABLE coxpro_tbl;");
			stmt.executeUpdate("DROP TABLE coxsia_tbl;");
			stmt.executeUpdate("DROP TABLE ptpago_tbl;");
			stmt.executeUpdate("DROP TABLE ternaria_tbl;");
			stmt.executeUpdate("DROP TABLE unaria_tbl;");

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		}
		finally 
		{
			if (stmt != null) 
			{
				try 
				{
					stmt.close();
				} 
				catch (SQLException ex) 
				{
					logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
				} 
				stmt = null;
			}
		}
			
		return true;
	}
	
	public static boolean eliminarTablasDatos(Connection conn)
	{
		Statement stmt = null;

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DROP TABLE ac_datos_tbl;");
			stmt.executeUpdate("DROP TABLE e1_comunidades_tbl;");
			stmt.executeUpdate("DROP TABLE e2_comunidades_tbl;");
			stmt.executeUpdate("DROP TABLE e3_referencias_tbl;");
			stmt.executeUpdate("DROP TABLE e4_impuestos_tbl;");
			stmt.executeUpdate("DROP TABLE ga_gastos_tbl;");
			stmt.executeUpdate("DROP TABLE provisiones_tbl;");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		}
		finally 
		{
			if (stmt != null) 
			{
				try 
				{
					stmt.close();
				} 
				catch (SQLException ex) 
				{
					logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
				} 
				stmt = null;
			}
		}
			
		return true;
	}
	
	public static boolean eliminarTablasMulti(Connection conn)
	{
		Statement stmt = null;

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DROP TABLE lista_impuestos_multi;");
			stmt.executeUpdate("DROP TABLE lista_cuotas_multi;");
			stmt.executeUpdate("DROP TABLE lista_gastos_multi;");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
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

	/*public static boolean crearTablasCodigos(Connection conn)
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

			stmt.executeUpdate("CREATE TABLE unaria_tbl ("
					+ "unaria_id CHAR, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(unaria_id))");

			stmt.executeUpdate("CREATE TABLE codtrn_tbl ("
					+ "codtrn_id VARCHAR(4), " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(codtrn_id))");

			stmt.executeUpdate("CREATE TABLE coacci_tbl ("
					+ "coacci_id CHAR, " +

					"descripcion VARCHAR(70) NOT NULL, " +

					"PRIMARY KEY(coacci_id))");

			stmt.executeUpdate("CREATE TABLE cocldo_tbl ("
					+ "cocldo_id CHAR, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(cocldo_id))");
			
			stmt.executeUpdate("CREATE TABLE coacci_e2_tbl ("
					+ "coacci_id CHAR, " +

					"descripcion VARCHAR(60) NOT NULL, " +

					"PRIMARY KEY(coacci_id))");

			stmt.executeUpdate("CREATE TABLE coacci_e3_tbl ("
					+ "coacci_id CHAR, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(coacci_id))");

			stmt.executeUpdate("CREATE TABLE coacci_e4_tbl ("
					+ "coacci_id CHAR, " +

					"descripcion VARCHAR(60) NOT NULL, " +

					"PRIMARY KEY(coacci_id))");

			stmt.executeUpdate("CREATE TABLE ternaria_tbl ("
					+ "ternaria_id CHAR, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(ternaria_id))");

			stmt.executeUpdate("CREATE TABLE cogrug_tbl ("
					+ "cogrug_id INT, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(cogrug_id))");
			

			stmt.executeUpdate("CREATE TABLE cotpga_g1_tbl ("
					+ "cotpga_id INT, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(cotpga_id))");


			stmt.executeUpdate("CREATE TABLE cotpga_g2_tbl ("
					+ "cotpga_id INT, " +

					"descripcion VARCHAR(20) NOT NULL, " +

					"PRIMARY KEY(cotpga_id))");
					

			stmt.executeUpdate("CREATE TABLE cotpga_g3_tbl ("
					+ "cotpga_id INT, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(cotpga_id))");
					

			stmt.executeUpdate("CREATE TABLE cosbga_t21_tbl ("
					+ "cosbga_id INT, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(cosbga_id))");
					

			stmt.executeUpdate("CREATE TABLE cosbga_t22_tbl ("
					+ "cosbga_id INT, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(cosbga_id))");
					

			stmt.executeUpdate("CREATE TABLE cosbga_t23_tbl ("
					+ "cosbga_id INT, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(cosbga_id))");
					

			stmt.executeUpdate("CREATE TABLE cosbga_t32_tbl ("
					+ "cosbga_id INT, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(cosbga_id))");			

			stmt.executeUpdate("CREATE TABLE ptpago_tbl ("
					+ "ptpago_id INT, " +

					"descripcion VARCHAR(20) NOT NULL, " +

					"PRIMARY KEY(ptpago_id))");			

			stmt.executeUpdate("CREATE TABLE cosiga_tbl ("
					+ "cosiga_id INT, " +

					"descripcion VARCHAR(30) NOT NULL, " +

					"PRIMARY KEY(cosiga_id))");

			stmt.executeUpdate("CREATE TABLE coimpt_tbl ("
					+ "coimpt_id INT, " +

					"descripcion VARCHAR(20) NOT NULL, " +

					"PRIMARY KEY(coimpt_id))");

			stmt.executeUpdate("CREATE TABLE cotneg_tbl ("
					+ "cotneg_id INT, " +

					"descripcion VARCHAR(20) NOT NULL, " +

					"PRIMARY KEY(cotneg_id))");

			stmt.executeUpdate("CREATE TABLE comona_tbl ("
					+ "comona_id INT, " +

					"descripcion VARCHAR(20) NOT NULL, " +

					"PRIMARY KEY(cotneg_id))");

			stmt.executeUpdate("CREATE TABLE biauto_tbl ("
					+ "biauto_id INT, " +

					"descripcion VARCHAR(20) NOT NULL, " +

					"PRIMARY KEY(biauto_id))");

			stmt.executeUpdate("CREATE TABLE coterr_tbl ("
					+ "coterr_id INT, " +

					"descripcion VARCHAR(400) NOT NULL, " +

					"PRIMARY KEY(coterr_id))");


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
	}*/	


	/*public static boolean crearTablasDatos(Connection conn)
	{

		Statement stmt = null;
		boolean bExit = false;

		try {
			stmt = conn.createStatement();

			stmt.executeUpdate("CREATE TABLE ac_datos_tbl ("
					+ "ac_datos_id INT AUTO_INCREMENT, " +
					"nuinmu VARCHAR(9) NOT NULL, " +
					"cod_cosopa INT NOT NULL, " +
					"cod_coenae INT NOT NULL, " +
					"cod_coesen INT NOT NULL, " +
					"novias VARCHAR(60) NOT NULL, " +
					"nupoac VARCHAR(17) NOT NULL, " +
					"nuesac VARCHAR(5) NOT NULL, " +
					"nupiac VARCHAR(11) NOT NULL, " +
					"nupuac VARCHAR(17) NOT NULL, " +
					"nomuin VARCHAR(30) NOT NULL, " +
					"cod_coprae INT NOT NULL, " +
					"noprac VARCHAR(18) NOT NULL, " +
					"copoin VARCHAR(5) NOT NULL, " +
					"fereap VARCHAR(8) NOT NULL, " +
					"cod_coreae INT NOT NULL, " +
					"feinau VARCHAR(8) NOT NULL, " +
					"fesopo VARCHAR(8) NOT NULL, " +
					"fesepo VARCHAR(8) NOT NULL, " +
					"ferepo VARCHAR(8) NOT NULL, " +
					"feadac VARCHAR(8) NOT NULL, " +
					"cod_codiju INT NOT NULL, " +
					"cod_cosjup INT NOT NULL, " +
					"cod_costli INT NOT NULL, " +
					"cod_coscar INT NOT NULL, " +
					"cod_coesve INT NOT NULL, " +
					"cod_cotsin VARCHAR(4) NOT NULL, " +
					"nufire VARCHAR(9) NOT NULL, " +
					"nuregp VARCHAR(3) NOT NULL, " +
					"nomui0 VARCHAR(30) NOT NULL, " +
					"nulibe VARCHAR(6) NOT NULL, " +
					"nutome VARCHAR(6) NOT NULL, " +
					"nufole VARCHAR(6) NOT NULL, " +
					"nuinsr VARCHAR(8) NOT NULL, " +
					"cod_cosocu INT NOT NULL, " +
					"cod_coxpro INT NOT NULL, " +
					"fesola VARCHAR(8) NOT NULL, " +
					"fesela VARCHAR(8) NOT NULL, " +
					"ferela VARCHAR(8) NOT NULL, " +
					"ferlla VARCHAR(8) NOT NULL, " +
					"caspre VARCHAR(11) NOT NULL, " +
					"casutr VARCHAR(11) NOT NULL, " +
					"casutc VARCHAR(11) NOT NULL, " +
					"casutg VARCHAR(11) NOT NULL, " +
					"cod_biarre CHAR NOT NULL, " +
					"cadorm VARCHAR(4) NOT NULL, " +
					"cabano VARCHAR(4) NOT NULL, " +
					"cod_bigapa CHAR NOT NULL, " +
					"cagapa VARCHAR(5) NOT NULL, " +
					"casute VARCHAR(9) NOT NULL, " +
					"cod_bilipo CHAR NOT NULL, " +
					"cod_biliac CHAR NOT NULL, " +
					"cod_bilius CHAR NOT NULL, " +
					"cod_biboin CHAR NOT NULL, " +
					"cod_bicefi CHAR NOT NULL, " +
					"casucb VARCHAR(9) NOT NULL, " +
					"casucs VARCHAR(9) NOT NULL, " +
					"feacon VARCHAR(4) NOT NULL, " +
					"idauto VARCHAR(10) NOT NULL, " +
					"fedema VARCHAR(8) NOT NULL, " +
					"ynocur VARCHAR(30) NOT NULL, " +
					"obreco VARCHAR(6) NOT NULL, " +
					"ynolec VARCHAR(30) NOT NULL, " +
					"nolojz VARCHAR(30) NOT NULL, " +
					"ferede VARCHAR(8) NOT NULL, " +
					"poprop VARCHAR(6) NOT NULL, " +
					"cod_cograp INT NOT NULL, " +
					"fepreg VARCHAR(8) NOT NULL, " +
					"fephac VARCHAR(8) NOT NULL, " +
					"fefoac VARCHAR(8) NOT NULL, " +
					"fevact VARCHAR(8) NOT NULL, " +
					"imvact VARCHAR(15) NOT NULL, " +
					"nufipr VARCHAR(9) NOT NULL, " +
					"cod_cotpet INT NOT NULL, " +
					"feempt VARCHAR(8) NOT NULL, " +
					"fesorc VARCHAR(8) NOT NULL, " +
					"fesode VARCHAR(8) NOT NULL, " +
					"fereac VARCHAR(8) NOT NULL, " +
					"cod_coxsia INT NOT NULL, " +
					"nujuzd INT NOT NULL, " +
					"nurcat VARCHAR(20) NOT NULL, " +
					"nomprc VARCHAR(55) NOT NULL, " +
					"nutprc VARCHAR(14) NOT NULL, " +
					"nomadc VARCHAR(55) NOT NULL, " +
					"nutadc VARCHAR(14) NOT NULL, " +
					"impcoo VARCHAR(15) NOT NULL, " +
					"coenor VARCHAR(5) NOT NULL, " +
					"cod_cospat INT NOT NULL, " +
					"cod_cospas INT NOT NULL, " +
					"idcol3 VARCHAR(4) NOT NULL, " +
					"cod_biobnu CHAR NOT NULL, " +
					"pobrar VARCHAR(6) NOT NULL, " +
					"PRIMARY KEY(ac_datos_id), " +
					"FOREIGN KEY(cod_cosopa) REFERENCES cosopa_tbl(cosopa_id), " +
					"FOREIGN KEY(cod_coenae) REFERENCES coenae_tbl(coenae_id), " +
					"FOREIGN KEY(cod_coesen) REFERENCES coesen_tbl(coesen_id), " +
					"FOREIGN KEY(cod_coprae) REFERENCES coprae_tbl(coprae_id), " +
					"FOREIGN KEY(cod_coreae) REFERENCES coreae_tbl(coreae_id), " +
					"FOREIGN KEY(cod_codiju) REFERENCES codiju_tbl(codiju_id), " +
					"FOREIGN KEY(cod_cosjup) REFERENCES cosjup_tbl(cosjup_id), " +
					"FOREIGN KEY(cod_costli) REFERENCES costli_tbl(costli_id), " +
					"FOREIGN KEY(cod_coscar) REFERENCES coscar_tbl(coscar_id), " +
					"FOREIGN KEY(cod_coesve) REFERENCES coesve_tbl(coesve_id), " +
					"FOREIGN KEY(cod_cotsin) REFERENCES cotsin_tbl(cotsin_id), " +
					"FOREIGN KEY(cod_cosocu) REFERENCES cosocu_tbl(cosocu_id), " +
					"FOREIGN KEY(cod_coxpro) REFERENCES coxpro_tbl(coxpro_id), " +
					"FOREIGN KEY(cod_biarre) REFERENCES binaria_tbl(binaria_id), " +
					"FOREIGN KEY(cod_bigapa) REFERENCES binaria_tbl(binaria_id), " +
					"FOREIGN KEY(cod_bilipo) REFERENCES binaria_tbl(binaria_id), " +
					"FOREIGN KEY(cod_biliac) REFERENCES binaria_tbl(binaria_id), " +
					"FOREIGN KEY(cod_bilius) REFERENCES binaria_tbl(binaria_id), " +
					"FOREIGN KEY(cod_biboin) REFERENCES binaria_tbl(binaria_id), " +
					"FOREIGN KEY(cod_bicefi) REFERENCES binaria_tbl(binaria_id), " +
					"FOREIGN KEY(cod_cograp) REFERENCES cograp_tbl(cograp_id), " +
					"FOREIGN KEY(cod_cotpet) REFERENCES cotpet_tbl(cotpet_id), " +
					"FOREIGN KEY(cod_coxsia) REFERENCES coxsia_tbl(coxsia_id), " +
					"FOREIGN KEY(cod_cospat) REFERENCES cospat_tbl(cospat_id), " +
					"FOREIGN KEY(cod_cospas) REFERENCES cospat_tbl(cospat_id), " +
					"FOREIGN KEY(cod_biobnu) REFERENCES binaria_tbl(binaria_id))");

			stmt.executeUpdate("CREATE TABLE e1_comunidades_tbl ("+
					"e1_comunidades_id INT AUTO_INCREMENT,"+
					"cod_codtrn VARCHAR(4) NOT NULL,"+
					"cod_cotdor INT NOT NULL,"+
					"idprov VARCHAR(9) NOT NULL,"+
					"cod_coacci CHAR NOT NULL,"+
					"coengp VARCHAR(5) NOT NULL,"+
					"cod_cocldo CHAR NOT NULL,"+
					"nudcom VARCHAR(10) NOT NULL,"+
					"cod_bitc10 CHAR NOT NULL,"+
					"cod_bitc01 CHAR NOT NULL,"+
					"nomcoc VARCHAR(60) NOT NULL,"+
					"cod_bitc02 CHAR NOT NULL,"+
					"nodcco VARCHAR(60) NOT NULL,"+
					"cod_bitc03 CHAR NOT NULL,"+
					"nomprc VARCHAR(55) NOT NULL,"+
					"cod_bitc04 CHAR NOT NULL,"+
					"nutprc VARCHAR(14) NOT NULL,"+
					"cod_bitc05 CHAR NOT NULL,"+
					"nomadc VARCHAR(55) NOT NULL,"+
					"cod_bitc06 CHAR NOT NULL,"+
					"nutadc VARCHAR(14) NOT NULL,"+
					"cod_bitc07 CHAR NOT NULL,"+
					"nodcad VARCHAR(60) NOT NULL,"+
					"cod_bitc08 CHAR NOT NULL,"+
					"nuccen VARCHAR(4) NOT NULL,"+
					"nuccof VARCHAR(4) NOT NULL,"+
					"nuccdi VARCHAR(2) NOT NULL,"+
					"nuccnt VARCHAR(10) NOT NULL,"+
					"cod_bitc09 CHAR NOT NULL,"+
					"obtexc VARCHAR(110) NOT NULL,"+
					"obdeer VARCHAR(80) NOT NULL,"+
					"PRIMARY KEY(e1_comunidades_id),"+
					"FOREIGN KEY(cod_codtrn) REFERENCES codtrn_tbl(codtrn_id),"+
					"FOREIGN KEY(cod_cotdor) REFERENCES cotdor_e1_tbl(cotdor_id),"+
					"FOREIGN KEY(cod_coacci) REFERENCES coacci_e1_tbl(coacci_id),"+
					"FOREIGN KEY(cod_cocldo) REFERENCES cocldo_tbl(cocldo_id),"+
					"FOREIGN KEY(cod_bitc10) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc01) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc02) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc03) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc04) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc05) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc06) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc07) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc08) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc09) REFERENCES ternaria_tbl(ternaria_id))");

			stmt.executeUpdate("CREATE TABLE e2_cuotas_tbl ("+
					"e2_cuotas_id INT AUTO_INCREMENT,"+
					"cod_codtrn VARCHAR(4) NOT NULL,"+
					"cod_cotdor INT NOT NULL,"+
					"idprov VARCHAR(9) NOT NULL,"+
					"cod_coacci CHAR NOT NULL,"+
					"cod_cocldo CHAR NOT NULL,"+
					"nudcom VARCHAR(10) NOT NULL,"+
					"coengp VARCHAR(5) NOT NULL,"+
					"cogrug INT NOT NULL,"+
					"cotaca INT NOT NULL,"+
					"cosbac INT NOT NULL,"+
					"cod_bitc11 CHAR NOT NULL,"+
					"fipago VARCHAR(8) NOT NULL,"+
					"cod_bitc12 CHAR NOT NULL,"+
					"ffpago VARCHAR(8) NOT NULL,"+
					"cod_bitc13 CHAR NOT NULL,"+
					"imcuco VARCHAR(15) NOT NULL,"+
					"cod_bitc14 CHAR NOT NULL,"+
					"faacta VARCHAR(8) NOT NULL,"+
					"cod_bitc15 CHAR NOT NULL,"+
					"ptpago INT NOT NULL,"+
					"cod_bitc09 CHAR NOT NULL,"+
					"obtexc VARCHAR(110) NOT NULL,"+
					"obdeer VARCHAR(80) NOT NULL,"+
					"PRIMARY KEY(e2_cuotas_id),"+
					"FOREIGN KEY(cod_codtrn) REFERENCES codtrn_tbl(codtrn_id),"+
					"FOREIGN KEY(cod_cotdor) REFERENCES cotdor_e2_tbl(cotdor_id),"+
					"FOREIGN KEY(cod_coacci) REFERENCES coacci_e2_tbl(coacci_id),"+
					"FOREIGN KEY(cod_cocldo) REFERENCES cocldo_tbl(cocldo_id),"+
					"FOREIGN KEY(cod_bitc11) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc12) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc13) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc14) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc15) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc09) REFERENCES ternaria_tbl(ternaria_id))");

			stmt.executeUpdate("CREATE TABLE e3_referencias_tbl ("+
					"e3_referencias_id INT AUTO_INCREMENT,"+
					"cod_codtrn VARCHAR(4) NOT NULL,"+
					"cod_cotdor INT NOT NULL,"+
					"idprov VARCHAR(9) NOT NULL,"+
					"cod_coacci CHAR NOT NULL,"+
					"coengp VARCHAR(5) NOT NULL,"+
					"nurcat VARCHAR(20) NOT NULL,"+
					"cod_bitc16 CHAR NOT NULL,"+
					"tircat VARCHAR(60) NOT NULL,"+
					"cod_bitc17 CHAR NOT NULL,"+
					"enemis VARCHAR(60) NOT NULL,"+
					"cotexa VARCHAR(10) NOT NULL,"+
					"cod_bitc09 CHAR NOT NULL,"+
					"obtexc VARCHAR(110) NOT NULL,"+
					"obdeer VARCHAR(80) NOT NULL,"+
					"PRIMARY KEY(e3_referencias_id),"+
					"FOREIGN KEY(cod_codtrn) REFERENCES codtrn_tbl(codtrn_id),"+
					"FOREIGN KEY(cod_cotdor) REFERENCES cotdor_e3_tbl(cotdor_id),"+
					"FOREIGN KEY(cod_coacci) REFERENCES coacci_e3_tbl(coacci_id),"+
					"FOREIGN KEY(cod_bitc16) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc17) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc09) REFERENCES ternaria_tbl(ternaria_id))");

			stmt.executeUpdate("CREATE TABLE e4_impuestos_tbl ("+
					"e4_impuestos_id INT AUTO_INCREMENT,"+
					"cod_codtrn VARCHAR(4) NOT NULL,"+
					"cod_cotdor INT NOT NULL,"+
					"idprov VARCHAR(9) NOT NULL,"+
					"cod_coacci CHAR NOT NULL,"+
					"coengp VARCHAR(5) NOT NULL,"+
					"nurcat VARCHAR(20) NOT NULL,"+
					"cod_cogruc INT NOT NULL,"+
					"cod_cotaca INT NOT NULL,"+
					"cod_cosbac INT NOT NULL,"+
					"cod_bitc18 CHAR NOT NULL,"+
					"feprre VARCHAR(8) NOT NULL,"+
					"cod_bitc19 CHAR NOT NULL,"+
					"ferere VARCHAR(8) NOT NULL,"+
					"cod_bitc20 CHAR NOT NULL,"+
					"fedein VARCHAR(8) NOT NULL,"+
					"cod_bitc21 CHAR NOT NULL,"+
					"cod_bisode CHAR NOT NULL,"+
					"cod_bitc22 CHAR NOT NULL,"+
					"cod_bireso CHAR NOT NULL,"+
					"cotexa VARCHAR(10) NOT NULL,"+
					"cod_bitc09 CHAR NOT NULL,"+
					"obtexc VARCHAR(110) NOT NULL,"+
					"obdeer VARCHAR(80) NOT NULL,"+
					"PRIMARY KEY(e4_impuestos_id),"+
					"FOREIGN KEY(cod_codtrn) REFERENCES codtrn_tbl(codtrn_id),"+
					"FOREIGN KEY(cod_cotdor) REFERENCES cotdor_e3_tbl(cotdor_id),"+
					"FOREIGN KEY(cod_coacci) REFERENCES coacci_e3_tbl(coacci_id),"+
					"FOREIGN KEY(cod_bitc18) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc19) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc20) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc21) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bisode) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc22) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bireso) REFERENCES unaria_tbl(unaria_id),"+
					"FOREIGN KEY(cod_bitc09) REFERENCES ternaria_tbl(ternaria_id))");
			
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
	}*/
}
