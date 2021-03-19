package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.DomeinController;
import exceptions.BuitenBereikException;


class DomeinControllerTest
{
	private DomeinController dc;
	private boolean geslaagd;
	
	@BeforeEach
	public void before()
	{
		dc = new DomeinController();
	}
	
// registreerAantal
	@Test
	void registreerAantal_teLaag1_gooiBuitenBereikException()
	{
		Assertions.assertThrows(BuitenBereikException.class,  () -> {dc.registreerAantal(1);});
	}
	
	@Test
	void registreerAantal_grensGeval2_doeNiets()
	{	
		geslaagd = true;
		
		try
		{
			dc.registreerAantal(2);
		}
		catch(Exception e)
		{
			geslaagd = false;
		}
		Assertions.assertTrue(geslaagd);
	}
	
	@Test
	void registreerAantal_geldigGeval3_doeNiets()
	{	
		geslaagd = true;
		
		try
		{
			dc.registreerAantal(3);
		}
		catch(Exception e)
		{
			geslaagd = false;
		}
		Assertions.assertTrue(geslaagd);
	}
	
	@Test
	void registreerAantal_grensGeval4_doeNiets()
	{	
		geslaagd = true;
		
		try
		{
			dc.registreerAantal(4);
		}
		catch(Exception e)
		{
			geslaagd = false;
		}
		Assertions.assertTrue(geslaagd);
	}
	
	@Test
	void registreerAantal_teHoog7_gooiBuitenBereikException()
	{
		Assertions.assertThrows(BuitenBereikException.class,  () -> {dc.registreerAantal(7);});
	}
	
// meldAan
	@Test
	void meldAan_geldigGeval_doeNiets()
	{	
		geslaagd = true;
		
		try
		{
			dc.meldAan("IkBenBen", "IkBenDokter");
		}
		catch(Exception e)
		{
			geslaagd = false;
		}
		Assertions.assertTrue(geslaagd);
	}
	
	@Test
	void meldAan_fouteGebruikersnaam_gooiRuntimeException()
	{
		Assertions.assertThrows(RuntimeException.class,  () -> {dc.meldAan("bestaatNiet", "IkBenDokter");});
	}
	
	@Test
	void meldAan_foutWachtwoord_gooiRuntimeException()
	{
		Assertions.assertThrows(RuntimeException.class,  () -> {dc.meldAan("IkBenBen", "fout");});
	}
	
	@Test
	void meldAan_reedsAangemeld_gooiReedsAangemeldException()
	{
		dc.meldAan("IkBenBen", "IkBenDokter");
		
		Assertions.assertThrows(RuntimeException.class,  () -> {dc.meldAan("IkBenBen", "IkBenDokter");});
	}
}
