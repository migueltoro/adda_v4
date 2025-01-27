package us.lsi.ag.sudoku;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.ag.BlocksData;
import us.lsi.alg.sudoku.Casilla;
import us.lsi.alg.sudoku.DatosSudoku;
import us.lsi.alg.sudoku.SolucionSudoku;
import us.lsi.alg.sudoku.SudokuVertex;

public class BlocksDatosSudokuFilasAG implements BlocksData<SolucionSudoku>{
	
	SudokuVertex sv;
	public List<Integer> blocksLimits;
	public List<Integer> initialValues;
	public List<Integer> decode;
	
	public BlocksDatosSudokuFilasAG(SudokuVertex sv){
		super();
		this.sv = sv;
		Comparator<Casilla> cmp = Comparator.comparing(c->c.f());
		Collections.sort(sv.casillas().subList(sv.index(),DatosSudoku.n),cmp);
		this.blocksLimits = IntStream.range(sv.index(),DatosSudoku.n).boxed()
			.filter(i->i==sv.index() || sv.casilla(i).f() != sv.casilla(i-1).f())
			.map(i->i-sv.index())
			.collect(Collectors.toList());
		this.blocksLimits.set(0, 0);
		this.blocksLimits.add(DatosSudoku.n-sv.index());
		this.initialValues = IntStream.range(0,DatosSudoku.nf).boxed()
			.flatMap(f->DatosSudoku.allValues.difference(sv.valoresOcupadosEnFilas().get(f)).stream())
			.toList();
	}
	
	public Boolean check() {
		return IntStream.range(0, DatosSudoku.nf).boxed()
				.allMatch(f->DatosSudoku.allValues.difference(sv.valoresOcupadosEnFilas().get(f)).isEmpty());
	}

	@Override
	public Integer size() {
		return DatosSudoku.n-sv.index();
	}

	@Override
	public Double fitnessFunction(List<Integer> cr) {
		SolucionSudoku s = solucion(cr);
		return -(double)s.errores();
	}
	
	@Override
	public SolucionSudoku solucion(List<Integer> dc) {
		this.decode = dc;
		IntStream.range(0,this.size()).forEach(i->sv.casilla(this.sv.index()+i).setValue(dc.get(i)));	
		SolucionSudoku s = SolucionSudoku.of(sv);
		System.out.println(this.sv.valoresOcupadosEnFilas());
		return s;
	}

	@Override
	public List<Integer> blocksLimits() {
		return blocksLimits;
	}

	@Override
	public List<Integer> initialValues() {
		return initialValues;
	}

}
