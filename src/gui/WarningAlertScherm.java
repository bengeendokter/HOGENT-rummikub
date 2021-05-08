package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class WarningAlertScherm
{
	public static void toonAlert()
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error!");
		alert.setHeaderText("Er is een onbekend probleem opgetreden\n"
							+ "An unknown problem has occurred");
		alert.setContentText("Contacteer de ontwikkelaars als dit probleem blijft optreden\n"
							+ "Contact the developers if this problem keeps occurring");
		alert.show();
	}
}
