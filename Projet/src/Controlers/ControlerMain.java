package Controlers;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;

import javax.swing.JFrame;

import Models.*;
import Views.MainFrame;


/**
 * Class to control the main application
 */
public class ControlerMain extends ControlerNetwork implements Serializable{

	private static final long serialVersionUID = -6850799842025316456L;


	private static MainFrame f;
	private ServerSocket ss = null;

	private ArrayList<Employee> employees = new ArrayList<Employee>();
	private ArrayList<Department> departments = new ArrayList<Department>();
	private ArrayList<Pointing> pointings = new ArrayList<Pointing>();
	
	
	//-------------------------------------------------------------Methods
	
	/**
	 * Returns the main frame of the application
	 * @return JFrame
	 */
	public JFrame getFrame() {
		return f;
	}
	
	/**
	 * Updates the lists contained in the JFrame of the ControlerMain used
	 */
	public void updateLists() {
		f.updateLists();
	}
	
	/**
	 * Updates the pointing list
	 * @param items : String[] of items to load in the list
	 */
	public static void updatePointings(String[] items) {
		f.getWindowPointings().updatePointings(items);
	}
	
	
	private void setSocket() throws IOException {
        isA = new InetSocketAddress(address, port);
        ss = new ServerSocket(isA.getPort());
        s = ss.accept();
    }
	
	//-------------------------------------------------------------Methods employees

	/**
	 * Adds an Employee to the ArrayList
	 * @param e : Employee to add
	 */
	public void addEmploye(Employee e) {
		employees.add(e);
	}
	
	/**
	 * Removes an employee from the ArrayList
	 * @param e : Employee to remove
	 */
	public void rmEmploye(Employee e) {
		employees.remove(e);
	}
	
	/**
	 * Checks if the given employee already exists
	 * @param idEmp : id of the employee to check
	 * @return boolean true if employee exists, false otherwise
	 */
	public boolean isEmployeExist(int idEmp) {
		boolean find = false;
		Iterator<Employee> i = employees.iterator();
		while(i.hasNext() && !find) {
			if(i.next().getId() == idEmp) {
				find = true;
			}
		}
		return find;
	}

	/**
	 * 
	 * @param id of the employee to get
	 * @return null or the Employee with the inputed id 
	 */
	public Employee getEmp(int id) {
		Employee emp = null, current = null;
		
		Iterator<Employee> ite = employees.iterator();
		while (ite.hasNext()) {
			current = ite.next();
			if (current.getId() == id) {
				emp = current;
				break; // break out of the while loop if employee is found
			}
		}
		
		return emp;
	}
	
	/**
	 * Get a String array of all the Employees from the ArrayList
	 * @return String[] of the employes
	 */
	public String[] getEmployees() {
		// Declarations & inits:
		ArrayList<String> pts = new ArrayList<String>();
		Employee currentEmployee = null;
		Iterator<Employee> ite = employees.iterator();
		
		// Iterating pointings list : 
		while(ite.hasNext()) {
			currentEmployee = ite.next();
			pts.add((currentEmployee.getId() + " : " + currentEmployee.getFirstname() + " " + currentEmployee.getName()));
		}
		pts.sort(new AlphanumericSortComparator<>(false));
		
		// Convert ArrayList to String array :
		String[] res = new String[pts.size()];
		res = pts.toArray(res);
		return res;
	}
	
	
	/**
	 * Get an Employee with the id passed in parameter
	 * @param idEmp : id of the Employee to get
	 * @return Employee found or null if not found.
	 */
	public Employee getEmployee(int idEmp) {
		Employee emp = null;
		try {
			boolean find = false;
			Iterator<Employee> i = employees.iterator();
			while(i.hasNext() && !find) {
				emp = i.next();
				if(emp.getId() == idEmp) {
					find = true;
				}
			}
			if(!find) {
				throw new Exception();
			}
		}catch(Exception e) {
			System.out.println("L'employé n'existe pas");
		}
		return emp;
	}
	
	/**
	 * Get the number of employees in the ArrayList
	 * @return int number of employees
	 */
	public int getNbEmps() {
		return employees.size();
	}
	
	//-------------------------------------------------------------Methods departments
	
	/**
	 * Adds a department to the ArrayList
	 * @param d : Department to add.
	 */
	public void addDepartment(Department d) {
		departments.add(d);
	}
	
	/**
	 * Removes a department from the ArrayList
	 * @param d : Department to remove.
	 */
	public void rmDepartment(Department d) {
		departments.remove(d);
	}
	
	/**
	 * Check if department exists
	 * @param str : Title of the department 
	 * @return boolean true if exists, false otherwise
	 */
	public boolean isDepartmentExist(String str) {
		boolean find = false;
		Iterator<Department> i = departments.iterator();
		while(i.hasNext() && !find) {
			if(i.next().getnameDep().equals(str)) {
				find = true;
			}
		}
		return find;
	}
	
	/**
	 * Returns the Department at the index <code>id</code> in the ArrayList 
	 * @param id : index of the department to get
	 * @return Department at the index <code>id</code>
	 */
	public Department getDepartment(int id) {
		return departments.get(id);
	}
	
	/**
	 * Gets the reference to the ArrayList of Departments
	 * @return ArrayList<Department> of Departments 
	 */
	public ArrayList<Department> getAllDepartment() {
		return departments;
	}
	
	//-------------------------------------------------------------Methods pointings
	
	/**
	 * Compares the dates of 2 LocalDateTime objects.
	 * @param o1 : First LocalDateTime object to compare
	 * @param o2 : Second LocalDateTime object to compare
	 * @return int difference of years or difference of days if in the same year.
	 */
	public int compareTime(LocalDateTime o1, LocalDateTime o2) {
		if (o1.getYear() != o2.getDayOfYear()) return o1.getYear()-o2.getYear();
		if (o1.getDayOfYear() != o2.getDayOfYear()) return o1.getDayOfYear()-o2.getDayOfYear();
		return 0;
	}
	
	
	/**
	 * Add a pointing to the array, change employee working status and calculate their new overtime.
	 * @param p : Pointing to add
	 */
	public void addPointing(Pointing p) {
		pointings.add(p);
		Employee emp = this.getEmployee(p.getIdEmp());
		
		if (!emp.isWorking()) emp.setWorking(true); // If emp isn't working
		else { // If emp is working
			Iterator<Pointing> ite = pointings.iterator();
			
			while (ite.hasNext()) {
				Pointing currentPt = ite.next();
				if (compareTime(p.getTime(), currentPt.getTime()) == 0 && currentPt.getIdEmp() == emp.getId()) { // If same day & same employee
					// Calculate overtime : 
					int ot = p.getTime().getMinute() - currentPt.getTime().getMinute() + 60 * (p.getTime().getHour() - currentPt.getTime().getHour());
					emp.setOvertime(emp.getOvertime() + ot);
					emp.setWorking(false);
				}
			}
		}
	}
	
	public void rmPointing(Pointing p) {
		pointings.remove(p);
	}
	
	/**
	 * Méthode pour recevoir un objet de la classe Pointage via l'émulateur
	 * @return Un pointeur crée et envoyé par l'émulateur
	 * @throws IOException si les sockets sont pas/mal initialisés
	 */
	public Pointing receivePointing() {
		try{
            System.out.println("TCPServerHello launched...");
            setSocket();
            System.out.println("Hello, the server is on");
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            Pointing pt = (Pointing) in.readObject();
            in.close();
            s.close();
            ss.close();
            return pt;
        }catch(Exception e){
            System.out.println("Exception TCP : receivePointing()");
        }
		return null;
	}
	
	
	/**
	 * Convert a LocalDateTime object to a String
	 * @param time : LocalDateTime to convert
	 * @return String of the time (formated). 
	 */
	public String toListString(LocalDateTime time) {
		return time.getYear() + " " + time.getMonth() + " " + time.getDayOfMonth() + " " + time.getHour() + ":" + time.getMinute();
	}
	
	/**
	 * Get a String array of all the pointings from the ArrayList
	 * @return String[] of the pointings (formated for the list UI)
	 */
	public String[] getPointings() {
		// Declarations & inits:
		ArrayList<String> pts = new ArrayList<String>();
		Pointing currentPointing = null;
		Iterator<Pointing> ite = pointings.iterator();
		
		// Iterating pointings list : 
		while(ite.hasNext()) {
			currentPointing = ite.next();
			pts.add((toListString(currentPointing.getTime()) + " - Employee " + currentPointing.getIdEmp()));
		}
		pts.sort(new AlphanumericSortComparator<String>(false));

		
		// Convert ArrayList to String array :
		String[] res = new String[pts.size()];
		res = pts.toArray(res);
		return res;
	}
	
	/**
	 * Get a String array of the pointings of the day from the ArrayList
	 * @return String[] of the pointings (formated for the list UI)
	 */

	public String[] getPointingsOfTheDay() {
		// Declarations & inits:
		ArrayList<String> pts = new ArrayList<String>();
		Pointing currentPointing = null;
		Iterator<Pointing> ite = pointings.iterator();
		
		// Iterating pointings list : 
		while(ite.hasNext()) {
			currentPointing = ite.next();
			if (currentPointing.getTime().getDayOfMonth() == LocalDateTime.now().getDayOfMonth() && currentPointing.getTime().getMonthValue() == LocalDateTime.now().getMonthValue())
				pts.add((toListString(currentPointing.getTime()) + " - Employee " + currentPointing.getIdEmp()));
		}
		pts.sort(new AlphanumericSortComparator<>(false));
		
		// Convert ArrayList to String array :
		String[] res = new String[pts.size()];
		res = pts.toArray(res);
		return res;
	}
	
	
	//-------------------------------------------------------------Serialize :
	
	/**
	 * Override of the serialize function writeObject
	 * @param aOutputStream
	 * @throws IOException
	 */
    private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
    	aOutputStream.writeObject(employees);
    	aOutputStream.writeObject(departments);
    	aOutputStream.writeObject(pointings);
    }
	
    
	/**
	 * Serializes the ArrayLists and settings of the ControlerMain
	 * @param c : ControlerMain to save
	 */
	public static void serialize(ControlerMain c) {
		try {
			File file = new File("data.ser");
			file.createNewFile(); // Does nothing if file already exists
			FileOutputStream oFile = new FileOutputStream(file, false); // false -> no append
			ObjectOutputStream out = new ObjectOutputStream(oFile);
			
			out.writeObject(c);
			out.close();
			
			System.out.println("Main controler data saved.");
			
		} catch(IOException err) {
			err.printStackTrace();
		}
	}
	
	
	//-------------------------------------------------------------Deserialize :
	
	@SuppressWarnings("unchecked")
	/**
	 * Override of the serialize function readObject
	 * @param aInputStream
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException{
		employees = (ArrayList<Employee>) aInputStream.readObject();
		departments = (ArrayList<Department>) aInputStream.readObject();
		pointings = (ArrayList<Pointing>) aInputStream.readObject();
	}
	
	
	/**
	 * Deserializes the ArrayLists and settings of the ControlerMain
	 * @param c : ControlerMain to load to
	 */
	public static void deserialize(ControlerMain c) {
		try {
			FileInputStream file = new FileInputStream("data.ser");
			ObjectInputStream in = new ObjectInputStream(file);
			
			c = (ControlerMain) in.readObject();
			in.close();
			
			System.out.println("Main controler data loaded.");
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
			
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	
	//-------------------------------------------------------------Main program

	
	/**
	 * Main program
	 * @param args	String Array for called arguments
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

        		ControlerMain controler = new ControlerMain();

        		Toolbox.generateDepartments(controler, 4);
                Toolbox.generateEmployees(controler, 20);
                Toolbox.generatePointings(controler, 40);

                ControlerMain.f = new MainFrame(controler);
                
        		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
                f.setVisible(true);
            }
        });
        
	}
}
