package com.example.DataMining;

import com.example.DataMining.NavigationUI;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@Theme("valo")
@SuppressWarnings("serial")
public class LoginView extends VerticalLayout implements View{
	
	public LoginView(){
		setSizeFull();
		setSpacing(true);
		
		Panel panel = new Panel();
		VerticalLayout panelLayout = new VerticalLayout();
		
		addComponent(panel);
		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
		
		panel.setWidth("360px");
		panel.setHeight("400px");
		
		panelLayout.setMargin(true);
		panelLayout.setSpacing(true);
		
		Label title = new Label(String.format("<font size = \"8\" color=\"#3BB9FF\">SearchLife"), ContentMode.HTML);
		title.setWidthUndefined();
		
		TextField username = new TextField("Username");
		PasswordField password = new PasswordField("Password");
		
		Button button = new Button("Log In", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(username.getValue().equals("ChannelMethods") && password.getValue().equals("methods1234")){
					getUI().getNavigator().navigateTo(NavigationUI.MAINVIEW);
				}
				else{
					username.setValue("");
					password.setValue("");
					Notification.show("Wrong username or password, try again.");
				}
			}
		});
		
		Label description = new Label("a Channel Methods Partners Data Mining App");
		title.setWidthUndefined();
		
		panelLayout.addComponent(title);
		panelLayout.addComponent(username);
		panelLayout.addComponent(password);
		panelLayout.addComponent(button);
		panelLayout.addComponent(description);
		
		panelLayout.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
		panelLayout.setComponentAlignment(username, Alignment.MIDDLE_CENTER);
		panelLayout.setComponentAlignment(password, Alignment.MIDDLE_CENTER);
		panelLayout.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
		panelLayout.setComponentAlignment(description, Alignment.MIDDLE_CENTER);
		
		panel.setContent(panelLayout);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Welcome, Please log in.");
	}

}
