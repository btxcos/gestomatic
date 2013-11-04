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
	
	public static final String TCOCLDO = "pp001_cocldo_tbl";
	public static final String ICOCLDO = "cocldo_id";
	
	public static final String TCOSBGAT22 = "pp001_cosbga_t22_tbl";
	public static final String ICOSBGAT22 = "cosbga_id";
	
	public static final String TCOSBGAT21 = "pp001_cosbga_t21_tbl";
	public static final String ICOSBGAT21 = "cosbga_id";
	
	public static final String TCOSIGA = "pp001_cosiga_tbl";
	public static final String ICOSIGA = "cosiga_id";
	
	public static final String TPTPAGO = "pp001_ptpago_tbl";
	public static final String IPTPAGO = "ptpago_id";
	
	public static final String TSOCTIT = "pp001_sociedades_titulizadas_tbl";
	public static final String ISOCTIT = "cospat_id";	
	
	public static final String TTIACSA = "pp001_tipo_activo_sareb_tbl";
	public static final String ITIACSA = "tipo_id";
	
	public static final String TBIRESO = "pp001_bireso_tbl";
	public static final String IBIRESO = "bireso_id";

	public static final String TBINARIA = "pp001_binaria_tbl";
	public static final String IBINARIA = "binaria_id";
	
	public static final String TCOTDORE1 = "pp001_cotdor_e1_tbl";
	public static final String ICOTDORE1 = "cotdor_id";
	
	public static final String TCOTDORE2 = "pp001_cotdor_e2_tbl";
	public static final String ICOTDORE2 = "cotdor_id";
	
	public static final String TCOTDORE3 = "pp001_cotdor_e3_tbl";
	public static final String ICOTDORE3 = "cotdor_id";
	
	public static final String TCOTDORE4 = "pp001_cotdor_e4_tbl";
	public static final String ICOTDORE4 = "cotdor_id";

	public static final String TCOTERR = "pp001_coterr_tbl";
	public static final String ICOTERR = "coterr_id";
	
	public static String getDesCampo(String sTabla, String sCampo, String sValor)
	{
		Connection conn = null;
		conn = ConnectionManager.OpenDBConnection();
		
		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		String sDescripcion = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
				+ DESCRIPCION +
				" FROM " 
				+ sTabla + 
				" WHERE "
				+ sCampo + " = '" + sValor + "'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sDescripcion = rs.getString(DESCRIPCION);

					logger.debug("Encontrado el registro!");

					logger.debug(DESCRIPCION+":|"+sDescripcion+"|");
				}
			}
			if (found == false) 
			{
 
				logger.debug("No se encontr� la informaci�n.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR "+sCampo.toUpperCase()+":|"+sValor+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
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
	

		String sTabla = "";
		String sCampo  = "cosbga_id";    
		
		String sDescripcion = "";
		
		switch (Integer.parseInt(sCodCOGRUG)) 
		{
			case 1:
				switch (Integer.parseInt(sCodCOTPGA)) 
				{
					case 1:case 2:
						sTabla = "pp001_cosbga_t11_tbl";						
						break;
				}
				break;
			case 2:
				switch (Integer.parseInt(sCodCOTPGA)) 
				{
					case 1:
						sTabla = "pp001_cosbga_t21_tbl";
						break;
					case 2:
						sTabla = "pp001_cosbga_t22_tbl";
						break;
					case 3:
						sTabla = "pp001_cosbga_t23_tbl";
						break;
				}
				break;
			case 3:
				switch (Integer.parseInt(sCodCOTPGA)) 
				{
					case 2:
						sTabla = "pp001_cosbga_32_tbl";
						break;
					case 3:
						sTabla = "";
						sDescripcion = "Obtenci�n de Licencias";
						break;
				}
				break;
		}
		

		if (!sTabla.equals(""))
		{
			Connection conn = null;

			conn = ConnectionManager.OpenDBConnection();
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ DESCRIPCION + 
					" FROM " 
					+ sTabla + 
					" WHERE "
					+ sCampo + " = '" + sCodCOSBGA + "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conn.createStatement();


				pstmt = conn.prepareStatement(sQuery);

				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");
				
				
				if (rs != null) 
				{
					
					while (rs.next()) 
					{
						found = true;

						sDescripcion = rs.getString(DESCRIPCION);

						logger.debug("Encontrado el registro!");

						logger.debug(DESCRIPCION+":|"+sDescripcion+"|");


					}
				}
				if (found == false) 
				{
	 
					logger.debug("No se encontr� la informaci�n.");
				}

			} 
			catch (SQLException ex) 
			{
				logger.error("ERROR COGRUG:|"+sCodCOGRUG+"|");
				logger.error("ERROR COTPGA:|"+sCodCOTPGA+"|");
				logger.error("ERROR COSBGA:|"+sCodCOSBGA+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
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
