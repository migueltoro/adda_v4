package us.lsi.tiposrecursivos.ast;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import us.lsi.colors.GraphColors;
import us.lsi.common.List2;
import us.lsi.common.Map2;
import us.lsi.common.String2;

public class TestExp {
	
	public static void test1() {
		Operators.addOperators();
		String2.toConsole("%s",Operators.operators.keySet().stream().map(e->e.longName()).toList());
		Var a = Var.of("a",Type.Int);
		Var b = Var.of("b",Type.Int);
		Const c = Const.of(34,Type.Int);
		Binary be1 = Binary.of(a, b, "+");
		Binary be2 = Binary.of(be1, c, "*");
		Unary ue1 = Unary.of(be2,"toDouble");
		Unary ue2 = Unary.of(ue1,"sqrt");
		String2.toConsole("%s",ue2.type());
		String2.toConsole("%s",ue2.vars());
		Set<Var> vars =ue2.vars();
		Map<String,Object> values = Map2.of("a",33,"b",45);
		vars.stream().forEach(v->v.setValue(values.get(v.name())));
		vars.stream().forEach(v->String2.toConsole(String.format("%s,%s,%s",v.name(),v.value(),v.value().getClass())));
		String2.toConsole("%s",ue2);
		String2.toConsole("1 %s",a.value());
		String2.toConsole("2 %s",b.value());
		String2.toConsole("3 %s",c.value());
		String2.toConsole("4 %s",be1.value());
		String2.toConsole("5 %s",be2.value());
		String2.toConsole("6 %s",ue1.value());
		String2.toConsole("7 %s",ue2.value());
		GraphColors.toDot(ue2,"ficheros/exp.gv");
	}
	
	public static void test2() {
		Operators.addOperators();
		Operator a = Var.of("a",Type.Double);
		Operator b = Const.of(3.45,Type.Double);
		List<Operator> lv2 = List2.of(a,b,a);
		List<Operator> lv1 = List2.of(Operators.get2("+",Type.Double,Type.Double),Operators.get1("sqrt",Type.Double));
		List<Operator> lv0 = List2.of(Operators.get2("+",Type.Double,Type.Double));
		String2.toConsole("%s",Exp.ofOperatorsInLevels(List.of(lv0,lv1,lv2)));
	}
	
	
	
	public static void main(String[] args) throws IOException {
		test1();
	}

}
