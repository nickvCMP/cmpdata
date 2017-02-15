package com.example.DataMining;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import com.example.DataMining.NavigationUI;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Theme("valo")
@SuppressWarnings("serial")
public class TwitterView extends VerticalLayout implements View{
	ArrayList<TwitterObject> list = new ArrayList<TwitterObject>();
	
	public TwitterView() throws TwitterException{
		
		setSizeFull();
		setSpacing(true);
		addComponent(new Menu());
		
		Label heading = new Label(String.format("<font size = \"3.5\" color=\"black\">Twitter Statuses"), ContentMode.HTML);
		addComponent(heading);
		heading.setSizeUndefined();
		
		ComboBox dropDown = new ComboBox("Search Terms");
		dropDown.setNullSelectionAllowed(false);
		dropDown.addItem("adverse events");
		dropDown.addItem("anti-infectives");
		dropDown.addItem("biomaker");
		dropDown.addItem("biosimilars");
		dropDown.addItem("CE Mark");
		dropDown.addItem("clinical market");
		dropDown.addItem("clinical research");
		dropDown.addItem("clinical trials");
		dropDown.addItem("clinicalTrials with phases I, II, III");
		dropDown.addItem("electronic medical records");
		dropDown.addItem("FDA approval");
		dropDown.addItem("gene therapy");
		dropDown.addItem("gene transfer");
		dropDown.addItem("imaging agents");
		dropDown.addItem("In vitro Diagnostics");
		dropDown.addItem("investigational new drug");
		dropDown.addItem("lab equiptment");
		dropDown.addItem("medical affairs");
		dropDown.addItem("medical device manufacturing");
		dropDown.addItem("medical manufacturing");
		dropDown.addItem("medical scribes");
		dropDown.addItem("medical trials");
		dropDown.addItem("medical writing");
		dropDown.addItem("molecular imaging");
		dropDown.addItem("new drug application");
		dropDown.addItem("oncology");
		dropDown.addItem("pharmacovigilance");
		dropDown.addItem("radiochemistry");
		dropDown.addItem("regenerative medicine");
		dropDown.addItem("rxtrace");
		dropDown.addItem("serialization");
		dropDown.addItem("serious adverse events");
		dropDown.addItem("signal detection");
		dropDown.addItem("solid tumor");
		dropDown.addItem("SUSAR");
		dropDown.addItem("verification");
		
		
		
		addComponent(dropDown);
		Grid grid = new Grid();
		grid.setSizeFull();
		
		dropDown.addValueChangeListener(event ->
			{
				try {
					getInfo(dropDown.getValue());
					grid.setContainerDataSource(new BeanItemContainer<>(TwitterObject.class, list));
					list.clear();
				} catch (TwitterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		
		grid.setContainerDataSource(new BeanItemContainer<>(TwitterObject.class, list));
		grid.setColumnOrder("username", "status", "time", "url");
		grid.getColumn("username").setWidth(165);
		grid.getColumn("status").setWidth(825);
		grid.getColumn("time").setWidth(225);
		grid.getColumn("url").setWidth(310);
		grid.setHeightMode( HeightMode.ROW );
		grid.setHeightByRows(11);
		grid.setSelectionMode(SelectionMode.NONE);
		
		addComponent(grid);
		
		Label tip = new Label(String.format("<font size = \"3.5\" color=\"F44B30\">Triple click the url to highlight and copy to clipboard!"), ContentMode.HTML);
		addComponent(tip);
		//F44B30/F72706
	}
	
	@Override
	public void enter(ViewChangeEvent event){
		Notification.show("Twitter Resources!");
	}
	
	public void getInfo(Object term) throws TwitterException{
		
		String searchTerm = term.toString();
		//System.out.println(searchTerm);
		
		ConfigurationBuilder config = new ConfigurationBuilder();		//setting up a configuration builder to configure the below authentications
	
		config.setDebugEnabled(true)
			.setOAuthConsumerKey("jD9DXc6q8jh7HbxuWOkdkjg1s")
			.setOAuthConsumerSecret("w7vtyfaQKKcCbWL7IMsDkel55KtlQST63Z74buWeZ8NE36vgmn")
			.setOAuthAccessToken("827535048727470080-kdmKTlA3GoGsbFyUHWkyA1XzWmak2Bb")
			.setOAuthAccessTokenSecret("hUPUkXrnIU5B4n6dtk93Y4Cp8dXtkV6E4pVcjCxe3Q9x0");
	
		TwitterFactory twitfact = new TwitterFactory(config.build());
		twitter4j.Twitter twitter = twitfact.getInstance();
	
		Query query = new Query(searchTerm);		//setting up the query to be what is wanted. (Testing with gene transfer)
		query.setCount(100);
		QueryResult result = twitter.search(query); 	//searching twitter for the query.
	
		//int number = 1; was testing to  see if the number of results match what was specified.
	
		for(Status stat : result.getTweets()){		//getting results of the search and printing them.
		
			String url = "https://twitter.com/" + stat.getUser().getScreenName() + "/status/" + stat.getId(); //getting url to the status post.
			
			if(stat.isRetweet()){
			}
			else{
				list.add(new TwitterObject(stat.getUser().getScreenName(), stat.getText(), url, stat.getCreatedAt()));
				//System.out.println("\nUSER: " + stat.getUser().getScreenName()+ "  STATUS: " + stat.getText() + "  URL: " + url + "  TIME POSTED: " + stat.getCreatedAt());
			}
			//number ++;
		}
	}
}

