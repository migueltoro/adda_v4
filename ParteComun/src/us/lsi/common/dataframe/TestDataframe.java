package us.lsi.common.dataframe;

import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.time.LocalDate;

public class TestDataframe {

	public static void main(String[] args) {	
	    DataFrame d = DataFrame.parse("ficheros/personas.csv",List.of("Nombre","Apellido","Edad","Altura","Fecha"));
	    System.out.println(d.colum("Altura",Double.class));
//	    System.out.println(d);
	    System.out.println(d.slice(3,5));
//	    System.out.println(d.removeColum("Nombre"));
//	    System.out.println(d);
//	    System.out.println(d.filter(ls->Integer.parseInt(ls.get(2).strip())>60));
//	    System.out.println(d.sortBy(ls->ls.get(1).strip(),true));
	    System.out.println(d.addCalculatedColum("PrimeraLetra",ls->Character.toString(d.propertie(ls, "Nombre").charAt(0))));
//	    DataFrame d2 = DataFrame.parse("ficheros/mascotas.csv",
//	    		List.of("IdMascota","Nombre","Especie","Sexo","Ubicacion","Estado"));
//	    System.out.println(d2);
//	    BinaryOperator<Integer> opm = (x,y) -> x+y;
//	    System.out.println(d2.groupBy(List.of("Especie","Sexo"),"Cantidad",opm,ls->1));
//	    DataFrame d3 = DataFrame.parse("ficheros/personas.csv",List.of("Nombre","Apellido","Edad","Altura","Fecha"));
		DataFrame d3 = DataFrame.parse("ficheros/personas.csv");
		System.out.println(d3);
//	    BinaryOperator<Double> opm2 =  (x,y)-> Math.min(x,y);
//	    System.out.println(d3.groupBy(List.of("Edad"),"Altura",opm2,ls->Double.parseDouble(ls.get(3))));
	    BinaryOperator<LocalDate> opm3 = BinaryOperator.minBy(Comparator.naturalOrder());
//	    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    System.out.println(d3.propertie(d3.row(0),"Fecha"));
	    System.out.println(d3.groupBy(List.of("Edad"),"MinFecha",opm3,
	    		ls->d3.propertie(ls,"Fecha",LocalDate.class)));
//	    System.out.println(d3.groupBy(List.of("Edad"),"MinFecha",opm3,ls->Persona.parse(ls).fecha()));
	    System.out.println(d3.cell(3,"Apellido"));
	    System.out.println(d3.cell(3,1));
	    System.out.println(d3.cell("Roberto","Nombre","Edad"));
	    System.out.println(d3.colum("Nombre"));
	    System.out.println(d3.colum(2));
	    System.out.println(d3.row("Jorge","Nombre"));
	}

}
