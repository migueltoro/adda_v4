package us.lsi.geometria;



public interface ObjetoGeometrico2D {
	/**
	 * @param p 
	 * @param angulo
	 * @return El objeto geom�trico rotado el �ngulo dado con respecto al punto p
	 */
	ObjetoGeometrico2D rota(Punto2D p, Double angulo);
	/**
	 * @param v
	 * @return El objeto geom�trico trasladado seg�n el vector v
	 */
	ObjetoGeometrico2D traslada(Vector2D v);
	/**
	 * @param p
	 * @param factor
	 * @return El objeto geom�trico homot�tico con respecto a p y el factor dado
	 */
	ObjetoGeometrico2D homotecia(Punto2D p, Double factor);
	/**
	 * @param r
	 * @return La proyecci�n del objeto geom�trico sobre la recta r
	 */
	ObjetoGeometrico2D proyectaSobre(Recta2D r);
	/**
	 * @param r
	 * @return El objeto geom�trico simetrico con respecto a la recta r
	 */
	ObjetoGeometrico2D simetrico(Recta2D r);
		
}
