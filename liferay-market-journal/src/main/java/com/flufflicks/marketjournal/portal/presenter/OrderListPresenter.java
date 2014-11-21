package com.flufflicks.marketjournal.portal.presenter;

import java.util.Date;
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

/**
 * The Class OrderListPresenter.
 */
public class OrderListPresenter implements Presenter {
	
	/** The view. */
	private final OrderListView view;

	/** The liferayipc component for Inter-Portlet-Communication. */
	private final LiferayIPC liferayipc = new LiferayIPC();
	
	/** The Constant POSITION. */
	static final String OPENDATE = "orderlist.opendate";
	
	/** The Constant POSITION. */
	static final String INSTRUMENT = "orderlist.instrument";

	/** The Constant ORDER_TYPE. */
	static final String ORDER_TYPE = "orderlist.ordertype";

	/** The Constant STRATEGY. */
	static final String STRATEGY = "orderlist.strategy";

	/** The Constant OPEN_PRICE. */
	static final String OPEN_PRICE = "orderlist.openPrice";

	/** The Constant CLOSE_PRICE. */
	static final String CLOSE_PRICE = "orderlist.closePrice";

	/** The Constant GUV. */
	static final String GUV = "orderlist.guv";

	/** The order data bo. */
	private final OrderDataBo orderDataBo = SpringBoHelper.getOrderDataBo();

	/**
	 * Instantiates a new order list presenter.
	 *
	 * @param orderListView the view
	 * @param controller the controller
	 */
	public OrderListPresenter(final OrderListView orderListView, final OrderListControllerUI controller) {
		liferayipc.extend(controller);
		this.view = orderListView;		
	}

	/* (non-Javadoc)
	 * @see com.flufflicks.marketjournal.portal.presenter.Presenter#bind()
	 */
	@Override
	public final void bind() {
		setupIpc();
		setupIndexContainer();
		setupOrderTable();

	}

	/**
	 * Setup index container.
	 */
	private void setupIndexContainer() {
		final IndexedContainer ic = new IndexedContainer();
		ic.addContainerProperty(OPENDATE, Date.class, "");
		ic.addContainerProperty(INSTRUMENT, String.class, "");
		ic.addContainerProperty(ORDER_TYPE, String.class, "");
		ic.addContainerProperty(STRATEGY, String.class, "");
		ic.addContainerProperty(OPEN_PRICE, Float.class, "");
		ic.addContainerProperty(CLOSE_PRICE, Float.class, "");
		ic.addContainerProperty(GUV, Float.class, "");
		loadData(ic);
		view.getOrderTable().setContainerDataSource(ic);

	}

	/**
	 * Load data.
	 *
	 * @param ic the ic
	 */
	@SuppressWarnings("unchecked")
	private void loadData(final IndexedContainer ic) {
		ic.removeAllItems();

		final List<OrderData> orderList = orderDataBo.findAll();
		for (final OrderData orderData : orderList) {
			ic.addItem(orderData.getId());
			ic.getContainerProperty(orderData.getId(), OPENDATE).setValue(orderData.getOpenDate());
			ic.getContainerProperty(orderData.getId(), INSTRUMENT).setValue(orderData.getInstrument());
			ic.getContainerProperty(orderData.getId(), ORDER_TYPE).setValue(orderData.getOrderType());
			ic.getContainerProperty(orderData.getId(), STRATEGY).setValue(orderData.getStrategy());
			ic.getContainerProperty(orderData.getId(), OPEN_PRICE).setValue(orderData.getOpenPrice());
			ic.getContainerProperty(orderData.getId(), CLOSE_PRICE).setValue(orderData.getClosePrice());
			ic.getContainerProperty(orderData.getId(), GUV).setValue(orderData.getGuv());
		}
	}

	/**
	 * Setup order table.
	 */
	private void setupOrderTable() {
		final Locale currentLocale = VaadinPortalUtil.getCurrentLocale();
		final ResourceBundle messages = ResourceBundle.getBundle("i18n", currentLocale);
		

		
		view.getOrderTable().setVisibleColumns(new Object[] {OPENDATE, INSTRUMENT, ORDER_TYPE, STRATEGY, OPEN_PRICE, CLOSE_PRICE, GUV });
		view.getOrderTable().setSelectable(true);
		view.getOrderTable().setColumnHeader(OPENDATE, messages.getString(OPENDATE));
		view.getOrderTable().setColumnHeader(INSTRUMENT, messages.getString(INSTRUMENT));
		view.getOrderTable().setColumnHeader(ORDER_TYPE, messages.getString(ORDER_TYPE));
		view.getOrderTable().setColumnHeader(STRATEGY, messages.getString(STRATEGY));
		view.getOrderTable().setColumnHeader(OPEN_PRICE, messages.getString(OPEN_PRICE));
		view.getOrderTable().setColumnHeader(CLOSE_PRICE, messages.getString(CLOSE_PRICE));
		view.getOrderTable().setColumnHeader(GUV, messages.getString(GUV));

		view.getOrderTable().setImmediate(true);

		view.getOrderTable().setConverter(OPENDATE, new com.vaadin.data.util.converter.StringToDateConverter(){
			@Override
			public java.text.DateFormat getFormat(final Locale locale){
			return new java.text.SimpleDateFormat("dd.MM.YYYY HH:mm");
			}
			});
		
		view.getOrderTable().addValueChangeListener(new Property.ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -1585214975533338551L;

			@Override
			public void valueChange(final ValueChangeEvent event) {
				final Long orderId = (Long) view.getOrderTable().getValue();
				if (orderId != null) {
					liferayipc.sendEvent(IpcConstants.EVENT_LOAD_ORDER, String.valueOf(orderId));
				}
			}
		});
	}

	/**
	 * Setup ipc.
	 */
	private void setupIpc() {
		liferayipc.addLiferayIPCEventListener(IpcConstants.EVENT_RELOAD_ORDERS, new LiferayIPCEventListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1197880924185747454L;

			@Override
			public void eventReceived(final LiferayIPCEvent event) {
				// Notification.show("Got event " + event.getEventId() +
				// " with data " + event.getData());
				// refreshData method doesnt work here:(
				setupIndexContainer();
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.flufflicks.marketjournal.portal.presenter.Presenter#unbind()
	 */
	@Override
	public void unbind() {
	}

}
