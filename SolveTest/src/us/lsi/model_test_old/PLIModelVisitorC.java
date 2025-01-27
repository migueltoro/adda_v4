package us.lsi.model_test_old;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.antlr.v4.runtime.misc.NotNull;

import us.lsi.common.Preconditions;
import us.lsi.solve.AuxGrammar;
import us.lsi.solve.AuxGrammar.Limits;
import us.lsi.solve.AuxGrammar.ListString;
import us.lsi.solve.AuxGrammar.Type;
import us.lsi.streams.Stream2;


@SuppressWarnings("deprecation")
public class PLIModelVisitorC extends PLIModelBaseVisitor<Object>{
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
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
		String bounds = "";
	    if(ctx.bounds()!=null) bounds = String.format("\nBounds\n\n%s\n",AuxGrammar.asString(visit(ctx.bounds())));
	    if(ctx.free_vars()!=null) bounds += AuxGrammar.asString(visit(ctx.free_vars()));
	    String ints = implicitInts();	    
	    if(ctx.int_vars()!=null) ints += AuxGrammar.asString(visit(ctx.int_vars()));
	    ints = AuxGrammar.allSpaces(ints)?ints:String.format("\nGeneral\n\n%s\n",ints);
	    String bins = implicitBins();
	    if(ctx.bin_vars()!=null) bins += AuxGrammar.asString(visit(ctx.bin_vars()));
	    bins = AuxGrammar.allSpaces(bins)? bins:String.format("\nBinary\n\n%s\n",bins);
		return String.format("%s\n%s\n%s\n%s\n%s\nEnd",goal,constraints,bounds,bins,ints);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitHead(PLIModelParser.HeadContext ctx) { 
		return visitChildren(ctx); 
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
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
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	    
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
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
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
		
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
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
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public Object visitConstraints(PLIModelParser.ConstraintsContext ctx) {
		Function<Integer,String> cn = i->AuxGrammar.constraintName(i);
		Integer n = ctx.list().size();
		Function<Integer,List<String>> cs = i->AuxGrammar.asListString(visit(ctx.list(i))); 
		Function<Integer,Stream<String>> sc = i ->Stream2.enumerate(cs.apply(i).stream().filter(ls->!ls.isEmpty()))
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
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	private void differentValue(String v1, String v2) {
		String s1 = String.format("%s - %s >= 1",v1,v2);
		String s2 = String.format("%s - %s >= 1",v2,v1);
		this.orConstraint("=",1,List.of(s1,s2));	
	}
	
	@Override public Object visitDifferentValueConstraint(PLIModelParser.DifferentValueConstraintContext ctx) { 
		String left = AuxGrammar.asString(visit(ctx.left));
		String right = AuxGrammar.asString(visit(ctx.right));
		differentValue(left,right);
		return "";
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitList(PLIModelParser.ListContext ctx) { 
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
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitIndx(PLIModelParser.IndxContext ctx) { 
		return visitChildren(ctx); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitIndexed_elem(PLIModelParser.Indexed_elemContext ctx) { 
		return visitChildren(ctx); 
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitAllDifferentValuesConstraint(PLIModelParser.AllDifferentValuesConstraintContext ctx) { 
		List<String>  ls = AuxGrammar.asListString(visit(ctx.list()));
		Integer n = ls.size();		
		Integer r = AuxGrammar.nContinous;
		String s ;
		for(int i = 0; i<n; i++) {
			String p[] = AuxGrammar.partes(ls.get(i));
			s = String.format("%s - y$%d = %s",p[0],r+i,p[1]);
			AuxGrammar.constraints.add(s);
		}
		for(int i = 0; i<n; i++) {
			s = String.format("y$%d free",r+i);
			AuxGrammar.bounds.add(s);
		}
		AuxGrammar.nContinous += n;
		for(int i = 0; i<n; i++) {
			for(int j = i+1; j<n; j++) {				
				this.differentValue(String.format("y$%d",r+i),String.format("y$%d",r+j));
			}
		}
		return "";
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitAtomConstraint(PLIModelParser.AtomConstraintContext ctx) { 
		String ge = AuxGrammar.asString(visit(ctx.generate_exp()));
		String op = ctx.rel_op().getText();
		String e = visit(ctx.exp()).toString();
		String r = String.format("%s %s %s",ge,op,e);
		return r;
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitIndicatorConstraint(PLIModelParser.IndicatorConstraintContext ctx) { 
		String ge = AuxGrammar.asString(visit(ctx.var_id()));
		String c = AuxGrammar.asString(visit(ctx.constraint()));
		return String.format("%s = 1 -> %s",ge,c);
	}
	
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitFactorGenerateExp(PLIModelParser.FactorGenerateExpContext ctx) { 
		Integer n = ctx.s_factor().size();
		String factor = AuxGrammar.asString(visit(ctx.factor()));
		String r =  IntStream.range(0,n).boxed()
				.map(i->AuxGrammar.asString(visit(ctx.s_factor(i))))
				.collect(Collectors.joining(""));	
		r = String.format("%s%s",factor,r);
		return r;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
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
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitTwoSideBound(PLIModelParser.TwoSideBoundContext ctx) { 
		String name = AuxGrammar.asString(this.visit(ctx.name));		
		Object li =  this.visit(ctx.li); 
		Object ls =  this.visit(ctx.ls); 
		return String.format("%s <= %s <= %s",li.toString(),name,ls.toString());
	}
	
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitVarFactor(PLIModelParser.VarFactorContext ctx) {
		String e = AuxGrammar.asString(visit(ctx.var_id()));
		if(ctx.exp() != null) {
			Object c = visit(ctx.exp());
			e = String.format("%s %s",c.toString(),e);
		}
		return e;
	}
//	/**
//	 * {@inheritDoc}
//	 *
//	 * <p>The default implementation returns the result of calling
//	 * {@link #visitChildren} on {@code ctx}.</p>
//	 */
//	@Override public Object visitNumFactor(PLIModelParser.NumFactorContext ctx) { 
//		return visit(ctx.exp()).toString();	
//	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public Object visitVar_id(PLIModelParser.Var_idContext ctx) {
		String name = ctx.name.getText();
		List<String> ls = AuxGrammar.asListString(visit(ctx.index_var_id()));
		if (ls != null) {
			Integer n = ls.size();
			name = IntStream.range(0, n).boxed()
					.map(i -> ls.get(i))
					.collect(Collectors.joining("_",name+"_", ""));
		}
		return name;
	}
	
	
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#index_var_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	
	@Override public Object visitIndex_var_id(@NotNull PLIModelParser.Index_var_idContext ctx) {
		Integer n = ctx.exp().size();
		if(n == 0) return null;
		List<String>  r = IntStream.range(0,n).boxed()
				.map(i->AuxGrammar.asInteger(visit(ctx.exp(i))))
				.map(e->e.toString())
			    .collect(Collectors.toList());
		return ListString.of(r);
	}
	
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitCall_function(PLIModelParser.Call_functionContext ctx) { 
		Integer n = 0;
		if(ctx.real_parameters() != null) n = ctx.real_parameters().exp().size();
		String name = ctx.name.getText();
		Object parameters[] = IntStream.range(0,n).boxed()
				.map(i->visit(ctx.real_parameters().exp(i)))
				.collect(Collectors.toList())
				.toArray(new Object[n]);
//		System.out.println(String.format("En Visit Call %s,%d,%s",name,parameters.length,AuxGrammar.toString(parameters)));		
		return AuxGrammar.result(name, parameters);	
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
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
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitIntExpr(PLIModelParser.IntExprContext ctx) { 
		return Integer.parseInt(ctx.getText());
    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitOneSideBound(PLIModelParser.OneSideBoundContext ctx) { 
		String name = AuxGrammar.asString(this.visit(ctx.name));
		String op = ctx.op.getText();		
		Object val =  this.visit(ctx.exp()); 
		return String.format("%s %s %s",name,op,val.toString());
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
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
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	
	private void orConstraint(String op, Integer n, List<String> constraints) {
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
	
	@Override public Object visitOrConstraint(PLIModelParser.OrConstraintContext ctx) { 
		Integer n = Integer.parseInt(ctx.n.getText());
		Integer nc = ctx.constraint().size();
		Preconditions.checkArgument(n>0 && n<nc,String.format("El parametro n no cumple las restricciones y es % d",n));
		Function<Integer,String> cs = i->AuxGrammar.asString(visit(ctx.constraint(i))); 
		List<String> lt = IntStream.range(0,nc).boxed().map(cs).collect(Collectors.toList());
		orConstraint(ctx.rel_op().getText(),n,lt);
		return "";
	}
	
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitFunExpr(PLIModelParser.FunExprContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitParenExpr(PLIModelParser.ParenExprContext ctx) { 
		return this.visit(ctx.exp());
    }
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitPlusFactor(PLIModelParser.PlusFactorContext ctx) { 
		String r = String.format(" + %s",AuxGrammar.asString(visit(ctx.factor())));
		return r;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitPlusSum(PLIModelParser.PlusSumContext ctx) { 
		List<String> ls = AuxGrammar.asListString(visit(ctx.list()));
		String r = ls.stream().collect(Collectors.joining(" + "));
		return String.format(" + %s",r);
	}
	
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitMinusSum(PLIModelParser.MinusSumContext ctx) { 
		List<String> ls = AuxGrammar.asListString(visit(ctx.list()));
		String r = ls.stream().collect(Collectors.joining(" - "));
		return String.format(" - %s",r);
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitMinusFactor(PLIModelParser.MinusFactorContext ctx) { 
		String r = String.format(" - %s",AuxGrammar.asString(visit(ctx.factor())));
		return r;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitDoubleExp(PLIModelParser.DoubleExpContext ctx) { 
		return Double.parseDouble(ctx.getText());
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitIdExpr(PLIModelParser.IdExprContext ctx) {
		Preconditions.checkArgument(AuxGrammar.values.keySet().contains(ctx.getText()), 
				String.format("Variable no declarada %s",ctx.getText()));
	    return AuxGrammar.values.get(ctx.getText());
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
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
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	public static String implicitBins() {
		return IntStream.range(0,AuxGrammar.nBinarys).boxed()
				.map(i->String.format("x$_%d",i))
				.collect(Collectors.joining(" ","",""));
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
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	
	public static String implicitInts() {
		return IntStream.range(0,AuxGrammar.nInts).boxed()
				.map(i->String.format("y$_%d",i))
				.collect(Collectors.joining(" "," ",""));
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
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitFree_vars(PLIModelParser.Free_varsContext ctx) { 
		Integer n = ctx.list().size();
		Function<Integer,List<String>> cs = i->AuxGrammar.asListString(visit(ctx.list(i))); 
		Function<Integer,Stream<String>> sc = i ->cs.apply(i).stream().map(p->String.format("%s",p));
		String lt1 = IntStream.range(0,n).boxed()
				.flatMap(i ->sc.apply(i))
				.map(v->String.format("%s free",v))
				.collect(Collectors.joining("\n"));
//		String lt2 = IntStream.range(0,AuxGrammar.nFrees).boxed()
//				.map(i->String.format("z$_%d free",i))
//				.collect(Collectors.joining("\n","",""));
		return String.format("%s",lt1);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Object visitRel_op(PLIModelParser.Rel_opContext ctx) { return visitChildren(ctx); }
	
}
