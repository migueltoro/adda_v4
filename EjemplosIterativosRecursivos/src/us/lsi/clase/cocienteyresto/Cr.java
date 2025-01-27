package us.lsi.clase.cocienteyresto;

public record Cr(Integer a, Integer b, Integer c, Integer r) {
	
	public static Cr of(Integer a, Integer b) {
		return new Cr(a,b,0,a);
	}
	
	public static Cr of(Integer a, Integer b, Integer c, Integer r) {
		return new Cr(a,b,c,r);
	}
	
	public Boolean g() {
		return r-b>0;
	}
	
	public Cr next() {
		return Cr.of(a,b,c+1,r-b);
	}
	
}
