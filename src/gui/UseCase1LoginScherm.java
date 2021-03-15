package gui;

import java.util.Locale;
import java.util.ResourceBundle;

import domein.DomeinController;
import exceptions.ReedsAangemeldException;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UseCase1LoginScherm extends GridPane
{
// Dit attribuut hebben we in meerdere methoden nodig
	private Label lblTitle;
	private Button btnTaal;
	private Label lblUserName;
    private TextField txfUser;
    private Label lblPassWord;
    private PasswordField pwfPassWord;
    private Label lblMessage;
    private Button btnSignIn;
    private Button btnCancel;
    
    private final static ResourceBundle ENGELS =
    		ResourceBundle.getBundle("gui.ResourceBundleGui", new Locale("en"));
    private final static ResourceBundle NEDERLANDS =
    		ResourceBundle.getBundle("gui.ResourceBundleGui", new Locale("nl"));
	private ResourceBundle resourceBundle = NEDERLANDS;
    
    private final DomeinController controller;
    
    
    public UseCase1LoginScherm(DomeinController controller)
    {
		this.controller = controller;
    	buildGui();
    	buildText();
    }
    
    
	private void buildGui()
    {
// breedte is 300, hoogte is 275
    	this.setMinSize(300,  200);
// Aligneert grid in het midden        
        this.setAlignment(Pos.CENTER);
// Vrije ruimte tussen kolommen        
        this.setHgap(10);
// Vrije ruimte tussen rijen        
        this.setVgap(10);

// Vrije ruimte rond de randen van de grid (boven, rechts, onder, links)        
        this.setPadding(new Insets(25));
        lblTitle = new Label();
        lblTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
        
// Bij GridPane kan in elke cel een component geplaatst worden  
        this.add(lblTitle, 0, 0);
        
        
        btnTaal = new Button();
// We aligneren btnCancel rechts
        setHalignment(btnTaal, HPos.RIGHT);
        this.add(btnTaal, 1, 0);
 
// We koppelen een event handler aan de knop Taal
// We gebruiker hiervoor method reference  
        btnTaal.setOnAction(this::TaalPushed);
        

        lblUserName = new Label();
        this.add(lblUserName, 0, 1);

        txfUser = new TextField();
        this.add(txfUser, 1, 1);
        
        lblPassWord = new Label();
        this.add(lblPassWord, 0, 2);
        
        pwfPassWord = new PasswordField();
        this.add(pwfPassWord, 1, 2);
        
        btnSignIn = new Button();
// We aligneren btnSignIn links
        setHalignment(btnSignIn, HPos.LEFT);
        this.add(btnSignIn, 0, 3);

        btnCancel = new Button();
// We aligneren btnCancel rechts
        setHalignment(btnCancel, HPos.RIGHT);
        this.add(btnCancel, 1, 3);
        
        lblMessage = new Label();
        setHalignment(lblMessage, HPos.CENTER);
// Een component kan over meerdere rijen en/of kolommen geplaatst worden 
// De label wordt hier over 2 kolommen en 1 rij geplaatst  
        this.add(lblMessage, 0, 4, 2, 1);
      
        btnSignIn.setOnAction(this::SignInPushed);

// We koppelen een event handler aan de knop Cancel
// We gebruiken hiervoor een lambda expressie
        btnCancel.setOnAction(evt -> 
        {
        	lblMessage.setText(resourceBundle.getString("msgCancel"));
        	txfUser.clear();
        	pwfPassWord.clear();
        }
        );
    }
	
    private void buildText()
	{
     	lblTitle.setText(resourceBundle.getString("lblTitle"));
    	btnTaal.setText(resourceBundle.getString("btnTaal"));
    	lblUserName.setText(resourceBundle.getString("lblUserName"));
    	lblPassWord.setText(resourceBundle.getString("lblPassWord"));
        btnSignIn.setText(resourceBundle.getString("btnSignIn"));
        btnCancel.setText(resourceBundle.getString("btnCancel"));
        lblMessage.setText("");
	}
    
// Event-afhandeling: wat er moet gebeuren als we op de knop Taal in klikken   
    private void TaalPushed(ActionEvent event)
    {
    	resourceBundle = resourceBundle.equals(NEDERLANDS) ? ENGELS : NEDERLANDS;
    	buildText();
    }
    

    private void SignInPushed(ActionEvent event)
    {
        String gebruikersnaam = txfUser.getText();
        String wachtwoord = pwfPassWord.getText();
        
    	try
    	{	
            controller.meldAan(gebruikersnaam, wachtwoord);
        	lblMessage.setText(resourceBundle.getString("msgSignIn"));
    	}
    	catch(ReedsAangemeldException e)
    	{
    		lblMessage.setText(resourceBundle.getString("reedsAangemeld"));
    	}
    	catch(IllegalArgumentException e)
    	{
    		lblMessage.setText(resourceBundle.getString("msgPassWordIncorrect"));
    	}
    	catch(RuntimeException e)
    	{
    		lblMessage.setText(resourceBundle.getString("msgUserNotFound"));
    	}
    }
}
