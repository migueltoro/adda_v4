package us.lsi.iterables;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class SeqCollector<E, B, R> {
	
	
	public static <E,B,R> SeqCollector<E,B,R> of(
			Supplier<B> supplier, 
			BiFunction<B, E, B> accumulator, 
			Function<B,R> finisher,
			Predicate<B> isDone){
		return new SeqCollector<>(supplier,accumulator,finisher,isDone);
	}
	
	public static <E,B> SeqCollector<E,B,B> of(
			Supplier<B> supplier, 
			BiFunction<B, E, B> accumulator){
		return new SeqCollector<>(supplier,accumulator,x->x,x->false);
	}
	
	public static <E> SeqCollector<E,E,E> of(
			BinaryOperator<E> accumulator){
		return new SeqCollector<>(()->null,accumulator,x->x,x->false);
	}
	
	public static <E,B,R> SeqCollector<E,B,R> ofBaseMutable(
			Supplier<B> supplier, 
			BiConsumer<B, E> accumulator, 
			Function<B, R> finisher,
			Predicate<B> isDone){
		return SeqCollector.of(supplier,(b,e)->{accumulator.accept(b, e);return b;},finisher,isDone);
	}
	
	private Supplier<B> supplier;;
	private BiFunction<B, E, B> accumulator;;
	private Function<B,R> finisher;
	private Predicate<B> isDone;
	
	public SeqCollector(Supplier<B> supplier, 
			BiFunction<B, E, B> accumulator, 
			Function<B, R> finisher,
			Predicate<B> isDone) {
		super();
		this.supplier = supplier;
		this.accumulator = accumulator;
		this.finisher = finisher;
		this.isDone = isDone;
	}
	
	public Supplier<B> supplier() {
		return supplier;
	}
	
	public BiFunction<B, E, B> accumulator() {
		return accumulator;
	}
	
	public Function<B, R> finisher() {
		return finisher;
	}
	
	public Predicate<B> isDone() {
		return isDone;
	}
		
}


