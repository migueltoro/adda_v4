package us.lsi.clase.multiplica;

public class Multiplica {
	
	public static Integer multiplica1(Integer x, Integer y){
		Integer r = 0;
		if(y > 0) {
			r = 2*multiplica1(x,y/2)+(y % 2 == 0? 0: x);
		} 
		return r;
	}
	
	public static Integer multiplica2(Integer x, Integer y){
		Integer b = 0;
		Integer p = 1;
		while(y>0) {
			Integer cf = y % 2 == 0? 0: x;
			p = p*2;
			b = b + cf;
		} 
		return b;	
	}
	
	public static void test11() {
		Integer x = 1234;
		Integer y = 456;
		System.out.printf("%d,%d\n",x*y,multiplica1(x,y));
		System.out.printf("%d,%d\n",x*y,multiplica2(x,y));
	}
	

}
