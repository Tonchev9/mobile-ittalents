package user_website_classe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

import allOfferClasses.Offer;

public class User {
	private static final int MAX_NUMBER_OF_MESSAGES = 50;
	private String userName;
	private String password;
	private String email;
	private Set<Offer> offers;
	private Set<Offer> favourites;
	private Queue<String> inbox;
	public User(String userName, String password, String email) throws Exception {
		super();
		this.setEmail(email);
		this.setPassword(password);
		this.setUserName(userName);
		this.offers = new HashSet<>();
		this.favourites = new HashSet<>();
		this.inbox = new ArrayBlockingQueue<>(MAX_NUMBER_OF_MESSAGES);
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
	
	public void createOffer() {
		Offer offer = new Offer(this);
		this.offers.add(offer);
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
	 		
	
	 
	 public void recieveMessage(String message) {
		 if(message != null) {
			 this.inbox.add(message);
		 }
	 }
	 
	 public void showInbox() {
		 for(String message : this.inbox) {
			 System.out.println(message);
		 }
	 }
	 
	 public void sendMessage(String userName) throws Exception {
		 
		 User owner = MobileBG.getMobileBG().giveMeUser(userName);
		 if(owner != null) {
			 System.out.println("You entered the message sending function, please enter message to send : ");
			 Scanner sc = new Scanner(System.in);
			 String message = sc.nextLine();
			 owner.recieveMessage(message);
		 }else {
			 System.out.println("INVALID USERNAME !!!");
		 }
		 
	 }
	
	 @Override
	public int hashCode() {
		return this.userName.hashCode();
	}
	 
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof User) {
			User user = (User) obj;
			return this.getUserName().equals(user.getUserName());
		}
		return false;
	}
	 
	
}
