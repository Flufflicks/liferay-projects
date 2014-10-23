package com.flufflicks.marketjournal.portal.applications.controller;

import com.flufflicks.marketjournal.portal.presenter.OrderListPresenter;
import com.flufflicks.marketjournal.portal.ui.views.OrderListView;
import com.vaadin.addon.ipcforliferay.LiferayIPC;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class OrderListControllerUI extends UI {

	private final OrderListView view = new OrderListView();
	private OrderListPresenter presenter;
	final LiferayIPC liferayIPC = new LiferayIPC();

	@Override
	protected void init(final VaadinRequest request) {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setMargin(true);
		setContent(layout);

		layout.addComponent(view);

		presenter = new OrderListPresenter(view,this);
		presenter.bind();
	}

}
