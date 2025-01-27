package us.lsi.regularexpressions;

import us.lsi.regularexpressions.Tokenizer.TokenType;

public class Token {
	
	public static Token of(String text, TokenType type, Integer start) {
		return new Token(text, type, start);
	}


	public String text;
	public TokenType type;
	public Integer start;
	public Integer end;
	
	
	private Token(String text, TokenType type, Integer start) {
		super();
		this.text = text;
		this.type = type;
		this.start = start;
		this.end = start+text.length();
	}


	@Override
	public String toString() {
		return type+"==" + text;
	}

	


}
