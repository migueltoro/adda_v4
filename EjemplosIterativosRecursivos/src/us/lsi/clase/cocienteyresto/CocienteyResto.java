package us.lsi.clase.cocienteyresto;

import java.io.IOException;
import java.util.stream.Stream;

import us.lsi.common.String2;

public class CocienteyResto {
	
	public static Cr cocienteyRestoR(Integer a, Integer b) {
		return cocienteyRestoR(a,b,0,a);
	}
	public static Cr cocienteyRestoR(Integer a, Integer b, Integer c, Integer r) {
		Cr s = Cr.of(a,b,c,r);
		if(r-b>0){
			s = cocienteyRestoR(a,b,c+1,r-b);
		}
		return s;
	}
	
	public static Cr cocienteyRestoI(Integer a, Integer b) {
		Integer c = 0;
		Integer r = a;
		while(r-b>0){
			c = c+1;
			r = r-b;
		}
		return Cr.of(a,b,c,r);
	}
	
	public static Cr cocienteyRestoF(Integer a, Integer b) {
		Stream<Cr> st = Stream.iterate(Cr.of(a, b),e->e.next())
				.filter(e->!e.g());
		Cr r = st.findFirst().get();
		return r;
	}
	
	
	public static void test13() {
		Cr s = cocienteyRestoR(100,37);
		String2.toConsole("%s,%s",s,37*s.c()+s.r());
		s = cocienteyRestoI(100,37);
		String2.toConsole("%s,%s",s,37*s.c()+s.r());
		s = cocienteyRestoF(100,37);
		String2.toConsole("%s,%s",s,37*s.c()+s.r());
	}
	
	public static void main(String[] args) throws IOException {
		test13();
	}
	
}
