package us.lsi.tiposrecursivos;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import us.lsi.common.MutableType;


public sealed interface  BinaryPattern<E> permits PTree, PEmpty, PLeaf, PVarLabel, PVarTree {
	
	public enum PatternType{Empty, Leaf, Binary, VarLabel , VarTree}
	
	public static <E> BinaryPattern<E> binary(E label, BinaryPattern<E> left, BinaryPattern<E> right) {
		return new PTree<E>(label, left, right);
	}
	
	public static <E> BinaryPattern<E> empty() {
		return new PEmpty<E>();
	}
	
	public static <E> BinaryPattern<E> leaf(E label) {
		return new PLeaf<>(label);
	}
	
	public static <E> BinaryPattern<E> varLabel(String varName) {
		return new PVarLabel<>(varName);
	}
	
	public static <E> BinaryPattern<E> varTree(String varName,BinaryPattern<E> left, BinaryPattern<E> right) {
		return new PVarTree<>(varName,left,right);
	}
	
	public static <E> BinaryPattern<E> parse(String s, Function<String,E> f){
		BinaryTree<String> tree = BinaryTree.parse(s);
		return BinaryPattern.of(tree, f);
	}
	
	public static <E> BinaryPattern<E> parse(String s){
		BinaryTree<String> tree = BinaryTree.parse(s);
		return BinaryPattern.of(tree, v->null);
	}
	
	public static <E> BinaryPattern<E> of(BinaryTree<String> tree, Function<String,E> f){
		return switch(tree) {
		case BEmpty<String> t -> BinaryPattern.<E>empty();
		case BLeaf<String> t when t.label().startsWith("_")-> BinaryPattern.varLabel(t.label());
		case BLeaf<String> t -> BinaryPattern.leaf(f.apply(t.label()));
		case BTree<String> t when t.label().startsWith("_") -> BinaryPattern.varTree(t.label(),
				BinaryPattern.of(t.left(),f),BinaryPattern.of(t.right(),f));
		case BTree<String> t -> BinaryPattern.binary(f.apply(t.label()),
				BinaryPattern.of(t.left(),f),BinaryPattern.of(t.right(),f));
		};
	}
	
	public static <E> Matches<E> match(BinaryPattern<E> pt, BinaryTree<E> tree) {
		return switch(pt) {
		case PEmpty<E> p  when tree instanceof BEmpty<E> t -> Matches.of(true);
		case PLeaf<E> p when tree instanceof BLeaf<E> t -> Matches.of(p.label().equals(t.label())); 
		case PVarLabel<E> p -> Matches.ofTrees(new HashMap<>(Map.of(p.varName(), tree))); 
		case PTree<E> p when tree instanceof BTree<E> t && p.label().equals(t.label()) -> {
			Matches<E> r = match(p.left(), t.left());
			if(r.match)  r.add(match(p.right(), t.right()));
			yield r;
		}
		case PVarTree<E> p when tree instanceof BTree<E> t -> {
			Matches<E> r = Matches.ofLabels(new HashMap<>(Map.of(p.varName(),t.label())));
			r.add(match(p.left(), t.left()));
			if(r.match)  r.add(match(p.right(), t.right()));
			yield r;
		}
		default -> Matches.of(false);		 
		};
	}
	
	public default Matches<E> match(BinaryTree<E> tree) {
		return BinaryPattern.match(this, tree);
	}
	
	public static <E> BinaryTree<E> sustitute(BinaryPattern<E> pt, Matches<E> matches) {
		return switch(pt) {
		case PEmpty<E> p -> BinaryTree.empty(); 
		case PLeaf<E> p ->  BinaryTree.leaf(p.label()); 
		case PVarLabel<E> p -> matches.treeMatches.get(p.varName()); 
		case PTree<E> p -> BinaryTree.binary(p.label(),p.left().sustitute(matches),p.right().sustitute(matches)); 
		case PVarTree<E> p ->  BinaryTree.binary(
				matches.labelsMatches.get(p.varName()),
				p.left().sustitute(matches),
				p.right().sustitute(matches)); 
		};
	}
	
	public default BinaryTree<E> sustitute(Matches<E> matches) {
		return BinaryPattern.sustitute(this, matches);
	}
	
	public static <E> BinaryTree<E> transform(BinaryTree<E> tree, BinaryPattern<E> pattern, 
			BinaryPattern<E> result, MutableType<Boolean> rs){		
		Matches<E> m = pattern.match(tree);	
		BinaryTree<E> r = tree;
		if(m.match) r = result.sustitute(m);
		rs.setValue(m.match);
		return r;
	}
	
	public static class Matches<E> {
		public Map<String,E> labelsMatches;
		public Map<String,BinaryTree<E>> treeMatches;
		public Boolean match;
		public static <E> Matches<E> of(Map<String, E> labelsMatches, Map<String, BinaryTree<E>> treeMatches, Boolean match){
			return new Matches<>(labelsMatches, treeMatches, match);
		}
		public static <E> Matches<E> of(Boolean r){
			return new Matches<E>(new HashMap<String,E>(), new HashMap<String, BinaryTree<E>>(), r);
		}
		public static <E> Matches<E> ofLabels(Map<String, E> labelsMatches){
			return new Matches<E>(labelsMatches, new HashMap<String, BinaryTree<E>>(), true);
		}
		public static <E> Matches<E> ofTrees(Map<String, BinaryTree<E>> treeMatches){
			return new Matches<E>(new HashMap<String,E>(), treeMatches, true);
		}
		public Matches(Map<String, E> labelsMatches, Map<String, BinaryTree<E>> treeMatches, Boolean match) {
			super();
			this.labelsMatches = labelsMatches;
			this.treeMatches = treeMatches;
			this.match = match;
		}
		public void add(Matches<E> rr) {
			this.match = this.match && rr.match;
			this.labelsMatches.putAll(rr.labelsMatches);	
			this.treeMatches.putAll(rr.treeMatches);			
		}
		@Override
		public String toString() {
			return "match=" + match +
					"\nlabelsMatches=" + labelsMatches + 
					"\ntreeMatches=" + treeMatches;
		}
		
	}
	
	
	public static void test1() {
		BinaryTree<Integer> t1 = BinaryTree.empty();
		BinaryTree<Integer> t2 = BinaryTree.leaf(2);
		BinaryTree<Integer> t3 = BinaryTree.leaf(3);
		BinaryTree<Integer> t4 = BinaryTree.leaf(4);
		BinaryTree<Integer> t5 = BinaryTree.binary(27,BinaryTree.binary(42,t1,t2),BinaryTree.binary(59,t3,t4));
		BinaryTree<Integer> t6 = BinaryTree.binary(39, t2,t5);
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t6);
		
		System.out.println("__________________");
		 
		BinaryTree<Double> t = BinaryTree.parse("-43.7(2.1,56(-27.3(_,2),78.2(3,4)))", x->Double.parseDouble(x));		
		BinaryPattern<Double> p = BinaryPattern.parse("-43.7(2.1,56(_e0(_,2),_T0))",x->Double.parseDouble(x));
		BinaryPattern<Double> r = BinaryPattern.parse("-43.7(_T0,_e0(_e0(_,_T0),_T0))",x->Double.parseDouble(x));
		BinaryPattern<Double> r2 = BinaryPattern.parse("_e0(_T0,_T0)",x->Double.parseDouble(x));
//		BinaryPattern<Double> q = BinaryPattern.parse("_d0(_T0,_c0(_e0(_,_T0),_T0))",x->Double.parseDouble(x));
//        BinaryPattern<Double> leftRight = BinaryPattern.parse("_e5(_e3(_A,_e4(_B,_C)),_D)",x->Double.parseDouble(x));
//        BinaryTree<Double> tree1 = BinaryTree.parse("54.5(39.2(20.1,50.5(40.2,51.0)),78.9)",x->Double.parseDouble(x));
//        BinaryPattern<Double> rightLeft = BinaryPattern.parse("_e3(_A,_e5(_e4(_B,_C),_D))",x->Double.parseDouble(x));
//		BinaryTree<Double> tree2 = BinaryTree.parse("54.5(39.2,20.1(50.5(40.2,51.0),78.9))",x->Double.parseDouble(x));
//        BinaryPattern<Double> leftLeft = BinaryPattern.parse("_e5(_e4(_e3(_A,_B),_C),_D)",x->Double.parseDouble(x));
//        BinaryTree<Double> tree3 = BinaryTree.parse("54.5(39.2(20.1(50.5,40.2),51.0),78.9)",x->Double.parseDouble(x));
//        BinaryPattern<Double> rightRight = BinaryPattern.parse("_e3(_A,_e4(_B,_e5(_C,_D)))",x->Double.parseDouble(x));
//        BinaryTree<Double> tree4 = BinaryTree.parse("54.5(39.2,20.1(50.5,40.2(51.0,78.9)))",x->Double.parseDouble(x));
//        BinaryPattern<Double> result = BinaryPattern.parse("_e4(_e3(_A,_B),_e5(_C,_D))",x->Double.parseDouble(x));
		
		
        System.out.println("__________________");
        System.out.println("t = "+t);
        System.out.println("p = "+p);
		System.out.println("r = "+r);
		System.out.println("r2 = "+r2);
        
		Matches<Double> m = p.match(t);
		System.out.println(m);
//		System.out.println(r2.toBinaryTree(m));
//		System.out.println("Aqui 0 = "+t.transform(p, r));
//		System.out.println("__________________");
//		System.out.println(tree1);
//		System.out.println(leftRight);
//		System.out.println(result);
//		System.out.println("Aqui 1 = " + tree1.transform(leftRight, result));
//		System.out.println(getTypeEquilibrate(tree1));
//		System.out.println(getTypeEquilibrate(tree1r));
//		System.out.println(tree2);
//		System.out.println(rightLeft);
//		System.out.println(rightLeft.varLabels()+"----"+rightLeft.varTrees());
//		System.out.println(result);			
//		System.out.println(getTypeEquilibrate(tree2r));
//		System.out.println(tree3);
//		System.out.println(leftLeft);
//		System.out.println(result);
//		System.out.println("Aqui 3 = "+tree3.transform(leftLeft, result));
//		System.out.println(getTypeEquilibrate(tree3));
//		System.out.println(getTypeEquilibrate(tree3r));
//		System.out.println(tree4);
//		System.out.println(rightRight);
//		System.out.println(result);
//		System.out.println("Aqui 4 = "+tree4.transform(rightRight, result));
//		System.out.println(getTypeEquilibrate(tree4));
//		System.out.println(getTypeEquilibrate(tree4r));
//		System.out.println(map(t,p,r));
//		BinaryPattern<Integer> p0 = BinaryPattern.free("_t0");
//		BinaryPattern<Integer> pt = BinaryPattern.binaryFree("_e0",BinaryPattern.leaf(2),p0);
//		System.out.println(pt);
//		System.out.println(pt.values());
//		System.out.println(match(t6,pt));
//		System.out.println(pt.values());
	}
	
	public static void test2() {
		
	}
	
	public static void main(String[] args) {
		test1();
	}

}