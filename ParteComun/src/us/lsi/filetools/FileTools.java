package us.lsi.filetools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.io.File;

import org.mozilla.universalchardet.UniversalDetector;

import us.lsi.common.Preconditions;

public class FileTools {
	
	public static String detectCharset(String file) {  
		
	    String encoding;  
	  
	    try {  
	    	
	        final FileInputStream fis = new FileInputStream(file);  
	  
	        final UniversalDetector detector = new UniversalDetector(null);  
	        handleData(fis, detector);  
	        encoding = getEncoding(detector);  
	        detector.reset();  
	          
	        fis.close();  
	  
	    } catch (IOException e) {  
	        encoding = "";  
	    }  
	  
	    return encoding;  
	}  
	  
	private static String getEncoding(UniversalDetector detector) {  
	    if(detector.isDone()) {  
	        return detector.getDetectedCharset();  
	    }  
	    return "";  
	}  
	  
	private static void handleData(FileInputStream fis, UniversalDetector detector) throws IOException {  
	    int nread;  
	    final byte[] buf = new byte[4096];  
	    while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {  
	        detector.handleData(buf, 0, nread);  
	    }  
	    detector.dataEnd();  
	} 
	

	public static void write(String file, String text){
		try {
			final PrintWriter f =  new PrintWriter(new BufferedWriter(new FileWriter(file)));
			f.println(text);
			f.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("No se ha podido crear el fichero " + file);
		}
	}
	
	public static String text(String file){
		List<String> lineas = null;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			lineas = bufferedReader.lines().collect(Collectors.toList());
			bufferedReader.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return lineas.stream().collect(Collectors.joining("\n"));
	}
	
	public static String projectRoot() {
		return System.getProperty("user.dir");
	}
	
	public static String absolutePath(String file) {
		String absolute = null;
		try {
            File f = new File(file);
            absolute = f.getAbsolutePath();
        }
        catch (Exception e) {
            Preconditions.checkArgument(false, absolute);
        }
		return absolute;
	}
	
	public static Boolean existFile(String file) {
		Boolean r = true;
		try {
            new File(file);
        }
        catch (Exception e) {
            r= false;
        }
		return r;
	}
	
	public static void main(String[] args) {
		System.out.println(existFile("ficheros/acciones.txt"));
		System.out.println(absolutePath("ficheros/acciones.txt"));
		System.out.println(projectRoot());
	}

}
