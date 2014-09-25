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

public final class QMListaImpuestos 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaImpuestos.class.getName());

	public static final String TABLA = "pp002_lista_impuestos_multi";
	
	//identificadores
	public static final String CAMPO1 = "cod_coaces";
	public static final String CAMPO2 = "cod_impuesto";
	public static final String CAMPO3 = "cod_movimiento";

	//Campos de control
	public static final String CAMPO4 = "cod_validado";
	public static final String CAMPO5 = "usuario_movimiento";
	public static final String CAMPO6  = "fecha_movimiento";    

	private QMListaImpuestos(){}

	public static boolean addRelacionImpuestos(Connection conexion, int iCodCOACES, long liCodImpuesto, long liCodMovimiento) 
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
					+ iCodCOACES + "','"
					+ liCodImpuesto + "','"
					+ liCodMovimiento + "','"
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
				
				logger.error("ERROR COACES:|"+iCodCOACES+"|");
				logger.error("ERROR Impuesto:|"+liCodImpuesto+"|");
				logger.error("ERROR Movimiento:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean delRelacionImpuestos(Connection conexion, long liCodMovimiento) 
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO3 + " = '" + liCodMovimiento +"'";
			
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
				
				logger.error("ERROR CodMovimiento:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean existeRelacionImpuesto(Connection conexion, int iCodCOACES, long liCodImpuesto, long liCodMovimiento)
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
				+ CAMPO1 + " = '" + iCodCOACES + "' AND "
				+ CAMPO2 + " = '" + liCodImpuesto + "' AND " 
				+ CAMPO3 + " = '" + liCodMovimiento +
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

			logger.error("ERROR COACES:|"+iCodCOACES+"|");
			logger.error("ERROR Impuesto:|"+liCodImpuesto+"|");
			logger.error("ERROR Movimiento:|"+liCodMovimiento+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		return bEncontrado;
	}

	public static boolean compruebaRelacionImpuestoActivo(Connection conexion, int iCodCOACES, long liCodImpuesto)
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
					+ CAMPO1 + " = '" + iCodCOACES + "' AND " 
					+ CAMPO2 + " = '" + liCodImpuesto + 
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
						logger.debug(CAMPO1+"|"+iCodCOACES+"|");
						logger.debug(CAMPO2+":|"+liCodImpuesto+"|");
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

				logger.error("ERROR COACES:|"+iCodCOACES+"|");
				logger.error("ERROR Impuesto:|"+liCodImpuesto+"|");

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
	
	public static int getActivoRecurso(Connection conexion, long liCodImpuesto)
	{
		int iCOACES = 0;

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
					" WHERE " 
					+ CAMPO2 + " = '" + liCodImpuesto +"'";
			
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

						iCOACES = rs.getInt(CAMPO1);

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
				iCOACES = 0;

				logger.error("ERROR liCodImpuesto:|"+liCodImpuesto+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return iCOACES;
	}

	public static boolean tieneRecursosActivo(Connection conexion, int iCOACES)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO2 + 
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '" + iCOACES +"'";
			
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

				logger.error("ERROR iCOACES:|"+iCOACES+"|");

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

	public static ArrayList<Long>  getMovimientosImpuestosPorEstado(Connection conexion, String sEstado) 
	{
		ArrayList<Long> resultado = new ArrayList<Long>(); 

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
				
				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						resultado.add(rs.getLong(CAMPO3));
											
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
				resultado = new ArrayList<Long>(); 

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
	
	public static ArrayList<ActivoTabla> buscaActivosAsociados(Connection conexion, ActivoTabla filtro)
	{
		ArrayList<ActivoTabla> resultado = new ArrayList<ActivoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String sCondicionCOPOIN = filtro.getCOPOIN().isEmpty()?"":QMActivos.CAMPO14 + " LIKE '%" + filtro.getCOPOIN()	+ "%' AND ";
			String sCondicionNOMUIN = filtro.getNOMUIN().isEmpty()?"":QMActivos.CAMPO11 + " LIKE '%" + filtro.getNOMUIN()	+ "%' AND ";
			String sCondicionNOPRAC = filtro.getNOPRAC().isEmpty()?"":QMActivos.CAMPO13 + " LIKE '%" + filtro.getNOPRAC()	+ "%' AND ";
			String sCondicionNOVIAS = filtro.getNOVIAS().isEmpty()?"":QMActivos.CAMPO6 + " LIKE '%" + filtro.getNOVIAS()	+ "%' AND ";
			String sCondicionNUPIAC = filtro.getNUPIAC().isEmpty()?"":QMActivos.CAMPO9 + " LIKE '%" + filtro.getNUPIAC()	+ "%' AND ";
			String sCondicionNUPOAC = filtro.getNUPOAC().isEmpty()?"":QMActivos.CAMPO7 + " LIKE '%" + filtro.getNUPOAC()	+ "%' AND ";
			String sCondicionNUPUAC = filtro.getNUPUAC().isEmpty()?"":QMActivos.CAMPO10 + " LIKE '%" + filtro.getNUPUAC()	+ "%' AND ";
			String sCondicionNUFIRE = filtro.getNUFIRE().isEmpty()?"":QMActivos.CAMPO28 + " LIKE '%" + filtro.getNUFIRE()	+ "%' AND ";

			
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
						   + QMActivos.CAMPO28 +  ","
						   + QMActivos.CAMPO81 + 

						   " FROM " 
						   + QMActivos.TABLA + 
						   " WHERE ("

					   		+ sCondicionCOPOIN  
					   		+ sCondicionNOMUIN  
					   		+ sCondicionNOPRAC  
					   		+ sCondicionNOVIAS 
					   		+ sCondicionNUPIAC  
					   		+ sCondicionNUPOAC
					   		+ sCondicionNUPUAC
					   		+ sCondicionNUFIRE	

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
						
						String sCOACES = rs.getString(QMActivos.CAMPO1);
						String sCOPOIN = rs.getString(QMActivos.CAMPO14);
						String sNOMUIN = rs.getString(QMActivos.CAMPO11);
						String sNOPRAC = rs.getString(QMActivos.CAMPO13);
						String sNOVIAS = rs.getString(QMActivos.CAMPO6);
						String sNUPIAC = rs.getString(QMActivos.CAMPO9);
						String sNUPOAC = rs.getString(QMActivos.CAMPO7);
						String sNUPUAC = rs.getString(QMActivos.CAMPO10);
						String sNUFIRE = rs.getString(QMActivos.CAMPO28);
						String sNURCAT = rs.getString(QMActivos.CAMPO81);
						
						ActivoTabla activoencontrado = new ActivoTabla(
								sCOACES, 
								sCOPOIN, 
								sNOMUIN, 
								sNOPRAC, 
								sNOVIAS, 
								sNUPIAC, 
								sNUPOAC, 
								sNUPUAC,
								sNUFIRE,
								sNURCAT);
						
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
	
	public static ArrayList<ActivoTabla> buscaActivosAsociadosResueltos(Connection conexion, ActivoTabla filtro)
	{
		ArrayList<ActivoTabla> resultado = new ArrayList<ActivoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			String sCondicionCOPOIN = filtro.getCOPOIN().isEmpty()?"":QMActivos.CAMPO14 + " LIKE '%" + filtro.getCOPOIN()	+ "%' AND ";
			String sCondicionNOMUIN = filtro.getNOMUIN().isEmpty()?"":QMActivos.CAMPO11 + " LIKE '%" + filtro.getNOMUIN()	+ "%' AND ";
			String sCondicionNOPRAC = filtro.getNOPRAC().isEmpty()?"":QMActivos.CAMPO13 + " LIKE '%" + filtro.getNOPRAC()	+ "%' AND ";
			String sCondicionNOVIAS = filtro.getNOVIAS().isEmpty()?"":QMActivos.CAMPO6 + " LIKE '%" + filtro.getNOVIAS()	+ "%' AND ";
			String sCondicionNUPIAC = filtro.getNUPIAC().isEmpty()?"":QMActivos.CAMPO9 + " LIKE '%" + filtro.getNUPIAC()	+ "%' AND ";
			String sCondicionNUPOAC = filtro.getNUPOAC().isEmpty()?"":QMActivos.CAMPO7 + " LIKE '%" + filtro.getNUPOAC()	+ "%' AND ";
			String sCondicionNUPUAC = filtro.getNUPUAC().isEmpty()?"":QMActivos.CAMPO10 + " LIKE '%" + filtro.getNUPUAC()	+ "%' AND ";
			String sCondicionNUFIRE = filtro.getNUFIRE().isEmpty()?"":QMActivos.CAMPO28 + " LIKE '%" + filtro.getNUFIRE()	+ "%' AND ";

			
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
						   + QMActivos.CAMPO28 +  ","
						   + QMActivos.CAMPO81 + 

						   " FROM " 
						   + QMActivos.TABLA + 
						   " WHERE ("

					   		+ sCondicionCOPOIN  
					   		+ sCondicionNOMUIN  
					   		+ sCondicionNOPRAC  
					   		+ sCondicionNOVIAS 
					   		+ sCondicionNUPIAC  
					   		+ sCondicionNUPOAC
					   		+ sCondicionNUPUAC
					   		+ sCondicionNUFIRE	

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
						
						String sCOACES = rs.getString(QMActivos.CAMPO1);
						String sCOPOIN = rs.getString(QMActivos.CAMPO14);
						String sNOMUIN = rs.getString(QMActivos.CAMPO11);
						String sNOPRAC = rs.getString(QMActivos.CAMPO13);
						String sNOVIAS = rs.getString(QMActivos.CAMPO6);
						String sNUPIAC = rs.getString(QMActivos.CAMPO9);
						String sNUPOAC = rs.getString(QMActivos.CAMPO7);
						String sNUPUAC = rs.getString(QMActivos.CAMPO10);
						String sNUFIRE = rs.getString(QMActivos.CAMPO28);
						String sNURCAT = rs.getString(QMActivos.CAMPO81);
						
						ActivoTabla activoencontrado = new ActivoTabla(
								sCOACES, 
								sCOPOIN, 
								sNOMUIN, 
								sNOPRAC, 
								sNOVIAS, 
								sNUPIAC, 
								sNUPOAC, 
								sNUPUAC,
								sNUFIRE,
								sNURCAT);
						
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
	


	public static ArrayList<ImpuestoRecursoTabla> buscaImpuestosActivo(Connection conexion, int iCodCOACES)
	{
		ArrayList<ImpuestoRecursoTabla> resultado = new ArrayList<ImpuestoRecursoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						   + QMImpuestos.CAMPO1 + ","   
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
						   + CAMPO1 + " = '" + iCodCOACES	+ "'))";					   
			
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
						
						String sRecursoID  = rs.getString(QMImpuestos.CAMPO1);
						String sNURCAT	   = rs.getString(QMImpuestos.CAMPO2);
						String sCOSBAC     = rs.getString(QMImpuestos.CAMPO3);
						String sDesCOSBAC  = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TCOSBGAT21,QMCodigosControl.ICOSBGAT21,sCOSBAC);
						String sFEPRRE     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO4));
						String sFERERE     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO5));
						String sFEDEIN     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO6));
						String sBISODE     = rs.getString(QMImpuestos.CAMPO7);
						String sDesBISODE  = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TBINARIA,QMCodigosControl.IBINARIA,sBISODE);
						String sBIRESO     = rs.getString(QMImpuestos.CAMPO8);
						String sDesBIRESO  = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TBIRESO,QMCodigosControl.IBIRESO,sBIRESO);
						String sOBTEXC     = rs.getString(QMImpuestos.CAMPO10);
						String sEstado     = rs.getString(QMImpuestos.CAMPO11); 

						ImpuestoRecursoTabla impuestoencontrado = new ImpuestoRecursoTabla(
								sRecursoID,
								sNURCAT,
								sCOSBAC,
								sDesCOSBAC,
								sFEPRRE,
								sFERERE,
								sFEDEIN,
								sBISODE,
								sDesBISODE,
								sBIRESO,
								sDesBIRESO,
								sOBTEXC,
								sEstado);
						
						resultado.add(impuestoencontrado);
						
						logger.debug("Encontrado el registro!");
						
						logger.debug(CAMPO1+":|"+iCodCOACES+"|");
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

	public static ArrayList<ImpuestoRecursoTabla> buscaImpuestosActivoPorFiltro(Connection conexion, ImpuestoRecursoTabla filtro, int iCodCOACES)
	{
		ArrayList<ImpuestoRecursoTabla> resultado = new ArrayList<ImpuestoRecursoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			//Condiciones de filtro
			String sCondicionNURCAT = filtro.getNURCAT().isEmpty()?"":QMImpuestos.CAMPO2 + " = '" + filtro.getNURCAT() + "' AND ";
			String sCondicionCOSBAC = filtro.getCOSBAC().isEmpty()?"":QMImpuestos.CAMPO3 + " = '" + filtro.getCOSBAC() + "' AND ";
			String sCondicionFEPRRE = (filtro.getFEPRRE().isEmpty() || filtro.getFEPRRE().equals("0"))?"":QMImpuestos.CAMPO4 + " = '" + filtro.getFEPRRE() + "' AND ";
			String sCondicionFERERE = (filtro.getFERERE().isEmpty() || filtro.getFERERE().equals("0"))?"":QMImpuestos.CAMPO5 + " = '" + filtro.getFERERE() + "' AND ";
			String sCondicionFEDEIN = (filtro.getFEDEIN().isEmpty() || filtro.getFEDEIN().equals("0"))?"":QMImpuestos.CAMPO6 + " = '" + filtro.getFEDEIN() + "' AND ";
			String sCondicionBISODE = filtro.getBISODE().isEmpty()?"":QMImpuestos.CAMPO7 + " = '" + filtro.getBISODE() + "' AND ";
			String sCondicionBIRESO = filtro.getBIRESO().isEmpty()?"":QMImpuestos.CAMPO8 + " = '" + filtro.getBIRESO() + "' AND ";
			String sCondicionEstado = filtro.getESTADO().isEmpty()?"":QMImpuestos.CAMPO11 + " = '" + filtro.getESTADO() + "' AND ";
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						   + QMImpuestos.CAMPO1 + ","   
						   + QMImpuestos.CAMPO2 + ","
						   + QMImpuestos.CAMPO3 + ","
						   + QMImpuestos.CAMPO4 + ","
						   + QMImpuestos.CAMPO5 + ","
						   + QMImpuestos.CAMPO6 + ","
						   + QMImpuestos.CAMPO7 + ","
						   + QMImpuestos.CAMPO8 + ","
						   + QMImpuestos.CAMPO9 + ","
						   + QMImpuestos.CAMPO10 + ","
						   + QMImpuestos.CAMPO11 +

						   " FROM " 
						   + QMImpuestos.TABLA + 
						   " WHERE ("
						   + sCondicionNURCAT
						   + sCondicionCOSBAC
						   + sCondicionFEPRRE
						   + sCondicionFERERE
						   + sCondicionFEDEIN
						   + sCondicionBISODE
						   + sCondicionBIRESO
						   + sCondicionEstado
						   + QMImpuestos.CAMPO1 +" IN (SELECT "
						   +  CAMPO2 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE " 
						   + CAMPO1 + " = '" + iCodCOACES	+ "'))";					   
			
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
						
						String sRecursoID  = rs.getString(QMImpuestos.CAMPO1);
						String sNURCAT	   = rs.getString(QMImpuestos.CAMPO2);
						String sCOSBAC     = rs.getString(QMImpuestos.CAMPO3);
						String sDesCOSBAC  = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TCOSBGAT21,QMCodigosControl.ICOSBGAT21,sCOSBAC);
						String sFEPRRE     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO4));
						String sFERERE     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO5));
						String sFEDEIN     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO6));
						String sBISODE     = rs.getString(QMImpuestos.CAMPO7);
						String sDesBISODE  = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TBINARIA,QMCodigosControl.IBINARIA,sBISODE);
						String sBIRESO     = rs.getString(QMImpuestos.CAMPO8);
						String sDesBIRESO  = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TBIRESO,QMCodigosControl.IBIRESO,sBIRESO);
						String sOBTEXC     = rs.getString(QMImpuestos.CAMPO10);
						String sEstado     = rs.getString(QMImpuestos.CAMPO11);

						ImpuestoRecursoTabla impuestoencontrado = new ImpuestoRecursoTabla(
								sRecursoID,
								sNURCAT,
								sCOSBAC,
								sDesCOSBAC,
								sFEPRRE,
								sFERERE,
								sFEDEIN,
								sBISODE,
								sDesBISODE,
								sBIRESO,
								sDesBIRESO,
								sOBTEXC,
								sEstado);
						
						resultado.add(impuestoencontrado);
						
						logger.debug("Encontrado el registro!");
						
						logger.debug(CAMPO1+":|"+iCodCOACES+"|");
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
	
	public static ArrayList<ImpuestoRecursoTabla> buscaDevolucionesActivo(Connection conexion, int iCodCOACES)
	{
		ArrayList<ImpuestoRecursoTabla> resultado = new ArrayList<ImpuestoRecursoTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						   + QMImpuestos.CAMPO1 + ","
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
						   + CAMPO1 + " = '" + iCodCOACES	+ "'))";				   
						   
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
						
						String sRecursoID		= rs.getString(QMImpuestos.CAMPO1);
						String sNURCAT		= rs.getString(QMImpuestos.CAMPO2);
						String sCOSBAC     = rs.getString(QMImpuestos.CAMPO3);
						String sDesCOSBAC  = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TCOSBGAT21,QMCodigosControl.ICOSBGAT21,sCOSBAC);
						String sFEPRRE     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO4));
						String sFERERE     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO5));
						String sFEDEIN     = Utils.recuperaFecha(rs.getString(QMImpuestos.CAMPO6));
						String sBISODE     = rs.getString(QMImpuestos.CAMPO7);
						String sDesBISODE  = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TBINARIA,QMCodigosControl.IBINARIA,sBISODE);
						String sBIRESO     = rs.getString(QMImpuestos.CAMPO8);
						String sDesBIRESO  = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TBIRESO,QMCodigosControl.IBIRESO,sBIRESO);
						String sOBTEXC     = rs.getString(QMImpuestos.CAMPO10);
						String sEstado     = rs.getString(QMImpuestos.CAMPO11);
						
						ImpuestoRecursoTabla impuestoencontrado = new ImpuestoRecursoTabla(
								sRecursoID,
								sNURCAT,
								sCOSBAC,
								sDesCOSBAC,
								sFEPRRE,
								sFERERE,
								sFEDEIN,
								sBISODE,
								sDesBISODE,
								sBIRESO,
								sDesBIRESO,
								sOBTEXC,
								sEstado);
						
						resultado.add(impuestoencontrado);
						
						logger.debug("Encontrado el registro!");
						
						logger.debug(CAMPO1+":|"+iCodCOACES+"|");
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

	public static boolean setValidado(Connection conexion, long liCodMovimiento, String sValidado)
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
					+ CAMPO3 + " = '" + liCodMovimiento +"'";
			
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

				logger.error("ERROR Movimiento:|"+liCodMovimiento+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static String getValidado(Connection conexion, long liCodMovimiento)
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
					+ CAMPO3 + " = '" + liCodMovimiento +"'";
			
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

				logger.error("ERROR Movimiento:|"+liCodMovimiento+"|");

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
	
	public static ArrayList<Long> buscarDependencias(Connection conexion, int iCodCOACES, long liCodImpuesto, long liCodMovimiento)
	{
		ArrayList<Long> resultado = new ArrayList<Long>();

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
					+ CAMPO1 + " = '" + iCodCOACES + "' AND "
					+ CAMPO2 + " = '" + liCodImpuesto + "' AND "
					+ CAMPO3 + " >=  '" + liCodMovimiento + 
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
						
						resultado.add(rs.getLong(CAMPO3));

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
				resultado = new ArrayList<Long>();
				
				logger.error("ERROR COACES:|"+iCodCOACES+"|");
				logger.error("ERROR Impuesto:|"+liCodImpuesto+"|");
				logger.error("ERROR Movimiento:|"+liCodMovimiento+"|");

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
