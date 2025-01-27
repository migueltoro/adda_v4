package us.lsi.basictypes;

import java.math.BigInteger;
import java.util.Random;

import us.lsi.common.String2;

public class TestLargeInteger {
	
	public static void test1() {
		LargeInteger x = LargeInteger.of("62345");
		LargeInteger y = LargeInteger.of("45");
		Integer n1 = x.size();
		Integer n2 = y.size();
		if (n1 < n2) x = x.completeZerosLeft(n2);
		if (n1 > n2) y = y.completeZerosLeft(n1);
		Integer n = x.size();
		Integer k = n / 2;
		Integer m = n - k;
		LargeInteger x1 = LargeInteger.ofLong(x.digits().subList(0, k));
		LargeInteger x0 = LargeInteger.ofLong(x.digits().subList(k, n));
		LargeInteger y1 = LargeInteger.ofLong(y.digits().subList(0, k));
		LargeInteger y0 = LargeInteger.ofLong(y.digits().subList(k, n));
		LargeInteger z2 = LargeInteger.multiply(x1, y1);
		LargeInteger z0 = LargeInteger.multiply(x0, y0);
		LargeInteger z1 = LargeInteger.multiply(x1.sum(x0), y1.sum(y0)).minus(z2.sum(z0));
		String2.toConsole("%s,%s,%s,%s",x,x1,x0,x1.addZerosRight(m).sum(x0));
		String2.toConsole("%s,%s,%s,%s",y,y1,y0,y1.addZerosRight(m).sum(y0));
		LargeInteger z22 = z2.addZerosRight(2*m);
		LargeInteger z11 = z1.addZerosRight(m);
		String2.toConsole("%s,%s,%s,%s,%s,%s",z2,z1,z0,z22,z11,z22.sum(z11).sum(z0));
	}
	
	public static void test2() {
		LargeInteger e1 = LargeInteger.of("2345");
		LargeInteger e2 = LargeInteger.of("45");
		LargeInteger e3 = LargeInteger.of("5");
		String2.toConsole("%s,%s,%s", e1, e2, e1.sum(e2));
		String2.toConsole("%s,%s,%s", e1, e2, e1.minus(e2));
		String2.toConsole("%s,%s,%s", e1, e2, e1.multiply(e2));
		String2.toConsole("%s,%s,%s", e3, e3, e3.multiply(e3));
	}
	
	public static void test3() {
		Random rd = new Random(System.nanoTime());
		Long e1 = (long)(rd.nextDouble()*1000);
		Long e2 = (long)(rd.nextDouble()*1000);
		LargeInteger x1 = LargeInteger.of(e1.toString());
		LargeInteger x2 = LargeInteger.of(e2.toString());
		String2.toConsole("%s,%s",e1+e2,x1.sum(x2));
	}
	
	public static void test4() {
		Random rd = new Random(System.nanoTime());
		Long e1 = (long)(rd.nextDouble()*1000);
		Long e2 = (long)(rd.nextDouble()*1000);
		LargeInteger x1 = LargeInteger.of(e1.toString());
		LargeInteger x2 = LargeInteger.of(e2.toString());
		String2.toConsole("%s,%s",e1-e2,x1.minus(x2));
	}
	
	public static void test5() {
		Random rd = new Random(System.nanoTime());
		Long e1 = (long)(rd.nextDouble()*1000);
		Long e2 = (long)(rd.nextDouble()*1000);
		LargeInteger x1 = LargeInteger.of(e1.toString());
		LargeInteger x2 = LargeInteger.of(e2.toString());
		String2.toConsole("%s,%s",e1<=e2,x1.compareTo(x2)<=0);
	}
	
	public static void test6() {
		Random rd = new Random(System.nanoTime());
		Long e1 = (long)(rd.nextDouble()*10000000);
		Long e2 = (long)(rd.nextDouble()*10000000);
		LargeInteger x1 = LargeInteger.of(e1.toString());
		LargeInteger x2 = LargeInteger.of(e2.toString());
		String2.toConsole("%s,%s",e1*e2,x1.multiply(x2));
	}
	
	public static void test7() {
		Random rd = new Random(System.nanoTime());
		Long e1 = (long)(rd.nextDouble()*10000000);
		LargeInteger x1 = LargeInteger.of(e1.toString());
		BigInteger x2 = new BigInteger(e1.toString());
		String2.toConsole("%s,\n%s",x1.pow(20),x2.pow(20));
	}

	public static void main(String[] args) {
		test7();
	}

}
