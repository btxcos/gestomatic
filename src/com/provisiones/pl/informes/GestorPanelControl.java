package com.provisiones.pl.informes;

import java.io.Serializable;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.ll.CLInformes;
import com.provisiones.misc.Utils;
import com.provisiones.types.informes.RangoAnual;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

public class GestorPanelControl implements Serializable 
{
	private static final long serialVersionUID = -7418304660771677052L;
	
	private static Logger logger = LoggerFactory.getLogger(GestorPanelControl.class.getName());
	

	private String sActivosTotales = "";
	private String sActivosGestionadosTotales = "";
	private String sActivosGestionadosUltimoMes = "";
	
	private ArrayList<RangoAnual> periodoactivos = new ArrayList<RangoAnual>();
	
	private CartesianChartModel model = new CartesianChartModel();

	private String sGastosTotales = "";
	private String sGastosAutorizados = "";
	private String sGastosPagados = "";
	
	private String sValorTotal = "";
	private String sValorAutorizado = "";
	private String sValorPagado = "";
	
	private String sValorPromedio = "";

	private String sProvisionesTotales = "";
	private String sProvisionesAutorizadas = "";
	private String sProvisionesPagadas = "";
	
	private String sPromedioGastosProvision = "";

	
	private String sComunidadesRegistradas = "";
	private String sCuotasRegistradas = "";
	private String sReferenciasRegistradas = "";
	private String sRecursossRegistrados = "";
	
	
	public GestorPanelControl()
	{
		logger.debug("Iniciando GestorPanelControl...");
		//actualizarDatos();
		cargarDatosActivos();
	}
	
	public void generarGraficoActivos()
	{
		ArrayList<String> muestraactivos = CLInformes.buscarActivosGestionadosEnRango(periodoactivos);
		
		ChartSeries evolucionactivos = new ChartSeries();
		evolucionactivos.setLabel("Evolución Activos");
		
		for(int i=0; i<muestraactivos.size(); i++)
		{
			evolucionactivos.set(periodoactivos.get(i).getsMes(), Integer.parseInt(muestraactivos.get(i)));
		}
		
		model.addSeries(evolucionactivos);
	}
	
	public void cargarDatosActivos()
	{
		sActivosTotales = CLInformes.obtenerNumeroActivosTotales();
		sActivosGestionadosTotales = CLInformes.obtenerNumeroActivosGestionadosTotales();
		sActivosGestionadosUltimoMes = CLInformes.obtenerNumeroActivosGestionadosUltimoMes();
		
		periodoactivos = Utils.rangoAnual();

		generarGraficoActivos();

		
	}
	
	public void cargarDatosGastos()
	{
		
	}
	
	public void cargarDatosValores()
	{
		
	}
	
	public void cargarDatosProvisiones()
	{
		
	}
	
	public void cargarDatosAsociaciones()
	{
		
	}
	
	public void actualizarDatos()
	{
		cargarDatosActivos();
		cargarDatosGastos();
		cargarDatosValores();
		cargarDatosProvisiones();
		cargarDatosAsociaciones();
	}

	public String getsActivosTotales() {
		return sActivosTotales;
	}

	public void setsActivosTotales(String sActivosTotales) {
		this.sActivosTotales = sActivosTotales;
	}

	public String getsActivosGestionadosTotales() {
		return sActivosGestionadosTotales;
	}

	public void setsActivosGestionadosTotales(String sActivosGestionadosTotales) {
		this.sActivosGestionadosTotales = sActivosGestionadosTotales;
	}

	public String getsActivosGestionadosUltimoMes() {
		return sActivosGestionadosUltimoMes;
	}

	public void setsActivosGestionadosUltimoMes(String sActivosGestionadosUltimoMes) {
		this.sActivosGestionadosUltimoMes = sActivosGestionadosUltimoMes;
	}

	public CartesianChartModel getModel() {
		return model;
	}
	
	public String getDatatipFormat() {
		return "<span style=\"display:none;\">%s</span><span>%s</span>";
		}

	public String getsGastosTotales() {
		return sGastosTotales;
	}

	public void setsGastosTotales(String sGastosTotales) {
		this.sGastosTotales = sGastosTotales;
	}

	public String getsGastosAutorizados() {
		return sGastosAutorizados;
	}

	public void setsGastosAutorizados(String sGastosAutorizados) {
		this.sGastosAutorizados = sGastosAutorizados;
	}

	public String getsGastosPagados() {
		return sGastosPagados;
	}

	public void setsGastosPagados(String sGastosPagados) {
		this.sGastosPagados = sGastosPagados;
	}

	public String getsValorTotal() {
		return sValorTotal;
	}

	public void setsValorTotal(String sValorTotal) {
		this.sValorTotal = sValorTotal;
	}

	public String getsValorAutorizado() {
		return sValorAutorizado;
	}

	public void setsValorAutorizado(String sValorAutorizado) {
		this.sValorAutorizado = sValorAutorizado;
	}

	public String getsValorPagado() {
		return sValorPagado;
	}

	public void setsValorPagado(String sValorPagado) {
		this.sValorPagado = sValorPagado;
	}

	public String getsValorPromedio() {
		return sValorPromedio;
	}

	public void setsValorPromedio(String sValorPromedio) {
		this.sValorPromedio = sValorPromedio;
	}

	public String getsProvisionesTotales() {
		return sProvisionesTotales;
	}

	public void setsProvisionesTotales(String sProvisionesTotales) {
		this.sProvisionesTotales = sProvisionesTotales;
	}

	public String getsProvisionesAutorizadas() {
		return sProvisionesAutorizadas;
	}

	public void setsProvisionesAutorizadas(String sProvisionesAutorizadas) {
		this.sProvisionesAutorizadas = sProvisionesAutorizadas;
	}

	public String getsProvisionesPagadas() {
		return sProvisionesPagadas;
	}

	public void setsProvisionesPagadas(String sProvisionesPagadas) {
		this.sProvisionesPagadas = sProvisionesPagadas;
	}

	public String getsPromedioGastosProvision() {
		return sPromedioGastosProvision;
	}

	public void setsPromedioGastosProvision(String sPromedioGastosProvision) {
		this.sPromedioGastosProvision = sPromedioGastosProvision;
	}

	public String getsComunidadesRegistradas() {
		return sComunidadesRegistradas;
	}

	public void setsComunidadesRegistradas(String sComunidadesRegistradas) {
		this.sComunidadesRegistradas = sComunidadesRegistradas;
	}

	public String getsCuotasRegistradas() {
		return sCuotasRegistradas;
	}

	public void setsCuotasRegistradas(String sCuotasRegistradas) {
		this.sCuotasRegistradas = sCuotasRegistradas;
	}

	public String getsReferenciasRegistradas() {
		return sReferenciasRegistradas;
	}

	public void setsReferenciasRegistradas(String sReferenciasRegistradas) {
		this.sReferenciasRegistradas = sReferenciasRegistradas;
	}

	public String getsRecursossRegistrados() {
		return sRecursossRegistrados;
	}

	public void setsRecursossRegistrados(String sRecursossRegistrados) {
		this.sRecursossRegistrados = sRecursossRegistrados;
	}

}
