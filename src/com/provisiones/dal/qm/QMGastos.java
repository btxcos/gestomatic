
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
	
	public static final String sField5  = "cod_ptpago";

	public static final String sField6  = "fedeve";

	public static final String sField7  = "ffgtvp";    
	public static final String sField8  = "fepaga";    
	public static final String sField9  = "felipg";    
	public static final String sField10 = "cod_cosiga";
	public static final String sField11 = "feeesi";    
	public static final String sField12 = "feecoi";    
	public static final String sField13 = "feeaui";    
	public static final String sField14 = "feepai";    
	public static final String sField15 = "imngas";    
	public static final String sField16 = "ycos02";    
	public static final String sField17 = "imrgas";    
	public static final String sField18 = "ycos04";    
	public static final String sField19 = "imdgas";    
	public static final String sField20 = "ycos06";    
	public static final String sField21 = "imcost";    
	public static final String sField22 = "ycos08";    
	public static final String sField23 = "imogas";    
	public static final String sField24 = "ycos10";    
	public static final String sField25 = "imdtga";    
	public static final String sField26 = "imimga";    
	public static final String sField27 = "cod_coimpt";
	public static final String sField28 = "cod_cotneg";
	public static final String sField29 = "feagto";    
	public static final String sField30 = "cod_comona";
	public static final String sField31 = "cod_biauto";
	public static final String sField32 = "feaufa";    
	public static final String sField33 = "fepgpr";
	
	public static final String sField34  = "cod_estado";
	
	public static boolean addGasto (Gasto NuevoGasto, String sEstado) 
	 
	{
		String sMethod = "addGasto";
		Statement stmt = null;
		Connection conn = null;
		ResultSet resulset = null;
		
		int iCodigo = 0;
		
		boolean bSalida = true;
		

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
				       + sField7  + ","              
				       + sField8  + ","              
				       + sField9  + ","              
				       + sField10 + ","              
				       + sField11 + ","              
				       + sField12 + ","              
				       + sField13 + ","              
				       + sField14 + ","              
				       + sField15 + ","              
				       + sField16 + ","              
				       + sField17 + ","              
				       + sField18 + ","              
				       + sField19 + ","              
				       + sField20 + ","              
				       + sField21 + ","              
				       + sField22 + ","              
				       + sField23 + ","              
				       + sField24 + ","              
				       + sField25 + ","              
				       + sField26 + ","              
				       + sField27 + ","              
				       + sField28 + ","              
				       + sField29 + ","              
				       + sField30 + ","              
				       + sField31 + ","              
				       + sField32 + ","              
				       + sField33 + ","              
				       + sField34 +               
                 
				       ") VALUES ('"        
				       + NuevoGasto.getCOACES() + "','"
				       + NuevoGasto.getCOGRUG() + "','"
				       + NuevoGasto.getCOTPGA() + "','"  
				       + NuevoGasto.getCOSBGA() + "','"  
				       + NuevoGasto.getPTPAGO() + "','"  
				       + NuevoGasto.getFEDEVE() + "','"  
				       + NuevoGasto.getFFGTVP() + "','"  
				       + NuevoGasto.getFEPAGA() + "','"  
				       + NuevoGasto.getFELIPG() + "','"  
				       + NuevoGasto.getCOSIGA() + "','"  
				       + NuevoGasto.getFEEESI() + "','"  
				       + NuevoGasto.getFEECOI() + "','"  
				       + NuevoGasto.getFEEAUI() + "','"  
				       + NuevoGasto.getFEEPAI() + "','"  
				       + NuevoGasto.getIMNGAS() + "','"  
				       + NuevoGasto.getYCOS02() + "','"  
				       + NuevoGasto.getIMRGAS() + "','"  
				       + NuevoGasto.getYCOS04() + "','"  
				       + NuevoGasto.getIMDGAS() + "','"  
				       + NuevoGasto.getYCOS06() + "','"  
				       + NuevoGasto.getIMCOST() + "','"  
				       + NuevoGasto.getYCOS08() + "','"  
				       + NuevoGasto.getIMOGAS() + "','"  
				       + NuevoGasto.getYCOS10() + "','"  
				       + NuevoGasto.getIMDTGA() + "','"  
				       + NuevoGasto.getIMIMGA() + "','"  
				       + NuevoGasto.getCOIMPT() + "','"  
				       + NuevoGasto.getCOTNEG() + "','"  
				       + NuevoGasto.getFEAGTO() + "','"  
				       + NuevoGasto.getCOMONA() + "','"  
				       + NuevoGasto.getBIAUTO() + "','"  
				       + NuevoGasto.getFEAUFA() + "','"  
				       + NuevoGasto.getFEPGPR() + "','"
				       + sEstado + "' )");

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
			
			bSalida = false;
		} 
		finally 
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
			Utils.closeResultSet(resulset,sClassName,sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		
		return bSalida;
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
					+ sField5  + " = '"+ NuevoGasto.getPTPAGO() + "', "
					+ sField7  + " = '"+ NuevoGasto.getFFGTVP() + "', "
					+ sField8  + " = '"+ NuevoGasto.getFEPAGA() + "', "
					+ sField9  + " = '"+ NuevoGasto.getFELIPG() + "', "
					+ sField10 + " = '"+ NuevoGasto.getCOSIGA() + "', "
					+ sField11 + " = '"+ NuevoGasto.getFEEESI() + "', "
					+ sField12 + " = '"+ NuevoGasto.getFEECOI() + "', "
					+ sField13 + " = '"+ NuevoGasto.getFEEAUI() + "', "
					+ sField14 + " = '"+ NuevoGasto.getFEEPAI() + "', "
					+ sField15 + " = '"+ NuevoGasto.getIMNGAS() + "', "
					+ sField16 + " = '"+ NuevoGasto.getYCOS02() + "', "
					+ sField17 + " = '"+ NuevoGasto.getIMRGAS() + "', "
					+ sField18 + " = '"+ NuevoGasto.getYCOS04() + "', "
					+ sField19 + " = '"+ NuevoGasto.getIMDGAS() + "', "
					+ sField20 + " = '"+ NuevoGasto.getYCOS06() + "', "
					+ sField21 + " = '"+ NuevoGasto.getIMCOST() + "', "
					+ sField22 + " = '"+ NuevoGasto.getYCOS08() + "', "
					+ sField23 + " = '"+ NuevoGasto.getIMOGAS() + "', "
					+ sField24 + " = '"+ NuevoGasto.getYCOS10() + "', "
					+ sField25 + " = '"+ NuevoGasto.getIMDTGA() + "', "
					+ sField26 + " = '"+ NuevoGasto.getIMIMGA() + "', "
					+ sField27 + " = '"+ NuevoGasto.getCOIMPT() + "', "
					+ sField28 + " = '"+ NuevoGasto.getCOTNEG() + "', "
					+ sField29 + " = '"+ NuevoGasto.getFEAGTO() + "', "
					+ sField30 + " = '"+ NuevoGasto.getCOMONA() + "', "
					+ sField31 + " = '"+ NuevoGasto.getBIAUTO() + "', "
					+ sField32 + " = '"+ NuevoGasto.getFEAUFA() + "', "
					+ sField33 + " = '"+ NuevoGasto.getFEPGPR() + 
					"' "+
					" WHERE " +
					"("	+ 
						sField1  + " = '"+ NuevoGasto.getCOACES() +"' AND " +
						sField2  + " = '"+ NuevoGasto.getCOGRUG() +"' AND " +
						sField3  + " = '"+ NuevoGasto.getCOTPGA() +"' AND " +
						sField4  + " = '"+ NuevoGasto.getCOSBGA() +"' AND " +
					    sField6  + " = '"+ NuevoGasto.getFEDEVE() + "' )");

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
				    sField6  + " = '"+ sFEDEVE + "' )");
			
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

		String sPTPAGO = "";
		String sFFGTVP = "";
		String sFEPAGA = "";
		String sFELIPG = "";
		String sCOSIGA = "";
		String sFEEESI = "";
		String sFEECOI = "";
		String sFEEAUI = "";
		String sFEEPAI = "";
		String sIMNGAS = "";
		String sYCOS02 = "";
		String sIMRGAS = "";
		String sYCOS04 = "";
		String sIMDGAS = "";
		String sYCOS06 = "";
		String sIMCOST = "";
		String sYCOS08 = "";
		String sIMOGAS = "";
		String sYCOS10 = "";
		String sIMDTGA = "";
		String sIMIMGA = "";
		String sCOIMPT = "";
		String sCOTNEG = "";
		String sFEAGTO = "";
		String sCOMONA = "";
		String sBIAUTO = "";
		String sFEAUFA = "";
		String sFEPGPR = "";


		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					   + sField5  + ","              
				       + sField7  + ","              
				       + sField8  + ","              
				       + sField9  + ","              
				       + sField10 + ","              
				       + sField11 + ","              
				       + sField12 + ","              
				       + sField13 + ","              
				       + sField14 + ","              
				       + sField15 + ","              
				       + sField16 + ","              
				       + sField17 + ","              
				       + sField18 + ","              
				       + sField19 + ","              
				       + sField20 + ","              
				       + sField21 + ","              
				       + sField22 + ","              
				       + sField23 + ","              
				       + sField24 + ","              
				       + sField25 + ","              
				       + sField26 + ","              
				       + sField27 + ","              
				       + sField28 + ","              
				       + sField29 + ","              
				       + sField30 + ","              
				       + sField31 + ","              
				       + sField32 + ","              
				       + sField33 +      
			"  FROM " + sTable + 
			" WHERE "+
			"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
			sField2  + " = '"+ sCodCOGRUG +"' AND " +
			sField3  + " = '"+ sCodCOTPGA +"' AND " +
			sField4  + " = '"+ sCodCOSBGA +"' AND " +
		    sField6  + " = '"+ sFEDEVE + "' )");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			


			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sPTPAGO = rs.getString(sField5);
					sFFGTVP = rs.getString(sField7);
					sFEPAGA = rs.getString(sField8);
					sFELIPG = rs.getString(sField9);
					sCOSIGA = rs.getString(sField10);
					sFEEESI = rs.getString(sField11);
					sFEECOI = rs.getString(sField12);
					sFEEAUI = rs.getString(sField13);
					sFEEPAI = rs.getString(sField14);
					sIMNGAS = rs.getString(sField15);
					sYCOS02 = rs.getString(sField16);
					sIMRGAS = rs.getString(sField17);
					sYCOS04 = rs.getString(sField18);
					sIMDGAS = rs.getString(sField19);
					sYCOS06 = rs.getString(sField20);
					sIMCOST = rs.getString(sField21);
					sYCOS08 = rs.getString(sField22);
					sIMOGAS = rs.getString(sField23);
					sYCOS10 = rs.getString(sField24);
					sIMDTGA = rs.getString(sField25);
					sIMIMGA = rs.getString(sField26);
					sCOIMPT = rs.getString(sField27);
					sCOTNEG = rs.getString(sField28);
					sFEAGTO = rs.getString(sField29);
					sCOMONA = rs.getString(sField30);
					sBIAUTO = rs.getString(sField31);
					sFEAUFA = rs.getString(sField32);
					sFEPGPR = rs.getString(sField33);
					
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
		return new Gasto(sCodCOACES, sCodCOGRUG, sCodCOTPGA, sCodCOSBGA,
				sPTPAGO, sFEDEVE, sFFGTVP, sFEPAGA, sFELIPG, sCOSIGA, sFEEESI,
				sFEECOI, sFEEAUI, sFEEPAI, sIMNGAS, sYCOS02, sIMRGAS, sYCOS04,
				sIMDGAS, sYCOS06, sIMCOST, sYCOS08, sIMOGAS, sYCOS10, sIMDTGA,
				sIMIMGA, sCOIMPT, sCOTNEG, sFEAGTO, sCOMONA, sBIAUTO, sFEAUFA,
				sFEPGPR);
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
				       + sField1  +       
			"  FROM " + sTable + 
			" WHERE "+
			"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
			sField2  + " = '"+ sCodCOGRUG +"' AND " +
			sField3  + " = '"+ sCodCOTPGA +"' AND " +
			sField4  + " = '"+ sCodCOSBGA +"' AND " +
		    sField6  + " = '"+ sFEDEVE + "' )");

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
	
	public static boolean gastoAnulado(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "gastoAnulado";

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
					+ sField7 + 
					"  FROM " + sTable + 
						" WHERE " +
						"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
						sField2  + " = '"+ sCodCOGRUG +"' AND " +
						sField3  + " = '"+ sCodCOTPGA +"' AND " +
						sField4  + " = '"+ sCodCOSBGA +"' AND " +
						sField6  + " = '"+ sFEDEVE +"' AND " +
					    sField34  + " = '"+ ValoresDefecto.DEF_GASTO_ANULADO + "' )");


			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					//com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField7 + ": " + sEstado);

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
					+ sField34 + " = '"+ sEstado + 
					"' "+
					" WHERE "+
					"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
					sField2  + " = '"+ sCodCOGRUG +"' AND " +
					sField3  + " = '"+ sCodCOTPGA +"' AND " +
					sField4  + " = '"+ sCodCOSBGA +"' AND " +
				    sField6  + " = '"+ sFEDEVE + "' )");
			
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
					+ sField34 + 
					"  FROM " + sTable + 
						" WHERE " +
						"("	+ sField1  + " = '"+ sCodCOACES +"' AND " +
						sField2  + " = '"+ sCodCOGRUG +"' AND " +
						sField3  + " = '"+ sCodCOTPGA +"' AND " +
						sField4  + " = '"+ sCodCOSBGA +"' AND " +
					    sField6  + " = '"+ sFEDEVE + "' )");


			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sEstado = rs.getString(sField34);
					
					
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
