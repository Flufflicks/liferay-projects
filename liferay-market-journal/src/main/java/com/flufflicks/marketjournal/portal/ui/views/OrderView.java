package com.flufflicks.marketjournal.portal.ui.views;

import com.vaadin.data.util.converter.StringToIntegerConverter;
import com.vaadin.data.util.converter.StringToLongConverter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

/** A start view for navigating to the main view */
public class OrderView extends VerticalLayout implements View {

	private static final long serialVersionUID = -3398565663865641952L;
	private final VerticalLayout mainLayout0 = new VerticalLayout();

	private final NativeSelect selectCurrency = new NativeSelect("Währungspaar");
	private final NativeSelect orderType = new NativeSelect("Order Typ");
	private final NativeSelect strategy = new NativeSelect("Strategie");

	private final TextField openPrice = new TextField("Open Preis");
	private final TextField closePrice = new TextField("Close Preis");
	private final TextField sl = new TextField("Stop Loss");
	private final TextField tp = new TextField("Take Profit");

	private final TextField guv = new TextField("GUV");



	private final Button eventButton = new Button("Hinzufügen");

	public OrderView() {
		setupView();
	}

	private void setupView() {
		setupLayout();

		final VerticalSplitPanel sample = new VerticalSplitPanel();
        sample.setSizeFull();
        sample.setSplitPosition(150, Unit.PIXELS);
 
		
		// Währungsdropdown
		selectCurrency.addItem("EUR/USD");
		selectCurrency.addItem("USD/JPY");
		selectCurrency.addItem("EUR/JPY");
		selectCurrency.addItem("NZD/USD");
		selectCurrency.addItem("USD/CAD");
		selectCurrency.setNullSelectionAllowed(false);
		selectCurrency.setValue(1);
		selectCurrency.setImmediate(true);

		orderType.addItem("BUY");
		orderType.addItem("SELL");
		orderType.addItem("BUY Entry");
		orderType.addItem("SELL Entry");
		orderType.setNullSelectionAllowed(false);
		orderType.setValue(1);
		orderType.setImmediate(true);

		openPrice.setImmediate(true);
		openPrice.setInputPrompt("...");
		openPrice.setConverter(new StringToLongConverter());
		openPrice.setValidationVisible(true);
		openPrice.setMaxLength(10);

		closePrice.setImmediate(true);
		closePrice.setInputPrompt("...");
		closePrice.setConverter(new StringToLongConverter());
		closePrice.setValidationVisible(true);
		closePrice.setMaxLength(10);
		
		tp.setImmediate(true);
		tp.setInputPrompt("...");
		tp.setConverter(new StringToIntegerConverter());
		tp.setValidationVisible(true);
		tp.setMaxLength(10);
		
		sl.setImmediate(true);
		sl.setInputPrompt("...");
		sl.setConverter(new StringToIntegerConverter());
		sl.setValidationVisible(true);
		sl.setMaxLength(10);
		
		guv.setImmediate(true);
		guv.setInputPrompt("...");
		guv.setConverter(new StringToLongConverter());
		guv.setValidationVisible(true);
		guv.setMaxLength(10);
		
		strategy.addItem("Flaggenausbruch");
		strategy.addItem("EMATrend");
		strategy.addItem("Wiederstandsausbruch");
		strategy.addItem("London Breakout");
		strategy.setNullSelectionAllowed(false);
		strategy.setValue(1);
		strategy.setImmediate(true);

		mainLayout0.addComponent(selectCurrency);
		mainLayout0.addComponent(orderType);
		mainLayout0.addComponent(strategy);

		mainLayout0.addComponent(openPrice);
		mainLayout0.addComponent(closePrice);
		mainLayout0.addComponent(sl);
		mainLayout0.addComponent(tp);
		mainLayout0.addComponent(guv);

		
		mainLayout0.addComponent(eventButton);
	}

	private void setupLayout() {
		addComponent(mainLayout0);
	}

	@Override
	public void enter(final ViewChangeEvent event) {
		Notification.show("ProductView");
	}

	public Button getEventButton() {
		return eventButton;
	}

	public NativeSelect getSelectCurrency() {
		return selectCurrency;
	}

	public VerticalLayout getMainLayout0() {
		return mainLayout0;
	}

	public NativeSelect getOrderType() {
		return orderType;
	}

	public NativeSelect getStrategy() {
		return strategy;
	}

	public TextField getOpenPrice() {
		return openPrice;
	}

	public TextField getClosePrice() {
		return closePrice;
	}

	public TextField getSl() {
		return sl;
	}

	public TextField getTp() {
		return tp;
	}

	public TextField getGuv() {
		return guv;
	}
}
