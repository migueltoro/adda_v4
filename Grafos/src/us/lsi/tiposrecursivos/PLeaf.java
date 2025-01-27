package us.lsi.tiposrecursivos;

public record PLeaf<E>(E label) implements BinaryPattern<E> {
	public PatternType type() { return PatternType.Leaf;}
	public String toString() { return this.label().toString(); }
}
