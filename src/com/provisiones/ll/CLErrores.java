package com.provisiones.ll;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.qm.QMComunidades;
import com.provisiones.dal.qm.listas.QMListaComunidades;
import com.provisiones.dal.qm.listas.QMListaComunidadesActivos;
import com.provisiones.dal.qm.listas.errores.QMListaErroresComunidades;
import com.provisiones.dal.qm.movimientos.QMMovimientosComunidades;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ErrorComunidadTabla;
import com.provisiones.types.ErrorTabla;
import com.provisiones.types.MovimientoComunidad;

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
					
					if (QMComunidades.modComunidad(CLComunidades.convierteMovimientoenComunidad(movimiento), movimiento.getCOCLDO(), movimiento.getNUDCOM()))	
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
}
