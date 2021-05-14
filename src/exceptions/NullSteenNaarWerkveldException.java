package exceptions;

@SuppressWarnings("serial")
public class NullSteenNaarWerkveldException extends IllegalArgumentException
{
	public NullSteenNaarWerkveldException()
	{
		super("De steen die je wilt verplaatsen naar het werkveld bestaat niet");
	}
	
	public NullSteenNaarWerkveldException(String s)
	{
		super(s);
	}
}