package us.lsi.tiposrecursivos.ast;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import us.lsi.common.Preconditions;

public sealed interface Exp extends Vertex 
	permits Unary, Binary, Ternary, Var, Const, CallFunction, Nary{
	
	Set<Var> vars();
	Operator operator();
	Object value();
	Type type();
	String name();
	Boolean isConst();
	Exp simplify();
	
	public static Exp of(List<Exp> operands, Operator op) {
		Exp r = switch(op.operatorId().arity()) {
		case 0 -> operands.get(0);
		case 1 -> Unary.of(operands.get(0),op.operatorId().name());
		case 2 -> Binary.of(operands.get(0),operands.get(1),op.operatorId().name());
		case 3 -> Ternary.of(operands.get(0),operands.get(1),operands.get(2),op.operatorId().name());
		case -1 -> Nary.of(operands,op.operatorId().name());
		default -> null;
		};
		return r;
	}
	
	public static List<Exp> ofOperatorsInLevels(List<List<Operator>> operators) {
		return Exp.of(operators,0);
	}
	
	private static List<Exp> of(List<List<Operator>> operators, Integer level) {
		Integer n = operators.size();
		List<Exp> r;
		if (level == n - 1)
			r = operators.get(level).stream().map(op -> (Exp) op).toList();
		else {
			List<Operator> ls = operators.get(level);
			List<Exp> next = of(operators, level + 1);
			Integer index = 0;
			r = new ArrayList<>();
			for (int i = 0; i < ls.size(); i++) {
				Exp e = null;
				Operator op = ls.get(i);
				if (op.operatorId().arity() == 0) e = (Exp) op;
				else if (op.operatorId().arity() == 1) {
					e = Unary.of(next.get(index), op.operatorId().name());
					index += 1;
				} else if (op.operatorId().arity() == 2) {
					e = Binary.of(next.get(index), next.get(index + 1), op.operatorId().name());
					index += 2;
				} else {
					Preconditions.checkArgument(0 <= op.operatorId().arity() && op.operatorId().arity() <= 2,
							String.format("La aridad es %d", op.operatorId().arity()));
				}
				r.add(e);
			}
		}
		return r;
	}
}
