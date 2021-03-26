package gui;

import java.io.IOException;
import java.util.List;

import domein.DomeinController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UseCase1GebruikersLijstScherm extends VBox
{
	@FXML
	private Button btnTaal;
	@FXML
	private Label lblUserList;
	@FXML
	private Label lblUsers;
	
	private DomeinController controller;
	
	public UseCase1GebruikersLijstScherm(DomeinController controller)
	{
		this.controller = controller;
		buildGui();
		buildText();
	}
	
	private void buildGui()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("UseCase1GebruikersLijstScherm.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			
	        btnTaal.setOnAction(this::TaalPushed);	        
		}
		catch (IOException e)
		{
			// TODO vervang door alert?
			System.out.println("Het scherm kan niet geladen worden");
			Platform.exit();
		}		
	}
	
	private void buildText()
	{
    	btnTaal.setText(controller.getMessages("btnTaal"));
		lblUserList.setText(controller.getMessages("lijstNamen"));

		List<String> gebruikersnamen = controller.geefLijstGebruikersnaam();
		String lijst = String.join("\n", gebruikersnamen);
		
		lblUsers.setText(lijst);
	}
	
    private void TaalPushed(ActionEvent event)
    {
    	controller.veranderTaal();
    	buildText();
    }
}
