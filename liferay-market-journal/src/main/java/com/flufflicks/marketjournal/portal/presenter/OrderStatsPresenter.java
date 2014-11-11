package com.flufflicks.marketjournal.portal.presenter;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.data.general.DefaultPieDataset;

import com.flufflicks.marketjournal.portal.ui.views.OrderStatsView;
import com.flufflicks.marketjournal.spring.bo.OrderDataBo;
import com.flufflicks.marketjournal.spring.bridge.SpringBoHelper;
import com.flufflicks.marketjournal.spring.model.OrderData;

public class OrderStatsPresenter implements Presenter {
	
	final DecimalFormat percentageFormat = new DecimalFormat("0.00");

	private final OrderStatsView view;

	private final OrderDataBo orderDataBo = SpringBoHelper.getOrderDataBo();

	public OrderStatsPresenter(final OrderStatsView view) {
		this.view = view;
	}

	@Override
	public void bind() {
		view.setCaption("");
		final List<OrderData> orders = orderDataBo.findAll();

		view.createPieChart(createOverviewDataset(orders), "Order Übersicht");
		view.createPieChart(createWinLossDataset(orders), "GUV Verhältnis");
	}

	/**
	 * Returns a dataset.
	 * 
	 * @return The dataset.
	 */
	private DefaultPieDataset createOverviewDataset(final List<OrderData> orders) {
		
		final int count = orders.size();
		final Map<String,Float> instrumentMap = getInstrumentMap(orders);
		final Object[] keys = instrumentMap.keySet().toArray();
		final DefaultPieDataset dataset = new DefaultPieDataset();

		for (final Object o : keys) {
			final String key = (String)o;
			final float percentage = instrumentMap.get(key)/count;
			dataset.setValue(key + "(" + percentageFormat.format(percentage) + ")", percentage);
			
		}
		return dataset;
	}
	
	/**
	 * Returns a dataset.
	 * 
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
		final String winrate = percentageFormat.format(win/count);
		percentageFormat.format(loss/count);
		dataset.setValue("Win (" + winrate + ")", win);
		dataset.setValue("Loss (" + winrate + ")",loss);

		return dataset;

	}

	private Map<String, Float> getInstrumentMap(final List<OrderData> orders) {
		final Map<String, Float> map = new HashMap<String, Float>();
		for (final OrderData orderData : orders) {
			final String instrument = orderData.getInstrument();
			final float f = map.get(instrument) != null ? map.get(instrument) : 0f;
			final float f1 = f + 1f;
			map.put(instrument, f1);
		}
		return map;
	}
	
	@Override
	public void unbind() {
		this.view.removeTabs();
	}

}
