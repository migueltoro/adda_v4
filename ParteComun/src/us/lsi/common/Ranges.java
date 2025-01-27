package us.lsi.common;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author migueltoro
 *
 * Rangos de valores de tipos numéricos
 */
public class Ranges {

	public record IntRange(Integer a,Integer b, Integer c) {
		
		public static IntRange of(Integer a, Integer b, Integer c) {
			return new IntRange(a,b,c);
		}
		public static IntRange of(Integer a, Integer b) {
			return new IntRange(a,b,1);
		}
		
		public Boolean isEmpty() {
			return a>=b;
		}
		public Boolean contains(Integer e) {
			return e>=a && e < b;
		}
		public Integer size() {
			return (b-a)/c;
		}
		public Integer get(Integer i) {
			Preconditions.checkElementIndex(0,this.size());
			return this.a()+i*this.c();
		}
		public Stream<Integer> stream(){
			return IntStream.of(a,b,c).boxed();
		}
		public View1<IntRange,Integer> view1(){
			Integer n = this.size();
			Preconditions.checkArgument(n>0,String.format("La lista debe ser de tamaño mayor que 0 y es %d  ",n));
			return View1.of(this.a,IntRange.of(a+c,b));
		}
		public View2E<IntRange,Integer> view2(){
			Integer n = this.size();
			Preconditions.checkArgument(n>1,String.format("La lista debe ser de tamaño mayor que 1 y es %d  ",n));
			Integer central = (b+a)/(2*c)*c;
			return View2E.of(central,IntRange.of(a,central,c),IntRange.of(central,b,c));
		}
		public View2E<IntRange,Integer> view2Overlapping(){
			Integer n = this.size();
			Preconditions.checkArgument(n>1,String.format("La lista debe ser de tamaño mayor que 1 y es %d  ",n));
			Integer central = (b+a)/(2*c)*c;
			return View2E.of(central,IntRange.of(a,central+c,c),IntRange.of(central,b,c));
		}	
	}
	
	
	public static record LongRange(Long a, Long b, Long c) {
		
		public static LongRange of(Long a, Long b, Long c) {
			return new LongRange(a,b,c);
		}
		public static LongRange of(Long a, Long b) {
			return new LongRange(a,b,1L);
		}

		public Boolean isEmpty() {
			return a>=b;
		}
		public Boolean contains(Long e) {
			return e>=a && e < b;
		}
		public Long size() {
			return (b-a)/c;
		}
		public Long get(Integer i) {
			Preconditions.checkElementIndex(0,this.size().intValue());
			return this.a()+i*this.c();
		}
		public Stream<Long> stream(){
			return LongStream.of(a,b,c).boxed();
		}
		public View1<LongRange,Long> view1(){
			Long n = this.size();
			Preconditions.checkArgument(n>0,String.format("La lista debe ser de tamaño mayor que 0 y es %d  ",n));
			return View1.of(this.a,LongRange.of(a+c,b));
		}
		public View2E<LongRange,Long> view2(){
			Long n = this.size();
			Preconditions.checkArgument(n>1,String.format("La lista debe ser de tamaño mayor que 1 y es %d  ",n));
			Long central = (b+a)/(2*c)*c;
			return View2E.of(central,LongRange.of(a,central,c),LongRange.of(central,b,c));
		}
		public View2E<LongRange,Long> view2Overlapping(){
			Long n = this.size();
			Preconditions.checkArgument(n>1,String.format("La lista debe ser de tamaño mayor que 1 y es %d  ",n));
			Long central = (b+a)/(2*c)*c;
			return View2E.of(central,LongRange.of(a,central+c,c),LongRange.of(central,b,c));
		}
	}
	
	public static record DoubleRange(Double a,Double b, Double c) {
		
		public static DoubleRange of(Double a, Double b, Double c) {
			return new DoubleRange(a,b,c);
		}
		public static DoubleRange of(Double a, Double b) {
			return new DoubleRange(a,b,1.);
		}
		public Boolean isEmpty() {
			return a>=b;
		}
		public Boolean contains(Double e) {
			return e>=a && e < b;
		}
		public Integer size() {
			return (int) ((b-a)/c);
		}
		public Double get(Integer i) {
			Preconditions.checkElementIndex(0,this.size());
			return this.a()+i*this.c();
		}
		public Stream<Double> stream(){
			return DoubleStream.of(a,b,c).boxed();
		}
		public View1<DoubleRange,Double> view1(){
			Integer n = this.size();
			Preconditions.checkArgument(n>0,String.format("La lista debe ser de tamaño mayor que 0 y es %d  ",n));
			return View1.of(this.a,DoubleRange.of(a+c,b));
		}
		public View2E<DoubleRange,Double> view2(){
			Integer n = this.size();
			Preconditions.checkArgument(n>1,String.format("La lista debe ser de tamaño mayor que 1 y es %d  ",n));
			Double central = (b+a)/2;
			return View2E.of(central,DoubleRange.of(a,central,c),DoubleRange.of(central,b,c));
		}
		public View2E<DoubleRange,Double> view2Overlapping(){
			Integer n = this.size();
			Preconditions.checkArgument(n>1,String.format("La lista debe ser de tamaño mayor que 1 y es %d  ",n));
			Double central = (b+a)/2;
			return View2E.of(central,DoubleRange.of(a,central+c,c),DoubleRange.of(central,b,c));
		}
	}
}
