package us.lsi.alg.sudoku;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jgrapht.GraphPath;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public class SolucionSudoku implements Comparable<SolucionSudoku>{	
	
	public static SolucionSudoku of(GraphPath<SudokuVertex,SimpleEdgeAction<SudokuVertex,Integer>> gp) {
		return new SolucionSudoku(gp.getEndVertex());
	}
	
	public static SolucionSudoku of(SudokuVertex vertex) {
		return new SolucionSudoku(vertex);
	}
	
	public SolucionSudoku(SudokuVertex vertex) {
		super();
		this.vertex = vertex;
		this.map = this.vertex.casillas().stream()
				.collect(Collectors.toMap(cs->CasFC.of(cs.f(),cs.c()),cs->cs));
	}

	private SudokuVertex vertex;
	private Map<CasFC,Casilla> map;
	
	public static record CasFC(Integer f, Integer c) {
		public static CasFC of(Integer f, Integer c) {
			return new CasFC(f, c);
		}
	}
	
	public Casilla casilla(Integer p) {
		return this.vertex.casillas().get(p);
	}
	
	public Casilla casilla(Integer f, Integer c) {
		CasFC cs = CasFC.of(f, c);
		return map.get(cs);
	}
	
	
	private String fila(Integer f) {
		return IntStream.range(0, DatosSudoku.nf).boxed()
				.map(c -> this.casilla(f, c).stringValue())
				.collect(Collectors.joining(" "));
	}
	
	@Override
	public String toString() {
		Integer errores = vertex.errores();
		return IntStream.range(0,DatosSudoku.nf)
			    .boxed()
			    .map(f->fila(f))
			    .collect(Collectors.joining("\n",
			    		"Errores = "+errores+"\n\n","\n__________\n"));
	}
	
	public Integer errores() {
		return vertex.errores();
	}

	@Override
	public int compareTo(SolucionSudoku other) {
		return this.errores().compareTo(other.errores());
	}
	
}
