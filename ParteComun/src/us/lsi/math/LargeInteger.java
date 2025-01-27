package us.lsi.math;


import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.List2;
import us.lsi.common.Preconditions;

/**
 * La clase implementa un entero largo. 
 * Los detalles del tipo pueden encontrarse 
 * en <a href="/document/LargeInteger.html" target="_blank">LargeInteger</a>
 * 
 */
public class LargeInteger implements Comparable<LargeInteger> {
	
	
	public static LargeInteger create(String digits) {
		return create(digits,10);
	}
	
	public static LargeInteger create(String digits,Integer base) {
		boolean isPositive = true;
		if(digits.charAt(0) == '-'){
			digits = digits.substring(1);
			isPositive = false;
		}
		List<Integer> dg = digits.chars().map(x->x-'0').boxed().collect(Collectors.toList());
		LargeInteger r = eval(dg, base);
		if (isPositive) return r;
		else return r.negate();
	}
	
	public static LargeInteger create(List<Integer> digits) {
		return new LargeInteger(digits);
	}

	public static LargeInteger create(Integer number) {
		return new LargeInteger(number);
	}

	public static LargeInteger create(Long number) {
		Integer[] r = {(int)(number/radix),(int)(number%radix)};
		return LargeInteger.create(Arrays.asList(r));
	}
	
	public static LargeInteger create(List<Integer> digits, Boolean isPositive) {
		return new LargeInteger(digits, isPositive);
	}
	
	public static LargeInteger create(LargeInteger m) {
		return new LargeInteger(m.digits, m.isPositive);
	}
	
	public static LargeInteger ZERO = LargeInteger.create(List2.of(0));
	public static LargeInteger ONE = LargeInteger.create(List2.of(1));
	public static LargeInteger TWO = LargeInteger.create(List2.of(2));
	public static LargeInteger TEN = LargeInteger.create(List2.of(10));
	
	/**
	 * Los dígitos están ordenados de más significativo a menos significativos
	 */
	private List<Integer> digits;
	/**
	 * La base usada
	 */
	private static Long radix = 2147483647L;
//	private static Long radix = 10L;
	/**
	 * El signo del entero grande
	 */
	private Boolean isPositive = true;
	
	private LargeInteger(List<Integer> digits) {
		this(List2.ofCollection(digits),true);
	}

	private LargeInteger(Integer number) {
		this(List2.of(number),true);
	}
	
	private LargeInteger(List<Integer> digits, Boolean isPositive) {
		super();
		Preconditions.checkArgument(digits.size()>=1, "Debe haber un dígito al menos");
		Preconditions.checkArgument(digits.stream().allMatch(x->x>=0), "Los digitos deben se positivos o cero");
		if(digits.size() == 1 && digits.get(0).equals(0)) isPositive = true;
		this.digits = List2.ofCollection(digits);
		this.isPositive = isPositive;
	}

	
	private static LargeInteger eval(List<Integer> n, Integer base){
		LargeInteger a = ZERO;
		LargeInteger b = ONE;
		for(int i=n.size()-1; i >= 0; i--){
			a = a.add(b.multiply(create(n.get(i))));
			b = b.multiply(TEN);
		}
		return a;
	}
	
	public int size(){
		return this.digits.size();
	}

	public Integer getDigit(int i){
		return this.digits.get(i);
	}
	
	public List<Integer> getDigits(){
		return List2.ofCollection(this.digits);
	}
	
	public boolean isNegative(){
    	return  !this.isPositive;
    }
	
	/**
	 * @return Multiplica por la base añadiendo un cero a la derecha
	 */
	public LargeInteger multiplyRadix(){
		List<Integer> r = List2.ofCollection(this.digits);
		r.add(0);
		return LargeInteger.create(r,this.isPositive);
	}
	
	/**
	 * @param a Un entero largo
	 * @param m El nuevo número de dígitos
	 * @return Un nuevo entero largo completado con dígitos no significativos a la izquierda
	 */
	
	public static LargeInteger completeWithLeftZeros(LargeInteger a, int m){
		int n = m-a.size();
		List<Integer> nDigits = List2.of(n, 0);
		nDigits.addAll(a.digits);
		return LargeInteger.create(nDigits,a.isPositive);
	}
	
	public LargeInteger completeWithLeftZeros(int m){
		return LargeInteger.completeWithLeftZeros(this,m);
	}
	
	/**
	 * @param m Un entero
	 * @return Multiplica por la base elevada a m añadiendo m ceros a las derecha
	 */
	public LargeInteger multiplyRadixToM(int m){
		List<Integer> r = List2.ofCollection(this.digits);
		List<Integer> nDigits = List2.of(m, 0);
		r.addAll(nDigits);
		return LargeInteger.create(r,this.isPositive);	
	}
	
	/**
	 * @return Elimina los ceros no significativos de la izquierda
	 */
	public LargeInteger deleteNonSignificantZeros(){
		int m = 0;
		for(int i=0;i<this.size();i++){
			if(!this.digits.get(i).equals(0)){
				m = i;
				break;
			}
		}	
		List<Integer> nDigits = this.digits.subList(m,this.size());
		return LargeInteger.create(nDigits,this.isPositive);
	}
	
	public LargeInteger divideRadixToM(int m){
		int n = this.size()-m;
		List<Integer> nDigits = this.digits.subList(0,n);
		return LargeInteger.create(nDigits,this.isPositive);
	}
	
		
    public LargeInteger abs(){
    	return LargeInteger.create(this.digits);
    }
    
    public LargeInteger negate(){
    	Boolean sig;
    	if(this.isPositive) sig = false;
    	else sig = true;
    	return LargeInteger.create(this.digits,sig);
    }
    
    @Override
	public int compareTo(LargeInteger object) {
    	return compare(this,object);
	}
    
	public static int compare(LargeInteger a, LargeInteger b){
		if(!a.isNegative() && b.isNegative()) return 1;
		if(a.isNegative() && !b.isNegative()) return -1;
		int r = 1;
		if(a.isNegative() && b.isNegative()){
			r = -1; 
		}
		int mx = Math.max(a.size(),b.size());
		a = completeWithLeftZeros(a,mx);
		b = completeWithLeftZeros(b,mx);
		int k = a.size();
		Long ac = 0L;
		for (int i = 0; i <k; i++) {
			ac = ac + a.getDigit(i) - b.getDigit(i);
			if (ac != 0) break;
		}
		return r*ac.intValue();
	}
	
	private static Integer[] addDigit(Integer a, Integer b){
		Long ab = a.longValue()+b.longValue();
		Integer[] r = {(int)(ab/radix),(int)(ab%radix)};
		return r;				
	}
	
	private static Integer[] minusDigit(Integer a, Integer b){
		Integer ab = a-b;
		Integer r = 0;
		if(ab<0) {
			ab = (int)(radix-b+a);
			r = -1;
		}
		Integer[] s = {r,ab};
		return s;				
	}
	private static Integer[] multiplyDigit(Integer a, Integer b){
		Long ab = a.longValue()*b.longValue();
		Integer[] s = {(int)(ab/radix),(int)(ab%radix)};
		return s;				
	}
	
	private static Integer[] divideDigit(Integer[] a, Integer b){
		Preconditions.checkArgument(a.length ==2);
		Long n = a[0].longValue()*radix+a[1];
		Integer[] s = {(int)(n/b),(int)(n%b)};
		return s;				
	}
	
	public static LargeInteger add(LargeInteger a, LargeInteger b){
		Boolean aPositive = true;
		Boolean bPositive = true;
		if(a.isNegative()){
			a = a.abs();
			aPositive = false;
		}
		if(b.isNegative()){
			b = b.abs();
			bPositive = false;
		}
		if(aPositive && !bPositive)
			return minus(a,b);
		else if(!aPositive && !bPositive)
			return add(a,b).negate();
		else if(!aPositive && bPositive)
			return minus(b,a);
		
		int mx = Math.max(a.size(),b.size());
		a = completeWithLeftZeros(a,mx);
		b = completeWithLeftZeros(b,mx);
		int i =  a.size()-1;
		int q = 0;
		List<Integer> digits = List2.empty();
		while(i >=0){
			Integer[] sd = addDigit(a.getDigit(i),q);
			q = sd[0];
			sd = addDigit(sd[1],b.getDigit(i));
			q = q+sd[0];
			digits.add(0,sd[1]);		
			i--;
		}
		digits.add(0,q);
		return LargeInteger.create(digits).deleteNonSignificantZeros();
	}
	
	public LargeInteger add(LargeInteger b){
		return add(this,b);
	}
	
	public LargeInteger add(Integer b){
		return add(this,create(b));
	}
	
	public static LargeInteger minus(LargeInteger a, LargeInteger b) {	
		int mx = Math.max(a.size(), b.size());
		a = completeWithLeftZeros(a, mx);
		b = completeWithLeftZeros(b, mx);
		Boolean positive = true;
		int cmp = compare(a, b);
		if(cmp == 0) return  ZERO;
		if (cmp<0) {
			LargeInteger c = b; b = a; a = c;
			positive = false;
		}
		int i = a.size() - 1;
		int q = 0;
		List<Integer> digits = List2.empty();
		while (i >= 0) {
			Integer[] sd = addDigit(a.getDigit(i),q);
			sd = minusDigit(sd[1],b.getDigit(i));
			q = sd[0];
			digits.add(0,sd[1]);		
			i--;
		}
		LargeInteger r = LargeInteger.create(digits).deleteNonSignificantZeros();
		if(positive) return r;
		else return r.negate();
	}
	
	public LargeInteger minus(LargeInteger b){
		return minus(this,b);
	}
	
	public LargeInteger minus(Integer b){
		return minus(this,create(b));
	}

	public static LargeInteger multiply(LargeInteger m, LargeInteger n) {
		Boolean aPositive = true;
		Boolean bPositive = true;
		Boolean rPositive = true;
		if (m.isNegative()) {
			m = m.abs();
			aPositive = false;
		}
		if (n.isNegative()) {
			n = n.abs();
			bPositive = false;
		}
		if ((aPositive && !bPositive) || (!aPositive && bPositive))
			rPositive = false;
		int mx = Math.max(m.size(), n.size());
		LargeInteger r;
		if (mx == 1) {
			Integer[] r0 = LargeInteger.multiplyDigit(m.getDigit(0),n.getDigit(0));
			r = LargeInteger.create(Arrays.asList(r0));
		} else {
			if(mx%2 == 1) mx = mx+1;
			m = completeWithLeftZeros(m, mx);
			n = completeWithLeftZeros(n, mx);
			int k = mx / 2;
			LargeInteger m1 = LargeInteger.create(m.digits.subList(0, k));
			LargeInteger m0 = LargeInteger.create(m.digits.subList(k, mx));
			LargeInteger n1 = LargeInteger.create(n.digits.subList(0, k));
			LargeInteger n0 = LargeInteger.create(n.digits.subList(k, mx));
			LargeInteger z2 = m1.multiply(n1);
			LargeInteger z1 = m1.multiply(n0).add(m0.multiply(n1));
			LargeInteger z0 = m0.multiply(n0);		
			r = z2.multiplyRadixToM(2 * k).add(z1.multiplyRadixToM(k)).add(z0);
		}
		
		if(rPositive) return r.deleteNonSignificantZeros();
		else return r.negate().deleteNonSignificantZeros();
	}
	
	public LargeInteger multiply(LargeInteger b){
		return multiply(this,b);
	}
	
	private static LargeInteger[] divide(LargeInteger a, Integer b){
		LargeInteger[] zr1 = {ZERO,a};
		LargeInteger[] zr2 = {a,ZERO};
		if(a.equals(ZERO)) return zr1;
		if(b.equals(1)) return zr2;	
		Boolean aPositive = true;
		Boolean bPositive = true;
		if(a.isNegative()){
			a = a.abs();
			aPositive = false;
		}
		if(b<0){
			b = Math.abs(b);
			bPositive = false;
		}
		Boolean sigr = true;
		if((aPositive && !bPositive) || (!aPositive && bPositive))
			sigr=false;
		int k = a.size();
		List<Integer> digits = List2.empty();	
		Integer[] cp = {a.getDigit(0),0};
		for(int i=0;i<k;i++){
			Integer[] s = {cp[1],a.getDigit(i)};
			cp = divideDigit(s,b);
			digits.add(cp[0]);		
		}
		LargeInteger c;		
		if(sigr) c = LargeInteger.create(digits).deleteNonSignificantZeros();
		else c = LargeInteger.create(digits).deleteNonSignificantZeros().negate();
		LargeInteger[] r = {c,create(cp[1])};
		return r;
	}

	public static LargeInteger[] divide(LargeInteger num, LargeInteger den){
		Boolean numPositive = true;
		Boolean denPositive = true;
		if(num.isNegative()){
			num = num.abs();
			numPositive = false;
		}
		if(den.isNegative()){
			den = den.abs();
			denPositive = false;
		}
		Boolean sigr = true;
		if((numPositive && !denPositive) || (!numPositive && denPositive))
			sigr=false;
		
		if(compare(num,den)<0){
			LargeInteger[] ss ={ZERO,den};
			return ss;
		} 
		
		int kd = den.size();
		Integer denD = den.getDigit(0);	
		LargeInteger numD = num.divideRadixToM(kd-1);
		LargeInteger q = divide(numD,denD)[0];
		LargeInteger r = add(den,ONE);
		LargeInteger s = TWO;
		LargeInteger t = TWO;
		while(compare(r.abs(),den)>=0 && compare(t.abs(),ONE)> 0){
			r = num.minus(q.multiply(den));
			s = divide(r.divideRadixToM(kd-1),denD)[0];
			t = divide(s, 2)[0];			
			q = add(q, t);
		}
		r = minus(num,multiply(q,den));
		while(r.isNegative()){
			q = q.minus(ONE);
			r = r.add(den);
		}
		q = LargeInteger.create(q.digits, sigr);
		LargeInteger[] u = {q,r};
		return u;
	}
	
	public LargeInteger[] divide(LargeInteger den) {
		return divide(this,den);
	}

	public LargeInteger[] divide(Integer den) {
		return divide(this,den);
	}
	
	@Override
	public String toString() {
		String r = isPositive?"":"-";
		LargeInteger n = this;
		StringBuffer rst = new StringBuffer();		
		while(n.compareTo(TEN) >= 0){
			LargeInteger[] cr = n.divide(10);
			n = cr[0];
			rst.insert(0,cr[1].getDigit(0));
		}
		rst.insert(0,n.getDigit(0));
		return r+rst.toString();
	}

	
	
	public static void main(String[] args) {
//		LargeInteger m = LargeInteger.create(Arrays.asList(1,4,5), "-");
//		m = m.addOneMoreSignificantDigit(7);
//		m = m.completeWithLeftZeros(5);
//		m = m.deleteNonSignificantZeros();
//		m = m.multiplyRadixToM(3);
//		m = m.multiplyRadix();
//		m = m.divideRadixToM(4);
//		int c = LargeInteger.create(Arrays.asList(1,4,5), "-").compareTo(LargeInteger.create(Arrays.asList(1,4,5), "-"));
//		System.out.println(m);
//		System.out.println(c);
//		System.out.println(Arrays.toString(addDigit(5,6)));
//		System.out.println(Arrays.toString(minusDigit(9,9)));
//		System.out.println(Arrays.toString(minusDigit(8,9)));
//		System.out.println(Arrays.toString(multiplyDigit(9,9)));
//		Integer[] a = {4,8};
//		System.out.println(Arrays.toString(divideDigit(a,4)));
		
//		System.out.println((9*9)%10);
//		System.out.println((long)(9223372036854775807.*9223372036854775805./9223372036854775807.));
//		System.out.println((new BigInteger("9223372036854775807")
//			.multiply(new BigInteger("9223372036854775805")).divide(new BigInteger("9223372036854775807"))));
//		System.out.println(Math2.pow(2,31)-1);
//		System.out.println(new BigInteger("2").pow(31));
//		System.out.println(new BigInteger("2").pow(62));
//		System.out.println(new BigInteger("2").pow(63));
//		System.out.println(2147483647L*2147483647L);
//		LargeInteger a1 = LargeInteger.create(Arrays.asList(2,3,4,0,7,8,3));
//		LargeInteger a2 = LargeInteger.create(Arrays.asList(2,3,4,0,7,8,3));
//		LargeInteger a3 = LargeInteger.create(Arrays.asList(2,4,6,8));
//		System.out.println(Arrays.toString(addDigit(5,6)));
//		System.out.println(Arrays.toString(minusDigit(9,9)));
//		System.out.println(Arrays.toString(minusDigit(8,9)));
//		System.out.println(Arrays.toString(multiplyDigit(9,9)));
//		System.out.println("a1 ="+a1);
//		System.out.println(a2.minus(a1));
//		System.out.println(2340783-2340783);
//		System.out.println(divide(a2,2));
//		System.out.println(2340783/2);
//		System.out.println("r="+a1.multiply(a2));
//		Long r = Math2.pow(2340783,2);
//		System.out.println("r="+r);
//		Long t1 = System.nanoTime();
//		LargeInteger a4 = LargeInteger.create("1457145797145714577145714579714571457145714579714571457714571457971457145771457145797145714571457145797145714577");
//		LargeInteger a5 = LargeInteger.create("52377145523771455237714552377145");
////		System.out.println("s="+a4.multiply(a5));
////		System.out.println("s="+new BigInteger("7145714579714571457").multiply(new BigInteger("5237714552377145")));
//		LargeInteger[] c1 = a4.divide(a5);
//		System.out.println(System.nanoTime()-t1);
//		System.out.println("d = "+c1[0]+","+c1[1]);
//		t1 = System.nanoTime();
//		BigInteger[] c2 = new BigInteger("1457145797145714577145714579714571457145714579714571457714571457971457145771457145797145714571457145797145714577").divideAndRemainder(new BigInteger("52377145523771455237714552377145"));
//		System.out.println(System.nanoTime()-t1);
//		System.out.println("d = "+c2[0]+","+c2[1]);
//		System.out.println(listOfDigits("934",2L));
		String s1 = "2147483669211111123214748366927777771123214727366921123214748366921123214748366921123214748366921123214748366921111112321474832147483669211111123214748366927777771123214727366921123214748366921123214748366921123214748366921123214748366921111112321474836692777777112321472736692112321474836692112321474836692112321474836692112366927777771123214727366921123214748366921123214748366921123214748366921123";
		String s2 = "232147483669211232147483669211221474836692112321474836692112321474836692112321474836692112";
		Long t1 = System.nanoTime();
		LargeInteger e1 = LargeInteger.create(s1);
		System.out.println("e1 = "+(System.nanoTime()-t1));
		t1 = System.nanoTime();
		LargeInteger e2 = LargeInteger.create(s2);
		System.out.println("e2 = "+(System.nanoTime()-t1));
		t1 = System.nanoTime();
		LargeInteger e3 = e1.multiply(e2);
		System.out.println("e3 = "+(System.nanoTime()-t1));
		t1 = System.nanoTime();
		System.out.println(e3);
		System.out.println("pe1 = "+(System.nanoTime()-t1));
		t1 = System.nanoTime();
		BigInteger b1 = new BigInteger(s1);
		System.out.println("b1 = "+(System.nanoTime()-t1));
		t1 = System.nanoTime();
		BigInteger b2 = new BigInteger(s2);
		System.out.println("b2 = "+(System.nanoTime()-t1));
		t1 = System.nanoTime();
		BigInteger b3 = b1.multiply(b2);
		System.out.println("b3 = "+(System.nanoTime()-t1));
		t1 = System.nanoTime();
		System.out.println(b3);
		System.out.println("pb3 = "+(System.nanoTime()-t1));
		t1 = System.nanoTime();
		LargeInteger[] r1 = e1.divide(e2);
		System.out.println("r1 = "+(System.nanoTime()-t1));
		t1 = System.nanoTime();
		System.out.println(Arrays.asList(r1));
		System.out.println("pr1 = "+(System.nanoTime()-t1));
		t1 = System.nanoTime();
		BigInteger[] r2 = b1.divideAndRemainder(b2);
		System.out.println("r2 = "+(System.nanoTime()-t1));
		t1 = System.nanoTime();
		System.out.println(Arrays.asList(r2));
		System.out.println("pr2 = "+(System.nanoTime()-t1));
		t1 = System.nanoTime();
		System.out.println(s1.length());
//		System.out.println(new BigInteger("1").multiply(new BigInteger("2147483647")).add(new BigInteger("3196789")));
//		System.out.println(Arrays.asList(new BigInteger(s).divideAndRemainder(new BigInteger("2147483647"))));
//		System.out.println(LargeInteger.create(Arrays.asList(100000,3196789)));
//		System.out.println(10*radix+1069820087);
//		System.out.println(LargeInteger.create(ZERO));
//		System.out.println(LargeInteger.create(ONE));
//		System.out.println(LargeInteger.create(TWO));
//		System.out.println(LargeInteger.create(ZERO));
//		String digits = "934456111";
//		List<Integer> n = digits.chars().map(x->x-'0').boxed().collect(Collectors.toList());
//		int i = 0;
//		LargeInteger a = ZERO;
//		LargeInteger b = ONE;
//		System.out.println(" a ==="+a);
//		System.out.println("base = "+10+" b ==="+b);
//		System.out.println(i+","+n.get(i)+", "+digits.charAt(i)+"==="+a);
//		a = a.add(b.multiply(n.get(i)));
//		System.out.println(b.multiply(n.get(i)));
//		System.out.println(a);
//		b = b.multiply(10);
//		System.out.println(i+","+n.get(i)+"==="+a);
//		System.out.println("base = "+10+" b ==="+b);
//		System.out.println(ONE.multiply(1));
//		System.out.println(LargeInteger.create("1000000000").multiply(TEN));
//		System.out.println(TEN);
	}
	

}
