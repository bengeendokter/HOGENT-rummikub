package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import gui.UseCase1AantalGebruikersScherm;
import domein.DomeinController;

public class StartUpGui extends Application
{
	@SuppressWarnings("exports")
	@Override
    public void start(Stage primaryStage)
    {
		DomeinController controller = new DomeinController();
		//UseCase1AantalGebruikersSchermOud grid = new UseCase1AantalGebruikersSchermOud(controller);
		UseCase1AantalGebruikersScherm grid = new UseCase1AantalGebruikersScherm(controller);

        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Rummikub");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
