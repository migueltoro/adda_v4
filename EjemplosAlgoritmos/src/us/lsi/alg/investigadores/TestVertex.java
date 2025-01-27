package us.lsi.alg.investigadores;

public class TestVertex {

	public static void main(String[] args) {
		DatosInv.iniDatos("ficheros/investigadores/inv3.txt");
		DatosInv.toConsole();
		
		InvVertex v1 = InvVertex.first();		
		InvVertex v2 = v1.neighbor(1);
		System.out.println(v1);
		System.out.println(v1.esTrabajoTerminable(0));
		System.out.println(v1.actions());
		System.out.println(v1.mayorAccionPosible());
		System.out.println(v2);
		System.out.println(v2.ldEr().get(0));
		System.out.println(v2.esp(1));
		System.out.println(v2.dEr());
		System.out.println(v2.esTrabajoTerminable(0));
		System.out.println(v2.actions());
		System.out.println(v2.mayorAccionPosible());
		System.out.println(v2.esTrabajoTerminable(1));		
	}

}
