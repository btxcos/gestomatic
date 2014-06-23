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
	
	public static boolean cuentaAsociadaActivo (String sNUCCEN, String sNUCCOF, String sNUCCDI, String sNUCCNT, int iCodCOACES)
	{
		return QMListaCuentasActivos.cuentaAsociadaActivo(ConnectionManager.getDBConnection(), sNUCCEN, sNUCCOF, sNUCCDI, sNUCCNT, iCodCOACES);
	}

	public static boolean cuentaAsociadaComunidad (String sNUCCEN, String sNUCCOF, String sNUCCDI, String sNUCCNT, long liCodComunidad)
	{
		return QMListaCuentasComunidades.cuentaAsociadaComunidad(ConnectionManager.getDBConnection(), sNUCCEN, sNUCCOF, sNUCCDI, sNUCCNT, liCodComunidad);
	}
	
	public static boolean tieneMasRelacciones (long liCodCuenta)
	{
		long liRelacionesActivos = QMListaCuentasActivos.buscaCantidadRelaccionesCuenta(ConnectionManager.getDBConnection(), liCodCuenta);
		long liRelacionesComunidad = QMListaCuentasComunidades.buscaCantidadRelaccionesCuenta(ConnectionManager.getDBConnection(), liCodCuenta);
		return ((liRelacionesActivos+liRelacionesComunidad) > 0);
	}
	
	//TODO Borrar cuentas Comunidad
	
	public static ArrayList<Cuenta> buscarCuentasActivo (int iCodCOACES)
	{
		return QMListaCuentasActivos.buscaListaCuentas(ConnectionManager.getDBConnection(),iCodCOACES);
	}
	
	public static ArrayList<Cuenta> buscarCuentasComunidad (long liCodComunidad)
	{
		return QMListaCuentasComunidades.buscaCuentas(ConnectionManager.getDBConnection(),liCodComunidad,ValoresDefecto.CUENTA_TODAS);
	}
	
	public static ArrayList<Cuenta> buscarCuentasConvencionalesComunidad (long liCodComunidad)
	{
		return QMListaCuentasComunidades.buscaCuentas(ConnectionManager.getDBConnection(),liCodComunidad,ValoresDefecto.CUENTA_CONVENCIONAL);
	}

	public static int registraMovimientoActivo (boolean bAlta, int iCodCOACES, Cuenta cuenta)
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
					
					liCodCuenta = QMCuentas.getCuentaID(conexion, cuenta.getsNUCCEN(), cuenta.getsNUCCOF(), cuenta.getsNUCCDI(), cuenta.getsNUCCNT());
					
					
					if ( liCodCuenta != 0)
					{
						if (QMListaCuentasActivos.addRelacionActivo(conexion, liCodCuenta, iCodCOACES))
						{
							//OK 
							iCodigo = 0;
						}
						else
						{
							//error relación cuenta no creada - Rollback
							iCodigo = -902;
						}
						
					}
					else
					{
						//error cuenta no creada - Rollback
						//iCodigo = -901;
						
						liCodCuenta = QMCuentas.addCuenta(ConnectionManager.getDBConnection(), cuenta); 
						
						if (QMListaCuentasActivos.addRelacionActivo(conexion, liCodCuenta, iCodCOACES))
						{
							//OK 
							iCodigo = 0;
						}
						else
						{
							//error relación cuenta no creada - Rollback
							iCodigo = -902;
						}
						
					}
				}
				else
				{
					liCodCuenta = CLCuentas.buscarCodigoCuenta(cuenta.getsNUCCEN(), cuenta.getsNUCCOF(), cuenta.getsNUCCDI(), cuenta.getsNUCCNT());

					if (QMListaCuentasActivos.delRelacionActivo(conexion, liCodCuenta, iCodCOACES))
					{
						if (!CLCuentas.tieneMasRelacciones(liCodCuenta))
						{
							if (QMCuentas.delCuenta(conexion, liCodCuenta))
							{
								//OK 
								iCodigo = 0;
							}
							else
							{
								//error cuenta no borrada - Rollback
								iCodigo = -903;
							}
						}
						else
						{
							//OK 
							iCodigo = 0;
						}
					}
					else
					{
						//error relación cuenta no borrada - Rollback
						iCodigo = -904;
					}

				}
				
				if (iCodigo == 0)
				{
					conexion.commit();
				}
				else
				{
					conexion.rollback();
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
					
					liCodCuenta = QMCuentas.getCuentaID(conexion, cuenta.getsNUCCEN(), cuenta.getsNUCCOF(), cuenta.getsNUCCDI(), cuenta.getsNUCCNT());
					
					if ( liCodCuenta != 0)
					{
						if (QMListaCuentasComunidades.addRelacionComunidad(conexion, liCodCuenta, liCodComunidad, ValoresDefecto.CUENTA_CONVENCIONAL))
						{
							//OK 
							iCodigo = 0;
						}
						else
						{
							//error relación cuenta no creada - Rollback
							iCodigo = -902;
						}
						
					}
					else
					{
						//error cuenta no creada - Rollback
						//iCodigo = -901;
						
						liCodCuenta = QMCuentas.addCuenta(ConnectionManager.getDBConnection(), cuenta); 
						
						if (QMListaCuentasComunidades.addRelacionComunidad(conexion, liCodCuenta, liCodComunidad, ValoresDefecto.CUENTA_CONVENCIONAL))
						{
							//OK 
							iCodigo = 0;
						}
						else
						{
							//error relación cuenta no creada - Rollback
							iCodigo = -902;
						}

						
					}
				}
				else
				{
					liCodCuenta = CLCuentas.buscarCodigoCuenta(cuenta.getsNUCCEN(), cuenta.getsNUCCOF(), cuenta.getsNUCCDI(), cuenta.getsNUCCNT());

					if (QMListaCuentasComunidades.delRelacionComunidad(conexion, liCodCuenta, liCodComunidad))
					{
						if (!CLCuentas.tieneMasRelacciones(liCodCuenta))
						{
							if (QMCuentas.delCuenta(conexion, liCodCuenta))
							{
								//OK 
								iCodigo = 0;
							}
							else
							{
								//error cuenta no borrada - Rollback
								iCodigo = -903;
							}
						}
						else
						{
							//OK 
							iCodigo = 0;
						}
					}
					else
					{
						//error relación cuenta no borrada - Rollback
						iCodigo = -904;
					}

				}
				
				if (iCodigo == 0)
				{
					conexion.commit();
				}
				else
				{
					conexion.rollback();
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
