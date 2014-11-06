package com.flufflicks.marketjournal.portal.applications.controller;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.flufflicks.marketjournal.portal.presenter.OrderEditPresenter;
import com.flufflicks.marketjournal.portal.presenter.OrderPresenter;
import com.flufflicks.marketjournal.portal.ui.views.OrderEditView;
import com.flufflicks.marketjournal.portal.ui.views.OrderView;
import com.vaadin.server.VaadinPortletSession;
import com.vaadin.server.VaadinPortletSession.PortletListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class OrderControllerUI extends UI implements PortletListener {

	private final OrderView view = new OrderView();
	private final OrderEditView edit = new OrderEditView();
	HorizontalLayout viewLayout = new HorizontalLayout();
	HorizontalLayout editLayout = new HorizontalLayout();

	private OrderPresenter viewPresenter;
	private OrderEditPresenter editPresenter;

	@Override
	protected void init(final VaadinRequest request) {
		viewLayout.setMargin(true);
		viewLayout.addComponent(view);

		editLayout.setMargin(true);
		editLayout.addComponent(edit);

		setContent(viewLayout);

		viewPresenter = new OrderPresenter(view, this);
		viewPresenter.bind();
		editPresenter = new OrderEditPresenter(edit);
		editPresenter.bind();

        
		final VaadinPortletSession portletsession = (VaadinPortletSession) VaadinSession.getCurrent();
		// Add a custom listener to handle action and
		// render requests.
		portletsession.addPortletListener(this);

	}

	@Override
	public void handleResourceRequest(final ResourceRequest request, final ResourceResponse response, final UI root) {
		if (request.getPortletMode() == PortletMode.EDIT)
			setContent(editLayout);
		else if (request.getPortletMode() == PortletMode.VIEW)
			setContent(viewLayout);
	}

	@Override
	public void handleRenderRequest(final RenderRequest request, final RenderResponse response, final UI uI) {

	}

	@Override
	public void handleActionRequest(final ActionRequest request, final ActionResponse response, final UI uI) {

	}

	@Override
	public void handleEventRequest(final EventRequest request, final EventResponse response, final UI uI) {

	}

}
