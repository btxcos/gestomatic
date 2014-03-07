package com.provisiones.dal.qm;

import com.provisiones.dal.qm.listas.QMListaComunidadesActivos;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Comunidad;
import com.provisiones.types.tablas.ComunidadTabla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class QMComunidades
{
	private static Logger logger = LoggerFactory.getLogger(QMComunidades.class.getName());
	
	public static final String TABLA = "pp002_e1_comunidades_tbl";

	//Primary Key
	public static final String CAMPO1  = "e1_comunidad_id";
	
	//Unique Key - comunidad
	public static final String CAMPO2  = "cod_cocldo";
	public static final String CAMPO3  = "nudcom_id"; 
	
	//Campos secundarios
	public static final String CAMPO4  = "nomcoc";    
	public static final String CAMPO5  = "nodcco";    
	public static final String CAMPO6  = "nomprc";    
	public static final String CAMPO7  = "nutprc";    
	public static final String CAMPO8  = "nomadc";    
	public static final String CAMPO9  = "nutadc";    
	public static final String CAMPO10 = "nodcad";    
	public static final String CAMPO11 = "cod_cuenta";    
	public static final String CAMPO12 = "obtexc";
	
	//Campos de control
	public static final String CAMPO13 = "cod_estado";
	public static final String CAMPO14 = "nota";
	
	private QMComunidades(){}

	public static long addComunidad(Connection conexion, Comunidad NuevaComunidad)

	{
		long liCodigo = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			ResultSet resulset = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "INSERT INTO " 
					   + TABLA + 
					   " ("
				       + CAMPO2  + ","              
				       + CAMPO3  + ","              
				       + CAMPO4  + ","              
				       + CAMPO5  + ","              
				       + CAMPO6  + ","              
				       + CAMPO7  + ","              
				       + CAMPO8  + ","              
				       + CAMPO9  + ","              
				       + CAMPO10 + ","              
				       + CAMPO11 + ","              
					   + CAMPO12 + ","
					   + CAMPO13 + ","
				       + CAMPO14 +               
				       ") VALUES ('" 
				       + NuevaComunidad.getsCOCLDO() + "','" 
				       + NuevaComunidad.getsNUDCOM() + "',"
				       /*+ NuevaComunidad.getsNOMCOC() + "','"
				       + NuevaComunidad.getsNODCCO() + "','"
				       + NuevaComunidad.getsNOMPRC() + "','"
				       + NuevaComunidad.getsNUTPRC() + "','"
				       + NuevaComunidad.getsNOMADC() + "','"
				       + NuevaComunidad.getsNUTADC() + "','"
				       + NuevaComunidad.getsNODCAD() + "','"
				       + NuevaComunidad.getsNUCCEN() + "','"
				       + NuevaComunidad.getsNUCCOF() + "','"
				       + NuevaComunidad.getsNUCCDI() + "','"
				       + NuevaComunidad.getsNUCCNT() + "','"*/

				       //cifrado de datos personales
				       + "AES_ENCRYPT('"+NuevaComunidad.getsNOMCOC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       + "AES_ENCRYPT('"+NuevaComunidad.getsNODCCO()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       + "AES_ENCRYPT('"+NuevaComunidad.getsNOMPRC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       + "AES_ENCRYPT('"+NuevaComunidad.getsNUTPRC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       + "AES_ENCRYPT('"+NuevaComunidad.getsNOMADC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       + "AES_ENCRYPT('"+NuevaComunidad.getsNUTADC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       + "AES_ENCRYPT('"+NuevaComunidad.getsNODCAD()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), '"
				       + NuevaComunidad.getsCuenta() + "','" 
				       + NuevaComunidad.getsOBTEXC() + "','" 
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
				
				logger.error("ERROR COCLDO:|"+NuevaComunidad.getsCOCLDO()+"|");
				logger.error("ERROR NUDCOM:|"+NuevaComunidad.getsNUDCOM()+"|");
				
				logger.error("ERROR SQLException:",ex.getMessage());
				logger.error("ERROR SQLState:",ex.getSQLState());
				logger.error("ERROR VendorError:",ex.getErrorCode());
			} 
			finally
			{

				Utils.closeStatement(stmt);
			}
		}

		return liCodigo;
	}
	public static boolean modComunidad(Connection conexion, Comunidad NuevaComunidad, long liComunidadID)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					// Campos unicos
					//+ CAMPO2    + " = '"+ NuevaComunidad.getsCOCLDO() + "', "
					//+ CAMPO3    + " = '"+ NuevaComunidad.getsNUDCOM() + "', "
					
					// Datos Personales
					/*+ CAMPO4    + " = '"+ NuevaComunidad.getsNOMCOC() + "', "
					+ CAMPO5    + " = '"+ NuevaComunidad.getsNODCCO() + "', "
					+ CAMPO6    + " = '"+ NuevaComunidad.getsNOMPRC() + "', "
					+ CAMPO7    + " = '"+ NuevaComunidad.getsNUTPRC() + "', "
					+ CAMPO8    + " = '"+ NuevaComunidad.getsNOMADC() + "', "
					+ CAMPO9    + " = '"+ NuevaComunidad.getsNUTADC() + "', "
					+ CAMPO10   + " = '"+ NuevaComunidad.getsNODCAD() + "', "
					+ CAMPO11   + " = '"+ NuevaComunidad.getsNUCCEN() + "', "
					+ CAMPO12   + " = '"+ NuevaComunidad.getsNUCCOF() + "', "
					+ CAMPO13   + " = '"+ NuevaComunidad.getsNUCCDI() + "', "
					+ CAMPO14   + " = '"+ NuevaComunidad.getsNUCCNT() + "', "*/
					
					//cifrado de datos personales
					+ CAMPO4    + " = AES_ENCRYPT('"+NuevaComunidad.getsNOMCOC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), " 
					+ CAMPO5    + " = AES_ENCRYPT('"+NuevaComunidad.getsNODCCO()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), " 
					+ CAMPO6    + " = AES_ENCRYPT('"+NuevaComunidad.getsNOMPRC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), " 
					+ CAMPO7    + " = AES_ENCRYPT('"+NuevaComunidad.getsNUTPRC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), " 
					+ CAMPO8    + " = AES_ENCRYPT('"+NuevaComunidad.getsNOMADC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), " 
					+ CAMPO9    + " = AES_ENCRYPT('"+NuevaComunidad.getsNUTADC()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), " 
					+ CAMPO10   + " = AES_ENCRYPT('"+NuevaComunidad.getsNODCAD()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), " 

					+ CAMPO11 + " = '"+ NuevaComunidad.getsCuenta() + "', "

					+ CAMPO12 + " = '"+ NuevaComunidad.getsOBTEXC() + "' "+
					" WHERE "
					+ CAMPO1 + " = '"+ liComunidadID +"'";
			
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

				logger.error("ERROR COMUNIDAD:|"+liComunidadID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean delComunidad(Connection conexion, long liComunidadID)
	{
		boolean bSalida = true;

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO1 + " = '"+ liComunidadID+"'";
			
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

				logger.error("ERROR COMUNIDAD:|"+liComunidadID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}			
		}

		return bSalida;
	}

	public static Comunidad getComunidad(Connection conexion, long liComunidadID)
	{
		String sCOCLDO = "";
		String sNUDCOM = "";
		String sNOMCOC = "";
		String sNODCCO = "";
		String sNOMPRC = "";
		String sNUTPRC = "";
		String sNOMADC = "";
		String sNUTADC = "";
		String sNODCAD = "";
		String sCuenta = "";

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
				       
				       // Datos Personales
				       /*+ CAMPO4  + ","              
				       + CAMPO5  + ","              
				       + CAMPO6  + ","              
				       + CAMPO7  + ","              
				       + CAMPO8  + ","              
				       + CAMPO9  + ","              
				       + CAMPO10 + ","              
				       + CAMPO11 + ","              
				       + CAMPO12 + ","              
				       + CAMPO13 + ","
				       + CAMPO14  + "," */
				       
				       + "AES_DECRYPT("+CAMPO4 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO5 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO8 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO10+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + CAMPO11 + ","
				       + CAMPO12 +            
				       " FROM " 
				       + TABLA + 
				       " WHERE "
				       + CAMPO1 + " = '"+ liComunidadID +"'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");

				logger.debug(CAMPO1 + ":|"+liComunidadID+"|");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;

						sCOCLDO = rs.getString(CAMPO2);  
						sNUDCOM = rs.getString(CAMPO3); 
						
						// Datos Personales
						/*sNOMCOC = rs.getString(CAMPO4);  
						sNODCCO = rs.getString(CAMPO5);  
						sNOMPRC = rs.getString(CAMPO6);  
						sNUTPRC = rs.getString(CAMPO7);  
						sNOMADC = rs.getString(CAMPO8);  
						sNUTADC = rs.getString(CAMPO9);  
						sNODCAD = rs.getString(CAMPO10); 
						sNUCCEN = rs.getString(CAMPO11); 
						sNUCCOF = rs.getString(CAMPO12); 
						sNUCCDI = rs.getString(CAMPO13); 
						sNUCCNT = rs.getString(CAMPO14);*/

						sNOMCOC = rs.getString("AES_DECRYPT("+CAMPO4 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sNODCCO = rs.getString("AES_DECRYPT("+CAMPO5 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sNOMPRC = rs.getString("AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sNUTPRC = rs.getString("AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sNOMADC = rs.getString("AES_DECRYPT("+CAMPO8 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sNUTADC = rs.getString("AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sNODCAD = rs.getString("AES_DECRYPT("+CAMPO10+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sCuenta = rs.getString(CAMPO11); 

						sOBTEXC = rs.getString(CAMPO12); 

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
				sCOCLDO = "";
				sNUDCOM = "";
				sNOMCOC = "";
				sNODCCO = "";
				sNOMPRC = "";
				sNUTPRC = "";
				sNOMADC = "";
				sNUTADC = "";
				sNODCAD = "";
				sCuenta = "";
				sOBTEXC = "";

				logger.error("ERROR COMUNIDAD:|"+liComunidadID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new Comunidad(
				sCOCLDO,
				sNUDCOM,
				sNOMCOC,
				sNODCCO,
				sNOMPRC,
				sNUTPRC,
				sNOMADC,
				sNUTADC,
				sNODCAD,
				sCuenta,
				sOBTEXC);
	}
	
	public static Comunidad getDetallesComunidad(Connection conexion, long liComunidadID)
	{
		String sCOCLDO = "";
		String sNUDCOM = "";
		String sNOMCOC = "";
		String sNODCCO = "";
		String sNOMPRC = "";
		String sNUTPRC = "";
		String sNOMADC = "";
		String sNUTADC = "";
		String sNODCAD = "";
		String sCuenta = "";

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
				       
				       // Datos Personales
				       /*+ CAMPO4  + ","              
				       + CAMPO5  + ","              
				       + CAMPO6  + ","              
				       + CAMPO7  + ","              
				       + CAMPO8  + ","              
				       + CAMPO9  + ","              
				       + CAMPO10 + ","              
				       + CAMPO11 + ","              
				       + CAMPO12 + ","              
				       + CAMPO13 + ","
				       + CAMPO14  + "," */
				       
				       + "AES_DECRYPT("+CAMPO4 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO5 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO8 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO10+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + CAMPO11 + ","
				       + CAMPO12 +            
				       " FROM " 
				       + TABLA + 
				       " WHERE "
				       + CAMPO1 + " = '"+ liComunidadID +"'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");

				logger.debug(CAMPO1 + ":|"+liComunidadID+"|");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;

						sCOCLDO = QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TCOCLDO, QMCodigosControl.ICOCLDO, rs.getString(CAMPO2));  
						sNUDCOM = rs.getString(CAMPO3); 
						
						// Datos Personales
						/*sNOMCOC = rs.getString(CAMPO4);  
						sNODCCO = rs.getString(CAMPO5);  
						sNOMPRC = rs.getString(CAMPO6);  
						sNUTPRC = rs.getString(CAMPO7);  
						sNOMADC = rs.getString(CAMPO8);  
						sNUTADC = rs.getString(CAMPO9);  
						sNODCAD = rs.getString(CAMPO10); 
						sNUCCEN = rs.getString(CAMPO11); 
						sNUCCOF = rs.getString(CAMPO12); 
						sNUCCDI = rs.getString(CAMPO13); 
						sNUCCNT = rs.getString(CAMPO14);*/

						sNOMCOC = rs.getString("AES_DECRYPT("+CAMPO4 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sNODCCO = rs.getString("AES_DECRYPT("+CAMPO5 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sNOMPRC = rs.getString("AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sNUTPRC = rs.getString("AES_DECRYPT("+CAMPO7 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sNOMADC = rs.getString("AES_DECRYPT("+CAMPO8 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sNUTADC = rs.getString("AES_DECRYPT("+CAMPO9 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sNODCAD = rs.getString("AES_DECRYPT("+CAMPO10+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sCuenta = rs.getString(CAMPO11); 

						sOBTEXC = rs.getString(CAMPO12); 

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
				sCOCLDO = "";
				sNUDCOM = "";
				sNOMCOC = "";
				sNODCCO = "";
				sNOMPRC = "";
				sNUTPRC = "";
				sNOMADC = "";
				sNUTADC = "";
				sNODCAD = "";
				sCuenta = "";
				sOBTEXC = "";

				logger.error("ERROR COMUNIDAD:|"+liComunidadID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new Comunidad(
				sCOCLDO,
				sNUDCOM,
				sNOMCOC,
				sNODCCO,
				sNOMPRC,
				sNUTPRC,
				sNOMADC,
				sNUTADC,
				sNODCAD,
				sCuenta,
				sOBTEXC);
	}

	public static long getComunidadID(Connection conexion, String sCodCOCLDO, String sCodNUDCOM)
	{
		long liComunidadID = 0;

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
				       	+ CAMPO2 + " = '"+ sCodCOCLDO +"' AND "
				       	+ CAMPO3 + " = '"+ sCodNUDCOM +"')";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");
				
				logger.debug(CAMPO2 + ":|"+sCodCOCLDO+"|");
				logger.debug(CAMPO3 + ":|"+sCodNUDCOM+"|");

				if (rs != null) 
				{

					while (rs.next()) 
					{
						bEncontrado = true;

						liComunidadID = rs.getLong(CAMPO1);
						
						logger.debug(CAMPO1+":|"+liComunidadID+"|");
						
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
				liComunidadID = 0;
				
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

		return liComunidadID;
	}	
	
	public static boolean existeComunidad(Connection conexion, long liComunidadID)
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
				       	" WHERE "
				       	+ CAMPO1 + " = '"+ liComunidadID +"'";
			
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
				
				logger.error("ERROR Comunidad:|"+liComunidadID+"|");

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
	
	public static boolean setEstado(Connection conexion, long liComunidadID, String sEstado)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO13 + " = '"+ sEstado +"' "+
					" WHERE "
					+ CAMPO1 + " = '"+ liComunidadID +"'";
			
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

				logger.error("ERROR COMUNIDAD:|"+liComunidadID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

			} 
			finally 
			{

				Utils.closeStatement(stmt);
			}			
		}

		return bSalida;
	}
	
	public static String getEstado(Connection conexion, long liComunidadID)
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
						+ CAMPO13 + 
						" FROM " 
						+ TABLA + 
						" WHERE "
						+ CAMPO1 + " = '"+ liComunidadID +"'";
			
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

						sEstado = rs.getString(CAMPO13);
						
						logger.debug(CAMPO1+":|"+liComunidadID+"|");
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO13+":|"+sEstado+"|");
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
				
				logger.error("ERROR COMUNIDAD:|"+liComunidadID+"|");

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
	
	public static String getNombreComunidad(Connection conexion, long liComunidadID)
	{
		String sNombre = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
						+ "AES_DECRYPT("+CAMPO4 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"+ 
						" FROM " 
						+ TABLA + 
						" WHERE "
						+ CAMPO1 + " = '"+ liComunidadID +"'";
			
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

						sNombre = rs.getString("AES_DECRYPT("+CAMPO4 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						
						logger.debug(CAMPO1+":|"+liComunidadID+"|");
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO4+":|"+sNombre+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sNombre = "";
				
				logger.error("ERROR COMUNIDAD:|"+liComunidadID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sNombre;
	}
	
	public static boolean setNota(Connection conexion, long liComunidadID, String sNota)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO14 + " = '"+ sNota +"' "+
					" WHERE "
					+ CAMPO1 + " = '"+ liComunidadID +"'";
			
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

				logger.error("ERROR COMUNIDAD:|"+liComunidadID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

			} 
			finally 
			{

				Utils.closeStatement(stmt);
			}			
		}

		return bSalida;
	}
	
	public static String getNota(Connection conexion, long liComunidadID)
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
						+ CAMPO14 + 
						" FROM " 
						+ TABLA + 
						" WHERE "
						+ CAMPO1 + " = '"+ liComunidadID +"'";
			
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

						sNota = rs.getString(CAMPO14);
						
						logger.debug(CAMPO1+":|"+liComunidadID+"|");
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO14+":|"+sNota+"|");
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
				
				logger.error("ERROR COMUNIDAD:|"+liComunidadID+"|");

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
	
	public static long getCodCuentaComunidad(Connection conexion, long liComunidadID)
	{
		long liCodCuenta = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
						+ CAMPO11 + 
						" FROM " 
						+ TABLA + 
						" WHERE "
						+ CAMPO1 + " = '"+ liComunidadID +"'";
			
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

						liCodCuenta = rs.getLong(CAMPO11);
						
						logger.debug(CAMPO1+":|"+liComunidadID+"|");
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO11+":|"+liCodCuenta+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				liCodCuenta = 0;
				
				logger.error("ERROR COMUNIDAD:|"+liComunidadID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return liCodCuenta;
	}
	
	public static ArrayList<ComunidadTabla> buscaComunidad(Connection conexion, long liCodComunidadID)
	{
		//Sin uso

		ArrayList<ComunidadTabla> resultado = new ArrayList<ComunidadTabla>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					   + CAMPO2 + ","        
					   + CAMPO3 + ","
					   + "AES_DECRYPT("+CAMPO4 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO8 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))" +
					   "  FROM " 
					   + TABLA + 
					   " WHERE "
					   
					   + CAMPO1 +  " = '" + liCodComunidadID + "'";
			
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
						
						String sCOCLDO = rs.getString(CAMPO2);
						String sNUDCOM = rs.getString(CAMPO3);

						String sNOMCOC = rs.getString("AES_DECRYPT("+CAMPO4 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						String sNOMPRC = rs.getString("AES_DECRYPT("+CAMPO6 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						String sNOMADC = rs.getString("AES_DECRYPT("+CAMPO8 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");

						
						String sActivos = ""+QMListaComunidadesActivos.buscaNumeroActivos(conexion, rs.getLong(CAMPO1));
						
						ComunidadTabla comunidadencontrada = new ComunidadTabla(
								Long.toString(liCodComunidadID),
								sCOCLDO,
								sNUDCOM,
								sNOMCOC,
								sNOMPRC,
								sNOMADC,
								sActivos);
						
						resultado.add(comunidadencontrada);
						
						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+liCodComunidadID+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				resultado = new ArrayList<ComunidadTabla>();

				logger.error("ERROR COACES:|"+liCodComunidadID+"|");

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