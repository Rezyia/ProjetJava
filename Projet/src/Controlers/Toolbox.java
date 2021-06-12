package Controlers;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import Models.*;

public class Toolbox {

	// Random list of first names 
	public static String[] names = {
			"Aaron",
			"Cameron",
			"Ehsan",
			"Elliott",
			"Hal",
			"Ivy",
			"Jeffrey", 
			"Juniper",
			"Kaiden",
			"Kelvin",
			"Maxine",
			"Mayur",
			"Miruna",
			"Mirza",
			"Neel",
			"Sultan",
			"Rafferty",
			"Scarlette",
			"Shane",
			"Valentino"
	};
	
	// Random list of last names
	public static String[] lastNames = {
			"Barnett ",
			"Bernard",
			"Graham",
			"Griffith",
			"Holt", 
			"Hopkins ",
			"Jennings",
			"Kennedy",
			"Lee",
			"Love",
			"Mccoy",
			"Middleton",
			"Owen",
			"Ramsey",
			"Reilly",
			"Robertson",
			"Rodgers ",
			"Scott",
			"Turner",
			"Walters"
	};
	
	// Random list of department names
	public static String[] deptNames = {
		"Communication",
		"Engineering",
		"Human Resources",
		"Interns",
		"Janitoring",
		"Maintenance",
		"Security"
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
     * Generates randomly <code>nbToGenerate</code> Departments in a ControlerMain
     * @param c : ControlerMain to generate departments in
     * @param nbToGenerate : int number of departments to generate
     */
    public static void generateDepartments(ControlerMain c, int nbToGenerate) {
    	int randDept;
    	int sizeDepts = deptNames.length;
    	
    	for (int i=0; i<nbToGenerate; i++) {
    		randDept = new Random().nextInt(sizeDepts);
    		
    		Department dept = new Department(deptNames[randDept]);
        	c.addDepartment(dept);
    	}
        System.out.println("Finished generating departments");
    }
    
    
    /**
     * Generates randomly <code>nbToGenerate</code> Employees in a ControlerMain
     * @param c : controler to generate employees in
     * @param nbToGenerate : integer number of employees to generate
     */
    public static void generateEmployees(ControlerMain c, int nbToGenerate) {
    	int randDept, randName1, randName2, randTime1, randTime2;
    	int sizeDepts = c.getAllDepartment().size();
    	int sizeNames = names.length;
    	
    	for (int i=0; i<nbToGenerate; i++) {
    		randDept = new Random().nextInt(sizeDepts);
    		randName1 = new Random().nextInt(sizeNames);
    		randName2 = new Random().nextInt(sizeNames);
    		
    		Employee emp = new Employee(c.getDepartment(randDept), names[randName1], lastNames[randName2]);
    		LocalDateTime t = LocalDateTime.now().withHour(0);
    		
    		for (String day : Employee.workingDays) {
        		randTime1= new Random().nextInt(3600*5) + 3600*6; // from 6:00 to 10:59
        		randTime2 = new Random().nextInt(3600*9) + 3600*12; // from 12:00 to 20:59
        		LocalDateTime tBegin = roundToNearestQuarter(t.plusSeconds(randTime1));
        		LocalDateTime tEnd = roundToNearestQuarter(t.plusSeconds(randTime2));
    			emp.setplanning(tBegin.getHour(), tEnd.getHour(), tBegin.getMinute(), tEnd.getMinute(), day);
    		}
    		
        	c.addEmploye(emp);
    	}
        System.out.println("Finished generating employees");
    }
    
    /**
     * Generates randomly <code>nbToGenerate</code> Pointings and a pointings of the day for half the Employees in the ControlerMain 
     * @param c : controler to generate pointings in
     * @param nbToGenerate : integer number of pointings to generate
     */
    public static void generatePointings(ControlerMain c, int nbToGenerate) {
    	int randTime, randDay, randEmp, randWorktime;
    	int sizeEmps = c.getEmployees().length;

    	// Generate previous pointings :
    	for (int i=0; i<nbToGenerate; i++) {
    		randEmp = new Random().nextInt(sizeEmps);
    		randTime = new Random().nextInt(3600);
    		randWorktime = new Random().nextInt(3600*9);
    		randWorktime += 3600; // Au moins 1 heure
    		randDay = new Random().nextInt(14);

    		LocalDateTime time = LocalDateTime.now().plusSeconds(randTime).minusDays(randDay+1); 

    		if (time.getHour() >= 12) { // Check if second half of the day
    			time = time.withHour(time.getHour()%12+8);
    		}

    		Pointing pt = new Pointing(randEmp, time);
    		Pointing pt2 = new Pointing(randEmp, time.plusSeconds(randWorktime));
    		c.addPointing(pt);    		
    		c.addPointing(pt2);
    	}
    	
    	// Generate pointings for the current day :
    	for (int i=0; i<Math.ceil(c.getNbEmps()/2); i++) {
    		randTime = new Random().nextInt(3600);
    		LocalDateTime now = LocalDateTime.now();
    		if (now.getHour() == 23) now.minusHours(1);
    		
    		c.addPointing(new Pointing(i, now.plusSeconds(randTime)));
    	}
    	
    	
        System.out.println("Finished generating pointings");
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
