package Models;
import java.io.Serializable;
import java.time.*;
import java.util.*;

/**
 * Classe repr�sentant un employ�
 * @author dylan
 *
 */
public class Employee implements Serializable {

	private static final long serialVersionUID = -2416428736464744808L;
	
	//Le nombre d'employé dans l'entreprise
	private static int nbEmploye = 0;
	private int id;					//L'ID de l'employé								
	private String name;			//Le nom de l'employé
	private String firstname;		//Le prénom de l'employé
	private Department departement;//Le département de l'employé
	
	private boolean isWorking;		//Booléen qui indique si l'employé est en train de travailler ou pas
	
	public static String[] workingDays = {
		"monday",
		"tuesday",
		"wednesday",
		"thursday",
		"friday"
	};
	
	private HashMap<String, LocalTime[]> planning;
	//La clé représente la journée (lundi, mardi, etc.)
	//La valeur est un tableau de LocalDateTime de 2 case
	//[0] -> Début de journée prévu par le planning pour l'employé
	//[1] -> Fin de journée prévu par le planning pour l'employé
	
	private int overtime;
	//Temps en surplus de l'employé (temps ou il a travaillé plus qu'il n'était prévu par son planning)
	//Peut être négatif (alors cela représente du temps ou il n'a pas travaillé)
	//Type peut être inexact, à changer en cas de besoin.
	
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
	 * @return l'ID de cet employ�
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Getter de nom
	 * @return Le nom de cet employ�
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter de nom
	 * @param name le nouveau nom de l'employ�
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter de pr�nom
	 * @return Le pr�nom de cet employ�
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Setter de pr�nom
	 * @param firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String toString() {
		return "Employ� "+id+" : "+firstname+" "+name;
	}
	
	/**
	 * Est ce que l'employ� est en train de travailler ? (Getter de la variable isWorking)
	 * @return un boolean correspondant � la question
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
	 * @return Le temps de travail en trop ou manquant de cet employ�
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
	 * @return Les horaires de d�but de journ�e (LocalTime[0]) et de fin de journ�e (LocalTime[1])
	 */
	public LocalTime[] getPlanningDay(String day) {
		return planning.get(day);
	}
	
	/**
	 * Modifie le planning d'un employ� via des int
	 * @param hourBegin
	 * @param hourFinish
	 * @param minBegin
	 * @param minFinish
	 * @param day
	 */
	public void setplanning(int hourBegin , int hourFinish, int minBegin, int minFinish, String day) {
		if(day != "monday" || day != "tuesday" || day != "wednesday" || day != "thursday" || day != "friday" ) { //attention à gérer les exceptions
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
	 * Modifie le planning d'un employ� via des LocalTime
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
	 * Setter de d�partement
	 * @param dpt
	 */
	public void setDepartment(Department dpt) {
		this.departement = dpt;
	}

	/**
	 * Getter de d�partement
	 * @return
	 */
	public Department getDepartment() {
		return departement;
	}
}
