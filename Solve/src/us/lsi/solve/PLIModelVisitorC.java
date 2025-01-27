package us.lsi.solve;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import us.lsi.common.Pair;
import us.lsi.common.Preconditions;
import us.lsi.common.String2;
import us.lsi.model.PLIModelBaseVisitor;
import us.lsi.model.PLIModelParser;
import us.lsi.solve.AuxGrammar.Limits;
import us.lsi.solve.AuxGrammar.ListString;
import us.lsi.solve.AuxGrammar.Type;
import us.lsi.streams.Stream2;


public class PLIModelVisitorC extends PLIModelBaseVisitor<Object>{
	
	public static String negative(String s) {
		s = s.trim();
		if(s=="") return s;
		if(Character.isAlphabetic(s.charAt(0))) s = " + "+s;
		String r = "";
		for(int i =0; i<s.length();i++) {
			if(s.charAt(i) == '+') r = r + '-';
			else if (s.charAt(i) == '-') r = r + '+';
			else r = r + s.charAt(i);
		}
		return r;
	}
	
	public static String removeLast(String s) {
		s = s.replace(" ","");
		while(!Character.isDigit(s.charAt(s.length()-1))) 
			s = s.substring(0,s.length()-1);
		return s;
	}
	
	public static String manteinLast(String s) {
		String r = " ";
		for(int i = s.length()-1; i>=0; i--) {
		   if(Character.isDigit(s.charAt(i))) break;
		   r = r+s.charAt(i);
		}   
		return r;
	}
	
	
	public static Pair<String,Integer> reorderGenExp(String s) {
		Pattern p = Pattern.compile(" *(\\+|-|^) *[0-9]+( *([\\+-])|$)");
		Matcher m = p.matcher(s);
		Integer r = 0;
		while(m.find()) {
			String g = m.group();
			g = removeLast(g);
			r -= Integer.parseInt(g);
		}
		m = p.matcher(s);
		String left = m.replaceAll(ss->manteinLast(ss.group()));
		return Pair.of(left,r);
	}
	
	public static String implicitBins() {
		String r = IntStream.range(0,AuxGrammar.nBinarys).boxed()
				.map(i->String.format("x$_%d",i))
				.collect(Collectors.joining(" "));
		r += AuxGrammar.dBinaries.stream().collect(Collectors.joining(" "));
		return r;
	}
	
	public static String implicitInts() {
		return IntStream.range(0,AuxGrammar.nInts).boxed()
				.map(i->String.format("y$_%d",i))
				.collect(Collectors.joining(" "," ",""));
	}
	
	public void differentValue(String exp1, String exp2) {
		Pair<String,Integer> p1 = PLIModelVisitorC.reorderGenExp(exp1);
		Pair<String,Integer> p2 = PLIModelVisitorC.reorderGenExp(exp2);
		String nExp2 = negative(p2.first());
		Integer r = AuxGrammar.nFrees;
		String c1;
		if (nExp2.trim()=="") 	c1 = String.format("%s - y$_%d = %d", p1.first(), r, p1.second() - p2.second());
		else c1 = String.format("%s%s - y$_%d = %d", p1.first(), nExp2, r, p1.second() - p2.second());
//		String2.toConsole("1===%s,%s,%s,%s,%s,%s",exp1,exp2,p1.toString(),p2.toString(),nExp2,c1);
		String s = String.format("y$_%d free",r);
		AuxGrammar.bounds.add(s);
		String abs = String.format("y$_%d =  ABS ( y$_%d )",r+1,r);
		String c2 = String.format("y$_%d >= 1",r+1);
		AuxGrammar.nFrees += 2;
		AuxGrammar.constraints.add(c1);
		AuxGrammar.constraints.add(c2);
		AuxGrammar.generalConstraints.add(abs);
	}
	
	public static String generalConstrainsts() {
		Integer m = AuxGrammar.generalConstraints.size();
		String lt2 = IntStream.range(0,m).boxed()
				.map(i ->String.format("gc%d: %s", i, AuxGrammar.generalConstraints.get(i)))
				.collect(Collectors.joining("\n","\n",""));
		return lt2;
	}
	
	public void orConstraint(String op, Integer n, List<String> constraints) {
		Integer nc = constraints.size();
		Integer r = AuxGrammar.nBinarys;
		List<String> lc = IntStream.range(0,nc).boxed()
				.map(i->String.format("x$_%d = 1 -> %s",r+i,constraints.get(i)))
				.collect(Collectors.toList());		
		AuxGrammar.nBinarys += nc;
		String s = IntStream.range(0,nc).boxed()
				.map(i->String.format("x$_%d",r+i))
				.collect(Collectors.joining(" + ",""," "+op+" "+n));
		lc.add(s);
		AuxGrammar.constraints.addAll(lc);
	}
	
	@Override public Object visitModel(PLIModelParser.ModelContext ctx) { 
		if(ctx.head()!=null) visit(ctx.head());
		System.out.println(AuxGrammar.functions.entrySet().stream()
				.map(e->String.format("(%s,%s)",e.getKey(),e.getValue().toString()))
				.collect(Collectors.joining("\n")));
		System.out.println(AuxGrammar.parametersType.entrySet().stream()
				.map(e->String.format("(%s,%s)",e.getKey(),AuxGrammar.toString(e.getValue())))
				.collect(Collectors.joining(",")));
		System.out.println(AuxGrammar.values.entrySet().stream()
				.map(e->String.format("(%s,%s)",e.getKey(),e.getValue().toString()))
				.collect(Collectors.joining(",")));
		String goal = AuxGrammar.asString(visit(ctx.goal()));
		String constraints = String.format("\nSubject To\n\n%s\n",AuxGrammar.asString(visit(ctx.constraints())));
		String generalConstraints = "";
		if(AuxGrammar.generalConstraints.size()>0)	generalConstraints += String.format("\nGeneral Constraints\n\n%s\n",generalConstrainsts());
		String bounds = "";
	    if(ctx.bounds()!=null || ctx.free_vars()!=null) bounds = String.format("\nBounds\n\n");
	    if(ctx.bounds()!=null) bounds += String.format("\n%s\n",AuxGrammar.asString(visit(ctx.bounds())));
	    if(ctx.free_vars()!=null) bounds += AuxGrammar.asString(visit(ctx.free_vars()));
	    String ints = implicitInts();	    
	    if(ctx.int_vars()!=null) ints += AuxGrammar.asString(visit(ctx.int_vars()));
	    ints = AuxGrammar.allSpaces(ints)?ints:String.format("\nGeneral\n\n%s\n",ints);
	    String bins = implicitBins();
	    if(ctx.bin_vars()!=null) bins += AuxGrammar.asString(visit(ctx.bin_vars()));
	    bins = AuxGrammar.allSpaces(bins)? bins:String.format("\nBinary\n\n%s\n",bins);
	    String semiContinous = "";
	    if(ctx.semi_continuous_vars()!=null) semiContinous += AuxGrammar.asString(visit(ctx.semi_continuous_vars()));
	    semiContinous = AuxGrammar.allSpaces(semiContinous)? semiContinous:String.format("\nSemi-continuous\n\n%s\n",semiContinous);
		return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\nEnd",goal,constraints,bounds,bins,ints,semiContinous,generalConstraints);
	}
	
	
	@Override public Object visitHead(PLIModelParser.HeadContext ctx) { 
		return visitChildren(ctx); 
	}
	
	
	@Override public Object visitVarDeclar(PLIModelParser.VarDeclarContext ctx) { 
		String name = ctx.name.getText();
		String type = ctx.type.getText();		
		Object val =  this.visit(ctx.exp());		
		switch(type) {
		case "Integer":
			Preconditions.checkArgument(AuxGrammar.isInteger(val),"El resultado de la expresi�n debe ser entero");
			AuxGrammar.types.put(name,Type.INT); 
			break;
		case "Double": 
			Preconditions.checkArgument(AuxGrammar.isDouble(val),"El resultado de la expresi�n debe ser double");
			AuxGrammar.types.put(name,Type.DOUBLE); 
			break;
		}
		AuxGrammar.values.put(name,val);
		return name; 	
	}
	
	
	    
	@Override public Object visitFunDeclar(PLIModelParser.FunDeclarContext ctx) { 
		String name = ctx.name.getText();
		Type rt = AuxGrammar.asType(ctx.type.getText());
		Integer n = 0;
		if(ctx.formal_parameters() != null) n = ctx.formal_parameters().formal_parameter().size();
		Type types[] = IntStream.range(0,n).boxed()
				.map(i->AuxGrammar.asType(ctx.formal_parameters().formal_parameter(i).type.getText()))
			    .collect(Collectors.toList())
			    .toArray(new Type[n]);			
		AuxGrammar.parametersType.put(name,types);
		Method m = AuxGrammar.getMethod(name,types);
		AuxGrammar.functions.put(name,m);
		AuxGrammar.resultType.put(name,rt);
		return ""; 
	}
	
	
	@Override public Object visitGoalSection(PLIModelParser.GoalSectionContext ctx) { 
		String goal = null;
		switch(ctx.obj.getText()) {
		case "min": goal = "Minimize"; break;
		case "max": goal = "Maximize"; break;
		}
		String e = AuxGrammar.asString(visit(ctx.generate_exp()));
		String r = String.format("%s\n\n%s\n",goal,e);
		return r;
	}
		
	
	
	@Override public Object visitUnaryOpExpr(PLIModelParser.UnaryOpExprContext ctx) { 
		Object r = this.visit(ctx.right);	
		Preconditions.checkArgument(AuxGrammar.isInteger(r) | AuxGrammar.isDouble(r),
				String.format("La variable debe ser int o double"));
			switch (ctx.op.getText()) {
			case "(int)": r = AuxGrammar.castInteger(r);break;
			case "(double)": r = AuxGrammar.castDouble(r); break;
			case "+":  break;
			case "-": r = AuxGrammar.minus(r); break;
			case "!": r = ! AuxGrammar.asBoolean(r); break;
			} 
		return r;  
	}
	
	
	@Override
	public Object visitConstraints(PLIModelParser.ConstraintsContext ctx) {
		Function<Integer,String> cn = i->AuxGrammar.constraintName(i);
		Integer n = ctx.list().size();
		Function<Integer,List<String>> cs = i->AuxGrammar.asListString(visit(ctx.list(i))); 
		Function<Integer,Stream<String>> sc = 
					i ->Stream2.enumerate(cs.apply(i).stream().filter(ls->!ls.isEmpty()))
				      .map(p->String.format("%s%d: %s",cn.apply(i),p.counter(),p.value()));
		String lt1 = IntStream.range(0,n).boxed()
				.flatMap(i ->sc.apply(i))
				.collect(Collectors.joining("\n"));
		Integer m = AuxGrammar.constraints.size();
		String lt2 = IntStream.range(0,m).boxed()
				.map(i ->String.format("z%d: %s", i, AuxGrammar.constraints.get(i)))
				.collect(Collectors.joining("\n","\n",""));
		return lt1+lt2;
	}
	
	@Override public Object visitDifferentValueConstraint(PLIModelParser.DifferentValueConstraintContext ctx) { 
		String left = AuxGrammar.asString(visit(ctx.left));
		String right = AuxGrammar.asString(visit(ctx.right));
		differentValue(left,right);
		return "";
	}
	
	@Override public Object visitC_list(PLIModelParser.C_listContext ctx) { 
		Integer n = ctx.indx().size();
		List<Limits> limites = new ArrayList<>(); 
		List<String> indexNames = new ArrayList<>();
		for(int i = 0;i<n;i++) {
			String name = ctx.indx(i).index_name.getText();
			indexNames.add(name);
			Integer li = AuxGrammar.asInteger(visit(ctx.indx(i).li));
			Integer ls = AuxGrammar.asInteger(visit(ctx.indx(i).ls));
			Limits lm = Limits.of(li, ls);
			limites.add(lm);
		}
		List<String> r = new ArrayList<>();
		switch(n) {
		case 0:
			String s = AuxGrammar.asString(visit(ctx.indexed_elem()));
			r.add(s);
			break;
		case 1: 
			for(int i = limites.get(0).li;i<limites.get(0).ls;i++) {
				AuxGrammar.values.put(indexNames.get(0),i);				
				if(ctx.exp()==null || AuxGrammar.asBoolean(visit(ctx.exp()))) {
					s = AuxGrammar.asString(visit(ctx.indexed_elem()));
					r.add(s);	
				}
			}
			break;
		case 2: 
			for(int i = limites.get(0).li;i<limites.get(0).ls;i++) {			
				AuxGrammar.values.put(indexNames.get(0),i);
				for(int j = limites.get(1).li;j<limites.get(1).ls;j++) {
					AuxGrammar.values.put(indexNames.get(1),j);
					if(ctx.exp()==null || AuxGrammar.asBoolean(visit(ctx.exp()))) {
						s = AuxGrammar.asString(visit(ctx.indexed_elem()));
						r.add(s);
					}
				}
			}
			break;
		case 3: 
			for(int i = limites.get(0).li;i<limites.get(0).ls;i++) {			
				AuxGrammar.values.put(indexNames.get(0),i);
				for(int j = limites.get(1).li;j<limites.get(1).ls;j++) {
					AuxGrammar.values.put(indexNames.get(1),j);
					for(int k = limites.get(2).li;k<limites.get(2).ls;k++) {
						AuxGrammar.values.put(indexNames.get(2),k);						
						if(ctx.exp()==null || AuxGrammar.asBoolean(visit(ctx.exp()))) {
							s = AuxGrammar.asString(visit(ctx.indexed_elem()));
							r.add(s);
						}
					}
				}
			}
			break;
		case 4:
			for (int i = limites.get(0).li; i < limites.get(0).ls; i++) {
				AuxGrammar.values.put(indexNames.get(0), i);
				for (int j = limites.get(1).li; j < limites.get(1).ls; j++) {
					AuxGrammar.values.put(indexNames.get(1), j);
					for (int k = limites.get(2).li; k < limites.get(2).ls; k++) {
						AuxGrammar.values.put(indexNames.get(2), k);
						for (int l = limites.get(3).li; l < limites.get(3).ls; l++) {
							AuxGrammar.values.put(indexNames.get(3), l);
							if (ctx.exp() == null || AuxGrammar.asBoolean(visit(ctx.exp()))) {
								s = AuxGrammar.asString(visit(ctx.indexed_elem()));
								r.add(s);
							}
						}
					}
				}
			}
			break;
		default: 
		}
		return ListString.of(r);
	}
	
	@Override public Object visitExps(PLIModelParser.ExpsContext ctx) { 
		List<String> r = new ArrayList<>();
		Integer n = ctx.exp().size();
		for(int i = 0; i<n;i++) {
			String e = visit(ctx.exp(i)).toString();
			r.add(e);
		}
		return ListString.of(r);
	}
	
	@Override public Object visitVar_ids(PLIModelParser.Var_idsContext ctx) { 
		List<String> r = new ArrayList<>();
		Integer n = ctx.var_id().size();
		for(int i = 0; i<n;i++) {
			String e = visit(ctx.var_id(i)).toString();
			r.add(e);
		}
		return ListString.of(r);
	}
	
	@Override public Object visitGenerate_exps(PLIModelParser.Generate_expsContext ctx) {  
		List<String> r = new ArrayList<>();
		Integer n = ctx.generate_exp().size();
		for(int i = 0; i<n;i++) {
			String e = visit(ctx.generate_exp(i)).toString();
			r.add(e);
		}
		return ListString.of(r);
	}
	
	@Override public Object visitIndx(PLIModelParser.IndxContext ctx) { 
		return visitChildren(ctx); 
	}
	
	
	@Override public Object visitIndexed_elem(PLIModelParser.Indexed_elemContext ctx) { 
		return visitChildren(ctx); 
	}
	
	
	@Override public Object visitAllDifferentValuesConstraint(PLIModelParser.AllDifferentValuesConstraintContext ctx) { 
		List<String>  ls = AuxGrammar.asListString(visit(ctx.list()));
		Integer n = ls.size();		
		for(int i = 0; i< n;i++) {
			for(int j=i+1; j<n;j++) {
				differentValue(ls.get(i),ls.get(j));
			}
		}
		return "";
	}
	
	
	@Override public Object visitAtomConstraint(PLIModelParser.AtomConstraintContext ctx) { 
		String ge = AuxGrammar.asString(visit(ctx.generate_exp()));
		String op = ctx.rel_op().getText();
		String e = visit(ctx.exp()).toString();
		String r = String.format("%s %s %s",ge,op,e);
		return r;
	}
	
	
	@Override public Object visitIndicatorConstraint(PLIModelParser.IndicatorConstraintContext ctx) { 
		String ge = AuxGrammar.asString(visit(ctx.var_id()));
		String n = ctx.values.getText();
		Preconditions.checkArgument(n.equals("0") | n.equals("1"),String.format("El valor debe ser 0 0 1 y es %s",n));
		String c = AuxGrammar.asString(visit(ctx.constraint()));
		return String.format("%s = %s -> %s",ge,n,c);
	}
	
	
	@Override public Object visitFactorGenerateExp(PLIModelParser.FactorGenerateExpContext ctx) { 
		Integer n = ctx.s_factor().size();
		String factor = AuxGrammar.asString(visit(ctx.factor()));
		String r =  IntStream.range(0,n).boxed()
				.map(i->AuxGrammar.asString(visit(ctx.s_factor(i))))
				.collect(Collectors.joining(""));	
		r = String.format("%s%s",factor,r);
		return r;
	}
	

	@Override public Object visitSumGenerateExp(PLIModelParser.SumGenerateExpContext ctx) { 
		List<String> ls = AuxGrammar.asListString(visit(ctx.list()));
		String r1 = ls.stream().collect(Collectors.joining(" + "));
		Integer n = ctx.s_factor().size();
		String r2 =  IntStream.range(0,n).boxed()
				.map(i->AuxGrammar.asString(visit(ctx.s_factor(i))))
				.collect(Collectors.joining(""));	
		r2 = String.format("%s%s",r1,r2);
		return r2;
	}
	
	
	@Override public Object visitTwoSideBound(PLIModelParser.TwoSideBoundContext ctx) { 
		String name = AuxGrammar.asString(this.visit(ctx.name));		
		Object li =  this.visit(ctx.li); 
		Object ls =  this.visit(ctx.ls); 
		return String.format("%s <= %s <= %s",li.toString(),name,ls.toString());
	}
	

	@Override public Object visitVarFactor(PLIModelParser.VarFactorContext ctx) {
		String id = AuxGrammar.asString(visit(ctx.var_id()));
		String c = String.format("%s",visit(ctx.exp()).toString());
		return String.format("%s %s",c,id);
	}
	
	@Override public Object visitVarIdFactor(PLIModelParser.VarIdFactorContext ctx) {
		return AuxGrammar.asString(visit(ctx.var_id()));
	}
	
	@Override public Object visitExpFactor(PLIModelParser.ExpFactorContext ctx) {
		return String.format("%s",visit(ctx.exp()).toString());
	}
	
	
	@Override
	public Object visitVar_id(PLIModelParser.Var_idContext ctx) {
		String name = ctx.name.getText();
		String s;
		if(ctx.index_var_id() == null) s = "";
		else s = AuxGrammar.asString(visit(ctx.index_var_id()));
		return name+s;
	}
	
	
	@Override public Object visitIndex_var_id(PLIModelParser.Index_var_idContext ctx) {
		Integer n = ctx.exp().size();
		String r;
		if(n == 0) {
				r = "";
				String2.toConsole("n = %s",n);
		}else {
			r = IntStream.range(0,n).boxed()
				.map(i->AuxGrammar.asInteger(visit(ctx.exp(i))))
				.map(e->e.toString())
				.collect(Collectors.joining("_","_",""));
		}
		return r;
	}
	

	@Override public Object visitCall_function(PLIModelParser.Call_functionContext ctx) { 
		Integer n = 0;
		if(ctx.exps() != null) n = ctx.exps().exp().size();
		String name = ctx.name.getText();
		Object parameters[] = IntStream.range(0,n).boxed()
				.map(i->visit(ctx.exps().exp(i)))
				.collect(Collectors.toList())
				.toArray(new Object[n]);
//		System.out.println(String.format("En Visit Call %s,%d,%s",name,parameters.length,AuxGrammar.toString(parameters)));		
		return AuxGrammar.result(name, parameters);	
	}
	
	
	@Override public Object visitBounds(PLIModelParser.BoundsContext ctx) { 
		Integer n = ctx.list().size();
		Function<Integer,List<String>> cs = i->AuxGrammar.asListString(visit(ctx.list(i))); 
		Function<Integer,Stream<String>> sc = i ->cs.apply(i).stream()
				      .map(p->String.format("%s",p));
		String lt1 = IntStream.range(0,n).boxed()
				.flatMap(i ->sc.apply(i))
				.collect(Collectors.joining("\n"));
		Integer m = AuxGrammar.bounds.size();
		String lt2 = IntStream.range(0,m).boxed()
				.map(i ->String.format("%s",AuxGrammar.bounds.get(i)))
				.collect(Collectors.joining("\n","\n",""));
		return lt1+lt2;
	}
	
	
	@Override public Object visitIntExpr(PLIModelParser.IntExprContext ctx) { 
		return Integer.parseInt(ctx.getText());
    }
	
	
	@Override public Object visitOneSideBound(PLIModelParser.OneSideBoundContext ctx) { 
		String name = AuxGrammar.asString(this.visit(ctx.name));
		String op = ctx.op.getText();		
		Object val =  this.visit(ctx.exp()); 
		return String.format("%s %s %s",name,op,val.toString());
	}
	
	@Override
	public Object visitOpExpr(PLIModelParser.OpExprContext ctx) {	
		String op = ctx.op.getText();
		Object left = this.visit(ctx.left);
		Preconditions.checkNotNull(left,String.format("%s",ctx.left));
		if(op.equals("&&") && !AuxGrammar.asBoolean(left)) return false;
		if(op.equals("||") && AuxGrammar.asBoolean(left)) return true;
		Object right = this.visit(ctx.right);
		Preconditions.checkNotNull(right,String.format("%s",ctx.right));
		
		Preconditions.checkArgument(
				(AuxGrammar.isInteger(left) || AuxGrammar.isDouble(left) ||  AuxGrammar.isBoolean(left))
						&& (AuxGrammar.isInteger(right) || AuxGrammar.isDouble(right) || AuxGrammar.isBoolean(right)),
				String.format("Los operandos deben ser num�ricos"));
		Object r = null;
		switch (op) {
		case "%": r = AuxGrammar.rest(left,right); break;
		case "*": r = AuxGrammar.multiply(left,right); break;
		case "/": r = AuxGrammar.div(left,right);; break;
		case "+": r = AuxGrammar.sum(left,right);; break;
		case "-": r = AuxGrammar.difference(left,right); break;
		case "<=": r = AuxGrammar.le(left,right); break;
        case "<": r = AuxGrammar.lt(left,right); break;
        case ">=": r = AuxGrammar.ge(left,right); break;
        case ">": r = AuxGrammar.gt(left,right); break;
        case "=": r = AuxGrammar.eq(left,right); break;
        case "!=": r = AuxGrammar.ne(left,right); break;
        case "&&": r = AuxGrammar.and(left,right); break;
        case "||": r = AuxGrammar.or(left,right); break;
		default: Preconditions.checkArgument(false, String.format("Operator %s desconocido", op));
		}		
		return r;
	}
	
	
	
	@Override public Object visitOrConstraint(PLIModelParser.OrConstraintContext ctx) { 
		Integer n = Integer.parseInt(ctx.n.getText());
		Integer nc = ctx.constraint().size();
		Preconditions.checkArgument(n>0 && n<nc,String.format("El parametro n no cumple las restricciones y es % d",n));
		Function<Integer,String> cs = i->AuxGrammar.asString(visit(ctx.constraint(i))); 
		List<String> lt = IntStream.range(0,nc).boxed().map(cs).collect(Collectors.toList());
		orConstraint(ctx.rel_op().getText(),n,lt);
		return "";
	}
	
	
	
	@Override public Object visitFunExpr(PLIModelParser.FunExprContext ctx) { return visitChildren(ctx); }
	
	@Override public Object visitParenExpr(PLIModelParser.ParenExprContext ctx) { 
		return this.visit(ctx.exp());
    }
	
	
	@Override public Object visitPlusFactor(PLIModelParser.PlusFactorContext ctx) { 
//		String2.toConsole("Plus Factor factor = %s",ctx.factor());
		String r = String.format(" + %s",AuxGrammar.asString(visit(ctx.factor())));
		return r;
	}
	
	
	@Override public Object visitPlusSum(PLIModelParser.PlusSumContext ctx) { 
		List<String> ls = AuxGrammar.asListString(visit(ctx.list()));
		String r = ls.stream().collect(Collectors.joining(" + "));
		return String.format(" + %s",r);
	}
	
	
	
	@Override public Object visitMinusSum(PLIModelParser.MinusSumContext ctx) { 
		List<String> ls = AuxGrammar.asListString(visit(ctx.list()));
		String r = ls.stream().collect(Collectors.joining(" - "));
		return String.format(" - %s",r);
	}
	
	
	@Override public Object visitMinusFactor(PLIModelParser.MinusFactorContext ctx) { 
		String r = String.format(" - %s",AuxGrammar.asString(visit(ctx.factor())));
		return r;
	}
	
	
	@Override public Object visitDoubleExp(PLIModelParser.DoubleExpContext ctx) { 
		return Double.parseDouble(ctx.getText());
	}
	
	@Override public Object visitIdExpr(PLIModelParser.IdExprContext ctx) {
		Preconditions.checkArgument(AuxGrammar.values.keySet().contains(ctx.getText()), 
				String.format("Variable no declarada %s",ctx.getText()));
	    return AuxGrammar.values.get(ctx.getText());
	}
	
	
	@Override public Object visitImplyConstraint(PLIModelParser.ImplyConstraintContext ctx) { 
		String left = AuxGrammar.asString(visit(ctx.left));
		String right = AuxGrammar.asString(visit(ctx.right));
		Integer r = AuxGrammar.nBinarys;
		String s1 = String.format("x$_%d = 1 -> %s",r,left);
		String s2 = String.format("x$_%d = 1 -> %s",r+1,right);
		String s3 = String.format("x$_%d - x$_%d  <=  0",r,r+1);
		AuxGrammar.nBinarys +=2;
		List<String> ls = List.of(s1,s2,s3);
		AuxGrammar.constraints.addAll(ls);
		return "";
	}
	
	
	
	@Override public Object visitBin_vars(PLIModelParser.Bin_varsContext ctx) { 
		Integer n = ctx.list().size();
		Function<Integer,List<String>> cs = i->AuxGrammar.asListString(visit(ctx.list(i))); 
		Function<Integer,Stream<String>> sc = i ->cs.apply(i).stream().map(p->String.format("%s",p));
		String lt1 = IntStream.range(0,n).boxed()
				.flatMap(i ->sc.apply(i))
				.collect(Collectors.joining(" "));
		return lt1;
	}
	
	
	
	
	@Override public Object visitInt_vars(PLIModelParser.Int_varsContext ctx) { 
		Integer n = ctx.list().size();
		Function<Integer,List<String>> cs = i->AuxGrammar.asListString(visit(ctx.list(i))); 
		Function<Integer,Stream<String>> sc = i ->cs.apply(i).stream().map(p->String.format("%s",p));
		String lt1 = IntStream.range(0,n).boxed()
				.flatMap(i ->sc.apply(i))
				.collect(Collectors.joining(" "));
		return lt1;
	}
	
	
	@Override public Object visitFree_vars(PLIModelParser.Free_varsContext ctx) { 
		Integer n = ctx.list().size();
		Function<Integer,List<String>> cs = i->AuxGrammar.asListString(visit(ctx.list(i))); 
		Function<Integer,Stream<String>> sc = i ->cs.apply(i).stream().map(p->String.format("%s",p));
		String lt1 = IntStream.range(0,n).boxed()
				.flatMap(i ->sc.apply(i))
				.map(v->String.format("%s free",v))
				.collect(Collectors.joining("\n"));
		return String.format("%s",lt1);
	}
	
	@Override public Object visitRel_op(PLIModelParser.Rel_opContext ctx) { return visitChildren(ctx); }
		
	@Override public Object visitMaxConstraint(PLIModelParser.MaxConstraintContext ctx) { 
		String resultant = AuxGrammar.asString(visit(ctx.left));
		String p = String.format("%s = MAX ( " ,resultant);
		List<String> param = AuxGrammar.asListString(visit(ctx.list())); //AuxGrammar.asListString(visit(ctx.list()));
		String s = IntStream.range(0,param.size()).boxed()
				.map(i->param.get(i))
				.collect(Collectors.joining(" , ",p," )"));
		AuxGrammar.generalConstraints.add(s);
		return "";
	}
	
	@Override public Object visitMinConstraint(PLIModelParser.MinConstraintContext ctx) { 
		String resultant =  AuxGrammar.asString(visit(ctx.left));
		String p = String.format("%s = MIN ( " ,resultant);
		List<String> param = AuxGrammar.asListString(visit(ctx.list())); //AuxGrammar.asListString(visit(ctx.list()));
		String s = IntStream.range(0,param.size()).boxed()
				.map(i->param.get(i))
				.collect(Collectors.joining(" , ",p," )"));
		AuxGrammar.generalConstraints.add(s);
		return "";
	}
	
	@Override public Object visitOrBinConstraint(PLIModelParser.OrBinConstraintContext ctx) { 
		String resultant =  AuxGrammar.asString(visit(ctx.left));
		String p = String.format("%s = OR ( " ,resultant);
		List<String> param = AuxGrammar.asListString(visit(ctx.list())); //AuxGrammar.asListString(visit(ctx.list()));
		String s = IntStream.range(0,param.size()).boxed()
				.map(i->param.get(i))
				.collect(Collectors.joining(" , ",p," )"));
		AuxGrammar.generalConstraints.add(s);
		return "";
	}
	
	@Override public Object visitAndBinConstraint(PLIModelParser.AndBinConstraintContext ctx) { 
		String resultant =  AuxGrammar.asString(visit(ctx.left));
		String p = String.format("%s = AND ( " ,resultant);
		List<String> param = AuxGrammar.asListString(visit(ctx.list())); //AuxGrammar.asListString(visit(ctx.list()));
		String s = IntStream.range(0,param.size()).boxed()
				.map(i->param.get(i))
				.collect(Collectors.joining(" , ",p," )"));
		AuxGrammar.generalConstraints.add(s);
		return "";
	}
	
	
	
	@Override public Object visitAllInValuesConstraint(PLIModelParser.AllInValuesConstraintContext ctx) { 
		List<String>  vars = AuxGrammar.asListString(visit(ctx.vars));
		List<String>  values = AuxGrammar.asListString(visit(ctx.values));
		Integer nVars = vars.size();
		Integer nValues = values.size();
		Integer k = AuxGrammar.nDBinarys;
		Integer r = AuxGrammar.nFrees;
		Preconditions.checkArgument(nVars<=nValues,
				String.format("El numero de variables debe ser menor o igual al de valores y son nVars=%d, nValues=%d",nVars,nValues));		
		for(int i = 0; i<nVars;i++) {
			Pair<String,Integer> p = reorderGenExp(vars.get(i));
			String v = negative(p.first());
			String vFree = String.format("y$%d",r+i);
			AuxGrammar.bounds.add(String.format("%s free",vFree));
			String c = String.format(String.format("%s + %s = %d",v,vFree,-p.second()));
			AuxGrammar.constraints.add(c);
			String factor_bin = String.format("%s x$_%d_%d",values.get(0).toString(),k+i,0);
			for(int j =1; j<nValues;j++) {
				factor_bin = factor_bin+" "+ String.format(" + %s x$_%d_%d",values.get(j).toString(),k+i,j);
			}
			String c1 = String.format("- %s + %s = 0",vFree,factor_bin);
			AuxGrammar.constraints.add(c1);
		}
		
		for(int i = 0; i<nValues;i++) {
			String bin = String.format("x$_%d_%d",k+i,0);
			AuxGrammar.dBinaries.add(String.format("x$_%d_%d",k+i,0));
			for(int j = 1; j<nValues;j++) {
				AuxGrammar.dBinaries.add(String.format("x$_%d_%d",k+i,j));
				bin = bin+" "+ String.format(" + x$_%d_%d",k+i,j);
			}
			String c2 = String.format("%s = 1",bin);
			AuxGrammar.constraints.add(c2);
		}
		for(int j = 0; j<nValues;j++) {
			String bin = String.format("x$_%d_%d",k+0,j);
			for(int i = 1; i<nValues;i++) {
				bin = bin+" "+ String.format(" + x$_%d_%d",k+i,j);
			}
			String c2 = String.format("%s = 1",bin);
			AuxGrammar.constraints.add(c2);
		}
		AuxGrammar.nFrees += nVars;
		AuxGrammar.nDBinarys += nValues;		
		return ""; 
	}
	
	@Override public Object visitEqualsConstraint(PLIModelParser.EqualsConstraintContext ctx) { 
		List<String>  left = AuxGrammar.asListString(visit(ctx.left));
		List<String>  right = AuxGrammar.asListString(visit(ctx.right));
		Integer n = left.size();
		Integer m = right.size();
		Preconditions.checkArgument(n == m, 
				String.format("La parte derecha e izquierda deben ser del mismo tama�o y sus tama�o es left =%d, right=%d", n,m));
		for (int i = 0; i < n; i++) {
			Pair<String, Integer> pLeft = reorderGenExp(left.get(i));
			Pair<String, Integer> pRight = reorderGenExp(right.get(i));
			String neg = negative(pLeft.first());
//			String2.toConsole("%s, %s, %s", pLeft, pRight, neg);
			String c;
			if (pRight.first().trim() == "") c = String.format("%s = %d", neg, pRight.second() - pLeft.second());			
			else c = String.format("%s + %s = %d", neg, pRight.first(), pRight.second() - pLeft.second());
			AuxGrammar.constraints.add(c);
		}
		return "";
	}
	
	@Override public Object visitValueInValuesConstraint(PLIModelParser.ValueInValuesConstraintContext ctx) { 
		String  var_id = AuxGrammar.asString(visit(ctx.var_id()));
		List<String>  values = AuxGrammar.asListString(visit(ctx.values));
		Integer nValues = values.size();
		Integer nb = AuxGrammar.nBinarys;
		String factor_bin = String.format("- %s + %s x$_%d",var_id,values.get(0),nb);
		String bin = String.format("x$_%d",nb);
		for(int j = 1; j<nValues;j++) {
			bin += " "+ String.format(" + x$_%d",nb+j);
			factor_bin += String.format(" + %s x$_%d",values.get(j),nb+j); 
		}
		String c1 = String.format("%s = 0",factor_bin);
		String c2 = String.format("%s = 1",bin);
		AuxGrammar.constraints.add(c1);
		AuxGrammar.constraints.add(c2);
		AuxGrammar.nBinarys += nValues;
		return "";
	}
	
	@Override public Object visitAbsConstraint(PLIModelParser.AbsConstraintContext ctx) { 
		String resultant =  AuxGrammar.asString(visit(ctx.left));
		String right =  AuxGrammar.asString(visit(ctx.right));
		Integer nf = AuxGrammar.nFrees;
		String var_id = String.format("y$%d",nf);
		AuxGrammar.bounds.add(String.format("%s free",var_id));
		String r = String.format("%s = ABS ( %s )" ,resultant,var_id);
		Pair<String,Integer> p = reorderGenExp(right);
		String c;
		if(p.first().trim() == "") c = String.format("- %s = %d,",var_id,p.second());
		else c = String.format("- %s + %s = %d",var_id,p.first(),p.second());
		AuxGrammar.constraints.add(c);
		AuxGrammar.generalConstraints.add(r);
		AuxGrammar.nFrees++;
		return "";
	}
	
	@Override public Object visitPair(PLIModelParser.PairContext ctx) { 
		String p1 = ctx.INT(0).getText();
		String p2 = ctx.INT(1).getText();
		String r = String.format("( %s , %s )", p1,p2);
		return r;
	}
	
	@Override public Object visitPiecewiseConstraint(PLIModelParser.PiecewiseConstraintContext ctx) { 
		String resultant =  AuxGrammar.asString(visit(ctx.left));
		String right =  AuxGrammar.asString(visit(ctx.right));
		Integer n = ctx.pair().size();
		String s = IntStream.range(0, n).boxed()
				.map(i->AuxGrammar.asString(visit(ctx.pair(i))))
				.collect(Collectors.joining(" "));
		String r = String.format("%s = PWL ( %s ) : %s" ,resultant,right,s);
		AuxGrammar.generalConstraints.add(r);
		return "";
	}
	
	@Override public Object visitSemi_continuous_vars(PLIModelParser.Semi_continuous_varsContext ctx) { 
		Integer n = ctx.list().size();
		Function<Integer,List<String>> cs = i->AuxGrammar.asListString(visit(ctx.list(i))); 
		Function<Integer,Stream<String>> sc = i ->cs.apply(i).stream().map(p->String.format("%s",p));
		String lt1 = IntStream.range(0,n).boxed()
				.flatMap(i ->sc.apply(i))
				.collect(Collectors.joining(" "));
		return lt1; 
	}
}
