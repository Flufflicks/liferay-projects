package com.flufflicks.marketjournal.portal.ui.views;

import java.util.List;
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

import com.flufflicks.marketjournal.portal.util.VaadinPortalUtil;
import com.flufflicks.marketjournal.wrapper.JFreeChartWrapper;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class OrderStatsView to display the statistic charts.
 */
public class OrderChartsView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2174989853292280782L;

	/** The LABEL_GAP. */
	private static final double LABEL_GAP = 0.02;
	
	/** Localization messages */
	final ResourceBundle messages = ResourceBundle.getBundle("i18n", VaadinPortalUtil.getCurrentLocale());

	/** Vertical layout.. */
	private final VerticalLayout verticalLayout = new VerticalLayout();

	/** Stats options layout */
	private final HorizontalLayout optionsLayout = new HorizontalLayout();

	/** The select field for the instrument. */
	private final NativeSelect selectInstrument = new NativeSelect();

	/** The select field for the strategy. */
	private final NativeSelect selectStrategy = new NativeSelect();
	
	private final Button reloadButton = new Button();

	private int i = 0;
	
	/** The chart wrapper */
	private JFreeChartWrapper chartWrapper;

	/**
	 * Instantiates a new order stats view.
	 */
	public OrderChartsView() {
		setupView();
		addComponent(verticalLayout);
	}

	private void setupView() {
		final Locale currentLocale = VaadinPortalUtil.getCurrentLocale();
		final ResourceBundle messages = ResourceBundle.getBundle("i18n", currentLocale);

		selectInstrument.setCaption(messages.getString("orderstats.position.description"));
		selectInstrument.setImmediate(true);
		selectInstrument.setStyleName("selectInstrument");

		selectStrategy.setCaption(messages.getString("orderstats.strategy.description"));
		selectStrategy.setImmediate(true);
		
		reloadButton.setCaption("label.reload");

		optionsLayout.addComponent(selectInstrument);
		optionsLayout.addComponent(selectStrategy);
//		optionsLayout.addComponent(reloadButton);
		verticalLayout.addComponent(optionsLayout);
	}

	/**
	 * Creates the pie chart.
	 *
	 * @param dataSet
	 *            the data set
	 * @param title
	 *            the title
	 */
	public final void createPieChart(final DefaultPieDataset dataSet, final String title) {
		final JFreeChart chart = createPichartWithDataset(dataSet, title);
		final JFreeChartWrapper chartWrapper = new JFreeChartWrapper(chart);
		chartWrapper.setWidth("600px");
		chartWrapper.setHeight("400px");
		// tabSheet.addTab(chartWrapper, title);
	}

	/**
	 * Creates the line chart.
	 *
	 * @param dataSet
	 *            the data set
	 * @param title
	 *            the title
	 */
	public final void createLineChart(final DefaultCategoryDataset dataSet, final String title) {
		if (chartWrapper != null) {
			verticalLayout.removeComponent(chartWrapper);
			chartWrapper = null;
		}
		i = i+1;
		final JFreeChart chart = createLinechartWithDataset(dataSet, title);
		chartWrapper = new JFreeChartWrapper(chart);
		chartWrapper.setWidth("600px");
		chartWrapper.setHeight("400px");
		verticalLayout.addComponent(chartWrapper);
	}

	/**
	 * Removes the tabs (used for view reloading).
	 */
	public final void removceChart() {
		// tabSheet.removeAllComponents();
	}

	/**
	 * Creates a pie chart.
	 *
	 * @param dataset
	 *            the dataset.
	 * @param title
	 *            the title
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
	 * @param dataset
	 *            the dataset.
	 * @param title
	 *            the title
	 * @return The chart.
	 */
	private JFreeChart createLinechartWithDataset(final DefaultCategoryDataset dataset, final String title) {
		final JFreeChart chart = ChartFactory.createLineChart(title, messages.getString("orderstats.trades"), messages.getString("orderstats.capital"),
				dataset, PlotOrientation.VERTICAL, true, true, false);

		final Plot plot = chart.getPlot();

		plot.setNoDataMessage(messages.getString("orderstats.nodata"));
		return chart;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener
	 * .ViewChangeEvent)
	 */
	@Override
	public void enter(final ViewChangeEvent event) {

	}

	/**
	 * Adds the positions to the select.
	 *
	 * @param positions
	 *            the positions
	 */
	public final void addPositions(final List<String> positions) {
		for (final String position : positions) {
			this.selectInstrument.addItem(position);
		}
	}

	/**
	 * Adds the strategies to the select.
	 *
	 * @param strategies
	 *            the strategies
	 */
	public final void addStrategies(final List<String> strategies) {
		for (final String strategy : strategies) {
			this.selectStrategy.addItem(strategy);
		}
	}

	/**
	 * get selected instrument
	 * 
	 * @return
	 */
	public final String getSelectedPosition() {
		final String position = (String) this.selectInstrument.getValue();
		return position;
	}
	
	/**
	 * get selected strategy
	 * 
	 * @return
	 */
	public final String getSelectedStrategy() {
		final String strategy = (String) this.selectStrategy.getValue();
		return strategy;
	}

	/**
	 * set change listener for nativeSelect
	 * 
	 * @param listener
	 */
	public void setChangeListener(final ValueChangeListener listener) {
		this.selectInstrument.addValueChangeListener(listener);
		this.selectStrategy.addValueChangeListener(listener);

	}
	
	/**
	 * add the clicklistener to buttons	
	 * @param listener
	 */
	public void setClickListener(final ClickListener listener) {
		this.reloadButton.addClickListener(listener);
	}

}
