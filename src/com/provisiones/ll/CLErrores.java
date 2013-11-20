package com.provisiones.ll;

import java.sql.Connection;
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
	
	public static ArrayList<ErrorComunidadTabla> buscarComunidadesActivoConErrores(String sCOACES)
	{
		return QMListaErroresComunidades.buscaComunidadesActivoConError(ConnectionManager.getDBConnection(),sCOACES);
	}
	
	public static ArrayList<ErrorTabla> buscarErroresComunidad(String sMovimiento)
	{
		return QMListaErroresComunidades.buscaErrores(ConnectionManager.getDBConnection(),sMovimiento);
	}

	public static int reparaMovimientoComunidad(MovimientoComunidad movimiento, String sCodMovimiento, String sCodError)
	{
		int iCodigo = 0;

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			String sCodComunidad = CLComunidades.buscarCodigoComunidad(movimiento.getCOCLDO(), movimiento.getNUDCOM());
			
			MovimientoComunidad movimiento_revisado = CLComunidades.revisaCodigosControl(movimiento,sCodComunidad);
			
			if (movimiento_revisado.getCOACCI().equals("#"))
			{	
				//Error modificacion sin cambios
				iCodigo = -804;	
			}
			else
			{
				MovimientoComunidad movimiento_antiguo = QMMovimientosComunidades.getMovimientoComunidad(conexion,sCodMovimiento);
				
				if (!QMMovimientosComunidades.modMovimientoComunidad(conexion,movimiento_revisado,sCodMovimiento))
				{
					//Error al crear un movimiento
					iCodigo = -900;
				}
				else
				{
					if(QMListaErroresComunidades.delErrorComunidad(conexion,sCodMovimiento, sCodError))
					{	
						
						if (QMComunidades.modComunidad(conexion,CLComunidades.convierteMovimientoenComunidad(movimiento), sCodComunidad))	
						{
							//OK 
							iCodigo = 0;
							
							ArrayList<String> dependenciascomunidades = QMListaComunidades.buscarDependencias(conexion,sCodComunidad, sCodMovimiento);
							ArrayList<String> dependenciasactivoscomunidades = QMListaComunidadesActivos.buscarDependencias(conexion,sCodComunidad, sCodMovimiento);

							for (int i = 0; i < dependenciascomunidades.size() ; i++)
				            {
				            	QMListaComunidades.setValidado(conexion,dependenciascomunidades.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
				            }
				            
				            for (int i = 0; i < dependenciasactivoscomunidades.size() ; i++)
				            {
				            	QMListaComunidadesActivos.setValidado(conexion,dependenciasactivoscomunidades.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
				            }
				            
						}
						else
						{
							//error y rollback - error al modificar la comunidad
							iCodigo = -905;
							QMListaErroresComunidades.addErrorComunidad(conexion,sCodMovimiento, sCodError);
							QMMovimientosComunidades.modMovimientoComunidad(conexion,movimiento_antiguo,sCodMovimiento);
						}

					}
					else
					{
						//error y rollback - error al eliminar el error
						iCodigo = -905;
						QMMovimientosComunidades.modMovimientoComunidad(conexion,movimiento_antiguo,sCodMovimiento);
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

	public static ArrayList<ErrorTabla> buscarErroresCuota(String sMovimiento)
	{
		return QMListaErroresCuotas.buscaErrores(ConnectionManager.getDBConnection(),sMovimiento);
	}
	
	public static int reparaMovimientoCuota(MovimientoCuota movimiento, String sCodMovimiento, String sCodError)
	{
		int iCodigo = 0;
		
		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			String sCodCuota = CLCuotas.buscarCodigoCuota(movimiento.getCOACES(), movimiento.getCOCLDO(), movimiento.getNUDCOM(), movimiento.getCOSBAC());
			
			MovimientoCuota movimiento_revisado = CLCuotas.revisaCodigosControl(movimiento,sCodCuota);
			
			if (movimiento_revisado.getCOACCI().equals("#"))
			{	
				//Error modificacion sin cambios
				iCodigo = -804;	
			}
			else
			{
				MovimientoCuota movimiento_antiguo = QMMovimientosCuotas.getMovimientoCuota(conexion,sCodMovimiento);
				
				if (!QMMovimientosCuotas.modMovimientoCuota(conexion,movimiento_revisado,sCodMovimiento))
				{
					//Error al crear un movimiento
					iCodigo = -900;
				}
				else
				{
					if(QMListaErroresCuotas.delErrorCuota(conexion,sCodMovimiento, sCodError))
					{	

						if (QMCuotas.modCuota(conexion,CLCuotas.convierteMovimientoenCuota(movimiento), sCodCuota))	
						{
							//OK 
							iCodigo = 0;
							
							ArrayList<String> dependenciascuotas = QMListaCuotas.buscarDependencias(conexion,sCodCuota, sCodMovimiento);


							for (int i = 0; i < dependenciascuotas.size() ; i++)
				            {
				            	QMListaCuotas.setValidado(conexion,dependenciascuotas.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
				            }
				            
						}
						else
						{
							//error y rollback - error al modificar la cuota
							iCodigo = -904;
							QMListaErroresCuotas.addErrorCuota(conexion,sCodMovimiento, sCodError);
							QMMovimientosCuotas.modMovimientoCuota(conexion,movimiento_antiguo,sCodMovimiento);
						}

					}
					else
					{
						//error y rollback - error al eliminar el error
						iCodigo = -904;
						QMMovimientosCuotas.modMovimientoCuota(conexion,movimiento_antiguo,sCodMovimiento);
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

	public static ArrayList<ErrorTabla> buscarErroresReferencia(String sMovimiento)
	{
		return QMListaErroresReferencias.buscaErrores(ConnectionManager.getDBConnection(),sMovimiento);
	}
	
	public static int reparaMovimientoReferencia(MovimientoReferenciaCatastral movimiento, String sCodMovimiento, String sCodError)
	{
		int iCodigo = 0;
		
		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			String sCodReferencia = CLReferencias.buscarCodigoReferencia(movimiento.getNURCAT());
			
			MovimientoReferenciaCatastral movimiento_revisado = CLReferencias.revisaCodigosControl(movimiento,sCodReferencia);
			
			if (movimiento_revisado.getCOACCI().equals("#"))
			{	
				//Error modificacion sin cambios
				iCodigo = -804;	
			}
			else
			{
				MovimientoReferenciaCatastral movimiento_antiguo = QMMovimientosReferencias.getMovimientoReferenciaCatastral(conexion,sCodMovimiento);
				
				if (!QMMovimientosReferencias.modMovimientoReferenciaCatastral(conexion,movimiento_revisado,sCodMovimiento))
				{
					//Error al crear un movimiento
					iCodigo = -900;
				}
				else
				{
					if(QMListaErroresReferencias.delErrorReferencia(conexion,sCodMovimiento, sCodError))
					{	

						if (QMReferencias.modReferenciaCatastral(conexion,CLReferencias.convierteMovimientoenReferencia(movimiento), sCodReferencia))	
						{
							//OK 
							iCodigo = 0;
							
							ArrayList<String> dependenciascuotas = QMListaReferencias.buscarDependencias(conexion,movimiento.getCOACES(), sCodReferencia, sCodMovimiento);


							for (int i = 0; i < dependenciascuotas.size() ; i++)
				            {
				            	QMListaReferencias.setValidado(conexion,dependenciascuotas.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
				            }
				            
						}
						else
						{
							//error y rollback - error al modificar la referencia
							iCodigo = -904;
							QMListaErroresReferencias.addErrorReferencia(conexion,sCodMovimiento, sCodError);
							QMMovimientosReferencias.modMovimientoReferenciaCatastral(conexion,movimiento_antiguo,sCodMovimiento);
						}

					}
					else
					{
						//error y rollback - error al eliminar el error
						iCodigo = -904;
						QMMovimientosReferencias.modMovimientoReferenciaCatastral(conexion,movimiento_antiguo,sCodMovimiento);
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

	public static ArrayList<ErrorTabla> buscarErroresImpuesto(String sMovimiento)
	{
		return QMListaErroresImpuestos.buscaErrores(ConnectionManager.getDBConnection(),sMovimiento);
	}
	
	public static int reparaMovimientoImpuesto(MovimientoImpuestoRecurso movimiento, String sCodMovimiento, String sCodError)
	{
		int iCodigo = 0;
		
		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			String sCodImpuesto = CLImpuestos.buscarCodigoImpuesto(movimiento.getNURCAT(), movimiento.getCOSBAC());
			
			MovimientoImpuestoRecurso movimiento_revisado = CLImpuestos.revisaCodigosControl(movimiento,sCodImpuesto);
			
			if (movimiento_revisado.getCOACCI().equals("#"))
			{	
				//Error modificacion sin cambios
				iCodigo = -804;	
			}
			else
			{
				MovimientoImpuestoRecurso movimiento_antiguo = QMMovimientosImpuestos.getMovimientoImpuestoRecurso(conexion,sCodMovimiento);
				
				if (!QMMovimientosImpuestos.modMovimientoImpuestoRecurso(conexion,movimiento_revisado,sCodMovimiento))
				{
					//Error al crear un movimiento
					iCodigo = -900;
				}
				else
				{
					if(QMListaErroresImpuestos.delErrorImpuesto(conexion,sCodMovimiento, sCodError))
					{	

						if (QMImpuestos.modImpuestoRecurso(conexion,CLImpuestos.convierteMovimientoenImpuesto(movimiento), sCodImpuesto))	
						{
							//OK 
							iCodigo = 0;
							
							ArrayList<String> dependenciasimpuestos = QMListaImpuestos.buscarDependencias(conexion,movimiento.getCOACES(), sCodImpuesto, sCodMovimiento);


							for (int i = 0; i < dependenciasimpuestos.size() ; i++)
				            {
				            	QMListaImpuestos.setValidado(conexion,dependenciasimpuestos.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
				            }
				            
						}
						else
						{
							//error y rollback - error al modificar la impuesto
							iCodigo = -904;
							QMListaErroresImpuestos.addErrorImpuesto(conexion,sCodMovimiento, sCodError);
							QMMovimientosImpuestos.modMovimientoImpuestoRecurso(conexion,movimiento_antiguo,sCodMovimiento);
						}

					}
					else
					{
						//error y rollback - error al eliminar el error
						iCodigo = -904;
						QMMovimientosImpuestos.modMovimientoImpuestoRecurso(conexion,movimiento_antiguo,sCodMovimiento);
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

	public static ArrayList<ErrorTabla> buscarErroresGasto(String sMovimiento)
	{
		return QMListaErroresGastos.buscaErrores(ConnectionManager.getDBConnection(),sMovimiento);
	}
	
	public static int reparaMovimientoGasto(MovimientoGasto movimiento, String sCodMovimiento, String sCodError)
	{
		
		int iCodigo = 0;
		
		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			String sEstado = QMGastos.getEstado(conexion,CLGastos.buscarCodigoGasto(movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE()));

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
				MovimientoGasto movimiento_antiguo = QMMovimientosGastos.getMovimientoGasto(conexion,sCodMovimiento);
				
				logger.debug(movimiento_revisado.logMovimientoGasto());
				
				if (!QMMovimientosGastos.modMovimientoGasto(conexion,movimiento_revisado,sCodMovimiento))
				{
					//Error al crear un movimiento
					iCodigo = -900;
				}
				else
				{
					if(QMListaErroresGastos.delErrorGasto(conexion,sCodMovimiento, sCodError))
					{	

						if (QMGastos.modGasto(conexion,CLGastos.convierteMovimientoenGasto(movimiento_revisado)))
						{
							//OK 
							iCodigo = 0;
							
							String sCodGasto = QMGastos.getGastoID(conexion,movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE());
							
							ArrayList<String> dependenciasgastos = QMListaGastos.buscarDependencias(conexion,sCodGasto, sCodMovimiento);


							for (int i = 0; i < dependenciasgastos.size() ; i++)
				            {
				            	QMListaGastos.setValidado(conexion,dependenciasgastos.get(i),ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE);
				            }
				            
						}
						else
						{
							//error y rollback - error al modificar la gasto
							iCodigo = -904;
							QMListaErroresGastos.addErrorGasto(conexion,sCodMovimiento, sCodError);
							QMMovimientosGastos.modMovimientoGasto(conexion,movimiento_antiguo,sCodMovimiento);
						}

					}
					else
					{
						//error y rollback - error al eliminar el error
						iCodigo = -904;
						QMMovimientosGastos.modMovimientoGasto(conexion,movimiento_antiguo,sCodMovimiento);
					}
				}
			}
		}

		logger.debug("iCodigo:|"+iCodigo+"|");
		return iCodigo;
	}
}
