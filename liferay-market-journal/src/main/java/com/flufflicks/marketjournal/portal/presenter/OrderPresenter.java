package com.flufflicks.marketjournal.portal.presenter;

import com.flufflicks.marketjournal.portal.applications.controller.OrderControllerUI;
import com.flufflicks.marketjournal.portal.ui.views.OrderView;
import com.flufflicks.marketjournal.portal.util.TextConverterUtil;
import com.flufflicks.marketjournal.spring.bo.OrderDataBo;
import com.flufflicks.marketjournal.spring.bridge.SpringBoHelper;
import com.flufflicks.marketjournal.spring.model.OrderData;
import com.vaadin.addon.ipcforliferay.LiferayIPC;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;

public class OrderPresenter implements Presenter, ClickListener, ValueChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final OrderView view;
	private final LiferayIPC liferayIpc = new LiferayIPC();
	private final OrderDataBo orderDataBo = SpringBoHelper.getOrderDataBo();

	public OrderPresenter(final OrderView view, final OrderControllerUI controller) {
		this.view = view;
		liferayIpc.extend(controller);
	}

	@Override
	public void bind() {
		view.getEventButton().addClickListener(this);
	}

	@Override
	public void unbind() {
		view.getEventButton().removeClickListener(this);
	}

	@Override
	public void buttonClick(final ClickEvent event) {
		if (event.getButton() == view.getEventButton()) {
			liferayIpc.sendEvent("theEventId", "This is Data");
			saveOrder();
		}
	}

	private void saveOrder() {
		this.view.getOpenPrice().setValidationVisible(false);

		final OrderData orderData = new OrderData();

		final boolean fieldsValid = validateOrderTextFields();
		if (fieldsValid) {
			final float openPrice = TextConverterUtil.getFloatValue(this.view.getOpenPrice().getValue());
			final float closePrice = TextConverterUtil.getFloatValue(this.view.getClosePrice().getValue());
			final int tp = TextConverterUtil.getIntValue(this.view.getTp().getValue());
			final int sl = TextConverterUtil.getIntValue(this.view.getSl().getValue());
			final int guv = TextConverterUtil.getIntValue(this.view.getGuv().getValue());
			//
			orderData.setOpenPrice(openPrice);
			orderData.setClosePrice(closePrice);
			orderData.setTp(tp);
			orderData.setSl(sl);
			orderData.setGuv(guv);
		}
		final boolean selectsValid = validateSelects();
		if (selectsValid) {
			final String instrument = (String) this.view.getSelectCurrency().getValue();
			final String orderType = (String) this.view.getOrderType().getValue();
			final String strategy = (String) this.view.getStrategy().getValue();

			orderData.setInstrument(instrument);
			orderData.setOrderType(orderType);
			orderData.setStrategy(strategy);
		}
		if (fieldsValid && selectsValid) {
			orderDataBo.save(orderData);
		}
	}

	private boolean validateSelects() {
		return validateSelect(this.view.getSelectCurrency()) && validateSelect(this.view.getOrderType()) && validateSelect(this.view.getStrategy());
	}

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

	private boolean validateOrderTextFields() {
		return validateTextField(this.view.getOpenPrice()) && validateTextField(this.view.getClosePrice()) && validateTextField(this.view.getSl());
	}

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

	@Override
	public void valueChange(final ValueChangeEvent event) {
		final String valueString = String.valueOf(event.getProperty().getValue());
		Notification.show("Value changed:", valueString, Type.TRAY_NOTIFICATION);

	}
}
