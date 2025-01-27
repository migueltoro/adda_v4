package us.lsi.math;

import java.math.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import us.lsi.common.IntPair;
import us.lsi.common.List2;
import us.lsi.common.Preconditions;



public class Math2 {

	
	/**
	 * @param d Un Double
	 * @return Representaci�n simplificada de un Double 
	 */
	public static String simplify(Double d){
		String s = "";
		if(d==1.){
			s+="+";
		}else if(d==-1.){
			s+="-";
		}else if(d>0.){
			s+="+"+d.toString();
		}else if(d!=0.){
			s+=d.toString();
		}
		return s;
	}
	
	
	/**
	 * @pre base &gt; 0
	 * @param base Base de la potencia
	 * @param n Exponente de la potencia
	 * @return base &#94; n en una versi�n iterativa de complejidad log(n)
	 */
	public static Long pow(Integer base, Integer n){
		Long r; 
		Long u;
		r = (long) base;
		u = 1L;
		while( n > 0){
	       if(n%2==1){
			     u = u * r;
		   }
		   r = r * r;
		   n = n/2;
		}
		return u;

	}
	
	/**
	 * @pre base &gt; 0
	 * @param base Base de la potencia
	 * @param n Exponente de la potencia
	 * @return base &#94; n en una versi�n iterativa de complejidad log(n)
	 */
	public static Double pow(Double base, Integer n){
		Double r, u;
		r = base;
		u = 1.;
		while( n > 0){
	       if(n%2==1){
			     u = u * r;
		   }
		   r = r * r;
		   n = n/2;
		}
		return u;

	}
	
	/**
	 * @pre base &gt; 0
	 * @param base Base de la potencia
	 * @param n Exponente de la potencia
	 * @return base &#94; n en una versi�n iterativa de complejidad log(n)
	 */
	public static Double powr(Double base, Integer n){
		Double r;		
		if(n==0){
			r = 1.;
		}else if(n==1){
			r = base;
		}else {
			r = powr(base,n/2);
			r = r*r;
			if(n%2!=0){
				r = r*base;
			}
		}
		return r;
	}
	
	
	/**
	 * @param a Un entero
	 * @param b Un segundo entero
	 * @return Calcula el m�ximo com�n divisor de los valores ablsolutos de los par�metros
	 */
	public static int mcd(int a, int b){		
	       int u = Math.abs(a);
	       int v = Math.abs(b);
	       int r;
	       while (v != 0) {
	            r = u % v;
	            u = v;
	            v = r;
	       }
		    return u;
	}
	
    public static Random rnd   = new Random(System.nanoTime());
	  
    public static void initRandom(){
    	rnd   = new Random(System.nanoTime());
    }
	
    /**
     * @return Un objeto de tipo Random
     * 
     * @see java.util.Random
     */
    public static Random getRandom() {
		return rnd;
	}

	/**
	 * @pre b &gt; a
	 * @param a L�mite inferior
	 * @param b L�mte Superior
	 * @return Un entero aleatorio r tal que a &le; = r &lt; b
	 */
	public static Integer getEnteroAleatorio(Integer a, Integer b){   	
	    	Integer valor;
			Preconditions.checkArgument(b>a,a+","+b);
	    	if(b-a == 1){
	    		valor = a;
	    	}else{
	    		valor = a+rnd.nextInt(b-a);
	    	}
	    	return valor;
	}

	/**
	 * @return Un entero aleatorio
	 * @see java.util.Random#nextLong()
	 */
	public static Long getLongAleatorio(){   	
    	return rnd.nextLong();
	}
	
	/**
	 * @pre b &gt; a
	 * @param a L�mite inferior
	 * @param b L�mite Superior
	 * @return Un par aleatorio cuyos elementos son distintos y est�n en el intervalo  a &lt; = r &lt; b
	 */
	public static IntPair getParAleatorioYDistinto(Integer a, Integer b){   	
    	Preconditions.checkArgument(b-a>=2,a+","+b);
    	Integer c1 = getEnteroAleatorio(a,b-1);
    	Integer c2 = getEnteroAleatorio(c1+1,b);
		return IntPair.of(c1, c2);
	}
	
	/**
	 * @pre b &gt; a
	 * @param a L�mite inferior
	 * @param b L�mte Superior
	 * @return Un double aleatorio que  est� en el intervalo  a &lt; = r &lt; b
	 */
	public static Double getDoubleAleatorio(Double a, Double b){   	
	    	Preconditions.checkArgument(b>a,a+","+b);
	    	Double r = a+(b-a)*rnd.nextDouble();
	    	return r;
	}
	
	
	/**
	 * @param increment El incremento &delta;
	 * @param t Temperatura
	 * @return Si &delta; &lt; 0 devuelve 1, si t = 0 devuelve 0, en otro caso devuelve e &#94; (- &delta;/t) 
	 */
	public static double boltzmann(double increment, double t){
		double r;
		if(increment <= 0.){
			r = 1.;
		} else {
			r = Math.exp(1-increment/t);
		}
		return r;
	}
	
	/**
	 * @param increment El incremento &delta;
	 * @param t Temperatura 
	 * @return Verdadero si r &lt; e &#94; (- &delta;/t). Donde r es un real aleatorio 0 &lt; = r &lt; = 1
	 */
	public static boolean aceptaBoltzmann(double increment, double t) {
			double rd = Math2.getDoubleAleatorio(0., 1.);
			double rd2 =  Math2.boltzmann(increment,t);
			return rd < rd2;
	}
	
	
	/**
	 * @param probabilities Es una distribuci�n de probabilidades para una variables aleatoria 
	 * con valores 0 hasta probabilidades.size() no incluido.
	 * @return Un entero entre 0 y probabilidades.size(), no incluido, con las probababilidades proporcionadas
	 */
	public static Integer escogeEntre(List<Double> probabilities){
		Preconditions.checkArgument(!probabilities.isEmpty());
		Double ppa = 0.;
		Integer r = 0;
		double na = Math2.getDoubleAleatorio(0., 1.);
		for(Double p:probabilities){
			ppa = ppa+p;
			if(ppa >= na){
				break;
			}
			r++;
		}
		return r;
	}
	
	/**
	 * @param rest Resto de la probabilidades
	 * @return Si se forma una lista ls con todos los par�metros el m�todo devuelve un entero 
	 * entre 0 y ls.size(), no incluido, con las probababilidades proporcionadas en la lista
	 */
	public static Integer escogeEntre(Double...rest){
		return escogeEntre(List2.of(rest));
	}
	 
	/**
	 * @param a Un entero
	 * @return Si es par
	 */
	public static boolean esPar(Integer a){
		return a%2 == 0;
	}
		
	/**
	 * @param a Un entero 
	 * @return Si es impar
	 */
	public static boolean esImpar(Integer a){
		return !esPar(a);
	}
	
	/**
	 * @param a Un entero
	 * @param b Un segundo entero
	 * @return Si a es divisible por b
	 */
	public static boolean esDivisible(Integer a, Integer b){
		return (a%b) == 0;
	}
	
	public static boolean esDivisible(Long a, Long b){
		return (a%b) == 0;
	}
	/**
	 * @param a Un entero
	 * @return Si a es primo
	 */
	public static boolean esPrimo(Integer a){
		Preconditions.checkArgument(a>=2,String.format("El argumento debe ser mayor o igual que 2 y es %d",a));
		Integer sqrt = (int)Math.sqrt((double)a);
		return IntStream.rangeClosed(2, sqrt).noneMatch(x->Math2.esDivisible(a, x));
	}
	
	public static boolean esPrimo(Long a){
		Preconditions.checkArgument(a>=2L,String.format("El argumento debe ser mayor o igual que 2 y es %d",a));
		Long sqrt = (long)Math.sqrt((double)a);
		return LongStream.rangeClosed(2, sqrt).noneMatch(x->Math2.esDivisible(a, x));
	}
	
	public static boolean esPrimo(BigInteger a){
		return a.isProbablePrime(100);
	}
	
	/**
	 * @param a Un entero
	 * @return Siguiente primo
	 */
	
	public static Integer siguientePrimo(Integer a){
		if(a<2) return 2;
		a = (a+1)%2==0?a+2:a+1;
		return Stream.iterate(a, x->x+2).filter(x->Math2.esPrimo(x)).findFirst().get();
	}
	
	public static Long siguientePrimo(Long a){
		if(a<2) return 2L;
		a = (a+1)%2==0?a+2:a+1;
		return Stream.iterate(a, x->x+2).filter(x->Math2.esPrimo(x)).findFirst().get();
	}
	
	public static BigInteger siguientePrimo(BigInteger a){
		BigInteger r = BigInteger.TWO;
		if(a.compareTo(r) >=0) r = a.nextProbablePrime();
		return r;
	}
	
	/**
	 * @param a Un entero
	 * @return El signo de a: +1,0,-1
	 */
	public static int sgn(Integer a) {
		int r = 0;
		if (a != 0)
			r = a >= 0 ? 1 : -1;
		return r;
	}

	
	/**
	 * @param a Un double
	 * @return El signo de a: +1,0,-1
	 */
	public static int sgn(Double a) {
		int r = 0;
		if (r != 0)
			r = a >= 0 ? 1 : -1;
		return r;
	}
	
	/**
	 * @pre Todos los par�metros son positivos. El valor de e debe ser menor que maxEscala
	 * @param e Un entero
	 * @param maxEscala Un entero 
	 * @param maxRange Un entero
	 * @return Devuelve un valor en el rango 0..maxRange-1 con la expresi�n e*maxRange/maxEscala
	 */
	public static Integer escala(Integer e, Integer maxEscala, Integer maxRange){
	     int a = e*maxRange/maxEscala;
	     return a;
	}
	
	
	/**
	 * @pre Todos los par�metros son positivos. El valor de e debe ser menor que maxEscala
	 * @param e Un entero
	 * @param maxEscala Un entero 
	 * @param maxRange Un entero
	 * @return Devuelve un valor en el rango 0..maxRange con la expresi�n e*(maxRange+1)/maxEscala
	 */
	public static Integer escalaIncluded(Integer e, Integer maxEscala, Integer maxRange){
	     int a = e*(maxRange+1)/maxEscala;
	     return a;
	}

	/**
	 * @param ls Una lista de bits
	 * @return El n�mero entero conrrespondiente
	 */
	public static  Integer decode(List<Integer> ls){
		Integer r = 0;
		for(Integer e:ls){
			r = r*2+e;
		}
		return r;
	}
	
	/**
	 * @pre j &gt; i, i &ge;0
	 * @param i Un entero
	 * @param j Un entero
	 * @param ls Una lista de bits
	 * @return El n�mero entero conrrespondiente a la sublista definida por el intevalo [i,j).
	 */
	public static  Integer decode(List<Integer> ls, Integer i, Integer j){
		Integer r = 0;
		for(Integer e:ls.subList(i,j)){
			r = r*2+e;
		}
		return r;
	}
	
	/**
	 * @pre n*nBits = ls.size()
	 * @param n Numero de enteros a decodificar
	 * @param nBits Numero de bits por entero.
	 * @param ls Una lista de bits
	 * @return Los numero enteros correspondientes a las n sublistas definidas 
	 */
	public static  List<Integer> decodes(List<Integer> ls, Integer n, Integer nBits){
		Preconditions.checkArgument(n*nBits == ls.size());
		List<Integer> lsr = new ArrayList<>();
		for (int i = 0; i < ls.size(); i = i + nBits) {
			Integer r = 0;
			for (Integer e : ls.subList(i,i+nBits)) {
				r = r * 2 + e;
			}
			lsr.add(r);
		}
		return lsr;
	}
	
	private static Integer[] pow2 = {1,2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384,32768,65536,131072,262144,524288};
	
	/**
	 * @pre n*nBits = ls.size(), n = maxRanges.size()
	 * @post return.size() = n, 0 &le; return[i] &lt; maxRanges(i)
	 * @param n Numero de enteros a decodificar
	 * @param nBits Numero de bits por entero.
	 * @param ls Una lista de bits
	 * @param maxRanges Una lista con los rangos m�ximos de los enteros resultantes
	 * @return Los numero enteros correspondientes a las n sublistas definidas en una escala con maximo maxRanges(i)-1
	 */
	public static  List<Integer> decodesInScala(List<Integer> ls, Integer n, Integer nBits, List<Integer> maxRanges){
		Preconditions.checkArgument(n*nBits == ls.size());
		Preconditions.checkArgument(maxRanges.size()==n);
		int maxEscala = nBits<20?pow2[nBits]:Math2.pow(2, nBits).intValue();
		List<Integer> lsr = decodes(ls,n,nBits);
		List<Integer> r = new ArrayList<>();
		for(int i =0; i<n;i++){
			r.add(escala(lsr.get(i),maxEscala,maxRanges.get(i)));
		}
		return r;
	}
	
	/**
	 * @param e Un entero positivo
	 * @return El n�mero de bits necesario para poder codificarlo en binario
	 */
	public static Integer numeroDeBits(Integer e){
		      int bits_necesarios = 0;
		      while(e >=2) {
		            bits_necesarios++;
		            e = e/2; // Desplazo bits (divisi�n por 2)
		      }
		      return bits_necesarios+1;
	}
	
	/**
	 * @param e Un entero positivo
	 * @return Una lista con la codificaci�n en binario
	 */
	public static List<Integer> code(Integer e){
		      List<Integer> r = new ArrayList<>();
		      while(e >=2) {
		           if(e%2==0)
		        	   r.add(0,0);
		           else
		        	   r.add(0,1);
		           e = e/2;
		      }
		      r.add(0,e);		      
		      return r;
	}
	
	public static void test1() {
		BigInteger mil = new BigInteger("1000");
		Stream<BigInteger> s = Stream.iterate(
				BigInteger.TWO,
				e -> e.compareTo(mil) < 0, 
				e->siguientePrimo(e));
		s.forEach(e->System.out.println(e.toString()));
		System.out.println(esPrimo(3));
		System.out.println(getParAleatorioYDistinto(0,11));
	}
	
	public static void test2() {
		System.out.println(Math2.pow(2,15));
		System.out.println(Math2.powr(2.,15));
	}
	
	public static void main(String[] args) {
		test2();
	}
}
