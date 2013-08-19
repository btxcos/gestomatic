package com.provisiones.dal.qm.movimientos;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.types.MovimientoReferenciaCatastral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QMMovimientosReferencias
{
	static String sClassName = QMMovimientosReferencias.class.getName();
	
	static boolean bTrazas = true;

	static String sTable = "e3_movimientos_tbl";

	static String sField1  = "e3_movimiento_id";
	static String sField2  = "cod_codtrn";  
	static String sField3  = "cod_cotdor";  
	static String sField4  = "idprov";      
	static String sField5  = "cod_coacci";  
	static String sField6  = "coengp";       
	static String sField7  = "cod_coaces";
	static String sField8  = "nurcat_id";   
	static String sField9  = "cod_bitc16";  
	static String sField10 = "tircat";      
	static String sField11 = "cod_bitc17";  
	static String sField12 = "enemis";      
	static String sField13 = "cotexa";      
	static String sField14 = "cod_bitc09";  
	static String sField15 = "obtexc";    
	static String sField16 = "obdeer";

	//Ampliacion de valor catastral
	static String sField17 = "cod_bitc23";  
	static String sField18 = "imvsue";      
	static String sField19 = "cod_bitc24";      
	static String sField20 = "imcata";  
	static String sField21 = "cod_bitc25";    
	static String sField22 = "fereca";


	public static int addMovimientoReferenciaCatastral(MovimientoReferenciaCatastral NuevoMovimientoReferenciaCatastral)

	{
		String sMethod = "addMovimientoReferenciaCatastral";
		Statement stmt = null;
		Connection conn = null;
		ResultSet resulset = null;
		
		int iCodigo = 0;

		//boolean bSalida = true;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try {

			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + sTable + " ("
				       + sField2  + ","              
				       + sField3  + ","              
				       + sField4  + ","              
				       + sField5  + ","              
				       + sField6  + ","              
				       + sField7  + ","              
				       + sField8  + ","              
				       + sField9  + ","              
				       + sField10 + ","              
				       + sField11 + ","              
				       + sField12 + ","              
				       + sField13 + ","
				       + sField14 + ","
				       + sField15 + ","
				       + sField16 +
				       //Ampliacion de valor catastral
				       ","
				       + sField17 + ","              
				       + sField18 + ","              
				       + sField19 + ","
				       + sField20 + ","
				       + sField21 + ","
				       + sField22 +
				       
				       ") VALUES ('" 
				       + NuevoMovimientoReferenciaCatastral.getCODTRN() + "','" 
				       + NuevoMovimientoReferenciaCatastral.getCOTDOR() + "','"
				       + NuevoMovimientoReferenciaCatastral.getIDPROV() + "','"
				       + NuevoMovimientoReferenciaCatastral.getCOACCI() + "','"
				       + NuevoMovimientoReferenciaCatastral.getCOENGP() + "','"
				       + NuevoMovimientoReferenciaCatastral.getCOACES() + "','"
				       + NuevoMovimientoReferenciaCatastral.getNURCAT() + "','"
				       + NuevoMovimientoReferenciaCatastral.getBITC16() + "','"
				       + NuevoMovimientoReferenciaCatastral.getTIRCAT() + "','"
				       + NuevoMovimientoReferenciaCatastral.getBITC17() + "','"
				       + NuevoMovimientoReferenciaCatastral.getENEMIS() + "','"
				       + NuevoMovimientoReferenciaCatastral.getCOTEXA() + "','"
				       + NuevoMovimientoReferenciaCatastral.getBITC09() + "','"
				       + NuevoMovimientoReferenciaCatastral.getOBTEXC() + "','"
				       + NuevoMovimientoReferenciaCatastral.getOBDEER()

				       //Ampliacion de valor catastral
				       + "','"
				       + NuevoMovimientoReferenciaCatastral.getBITC23() + "','"
				       + NuevoMovimientoReferenciaCatastral.getIMVSUE() + "','"
				       + NuevoMovimientoReferenciaCatastral.getBITC24() + "','"
				       + NuevoMovimientoReferenciaCatastral.getIMCATA() + "','"
				       + NuevoMovimientoReferenciaCatastral.getBITC25() + "','"
				       + NuevoMovimientoReferenciaCatastral.getFERECA() + "' )"

				       , Statement.RETURN_GENERATED_KEYS);
			
			resulset = stmt.getGeneratedKeys();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			if (resulset.next()) 
			{
				iCodigo= resulset.getInt(1);
			} 
		} 
		catch (SQLException ex) 
		{


			//System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRAP: " + NuevaComunidad.getCOGRAP());
			
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + NuevoMovimientoReferenciaCatastral.getNURCAT());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + NuevoMovimientoReferenciaCatastral.getCOACES());
			
			
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());
			
			//bSalida = false;
		} 
		finally
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
			Utils.closeResultSet(resulset,sClassName,sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return iCodigo;//bSalida;
	}
	public static boolean modMovimientoReferenciaCatastral(MovimientoReferenciaCatastral NuevoMovimientoReferenciaCatastral, String sMovimientoReferenciaCatastralID)
	{
		String sMethod = "modMovimientoReferenciaCatastral";
		Statement stmt = null;
		boolean bSalida = true;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField2  + " = '"+ NuevoMovimientoReferenciaCatastral.getCODTRN() + "', "
					+ sField3  + " = '"+ NuevoMovimientoReferenciaCatastral.getCOTDOR() + "', "
					+ sField4  + " = '"+ NuevoMovimientoReferenciaCatastral.getIDPROV() + "', "
					+ sField5  + " = '"+ NuevoMovimientoReferenciaCatastral.getCOACCI() + "', "
					+ sField6  + " = '"+ NuevoMovimientoReferenciaCatastral.getCOENGP() + "', "
					+ sField7  + " = '"+ NuevoMovimientoReferenciaCatastral.getCOACES() + "', "
					+ sField8  + " = '"+ NuevoMovimientoReferenciaCatastral.getNURCAT() + "', "
					+ sField9  + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC16() + "', "
					+ sField10 + " = '"+ NuevoMovimientoReferenciaCatastral.getTIRCAT() + "', "
					+ sField11 + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC17() + "', "
					+ sField12 + " = '"+ NuevoMovimientoReferenciaCatastral.getENEMIS() + "', "
					+ sField13 + " = '"+ NuevoMovimientoReferenciaCatastral.getCOTEXA() + "', "
					+ sField14 + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC09() + "', "
					+ sField15 + " = '"+ NuevoMovimientoReferenciaCatastral.getOBTEXC() + "', "
					+ sField16 + " = '"+ NuevoMovimientoReferenciaCatastral.getOBDEER() + 

					//Ampliacion de valor catastral
					"', "
					+ sField17 + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC23() + "', "
					+ sField18 + " = '"+ NuevoMovimientoReferenciaCatastral.getIMVSUE() + "', "
					+ sField19 + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC24() + "', "
					+ sField20 + " = '"+ NuevoMovimientoReferenciaCatastral.getIMCATA() + "', "
					+ sField21 + " = '"+ NuevoMovimientoReferenciaCatastral.getBITC25() + "', "
					+ sField22 + " = '"+ NuevoMovimientoReferenciaCatastral.getFERECA() + 
					
					"' "+
					" WHERE "
					+ sField1 + " = '"+ sMovimientoReferenciaCatastralID +"'");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: MovimientoReferenciaCatastralID: " + sMovimientoReferenciaCatastralID);

			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());

			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt, sClassName, sMethod);

		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static boolean delMovimientoReferenciaCatastral(String sMovimientoReferenciaCatastralID)
	{
		String sMethod = "delMovimientoReferenciaCatastral";
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sMovimientoReferenciaCatastralID + "' )");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: MovimientoReferenciaCatastralID: " + sMovimientoReferenciaCatastralID);

			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static MovimientoReferenciaCatastral getMovimientoReferenciaCatastral(String sMovimientoReferenciaCatastralID)
	{
		
		String sMethod = "getMovimientoReferenciaCatastral";

		Statement stmt = null;
		ResultSet rs = null;

		String sCODTRN = "";
		String sCOTDOR = "";
		String sIDPROV = "";
		String sCOACCI = "";
		String sCOENGP = "";
		String sCOACES = "";
		String sNURCAT = "";
		String sBITC16 = "";
		String sTIRCAT = "";
		String sBITC17 = "";
		String sENEMIS = "";
		String sCOTEXA = "";
		String sBITC09 = "";
		String sOBTEXC = "";
		String sOBDEER = "";

		//Ampliacion de valor catastral
		String sBITC23 = "";
		String sIMVSUE = "";
		String sBITC24 = "";
		String sIMCATA = "";
		String sBITC25 = "";
		String sFERECA = "";
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();

		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");
		
		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
				       + sField2  + ","              
				       + sField3  + ","              
				       + sField4  + ","              
				       + sField5  + ","              
				       + sField6  + ","              
				       + sField7  + ","              
				       + sField8  + ","              
				       + sField9  + ","              
				       + sField10 + ","              
				       + sField11 + ","              
				       + sField12 + ","              
				       + sField13 + ","
				       + sField14 + ","
				       + sField15 + ","
				       + sField16 +             

				       //Ampliacion de valor catastral
				       ","
				       + sField17 + ","              
				       + sField18 + ","              
				       + sField19 + ","
				       + sField20 + ","
				       + sField21 + ","
				       + sField22 + 
       
			"  FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sMovimientoReferenciaCatastralID	+ "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

  					sCODTRN = rs.getString(sField2); 
  					sCOTDOR = rs.getString(sField3); 
  					sIDPROV = rs.getString(sField4); 
  					sCOACCI = rs.getString(sField5); 
  					sCOENGP = rs.getString(sField6); 
  					sCOACES = rs.getString(sField7); 
  					sNURCAT = rs.getString(sField8); 
  					sBITC16 = rs.getString(sField9); 
  					sTIRCAT = rs.getString(sField10);
  					sBITC17 = rs.getString(sField11);
  					sENEMIS = rs.getString(sField12);
  					sCOTEXA = rs.getString(sField13);
  					sBITC09 = rs.getString(sField14);
  					sOBTEXC = rs.getString(sField15);
  					sOBDEER = rs.getString(sField16);

  					sBITC23 = rs.getString(sField17);
  					sIMVSUE = rs.getString(sField18);
  					sBITC24 = rs.getString(sField19);
  					sIMCATA = rs.getString(sField20);
  					sBITC25 = rs.getString(sField21);
  					sFERECA = rs.getString(sField22);
  					
  					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

  					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField1 + ": " + sMovimientoReferenciaCatastralID);

				}
			}
			if (found == false) 
			{
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: MovimientoReferenciaCatastralID: " + sMovimientoReferenciaCatastralID);

			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs,sClassName,sMethod);
			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return new MovimientoReferenciaCatastral(sCODTRN, sCOTDOR, sIDPROV, sCOACCI,
				sCOENGP, sCOACES, sNURCAT, sBITC16, sTIRCAT, sBITC17, sENEMIS,
				sCOTEXA, sBITC09, sOBTEXC, sOBDEER

				//Ampliacion de valor catastral
				,sBITC23, sIMVSUE, sBITC24, sIMCATA, sBITC25, sFERECA
				);
	}


	public static boolean existeMovimientoReferenciaCatastral(String sCodReferencia)
	{
		String sMethod = "existeMovimientoReferenciaCatastral";

		Statement stmt = null;
		ResultSet rs = null;

		boolean bSalida = true;

		PreparedStatement pstmt = null;
		boolean found = false;
	

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField16 + "  FROM " + sTable + 
					" WHERE (" + sField3 + " = '" + sCodReferencia + "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

				}
			}
			if (found == false) 
			{
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: CodReferencia: " + sCodReferencia);

			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());
			
			bSalida = false;
		} 
		finally 
		{
			Utils.closeResultSet(rs,sClassName,sMethod);
			Utils.closeStatement(stmt, sClassName, sMethod);
		}

		ConnectionManager.CloseDBConnection(conn);
		return (found && bSalida);
	}
	
}
