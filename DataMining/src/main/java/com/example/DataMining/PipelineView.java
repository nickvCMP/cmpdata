package com.example.DataMining;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.DataMining.NavigationUI;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;

import twitter4j.TwitterException;

import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class PipelineView extends VerticalLayout implements View{
	
	ArrayList<PipleineObject> list = new ArrayList<PipleineObject>();
	
	public PipelineView(){
		
		setSizeFull();
		setSpacing(true);
		addComponent(new Menu());
		
		Label heading = new Label(String.format("<font size = \"3.5\" color=\"grey\">Pipeline Articles"), ContentMode.HTML);
		addComponent(heading);
		
		ComboBox dropDown = new ComboBox("News Channels");
		dropDown.setNullSelectionAllowed(false);
		dropDown.addItem("Antibodies");
		dropDown.addItem("DNA-RNA-and-Cells");
		dropDown.addItem("More-News");
		dropDown.addItem("Proteins-and-Peptides");
		dropDown.addItem("Small-Molecules");
		dropDown.addItem("Vaccines");
		
		addComponent(dropDown);
		Grid grid = new Grid();
		grid.setSizeFull();
		
		dropDown.addValueChangeListener(event ->
			{
				getInfo(dropDown.getValue());
				grid.setContainerDataSource(new BeanItemContainer<>(PipleineObject.class, list));
				list.clear();
			});
		
		grid.setSizeFull();
		grid.setContainerDataSource(new BeanItemContainer<>(PipleineObject.class, list));
		grid.setColumnOrder("date","description","url");
		grid.getColumn("date").setWidth(120);
		grid.getColumn("description").setWidth(1000);
		grid.getColumn("url").setWidth(400);
		grid.setHeightMode(HeightMode.ROW);
		grid.setHeightByRows(11);
		grid.setSelectionMode(SelectionMode.NONE);
		
		addComponent(grid);
		
		Label tip = new Label(String.format("<font size = \"3.5\" color=\"F44B30\">Triple click the url to highlight and copy to clipboard!"), ContentMode.HTML);
		addComponent(tip);
		//F44B30/F72706
		
	}
	
	@Override
	public void enter(ViewChangeEvent event){
		Notification.show("Pipeline Resources!");
	}
	
	public void getInfo(Object term){
		
		String searchTerm = term.toString();
		
		try{
			Document doc = Jsoup.connect("http://pipelinereview.com/index.php/Table/News-Channels/" + searchTerm + "/").get();
			
			Elements heading = doc.select("span.subheading-category");
			//System.out.println("Heading: "+ heading.text());
			//System.out.println();
			
			Elements table = doc.select("table.category");
			Elements rows = table.select("tr");
			
			for(int i=0; i<rows.size(); i++){
				Element row = rows.get(i);
				Elements date = row.select("td.list-date");
				Elements description = row.select("td.list-title");
				Elements url = row.select("a[href]");
				
				list.add(new PipleineObject(date.text(), description.text(), "http://pipelinereview.com" + url.attr("href")));
				//System.out.println("\nDATE: " + date.text());
				//System.out.println("DESCRIPTION: " + description.text());
				//System.out.println("URL: " + "http://pipelinereview.com" + url.attr("href"));
			}

		} 
		catch(IOException e){
			e.printStackTrace();
		}
	}

}
