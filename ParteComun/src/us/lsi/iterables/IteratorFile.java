package us.lsi.iterables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class IteratorFile implements Iterator<String>, Iterable<String> {
	
	private BufferedReader bf;
	private String file;
	private String nextLine;

	public IteratorFile(String file) {
		super();
		this.file = file;			
		try {
			this.bf = new BufferedReader(new FileReader(file));
			this.nextLine = this.bf.readLine();
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}

	@Override
	public Iterator<String> iterator() {
		return new IteratorFile(file);
	}

	@Override
	public boolean hasNext() {
		return this.nextLine != null;
	}

	@Override
	public String next() {
		String r = this.nextLine;
		try {
			this.nextLine = bf.readLine();
		} catch (IOException e) {
			System.err.println(e);
		}
		return r;
	}

}

