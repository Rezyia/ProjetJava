package Models;

import java.io.Serializable;
import java.time.LocalDateTime;

import Controlers.Toolbox;

/**
 * Classe représentant un pointage
 *
 */
public class Pointing implements Serializable{
	
	private static final long serialVersionUID = 369934536979460449L;
	
	private int idEmp;
	private LocalDateTime time;
	
	public Pointing(int id) {
		idEmp = id;
		time = Toolbox.roundToNearestQuarter(LocalDateTime.now());
	}
	
	public Pointing(int id, LocalDateTime time) {
		idEmp = id;
		this.time = Toolbox.roundToNearestQuarter(time);
	}
	
	/**
	 * Getter de time
	 * @return L'horaire et le jour ou a été effectué le pointage
	 */
	public LocalDateTime getTime() {
		return time;
	}
	
	/**
	 * Getter de l'ID de l'employé
	 * @return L'ID de l'employé qui a créé ce pointage
	 */
	public int getIdEmp() {
		return idEmp;
	}
	
	public String toString() {
		return "Pointage de l'employé "+idEmp+" à "+time;
	}
}
