package gui;

import java.util.List;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class UseCase1GebruikersLijstScherm extends VBox
{
	private Label lblUserList;
	private Label lblUser1;
	private Label lblUser2;
	private Label lblUser3;
	private Label lblUser4;
	
	private DomeinController controller;
	
	public UseCase1GebruikersLijstScherm(DomeinController controller)
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
        lblUser1 = new Label();
        lblUser2 = new Label();
        lblUser3 = new Label();
        lblUser4 = new Label();
		this.getChildren().addAll(lblUserList, lblUser1, lblUser2, lblUser3, lblUser4);
	}
	
	private void buildText()
	{
		lblUserList.setText(controller.getMessages("lijstNamen"));

		
		List<String> namen = controller.geefLijstGebruikersnaam();
		
		if(namen.size() < 3)
		{
			namen.add("");
		}
		if(namen.size() < 4)
		{
			namen.add("");
		}
		
		lblUser1.setText(namen.get(0));
		lblUser2.setText(namen.get(1));
		lblUser3.setText(namen.get(2));
		lblUser4.setText(namen.get(3));
	}
}
