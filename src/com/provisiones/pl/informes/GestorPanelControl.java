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
import org.primefaces.model.chart.PieChartModel;

public class GestorPanelControl implements Serializable 
{
	private static final long serialVersionUID = -7418304660771677052L;
	
	private static Logger logger = LoggerFactory.getLogger(GestorPanelControl.class.getName());
	

	private String sActivosTotales = "";
	private String sActivosGestionadosTotales = "";
	private String sActivosGestionadosUltimoMes = "";
	private String sActivosVendidosTotales = "";
	
	private ArrayList<RangoAnual> periodoanual = Utils.rangoAnual();
	
	private CartesianChartModel graficolineasactivos;
	private CartesianChartModel graficolineasgastos;

	private CartesianChartModel graficolineasprovisiones;
	private CartesianChartModel graficolineasvaloresprovisiones;

	private PieChartModel graficotartatipogastos;
	private PieChartModel graficotartatipogastospagados;
	
	private String sGastosTotales = "";
	private String sGastosAutorizados = "";
	private String sGastosPagados = "";
	
	private String sGastosComunidades = "";
	private String sGastosImpuestos = "";
	private String sGastosOtros = "";
	
	private String sGastosComunidadesPagados = "";
	private String sGastosImpuestosPagados = "";
	private String sGastosOtrosPagados = "";
	
	private String sValorPromedio = "";

	private String sProvisionesTotales = "";
	private String sProvisionesAutorizadas = "";
	private String sProvisionesPagadas = "";
	
	private String sPromedioGastosProvision = "";

	private String sValorProvisionado = "";
	private String sValorAutorizado = "";
	private String sValorPagado = "";
	
	private String sComunidadesRegistradas = "";
	private String sCuotasRegistradas = "";
	private String sReferenciasRegistradas = "";
	private String sRecursossRegistrados = "";
	
	
	public GestorPanelControl()
	{
		logger.debug("Iniciando GestorPanelControl...");
		actualizarDatos();
	}
	
	public void generarGraficoLineasActivos()
	{
		graficolineasactivos = new CartesianChartModel();
		
		ArrayList<String> muestragestionados = CLInformes.buscarActivosGestionadosEnRango(periodoanual);
		
		ChartSeries evoluciongestionados = new ChartSeries();
		evoluciongestionados.setLabel("Gestionados");
		
		for(int i=0; i<muestragestionados.size(); i++)
		{
			evoluciongestionados.set(periodoanual.get(i).getsMes(), Long.parseLong(muestragestionados.get(i)));
		}
		
		graficolineasactivos.addSeries(evoluciongestionados);

		ArrayList<String> muestravendidos = CLInformes.buscarActivosVendidosAcumuladosEnRango(periodoanual);
		
		ChartSeries evolucionvendidos = new ChartSeries();
		evolucionvendidos.setLabel("Vendidos");
		
		for(int i=0; i<muestravendidos.size(); i++)
		{
			evolucionvendidos.set(periodoanual.get(i).getsMes(), Long.parseLong(muestravendidos.get(i)));
		}
		
		graficolineasactivos.addSeries(evolucionvendidos);
	}
	
	public void generarGraficoLineasGastos()
	{
		graficolineasgastos = new CartesianChartModel();
		
		ArrayList<String> muestraautorizados = CLInformes.buscarGastosAutorizadosEnRango(periodoanual);
		
		ChartSeries evolucionautoriazados = new ChartSeries();
		evolucionautoriazados.setLabel("Autorizados");
		
		for(int i=0; i<muestraautorizados.size(); i++)
		{
			evolucionautoriazados.set(periodoanual.get(i).getsMes(), Long.parseLong(muestraautorizados.get(i)));
		}
		
		graficolineasgastos.addSeries(evolucionautoriazados);
		
		ArrayList<String> muestrapagados= CLInformes.buscarGastosPagadosEnRango(periodoanual);
		
		ChartSeries evolucionpagados = new ChartSeries();
		evolucionpagados.setLabel("Pagados");
		
		for(int i=0; i<muestrapagados.size(); i++)
		{
			evolucionpagados.set(periodoanual.get(i).getsMes(), Long.parseLong(muestrapagados.get(i)));
		}
		
		graficolineasgastos.addSeries(evolucionpagados);
	}
	
	public void generarGraficoTartaGastos()
	{
		graficotartatipogastos = new PieChartModel();
		
		graficotartatipogastos.set("Comunidades", Long.parseLong(sGastosComunidades));
		graficotartatipogastos.set("Impuestos", Long.parseLong(sGastosImpuestos));
		graficotartatipogastos.set("Otros", Long.parseLong(sGastosOtros));
	}
	
	public void generarGraficoTartaGastosPagados()
	{
		graficotartatipogastospagados = new PieChartModel();
		
		graficotartatipogastospagados.set("Comunidades", Long.parseLong(sGastosComunidadesPagados));
		graficotartatipogastospagados.set("Impuestos", Long.parseLong(sGastosImpuestosPagados));
		graficotartatipogastospagados.set("Otros", Long.parseLong(sGastosOtrosPagados));
	}
	
	public void generarGraficoLineasProvisiones()
	{
		graficolineasprovisiones = new CartesianChartModel();
		
		ArrayList<String> muestraautorizadas = CLInformes.buscarProvisionesAutorizadasEnRango(periodoanual);
		
		ChartSeries evolucionautorizadas = new ChartSeries();
		evolucionautorizadas.setLabel("Autorizadas");
		
		for(int i=0; i<muestraautorizadas.size(); i++)
		{
			evolucionautorizadas.set(periodoanual.get(i).getsMes(), Long.parseLong(muestraautorizadas.get(i)));
		}
		
		graficolineasprovisiones.addSeries(evolucionautorizadas);

		ArrayList<String> muestrapagadas = CLInformes.buscarProvisionesPagadasEnRango(periodoanual);
		
		ChartSeries evolucionpagadas = new ChartSeries();
		evolucionpagadas.setLabel("Pagadas");
		
		for(int i=0; i<muestrapagadas.size(); i++)
		{
			evolucionpagadas.set(periodoanual.get(i).getsMes(), Long.parseLong(muestrapagadas.get(i)));
		}
		
		graficolineasprovisiones.addSeries(evolucionpagadas);
	}
	
	public void generarGraficoLineasValoresProvisiones()
	{
		graficolineasvaloresprovisiones = new CartesianChartModel();
		
		ArrayList<String> muestravalorautorizados = CLInformes.buscarValoresProvisionesAutorizadasEnRango(periodoanual);
		
		ChartSeries evolucionautorizados = new ChartSeries();
		evolucionautorizados.setLabel("Autorizado");
		
		for(int i=0; i<muestravalorautorizados.size(); i++)
		{
			evolucionautorizados.set(periodoanual.get(i).getsMes(), Long.parseLong(muestravalorautorizados.get(i)));
		}
		
		graficolineasvaloresprovisiones.addSeries(evolucionautorizados);

		ArrayList<String> muestravalorpagados = CLInformes.buscarValoresProvisionesPagadasEnRango(periodoanual);
		
		ChartSeries evolucionpagados = new ChartSeries();
		evolucionpagados.setLabel("Pagado");
		
		for(int i=0; i<muestravalorpagados.size(); i++)
		{
			evolucionpagados.set(periodoanual.get(i).getsMes(), Long.parseLong(muestravalorpagados.get(i)));
		}
		
		graficolineasvaloresprovisiones.addSeries(evolucionpagados);
	}
	
	public void generarGraficosActivos()
	{
		generarGraficoLineasActivos();
	}
	
	public void generarGraficosGastos()
	{

		generarGraficoLineasGastos();
		generarGraficoTartaGastos();
		generarGraficoTartaGastosPagados();
		
	}
	
	public void generarGraficosProvisiones()
	{
		generarGraficoLineasProvisiones();
		generarGraficoLineasValoresProvisiones();
	}
	
	
	public void cargarDatosActivos()
	{
		sActivosTotales = CLInformes.obtenerNumeroActivosTotales();
		sActivosGestionadosTotales = CLInformes.obtenerNumeroActivosGestionadosTotales();
		sActivosGestionadosUltimoMes = CLInformes.obtenerNumeroActivosGestionadosUltimoMes();
		sActivosVendidosTotales = CLInformes.obtenerNumeroActivosVendidosTotales();

		generarGraficosActivos();

		
	}
	
	public void cargarDatosGastos()
	{
		sGastosTotales = CLInformes.obtenerGastosTotales();
		sGastosAutorizados = CLInformes.obtenerGastosAutorizados();
		sGastosPagados = CLInformes.obtenerGastosPagados();
		sValorPromedio = CLInformes.obtenerValorPromedioGasto();
		
		sGastosComunidades = CLInformes.obtenerTotalGastosComunidades();
		sGastosImpuestos = CLInformes.obtenerTotalGastosImpuestos();
		sGastosOtros = CLInformes.obtenerTotalGastosOtros();
		
		sGastosComunidadesPagados = CLInformes.obtenerTotalGastosComunidadesPagados();
		sGastosImpuestosPagados = CLInformes.obtenerTotalGastosImpuestosPagados();
		sGastosOtrosPagados = CLInformes.obtenerTotalGastosOtrosPagados();
		
		generarGraficosGastos();
	}
	
	public void cargarDatosProvisiones()
	{
		sProvisionesTotales = CLInformes.obtenerTotalProvisiones();
		sProvisionesAutorizadas = CLInformes.obtenerProvisionesAutorizadas();
		sProvisionesPagadas = CLInformes.obtenerProvisionesPagadas();
		
		sPromedioGastosProvision = CLInformes.obtenerPromedioGastosProvision();
		
		sValorProvisionado = CLInformes.obtenerValorProvisionado();
		sValorAutorizado = CLInformes.obtenerValorProvisionadoAutorizado();
		sValorPagado = CLInformes.obtenerValorProvisionadoPagado();
		
		generarGraficosProvisiones();	
	}
	
	public void cargarDatosAsociaciones()
	{
		
	}
	
	public void actualizarDatos()
	{
		cargarDatosActivos();
		cargarDatosGastos();
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

	public String getsActivosVendidosTotales() {
		return sActivosVendidosTotales;
	}

	public void setsActivosVendidosTotales(String sActivosVendidosTotales) {
		this.sActivosVendidosTotales = sActivosVendidosTotales;
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

	public String getsGastosComunidades() {
		return sGastosComunidades;
	}

	public void setsGastosComunidades(String sGastosComunidades) {
		this.sGastosComunidades = sGastosComunidades;
	}

	public String getsGastosImpuestos() {
		return sGastosImpuestos;
	}

	public void setsGastosImpuestos(String sGastosImpuestos) {
		this.sGastosImpuestos = sGastosImpuestos;
	}

	public String getsGastosOtros() {
		return sGastosOtros;
	}

	public void setsGastosOtros(String sGastosOtros) {
		this.sGastosOtros = sGastosOtros;
	}

	public String getsGastosComunidadesPagados() {
		return sGastosComunidadesPagados;
	}

	public void setsGastosComunidadesPagados(String sGastosComunidadesPagados) {
		this.sGastosComunidadesPagados = sGastosComunidadesPagados;
	}

	public String getsGastosImpuestosPagados() {
		return sGastosImpuestosPagados;
	}

	public void setsGastosImpuestosPagados(String sGastosImpuestosPagados) {
		this.sGastosImpuestosPagados = sGastosImpuestosPagados;
	}

	public String getsGastosOtrosPagados() {
		return sGastosOtrosPagados;
	}

	public void setsGastosOtrosPagados(String sGastosOtrosPagados) {
		this.sGastosOtrosPagados = sGastosOtrosPagados;
	}

	public String getsValorProvisionado() {
		return sValorProvisionado;
	}

	public void setsValorProvisionado(String sValorProvisionado) {
		this.sValorProvisionado = sValorProvisionado;
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

	public CartesianChartModel getGraficolineasactivos() {
		return graficolineasactivos;
	}

	public CartesianChartModel getGraficolineasgastos() {
		return graficolineasgastos;
	}

	public PieChartModel getGraficotartatipogastos() {
		return graficotartatipogastos;
	}

	public PieChartModel getGraficotartatipogastospagados() {
		return graficotartatipogastospagados;
	}

	public CartesianChartModel getGraficolineasprovisiones() {
		return graficolineasprovisiones;
	}

	public CartesianChartModel getGraficolineasvaloresprovisiones() {
		return graficolineasvaloresprovisiones;
	}
}
