package us.lsi.streams;

import java.util.List;
import java.util.stream.Collector;
import us.lsi.common.MutableType;
import us.lsi.streams.Spliterators2.SpliteratorOfView;

public class TestCollect {

	
	public static Collector<Double,MutableType<Double>,Double> suma(){
		return Collectors2.of(()->0.,
				(b,e)->b+e,
				(x,y)->x+y,
				x->x);
	}
	
	public static Collector<String,MutableType<String>,String> join(){
		return Collectors2.of(()->"",
				(b,e)->b+e,
				(x,y)->x+y,
				x->x);
	}
	
	public static void main(String[] args) {
//		List<Double> ls = List.of(1.,2.,56.,123.,-45.,567.,-2.,-89.,55.,67.,1.,2.,56.,123.,-45.,567.,-2.,-89.,55.,67.);
		List<String> ls2 = List.of("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17");
		SpliteratorOfView<List<String>,String> sp = Spliterators2.ofList(ls2);
		
		String r1 = Stream2.collect(sp.stream(), TestCollect.join());
		System.out.println("Sol1 = "+r1);
		String r2 = ls2.stream().reduce((x,y)->x+y).get();
		System.out.println("Sol2 = "+r2);
//		Stream<Double> s = new Random().doubles().limit(1000).boxed();
//		Double r3 = Stream2.collect(s, TestCollect.suma());
//		System.out.println("Sol = "+r3);
	}

}

