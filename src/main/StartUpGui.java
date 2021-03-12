package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import gui.UseCase1LoginScherm;
import domein.DomeinController;

public class StartUpGui extends Application
{
	@SuppressWarnings("exports")
	@Override
    public void start(Stage primaryStage)
    {
		DomeinController controller = new DomeinController();
        UseCase1LoginScherm grid = new UseCase1LoginScherm(controller);

        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Rummikub Login");
        primaryStage.setResizable(false);
        primaryStage.show();


    }

    public static void main(String[] args)
    {
        launch(args);
    }
}