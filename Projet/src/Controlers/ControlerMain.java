package Controlers;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.*;

import javax.swing.JFrame;

import Models.*;
import Views.MainFrame;


/**
 * Class to control the main application
 */
public class ControlerMain extends ControlerNetwork implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6850799842025316456L;


	//private static WindowMain f = new WindowMain();
	private static MainFrame f;
	
	
	private ServerSocket ss = null;
	
	
	private ArrayList<Employee> employees = new ArrayList<Employee>();
	private ArrayList<Department> departments = new ArrayList<Department>();
	private ArrayList<Pointing> pointings = new ArrayList<Pointing>();
	
	
	public JFrame getFrame() {
		return f;
	}
	
	
	public static void updatePointings(String[] items) {
		f.getWindowPointings().updatePointings(items);
	}
	
	private void setSocket() throws IOException {
        isA = new InetSocketAddress(address, port);
        ss = new ServerSocket(isA.getPort());
        s = ss.accept();
    }
	
	//-------------------------------------------------------------Méthode pour employees
	
	public void addEmploye(Employee e) {
		employees.add(e);
	}
	
	public void rmEmploye(Employee e) {
		employees.remove(e);
	}
	
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
		
		// Convert ArrayList to String array :
		String[] res = new String[pts.size()];
		res = pts.toArray(res);
		return res;
	}
	
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
	
	//-------------------------------------------------------------Méthode pour departments
	
	public void addDepartment(Department d) {
		departments.add(d);
	}
	
	public void rmDepartment(Department d) {
		departments.remove(d);
	}
	
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
	
	public Department getDepartment(int id) {
		return departments.get(id);
	}
	
	public ArrayList<Department> getAllDepartment() {
		return departments;
	}
	
	//-------------------------------------------------------------Méthode pour pointings
	
	public void addPointing(Pointing p) {
		pointings.add(p);
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
	
	
	public String toListString(LocalDateTime time) {
		return time.getYear() + " " + time.getMonth() + " " + time.getDayOfMonth() + " " + time.getHour() + ":" + time.getMinute();
	}
	
	
	public String[] getPointings() {
		// Declarations & inits:
		ArrayList<String> pts = new ArrayList<String>();
		Pointing currentPointing = null;
		Iterator<Pointing> ite = pointings.iterator();
		
		// Iterating pointings list : 
		while(ite.hasNext()) {
			currentPointing = ite.next();
			pts.add((toListString(currentPointing.getTime()) + " - " + currentPointing.getIdEmp()));
		}
		pts.sort(null);
		// Convert ArrayList to String array :
		String[] res = new String[pts.size()];
		res = pts.toArray(res);
		return res;
	}
	
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
		// Convert ArrayList to String array :
		String[] res = new String[pts.size()];
		res = pts.toArray(res);
		return res;
	}
	
	//-------------------------------------------------------------Serialize :
	
	public static void serialize(ControlerMain c) {
		try {
			File file = new File("data.ser");
			file.createNewFile();
			FileOutputStream oFile = new FileOutputStream(file, false);
			ObjectOutputStream out = new ObjectOutputStream(oFile);
			out.writeObject(c);
			out.close();
			System.out.println("Main controler data saved.");
		} catch(IOException err) {
			err.printStackTrace();
		}
	}
	
	
	//-------------------------------------------------------------Deserialize :
	
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
        		
                //Toolbox.generateDepartments(controler, 4);
                //Toolbox.generateEmployees(controler, 20);
                //Toolbox.generatePointings(controler, 40);

                //ControlerMain.deserialize(controler);
                
                ControlerMain.f = new MainFrame(controler);
                
        		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
                f.setVisible(true);
                
                //ControlerMain.serialize(controler);
            }
        });
        
	}
}
