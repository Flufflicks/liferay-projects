package com.flufflicks.marketjournal.portal.applications.controller;

import com.flufflicks.marketjournal.portal.presenter.OrderPresenter;
import com.flufflicks.marketjournal.portal.ui.views.OrderView;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class OrderControllerUI extends UI {

	private final OrderView view = new OrderView();
	private OrderPresenter presenter;

	@Override
	protected void init(final VaadinRequest request) {	
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setMargin(true);
		setContent(layout);

		layout.addComponent(view);

		presenter = new OrderPresenter(view, this);
		presenter.bind();
	}

}
