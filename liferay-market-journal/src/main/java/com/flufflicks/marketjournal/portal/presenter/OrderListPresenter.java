package com.flufflicks.marketjournal.portal.presenter;

import java.util.List;

import com.flufflicks.marketjournal.portal.applications.controller.ProductReceiverControllerUI;
import com.flufflicks.marketjournal.portal.ui.views.OrderListView;
import com.flufflicks.marketjournal.spring.bo.OrderDataBo;
import com.flufflicks.marketjournal.spring.bridge.SpringBoHelper;
import com.flufflicks.marketjournal.spring.model.OrderData;
import com.vaadin.addon.ipcforliferay.LiferayIPC;
import com.vaadin.addon.ipcforliferay.event.LiferayIPCEvent;
import com.vaadin.addon.ipcforliferay.event.LiferayIPCEventListener;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Notification;

public class OrderListPresenter implements Presenter {
	private final OrderListView view;

	final LiferayIPC liferayipc = new LiferayIPC();

	static final String ORDER_TYPE = "orderType";
	
	static final String STRATEGY = "strategy";
	
	static final String OPEN_PRICE = "openPrice";

	static final String CLOSE_PRICE = "closePrice";
	
	static final String GUV = "guv";


	
	private final OrderDataBo orderDataBo = SpringBoHelper.getOrderDataBo();

	public OrderListPresenter(final OrderListView view, final ProductReceiverControllerUI controller) {
		liferayipc.extend(controller);
		this.view = view;
	}

	@Override
	public void bind() {
		setupIpc();
		setupIndexContainer();
		setupOrderTable();
	}

	private void setupIndexContainer() {
		final IndexedContainer ic = new IndexedContainer();
		ic.addContainerProperty(ORDER_TYPE, String.class, "");
		ic.addContainerProperty(STRATEGY, String.class, "");
		ic.addContainerProperty(OPEN_PRICE, Long.class, "");
		ic.addContainerProperty(CLOSE_PRICE, Long.class, "");
		ic.addContainerProperty(GUV, Long.class, "");

		final List<OrderData> orderList = orderDataBo.findAll();
		for (final OrderData orderData : orderList) {
			final Object id = ic.addItem();
			ic.getContainerProperty(id, ORDER_TYPE).setValue(orderData.getOrderType());
			ic.getContainerProperty(id, STRATEGY).setValue(orderData.getStrategy());
			ic.getContainerProperty(id, OPEN_PRICE).setValue(orderData.getOpenPrice());
			ic.getContainerProperty(id, CLOSE_PRICE).setValue(orderData.getClosePrice());
			ic.getContainerProperty(id, GUV).setValue(orderData.getGuv());
		}

		this.view.setOrderContainer(ic);
	}
	
	private void setupOrderTable() {
		view.getOrderTable().setContainerDataSource(view.getOrderContainer());
		view.getOrderTable().setVisibleColumns(new String[] { ORDER_TYPE, STRATEGY, OPEN_PRICE, CLOSE_PRICE, GUV });
		view.getOrderTable().setSelectable(true);
		view.getOrderTable().setImmediate(true);

		view.getOrderTable().addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final ValueChangeEvent event) {
				final Object orderId = view.getOrderTable().getValue();
				if (orderId != null) {
					// TODO
				}

			}
		});
	}

	private void setupIpc() {
		liferayipc.addLiferayIPCEventListener("theEventId", new LiferayIPCEventListener() {
			@Override
			public void eventReceived(final LiferayIPCEvent event) {
				Notification.show("Got event " + event.getEventId() + " with data " + event.getData());
				setText(event.getData());
			}
		});
	}

	@Override
	public void unbind() {
	}

	public void setText(final String text) {
		view.getLabel().setValue(text);
	}

}
