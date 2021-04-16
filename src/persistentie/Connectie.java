package persistentie;

public class Connectie
{
	private final static String HOSTNAME = "ID343680_g16.db.webhosting.be";
	public final static String USER = "ID343680_g16";
	private final static String WACHTWOORD = "rummikubg16";
	
	
	public static final String JDBC_URL =
		String.format("jdbc:mysql://%s?serverTimezone=UTC&useLegacyDatetimeCode=false&user=%s&password=%s"
						,HOSTNAME
						,USER
						,WACHTWOORD);
}
