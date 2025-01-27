package us.lsi.common;

import java.util.function.BinaryOperator;

public class BinaryOperator2<E> implements BinaryOperator<E> {
	
	public static <E> BinaryOperator2<E> of(BinaryOperator<E> op)  {
		return new BinaryOperator2<E>(op);
	}

	private BinaryOperator<E> op;

	private BinaryOperator2(BinaryOperator<E> op) {
		super();
		this.op = op;
	}

	@Override
	public E apply(E t, E u) {
		if(t == null && u != null) return u;
		if(t != null && u == null) return t;
		if(t != null && u != null) return op.apply(t,u);
		else return null;
	}
	
}
