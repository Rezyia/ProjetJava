package Models;

import java.io.Serializable;
import java.time.LocalDateTime;

import Controlers.Toolbox;

public class Pointing implements Serializable{
	
	private static final long serialVersionUID = 369934536979460449L;
	
	private int idEmp;
	private LocalDateTime time;
	
	public Pointing(int i) {
		idEmp = i;
		time = Toolbox.roundToNearestQuarter(LocalDateTime.now());
	}
	
	public Pointing(int i, LocalDateTime time) {
		idEmp = i;
		this.time = Toolbox.roundToNearestQuarter(time);
	}
	
	public LocalDateTime getTime() {
		return time;
	}
	
	public int getIdEmp() {
		return idEmp;
	}
	
	public String toString() {
		return "Pointage de l'employé "+idEmp+" à "+time;
	}
	
	public static void main(String[] args) {
		System.out.println(new Pointing(0));
	}
}
