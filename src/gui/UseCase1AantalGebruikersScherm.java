package gui;


import domein.DomeinController;
import exceptions.BuitenBereikException;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class UseCase1AantalGebruikersScherm extends GridPane
{	
    private Label lblTitle;
    private Label lblAmount;
    private Button btnTaal;
    private TextField txfAmount;
    private Button btnAmount;
    private Label lblMessage;
	
	private final DomeinController controller;

	public UseCase1AantalGebruikersScherm(DomeinController controller)
	{
		this.controller = controller;
    	buildGui();
    	buildText();
	}


	private void buildGui()
	{
		this.setMinSize(400, 175);    
        this.setAlignment(Pos.CENTER);
        this.setHgap(10); 
        this.setVgap(10);		
        this.setPadding(new Insets(25));
        
        lblTitle = new Label();
        lblTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
        this.add(lblTitle, 0, 0);
        
        btnTaal = new Button();
        setHalignment(btnTaal, HPos.RIGHT);
        this.add(btnTaal, 1, 0);
        btnTaal.setOnAction(this::TaalPushed);
        
        lblAmount = new Label();
        setHalignment(lblAmount, HPos.CENTER);
        this.add(lblAmount, 0, 1, 2, 1);
        
        txfAmount = new TextField();
        setHalignment(txfAmount, HPos.CENTER);
        txfAmount.setMaxWidth(Double.MAX_VALUE);
        this.add(txfAmount, 0, 2, 2, 1);
        
        btnAmount = new Button();
	    setHalignment(btnAmount, HPos.CENTER);
	    this.add(btnAmount, 0, 3, 2, 1);
	    btnAmount.setOnAction(this::AmountPushed);
	    
	    lblMessage = new Label();
        setHalignment(lblMessage, HPos.CENTER);
        this.add(lblMessage, 0, 4, 2, 1);
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
