package us.lsi.curvefitting;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class RealVectors {
	
	public static RealVector toRealVector(double[] d) {
		RealVector rv = new ArrayRealVector();
		for(int i=0;i<d.length;i++)
			rv = rv.append(d[i]);
		return rv;
	}

}
