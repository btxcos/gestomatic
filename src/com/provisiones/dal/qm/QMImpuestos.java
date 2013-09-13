package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ImpuestoRecurso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QMImpuestos 
{
	static String sClassName = QMImpuestos.class.getName();
	
	static boolean bTrazas = true;

	public static final String sTable = "e4_impuestos_tbl";

	public static final String sField1  = "cod_nurcat";    
	public static final String sField2 = "cod_cosbac";
	public static final String sField3 = "feprre";    
	public static final String sField4 = "ferere";    
	public static final String sField5 = "fedein";    
	public static final String sField6 = "cod_bisode";
	public static final String sField7 = "cod_bireso";
	public static final String sField8 = "cotexa";    
	public static final String sField9 = "obtexc";

	public static final String sField10 = "cod_estado";

	public static boolean addImpuesto(ImpuestoRecurso NuevoImpuestoRecurso)

	{
		String sMethod = "addImpuesto";
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
				       + sField10  +  
				       ") VALUES ('" 
				       + NuevoImpuestoRecurso.getNURCAT() + "','"
				       + NuevoImpuestoRecurso.getCOSBAC() + "','"
				       + NuevoImpuestoRecurso.getFEPRRE() + "','"
				       + NuevoImpuestoRecurso.getFERERE() + "','"
				       + NuevoImpuestoRecurso.getFEDEIN() + "','"
				       + NuevoImpuestoRecurso.getBISODE() + "','"
				       + NuevoImpuestoRecurso.getBIRESO() + "','"
				       + NuevoImpuestoRecurso.getCOTEXA() + "','"
				       + NuevoImpuestoRecurso.getOBTEXC() + "','" 
				       + ValoresDefecto.DEF_ALTA + "' )");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
		
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + NuevoImpuestoRecurso.getNURCAT());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBAC: " + NuevoImpuestoRecurso.getCOSBAC());
			
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

	public static boolean modImpuestoRecurso(ImpuestoRecurso NuevoImpuestoRecurso, String sCodNURCAT, String sCodCOSBAC)
	{
		String sMethod = "modImpuestoRecurso";
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
					+ sField1 + " = '"+ NuevoImpuestoRecurso.getNURCAT() + "', "
					+ sField2 + " = '"+ NuevoImpuestoRecurso.getCOSBAC() + "', "
					+ sField3 + " = '"+ NuevoImpuestoRecurso.getFEPRRE() + "', "
					+ sField4 + " = '"+ NuevoImpuestoRecurso.getFERERE() + "', "
					+ sField5 + " = '"+ NuevoImpuestoRecurso.getFEDEIN() + "', "
					+ sField6 + " = '"+ NuevoImpuestoRecurso.getBISODE() + "', "
					+ sField7 + " = '"+ NuevoImpuestoRecurso.getBIRESO() + "', "
					+ sField8 + " = '"+ NuevoImpuestoRecurso.getCOTEXA() + "', "
					+ sField9 + " = '"+ NuevoImpuestoRecurso.getOBTEXC() +
					"' "+
					" WHERE "+
					"(" + sField1 + " = '" + sCodNURCAT + "' AND " +
					 sField2 + " = '" + sCodCOSBAC + "' )");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + sCodNURCAT);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBAC: " + sCodCOSBAC);

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

	public static boolean delImpuestoRecurso(String sCodNURCAT, String sCodCOSBAC)
	{
		String sMethod = "delImpuestoRecurso";
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
					"(" + sField1 + " = '" + sCodNURCAT + "' AND " +
							 sField2 + " = '" + sCodCOSBAC + "' )");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + sCodNURCAT);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBAC: " + sCodCOSBAC);

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

	public static ImpuestoRecurso getImpuestoRecurso(String sCodNURCAT, String sCodCOSBAC)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getImpuestoRecurso";

		Statement stmt = null;
		ResultSet rs = null;


		String sNURCAT = "";
		String sCOSBAC = "";
		String sFEPRRE = "";
		String sFERERE = "";
		String sFEDEIN = "";
		String sBISODE = "";
		String sBIRESO = "";
		String sCOTEXA = "";
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
				       + sField9  +              
       
			"  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodNURCAT + "' AND " +
					 sField2 + " = '" + sCodCOSBAC + "' )");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sNURCAT = rs.getString(sField1); 
					sCOSBAC = rs.getString(sField2);
					sFEPRRE = rs.getString(sField3);
					sFERERE = rs.getString(sField4);
					sFEDEIN = rs.getString(sField5);
					sBISODE = rs.getString(sField6);
					sBIRESO = rs.getString(sField7);
					sCOTEXA = rs.getString(sField8);
					sOBTEXC = rs.getString(sField9);

					
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField1 + ": " + sNURCAT);
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField2 + ": " + sCOSBAC);

				}
			}
			if (found == false) 
			{
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + sCodNURCAT);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBAC: " + sCodCOSBAC);

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
		return new ImpuestoRecurso(sNURCAT, sCOSBAC, sFEPRRE, sFERERE, sFEDEIN, sBISODE, sBIRESO, sCOTEXA, sOBTEXC);
	}

	public static boolean existeImpuestoRecurso(String sCodNURCAT, String sCodCOSBAC)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getImpuestoRecurso";

		Statement stmt = null;
		ResultSet rs = null;

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
				       + sField2  +       
				       "  FROM " + sTable + 
				       " WHERE " +
				       "(" + 
				       sField1 + " = '" + sCodNURCAT + "' AND " +
				       sField2 + " = '" + sCodCOSBAC + "' " +
						")");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;



					
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField1 + ": " + sCodNURCAT);
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField2 + ": " + sCodCOSBAC);

				}
			}
			if (found == false) 
			{
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + sCodNURCAT);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBAC: " + sCodCOSBAC);

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
		return found;
	}
	
	
	public static boolean tieneImpuestoRecurso(String sCodNURCAT)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getImpuestoRecurso";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
				       + sField2  + 
			"  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodNURCAT + "' AND " +
					sField10  + " <> 'B' )");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;


					
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField1 + ": " + sCodNURCAT);

				}
			}
			if (found == false) 
			{
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + sCodNURCAT);

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
		return found;
	}

/*	public static String getImpuestoRecursoID(ImpuestoRecurso impuesto)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getImpuestoRecursoID";

		Statement stmt = null;
		ResultSet rs = null;

		String sImpuestoID = "";

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					+ sField1 + 
					"  FROM " + sTable + 
						" WHERE ("
					       + sField2  +" = '" + impuesto.getCODTRN() + "' AND "
					       + sField3  +" = '" + impuesto.getCOTDOR() + "' AND "
					       + sField4  +" = '" + impuesto.getIDPROV() + "' AND "
					       + sField5  +" = '" + impuesto.getCOACCI() + "' AND "
					       + sField6  +" = '" + impuesto.getCOENGP() + "' AND "
					       + sField7  +" = '" + impuesto.getCOACES() + "' AND "
					       + sField8  +" = '" + impuesto.getNURCAT() + "' AND "
					       + sField9  +" = '" + impuesto.getCOGRUC() + "' AND "
					       + sField10 +" = '" + impuesto.getCOTACA() + "' AND "
					       + sField11 +" = '" + impuesto.getCOSBAC() + "' AND "
					       + sField12 +" = '" + impuesto.getBITC18() + "' AND "
					       + sField13 +" = '" + impuesto.getFEPRRE() + "' AND "
					       + sField14 +" = '" + impuesto.getBITC19() + "' AND "
					       + sField15 +" = '" + impuesto.getFERERE() + "' AND "
					       + sField16 +" = '" + impuesto.getBITC20() + "' AND "
					       + sField17 +" = '" + impuesto.getFEDEIN() + "' AND "
					       + sField18 +" = '" + impuesto.getBITC21() + "' AND "
					       + sField19 +" = '" + impuesto.getBISODE() + "' AND "
					       + sField20 +" = '" + impuesto.getBITC22() + "' AND "
					       + sField21 +" = '" + impuesto.getBIRESO() + "' AND "
					       + sField22 +" = '" + impuesto.getCOTEXA() + "' AND "
					       + sField23 +" = '" + impuesto.getBITC09() + "' AND "
					       + sField24 +" = '" + impuesto.getOBTEXC() + "' AND "
					       + sField25 +" = '" + impuesto.getOBDEER() + "' )"); 

			rs = pstmt.executeQuery();

			//System.out.println("===================================================");
			//System.out.println(sField1 + ": " + sCuotaID);

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sImpuestoID = rs.getString(sField1);
					System.out.println(sField1 + ": " + sImpuestoID);
				}
			}
			if (found == false) 
			{
				System.out.println("No Information Found");
			}

		} 
		catch (SQLException ex) 
		{

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
		return sImpuestoID;
	}*/

	public static boolean setEstado(String sCodNURCAT, String sCodCOSBAC, String sEstado)
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
					+ sField10 + " = '"+ sEstado + 
					"' "+
					" WHERE "+
					"(" + sField1 + " = '" + sCodNURCAT + "' AND " +
						sField2 + " = '" + sCodCOSBAC + "' )");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + sCodNURCAT);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBAC: " + sCodCOSBAC);

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
	
	public static String getEstado(String sCodNURCAT, String sCodCOSBAC)
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


			pstmt = conn.prepareStatement("SELECT " + sField10 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodNURCAT + "' AND " +
					sField2 + " = '" + sCodCOSBAC + "' )");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

			rs = pstmt.executeQuery();
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sEstado = rs.getString(sField10);
					
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField10 + ": " + sEstado);


				}
			}
			if (found == false) 
			{
 
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NURCAT: " + sCodNURCAT);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBAC: " + sCodCOSBAC);

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
