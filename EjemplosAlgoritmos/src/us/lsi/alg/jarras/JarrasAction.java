package us.lsi.alg.jarras;

import java.util.List;

import us.lsi.graphs.virtual.Action;

public class JarrasAction   {

	public static Action<JarrasVertex> a0 = Action.of(0,"Vaciar J1",
			v->v.c1()>0,
			v->JarrasVertex.of(0,v.c2()));
	public static Action<JarrasVertex> a1 = Action.of(1,"Volcar J1 en J2",//J1 queda vacía.
			v->v.c1()>0 && v.c2() < JarrasVertex.cP2,
			v->JarrasVertex.of(0,Math.min(v.c1()+v.c2(),JarrasVertex.cP2)));
	public static Action<JarrasVertex> a2 = Action.of(2,"Echar J1 en J2", // J1 conserva el liquido restante 
		v->v.c1()>0 && v.c2() < JarrasVertex.cP2,
		v->JarrasVertex.of(Math.max(0,v.c1()+v.c2()-JarrasVertex.cP2), 
				Math.min(v.c1()+v.c2(),JarrasVertex.cP2)));
	public static Action<JarrasVertex> a3 = Action.of(3,"Llenar J1",
			v->v.c1() < JarrasVertex.cP1,
			v->JarrasVertex.of(JarrasVertex.cP1,v.c2()));
	public static Action<JarrasVertex> a4 = Action.of(4,"Llenar J2",
			v->v.c2() < JarrasVertex.cP2,
			v->JarrasVertex.of(v.c1(),JarrasVertex.cP2));
	public static Action<JarrasVertex> a5 = Action.of(5,"Vaciar J2",
			v->v.c2() > 0,
			v->JarrasVertex.of(v.c1(), 0));
	public static Action<JarrasVertex> a6 = Action.of(6,"Volcar J2 en J1",  // J2 siempre queda vacía.
			v->v.c2() > 0 && v.c1() < JarrasVertex.cP1,
			v->JarrasVertex.of(Math.min(v.c1()+v.c2(),JarrasVertex.cP1),0));
	public static Action<JarrasVertex> a7 = Action.of(7,"Echar J2 en J1",  // J2 se conserva liquido restante
			v->v.c2() > 0 && v.c1() < JarrasVertex.cP1,
			v->JarrasVertex.of(Math.min(v.c1()+v.c2(),JarrasVertex.cP1), 
					Math.max(0,v.c1()+v.c2()-JarrasVertex.cP1)));
	
	public static List<Action<JarrasVertex>> actions = List.of(a0,a1,a2,a3,a4,a5,a6,a7);
}
