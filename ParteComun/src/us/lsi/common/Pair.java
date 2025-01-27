package us.lsi.common;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public record Pair<A, B>(A first,B second) {
	
	public static <A,B>  Pair<A,B> of(A a, B b){
		return new Pair<>(a,b);
	}
	
	public static <A,B>  Pair<A,B> parse(String text, String delimiters, 
			Function<String,A> first,Function<String,B> second){
		String[] partes = text.split(delimiters);
		List<String> partes2 = Arrays.stream(partes).filter(p->!p.isEmpty()).toList();
		A a = first.apply(partes2.get(0));
		B b = second.apply(partes2.get(1));
		return new Pair<>(a,b);
	}

	@Override
	public String toString() {
		return String.format("(%s,%s)",first,second);
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(Pair.parse("[23,45","[\\[,]",s->Integer.parseInt(s),s->Integer.parseInt(s)));
	}
		
}

