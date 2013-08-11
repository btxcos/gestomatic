package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Comunidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QMComunidades
{
	static String sClassName = QMComunidades.class.getName();
	
	static boolean bTrazas = true;

	public static final String sTable = "e1_comunidades_tbl";

	public static final String sField1  = "cod_cocldo";
	
	public static final String sField2  = "nudcom_id"; 
	
	public static final String sField3  = "nomcoc";    
	public static final String sField4  = "nodcco";    
	public static final String sField5  = "nomprc";    
	public static final String sField6  = "nutprc";    
	public static final String sField7  = "nomadc";    
	public static final String sField8  = "nutadc";    
	public static final String sField9  = "nodcad";    
	public static final String sField10 = "nuccen";    
	public static final String sField11 = "nuccof";    
	public static final String sField12 = "nuccdi";    
	public static final String sField13 = "nuccnt";    
	public static final String sField14 = "obtexc";

	public static final String sField15 = "cod_estado";
	

	public static boolean addComunidad(Comunidad NuevaComunidad)

	{
		String sMethod = "addComunidad";
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try {

			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + sTable + " ("
					   + sField1  + "," 
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
				       + sField15 +               
				       ") VALUES ('" 
				       + NuevaComunidad.getCOCLDO() + "','" 
				       + NuevaComunidad.getNUDCOM() + "','"
				       + NuevaComunidad.getNOMCOC() + "','"
				       + NuevaComunidad.getNODCCO() + "','"
				       + NuevaComunidad.getNOMPRC() + "','"
				       + NuevaComunidad.getNUTPRC() + "','"
				       + NuevaComunidad.getNOMADC() + "','"
				       + NuevaComunidad.getNUTADC() + "','"
				       + NuevaComunidad.getNODCAD() + "','"
				       + NuevaComunidad.getNUCCEN() + "','"
				       + NuevaComunidad.getNUCCOF() + "','"
				       + NuevaComunidad.getNUCCDI() + "','"
				       + NuevaComunidad.getNUCCNT() + "','"
				       + NuevaComunidad.getOBTEXC() + "','" 
				       + ValoresDefecto.DEF_PENDIENTE + "' )");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{


			
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COCLDO: " + NuevaComunidad.getCOCLDO());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NUDCOM: " + NuevaComunidad.getNUDCOM());
			
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
	public static boolean modComunidad(Comunidad NuevaComunidad, String sCodCOCLDO, String sCodNUDCOM)
	{
		String sMethod = "modComunidad";
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
					//+ sField1  + " = '"+ NuevaComunidad.getCOCLDO() + "', "
					//+ sField2  + " = '"+ NuevaComunidad.getNUDCOM() + "', "
					+ sField3  + " = '"+ NuevaComunidad.getNOMCOC() + "', "
					+ sField4  + " = '"+ NuevaComunidad.getNODCCO() + "', "
					+ sField5  + " = '"+ NuevaComunidad.getNOMPRC() + "', "
					+ sField6  + " = '"+ NuevaComunidad.getNUTPRC() + "', "
					+ sField7  + " = '"+ NuevaComunidad.getNOMADC() + "', "
					+ sField8  + " = '"+ NuevaComunidad.getNUTADC() + "', "
					+ sField9  + " = '"+ NuevaComunidad.getNODCAD() + "', "
					+ sField10 + " = '"+ NuevaComunidad.getNUCCEN() + "', "
					+ sField11 + " = '"+ NuevaComunidad.getNUCCOF() + "', "
					+ sField12 + " = '"+ NuevaComunidad.getNUCCDI() + "', "
					+ sField13 + " = '"+ NuevaComunidad.getNUCCNT() + "', "
					+ sField14 + " = '"+ NuevaComunidad.getOBTEXC() +
					"' "+
					" WHERE " +
					"("+ sField1 + " = '"+ sCodCOCLDO +"' AND "+
						sField2 + " = '"+ sCodNUDCOM +"')");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COCLDO: " + NuevaComunidad.getCOCLDO());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NUDCOM: " + NuevaComunidad.getNUDCOM());

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

	public static boolean delComunidad(String sCodCOCLDO, String sCodNUDCOM)
	{
		String sMethod = "delComunidad";
		Statement stmt = null;
		Connection conn = null;
		
		boolean bSalida = true;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE " +
					"("+ sField1 + " = '"+ sCodCOCLDO +"' AND "+
					sField2 + " = '"+ sCodNUDCOM +"')");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COCLDO: " + sCodCOCLDO);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NUDCOM: " + sCodNUDCOM);

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

	public static Comunidad getComunidad(String sCodCOCLDO, String sCodNUDCOM)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getComunidad";

		Statement stmt = null;
		ResultSet rs = null;

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

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
				       + sField1  + ","              
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
				       + sField14 +               
       
			"  FROM " + sTable + 
					" WHERE " +
					"("+ sField1 + " = '"+ sCodCOCLDO +"' AND "+
					sField2 + " = '"+ sCodNUDCOM +"')");
			

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField1 + ": " + sCodCOCLDO);
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField2 + ": " + sCodNUDCOM);

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCOCLDO = rs.getString(sField1);  
					sNUDCOM = rs.getString(sField2);  
					sNOMCOC = rs.getString(sField3);  
					sNODCCO = rs.getString(sField4);  
					sNOMPRC = rs.getString(sField5);  
					sNUTPRC = rs.getString(sField6);  
					sNOMADC = rs.getString(sField7);  
					sNUTADC = rs.getString(sField8);  
					sNODCAD = rs.getString(sField9);  
					sNUCCEN = rs.getString(sField10); 
					sNUCCOF = rs.getString(sField11); 
					sNUCCDI = rs.getString(sField12); 
					sNUCCNT = rs.getString(sField13); 
					sOBTEXC = rs.getString(sField14); 


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
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COCLDO: " + sCodCOCLDO);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NUDCOM: " + sCodNUDCOM);

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
	
	public static boolean setEstado(String sCodCOCLDO, String sCodNUDCOM, String sEstado)
	{
		String sMethod = "setEstado";
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
					+ sField15 + " = '"+ sEstado + 
					"' "+
					" WHERE "+
					"("+ sField1 + " = '"+ sCodCOCLDO +"' AND "+
					sField2 + " = '"+ sCodNUDCOM +"')");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COCLDO: " + sCodCOCLDO);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NUDCOM: " + sCodNUDCOM);

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
	
	public static String getEstado(String sCodCOCLDO, String sCodNUDCOM)
	{
		String sMethod = "getEstado";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sEstado = "";

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField15 + "  FROM " + sTable + 
					" WHERE " +
					"("+ sField1 + " = '"+ sCodCOCLDO +"' AND "+
					sField2 + " = '"+ sCodNUDCOM +"')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sEstado = rs.getString(sField15);
					
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField15 + ": " + sEstado);
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField1 + ": " + sCodCOCLDO);
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField2 + ": " + sCodNUDCOM);


				}
			}
			if (found == false) 
			{
 
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COCLDO: " + sCodCOCLDO);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NUDCOM: " + sCodNUDCOM);

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
		return sEstado;
	}
}