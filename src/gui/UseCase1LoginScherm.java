package gui;

import java.io.IOException;

import domein.DomeinController;
import exceptions.ReedsAangemeldException;
import exceptions.SpelerNietGevondenException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class UseCase1LoginScherm extends GridPane
{
	@FXML
	private Button btnSignIn;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnTaal;
	@FXML
	private Label lblTitle;
	@FXML
	private Label lblUserName;
	@FXML
	private Label lblPassWord;
	@FXML
	private Label lblMessage;
	@FXML
	private TextField txfUser;
	@FXML
	private PasswordField pwfPassWord;
	
    private int currentUserIndex = 1;
	private int numberOfUsers;
    private DomeinController controller;
    
    
    public UseCase1LoginScherm(DomeinController controller, int numberOfUsers) throws RuntimeException
    {
    	try
    	{
			this.controller = controller;
			this.numberOfUsers = numberOfUsers;
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
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("UseCase1LoginScherm.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			
	        btnTaal.setOnAction(this::TaalPushed);
	        btnSignIn.setOnAction(this::SignInPushed);
	        btnCancel.setOnAction(evt -> 
	        {
	        	lblMessage.setText(controller.getMessages("msgCancel"));
	        	txfUser.clear();
	        	pwfPassWord.clear();
	        }
	        );
	        
	        // wat er gebeurt als er op ENTER wordt gedrukt in een veld
	        txfUser.setOnAction(evt -> 
	        {
	        	pwfPassWord.requestFocus();
	        }
	        ); // ga naar volgend veld
	        pwfPassWord.setOnAction(this:: SignInPushed); // probeer in te loggen
	        
		}
		catch (IOException e)
		{
			throw new RuntimeException("Het scherm kan niet geladen worden");
		}		
	}
    
    private void buildText()
	{
     	lblTitle.setText(String.format(controller.getMessages("userIndex"), currentUserIndex));
    	btnTaal.setText(controller.getMessages("btnTaal"));
    	lblUserName.setText(controller.getMessages("lblUserName"));
    	lblPassWord.setText(controller.getMessages("lblPassWord"));
        btnSignIn.setText(controller.getMessages("btnSignIn"));
        btnCancel.setText(controller.getMessages("btnCancel"));
        lblMessage.setText("");
	}
    
// Event-afhandeling: wat er moet gebeuren als we op de knop Taal in klikken   
    private void TaalPushed(ActionEvent event)
    {
    	controller.veranderTaal();
    	buildText();
    }
    

    private void SignInPushed(ActionEvent event)
    {
        String gebruikersnaam = txfUser.getText();
        String wachtwoord = pwfPassWord.getText();
        
    	try
    	{	
            controller.meldAan(gebruikersnaam, wachtwoord);
        	txfUser.clear();
        	pwfPassWord.clear();
        	
        	if(currentUserIndex == numberOfUsers)
    		{
    		volgendScherm();
    		}
        	
        	currentUserIndex++;
        	txfUser.requestFocus();
        	buildText();
        	lblMessage.setText(controller.getMessages("msgSignIn"));
    	}
    	catch(ReedsAangemeldException e)
    	{
    		lblMessage.setText(controller.getMessages("reedsAangemeld"));
    	}
		catch(SpelerNietGevondenException e)
		{
			lblMessage.setText(controller.getMessages("msgSignInFailed"));				
		}
    	catch(RuntimeException e)
    	{
    		lblMessage.setText(controller.getMessages("msgConnectionFailed"));
    	}
		catch(Exception e)
		{
			WarningAlertScherm.toonAlert();
		}
    }

	private void volgendScherm()
	{
		try
		{
			UseCase1GebruikersLijstScherm sc = new UseCase1GebruikersLijstScherm(controller);
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
