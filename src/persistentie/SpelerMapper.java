package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import domein.Speler;
import exceptions.SpelerNietGevondenException;

public class SpelerMapper
{

	public Speler geefSpeler(String gebruikersnaam, String wachtwoord)
	{
		Speler speler = null;

		try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
				PreparedStatement query = conn
						.prepareStatement
						(
								String.format
								(
										"SELECT * FROM %s.Speler"
										+ " WHERE gebruikersnaam = ? AND wachtwoord = ?"
										,Connectie.USER
								)
						)
			)
		{
			query.setString(1, gebruikersnaam);
			query.setString(2, wachtwoord);

			try (ResultSet rs = query.executeQuery())
			{

				if (rs.next())
				{
					// String wachtwoord = rs.getString("wachtwoord");
					speler = new Speler(gebruikersnaam, wachtwoord);
				}
				else // Speler niet gevonden
				{
					throw new SpelerNietGevondenException();
				}
			}
		}
		catch (CommunicationsException e) // waarschijnlijk geen internetverbinding
		{
			throw new RuntimeException("Kan geen verbinding maken met de online databank"
										+ ", contoleer uw internet verbinding");
		}
		catch(SQLException e) // er is een probleem opgetreden
		{
			throw new RuntimeException("Er is een probleem opgetreden"
										+ ", er zit waarschijnlijk een fout in de SQL syntax of de Connectie klasse");
		}
		return speler;
	}
}
