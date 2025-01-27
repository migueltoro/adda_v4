package us.lsi.alg.monedas;

public record Moneda(Integer valor,Integer peso) {
	
	public static Moneda of(Integer valor, Integer peso) {
		return new Moneda(valor, peso);
	}
	
	public static Moneda parse(String linea) {
		String[] p = linea.split(",");
		return new Moneda(Integer.parseInt(p[0]),Integer.parseInt(p[1]));
	}
	
	public Double pesoUnitario() {
		return (1.*this.peso)/this.valor;
	}

	@Override
	public String toString() {
		return String.format("(%d,%d,%.2f)",this.valor,this.peso,this.pesoUnitario());
	}
	
	
	
	
}
