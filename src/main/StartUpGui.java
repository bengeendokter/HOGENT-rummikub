package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
//import gui.UseCase1AantalGebruikersScherm;
import gui.UseCase3SpelOverzicht;
import domein.DomeinController;

public class StartUpGui extends Application
{
	@SuppressWarnings("exports")
	@Override
    public void start(Stage primaryStage)
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

    public static void main(String[] args)
    {
        launch(args);
    }
}
