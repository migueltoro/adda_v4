package us.lsi.streams;

import java.util.List;

public class TestCollectors2 {

	public static void main(String[] args) {
		List<String> ls2 = List.of("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17");
		System.out.println(ls2.stream().map(x->Integer.parseInt(x)).collect(Collectors2.toIntegerSet()));

	}

}
