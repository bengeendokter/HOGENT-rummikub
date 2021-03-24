package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.application.Platform;
//import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class UseCase1AantalGebruikersScherm extends GridPane
{	
	// @FXML
	// private Label lblTipPercentage;
	//private final DomeinController controller;
	
	public UseCase1AantalGebruikersScherm(DomeinController controller)
	{
		//this.controller = controller;
		buildGui();
	}

	private void buildGui()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("UseCase1AantalGebruikersScherm.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		}
		catch (IOException e)
		{
			System.out.println("Het scherm kan niet geladen worden");
			Platform.exit();
		}		
	}
}
