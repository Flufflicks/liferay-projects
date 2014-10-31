package com.flufflicks.marketjournal.portal.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/** A start view for navigating to the main view */
public class OrderListView extends VerticalLayout implements View {

	private static final long serialVersionUID = -3398565663865641952L;

	private final HorizontalLayout mainLayout = new HorizontalLayout();

	private final VerticalLayout leftLayout = new VerticalLayout();

	private final Table orderTable = new Table();

	public OrderListView() {
		setupView();
	}

	private void setupView() {
		setupLayout();
		leftLayout.addComponent(orderTable);

	}



	private void setupLayout() {
		addComponent(mainLayout);
		mainLayout.addComponent(leftLayout);
	}



	@Override
	public void enter(final ViewChangeEvent event) {
		Notification.show("ProductView");
	}

	
	public Table getOrderTable(){
		return orderTable;
	}

}
