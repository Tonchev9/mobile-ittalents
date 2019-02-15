package allOfferClasses;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import user_website_classe.MobileBG;
import user_website_classe.User;

public class Offer {
	public static final int TOP_OFFER_PRIORITY = 3;
	public static final int VIP_OFFER_PRIORITY = 2;
	public static final int REGULAR_OFFER_PRIORITY = 1;

	private enum OfferType{
		VIP(VIP_OFFER_PRIORITY),REGULAR(REGULAR_OFFER_PRIORITY),TOP(TOP_OFFER_PRIORITY);
		
		private int priority;
		
		OfferType(int priority){
			this.priority = priority;
		}
		
		private static OfferType getOfferType(String offerType) {
			if(offerType.equalsIgnoreCase("VIP")) {
				return OfferType.VIP;
			}
			if(offerType.equalsIgnoreCase("REGULAR")) {
				return OfferType.REGULAR;
			}
			if(offerType.equalsIgnoreCase("TOP")) {
				return OfferType.TOP;
			}
			System.out.println("Invalid offer type, please enter new one out of ( REGULAR / VIP / TOP ");
			Scanner sc = new Scanner(System.in);
			offerType = sc.nextLine();
			return OfferType.getOfferType(offerType);
		}
	}
	
	private User owner;
	private static int nextId = 0;
	private Vehicle vehicle;
	private int id;
	private String town;
	private LocalDateTime timeOfPostingOffer;
	private OfferType offerType; //VIP, REGULAR, TOP
//	private int offerValidity; // days
	

	
	
	
	public Offer(User user){
		
		try {
			this.setUser(user);
		} catch (Exception e) {
			System.out.println("INVALID USER ");
			return;
		}
		System.out.println("Please enter vehicle cathegory out of ( AUTOMOBILE / BUS / TRUCK / MOTORCYCLE )");
		Scanner sc = new Scanner(System.in);
		String vehicleType = sc.nextLine();
		this.vehicle = Vehicle.createVehicle(vehicleType);
		
		System.out.println("Please enter the name of the town of the offer");
		String town = sc.nextLine();
		this.town = town;
		
		System.out.println("Please enter the type of the offer ot of ( REGULAR / VIP / TOP )");
		String offerType = sc.nextLine();
		this.offerType = OfferType.getOfferType(offerType);
		
		this.timeOfPostingOffer = LocalDateTime.now();
		
		this.id = Offer.nextId;
		Offer.nextId++;
	}

	public LocalDateTime getTimeOfPostingOffer() {
		return timeOfPostingOffer;
	}

	public OfferType getOfferType() {
		return offerType;
	}
	
	private void setUser(User user) throws Exception {
		if(MobileBG.getMobileBG().isThereSuchUser(user)) {
			this.owner = user;
		}else {
			System.out.println("INVALID USER !!!");
		}
	}
	
	public int getOfferVehiclePrice() {
		return this.vehicle.getPrice();
	}

	public int getId() {
		return id;
	}

	public String getVehicleBrand() {
		return this.vehicle.getBrand();
	}
	public int getVehicleYearOfManufacture() {
		return this.vehicle.getYearOfManufacture();
	}
	public int getVehiclePrice() {
		return this.vehicle.getPrice();
	}
	public String getOfferVegicleCathegory() {
		return this.vehicle.getTypeOfVehicle();
	}
	public int getOfferPriority() {
		return this.offerType.priority;
	}

	public void showOffer() {
		System.out.println("[ OFFER INFO ]");
		System.out.println("Owner user name : " + this.owner.getUserName());
		System.out.println("Offer ID : " + this.id);
		System.out.println("Town : " + this.town);
		System.out.println("Offer type : " + this.offerType);
		this.vehicle.showVehicleInfo();
	}
	
	@Override
	public int hashCode() {
		return this.id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Offer) {
			Offer offer = (Offer) obj;
			return this.id == offer.getId();
		}
		return false;
	}
}