package us.lsi.alg.puzzle;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import us.lsi.common.Arrays2;
import us.lsi.common.IntPair;
import us.lsi.common.Preconditions;
import us.lsi.graphs.virtual.VirtualVertex;
import us.lsi.streams.Stream2;

public record VertexPuzzleI(IntPair blackPosition,Integer[][] datos)
         implements VirtualVertex<VertexPuzzle, EdgePuzzle, ActionPuzzle>, VertexPuzzle {
	
	public static VertexPuzzle end = VertexPuzzle.of(1,2,3,4,6,5,8,7,0);
	
	public static VertexPuzzle copy(VertexPuzzle m) {
		VertexPuzzleI m2 = (VertexPuzzleI) m;
		return new VertexPuzzleI(m2.blackPosition, m2.datos);
	}

	/**
	 * @param d Lista de valores del puzzle dados por filas de arriba abajo
	 * @return Un EstadoPuzzle
	 */
	
	public static VertexPuzzle of(Integer... d) {
		Integer dt[][] = Arrays2.toMultiArray(d, VertexPuzzle.n, VertexPuzzle.n);	
		IntPair bp = Arrays2.findPosition(dt, e->e==0);
		Preconditions.checkArgument(validData(dt),"No es valido");
		return VertexPuzzleI.of(dt,bp);
	}
	
	private static Boolean validData(Integer[][] datos) {
		Set<Integer> s = Arrays.stream(datos)
				.flatMap(f->Arrays.stream(f))
				.filter(e->VertexPuzzleI.validDato(e))
				.collect(Collectors.toSet());
		return s.size()== n*n;
	}	
	
	public static VertexPuzzleI of(Integer[][] datos, IntPair blackPosition) {
		Integer[][] dt = Arrays2.copyArray(datos);
		VertexPuzzleI r = new VertexPuzzleI(blackPosition,dt);
		Preconditions.checkArgument(r.isValid(),"No es válido");
		return r;
	}
	
	@Override
	public Integer getInvCount() {
		return getInvCount(this.datos());
	}
	
	private static int getInvCount(Integer[][] dt) {
		List<Integer> d = Stream2.allPairs(0,3,0,3)
				.map(p->dt[p.first()][p.second()])
				.collect(Collectors.toList());
	    int inv_count = 0;
	    for (int i = 0; i < 9 - 1; i++)
	        for (int j = i + 1; j < 9; j++)
	            if (d.get(j) >0 && d.get(i) >0 &&  d.get(i) > d.get(j))
	                inv_count++;
	    return inv_count;
	}
	
	public static boolean isSolvable2(Integer[][] d1, Integer[][] d2) {
	    Integer n1 = getInvCount(d1)%2;
	    Integer n2 = getInvCount(d2)%2;
	    return n1 == n2;
	}
	 
	public static boolean isSolvable(Integer[][] puzzle) {
	    int invCount = getInvCount(puzzle);
	    return (invCount % 2 == 0);
	}
	
	public Boolean isSolvable(VertexPuzzle other) {
		VertexPuzzleI other2 = (VertexPuzzleI) other;
		return isSolvable2(this.datos(),other2.datos());
	}
	
	@Override
	public Boolean isSolvable() {
		return isSolvable(this.datos);
	}

	
	@Override
	public Integer numFilas() {
		return VertexPuzzle.numFilas;
	}

	private static boolean validDato(Integer d) {
		return 0<=d && d < VertexPuzzle.numFilas*VertexPuzzle.numFilas;
	}
	
	public static Boolean validPosition(IntPair p) {
		return p.first()>=0 && p.first()< VertexPuzzle.numFilas && p.second()>=0 && p.second()<VertexPuzzle.numFilas;
	}
	
	@Override
	public Map<Integer,IntPair> positions(){
		return Stream2.allPairs(0,VertexPuzzleI.n,0,VertexPuzzleI.n)
				.collect(Collectors.toMap(p->datos()[p.first()][p.second()],p->p));
	}
	
	@Override
	public Boolean isValid() {
		return validData(this.datos()) && get(blackPosition()) == 0;
	}

	@Override
	public List<ActionPuzzle> actions() {
    	return Stream.of(ActionPuzzle.values())
				.filter(a->VertexPuzzleI.validPosition(this.blackPosition().add(ActionPuzzle.direction(a))))
				.collect(Collectors.toList());
	}
	
	@Override
	public VertexPuzzleI neighbor(ActionPuzzle a) {
		return this.swap(this.blackPosition().add(ActionPuzzle.direction(a)));
	}
	
	@Override
	public VertexPuzzleI swap(IntPair np) {
		IntPair op = this.blackPosition();
		Integer dd[][] = Arrays2.copyArray(this.datos());
		Integer value = dd[np.first()][np.second()];
		dd[op.first()][op.second()] = value;
		dd[np.first()][np.second()] = 0;
		VertexPuzzleI v  = VertexPuzzleI.of(dd,np);
		return v;
	}
	
	@Override
	public Integer get(Integer f, Integer c) {
		return get(IntPair.of(f, c));
	}
	
	@Override
	public Integer get(IntPair p) {
		Preconditions.checkArgument(validPosition(p),"No se cumple la precondici�n");
		return datos[p.first()][p.second()];
	}
	
	@Override
	public Integer getNumDiferentes(VertexPuzzle e){
		Integer n = VertexPuzzleI.numFilas;
		Long s = IntStream.range(0,n).boxed()
				.flatMap(f->IntStream.range(0,n).boxed().map(c->IntPair.of(f, c)))
				.filter(p->this.get(p) != e.get(p))
				.count();
		return s.intValue();
	}

	@Override
	public String toString() {
		String s = IntStream.range(0,VertexPuzzleI.numFilas).boxed()
				.map(y->fila(y))
				.collect(Collectors.joining("\n", "", ""));
		return s+"\n"+this.blackPosition();
	}

	private String fila(Integer y) {
		Integer n = VertexPuzzleI.numFilas;
		return IntStream.range(0,n).boxed()
				.map(j->datos[y][j].toString()).collect(Collectors.joining("|", "|", "|"));
	}

	@Override
	public EdgePuzzle edge(ActionPuzzle a) {
		return  EdgePuzzle.of(this, this.neighbor(a), a);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blackPosition == null) ? 0 : blackPosition.hashCode());
		result = prime * result + Arrays.deepHashCode(datos);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VertexPuzzleI other = (VertexPuzzleI) obj;
		if (blackPosition == null) {
			if (other.blackPosition != null)
				return false;
		} else if (!blackPosition.equals(other.blackPosition))
			return false;
		if (!Arrays.deepEquals(datos, other.datos))
			return false;
		return true;
	}

	@Override
	public Boolean goal() {
		return this.equals(VertexPuzzleI.end);
	}

	@Override
	public Boolean goalHasSolution() {
		return true;
	}
	
	@Override
	public ActionPuzzle greedyAction() {
		Random r = new Random();
		List<ActionPuzzle> actions = this.actions();
		Integer n = actions.size();
		return actions.get(r.nextInt(n));
	}
	
}
