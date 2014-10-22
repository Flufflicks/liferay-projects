package com.flufflicks.marketjournal.portal.presenter;

import com.flufflicks.marketjournal.portal.applications.controller.OrderControllerUI;
import com.flufflicks.marketjournal.portal.ui.views.OrderView;
import com.flufflicks.marketjournal.spring.bo.OrderDataBo;
import com.flufflicks.marketjournal.spring.bridge.SpringBoHelper;
import com.flufflicks.marketjournal.spring.model.OrderData;
import com.vaadin.addon.ipcforliferay.LiferayIPC;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

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
	
	private void saveOrder(){
		final String instrument = (String) this.view.getSelectCurrency().getValue();
		final String orderType = (String) this.view.getOrderType().getValue();
		final long openPrice = (Long) this.view.getOpenPrice().getConvertedValue();
		final long closePrice = (Long) this.view.getClosePrice().getConvertedValue();
		final int tp = (Integer) this.view.getTp().getConvertedValue();
		final int sl = (Integer) this.view.getSl().getConvertedValue();
		final long guv = (Long) this.view.getGuv().getConvertedValue();
		this.view.getStrategy().getValue();
		
		final OrderData orderData = new OrderData();
		orderData.setInstrument(instrument);
		orderData.setOrderType(orderType);
		orderData.setOpenPrice(openPrice);
		orderData.setClosePrice(closePrice);
		orderData.setTp(tp);
		orderData.setSl(sl);
		orderData.setGuv(guv);
		
		orderDataBo.save(orderData);
	}

	@Override
	public void valueChange(final ValueChangeEvent event) {
		final String valueString = String.valueOf(event.getProperty().getValue());
		Notification.show("Value changed:", valueString, Type.TRAY_NOTIFICATION);

	}
}
