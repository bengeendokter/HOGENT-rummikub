package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class UseCase3SpelOverzicht extends VBox
{
	@FXML
	private Label lblUserName;
	@FXML
	private Button btnTaal;
	@FXML
	private Label lblLegende;
	@FXML
	private Label lblGv;
	@FXML
	private TextArea txaGv;
	@FXML
	private Label lblWv;
	@FXML
	private TextArea txaWv;
	@FXML
	private Label lblSpelerStenen;
	@FXML
	private TextArea txaSpelerStenen;
	
	private DomeinController controller;
	private Object paneel;
	
	public UseCase3SpelOverzicht(DomeinController controller)
	{
		this.controller = controller;
		
		
		
		precondities();
		buildGui();
		buildText();
		
		paneel = new UseCase3ActiePaneel(controller, this);
		
		this.getChildren().add((Node) paneel);
	}
	
	public void knoppenPaneel()
	{
		this.getChildren().remove((Node) paneel);
		paneel = new UseCase3KnoppenPaneelStartBeurt(controller, this);
		this.getChildren().add((Node) paneel);
	}
	
	public void actiePaneel()
	{
		this.getChildren().remove((Node) paneel);
		paneel = new UseCase3ActiePaneel(controller, this);
		this.getChildren().add((Node) paneel);
	}
	
	private void precondities()
	{
		controller.meldAan("IkBenBen", "IkBenDokter");
		controller.meldAan("mns58", "myDiscordPassword");
		
		controller.startSpel();
		controller.startBeurt();
	}

	private void buildGui() throws RuntimeException
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("UseCase3SpelOverzicht.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		}
		catch(IOException e)
		{
			throw new RuntimeException("Het scherm kan niet geladen worden");
		}
	}
	
	private void buildText()
	{
		lblUserName.setText(controller.geefNaamSpelerAanBeurt());
		
		String[] spelOverzicht = controller.geefSpelOverzicht();
		
		txaGv.setText(spelOverzicht[0]);
		txaWv.setText(spelOverzicht[1]);
		txaSpelerStenen.setText(spelOverzicht[2]);	
	}
}
