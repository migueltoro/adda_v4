package us.lsi.common;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTime2 {
	
	public static DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	
	public static LocalDateTime next(LocalDateTime dateTime, LocalDateTime time) {
		while(time.isBefore(dateTime)) {
			time = time.plusDays(1L);
		}
		return time;
	}
	
	public static void main(String[] args) {
		System.out.println(next(LocalDateTime.of(2023,10,22,23,45),LocalDateTime.of(2023,10,22,7,45)).format(pattern));
	}

}
