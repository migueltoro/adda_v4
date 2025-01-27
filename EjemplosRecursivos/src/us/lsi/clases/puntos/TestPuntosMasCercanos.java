package us.lsi.clases.puntos;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.common.Preconditions;
import us.lsi.geometria.ParDePuntos;
import us.lsi.geometria.Punto2D;



public class TestPuntosMasCercanos {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Punto2D> lista = Files2.streamFromFile("puntos.txt")
				.<Punto2D> map(
						s -> {
							String[] ps = s.split(",");
							Preconditions.checkArgument(ps.length == 2);
							return Punto2D.of(Double.parseDouble(ps[0]),Double.parseDouble(ps[1]));
						}).collect(Collectors.<Punto2D> toList());
		ParDePuntos r1 = ListasDePuntos.parMasCercano(lista);
		ParDePuntos r2 = ListasDePuntos.parMasCercanoBase(0, lista.size(), lista);
		System.out.println(r1+","+r2);
	}

}
