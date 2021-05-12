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
	
	private HashMap<String, LocalDateTime[]> planning;
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
		planning = new HashMap<String, LocalDateTime[]>(5);
		planning.put("Lundi", null);
		planning.put("Mardi", null);
		planning.put("Mercredi", null);
		planning.put("Jeudi", null);
		planning.put("Vendredi", null);
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
	
	public LocalDateTime[] LocalDateTime(String day) {
		return planning.get(day);
	}
	
	
}
