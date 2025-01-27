package us.lsi.mochila.datos;

/**
 * <p> Esta clase implementa el tipo ObjetoMochila.</p>
 * <p> Las propiedades de estos problemas son: </p>
 * <ul>
 * <li> C�digo
 * <li> Valor
 * <li> Peso
 * <li> N�mero m�ximo de unidades
 * </ul> 
 * 
 * 
 * 
 * @author Miguel Toro
 *
 */
public record ObjetoMochila(Integer codigo,Integer valor,Integer peso,Integer numMaxDeUnidades)
	implements Comparable<ObjetoMochila>{
	
	public static ObjetoMochila of(Integer valor,Integer peso, Integer numMaxDeUnidades) {
		Integer codigo = nCodigo;
		nCodigo++;
		return new ObjetoMochila(codigo,valor,peso, numMaxDeUnidades);
	}
	
	private static Integer nCodigo = 0;
	
	public static ObjetoMochila parse (String s){		
		String[] v = s.split("[ ,]");
		Integer ne = v.length;
		if(ne != 3) throw new IllegalArgumentException("Formato no adecuado en l�nea  "+s);	
		Integer valor = Integer.parseInt(v[0].trim());
		Integer peso = Integer.parseInt(v[1].trim());
		Integer numMaxDeUnidades = Integer.parseInt(v[2]);
		return ObjetoMochila.of(valor,peso,numMaxDeUnidades);
	}	
		
	public Double ratioValorPeso() {
		return ((double)valor)/peso;
	}
	
	@Override
	public String toString() {
		return String.format("(%d,%d,%d,%.2f)",
				valor,peso,numMaxDeUnidades,ratioValorPeso());
	}

	
	@Override
	public int compareTo(ObjetoMochila o) {
		return this.ratioValorPeso().compareTo(o.ratioValorPeso());
	}
	
}
