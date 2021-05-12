package Models;
import java.time.*;
import java.util.*;

public class Employe {
	//Le nombre d'employé dans l'entreprise
	private static int nbEmploye = 0;
	private int id;					//L'ID de l'employé								
	private String name;			//Le nom de l'employé
	private String firstname;		//Le prénom de l'employé
	private Departement departement;//Le département de l'employé
	
	private boolean isWorking;		//Booléen qui indique si l'employé est en train de travailler ou pas
	
	private HashMap<String, LocalTime[]> planning;
	//La clé représente la journée (lundi, mardi, etc.)
	//La valeur est un tableau de LocalDateTime de 2 case
	//[0] -> Début de journée prévu par le planning pour l'employé
	//[1] -> Fin de journée prévu par le planning pour l'employé
	
	private int overtime;
	//Temps en surplus de l'employé (temps ou il a travaillé plus qu'il n'était prévu par son planning)
	//Peut être négatif (alors cela représente du temps ou il n'a pas travaillé)
	//Type peut être inexact, à changer en cas de besoin.
	
	public Employe(Departement dpt, String str1, String str2) {
		id = nbEmploye;
		nbEmploye++;
		departement = dpt;
		name = str1;
		firstname = str2;
		isWorking = false;
		overtime = 0;
		planning = new HashMap<String, LocalTime[]>(5);
		LocalTime midnight =  LocalTime.of(0,0); 
		LocalTime tabtime[] = {midnight,midnight};
		planning.put("monday", tabtime);
		planning.put("tuesday", tabtime);
		planning.put("wednesday", tabtime);
		planning.put("thursday", tabtime);
		planning.put("friday", tabtime);
	}
	
	public String toString() {
		return "Employé "+id+" : "+firstname+" "+name;
	}
	
	public boolean isWorking() {
		return isWorking;
	}
	
	public int getOvertime() {
		return  overtime;
	}
	
	public LocalTime[] getPlanningDay(String day) {
		return planning.get(day);
	}
	
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
	
	
}
