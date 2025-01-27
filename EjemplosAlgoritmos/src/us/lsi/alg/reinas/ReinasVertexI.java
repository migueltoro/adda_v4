package us.lsi.alg.reinas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.common.List2;
import us.lsi.common.IntegerSet;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record ReinasVertexI(Integer index, List<Integer> fo, IntegerSet dpo, IntegerSet dso)
                   implements ReinasVertex {
	
	public static ReinasVertex copy(ReinasVertexI reinas) {
		return new ReinasVertexI(reinas.index,List2.copy(reinas.fo),IntegerSet.copy(reinas.dpo),IntegerSet.copy(reinas.dso));
	}

	public static ReinasVertexI of(Integer index, List<Integer> fo, IntegerSet dpo, IntegerSet dso) {
		return new ReinasVertexI(index, fo, dpo, dso);
	}

	public static ReinasVertexI first() {
		return new ReinasVertexI(0,List2.empty(),IntegerSet.empty(-n,10),IntegerSet.empty());
	}
	
	public static ReinasVertex last() {
		return new ReinasVertexI(n,List2.empty(),IntegerSet.empty(-n,10),IntegerSet.empty());
	}
	
	@Override
	public Boolean goal() {
		return this.index == ReinasVertexI.n;
	}
	
	@Override
	public Boolean goalHasSolution() {
		return this.errores() == 0;
	}
	
	@Override
	public Integer greedyAction() {
		Random r = new Random();
		List<Integer> actions = this.actions();
		Integer n = actions.size();
		return actions.get(r.nextInt(n));
	}
	
	
//	public final Integer errores;
	public static Integer n; // numero de reinas

	
	@Override
	public Integer errores() {
		return 3*ReinasVertexI.n-this.fo.size()-this.dpo.size()-this.dso.size();
	}

	@Override
	public Boolean isValid() {
		return this.index >=0 && this.index <= ReinasVertexI.n;
	}
	
	@Override
	public Integer size() {
		return ReinasVertexI.n - this.index;
	}

	@Override
	public List<Integer> actions() {
		List<Integer> r = IntStream.range(0,ReinasVertexI.n).boxed()
				.filter(e->!this.fo.contains(e) && !this.dpo.contains(e-this.index) && !this.dso.contains(e+this.index))
				.collect(Collectors.toList());
		return r;
	}

	@Override
	public ReinasVertex neighbor(Integer a) {
		Integer index = this.index+1;
		List<Integer> fo = new ArrayList<>(this.fo); fo.add(a);
		IntegerSet dpo = this.dpo.addF(a-this.index);
		IntegerSet dso = this.dso.addF(a+this.index);
		return ReinasVertexI.of(index, fo, dpo, dso);
	}

	@Override
	public SimpleEdgeAction<ReinasVertex, Integer> edge(Integer a) {
		return SimpleEdgeAction.of(this,this.neighbor(a), a, 1.);
	}

}
