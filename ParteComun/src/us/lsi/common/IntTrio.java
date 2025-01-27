package us.lsi.common;

public record IntTrio(Integer first, Integer second, Integer third) {

	public static IntTrio of(Integer first, Integer second, Integer third) {
		return new IntTrio(first, second, third);
	}
	
	public static IntTrio parse(String s) {
		String[] partes = s.split("[(),]");
		return new IntTrio(Integer.parseInt(partes[0].trim()), 
				Integer.parseInt(partes[1].trim()),
				Integer.parseInt(partes[2].trim()));
	}

	@Override
	public String toString() {
		return String.format("(%d,%d,%d)",this.first(),this.second(),this.third());
	}
	
}
