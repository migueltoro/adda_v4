package us.lsi.math;


public class Racional implements Comparable<Racional> {
	
	
	public static Racional getCero() {
		return new Racional(0,1);
	}

	public static Racional getUno() {
		return new Racional(1,1);
	}
	
	public static Racional create(Integer a, Integer b) {
		return new Racional(a, b);
	}

	public static Racional create(Integer a) {
		return new Racional(a,1);
	}

	public static Racional create(String s) {
		return new Racional(s);
	}
	
	public static Racional create(Racional r) {
		return new Racional(r.getNumerador(), r.getDenominador());
	}

	private Integer numerador;
	
	private Integer denominador;

	
	private Racional(Integer a, Integer b){
		if(a==null || b==null || b==0) throw new IllegalArgumentException("El denominador no puede ser cero");
		 numerador=a;
	     denominador=b;
	     normaliza();
	} 
	
	private Racional (String s) {
		String[] sp = s.split("[/]");
		Integer ne = sp.length;
		if(ne>2) throw new IllegalArgumentException("Cadena con formato no válido");
		numerador = Integer.parseInt(sp[0].trim());
		if(ne==1)
			denominador = 1;
		else
			denominador = Integer.parseInt(sp[1].trim());
		normaliza();
   }

   public Integer getNumerador() {
		return numerador;
   }

   public Integer getDenominador() {
		return denominador;
   }

   private void normaliza(){
	   Integer m = Math2.mcd(numerador,denominador);
	   if(denominador<=0) m =-m;
	   numerador = numerador/m;
	   denominador = denominador/m;
   }
   
   @Override
   public String toString(){
	   String s;
	   
	   if(numerador!=null && denominador!=null && denominador==1){
		   s = numerador.toString();
	   } else {
		   s = numerador+"/"+denominador;
	   }
	   return s;
   }
   
   
   @Override
   public int compareTo(Racional r) {
	   if(r==null || this.getDenominador() ==null || this.getNumerador() == null|| r.getDenominador() ==null || r.getNumerador() == null ){
           throw new NullPointerException();
        }
		Integer prod1;
		Integer prod2;
		prod1 = getNumerador()*r.getDenominador();
		prod2 = getDenominador()*r.getNumerador();
		return prod1.compareTo(prod2);	
   }


	public int compareByNumerador(Racional r) {
		if (r == null || r.getNumerador() == null) {
			throw new NullPointerException();
		}
		int s = getNumerador().compareTo(r.getNumerador());
		if (s == 0) {
			s = this.compareTo(r);
		}
		return s;
	}

	public int compareByDenominador(Racional r) {
		if (r == null || getDenominador() == null) {
			throw new NullPointerException();
		}
		int s = getDenominador().compareTo(r.getDenominador());
		if (s == 0) {
			s = this.compareTo(r);
		}
		return s;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((denominador == null) ? 0 : denominador.hashCode());
		result = prime * result
				+ ((numerador == null) ? 0 : numerador.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Racional))
			return false;
		Racional other = (Racional) obj;
		if (denominador == null) {
			if (other.denominador != null)
				return false;
		} else if (!denominador.equals(other.denominador))
			return false;
		if (numerador == null) {
			if (other.numerador != null)
				return false;
		} else if (!numerador.equals(other.numerador))
			return false;
		return true;
	}

	public Double getValorReal() {
		return ((double)getNumerador())/getDenominador();
	}
	

	public Racional suma(Racional r) {
		Integer a = numerador*r.getDenominador()+denominador*r.getNumerador();
	    Integer b = denominador*r.getDenominador();
	    return create(a,b);
	}
	
	public Racional resta(Racional r) {
		Integer a = numerador*r.getDenominador()-denominador*r.getNumerador();
	    Integer b = denominador*r.getDenominador();
	    return create(a,b);
	}
	
	public Racional multiplica(Racional r) {
		Integer a = numerador*r.getNumerador();
	    Integer b = denominador*r.getDenominador();
	    return create(a,b);
	}
	
	public Racional divide(Racional r) {
		if(r.getNumerador() == 0) throw new ArithmeticException
        ("No se puede dividir entre cero");
		Integer a = numerador*r.getDenominador();
		Integer b = denominador*r.getNumerador();
		return create(a,b);
	}
	
	public Racional invierte() {
		if(numerador == 0) throw new ArithmeticException
        ("No se puede invertir si el numerador es cero"); 
		return create(this.denominador,this.numerador);
	}
   
}
