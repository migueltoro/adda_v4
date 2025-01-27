package us.lsi.sa;


public interface StateSa {
	
	double fitness();
	StateSa mutate();
	StateSa random();
	StateSa copy();

}
