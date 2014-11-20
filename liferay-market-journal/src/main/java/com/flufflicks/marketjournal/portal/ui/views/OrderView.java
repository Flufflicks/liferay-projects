package com.flufflicks.marketjournal.portal.ui.views;

import java.util.Locale;
import java.util.ResourceBundle;

import com.flufflicks.marketjournal.portal.util.VaadinPortalUtil;
import com.flufflicks.marketjournal.portal.validator.FloatValidator;
import com.flufflicks.marketjournal.portal.validator.IntValidator;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *  The OrderView class for create and edit orders.
 */
public class OrderView extends VerticalLayout implements View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3398565663865641952L;
	
	/** The Constant SAVE_BTN_ID to ident the button in clicklistener method. */
	public static final String SAVE_BTN_ID = "OV_BTN_SAVE";
	
	/** The Constant SAVE_BTN_ID to ident the button in clicklistener method. */
	public static final String DELETE_BTN_ID = "OV_BTN_DELTE";

	/** The Constant textfield max length. */
	private static final int TF_MAX_LENGTH = 10;
	
	/** The main layout. */
	private final VerticalLayout mainLayout0 = new VerticalLayout();

	/** The order id to link the order to databaseobject. */
	private long orderId;

	/** The select field for the instrument. */
	private final NativeSelect selectCurrency = new NativeSelect();
	
	/** The select field for the order type. */
	private final NativeSelect orderType = new NativeSelect();
	
	/** The  select field for the strategy. */
	private final NativeSelect strategy = new NativeSelect();

	/** The open price textfield. */
	private final TextField openPrice = new TextField();
	
	/** The close price textfield. */
	private final TextField closePrice = new TextField();
	
	/** The sl textfield. */
	private final TextField sl = new TextField();
	
	/** The tp textfield. */
	private final TextField tp = new TextField();

	/** The guv textfield. */
	private final TextField guv = new TextField();
	
	/** Button Layout *> */
	private final HorizontalLayout buttonLayout = new HorizontalLayout();

	/** The save button. */
	private final Button saveButton = new Button();
	
	/** The delete button. */
	private final Button deleteButton = new Button();

	/**
	 * Instantiates a new order view.
	 */
	public OrderView() {
		setupView();
	}

	/**
	 * Setup the view.
	 */
	private void setupView() {
		final Locale currentLocale = VaadinPortalUtil.getCurrentLocale();
		final ResourceBundle messages = ResourceBundle.getBundle("i18n", currentLocale);
		
		setupLayout();		

		// add positions from property
		selectCurrency.setNullSelectionAllowed(false);
		selectCurrency.setRequired(true);
		selectCurrency.setCaption(messages.getString("orderview.position.description"));
		selectCurrency.setRequiredError(messages.getString("orderview.position.error"));
		selectCurrency.setImmediate(true);

		orderType.addItem(messages.getString("orderview.ordertype.buy"));
		orderType.addItem(messages.getString("orderview.ordertype.sell"));
		orderType.addItem(messages.getString("orderview.ordertype.buyentry"));
		orderType.addItem(messages.getString("orderview.ordertype.sellentry"));
		orderType.setNullSelectionAllowed(false);
		orderType.setRequired(true);
		orderType.setCaption(messages.getString("orderview.ordertype.description"));
		orderType.setRequiredError(messages.getString("orderview.ordertype.error"));
		orderType.setImmediate(true);

		strategy.setNullSelectionAllowed(false);
		strategy.setRequired(true);
		strategy.setCaption(messages.getString("orderview.strategy.description"));
		strategy.setRequiredError(messages.getString("orderview.strategy.error"));
		strategy.setImmediate(true);

		openPrice.setImmediate(true);
		openPrice.setInputPrompt(messages.getString("label.placeholder"));
		openPrice.addValidator(new FloatValidator());
		openPrice.setRequired(true);
		openPrice.setCaption(messages.getString("orderview.openprice.description"));
		openPrice.setRequiredError(messages.getString("orderview.openprice.error"));
		openPrice.setValidationVisible(true);
		openPrice.setMaxLength(TF_MAX_LENGTH);

		closePrice.setImmediate(true);
		closePrice.setInputPrompt(messages.getString("label.placeholder"));
		closePrice.addValidator(new FloatValidator());
		closePrice.setCaption(messages.getString("orderview.closeprice.description"));
		closePrice.setValidationVisible(true);
		closePrice.setMaxLength(TF_MAX_LENGTH);

		tp.setImmediate(true);
		tp.setInputPrompt(messages.getString("label.placeholder"));
		tp.addValidator(new IntValidator());
		tp.setValidationVisible(true);
		tp.setRequired(true);
		tp.setCaption(messages.getString("orderview.tp.description"));
		tp.setRequiredError(messages.getString("orderview.tp.error"));
		tp.setMaxLength(TF_MAX_LENGTH);

		sl.setImmediate(true);
		sl.setInputPrompt(messages.getString("label.placeholder"));
		sl.addValidator(new IntValidator());
		sl.setValidationVisible(true);
		sl.setRequired(true);
		sl.setCaption(messages.getString("orderview.sl.description"));
		sl.setRequiredError(messages.getString("orderview.sl.error"));
		sl.setMaxLength(TF_MAX_LENGTH);

		guv.setImmediate(true);
		guv.setInputPrompt(messages.getString("label.placeholder"));
		guv.addValidator(new IntValidator());
		guv.setValidationVisible(true);
		guv.setCaption(messages.getString("orderview.guv.description"));
		guv.setMaxLength(TF_MAX_LENGTH);

		saveButton.setCaption(messages.getString("label.save"));
		saveButton.setId(SAVE_BTN_ID);
		
//		deleteButton.setIcon(FontAwesome.ENVELOPE);
		deleteButton.setCaption(messages.getString("label.delete"));
		deleteButton.setId(DELETE_BTN_ID);
		
		mainLayout0.addComponent(selectCurrency);
		mainLayout0.addComponent(orderType);
		mainLayout0.addComponent(strategy);
		mainLayout0.addComponent(openPrice);
		mainLayout0.addComponent(closePrice);
		mainLayout0.addComponent(sl);
		mainLayout0.addComponent(tp);
		mainLayout0.addComponent(guv);
		mainLayout0.addComponent(buttonLayout);
		buttonLayout.addComponent(saveButton);
		buttonLayout.addComponent(deleteButton);
	}

	/**
	 * Setup the layout.
	 */
	private void setupLayout() {
		addComponent(mainLayout0);
	}

	/* (non-Javadoc)
	 * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	public final void enter(final ViewChangeEvent event) {
		Notification.show("ProductView");
	}
	
	/**
	 * Adds the positions to the select.
	 *
	 * @param positions the positions
	 */
	public final void addPositions(final String[] positions) {
		for (final String position : positions) {
			this.selectCurrency.addItem(position);
		}		
	}
	
	/**
	 * Adds the strategies to the select.
	 *
	 * @param strategies the strategies
	 */
	public final void addStrategies(final String[] strategies) {
		for (final String str : strategies) {
			this.strategy.addItem(str);
		}
	}		

	/**
	 * Validate order text fields.
	 *
	 * @return true, if successful
	 */
	public final boolean validateOrderTextFields() {
		return validateTextField(openPrice) && validateTextField(closePrice) && validateTextField(sl);
	}
	
	/**
	 * Validate a text field and add the error indicator.
	 *
	 * @param textField the text field
	 * @return true, if successful
	 */
	private boolean validateTextField(final TextField textField) {
		try {
			textField.validate();
			textField.setComponentError(null);
		} catch (final EmptyValueException e) {
			// do nothing
			return false;
		} catch (final InvalidValueException e) {

			textField.setComponentError(new UserError(e.getMessage()));
			return false;
		}
		return true;
	}
	
	/**
	 * Validate the select fields.
	 *
	 * @return true, if successful
	 */
	public final boolean validateSelects() {
		return validateSelect(selectCurrency) && validateSelect(orderType) && validateSelect(strategy);
	}

	/**
	 * Validate a select field and add the error indicator.
	 *
	 * @param select the select
	 * @return true, if successful
	 */
	private boolean validateSelect(final NativeSelect select) {
		try {
			select.validate();
			select.setComponentError(null);
		} catch (final EmptyValueException e) {
			// do nothing
			return false;
		} catch (final InvalidValueException e) {

			select.setComponentError(new UserError(e.getMessage()));
			return false;
		}
		return true;
	}

	/**
	 * Gets the selected currency.
	 *
	 * @return the selected currency
	 */
	public final String getSelectedCurrency() {
		return (String) selectCurrency.getValue();
	}

	/**
	 * Gets the order type.
	 *
	 * @return the order type
	 */
	public final String getOrderType() {
		return (String) orderType.getValue();
	}

	/**
	 * Gets the strategy.
	 *
	 * @return the strategy
	 */
	public final String getStrategy() {
		return (String) strategy.getValue();
	}

	/**
	 * Gets the open price.
	 *
	 * @return the open price
	 */
	public final String getOpenPrice() {
		return openPrice.getValue();
	}

	/**
	 * Gets the close price.
	 *
	 * @return the close price
	 */
	public final String getClosePrice() {
		return closePrice.getValue();
	}

	/**
	 * Gets the sl.
	 *
	 * @return the sl
	 */
	public final String getSl() {
		return sl.getValue();
	}

	/**
	 * Gets the tp.
	 *
	 * @return the tp
	 */
	public final String getTp() {
		return tp.getValue();
	}

	/**
	 * Gets the guv.
	 *
	 * @return the guv
	 */
	public final String getGuv() {
		return guv.getValue();
	}
	
	/**
	 * Gets the order id.
	 *
	 * @return the order id
	 */
	public final long getOrderId() {
		return orderId;
	}
		
	
	/**
	 * Sets the select currency.
	 *
	 * @param value the new select currency
	 */
	public final void setSelectCurrency(final String value) {
		selectCurrency.setValue(value);
	}

	/**
	 * Sets the order type.
	 *
	 * @param value the new order type
	 */
	public final void setOrderType(final String value) {
		orderType.setValue(value);
	}

	/**
	 * Sets the strategy.
	 *
	 * @param value the new strategy
	 */
	public final void setStrategy(final String value) {
		strategy.setValue(value);
	}

	/**
	 * Sets the open price.
	 *
	 * @param value the new open price
	 */
	public final void setOpenPrice(final String value) {
		openPrice.setValue(value);
	}

	/**
	 * Sets the close price.
	 *
	 * @param value the new close price
	 */
	public final void setClosePrice(final String value) {
		closePrice.setValue(value);
	}

	/**
	 * Sets the sl.
	 *
	 * @param value the new sl
	 */
	public final void setSl(final String value) {
		sl.setValue(value);
	}

	/**
	 * Sets the tp.
	 *
	 * @param value the new tp
	 */
	public final void setTp(final String value) {
		tp.setValue(value);
	}

	/**
	 * Sets the guv.
	 *
	 * @param value the new guv
	 */
	public final void setGuv(final String value) {
		guv.setValue(value);
	}

	/**
	 * Sets the order id.
	 *
	 * @param value the new order id
	 */
	public final void setOrderId(final long value) {
		this.orderId = value;
	}

	/**
	 * Adds the click listener (the presenter).
	 *
	 * @param listener the listener
	 */
	public final void addClickListener(final ClickListener listener) {
		saveButton.addClickListener(listener);
		deleteButton.addClickListener(listener);
	}
	
	/**
	 * Removes the click listener.
	 *
	 * @param listener the listener
	 */
	public final void removeClickListener(final ClickListener listener) {
		saveButton.removeClickListener(listener);
		deleteButton.removeClickListener(listener);
	}

	public void reset() {
		this.orderId = 0L;
		setStrategy(null);
		setOrderType(null);
		setOpenPrice(null);
		setSelectCurrency(null);
		setClosePrice(null);
		setTp(null);
		setSl(null);
		setGuv(null);
	}
	
}
