package us.lsi.tiposrecursivos;

public record PTree<E>(E label, BinaryPattern<E> left, BinaryPattern<E> right) implements BinaryPattern<E> {
	public PatternType type() {
		return PatternType.Binary;
	}

	public String toString() {
		return String.format("%s(%s,%s)", this.label().toString(), this.left().toString(), this.right().toString());
	}
}
