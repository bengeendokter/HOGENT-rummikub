package gui;


import domein.DomeinController;
import exceptions.ReedsAangemeldException;
import exceptions.SpelerNietGevondenException;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

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
     
    private int currentUserIndex = 1;
    private int numberOfUsers;
    private final DomeinController controller;
    
    
    public UseCase1LoginScherm(DomeinController controller, int numberOfUsers)
    {
		this.controller = controller;
		this.numberOfUsers = numberOfUsers;
    	buildGui();
    	buildText();
    }
    
    
	private void buildGui()
    {
// breedte is 300, hoogte is 275
    	this.setMinSize(300, 200);
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
        	lblMessage.setText(controller.getMessages("msgCancel"));
        	txfUser.clear();
        	pwfPassWord.clear();
        }
        );
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
    }


	private void volgendScherm()
	{
		UseCase1GebruikersLijstScherm gl = new UseCase1GebruikersLijstScherm(controller);
        Scene scene = new Scene(gl);
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
	}
}
