package us.lsi.alg.vuelos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import us.lsi.common.LocalDateTime2;

public class Vuelo {
	
	public static LocalDate fecha;
	public static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	
	private Integer id;
	private String from;
	private String to;
	private LocalDateTime horaDeSalida;
	private Integer duracion;
	public static Integer n = 0;
	
	public static  Vuelo ofFormat(String[] s) {
		LocalTime hs = LocalTime.parse(s[2],DateTimeFormatter.ofPattern("HH:mm"));
		LocalDateTime dhs = LocalDateTime.of(Vuelo.fecha, hs);
		return Vuelo.of(s[0],s[1],dhs,Integer.parseInt(s[3]));
	}
	
	public static Vuelo of(Integer id, String from, String to, LocalDateTime timeSalida, Integer duracion) {
		Vuelo v = new Vuelo(id,from, to, timeSalida, duracion);
		return v;
	}
	
	public static Vuelo of(String from, String to, LocalDateTime timeSalida, Integer duracion) {
		Vuelo v = new Vuelo(n,from, to, timeSalida, duracion);
		n++;
		return v;
	}
	
	public static Double getVertexPassWeight(String vertex, Vuelo edgeIn, Vuelo edgeOut) {		
		double r=0.;
		if (edgeIn != null && edgeOut !=null) {
			LocalDateTime horaDeLlegada = edgeIn.getHoraDeLlegada();
			LocalDateTime horaDeSalida = LocalDateTime2.next(horaDeLlegada,edgeOut.getHoraDeSalida());
			r = horaDeLlegada.until(horaDeSalida,ChronoUnit.MINUTES);
		}
		return r;
	}
	
	public static List<Vuelo> vuelos(List<Vuelo> vuelos) {
		List<Vuelo> r = new ArrayList<>();
		r.add(vuelos.get(0));
		for (int i=1;i<vuelos.size();i++) {
			Vuelo v=vuelos.get(i);
			Vuelo va=vuelos.get(i-1);
			r.add(Vuelo.of(v.getId(),v.getFrom(),v.getTo(),LocalDateTime2.next(va.getHoraDeLlegada(),v.getHoraDeSalida()),v.getDuracion()));
		}
		return r;
	}
	
	
	Vuelo(Integer n, String from, String to, LocalDateTime horaDeSalida, Integer duracion) {
		super();
		this.from = from;
		this.to = to;
		this.horaDeSalida = horaDeSalida;
		this.duracion = duracion;
		this.id = n;
	}
	
	public Vuelo copy() {
		return Vuelo.of(from, to, this.horaDeSalida, duracion);
	}

	public Vuelo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public LocalDateTime getHoraDeSalida() {
		return horaDeSalida;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public LocalDateTime getHoraDeLlegada() {
		return horaDeSalida.plusMinutes(duracion);
	}	

	@Override
	public String toString() {
		String hl = horaDeSalida.format(Vuelo.dateFormat);
		String hs = getHoraDeLlegada().format(Vuelo.dateFormat);
		return String.format("(%d,%s,%s,%s,%s,%d)",id,from,to,hl,hs,duracion);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vuelo other = (Vuelo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
