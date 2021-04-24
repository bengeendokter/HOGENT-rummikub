package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class UseCase3KnoppenPaneelStartBeurt extends VBox implements UseCase3HasText
{
	@FXML
	private Button btnStart;
	
	private DomeinController controller;
	private UseCase3SpelOverzicht parent;
	
	public UseCase3KnoppenPaneelStartBeurt(DomeinController controller, UseCase3SpelOverzicht parent)
	{
		this.controller = controller;
		this.parent = parent;
		
		buildGui();
		buildText();
		Platform.runLater(() -> btnStart.requestFocus());
	}
	
	private void buildGui() throws RuntimeException
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("UseCase3KnoppenPaneelStartBeurt.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			
	        btnStart.setOnAction(evt -> 
	        {
	        	startBeurt();
	        	parent.keuzePaneel();      
	        }
	        );
		}
		catch(IOException e)
		{
			throw new RuntimeException("Het scherm kan niet geladen worden");
		}
	}
	
	private void startBeurt()
	{
		controller.startBeurt();
	}
	
	@Override
	public void buildText()
	{
		btnStart.setText(controller.getMessages("btnStartBeurt"));
	}
}
