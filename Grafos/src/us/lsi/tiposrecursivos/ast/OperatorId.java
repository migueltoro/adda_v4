package us.lsi.tiposrecursivos.ast;

public interface OperatorId {
		
	String name();
	
	Integer arity();
	
	String longName();
	
	public static OperatorId of0(String name) {
		return new OperatorId0(name,0);
	}
	
	public static OperatorId of1(String name,Type tp) {
		return new OperatorId1(name,tp,1);
	}
	
	public static OperatorId of2(String name,Type tp1,Type tp2) {
		return new OperatorId2(name,tp1,tp2,2);
	}
	
	public static OperatorId of3(String name,Type tp1,Type tp2, Type tp3) {
		return new OperatorId3(name,tp1,tp2,tp3,3);
	}
	
	public static OperatorId ofN(String name,Type tp) {
		return new OperatorIdN(name,tp,-1);
	}
	
	public static record OperatorId0(String name,Integer arity) implements OperatorId {
		public String longName() {
			return String.format("%s%d",name,arity);
		}
	}
	
	public static record OperatorId1(String name,Type tp,Integer arity) implements OperatorId {
		public String longName() {
			return String.format("%s%d%s",name,arity,tp);
		}
	}
	
	public static record OperatorId2(String name,Type tp1,Type tp2,Integer arity) implements OperatorId {
		public String longName() {
			return String.format("%s%d%s%s",name,arity,tp1,tp2);
		}
	}
	
	public static record OperatorId3(String name,Type tp1,Type tp2,Type tp3,Integer arity) implements OperatorId {
		public String longName() {
			return String.format("%s%d%s%s%s",name,arity,tp1,tp2,tp3);
		}
	}
	
	public static record OperatorIdN(String name,Type tp,Integer arity) implements OperatorId {
		public String longName() {
			return String.format("%s%d%s",name,arity,tp);
		}
	}
}
