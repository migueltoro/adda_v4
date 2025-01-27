package us.lsi.java8ejemplos;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;



import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.Multiset;
import us.lsi.common.Preconditions;
import us.lsi.common.SetMultimap;
import us.lsi.common.String2;
import us.lsi.geometria.Cuadrante;
import us.lsi.geometria.Punto2D;
import us.lsi.math.Math2;

/**
*
*
* @author Miguel Toro
*/

public class Ejemplos {

	/**
	 * @param <T> El tipo de los elementos
	 * @param st Un Stream
	 * @return Crear una lista a partir de un Stream de valores
	 */
	public static <T> List<T> ejemploA(Stream<T> st){
		return st.collect(Collectors.<T>toList());
	}
	
	/**
	 * @param ls una lista
	 * @return Dada una lista de objetos de tipo Punto, devolver otra lista con la coordenada X de esos puntos
	 */
	public static List<Double> ejemploB(List<Punto2D> ls){
		return ls.stream().map(x->x.x()).collect(Collectors.toList());
	}
	
	/**
	 * @param ls Una lista
	 * @return Si son impares todos los elementos de la lista.
	 */
	public static boolean ejemploC(List<Integer> ls){
		return ls.stream().allMatch(x->x%2==1);
	}
	
	/**
	 * @param ls Una lista
	 * @return Si existe alguno impar y primo
	 */
	public static boolean ejemploD(List<Integer> ls){
		return ls.stream().anyMatch(x->x%2==1 && Math2.esPrimo(x));
	}
	
	/**
	 * @param ls Una lista
	 * @return La suma de todos los elementos de la lista.
	 */
	public static Double ejemploE(List<Double> ls){
		return ls.stream().mapToDouble(x->x).sum();
	}
	
	/**
	 * @param ls Una lista
	 * @return La suma de los cuadrados de todos los elementos de la lista
	 */
	public static Integer ejemploF(List<Integer> ls){
		return ls.stream().mapToInt(x->x*x).sum();
	}
	
	/**
	 * @param ls Una lista
	 * @param umbral Un elemento de referencia
	 * @return El primer real que encuentre que sea mayor que un umbral dado como par�metro
	 */
	public static Double ejemploG(List<Double> ls, Double umbral){
		return ls.stream()
				 .filter(x-> x > umbral)
				 .findFirst()
				 .get();
	}
	
	/**
	 * @param ls Una lista
	 * @return Una lista de Punto con los puntos sim�tricos sobre el eje Y
	 */
	public static List<Punto2D> ejemploH(List<Punto2D> ls){
		return ls.stream()
				 .map(x->Punto2D.of(-x.x(),x.y()))
				 .collect(Collectors.toList());
	}
	
	/**
	 * @param ls Una lista
	 * @return El punto con menor coordenada X.
	 */
	public static Punto2D ejemploI(List<Punto2D> ls){
		return ls.stream()
				 .min(Comparator.comparing(Punto2D::x)).get();
	}
	
	/**
	 * @param ls Una lista
	 * @return N�mero de puntos de la lista en el primer cuadrante
	 */
	public static Long ejemploJ(List<Punto2D> ls){
		return ls.stream()
				 .filter(x->x.x()>=0. && x.y()>=0.)
				 .count();	
	}
	
	/**
	 * @param ls Una array
	 * @return Una lista con todos los elementos del array
	 */
	public static List<Punto2D> ejemploK(Punto2D[] ls){
		return Arrays.stream(ls).collect(Collectors.toList());
	}
	
	/**
	 * @post Guarda en un fichero de texto los n�meros primos hasta un n�mero n dado.
	 * @param fileOut  Un fichero 
	 * @param limit Un n�mero de referencia
	 */
	public static void ejemploL(String fileOut, Integer limit){
		String r = Stream.iterate(1, x->x<=limit, x->Math2.siguientePrimo(x))
			  .map(x->x.toString())
			  .collect(Collectors.joining("\n"));
		String2.toFile(r, fileOut);
	}
	
	/**
	 * @param st Un Stream de listas
	 * @return Stream de con los elementos de todas las listas
	 */
	public static Stream<Integer> ejemploM(Stream<List<Integer>> st){
		return st.flatMap(x->x.stream());
	}
	
	/**
	 * 
	 * @return Un String con el cuadrado de los n�meros primos hasta un n�mero n dado.
	 * @param limit Un n�mero de referencia
	 */
	public String ejemploN(Integer limit){
		return Stream.iterate(1, x-> x<=limit, x->Math2.siguientePrimo(x))
			  .map(x->x*x)
			  .map(x->x.toString())
			  .collect(Collectors.joining("\n"));
	}
	
	/**
	 * @param fileIn Un fichero de texto que contiene en cada l�nea un n�mero entero
	 * @return Un IntStream
	 */
	public static IntStream ejemploO(String fileIn){
		return Files2.streamFromFile(fileIn)
				     .mapToInt(x-> Integer.parseInt(x));
	}
	
	/**
	 * @param limit Una referencia
	 * @return Un Stream de Punto con los puntos del plano cuya coordenada sea (X,X), 
	 * siendo X un n�mero primo menor que un n�mero dado
	 */
	public static Stream<Punto2D> ejemploP(Integer limit){
		return Stream.iterate(1L, x-> x<=limit, x->Math2.siguientePrimo(x))
					 .<Punto2D>map(x->Punto2D.of((double)x, (double)x));
	}
	
	/**
	 * @post Genera fileOut a partir de fileIn
	 * @param fileIn Un fichero de texto con una fecha escrita en cada l�nea
	 * @param fileOut Un fichero con las fechas ordenadas y que est�n entre dos fechas dadas
	 * @param c1 L�mite inferior de las fechas
	 * @param c2 L�mite superior de las fecha
	 */
	public static void ejemploQ(String fileIn, String fileOut, LocalDate c1, LocalDate c2){
		Preconditions.checkArgument(c2.compareTo(c1) > 0);
		Stream<String> r = Files2.streamFromFile(fileIn)
				.map(x-> LocalDate.parse(x))
				.filter(x->c1.compareTo(x)< 0 && c2.compareTo(x)>0)
				.sorted()
				.map(x->x.toString());
		Files2.toFile(r,fileOut);
	}
	
	/**
	 * @param fileIn Un fichero de texto que contiene en cada l�nea una lista de n�meros enteros separados por comas
	 * @return Un IntStream
	 */
	public static IntStream ejemploR(String fileIn){
		return Files2.streamFromFile(fileIn)
				     .map(x->x.split(","))
				     .flatMap(x->Arrays.stream(x))
				     .mapToInt(x-> Integer.parseInt(x));
	}
	
	/**
	 * @post Agrupa los puntos por cuadrantes
	 * @param st Un Stream
	 * @return Multimap&lt;Cuadrante,Punto&gt; en el que se asocia a cada cuadrante, los puntos del Stream que est�n en ese cuadrante
	 */
	public static SetMultimap<Cuadrante,Punto2D> ejemploS1(Stream<Punto2D> st){
		SetMultimap<Cuadrante,Punto2D> m = SetMultimap.empty();
		st.forEach(x->m.put(x.cuadrante(),x));
		return m;
	}
	
	/**
	 * @post Agrupa los puntos por cuadrantes
	 * @param st Un Stream
	 * @return Map&lt;Cuadrante,List&lt;Punto&gt;&gt; en el que se asocia a cada cuadrante, los puntos del Stream que est�n en ese cuadrante
	 */
	public static Map<Cuadrante,List<Punto2D>> ejemploT1(Stream<Punto2D> st){
		return st.collect(Collectors.groupingBy(Punto2D::cuadrante));
	}
	
	/**
	 * @post Suma las coordenadas X de los puntos en cada cuadrante
	 * @param st Un Stream
	 * @return Map@lt;Cuadrante,Double&gt; en el que se asocia a cada cuadrante, la suma de las coordenadas X de los puntos de ese cuadrante
	 */
	public static Map<Cuadrante,Double> ejemploU(Stream<Punto2D> st){
		return st.collect(Collectors.groupingBy(Punto2D::cuadrante, 
							Collectors.reducing(0.,x->x.x(),(x,y)->x+y)));
	}
		
	/**
	 * @post Cuenta cu�ntos puntos hay de cada cuadrante en el Stream
	 * @param st Un Stream
	 * @return  Un Multiset&lt;Cuadrante&gt;
	 */
	public static Multiset<Cuadrante> ejemploV(Stream<Punto2D> st){
		Multiset<Cuadrante> m = Multiset.empty();
		st.forEach(x->m.add(x.cuadrante()));	
		return m;
	}
	
	/**
	 * @post Cuenta cu�ntos puntos hay de cada cuadrante en el Stream
	 * @param st Un Stream 
	 * @return Un Map&lt;Cuadrante,Long&gt;
	 */
	public static Map<Cuadrante,Long> ejemploW(Stream<Punto2D> st){
		return st.collect(Collectors.groupingBy(Punto2D::cuadrante, Collectors.counting()));
	}

	
	/**
	 * @param s Un String
	 * @return N�mero de caracteres en min�scula que tiene la cadena
	 */
	public static Long ejemploX(String s){
		return s.chars()
				.filter(x->Character.isLowerCase(x))
				.count();
	}
	
	/**
	 * @param ls Una lista
	 * @return La cadena que tiene un mayor n�mero de caracteres en min�scula
	 */
	public static String ejemploY(List<String> ls){
		return ls.stream()
				 .max(Comparator.comparing(Ejemplos::ejemploX))
				 .get();
	}
	
	/**
	 * @param ls Una lista
	 * @return El m�ximo, el m�nimo, la media y la suma.
	 */
	public static IntSummaryStatistics ejemploZ(List<Integer> ls){
		return ls.stream()
				 .collect(Collectors.summarizingInt(x->x));
	}
	
	/**
	 * @param n Un entero
	 * @return Si es primo
	 */
	public static boolean esPrimo1(Long n){
		Long sqrt = (long)Math.sqrt((double)n);
		return !LongStream.rangeClosed(2, sqrt)
				          .anyMatch(x->Math2.esDivisible(n, x));
	}
	
	/**
	 * @param n Un entero
	 * @return Si es primo
	 */
	public static boolean esPrimo2(Long n){
		Long sqrt = (long)Math.sqrt((double)n);
		Long e = 2L;
		Boolean a = false;
		while(e <= sqrt){	   
	      	a = Math2.esDivisible(n, e);
	      	if(a) break; 
	   		e = e + 1;		   
        }
		return !a;
	}
	/**
	 * @param n Un entero
	 * @return Primer primo mayor que a
	 */
	public static Long siguientePrimo1(Long n){
		Long e0 = n%2==0?n+1:n+2;
		return Stream.iterate(e0, e->e+2)
				     .filter(e->Math2.esPrimo(e))
				     .findFirst()
				     .get();
	}
	/**
	 * @param n Un entero
	 * @return Primer primo mayor que a
	 */
	public static Long siguientePrimo2(Long n){
		Long e = n%2==0?n+1:n+2;
		Long a = null;
		while(true){	   
	      	if(esPrimo2(e))  {
	      		a = e;
	      		break;
	      	}
	   		e = e +2;		   
        }
		return a;
	}
	/**
	 * @param limit Un entero
	 * @return Los  primos menores o iguales a limit
	 */
	public static List<Long> primosMenoresOIgualesA1(Long limit){
		return Stream.iterate(1L, x->x<=limit, x->siguientePrimo1(x))
				     .collect(Collectors.toList());
	}
	/**
	 * @param limit Un entero
	 * @return Los  primos menores o iguales a limit
	 */
	public static List<Long> primosMenoresOIgualesA2(Long limit){
		Long e = 1L;
		List<Long> a = List2.empty();
		while(e<=limit){	   
	      	a.add(e);
	   		e = siguientePrimo2(e);		   
        }
		return a;
	}
	/**
	 * @param limit Un entero
	 * @return Suma de los  primos menores o iguales a limit
	 */
	public static Long sumaPrimosMenoresOIgualesA1(Long limit){
		return Stream.iterate(1L, x->x<=limit, x->siguientePrimo1(x))
				     .reduce(1L,(x,y)->x+y);
	}
	/**
	 * @param limit Un entero
	 * @return Suma de los  primos menores o iguales a limit
	 */
	public static Long sumaPrimosMenoresOIgualesA2(Long limit){
		Long e = 1L;
		Long a = 0L;
		while(e<=limit){	   
	      	a = a+e;
	   		e = siguientePrimo2(e);		   
        }
		return a;
	}
	
	public static void main(String[] args) {
//		OtrosEjemplos.ejemplos8();	
//		LocalDate f1 = LocalDate.of(1990,1,1);
//		LocalDate f2 = LocalDate.of(2010,1,1);
//		Ejemplos.ejemploQ("ficheros/fechas.txt", "ficheros/fechasOut.txt", f1,f2);	
		System.out.println(Ejemplos.esPrimo1(1031L));
		System.out.println(Ejemplos.esPrimo2(1031L));
		System.out.println(Ejemplos.siguientePrimo1(1023L));
		System.out.println(Ejemplos.siguientePrimo2(1023L));
		System.out.println(Ejemplos.primosMenoresOIgualesA1(100L));
		System.out.println(Ejemplos.primosMenoresOIgualesA2(100L));
		System.out.println(Ejemplos.sumaPrimosMenoresOIgualesA1(100L));
		System.out.println(Ejemplos.sumaPrimosMenoresOIgualesA1(100L));
		Ejemplos.ejemploL("ficheros/primos", 100);
	}
		
}
