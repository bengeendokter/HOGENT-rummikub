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
	        	parent.keuzePaneel();      
	        });
	        
	        btnGaTerug.setOnAction(evt -> 
	        {
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
		radioDoelGv.setDisable(false);
		radioDoelWv.setDisable(false);
		radioBronWv.setDisable(false);
		radioBronSpeler.setDisable(false);
		
		radioDoelGv.setSelected(true);
		
		radioDoelGv.setOnAction(evt -> 
	        {
	        	disableDoel();
	        	txfDoelRij.setDisable(false);
	        	txfDoelKolom.setDisable(false);
	        });
		
		radioDoelWv.setOnAction(evt -> 
        {
        	disableDoel();
        	txfDoelRij.setDisable(false);
        	txfDoelKolom.setDisable(false);
        });
		
		radioBronWv.setOnAction(evt -> 
        {
        	disableBron();
        	txfBronRij.setDisable(false);
        	txfBronKolom.setDisable(false);
        });
		
		radioBronSpeler.setOnAction(evt -> 
        {
        	disableBron();
        	txfBronRij.setDisable(false);
        });
        btnOk.setOnAction(evt -> 
        {
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
		// TODO Auto-generated method stub
		
	}

	public void joker()
	{
		// TODO Auto-generated method stub
		
	}

	public void werkVeld()
	{
		// TODO Auto-generated method stub
		
	}
	
	private void disableDoel()
	{
		txfDoelRij.setDisable(true);
		txfDoelKolom.setDisable(true);
	}
	
	private void disableBron()
	{
		txfBronRij.setDisable(true);
		txfBronKolom.setDisable(true);
	}
}
