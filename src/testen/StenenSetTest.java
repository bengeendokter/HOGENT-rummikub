package testen;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import domein.Steen;
import domein.StenenSet;
import exceptions.GeenSerieOfRijException;


class StenenSetTest
{
	private boolean geslaagd;
	
	private StenenSet stringToStenenSet(String setString)
	{
		String[] stenenStringArray = setString.split("-");
		List<Steen> stenenList = new ArrayList<>();
		
		for(String steenString : stenenStringArray)
		{
			Steen steen;
			if(steenString.equals("JOK"))
			{
				steen = new Steen(true);
			}
			else
			{
				String kleur = "";
				switch(steenString.charAt(0))
				{
					case 'B':
						kleur = "blauw";
						break;
					case 'G':
						kleur = "geel";
						break;
					case 'R':
						kleur = "rood";
						break;
					case 'Z':
						kleur = "zwart";
						break;
				}
				
				int getalWaarde = Integer.parseInt(steenString.substring(1, 3));
				
				steen = new Steen(getalWaarde, kleur);
			}
			stenenList.add(steen);
		}
		
		return new StenenSet(stenenList);
	}
	
	private void controleerSetString(String setString)
	{
		stringToStenenSet(setString).controleerSet();
	}

// controleerSet
	@Test
	void controleerSet_teWeinigStenen_gooiGeenSerieOfRijException()
	{
		Assertions.assertThrows(GeenSerieOfRijException.class, () -> controleerSetString("G03-G04"));
	}
	
	@Test
	void controleerSet_geenSerieOfRij_gooiGeenSerieOfRijException()
	{
		Assertions.assertThrows(GeenSerieOfRijException.class, () -> controleerSetString("G03-B08-G11-R09-Z06"));
	}
	
	// rij controles
	@Test
	void controleerSet_grensDrieStenenRij_doeNiets()
	{
		geslaagd = true;
		
		try
		{
			controleerSetString("G03-G04-G05");
		}
		catch(Exception e)
		{
			geslaagd = false;
		}
		Assertions.assertTrue(geslaagd);
	}
	
	@Test
	void controleerSet_rijMetTweeJokersInBegin_doeNiets()
	{
		geslaagd = true;
		
		try
		{
			controleerSetString("JOK-JOK-G05-G06");
		}
		catch(Exception e)
		{
			geslaagd = false;
		}
		Assertions.assertTrue(geslaagd);
	}
	
	@Test
	void controleerSet_rijMetTweeJokersInBeginVoorGetalWaarde2_gooiGeenSerieOfRijException()
	{
		Assertions.assertThrows(GeenSerieOfRijException.class, () -> controleerSetString("JOK-JOK-G02-G03"));
	}
	
	@Test
	void controleerSet_rijMetJokersInBegin_doeNiets()
	{
		geslaagd = true;
		
		try
		{
			controleerSetString("JOK-Z08-Z09");
		}
		catch(Exception e)
		{
			geslaagd = false;
		}
		Assertions.assertTrue(geslaagd);
	}
	
	@Test
	void controleerSet_rijMetJokerInBeginVoorGetalWaarde1_gooiGeenSerieOfRijException()
	{
		Assertions.assertThrows(GeenSerieOfRijException.class, () -> controleerSetString("JOK-G01-G02-G03"));
	}
	
	@Test
	void controleerSet_rijMetTweeJokersOpEinde_doeNiets()
	{
		geslaagd = true;
		
		try
		{
			controleerSetString("B08-B09-JOK-JOK");
		}
		catch(Exception e)
		{
			geslaagd = false;
		}
		Assertions.assertTrue(geslaagd);
	}
	
	@Test
	void controleerSet_rijMetTweeJokersOpEindeNaGetalWaarde12_gooiGeenSerieOfRijException()
	{
		Assertions.assertThrows(GeenSerieOfRijException.class, () -> controleerSetString("B11-B12-JOK-JOK"));
	}
	
	@Test
	void controleerSet_rijAflopend_doeNiets()
	{
		geslaagd = true;
		
		try
		{
			controleerSetString("G11-G10-G09-G08");
		}
		catch(Exception e)
		{
			geslaagd = false;
		}
		Assertions.assertTrue(geslaagd);
	}
	
	@Test
	void controleerSet_rijMetTweeJokersOpEindeNaGetalWaarde12Aflopend_gooiGeenSerieOfRijException()
	{
		Assertions.assertThrows(GeenSerieOfRijException.class, () -> controleerSetString("JOK-JOK-B12-B11"));
	}
	
	@Test
	void controleerSet_rijMetTweeJokersInBeginAflopend_doeNiets()
	{
		geslaagd = true;
		
		try
		{
			controleerSetString("G11-G10-G09-G08-JOK-JOK");
		}
		catch(Exception e)
		{
			geslaagd = false;
		}
		Assertions.assertTrue(geslaagd);
	}
	
	// serie controles
	@Test
	void controleerSet_grensDrieStenenSerie_doeNiets()
	{
		geslaagd = true;
		
		try
		{
			controleerSetString("G08-R08-Z08");
		}
		catch(Exception e)
		{
			geslaagd = false;
		}
		Assertions.assertTrue(geslaagd);
	}
	
	@Test
	void controleerSet_serieMetVijfStenen_gooiGeenSerieOfRijException()
	{
		Assertions.assertThrows(GeenSerieOfRijException.class, () -> controleerSetString("B04-G04-Z04-R04-JOK"));
	}
	
	@Test
	void controleerSet_serieMetTweeJokersInBegin_doeNiets()
	{
		geslaagd = true;
		
		try
		{
			controleerSetString("JOK-JOK-R08-Z08");
		}
		catch(Exception e)
		{
			geslaagd = false;
		}
		Assertions.assertTrue(geslaagd);
	}
	
	@Test
	void controleerSet_serieMetDubbeleKleur_gooiGeenSerieOfRijException()
	{
		Assertions.assertThrows(GeenSerieOfRijException.class, () -> controleerSetString("B04-G04-B04"));
	}
}
