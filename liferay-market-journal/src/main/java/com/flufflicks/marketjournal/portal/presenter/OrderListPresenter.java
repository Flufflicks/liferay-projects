package com.flufflicks.marketjournal.portal.presenter;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.flufflicks.marketjournal.portal.applications.controller.OrderListControllerUI;
import com.flufflicks.marketjournal.portal.ui.views.OrderListView;
import com.flufflicks.marketjournal.portal.util.IpcConstants;
import com.flufflicks.marketjournal.portal.util.VaadinPortalUtil;
import com.flufflicks.marketjournal.spring.bo.OrderDataBo;
import com.flufflicks.marketjournal.spring.bridge.SpringBoHelper;
import com.flufflicks.marketjournal.spring.model.OrderData;
import com.vaadin.addon.ipcforliferay.LiferayIPC;
import com.vaadin.addon.ipcforliferay.event.LiferayIPCEvent;
import com.vaadin.addon.ipcforliferay.event.LiferayIPCEventListener;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;

public class OrderListPresenter implements Presenter {
	private final OrderListView view;

	final LiferayIPC liferayipc = new LiferayIPC();

	static final String ORDER_TYPE = "orderlist.ordertype";

	static final String STRATEGY = "orderlist.strategy";

	static final String OPEN_PRICE = "orderlist.openPrice";

	static final String CLOSE_PRICE = "orderlist.closePrice";

	static final String GUV = "orderlist.guv";

	private final OrderDataBo orderDataBo = SpringBoHelper.getOrderDataBo();

	public OrderListPresenter(final OrderListView view, final OrderListControllerUI controller) {
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
		ic.addContainerProperty(OPEN_PRICE, Float.class, "");
		ic.addContainerProperty(CLOSE_PRICE, Float.class, "");
		ic.addContainerProperty(GUV, Integer.class, "");
		loadData(ic);
		view.getOrderTable().setContainerDataSource(ic);

	}

	@SuppressWarnings("unchecked")
	private void loadData(final IndexedContainer ic) {
		ic.removeAllItems();

		final List<OrderData> orderList = orderDataBo.findAll();
		for (final OrderData orderData : orderList) {
			ic.addItem(orderData.getId());
			ic.getContainerProperty(orderData.getId(), ORDER_TYPE).setValue(orderData.getOrderType());
			ic.getContainerProperty(orderData.getId(), STRATEGY).setValue(orderData.getStrategy());
			ic.getContainerProperty(orderData.getId(), OPEN_PRICE).setValue(orderData.getOpenPrice());
			ic.getContainerProperty(orderData.getId(), CLOSE_PRICE).setValue(orderData.getClosePrice());
			ic.getContainerProperty(orderData.getId(), GUV).setValue(orderData.getGuv());
		}
	}

	private void setupOrderTable() {
		final Locale currentLocale = VaadinPortalUtil.getCurrentLocale();
		final ResourceBundle messages = ResourceBundle.getBundle("i18n", currentLocale);
		
		view.getOrderTable().setVisibleColumns(new String[] { ORDER_TYPE, STRATEGY, OPEN_PRICE, CLOSE_PRICE, GUV });
		view.getOrderTable().setSelectable(true);		
		view.getOrderTable().setColumnHeader(ORDER_TYPE, messages.getString(ORDER_TYPE));
		view.getOrderTable().setColumnHeader(STRATEGY, messages.getString(STRATEGY));
		view.getOrderTable().setColumnHeader(OPEN_PRICE, messages.getString(OPEN_PRICE));
		view.getOrderTable().setColumnHeader(CLOSE_PRICE, messages.getString(CLOSE_PRICE));
		view.getOrderTable().setColumnHeader(GUV, messages.getString(GUV));

		view.getOrderTable().setImmediate(true);

		view.getOrderTable().addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final ValueChangeEvent event) {
				final Long orderId = (Long) view.getOrderTable().getValue();
				if (orderId != null) {
					liferayipc.sendEvent(IpcConstants.EVENT_LOAD_ORDER, String.valueOf(orderId));
				}
			}
		});
	}

	private void setupIpc() {
		liferayipc.addLiferayIPCEventListener(IpcConstants.EVENT_RELOAD_ORDERS, new LiferayIPCEventListener() {
			@Override
			public void eventReceived(final LiferayIPCEvent event) {
				// Notification.show("Got event " + event.getEventId() +
				// " with data " + event.getData());
				// refreshData method doesnt work here:(
				setupIndexContainer();
			}
		});
	}

	@Override
	public void unbind() {
	}

}
