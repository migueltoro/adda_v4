package us.lsi.java8ejemplos;


public class Test {


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
