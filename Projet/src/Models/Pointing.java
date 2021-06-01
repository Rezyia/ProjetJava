package Models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Pointing implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idEmp;
	private LocalDateTime time;
	
	public Pointing(int i) {
		idEmp = i;
		time = LocalDateTime.now();
	}
	
	public Pointing(int i, LocalDateTime time) {
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
		System.out.println(new Pointing(0));
	}
}
