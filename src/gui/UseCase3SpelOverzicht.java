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
	private Node paneel;
	
	public UseCase3SpelOverzicht(DomeinController controller)
	{
//		try
//		{
			this.controller = controller;
			
			// TODO tijdelijke methode, verwijder later
			precondities();
			
			// bouw het spelOverzicht
			buildGui();
			
			// voeg het startBeurtPaneel onderaan toe
			paneel = new UseCase3KnoppenPaneelStartBeurt(controller, this);
			this.getChildren().add(paneel);
			
			// update alle tekst en velden
			updateGui();
//		}
//		catch(Exception e)
//		{
//			WarningAlertScherm.toonAlert();
//		}
	}

	private void precondities()
	{
//		controller.meldAan("IkBenBen", "IkBenDokter");
//		controller.meldAan("mns58", "myDiscordPassword");
		controller.meldAanOffline();
		
		controller.startSpel();
	}

	private void buildGui() throws RuntimeException
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("UseCase3SpelOverzicht.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			
			btnTaal.setOnAction(evt -> 
	        {
	        	controller.veranderTaal();
	        	buildText();;      
	        });
		}
		catch(IOException e)
		{
			throw new RuntimeException("Het scherm kan niet geladen worden");
		}
	}
	
	public void updateGui()
	{
		buildText();
		buildVelden();
	}
	
	private void buildText()
	{
		lblUserName.setText(controller.geefNaamSpelerAanBeurt());
		btnTaal.setText(controller.getMessages("btnTaal"));
		lblLegende.setText(controller.getMessages("lblLegende"));
		lblGv.setText(controller.getMessages("lblGv"));
		lblWv.setText(controller.getMessages("lblWv"));
		lblSpelerStenen.setText(controller.getMessages("lblSpelerStenen"));
		
		if(paneel instanceof UseCase3HasText)
		{
			((UseCase3HasText) paneel).buildText();
		}
	}
	
	private void buildVelden()
	{
		String[] spelOverzicht = controller.geefSpelOverzicht();
		
		txaGv.setText(spelOverzicht[0]);
		txaWv.setText(spelOverzicht[1]);
		txaSpelerStenen.setText(spelOverzicht[2]);
	}
	
	public void toonEindeBeurt()
	{
		txaSpelerStenen.setText(controller.beeindigBeurt());
	}
	
	public void startPaneel()
	{
		this.getChildren().remove(paneel);
		paneel = new UseCase3KnoppenPaneelStartBeurt(controller, this);
		this.getChildren().add(paneel);
	}
	
	public void keuzePaneel()
	{
		this.getChildren().remove(paneel);
		paneel = new UseCase3KnoppenPaneelKeuze(controller, this);
		this.getChildren().add(paneel);
	}
	
	public void actiePaneel(String actie)
	{
		this.getChildren().remove(paneel);
		UseCase3ActiePaneel actiePaneel = new UseCase3ActiePaneel(controller, this);
		
		switch(actie)
		{
			case "legAan":
				actiePaneel.legAan();
				break;
			case "splits":
				actiePaneel.splits();
				break;
			case "joker":
				actiePaneel.joker();
				break;
			case "werkVeld":
				actiePaneel.werkVeld();
				break;
		}
		
		paneel = actiePaneel;
		this.getChildren().add(paneel);
	}
	
	public void eindePaneel()
	{
		this.getChildren().remove(paneel);
		paneel = new UseCase3KnoppenPaneelEindeBeurt(controller, this);
		this.getChildren().add(paneel);
	}
}
