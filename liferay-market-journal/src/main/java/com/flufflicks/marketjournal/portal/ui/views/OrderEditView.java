package com.flufflicks.marketjournal.portal.ui.views;


import java.util.Locale;
import java.util.ResourceBundle;

import com.flufflicks.marketjournal.portal.util.VaadinPortalUtil;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class OrderEditView  extends VerticalLayout implements View {

	private final VerticalLayout mainLayout0 = new VerticalLayout();
	
	private final TextField positions = new TextField("positions");
	private final TextField strategies = new TextField("strategies");

	private final Button saveButton = new Button();

	@Override
	public void enter(final ViewChangeEvent event) {
		
	}
	
	public OrderEditView(){
		final Locale currentLocale = VaadinPortalUtil.getCurrentLocale();
		final ResourceBundle messages = ResourceBundle.getBundle("i18n", currentLocale);
		
		saveButton.setCaption(messages.getString("label.save"));

		addComponent(mainLayout0);        
        mainLayout0.addComponent(positions);
        mainLayout0.addComponent(strategies);

        mainLayout0.addComponent(saveButton);
	}

	public TextField getPositions() {
		return positions;
	}
	
	public TextField getStrategies() {
		return strategies;
	}
	
	public Button getSaveButton(){
		return saveButton;
	}

}
