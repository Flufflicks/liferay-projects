package com.flufflicks.marketjournal.portal.presenter;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.flufflicks.marketjournal.portal.ui.views.OrderStatsView;
import com.flufflicks.marketjournal.portal.util.VaadinPortalUtil;
import com.flufflicks.marketjournal.spring.bo.OrderDataBo;
import com.flufflicks.marketjournal.spring.bridge.SpringBoHelper;
import com.flufflicks.marketjournal.spring.model.OrderData;

/**
 * The Class OrderStatsPresenter.
 */
public class OrderStatsPresenter implements Presenter {

	/** The format for decimal format in chart key. */
	private final DecimalFormat percentageFormat = new DecimalFormat("0.00");

	/** The view. */
	private final OrderStatsView view;

	/** The order data bussiness object. */
	private final OrderDataBo orderDataBo = SpringBoHelper.getOrderDataBo();

	/**
	 * Instantiates a new order stats presenter.
	 *
	 * @param orderStatsView
	 *            the view
	 */
	public OrderStatsPresenter(final OrderStatsView orderStatsView) {
		this.view = orderStatsView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flufflicks.marketjournal.portal.presenter.Presenter#bind()
	 */
	@Override
	public final void bind() {
		view.setCaption("");
		final List<OrderData> orders = orderDataBo.findAll();
		final Locale currentLocale = VaadinPortalUtil.getCurrentLocale();
		final ResourceBundle messages = ResourceBundle.getBundle("i18n", currentLocale);

		view.createPieChart(createOverviewDataset(orders), messages.getString("orderstats.chart.overview"));
		view.createPieChart(createWinLossDataset(orders), messages.getString("orderstats.chart.guvstats"));
		view.createLineChart(createCapitalDataset(orders), messages.getString("orderstats.chart.capital"));

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
	private DefaultCategoryDataset createCapitalDataset(final List<OrderData> orders) {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		float guv = 0.0f;
		
		for (int i = 0; i < orders.size(); i++) {
			final OrderData order = orders.get(i);
			guv+=order.getGuv();
			dataset.addValue(guv, "orders", String.valueOf(i));
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
		this.view.removeTabs();
	}

}
