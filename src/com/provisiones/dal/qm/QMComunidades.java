package com.provisiones.dal.qm;

import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Comunidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QMComunidades
{
	private static Logger logger = LoggerFactory.getLogger(QMComunidades.class.getName());
	
	public static final String TABLA = "pp001_e1_comunidades_tbl";

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
	public static final String CAMPO11 = "nuccen";    
	public static final String CAMPO12 = "nuccof";    
	public static final String CAMPO13 = "nuccdi";    
	public static final String CAMPO14 = "nuccnt";    
	public static final String CAMPO15 = "obtexc";
	
	//Campos de control
	public static final String CAMPO16 = "cod_estado";
	

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
				       + CAMPO14 + "," 
					   + CAMPO15 + "," 
				       + CAMPO16 +               
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
				       + "AES_ENCRYPT('"+NuevaComunidad.getsNODCAD()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       + "AES_ENCRYPT('"+NuevaComunidad.getsNUCCEN()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       + "AES_ENCRYPT('"+NuevaComunidad.getsNUCCOF()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       + "AES_ENCRYPT('"+NuevaComunidad.getsNUCCDI()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				       + "AES_ENCRYPT('"+NuevaComunidad.getsNUCCNT()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'"
				       
				       + NuevaComunidad.getsOBTEXC() + "','" 
				       + ValoresDefecto.DEF_ALTA + "' )";
			
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
	public static boolean modComunidad(Connection conexion, Comunidad NuevaComunidad, String sComunidadID)
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
					+ CAMPO11   + " = AES_ENCRYPT('"+NuevaComunidad.getsNUCCEN()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), " 
					+ CAMPO12   + " = AES_ENCRYPT('"+NuevaComunidad.getsNUCCOF()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), " 
					+ CAMPO13   + " = AES_ENCRYPT('"+NuevaComunidad.getsNUCCDI()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), " 
					+ CAMPO14   + " = AES_ENCRYPT('"+NuevaComunidad.getsNUCCNT()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")), " 

					
					+ CAMPO15 + " = '"+ NuevaComunidad.getsOBTEXC() +
					"' "+
					" WHERE "
					+ CAMPO1 + " = '"+ sComunidadID +"'";
			
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

				logger.error("ERROR COMUNIDAD:|"+sComunidadID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean delComunidad(Connection conexion, String sComunidadID)
	{
		boolean bSalida = true;

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO1 + " = '"+ sComunidadID+"'";
			
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

				logger.error("ERROR COMUNIDAD:|"+sComunidadID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}			
		}

		return bSalida;
	}

	public static Comunidad getComunidad(Connection conexion, String sComunidadID)
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
		String sNUCCEN = "";
		String sNUCCOF = "";
		String sNUCCDI = "";
		String sNUCCNT = "";
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
				       + "AES_DECRYPT("+CAMPO11+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO12+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO13+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"
				       + "AES_DECRYPT("+CAMPO14+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")) ,"

				       + CAMPO15 +               
				       " FROM " 
				       + TABLA + 
				       " WHERE "
				       + CAMPO1 + " = '"+ sComunidadID +"'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");

				logger.debug(CAMPO1 + ":|"+sComunidadID+"|");

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
						sNUCCEN = rs.getString("AES_DECRYPT("+CAMPO11+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sNUCCOF = rs.getString("AES_DECRYPT("+CAMPO12+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sNUCCDI = rs.getString("AES_DECRYPT("+CAMPO13+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
						sNUCCNT = rs.getString("AES_DECRYPT("+CAMPO14+",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");

						sOBTEXC = rs.getString(CAMPO15); 

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
				sNUCCEN = "";
				sNUCCOF = "";
				sNUCCDI = "";
				sNUCCNT = "";
				sOBTEXC = "";

				logger.error("ERROR COMUNIDAD:|"+sComunidadID+"|");

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
				sNUCCEN,
				sNUCCOF,
				sNUCCDI,
				sNUCCNT,
				sOBTEXC);
	}

	public static String getComunidadID(Connection conexion, String sCodCOCLDO, String sCodNUDCOM)
	{
		String sComunidadID = "";

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

						sComunidadID = rs.getString(CAMPO1);
						
						logger.debug(CAMPO1+":|"+sComunidadID+"|");
						
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
				sComunidadID = "";
				
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

		return sComunidadID;
	}	
	
	public static boolean existeComunidad(Connection conexion, String sComunidadID)
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
				       	+ CAMPO1 + " = '"+ sComunidadID +"'";
			
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
				
				logger.error("ERROR Comunidad:|"+sComunidadID+"|");

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
	
	public static boolean setEstado(Connection conexion, String sComunidadID, String sEstado)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO16 + " = '"+ sEstado +"' "+
					" WHERE "
					+ CAMPO1 + " = '"+ sComunidadID +"'";
			
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

				logger.error("ERROR COMUNIDAD:|"+sComunidadID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());

			} 
			finally 
			{

				Utils.closeStatement(stmt);
			}			
		}

		return bSalida;
	}
	
	public static String getEstado(Connection conexion, String sComunidadID)
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
						+ CAMPO16 + 
						" FROM " 
						+ TABLA + 
						" WHERE "
						+ CAMPO1 + " = '"+ sComunidadID +"'";
			
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

						sEstado = rs.getString(CAMPO16);
						
						logger.debug(CAMPO1+":|"+sComunidadID+"|");
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO16+":|"+sEstado+"|");
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
				
				logger.error("ERROR COMUNIDAD:|"+sComunidadID+"|");

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
}