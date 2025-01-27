package us.lsi.clase.maximocomundivisor;

public record Mcd(Integer a, Integer b) {
	
	public static Mcd of(Integer a, Integer b) {
		return new Mcd(a, b);
	}
	
	public Mcd {
		if (a < 0 || b < 0) {
			throw new IllegalArgumentException("Los números deben ser positivos");
		}
	}
	
	public Mcd nx() {
		Integer a = this.b;
		Integer b = this.a % this.b;
		return Mcd.of(a, b);
	}
	
	public Boolean g() {
		return this.b > 0;
	}
	
	public Integer r() {
		return this.a;
	}

}
