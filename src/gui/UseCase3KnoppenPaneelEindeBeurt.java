package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UseCase3KnoppenPaneelEindeBeurt extends VBox
{
	@FXML
	private Label lblMelding;
	@FXML
	private Button btnOk;
	
	private UseCase3SpelOverzicht parent;
	
	public UseCase3KnoppenPaneelEindeBeurt(UseCase3SpelOverzicht parent)
	{
		buildGui();
		this.parent = parent;
	}
	
	
	private void buildGui() throws RuntimeException
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("UseCase3KnoppenPaneelEindeBeurt.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			
			btnOk.setOnAction(evt -> 
	        {
	        	parent.startPaneel();      
	        });
		}
		catch(IOException e)
		{
			throw new RuntimeException("Het scherm kan niet geladen worden");
		}
	}
}
