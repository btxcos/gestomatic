package com.provisiones.dal.qm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.misc.Utils;

public final class QMCodigosControl 
{
	private static Logger logger = LoggerFactory.getLogger(QMCodigosControl.class.getName());

	public static final String DESCRIPCION = "descripcion";
	
	public static final String TESWIFT = "pp001_entidades_swift_tbl";
	public static final String IESWIFT = "entidad_id";
	public static final String SESWIFT = "swift";
	
	public static final String TCOCLDO = "pp001_cocldo_tbl";
	public static final String ICOCLDO = "cocldo_id";
	
	public static final String TCOGRUG = "pp001_cogrug_tbl";
	public static final String ICOGRUG = "cogrug_id";
	
	
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

	public static final String TCOSOPA = "pp001_cosopa_tbl";
	public static final String ICOSOPA = "cosopa_id";

	public static final String TCOENAE = "pp001_coenae_tbl";
	public static final String ICOENAE = "coenae_id";
	
	public static final String TCOESEN = "pp001_coesen_tbl";
	public static final String ICOESEN = "coesen_id";

	public static final String TCOREAE = "pp001_coreae_tbl";
	public static final String ICOREAE = "coreae_id";

	public static final String TCODIJU = "pp001_codiju_tbl";
	public static final String ICODIJU = "codiju_id";
	
	public static final String TCOSJUP = "pp001_cosjup_tbl";
	public static final String ICOSJUP = "cosjup_id";
	
	public static final String TCOSTLI = "pp001_costli_tbl";
	public static final String ICOSTLI = "costli_id";
	
	public static final String TCOSCAR = "pp001_coscar_tbl";
	public static final String ICOSCAR = "coscar_id";
	
	public static final String TCOESVE = "pp001_coesve_tbl";
	public static final String ICOESVE = "coesve_id";
	
	public static final String TCOTSIN = "pp001_cotsin_tbl";
	public static final String ICOTSIN = "cotsin_id";
	
	public static final String TCOSOCU = "pp001_cosocu_tbl";
	public static final String ICOSOCU = "cosocu_id";
	
	public static final String TCOXPRO = "pp001_coxpro_tbl";
	public static final String ICOXPRO = "coxpro_id";
	
	public static final String TCOGRAP = "pp001_cograp_tbl";
	public static final String ICOGRAP = "cograp_id";

	public static final String TCOTPET = "pp001_cotpet_tbl";
	public static final String ICOTPET = "cotpet_id";

	public static final String TCOXSIA = "pp001_coxsia_tbl";
	public static final String ICOXSIA = "coxsia_id";
	
	public static final String TCOSPAT = "pp001_cospat_tbl";
	public static final String ICOSPAT = "cospat_id";
	
	public static final String TBIOBNU = "pp001_biobnu_tbl";
	public static final String IBIOBNU = "biobnu_id";
	
	public static final String TESGAST = "pp001_estados_gasto_tbl";
	public static final String IESGAST = "estado_gasto_id";
	
	public static final String TCOIMPT = "pp001_coimpt_tbl";
	public static final String ICOIMPT = "coimpt_id";
	
	public static final String TCOTNEG = "pp001_cotneg_tbl";
	public static final String ICOTNEG = "cotneg_id";
	
	public static final String TCOMONA = "pp001_comona_tbl";
	public static final String ICOMONA = "comona_id";
	
	public static final String TBIAUTO = "pp001_biauto_tbl";
	public static final String IBIAUTO = "biauto_id";
	
	public static final String TESPROF = "pp001_estados_provision_tbl";
	public static final String IESPROF = "estado_provision_id";
	
	public static final String TESACTI = "pp001_estados_activo_tbl";
	public static final String IESACTI = "estado_activo_id";	

	public static final String TCOPAGO = "pp001_tipo_pago_tbl";
	public static final String ICOPAGO = "tipo_id";
	
	
	private QMCodigosControl(){}
	
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
				if (!bEncontrado) 
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
	
	public static String getDesCOTPGA(Connection conexion, String sCodCOGRUG, String sCodCOTPGA)
	{
		String sDescripcion = "";

		if ((conexion != null) || (sCodCOGRUG.equals("0")))
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
		
			String sTabla = "pp001_cotpga_g"+sCodCOGRUG+"_tbl";
			String sCampo  = "cotpga_id";    

			if (!sTabla.equals(""))
			{
				
				logger.debug("Ejecutando Query...");
				
				String sQuery = "SELECT " 
						+ DESCRIPCION + 
						" FROM " 
						+ sTabla + 
						" WHERE "
						+ sCampo + " = '" + sCodCOTPGA + "'";
				
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
					if (!bEncontrado) 
					{
		 
						logger.debug("No se encontró la información.");
					}

				} 
				catch (SQLException ex) 
				{
					sDescripcion = "";

					logger.error("ERROR COGRUG:|"+sCodCOGRUG+"|");
					logger.error("ERROR COTPGA:|"+sCodCOTPGA+"|");

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
							sTabla = "pp001_cosbga_t32_tbl";
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
					if (!bEncontrado) 
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
	
	public static String getSWIFT(Connection conexion, String sEntidad)
	{
		String sSWIFT = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					+ SESWIFT +
					" FROM " 
					+ TESWIFT + 
					" WHERE "
					+ IESWIFT + " = '" + sEntidad + "'";
			
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

						sSWIFT = rs.getString(SESWIFT);

						logger.debug("Encontrado el registro!");

						logger.debug(SESWIFT+":|"+sSWIFT+"|");
					}
				}
				if (!bEncontrado) 
				{
	 
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				sSWIFT = "";

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sSWIFT;
	}
}
