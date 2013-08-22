package com.provisiones.dal.qm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;

public class QMCodigosControl 
{
	static String sClassName = QMCodigosControl.class.getName();
	
	static boolean bTrazas = true;



	
	public static String getDesCOCLDO(String sCodCOCLDO)
	{
		String sMethod = "getDesCOCLDO";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sTable = "cocldo_tbl";

		String sField1  = "cocldo_id";    
		String sField2  = "descripcion";
		
		String sDescripcion = "";

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField2 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodCOCLDO + "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sDescripcion = rs.getString(sField2);

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField2 + ": " + sDescripcion);


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
		return sDescripcion;
	}
	
	public static String getDesCOSBGA(String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA)
	{
		String sMethod = "getDesCOSBGA";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sTable = "";
		String sField1  = "cosbga_id";    
		String sField2  = "descripcion";
		
		String sDescripcion = "";
		
		switch (Integer.parseInt(sCodCOGRUG)) 
		{
			case 1:
				switch (Integer.parseInt(sCodCOTPGA)) 
				{
					case 1:case 2:
						sTable = "cosbga_t11_tbl";						
						break;
				}
				break;
			case 2:
				switch (Integer.parseInt(sCodCOTPGA)) 
				{
					case 1:
						sTable = "cosbga_t21_tbl";
						break;
					case 2:
						sTable = "cosbga_t22_tbl";
						break;
					case 3:
						sTable = "cosbga_t23_tbl";
						break;
				}
				break;
			case 3:
				switch (Integer.parseInt(sCodCOTPGA)) 
				{
					case 2:
						sTable = "cosbga_32_tbl";
						break;
					case 3:
						sTable = "";//
						sDescripcion = "Obtencion de Licencias";
						break;
				}
				break;
		}
		

		if (!sTable.equals(""))
		{
			Connection conn = null;

			conn = ConnectionManager.OpenDBConnection();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

			try 
			{
				stmt = conn.createStatement();


				pstmt = conn.prepareStatement("SELECT " + sField2 + "  FROM " + sTable + 
						" WHERE " +
						"(" + sField1 + " = '" + sCodCOSBGA + "')");

				rs = pstmt.executeQuery();
				
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
				
				
				if (rs != null) 
				{
					
					while (rs.next()) 
					{
						found = true;

						sDescripcion = rs.getString(sField2);

						com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

						com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField2 + ": " + sDescripcion);


					}
				}
				if (found == false) 
				{
	 
					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
				}

			} 
			catch (SQLException ex) 
			{
				System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRUG: " + sCodCOGRUG);
				System.out.println("["+sClassName+"."+sMethod+"] ERROR: COTPGA: " + sCodCOTPGA);
				System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBGA: " + sCodCOSBGA);

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
		}
		return sDescripcion;
	}
	
	public static String getDesCOSBGA_E2(String sCodCOSBGA)
	{
		String sMethod = "getDesCOSBGA_E2";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sTable = "cosbga_t22_tbl";

		String sField1  = "cosbga_id";    
		String sField2  = "descripcion";
		
		String sDescripcion = "";

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField2 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodCOSBGA + "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sDescripcion = rs.getString(sField2);

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField2 + ": " + sDescripcion);


				}
			}
			if (found == false) 
			{
 
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBGA: " + sCodCOSBGA);

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
		return sDescripcion;
	}
	
	public static String getDesCOSBGA_E4(String sCodCOSBGA)
	{
		String sMethod = "getDesCOSBGA_E4";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sTable = "cosbga_t21_tbl";

		String sField1  = "cosbga_id";    
		String sField2  = "descripcion";
		
		String sDescripcion = "";

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField2 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodCOSBGA + "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sDescripcion = rs.getString(sField2);

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField2 + ": " + sDescripcion);


				}
			}
			if (found == false) 
			{
 
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSBGA: " + sCodCOSBGA);

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
		return sDescripcion;
	}
	
	public static String getDesCOSIGA(String sCodCOSIGA)
	{
		String sMethod = "getDesCOSIGA";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sTable = "cosiga_tbl";

		String sField1  = "cosiga_id";    
		String sField2  = "descripcion";
		
		String sDescripcion = "";

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField2 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodCOSIGA + "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sDescripcion = rs.getString(sField2);

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField2 + ": " + sDescripcion);


				}
			}
			if (found == false) 
			{
 
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSIGA: " + sCodCOSIGA);

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
		return sDescripcion;
	}

	public static String getDesPTPAGO(String sCodPTPAGO)
	{
		String sMethod = "getDesPTPAGO";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sTable = "ptpago_tbl";

		String sField1  = "ptpago_id";    
		String sField2  = "descripcion";
		
		String sDescripcion = "";

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField2 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodPTPAGO + "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sDescripcion = rs.getString(sField2);

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField2 + ": " + sDescripcion);


				}
			}
			if (found == false) 
			{
 
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: PTPAGO: " + sCodPTPAGO);

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
		return sDescripcion;
	}
	
	public static String getDesSociedadesTitulizadas(String sCodCOSPAT)
	{
		String sMethod = "getDesSociedadesTitulizadas";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sTable = "sociedades_titulizadas_tbl";

		String sField1  = "cospat_id";    
		String sField2  = "descripcion";
		
		String sDescripcion = "";

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField2 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodCOSPAT + "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sDescripcion = rs.getString(sField2);

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField2 + ": " + sDescripcion);


				}
			}
			if (found == false) 
			{
 
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSPAT: " + sCodCOSPAT);

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
		return sDescripcion;
	}
	
	public static String getDesTipoActivo(String sCodTAS)
	{
		String sMethod = "getDesTipoActivo";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sTable = "tipo_activo_sareb_tbl";

		String sField1  = "tipo_id";    
		String sField2  = "descripcion";
		
		String sDescripcion = "";

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField2 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodTAS + "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sDescripcion = rs.getString(sField2);

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField2 + ": " + sDescripcion);


				}
			}
			if (found == false) 
			{
 
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: TAS: " + sCodTAS);

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
		return sDescripcion;
	}
	
	public static String getDesBIRESO(String sCodBIRESO)
	{
		String sMethod = "getDesBIRESO";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sTable = "bireso_tbl";

		String sField1  = "bireso_id";    
		String sField2  = "descripcion";
		
		String sDescripcion = "";

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField2 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodBIRESO + "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sDescripcion = rs.getString(sField2);

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField2 + ": " + sDescripcion);


				}
			}
			if (found == false) 
			{
 
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: PTPAGO: " + sCodBIRESO);

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
		return sDescripcion;
	}
	
	public static String getDesBINARIA(String sCodBINARIA)
	{
		String sMethod = "getDesBINARIA";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sTable = "ptpago_tbl";

		String sField1  = "ptpago_id";    
		String sField2  = "descripcion";
		
		String sDescripcion = "";

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField2 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodBINARIA + "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sDescripcion = rs.getString(sField2);

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField2 + ": " + sDescripcion);


				}
			}
			if (found == false) 
			{
 
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: PTPAGO: " + sCodBINARIA);

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
		return sDescripcion;
	}
	
	public static String getDesCampo(String sTable, String sField1, String sCodBINARIA)
	{
		String sMethod = "getDesBINARIA";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		//String sTable = "ptpago_tbl";

		//String sField1  = "ptpago_id";    
		String sField2  = "descripcion";
		
		String sDescripcion = "";

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField2 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodBINARIA + "')");

			rs = pstmt.executeQuery();
			
			com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sDescripcion = rs.getString(sField2);

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "Encontrado el registro!");

					com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, sField2 + ": " + sDescripcion);


				}
			}
			if (found == false) 
			{
 
				com.provisiones.misc.Utils.debugTrace(bTrazas, sClassName, sMethod, "No se encontro la informacion.");
			}

		} 
		catch (SQLException ex) 
		{
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: PTPAGO: " + sCodBINARIA);

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
		return sDescripcion;
	}
}
