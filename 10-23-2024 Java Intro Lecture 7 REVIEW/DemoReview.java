package edu.neu.info5100;

import java.util.Iterator;

/**
 * DemoReview class to review introductory Java concepts
 */
public class DemoReview {
	private int count;
	/**
	 * object initialization block
	 * executed by each class constructor
	 */
	{
		count = 0;
	}
	/**
	 * static data members
	 */
	private final static int MAJOR;
	private final static int MINOR;
	private final static String REVISION;
	/**
	 * static initialization block
	 * executed once for the class
	 */
	static {
		MAJOR = 1;
		MINOR = 1;
		REVISION = MAJOR + "." + MINOR;
	}
	/**
	 * static class member methods
	 */
	public static void aPublicStaticMethod() {
		System.out.println("\n\t a public static method callable without creating an object by members of any class." );
	}
	private static void aPrivateStaticMethod() {
		System.out.println("\n\t a private static method callable without creating an object and ONLY by it's own class members." );
	}
	/**
	 * object instance member methods
	 */
	public void aPublicMethod() {
		System.out.println("\n\t a public method callable with an object instance by members of any class." );
	}
	private void aPrivateMethod() {
		System.out.println("\n\t a private method callable with an object instance and ONLY by it's own class members." );
	}
	/**
	 * Pass-By-Value example:
	 * 
	 * incrementCount uses pass primitive int datatype by value (i.e. a copy of original) and can NOT affect original primitive value
	 * @param count
	 * @return
	 */
	public int incrementCount(int count ) {
		System.out.println("\n\t" + " value BEFORE incrementing " + count);
		count++;
		System.out.println("\n\t" + " value AFTER incrementing " + count);
		
		return count;
	}
	/**
	 * Pass-By-Reference example:
	 * 
	 * incrementCount uses pass reference datatype by reference (i.e. a copy of original reference STILL point to original object) and CAN affect original value
	 * @param count
	 * @return
	 */
	public int incrementCount(DemoReview object ) {
		System.out.println("\n\t" + " value BEFORE incrementing " +object.count);
		object.count++;
		System.out.println("\n\t" + " value AFTER incrementing " + object.count);
		
		return object.count;
	}
	/**
	 * Item class models an item bought and sold
	 */
	private static class Item {
		/**
		 * static data members
		 */
		private final static int MAJOR;
		private final static int MINOR;
		private final static String REVISION;
		/**
		 * static initialization block
		 * executed once for the class
		 */
		static {
			MAJOR = 1;
			MINOR = 2;
			REVISION = MAJOR + "." + MINOR;
		}
		/**
		 * object instance data members
		 */
		private int id;
		private double price;
		private String name;
		/**
		 * object initialization block
		 * executed with each class constructor
		 */
		{
			id = 1;
			price = 0.99;
			name = "item";
		}
		
		public Item() {
			super();
		}
		public Item(int id, double price, String name) {
			super();
			this.id = id;
			this.price = price;
			this.name = name;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * return a String representation of the Item class
		 * overriding the toString() method of super classl (Object)
		 */
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("Item v " + REVISION + ": ");
			
			sb.append("# ").append(id);
			sb.append(" $ ").append(price);
			sb.append(" ").append(name);
			
			return sb.toString();
//			return super.toString(); 	// call toString() in Object class
		}

		// using implicit compiler-provided default class constructor
		
//		/**
//		 * using explicitly coded default class constructor
//		 */
//		public Item() {
//			super();
//		}
	}
	/**
	 * useInteger demonstrates use of Integer class with auto-boxing primitive int
	 */
	public static void useInteger() {
		/*
		 *  array of Integer objects
		 *  assigned by primitive int literal values
		 *  SO java performs auto-boxing
		 *  instantiating an object from the Interger class
		 *  to wrap each primitive int value
		 */
		Integer[] aInteger = { Integer.valueOf(1), 2, 3 }; // literal int values

		/**
		 * range based loop
		 */
		System.out.println(aInteger.length + " elements in array");
		for (Integer n : aInteger) {
			System.out.println(n);
		}
	}

	/**
	 * object instance demo() method (object instantiation required)
	 */
	public void demoObject() {
		System.out.println("\n\t" + DemoReview.class.getName() + ".demoObject() (obj) version [" + REVISION + "]...");
		
		/**
		 * pass by value example
		 */
		int returnValue = 0;
		
		System.out.println("\n\t Pass-By-Value example incrementing count...");
		returnValue = incrementCount(this.count);
		System.out.println("\n\t returnValue = " + returnValue);
		
		returnValue = incrementCount(this.count);
		System.out.println("\n\t returnValue = " + returnValue);
		
		returnValue = incrementCount(this.count);
		System.out.println("\n\t returnValue = " + returnValue);
		
		/**
		 * pass by reference (object created from DemoReview class) example
		 */
		returnValue = 0;
		
		System.out.println("\n\t Pass-By-Reference example incrementing count...");
		returnValue = incrementCount(this);
		System.out.println("\n\t returnValue = " + returnValue);
		
		returnValue = incrementCount(this);
		System.out.println("\n\t returnValue = " + returnValue);
		
		returnValue = incrementCount(this);
		System.out.println("\n\t returnValue = " + returnValue);
		
		System.out.println("instantiate object from Item class...");
		System.out.println("call Item toString()...");
		Item obj = new Item();	// instantiate object from Item class
		
		System.out.println(obj); 	// implicit call method toString 
		System.out.println(obj.toString()); 	// explicit call method toString 
		
		DemoReview.useInteger();	// use static method
		System.out.println("\n\t" + DemoReview.class.getName() + ".demoObject() (obj) version [" + REVISION + "]... done!");
	}
	
	/**
	 * static class demo() method (no object instantiation required, used from class)
	 */
	public static void demo() {
		System.out.println("\n\t" + DemoReview.class.getName() + ".demo() (class static) version ["+ REVISION +"]...");
		
		// instantiate object and call object instance method
		new DemoReview().demoObject();
		
		System.out.println("\n\t" + DemoReview.class.getName() + ".demo() (class static) version ["+ REVISION +"]... done!");
	}
}
