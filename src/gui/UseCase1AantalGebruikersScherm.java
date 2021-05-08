package gui;

import java.io.IOException;

import domein.DomeinController;
import exceptions.BuitenBereikException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
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
	
	private DomeinController controller;
	
	public UseCase1AantalGebruikersScherm(DomeinController controller) throws RuntimeException
	{
		try
		{
			this.controller = controller;
			buildGui();
			buildText();
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
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("UseCase1AantalGebruikersScherm.fxml"));
			
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			
	        btnTaal.setOnAction(this::TaalPushed);
		    btnAmount.setOnAction(this::AmountPushed);
		    txfAmount.setOnAction(this::AmountPushed);
		}
		catch (IOException e)
		{
			throw new RuntimeException("Het scherm kan niet geladen worden");
		}		
	}
	
	private void buildText()
	{
    	lblTitle.setText(controller.getMessages("lblTitle"));
		btnTaal.setText(controller.getMessages("btnTaal"));
    	lblAmount.setText(controller.getMessages("lblAmount"));
    	btnAmount.setText(controller.getMessages("btnOk"));
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
        	
        	UseCase1LoginScherm sc = new UseCase1LoginScherm(controller, amount);
            Scene scene = new Scene(sc);
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(scene);
            // centreer scherm
            stage.setX((Screen.getPrimary().getVisualBounds().getWidth() - sc.getWidth()) / 2);
            stage.setY((Screen.getPrimary().getVisualBounds().getHeight() - sc.getWidth()) / 2);
            stage.show();
        }
        catch (NumberFormatException | BuitenBereikException e)
        {
            txfAmount.clear();
            lblMessage.setText(controller.getMessages("askNrUsersError"));
        }
		catch(Exception e)
		{
			WarningAlertScherm.toonAlert();
		}
	}
}
