package gui;

import java.io.IOException;
import java.util.List;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UseCase2ScoreScherm extends VBox
{
	@FXML
	private Button btnTaal;
	@FXML
	private Label lblScoreList;
	@FXML
	private Label lblScores;
	@FXML
	private Button btnUploadScore;
	@FXML
	private Label lblMelding;
	
	private DomeinController controller;
	
	public UseCase2ScoreScherm(DomeinController controller) throws RuntimeException
	{
		try
		{
			this.controller = controller;
			buildGui();
			buildText();
			geefScore();
		}
		catch(Exception e)
		{
			WarningAlertScherm.toonAlert();
		}
	}
	
	private void geefScore()
	{
		List<String> gebruikersnamen = controller.geefScoreOverzicht();
		String lijst = String.join("\n", gebruikersnamen);
		
		lblScores.setText(lijst);
	}
	
	private void buildGui() throws RuntimeException
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("UseCase2ScoreScherm.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			
	        btnTaal.setOnAction(this::TaalPushed);
	        btnUploadScore.setOnAction(this::uploadScore);
		}
		catch (IOException e)
		{
			throw new RuntimeException("Het scherm kan niet geladen worden");
		}		
	}
	
	private void buildText()
	{
    	btnTaal.setText(controller.getMessages("btnTaal"));
    	lblScoreList.setText(controller.getMessages("eindScore"));
    	btnUploadScore.setText(controller.getMessages("btnUploadScore"));
    	lblMelding.setText("");
	}
	
    private void TaalPushed(ActionEvent event)
    {
    	controller.veranderTaal();
    	buildText();
    }
    
    private void uploadScore(ActionEvent evt)
    {
    	try
		{
			controller.updateTotaalScore();
			lblMelding.setText(controller.getMessages("msgScoreUploaded"));
		}
		catch(RuntimeException e)
		{
			lblMelding.setText(controller.getMessages("msgConnectionFailed"));
		}
		catch(Exception e)
		{
			WarningAlertScherm.toonAlert();
		}
    }
}
