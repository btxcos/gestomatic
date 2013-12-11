package com.provisiones.ll;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMCodigosControl;
import com.provisiones.dal.qm.QMGastos;
import com.provisiones.dal.qm.QMProvisiones;
import com.provisiones.dal.qm.listas.QMListaGastosProvisiones;
import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Cierre;
import com.provisiones.types.Provision;
import com.provisiones.types.tablas.ProvisionTabla;

public final class CLProvisiones
{
	private static Logger logger = LoggerFactory.getLogger(CLProvisiones.class.getName());

	private CLProvisiones(){}
	
	public static long buscarNumeroGastosProvision(String sNUPROF)
	{
		return QMListaGastosProvisiones.buscaCantidadGastos(ConnectionManager.getDBConnection(),sNUPROF);
	}
	
	public static ArrayList<String> buscarGastosProvision(String sNUPROF)
	{
		return QMListaGastosProvisiones.buscaGastosPorProvision(ConnectionManager.getDBConnection(),sNUPROF);
	}
	
	
	public static long buscarNumeroProvisionesCerradas()
	{
		return (QMProvisiones.buscaCantidadProvisionesCerradasPendientes(ConnectionManager.getDBConnection()));
	}

	public static ArrayList<ProvisionTabla> buscarProvisionesAbiertas()
	{
		return QMProvisiones.buscaProvisionesAbiertas(ConnectionManager.getDBConnection());
	}	
	
	public static String calcularValorProvision (String sNUPROF)
	{
		long liValor = QMListaGastosProvisiones.calculaValorProvision(ConnectionManager.getDBConnection(),sNUPROF);
		boolean bNegativo = (liValor < 0);
		return Utils.recuperaImporte(bNegativo,Long.toString(liValor));
	}
	
	public static Provision buscarProvision (String sCodNUPROF)
	{
		return QMProvisiones.getProvision(ConnectionManager.getDBConnection(),sCodNUPROF);
	}
	
	public static String estadoProvision (String sCodNUPROF)
	{
		return QMProvisiones.getEstado(ConnectionManager.getDBConnection(),sCodNUPROF);
	}
	
	public static boolean estaCerrada (String sCodNUPROF)
	{
		return QMProvisiones.provisionCerrada(ConnectionManager.getDBConnection(),sCodNUPROF);
	}
	
	public static boolean existeProvision (String sCodNUPROF)
	{
		return QMProvisiones.existeProvision(ConnectionManager.getDBConnection(),sCodNUPROF);
	}
	
	public static String ultimaProvisionCerrada (String sCodCOSPAT)
	{
		return QMProvisiones.getUltimaProvisionCerrada(ConnectionManager.getDBConnection(),sCodCOSPAT);
	}
	
	public static int inyertarCierreVolcado(String linea)
	{
		int iCodigo = 0;

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			Cierre cierre = Parser.leerCierre(linea);
			
			if (QMProvisiones.existeProvision(conexion,cierre.getsNUPROF()))
			{
				
				if (QMProvisiones.provisionCerrada(conexion, cierre.getsNUPROF()))
				{
					Provision provision = buscarProvision(cierre.getsNUPROF());
					
					provision.setsFEPFON(cierre.getsFEPFON());
					provision.setsFechaEnvio(Utils.fechaDeHoy(false));
					provision.setsFechaFacturado(Utils.fechaDeHoy(false));
					provision.setsCodEstado(ValoresDefecto.DEF_PROVISION_FACTURADA);

					if (QMProvisiones.modProvision(conexion,provision))
					{
						ArrayList<String> listagastos = buscarGastosProvision(cierre.getsNUPROF());
			    		if (listagastos.size() > 0)
			    		{
				        	for (int i = 0; i < listagastos.size() ; i++)
					        {
					        	
					        	String sEstado = "";
					        	
					        	if (QMGastos.getValor(conexion, listagastos.get(i)) < 0)
					        	{
					        		sEstado = ValoresDefecto.DEF_GASTO_ABONADO;
					        	}
					        	else
					        	{
					        		sEstado = ValoresDefecto.DEF_GASTO_PAGADO;
					        	}
					        	
					        	QMGastos.setEstado(conexion, listagastos.get(i), sEstado);
					        }

			    		}
			    		//TODO else rollback
						
						logger.debug("Provision '"+cierre.getsNUPROF()+"' validada.");
					}
					else
					{
						//no se ha validado la provisión
						iCodigo = -2;
					}
				}
				else
				{
					//provisión no cerrada
					iCodigo = -3;
				}

			}
			else 
			{
				//provisión inexistente
				iCodigo = -1;
			}
		}
		
		logger.error("iCodigo:|"+iCodigo+"|");
		
		return iCodigo;
	}
	
	
	public static boolean cerrarProvision (Provision provision)
	{
		boolean bError = true;
		
		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			logger.debug(provision.logProvision());
			
			bError = !QMProvisiones.modProvision(conexion,provision);
			
	        /*if (!bError)
	        {
	        	long OK = 0;
	    		//Anular todos los gastos pendientes	
	    		ArrayList<String> listagastos = QMListaGastosProvisiones.buscaGastosSinValidarEnProvision(conexion,provision.getsNUPROF());
	    		
	    		if (listagastos.size() > 0)
	    		{

	    			String sActivo = QMGastos.getGasto(conexion,listagastos.get(0)).getCOACES();
					
		        	String sNuevaProvision = provisionAsignada(sActivo);
		        	
		        	for (int i = 0; i < listagastos.size() ; i++)
			        {
			        	ArrayList<String> listamovimientos = QMListaGastos.buscarMovimientosGasto(conexion,listagastos.get(i));
			        	ArrayList<String> listaestados = new ArrayList<String>();
			        	
			        	String sEstadoGasto = CLGastos.estadoGasto(listagastos.get(i));

			        	for (int j = 0; j < listamovimientos.size() ; j++)
				        {
				        	listaestados.add(QMListaGastos.getValidado(conexion,listamovimientos.get(i)));

				        	bError = !QMListaGastos.setValidado(conexion,listamovimientos.get(i), ValoresDefecto.DEF_MOVIMIENTO_RESUELTO);
				            
				            if (bError)
				            {
				            	logger.error("ERROR: No se pudo validad el movimiento '"+listamovimientos.get(i)+"'.");
				            	//Si falla volvemos a dejar los movimientos en el estado anterior
					            for (int k = 0; k < j ; k++)
					            {
					            	QMListaGastos.setValidado(conexion,listamovimientos.get(k), listaestados.get(k));
					            }
				            	i = listagastos.size();
				            	j = listamovimientos.size();
				            }
				        }
			        	
				        if(!bError)
				        {
				        	
				        	MovimientoGasto movimiento = CLGastos.convierteGastoenMovimiento(QMGastos.getGasto(conexion,listagastos.get(i)), provision.getsNUPROF());

				        	String sRevisadoAnterior = QMListaGastosProvisiones.getRevisado(conexion,listagastos.get(i));
				        	
				        	
				        	//anulamos el gasto antiguo
				        	movimiento.setFEAGTO(Utils.fechaDeHoy(false));
				        	
				        	

				        	//inicializamos los signos del gasto
				        	String sYCOS02 = movimiento.getYCOS02();
				        	String sYCOS04 = movimiento.getYCOS04();
				        	String sYCOS06 = movimiento.getYCOS06();
				        	String sYCOS08 = movimiento.getYCOS08();
				        	String sYCOS10 = movimiento.getYCOS10();
				        	
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
					        	
					        	//restablecemos los signos
					        	movimiento.setIMNGAS(movimiento.getIMNGAS().replaceFirst("-", ""));
					        	movimiento.setYCOS02(sYCOS02);
					        	movimiento.setIMRGAS(movimiento.getIMRGAS().replaceFirst("-", ""));
					        	movimiento.setYCOS04(sYCOS04);
					        	movimiento.setIMDGAS(movimiento.getIMDGAS().replaceFirst("-", ""));
					        	movimiento.setYCOS06(sYCOS06);
					        	movimiento.setIMCOST(movimiento.getIMCOST().replaceFirst("-", ""));
					        	movimiento.setYCOS08(sYCOS08);
					        	movimiento.setIMOGAS(movimiento.getIMOGAS().replaceFirst("-", ""));
					        	movimiento.setYCOS10(sYCOS10);
					        	
					        	String sCodMovimiento = QMMovimientosGastos.getMovimientoGastoID(conexion,movimiento);

					        	//creamos un nuevo gasto con fecha de hoy
					        	movimiento.setFEAGTO("0");
					        	movimiento.setFEDEVE(Utils.fechaDeHoy(false));
					        	movimiento.setNUPROF(sNuevaProvision);
					        	
					        	
					        	//Volvemos a inicializar los signos
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
					        		bError = !QMListaGastosProvisiones.setRevisado(conexion,listagastos.get(i), ValoresDefecto.DEF_MOVIMIENTO_RESUELTO);
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
					        		QMMovimientosGastos.delMovimientoGasto(conexion,sCodMovimiento);
									QMListaGastos.delRelacionGasto(conexion,sCodMovimiento);
									QMGastos.setEstado(conexion,listagastos.get(i), sEstadoGasto);
									QMListaGastosProvisiones.setRevisado(conexion,listagastos.get(i), sRevisadoAnterior);
									QMGastos.setFechaAnulado(conexion,listagastos.get(i), "0");
					        	}
					        
					        }
					        
					        if(bError)
					        {
					        	//Devolver estado anterior a los movimientos
					        	logger.error("ERROR: No se pudo anular el movimiento '"+listamovimientos.get(i)+"'.");
					        	
					        	for (int j = 0; j < listamovimientos.size() ; j++)
						        {
						        	QMListaGastos.setValidado(conexion,listamovimientos.get(j), listaestados.get(j));
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
			        	provision.setsValorTolal(Utils.compruebaImporte(calcularValorProvision(provision.getsNUPROF())));
		        	}
		        	QMProvisiones.modProvision(conexion,provision);
	    		}

			}*/
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
		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			logger.info("Inicializando provisiones...");
			if (!existeProvision("0"))
			{
				Provision provision = new Provision ("0", "0", "#", "0","0","0","0","0","0","0","0",ValoresDefecto.DEF_PROVISION_ABIERTA);
				
				QMProvisiones.addProvision(conexion,provision);
			}
			logger.info("Provisiones inicializadas.");
		}
	}
	
	public static String provisionAsignada (String sCodCOACES)
	{
		String sProvision = "";

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			String sCOSPAT = CLActivos.sociedadPatrimonialAsociada(sCodCOACES);
			
			if (QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TSOCTIT,QMCodigosControl.ISOCTIT,sCOSPAT).equals(""))
			{
				sCOSPAT = "0";
			}

			String sTipo = CLActivos.compruebaTipoActivoSAREB(sCodCOACES);

			sProvision = QMProvisiones.getProvisionAbierta(conexion,sCOSPAT,sTipo);

			if (sProvision.equals(""))
			{
				sProvision = QMProvisiones.getUltimaProvision(conexion);
		
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
				
				Provision provision = new Provision (sProvision, sCOSPAT, sTipo , "0","0","0","0","0","0","0","0",ValoresDefecto.DEF_PROVISION_ABIERTA);
				
				QMProvisiones.addProvision(conexion,provision);
			}
		}
		
		return sProvision;
	}

}
