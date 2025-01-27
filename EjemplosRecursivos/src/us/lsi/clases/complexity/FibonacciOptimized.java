package us.lsi.clases.complexity;

import org.apache.commons.math3.FieldElement;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;

import us.lsi.common.Arrays2;
import us.lsi.common.Matrix;
import us.lsi.common.String2;


public  record  FibonacciOptimized<E extends FieldElement<E>>(E a, E b) {
	
	public static <E extends FieldElement<E>> FibonacciOptimized<E> of(E a, E b) {
		return new FibonacciOptimized<E>(a,b);
	}
	
	public static <E extends FieldElement<E>> FibonacciOptimized<E> unity(Field<E> field) {
		E one = field.getOne();
		E zero = field.getZero();
		return new FibonacciOptimized<E>(zero,one);
	}
	
	public static <E extends FieldElement<E>> FibonacciOptimized<E> base(Field<E> field) {
		E one = field.getOne();
		E zero = field.getZero();
		return new FibonacciOptimized<E>(one,zero);
	}
	
	public FibonacciOptimized<E> square() {
		E one = this.a().getField().getOne();
		E two = one.add(one);		
		E a = (this.a().multiply(this.a())).add(two.multiply(this.a()).multiply(this.b()));
		E b = (this.a().multiply(this.a())).add(this.b().multiply(this.b()));
		return FibonacciOptimized.of(a,b);
	}
	
	public FibonacciOptimized<E> multiplyBase() {
		return FibonacciOptimized.of(this.a().add(this.b()),this.a());
	}
	
	public FibonacciOptimized<E> multiply(FibonacciOptimized<E> other) {
		E a = this.a().multiply(other.a().add(other.b()))
				.add(other.a().multiply(this.b()));
		E b = this.a().multiply(other.a()).add(this.b().multiply(other.b()));
		return FibonacciOptimized.of(a,b);
	}
	
	
	public FibonacciOptimized<E> pow(Integer n,Field<E> field) {
		FibonacciOptimized<E> r;
		if(n==0) 
			r = FibonacciOptimized.unity(field);
		if(n==1)
			r = this;
		else {
			r = this.pow(n/2,field);
			r = r.square();
			if(n%2!=0){
				r = r.multiplyBase();				
			}
		}
		return r;
	}
	
	public FibonacciOptimized<E> pow_i(Integer n, Field<E> field) {
		FibonacciOptimized<E> r = FibonacciOptimized.base(field);
		FibonacciOptimized<E> u = FibonacciOptimized.unity(field);
		while (n > 0) {
			if (n % 2 == 1) {
				u = u.multiply(r);
			}
			r = r.square();
			n = n / 2;			
		}
		return u;

	}

	Matrix<E> toMatrix() {		
		E[] datos = Arrays2.newArray(this.a().add(this.b()),this.a(),this.a(),this.b());
		return Matrix.of(2,2,datos);
	}
		
	@Override
	public String toString() {
		return a.add(b) + "," + a +"\n"+ a +","+ b;
	}
	
	public static <E extends FieldElement<E>> E fibonacci(Integer n, Field<E> field) {
		FibonacciOptimized<E> r = FibonacciOptimized.base(field).pow(n,field);
		return r.a();
	}
	
	public static <E extends FieldElement<E>> E fibonacci_i(Integer n, Field<E> field) {
		FibonacciOptimized<E> r = FibonacciOptimized.base(field).pow_i(n,field);
		return r.a();
	}
	
	public static void test2() {
		Integer n = 500000;
		Long t0 = System.nanoTime();
		BigFraction r1 = FibonacciOptimized.fibonacci(n,BigFractionField.getInstance());
		Long t1 = System.nanoTime();
		BigFraction r2 = FibonacciOptimized.fibonacci_i(n,BigFractionField.getInstance());
		Long t2 = System.nanoTime();
		BigFraction r3 = Fibonacci.fibMatrix(n,BigFractionField.getInstance()); 
		Long t3 = System.nanoTime();
		String2.toConsole("fib = %s",r1);
		String2.toConsole("fib_i = %s",r2);
		String2.toConsole("fibMat = %s",r3);
		List<Long> ls = List.of(t1-t0,t2-t1,t3-t2);
		Double m = ls.stream().min(Comparator.naturalOrder()).get().doubleValue();
		List<Double> lsd = ls.stream().map(x->x/m).toList();
		List<String> lss = List.of("fib","fib_i","fibMat");
		System.out.println(lss);
		System.out.println(ls);
		System.out.println(lsd);
	}
	
	
	
	public static void main(String[] args) {
		test2();
	}
	
}

