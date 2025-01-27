package us.lsi.common.dataframe;

import java.util.List;

public class Test2 {

	public static void main(String[] args) {
		DataFrame d = DataFrame.parse("ficheros/personas.csv",List.of("Nombre","Apellido","Edad","Altura","Fecha"));
	    System.out.println(d);
	    DataFrame d2 = DataFrame.parse("ficheros/personas_2.csv",List.of("Nombre","Salario"));
	    System.out.println(d2);
	    System.out.println(d.join(d2, "Nombre",List.of("Apellido","Edad"),List.of("Salario")));
	    DataFrame.setTam(16);
	    System.out.println(d.addCalculatedColum("Nombre Completo",ls->ls.get(0)+","+ls.get(1)));
	}

}
