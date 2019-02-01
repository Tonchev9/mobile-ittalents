package allOfferClasses;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;

public class OldOffersCleaner extends Thread{

	private static final int OFFERS_DAYS_LIMIT = 35;
	private Set<Offer> offers;
	
	OldOffersCleaner(Set<Offer> offers) throws InvalidOffersValueToCheckException{
		if(offers != null) {
			
			this.offers = offers;
			
		}else {
			throw new InvalidOffersValueToCheckException("Invalid Offers Set !!!");
		}
		//making the thread Deamon Thread with min priority
		this.setDaemon(true);
		this.setPriority(MIN_PRIORITY);
	}
	
	@Override
	public void run() {
		//the thread must work through the whole programm
		while(true) {
			//removing Offers, older than 35 days
			for(Iterator<Offer> it = this.offers.iterator(); it.hasNext();) {
				Offer o = (Offer) it.next();
				if(o.getTimeOfPostingOffer().isBefore(LocalDateTime.now().minusDays(OFFERS_DAYS_LIMIT))) {
					it.remove();
				}
			}
		}
	}
	

}
