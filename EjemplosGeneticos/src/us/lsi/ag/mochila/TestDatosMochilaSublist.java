package us.lsi.ag.mochila;

import us.lsi.mochila.datos.DatosMochila;

public class TestDatosMochilaSublist {

	public static void main(String[] args) {
		DatosMochilaSubList d = new DatosMochilaSubList("ficheros/objetosmochila.txt");
		for(int i = 0;i<DatosMochila.numeroDeObjetos;i++) {
			System.out.println(d.maxMultiplicity(i));
		}
		System.out.println(d.normalSequence());
	}

}
