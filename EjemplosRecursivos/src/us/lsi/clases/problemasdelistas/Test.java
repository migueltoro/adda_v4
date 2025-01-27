package us.lsi.clases.problemasdelistas;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import us.lsi.common.String2;

public abstract class Test extends us.lsi.basictypes.Test {
	
	public static void test1() {
		List<Integer> ls = new ArrayList<>(Arrays.asList(1,2,3,4,5,5,5,5,5,5));
		String2.toConsole("%s",ProblemasDeListas.masDeLaMitad(ls));
		String2.toConsole("%s",ProblemasDeListas.masDeLaMitad2(ls,Comparator.naturalOrder()));
	}
	
	public static void test2() {
		List<Double> ls = List.of(-1.,6.,-2.,5.,-1.,4.,3.,-4.,3.);
		String2.toConsole("%s",ProblemasDeListas.getSubSecuenciaMaxima(ls));
	}
	
	public static void test3() {
		List<Double> ls1 = Arrays.asList(-1.,6.,-2.,5.,-1.,4.,3.,-4.,3.);
		List<Double> ls2 = new ArrayList<>(ls1);
		String2.toConsole("%s == %s",ProblemasDeListas.banderaHolandesa(ls1,ls1.get(0),Comparator.naturalOrder()),ls1);
		String2.toConsole("%s == %s",ProblemasDeListas.banderaHolandesaF(ls2,ls2.get(0),Comparator.naturalOrder()),ls2);
	}

	public static void main(String[] args) {
		test3();
	}

}
