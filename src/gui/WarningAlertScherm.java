package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class WarningAlertScherm
{
	public static void toonAlert()
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Fout!");
		alert.setHeaderText("Het scherm kan niet geladen worden\n"
							+ "The screen cannot be loaded");
		alert.setContentText("Contacteer de ontwikkelaars als dit probleem blijft optreden\n"
							+ "Contact the developers if this problem keeps occurring");
		alert.show();
	}
}
