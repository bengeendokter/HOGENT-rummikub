package cui;

import domein.DomeinController;

public class UseCase2Applicatie
{
	private final DomeinController controller;
	
	public UseCase2Applicatie(DomeinController controller)
	{
		this.controller = controller;
		
		// TODO Onderstaande code vervult precondities
		// verander uiteindelijk StartUp terug en roep  na testen UC2 aan via UC1
			this.controller.meldAan("IkBenBen", "IkBenDokter");
			this.controller.meldAan("mns58", "myDiscordPassword");
			// this.controller.meldAan("IceBergUser58", "hogenthogent123");
			// this.controller.meldAan("TUF", "Thangz");
	}

}
