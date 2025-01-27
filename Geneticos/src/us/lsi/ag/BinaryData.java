package us.lsi.ag;

import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public interface BinaryData<S> extends ValuesInRangeData<Integer,S>{
	
	default Integer max(Integer i) {
		return 2;
	}
	
	default Integer min(Integer i) {
		return 0;
	}
	
	default ChromosomeType type() {
		return ChromosomeType.Binary;
	}

}
