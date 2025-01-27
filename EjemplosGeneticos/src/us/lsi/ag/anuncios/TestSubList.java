package us.lsi.ag.anuncios;

import java.util.List;

import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.PermutationSubList2Chromosome;

public class TestSubList {

	public static void main(String[] args) {
		SeqNormalData<?> d = new DatosAnunciosAG("ficheros/anuncios.txt");
		System.out.println(d.normalSequence());
		PermutationSubList2Chromosome.iniValues((SeqNormalData<Object>)d);
		PermutationSubList2Chromosome c =  PermutationSubList2Chromosome.getInitialChromosome();
		System.out.println(PermutationSubList2Chromosome.DIMENSION);
		System.out.println(d.itemsNumber());
		List<Integer> v = c.decode();
		System.out.println(v);
		System.out.println(v.size());
	}

}
