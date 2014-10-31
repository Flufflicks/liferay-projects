package com.flufflicks.marketjournal.portal.ui.views;

import com.flufflicks.marketjournal.portal.validator.FloatValidator;
import com.flufflicks.marketjournal.portal.validator.IntValidator;
import com.flufflicks.marketjournal.spring.model.OrderData;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
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
	
	final BeanFieldGroup<OrderData> beanFieldGroup = new BeanFieldGroup<OrderData>(OrderData.class);


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
		selectCurrency.setRequired(true);
		selectCurrency.setDescription("Position");
		selectCurrency.setRequiredError("Bitte wählen Sie eine Position!");
		selectCurrency.setImmediate(true);

		orderType.addItem("BUY");
		orderType.addItem("SELL");
		orderType.addItem("BUY Entry");
		orderType.addItem("SELL Entry");
		orderType.setNullSelectionAllowed(false);
		orderType.setRequired(true);
		orderType.setDescription("Ordertyp");
		orderType.setRequiredError("Bitte wählen Sie eine Ordertyp!");
		orderType.setImmediate(true);

		strategy.addItem("Flaggenausbruch");
		strategy.addItem("EMATrend");
		strategy.addItem("Wiederstandsausbruch");
		strategy.addItem("London Breakout");
		strategy.setNullSelectionAllowed(false);
		strategy.setRequired(true);
		strategy.setDescription("Strategie des Trades");
		strategy.setRequiredError("Bitte wählen Sie eine Strategie!");
		strategy.setImmediate(true);		
		
		openPrice.setImmediate(true);
		openPrice.setInputPrompt("...");
		openPrice.addValidator(new FloatValidator());
		openPrice.setRequired(true);
		openPrice.setDescription("Eröffnungspreis");
		openPrice.setRequiredError("Bitte tragen Sie einen Wert ein!");
		openPrice.setValidationVisible(true);
		openPrice.setMaxLength(10);

		closePrice.setImmediate(true);
		closePrice.setInputPrompt("...");
		closePrice.addValidator(new FloatValidator());
		closePrice.setDescription("Schlußpreis");
		closePrice.setValidationVisible(true);
		closePrice.setMaxLength(10);

		tp.setImmediate(true);
		tp.setInputPrompt("...");
		tp.addValidator(new IntValidator());
		tp.setValidationVisible(true);
		tp.setRequired(true);
		tp.setDescription("TakeProfit in Pips");
		tp.setRequiredError("Bitte tragen Sie einen Wert ein!");
		tp.setMaxLength(10);

		sl.setImmediate(true);
		sl.setInputPrompt("...");
		sl.addValidator(new IntValidator());
		sl.setValidationVisible(true);
		sl.setRequired(true);
		sl.setDescription("StopLoss in Pips");
		sl.setRequiredError("Bitte tragen Sie einen Wert ein!");
		sl.setMaxLength(10);

		guv.setImmediate(true);
		guv.setInputPrompt("...");
		guv.addValidator(new IntValidator());
		guv.setValidationVisible(true);
		guv.setDescription("Gewin/Verlust in Pips");
		guv.setMaxLength(10);
		
		mainLayout0.addComponent(selectCurrency);
		mainLayout0.addComponent(orderType);
		mainLayout0.addComponent(strategy);
		mainLayout0.addComponent(openPrice);
		mainLayout0.addComponent(closePrice);
		mainLayout0.addComponent(sl);
		mainLayout0.addComponent(tp);
		mainLayout0.addComponent(guv);

		new BeanFieldGroup<OrderData>(OrderData.class);

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
