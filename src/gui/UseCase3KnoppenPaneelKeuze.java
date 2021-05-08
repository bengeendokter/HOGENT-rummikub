package gui;

import java.io.IOException;

import domein.DomeinController;
import exceptions.GeenSerieOfRijException;
import exceptions.Min30PuntenException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
	        	toonActiePaneel("splits");      
	        });
			
			btnSteenAan.setOnAction(evt -> 
	        {
	        	toonActiePaneel("legAan");      
	        });
			
			btnWerkVeld.setOnAction(evt -> 
	        {
	        	toonActiePaneel("werkVeld");      
	        });
			
			btnJoker.setOnAction(evt -> 
	        {
	        	toonActiePaneel("joker");      
	        });
			
			btnReset.setOnAction(this::resetBeurt);
			
			btnEnd.setOnAction(this::eindigBeurt);
		}
		catch(IOException e)
		{
			throw new RuntimeException("Het scherm kan niet geladen worden");
		}
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
	
	private void toonActiePaneel(String actie)
	{
    	try
		{
			parent.actiePaneel(actie);
		}
		catch(Exception e)
		{
			WarningAlertScherm.toonAlert();
		}
	}
	
	private void resetBeurt(ActionEvent evt)
	{
    	try
		{
			controller.resetBeurt();
			parent.updateGui();
			lblMelding.setText(controller.getMessages("msgResetBeurt"));
		}
		catch(Exception e)
		{
			WarningAlertScherm.toonAlert();
		}
	}
	
	private void eindigBeurt(ActionEvent evt)
	{
    	try
    	{
	    	// indien de speler geen stenen meer heeft
	    	if(controller.isEindeSpel())
	    	{
	    		toonScoreScherm();
	    	}
    	
        	parent.toonEindeBeurt();
        	parent.eindePaneel();
    	}
    	catch(Min30PuntenException e)
		{
			lblMelding.setText(controller.getMessages("msgMin30"));
		}
    	catch(GeenSerieOfRijException e)
    	{
			lblMelding.setText(controller.getMessages("msgGeenSerieOfRij"));
    	}
		catch(Exception e)
		{
			WarningAlertScherm.toonAlert();
		}
	}
	
	private void toonScoreScherm()
	{
		try
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
		catch(Exception e)
		{
			WarningAlertScherm.toonAlert();
		}
	}
}

