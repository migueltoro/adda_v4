package us.lsi.geometria;
import java.util.*;


public class TestPunto {

	
	public static void main(String[] args) {
		Punto2D p1 = Punto2D.of(0.,0.);
		Punto2D p2 = Punto2D.of(1.,9.);
		Punto2D p3 = Punto2D.of(0.,10.);
		Punto2D p4 = Punto2D.of(0.,0.);
		Punto2D p5 = Punto2D.of(0.,0.);
		SortedSet<Punto2D> s = new TreeSet<Punto2D>();
		Set<Punto2D> s2 = new HashSet<Punto2D>();
		s.add(p1);
		s.add(p2);
		s.add(p3);
		s.add(p4);
		s.add(p5);
		s2.add(p1);
		s2.add(p2);
		s2.add(p3);
		s2.add(p4);
		System.out.println("p4  "+s2.contains(p4));
		System.out.println("p5  "+s2.contains(p5));
		System.out.println("p5 igual p4  "+p4.equals(p5));
		s2.add(p5);
		System.out.println("p4  "+s2.contains(p4));
		System.out.println("p5  "+s2.contains(p5));
		System.out.println("p5 igual p4  "+p4.equals(p5));
		Punto2D[] ap = {p1,p2,p3,p4,p5};
		int num = 0;
		for(Punto2D p : ap)
			if(p.equals(p1))
				num =num+1;
		System.out.println(num);
		num = 0;
		for(Punto2D p : s2)
			if(p.equals(p1))
				num =num+1;
		System.out.println(num);
		System.out.println("   --------");
		for(Punto2D p: s)
			System.out.println(p.toString());
		System.out.println("--------");
		for(Punto2D p: s2)
			System.out.println(p.toString());
		System.out.println("--------");
	}
}
