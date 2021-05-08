package gui;

import java.io.IOException;
import java.util.List;

import domein.DomeinController;
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

public class UseCase1GebruikersLijstScherm extends VBox
{
	@FXML
	private Button btnTaal;
	@FXML
	private Label lblUserList;
	@FXML
	private Label lblUsers;
	@FXML
	private Button btnStart;
	
	private DomeinController controller;
	
	public UseCase1GebruikersLijstScherm(DomeinController controller) throws RuntimeException
	{
		try
		{
			this.controller = controller;
			buildGui();
			buildText();
			// focust op de Start spel knop vanaf dat het scherm geladen is
			Platform.runLater(() -> btnStart.requestFocus());
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
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("UseCase1GebruikersLijstScherm.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			
	        btnTaal.setOnAction(this::TaalPushed);
	        btnStart.setOnAction(this::StartPushed);
		}
		catch (IOException e)
		{
			throw new RuntimeException("Het scherm kan niet geladen worden");
		}		
	}
	
	private void buildText()
	{
    	btnTaal.setText(controller.getMessages("btnTaal"));
		lblUserList.setText(controller.getMessages("lijstNamen"));

		List<String> gebruikersnamen = controller.geefLijstGebruikersnaam();
		String lijst = String.join("\n", gebruikersnamen);
		
		lblUsers.setText(lijst);
		btnStart.setText(controller.getMessages("btnStart"));
	}
	
    private void TaalPushed(ActionEvent event)
    {
    	controller.veranderTaal();
    	buildText();
    }
    
    private void StartPushed(ActionEvent event)
    {
		try
		{
			controller.startSpel();
			UseCase3SpelOverzicht sc = new UseCase3SpelOverzicht(controller);
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
