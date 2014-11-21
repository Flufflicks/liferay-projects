package com.flufflicks.marketjournal.portal.ui.views;

import java.util.Locale;
import java.util.ResourceBundle;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.vaadin.addon.JFreeChartWrapper;

import com.flufflicks.marketjournal.portal.util.VaadinPortalUtil;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class OrderStatsView to display the statistic charts.
 */
public class OrderStatsView extends VerticalLayout implements View {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2174989853292280782L;

	/** The LABEL_GAP. */
	private static final double LABEL_GAP = 0.02;
	
	/** The tab sheet for the different charts. */
	private final TabSheet tabSheet = new TabSheet();
	
	/**
	 * Instantiates a new order stats view.
	 */
	public OrderStatsView() {
		tabSheet.setHeight("500px");
		tabSheet.addStyleName("padded-tabbar");
		addComponent(tabSheet);
	}
	
	/**
	 * Creates the pie chart.
	 *
	 * @param dataSet the data set
	 * @param title the title
	 */
	public final void createPieChart(final DefaultPieDataset dataSet, final String title) {
		final JFreeChart chart = createPichartWithDataset(dataSet, title);
		final JFreeChartWrapper chartWrapper = new JFreeChartWrapper(chart);
		chartWrapper.setWidth("600px");
		chartWrapper.setHeight("400px");
		final VerticalLayout layout = new VerticalLayout(chartWrapper);
		tabSheet.addTab(layout, title);
	}
	
	/**
	 * Creates the line chart.
	 *
	 * @param dataSet the data set
	 * @param title the title
	 */
	public final void createLineChart(final DefaultCategoryDataset dataSet, final String title) {
		final JFreeChart chart = createLinechartWithDataset(dataSet, title);
		final JFreeChartWrapper chartWrapper = new JFreeChartWrapper(chart);
		chartWrapper.setWidth("600px");
		chartWrapper.setHeight("400px");
		final VerticalLayout layout = new VerticalLayout(chartWrapper);
		tabSheet.addTab(layout, title);
	}
	
	/**
	 * Removes the tabs (used for view reloading).
	 */
	public final void removeTabs() {
		tabSheet.removeAllComponents();
	}



	/**
	 * Creates a pie chart.
	 *
	 * @param dataset            the dataset.
	 * @param title the title
	 * @return The chart.
	 */
	private JFreeChart createPichartWithDataset(final PieDataset dataset, final String title) {
		final JFreeChart chart = ChartFactory.createPieChart(title, // chart
																					// title
				dataset, // data
				true, // include legend
				true, false);
				
		final PiePlot plot = (PiePlot) chart.getPlot();
		final Locale currentLocale = VaadinPortalUtil.getCurrentLocale();
		final ResourceBundle messages = ResourceBundle.getBundle("i18n", currentLocale);
		
		plot.setNoDataMessage(messages.getString("orderstats.nodata"));
		plot.setCircular(false);
		plot.setLabelGap(LABEL_GAP);
		return chart;
	}
	

	/**
	 * Creates a line chart.
	 *
	 * @param dataset            the dataset.
	 * @param title the title
	 * @return The chart.
	 */
	private JFreeChart createLinechartWithDataset(final DefaultCategoryDataset dataset, final String title) {
		final Locale currentLocale = VaadinPortalUtil.getCurrentLocale();
		final ResourceBundle messages = ResourceBundle.getBundle("i18n", currentLocale);
		final JFreeChart chart = ChartFactory.createLineChart(title, messages.getString("orderstats.trades"), messages.getString("orderstats.capital"), dataset, PlotOrientation.VERTICAL, true, true, false);
						
		final Plot plot = chart.getPlot();
			
		plot.setNoDataMessage(messages.getString("orderstats.nodata"));
		return chart;
	}
	
	/* (non-Javadoc)
	 * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	public void enter(final ViewChangeEvent event) {

	}

}
