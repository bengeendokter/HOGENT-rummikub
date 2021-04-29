package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class UseCase3KnoppenPaneelKeuze extends VBox implements UseCase3HasText
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
		try
		{
			this.controller = controller;
			this.parent = parent;
			
			buildGui();
			buildText();
			Platform.runLater(() -> btnSteenAan.requestFocus());
		}
		catch(Exception e)
		{
			WarningAlertScherm.toonAlert();
		}
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
			
			btnReset.setOnAction(evt -> 
	        {
	        	controller.resetBeurt();
	        	parent.updateGui();
	        	resetBeurtMessage();
	        });
			
			btnEnd.setOnAction(evt -> 
	        {
	        	// indien de speler geen stenen meer heeft
	        	if(controller.isEindeSpel())
	        	{
	        		toonScoreScherm();
	        	}
	        	
	        	parent.toonEindeBeurt();
	        	parent.eindePaneel();      
	        });
		}
		catch(IOException e)
		{
			throw new RuntimeException("Het scherm kan niet geladen worden");
		}
	}

	private void resetBeurtMessage()
	{
		lblMelding.setText(controller.getMessages("msgResetBeurt"));
	}


	@Override
	public void buildText()
	{
		btnSteenAan.setText(controller.getMessages("legAan"));
		btnSplitsen.setText(controller.getMessages("splits"));
		btnJoker.setText(controller.getMessages("joker"));
		btnWerkVeld.setText(controller.getMessages("werkVeld"));
		btnReset.setText(controller.getMessages("resetBeurt"));
		btnEnd.setText(controller.getMessages("eindigBeurt"));
		lblMelding.setText("");
	}
	
	private void toonScoreScherm()
	{
		UseCase2ScoreScherm sc = new UseCase2ScoreScherm(controller);
        Scene scene = new Scene(sc);
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(scene);
        // centreer scherm
        stage.setX((Screen.getPrimary().getVisualBounds().getWidth() - sc.getWidth()) / 2);
        stage.setY((Screen.getPrimary().getVisualBounds().getHeight() - sc.getWidth()) / 2);
        stage.show();
	}
}

