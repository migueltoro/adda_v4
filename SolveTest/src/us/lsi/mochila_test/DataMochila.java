package us.lsi.mochila_test;

import java.util.List;

import us.lsi.mochila.datos.DatosMochila;
import us.lsi.mochila.datos.ObjetoMochila;
import us.lsi.solve.AuxGrammar;

public class DataMochila {
	
	public static Integer CI;
	public static List<ObjetoMochila>  objetos;
	public static int n;
	public static Integer getCI() {
		return CI;
	}
	public static Integer getN() {
		return n;
	}
	public static Integer getValor(Integer i) {
		return objetos.get(i).valor();
	}
	public static Integer getPeso(Integer i) {
		return objetos.get(i).peso();
	}
	public static Integer getNMU(Integer i) {
		return objetos.get(i).numMaxDeUnidades();
	}	
	public static Integer s(Integer a, Integer b, Integer c) {
		return a+b+c;
	}
	public static void iniMochila() {
		AuxGrammar.dataClass = DataMochila.class;
		DatosMochila.iniDatos("ficheros/objetosMochila.txt");
		DatosMochila.capacidadInicial = 78;	
		DataMochila.CI = DatosMochila.capacidadInicial;
		DataMochila.objetos = DatosMochila.getObjetos();
		DataMochila.n = DataMochila.objetos.size();
	}

}
