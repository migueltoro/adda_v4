package us.lsi.reinas.datos;

public class Reina {
	private Integer y;
	private Integer x;
	public static int numeroDeReinas = 8;
	
	public static Reina create(int x, int y) {
		return new Reina(x, y);
	}
	
	Reina(int x, int y) {
		super();		
		this.x = x;
		this.y = y;
	}
	
	public Integer getY() {
		return y;
	}
	public Integer getX() {
		return x;
	}

	public Integer getDiagonalPrincipal(){
		return y-x;
	}
	public Integer getDiagonalSecundaria(){
		return y+x;
	}
	
	@Override
	public String toString() {
		return "[" + x + ","+ y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reina other = (Reina) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}
	
}
