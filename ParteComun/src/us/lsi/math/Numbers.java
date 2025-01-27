package us.lsi.math;

import java.math.BigInteger;

public class Numbers {
	
	public static Long stringToLong(String s){
		Long r = null;
		try {
			r = Long.parseLong(s);
		} catch(NumberFormatException e){
			throw new IllegalArgumentException("La cadena "+s+" no constiuye un Long");
		}
		return r;
	}
	public static Integer stringToInteger(String s){
		Integer r = null;
		try {
			r = Integer.parseInt(s);
		} catch(NumberFormatException e){
			throw new IllegalArgumentException("La cadena "+s+" no constiuye un Integer");
		}
		return r;
	}
	public static Float stringToFloat(String s){
		Float r = null;
		try {
			r = Float.parseFloat(s);
		} catch(NumberFormatException e){
			throw new IllegalArgumentException("La cadena "+s+" no constiuye un Float");
		}
		return r;
	}
	public static Double stringToDouble(String s){
		Double r = null;
		try {
			r = Double.parseDouble(s);
		} catch(NumberFormatException e){
			throw new IllegalArgumentException("La cadena "+s+" no constiuye un Double");
		}
		return r;
	}
	public static BigInteger stringToBigInteger(String s){
		BigInteger r = null;
		try {
			r = new BigInteger(s);
		} catch(NumberFormatException e){
			throw new IllegalArgumentException("La cadena "+s+" no constiuye un BigInteger");
		}
		return r;
	}
}
