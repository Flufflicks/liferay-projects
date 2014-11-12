package com.flufflicks.marketjournal.portal.ui.views;

import java.util.Locale;
import java.util.ResourceBundle;

import com.flufflicks.marketjournal.portal.util.VaadinPortalUtil;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class OrderEditView extends VerticalLayout implements View {

	private final VerticalLayout mainLayout0 = new VerticalLayout();

	private final TextField positions = new TextField("positions");
	private final TextField strategies = new TextField("strategies");

	private final Button saveButton = new Button();

	@Override
	public void enter(final ViewChangeEvent event) {

	}

	public OrderEditView() {
		final Locale currentLocale = VaadinPortalUtil.getCurrentLocale();
		final ResourceBundle messages = ResourceBundle.getBundle("i18n", currentLocale);

		saveButton.setCaption(messages.getString("label.save"));

		addComponent(mainLayout0);
		mainLayout0.addComponent(positions);
		mainLayout0.addComponent(strategies);

		mainLayout0.addComponent(saveButton);
	}

	public void setPositions(final String value) {
		positions.setValue(value);
	}

	public void setStrategies(final String value) {
		strategies.setValue(value);
	}

	public String getPositions() {
		return positions.getValue();
	}

	public String getStrategies() {
		return strategies.getValue();
	}

	public void addClickListener(final ClickListener listener) {
		saveButton.addClickListener(listener);
	}

	public void removeClickListener(final ClickListener listener) {
		saveButton.removeClickListener(listener);
	}
}
