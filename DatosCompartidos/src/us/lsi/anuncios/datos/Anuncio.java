package us.lsi.anuncios.datos;

import us.lsi.common.Preconditions;

public class Anuncio implements Comparable<Anuncio> {

	public static Anuncio create(Integer codigo, Integer duracion,Integer precioBase) {
		return new Anuncio(codigo, duracion, precioBase);
	}
	public static Anuncio create(String[] fm) {
		return new Anuncio(fm);
	}
	
	private Integer codigo;
	private Integer duracion;
	private Integer precioBase;
	
	private Anuncio(String[] fm) {
		super();
		Preconditions.checkArgument(fm.length==3);
		this.codigo = Integer.parseInt(fm[0]);
		this.duracion = Integer.parseInt(fm[1]);
		this.precioBase = Integer.parseInt(fm[2]);
	}
	
	private Anuncio(Integer codigo, Integer duracion, Integer precioBase) {
		super();
		this.codigo = codigo;
		this.duracion = duracion;
		this.precioBase = precioBase;           
	}
	
	public Integer getDuracion(){
		return duracion;
	}
	
	public Integer getPrecioBase() {
		return precioBase;
	}
	
	public Integer getCodigo (){
		return codigo;
	}
	
	public Double getPrecioUnitario(){
		return (precioBase*1.)/duracion;
	}
	
	public Double getPrecio(Integer pos){
		return precioBase*1000./pos + 50000;
	}
	
	@Override
	public int compareTo(Anuncio a) {
		int r = getPrecioUnitario().compareTo(a.getPrecioUnitario());
		if(r==0){
			r = codigo.compareTo(codigo);
		}
		return r;
	}
	
	

	@Override
	public String toString() {
		return String.format("(%s,%d,%d)",codigo,duracion,precioBase);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Anuncio))
			return false;
		Anuncio other = (Anuncio) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
}

