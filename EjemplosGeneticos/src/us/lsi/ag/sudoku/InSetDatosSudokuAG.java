package us.lsi.ag.sudoku;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import us.lsi.ag.ValuesInSetData;
import us.lsi.alg.sudoku.Casilla;
import us.lsi.alg.sudoku.DatosSudoku;
import us.lsi.alg.sudoku.SolucionSudoku;
import us.lsi.alg.sudoku.SudokuVertex;
import us.lsi.common.Preconditions;


public class InSetDatosSudokuAG implements ValuesInSetData<SolucionSudoku> {


	SudokuVertex sv;
	Map<Integer,List<Integer>> values = new HashMap<>();
	public List<Integer> decode;
	
	public InSetDatosSudokuAG(SudokuVertex sv) {	
		super();
		this.sv = sv;	  
		for(int i = sv.index(); i<DatosSudoku.n;i++) {
			Integer ic = i;
			Casilla c = sv.casillas().get(ic);
			List<Integer> r = sv.valoresLibresEnCasilla(c).stream().toList();
			values.put(i-sv.index(), r);
		}	
	}
	
	@Override
	public Integer size() {
		return DatosSudoku.n-sv.index();
	}

	@Override
	public List<Integer> values(Integer index) {
		return  values.get(index);
	}
	
	
	public Map<Integer, List<Integer>> values() {
		return  values;
	}
	
	@Override
	public Double fitnessFunction(List<Integer> dc) {
		this.decode = dc;
		SolucionSudoku s = solucion(dc);
		return -(double)s.errores();
	}
	
	@Override
	public SolucionSudoku solucion(List<Integer> dc) {
		Preconditions.checkArgument(dc.size()==this.size(),String.format("%d,%d",dc.size(),this.size()));
		IntStream.range(0,this.size()).forEach(i->sv.casilla(this.sv.index()+i).setValue(dc.get(i)));	
		SolucionSudoku s = SolucionSudoku.of(sv);
		return s;
	}

}
