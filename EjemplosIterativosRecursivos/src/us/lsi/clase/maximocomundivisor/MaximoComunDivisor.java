package us.lsi.clase.maximocomundivisor;

import java.util.stream.Stream;

public class MaximoComunDivisor {
	
	public static Integer mcd(Integer a, Integer b) {
		if (a < 0 || b < 0) {
			throw new IllegalArgumentException("Los números deben ser positivos");
		}
		while (b > 0) {
			Integer r = a % b;
			a = b;
			b = r;
		}
		return a;
	}
	
	public static Integer mcd_2(Integer a, Integer b) {
		Mcd mcd = Mcd.of(a, b);
		while (mcd.g()) {
			mcd = mcd.nx();
		}
		return mcd.r();
	}
	
	public static Integer mcd_3(Integer a, Integer b) {
		Mcd mcd = Mcd.of(a, b);		
		mcd = mcd_3(mcd);
		return mcd.r();
	}
	
	public static Mcd mcd_3(Mcd mcd) {
		if (mcd.g()) {
			return mcd_3(mcd.nx());
		}
		return mcd;
	}
	
	public static Integer mcd_4(Integer a, Integer b) {
		Stream<Mcd> s = Stream.iterate(Mcd.of(a, b),m->m.nx()).dropWhile(m->m.g());
		Mcd mcd = s.findFirst().get();
		return mcd.r();
	}


	public static void main(String[] args) {
		System.out.println(mcd(48, 18));
		System.out.println(mcd_2(48, 18));
		System.out.println(mcd_3(48, 18));
		System.out.println(mcd_4(48, 18));
	}

}
