package us.lsi.math;

import java.util.Arrays;

public class Polinomio {
	
	private Double[] coeficientes;
	private static Double[] zero = {0.};
	private static Double[] one = {0.,1.};
	private static Polinomio pzero = Polinomio.create(zero);
	private static Polinomio pone = Polinomio.create(one);
	
	Polinomio() {
		super();
		this.coeficientes = new Double[1];
		coeficientes[0] = 0.;
	}
	
	Polinomio(Double[] coeficientes) {
		super();
		this.coeficientes = coeficientes;
		this.coeficientes = normal(this.coeficientes);
	}

	Polinomio(Double coef, int grado) {
		super();
		this.coeficientes = Polinomio.reset(grado+1);
		this.coeficientes[grado] = coef;
	}

	Polinomio(Polinomio p) {
		super();
		this.coeficientes = Arrays.copyOf(p.getCoefficients(),p.getDegree()+1);
	}

	public static Polinomio create() {
		return new Polinomio();
	}

	public static Polinomio create(Double[] coeficientes) {
		return new Polinomio(coeficientes);
	}

	public static Polinomio create(Double coef, int grado) {
		return new Polinomio(coef, grado);
	}

	public static Polinomio create(Polinomio p) {
		return new Polinomio(p);
	}
	
	public static Polinomio getZero() {
		return pzero;
	}

	public static Polinomio getOne() {
		return pone;
	}

	public Double[] getCoefficients() {
		return Arrays.copyOf(coeficientes,coeficientes.length);
	}

	
	public Double getCoefficient(int index) {
		Double r = 0.;
		if(index <= getDegree()){
			r = coeficientes[index];
		}
		return r;
	}
	
	public Polinomio setCoefficient(int index, Double coeficiente) {		
		int newLength = Math.max(index+1, coeficientes.length);
		Double[] coef  = Polinomio.reset(newLength,coeficientes);
		coef[index] = coeficiente;
		return create(coef);
	}
	
	public int getDegree(){
		return coeficientes.length-1;
	}
	
	public Double getValue(Double v){
		Double r = 0.;
		for(int i = getDegree() ; i >=0 ; i--){
			r = r*v+this.getCoefficient(i);
		}
		return r;
	}
	
	public Polinomio add(Polinomio p){
		int newGrado = Math.max(this.getDegree(), p.getDegree());
		Double[] rCoef = Polinomio.reset(newGrado+1);
		for(int i = 0; i <= p.getDegree(); i ++){
			rCoef[i] = this.getCoefficient(i) + p.getCoefficient(i);
		}
		return Polinomio.create(rCoef);
	}
	
	public Polinomio negate(){
		Double[] thisCoef = Polinomio.reset(coeficientes.length,this.getCoefficients());
		for(int i = 0; i <= getDegree(); i ++){
			thisCoef[i] = -thisCoef[i];
		}
		return Polinomio.create(thisCoef);
	}
	
	public Polinomio multiply(Double e){
		Double[] thisCoef = Polinomio.reset(coeficientes.length,this.getCoefficients());
		for(int i = 0; i <= getDegree(); i ++){
			thisCoef[i] = e*thisCoef[i];
		}
		return Polinomio.create(thisCoef);
	}
	
	
	public Polinomio multiply(Polinomio p){
		int newGrado = this.getDegree()+p.getDegree();
		Double[] newCoef = Polinomio.reset(newGrado+1);
		for(int i = 0; i <= this.getDegree(); i++){
			for(int j = 0; j <= p.getDegree(); j++){
				newCoef[i+j] = newCoef[i+j] + this.getCoefficient(i)*p.getCoefficient(j);
			}
		}
		return Polinomio.create(newCoef);
	}
	
	public Polinomio subtract(Polinomio p){
		return p.negate().add(this);
	}
	
	public Polinomio derivative(){
		Polinomio r = Polinomio.getZero();
		if(getDegree()>0){
			int newGrado = this.getDegree()-1;
			Double[] newCoef = Polinomio.reset(newGrado+1);
			for(int i = 0; i <= newGrado; i++){
				newCoef[i] = this.getCoefficient(i+1)*(i+1);
			}
			r = Polinomio.create(newCoef);
		}		
		return r;
	}
	
	public static Double[] reset(int len, Double[] coef){
		Double[] r;
		r = Arrays.copyOf(coef, len);
		if(len!=coef.length){
		    for(int i = 0; i < r.length; i++){
		    	if(r[i]==null){
		    		r[i] = 0.;
		    	}
		    }
		}
		return r;
	}
	
	public static Double[] reset(int len){
		Double[] r = new Double[len];
		for(int i = 0; i < r.length; i++){
		        r[i] = 0.;
		}
		return r;
	}
	
	private Double[] normal(Double[] coef){
		int r = 0;
		Double d;
		for(int i = coef.length-1; i > 0;  i--){
			d = coef[i];
			if(d!=0.){
				r = i;
				break;
			}
		}
		return Polinomio.reset(r+1, coef);
	}

	
	
	
	@Override
	public String toString() {
		String s = "";
		for(int i = 0 ; i < coeficientes.length; i++){
			String rc;
			Double d = coeficientes[i];
			if(d > 0.){
				rc = "+"+coeficientes[i];
			}else if(d == 0.){
				rc = "";
			}else{
				rc = coeficientes[i].toString();
			}
			rc = rc +" ";
			if(d!=0.){
				if(i==0){
					s = s+rc;
				}else if(i==1){
					s =s+rc+"X ";
				}else{
					s=s+rc+"X^"+i+" ";
				}
			}	
		}
		if(s.equals("")){
			s = "0";
		}
		return s;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(coeficientes);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Polinomio))
			return false;
		Polinomio other = (Polinomio) obj;
		if (!Arrays.equals(coeficientes, other.coeficientes))
			return false;
		return true;
	}

	
}
