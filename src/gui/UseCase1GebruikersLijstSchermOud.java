package gui;

import java.util.List;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class UseCase1GebruikersLijstSchermOud extends VBox
{
	private Label lblUserList;
	private Label lblUsers;
	
	private DomeinController controller;
	
	public UseCase1GebruikersLijstSchermOud(DomeinController controller)
	{
		this.controller = controller;
		buildGui();
		buildText();
	}


	private void buildGui()
	{	
    	this.setMinSize(230, 150);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(25));
		
		lblUserList = new Label();
        lblUsers = new Label();
		this.getChildren().addAll(lblUserList, lblUsers);
	}
	
	private void buildText()
	{
		lblUserList.setText(controller.getMessages("lijstNamen"));

		List<String> gebruikersnamen = controller.geefLijstGebruikersnaam();
		String lijst = String.join("\n", gebruikersnamen);
		
		lblUsers.setText(lijst);
	}
}
