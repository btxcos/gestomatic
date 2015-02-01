package com.provisiones.ll;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMInformes;

import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Activo;
import com.provisiones.types.informes.CierreInforme;
import com.provisiones.types.informes.RangoAnual;
import com.provisiones.types.tablas.GastoTabla;
import com.provisiones.types.tablas.ProvisionTabla;

public class CLInformes 
{
	private static Logger logger = LoggerFactory.getLogger(CLInformes.class.getName());
	
	private CLInformes(){}
	
	public static ArrayList<CierreInforme> buscarInformeCierreProvision(String sNUPROF)
	{
		return QMInformes.buscaCierreInformeProvision(ConnectionManager.getDBConnection(),sNUPROF);
	}


	public static String obtenerNumeroActivosTotales()
	{
		return QMInformes.getActivosTotales(ConnectionManager.getDBConnection());
	}
	
	public static String obtenerNumeroActivosGestionadosTotales()
	{
		return QMInformes.getActivosGestionadosTotales(ConnectionManager.getDBConnection());
	}
	
	public static String obtenerNumeroActivosGestionadosUltimoMes()
	{
		return QMInformes.getActivosGestionadosUltimoMes(ConnectionManager.getDBConnection());
	}

	public static String obtenerNumeroActivosVendidosTotales()
	{
		return QMInformes.getActivosVendidosTotales(ConnectionManager.getDBConnection());
	}
	
	public static String obtenerGastosTotales()
	{
		return QMInformes.getGastosTotales(ConnectionManager.getDBConnection());
	}
	
	public static String obtenerGastosAutorizados()
	{
		return QMInformes.getGastosEstado(ConnectionManager.getDBConnection(),ValoresDefecto.DEF_GASTO_AUTORIZADO);
	}
	
	public static String obtenerGastosPagados()
	{
		return QMInformes.getGastosEstado(ConnectionManager.getDBConnection(),ValoresDefecto.DEF_GASTO_PAGADO);
	}
	
	public static String obtenerValorPromedioGasto()
	{
		long liValorPromedio = (Long.parseLong(QMInformes.getValorTotalGastos(ConnectionManager.getDBConnection())))
				/(Long.parseLong(QMInformes.getGastosTotales(ConnectionManager.getDBConnection())));
		
		String sValorPromedio = Utils.cortaDecimales(Long.toString(liValorPromedio));
		
		return Utils.recuperaImporte(sValorPromedio.startsWith("-"), sValorPromedio);
	}
	
	public static String obtenerTotalGastosComunidades()
	{
		return QMInformes.getTotalGastosTipoEstado(ConnectionManager.getDBConnection(),ValoresDefecto.DEF_GASTO_COMUNIDADES,ValoresDefecto.CAMPO_ALFA_SIN_INFORMAR);
	}
	
	public static String obtenerTotalGastosImpuestos()
	{
		return QMInformes.getTotalGastosTipoEstado(ConnectionManager.getDBConnection(),ValoresDefecto.DEF_GASTO_IMPUESTOS,ValoresDefecto.CAMPO_ALFA_SIN_INFORMAR);
	}
	
	public static String obtenerTotalGastosOtros()
	{
		return QMInformes.getTotalGastosTipoEstado(ConnectionManager.getDBConnection(),ValoresDefecto.CAMPO_NUME_SIN_INFORMAR,ValoresDefecto.CAMPO_ALFA_SIN_INFORMAR);
	}
	
	public static String obtenerTotalGastosComunidadesPagados()
	{
		return QMInformes.getTotalGastosTipoEstado(ConnectionManager.getDBConnection(),ValoresDefecto.DEF_GASTO_COMUNIDADES,ValoresDefecto.DEF_GASTO_PAGADO);
	}
	
	public static String obtenerTotalGastosImpuestosPagados()
	{
		return QMInformes.getTotalGastosTipoEstado(ConnectionManager.getDBConnection(),ValoresDefecto.DEF_GASTO_IMPUESTOS,ValoresDefecto.DEF_GASTO_PAGADO);
	}
	
	public static String obtenerTotalGastosOtrosPagados()
	{
		return QMInformes.getTotalGastosTipoEstado(ConnectionManager.getDBConnection(),ValoresDefecto.CAMPO_NUME_SIN_INFORMAR,ValoresDefecto.DEF_GASTO_PAGADO);
	}
	
	public static String obtenerTotalProvisiones()
	{
		return QMInformes.getProvisionesTotales(ConnectionManager.getDBConnection());
	}
	
	public static String obtenerProvisionesAutorizadas()
	{
		return QMInformes.getProvisionesEstado(ConnectionManager.getDBConnection(),ValoresDefecto.DEF_PROVISION_AUTORIZADA);
	}
	
	public static String obtenerProvisionesPagadas()
	{
		return QMInformes.getProvisionesEstado(ConnectionManager.getDBConnection(),ValoresDefecto.DEF_PROVISION_PAGADA);
	}
	
	public static String obtenerPromedioGastosProvision()
	{
		long liValorPromedio = (Long.parseLong(QMInformes.getGastosEnProvisionesTotales(ConnectionManager.getDBConnection())))
				/(Long.parseLong(QMInformes.getProvisionesTotales(ConnectionManager.getDBConnection())));
		
		logger.debug("liValorPromedio:|"+liValorPromedio+"|");
		
		return Long.toString(liValorPromedio);
	}
	
	public static String obtenerValorProvisionado()
	{
		String sValor = QMInformes.getValorProvisionado(ConnectionManager.getDBConnection());
		
		return Utils.recuperaImporte(sValor.startsWith("-"), sValor);
	}
	
	public static String obtenerValorProvisionadoAutorizado()
	{
		String sValor = QMInformes.getValorProvisionadoEstado(ConnectionManager.getDBConnection(),ValoresDefecto.DEF_PROVISION_AUTORIZADA);
		
		return Utils.recuperaImporte(sValor.startsWith("-"), sValor);
	}
	
	public static String obtenerValorProvisionadoPagado()
	{
		String sValor = QMInformes.getValorProvisionadoEstado(ConnectionManager.getDBConnection(),ValoresDefecto.DEF_PROVISION_PAGADA);
		
		return Utils.recuperaImporte(sValor.startsWith("-"), sValor);
	}
	
	public static String obtenerComunidadesTolales()
	{
		return QMInformes.getComunidadesTotales(ConnectionManager.getDBConnection());
	}
	
	public static String obtenerCuotasTolales()
	{
		return QMInformes.getCuotasTotales(ConnectionManager.getDBConnection());
	}
	
	public static String obtenerReferenciasCatastralesTolales()
	{
		return QMInformes.getReferenciasCatastralesTotales(ConnectionManager.getDBConnection());
	}
	
	public static String obtenerRecursosTolales()
	{
		return QMInformes.getRecursosTotales(ConnectionManager.getDBConnection());
	}
	
	public static ArrayList<String> buscarActivosGestionadosEnRango (ArrayList<RangoAnual> rango)
	{
		return QMInformes.buscaActivosGestionadosEnRango(ConnectionManager.getDBConnection(), rango);
	}

	public static ArrayList<String> buscarActivosVendidosAcumuladosEnRango (ArrayList<RangoAnual> rango)
	{
		return QMInformes.buscaActivosVendidosAcumuladosEnRango(ConnectionManager.getDBConnection(), rango);
	}
	
	public static ArrayList<String> buscarActivosNuevosEnRango (ArrayList<RangoAnual> rango)
	{
		return QMInformes.buscaActivosNuevosEnRango(ConnectionManager.getDBConnection(), rango);
	}

	public static ArrayList<String> buscarGastosAutorizadosEnRango (ArrayList<RangoAnual> rango)
	{
		return QMInformes.buscaGastosEstadoEnRango(ConnectionManager.getDBConnection(), rango, ValoresDefecto.DEF_GASTO_AUTORIZADO);
	}
	
	public static ArrayList<String> buscarGastosPagadosEnRango (ArrayList<RangoAnual> rango)
	{
		return QMInformes.buscaGastosEstadoEnRango(ConnectionManager.getDBConnection(), rango, ValoresDefecto.DEF_GASTO_PAGADO);
	}

	public static ArrayList<String> buscarProvisionesAutorizadasEnRango (ArrayList<RangoAnual> rango)
	{
		return QMInformes.buscaProvisionesEstadoEnRango(ConnectionManager.getDBConnection(), rango, ValoresDefecto.DEF_PROVISION_AUTORIZADA);
	}
	
	public static ArrayList<String> buscarProvisionesPagadasEnRango (ArrayList<RangoAnual> rango)
	{
		return QMInformes.buscaProvisionesEstadoEnRango(ConnectionManager.getDBConnection(), rango, ValoresDefecto.DEF_PROVISION_PAGADA);
	}
	
	public static ArrayList<String> buscarValoresProvisionesAutorizadasEnRango (ArrayList<RangoAnual> rango)
	{
		return QMInformes.buscaValoresProvisionesEstadoEnRango(ConnectionManager.getDBConnection(), rango, ValoresDefecto.DEF_PROVISION_AUTORIZADA);
	}
	
	public static ArrayList<String> buscarValoresProvisionesPagadasEnRango (ArrayList<RangoAnual> rango)
	{
		return QMInformes.buscaValoresProvisionesEstadoEnRango(ConnectionManager.getDBConnection(), rango, ValoresDefecto.DEF_PROVISION_PAGADA);
	}
	
	public static ArrayList<String> buscarComunidadesEnRango (ArrayList<RangoAnual> rango)
	{
		return QMInformes.buscaComunidadesEnRango(ConnectionManager.getDBConnection(), rango);
	}
	
	public static ArrayList<String> buscarCuotasEnRango (ArrayList<RangoAnual> rango)
	{
		return QMInformes.buscaCuotasEnRango(ConnectionManager.getDBConnection(), rango);
	}
	
	public static ArrayList<String> buscarReferenciasCatastralesEnRango (ArrayList<RangoAnual> rango)
	{
		return QMInformes.buscaReferenciasCatastralesEnRango(ConnectionManager.getDBConnection(), rango);
	}
	
	public static ArrayList<String> buscarRecursosEnRango (ArrayList<RangoAnual> rango)
	{
		return QMInformes.buscaRecursosEnRango(ConnectionManager.getDBConnection(), rango);
	}
	
	
	public static String generarInformeGastosActivo(int iCOACES, ArrayList<GastoTabla> listagastos)
	{
		String sNombreInforme = "";
		
		if (listagastos.size() > 0)
		{
			Document pdf = new Document();
			
			sNombreInforme = ValoresDefecto.DEF_PATH_BACKUP_INFORMES+Utils.timeStamp()+"_Informe_Gastos_Activo_"+iCOACES+".pdf";
			
			try 
			{
				FileOutputStream ficheroPdf = new FileOutputStream(sNombreInforme);
				
				PdfWriter.getInstance(pdf,ficheroPdf).setInitialLeading(20);
				
				pdf.open();
				
				
				//Cabecera
				
				pdf.setPageSize(PageSize.A4);
				pdf.open();
				pdf.newPage();
				
				ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
				
				String logo = servletContext.getRealPath("") + File.separator + "recursos"+ File.separator +"cabecera.png";
				
				logger.debug ("logo:|"+logo+"|");
				
				
				Phrase phraseBefore = new Phrase("Gastos del Activo "+iCOACES+"  Página ");
				
				HeaderFooter header = new HeaderFooter(phraseBefore, true);

				Font fuentecabecera = FontFactory.getFont(
						"arial",
		                12,
		                Font.NORMAL);

				pdf.add(Image.getInstance(logo));
				
				Activo activo = CLActivos.buscarDetallesActivo(iCOACES);
				
				//Cabecera
				
				String sFincaRegistral = "";
				
				if (activo.getNUFIRE().isEmpty() || activo.getNUFIRE().equals(ValoresDefecto.CAMPO_NUME_SIN_INFORMAR))
				{
					sFincaRegistral = "N/D";
				}
				else
				{
					sFincaRegistral = activo.getNUFIRE();
				}
				
				
				String sFechaAdjudicacion = "";
				
				if (activo.getFEADAC().isEmpty() || activo.getFEADAC().equals(ValoresDefecto.CAMPO_NUME_SIN_INFORMAR))
				{
					sFechaAdjudicacion = "N/D";
				}
				else
				{
					sFechaAdjudicacion = Utils.recuperaFecha(activo.getFEADAC());
				}

				
				String sFechaVenta = "";
				
				if (activo.getFEVACT().isEmpty() || activo.getFEVACT().equals(ValoresDefecto.CAMPO_NUME_SIN_INFORMAR))
				{
					sFechaVenta = "N/D";
				}
				else
				{
					sFechaVenta = Utils.recuperaFecha(activo.getFEVACT());
				}
				
				String sCalle = "";
				
				if (activo.getNOVIAS().isEmpty())
				{
					sCalle = "N/D";
				}
				else
				{
					sCalle = activo.getNOVIAS();
				}
				
				String sNumero = "";
				
				if (activo.getNUPOAC().isEmpty())
				{
					sNumero = "N/D";
				}
				else
				{
					sNumero = activo.getNUPOAC();
				}
				
				String sPiso = "";
				
				if (activo.getNUPIAC().isEmpty())
				{
					sPiso = "N/D";
				}
				else
				{
					sPiso = activo.getNUPIAC();
				}
				
				String sEscalera = "";
				
				if (activo.getNUESAC().isEmpty())
				{
					sEscalera = "N/D";
				}
				else
				{
					sEscalera = activo.getNUESAC();
				}
				
				String sLetra = "";
				
				if (activo.getNUPUAC().isEmpty())
				{
					sLetra = "N/D";
				}
				else
				{
					sLetra = activo.getNUPUAC();
				}
				
				String sCP = "";
				
				if (activo.getCOPOIN().isEmpty() || activo.getCOPOIN().equals(ValoresDefecto.CAMPO_NUME_SIN_INFORMAR))
				{
					sCP = "N/D";
				}
				else
				{
					sCP = activo.getCOPOIN();
				}
				
				String sMunicipio = "";
				
				if (activo.getNOMUIN().isEmpty())
				{
					sMunicipio = "N/D";
				}
				else
				{
					sMunicipio = activo.getNOMUIN();
				}
				
				String sProvincia = "";
				
				if (activo.getNOPRAC().isEmpty())
				{
					sProvincia = "N/D";
				}
				else
				{
					sProvincia = activo.getNOPRAC();
				}

				String sCIF = CLComunidades.obtenerCIFComunidadDeActivo(iCOACES);
				
				pdf.add(new Paragraph("Activo: "+iCOACES+"   Finca Registral: "+sFincaRegistral+"   Adjudicación: "+sFechaAdjudicacion +"   Venta: "+sFechaVenta +"\n",fuentecabecera));

				if (!sCIF.isEmpty())
				{
					pdf.add(new Paragraph("Comunidad: "+sCIF+"\n",fuentecabecera));
				}

				pdf.add(new Paragraph("Calle: "+sCalle + "   Número: "+sNumero+"\n",fuentecabecera));
				pdf.add(new Paragraph("Escalera: "+sEscalera +"   Piso: "+sPiso+"   Letra: "+sLetra+"\n",fuentecabecera));
				pdf.add(new Paragraph("C.P.: "+sCP +"   Municipio: "+sMunicipio + "   Provincia: "+sProvincia +"\n\n",fuentecabecera));
				
				pdf.setHeader(header);
				
				//Tabla
				

				
				
				
				int iColumnas = 7;
				int iFilas = listagastos.size();
				
				float[] columnWidths = new float[] {8f, 8f, 30f, 15f, 20f, 10f, 9f};
				
				PdfPTable tabla = new PdfPTable(iColumnas);
				
				tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				tabla.setWidthPercentage(100);
				tabla.setSpacingBefore(0f);
				tabla.setSpacingAfter(0f);
				
				
				tabla.setWidths(columnWidths);

				Font fuentecabeceraceldas = FontFactory.getFont(
						"times",
		                8,
		                Font.BOLD);
				
				
				tabla.addCell(new Phrase ("Subtipo",fuentecabeceraceldas));
				tabla.addCell(new Phrase ("Devengo",fuentecabeceraceldas));
				tabla.addCell(new Phrase ("Concepto",fuentecabeceraceldas));
				tabla.addCell(new Phrase ("Periodo",fuentecabeceraceldas));
				tabla.addCell(new Phrase ("Importe",fuentecabeceraceldas));
				tabla.addCell(new Phrase ("Provision",fuentecabeceraceldas));
				tabla.addCell(new Phrase ("Pago",fuentecabeceraceldas));

				Font fuenteceldas = FontFactory.getFont(
						"times",
		                8,
		                Font.NORMAL);

				long liValorTotal = 0;
				int iNumPagados = 0;
				long liValorPagados = 0;
				
				for (int i = 0; i < iFilas; i++)
				{
				    tabla.addCell(new Phrase (listagastos.get(i).getCOSBGA(),fuenteceldas));
				    tabla.addCell(new Phrase (listagastos.get(i).getFEDEVE(),fuenteceldas));
					tabla.addCell(new Phrase (listagastos.get(i).getDCOSBGA(),fuenteceldas));
				    tabla.addCell(new Phrase (listagastos.get(i).getDPTPAGO(),fuenteceldas));
				    tabla.addCell(new Phrase (listagastos.get(i).getIMNGAS(),fuenteceldas));
				    tabla.addCell(new Phrase (listagastos.get(i).getNUPROF(),fuenteceldas));
				    tabla.addCell(new Phrase (listagastos.get(i).getFEEPAI(),fuenteceldas));

				    long liValor = Long.parseLong(Utils.compruebaImporte(listagastos.get(i).getIMNGAS()));
				    liValorTotal = liValorTotal + liValor;
				    if (!listagastos.get(i).getFEEPAI().isEmpty())
				    {
				    	iNumPagados++;
				    	liValorPagados = liValorPagados + liValor;
				    }

				}
				pdf.add(tabla);
				
				//Pie
				
				logger.debug ("pdf.getPageNumber():|"+pdf.getPageNumber()+"|");
				
				Font fuentepie = FontFactory.getFont(
						"arial",
		                10,
		                Font.BOLD);
				
				
				String sValorTotal = Long.toString(liValorTotal);
				String sValorPendiente = "";
				
				if (liValorTotal >= 0)
				{
					if (liValorPagados >= 0)
					{
						sValorPendiente = Long.toString(liValorTotal - liValorPagados);
					}
					else
					{
						sValorPendiente = Long.toString(liValorTotal + liValorPagados);
					}
				}
				else
				{
					sValorPendiente = Long.toString(liValorTotal + liValorPagados);
				}
				
				
				String sValorPagado = Long.toString(liValorPagados);
				
				Paragraph pargastostotales = new Paragraph("\n\nNúmero de Gastos: "+ listagastos.size(),fuentepie);
				pargastostotales.setAlignment(Element.ALIGN_RIGHT);
				pdf.add(pargastostotales);
				Paragraph parvalortotal = new Paragraph("Valor de Gastos: "+ Utils.recuperaImporte(sValorTotal.startsWith("-"), sValorTotal) +"¤",fuentepie);
				parvalortotal.setAlignment(Element.ALIGN_RIGHT);
				pdf.add(parvalortotal);
				
				Paragraph pargastospendientes = new Paragraph("\nNúmero de Gastos por pagar: "+ (listagastos.size() - iNumPagados),fuentepie);
				pargastospendientes.setAlignment(Element.ALIGN_RIGHT);
				pdf.add(pargastospendientes);
				Paragraph parvalorpendiente = new Paragraph("Valor de Gastos por pagar: "+ Utils.recuperaImporte(sValorPendiente.startsWith("-"), sValorPendiente) +"¤",fuentepie);
				parvalorpendiente.setAlignment(Element.ALIGN_RIGHT);
				pdf.add(parvalorpendiente);
				
				Paragraph pargastospagados = new Paragraph("\nNúmero de Gastos pagados: "+ iNumPagados,fuentepie);
				pargastospagados.setAlignment(Element.ALIGN_RIGHT);
				pdf.add(pargastospagados);
				Paragraph parvalorpagados = new Paragraph("Valor de Gastos pagados: "+ Utils.recuperaImporte(sValorPagado.startsWith("-"), sValorPagado) +"¤",fuentepie);
				parvalorpagados.setAlignment(Element.ALIGN_RIGHT);
				pdf.add(parvalorpagados);
				
				pdf.close();
				
			} 
			catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (MalformedURLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
		
		
		return sNombreInforme;
	}
	
	public static String generarInformeCierreProvision(ProvisionTabla provision)
	{
		String sNombreInforme = "";
		
		ArrayList<CierreInforme> informe =  buscarInformeCierreProvision(provision.getNUPROF());
		
		if (informe.size() > 0)
		{
			Document pdf = new Document();
			
			sNombreInforme = ValoresDefecto.DEF_PATH_BACKUP_INFORMES+Utils.timeStamp()+"_Informe_Cierre_"+provision.getNUPROF()+".pdf";
			
			try 
			{
				FileOutputStream ficheroPdf = new FileOutputStream(sNombreInforme);
				
				PdfWriter.getInstance(pdf,ficheroPdf).setInitialLeading(20);
				
				pdf.open();
				
				
				//Cabecera
				
				pdf.setPageSize(PageSize.A4.rotate());
				pdf.open();
				pdf.newPage();
				
				ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
				
				String logo = servletContext.getRealPath("") + File.separator + "recursos"+ File.separator +"cabecera.png";
				
				logger.debug ("logo:|"+logo+"|");
				
				
				Phrase phraseBefore = new Phrase("Provisión "+provision.getNUPROF()+"  Página ");
				
				HeaderFooter header = new HeaderFooter(phraseBefore, true);

				Font fuentecabecera = FontFactory.getFont(
						"arial",
		                12,
		                Font.NORMAL);
				
				String sTipoGastos = provision.getCOGRUG() + provision.getCOTPGA();
				
				String sContenido = "";
				
				if (sTipoGastos.equals(ValoresDefecto.DEF_GASTO_COMUNIDADES) 
					||sTipoGastos.equals(ValoresDefecto.DEF_GASTO_SUMINISTROS))
				{
					sContenido = "Comunidades";
				}
				else if (sTipoGastos.equals(ValoresDefecto.DEF_GASTO_IMPUESTOS)
						||sTipoGastos.equals(ValoresDefecto.DEF_GASTO_PLUSVALIAS))
				{
					sContenido = "Tributos";
				}
				else
				{
					sContenido = provision.getDCOTPGA();
				}

				pdf.add(Image.getInstance(logo));
				pdf.add(new Paragraph("Provisión de fondos: "+provision.getNUPROF()+"   Fecha: "+Utils.fechaDeHoy(true) +"\n",fuentecabecera)); 
				pdf.add(new Paragraph("Sociedad Patrimonial: "+provision.getDCOSPAT()+"   Gastos contenidos: "+ sContenido +"\n\n",fuentecabecera));

				
				pdf.setHeader(header);
				
				//Tabla
				

				
				
				
				int iColumnas = 10;
				int iFilas = informe.size();
				
				float[] columnWidths = new float[] {9f, 12f, 25f, 8f, 10f, 11f, 30f, 20f, 15f, 13f};
				
				
				//Definicion de columnas segun tipo de gastos
				
				if (sTipoGastos.equals(ValoresDefecto.DEF_GASTO_COMUNIDADES))
				{
					iColumnas = 12;
					columnWidths = new float[] {9f, 12f, 25f, 8f, 10f, 11f, 30f, 20f, 15f, 10f, 10f, 13f};
				}
				else if (sTipoGastos.equals(ValoresDefecto.DEF_GASTO_SUMINISTROS))
				{
					iColumnas = 11;
					columnWidths = new float[] {9f, 12f, 25f, 8f, 10f, 11f, 30f, 20f, 15f, 10f, 13f};
				}
				else if (sTipoGastos.equals(ValoresDefecto.DEF_GASTO_IMPUESTOS)
						||sTipoGastos.equals(ValoresDefecto.DEF_GASTO_PLUSVALIAS))
				{
					iColumnas = 11;
					columnWidths = new float[] {9f, 12f, 25f, 8f, 10f, 11f, 30f, 20f, 15f, 22f, 13f};
				}

				PdfPTable tabla = new PdfPTable(iColumnas);
				
				//tabla.setw.setWidths(new int[]{24, 10});
				//tabla.setTotalWidth(150);
		          //Establecemos la altura de la celda
				//tabla.getDefaultCell().setFixedHeight(20);
		          //Quitamos el borde
				//tabla.getDefaultCell().setBorder(Rectangle.NO_BORDER);

				tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				tabla.setWidthPercentage(100);
				tabla.setSpacingBefore(0f);
				tabla.setSpacingAfter(0f);
				
				
				tabla.setWidths(columnWidths);

				Font fuentecabeceraceldas = FontFactory.getFont(
						"times",
		                8,
		                Font.BOLD);
				
				
				//tabla.addCell(new Phrase ("Referencia",fuentecabeceraceldas));
				tabla.addCell(new Phrase ("Activo",fuentecabeceraceldas));
				tabla.addCell(new Phrase ("Adjudicación",fuentecabeceraceldas));
				tabla.addCell(new Phrase ("Obra",fuentecabeceraceldas));
				//tabla.addCell(new Phrase ("Grupo",fuentecabeceraceldas));
				//tabla.addCell(new Phrase ("Tipo",fuentecabeceraceldas));
				tabla.addCell(new Phrase ("Subtipo",fuentecabeceraceldas));
				tabla.addCell(new Phrase ("Devengo",fuentecabeceraceldas));
				tabla.addCell(new Phrase ("Vencimiento",fuentecabeceraceldas));
				tabla.addCell(new Phrase ("Concepto",fuentecabeceraceldas));
				tabla.addCell(new Phrase ("Periodo",fuentecabeceraceldas));
				tabla.addCell(new Phrase ("Importe",fuentecabeceraceldas));

				if (sTipoGastos.equals(ValoresDefecto.DEF_GASTO_COMUNIDADES)
					|| sTipoGastos.equals(ValoresDefecto.DEF_GASTO_SUMINISTROS))
				{
					tabla.addCell(new Phrase ("CIF",fuentecabeceraceldas));
				}

				if (sTipoGastos.equals(ValoresDefecto.DEF_GASTO_COMUNIDADES))
				{
					tabla.addCell(new Phrase ("Acta",fuentecabeceraceldas));
				}
				
				if (sTipoGastos.equals(ValoresDefecto.DEF_GASTO_IMPUESTOS))
				{
					tabla.addCell(new Phrase ("Referencia Catastral",fuentecabeceraceldas));
				}
				
				
				tabla.addCell(new Phrase ("Observaciones",fuentecabeceraceldas));
				
				
				Font fuenteceldas = FontFactory.getFont(
						"times",
		                8,
		                Font.NORMAL);
				
				for (int i = 0; i < iFilas; i++)
				{
				    //tabla.addCell(new Phrase (informe.get(i).getREFERENCIA(),fuenteceldas));
				    tabla.addCell(new Phrase (informe.get(i).getACTIVO(),fuenteceldas));
				    tabla.addCell(new Phrase (informe.get(i).getADJUDICACION(),fuenteceldas));
				    tabla.addCell(new Phrase (informe.get(i).getOBRA(),fuenteceldas));
				    //tabla.addCell(new Phrase (informe.get(i).getGRUPO(),fuenteceldas));
				    //tabla.addCell(new Phrase (informe.get(i).getTIPO(),fuenteceldas));
				    tabla.addCell(new Phrase (informe.get(i).getSUBTIPO(),fuenteceldas));
				    tabla.addCell(new Phrase (informe.get(i).getDEVENGO(),fuenteceldas));
				    tabla.addCell(new Phrase (informe.get(i).getVENCIMIENTO(),fuenteceldas));
				    tabla.addCell(new Phrase (informe.get(i).getCONCEPTO(),fuenteceldas));
				    tabla.addCell(new Phrase (informe.get(i).getPERIODO(),fuenteceldas));
				    tabla.addCell(new Phrase (informe.get(i).getIMPORTE(),fuenteceldas));
				    
					if (sTipoGastos.equals(ValoresDefecto.DEF_GASTO_COMUNIDADES)
							|| sTipoGastos.equals(ValoresDefecto.DEF_GASTO_SUMINISTROS))
						{
							tabla.addCell(new Phrase (informe.get(i).getCIF(),fuenteceldas));
						}
				    
					if (sTipoGastos.equals(ValoresDefecto.DEF_GASTO_COMUNIDADES))
					{
						tabla.addCell(new Phrase (informe.get(i).getACTA(),fuenteceldas));
					}
				   
					if (sTipoGastos.equals(ValoresDefecto.DEF_GASTO_IMPUESTOS))
					{
						tabla.addCell(new Phrase (informe.get(i).getREFERENCIACATASTRAL(),fuenteceldas));
					}

					tabla.addCell(new Phrase (informe.get(i).getOBSERVACIONES(),fuenteceldas));
				}
				pdf.add(tabla);
				
				//Pie
				
				logger.debug ("pdf.getPageNumber():|"+pdf.getPageNumber()+"|");
				
				Font fuentepie = FontFactory.getFont(
						"arial",
		                10,
		                Font.BOLD);
				
				
				Paragraph pargastos = new Paragraph("\n\nNúmero de Gastos: "+ provision.getGASTOS(),fuentepie);
				pargastos.setAlignment(Element.ALIGN_RIGHT);
				pdf.add(pargastos);
				Paragraph parvalor = new Paragraph("Valor de provisión: "+ provision.getVALOR() +"¤",fuentepie);
				parvalor.setAlignment(Element.ALIGN_RIGHT);
				pdf.add(parvalor);
				
				pdf.close();
				
			} 
			catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (MalformedURLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
		
		
		return sNombreInforme;
	}

}

//Autor: Francisco Valverde Manjón