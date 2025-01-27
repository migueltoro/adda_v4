package us.lsi.tiposrecursivos.ast;

import java.io.IOException;

import us.lsi.colors.GraphColors;

public class TestAst {

	public static void main(String[] args) throws IOException {
		Ast p = Ast.parse("ficheros/program3.txt");
	
		
		if(p!=null) {
			Operators.addOperators();
			System.out.println(p);
			GraphColors.toDot(p,"ficheros/program.gv");
//			System.out.println(p.block().sentences());
//			Exp s = p.block().sentences().get(0).;			
//			GraphColors.toDot(p, "ficheros/program.gv");
//			GraphColors.toDot(s, "ficheros/exp.gv");
//			FunDeclaration fd = (FunDeclaration) p.block().symbolTable().get("min");
//			System.out.println(fd);
//			System.out.println(fd.block());
			IfThenElse s = (IfThenElse) p.block().sentences().get(0);
			Assign a = (Assign)s.trueBlock().sentences().get(0);
			System.out.println(a.exp());
			System.out.println(a.exp().type());
			System.out.println(((Binary)a.exp()).right());
			Exp ex = (Exp)((CallFunction)((Binary)a.exp()).right()).parameters().get(0);
//			System.out.println(ex);
//			System.out.println(ex.type());
//			System.out.println(((Binary)a.exp()).right().type());
//			OperatorId id = OperatorId.of2("+",Type.Int,Type.Int);
//			Operator op = Operators.operators.get(id);
//			System.out.println(id.longName());
//			System.out.println(p.block().symbolTable());
		}
		
	}
}
