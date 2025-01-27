package us.lsi.tiposrecursivos.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import us.lsi.tiposrecursivos.parsers.program.ProgramBaseVisitor;
import us.lsi.tiposrecursivos.parsers.program.ProgramParser;


public class AstVisitorC extends ProgramBaseVisitor<Object> {
	
	private static Integer nError = 0;
	
	public static void printError1(String format,Object...param) {
		String ft = "Error numero %d = "+format;
		System.out.println(String.format(ft,AstVisitorC.nError,param[0]));
		AstVisitorC.nError++;
	}
	
	public static void printError2(String format,Object...param) {
		String ft = "Error numero %d = "+format;
		System.out.println(String.format(ft,AstVisitorC.nError,param[0],param[1]));
		AstVisitorC.nError++;
	}
	
	public static List<Map<String,Object>> symbolTable = 
			new ArrayList<Map<String,Object>>();
	
	public static Boolean containsKey(String id) {
		Integer n = AstVisitorC.symbolTable.size();
		Boolean r = false;
		for(int i= n-1;i>=0;i--) {
			if(AstVisitorC.symbolTable.get(i).containsKey(id)) {
				r = true;
				break;
			}
		}
		return r;
	}
	
	public static Boolean containsKeyInBlock(String id) {
		Integer n = AstVisitorC.symbolTable.size();
		return AstVisitorC.symbolTable.get(n-1).containsKey(id);
	}
	
	public static Object get(String id) {
		Integer n = AstVisitorC.symbolTable.size();
		Object r = null;
		for(int i= n-1;i>=0;i--) {
			if(AstVisitorC.symbolTable.get(i).containsKey(id)) {
				r = AstVisitorC.symbolTable.get(i).get(id);
				break;
			}
		}
		return r;
	}
	
	public static Map<String,Object> table() {
		Integer n = AstVisitorC.symbolTable.size();
		Map<String,Object> m = new HashMap<>();
		for(int i= n-1;i>=0;i--) {			
			Map<String,Object> r = AstVisitorC.symbolTable.get(i);
			m.putAll(r);
		}
		return m;
	}
	
	
	public static void put(String id, Object val) {
		Integer n = AstVisitorC.symbolTable.size();
		Map<String,Object> m = AstVisitorC.symbolTable.get(n-1);
		m.put(id, val);
	}
	
	public static void addEmptyTable() {
		Map<String,Object> m = new HashMap<>();
		AstVisitorC.symbolTable.add(m);
	}
	
	public static void removeTable() {
		int n = symbolTable.size();
		AstVisitorC.symbolTable.remove(n-1);
	}
	
	

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@SuppressWarnings("unchecked")
	@Override 
	public Ast visitProgram(ProgramParser.ProgramContext ctx) { 
		AstVisitorC.addEmptyTable();
		List<ParamDeclaration> parameters = 
				(List<ParamDeclaration>) visit(ctx.formal_parameters());
		Block block = (Block) visit(ctx.block());
		Ast ast = Ast.of(parameters,block);
		AstVisitorC.removeTable();
		return ast;
	}
	
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Declaration visitFunDeclaration(ProgramParser.FunDeclarationContext ctx) { 
		AstVisitorC.addEmptyTable();
		String id = ctx.id.getText();
		Type type = Type.valueOf(ctx.type.getText());
		List<ParamDeclaration> parameters = 
				(List<ParamDeclaration>) visit(ctx.formal_parameters());
		Block block = (Block) visit(ctx.block());
		FunDeclaration d = FunDeclaration.of(id,type,parameters,block);
		AstVisitorC.removeTable();
		if(AstVisitorC.containsKey(id))
			AstVisitorC.printError1("La función %s ya ha sido declarada",id);
		AstVisitorC.put(id,d);
		return d;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override 
	public Block visitBlock(ProgramParser.BlockContext ctx) { 
		Integer nd = ctx.declaration().size();
		List<Declaration> dcs = IntStream.range(0, nd).boxed()
				.map(i->(Declaration)visit(ctx.declaration(i)))
				.collect(Collectors.toList());
		Integer ns = ctx.sentence().size();
		List<Sentence> sentences = IntStream.range(0,ns).boxed()
				.map(i->(Sentence)visit(ctx.sentence(i)))
				.collect(Collectors.toList());
		Block block = Block.of(dcs,sentences,AstVisitorC.table());
		return block;
	}
		
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public VarDeclaration visitVarDeclaration(ProgramParser.VarDeclarationContext ctx) { 
		String id = ctx.id.getText();
		Type type = Type.valueOf(ctx.type.getText());
		Exp exp = (Exp)visit(ctx.exp());
		Var r = Var.of(id,type,exp.value());
		if(AstVisitorC.containsKeyInBlock(id))
				AstVisitorC.printError1("La variable %s ya ha sido declarada",id);
		AstVisitorC.put(id,r);
		return VarDeclaration.of(r);
	}
	
	@Override public List<ParamDeclaration> visitFormal_parameters(ProgramParser.Formal_parametersContext ctx) { 
		Integer n = ctx.formal_parameter().size();
		List<ParamDeclaration> r = IntStream.range(0, n).boxed()
				.map(i->(ParamDeclaration)visit(ctx.formal_parameter(i)))
				.collect(Collectors.toList());
		return r; 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public ParamDeclaration visitFormal_parameter(ProgramParser.Formal_parameterContext ctx) { 
		String id = ctx.id.getText();
		Type type = Type.valueOf(ctx.type.getText());
		Var dv = Var.of(id, type);
		ParamDeclaration d = ParamDeclaration.of(dv);
		AstVisitorC.put(id,dv);
		return d;
    }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */	
	@Override 
	public Sentence visitAsignSentence(ProgramParser.AsignSentenceContext ctx) { 
		String idText =ctx.id.getText();
		if(!AstVisitorC.containsKey(idText)) 
			AstVisitorC.printError1("La variable %s no ha sido declarada",idText);
		Var id = (Var)AstVisitorC.get(idText);
		Exp exp = (Exp) visit(ctx.exp());
		return Assign.of(id,exp); 
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Sentence visitIfSentence(ProgramParser.IfSentenceContext ctx) { 
		Exp guard = (Exp) visit(ctx.exp());
		AstVisitorC.addEmptyTable();
		Block trueBlock = (Block) visit(ctx.trueBlock);
		AstVisitorC.removeTable();
		Block elseBlock = null;
		if(ctx.elseBlock != null)  {
				AstVisitorC.addEmptyTable();
				elseBlock = (Block) visit(ctx.elseBlock);
				AstVisitorC.removeTable();
		}
		return IfThenElse.of(guard,trueBlock,elseBlock);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override 
	public Sentence visitWhileSentence(ProgramParser.WhileSentenceContext ctx) { 		
		Exp guard = (Exp) visit(ctx.exp());
		AstVisitorC.addEmptyTable();
		Block block = (Block) visit(ctx.block());
		AstVisitorC.removeTable();
		return While.of(guard,block); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override 
	public Exp visitUnaryExpr(ProgramParser.UnaryExprContext ctx) { 
		String op = ctx.op.getText();
		Exp operand = (Exp) visit(ctx.right);
		return Unary.of(operand,op); 
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Exp visitIntExpr(ProgramParser.IntExprContext ctx) { 
		return Const.of(Integer.parseInt(ctx.getText()),Type.Int); 
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Exp visitBinaryExpr(ProgramParser.BinaryExprContext ctx) { 
		String op = ctx.op.getText();
		Exp left = (Exp) visit(ctx.left);
		Exp right = (Exp) visit(ctx.right);
		return Binary.of(left,right,op);
	}
	
	@Override public Exp visitTernaryExpr(ProgramParser.TernaryExprContext ctx) { 
		Exp guard = (Exp) visit(ctx.guard);
		Exp left = (Exp) visit(ctx.left);
		Exp right = (Exp) visit(ctx.right);		
		return Ternary.of(guard,left,right,"IfExp"); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@SuppressWarnings("unchecked")
	@Override public Exp visitCallExpr(ProgramParser.CallExprContext ctx) { 
		String id = ctx.ID().getText();
		List<Exp> parameters = (List<Exp>) visit(ctx.real_parameters());
		if(!AstVisitorC.containsKey(id)) 
			AstVisitorC.printError1("La función %s no ha sido declarada",id);
		FunDeclaration d = (FunDeclaration)AstVisitorC.get(id);
		return CallFunction.of(id,parameters,d);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override 
	public Exp visitParenExpr(ProgramParser.ParenExprContext ctx) { 
		return (Exp) visit(ctx.exp());
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Exp visitDoubleExp(ProgramParser.DoubleExpContext ctx) { 
		return Const.of(Double.parseDouble(ctx.getText()),Type.Double); 
	}
	
	@Override public Exp visitStrExpr(ProgramParser.StrExprContext ctx) { 
		String text = ctx.getText();
		text = text.substring(1,text.length()-1);
		return Const.of(text,Type.String); 
	}
	
	
	@Override public Exp visitBoolExpr(ProgramParser.BoolExprContext ctx) { 
		return Const.of(ctx.getText().equals("true")?true:false,Type.Boolean); 
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override 
	public Var visitIdExpr(ProgramParser.IdExprContext ctx) { 
		String idText = ctx.id.getText();
		if(!AstVisitorC.containsKey(idText))
			AstVisitorC.printError1("La variable %s no ha sido declarada",idText);
		Var id = (Var)AstVisitorC.get(idText);
		return id;
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public List<Exp> visitReal_parameters(ProgramParser.Real_parametersContext ctx) { 
		Integer n = ctx.exp().size();
		List<Exp> parameters = IntStream.range(0,n).boxed()
				.map(i->(Exp)visit(ctx.exp(i)))
				.collect(Collectors.toList());
		return parameters;
	}
	
	@Override public Return visitReturnSentence(ProgramParser.ReturnSentenceContext ctx) { 
		Exp exp = (Exp) visit(ctx.exp());
		return Return.of(exp);
	}
}
