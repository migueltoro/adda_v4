package us.lsi.common;

/**
 * @author migueltoro
 *
 * @param <T> Un tipo inmutable
 * 
 * Un versi�n mutable del tipo inmutable
 */
public class MutableType<T> {

	public static <T> MutableType<T> of(T e) {
		return new MutableType<T>(e);
	}
	
	protected T value;
	
	protected MutableType(T e) {
		super();
		this.value = e;
	}
	
	public T setValue(T e) {
		T old = this.value;
		this.value = e;
		return old;
	}
	
	public T value() {
		return value;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MutableType))
			return false;
		MutableType<?> other = (MutableType<?>) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	public static class MutableInteger extends MutableType<Integer> {

		private MutableInteger(Integer e) {
			super(e);
		}
		
		public static MutableInteger of(Integer e) {
			return new MutableInteger(e);
		}
		
		public static MutableInteger of() {
			return new MutableInteger(0);
		}
		
		public Integer valueInc() {
			Integer oldValue = super.value;
			super.value++;	
			return oldValue;
		}
		
	}
	
}
