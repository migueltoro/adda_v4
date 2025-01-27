package us.lsi.regularexpressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import us.lsi.common.Preconditions;
import us.lsi.common.Set2;


public class Tokenizer {

	public enum TokenType{Integer,Double,Variable,Function,ReservedWord,Operator,Separator,Symbol};
	public static Set<String> separators = Set2.of("{", "}", ",", ";", "(", ")");
	private static String space = "\\s+";
	private static String number = "[0-9]+(\\.[0-9]*)?";
	private static String identifier = "[_a-zA-Z][a-zA-Z0-9]*";
	private static String operatorOrSeparator =  
			"\\*=?|\\+=?|\\-=?|/=?|!=?|(==?)|(<=?)|(>=?)|\\?\\:?|[\\^\\(\\)\\{\\}\\,\\;]";
	private static String symbol = "[$&@]";
	private static Pattern pSpace = Pattern.compile(space);
	private static Pattern pNumber = Pattern.compile(number);
	private static Pattern pIdentifier = Pattern.compile(identifier);
	private static Pattern pOperatorOrSeparator = Pattern.compile(operatorOrSeparator);
	private static Pattern pSymbol = Pattern.compile(symbol);
	
	private String text;	
	private List<Token> tokens;
	private Set<String> functions;
	private Set<String> reservedWords;
	
	
	private int index;
	private int end;
	private int start;
	
	public static Tokenizer create(String text, Set<String> functions, Set<String> reservedWords) {
		return new Tokenizer(text,functions,reservedWords);
	}
	
	public static Tokenizer create(String text) {
		return new Tokenizer(text,null,null);
	}
	
	private Tokenizer(String text,Set<String> functions, Set<String> reservedWords) {
		super();
		this.text = text;
		if (functions != null) this.functions = functions;
		else this.functions = Set2.of();
		if (reservedWords != null) this.reservedWords = reservedWords;
		else this.reservedWords = Set2.of();
		
		
		this.index=0;
		this.start = 0;
		this.end = this.text.length();
		this.tokens = tokens();
	}

	public String getText() {
		return text;
	}
	
	public String suffix(){
		Integer in = Math.max(0,index-1);
		return text.substring(tokens.get(in).start);
	}
	
	public Integer index(){
		return index;
	}

	public Set<String> getFunctions() {
		return functions;
	}

	public Set<String> getReservedWords() {
		return reservedWords;
	}
	
	public List<Token> getTokens() {
		return tokens;
	}
	
	public Token currentToken() {
		Preconditions.checkState(index>0, String.format("No hay token actual todavía %d",index));
		return 	tokens.get(index-1);	
	}

	public Token nextToken(){
		Preconditions.checkState(hasMoreTokens(), 
			String.format("Cadena acabada inesperadamente en la posición %d,%s",index(),suffix()));
		Integer oldIndex = index;
		index = index+1;
		return 	tokens.get(oldIndex);	
	}
	
	public boolean hasMoreTokens() {
		return index < tokens.size();
	}
	
	public Token seeNextToken() {
		Preconditions.checkState(hasMoreTokens(), 
			String.format("Cadena acabada inesperadamente en la posición %d,%s",index(),suffix()));
	    return tokens.get(index);
	}
	
	public Token previousToken(){
		Preconditions.checkState(index>0, "No existe estado previo");
		index = index-1;
		return tokens.get(index);	
	}
	
	private List<Token> tokens(){
		List<Token> r = new ArrayList<>();
		while(hasMore()){
			r.add(next());
		}
		return r;
	}
	
	private boolean hasMore() {
		return start < end;
	}
	
	private Token next() {
		Matcher matcher = null;		
		String token = null;
		TokenType tokenType = null;
		int inc = 0;
		Character c = text.charAt(start);
		boolean space = false;
		boolean match = false;
		if (Character.isSpaceChar(c)) {
			matcher = pSpace.matcher(text.subSequence(start, end));
			match = matcher.find();
			inc = matcher.end();
			space = true;
		} else if (Character.isDigit(c)) {
			matcher = pNumber.matcher(text.subSequence(start, end));
			match = matcher.find();
			inc = matcher.end();
			token= text.subSequence(start,start+inc).toString();
			if (token.contains(".")) {
				tokenType = TokenType.Double;
			} else {
				tokenType = TokenType.Integer;
			}
		} else if (Character.isLetter(c) || c.equals('_')) {
			matcher = pIdentifier.matcher(text.subSequence(start, end));
			match = matcher.find();
			inc = matcher.end();
			token= text.subSequence(start,start+inc).toString();
			if (reservedWords.contains(token)) {
				tokenType = TokenType.ReservedWord;
			}else if(functions.contains(token)){
				tokenType = TokenType.Function;
			}else {
				tokenType = TokenType.Variable;
			}
		} else if(c.equals('$') || c.equals('&') || c.equals('@')) {		
			matcher = pSymbol.matcher(text.subSequence(start, end));
			match = matcher.find();
			inc = matcher.end();
			token= text.subSequence(start,start+inc).toString();
			tokenType = TokenType.Symbol;
		} else {
			matcher = pOperatorOrSeparator.matcher(text.subSequence(start, end));
			match = matcher.find();
			if (match) {
				inc = matcher.end();
				token= text.subSequence(start,start+inc).toString();
				if (separators.contains(token)) {
					tokenType = TokenType.Separator;
				}else{
					tokenType = TokenType.Operator;
				}
			} else {
				Preconditions.checkState(false, 
						String.format("Caracter %c no reconocido en la posición %d",c,start));
			}			
		}		
		int oldStart = start;
		start = start + inc;
		if(space && hasMore()){
			return next();
		} else {
			return Token.of(token,tokenType,oldStart);
		}
	}
	
	public Boolean isCurrentInTokens(TokenType... s) {
		List<TokenType> sl = Arrays.asList(s);
		Token r = currentToken();
		return sl.contains(r.type);
	}
	
	public Boolean isCurrentInTokens(String... s) {
		List<String> sl = Arrays.asList(s);
		Token r = currentToken();
		return sl.contains(r.text);
	}
	
	public Boolean isNextInTokens(TokenType... s) {
		List<TokenType> sl = Arrays.asList(s);
		Token r = seeNextToken();
		return sl.contains(r.type);
	}
	
	public Boolean isNextInTokens(String... s) {
		List<String> sl = Arrays.asList(s);
		Token r = seeNextToken();
		return sl.contains(r.text);
	}
	
	public Token matchTokens(TokenType... s) {
		Token tk = nextToken();
		error(s);
		return tk;
	}

	public void error(TokenType... s) {
		List<TokenType> sl = Arrays.asList(s);
		Token r = currentToken();
		Preconditions.checkState(sl.contains(r.type),
				String.format("\nSe esperaba %s \ny se ha encontrado %s en la posición \n%s", 
						sl.toString(), 
						currentToken(),
						suffix()));
	}

	public Token matchTokens(String... s) {
		Token tk = nextToken();
		error(s);
		return tk;
	}
	
	public void error(String... s) {
		List<String> sl = Arrays.asList(s);
		Token r = currentToken();
		Preconditions.checkState(sl.contains(r.text),
				String.format("\nSe esperaba %s \ny se ha encontrado %s en la posición \n%s", 
						sl.toString(),r,suffix()));
	}
	
	public void error() {
		Preconditions.checkState(false, 
				String.format("Token %s no reconocido en la posición %d, %s",
						currentToken(),index(),suffix()));
	}

	public boolean isTokenType(TokenType... s){
		List<TokenType> r = Arrays.asList(s);
		return r.contains(currentToken().type);
	}
	
	public boolean isToken(String... s){
		List<String> r = Arrays.asList(s);
		return r.contains(currentToken().text);
	}
	
	public static void main(String[] args) {
		String ex = "while{d==!=(int)23.4   *_y+5+((double)4*x+2.)^3  -sqrt(45.6+2.*@a23),;+45.6/(45<=z*7}";
		System.out.println(Tokenizer.operatorOrSeparator);
		Set<String> functions = Set2.of("sqrt");
		Set<String> reservedWords = Set2.of("while");
		Tokenizer t = Tokenizer.create(ex,functions,reservedWords);
		String s = t.tokens.stream().map(x->x.toString()).collect(Collectors.joining("\n"));
		System.out.println(s);
		System.out.println(t.tokens.size());
	}
	
	
}
