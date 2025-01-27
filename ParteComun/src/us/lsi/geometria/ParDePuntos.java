package us.lsi.geometria;


public record ParDePuntos(Punto2D p1,Punto2D p2) implements Comparable<ParDePuntos>{

	public static ParDePuntos create(Punto2D p1, Punto2D p2) {
		return new ParDePuntos(p1, p2);
	}
	
	public Double distancia(){
		return p1.distanciaA(p2);
	}
	
	@Override
	public int compareTo(ParDePuntos a) {
		// TODO Auto-generated method stub
		int r = this.distancia().compareTo(a.distancia());
		if(r==0){
			r = this.p1().compareTo(a.p1());
		}
		if(r==0){
			r = this.p2().compareTo(a.p2());
		}
		return r;
	}

	@Override
	public String toString() {
		return "[" + p1 + "," + p2 + ","+this.distancia()+"]";
	}
	
	
}
