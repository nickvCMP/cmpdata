package com.example.DataMining;

import com.example.DataMining.NavigationUI;

import com.vaadin.annotations.Theme;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

@Theme("valo")
@SuppressWarnings("serial")
public class Menu extends CustomComponent{

	public Menu(){
		HorizontalLayout layout = new HorizontalLayout();
		Label title = new Label(String.format("<font size = \"6.9\" color=\"#3BB9FF\">SearchLife"), ContentMode.HTML);
		layout.addComponent(title);
		
		Button homeButton = new Button("Home", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(NavigationUI.MAINVIEW);
			}
		});
		homeButton.setStyleName(ValoTheme.BUTTON_BORDERLESS);
		layout.addComponent(homeButton);
		layout.setComponentAlignment(homeButton, Alignment.BOTTOM_CENTER);
		
		Button twitterButton = new Button("Twitter", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(NavigationUI.TWITTERVIEW);
			}
		});
		twitterButton.setStyleName(ValoTheme.BUTTON_BORDERLESS);
		layout.addComponent(twitterButton);
		layout.setComponentAlignment(twitterButton, Alignment.BOTTOM_CENTER);
		
		Button pipeButton = new Button("Pipeline", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(NavigationUI.PIPELINEVIEW);
			}
		});
		pipeButton.setStyleName(ValoTheme.BUTTON_BORDERLESS);
		layout.addComponent(pipeButton);
		layout.setComponentAlignment(pipeButton, Alignment.BOTTOM_CENTER);
		
		Button logoutButton = new Button("Logout", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getSession().close();
				getUI().getPage().setLocation(getLogoutPath());
			}
		});
		logoutButton.setStyleName(ValoTheme.BUTTON_BORDERLESS);
		layout.addComponent(logoutButton);
		layout.setComponentAlignment(logoutButton, Alignment.BOTTOM_CENTER);
		
		layout.setSizeUndefined();
		layout.setSpacing(true);
		setSizeUndefined();
		setCompositionRoot(layout);	
	}
	
	private String getLogoutPath() {
		return getUI().getPage().getLocation().getPath();
	}
}
