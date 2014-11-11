package com.flufflicks.marketjournal.portal.ui.views;

import java.util.Locale;
import java.util.ResourceBundle;

import com.flufflicks.marketjournal.portal.util.VaadinPortalUtil;
import com.flufflicks.marketjournal.portal.validator.FloatValidator;
import com.flufflicks.marketjournal.portal.validator.IntValidator;
import com.flufflicks.marketjournal.spring.model.OrderData;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

/** A start view for navigating to the main view */
public class OrderView extends VerticalLayout implements View {

	private static final long serialVersionUID = -3398565663865641952L;
	private final VerticalLayout mainLayout0 = new VerticalLayout();

	private final Label idLabel = new Label();

	private final NativeSelect selectCurrency = new NativeSelect("Währungspaar");
	private final NativeSelect orderType = new NativeSelect("Order Typ");
	private final NativeSelect strategy = new NativeSelect("Strategie");

	private final TextField openPrice = new TextField("Open Preis");
	private final TextField closePrice = new TextField("Close Preis");
	private final TextField sl = new TextField("Stop Loss");
	private final TextField tp = new TextField("Take Profit");

	private final TextField guv = new TextField("GUV");

	private final Button saveButton = new Button();

	final BeanFieldGroup<OrderData> beanFieldGroup = new BeanFieldGroup<OrderData>(OrderData.class);

	public OrderView() {
		setupView();
	}

	private void setupView() {
		final Locale currentLocale = VaadinPortalUtil.getCurrentLocale();
		final ResourceBundle messages = ResourceBundle.getBundle("i18n", currentLocale);
		
		setupLayout();
		final VerticalSplitPanel sample = new VerticalSplitPanel();
		sample.setSizeFull();
		sample.setSplitPosition(150, Unit.PIXELS);

		// add positions from property
		selectCurrency.setNullSelectionAllowed(false);
		selectCurrency.setRequired(true);
		selectCurrency.setDescription(messages.getString("orderview.position.description"));
		selectCurrency.setRequiredError(messages.getString("orderview.position.error"));
		selectCurrency.setImmediate(true);

		orderType.addItem(messages.getString("orderview.ordertype.buy"));
		orderType.addItem(messages.getString("orderview.ordertype.sell"));
		orderType.addItem(messages.getString("orderview.ordertype.buyentry"));
		orderType.addItem(messages.getString("orderview.ordertype.sellentry"));
		orderType.setNullSelectionAllowed(false);
		orderType.setRequired(true);
		orderType.setDescription(messages.getString("orderview.ordertype.description"));
		orderType.setRequiredError(messages.getString("orderview.ordertype.error"));
		orderType.setImmediate(true);

		strategy.setNullSelectionAllowed(false);
		strategy.setRequired(true);
		strategy.setDescription(messages.getString("orderview.strategy.description"));
		strategy.setRequiredError(messages.getString("orderview.strategy.error"));
		strategy.setImmediate(true);

		openPrice.setImmediate(true);
		openPrice.setInputPrompt(messages.getString("label.placeholder"));
		openPrice.addValidator(new FloatValidator());
		openPrice.setRequired(true);
		openPrice.setDescription(messages.getString("orderview.openprice.description"));
		openPrice.setRequiredError(messages.getString("orderview.openprice.error"));
		openPrice.setValidationVisible(true);
		openPrice.setMaxLength(10);

		closePrice.setImmediate(true);
		closePrice.setInputPrompt(messages.getString("label.placeholder"));
		closePrice.addValidator(new FloatValidator());
		closePrice.setDescription(messages.getString("orderview.closeprice.description"));
		closePrice.setValidationVisible(true);
		closePrice.setMaxLength(10);

		tp.setImmediate(true);
		tp.setInputPrompt(messages.getString("label.placeholder"));
		tp.addValidator(new IntValidator());
		tp.setValidationVisible(true);
		tp.setRequired(true);
		tp.setDescription(messages.getString("orderview.tp.description"));
		tp.setRequiredError(messages.getString("orderview.tp.error"));
		tp.setMaxLength(10);

		sl.setImmediate(true);
		sl.setInputPrompt(messages.getString("label.placeholder"));
		sl.addValidator(new IntValidator());
		sl.setValidationVisible(true);
		sl.setRequired(true);
		sl.setDescription("StopLoss in Pips");
		sl.setRequiredError("Bitte tragen Sie einen Wert ein!");
		sl.setMaxLength(10);

		guv.setImmediate(true);
		guv.setInputPrompt(messages.getString("label.placeholder"));
		guv.addValidator(new IntValidator());
		guv.setValidationVisible(true);
		guv.setDescription(messages.getString("orderview.guv.description"));
		guv.setMaxLength(10);

		saveButton.setCaption(messages.getString("label.save"));

		mainLayout0.addComponent(idLabel);
		mainLayout0.addComponent(selectCurrency);
		mainLayout0.addComponent(orderType);
		mainLayout0.addComponent(strategy);
		mainLayout0.addComponent(openPrice);
		mainLayout0.addComponent(closePrice);
		mainLayout0.addComponent(sl);
		mainLayout0.addComponent(tp);
		mainLayout0.addComponent(guv);

		new BeanFieldGroup<OrderData>(OrderData.class);

		mainLayout0.addComponent(saveButton);
	}

	private void setupLayout() {
		addComponent(mainLayout0);
	}

	@Override
	public void enter(final ViewChangeEvent event) {
		Notification.show("ProductView");
	}

	public Button getEventButton() {
		return saveButton;
	}

	public Label getIdLabel() {
		return idLabel;
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
