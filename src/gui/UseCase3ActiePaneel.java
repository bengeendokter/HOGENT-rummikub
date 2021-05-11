package gui;

import java.io.IOException;

import domein.DomeinController;
import exceptions.FouteEersteZetException;
import exceptions.FoutePositieException;
import exceptions.GeenPlaatsOpRijException;
import exceptions.GeenSerieOfRijException;
import exceptions.GeenSpelerSteenOpPlaats;
import exceptions.OngeldigInvoerException;
import exceptions.SteenIsGeenJokerException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;

import javafx.scene.control.Label;

import javafx.scene.control.RadioButton;

public class UseCase3ActiePaneel extends GridPane implements UseCase3HasText
{
	@FXML
	private Label lblActieNaam;
	@FXML
	private Label lblDoel;
	@FXML
	private RadioButton radioDoelWv;
	@FXML
	private ToggleGroup doel;
	@FXML
	private RadioButton radioDoelGv;
	@FXML
	private Label lblBron;
	@FXML
	private RadioButton radioBronWv;
	@FXML
	private ToggleGroup bron;
	@FXML
	private RadioButton radioBronSpeler;
	@FXML
	private RadioButton radioBronGv;
	@FXML
	private Label lblDoelRij;
	@FXML
	private TextField txfDoelRij;
	@FXML
	private Label lblDoelKolom;
	@FXML
	private TextField txfDoelKolom;
	@FXML
	private Label lblBronRij;
	@FXML
	private TextField txfBronRij;
	@FXML
	private Label lblBronKolom;
	@FXML
	private TextField txfBronKolom;
	@FXML
	private Button btnOk;
	@FXML
	private Button btnGaTerug;
	@FXML
	private Label lblMelding;
	
	private DomeinController controller;
	private UseCase3SpelOverzicht parent;
	private String actie;
	
	private int[] positieDoel;
	private boolean doelIsWv;
	private int[] positieBron;
	private boolean bronIsWv;
	
	public UseCase3ActiePaneel(DomeinController controller, UseCase3SpelOverzicht parent)
	{
		try
		{
			this.controller = controller;
			this.parent = parent;
			this.actie = "actie";
			
			buildGui();
			buildText();
			Platform.runLater(() -> txfDoelRij.requestFocus());
		}
		catch(Exception e)
		{
			WarningAlertScherm.toonAlert();
		}
	}
	
	private void buildGui() throws RuntimeException
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("UseCase3ActiePaneel.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			
			btnGaTerug.setOnAction(evt -> {
				parent.keuzePaneel();
			});
			
			// wat er gebeurt als er op ENTER wordt gedrukt in een veld
			txfDoelRij.setOnAction(evt -> {
				txfDoelKolom.requestFocus();
			}); // ga naar volgend veld
			txfDoelKolom.setOnAction(evt -> {
				txfBronRij.requestFocus();
			});
			txfBronRij.setOnAction(evt -> {
				txfBronKolom.requestFocus();
			});
			
		}
		catch(IOException e)
		{
			throw new RuntimeException("Het scherm kan niet geladen worden");
		}
	}
	
	public void legAan()
	{
		// bepaal welke actie dit is voor de buildText methode
		actie = "legAan";
		buildText();
		
		// activeer de juiste radio knoppen
		radioDoelGv.setDisable(false);
		radioDoelWv.setDisable(false);
		radioBronWv.setDisable(false);
		radioBronSpeler.setDisable(false);
		
		// selecteer alvast de meest logische keuzes
		radioDoelWv.setSelected(true);
		radioBronSpeler.setSelected(true);
		// en activeer de bijhorende velden
		enableDoelVelden();
		txfBronRij.setDisable(false);
		// aangezien logische keuze afwijkt van wat er met velden normaal gebeurt stellen we dit direct in
		txfBronRij.setOnAction(this::actieLegAan); // hetzelfde als OK knop
		
		// bepaal wat er met BronVelden gebeurt als er een de bron wordt veranderd via tabs en toetsenbord
		this.setOnKeyReleased(evt -> 
		{
			RadioButton selected = (RadioButton) bron.getSelectedToggle();
			if(selected.equals(radioBronSpeler))
			{
				disableBronVelden();
				txfBronRij.setDisable(false);
				txfBronKolom.clear();
				
				// wat er gebeurt als er op ENTER wordt gedrukt in een veld
				txfBronRij.setOnAction(this::actieLegAan); // hetzelfde als OK knop
			}
			else
			{
				enableBronVelden();
				
				// wat er gebeurt als er op ENTER wordt gedrukt in een veld
				txfBronRij.setOnAction(evt2 -> {
					txfBronKolom.requestFocus();
				});
				txfBronKolom.setOnAction(this::actieLegAan); // hetzelfde als OK knop
			}
		});
		
		// bepaal wat er met BronVelden gebeurt als er een BronRadio wordt aangeklikt
		// niet nodig voor DoelVelden, die staan altijd beide aan
		radioBronWv.setOnAction(evt -> {
			enableBronVelden();
			
			// wat er gebeurt als er op ENTER wordt gedrukt in een veld
			txfBronRij.setOnAction(evt2 -> {
				txfBronKolom.requestFocus();
			});
			txfBronKolom.setOnAction(this::actieLegAan); // hetzelfde als OK knop
		});
		
		radioBronSpeler.setOnAction(evt -> {
			disableBronVelden();
			txfBronRij.setDisable(false);
			txfBronKolom.clear();
			
			// wat er gebeurt als er op ENTER wordt gedrukt in een veld
			txfBronRij.setOnAction(this::actieLegAan); // hetzelfde als OK knop
		});
		
		// implementeer de OK knop
		btnOk.setOnAction(this::actieLegAan);
	}
	
	private void actieLegAan(ActionEvent evt)
	{
		try
		{
			leesVelden();
			controller.legSteenAan(positieDoel, doelIsWv, positieBron, bronIsWv);
			parent.updateGui();
		}
		catch(Exception e)
		{
			exceptionToMelding(e);
		}
	}
	
	public void splits()
	{
		// bepaal welke actie dit is voor de buildText methode
		actie = "splits";
		buildText();
		
		// activeer de juiste radio knoppen
		radioDoelGv.setDisable(false);
		radioDoelWv.setDisable(false);
		
		// selecteer alvast de meest logische keuzes
		radioDoelGv.setSelected(true);
		radioBronGv.setSelected(true);
		// en activeer de bijhorende veld(en)
		enableDoelVelden();
		
		// bij splits selecteren we de bron afhankelijk van het doel
		radioDoelGv.setOnAction(evt -> {
			radioBronGv.setSelected(true);
		});
		radioDoelWv.setOnAction(evt -> {
			radioBronWv.setSelected(true);
		});
		
		// wat er gebeurt als er op ENTER wordt gedrukt in een veld
		txfDoelKolom.setOnAction(this::actieSplits); // hetzelfde als OK knop
		
		// implementeer de OK knop
		btnOk.setOnAction(this::actieSplits);
	}
	
	private void actieSplits(ActionEvent evt)
	{
		try
		{
			leesVelden();
			controller.splitsRijOfSerie(positieDoel, doelIsWv);
			parent.updateGui();
		}
		catch(Exception e)
		{
			exceptionToMelding(e);
		}
	}
	
	public void joker()
	{
		// bepaal welke actie dit is voor de buildText methode
		actie = "joker";
		buildText();
		
		// activeer de juiste radio knoppen
		radioDoelGv.setDisable(false);
		radioDoelWv.setDisable(false);
		radioBronWv.setDisable(false);
		radioBronSpeler.setDisable(false);
		
		// selecteer alvast de meest logische keuzes
		radioDoelGv.setSelected(true);
		radioBronSpeler.setSelected(true);
		// en activeer de bijhorende velden
		enableDoelVelden();
		txfBronRij.setDisable(false);
		// aangezien logische keuze afwijkt van wat er met velden normaal gebeurt stellen we dit direct in
		txfBronRij.setOnAction(this::actieJoker); // hetzelfde als OK knop
		
		// bepaal wat er met BronVelden gebeurt als er een de bron wordt veranderd via tabs en toetsenbord
		this.setOnKeyReleased(evt -> 
		{
			RadioButton selected = (RadioButton) bron.getSelectedToggle();
			if(selected.equals(radioBronSpeler))
			{
				disableBronVelden();
				txfBronRij.setDisable(false);
				txfBronKolom.clear();
				
				// wat er gebeurt als er op ENTER wordt gedrukt in een veld
				txfBronRij.setOnAction(this::actieJoker); // hetzelfde als OK knop
			}
			else
			{
				enableBronVelden();
				
				// wat er gebeurt als er op ENTER wordt gedrukt in een veld
				txfBronRij.setOnAction(evt2 -> {
					txfBronKolom.requestFocus();
				});
				txfBronKolom.setOnAction(this::actieJoker); // hetzelfde als OK knop
			}
		});
		
		// bepaal wat er met BronVelden gebeurt als er een BronRadio wordt aangeklikt
		// niet nodig voor DoelVelden, die staan altijd beide aan
		radioBronWv.setOnAction(evt -> {
			enableBronVelden();
			
			// wat er gebeurt als er op ENTER wordt gedrukt in een veld
			txfBronRij.setOnAction(evt2 -> {
				txfBronKolom.requestFocus();
			});
			txfBronKolom.setOnAction(this::actieJoker); // hetzelfde als OK knop
		});
		
		radioBronSpeler.setOnAction(evt -> {
			disableBronVelden();
			txfBronRij.setDisable(false);
			txfBronKolom.clear();
			
			// wat er gebeurt als er op ENTER wordt gedrukt in een veld
			txfBronRij.setOnAction(this::actieJoker); // hetzelfde als OK knop
		});
		
		// implementeer de OK knop
		btnOk.setOnAction(this::actieJoker);
	}
	
	private void actieJoker(ActionEvent evt)
	{
		try
		{
			leesVelden();
			controller.vervangJoker(positieDoel, doelIsWv, positieBron, bronIsWv);
			parent.updateGui();
		}
		catch(Exception e)
		{
			exceptionToMelding(e);
		}
	}
	
	public void werkVeld()
	{
		// bepaal welke actie dit is voor de buildText methode
		actie = "werkVeld";
		buildText();
		
		// activeer de juiste radio knoppen
		radioDoelWv.setDisable(false);
		radioBronGv.setDisable(false);
		
		// selecteer alvast de meest logische keuzes
		radioDoelWv.setSelected(true);
		radioBronGv.setSelected(true);
		// en activeer de bijhorende velden
		enableDoelVelden();
		enableBronVelden();
		
		// wat er gebeurt als er op ENTER wordt gedrukt in laatste veld
		txfBronKolom.setOnAction(this::actieWerkveld); // hetzelfde als OK knop
		
		// implementeer de OK knop
		btnOk.setOnAction(this::actieWerkveld);
	}
	
	private void actieWerkveld(ActionEvent evt)
	{
		try
		{
			leesVelden();
			controller.verplaatsNaarWerkveld(positieDoel, positieBron);
			parent.updateGui();
		}
		catch(Exception e)
		{
			exceptionToMelding(e);
		}
	}

	private void enableDoelVelden()
	{
		txfDoelRij.setDisable(false);
		txfDoelKolom.setDisable(false);
	}
	
	private void disableBronVelden()
	{
		txfBronRij.setDisable(true);
		txfBronKolom.setDisable(true);
	}
	
	private void enableBronVelden()
	{
		txfBronRij.setDisable(false);
		txfBronKolom.setDisable(false);
	}
	
	@Override
	public void buildText()
	{
		lblActieNaam.setText(controller.getMessages(actie));
		lblDoel.setText(controller.getMessages("lblDoel"));
		lblBron.setText(controller.getMessages("lblBron"));
		radioDoelWv.setText(controller.getMessages("lblWv"));
		radioDoelGv.setText(controller.getMessages("lblGv"));
		radioBronWv.setText(controller.getMessages("lblWv"));
		radioBronSpeler.setText(controller.getMessages("lblSpelerStenen"));
		radioBronGv.setText(controller.getMessages("lblGv"));
		lblDoelRij.setText(controller.getMessages("lblDoelRij"));
		lblDoelKolom.setText(controller.getMessages("lblDoelKolom"));
		lblBronRij.setText(controller.getMessages("lblBronRij"));
		lblBronKolom.setText(controller.getMessages("lblBronKolom"));
		btnOk.setText(controller.getMessages("btnOk"));
		btnGaTerug.setText(controller.getMessages("btnGaTerug"));
		lblMelding.setText("");
	}
	
	private void leesVelden()
	{
		try
		{
			// get doel velden
			int doelRij = Integer.parseInt(txfDoelRij.getText());
			int doelKolom = Integer.parseInt(txfDoelKolom.getText());
			
			// get bron velden
			String bronRijTekst = txfBronRij.getText();
			String bronKolomTekst = txfBronKolom.getText();
			
			// TODO leeg veld gooit exception
			if(bronRijTekst.isBlank())
			{
				bronRijTekst = "1";
			}
			if(bronKolomTekst.isBlank())
			{
				bronKolomTekst = "1";
			}
			
			int bronRij = Integer.parseInt(bronRijTekst);
			int bronKolom = Integer.parseInt(bronKolomTekst);
			
			// get radio doel knop
			doelIsWv = doel.getSelectedToggle().equals(radioDoelWv);
			
			// get radio bron knop
			bronIsWv = bron.getSelectedToggle().equals(radioBronWv);
			
			// controleer of positie waarden minstens 1 zijn
			if(doelRij < 1 || doelKolom < 1 || bronRij < 1 || bronKolom < 1)
			{
				throw new IllegalArgumentException("De positie waarden moeten minstens 1 zijn");
			}
			
			// stel posities in
			// verlaag de indexen met 1 en stel de posities in
			positieDoel = new int[] {--doelRij, --doelKolom};
			positieBron = new int[] {--bronRij, --bronKolom};
		}
		catch(IllegalArgumentException e)
		{
			throw new OngeldigInvoerException();
		}
	}
	
	private void exceptionToMelding(Exception ex)
	{
		try
		{
			throw ex;
		}
		catch(OngeldigInvoerException e)
		{
			lblMelding.setText(controller.getMessages("msgOngeldigeInvoer"));
		}
		catch(FoutePositieException e)
		{
			lblMelding.setText(controller.getMessages("msgFoutePositie"));
		}
		catch(FouteEersteZetException e)
		{
			lblMelding.setText(controller.getMessages("msgFouteEersteZet"));
		}
		catch(GeenPlaatsOpRijException e)
		{
			lblMelding.setText(controller.getMessages("msgFouteEersteZet"));
		}
		catch(GeenSpelerSteenOpPlaats e)
		{
			lblMelding.setText(controller.getMessages("msgFouteSpelerSteen"));
		}
		catch(SteenIsGeenJokerException e)
		{
			lblMelding.setText(controller.getMessages("msgGeenJoker"));
		}
		catch(GeenSerieOfRijException e)
		{
			lblMelding.setText(controller.getMessages("msgGeenSerieOfRij"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			WarningAlertScherm.toonAlert();
		}
	}
}
