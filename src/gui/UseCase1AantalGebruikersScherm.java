package gui;

import java.io.IOException;

import domein.DomeinController;
import exceptions.BuitenBereikException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UseCase1AantalGebruikersScherm extends GridPane
{	
	@FXML
    private Label lblTitle;
	@FXML
    private Label lblAmount;
	@FXML
	private Button btnTaal;
	@FXML
	private TextField txfAmount;
	@FXML
	private Button btnAmount;
	@FXML
	private Label lblMessage;
	
	private final DomeinController controller;
	
	// TODO fix tabs en enters
	public UseCase1AantalGebruikersScherm(DomeinController controller)
	{
		this.controller = controller;
		buildGui();
    	buildText();
	}

	private void buildGui()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("UseCase1AantalGebruikersScherm.fxml"));

			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			
	        btnTaal.setOnAction(this::TaalPushed);
		    btnAmount.setOnAction(this::AmountPushed);
		}
		catch (IOException e)
		{
			// TODO vervang door alert?
			System.out.println("Het scherm kan niet geladen worden");
			Platform.exit();
			
			/*
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Fout!");
			alert.setHeaderText("Het scherm kan niet geladen worden");
			alert.setContentText("Contacteer de ontwikkelaars als dit probleem blijft optreden");
			alert.show();
			System.exit(0);
			*/
		}		
	}
	
	private void buildText()
	{
    	lblTitle.setText(controller.getMessages("lblTitle"));
		btnTaal.setText(controller.getMessages("btnTaal"));
    	lblAmount.setText(controller.getMessages("lblAmount"));
    	btnAmount.setText(controller.getMessages("btnAmount"));
    	lblMessage.setText("");
	}
	
	private void TaalPushed(ActionEvent event)
	{
    	controller.veranderTaal();
    	buildText();
	}
	
	private void AmountPushed(ActionEvent event)
	{
        try
        {
        	int amount = Integer.parseInt(txfAmount.getText());
        	controller.registreerAantal(amount);
        	
        	UseCase1LoginScherm ls = new UseCase1LoginScherm(controller, amount);
            Scene scene = new Scene(ls);
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        catch (NumberFormatException | BuitenBereikException e)
        {
            txfAmount.clear();
            lblMessage.setText(controller.getMessages("askNrUsersError"));
        }
	}
}
