package allOfferClasses;

import java.time.LocalDate;
import java.util.Scanner;

// Must add validation for local date time(dateOfManufacture)
//Must see why when creating vehicle The console first shows invalid transmiision type and invalid color, before any value entered
abstract class Vehicle {

	private enum TypeOfEngine{
		GASOLINE, DIESEL, ELECTRIC;
		
		private static TypeOfEngine getTypeOfEngine(String type) {
			if(type.equalsIgnoreCase("Gasoline")) {
				return TypeOfEngine.GASOLINE;
			}
			if(type.equalsIgnoreCase("Diesel")) {
				return TypeOfEngine.DIESEL;
			}
			if(type.equalsIgnoreCase("Electric")) {
				return TypeOfEngine.ELECTRIC;
			}
			System.out.println("Invalid type of engine, please enter new one");
			Scanner sc = new Scanner(System.in);
			type = sc.nextLine();
			return getTypeOfEngine(type);
		}
	}
	private enum Condition{
		NEW,USED,FOR_PARTS;
		
		private static Condition getCondition(String condition) {
			if(condition.equalsIgnoreCase("New")) {
				return Condition.NEW;
			}
			if(condition.equalsIgnoreCase("Used")) {
				return Condition.USED;
			}
			if(condition.equalsIgnoreCase("For parts")) {
				return Condition.FOR_PARTS;
			}
			System.out.println("Invalid type of condition, please enter new one");
			Scanner sc = new Scanner(System.in);
			condition = sc.nextLine();
			return getCondition(condition);
		}
	}
	private enum Transmission{
		MANUAL,AUTOMATIC, SEMI_AUTOMATIC;
		
		private static Transmission getTransmission(String transmission) {
			if(transmission.equalsIgnoreCase("Manual")) {
				return Transmission.MANUAL;
			}
			if(transmission.equalsIgnoreCase("Automatic")) {
				return Transmission.AUTOMATIC;
			}
			if(transmission.equalsIgnoreCase("Semi Automatic")) {
				return Transmission.SEMI_AUTOMATIC;
			}
			System.out.println("Invalid type of transmission, please enter new one out of MANUAL/AUTOAMTIC/SEMI AUTOMATIC");
			Scanner sc = new Scanner(System.in);
			transmission = sc.nextLine();
			return getTransmission(transmission);
			 
		}
	}
	
	private String brand;
	private String model;
	private TypeOfEngine typeOfEngine;
	private Condition condition;
	private int horse_power;
	private Transmission transmission;
	private int price;
	private LocalDate dateOfManufacture;
	private int kilometersDriven;
	private String color;
	private int yearOfManufacture; // year needed to search criteria (int)
		
	Vehicle(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter brand : ");
		String brand = sc.nextLine();
		if(brand != null) {
			this.brand = brand;
		}
		System.out.println("Enter model : ");
		String model = sc.nextLine();
		if(model != null) {
			this.model = model;
		}
		System.out.println("Enter engine type GASOLINE/ELECTRIC/DIESEL");
		String engineType = sc.nextLine();
		this.typeOfEngine = TypeOfEngine.getTypeOfEngine(engineType);
		System.out.println("Please enter condition NEW/USED/FOR PARTS");
		String condition = sc.nextLine();
		this.condition = Condition.getCondition(condition);
		System.out.println("Please enter Horse Power ");
		int power = sc.nextInt();
		this.setPower(power);
		System.out.println("Please enter transmission type out of MANUAL/AUTOMATIC/SEMI AUTOMATIC");
		String transmission = sc.nextLine();
		this.transmission = Transmission.getTransmission(transmission);
		System.out.println("Please enter price");
		int price = sc.nextInt();
		this.setPrice(price);
		System.out.println("Please enter date of manufacture by YEAR/MONTH/DAY");
		int year = sc.nextInt();
		int month = sc.nextInt();
		int dayOfMonth = sc.nextInt();
		this.dateOfManufacture = LocalDate.of(year, month, dayOfMonth);
		this.yearOfManufacture = this.dateOfManufacture.getYear();
		System.out.println("Please enter the kilometers driven");
		int kilometers = sc.nextInt();
		this.setKilometersDriven(kilometers);
		System.out.println("Please enter the color of the vehicle");
		String color = sc.nextLine();
		this.setColor(color);
		
	}
		
	public abstract String getTypeOfVehicle();
		
	private void setColor(String color) {
		if(color != null && !color.trim().equals("")) {
			this.color = color;
		}else {
			System.out.println("Invalid color value, please enter new one");
			Scanner sc = new Scanner(System.in);
			color = sc.nextLine();
			this.setColor(color);
		}
	}
	private void setKilometersDriven(int kilometers) {
		if(kilometers >= 0) {
			this.kilometersDriven = kilometers;
		}else {
			System.out.println("Invalid kilometers value, please enter positive number");
			Scanner sc = new Scanner(System.in);
			kilometers = sc.nextInt();
			this.setKilometersDriven(kilometers);
		}
	}
	private void setPrice(int price) {
		if(price >= 0) {
			this.price = price;
		}else {
			System.out.println("Invalid price, please enter positive number");
			Scanner sc = new Scanner(System.in);
			price = sc.nextInt();
			this.setPrice(price);
		}
	}
	private void setPower(int power) {
		if(power > 0) {
			this.horse_power = power;
		}else {
			System.out.println("Invalid horse power value, please enter new one");
			Scanner sc = new Scanner(System.in);
			power = sc.nextInt();
			this.setPower(power);
		}
	}
	
	private static class Automobile extends Vehicle{

		Automobile(){
			super();
		}
		@Override
		public String getTypeOfVehicle() {
			
			return "Automobile";
		}
		
	}
	
	private static class Motorcycle extends Vehicle{

		private int cubicle;//kubatura
		private String coolingSystem;
		private String typeOfEngine;
		
		Motorcycle(){
		System.out.println("Please enter cooling system type out of (Water/Air)");
		Scanner sc = new Scanner(System.in);
		String coolingSystem = sc.nextLine();
		this.setCoolingSystem(coolingSystem);
		System.out.println("Please enter engine type out of (Two stroke/Foyr Stroke)");
		String typeOfEngine = sc.nextLine();
		this.setTypeOfEngine(typeOfEngine);
		System.out.println("Please enter the cubicle of the motorcycle ");
		int cubicle = sc.nextInt();
		this.setCubicle(cubicle);
		}
		
		@Override
		public String getTypeOfVehicle() {
			return "Motorcylce";
		}
		
		private void setCubicle(int cubicle) {
			if(cubicle > 0 ) {
				this.cubicle = cubicle;
			}else {
				System.out.println("Invalid cubicle value, please enter new POSITIVE one");
				Scanner sc = new Scanner(System.in);
				cubicle = sc.nextInt();
				this.setCubicle(cubicle);
			}
		}
		private void setCoolingSystem(String coolingSystem) {
			if(coolingSystem.equalsIgnoreCase("Water")) {
				this.coolingSystem = coolingSystem;
			}
			if(coolingSystem.equalsIgnoreCase("Air")) {
				this.coolingSystem = coolingSystem;
			}
			System.out.println("Invalid cooling system, please enter new one out of Water/Air cooling system");
			Scanner sc = new Scanner(System.in);
			coolingSystem = sc.nextLine();
			this.setCoolingSystem(coolingSystem);
		}
		private void setTypeOfEngine(String typeOfEngine) {
			if(typeOfEngine.equalsIgnoreCase("Two stroke")) {
				this.typeOfEngine = typeOfEngine;
			}
			if(typeOfEngine.equalsIgnoreCase("Four stroke")) {
				this.typeOfEngine = typeOfEngine;
			}
			System.out.println("Invalid type of engine, please enter new one out of (Two stroke/Four stroke)");
			Scanner sc = new Scanner(System.in);
			typeOfEngine = sc.nextLine();
			this.setTypeOfEngine(typeOfEngine);
		}
	}
	
	private static class Bus extends Vehicle{

		private int numberOfAxles;// broi osi
		private int numberOfSeats;
		private int loadability;// in kg
		
		Bus(){
			super();
			System.out.println("Please enter the number of the bus axles between 1 and 8");
			Scanner sc = new Scanner(System.in);
			int numberOfAxles = sc.nextInt();
			this.setNumberOfAxles(numberOfAxles);
			System.out.println("Please enter number of seats");
			int numberOfSeats = sc.nextInt();
			this.setNumberOfSeats(numberOfSeats);
			System.out.println("Please enter the loadability of the Bus");
			int loadability = sc.nextInt();
			this.setLoadability(loadability);
		}
		
		@Override
		public String getTypeOfVehicle() {
			
			return "Bus";
		}
		
		private void setLoadability(int loadability) {
			if(loadability > 0) {
				this.loadability = loadability;
			}else {
				System.out.println("Invalid loadability value , please enter new POSITIVE one ");
				Scanner sc = new Scanner(System.in);
				loadability = sc.nextInt();
				this.setLoadability(loadability);
			}
		}
		
		private void setNumberOfSeats(int numberOfSeats) {
			if(numberOfSeats > 0) {
				this.numberOfSeats = numberOfSeats;
			}else {
				System.out.println("Invalid number of seats number, please enter new POSITIVE one ");
				Scanner sc = new Scanner(System.in);
				numberOfSeats = sc.nextInt();
				this.setNumberOfSeats(numberOfSeats);
			}
		}
		private void setNumberOfAxles(int numberOfAxles) {
			if(numberOfAxles >= 1 && numberOfAxles <= 8) {
				this.numberOfAxles = numberOfAxles;
			}else {
				System.out.println("Invalid number of axles number, please enter new one between 1 and 8");
				Scanner sc = new Scanner(System.in);
				numberOfAxles = sc.nextInt();
				this.setNumberOfAxles(numberOfAxles);
			}
		}
	}
	
	private static class Truck extends Bus{
		
		@Override
		public String getTypeOfVehicle() {
			
			return "Truck";
		}
		
	}
	
	//factory method
	public  static Vehicle createVehicle(String vehicleType) {
		if(vehicleType.equalsIgnoreCase("Automobile")) {
			return new Automobile();
		}
		if(vehicleType.equalsIgnoreCase("Bus")) {
			return new Bus();
		}
		if(vehicleType.equalsIgnoreCase("Truck")) {
			return new Truck();
		}
		if(vehicleType.equalsIgnoreCase("Motorcycle")) {
			return new Motorcycle();
		}
		System.out.println("There is no such type of vehicle, please enter new one out of ( AUTOMOBILE / BUS / TRUCK / MOTORCYCLE )");
		Scanner sc = new Scanner(System.in);
		vehicleType = sc.nextLine();
		return createVehicle(vehicleType);
	}

	int getPrice() {
		return price;
	}

	public String getBrand() {
		return brand;
	}

	public LocalDate getDateOfManufacture() {
		return dateOfManufacture;
	}

	public int getYearOfManufacture() {
		return yearOfManufacture;
	}
	
	
}
