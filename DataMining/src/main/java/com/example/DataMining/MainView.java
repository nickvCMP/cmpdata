package com.example.DataMining;

import com.example.DataMining.NavigationUI;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ClassResource;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@Theme("valo")
@SuppressWarnings("serial")
public class MainView extends VerticalLayout implements View{

	public MainView(){
		
		setSizeFull();
		setSpacing(true);
		addComponent(new Menu());
		addComponent(headingLabel());
	}
	
	@Override
	public void enter(ViewChangeEvent event){
		
		Notification.show("Home Page!");
	}
	
	private Label headingLabel(){
		
		return new Label("Home Page");
	}
}
