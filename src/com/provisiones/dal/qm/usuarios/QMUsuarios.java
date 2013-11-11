package com.provisiones.dal.qm.usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.usuarios.Usuario;

public class QMUsuarios 
{
	private static Logger logger = LoggerFactory.getLogger(QMUsuarios.class.getName());
	
	public static final String TABLA = "pp001_usuarios_tbl";

	public static final String CAMPO1  = "usuario_id";
	public static final String CAMPO2  = "login";
	public static final String CAMPO3  = "password";
	public static final String CAMPO4  = "nombre";
	public static final String CAMPO5  = "apellido1";
	public static final String CAMPO6  = "apellido2";
	public static final String CAMPO7  = "contacto";
	public static final String CAMPO8  = "fecha_alta";
	public static final String CAMPO9  = "fecha_modificacion";
	public static final String CAMPO10  = "tipo_usuario";
	public static final String CAMPO11  = "activo";
	
	public static long addUsuario (Usuario NuevoUsuario) 
	 
	{
		Connection conn = null;

		Statement stmt = null;
		ResultSet resulset = null;
		
		conn = ConnectionManager.getDBConnection();

		long iCodigo = 0;
		
		String sFecha = Utils.fechaDeHoy(false);

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
			       + CAMPO11 +               
          
			       ") VALUES ('"        
			       + NuevoUsuario.getsLogin() + "'," 
			       + "AES_ENCRYPT('"+ NuevoUsuario.getsPassword()+"',SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),'"
			       + NuevoUsuario.getsNombre() + "','"  
			       + NuevoUsuario.getsApellido1() + "','"  
			       + NuevoUsuario.getsApellido2() + "','"  
			       + NuevoUsuario.getsContacto() + "','"  
			       + sFecha + "','"  
			       + sFecha + "','"  
			       + NuevoUsuario.getsTipoUsuario() + "',b'"  
			       + ValoresDefecto.ACTIVO + "' )";
		
		logger.debug(sQuery);


		try 
		{
			
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery, Statement.RETURN_GENERATED_KEYS);

			resulset = stmt.getGeneratedKeys();
			
			if (resulset.next()) 
			{
				iCodigo= resulset.getLong(1);
			} 

			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			iCodigo = 0;
			
			logger.error("ERROR USUARIO:|"+NuevoUsuario.getsLogin()+"|");

			
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
		} 
		finally 
		{
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		
		return iCodigo;
	}
	
	public static boolean modUsuario(Usuario NuevoUsuario, String sUsuarioID)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				//+ CAMPO2 + " = '"+ NuevoUsuario.getsLogin() + "', "
				+ CAMPO3 + " = '"+ NuevoUsuario.getsPassword() + "', "
				+ CAMPO4 + " = '"+ NuevoUsuario.getsNombre() + "', "
				+ CAMPO5 + " = '"+ NuevoUsuario.getsApellido1() + "', "
				+ CAMPO6 + " = '"+ NuevoUsuario.getsApellido2() + "', "
				+ CAMPO7 + " = '"+ NuevoUsuario.getsContacto() + "', "
				//+ CAMPO8 + " = '"+ NuevoUsuario.getsFechaAlta() + "', "
				+ CAMPO9 + " = '"+ Utils.fechaDeHoy(false) + "', "
				+ CAMPO10 + " = '"+ NuevoUsuario.getsTipoUsuario() + "', "
				+ CAMPO11 + " = '"+ NuevoUsuario.getsActivo() + "' "+
				" WHERE " 
				+ CAMPO1 + " = '"+ sUsuarioID + "'";

		
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);

			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			bSalida = false;

			logger.error("ERROR USUARIO:|"+NuevoUsuario.getsLogin()+"|");


			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	
	public static boolean delUsuario(String sUsuarioID)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true; 

		logger.debug("Ejecutando Query...");
		
		String sQuery = "DELETE FROM " 
				+ TABLA + 
				" WHERE "
				+ CAMPO1  + " = '"+ sUsuarioID +"'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR USUARIO_ID:|"+sUsuarioID+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static Usuario getUsuario(String sUsuarioID)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sLogin = "";
		String sPassword = "";
		String sNombre = "";
		String sApellido1 = "";
		String sApellido2 = "";	
		String sContacto = "";
		String sFechaAlta = "";
		String sFechaModificacion = "";
		String sTipoUsuario = "";	
		String sActivo = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
				   + CAMPO2  + ","
				   + "AES_DECRYPT("+ CAMPO3 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+")),"
				   //+ CAMPO3  + ","
				   + CAMPO4  + ","
				   + CAMPO5  + ","
			       + CAMPO6  + ","
			       + CAMPO7  + ","
			       + CAMPO8  + ","              
			       + CAMPO9  + ","              
			       + CAMPO10 + ","              
			       + CAMPO11 +      
			       "  FROM " 
			       + TABLA + 
			       " WHERE "
			       + CAMPO1  + " = '"+ sUsuarioID +"'";
		
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

					
					sLogin = rs.getString(CAMPO2); 
					sPassword = rs.getString("AES_DECRYPT("+ CAMPO3 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
					//sPassword = rs.getString(CAMPO3);
					sNombre = rs.getString(CAMPO4);
					sApellido1 = rs.getString(CAMPO5); 
					sApellido2 = rs.getString(CAMPO6);  
					sContacto = rs.getString(CAMPO7);
					sFechaAlta = rs.getString(CAMPO8);  
					sFechaModificacion = rs.getString(CAMPO9);  
					sTipoUsuario = rs.getString(CAMPO10); 
					sActivo = rs.getString(CAMPO11); 
					
					logger.debug("Encontrado el registro!");

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR USUARIO_ID:|"+sUsuarioID+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		
		
		return new Usuario(sLogin, sPassword, sNombre, sApellido1,
				sApellido2, sContacto, sFechaAlta, sFechaModificacion, sTipoUsuario, sActivo);
	}
	
	public static String getUsuarioID(String sLogin)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sUsuarioID = "";


		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
			       + CAMPO1 +      
			       " FROM " 
			       + TABLA + 
			       " WHERE "	
			       + CAMPO2  + " = '"+ sLogin +"' )";

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

					sUsuarioID = rs.getString(CAMPO1);  
					
					logger.debug("Encontrado el registro!");

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			sUsuarioID = "";

			logger.error("ERROR USUARIO:|"+sLogin+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return sUsuarioID;
	}
	
	public static boolean existeUsuario(String sLogin)
	{
		Connection conn = null;
		conn = ConnectionManager.openDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
					+ CAMPO1  +       
					" FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO2 + " = '"+ sLogin +"'";
		
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

					logger.debug("Encontrado el registro!");

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			found = false;

			logger.error("ERROR USUARIO:|"+sLogin+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.closeDBConnection(conn);
		return found;
	}
	
	public static boolean setEstado(String sUsuarioID, String sEstado)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO11 + " = '"+ sEstado + "' "+
				" WHERE "
				+ CAMPO1  + " = b'"+ sUsuarioID +"'";
		
		logger.debug(sQuery);
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR USUARIO_ID:|"+sUsuarioID+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	
	public static String getEstado(String sUsuarioID)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sEstado = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
				+ CAMPO11 + 
				" FROM "
				+ TABLA + 
				" WHERE "
				+ CAMPO1  + " = '"+ sUsuarioID +"'";
		
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

					sEstado = rs.getString(CAMPO11);
					
					
					logger.debug("Encontrado el registro!");

					logger.debug("{}:|"+CAMPO11,sEstado);

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR USUARIO_ID:|"+sUsuarioID);

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return sEstado;
	}
	
	public static String getPassword(String sUsuario)
	{
		Connection conn = null;
		conn = ConnectionManager.openDBConnection();

		Statement stmt = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sPassword = "";

		boolean found = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
				+ "AES_DECRYPT("+ CAMPO3 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))"+
				//+ CAMPO3 + 
				" FROM "
				+ TABLA + 
				" WHERE "
				+ CAMPO2  + " = '"+ sUsuario +"'";
		
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

					
					sPassword = rs.getString("AES_DECRYPT("+ CAMPO3 +",SHA2('"+ValoresDefecto.CIFRADO_LLAVE_SIMETRICA+"',"+ValoresDefecto.CIFRADO_LONGITUD+"))");
					//sPassword = rs.getString(CAMPO3);
					
					
					logger.debug("Encontrado el registro!");

					//logger.debug("{}:|"+CAMPO3,sPassword);

				}
			}
			if (found == false) 
			{
				logger.debug("No se encontró la información.");
			}

		} 
		catch (SQLException ex) 
		{
			found = false;

			logger.error("ERROR USUARIO:|"+sUsuario);

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		ConnectionManager.closeDBConnection(conn);
		return sPassword;
	}
}
