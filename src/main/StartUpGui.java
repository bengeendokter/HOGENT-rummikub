package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import gui.UseCase1AantalGebruikersScherm;
import gui.UseCase3SpelOverzicht;
import domein.DomeinController;

public class StartUpGui extends Application
{
	@SuppressWarnings("exports")
	@Override
    public void start(Stage primaryStage)
    {
//		try
//		{
		DomeinController controller = new DomeinController();
		// UseCase1AantalGebruikersScherm grid = new UseCase1AantalGebruikersScherm(controller);
		UseCase3SpelOverzicht grid = new UseCase3SpelOverzicht(controller);

        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        
        Image icon = new Image(getClass().getResource("/gui/images/icon.png").toExternalForm());
        primaryStage.getIcons().add(icon);

        primaryStage.setTitle("Rummikub");
        primaryStage.setResizable(false);
        primaryStage.show();
//		}
//		catch(Exception e)
//		{
//			Alert alert = new Alert(AlertType.WARNING);
//			alert.setTitle("Fout!");
//			alert.setHeaderText("Het scherm kan niet geladen worden\n"
//								+ "The screen cannot be loaded");
//			alert.setContentText("Contacteer de ontwikkelaars als dit probleem blijft optreden\n"
//								+ "Contact the developers if this problem keeps occurring");
//			alert.show();
//		}
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
