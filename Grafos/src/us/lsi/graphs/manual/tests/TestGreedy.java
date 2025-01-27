package us.lsi.graphs.manual.tests;



import java.util.function.Predicate;
import us.lsi.common.TriFunction;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.manual.GraphPath;
import us.lsi.graphs.manual.VirtualGraph;
import us.lsi.graphs.manual.recorridos.GlobalValues;
import us.lsi.graphs.manual.recorridos.Greedy;
import us.lsi.graphs.manual.recorridos.State;

public class TestGreedy {
	

	public static void main(String[] args) {
		Mapa m = Mapa.parse("ficheros/ciudades.txt", 
				"ficheros/carreteras.txt", 
				VirtualGraph.GraphType.UNDIRECTED,
				VirtualGraph.TraverseType.FORWARD);
		System.out.println(m);
		Ciudad c1 = m.ciudad("Sevilla");
		Ciudad c2 = m.ciudad("Algeciras");
		Predicate<Ciudad> end = x->x.equals(c2);
		TriFunction<State<Ciudad, Carretera>,GlobalValues<Ciudad, Carretera>, Ciudad, Ciudad> gv = 
				(s,g,v)-> m.closestVertices(v).stream().filter(x->!s.contains(x)).findFirst().get();
		GraphPath<Ciudad, Carretera> pbt = 
				Greedy.greedy(c1,end,m,gv);
		System.out.println("Weight = "+pbt.weight());
		System.out.println("MinPath "+pbt.vertices());

	}
}
