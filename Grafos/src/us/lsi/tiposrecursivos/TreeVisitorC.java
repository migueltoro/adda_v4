package us.lsi.tiposrecursivos;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.tiposrecursivos.parsers.TreeBaseVisitor;
import us.lsi.tiposrecursivos.parsers.TreeParser;

public class TreeVisitorC extends TreeBaseVisitor<Object> {
	
	
	@Override public Object visitEmptyTree(TreeParser.EmptyTreeContext ctx) { 
		return Tree.empty();
	}
	
	@Override public Object visitLabelTree(TreeParser.LabelTreeContext ctx) { 
		Object label = visit(ctx.label());
		return Tree.leaf(label); 
	}
	
	@SuppressWarnings("unchecked")
	@Override public Object visitNaryTree(TreeParser.NaryTreeContext ctx) { 
		String label = (String) visit(ctx.label());
		Integer n = ctx.nary_tree().size();
		List<Tree<String>> children = IntStream.range(0,n).boxed()
				.map(i-> (Tree<String>) visit(ctx.nary_tree(i)))
				.collect(Collectors.toList());				
		Tree<String> tree = Tree.nary(label, children);
		return tree;
	}
	
	@Override public Object visitIdLabel(TreeParser.IdLabelContext ctx) { 
		String s = ctx.getText();
		s = s.replace("/(","(");
		s = s.replace("/)",")");
		s = s.replace("/,",",");
		s = s.replace("/,_",",_"); 
		return s;
	}

}
