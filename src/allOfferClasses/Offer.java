package allOfferClasses;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Offer {
	private enum OfferType{
		VIP,REGULAR,TOP;
		
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
	
//	private User owner;
	private static int nextId = 0;
	private Vehicle vehicle;
	private int id;
	private String town;
	private LocalDateTime timeOfPostingOffer;
	private OfferType offerType; //VIP, REGULAR, TOP
//	private int offerValidity; // days
	
	Offer(){
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
	
	public int getOfferVehiclePrice() {
		return this.vehicle.getPrice();
	}

	public int getId() {
		return id;
	}
	
}