
package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Gasto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QMGastos
{
	static String sClassName = QMGastos.class.getName();
	
	static boolean bTrazas = true;

	public static final String sTable = "ga_gastos_tbl";

	
	
	public static final String sField1  = "cod_coaces";
	public static final String sField2  = "cod_cogrug";    
	public static final String sField3  = "cotpga";    
	public static final String sField4  = "cosbga";    
	public static final String sField5  = "fedeve";
	
	public static final String sField6  = "importe";
	
	public static final String sField7  = "cod_estado";
	
	public static int addGasto (Gasto NuevoGasto) 
	 
	{
		String sMethod = "addGasto";
		Statement stmt = null;
		Connection conn = null;
		ResultSet resulset = null;
		
		int iCodigo = 0;
		
		//boolean bSalida = true;
		

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");
		
		try 
		{
			
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + sTable + " ("
				       + sField1  + ","              
				       + sField2  + ","              
				       + sField3  + ","              
				       + sField4  + ","
				       + sField5  + ","
				       + sField6  + ","
				       + sField7  +              
                 
				       					") VALUES ('"        
				 				       + NuevoGasto.getCOACES() + "','"
				 				       + NuevoGasto.getCOGRUG() + "','"
								       + NuevoGasto.getCOTPGA() + "','"  
								       + NuevoGasto.getCOSBGA() + "','"  
								       + NuevoGasto.getFEDEVE() + "','"
								       + NuevoGasto.getIMPORTE() + "','"
								       + ValoresDefecto.DEF_ESTADO_GASTO + "' )", Statement.RETURN_GENERATED_KEYS);

			resulset = stmt.getGeneratedKeys();
			
			if (resulset.next()) 
			{
				iCodigo= resulset.getInt(1);
			} 
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
	
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + NuevoGasto.getCOACES());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRUG: " + NuevoGasto.getCOGRUG());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COTPGA: " + NuevoGasto.getCOTPGA());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBGA: " + NuevoGasto.getCOSBGA());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: FEDEVE: " + NuevoGasto.getFEDEVE());
			
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
		
		return iCodigo;
	}
	public static boolean modGasto(Gasto NuevoGasto)
	{
		String sMethod = "modGasto";
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
					+ sField6  + " = '"+ NuevoGasto.getIMPORTE() +  
					"' "+
					" WHERE " +
					"("	+ 
						sField1  + " = '"+ NuevoGasto.getCOACES() +"' AND " +
						sField2  + " = '"+ NuevoGasto.getCOGRUG() +"' AND " +
						sField3  + " = '"+ NuevoGasto.getCOTPGA() +"' AND " +
						sField4  + " = '"+ NuevoGasto.getCOSBGA() +"' AND " +
					    sField5  + " = '"+ NuevoGasto.getIMPORTE() + "' )");

			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + NuevoGasto.getCOACES());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRUG: " + NuevoGasto.getCOGRUG());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COTPGA: " + NuevoGasto.getCOTPGA());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBGA: " + NuevoGasto.getCOSBGA());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: FEDEVE: " + NuevoGasto.getFEDEVE());

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

	public static boolean delGasto(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{
		String sMethod = "delGasto";
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
					"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
					sField2  + " = '"+ sCodCOGRUG +"' AND " +
					sField3  + " = '"+ sCodCOTPGA +"' AND " +
					sField4  + " = '"+ sCodCOSBGA +"' AND " +
				    sField5  + " = '"+ sFEDEVE + "' )");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + sCodCOACES);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRUG: " + sCodCOGRUG);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COTPGA: " + sCodCOTPGA);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBGA: " + sCodCOSBGA);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: FEDEVE: " + sFEDEVE);

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

	public static Gasto getGasto(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getGasto";

		Statement stmt = null;
		ResultSet rs = null;

		String sIMPORTE = "";


		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
				       + sField6  +       
			"  FROM " + sTable + 
			" WHERE "+
			"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
			sField2  + " = '"+ sCodCOGRUG +"' AND " +
			sField3  + " = '"+ sCodCOTPGA +"' AND " +
			sField4  + " = '"+ sCodCOSBGA +"' AND " +
		    sField5  + " = '"+ sFEDEVE + "' )");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			


			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sIMPORTE = rs.getString(sField6); 
					
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
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + sCodCOACES);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRUG: " + sCodCOGRUG);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COTPGA: " + sCodCOTPGA);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBGA: " + sCodCOSBGA);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: FEDEVE: " + sFEDEVE);

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
		return new Gasto(sCodCOACES, sCodCOGRUG, sCodCOTPGA, sCodCOSBGA, sFEDEVE, sIMPORTE);
	}
	
	public static boolean existeGasto(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "existeGasto";

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
				       + sField6  +       
			"  FROM " + sTable + 
			" WHERE "+
			"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
			sField2  + " = '"+ sCodCOGRUG +"' AND " +
			sField3  + " = '"+ sCodCOTPGA +"' AND " +
			sField4  + " = '"+ sCodCOSBGA +"' AND " +
		    sField5  + " = '"+ sFEDEVE + "' )");

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
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + sCodCOACES);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRUG: " + sCodCOGRUG);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COTPGA: " + sCodCOTPGA);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBGA: " + sCodCOSBGA);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: FEDEVE: " + sFEDEVE);

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

	public static boolean setEstado(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE, String sEstado)
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
					+ sField7 + " = '"+ sEstado + 
					"' "+
					" WHERE "+
					"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
					sField2  + " = '"+ sCodCOGRUG +"' AND " +
					sField3  + " = '"+ sCodCOTPGA +"' AND " +
					sField4  + " = '"+ sCodCOSBGA +"' AND " +
				    sField5  + " = '"+ sFEDEVE + "' )");
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + sCodCOACES);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRUG: " + sCodCOGRUG);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COTPGA: " + sCodCOTPGA);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBGA: " + sCodCOSBGA);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: FEDEVE: " + sFEDEVE);

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
	
	public static String getEstado(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getEstado";

		Statement stmt = null;
		ResultSet rs = null;

		String sEstado = "";

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					+ sField7 + 
					"  FROM " + sTable + 
						" WHERE " +
						"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
						sField2  + " = '"+ sCodCOGRUG +"' AND " +
						sField3  + " = '"+ sCodCOTPGA +"' AND " +
						sField4  + " = '"+ sCodCOSBGA +"' AND " +
					    sField5  + " = '"+ sFEDEVE + "' )");


			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sEstado = rs.getString(sField7);
					
					
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField7 + ": " + sEstado);

				}
			}
			if (found == false) 
			{
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + sCodCOACES);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRUG: " + sCodCOGRUG);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COTPGA: " + sCodCOTPGA);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBGA: " + sCodCOSBGA);
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: FEDEVE: " + sFEDEVE);

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
