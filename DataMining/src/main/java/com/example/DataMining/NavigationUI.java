package com.example.DataMining;

import javax.servlet.annotation.WebServlet;

import com.example.DataMining.LoginView;
import com.example.DataMining.MainView;
import com.example.DataMining.NavigationUI;
import com.example.DataMining.PipelineView;
import com.example.DataMining.TwitterView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.ComponentContainerViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import twitter4j.TwitterException;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("valo")
@SuppressWarnings("serial")
public class NavigationUI extends UI {
	
	public Navigator navigator;
	public static final String MAINVIEW = "main";
	public static final String TWITTERVIEW = "twitter";
	public static final String PIPELINEVIEW = "pipeline";

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
    	final VerticalLayout layout = new VerticalLayout();
    	layout.setMargin(true);
    	layout.setSpacing(true);
    	setContent(layout);
    	
    	ComponentContainerViewDisplay viewDisplay = new ComponentContainerViewDisplay(layout);
    	
    	navigator = new Navigator(UI.getCurrent(), viewDisplay);
    	navigator.addView("", new LoginView());
    	navigator.addView(MAINVIEW, new MainView());
    	try {
			navigator.addView(TWITTERVIEW, new TwitterView());
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	navigator.addView(PIPELINEVIEW, new PipelineView());
    }

    @WebServlet(urlPatterns = "/*", name = "NavigationUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NavigationUI.class, productionMode = false)
    public static class NavigationUIServlet extends VaadinServlet {
    }
}
