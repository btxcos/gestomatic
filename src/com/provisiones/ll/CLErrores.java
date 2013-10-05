package com.provisiones.ll;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import com.provisiones.types.ErrorComunidadTabla;
import com.provisiones.types.ErrorCuotaTabla;
import com.provisiones.types.ErrorGastoTabla;
import com.provisiones.types.ErrorImpuestoTabla;
import com.provisiones.types.ErrorReferenciaTabla;
import com.provisiones.types.ErrorTabla;
import com.provisiones.types.MovimientoComunidad;
import com.provisiones.types.MovimientoCuota;
import com.provisiones.types.MovimientoGasto;
import com.provisiones.types.MovimientoImpuestoRecurso;
import com.provisiones.types.MovimientoReferenciaCatastral;

public class CLErrores 
{
	private static Logger logger = LoggerFactory.getLogger(CLErrores.class.getName());
	
	public static ArrayList<ErrorComunidadTabla> buscarComunidadesConErrores(ErrorComunidadTabla filtro)
	{
		return QMListaErroresComunidades.buscaComunidadesConError(filtro);
	}
	
	public static ArrayList<ErrorComunidadTabla> buscarComunidadesActivoConErrores(String sCOACES)
	{
		return QMListaErroresComunidades.buscaComunidadesActivoConError(sCOACES);
	}
	
	public static ArrayList<ErrorTabla> buscarErroresComunidad(String sMovimiento)
	{
		return QMListaErroresComunidades.buscaErrores(sMovimiento);
	}

	public static int reparaMovimientoComunidad(MovimientoComunidad movimiento, String sCodMovimiento, String sCodError)
	{
		int iCodigo = 0;
		
		MovimientoComunidad movimiento_revisado = CLComunidades.revisaCodigosControl(movimiento);
		
		if (movimiento_revisado.getCOACCI().equals("#"))
		{	
			//Error modificacion sin cambios
			iCodigo = -804;	
		}
		else
		{
			MovimientoComunidad movimiento_antiguo = QMMovimientosComunidades.getMovimientoComunidad(sCodMovimiento);
			
			if (!QMMovimientosComunidades.modMovimientoComunidad(movimiento_revisado,sCodMovimiento))
			{
				//Error al crear un movimiento
				iCodigo = -900;
			}
			else
			{
				if(QMListaErroresComunidades.delErrorComunidad(sCodMovimiento, sCodError))
				{	
					
					if (QMComunidades.modComunidad(CLComunidades.convierteMovimientoenComunidad(movimiento_revisado), movimiento.getCOCLDO(), movimiento.getNUDCOM()))	
					{
						//OK 
						iCodigo = 0;
						
						ArrayList<String> dependenciascomunidades = QMListaComunidades.buscarDependencias(movimiento.getCOCLDO(), movimiento.getNUDCOM(), sCodMovimiento);
						ArrayList<String> dependenciasactivoscomunidades = QMListaComunidadesActivos.buscarDependencias(movimiento.getCOCLDO(), movimiento.getNUDCOM(), sCodMovimiento);

						for (int i = 0; i < dependenciascomunidades.size() ; i++)
			            {
			            	QMListaComunidades.setValidado(dependenciascomunidades.get(i),ValoresDefecto.DEF_PENDIENTE);
			            }
			            
			            for (int i = 0; i < dependenciasactivoscomunidades.size() ; i++)
			            {
			            	QMListaComunidadesActivos.setValidado(dependenciasactivoscomunidades.get(i),ValoresDefecto.DEF_PENDIENTE);
			            }
			            
					}
					else
					{
						//error y rollback - error al modificar la comunidad
						iCodigo = -905;
						QMListaErroresComunidades.addErrorComunidad(sCodMovimiento, sCodError);
						QMMovimientosComunidades.modMovimientoComunidad(movimiento_antiguo,sCodMovimiento);
					}

				}
				else
				{
					//error y rollback - error al eliminar el error
					iCodigo = -905;
					QMMovimientosComunidades.modMovimientoComunidad(movimiento_antiguo,sCodMovimiento);
				}
			}
		}

		logger.debug("Codigo de Salida:|{}|",iCodigo);
		return iCodigo;
	}

	public static ArrayList<ErrorCuotaTabla> buscarCuotasConErrores(ErrorCuotaTabla filtro)
	{
		return QMListaErroresCuotas.buscaCuotasConError(filtro);
	}

	public static ArrayList<ErrorTabla> buscarErroresCuota(String sMovimiento)
	{
		return QMListaErroresCuotas.buscaErrores(sMovimiento);
	}
	
	public static int reparaMovimientoCuota(MovimientoCuota movimiento, String sCodMovimiento, String sCodError)
	{
		int iCodigo = 0;
		
		MovimientoCuota movimiento_revisado = CLCuotas.revisaCodigosControl(movimiento);
		
		if (movimiento_revisado.getCOACCI().equals("#"))
		{	
			//Error modificacion sin cambios
			iCodigo = -804;	
		}
		else
		{
			MovimientoCuota movimiento_antiguo = QMMovimientosCuotas.getMovimientoCuota(sCodMovimiento);
			
			if (!QMMovimientosCuotas.modMovimientoCuota(movimiento_revisado,sCodMovimiento))
			{
				//Error al crear un movimiento
				iCodigo = -900;
			}
			else
			{
				if(QMListaErroresCuotas.delErrorCuota(sCodMovimiento, sCodError))
				{	

					if (QMCuotas.modCuota(CLCuotas.convierteMovimientoenCuota(movimiento_revisado), movimiento.getCOACES(), movimiento.getCOCLDO(), movimiento.getNUDCOM(), movimiento.getCOSBAC()))	
					{
						//OK 
						iCodigo = 0;
						
						ArrayList<String> dependenciascuotas = QMListaCuotas.buscarDependencias(movimiento.getCOACES(), movimiento.getCOCLDO(), movimiento.getNUDCOM(), movimiento.getCOSBAC(), sCodMovimiento);


						for (int i = 0; i < dependenciascuotas.size() ; i++)
			            {
			            	QMListaCuotas.setValidado(dependenciascuotas.get(i),ValoresDefecto.DEF_PENDIENTE);
			            }
			            
					}
					else
					{
						//error y rollback - error al modificar la cuota
						iCodigo = -904;
						QMListaErroresCuotas.addErrorCuota(sCodMovimiento, sCodError);
						QMMovimientosCuotas.modMovimientoCuota(movimiento_antiguo,sCodMovimiento);
					}

				}
				else
				{
					//error y rollback - error al eliminar el error
					iCodigo = -904;
					QMMovimientosCuotas.modMovimientoCuota(movimiento_antiguo,sCodMovimiento);
				}
			}
		}

		logger.debug("Codigo de Salida:|{}|",iCodigo);
		return iCodigo;
	}
	
	public static ArrayList<ErrorReferenciaTabla> buscarReferenciasConErrores(ErrorReferenciaTabla filtro)
	{
		return QMListaErroresReferencias.buscaReferenciasConError(filtro);
	}

	public static ArrayList<ErrorTabla> buscarErroresReferencia(String sMovimiento)
	{
		return QMListaErroresReferencias.buscaErrores(sMovimiento);
	}
	
	public static int reparaMovimientoReferencia(MovimientoReferenciaCatastral movimiento, String sCodMovimiento, String sCodError)
	{
		int iCodigo = 0;
		
		MovimientoReferenciaCatastral movimiento_revisado = CLReferencias.revisaCodigosControl(movimiento);
		
		if (movimiento_revisado.getCOACCI().equals("#"))
		{	
			//Error modificacion sin cambios
			iCodigo = -804;	
		}
		else
		{
			MovimientoReferenciaCatastral movimiento_antiguo = QMMovimientosReferencias.getMovimientoReferenciaCatastral(sCodMovimiento);
			
			if (!QMMovimientosReferencias.modMovimientoReferenciaCatastral(movimiento_revisado,sCodMovimiento))
			{
				//Error al crear un movimiento
				iCodigo = -900;
			}
			else
			{
				if(QMListaErroresReferencias.delErrorReferencia(sCodMovimiento, sCodError))
				{	

					if (QMReferencias.modReferenciaCatastral(CLReferencias.convierteMovimientoenReferencia(movimiento_revisado), movimiento.getNURCAT()))	
					{
						//OK 
						iCodigo = 0;
						
						ArrayList<String> dependenciascuotas = QMListaReferencias.buscarDependencias(movimiento.getNURCAT(), movimiento.getCOACES(), sCodMovimiento);


						for (int i = 0; i < dependenciascuotas.size() ; i++)
			            {
			            	QMListaReferencias.setValidado(dependenciascuotas.get(i),ValoresDefecto.DEF_PENDIENTE);
			            }
			            
					}
					else
					{
						//error y rollback - error al modificar la referencia
						iCodigo = -904;
						QMListaErroresReferencias.addErrorReferencia(sCodMovimiento, sCodError);
						QMMovimientosReferencias.modMovimientoReferenciaCatastral(movimiento_antiguo,sCodMovimiento);
					}

				}
				else
				{
					//error y rollback - error al eliminar el error
					iCodigo = -904;
					QMMovimientosReferencias.modMovimientoReferenciaCatastral(movimiento_antiguo,sCodMovimiento);
				}
			}
		}

		logger.debug("Codigo de Salida:|{}|",iCodigo);
		return iCodigo;
	}
	
	public static ArrayList<ErrorImpuestoTabla> buscarImpuestosConErrores(ErrorImpuestoTabla filtro)
	{
		return QMListaErroresImpuestos.buscaImpuestosConError(filtro);
	}

	public static ArrayList<ErrorTabla> buscarErroresImpuesto(String sMovimiento)
	{
		return QMListaErroresImpuestos.buscaErrores(sMovimiento);
	}
	
	public static int reparaMovimientoImpuesto(MovimientoImpuestoRecurso movimiento, String sCodMovimiento, String sCodError)
	{
		int iCodigo = 0;
		
		MovimientoImpuestoRecurso movimiento_revisado = CLImpuestos.revisaCodigosControl(movimiento);
		
		if (movimiento_revisado.getCOACCI().equals("#"))
		{	
			//Error modificacion sin cambios
			iCodigo = -804;	
		}
		else
		{
			MovimientoImpuestoRecurso movimiento_antiguo = QMMovimientosImpuestos.getMovimientoImpuestoRecurso(sCodMovimiento);
			
			if (!QMMovimientosImpuestos.modMovimientoImpuestoRecurso(movimiento_revisado,sCodMovimiento))
			{
				//Error al crear un movimiento
				iCodigo = -900;
			}
			else
			{
				if(QMListaErroresImpuestos.delErrorImpuesto(sCodMovimiento, sCodError))
				{	

					if (QMImpuestos.modImpuestoRecurso(CLImpuestos.convierteMovimientoenImpuesto(movimiento_revisado), movimiento.getNURCAT(), movimiento.getCOSBAC()))	
					{
						//OK 
						iCodigo = 0;
						
						ArrayList<String> dependenciasimpuestos = QMListaImpuestos.buscarDependencias(movimiento.getCOACES(), movimiento.getNURCAT(), movimiento.getCOSBAC(), sCodMovimiento);


						for (int i = 0; i < dependenciasimpuestos.size() ; i++)
			            {
			            	QMListaImpuestos.setValidado(dependenciasimpuestos.get(i),ValoresDefecto.DEF_PENDIENTE);
			            }
			            
					}
					else
					{
						//error y rollback - error al modificar la impuesto
						iCodigo = -904;
						QMListaErroresImpuestos.addErrorImpuesto(sCodMovimiento, sCodError);
						QMMovimientosImpuestos.modMovimientoImpuestoRecurso(movimiento_antiguo,sCodMovimiento);
					}

				}
				else
				{
					//error y rollback - error al eliminar el error
					iCodigo = -904;
					QMMovimientosImpuestos.modMovimientoImpuestoRecurso(movimiento_antiguo,sCodMovimiento);
				}
			}
		}

		logger.debug("Codigo de Salida:|{}|",iCodigo);
		return iCodigo;
	}
	public static ArrayList<ErrorGastoTabla> buscarGastosConErrores(ErrorGastoTabla filtro)
	{
		return QMListaErroresGastos.buscaGastosConError(filtro);
	}

	public static ArrayList<ErrorTabla> buscarErroresGasto(String sMovimiento)
	{
		return QMListaErroresGastos.buscaErrores(sMovimiento);
	}
	
	public static int reparaMovimientoGasto(MovimientoGasto movimiento, String sCodMovimiento, String sCodError)
	{
		int iCodigo = 0;
		
		String sEstado = QMGastos.getEstado(movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE());

		logger.debug("sEstado:|{}|",sEstado);
		
		String sAccion = "M";
		
		MovimientoGasto movimiento_revisado = CLGastos.revisaSignos(movimiento,sAccion);
		
		if (movimiento_revisado.getCOSIGA().equals("#"))
		{
			//error modificacion sin cambios
			iCodigo = -806;	

		}
		else
		{
			MovimientoGasto movimiento_antiguo = QMMovimientosGastos.getMovimientoGasto(sCodMovimiento);
			
			logger.debug(movimiento_revisado.logMovimientoGasto());
			
			if (!QMMovimientosGastos.modMovimientoGasto(movimiento_revisado,sCodMovimiento))
			{
				//Error al crear un movimiento
				iCodigo = -900;
			}
			else
			{
				if(QMListaErroresGastos.delErrorGasto(sCodMovimiento, sCodError))
				{	

					if (QMGastos.modGasto(CLGastos.convierteMovimientoenGasto(movimiento_revisado)))
					{
						//OK 
						iCodigo = 0;
						
						ArrayList<String> dependenciasgastos = QMListaGastos.buscarDependencias(movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE(), sCodMovimiento);


						for (int i = 0; i < dependenciasgastos.size() ; i++)
			            {
			            	QMListaGastos.setValidado(dependenciasgastos.get(i),ValoresDefecto.DEF_PENDIENTE);
			            }
			            
					}
					else
					{
						//error y rollback - error al modificar la gasto
						iCodigo = -904;
						QMListaErroresGastos.addErrorGasto(sCodMovimiento, sCodError);
						QMMovimientosGastos.modMovimientoGasto(movimiento_antiguo,sCodMovimiento);
					}

				}
				else
				{
					//error y rollback - error al eliminar el error
					iCodigo = -904;
					QMMovimientosGastos.modMovimientoGasto(movimiento_antiguo,sCodMovimiento);
				}
			}
		}

		logger.debug("Codigo de Salida:|{}|",iCodigo);
		return iCodigo;
	}
}
