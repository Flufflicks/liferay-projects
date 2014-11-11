package com.flufflicks.marketjournal.portal.applications.controller;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.flufflicks.marketjournal.portal.presenter.OrderStatsPresenter;
import com.flufflicks.marketjournal.portal.ui.views.OrderStatsView;
import com.vaadin.server.VaadinPortletSession;
import com.vaadin.server.VaadinPortletSession.PortletListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class OrderStatsControllerUI extends UI implements PortletListener {

	private final OrderStatsView view = new OrderStatsView();
	private OrderStatsPresenter presenter;

	@Override
	protected void init(final VaadinRequest request) {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setMargin(true);
		setContent(layout);

		layout.addComponent(view);

		presenter = new OrderStatsPresenter(view);
		presenter.bind();

		final VaadinPortletSession portletsession = (VaadinPortletSession) VaadinSession.getCurrent();
		// Add a custom listener to handle action and
		// render requests.
		portletsession.addPortletListener(this);

	}

	@Override
	public void handleRenderRequest(final RenderRequest request, final RenderResponse response, final UI uI) {
		presenter.unbind();
		presenter.bind();
	}

	@Override
	public void handleActionRequest(final ActionRequest request, final ActionResponse response, final UI uI) {
		
	}

	@Override
	public void handleEventRequest(final EventRequest request, final EventResponse response, final UI uI) {
		
	}

	@Override
	public void handleResourceRequest(final ResourceRequest request, final ResourceResponse response, final UI uI) {

	}

}