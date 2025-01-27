package us.lsi.geometria;



public class TestGeometria {

	
	public static void main(String[] args) {
		Recta2D r = Recta2D.of(Punto2D.of(0., 0.), Punto2D.of(1., 1.));
		Recta2D rc = Recta2D.of(Punto2D.of(0., 0.), Punto2D.of(-1., 1.));
		Recta2D rm = Recta2D.of(Punto2D.of(2., 2.),Punto2D.of(-1.,-1.));
	}

}
