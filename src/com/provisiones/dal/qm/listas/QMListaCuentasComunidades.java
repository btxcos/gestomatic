package com.provisiones.dal.qm.listas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.qm.QMCuentas;
import com.provisiones.misc.Longitudes;
import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Cuenta;

public class QMListaCuentasComunidades 
{
	private static Logger logger = LoggerFactory.getLogger(QMListaCuentasComunidades.class.getName());
	
	public static final String TABLA = "pp002_lista_cuentas_comunidades_multi";

	//identificadores
	public static final String CAMPO1  = "cod_cuenta";
	public static final String CAMPO2  = "cod_comunidad";
	public static final String CAMPO3 = "comunidad";

	private QMListaCuentasComunidades(){}

	public static boolean addRelacionComunidad(Connection conexion, long liCodCuenta, long liCodComunidad, byte btComunidad)
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "INSERT INTO " 
					   + TABLA + 
					   " ("
					   + CAMPO1  + "," 
					   + CAMPO2  + "," 
				       + CAMPO3  +    
				       ") VALUES ('"
				       + liCodCuenta + "','" 
				       + liCodComunidad + "'," +
				       + btComunidad + " )";
			
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

				logger.error("ERROR CUENTA:|"+liCodCuenta+"|");
				logger.error("ERROR COMUNIDAD:|"+liCodComunidad+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean delRelacionComunidad(Connection conexion, long liCodCuenta, long liCodComunidad)
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE ("
					+ CAMPO1 + " = '" + liCodCuenta	+ "' AND "
					+ CAMPO2 + " = '" + liCodComunidad	+ "' )";
			
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

				logger.error("ERROR CUENTA:|"+liCodCuenta+"|");
				logger.error("ERROR COMUNIDAD:|"+liCodComunidad+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean existeRelacionComunidad(Connection conexion, long liCodCuenta, long liCodComunidad)
	{
		boolean bEncontrado = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
				       + CAMPO1  +
				       " FROM " 
				       + TABLA + 
				       " WHERE (" 
				       + CAMPO1 + " = '" + liCodCuenta + "' AND "
				       + CAMPO2 + " = '" + liCodComunidad + "')";
			
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
					logger.debug("No se encontr� la informaci�n.");
				}
			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR CUENTA:|"+liCodCuenta+"|");
				logger.error("ERROR COMUNIDAD:|"+liCodComunidad+"|");

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

	public static ArrayList<Cuenta> buscaCuentas(Connection conexion, long liCodComunidad, byte btTipoCuenta)
	{
		ArrayList<Cuenta> resultado = new ArrayList<Cuenta>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			String sPais = "";	
			String sDCIBAN = "";
			String sNUCCEN = "";
			String sNUCCOF = "";
			String sNUCCDI = "";
			String sNUCCNT = "";
			String sDescripcion = "";
			
			String sCondicionTipoCuenta = (btTipoCuenta==ValoresDefecto.CUENTA_TODAS)? "":CAMPO3 + " = "+ btTipoCuenta + " AND ";
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "

						   + "CONVERT(AES_DECRYPT("+QMCuentas.CAMPO2 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   + "CONVERT(AES_DECRYPT("+QMCuentas.CAMPO3 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   + "CONVERT(AES_DECRYPT("+QMCuentas.CAMPO4 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   + "CONVERT(AES_DECRYPT("+QMCuentas.CAMPO5 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   + "CONVERT(AES_DECRYPT("+QMCuentas.CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "
						   + "CONVERT(AES_DECRYPT("+QMCuentas.CAMPO7+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1), "

						   + QMCuentas.CAMPO8 +						   

						   " FROM " 
						   + QMCuentas.TABLA + 
						   " WHERE ("
						   + QMCuentas.CAMPO1 +" IN (SELECT "
						   +  CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE ("
						   + sCondicionTipoCuenta
						   + CAMPO2 + " = '"+ liCodComunidad + "')))";					   
						   
			
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
						
						sPais    = rs.getString("CONVERT(AES_DECRYPT("+QMCuentas.CAMPO2 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)");
						sDCIBAN  = Parser.formateaCampoNumerico(rs.getString("CONVERT(AES_DECRYPT("+QMCuentas.CAMPO3 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)"),Longitudes.DCIBAN_L);
						sNUCCEN  = Parser.formateaCampoNumerico(rs.getString("CONVERT(AES_DECRYPT("+QMCuentas.CAMPO4 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)"),Longitudes.NUCCEN_L);
						sNUCCOF  = Parser.formateaCampoNumerico(rs.getString("CONVERT(AES_DECRYPT("+QMCuentas.CAMPO5 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)"),Longitudes.NUCCOF_L);
						sNUCCDI  = Parser.formateaCampoNumerico(rs.getString("CONVERT(AES_DECRYPT("+QMCuentas.CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)"),Longitudes.NUCCDI_L);
						sNUCCNT  = Parser.formateaCampoNumerico(rs.getString("CONVERT(AES_DECRYPT("+QMCuentas.CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) USING latin1)"),Longitudes.NUCCNT_L);
						sDescripcion  = rs.getString(QMCuentas.CAMPO8);
					
						Cuenta cuentaencontrada = new Cuenta(
								sPais,
								sDCIBAN,
								sNUCCEN,
								sNUCCOF,
								sNUCCDI,
								sNUCCNT,
								sDescripcion);
						
						resultado.add(cuentaencontrada);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO2+":|"+liCodComunidad+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontr� la informaci�n.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<Cuenta>();
				
				logger.error("ERROR COACES:|"+liCodComunidad+"|");
				
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
	
	public static ArrayList<Long> buscaCodigosCuentasComunidad(Connection conexion, long liCodComunidad)
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
						   + CAMPO1 + 
						   " FROM " 
						   + TABLA + 
						   " WHERE " 
						   + CAMPO2 + " = '"+ liCodComunidad + "'";					   
						   
			
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
						
						long liCodCuenta = rs.getLong(CAMPO1);
					
						resultado.add(liCodCuenta);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+liCodCuenta+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontr� la informaci�n.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<Long>();
				
				logger.error("ERROR COACES:|"+liCodComunidad+"|");
				
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
	
	public static boolean eliminarCuentasComunidad(Connection conexion, long liCodComunidad)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");

			String sQuery = "DELETE FROM " 
					+ QMCuentas.TABLA + 
					" WHERE ("
					+ QMCuentas.CAMPO1 + " IN (SELECT "
					+  CAMPO1 + 
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO2 + " = '"+ liCodComunidad + "'))";				   
						   
			
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

				logger.error("ERROR COMUNIDAD:|"+liCodComunidad+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static boolean eliminarRelacionesCuentasComunidad(Connection conexion, long liCodComunidad)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");

			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO2 + " = '"+ liCodComunidad + "'";				   
						   
			
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

				logger.error("ERROR COMUNIDAD:|"+liCodComunidad+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static long buscaCantidadRelaccionesCuenta(Connection conexion, long liCodCuenta)
	{
		long liNumero = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT COUNT("+CAMPO1+") FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO1 + " = "+liCodCuenta;
			
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

						liNumero = rs.getLong("COUNT("+CAMPO1+")");
						
						logger.debug("Encontrado el registro!");

						logger.debug("Numero de registros:|"+liNumero+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontr� la informaci�n.");
				}
			} 
			catch (SQLException ex) 
			{
				liNumero = 0;

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
	
	public static boolean cuentaAsociadaComunidad(Connection conexion, String sNUCCEN, String sNUCCOF, String sNUCCDI, String sNUCCNT, long liCodComunidad)
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
					   " WHERE ("
					   + CAMPO2 + " = " + liCodComunidad + " AND "
					   + CAMPO1 + " IN (SELECT "
			       	+ QMCuentas.CAMPO1  +                
			       	" FROM " 
			       	+ QMCuentas.TABLA + 
			       	" WHERE ("
			       	+ QMCuentas.CAMPO4    + " = AES_ENCRYPT('"+sNUCCEN+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND " 
					+ QMCuentas.CAMPO5    + " = AES_ENCRYPT('"+sNUCCOF+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND " 
					+ QMCuentas.CAMPO6    + " = AES_ENCRYPT('"+sNUCCDI+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND " 
					+ QMCuentas.CAMPO7    + " = AES_ENCRYPT('"+sNUCCNT+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) )))"; 

			
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
					logger.debug("No se encontr� la informaci�n.");
				}
			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;
				
				logger.error("ERROR ENTIDAD:|"+sNUCCEN+"|");
				logger.error("ERROR OFICINA:|"+sNUCCOF+"|");
				logger.error("ERROR DC:|"+sNUCCDI+"|");
				logger.error("ERROR CUENTA:|"+sNUCCNT+"|");

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
	
}

//Autor: Francisco Valverde Manj�n
