package us.lsi.gurobi_test;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import us.lsi.common.String2;
import us.lsi.solve_test.PLIModelVisitorC;

public class Test {
	
	public static void test1() {
		String s = "4 + 3 x_2 - 2 + x_4 + 4 - y$_56";	
		Pattern p = Pattern.compile("( \\+ | - |^)[0-9]+ [\\+-]");
		Matcher m = p.matcher(s);
		Integer r = 0;
		while(m.find()) {
			String n = m.group().replace(" ","");
//			String2.toConsole(n);
			n = n.substring(0,n.length()-1);
			r -= Integer.parseInt(n);
		}
		m = p.matcher(s);
		String left = m.replaceAll(ss->ss.group().substring(ss.group().length()-2,ss.group().length()));
		String2.toConsole("left = %s, right = %d",left,r);
		String2.toConsole(PLIModelVisitorC.reorderGenExp(s).toString());
	}
	
	public static void test2(String s) {
		String2.toConsole("%s",PLIModelVisitorC.reorderGenExp(s));
	}

	public static void main(String[] args) {
		String2.toConsole("%d",Integer.parseInt("+4"));
		test1();
		test2("x_1 + 1");
		test2("4 + 3 x_2 - 2 + x_4 + 4 - y$_56");
	}

}
