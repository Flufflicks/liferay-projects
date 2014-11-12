package com.flufflicks.marketjournal.portal.presenter;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

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
import com.vaadin.server.VaadinPortletService;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

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
		addPositions();
	}

	private void addPositions() {
		final PortletRequest req = VaadinPortletService.getCurrentPortletRequest();
		final PortletPreferences prefs = req.getPreferences();
		
		final String p = prefs.getValue("positions", "");
		final String[] positions = p.split(",");
		this.view.addPositions(positions);

		final String s = prefs.getValue("strategies", "");
		final String[] strategies = s.split(",");
		this.view.addStrategies(strategies);
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
		// this.view.getOpenPrice().setValidationVisible(false);
		final OrderData orderData = new OrderData();
		final boolean fieldsValid = view.validateOrderTextFields();
		if (fieldsValid) {
			final float openPrice = TextConverterUtil.getFloatValue(this.view.getOpenPrice());
			final float closePrice = TextConverterUtil.getFloatValue(this.view.getClosePrice());
			final int tp = TextConverterUtil.getIntValue(this.view.getTp());
			final int sl = TextConverterUtil.getIntValue(this.view.getSl());
			final int guv = TextConverterUtil.getIntValue(this.view.getGuv());
			//
			orderData.setOpenPrice(openPrice);
			orderData.setClosePrice(closePrice);
			orderData.setTp(tp);
			orderData.setSl(sl);
			orderData.setGuv(guv);
		}
		final boolean selectsValid = view.validateSelects();
		if (selectsValid) {
			final String instrument = this.view.getSelectCurrency();
			final String orderType = this.view.getOrderType();
			final String strategy = this.view.getStrategy();

			orderData.setInstrument(instrument);
			orderData.setOrderType(orderType);
			orderData.setStrategy(strategy);
		}

		final String order = this.view.getIdLabel().getValue();
		if (order != null && order.length() != 0) {
			orderData.setId(Long.valueOf(order));
		}
		if (fieldsValid && selectsValid) {
			orderDataBo.saveOrUpdate(orderData);
		}
	}

	private void setupIpc() {
		liferayipc.addLiferayIPCEventListener(IpcConstants.EVENT_LOAD_ORDER, new LiferayIPCEventListener() {
			@Override
			public void eventReceived(final LiferayIPCEvent event) {
				// Notification.show("Got event " + event.getEventId() +
				// " with data " + event.getData());
				final String orderIdString = event.getData();
				final long orderId = Long.valueOf(orderIdString);
				loadOrder(orderId);
			}

		});
	}

	private void loadOrder(final long orderId) {
		final OrderData orderData = orderDataBo.findById(orderId);
		this.view.getIdLabel().setValue(String.valueOf(orderId));
		this.view.setSelectCurrency(orderData.getInstrument());
		this.view.setOrderType(orderData.getOrderType());
		this.view.setStrategy(orderData.getStrategy());
		this.view.setOpenPrice(String.valueOf(orderData.getOpenPrice()));
		this.view.setClosePrice(String.valueOf(orderData.getClosePrice()));
		this.view.setSl(String.valueOf(orderData.getSl()));
		this.view.setTp(String.valueOf(orderData.getTp()));
		this.view.setGuv(String.valueOf(orderData.getGuv()));
	}

}
