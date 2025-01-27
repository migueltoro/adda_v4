package us.lsi.geometria;

public class TestRecta {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Recta2D r = Recta2D.of(Punto2D.of(0., 0.), Punto2D.of(2., 1.));
		Vector2D v = r.vector();
		Punto2D p = r.punto();
	}

}
