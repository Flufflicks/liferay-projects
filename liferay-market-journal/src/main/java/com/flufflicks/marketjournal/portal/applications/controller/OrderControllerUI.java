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

/**
 * The Class OrderControllerUI.
 */
@SuppressWarnings("deprecation")
public class OrderControllerUI extends UI implements PortletListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4029767130761422681L;

	/** The ViewMode view. */
	private final OrderView view = new OrderView();

	/** The EditMode view. */
	private final OrderEditView edit = new OrderEditView();

	/** The view layout. */
	private final HorizontalLayout viewLayout = new HorizontalLayout();

	/** The edit layout. */
	private final HorizontalLayout editLayout = new HorizontalLayout();

	/** The ViewMode presenter. */
	private OrderPresenter viewPresenter;

	/** The EditMode presenter. */
	private OrderEditPresenter editPresenter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
	 */
	@Override
	protected final void init(final VaadinRequest request) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.server.VaadinPortletSession.PortletListener#handleResourceRequest
	 * (javax.portlet.ResourceRequest, javax.portlet.ResourceResponse,
	 * com.vaadin.ui.UI)
	 */
	@Override
	public final void handleResourceRequest(final ResourceRequest request, final ResourceResponse response, final UI root) {
		if (request.getPortletMode() == PortletMode.EDIT) {
			setContent(editLayout);
		} else if (request.getPortletMode() == PortletMode.VIEW) {
			setContent(viewLayout);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.server.VaadinPortletSession.PortletListener#handleRenderRequest
	 * (javax.portlet.RenderRequest, javax.portlet.RenderResponse,
	 * com.vaadin.ui.UI)
	 */
	@Override
	public void handleRenderRequest(final RenderRequest request, final RenderResponse response, final UI uI) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.server.VaadinPortletSession.PortletListener#handleActionRequest
	 * (javax.portlet.ActionRequest, javax.portlet.ActionResponse,
	 * com.vaadin.ui.UI)
	 */
	@Override
	public void handleActionRequest(final ActionRequest request, final ActionResponse response, final UI uI) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.server.VaadinPortletSession.PortletListener#handleEventRequest
	 * (javax.portlet.EventRequest, javax.portlet.EventResponse,
	 * com.vaadin.ui.UI)
	 */
	@Override
	public void handleEventRequest(final EventRequest request, final EventResponse response, final UI uI) {

	}

}
