package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UseCase3KnoppenPaneelKeuze extends VBox
{
	@FXML
	private Button btnSteenAan;
	@FXML
	private Button btnSplitsen;
	@FXML
	private Button btnJoker;
	@FXML
	private Button btnWerkVeld;
	@FXML
	private Button btnReset;
	@FXML
	private Button btnEnd;
	@FXML
	private Label lblMelding;
	
	private DomeinController controller;
	private UseCase3SpelOverzicht parent;
	
	public UseCase3KnoppenPaneelKeuze(DomeinController controller, UseCase3SpelOverzicht parent)
	{
		this.controller = controller;
		buildGui();
		this.parent = parent;
	}
	
	
	private void buildGui() throws RuntimeException
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("UseCase3KnoppenPaneelKeuze.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			
			btnSplitsen.setOnAction(evt -> 
	        {
	        	parent.actiePaneel("splits");      
	        });
			
			btnSteenAan.setOnAction(evt -> 
	        {
	        	parent.actiePaneel("legAan");      
	        });
			
			btnWerkVeld.setOnAction(evt -> 
	        {
	        	parent.actiePaneel("werkVeld");      
	        });
			
			btnJoker.setOnAction(evt -> 
	        {
	        	parent.actiePaneel("joker");      
	        });
			
			btnEnd.setOnAction(evt -> 
	        {
	        	parent.eindePaneel();      
	        });
		}
		catch(IOException e)
		{
			throw new RuntimeException("Het scherm kan niet geladen worden");
		}
	}
}

