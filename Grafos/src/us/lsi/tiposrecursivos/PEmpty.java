package us.lsi.tiposrecursivos;

public record PEmpty<E>() implements BinaryPattern<E> {
	public PatternType type() { return PatternType.Empty;}
	public String toString() { return "_"; }
}
