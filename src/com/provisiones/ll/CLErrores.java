package com.provisiones.ll;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMComunidades;
import com.provisiones.dal.qm.QMCuotas;
import com.provisiones.dal.qm.QMGastos;
import com.provisiones.dal.qm.QMImpuestos;
import com.provisiones.dal.qm.QMReferencias;
import com.provisiones.dal.qm.listas.QMListaComunidades;
import com.provisiones.dal.qm.listas.QMListaComunidadesActivos;
import com.provisiones.dal.qm.listas.QMListaCuotas;
import com.provisiones.dal.qm.listas.QMListaGastos;
import com.provisiones.dal.qm.listas.QMListaGastosProvisiones;
import com.provisiones.dal.qm.listas.QMListaImpuestos;
import com.provisiones.dal.qm.listas.QMListaReferencias;
import com.provisiones.dal.qm.listas.errores.QMListaErroresComunidades;
import com.provisiones.dal.qm.listas.errores.QMListaErroresCuotas;
import com.provisiones.dal.qm.listas.errores.QMListaErroresGastos;
import com.provisiones.dal.qm.listas.errores.QMListaErroresImpuestos;
import com.provisiones.dal.qm.listas.errores.QMListaErroresReferencias;
import com.provisiones.dal.qm.movimientos.QMMovimientosComunidades;
import com.provisiones.dal.qm.movimientos.QMMovimientosCuotas;
import com.provisiones.dal.qm.movimientos.QMMovimientosGastos;
import com.provisiones.dal.qm.movimientos.QMMovimientosImpuestos;
import com.provisiones.dal.qm.movimientos.QMMovimientosReferencias;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.errores.ErrorComunidadTabla;
import com.provisiones.types.errores.ErrorCuotaTabla;
import com.provisiones.types.errores.ErrorGastoTabla;
import com.provisiones.types.errores.ErrorImpuestoTabla;
import com.provisiones.types.errores.ErrorReferenciaTabla;
import com.provisiones.types.errores.ErrorTabla;
import com.provisiones.types.movimientos.MovimientoComunidad;
import com.provisiones.types.movimientos.MovimientoCuota;
import com.provisiones.types.movimientos.MovimientoGasto;
import com.provisiones.types.movimientos.MovimientoImpuestoRecurso;
import com.provisiones.types.movimientos.MovimientoReferenciaCatastral;

public final class CLErrores 
{
	private static Logger logger = LoggerFactory.getLogger(CLErrores.class.getName());

	private CLErrores(){}
	
	public static ArrayList<ErrorComunidadTabla> buscarComunidadesConErrores(ErrorComunidadTabla filtro)
	{
		return QMListaErroresComunidades.buscaComunidadesConError(ConnectionManager.getDBConnection(),filtro);
	}
	
	public static ArrayList<ErrorComunidadTabla> buscarComunidadesActivoConErrores(int iCOACES)
	{
		return QMListaErroresComunidades.buscaComunidadesActivoConError(ConnectionManager.getDBConnection(),iCOACES);
	}
	
	public static ArrayList<ErrorTabla> buscarErroresComunidad(long liCodMovimiento)
	{
		return QMListaErroresComunidades.buscaErrores(ConnectionManager.getDBConnection(),liCodMovimiento);
	}
	
	public static int reparaMovimientoComunidad(MovimientoComunidad movimiento, long liCodMovimiento, String sCodError)
	{
		int iCodigo = -910;//Error de conexion

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			long liCodComunidad = CLComunidades.buscarCodigoComunidad(movimiento.getCOCLDO(), movimiento.getNUDCOM());
			
			MovimientoComunidad movimiento_revisado = CLComunidades.revisaCodigosControl(movimiento,liCodComunidad);
			
			if (movimiento_revisado.getCOACCI().equals("#"))
			{	
				//Error modificacion sin cambios
				iCodigo = -804;	
			}
			else
			{
				//MovimientoComunidad movimiento_antiguo = QMMovimientosComunidades.getMovimientoComunidad(conexion,liCodMovimiento);
				
				try 
				{
					conexion.setAutoCommit(false);

					if (!QMMovimientosComunidades.modMovimientoComunidad(conexion,movimiento_revisado,liCodMovimiento))
					{
						//Error al crear un movimiento
						iCodigo = -900;
					}
					else
					{
						if(QMListaErroresComunidades.delErrorComunidad(conexion,liCodMovimiento, sCodError))
						{	
							
							if (QMComunidades.modComunidad(conexion,CLComunidades.convierteMovimientoenComunidad(movimiento), liCodComunidad))	
							{
								ArrayList<Long> dependenciascomunidades = QMListaComunidades.buscarDependencias(conexion,liCodComunidad, liCodMovimiento);
								ArrayList<Long> dependenciasactivoscomunidades = QMListaComunidadesActivos.buscarDependencias(conexion,liCodComunidad, liCodMovimiento);

								boolean bError = false;
								
								for (int i = 0; i < dependenciascomunidades.size() ; i++)
					            {
									bError = !QMListaComunidades.setValidado(conexion,dependenciascomunidades.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
									if (bError)
									{
										i = dependenciascomunidades.size();
										iCodigo = -900;
									}
					            }
					            
								if (!bError)
								{
						            for (int i = 0; i < dependenciasactivoscomunidades.size() ; i++)
						            {
						            	bError = !QMListaComunidadesActivos.setValidado(conexion,dependenciasactivoscomunidades.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
										if (bError)
										{
											i = dependenciascomunidades.size();
											iCodigo = -900;
										}
						            }
									if (!bError)
									{
										//OK 
										iCodigo = 0;
									}
								}
							}
							else
							{
								//error y rollback - error al modificar la comunidad
								iCodigo = -905;
							}

						}
						else
						{
							//error y rollback - error al eliminar el error
							iCodigo = -905;
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
		}

		logger.debug("iCodigo:|"+iCodigo+"|");
		return iCodigo;
	}

	public static ArrayList<ErrorCuotaTabla> buscarCuotasConErrores(ErrorCuotaTabla filtro)
	{
		return QMListaErroresCuotas.buscaCuotasConError(ConnectionManager.getDBConnection(),filtro);
	}

	public static ArrayList<ErrorTabla> buscarErroresCuota(long liCodMovimiento)
	{
		return QMListaErroresCuotas.buscaErrores(ConnectionManager.getDBConnection(),liCodMovimiento);
	}
	
	public static int reparaMovimientoCuota(MovimientoCuota movimiento, long liCodMovimiento, String sCodError)
	{
		int iCodigo = -910;//Error de conexion
		
		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			long liCodCuota = CLCuotas.buscarCodigoCuota(Integer.parseInt(movimiento.getCOACES()), movimiento.getCOCLDO(), movimiento.getNUDCOM(), movimiento.getCOSBAC());
			
			MovimientoCuota movimiento_revisado = CLCuotas.revisaCodigosControl(movimiento,liCodCuota);
			
			if (movimiento_revisado.getCOACCI().equals("#"))
			{	
				//Error modificacion sin cambios
				iCodigo = -804;	
			}
			else
			{
				//MovimientoCuota movimiento_antiguo = QMMovimientosCuotas.getMovimientoCuota(conexion,liCodMovimiento);
				
				try 
				{
					conexion.setAutoCommit(false);

					if (!QMMovimientosCuotas.modMovimientoCuota(conexion,movimiento_revisado,liCodMovimiento))
					{
						//Error al crear un movimiento
						iCodigo = -900;
					}
					else
					{
						if(QMListaErroresCuotas.delErrorCuota(conexion,liCodMovimiento, sCodError))
						{	

							if (QMCuotas.modCuota(conexion,CLCuotas.convierteMovimientoenCuota(movimiento), liCodCuota))	
							{
								
								ArrayList<Long> dependenciascuotas = QMListaCuotas.buscarDependencias(conexion,liCodCuota, liCodMovimiento);

								boolean bError = false;
								
								for (int i = 0; i < dependenciascuotas.size() ; i++)
					            {
									bError = !QMListaCuotas.setValidado(conexion,dependenciascuotas.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
									if (bError)
									{
										i = dependenciascuotas.size();
										iCodigo = -900;
									}
					            }
								
								if (!bError)
								{
									//OK 
									iCodigo = 0;
								}
							}
							else
							{
								//error y rollback - error al modificar la cuota
								iCodigo = -904;
							}

						}
						else
						{
							//error y rollback - error al eliminar el error
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
		}

		logger.debug("iCodigo:|"+iCodigo+"|");
		return iCodigo;
	}
	
	public static ArrayList<ErrorReferenciaTabla> buscarReferenciasConErrores(ErrorReferenciaTabla filtro)
	{
		return QMListaErroresReferencias.buscaReferenciasConError(ConnectionManager.getDBConnection(),filtro);
	}

	public static ArrayList<ErrorTabla> buscarErroresReferencia(long liCodMovimiento)
	{
		return QMListaErroresReferencias.buscaErrores(ConnectionManager.getDBConnection(),liCodMovimiento);
	}
	
	public static int reparaMovimientoReferencia(MovimientoReferenciaCatastral movimiento, long liCodMovimiento, String sCodError)
	{
		int iCodigo = -910;//Error de conexion
		
		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			long liCodReferencia = CLReferencias.buscarCodigoReferencia(movimiento.getNURCAT());
			
			MovimientoReferenciaCatastral movimiento_revisado = CLReferencias.revisaCodigosControl(movimiento,liCodReferencia);
			
			if (movimiento_revisado.getCOACCI().equals("#"))
			{	
				//Error modificacion sin cambios
				iCodigo = -804;	
			}
			else
			{
				//MovimientoReferenciaCatastral movimiento_antiguo = QMMovimientosReferencias.getMovimientoReferenciaCatastral(conexion,liCodMovimiento);
	
				try 
				{
					conexion.setAutoCommit(false);

					if (!QMMovimientosReferencias.modMovimientoReferenciaCatastral(conexion,movimiento_revisado,liCodMovimiento))
					{
						//Error al crear un movimiento
						iCodigo = -900;
					}
					else
					{
						if(QMListaErroresReferencias.delErrorReferencia(conexion,liCodMovimiento, sCodError))
						{	

							if (QMReferencias.modReferenciaCatastral(conexion,CLReferencias.convierteMovimientoenReferencia(movimiento), liCodReferencia))	
							{
								ArrayList<Long> dependenciascuotas = QMListaReferencias.buscarDependencias(conexion,Integer.parseInt(movimiento.getCOACES()), liCodReferencia, liCodMovimiento);

								boolean bError = false;

								for (int i = 0; i < dependenciascuotas.size() ; i++)
					            {
					            	QMListaReferencias.setValidado(conexion,dependenciascuotas.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
									if (bError)
									{
										i = dependenciascuotas.size();
										iCodigo = -900;
									}
					            }
								
								if (!bError)
								{
									//OK 
									iCodigo = 0;
								}
					            
							}
							else
							{
								//error y rollback - error al modificar la referencia
								iCodigo = -904;
							}

						}
						else
						{
							//error y rollback - error al eliminar el error
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
		}

		logger.debug("iCodigo:|"+iCodigo+"|");
		return iCodigo;
	}
	
	public static ArrayList<ErrorImpuestoTabla> buscarImpuestosConErrores(ErrorImpuestoTabla filtro)
	{
		return QMListaErroresImpuestos.buscaImpuestosConError(ConnectionManager.getDBConnection(),filtro);
	}

	public static ArrayList<ErrorTabla> buscarErroresImpuesto(long liCodMovimiento)
	{
		return QMListaErroresImpuestos.buscaErrores(ConnectionManager.getDBConnection(),liCodMovimiento);
	}
	
	public static int reparaMovimientoImpuesto(MovimientoImpuestoRecurso movimiento, long liCodMovimiento, String sCodError)
	{
		int iCodigo = -910;//Error de conexion
		
		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			long liCodImpuesto = CLImpuestos.buscarCodigoImpuesto(movimiento.getNURCAT(), movimiento.getCOSBAC());
			
			MovimientoImpuestoRecurso movimiento_revisado = CLImpuestos.revisaCodigosControl(movimiento,liCodImpuesto);
			
			if (movimiento_revisado.getCOACCI().equals("#"))
			{	
				//Error modificacion sin cambios
				iCodigo = -804;	
			}
			else
			{
				//MovimientoImpuestoRecurso movimiento_antiguo = QMMovimientosImpuestos.getMovimientoImpuestoRecurso(conexion,liCodMovimiento);
				
				try 
				{
					conexion.setAutoCommit(false);

					if (!QMMovimientosImpuestos.modMovimientoImpuestoRecurso(conexion,movimiento_revisado,liCodMovimiento))
					{
						//Error al crear un movimiento
						iCodigo = -900;
					}
					else
					{
						if(QMListaErroresImpuestos.delErrorImpuesto(conexion,liCodMovimiento, sCodError))
						{	

							if (QMImpuestos.modImpuestoRecurso(conexion,CLImpuestos.convierteMovimientoenImpuesto(movimiento), liCodImpuesto))	
							{
								ArrayList<Long> dependenciasimpuestos = QMListaImpuestos.buscarDependencias(conexion,Integer.parseInt(movimiento.getCOACES()), liCodImpuesto, liCodMovimiento);

								boolean bError = false;

								for (int i = 0; i < dependenciasimpuestos.size() ; i++)
					            {
					            	QMListaImpuestos.setValidado(conexion,dependenciasimpuestos.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
									if (bError)
									{
										i = dependenciasimpuestos.size();
										iCodigo = -900;
									}
					            }
								
								if (!bError)
								{
									//OK 
									iCodigo = 0;
								}
					            
							}
							else
							{
								//error y rollback - error al modificar la impuesto
								iCodigo = -904;
							}

						}
						else
						{
							//error y rollback - error al eliminar el error
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
		}

		logger.debug("iCodigo:|"+iCodigo+"|");
		return iCodigo;
	}
	public static ArrayList<ErrorGastoTabla> buscarGastosConErrores(ErrorGastoTabla filtro)
	{
		return QMListaErroresGastos.buscaGastosConError(ConnectionManager.getDBConnection(),filtro);
	}

	public static ArrayList<ErrorTabla> buscarErroresGasto(long liCodMovimiento)
	{
		return QMListaErroresGastos.buscaErrores(ConnectionManager.getDBConnection(),liCodMovimiento);
	}
	
	public static int ignoraErrorGasto(long liCodMovimiento)
	{
		int iCodigo = -910;//Error de conexion
		
		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			if (QMMovimientosGastos.existeMovimientoGasto(conexion, liCodMovimiento))
			{
				try 
				{
					conexion.setAutoCommit(false);
					
					if (QMListaGastos.setValidado(conexion, liCodMovimiento, ValoresDefecto.DEF_MOVIMIENTO_VALIDADO))
					{
						long liCodGasto = QMListaGastos.getCodGasto(conexion, liCodMovimiento);
						
						if (QMListaGastosProvisiones.setRevisado(conexion, liCodGasto, ValoresDefecto.DEF_MOVIMIENTO_VALIDADO))
						{
							iCodigo = 0;
						}
						else
						{
							//error (FATAL) - No se ha podido establecer el estado en la relación Gasto-Provisión
							iCodigo = -903;
						}
						
					}
					else
					{
						//error (FATAL) - No se ha podido establecer el estado en la relación Gasto-Movimiento
						iCodigo = -902;
					}
					
					if (iCodigo < 0)
					{
						conexion.rollback();
					}
					else
					{
						conexion.commit();
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
			else
			{
				//error (FATAL) - no existe el movimiento
				iCodigo = -901;	
			}
		}
		
		return iCodigo;
	}
	
	public static int reenviarErrorGasto(long liCodMovimiento)
	{
		int iCodigo = -910;//Error de conexion
		
		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			if (QMMovimientosGastos.existeMovimientoGasto(conexion, liCodMovimiento))
			{
				try 
				{
					conexion.setAutoCommit(false);
					
					if (QMListaGastos.setValidado(conexion, liCodMovimiento, ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE))
					{
						long liCodGasto = QMListaGastos.getCodGasto(conexion, liCodMovimiento);
						
						if (QMListaGastosProvisiones.setRevisado(conexion, liCodGasto, ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE))
						{
							if (QMListaErroresGastos.delErrorGasto(conexion, liCodMovimiento, ValoresDefecto.CAMPO_ALFA_SIN_INFORMAR))
							{
								iCodigo = 0;
							}
							else
							{
								//error (FATAL) - No se han podido eliminar los errores del movimiento
								iCodigo = -905;
							}
							
						}
						else
						{
							//error (FATAL) - No se ha podido establecer el estado en la relación Gasto-Provisión
							iCodigo = -903;
						}
					}
					else
					{
						//error (FATAL) - No se ha podido establecer el estado en la relación Gasto-Movimiento
						iCodigo = -902;
					}
					
					if (iCodigo < 0)
					{
						conexion.rollback();
					}
					else
					{
						conexion.commit();
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
			else
			{
				//error (FATAL) - no existe el movimiento
				iCodigo = -901;	
			}
		}
		
		return iCodigo;
	}
	
	public static int reparaMovimientoGasto(MovimientoGasto movimiento, long liCodMovimiento, String sCodError)
	{
		
		int iCodigo = -910;//Error de conexion
		
		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			String sEstado = QMGastos.getEstado(conexion,CLGastos.buscarCodigoGasto(Integer.parseInt(movimiento.getCOACES()), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE()));

			logger.debug("sEstado:|"+sEstado+"|");
			
			String sAccion = "M";
			
			MovimientoGasto movimiento_revisado = CLGastos.revisaSignos(movimiento,sAccion);
			
			if (movimiento_revisado.getCOSIGA().equals("#"))
			{
				//error modificacion sin cambios
				iCodigo = -806;	

			}
			else
			{
				//MovimientoGasto movimiento_antiguo = QMMovimientosGastos.getMovimientoGasto(conexion,liCodMovimiento);
				
				try 
				{
					conexion.setAutoCommit(false);

					logger.debug(movimiento_revisado.logMovimientoGasto());
					
					if (!QMMovimientosGastos.modMovimientoGasto(conexion,movimiento_revisado,liCodMovimiento))
					{
						//Error al crear un movimiento
						iCodigo = -900;
					}
					else
					{
						if(QMListaErroresGastos.delErrorGasto(conexion,liCodMovimiento, sCodError))
						{	

							if (QMGastos.modGasto(conexion,CLGastos.convierteMovimientoenGasto(movimiento_revisado)))
							{
								long liCodGasto = QMGastos.getGastoID(conexion,Integer.parseInt(movimiento.getCOACES()), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE());
								
								ArrayList<Long> dependenciasgastos = QMListaGastos.buscarDependencias(conexion,liCodGasto, liCodMovimiento);

								boolean bError = false;

								for (int i = 0; i < dependenciasgastos.size() ; i++)
					            {
					            	QMListaGastos.setValidado(conexion,dependenciasgastos.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
									if (bError)
									{
										i = dependenciasgastos.size();
										iCodigo = -900;
									}
					            }
								
								if (!bError)
								{
									//OK 
									iCodigo = 0;
								}
					            
							}
							else
							{
								//error y rollback - error al modificar la gasto
								iCodigo = -904;
							}

						}
						else
						{
							//error y rollback - error al eliminar el error
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
		}

		logger.debug("iCodigo:|"+iCodigo+"|");
		return iCodigo;
	}
}

//Autor: Francisco Valverde Manjón