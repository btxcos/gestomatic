package com.provisiones.dal.qm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;

public class QMCodigosControl 
{

	private static Logger logger = LoggerFactory.getLogger(QMCodigosControl.class.getName());

	public static final String DESCRIPCION = "descripcion";
	
	public static final String TCOCLDO = "cocldo_tbl";
	public static final String ICOCLDO = "cocldo_id";
	
	public static final String TCOSBGAT22 = "cosbga_t22_tbl";
	public static final String ICOSBGAT22 = "cosbga_id";
	
	public static final String TCOSBGAT21 = "cosbga_t21_tbl";
	public static final String ICOSBGAT21 = "cosbga_id";
	
	public static final String TCOSIGA = "cosiga_tbl";
	public static final String ICOSIGA = "cosiga_id";
	
	public static final String TPTPAGO = "ptpago_tbl";
	public static final String IPTPAGO = "ptpago_id";
	
	public static final String TSOCTIT = "sociedades_titulizadas_tbl";
	public static final String ISOCTIT = "cospat_id";	
	
	public static final String TTIACSA = "tipo_activo_sareb_tbl";
	public static final String ITIACSA = "tipo_id";
	
	public static final String TBIRESO = "bireso_tbl";
	public static final String IBIRESO = "bireso_id";

	public static final String TBINARIA = "binaria_tbl";
	public static final String IBINARIA = "binaria_id";
	
	public static final String TCOTDORE1 = "cotdor_e1_tbl";
	public static final String ICOTDORE1 = "cotdor_id";
	
	public static final String TCOTDORE2 = "cotdor_e2_tbl";
	public static final String ICOTDORE2 = "cotdor_id";
	
	public static final String TCOTDORE3 = "cotdor_e3_tbl";
	public static final String ICOTDORE3 = "cotdor_id";
	
	public static final String TCOTDORE4 = "cotdor_e4_tbl";
	public static final String ICOTDORE4 = "cotdor_id";
	
	public static String getDesCampo(String sTable, String sField1, String sValor)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();
		
		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		String sDescripcion = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT "+DESCRIPCION+" FROM " + sTable + 
					" WHERE "
					+ sField1 + " = '" + sValor + "'");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sDescripcion = rs.getString(DESCRIPCION);

					logger.debug("Encontrado el registro!");

					logger.debug("{}:|{}|",DESCRIPCION,sDescripcion);
				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR: {}:|{}|",sField1.toUpperCase(),sValor);

			logger.error("ERROR: SQLException:{}",ex.getMessage());
			logger.error("ERROR: SQLState:{}",ex.getSQLState());
			logger.error("ERROR: VendorError:{}",ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		ConnectionManager.CloseDBConnection(conn);
		return sDescripcion;
	}
	
	public static String getDesCOSBGA(String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA)
	{
		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sTable = "";
		String sField1  = "cosbga_id";    
		
		String sDescripcion = "";
		
		switch (Integer.parseInt(sCodCOGRUG)) 
		{
			case 1:
				switch (Integer.parseInt(sCodCOTPGA)) 
				{
					case 1:case 2:
						sTable = "cosbga_t11_tbl";						
						break;
				}
				break;
			case 2:
				switch (Integer.parseInt(sCodCOTPGA)) 
				{
					case 1:
						sTable = "cosbga_t21_tbl";
						break;
					case 2:
						sTable = "cosbga_t22_tbl";
						break;
					case 3:
						sTable = "cosbga_t23_tbl";
						break;
				}
				break;
			case 3:
				switch (Integer.parseInt(sCodCOTPGA)) 
				{
					case 2:
						sTable = "cosbga_32_tbl";
						break;
					case 3:
						sTable = "";
						sDescripcion = "Obtencion de Licencias";
						break;
				}
				break;
		}
		

		if (!sTable.equals(""))
		{
			Connection conn = null;

			conn = ConnectionManager.OpenDBConnection();
			
			logger.debug("Ejecutando Query...");

			try 
			{
				stmt = conn.createStatement();


				pstmt = conn.prepareStatement("SELECT " + DESCRIPCION + "  FROM " + sTable + 
						" WHERE " +
						"(" + sField1 + " = '" + sCodCOSBGA + "')");

				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");
				
				
				if (rs != null) 
				{
					
					while (rs.next()) 
					{
						found = true;

						sDescripcion = rs.getString(DESCRIPCION);

						logger.debug("Encontrado el registro!");

						logger.debug("{}:|{}|",DESCRIPCION,sDescripcion);


					}
				}
				if (found == false) 
				{
	 
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				logger.error("ERROR: COGRUG:|{}|",sCodCOGRUG);
				logger.error("ERROR: COTPGA:|{}|",sCodCOTPGA);
				logger.error("ERROR: COSBGA:|{}|",sCodCOSBGA);

				logger.error("ERROR: SQLException:{}",ex.getMessage());
				logger.error("ERROR: SQLState:{}",ex.getSQLState());
				logger.error("ERROR: VendorError:{}",ex.getErrorCode());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}

			ConnectionManager.CloseDBConnection(conn);
		}
		return sDescripcion;
	}
}
