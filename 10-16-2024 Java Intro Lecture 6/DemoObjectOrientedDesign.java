package edu.neu.csye6200;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoObjectOrientedDesign {
	public final static int MAJOR_VERSION;
	public final static int MINOR_VERSION;
	public final static String VERSION;
	private static NumberFormat f;
	/**
	 * static initialization block
	 * executed once with class
	 */
	static {
		MAJOR_VERSION = 3;
		MINOR_VERSION = 2;
		String[] DEV_LOG = {
				"1.0 Person class",
				"2.0 AddressAPI interface",
				"USStatesAPI interface with USAStatesEnum",
				"Address class",
				"add Address to Person class",
				"MySmartOffice class with Owner (Person) and office location (Address)",
				"3.0 refactor adding list of Addresses to Person class",
				"refactor adding list of leasees (Person) to SmartOffice class",
				"bug fix for nulllist of Addresses to Person class",
				"** END OF DEVELOPMENT LOG DO NOT DELETE **"
		};
		VERSION = Integer.valueOf(MAJOR_VERSION).toString()
				+ "."
				+ DEV_LOG.length
				+ "."
				+ Integer.valueOf(MINOR_VERSION).toString();
		f = new DecimalFormat("#0.00");
	}
	
	/**
	 * Person class
	 */
	private static class Person {
		private int id;
		private int age;
		private String firstName;
		private String lastName;
		private char mi;
		private List<Address> addressList;
		/**
		 * object initialization block
		 * executed with each constructor
		 */
		{
			this.id = 1;
			this.age = 17;
			firstName = "Dan";
			this.lastName = "Peters";
			this.mi = 'G';
			
			this.addressList = new ArrayList<Address>(Arrays.asList(
					new Address("Home", "143-03 97th Ave", "Jamaica Queens", USAStatesAPI.USAStatesEnum.NEW_YORK, "11432"),
					new Address("Work", "300 Concord Rd", "Billerica", USAStatesAPI.USAStatesEnum.MASSACHUSSETTES, "01824"),
					new Address("School", "LTI 1 Textile Ave", "Lowell", USAStatesAPI.USAStatesEnum.MASSACHUSSETTES, "01854")
					)); 
			
//			this.homeAddress = new Address("Home", "143-03 97th Ave", "Jamaica Queens", USAStatesAPI.USAStatesEnum.NEW_YORK, "11432");
		}
		
		public Person() {
			super();
		}

		public Person(int id, int age, String firstName, String lastName, char mi, List<Address> addresses) {
			super();
			this.id = id;
			this.age = age;
			this.firstName = firstName;
			this.lastName = lastName;
			this.mi = mi;
			this.addressList = addresses;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public char getMi() {
			return mi;
		}

		public void setMi(char mi) {
			this.mi = mi;
		}

		public void addHomeAddress(Address homeAddress) {
			this.addressList.add(homeAddress);
		}
		public List<Address> getHomeAddress() {
			return addressList;
		}
		public void setHomeAddress(List<Address> addresses) {
			this.addressList = addresses;
		}

		/**
		 * return a String representation of Person state
		 */
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("\t# ").append(id);
			sb.append(" ").append(firstName);
			sb.append(" ").append(mi).append(".");
			sb.append(" ").append(lastName);
			sb.append(", age ").append(age);
			if (null != addressList) {
				for (Address a : addressList) {
					sb.append( (null != a) ? "\n" + a.toString() : "");
				}
			}
//			sb.append( (null != homeAddress) ? "\n" + homeAddress.toString() : "");
			
//			String addr = (null == homeAddress) ? " " : sb.append(" HOME: ").append(homeAddress.toString()).toString();
//			if (null != homeAddress) sb.append(" HOME: ").append(homeAddress.toString());
			
			return sb.toString();
		}
	}
	
	/**
	 * AddressAPI interface for international use
	 */
	private interface AddressAPI {
		String getName();	// e.g., Home
		String getLine1();	// e.g., street address
		String getLine2();	// e.g., city
		String getLine3();	// e.g., county or state
		String getLine4();	// e.g., zip code
		String getLine5();	// e.g., country
	}
	
	/**
	 * USAAddressAPI interface for USA use
	 */
	private interface USAAddressAPI {
		String getName();
		String getStreet();
		String getCity();
		USAStatesAPI.USAStatesEnum getState();
		String getZip();
	}
	
	/**
	 * AddressAPI enumerated type
	 */
	private interface USAStatesAPI {
		public enum USAStatesEnum {
			ALABAMA("AL"), ALASKA("AK"), ARKANSAS("AR"), CALIFORNIA("CF"), COLORADO("CO"), CONNECTICUT("CT"), DELEWARE("DE"), FLORIDA("FL"), GEORGIA("GA"), HAWAII("HA"),
			IDAHO("ID"), ILLINOIS("IL"), INDIANA("IN"), IOA("IA"), KANSAS("KS"), KENTUCKY("KT"), LOUISIANA("LA"), MAINE("ME"), MARYLAND("MD"), MASSACHUSSETTES("MA"),
			MICHIGAN("MI"), MINNESOTA("MN"), MISSISSIPPI("MS"), MISSOURI("MO"), MONTANA("MT"), NEBRASKA("NB"), NEVADA("NV"), NEW_HAMPSHIRE("NH"), NEW_JERSEY("NJ"), NEW_MEXICO("NM"),
			NEW_YORK("NY"), NORTH_CAROLINA("NC"), NORTH_DAKOTA("ND"), OHIO("OH"), OKLAHOMA("OK"), OREGON("OR"), PENNSYLVANIA("PN"), RHODE_ISLAND("RI"), SOUTH_CAROLINA("SC"), SOUTH_DAKOTA("SD"),
			TENNESSEE("TN"), TEXAS("TX"), UTAH("UT"), VIRGINIA("VA"), WASHINGTON("WA"), WEST_VIRGINIA("WV"), WISCONSIN("WI"), WYOMING("WY"), DISTRICT_OF_COLUMBIA("DC"), AMERICAN_SOMOA("AS"),
			GUAM("GU"), NORTHERN_MARIANA_ISLANDS("MP"), PUERTO_RICO("PR"), UNITED_STATES_MINOR_OUTLYING_ISLANDS("UM"), VIRGIN_ISLANDS("VI");
			private String ab = "AK";
			private USAStatesEnum(String s) { this.ab = s; }
			public String getAb() { return this.ab; }
		}
	} // end USStatesAPI
	
	/**
	 * Address class
	 */
	private static class Address implements AddressAPI, USAAddressAPI {
		public final static String COUNTRY_USA = "USA";
		private String name;
		private String street;
		private String city;
		private USAStatesAPI.USAStatesEnum state;
		private String zip;
		{
			this.name = "Work";
			this.street = "105 Main St";
			this.city = "Boston";
			this.state = USAStatesAPI.USAStatesEnum.MASSACHUSSETTES;
			this.zip = "02134";
		}
		
		public Address() {
			super();
		}
		public Address(String name, String street, String city, USAStatesAPI.USAStatesEnum state, String zip) {
			super();
			this.name = name;
			this.street = street;
			this.city = city;
			this.state = state;
			this.zip = zip;
		}
		
		@Override
		public String getName() {
			return null;
		}
		@Override
		public String getLine1() {
			return getStreet();
		}
		@Override
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		
		@Override
		public String getLine2() {
			return getCity();
		}
		@Override
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		
		@Override
		public String getLine3() {
			return getState().toString();
		}
		@Override
		public USAStatesAPI.USAStatesEnum getState() {
			return state;
		}
		public void setState(USAStatesAPI.USAStatesEnum state) {
			this.state = state;
		}
		
		@Override
		public String getLine4() {
			return getZip();
		}
		@Override
		public String getZip() {
			return zip;
		}
		public void setZip(String zip) {
			this.zip = zip;
		}
		
		@Override
		public String getLine5() {
			return COUNTRY_USA;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(name).append(":\t");
			sb.append(street);
			sb.append(" ").append(city);
			sb.append(", ").append(state.getAb());
			sb.append(" ").append(zip);
			
			return sb.toString();
		}
	} // end class Address
	
	/**
	 * MySmartOffice class
	 */
	private static class MySmartOffice {
		private List<Person> personList;
		private Address location;
		{
			location = new Address("Location", "1345 Utica Ave", "Brooklyn", USAStatesAPI.USAStatesEnum.NEW_YORK, "11203");
			personList = new ArrayList<Person>(Arrays.asList(
					new Person(),
					new Person(2, 18, "Lee", "Mays", 'A', null),
					new Person(3, 20, "Jim", "Mays", 'G', null)
					));
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(location.toString()).append("\n");
			for (Person p : personList) {
				sb.append("Leasee #").append(p.getId()).append(":\t").append(p.getFirstName()).append(" ").append(p.getMi()).append(". ").append(p.getLastName()).append("\n");
//				sb.append("Partner:").append(person.toString()).append("\n");
			}

			return sb.toString();
		}
	}
	
	/**
	 * demonstrate use of this DemoObjectOrientedDesign class
	 */
	public static void demo() {
		System.out.println("\n\t" + DemoObjectOrientedDesign.class.getName() + ".demo() [version " + VERSION + "] ...");
		
		System.out.println();
		System.out.println("Person:\t");
		System.out.println(new Person());

		System.out.println();
		System.out.println("Work Address:\t");
		System.out.println(new Address());

		System.out.println();
		System.out.println("Smart Office:\t");
		System.out.println(new MySmartOffice());
		System.out.println();
		
		
		System.out.println("\n\t" + DemoObjectOrientedDesign.class.getName() + ".demo() [version " + VERSION + "] ... done!");
	}
}
