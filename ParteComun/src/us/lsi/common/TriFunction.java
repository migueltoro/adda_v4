package us.lsi.common;

public interface TriFunction<S1,S2,S3,T> {
	T apply(S1 op1,S2 op2,S3 op3);
}
