package gui;

import java.io.IOException;

import domein.DomeinController;
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
	}
	
	private void buildGui() throws RuntimeException
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("UseCase3ActiePaneel.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			
	        btnOk.setOnAction(evt -> 
	        {
	        	parent.knoppenPaneel();      
	        	// of dit parent.getChildren().remove(this);
	        }
	        );
	        
	        
		}
		catch(IOException e)
		{
			throw new RuntimeException("Het scherm kan niet geladen worden");
		}
	}
}
