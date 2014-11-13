package com.flufflicks.marketjournal.portal.applications.controller;

import com.flufflicks.marketjournal.portal.presenter.OrderListPresenter;
import com.flufflicks.marketjournal.portal.ui.views.OrderListView;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

/**
 * The Class OrderListControllerUI.
 */
public class OrderListControllerUI extends UI {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4615694369498167489L;

	/** The view. */
	private final OrderListView view = new OrderListView();

	/** The presenter. */
	private OrderListPresenter presenter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
	 */
	@Override
	protected final void init(final VaadinRequest request) {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setMargin(true);
		setContent(layout);

		layout.addComponent(view);

		presenter = new OrderListPresenter(view, this);
		presenter.bind();
	}

}
