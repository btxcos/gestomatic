package com.provisiones.ll;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMCuentas;
import com.provisiones.dal.qm.listas.QMListaCuentasActivos;
import com.provisiones.dal.qm.listas.QMListaCuentasComunidades;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Cuenta;


public class CLCuentas 
{
	private static Logger logger = LoggerFactory.getLogger(CLCuentas.class.getName());

	private CLCuentas(){}
	
	//ID
	public static long buscarCodigoCuenta (String sNUCCEN, String sNUCCOF, String sNUCCDI, String sNUCCNT)
	{
		return QMCuentas.getCuentaID(ConnectionManager.getDBConnection(), sNUCCEN, sNUCCOF, sNUCCDI, sNUCCNT);
	}
	
	public static Cuenta buscarCuenta (long liCodCuenta)
	{
		return QMCuentas.getCuenta(ConnectionManager.getDBConnection(), liCodCuenta);
	}
	
	public static boolean existeCuenta (String sNUCCEN, String sNUCCOF, String sNUCCDI, String sNUCCNT)
	{
		return QMCuentas.existeCuenta(ConnectionManager.getDBConnection(), sNUCCEN, sNUCCOF, sNUCCDI, sNUCCNT);
	}
	
	public static ArrayList<Cuenta> buscarCuentasActivo (String sCOACES)
	{
		return QMListaCuentasActivos.buscaListaCuentas(ConnectionManager.getDBConnection(),sCOACES);
	}
	
	public static ArrayList<Cuenta> buscarCuentasComunidad (long liCodComunidad)
	{
		return QMListaCuentasComunidades.buscaCuentasConvnecionales(ConnectionManager.getDBConnection(),liCodComunidad);
	}

	public static int registraMovimientoActivo (boolean bAlta, String sCOACES, Cuenta cuenta)
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
				
				long liCodCuenta = 0;
				
				
				if (bAlta)
				{
					
					liCodCuenta = QMCuentas.addCuenta(ConnectionManager.getDBConnection(), cuenta, ValoresDefecto.CUENTA_CONVENCIONAL); 
					
					
					
					if ( liCodCuenta != 0)
					{
						if (QMListaCuentasActivos.addRelacionActivo(conexion, liCodCuenta, sCOACES))
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
					liCodCuenta = CLCuentas.buscarCodigoCuenta(cuenta.getsNUCCEN(), cuenta.getsNUCCOF(), cuenta.getsNUCCDI(), cuenta.getsNUCCNT());

					if (QMCuentas.delCuenta(conexion, liCodCuenta))
					{
						if (QMListaCuentasActivos.delRelacionActivo(conexion, liCodCuenta, sCOACES))
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

	public static int registraMovimientoComunidad (boolean bAlta, String sCOCLDO, String sNUDCOM, Cuenta cuenta)
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
				
				long liCodCuenta = 0;
				
				long liCodComunidad = CLComunidades.buscarCodigoComunidad(sCOCLDO, sNUDCOM);
				
				if (bAlta)
				{
					
					liCodCuenta = QMCuentas.addCuenta(ConnectionManager.getDBConnection(), cuenta, ValoresDefecto.CUENTA_CONVENCIONAL); 
					
					
					
					if ( liCodCuenta != 0)
					{
						if (QMListaCuentasComunidades.addRelacionComunidad(conexion, liCodCuenta, liCodComunidad))
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
					liCodCuenta = CLCuentas.buscarCodigoCuenta(cuenta.getsNUCCEN(), cuenta.getsNUCCOF(), cuenta.getsNUCCDI(), cuenta.getsNUCCNT());

					if (QMCuentas.delCuenta(conexion, liCodCuenta))
					{
						if (QMListaCuentasComunidades.delRelacionComunidad(conexion, liCodCuenta, liCodComunidad))
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
