package Models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Pointage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idEmp;
	private LocalDateTime time;
	
	public Pointage(int i) {
		idEmp = i;
		time = LocalDateTime.now();
	}
	
	public Pointage(int i, LocalDateTime time) {
		idEmp = i;
		this.time = time;
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
		System.out.println(new Pointage(0));
	}
}
