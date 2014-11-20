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

/**
 * The Class OrderEditView for displaying properties and configurations for order mask.
 */
public class OrderEditView extends VerticalLayout implements View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6379059798718988734L;

	/** The main layout. */
	private final VerticalLayout mainLayout0 = new VerticalLayout();

	/** The positions for the orderview dropdown (configure portlet-properties).  */
	private final TextField positions = new TextField("positions");
	
	/** The strategies for the orderview dropdown (configure portlet-properties). */
	private final TextField strategies = new TextField("strategies");

	/** The save button. */
	private final Button saveButton = new Button();
	


	/* (non-Javadoc)
	 * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	public void enter(final ViewChangeEvent event) {

	}

	/**
	 * Instantiates a new order edit view.
	 */
	public OrderEditView() {
		final Locale currentLocale = VaadinPortalUtil.getCurrentLocale();
		final ResourceBundle messages = ResourceBundle.getBundle("i18n", currentLocale);

		saveButton.setCaption(messages.getString("label.save"));
		addComponent(mainLayout0);
		mainLayout0.addComponent(positions);
		mainLayout0.addComponent(strategies);

		mainLayout0.addComponent(saveButton);
	}

	/**
	 * Sets the positions from the portlet-properties to the textfield.
	 *
	 * @param value the new positions
	 */
	public final void setPositions(final String value) {
		positions.setValue(value);
	}

	/**
	 * Sets the strategies from the portlet-properties to the textfield.
	 *
	 * @param value the new strategies
	 */
	public final void setStrategies(final String value) {
		strategies.setValue(value);
	}

	/**
	 * Gets the positions from textfield.
	 *
	 * @return the positions
	 */
	public final String getPositions() {
		return positions.getValue();
	}

	/**
	 * Gets the strategies from textfield.
	 *
	 * @return the strategies
	 */
	public final String getStrategies() {
		return strategies.getValue();
	}

	/**
	 * Adds the click listener (the presenter).
	 *
	 * @param listener the listener
	 */
	public final void addClickListener(final ClickListener listener) {
		saveButton.addClickListener(listener);
	}

	/**
	 * Removes the click listener.
	 *
	 * @param listener the listener
	 */
	public final void removeClickListener(final ClickListener listener) {
		saveButton.removeClickListener(listener);
	}
}
