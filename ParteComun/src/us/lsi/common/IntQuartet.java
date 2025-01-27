package us.lsi.common;

public record IntQuartet(Integer first, Integer second, Integer third, Integer fourth) {
	public static IntQuartet of(Integer first, Integer second, Integer third, Integer fourth) {
		return new IntQuartet(first,second,third,fourth);
	}
	
	public static IntQuartet parse(String s) {
		String[] partes = s.split("[(),]");
		return new IntQuartet(Integer.parseInt(partes[0].trim()), 
				Integer.parseInt(partes[1].trim()),
				Integer.parseInt(partes[2].trim()),
				Integer.parseInt(partes[3].trim()));
	}

	@Override
	public String toString() {
		return String.format("(%d,%d,%d,%d)",this.first(),this.second(),this.third(),this.fourth());
	}

}
