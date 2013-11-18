package com.provisiones.dal.qm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	public static String getDesCampo(Connection conexion, String sTabla, String sCampo, String sValor)
	{
		String sDescripcion = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

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
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");
				
				
				if (rs != null) 
				{
					
					while (rs.next()) 
					{
						bEncontrado = true;

						sDescripcion = rs.getString(DESCRIPCION);

						logger.debug("Encontrado el registro!");

						logger.debug(DESCRIPCION+":|"+sDescripcion+"|");
					}
				}
				if (bEncontrado == false) 
				{
	 
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				sDescripcion = "";

				logger.error("ERROR "+sCampo.toUpperCase()+":|"+sValor+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sDescripcion;
	}
	
	public static String getDesCOSBGA(Connection conexion, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA)
	{
		String sDescripcion = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
		
			String sTabla = "";
			String sCampo  = "cosbga_id";    
			
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
							sDescripcion = "Obtención de Licencias";
							break;
					}
					break;
			}

			if (!sTabla.equals(""))
			{
				
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
					stmt = conexion.createStatement();

					pstmt = conexion.prepareStatement(sQuery);
					rs = pstmt.executeQuery();
					
					logger.debug("Ejecutada con exito!");
					
					
					if (rs != null) 
					{
						
						while (rs.next()) 
						{
							bEncontrado = true;

							sDescripcion = rs.getString(DESCRIPCION);

							logger.debug("Encontrado el registro!");

							logger.debug(DESCRIPCION+":|"+sDescripcion+"|");


						}
					}
					if (bEncontrado == false) 
					{
		 
						logger.debug("No se encontró la información.");
					}

				} 
				catch (SQLException ex) 
				{
					sDescripcion = "";

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
			}
		}

		return sDescripcion;
	}
}
