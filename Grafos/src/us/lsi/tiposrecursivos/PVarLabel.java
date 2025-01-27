package us.lsi.tiposrecursivos;

public record PVarLabel<E> (String varName) implements BinaryPattern<E> {
	public PatternType type() { return PatternType.VarLabel; }
	public String toString() { return this.varName().toString() ; }
}
