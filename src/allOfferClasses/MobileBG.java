package allOfferClasses;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

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
		this.sayHello();
		this.showSite();
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
	private boolean isUserNameAvailable(String userName) {
		boolean isFree = true;
		for(User user: this.users) {
			if(user.getUserName().equals(userName)) {
				isFree = false;
			}
		}
		return isFree;
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
	
	public void showOrderedByPrice(Set<Offer> offers, String brand,int year) {
		System.out.println("Ordered by price (low -> high)");
		offers.stream()
		.filter(offer -> offer.getVehicle().getBrand().equalsIgnoreCase(brand) && offer.getVehicle().getYearOfManufacture() > year)
		.sorted((offer1, offer2) -> offer1.getVehicle().getPrice() - offer2.getVehicle().getPrice())
		.forEach(offer -> System.out.println(offer));
	}
	public void showOrderedByNewestOffers(Set<Offer> offers,String brand,int year) {
		System.out.println("Ordered by newest ");
		offers.stream()
		.filter(offer -> offer.getVehicle().getBrand().equalsIgnoreCase(brand) && offer.getVehicle().getYearOfManufacture() > year)
		.sorted((offer1, offer2) -> offer1.getVehicle().getDateOfManufacture().compareTo(offer2.getVehicle().getDateOfManufacture()))
		.forEach(offer -> System.out.println(offer));
	}
	public void showOrderedByOldestOffers(Set<Offer> offers,String brand,int year) {
		System.out.println("Ordered by oldest ");
		offers.stream()
		.filter(offer -> offer.getVehicle().getBrand().equalsIgnoreCase(brand) && offer.getVehicle().getYearOfManufacture() > year)
		.sorted((offer1, offer2) -> offer2.getVehicle().getDateOfManufacture().compareTo(offer1.getVehicle().getDateOfManufacture()))
		.forEach(offer -> System.out.println(offer));
	}
	
	public void showOrderedByYearOfManufacture(Set<Offer> offers,String brand, int year) {
		System.out.println("Ordered by Year Of Manufacture");
		offers.stream()
		.filter(offer -> offer.getVehicle().getBrand().equalsIgnoreCase(brand) && offer.getVehicle().getYearOfManufacture() > year)
		.sorted((offer1, offer2) -> offer1.getVehicle().getYearOfManufacture() - offer2.getVehicle().getYearOfManufacture())
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
		this.showOptions();
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		boolean condition = true;
		while (condition) {
			switch (x) {
			case 1:
				System.out.println("Enter username");
				Scanner sc1 = new Scanner(System.in);
				String username = sc1.nextLine();
				System.out.println("Enter password");
				Scanner sc2 = new Scanner(System.in);
				String password = sc1.nextLine();
				if (!checkLogin(username, password)) {
					System.out.println("Invalid username or password");	
				} else {
					User tempUser = this.getSuccessfullyLoggedUser(username);
					System.out.println("Login successful");
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
				System.out.println("You want to search, enter the brand you want ");
				String brandToSearch = sc.nextLine();
				//
				break;
				
			case 4: condition = false;
			System.out.println("Bye");
			break;

			}
		}

	}
}
