package com.provisiones.dal.qm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Longitudes;
import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Cuenta;

public class QMCuentas
{

	private static Logger logger = LoggerFactory.getLogger(QMCuentas.class.getName());
	
	public static final String TABLA = "pp002_cuentas_tbl";

	public static final String CAMPO1 = "cuenta_id";

	public static final String CAMPO2 = "pais";
	public static final String CAMPO3 = "dciban";
	public static final String CAMPO4 = "nuccen";
	public static final String CAMPO5 = "nuccof";
	public static final String CAMPO6 = "nuccdi";
	public static final String CAMPO7 = "nuccnt";
	public static final String CAMPO8 = "descripcion";
	//public static final String CAMPO9 = "comunidad";
	public static final String CAMPO10 = "usuario_modificacion";
	public static final String CAMPO11 = "fecha_modificacion";

	private QMCuentas(){}
	
	public static long addCuenta(Connection conexion, Cuenta NuevaCuenta)
	{
		long liCodigo = 0;

		if (conexion != null)
		{
			String sUsuario = ConnectionManager.getUser();
			
			Statement stmt = null;

			ResultSet resulset = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "INSERT INTO " 
					+ TABLA + 
					" ("
					+ CAMPO2 + ","
					+ CAMPO3 + "," 
					+ CAMPO4 + ","
					+ CAMPO5 + ","
					+ CAMPO6 + ","
					+ CAMPO7 + ","
					+ CAMPO8 + ","
					+ CAMPO10 + ","
					+ CAMPO11 +
					") VALUES (" 
					+ "AES_ENCRYPT('"+NuevaCuenta.getsPais()  +"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))," 
					+ "AES_ENCRYPT('"+NuevaCuenta.getsDCIBAN()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))," 
					+ "AES_ENCRYPT('"+NuevaCuenta.getsNUCCEN()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))," 
					+ "AES_ENCRYPT('"+NuevaCuenta.getsNUCCOF()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))," 
					+ "AES_ENCRYPT('"+NuevaCuenta.getsNUCCDI()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))," 
					+ "AES_ENCRYPT('"+NuevaCuenta.getsNUCCNT()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'"
					+ NuevaCuenta.getsDescripcion() + "','"
					+ sUsuario + "','"
					+ Utils.timeStamp() + 
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
			}
			catch (SQLException ex) 
			{
				liCodigo = 0;

				logger.error("ERROR CUENTA:|"+NuevaCuenta.getsDescripcion()+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());	
			} 
			finally
			{

				Utils.closeStatement(stmt);
			}
		}

		return liCodigo;
	}

	public static boolean modCuenta(Connection conexion, Cuenta cuenta, long liCuentaID) 
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			String sUsuario = ConnectionManager.getUser();
			
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO8 + " = '" + cuenta.getsDescripcion() + "', "
					+ CAMPO10 + " = '" + sUsuario + "', " 
					+ CAMPO11 + " = '" + Utils.timeStamp() + "' " +					
					" WHERE " 
					+ CAMPO1 + " = '" + liCuentaID + "'";


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

				logger.error("ERROR CUENTA:|"+liCuentaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean delCuenta(Connection conexion, long liCuentaID) 
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '" + liCuentaID + "'";
			
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

				logger.error("ERROR CUENTA:|"+liCuentaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
			}
		}		

		return bSalida;
	}
	
	public static Cuenta getCuenta(Connection conexion, long liCuentaID)
	{
		String sPais = "";	
		String sDCIBAN = "";
		String sNUCCEN = "";
		String sNUCCOF = "";
		String sNUCCDI = "";
		String sNUCCNT = "";
		String sDescripcion = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
				       + "AES_DECRYPT("+CAMPO2 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO3 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO4 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO5 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO7+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + CAMPO8 + 
				       " FROM "
				       + TABLA + 
				       " WHERE "
				       + CAMPO1 + " = '"+ liCuentaID +"'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");

				logger.debug(CAMPO1 + ":|"+liCuentaID+"|");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;

						sPais = rs.getString("AES_DECRYPT("+CAMPO2 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sDCIBAN = Parser.formateaCampoNumerico(rs.getString("AES_DECRYPT("+CAMPO3 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"),Longitudes.DCIBAN_L);
						sNUCCEN = Parser.formateaCampoNumerico(rs.getString("AES_DECRYPT("+CAMPO4 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"),Longitudes.NUCCEN_L);
						sNUCCOF = Parser.formateaCampoNumerico(rs.getString("AES_DECRYPT("+CAMPO5 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"),Longitudes.NUCCOF_L);
						sNUCCDI = Parser.formateaCampoNumerico(rs.getString("AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"),Longitudes.NUCCDI_L);
						sNUCCNT = Parser.formateaCampoNumerico(rs.getString("AES_DECRYPT("+CAMPO7+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"),Longitudes.NUCCNT_L);
						sDescripcion = rs.getString(CAMPO8);  
						
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
				sPais = "";	
				sDCIBAN = "";
				sNUCCEN = "";
				sNUCCOF = "";
				sNUCCDI = "";
				sNUCCNT = "";
				sDescripcion = "";

				logger.error("ERROR CUENTA:|"+liCuentaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new Cuenta(
				sPais,
				sDCIBAN,
				sNUCCEN,
				sNUCCOF,
				sNUCCDI,
				sNUCCNT,
				sDescripcion);
	}

	
	public static long getCuentaID(Connection conexion, String sNUCCEN, String sNUCCOF, String sNUCCDI, String sNUCCNT)
	{
		//Buscar por CCC
		long liCuentaID = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
				       	+ CAMPO1  +                
				       	" FROM " 
				       	+ TABLA + 
				       	" WHERE ("
				       	+ CAMPO4    + " = AES_ENCRYPT('"+sNUCCEN+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND " 
						+ CAMPO5    + " = AES_ENCRYPT('"+sNUCCOF+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND " 
						+ CAMPO6    + " = AES_ENCRYPT('"+sNUCCDI+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND " 
						+ CAMPO7    + " = AES_ENCRYPT('"+sNUCCNT+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) )"; 

			
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

						liCuentaID = rs.getLong(CAMPO1);
						
						logger.debug(CAMPO1+":|"+liCuentaID+"|");
						
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
				liCuentaID = 0;
				
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

		return liCuentaID;
	}	
	
	public static boolean existeCuenta(Connection conexion, String sNUCCEN, String sNUCCOF, String sNUCCDI, String sNUCCNT)
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
			       	+ CAMPO4    + " = AES_ENCRYPT('"+sNUCCEN+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND " 
					+ CAMPO5    + " = AES_ENCRYPT('"+sNUCCOF+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND " 
					+ CAMPO6    + " = AES_ENCRYPT('"+sNUCCDI+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) AND " 
					+ CAMPO7    + " = AES_ENCRYPT('"+sNUCCNT+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) )"; 

			
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
	
	public static String getDescripcion(Connection conexion, long liCuentaID) 
	{
		String sFechaCuenta = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO8  +
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '"+ liCuentaID + "'";
			
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
						
						sFechaCuenta = rs.getString(CAMPO4);

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+liCuentaID+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sFechaCuenta = "";

				logger.error("ERROR CUENTA:|"+liCuentaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}		

		return sFechaCuenta;
	}
}
