package Models;
import java.time.*;
import java.util.*;

public class Employe {
	//Le nombre d'employ� dans l'entreprise
	private static int nbEmploye = 0;
	private int id;					//L'ID de l'employ�								
	private String name;			//Le nom de l'employ�
	private String firstname;		//Le pr�nom de l'employ�
	private Departement departement;//Le d�partement de l'employ�
	
	private boolean isWorking;		//Bool�en qui indique si l'employ� est en train de travailler ou pas
	
	private HashMap<String, LocalDateTime[]> planning;
	//La cl� repr�sente la journ�e (lundi, mardi, etc.)
	//La valeur est un tableau de LocalDateTime de 2 case
	//[0] -> D�but de journ�e pr�vu par le planning pour l'employ�
	//[1] -> Fin de journ�e pr�vu par le planning pour l'employ�
	
	private int overtime;
	//Temps en surplus de l'employ� (temps ou il a travaill� plus qu'il n'�tait pr�vu par son planning)
	//Peut �tre n�gatif (alors cela repr�sente du temps ou il n'a pas travaill�)
	//Type peut �tre inexact, � changer en cas de besoin.
	
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
		return "Employ� "+id+" : "+firstname+" "+name;
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
