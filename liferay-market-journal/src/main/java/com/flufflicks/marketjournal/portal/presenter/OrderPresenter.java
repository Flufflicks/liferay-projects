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

// TODO: Auto-generated Javadoc
/**
 * The Class OrderPresenter.
 */
public class OrderPresenter implements Presenter, ClickListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2586287264036274600L;

	/** The view. */
	private final OrderView view;
	
	/** The liferayipc component for Inter-Portlet-Communication. */
	private final LiferayIPC liferayipc = new LiferayIPC();
	
	/** The order data bussiness object. */
	private final OrderDataBo orderDataBo = SpringBoHelper.getOrderDataBo();

	/**
	 * Instantiates a new order presenter.
	 *
	 * @param orderView the view
	 * @param controller the controller
	 */
	public OrderPresenter(final OrderView orderView, final OrderControllerUI controller) {
		this.view = orderView;
		liferayipc.extend(controller);
	}

	/* (non-Javadoc)
	 * @see com.flufflicks.marketjournal.portal.presenter.Presenter#bind()
	 */
	@Override
	public final void bind() {
		setupIpc();
		view.addClickListener(this);
		addPositions();
	}

	/**
	 * Adds the positions from portlet properties.
	 */
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

	/* (non-Javadoc)
	 * @see com.flufflicks.marketjournal.portal.presenter.Presenter#unbind()
	 */
	@Override
	public final void unbind() {
		view.removeClickListener(this);
	}

	/* (non-Javadoc)
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
	 */
	@Override
	public final void buttonClick(final ClickEvent event) {
		if (event.getButton().getId() == OrderView.SAVE_BTN_ID) {
			liferayipc.sendEvent(IpcConstants.EVENT_RELOAD_ORDERS, null);
			saveOrder();
		} else if (event.getButton().getId() == OrderView.DELETE_BTN_ID) {
			deleteOrder();
			resetView();
			liferayipc.sendEvent(IpcConstants.EVENT_RELOAD_ORDERS, null);
		}
	}

	/**
	 * Reset the view.
	 */
	private void resetView() {
		this.view.reset();
	}

	/**
	 * Save the order in database.
	 */
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
			final String instrument = this.view.getSelectedCurrency();
			final String orderType = this.view.getOrderType();
			final String strategy = this.view.getStrategy();

			orderData.setInstrument(instrument);
			orderData.setOrderType(orderType);
			orderData.setStrategy(strategy);
		}

		final long orderId = this.view.getOrderId();
		orderData.setId(orderId);

		if (fieldsValid && selectsValid) {
			orderDataBo.saveOrUpdate(orderData);
		}
	}
	
	/**
	 * Delete the order.
	 */
	private void deleteOrder() {
		final long orderId = this.view.getOrderId();
		orderDataBo.deleteById(orderId);
	}

	/**
	 * Setup Inter-Portlet-Communication.
	 */
	private void setupIpc() {
		liferayipc.addLiferayIPCEventListener(IpcConstants.EVENT_LOAD_ORDER, new LiferayIPCEventListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2243135528770194481L;

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

	/**
	 * Load order from database.
	 *
	 * @param orderId the order id
	 */
	private void loadOrder(final long orderId) {
		final OrderData orderData = orderDataBo.findById(orderId);
		this.view.setOrderId(orderId);
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
