package com.provisiones.ll;

import java.util.ArrayList;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.qm.QMCodigosControl;
import com.provisiones.dal.qm.QMGastos;
import com.provisiones.dal.qm.QMProvisiones;
import com.provisiones.dal.qm.listas.QMListaGastos;
import com.provisiones.dal.qm.listas.QMListaGastosProvisiones;
import com.provisiones.dal.qm.movimientos.QMMovimientosGastos;
import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Provision;
import com.provisiones.types.movimientos.MovimientoGasto;
import com.provisiones.types.tablas.ProvisionTabla;

public class CLProvisiones
{
	private static Logger logger = LoggerFactory.getLogger(CLProvisiones.class.getName());

	public static ArrayList<ProvisionTabla> buscarProvisionesAbiertas()
	{
		return QMProvisiones.buscaProvisionesAbiertas();
	}
	
	public static long buscarNumeroProvisionesCerradas()
	{
		return (QMProvisiones.buscaCantidadProvisionesCerradasPendientes());
	}
	
	public static String ultimaProvisionCerrada (String sCodCOSPAT)
	{
		return QMProvisiones.getUltimaProvisionCerrada(sCodCOSPAT);
	}
	
	public static double calcularValorProvision (String sNUPROF)
	{
		return QMListaGastosProvisiones.calculaValorProvision(sNUPROF);
	}

	public static long buscarNumeroGastosProvision(String sNUPROF)
	{
		return QMListaGastosProvisiones.buscaCantidadGastos(sNUPROF);
	}
	
	public static String provisionAsignada (String sCodCOACES)
	{
		String sCOSPAT = CLActivos.sociedadPatrimonialAsociada(sCodCOACES);
		
		if (QMCodigosControl.getDesCampo(QMCodigosControl.TSOCTIT,QMCodigosControl.ISOCTIT,sCOSPAT).equals(""))
		{
			sCOSPAT = "0";
		}

		String sTipo = CLActivos.compruebaTipoActivoSAREB(sCodCOACES);

		String sProvision = QMProvisiones.getProvisionAbierta(sCOSPAT,sTipo);

		if (sProvision.equals(""))
		{
			sProvision = QMProvisiones.getUltimaProvision();
	
			Calendar fecha = Calendar.getInstance();
			
			if (sProvision.equals(""))
			{
				sProvision = "00" + fecha.get(Calendar.YEAR)%100 + ValoresDefecto.DEF_COREAE + "000";
			}
			else
			{
				//Ampliacion de numeros de provision de fondos
				sProvision = Parser.formateaCampoNumerico(sProvision,9);
				int iAmpliacion = Integer.parseInt(sProvision.substring(0, 2));
				
				int iAño = Integer.parseInt(sProvision.substring(2, 4));
				int iNumero = Integer.parseInt(sProvision.substring(6));
				
				logger.debug("iAmpliacion:|{}|",iAmpliacion);
				logger.debug("iAño:|{}|",iAño);
				logger.debug("iNumero:|{}|",iNumero);
			
				if (iAño < fecha.get(Calendar.YEAR)%100)
				{
					iAño = fecha.get(Calendar.YEAR)%100;
				}
			 
				iNumero++;
				
				String sNumero = Integer.toString(iNumero);
				
				if (iNumero < 10)
				{
					sNumero = "00"+sNumero;
				}
				else if (iNumero < 100)
				{
					sNumero = "0"+sNumero;
				}
				//Ampliacion de numeros de provision de fondos
				else if (iNumero > 999)
				{
					sNumero = "000";
					iNumero = 0;
					iAmpliacion++; 
					
				}
				
				String sAmpliacion = Integer.toString(iAmpliacion);
				
				if (iAmpliacion < 10)
				{
					sAmpliacion = "0"+sAmpliacion;
				}
				else if (iAmpliacion == 19 || iAmpliacion == 20)
				{
					sAmpliacion = "21";
				}
				else if (iAmpliacion > 99)
				{
					sNumero = "ERROR";
				}
		
				sProvision = sAmpliacion+iAño+ValoresDefecto.DEF_COREAE+sNumero;
				logger.debug("sProvision:|{}|",sProvision);
			}			
			
			Provision provision = new Provision (sProvision, sCOSPAT, sTipo , "0","0","0","0",ValoresDefecto.DEF_ALTA);
			
			QMProvisiones.addProvision(provision);
		}
		
		return sProvision;
	}
	
	public static Provision detallesProvision (String sCodNUPROF)
	{
		return QMProvisiones.getProvision(sCodNUPROF);
	}
	
	public static boolean existeProvision (String sCodNUPROF)
	{
		return QMProvisiones.existeProvision(sCodNUPROF);
	}
	
	public static boolean estaCerrada (String sCodNUPROF)
	{
		return QMProvisiones.provisionCerrada(sCodNUPROF);
	}
	
	public static boolean cerrarProvision (Provision provision)
	{
		logger.debug(provision.logProvision());
		
		boolean bError = !QMProvisiones.modProvision(provision);
		
        if (!bError)
        {
        	long OK = 0;
    		//Anular todos los gastos pendientes	
    		ArrayList<String> listagastos = QMListaGastosProvisiones.buscaGastosSinValidarEnProvision(provision.getsNUPROF());
    		
    		if (listagastos.size() > 0)
    		{

    			String sActivo = QMGastos.getGasto(listagastos.get(0)).getCOACES();
				
	        	String sNuevaProvision = provisionAsignada(sActivo);
	        	
	        	for (int i = 0; i < listagastos.size() ; i++)
		        {
		        	ArrayList<String> listamovimientos = QMListaGastos.buscarMovimientosGasto(listagastos.get(i));
		        	ArrayList<String> listaestados = new ArrayList<String>();
		        	
		        	String sEstadoGasto = CLGastos.estadoGasto(listagastos.get(i));

		        	for (int j = 0; j < listamovimientos.size() ; j++)
			        {
			        	listaestados.add(QMListaGastos.getValidado(listamovimientos.get(i)));

			        	bError = !QMListaGastos.setValidado(listamovimientos.get(i), ValoresDefecto.DEF_MOVIMIENTO_RESUELTO);
			            
			            if (bError)
			            {
			            	logger.error("ERROR: No se pudo validad el movimiento '"+listamovimientos.get(i)+"'.");
			            	//Si falla volvemos a dejar los movimientos en el estado anterior
				            for (int k = 0; k < j ; k++)
				            {
				            	QMListaGastos.setValidado(listamovimientos.get(k), listaestados.get(k));
				            }
			            	i = listagastos.size();
			            	j = listamovimientos.size();
			            }
			        }
		        	
			        if(!bError)
			        {
			        	
			        	MovimientoGasto movimiento = CLGastos.convierteGastoenMovimiento(QMGastos.getGasto(listagastos.get(i)), provision.getsNUPROF());

			        	String sRevisadoAnterior = QMListaGastosProvisiones.getRevisado(listagastos.get(i));
			        	
			        	
			        	
			        	
			        	//anulamos el gasto antiguo
			        	movimiento.setFEAGTO(Utils.fechaDeHoy(false));
			        	
			        	bError = (CLGastos.registraMovimiento(movimiento,false) < 0);
			        	
			        	
			        	
			        	
				        if(!bError)
				        {
				        	
				        	String sCodMovimiento = QMMovimientosGastos.getMovimientoGastoID(movimiento);

				        	//creamos un nuevo gasto con fecha de hoy
				        	movimiento.setFEAGTO("0");
				        	movimiento.setFEDEVE(Utils.fechaDeHoy(false));
				        	movimiento.setNUPROF(sNuevaProvision);
				        	
				        	//inicializamos los signos del gasto
				        	movimiento.setIMNGAS(movimiento.getYCOS02()+movimiento.getIMNGAS());
				        	movimiento.setYCOS02("");
				        	movimiento.setIMRGAS(movimiento.getYCOS04()+movimiento.getIMRGAS());
				        	movimiento.setYCOS04("");
				        	movimiento.setIMDGAS(movimiento.getYCOS06()+movimiento.getIMDGAS());
				        	movimiento.setYCOS06("");
				        	movimiento.setIMCOST(movimiento.getYCOS08()+movimiento.getIMCOST());
				        	movimiento.setYCOS08("");
				        	movimiento.setIMOGAS(movimiento.getYCOS10()+movimiento.getIMOGAS());
				        	movimiento.setYCOS10("");

				        	bError = (CLGastos.registraMovimiento(movimiento,false) < 0);
				        	if(!bError)
					        {
				        		bError = !QMListaGastosProvisiones.setRevisado(listagastos.get(i), ValoresDefecto.DEF_MOVIMIENTO_RESUELTO);
					        	if(!bError)
						        {
					        		OK++;
					        		logger.debug("Gasto '"+listagastos.get(i)+"' procesado correctamente.");
						        }
					        }
				        	else
				        	{
				        		logger.error("ERROR: No se pudo registrar el nuevo movimiento.");

				        		//restauramos el gasto antiguo
				        		QMMovimientosGastos.delMovimientoGasto(sCodMovimiento);
								QMListaGastos.delRelacionGasto(sCodMovimiento);
								QMGastos.setEstado(listagastos.get(i), sEstadoGasto);
								QMListaGastosProvisiones.setRevisado(listagastos.get(i), sRevisadoAnterior);
								QMGastos.setFechaAnulado(listagastos.get(i), "0");
				        	}
				        
				        }
				        
				        if(bError)
				        {
				        	//Devolver estado anterior a los movimientos
				        	logger.error("ERROR: No se pudo anular el movimiento '"+listamovimientos.get(i)+"'.");
				        	
				        	for (int j = 0; j < listamovimientos.size() ; j++)
					        {
					        	QMListaGastos.setValidado(listamovimientos.get(j), listaestados.get(j));
					        }
			            	i = listagastos.size();
				        }

			        }

			        if(bError)
		        	{
			        	//encaso de fallo en gasto, paramos ejecucion y volvemos a dar de alta la provision
		        		i = listagastos.size();
		        		
		        		provision.setsFEPFON("0");
		        		provision.setsCodEstado("A");
		        	}

		        }
	        	if (OK > 0)
	        	{
		        	//Se han anulado gastos, recalculamos el valor de la provision
		        	provision.setsValorTolal(Utils.compruebaImporte(Double.toString(calcularValorProvision(provision.getsNUPROF()))));
	        	}
	        	QMProvisiones.modProvision(provision);
    		}

		}
        
        if(!bError)
        {
        	//OK
        	logger.debug("Gastos pendientes resueltos.");
        }

		return !bError;
	}
	
	public static void inicializaProvisiones() 
	{
		logger.info("Inicializando provisiones...");
		if (!existeProvision("0"))
		{
			Provision provision = new Provision ("0", "0", "#", "0","0","0","0",ValoresDefecto.DEF_ALTA);
			
			QMProvisiones.addProvision(provision);
		}
		logger.info("Provisiones inicializadas.");
	
	}

}
