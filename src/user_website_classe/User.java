package user_website_classe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import allOfferClasses.Offer;

public class User {
	private String userName;
	private String password;
	private String email;
	private Set<Offer> offers;
	private Set<Offer> favourites;
	private List<String> inbox;
	public User(String userName, String password, String email) throws Exception {
		super();
		this.setEmail(email);
		this.setPassword(password);
		this.setUserName(userName);
		this.offers = new HashSet<>();
		this.favourites = new HashSet<>();
		this.inbox = new ArrayList<>();
	}
	public String getUserName() {
		return userName;
	}
	private void setUserName(String userName) throws Exception {
		if(userName!= null)
			this.userName = userName;
			else throw new Exception("UserName is NULL");
	}
	public String getPassword() {
		return password;
	}
	private void setPassword(String password) throws Exception {
		if(password!= null)
		this.password = password;
		else throw new Exception("Password is NULL");
	}
	public String getEmail() {
		return email;
	}
	
	private void setEmail(String email) throws Exception {
		if(email!= null)
		this.email = email;
		else throw new Exception("Email is NULL");
	}
	
	public Set<Offer> getOffers() {
		return Collections.unmodifiableSet(offers);
	}
	
	public Set<Offer> getFavourites() {
		return Collections.unmodifiableSet(favourites);
	}
	
	 void addOffer(Offer offer) throws Exception {
		if(offer == null) throw new Exception("The offer to add is null");
		this.offers.add(offer);
	}
	 
	 void addFavourites(Offer offer) throws Exception {
		if(offer == null) throw new Exception("The favourite offer to add is null");
		this.favourites.add(offer);
	}
	 
	 void removeOffer(Offer offer) {
		 for(Iterator<Offer> it = offers.iterator(); it.hasNext();) {
			 Offer o = it.next();
			 if(o.getId() == offer.getId()) {
				 it.remove();
			 }
		 }
	 }
	 void removeFavourite(Offer offer) {
		 for(Iterator<Offer> it = favourites.iterator(); it.hasNext();) {
			 Offer o = it.next();
			 if(o.getId() == offer.getId()) {
				 it.remove();
			 }
		 }
	 }
	 
	 public void sendMessage(User owner) {
		 
		 System.out.println("Message!!!");
	 }
	
	 
	
}
