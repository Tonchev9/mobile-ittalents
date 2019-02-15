package user_website_classe;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import allOfferClasses.Offer;

public class MobileBG {
	private static final int MIN_PASSWORD_LENGTH = 5;
	private static final int MAX_PASSWORD_LENGTH = 20;
	private Set<User> users;
	private Set<Offer> offers;
	private static MobileBG mobileBgExample = null;
	
	
	
	private MobileBG() throws Exception {
		super();
		this.users = new HashSet<>();
		this.offers = new HashSet<>();
	}
	public static MobileBG getMobileBG() throws Exception {
		if(MobileBG.mobileBgExample == null) {
			MobileBG.mobileBgExample =  new MobileBG();
		}
		return MobileBG.mobileBgExample;
	}
	 private void registerNewUser(String username,String password,String email) throws Exception {
		if (username != null && password != null && email != null) {
			if (isUserNameAvailable(username) && isPasswordValid(password)) {
				if (isEmailAvailable(email)) {
					this.users.add(new User(username, password, email));
					System.out.println("Registration sucessful");
					return;
				} else {
					System.out.println("Invalid email");
					return;
				}
			} else {
				System.out.println("Invalid username or password");
				return;
			}
		}
	}
	 
	public boolean isThereSuchUser(User user) {
		if(user != null) {
			if(this.users.contains(user)) {
				return true;
			}
		}
		return false;
	}
	
	public User giveMeUser(String userName) {
		User user = null;
		for(User u : this.users) {
			if(u.getUserName().equals(userName)) {
				return u;
			}
		}
		return user;
	}
	private boolean isUserNameAvailable(String userName) {
		boolean isFree = true;
		for(User user: this.users) {
			if(user.getUserName().equals(userName)) {
				isFree = false;
			}
		}
		return isFree;
	}
	
	public void addOfferToMobileBG(Offer offer) {
		if(offer != null) {
			this.offers.add(offer);
		}
	}
	
	private boolean isPasswordValid(String password) {
		boolean isValid = true;
		if(password.length() > MAX_PASSWORD_LENGTH || password.length() < MIN_PASSWORD_LENGTH ) {
			isValid = false; 
		}
		String[] myPassword = password.split(" ");
		if(myPassword.length > 1) {
			isValid = false;
		}
		return isValid;
	}
	private boolean isEmailAvailable(String email) {
		boolean isValid = false;
		for(int index = 0; index < email.length(); index++) {
			if(email.charAt(index) == '@') {
				isValid = true;
			}
		}
		String[] myEmail = email.split(" ");
		if(myEmail.length == 0) {
			isValid = true;
		}
		return isValid;
	}
	
	public User getSuccessfullyLoggedUser(String userName) {
		User tempU = null;
			for(User u : this.users) {
				if(u.getUserName().equals(userName)) {
					tempU = u;
				}
			}
			return tempU;
	 }
	public Set<Offer> showOffersByCathegories() {
		//String category -> Set of offers prioritirized by OfferType
		Set<Offer> offersByCat = new HashSet<>();
		Map<String, PriorityQueue<Offer>> offersByCathegories= new HashMap<String, PriorityQueue<Offer>>();
		for(Offer o : this.offers) {
			if(!offersByCathegories.containsKey(o.getOfferVegicleCathegory())) {
				PriorityQueue<Offer> cath = new PriorityQueue<Offer>((o1, o2) -> o2.getOfferPriority() - o1.getOfferPriority());
				offersByCathegories.put(o.getOfferVegicleCathegory(), cath);
			}
			offersByCathegories.get(o.getOfferVegicleCathegory()).offer(o);
		}
		
		for(Offer offer : this.offers) {
			if(!offersByCathegories.containsKey(offer.getOfferVegicleCathegory())) {
				PriorityQueue<Offer> offersCathegory = new PriorityQueue<Offer>((o1, o2) -> o2.getOfferPriority() - o1.getOfferPriority());
				offersCathegory.offer(offer);
				offersByCathegories.put(offer.getOfferVegicleCathegory(), offersCathegory);
			}else {
				offersByCathegories.get(offer.getOfferVegicleCathegory())
				.add(offer);
			}
		}
	
		System.out.println("Please choose category out of : ");
		for(Entry<String, PriorityQueue<Offer>> entry : offersByCathegories.entrySet()) {
			System.out.println(entry.getKey());
		}
		Scanner sc = new Scanner(System.in);
		String cathegoryChoice = sc.nextLine();
		while(!offersByCathegories.containsKey(cathegoryChoice)) {
			System.out.println("Invalid category choice, please enter new VALID ONE !!!");
			cathegoryChoice = sc.nextLine();
		}
		PriorityQueue<Offer> cathegory = new PriorityQueue<Offer>();
		cathegory = offersByCathegories.get(cathegoryChoice);
		for(Offer offer : cathegory) {
			offersByCat.add(offer);
			offer.showOffer();
		}
		return offersByCat;
	}	
	
	
	
	public void showOrderedByPrice(String brand) {
		Set<Offer> offersToSort = this.showOffersByCathegories();
		System.out.println("Ordered by price (low -> high)");
		offersToSort.stream()
		.filter(offer -> offer.getVehicleBrand().equalsIgnoreCase(brand))
		.sorted((offer1, offer2) -> offer1.getVehiclePrice() - offer2.getVehiclePrice())
		.forEach(offer -> System.out.println(offer));
	}
	public void showOrderedByNewestOffers(String brand) {
		Set<Offer> offersToSort = this.showOffersByCathegories();
		System.out.println("Ordered by newest ");
		offersToSort.stream()
		.filter(offer -> offer.getVehicleBrand().equalsIgnoreCase(brand))
		.sorted((offer1, offer2) -> offer2.getTimeOfPostingOffer().compareTo(offer1.getTimeOfPostingOffer()))
		.forEach(offer -> System.out.println(offer));
	}
	
	
	public void showOrderedByYearOfManufacture(String brand) {
		Set<Offer> offersToSort = this.showOffersByCathegories();
		System.out.println("Ordered by Year Of Manufacture");
		offersToSort.stream()
		.filter(offer -> offer.getVehicleBrand().equalsIgnoreCase(brand))
		.sorted((offer1, offer2) -> offer1.getVehicleYearOfManufacture() - offer2.getVehicleYearOfManufacture())
		.forEach(offer -> System.out.println(offer));
	}
	
	public boolean checkLogin(String userName, String password) {
		boolean isRegistered = false;
		for(User u: this.users) {
			if(u.getUserName().equals(userName) && u.getPassword().equals(password)) {
				isRegistered = true;
			}
		}
		return isRegistered;
	}

	public void showFunctions() {
		boolean condition = true;
		while (condition) {
			System.out.println("Enter 0 to exit");
			System.out.println("Enter 1 to show by category");
			System.out.println("Enter 2 to show offers ordered by price");
			System.out.println("Enter 3 to show offers ordered by year of manufacture");
			System.out.println("Enter 4 to show offers ordered by add date");

			Scanner sc = new Scanner(System.in);
			int x = sc.nextInt();
			switch (x) {
			case 0:
				condition = false;
				break;
			case 1:
				this.showOffersByCathegories();
				break;
			case 2:
				System.out.println("Enter brand ");
				String brandToShow = sc.nextLine();
				this.showOrderedByPrice(brandToShow);
				break;
			case 3:
				System.out.println("Enter brand ");
				String brandToShow2 = sc.nextLine();
				this.showOrderedByYearOfManufacture(brandToShow2);
				break;
			case 4:
				System.out.println("Enter brand ");
				String brandToShow3 = sc.nextLine();
				this.showOrderedByNewestOffers(brandToShow3);
				break;
			}
		}
		
	}
	////////////////test
	public void addUser(User u) {
		this.users.add(u);
	}
	
	public void sayHello() {
		System.out.println("Hello!");
		System.out.println("MOBILE BG");
	}
	public void showOptions() {
		System.out.println("What do you want to do ");
		System.out.println("Enter 1 to login");
		System.out.println("Enter 2 to register");
		System.out.println("Enter 3 if you are a guest");
		System.out.println("Enter 4 to exit");
		System.out.println("--------------------------");
	}
	public void showSite() throws Exception {
		this.sayHello();
	
		boolean condition = true;
		while (condition) {
			this.showOptions();
			Scanner sc = new Scanner(System.in);
			int x = sc.nextInt();
			switch (x) {
			case 1:
				System.out.println("Enter username"); 
				Scanner sc1 = new Scanner(System.in);
				String username = sc1.nextLine();
				System.out.println("Enter password");
				Scanner sc2 = new Scanner(System.in);
				String password = sc2.nextLine();
				if (!checkLogin(username, password)) {
					System.out.println("Invalid username or password");	
				} else {
					User tempUser = this.getSuccessfullyLoggedUser(username);
					boolean cond = true;
					while(cond) {
						System.out.println("Login successful");
						System.out.println("Type 0 to exit");
						System.out.println("Type 1 to add offer");
						System.out.println("Type 2 to send message");
						System.out.println("Type 3 to show my offers");
						System.out.println("Type 4 to show advanced options ");
						int y = sc1.nextInt();
						switch (y) {
						case 0:
							this.showSite();
							cond = false;
							break;
						case 1:
							tempUser.createOffer();
							
							break;
						case 2:
							System.out.println("Type user to send messsage");
							Scanner sc5 = new Scanner(System.in);
							String userNameToSendMessage = sc5.nextLine();
							tempUser.sendMessage(userNameToSendMessage);
							break;
						case 3:
							System.out.println("My offers!");
							tempUser.showMyOffers();
							break;
						case 4:
							this.showFunctions();
							break;
						}
					}
				}
				break;

			case 2:
				
				System.out.println("----REGISTRATION----");
				System.out.println("Enter username");
				Scanner sc3 = new Scanner(System.in);
				String usernameToReg = sc3.nextLine();
				System.out.println("Enter password");
				Scanner sc4 = new Scanner(System.in);
				String passwordToReg = sc4.nextLine();
				System.out.println("Enter email");
				Scanner sc5 = new Scanner(System.in);
				String emailToReg = sc5.nextLine();
				this.registerNewUser(usernameToReg, passwordToReg, emailToReg);
				this.showOptions();
				x = sc.nextInt();
				break;

			case 3:
				this.showFunctions();
				break;
				
			case 4: condition = false;
			System.out.println("Bye");
			break;

			}
		}

	}
}
