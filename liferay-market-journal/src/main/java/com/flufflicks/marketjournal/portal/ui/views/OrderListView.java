package com.flufflicks.marketjournal.portal.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 * This view is used to display the orders in a tableView.
 */
public class OrderListView extends VerticalLayout implements View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3398565663865641952L;

	/** The main layout. */
	private final HorizontalLayout mainLayout = new HorizontalLayout();

	/** The order table which display all orders for the portal user. */
	private final Table orderTable = new Table();

	/**
	 * Instantiates a new order list view.
	 */
	public OrderListView() {
		setupView();
	}

	/**
	 * Setup the view and add the orderTable.
	 */
	private void setupView() {
		setupLayout();
		mainLayout.addComponent(orderTable);
	}

	/**
	 * Setup the layout.
	 */
	private void setupLayout() {
		addComponent(mainLayout);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener
	 * .ViewChangeEvent)
	 */
	@Override
	public void enter(final ViewChangeEvent event) {
	}

	/**
	 * Gets the order table.
	 *
	 * @return the order table
	 */
	public final Table getOrderTable() {
		return orderTable;
	}

}
