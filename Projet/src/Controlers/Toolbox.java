package Controlers;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import Models.*;

public class Toolbox {

	public static String[] names = {
			"Scarlette",
			"Elliott",
			"Neel",
			"Mayur",
			"Valentino",
			"Kelvin",
			"Juniper",
			"Cameron",
			"Miruna",
			"Kaiden",
			"Shane",
			"Hal",
			"Aaron",
			"Maxine",
			"Ivy ",
			"Jeffrey", 
			"Mirza ",
			"Sultan ",
			"Ehsan ",
			"Rafferty" 
	};
	
	public static String[] deptNames = {
		"Human Resources",
		"Engineering",
		"Interns",
		"Security",
		"Janitoring",
		"Communication",
		"Maintenance"
	};
 	
	/**
     * Méthode pour arrondir au quart d'heure un LocalDateTime
     * @param t Le temps qu'on veut arrondir
     * @return tqh Le temps arrondi au quart d'heure
     */
    public static LocalDateTime roundToNearestQuarter(LocalDateTime t) {
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
    
    
    /**
     * 
     * @param c : controler to generate employees in
     * @param nbToGenerate : integer number of employees to generate
     */
    public static void generateDepartments(ControlerMain c, int nbToGenerate) {
    	int randDept;
    	int sizeDepts = deptNames.length;
    	
    	for (int i=0; i<nbToGenerate; i++) {
    		randDept = new Random().nextInt(sizeDepts);
    		
    		Department dept = new Department(deptNames[randDept]);
        	c.addDepartment(dept);
    	}
    }
    
    
    /**
     * 
     * @param c : controler to generate employees in
     * @param nbToGenerate : integer number of employees to generate
     */
    public static void generateEmployees(ControlerMain c, int nbToGenerate) {
    	int randDept, randName1, randName2;
    	int sizeDepts = c.getAllDepartment().size();
    	int sizeNames = names.length;
    	
    	for (int i=0; i<nbToGenerate; i++) {
    		randDept = new Random().nextInt(sizeDepts);
    		randName1 = new Random().nextInt(sizeNames);
    		randName2 = new Random().nextInt(sizeNames);
    		
    		Employee emp = new Employee(c.getDepartment(randDept), names[randName1], names[randName2]);
        	c.addEmploye(emp);
    	}
    	
    }

    
    
    public static ArrayList<LocalTime> getAllTimeBefore(LocalTime time) {
    	LocalTime t = LocalTime.of(0, 0);
    	ArrayList<LocalTime> liste = new ArrayList<LocalTime>();
    	while(t.isBefore(time)) {
    		liste.add(t);
    		t = t.plusMinutes(15);
    	}
    	return liste;
    }
    
    public static ArrayList<LocalTime> getAllTimeAfter(LocalTime time) {
    	LocalTime t = LocalTime.of(23, 45);
    	ArrayList<LocalTime> liste = new ArrayList<LocalTime>();
    	while(time.isBefore(t)) {
    		liste.add(time);
    		time = time.plusMinutes(15);
    	}
    	liste.add(LocalTime.of(23, 45));
    	return liste;
    }
    
    public static void main(String[] args) {
    	System.out.println(getAllTimeBefore(LocalTime.of(12, 0)));
    	System.out.println(getAllTimeAfter(LocalTime.of(12, 0)));
    	System.out.println(getAllTimeAfter(LocalTime.of(0, 0)));
    	System.out.println(getAllTimeBefore(LocalTime.of(23, 45)));
    }
}
