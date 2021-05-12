package Models;

import java.time.LocalDateTime;

public class Pointage {
	private int idEmp;
	private LocalDateTime time;
	
	public Pointage(int i) {
		idEmp = i;
		time = arrondiQuartHeure(LocalDateTime.now());
	}
	
	public LocalDateTime arrondiQuartHeure(LocalDateTime t) {
		int diff = t.getMinute()%15;
		LocalDateTime tqh = null;
		if(diff < 8) {
			tqh = t.minusMinutes(diff);
		}else if(diff >= 8) {
			tqh = t.plusMinutes(15-diff);
		}
		tqh = tqh.minusSeconds(tqh.getSecond());
		tqh = tqh.minusNanos(tqh.getNano());
		return tqh;
	}
	
	public LocalDateTime getTime() {
		return time;
	}
	
	public int getIdEmp() {
		return idEmp;
	}
	
	public String toString() {
		return "Pointage de l'employ� "+idEmp+" � "+time;
	}
	
	public static void main(String[] args) {
		System.out.println(new Pointage(0));
	}
}
