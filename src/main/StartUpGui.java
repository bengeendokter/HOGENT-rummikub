package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
//import gui.UseCase1AantalGebruikersScherm;
import gui.UseCase3SpelOverzicht;
import gui.WarningAlertScherm;
import domein.DomeinController;

public class StartUpGui extends Application
{
	@SuppressWarnings("exports")
	@Override
    public void start(Stage primaryStage)
    {
		try
		{
			DomeinController controller = new DomeinController();
//		UseCase1AantalGebruikersScherm sc = new UseCase1AantalGebruikersScherm(controller);
			UseCase3SpelOverzicht sc = new UseCase3SpelOverzicht(controller);

			Scene scene = new Scene(sc);
			primaryStage.setScene(scene);
			
			Image icon = new Image(getClass().getResource("/gui/images/icon.png").toExternalForm());
			primaryStage.getIcons().add(icon);

			primaryStage.setTitle("Rummikub");
			primaryStage.setResizable(false);
			primaryStage.show();
		}
		catch(Exception e)
		{
			WarningAlertScherm.toonAlert();
		}
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
