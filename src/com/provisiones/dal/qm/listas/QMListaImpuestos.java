package com.provisiones.dal.qm.listas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMCodigosControl;
import com.provisiones.dal.qm.QMImpuestos;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.ImpuestoRecursoTabla;

public class QMListaImpuestos 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaImpuestos.class.getName());

	public static final String TABLA = "pp001_lista_impuestos_multi";
	
	//identificadores
	public static final String CAMPO1 = "cod_coaces";
	public static final String CAMPO2 = "cod_impuesto";
	public static final String CAMPO3 = "cod_movimiento";

	//Campos de control
	public static final String CAMPO4 = "cod_validado";
	public static final String CAMPO5 = "usuario_movimiento";
	public static final String CAMPO6  = "fecha_movimiento";    


	public static boolean addRelacionImpuestos(Connection conexion, String sCodCOACES, String sCodImpuesto, String sCodMovimiento) 
	{
		boolean bSalida = true;

		String sUsuario = ConnectionManager.getUser();

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "INSERT INTO " 
					+ TABLA + 
					" (" 
					+ CAMPO1 + ","
					+ CAMPO2 + ","
					+ CAMPO3 + "," 
					+ CAMPO4 + "," 
					+ CAMPO5 + ","
					+ CAMPO6 + 
					") VALUES ('" 
					+ sCodCOACES + "','"
					+ sCodImpuesto + "','"
					+ sCodMovimiento + "','"
					+ ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE + "','"
				    + sUsuario + "','"
				    + Utils.timeStamp() +
					"')";
			
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
				
				logger.error("ERROR COACES:|"+sCodCOACES+"|");
				logger.error("ERROR Impuesto:|"+sCodImpuesto+"|");
				logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean delRelacionImpuestos(Connection conexion, String sCodMovimiento) 
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO3 + " = '" + sCodMovimiento +"'";
			
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
				
				logger.error("ERROR CodMovimiento:|"+sCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean existeRelacionImpuesto(Connection conexion, String sCodCOACES, String sCodImpuesto, String sCodMovimiento)
	{
		boolean bEncontrado = false;
		
		Statement stmt = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO4 + 
				" FROM " 
				+ TABLA + 
				" WHERE (" 
				+ CAMPO1 + " = '" + sCodCOACES + "' AND "
				+ CAMPO2 + " = '" + sCodImpuesto + "' AND " 
				+ CAMPO3 + " = '" + sCodMovimiento +
				"')";
		
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

			logger.error("ERROR COACES:|"+sCodCOACES+"|");
			logger.error("ERROR Impuesto:|"+sCodImpuesto+"|");
			logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		return bEncontrado;
	}

	public static boolean compruebaRelacionImpuestoActivo(Connection conexion, String sCodCOACES, String sCodImpuesto)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO4 + 
					" FROM " 
					+ TABLA + 
					" WHERE (" 
					+ CAMPO1 + " = '" + sCodCOACES + "' AND " 
					+ CAMPO2 + " = '" + sCodImpuesto + 
					"')";
			
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
						logger.debug(CAMPO1+"|"+sCodCOACES+"|");
						logger.debug(CAMPO2+":|"+sCodImpuesto+"|");
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

				logger.error("ERROR COACES:|"+sCodCOACES+"|");
				logger.error("ERROR Impuesto:|"+sCodImpuesto+"|");

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
	
	public static ArrayList<String>  getImpuestosPorEstado(Connection conexion, String sEstado) 
	{
		ArrayList<String> resultado = new ArrayList<String>(); 

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO3 + 
					" FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO4 + " = '" + sEstado + "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");
				
				int i = 0;
				
				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						resultado.add(rs.getString(CAMPO3));
											
						logger.debug("Encontrado el registro!");
						
						logger.debug(CAMPO4+":|"+sEstado+"|");
						logger.debug(CAMPO3+":|"+resultado.get(i)+"|");

						i++;
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<String>(); 

				logger.error("ERROR Validado:|"+sEstado+"|");

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
	
	public static ArrayList<ActivoTabla> buscaActivosAsociados(Connection conexion, ActivoTabla activo)
	{
		ArrayList<ActivoTabla> resultado = new ArrayList<ActivoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String sCOACES = "";
			String sCOPOIN = "";
			String sNOMUIN = "";
			String sNOPRAC = "";
			String sNOVIAS = "";
			String sNUPIAC = "";
			String sNUPOAC = "";
			String sNUPUAC = "";
			String sNURCAT = "";
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						
						   + QMActivos.CAMPO1 + ","        
						   + QMActivos.CAMPO14 + ","
						   + QMActivos.CAMPO11 + ","
						   + QMActivos.CAMPO13 + ","
						   + QMActivos.CAMPO6 + ","
						   + QMActivos.CAMPO9 + ","
						   + QMActivos.CAMPO7 + ","
						   + QMActivos.CAMPO10 + ","
						   + QMActivos.CAMPO81 + 

						   " FROM " 
						   + QMActivos.TABLA + 
						   " WHERE ("

						   + QMActivos.CAMPO14 + " LIKE '%" + activo.getCOPOIN()	+ "%' AND "  
						   + QMActivos.CAMPO11 + " LIKE '%" + activo.getNOMUIN()	+ "%' AND "  
						   + QMActivos.CAMPO13 + " LIKE '%" + activo.getNOPRAC()	+ "%' AND "  
						   + QMActivos.CAMPO6 + " LIKE '%" + activo.getNOVIAS()	+ "%' AND "  
						   + QMActivos.CAMPO9 + " LIKE '%" + activo.getNUPIAC()	+ "%' AND "  
						   + QMActivos.CAMPO7 + " LIKE '%" + activo.getNUPOAC()	+ "%' AND "  
						   + QMActivos.CAMPO10 + " LIKE '%" + activo.getNUPUAC()	+ "%' AND "			

						   + QMActivos.CAMPO1 +" IN (SELECT "
						   +  CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE " 
						   
						   + CAMPO2 + " IN (SELECT "
	   					   + QMImpuestos.CAMPO1 + 
	   					   " FROM " 
	   					   + QMImpuestos.TABLA +
	   					   " WHERE " 
	   					   + QMImpuestos.CAMPO11 + " = '"+ ValoresDefecto.DEF_ALTA +
	   					   "')))";

			
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
						
						sCOACES = rs.getString(QMActivos.CAMPO1);
						sCOPOIN = rs.getString(QMActivos.CAMPO14);
						sNOMUIN = rs.getString(QMActivos.CAMPO11);
						sNOPRAC = rs.getString(QMActivos.CAMPO13);
						sNOVIAS = rs.getString(QMActivos.CAMPO6);
						sNUPIAC = rs.getString(QMActivos.CAMPO9);
						sNUPOAC = rs.getString(QMActivos.CAMPO7);
						sNUPUAC = rs.getString(QMActivos.CAMPO10);
						sNURCAT = rs.getString(QMActivos.CAMPO81);
						
						ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, sNURCAT);
						
						resultado.add(activoencontrado);
						
						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+sCOACES+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ActivoTabla>();

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
	
	public static ArrayList<ActivoTabla> buscaActivosAsociadosResueltos(Connection conexion, ActivoTabla activo)
	{
		ArrayList<ActivoTabla> resultado = new ArrayList<ActivoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String sCOACES = "";
			String sCOPOIN = "";
			String sNOMUIN = "";
			String sNOPRAC = "";
			String sNOVIAS = "";
			String sNUPIAC = "";
			String sNUPOAC = "";
			String sNUPUAC = "";
			String sNURCAT = "";
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						
						   + QMActivos.CAMPO1 + ","        
						   + QMActivos.CAMPO14 + ","
						   + QMActivos.CAMPO11 + ","
						   + QMActivos.CAMPO13 + ","
						   + QMActivos.CAMPO6 + ","
						   + QMActivos.CAMPO9 + ","
						   + QMActivos.CAMPO7 + ","
						   + QMActivos.CAMPO10 + ","
						   + QMActivos.CAMPO81 + 

						   " FROM " 
						   + QMActivos.TABLA + 
						   " WHERE ("

						   + QMActivos.CAMPO14 + " LIKE '%" + activo.getCOPOIN()	+ "%' AND "  
						   + QMActivos.CAMPO11 + " LIKE '%" + activo.getNOMUIN()	+ "%' AND "  
						   + QMActivos.CAMPO13 + " LIKE '%" + activo.getNOPRAC()	+ "%' AND "  
						   + QMActivos.CAMPO6 + " LIKE '%" + activo.getNOVIAS()	+ "%' AND "  
						   + QMActivos.CAMPO9 + " LIKE '%" + activo.getNUPIAC()	+ "%' AND "  
						   + QMActivos.CAMPO7 + " LIKE '%" + activo.getNUPOAC()	+ "%' AND "  
						   + QMActivos.CAMPO10 + " LIKE '%" + activo.getNUPUAC()	+ "%' AND "			

						   + QMActivos.CAMPO1 +" IN (SELECT "
						   +  CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE ("+
						   
						   CAMPO2 +" IN (SELECT "
						   + QMImpuestos.CAMPO1 + 
						   " FROM " 
						   + QMImpuestos.TABLA +
						   " WHERE " +
						   "("
			   			   + QMImpuestos.CAMPO8 + " = 'F' AND " 
			   			   + QMImpuestos.CAMPO7 + " = 'S' AND "
			   			   + QMImpuestos.CAMPO5 + " <= '"+Utils.fechaDeHoy(false)+"' AND "
			   			   + QMImpuestos.CAMPO11 + " = '" + ValoresDefecto.DEF_ALTA + "' " +
			   			   ")))))";
			
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
						
						sCOACES = rs.getString(QMActivos.CAMPO1);
						sCOPOIN = rs.getString(QMActivos.CAMPO14);
						sNOMUIN = rs.getString(QMActivos.CAMPO11);
						sNOPRAC = rs.getString(QMActivos.CAMPO13);
						sNOVIAS = rs.getString(QMActivos.CAMPO6);
						sNUPIAC = rs.getString(QMActivos.CAMPO9);
						sNUPOAC = rs.getString(QMActivos.CAMPO7);
						sNUPUAC = rs.getString(QMActivos.CAMPO10);
						sNURCAT = rs.getString(QMActivos.CAMPO81);
						
						ActivoTabla activoencontrado = new ActivoTabla(sCOACES, sCOPOIN, sNOMUIN, sNOPRAC, sNOVIAS, sNUPIAC, sNUPOAC, sNUPUAC, sNURCAT);
						
						resultado.add(activoencontrado);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+sCOACES+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ActivoTabla>();

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
	


	public static ArrayList<ImpuestoRecursoTabla> buscaImpuestosActivo(Connection conexion, String sCodCOACES)
	{
		ArrayList<ImpuestoRecursoTabla> resultado = new ArrayList<ImpuestoRecursoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String sCOSBAC = "";
			String sDesCOSBAC = "";
			String sFEPRRE = "";
			String sFERERE = "";
			String sFEDEIN = "";
			String sBISODE = "";
			String sDesBISODE = "";
			String sBIRESO = "";
			String sDesBIRESO = "";
			String sOBTEXC = "";

			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						      
						   + QMImpuestos.CAMPO2 + ","
						   + QMImpuestos.CAMPO3 + ","
						   + QMImpuestos.CAMPO4 + ","
						   + QMImpuestos.CAMPO5 + ","
						   + QMImpuestos.CAMPO6 + ","
						   + QMImpuestos.CAMPO7 + ","
						   + QMImpuestos.CAMPO8 + ","
						   + QMImpuestos.CAMPO9 + ","  
						   + QMImpuestos.CAMPO10 +

						   " FROM " 
						   + QMImpuestos.TABLA + 
						   " WHERE ("

						   + QMImpuestos.CAMPO11 + " = '" + ValoresDefecto.DEF_ALTA + "' AND "  

						   + QMImpuestos.CAMPO1 +" IN (SELECT "
						   +  CAMPO2 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE " 
						   + CAMPO1 + " = '" + sCodCOACES	+ "'))";					   
			
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
						
						sCOSBAC     = rs.getString(QMImpuestos.CAMPO3);
						sDesCOSBAC  = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TCOSBGAT21,QMCodigosControl.ICOSBGAT21,sCOSBAC);
						sFEPRRE     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO4));
						sFERERE     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO5));
						sFEDEIN     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO6));
						sBISODE     = rs.getString(QMImpuestos.CAMPO7);
						sDesBISODE  = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TBINARIA,QMCodigosControl.IBINARIA,sBISODE);
						sBIRESO     = rs.getString(QMImpuestos.CAMPO8);
						sDesBIRESO  = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TBIRESO,QMCodigosControl.IBIRESO,sBIRESO);
						sOBTEXC     = rs.getString(QMImpuestos.CAMPO10);  

						ImpuestoRecursoTabla impuestoencontrado = new ImpuestoRecursoTabla(
								sCOSBAC,
								sDesCOSBAC,
								sFEPRRE,
								sFERERE,
								sFEDEIN,
								sBISODE,
								sDesBISODE,
								sBIRESO,
								sDesBIRESO,
								sOBTEXC);
						
						resultado.add(impuestoencontrado);
						
						logger.debug("Encontrado el registro!");
						
						logger.debug(CAMPO1+":|"+sCodCOACES+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ImpuestoRecursoTabla>();

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
	
	public static ArrayList<ImpuestoRecursoTabla> buscaDevolucionesActivo(Connection conexion, String sCodCOACES)
	{
		ArrayList<ImpuestoRecursoTabla> resultado = new ArrayList<ImpuestoRecursoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String sCOSBAC = "";
			String sDesCOSBAC = "";
			String sFEPRRE = "";
			String sFERERE = "";
			String sFEDEIN = "";
			String sBISODE = "";
			String sDesBISODE = "";
			String sBIRESO = "";
			String sDesBIRESO = "";
			String sOBTEXC = "";
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						
						   + QMImpuestos.CAMPO2 + ","
						   + QMImpuestos.CAMPO3 + ","
						   + QMImpuestos.CAMPO4 + ","
						   + QMImpuestos.CAMPO5 + ","
						   + QMImpuestos.CAMPO6 + ","
						   + QMImpuestos.CAMPO7 + ","
						   + QMImpuestos.CAMPO8 + ","
						   + QMImpuestos.CAMPO9 + "," 
						   + QMImpuestos.CAMPO10 +
						    
						   " FROM " 
						   + QMImpuestos.TABLA + 
						   " WHERE ("

						   + QMImpuestos.CAMPO8 + " = 'F' AND " 
						   + QMImpuestos.CAMPO7 + " = 'S' AND "
						   + QMImpuestos.CAMPO5 + " <= '"+Utils.fechaDeHoy(false)+"' AND "
						   + QMImpuestos.CAMPO11 + " = '" + ValoresDefecto.DEF_ALTA + "' " +

						   "AND "  

						   + QMImpuestos.CAMPO1 +" IN (SELECT "
						   + CAMPO2 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE " 
						   + CAMPO1 + " = '" + sCodCOACES	+ "'))";				   
						   
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
						
						sCOSBAC     = rs.getString(QMImpuestos.CAMPO3);
						sDesCOSBAC  = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TCOSBGAT21,QMCodigosControl.ICOSBGAT21,sCOSBAC);
						sFEPRRE     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO4));
						sFERERE     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO5));
						sFEDEIN     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO6));
						sBISODE     = rs.getString(QMImpuestos.CAMPO7);
						sDesBISODE  = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TBINARIA,QMCodigosControl.IBINARIA,sBISODE);
						sBIRESO     = rs.getString(QMImpuestos.CAMPO8);
						sDesBIRESO  = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TBIRESO,QMCodigosControl.IBIRESO,sBIRESO);
						sOBTEXC     = rs.getString(QMImpuestos.CAMPO10);  
						
						ImpuestoRecursoTabla impuestoencontrado = new ImpuestoRecursoTabla(
								sCOSBAC,
								sDesCOSBAC,
								sFEPRRE,
								sFERERE,
								sFEDEIN,
								sBISODE,
								sDesBISODE,
								sBIRESO,
								sDesBIRESO,
								sOBTEXC);
						
						resultado.add(impuestoencontrado);
						
						logger.debug("Encontrado el registro!");
						
						logger.debug(CAMPO1+":|"+sCodCOACES+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ImpuestoRecursoTabla>();

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

	public static boolean setValidado(Connection conexion, String sCodMovimiento, String sValidado)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO4 + " = '"+ sValidado + "' "+
					" WHERE "
					+ CAMPO3 + " = '" + sCodMovimiento +"'";
			
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

				logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static String getValidado(Connection conexion, String sCodMovimiento)
	{
		String sValidado = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO4 + 
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO3 + " = '" + sCodMovimiento +"'";
			
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

						sValidado = rs.getString(CAMPO4);

						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO4+":|"+sValidado+"|");
					}
				}
				if (!bEncontrado) 
				{
	 				logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sValidado = "";

				logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sValidado;
	}
	
	public static long buscaCantidadValidado(Connection conexion, String sCodValidado)
	{
		long liNumero = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
		
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT COUNT(*) FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO4 + " = '" + sCodValidado + "'";
			
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

						liNumero = rs.getLong("COUNT(*)");
						
						logger.debug("Encontrado el registro!");

						logger.debug( "Numero de registros:|"+liNumero+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				liNumero = 0;

				logger.error("ERROR CodValidado:|"+sCodValidado+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return liNumero;
	}
	
	public static ArrayList<String> buscarDependencias(Connection conexion, String sCodCOACES, String sCodImpuesto, String sCodMovimiento)
	{
		ArrayList<String> resultado = new ArrayList<String>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;	
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO3 + 
					" FROM " 
					+ TABLA + 
					" WHERE (" 
					+ CAMPO1 + " = '" + sCodCOACES + "' AND "
					+ CAMPO2 + " = '" + sCodImpuesto + "' AND "
					+ CAMPO3 + " >=  '" + sCodMovimiento + 
					"')";
			
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
						
						resultado.add(rs.getString(CAMPO3));

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
				
				logger.error("ERROR COACES:|"+sCodCOACES+"|");
				logger.error("ERROR Impuesto:|"+sCodImpuesto+"|");
				logger.error("ERROR Movimiento:|"+sCodMovimiento+"|");

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
}
