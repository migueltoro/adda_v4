package us.lsi.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Printers {

	public static PrintStream file(String file) {
		PrintStream p;
		try {
			p = new PrintStream(new File(file));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("No se puede abrir el fichero " + file);
		}
		return p;
	}
}
