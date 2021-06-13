package Models;
import java.io.Serializable;
import java.time.*;
import java.util.*;

/**
 * Classe représentant un employé
 * @author dylan
 *
 */
public class Employee implements Serializable {

	private static final long serialVersionUID = -2416428736464744808L;
	
	//Le nombre d'employÃ© dans l'entreprise
	private static int nbEmploye = 0;
	private int id;					//L'ID de l'employÃ©								
	private String name;			//Le nom de l'employÃ©
	private String firstname;		//Le prÃ©nom de l'employÃ©
	private Department departement;//Le dÃ©partement de l'employÃ©
	
	private boolean isWorking;		//BoolÃ©en qui indique si l'employÃ© est en train de travailler ou pas
	
	public static String[] workingDays = {
		"monday",
		"tuesday",
		"wednesday",
		"thursday",
		"friday"
	};
	
	private HashMap<String, LocalTime[]> planning;
	//La clÃ© reprÃ©sente la journÃ©e (lundi, mardi, etc.)
	//La valeur est un tableau de LocalDateTime de 2 case
	//[0] -> DÃ©but de journÃ©e prÃ©vu par le planning pour l'employÃ©
	//[1] -> Fin de journÃ©e prÃ©vu par le planning pour l'employÃ©
	
	private int overtime;
	//Temps en surplus de l'employÃ© (temps ou il a travaillÃ© plus qu'il n'Ã©tait prÃ©vu par son planning)
	//Peut Ãªtre nÃ©gatif (alors cela reprÃ©sente du temps ou il n'a pas travaillÃ©)
	//Type peut Ãªtre inexact, Ã  changer en cas de besoin.
	
	public Employee(Department dpt, String str1, String str2) {
		id = nbEmploye;
		nbEmploye++;
		departement = dpt;
		name = str1;
		firstname = str2;
		isWorking = false;
		overtime = 0;
		planning = new HashMap<String, LocalTime[]>(5);
		LocalTime begin =  LocalTime.of(0,0);
		LocalTime end = LocalTime.of(23, 45);
		LocalTime tabtime[] = {begin,end};
		planning.put("monday", tabtime);
		planning.put("tuesday", tabtime);
		planning.put("wednesday", tabtime);
		planning.put("thursday", tabtime);
		planning.put("friday", tabtime);
	}
	
	/**
	 * Getter de l'ID
	 * @return l'ID de cet employé
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Getter de nom
	 * @return Le nom de cet employé
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter de nom
	 * @param name le nouveau nom de l'employé
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter de prénom
	 * @return Le prénom de cet employé
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Setter de prénom
	 * @param firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String toString() {
		return "Employé "+id+" : "+firstname+" "+name;
	}
	
	/**
	 * Est ce que l'employé est en train de travailler ? (Getter de la variable isWorking)
	 * @return un boolean correspondant à la question
	 */
	public boolean isWorking() {
		return isWorking;
	}
	
	/**
	 * Setter de isWorking
	 * @param b
	 */
	public void setWorking(boolean b) {
		isWorking = b;
	}
	
	/**
	 * Getter de overtime
	 * @return Le temps de travail en trop ou manquant de cet employé
	 */
	public int getOvertime() {
		return  overtime;
	}
	
	/**
	 * Setter de overtime
	 * @param ot
	 */
	public void setOvertime(int ot) {
		overtime = ot;
	}
	
	/**
	 * Getter de planning, en fonction du jour
	 * @param day Le jour de la semaine (en anglais)
	 * @return Les horaires de début de journée (LocalTime[0]) et de fin de journée (LocalTime[1])
	 */
	public LocalTime[] getPlanningDay(String day) {
		return planning.get(day);
	}
	
	/**
	 * Modifie le planning d'un employé via des int
	 * @param hourBegin
	 * @param hourFinish
	 * @param minBegin
	 * @param minFinish
	 * @param day
	 */
	public void setplanning(int hourBegin , int hourFinish, int minBegin, int minFinish, String day) {
		if(day != "monday" || day != "tuesday" || day != "wednesday" || day != "thursday" || day != "friday" ) { //attention Ã  gÃ©rer les exceptions
			if(	(hourBegin < 24 && hourBegin >= 0) || (hourFinish < 24 && hourFinish >= 0) || 
				(minBegin < 60 && minBegin >= 0) || (minBegin < 60 && minBegin >= 0) ) {
				if(hourBegin*60 +minBegin <hourFinish*60 + minFinish) {
					LocalTime tabtime[] = {LocalTime.of(hourBegin,minBegin),LocalTime.of(hourFinish,minFinish)};
					planning.put(day,tabtime);
				}
			}
		}
	}
	
	/**
	 * Modifie le planning d'un employé via des LocalTime
	 * @param begin
	 * @param end
	 * @param day
	 * @throws Exception Si end < begin
	 */
	public void setPlanning(LocalTime begin, LocalTime end, String day) throws Exception {
		if(end.isBefore(begin)) {
			throw new Exception();
		}
		LocalTime tabtime[] = {begin, end};
		planning.put(day, tabtime);
	}
	
	/**
	 * Setter de département
	 * @param dpt
	 */
	public void setDepartment(Department dpt) {
		this.departement = dpt;
	}

	/**
	 * Getter de département
	 * @return
	 */
	public Department getDepartment() {
		return departement;
	}
}
