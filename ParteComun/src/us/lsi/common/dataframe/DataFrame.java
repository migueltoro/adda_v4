package us.lsi.common.dataframe;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public interface DataFrame {
	    
	    public static DataFrame of(List<String> columNames,List<List<String>> rows) {  
	        return DataFrameI.of(columNames,rows);
	    }
	    
	    public static DataFrame of(Map<String,List<String>> data) {
	        return DataFrameI.of(data);
	    }
	        
	    public static DataFrame of(Map<String,List<String>> data, List<String> columNames) {
	        return DataFrameI.of(data, columNames);
	    }
	    
	    public static DataFrame parse(String file) {
	        return DataFrameI.parse(file);
	    }
	    
	    public static DataFrame parse(String file, List<String> columNames) {
	        return DataFrameI.parse(file, columNames);
	    }
	    
	    public static DateTimeFormatter dateFormat() {
	    	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    	return fmt;
	    }
	    
	    public static DateTimeFormatter timeFormat() {
	    	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
	    	return fmt;
	    }
	    
	    public static DateTimeFormatter dateTimeFormat() {
	    	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	    	return fmt;
	    }
	    
	    public static String string(Object r) {
	    	return DataFrameI.string(r);
	    }
	    
	    public static <R> R parse(String text, Class<R> type) {
	    	return DataFrameI.parse(text, type);
	    }
	    
	    public static void setTam(Integer tam) {
	    	DataFrameI.tam = tam;
	    }
	    
	    List<String> columNames();
	    
	    Integer columNumber();
	    
	    Map<String,Integer> columnIndex();
	    
	    List<String> colum(String name);
	    
	    List<String> colum(Integer index);
	    
	    <R> List<R> colum(String name, Class<R> type);
	    
	    <R> List<R> colum(Integer index, Class<R> type);
	           
	    Boolean columAllDifferent(String name);
	    
	    String propertie(List<String> row, String colum);
	    
	    <R> R propertie(List<String> row, String colum, Class<R> type);
	      
	    String cell(Integer row,String colum);
	       
	    String cell(Integer row,Integer colum);
	    
	    String cell(String row,String colum, String propertie);
	
	    Integer rowNumber();
	        
	    List<String> row(Integer n);
	        
	    List<String> row(String row, String colum);
	      	  
	    List<List<String>> rows();
	    
	    Map<String,List<String>> toMap();

	    DataFrame head();
	    
	    // Mostrar los primeros n registros (por defecto, n=5)
	    DataFrame head(Integer n);
	    
	    DataFrame tail();
	    
	    // Mostrar los últimos n registros (por defecto, n=5)
	    DataFrame tail(Integer n);
	    
	    DataFrame slice(Integer n,Integer m);
	    
	    DataFrame filter(Predicate<List<String>> p);
	       
	    // Ordena según los valores devueltos por la función order
	    <E extends Comparable<? super E>> DataFrame sortBy(Function<List<String>,E> order, Boolean reverse);
	        	    
	    <R> DataFrame groupBy(List<String> columNames,String newColumn,BinaryOperator<R> op, Function<List<String>,R> value);
	         
	    DataFrame addColum(String newColum, List<String> datos);
	    
	    DataFrame addCalculatedColum(String newColum,Function<List<String>,String> f);
	    
	    DataFrame removeColum(String colum);
	    
	    DataFrame join(DataFrame other, String key, List<String> columsKey, List<String> columnsOther);
	    
	    String toString();	      
}
