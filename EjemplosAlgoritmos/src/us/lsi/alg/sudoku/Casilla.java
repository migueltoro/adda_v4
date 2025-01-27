package us.lsi.alg.sudoku;

import us.lsi.common.Preconditions;

/**
 * @author Miguel Toro
 *
 *<p> La casilla de un Sudoku
 */
public class Casilla implements Comparable<Casilla> {
	
	public static Casilla parse(String s) {
		String[] sp = s.split(",");
		Integer f = Integer.parseInt(sp[0].trim());
		Integer c = Integer.parseInt(sp[1].trim());
		Integer value =	Integer.parseInt(sp[2].trim());
		return Casilla.of(f,c,value,true,true);
	}
	
	public static Casilla of(Integer p) {
		return new Casilla(p/DatosSudoku.nf,p%DatosSudoku.nf, null, false,false);
	}
	
	public static Casilla of(Integer f, Integer c) {
		return new Casilla(f, c, null, false,false);
	}
	
	public static Casilla of(Integer f, Integer c, Integer value, Boolean definida, Boolean intialValue) {
		return new Casilla(f, c, value, definida,intialValue);
	}

	private Integer f;
	private Integer c;
	private Integer value;
	private Boolean definida;
	private Boolean withInitialValue;
	
	private Casilla(Integer f, Integer c, Integer value, Boolean definida, Boolean intialValue) {
		super();
		Preconditions.checkArgument(0 <= f && f < DatosSudoku.nf, String.format("El numero de fila es %d",f));
		Preconditions.checkArgument(0 <= c && c < DatosSudoku.nf, String.format("El numero de columna es %d",c));
		this.f = f;
		this.c = c;
		this.value = value;	
		this.definida = definida;
		this.withInitialValue = intialValue;
	}
		
	public Integer st() {
		Integer tm = DatosSudoku.tamSubCuadro;
		return this.c/tm+tm*(this.f/tm);
	}
	
	public Integer p() {
		return this.f*DatosSudoku.nf+this.c;	
	}

	/**
	 * @return La coordenada x de la casilla en 0..DatosSudoku.numeroDeFilas-1
	 */
	public Integer c() {
		return c;
	}
	/**
	 * @return La coordenada y de la casilla en 0..CuadroSudoku.numeroDeFilas-1
	 */
	public Integer f() {
		return f;
	}
	
	public boolean definida() {
		return this.definida;
	}
	
	/**
	 * @return Si tiene un valor inicial
	 */
	public Boolean isWithInitialValue() {
		return this.withInitialValue;
	}
	/**
	 * @return El valor contenido en la casilla
	 */
	public Integer value() {
		Preconditions.checkArgument(this.definida(),String.format("La casilla %s esta libre",this));
		return value;
	}
	
	public void setValue(Integer value) {
		this.value = value;
		this.definida = true;
	}
	
	public void setInitialValue(Integer value) {
		this.withInitialValue = true;
		this.value = value;
		this.definida = true;
	}
	
	public void setNoDefinida() {
		this.definida = false;
	}
	
	public Casilla copy() {
		return new Casilla(f,c,value,this.definida,this.withInitialValue);
	}
	
	public static String blank = "_";
	
	public String stringValue() {
		String r;
		if(!this.definida()) {
			r = String.format("%2s", blank);
		} else if(this.withInitialValue){
			r = String.format("X%1d", this.value());
		} else {
			r = String.format("%2d", this.value());
		}
		return r;	
	}
	
	public String getString() {
		return String.format("(%d,%d)",f,c);
	}
	
	@Override
	public int compareTo(Casilla cs) {
		return this.p().compareTo(cs.p());
	}
	
	@Override
	public String toString() {
		return String.format("(%d,%d,%d,%d,%s,%s)",f,c,st(),p(),this.definida(),this.withInitialValue);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((c == null) ? 0 : c.hashCode());
		result = prime * result + ((f == null) ? 0 : f.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Casilla))
			return false;
		Casilla other = (Casilla) obj;
		if (c == null) {
			if (other.c != null)
				return false;
		} else if (!c.equals(other.c))
			return false;
		if (f == null) {
			if (other.f != null)
				return false;
		} else if (!f.equals(other.f))
			return false;
		return true;
	}
	
}
