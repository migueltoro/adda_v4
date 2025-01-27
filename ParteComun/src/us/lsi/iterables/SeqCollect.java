package us.lsi.iterables;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class SeqCollect {
	
	/**
	 * @param s Un iterador
	 * @param a Un acumulador secuencial
	 * @return El resultado de acumular secuencialmente por la izquierda implementado de forma iterativa
	 */
	public static <E,B,R> R seqCollectLeft(Iterator<E> s, SeqCollector<E,B,R> a) {
		B b = a.supplier().get();
		while(s.hasNext() && !a.isDone().test(b)){
		   E e = s.next();
		   b = a.accumulator().apply(b,e);
		}
		return a.finisher().apply(b);
	}
	
	/**
	 * @param s Un iterador
	 * @param a Un acumulador secuencial
	 * @return El resultado de acumular secuencialmente implementado de forma recursiva
	 */
	public static <E,B,R> R seqCollectLeftRecursivo(Iterator<E> s, SeqCollector<E,B,R> a) {
		B b = a.supplier().get();
		b = seqCollectLeftRecursivo(s,a,b);
		return a.finisher().apply(b);
	}
	
	private static <E,B,R> B seqCollectLeftRecursivo(Iterator<E> s, SeqCollector<E,B,R> a, B b) {
		if(s.hasNext() && !a.isDone().test(b)){
		   E e = s.next();
		   b = a.accumulator().apply(b,e);
		   b = seqCollectLeftRecursivo(s,a,b);
		}
		return b;
	}
	
	/**
	 * @param s Un iterador
	 * @param op Un operador binario
	 * @return El resultado de acumular por la izquierda secuencialmente s mediante op (reducir por la izquierda)
	 */
	public static <E> Optional<E> reduceLeft(Iterator<E> s, BinaryOperator<E> op) {
		if(!s.hasNext()) return Optional.empty();
		E b = s.next();
		while(s.hasNext()){
		   E e = s.next();
		   b = op.apply(b,e);
		}
		return Optional.of(b);
	}
	
	/**
	 * @param s Un iterador
	 * @param a Un acumulador secuencial
	 * @return El resultado de acumular secuencialmente por la derecha 
	 */
	public static <E,B,R> R seqCollectRight(Iterator<E> s, SeqCollector<E,B,R> a) {
		B b = seqCollectRightP(s,a);
		return a.finisher().apply(b);
	}

	private static <E,B,R> B seqCollectRightP(Iterator<E> s, SeqCollector<E,B,R> a) {
		B b = a.supplier().get(); 
		if(s.hasNext()){
			E e = s.next();
			b = seqCollectRightP(s,a);
			if(!a.isDone().test(b)){
	 	 	   b = a.accumulator().apply(b, e);
	 		}
		} 
		return b;
	}

	/**
	 * @param s Un iterador
	 * @param op Un operador binario
	 * @return El resultado de acumular por la derecha s mediante op (reducir por la derecha)
	 */
	public static <E> Optional<E> reduceRight(Iterator<E> s, BinaryOperator<E> op) {
		if(!s.hasNext()) return Optional.empty();
		E b = reduceRightP(s,op);
		return Optional.of(b);
	}

	private static <E,R> E reduceRightP(Iterator<E> s, BinaryOperator<E> op) {
		E b = null;
		if (s.hasNext()) {
			E e = s.next();
			b = reduceRightP(s,op);
			if (b==null) b = e;
			else {
				b = op.apply(b, e);
			}
		} 
		return b;
	}

}
