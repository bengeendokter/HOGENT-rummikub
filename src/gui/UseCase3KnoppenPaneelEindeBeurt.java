package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UseCase3KnoppenPaneelEindeBeurt extends VBox implements UseCase3HasText
{
	@FXML
	private Label lblMelding;
	@FXML
	private Button btnOk;
	
	private DomeinController controller;
	private UseCase3SpelOverzicht parent;
	
	public UseCase3KnoppenPaneelEindeBeurt(DomeinController controller, UseCase3SpelOverzicht parent)
	{
		try
		{
			this.controller = controller;
			this.parent = parent;
			
			buildGui();
			buildText();
			Platform.runLater(() -> btnOk.requestFocus());
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
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("UseCase3KnoppenPaneelEindeBeurt.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			
			btnOk.setOnAction(this::toonStartScherm);
		}
		catch(IOException e)
		{
			throw new RuntimeException("Het scherm kan niet geladen worden");
		}
	}
	
	private void toonStartScherm(ActionEvent evt)
	{
    	try
		{
			parent.startPaneel();
			parent.updateGui();
		}
		catch(Exception e)
		{
			WarningAlertScherm.toonAlert();
		}
	}
	
	@Override
	public void buildText()
	{
		lblMelding.setText(controller.getMessages("msgEindeBeurt"));
		btnOk.setText(controller.getMessages("btnOk"));
	}
}
