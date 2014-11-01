package com.flufflicks.marketjournal.portal.presenter;

import com.flufflicks.marketjournal.portal.applications.controller.OrderControllerUI;
import com.flufflicks.marketjournal.portal.ui.views.OrderView;
import com.flufflicks.marketjournal.portal.util.IpcConstants;
import com.flufflicks.marketjournal.portal.util.TextConverterUtil;
import com.flufflicks.marketjournal.spring.bo.OrderDataBo;
import com.flufflicks.marketjournal.spring.bridge.SpringBoHelper;
import com.flufflicks.marketjournal.spring.model.OrderData;
import com.vaadin.addon.ipcforliferay.LiferayIPC;
import com.vaadin.addon.ipcforliferay.event.LiferayIPCEvent;
import com.vaadin.addon.ipcforliferay.event.LiferayIPCEventListener;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;

public class OrderPresenter implements Presenter, ClickListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final OrderView view;
	private final LiferayIPC liferayipc = new LiferayIPC();
	private final OrderDataBo orderDataBo = SpringBoHelper.getOrderDataBo();

	public OrderPresenter(final OrderView view, final OrderControllerUI controller) {
		this.view = view;
		liferayipc.extend(controller);
	}

	@Override
	public void bind() {
		setupIpc();
		view.getEventButton().addClickListener(this);
	}

	@Override
	public void unbind() {
		view.getEventButton().removeClickListener(this);
	}

	@Override
	public void buttonClick(final ClickEvent event) {
		if (event.getButton() == view.getEventButton()) {
			liferayipc.sendEvent(IpcConstants.EVENT_RELOAD_ORDERS, null);
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
		
		final String order = this.view.getIdLabel().getValue();
		if (order != null) {
			orderData.setId(Long.valueOf(order));
		}
		if (fieldsValid && selectsValid) {
			orderDataBo.saveOrUpdate(orderData);
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

	private void setupIpc() {
		liferayipc.addLiferayIPCEventListener(IpcConstants.EVENT_LOAD_ORDER, new LiferayIPCEventListener() {
			@Override
			public void eventReceived(final LiferayIPCEvent event) {
//				Notification.show("Got event " + event.getEventId() + " with data " + event.getData());
				final String orderIdString = event.getData();
				final long orderId = Long.valueOf(orderIdString);
				loadOrder(orderId);
			}

		});
	}

	private void loadOrder(final long orderId) {
		final OrderData orderData = orderDataBo.findById(orderId);
		this.view.getIdLabel().setValue(String.valueOf(orderId));
		this.view.getSelectCurrency().setValue(orderData.getInstrument());
		this.view.getOrderType().setValue(orderData.getOrderType());
		this.view.getStrategy().setValue(orderData.getStrategy());
		this.view.getOpenPrice().setValue(String.valueOf(orderData.getOpenPrice()));
		this.view.getClosePrice().setValue(String.valueOf(orderData.getClosePrice()));
		this.view.getSl().setValue(String.valueOf(orderData.getSl()));
		this.view.getTp().setValue(String.valueOf(orderData.getTp()));
		this.view.getGuv().setValue(String.valueOf(orderData.getGuv()));
	}

}
