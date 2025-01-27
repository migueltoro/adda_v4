package us.lsi.tiposrecursivos;

public record PVarTree<E> (String varName, BinaryPattern<E> left, BinaryPattern<E> right) implements BinaryPattern<E> {
	public PatternType type() { return PatternType.VarTree; }
	public String toString() { return String.format("%s(%s,%s)",
			this.varName().toString(),this.left().toString(),this.right().toString());}
}
