package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;

import javafx.scene.control.Label;

import javafx.scene.control.RadioButton;

public class UseCase3ActiePaneel extends GridPane
{
	@FXML
	private Label lblActieNaam;
	@FXML
	private Label lblDoel;
	@FXML
	private RadioButton radioDoelWv;
	@FXML
	private ToggleGroup doel;
	@FXML
	private RadioButton radioDoelGv;
	@FXML
	private Label lblBron;
	@FXML
	private RadioButton radioBronWv;
	@FXML
	private ToggleGroup bron;
	@FXML
	private RadioButton radioBronSpeler;
	@FXML
	private RadioButton radioBronGv;
	@FXML
	private Label lblDoelRij;
	@FXML
	private TextField txfDoelRij;
	@FXML
	private Label lblDoelKolom;
	@FXML
	private TextField txfDoelKolom;
	@FXML
	private Label lblBronRij;
	@FXML
	private TextField txfBronRij;
	@FXML
	private Label lblBronKolom;
	@FXML
	private TextField txfBronKolom;
	@FXML
	private Button btnOk;
	@FXML
	private Button btnGaTerug;
	@FXML
	private Label lblMelding;
	
	private DomeinController controller;
	private UseCase3SpelOverzicht parent;
	
	public UseCase3ActiePaneel(DomeinController controller, UseCase3SpelOverzicht parent)
	{
		this.controller = controller;
		buildGui();
		this.parent = parent;
		Platform.runLater(() -> txfDoelRij.requestFocus());
	}
	
	private void buildGui() throws RuntimeException
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("UseCase3ActiePaneel.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			
			btnGaTerug.setOnAction(evt -> {
				parent.keuzePaneel();
			});
			
		}
		catch(IOException e)
		{
			throw new RuntimeException("Het scherm kan niet geladen worden");
		}
	}
	
	// TODO bepaal begin focus, momenteel is dit taal knop en dit is slecht
	
	public void legAan()
	{
		// activeer de juiste radio knoppen
		radioDoelGv.setDisable(false);
		radioDoelWv.setDisable(false);
		radioBronWv.setDisable(false);
		radioBronSpeler.setDisable(false);
		
		// selecteer alvast de meest logische keuzes
		radioDoelGv.setSelected(true);
		radioBronSpeler.setSelected(true);
		// en activeer de bijhorende velden
		enableDoelVelden();
		txfBronRij.setDisable(false);
		
		// bepaal wat er met BronVelden gebeurt als er een BronRadio wordt aangeklikt
		// niet nodig voor DoelVelden, die staan altijd beide aan
		radioBronWv.setOnAction(evt -> {
			enableBronVelden();
		});
		
		radioBronSpeler.setOnAction(evt -> {
			disableBronVelden();
			txfBronRij.setDisable(false);
			txfBronKolom.clear();
		});
		
		// implementeer de OK knop
		btnOk.setOnAction(evt -> {
//        	controleerVelden()
//        	
//        	controller.legSteenAan(...);    
			if(doel.getSelectedToggle().equals(radioDoelGv))
			{
				System.out.println("gv");
			}
			else
			{
				System.out.println("wv");
			}
		});
	}
	
	public void splits()
	{
		// activeer de juiste radio knoppen
		radioDoelGv.setDisable(false);
		radioDoelWv.setDisable(false);
		
		// selecteer alvast de meest logische keuzes
		radioDoelGv.setSelected(true);
		// en activeer de bijhorende velden
		enableDoelVelden();
		
		// implementeer de OK knop
		btnOk.setOnAction(evt -> {
//		        	controleerVelden()
//		        	
//		        	controller.legSteenAan(...);    
			if(doel.getSelectedToggle().equals(radioDoelGv))
			{
				System.out.println("gv");
			}
			else
			{
				System.out.println("wv");
			}
		});
	}
	
	public void joker()
	{
		// activeer de juiste radio knoppen
		radioDoelGv.setDisable(false);
		radioDoelWv.setDisable(false);
		radioBronWv.setDisable(false);
		radioBronSpeler.setDisable(false);
		
		// selecteer alvast de meest logische keuzes
		radioDoelGv.setSelected(true);
		radioBronSpeler.setSelected(true);
		// en activeer de bijhorende velden
		enableDoelVelden();
		txfBronRij.setDisable(false);
		
		// bepaal wat er met BronVelden gebeurt als er een BronRadio wordt aangeklikt
		// niet nodig voor DoelVelden, die staan altijd beide aan
		radioBronWv.setOnAction(evt -> {
			enableBronVelden();
		});
		
		radioBronSpeler.setOnAction(evt -> {
			disableBronVelden();
			txfBronRij.setDisable(false);
			txfBronKolom.clear();
		});
		
		// implementeer de OK knop
		btnOk.setOnAction(evt -> {
//		        	controleerVelden()
//		        	
//		        	controller.legSteenAan(...);    
			if(doel.getSelectedToggle().equals(radioDoelGv))
			{
				System.out.println("gv");
			}
			else
			{
				System.out.println("wv");
			}
		});
	}
	
	public void werkVeld()
	{
		// activeer de juiste radio knoppen
		radioDoelWv.setDisable(false);
		radioBronGv.setDisable(false);
		
		// selecteer alvast de meest logische keuzes
		radioDoelWv.setSelected(true);
		radioBronGv.setSelected(true);
		// en activeer de bijhorende velden
		enableDoelVelden();
		enableBronVelden();
				
		// implementeer de OK knop
		btnOk.setOnAction(evt -> {
//		        	controleerVelden()
//		        	
//		        	controller.legSteenAan(...);    
			if(doel.getSelectedToggle().equals(radioDoelGv))
			{
				System.out.println("wv");
			}
		});
	}
	
	private void enableDoelVelden()
	{
		txfDoelRij.setDisable(false);
		txfDoelKolom.setDisable(false);
	}
	
	private void disableBronVelden()
	{
		txfBronRij.setDisable(true);
		txfBronKolom.setDisable(true);
	}
	
	private void enableBronVelden()
	{
		txfBronRij.setDisable(false);
		txfBronKolom.setDisable(false);
	}
}
