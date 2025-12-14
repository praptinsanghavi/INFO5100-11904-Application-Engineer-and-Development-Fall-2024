package edu.neu.csye6200;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DemoCalculator demonstrates integer, double precision floating point, BigDecimal arithmetic
 */
public class DemoCalculator {
	public static final int MAJOR_REVISION;
	public static final int MINOR_REVISION;
	public static final String REVISION;
	private final static NumberFormat f = new DecimalFormat("##.##");
	/**
	 * static initialization block
	 * executed once for the class
	 */
	static {
		MAJOR_REVISION = 5;	
		MINOR_REVISION = 4;	
		final String[] DEV_LOG = {
				"1.0 added CalcabilityAPI interface as API for all calculator implementations",
				"2.0 added ConvertUtil class",
				"3.0 added CalcInteger class",
				"4.0 added CalcDouble class",
				"5.0 added CalcBigDecimal class",
				"5.1 added demoCalculator",
				"5.1 added demoBigDecimal",
				"5.4 added use of MathContext for BigDecimal unresolving divide (infinite decimals like 22/7)",
				"** END OF DEVELOPMENT LOG ** DO NOT DELETE **"
		};
		REVISION = MAJOR_REVISION + "." + MINOR_REVISION + "." + DEV_LOG.length;	
	}
	
	
	/*
	 * ConvertUtil class a String Conversion Utility
	 */
	private static class ConvertUtil {
		/**
		 * Convert from String representation of an integer to an int
		 * @param s		String object
		 * @return		integer value
		 */
		public static int stringToInt(String s) {
			return new ConvertUtil().parseInt(s);
		}
		public int parseInt(String s) {
			int n = 0;
			
			try {
				n = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				System.err.println("** ERROR: INVALID STRING FORMAT OF INT \'" + s + "\'");
				e.printStackTrace();
			}
			
			return n;
		}
		/**
		 * Convert from String representation of an double to an double
		 * @param s		String object
		 * @return		integer value
		 */
		public static double stringToDouble(String s) {
			return new ConvertUtil().parseDouble(s);
		}
		public double parseDouble(String s) {
			double d = 0;
			
			try {
				d = Double.parseDouble(s);
			} catch (NumberFormatException e) {
				System.err.println("** ERROR: INVALID STRING FORMAT OF DOUBLE \'" + s + "\'");
				e.printStackTrace();
			}
			
			return d;
		}
		public static void demo() {
			System.out.println(ConvertUtil.class.getName() + ".demo()...");
			
			String s1 = "3";
			String s2 = "7.7";
			System.out.println(s1 + " = " + ConvertUtil.stringToInt(s1));
			System.out.println(s2 + " = " + ConvertUtil.stringToDouble(s2));
			
			System.out.println(ConvertUtil.class.getName() + ".demo()...");
		}
	}
	/**
	 * CalabiilityAPI interface API implemented by each calculator implementation
	 */
	private interface CalcabilityAPI {
		public enum CalculatorOperation {
			ADDITION(" + "), SUBTRACTION(" - "), MULTIPLICATION(" * "), DIVISION(" / ");
			private String symbol = " + ";
			private CalculatorOperation(String s) { symbol = s; } 
			public String getSymbol() { return symbol; }
		}
		String op(String a, String b, CalculatorOperation op);
	}
	
	/**
	 * CalInteger class an integer calculator implementation
	 */
	private static class CalcInteger implements CalcabilityAPI {
		private int a = 0;
		private int b = 0;
		private int result = 0;
		private CalculatorOperation op = CalculatorOperation.ADDITION;
		@Override
		public String op(String s1, String s2, CalculatorOperation op) {
			this.a = new ConvertUtil().parseInt(s1);
			this.b = new ConvertUtil().parseInt(s2);
			this.op = op;
			
			switch (op) {
			case ADDITION:
				result = a+b;
				break;
			case SUBTRACTION:
				result = a-b;
				break;
			case MULTIPLICATION:
				result = a*b;
				break;
			case DIVISION:
				result = a/b;
				break;
			}
			return Integer.toString(result);
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("CalcInteger:\t");
			
			sb.append(a);
			sb.append(op.getSymbol());
			sb.append(b);
			sb.append(" = ").append(result);
			
			return sb.toString();
		}
	}
	
	/**
	 * CalDouble class a double precision floating point calculator implementation
	 */
	private static class CalcDouble implements CalcabilityAPI {
		private double a = 0;
		private double b = 0;
		private double result = 0;
		private CalculatorOperation op = CalculatorOperation.ADDITION;
		@Override
		public String op(String s1, String s2, CalculatorOperation op) {
			this.a = new ConvertUtil().parseDouble(s1);
			this.b = new ConvertUtil().parseDouble(s2);
			this.op = op;
			
			switch (op) {
			case ADDITION:
				result = a+b;
				break;
			case SUBTRACTION:
				result = a-b;
				break;
			case MULTIPLICATION:
				result = a*b;
				break;
			case DIVISION:
				result = a/b;
				break;
			}
			return Double.toString(result);
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("CalcDouble:\t");
			
			sb.append(a);
			sb.append(op.getSymbol());
			sb.append(b);
			sb.append(" = ").append(result);
			
			return sb.toString();
		}
	}

	/**
	 * CalcBigDecimal class a BigDecimal calculator implementation
	 */
	private static class CalcBigDecimal implements CalcabilityAPI {
		private BigDecimal a = null;
		private BigDecimal b = null;
		private BigDecimal result = null;
		private CalculatorOperation op = CalculatorOperation.ADDITION;
		
		/**
		 * op performs specified arithmetic operation
		 * 
		 * @param first String operand
		 * @param second String operand
		 * @param specified arithmetic operation
		 * 
		 * @return String representation of result
		 */
		@Override
		public String op(String s1, String s2, CalculatorOperation op) {
			this.a = new BigDecimal(s1);
			this.b = new BigDecimal(s2);
			this.op = op;
			op(a, b, op);

			return result.toString();
		}
		/**
		 * op performs specified arithmetic operation
		 * 
		 * @param first BigDecimal operand
		 * @param second BigDecimal operand
		 * @param specified arithmetic operation
		 * 
		 * @return BigDecimal representation of result
		 */
		public BigDecimal op(BigDecimal d1, BigDecimal d2, CalculatorOperation op) {
			this.a = d1;
			this.b = d2;
			this.op = op;
			
			switch (op) {
			case ADDITION:
				result = a.add(b);
				break;
			case SUBTRACTION:
				result = a.subtract(b);
				break;
			case MULTIPLICATION:
				result = a.multiply(b);
				break;
			case DIVISION:
				MathContext mc = new MathContext(9, RoundingMode.HALF_UP);
				result = a.divide(b, mc);
				break;
			}
			return result;
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("CalcBigDecimal:\t");
			
			sb.append(a);
			sb.append(op.getSymbol());
			sb.append(b);
			sb.append(" = ").append(result);
			
			return sb.toString();
		}
	}
	
	/**
	 * use the listed calculators to perform a set of arithmetic operations on supplied operands
	 * @param a					first String operand
	 * @param b					second String operand
	 * @param calculatorList	specified calculators to use for arithmetic operations
	 */
	public void demoCalculator(String a, String b, List<CalcabilityAPI> calculatorList) {
		
		System.out.println("addition \t" + a + ", " + b + " ...");
		for (CalcabilityAPI calculate : calculatorList) {
			calculate.op(a, b, CalcabilityAPI.CalculatorOperation.ADDITION);
			System.out.println(calculate);
		}
		System.out.println("subtraction \t" + a + ", " + b + " ...");
		for (CalcabilityAPI calculate : calculatorList) {
			calculate.op(a, b, CalcabilityAPI.CalculatorOperation.SUBTRACTION);
			System.out.println(calculate);
		}
		System.out.println("multiplication \t" + a + ", " + b + " ...");
		for (CalcabilityAPI calculate : calculatorList) {
			calculate.op(a, b, CalcabilityAPI.CalculatorOperation.MULTIPLICATION);
			System.out.println(calculate);
		}
		System.out.println("division \t" + a + ", " + b + " ...");
		for (CalcabilityAPI calculate : calculatorList) {
			calculate.op(a, b, CalcabilityAPI.CalculatorOperation.DIVISION);
			System.out.println(calculate);
		}
	}
	
	/**
	 * demoBigDecimal uses supplied BigDecimal operands to perform an arithmetic calculation
	 * 
	 * @param a		first c operand
	 * @param b		second BigDecimal operand
	 * @param op	specified arithmetic operation
	 * @return		BigDecimal result
	 */
	public BigDecimal demoBigDecimal(BigDecimal a, BigDecimal b, CalcabilityAPI.CalculatorOperation op) {		
		return new CalcBigDecimal().op(a, b, op);
	}
	
	/**
	 * demoBigDecimal use a BigDecimal calculator implementation to perform a set of calculations
	 */
	public static void demoBigDecimal() {
		System.out.println("\nBigDecimal...");
		
		BigDecimal x = new BigDecimal("1");
		BigDecimal y = new BigDecimal("32");
		
		DemoCalculator c = new DemoCalculator();
		
		System.out.println(x + " + " + y + " = " + c.demoBigDecimal(x, y, CalcabilityAPI.CalculatorOperation.ADDITION));
		System.out.println(x + " - " + y + " = " + c.demoBigDecimal(x, y, CalcabilityAPI.CalculatorOperation.SUBTRACTION));
		System.out.println(x + " * " + y + " = " + c.demoBigDecimal(x, y, CalcabilityAPI.CalculatorOperation.MULTIPLICATION));
		System.out.println(x + " / " + y + " = " + c.demoBigDecimal(x, y, CalcabilityAPI.CalculatorOperation.DIVISION));
		System.out.println("BigDecimal... done!\n");
	}
	
	/**
	 * demoCalculator creates list of calculator implementations to use abstractly to perform the same calculations
	 */
	public static void demoCalculator() {
		System.out.println("\nCalculators...");
		
		CalcabilityAPI[] aCalculator = {
				new CalcInteger(),
				new CalcDouble(),
				new CalcBigDecimal(),
		};
		List<CalcabilityAPI> calculatorList = new ArrayList<DemoCalculator.CalcabilityAPI>(Arrays.asList(aCalculator));
		
		DemoCalculator c = new DemoCalculator();
		
		/**
		 * compare results using different calculators
		 */		
		{
			String a = "6";
			String b = "3";
			c.demoCalculator(a, b, calculatorList);
		}
		
		{
			String a = "1";
			String b = "32";
			c.demoCalculator(a, b, calculatorList);
		}
		
		{
			String a = "22";
			String b = "7";
			c.demoCalculator(a, b, calculatorList);
		}
		
		System.out.println("Calculators... done!\n");
	}
	
	/**
	 * demonstrate the use of this DemoCalculator class
	 */
	public static void demo() {
		System.out.println("\n\t" + DemoCalculator.class.getName() + ".demo() [ version " + REVISION + " ]...");

		DemoCalculator.demoBigDecimal();
		DemoCalculator.demoCalculator();
		
		{
			BigDecimal a = new BigDecimal("1");
			BigDecimal b = new BigDecimal("32");
			BigDecimal x = a.divide(b);
			System.out.println("result: " + x);
		}
		
		{
			BigDecimal a = new BigDecimal("22");
			BigDecimal b = new BigDecimal("7");
			
			MathContext m = new MathContext(9, RoundingMode.HALF_UP);
			BigDecimal x = a.divide(b,m);
			System.out.println("result: " + x);
		}

		
		System.out.println("\n\t" + DemoCalculator.class.getName() + ".demo() [ version " + REVISION + " ]... done!");
	}

}
