package us.lsi.common.dataframe;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.common.Enumerate;
import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.Preconditions;
import us.lsi.streams.Stream2;

class DataFrameI implements DataFrame {
	
	private static DataFrameI of(List<String> columNames,Map<String,Integer> columIndex,List<List<String>> rows) {
		return new DataFrameI(columNames,columIndex,rows);
	}
	
	static DataFrameI of(Map<String,List<String>> data) {
		List<String> columNames = data.keySet().stream().toList();
		return DataFrameI.of(data,columNames);
	}
	
	static DataFrameI of(Map<String,List<String>> data, List<String> columNames) {
		Preconditions.checkArgument(data.keySet().equals(new HashSet<>(columNames)),
				String.format("Las cabeceras %s == %s no coinciden",data.keySet(),columNames));
		Map<String,Integer> columIndex = new HashMap<>();
		Integer i=0;		
		for(String key:columNames) {
			columIndex.put(key, i);
			i++;
		}
		Integer rn = data.get(columNames.get(0)).size();
		List<List<String>> rows = new ArrayList<>();
		for(Integer r=0; r<rn;r++) {
			List<String> row = new ArrayList<>();
			for(String c:columNames) {
				row.add(data.get(c).get(r));
			}	
			rows.add(row);
		}
        return DataFrameI.of(columNames,columIndex,rows);
    }
	
	static DataFrameI parse(String file) {
		Map<String,List<String>> data = Files2.mapDeCsv(file);
		return DataFrameI.of(data);
    }
	
	static DataFrameI parse(String file, List<String> columNames) {
		Map<String,List<String>> data = Files2.mapDeCsv(file);
		return DataFrameI.of(data, columNames);
    }
	
	static DataFrameI of(List<String> columNames, List<List<String>> rows) {
		Map<String,Integer> columIndex = new HashMap<>();
		IntStream.range(0,columNames.size()).forEach(i->columIndex.put(columNames.get(i),i));
        return DataFrameI.of(columNames,columIndex,rows);
    }

	private List<String> columNames;
	private Map<String,Integer> columIndex;
	private List<List<String>> rows;

	private DataFrameI(List<String>columNames, Map<String, Integer> columIndex, List<List<String>> rows) {
		super();
		this.columNames = new ArrayList<>(columNames);
		this.columIndex = new HashMap<>(columIndex);
		this.rows = new ArrayList<>(rows);
	}
	
	public static Boolean allDifferent(List<String> values) {
		Integer n = values.size();
		Integer m = values.stream().collect(Collectors.toSet()).size();
        return n.equals(m);
    }
	
	public static String string(Object r) {
		String s = null;
		if(r instanceof LocalDate) {
			LocalDate r1 = (LocalDate) r;
			s = r1.format(DataFrame.dateFormat());
		} if(r instanceof LocalTime) {
			LocalTime r1 = (LocalTime) r;
			s = r1.format(DataFrame.timeFormat());
		} if(r instanceof LocalDateTime) {
			LocalDateTime r1 = (LocalDateTime) r;
			s = r1.format(DataFrame.dateTimeFormat());
		} else if(r instanceof Double) {
			s = String.format("%.2f",r);
		} else if(r instanceof Integer) {
			s = String.format("%d",r);
		} else {
			s = r.toString();
		}
		return s;
	}
	
	@SuppressWarnings("unchecked")
	public static <R> R parse(String text, Class<R> type) {
		Object r = null;
		if(type.equals(LocalDate.class)) {
			r = LocalDate.parse(text,DataFrame.dateFormat());
		} else if(type.equals(LocalTime.class)) {
			r = LocalTime.parse(text,DataFrame.timeFormat());
		} else if(type.equals(LocalDateTime.class)) {
			r = LocalDateTime.parse(text,DataFrame.dateTimeFormat());
		} else if(type.equals(Double.class)) {
			r = Double.parseDouble(text);
		} else if(type.equals(Integer.class)) {
			r = Integer.parseInt(text);
		} else {
			r = text;
		}
		return (R) r;
	}

	@Override
	public List<String> columNames() {
		return this.columNames.stream().toList();
	}

	@Override
	public Integer columNumber() {
		return this.columNames.size();
	}
	
	@Override
	public Map<String,Integer> columnIndex() {
		return new HashMap<>(this.columIndex);
	}

	@Override
	public List<String> colum(String name) {
		Integer c = this.columIndex.get(name);
		return this.rows.stream().map(r->r.get(c)).toList();
	}

	@Override
	public List<String> colum(Integer index) {
		return this.rows.stream().map(r->r.get(index)).toList();
	}
	
	@Override
	public <R> List<R> colum(String name, Class<R> type){
		return this.colum(name).stream().map(x->DataFrame.parse(x,type)).toList();
	}
    
	@Override
    public <R> List<R> colum(Integer index, Class<R> type){
		return this.colum(index).stream().map(x->DataFrame.parse(x,type)).toList();
	}

	@Override
	public Boolean columAllDifferent(String name) {
		return DataFrameI.allDifferent(this.colum(name));
	}
	
	@Override
	public String propertie(List<String> row, String colum) {
		return row.get(this.columIndex.get(colum));
	}
	
	@Override
	public <R> R propertie(List<String> row, String colum, Class<R> type) {
		String text = row.get(this.columIndex.get(colum));
		return DataFrame.parse(text, type);
	}

	@Override
	public String cell(Integer row, String colum) {
		return this.rows.get(row).get(this.columIndex.get(colum));
	}

	@Override
	public String cell(Integer row, Integer colum) {
		return this.rows.get(row).get(colum);
	}
	
	@Override
	public String cell(String row,String colum, String propertie) {
		Integer r = this.colum(colum).indexOf(row);
		Integer p = this.columIndex.get(propertie);
		return this.rows.get(r).get(p);
	}

	@Override
	public Integer rowNumber() {
		return this.rows.size();
	}

	@Override
	public List<String> row(Integer i) {
		return this.rows.get(i);
	}

	@Override
	public List<String> row(String row, String colum) {
		Preconditions.checkArgument(DataFrameI.allDifferent(this.colum(colum)));
		Integer r = this.colum(colum).indexOf(row);
		return this.rows.get(r);
	}

	@Override
	public List<List<String>> rows() {
		return this.rows.stream().<List<String>>map(r->r.stream().toList()).toList();
	}
	
	@Override
	public Map<String,List<String>> toMap() {
		Map<String,List<String>> m = new HashMap<>();
		for(String c:this.columNames) {
			m.put(c,this.colum(c));
		}
		return m;
	}

	@Override
	public DataFrame head() {
		return this.head(5);
	}

	@Override
	public DataFrame head(Integer n) {
		List<String> columNames = new ArrayList<>(this.columNames);
		Map<String,Integer> columIndex = new HashMap<>(this.columIndex);
		List<List<String>> rows = new ArrayList<>(this.rows);
		return DataFrameI.of(columNames,columIndex,rows.subList(0, n));
	}

	@Override
	public DataFrame tail() {
		return this.tail(5);
	}

	@Override
	public DataFrame tail(Integer n) {
		List<String> columNames = new ArrayList<>(this.columNames);
		Map<String,Integer> columIndex = new HashMap<>(this.columIndex);
		List<List<String>> rows = new ArrayList<>(this.rows);
		Integer nr = this.rowNumber();
		return DataFrameI.of(columNames,columIndex,rows.subList(nr-n, nr));
	}

	@Override
	public DataFrame slice(Integer n, Integer m) {
		List<String> columNames = new ArrayList<>(this.columNames);
		Map<String,Integer> columIndex = new HashMap<>(this.columIndex);
		List<List<String>> rows = new ArrayList<>(this.rows);
		return DataFrameI.of(columNames,columIndex,rows.subList(n, m));
	}

	@Override
	public DataFrame filter(Predicate<List<String>> p) {
		List<String> columNames = new ArrayList<>(this.columNames);
		Map<String,Integer> columIndex = new HashMap<>(this.columIndex);
		List<List<String>> rows = new ArrayList<>(this.rows).stream().filter(p).toList();
		return DataFrameI.of(columNames,columIndex,rows);
	}

	@Override
	public <E extends Comparable<? super E>> DataFrame sortBy(Function<List<String>, E> f, Boolean reverse) {
		List<String> columNames = new ArrayList<>(this.columNames);
		Map<String,Integer> columIndex = new HashMap<>(this.columIndex);
		List<List<String>> rows = new ArrayList<>(this.rows);
		Comparator<List<String>> cmp = reverse?Comparator.comparing(f).reversed():Comparator.comparing(f);
		Collections.sort(rows,cmp);
		return DataFrameI.of(columNames,columIndex,rows);
	}
	
	private Set<Integer> indexes(List<String> columNames){
		return columNames.stream().map(cn->this.columIndex.get(cn)).collect(Collectors.toSet());
	}
	
	private List<String> select(List<String> ls, Set<Integer> sl){
		return IntStream.range(0, ls.size()).boxed()
				.filter(i->sl.contains(i))
				.map(i->ls.get(i))
				.toList();
	}
	

	@Override
	public <R> DataFrame groupBy(List<String> columNames, String newColumn, BinaryOperator<R> op,
			Function<List<String>, R> value) {
		Function<List<String>,List<String>> key = ls->this.select(ls,this.indexes(columNames));
		Map<List<String>,R> g = Stream2.groupingReduce(this.rows.stream(),key,op,value);
		DataFrame r = DataFrame.of(columNames,g.keySet().stream().toList());		
		r = r.addColum(newColumn,g.values().stream().map(x->DataFrameI.string(x)).toList());
		return r;
	}

	@Override
	public DataFrame addColum(String newColum, List<String> datos) {
		List<String> columNames = new ArrayList<>(this.columNames);
		columNames.add(newColum);
		Map<String,Integer> columIndex = new HashMap<>(this.columIndex);
		columIndex.put(newColum,this.columNumber()+1);
		List<List<String>> rows = new ArrayList<>(this.rows);
		Integer nr = this.rowNumber();
		List<List<String>> rn = IntStream.range(0, nr).boxed()
				.map(r->List2.concat(rows.get(r),List.of(datos.get(r))))
				.toList();
		return DataFrameI.of(columNames,columIndex,rn);
	}

	@Override
	public DataFrame addCalculatedColum(String newColum, Function<List<String>, String> f) {
		List<String> datos = this.rows.stream().map(f).toList();
		return this.addColum(newColum, datos);
	}

	@Override
	public DataFrame removeColum(String colum) {
		Integer c = this.columIndex.get(colum);
		List<String> columNames = new ArrayList<>(this.columNames);
		columNames.remove(colum);
		Map<String,Integer> columIndex = new HashMap<>();
		IntStream.range(0,columNames.size()).forEach(i->columIndex.put(columNames.get(i),i));		
		List<List<String>> rows = new ArrayList<>(this.rows);
		List<List<String>> rn = rows.stream()
				.map(r->IntStream.range(0, this.columNumber()).boxed().filter(i->i != c).map(i->r.get(i)).toList())
				.toList();
		return DataFrameI.of(columNames,columIndex,rn);
	}
	
	@Override
	public DataFrame join(DataFrame other, String key, List<String> columsKey, List<String> columnsOther) {
		Set<String> ckeys = List2.intersection(this.colum(key),other.colum(key)).stream().collect(Collectors.toSet());
		Integer k1 = this.columnIndex().get(key);
		Integer k2 = other.columnIndex().get(key);
		DataFrame d1 = this.filter(ls->ckeys.contains(ls.get(k1)));
		DataFrame d2 = other.filter(ls->ckeys.contains(ls.get(k2)));
		List<String> nc = List2.concat(List2.concat(List.of(key),columsKey),columnsOther);
		Map<String,List<String>> r = new HashMap<>();
		r.put(key,d1.colum(key));
		for(String c: columsKey) {
			r.put(c, d1.colum(c));
		}
		for(String c: columnsOther) {
			r.put(c, d2.colum(c));
		}
		return DataFrame.of(r, nc);
	}
	
	
	private String format(String propertie, List<String> ls, Integer n) {
		List<String> nls = new ArrayList<String>();
		nls.add(propertie);
		nls.addAll(ls);
		String fmt = "%"+n+"s";
		return nls.stream().map(c->String.format(fmt,c)).collect(Collectors.joining("|","|","|"));
	}
	
	private String format(Enumerate<List<String>> e, Integer n) {
		return this.format(e.counter().toString(),e.value(),n);
	}
	
	private String line(Integer m, Integer n) {
		String s = IntStream.range(0, n).boxed().map(i->"_").collect(Collectors.joining(""));
		return IntStream.range(0, m).boxed().map(i->s).collect(Collectors.joining("|","|","|"));
	}
	
	static Integer tam = 13;
	
	@Override
	public String toString() {
		Integer t = tam;
		String r = this.format(" ",this.columNames(),t);
		String line = this.line(this.columNames().size()+1, t);
		String r3 = Stream2.enumerate(this.rows.stream()).map(x->this.format(x,t))
				.collect(Collectors.joining("\n", r+"\n"+line+"\n", "\n"));
		return r3;
	}

}
