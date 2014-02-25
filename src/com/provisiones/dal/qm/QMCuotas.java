package com.provisiones.dal.qm;

import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Cuota;
import com.provisiones.types.tablas.CuotaTabla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class QMCuotas
{
	private static Logger logger = LoggerFactory.getLogger(QMCuotas.class.getName());
	
	public static final String TABLA = "pp002_e2_cuotas_tbl";

	//Primary Key
	public static final String CAMPO1  = "e2_cuota_id";
	
	//Unique Key - cuota
	public static final String CAMPO2  = "cod_coaces";
	public static final String CAMPO3  = "cod_cocldo";
	public static final String CAMPO4  = "cod_nudcom";
	public static final String CAMPO5  = "cod_cosbac";
	
	//Campos secundarios
	public static final String CAMPO6  = "fipago";    
	public static final String CAMPO7  = "ffpago";    
	public static final String CAMPO8  = "imcuco";    
	public static final String CAMPO9  = "faacta";    
	public static final String CAMPO10 = "cod_ptpago";
	public static final String CAMPO11 = "obtexc";
	
	//Campos de control
	public static final String CAMPO12 = "cod_estado";
	public static final String CAMPO13 = "nota";

	private QMCuotas(){}
	
	public static long addCuota(Connection conexion, Cuota NuevaCuota)
	{
		long liCodigo = 0;

		if (conexion != null)
		{
			Statement stmt = null;
			ResultSet resulset = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "INSERT INTO " + TABLA + " ("
				       + CAMPO2  + ","              
				       + CAMPO3  + ","              
				       + CAMPO4  + ","              
				       + CAMPO5  + ","              
				       + CAMPO6  + ","              
				       + CAMPO7  + ","              
				       + CAMPO8  + ","
				       + CAMPO9  + ","
				       + CAMPO10  + ","
					   + CAMPO11  + ","
					   + CAMPO12  + "," 
				       + CAMPO13  + 
				       ") VALUES ('"
				       + NuevaCuota.getCOACES() + "','"
				       + NuevaCuota.getCOCLDO() + "','"
				       + NuevaCuota.getNUDCOM() + "','"
				       + NuevaCuota.getCOSBAC() + "','"
				       + NuevaCuota.getFIPAGO() + "','"
				       + NuevaCuota.getFFPAGO() + "','"
				       + NuevaCuota.getIMCUCO() + "','"
				       + NuevaCuota.getFAACTA() + "','"
				       + NuevaCuota.getPTPAGO() + "','"
				       + NuevaCuota.getOBTEXC() + "','" 
				       + ValoresDefecto.DEF_ALTA + "','"
				       + ValoresDefecto.CAMPO_ALFA_SIN_INFORMAR + 
				       "')";

			logger.debug(sQuery);

			try 
			{

				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery, Statement.RETURN_GENERATED_KEYS);
				
				resulset = stmt.getGeneratedKeys();
				
				if (resulset.next()) 
				{
					liCodigo= resulset.getLong(1);
				} 

				logger.debug("Ejecutada con exito!");
				
				logger.debug("Ejecutada con exito!");
			}
			catch (SQLException ex)
			{
				liCodigo = 0;

				logger.error("ERROR COACES:|"+NuevaCuota.getCOACES()+"|");
				logger.error("ERROR COCLDO:|"+NuevaCuota.getCOCLDO()+"|");
				logger.error("ERROR NUDCOM:|"+NuevaCuota.getNUDCOM()+"|");
				logger.error("ERROR COSBAC:|"+NuevaCuota.getCOSBAC()+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
			}
		}

		return liCodigo;
	}
	public static boolean modCuota(Connection conexion, Cuota NuevaCuota, long liCuotaID)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO6  + " = '"+ NuevaCuota.getFIPAGO() + "', "
					+ CAMPO7  + " = '"+ NuevaCuota.getFFPAGO() + "', "
					+ CAMPO8  + " = '"+ NuevaCuota.getIMCUCO() + "', "
					+ CAMPO9  + " = '"+ NuevaCuota.getFAACTA() + "', "
					+ CAMPO10  + " = '"+ NuevaCuota.getPTPAGO() + "', "
					+ CAMPO11 + " = '"+ NuevaCuota.getOBTEXC() + "' "+
					" WHERE " 
					+ CAMPO1  + " = '"+ liCuotaID +"'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con exito!");
				
				bSalida = true;
			} 
			catch (SQLException ex) 
			{
				bSalida = false;

				logger.error("ERROR COACES:|"+NuevaCuota.getCOACES()+"|");
				logger.error("ERROR COCLDO:|"+NuevaCuota.getCOCLDO()+"|");
				logger.error("ERROR NUDCOM:|"+NuevaCuota.getNUDCOM()+"|");
				logger.error("ERROR COSBAC:|"+NuevaCuota.getCOSBAC()+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}			
		}

		return bSalida;
	}

	public static boolean delCuota(Connection conexion, long liCuotaID)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO1  + " = '"+ liCuotaID +"'";

			logger.debug(sQuery);
			
			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con exito!");
				
				bSalida = true;
				
			} 
			catch (SQLException ex) 
			{
				bSalida = false;

				logger.error("ERROR Cuota:|"+liCuotaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean existeCuota(Connection conexion, long liCuotaID)
	{
		boolean bEncontrado = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO1 + 
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1  + " = '"+ liCuotaID +"'";

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

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR Cuota:|"+liCuotaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}	
		}

		return bEncontrado;
	}

	public static Cuota getCuota(Connection conexion, long liCuotaID)
	{
		String sCOACES = "";
		String sCOCLDO = "";
		String sNUDCOM = "";
		String sCOSBAC = "";
		String sFIPAGO = "";
		String sFFPAGO = "";
		String sIMCUCO = "";
		String sFAACTA = "";
		String sPTPAGO = "";
		String sOBTEXC = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
					   + CAMPO2  + ","              
				       + CAMPO3  + ","              
				       + CAMPO4  + ","              
				       + CAMPO5  + ","              
				       + CAMPO6  + ","              
				       + CAMPO7  + ","              
				       + CAMPO8  + ","
				       + CAMPO9  + ","
					   + CAMPO10  + ","
				       + CAMPO11  +       
				       " FROM " 
				       + TABLA + 
				       " WHERE " 
				       + CAMPO1  + " = '"+ liCuotaID +"'";

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

						sCOACES = rs.getString(CAMPO2); 
						sCOCLDO = rs.getString(CAMPO3); 
						sNUDCOM = rs.getString(CAMPO4); 
						sCOSBAC = rs.getString(CAMPO5); 
						sFIPAGO = rs.getString(CAMPO6); 
						sFFPAGO = rs.getString(CAMPO7); 
						sIMCUCO = rs.getString(CAMPO8); 
						sFAACTA = rs.getString(CAMPO9); 
						sPTPAGO = rs.getString(CAMPO10);
						sOBTEXC = rs.getString(CAMPO11);
						
						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				sCOACES = "";
				sCOCLDO = "";
				sNUDCOM = "";
				sCOSBAC = "";
				sFIPAGO = "";
				sFFPAGO = "";
				sIMCUCO = "";
				sFAACTA = "";
				sPTPAGO = "";
				sOBTEXC = "";

				logger.error("ERROR Cuota:|"+liCuotaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new Cuota(sCOACES, sCOCLDO, sNUDCOM, sCOSBAC, sFIPAGO, sFFPAGO, sIMCUCO, sFAACTA, sPTPAGO, sOBTEXC);
	}
	
	public static Cuota getDetallesCuota(Connection conexion, long liCuotaID)
	{
		String sCOACES = "";
		String sCOCLDO = "";
		String sNUDCOM = "";
		String sCOSBAC = "";
		String sFIPAGO = "";
		String sFFPAGO = "";
		String sIMCUCO = "";
		String sFAACTA = "";
		String sPTPAGO = "";
		String sOBTEXC = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
					   + CAMPO2  + ","              
				       + CAMPO3  + ","              
				       + CAMPO4  + ","              
				       + CAMPO5  + ","              
				       + CAMPO6  + ","              
				       + CAMPO7  + ","              
				       + CAMPO8  + ","
				       + CAMPO9  + ","
					   + CAMPO10  + ","
				       + CAMPO11  +       
				       " FROM " 
				       + TABLA + 
				       " WHERE " 
				       + CAMPO1  + " = '"+ liCuotaID +"'";

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

						sCOACES = rs.getString(CAMPO2); 
						sCOCLDO = rs.getString(CAMPO3); 
						sNUDCOM = rs.getString(CAMPO4); 
						sCOSBAC = rs.getString(CAMPO5); 
						sFIPAGO = rs.getString(CAMPO6); 
						sFFPAGO = rs.getString(CAMPO7); 
						sIMCUCO = rs.getString(CAMPO8); 
						sFAACTA = rs.getString(CAMPO9); 
						sPTPAGO = rs.getString(CAMPO10);
						sOBTEXC = rs.getString(CAMPO11);
						
						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				sCOACES = "";
				sCOCLDO = "";
				sNUDCOM = "";
				sCOSBAC = "";
				sFIPAGO = "";
				sFFPAGO = "";
				sIMCUCO = "";
				sFAACTA = "";
				sPTPAGO = "";
				sOBTEXC = "";

				logger.error("ERROR Cuota:|"+liCuotaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new Cuota(sCOACES, sCOCLDO, sNUDCOM, sCOSBAC, sFIPAGO, sFFPAGO, sIMCUCO, sFAACTA, sPTPAGO, sOBTEXC);
	}
	
	public static long getCuotaID(Connection conexion, int iCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC)
	{
		long liCuotaID = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
					   + CAMPO1 +       
				       " FROM " 
					   + TABLA + 
					   " WHERE ("	
					   + CAMPO2  + " = '"+ iCOACES +"' AND " 
					   + CAMPO3  + " = '"+ sCodCOCLDO +"' AND " 
					   + CAMPO4  + " = '"+ sCodNUDCOM +"' AND " 
					   + CAMPO5  + " = '"+ sCodCOSBAC + "')";

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

						liCuotaID = rs.getLong(CAMPO1);
						
						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				liCuotaID = 0;

				logger.error("ERROR COACES:|"+iCOACES+"|");
				logger.error("ERROR COCLDO:|"+sCodCOCLDO+"|");
				logger.error("ERROR NUDCOM:|"+sCodNUDCOM+"|");
				logger.error("ERROR COSBAC:|"+sCodCOSBAC+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return liCuotaID;
	}
	
	public static boolean tieneCuotasActivo(Connection conexion, int iCOACES)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "              
				       + CAMPO3  +       
				       " FROM " 
				       + TABLA + 
				       " WHERE ("
				       + CAMPO2  + " = '"+ iCOACES +"' AND "
				       + CAMPO12  + " <> 'B')";
			
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

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR COACES:|"+iCOACES+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}			
		}

		return bEncontrado;
	}
	
	public static boolean tieneCuotasComunidad(Connection conexion, String sCodCOCLDO, String sCodNUDCOM)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "              
				       + CAMPO3  +       
				       " FROM " 
				       + TABLA + 
				       " WHERE ("
				       + CAMPO3  + " = '"+ sCodCOCLDO +"' AND "
				       + CAMPO4  + " = '"+ sCodNUDCOM +"' AND " 
				       + CAMPO12  + " <> 'B')";
			
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

						logger.debug("Encontrado el registro!");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR COCLDO:|"+sCodCOCLDO+"|");
				logger.error("ERROR NUDCOM:|"+sCodNUDCOM+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}			
		}

		return bEncontrado;
	}
	
	public static ArrayList<CuotaTabla> buscaCuotasActivo(Connection conexion, int iCOACES)
	{
		ArrayList<CuotaTabla> resultado = new ArrayList<CuotaTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
	
			String sCOACES = "";
			String sCOCLDO = "";
			String sDesCOCLDO = "";
			String sNUDCOM = "";
			String sCOSBAC = "";
			String sDesCOSBAC = "";
			String sFIPAGO = "";
			String sFFPAGO = "";
			String sIMCUCO = "";
			String sFAACTA = "";
			String sPTPAGO = "";
			String sDesPTPAGO = "";
			String sOBTEXC = "";
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
						   + CAMPO2 + ","
						   + CAMPO3 + ","
						   + CAMPO4 + ","
						   + CAMPO5 + ","
						   + CAMPO6 + ","
						   + CAMPO7 + ","
						   + CAMPO8 + ","
						   + CAMPO9 + ","
						   + CAMPO10 + "," 
						   + CAMPO11 + ","
						   + CAMPO12 +
						   " FROM " 
						   + TABLA + 
						   " WHERE ("
						   + CAMPO12 + " = '" + ValoresDefecto.DEF_ALTA + "' AND "
						   + CAMPO2 + " = '" + iCOACES	+ "')";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();
				
				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;
						
						sCOACES		= rs.getString(CAMPO2);
						sCOCLDO     = rs.getString(CAMPO3);
						sDesCOCLDO  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOCLDO, QMCodigosControl.ICOCLDO, sCOCLDO);
						sNUDCOM     = rs.getString(CAMPO4);
						sCOSBAC     = rs.getString(CAMPO5);
						sDesCOSBAC  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSBGAT22,QMCodigosControl.ICOSBGAT22,sCOSBAC);
						sFIPAGO     = Utils.recuperaFecha(rs.getString(CAMPO6));
						sFFPAGO     = Utils.recuperaFecha(rs.getString(CAMPO7));
						sIMCUCO     = Utils.recuperaImporte(false,rs.getString(CAMPO8));
						sFAACTA     = Utils.recuperaFecha(rs.getString(CAMPO9));
						sPTPAGO     = rs.getString(CAMPO10);
						sDesPTPAGO  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
						sOBTEXC     = rs.getString(CAMPO11);  

						
						CuotaTabla cuotaencontrada = new CuotaTabla(
								sCOACES,
								sCOCLDO,
								sDesCOCLDO,
								sNUDCOM,
								sCOSBAC,
								sDesCOSBAC,
								sFIPAGO,
								sFFPAGO,
								sIMCUCO,
								sFAACTA,
								sPTPAGO,
								sDesPTPAGO,
								sOBTEXC);
						
						resultado.add(cuotaencontrada);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+iCOACES+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<CuotaTabla>();

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return resultado;
	}

	public static ArrayList<CuotaTabla> buscaCuotasComunidad(Connection conexion, String sCodCOCLDO, String sCodNUDCOM)
	{
		ArrayList<CuotaTabla> resultado = new ArrayList<CuotaTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String sCOACES = "";
			String sCOCLDO = "";
			String sDesCOCLDO = "";
			String sNUDCOM = "";
			String sCOSBAC = "";
			String sDesCOSBAC = "";
			String sFIPAGO = "";
			String sFFPAGO = "";
			String sIMCUCO = "";
			String sFAACTA = "";
			String sPTPAGO = "";
			String sDesPTPAGO = "";
			String sOBTEXC = "";
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
						   + CAMPO2 + ","
						   + CAMPO3 + ","
						   + CAMPO4 + ","
						   + CAMPO5 + ","
						   + CAMPO6 + ","
						   + CAMPO7 + ","
						   + CAMPO8 + ","
						   + CAMPO9 + ","
						   + CAMPO10 + "," 
						   + CAMPO11 + ","
						   + CAMPO12 +
						   " FROM " 
						   + TABLA + 
						   " WHERE ("
						   + CAMPO12 + " = '" + ValoresDefecto.DEF_ALTA + "' AND "
						   + CAMPO3  + " = '"+ sCodCOCLDO +"' AND "
						   + CAMPO4  + " = '"+ sCodNUDCOM +"')";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();
				
				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con éxito!");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;
						
						sCOACES		= rs.getString(CAMPO2);
						sCOCLDO     = rs.getString(CAMPO3);
						sDesCOCLDO  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOCLDO, QMCodigosControl.ICOCLDO, sCOCLDO);
						sNUDCOM     = rs.getString(CAMPO4);
						sCOSBAC     = rs.getString(CAMPO5);
						sDesCOSBAC  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOSBGAT22,QMCodigosControl.ICOSBGAT22,sCOSBAC);
						sFIPAGO     = Utils.recuperaFecha(rs.getString(CAMPO6));
						sFFPAGO     = Utils.recuperaFecha(rs.getString(CAMPO7));
						sIMCUCO     = Utils.recuperaImporte(false,rs.getString(CAMPO8));
						sFAACTA     = Utils.recuperaFecha(rs.getString(CAMPO9));
						sPTPAGO     = rs.getString(CAMPO10);
						sDesPTPAGO  = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
						sOBTEXC     = rs.getString(CAMPO11);  

						
						CuotaTabla cuotaencontrada = new CuotaTabla(
								sCOACES,
								sCOCLDO,
								sDesCOCLDO,
								sNUDCOM,
								sCOSBAC,
								sDesCOSBAC,
								sFIPAGO,
								sFFPAGO,
								sIMCUCO,
								sFAACTA,
								sPTPAGO,
								sDesPTPAGO,
								sOBTEXC);
						
						resultado.add(cuotaencontrada);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO3+":|"+sDesCOCLDO+"|");
						logger.debug(CAMPO4+":|"+sNUDCOM+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}

			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<CuotaTabla>();
				
				logger.error("COMUNIDAD:|"+sCodCOCLDO+"|"+sCodNUDCOM+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return resultado;
	}
	
	public static boolean setEstado(Connection conexion, long liCuotaID, String sEstado)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO12 + " = '"+ sEstado +"' "+
					" WHERE "
					+ CAMPO1  + " = '"+ liCuotaID +"'";
			
			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con exito!");
				
				bSalida = true;
		
			} 
			catch (SQLException ex) 
			{
				bSalida = false;

				logger.error("ERROR Cuota:|"+liCuotaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static String getEstado(Connection conexion, long liCuotaID)
	{
		String sEstado = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT " 
					+ CAMPO12 + 
					" FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO1  + " = '"+ liCuotaID +"'"; 

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

						sEstado = rs.getString(CAMPO12);
						
						logger.debug("Encontrado el registro!");
						
						logger.debug(CAMPO12+":|"+sEstado+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sEstado = "";

				logger.error("ERROR Cuota:|"+liCuotaID+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sEstado;
	}
	
	public static boolean setNota(Connection conexion, long liCuotaID, String sNota)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO13 + " = '"+ sNota +"' "+
					" WHERE "
					+ CAMPO1 + " = '"+ liCuotaID +"'";
			
			logger.debug(sQuery);
			
			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con exito!");
				
				bSalida = true;
				
			} 
			catch (SQLException ex) 
			{
				bSalida = false;

				logger.error("ERROR CUOTA:|"+liCuotaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

			} 
			finally 
			{

				Utils.closeStatement(stmt);
			}			
		}

		return bSalida;
	}
	
	public static String getNota(Connection conexion, long liCuotaID)
	{
		String sNota = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
						+ CAMPO13 + 
						" FROM " 
						+ TABLA + 
						" WHERE "
						+ CAMPO1 + " = '"+ liCuotaID +"'";
			
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

						sNota = rs.getString(CAMPO13);
						
						logger.debug(CAMPO1+":|"+liCuotaID+"|");
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO13+":|"+sNota+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sNota = "";
				
				logger.error("ERROR CUOTA:|"+liCuotaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sNota;
	}
}
