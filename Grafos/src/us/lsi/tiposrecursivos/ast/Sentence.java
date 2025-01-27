package us.lsi.tiposrecursivos.ast;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.nio.Attribute;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;

public sealed interface Sentence extends Vertex permits Assign, IfThenElse, While, Block, Return{
	String name();
	
	@Override
	default Map<String,Attribute> styleAndShape() {
		Map<String,Attribute> m1 = new HashMap<>(GraphColors.color(Color.blue));
		Map<String,Attribute> m2 = GraphColors.style(Style.bold);
		m1.putAll(m2);
		return m1;
	}

}
