package com.flufflicks.marketjournal.portal.presenter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.flufflicks.marketjournal.portal.ui.views.OrderChartsView;
import com.flufflicks.marketjournal.portal.util.VaadinPortalUtil;
import com.flufflicks.marketjournal.spring.bo.OrderDataBo;
import com.flufflicks.marketjournal.spring.bridge.SpringBoHelper;
import com.flufflicks.marketjournal.spring.model.OrderData;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * The Class OrderStatsPresenter.
 */
public class OrderChartsPresenter implements Presenter, ValueChangeListener, ClickListener{

	/** The format for decimal format in chart key. */
	private final DecimalFormat percentageFormat = new DecimalFormat("0.00");

	/** The view. */
	private final OrderChartsView view;

	/** The order data bussiness object. */
	private final OrderDataBo orderDataBo = SpringBoHelper.getOrderDataBo();

	final ResourceBundle messages = ResourceBundle.getBundle("i18n", VaadinPortalUtil.getCurrentLocale());

	/**
	 * Instantiates a new order stats presenter.
	 *
	 * @param orderStatsView
	 *            the view
	 */
	public OrderChartsPresenter(final OrderChartsView orderStatsView) {
		this.view = orderStatsView;
	}

	private void loadCharts() {
		this.view.removceChart();

		final List<OrderData> orders = orderDataBo.findAll();
		addPositions(orders);
		final String position = this.view.getSelectedPosition();
		final String strategy = this.view.getSelectedStrategy();
		
		// view.createPieChart(createOverviewDataset(orders),
		// messages.getString("orderstats.chart.overview"));
		// view.createPieChart(createWinLossDataset(orders),
		// messages.getString("orderstats.chart.guvstats"));
		final DefaultCategoryDataset dataset = createCapitalDataset(orders, position, strategy);
		view.createLineChart(dataset, messages.getString("orderstats.chart.capital"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flufflicks.marketjournal.portal.presenter.Presenter#bind()
	 */
	@Override
	public final void bind() {
		view.setCaption("");
		view.setChangeListener(this);
		view.setClickListener(this);
		loadCharts();
	}

	/**
	 * Adds the positions from portlet properties.
	 */
	private void addPositions(final List<OrderData> orders) {
		final List<String> positions = getPositions(orders);
		this.view.addPositions(positions);

		final List<String> strategies = getStrategies(orders);
		this.view.addStrategies(strategies);
	}

	/**
	 * Get all positions from orders
	 * 
	 * @param orders
	 * @return
	 */
	private List<String> getPositions(final List<OrderData> orders) {
		final Set<String> positionSet = new HashSet<String>();

		for (final OrderData order : orders) {
			positionSet.add(order.getInstrument());
		}

		final List<String> list = new ArrayList<String>();
		list.addAll(positionSet);
		return list;
	}

	/**
	 * Get all positions from orders
	 * 
	 * @param orders
	 * @return
	 */
	private List<String> getStrategies(final List<OrderData> orders) {
		final Set<String> strategySet = new HashSet<String>();

		for (final OrderData order : orders) {
			strategySet.add(order.getStrategy());
		}

		final List<String> list = new ArrayList<String>();
		list.addAll(strategySet);
		return list;
	}

	/**
	 * Returns the dataset for the overview chart.
	 *
	 * @param orders
	 *            the order from datatabase
	 * @return The result dataset.
	 */
	private DefaultPieDataset createOverviewDataset(final List<OrderData> orders) {

		final int count = orders.size();
		final Map<String, Float> instrumentMap = getInstrumentMap(orders);
		final Object[] keys = instrumentMap.keySet().toArray();
		final DefaultPieDataset dataset = new DefaultPieDataset();

		for (final Object o : keys) {
			final String key = (String) o;
			final float percentage = instrumentMap.get(key) / count;
			dataset.setValue(key + "(" + percentageFormat.format(percentage) + ")", percentage);

		}
		return dataset;
	}

	/**
	 * Returns the dataset for the WIN/LOSS chart.
	 *
	 * @param orders
	 *            the orders from database
	 * @return The dataset.
	 */
	private DefaultPieDataset createWinLossDataset(final List<OrderData> orders) {
		float win = 0;
		float loss = 0;
		final int count = orders.size();

		for (final OrderData orderData : orders) {
			if (orderData.getGuv() > 0) {
				win++;
			} else if (orderData.getGuv() < 0) {
				loss++;
			}
		}
		final DefaultPieDataset dataset = new DefaultPieDataset();
		final String winrate = percentageFormat.format(win / count);
		percentageFormat.format(loss / count);
		dataset.setValue("Win (" + winrate + ")", win);
		dataset.setValue("Loss (" + winrate + ")", loss);

		return dataset;
	}

	/**
	 * Returns the dataset for the capital chart.
	 *
	 * @param orders
	 *            the orders from database
	 * @return The dataset.
	 */
	private DefaultCategoryDataset createCapitalDataset(final List<OrderData> orders, final String position, final String strategy) {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		float guv = 0.0f;
		int i = 0;
		for (final OrderData order : orders) {

			if ((position == null || order.getInstrument().equals(position))
					&& ( strategy == null || order.getStrategy().equals(strategy))) {
				i++;
				System.out.println("add: " + order.getInstrument() + " - " + order.getStrategy());
				guv += order.getGuv();
				dataset.addValue(guv, "orders", String.valueOf(i));
			}
		}
		return dataset;
	}

	/**
	 * Gets the instruments with the overall count.
	 *
	 * @param orders
	 *            the orders from database
	 * @return the instrument map
	 */
	private Map<String, Float> getInstrumentMap(final List<OrderData> orders) {
		final Map<String, Float> map = new HashMap<String, Float>();
		for (final OrderData orderData : orders) {
			final String instrument = orderData.getInstrument();
			float f1 = 0f;
			if (map.get(instrument) != null) {
				final float f = map.get(instrument);
				f1 = f + 1f;
			}
			map.put(instrument, f1);
		}
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flufflicks.marketjournal.portal.presenter.Presenter#unbind()
	 */
	@Override
	public final void unbind() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.data.Property.ValueChangeListener#valueChange(com.vaadin.data
	 * .Property.ValueChangeEvent)
	 */
	@Override
	public void valueChange(final ValueChangeEvent event) {
		loadCharts();		
	}

	/*
	 * (non-Javadoc)
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
	 */
	@Override
	public void buttonClick(final ClickEvent event) {
		loadCharts();		
	}

}
