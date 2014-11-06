package com.flufflicks.marketjournal.portal.presenter;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;

import com.flufflicks.marketjournal.portal.ui.views.OrderEditView;
import com.flufflicks.marketjournal.portal.util.VaadinPortalUtil;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class OrderEditPresenter implements Presenter, ClickListener {
	private final OrderEditView view;

	public OrderEditPresenter(final OrderEditView view) {
		this.view = view;
		this.view.getSaveButton().addClickListener(this);
	}

	@Override
	public void buttonClick(final ClickEvent event) {
		final PortletPreferences prefs = VaadinPortalUtil.getPortletPrefs();
		try {
			prefs.setValue("positions", this.view.getPositions().getValue());
			prefs.setValue("strategies", this.view.getStrategies().getValue());
		} catch (final ReadOnlyException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void bind() {
		final PortletPreferences prefs = VaadinPortalUtil.getPortletPrefs();
		final String positions = prefs.getValue("positions", "");
		final String strategies = prefs.getValue("strategies", "");

		this.view.getPositions().setValue(positions);
		this.view.getStrategies().setValue(strategies);

	}

	@Override
	public void unbind() {

	}

}
