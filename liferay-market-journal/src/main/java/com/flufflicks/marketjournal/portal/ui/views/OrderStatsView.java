package com.flufflicks.marketjournal.portal.ui.views;

import java.util.Locale;
import java.util.ResourceBundle;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.vaadin.addon.JFreeChartWrapper;

import com.flufflicks.marketjournal.portal.util.VaadinPortalUtil;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class OrderStatsView extends VerticalLayout implements View {
	
	private final VerticalLayout mainLayout0 = new VerticalLayout();
	private final TabSheet tabSheet = new TabSheet();
	
	public OrderStatsView() {
//		addComponent(mainLayout0);
		tabSheet.setHeight("500px");
		tabSheet.addStyleName("padded-tabbar");
//		mainLayout0.addComponent(tabSheet);
		addComponent(tabSheet);
	}
	
	public void createPieChart(final DefaultPieDataset dataSet, final String title) {
		final JFreeChart chart = createchart(dataSet, title);
		final JFreeChartWrapper chartWrapper = new JFreeChartWrapper(chart);
		chartWrapper.setWidth("600px");
		chartWrapper.setHeight("400px");
		final VerticalLayout layout = new VerticalLayout(chartWrapper);
		tabSheet.addTab(layout, title);
	}
	
	public void removeTabs(){
		tabSheet.removeAllComponents();
	}



	/**
	 * Creates a sample chart.
	 * 
	 * @param dataset
	 *            the dataset.
	 * 
	 * @return The chart.
	 */
	private JFreeChart createchart(final PieDataset dataset, final String title) {
		final JFreeChart chart = ChartFactory.createPieChart(title, // chart
																					// title
				dataset, // data
				true, // include legend
				true, false);
				
		final PiePlot plot = (PiePlot) chart.getPlot();
		final Locale currentLocale = VaadinPortalUtil.getCurrentLocale();
		final ResourceBundle messages = ResourceBundle.getBundle("i18n", currentLocale);
		
		plot.setNoDataMessage(messages.getString("orderlist.nodata"));
		plot.setCircular(false);
		plot.setLabelGap(0.02);
		return chart;

	}

	@Override
	public void enter(final ViewChangeEvent event) {

	}

}
