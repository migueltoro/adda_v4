package us.lsi.clase.palindromo;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import us.lsi.common.IntPair;

public class Palindromo {
	
	public static boolean esPalindromo1(String st) {
		UnaryOperator<IntPair> next = p -> IntPair.of(p.first()+1, p.second()-1);
		Stream<IntPair> s = Stream.iterate(IntPair.of(0,st.length()),p->(p.second()-p.first())> 1, next);
		return s.allMatch(p->st.charAt(p.first()) == st.charAt(p.second()-1));
	}
	
	public static boolean esPalindromo2(String p) {
		int i = 0;
		int j = p.length();
		Boolean b = true;
		while(j - i > 1 && b) {	
			i = i + 1;
			j = j - 1;
			b = p.charAt(i) == p.charAt(j-1);
		}
		return b;
	}
	
	public static boolean esPalindromo3(String p) {
		return esPalindromo3(p,0,p.length(),true);
	}
	private static boolean esPalindromo3(String p, int i, int j, Boolean b) {
		if (j - i > 1 & b) {
			b = esPalindromo3(p, i + 1, j - 1, p.charAt(i) == p.charAt(j-1));
		}
		return b;
	}
	
	public static boolean esPalindromo4(String p) {
		return esPalindromo4(0,p.length(),p);
	}
	private static boolean esPalindromo4(int i, int j, String p) {
		boolean b = true;
		if (j - i > 1) {
			b = p.charAt(i) == p.charAt(j-1)  && esPalindromo4(i + 1, j - 1, p);
		}
		return b;
	}
	
	public static boolean esPalindromo5(String p) {
		return esPalindromo5(0,p.length(),p);
	}
	private static boolean esPalindromo5(int i, int j, String p) {
		boolean b = true;
		if (j - i > 1) {
			if(b = p.charAt(i) != p.charAt(j-1)) b = false;
			else b = esPalindromo5(i + 1, j - 1, p);
		}
		return b;
	}

	public static void test4() {
		String text = "reordenanedroer";
		String text2 = "reordenanefroer";
		System.out.println(String.format("4: %b, %b, %b, %b, %b",
				esPalindromo1(text),esPalindromo2(text),esPalindromo3(text),esPalindromo4(text),esPalindromo3(text),esPalindromo5(text)));
		System.out.println(String.format("5: %b, %b, %b, %b, %b",
				esPalindromo1(text2),esPalindromo2(text2),esPalindromo3(text2),esPalindromo4(text2),esPalindromo5(text2)));
	}

}
