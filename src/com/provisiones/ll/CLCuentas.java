package com.provisiones.ll;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMCuentas;
import com.provisiones.dal.qm.listas.QMListaCuentasActivos;
import com.provisiones.types.Cuenta;


public class CLCuentas 
{
	private static Logger logger = LoggerFactory.getLogger(CLCuentas.class.getName());

	private CLCuentas(){}
	
	//ID
	public static String buscarCodigoCuenta (String sNUCCEN, String sNUCCOF, String sNUCCDI, String sNUCCNT)
	{
		return QMCuentas.getCuentaID(ConnectionManager.getDBConnection(), sNUCCEN, sNUCCOF, sNUCCDI, sNUCCNT);
	}
	
	public static ArrayList<Cuenta> buscarCuentasActivo (String sCOACES)
	{
		return QMListaCuentasActivos.buscaListaCuentas(ConnectionManager.getDBConnection(),sCOACES);
	}

	public static int registraMovimientoActivo (boolean bAlta, String sCOACES, String sNUCCEN, String sNUCCOF, String sNUCCDI, String sNUCCNT, String sDescripcion)
	{
		//TODO Implementar SEPA
		int iCodigo = -910;
		
		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;

			try 
			{
				conexion.setAutoCommit(false);
				
				if (bAlta)
				{
					Cuenta NuevaCuenta = new Cuenta("ES","#",sNUCCEN,sNUCCOF,sNUCCDI,sNUCCNT,sDescripcion);
					
					long indice = QMCuentas.addCuenta(ConnectionManager.getDBConnection(), NuevaCuenta); 
					
					if ( indice != 0)
					{
						if (QMListaCuentasActivos.addRelacionActivo(conexion, Long.toString(indice), sCOACES))
						{
							//OK 
							iCodigo = 0;
							conexion.commit();
						}
						else
						{
							//error relación cuenta no creada - Rollback
							iCodigo = -902;
							conexion.rollback();
						}
						
					}
					else
					{
						//error cuenta no creada - Rollback
						iCodigo = -901;
						conexion.rollback();
					}
				}
				else
				{
					String sCodCuenta = QMCuentas.getCuentaID(conexion, sNUCCEN, sNUCCOF, sNUCCDI, sNUCCNT);

					if (QMCuentas.delCuenta(conexion, sCodCuenta))
					{
						if (QMListaCuentasActivos.delRelacionActivo(conexion, sCodCuenta, sCOACES))
						{
							//OK 
							iCodigo = 0;
							conexion.commit();
						}
						else
						{
							//error relación cuenta no borrada - Rollback
							iCodigo = -904;
							conexion.rollback();
						}
						
					}
					else
					{
						//error cuenta no borrada - Rollback
						iCodigo = -903;
						conexion.rollback();
					}
				}
				
				conexion.setAutoCommit(true);	
				
			}
			catch (SQLException e)
			{
				//error de conexion con base de datos.
				iCodigo = -910;

				try 
				{
					//reintentamos
					conexion.rollback();
					conexion.setAutoCommit(true);
					conexion.close();
				} 
				catch (SQLException e1) 
				{
					try 
					{
						conexion.close();
					}
					catch (SQLException e2) 
					{
						logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
					}
				}
			}
			

			
		}
		

		
		
		return iCodigo;
	}

}
