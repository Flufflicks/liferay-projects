package com.flufflicks.marketjournal.portal.presenter;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;

import com.flufflicks.marketjournal.portal.ui.views.OrderEditView;
import com.flufflicks.marketjournal.portal.util.VaadinPortalUtil;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * The Class OrderEditPresenter.
 */
public class OrderEditPresenter implements Presenter, ClickListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3536805609526001984L;

	/** The view. */
	private final OrderEditView view;

	/**
	 * Instantiates a new order edit presenter.
	 *
	 * @param orderView
	 *            the view
	 */
	public OrderEditPresenter(final OrderEditView orderView) {
		this.view = orderView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.
	 * ClickEvent)
	 */
	@Override
	public final void buttonClick(final ClickEvent event) {
		final PortletPreferences prefs = VaadinPortalUtil.getPortletPrefs();
		try {
			prefs.setValue("positions", this.view.getPositions());
			prefs.setValue("strategies", this.view.getStrategies());
		} catch (final ReadOnlyException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flufflicks.marketjournal.portal.presenter.Presenter#bind()
	 */
	@Override
	public final void bind() {
		this.view.addClickListener(this);

		final PortletPreferences prefs = VaadinPortalUtil.getPortletPrefs();
		final String positions = prefs.getValue("positions", "");
		final String strategies = prefs.getValue("strategies", "");

		this.view.setPositions(positions);
		this.view.setStrategies(strategies);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flufflicks.marketjournal.portal.presenter.Presenter#unbind()
	 */
	@Override
	public final void unbind() {
		this.view.removeClickListener(this);
	}

}
